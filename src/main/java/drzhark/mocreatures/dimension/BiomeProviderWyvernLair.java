/*    */ package drzhark.mocreatures.dimension;
/*    */ 
/*    */ import drzhark.mocreatures.init.MoCBiomes;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraft.world.biome.BiomeProvider;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeProviderWyvernLair
/*    */   extends BiomeProvider
/*    */ {
/* 17 */   private Biome biomeGenerator = MoCBiomes.WyvernLairBiome;
/*    */   
/*    */   private float hellTemperature;
/*    */   
/*    */   private float rainfall;
/*    */   
/*    */   public BiomeProviderWyvernLair(Biome par1Biome, float par2, float par3) {
/* 24 */     this.biomeGenerator = par1Biome;
/* 25 */     this.hellTemperature = par2;
/* 26 */     this.rainfall = par3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Biome getBiomeGenAt(int par1, int par2) {
/* 33 */     return this.biomeGenerator;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Biome[] getBiomesForGeneration(Biome[] biomes, int xStart, int zStart, int xSize, int zSize) {
/* 41 */     return getBiomes(biomes, xStart, zStart, xSize, zSize, true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getTemperatures(float[] par1ArrayOfFloat, int xStart, int zStart, int xSize, int zSize) {
/* 49 */     if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < xSize * zSize) {
/* 50 */       par1ArrayOfFloat = new float[xSize * zSize];
/*    */     }
/*    */     
/* 53 */     Arrays.fill(par1ArrayOfFloat, 0, xSize * zSize, this.hellTemperature);
/* 54 */     return par1ArrayOfFloat;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Biome[] getBiomes(@Nullable Biome[] biomes, int xStart, int zStart, int xSize, int zSize) {
/* 64 */     return getBiomes(biomes, xStart, zStart, xSize, zSize, true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Biome[] getBiomes(@Nullable Biome[] biomes, int xStart, int zStart, int xSize, int zSize, boolean par6) {
/* 74 */     if (biomes == null || biomes.length < xSize * zSize) {
/* 75 */       biomes = new Biome[xSize * zSize];
/*    */     }
/*    */     
/* 78 */     Arrays.fill((Object[])biomes, 0, xSize * zSize, this.biomeGenerator);
/* 79 */     return biomes;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
/* 89 */     return biomes.contains(this.biomeGenerator) ? new BlockPos(x - range + random.nextInt(range * 2 + 1), 0, z - range + random
/* 90 */         .nextInt(range * 2 + 1)) : null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean areBiomesViable(int par1, int par2, int par3, List<Biome> biomes) {
/* 98 */     return biomes.contains(this.biomeGenerator);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\dimension\BiomeProviderWyvernLair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */