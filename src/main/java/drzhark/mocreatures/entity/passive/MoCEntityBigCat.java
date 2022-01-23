package drzhark.mocreatures.entity.passive;
import drzhark.mocreatures.MoCPetData;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityAnimal;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
import net.minecraft.entity.ai.EntityAISwimming;
import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
import drzhark.mocreatures.entity.ai.EntityAIHunt;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.entity.IMoCEntity;
import drzhark.mocreatures.inventory.MoCAnimalChest;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCEntityBigCat extends MoCEntityTameableAnimal {
  public int mouthCounter;
  public int tailCounter;
  protected String chestName = "BigCatChest"; public int wingFlapCounter; public MoCAnimalChest localchest; public ItemStack localstack;
  private int tCounter;
  private float fTransparency;
  private static final DataParameter<Boolean> RIDEABLE = EntityDataManager.createKey(MoCEntityBigCat.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> HAS_AMULET = EntityDataManager.createKey(MoCEntityBigCat.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(MoCEntityBigCat.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> GHOST = EntityDataManager.createKey(MoCEntityBigCat.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> CHESTED = EntityDataManager.createKey(MoCEntityBigCat.class, DataSerializers.BOOLEAN);

  public MoCEntityBigCat(World world) {
    super(world);
    setEdad(45);
    setSize(1.4F, 1.3F);
    if (this.rand.nextInt(4) == 0) {
      setAdult(false);
    } else {
      setAdult(true);
    }
    this.stepHeight = 1.0F;
  }


  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(1, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 1.0D, 2.0F, 10.0F));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.8D, 30));
    this.targetTasks.addTask(3, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
    this.targetTasks.addTask(4, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(8.0D);
  }





  public void selectType() {
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
    setHealth(getMaxHealth());
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(calculateAttackDmg());
    getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(getAttackRange());
    if (getIsAdult()) {
      setEdad(getMaxEdad());
    }
  }


  public double getCustomSpeed() {
    return 2.0D;
  }

  public float getMoveSpeed() {
    return 1.6F;
  }

  public float calculateMaxHealth() {
    return 20.0F;
  }

  public double calculateAttackDmg() {
    return 5.0D;
  }

  public double getAttackRange() {
    return 6.0D;
  }






  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(RIDEABLE, Boolean.valueOf(false));
    this.dataManager.register(SITTING, Boolean.valueOf(false));
    this.dataManager.register(GHOST, Boolean.valueOf(false));
    this.dataManager.register(HAS_AMULET, Boolean.valueOf(false));
    this.dataManager.register(CHESTED, Boolean.valueOf(false));
  }

  public boolean getHasAmulet() {
    return ((Boolean)this.dataManager.get(HAS_AMULET)).booleanValue();
  }


  public boolean getIsSitting() {
    return ((Boolean)this.dataManager.get(SITTING)).booleanValue();
  }


  public boolean getIsRideable() {
    return ((Boolean)this.dataManager.get(RIDEABLE)).booleanValue();
  }

  public boolean getIsChested() {
    return ((Boolean)this.dataManager.get(CHESTED)).booleanValue();
  }


  public boolean getIsGhost() {
    return ((Boolean)this.dataManager.get(GHOST)).booleanValue();
  }

  public void setHasAmulet(boolean flag) {
    this.dataManager.set(HAS_AMULET, Boolean.valueOf(flag));
  }

  public void setSitting(boolean flag) {
    this.dataManager.set(SITTING, Boolean.valueOf(flag));
  }

  public void setIsChested(boolean flag) {
    this.dataManager.set(CHESTED, Boolean.valueOf(flag));
  }

  public void setRideable(boolean flag) {
    this.dataManager.set(RIDEABLE, Boolean.valueOf(flag));
  }

  public void setIsGhost(boolean flag) {
    this.dataManager.set(GHOST, Boolean.valueOf(flag));
  }



  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    Entity entity = damagesource.getTrueSource();
    if (isBeingRidden() && entity == getRidingEntity()) {
      return false;
    }

    if (super.attackEntityFrom(damagesource, i)) {
      if (entity != null && getIsTamed() && entity instanceof EntityPlayer) {
        return false;
      }
      if (entity != this && entity instanceof EntityLivingBase && this.world.getDifficulty() != EnumDifficulty.PEACEFUL) {
        setAttackTarget((EntityLivingBase)entity);
      }
      return true;
    }
    return false;
  }



  protected SoundEvent getDeathSound() {
    openMouth();
    if (getIsAdult()) {
      return MoCSoundEvents.ENTITY_LION_DEATH;
    }
    return MoCSoundEvents.ENTITY_LION_DEATH_BABY;
  }



  protected Item getDropItem() {
    return (Item)MoCItems.bigcatclaw;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    openMouth();
    if (getIsAdult()) {
      return MoCSoundEvents.ENTITY_LION_HURT;
    }
    return MoCSoundEvents.ENTITY_LION_HURT_BABY;
  }



  protected SoundEvent getAmbientSound() {
    openMouth();
    if (getIsAdult()) {
      return MoCSoundEvents.ENTITY_LION_AMBIENT;
    }
    return MoCSoundEvents.ENTITY_LION_AMBIENT_BABY;
  }



  public void onDeath(DamageSource damagesource) {
    if (!this.world.isRemote) {
      if (getHasAmulet()) {
        MoCTools.dropCustomItem((Entity)this, this.world, new ItemStack((Item)MoCItems.medallion, 1));
        setHasAmulet(false);
      }

      if (getIsTamed() && !getIsGhost() && this.rand.nextInt(4) == 0) {
        spawnGhost();
      }
    }
    super.onDeath(damagesource);
  }

  public void spawnGhost() {
    try {
      EntityLiving templiving = (EntityLiving)EntityList.createEntityByIDFromName(new ResourceLocation(getClazzString().toLowerCase()), this.world);
      if (templiving != null && templiving instanceof MoCEntityBigCat) {
        MoCEntityBigCat ghost = (MoCEntityBigCat)templiving;
        ghost.setPosition(this.posX, this.posY, this.posZ);
        this.world.spawnEntity((Entity)ghost);
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR);
        ghost.setOwnerId(getOwnerId());
        ghost.setTamed(true);
        EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
        if (entityplayer != null) {
          MoCTools.tameWithName(entityplayer, (IMoCTameable)ghost);
        }

        ghost.setAdult(false);
        ghost.setEdad(1);
        ghost.setType(getType());
        ghost.selectType();
        ghost.setIsGhost(true);
      }

    } catch (Exception exception) {}
  }





  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote) {
      if (getAttackTarget() == null) {
        setSprinting(false);
      } else {
        setSprinting(true);
      }
    }

    if (this.world.isRemote) {

      if (this.mouthCounter > 0 && ++this.mouthCounter > 30) {
        this.mouthCounter = 0;
      }

      if (this.rand.nextInt(250) == 0) {
        moveTail();
      }

      if (this.tailCounter > 0 && ++this.tailCounter > 10 && this.rand.nextInt(15) == 0) {
        this.tailCounter = 0;
      }
    } else {

      if (getIsGhost() && getEdad() > 0 && getEdad() < 10 && this.rand.nextInt(5) == 0) {
        setEdad(getEdad() + 1);
        if (getEdad() == 9) {
          setEdad(getMaxEdad());
          setAdult(true);
        }
      }

      if (!getIsGhost() && getEdad() < 10)
      {
        setDead();
      }
    }





    if (!this.world.isRemote && isFlyer() && isOnAir()) {
      float myFlyingSpeed = MoCTools.getMyMovementSpeed((Entity)this);
      int wingFlapFreq = (int)(25.0F - myFlyingSpeed * 10.0F);
      if (!isBeingRidden() || wingFlapFreq < 5) {
        wingFlapFreq = 5;
      }
      if (this.rand.nextInt(wingFlapFreq) == 0) {
        wingFlap();
      }
    }

    if (isFlyer()) {
      if (this.wingFlapCounter > 0 && ++this.wingFlapCounter > 20) {
        this.wingFlapCounter = 0;
      }
      if (!this.world.isRemote && this.wingFlapCounter == 5) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_WINGFLAP);
      }
    }

    if (this.rand.nextInt(300) == 0 && getHealth() <= getMaxHealth() && this.deathTime == 0 && !this.world.isRemote) {
      setHealth(getHealth() + 1.0F);
    }

    if (this.deathTime == 0 && !isMovementCeased()) {
      EntityItem entityitem = getClosestItem((Entity)this, 12.0D, Items.PORKCHOP, Items.FISH);
      if (entityitem != null) {
        float f = entityitem.getDistance((Entity)this);
        if (f > 2.0F) {
          getMyOwnPath((Entity)entityitem, f);
        }
        if (f < 2.0F && entityitem != null && this.deathTime == 0) {
          entityitem.setDead();
          setHealth(getMaxHealth());
          setHasEaten(true);
          MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
        }
      }
    }
  }


  public boolean readytoBreed() {
    return (!getIsGhost() && super.readytoBreed());
  }

  public void wingFlap() {
    if (this.world.isRemote) {
      return;
    }

    if (this.wingFlapCounter == 0) {
      this.wingFlapCounter = 1;
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 3), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
  }


  public boolean isNotScared() {
    return (getIsAdult() || getEdad() > 80);
  }


  public boolean isReadyToHunt() {
    return (getIsAdult() && !isMovementCeased());
  }


  public void updatePassenger(Entity passenger) {
    double dist = getSizeFactor() * 0.1D;
    double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
    double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
    passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
  }


  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
    nbttagcompound.setBoolean("Saddle", getIsRideable());
    nbttagcompound.setBoolean("Sitting", getIsSitting());
    nbttagcompound.setBoolean("Chested", getIsChested());
    nbttagcompound.setBoolean("Ghost", getIsGhost());
    nbttagcompound.setBoolean("Amulet", getHasAmulet());
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
    setSitting(nbttagcompound.getBoolean("Sitting"));
    setIsChested(nbttagcompound.getBoolean("Chested"));
    setIsGhost(nbttagcompound.getBoolean("Ghost"));
    setHasAmulet(nbttagcompound.getBoolean("Amulet"));
    if (getIsChested()) {
      NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
      this.localchest = new MoCAnimalChest("BigCatChest", 18);
      for (int i = 0; i < nbttaglist.tagCount(); i++) {
        NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
        int j = nbttagcompound1.getByte("Slot") & 0xFF;
        if (j >= 0 && j < this.localchest.getSizeInventory()) {
          this.localchest.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
        }
      }
    }
  }



  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && !getIsTamed() && getHasEaten() && !getIsAdult() && stack.getItem() == MoCItems.medallion) {
      if (!this.world.isRemote) {
        setHasAmulet(true);
        MoCTools.tameWithName(player, (IMoCTameable)this);
      }
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
        return true;
      }
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && !getHasAmulet() && stack.getItem() == MoCItems.medallion) {
      if (!this.world.isRemote) {
        setHasAmulet(true);
      }
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
        return true;
      }
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.whip) {
      setSitting(!getIsSitting());
      return true;
    }
    if (!stack.isEmpty() && getIsTamed() && MoCTools.isItemEdibleforCarnivores(stack.getItem())) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      setHealth(getMaxHealth());
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
      setIsHunting(false);
      setHasEaten(true);
      return true;
    }
    if (!stack.isEmpty() && getIsTamed() && !getIsRideable() && getEdad() > 80 && (stack
      .getItem() == Items.SADDLE || stack.getItem() == MoCItems.horsesaddle)) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      setRideable(true);
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
        MoCTools.dropAmulet((IMoCEntity)this, 3, player);
        this.isDead = true;
      }

      return true;
    }


    if (!stack.isEmpty() && getIsTamed() && getIsAdult() && !getIsChested() && stack.getItem() == Item.getItemFromBlock((Block)Blocks.CHEST)) {
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
        this.localchest = new MoCAnimalChest(this.chestName, 18);
      }
      if (!this.world.isRemote) {
        player.displayGUIChest((IInventory)this.localchest);
      }
      return true;
    }

    return super.processInteract(player, hand);
  }


  public float getSizeFactor() {
    return getEdad() * 0.01F;
  }


  public void fall(float f, float f1) {
    if (isFlyer()) {
      return;
    }
    float i = (float)(Math.ceil((f - 3.0F)) / 2.0D);
    if (!this.world.isRemote && i > 0.0F) {
      i /= 2.0F;
      if (i > 1.0F) {
        attackEntityFrom(DamageSource.FALL, i);
      }
      if (isBeingRidden() && i > 1.0F) {
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

  private void openMouth() {
    this.mouthCounter = 1;
  }

  public boolean hasMane() {
    return false;
  }


  public int getTalkInterval() {
    return 400;
  }

  private void moveTail() {
    this.tailCounter = 1;
  }

  public boolean hasSaberTeeth() {
    return false;
  }


  public void performAnimation(int animationType) {
    if (animationType != 0)
    {

      if (animationType == 3)
      {
        this.wingFlapCounter = 1;
      }
    }
  }

  public void makeEntityJump() {
    if (isFlyer()) {
      wingFlap();
    }
    super.makeEntityJump();
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

  public boolean getHasStinger() {
    return false;
  }


  public double getMountedYOffset() {
    double Yfactor = (0.0833D * getEdad() - 2.5D) / 10.0D;
    return this.height * Yfactor;
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


  public int nameYOffset() {
    return (int)((0.445D * getEdad() + 15.0D) * -1.0D);
  }


  public boolean rideableEntity() {
    return true;
  }


  public float getAIMoveSpeed() {
    if (isSprinting()) {
      return 0.37F;
    }
    return 0.18F;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityBigCat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
