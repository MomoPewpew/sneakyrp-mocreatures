package drzhark.mocreatures.item;

import javax.annotation.Nullable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MoCItemTurtleSoup
  extends MoCItemFood
{
  public MoCItemTurtleSoup(String name, int j) {
    super(name, j);
    this.maxStackSize = 1;
  }

  public MoCItemTurtleSoup(String name, int j, float f, boolean flag) {
    super(name, j, f, flag);
    this.maxStackSize = 1;
  }


  @Nullable
  public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
    super.onItemUseFinish(stack, worldIn, entityLiving);
    return new ItemStack(Items.BOWL);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemTurtleSoup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
