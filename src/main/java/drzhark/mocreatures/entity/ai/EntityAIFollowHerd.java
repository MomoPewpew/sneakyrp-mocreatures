package drzhark.mocreatures.entity.ai;

import drzhark.mocreatures.entity.IMoCEntity;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIFollowHerd
  extends EntityAIBase
{
  EntityLiving theAnimal;
  EntityLiving herdAnimal;
  double moveSpeed;
  double maxRange;
  double minRange;
  private int delayCounter;
  private int executionChance;

  public EntityAIFollowHerd(EntityLiving animal, double speed, double minRangeIn, double maxRangeIn, int chance) {
    this.theAnimal = animal;
    this.moveSpeed = speed;
    this.minRange = minRangeIn;
    this.maxRange = maxRangeIn;
    this.executionChance = chance;
  }

  public EntityAIFollowHerd(EntityLiving animal, double speed) {
    this(animal, speed, 4.0D, 20.0D, 120);
  }






  public boolean shouldExecute() {
    if (this.theAnimal.getRNG().nextInt(this.executionChance) != 0) {
      return false;
    }


    List<EntityLiving> list = this.theAnimal.world.getEntitiesWithinAABB(this.theAnimal.getClass(), this.theAnimal
        .getEntityBoundingBox().expand(this.maxRange, 4.0D, this.maxRange));
    EntityLiving entityliving = null;
    double d0 = Double.MAX_VALUE;
    Iterator<EntityLiving> iterator = list.iterator();

    while (iterator.hasNext()) {
      EntityLiving entityliving1 = iterator.next();
      double d1 = this.theAnimal.getDistanceSq((Entity)entityliving1);
      if (d1 >= this.minRange && this.theAnimal != entityliving1) {
        d0 = d1;
        entityliving = entityliving1;
      }
    }


    if (entityliving == null)
    {
      return false;
    }
    if (d0 < this.maxRange)
    {

      return false;
    }
    this.herdAnimal = entityliving;

    return true;
  }






  public boolean shouldContinueExecuting() {
    if (this.theAnimal instanceof IMoCEntity && ((IMoCEntity)this.theAnimal).isMovementCeased())
      return false;
    if (!this.herdAnimal.isEntityAlive()) {
      return false;
    }
    double d0 = this.theAnimal.getDistanceSq((Entity)this.herdAnimal);
    return (d0 >= this.minRange && d0 <= this.maxRange);
  }






  public void startExecuting() {
    this.delayCounter = 0;
  }





  public void resetTask() {
    this.herdAnimal = null;
  }





  public void updateTask() {
    if (--this.delayCounter <= 0) {
      this.delayCounter = 20;

      this.theAnimal.getNavigator().tryMoveToEntityLiving((Entity)this.herdAnimal, this.moveSpeed);
    }
  }




  public void setExecutionChance(int newchance) {
    this.executionChance = newchance;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIFollowHerd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
