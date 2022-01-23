package drzhark.mocreatures.client;

import drzhark.mocreatures.init.MoCItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MoCCreativeTabs
  extends CreativeTabs {
  public MoCCreativeTabs(int length, String name) {
    super(length, name);
  }


  @SideOnly(Side.CLIENT)
  public ItemStack getTabIconItem() {
    return new ItemStack((Item)MoCItems.amuletfairyfull, 1);
  }




  @SideOnly(Side.CLIENT)
  public void displayAllRelevantItems(NonNullList<ItemStack> items) {
    for (Item item : Item.REGISTRY) {

      if (item == MoCItems.mocegg) {
        continue;
      }
      item.getSubItems(this, items);
    }

    MoCItems.mocegg.getSubItems(this, items);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\MoCCreativeTabs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
