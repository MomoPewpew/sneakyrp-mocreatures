/*    */ package de.matthiasmann.twl.model;
/*    */ 
/*    */ import de.matthiasmann.twl.utils.CallbackSupport;
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
/*    */ public abstract class AbstractListModel<T>
/*    */   implements ListModel<T>
/*    */ {
/*    */   private ListModel.ChangeListener[] listeners;
/*    */   
/*    */   public void addChangeListener(ListModel.ChangeListener listener) {
/* 46 */     this.listeners = (ListModel.ChangeListener[])CallbackSupport.addCallbackToList((Object[])this.listeners, listener, ListModel.ChangeListener.class);
/*    */   }
/*    */   
/*    */   public void removeChangeListener(ListModel.ChangeListener listener) {
/* 50 */     this.listeners = (ListModel.ChangeListener[])CallbackSupport.removeCallbackFromList((Object[])this.listeners, listener);
/*    */   }
/*    */   
/*    */   protected void fireEntriesInserted(int first, int last) {
/* 54 */     if (this.listeners != null) {
/* 55 */       for (ListModel.ChangeListener cl : this.listeners) {
/* 56 */         cl.entriesInserted(first, last);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   protected void fireEntriesDeleted(int first, int last) {
/* 62 */     if (this.listeners != null) {
/* 63 */       for (ListModel.ChangeListener cl : this.listeners) {
/* 64 */         cl.entriesDeleted(first, last);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   protected void fireEntriesChanged(int first, int last) {
/* 70 */     if (this.listeners != null) {
/* 71 */       for (ListModel.ChangeListener cl : this.listeners) {
/* 72 */         cl.entriesChanged(first, last);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   protected void fireAllChanged() {
/* 78 */     if (this.listeners != null)
/* 79 */       for (ListModel.ChangeListener cl : this.listeners)
/* 80 */         cl.allChanged();  
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\AbstractListModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */