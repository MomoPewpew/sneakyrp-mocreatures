/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.TableModel;
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
/*     */ public class TableSearchWindow
/*     */   extends InfoWindow
/*     */   implements TableBase.KeyboardSearchHandler
/*     */ {
/*     */   private final TableSelectionModel selectionModel;
/*     */   private final EditField searchTextField;
/*     */   private final StringBuilder searchTextBuffer;
/*     */   private String searchText;
/*     */   private String searchTextLowercase;
/*     */   private Timer timer;
/*     */   private TableModel model;
/*     */   private int column;
/*     */   private int currentRow;
/*     */   private boolean searchStartOnly;
/*     */   
/*     */   public TableSearchWindow(Table table, TableSelectionModel selectionModel) {
/*  55 */     super(table);
/*  56 */     this.selectionModel = selectionModel;
/*  57 */     this.searchTextField = new EditField();
/*  58 */     this.searchTextBuffer = new StringBuilder();
/*  59 */     this.searchText = "";
/*     */     
/*  61 */     Label label = new Label("Search");
/*  62 */     label.setLabelFor(this.searchTextField);
/*     */     
/*  64 */     this.searchTextField.setReadOnly(true);
/*     */     
/*  66 */     DialogLayout l = new DialogLayout();
/*  67 */     l.setHorizontalGroup(l.createSequentialGroup().addWidget(label).addWidget(this.searchTextField));
/*     */ 
/*     */     
/*  70 */     l.setVerticalGroup(l.createParallelGroup().addWidget(label).addWidget(this.searchTextField));
/*     */ 
/*     */ 
/*     */     
/*  74 */     add(l);
/*     */   }
/*     */   
/*     */   public Table getTable() {
/*  78 */     return (Table)getOwner();
/*     */   }
/*     */   
/*     */   public TableModel getModel() {
/*  82 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(TableModel model, int column) {
/*  86 */     if (column < 0) {
/*  87 */       throw new IllegalArgumentException("column");
/*     */     }
/*  89 */     if (model != null && column >= model.getNumColumns()) {
/*  90 */       throw new IllegalArgumentException("column");
/*     */     }
/*  92 */     this.model = model;
/*  93 */     this.column = column;
/*  94 */     cancelSearch();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isActive() {
/*  99 */     return isOpen();
/*     */   }
/*     */   
/*     */   public void updateInfoWindowPosition() {
/* 103 */     adjustSize();
/* 104 */     setPosition(getOwner().getX(), getOwner().getBottom());
/*     */   }
/*     */   
/*     */   public boolean handleKeyEvent(Event evt) {
/* 108 */     if (this.model == null) {
/* 109 */       return false;
/*     */     }
/*     */     
/* 112 */     if (evt.isKeyPressedEvent())
/* 113 */     { switch (evt.getKeyCode())
/*     */       { case 1:
/* 115 */           if (isOpen()) {
/* 116 */             cancelSearch();
/* 117 */             return true;
/*     */           } 
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
/* 163 */           return false;case 28: return false;case 14: if (isOpen()) { int length = this.searchTextBuffer.length(); if (length > 0) { this.searchTextBuffer.setLength(length - 1); updateText(); }  restartTimer(); return true; }  return false;case 200: if (isOpen()) { searchDir(-1); restartTimer(); return true; }  return false;case 208: if (isOpen()) { searchDir(1); restartTimer(); return true; }  return false; }  if (evt.hasKeyCharNoModifiers()) { if (this.searchTextBuffer.length() == 0) { this.currentRow = Math.max(0, getTable().getSelectionManager().getLeadRow()); this.searchStartOnly = true; }  this.searchTextBuffer.append(evt.getKeyChar()); updateText(); restartTimer(); return true; }  }  return false;
/*     */   }
/*     */   
/*     */   public void cancelSearch() {
/* 167 */     this.searchTextBuffer.setLength(0);
/* 168 */     updateText();
/* 169 */     closeInfo();
/* 170 */     if (this.timer != null) {
/* 171 */       this.timer.stop();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void afterAddToGUI(GUI gui) {
/* 177 */     super.afterAddToGUI(gui);
/* 178 */     this.timer = gui.createTimer();
/* 179 */     this.timer.setDelay(3000);
/* 180 */     this.timer.setCallback(new Runnable() {
/*     */           public void run() {
/* 182 */             TableSearchWindow.this.cancelSearch();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   protected void beforeRemoveFromGUI(GUI gui) {
/* 189 */     this.timer.stop();
/* 190 */     this.timer = null;
/*     */     
/* 192 */     super.beforeRemoveFromGUI(gui);
/*     */   }
/*     */   
/*     */   private void updateText() {
/* 196 */     this.searchText = this.searchTextBuffer.toString();
/* 197 */     this.searchTextLowercase = null;
/* 198 */     this.searchTextField.setText(this.searchText);
/* 199 */     if (this.searchText.length() >= 0 && this.model != null) {
/* 200 */       if (!isOpen() && openInfo()) {
/* 201 */         updateInfoWindowPosition();
/*     */       }
/* 203 */       updateSearch();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void restartTimer() {
/* 208 */     this.timer.stop();
/* 209 */     this.timer.start();
/*     */   }
/*     */   
/*     */   private void updateSearch() {
/* 213 */     int numRows = this.model.getNumRows();
/* 214 */     if (numRows == 0)
/*     */       return; 
/*     */     int row;
/* 217 */     for (row = this.currentRow; row < numRows; row++) {
/* 218 */       if (checkRow(row)) {
/* 219 */         setRow(row);
/*     */         return;
/*     */       } 
/*     */     } 
/* 223 */     if (this.searchStartOnly) {
/* 224 */       this.searchStartOnly = false;
/*     */     } else {
/* 226 */       numRows = this.currentRow;
/*     */     } 
/* 228 */     for (row = 0; row < numRows; row++) {
/* 229 */       if (checkRow(row)) {
/* 230 */         setRow(row);
/*     */         return;
/*     */       } 
/*     */     } 
/* 234 */     this.searchTextField.setErrorMessage("'" + this.searchText + "' not found");
/*     */   }
/*     */   
/*     */   private void searchDir(int dir) {
/* 238 */     int numRows = this.model.getNumRows();
/* 239 */     if (numRows == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 243 */     int startRow = wrap(this.currentRow, numRows);
/* 244 */     int row = startRow;
/*     */ 
/*     */     
/*     */     while (true) {
/* 248 */       row = wrap(row + dir, numRows);
/* 249 */       if (checkRow(row)) {
/* 250 */         setRow(row);
/*     */         return;
/*     */       } 
/* 253 */       if (row == startRow) {
/*     */         
/* 255 */         if (!this.searchStartOnly) {
/*     */           break;
/*     */         }
/* 258 */         this.searchStartOnly = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void setRow(int row) {
/* 263 */     if (this.currentRow != row) {
/* 264 */       this.currentRow = row;
/* 265 */       getTable().scrollToRow(row);
/* 266 */       if (this.selectionModel != null) {
/* 267 */         this.selectionModel.setSelection(row, row);
/*     */       }
/*     */     } 
/* 270 */     this.searchTextField.setErrorMessage(null);
/*     */   }
/*     */   
/*     */   private boolean checkRow(int row) {
/* 274 */     Object data = this.model.getCell(row, this.column);
/* 275 */     if (data == null) {
/* 276 */       return false;
/*     */     }
/* 278 */     String str = data.toString();
/* 279 */     if (this.searchStartOnly) {
/* 280 */       return str.regionMatches(true, 0, this.searchText, 0, this.searchText.length());
/*     */     }
/* 282 */     str = str.toLowerCase();
/* 283 */     if (this.searchTextLowercase == null) {
/* 284 */       this.searchTextLowercase = this.searchText.toLowerCase();
/*     */     }
/* 286 */     return str.contains(this.searchTextLowercase);
/*     */   }
/*     */   
/*     */   private static int wrap(int row, int numRows) {
/* 290 */     if (row < 0) {
/* 291 */       return numRows - 1;
/*     */     }
/* 293 */     if (row >= numRows) {
/* 294 */       return 0;
/*     */     }
/* 296 */     return row;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\TableSearchWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */