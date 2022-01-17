/*     */ package drzhark.mocreatures.dimension;
/*     */ 
/*     */ import drzhark.mocreatures.init.MoCBlocks;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ 
/*     */ public class MoCWorldGenBigTree
/*     */   extends WorldGenAbstractTree
/*     */ {
/*     */   public MoCWorldGenBigTree(boolean par1) {
/*  17 */     super(par1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     this.rand = new Random();
/*     */ 
/*     */ 
/*     */     
/*  51 */     this.basePos = new int[] { 0, 0, 0 };
/*  52 */     this.heightLimit = 20;
/*     */     
/*  54 */     this.heightAttenuation = 0.618D;
/*  55 */     this.branchDensity = 1.0D;
/*  56 */     this.branchSlope = 0.381D;
/*  57 */     this.scaleWidth = 1.0D;
/*  58 */     this.leafDensity = 1.0D; } public MoCWorldGenBigTree(boolean par1, IBlockState iblockstateLog, IBlockState iblockstateleaf, int trunksize, int heightlimit, int leafdist) { super(par1); this.rand = new Random(); this.basePos = new int[] { 0, 0, 0 }; this.heightLimit = 20; this.heightAttenuation = 0.618D; this.branchDensity = 1.0D; this.branchSlope = 0.381D; this.scaleWidth = 1.0D; this.leafDensity = 1.0D;
/*     */     this.iBlockStateLog = iblockstateLog;
/*     */     this.iBlockStateLeaf = iblockstateleaf;
/*     */     this.trunkSize = trunksize;
/*     */     this.heightLimitLimit = heightlimit;
/*     */     this.leafDistanceLimit = leafdist; }
/*     */ 
/*     */ 
/*     */   
/*     */   static final byte[] otherCoordPairs = new byte[] { 2, 0, 0, 1, 2, 1 };
/*     */   
/*     */   Random rand;
/*     */   World world;
/*     */   int[] basePos;
/*     */   int heightLimit;
/*     */   int height;
/*     */   double heightAttenuation;
/*     */   double branchDensity;
/*     */   double branchSlope;
/*     */   double scaleWidth;
/*     */   double leafDensity;
/*     */   private IBlockState iBlockStateLog;
/*     */   private IBlockState iBlockStateLeaf;
/*     */   int trunkSize;
/*     */   int heightLimitLimit;
/*     */   int leafDistanceLimit;
/*     */   int[][] leafNodes;
/*     */   
/*     */   void generateLeafNodeList() {
/*  87 */     this.height = (int)(this.heightLimit * this.heightAttenuation);
/*     */     
/*  89 */     if (this.height >= this.heightLimit) {
/*  90 */       this.height = this.heightLimit - 1;
/*     */     }
/*     */     
/*  93 */     int var1 = (int)(1.382D + Math.pow(this.leafDensity * this.heightLimit / 13.0D, 2.0D));
/*     */     
/*  95 */     if (var1 < 1) {
/*  96 */       var1 = 1;
/*     */     }
/*     */     
/*  99 */     int[][] var2 = new int[var1 * this.heightLimit][4];
/* 100 */     int var3 = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
/* 101 */     int var4 = 1;
/* 102 */     int var5 = this.basePos[1] + this.height;
/* 103 */     int var6 = var3 - this.basePos[1];
/* 104 */     var2[0][0] = this.basePos[0];
/* 105 */     var2[0][1] = var3;
/* 106 */     var2[0][2] = this.basePos[2];
/* 107 */     var2[0][3] = var5;
/* 108 */     var3--;
/*     */     
/* 110 */     while (var6 >= 0) {
/* 111 */       int var7 = 0;
/* 112 */       float var8 = layerSize(var6);
/*     */       
/* 114 */       if (var8 < 0.0F) {
/* 115 */         var3--;
/* 116 */         var6--; continue;
/*     */       } 
/* 118 */       for (double var9 = 0.5D; var7 < var1; var7++) {
/* 119 */         double var11 = this.scaleWidth * var8 * (this.rand.nextFloat() + 0.328D);
/* 120 */         double var13 = this.rand.nextFloat() * 2.0D * Math.PI;
/* 121 */         int var15 = MathHelper.floor(var11 * Math.sin(var13) + this.basePos[0] + var9);
/* 122 */         int var16 = MathHelper.floor(var11 * Math.cos(var13) + this.basePos[2] + var9);
/* 123 */         int[] var17 = { var15, var3, var16 };
/* 124 */         int[] var18 = { var15, var3 + this.leafDistanceLimit, var16 };
/*     */         
/* 126 */         if (checkBlockLine(var17, var18) == -1) {
/* 127 */           int[] var19 = { this.basePos[0], this.basePos[1], this.basePos[2] };
/*     */           
/* 129 */           double var20 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - var17[0]), 2.0D) + Math.pow(Math.abs(this.basePos[2] - var17[2]), 2.0D));
/* 130 */           double var22 = var20 * this.branchSlope;
/*     */           
/* 132 */           if (var17[1] - var22 > var5) {
/* 133 */             var19[1] = var5;
/*     */           } else {
/* 135 */             var19[1] = (int)(var17[1] - var22);
/*     */           } 
/*     */           
/* 138 */           if (checkBlockLine(var19, var17) == -1) {
/* 139 */             var2[var4][0] = var15;
/* 140 */             var2[var4][1] = var3;
/* 141 */             var2[var4][2] = var16;
/* 142 */             var2[var4][3] = var19[1];
/* 143 */             var4++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 148 */       var3--;
/* 149 */       var6--;
/*     */     } 
/*     */ 
/*     */     
/* 153 */     this.leafNodes = new int[var4][4];
/* 154 */     System.arraycopy(var2, 0, this.leafNodes, 0, var4);
/*     */   }
/*     */   
/*     */   void func_150529_a(int par1, int par2, int par3, float par4, byte par5, Block par6) {
/* 158 */     int var7 = (int)(par4 + 0.618D);
/* 159 */     byte var8 = otherCoordPairs[par5];
/* 160 */     byte var9 = otherCoordPairs[par5 + 3];
/* 161 */     int[] var10 = { par1, par2, par3 };
/* 162 */     int[] var11 = { 0, 0, 0 };
/* 163 */     int var12 = -var7;
/* 164 */     int var13 = -var7;
/*     */     
/* 166 */     for (var11[par5] = var10[par5]; var12 <= var7; var12++) {
/* 167 */       var11[var8] = var10[var8] + var12;
/* 168 */       var13 = -var7;
/*     */       
/* 170 */       while (var13 <= var7) {
/* 171 */         double var15 = Math.pow(Math.abs(var12) + 0.5D, 2.0D) + Math.pow(Math.abs(var13) + 0.5D, 2.0D);
/*     */         
/* 173 */         if (var15 > (par4 * par4)) {
/* 174 */           var13++; continue;
/*     */         } 
/* 176 */         var11[var9] = var10[var9] + var13;
/* 177 */         BlockPos pos = new BlockPos(var11[0], var11[1], var11[2]);
/* 178 */         IBlockState blockstate = this.world.getBlockState(pos);
/* 179 */         Block block = blockstate.getBlock();
/*     */         
/* 181 */         if (block != Blocks.AIR && block != this.iBlockStateLeaf.getBlock()) {
/*     */           
/* 183 */           var13++; continue;
/*     */         } 
/* 185 */         setBlockAndNotifyAdequately(this.world, pos, this.iBlockStateLeaf);
/* 186 */         var13++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float layerSize(int par1) {
/*     */     float var4;
/* 197 */     if (par1 < this.heightLimit * 0.3D) {
/* 198 */       return -1.618F;
/*     */     }
/* 200 */     float var2 = this.heightLimit / 2.0F;
/* 201 */     float var3 = this.heightLimit / 2.0F - par1;
/*     */ 
/*     */     
/* 204 */     if (var3 == 0.0F) {
/* 205 */       var4 = var2;
/* 206 */     } else if (Math.abs(var3) >= var2) {
/* 207 */       var4 = 0.0F;
/*     */     } else {
/* 209 */       var4 = (float)Math.sqrt(Math.pow(Math.abs(var2), 2.0D) - Math.pow(Math.abs(var3), 2.0D));
/*     */     } 
/*     */     
/* 212 */     var4 *= 0.5F;
/* 213 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   float leafSize(int par1) {
/* 218 */     return (par1 >= 0 && par1 < this.leafDistanceLimit) ? ((par1 != 0 && par1 != this.leafDistanceLimit - 1) ? 3.0F : 2.0F) : -1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeafNode(int par1, int par2, int par3) {
/* 226 */     int var4 = par2;
/*     */     
/* 228 */     for (int var5 = par2 + this.leafDistanceLimit; var4 < var5; var4++) {
/* 229 */       float var6 = leafSize(var4 - par2);
/* 230 */       func_150529_a(par1, var4, par3, var6, (byte)1, (Block)MoCBlocks.mocLeaf);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void func_150530_a(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, Block par3) {
/* 239 */     int[] var4 = { 0, 0, 0 };
/* 240 */     byte var5 = 0;
/*     */     
/*     */     byte var6;
/* 243 */     for (var6 = 0; var5 < 3; var5 = (byte)(var5 + 1)) {
/* 244 */       var4[var5] = par2ArrayOfInteger[var5] - par1ArrayOfInteger[var5];
/*     */       
/* 246 */       if (Math.abs(var4[var5]) > Math.abs(var4[var6])) {
/* 247 */         var6 = var5;
/*     */       }
/*     */     } 
/*     */     
/* 251 */     if (var4[var6] != 0) {
/* 252 */       byte var9, var7 = otherCoordPairs[var6];
/* 253 */       byte var8 = otherCoordPairs[var6 + 3];
/*     */ 
/*     */       
/* 256 */       if (var4[var6] > 0) {
/* 257 */         var9 = 1;
/*     */       } else {
/* 259 */         var9 = -1;
/*     */       } 
/*     */       
/* 262 */       double var10 = var4[var7] / var4[var6];
/* 263 */       double var12 = var4[var8] / var4[var6];
/* 264 */       int[] var14 = { 0, 0, 0 };
/* 265 */       int var15 = 0;
/*     */       
/* 267 */       for (int var16 = var4[var6] + var9; var15 != var16; var15 += var9) {
/* 268 */         var14[var6] = MathHelper.floor((par1ArrayOfInteger[var6] + var15) + 0.5D);
/* 269 */         var14[var7] = MathHelper.floor(par1ArrayOfInteger[var7] + var15 * var10 + 0.5D);
/* 270 */         var14[var8] = MathHelper.floor(par1ArrayOfInteger[var8] + var15 * var12 + 0.5D);
/* 271 */         setBlockAndNotifyAdequately(this.world, new BlockPos(var14[0], var14[1], var14[2]), this.iBlockStateLog);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeaves() {
/* 281 */     int var1 = 0;
/*     */     
/* 283 */     for (int var2 = this.leafNodes.length; var1 < var2; var1++) {
/* 284 */       int var3 = this.leafNodes[var1][0];
/* 285 */       int var4 = this.leafNodes[var1][1];
/* 286 */       int var5 = this.leafNodes[var1][2];
/* 287 */       generateLeafNode(var3, var4, var5);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean leafNodeNeedsBase(int par1) {
/* 296 */     return (par1 >= this.heightLimit * 0.2D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateTrunk() {
/* 304 */     int var1 = this.basePos[0];
/* 305 */     int var2 = this.basePos[1];
/* 306 */     int var3 = this.basePos[1] + this.height;
/* 307 */     int var4 = this.basePos[2];
/* 308 */     int[] var5 = { var1, var2, var4 };
/* 309 */     int[] var6 = { var1, var3, var4 };
/* 310 */     func_150530_a(var5, var6, this.iBlockStateLog.getBlock());
/*     */     
/* 312 */     if (this.trunkSize == 2) {
/* 313 */       var5[0] = var5[0] + 1;
/* 314 */       var6[0] = var6[0] + 1;
/* 315 */       func_150530_a(var5, var6, this.iBlockStateLog.getBlock());
/* 316 */       var5[2] = var5[2] + 1;
/* 317 */       var6[2] = var6[2] + 1;
/* 318 */       func_150530_a(var5, var6, this.iBlockStateLog.getBlock());
/* 319 */       var5[0] = var5[0] + -1;
/* 320 */       var6[0] = var6[0] + -1;
/* 321 */       func_150530_a(var5, var6, this.iBlockStateLog.getBlock());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeafNodeBases() {
/* 330 */     int var1 = 0;
/* 331 */     int var2 = this.leafNodes.length;
/*     */     
/* 333 */     for (int[] var3 = { this.basePos[0], this.basePos[1], this.basePos[2] }; var1 < var2; var1++) {
/* 334 */       int[] var4 = this.leafNodes[var1];
/* 335 */       int[] var5 = { var4[0], var4[1], var4[2] };
/* 336 */       var3[1] = var4[3];
/* 337 */       int var6 = var3[1] - this.basePos[1];
/*     */       
/* 339 */       if (leafNodeNeedsBase(var6)) {
/* 340 */         func_150530_a(var3, var5, this.iBlockStateLog.getBlock());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger) {
/*     */     byte var8;
/* 351 */     int[] var3 = { 0, 0, 0 };
/* 352 */     byte var4 = 0;
/*     */     
/*     */     byte var5;
/* 355 */     for (var5 = 0; var4 < 3; var4 = (byte)(var4 + 1)) {
/* 356 */       var3[var4] = par2ArrayOfInteger[var4] - par1ArrayOfInteger[var4];
/*     */       
/* 358 */       if (Math.abs(var3[var4]) > Math.abs(var3[var5])) {
/* 359 */         var5 = var4;
/*     */       }
/*     */     } 
/*     */     
/* 363 */     if (var3[var5] == 0) {
/* 364 */       return -1;
/*     */     }
/* 366 */     byte var6 = otherCoordPairs[var5];
/* 367 */     byte var7 = otherCoordPairs[var5 + 3];
/*     */ 
/*     */     
/* 370 */     if (var3[var5] > 0) {
/* 371 */       var8 = 1;
/*     */     } else {
/* 373 */       var8 = -1;
/*     */     } 
/*     */     
/* 376 */     double var9 = var3[var6] / var3[var5];
/* 377 */     double var11 = var3[var7] / var3[var5];
/* 378 */     int[] var13 = { 0, 0, 0 };
/* 379 */     int var14 = 0;
/*     */ 
/*     */     
/* 382 */     int var15 = var3[var5] + var8;
/* 383 */     var13[var5] = par1ArrayOfInteger[var5] + var14;
/* 384 */     var13[var6] = MathHelper.floor(par1ArrayOfInteger[var6] + var14 * var9);
/* 385 */     var13[var7] = MathHelper.floor(par1ArrayOfInteger[var7] + var14 * var11);
/*     */     
/* 387 */     for (; var14 != var15 && isReplaceable(this.world, new BlockPos(var13[0], var13[1], var13[2])); var14 += var8);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 392 */     return (var14 == var15) ? -1 : Math.abs(var14);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean validTreeLocation(BlockPos pos, World par1World) {
/* 401 */     int[] var1 = { pos.getX(), pos.getY(), pos.getZ() };
/* 402 */     int[] var2 = { pos.getX(), pos.getY() + this.heightLimit - 1, pos.getZ() };
/* 403 */     Block block = par1World.getBlockState(pos.down()).getBlock();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 412 */     if (block != MoCBlocks.mocDirt && block != MoCBlocks.mocGrass) {
/* 413 */       return false;
/*     */     }
/* 415 */     int var4 = checkBlockLine(var1, var2);
/*     */     
/* 417 */     if (var4 == -1)
/* 418 */       return true; 
/* 419 */     if (var4 < 6) {
/* 420 */       return false;
/*     */     }
/* 422 */     this.heightLimit = var4;
/* 423 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScale(double par1, double par3, double par5) {
/* 432 */     this.heightLimitLimit = (int)(par1 * 12.0D);
/*     */     
/* 434 */     if (par1 > 0.5D) {
/* 435 */       this.leafDistanceLimit = 5;
/*     */     }
/*     */     
/* 438 */     this.scaleWidth = par3;
/* 439 */     this.leafDensity = par5;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World par1World, Random par2Random, BlockPos pos) {
/* 444 */     this.world = par1World;
/* 445 */     long var6 = par2Random.nextLong();
/* 446 */     this.rand.setSeed(var6);
/* 447 */     this.basePos[0] = pos.getX();
/* 448 */     this.basePos[1] = pos.getY();
/* 449 */     this.basePos[2] = pos.getZ();
/* 450 */     if (this.heightLimit == 0) {
/* 451 */       this.heightLimit = 5 + this.rand.nextInt(this.heightLimitLimit);
/*     */     }
/*     */     
/* 454 */     if (!validTreeLocation(pos, par1World)) {
/* 455 */       return false;
/*     */     }
/* 457 */     generateLeafNodeList();
/* 458 */     generateLeaves();
/* 459 */     generateTrunk();
/* 460 */     generateLeafNodeBases();
/* 461 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\dimension\MoCWorldGenBigTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */