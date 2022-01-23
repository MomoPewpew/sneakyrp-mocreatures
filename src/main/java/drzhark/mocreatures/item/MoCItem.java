package drzhark.mocreatures.item;

import drzhark.mocreatures.MoCConstants;
import drzhark.mocreatures.MoCreatures;
import net.minecraft.item.Item;


public class MoCItem
  extends Item
{
  public MoCItem() {}

  public MoCItem(String name) {
    this(name, 0);
  }

  public MoCItem(String name, int meta) {
    setCreativeTab(MoCreatures.tabMoC);
    setRegistryName(MoCConstants.MOD_ID, name);
    setUnlocalizedName(name);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
