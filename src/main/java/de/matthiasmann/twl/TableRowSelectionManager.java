/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.DefaultTableSelectionModel;
/*     */ import de.matthiasmann.twl.model.TableSelectionModel;
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
/*     */ public class TableRowSelectionManager
/*     */   implements TableSelectionManager
/*     */ {
/*     */   protected final ActionMap actionMap;
/*     */   protected final TableSelectionModel selectionModel;
/*     */   protected TableBase tableBase;
/*     */   protected static final int TOGGLE = 0;
/*     */   protected static final int EXTEND = 1;
/*     */   protected static final int SET = 2;
/*     */   protected static final int MOVE = 3;
/*     */   
/*     */   public TableRowSelectionManager(TableSelectionModel selectionModel) {
/*  51 */     if (selectionModel == null) {
/*  52 */       throw new NullPointerException("selectionModel");
/*     */     }
/*  54 */     this.selectionModel = selectionModel;
/*  55 */     this.actionMap = new ActionMap();
/*     */     
/*  57 */     this.actionMap.addMapping(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TableRowSelectionManager() {
/*  66 */     this((TableSelectionModel)new DefaultTableSelectionModel());
/*     */   }
/*     */   
/*     */   public TableSelectionModel getSelectionModel() {
/*  70 */     return this.selectionModel;
/*     */   }
/*     */   
/*     */   public void setAssociatedTable(TableBase base) {
/*  74 */     if (this.tableBase != base) {
/*  75 */       if (this.tableBase != null && base != null) {
/*  76 */         throw new IllegalStateException("selection manager still in use");
/*     */       }
/*  78 */       this.tableBase = base;
/*  79 */       modelChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   public TableSelectionManager.SelectionGranularity getSelectionGranularity() {
/*  84 */     return TableSelectionManager.SelectionGranularity.ROWS;
/*     */   }
/*     */   
/*     */   public boolean handleKeyStrokeAction(String action, Event event) {
/*  88 */     return this.actionMap.invoke(action, event);
/*     */   }
/*     */   
/*     */   public boolean handleMouseEvent(int row, int column, Event event) {
/*  92 */     boolean isShift = ((event.getModifiers() & 0x9) != 0);
/*  93 */     boolean isCtrl = ((event.getModifiers() & 0x24) != 0);
/*  94 */     if (event.getType() == Event.Type.MOUSE_BTNDOWN && event.getMouseButton() == 0) {
/*  95 */       handleMouseDown(row, column, isShift, isCtrl);
/*  96 */       return true;
/*     */     } 
/*  98 */     if (event.getType() == Event.Type.MOUSE_CLICKED) {
/*  99 */       return handleMouseClick(row, column, isShift, isCtrl);
/*     */     }
/* 101 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isRowSelected(int row) {
/* 105 */     return this.selectionModel.isSelected(row);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCellSelected(int row, int column) {
/* 117 */     return false;
/*     */   }
/*     */   
/*     */   public int getLeadRow() {
/* 121 */     return this.selectionModel.getLeadIndex();
/*     */   }
/*     */   
/*     */   public int getLeadColumn() {
/* 125 */     return -1;
/*     */   }
/*     */   
/*     */   public void modelChanged() {
/* 129 */     this.selectionModel.clearSelection();
/* 130 */     this.selectionModel.setAnchorIndex(-1);
/* 131 */     this.selectionModel.setLeadIndex(-1);
/*     */   }
/*     */   
/*     */   public void rowsInserted(int index, int count) {
/* 135 */     this.selectionModel.rowsInserted(index, count);
/*     */   }
/*     */   
/*     */   public void rowsDeleted(int index, int count) {
/* 139 */     this.selectionModel.rowsDeleted(index, count);
/*     */   }
/*     */ 
/*     */   
/*     */   public void columnInserted(int index, int count) {}
/*     */ 
/*     */   
/*     */   public void columnsDeleted(int index, int count) {}
/*     */   
/*     */   @Action
/*     */   public void selectNextRow() {
/* 150 */     handleRelativeAction(1, 2);
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void selectPreviousRow() {
/* 155 */     handleRelativeAction(-1, 2);
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void selectNextPage() {
/* 160 */     handleRelativeAction(getPageSize(), 2);
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void selectPreviousPage() {
/* 165 */     handleRelativeAction(-getPageSize(), 2);
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void selectFirstRow() {
/* 170 */     int numRows = getNumRows();
/* 171 */     if (numRows > 0) {
/* 172 */       handleAbsoluteAction(0, 2);
/*     */     }
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void selectLastRow() {
/* 178 */     int numRows = getNumRows();
/* 179 */     if (numRows > 0) {
/* 180 */       handleRelativeAction(numRows - 1, 2);
/*     */     }
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void extendSelectionToNextRow() {
/* 186 */     handleRelativeAction(1, 1);
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void extendSelectionToPreviousRow() {
/* 191 */     handleRelativeAction(-1, 1);
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void extendSelectionToNextPage() {
/* 196 */     handleRelativeAction(getPageSize(), 1);
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void extendSelectionToPreviousPage() {
/* 201 */     handleRelativeAction(-getPageSize(), 1);
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void extendSelectionToFirstRow() {
/* 206 */     int numRows = getNumRows();
/* 207 */     if (numRows > 0) {
/* 208 */       handleAbsoluteAction(0, 1);
/*     */     }
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void extendSelectionToLastRow() {
/* 214 */     int numRows = getNumRows();
/* 215 */     if (numRows > 0) {
/* 216 */       handleRelativeAction(numRows - 1, 1);
/*     */     }
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void moveLeadToNextRow() {
/* 222 */     handleRelativeAction(1, 3);
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void moveLeadToPreviousRow() {
/* 227 */     handleRelativeAction(-1, 3);
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void moveLeadToNextPage() {
/* 232 */     handleRelativeAction(getPageSize(), 3);
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void moveLeadToPreviousPage() {
/* 237 */     handleRelativeAction(-getPageSize(), 3);
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void moveLeadToFirstRow() {
/* 242 */     int numRows = getNumRows();
/* 243 */     if (numRows > 0) {
/* 244 */       handleAbsoluteAction(0, 3);
/*     */     }
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void moveLeadToLastRow() {
/* 250 */     int numRows = getNumRows();
/* 251 */     if (numRows > 0) {
/* 252 */       handleAbsoluteAction(numRows - 1, 3);
/*     */     }
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void toggleSelectionOnLeadRow() {
/* 258 */     int leadIndex = this.selectionModel.getLeadIndex();
/* 259 */     if (leadIndex > 0) {
/* 260 */       this.selectionModel.invertSelection(leadIndex, leadIndex);
/*     */     }
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void selectAll() {
/* 266 */     int numRows = getNumRows();
/* 267 */     if (numRows > 0) {
/* 268 */       this.selectionModel.setSelection(0, numRows - 1);
/*     */     }
/*     */   }
/*     */   
/*     */   @Action
/*     */   public void selectNone() {
/* 274 */     this.selectionModel.clearSelection();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleRelativeAction(int delta, int mode) {
/* 283 */     int numRows = getNumRows();
/* 284 */     if (numRows > 0) {
/* 285 */       int leadIndex = Math.max(0, this.selectionModel.getLeadIndex());
/* 286 */       int index = Math.max(0, Math.min(numRows - 1, leadIndex + delta));
/*     */       
/* 288 */       handleAbsoluteAction(index, mode);
/*     */     } 
/*     */   }
/*     */   protected void handleAbsoluteAction(int index, int mode) {
/*     */     int anchorIndex;
/* 293 */     if (this.tableBase != null) {
/* 294 */       this.tableBase.adjustScrollPosition(index);
/*     */     }
/*     */     
/* 297 */     switch (mode) {
/*     */       case 3:
/* 299 */         this.selectionModel.setLeadIndex(index);
/*     */         return;
/*     */       case 1:
/* 302 */         anchorIndex = Math.max(0, this.selectionModel.getAnchorIndex());
/* 303 */         this.selectionModel.setSelection(anchorIndex, index);
/*     */         return;
/*     */       case 0:
/* 306 */         this.selectionModel.invertSelection(index, index);
/*     */         return;
/*     */     } 
/* 309 */     this.selectionModel.setSelection(index, index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleMouseDown(int row, int column, boolean isShift, boolean isCtrl) {
/* 315 */     if (row < 0 || row >= getNumRows()) {
/* 316 */       if (!isShift)
/* 317 */         this.selectionModel.clearSelection(); 
/*     */     } else {
/*     */       boolean anchorSelected;
/* 320 */       this.tableBase.adjustScrollPosition(row);
/* 321 */       int anchorIndex = this.selectionModel.getAnchorIndex();
/*     */       
/* 323 */       if (anchorIndex == -1) {
/* 324 */         anchorIndex = 0;
/* 325 */         anchorSelected = false;
/*     */       } else {
/* 327 */         anchorSelected = this.selectionModel.isSelected(anchorIndex);
/*     */       } 
/*     */       
/* 330 */       if (isCtrl) {
/* 331 */         if (isShift) {
/* 332 */           if (anchorSelected) {
/* 333 */             this.selectionModel.addSelection(anchorIndex, row);
/*     */           } else {
/* 335 */             this.selectionModel.removeSelection(anchorIndex, row);
/*     */           } 
/* 337 */         } else if (this.selectionModel.isSelected(row)) {
/* 338 */           this.selectionModel.removeSelection(row, row);
/*     */         } else {
/* 340 */           this.selectionModel.addSelection(row, row);
/*     */         } 
/* 342 */       } else if (isShift) {
/* 343 */         this.selectionModel.setSelection(anchorIndex, row);
/*     */       } else {
/* 345 */         this.selectionModel.setSelection(row, row);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean handleMouseClick(int row, int column, boolean isShift, boolean isCtrl) {
/* 351 */     return false;
/*     */   }
/*     */   
/*     */   protected int getNumRows() {
/* 355 */     if (this.tableBase != null) {
/* 356 */       return this.tableBase.getNumRows();
/*     */     }
/* 358 */     return 0;
/*     */   }
/*     */   
/*     */   protected int getPageSize() {
/* 362 */     if (this.tableBase != null) {
/* 363 */       return Math.max(1, this.tableBase.getNumVisibleRows());
/*     */     }
/* 365 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\TableRowSelectionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */