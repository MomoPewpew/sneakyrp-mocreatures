/*    */ package de.matthiasmann.twl.model;
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
/*    */ public abstract class SimpleListModel<T>
/*    */   extends AbstractListModel<T>
/*    */ {
/*    */   public Object getEntryTooltip(int index) {
/* 40 */     return null;
/*    */   }
/*    */   
/*    */   public boolean matchPrefix(int index, String prefix) {
/* 44 */     Object entry = getEntry(index);
/* 45 */     if (entry != null) {
/* 46 */       return entry.toString().regionMatches(true, 0, prefix, 0, prefix.length());
/*    */     }
/* 48 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleListModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */