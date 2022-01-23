package drzhark.mocreatures.entity.ai;

import com.google.common.base.Predicate;
import drzhark.mocreatures.entity.MoCEntityAnimal;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.EntityLivingBase;

public class EntityAIHunt
  extends EntityAINearestAttackableTargetMoC {
  private EntityCreature hunter;

  public EntityAIHunt(EntityCreature entity, Class<? extends EntityCreature> classTarget, int chance, boolean checkSight, boolean onlyNearby, Predicate<EntityLivingBase> predicate) {
    super(entity, (Class)classTarget, chance, checkSight, onlyNearby, predicate);
    this.hunter = entity;
  }

  public EntityAIHunt(EntityCreature entityCreature, Class<EntityAnimal> class1, boolean checkSight) {
    this(entityCreature, class1, checkSight, false);
  }

  public EntityAIHunt(EntityCreature entity, Class<? extends EntityCreature> classTarget, boolean checkSight, boolean onlyNearby) {
    this(entity, classTarget, 10, checkSight, onlyNearby, null);
  }






  public boolean shouldExecute() {
    return (((MoCEntityAnimal)this.hunter).getIsHunting() && super.shouldExecute());
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ai\EntityAIHunt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
