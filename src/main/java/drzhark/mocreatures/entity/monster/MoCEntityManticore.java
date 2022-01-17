/*     */ package drzhark.mocreatures.entity.monster;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityMob;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ 
/*     */ public class MoCEntityManticore
/*     */   extends MoCEntityMob {
/*     */   public int mouthCounter;
/*     */   public int tailCounter;
/*     */   
/*     */   public MoCEntityManticore(World world) {
/*  41 */     super(world);
/*  42 */     setSize(1.4F, 1.6F);
/*  43 */     this.isImmuneToFire = true;
/*     */   }
/*     */   public int wingFlapCounter; private boolean isPoisoning; private int poisontimer;
/*     */   
/*     */   protected void initEntityAI() {
/*  48 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  49 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  50 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  55 */     super.applyEntityAttributes();
/*  56 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
/*  57 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
/*  58 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  63 */     checkSpawningBiome();
/*     */     
/*  65 */     if (getType() == 0) {
/*  66 */       setType(this.rand.nextInt(2) * 2 + 2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkSpawningBiome() {
/*  72 */     if (this.world.provider.doesWaterVaporize()) {
/*  73 */       setType(1);
/*  74 */       this.isImmuneToFire = true;
/*  75 */       return true;
/*     */     } 
/*     */     
/*  78 */     int i = MathHelper.floor(this.posX);
/*  79 */     int j = MathHelper.floor((getEntityBoundingBox()).minY);
/*  80 */     int k = MathHelper.floor(this.posZ);
/*  81 */     BlockPos pos = new BlockPos(i, j, k);
/*     */     
/*  83 */     Biome currentbiome = MoCTools.Biomekind(this.world, pos);
/*     */     
/*  85 */     if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
/*  86 */       setType(3);
/*     */     }
/*     */     
/*  89 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  94 */     switch (getType()) {
/*     */       case 1:
/*  96 */         return MoCreatures.proxy.getTexture("bcmanticore.png");
/*     */       case 2:
/*  98 */         return MoCreatures.proxy.getTexture("bcmanticoredark.png");
/*     */       case 3:
/* 100 */         return MoCreatures.proxy.getTexture("bcmanticoreblue.png");
/*     */       case 4:
/* 102 */         return MoCreatures.proxy.getTexture("bcmanticoregreen.png");
/*     */     } 
/* 104 */     return MoCreatures.proxy.getTexture("bcmanticoregreen.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMoveSpeed() {
/* 115 */     return 0.9F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity entityIn) {
/* 128 */     return super.attackEntityAsMob(entityIn);
/*     */   }
/*     */   
/*     */   public boolean getIsRideable() {
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isHarmedByDaylight() {
/* 137 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int maxFlyingHeight() {
/* 142 */     return 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public int minFlyingHeight() {
/* 147 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePassenger(Entity passenger) {
/* 152 */     double dist = -0.1D;
/* 153 */     double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
/* 154 */     double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
/* 155 */     passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
/* 156 */     passenger.rotationYaw = this.rotationYaw;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMountedYOffset() {
/* 161 */     return this.height * 0.75D - 0.1D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void openMouth() {
/* 178 */     this.mouthCounter = 1;
/*     */   }
/*     */   
/*     */   private void moveTail() {
/* 182 */     this.tailCounter = 1;
/*     */   }
/*     */   
/*     */   public boolean isOnAir() {
/* 186 */     return this.world.isAirBlock(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY - 0.2D), 
/* 187 */           MathHelper.floor(this.posZ)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 192 */     super.onUpdate();
/*     */     
/* 194 */     if (this.mouthCounter > 0 && ++this.mouthCounter > 30) {
/* 195 */       this.mouthCounter = 0;
/*     */     }
/*     */     
/* 198 */     if (this.tailCounter > 0 && ++this.tailCounter > 8) {
/* 199 */       this.tailCounter = 0;
/*     */     }
/*     */     
/* 202 */     if (this.wingFlapCounter > 0 && ++this.wingFlapCounter > 20) {
/* 203 */       this.wingFlapCounter = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 210 */     super.onLivingUpdate();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 219 */     if (isOnAir() && isFlyer() && this.rand.nextInt(5) == 0) {
/* 220 */       this.wingFlapCounter = 1;
/*     */     }
/*     */     
/* 223 */     if (this.rand.nextInt(200) == 0) {
/* 224 */       moveTail();
/*     */     }
/*     */     
/* 227 */     if (!this.world.isRemote && isFlyer() && isOnAir()) {
/* 228 */       float myFlyingSpeed = MoCTools.getMyMovementSpeed((Entity)this);
/* 229 */       int wingFlapFreq = (int)(25.0F - myFlyingSpeed * 10.0F);
/* 230 */       if (!isBeingRidden() || wingFlapFreq < 5) {
/* 231 */         wingFlapFreq = 5;
/*     */       }
/* 233 */       if (this.rand.nextInt(wingFlapFreq) == 0) {
/* 234 */         wingFlap();
/*     */       }
/*     */     } 
/*     */     
/* 238 */     if (isFlyer()) {
/* 239 */       if (this.wingFlapCounter > 0 && ++this.wingFlapCounter > 20) {
/* 240 */         this.wingFlapCounter = 0;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 245 */       if (this.wingFlapCounter == 5 && !this.world.isRemote)
/*     */       {
/* 247 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_WINGFLAP);
/*     */       }
/*     */     } 
/*     */     
/* 251 */     if (getIsPoisoning()) {
/* 252 */       this.poisontimer++;
/* 253 */       if (this.poisontimer == 1) {
/* 254 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SCORPION_STING);
/*     */       }
/* 256 */       if (this.poisontimer > 50) {
/* 257 */         this.poisontimer = 0;
/* 258 */         setPoisoning(false);
/*     */       } 
/*     */     } 
/* 261 */     if (!this.world.isRemote) {
/* 262 */       if (isFlyer() && this.rand.nextInt(500) == 0) {
/* 263 */         wingFlap();
/*     */       }
/*     */       
/* 266 */       if (!isBeingRidden() && this.rand.nextInt(200) == 0) {
/* 267 */         MoCTools.findMobRider((Entity)this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void makeEntityJump() {
/* 274 */     wingFlap();
/* 275 */     super.makeEntityJump();
/*     */   }
/*     */   
/*     */   public void wingFlap() {
/* 279 */     if (isFlyer() && this.wingFlapCounter == 0) {
/* 280 */       this.wingFlapCounter = 1;
/* 281 */       if (!this.world.isRemote) {
/* 282 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 3), new NetworkRegistry.TargetPoint(this.world.provider
/* 283 */               .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void performAnimation(int animationType) {
/* 295 */     if (animationType == 0) {
/*     */       
/* 297 */       setPoisoning(true);
/* 298 */     } else if (animationType == 3) {
/*     */       
/* 300 */       this.wingFlapCounter = 1;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getIsPoisoning() {
/* 305 */     return this.isPoisoning;
/*     */   }
/*     */   
/*     */   public void setPoisoning(boolean flag) {
/* 309 */     if (flag && !this.world.isRemote) {
/* 310 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
/* 311 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/* 313 */     this.isPoisoning = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 318 */     if (super.attackEntityFrom(damagesource, i)) {
/* 319 */       Entity entity = damagesource.getTrueSource();
/*     */       
/* 321 */       if (entity != null && entity != this && entity instanceof EntityLivingBase && shouldAttackPlayers() && getIsAdult()) {
/* 322 */         setAttackTarget((EntityLivingBase)entity);
/*     */       }
/* 324 */       return true;
/*     */     } 
/* 326 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
/* 332 */     boolean flag = entityIn instanceof EntityPlayer;
/* 333 */     if (!getIsPoisoning() && this.rand.nextInt(5) == 0 && entityIn instanceof EntityLivingBase) {
/* 334 */       setPoisoning(true);
/* 335 */       if (getType() == 4 || getType() == 2) {
/*     */         
/* 337 */         if (flag) {
/* 338 */           MoCreatures.poisonPlayer((EntityPlayer)entityIn);
/*     */         }
/* 340 */         ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 70, 0));
/* 341 */       } else if (getType() == 3) {
/*     */         
/* 343 */         if (flag) {
/* 344 */           MoCreatures.freezePlayer((EntityPlayer)entityIn);
/*     */         }
/* 346 */         ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 70, 0));
/*     */       }
/* 348 */       else if (getType() == 1) {
/*     */         
/* 350 */         if (flag && !this.world.isRemote && !this.world.provider.doesWaterVaporize()) {
/* 351 */           MoCreatures.burnPlayer((EntityPlayer)entityIn);
/* 352 */           ((EntityLivingBase)entityIn).setFire(15);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 356 */       openMouth();
/*     */     } 
/* 358 */     super.applyEnchantments(entityLivingBaseIn, entityIn);
/*     */   }
/*     */   
/*     */   public boolean swingingTail() {
/* 362 */     return (getIsPoisoning() && this.poisontimer < 15);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 367 */     openMouth();
/* 368 */     return MoCSoundEvents.ENTITY_LION_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 373 */     openMouth();
/* 374 */     return MoCSoundEvents.ENTITY_LION_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 379 */     openMouth();
/* 380 */     return MoCSoundEvents.ENTITY_LION_AMBIENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSizeFactor() {
/* 400 */     return 1.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 405 */     boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);
/*     */     
/* 407 */     switch (getType()) {
/*     */       case 1:
/* 409 */         if (flag) {
/* 410 */           return (Item)MoCItems.scorpStingNether;
/*     */         }
/* 412 */         return (Item)MoCItems.chitinNether;
/*     */       case 2:
/* 414 */         if (flag) {
/* 415 */           return (Item)MoCItems.scorpStingCave;
/*     */         }
/* 417 */         return (Item)MoCItems.chitinCave;
/*     */       
/*     */       case 3:
/* 420 */         if (flag) {
/* 421 */           return (Item)MoCItems.scorpStingFrost;
/*     */         }
/* 423 */         return (Item)MoCItems.chitinFrost;
/*     */       case 4:
/* 425 */         if (flag) {
/* 426 */           return (Item)MoCItems.scorpStingDirt;
/*     */         }
/* 428 */         return (Item)MoCItems.chitin;
/*     */     } 
/*     */     
/* 431 */     return (Item)MoCItems.chitin;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean flag, int x) {
/* 437 */     BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), this.posZ);
/* 438 */     int chance = MoCreatures.proxy.rareItemDropChance;
/* 439 */     if (this.rand.nextInt(100) < chance) {
/* 440 */       entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, getType() + 61), 0.0F);
/*     */     } else {
/* 442 */       super.dropFewItems(flag, x);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityManticore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */