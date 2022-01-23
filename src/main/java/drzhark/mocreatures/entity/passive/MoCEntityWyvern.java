package drzhark.mocreatures.entity.passive;
import drzhark.mocreatures.MoCPetData;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityAnimal;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.entity.ai.EntityAIHunt;
import drzhark.mocreatures.entity.item.MoCEntityEgg;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.inventory.MoCAnimalChest;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCEntityWyvern extends MoCEntityTameableAnimal {
  public MoCAnimalChest localchest;
  public static final String[] wyvernNames = new String[] { "Jungle", "Swamp", "Savanna", "Sand", "Mother", "Undead", "Light", "Dark", "Arctic", "Cave", "Mountain", "Sea" }; public ItemStack localstack; public int mouthCounter;
  public int wingFlapCounter;
  public int diveCounter;
  protected EntityAIWanderMoC2 wander;
  private int transformType;
  private int transformCounter;
  private int tCounter;
  private float fTransparency;
  private static final DataParameter<Boolean> RIDEABLE = EntityDataManager.createKey(MoCEntityWyvern.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> CHESTED = EntityDataManager.createKey(MoCEntityWyvern.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(MoCEntityWyvern.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> GHOST = EntityDataManager.createKey(MoCEntityWyvern.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> FLYING = EntityDataManager.createKey(MoCEntityWyvern.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Integer> ARMOR_TYPE = EntityDataManager.createKey(MoCEntityWyvern.class, DataSerializers.VARINT);


  public MoCEntityWyvern(World world) {
    super(world);
    setSize(1.9F, 1.7F);
    setAdult(false);
    setTamed(false);
    this.stepHeight = 1.0F;

    if (this.rand.nextInt(6) == 0) {
      setEdad(50 + this.rand.nextInt(50));
    } else {
      setEdad(80 + this.rand.nextInt(20));
    }
  }


  protected void initEntityAI() {
    this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(4, (EntityAIBase)(this.wander = new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80)));
    this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
    this.targetTasks.addTask(2, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(RIDEABLE, Boolean.valueOf(false));
    this.dataManager.register(SITTING, Boolean.valueOf(false));
    this.dataManager.register(CHESTED, Boolean.valueOf(false));
    this.dataManager.register(FLYING, Boolean.valueOf(false));
    this.dataManager.register(GHOST, Boolean.valueOf(false));
    this.dataManager.register(ARMOR_TYPE, Integer.valueOf(0));
  }

  public boolean getIsFlying() {
    return ((Boolean)this.dataManager.get(FLYING)).booleanValue();
  }

  public void setIsFlying(boolean flag) {
    this.dataManager.set(FLYING, Boolean.valueOf(flag));
  }


  public int getArmorType() {
    return ((Integer)this.dataManager.get(ARMOR_TYPE)).intValue();
  }


  public void setArmorType(int i) {
    this.dataManager.set(ARMOR_TYPE, Integer.valueOf(i));
  }


  public boolean getIsRideable() {
    return ((Boolean)this.dataManager.get(RIDEABLE)).booleanValue();
  }


  public void setRideable(boolean flag) {
    this.dataManager.set(RIDEABLE, Boolean.valueOf(flag));
  }

  public boolean getIsChested() {
    return ((Boolean)this.dataManager.get(CHESTED)).booleanValue();
  }

  public void setIsChested(boolean flag) {
    this.dataManager.set(CHESTED, Boolean.valueOf(flag));
  }


  public boolean getIsSitting() {
    return ((Boolean)this.dataManager.get(SITTING)).booleanValue();
  }

  public void setSitting(boolean flag) {
    this.dataManager.set(SITTING, Boolean.valueOf(flag));
  }

  public boolean getIsGhost() {
    return ((Boolean)this.dataManager.get(GHOST)).booleanValue();
  }

  public void setIsGhost(boolean flag) {
    this.dataManager.set(GHOST, Boolean.valueOf(flag));
  }


  public void selectType() {
    if (getType() == 0) {
      if (this.rand.nextInt(5) == 0) {
        setType(5);
      } else {
        int i = this.rand.nextInt(100);
        if (i <= 12) {
          setType(1);
        } else if (i <= 24) {
          setType(2);
        } else if (i <= 36) {
          setType(3);
        } else if (i <= 48) {
          setType(4);
        } else if (i <= 60) {
          setType(9);
        } else if (i <= 72) {
          setType(10);
        } else if (i <= 84) {
          setType(11);
        } else if (i <= 95) {
          setType(12);
        } else {
          setType(5);
        }
      }
    }
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
    setHealth(getMaxHealth());
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(calculateAttackDmg());
  }


  public boolean isNotScared() {
    return true;
  }

  public double calculateMaxHealth() {
    if (getType() == 6 || getType() == 7 || getType() == 8) {
      return 60.0D;
    }
    if (getType() == 5) {
      return 80.0D;
    }
    if (getType() == 13) {
      return 100.0D;
    }
    return 40.0D;
  }

  public double calculateAttackDmg() {
    if (getType() == 6 || getType() == 7 || getType() == 8 || getType() == 13) {
      return 8.0D;
    }
    if (getType() == 5) {
      return 12.0D;
    }
    return 5.0D;
  }










  public ResourceLocation getTexture() {
    if (this.transformCounter != 0 && this.transformType > 5) {
      String newText = "wyverndark.png";
      if (this.transformType == 6) {
        newText = "wyvernundead.png";
      }
      if (this.transformType == 7) {
        newText = "wyvernlight.png";
      }
      if (this.transformType == 8) {
        newText = "wyverndark.png";
      }

      if (this.transformCounter % 5 == 0) {
        return MoCreatures.proxy.getTexture(newText);
      }
      if (this.transformCounter > 50 && this.transformCounter % 3 == 0) {
        return MoCreatures.proxy.getTexture(newText);
      }

      if (this.transformCounter > 75 && this.transformCounter % 4 == 0) {
        return MoCreatures.proxy.getTexture(newText);
      }
    }

    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("wyvernjungle.png");
      case 2:
        return MoCreatures.proxy.getTexture("wyvernmix.png");
      case 3:
        return MoCreatures.proxy.getTexture("wyvernsand.png");
      case 4:
        return MoCreatures.proxy.getTexture("wyvernsun.png");
      case 5:
        return MoCreatures.proxy.getTexture("wyvernmother.png");
      case 6:
        return MoCreatures.proxy.getTexture("wyvernundead.png");
      case 7:
        return MoCreatures.proxy.getTexture("wyvernlight.png");
      case 8:
        return MoCreatures.proxy.getTexture("wyverndark.png");
      case 9:
        return MoCreatures.proxy.getTexture("wyvernarctic.png");
      case 10:
        return MoCreatures.proxy.getTexture("wyverncave.png");
      case 11:
        return MoCreatures.proxy.getTexture("wyvernmountain.png");
      case 12:
        return MoCreatures.proxy.getTexture("wyvernsea.png");
    }


    return MoCreatures.proxy.getTexture("wyvernsun.png");
  }


  public void transform(int tType) {
    if (!this.world.isRemote) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), tType), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
    this.transformType = tType;
    this.transformCounter = 1;
  }



  public void onLivingUpdate() {
    if (this.wingFlapCounter > 0 && ++this.wingFlapCounter > 20) {
      this.wingFlapCounter = 0;
    }
    if (this.wingFlapCounter == 5 && !this.world.isRemote) {
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_WYVERN_WINGFLAP);
    }

    if (this.transformCounter > 0) {
      if (this.transformCounter == 40) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_TRANSFORM);
      }

      if (++this.transformCounter > 100) {
        this.transformCounter = 0;
        if (this.transformType != 0) {
          setType(this.transformType);
          selectType();
        }
      }
    }

    if (!this.world.isRemote) {
      if (!isMovementCeased() && !getIsTamed() && this.rand.nextInt(300) == 0) {
        setIsFlying(!getIsFlying());
      }
      if (isMovementCeased() && getIsFlying()) {
        setIsFlying(false);
      }

      if (getAttackTarget() != null && (!getIsTamed() || getRidingEntity() != null) && !isMovementCeased() && this.rand.nextInt(20) == 0) {
        setIsFlying(true);
      }
      if (!getIsTamed() && this.dimension == MoCreatures.WyvernLairDimensionID && this.rand.nextInt(50) == 0 && this.posY < 10.0D) {
        setDead();
      }

      if (getIsFlying() && getNavigator().noPath() && !isMovementCeased() && getAttackTarget() == null && this.rand.nextInt(30) == 0) {
        this.wander.makeUpdate();
      }

      if (this.motionY > 0.5D)
      {
        this.motionY = 0.5D;
      }

      if (isOnAir()) {
        float myFlyingSpeed = MoCTools.getMyMovementSpeed((Entity)this);
        int wingFlapFreq = (int)(25.0F - myFlyingSpeed * 10.0F);
        if (!isBeingRidden() || wingFlapFreq < 5) {
          wingFlapFreq = 5;
        }
        if (this.rand.nextInt(wingFlapFreq) == 0) {
          wingFlap();
        }
      }

      if (getIsGhost() && getEdad() > 0 && getEdad() < 10 && this.rand.nextInt(5) == 0) {
        setEdad(getEdad() + 1);
        if (getEdad() == 9) {
          setEdad(140);
          setAdult(true);
        }

      }
    } else {

      if (this.mouthCounter > 0 && ++this.mouthCounter > 30) {
        this.mouthCounter = 0;
      }

      if (this.diveCounter > 0 && ++this.diveCounter > 5) {
        this.diveCounter = 0;
      }
    }
    super.onLivingUpdate();
  }

  public void wingFlap() {
    if (this.wingFlapCounter == 0) {
      this.wingFlapCounter = 1;
      if (!this.world.isRemote) {
        MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 3), new NetworkRegistry.TargetPoint(this.world.provider
              .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
      }
    }
  }


  public float getSizeFactor() {
    return getEdad() * 0.01F;
  }


  public boolean isFlyingAlone() {
    return (getIsFlying() && !isBeingRidden());
  }


  public int maxFlyingHeight() {
    if (getIsTamed())
      return 5;
    return 18;
  }


  public int minFlyingHeight() {
    return 1;
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && stack.getItem() == MoCItems.whip && getIsTamed() && !isBeingRidden()) {
      setSitting(!getIsSitting());
      return true;
    }

    if (!stack.isEmpty() && !getIsRideable() && getEdad() > 90 && getIsTamed() && (stack
      .getItem() == Items.SADDLE || stack.getItem() == MoCItems.horsesaddle)) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      setRideable(true);
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && getEdad() > 90 && stack.getItem() == Items.IRON_HORSE_ARMOR) {
      if (getArmorType() == 0) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON);
      }
      dropArmor();
      setArmorType(1);
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }

      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && getEdad() > 90 && stack.getItem() == Items.GOLDEN_HORSE_ARMOR) {
      if (getArmorType() == 0) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON);
      }
      dropArmor();
      setArmorType(2);
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && getEdad() > 90 && stack.getItem() == Items.DIAMOND_HORSE_ARMOR) {
      if (getArmorType() == 0) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON);
      }
      dropArmor();
      setArmorType(3);
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && getEdad() > 90 && !getIsChested() && stack.getItem() == Item.getItemFromBlock((Block)Blocks.CHEST)) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      setIsChested(true);
      MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
      return true;
    }

    if (getIsChested() && player.isSneaking()) {
      if (this.localchest == null) {
        this.localchest = new MoCAnimalChest("WyvernChest", 9);
      }
      if (!this.world.isRemote) {
        player.displayGUIChest((IInventory)this.localchest);
      }
      return true;
    }

    if (!stack.isEmpty() && getIsGhost() && getIsTamed() && stack.getItem() == MoCItems.amuletghost) {

      player.setHeldItem(hand, ItemStack.EMPTY);
      if (!this.world.isRemote) {
        MoCPetData petData = MoCreatures.instance.mapData.getPetData(getOwnerId());
        if (petData != null) {
          petData.setInAmulet(getOwnerPetId(), true);
        }
        dropMyStuff();
        MoCTools.dropAmulet(this, 3, player);
        this.isDead = true;
      }

      return true;
    }


    if (!stack.isEmpty() && !getIsGhost() && stack.getItem() == MoCItems.essencelight && getIsTamed() && getEdad() > 90 &&
      getType() < 5) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
      } else {
        player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
      }

      if (!this.world.isRemote) {
        int i = getType() + 49;
        MoCEntityEgg entityegg = new MoCEntityEgg(this.world, i);
        entityegg.setPosition(player.posX, player.posY, player.posZ);
        player.world.spawnEntity((Entity)entityegg);
        entityegg.motionY += (this.world.rand.nextFloat() * 0.05F);
        entityegg.motionX += ((this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.3F);
        entityegg.motionZ += ((this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.3F);
      }
      return true;
    }

    if (!stack.isEmpty() && this.transformCounter == 0 && !getIsGhost() && getType() == 5 && stack
      .getItem() == MoCItems.essenceundead && getIsTamed()) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
      } else {
        player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
      }

      if (!this.world.isRemote) {
        transform(6);
      }
      return true;
    }

    if (!stack.isEmpty() && this.transformCounter == 0 && !getIsGhost() && getType() == 5 && stack
      .getItem() == MoCItems.essencelight && getIsTamed()) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
      } else {
        player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
      }

      if (!this.world.isRemote) {
        transform(7);
      }
      return true;
    }

    if (!stack.isEmpty() && this.transformCounter == 0 && !getIsGhost() && getType() == 5 && stack
      .getItem() == MoCItems.essencedarkness && getIsTamed()) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
      } else {
        player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
      }

      if (!this.world.isRemote) {
        transform(8);
      }
      return true;
    }

    if (getIsRideable() && getEdad() > 90 && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
      if (!this.world.isRemote && player.startRiding((Entity)this)) {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;
        setSitting(false);
      }

      return true;
    }

    return super.processInteract(player, hand);
  }





  public void dropArmor() {
    if (!this.world.isRemote) {
      int i = getArmorType();
      if (i != 0) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF);
      }

      if (i == 1) {
        EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Items.IRON_HORSE_ARMOR, 1));
        entityitem.setPickupDelay(10);
        this.world.spawnEntity((Entity)entityitem);
      }
      if (i == 2) {
        EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Items.GOLDEN_HORSE_ARMOR, 1));
        entityitem.setPickupDelay(10);
        this.world.spawnEntity((Entity)entityitem);
      }
      if (i == 3) {
        EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(Items.DIAMOND_HORSE_ARMOR, 1));
        entityitem.setPickupDelay(10);
        this.world.spawnEntity((Entity)entityitem);
      }
      setArmorType(0);
    }
  }


  public boolean rideableEntity() {
    return true;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_WYVERN_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    openMouth();
    return MoCSoundEvents.ENTITY_WYVERN_HURT;
  }


  protected SoundEvent getAmbientSound() {
    openMouth();
    return MoCSoundEvents.ENTITY_WYVERN_AMBIENT;
  }


  public int getTalkInterval() {
    return 400;
  }


  public boolean isMovementCeased() {
    return (isBeingRidden() || getIsSitting());
  }


  public boolean isFlyer() {
    return true;
  }



  public void fall(float f, float f1) {}


  public double getMountedYOffset() {
    return this.height * 0.85D * getSizeFactor();
  }


  public void updatePassenger(Entity passenger) {
    double dist = getSizeFactor() * 0.3D;
    double newPosX = this.posX - dist * Math.cos((MoCTools.realAngle(this.renderYawOffset - 90.0F) / 57.29578F));
    double newPosZ = this.posZ - dist * Math.sin((MoCTools.realAngle(this.renderYawOffset - 90.0F) / 57.29578F));
    passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
  }


  public boolean attackEntityAsMob(Entity entityIn) {
    if (entityIn instanceof EntityPlayer && !shouldAttackPlayers()) {
      return false;
    }
    openMouth();
    return super.attackEntityAsMob(entityIn);
  }


  protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
    if (entityIn instanceof EntityPlayer && this.rand.nextInt(3) == 0) {
      MoCreatures.poisonPlayer((EntityPlayer)entityIn);
      ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 0));
    }

    super.applyEnchantments(entityLivingBaseIn, entityIn);
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    Entity entity = damagesource.getTrueSource();
    if (isRidingOrBeingRiddenBy(entity)) {
      return false;
    }
    if (super.attackEntityFrom(damagesource, i)) {
      if (entity != null && getIsTamed() && entity instanceof EntityPlayer) {
        return false;
      }

      if (entity != this && super.shouldAttackPlayers()) {
        setAttackTarget((EntityLivingBase)entity);
      }
      return true;
    }
    return false;
  }







  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
    nbttagcompound.setBoolean("Saddle", getIsRideable());
    nbttagcompound.setBoolean("Chested", getIsChested());
    nbttagcompound.setInteger("ArmorType", getArmorType());
    nbttagcompound.setBoolean("isSitting", getIsSitting());
    nbttagcompound.setBoolean("isGhost", getIsGhost());
    if (getIsChested() && this.localchest != null) {
      NBTTagList nbttaglist = new NBTTagList();
      for (int i = 0; i < this.localchest.getSizeInventory(); i++) {
        this.localstack = this.localchest.getStackInSlot(i);
        if (this.localstack != null && !this.localstack.isEmpty()) {
          NBTTagCompound nbttagcompound1 = new NBTTagCompound();
          nbttagcompound1.setByte("Slot", (byte)i);
          this.localstack.writeToNBT(nbttagcompound1);
          nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
      }
      nbttagcompound.setTag("Items", (NBTBase)nbttaglist);
    }
  }


  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    super.readEntityFromNBT(nbttagcompound);
    setRideable(nbttagcompound.getBoolean("Saddle"));
    setIsChested(nbttagcompound.getBoolean("Chested"));
    setArmorType(nbttagcompound.getInteger("ArmorType"));
    setSitting(nbttagcompound.getBoolean("isSitting"));
    setIsGhost(nbttagcompound.getBoolean("isGhost"));
    if (getIsChested()) {
      NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
      this.localchest = new MoCAnimalChest("WyvernChest", 14);
      for (int i = 0; i < nbttaglist.tagCount(); i++) {
        NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
        int j = nbttagcompound1.getByte("Slot") & 0xFF;
        if (j >= 0 && j < this.localchest.getSizeInventory()) {
          this.localchest.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
        }
      }
    }
  }


  public int nameYOffset() {
    int yOff = getEdad() * -1;
    if (yOff < -120) {
      yOff = -120;
    }
    if (getIsSitting())
      yOff += 25;
    return yOff;
  }


  public boolean isMyHealFood(ItemStack stack) {
    return (!stack.isEmpty() && (stack.getItem() == MoCItems.ratRaw || stack.getItem() == MoCItems.rawTurkey));
  }

  private void openMouth() {
    if (!this.world.isRemote) {
      this.mouthCounter = 1;
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
  }



  public void performAnimation(int animationType) {
    if (animationType == 1)
    {
      this.mouthCounter = 1;
    }
    if (animationType == 2)
    {
      this.diveCounter = 1;
    }
    if (animationType == 3) {
      this.wingFlapCounter = 1;
    }
    if (animationType > 5 && animationType < 9) {

      this.transformType = animationType;
      this.transformCounter = 1;
    }
  }


  public void makeEntityDive() {
    if (!this.world.isRemote) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 2), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
    super.makeEntityDive();
  }


  protected void dropFewItems(boolean flag, int x) {
    int chance = MoCreatures.proxy.wyvernEggDropChance;
    if (getType() == 5) {
      chance = MoCreatures.proxy.motherWyvernEggDropChance;
    }
    if (this.rand.nextInt(100) < chance) {
      entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, getType() + 49), 0.0F);
    }
  }


  public boolean canBeCollidedWith() {
    return !isBeingRidden();
  }


  public void dropMyStuff() {
    if (!this.world.isRemote) {
      dropArmor();
      MoCTools.dropSaddle((MoCEntityAnimal)this, this.world);

      if (getIsChested()) {
        MoCTools.dropInventory((Entity)this, this.localchest);
        MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Block)Blocks.CHEST, 1));
        setIsChested(false);
      }
    }
  }


  public float getAdjustedYOffset() {
    if (getIsSitting()) {
      return 0.4F;
    }
    return 0.0F;
  }


  public double getCustomSpeed() {
    if (isBeingRidden()) {
      return 1.0D;
    }
    return 0.8D;
  }


  public int getMaxEdad() {
    if (getType() == 5) {
      return 180;
    }
    if (getType() == 6 || getType() == 7 || getType() == 8) {
      return 160;
    }
    return 120;
  }


  public EnumCreatureAttribute getCreatureAttribute() {
    if (getType() == 6 || getIsGhost()) {
      return EnumCreatureAttribute.UNDEAD;
    }
    return super.getCreatureAttribute();
  }


  public boolean isReadyToHunt() {
    return (!isMovementCeased() && !isBeingRidden());
  }


  public boolean canAttackTarget(EntityLivingBase entity) {
    return (!(entity instanceof MoCEntityWyvern) && entity.height <= 1.0D && entity.width <= 1.0D);
  }


  protected double flyerThrust() {
    return 0.6D;
  }


  public float getAIMoveSpeed() {
    if (getIsFlying()) {
      return 0.4F;
    }
    return super.getAIMoveSpeed();
  }


  protected float flyerFriction() {
    if (getType() == 5) {
      return 0.96F;
    }
    if (getType() == 6 || getType() == 7 || getType() == 8 || getIsGhost()) {
      return 0.96F;
    }
    return 0.94F;
  }


  public void makeEntityJump() {
    wingFlap();
    super.makeEntityJump();
  }


  public boolean shouldAttackPlayers() {
    return (!getIsTamed() && super.shouldAttackPlayers());
  }


  public void onDeath(DamageSource damagesource) {
    if (!this.world.isRemote) {
      if (getType() == 6) {
        MoCTools.spawnMaggots(this.world, (Entity)this);
      }

      if (!getIsGhost() && getIsTamed() && this.rand.nextInt(4) == 0) {
        MoCEntityWyvern entitywyvern = new MoCEntityWyvern(this.world);
        entitywyvern.setPosition(this.posX, this.posY, this.posZ);
        this.world.spawnEntity((Entity)entitywyvern);
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR);

        entitywyvern.setOwnerId(getOwnerId());
        entitywyvern.setTamed(true);
        EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
        if (entityplayer != null) {
          MoCTools.tameWithName(entityplayer, (IMoCTameable)entitywyvern);
        }

        entitywyvern.setAdult(false);
        entitywyvern.setEdad(1);
        entitywyvern.setType(getType());
        entitywyvern.selectType();
        entitywyvern.setIsGhost(true);
      }
    }

    super.onDeath(damagesource);
  }



  public float tFloat() {
    if (++this.tCounter > 30) {
      this.tCounter = 0;
      this.fTransparency = this.rand.nextFloat() * 0.2F + 0.15F;
    }

    if (getEdad() < 10) {
      return 0.0F;
    }
    return this.fTransparency;
  }


  protected boolean canBeTrappedInNet() {
    return (getIsTamed() && !getIsGhost());
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityWyvern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
