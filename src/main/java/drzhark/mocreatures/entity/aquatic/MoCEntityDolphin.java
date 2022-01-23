package drzhark.mocreatures.entity.aquatic;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.network.message.MoCMessageHeart;
import drzhark.mocreatures.network.MoCMessageHandler;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCEntityDolphin extends MoCEntityTameableAquatic {
  public int gestationtime;
  private static final DataParameter<Boolean> IS_HUNGRY = EntityDataManager.createKey(MoCEntityDolphin.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> HAS_EATEN = EntityDataManager.createKey(MoCEntityDolphin.class, DataSerializers.BOOLEAN);

  public MoCEntityDolphin(World world) {
    super(world);
    setSize(1.5F, 0.8F);
    setEdad(60 + this.rand.nextInt(100));
  }


  protected void initEntityAI() {
    this.tasks.addTask(1, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.3D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 30));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
  }


  public void selectType() {
    if (getType() == 0) {
      int i = this.rand.nextInt(100);
      if (i <= 35) {
        setType(1);
      } else if (i <= 60) {
        setType(2);
      } else if (i <= 85) {
        setType(3);
      } else if (i <= 96) {
        setType(4);
      } else if (i <= 98) {
        setType(5);
      } else {
        setType(6);
      }
    }
  }



  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("dolphin.png");
      case 2:
        return MoCreatures.proxy.getTexture("dolphin2.png");
      case 3:
        return MoCreatures.proxy.getTexture("dolphin3.png");
      case 4:
        return MoCreatures.proxy.getTexture("dolphin4.png");
      case 5:
        return MoCreatures.proxy.getTexture("dolphin5.png");
      case 6:
        return MoCreatures.proxy.getTexture("dolphin6.png");
    }
    return MoCreatures.proxy.getTexture("dolphin.png");
  }




  public int getMaxTemper() {
    switch (getType()) {
      case 1:
        return 50;
      case 2:
        return 100;
      case 3:
        return 150;
      case 4:
        return 200;
      case 5:
        return 250;
      case 6:
        return 300;
    }
    return 100;
  }


  public int getInitialTemper() {
    switch (getType()) {
      case 1:
        return 50;
      case 2:
        return 100;
      case 3:
        return 150;
      case 4:
        return 200;
      case 5:
        return 250;
      case 6:
        return 300;
    }
    return 50;
  }



  public double getCustomSpeed() {
    switch (getType()) {
      case 1:
        return 1.5D;
      case 2:
        return 2.0D;
      case 3:
        return 2.5D;
      case 4:
        return 3.0D;
      case 5:
        return 3.5D;
      case 6:
        return 4.0D;
    }
    return 1.5D;
  }



  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(IS_HUNGRY, Boolean.valueOf(false));
    this.dataManager.register(HAS_EATEN, Boolean.valueOf(false));
  }

  public boolean getIsHungry() {
    return ((Boolean)this.dataManager.get(IS_HUNGRY)).booleanValue();
  }

  public boolean getHasEaten() {
    return ((Boolean)this.dataManager.get(HAS_EATEN)).booleanValue();
  }

  public void setIsHungry(boolean flag) {
    this.dataManager.set(IS_HUNGRY, Boolean.valueOf(flag));
  }

  public void setHasEaten(boolean flag) {
    this.dataManager.set(HAS_EATEN, Boolean.valueOf(flag));
  }












  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (super.attackEntityFrom(damagesource, i) && this.world.getDifficulty().getDifficultyId()  > 0) {
      Entity entity = damagesource.getTrueSource();
      if (entity instanceof EntityLivingBase) {
        EntityLivingBase entityliving = (EntityLivingBase)entity;
        if (isRidingOrBeingRiddenBy(entity)) {
          return true;
        }
        if (entity != this && getEdad() >= 100) {
          setAttackTarget(entityliving);
        }
        return true;
      }
      return false;
    }
    return false;
  }



  public boolean canBeCollidedWith() {
    return !isBeingRidden();
  }

  private int Genetics(MoCEntityDolphin entitydolphin, MoCEntityDolphin entitydolphin1) {
    if (entitydolphin.getType() == entitydolphin1.getType()) {
      return entitydolphin.getType();
    }
    int i = entitydolphin.getType() + entitydolphin1.getType();
    boolean flag = (this.rand.nextInt(3) == 0);
    boolean flag1 = (this.rand.nextInt(10) == 0);
    if (i < 5 && flag) {
      return i;
    }
    if ((i == 5 || i == 6) && flag1) {
      return i;
    }
    return 0;
  }



  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_DOLPHIN_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_DOLPHIN_HURT;
  }


  protected SoundEvent getAmbientSound() {
    return MoCSoundEvents.ENTITY_DOLPHIN_AMBIENT;
  }


  protected SoundEvent getUpsetSound() {
    return MoCSoundEvents.ENTITY_DOLPHIN_UPSET;
  }


  protected Item getDropItem() {
    return Items.FISH;
  }


  protected float getSoundVolume() {
    return 0.4F;
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    ItemStack stack = player.getHeldItem(hand);
    if (!stack.isEmpty() && stack.getItem() == Items.FISH) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      if (!this.world.isRemote) {
        setTemper(getTemper() + 25);
        if (getTemper() > getMaxTemper()) {
          setTemper(getMaxTemper() - 1);
        }

        if (getHealth() + 15.0F > getMaxHealth()) {
          setHealth(getMaxHealth());
        }

        if (!getIsAdult()) {
          setEdad(getEdad() + 1);
        }
      }

      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);

      return true;
    }
    if (!stack.isEmpty() && stack.getItem() == Items.COOKED_FISH && getIsTamed() && getIsAdult()) {
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
      if (getHealth() + 25.0F > getMaxHealth()) {
        setHealth(getMaxHealth());
      }
      setHasEaten(true);
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
      return true;
    }
    if (!isBeingRidden()) {
      if (!this.world.isRemote && player.startRiding((Entity)this)) {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;
        player.posY = this.posY;
      }

      return true;
    }

    return super.processInteract(player, hand);
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote) {






      if (!getIsAdult() && this.rand.nextInt(50) == 0) {

        setEdad(getEdad() + 1);
        if (getEdad() >= 150)
        {
          setAdult(true);
        }
      }

      if (!isBeingRidden() && this.deathTime == 0 && (!getIsTamed() || getIsHungry())) {
        EntityItem entityitem = getClosestFish((Entity)this, 12.0D);
        if (entityitem != null) {
          MoveToNextEntity((Entity)entityitem);
          EntityItem entityitem1 = getClosestFish((Entity)this, 2.0D);
          if (this.rand.nextInt(20) == 0 && entityitem1 != null && this.deathTime == 0) {

            entityitem1.setDead();
            setTemper(getTemper() + 25);
            if (getTemper() > getMaxTemper()) {
              setTemper(getMaxTemper() - 1);
            }
            setHealth(getMaxHealth());
          }
        }
      }
      if (!ReadyforParenting(this)) {
        return;
      }
      int i = 0;
      List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(8.0D, 2.0D, 8.0D));
      for (int j = 0; j < list.size(); j++) {
        Entity entity = list.get(j);
        if (entity instanceof MoCEntityDolphin) {
          i++;
        }
      }

      if (i > 1) {
        return;
      }
      List<Entity> list1 = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
      for (int k = 0; k < list1.size(); k++) {
        Entity entity1 = list1.get(k);
        if (entity1 instanceof MoCEntityDolphin && entity1 != this) {


          MoCEntityDolphin entitydolphin = (MoCEntityDolphin)entity1;
          if (ReadyforParenting(this) && ReadyforParenting(entitydolphin)) {


            if (this.rand.nextInt(100) == 0) {
              this.gestationtime++;
            }
            if (this.gestationtime % 3 == 0) {
              MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHeart(getEntityId()), new NetworkRegistry.TargetPoint(this.world.provider
                    .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
            }
            if (this.gestationtime > 50) {


              MoCEntityDolphin babydolphin = new MoCEntityDolphin(this.world);
              babydolphin.setPosition(this.posX, this.posY, this.posZ);
              if (this.world.spawnEntity((Entity)babydolphin)) {
                MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
                setHasEaten(false);
                entitydolphin.setHasEaten(false);
                this.gestationtime = 0;
                entitydolphin.gestationtime = 0;
                int l = Genetics(this, entitydolphin);
                babydolphin.setEdad(35);
                babydolphin.setAdult(false);
                babydolphin.setOwnerId(getOwnerId());
                babydolphin.setTamed(true);
                EntityPlayer entityplayer = this.world.getPlayerEntityByUUID(getOwnerId());
                if (entityplayer != null) {
                  MoCTools.tameWithName(entityplayer, (IMoCTameable)babydolphin);
                }
                babydolphin.setTypeInt(l);
                break;
              }
            }
          }
        }
      }
    }  } public boolean ReadyforParenting(MoCEntityDolphin entitydolphin) {
    EntityLivingBase passenger = (EntityLivingBase)getControllingPassenger();
    return (entitydolphin.getRidingEntity() == null && passenger == null && entitydolphin.getIsTamed() && entitydolphin
      .getHasEaten() && entitydolphin.getIsAdult());
  }


  public void setDead() {
    if (!this.world.isRemote && getIsTamed() && getHealth() > 0.0F) {
      return;
    }
    super.setDead();
  }




  public int getMaxSpawnedInChunk() {
    return 1;
  }


  protected boolean usesNewAI() {
    return true;
  }


  public float getAIMoveSpeed() {
    return 0.15F;
  }


  public boolean isMovementCeased() {
    return !isInWater();
  }


  protected double minDivingDepth() {
    return 0.4D;
  }


  protected double maxDivingDepth() {
    return 4.0D;
  }


  public int getMaxEdad() {
    return 160;
  }


  public void updatePassenger(Entity passenger) {
    double dist = 0.8D;
    double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
    double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
    passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
  }


  public double getMountedYOffset() {
    return (getEdad() * 0.01F) * this.height * 0.3D;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityDolphin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
