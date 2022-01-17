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
/*    */ public class SimpleListSelectionModel<T>
/*    */   extends HasCallback
/*    */   implements ListSelectionModel<T>
/*    */ {
/*    */   private final ListModel<T> listModel;
/*    */   private int selected;
/*    */   
/*    */   public SimpleListSelectionModel(ListModel<T> listModel) {
/* 44 */     if (listModel == null) {
/* 45 */       throw new NullPointerException("listModel");
/*    */     }
/* 47 */     this.listModel = listModel;
/*    */   }
/*    */   
/*    */   public ListModel<T> getListModel() {
/* 51 */     return this.listModel;
/*    */   }
/*    */   
/*    */   public T getSelectedEntry() {
/* 55 */     if (this.selected >= 0 && this.selected < this.listModel.getNumEntries()) {
/* 56 */       return this.listModel.getEntry(this.selected);
/*    */     }
/* 58 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean setSelectedEntry(T entry) {
/* 63 */     return setSelectedEntry(entry, -1);
/*    */   }
/*    */   
/*    */   public boolean setSelectedEntry(T entry, int defaultIndex) {
/* 67 */     if (entry != null) {
/* 68 */       for (int i = 0, n = this.listModel.getNumEntries(); i < n; i++) {
/* 69 */         if (entry.equals(this.listModel.getEntry(i))) {
/* 70 */           setValue(i);
/* 71 */           return true;
/*    */         } 
/*    */       } 
/*    */     }
/* 75 */     setValue(defaultIndex);
/* 76 */     return false;
/*    */   }
/*    */   
/*    */   public int getMaxValue() {
/* 80 */     return this.listModel.getNumEntries() - 1;
/*    */   }
/*    */   
/*    */   public int getMinValue() {
/* 84 */     return -1;
/*    */   }
/*    */   
/*    */   public int getValue() {
/* 88 */     return this.selected;
/*    */   }
/*    */   
/*    */   public void setValue(int value) {
/* 92 */     if (value < -1) {
/* 93 */       throw new IllegalArgumentException("value");
/*    */     }
/* 95 */     if (this.selected != value) {
/* 96 */       this.selected = value;
/* 97 */       doCallback();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleListSelectionModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */