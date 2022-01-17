/*    */ package drzhark.mocreatures.dimension;
/*    */ 
/*    */ import drzhark.mocreatures.entity.ambient.MoCEntityDragonfly;
/*    */ import drzhark.mocreatures.entity.passive.MoCEntityBunny;
/*    */ import drzhark.mocreatures.entity.passive.MoCEntitySnake;
/*    */ import drzhark.mocreatures.entity.passive.MoCEntityWyvern;
/*    */ import drzhark.mocreatures.init.MoCBlocks;
/*    */ import java.util.Random;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*    */ import net.minecraft.world.gen.feature.WorldGenShrub;
/*    */ import net.minecraft.world.gen.feature.WorldGenVines;
/*    */ import net.minecraft.world.gen.feature.WorldGenerator;
/*    */ 
/*    */ public class BiomeGenWyvernLair
/*    */   extends Biome
/*    */ {
/*    */   private MoCWorldGenBigTree wyvernGenBigTree;
/*    */   private WorldGenShrub worldGenShrub;
/*    */   
/*    */   public BiomeGenWyvernLair(Biome.BiomeProperties biomeProperties) {
/* 25 */     super(biomeProperties);
/* 26 */     this.spawnableCreatureList.clear();
/* 27 */     this.spawnableMonsterList.clear();
/* 28 */     this.spawnableWaterCreatureList.clear();
/* 29 */     this.spawnableCreatureList.add(new Biome.SpawnListEntry(MoCEntityBunny.class, 6, 2, 3));
/* 30 */     this.spawnableCreatureList.add(new Biome.SpawnListEntry(MoCEntityDragonfly.class, 8, 2, 3));
/* 31 */     this.spawnableCreatureList.add(new Biome.SpawnListEntry(MoCEntitySnake.class, 6, 1, 2));
/* 32 */     this.spawnableCreatureList.add(new Biome.SpawnListEntry(MoCEntityWyvern.class, 10, 1, 4));
/* 33 */     this.topBlock = MoCBlocks.mocGrass.getDefaultState();
/* 34 */     this.fillerBlock = MoCBlocks.mocDirt.getDefaultState();
/* 35 */     this.wyvernGenBigTree = new MoCWorldGenBigTree(false, MoCBlocks.mocLog.getDefaultState(), MoCBlocks.mocLeaf.getDefaultState(), 2, 30, 10);
/* 36 */     this.worldGenShrub = new WorldGenShrub(Blocks.DIRT.getDefaultState(), Blocks.AIR.getDefaultState());
/* 37 */     this.decorator = new BiomeWyvernDecorator();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenAbstractTree getRandomTreeFeature(Random par1Random) {
/* 45 */     if (par1Random.nextInt(10) == 0) {
/* 46 */       return this.wyvernGenBigTree;
/*    */     }
/* 48 */     return (WorldGenAbstractTree)this.worldGenShrub;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
/* 57 */     return new WorldGenWyvernGrass(MoCBlocks.mocTallGrass.getDefaultState());
/*    */   }
/*    */ 
/*    */   
/*    */   public void decorate(World par1World, Random par2Random, BlockPos pos) {
/* 62 */     super.decorate(par1World, par2Random, pos);
/*    */     
/* 64 */     WorldGenVines var5 = new WorldGenVines();
/*    */     
/* 66 */     for (int var6 = 0; var6 < 50; var6++) {
/* 67 */       int var7 = par2Random.nextInt(16) + 8;
/* 68 */       byte var8 = 64;
/* 69 */       int var9 = par2Random.nextInt(16) + 8;
/* 70 */       var5.generate(par1World, par2Random, pos.add(var7, var8, var9));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\dimension\BiomeGenWyvernLair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */