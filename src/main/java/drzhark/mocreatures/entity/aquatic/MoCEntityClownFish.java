package drzhark.mocreatures.entity.aquatic;

import drzhark.mocreatures.MoCreatures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityClownFish
  extends MoCEntitySmallFish {
  public MoCEntityClownFish(World world) {
    super(world);
    setType(4);
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture("smallfish_clownfish.png");
  }


  protected int getEggNumber() {
    return 83;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityClownFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
