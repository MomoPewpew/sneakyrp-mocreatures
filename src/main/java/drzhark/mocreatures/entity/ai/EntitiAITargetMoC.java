package drzhark.mocreatures.entity.ai;

import drzhark.mocreatures.entity.MoCEntityAnimal;
import drzhark.mocreatures.entity.monster.MoCEntityOgre;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;








public abstract class EntitiAITargetMoC
  extends EntityAIBase
{
  protected final EntityCreature taskOwner;
  protected boolean shouldCheckSight;
  private boolean nearbyOnly;
  private int targetSearchStatus;
  private int targetSearchDelay;
  private int targetUnseenTicks;

  public EntitiAITargetMoC(EntityCreature creature, boolean checkSight, boolean onlyNearby) {
    this.taskOwner = creature;
    this.shouldCheckSight = checkSight;
    this.nearbyOnly = onlyNearby;
  }

  public EntitiAITargetMoC(EntityCreature creature, boolean checkSight) {
    this(creature, checkSight, false);
  }










  public static boolean isSuitableTarget(EntityLiving attacker, EntityLivingBase target, boolean includeInvincibles, boolean checkSight) {
    if (target == null)
      return false;
    if (target == attacker)
      return false;
    if (!target.isEntityAlive())
      return false;
    if (!attacker.canAttackClass(target.getClass()))
      return false;
    if (attacker instanceof MoCEntityAnimal && !(target instanceof EntityPlayer)) {
      MoCEntityAnimal mocattacker = (MoCEntityAnimal)attacker;
      if (!mocattacker.canAttackTarget(target)) {
        return false;
      }


      if (mocattacker.getIsTamed() && target instanceof MoCEntityAnimal && ((MoCEntityAnimal)target).getIsTamed()) {
        return false;
      }
    }
    Team team = attacker.getTeam();
    Team team1 = target.getTeam();

    if (team != null && team1 == team) {
      return false;
    }
    if (attacker instanceof IEntityOwnable && ((IEntityOwnable)attacker).getOwnerId() != null) {
      if (target instanceof IEntityOwnable && ((IEntityOwnable)attacker).getOwnerId().equals(((IEntityOwnable)target).getOwnerId())) {
        return false;
      }

      if (target == ((IEntityOwnable)attacker).getOwner()) {
        return false;
      }
    } else if (target instanceof EntityPlayer && includeInvincibles && ((EntityPlayer)target).capabilities.disableDamage) {
      return false;
    }

    return (!checkSight || attacker.getEntitySenses().canSee((Entity)target));
  }







  public boolean shouldContinueExecuting() {
    EntityLivingBase entitylivingbase = this.taskOwner.getAttackTarget();

    if (entitylivingbase == null)
      return false;
    if (!entitylivingbase.isEntityAlive()) {
      return false;
    }
    Team team = this.taskOwner.getTeam();
    Team team1 = entitylivingbase.getTeam();

    if (team != null && team1 == team) {
      return false;
    }
    double d0 = getTargetDistance();

    if (this.taskOwner.getDistanceSq((Entity)entitylivingbase) > d0 * d0) {
      return false;
    }
    if (this.shouldCheckSight) {
      if (this.taskOwner.getEntitySenses().canSee((Entity)entitylivingbase)) {
        this.targetUnseenTicks = 0;
      } else if (++this.targetUnseenTicks > 60) {
        return false;
      }
    }

    return (!(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer)entitylivingbase).capabilities.disableDamage);
  }




  protected double getTargetDistance() {
    if (this.taskOwner instanceof MoCEntityOgre) {
      return ((MoCEntityOgre)this.taskOwner).getAttackRange();
    }
    IAttributeInstance iattributeinstance = this.taskOwner.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
    return (iattributeinstance == null) ? 16.0D : iattributeinstance.getAttributeValue();
  }





  public void startExecuting() {
    this.targetSearchStatus = 0;
    this.targetSearchDelay = 0;
    this.targetUnseenTicks = 0;
  }





  public void resetTask() {
    this.taskOwner.setAttackTarget((EntityLivingBase)null);
  }





  protected boolean isSuitableTarget(EntityLivingBase target, boolean includeInvincibles) {
    if (!isSuitableTarget((EntityLiving)this.taskOwner, target, includeInvincibles, this.shouldCheckSight))
    {
      return false; }
    if (!this.taskOwner.isWithinHomeDistanceFromPosition(new BlockPos((Entity)target)))
    {
      return false;
    }
    if (this.nearbyOnly) {
      if (--this.targetSearchDelay <= 0) {
        this.targetSearchStatus = 0;
      }

      if (this.targetSearchStatus == 0) {
        this.targetSearchStatus = canEasilyReach(target) ? 1 : 2;
      }

      if (this.targetSearchStatus == 2) {
        return false;
      }
    }

    return true;
  }





  private boolean canEasilyReach(EntityLivingBase p_75295_1_) {
    this.targetSearchDelay = 10 + this.taskOwner.getRNG().nextInt(5);
    Path path = this.taskOwner.getNavigator().getPathToEntityLiving((Entity)p_75295_1_);

    if (path == null)
    {
      return false;
    }
    PathPoint pathpoint = path.getFinalPathPoint();

    if (pathpoint == null) {
      return false;
    }
    int i = pathpoint.x - MathHelper.floor(p_75295_1_.posX);
    int j = pathpoint.z - MathHelper.floor(p_75295_1_.posZ);
    return ((i * i + j * j) <= 2.25D);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntitiAITargetMoC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
