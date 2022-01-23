package drzhark.mocreatures.entity.ai;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import drzhark.mocreatures.entity.IMoCEntity;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;






public class EntityAINearestAttackableTargetMoC
  extends EntitiAITargetMoC
{
  protected final Class<? extends EntityLivingBase> targetClass;
  private final int targetChance;
  protected final Sorter theNearestAttackableTargetSorter;
  protected Predicate<EntityLivingBase> targetEntitySelector;
  protected EntityLivingBase targetEntity;
  private IMoCEntity theAttacker;

  public EntityAINearestAttackableTargetMoC(EntityCreature creature, Class<? extends EntityLivingBase> classTarget, boolean checkSight) {
    this(creature, classTarget, checkSight, false);
  }

  public EntityAINearestAttackableTargetMoC(EntityCreature creature, Class<? extends EntityLivingBase> classTarget, boolean checkSight, boolean onlyNearby) {
    this(creature, classTarget, 10, checkSight, onlyNearby, (Predicate<EntityLivingBase>)null);
  }


  public EntityAINearestAttackableTargetMoC(EntityCreature creature, Class<? extends EntityLivingBase> classTarget, int chance, boolean checkSight, boolean onlyNearby, final Predicate<EntityLivingBase> targetSelector) {
    super(creature, checkSight, onlyNearby);
    if (creature instanceof IMoCEntity) {
      this.theAttacker = (IMoCEntity)creature;
    }
    this.targetClass = classTarget;
    this.targetChance = chance;
    this.theNearestAttackableTargetSorter = new Sorter((Entity)creature);
    setMutexBits(1);
    this.targetEntitySelector = new Predicate<EntityLivingBase>()
      {
        public boolean apply(EntityLivingBase entitylivingbaseIn)
        {
          if (targetSelector != null && !targetSelector.apply(entitylivingbaseIn)) {
            return false;
          }
          if (entitylivingbaseIn instanceof EntityPlayer) {
            double d0 = EntityAINearestAttackableTargetMoC.this.getTargetDistance();

            if (entitylivingbaseIn.isSneaking()) {
              d0 *= 0.800000011920929D;
            }

            if (entitylivingbaseIn.isInvisible()) {
              float f = ((EntityPlayer)entitylivingbaseIn).getArmorVisibility();

              if (f < 0.1F) {
                f = 0.1F;
              }

              d0 *= (0.7F * f);
            }

            if (entitylivingbaseIn.getDistance((Entity)EntityAINearestAttackableTargetMoC.this.taskOwner) > d0) {
              return false;
            }
          }

          return EntityAINearestAttackableTargetMoC.this.isSuitableTarget(entitylivingbaseIn, false);
        }
      };
  }






  public boolean shouldExecute() {
    if (this.theAttacker != null && (this.theAttacker.isMovementCeased() || !this.theAttacker.isNotScared())) {
      return false;
    }
    if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0) {
      return false;
    }
    double d0 = getTargetDistance();

    List<EntityLivingBase> list = this.taskOwner.world.getEntitiesWithinAABB(this.targetClass, this.taskOwner.getEntityBoundingBox().expand(d0, 4.0D, d0),
        Predicates.and(this.targetEntitySelector, EntitySelectors.NOT_SPECTATING));
    Collections.sort(list, this.theNearestAttackableTargetSorter);

    if (list.isEmpty()) {
      return false;
    }
    this.targetEntity = list.get(0);
    if (this.targetEntity instanceof EntityPlayer && !this.theAttacker.shouldAttackPlayers()) {
      return false;
    }
    return true;
  }







  public void startExecuting() {
    this.taskOwner.setAttackTarget(this.targetEntity);
    super.startExecuting();
  }

  public static class Sorter
    implements Comparator<Entity> {
    private final Entity entity;

    public Sorter(Entity entityIn) {
      this.entity = entityIn;
    }

    public int compare(Entity p_compare_1_, Entity p_compare_2_) {
      double d0 = this.entity.getDistanceSq(p_compare_1_);
      double d1 = this.entity.getDistanceSq(p_compare_2_);
      return (d0 < d1) ? -1 : ((d0 > d1) ? 1 : 0);
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAINearestAttackableTargetMoC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
