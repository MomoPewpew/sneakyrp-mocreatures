/*    */ package drzhark.mocreatures.dimension;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.gen.feature.WorldGenerator;
/*    */ 
/*    */ public class MoCWorldGenPortal
/*    */   extends WorldGenerator
/*    */ {
/*    */   private Block pillarBlock;
/*    */   private Block stairBlock;
/*    */   private Block wallBlock;
/*    */   private Block centerBlock;
/*    */   private int pillarMetadata;
/*    */   private int stairMetadata;
/*    */   private int wallMetadata;
/*    */   private int centerMetadata;
/*    */   
/*    */   public MoCWorldGenPortal(Block pillar, int pillarMeta, Block stair, int stairMeta, Block wall, int wallMeta, Block center, int centerMeta) {
/* 22 */     this.pillarBlock = pillar;
/* 23 */     this.stairBlock = stair;
/* 24 */     this.wallBlock = wall;
/* 25 */     this.centerBlock = center;
/* 26 */     this.pillarMetadata = pillarMeta;
/* 27 */     this.stairMetadata = stairMeta;
/* 28 */     this.wallMetadata = wallMeta;
/* 29 */     this.centerMetadata = centerMeta;
/*    */   }
/*    */   
/*    */   public boolean generatePillar(World world, BlockPos pos) {
/* 33 */     for (int nY = pos.getY(); nY < pos.getY() + 6; nY++) {
/* 34 */       world.setBlockState(new BlockPos(pos.getX(), nY, pos.getZ()), this.pillarBlock.getStateFromMeta(this.pillarMetadata), 2);
/*    */     }
/* 36 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World world, Random random, BlockPos pos) {
/* 41 */     if (world.getBlockState(pos).getBlock() == this.centerBlock || world.getBlockState(pos.down()).getBlock() == this.centerBlock || world
/* 42 */       .getBlockState(pos.up()).getBlock() == this.centerBlock) {
/* 43 */       return true;
/*    */     }
/*    */     
/* 46 */     if (world.isAirBlock(pos) || !world.isAirBlock(pos.up())) {
/* 47 */       return false;
/*    */     }
/*    */     
/* 50 */     int x = pos.getX();
/* 51 */     int y = pos.getY();
/* 52 */     int z = pos.getZ();
/*    */ 
/*    */     
/* 55 */     this.stairMetadata = 2; int nZ;
/* 56 */     for (nZ = z - 3; nZ < z + 3; nZ += 5) {
/* 57 */       for (int i = x - 2; i < x + 2; i++) {
/* 58 */         if (nZ > z) {
/* 59 */           this.stairMetadata = 3;
/*    */         }
/*    */         
/* 62 */         world.setBlockState(new BlockPos(i, y + 1, nZ), this.stairBlock.getStateFromMeta(this.stairMetadata), 2);
/*    */       } 
/*    */     } 
/*    */     int nX;
/* 66 */     for (nX = x - 2; nX < x + 2; nX++) {
/* 67 */       for (int i = z - 2; i < z + 2; i++) {
/* 68 */         world.setBlockState(new BlockPos(nX, y + 1, i), this.wallBlock.getStateFromMeta(this.wallMetadata), 2);
/*    */       }
/*    */     } 
/*    */     
/* 72 */     for (nX = x - 1; nX < x + 1; nX++) {
/* 73 */       for (int i = z - 1; i < z + 1; i++) {
/* 74 */         world.setBlockState(new BlockPos(nX, y + 1, i), this.centerBlock.getStateFromMeta(this.centerMetadata), 2);
/*    */       }
/*    */     } 
/*    */     int j;
/* 78 */     for (j = x - 3; j < x + 3; j += 5) {
/* 79 */       for (int i = z - 3; i < z + 3; i++) {
/* 80 */         world.setBlockState(new BlockPos(j, y + 6, i), this.wallBlock.getStateFromMeta(this.wallMetadata), 2);
/*    */       }
/*    */     } 
/*    */     
/* 84 */     generatePillar(world, new BlockPos(x - 3, y, z - 3));
/* 85 */     generatePillar(world, new BlockPos(x - 3, y, z + 2));
/* 86 */     generatePillar(world, new BlockPos(x + 2, y, z - 3));
/* 87 */     generatePillar(world, new BlockPos(x + 2, y, z + 2));
/*    */     
/* 89 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\dimension\MoCWorldGenPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */