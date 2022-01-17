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
/*    */ public class InfoWindow
/*    */   extends Container
/*    */ {
/*    */   private final Widget owner;
/*    */   
/*    */   public InfoWindow(Widget owner) {
/* 44 */     if (owner == null) {
/* 45 */       throw new NullPointerException("owner");
/*    */     }
/*    */     
/* 48 */     this.owner = owner;
/*    */   }
/*    */   
/*    */   public Widget getOwner() {
/* 52 */     return this.owner;
/*    */   }
/*    */   
/*    */   public boolean isOpen() {
/* 56 */     return (getParent() != null);
/*    */   }
/*    */   
/*    */   public boolean openInfo() {
/* 60 */     if (getParent() != null) {
/* 61 */       return true;
/*    */     }
/* 63 */     if (isParentInfoWindow(this.owner)) {
/* 64 */       return false;
/*    */     }
/* 66 */     GUI gui = this.owner.getGUI();
/* 67 */     if (gui != null) {
/* 68 */       gui.openInfo(this);
/* 69 */       focusFirstChild();
/* 70 */       return true;
/*    */     } 
/* 72 */     return false;
/*    */   }
/*    */   
/*    */   public void closeInfo() {
/* 76 */     GUI gui = getGUI();
/* 77 */     if (gui != null) {
/* 78 */       gui.closeInfo(this);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void infoWindowClosed() {}
/*    */ 
/*    */ 
/*    */   
/*    */   private static boolean isParentInfoWindow(Widget w) {
/* 89 */     while (w != null) {
/* 90 */       if (w instanceof InfoWindow) {
/* 91 */         return true;
/*    */       }
/* 93 */       w = w.getParent();
/*    */     } 
/* 95 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\InfoWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */