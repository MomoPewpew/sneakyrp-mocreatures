package drzhark.mocreatures.entity.ai;

import drzhark.mocreatures.entity.IMoCEntity;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIFollowAdult
  extends EntityAIBase
{
  EntityLiving childAnimal;
  EntityLiving parentAnimal;
  double moveSpeed;
  private int delayCounter;

  public EntityAIFollowAdult(EntityLiving animal, double speed) {
    this.childAnimal = animal;
    this.moveSpeed = speed;
  }





  public boolean shouldExecute() {
    if (!(this.childAnimal instanceof IMoCEntity) || ((IMoCEntity)this.childAnimal).getIsAdult()) {
      return false;
    }

    List<EntityLiving> list = this.childAnimal.world.getEntitiesWithinAABB(this.childAnimal.getClass(), this.childAnimal
        .getEntityBoundingBox().expand(8.0D, 4.0D, 8.0D));
    EntityLiving entityliving = null;
    double d0 = Double.MAX_VALUE;
    Iterator<EntityLiving> iterator = list.iterator();

    while (iterator.hasNext()) {
      EntityLiving entityliving1 = iterator.next();

      if (((IMoCEntity)entityliving1).getIsAdult()) {
        double d1 = this.childAnimal.getDistanceSq((Entity)entityliving1);

        if (d1 <= d0) {
          d0 = d1;
          entityliving = entityliving1;
        }
      }
    }

    if (entityliving == null)
      return false;
    if (d0 < 9.0D) {
      return false;
    }
    this.parentAnimal = entityliving;
    return true;
  }







  public boolean shouldContinueExecuting() {
    if (((IMoCEntity)this.childAnimal).getIsAdult())
      return false;
    if (!this.parentAnimal.isEntityAlive()) {
      return false;
    }
    double d0 = this.childAnimal.getDistanceSq((Entity)this.parentAnimal);
    return (d0 >= 9.0D && d0 <= 256.0D);
  }






  public void startExecuting() {
    this.delayCounter = 0;
  }





  public void resetTask() {
    this.parentAnimal = null;
  }





  public void updateTask() {
    if (--this.delayCounter <= 0) {
      this.delayCounter = 10;
      this.childAnimal.getNavigator().tryMoveToEntityLiving((Entity)this.parentAnimal, this.moveSpeed);
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIFollowAdult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
