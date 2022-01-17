/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.IntegerModel;
/*     */ import de.matthiasmann.twl.model.ListModel;
/*     */ import de.matthiasmann.twl.model.ListSelectionModel;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListBox<T>
/*     */   extends Widget
/*     */ {
/*     */   public static final int NO_SELECTION = -1;
/*     */   public static final int DEFAULT_CELL_HEIGHT = 20;
/*     */   public static final int SINGLE_COLUMN = -1;
/*     */   
/*     */   public enum CallbackReason
/*     */   {
/*  57 */     MODEL_CHANGED(false),
/*  58 */     SET_SELECTED(false),
/*  59 */     MOUSE_CLICK(false),
/*  60 */     MOUSE_DOUBLE_CLICK(true),
/*  61 */     KEYBOARD(false),
/*  62 */     KEYBOARD_RETURN(true);
/*     */     final boolean forceCallback;
/*     */     
/*     */     CallbackReason(boolean forceCallback) {
/*  66 */       this.forceCallback = forceCallback;
/*     */     }
/*     */     
/*     */     public boolean actionRequested() {
/*  70 */       return this.forceCallback;
/*     */     }
/*     */   }
/*     */   
/*  74 */   private static final ListBoxDisplay[] EMPTY_LABELS = new ListBoxDisplay[0];
/*     */   
/*     */   private final ListModel.ChangeListener modelCallback;
/*     */   private final Scrollbar scrollbar;
/*     */   private ListBoxDisplay[] labels;
/*     */   private ListModel<T> model;
/*     */   private IntegerModel selectionModel;
/*     */   private Runnable selectionModelCallback;
/*  82 */   private int cellHeight = 20;
/*  83 */   private int cellWidth = -1;
/*     */   private boolean rowMajor = true;
/*     */   private boolean fixedCellWidth;
/*     */   private boolean fixedCellHeight;
/*  87 */   private int minDisplayedRows = 1;
/*     */   
/*  89 */   private int numCols = 1;
/*     */   private int firstVisible;
/*  91 */   private int selected = -1;
/*     */   private int numEntries;
/*     */   private boolean needUpdate;
/*     */   private boolean inSetSelected;
/*     */   private CallbackWithReason<?>[] callbacks;
/*     */   
/*     */   public ListBox() {
/*  98 */     LImpl li = new LImpl();
/*     */     
/* 100 */     this.modelCallback = li;
/* 101 */     this.scrollbar = new Scrollbar();
/* 102 */     this.scrollbar.addCallback(li);
/* 103 */     this.labels = EMPTY_LABELS;
/*     */     
/* 105 */     super.insertChild(this.scrollbar, 0);
/*     */     
/* 107 */     setSize(200, 300);
/* 108 */     setCanAcceptKeyboardFocus(true);
/* 109 */     setDepthFocusTraversal(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public ListBox(ListModel<T> model) {
/* 114 */     this();
/* 115 */     setModel(model);
/*     */   }
/*     */ 
/*     */   
/*     */   public ListBox(ListSelectionModel<T> model) {
/* 120 */     this();
/* 121 */     setModel(model);
/*     */   }
/*     */   
/*     */   public ListModel<T> getModel() {
/* 125 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(ListModel<T> model) {
/* 129 */     if (this.model != model) {
/* 130 */       if (this.model != null) {
/* 131 */         this.model.removeChangeListener(this.modelCallback);
/*     */       }
/* 133 */       this.model = model;
/* 134 */       if (model != null) {
/* 135 */         model.addChangeListener(this.modelCallback);
/*     */       }
/* 137 */       this.modelCallback.allChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   public IntegerModel getSelectionModel() {
/* 142 */     return this.selectionModel;
/*     */   }
/*     */   
/*     */   public void setSelectionModel(IntegerModel selectionModel) {
/* 146 */     if (this.selectionModel != selectionModel) {
/* 147 */       if (this.selectionModel != null) {
/* 148 */         this.selectionModel.removeCallback(this.selectionModelCallback);
/*     */       }
/* 150 */       this.selectionModel = selectionModel;
/* 151 */       if (selectionModel != null) {
/* 152 */         if (this.selectionModelCallback == null) {
/* 153 */           this.selectionModelCallback = new Runnable() {
/*     */               public void run() {
/* 155 */                 ListBox.this.syncSelectionFromModel();
/*     */               }
/*     */             };
/*     */         }
/* 159 */         this.selectionModel.addCallback(this.selectionModelCallback);
/* 160 */         syncSelectionFromModel();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setModel(ListSelectionModel<T> model) {
/* 166 */     setSelectionModel((IntegerModel)null);
/* 167 */     if (model == null) {
/* 168 */       setModel((ListModel<T>)null);
/*     */     } else {
/* 170 */       setModel(model.getListModel());
/* 171 */       setSelectionModel((IntegerModel)model);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addCallback(CallbackWithReason<CallbackReason> cb) {
/* 176 */     this.callbacks = (CallbackWithReason<?>[])CallbackSupport.addCallbackToList((Object[])this.callbacks, cb, CallbackWithReason.class);
/*     */   }
/*     */   
/*     */   public void removeCallback(CallbackWithReason<CallbackReason> cb) {
/* 180 */     this.callbacks = (CallbackWithReason<?>[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, cb);
/*     */   }
/*     */   
/*     */   private void doCallback(CallbackReason reason) {
/* 184 */     CallbackSupport.fireCallbacks((CallbackWithReason[])this.callbacks, reason);
/*     */   }
/*     */   
/*     */   public int getCellHeight() {
/* 188 */     return this.cellHeight;
/*     */   }
/*     */   
/*     */   public void setCellHeight(int cellHeight) {
/* 192 */     if (cellHeight < 1) {
/* 193 */       throw new IllegalArgumentException("cellHeight < 1");
/*     */     }
/* 195 */     this.cellHeight = cellHeight;
/*     */   }
/*     */   
/*     */   public int getCellWidth() {
/* 199 */     return this.cellWidth;
/*     */   }
/*     */   
/*     */   public void setCellWidth(int cellWidth) {
/* 203 */     if (cellWidth < 1 && cellWidth != -1) {
/* 204 */       throw new IllegalArgumentException("cellWidth < 1");
/*     */     }
/* 206 */     this.cellWidth = cellWidth;
/*     */   }
/*     */   
/*     */   public boolean isFixedCellHeight() {
/* 210 */     return this.fixedCellHeight;
/*     */   }
/*     */   
/*     */   public void setFixedCellHeight(boolean fixedCellHeight) {
/* 214 */     this.fixedCellHeight = fixedCellHeight;
/*     */   }
/*     */   
/*     */   public boolean isFixedCellWidth() {
/* 218 */     return this.fixedCellWidth;
/*     */   }
/*     */   
/*     */   public void setFixedCellWidth(boolean fixedCellWidth) {
/* 222 */     this.fixedCellWidth = fixedCellWidth;
/*     */   }
/*     */   
/*     */   public boolean isRowMajor() {
/* 226 */     return this.rowMajor;
/*     */   }
/*     */   
/*     */   public void setRowMajor(boolean rowMajor) {
/* 230 */     this.rowMajor = rowMajor;
/*     */   }
/*     */   
/*     */   public int getFirstVisible() {
/* 234 */     return this.firstVisible;
/*     */   }
/*     */   
/*     */   public int getLastVisible() {
/* 238 */     return getFirstVisible() + this.labels.length - 1;
/*     */   }
/*     */   
/*     */   public void setFirstVisible(int firstVisible) {
/* 242 */     firstVisible = Math.max(0, Math.min(firstVisible, this.numEntries - 1));
/* 243 */     if (this.firstVisible != firstVisible) {
/* 244 */       this.firstVisible = firstVisible;
/* 245 */       this.scrollbar.setValue(firstVisible / this.numCols, false);
/* 246 */       this.needUpdate = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getSelected() {
/* 251 */     return this.selected;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelected(int selected) {
/* 262 */     setSelected(selected, true, CallbackReason.SET_SELECTED);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelected(int selected, boolean scroll) {
/* 273 */     setSelected(selected, scroll, CallbackReason.SET_SELECTED);
/*     */   }
/*     */   
/*     */   void setSelected(int selected, boolean scroll, CallbackReason reason) {
/* 277 */     if (selected < -1 || selected >= this.numEntries) {
/* 278 */       throw new IllegalArgumentException();
/*     */     }
/* 280 */     if (scroll) {
/* 281 */       validateLayout();
/* 282 */       if (selected == -1) {
/* 283 */         setFirstVisible(0);
/*     */       } else {
/* 285 */         int delta = getFirstVisible() - selected;
/* 286 */         if (delta > 0) {
/* 287 */           int deltaRows = (delta + this.numCols - 1) / this.numCols;
/* 288 */           setFirstVisible(getFirstVisible() - deltaRows * this.numCols);
/*     */         } else {
/* 290 */           delta = selected - getLastVisible();
/* 291 */           if (delta > 0) {
/* 292 */             int deltaRows = (delta + this.numCols - 1) / this.numCols;
/* 293 */             setFirstVisible(getFirstVisible() + deltaRows * this.numCols);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 298 */     if (this.selected != selected) {
/* 299 */       this.selected = selected;
/* 300 */       if (this.selectionModel != null) {
/*     */         try {
/* 302 */           this.inSetSelected = true;
/* 303 */           this.selectionModel.setValue(selected);
/*     */         } finally {
/* 305 */           this.inSetSelected = false;
/*     */         } 
/*     */       }
/* 308 */       this.needUpdate = true;
/* 309 */       doCallback(reason);
/* 310 */     } else if (reason.actionRequested() || reason == CallbackReason.MOUSE_CLICK) {
/* 311 */       doCallback(reason);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void scrollToSelected() {
/* 316 */     setSelected(this.selected, true, CallbackReason.SET_SELECTED);
/*     */   }
/*     */   
/*     */   public int getNumEntries() {
/* 320 */     return this.numEntries;
/*     */   }
/*     */   
/*     */   public int getNumRows() {
/* 324 */     return (this.numEntries + this.numCols - 1) / this.numCols;
/*     */   }
/*     */   
/*     */   public int getNumColumns() {
/* 328 */     return this.numCols;
/*     */   }
/*     */   public int findEntryByName(String prefix) {
/*     */     int i;
/* 332 */     for (i = this.selected + 1; i < this.numEntries; i++) {
/* 333 */       if (this.model.matchPrefix(i, prefix)) {
/* 334 */         return i;
/*     */       }
/*     */     } 
/* 337 */     for (i = 0; i < this.selected; i++) {
/* 338 */       if (this.model.matchPrefix(i, prefix)) {
/* 339 */         return i;
/*     */       }
/*     */     } 
/* 342 */     return -1;
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
/*     */   
/*     */   public Widget getWidgetAt(int x, int y) {
/* 355 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEntryAt(int x, int y) {
/* 366 */     int n = Math.max(this.labels.length, this.numEntries - this.firstVisible);
/* 367 */     for (int i = 0; i < n; i++) {
/* 368 */       if (this.labels[i].getWidget().isInside(x, y)) {
/* 369 */         return this.firstVisible + i;
/*     */       }
/*     */     } 
/* 372 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertChild(Widget child, int index) throws IndexOutOfBoundsException {
/* 377 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAllChildren() {
/* 382 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public Widget removeChild(int index) throws IndexOutOfBoundsException {
/* 387 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 392 */     super.applyTheme(themeInfo);
/* 393 */     setCellHeight(themeInfo.getParameter("cellHeight", 20));
/* 394 */     setCellWidth(themeInfo.getParameter("cellWidth", -1));
/* 395 */     setRowMajor(themeInfo.getParameter("rowMajor", true));
/* 396 */     setFixedCellWidth(themeInfo.getParameter("fixedCellWidth", false));
/* 397 */     setFixedCellHeight(themeInfo.getParameter("fixedCellHeight", false));
/* 398 */     this.minDisplayedRows = themeInfo.getParameter("minDisplayedRows", 1);
/*     */   }
/*     */   
/*     */   protected void goKeyboard(int dir) {
/* 402 */     int newPos = this.selected + dir;
/* 403 */     if (newPos >= 0 && newPos < this.numEntries) {
/* 404 */       setSelected(newPos, true, CallbackReason.KEYBOARD);
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean isSearchChar(char ch) {
/* 409 */     return (ch != '\000' && Character.isLetterOrDigit(ch));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyboardFocusGained() {
/* 414 */     setLabelFocused(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyboardFocusLost() {
/* 419 */     setLabelFocused(false);
/*     */   }
/*     */   
/*     */   private void setLabelFocused(boolean focused) {
/* 423 */     int idx = this.selected - this.firstVisible;
/* 424 */     if (idx >= 0 && idx < this.labels.length) {
/* 425 */       this.labels[idx].setFocused(focused);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean handleEvent(Event evt) {
/* 431 */     switch (evt.getType()) {
/*     */       case MOUSE_WHEEL:
/* 433 */         this.scrollbar.scroll(-evt.getMouseWheelDelta());
/* 434 */         return true;
/*     */       case KEY_PRESSED:
/* 436 */         switch (evt.getKeyCode())
/*     */         { case 200:
/* 438 */             goKeyboard(-this.numCols);
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
/* 480 */             return true;case 208: goKeyboard(this.numCols); return true;case 203: goKeyboard(-1); return true;case 205: goKeyboard(1); return true;case 201: if (this.numEntries > 0) setSelected(Math.max(0, this.selected - this.labels.length), true, CallbackReason.KEYBOARD);  return true;case 209: setSelected(Math.min(this.numEntries - 1, this.selected + this.labels.length), true, CallbackReason.KEYBOARD); return true;case 199: if (this.numEntries > 0) setSelected(0, true, CallbackReason.KEYBOARD);  return true;case 207: setSelected(this.numEntries - 1, true, CallbackReason.KEYBOARD); return true;case 28: setSelected(this.selected, false, CallbackReason.KEYBOARD_RETURN); return true; }  if (evt.hasKeyChar() && isSearchChar(evt.getKeyChar())) { int idx = findEntryByName(Character.toString(evt.getKeyChar())); if (idx != -1) setSelected(idx, true, CallbackReason.KEYBOARD);  return true; }  return true;
/*     */       case KEY_RELEASED:
/* 482 */         return true;
/*     */     } 
/*     */     
/* 485 */     if (super.handleEvent(evt)) {
/* 486 */       return true;
/*     */     }
/*     */     
/* 489 */     return evt.isMouseEvent();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/* 494 */     return Math.max(super.getMinWidth(), this.scrollbar.getMinWidth());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/* 499 */     int minHeight = Math.max(super.getMinHeight(), this.scrollbar.getMinHeight());
/* 500 */     if (this.minDisplayedRows > 0) {
/* 501 */       minHeight = Math.max(minHeight, getBorderVertical() + Math.min(this.numEntries, this.minDisplayedRows) * this.cellHeight);
/*     */     }
/*     */     
/* 504 */     return minHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 509 */     return Math.max(super.getPreferredInnerWidth(), this.scrollbar.getPreferredWidth());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 514 */     return Math.max(getNumRows() * getCellHeight(), this.scrollbar.getPreferredHeight());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paint(GUI gui) {
/* 519 */     if (this.needUpdate) {
/* 520 */       updateDisplay();
/*     */     }
/*     */     
/* 523 */     int maxFirstVisibleRow = computeMaxFirstVisibleRow();
/* 524 */     this.scrollbar.setMinMaxValue(0, maxFirstVisibleRow);
/* 525 */     this.scrollbar.setValue(this.firstVisible / this.numCols, false);
/*     */     
/* 527 */     super.paint(gui);
/*     */   }
/*     */   
/*     */   private int computeMaxFirstVisibleRow() {
/* 531 */     int maxFirstVisibleRow = Math.max(0, this.numEntries - this.labels.length);
/* 532 */     maxFirstVisibleRow = (maxFirstVisibleRow + this.numCols - 1) / this.numCols;
/* 533 */     return maxFirstVisibleRow;
/*     */   }
/*     */   
/*     */   private void updateDisplay() {
/* 537 */     this.needUpdate = false;
/*     */     
/* 539 */     if (this.selected >= this.numEntries) {
/* 540 */       this.selected = -1;
/*     */     }
/*     */     
/* 543 */     int maxFirstVisibleRow = computeMaxFirstVisibleRow();
/* 544 */     int maxFirstVisible = maxFirstVisibleRow * this.numCols;
/* 545 */     if (this.firstVisible > maxFirstVisible) {
/* 546 */       this.firstVisible = Math.max(0, maxFirstVisible);
/*     */     }
/*     */     
/* 549 */     boolean hasFocus = hasKeyboardFocus();
/*     */     
/* 551 */     for (int i = 0; i < this.labels.length; i++) {
/* 552 */       ListBoxDisplay label = this.labels[i];
/* 553 */       int cell = i + this.firstVisible;
/* 554 */       if (cell < this.numEntries) {
/* 555 */         label.setData(this.model.getEntry(cell));
/* 556 */         label.setTooltipContent(this.model.getEntryTooltip(cell));
/*     */       } else {
/* 558 */         label.setData(null);
/* 559 */         label.setTooltipContent(null);
/*     */       } 
/* 561 */       label.setSelected((cell == this.selected));
/* 562 */       label.setFocused((cell == this.selected && hasFocus));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 568 */     this.scrollbar.setSize(this.scrollbar.getPreferredWidth(), getInnerHeight());
/* 569 */     this.scrollbar.setPosition(getInnerRight() - this.scrollbar.getWidth(), getInnerY());
/*     */     
/* 571 */     int numRows = Math.max(1, getInnerHeight() / this.cellHeight);
/* 572 */     if (this.cellWidth != -1) {
/* 573 */       this.numCols = Math.max(1, (this.scrollbar.getX() - getInnerX()) / this.cellWidth);
/*     */     } else {
/* 575 */       this.numCols = 1;
/*     */     } 
/* 577 */     setVisibleCells(numRows);
/*     */     
/* 579 */     this.needUpdate = true;
/*     */   }
/*     */   
/*     */   private void setVisibleCells(int numRows) {
/* 583 */     int visibleCells = numRows * this.numCols;
/* 584 */     assert visibleCells >= 1;
/*     */     
/* 586 */     this.scrollbar.setPageSize(visibleCells);
/*     */     
/* 588 */     int curVisible = this.labels.length;
/* 589 */     for (int i = curVisible; i-- > visibleCells;) {
/* 590 */       super.removeChild(1 + i);
/*     */     }
/*     */     
/* 593 */     ListBoxDisplay[] newLabels = new ListBoxDisplay[visibleCells];
/* 594 */     System.arraycopy(this.labels, 0, newLabels, 0, Math.min(visibleCells, this.labels.length));
/* 595 */     this.labels = newLabels;
/*     */     
/* 597 */     for (int j = curVisible; j < visibleCells; j++) {
/* 598 */       final int cellOffset = j;
/* 599 */       ListBoxDisplay lbd = createDisplay();
/* 600 */       lbd.addListBoxCallback(new CallbackWithReason<CallbackReason>() {
/*     */             public void callback(ListBox.CallbackReason reason) {
/* 602 */               int cell = ListBox.this.getFirstVisible() + cellOffset;
/* 603 */               if (cell < ListBox.this.getNumEntries()) {
/* 604 */                 ListBox.this.setSelected(cell, false, reason);
/*     */               }
/*     */             }
/*     */           });
/* 608 */       super.insertChild(lbd.getWidget(), 1 + j);
/* 609 */       this.labels[j] = lbd;
/*     */     } 
/*     */     
/* 612 */     int innerWidth = this.scrollbar.getX() - getInnerX();
/* 613 */     int innerHeight = getInnerHeight();
/* 614 */     for (int k = 0; k < visibleCells; k++) {
/*     */       int row, col, x, y, w, h;
/* 616 */       if (this.rowMajor) {
/* 617 */         row = k / this.numCols;
/* 618 */         col = k % this.numCols;
/*     */       } else {
/* 620 */         row = k % numRows;
/* 621 */         col = k / numRows;
/*     */       } 
/*     */       
/* 624 */       if (this.fixedCellHeight) {
/* 625 */         y = row * this.cellHeight;
/* 626 */         h = this.cellHeight;
/*     */       } else {
/* 628 */         y = row * innerHeight / numRows;
/* 629 */         h = (row + 1) * innerHeight / numRows - y;
/*     */       } 
/* 631 */       if (this.fixedCellWidth && this.cellWidth != -1) {
/* 632 */         x = col * this.cellWidth;
/* 633 */         w = this.cellWidth;
/*     */       } else {
/* 635 */         x = col * innerWidth / this.numCols;
/* 636 */         w = (col + 1) * innerWidth / this.numCols - x;
/*     */       } 
/* 638 */       Widget cell = (Widget)this.labels[k];
/* 639 */       cell.setSize(Math.max(0, w), Math.max(0, h));
/* 640 */       cell.setPosition(x + getInnerX(), y + getInnerY());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected ListBoxDisplay createDisplay() {
/* 645 */     return new ListBoxLabel();
/*     */   }
/*     */   
/*     */   protected static class ListBoxLabel extends TextWidget implements ListBoxDisplay {
/* 649 */     public static final AnimationState.StateKey STATE_SELECTED = AnimationState.StateKey.get("selected");
/* 650 */     public static final AnimationState.StateKey STATE_EMPTY = AnimationState.StateKey.get("empty");
/*     */     
/*     */     private boolean selected;
/*     */     private CallbackWithReason<?>[] callbacks;
/*     */     
/*     */     public ListBoxLabel() {
/* 656 */       setClip(true);
/* 657 */       setTheme("display");
/*     */     }
/*     */     
/*     */     public boolean isSelected() {
/* 661 */       return this.selected;
/*     */     }
/*     */     
/*     */     public void setSelected(boolean selected) {
/* 665 */       if (this.selected != selected) {
/* 666 */         this.selected = selected;
/* 667 */         getAnimationState().setAnimationState(STATE_SELECTED, selected);
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean isFocused() {
/* 672 */       return getAnimationState().getAnimationState(STATE_KEYBOARD_FOCUS);
/*     */     }
/*     */     
/*     */     public void setFocused(boolean focused) {
/* 676 */       getAnimationState().setAnimationState(STATE_KEYBOARD_FOCUS, focused);
/*     */     }
/*     */     
/*     */     public void setData(Object data) {
/* 680 */       setCharSequence((data == null) ? "" : data.toString());
/* 681 */       getAnimationState().setAnimationState(STATE_EMPTY, (data == null));
/*     */     }
/*     */     
/*     */     public Widget getWidget() {
/* 685 */       return this;
/*     */     }
/*     */     
/*     */     public void addListBoxCallback(CallbackWithReason<ListBox.CallbackReason> cb) {
/* 689 */       this.callbacks = (CallbackWithReason<?>[])CallbackSupport.addCallbackToList((Object[])this.callbacks, cb, CallbackWithReason.class);
/*     */     }
/*     */     
/*     */     public void removeListBoxCallback(CallbackWithReason<ListBox.CallbackReason> cb) {
/* 693 */       this.callbacks = (CallbackWithReason<?>[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, cb);
/*     */     }
/*     */     
/*     */     protected void doListBoxCallback(ListBox.CallbackReason reason) {
/* 697 */       CallbackSupport.fireCallbacks((CallbackWithReason[])this.callbacks, reason);
/*     */     }
/*     */     
/*     */     protected boolean handleListBoxEvent(Event evt) {
/* 701 */       switch (evt.getType()) {
/*     */         case MOUSE_BTNDOWN:
/* 703 */           if (!this.selected) {
/* 704 */             doListBoxCallback(ListBox.CallbackReason.MOUSE_CLICK);
/*     */           }
/* 706 */           return true;
/*     */         case MOUSE_CLICKED:
/* 708 */           if (this.selected && evt.getMouseClickCount() == 2) {
/* 709 */             doListBoxCallback(ListBox.CallbackReason.MOUSE_DOUBLE_CLICK);
/*     */           }
/* 711 */           return true;
/*     */       } 
/* 713 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean handleEvent(Event evt) {
/* 718 */       handleMouseHover(evt);
/* 719 */       if (!evt.isMouseDragEvent() && 
/* 720 */         handleListBoxEvent(evt)) {
/* 721 */         return true;
/*     */       }
/*     */       
/* 724 */       if (super.handleEvent(evt)) {
/* 725 */         return true;
/*     */       }
/* 727 */       return evt.isMouseEventNoWheel();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void entriesInserted(int first, int last) {
/* 733 */     int delta = last - first + 1;
/* 734 */     int prevNumEntries = this.numEntries;
/* 735 */     this.numEntries += delta;
/* 736 */     int fv = getFirstVisible();
/* 737 */     if (fv >= first && prevNumEntries >= this.labels.length) {
/* 738 */       fv += delta;
/* 739 */       setFirstVisible(fv);
/*     */     } 
/* 741 */     int s = getSelected();
/* 742 */     if (s >= first) {
/* 743 */       setSelected(s + delta, false, CallbackReason.MODEL_CHANGED);
/*     */     }
/* 745 */     if (first <= getLastVisible() && last >= fv) {
/* 746 */       this.needUpdate = true;
/*     */     }
/*     */   }
/*     */   
/*     */   void entriesDeleted(int first, int last) {
/* 751 */     int delta = last - first + 1;
/* 752 */     this.numEntries -= delta;
/* 753 */     int fv = getFirstVisible();
/* 754 */     int lv = getLastVisible();
/* 755 */     if (fv > last) {
/* 756 */       setFirstVisible(fv - delta);
/* 757 */     } else if (fv <= last && lv >= first) {
/* 758 */       setFirstVisible(first);
/*     */     } 
/* 760 */     int s = getSelected();
/* 761 */     if (s > last) {
/* 762 */       setSelected(s - delta, false, CallbackReason.MODEL_CHANGED);
/* 763 */     } else if (s >= first && s <= last) {
/* 764 */       setSelected(-1, false, CallbackReason.MODEL_CHANGED);
/*     */     } 
/*     */   }
/*     */   
/*     */   void entriesChanged(int first, int last) {
/* 769 */     int fv = getFirstVisible();
/* 770 */     int lv = getLastVisible();
/* 771 */     if (fv <= last && lv >= first) {
/* 772 */       this.needUpdate = true;
/*     */     }
/*     */   }
/*     */   
/*     */   void allChanged() {
/* 777 */     this.numEntries = (this.model != null) ? this.model.getNumEntries() : 0;
/* 778 */     setSelected(-1, false, CallbackReason.MODEL_CHANGED);
/* 779 */     setFirstVisible(0);
/* 780 */     this.needUpdate = true;
/*     */   }
/*     */   
/*     */   void scrollbarChanged() {
/* 784 */     setFirstVisible(this.scrollbar.getValue() * this.numCols);
/*     */   }
/*     */   
/*     */   void syncSelectionFromModel() {
/* 788 */     if (!this.inSetSelected)
/* 789 */       setSelected(this.selectionModel.getValue()); 
/*     */   }
/*     */   
/*     */   private class LImpl implements ListModel.ChangeListener, Runnable { private LImpl() {}
/*     */     
/*     */     public void entriesInserted(int first, int last) {
/* 795 */       ListBox.this.entriesInserted(first, last);
/*     */     }
/*     */     public void entriesDeleted(int first, int last) {
/* 798 */       ListBox.this.entriesDeleted(first, last);
/*     */     }
/*     */     public void entriesChanged(int first, int last) {
/* 801 */       ListBox.this.entriesChanged(first, last);
/*     */     }
/*     */     public void allChanged() {
/* 804 */       ListBox.this.allChanged();
/*     */     }
/*     */     public void run() {
/* 807 */       ListBox.this.scrollbarChanged();
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ListBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */