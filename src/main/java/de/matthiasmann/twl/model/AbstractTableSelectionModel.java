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
/*    */ 
/*    */ 
/*    */ public abstract class AbstractTableSelectionModel
/*    */   implements TableSelectionModel
/*    */ {
/* 45 */   protected int leadIndex = -1;
/* 46 */   protected int anchorIndex = -1;
/*    */   protected Runnable[] selectionChangeListener;
/*    */   
/*    */   public int getAnchorIndex() {
/* 50 */     return this.anchorIndex;
/*    */   }
/*    */   
/*    */   public int getLeadIndex() {
/* 54 */     return this.leadIndex;
/*    */   }
/*    */   
/*    */   public void setAnchorIndex(int index) {
/* 58 */     this.anchorIndex = index;
/*    */   }
/*    */   
/*    */   public void setLeadIndex(int index) {
/* 62 */     this.leadIndex = index;
/*    */   }
/*    */   
/*    */   public void addSelectionChangeListener(Runnable cb) {
/* 66 */     this.selectionChangeListener = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.selectionChangeListener, cb, Runnable.class);
/*    */   }
/*    */   
/*    */   public void removeSelectionChangeListener(Runnable cb) {
/* 70 */     this.selectionChangeListener = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.selectionChangeListener, cb);
/*    */   }
/*    */   
/*    */   public void rowsDeleted(int index, int count) {
/* 74 */     if (this.leadIndex >= index) {
/* 75 */       this.leadIndex = Math.max(index, this.leadIndex - count);
/*    */     }
/* 77 */     if (this.anchorIndex >= index) {
/* 78 */       this.anchorIndex = Math.max(index, this.anchorIndex - count);
/*    */     }
/*    */   }
/*    */   
/*    */   public void rowsInserted(int index, int count) {
/* 83 */     if (this.leadIndex >= index) {
/* 84 */       this.leadIndex += count;
/*    */     }
/* 86 */     if (this.anchorIndex >= index) {
/* 87 */       this.anchorIndex += count;
/*    */     }
/*    */   }
/*    */   
/*    */   protected void fireSelectionChange() {
/* 92 */     CallbackSupport.fireCallbacks(this.selectionChangeListener);
/*    */   }
/*    */   
/*    */   protected void updateLeadAndAnchor(int index0, int index1) {
/* 96 */     this.anchorIndex = index0;
/* 97 */     this.leadIndex = index1;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\AbstractTableSelectionModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */