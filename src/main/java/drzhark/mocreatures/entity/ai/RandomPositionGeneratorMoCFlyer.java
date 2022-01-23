package drzhark.mocreatures.entity.ai;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;







public class RandomPositionGeneratorMoCFlyer
{
  private static Vec3d staticVector = Vec3d.ZERO;









  @Nullable
  public static Vec3d findRandomTarget(EntityCreature entitycreatureIn, int xz, int y) {
    return findRandomTargetBlock(entitycreatureIn, xz, y, (Vec3d)null);
  }





  @Nullable
  public static Vec3d findRandomTargetBlockTowards(EntityCreature entitycreatureIn, int xz, int y, Vec3d targetVec3) {
    staticVector = targetVec3.subtract(entitycreatureIn.posX, entitycreatureIn.posY, entitycreatureIn.posZ);




    return findRandomTargetBlock(entitycreatureIn, xz, y, staticVector);
  }





  @Nullable
  public static Vec3d findRandomTargetBlockAwayFrom(EntityCreature entitycreatureIn, int xz, int y, Vec3d targetVec3) {
    staticVector = (new Vec3d(entitycreatureIn.posX, entitycreatureIn.posY, entitycreatureIn.posZ)).subtract(targetVec3);




    return findRandomTargetBlock(entitycreatureIn, xz, y, staticVector);
  }





  @Nullable
  private static Vec3d findRandomTargetBlock(EntityCreature entitycreatureIn, int xz, int y, @Nullable Vec3d targetVec3) {
    boolean flag1;
    PathNavigate pathnavigate = entitycreatureIn.getNavigator();
    Random random = entitycreatureIn.getRNG();
    boolean flag = false;
    int i = 0;
    int j = 0;
    int k = 0;
    float f = -99999.0F;


    if (entitycreatureIn.hasHome()) {

      double d0 = entitycreatureIn.getHomePosition().distanceSq(MathHelper.floor(entitycreatureIn.posX), MathHelper.floor(entitycreatureIn.posY), MathHelper.floor(entitycreatureIn.posZ)) + 4.0D;
      double d1 = (entitycreatureIn.getMaximumHomeDistance() + xz);
      flag1 = (d0 < d1 * d1);
    }
    else {

      flag1 = false;
    }

    for (int j1 = 0; j1 < 10; j1++) {

      int l = random.nextInt(2 * xz + 1) - xz;
      int k1 = random.nextInt(2 * y + 1) - y;
      int i1 = random.nextInt(2 * xz + 1) - xz;

      if (targetVec3 == null || l * targetVec3.x + i1 * targetVec3.z >= 0.0D) {

        if (entitycreatureIn.hasHome() && xz > 1) {

          BlockPos blockpos = entitycreatureIn.getHomePosition();

          if (entitycreatureIn.posX > blockpos.getX()) {

            l -= random.nextInt(xz / 2);
          }
          else {

            l += random.nextInt(xz / 2);
          }

          if (entitycreatureIn.posZ > blockpos.getZ()) {

            i1 -= random.nextInt(xz / 2);
          }
          else {

            i1 += random.nextInt(xz / 2);
          }
        }

        BlockPos blockpos1 = new BlockPos(l + entitycreatureIn.posX, k1 + entitycreatureIn.posY, i1 + entitycreatureIn.posZ);

        if (!flag1 || entitycreatureIn.isWithinHomeDistanceFromPosition(blockpos1)) {

          float f1 = entitycreatureIn.getBlockPathWeight(blockpos1);

          if (f1 > f) {

            f = f1;
            i = l;
            j = k1;
            k = i1;
            flag = true;
          }
        }
      }
    }

    if (flag)
    {
      return new Vec3d(i + entitycreatureIn.posX, j + entitycreatureIn.posY, k + entitycreatureIn.posZ);
    }


    return null;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\RandomPositionGeneratorMoCFlyer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
