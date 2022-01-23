package drzhark.mocreatures.entity.aquatic;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class MoCEntityJellyFish extends MoCEntityTameableAquatic {
  private static final DataParameter<Boolean> GLOWS = EntityDataManager.createKey(MoCEntityJellyFish.class, DataSerializers.BOOLEAN); private int poisoncounter;

  public MoCEntityJellyFish(World world) {
    super(world);
    setSize(0.3F, 0.5F);
    setEdad(50 + this.rand.nextInt(50));
  }


  protected void initEntityAI() {
    this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.5D, 120));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
  }


  public void selectType() {
    if (getType() == 0) {
      setType(this.rand.nextInt(5) + 1);
    }
  }


  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(GLOWS, Boolean.valueOf(false));
  }

  public void setGlowing(boolean flag) {
    this.dataManager.set(GLOWS, Boolean.valueOf(flag));
  }

  public boolean isGlowing() {
    return ((Boolean)this.dataManager.get(GLOWS)).booleanValue();
  }


  public float getAIMoveSpeed() {
    return 0.02F;
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("jellyfisha.png");
      case 2:
        return MoCreatures.proxy.getTexture("jellyfishb.png");
      case 3:
        return MoCreatures.proxy.getTexture("jellyfishc.png");
      case 4:
        return MoCreatures.proxy.getTexture("jellyfishd.png");
      case 5:
        return MoCreatures.proxy.getTexture("jellyfishe.png");
      case 6:
        return MoCreatures.proxy.getTexture("jellyfishf.png");
      case 7:
        return MoCreatures.proxy.getTexture("jellyfishg.png");
      case 8:
        return MoCreatures.proxy.getTexture("jellyfishh.png");
      case 9:
        return MoCreatures.proxy.getTexture("jellyfishi.png");
      case 10:
        return MoCreatures.proxy.getTexture("jellyfishj.png");
      case 11:
        return MoCreatures.proxy.getTexture("jellyfishk.png");
      case 12:
        return MoCreatures.proxy.getTexture("jellyfishl.png");
    }

    return MoCreatures.proxy.getTexture("jellyfisha.png");
  }



  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (!this.world.isRemote) {

      if (this.rand.nextInt(200) == 0) {
        setGlowing(!this.world.isDaytime());
      }

      if (!getIsTamed() && ++this.poisoncounter > 250 && shouldAttackPlayers() && this.rand.nextInt(30) == 0 &&
        MoCTools.findNearPlayerAndPoison((Entity)this, true)) {
        this.poisoncounter = 0;
      }
    }
  }



  protected Item getDropItem() {
    boolean flag = (this.rand.nextInt(2) == 0);
    if (flag) {
      return Items.SLIME_BALL;
    }
    return null;
  }


  public float pitchRotationOffset() {
    if (!isInWater()) {
      return 90.0F;
    }
    return 0.0F;
  }


  public int nameYOffset() {
    int yOff = (int)((getEdad() * -1) / 2.3D);
    return yOff;
  }


  public float getSizeFactor() {
    float myMoveSpeed = MoCTools.getMyMovementSpeed((Entity)this);
    float pulseSpeed = 0.08F;
    if (myMoveSpeed > 0.0F)
      pulseSpeed = 0.5F;
    float pulseSize = MathHelper.cos(this.ticksExisted * pulseSpeed) * 0.2F;
    return getEdad() * 0.01F + pulseSize / 5.0F;
  }


  protected boolean canBeTrappedInNet() {
    return true;
  }


  public int getMaxEdad() {
    return 100;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityJellyFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
