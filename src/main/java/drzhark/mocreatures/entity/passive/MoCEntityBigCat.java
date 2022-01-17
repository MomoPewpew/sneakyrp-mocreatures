/*     */ package drzhark.mocreatures.entity.passive;
/*     */ import drzhark.mocreatures.MoCPetData;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.MoCEntityAnimal;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIHunt;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.entity.IMoCEntity;
/*     */ import drzhark.mocreatures.inventory.MoCAnimalChest;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.SoundType;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
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
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */
/*     */ public class MoCEntityBigCat extends MoCEntityTameableAnimal {
/*     */   public int mouthCounter;
/*     */   public int tailCounter;
/*  58 */   protected String chestName = "BigCatChest"; public int wingFlapCounter; public MoCAnimalChest localchest; public ItemStack localstack;
/*     */   private int tCounter;
/*     */   private float fTransparency;
/*  61 */   private static final DataParameter<Boolean> RIDEABLE = EntityDataManager.createKey(MoCEntityBigCat.class, DataSerializers.BOOLEAN);
/*  62 */   private static final DataParameter<Boolean> HAS_AMULET = EntityDataManager.createKey(MoCEntityBigCat.class, DataSerializers.BOOLEAN);
/*  63 */   private static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(MoCEntityBigCat.class, DataSerializers.BOOLEAN);
/*  64 */   private static final DataParameter<Boolean> GHOST = EntityDataManager.createKey(MoCEntityBigCat.class, DataSerializers.BOOLEAN);
/*  65 */   private static final DataParameter<Boolean> CHESTED = EntityDataManager.createKey(MoCEntityBigCat.class, DataSerializers.BOOLEAN);
/*     */
/*     */   public MoCEntityBigCat(World world) {
/*  68 */     super(world);
/*  69 */     setEdad(45);
/*  70 */     setSize(1.4F, 1.3F);
/*  71 */     if (this.rand.nextInt(4) == 0) {
/*  72 */       setAdult(false);
/*     */     } else {
/*  74 */       setAdult(true);
/*     */     }
/*  76 */     this.stepHeight = 1.0F;
/*     */   }
/*     */
/*     */
/*     */   protected void initEntityAI() {
/*  81 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  82 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  83 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
/*  84 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 1.0D, 2.0F, 10.0F));
/*  85 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.8D, 30));
/*  86 */     this.targetTasks.addTask(3, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*  87 */     this.targetTasks.addTask(4, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
/*     */   }
/*     */
/*     */
/*     */   protected void applyEntityAttributes() {
/*  92 */     super.applyEntityAttributes();
/*  93 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
/*  94 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*  95 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  96 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
/*  97 */     getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(8.0D);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public void selectType() {
/* 105 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
/* 106 */     setHealth(getMaxHealth());
/* 107 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(calculateAttackDmg());
/* 108 */     getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(getAttackRange());
/* 109 */     if (getIsAdult()) {
/* 110 */       setEdad(getMaxEdad());
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public double getCustomSpeed() {
/* 116 */     return 2.0D;
/*     */   }
/*     */
/*     */   public float getMoveSpeed() {
/* 120 */     return 1.6F;
/*     */   }
/*     */
/*     */   public float calculateMaxHealth() {
/* 124 */     return 20.0F;
/*     */   }
/*     */
/*     */   public double calculateAttackDmg() {
/* 128 */     return 5.0D;
/*     */   }
/*     */
/*     */   public double getAttackRange() {
/* 132 */     return 6.0D;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   protected void entityInit() {
/* 141 */     super.entityInit();
/* 142 */     this.dataManager.register(RIDEABLE, Boolean.valueOf(false));
/* 143 */     this.dataManager.register(SITTING, Boolean.valueOf(false));
/* 144 */     this.dataManager.register(GHOST, Boolean.valueOf(false));
/* 145 */     this.dataManager.register(HAS_AMULET, Boolean.valueOf(false));
/* 146 */     this.dataManager.register(CHESTED, Boolean.valueOf(false));
/*     */   }
/*     */
/*     */   public boolean getHasAmulet() {
/* 150 */     return ((Boolean)this.dataManager.get(HAS_AMULET)).booleanValue();
/*     */   }
/*     */
/*     */
/*     */   public boolean getIsSitting() {
/* 155 */     return ((Boolean)this.dataManager.get(SITTING)).booleanValue();
/*     */   }
/*     */
/*     */
/*     */   public boolean getIsRideable() {
/* 160 */     return ((Boolean)this.dataManager.get(RIDEABLE)).booleanValue();
/*     */   }
/*     */
/*     */   public boolean getIsChested() {
/* 164 */     return ((Boolean)this.dataManager.get(CHESTED)).booleanValue();
/*     */   }
/*     */
/*     */
/*     */   public boolean getIsGhost() {
/* 169 */     return ((Boolean)this.dataManager.get(GHOST)).booleanValue();
/*     */   }
/*     */
/*     */   public void setHasAmulet(boolean flag) {
/* 173 */     this.dataManager.set(HAS_AMULET, Boolean.valueOf(flag));
/*     */   }
/*     */
/*     */   public void setSitting(boolean flag) {
/* 177 */     this.dataManager.set(SITTING, Boolean.valueOf(flag));
/*     */   }
/*     */
/*     */   public void setIsChested(boolean flag) {
/* 181 */     this.dataManager.set(CHESTED, Boolean.valueOf(flag));
/*     */   }
/*     */
/*     */   public void setRideable(boolean flag) {
/* 185 */     this.dataManager.set(RIDEABLE, Boolean.valueOf(flag));
/*     */   }
/*     */
/*     */   public void setIsGhost(boolean flag) {
/* 189 */     this.dataManager.set(GHOST, Boolean.valueOf(flag));
/*     */   }
/*     */
/*     */
/*     */
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 195 */     Entity entity = damagesource.getTrueSource();
/* 196 */     if (isBeingRidden() && entity == getRidingEntity()) {
/* 197 */       return false;
/*     */     }
/*     */
/* 200 */     if (super.attackEntityFrom(damagesource, i)) {
/* 201 */       if (entity != null && getIsTamed() && entity instanceof EntityPlayer) {
/* 202 */         return false;
/*     */       }
/* 204 */       if (entity != this && entity instanceof EntityLivingBase && this.world.getDifficulty() != EnumDifficulty.PEACEFUL) {
/* 205 */         setAttackTarget((EntityLivingBase)entity);
/*     */       }
/* 207 */       return true;
/*     */     }
/* 209 */     return false;
/*     */   }
/*     */
/*     */
/*     */
/*     */   protected SoundEvent getDeathSound() {
/* 215 */     openMouth();
/* 216 */     if (getIsAdult()) {
/* 217 */       return MoCSoundEvents.ENTITY_LION_DEATH;
/*     */     }
/* 219 */     return MoCSoundEvents.ENTITY_LION_DEATH_BABY;
/*     */   }
/*     */
/*     */
/*     */
/*     */   protected Item getDropItem() {
/* 225 */     return (Item)MoCItems.bigcatclaw;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 230 */     openMouth();
/* 231 */     if (getIsAdult()) {
/* 232 */       return MoCSoundEvents.ENTITY_LION_HURT;
/*     */     }
/* 234 */     return MoCSoundEvents.ENTITY_LION_HURT_BABY;
/*     */   }
/*     */
/*     */
/*     */
/*     */   protected SoundEvent getAmbientSound() {
/* 240 */     openMouth();
/* 241 */     if (getIsAdult()) {
/* 242 */       return MoCSoundEvents.ENTITY_LION_AMBIENT;
/*     */     }
/* 244 */     return MoCSoundEvents.ENTITY_LION_AMBIENT_BABY;
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void onDeath(DamageSource damagesource) {
/* 250 */     if (!this.world.isRemote) {
/* 251 */       if (getHasAmulet()) {
/* 252 */         MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.medallion, 1));
/* 253 */         setHasAmulet(false);
/*     */       }
/*     */
/* 256 */       if (getIsTamed() && !getIsGhost() && this.rand.nextInt(4) == 0) {
/* 257 */         spawnGhost();
/*     */       }
/*     */     }
/* 260 */     super.onDeath(damagesource);
/*     */   }
/*     */
/*     */   public void spawnGhost() {
/*     */     try {
/* 265 */       EntityLiving templiving = (EntityLiving)EntityList.createEntityByIDFromName(new ResourceLocation(getClazzString().toLowerCase()), this.world);
/* 266 */       if (templiving != null && templiving instanceof MoCEntityBigCat) {
/* 267 */         MoCEntityBigCat ghost = (MoCEntityBigCat)templiving;
/* 268 */         ghost.setPosition(this.posX, this.posY, this.posZ);
/* 269 */         this.world.spawnEntity((Entity)ghost);
/* 270 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR);
/* 271 */         ghost.setOwnerId(getOwnerId());
/* 272 */         ghost.setTamed(true);
/* 273 */         EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 274 */         if (entityplayer != null) {
/* 275 */           MoCTools.tameWithName(entityplayer, (IMoCTameable)ghost);
/*     */         }
/*     */
/* 278 */         ghost.setAdult(false);
/* 279 */         ghost.setEdad(1);
/* 280 */         ghost.setType(getType());
/* 281 */         ghost.selectType();
/* 282 */         ghost.setIsGhost(true);
/*     */       }
/*     */
/* 285 */     } catch (Exception exception) {}
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/* 293 */     super.onLivingUpdate();
/*     */
/* 295 */     if (!this.world.isRemote) {
/* 296 */       if (getAttackTarget() == null) {
/* 297 */         setSprinting(false);
/*     */       } else {
/* 299 */         setSprinting(true);
/*     */       }
/*     */     }
/*     */
/* 303 */     if (this.world.isRemote) {
/*     */
/* 305 */       if (this.mouthCounter > 0 && ++this.mouthCounter > 30) {
/* 306 */         this.mouthCounter = 0;
/*     */       }
/*     */
/* 309 */       if (this.rand.nextInt(250) == 0) {
/* 310 */         moveTail();
/*     */       }
/*     */
/* 313 */       if (this.tailCounter > 0 && ++this.tailCounter > 10 && this.rand.nextInt(15) == 0) {
/* 314 */         this.tailCounter = 0;
/*     */       }
/*     */     } else {
/*     */
/* 318 */       if (getIsGhost() && getEdad() > 0 && getEdad() < 10 && this.rand.nextInt(5) == 0) {
/* 319 */         setEdad(getEdad() + 1);
/* 320 */         if (getEdad() == 9) {
/* 321 */           setEdad(getMaxEdad());
/* 322 */           setAdult(true);
/*     */         }
/*     */       }
/*     */
/* 326 */       if (!getIsGhost() && getEdad() < 10)
/*     */       {
/* 328 */         setDead();
/*     */       }
/*     */     }
/*     */
/*     */
/*     */
/*     */
/*     */
/* 336 */     if (!this.world.isRemote && isFlyer() && isOnAir()) {
/* 337 */       float myFlyingSpeed = MoCTools.getMyMovementSpeed((Entity)this);
/* 338 */       int wingFlapFreq = (int)(25.0F - myFlyingSpeed * 10.0F);
/* 339 */       if (!isBeingRidden() || wingFlapFreq < 5) {
/* 340 */         wingFlapFreq = 5;
/*     */       }
/* 342 */       if (this.rand.nextInt(wingFlapFreq) == 0) {
/* 343 */         wingFlap();
/*     */       }
/*     */     }
/*     */
/* 347 */     if (isFlyer()) {
/* 348 */       if (this.wingFlapCounter > 0 && ++this.wingFlapCounter > 20) {
/* 349 */         this.wingFlapCounter = 0;
/*     */       }
/* 351 */       if (!this.world.isRemote && this.wingFlapCounter == 5) {
/* 352 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_WINGFLAP);
/*     */       }
/*     */     }
/*     */
/* 356 */     if (this.rand.nextInt(300) == 0 && getHealth() <= getMaxHealth() && this.deathTime == 0 && !this.world.isRemote) {
/* 357 */       setHealth(getHealth() + 1.0F);
/*     */     }
/*     */
/* 360 */     if (this.deathTime == 0 && !isMovementCeased()) {
/* 361 */       EntityItem entityitem = getClosestItem((Entity)this, 12.0D, Items.PORKCHOP, Items.FISH);
/* 362 */       if (entityitem != null) {
/* 363 */         float f = entityitem.getDistance((Entity)this);
/* 364 */         if (f > 2.0F) {
/* 365 */           getMyOwnPath((Entity)entityitem, f);
/*     */         }
/* 367 */         if (f < 2.0F && entityitem != null && this.deathTime == 0) {
/* 368 */           entityitem.setDead();
/* 369 */           setHealth(getMaxHealth());
/* 370 */           setHasEaten(true);
/* 371 */           MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public boolean readytoBreed() {
/* 379 */     return (!getIsGhost() && super.readytoBreed());
/*     */   }
/*     */
/*     */   public void wingFlap() {
/* 383 */     if (this.world.isRemote) {
/*     */       return;
/*     */     }
/*     */
/* 387 */     if (this.wingFlapCounter == 0) {
/* 388 */       this.wingFlapCounter = 1;
/* 389 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 3), new NetworkRegistry.TargetPoint(this.world.provider
/* 390 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public boolean isNotScared() {
/* 396 */     return (getIsAdult() || getEdad() > 80);
/*     */   }
/*     */
/*     */
/*     */   public boolean isReadyToHunt() {
/* 401 */     return (getIsAdult() && !isMovementCeased());
/*     */   }
/*     */
/*     */
/*     */   public void updatePassenger(Entity passenger) {
/* 406 */     double dist = getSizeFactor() * 0.1D;
/* 407 */     double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
/* 408 */     double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
/* 409 */     passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
/*     */   }
/*     */
/*     */
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 414 */     super.writeEntityToNBT(nbttagcompound);
/* 415 */     nbttagcompound.setBoolean("Saddle", getIsRideable());
/* 416 */     nbttagcompound.setBoolean("Sitting", getIsSitting());
/* 417 */     nbttagcompound.setBoolean("Chested", getIsChested());
/* 418 */     nbttagcompound.setBoolean("Ghost", getIsGhost());
/* 419 */     nbttagcompound.setBoolean("Amulet", getHasAmulet());
/* 420 */     if (getIsChested() && this.localchest != null) {
/* 421 */       NBTTagList nbttaglist = new NBTTagList();
/* 422 */       for (int i = 0; i < this.localchest.getSizeInventory(); i++) {
/*     */
/* 424 */         this.localstack = this.localchest.getStackInSlot(i);
/* 425 */         if (this.localstack != null && !this.localstack.isEmpty()) {
/* 426 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 427 */           nbttagcompound1.setByte("Slot", (byte)i);
/* 428 */           this.localstack.writeToNBT(nbttagcompound1);
/* 429 */           nbttaglist.appendTag((NBTBase)nbttagcompound1);
/*     */         }
/*     */       }
/* 432 */       nbttagcompound.setTag("Items", (NBTBase)nbttaglist);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 439 */     super.readEntityFromNBT(nbttagcompound);
/* 440 */     setRideable(nbttagcompound.getBoolean("Saddle"));
/* 441 */     setSitting(nbttagcompound.getBoolean("Sitting"));
/* 442 */     setIsChested(nbttagcompound.getBoolean("Chested"));
/* 443 */     setIsGhost(nbttagcompound.getBoolean("Ghost"));
/* 444 */     setHasAmulet(nbttagcompound.getBoolean("Amulet"));
/* 445 */     if (getIsChested()) {
/* 446 */       NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
/* 447 */       this.localchest = new MoCAnimalChest("BigCatChest", 18);
/* 448 */       for (int i = 0; i < nbttaglist.tagCount(); i++) {
/* 449 */         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
/* 450 */         int j = nbttagcompound1.getByte("Slot") & 0xFF;
/* 451 */         if (j >= 0 && j < this.localchest.getSizeInventory()) {
/* 452 */           this.localchest.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 461 */     Boolean tameResult = processTameInteract(player, hand);
/* 462 */     if (tameResult != null) {
/* 463 */       return tameResult.booleanValue();
/*     */     }
/*     */
/* 466 */     ItemStack stack = player.getHeldItem(hand);
/* 467 */     if (!stack.isEmpty() && !getIsTamed() && getHasEaten() && !getIsAdult() && stack.getItem() == MoCItems.medallion) {
/* 468 */       if (!this.world.isRemote) {
/* 469 */         setHasAmulet(true);
/* 470 */         MoCTools.tameWithName(player, (IMoCTameable)this);
/*     */       }
/* 472 */       stack.shrink(1);
/* 473 */       if (stack.isEmpty()) {
/* 474 */         player.setHeldItem(hand, ItemStack.EMPTY);
/* 475 */         return true;
/*     */       }
/* 477 */       return true;
/*     */     }
/*     */
/* 480 */     if (!stack.isEmpty() && getIsTamed() && !getHasAmulet() && stack.getItem() == MoCItems.medallion) {
/* 481 */       if (!this.world.isRemote) {
/* 482 */         setHasAmulet(true);
/*     */       }
/* 484 */       stack.shrink(1);
/* 485 */       if (stack.isEmpty()) {
/* 486 */         player.setHeldItem(hand, ItemStack.EMPTY);
/* 487 */         return true;
/*     */       }
/* 489 */       return true;
/*     */     }
/*     */
/* 492 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.whip) {
/* 493 */       setSitting(!getIsSitting());
/* 494 */       return true;
/*     */     }
/* 496 */     if (!stack.isEmpty() && getIsTamed() && MoCTools.isItemEdibleforCarnivores(stack.getItem())) {
/* 497 */       stack.shrink(1);
/* 498 */       if (stack.isEmpty()) {
/* 499 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 501 */       setHealth(getMaxHealth());
/* 502 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/* 503 */       setIsHunting(false);
/* 504 */       setHasEaten(true);
/* 505 */       return true;
/*     */     }
/* 507 */     if (!stack.isEmpty() && getIsTamed() && !getIsRideable() && getEdad() > 80 && (stack
/* 508 */       .getItem() == Items.SADDLE || stack.getItem() == MoCItems.horsesaddle)) {
/* 509 */       stack.shrink(1);
/* 510 */       if (stack.isEmpty()) {
/* 511 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 513 */       setRideable(true);
/* 514 */       return true;
/*     */     }
/*     */
/* 517 */     if (!stack.isEmpty() && getIsGhost() && getIsTamed() && stack.getItem() == MoCItems.amuletghost) {
/*     */
/* 519 */       player.setHeldItem(hand, ItemStack.EMPTY);
/* 520 */       if (!this.world.isRemote) {
/* 521 */         MoCPetData petData = MoCreatures.instance.mapData.getPetData(getOwnerId());
/* 522 */         if (petData != null) {
/* 523 */           petData.setInAmulet(getOwnerPetId(), true);
/*     */         }
/* 525 */         dropMyStuff();
/* 526 */         MoCTools.dropAmulet((IMoCEntity)this, 3, player);
/* 527 */         this.isDead = true;
/*     */       }
/*     */
/* 530 */       return true;
/*     */     }
/*     */
/*     */
/* 534 */     if (!stack.isEmpty() && getIsTamed() && getIsAdult() && !getIsChested() && stack.getItem() == Item.getItemFromBlock((Block)Blocks.CHEST)) {
/* 535 */       stack.shrink(1);
/* 536 */       if (stack.isEmpty()) {
/* 537 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 539 */       setIsChested(true);
/* 540 */       MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 541 */       return true;
/*     */     }
/*     */
/* 544 */     if (getIsChested() && player.isSneaking()) {
/* 545 */       if (this.localchest == null) {
/* 546 */         this.localchest = new MoCAnimalChest(this.chestName, 18);
/*     */       }
/* 548 */       if (!this.world.isRemote) {
/* 549 */         player.displayGUIChest((IInventory)this.localchest);
/*     */       }
/* 551 */       return true;
/*     */     }
/*     */
/* 554 */     return super.processInteract(player, hand);
/*     */   }
/*     */
/*     */
/*     */   public float getSizeFactor() {
/* 559 */     return getEdad() * 0.01F;
/*     */   }
/*     */
/*     */
/*     */   public void fall(float f, float f1) {
/* 564 */     if (isFlyer()) {
/*     */       return;
/*     */     }
/* 567 */     float i = (float)(Math.ceil((f - 3.0F)) / 2.0D);
/* 568 */     if (!this.world.isRemote && i > 0.0F) {
/* 569 */       i /= 2.0F;
/* 570 */       if (i > 1.0F) {
/* 571 */         attackEntityFrom(DamageSource.FALL, i);
/*     */       }
/* 573 */       if (isBeingRidden() && i > 1.0F) {
/* 574 */         for (Entity entity : getRecursivePassengers())
/*     */         {
/* 576 */           entity.attackEntityFrom(DamageSource.FALL, i);
/*     */         }
/*     */       }
/* 579 */       IBlockState iblockstate = this.world.getBlockState(new BlockPos(this.posX, this.posY - 0.2D - this.prevRotationYaw, this.posZ));
/* 580 */       Block block = iblockstate.getBlock();
/*     */
/* 582 */       if (iblockstate.getMaterial() != Material.AIR && !isSilent()) {
/*     */
/* 584 */         SoundType soundtype = block.getSoundType(iblockstate, this.world, new BlockPos(this.posX, this.posY - 0.2D - this.prevRotationYaw, this.posZ), (Entity)this);
/* 585 */         this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, soundtype.getStepSound(), getSoundCategory(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */   private void openMouth() {
/* 591 */     this.mouthCounter = 1;
/*     */   }
/*     */
/*     */   public boolean hasMane() {
/* 595 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public int getTalkInterval() {
/* 600 */     return 400;
/*     */   }
/*     */
/*     */   private void moveTail() {
/* 604 */     this.tailCounter = 1;
/*     */   }
/*     */
/*     */   public boolean hasSaberTeeth() {
/* 608 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public void performAnimation(int animationType) {
/* 613 */     if (animationType != 0)
/*     */     {
/*     */
/* 616 */       if (animationType == 3)
/*     */       {
/* 618 */         this.wingFlapCounter = 1;
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */   public void makeEntityJump() {
/* 624 */     if (isFlyer()) {
/* 625 */       wingFlap();
/*     */     }
/* 627 */     super.makeEntityJump();
/*     */   }
/*     */
/*     */
/*     */   public void dropMyStuff() {
/* 632 */     if (!this.world.isRemote) {
/* 633 */       dropArmor();
/* 634 */       MoCTools.dropSaddle((MoCEntityAnimal)this, this.world);
/*     */
/* 636 */       if (getIsChested()) {
/* 637 */         MoCTools.dropInventory((Entity)this, this.localchest);
/* 638 */         MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Block)Blocks.CHEST, 1));
/* 639 */         setIsChested(false);
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */   public boolean getHasStinger() {
/* 645 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public double getMountedYOffset() {
/* 650 */     double Yfactor = (0.0833D * getEdad() - 2.5D) / 10.0D;
/* 651 */     return this.height * Yfactor;
/*     */   }
/*     */
/*     */
/*     */   public float tFloat() {
/* 656 */     if (++this.tCounter > 30) {
/* 657 */       this.tCounter = 0;
/* 658 */       this.fTransparency = this.rand.nextFloat() * 0.2F + 0.15F;
/*     */     }
/*     */
/* 661 */     if (getEdad() < 10) {
/* 662 */       return 0.0F;
/*     */     }
/* 664 */     return this.fTransparency;
/*     */   }
/*     */
/*     */
/*     */   public int nameYOffset() {
/* 669 */     return (int)((0.445D * getEdad() + 15.0D) * -1.0D);
/*     */   }
/*     */
/*     */
/*     */   public boolean rideableEntity() {
/* 674 */     return true;
/*     */   }
/*     */
/*     */
/*     */   public float getAIMoveSpeed() {
/* 679 */     if (isSprinting()) {
/* 680 */       return 0.37F;
/*     */     }
/* 682 */     return 0.18F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityBigCat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
