/*    */ package de.matthiasmann.twl.theme;
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
/*    */ class ThemeChildImpl
/*    */ {
/*    */   final ThemeManager manager;
/*    */   final ThemeInfoImpl parent;
/*    */   
/*    */   ThemeChildImpl(ThemeManager manager, ThemeInfoImpl parent) {
/* 42 */     this.manager = manager;
/* 43 */     this.parent = parent;
/*    */   }
/*    */   
/*    */   protected String getParentDescription() {
/* 47 */     if (this.parent != null) {
/* 48 */       return ", defined in " + this.parent.getThemePath();
/*    */     }
/* 50 */     return "";
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\ThemeChildImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */