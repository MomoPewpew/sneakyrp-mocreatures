package drzhark.mocreatures.item;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.init.MoCSoundEvents;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemStaffTeleport extends MoCItem {
  public ItemStaffTeleport(String name) {
    super(name);
    this.maxStackSize = 1;
    setMaxDamage(128);
  }





  public boolean isFull3D() {
    return true;
  }






  public EnumAction getItemUseAction(ItemStack par1ItemStack) {
    return EnumAction.BLOCK;
  }






  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
    ItemStack stack = player.getHeldItem(hand);
    if (player.getRidingEntity() != null || player.isBeingRidden()) {
      return ActionResult.newResult(EnumActionResult.PASS, stack);
    }

    double coordY = player.posY + player.getEyeHeight();
    double coordZ = player.posZ;
    double coordX = player.posX;
    for (int x = 4; x < 128; x++) {
      double newPosY = coordY - Math.cos(((player.rotationPitch - 90.0F) / 57.29578F)) * x;


      double newPosX = coordX + Math.cos((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * x;


      double newPosZ = coordZ + Math.sin((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * x;
      BlockPos pos = new BlockPos(MathHelper.floor(newPosX), MathHelper.floor(newPosY), MathHelper.floor(newPosZ));
      IBlockState blockstate = player.world.getBlockState(pos);
      if (blockstate.getBlock() != Blocks.AIR) {
        newPosY = coordY - Math.cos(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);


        newPosX = coordX + Math.cos((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);


        newPosZ = coordZ + Math.sin((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F)) * Math.sin(((player.rotationPitch - 90.0F) / 57.29578F)) * (x - 1);

        if (!worldIn.isRemote) {
          EntityPlayerMP playerMP = (EntityPlayerMP)player;
          playerMP.connection.setPlayerLocation(newPosX, newPosY, newPosZ, player.rotationYaw, player.rotationPitch);

          MoCTools.playCustomSound((Entity)player, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR);
        }
        MoCreatures.proxy.teleportFX(player);

        stack.damageItem(1, (EntityLivingBase)player);

        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
      }
    }


    return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
  }


  public int getMaxItemUseDuration(ItemStack stack) {
    return 200;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\ItemStaffTeleport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
