package drzhark.mocreatures.item;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public class ItemStaffPortal extends MoCItem {
  public ItemStaffPortal(String name) {
    super(name);
    this.maxStackSize = 1;
    setMaxDamage(3);
  }




  public boolean isFull3D() {
    return true;
  }








  public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
    return false;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\ItemStaffPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
