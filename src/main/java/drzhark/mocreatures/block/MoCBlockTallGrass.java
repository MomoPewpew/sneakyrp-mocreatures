package drzhark.mocreatures.block;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.init.MoCBlocks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;

public class MoCBlockTallGrass extends MoCBlockBush implements IShearable {
  protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);

  public MoCBlockTallGrass(String name) {
    super(name, Material.VINE);
    setCreativeTab(MoCreatures.tabMoC);
    setSoundType(SoundType.PLANT);
  }

  public MoCBlockTallGrass(String name, boolean lighted) {
    this(name);
    if (lighted) {
      setLightLevel(0.8F);
    }
  }


  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return AABB;
  }




  public Item getItemDropped(int par1, Random par2Random, int par3) {
    return null;
  }






  public int quantityDroppedWithBonus(int par1, Random par2Random) {
    return 1 + par2Random.nextInt(par1 * 2 + 1);
  }


  public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
    return true;
  }


  public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
    return new ArrayList<>(Arrays.asList(new ItemStack[] { new ItemStack((Block)MoCBlocks.mocTallGrass) }));
  }


  public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
    Block soil = worldIn.getBlockState(pos.down()).getBlock();
    return (soil == MoCBlocks.mocGrass || soil == MoCBlocks.mocDirt || soil == Blocks.GRASS || soil == Blocks.DIRT || soil == Blocks.FARMLAND);
  }




  public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
    IBlockState soil = worldIn.getBlockState(pos.down());
    Block tempblock = soil.getBlock();
    if (tempblock instanceof MoCBlockDirt || tempblock instanceof MoCBlockGrass) {
      return true;
    }
    return (super.canPlaceBlockAt(worldIn, pos) && soil.getBlock().canSustainPlant(soil, (IBlockAccess)worldIn, pos.down(), EnumFacing.UP, (IPlantable)this));
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\block\MoCBlockTallGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
