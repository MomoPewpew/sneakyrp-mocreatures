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
/*    */ public class MenuSpacer
/*    */   extends MenuElement
/*    */ {
/*    */   protected Widget createMenuWidget(MenuManager mm, int level) {
/* 40 */     Widget w = new Widget();
/* 41 */     setWidgetTheme(w, "spacer");
/* 42 */     return w;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\MenuSpacer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */