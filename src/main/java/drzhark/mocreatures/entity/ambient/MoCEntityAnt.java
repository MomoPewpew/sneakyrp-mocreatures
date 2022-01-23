package drzhark.mocreatures.entity.ambient;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.entity.MoCEntityInsect;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

public class MoCEntityAnt extends MoCEntityInsect {
  private static final DataParameter<Boolean> FOUND_FOOD = EntityDataManager.createKey(MoCEntityAnt.class, DataSerializers.BOOLEAN);

  public MoCEntityAnt(World world) {
    super(world);
    this.texture = "ant.png";
  }


  protected void initEntityAI() {
    this.tasks.addTask(1, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.2D));
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(FOUND_FOOD, Boolean.valueOf(false));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
  }

  public boolean getHasFood() {
    return ((Boolean)this.dataManager.get(FOUND_FOOD)).booleanValue();
  }

  public void setHasFood(boolean flag) {
    this.dataManager.set(FOUND_FOOD, Boolean.valueOf(flag));
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote &&
      !getHasFood()) {
      EntityItem entityitem = MoCTools.getClosestFood((Entity)this, 8.0D);
      if (entityitem == null || entityitem.isDead) {
        return;
      }
      if (entityitem.getRidingEntity() == null) {
        float f = entityitem.getDistance((Entity)this);
        if (f > 1.0F) {
          int i = MathHelper.floor(entityitem.posX);
          int j = MathHelper.floor(entityitem.posY);
          int k = MathHelper.floor(entityitem.posZ);
          faceLocation(i, j, k, 30.0F);

          getMyOwnPath((Entity)entityitem, f);
          return;
        }
        if (f < 1.0F) {
          exchangeItem(entityitem);
          setHasFood(true);


          return;
        }
      }
    }

    if (getHasFood() &&
      !isBeingRidden()) {
      EntityItem entityitem = MoCTools.getClosestFood((Entity)this, 2.0D);
      if (entityitem != null && entityitem.getRidingEntity() == null) {
        entityitem.startRiding((Entity)this);

        return;
      }

      if (!isBeingRidden()) {
        setHasFood(false);
      }
    }
  }


  private void exchangeItem(EntityItem entityitem) {
    EntityItem cargo = new EntityItem(this.world, this.posX, this.posY + 0.2D, this.posZ, entityitem.getItem());
    entityitem.setDead();
    if (!this.world.isRemote) {
      this.world.spawnEntity((Entity)cargo);
    }
  }


  public boolean getIsFlying() {
    return false;
  }


  public boolean isMyFavoriteFood(ItemStack stack) {
    return (!stack.isEmpty() && MoCTools.isItemEdible(stack.getItem()));
  }


  public boolean isFlyer() {
    return false;
  }


  public float getAIMoveSpeed() {
    if (getHasFood()) {
      return 0.05F;
    }
    return 0.15F;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityAnt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
