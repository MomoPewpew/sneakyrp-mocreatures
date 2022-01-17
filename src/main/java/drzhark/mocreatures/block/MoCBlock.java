/*     */ package drzhark.mocreatures.block;
/*     */
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.init.MoCBlocks;
/*     */ import net.minecraft.block.Block;
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
/*     */ public class MoCBlock
/*     */   extends Block
/*     */ {
/*  21 */   public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);
/*     */
/*     */   public MoCBlock(String name, Material material) {
/*  24 */     super(material);
/*  25 */     setCreativeTab(MoCreatures.tabMoC);
/*  26 */     setRegistryName("mocreatures", name);
/*  27 */     setUnlocalizedName(name);
/*  28 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT, EnumType.WYVERN_LAIR));
/*     */   }
/*     */
/*     */
/*     */   public int damageDropped(IBlockState state) {
/*  33 */     return ((EnumType)state.getValue((IProperty)VARIANT)).getMetadata();
/*     */   }
/*     */
/*     */
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
/*  39 */     for (int ix = 0; ix < MoCBlocks.multiBlockNames.size(); ix++) {
/*  40 */       items.add(new ItemStack(this, 1, ix));
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  46 */     return getDefaultState().withProperty((IProperty)VARIANT, EnumType.byMetadata(meta));
/*     */   }
/*     */
/*     */
/*     */   public int getMetaFromState(IBlockState state) {
/*  51 */     return ((EnumType)state.getValue((IProperty)VARIANT)).getMetadata();
/*     */   }
/*     */
/*     */
/*     */   protected BlockStateContainer createBlockState() {
/*  56 */     return new BlockStateContainer(this, new IProperty[] { (IProperty)VARIANT });
/*     */   }
/*     */
/*     */   public enum EnumType implements IStringSerializable {
/*  60 */     WYVERN_LAIR(0, "wyvern_lair"), OGRE_LAIR(1, "ogre_lair");
/*     */     private final String unlocalizedName;
/*  62 */     private static final EnumType[] META_LOOKUP = new EnumType[(values()).length];
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
/* 104 */       EnumType[] var0 = values();
/* 105 */       int var1 = var0.length;
/*     */
/* 107 */       for (int var2 = 0; var2 < var1; var2++) {
/* 108 */         EnumType var3 = var0[var2];
/* 109 */         META_LOOKUP[var3.getMetadata()] = var3;
/*     */       }
/*     */     }
/*     */
/*     */     EnumType(int meta, String name) {
/*     */       this.meta = meta;
/*     */       this.name = name;
/*     */       this.unlocalizedName = name;
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


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\block\MoCBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
