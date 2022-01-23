package drzhark.mocreatures.entity.passive;

import drzhark.mocreatures.MoCEntityData;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.inventory.MoCAnimalChest;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCEntityElephant
  extends MoCEntityTameableAnimal {
  public int sprintCounter;
  public int sitCounter;
  public MoCAnimalChest localelephantchest;
  public MoCAnimalChest localelephantchest2;
  public MoCAnimalChest localelephantchest3;
  public MoCAnimalChest localelephantchest4;
  private static final DataParameter<Integer> TUSK_TYPE = EntityDataManager.createKey(MoCEntityElephant.class, DataSerializers.VARINT); public ItemStack localstack; boolean hasPlatform; public int tailCounter; public int trunkCounter; public int earCounter; private byte tuskUses; private byte temper;
  private static final DataParameter<Integer> STORAGE_TYPE = EntityDataManager.createKey(MoCEntityElephant.class, DataSerializers.VARINT);
  private static final DataParameter<Integer> HARNESS_TYPE = EntityDataManager.createKey(MoCEntityElephant.class, DataSerializers.VARINT);


  public MoCEntityElephant(World world) {
    super(world);
    setAdult(true);
    setTamed(false);
    setEdad(50);
    setSize(1.1F, 3.0F);
    this.stepHeight = 1.0F;

    if (!this.world.isRemote) {
      if (this.rand.nextInt(4) == 0) {
        setAdult(false);
      } else {
        setAdult(true);
      }
    }
  }


  protected void initEntityAI() {
    this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
    this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
  }


  public void selectType() {
    checkSpawningBiome();
    if (getType() == 0) {
      setType(this.rand.nextInt(2) + 1);
    }
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
    setHealth(getMaxHealth());
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(TUSK_TYPE, Integer.valueOf(0));
    this.dataManager.register(STORAGE_TYPE, Integer.valueOf(0));
    this.dataManager.register(HARNESS_TYPE, Integer.valueOf(0));
  }

  public int getTusks() {
    return ((Integer)this.dataManager.get(TUSK_TYPE)).intValue();
  }

  public void setTusks(int i) {
    this.dataManager.set(TUSK_TYPE, Integer.valueOf(i));
  }


  public int getArmorType() {
    return ((Integer)this.dataManager.get(HARNESS_TYPE)).intValue();
  }


  public void setArmorType(int i) {
    this.dataManager.set(HARNESS_TYPE, Integer.valueOf(i));
  }

  public int getStorage() {
    return ((Integer)this.dataManager.get(STORAGE_TYPE)).intValue();
  }

  public void setStorage(int i) {
    this.dataManager.set(STORAGE_TYPE, Integer.valueOf(i));
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("elephantafrican.png");
      case 2:
        return MoCreatures.proxy.getTexture("elephantindian.png");
      case 3:
        return MoCreatures.proxy.getTexture("mammoth.png");
      case 4:
        return MoCreatures.proxy.getTexture("mammothsonghua.png");
      case 5:
        return MoCreatures.proxy.getTexture("elephantindianpretty.png");
    }
    return MoCreatures.proxy.getTexture("elephantafrican.png");
  }


  public float calculateMaxHealth() {
    switch (getType()) {
      case 1:
        return 40.0F;
      case 2:
        return 30.0F;
      case 3:
        return 50.0F;
      case 4:
        return 60.0F;
      case 5:
        return 40.0F;
    }

    return 30.0F;
  }



  public double getCustomSpeed() {
    if (this.sitCounter != 0) {
      return 0.0D;
    }
    double tSpeed = 0.5D;
    if (getType() == 1) {
      tSpeed = 0.55D;
    } else if (getType() == 2) {
      tSpeed = 0.6D;
    } else if (getType() == 3) {
      tSpeed = 0.5D;
    } else if (getType() == 4) {
      tSpeed = 0.55D;
    } else if (getType() == 5) {
      tSpeed = 0.5D;
    }

    if (this.sprintCounter > 0 && this.sprintCounter < 150) {
      tSpeed *= 1.5D;
    }
    if (this.sprintCounter > 150) {
      tSpeed *= 0.5D;
    }

    return tSpeed;
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (!this.world.isRemote) {
      if (this.sprintCounter > 0 && this.sprintCounter < 150 && isBeingRidden() && this.rand.nextInt(15) == 0) {
        MoCTools.buckleMobsNotPlayers((EntityLiving)this, Double.valueOf(3.0D), this.world);
      }

      if (this.sprintCounter > 0 && ++this.sprintCounter > 300) {
        this.sprintCounter = 0;
      }

      if (getIsTamed() && !isBeingRidden() && getArmorType() >= 1 && this.rand.nextInt(20) == 0) {
        EntityPlayer ep = this.world.getClosestPlayerToEntity((Entity)this, 3.0D);
        if (ep != null && (!MoCreatures.proxy.enableOwnership || ep.getUniqueID().equals(getOwnerId())) && ep.isSneaking()) {
          sit();
        }
      }

      if (MoCreatures.proxy.elephantBulldozer && getIsTamed() && isBeingRidden() && getTusks() > 0) {
        int height = 2;
        if (getType() == 3) {
          height = 3;
        }
        if (getType() == 4) {
          height = 3;
        }
        int dmg = MoCTools.destroyBlocksInFront((Entity)this, 2.0D, getTusks(), height);
        checkTusks(dmg);
      }

    }
    else {

      if (this.tailCounter > 0 && ++this.tailCounter > 8) {
        this.tailCounter = 0;
      }

      if (this.rand.nextInt(200) == 0) {
        this.tailCounter = 1;
      }

      if (this.trunkCounter > 0 && ++this.trunkCounter > 38) {
        this.trunkCounter = 0;
      }

      if (this.trunkCounter == 0 && this.rand.nextInt(200) == 0) {
        this.trunkCounter = this.rand.nextInt(10) + 1;
      }

      if (this.earCounter > 0 && ++this.earCounter > 30) {
        this.earCounter = 0;
      }

      if (this.rand.nextInt(200) == 0) {
        this.earCounter = this.rand.nextInt(20) + 1;
      }
    }


    if (this.sitCounter != 0 &&
      ++this.sitCounter > 100) {
      this.sitCounter = 0;
    }
  }








  private void checkTusks(int dmg) {
    this.tuskUses = (byte)(this.tuskUses + (byte)dmg);
    if ((getTusks() == 1 && this.tuskUses > 59) || (getTusks() == 2 && this.tuskUses > 250) || (
      getTusks() == 3 && this.tuskUses > 1000)) {
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_TURTLE_HURT);
      setTusks(0);
    }
  }

  private void sit() {
    this.sitCounter = 1;
    if (!this.world.isRemote) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
    getNavigator().clearPath();
  }


  public void performAnimation(int animationType) {
    if (animationType == 0) {

      this.sitCounter = 1;
      getNavigator().clearPath();
    }
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && !getIsTamed() && !getIsAdult() && stack.getItem() == Items.CAKE) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
      this.temper = (byte)(this.temper + 2);
      setHealth(getMaxHealth());
      if (!this.world.isRemote && !getIsAdult() && !getIsTamed() && this.temper >= 10) {
        MoCTools.tameWithName(player, (IMoCTameable)this);
      }
      return true;
    }

    if (!stack.isEmpty() && !getIsTamed() && !getIsAdult() && stack.getItem() == MoCItems.sugarlump) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
      this.temper = (byte)(this.temper + 1);
      setHealth(getMaxHealth());
      if (!this.world.isRemote && !getIsAdult() && !getIsTamed() && this.temper >= 10) {
        setTamed(true);
        MoCTools.tameWithName(player, (IMoCTameable)this);
      }
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() == 0 && stack.getItem() == MoCItems.elephantHarness) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
      setArmorType(1);
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() >= 1 && getStorage() == 0 && stack
      .getItem() == MoCItems.elephantChest) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ROPING);

      setStorage(1);
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() >= 1 && getStorage() == 1 && stack
      .getItem() == MoCItems.elephantChest) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
      setStorage(2);
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getType() == 3 && getArmorType() >= 1 && getStorage() == 2 && stack
      .getItem() == Item.getItemFromBlock((Block)Blocks.CHEST)) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
      setStorage(3);
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getType() == 3 && getArmorType() >= 1 && getStorage() == 3 && stack
      .getItem() == Item.getItemFromBlock((Block)Blocks.CHEST)) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
      setStorage(4);
      return true;
    }


    if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() == 1 && getType() == 2 && stack
      .getItem() == MoCItems.elephantGarment) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
      setArmorType(2);
      setType(5);
      return true;
    }


    if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() == 2 && getType() == 5 && stack
      .getItem() == MoCItems.elephantHowdah) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ROPING);
      setArmorType(3);
      return true;
    }


    if (!stack.isEmpty() && getIsTamed() && getIsAdult() && getArmorType() == 1 && getType() == 4 && stack
      .getItem() == MoCItems.mammothPlatform) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
      setArmorType(3);
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && getIsAdult() && stack.getItem() == MoCItems.tusksWood) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
      dropTusks();
      this.tuskUses = (byte)stack.getItemDamage();
      setTusks(1);
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && getIsAdult() && stack.getItem() == MoCItems.tusksIron) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
      dropTusks();
      this.tuskUses = (byte)stack.getItemDamage();
      setTusks(2);
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && getIsAdult() && stack.getItem() == MoCItems.tusksDiamond) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
      dropTusks();
      this.tuskUses = (byte)stack.getItemDamage();
      setTusks(3);
      return true;
    }

    if (player.isSneaking()) {
      initChest();
      if (getStorage() == 1) {
        if (!this.world.isRemote) {
          player.displayGUIChest((IInventory)this.localelephantchest);
        }
        return true;
      }
      if (getStorage() == 2) {
        InventoryLargeChest doubleChest = new InventoryLargeChest("ElephantChest", (ILockableContainer)this.localelephantchest, (ILockableContainer)this.localelephantchest2);
        if (!this.world.isRemote) {
          player.displayGUIChest((IInventory)doubleChest);
        }
        return true;
      }
      if (getStorage() == 3) {
        InventoryLargeChest doubleChest = new InventoryLargeChest("ElephantChest", (ILockableContainer)this.localelephantchest, (ILockableContainer)this.localelephantchest2);
        InventoryLargeChest tripleChest = new InventoryLargeChest("ElephantChest", (ILockableContainer)doubleChest, (ILockableContainer)this.localelephantchest3);
        if (!this.world.isRemote) {
          player.displayGUIChest((IInventory)tripleChest);
        }
        return true;
      }
      if (getStorage() == 4) {
        InventoryLargeChest doubleChest = new InventoryLargeChest("ElephantChest", (ILockableContainer)this.localelephantchest, (ILockableContainer)this.localelephantchest2);
        InventoryLargeChest doubleChestb = new InventoryLargeChest("ElephantChest", (ILockableContainer)this.localelephantchest3, (ILockableContainer)this.localelephantchest4);
        InventoryLargeChest fourChest = new InventoryLargeChest("ElephantChest", (ILockableContainer)doubleChest, (ILockableContainer)doubleChestb);
        if (!this.world.isRemote) {
          player.displayGUIChest((IInventory)fourChest);
        }
        return true;
      }
    }

    if (!stack.isEmpty() &&
      getTusks() > 0 && (stack
      .getItem() == Items.DIAMOND_PICKAXE || stack.getItem() == Items.WOODEN_PICKAXE || stack
      .getItem() == Items.STONE_PICKAXE || stack.getItem() == Items.IRON_PICKAXE || stack.getItem() == Items.GOLDEN_PICKAXE)) {
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
      dropTusks();
      return true;
    }

    if (getIsTamed() && getIsAdult() && getArmorType() >= 1 && this.sitCounter != 0) {
      if (!this.world.isRemote && player.startRiding((Entity)this)) {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;
        this.sitCounter = 0;
      }

      return true;
    }

    return super.processInteract(player, hand);
  }

  private void initChest() {
    if (getStorage() > 0 && this.localelephantchest == null) {
      this.localelephantchest = new MoCAnimalChest("ElephantChest", 18);
    }

    if (getStorage() > 1 && this.localelephantchest2 == null) {
      this.localelephantchest2 = new MoCAnimalChest("ElephantChest", 18);
    }

    if (getStorage() > 2 && this.localelephantchest3 == null) {
      this.localelephantchest3 = new MoCAnimalChest("ElephantChest", 9);
    }

    if (getStorage() > 3 && this.localelephantchest4 == null) {
      this.localelephantchest4 = new MoCAnimalChest("ElephantChest", 9);
    }
  }




  private void dropTusks() {
    if (this.world.isRemote) {
      return;
    }
    int i = getTusks();

    if (i == 1) {
      EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack((Item)MoCItems.tusksWood, 1, this.tuskUses));

      entityitem.setPickupDelay(10);
      this.world.spawnEntity((Entity)entityitem);
    }
    if (i == 2) {
      EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack((Item)MoCItems.tusksIron, 1, this.tuskUses));

      entityitem.setPickupDelay(10);
      this.world.spawnEntity((Entity)entityitem);
    }
    if (i == 3) {
      EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack((Item)MoCItems.tusksDiamond, 1, this.tuskUses));

      entityitem.setPickupDelay(10);
      this.world.spawnEntity((Entity)entityitem);
    }
    setTusks(0);
    this.tuskUses = 0;
  }


  public boolean rideableEntity() {
    return true;
  }












  public boolean checkSpawningBiome() {
    BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), this.posZ);
    Biome currentbiome = MoCTools.Biomekind(this.world, pos);

    if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
      setType(3 + this.rand.nextInt(2));
      return true;
    }
    if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SANDY)) {
      setType(1);
      return true;
    }

    if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.JUNGLE)) {
      setType(2);
      return true;
    }

    if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.PLAINS) || BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.FOREST)) {
      setType(1 + this.rand.nextInt(2));
      return true;
    }

    return false;
  }


  public float getSizeFactor() {
    float sizeF = 1.25F;

    switch (getType()) {
      case 4:
        sizeF *= 1.2F;
        break;
      case 2:
      case 5:
        sizeF *= 0.8F;
        break;
    }

    if (!getIsAdult()) {
      sizeF *= getEdad() * 0.01F;
    }
    return sizeF;
  }


  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    super.readEntityFromNBT(nbttagcompound);
    setTusks(nbttagcompound.getInteger("Tusks"));
    setArmorType(nbttagcompound.getInteger("Harness"));
    setStorage(nbttagcompound.getInteger("Storage"));
    this.tuskUses = nbttagcompound.getByte("TuskUses");
    if (getStorage() > 0) {
      NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
      this.localelephantchest = new MoCAnimalChest("ElephantChest", 18);
      for (int i = 0; i < nbttaglist.tagCount(); i++) {
        NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
        int j = nbttagcompound1.getByte("Slot") & 0xFF;
        if (j >= 0 && j < this.localelephantchest.getSizeInventory()) {
          this.localelephantchest.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
        }
      }
    }
    if (getStorage() >= 2) {
      NBTTagList nbttaglist = nbttagcompound.getTagList("Items2", 10);
      this.localelephantchest2 = new MoCAnimalChest("ElephantChest", 18);
      for (int i = 0; i < nbttaglist.tagCount(); i++) {
        NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
        int j = nbttagcompound1.getByte("Slot") & 0xFF;
        if (j >= 0 && j < this.localelephantchest2.getSizeInventory()) {
          this.localelephantchest2.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
        }
      }
    }

    if (getStorage() >= 3) {
      NBTTagList nbttaglist = nbttagcompound.getTagList("Items3", 10);
      this.localelephantchest3 = new MoCAnimalChest("ElephantChest", 9);
      for (int i = 0; i < nbttaglist.tagCount(); i++) {
        NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
        int j = nbttagcompound1.getByte("Slot") & 0xFF;
        if (j >= 0 && j < this.localelephantchest3.getSizeInventory()) {
          this.localelephantchest3.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
        }
      }
    }
    if (getStorage() >= 4) {
      NBTTagList nbttaglist = nbttagcompound.getTagList("Items4", 10);
      this.localelephantchest4 = new MoCAnimalChest("ElephantChest", 9);
      for (int i = 0; i < nbttaglist.tagCount(); i++) {
        NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
        int j = nbttagcompound1.getByte("Slot") & 0xFF;
        if (j >= 0 && j < this.localelephantchest4.getSizeInventory()) {
          this.localelephantchest4.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
        }
      }
    }
  }


  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
    nbttagcompound.setInteger("Tusks", getTusks());
    nbttagcompound.setInteger("Harness", getArmorType());
    nbttagcompound.setInteger("Storage", getStorage());
    nbttagcompound.setByte("TuskUses", this.tuskUses);

    if (getStorage() > 0 && this.localelephantchest != null) {
      NBTTagList nbttaglist = new NBTTagList();
      for (int i = 0; i < this.localelephantchest.getSizeInventory(); i++) {
        this.localstack = this.localelephantchest.getStackInSlot(i);
        if (this.localstack != null && !this.localstack.isEmpty()) {
          NBTTagCompound nbttagcompound1 = new NBTTagCompound();
          nbttagcompound1.setByte("Slot", (byte)i);
          this.localstack.writeToNBT(nbttagcompound1);
          nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
      }
      nbttagcompound.setTag("Items", (NBTBase)nbttaglist);
    }

    if (getStorage() >= 2 && this.localelephantchest2 != null) {
      NBTTagList nbttaglist = new NBTTagList();
      for (int i = 0; i < this.localelephantchest2.getSizeInventory(); i++) {
        this.localstack = this.localelephantchest2.getStackInSlot(i);
        if (this.localstack != null && !this.localstack.isEmpty()) {
          NBTTagCompound nbttagcompound1 = new NBTTagCompound();
          nbttagcompound1.setByte("Slot", (byte)i);
          this.localstack.writeToNBT(nbttagcompound1);
          nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
      }
      nbttagcompound.setTag("Items2", (NBTBase)nbttaglist);
    }

    if (getStorage() >= 3 && this.localelephantchest3 != null) {
      NBTTagList nbttaglist = new NBTTagList();
      for (int i = 0; i < this.localelephantchest3.getSizeInventory(); i++) {
        this.localstack = this.localelephantchest3.getStackInSlot(i);
        if (this.localstack != null && !this.localstack.isEmpty()) {
          NBTTagCompound nbttagcompound1 = new NBTTagCompound();
          nbttagcompound1.setByte("Slot", (byte)i);
          this.localstack.writeToNBT(nbttagcompound1);
          nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
      }
      nbttagcompound.setTag("Items3", (NBTBase)nbttaglist);
    }

    if (getStorage() >= 4 && this.localelephantchest4 != null) {
      NBTTagList nbttaglist = new NBTTagList();
      for (int i = 0; i < this.localelephantchest4.getSizeInventory(); i++) {
        this.localstack = this.localelephantchest4.getStackInSlot(i);
        if (this.localstack != null && !this.localstack.isEmpty()) {
          NBTTagCompound nbttagcompound1 = new NBTTagCompound();
          nbttagcompound1.setByte("Slot", (byte)i);
          this.localstack.writeToNBT(nbttagcompound1);
          nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
      }
      nbttagcompound.setTag("Items4", (NBTBase)nbttaglist);
    }
  }


  public boolean isMyHealFood(ItemStack stack) {
    return (!stack.isEmpty() && (stack
      .getItem() == Items.BAKED_POTATO || stack.getItem() == Items.BREAD || stack.getItem() == MoCItems.haystack));
  }


  public boolean isMovementCeased() {
    return (isBeingRidden() || this.sitCounter != 0);
  }


  public void Riding() {
    if (isBeingRidden() && getRidingEntity() instanceof EntityPlayer) {
      EntityPlayer entityplayer = (EntityPlayer)getRidingEntity();
      List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(1.0D, 0.0D, 1.0D));
      if (list != null) {
        for (int i = 0; i < list.size(); i++) {
          Entity entity = list.get(i);
          if (!entity.isDead)
          {

            entity.onCollideWithPlayer(entityplayer);
          }
        }
      }
      if (entityplayer.isSneaking() &&
        !this.world.isRemote) {
        if (this.sitCounter == 0) {
          sit();
        }
        if (this.sitCounter >= 50) {
          entityplayer.dismountRidingEntity();
        }
      }
    }
  }




  public boolean canBePushed() {
    return !isBeingRidden();
  }


  public boolean canBeCollidedWith() {
    return !isBeingRidden();
  }



  public void updatePassenger(Entity passenger) {
    double dist = 1.0D;
    switch (getType()) {

      case 1:
      case 3:
        dist = 0.8D;
        break;

      case 2:
      case 5:
        dist = 0.1D;
        break;
      case 4:
        dist = 1.2D;
        break;
    }


    double newPosX = this.posX - dist * Math.cos((MoCTools.realAngle(this.renderYawOffset - 90.0F) / 57.29578F));
    double newPosZ = this.posZ - dist * Math.sin((MoCTools.realAngle(this.renderYawOffset - 90.0F) / 57.29578F));
    passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
  }


  public double getMountedYOffset() {
    double yOff = 0.0D;
    boolean sit = (this.sitCounter != 0);

    switch (getType()) {
      case 1:
        yOff = 0.55D;
        if (sit) {
          yOff = -0.05D;
        }
        break;
      case 3:
        yOff = 0.55D;
        if (sit) {
          yOff = -0.05D;
        }
        break;
      case 2:
      case 5:
        yOff = -0.17D;
        if (sit) {
          yOff = -0.5D;
        }
        break;
      case 4:
        yOff = 1.2D;
        if (sit) {
          yOff = 0.45D;
        }
        break;
    }
    return yOff + this.height * 0.75D;
  }





  public boolean isEntityInsideOpaqueBlock() {
    return false;
  }


  public int getTalkInterval() {
    return 300;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_ELEPHANT_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_ELEPHANT_HURT;
  }


  protected SoundEvent getAmbientSound() {
    if (!getIsAdult() && getEdad() < 80) {
      return MoCSoundEvents.ENTITY_ELEPHANT_AMBIENT_BABY;
    }
    return MoCSoundEvents.ENTITY_ELEPHANT_AMBIENT;
  }


  protected Item getDropItem() {
    return (Item)MoCItems.animalHide;
  }


  public boolean getCanSpawnHere() {
    return (((MoCEntityData)MoCreatures.entityMap.get(getClass())).getFrequency() > 0 && getCanSpawnHereCreature() && getCanSpawnHereLiving());
  }


  public void dropMyStuff() {
    if (!this.world.isRemote) {
      dropTusks();

      if (getStorage() > 0) {
        if (getStorage() > 0) {
          MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.elephantChest, 1));
          if (this.localelephantchest != null) {
            MoCTools.dropInventory((Entity)this, this.localelephantchest);
          }
        }

        if (getStorage() >= 2) {
          if (this.localelephantchest2 != null) {
            MoCTools.dropInventory((Entity)this, this.localelephantchest2);
          }
          MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.elephantChest, 1));
        }
        if (getStorage() >= 3) {
          if (this.localelephantchest3 != null) {
            MoCTools.dropInventory((Entity)this, this.localelephantchest3);
          }
          MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Block)Blocks.CHEST, 1));
        }
        if (getStorage() >= 4) {
          if (this.localelephantchest4 != null) {
            MoCTools.dropInventory((Entity)this, this.localelephantchest4);
          }
          MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Block)Blocks.CHEST, 1));
        }
        setStorage(0);
      }
      dropArmor();
    }
  }


  public void dropArmor() {
    if (this.world.isRemote) {
      return;
    }
    if (getArmorType() >= 1) {
      MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.elephantHarness, 1));
    }
    if (getType() == 5 && getArmorType() >= 2) {

      MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.elephantGarment, 1));
      if (getArmorType() == 3) {
        MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.elephantHowdah, 1));
      }
      setType(2);
    }
    if (getType() == 4 && getArmorType() == 3) {
      MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.mammothPlatform, 1));
    }
    setArmorType(0);
  }



  public int nameYOffset() {
    int yOff = (int)((100 / getEdad()) * getSizeFactor() * -110.0F);
    if (getIsAdult()) {
      yOff = (int)(getSizeFactor() * -110.0F);
    }
    if (this.sitCounter != 0)
      yOff += 25;
    return yOff;
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (super.attackEntityFrom(damagesource, i)) {
      Entity entity = damagesource.getTrueSource();
      if ((entity != null && getIsTamed() && entity instanceof EntityPlayer) || !(entity instanceof EntityLivingBase)) {
        return false;
      }
      if (isRidingOrBeingRiddenBy(entity)) {
        return true;
      }
      if (entity != this && shouldAttackPlayers()) {
        setAttackTarget((EntityLivingBase)entity);
      }
      return true;
    }
    return false;
  }


  public void fall(float f, float f1) {
    int i = (int)Math.ceil((f - 3.0F));
    if (i > 0) {
      i /= 2;
      if (i > 0) {
        attackEntityFrom(DamageSource.FALL, i);
      }
      if (isBeingRidden() && i > 0) {
        for (Entity entity : getRecursivePassengers())
        {
          entity.attackEntityFrom(DamageSource.FALL, i);
        }
      }
      IBlockState iblockstate = this.world.getBlockState(new BlockPos(this.posX, this.posY - 0.2D - this.prevRotationYaw, this.posZ));
      Block block = iblockstate.getBlock();

      if (iblockstate.getMaterial() != Material.AIR && !isSilent()) {

        SoundType soundtype = block.getSoundType(iblockstate, this.world, new BlockPos(this.posX, this.posY - 0.2D - this.prevRotationYaw, this.posZ), (Entity)this);
        this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, soundtype.getStepSound(), getSoundCategory(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
      }
    }
  }


  public boolean isNotScared() {
    return (getIsAdult() || getEdad() > 80 || getIsTamed());
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityElephant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
