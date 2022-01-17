/*     */ package drzhark.mocreatures.dimension;
/*     */ 
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.init.MoCBiomes;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.DimensionType;
/*     */ import net.minecraft.world.WorldProviderSurface;
/*     */ import net.minecraft.world.gen.IChunkGenerator;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldProviderWyvernEnd
/*     */   extends WorldProviderSurface
/*     */ {
/*     */   protected void init() {
/*  23 */     this.biomeProvider = new BiomeProviderWyvernLair(MoCBiomes.WyvernLairBiome, 0.5F, 0.0F);
/*  24 */     setDimension(MoCreatures.WyvernLairDimensionID);
/*  25 */     setCustomSky();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChunkGenerator createChunkGenerator() {
/*  33 */     return new ChunkGeneratorWyvernLair(this.world, false, this.world.getSeed());
/*     */   }
/*     */   
/*     */   private void setCustomSky() {
/*  37 */     if (!this.world.isRemote) {
/*     */       return;
/*     */     }
/*  40 */     setSkyRenderer(new MoCSkyRenderer());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float[] calcSunriseSunsetColors(float par1, float par2) {
/*  49 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public Vec3d getFogColor(float par1, float par2) {
/*  58 */     float var4 = MathHelper.cos(par1 * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/*     */     
/*  60 */     if (var4 < 0.0F) {
/*  61 */       var4 = 0.0F;
/*     */     }
/*     */     
/*  64 */     if (var4 > 1.0F) {
/*  65 */       var4 = 1.0F;
/*     */     }
/*     */     
/*  68 */     float var5 = 0.0F;
/*  69 */     float var6 = 0.38431373F;
/*  70 */     float var7 = 0.28627452F;
/*     */     
/*  72 */     var5 *= var4 * 0.0F + 0.15F;
/*  73 */     var6 *= var4 * 0.0F + 0.15F;
/*  74 */     var7 *= var4 * 0.0F + 0.15F;
/*  75 */     return new Vec3d(var5, var6, var7);
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean isSkyColored() {
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRespawnHere() {
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSurfaceWorld() {
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float getCloudHeight() {
/* 108 */     return 76.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCoordinateBeSpawn(int par1, int par2) {
/* 117 */     BlockPos pos = this.world.getTopSolidOrLiquidBlock(new BlockPos(par1, 0, par2));
/* 118 */     return this.world.getBlockState(pos).getMaterial().blocksMovement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos getSpawnCoordinate() {
/* 126 */     return new BlockPos(0, 70, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAverageGroundLevel() {
/* 131 */     return 50;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean doesXZShowFog(int par1, int par2) {
/* 140 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public DimensionType getDimensionType() {
/* 145 */     return MoCreatures.WYVERN_LAIR;
/*     */   }
/*     */   
/*     */   public String getSunTexture() {
/* 149 */     return "/mocreatures.twinsuns.png";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getMovementFactor() {
/* 159 */     return 1.0D;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\dimension\WorldProviderWyvernEnd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */