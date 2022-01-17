/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.TableColumnHeaderModel;
/*     */ import de.matthiasmann.twl.model.TableModel;
/*     */ import de.matthiasmann.twl.model.TreeTableNode;
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
/*     */ public class Table
/*     */   extends TableBase
/*     */ {
/*  53 */   private final TableModel.ChangeListener modelChangeListener = new ModelChangeListener();
/*     */   
/*     */   TableModel model;
/*     */   
/*     */   public Table(TableModel model) {
/*  58 */     this();
/*  59 */     setModel(model);
/*     */   }
/*     */   public Table() {}
/*     */   public TableModel getModel() {
/*  63 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(TableModel model) {
/*  67 */     if (this.model != null) {
/*  68 */       this.model.removeChangeListener(this.modelChangeListener);
/*     */     }
/*  70 */     this.columnHeaderModel = (TableColumnHeaderModel)model;
/*  71 */     this.model = model;
/*  72 */     if (this.model != null) {
/*  73 */       this.numRows = model.getNumRows();
/*  74 */       this.numColumns = model.getNumColumns();
/*  75 */       this.model.addChangeListener(this.modelChangeListener);
/*     */     } else {
/*  77 */       this.numRows = 0;
/*  78 */       this.numColumns = 0;
/*     */     } 
/*  80 */     modelAllChanged();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object getCellData(int row, int column, TreeTableNode node) {
/*  85 */     return this.model.getCell(row, column);
/*     */   }
/*     */ 
/*     */   
/*     */   protected TreeTableNode getNodeFromRow(int row) {
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object getTooltipContentFromRow(int row, int column) {
/*  95 */     return this.model.getTooltipContent(row, column);
/*     */   }
/*     */   
/*     */   class ModelChangeListener implements TableModel.ChangeListener {
/*     */     public void rowsInserted(int idx, int count) {
/* 100 */       Table.this.numRows = Table.this.model.getNumRows();
/* 101 */       Table.this.modelRowsInserted(idx, count);
/*     */     }
/*     */     public void rowsDeleted(int idx, int count) {
/* 104 */       Table.this.checkRowRange(idx, count);
/* 105 */       Table.this.numRows = Table.this.model.getNumRows();
/* 106 */       Table.this.modelRowsDeleted(idx, count);
/*     */     }
/*     */     public void rowsChanged(int idx, int count) {
/* 109 */       Table.this.modelRowsChanged(idx, count);
/*     */     }
/*     */     public void columnDeleted(int idx, int count) {
/* 112 */       Table.this.checkColumnRange(idx, count);
/* 113 */       Table.this.numColumns = Table.this.model.getNumColumns();
/* 114 */       Table.this.modelColumnsDeleted(count, count);
/*     */     }
/*     */     public void columnInserted(int idx, int count) {
/* 117 */       Table.this.numColumns = Table.this.model.getNumColumns();
/* 118 */       Table.this.modelColumnsInserted(count, count);
/*     */     }
/*     */     public void columnHeaderChanged(int column) {
/* 121 */       Table.this.modelColumnHeaderChanged(column);
/*     */     }
/*     */     public void cellChanged(int row, int column) {
/* 124 */       Table.this.modelCellChanged(row, column);
/*     */     }
/*     */     public void allChanged() {
/* 127 */       Table.this.numRows = Table.this.model.getNumRows();
/* 128 */       Table.this.numColumns = Table.this.model.getNumColumns();
/* 129 */       Table.this.modelAllChanged();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Table.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */