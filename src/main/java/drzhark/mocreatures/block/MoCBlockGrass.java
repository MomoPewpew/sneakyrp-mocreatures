/*    */ package drzhark.mocreatures.block;
/*    */ 
/*    */ import drzhark.mocreatures.init.MoCBlocks;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MoCBlockGrass
/*    */   extends MoCBlock {
/*    */   public MoCBlockGrass(String name) {
/* 15 */     super(name, Material.GRASS);
/* 16 */     setTickRandomly(true);
/* 17 */     setSoundType(SoundType.PLANT);
/*    */   }
/*    */   
/*    */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 21 */     if (!worldIn.isRemote)
/* 22 */       if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getLightOpacity((IBlockAccess)worldIn, pos.up()) > 2) {
/* 23 */         worldIn.setBlockState(pos, MoCBlocks.mocDirt.getDefaultState());
/*    */       }
/* 25 */       else if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
/* 26 */         for (int i = 0; i < 4; i++) {
/* 27 */           BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
/* 28 */           IBlockState blockstate = worldIn.getBlockState(blockpos1.up());
/* 29 */           IBlockState iblockstate1 = worldIn.getBlockState(blockpos1);
/*    */           
/* 31 */           if (iblockstate1.getBlock() == MoCBlocks.mocDirt && worldIn.getLightFromNeighbors(blockpos1.up()) >= 4 && blockstate
/* 32 */             .getLightOpacity((IBlockAccess)worldIn, blockpos1.up()) <= 2)
/* 33 */             worldIn.setBlockState(blockpos1, MoCBlocks.mocGrass.getDefaultState()); 
/*    */         } 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\block\MoCBlockGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */