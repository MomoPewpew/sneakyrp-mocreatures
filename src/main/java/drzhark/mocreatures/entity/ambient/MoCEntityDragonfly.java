package drzhark.mocreatures.entity.ambient;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityInsect;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityDragonfly extends MoCEntityInsect {
  private int soundCount;

  public MoCEntityDragonfly(World world) {
    super(world);
    this.texture = "dragonflya.png";
  }


  public void selectType() {
    if (getType() == 0) {
      setType(this.rand.nextInt(4) + 1);
    }
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("dragonflya.png");
      case 2:
        return MoCreatures.proxy.getTexture("dragonflyb.png");
      case 3:
        return MoCreatures.proxy.getTexture("dragonflyc.png");
      case 4:
        return MoCreatures.proxy.getTexture("dragonflyd.png");
    }

    return MoCreatures.proxy.getTexture("dragonflyd.png");
  }



  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote) {
      EntityPlayer ep = this.world.getClosestPlayerToEntity((Entity)this, 5.0D);
      if (ep != null && getIsFlying() && --this.soundCount == -1) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_DRAGONFLY_AMBIENT);
        this.soundCount = 20;
      }

      if (getIsFlying() && this.rand.nextInt(200) == 0) {
        setIsFlying(false);
      }
    }
  }


  public boolean isFlyer() {
    return true;
  }


  public float getAIMoveSpeed() {
    if (getIsFlying()) {
      return 0.25F;
    }
    return 0.12F;
  }

  public int maxFlyingHeight() {
    return 4;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityDragonfly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
