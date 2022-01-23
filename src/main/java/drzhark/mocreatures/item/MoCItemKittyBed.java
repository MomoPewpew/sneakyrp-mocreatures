package drzhark.mocreatures.item;

import drzhark.mocreatures.entity.item.MoCEntityKittyBed;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class MoCItemKittyBed extends MoCItem {
  private int sheetType;

  public MoCItemKittyBed(String name) {
    super(name);
  }

  public MoCItemKittyBed(String name, int type) {
    this(name);
    this.sheetType = type;
  }


  public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
    ItemStack stack = player.getHeldItem(hand);
    if (!world.isRemote) {
      MoCEntityKittyBed entitykittybed = new MoCEntityKittyBed(world, this.sheetType);
      entitykittybed.setPosition(player.posX, player.posY, player.posZ);
      world.spawnEntity((Entity)entitykittybed);
      entitykittybed.motionY += (world.rand.nextFloat() * 0.05F);
      entitykittybed.motionX += ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F);
      entitykittybed.motionZ += ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F);
      stack.shrink(1);
      if (stack.isEmpty()) {
        player.setHeldItem(hand, ItemStack.EMPTY);
      }
    }
    return new ActionResult(EnumActionResult.SUCCESS, stack);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemKittyBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
