/*    */ package de.matthiasmann.twl;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Container
/*    */   extends Widget
/*    */ {
/*    */   public int getMinWidth() {
/* 41 */     return Math.max(super.getMinWidth(), getBorderHorizontal() + BoxLayout.computeMinWidthVertical(this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMinHeight() {
/* 47 */     return Math.max(super.getMinHeight(), getBorderVertical() + BoxLayout.computeMinHeightHorizontal(this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPreferredInnerWidth() {
/* 53 */     return BoxLayout.computePreferredWidthVertical(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPreferredInnerHeight() {
/* 58 */     return BoxLayout.computePreferredHeightHorizontal(this);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void layout() {
/* 63 */     layoutChildrenFullInnerArea();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Container.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */