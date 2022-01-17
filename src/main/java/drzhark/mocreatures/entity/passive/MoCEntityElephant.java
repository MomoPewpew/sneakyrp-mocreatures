/*      */ package drzhark.mocreatures.entity.passive;
/*      */ 
/*      */ import drzhark.mocreatures.MoCEntityData;
/*      */ import drzhark.mocreatures.MoCTools;
/*      */ import drzhark.mocreatures.MoCreatures;
/*      */ import drzhark.mocreatures.entity.IMoCTameable;
/*      */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*      */ import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
/*      */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*      */ import drzhark.mocreatures.init.MoCItems;
/*      */ import drzhark.mocreatures.init.MoCSoundEvents;
/*      */ import drzhark.mocreatures.inventory.MoCAnimalChest;
/*      */ import drzhark.mocreatures.network.MoCMessageHandler;
/*      */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*      */ import java.util.List;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.SoundType;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*      */ import net.minecraft.entity.ai.EntityAIBase;
/*      */ import net.minecraft.entity.ai.EntityAISwimming;
/*      */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.inventory.InventoryLargeChest;
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
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.SoundEvent;
/*      */ import net.minecraft.util.math.BlockPos;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.world.ILockableContainer;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.biome.Biome;
/*      */ import net.minecraftforge.common.BiomeDictionary;
/*      */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*      */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*      */ 
/*      */ public class MoCEntityElephant
/*      */   extends MoCEntityTameableAnimal {
/*      */   public int sprintCounter;
/*      */   public int sitCounter;
/*      */   public MoCAnimalChest localelephantchest;
/*      */   public MoCAnimalChest localelephantchest2;
/*      */   public MoCAnimalChest localelephantchest3;
/*      */   public MoCAnimalChest localelephantchest4;
/*   64 */   private static final DataParameter<Integer> TUSK_TYPE = EntityDataManager.createKey(MoCEntityElephant.class, DataSerializers.VARINT); public ItemStack localstack; boolean hasPlatform; public int tailCounter; public int trunkCounter; public int earCounter; private byte tuskUses; private byte temper;
/*   65 */   private static final DataParameter<Integer> STORAGE_TYPE = EntityDataManager.createKey(MoCEntityElephant.class, DataSerializers.VARINT);
/*   66 */   private static final DataParameter<Integer> HARNESS_TYPE = EntityDataManager.createKey(MoCEntityElephant.class, DataSerializers.VARINT);
/*      */ 
/*      */   
/*      */   public MoCEntityElephant(World world) {
/*   70 */     super(world);
/*   71 */     setAdult(true);
/*   72 */     setTamed(false);
/*   73 */     setEdad(50);
/*   74 */     setSize(1.1F, 3.0F);
/*   75 */     this.stepHeight = 1.0F;
/*      */     
/*   77 */     if (!this.world.isRemote) {
/*   78 */       if (this.rand.nextInt(4) == 0) {
/*   79 */         setAdult(false);
/*      */       } else {
/*   81 */         setAdult(true);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initEntityAI() {
/*   88 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*   89 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
/*   90 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*   91 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*   92 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyEntityAttributes() {
/*   97 */     super.applyEntityAttributes();
/*   98 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
/*   99 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
/*  100 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  101 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   public void selectType() {
/*  106 */     checkSpawningBiome();
/*  107 */     if (getType() == 0) {
/*  108 */       setType(this.rand.nextInt(2) + 1);
/*      */     }
/*  110 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
/*  111 */     setHealth(getMaxHealth());
/*      */   }
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*  116 */     super.entityInit();
/*  117 */     this.dataManager.register(TUSK_TYPE, Integer.valueOf(0));
/*  118 */     this.dataManager.register(STORAGE_TYPE, Integer.valueOf(0));
/*  119 */     this.dataManager.register(HARNESS_TYPE, Integer.valueOf(0));
/*      */   }
/*      */   
/*      */   public int getTusks() {
/*  123 */     return ((Integer)this.dataManager.get(TUSK_TYPE)).intValue();
/*      */   }
/*      */   
/*      */   public void setTusks(int i) {
/*  127 */     this.dataManager.set(TUSK_TYPE, Integer.valueOf(i));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getArmorType() {
/*  132 */     return ((Integer)this.dataManager.get(HARNESS_TYPE)).intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setArmorType(int i) {
/*  137 */     this.dataManager.set(HARNESS_TYPE, Integer.valueOf(i));
/*      */   }
/*      */   
/*      */   public int getStorage() {
/*  141 */     return ((Integer)this.dataManager.get(STORAGE_TYPE)).intValue();
/*      */   }
/*      */   
/*      */   public void setStorage(int i) {
/*  145 */     this.dataManager.set(STORAGE_TYPE, Integer.valueOf(i));
/*      */   }
/*      */ 
/*      */   
/*      */   public ResourceLocation getTexture() {
/*  150 */     switch (getType()) {
/*      */       case 1:
/*  152 */         return MoCreatures.proxy.getTexture("elephantafrican.png");
/*      */       case 2:
/*  154 */         return MoCreatures.proxy.getTexture("elephantindian.png");
/*      */       case 3:
/*  156 */         return MoCreatures.proxy.getTexture("mammoth.png");
/*      */       case 4:
/*  158 */         return MoCreatures.proxy.getTexture("mammothsonghua.png");
/*      */       case 5:
/*  160 */         return MoCreatures.proxy.getTexture("elephantindianpretty.png");
/*      */     } 
/*  162 */     return MoCreatures.proxy.getTexture("elephantafrican.png");
/*      */   }
/*      */ 
/*      */   
/*      */   public float calculateMaxHealth() {
/*  167 */     switch (getType()) {
/*      */       case 1:
/*  169 */         return 40.0F;
/*      */       case 2:
/*  171 */         return 30.0F;
/*      */       case 3:
/*  173 */         return 50.0F;
/*      */       case 4:
/*  175 */         return 60.0F;
/*      */       case 5:
/*  177 */         return 40.0F;
/*      */     } 
/*      */     
/*  180 */     return 30.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double getCustomSpeed() {
/*  186 */     if (this.sitCounter != 0) {
/*  187 */       return 0.0D;
/*      */     }
/*  189 */     double tSpeed = 0.5D;
/*  190 */     if (getType() == 1) {
/*  191 */       tSpeed = 0.55D;
/*  192 */     } else if (getType() == 2) {
/*  193 */       tSpeed = 0.6D;
/*  194 */     } else if (getType() == 3) {
/*  195 */       tSpeed = 0.5D;
/*  196 */     } else if (getType() == 4) {
/*  197 */       tSpeed = 0.55D;
/*  198 */     } else if (getType() == 5) {
/*  199 */       tSpeed = 0.5D;
/*      */     } 
/*      */     
/*  202 */     if (this.sprintCounter > 0 && this.sprintCounter < 150) {
/*  203 */       tSpeed *= 1.5D;
/*      */     }
/*  205 */     if (this.sprintCounter > 150) {
/*  206 */       tSpeed *= 0.5D;
/*      */     }
/*      */     
/*  209 */     return tSpeed;
/*      */   }
/*      */ 
/*      */   
/*      */   public void onLivingUpdate() {
/*  214 */     super.onLivingUpdate();
/*  215 */     if (!this.world.isRemote) {
/*  216 */       if (this.sprintCounter > 0 && this.sprintCounter < 150 && isBeingRidden() && this.rand.nextInt(15) == 0) {
/*  217 */         MoCTools.buckleMobsNotPlayers((EntityLiving)this, Double.valueOf(3.0D), this.world);
/*      */       }
/*      */       
/*  220 */       if (this.sprintCounter > 0 && ++this.sprintCounter > 300) {
/*  221 */         this.sprintCounter = 0;
/*      */       }
/*      */       
/*  224 */       if (getIsTamed() && !isBeingRidden() && getArmorType() >= 1 && this.rand.nextInt(20) == 0) {
/*  225 */         EntityPlayer ep = this.world.getClosestPlayerToEntity((Entity)this, 3.0D);
/*  226 */         if (ep != null && (!MoCreatures.proxy.enableOwnership || ep.getUniqueID().equals(getOwnerId())) && ep.isSneaking()) {
/*  227 */           sit();
/*      */         }
/*      */       } 
/*      */       
/*  231 */       if (MoCreatures.proxy.elephantBulldozer && getIsTamed() && isBeingRidden() && getTusks() > 0) {
/*  232 */         int height = 2;
/*  233 */         if (getType() == 3) {
/*  234 */           height = 3;
/*      */         }
/*  236 */         if (getType() == 4) {
/*  237 */           height = 3;
/*      */         }
/*  239 */         int dmg = MoCTools.destroyBlocksInFront((Entity)this, 2.0D, getTusks(), height);
/*  240 */         checkTusks(dmg);
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  246 */       if (this.tailCounter > 0 && ++this.tailCounter > 8) {
/*  247 */         this.tailCounter = 0;
/*      */       }
/*      */       
/*  250 */       if (this.rand.nextInt(200) == 0) {
/*  251 */         this.tailCounter = 1;
/*      */       }
/*      */       
/*  254 */       if (this.trunkCounter > 0 && ++this.trunkCounter > 38) {
/*  255 */         this.trunkCounter = 0;
/*      */       }
/*      */       
/*  258 */       if (this.trunkCounter == 0 && this.rand.nextInt(200) == 0) {
/*  259 */         this.trunkCounter = this.rand.nextInt(10) + 1;
/*      */       }
/*      */       
/*  262 */       if (this.earCounter > 0 && ++this.earCounter > 30) {
/*  263 */         this.earCounter = 0;
/*      */       }
/*      */       
/*  266 */       if (this.rand.nextInt(200) == 0) {
/*  267 */         this.earCounter = this.rand.nextInt(20) + 1;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  272 */     if (this.sitCounter != 0 && 
/*  273 */       ++this.sitCounter > 100) {
/*  274 */       this.sitCounter = 0;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkTusks(int dmg) {
/*  286 */     this.tuskUses = (byte)(this.tuskUses + (byte)dmg);
/*  287 */     if ((getTusks() == 1 && this.tuskUses > 59) || (getTusks() == 2 && this.tuskUses > 250) || (
/*  288 */       getTusks() == 3 && this.tuskUses > 1000)) {
/*  289 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_TURTLE_HURT);
/*  290 */       setTusks(0);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void sit() {
/*  295 */     this.sitCounter = 1;
/*  296 */     if (!this.world.isRemote) {
/*  297 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
/*  298 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*      */     }
/*  300 */     getNavigator().clearPath();
/*      */   }
/*      */ 
/*      */   
/*      */   public void performAnimation(int animationType) {
/*  305 */     if (animationType == 0) {
/*      */       
/*  307 */       this.sitCounter = 1;
/*  308 */       getNavigator().clearPath();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/*  314 */     Boolean tameResult = processTameInteract(player, hand);
/*  315 */     if (tameResult != null) {
/*  316 */       return tameResult.booleanValue();
/*      */     }
/*      */     
/*  319 */     ItemStack stack = player.getHeldItem(hand);
/*  320 */     if (!stack.isEmpty() && !getIsTamed() && !getIsAdult() && stack.getItem() == Items.CAKE) {
/*  321 */       stack.shrink(1);
/*  322 */       if (stack.isEmpty()) {
/*  323 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  325 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/*  326 */       this.temper = (byte)(this.temper + 2);
/*  327 */       setHealth(getMaxHealth());
/*  328 */       if (!this.world.isRemote && !getIsAdult() && !getIsTamed() && this.temper >= 10) {
/*  329 */         MoCTools.tameWithName(player, (IMoCTameable)this);
/*      */       }
/*  331 */       return true;
/*      */     } 
/*      */     
/*  334 */     if (!stack.isEmpty() && !getIsTamed() && !getIsAdult() && stack.getItem() == MoCItems.sugarlump) {
/*  335 */       stack.shrink(1);
/*  336 */       if (stack.isEmpty()) {
/*  337 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  339 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/*  340 */       this.temper = (byte)(this.temper + 1);
/*  341 */       setHealth(getMaxHealth());
/*  342 */       if (!this.world.isRemote && !getIsAdult() && !getIsTamed() && this.temper >= 10) {
/*  343 */         setTamed(true);
/*  344 */         MoCTools.tameWithName(player, (IMoCTameable)this);
/*      */       } 
/*  346 */       return true;
/*      */     } 
/*      */     
/*  349 */     if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() == 0 && stack.getItem() == MoCItems.elephantHarness) {
/*  350 */       stack.shrink(1);
/*  351 */       if (stack.isEmpty()) {
/*  352 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  354 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
/*  355 */       setArmorType(1);
/*  356 */       return true;
/*      */     } 
/*      */     
/*  359 */     if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() >= 1 && getStorage() == 0 && stack
/*  360 */       .getItem() == MoCItems.elephantChest) {
/*  361 */       stack.shrink(1);
/*  362 */       if (stack.isEmpty()) {
/*  363 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  365 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
/*      */       
/*  367 */       setStorage(1);
/*  368 */       return true;
/*      */     } 
/*      */     
/*  371 */     if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() >= 1 && getStorage() == 1 && stack
/*  372 */       .getItem() == MoCItems.elephantChest) {
/*  373 */       stack.shrink(1);
/*  374 */       if (stack.isEmpty()) {
/*  375 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  377 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
/*  378 */       setStorage(2);
/*  379 */       return true;
/*      */     } 
/*      */     
/*  382 */     if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getType() == 3 && getArmorType() >= 1 && getStorage() == 2 && stack
/*  383 */       .getItem() == Item.getItemFromBlock((Block)Blocks.CHEST)) {
/*  384 */       stack.shrink(1);
/*  385 */       if (stack.isEmpty()) {
/*  386 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  388 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
/*  389 */       setStorage(3);
/*  390 */       return true;
/*      */     } 
/*      */     
/*  393 */     if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getType() == 3 && getArmorType() >= 1 && getStorage() == 3 && stack
/*  394 */       .getItem() == Item.getItemFromBlock((Block)Blocks.CHEST)) {
/*  395 */       stack.shrink(1);
/*  396 */       if (stack.isEmpty()) {
/*  397 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  399 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
/*  400 */       setStorage(4);
/*  401 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  405 */     if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() == 1 && getType() == 2 && stack
/*  406 */       .getItem() == MoCItems.elephantGarment) {
/*  407 */       stack.shrink(1);
/*  408 */       if (stack.isEmpty()) {
/*  409 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  411 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
/*  412 */       setArmorType(2);
/*  413 */       setType(5);
/*  414 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  418 */     if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() == 2 && getType() == 5 && stack
/*  419 */       .getItem() == MoCItems.elephantHowdah) {
/*  420 */       stack.shrink(1);
/*  421 */       if (stack.isEmpty()) {
/*  422 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  424 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
/*  425 */       setArmorType(3);
/*  426 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  430 */     if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() == 1 && getType() == 4 && stack
/*  431 */       .getItem() == MoCItems.mammothPlatform) {
/*  432 */       stack.shrink(1);
/*  433 */       if (stack.isEmpty()) {
/*  434 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  436 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
/*  437 */       setArmorType(3);
/*  438 */       return true;
/*      */     } 
/*      */     
/*  441 */     if (!stack.isEmpty() && getIsTamed() && getIsAdult() && stack.getItem() == MoCItems.tusksWood) {
/*  442 */       stack.shrink(1);
/*  443 */       if (stack.isEmpty()) {
/*  444 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  446 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
/*  447 */       dropTusks();
/*  448 */       this.tuskUses = (byte)stack.getItemDamage();
/*  449 */       setTusks(1);
/*  450 */       return true;
/*      */     } 
/*      */     
/*  453 */     if (!stack.isEmpty() && getIsTamed() && getIsAdult() && stack.getItem() == MoCItems.tusksIron) {
/*  454 */       stack.shrink(1);
/*  455 */       if (stack.isEmpty()) {
/*  456 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  458 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
/*  459 */       dropTusks();
/*  460 */       this.tuskUses = (byte)stack.getItemDamage();
/*  461 */       setTusks(2);
/*  462 */       return true;
/*      */     } 
/*      */     
/*  465 */     if (!stack.isEmpty() && getIsTamed() && getIsAdult() && stack.getItem() == MoCItems.tusksDiamond) {
/*  466 */       stack.shrink(1);
/*  467 */       if (stack.isEmpty()) {
/*  468 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*      */       }
/*  470 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
/*  471 */       dropTusks();
/*  472 */       this.tuskUses = (byte)stack.getItemDamage();
/*  473 */       setTusks(3);
/*  474 */       return true;
/*      */     } 
/*      */     
/*  477 */     if (player.isSneaking()) {
/*  478 */       initChest();
/*  479 */       if (getStorage() == 1) {
/*  480 */         if (!this.world.isRemote) {
/*  481 */           player.displayGUIChest((IInventory)this.localelephantchest);
/*      */         }
/*  483 */         return true;
/*      */       } 
/*  485 */       if (getStorage() == 2) {
/*  486 */         InventoryLargeChest doubleChest = new InventoryLargeChest("ElephantChest", (ILockableContainer)this.localelephantchest, (ILockableContainer)this.localelephantchest2);
/*  487 */         if (!this.world.isRemote) {
/*  488 */           player.displayGUIChest((IInventory)doubleChest);
/*      */         }
/*  490 */         return true;
/*      */       } 
/*  492 */       if (getStorage() == 3) {
/*  493 */         InventoryLargeChest doubleChest = new InventoryLargeChest("ElephantChest", (ILockableContainer)this.localelephantchest, (ILockableContainer)this.localelephantchest2);
/*  494 */         InventoryLargeChest tripleChest = new InventoryLargeChest("ElephantChest", (ILockableContainer)doubleChest, (ILockableContainer)this.localelephantchest3);
/*  495 */         if (!this.world.isRemote) {
/*  496 */           player.displayGUIChest((IInventory)tripleChest);
/*      */         }
/*  498 */         return true;
/*      */       } 
/*  500 */       if (getStorage() == 4) {
/*  501 */         InventoryLargeChest doubleChest = new InventoryLargeChest("ElephantChest", (ILockableContainer)this.localelephantchest, (ILockableContainer)this.localelephantchest2);
/*  502 */         InventoryLargeChest doubleChestb = new InventoryLargeChest("ElephantChest", (ILockableContainer)this.localelephantchest3, (ILockableContainer)this.localelephantchest4);
/*  503 */         InventoryLargeChest fourChest = new InventoryLargeChest("ElephantChest", (ILockableContainer)doubleChest, (ILockableContainer)doubleChestb);
/*  504 */         if (!this.world.isRemote) {
/*  505 */           player.displayGUIChest((IInventory)fourChest);
/*      */         }
/*  507 */         return true;
/*      */       } 
/*      */     } 
/*      */     
/*  511 */     if (!stack.isEmpty() && 
/*  512 */       getTusks() > 0 && (stack
/*  513 */       .getItem() == Items.DIAMOND_PICKAXE || stack.getItem() == Items.WOODEN_PICKAXE || stack
/*  514 */       .getItem() == Items.STONE_PICKAXE || stack.getItem() == Items.IRON_PICKAXE || stack.getItem() == Items.GOLDEN_PICKAXE)) {
/*  515 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
/*  516 */       dropTusks();
/*  517 */       return true;
/*      */     } 
/*      */     
/*  520 */     if (getIsTamed() && getIsAdult() && getArmorType() >= 1 && this.sitCounter != 0) {
/*  521 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/*  522 */         player.rotationYaw = this.rotationYaw;
/*  523 */         player.rotationPitch = this.rotationPitch;
/*  524 */         this.sitCounter = 0;
/*      */       } 
/*      */       
/*  527 */       return true;
/*      */     } 
/*      */     
/*  530 */     return super.processInteract(player, hand);
/*      */   }
/*      */   
/*      */   private void initChest() {
/*  534 */     if (getStorage() > 0 && this.localelephantchest == null) {
/*  535 */       this.localelephantchest = new MoCAnimalChest("ElephantChest", 18);
/*      */     }
/*      */     
/*  538 */     if (getStorage() > 1 && this.localelephantchest2 == null) {
/*  539 */       this.localelephantchest2 = new MoCAnimalChest("ElephantChest", 18);
/*      */     }
/*      */     
/*  542 */     if (getStorage() > 2 && this.localelephantchest3 == null) {
/*  543 */       this.localelephantchest3 = new MoCAnimalChest("ElephantChest", 9);
/*      */     }
/*      */     
/*  546 */     if (getStorage() > 3 && this.localelephantchest4 == null) {
/*  547 */       this.localelephantchest4 = new MoCAnimalChest("ElephantChest", 9);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void dropTusks() {
/*  555 */     if (this.world.isRemote) {
/*      */       return;
/*      */     }
/*  558 */     int i = getTusks();
/*      */     
/*  560 */     if (i == 1) {
/*  561 */       EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack((Item)MoCItems.tusksWood, 1, this.tuskUses));
/*      */       
/*  563 */       entityitem.setPickupDelay(10);
/*  564 */       this.world.spawnEntity((Entity)entityitem);
/*      */     } 
/*  566 */     if (i == 2) {
/*  567 */       EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack((Item)MoCItems.tusksIron, 1, this.tuskUses));
/*      */       
/*  569 */       entityitem.setPickupDelay(10);
/*  570 */       this.world.spawnEntity((Entity)entityitem);
/*      */     } 
/*  572 */     if (i == 3) {
/*  573 */       EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack((Item)MoCItems.tusksDiamond, 1, this.tuskUses));
/*      */       
/*  575 */       entityitem.setPickupDelay(10);
/*  576 */       this.world.spawnEntity((Entity)entityitem);
/*      */     } 
/*  578 */     setTusks(0);
/*  579 */     this.tuskUses = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean rideableEntity() {
/*  584 */     return true;
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
/*      */   public boolean checkSpawningBiome() {
/*  599 */     BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), this.posZ);
/*  600 */     Biome currentbiome = MoCTools.Biomekind(this.world, pos);
/*      */     
/*  602 */     if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
/*  603 */       setType(3 + this.rand.nextInt(2));
/*  604 */       return true;
/*      */     } 
/*  606 */     if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SANDY)) {
/*  607 */       setType(1);
/*  608 */       return true;
/*      */     } 
/*      */     
/*  611 */     if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.JUNGLE)) {
/*  612 */       setType(2);
/*  613 */       return true;
/*      */     } 
/*      */     
/*  616 */     if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.PLAINS) || BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.FOREST)) {
/*  617 */       setType(1 + this.rand.nextInt(2));
/*  618 */       return true;
/*      */     } 
/*      */     
/*  621 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getSizeFactor() {
/*  626 */     float sizeF = 1.25F;
/*      */     
/*  628 */     switch (getType()) {
/*      */       case 4:
/*  630 */         sizeF *= 1.2F;
/*      */         break;
/*      */       case 2:
/*      */       case 5:
/*  634 */         sizeF *= 0.8F;
/*      */         break;
/*      */     } 
/*      */     
/*  638 */     if (!getIsAdult()) {
/*  639 */       sizeF *= getEdad() * 0.01F;
/*      */     }
/*  641 */     return sizeF;
/*      */   }
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/*  646 */     super.readEntityFromNBT(nbttagcompound);
/*  647 */     setTusks(nbttagcompound.getInteger("Tusks"));
/*  648 */     setArmorType(nbttagcompound.getInteger("Harness"));
/*  649 */     setStorage(nbttagcompound.getInteger("Storage"));
/*  650 */     this.tuskUses = nbttagcompound.getByte("TuskUses");
/*  651 */     if (getStorage() > 0) {
/*  652 */       NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
/*  653 */       this.localelephantchest = new MoCAnimalChest("ElephantChest", 18);
/*  654 */       for (int i = 0; i < nbttaglist.tagCount(); i++) {
/*  655 */         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
/*  656 */         int j = nbttagcompound1.getByte("Slot") & 0xFF;
/*  657 */         if (j >= 0 && j < this.localelephantchest.getSizeInventory()) {
/*  658 */           this.localelephantchest.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
/*      */         }
/*      */       } 
/*      */     } 
/*  662 */     if (getStorage() >= 2) {
/*  663 */       NBTTagList nbttaglist = nbttagcompound.getTagList("Items2", 10);
/*  664 */       this.localelephantchest2 = new MoCAnimalChest("ElephantChest", 18);
/*  665 */       for (int i = 0; i < nbttaglist.tagCount(); i++) {
/*  666 */         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
/*  667 */         int j = nbttagcompound1.getByte("Slot") & 0xFF;
/*  668 */         if (j >= 0 && j < this.localelephantchest2.getSizeInventory()) {
/*  669 */           this.localelephantchest2.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  674 */     if (getStorage() >= 3) {
/*  675 */       NBTTagList nbttaglist = nbttagcompound.getTagList("Items3", 10);
/*  676 */       this.localelephantchest3 = new MoCAnimalChest("ElephantChest", 9);
/*  677 */       for (int i = 0; i < nbttaglist.tagCount(); i++) {
/*  678 */         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
/*  679 */         int j = nbttagcompound1.getByte("Slot") & 0xFF;
/*  680 */         if (j >= 0 && j < this.localelephantchest3.getSizeInventory()) {
/*  681 */           this.localelephantchest3.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
/*      */         }
/*      */       } 
/*      */     } 
/*  685 */     if (getStorage() >= 4) {
/*  686 */       NBTTagList nbttaglist = nbttagcompound.getTagList("Items4", 10);
/*  687 */       this.localelephantchest4 = new MoCAnimalChest("ElephantChest", 9);
/*  688 */       for (int i = 0; i < nbttaglist.tagCount(); i++) {
/*  689 */         NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
/*  690 */         int j = nbttagcompound1.getByte("Slot") & 0xFF;
/*  691 */         if (j >= 0 && j < this.localelephantchest4.getSizeInventory()) {
/*  692 */           this.localelephantchest4.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/*  700 */     super.writeEntityToNBT(nbttagcompound);
/*  701 */     nbttagcompound.setInteger("Tusks", getTusks());
/*  702 */     nbttagcompound.setInteger("Harness", getArmorType());
/*  703 */     nbttagcompound.setInteger("Storage", getStorage());
/*  704 */     nbttagcompound.setByte("TuskUses", this.tuskUses);
/*      */     
/*  706 */     if (getStorage() > 0 && this.localelephantchest != null) {
/*  707 */       NBTTagList nbttaglist = new NBTTagList();
/*  708 */       for (int i = 0; i < this.localelephantchest.getSizeInventory(); i++) {
/*  709 */         this.localstack = this.localelephantchest.getStackInSlot(i);
/*  710 */         if (this.localstack != null && !this.localstack.isEmpty()) {
/*  711 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  712 */           nbttagcompound1.setByte("Slot", (byte)i);
/*  713 */           this.localstack.writeToNBT(nbttagcompound1);
/*  714 */           nbttaglist.appendTag((NBTBase)nbttagcompound1);
/*      */         } 
/*      */       } 
/*  717 */       nbttagcompound.setTag("Items", (NBTBase)nbttaglist);
/*      */     } 
/*      */     
/*  720 */     if (getStorage() >= 2 && this.localelephantchest2 != null) {
/*  721 */       NBTTagList nbttaglist = new NBTTagList();
/*  722 */       for (int i = 0; i < this.localelephantchest2.getSizeInventory(); i++) {
/*  723 */         this.localstack = this.localelephantchest2.getStackInSlot(i);
/*  724 */         if (this.localstack != null && !this.localstack.isEmpty()) {
/*  725 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  726 */           nbttagcompound1.setByte("Slot", (byte)i);
/*  727 */           this.localstack.writeToNBT(nbttagcompound1);
/*  728 */           nbttaglist.appendTag((NBTBase)nbttagcompound1);
/*      */         } 
/*      */       } 
/*  731 */       nbttagcompound.setTag("Items2", (NBTBase)nbttaglist);
/*      */     } 
/*      */     
/*  734 */     if (getStorage() >= 3 && this.localelephantchest3 != null) {
/*  735 */       NBTTagList nbttaglist = new NBTTagList();
/*  736 */       for (int i = 0; i < this.localelephantchest3.getSizeInventory(); i++) {
/*  737 */         this.localstack = this.localelephantchest3.getStackInSlot(i);
/*  738 */         if (this.localstack != null && !this.localstack.isEmpty()) {
/*  739 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  740 */           nbttagcompound1.setByte("Slot", (byte)i);
/*  741 */           this.localstack.writeToNBT(nbttagcompound1);
/*  742 */           nbttaglist.appendTag((NBTBase)nbttagcompound1);
/*      */         } 
/*      */       } 
/*  745 */       nbttagcompound.setTag("Items3", (NBTBase)nbttaglist);
/*      */     } 
/*      */     
/*  748 */     if (getStorage() >= 4 && this.localelephantchest4 != null) {
/*  749 */       NBTTagList nbttaglist = new NBTTagList();
/*  750 */       for (int i = 0; i < this.localelephantchest4.getSizeInventory(); i++) {
/*  751 */         this.localstack = this.localelephantchest4.getStackInSlot(i);
/*  752 */         if (this.localstack != null && !this.localstack.isEmpty()) {
/*  753 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*  754 */           nbttagcompound1.setByte("Slot", (byte)i);
/*  755 */           this.localstack.writeToNBT(nbttagcompound1);
/*  756 */           nbttaglist.appendTag((NBTBase)nbttagcompound1);
/*      */         } 
/*      */       } 
/*  759 */       nbttagcompound.setTag("Items4", (NBTBase)nbttaglist);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isMyHealFood(ItemStack stack) {
/*  765 */     return (!stack.isEmpty() && (stack
/*  766 */       .getItem() == Items.BAKED_POTATO || stack.getItem() == Items.BREAD || stack.getItem() == MoCItems.haystack));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isMovementCeased() {
/*  771 */     return (isBeingRidden() || this.sitCounter != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void Riding() {
/*  776 */     if (isBeingRidden() && getRidingEntity() instanceof EntityPlayer) {
/*  777 */       EntityPlayer entityplayer = (EntityPlayer)getRidingEntity();
/*  778 */       List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(1.0D, 0.0D, 1.0D));
/*  779 */       if (list != null) {
/*  780 */         for (int i = 0; i < list.size(); i++) {
/*  781 */           Entity entity = list.get(i);
/*  782 */           if (!entity.isDead)
/*      */           {
/*      */             
/*  785 */             entity.onCollideWithPlayer(entityplayer);
/*      */           }
/*      */         } 
/*      */       }
/*  789 */       if (entityplayer.isSneaking() && 
/*  790 */         !this.world.isRemote) {
/*  791 */         if (this.sitCounter == 0) {
/*  792 */           sit();
/*      */         }
/*  794 */         if (this.sitCounter >= 50) {
/*  795 */           entityplayer.dismountRidingEntity();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBePushed() {
/*  805 */     return !isBeingRidden();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBeCollidedWith() {
/*  810 */     return !isBeingRidden();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void updatePassenger(Entity passenger) {
/*  816 */     double dist = 1.0D;
/*  817 */     switch (getType()) {
/*      */       
/*      */       case 1:
/*      */       case 3:
/*  821 */         dist = 0.8D;
/*      */         break;
/*      */       
/*      */       case 2:
/*      */       case 5:
/*  826 */         dist = 0.1D;
/*      */         break;
/*      */       case 4:
/*  829 */         dist = 1.2D;
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  834 */     double newPosX = this.posX - dist * Math.cos((MoCTools.realAngle(this.renderYawOffset - 90.0F) / 57.29578F));
/*  835 */     double newPosZ = this.posZ - dist * Math.sin((MoCTools.realAngle(this.renderYawOffset - 90.0F) / 57.29578F));
/*  836 */     passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public double getMountedYOffset() {
/*  841 */     double yOff = 0.0D;
/*  842 */     boolean sit = (this.sitCounter != 0);
/*      */     
/*  844 */     switch (getType()) {
/*      */       case 1:
/*  846 */         yOff = 0.55D;
/*  847 */         if (sit) {
/*  848 */           yOff = -0.05D;
/*      */         }
/*      */         break;
/*      */       case 3:
/*  852 */         yOff = 0.55D;
/*  853 */         if (sit) {
/*  854 */           yOff = -0.05D;
/*      */         }
/*      */         break;
/*      */       case 2:
/*      */       case 5:
/*  859 */         yOff = -0.17D;
/*  860 */         if (sit) {
/*  861 */           yOff = -0.5D;
/*      */         }
/*      */         break;
/*      */       case 4:
/*  865 */         yOff = 1.2D;
/*  866 */         if (sit) {
/*  867 */           yOff = 0.45D;
/*      */         }
/*      */         break;
/*      */     } 
/*  871 */     return yOff + this.height * 0.75D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityInsideOpaqueBlock() {
/*  879 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTalkInterval() {
/*  884 */     return 300;
/*      */   }
/*      */ 
/*      */   
/*      */   protected SoundEvent getDeathSound() {
/*  889 */     return MoCSoundEvents.ENTITY_ELEPHANT_DEATH;
/*      */   }
/*      */ 
/*      */   
/*      */   protected SoundEvent getHurtSound(DamageSource source) {
/*  894 */     return MoCSoundEvents.ENTITY_ELEPHANT_HURT;
/*      */   }
/*      */ 
/*      */   
/*      */   protected SoundEvent getAmbientSound() {
/*  899 */     if (!getIsAdult() && getEdad() < 80) {
/*  900 */       return MoCSoundEvents.ENTITY_ELEPHANT_AMBIENT_BABY;
/*      */     }
/*  902 */     return MoCSoundEvents.ENTITY_ELEPHANT_AMBIENT;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Item getDropItem() {
/*  907 */     return (Item)MoCItems.animalHide;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getCanSpawnHere() {
/*  912 */     return (((MoCEntityData)MoCreatures.entityMap.get(getClass())).getFrequency() > 0 && getCanSpawnHereCreature() && getCanSpawnHereLiving());
/*      */   }
/*      */ 
/*      */   
/*      */   public void dropMyStuff() {
/*  917 */     if (!this.world.isRemote) {
/*  918 */       dropTusks();
/*      */       
/*  920 */       if (getStorage() > 0) {
/*  921 */         if (getStorage() > 0) {
/*  922 */           MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.elephantChest, 1));
/*  923 */           if (this.localelephantchest != null) {
/*  924 */             MoCTools.dropInventory((Entity)this, this.localelephantchest);
/*      */           }
/*      */         } 
/*      */         
/*  928 */         if (getStorage() >= 2) {
/*  929 */           if (this.localelephantchest2 != null) {
/*  930 */             MoCTools.dropInventory((Entity)this, this.localelephantchest2);
/*      */           }
/*  932 */           MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.elephantChest, 1));
/*      */         } 
/*  934 */         if (getStorage() >= 3) {
/*  935 */           if (this.localelephantchest3 != null) {
/*  936 */             MoCTools.dropInventory((Entity)this, this.localelephantchest3);
/*      */           }
/*  938 */           MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Block)Blocks.CHEST, 1));
/*      */         } 
/*  940 */         if (getStorage() >= 4) {
/*  941 */           if (this.localelephantchest4 != null) {
/*  942 */             MoCTools.dropInventory((Entity)this, this.localelephantchest4);
/*      */           }
/*  944 */           MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Block)Blocks.CHEST, 1));
/*      */         } 
/*  946 */         setStorage(0);
/*      */       } 
/*  948 */       dropArmor();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void dropArmor() {
/*  954 */     if (this.world.isRemote) {
/*      */       return;
/*      */     }
/*  957 */     if (getArmorType() >= 1) {
/*  958 */       MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.elephantHarness, 1));
/*      */     }
/*  960 */     if (getType() == 5 && getArmorType() >= 2) {
/*      */       
/*  962 */       MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.elephantGarment, 1));
/*  963 */       if (getArmorType() == 3) {
/*  964 */         MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.elephantHowdah, 1));
/*      */       }
/*  966 */       setType(2);
/*      */     } 
/*  968 */     if (getType() == 4 && getArmorType() == 3) {
/*  969 */       MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.mammothPlatform, 1));
/*      */     }
/*  971 */     setArmorType(0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int nameYOffset() {
/*  977 */     int yOff = (int)((100 / getEdad()) * getSizeFactor() * -110.0F);
/*  978 */     if (getIsAdult()) {
/*  979 */       yOff = (int)(getSizeFactor() * -110.0F);
/*      */     }
/*  981 */     if (this.sitCounter != 0)
/*  982 */       yOff += 25; 
/*  983 */     return yOff;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/*  988 */     if (super.attackEntityFrom(damagesource, i)) {
/*  989 */       Entity entity = damagesource.getTrueSource();
/*  990 */       if ((entity != null && getIsTamed() && entity instanceof EntityPlayer) || !(entity instanceof EntityLivingBase)) {
/*  991 */         return false;
/*      */       }
/*  993 */       if (isRidingOrBeingRiddenBy(entity)) {
/*  994 */         return true;
/*      */       }
/*  996 */       if (entity != this && shouldAttackPlayers()) {
/*  997 */         setAttackTarget((EntityLivingBase)entity);
/*      */       }
/*  999 */       return true;
/*      */     } 
/* 1001 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void fall(float f, float f1) {
/* 1006 */     int i = (int)Math.ceil((f - 3.0F));
/* 1007 */     if (i > 0) {
/* 1008 */       i /= 2;
/* 1009 */       if (i > 0) {
/* 1010 */         attackEntityFrom(DamageSource.FALL, i);
/*      */       }
/* 1012 */       if (isBeingRidden() && i > 0) {
/* 1013 */         for (Entity entity : getRecursivePassengers())
/*      */         {
/* 1015 */           entity.attackEntityFrom(DamageSource.FALL, i);
/*      */         }
/*      */       }
/* 1018 */       IBlockState iblockstate = this.world.getBlockState(new BlockPos(this.posX, this.posY - 0.2D - this.prevRotationYaw, this.posZ));
/* 1019 */       Block block = iblockstate.getBlock();
/*      */       
/* 1021 */       if (iblockstate.getMaterial() != Material.AIR && !isSilent()) {
/*      */         
/* 1023 */         SoundType soundtype = block.getSoundType(iblockstate, this.world, new BlockPos(this.posX, this.posY - 0.2D - this.prevRotationYaw, this.posZ), (Entity)this);
/* 1024 */         this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, soundtype.getStepSound(), getSoundCategory(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNotScared() {
/* 1031 */     return (getIsAdult() || getEdad() > 80 || getIsTamed());
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityElephant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */