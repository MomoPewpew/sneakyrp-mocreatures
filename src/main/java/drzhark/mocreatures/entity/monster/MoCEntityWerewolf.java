/*     */ package drzhark.mocreatures.entity.monster;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityMob;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAILeapAtTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemSword;
/*     */ import net.minecraft.item.ItemTool;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityWerewolf extends MoCEntityMob {
/*     */   private boolean transforming;
/*  40 */   private static final DataParameter<Boolean> IS_HUMAN = EntityDataManager.createKey(MoCEntityWerewolf.class, DataSerializers.BOOLEAN); private int tcounter; private int textCounter;
/*  41 */   private static final DataParameter<Boolean> IS_HUNCHED = EntityDataManager.createKey(MoCEntityWerewolf.class, DataSerializers.BOOLEAN);
/*     */   
/*     */   public MoCEntityWerewolf(World world) {
/*  44 */     super(world);
/*  45 */     setSize(0.9F, 1.6F);
/*  46 */     this.transforming = false;
/*  47 */     this.tcounter = 0;
/*  48 */     setHumanForm(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  53 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  54 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  55 */     this.tasks.addTask(3, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4F));
/*  56 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  57 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  62 */     super.applyEntityAttributes();
/*  63 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
/*  64 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*  65 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  70 */     super.entityInit();
/*  71 */     this.dataManager.register(IS_HUMAN, Boolean.valueOf(false));
/*  72 */     this.dataManager.register(IS_HUNCHED, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHealth(float par1) {
/*  77 */     if (getIsHumanForm() && par1 > 15.0F) {
/*  78 */       par1 = 15.0F;
/*     */     }
/*  80 */     super.setHealth(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  85 */     if (getType() == 0) {
/*  86 */       int k = this.rand.nextInt(100);
/*  87 */       if (k <= 28) {
/*  88 */         setType(1);
/*  89 */       } else if (k <= 56) {
/*  90 */         setType(2);
/*  91 */       } else if (k <= 85) {
/*  92 */         setType(3);
/*     */       } else {
/*  94 */         setType(4);
/*  95 */         this.isImmuneToFire = true;
/*     */       } 
/*     */     } 
/*     */   } public ResourceLocation getTexture() {
/*     */     String NTA;
/*     */     String NTB;
/*     */     String NTC;
/* 102 */     if (getIsHumanForm()) {
/* 103 */       return MoCreatures.proxy.getTexture("wereblank.png");
/*     */     }
/*     */     
/* 106 */     switch (getType()) {
/*     */       case 1:
/* 108 */         return MoCreatures.proxy.getTexture("wolfblack.png");
/*     */       case 2:
/* 110 */         return MoCreatures.proxy.getTexture("wolfbrown.png");
/*     */       case 3:
/* 112 */         return MoCreatures.proxy.getTexture("wolftimber.png");
/*     */       case 4:
/* 114 */         if (!MoCreatures.proxy.getAnimateTextures()) {
/* 115 */           return MoCreatures.proxy.getTexture("wolffire1.png");
/*     */         }
/* 117 */         this.textCounter++;
/* 118 */         if (this.textCounter < 10) {
/* 119 */           this.textCounter = 10;
/*     */         }
/* 121 */         if (this.textCounter > 39) {
/* 122 */           this.textCounter = 10;
/*     */         }
/* 124 */         NTA = "wolffire";
/* 125 */         NTB = "" + this.textCounter;
/* 126 */         NTB = NTB.substring(0, 1);
/* 127 */         NTC = ".png";
/*     */         
/* 129 */         return MoCreatures.proxy.getTexture(NTA + NTB + NTC);
/*     */     } 
/* 131 */     return MoCreatures.proxy.getTexture("wolfbrown.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsHumanForm() {
/* 136 */     return ((Boolean)this.dataManager.get(IS_HUMAN)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setHumanForm(boolean flag) {
/* 140 */     this.dataManager.set(IS_HUMAN, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean getIsHunched() {
/* 144 */     return ((Boolean)this.dataManager.get(IS_HUNCHED)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setHunched(boolean flag) {
/* 148 */     this.dataManager.set(IS_HUNCHED, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity entityIn) {
/* 153 */     if (getIsHumanForm()) {
/* 154 */       setAttackTarget(null);
/* 155 */       return false;
/*     */     } 
/* 157 */     if (getType() == 4 && entityIn instanceof EntityLivingBase) {
/* 158 */       ((EntityLivingBase)entityIn).setFire(10);
/*     */     }
/* 160 */     return super.attackEntityAsMob(entityIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 165 */     Entity entity = damagesource.getTrueSource();
/* 166 */     if (!getIsHumanForm() && entity != null && entity instanceof EntityPlayer) {
/* 167 */       EntityPlayer entityplayer = (EntityPlayer)entity;
/* 168 */       ItemStack stack = entityplayer.getHeldItemMainhand();
/* 169 */       if (!stack.isEmpty()) {
/* 170 */         i = 1.0F;
/* 171 */         if (stack.getItem() == MoCItems.silversword) {
/* 172 */           i = 10.0F;
/*     */         }
/* 174 */         if (stack.getItem() instanceof ItemSword) {
/* 175 */           String swordMaterial = ((ItemSword)stack.getItem()).getToolMaterialName();
/* 176 */           String swordName = ((ItemSword)stack.getItem()).getTranslationKey();
/* 177 */           if (swordMaterial.toLowerCase().contains("silver") || swordName.toLowerCase().contains("silver")) {
/* 178 */             i = ((ItemSword)stack.getItem()).getAttackDamage() * 3.0F;
/*     */           }
/* 180 */         } else if (stack.getItem() instanceof ItemTool) {
/* 181 */           String toolMaterial = ((ItemTool)stack.getItem()).getToolMaterialName();
/* 182 */           String toolName = ((ItemTool)stack.getItem()).getTranslationKey();
/* 183 */           if (toolMaterial.toLowerCase().contains("silver") || toolName.toLowerCase().contains("silver")) {
/* 184 */             i = ((ItemSword)stack.getItem()).getAttackDamage() * 2.0F;
/*     */           }
/* 186 */         } else if (stack.getItem().getTranslationKey().toLowerCase().contains("silver")) {
/* 187 */           i = 6.0F;
/*     */         } 
/*     */       } 
/*     */     } 
/* 191 */     return super.attackEntityFrom(damagesource, i);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldAttackPlayers() {
/* 196 */     return (!getIsHumanForm() && super.shouldAttackPlayers());
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 201 */     int i = this.rand.nextInt(12);
/* 202 */     if (getIsHumanForm()) {
/* 203 */       switch (i) {
/*     */         case 0:
/* 205 */           return Items.WOODEN_SHOVEL;
/*     */         
/*     */         case 1:
/* 208 */           return Items.WOODEN_AXE;
/*     */         
/*     */         case 2:
/* 211 */           return Items.WOODEN_SWORD;
/*     */         
/*     */         case 3:
/* 214 */           return Items.WOODEN_HOE;
/*     */         
/*     */         case 4:
/* 217 */           return Items.WOODEN_PICKAXE;
/*     */       } 
/* 219 */       return Items.STICK;
/*     */     } 
/* 221 */     switch (i) {
/*     */       case 0:
/* 223 */         return Items.IRON_HOE;
/*     */       
/*     */       case 1:
/* 226 */         return Items.IRON_SHOVEL;
/*     */       
/*     */       case 2:
/* 229 */         return Items.IRON_AXE;
/*     */       
/*     */       case 3:
/* 232 */         return Items.IRON_PICKAXE;
/*     */       
/*     */       case 4:
/* 235 */         return Items.IRON_SWORD;
/*     */       
/*     */       case 5:
/* 238 */         return Items.STONE_HOE;
/*     */       
/*     */       case 6:
/* 241 */         return Items.STONE_SHOVEL;
/*     */       
/*     */       case 7:
/* 244 */         return Items.STONE_AXE;
/*     */       
/*     */       case 8:
/* 247 */         return Items.STONE_PICKAXE;
/*     */       
/*     */       case 9:
/* 250 */         return Items.STONE_SWORD;
/*     */     } 
/* 252 */     return Items.GOLDEN_APPLE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 257 */     if (getIsHumanForm()) {
/* 258 */       return MoCSoundEvents.ENTITY_WEREWOLF_DEATH_HUMAN;
/*     */     }
/* 260 */     return MoCSoundEvents.ENTITY_WEREWOLF_DEATH;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 266 */     if (getIsHumanForm()) {
/* 267 */       if (!this.transforming)
/* 268 */         return MoCSoundEvents.ENTITY_WEREWOLF_HURT_HUMAN; 
/* 269 */       return null;
/*     */     } 
/* 271 */     return MoCSoundEvents.ENTITY_WEREWOLF_HURT;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 277 */     if (getIsHumanForm()) {
/* 278 */       return MoCSoundEvents.ENTITY_WEREWOLF_AMBIENT_HUMAN;
/*     */     }
/* 280 */     return MoCSoundEvents.ENTITY_WEREWOLF_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean IsNight() {
/* 285 */     return !this.world.isDaytime();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource damagesource) {
/* 290 */     Entity entity = damagesource.getTrueSource();
/* 291 */     if (this.scoreValue > 0 && entity != null) {
/* 292 */       entity.awardKillScore((Entity)this, this.scoreValue, damagesource);
/*     */     }
/* 294 */     if (entity != null) {
/* 295 */       entity.onKillEntity((EntityLivingBase)this);
/*     */     }
/*     */     
/* 298 */     if (!this.world.isRemote) {
/* 299 */       for (int i = 0; i < 2; i++) {
/* 300 */         Item item = getDropItem();
/* 301 */         if (item != null) {
/* 302 */           dropItem(item, 1);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 311 */     super.onLivingUpdate();
/* 312 */     if (!this.world.isRemote) {
/* 313 */       if (((IsNight() && getIsHumanForm()) || (!IsNight() && !getIsHumanForm())) && this.rand.nextInt(250) == 0) {
/* 314 */         this.transforming = true;
/*     */       }
/* 316 */       if (getIsHumanForm() && getAttackTarget() != null) {
/* 317 */         setAttackTarget(null);
/*     */       }
/* 319 */       if (getAttackTarget() != null && !getIsHumanForm()) {
/* 320 */         boolean hunch = (getDistanceSq((Entity)getAttackTarget()) > 12.0D);
/* 321 */         setHunched(hunch);
/*     */       } 
/*     */       
/* 324 */       if (this.transforming && this.rand.nextInt(3) == 0) {
/* 325 */         this.tcounter++;
/* 326 */         if (this.tcounter % 2 == 0) {
/* 327 */           this.posX += 0.3D;
/* 328 */           this.posY += (this.tcounter / 30);
/* 329 */           attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 1.0F);
/*     */         } 
/* 331 */         if (this.tcounter % 2 != 0) {
/* 332 */           this.posX -= 0.3D;
/*     */         }
/* 334 */         if (this.tcounter == 10) {
/* 335 */           MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_WEREWOLF_TRANSFORM);
/*     */         }
/* 337 */         if (this.tcounter > 30) {
/* 338 */           Transform();
/* 339 */           this.tcounter = 0;
/* 340 */           this.transforming = false;
/*     */         } 
/*     */       } 
/*     */       
/* 344 */       if (this.rand.nextInt(300) == 0) {
/* 345 */         this.idleTime -= 100 * this.world.getDifficulty().getId();
/* 346 */         if (this.idleTime < 0) {
/* 347 */           this.idleTime = 0;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void Transform() {
/* 354 */     if (this.deathTime > 0) {
/*     */       return;
/*     */     }
/* 357 */     int i = MathHelper.floor(this.posX);
/* 358 */     int j = MathHelper.floor((getEntityBoundingBox()).minY) + 1;
/* 359 */     int k = MathHelper.floor(this.posZ);
/* 360 */     float f = 0.1F;
/* 361 */     for (int l = 0; l < 30; l++) {
/* 362 */       double d = (i + this.world.rand.nextFloat());
/* 363 */       double d1 = (j + this.world.rand.nextFloat());
/* 364 */       double d2 = (k + this.world.rand.nextFloat());
/* 365 */       double d3 = d - i;
/* 366 */       double d4 = d1 - j;
/* 367 */       double d5 = d2 - k;
/* 368 */       double d6 = MathHelper.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/* 369 */       d3 /= d6;
/* 370 */       d4 /= d6;
/* 371 */       d5 /= d6;
/* 372 */       double d7 = 0.5D / (d6 / f + 0.1D);
/* 373 */       d7 *= (this.world.rand.nextFloat() * this.world.rand.nextFloat() + 0.3F);
/* 374 */       d3 *= d7;
/* 375 */       d4 *= d7;
/* 376 */       d5 *= d7;
/* 377 */       this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d + i * 1.0D) / 2.0D, (d1 + j * 1.0D) / 2.0D, (d2 + k * 1.0D) / 2.0D, d3, d4, d5, new int[0]);
/*     */     } 
/*     */ 
/*     */     
/* 381 */     if (getIsHumanForm()) {
/* 382 */       setHumanForm(false);
/* 383 */       setHealth(40.0F);
/* 384 */       this.transforming = false;
/* 385 */       getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
/*     */     } else {
/* 387 */       setHumanForm(true);
/* 388 */       setHealth(15.0F);
/* 389 */       this.transforming = false;
/* 390 */       getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 396 */     super.readEntityFromNBT(nbttagcompound);
/* 397 */     setHumanForm(nbttagcompound.getBoolean("HumanForm"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 402 */     super.writeEntityToNBT(nbttagcompound);
/* 403 */     nbttagcompound.setBoolean("HumanForm", getIsHumanForm());
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAIMoveSpeed() {
/* 408 */     if (getIsHumanForm()) {
/* 409 */       return 0.1F;
/*     */     }
/* 411 */     if (getIsHunched()) {
/* 412 */       return 0.35F;
/*     */     }
/* 414 */     return 0.2F;
/*     */   }
/*     */ 
/*     */   
/*     */   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
/* 419 */     if (getType() == 4) {
/* 420 */       this.isImmuneToFire = true;
/*     */     }
/* 422 */     return super.onInitialSpawn(difficulty, livingdata);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityWerewolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */