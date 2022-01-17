/*    */ package drzhark.mocreatures.client;
/*    */ 
/*    */ import drzhark.mocreatures.init.MoCItems;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.NonNullList;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class MoCCreativeTabs
/*    */   extends CreativeTabs {
/*    */   public MoCCreativeTabs(int length, String name) {
/* 14 */     super(length, name);
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public ItemStack createIcon() {
/* 20 */     return new ItemStack((Item)MoCItems.amuletfairyfull, 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void displayAllRelevantItems(NonNullList<ItemStack> items) {
/* 28 */     for (Item item : Item.REGISTRY) {
/*    */       
/* 30 */       if (item == MoCItems.mocegg) {
/*    */         continue;
/*    */       }
/* 33 */       item.getSubItems(this, items);
/*    */     } 
/*    */     
/* 36 */     MoCItems.mocegg.getSubItems(this, items);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\MoCCreativeTabs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */