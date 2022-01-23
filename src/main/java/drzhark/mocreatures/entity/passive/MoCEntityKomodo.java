package drzhark.mocreatures.entity.passive;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
import drzhark.mocreatures.entity.ai.EntityAIHunt;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCEntityKomodo extends MoCEntityTameableAnimal {
  private int sitCounter;
  public int tailCounter;
  private static final DataParameter<Boolean> RIDEABLE = EntityDataManager.createKey(MoCEntityKomodo.class, DataSerializers.BOOLEAN); public int tongueCounter; public int mouthCounter;

  public MoCEntityKomodo(World world) {
    super(world);
    setSize(1.6F, 0.5F);
    this.texture = "komododragon.png";
    setTamed(false);
    setAdult(false);
    this.stepHeight = 1.0F;

    if (this.rand.nextInt(6) == 0) {
      setEdad(30 + this.rand.nextInt(40));
    } else {
      setEdad(90 + this.rand.nextInt(20));
    }
  }


  protected void initEntityAI() {
    this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.1D));
    this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 1.1D, 4.0D));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(7, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.9D));
    this.tasks.addTask(9, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
    this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.18D);
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(RIDEABLE, Boolean.valueOf(false));
  }


  public void setRideable(boolean flag) {
    this.dataManager.set(RIDEABLE, Boolean.valueOf(flag));
  }


  public boolean getIsRideable() {
    return ((Boolean)this.dataManager.get(RIDEABLE)).booleanValue();
  }


  public boolean getCanSpawnHere() {
    return (getCanSpawnHereCreature() && getCanSpawnHereLiving());
  }


  protected SoundEvent getDeathSound() {
    openmouth();
    return MoCSoundEvents.ENTITY_SNAKE_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    openmouth();
    return MoCSoundEvents.ENTITY_SNAKE_HURT;
  }


  protected SoundEvent getAmbientSound() {
    openmouth();
    return MoCSoundEvents.ENTITY_SNAKE_AMBIENT;
  }


  public int getTalkInterval() {
    return 500;
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (this.sitCounter > 0 && (isBeingRidden() || ++this.sitCounter > 150)) {
      this.sitCounter = 0;
    }
    if (!this.world.isRemote) {
      if (!isSwimming() && !isBeingRidden() && this.sitCounter == 0 && this.rand.nextInt(500) == 0) {
        sit();
      }
    }
    else {

      if (this.tailCounter > 0 && ++this.tailCounter > 60) {
        this.tailCounter = 0;
      }

      if (this.rand.nextInt(100) == 0) {
        this.tailCounter = 1;
      }

      if (this.rand.nextInt(100) == 0) {
        this.tongueCounter = 1;
      }

      if (this.mouthCounter > 0 && ++this.mouthCounter > 30) {
        this.mouthCounter = 0;
      }

      if (this.tongueCounter > 0 && ++this.tongueCounter > 20) {
        this.tongueCounter = 0;
      }
    }
  }

  private void openmouth() {
    this.mouthCounter = 1;
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


  protected void dropFewItems(boolean flag, int x) {
    boolean flag2 = (getEdad() > 90 && this.rand.nextInt(5) == 0);

    if (flag2) {
      int j = this.rand.nextInt(2) + 1;
      for (int k = 0; k < j; k++) {
        entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, 33), 0.0F);
      }
    } else {

      entityDropItem(new ItemStack((Item)MoCItems.hideCroc, 1, 0), 0.0F);
    }
  }


  public float getSizeFactor() {
    if (!getIsAdult()) {
      return getEdad() * 0.01F;
    }
    return 1.2F;
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && getIsTamed() && (getEdad() > 90 || getIsAdult()) && !getIsRideable() && (stack
      .getItem() == Items.SADDLE || stack.getItem() == MoCItems.horsesaddle)) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      setRideable(true);
      return true;
    }

    if (getIsRideable() && getIsTamed() && getEdad() > 90 && !isBeingRidden()) {
      if (!this.world.isRemote && player.startRiding((Entity)this)) {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;
      }

      return true;
    }

    return super.processInteract(player, hand);
  }


  public boolean isMovementCeased() {
    return (getIsSitting() || isBeingRidden());
  }


  public boolean rideableEntity() {
    return true;
  }


  public int nameYOffset() {
    if (getIsAdult()) {
      return -50;
    }
    return -50 + getEdad() / 2;
  }


  public boolean canBreatheUnderwater() {
    return true;
  }


  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
    nbttagcompound.setBoolean("Saddle", getIsRideable());
  }


  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    super.readEntityFromNBT(nbttagcompound);
    setRideable(nbttagcompound.getBoolean("Saddle"));
  }


  public double getMountedYOffset() {
    double yOff = 0.15000000596046448D;
    boolean sit = (this.sitCounter != 0);
    if (sit);


    if (getIsAdult()) {
      return yOff + this.height;
    }
    return (this.height * (120 / getEdad()));
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (super.attackEntityFrom(damagesource, i)) {
      Entity entity = damagesource.getTrueSource();

      if ((entity != null && getIsTamed() && entity instanceof EntityPlayer) || !(entity instanceof EntityLivingBase)) {
        return false;
      }

      if (isBeingRidden() && entity == getRidingEntity()) {
        return false;
      }

      if (entity != this && shouldAttackPlayers()) {
        setAttackTarget((EntityLivingBase)entity);
      }
      return true;
    }
    return false;
  }


  public boolean isMyHealFood(ItemStack stack) {
    return (!stack.isEmpty() && (stack.getItem() == MoCItems.ratRaw || stack.getItem() == MoCItems.rawTurkey));
  }


  public boolean canBeCollidedWith() {
    return !isBeingRidden();
  }


  public void dropMyStuff() {
    if (!this.world.isRemote) {
      dropArmor();
      MoCTools.dropSaddle((MoCEntityAnimal)this, this.world);
    }
  }


  public int getMaxSpawnedInChunk() {
    return 2;
  }


  public boolean canAttackTarget(EntityLivingBase entity) {
    return (!(entity instanceof MoCEntityKomodo) && super.canAttackTarget(entity));
  }


  public int getMaxEdad() {
    return 120;
  }


  public boolean getIsSitting() {
    return (this.sitCounter != 0);
  }


  public boolean isNotScared() {
    return (getEdad() > 70);
  }


  protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
    if (entityIn instanceof EntityPlayer) {
      MoCreatures.poisonPlayer((EntityPlayer)entityIn);
    }
    ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 150, 0));
    super.applyEnchantments(entityLivingBaseIn, entityIn);
  }


  public boolean isReadyToHunt() {
    return (isNotScared() && !isMovementCeased() && !isBeingRidden());
  }


  public boolean isAmphibian() {
    return true;
  }


  public boolean isSwimming() {
    return isInWater();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityKomodo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
