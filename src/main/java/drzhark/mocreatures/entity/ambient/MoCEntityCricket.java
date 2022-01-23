package drzhark.mocreatures.entity.ambient;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityInsect;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class MoCEntityCricket
  extends MoCEntityInsect
{
  private int jumpCounter;
  private int soundCounter;

  public MoCEntityCricket(World world) {
    super(world);
    this.texture = "cricketa.png";
  }


  public void selectType() {
    if (getType() == 0) {
      int i = this.rand.nextInt(100);
      if (i <= 50) {
        setType(1);
      } else {
        setType(2);
      }
    }
  }


  public ResourceLocation getTexture() {
    if (getType() == 1) {
      return MoCreatures.proxy.getTexture("cricketa.png");
    }
    return MoCreatures.proxy.getTexture("cricketb.png");
  }



  public void onLivingUpdate() {
    super.onLivingUpdate();
    if (!this.world.isRemote) {
      if (getIsFlying() && this.rand.nextInt(50) == 0) {
        setIsFlying(false);
      }

      if (getIsFlying() || !this.onGround) {
        EntityPlayer ep = this.world.getClosestPlayerToEntity((Entity)this, 5.0D);
        if (ep != null && --this.soundCounter == -1) {
          MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_CRICKET_FLY);
          this.soundCounter = 10;
        }
      } else if (!DimensionManager.getWorld(0).isDaytime()) {
        EntityPlayer ep = this.world.getClosestPlayerToEntity((Entity)this, 12.0D);
        if (ep != null && --this.soundCounter == -1) {
          MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_CRICKET_AMBIENT);
          this.soundCounter = 20;
        }
      }

      if (this.jumpCounter > 0 && ++this.jumpCounter > 30) {
        this.jumpCounter = 0;
      }
    }
  }


  public void onUpdate() {
    super.onUpdate();
    if (!this.world.isRemote &&
      this.onGround && (this.motionX > 0.05D || this.motionZ > 0.05D || this.motionX < -0.05D || this.motionZ < -0.05D) &&
      this.jumpCounter == 0 && this.onGround && (this.motionX > 0.05D || this.motionZ > 0.05D || this.motionX < -0.05D || this.motionZ < -0.05D)) {

      this.motionY = 0.45D;
      this.motionX *= 5.0D;
      this.motionZ *= 5.0D;
      this.jumpCounter = 1;
    }
  }



  public float getAIMoveSpeed() {
    if (getIsFlying()) {
      return 0.12F;
    }
    return 0.15F;
  }


  public boolean isFlyer() {
    return true;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityCricket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
