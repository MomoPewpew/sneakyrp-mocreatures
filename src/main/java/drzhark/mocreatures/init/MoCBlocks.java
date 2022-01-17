/*     */ package drzhark.mocreatures.init;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.block.MoCBlock;
/*     */ import drzhark.mocreatures.block.MoCBlockDirt;
/*     */ import drzhark.mocreatures.block.MoCBlockGrass;
/*     */ import drzhark.mocreatures.block.MoCBlockLeaf;
/*     */ import drzhark.mocreatures.block.MoCBlockLog;
/*     */ import drzhark.mocreatures.block.MoCBlockPlanks;
/*     */ import drzhark.mocreatures.block.MoCBlockRock;
/*     */ import drzhark.mocreatures.block.MoCBlockTallGrass;
/*     */ import drzhark.mocreatures.block.MultiItemBlock;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.block.model.ModelBakery;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.model.ModelLoader;
/*     */ import net.minecraftforge.event.RegistryEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
/*     */ import net.minecraftforge.registries.IForgeRegistry;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ 
/*     */ 
/*     */ @ObjectHolder("mocreatures")
/*     */ public class MoCBlocks
/*     */ {
/*  35 */   public static ArrayList<String> multiBlockNames = new ArrayList<>();
/*     */   
/*  37 */   public static MoCBlock mocStone = (MoCBlock)(new MoCBlockRock("MoCStone")).setHardness(1.5F).setResistance(10.0F);
/*  38 */   public static MoCBlock mocGrass = (MoCBlock)(new MoCBlockGrass("MoCGrass")).setHardness(0.5F);
/*  39 */   public static MoCBlock mocDirt = (MoCBlock)(new MoCBlockDirt("MoCDirt")).setHardness(0.6F);
/*     */   
/*  41 */   public static MoCBlock mocLeaf = (MoCBlock)(new MoCBlockLeaf("MoCLeaves")).setHardness(0.2F).setLightOpacity(1);
/*  42 */   public static MoCBlock mocLog = (MoCBlock)(new MoCBlockLog("MoCLog")).setHardness(2.0F);
/*  43 */   public static MoCBlockTallGrass mocTallGrass = (MoCBlockTallGrass)(new MoCBlockTallGrass("MoCTallGrass", true)).setHardness(0.0F);
/*  44 */   public static MoCBlock mocPlank = (MoCBlock)(new MoCBlockPlanks("MoCWoodPlank")).setHardness(2.0F).setResistance(5.0F);
/*     */   
/*     */   @EventBusSubscriber(modid = "mocreatures")
/*     */   public static class RegistrationHandler {
/*  48 */     public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void registerBlocks(RegistryEvent.Register<Block> event) {
/*  57 */       IForgeRegistry<Block> registry = event.getRegistry();
/*     */       
/*  59 */       Block[] blocks = { (Block)MoCBlocks.mocStone, (Block)MoCBlocks.mocGrass, (Block)MoCBlocks.mocDirt, (Block)MoCBlocks.mocLeaf, (Block)MoCBlocks.mocLog, (Block)MoCBlocks.mocTallGrass, (Block)MoCBlocks.mocPlank };
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
/*  70 */       MoCBlocks.mocDirt.setHarvestLevel("shovel", 0, MoCBlocks.mocDirt.getDefaultState());
/*  71 */       MoCBlocks.mocGrass.setHarvestLevel("shovel", 0, MoCBlocks.mocGrass.getDefaultState());
/*  72 */       MoCBlocks.mocStone.setHarvestLevel("pickaxe", 1, MoCBlocks.mocStone.getDefaultState());
/*  73 */       MoCBlocks.multiBlockNames.add("WyvernLair");
/*  74 */       MoCBlocks.multiBlockNames.add("OgreLair");
/*  75 */       registry.registerAll((IForgeRegistryEntry[])blocks);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
/*  86 */       ItemBlock[] items = { (ItemBlock)new MultiItemBlock((Block)MoCBlocks.mocStone), (ItemBlock)new MultiItemBlock((Block)MoCBlocks.mocGrass), (ItemBlock)new MultiItemBlock((Block)MoCBlocks.mocDirt), (ItemBlock)new MultiItemBlock((Block)MoCBlocks.mocLeaf), (ItemBlock)new MultiItemBlock((Block)MoCBlocks.mocLog), (ItemBlock)new MultiItemBlock((Block)MoCBlocks.mocTallGrass), (ItemBlock)new MultiItemBlock((Block)MoCBlocks.mocPlank) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  96 */       IForgeRegistry<Item> registry = event.getRegistry();
/*     */       
/*  98 */       for (ItemBlock item : items) {
/*  99 */         Block block = item.getBlock();
/* 100 */         ResourceLocation registryName = (ResourceLocation)Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
/* 101 */         registry.register(item.setRegistryName(registryName));
/* 102 */         ITEM_BLOCKS.add(item);
/* 103 */         if (!MoCreatures.isServer()) {
/* 104 */           String name = item.getTranslationKey().replace("tile.", "").replace("MoC", "").toLowerCase();
/* 105 */           System.out.println("registering custom location " + name);
/* 106 */           ModelBakery.registerItemVariants((Item)item, new ResourceLocation[] { new ResourceLocation("mocreatures:wyvern_" + name) });
/* 107 */           ModelBakery.registerItemVariants((Item)item, new ResourceLocation[] { new ResourceLocation("mocreatures:ogre_" + name) });
/* 108 */           ModelLoader.setCustomModelResourceLocation((Item)item, 0, new ModelResourceLocation("mocreatures:wyvern_" + name, "inventory"));
/* 109 */           ModelLoader.setCustomModelResourceLocation((Item)item, 1, new ModelResourceLocation("mocreatures:ogre_" + name, "inventory"));
/* 110 */           ModelLoader.setCustomModelResourceLocation((Item)item, 2, new ModelResourceLocation("mocreatures:wyvern_" + name, "variant=wyvern_lair"));
/* 111 */           ModelLoader.setCustomModelResourceLocation((Item)item, 3, new ModelResourceLocation("mocreatures:ogre_" + name, "variant=ogre_lair"));
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\init\MoCBlocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */