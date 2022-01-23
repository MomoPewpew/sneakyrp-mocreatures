package drzhark.mocreatures.item;

import drzhark.mocreatures.MoCreatures;
import net.minecraft.item.ItemFood;

public class MoCItemFood
  extends ItemFood
{
  public MoCItemFood(String name, int j) {
    super(j, 0.6F, false);
    setCreativeTab(MoCreatures.tabMoC);
    setRegistryName("mocreatures", name);
    setUnlocalizedName(name);
    this.maxStackSize = 32;
  }

  public MoCItemFood(String name, int j, float f, boolean flag) {
    super(j, f, flag);
    setCreativeTab(MoCreatures.tabMoC);
    setRegistryName("mocreatures", name);
    setUnlocalizedName(name);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
