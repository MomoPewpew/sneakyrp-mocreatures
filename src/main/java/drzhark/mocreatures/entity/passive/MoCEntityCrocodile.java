package drzhark.mocreatures.entity.passive;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
import drzhark.mocreatures.entity.ai.EntityAIHunt;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.block.material.Material;
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
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class MoCEntityCrocodile
  extends MoCEntityTameableAnimal {
  public float biteProgress;
  public float spin;
  private static final DataParameter<Boolean> IS_RESTING = EntityDataManager.createKey(MoCEntityCrocodile.class, DataSerializers.BOOLEAN); public int spinInt; private boolean waterbound;
  private static final DataParameter<Boolean> EATING_PREY = EntityDataManager.createKey(MoCEntityCrocodile.class, DataSerializers.BOOLEAN);
  private static final DataParameter<Boolean> IS_BITING = EntityDataManager.createKey(MoCEntityCrocodile.class, DataSerializers.BOOLEAN);

  public MoCEntityCrocodile(World world) {
    super(world);
    this.texture = "crocodile.png";
    setSize(1.4F, 0.6F);
    setEdad(50 + this.rand.nextInt(50));
    setTamed(false);
  }


  protected void initEntityAI() {
    this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 0.8D, 4.0D));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(7, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.9D));
    this.tasks.addTask(9, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
    this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }



  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(IS_RESTING, Boolean.valueOf(false));
    this.dataManager.register(EATING_PREY, Boolean.valueOf(false));
    this.dataManager.register(IS_BITING, Boolean.valueOf(false));
  }

  public boolean getIsBiting() {
    return ((Boolean)this.dataManager.get(IS_BITING)).booleanValue();
  }

  public boolean getIsSitting() {
    return ((Boolean)this.dataManager.get(IS_RESTING)).booleanValue();
  }

  public boolean getHasCaughtPrey() {
    return ((Boolean)this.dataManager.get(EATING_PREY)).booleanValue();
  }

  public void setBiting(boolean flag) {
    this.dataManager.set(IS_BITING, Boolean.valueOf(flag));
  }

  public void setIsSitting(boolean flag) {
    this.dataManager.set(IS_RESTING, Boolean.valueOf(flag));
  }

  public void setHasCaughtPrey(boolean flag) {
    this.dataManager.set(EATING_PREY, Boolean.valueOf(flag));
  }



  protected void jump() {
    if (isSwimming()) {
      if (getHasCaughtPrey()) {
        return;
      }

      this.motionY = 0.3D;
      this.isAirBorne = true;
    }
    else if (getAttackTarget() != null || getHasCaughtPrey()) {
      super.jump();
    }
  }


  public boolean isMovementCeased() {
    return getIsSitting();
  }


  public void onLivingUpdate() {
    if (getIsSitting()) {
      this.rotationPitch = -5.0F;
      if (!isSwimming() && this.biteProgress < 0.3F && this.rand.nextInt(5) == 0) {
        this.biteProgress += 0.005F;
      }
      if (getAttackTarget() != null) {
        setIsSitting(false);
      }
      if ((!this.world.isRemote && getAttackTarget() != null) || isSwimming() || getHasCaughtPrey() || this.rand.nextInt(500) == 0)
      {
        setIsSitting(false);
        this.biteProgress = 0.0F;
      }

    }
    else if (!this.world.isRemote && this.rand.nextInt(500) == 0 && getAttackTarget() == null && !getHasCaughtPrey() && !isSwimming()) {
      setIsSitting(true);
      getNavigator().clearPath();
    }



    if (this.rand.nextInt(500) == 0 && !getHasCaughtPrey() && !getIsSitting()) {
      crocBite();
    }


    if (!this.world.isRemote && this.rand.nextInt(500) == 0 && !this.waterbound && !getIsSitting() && !isSwimming()) {
      MoCTools.MoveToWater((EntityCreature)this);
    }

    if (this.waterbound) {
      if (!isInsideOfMaterial(Material.WATER)) {
        MoCTools.MoveToWater((EntityCreature)this);
      } else {
        this.waterbound = false;
      }
    }

    if (getHasCaughtPrey()) {
      if (isBeingRidden()) {
        setAttackTarget(null);

        this.biteProgress = 0.4F;
        setIsSitting(false);

        if (!isInsideOfMaterial(Material.WATER)) {
          this.waterbound = true;
          if (getRidingEntity() instanceof EntityLiving && ((EntityLivingBase)getRidingEntity()).getHealth() > 0.0F) {
            ((EntityLivingBase)getRidingEntity()).deathTime = 0;
          }

          if (!this.world.isRemote && this.rand.nextInt(50) == 0) {
            getRidingEntity().attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 2.0F);
          }
        }
      } else {
        setHasCaughtPrey(false);
        this.biteProgress = 0.0F;
        this.waterbound = false;
      }

      if (isSpinning()) {
        this.spinInt += 3;
        if (this.spinInt % 20 == 0) {
          MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_CROCODILE_ROLL);
        }
        if (this.spinInt > 80) {
          this.spinInt = 0;
          getRidingEntity().attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 4.0F);
        }




        if (this.world.isRemote || !isBeingRidden() || getRidingEntity() instanceof EntityPlayer);
      }
    }




    super.onLivingUpdate();
  }


  public boolean isNotScared() {
    return true;
  }

  public void crocBite() {
    if (!getIsBiting()) {
      setBiting(true);
      this.biteProgress = 0.0F;
    }
  }


  public void onUpdate() {
    if (getIsBiting() && !getHasCaughtPrey()) {

      this.biteProgress += 0.1F;
      if (this.biteProgress == 0.4F) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_CROCODILE_JAWSNAP);
      }
      if (this.biteProgress > 0.6F) {

        setBiting(false);
        this.biteProgress = 0.0F;
      }
    }

    super.onUpdate();
  }


  public boolean attackEntityAsMob(Entity entityIn) {
    if (getHasCaughtPrey()) {
      return false;
    }








    crocBite();
    setHasCaughtPrey(false);
    return super.attackEntityAsMob(entityIn);
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (isBeingRidden()) {

      Entity entity = damagesource.getTrueSource();
      if (entity != null && getRidingEntity() == entity) {
        if (this.rand.nextInt(2) != 0) {
          return false;
        }
        unMount();
      }
    }


    if (super.attackEntityFrom(damagesource, i)) {
      Entity entity = damagesource.getTrueSource();

      if (isBeingRidden() && getRidingEntity() == entity &&
        entity != this && entity instanceof EntityLivingBase && shouldAttackPlayers()) {
        setAttackTarget((EntityLivingBase)entity);
      }

      return true;
    }
    return false;
  }



  public boolean canAttackTarget(EntityLivingBase entity) {
    return !(entity instanceof MoCEntityCrocodile);
  }


  public void updatePassenger(Entity passenger) {
    if (!isBeingRidden()) {
      return;
    }
    int direction = 1;

    double dist = (getEdad() * 0.01F + passenger.width) - 0.4D;
    double newPosX = this.posX - dist * Math.cos((MoCTools.realAngle(this.rotationYaw - 90.0F) / 57.29578F));
    double newPosZ = this.posZ - dist * Math.sin((MoCTools.realAngle(this.rotationYaw - 90.0F) / 57.29578F));
    passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);

    if (this.spinInt > 40) {
      direction = -1;
    } else {
      direction = 1;
    }

    ((EntityLivingBase)passenger).renderYawOffset = this.rotationYaw * direction;
    ((EntityLivingBase)passenger).prevRenderYawOffset = this.rotationYaw * direction;
  }


  public double getMountedYOffset() {
    return this.height * 0.35D;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_CROCODILE_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_CROCODILE_HURT;
  }


  protected SoundEvent getAmbientSound() {
    if (getIsSitting()) {
      return MoCSoundEvents.ENTITY_CROCODILE_RESTING;
    }
    return MoCSoundEvents.ENTITY_CROCODILE_AMBIENT;
  }


  protected Item getDropItem() {
    return (Item)MoCItems.hideCroc;
  }

  public boolean isSpinning() {
    return (getHasCaughtPrey() && isBeingRidden() && isSwimming());
  }



  public void onDeath(DamageSource damagesource) {
    unMount();
    MoCTools.checkForTwistedEntities(this.world);
    super.onDeath(damagesource);
  }


  public void unMount() {
    if (isBeingRidden()) {
      if (getRidingEntity() instanceof EntityLiving && ((EntityLivingBase)getRidingEntity()).getHealth() > 0.0F) {
        ((EntityLivingBase)getRidingEntity()).deathTime = 0;
      }

      dismountRidingEntity();
      setHasCaughtPrey(false);
    }
  }


  public int getTalkInterval() {
    return 400;
  }


  public boolean isAmphibian() {
    return true;
  }


  public boolean isSwimming() {
    return isInWater();
  }


  public boolean isReadyToHunt() {
    return (isNotScared() && !isMovementCeased() && !isBeingRidden() && !getHasCaughtPrey());
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityCrocodile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
