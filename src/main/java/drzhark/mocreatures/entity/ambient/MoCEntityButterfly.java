package drzhark.mocreatures.entity.ambient;

import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityInsect;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class MoCEntityButterfly extends MoCEntityInsect {
  public MoCEntityButterfly(World world) {
    super(world);
  }


  private int fCounter;

  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote &&
      getIsFlying() && this.rand.nextInt(200) == 0) {
      setIsFlying(false);
    }
  }



  public void selectType() {
    if (getType() == 0) {
      setType(this.rand.nextInt(10) + 1);
    }
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("bfagalaisurticae.png");
      case 2:
        return MoCreatures.proxy.getTexture("bfargyreushyperbius.png");
      case 3:
        return MoCreatures.proxy.getTexture("bfathymanefte.png");
      case 4:
        return MoCreatures.proxy.getTexture("bfcatopsiliapomona.png");
      case 5:
        return MoCreatures.proxy.getTexture("bfmorphopeleides.png");
      case 6:
        return MoCreatures.proxy.getTexture("bfvanessaatalanta.png");
      case 7:
        return MoCreatures.proxy.getTexture("bfpierisrapae.png");
      case 8:
        return MoCreatures.proxy.getTexture("mothcamptogrammabilineata.png");
      case 9:
        return MoCreatures.proxy.getTexture("mothidiaaemula.png");
      case 10:
        return MoCreatures.proxy.getTexture("moththyatirabatis.png");
    }
    return MoCreatures.proxy.getTexture("bfpierisrapae.png");
  }


  public float tFloat() {
    if (!getIsFlying()) {
      return 0.0F;
    }
    if (++this.fCounter > 1000) {
      this.fCounter = 0;
    }

    return MathHelper.cos(this.fCounter * 0.1F) * 0.2F;
  }


  public float getSizeFactor() {
    if (getType() < 8) {
      return 0.7F;
    }
    return 1.0F;
  }


  public boolean isMyFavoriteFood(ItemStack stack) {
    return (!stack.isEmpty() && (stack
      .getItem() == Item.getItemFromBlock((Block)Blocks.RED_FLOWER) || stack.getItem() ==
      Item.getItemFromBlock((Block)Blocks.YELLOW_FLOWER)));
  }


  public boolean isAttractedToLight() {
    return (getType() > 7);
  }


  public boolean isFlyer() {
    return true;
  }


  public float getAIMoveSpeed() {
    if (getIsFlying()) {
      return 0.13F;
    }
    return 0.1F;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityButterfly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
