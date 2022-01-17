/*     */ package drzhark.mocreatures.entity.passive;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.MoCEntityAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIHunt;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.entity.item.MoCEntityEgg;
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
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */
/*     */ public class MoCEntityPetScorpion extends MoCEntityTameableAnimal {
/*  43 */   public static final String[] scorpionNames = new String[] { "Dirt", "Cave", "Nether", "Frost", "Undead" };
/*     */   private boolean isPoisoning;
/*     */   private int poisontimer;
/*     */   public int mouthCounter;
/*     */   public int armCounter;
/*     */   private int transformCounter;
/*  49 */   private static final DataParameter<Boolean> RIDEABLE = EntityDataManager.createKey(MoCEntityPetScorpion.class, DataSerializers.BOOLEAN);
/*  50 */   private static final DataParameter<Boolean> HAS_BABIES = EntityDataManager.createKey(MoCEntityPetScorpion.class, DataSerializers.BOOLEAN);
/*  51 */   private static final DataParameter<Boolean> IS_SITTING = EntityDataManager.createKey(MoCEntityPetScorpion.class, DataSerializers.BOOLEAN);
/*     */
/*     */   public MoCEntityPetScorpion(World world) {
/*  54 */     super(world);
/*  55 */     setSize(1.4F, 0.9F);
/*  56 */     this.poisontimer = 0;
/*  57 */     setAdult(false);
/*  58 */     setEdad(20);
/*  59 */     setHasBabies(false);
/*  60 */     this.stepHeight = 20.0F;
/*     */   }
/*     */
/*     */
/*     */   protected void initEntityAI() {
/*  65 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  66 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  67 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*  68 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 1.2D, 4.0D));
/*  69 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 1.0D, 2.0F, 10.0F));
/*  70 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  71 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
/*     */   }
/*     */
/*     */
/*     */   protected void applyEntityAttributes() {
/*  76 */     super.applyEntityAttributes();
/*  77 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  78 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
/*  79 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
/*  80 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
/*     */   }
/*     */
/*     */
/*     */   public void selectType() {
/*  85 */     if (getType() == 0) {
/*  86 */       setType(1);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public ResourceLocation getTexture() {
/*  92 */     boolean saddle = getIsRideable();
/*     */
/*  94 */     if (this.transformCounter != 0) {
/*  95 */       String newText = saddle ? "scorpionundeadsaddle.png" : "scorpionundead.png";
/*  96 */       if (this.transformCounter % 5 == 0) {
/*  97 */         return MoCreatures.proxy.getTexture(newText);
/*     */       }
/*  99 */       if (this.transformCounter > 50 && this.transformCounter % 3 == 0) {
/* 100 */         return MoCreatures.proxy.getTexture(newText);
/*     */       }
/*     */
/* 103 */       if (this.transformCounter > 75 && this.transformCounter % 4 == 0) {
/* 104 */         return MoCreatures.proxy.getTexture(newText);
/*     */       }
/*     */     }
/*     */
/* 108 */     switch (getType()) {
/*     */       case 1:
/* 110 */         if (!saddle) {
/* 111 */           return MoCreatures.proxy.getTexture("scorpiondirt.png");
/*     */         }
/* 113 */         return MoCreatures.proxy.getTexture("scorpiondirtsaddle.png");
/*     */       case 2:
/* 115 */         if (!saddle) {
/* 116 */           return MoCreatures.proxy.getTexture("scorpioncave.png");
/*     */         }
/* 118 */         return MoCreatures.proxy.getTexture("scorpioncavesaddle.png");
/*     */       case 3:
/* 120 */         if (!saddle) {
/* 121 */           return MoCreatures.proxy.getTexture("scorpionnether.png");
/*     */         }
/* 123 */         return MoCreatures.proxy.getTexture("scorpionnethersaddle.png");
/*     */       case 4:
/* 125 */         if (!saddle) {
/* 126 */           return MoCreatures.proxy.getTexture("scorpionfrost.png");
/*     */         }
/* 128 */         return MoCreatures.proxy.getTexture("scorpionfrostsaddle.png");
/*     */       case 5:
/* 130 */         if (!saddle) {
/* 131 */           return MoCreatures.proxy.getTexture("scorpionundead.png");
/*     */         }
/* 133 */         return MoCreatures.proxy.getTexture("scorpionundeadsaddle.png");
/*     */     }
/* 135 */     return MoCreatures.proxy.getTexture("scorpiondirt.png");
/*     */   }
/*     */
/*     */
/*     */
/*     */   protected void entityInit() {
/* 141 */     super.entityInit();
/* 142 */     this.dataManager.register(HAS_BABIES, Boolean.valueOf(false));
/* 143 */     this.dataManager.register(IS_SITTING, Boolean.valueOf(false));
/* 144 */     this.dataManager.register(RIDEABLE, Boolean.valueOf(false));
/*     */   }
/*     */
/*     */
/*     */   public void setRideable(boolean flag) {
/* 149 */     this.dataManager.set(RIDEABLE, Boolean.valueOf(flag));
/*     */   }
/*     */
/*     */
/*     */   public boolean getIsRideable() {
/* 154 */     return ((Boolean)this.dataManager.get(RIDEABLE)).booleanValue();
/*     */   }
/*     */
/*     */   public boolean getHasBabies() {
/* 158 */     return (getIsAdult() && ((Boolean)this.dataManager.get(HAS_BABIES)).booleanValue());
/*     */   }
/*     */
/*     */   public boolean getIsPoisoning() {
/* 162 */     return this.isPoisoning;
/*     */   }
/*     */
/*     */
/*     */   public boolean getIsSitting() {
/* 167 */     return ((Boolean)this.dataManager.get(IS_SITTING)).booleanValue();
/*     */   }
/*     */
/*     */   public void setSitting(boolean flag) {
/* 171 */     this.dataManager.set(IS_SITTING, Boolean.valueOf(flag));
/*     */   }
/*     */
/*     */   public void setHasBabies(boolean flag) {
/* 175 */     this.dataManager.set(HAS_BABIES, Boolean.valueOf(flag));
/*     */   }
/*     */
/*     */   public void setPoisoning(boolean flag) {
/* 179 */     if (flag && !this.world.isRemote) {
/* 180 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
/* 181 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/* 183 */     this.isPoisoning = flag;
/*     */   }
/*     */
/*     */
/*     */   public void performAnimation(int animationType) {
/* 188 */     if (animationType == 0) {
/*     */
/* 190 */       setPoisoning(true);
/* 191 */     } else if (animationType == 1) {
/*     */
/* 193 */       this.armCounter = 1;
/*     */     }
/* 195 */     else if (animationType == 3) {
/*     */
/* 197 */       this.mouthCounter = 1;
/* 198 */     } else if (animationType == 5) {
/*     */
/* 200 */       this.transformCounter = 1;
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public boolean isOnLadder() {
/* 206 */     return this.collidedHorizontally;
/*     */   }
/*     */
/*     */   public boolean climbing() {
/* 210 */     return (!this.onGround && isOnLadder());
/*     */   }
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/* 215 */     if (!this.onGround && getRidingEntity() != null) {
/* 216 */       this.rotationYaw = (getRidingEntity()).rotationYaw;
/*     */     }
/*     */
/* 219 */     if (this.mouthCounter != 0 && this.mouthCounter++ > 50) {
/* 220 */       this.mouthCounter = 0;
/*     */     }
/*     */
/* 223 */     if (!this.world.isRemote && (this.armCounter == 10 || this.armCounter == 40)) {
/* 224 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SCORPION_CLAW);
/*     */     }
/*     */
/* 227 */     if (this.armCounter != 0 && this.armCounter++ > 24) {
/* 228 */       this.armCounter = 0;
/*     */     }
/*     */
/* 231 */     if (getIsPoisoning()) {
/* 232 */       this.poisontimer++;
/* 233 */       if (this.poisontimer == 1) {
/* 234 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SCORPION_STING);
/*     */       }
/* 236 */       if (this.poisontimer > 50) {
/* 237 */         this.poisontimer = 0;
/* 238 */         setPoisoning(false);
/*     */       }
/*     */     }
/*     */
/* 242 */     if (this.transformCounter > 0) {
/* 243 */       if (this.transformCounter == 40) {
/* 244 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_TRANSFORM);
/*     */       }
/*     */
/* 247 */       if (++this.transformCounter > 100) {
/* 248 */         this.transformCounter = 0;
/* 249 */         setType(5);
/* 250 */         selectType();
/*     */       }
/*     */     }
/* 253 */     super.onLivingUpdate();
/*     */   }
/*     */
/*     */
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 258 */     if (super.attackEntityFrom(damagesource, i)) {
/* 259 */       Entity entity = damagesource.getTrueSource();
/* 260 */       if (!(entity instanceof EntityLivingBase) || (entity != null && entity instanceof EntityPlayer && getIsTamed())) {
/* 261 */         return false;
/*     */       }
/*     */
/* 264 */       if (entity != null && entity != this && shouldAttackPlayers() && getIsAdult()) {
/* 265 */         setAttackTarget((EntityLivingBase)entity);
/*     */       }
/* 267 */       return true;
/*     */     }
/* 269 */     return false;
/*     */   }
/*     */
/*     */
/*     */
/*     */   protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
/* 275 */     boolean flag = entityIn instanceof EntityPlayer;
/* 276 */     if (!getIsPoisoning() && this.rand.nextInt(5) == 0 && entityIn instanceof EntityLivingBase) {
/* 277 */       setPoisoning(true);
/* 278 */       if (getType() <= 2) {
/*     */
/* 280 */         if (flag) {
/* 281 */           MoCreatures.poisonPlayer((EntityPlayer)entityIn);
/*     */         }
/* 283 */         ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 70, 0));
/* 284 */       } else if (getType() == 4) {
/*     */
/* 286 */         if (flag) {
/* 287 */           MoCreatures.freezePlayer((EntityPlayer)entityIn);
/*     */         }
/* 289 */         ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 70, 0));
/*     */       }
/* 291 */       else if (getType() == 3) {
/*     */
/* 293 */         if (flag && !this.world.isRemote && !this.world.provider.doesWaterVaporize()) {
/* 294 */           MoCreatures.burnPlayer((EntityPlayer)entityIn);
/* 295 */           ((EntityLivingBase)entityIn).setFire(15);
/*     */         }
/*     */       }
/*     */     } else {
/* 299 */       swingArm();
/*     */     }
/* 301 */     super.applyEnchantments(entityLivingBaseIn, entityIn);
/*     */   }
/*     */
/*     */   public void swingArm() {
/* 305 */     if (!this.world.isRemote) {
/* 306 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
/* 307 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/*     */   }
/*     */
/*     */   public boolean swingingTail() {
/* 312 */     return (getIsPoisoning() && this.poisontimer < 15);
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getDeathSound() {
/* 317 */     return MoCSoundEvents.ENTITY_SCORPION_DEATH;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 322 */     return MoCSoundEvents.ENTITY_SCORPION_HURT;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getAmbientSound() {
/* 327 */     if (!this.world.isRemote) {
/* 328 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 3), new NetworkRegistry.TargetPoint(this.world.provider
/* 329 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/*     */
/* 332 */     return MoCSoundEvents.ENTITY_SCORPION_AMBIENT;
/*     */   }
/*     */
/*     */
/*     */   protected Item getDropItem() {
/* 337 */     if (!getIsAdult()) {
/* 338 */       return Items.STRING;
/*     */     }
/*     */
/* 341 */     boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);
/*     */
/* 343 */     switch (getType()) {
/*     */       case 1:
/* 345 */         if (flag) {
/* 346 */           return (Item)MoCItems.scorpStingDirt;
/*     */         }
/* 348 */         return (Item)MoCItems.chitin;
/*     */       case 2:
/* 350 */         if (flag) {
/* 351 */           return (Item)MoCItems.scorpStingCave;
/*     */         }
/* 353 */         return (Item)MoCItems.chitinCave;
/*     */       case 3:
/* 355 */         if (flag) {
/* 356 */           return (Item)MoCItems.scorpStingNether;
/*     */         }
/* 358 */         return (Item)MoCItems.chitinNether;
/*     */       case 4:
/* 360 */         if (flag) {
/* 361 */           return (Item)MoCItems.scorpStingFrost;
/*     */         }
/* 363 */         return (Item)MoCItems.chitinFrost;
/*     */       case 5:
/* 365 */         return Items.ROTTEN_FLESH;
/*     */     }
/*     */
/* 368 */     return Items.STRING;
/*     */   }
/*     */
/*     */
/*     */
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 374 */     Boolean tameResult = processTameInteract(player, hand);
/* 375 */     if (tameResult != null) {
/* 376 */       return tameResult.booleanValue();
/*     */     }
/*     */
/* 379 */     ItemStack stack = player.getHeldItem(hand);
/* 380 */     if (!stack.isEmpty() && getIsAdult() && !getIsRideable() && (stack
/* 381 */       .getItem() == Items.SADDLE || stack.getItem() == MoCItems.horsesaddle)) {
/* 382 */       stack.shrink(1);
/* 383 */       if (stack.isEmpty()) {
/* 384 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 386 */       setRideable(true);
/* 387 */       return true;
/*     */     }
/*     */
/* 390 */     if (!stack.isEmpty() && stack.getItem() == MoCItems.whip && getIsTamed() && !isBeingRidden()) {
/* 391 */       setSitting(!getIsSitting());
/* 392 */       return true;
/*     */     }
/*     */
/* 395 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.essenceundead) {
/* 396 */       stack.shrink(1);
/* 397 */       if (stack.isEmpty()) {
/* 398 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*     */       } else {
/* 400 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*     */       }
/* 402 */       transform(5);
/* 403 */       return true;
/*     */     }
/*     */
/* 406 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.essencedarkness) {
/* 407 */       stack.shrink(1);
/* 408 */       if (stack.isEmpty()) {
/* 409 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*     */       } else {
/* 411 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*     */       }
/* 413 */       setHealth(getMaxHealth());
/* 414 */       if (!this.world.isRemote) {
/* 415 */         int i = getType() + 40;
/* 416 */         MoCEntityEgg entityegg = new MoCEntityEgg(this.world, i);
/* 417 */         entityegg.setPosition(player.posX, player.posY, player.posZ);
/* 418 */         player.world.spawnEntity((Entity)entityegg);
/* 419 */         entityegg.motionY += (this.world.rand.nextFloat() * 0.05F);
/* 420 */         entityegg.motionX += ((this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.3F);
/* 421 */         entityegg.motionZ += ((this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.3F);
/*     */       }
/* 423 */       return true;
/*     */     }
/*     */
/* 426 */     if (getRidingEntity() == null && getEdad() < 60 && !getIsAdult()) {
/* 427 */       if (startRiding((Entity)player)) {
/* 428 */         this.rotationYaw = player.rotationYaw;
/* 429 */         if (!this.world.isRemote && !getIsTamed()) {
/* 430 */           MoCTools.tameWithName(player, (IMoCTameable)this);
/*     */         }
/*     */       }
/*     */
/* 434 */       return true;
/* 435 */     }  if (getRidingEntity() != null) {
/* 436 */       MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 437 */       dismountRidingEntity();
/* 438 */       this.motionX = player.motionX * 5.0D;
/* 439 */       this.motionY = player.motionY / 2.0D + 0.5D;
/* 440 */       this.motionZ = player.motionZ * 5.0D;
/* 441 */       return true;
/*     */     }
/*     */
/* 444 */     if (getIsRideable() && getIsTamed() && getIsAdult() && !isBeingRidden()) {
/* 445 */       player.rotationYaw = this.rotationYaw;
/* 446 */       player.rotationPitch = this.rotationPitch;
/* 447 */       if (!this.world.isRemote) {
/* 448 */         player.startRiding((Entity)this);
/*     */       }
/*     */
/* 451 */       return true;
/*     */     }
/*     */
/* 454 */     return super.processInteract(player, hand);
/*     */   }
/*     */
/*     */
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 459 */     super.readEntityFromNBT(nbttagcompound);
/* 460 */     setHasBabies(nbttagcompound.getBoolean("Babies"));
/* 461 */     setRideable(nbttagcompound.getBoolean("Saddled"));
/*     */   }
/*     */
/*     */
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 466 */     super.writeEntityToNBT(nbttagcompound);
/* 467 */     nbttagcompound.setBoolean("Babies", getHasBabies());
/* 468 */     nbttagcompound.setBoolean("Saddled", getIsRideable());
/*     */   }
/*     */
/*     */
/*     */   public int nameYOffset() {
/* 473 */     int n = (int)(1.0D - getEdad() * 0.8D);
/* 474 */     if (n < -60) {
/* 475 */       n = -60;
/*     */     }
/* 477 */     if (getIsAdult()) {
/* 478 */       n = -60;
/*     */     }
/* 480 */     if (getIsSitting()) {
/* 481 */       n = (int)(n * 0.8D);
/*     */     }
/* 483 */     return n;
/*     */   }
/*     */
/*     */
/*     */   protected boolean isMyHealFood(ItemStack itemstack) {
/* 488 */     return (itemstack.getItem() == MoCItems.ratRaw || itemstack.getItem() == MoCItems.ratCooked);
/*     */   }
/*     */
/*     */
/*     */   public int getTalkInterval() {
/* 493 */     return 300;
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void fall(float f, float f1) {}
/*     */
/*     */
/*     */   public boolean canBeCollidedWith() {
/* 502 */     return !isBeingRidden();
/*     */   }
/*     */
/*     */
/*     */   public boolean rideableEntity() {
/* 507 */     return true;
/*     */   }
/*     */
/*     */
/*     */   public boolean isMovementCeased() {
/* 512 */     return (isBeingRidden() || getIsSitting());
/*     */   }
/*     */
/*     */
/*     */   public void dropMyStuff() {
/* 517 */     MoCTools.dropSaddle((MoCEntityAnimal)this, this.world);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 525 */     return EnumCreatureAttribute.ARTHROPOD;
/*     */   }
/*     */
/*     */
/*     */   public float getAdjustedYOffset() {
/* 530 */     return 0.2F;
/*     */   }
/*     */
/*     */
/*     */   public int getMaxEdad() {
/* 535 */     return 120;
/*     */   }
/*     */
/*     */
/*     */   public double getMountedYOffset() {
/* 540 */     return this.height * 0.75D - 0.15D;
/*     */   }
/*     */
/*     */
/*     */   public double getYOffset() {
/* 545 */     if (getRidingEntity() instanceof EntityPlayer && getRidingEntity() == MoCreatures.proxy.getPlayer() && this.world.isRemote) {
/* 546 */       return 0.10000000149011612D;
/*     */     }
/*     */
/* 549 */     if (getRidingEntity() instanceof EntityPlayer && this.world.isRemote) {
/* 550 */       return super.getYOffset() + 0.10000000149011612D;
/*     */     }
/* 552 */     return super.getYOffset();
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void updatePassenger(Entity passenger) {
/* 558 */     double dist = 0.2D;
/* 559 */     double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
/* 560 */     double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
/* 561 */     passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
/*     */   }
/*     */
/*     */
/*     */   public boolean isNotScared() {
/* 566 */     return getIsTamed();
/*     */   }
/*     */
/*     */   public void transform(int tType) {
/* 570 */     if (!this.world.isRemote) {
/* 571 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), tType), new NetworkRegistry.TargetPoint(this.world.provider
/* 572 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/* 574 */     this.transformCounter = 1;
/*     */   }
/*     */
/*     */
/*     */   public boolean isReadyToHunt() {
/* 579 */     return (getIsAdult() && !isMovementCeased());
/*     */   }
/*     */
/*     */
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 584 */     return (!(entity instanceof MoCEntityFox) && entity.height <= 1.0D && entity.width <= 1.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityPetScorpion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
