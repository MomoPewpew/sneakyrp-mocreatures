package drzhark.mocreatures.entity.ai;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.entity.IMoCEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class EntityAIWanderMoC2
  extends EntityAIBase
{
  private EntityCreature entity;
  private double xPosition;
  private double yPosition;
  private double zPosition;
  private double speed;
  private int executionChance;
  private boolean mustUpdate;

  public EntityAIWanderMoC2(EntityCreature creatureIn, double speedIn) {
    this(creatureIn, speedIn, 120);
  }

  public EntityAIWanderMoC2(EntityCreature creatureIn, double speedIn, int chance) {
    this.entity = creatureIn;
    this.speed = speedIn;
    this.executionChance = chance;
    setMutexBits(1);
  }





  public boolean shouldExecute() {
    if (this.entity instanceof IMoCEntity && ((IMoCEntity)this.entity).isMovementCeased()) {
      return false;
    }
    if (this.entity.isBeingRidden() && !(this.entity instanceof drzhark.mocreatures.entity.ambient.MoCEntityAnt) && !(this.entity instanceof drzhark.mocreatures.entity.MoCEntityMob)) {
      return false;
    }

    if (!this.mustUpdate) {
      if (this.entity.getIdleTime() >= 100)
      {
        return false;
      }

      if (this.entity.getRNG().nextInt(this.executionChance) != 0)
      {
        return false;
      }
    }

    Vec3d vec3 = RandomPositionGeneratorMoCFlyer.findRandomTarget(this.entity, 10, 12);

    if (vec3 != null && this.entity instanceof IMoCEntity && this.entity.getNavigator() instanceof PathNavigateFlyer) {
      int distToFloor = MoCTools.distanceToFloor((Entity)this.entity);
      int finalYHeight = distToFloor + MathHelper.floor(vec3.y - this.entity.posY);
      if (finalYHeight < ((IMoCEntity)this.entity).minFlyingHeight())
      {
        return false;
      }
      if (finalYHeight > ((IMoCEntity)this.entity).maxFlyingHeight())
      {
        return false;
      }
    }


    if (vec3 == null)
    {
      return false;
    }

    this.xPosition = vec3.x;
    this.yPosition = vec3.y;
    this.zPosition = vec3.z;
    this.mustUpdate = false;
    return true;
  }






  public boolean shouldContinueExecuting() {
    return (!this.entity.getNavigator().noPath() && !this.entity.isBeingRidden());
  }






  public void startExecuting() {
    this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
  }





  public void makeUpdate() {
    this.mustUpdate = true;
  }




  public void setExecutionChance(int newchance) {
    this.executionChance = newchance;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIWanderMoC2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
