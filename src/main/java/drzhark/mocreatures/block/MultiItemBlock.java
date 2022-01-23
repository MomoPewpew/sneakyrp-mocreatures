package drzhark.mocreatures.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;

public class MultiItemBlock
  extends ItemMultiTexture {
  public MultiItemBlock(Block block) {
    super(block, block, new ItemMultiTexture.Mapper()
        {
          public String apply(ItemStack stack) {
            return MoCBlock.EnumType.byMetadata(stack.getMetadata()).getUnlocalizedName();
          }
        });
    setHasSubtypes(true);
  }


  public int getMetadata(int damageValue) {
    return damageValue;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\block\MultiItemBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
