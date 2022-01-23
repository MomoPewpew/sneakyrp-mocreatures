package drzhark.mocreatures.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class MoCBlockDirt
  extends MoCBlock {
  public MoCBlockDirt(String name) {
    super(name, Material.GROUND);
    setTickRandomly(true);
    setSoundType(SoundType.GROUND);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\block\MoCBlockDirt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
