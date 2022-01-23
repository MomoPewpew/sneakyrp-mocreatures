package drzhark.mocreatures.entity.passive;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
import drzhark.mocreatures.entity.ai.EntityAIHunt;
import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.item.MoCEntityEgg;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

public class MoCEntityPetScorpion extends MoCEntityTameableAnimal {
  public static final String[] scorpionNames = new String[] { "Dirt", "Cave", "Nether", "Frost", "Undead" };
  private boolean isPoisoning;
  private int poisontimer;
  public int mouthCounter;
  public int armCounter;
  private int transformCounter;
  private static final DataParameter<Boolean> RIDEABLE = EntityDataManager.createKey(MoCEntityPetScorpion.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> HAS_BABIES = EntityDataManager.createKey(MoCEntityPetScorpion.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> IS_SITTING = EntityDataManager.createKey(MoCEntityPetScorpion.class, DataSerializers.BOOLEAN);

  public MoCEntityPetScorpion(World world) {
    super(world);
    setSize(1.4F, 0.9F);
    this.poisontimer = 0;
    setAdult(false);
    setEdad(20);
    setHasBabies(false);
    this.stepHeight = 20.0F;
  }


  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(1, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 1.2D, 4.0D));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 1.0D, 2.0F, 10.0F));
    this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
  }


  public void selectType() {
    if (getType() == 0) {
      setType(1);
    }
  }


  public ResourceLocation getTexture() {
    boolean saddle = getIsRideable();

    if (this.transformCounter != 0) {
      String newText = saddle ? "scorpionundeadsaddle.png" : "scorpionundead.png";
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
        if (!saddle) {
          return MoCreatures.proxy.getTexture("scorpiondirt.png");
        }
        return MoCreatures.proxy.getTexture("scorpiondirtsaddle.png");
      case 2:
        if (!saddle) {
          return MoCreatures.proxy.getTexture("scorpioncave.png");
        }
        return MoCreatures.proxy.getTexture("scorpioncavesaddle.png");
      case 3:
        if (!saddle) {
          return MoCreatures.proxy.getTexture("scorpionnether.png");
        }
        return MoCreatures.proxy.getTexture("scorpionnethersaddle.png");
      case 4:
        if (!saddle) {
          return MoCreatures.proxy.getTexture("scorpionfrost.png");
        }
        return MoCreatures.proxy.getTexture("scorpionfrostsaddle.png");
      case 5:
        if (!saddle) {
          return MoCreatures.proxy.getTexture("scorpionundead.png");
        }
        return MoCreatures.proxy.getTexture("scorpionundeadsaddle.png");
    }
    return MoCreatures.proxy.getTexture("scorpiondirt.png");
  }



  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(HAS_BABIES, Boolean.valueOf(false));
    this.dataManager.register(IS_SITTING, Boolean.valueOf(false));
    this.dataManager.register(RIDEABLE, Boolean.valueOf(false));
  }


  public void setRideable(boolean flag) {
    this.dataManager.set(RIDEABLE, Boolean.valueOf(flag));
  }


  public boolean getIsRideable() {
    return ((Boolean)this.dataManager.get(RIDEABLE)).booleanValue();
  }

  public boolean getHasBabies() {
    return (getIsAdult() && ((Boolean)this.dataManager.get(HAS_BABIES)).booleanValue());
  }

  public boolean getIsPoisoning() {
    return this.isPoisoning;
  }


  public boolean getIsSitting() {
    return ((Boolean)this.dataManager.get(IS_SITTING)).booleanValue();
  }

  public void setSitting(boolean flag) {
    this.dataManager.set(IS_SITTING, Boolean.valueOf(flag));
  }

  public void setHasBabies(boolean flag) {
    this.dataManager.set(HAS_BABIES, Boolean.valueOf(flag));
  }

  public void setPoisoning(boolean flag) {
    if (flag && !this.world.isRemote) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
    this.isPoisoning = flag;
  }


  public void performAnimation(int animationType) {
    if (animationType == 0) {

      setPoisoning(true);
    } else if (animationType == 1) {

      this.armCounter = 1;
    }
    else if (animationType == 3) {

      this.mouthCounter = 1;
    } else if (animationType == 5) {

      this.transformCounter = 1;
    }
  }


  public boolean isOnLadder() {
    return this.collidedHorizontally;
  }

  public boolean climbing() {
    return (!this.onGround && isOnLadder());
  }


  public void onLivingUpdate() {
    if (!this.onGround && getRidingEntity() != null) {
      this.rotationYaw = (getRidingEntity()).rotationYaw;
    }

    if (this.mouthCounter != 0 && this.mouthCounter++ > 50) {
      this.mouthCounter = 0;
    }

    if (!this.world.isRemote && (this.armCounter == 10 || this.armCounter == 40)) {
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SCORPION_CLAW);
    }

    if (this.armCounter != 0 && this.armCounter++ > 24) {
      this.armCounter = 0;
    }

    if (getIsPoisoning()) {
      this.poisontimer++;
      if (this.poisontimer == 1) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_SCORPION_STING);
      }
      if (this.poisontimer > 50) {
        this.poisontimer = 0;
        setPoisoning(false);
      }
    }

    if (this.transformCounter > 0) {
      if (this.transformCounter == 40) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_TRANSFORM);
      }

      if (++this.transformCounter > 100) {
        this.transformCounter = 0;
        setType(5);
        selectType();
      }
    }
    super.onLivingUpdate();
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (super.attackEntityFrom(damagesource, i)) {
      Entity entity = damagesource.getTrueSource();
      if (!(entity instanceof EntityLivingBase) || (entity != null && entity instanceof EntityPlayer && getIsTamed())) {
        return false;
      }

      if (entity != null && entity != this && shouldAttackPlayers() && getIsAdult()) {
        setAttackTarget((EntityLivingBase)entity);
      }
      return true;
    }
    return false;
  }



  protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
    boolean flag = entityIn instanceof EntityPlayer;
    if (!getIsPoisoning() && this.rand.nextInt(5) == 0 && entityIn instanceof EntityLivingBase) {
      setPoisoning(true);
      if (getType() <= 2) {

        if (flag) {
          MoCreatures.poisonPlayer((EntityPlayer)entityIn);
        }
        ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 70, 0));
      } else if (getType() == 4) {

        if (flag) {
          MoCreatures.freezePlayer((EntityPlayer)entityIn);
        }
        ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 70, 0));
      }
      else if (getType() == 3) {

        if (flag && !this.world.isRemote && !this.world.provider.doesWaterVaporize()) {
          MoCreatures.burnPlayer((EntityPlayer)entityIn);
          ((EntityLivingBase)entityIn).setFire(15);
        }
      }
    } else {
      swingArm();
    }
    super.applyEnchantments(entityLivingBaseIn, entityIn);
  }

  public void swingArm() {
    if (!this.world.isRemote) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
  }

  public boolean swingingTail() {
    return (getIsPoisoning() && this.poisontimer < 15);
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_SCORPION_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_SCORPION_HURT;
  }


  protected SoundEvent getAmbientSound() {
    if (!this.world.isRemote) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 3), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }

    return MoCSoundEvents.ENTITY_SCORPION_AMBIENT;
  }


  protected Item getDropItem() {
    if (!getIsAdult()) {
      return Items.STRING;
    }

    boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);

    switch (getType()) {
      case 1:
        if (flag) {
          return (Item)MoCItems.scorpStingDirt;
        }
        return (Item)MoCItems.chitin;
      case 2:
        if (flag) {
          return (Item)MoCItems.scorpStingCave;
        }
        return (Item)MoCItems.chitinCave;
      case 3:
        if (flag) {
          return (Item)MoCItems.scorpStingNether;
        }
        return (Item)MoCItems.chitinNether;
      case 4:
        if (flag) {
          return (Item)MoCItems.scorpStingFrost;
        }
        return (Item)MoCItems.chitinFrost;
      case 5:
        return Items.ROTTEN_FLESH;
    }

    return Items.STRING;
  }



  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && getIsAdult() && !getIsRideable() && (stack
      .getItem() == Items.SADDLE || stack.getItem() == MoCItems.horsesaddle)) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      setRideable(true);
      return true;
    }

    if (!stack.isEmpty() && stack.getItem() == MoCItems.whip && getIsTamed() && !isBeingRidden()) {
      setSitting(!getIsSitting());
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.essenceundead) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
      } else {
        player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
      }
      transform(5);
      return true;
    }

    if (!stack.isEmpty() && getIsTamed() && stack.getItem() == MoCItems.essencedarkness) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, new ItemStack(Items.GLASS_BOTTLE));
      } else {
        player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
      }
      setHealth(getMaxHealth());
      if (!this.world.isRemote) {
        int i = getType() + 40;
        MoCEntityEgg entityegg = new MoCEntityEgg(this.world, i);
        entityegg.setPosition(player.posX, player.posY, player.posZ);
        player.world.spawnEntity((Entity)entityegg);
        entityegg.motionY += (this.world.rand.nextFloat() * 0.05F);
        entityegg.motionX += ((this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.3F);
        entityegg.motionZ += ((this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.3F);
      }
      return true;
    }

    if (getRidingEntity() == null && getEdad() < 60 && !getIsAdult()) {
      if (startRiding((Entity)player)) {
        this.rotationYaw = player.rotationYaw;
        if (!this.world.isRemote && !getIsTamed()) {
          MoCTools.tameWithName(player, (IMoCTameable)this);
        }
      }

      return true;
    }  if (getRidingEntity() != null) {
      MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
      dismountRidingEntity();
      this.motionX = player.motionX * 5.0D;
      this.motionY = player.motionY / 2.0D + 0.5D;
      this.motionZ = player.motionZ * 5.0D;
      return true;
    }

    if (getIsRideable() && getIsTamed() && getIsAdult() && !isBeingRidden()) {
      player.rotationYaw = this.rotationYaw;
      player.rotationPitch = this.rotationPitch;
      if (!this.world.isRemote) {
        player.startRiding((Entity)this);
      }

      return true;
    }

    return super.processInteract(player, hand);
  }


  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    super.readEntityFromNBT(nbttagcompound);
    setHasBabies(nbttagcompound.getBoolean("Babies"));
    setRideable(nbttagcompound.getBoolean("Saddled"));
  }


  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
    nbttagcompound.setBoolean("Babies", getHasBabies());
    nbttagcompound.setBoolean("Saddled", getIsRideable());
  }


  public int nameYOffset() {
    int n = (int)(1.0D - getEdad() * 0.8D);
    if (n < -60) {
      n = -60;
    }
    if (getIsAdult()) {
      n = -60;
    }
    if (getIsSitting()) {
      n = (int)(n * 0.8D);
    }
    return n;
  }


  protected boolean isMyHealFood(ItemStack itemstack) {
    return (itemstack.getItem() == MoCItems.ratRaw || itemstack.getItem() == MoCItems.ratCooked);
  }


  public int getTalkInterval() {
    return 300;
  }



  public void fall(float f, float f1) {}


  public boolean canBeCollidedWith() {
    return !isBeingRidden();
  }


  public boolean rideableEntity() {
    return true;
  }


  public boolean isMovementCeased() {
    return (isBeingRidden() || getIsSitting());
  }


  public void dropMyStuff() {
    MoCTools.dropSaddle((MoCEntityAnimal)this, this.world);
  }





  public EnumCreatureAttribute getCreatureAttribute() {
    return EnumCreatureAttribute.ARTHROPOD;
  }


  public float getAdjustedYOffset() {
    return 0.2F;
  }


  public int getMaxEdad() {
    return 120;
  }


  public double getMountedYOffset() {
    return this.height * 0.75D - 0.15D;
  }


  public double getYOffset() {
    if (getRidingEntity() instanceof EntityPlayer && getRidingEntity() == MoCreatures.proxy.getPlayer() && this.world.isRemote) {
      return 0.10000000149011612D;
    }

    if (getRidingEntity() instanceof EntityPlayer && this.world.isRemote) {
      return super.getYOffset() + 0.10000000149011612D;
    }
    return super.getYOffset();
  }



  public void updatePassenger(Entity passenger) {
    double dist = 0.2D;
    double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
    double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
    passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
  }


  public boolean isNotScared() {
    return getIsTamed();
  }

  public void transform(int tType) {
    if (!this.world.isRemote) {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), tType), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
    this.transformCounter = 1;
  }


  public boolean isReadyToHunt() {
    return (getIsAdult() && !isMovementCeased());
  }


  public boolean canAttackTarget(EntityLivingBase entity) {
    return (!(entity instanceof MoCEntityFox) && entity.height <= 1.0D && entity.width <= 1.0D);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityPetScorpion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
