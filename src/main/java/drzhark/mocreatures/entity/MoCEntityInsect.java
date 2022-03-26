package drzhark.mocreatures.entity;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCEntityInsect extends MoCEntityAmbient {
  private int climbCounter;
  protected EntityAIWanderMoC2 wander;
  private static final DataParameter<Boolean> IS_FLYING = EntityDataManager.createKey(MoCEntityInsect.class, DataSerializers.BOOLEAN);

  public MoCEntityInsect(World world) {
    super(world);
    setSize(0.2F, 0.2F);
    this.tasks.addTask(2, (EntityAIBase)(this.wander = new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80)));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(IS_FLYING, Boolean.valueOf(false));
  }


  public boolean isFlyer() {
    return false;
  }


  public boolean isFlyingAlone() {
    return getIsFlying();
  }

  public boolean getIsFlying() {
    return ((Boolean)this.dataManager.get(IS_FLYING)).booleanValue();
  }

  public void setIsFlying(boolean flag) {
    this.dataManager.set(IS_FLYING, Boolean.valueOf(flag));
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote) {
      if (!getIsFlying() && isOnLadder() && !this.onGround) {
        MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
              .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
      }

      if (isFlyer() && !getIsFlying() && this.rand.nextInt(getFlyingFreq()) == 0) {
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 4.0D, 4.0D));
        for (int i = 0; i < list.size(); i++) {
          Entity entity1 = list.get(i);
          if (entity1 instanceof EntityLivingBase)
          {

            if (((EntityLivingBase)entity1).width >= 0.4F && ((EntityLivingBase)entity1).height >= 0.4F && canEntityBeSeen(entity1)) {
              setIsFlying(true);
              this.wander.makeUpdate();
            }
          }
        }
      }
      if (isFlyer() && !getIsFlying() && this.rand.nextInt(200) == 0) {
        setIsFlying(true);
        this.wander.makeUpdate();
      }

      if (isAttractedToLight() && this.rand.nextInt(50) == 0) {
        int[] ai = MoCTools.ReturnNearestBlockCoord((Entity)this, Blocks.TORCH, Double.valueOf(8.0D));
        if (ai[0] > -1000) {
          getNavigator().tryMoveToXYZ(ai[0], ai[1], ai[2], 1.0D);
        }
      }


      if (getIsFlying() && getNavigator().noPath() && !isMovementCeased() && getAttackTarget() == null) {
        this.wander.makeUpdate();

      }

    }
    else if (this.climbCounter > 0 && ++this.climbCounter > 8) {
      this.climbCounter = 0;
    }
  }







  public boolean isAttractedToLight() {
    return false;
  }


  public void performAnimation(int animationType) {
    if (animationType == 1)
    {
      this.climbCounter = 1;
    }
  }



  public void fall(float f, float f1) {}



  // public boolean getCanSpawnHere() {
  //   return (getCanSpawnHereAnimal() && getCanSpawnHereCreature());
  // }


  public float getSizeFactor() {
    return 1.0F;
  }


  public int getMaxSpawnedInChunk() {
    return 4;
  }


  public boolean isOnLadder() {
    return this.collidedHorizontally;
  }

  public boolean climbing() {
    return (this.climbCounter != 0);
  }



  protected void jump() {}


  protected boolean canTriggerWalking() {
    return false;
  }

  protected int getFlyingFreq() {
    return 20;
  }


  public float rollRotationOffset() {
    return 0.0F;
  }





  public EnumCreatureAttribute getCreatureAttribute() {
    return EnumCreatureAttribute.ARTHROPOD;
  }





  public PathNavigate getNavigator() {
    if (getIsFlying()) {
      return this.navigatorFlyer;
    }
    return this.navigator;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\MoCEntityInsect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
