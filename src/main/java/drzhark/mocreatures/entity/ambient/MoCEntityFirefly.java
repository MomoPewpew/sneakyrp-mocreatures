package drzhark.mocreatures.entity.ambient;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.entity.MoCEntityInsect;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MoCEntityFirefly extends MoCEntityInsect {
  private int soundCount;

  public MoCEntityFirefly(World world) {
    super(world);
    this.texture = "firefly.png";
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote) {
      EntityPlayer ep = this.world.getClosestPlayerToEntity((Entity)this, 5.0D);
      if (ep != null && getIsFlying() && --this.soundCount == -1) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_CRICKET_FLY);
        this.soundCount = 20;
      }

      if (getIsFlying() && this.rand.nextInt(500) == 0) {
        setIsFlying(false);
      }
    }
  }


  public boolean isFlyer() {
    return true;
  }


  public float getAIMoveSpeed() {
    if (getIsFlying()) {
      return 0.12F;
    }
    return 0.1F;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityFirefly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
