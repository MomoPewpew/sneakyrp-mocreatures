/*     */ package drzhark.mocreatures.entity.passive;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIHunt;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityCrocodile
/*     */   extends MoCEntityTameableAnimal {
/*     */   public float biteProgress;
/*     */   public float spin;
/*  34 */   private static final DataParameter<Boolean> IS_RESTING = EntityDataManager.createKey(MoCEntityCrocodile.class, DataSerializers.BOOLEAN); public int spinInt; private boolean waterbound;
/*  35 */   private static final DataParameter<Boolean> EATING_PREY = EntityDataManager.createKey(MoCEntityCrocodile.class, DataSerializers.BOOLEAN);
/*  36 */   private static final DataParameter<Boolean> IS_BITING = EntityDataManager.createKey(MoCEntityCrocodile.class, DataSerializers.BOOLEAN);
/*     */   
/*     */   public MoCEntityCrocodile(World world) {
/*  39 */     super(world);
/*  40 */     this.texture = "crocodile.png";
/*  41 */     setSize(1.4F, 0.6F);
/*  42 */     setEdad(50 + this.rand.nextInt(50));
/*  43 */     setTamed(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  48 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 0.8D, 4.0D));
/*  49 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  50 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.9D));
/*  51 */     this.tasks.addTask(9, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  52 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
/*  53 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  59 */     super.applyEntityAttributes();
/*  60 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
/*  61 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  62 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
/*  63 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  68 */     super.entityInit();
/*  69 */     this.dataManager.register(IS_RESTING, Boolean.valueOf(false));
/*  70 */     this.dataManager.register(EATING_PREY, Boolean.valueOf(false));
/*  71 */     this.dataManager.register(IS_BITING, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean getIsBiting() {
/*  75 */     return ((Boolean)this.dataManager.get(IS_BITING)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean getIsSitting() {
/*  79 */     return ((Boolean)this.dataManager.get(IS_RESTING)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean getHasCaughtPrey() {
/*  83 */     return ((Boolean)this.dataManager.get(EATING_PREY)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setBiting(boolean flag) {
/*  87 */     this.dataManager.set(IS_BITING, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public void setIsSitting(boolean flag) {
/*  91 */     this.dataManager.set(IS_RESTING, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public void setHasCaughtPrey(boolean flag) {
/*  95 */     this.dataManager.set(EATING_PREY, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void jump() {
/* 101 */     if (isSwimming()) {
/* 102 */       if (getHasCaughtPrey()) {
/*     */         return;
/*     */       }
/*     */       
/* 106 */       this.motionY = 0.3D;
/* 107 */       this.isAirBorne = true;
/*     */     }
/* 109 */     else if (getAttackTarget() != null || getHasCaughtPrey()) {
/* 110 */       super.jump();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMovementCeased() {
/* 116 */     return getIsSitting();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 121 */     if (getIsSitting()) {
/* 122 */       this.rotationPitch = -5.0F;
/* 123 */       if (!isSwimming() && this.biteProgress < 0.3F && this.rand.nextInt(5) == 0) {
/* 124 */         this.biteProgress += 0.005F;
/*     */       }
/* 126 */       if (getAttackTarget() != null) {
/* 127 */         setIsSitting(false);
/*     */       }
/* 129 */       if ((!this.world.isRemote && getAttackTarget() != null) || isSwimming() || getHasCaughtPrey() || this.rand.nextInt(500) == 0)
/*     */       {
/* 131 */         setIsSitting(false);
/* 132 */         this.biteProgress = 0.0F;
/*     */       }
/*     */     
/*     */     }
/* 136 */     else if (!this.world.isRemote && this.rand.nextInt(500) == 0 && getAttackTarget() == null && !getHasCaughtPrey() && !isSwimming()) {
/* 137 */       setIsSitting(true);
/* 138 */       getNavigator().clearPath();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 143 */     if (this.rand.nextInt(500) == 0 && !getHasCaughtPrey() && !getIsSitting()) {
/* 144 */       crocBite();
/*     */     }
/*     */ 
/*     */     
/* 148 */     if (!this.world.isRemote && this.rand.nextInt(500) == 0 && !this.waterbound && !getIsSitting() && !isSwimming()) {
/* 149 */       MoCTools.MoveToWater((EntityCreature)this);
/*     */     }
/*     */     
/* 152 */     if (this.waterbound) {
/* 153 */       if (!isInsideOfMaterial(Material.WATER)) {
/* 154 */         MoCTools.MoveToWater((EntityCreature)this);
/*     */       } else {
/* 156 */         this.waterbound = false;
/*     */       } 
/*     */     }
/*     */     
/* 160 */     if (getHasCaughtPrey()) {
/* 161 */       if (isBeingRidden()) {
/* 162 */         setAttackTarget(null);
/*     */         
/* 164 */         this.biteProgress = 0.4F;
/* 165 */         setIsSitting(false);
/*     */         
/* 167 */         if (!isInsideOfMaterial(Material.WATER)) {
/* 168 */           this.waterbound = true;
/* 169 */           if (getRidingEntity() instanceof EntityLiving && ((EntityLivingBase)getRidingEntity()).getHealth() > 0.0F) {
/* 170 */             ((EntityLivingBase)getRidingEntity()).deathTime = 0;
/*     */           }
/*     */           
/* 173 */           if (!this.world.isRemote && this.rand.nextInt(50) == 0) {
/* 174 */             getRidingEntity().attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 2.0F);
/*     */           }
/*     */         } 
/*     */       } else {
/* 178 */         setHasCaughtPrey(false);
/* 179 */         this.biteProgress = 0.0F;
/* 180 */         this.waterbound = false;
/*     */       } 
/*     */       
/* 183 */       if (isSpinning()) {
/* 184 */         this.spinInt += 3;
/* 185 */         if (this.spinInt % 20 == 0) {
/* 186 */           MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_CROCODILE_ROLL);
/*     */         }
/* 188 */         if (this.spinInt > 80) {
/* 189 */           this.spinInt = 0;
/* 190 */           getRidingEntity().attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 4.0F);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 196 */         if (this.world.isRemote || !isBeingRidden() || getRidingEntity() instanceof EntityPlayer);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/* 208 */     return true;
/*     */   }
/*     */   
/*     */   public void crocBite() {
/* 212 */     if (!getIsBiting()) {
/* 213 */       setBiting(true);
/* 214 */       this.biteProgress = 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 220 */     if (getIsBiting() && !getHasCaughtPrey()) {
/*     */       
/* 222 */       this.biteProgress += 0.1F;
/* 223 */       if (this.biteProgress == 0.4F) {
/* 224 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_CROCODILE_JAWSNAP);
/*     */       }
/* 226 */       if (this.biteProgress > 0.6F) {
/*     */         
/* 228 */         setBiting(false);
/* 229 */         this.biteProgress = 0.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 233 */     super.onUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity entityIn) {
/* 238 */     if (getHasCaughtPrey()) {
/* 239 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 249 */     crocBite();
/* 250 */     setHasCaughtPrey(false);
/* 251 */     return super.attackEntityAsMob(entityIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 256 */     if (isBeingRidden()) {
/*     */       
/* 258 */       Entity entity = damagesource.getTrueSource();
/* 259 */       if (entity != null && getRidingEntity() == entity) {
/* 260 */         if (this.rand.nextInt(2) != 0) {
/* 261 */           return false;
/*     */         }
/* 263 */         unMount();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 268 */     if (super.attackEntityFrom(damagesource, i)) {
/* 269 */       Entity entity = damagesource.getTrueSource();
/*     */       
/* 271 */       if (isBeingRidden() && getRidingEntity() == entity && 
/* 272 */         entity != this && entity instanceof EntityLivingBase && shouldAttackPlayers()) {
/* 273 */         setAttackTarget((EntityLivingBase)entity);
/*     */       }
/*     */       
/* 276 */       return true;
/*     */     } 
/* 278 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 284 */     return !(entity instanceof MoCEntityCrocodile);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePassenger(Entity passenger) {
/* 289 */     if (!isBeingRidden()) {
/*     */       return;
/*     */     }
/* 292 */     int direction = 1;
/*     */     
/* 294 */     double dist = (getEdad() * 0.01F + passenger.width) - 0.4D;
/* 295 */     double newPosX = this.posX - dist * Math.cos((MoCTools.realAngle(this.rotationYaw - 90.0F) / 57.29578F));
/* 296 */     double newPosZ = this.posZ - dist * Math.sin((MoCTools.realAngle(this.rotationYaw - 90.0F) / 57.29578F));
/* 297 */     passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
/*     */     
/* 299 */     if (this.spinInt > 40) {
/* 300 */       direction = -1;
/*     */     } else {
/* 302 */       direction = 1;
/*     */     } 
/*     */     
/* 305 */     ((EntityLivingBase)passenger).renderYawOffset = this.rotationYaw * direction;
/* 306 */     ((EntityLivingBase)passenger).prevRenderYawOffset = this.rotationYaw * direction;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMountedYOffset() {
/* 311 */     return this.height * 0.35D;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 316 */     return MoCSoundEvents.ENTITY_CROCODILE_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 321 */     return MoCSoundEvents.ENTITY_CROCODILE_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 326 */     if (getIsSitting()) {
/* 327 */       return MoCSoundEvents.ENTITY_CROCODILE_RESTING;
/*     */     }
/* 329 */     return MoCSoundEvents.ENTITY_CROCODILE_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 334 */     return (Item)MoCItems.hideCroc;
/*     */   }
/*     */   
/*     */   public boolean isSpinning() {
/* 338 */     return (getHasCaughtPrey() && isBeingRidden() && isSwimming());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource damagesource) {
/* 344 */     unMount();
/* 345 */     MoCTools.checkForTwistedEntities(this.world);
/* 346 */     super.onDeath(damagesource);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unMount() {
/* 351 */     if (isBeingRidden()) {
/* 352 */       if (getRidingEntity() instanceof EntityLiving && ((EntityLivingBase)getRidingEntity()).getHealth() > 0.0F) {
/* 353 */         ((EntityLivingBase)getRidingEntity()).deathTime = 0;
/*     */       }
/*     */       
/* 356 */       dismountRidingEntity();
/* 357 */       setHasCaughtPrey(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTalkInterval() {
/* 363 */     return 400;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAmphibian() {
/* 368 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSwimming() {
/* 373 */     return isInWater();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadyToHunt() {
/* 378 */     return (isNotScared() && !isMovementCeased() && !isBeingRidden() && !getHasCaughtPrey());
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityCrocodile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */