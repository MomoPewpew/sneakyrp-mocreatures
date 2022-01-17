/*     */ package drzhark.mocreatures.entity.monster;
/*     */ 
/*     */ import drzhark.mocreatures.MoCEntityData;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityMob;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityPetScorpion;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIFleeSun;
/*     */ import net.minecraft.entity.ai.EntityAILeapAtTarget;
/*     */ import net.minecraft.entity.ai.EntityAIRestrictSun;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
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
/*     */ public class MoCEntityScorpion extends MoCEntityMob {
/*     */   private boolean isPoisoning;
/*     */   private int poisontimer;
/*  50 */   private static final DataParameter<Boolean> IS_PICKED = EntityDataManager.createKey(MoCEntityScorpion.class, DataSerializers.BOOLEAN); public int mouthCounter; public int armCounter;
/*  51 */   private static final DataParameter<Boolean> HAS_BABIES = EntityDataManager.createKey(MoCEntityScorpion.class, DataSerializers.BOOLEAN);
/*     */   
/*     */   public MoCEntityScorpion(World world) {
/*  54 */     super(world);
/*  55 */     setSize(1.4F, 0.9F);
/*  56 */     this.poisontimer = 0;
/*  57 */     setAdult(true);
/*  58 */     setEdad(20);
/*     */     
/*  60 */     if (!this.world.isRemote) {
/*  61 */       if (this.rand.nextInt(4) == 0) {
/*  62 */         setHasBabies(true);
/*     */       } else {
/*  64 */         setHasBabies(false);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  71 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  72 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  73 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIRestrictSun((EntityCreature)this));
/*  74 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIFleeSun((EntityCreature)this, 1.0D));
/*  75 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 1.2D, 4.0D));
/*  76 */     this.tasks.addTask(6, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4F));
/*  77 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  82 */     super.applyEntityAttributes();
/*  83 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
/*  84 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
/*  85 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  90 */     checkSpawningBiome();
/*     */     
/*  92 */     if (getType() == 0) {
/*  93 */       setType(1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  99 */     switch (getType()) {
/*     */       case 1:
/* 101 */         return MoCreatures.proxy.getTexture("scorpiondirt.png");
/*     */       case 2:
/* 103 */         return MoCreatures.proxy.getTexture("scorpioncave.png");
/*     */       case 3:
/* 105 */         return MoCreatures.proxy.getTexture("scorpionnether.png");
/*     */       case 4:
/* 107 */         return MoCreatures.proxy.getTexture("scorpionfrost.png");
/*     */     } 
/* 109 */     return MoCreatures.proxy.getTexture("scorpiondirt.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 115 */     super.entityInit();
/* 116 */     this.dataManager.register(IS_PICKED, Boolean.valueOf(false));
/* 117 */     this.dataManager.register(HAS_BABIES, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean getHasBabies() {
/* 121 */     return ((Boolean)this.dataManager.get(HAS_BABIES)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean getIsPicked() {
/* 125 */     return ((Boolean)this.dataManager.get(IS_PICKED)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean getIsPoisoning() {
/* 129 */     return this.isPoisoning;
/*     */   }
/*     */   
/*     */   public void setHasBabies(boolean flag) {
/* 133 */     this.dataManager.set(HAS_BABIES, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public void setPicked(boolean flag) {
/* 137 */     this.dataManager.set(IS_PICKED, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public void setPoisoning(boolean flag) {
/* 141 */     if (flag && !this.world.isRemote) {
/* 142 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
/* 143 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/* 145 */     this.isPoisoning = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void performAnimation(int animationType) {
/* 150 */     if (animationType == 0) {
/*     */       
/* 152 */       setPoisoning(true);
/* 153 */     } else if (animationType == 1) {
/*     */       
/* 155 */       this.armCounter = 1;
/*     */     }
/* 157 */     else if (animationType == 3) {
/*     */       
/* 159 */       this.mouthCounter = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMoveSpeed() {
/* 165 */     return 0.8F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnLadder() {
/* 170 */     return this.collidedHorizontally;
/*     */   }
/*     */   
/*     */   public boolean climbing() {
/* 174 */     return (!this.onGround && isOnLadder());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 180 */     if (!this.onGround && getRidingEntity() != null) {
/* 181 */       this.rotationYaw = (getRidingEntity()).rotationYaw;
/*     */     }
/*     */     
/* 184 */     if (this.mouthCounter != 0 && this.mouthCounter++ > 50) {
/* 185 */       this.mouthCounter = 0;
/*     */     }
/*     */     
/* 188 */     if (!this.world.isRemote && (this.armCounter == 10 || this.armCounter == 40)) {
/* 189 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SCORPION_CLAW);
/*     */     }
/*     */     
/* 192 */     if (this.armCounter != 0 && this.armCounter++ > 24) {
/* 193 */       this.armCounter = 0;
/*     */     }
/*     */     
/* 196 */     if (!this.world.isRemote && !isBeingRidden() && getIsAdult() && !getHasBabies() && this.rand.nextInt(100) == 0) {
/* 197 */       MoCTools.findMobRider((Entity)this);
/*     */     }
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
/* 213 */     if (getIsPoisoning()) {
/* 214 */       this.poisontimer++;
/* 215 */       if (this.poisontimer == 1) {
/* 216 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SCORPION_STING);
/*     */       }
/* 218 */       if (this.poisontimer > 50) {
/* 219 */         this.poisontimer = 0;
/* 220 */         setPoisoning(false);
/*     */       } 
/*     */     } 
/* 223 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 228 */     if (super.attackEntityFrom(damagesource, i)) {
/* 229 */       Entity entity = damagesource.getTrueSource();
/*     */       
/* 231 */       if (entity != null && entity != this && entity instanceof EntityLivingBase && shouldAttackPlayers() && getIsAdult()) {
/* 232 */         setAttackTarget((EntityLivingBase)entity);
/*     */       }
/* 234 */       return true;
/*     */     } 
/* 236 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean entitiesToIgnore(Entity entity) {
/* 242 */     return (super.entitiesToIgnore(entity) || (getIsTamed() && entity instanceof MoCEntityScorpion && ((MoCEntityScorpion)entity)
/* 243 */       .getIsTamed()));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
/* 248 */     boolean flag = entityIn instanceof EntityPlayer;
/* 249 */     if (!getIsPoisoning() && this.rand.nextInt(5) == 0 && entityIn instanceof EntityLivingBase) {
/* 250 */       setPoisoning(true);
/* 251 */       if (getType() <= 2) {
/*     */         
/* 253 */         if (flag) {
/* 254 */           MoCreatures.poisonPlayer((EntityPlayer)entityIn);
/*     */         }
/* 256 */         ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 70, 0));
/* 257 */       } else if (getType() == 4) {
/*     */         
/* 259 */         if (flag) {
/* 260 */           MoCreatures.freezePlayer((EntityPlayer)entityIn);
/*     */         }
/* 262 */         ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 70, 0));
/*     */       }
/* 264 */       else if (getType() == 3) {
/*     */         
/* 266 */         if (!this.world.isRemote && flag && !this.world.provider.doesWaterVaporize()) {
/* 267 */           MoCreatures.burnPlayer((EntityPlayer)entityIn);
/* 268 */           ((EntityLivingBase)entityIn).setFire(15);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 272 */       swingArm();
/*     */     } 
/* 274 */     super.applyEnchantments(entityLivingBaseIn, entityIn);
/*     */   }
/*     */   
/*     */   public void swingArm() {
/* 278 */     if (!this.world.isRemote) {
/* 279 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
/* 280 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 286 */     super.onUpdate();
/*     */   }
/*     */   
/*     */   public boolean swingingTail() {
/* 290 */     return (getIsPoisoning() && this.poisontimer < 15);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource damagesource) {
/* 295 */     super.onDeath(damagesource);
/* 296 */     if (!this.world.isRemote && getIsAdult() && getHasBabies()) {
/* 297 */       int k = this.rand.nextInt(5);
/* 298 */       for (int i = 0; i < k; i++) {
/* 299 */         MoCEntityPetScorpion entityscorpy = new MoCEntityPetScorpion(this.world);
/* 300 */         entityscorpy.setPosition(this.posX, this.posY, this.posZ);
/* 301 */         entityscorpy.setAdult(false);
/* 302 */         entityscorpy.setEdad(20);
/* 303 */         entityscorpy.setType(getType());
/* 304 */         this.world.spawnEntity((Entity)entityscorpy);
/* 305 */         MoCTools.playCustomSound((Entity)entityscorpy, SoundEvents.ENTITY_CHICKEN_EGG);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 312 */     return MoCSoundEvents.ENTITY_SCORPION_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 317 */     return MoCSoundEvents.ENTITY_SCORPION_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 322 */     if (!this.world.isRemote) {
/* 323 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 3), new NetworkRegistry.TargetPoint(this.world.provider
/* 324 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/* 326 */     return MoCSoundEvents.ENTITY_SCORPION_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 331 */     if (!getIsAdult()) {
/* 332 */       return Items.STRING;
/*     */     }
/*     */     
/* 335 */     boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);
/*     */     
/* 337 */     switch (getType()) {
/*     */       case 1:
/* 339 */         if (flag) {
/* 340 */           return (Item)MoCItems.scorpStingDirt;
/*     */         }
/* 342 */         return (Item)MoCItems.chitin;
/*     */       case 2:
/* 344 */         if (flag) {
/* 345 */           return (Item)MoCItems.scorpStingCave;
/*     */         }
/* 347 */         return (Item)MoCItems.chitinCave;
/*     */       case 3:
/* 349 */         if (flag) {
/* 350 */           return (Item)MoCItems.scorpStingNether;
/*     */         }
/* 352 */         return (Item)MoCItems.chitinNether;
/*     */       case 4:
/* 354 */         if (flag) {
/* 355 */           return (Item)MoCItems.scorpStingFrost;
/*     */         }
/* 357 */         return (Item)MoCItems.chitinFrost;
/*     */     } 
/* 359 */     return Items.STRING;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean flag, int x) {
/* 365 */     if (!flag) {
/*     */       return;
/*     */     }
/* 368 */     Item item = getDropItem();
/*     */     
/* 370 */     if (item != null && 
/* 371 */       this.rand.nextInt(3) == 0) {
/* 372 */       dropItem(item, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 380 */     return (isValidLightLevel() && ((MoCEntityData)MoCreatures.entityMap.get(getClass())).getFrequency() > 0 && getCanSpawnHereLiving() && 
/* 381 */       getCanSpawnHereCreature());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkSpawningBiome() {
/* 386 */     if (this.world.provider.doesWaterVaporize()) {
/* 387 */       setType(3);
/* 388 */       this.isImmuneToFire = true;
/* 389 */       return true;
/*     */     } 
/*     */     
/* 392 */     int i = MathHelper.floor(this.posX);
/* 393 */     int j = MathHelper.floor((getEntityBoundingBox()).minY);
/* 394 */     int k = MathHelper.floor(this.posZ);
/* 395 */     BlockPos pos = new BlockPos(i, j, k);
/*     */     
/* 397 */     Biome currentbiome = MoCTools.Biomekind(this.world, pos);
/*     */     
/* 399 */     if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
/* 400 */       setType(4);
/* 401 */     } else if (!this.world.canBlockSeeSky(pos) && this.posY < 50.0D) {
/* 402 */       setType(2);
/* 403 */       return true;
/*     */     } 
/*     */     
/* 406 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 411 */     super.readEntityFromNBT(nbttagcompound);
/* 412 */     setHasBabies(nbttagcompound.getBoolean("Babies"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 417 */     super.writeEntityToNBT(nbttagcompound);
/* 418 */     nbttagcompound.setBoolean("Babies", getHasBabies());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTalkInterval() {
/* 423 */     return 300;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 431 */     return EnumCreatureAttribute.ARTHROPOD;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAdjustedYOffset() {
/* 436 */     return 30.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getMaxEdad() {
/* 441 */     return 120;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/* 446 */     return (getIsAdult() || getEdad() > 70);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMountedYOffset() {
/* 451 */     return this.height * 0.75D - 0.15D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePassenger(Entity passenger) {
/* 456 */     double dist = 0.2D;
/* 457 */     double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
/* 458 */     double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
/* 459 */     passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
/* 460 */     passenger.rotationYaw = this.rotationYaw;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityScorpion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */