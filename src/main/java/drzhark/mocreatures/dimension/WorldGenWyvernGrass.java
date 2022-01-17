/*    */ package drzhark.mocreatures.dimension;
/*    */ 
/*    */ import drzhark.mocreatures.block.MoCBlockTallGrass;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.gen.feature.WorldGenerator;
/*    */ 
/*    */ public class WorldGenWyvernGrass
/*    */   extends WorldGenerator
/*    */ {
/*    */   private IBlockState iBlockStateGrass;
/*    */   private MoCBlockTallGrass grass;
/*    */   
/*    */   public WorldGenWyvernGrass(IBlockState iblockstategrass) {
/* 19 */     this.iBlockStateGrass = iblockstategrass;
/* 20 */     this.grass = (MoCBlockTallGrass)this.iBlockStateGrass.getBlock();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean generate(World worldIn, Random rand, BlockPos position) {
/*    */     do {
/* 28 */       IBlockState blockstate = worldIn.getBlockState(position);
/* 29 */       Block block = blockstate.getBlock();
/* 30 */       if (!block.isAir(blockstate, (IBlockAccess)worldIn, position) && !block.isLeaves(blockstate, (IBlockAccess)worldIn, position))
/*    */         break; 
/* 32 */       position = position.down();
/* 33 */     } while (position.getY() > 0);
/*    */     
/* 35 */     for (int i = 0; i < 128; i++) {
/*    */       
/* 37 */       BlockPos blockpos1 = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
/* 38 */       if (worldIn.isAirBlock(blockpos1) && this.grass.canBlockStay(worldIn, blockpos1, this.iBlockStateGrass)) {
/* 39 */         worldIn.setBlockState(blockpos1, this.iBlockStateGrass, 2);
/*    */       }
/*    */     } 
/*    */     
/* 43 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\dimension\WorldGenWyvernGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */