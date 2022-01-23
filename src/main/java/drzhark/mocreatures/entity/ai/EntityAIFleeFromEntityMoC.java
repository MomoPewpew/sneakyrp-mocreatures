package drzhark.mocreatures.entity.ai;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import drzhark.mocreatures.entity.IMoCEntity;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.Vec3d;


public class EntityAIFleeFromEntityMoC
  extends EntityAIBase
{
  public final Predicate<Entity> canBeSeenSelector = new Predicate<Entity>()
    {
      public boolean isApplicable(Entity entityIn) {
        return (entityIn.isEntityAlive() && EntityAIFleeFromEntityMoC.this.entity.getEntitySenses().canSee(entityIn));
      }


      public boolean apply(Entity p_apply_1_) {
        return isApplicable(p_apply_1_);
      }
    };


  protected EntityCreature entity;
  private double farSpeed;
  private double nearSpeed;
  protected Entity closestLivingEntity;
  private float avoidDistance;
  private Predicate<Entity> avoidTargetSelector;
  private double randPosX;
  private double randPosY;
  private double randPosZ;

  public EntityAIFleeFromEntityMoC(EntityCreature creature, Predicate<Entity> targetSelector, float searchDistance, double farSpeedIn, double nearSpeedIn) {
    this.entity = creature;
    this.avoidTargetSelector = targetSelector;
    this.avoidDistance = searchDistance;
    this.farSpeed = farSpeedIn;
    this.nearSpeed = nearSpeedIn;
    setMutexBits(1);
  }






  public boolean shouldExecute() {
    if (this.entity instanceof IMoCEntity && ((IMoCEntity)this.entity).isNotScared()) {
      return false;
    }

    if (this.entity instanceof drzhark.mocreatures.entity.MoCEntityAquatic && !this.entity.isInWater()) {
      return false;
    }


    List<Entity> list = this.entity.world.getEntitiesInAABBexcluding((Entity)this.entity, this.entity
        .getEntityBoundingBox().expand(this.avoidDistance, 3.0D, this.avoidDistance),
        Predicates.and(new Predicate[] { EntitySelectors.NOT_SPECTATING, this.canBeSeenSelector, this.avoidTargetSelector }));

    if (list.isEmpty()) {
      return false;
    }
    this.closestLivingEntity = list.get(0);

    Vec3d vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.entity, 16, 7, new Vec3d(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));


    if (vec3 == null)
      return false;
    if (this.closestLivingEntity.getDistanceSq(vec3.x, vec3.y, vec3.z) < this.closestLivingEntity
      .getDistanceSq((Entity)this.entity)) {
      return false;
    }
    this.randPosX = vec3.x;
    this.randPosY = vec3.y;
    this.randPosZ = vec3.z;
    return true;
  }







  public void startExecuting() {
    this.entity.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.nearSpeed);
  }





  public boolean shouldContinueExecuting() {
    return !this.entity.getNavigator().noPath();
  }





  public void resetTask() {
    this.closestLivingEntity = null;
  }





  public void updateTask() {
    if (this.entity.getDistanceSq(this.closestLivingEntity) < 8.0D) {
      this.entity.getNavigator().setSpeed(this.nearSpeed);
    } else {
      this.entity.getNavigator().setSpeed(this.farSpeed);
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIFleeFromEntityMoC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
