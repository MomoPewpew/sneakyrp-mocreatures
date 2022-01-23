package drzhark.mocreatures.entity.passive;

import com.google.common.base.Predicate;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class MoCEntityDeer extends MoCEntityTameableAnimal {
  public MoCEntityDeer(World world) {
    super(world);
    setEdad(75);
    setSize(0.9F, 1.3F);
    setAdult(true);
    setTamed(false);
  }


  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(1, (EntityAIBase)new EntityAIFleeFromEntityMoC((EntityCreature)this, new Predicate<Entity>() {
            public boolean apply(Entity entity) {
              return (!(entity instanceof MoCEntityDeer) && (entity.height > 0.8F || entity.width > 0.8F));
            }
          },  6.0F, getMyAISpeed(), getMyAISpeed() * 1.2D));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIPanic((EntityCreature)this, getMyAISpeed() * 1.2D));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, getMyAISpeed()));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, getMyAISpeed()));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
  }
  private int readyToJumpTimer;

  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
  }


  public void selectType() {
    if (getType() == 0) {
      int i = this.rand.nextInt(100);
      if (i <= 20) {
        setType(1);
      } else if (i <= 70) {
        setType(2);
      } else {
        setAdult(false);
        setType(3);
      }
    }
  }



  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("deer.png");
      case 2:
        return MoCreatures.proxy.getTexture("deerf.png");
      case 3:
        setAdult(false);
        return MoCreatures.proxy.getTexture("deerb.png");
    }

    return MoCreatures.proxy.getTexture("deer.png");
  }



  public void fall(float f, float f1) {}



  public boolean entitiesToInclude(Entity entity) {
    return (!(entity instanceof MoCEntityDeer) && super.entitiesToInclude(entity));
  }


  protected Item getDropItem() {
    return (Item)MoCItems.fur;
  }


  protected SoundEvent getDeathSound() {
    return MoCSoundEvents.ENTITY_DEER_DEATH;
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_DEER_HURT;
  }


  protected SoundEvent getAmbientSound() {
    if (!getIsAdult()) {
      return MoCSoundEvents.ENTITY_DEER_AMBIENT_BABY;
    }
    return MoCSoundEvents.ENTITY_DEER_AMBIENT;
  }







  public double getMyAISpeed() {
    return 1.1D;
  }


  public int getTalkInterval() {
    return 400;
  }


  public int getMaxEdad() {
    return 130;
  }


  public void setAdult(boolean flag) {
    if (!this.world.isRemote) {
      setType(this.rand.nextInt(1));
    }
    super.setAdult(flag);
  }


  public boolean getIsAdult() {
    return (getType() != 3 && super.getIsAdult());
  }


  public void onUpdate() {
    super.onUpdate();

    if (!this.world.isRemote)
    {
      if (this.onGround && --this.readyToJumpTimer <= 0 &&
        MoCTools.getMyMovementSpeed((Entity)this) > 0.17F) {
        float velX = (float)(0.5D * Math.cos((MoCTools.realAngle(this.rotationYaw - 90.0F) / 57.29578F)));
        float velZ = (float)(0.5D * Math.sin((MoCTools.realAngle(this.rotationYaw - 90.0F) / 57.29578F)));
        this.motionX -= velX;
        this.motionZ -= velZ;
        this.motionY = 0.5D;
        this.readyToJumpTimer = this.rand.nextInt(10) + 20;
      }
    }
  }



  public float pitchRotationOffset() {
    if (!this.onGround && MoCTools.getMyMovementSpeed((Entity)this) > 0.08F) {
      if (this.motionY > 0.5D) {
        return 25.0F;
      }
      if (this.motionY < -0.5D) {
        return -25.0F;
      }
      return (float)(this.motionY * 70.0D);
    }
    return 0.0F;
  }


  public float getSizeFactor() {
    if (getType() == 1) {
      return 1.6F;
    }
    if (getType() == 2) {
      return 1.3F;
    }
    return getEdad() * 0.01F;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityDeer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
