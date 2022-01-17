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
/*    */ public class MenuAction
/*    */   extends MenuElement
/*    */ {
/*    */   private Runnable cb;
/*    */   
/*    */   public MenuAction() {}
/*    */   
/*    */   public MenuAction(Runnable cb) {
/* 44 */     this.cb = cb;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MenuAction(String name, Runnable cb) {
/* 56 */     super(name);
/* 57 */     this.cb = cb;
/*    */   }
/*    */   
/*    */   public Runnable getCallback() {
/* 61 */     return this.cb;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCallback(Runnable cb) {
/* 72 */     this.cb = cb;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Widget createMenuWidget(MenuManager mm, int level) {
/* 77 */     Button b = new MenuElement.MenuBtn(this);
/* 78 */     setWidgetTheme(b, "button");
/*    */     
/* 80 */     b.addCallback(mm.getCloseCallback());
/*    */     
/* 82 */     if (this.cb != null) {
/* 83 */       b.addCallback(this.cb);
/*    */     }
/*    */     
/* 86 */     return b;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\MenuAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */