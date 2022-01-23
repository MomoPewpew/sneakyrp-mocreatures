package drzhark.mocreatures.entity.aquatic;

import drzhark.mocreatures.MoCreatures;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityBass
  extends MoCEntityMediumFish {
  public MoCEntityBass(World world) {
    super(world);
    setType(3);
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture("mediumfish_bass.png");
  }


  protected int getEggNumber() {
    return 72;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityBass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
