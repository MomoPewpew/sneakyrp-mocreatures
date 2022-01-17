/*    */ package drzhark.mocreatures.init;
/*    */ 
/*    */ import drzhark.mocreatures.dimension.BiomeGenWyvernLair;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraftforge.common.BiomeDictionary;
/*    */ import net.minecraftforge.common.BiomeManager;
/*    */ import net.minecraftforge.event.RegistryEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
/*    */ import net.minecraftforge.registries.IForgeRegistry;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @ObjectHolder("mocreatures")
/*    */ public class MoCBiomes
/*    */ {
/* 19 */   public static Biome WyvernLairBiome = (Biome)new BiomeGenWyvernLair((new Biome.BiomeProperties("WyvernLair")).setBaseHeight(0.3F).setHeightVariation(1.5F));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @EventBusSubscriber(modid = "mocreatures")
/*    */   public static class RegistrationHandler
/*    */   {
/*    */     @SubscribeEvent
/*    */     public static void registerBiomes(RegistryEvent.Register<Biome> event) {
/* 31 */       IForgeRegistry<Biome> registry = event.getRegistry();
/* 32 */       registerBiome(registry, MoCBiomes.WyvernLairBiome, "wyvernbiome", BiomeManager.BiomeType.WARM, 1000, new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.END });
/*    */     }
/*    */     
/*    */     private static <T extends Biome> void registerBiome(IForgeRegistry<Biome> registry, T biome, String biomeName, BiomeManager.BiomeType biomeType, int weight, BiomeDictionary.Type... types) {
/* 36 */       registry.register(biome.setRegistryName("mocreatures", biomeName));
/* 37 */       BiomeDictionary.addTypes((Biome)biome, types);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\init\MoCBiomes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */