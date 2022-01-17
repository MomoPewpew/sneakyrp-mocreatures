/*      */ package drzhark.mocreatures.entity.passive;
/*      */
/*      */ import drzhark.mocreatures.MoCTools;
/*      */ import drzhark.mocreatures.MoCreatures;
/*      */ import drzhark.mocreatures.entity.IMoCTameable;
/*      */ import drzhark.mocreatures.entity.MoCEntityAnimal;
/*      */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*      */ import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
/*      */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*      */ import drzhark.mocreatures.init.MoCItems;
/*      */ import drzhark.mocreatures.init.MoCSoundEvents;
/*      */ import drzhark.mocreatures.inventory.MoCAnimalChest;
/*      */ import drzhark.mocreatures.item.MoCItemRecord;
/*      */ import drzhark.mocreatures.network.MoCMessageHandler;
/*      */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*      */ import drzhark.mocreatures.network.message.MoCMessageHeart;
/*      */ import drzhark.mocreatures.network.message.MoCMessageVanish;
/*      */ import java.util.List;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockJukebox;
/*      */ import net.minecraft.block.SoundType;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EnumCreatureAttribute;
/*      */ import net.minecraft.entity.IEntityLivingData;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityAIBase;
/*      */ import net.minecraft.entity.ai.EntityAISwimming;
/*      */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.passive.AbstractHorse;
/*      */ import net.minecraft.entity.passive.EntityHorse;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.init.SoundEvents;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.network.datasync.DataParameter;
/*      */ import net.minecraft.network.datasync.DataSerializers;
/*      */ import net.minecraft.network.datasync.EntityDataManager;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EnumHand;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.SoundEvent;
/*      */ import net.minecraft.util.math.BlockPos;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.world.DifficultyInstance;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.biome.Biome;
/*      */ import net.minecraftforge.common.BiomeDictionary;
/*      */ import net.minecraftforge.common.DimensionManager;
/*      */ import net.minecraftforge.common.util.FakePlayerFactory;
/*      */ import net.minecraftforge.event.world.BlockEvent;
/*      */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*      */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*      */
/*      */ public class MoCEntityHorse extends MoCEntityTameableAnimal {
/*      */   private int gestationtime;
/*      */   private int countEating;
/*      */   private int textCounter;
/*   70 */   private float transFloat = 0.2F;
/*      */
/*      */   private int fCounter;
/*      */
/*      */   public int shuffleCounter;
/*      */
/*      */   public int wingFlapCounter;
/*      */
/*      */   public MoCAnimalChest localchest;
/*      */
/*      */   public boolean eatenpumpkin;
/*      */
/*      */   private boolean hasReproduced;
/*      */   private int nightmareInt;
/*      */   public ItemStack localstack;
/*   85 */   private static final DataParameter<Boolean> RIDEABLE = EntityDataManager.createKey(MoCEntityHorse.class, DataSerializers.BOOLEAN); public int mouthCounter; public int standCounter; public int tailCounter; public int vanishCounter; public int sprintCounter; public int transformType; public int transformCounter; protected EntityAIWanderMoC2 wander;
/*   86 */   private static final DataParameter<Boolean> CHESTED = EntityDataManager.createKey(MoCEntityHorse.class, DataSerializers.BOOLEAN);
/*   87 */   private static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(MoCEntityHorse.class, DataSerializers.BOOLEAN);
/*   88 */   private static final DataParameter<Boolean> BRED = EntityDataManager.createKey(MoCEntityHorse.class, DataSerializers.BOOLEAN);
/*   89 */   private static final DataParameter<Integer> ARMOR_TYPE = EntityDataManager.createKey(MoCEntityHorse.class, DataSerializers.VARINT);
/*      */
/*      */   public MoCEntityHorse(World world) {
/*   92 */     super(world);
/*   93 */     setSize(1.4F, 1.6F);
/*   94 */     this.gestationtime = 0;
/*   95 */     this.eatenpumpkin = false;
/*   96 */     this.nightmareInt = 0;
/*   97 */     this.isImmuneToFire = false;
/*   98 */     setEdad(50);
/*   99 */     setIsChested(false);
/*  100 */     this.stepHeight = 1.0F;
/*      */
/*  102 */     if (!this.world.isRemote) {
/*  103 */       if (this.rand.nextInt(5) == 0) {
/*  104 */         setAdult(false);
/*      */       } else {
/*  106 */         setAdult(true);
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */
/*      */   protected void initEntityAI() {
/*  113 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  114 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
/*  115 */     this.tasks.addTask(4, (EntityAIBase)(this.wander = new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80)));
/*  116 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*      */   }
/*      */
/*      */
/*      */   protected void applyEntityAttributes() {
/*  121 */     super.applyEntityAttributes();
/*  122 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
/*  123 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*      */   }
/*      */
/*      */
/*      */   protected void entityInit() {
/*  128 */     super.entityInit();
/*  129 */     this.dataManager.register(RIDEABLE, Boolean.valueOf(false));
/*  130 */     this.dataManager.register(SITTING, Boolean.valueOf(false));
/*  131 */     this.dataManager.register(CHESTED, Boolean.valueOf(false));
/*  132 */     this.dataManager.register(BRED, Boolean.valueOf(false));
/*  133 */     this.dataManager.register(ARMOR_TYPE, Integer.valueOf(0));
/*      */   }
/*      */
/*      */
/*      */   public int getArmorType() {
/*  138 */     return ((Integer)this.dataManager.get(ARMOR_TYPE)).intValue();
/*      */   }
/*      */
/*      */   public boolean getIsChested() {
/*  142 */     return ((Boolean)this.dataManager.get(CHESTED)).booleanValue();
/*      */   }
/*      */
/*      */
/*      */   public boolean getIsSitting() {
/*  147 */     return ((Boolean)this.dataManager.get(SITTING)).booleanValue();
/*      */   }
/*      */
/*      */   public boolean getHasBred() {
/*  151 */     return ((Boolean)this.dataManager.get(BRED)).booleanValue();
/*      */   }
/*      */
/*      */   public void setBred(boolean flag) {
/*  155 */     this.dataManager.set(BRED, Boolean.valueOf(flag));
/*      */   }
/*      */
/*      */
/*      */   public boolean getIsRideable() {
/*  160 */     return ((Boolean)this.dataManager.get(RIDEABLE)).booleanValue();
/*      */   }
/*      */
/*      */   public void setRideable(boolean flag) {
/*  164 */     this.dataManager.set(RIDEABLE, Boolean.valueOf(flag));
/*      */   }
/*      */
/*      */
/*      */   public void setArmorType(int i) {
/*  169 */     this.dataManager.set(ARMOR_TYPE, Integer.valueOf(i));
/*      */   }
/*      */
/*      */   public void setIsChested(boolean flag) {
/*  173 */     this.dataManager.set(CHESTED, Boolean.valueOf(flag));
/*      */   }
/*      */
/*      */   public void setSitting(boolean flag) {
/*  177 */     this.dataManager.set(SITTING, Boolean.valueOf(flag));
/*      */   }
/*      */
/*      */
/*      */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/*  182 */     Entity entity = damagesource.getTrueSource();
/*  183 */     if (isBeingRidden() && entity == getRidingEntity()) {
/*  184 */       return false;
/*      */     }
/*  186 */     if (entity instanceof net.minecraft.entity.passive.EntityWolf) {
/*  187 */       EntityCreature entitycreature = (EntityCreature)entity;
/*  188 */       entitycreature.setAttackTarget(null);
/*  189 */       return false;
/*      */     }
/*  191 */     i -= (getArmorType() + 2);
/*  192 */     if (i < 0.0F) {
/*  193 */       i = 0.0F;
/*      */     }
/*  195 */     return super.attackEntityFrom(damagesource, i);
/*      */   }
/*      */
/*      */
/*      */
/*      */   public boolean canBeCollidedWith() {
/*  201 */     return !isBeingRidden();
/*      */   }
/*      */
/*      */
/*      */   public boolean checkSpawningBiome() {
/*  206 */     BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), this.posZ);
/*      */
/*  208 */     Biome currentbiome = MoCTools.Biomekind(this.world, pos);
/*  209 */     String s = MoCTools.biomeName(this.world, pos);
/*      */     try {
/*  211 */       if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.PLAINS) &&
/*  212 */         this.rand.nextInt(3) == 0) {
/*  213 */         setType(60);
/*      */       }
/*      */
/*  216 */       if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SANDY)) {
/*  217 */         setType(60);
/*      */       }
/*      */
/*  220 */       if (s.toLowerCase().contains("prairie"))
/*      */       {
/*  222 */         setType(this.rand.nextInt(5) + 1);
/*      */       }
/*  224 */     } catch (Exception exception) {}
/*      */
/*  226 */     return true;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public float colorFX(int sColor, int typeInt) {
/*  239 */     if (typeInt == 48) {
/*      */
/*  241 */       if (sColor == 1) {
/*  242 */         return 0.69921875F;
/*      */       }
/*  244 */       if (sColor == 2) {
/*  245 */         return 0.625F;
/*      */       }
/*  247 */       return 0.0859375F;
/*      */     }
/*      */
/*  250 */     if (typeInt == 49) {
/*      */
/*  252 */       if (sColor == 1) {
/*  253 */         return 0.57421875F;
/*      */       }
/*  255 */       if (sColor == 2) {
/*  256 */         return 0.3515625F;
/*      */       }
/*  258 */       return 0.76171875F;
/*      */     }
/*      */
/*  261 */     if (typeInt == 51) {
/*      */
/*  263 */       if (sColor == 1) {
/*  264 */         return 0.1171875F;
/*      */       }
/*  266 */       if (sColor == 2) {
/*  267 */         return 0.5625F;
/*      */       }
/*  269 */       return 0.99609375F;
/*      */     }
/*  271 */     if (typeInt == 52) {
/*      */
/*  273 */       if (sColor == 1) {
/*  274 */         return 0.99609375F;
/*      */       }
/*  276 */       if (sColor == 2) {
/*  277 */         return 0.41015625F;
/*      */       }
/*  279 */       return 0.703125F;
/*      */     }
/*      */
/*  282 */     if (typeInt == 53) {
/*      */
/*  284 */       if (sColor == 1) {
/*  285 */         return 0.734375F;
/*      */       }
/*  287 */       if (sColor == 2) {
/*  288 */         return 0.9296875F;
/*      */       }
/*  290 */       return 0.40625F;
/*      */     }
/*      */
/*  293 */     if (typeInt == 54) {
/*      */
/*  295 */       if (sColor == 1) {
/*  296 */         return 0.4296875F;
/*      */       }
/*  298 */       if (sColor == 2) {
/*  299 */         return 0.48046875F;
/*      */       }
/*  301 */       return 0.54296875F;
/*      */     }
/*      */
/*  304 */     if (typeInt == 55) {
/*      */
/*  306 */       if (sColor == 1) {
/*  307 */         return 0.7578125F;
/*      */       }
/*  309 */       if (sColor == 2) {
/*  310 */         return 0.11328125F;
/*      */       }
/*  312 */       return 0.1328125F;
/*      */     }
/*      */
/*  315 */     if (typeInt == 56) {
/*      */
/*  317 */       if (sColor == 1) {
/*  318 */         return 0.24609375F;
/*      */       }
/*  320 */       if (sColor == 2) {
/*  321 */         return 0.17578125F;
/*      */       }
/*  323 */       return 0.99609375F;
/*      */     }
/*      */
/*  326 */     if (typeInt == 57) {
/*      */
/*  328 */       if (sColor == 1) {
/*  329 */         return 0.26953125F;
/*      */       }
/*  331 */       if (sColor == 2) {
/*  332 */         return 0.5703125F;
/*      */       }
/*  334 */       return 0.56640625F;
/*      */     }
/*      */
/*  337 */     if (typeInt == 58) {
/*      */
/*  339 */       if (sColor == 1) {
/*  340 */         return 0.3515625F;
/*      */       }
/*  342 */       if (sColor == 2) {
/*  343 */         return 0.53125F;
/*      */       }
/*  345 */       return 0.16796875F;
/*      */     }
/*      */
/*  348 */     if (typeInt == 59) {
/*      */
/*  350 */       if (sColor == 1) {
/*  351 */         return 0.8515625F;
/*      */       }
/*  353 */       if (sColor == 2) {
/*  354 */         return 0.15625F;
/*      */       }
/*  356 */       return 0.0F;
/*      */     }
/*      */
/*  359 */     if (typeInt > 22 && typeInt < 26) {
/*      */
/*  361 */       if (sColor == 1) {
/*  362 */         return 0.234375F;
/*      */       }
/*  364 */       if (sColor == 2) {
/*  365 */         return 0.69921875F;
/*      */       }
/*  367 */       return 0.4375F;
/*      */     }
/*      */
/*  370 */     if (typeInt == 40) {
/*      */
/*  372 */       if (sColor == 1) {
/*  373 */         return 0.54296875F;
/*      */       }
/*  375 */       if (sColor == 2) {
/*  376 */         return 0.0F;
/*      */       }
/*  378 */       return 0.0F;
/*      */     }
/*      */
/*      */
/*      */
/*  383 */     if (sColor == 1) {
/*  384 */       return 0.99609375F;
/*      */     }
/*  386 */     if (sColor == 2) {
/*  387 */       return 0.921875F;
/*      */     }
/*  389 */     return 0.54296875F;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */   public void dissapearHorse() {
/*  396 */     this.isDead = true;
/*      */   }
/*      */
/*      */   private void drinkingHorse() {
/*  400 */     openMouth();
/*  401 */     MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_DRINKING);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public void dropArmor() {
/*  409 */     if (!this.world.isRemote) {
/*  410 */       int i = getArmorType();
/*  411 */       if (i != 0) {
/*  412 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
/*      */       }
/*      */
/*  415 */       if (i == 1) {
/*  416 */         EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Items.IRON_HORSE_ARMOR, 1));
/*  417 */         entityitem.setPickupDelay(10);
/*  418 */         this.world.spawnEntity((Entity)entityitem);
/*      */       }
/*  420 */       if (i == 2) {
/*  421 */         EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Items.GOLDEN_HORSE_ARMOR, 1));
/*  422 */         entityitem.setPickupDelay(10);
/*  423 */         this.world.spawnEntity((Entity)entityitem);
/*      */       }
/*  425 */       if (i == 3) {
/*  426 */         EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Items.DIAMOND_HORSE_ARMOR, 1));
/*  427 */         entityitem.setPickupDelay(10);
/*  428 */         this.world.spawnEntity((Entity)entityitem);
/*      */       }
/*  430 */       if (i == 4) {
/*  431 */         EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack((Item)MoCItems.horsearmorcrystal, 1));
/*      */
/*  433 */         entityitem.setPickupDelay(10);
/*  434 */         this.world.spawnEntity((Entity)entityitem);
/*      */       }
/*  436 */       setArmorType(0);
/*      */     }
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */   public void dropBags() {
/*  444 */     if (!isBagger() || !getIsChested() || this.world.isRemote) {
/*      */       return;
/*      */     }
/*      */
/*  448 */     EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack((Block)Blocks.CHEST, 1));
/*  449 */     float f3 = 0.05F;
/*  450 */     entityitem.motionX = ((float)this.world.rand.nextGaussian() * f3);
/*  451 */     entityitem.motionY = ((float)this.world.rand.nextGaussian() * f3 + 0.2F);
/*  452 */     entityitem.motionZ = ((float)this.world.rand.nextGaussian() * f3);
/*  453 */     this.world.spawnEntity((Entity)entityitem);
/*  454 */     setIsChested(false);
/*      */   }
/*      */
/*      */   private void eatingHorse() {
/*  458 */     openMouth();
/*  459 */     MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/*      */   }
/*      */
/*      */
/*      */   public void fall(float f, float f1) {
/*  464 */     if (isFlyer() || isFloater()) {
/*      */       return;
/*      */     }
/*      */
/*  468 */     float i = (float)(Math.ceil((f - 3.0F)) / 2.0D);
/*  469 */     if (!this.world.isRemote && i > 0.0F) {
/*  470 */       if (getType() >= 10) {
/*  471 */         i /= 2.0F;
/*      */       }
/*  473 */       if (i > 1.0F) {
/*  474 */         attackEntityFrom(DamageSource.FALL, i);
/*      */       }
/*  476 */       if (isBeingRidden() && i > 1.0F) {
/*  477 */         for (Entity entity : getRecursivePassengers())
/*      */         {
/*  479 */           entity.attackEntityFrom(DamageSource.FALL, i);
/*      */         }
/*      */       }
/*  482 */       IBlockState iblockstate = this.world.getBlockState(new BlockPos(this.posX, this.posY - 0.2D - this.prevRotationYaw, this.posZ));
/*  483 */       Block block = iblockstate.getBlock();
/*      */
/*  485 */       if (iblockstate.getMaterial() != Material.AIR && !isSilent()) {
/*      */
/*  487 */         SoundType soundtype = block.getSoundType(iblockstate, this.world, new BlockPos(this.posX, this.posY - 0.2D - this.prevRotationYaw, this.posZ), (Entity)this);
/*  488 */         this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, soundtype.getStepSound(), getSoundCategory(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */   public int getInventorySize() {
/*  494 */     if (getType() == 40)
/*  495 */       return 18;
/*  496 */     if (getType() > 64) {
/*  497 */       return 27;
/*      */     }
/*  499 */     return 9;
/*      */   }
/*      */
/*      */
/*      */   public double getCustomJump() {
/*  504 */     double HorseJump = 0.35D;
/*  505 */     if (getType() < 6) {
/*      */
/*  507 */       HorseJump = 0.35D;
/*  508 */     } else if (getType() > 5 && getType() < 11) {
/*      */
/*  510 */       HorseJump = 0.4D;
/*  511 */     } else if (getType() > 10 && getType() < 16) {
/*      */
/*  513 */       HorseJump = 0.45D;
/*  514 */     } else if (getType() > 15 && getType() < 21) {
/*      */
/*  516 */       HorseJump = 0.5D;
/*      */
/*      */     }
/*  519 */     else if (getType() > 20 && getType() < 26) {
/*      */
/*  521 */       HorseJump = 0.45D;
/*  522 */     } else if (getType() > 25 && getType() < 30) {
/*      */
/*  524 */       HorseJump = 0.5D;
/*  525 */     } else if (getType() >= 30 && getType() < 40) {
/*      */
/*  527 */       HorseJump = 0.55D;
/*  528 */     } else if (getType() >= 40 && getType() < 60) {
/*      */
/*  530 */       HorseJump = 0.6D;
/*  531 */     } else if (getType() >= 60) {
/*      */
/*  533 */       HorseJump = 0.4D;
/*      */     }
/*  535 */     return HorseJump;
/*      */   }
/*      */
/*      */
/*      */   public double getCustomSpeed() {
/*  540 */     double HorseSpeed = 0.8D;
/*  541 */     if (getType() < 6) {
/*      */
/*  543 */       HorseSpeed = 0.9D;
/*  544 */     } else if (getType() > 5 && getType() < 11) {
/*      */
/*  546 */       HorseSpeed = 1.0D;
/*  547 */     } else if (getType() > 10 && getType() < 16) {
/*      */
/*  549 */       HorseSpeed = 1.1D;
/*  550 */     } else if (getType() > 15 && getType() < 21) {
/*      */
/*  552 */       HorseSpeed = 1.2D;
/*      */
/*      */     }
/*  555 */     else if (getType() > 20 && getType() < 26) {
/*      */
/*  557 */       HorseSpeed = 0.8D;
/*  558 */     } else if (getType() > 25 && getType() < 30) {
/*      */
/*  560 */       HorseSpeed = 1.0D;
/*  561 */     } else if (getType() > 30 && getType() < 40) {
/*      */
/*  563 */       HorseSpeed = 1.2D;
/*  564 */     } else if (getType() >= 40 && getType() < 60) {
/*      */
/*      */
/*  567 */       HorseSpeed = 1.3D;
/*  568 */     } else if (getType() == 60 || getType() == 61) {
/*      */
/*  570 */       HorseSpeed = 1.1D;
/*  571 */     } else if (getType() == 65) {
/*      */
/*  573 */       HorseSpeed = 0.7D;
/*  574 */     } else if (getType() > 65) {
/*      */
/*  576 */       HorseSpeed = 0.9D;
/*      */     }
/*  578 */     if (this.sprintCounter > 0 && this.sprintCounter < 150) {
/*  579 */       HorseSpeed *= 1.5D;
/*      */     }
/*  581 */     if (this.sprintCounter > 150) {
/*  582 */       HorseSpeed *= 0.5D;
/*      */     }
/*  584 */     return HorseSpeed;
/*      */   }
/*      */
/*      */
/*      */   protected SoundEvent getDeathSound() {
/*  589 */     openMouth();
/*  590 */     if (isUndead()) {
/*  591 */       return MoCSoundEvents.ENTITY_HORSE_DEATH_UNDEAD;
/*      */     }
/*  593 */     if (getIsGhost()) {
/*  594 */       return MoCSoundEvents.ENTITY_HORSE_DEATH_GHOST;
/*      */     }
/*  596 */     if (getType() == 60 || getType() == 61) {
/*  597 */       return MoCSoundEvents.ENTITY_HORSE_HURT_ZEBRA;
/*      */     }
/*  599 */     if (getType() >= 65 && getType() <= 67) {
/*  600 */       return MoCSoundEvents.ENTITY_HORSE_DEATH_DONKEY;
/*      */     }
/*  602 */     return MoCSoundEvents.ENTITY_HORSE_DEATH;
/*      */   }
/*      */
/*      */
/*      */   public boolean renderName() {
/*  607 */     if (getIsGhost() && getEdad() < 10) {
/*  608 */       return false;
/*      */     }
/*      */
/*  611 */     return super.renderName();
/*      */   }
/*      */
/*      */
/*      */   protected Item getDropItem() {
/*  616 */     boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);
/*      */
/*  618 */     if (flag && (getType() == 36 || (getType() >= 50 && getType() < 60)))
/*      */     {
/*  620 */       return (Item)MoCItems.unicornhorn;
/*      */     }
/*  622 */     if (getType() == 39)
/*      */     {
/*  624 */       return Items.FEATHER;
/*      */     }
/*  626 */     if (getType() == 40)
/*      */     {
/*  628 */       return Items.FEATHER;
/*      */     }
/*  630 */     if (getType() == 38 && flag && this.world.provider.doesWaterVaporize())
/*      */     {
/*  632 */       return (Item)MoCItems.heartfire;
/*      */     }
/*  634 */     if (getType() == 32 && flag)
/*      */     {
/*  636 */       return (Item)MoCItems.heartdarkness;
/*      */     }
/*  638 */     if (getType() == 26)
/*      */     {
/*  640 */       return Items.BONE;
/*      */     }
/*  642 */     if (getType() == 23 || getType() == 24 || getType() == 25) {
/*  643 */       if (flag) {
/*  644 */         return (Item)MoCItems.heartundead;
/*      */       }
/*  646 */       return Items.ROTTEN_FLESH;
/*      */     }
/*  648 */     if (getType() == 21 || getType() == 22) {
/*  649 */       return Items.GHAST_TEAR;
/*      */     }
/*      */
/*  652 */     return Items.LEATHER;
/*      */   }
/*      */
/*      */   public boolean getHasReproduced() {
/*  656 */     return this.hasReproduced;
/*      */   }
/*      */
/*      */
/*      */   protected SoundEvent getHurtSound(DamageSource source) {
/*  661 */     openMouth();
/*  662 */     if (isFlyer() && !isBeingRidden()) {
/*  663 */       wingFlap();
/*      */     }
/*  665 */     else if (this.rand.nextInt(3) == 0) {
/*  666 */       stand();
/*      */     }
/*      */
/*  669 */     if (isUndead()) {
/*  670 */       return MoCSoundEvents.ENTITY_HORSE_HURT_UNDEAD;
/*      */     }
/*  672 */     if (getIsGhost()) {
/*  673 */       return MoCSoundEvents.ENTITY_HORSE_HURT_GHOST;
/*      */     }
/*  675 */     if (getType() == 60 || getType() == 61) {
/*  676 */       return MoCSoundEvents.ENTITY_HORSE_HURT_ZEBRA;
/*      */     }
/*  678 */     if (getType() >= 65 && getType() <= 67) {
/*  679 */       return MoCSoundEvents.ENTITY_HORSE_HURT_DONKEY;
/*      */     }
/*  681 */     return MoCSoundEvents.ENTITY_HORSE_HURT;
/*      */   }
/*      */
/*      */
/*      */   protected SoundEvent getAmbientSound() {
/*  686 */     openMouth();
/*  687 */     if (this.rand.nextInt(10) == 0 && !isMovementCeased()) {
/*  688 */       stand();
/*      */     }
/*  690 */     if (isUndead()) {
/*  691 */       return MoCSoundEvents.ENTITY_HORSE_AMBIENT_UNDEAD;
/*      */     }
/*  693 */     if (getIsGhost()) {
/*  694 */       return MoCSoundEvents.ENTITY_HORSE_AMBIENT_GHOST;
/*      */     }
/*  696 */     if (getType() == 60 || getType() == 61) {
/*  697 */       return MoCSoundEvents.ENTITY_HORSE_AMBIENT_ZEBRA;
/*      */     }
/*  699 */     if (getType() >= 65 && getType() <= 67) {
/*  700 */       return MoCSoundEvents.ENTITY_HORSE_HURT_DONKEY;
/*      */     }
/*  702 */     return MoCSoundEvents.ENTITY_HORSE_AMBIENT;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   protected SoundEvent getAngrySound() {
/*  710 */     openMouth();
/*  711 */     stand();
/*  712 */     if (isUndead()) {
/*  713 */       return MoCSoundEvents.ENTITY_HORSE_ANGRY_UNDEAD;
/*      */     }
/*  715 */     if (getIsGhost()) {
/*  716 */       return MoCSoundEvents.ENTITY_HORSE_ANGRY_GHOST;
/*      */     }
/*  718 */     if (getType() == 60 || getType() == 61) {
/*  719 */       return MoCSoundEvents.ENTITY_HORSE_HURT_ZEBRA;
/*      */     }
/*  721 */     if (getType() >= 65 && getType() <= 67) {
/*  722 */       return MoCSoundEvents.ENTITY_HORSE_HURT_DONKEY;
/*      */     }
/*  724 */     return MoCSoundEvents.ENTITY_HORSE_MAD;
/*      */   }
/*      */
/*      */   public float calculateMaxHealth() {
/*  728 */     int maximumHealth = 30;
/*  729 */     if (getType() < 6) {
/*      */
/*  731 */       maximumHealth = 25;
/*  732 */     } else if (getType() > 5 && getType() < 11) {
/*      */
/*  734 */       maximumHealth = 30;
/*  735 */     } else if (getType() > 10 && getType() < 16) {
/*      */
/*  737 */       maximumHealth = 35;
/*  738 */     } else if (getType() > 15 && getType() < 21) {
/*      */
/*  740 */       maximumHealth = 40;
/*      */
/*      */     }
/*  743 */     else if (getType() > 20 && getType() < 26) {
/*      */
/*  745 */       maximumHealth = 35;
/*  746 */     } else if (getType() > 25 && getType() < 30) {
/*      */
/*  748 */       maximumHealth = 35;
/*  749 */     } else if (getType() >= 30 && getType() < 40) {
/*      */
/*  751 */       maximumHealth = 50;
/*  752 */     } else if (getType() == 40) {
/*      */
/*  754 */       maximumHealth = 50;
/*  755 */     } else if (getType() > 40 && getType() < 60) {
/*      */
/*  757 */       maximumHealth = 40;
/*  758 */     } else if (getType() >= 60) {
/*      */
/*  760 */       maximumHealth = 30;
/*      */     }
/*      */
/*  763 */     return maximumHealth;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public int getMaxTemper() {
/*  773 */     if (getType() == 60) {
/*  774 */       return 200;
/*      */     }
/*  776 */     return 100;
/*      */   }
/*      */
/*      */   public int getNightmareInt() {
/*  780 */     return this.nightmareInt;
/*      */   }
/*      */
/*      */
/*      */   protected float getSoundVolume() {
/*  785 */     return 0.8F;
/*      */   }
/*      */
/*      */
/*      */   public int getTalkInterval() {
/*  790 */     return 400;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public ResourceLocation getTexture() {
/*      */     String tempTexture;
/*  800 */     switch (getType()) {
/*      */       case 1:
/*  802 */         tempTexture = "horsewhite.png";
/*      */         break;
/*      */       case 2:
/*  805 */         tempTexture = "horsecreamy.png";
/*      */         break;
/*      */       case 3:
/*  808 */         tempTexture = "horsebrown.png";
/*      */         break;
/*      */       case 4:
/*  811 */         tempTexture = "horsedarkbrown.png";
/*      */         break;
/*      */       case 5:
/*  814 */         tempTexture = "horseblack.png";
/*      */         break;
/*      */       case 6:
/*  817 */         tempTexture = "horsebrightcreamy.png";
/*      */         break;
/*      */       case 7:
/*  820 */         tempTexture = "horsespeckled.png";
/*      */         break;
/*      */       case 8:
/*  823 */         tempTexture = "horsepalebrown.png";
/*      */         break;
/*      */       case 9:
/*  826 */         tempTexture = "horsegrey.png";
/*      */         break;
/*      */       case 11:
/*  829 */         tempTexture = "horsepinto.png";
/*      */         break;
/*      */       case 12:
/*  832 */         tempTexture = "horsebrightpinto.png";
/*      */         break;
/*      */       case 13:
/*  835 */         tempTexture = "horsepalespeckles.png";
/*      */         break;
/*      */       case 16:
/*  838 */         tempTexture = "horsespotted.png";
/*      */         break;
/*      */       case 17:
/*  841 */         tempTexture = "horsecow.png";
/*      */         break;
/*      */
/*      */       case 21:
/*  845 */         tempTexture = "horseghost.png";
/*      */         break;
/*      */       case 22:
/*  848 */         tempTexture = "horseghostb.png";
/*      */         break;
/*      */       case 23:
/*  851 */         tempTexture = "horseundead.png";
/*      */         break;
/*      */       case 24:
/*  854 */         tempTexture = "horseundeadunicorn.png";
/*      */         break;
/*      */       case 25:
/*  857 */         tempTexture = "horseundeadpegasus.png";
/*      */         break;
/*      */       case 26:
/*  860 */         tempTexture = "horseskeleton.png";
/*      */         break;
/*      */       case 27:
/*  863 */         tempTexture = "horseunicornskeleton.png";
/*      */         break;
/*      */       case 28:
/*  866 */         tempTexture = "horsepegasusskeleton.png";
/*      */         break;
/*      */       case 30:
/*  869 */         tempTexture = "horsebug.png";
/*      */         break;
/*      */       case 32:
/*  872 */         tempTexture = "horsebat.png";
/*      */         break;
/*      */       case 36:
/*  875 */         tempTexture = "horseunicorn.png";
/*      */         break;
/*      */
/*      */       case 38:
/*  879 */         tempTexture = "horsenightmare.png";
/*      */         break;
/*      */       case 39:
/*  882 */         tempTexture = "horsepegasus.png";
/*      */         break;
/*      */
/*      */       case 40:
/*  886 */         tempTexture = "horsedarkpegasus.png";
/*      */         break;
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */       case 48:
/*  895 */         tempTexture = "horsefairyyellow.png";
/*      */         break;
/*      */       case 49:
/*  898 */         tempTexture = "horsefairypurple.png";
/*      */         break;
/*      */       case 50:
/*  901 */         tempTexture = "horsefairywhite.png";
/*      */         break;
/*      */       case 51:
/*  904 */         tempTexture = "horsefairyblue.png";
/*      */         break;
/*      */       case 52:
/*  907 */         tempTexture = "horsefairypink.png";
/*      */         break;
/*      */       case 53:
/*  910 */         tempTexture = "horsefairylightgreen.png";
/*      */         break;
/*      */       case 54:
/*  913 */         tempTexture = "horsefairyblack.png";
/*      */         break;
/*      */       case 55:
/*  916 */         tempTexture = "horsefairyred.png";
/*      */         break;
/*      */       case 56:
/*  919 */         tempTexture = "horsefairydarkblue.png";
/*      */         break;
/*      */       case 57:
/*  922 */         tempTexture = "horsefairycyan.png";
/*      */         break;
/*      */       case 58:
/*  925 */         tempTexture = "horsefairygreen.png";
/*      */         break;
/*      */       case 59:
/*  928 */         tempTexture = "horsefairyorange.png";
/*      */         break;
/*      */
/*      */       case 60:
/*  932 */         tempTexture = "horsezebra.png";
/*      */         break;
/*      */       case 61:
/*  935 */         tempTexture = "horsezorse.png";
/*      */         break;
/*      */       case 65:
/*  938 */         tempTexture = "horsedonkey.png";
/*      */         break;
/*      */       case 66:
/*  941 */         tempTexture = "horsemule.png";
/*      */         break;
/*      */       case 67:
/*  944 */         tempTexture = "horsezonky.png";
/*      */         break;
/*      */
/*      */       default:
/*  948 */         tempTexture = "horsebug.png";
/*      */         break;
/*      */     }
/*  951 */     if ((isArmored() || isMagicHorse()) && getArmorType() > 0) {
/*  952 */       String armorTex = "";
/*  953 */       if (getArmorType() == 1) {
/*  954 */         armorTex = "metal.png";
/*      */       }
/*  956 */       if (getArmorType() == 2) {
/*  957 */         armorTex = "gold.png";
/*      */       }
/*  959 */       if (getArmorType() == 3) {
/*  960 */         armorTex = "diamond.png";
/*      */       }
/*  962 */       if (getArmorType() == 4) {
/*  963 */         armorTex = "crystaline.png";
/*      */       }
/*  965 */       return MoCreatures.proxy.getTexture(tempTexture.replace(".png", armorTex));
/*      */     }
/*      */
/*  968 */     if (isUndead() && getType() < 26) {
/*  969 */       String baseTex = "horseundead";
/*  970 */       int max = 79;
/*  971 */       if (getType() == 25)
/*      */       {
/*  973 */         baseTex = "horseundeadpegasus";
/*      */       }
/*      */
/*      */
/*  977 */       if (getType() == 24) {
/*      */
/*  979 */         baseTex = "horseundeadunicorn";
/*  980 */         max = 69;
/*      */       }
/*      */
/*  983 */       String iteratorTex = "1";
/*  984 */       if (MoCreatures.proxy.getAnimateTextures()) {
/*  985 */         if (this.rand.nextInt(3) == 0) {
/*  986 */           this.textCounter++;
/*      */         }
/*  988 */         if (this.textCounter < 10) {
/*  989 */           this.textCounter = 10;
/*      */         }
/*  991 */         if (this.textCounter > max) {
/*  992 */           this.textCounter = 10;
/*      */         }
/*  994 */         iteratorTex = "" + this.textCounter;
/*  995 */         iteratorTex = iteratorTex.substring(0, 1);
/*      */       }
/*      */
/*  998 */       String decayTex = "" + (getEdad() / 100);
/*  999 */       decayTex = decayTex.substring(0, 1);
/* 1000 */       return MoCreatures.proxy.getTexture(baseTex + decayTex + iteratorTex + ".png");
/*      */     }
/*      */
/*      */
/* 1004 */     if (!MoCreatures.proxy.getAnimateTextures()) {
/* 1005 */       return MoCreatures.proxy.getTexture(tempTexture);
/*      */     }
/*      */
/* 1008 */     if (isNightmare()) {
/* 1009 */       if (this.rand.nextInt(1) == 0) {
/* 1010 */         this.textCounter++;
/*      */       }
/* 1012 */       if (this.textCounter < 10) {
/* 1013 */         this.textCounter = 10;
/*      */       }
/* 1015 */       if (this.textCounter > 59) {
/* 1016 */         this.textCounter = 10;
/*      */       }
/* 1018 */       String NTA = "horsenightmare";
/* 1019 */       String NTB = "" + this.textCounter;
/* 1020 */       NTB = NTB.substring(0, 1);
/* 1021 */       String NTC = ".png";
/*      */
/* 1023 */       return MoCreatures.proxy.getTexture(NTA + NTB + NTC);
/*      */     }
/*      */
/* 1026 */     if (this.transformCounter != 0 && this.transformType != 0) {
/* 1027 */       String newText = "horseundead.png";
/* 1028 */       if (this.transformType == 23) {
/* 1029 */         newText = "horseundead.png";
/*      */       }
/* 1031 */       if (this.transformType == 24) {
/* 1032 */         newText = "horseundeadunicorn.png";
/*      */       }
/* 1034 */       if (this.transformType == 25) {
/* 1035 */         newText = "horseundeadpegasus.png";
/*      */       }
/* 1037 */       if (this.transformType == 36) {
/* 1038 */         newText = "horseunicorn.png";
/*      */       }
/* 1040 */       if (this.transformType == 39) {
/* 1041 */         newText = "horsepegasus.png";
/*      */       }
/* 1043 */       if (this.transformType == 40) {
/* 1044 */         newText = "horseblackpegasus.png";
/*      */       }
/*      */
/* 1047 */       if (this.transformType == 48) {
/* 1048 */         newText = "horsefairyyellow.png";
/*      */       }
/* 1050 */       if (this.transformType == 49) {
/* 1051 */         newText = "horsefairypurple.png";
/*      */       }
/* 1053 */       if (this.transformType == 50) {
/* 1054 */         newText = "horsefairywhite.png";
/*      */       }
/* 1056 */       if (this.transformType == 51) {
/* 1057 */         newText = "horsefairyblue.png";
/*      */       }
/* 1059 */       if (this.transformType == 52) {
/* 1060 */         newText = "horsefairypink.png";
/*      */       }
/* 1062 */       if (this.transformType == 53) {
/* 1063 */         newText = "horsefairylightgreen.png";
/*      */       }
/* 1065 */       if (this.transformType == 54) {
/* 1066 */         newText = "horsefairyblack.png";
/*      */       }
/* 1068 */       if (this.transformType == 55) {
/* 1069 */         newText = "horsefairyred.png";
/*      */       }
/* 1071 */       if (this.transformType == 56) {
/* 1072 */         newText = "horsefairydarkblue.png";
/*      */       }
/*      */
/* 1075 */       if (this.transformType == 57) {
/* 1076 */         newText = "horsefairycyan.png";
/*      */       }
/*      */
/* 1079 */       if (this.transformType == 58) {
/* 1080 */         newText = "horsefairygreen.png";
/*      */       }
/*      */
/* 1083 */       if (this.transformType == 59) {
/* 1084 */         newText = "horsefairyorange.png";
/*      */       }
/*      */
/* 1087 */       if (this.transformType == 32) {
/* 1088 */         newText = "horsebat.png";
/*      */       }
/* 1090 */       if (this.transformType == 38) {
/* 1091 */         newText = "horsenightmare1.png";
/*      */       }
/* 1093 */       if (this.transformCounter % 5 == 0) {
/* 1094 */         return MoCreatures.proxy.getTexture(newText);
/*      */       }
/* 1096 */       if (this.transformCounter > 50 && this.transformCounter % 3 == 0) {
/* 1097 */         return MoCreatures.proxy.getTexture(newText);
/*      */       }
/*      */
/* 1100 */       if (this.transformCounter > 75 && this.transformCounter % 4 == 0) {
/* 1101 */         return MoCreatures.proxy.getTexture(newText);
/*      */       }
/*      */     }
/*      */
/* 1105 */     return MoCreatures.proxy.getTexture(tempTexture);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public byte getVanishC() {
/* 1115 */     return (byte)this.vanishCounter;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   private int HorseGenetics(int typeA, int typeB) {
/* 1127 */     boolean flag = MoCreatures.proxy.easyBreeding;
/*      */
/*      */
/*      */
/*      */
/* 1132 */     if (typeA == typeB) {
/* 1133 */       return typeA;
/*      */     }
/*      */
/*      */
/* 1137 */     if ((typeA == 60 && typeB < 21) || (typeB == 60 && typeA < 21)) {
/* 1138 */       return 61;
/*      */     }
/*      */
/*      */
/* 1142 */     if ((typeA == 65 && typeB < 21) || (typeB == 65 && typeA < 21)) {
/* 1143 */       return 66;
/*      */     }
/*      */
/*      */
/* 1147 */     if ((typeA == 60 && typeB == 65) || (typeB == 60 && typeA == 65)) {
/* 1148 */       return 67;
/*      */     }
/*      */
/* 1151 */     if ((typeA > 20 && typeB < 21) || (typeB > 20 && typeA < 21)) {
/*      */
/* 1153 */       if (typeA < typeB) {
/* 1154 */         return typeA;
/*      */       }
/* 1156 */       return typeB;
/*      */     }
/*      */
/*      */
/* 1160 */     if ((typeA == 36 && typeB == 39) || (typeB == 36 && typeA == 39)) {
/* 1161 */       return 50;
/*      */     }
/*      */
/*      */
/* 1165 */     if ((typeA == 36 && typeB == 40) || (typeB == 36 && typeA == 40)) {
/* 1166 */       return 54;
/*      */     }
/*      */
/*      */
/* 1170 */     if (typeA > 20 && typeB > 20 && typeA != typeB) {
/* 1171 */       return this.rand.nextInt(5) + 1;
/*      */     }
/*      */
/*      */
/* 1175 */     int chanceInt = this.rand.nextInt(4) + 1;
/* 1176 */     if (!flag) {
/* 1177 */       if (chanceInt == 1)
/*      */       {
/* 1179 */         return typeA; }
/* 1180 */       if (chanceInt == 2)
/*      */       {
/* 1182 */         return typeB;
/*      */       }
/*      */     }
/*      */
/* 1186 */     if ((typeA == 1 && typeB == 2) || (typeA == 2 && typeB == 1)) {
/* 1187 */       return 6;
/*      */     }
/*      */
/* 1190 */     if ((typeA == 1 && typeB == 3) || (typeA == 3 && typeB == 1)) {
/* 1191 */       return 2;
/*      */     }
/*      */
/* 1194 */     if ((typeA == 1 && typeB == 4) || (typeA == 4 && typeB == 1)) {
/* 1195 */       return 7;
/*      */     }
/*      */
/* 1198 */     if ((typeA == 1 && typeB == 5) || (typeA == 5 && typeB == 1)) {
/* 1199 */       return 9;
/*      */     }
/*      */
/* 1202 */     if ((typeA == 1 && typeB == 7) || (typeA == 7 && typeB == 1)) {
/* 1203 */       return 12;
/*      */     }
/*      */
/* 1206 */     if ((typeA == 1 && typeB == 8) || (typeA == 8 && typeB == 1)) {
/* 1207 */       return 7;
/*      */     }
/*      */
/* 1210 */     if ((typeA == 1 && typeB == 9) || (typeA == 9 && typeB == 1)) {
/* 1211 */       return 13;
/*      */     }
/*      */
/* 1214 */     if ((typeA == 1 && typeB == 11) || (typeA == 11 && typeB == 1)) {
/* 1215 */       return 12;
/*      */     }
/*      */
/* 1218 */     if ((typeA == 1 && typeB == 12) || (typeA == 12 && typeB == 1)) {
/* 1219 */       return 13;
/*      */     }
/*      */
/* 1222 */     if ((typeA == 1 && typeB == 17) || (typeA == 17 && typeB == 1)) {
/* 1223 */       return 16;
/*      */     }
/*      */
/* 1226 */     if ((typeA == 2 && typeB == 4) || (typeA == 4 && typeB == 2)) {
/* 1227 */       return 3;
/*      */     }
/*      */
/* 1230 */     if ((typeA == 2 && typeB == 5) || (typeA == 5 && typeB == 2)) {
/* 1231 */       return 4;
/*      */     }
/*      */
/* 1234 */     if ((typeA == 2 && typeB == 7) || (typeA == 7 && typeB == 2)) {
/* 1235 */       return 8;
/*      */     }
/*      */
/* 1238 */     if ((typeA == 2 && typeB == 8) || (typeA == 8 && typeB == 2)) {
/* 1239 */       return 3;
/*      */     }
/*      */
/* 1242 */     if ((typeA == 2 && typeB == 12) || (typeA == 12 && typeB == 2)) {
/* 1243 */       return 6;
/*      */     }
/*      */
/* 1246 */     if ((typeA == 2 && typeB == 16) || (typeA == 16 && typeB == 2)) {
/* 1247 */       return 13;
/*      */     }
/*      */
/* 1250 */     if ((typeA == 2 && typeB == 17) || (typeA == 17 && typeB == 2)) {
/* 1251 */       return 12;
/*      */     }
/*      */
/* 1254 */     if ((typeA == 3 && typeB == 4) || (typeA == 4 && typeB == 3)) {
/* 1255 */       return 8;
/*      */     }
/*      */
/* 1258 */     if ((typeA == 3 && typeB == 5) || (typeA == 5 && typeB == 3)) {
/* 1259 */       return 8;
/*      */     }
/*      */
/* 1262 */     if ((typeA == 3 && typeB == 6) || (typeA == 6 && typeB == 3)) {
/* 1263 */       return 2;
/*      */     }
/*      */
/* 1266 */     if ((typeA == 3 && typeB == 7) || (typeA == 7 && typeB == 3)) {
/* 1267 */       return 11;
/*      */     }
/*      */
/* 1270 */     if ((typeA == 3 && typeB == 9) || (typeA == 9 && typeB == 3)) {
/* 1271 */       return 8;
/*      */     }
/*      */
/* 1274 */     if ((typeA == 3 && typeB == 12) || (typeA == 12 && typeB == 3)) {
/* 1275 */       return 11;
/*      */     }
/*      */
/* 1278 */     if ((typeA == 3 && typeB == 16) || (typeA == 16 && typeB == 3)) {
/* 1279 */       return 11;
/*      */     }
/*      */
/* 1282 */     if ((typeA == 3 && typeB == 17) || (typeA == 17 && typeB == 3)) {
/* 1283 */       return 11;
/*      */     }
/*      */
/* 1286 */     if ((typeA == 4 && typeB == 6) || (typeA == 6 && typeB == 4)) {
/* 1287 */       return 3;
/*      */     }
/*      */
/* 1290 */     if ((typeA == 4 && typeB == 7) || (typeA == 7 && typeB == 4)) {
/* 1291 */       return 8;
/*      */     }
/*      */
/* 1294 */     if ((typeA == 4 && typeB == 9) || (typeA == 9 && typeB == 4)) {
/* 1295 */       return 7;
/*      */     }
/*      */
/* 1298 */     if ((typeA == 4 && typeB == 11) || (typeA == 11 && typeB == 4)) {
/* 1299 */       return 7;
/*      */     }
/*      */
/* 1302 */     if ((typeA == 4 && typeB == 12) || (typeA == 12 && typeB == 4)) {
/* 1303 */       return 7;
/*      */     }
/*      */
/* 1306 */     if ((typeA == 4 && typeB == 13) || (typeA == 13 && typeB == 4)) {
/* 1307 */       return 7;
/*      */     }
/*      */
/* 1310 */     if ((typeA == 4 && typeB == 16) || (typeA == 16 && typeB == 4)) {
/* 1311 */       return 13;
/*      */     }
/*      */
/* 1314 */     if ((typeA == 4 && typeB == 17) || (typeA == 17 && typeB == 4)) {
/* 1315 */       return 5;
/*      */     }
/*      */
/* 1318 */     if ((typeA == 5 && typeB == 6) || (typeA == 6 && typeB == 5)) {
/* 1319 */       return 4;
/*      */     }
/*      */
/* 1322 */     if ((typeA == 5 && typeB == 7) || (typeA == 7 && typeB == 5)) {
/* 1323 */       return 4;
/*      */     }
/*      */
/* 1326 */     if ((typeA == 5 && typeB == 8) || (typeA == 8 && typeB == 5)) {
/* 1327 */       return 4;
/*      */     }
/*      */
/* 1330 */     if ((typeA == 5 && typeB == 11) || (typeA == 11 && typeB == 5)) {
/* 1331 */       return 17;
/*      */     }
/*      */
/* 1334 */     if ((typeA == 5 && typeB == 12) || (typeA == 12 && typeB == 5)) {
/* 1335 */       return 13;
/*      */     }
/*      */
/* 1338 */     if ((typeA == 5 && typeB == 13) || (typeA == 13 && typeB == 5)) {
/* 1339 */       return 16;
/*      */     }
/*      */
/* 1342 */     if ((typeA == 5 && typeB == 16) || (typeA == 16 && typeB == 5)) {
/* 1343 */       return 17;
/*      */     }
/*      */
/* 1346 */     if ((typeA == 6 && typeB == 8) || (typeA == 8 && typeB == 6)) {
/* 1347 */       return 2;
/*      */     }
/*      */
/* 1350 */     if ((typeA == 6 && typeB == 17) || (typeA == 17 && typeB == 6)) {
/* 1351 */       return 7;
/*      */     }
/*      */
/* 1354 */     if ((typeA == 7 && typeB == 16) || (typeA == 16 && typeB == 7)) {
/* 1355 */       return 13;
/*      */     }
/*      */
/* 1358 */     if ((typeA == 8 && typeB == 11) || (typeA == 11 && typeB == 8)) {
/* 1359 */       return 7;
/*      */     }
/*      */
/* 1362 */     if ((typeA == 8 && typeB == 12) || (typeA == 12 && typeB == 8)) {
/* 1363 */       return 7;
/*      */     }
/*      */
/* 1366 */     if ((typeA == 8 && typeB == 13) || (typeA == 13 && typeB == 8)) {
/* 1367 */       return 7;
/*      */     }
/*      */
/* 1370 */     if ((typeA == 8 && typeB == 16) || (typeA == 16 && typeB == 8)) {
/* 1371 */       return 7;
/*      */     }
/*      */
/* 1374 */     if ((typeA == 8 && typeB == 17) || (typeA == 17 && typeB == 8)) {
/* 1375 */       return 7;
/*      */     }
/*      */
/* 1378 */     if ((typeA == 9 && typeB == 16) || (typeA == 16 && typeB == 9)) {
/* 1379 */       return 13;
/*      */     }
/*      */
/* 1382 */     if ((typeA == 11 && typeB == 16) || (typeA == 16 && typeB == 11)) {
/* 1383 */       return 13;
/*      */     }
/*      */
/* 1386 */     if ((typeA == 11 && typeB == 17) || (typeA == 17 && typeB == 11)) {
/* 1387 */       return 7;
/*      */     }
/*      */
/* 1390 */     if ((typeA == 12 && typeB == 16) || (typeA == 16 && typeB == 12)) {
/* 1391 */       return 13;
/*      */     }
/*      */
/* 1394 */     if ((typeA == 13 && typeB == 17) || (typeA == 17 && typeB == 13)) {
/* 1395 */       return 9;
/*      */     }
/*      */
/* 1398 */     return typeA;
/*      */   }
/*      */
/*      */
/*      */
/*      */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 1404 */     Boolean tameResult = processTameInteract(player, hand);
/* 1405 */     if (tameResult != null) {
/* 1406 */       return tameResult.booleanValue();
/*      */     }
/* 1408 */     if (getType() == 60 && !getIsTamed() && isZebraRunning()) {
/* 1409 */       return false;
/*      */     }
/*      */
/* 1412 */     ItemStack stack = player.getHeldItem(hand);
/* 1413 */     if (!stack.isEmpty() && !getIsRideable() && stack.getItem() == Items.SADDLE) {
/* 1414 */       stack.shrink(1);
/* 1415 */       if (stack.isEmpty()) {
/* 1416 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/* 1418 */       setRideable(true);
/* 1419 */       return true;
/*      */     }
/*      */
/* 1422 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == Items.IRON_HORSE_ARMOR && isArmored()) {
/* 1423 */       if (getArmorType() == 0) {
/* 1424 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON);
/*      */       }
/* 1426 */       dropArmor();
/* 1427 */       setArmorType(1);
/* 1428 */       stack.shrink(1);
/* 1429 */       if (stack.isEmpty()) {
/* 1430 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*      */
/* 1433 */       return true;
/*      */     }
/*      */
/* 1436 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == Items.GOLDEN_HORSE_ARMOR && isArmored()) {
/* 1437 */       if (getArmorType() == 0) {
/* 1438 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON);
/*      */       }
/* 1440 */       dropArmor();
/* 1441 */       setArmorType(2);
/* 1442 */       stack.shrink(1);
/* 1443 */       if (stack.isEmpty()) {
/* 1444 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/* 1446 */       return true;
/*      */     }
/*      */
/* 1449 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == Items.DIAMOND_HORSE_ARMOR && isArmored()) {
/* 1450 */       if (getArmorType() == 0) {
/* 1451 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON);
/*      */       }
/* 1453 */       dropArmor();
/* 1454 */       setArmorType(3);
/* 1455 */       stack.shrink(1);
/* 1456 */       if (stack.isEmpty()) {
/* 1457 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/* 1459 */       return true;
/*      */     }
/*      */
/* 1462 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.horsearmorcrystal && isMagicHorse()) {
/* 1463 */       if (getArmorType() == 0) {
/* 1464 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON);
/*      */       }
/* 1466 */       dropArmor();
/* 1467 */       setArmorType(4);
/* 1468 */       stack.shrink(1);
/* 1469 */       if (stack.isEmpty()) {
/* 1470 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/* 1472 */       return true;
/*      */     }
/*      */
/*      */
/* 1476 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.essenceundead) {
/* 1477 */       stack.shrink(1);
/* 1478 */       if (stack.isEmpty()) {
/* 1479 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*      */       } else {
/* 1481 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*      */       }
/*      */
/* 1484 */       if (isUndead() || getIsGhost()) {
/* 1485 */         setHealth(getMaxHealth());
/*      */       }
/*      */
/*      */
/*      */
/* 1490 */       if (getType() == 39 || getType() == 32 || getType() == 40) {
/*      */
/*      */
/* 1493 */         transform(25);
/*      */       }
/* 1495 */       else if (getType() == 36 || (getType() > 47 && getType() < 60)) {
/*      */
/*      */
/*      */
/* 1499 */         transform(24);
/* 1500 */       } else if (getType() < 21 || getType() == 60 || getType() == 61) {
/*      */
/*      */
/*      */
/* 1504 */         transform(23);
/*      */       }
/*      */
/* 1507 */       drinkingHorse();
/* 1508 */       return true;
/*      */     }
/*      */
/*      */
/* 1512 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.essencefire) {
/* 1513 */       stack.shrink(1);
/* 1514 */       if (stack.isEmpty()) {
/* 1515 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*      */       } else {
/* 1517 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*      */       }
/*      */
/* 1520 */       if (isNightmare()) {
/* 1521 */         if (getIsAdult() && getHealth() == getMaxHealth()) {
/* 1522 */           this.eatenpumpkin = true;
/*      */         }
/* 1524 */         setHealth(getMaxHealth());
/*      */       }
/*      */
/* 1527 */       if (getType() == 61)
/*      */       {
/* 1529 */         transform(38);
/*      */       }
/* 1531 */       drinkingHorse();
/* 1532 */       return true;
/*      */     }
/*      */
/*      */
/* 1536 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.essencedarkness) {
/* 1537 */       stack.shrink(1);
/* 1538 */       if (stack.isEmpty()) {
/* 1539 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*      */       } else {
/* 1541 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*      */       }
/*      */
/* 1544 */       if (getType() == 32) {
/* 1545 */         if (getIsAdult() && getHealth() == getMaxHealth()) {
/* 1546 */           this.eatenpumpkin = true;
/*      */         }
/* 1548 */         setHealth(getMaxHealth());
/*      */       }
/*      */
/* 1551 */       if (getType() == 61) {
/* 1552 */         transform(32);
/*      */       }
/*      */
/* 1555 */       if (getType() == 39)
/*      */       {
/*      */
/* 1558 */         transform(40);
/*      */       }
/* 1560 */       drinkingHorse();
/* 1561 */       return true;
/*      */     }
/*      */
/* 1564 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.essencelight) {
/* 1565 */       stack.shrink(1);
/* 1566 */       if (stack.isEmpty()) {
/* 1567 */         player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
/*      */       } else {
/* 1569 */         player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
/*      */       }
/*      */
/* 1572 */       if (isMagicHorse()) {
/* 1573 */         if (getIsAdult() && getHealth() == getMaxHealth()) {
/* 1574 */           this.eatenpumpkin = true;
/*      */         }
/* 1576 */         setHealth(getMaxHealth());
/*      */       }
/*      */
/* 1579 */       if (isNightmare())
/*      */       {
/* 1581 */         transform(36);
/*      */       }
/* 1583 */       if (getType() == 32 && this.posY > 128.0D)
/*      */       {
/*      */
/* 1586 */         transform(39);
/*      */       }
/*      */
/* 1589 */       if (isUndead() && getIsAdult() && !this.world.isRemote) {
/* 1590 */         setEdad(10);
/* 1591 */         if (getType() >= 26) {
/* 1592 */           setType(getType() - 3);
/*      */         }
/*      */       }
/* 1595 */       drinkingHorse();
/* 1596 */       return true;
/*      */     }
/*      */
/* 1599 */     if (!stack.isEmpty() && isAmuletHorse() && getIsTamed()) {
/* 1600 */       if ((getType() == 26 || getType() == 27 || getType() == 28) && stack.getItem() == MoCItems.amuletbone) {
/* 1601 */         player.setHeldItem(hand, ItemStack.EMPTY);
/* 1602 */         vanishHorse();
/* 1603 */         return true;
/*      */       }
/*      */
/* 1606 */       if (getType() > 47 && getType() < 60 && stack.getItem() == MoCItems.amuletfairy) {
/* 1607 */         player.setHeldItem(hand, ItemStack.EMPTY);
/* 1608 */         vanishHorse();
/* 1609 */         return true;
/*      */       }
/*      */
/* 1612 */       if ((getType() == 39 || getType() == 40) && stack.getItem() == MoCItems.amuletpegasus) {
/* 1613 */         player.setHeldItem(hand, ItemStack.EMPTY);
/* 1614 */         vanishHorse();
/* 1615 */         return true;
/*      */       }
/*      */
/* 1618 */       if ((getType() == 21 || getType() == 22) && stack.getItem() == MoCItems.amuletghost) {
/* 1619 */         player.setHeldItem(hand, ItemStack.EMPTY);
/* 1620 */         vanishHorse();
/* 1621 */         return true;
/*      */       }
/*      */     }
/*      */
/*      */
/* 1626 */     if (!stack.isEmpty() && stack.getItem() == Items.DYE && getType() == 50) {
/*      */
/* 1628 */       int colorInt = stack.getItemDamage();
/* 1629 */       switch (colorInt) {
/*      */         case 14:
/* 1631 */           transform(59);
/*      */           break;
/*      */
/*      */
/*      */
/*      */         case 12:
/* 1637 */           transform(51);
/*      */           break;
/*      */         case 11:
/* 1640 */           transform(48);
/*      */           break;
/*      */         case 10:
/* 1643 */           transform(53);
/*      */           break;
/*      */         case 9:
/* 1646 */           transform(52);
/*      */           break;
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */         case 6:
/* 1655 */           transform(57);
/*      */           break;
/*      */         case 5:
/* 1658 */           transform(49);
/*      */           break;
/*      */         case 4:
/* 1661 */           transform(56);
/*      */           break;
/*      */
/*      */
/*      */
/*      */         case 2:
/* 1667 */           transform(58);
/*      */           break;
/*      */         case 1:
/* 1670 */           transform(55);
/*      */           break;
/*      */         case 0:
/* 1673 */           transform(54);
/*      */           break;
/*      */       }
/*      */
/*      */
/* 1678 */       stack.shrink(1);
/* 1679 */       if (stack.isEmpty()) {
/* 1680 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/* 1682 */       eatingHorse();
/* 1683 */       return true;
/*      */     }
/*      */
/*      */
/* 1687 */     if (!stack.isEmpty() &&
/* 1688 */       getType() == 60 && (stack
/* 1689 */       .getItem() == Items.RECORD_11 || stack.getItem() == Items.RECORD_13 || stack.getItem() == Items.RECORD_CAT || stack
/* 1690 */       .getItem() == Items.RECORD_CHIRP || stack.getItem() == Items.RECORD_FAR || stack
/* 1691 */       .getItem() == Items.RECORD_MALL || stack.getItem() == Items.RECORD_MELLOHI || stack
/* 1692 */       .getItem() == Items.RECORD_STAL || stack.getItem() == Items.RECORD_STRAD || stack.getItem() == Items.RECORD_WARD)) {
/* 1693 */       player.setHeldItem(hand, ItemStack.EMPTY);
/* 1694 */       if (!this.world.isRemote) {
/* 1695 */         EntityItem entityitem1 = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack((Item)MoCItems.recordshuffle, 1));
/* 1696 */         entityitem1.setPickupDelay(20);
/* 1697 */         this.world.spawnEntity((Entity)entityitem1);
/*      */       }
/* 1699 */       eatingHorse();
/* 1700 */       return true;
/*      */     }
/* 1702 */     if (!stack.isEmpty() && stack.getItem() == Items.WHEAT && !isMagicHorse() && !isUndead()) {
/* 1703 */       stack.shrink(1);
/* 1704 */       if (stack.isEmpty()) {
/* 1705 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/* 1707 */       if (!this.world.isRemote) {
/* 1708 */         setTemper(getTemper() + 25);
/* 1709 */         if (getTemper() > getMaxTemper()) {
/* 1710 */           setTemper(getMaxTemper() - 5);
/*      */         }
/*      */       }
/* 1713 */       if (getHealth() + 5.0F > getMaxHealth()) {
/* 1714 */         setHealth(getMaxHealth());
/*      */       }
/* 1716 */       eatingHorse();
/* 1717 */       if (!getIsAdult() && getEdad() < getMaxEdad()) {
/* 1718 */         setEdad(getEdad() + 1);
/*      */       }
/* 1720 */       return true;
/*      */     }
/*      */
/* 1723 */     if (!stack.isEmpty() && stack.getItem() == MoCItems.sugarlump && !isMagicHorse() && !isUndead()) {
/* 1724 */       stack.shrink(1);
/* 1725 */       if (stack.isEmpty()) {
/* 1726 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/* 1728 */       if (!this.world.isRemote) {
/* 1729 */         setTemper(getTemper() + 25);
/* 1730 */         if (getTemper() > getMaxTemper()) {
/* 1731 */           setTemper(getMaxTemper() - 5);
/*      */         }
/*      */       }
/* 1734 */       if (getHealth() + 10.0F > getMaxHealth()) {
/* 1735 */         setHealth(getMaxHealth());
/*      */       }
/* 1737 */       eatingHorse();
/* 1738 */       if (!getIsAdult() && getEdad() < getMaxEdad()) {
/* 1739 */         setEdad(getEdad() + 2);
/*      */       }
/* 1741 */       return true;
/*      */     }
/*      */
/* 1744 */     if (!stack.isEmpty() && stack.getItem() == Items.BREAD && !isMagicHorse() && !isUndead()) {
/* 1745 */       stack.shrink(1);
/* 1746 */       if (stack.isEmpty()) {
/* 1747 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/* 1749 */       if (!this.world.isRemote) {
/* 1750 */         setTemper(getTemper() + 100);
/* 1751 */         if (getTemper() > getMaxTemper()) {
/* 1752 */           setTemper(getMaxTemper() - 5);
/*      */         }
/*      */       }
/* 1755 */       if (getHealth() + 20.0F > getMaxHealth()) {
/* 1756 */         setHealth(getMaxHealth());
/*      */       }
/* 1758 */       eatingHorse();
/* 1759 */       if (!getIsAdult() && getEdad() < getMaxEdad()) {
/* 1760 */         setEdad(getEdad() + 3);
/*      */       }
/* 1762 */       return true;
/*      */     }
/*      */
/* 1765 */     if (!stack.isEmpty() && (stack.getItem() == Items.APPLE || stack.getItem() == Items.GOLDEN_APPLE) && !isMagicHorse() &&
/* 1766 */       !isUndead()) {
/* 1767 */       stack.shrink(1);
/* 1768 */       if (stack.isEmpty()) {
/* 1769 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*      */
/* 1772 */       if (!this.world.isRemote) {
/* 1773 */         MoCTools.tameWithName(player, (IMoCTameable)this);
/*      */       }
/*      */
/* 1776 */       setHealth(getMaxHealth());
/* 1777 */       eatingHorse();
/* 1778 */       if (!getIsAdult() && getEdad() < getMaxEdad() && !this.world.isRemote) {
/* 1779 */         setEdad(getEdad() + 1);
/*      */       }
/*      */
/* 1782 */       return true;
/*      */     }
/*      */
/* 1785 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == Item.getItemFromBlock((Block)Blocks.CHEST) && isBagger()) {
/* 1786 */       if (getIsChested()) {
/* 1787 */         return false;
/*      */       }
/* 1789 */       stack.shrink(1);
/* 1790 */       if (stack.isEmpty()) {
/* 1791 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*      */
/*      */
/* 1795 */       setIsChested(true);
/* 1796 */       MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 1797 */       return true;
/*      */     }
/* 1799 */     if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.haystack) {
/* 1800 */       stack.shrink(1);
/* 1801 */       if (stack.isEmpty()) {
/* 1802 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*      */
/* 1805 */       setSitting(true);
/* 1806 */       eatingHorse();
/* 1807 */       if (!isMagicHorse() && !isUndead()) {
/* 1808 */         setHealth(getMaxHealth());
/*      */       }
/*      */
/* 1811 */       return true;
/*      */     }
/* 1813 */     if (getIsChested() && player.isSneaking()) {
/*      */
/* 1815 */       if (this.localchest == null) {
/* 1816 */         this.localchest = new MoCAnimalChest("HorseChest", getInventorySize());
/*      */       }
/*      */
/* 1819 */       if (!this.world.isRemote) {
/* 1820 */         player.displayGUIChest((IInventory)this.localchest);
/*      */       }
/* 1822 */       return true;
/*      */     }
/*      */
/*      */
/* 1826 */     if (!stack.isEmpty() && (stack
/* 1827 */       .getItem() == Item.getItemFromBlock(Blocks.PUMPKIN) || stack.getItem() == Items.MUSHROOM_STEW || stack
/* 1828 */       .getItem() == Items.CAKE || stack.getItem() == Items.GOLDEN_CARROT)) {
/* 1829 */       if (!getIsAdult() || isMagicHorse() || isUndead()) {
/* 1830 */         return false;
/*      */       }
/* 1832 */       stack.shrink(1);
/* 1833 */       if (stack.getItem() == Items.MUSHROOM_STEW) {
/* 1834 */         if (stack.isEmpty()) {
/* 1835 */           player.setHeldItem(hand, new ItemStack(Items.BOWL));
/*      */         } else {
/* 1837 */           player.inventory.addItemStackToInventory(new ItemStack(Items.BOWL));
/*      */         }
/* 1839 */       } else if (stack.isEmpty()) {
/* 1840 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/* 1842 */       this.eatenpumpkin = true;
/* 1843 */       setHealth(getMaxHealth());
/* 1844 */       eatingHorse();
/* 1845 */       return true;
/*      */     }
/*      */
/* 1848 */     if (!stack.isEmpty() && stack.getItem() == MoCItems.whip && getIsTamed() && !isBeingRidden()) {
/* 1849 */       setSitting(!getIsSitting());
/* 1850 */       return true;
/*      */     }
/*      */
/* 1853 */     if (getIsRideable() && getIsAdult() && !isBeingRidden()) {
/* 1854 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/* 1855 */         player.rotationYaw = this.rotationYaw;
/* 1856 */         player.rotationPitch = this.rotationPitch;
/* 1857 */         setSitting(false);
/* 1858 */         this.gestationtime = 0;
/*      */       }
/*      */
/* 1861 */       return true;
/*      */     }
/*      */
/* 1864 */     return super.processInteract(player, hand);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public boolean isAmuletHorse() {
/* 1872 */     return ((getType() >= 48 && getType() < 60) || getType() == 40 || getType() == 39 || getType() == 21 ||
/* 1873 */       getType() == 22 || getType() == 26 || getType() == 27 || getType() == 28);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */   public boolean isArmored() {
/* 1880 */     return (getType() < 21);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public boolean isBagger() {
/* 1889 */     return (getType() == 66 ||
/* 1890 */       getType() == 65 ||
/* 1891 */       getType() == 67 ||
/* 1892 */       getType() == 39 ||
/* 1893 */       getType() == 40 ||
/* 1894 */       getType() == 25 ||
/* 1895 */       getType() == 28 || (
/* 1896 */       getType() >= 45 && getType() < 60));
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public boolean isFloater() {
/* 1904 */     return (getType() == 36 ||
/* 1905 */       getType() == 27 ||
/* 1906 */       getType() == 24 ||
/* 1907 */       getType() == 22);
/*      */   }
/*      */
/*      */
/*      */
/*      */   public boolean isFlyer() {
/* 1913 */     return (getType() == 39 ||
/* 1914 */       getType() == 40 || (
/* 1915 */       getType() >= 45 && getType() < 60) ||
/* 1916 */       getType() == 32 ||
/* 1917 */       getType() == 21 ||
/* 1918 */       getType() == 25 ||
/* 1919 */       getType() == 28);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public boolean getIsGhost() {
/* 1930 */     return (getType() == 21 || getType() == 22);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */   public boolean isMagicHorse() {
/* 1937 */     return (
/*      */
/* 1939 */       getType() == 39 || getType() == 36 || getType() == 32 || getType() == 40 || (getType() >= 45 && getType() < 60) ||
/* 1940 */       getType() == 21 || getType() == 22);
/*      */   }
/*      */
/*      */
/*      */   public boolean isMovementCeased() {
/* 1945 */     return (getIsSitting() || isBeingRidden() || this.standCounter != 0 || this.shuffleCounter != 0 || getVanishC() != 0);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public boolean isNightmare() {
/* 1953 */     return (getType() == 38);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public boolean isPureBreed() {
/* 1962 */     return (getType() > 10 && getType() < 21);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public boolean isUndead() {
/* 1972 */     return (getType() == 23 || getType() == 24 || getType() == 25 || getType() == 26 ||
/* 1973 */       getType() == 27 ||
/* 1974 */       getType() == 28);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public boolean isUnicorned() {
/* 1984 */     return (getType() == 36 || (getType() >= 45 && getType() < 60) || getType() == 27 || getType() == 24);
/*      */   }
/*      */
/*      */   public boolean isZebraRunning() {
/* 1988 */     boolean flag = false;
/* 1989 */     EntityPlayer ep1 = this.world.getClosestPlayerToEntity((Entity)this, 8.0D);
/* 1990 */     if (ep1 != null) {
/* 1991 */       flag = true;
/* 1992 */       if (ep1.getRidingEntity() != null && ep1.getRidingEntity() instanceof MoCEntityHorse) {
/* 1993 */         MoCEntityHorse playerHorse = (MoCEntityHorse)ep1.getRidingEntity();
/* 1994 */         if (playerHorse.getType() == 16 || playerHorse.getType() == 17 || playerHorse.getType() == 60 || playerHorse.getType() == 61) {
/* 1995 */           flag = false;
/*      */         }
/*      */       }
/*      */     }
/*      */
/* 2000 */     if (flag) {
/* 2001 */       MoCTools.runLikeHell((EntityLiving)this, (Entity)ep1);
/*      */     }
/* 2003 */     return flag;
/*      */   }
/*      */
/*      */   public void LavaFX() {
/* 2007 */     MoCreatures.proxy.LavaFX((Entity)this);
/*      */   }
/*      */
/*      */   public void MaterializeFX() {
/* 2011 */     MoCreatures.proxy.MaterializeFX(this);
/*      */   }
/*      */
/*      */   private void moveTail() {
/* 2015 */     this.tailCounter = 1;
/*      */   }
/*      */
/*      */
/*      */   public int nameYOffset() {
/* 2020 */     if (getIsAdult()) {
/* 2021 */       return -80;
/*      */     }
/* 2023 */     return -5 - getEdad();
/*      */   }
/*      */
/*      */
/*      */
/*      */   private boolean nearMusicBox() {
/* 2029 */     if (this.world.isRemote) {
/* 2030 */       return false;
/*      */     }
/*      */
/* 2033 */     boolean flag = false;
/* 2034 */     BlockJukebox.TileEntityJukebox jukebox = MoCTools.nearJukeBoxRecord((Entity)this, Double.valueOf(6.0D));
/* 2035 */     if (jukebox != null && jukebox.getRecord() != null) {
/* 2036 */       Item record = jukebox.getRecord().getItem();
/* 2037 */       MoCItemRecord moCItemRecord = MoCItems.recordshuffle;
/* 2038 */       if (record == moCItemRecord) {
/* 2039 */         flag = true;
/* 2040 */         if (this.shuffleCounter > 1000) {
/* 2041 */           this.shuffleCounter = 0;
/* 2042 */           MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 102), new NetworkRegistry.TargetPoint(this.world.provider
/* 2043 */                 .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/* 2044 */           flag = false;
/*      */         }
/*      */       }
/*      */     }
/* 2048 */     return flag;
/*      */   }
/*      */
/*      */
/*      */   public void NightmareEffect() {
/* 2053 */     if (!MoCTools.mobGriefing(this.world)) {
/* 2054 */       setNightmareInt(getNightmareInt() - 1);
/*      */       return;
/*      */     }
/* 2057 */     int i = MathHelper.floor(this.posX);
/* 2058 */     int j = MathHelper.floor((getEntityBoundingBox()).minY);
/* 2059 */     int k = MathHelper.floor(this.posZ);
/* 2060 */     BlockPos pos = new BlockPos(i, j, k);
/* 2061 */     IBlockState blockstate = this.world.getBlockState(pos.add(-1, 0, -1));
/*      */
/* 2063 */     BlockEvent.BreakEvent event = null;
/* 2064 */     if (!this.world.isRemote) {
/*      */
/*      */       try {
/* 2067 */         event = new BlockEvent.BreakEvent(this.world, pos, blockstate, (EntityPlayer)FakePlayerFactory.get(
/* 2068 */               DimensionManager.getWorld(this.world.provider.getDimensionType().getId()), MoCreatures.MOCFAKEPLAYER));
/* 2069 */       } catch (Throwable throwable) {}
/*      */     }
/*      */
/*      */
/* 2073 */     if (event != null && !event.isCanceled()) {
/* 2074 */       this.world.setBlockState(pos, Blocks.FIRE.getDefaultState(), 3);
/* 2075 */       EntityPlayer entityplayer = (EntityPlayer)getRidingEntity();
/* 2076 */       if (entityplayer != null && entityplayer.isBurning()) {
/* 2077 */         entityplayer.extinguish();
/*      */       }
/* 2079 */       setNightmareInt(getNightmareInt() - 1);
/*      */     }
/*      */   }
/*      */
/*      */
/*      */   public void onDeath(DamageSource damagesource) {
/* 2085 */     super.onDeath(damagesource);
/* 2086 */     if (!this.world.isRemote) {
/* 2087 */       if ((this.rand.nextInt(10) == 0 && getType() == 23) || getType() == 24 || getType() == 25) {
/* 2088 */         MoCTools.spawnMaggots(this.world, (Entity)this);
/*      */       }
/*      */
/* 2091 */       if (getIsTamed() && (isMagicHorse() || isPureBreed()) && !getIsGhost() && this.rand.nextInt(4) == 0) {
/* 2092 */         MoCEntityHorse entityhorse1 = new MoCEntityHorse(this.world);
/* 2093 */         entityhorse1.setPosition(this.posX, this.posY, this.posZ);
/* 2094 */         this.world.spawnEntity((Entity)entityhorse1);
/* 2095 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR);
/*      */
/* 2097 */         entityhorse1.setOwnerId(getOwnerId());
/* 2098 */         entityhorse1.setTamed(true);
/* 2099 */         EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 2100 */         if (entityplayer != null) {
/* 2101 */           MoCTools.tameWithName(entityplayer, (IMoCTameable)entityhorse1);
/*      */         }
/*      */
/* 2104 */         entityhorse1.setAdult(false);
/* 2105 */         entityhorse1.setEdad(1);
/* 2106 */         int l = 22;
/* 2107 */         if (isFlyer()) {
/* 2108 */           l = 21;
/*      */         }
/* 2110 */         entityhorse1.setType(l);
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public void onLivingUpdate() {
/* 2121 */     if ((isFlyer() || isFloater()) &&
/* 2122 */       !this.onGround && this.motionY < 0.0D) {
/* 2123 */       this.motionY *= 0.6D;
/*      */     }
/*      */
/*      */
/* 2127 */     if (this.rand.nextInt(200) == 0) {
/* 2128 */       moveTail();
/*      */     }
/*      */
/* 2131 */     if (getType() == 38 && this.rand.nextInt(50) == 0 && this.world.isRemote) {
/* 2132 */       LavaFX();
/*      */     }
/*      */
/* 2135 */     if (getType() == 36 && isOnAir() && this.world.isRemote) {
/* 2136 */       StarFX();
/*      */     }
/*      */
/* 2139 */     if (!this.world.isRemote && isFlyer() && isOnAir()) {
/* 2140 */       float myFlyingSpeed = MoCTools.getMyMovementSpeed((Entity)this);
/* 2141 */       int wingFlapFreq = (int)(25.0F - myFlyingSpeed * 10.0F);
/* 2142 */       if (!isBeingRidden() || wingFlapFreq < 5) {
/* 2143 */         wingFlapFreq = 5;
/*      */       }
/* 2145 */       if (this.rand.nextInt(wingFlapFreq) == 0) {
/* 2146 */         wingFlap();
/*      */       }
/*      */     }
/*      */
/* 2150 */     if (isFlyer()) {
/* 2151 */       if (this.wingFlapCounter > 0 && ++this.wingFlapCounter > 20) {
/* 2152 */         this.wingFlapCounter = 0;
/*      */       }
/* 2154 */       if (this.wingFlapCounter != 0 && this.wingFlapCounter % 5 == 0 && this.world.isRemote) {
/* 2155 */         StarFX();
/*      */       }
/* 2157 */       if (this.wingFlapCounter == 5 && !this.world.isRemote) {
/* 2158 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_WINGFLAP);
/*      */       }
/*      */     }
/*      */
/* 2162 */     if (isUndead() && getType() < 26 && getIsAdult() && this.rand.nextInt(20) == 0) {
/* 2163 */       if (!this.world.isRemote) {
/* 2164 */         if (this.rand.nextInt(16) == 0) {
/* 2165 */           setEdad(getEdad() + 1);
/*      */         }
/* 2167 */         if (getEdad() >= 399) {
/* 2168 */           setType(getType() + 3);
/*      */         }
/*      */       } else {
/* 2171 */         UndeadFX();
/*      */       }
/*      */     }
/*      */
/*      */
/* 2176 */     super.onLivingUpdate();
/*      */
/* 2178 */     if (!this.world.isRemote) {
/*      */
/*      */
/*      */
/* 2182 */       if (getType() == 60 && getIsTamed() && this.rand.nextInt(50) == 0 && nearMusicBox() && this.shuffleCounter == 0) {
/* 2183 */         this.shuffleCounter = 1;
/* 2184 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 101), new NetworkRegistry.TargetPoint(this.world.provider
/* 2185 */               .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*      */       }
/*      */
/* 2188 */       if (this.rand.nextInt(300) == 0 && this.deathTime == 0) {
/* 2189 */         setHealth(getHealth() + 1.0F);
/* 2190 */         if (getHealth() > getMaxHealth()) {
/* 2191 */           setHealth(getMaxHealth());
/*      */         }
/*      */       }
/*      */
/* 2195 */       if (!getIsSitting() && !getIsTamed() && this.rand.nextInt(300) == 0) {
/* 2196 */         setSitting(true);
/*      */       }
/*      */
/* 2199 */       if (getIsSitting() && ++this.countEating > 50 && !getIsTamed()) {
/* 2200 */         this.countEating = 0;
/* 2201 */         setSitting(false);
/*      */       }
/*      */
/* 2204 */       if (getType() == 38 && isBeingRidden() && getNightmareInt() > 0 && this.rand.nextInt(2) == 0) {
/* 2205 */         NightmareEffect();
/*      */       }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/* 2234 */       if (this.sprintCounter > 0 && this.sprintCounter < 150 && isUnicorned() && isBeingRidden()) {
/* 2235 */         MoCTools.buckleMobs((EntityLiving)this, Double.valueOf(2.0D), this.world);
/*      */       }
/*      */
/* 2238 */       if (isFlyer() && !getIsTamed() && this.rand.nextInt(100) == 0 && !isMovementCeased() && !getIsSitting()) {
/* 2239 */         wingFlap();
/*      */       }
/*      */
/* 2242 */       if (!ReadyforParenting(this)) {
/*      */         return;
/*      */       }
/*      */
/* 2246 */       int i = 0;
/*      */
/* 2248 */       List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(8.0D, 3.0D, 8.0D));
/* 2249 */       for (int j = 0; j < list.size(); j++) {
/* 2250 */         Entity entity = list.get(j);
/* 2251 */         if (entity instanceof MoCEntityHorse || entity instanceof EntityHorse) {
/* 2252 */           i++;
/*      */         }
/*      */       }
/*      */
/* 2256 */       if (i > 1) {
/*      */         return;
/*      */       }
/* 2259 */       List<Entity> list1 = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
/* 2260 */       for (int k = 0; k < list1.size(); k++) {
/* 2261 */         Entity horsemate = list1.get(k);
/* 2262 */         boolean flag = horsemate instanceof EntityHorse;
/* 2263 */         if ((horsemate instanceof MoCEntityHorse || flag) && horsemate != this) {
/*      */
/*      */
/*      */
/* 2267 */           if (!flag &&
/* 2268 */             !ReadyforParenting((MoCEntityHorse)horsemate)) {
/*      */             return;
/*      */           }
/*      */
/*      */
/* 2273 */           if (this.rand.nextInt(100) == 0) {
/* 2274 */             this.gestationtime++;
/*      */           }
/*      */
/* 2277 */           if (this.gestationtime % 3 == 0) {
/* 2278 */             MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHeart(getEntityId()), new NetworkRegistry.TargetPoint(this.world.provider
/* 2279 */                   .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*      */           }
/*      */
/* 2282 */           if (this.gestationtime > 50) {
/*      */             int horsemateType;
/*      */
/* 2285 */             MoCEntityHorse baby = new MoCEntityHorse(this.world);
/* 2286 */             baby.setPosition(this.posX, this.posY, this.posZ);
/* 2287 */             this.world.spawnEntity((Entity)baby);
/* 2288 */             MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 2289 */             this.eatenpumpkin = false;
/* 2290 */             this.gestationtime = 0;
/*      */
/*      */
/*      */
/* 2294 */             if (flag) {
/* 2295 */               horsemateType = TranslateVanillaHorseType((AbstractHorse)horsemate);
/* 2296 */               if (horsemateType == -1) {
/*      */                 return;
/*      */               }
/*      */             } else {
/* 2300 */               horsemateType = ((MoCEntityHorse)horsemate).getType();
/* 2301 */               ((MoCEntityHorse)horsemate).eatenpumpkin = false;
/* 2302 */               ((MoCEntityHorse)horsemate).gestationtime = 0;
/*      */             }
/* 2304 */             int l = HorseGenetics(getType(), horsemateType);
/*      */
/* 2306 */             if (l == 50 || l == 54) {
/*      */
/* 2308 */               MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR);
/* 2309 */               if (!flag) {
/* 2310 */                 ((MoCEntityHorse)horsemate).dissapearHorse();
/*      */               }
/* 2312 */               dissapearHorse();
/*      */             }
/* 2314 */             baby.setOwnerId(getOwnerId());
/* 2315 */             baby.setTamed(true);
/*      */
/* 2317 */             baby.setAdult(false);
/* 2318 */             EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(getOwnerId());
/* 2319 */             if (entityplayer != null) {
/* 2320 */               MoCTools.tameWithName(entityplayer, (IMoCTameable)baby);
/*      */             }
/* 2322 */             baby.setType(l);
/*      */             break;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   private int TranslateVanillaHorseType(AbstractHorse horse) {
/* 2336 */     if (horse instanceof net.minecraft.entity.passive.EntityDonkey) {
/* 2337 */       return 65;
/*      */     }
/* 2339 */     if (horse instanceof EntityHorse) {
/* 2340 */       switch ((byte)((EntityHorse)horse).getHorseVariant()) {
/*      */         case 0:
/* 2342 */           return 1;
/*      */         case 1:
/* 2344 */           return 2;
/*      */         case 3:
/* 2346 */           return 3;
/*      */         case 4:
/* 2348 */           return 5;
/*      */         case 5:
/* 2350 */           return 9;
/*      */         case 6:
/* 2352 */           return 4;
/*      */       }
/* 2354 */       return 3;
/*      */     }
/*      */
/*      */
/* 2358 */     return -1;
/*      */   }
/*      */
/*      */
/*      */   public void onUpdate() {
/* 2363 */     super.onUpdate();
/*      */
/* 2365 */     if (this.shuffleCounter > 0) {
/* 2366 */       this.shuffleCounter++;
/* 2367 */       if (this.world.isRemote && this.shuffleCounter % 20 == 0) {
/* 2368 */         double var2 = this.rand.nextGaussian() * 0.5D;
/* 2369 */         double var4 = this.rand.nextGaussian() * -0.1D;
/* 2370 */         double var6 = this.rand.nextGaussian() * 0.02D;
/* 2371 */         this.world.spawnParticle(EnumParticleTypes.NOTE, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand
/* 2372 */             .nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var2, var4, var6, new int[0]);
/*      */       }
/*      */
/*      */
/* 2376 */       if (!this.world.isRemote && !nearMusicBox()) {
/* 2377 */         this.shuffleCounter = 0;
/* 2378 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 102), new NetworkRegistry.TargetPoint(this.world.provider
/* 2379 */               .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*      */       }
/*      */     }
/*      */
/* 2383 */     if (this.mouthCounter > 0 && ++this.mouthCounter > 30) {
/* 2384 */       this.mouthCounter = 0;
/*      */     }
/*      */
/* 2387 */     if (this.standCounter > 0 && ++this.standCounter > 20) {
/* 2388 */       this.standCounter = 0;
/*      */     }
/*      */
/* 2391 */     if (this.tailCounter > 0 && ++this.tailCounter > 8) {
/* 2392 */       this.tailCounter = 0;
/*      */     }
/*      */
/* 2395 */     if (getVanishC() > 0) {
/*      */
/* 2397 */       setVanishC((byte)(getVanishC() + 1));
/*      */
/* 2399 */       if (getVanishC() < 15 && this.world.isRemote) {
/* 2400 */         VanishFX();
/*      */       }
/*      */
/*      */
/* 2404 */       if (getVanishC() > 100) {
/* 2405 */         setVanishC((byte)101);
/* 2406 */         MoCTools.dropHorseAmulet(this);
/* 2407 */         dissapearHorse();
/*      */       }
/*      */
/* 2410 */       if (getVanishC() == 1) {
/* 2411 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_VANISH);
/*      */       }
/*      */
/* 2414 */       if (getVanishC() == 70) {
/* 2415 */         stand();
/*      */       }
/*      */     }
/*      */
/* 2419 */     if (this.sprintCounter > 0) {
/* 2420 */       this.sprintCounter++;
/* 2421 */       if (this.sprintCounter < 150 && this.sprintCounter % 2 == 0 && this.world.isRemote) {
/* 2422 */         StarFX();
/*      */       }
/*      */
/* 2425 */       if (this.sprintCounter > 300) {
/* 2426 */         this.sprintCounter = 0;
/*      */       }
/*      */     }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/* 2441 */     if (this.transformCounter > 0) {
/* 2442 */       if (this.transformCounter == 40) {
/* 2443 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_TRANSFORM);
/*      */       }
/*      */
/* 2446 */       if (++this.transformCounter > 100) {
/* 2447 */         this.transformCounter = 0;
/* 2448 */         if (this.transformType != 0) {
/* 2449 */           dropArmor();
/* 2450 */           setType(this.transformType);
/*      */         }
/*      */       }
/*      */     }
/*      */
/* 2455 */     if (getIsGhost() && getEdad() < 10 && this.rand.nextInt(7) == 0) {
/* 2456 */       setEdad(getEdad() + 1);
/*      */     }
/*      */
/* 2459 */     if (getIsGhost() && getEdad() == 9) {
/* 2460 */       setEdad(100);
/* 2461 */       setAdult(true);
/*      */     }
/*      */   }
/*      */
/*      */   private void openMouth() {
/* 2466 */     this.mouthCounter = 1;
/*      */   }
/*      */
/*      */   public boolean ReadyforParenting(MoCEntityHorse entityhorse) {
/* 2470 */     int i = entityhorse.getType();
/* 2471 */     return (!entityhorse.isBeingRidden() && entityhorse.getRidingEntity() == null && entityhorse.getIsTamed() && entityhorse.eatenpumpkin && entityhorse
/* 2472 */       .getIsAdult() && !entityhorse.isUndead() && !entityhorse.getIsGhost() && i != 61 && i < 66);
/*      */   }
/*      */
/*      */
/*      */   public boolean rideableEntity() {
/* 2477 */     return true;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public void selectType() {
/* 2516 */     checkSpawningBiome();
/* 2517 */     if (getType() == 0) {
/* 2518 */       if (this.rand.nextInt(5) == 0) {
/* 2519 */         setAdult(false);
/*      */       }
/* 2521 */       int j = this.rand.nextInt(100);
/* 2522 */       int i = MoCreatures.proxy.zebraChance;
/* 2523 */       if (j <= 33 - i) {
/* 2524 */         setType(6);
/* 2525 */       } else if (j <= 66 - i) {
/* 2526 */         setType(7);
/* 2527 */       } else if (j <= 99 - i) {
/* 2528 */         setType(8);
/*      */       } else {
/* 2530 */         setType(60);
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */   public void setNightmareInt(int i) {
/* 2536 */     this.nightmareInt = i;
/*      */   }
/*      */
/*      */   public void setReproduced(boolean var1) {
/* 2540 */     this.hasReproduced = var1;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public void setVanishC(byte i) {
/* 2549 */     this.vanishCounter = i;
/*      */   }
/*      */
/*      */   private void stand() {
/* 2553 */     if (!isBeingRidden() && !isOnAir()) {
/* 2554 */       this.standCounter = 1;
/*      */     }
/*      */   }
/*      */
/*      */   public void StarFX() {
/* 2559 */     MoCreatures.proxy.StarFX(this);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public float tFloat() {
/* 2568 */     if (++this.fCounter > 60) {
/* 2569 */       this.fCounter = 0;
/* 2570 */       this.transFloat = this.rand.nextFloat() * 0.3F + 0.3F;
/*      */     }
/*      */
/* 2573 */     if (getIsGhost() && getEdad() < 10) {
/* 2574 */       this.transFloat = 0.0F;
/*      */     }
/* 2576 */     return this.transFloat;
/*      */   }
/*      */
/*      */   public void transform(int tType) {
/* 2580 */     if (!this.world.isRemote) {
/* 2581 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), tType), new NetworkRegistry.TargetPoint(this.world.provider
/* 2582 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*      */     }
/*      */
/* 2585 */     this.transformType = tType;
/* 2586 */     if (!isBeingRidden() && this.transformType != 0) {
/* 2587 */       dropArmor();
/* 2588 */       this.transformCounter = 1;
/*      */     }
/*      */   }
/*      */
/*      */   public void UndeadFX() {
/* 2593 */     MoCreatures.proxy.UndeadFX((Entity)this);
/*      */   }
/*      */
/*      */   public void VanishFX() {
/* 2597 */     MoCreatures.proxy.VanishFX(this);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public void vanishHorse() {
/* 2605 */     getNavigator().clearPath();
/* 2606 */     this.motionX = 0.0D;
/* 2607 */     this.motionZ = 0.0D;
/*      */
/* 2609 */     if (isBagger()) {
/* 2610 */       MoCTools.dropInventory((Entity)this, this.localchest);
/* 2611 */       dropBags();
/*      */     }
/* 2613 */     if (!this.world.isRemote) {
/* 2614 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageVanish(getEntityId()), new NetworkRegistry.TargetPoint(this.world.provider
/* 2615 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/* 2616 */       setVanishC((byte)1);
/*      */     }
/* 2618 */     MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_VANISH);
/*      */   }
/*      */
/*      */
/*      */   public void dropMyStuff() {
/* 2623 */     dropArmor();
/* 2624 */     MoCTools.dropSaddle((MoCEntityAnimal)this, this.world);
/* 2625 */     if (isBagger()) {
/* 2626 */       MoCTools.dropInventory((Entity)this, this.localchest);
/* 2627 */       dropBags();
/*      */     }
/*      */   }
/*      */
/*      */   public void wingFlap() {
/* 2632 */     if (isFlyer() && this.wingFlapCounter == 0) {
/* 2633 */       this.wingFlapCounter = 1;
/* 2634 */       if (!this.world.isRemote) {
/* 2635 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 3), new NetworkRegistry.TargetPoint(this.world.provider
/* 2636 */               .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */
/*      */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 2643 */     super.writeEntityToNBT(nbttagcompound);
/* 2644 */     nbttagcompound.setBoolean("Saddle", getIsRideable());
/* 2645 */     nbttagcompound.setBoolean("EatingHaystack", getIsSitting());
/* 2646 */     nbttagcompound.setBoolean("ChestedHorse", getIsChested());
/* 2647 */     nbttagcompound.setBoolean("HasReproduced", getHasReproduced());
/* 2648 */     nbttagcompound.setBoolean("Bred", getHasBred());
/* 2649 */     nbttagcompound.setInteger("ArmorType", getArmorType());
/*      */
/* 2651 */     if (getIsChested() && this.localchest != null) {
/* 2652 */       NBTTagList nbttaglist = new NBTTagList();
/* 2653 */       for (int i = 0; i < this.localchest.getSizeInventory(); i++) {
/*      */
/* 2655 */         this.localstack = this.localchest.getStackInSlot(i);
/* 2656 */         if (this.localstack != null && !this.localstack.isEmpty()) {
/* 2657 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 2658 */           nbttagcompound1.setByte("Slot", (byte)i);
/* 2659 */           this.localstack.writeToNBT(nbttagcompound1);
/* 2660 */           nbttaglist.appendTag((NBTBase)nbttagcompound1);
/*      */         }
/*      */       }
/* 2663 */       nbttagcompound.setTag("Items", (NBTBase)nbttaglist);
/*      */     }
/*      */   }
/*      */
/*      */
/*      */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 2669 */     super.readEntityFromNBT(nbttagcompound);
/* 2670 */     setRideable(nbttagcompound.getBoolean("Saddle"));
/* 2671 */     setSitting(nbttagcompound.getBoolean("EatingHaystack"));
/* 2672 */     setBred(nbttagcompound.getBoolean("Bred"));
/* 2673 */     setIsChested(nbttagcompound.getBoolean("ChestedHorse"));
/* 2674 */     setReproduced(nbttagcompound.getBoolean("HasReproduced"));
/* 2675 */     setArmorType((byte)nbttagcompound.getInteger("ArmorType"));
/* 2676 */     if (getIsChested()) {
/* 2677 */       NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
/* 2678 */       this.localchest = new MoCAnimalChest("HorseChest", getInventorySize());
/* 2679 */       for (int i = 0; i < nbttaglist.tagCount(); i++) {
/* 2680 */         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
/* 2681 */         int j = nbttagcompound1.getByte("Slot") & 0xFF;
/* 2682 */         if (j >= 0 && j < this.localchest.getSizeInventory()) {
/* 2683 */           this.localchest.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */
/*      */
/*      */   public void performAnimation(int animationType) {
/* 2692 */     if (animationType >= 23 && animationType < 60) {
/*      */
/* 2694 */       this.transformType = animationType;
/* 2695 */       this.transformCounter = 1;
/*      */     }
/* 2697 */     if (animationType == 3)
/*      */     {
/* 2699 */       this.wingFlapCounter = 1;
/*      */     }
/* 2701 */     if (animationType == 101)
/*      */     {
/* 2703 */       this.shuffleCounter = 1;
/*      */     }
/* 2705 */     if (animationType == 102)
/*      */     {
/* 2707 */       this.shuffleCounter = 0;
/*      */     }
/*      */   }
/*      */
/*      */
/*      */   public EnumCreatureAttribute getCreatureAttribute() {
/* 2713 */     if (isUndead()) {
/* 2714 */       return EnumCreatureAttribute.UNDEAD;
/*      */     }
/* 2716 */     return super.getCreatureAttribute();
/*      */   }
/*      */
/*      */
/*      */   protected boolean canBeTrappedInNet() {
/* 2721 */     return (getIsTamed() && !isAmuletHorse());
/*      */   }
/*      */
/*      */
/*      */   public int getMaxSpawnedInChunk() {
/* 2726 */     return 4;
/*      */   }
/*      */
/*      */
/*      */   public void setType(int i) {
/* 2731 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
/* 2732 */     setHealth(getMaxHealth());
/* 2733 */     if (getType() == 38 || getType() == 40) {
/* 2734 */       this.isImmuneToFire = true;
/*      */     }
/* 2736 */     super.setType(i);
/*      */   }
/*      */
/*      */
/*      */   public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
/* 2741 */     if (getType() == 38 || getType() == 40) {
/* 2742 */       this.isImmuneToFire = true;
/*      */     }
/* 2744 */     return super.onInitialSpawn(difficulty, livingdata);
/*      */   }
/*      */
/*      */
/*      */   public void updatePassenger(Entity passenger) {
/* 2749 */     double dist = getSizeFactor() * 0.25D;
/* 2750 */     double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
/* 2751 */     double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
/* 2752 */     passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
/*      */   }
/*      */
/*      */
/*      */   public void makeEntityJump() {
/* 2757 */     wingFlap();
/* 2758 */     super.makeEntityJump();
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
