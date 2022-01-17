/*    */ package drzhark.mocreatures.item;
/*    */
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import net.minecraft.item.Item;
/*    */
/*    */
/*    */ public class MoCItem
/*    */   extends Item
/*    */ {
/*    */   public MoCItem() {}
/*    */
/*    */   public MoCItem(String name) {
/* 13 */     this(name, 0);
/*    */   }
/*    */
/*    */   public MoCItem(String name, int meta) {
/* 17 */     setCreativeTab(MoCreatures.tabMoC);
/* 18 */     setRegistryName("mocreatures", name);
/* 19 */     setUnlocalizedName(name);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
