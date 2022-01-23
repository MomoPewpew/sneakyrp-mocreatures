package drzhark.mocreatures.entity.ai;

import drzhark.mocreatures.entity.IMoCEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class EntityAIFleeFromPlayer extends EntityAIBase {
  private EntityCreature entityCreature;
  protected double speed;
  protected double distance;
  private double randPosX;
  private double randPosY;
  private double randPosZ;

  public EntityAIFleeFromPlayer(EntityCreature creature, double speedIn, double distanceToCheck) {
    this.entityCreature = creature;
    this.distance = distanceToCheck;
    this.speed = speedIn;
    setMutexBits(1);
  }






  public boolean shouldExecute() {
    if (this.entityCreature instanceof IMoCEntity && (
      (IMoCEntity)this.entityCreature).isNotScared()) {
      return false;
    }


    if (!IsNearPlayer(this.distance)) {
      return false;
    }
    Vec3d vec3 = RandomPositionGenerator.findRandomTarget(this.entityCreature, 5, 4);

    if (vec3 == null) {
      return false;
    }
    this.randPosX = vec3.x;
    this.randPosY = vec3.y;
    this.randPosZ = vec3.z;
    return true;
  }



  protected boolean IsNearPlayer(double d) {
    EntityPlayer entityplayer1 = this.entityCreature.world.getClosestPlayerToEntity((Entity)this.entityCreature, d);
    if (entityplayer1 != null) {
      return true;
    }
    return false;
  }





  public void startExecuting() {
    this.entityCreature.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
  }





  public boolean shouldContinueExecuting() {
    return !this.entityCreature.getNavigator().noPath();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIFleeFromPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
