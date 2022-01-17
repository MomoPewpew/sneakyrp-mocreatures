/*    */ package drzhark.mocreatures.item;
/*    */ 
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import net.minecraft.item.ItemFood;
/*    */ 
/*    */ public class MoCItemFood
/*    */   extends ItemFood
/*    */ {
/*    */   public MoCItemFood(String name, int j) {
/* 10 */     super(j, 0.6F, false);
/* 11 */     setCreativeTab(MoCreatures.tabMoC);
/* 12 */     setRegistryName("mocreatures", name);
/* 13 */     setTranslationKey(name);
/* 14 */     this.maxStackSize = 32;
/*    */   }
/*    */   
/*    */   public MoCItemFood(String name, int j, float f, boolean flag) {
/* 18 */     super(j, f, flag);
/* 19 */     setCreativeTab(MoCreatures.tabMoC);
/* 20 */     setRegistryName("mocreatures", name);
/* 21 */     setTranslationKey(name);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemFood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */