/*     */ package drzhark.mocreatures.entity.passive;
/*     */ import drzhark.mocreatures.MoCPetData;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.MoCEntityAnimal;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.entity.item.MoCEntityEgg;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.inventory.MoCAnimalChest;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.MobEffects;
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
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ 
/*     */ public class MoCEntityWyvern extends MoCEntityTameableAnimal {
/*     */   public MoCAnimalChest localchest;
/*  52 */   public static final String[] wyvernNames = new String[] { "Jungle", "Swamp", "Savanna", "Sand", "Mother", "Undead", "Light", "Dark", "Arctic", "Cave", "Mountain", "Sea" }; public ItemStack localstack; public int mouthCounter;
/*     */   public int wingFlapCounter;
/*     */   public int diveCounter;
/*     */   protected EntityAIWanderMoC2 wander;
/*     */   private int transformType;
/*     */   private int transformCounter;
/*     */   private int tCounter;
/*     */   private float fTransparency;
/*  60 */   private static final DataParameter<Boolean> RIDEABLE = EntityDataManager.createKey(MoCEntityWyvern.class, DataSerializers.BOOLEAN);
/*  61 */   private static final DataParameter<Boolean> CHESTED = EntityDataManager.createKey(MoCEntityWyvern.class, DataSerializers.BOOLEAN);
/*  62 */   private static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(MoCEntityWyvern.class, DataSerializers.BOOLEAN);
/*  63 */   private static final DataParameter<Boolean> GHOST = EntityDataManager.createKey(MoCEntityWyvern.class, DataSerializers.BOOLEAN);
/*  64 */   private static final DataParameter<Boolean> FLYING = EntityDataManager.createKey(MoCEntityWyvern.class, DataSerializers.BOOLEAN);
/*  65 */   private static final DataParameter<Integer> ARMOR_TYPE = EntityDataManager.createKey(MoCEntityWyvern.class, DataSerializers.VARINT);
/*     */ 
/*     */   
/*     */   public MoCEntityWyvern(World world) {
/*  69 */     super(world);
/*  70 */     setSize(1.9F, 1.7F);
/*  71 */     setAdult(false);
/*  72 */     setTamed(false);
/*  73 */     this.stepHeight = 1.0F;
/*     */     
/*  75 */     if (this.rand.nextInt(6) == 0) {
/*  76 */       setEdad(50 + this.rand.nextInt(50));
/*     */     } else {
/*  78 */       setEdad(80 + this.rand.nextInt(20));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  84 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  85 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  86 */     this.tasks.addTask(4, (EntityAIBase)(this.wander = new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80)));
/*  87 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  88 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*  89 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  94 */     super.applyEntityAttributes();
/*  95 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
/*  96 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
/*  97 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  98 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 103 */     super.entityInit();
/* 104 */     this.dataManager.register(RIDEABLE, Boolean.valueOf(false));
/* 105 */     this.dataManager.register(SITTING, Boolean.valueOf(false));
/* 106 */     this.dataManager.register(CHESTED, Boolean.valueOf(false));
/* 107 */     this.dataManager.register(FLYING, Boolean.valueOf(false));
/* 108 */     this.dataManager.register(GHOST, Boolean.valueOf(false));
/* 109 */     this.dataManager.register(ARMOR_TYPE, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public boolean getIsFlying() {
/* 113 */     return ((Boolean)this.dataManager.get(FLYING)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setIsFlying(boolean flag) {
/* 117 */     this.dataManager.set(FLYING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getArmorType() {
/* 122 */     return ((Integer)this.dataManager.get(ARMOR_TYPE)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArmorType(int i) {
/* 127 */     this.dataManager.set(ARMOR_TYPE, Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsRideable() {
/* 132 */     return ((Boolean)this.dataManager.get(RIDEABLE)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRideable(boolean flag) {
/* 137 */     this.dataManager.set(RIDEABLE, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean getIsChested() {
/* 141 */     return ((Boolean)this.dataManager.get(CHESTED)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setIsChested(boolean flag) {
/* 145 */     this.dataManager.set(CHESTED, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsSitting() {
/* 150 */     return ((Boolean)this.dataManager.get(SITTING)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setSitting(boolean flag) {
/* 154 */     this.dataManager.set(SITTING, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean getIsGhost() {
/* 158 */     return ((Boolean)this.dataManager.get(GHOST)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setIsGhost(boolean flag) {
/* 162 */     this.dataManager.set(GHOST, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/* 167 */     if (getType() == 0) {
/* 168 */       if (this.rand.nextInt(5) == 0) {
/* 169 */         setType(5);
/*     */       } else {
/* 171 */         int i = this.rand.nextInt(100);
/* 172 */         if (i <= 12) {
/* 173 */           setType(1);
/* 174 */         } else if (i <= 24) {
/* 175 */           setType(2);
/* 176 */         } else if (i <= 36) {
/* 177 */           setType(3);
/* 178 */         } else if (i <= 48) {
/* 179 */           setType(4);
/* 180 */         } else if (i <= 60) {
/* 181 */           setType(9);
/* 182 */         } else if (i <= 72) {
/* 183 */           setType(10);
/* 184 */         } else if (i <= 84) {
/* 185 */           setType(11);
/* 186 */         } else if (i <= 95) {
/* 187 */           setType(12);
/*     */         } else {
/* 189 */           setType(5);
/*     */         } 
/*     */       } 
/*     */     }
/* 193 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
/* 194 */     setHealth(getMaxHealth());
/* 195 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(calculateAttackDmg());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/* 200 */     return true;
/*     */   }
/*     */   
/*     */   public double calculateMaxHealth() {
/* 204 */     if (getType() == 6 || getType() == 7 || getType() == 8) {
/* 205 */       return 60.0D;
/*     */     }
/* 207 */     if (getType() == 5) {
/* 208 */       return 80.0D;
/*     */     }
/* 210 */     if (getType() == 13) {
/* 211 */       return 100.0D;
/*     */     }
/* 213 */     return 40.0D;
/*     */   }
/*     */   
/*     */   public double calculateAttackDmg() {
/* 217 */     if (getType() == 6 || getType() == 7 || getType() == 8 || getType() == 13) {
/* 218 */       return 8.0D;
/*     */     }
/* 220 */     if (getType() == 5) {
/* 221 */       return 12.0D;
/*     */     }
/* 223 */     return 5.0D;
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
/*     */   public ResourceLocation getTexture() {
/* 236 */     if (this.transformCounter != 0 && this.transformType > 5) {
/* 237 */       String newText = "wyverndark.png";
/* 238 */       if (this.transformType == 6) {
/* 239 */         newText = "wyvernundead.png";
/*     */       }
/* 241 */       if (this.transformType == 7) {
/* 242 */         newText = "wyvernlight.png";
/*     */       }
/* 244 */       if (this.transformType == 8) {
/* 245 */         newText = "wyverndark.png";
/*     */       }
/*     */       
/* 248 */       if (this.transformCounter % 5 == 0) {
/* 249 */         return MoCreatures.proxy.getTexture(newText);
/*     */       }
/* 251 */       if (this.transformCounter > 50 && this.transformCounter % 3 == 0) {
/* 252 */         return MoCreatures.proxy.getTexture(newText);
/*     */       }
/*     */       
/* 255 */       if (this.transformCounter > 75 && this.transformCounter % 4 == 0) {
/* 256 */         return MoCreatures.proxy.getTexture(newText);
/*     */       }
/*     */     } 
/*     */     
/* 260 */     switch (getType()) {
/*     */       case 1:
/* 262 */         return MoCreatures.proxy.getTexture("wyvernjungle.png");
/*     */       case 2:
/* 264 */         return MoCreatures.proxy.getTexture("wyvernmix.png");
/*     */       case 3:
/* 266 */         return MoCreatures.proxy.getTexture("wyvernsand.png");
/*     */       case 4:
/* 268 */         return MoCreatures.proxy.getTexture("wyvernsun.png");
/*     */       case 5:
/* 270 */         return MoCreatures.proxy.getTexture("wyvernmother.png");
/*     */       case 6:
/* 272 */         return MoCreatures.proxy.getTexture("wyvernundead.png");
/*     */       case 7:
/* 274 */         return MoCreatures.proxy.getTexture("wyvernlight.png");
/*     */       case 8:
/* 276 */         return MoCreatures.proxy.getTexture("wyverndark.png");
/*     */       case 9:
/* 278 */         return MoCreatures.proxy.getTexture("wyvernarctic.png");
/*     */       case 10:
/* 280 */         return MoCreatures.proxy.getTexture("wyverncave.png");
/*     */       case 11:
/* 282 */         return MoCreatures.proxy.getTexture("wyvernmountain.png");
/*     */       case 12:
/* 284 */         return MoCreatures.proxy.getTexture("wyvernsea.png");
/*     */     } 
/*     */ 
/*     */     
/* 288 */     return MoCreatures.proxy.getTexture("wyvernsun.png");
/*     */   }
/*     */ 
/*     */   
/*     */   public void transform(int tType) {
/* 293 */     if (!this.world.isRemote) {
/* 294 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), tType), new NetworkRegistry.TargetPoint(this.world.provider
/* 295 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/* 297 */     this.transformType = tType;
/* 298 */     this.transformCounter = 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 304 */     if (this.wingFlapCounter > 0 && ++this.wingFlapCounter > 20) {
/* 305 */       this.wingFlapCounter = 0;
/*     */     }
/* 307 */     if (this.wingFlapCounter == 5 && !this.world.isRemote) {
/* 308 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_WYVERN_WINGFLAP);
/*     */     }
/*     */     
/* 311 */     if (this.transformCounter > 0) {
/* 312 */       if (this.transformCounter == 40) {
/* 313 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_TRANSFORM);
/*     */       }
/*     */       
/* 316 */       if (++this.transformCounter > 100) {
/* 317 */         this.transformCounter = 0;
/* 318 */         if (this.transformType != 0) {
/* 319 */           setType(this.transformType);
/* 320 */           selectType();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 325 */     if (!this.world.isRemote) {
/* 326 */       if (!isMovementCeased() && !getIsTamed() && this.rand.nextInt(300) == 0) {
/* 327 */         setIsFlying(!getIsFlying());
/*     */       }
/* 329 */       if (isMovementCeased() && getIsFlying()) {
/* 330 */         setIsFlying(false);
/*     */       }
/*     */       
/* 333 */       if (getAttackTarget() != null && (!getIsTamed() || getRidingEntity() != null) && !isMovementCeased() && this.rand.nextInt(20) == 0) {
/* 334 */         setIsFlying(true);
/*     */       }
/* 336 */       if (!getIsTamed() && this.dimension == MoCreatures.WyvernLairDimensionID && this.rand.nextInt(50) == 0 && this.posY < 10.0D) {
/* 337 */         setDead();
/*     */       }
/*     */       
/* 340 */       if (getIsFlying() && getNavigator().noPath() && !isMovementCeased() && getAttackTarget() == null && this.rand.nextInt(30) == 0) {
/* 341 */         this.wander.makeUpdate();
/*     */       }
/*     */       
/* 344 */       if (this.motionY > 0.5D)
/*     */       {
/* 346 */         this.motionY = 0.5D;
/*     */       }
/*     */       
/* 349 */       if (isOnAir()) {
/* 350 */         float myFlyingSpeed = MoCTools.getMyMovementSpeed((Entity)this);
/* 351 */         int wingFlapFreq = (int)(25.0F - myFlyingSpeed * 10.0F);
/* 352 */         if (!isBeingRidden() || wingFlapFreq < 5) {
/* 353 */           wingFlapFreq = 5;
/*     */         }
/* 355 */         if (this.rand.nextInt(wingFlapFreq) == 0) {
/* 356 */           wingFlap();
/*     */         }
/*     */       } 
/*     */       
/* 360 */       if (getIsGhost() && getEdad() > 0 && getEdad() < 10 && this.rand.nextInt(5) == 0) {
/* 361 */         setEdad(getEdad() + 1);
/* 362 */         if (getEdad() == 9) {
/* 363 */           setEdad(140);
/* 364 */           setAdult(true);
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 370 */       if (this.mouthCounter > 0 && ++this.mouthCounter > 30) {
/* 371 */         this.mouthCounter = 0;
/*     */       }
/*     */       
/* 374 */       if (this.diveCounter > 0 && ++this.diveCounter > 5) {
/* 375 */         this.diveCounter = 0;
/*     */       }
/*     */     } 
/* 378 */     super.onLivingUpdate();
/*     */   }
/*     */   
/*     */   public void wingFlap() {
/* 382 */     if (this.wingFlapCounter == 0) {
/* 383 */       this.wingFlapCounter = 1;
/* 384 */       if (!this.world.isRemote) {
/* 385 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 3), new NetworkRegistry.TargetPoint(this.world.provider
/* 386 */               .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSizeFactor() {
/* 393 */     return getEdad() * 0.01F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlyingAlone() {
/* 398 */     return (getIsFlying() && !isBeingRidden());
/*     */   }
/*     */ 
/*     */   
/*     */   public int maxFlyingHeight() {
/* 403 */     if (getIsTamed())
/* 404 */       return 5; 
/* 405 */     return 18;
/*     */   }
/*     */ 
/*     */   
/*     */   public int minFlyingHeight() {
/* 410 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 415 */     Boolean tameResult = processTameInteract(player, hand);
/* 416 */     if (tameResult != null) {
/* 417 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/* 420 */     ItemStack stack = player.getHeldItem(hand);
/* 421 */     if (!stack.isEmpty() && stack.getItem() == MoCItems.whip && getIsTamed() && !isBeingRidden()) {
/* 422 */       setSitting(!getIsSitting());
/* 423 */       return true;
/*     */     } 
/*     */     
/* 426 */     if (!stack.isEmpty() && !getIsRideable() && getEdad() > 90 && getIsTamed() && (stack
/* 427 */       .getItem() == Items.SADDLE || stack.getItem() == MoCItems.horsesaddle)) {
/* 428 */       stack.shrink(1);
/* 429 */       if (stack.isEmpty()) {
/* 430 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 432 */       setRideable(true);
/* 433 */       return true;
/*     */     } 
/*     */     
/* 436 */     if (!stack.isEmpty() && getIsTamed() && getEdad() > 90 && stack.getItem() == Items.IRON_HORSE_ARMOR) {
/* 437 */       if (getArmorType() == 0) {
/* 438 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON);
/*     */       }
/* 440 */       dropArmor();
/* 441 */       setArmorType(1);
/* 442 */       stack.shrink(1);
/* 443 */       if (stack.isEmpty()) {
/* 444 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/*     */       
/* 447 */       return true;
/*     */     } 
/*     */     
/* 450 */     if (!stack.isEmpty() && getIsTamed() && getEdad() > 90 && stack.getItem() == Items.GOLDEN_HORSE_ARMOR) {
/* 451 */       if (getArmorType() == 0) {
/* 452 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON);
/*     */       }
/* 454 */       dropArmor();
/* 455 */       setArmorType(2);
/* 456 */       stack.shrink(1);
/* 457 */       if (stack.isEmpty()) {
/* 458 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 460 */       return true;
/*     */     } 
/*     */     
/* 463 */     if (!stack.isEmpty() && getIsTamed() && getEdad() > 90 && stack.getItem() == Items.DIAMOND_HORSE_ARMOR) {
/* 464 */       if (getArmorType() == 0) {
/* 465 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON);
/*     */       }
/* 467 */       dropArmor();
/* 468 */       setArmorType(3);
/* 469 */       stack.shrink(1);
/* 470 */       if (stack.isEmpty()) {
/* 471 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 473 */       return true;
/*     */     } 
/*     */     
/* 476 */     if (!stack.isEmpty() && getIsTamed() && getEdad() > 90 && !getIsChested() && stack.getItem() == Item.getItemFromBlock((Block)Blocks.CHEST)) {
/* 477 */       stack.shrink(1);
/* 478 */       if (stack.isEmpty()) {
/* 479 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 481 */       setIsChested(true);
/* 482 */       MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 483 */       return true;
/*     */     } 
/*     */     
/* 486 */     if (getIsChested() && player.isSneaking()) {
/* 487 */       if (this.localchest == null) {
/* 488 */         this.localchest = new MoCAnimalChest("WyvernChest", 9);
/*     */       }
/* 490 */       if (!this.world.isRemote) {
/* 491 */         player.displayGUIChest((IInventory)this.localchest);
/*     */       }
/* 493 */       return true;
/*     */     } 
/*     */     
/* 496 */     if (!stack.isEmpty() && getIsGhost() && getIsTamed() && stack.getItem() == MoCItems.amuletghost) {
/*     */       
/* 498 */       player.setHeldItem(hand, ItemStack.EMPTY);
/* 499 */       if (!this.world.isRemote) {
/* 500 */         MoCPetData petData = MoCreatures.instance.mapData.getPetData(getOwnerId());
/* 501 */         if (petData != null) {
/* 502 */           petData.setInAmulet(getOwnerPetId(), true);
/*     */         }
/* 504 */         dropMyStuff();
/* 505 */         MoCTools.dropAmulet((IMoCEntity)this, 3, player);
/* 506 */         this.isDead = true;
/*     */       } 
/*     */       
/* 509 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 513 */     if (!stack.isEmpty() && !getIsGhost() && stack.getItem() == MoCItems.essencelight && getIsTamed() && getEdad() > 90 && 
/* 514 */       getType() < 5) {
/* 515 */       stack.shrink(1);
/* 516 */       if (stack.isEmpty()) {
/* 517 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*     */       } else {
/* 519 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*     */       } 
/*     */       
/* 522 */       if (!this.world.isRemote) {
/* 523 */         int i = getType() + 49;
/* 524 */         MoCEntityEgg entityegg = new MoCEntityEgg(this.world, i);
/* 525 */         entityegg.setPosition(player.posX, player.posY, player.posZ);
/* 526 */         player.world.spawnEntity((Entity)entityegg);
/* 527 */         entityegg.motionY += (this.world.rand.nextFloat() * 0.05F);
/* 528 */         entityegg.motionX += ((this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.3F);
/* 529 */         entityegg.motionZ += ((this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.3F);
/*     */       } 
/* 531 */       return true;
/*     */     } 
/*     */     
/* 534 */     if (!stack.isEmpty() && this.transformCounter == 0 && !getIsGhost() && getType() == 5 && stack
/* 535 */       .getItem() == MoCItems.essenceundead && getIsTamed()) {
/* 536 */       stack.shrink(1);
/* 537 */       if (stack.isEmpty()) {
/* 538 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*     */       } else {
/* 540 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*     */       } 
/*     */       
/* 543 */       if (!this.world.isRemote) {
/* 544 */         transform(6);
/*     */       }
/* 546 */       return true;
/*     */     } 
/*     */     
/* 549 */     if (!stack.isEmpty() && this.transformCounter == 0 && !getIsGhost() && getType() == 5 && stack
/* 550 */       .getItem() == MoCItems.essencelight && getIsTamed()) {
/* 551 */       stack.shrink(1);
/* 552 */       if (stack.isEmpty()) {
/* 553 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*     */       } else {
/* 555 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*     */       } 
/*     */       
/* 558 */       if (!this.world.isRemote) {
/* 559 */         transform(7);
/*     */       }
/* 561 */       return true;
/*     */     } 
/*     */     
/* 564 */     if (!stack.isEmpty() && this.transformCounter == 0 && !getIsGhost() && getType() == 5 && stack
/* 565 */       .getItem() == MoCItems.essencedarkness && getIsTamed()) {
/* 566 */       stack.shrink(1);
/* 567 */       if (stack.isEmpty()) {
/* 568 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*     */       } else {
/* 570 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*     */       } 
/*     */       
/* 573 */       if (!this.world.isRemote) {
/* 574 */         transform(8);
/*     */       }
/* 576 */       return true;
/*     */     } 
/*     */     
/* 579 */     if (getIsRideable() && getEdad() > 90 && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
/* 580 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/* 581 */         player.rotationYaw = this.rotationYaw;
/* 582 */         player.rotationPitch = this.rotationPitch;
/* 583 */         setSitting(false);
/*     */       } 
/*     */       
/* 586 */       return true;
/*     */     } 
/*     */     
/* 589 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropArmor() {
/* 597 */     if (!this.world.isRemote) {
/* 598 */       int i = getArmorType();
/* 599 */       if (i != 0) {
/* 600 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
/*     */       }
/*     */       
/* 603 */       if (i == 1) {
/* 604 */         EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Items.IRON_HORSE_ARMOR, 1));
/* 605 */         entityitem.setPickupDelay(10);
/* 606 */         this.world.spawnEntity((Entity)entityitem);
/*     */       } 
/* 608 */       if (i == 2) {
/* 609 */         EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Items.GOLDEN_HORSE_ARMOR, 1));
/* 610 */         entityitem.setPickupDelay(10);
/* 611 */         this.world.spawnEntity((Entity)entityitem);
/*     */       } 
/* 613 */       if (i == 3) {
/* 614 */         EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Items.DIAMOND_HORSE_ARMOR, 1));
/* 615 */         entityitem.setPickupDelay(10);
/* 616 */         this.world.spawnEntity((Entity)entityitem);
/*     */       } 
/* 618 */       setArmorType(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean rideableEntity() {
/* 624 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 629 */     return MoCSoundEvents.ENTITY_WYVERN_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 634 */     openMouth();
/* 635 */     return MoCSoundEvents.ENTITY_WYVERN_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 640 */     openMouth();
/* 641 */     return MoCSoundEvents.ENTITY_WYVERN_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTalkInterval() {
/* 646 */     return 400;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMovementCeased() {
/* 651 */     return (isBeingRidden() || getIsSitting());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/* 656 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {}
/*     */ 
/*     */   
/*     */   public double getMountedYOffset() {
/* 665 */     return this.height * 0.85D * getSizeFactor();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePassenger(Entity passenger) {
/* 670 */     double dist = getSizeFactor() * 0.3D;
/* 671 */     double newPosX = this.posX - dist * Math.cos((MoCTools.realAngle(this.renderYawOffset - 90.0F) / 57.29578F));
/* 672 */     double newPosZ = this.posZ - dist * Math.sin((MoCTools.realAngle(this.renderYawOffset - 90.0F) / 57.29578F));
/* 673 */     passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity entityIn) {
/* 678 */     if (entityIn instanceof EntityPlayer && !shouldAttackPlayers()) {
/* 679 */       return false;
/*     */     }
/* 681 */     openMouth();
/* 682 */     return super.attackEntityAsMob(entityIn);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
/* 687 */     if (entityIn instanceof EntityPlayer && this.rand.nextInt(3) == 0) {
/* 688 */       MoCreatures.poisonPlayer((EntityPlayer)entityIn);
/* 689 */       ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 0));
/*     */     } 
/*     */     
/* 692 */     super.applyEnchantments(entityLivingBaseIn, entityIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 697 */     Entity entity = damagesource.getTrueSource();
/* 698 */     if (isRidingOrBeingRiddenBy(entity)) {
/* 699 */       return false;
/*     */     }
/* 701 */     if (super.attackEntityFrom(damagesource, i)) {
/* 702 */       if (entity != null && getIsTamed() && entity instanceof EntityPlayer) {
/* 703 */         return false;
/*     */       }
/*     */       
/* 706 */       if (entity != this && super.shouldAttackPlayers()) {
/* 707 */         setAttackTarget((EntityLivingBase)entity);
/*     */       }
/* 709 */       return true;
/*     */     } 
/* 711 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 721 */     super.writeEntityToNBT(nbttagcompound);
/* 722 */     nbttagcompound.setBoolean("Saddle", getIsRideable());
/* 723 */     nbttagcompound.setBoolean("Chested", getIsChested());
/* 724 */     nbttagcompound.setInteger("ArmorType", getArmorType());
/* 725 */     nbttagcompound.setBoolean("isSitting", getIsSitting());
/* 726 */     nbttagcompound.setBoolean("isGhost", getIsGhost());
/* 727 */     if (getIsChested() && this.localchest != null) {
/* 728 */       NBTTagList nbttaglist = new NBTTagList();
/* 729 */       for (int i = 0; i < this.localchest.getSizeInventory(); i++) {
/* 730 */         this.localstack = this.localchest.getStackInSlot(i);
/* 731 */         if (this.localstack != null && !this.localstack.isEmpty()) {
/* 732 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 733 */           nbttagcompound1.setByte("Slot", (byte)i);
/* 734 */           this.localstack.writeToNBT(nbttagcompound1);
/* 735 */           nbttaglist.appendTag((NBTBase)nbttagcompound1);
/*     */         } 
/*     */       } 
/* 738 */       nbttagcompound.setTag("Items", (NBTBase)nbttaglist);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 744 */     super.readEntityFromNBT(nbttagcompound);
/* 745 */     setRideable(nbttagcompound.getBoolean("Saddle"));
/* 746 */     setIsChested(nbttagcompound.getBoolean("Chested"));
/* 747 */     setArmorType(nbttagcompound.getInteger("ArmorType"));
/* 748 */     setSitting(nbttagcompound.getBoolean("isSitting"));
/* 749 */     setIsGhost(nbttagcompound.getBoolean("isGhost"));
/* 750 */     if (getIsChested()) {
/* 751 */       NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
/* 752 */       this.localchest = new MoCAnimalChest("WyvernChest", 14);
/* 753 */       for (int i = 0; i < nbttaglist.tagCount(); i++) {
/* 754 */         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
/* 755 */         int j = nbttagcompound1.getByte("Slot") & 0xFF;
/* 756 */         if (j >= 0 && j < this.localchest.getSizeInventory()) {
/* 757 */           this.localchest.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int nameYOffset() {
/* 765 */     int yOff = getEdad() * -1;
/* 766 */     if (yOff < -120) {
/* 767 */       yOff = -120;
/*     */     }
/* 769 */     if (getIsSitting())
/* 770 */       yOff += 25; 
/* 771 */     return yOff;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMyHealFood(ItemStack stack) {
/* 776 */     return (!stack.isEmpty() && (stack.getItem() == MoCItems.ratRaw || stack.getItem() == MoCItems.rawTurkey));
/*     */   }
/*     */   
/*     */   private void openMouth() {
/* 780 */     if (!this.world.isRemote) {
/* 781 */       this.mouthCounter = 1;
/* 782 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
/* 783 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void performAnimation(int animationType) {
/* 790 */     if (animationType == 1)
/*     */     {
/* 792 */       this.mouthCounter = 1;
/*     */     }
/* 794 */     if (animationType == 2)
/*     */     {
/* 796 */       this.diveCounter = 1;
/*     */     }
/* 798 */     if (animationType == 3) {
/* 799 */       this.wingFlapCounter = 1;
/*     */     }
/* 801 */     if (animationType > 5 && animationType < 9) {
/*     */       
/* 803 */       this.transformType = animationType;
/* 804 */       this.transformCounter = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void makeEntityDive() {
/* 810 */     if (!this.world.isRemote) {
/* 811 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 2), new NetworkRegistry.TargetPoint(this.world.provider
/* 812 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/* 814 */     super.makeEntityDive();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean flag, int x) {
/* 819 */     int chance = MoCreatures.proxy.wyvernEggDropChance;
/* 820 */     if (getType() == 5) {
/* 821 */       chance = MoCreatures.proxy.motherWyvernEggDropChance;
/*     */     }
/* 823 */     if (this.rand.nextInt(100) < chance) {
/* 824 */       entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, getType() + 49), 0.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 830 */     return !isBeingRidden();
/*     */   }
/*     */ 
/*     */   
/*     */   public void dropMyStuff() {
/* 835 */     if (!this.world.isRemote) {
/* 836 */       dropArmor();
/* 837 */       MoCTools.dropSaddle((MoCEntityAnimal)this, this.world);
/*     */       
/* 839 */       if (getIsChested()) {
/* 840 */         MoCTools.dropInventory((Entity)this, this.localchest);
/* 841 */         MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Block)Blocks.CHEST, 1));
/* 842 */         setIsChested(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAdjustedYOffset() {
/* 849 */     if (getIsSitting()) {
/* 850 */       return 0.4F;
/*     */     }
/* 852 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getCustomSpeed() {
/* 857 */     if (isBeingRidden()) {
/* 858 */       return 1.0D;
/*     */     }
/* 860 */     return 0.8D;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/* 865 */     if (getType() == 5) {
/* 866 */       return 180;
/*     */     }
/* 868 */     if (getType() == 6 || getType() == 7 || getType() == 8) {
/* 869 */       return 160;
/*     */     }
/* 871 */     return 120;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 876 */     if (getType() == 6 || getIsGhost()) {
/* 877 */       return EnumCreatureAttribute.UNDEAD;
/*     */     }
/* 879 */     return super.getCreatureAttribute();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadyToHunt() {
/* 884 */     return (!isMovementCeased() && !isBeingRidden());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 889 */     return (!(entity instanceof MoCEntityWyvern) && entity.height <= 1.0D && entity.width <= 1.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected double flyerThrust() {
/* 894 */     return 0.6D;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAIMoveSpeed() {
/* 899 */     if (getIsFlying()) {
/* 900 */       return 0.4F;
/*     */     }
/* 902 */     return super.getAIMoveSpeed();
/*     */   }
/*     */ 
/*     */   
/*     */   protected float flyerFriction() {
/* 907 */     if (getType() == 5) {
/* 908 */       return 0.96F;
/*     */     }
/* 910 */     if (getType() == 6 || getType() == 7 || getType() == 8 || getIsGhost()) {
/* 911 */       return 0.96F;
/*     */     }
/* 913 */     return 0.94F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void makeEntityJump() {
/* 918 */     wingFlap();
/* 919 */     super.makeEntityJump();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldAttackPlayers() {
/* 924 */     return (!getIsTamed() && super.shouldAttackPlayers());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource damagesource) {
/* 929 */     if (!this.world.isRemote) {
/* 930 */       if (getType() == 6) {
/* 931 */         MoCTools.spawnMaggots(this.world, (Entity)this);
/*     */       }
/*     */       
/* 934 */       if (!getIsGhost() && getIsTamed() && this.rand.nextInt(4) == 0) {
/* 935 */         MoCEntityWyvern entitywyvern = new MoCEntityWyvern(this.world);
/* 936 */         entitywyvern.setPosition(this.posX, this.posY, this.posZ);
/* 937 */         this.world.spawnEntity((Entity)entitywyvern);
/* 938 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR);
/*     */         
/* 940 */         entitywyvern.setOwnerId(getOwnerId());
/* 941 */         entitywyvern.setTamed(true);
/* 942 */         EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 943 */         if (entityplayer != null) {
/* 944 */           MoCTools.tameWithName(entityplayer, (IMoCTameable)entitywyvern);
/*     */         }
/*     */         
/* 947 */         entitywyvern.setAdult(false);
/* 948 */         entitywyvern.setEdad(1);
/* 949 */         entitywyvern.setType(getType());
/* 950 */         entitywyvern.selectType();
/* 951 */         entitywyvern.setIsGhost(true);
/*     */       } 
/*     */     } 
/*     */     
/* 955 */     super.onDeath(damagesource);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float tFloat() {
/* 961 */     if (++this.tCounter > 30) {
/* 962 */       this.tCounter = 0;
/* 963 */       this.fTransparency = this.rand.nextFloat() * 0.2F + 0.15F;
/*     */     } 
/*     */     
/* 966 */     if (getEdad() < 10) {
/* 967 */       return 0.0F;
/*     */     }
/* 969 */     return this.fTransparency;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canBeTrappedInNet() {
/* 974 */     return (getIsTamed() && !getIsGhost());
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityWyvern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */