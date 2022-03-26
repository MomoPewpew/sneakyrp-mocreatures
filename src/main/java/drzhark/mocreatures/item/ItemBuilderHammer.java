package drzhark.mocreatures.item;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemBuilderHammer
  extends MoCItem
{
  public ItemBuilderHammer(String name) {
    super(name);
    this.maxStackSize = 1;
    setMaxDamage(2048);
  }





  public boolean isFull3D() {
    return true;
  }






  public EnumAction getItemUseAction(ItemStack par1ItemStack) {
    return EnumAction.BLOCK;
  }





  public int getMaxItemUseDuration(ItemStack par1ItemStack) {
    return 72000;
  }






  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
    ItemStack stack = player.getHeldItem(hand);
    double coordY = player.posY + player.getEyeHeight();
    double coordZ = player.posZ;
    double coordX = player.posX;

    for (int x = 3; x < 128; x++) {
      double newPosY = coordY - Math.cos(((player.rotationPitch - 90.0F) / 57.29578F)) * x;


      double newPosX = coordX + Math.cos((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * x;


      double newPosZ = coordZ + Math.sin((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * x;
      BlockPos pos = new BlockPos(MathHelper.floor(newPosX), MathHelper.floor(newPosY), MathHelper.floor(newPosZ));
      IBlockState blockstate = player.world.getBlockState(pos);

      if (blockstate.getBlock() != Blocks.AIR) {

        newPosY = coordY - Math.cos(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);


        newPosX = coordX + Math.cos((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);


        newPosZ = coordZ + Math.sin((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);
        pos = new BlockPos(MathHelper.floor(newPosX), MathHelper.floor(newPosY), MathHelper.floor(newPosZ));
        if (!player.world.isAirBlock(pos)) {
          return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
        }

        int[] blockInfo = obtainBlockAndMetadataFromBelt(player, true);
        if (blockInfo[0] != 0) {
          if (!worldIn.isRemote) {
            Block block = Block.getBlockById(blockInfo[0]);
            player.world.setBlockState(pos, block.getDefaultState(), 3);
            player.world.playSound(player, ((float)newPosX + 0.5F), ((float)newPosY + 0.5F), ((float)newPosZ + 0.5F), block
                .getSoundType().getPlaceSound(), SoundCategory.BLOCKS, (block.getSoundType().getVolume() + 1.0F) / 2.0F, block.getSoundType().getPitch() * 0.8F);
          }
          MoCreatures.proxy.hammerFX(player);
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
      }
    }
    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
  }








  private int[] obtainBlockAndMetadataFromBelt(EntityPlayer entityplayer, boolean remove) {
    for (int y = 0; y < 9; y++) {
      ItemStack slotStack = entityplayer.inventory.getStackInSlot(y);
      if (!slotStack.isEmpty()) {


        Item itemTemp = slotStack.getItem();
        int metadata = slotStack.getItemDamage();
        if (itemTemp instanceof net.minecraft.item.ItemBlock) {
          if (remove && !entityplayer.capabilities.isCreativeMode) {
            slotStack.shrink(1);
            if (slotStack.isEmpty()) {
              entityplayer.inventory.setInventorySlotContents(y, ItemStack.EMPTY);
            } else {
              entityplayer.inventory.setInventorySlotContents(y, slotStack);
            }
          }
          return new int[] { Item.getIdFromItem(itemTemp), metadata };
        }
      }
    }  return new int[] { 0, 0 };
  }



  public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
    return EnumActionResult.FAIL;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\ItemBuilderHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
