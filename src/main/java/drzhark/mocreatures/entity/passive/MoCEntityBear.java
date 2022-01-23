package drzhark.mocreatures.entity.passive;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.entity.MoCEntityAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
import drzhark.mocreatures.entity.ai.EntityAIHunt;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.inventory.MoCAnimalChest;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCEntityBear extends MoCEntityTameableAnimal {
  public int mouthCounter;
  private static final DataParameter<Integer> BEAR_STATE = EntityDataManager.createKey(MoCEntityBear.class, DataSerializers.VARINT); private int attackCounter; private int standingCounter;
  private static final DataParameter<Boolean> RIDEABLE = EntityDataManager.createKey(MoCEntityBear.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> CHESTED = EntityDataManager.createKey(MoCEntityBear.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> GHOST = EntityDataManager.createKey(MoCEntityBear.class, DataSerializers.BOOLEAN);
  public MoCAnimalChest localchest;
  public ItemStack localstack;

  public MoCEntityBear(World world) {
    super(world);
    setSize(1.2F, 1.5F);
    setEdad(55);
    if (this.rand.nextInt(4) == 0) {
      setAdult(false);
    } else {
      setAdult(true);
    }
    this.stepHeight = 1.0F;
  }


  protected void initEntityAI() {
    this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.0D));
    this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 1.0D, 2.0F, 10.0F));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
    this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
    this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getAttackStrength());
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
  }






  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(BEAR_STATE, Integer.valueOf(0));
    this.dataManager.register(RIDEABLE, Boolean.valueOf(false));
    this.dataManager.register(CHESTED, Boolean.valueOf(false));
    this.dataManager.register(GHOST, Boolean.valueOf(false));
  }






  public int getBearState() {
    return ((Integer)this.dataManager.get(BEAR_STATE)).intValue();
  }

  public void setBearState(int i) {
    this.dataManager.set(BEAR_STATE, Integer.valueOf(i));
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

  public void setIsChested(boolean flag) {
    this.dataManager.set(CHESTED, Boolean.valueOf(flag));
  }

  public void setRideable(boolean flag) {
    this.dataManager.set(RIDEABLE, Boolean.valueOf(flag));
  }

  public void setIsGhost(boolean flag) {
    this.dataManager.set(GHOST, Boolean.valueOf(flag));
  }


  public void selectType() {
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(calculateMaxHealth());
    setHealth(getMaxHealth());
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getAttackStrength());
    getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(getAttackRange());
    if (getIsAdult()) {
      setEdad(getMaxEdad());
    }
  }







  public float getBearSize() {
    return 1.0F;
  }

  public float calculateMaxHealth() {
    return 30.0F;
  }






  public double getAttackRange() {
    return 8.0D;
  }






  public int getAttackStrength() {
    return 2;
  }


  public boolean attackEntityAsMob(Entity entityIn) {
    startAttack();
    return super.attackEntityAsMob(entityIn);
  }





  public boolean isMovementCeased() {
    return (getBearState() == 2);
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (super.attackEntityFrom(damagesource, i)) {
      Entity entity = damagesource.getTrueSource();
      if (isRidingOrBeingRiddenBy(entity)) {
        return true;
      }
      if (entity != this && entity instanceof EntityLivingBase && shouldAttackPlayers()) {
        setAttackTarget((EntityLivingBase)entity);
      }
      return true;
    }
    return false;
  }



  public boolean isNotScared() {
    return getIsAdult();
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (this.mouthCounter > 0 && ++this.mouthCounter > 20) {
      this.mouthCounter = 0;
    }
    if (this.attackCounter > 0 && ++this.attackCounter > 9) {
      this.attackCounter = 0;
    }
    if (!this.world.isRemote && !getIsAdult() && getEdad() < 80 && this.rand.nextInt(300) == 0) {
      setBearState(2);
    }



    if (!this.world.isRemote && getBearState() == 2 && !getIsTamed() && this.rand.nextInt(800) == 0) {
      setBearState(0);
    }
    if (!this.world.isRemote && getBearState() == 2 && !getIsTamed() && !getNavigator().noPath()) {
      setBearState(0);
    }
    if (!this.world.isRemote && this.standingCounter > 0 && ++this.standingCounter > 100) {
      this.standingCounter = 0;
      setBearState(0);
    }



    if (!this.world.isRemote && !getIsTamed() && getIsStanding() &&
      getBearState() != 2 && getIsAdult() && this.rand.nextInt(200) == 0 && shouldAttackPlayers()) {
      EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 4.0D);
      if (entityplayer1 != null && canEntityBeSeen((Entity)entityplayer1) && !entityplayer1.capabilities.disableDamage) {
        setStand();
        setBearState(1);
      }
    }

    if (!this.world.isRemote && getType() == 3 && this.deathTime == 0 && getBearState() != 2) {
      EntityItem entityitem = getClosestItem((Entity)this, 12.0D, Items.REEDS, Items.SUGAR);
      if (entityitem != null) {

        float f = entityitem.getDistance((Entity)this);
        if (f > 2.0F) {
          getMyOwnPath((Entity)entityitem, f);
        }
        if (f < 2.0F && entityitem != null && this.deathTime == 0) {
          entityitem.setDead();
          MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
          setHealth(getMaxHealth());
        }
      }
    }
  }



  public boolean canAttackTarget(EntityLivingBase entity) {
    return (!(entity instanceof MoCEntityBear) && entity.height <= 1.0D && entity.width <= 1.0D);
  }


  protected Item getDropItem() {
    return (Item)MoCItems.animalHide;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_BEAR_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    openMouth();
    return MoCSoundEvents.ENTITY_BEAR_HURT;
  }


  protected SoundEvent getAmbientSound() {
    openMouth();
    return MoCSoundEvents.ENTITY_BEAR_AMBIENT;
  }

  private void openMouth() {
    this.mouthCounter = 1;
  }

  public float getAttackSwing() {
    if (this.attackCounter == 0)
      return 0.0F;
    return 1.5F + (this.attackCounter / 10.0F - 10.0F) * 5.0F;
  }

  private void startAttack() {
    if (!this.world.isRemote && this.attackCounter == 0 && getBearState() == 1) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
      this.attackCounter = 1;
    }
  }


  public void performAnimation(int i) {
    this.attackCounter = 1;
  }

  protected void eatingAnimal() {
    openMouth();
    MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
  }


  public double getCustomSpeed() {
    if (getBearState() == 2) {
      return 0.0D;
    }
    return super.getCustomSpeed();
  }


  public boolean isReadyToHunt() {
    return (getIsAdult() && !isMovementCeased());
  }

  public boolean getIsStanding() {
    return (this.standingCounter != 0);
  }

  public void setStand() {
    this.standingCounter = 1;
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && getIsTamed() && !getIsRideable() && getEdad() > 80 && (stack
      .getItem() == Items.SADDLE || stack.getItem() == MoCItems.horsesaddle)) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      setRideable(true);
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
        this.localchest = new MoCAnimalChest("BigBearChest", 18);
      }
      if (!this.world.isRemote) {
        player.displayGUIChest((IInventory)this.localchest);
      }
      return true;
    }

    return super.processInteract(player, hand);
  }


  public double getMountedYOffset() {
    double Yfactor = (0.086D * getEdad() - 2.5D) / 10.0D;
    return this.height * Yfactor;
  }


  public int nameYOffset() {
    return (int)((0.445D * getEdad() + 15.0D) * -1.0D);
  }


  public boolean rideableEntity() {
    return true;
  }


  public float getSizeFactor() {
    return getEdad() * 0.01F;
  }


  public void updatePassenger(Entity passenger) {
    double dist = getSizeFactor() * 0.1D;
    double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
    double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
    passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
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


  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
    nbttagcompound.setBoolean("Saddle", getIsRideable());
    nbttagcompound.setBoolean("Chested", getIsChested());
    nbttagcompound.setBoolean("Ghost", getIsGhost());
    nbttagcompound.setInteger("BearState", getBearState());
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
    setIsGhost(nbttagcompound.getBoolean("Ghost"));
    setBearState(nbttagcompound.getInteger("BearState"));
    if (getIsChested()) {
      NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
      this.localchest = new MoCAnimalChest("BigBearChest", 18);
      for (int i = 0; i < nbttaglist.tagCount(); i++) {
        NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
        int j = nbttagcompound1.getByte("Slot") & 0xFF;
        if (j >= 0 && j < this.localchest.getSizeInventory())
          this.localchest.setInventorySlotContents(j, new ItemStack(nbttagcompound1));
      }
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
