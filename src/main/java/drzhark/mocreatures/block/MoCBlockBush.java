/*     */ package drzhark.mocreatures.block;
/*     */ 
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockBush;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockStateContainer;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ public class MoCBlockBush
/*     */   extends BlockBush {
/*  20 */   public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);
/*     */   
/*     */   public MoCBlockBush(String name, Material material) {
/*  23 */     super(material);
/*  24 */     setCreativeTab(MoCreatures.tabMoC);
/*  25 */     setRegistryName("mocreatures", name);
/*  26 */     setTranslationKey(name);
/*  27 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT, EnumType.WYVERN_LAIR));
/*     */   }
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/*  32 */     return ((EnumType)state.getValue((IProperty)VARIANT)).getMetadata();
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
/*  38 */     for (int j = 0; j < (EnumType.values()).length; j++) {
/*  39 */       items.add(new ItemStack((Block)this, 1, j));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  45 */     return getDefaultState().withProperty((IProperty)VARIANT, EnumType.byMetadata(meta));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  50 */     return ((EnumType)state.getValue((IProperty)VARIANT)).getMetadata();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateContainer createBlockState() {
/*  55 */     return new BlockStateContainer((Block)this, new IProperty[] { (IProperty)VARIANT });
/*     */   }
/*     */   
/*     */   public enum EnumType implements IStringSerializable {
/*  59 */     WYVERN_LAIR(0, "wyvern_lair"), OGRE_LAIR(1, "ogre_lair");
/*     */     private final String unlocalizedName;
/*  61 */     private static final EnumType[] META_LOOKUP = new EnumType[(values()).length];
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
/*     */     private final String name;
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
/*     */     private final int meta;
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
/*     */     static {
/* 103 */       EnumType[] var0 = values();
/* 104 */       int var1 = var0.length;
/*     */       
/* 106 */       for (int var2 = 0; var2 < var1; var2++) {
/* 107 */         EnumType var3 = var0[var2];
/* 108 */         META_LOOKUP[var3.getMetadata()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumType(int meta, String name, String unlocalizedName) {
/*     */       this.meta = meta;
/*     */       this.name = name;
/*     */       this.unlocalizedName = unlocalizedName;
/*     */     }
/*     */     
/*     */     public int getMetadata() {
/*     */       return this.meta;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.name;
/*     */     }
/*     */     
/*     */     public static EnumType byMetadata(int meta) {
/*     */       if (meta < 0 || meta >= META_LOOKUP.length)
/*     */         meta = 0; 
/*     */       return META_LOOKUP[meta];
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.name;
/*     */     }
/*     */     
/*     */     public String getUnlocalizedName() {
/*     */       return this.unlocalizedName;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\block\MoCBlockBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */