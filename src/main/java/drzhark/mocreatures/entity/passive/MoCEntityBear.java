/*     */ package drzhark.mocreatures.entity.passive;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.entity.MoCEntityAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIHunt;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.inventory.MoCAnimalChest;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */
/*     */ public class MoCEntityBear extends MoCEntityTameableAnimal {
/*     */   public int mouthCounter;
/*  46 */   private static final DataParameter<Integer> BEAR_STATE = EntityDataManager.createKey(MoCEntityBear.class, DataSerializers.VARINT); private int attackCounter; private int standingCounter;
/*  47 */   private static final DataParameter<Boolean> RIDEABLE = EntityDataManager.createKey(MoCEntityBear.class, DataSerializers.BOOLEAN);
/*  48 */   private static final DataParameter<Boolean> CHESTED = EntityDataManager.createKey(MoCEntityBear.class, DataSerializers.BOOLEAN);
/*  49 */   private static final DataParameter<Boolean> GHOST = EntityDataManager.createKey(MoCEntityBear.class, DataSerializers.BOOLEAN);
/*     */   public MoCAnimalChest localchest;
/*     */   public ItemStack localstack;
/*     */
/*     */   public MoCEntityBear(World world) {
/*  54 */     super(world);
/*  55 */     setSize(1.2F, 1.5F);
/*  56 */     setEdad(55);
/*  57 */     if (this.rand.nextInt(4) == 0) {
/*  58 */       setAdult(false);
/*     */     } else {
/*  60 */       setAdult(true);
/*     */     }
/*  62 */     this.stepHeight = 1.0F;
/*     */   }
/*     */
/*     */
/*     */   protected void initEntityAI() {
/*  67 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  68 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.0D));
/*  69 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 1.0D, 2.0F, 10.0F));
/*  70 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
/*  71 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  72 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*  73 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  74 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
/*  75 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */
/*     */
/*     */   protected void applyEntityAttributes() {
/*  80 */     super.applyEntityAttributes();
/*  81 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
/*  82 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  83 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getAttackStrength());
/*  84 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   protected void entityInit() {
/*  93 */     super.entityInit();
/*  94 */     this.dataManager.register(BEAR_STATE, Integer.valueOf(0));
/*  95 */     this.dataManager.register(RIDEABLE, Boolean.valueOf(false));
/*  96 */     this.dataManager.register(CHESTED, Boolean.valueOf(false));
/*  97 */     this.dataManager.register(GHOST, Boolean.valueOf(false));
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public int getBearState() {
/* 106 */     return ((Integer)this.dataManager.get(BEAR_STATE)).intValue();
/*     */   }
/*     */
/*     */   public void setBearState(int i) {
/* 110 */     this.dataManager.set(BEAR_STATE, Integer.valueOf(i));
/*     */   }
/*     */
/*     */
/*     */   public boolean getIsRideable() {
/* 115 */     return ((Boolean)this.dataManager.get(RIDEABLE)).booleanValue();
/*     */   }
/*     */
/*     */   public boolean getIsChested() {
/* 119 */     return ((Boolean)this.dataManager.get(CHESTED)).booleanValue();
/*     */   }
/*     */
/*     */   public boolean getIsGhost() {
/* 123 */     return ((Boolean)this.dataManager.get(GHOST)).booleanValue();
/*     */   }
/*     */
/*     */   public void setIsChested(boolean flag) {
/* 127 */     this.dataManager.set(CHESTED, Boolean.valueOf(flag));
/*     */   }
/*     */
/*     */   public void setRideable(boolean flag) {
/* 131 */     this.dataManager.set(RIDEABLE, Boolean.valueOf(flag));
/*     */   }
/*     */
/*     */   public void setIsGhost(boolean flag) {
/* 135 */     this.dataManager.set(GHOST, Boolean.valueOf(flag));
/*     */   }
/*     */
/*     */
/*     */   public void selectType() {
/* 140 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
/* 141 */     setHealth(getMaxHealth());
/* 142 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getAttackStrength());
/* 143 */     getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(getAttackRange());
/* 144 */     if (getIsAdult()) {
/* 145 */       setEdad(getMaxEdad());
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public float getBearSize() {
/* 156 */     return 1.0F;
/*     */   }
/*     */
/*     */   public float calculateMaxHealth() {
/* 160 */     return 30.0F;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public double getAttackRange() {
/* 169 */     return 8.0D;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public int getAttackStrength() {
/* 178 */     return 2;
/*     */   }
/*     */
/*     */
/*     */   public boolean attackEntityAsMob(Entity entityIn) {
/* 183 */     startAttack();
/* 184 */     return super.attackEntityAsMob(entityIn);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean isMovementCeased() {
/* 192 */     return (getBearState() == 2);
/*     */   }
/*     */
/*     */
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 197 */     if (super.attackEntityFrom(damagesource, i)) {
/* 198 */       Entity entity = damagesource.getTrueSource();
/* 199 */       if (isRidingOrBeingRiddenBy(entity)) {
/* 200 */         return true;
/*     */       }
/* 202 */       if (entity != this && entity instanceof EntityLivingBase && shouldAttackPlayers()) {
/* 203 */         setAttackTarget((EntityLivingBase)entity);
/*     */       }
/* 205 */       return true;
/*     */     }
/* 207 */     return false;
/*     */   }
/*     */
/*     */
/*     */
/*     */   public boolean isNotScared() {
/* 213 */     return getIsAdult();
/*     */   }
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/* 218 */     super.onLivingUpdate();
/* 219 */     if (this.mouthCounter > 0 && ++this.mouthCounter > 20) {
/* 220 */       this.mouthCounter = 0;
/*     */     }
/* 222 */     if (this.attackCounter > 0 && ++this.attackCounter > 9) {
/* 223 */       this.attackCounter = 0;
/*     */     }
/* 225 */     if (!this.world.isRemote && !getIsAdult() && getEdad() < 80 && this.rand.nextInt(300) == 0) {
/* 226 */       setBearState(2);
/*     */     }
/*     */
/*     */
/*     */
/* 231 */     if (!this.world.isRemote && getBearState() == 2 && !getIsTamed() && this.rand.nextInt(800) == 0) {
/* 232 */       setBearState(0);
/*     */     }
/* 234 */     if (!this.world.isRemote && getBearState() == 2 && !getIsTamed() && !getNavigator().noPath()) {
/* 235 */       setBearState(0);
/*     */     }
/* 237 */     if (!this.world.isRemote && this.standingCounter > 0 && ++this.standingCounter > 100) {
/* 238 */       this.standingCounter = 0;
/* 239 */       setBearState(0);
/*     */     }
/*     */
/*     */
/*     */
/* 244 */     if (!this.world.isRemote && !getIsTamed() && getIsStanding() &&
/* 245 */       getBearState() != 2 && getIsAdult() && this.rand.nextInt(200) == 0 && shouldAttackPlayers()) {
/* 246 */       EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 4.0D);
/* 247 */       if (entityplayer1 != null && canEntityBeSeen((Entity)entityplayer1) && !entityplayer1.capabilities.disableDamage) {
/* 248 */         setStand();
/* 249 */         setBearState(1);
/*     */       }
/*     */     }
/*     */
/* 253 */     if (!this.world.isRemote && getType() == 3 && this.deathTime == 0 && getBearState() != 2) {
/* 254 */       EntityItem entityitem = getClosestItem((Entity)this, 12.0D, Items.REEDS, Items.SUGAR);
/* 255 */       if (entityitem != null) {
/*     */
/* 257 */         float f = entityitem.getDistance((Entity)this);
/* 258 */         if (f > 2.0F) {
/* 259 */           getMyOwnPath((Entity)entityitem, f);
/*     */         }
/* 261 */         if (f < 2.0F && entityitem != null && this.deathTime == 0) {
/* 262 */           entityitem.setDead();
/* 263 */           MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/* 264 */           setHealth(getMaxHealth());
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 273 */     return (!(entity instanceof MoCEntityBear) && entity.height <= 1.0D && entity.width <= 1.0D);
/*     */   }
/*     */
/*     */
/*     */   protected Item getDropItem() {
/* 278 */     return (Item)MoCItems.animalHide;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getDeathSound() {
/* 283 */     return MoCSoundEvents.ENTITY_BEAR_DEATH;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 288 */     openMouth();
/* 289 */     return MoCSoundEvents.ENTITY_BEAR_HURT;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getAmbientSound() {
/* 294 */     openMouth();
/* 295 */     return MoCSoundEvents.ENTITY_BEAR_AMBIENT;
/*     */   }
/*     */
/*     */   private void openMouth() {
/* 299 */     this.mouthCounter = 1;
/*     */   }
/*     */
/*     */   public float getAttackSwing() {
/* 303 */     if (this.attackCounter == 0)
/* 304 */       return 0.0F;
/* 305 */     return 1.5F + (this.attackCounter / 10.0F - 10.0F) * 5.0F;
/*     */   }
/*     */
/*     */   private void startAttack() {
/* 309 */     if (!this.world.isRemote && this.attackCounter == 0 && getBearState() == 1) {
/* 310 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
/* 311 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/* 312 */       this.attackCounter = 1;
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public void performAnimation(int i) {
/* 318 */     this.attackCounter = 1;
/*     */   }
/*     */
/*     */   protected void eatingAnimal() {
/* 322 */     openMouth();
/* 323 */     MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/*     */   }
/*     */
/*     */
/*     */   public double getCustomSpeed() {
/* 328 */     if (getBearState() == 2) {
/* 329 */       return 0.0D;
/*     */     }
/* 331 */     return super.getCustomSpeed();
/*     */   }
/*     */
/*     */
/*     */   public boolean isReadyToHunt() {
/* 336 */     return (getIsAdult() && !isMovementCeased());
/*     */   }
/*     */
/*     */   public boolean getIsStanding() {
/* 340 */     return (this.standingCounter != 0);
/*     */   }
/*     */
/*     */   public void setStand() {
/* 344 */     this.standingCounter = 1;
/*     */   }
/*     */
/*     */
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 349 */     Boolean tameResult = processTameInteract(player, hand);
/* 350 */     if (tameResult != null) {
/* 351 */       return tameResult.booleanValue();
/*     */     }
/*     */
/* 354 */     ItemStack stack = player.getHeldItem(hand);
/* 355 */     if (!stack.isEmpty() && getIsTamed() && !getIsRideable() && getEdad() > 80 && (stack
/* 356 */       .getItem() == Items.SADDLE || stack.getItem() == MoCItems.horsesaddle)) {
/* 357 */       stack.shrink(1);
/* 358 */       if (stack.isEmpty()) {
/* 359 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 361 */       setRideable(true);
/* 362 */       return true;
/*     */     }
/* 364 */     if (!stack.isEmpty() && getIsTamed() && MoCTools.isItemEdibleforCarnivores(stack.getItem())) {
/* 365 */       stack.shrink(1);
/* 366 */       if (stack.isEmpty()) {
/* 367 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 369 */       setHealth(getMaxHealth());
/* 370 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/* 371 */       setIsHunting(false);
/* 372 */       setHasEaten(true);
/* 373 */       return true;
/*     */     }
/* 375 */     if (!stack.isEmpty() && getIsTamed() && getIsAdult() && !getIsChested() && stack.getItem() == Item.getItemFromBlock((Block)Blocks.CHEST)) {
/* 376 */       stack.shrink(1);
/* 377 */       if (stack.isEmpty()) {
/* 378 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 380 */       setIsChested(true);
/* 381 */       MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 382 */       return true;
/*     */     }
/* 384 */     if (getIsChested() && player.isSneaking()) {
/* 385 */       if (this.localchest == null) {
/* 386 */         this.localchest = new MoCAnimalChest("BigBearChest", 18);
/*     */       }
/* 388 */       if (!this.world.isRemote) {
/* 389 */         player.displayGUIChest((IInventory)this.localchest);
/*     */       }
/* 391 */       return true;
/*     */     }
/*     */
/* 394 */     return super.processInteract(player, hand);
/*     */   }
/*     */
/*     */
/*     */   public double getMountedYOffset() {
/* 399 */     double Yfactor = (0.086D * getEdad() - 2.5D) / 10.0D;
/* 400 */     return this.height * Yfactor;
/*     */   }
/*     */
/*     */
/*     */   public int nameYOffset() {
/* 405 */     return (int)((0.445D * getEdad() + 15.0D) * -1.0D);
/*     */   }
/*     */
/*     */
/*     */   public boolean rideableEntity() {
/* 410 */     return true;
/*     */   }
/*     */
/*     */
/*     */   public float getSizeFactor() {
/* 415 */     return getEdad() * 0.01F;
/*     */   }
/*     */
/*     */
/*     */   public void updatePassenger(Entity passenger) {
/* 420 */     double dist = getSizeFactor() * 0.1D;
/* 421 */     double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
/* 422 */     double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
/* 423 */     passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
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
/*     */   public void dropMyStuff() {
/* 436 */     if (!this.world.isRemote) {
/* 437 */       dropArmor();
/* 438 */       MoCTools.dropSaddle((MoCEntityAnimal)this, this.world);
/*     */
/* 440 */       if (getIsChested()) {
/* 441 */         MoCTools.dropInventory((Entity)this, this.localchest);
/* 442 */         MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Block)Blocks.CHEST, 1));
/* 443 */         setIsChested(false);
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 450 */     super.writeEntityToNBT(nbttagcompound);
/* 451 */     nbttagcompound.setBoolean("Saddle", getIsRideable());
/* 452 */     nbttagcompound.setBoolean("Chested", getIsChested());
/* 453 */     nbttagcompound.setBoolean("Ghost", getIsGhost());
/* 454 */     nbttagcompound.setInteger("BearState", getBearState());
/* 455 */     if (getIsChested() && this.localchest != null) {
/* 456 */       NBTTagList nbttaglist = new NBTTagList();
/* 457 */       for (int i = 0; i < this.localchest.getSizeInventory(); i++) {
/*     */
/* 459 */         this.localstack = this.localchest.getStackInSlot(i);
/* 460 */         if (this.localstack != null && !this.localstack.isEmpty()) {
/* 461 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 462 */           nbttagcompound1.setByte("Slot", (byte)i);
/* 463 */           this.localstack.writeToNBT(nbttagcompound1);
/* 464 */           nbttaglist.appendTag((NBTBase)nbttagcompound1);
/*     */         }
/*     */       }
/* 467 */       nbttagcompound.setTag("Items", (NBTBase)nbttaglist);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 474 */     super.readEntityFromNBT(nbttagcompound);
/* 475 */     setRideable(nbttagcompound.getBoolean("Saddle"));
/* 476 */     setIsChested(nbttagcompound.getBoolean("Chested"));
/* 477 */     setIsGhost(nbttagcompound.getBoolean("Ghost"));
/* 478 */     setBearState(nbttagcompound.getInteger("BearState"));
/* 479 */     if (getIsChested()) {
/* 480 */       NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
/* 481 */       this.localchest = new MoCAnimalChest("BigBearChest", 18);
/* 482 */       for (int i = 0; i < nbttaglist.tagCount(); i++) {
/* 483 */         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
/* 484 */         int j = nbttagcompound1.getByte("Slot") & 0xFF;
/* 485 */         if (j >= 0 && j < this.localchest.getSizeInventory())
/* 486 */           this.localchest.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
