package drzhark.mocreatures.entity.aquatic;

import drzhark.mocreatures.MoCreatures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityManderin
  extends MoCEntitySmallFish {
  public MoCEntityManderin(World world) {
    super(world);
    setType(7);
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture("smallfish_manderin.png");
  }


  protected int getEggNumber() {
    return 86;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityManderin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
