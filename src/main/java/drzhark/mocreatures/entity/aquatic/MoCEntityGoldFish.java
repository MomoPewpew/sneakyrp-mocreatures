package drzhark.mocreatures.entity.aquatic;

import drzhark.mocreatures.MoCreatures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityGoldFish
  extends MoCEntitySmallFish {
  public MoCEntityGoldFish(World world) {
    super(world);
    setType(5);
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture("smallfish_goldfish.png");
  }


  protected int getEggNumber() {
    return 84;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityGoldFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
