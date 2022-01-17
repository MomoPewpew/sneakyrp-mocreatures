/*     */ package drzhark.mocreatures.dimension;
/*     */ import drzhark.mocreatures.init.MoCBlocks;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFalling;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.ChunkPrimer;
/*     */ import net.minecraft.world.gen.NoiseGeneratorOctaves;
/*     */ import net.minecraft.world.gen.NoiseGeneratorSimplex;
/*     */ import net.minecraft.world.gen.feature.WorldGenEndIsland;
/*     */ import net.minecraft.world.gen.feature.WorldGenLakes;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
/*     */ import net.minecraftforge.event.terraingen.InitNoiseGensEvent;
/*     */ import net.minecraftforge.event.terraingen.TerrainGen;
/*     */ import net.minecraftforge.fml.common.eventhandler.Event;
/*     */ 
/*     */ public class ChunkGeneratorWyvernLair implements IChunkGenerator {
/*     */   private final Random rand;
/*  28 */   protected static final IBlockState WYVERN_STONE = MoCBlocks.mocStone.getDefaultState();
/*  29 */   protected static final IBlockState WYVERN_DIRT = MoCBlocks.mocDirt.getDefaultState();
/*  30 */   protected static final IBlockState WYVERN_GRASS = MoCBlocks.mocGrass.getDefaultState();
/*  31 */   protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
/*     */   
/*     */   private NoiseGeneratorOctaves lperlinNoise1;
/*     */   
/*     */   private NoiseGeneratorOctaves lperlinNoise2;
/*     */   
/*     */   private NoiseGeneratorOctaves perlinNoise1;
/*     */   
/*     */   public NoiseGeneratorOctaves noiseGen5;
/*     */   
/*     */   public NoiseGeneratorOctaves noiseGen6;
/*     */   
/*     */   private final World world;
/*     */   private final boolean mapFeaturesEnabled;
/*     */   private NoiseGeneratorSimplex islandNoise;
/*     */   private double[] buffer;
/*     */   private Biome[] biomesForGeneration;
/*     */   double[] pnr;
/*     */   double[] ar;
/*     */   double[] br;
/*  51 */   private final WorldGenEndIsland endIslands = new WorldGenEndIsland();
/*     */   
/*  53 */   private int chunkX = 0;
/*  54 */   private int chunkZ = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean towerDone;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean portalDone;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
/*  84 */     int i = 2;
/*  85 */     int j = 3;
/*  86 */     int k = 33;
/*  87 */     int l = 3;
/*  88 */     this.buffer = getHeights(this.buffer, x * 2, 0, z * 2, 3, 33, 3);
/*     */     
/*  90 */     for (int i1 = 0; i1 < 2; i1++) {
/*     */       
/*  92 */       for (int j1 = 0; j1 < 2; j1++) {
/*     */         
/*  94 */         for (int k1 = 0; k1 < 32; k1++) {
/*     */           
/*  96 */           double d0 = 0.25D;
/*  97 */           double d1 = this.buffer[((i1 + 0) * 3 + j1 + 0) * 33 + k1 + 0];
/*  98 */           double d2 = this.buffer[((i1 + 0) * 3 + j1 + 1) * 33 + k1 + 0];
/*  99 */           double d3 = this.buffer[((i1 + 1) * 3 + j1 + 0) * 33 + k1 + 0];
/* 100 */           double d4 = this.buffer[((i1 + 1) * 3 + j1 + 1) * 33 + k1 + 0];
/* 101 */           double d5 = (this.buffer[((i1 + 0) * 3 + j1 + 0) * 33 + k1 + 1] - d1) * 0.25D;
/* 102 */           double d6 = (this.buffer[((i1 + 0) * 3 + j1 + 1) * 33 + k1 + 1] - d2) * 0.25D;
/* 103 */           double d7 = (this.buffer[((i1 + 1) * 3 + j1 + 0) * 33 + k1 + 1] - d3) * 0.25D;
/* 104 */           double d8 = (this.buffer[((i1 + 1) * 3 + j1 + 1) * 33 + k1 + 1] - d4) * 0.25D;
/*     */           
/* 106 */           for (int l1 = 0; l1 < 4; l1++) {
/*     */             
/* 108 */             double d9 = 0.125D;
/* 109 */             double d10 = d1;
/* 110 */             double d11 = d2;
/* 111 */             double d12 = (d3 - d1) * 0.125D;
/* 112 */             double d13 = (d4 - d2) * 0.125D;
/*     */             
/* 114 */             for (int i2 = 0; i2 < 8; i2++) {
/*     */               
/* 116 */               double d14 = 0.125D;
/* 117 */               double d15 = d10;
/* 118 */               double d16 = (d11 - d10) * 0.125D;
/*     */               
/* 120 */               for (int j2 = 0; j2 < 8; j2++) {
/*     */                 
/* 122 */                 IBlockState iblockstate = AIR;
/*     */                 
/* 124 */                 if (d15 > 0.0D)
/*     */                 {
/* 126 */                   iblockstate = WYVERN_STONE;
/*     */                 }
/*     */                 
/* 129 */                 int k2 = i2 + i1 * 8;
/* 130 */                 int l2 = l1 + k1 * 4;
/* 131 */                 int i3 = j2 + j1 * 8;
/* 132 */                 primer.setBlockState(k2, l2, i3, iblockstate);
/* 133 */                 d15 += d16;
/*     */               } 
/*     */               
/* 136 */               d10 += d12;
/* 137 */               d11 += d13;
/*     */             } 
/*     */             
/* 140 */             d1 += d5;
/* 141 */             d2 += d6;
/* 142 */             d3 += d7;
/* 143 */             d4 += d8;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void buildSurfaces(ChunkPrimer primer) {
/* 152 */     if (!ForgeEventFactory.onReplaceBiomeBlocks(this, this.chunkX, this.chunkZ, primer, this.world))
/* 153 */       return;  for (int i = 0; i < 16; i++) {
/* 154 */       for (int j = 0; j < 16; j++) {
/* 155 */         byte b0 = 5;
/* 156 */         int k = -1;
/*     */         
/* 158 */         IBlockState iblockstateTopBlock = WYVERN_GRASS;
/* 159 */         IBlockState iblockstateFillerBlock = WYVERN_DIRT;
/*     */         
/* 161 */         for (int l = 127; l >= 0; l--) {
/* 162 */           IBlockState iblockstate2 = primer.getBlockState(i, l, j);
/*     */           
/* 164 */           if (iblockstate2.getMaterial() == Material.AIR) {
/*     */             
/* 166 */             k = -1;
/* 167 */           } else if (iblockstate2.getBlock() == WYVERN_STONE.getBlock()) {
/*     */             
/* 169 */             if (k == -1) {
/*     */               
/* 171 */               k = b0;
/* 172 */               if (l >= 0) {
/*     */                 
/* 174 */                 primer.setBlockState(i, l, j, iblockstateTopBlock);
/*     */               } else {
/* 176 */                 primer.setBlockState(i, l, j, iblockstateFillerBlock);
/*     */               } 
/* 178 */             } else if (k > 0) {
/* 179 */               k--;
/* 180 */               primer.setBlockState(i, l, j, iblockstateFillerBlock);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk generateChunk(int x, int z) {
/* 191 */     this.chunkX = x; this.chunkZ = z;
/* 192 */     this.rand.setSeed(x * 341873128712L + z * 132897987541L);
/* 193 */     ChunkPrimer chunkprimer = new ChunkPrimer();
/* 194 */     this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
/* 195 */     setBlocksInChunk(x, z, chunkprimer);
/* 196 */     buildSurfaces(chunkprimer);
/*     */     
/* 198 */     Chunk chunk = new Chunk(this.world, chunkprimer, x, z);
/* 199 */     byte[] abyte = chunk.getBiomeArray();
/*     */     
/* 201 */     for (int i = 0; i < abyte.length; i++)
/*     */     {
/* 203 */       abyte[i] = (byte)Biome.getIdForBiome(this.biomesForGeneration[i]);
/*     */     }
/*     */     
/* 206 */     chunk.generateSkylightMap();
/* 207 */     return chunk;
/*     */   }
/*     */ 
/*     */   
/*     */   private float getIslandHeightValue(int p_185960_1_, int p_185960_2_, int p_185960_3_, int p_185960_4_) {
/* 212 */     float f = (p_185960_1_ * 2 + p_185960_3_);
/* 213 */     float f1 = (p_185960_2_ * 2 + p_185960_4_);
/* 214 */     float f2 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * 8.0F;
/*     */     
/* 216 */     if (f2 > 80.0F)
/*     */     {
/* 218 */       f2 = 80.0F;
/*     */     }
/*     */     
/* 221 */     if (f2 < -100.0F)
/*     */     {
/* 223 */       f2 = -100.0F;
/*     */     }
/*     */     
/* 226 */     for (int i = -12; i <= 12; i++) {
/*     */       
/* 228 */       for (int j = -12; j <= 12; j++) {
/*     */         
/* 230 */         long k = (p_185960_1_ + i);
/* 231 */         long l = (p_185960_2_ + j);
/*     */         
/* 233 */         if (k * k + l * l > 4096L && this.islandNoise.getValue(k, l) < -0.8999999761581421D) {
/*     */           
/* 235 */           float f3 = (MathHelper.abs((float)k) * 3439.0F + MathHelper.abs((float)l) * 147.0F) % 13.0F + 9.0F;
/* 236 */           f = (p_185960_3_ - i * 2);
/* 237 */           f1 = (p_185960_4_ - j * 2);
/* 238 */           float f4 = 100.0F - MathHelper.sqrt(f * f + f1 * f1) * f3;
/*     */           
/* 240 */           if (f4 > 80.0F)
/*     */           {
/* 242 */             f4 = 80.0F;
/*     */           }
/*     */           
/* 245 */           if (f4 < -100.0F)
/*     */           {
/* 247 */             f4 = -100.0F;
/*     */           }
/*     */           
/* 250 */           if (f4 > f2)
/*     */           {
/* 252 */             f2 = f4;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 258 */     return f2;
/*     */   }
/*     */ 
/*     */   
/*     */   private double[] getHeights(double[] p_185963_1_, int p_185963_2_, int p_185963_3_, int p_185963_4_, int p_185963_5_, int p_185963_6_, int p_185963_7_) {
/* 263 */     ChunkGeneratorEvent.InitNoiseField event = new ChunkGeneratorEvent.InitNoiseField(this, p_185963_1_, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_);
/* 264 */     MinecraftForge.EVENT_BUS.post((Event)event);
/* 265 */     if (event.getResult() == Event.Result.DENY) return event.getNoisefield();
/*     */     
/* 267 */     if (p_185963_1_ == null)
/*     */     {
/* 269 */       p_185963_1_ = new double[p_185963_5_ * p_185963_6_ * p_185963_7_];
/*     */     }
/*     */     
/* 272 */     double d0 = 684.412D;
/* 273 */     double d1 = 684.412D;
/* 274 */     d0 *= 2.0D;
/* 275 */     this.pnr = this.perlinNoise1.generateNoiseOctaves(this.pnr, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0 / 80.0D, 4.277575000000001D, d0 / 80.0D);
/* 276 */     this.ar = this.lperlinNoise1.generateNoiseOctaves(this.ar, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0, 684.412D, d0);
/* 277 */     this.br = this.lperlinNoise2.generateNoiseOctaves(this.br, p_185963_2_, p_185963_3_, p_185963_4_, p_185963_5_, p_185963_6_, p_185963_7_, d0, 684.412D, d0);
/* 278 */     int i = p_185963_2_ / 2;
/* 279 */     int j = p_185963_4_ / 2;
/* 280 */     int k = 0;
/*     */     
/* 282 */     for (int l = 0; l < p_185963_5_; l++) {
/*     */       
/* 284 */       for (int i1 = 0; i1 < p_185963_7_; i1++) {
/*     */         
/* 286 */         float f = getIslandHeightValue(i, j, l, i1);
/*     */         
/* 288 */         for (int j1 = 0; j1 < p_185963_6_; j1++) {
/*     */           
/* 290 */           double d4, d2 = this.ar[k] / 512.0D;
/* 291 */           double d3 = this.br[k] / 512.0D;
/* 292 */           double d5 = (this.pnr[k] / 10.0D + 1.0D) / 2.0D;
/*     */ 
/*     */           
/* 295 */           if (d5 < 0.0D) {
/*     */             
/* 297 */             d4 = d2;
/*     */           }
/* 299 */           else if (d5 > 1.0D) {
/*     */             
/* 301 */             d4 = d3;
/*     */           }
/*     */           else {
/*     */             
/* 305 */             d4 = d2 + (d3 - d2) * d5;
/*     */           } 
/*     */           
/* 308 */           d4 -= 8.0D;
/* 309 */           d4 += f;
/* 310 */           int k1 = 2;
/*     */           
/* 312 */           if (j1 > p_185963_6_ / 2 - k1) {
/*     */             
/* 314 */             double d6 = ((j1 - p_185963_6_ / 2 - k1) / 64.0F);
/* 315 */             d6 = MathHelper.clamp(d6, 0.0D, 1.0D);
/* 316 */             d4 = d4 * (1.0D - d6) + -3000.0D * d6;
/*     */           } 
/*     */           
/* 319 */           k1 = 8;
/*     */           
/* 321 */           if (j1 < k1) {
/*     */             
/* 323 */             double d7 = ((k1 - j1) / (k1 - 1.0F));
/* 324 */             d4 = d4 * (1.0D - d7) + -30.0D * d7;
/*     */           } 
/*     */           
/* 327 */           p_185963_1_[k] = d4;
/* 328 */           k++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 333 */     return p_185963_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void populate(int x, int z) {
/* 338 */     BlockFalling.fallInstantly = true;
/* 339 */     ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, false);
/*     */     
/* 341 */     int var4 = x * 16;
/* 342 */     int var5 = z * 16;
/* 343 */     BlockPos blockpos = new BlockPos(var4 + 16, 0, var5 + 16);
/* 344 */     Biome var6 = this.world.getBiome(blockpos);
/* 345 */     boolean var11 = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 351 */     if (!var11 && this.rand.nextInt(2) == 0) {
/* 352 */       int var12 = var4 + this.rand.nextInt(16) + 8;
/* 353 */       int var13 = this.rand.nextInt(128);
/* 354 */       int var14 = var5 + this.rand.nextInt(16) + 8;
/* 355 */       (new WorldGenLakes((Block)Blocks.WATER)).generate(this.world, this.rand, new BlockPos(var12, var13, var14));
/*     */     } 
/*     */     
/* 358 */     if (!var11 && this.rand.nextInt(8) == 0) {
/* 359 */       int var12 = var4 + this.rand.nextInt(16) + 8;
/* 360 */       int var13 = this.rand.nextInt(this.rand.nextInt(120) + 8);
/* 361 */       int var14 = var5 + this.rand.nextInt(16) + 8;
/*     */       
/* 363 */       if (var13 < 63 || this.rand.nextInt(10) == 0) {
/* 364 */         (new WorldGenLakes((Block)Blocks.LAVA)).generate(this.world, this.rand, new BlockPos(var12, var13, var14));
/*     */       }
/*     */     } 
/*     */     
/* 368 */     var6.decorate(this.world, this.rand, new BlockPos(var4, 0, var5));
/*     */     
/* 370 */     if (x == 0 && z == 0 && !this.portalDone) {
/* 371 */       createPortal(this.world, this.rand);
/*     */     }
/*     */     
/* 374 */     ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, false);
/* 375 */     BlockFalling.fallInstantly = false;
/*     */   }
/*     */   
/* 378 */   public ChunkGeneratorWyvernLair(World worldIn, boolean mapFeaturesEnabledIn, long seed) { this.towerDone = false;
/* 379 */     this.portalDone = false; this.world = worldIn; this.mapFeaturesEnabled = mapFeaturesEnabledIn; this.rand = new Random(seed); this.lperlinNoise1 = new NoiseGeneratorOctaves(this.rand, 16); this.lperlinNoise2 = new NoiseGeneratorOctaves(this.rand, 16); this.perlinNoise1 = new NoiseGeneratorOctaves(this.rand, 8); this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10); this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16); this.islandNoise = new NoiseGeneratorSimplex(this.rand); InitNoiseGensEvent.ContextEnd ctx = new InitNoiseGensEvent.ContextEnd(this.lperlinNoise1, this.lperlinNoise2, this.perlinNoise1, this.noiseGen5, this.noiseGen6, this.islandNoise); ctx = (InitNoiseGensEvent.ContextEnd)TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, (InitNoiseGensEvent.Context)ctx); this.lperlinNoise1 = ctx.getLPerlin1(); this.lperlinNoise2 = ctx.getLPerlin2(); this.perlinNoise1 = ctx.getPerlin();
/*     */     this.noiseGen5 = ctx.getDepth();
/*     */     this.noiseGen6 = ctx.getScale();
/* 382 */     this.islandNoise = ctx.getIsland(); } public void generateTower(World par1World, Random par2Random, int par3, int par4) { WorldGenTower myTower = new WorldGenTower((Block)Blocks.GRASS, (Block)Blocks.DOUBLE_STONE_SLAB, Blocks.LAPIS_ORE);
/* 383 */     if (!this.towerDone) {
/* 384 */       int randPosX = par3 + par2Random.nextInt(16) + 8;
/* 385 */       int randPosZ = par4 + par2Random.nextInt(16) + 8;
/* 386 */       this.towerDone = myTower.generate(par1World, par2Random, new BlockPos(randPosX, 61, randPosZ));
/*     */     }  }
/*     */ 
/*     */   
/*     */   public void createPortal(World par1World, Random par2Random) {
/* 391 */     MoCWorldGenPortal myPortal = new MoCWorldGenPortal(Blocks.QUARTZ_BLOCK, 2, Blocks.QUARTZ_STAIRS, 0, Blocks.QUARTZ_BLOCK, 1, Blocks.QUARTZ_BLOCK, 0);
/*     */     
/* 393 */     for (int i = 0; i < 16; i++) {
/* 394 */       if (!this.portalDone) {
/* 395 */         int randPosY = 56 + i;
/* 396 */         this.portalDone = myPortal.generate(par1World, par2Random, new BlockPos(0, randPosY, 0));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generateStructures(Chunk chunkIn, int x, int z) {
/* 403 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
/* 408 */     return this.world.getBiome(pos).getSpawnableList(creatureType);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
/* 413 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
/* 418 */     return false;
/*     */   }
/*     */   
/*     */   public void recreateStructures(Chunk chunkIn, int x, int z) {}
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\dimension\ChunkGeneratorWyvernLair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */