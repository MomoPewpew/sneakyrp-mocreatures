package drzhark.mocreatures.block;

import drzhark.mocreatures.init.MoCBlocks;
import java.util.Random;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MoCBlockGrass
  extends MoCBlock {
  public MoCBlockGrass(String name) {
    super(name, Material.GRASS);
    setTickRandomly(true);
    setSoundType(SoundType.PLANT);
  }

  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    if (!worldIn.isRemote)
      if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity((IBlockAccess)worldIn, pos.up()) > 2) {
        worldIn.setBlockState(pos, MoCBlocks.mocDirt.getDefaultState());
      }
      else if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
        for (int i = 0; i < 4; i++) {
          BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
          IBlockState blockstate = worldIn.getBlockState(blockpos1.up());
          IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);

          if (iblockstate1.getBlock() == MoCBlocks.mocDirt && worldIn.getLightFromNeighbors(blockpos1.up()) >= 4 && blockstate
            .getLightOpacity((IBlockAccess)worldIn, blockpos1.up()) <= 2)
            worldIn.setBlockState(blockpos1, MoCBlocks.mocGrass.getDefaultState());
        }
      }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\block\MoCBlockGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
