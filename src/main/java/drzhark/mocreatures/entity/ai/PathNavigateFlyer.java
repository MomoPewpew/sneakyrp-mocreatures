package drzhark.mocreatures.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PathNavigateFlyer extends PathNavigate {
  public PathNavigateFlyer(EntityLiving entitylivingIn, World worldIn) {
    super(entitylivingIn, worldIn);
  }

  protected PathFinder getPathFinder() {
    return new PathFinder(new FlyNodeProcessor());
  }




  protected boolean canNavigate() {
    return true;
  }

  protected Vec3d getEntityPosition() {
    return new Vec3d(this.entity.posX, this.entity.posY + this.entity.height * 0.5D, this.entity.posZ);
  }

  protected void pathFollow() {
    Vec3d vec3 = getEntityPosition();
    float f = this.entity.width * this.entity.width;
    byte b0 = 6;

    if (vec3.squareDistanceTo(this.currentPath.getVectorFromIndex((Entity)this.entity, this.currentPath.getCurrentPathIndex())) < f) {
      this.currentPath.incrementPathIndex();
    }

    int i = Math.min(this.currentPath.getCurrentPathIndex() + b0, this.currentPath.getCurrentPathLength() - 1); for (; i > this.currentPath
      .getCurrentPathIndex(); i--) {
      Vec3d vec31 = this.currentPath.getVectorFromIndex((Entity)this.entity, i);

      if (vec31.squareDistanceTo(vec3) <= 36.0D && isDirectPathBetweenPoints(vec3, vec31, 0, 0, 0)) {
        this.currentPath.setCurrentPathIndex(i);

        break;
      }
    }
    checkForStuck(vec3);
  }




  protected void removeSunnyPath() {
    super.removeSunnyPath();
  }







  protected boolean isDirectPathBetweenPoints(Vec3d posVec31, Vec3d posVec32, int sizeX, int sizeY, int sizeZ) {
    RayTraceResult raytraceresult = this.world.rayTraceBlocks(posVec31, new Vec3d(posVec32.x, posVec32.y + this.entity.height * 0.5D, posVec32.z), false, true, false);
    return (raytraceresult == null || raytraceresult.typeOfHit == RayTraceResult.Type.MISS);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\PathNavigateFlyer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
