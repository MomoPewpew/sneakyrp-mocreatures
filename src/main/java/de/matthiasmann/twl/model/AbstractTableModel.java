/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ import de.matthiasmann.twl.utils.CallbackSupport;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractTableModel
/*     */   extends AbstractTableColumnHeaderModel
/*     */   implements TableModel
/*     */ {
/*     */   private TableModel.ChangeListener[] callbacks;
/*     */   
/*     */   public Object getTooltipContent(int row, int column) {
/*  43 */     return null;
/*     */   }
/*     */   
/*     */   public void addChangeListener(TableModel.ChangeListener listener) {
/*  47 */     this.callbacks = (TableModel.ChangeListener[])CallbackSupport.addCallbackToList((Object[])this.callbacks, listener, TableModel.ChangeListener.class);
/*     */   }
/*     */   
/*     */   public void removeChangeListener(TableModel.ChangeListener listener) {
/*  51 */     this.callbacks = (TableModel.ChangeListener[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, listener);
/*     */   }
/*     */   
/*     */   protected boolean hasCallbacks() {
/*  55 */     return (this.callbacks != null);
/*     */   }
/*     */   
/*     */   protected void fireRowsInserted(int idx, int count) {
/*  59 */     if (this.callbacks != null) {
/*  60 */       for (TableModel.ChangeListener cl : this.callbacks) {
/*  61 */         cl.rowsInserted(idx, count);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireRowsDeleted(int idx, int count) {
/*  67 */     if (this.callbacks != null) {
/*  68 */       for (TableModel.ChangeListener cl : this.callbacks) {
/*  69 */         cl.rowsDeleted(idx, count);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireRowsChanged(int idx, int count) {
/*  75 */     if (this.callbacks != null) {
/*  76 */       for (TableModel.ChangeListener cl : this.callbacks) {
/*  77 */         cl.rowsChanged(idx, count);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireColumnInserted(int idx, int count) {
/*  83 */     if (this.callbacks != null) {
/*  84 */       for (TableModel.ChangeListener cl : this.callbacks) {
/*  85 */         cl.columnInserted(idx, count);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireColumnDeleted(int idx, int count) {
/*  91 */     if (this.callbacks != null) {
/*  92 */       for (TableModel.ChangeListener cl : this.callbacks) {
/*  93 */         cl.columnDeleted(idx, count);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireColumnHeaderChanged(int column) {
/*  99 */     if (this.callbacks != null) {
/* 100 */       for (TableModel.ChangeListener cl : this.callbacks) {
/* 101 */         cl.columnHeaderChanged(column);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireCellChanged(int row, int column) {
/* 107 */     if (this.callbacks != null) {
/* 108 */       for (TableModel.ChangeListener cl : this.callbacks) {
/* 109 */         cl.cellChanged(row, column);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireAllChanged() {
/* 115 */     if (this.callbacks != null)
/* 116 */       for (TableModel.ChangeListener cl : this.callbacks)
/* 117 */         cl.allChanged();  
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\AbstractTableModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */