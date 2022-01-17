/*    */ package drzhark.mocreatures.dimension;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.gen.feature.WorldGenerator;
/*    */ 
/*    */ public class WorldGenTower
/*    */   extends WorldGenerator
/*    */ {
/*    */   private Block MainBlock;
/*    */   private Block brickBlock;
/*    */   
/*    */   public WorldGenTower(Block Main, Block Brick, Block Deco) {
/* 17 */     this.MainBlock = Main;
/* 18 */     this.brickBlock = Brick;
/*    */   }
/*    */   
/*    */   public WorldGenTower(Block Main, int MainMeta, Block Brick, int BrickMeta, Block Deco, int DecoMeta) {
/* 22 */     this.MainBlock = Main;
/* 23 */     this.brickBlock = Brick;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World world, Random rand, BlockPos pos) {
/* 28 */     int t = 3;
/* 29 */     int x = pos.getX();
/* 30 */     int y = pos.getY();
/* 31 */     int z = pos.getZ();
/* 32 */     if (!world.isAirBlock(new BlockPos(x, y - 1, z))) {
/* 33 */       for (int i = 0; i < 9; i++) {
/* 34 */         if (world.isAirBlock(new BlockPos(x + 4 - i, y - 1, z))) {
/* 35 */           return false;
/*    */         }
/*    */       } 
/* 38 */       for (int j = y - 3; j < y + 21; j++) {
/* 39 */         int Nz; for (Nz = z - 5; Nz < z + 6; Nz += 10) {
/* 40 */           for (int m = x - 2; m < x + 3; m++) {
/* 41 */             BlockPos pos1 = new BlockPos(m, j, Nz);
/* 42 */             world.setBlockState(pos1, this.MainBlock.getDefaultState(), t);
/*    */           } 
/*    */         } 
/* 45 */         world.setBlockState(new BlockPos(x - 3, j, z - 4), this.MainBlock.getDefaultState(), t);
/* 46 */         world.setBlockState(new BlockPos(x + 3, j, z - 4), this.MainBlock.getDefaultState(), t);
/* 47 */         world.setBlockState(new BlockPos(x - 4, j, z - 3), this.MainBlock.getDefaultState(), t);
/* 48 */         world.setBlockState(new BlockPos(x + 4, j, z - 3), this.MainBlock.getDefaultState(), t);
/* 49 */         world.setBlockState(new BlockPos(x - 3, j, z + 4), this.MainBlock.getDefaultState(), t);
/* 50 */         world.setBlockState(new BlockPos(x + 3, j, z + 4), this.MainBlock.getDefaultState(), t);
/* 51 */         world.setBlockState(new BlockPos(x - 4, j, z + 3), this.MainBlock.getDefaultState(), t);
/* 52 */         world.setBlockState(new BlockPos(x + 4, j, z + 3), this.MainBlock.getDefaultState(), t);
/*    */         int k;
/* 54 */         for (k = x - 5; k < x + 6; k += 10) {
/* 55 */           for (int m = z - 2; m < z + 3; m++) {
/* 56 */             world.setBlockState(new BlockPos(k, j, m), this.MainBlock.getDefaultState(), t);
/*    */           }
/*    */         } 
/*    */       } 
/*    */       
/* 61 */       for (int Nx = x - 3; Nx < x + 4; Nx++) {
/* 62 */         for (int Nz = z - 3; Nz < z + 4; Nz++) {
/* 63 */           world.setBlockState(new BlockPos(Nx, y - 1, Nz), Blocks.FLOWING_LAVA.getDefaultState(), t);
/*    */         }
/*    */       } 
/*    */       int Ny;
/* 67 */       for (Ny = y; Ny < y + 24; Ny += 8) {
/* 68 */         world.setBlockState(new BlockPos(x - 1, Ny, z - 4), this.brickBlock.getDefaultState(), t);
/* 69 */         world.setBlockState(new BlockPos(x - 2, Ny, z - 4), this.brickBlock.getDefaultState(), t);
/* 70 */         world.setBlockState(new BlockPos(x - 4, Ny + 1, z - 1), this.brickBlock.getDefaultState(), t);
/* 71 */         world.setBlockState(new BlockPos(x - 4, Ny + 1, z - 2), this.brickBlock.getDefaultState(), t);
/* 72 */         world.setBlockState(new BlockPos(x - 4, Ny + 2, z + 1), this.brickBlock.getDefaultState(), t);
/* 73 */         world.setBlockState(new BlockPos(x - 4, Ny + 2, z + 2), this.brickBlock.getDefaultState(), t);
/* 74 */         world.setBlockState(new BlockPos(x - 2, Ny + 3, z + 4), this.brickBlock.getDefaultState(), t);
/* 75 */         world.setBlockState(new BlockPos(x - 1, Ny + 3, z + 4), this.brickBlock.getDefaultState(), t);
/* 76 */         world.setBlockState(new BlockPos(x + 1, Ny + 4, z + 4), this.brickBlock.getDefaultState(), t);
/* 77 */         world.setBlockState(new BlockPos(x + 2, Ny + 4, z + 4), this.brickBlock.getDefaultState(), t);
/* 78 */         world.setBlockState(new BlockPos(x + 4, Ny + 5, z + 2), this.brickBlock.getDefaultState(), t);
/* 79 */         world.setBlockState(new BlockPos(x + 4, Ny + 5, z + 1), this.brickBlock.getDefaultState(), t);
/* 80 */         world.setBlockState(new BlockPos(x + 4, Ny + 6, z - 1), this.brickBlock.getDefaultState(), t);
/* 81 */         world.setBlockState(new BlockPos(x + 4, Ny + 6, z - 2), this.brickBlock.getDefaultState(), t);
/* 82 */         world.setBlockState(new BlockPos(x + 2, Ny + 7, z - 4), this.brickBlock.getDefaultState(), t);
/* 83 */         world.setBlockState(new BlockPos(x + 1, Ny + 7, z - 4), this.brickBlock.getDefaultState(), t);
/*    */       } 
/* 85 */       return true;
/*    */     } 
/* 87 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\dimension\WorldGenTower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */