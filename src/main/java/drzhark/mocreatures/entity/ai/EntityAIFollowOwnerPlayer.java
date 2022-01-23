package drzhark.mocreatures.entity.ai;

import drzhark.mocreatures.entity.IMoCEntity;
import drzhark.mocreatures.entity.IMoCTameable;
import java.util.UUID;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityAIFollowOwnerPlayer extends EntityAIBase {
  private EntityLiving thePet;
  private EntityPlayer theOwner;
  World world;
  private double speed;
  private PathNavigate petPathfinder;
  private int delayCounter;
  float maxDist;
  float minDist;

  public EntityAIFollowOwnerPlayer(EntityLiving thePetIn, double speedIn, float minDistIn, float maxDistIn) {
    this.thePet = thePetIn;
    this.world = thePetIn.world;
    this.speed = speedIn;
    this.petPathfinder = thePetIn.getNavigator();
    this.minDist = minDistIn;
    this.maxDist = maxDistIn;
    setMutexBits(3);
  }










  public boolean shouldExecute() {
    if (((IMoCEntity)this.thePet).getIsSitting()) {
      return false;
    }

    UUID ownerUniqueId = ((IMoCTameable)this.thePet).getOwnerId();
    if (ownerUniqueId == null) {
      return false;
    }

    EntityPlayer entityplayer = EntityAITools.getIMoCTameableOwner((IMoCTameable)this.thePet);

    if (entityplayer == null) {
      return false;
    }

    if (this.thePet.getDistanceSq((Entity)entityplayer) < (this.minDist * this.minDist) || this.thePet
      .getDistanceSq((Entity)entityplayer) > (this.maxDist * this.maxDist)) {
      return false;
    }
    this.theOwner = entityplayer;
    return true;
  }






  public boolean shouldContinueExecuting() {
    return (!this.petPathfinder.noPath() && this.thePet.getDistanceSq((Entity)this.theOwner) > (this.maxDist * this.maxDist) &&
      !((IMoCEntity)this.thePet).getIsSitting());
  }





  public void startExecuting() {
    this.delayCounter = 0;
  }







  public void resetTask() {
    this.theOwner = null;
    this.petPathfinder.clearPath();
  }



  private boolean isEmptyBlock(BlockPos pos) {
    IBlockState iblockstate = this.world.getBlockState(pos);
    return (iblockstate.getMaterial() == Material.AIR) ? true : (!iblockstate.isFullCube());
  }


  public void updateTask() {
    this.thePet.getLookHelper().setLookPositionWithEntity((Entity)this.theOwner, 10.0F, this.thePet.getVerticalFaceSpeed());

    if (!((IMoCEntity)this.thePet).getIsSitting() &&
      --this.delayCounter <= 0) {

      this.delayCounter = 10;

      if (!this.petPathfinder.tryMoveToEntityLiving((Entity)this.theOwner, this.speed))
      {
        if (!this.thePet.getLeashed())
        {
          if (this.thePet.getDistanceSq((Entity)this.theOwner) >= 144.0D) {

            int i = MathHelper.floor(this.theOwner.posX) - 2;
            int j = MathHelper.floor(this.theOwner.posZ) - 2;
            int k = MathHelper.floor((this.theOwner.getEntityBoundingBox()).minY);

            for (int l = 0; l <= 4; l++) {

              for (int i1 = 0; i1 <= 4; i1++) {

                BlockPos pos = new BlockPos(i + l, k - 1, j + i1);
                if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.world.getBlockState(pos).isSideSolid((IBlockAccess)this.world, pos, EnumFacing.DOWN) && isEmptyBlock(new BlockPos(i + l, k, j + i1)) && isEmptyBlock(new BlockPos(i + l, k + 1, j + i1))) {

                  this.thePet.setLocationAndAngles(((i + l) + 0.5F), k, ((j + i1) + 0.5F), this.thePet.rotationYaw, this.thePet.rotationPitch);
                  this.petPathfinder.clearPath();
                  return;
                }
              }
            }
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIFollowOwnerPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
