package drzhark.mocreatures.entity.ambient;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityAmbient;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;

public class MoCEntitySnail extends MoCEntityAmbient {
  private static final DataParameter<Boolean> IS_HIDDING = EntityDataManager.createKey(MoCEntitySnail.class, DataSerializers.BOOLEAN);

  public MoCEntitySnail(World world) {
    super(world);
    setSize(0.2F, 0.2F);
  }


  protected void initEntityAI() {
    this.tasks.addTask(1, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.8D));
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(IS_HIDDING, Boolean.valueOf(false));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
  }


  public boolean isMovementCeased() {
    return getIsHiding();
  }


  public void selectType() {
    if (getType() == 0) {
      setType(this.rand.nextInt(6) + 1);
    }
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("snaila.png");
      case 2:
        return MoCreatures.proxy.getTexture("snailb.png");
      case 3:
        return MoCreatures.proxy.getTexture("snailc.png");
      case 4:
        return MoCreatures.proxy.getTexture("snaild.png");
      case 5:
        return MoCreatures.proxy.getTexture("snaile.png");
      case 6:
        return MoCreatures.proxy.getTexture("snailf.png");
    }
    return MoCreatures.proxy.getTexture("snaila.png");
  }


  public boolean getIsHiding() {
    return ((Boolean)this.dataManager.get(IS_HIDDING)).booleanValue();
  }

  public void setIsHiding(boolean flag) {
    this.dataManager.set(IS_HIDDING, Boolean.valueOf(flag));
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote) {
      EntityLivingBase entityliving = getBoogey(3.0D);
      if (entityliving != null && entityliving.height > 0.5F && entityliving.width > 0.5F && canEntityBeSeen((Entity)entityliving)) {
        if (!getIsHiding()) {
          setIsHiding(true);
        }
        getNavigator().clearPath();
      } else {
        setIsHiding(false);
      }



      if (getIsHiding() && getType() > 4) {
        setIsHiding(false);
      }
    }
  }



  public void fall(float f, float f1) {}


  public void onUpdate() {
    super.onUpdate();

    if (getIsHiding()) {
      this.prevRenderYawOffset = this.renderYawOffset = this.rotationYaw = this.prevRotationYaw;
    }
  }


  protected Item getDropItem() {
    return Items.SLIME_BALL;
  }


  public boolean isOnLadder() {
    return this.collidedHorizontally;
  }

  public boolean climbing() {
    return (!this.onGround && isOnLadder());
  }

  public void jump() {}
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntitySnail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
