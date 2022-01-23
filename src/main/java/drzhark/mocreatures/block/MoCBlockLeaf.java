package drzhark.mocreatures.block;

import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.init.MoCBlocks;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class MoCBlockLeaf
  extends MoCBlock
{
  public MoCBlockLeaf(String name) {
    super(name, Material.LEAVES);
    setTickRandomly(true);
    setCreativeTab(MoCreatures.tabMoC);
    setUnlocalizedName(name);
    setSoundType(SoundType.PLANT);
  }


  public boolean isFullCube(IBlockState state) {
    return false;
  }


  public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
    return true;
  }


  public int quantityDropped(Random random) {
    return (random.nextInt(20) != 0) ? 0 : 1;
  }


  public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack) {
    if (!worldIn.isRemote && !stack.isEmpty() && stack.getItem() == Items.SHEARS) {
      player.addStat(StatList.getBlockStats(this), 1);
      spawnAsEntity(worldIn, pos, new ItemStack(MoCBlocks.mocLeaf, 1, 0));
    } else {
      super.harvestBlock(worldIn, player, pos, state, te, stack);
    }
  }


  public int quantityDropped(IBlockState state, int fortune, Random random) {
    return 0;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\block\MoCBlockLeaf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
