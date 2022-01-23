package drzhark.mocreatures.entity.aquatic;
import com.google.common.base.Predicate;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MoCEntitySmallFish extends MoCEntityTameableAquatic {
  public static final String[] fishNames = new String[] { "Anchovy", "Angelfish", "Angler", "Clownfish", "Goldfish", "Hippotang", "Manderin" };

  public MoCEntitySmallFish(World world) {
    super(world);
    setSize(0.3F, 0.3F);
    setEdad(70 + this.rand.nextInt(30));
  }

  public static MoCEntitySmallFish createEntity(World world, int type) {
    if (type == 1) {
      return new MoCEntityAnchovy(world);
    }
    if (type == 2) {
      return new MoCEntityAngelFish(world);
    }
    if (type == 3) {
      return new MoCEntityAngler(world);
    }
    if (type == 4) {
      return new MoCEntityClownFish(world);
    }
    if (type == 5) {
      return new MoCEntityGoldFish(world);
    }
    if (type == 6) {
      return new MoCEntityHippoTang(world);
    }
    if (type == 7) {
      return new MoCEntityManderin(world);
    }

    return new MoCEntityClownFish(world);
  }


  protected void initEntityAI() {
    this.tasks.addTask(1, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.3D));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIFleeFromEntityMoC((EntityCreature)this, new Predicate<Entity>()
          {
            public boolean apply(Entity entity) {
              return (entity.height > 0.3F || entity.width > 0.3F);
            }
          },  2.0F, 0.6D, 1.5D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
  }


  public void selectType() {
    if (getType() == 0) {
      setType(this.rand.nextInt(fishNames.length) + 1);
    }
  }




  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("smallfish_anchovy.png");
      case 2:
        return MoCreatures.proxy.getTexture("smallfish_angelfish.png");
      case 3:
        return MoCreatures.proxy.getTexture("smallfish_angler.png");
      case 4:
        return MoCreatures.proxy.getTexture("smallfish_clownfish.png");
      case 5:
        return MoCreatures.proxy.getTexture("smallfish_goldfish.png");
      case 6:
        return MoCreatures.proxy.getTexture("smallfish_hippotang.png");
      case 7:
        return MoCreatures.proxy.getTexture("smallfish_manderin.png");
    }
    return MoCreatures.proxy.getTexture("smallfish_clownfish.png");
  }



  protected boolean canBeTrappedInNet() {
    return true;
  }


  protected void dropFewItems(boolean flag, int x) {
    int i = this.rand.nextInt(100);
    if (i < 70) {
      entityDropItem(new ItemStack(Items.FISH, 1, 0), 0.0F);
    } else {
      int j = this.rand.nextInt(2);
      for (int k = 0; k < j; k++) {
        entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, getEggNumber()), 0.0F);
      }
    }
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote)
    {
      if (getIsTamed() && this.rand.nextInt(100) == 0 && getHealth() < getMaxHealth()) {
        setHealth(getMaxHealth());
      }
    }
    if (!isInWater()) {
      this.prevRenderYawOffset = this.renderYawOffset = this.rotationYaw = this.prevRotationYaw;
      this.rotationPitch = this.prevRotationPitch;
    }
  }


  public float getSizeFactor() {
    return getEdad() * 0.01F;
  }


  public float getAdjustedYOffset() {
    if (!isInWater()) {
      return 0.5F;
    }
    return 0.3F;
  }


  protected boolean isFisheable() {
    return !getIsTamed();
  }


  @SideOnly(Side.CLIENT)
  public float yawRotationOffset() {
    if (!isInWater()) {
      return 90.0F;
    }
    return 90.0F + super.yawRotationOffset();
  }


  public float rollRotationOffset() {
    if (!isInWater()) {
      return -90.0F;
    }
    return 0.0F;
  }


  public int nameYOffset() {
    return -25;
  }


  public float getAdjustedXOffset() {
    return 0.0F;
  }


  protected boolean usesNewAI() {
    return true;
  }


  public float getAIMoveSpeed() {
    return 0.1F;
  }


  public boolean isMovementCeased() {
    return !isInWater();
  }


  protected double minDivingDepth() {
    return 0.2D;
  }


  protected double maxDivingDepth() {
    return 2.0D;
  }


  public int getMaxEdad() {
    return 120;
  }


  public boolean isNotScared() {
    return getIsTamed();
  }


  public float getAdjustedZOffset() {
    if (!isInWater()) {
      return 0.1F;
    }
    return 0.0F;
  }

  protected int getEggNumber() {
    return 80;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntitySmallFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
