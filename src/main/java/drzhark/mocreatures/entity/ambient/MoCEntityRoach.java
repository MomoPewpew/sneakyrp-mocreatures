package drzhark.mocreatures.entity.ambient;

import com.google.common.base.Predicate;
import drzhark.mocreatures.entity.MoCEntityInsect;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MoCEntityRoach
  extends MoCEntityInsect {
  public MoCEntityRoach(World world) {
    super(world);
    this.texture = "roach.png";
  }


  protected void initEntityAI() {
    this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromEntityMoC((EntityCreature)this, new Predicate<Entity>()
          {
            public boolean apply(Entity entity) {
              return (!(entity instanceof MoCEntityCrab) && (entity.height > 0.3F || entity.width > 0.3F));
            }
          },  6.0F, 0.8D, 1.3D));
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote)
    {
      if (getIsFlying() && this.rand.nextInt(50) == 0) {
        setIsFlying(false);
      }
    }
  }









  public boolean entitiesToInclude(Entity entity) {
    return (!(entity instanceof MoCEntityInsect) && super.entitiesToInclude(entity));
  }


  public boolean isFlyer() {
    return true;
  }


  public boolean isMyFavoriteFood(ItemStack stack) {
    return (!stack.isEmpty() && stack.getItem() == Items.ROTTEN_FLESH);
  }


  protected int getFlyingFreq() {
    return 500;
  }


  public float getAIMoveSpeed() {
    if (getIsFlying()) {
      return 0.1F;
    }
    return 0.25F;
  }


  public boolean isNotScared() {
    return getIsFlying();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityRoach.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
