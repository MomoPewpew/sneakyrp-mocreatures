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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DesktopArea
/*    */   extends Widget
/*    */ {
/*    */   public DesktopArea() {
/* 44 */     setFocusKeyEnabled(false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void keyboardFocusChildChanged(Widget child) {
/* 49 */     super.keyboardFocusChildChanged(child);
/* 50 */     if (child != null) {
/* 51 */       int fromIdx = getChildIndex(child);
/* 52 */       assert fromIdx >= 0;
/* 53 */       int numChildren = getNumChildren();
/* 54 */       if (fromIdx < numChildren - 1) {
/* 55 */         moveChild(fromIdx, numChildren - 1);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void layout() {
/* 63 */     restrictChildrenToInnerArea();
/*    */   }
/*    */   
/*    */   protected void restrictChildrenToInnerArea() {
/* 67 */     int top = getInnerY();
/* 68 */     int left = getInnerX();
/* 69 */     int right = getInnerRight();
/* 70 */     int bottom = getInnerBottom();
/* 71 */     int width = Math.max(0, right - left);
/* 72 */     int height = Math.max(0, bottom - top);
/*    */     
/* 74 */     for (int i = 0, n = getNumChildren(); i < n; i++) {
/* 75 */       Widget w = getChild(i);
/* 76 */       w.setSize(Math.min(Math.max(width, w.getMinWidth()), w.getWidth()), Math.min(Math.max(height, w.getMinHeight()), w.getHeight()));
/*    */ 
/*    */       
/* 79 */       w.setPosition(Math.max(left, Math.min(right - w.getWidth(), w.getX())), Math.max(top, Math.min(bottom - w.getHeight(), w.getY())));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\DesktopArea.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */