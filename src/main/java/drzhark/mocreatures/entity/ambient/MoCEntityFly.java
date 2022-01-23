package drzhark.mocreatures.entity.ambient;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.entity.MoCEntityInsect;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MoCEntityFly extends MoCEntityInsect {
  public MoCEntityFly(World world) {
    super(world);
    this.texture = "fly.png";
  }


  private int soundCount;

  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote) {

      if (getIsFlying() && this.rand.nextInt(200) == 0) {
        setIsFlying(false);
      }
      if (getIsFlying() && --this.soundCount == -1) {
        EntityPlayer ep = this.world.getClosestPlayerToEntity((Entity)this, 5.0D);
        if (ep != null) {
          MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_FLY_AMBIENT);
          this.soundCount = 55;
        }
      }
    }
  }


  public boolean isMyFavoriteFood(ItemStack stack) {
    return (!stack.isEmpty() && stack.getItem() == Items.ROTTEN_FLESH);
  }


  public boolean isFlyer() {
    return true;
  }


  public float getAIMoveSpeed() {
    if (getIsFlying()) {
      return 0.2F;
    }
    return 0.12F;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityFly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
