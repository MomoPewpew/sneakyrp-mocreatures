package drzhark.mocreatures.item;

import drzhark.mocreatures.entity.aquatic.MoCEntityFishy;
import drzhark.mocreatures.entity.aquatic.MoCEntityMediumFish;
import drzhark.mocreatures.entity.aquatic.MoCEntitySmallFish;
import drzhark.mocreatures.entity.item.MoCEntityEgg;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MoCItemEgg extends MoCItem {
  public MoCItemEgg(String name) {
    super(name);
    this.maxStackSize = 16;
    setHasSubtypes(true);
  }


  public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
    ItemStack stack = player.getHeldItem(hand);
    stack.shrink(1);
    if (!world.isRemote && player.onGround) {
      int i = stack.getItemDamage();
      if (i == 30) {
        i = 31;
      }
      MoCEntityEgg entityegg = new MoCEntityEgg(world, i);
      entityegg.setPosition(player.posX, player.posY, player.posZ);
      player.world.spawnEntity((Entity)entityegg);
      entityegg.motionY += (world.rand.nextFloat() * 0.05F);
      entityegg.motionX += ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F);
      entityegg.motionZ += ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.3F);
    }
    return new ActionResult(EnumActionResult.SUCCESS, stack);
  }


  @SideOnly(Side.CLIENT)
  public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
    if (!isInCreativeTab(tab)) {
      return;
    }


    int length = MoCEntityFishy.fishNames.length; int i;
    for (i = 0; i < length; i++) {
      items.add(new ItemStack(this, 1, i));
    }
    length = 80 + MoCEntitySmallFish.fishNames.length;
    for (i = 80; i < length; i++) {
      items.add(new ItemStack(this, 1, i));
    }
    length = 70 + MoCEntityMediumFish.fishNames.length;
    for (i = 70; i < length; i++) {
      items.add(new ItemStack(this, 1, i));
    }
    items.add(new ItemStack(this, 1, 90));
    items.add(new ItemStack(this, 1, 11));
    for (i = 21; i < 28; i++) {
      items.add(new ItemStack(this, 1, i));
    }
    items.add(new ItemStack(this, 1, 30));
    items.add(new ItemStack(this, 1, 31));
    items.add(new ItemStack(this, 1, 33));
    for (i = 41; i < 46; i++) {
      items.add(new ItemStack(this, 1, i));
    }
    for (i = 50; i < 65; i++) {
      items.add(new ItemStack(this, 1, i));
    }
  }


  public String getUnlocalizedName(ItemStack itemstack) {
    return getUnlocalizedName() + "." + itemstack.getItemDamage();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
