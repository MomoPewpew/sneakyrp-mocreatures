package drzhark.mocreatures.entity.aquatic;
import com.google.common.base.Predicate;
import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MoCEntityMediumFish extends MoCEntityTameableAquatic {
  public static final String[] fishNames = new String[] { "Salmon", "Cod", "Bass" };

  public MoCEntityMediumFish(World world) {
    super(world);
    setSize(0.6F, 0.3F);
    setEdad(30 + this.rand.nextInt(70));
  }

  public static MoCEntityMediumFish createEntity(World world, int type) {
    if (type == 1) {
      return new MoCEntitySalmon(world);
    }
    if (type == 2) {
      return new MoCEntityCod(world);
    }
    if (type == 3) {
      return new MoCEntityBass(world);
    }

    return new MoCEntitySalmon(world);
  }


  protected void initEntityAI() {
    this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromEntityMoC((EntityCreature)this, new Predicate<Entity>()
          {
            public boolean apply(Entity entity) {
              return (entity.height > 0.6F && entity.width > 0.3F);
            }
          },  2.0F, 0.6D, 1.5D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 50));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
  }


  public void selectType() {
    if (getType() == 0) {
      setType(this.rand.nextInt(fishNames.length) + 1);
    }
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

  protected int getEggNumber() {
    return 70;
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote &&
      getIsTamed() && this.rand.nextInt(100) == 0 && getHealth() < getMaxHealth()) {
      setHealth(getMaxHealth());
    }

    if (!isInsideOfMaterial(Material.WATER)) {
      this.prevRenderYawOffset = this.renderYawOffset = this.rotationYaw = this.prevRotationYaw;
      this.rotationPitch = this.prevRotationPitch;
    }
  }


  public float getSizeFactor() {
    return getEdad() * 0.01F;
  }


  public float getAdjustedYOffset() {
    if (!isInsideOfMaterial(Material.WATER)) {
      return 1.0F;
    }
    return 0.5F;
  }


  protected boolean isFisheable() {
    return !getIsTamed();
  }


  @SideOnly(Side.CLIENT)
  public float yawRotationOffset() {
    if (!isInsideOfMaterial(Material.WATER)) {
      return 90.0F;
    }
    return 90.0F + super.yawRotationOffset();
  }


  public float rollRotationOffset() {
    if (!isInWater() && this.onGround) {
      return -90.0F;
    }
    return 0.0F;
  }


  public int nameYOffset() {
    return -30;
  }


  public float getAdjustedZOffset() {
    if (!isInWater()) {
      return 0.2F;
    }
    return 0.0F;
  }


  public float getAdjustedXOffset() {
    return 0.0F;
  }


  protected boolean canBeTrappedInNet() {
    return true;
  }


  protected boolean usesNewAI() {
    return true;
  }


  public float getAIMoveSpeed() {
    return 0.15F;
  }


  public boolean isMovementCeased() {
    return !isInWater();
  }


  protected double minDivingDepth() {
    return 0.5D;
  }


  protected double maxDivingDepth() {
    return 4.0D;
  }


  public int getMaxEdad() {
    return 120;
  }


  public boolean isNotScared() {
    return getIsTamed();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityMediumFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
