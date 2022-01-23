package drzhark.mocreatures.entity.aquatic;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class MoCEntityRay extends MoCEntityTameableAquatic {
  public MoCEntityRay(World world) {
    super(world);
  }


  protected void initEntityAI() {
    this.tasks.addTask(2, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80));
  }

  public boolean isPoisoning() {
    return false;
  }


  public boolean processInteract(EntityPlayer player, EnumHand hand) {
    Boolean tameResult = processTameInteract(player, hand);
    if (tameResult != null) {
      return tameResult.booleanValue();
    }

    if (!isBeingRidden() && getType() == 1) {
      if (!this.world.isRemote && player.startRiding((Entity)this)) {
        player.rotationYaw = this.rotationYaw;
        player.rotationPitch = this.rotationPitch;
        player.posY = this.posY;
      }

      return true;
    }

    return super.processInteract(player, hand);
  }


  public float getAdjustedYOffset() {
    if (!isInWater()) {
      return 0.09F;
    }
    return 0.15F;
  }


  public int nameYOffset() {
    return -25;
  }


  public boolean canBeTrappedInNet() {
    return true;
  }


  public double getMountedYOffset() {
    return this.height * 0.15D * getSizeFactor();
  }


  public float getSizeFactor() {
    float f = getEdad() * 0.01F;
    if (f > 1.5F) {
      f = 1.5F;
    }
    return f;
  }


  protected boolean usesNewAI() {
    return true;
  }


  public float getAIMoveSpeed() {
    return 0.06F;
  }


  public boolean isMovementCeased() {
    return !isInWater();
  }


  protected double minDivingDepth() {
    return 3.0D;
  }


  protected double maxDivingDepth() {
    return 6.0D;
  }


  public int getMaxEdad() {
    return 90;
  }

  public boolean isMantaRay() {
    return false;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityRay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
