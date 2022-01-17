/*      */ package de.matthiasmann.twl;
/*      */ 
/*      */ import de.matthiasmann.twl.model.SortOrder;
/*      */ import de.matthiasmann.twl.model.TableColumnHeaderModel;
/*      */ import de.matthiasmann.twl.model.TreeTableNode;
/*      */ import de.matthiasmann.twl.renderer.AnimationState;
/*      */ import de.matthiasmann.twl.renderer.Image;
/*      */ import de.matthiasmann.twl.renderer.MouseCursor;
/*      */ import de.matthiasmann.twl.renderer.Renderer;
/*      */ import de.matthiasmann.twl.utils.CallbackSupport;
/*      */ import de.matthiasmann.twl.utils.SizeSequence;
/*      */ import de.matthiasmann.twl.utils.SparseGrid;
/*      */ import de.matthiasmann.twl.utils.TypeMapping;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class TableBase
/*      */   extends Widget
/*      */   implements ScrollPane.Scrollable, ScrollPane.AutoScrollable, ScrollPane.CustomPageSize
/*      */ {
/*  187 */   public static final AnimationState.StateKey STATE_FIRST_COLUMNHEADER = AnimationState.StateKey.get("firstColumnHeader");
/*  188 */   public static final AnimationState.StateKey STATE_LAST_COLUMNHEADER = AnimationState.StateKey.get("lastColumnHeader");
/*  189 */   public static final AnimationState.StateKey STATE_ROW_SELECTED = AnimationState.StateKey.get("rowSelected");
/*  190 */   public static final AnimationState.StateKey STATE_ROW_HOVER = AnimationState.StateKey.get("rowHover");
/*  191 */   public static final AnimationState.StateKey STATE_ROW_DROPTARGET = AnimationState.StateKey.get("rowDropTarget");
/*  192 */   public static final AnimationState.StateKey STATE_ROW_ODD = AnimationState.StateKey.get("rowOdd");
/*  193 */   public static final AnimationState.StateKey STATE_LEAD_ROW = AnimationState.StateKey.get("leadRow");
/*  194 */   public static final AnimationState.StateKey STATE_SELECTED = AnimationState.StateKey.get("selected");
/*  195 */   public static final AnimationState.StateKey STATE_SORT_ASCENDING = AnimationState.StateKey.get("sortAscending");
/*  196 */   public static final AnimationState.StateKey STATE_SORT_DESCENDING = AnimationState.StateKey.get("sortDescending");
/*      */   
/*      */   private final StringCellRenderer stringCellRenderer;
/*      */   
/*      */   private final RemoveCellWidgets removeCellWidgetsFunction;
/*      */   
/*      */   private final InsertCellWidgets insertCellWidgetsFunction;
/*      */   
/*      */   private final CellWidgetContainer cellWidgetContainer;
/*      */   protected final TypeMapping<CellRenderer> cellRenderers;
/*      */   protected final SparseGrid widgetGrid;
/*      */   protected final ColumnSizeSequence columnModel;
/*      */   protected TableColumnHeaderModel columnHeaderModel;
/*      */   protected SizeSequence rowModel;
/*      */   protected boolean hasCellWidgetCreators;
/*      */   protected ColumnHeader[] columnHeaders;
/*      */   protected CellRenderer[] columnDefaultCellRenderer;
/*      */   protected TableSelectionManager selectionManager;
/*      */   protected KeyboardSearchHandler keyboardSearchHandler;
/*      */   protected DragListener dragListener;
/*      */   protected Callback[] callbacks;
/*      */   protected Image imageColumnDivider;
/*      */   protected Image imageRowBackground;
/*      */   protected Image imageRowOverlay;
/*      */   protected Image imageRowDropMarker;
/*      */   protected ThemeInfo tableBaseThemeInfo;
/*      */   protected int columnHeaderHeight;
/*      */   protected int columnDividerDragableDistance;
/*      */   protected MouseCursor columnResizeCursor;
/*      */   protected MouseCursor normalCursor;
/*      */   protected MouseCursor dragNotPossibleCursor;
/*      */   protected boolean ensureColumnHeaderMinWidth;
/*      */   protected int numRows;
/*      */   protected int numColumns;
/*  230 */   protected int rowHeight = 32;
/*  231 */   protected int defaultColumnWidth = 256;
/*      */   
/*      */   protected boolean autoSizeAllRows;
/*      */   
/*      */   protected boolean updateAllCellWidgets;
/*      */   
/*      */   protected boolean updateAllColumnWidth;
/*      */   protected int scrollPosX;
/*      */   protected int scrollPosY;
/*      */   protected int firstVisibleRow;
/*      */   protected int firstVisibleColumn;
/*      */   protected int lastVisibleRow;
/*      */   protected int lastVisibleColumn;
/*      */   protected boolean firstRowPartialVisible;
/*      */   protected boolean lastRowPartialVisible;
/*  246 */   protected int dropMarkerRow = -1;
/*      */   
/*      */   protected boolean dropMarkerBeforeRow;
/*      */   
/*      */   protected static final int LAST_MOUSE_Y_OUTSIDE = -2147483648;
/*  251 */   protected int lastMouseY = Integer.MIN_VALUE;
/*  252 */   protected int lastMouseRow = -1;
/*  253 */   protected int lastMouseColumn = -1; protected static final int DRAG_INACTIVE = 0; protected static final int DRAG_COLUMN_HEADER = 1; protected static final int DRAG_USER = 2; protected static final int DRAG_IGNORE = 3; protected int dragActive; protected int dragColumn; protected int dragStartX; protected int dragStartColWidth; protected int dragStartSumWidth;
/*      */   
/*      */   protected TableBase() {
/*  256 */     this.cellRenderers = new TypeMapping();
/*  257 */     this.stringCellRenderer = new StringCellRenderer();
/*  258 */     this.widgetGrid = new SparseGrid(32);
/*  259 */     this.removeCellWidgetsFunction = new RemoveCellWidgets();
/*  260 */     this.insertCellWidgetsFunction = new InsertCellWidgets();
/*  261 */     this.columnModel = new ColumnSizeSequence();
/*  262 */     this.columnDefaultCellRenderer = new CellRenderer[8];
/*  263 */     this.cellWidgetContainer = new CellWidgetContainer();
/*      */     
/*  265 */     insertChild(this.cellWidgetContainer, 0);
/*  266 */     setCanAcceptKeyboardFocus(true);
/*      */   }
/*      */   
/*      */   public TableSelectionManager getSelectionManager() {
/*  270 */     return this.selectionManager;
/*      */   }
/*      */   
/*      */   public void setSelectionManager(TableSelectionManager selectionManager) {
/*  274 */     if (this.selectionManager != selectionManager) {
/*  275 */       if (this.selectionManager != null) {
/*  276 */         this.selectionManager.setAssociatedTable(null);
/*      */       }
/*  278 */       this.selectionManager = selectionManager;
/*  279 */       if (this.selectionManager != null) {
/*  280 */         this.selectionManager.setAssociatedTable(this);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDefaultSelectionManager() {
/*  292 */     setSelectionManager(new TableRowSelectionManager());
/*      */   }
/*      */   
/*      */   public KeyboardSearchHandler getKeyboardSearchHandler() {
/*  296 */     return this.keyboardSearchHandler;
/*      */   }
/*      */   
/*      */   public void setKeyboardSearchHandler(KeyboardSearchHandler keyboardSearchHandler) {
/*  300 */     this.keyboardSearchHandler = keyboardSearchHandler;
/*      */   }
/*      */   
/*      */   public DragListener getDragListener() {
/*  304 */     return this.dragListener;
/*      */   }
/*      */   
/*      */   public void setDragListener(DragListener dragListener) {
/*  308 */     cancelDragging();
/*  309 */     this.dragListener = dragListener;
/*      */   }
/*      */   
/*      */   public boolean isDropMarkerBeforeRow() {
/*  313 */     return this.dropMarkerBeforeRow;
/*      */   }
/*      */   
/*      */   public int getDropMarkerRow() {
/*  317 */     return this.dropMarkerRow;
/*      */   }
/*      */   
/*      */   public void setDropMarker(int row, boolean beforeRow) {
/*  321 */     if (row < 0 || row > this.numRows) {
/*  322 */       throw new IllegalArgumentException("row");
/*      */     }
/*  324 */     if (row == this.numRows && !beforeRow) {
/*  325 */       throw new IllegalArgumentException("row");
/*      */     }
/*  327 */     this.dropMarkerRow = row;
/*  328 */     this.dropMarkerBeforeRow = beforeRow;
/*      */   }
/*      */   
/*      */   public boolean setDropMarker(Event evt) {
/*  332 */     int mouseY = evt.getMouseY();
/*  333 */     if (isMouseInside(evt) && !isMouseInColumnHeader(mouseY)) {
/*  334 */       mouseY -= getOffsetY();
/*  335 */       int row = getRowFromPosition(mouseY);
/*  336 */       if (row >= 0 && row < this.numRows) {
/*  337 */         int rowStart = getRowStartPosition(row);
/*  338 */         int rowEnd = getRowEndPosition(row);
/*  339 */         int margin = (rowEnd - rowStart + 2) / 4;
/*  340 */         if (mouseY - rowStart < margin) {
/*  341 */           setDropMarker(row, true);
/*  342 */         } else if (rowEnd - mouseY < margin) {
/*  343 */           setDropMarker(row + 1, true);
/*      */         } else {
/*  345 */           setDropMarker(row, false);
/*      */         } 
/*  347 */         return true;
/*  348 */       }  if (row == this.numRows) {
/*  349 */         setDropMarker(row, true);
/*  350 */         return true;
/*      */       } 
/*      */     } 
/*  353 */     return false;
/*      */   }
/*      */   
/*      */   public void clearDropMarker() {
/*  357 */     this.dropMarkerRow = -1;
/*      */   }
/*      */   
/*      */   public void addCallback(Callback callback) {
/*  361 */     this.callbacks = (Callback[])CallbackSupport.addCallbackToList((Object[])this.callbacks, callback, Callback.class);
/*      */   }
/*      */   
/*      */   public void removeCallback(Callback callback) {
/*  365 */     this.callbacks = (Callback[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, callback);
/*      */   }
/*      */   
/*      */   public boolean isVariableRowHeight() {
/*  369 */     return (this.rowModel != null);
/*      */   }
/*      */   
/*      */   public void setVaribleRowHeight(boolean varibleRowHeight) {
/*  373 */     if (varibleRowHeight && this.rowModel == null) {
/*  374 */       this.rowModel = new RowSizeSequence(this.numRows);
/*  375 */       this.autoSizeAllRows = true;
/*  376 */       invalidateLayout();
/*  377 */     } else if (!varibleRowHeight) {
/*  378 */       this.rowModel = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getNumRows() {
/*  383 */     return this.numRows;
/*      */   }
/*      */   
/*      */   public int getNumColumns() {
/*  387 */     return this.numColumns;
/*      */   }
/*      */   
/*      */   public int getRowFromPosition(int y) {
/*  391 */     if (y >= 0) {
/*  392 */       if (this.rowModel != null) {
/*  393 */         return this.rowModel.getIndex(y);
/*      */       }
/*  395 */       return Math.min(this.numRows - 1, y / this.rowHeight);
/*      */     } 
/*  397 */     return -1;
/*      */   }
/*      */   
/*      */   public int getRowStartPosition(int row) {
/*  401 */     checkRowIndex(row);
/*  402 */     if (this.rowModel != null) {
/*  403 */       return this.rowModel.getPosition(row);
/*      */     }
/*  405 */     return row * this.rowHeight;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getRowHeight(int row) {
/*  410 */     checkRowIndex(row);
/*  411 */     if (this.rowModel != null) {
/*  412 */       return this.rowModel.getSize(row);
/*      */     }
/*  414 */     return this.rowHeight;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getRowEndPosition(int row) {
/*  419 */     checkRowIndex(row);
/*  420 */     if (this.rowModel != null) {
/*  421 */       return this.rowModel.getPosition(row + 1);
/*      */     }
/*  423 */     return (row + 1) * this.rowHeight;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getColumnFromPosition(int x) {
/*  428 */     if (x >= 0) {
/*  429 */       int column = this.columnModel.getIndex(x);
/*  430 */       return column;
/*      */     } 
/*  432 */     return -1;
/*      */   }
/*      */   
/*      */   public int getColumnStartPosition(int column) {
/*  436 */     checkColumnIndex(column);
/*  437 */     return this.columnModel.getPosition(column);
/*      */   }
/*      */   
/*      */   public int getColumnWidth(int column) {
/*  441 */     checkColumnIndex(column);
/*  442 */     return this.columnModel.getSize(column);
/*      */   }
/*      */   
/*      */   public int getColumnEndPosition(int column) {
/*  446 */     checkColumnIndex(column);
/*  447 */     return this.columnModel.getPosition(column + 1);
/*      */   }
/*      */   
/*      */   public void setColumnWidth(int column, int width) {
/*  451 */     checkColumnIndex(column);
/*  452 */     this.columnHeaders[column].setColumnWidth(width);
/*  453 */     if (this.columnModel.update(column)) {
/*  454 */       invalidateLayout();
/*      */     }
/*      */   }
/*      */   
/*      */   public AnimationState getColumnHeaderAnimationState(int column) {
/*  459 */     checkColumnIndex(column);
/*  460 */     return this.columnHeaders[column].getAnimationState();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColumnSortOrderAnimationState(int sortColumn, SortOrder sortOrder) {
/*  469 */     for (int column = 0; column < this.numColumns; column++) {
/*  470 */       AnimationState animState = this.columnHeaders[column].getAnimationState();
/*  471 */       animState.setAnimationState(STATE_SORT_ASCENDING, (column == sortColumn && sortOrder == SortOrder.ASCENDING));
/*  472 */       animState.setAnimationState(STATE_SORT_DESCENDING, (column == sortColumn && sortOrder == SortOrder.DESCENDING));
/*      */     } 
/*      */   }
/*      */   
/*      */   public void scrollToRow(int row) {
/*  477 */     ScrollPane scrollPane = ScrollPane.getContainingScrollPane(this);
/*  478 */     if (scrollPane != null && this.numRows > 0) {
/*  479 */       scrollPane.validateLayout();
/*  480 */       int rowStart = getRowStartPosition(row);
/*  481 */       int rowEnd = getRowEndPosition(row);
/*  482 */       int height = rowEnd - rowStart;
/*  483 */       scrollPane.scrollToAreaY(rowStart, height, height / 2);
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getNumVisibleRows() {
/*  488 */     int rows = this.lastVisibleRow - this.firstVisibleRow;
/*  489 */     if (!this.lastRowPartialVisible) {
/*  490 */       rows++;
/*      */     }
/*  492 */     return rows;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMinHeight() {
/*  497 */     return Math.max(super.getMinHeight(), this.columnHeaderHeight);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPreferredInnerWidth() {
/*  502 */     if (getInnerWidth() == 0) {
/*  503 */       return this.columnModel.computePreferredWidth();
/*      */     }
/*  505 */     if (this.updateAllColumnWidth) {
/*  506 */       updateAllColumnWidth();
/*      */     }
/*  508 */     return (this.numColumns > 0) ? getColumnEndPosition(this.numColumns - 1) : 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPreferredInnerHeight() {
/*  513 */     if (this.autoSizeAllRows) {
/*  514 */       autoSizeAllRows();
/*      */     }
/*  516 */     return this.columnHeaderHeight + 1 + ((this.numRows > 0) ? getRowEndPosition(this.numRows - 1) : 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void registerCellRenderer(Class<?> dataClass, CellRenderer cellRenderer) {
/*  521 */     if (dataClass == null) {
/*  522 */       throw new NullPointerException("dataClass");
/*      */     }
/*  524 */     this.cellRenderers.put(dataClass, cellRenderer);
/*      */     
/*  526 */     if (cellRenderer instanceof CellWidgetCreator) {
/*  527 */       this.hasCellWidgetCreators = true;
/*      */     }
/*      */ 
/*      */     
/*  531 */     if (this.tableBaseThemeInfo != null) {
/*  532 */       applyCellRendererTheme(cellRenderer);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setScrollPosition(int scrollPosX, int scrollPosY) {
/*  537 */     if (this.scrollPosX != scrollPosX || this.scrollPosY != scrollPosY) {
/*  538 */       this.scrollPosX = scrollPosX;
/*  539 */       this.scrollPosY = scrollPosY;
/*  540 */       invalidateLayoutLocally();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void adjustScrollPosition(int row) {
/*  545 */     checkRowIndex(row);
/*  546 */     ScrollPane scrollPane = ScrollPane.getContainingScrollPane(this);
/*  547 */     int numVisibleRows = getNumVisibleRows();
/*  548 */     if (numVisibleRows >= 1 && scrollPane != null) {
/*  549 */       if (row < this.firstVisibleRow || (row == this.firstVisibleRow && this.firstRowPartialVisible)) {
/*  550 */         int pos = getRowStartPosition(row);
/*  551 */         scrollPane.setScrollPositionY(pos);
/*  552 */       } else if (row > this.lastVisibleRow || (row == this.lastVisibleRow && this.lastRowPartialVisible)) {
/*  553 */         int innerHeight = Math.max(0, getInnerHeight() - this.columnHeaderHeight);
/*  554 */         int pos = getRowEndPosition(row);
/*  555 */         pos = Math.max(0, pos - innerHeight);
/*  556 */         scrollPane.setScrollPositionY(pos);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public int getAutoScrollDirection(Event evt, int autoScrollArea) {
/*  562 */     int areaY = getInnerY() + this.columnHeaderHeight;
/*  563 */     int areaHeight = getInnerHeight() - this.columnHeaderHeight;
/*  564 */     int mouseY = evt.getMouseY();
/*  565 */     if (mouseY >= areaY && mouseY < areaY + areaHeight) {
/*  566 */       mouseY -= areaY;
/*  567 */       if (mouseY <= autoScrollArea || areaHeight - mouseY <= autoScrollArea) {
/*      */         
/*  569 */         if (mouseY < areaHeight / 2) {
/*  570 */           return -1;
/*      */         }
/*  572 */         return 1;
/*      */       } 
/*      */     } 
/*      */     
/*  576 */     return 0;
/*      */   }
/*      */   
/*      */   public int getPageSizeX(int availableWidth) {
/*  580 */     return availableWidth;
/*      */   }
/*      */   
/*      */   public int getPageSizeY(int availableHeight) {
/*  584 */     return availableHeight - this.columnHeaderHeight;
/*      */   }
/*      */   
/*      */   public boolean isFixedWidthMode() {
/*  588 */     ScrollPane scrollPane = ScrollPane.getContainingScrollPane(this);
/*  589 */     if (scrollPane != null && 
/*  590 */       scrollPane.getFixed() != ScrollPane.Fixed.HORIZONTAL) {
/*  591 */       return false;
/*      */     }
/*      */     
/*  594 */     return true;
/*      */   }
/*      */   
/*      */   protected final void checkRowIndex(int row) {
/*  598 */     if (row < 0 || row >= this.numRows) {
/*  599 */       throw new IndexOutOfBoundsException("row");
/*      */     }
/*      */   }
/*      */   
/*      */   protected final void checkColumnIndex(int column) {
/*  604 */     if (column < 0 || column >= this.numColumns) {
/*  605 */       throw new IndexOutOfBoundsException("column");
/*      */     }
/*      */   }
/*      */   
/*      */   protected final void checkRowRange(int idx, int count) {
/*  610 */     if (idx < 0 || count < 0 || count > this.numRows || idx > this.numRows - count) {
/*  611 */       throw new IllegalArgumentException("row");
/*      */     }
/*      */   }
/*      */   
/*      */   protected final void checkColumnRange(int idx, int count) {
/*  616 */     if (idx < 0 || count < 0 || count > this.numColumns || idx > this.numColumns - count) {
/*  617 */       throw new IllegalArgumentException("column");
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyTheme(ThemeInfo themeInfo) {
/*  623 */     super.applyTheme(themeInfo);
/*  624 */     applyThemeTableBase(themeInfo);
/*  625 */     updateAll();
/*      */   }
/*      */   
/*      */   protected void applyThemeTableBase(ThemeInfo themeInfo) {
/*  629 */     this.tableBaseThemeInfo = themeInfo;
/*  630 */     this.imageColumnDivider = themeInfo.getImage("columnDivider");
/*  631 */     this.imageRowBackground = themeInfo.getImage("row.background");
/*  632 */     this.imageRowOverlay = themeInfo.getImage("row.overlay");
/*  633 */     this.imageRowDropMarker = themeInfo.getImage("row.dropmarker");
/*  634 */     this.rowHeight = themeInfo.getParameter("rowHeight", 32);
/*  635 */     this.defaultColumnWidth = themeInfo.getParameter("columnHeaderWidth", 256);
/*  636 */     this.columnHeaderHeight = themeInfo.getParameter("columnHeaderHeight", 10);
/*  637 */     this.columnDividerDragableDistance = themeInfo.getParameter("columnDividerDragableDistance", 3);
/*  638 */     this.ensureColumnHeaderMinWidth = themeInfo.getParameter("ensureColumnHeaderMinWidth", false);
/*      */     
/*  640 */     for (CellRenderer cellRenderer : this.cellRenderers.getUniqueValues()) {
/*  641 */       applyCellRendererTheme(cellRenderer);
/*      */     }
/*  643 */     applyCellRendererTheme(this.stringCellRenderer);
/*  644 */     this.updateAllColumnWidth = true;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyThemeMouseCursor(ThemeInfo themeInfo) {
/*  649 */     this.columnResizeCursor = themeInfo.getMouseCursor("columnResizeCursor");
/*  650 */     this.normalCursor = themeInfo.getMouseCursor("mouseCursor");
/*  651 */     this.dragNotPossibleCursor = themeInfo.getMouseCursor("dragNotPossibleCursor");
/*      */   }
/*      */   
/*      */   protected void applyCellRendererTheme(CellRenderer cellRenderer) {
/*  655 */     String childThemeName = cellRenderer.getTheme();
/*  656 */     assert !isAbsoluteTheme(childThemeName);
/*  657 */     ThemeInfo childTheme = this.tableBaseThemeInfo.getChildTheme(childThemeName);
/*  658 */     if (childTheme != null) {
/*  659 */       cellRenderer.applyTheme(childTheme);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeAllChildren() {
/*  665 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void childAdded(Widget child) {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected void childRemoved(Widget exChild) {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getOffsetX() {
/*  679 */     return getInnerX() - this.scrollPosX;
/*      */   }
/*      */   
/*      */   protected int getOffsetY() {
/*  683 */     return getInnerY() - this.scrollPosY + this.columnHeaderHeight;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void positionChanged() {
/*  688 */     super.positionChanged();
/*  689 */     if (this.keyboardSearchHandler != null) {
/*  690 */       this.keyboardSearchHandler.updateInfoWindowPosition();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void sizeChanged() {
/*  696 */     super.sizeChanged();
/*  697 */     if (isFixedWidthMode()) {
/*  698 */       this.updateAllColumnWidth = true;
/*      */     }
/*  700 */     if (this.keyboardSearchHandler != null) {
/*  701 */       this.keyboardSearchHandler.updateInfoWindowPosition();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected Object getTooltipContentAt(int mouseX, int mouseY) {
/*  708 */     if (this.lastMouseRow >= 0 && this.lastMouseRow < getNumRows() && this.lastMouseColumn >= 0 && this.lastMouseColumn < getNumColumns()) {
/*      */       
/*  710 */       Object tooltip = getTooltipContentFromRow(this.lastMouseRow, this.lastMouseColumn);
/*  711 */       if (tooltip != null) {
/*  712 */         return tooltip;
/*      */       }
/*      */     } 
/*  715 */     return super.getTooltipContentAt(mouseX, mouseY);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void layout() {
/*  720 */     int innerWidth = getInnerWidth();
/*  721 */     int innerHeight = Math.max(0, getInnerHeight() - this.columnHeaderHeight);
/*      */     
/*  723 */     this.cellWidgetContainer.setPosition(getInnerX(), getInnerY() + this.columnHeaderHeight);
/*  724 */     this.cellWidgetContainer.setSize(innerWidth, innerHeight);
/*      */     
/*  726 */     if (this.updateAllColumnWidth) {
/*  727 */       updateAllColumnWidth();
/*      */     }
/*  729 */     if (this.autoSizeAllRows) {
/*  730 */       autoSizeAllRows();
/*      */     }
/*  732 */     if (this.updateAllCellWidgets) {
/*  733 */       updateAllCellWidgets();
/*      */     }
/*      */     
/*  736 */     int scrollEndX = this.scrollPosX + innerWidth;
/*  737 */     int scrollEndY = this.scrollPosY + innerHeight;
/*      */     
/*  739 */     int startRow = Math.min(this.numRows - 1, Math.max(0, getRowFromPosition(this.scrollPosY)));
/*  740 */     int startColumn = Math.min(this.numColumns - 1, Math.max(0, getColumnFromPosition(this.scrollPosX)));
/*  741 */     int endRow = Math.min(this.numRows - 1, Math.max(startRow, getRowFromPosition(scrollEndY)));
/*  742 */     int endColumn = Math.min(this.numColumns - 1, Math.max(startColumn, getColumnFromPosition(scrollEndX)));
/*      */     
/*  744 */     if (this.numRows > 0) {
/*  745 */       this.firstRowPartialVisible = (getRowStartPosition(startRow) < this.scrollPosY);
/*  746 */       this.lastRowPartialVisible = (getRowEndPosition(endRow) > scrollEndY);
/*      */     } else {
/*  748 */       this.firstRowPartialVisible = false;
/*  749 */       this.lastRowPartialVisible = false;
/*      */     } 
/*      */     
/*  752 */     if (!this.widgetGrid.isEmpty()) {
/*  753 */       if (startRow > this.firstVisibleRow) {
/*  754 */         this.widgetGrid.iterate(this.firstVisibleRow, 0, startRow - 1, this.numColumns, this.removeCellWidgetsFunction);
/*      */       }
/*  756 */       if (endRow < this.lastVisibleRow) {
/*  757 */         this.widgetGrid.iterate(endRow + 1, 0, this.lastVisibleRow, this.numColumns, this.removeCellWidgetsFunction);
/*      */       }
/*      */       
/*  760 */       this.widgetGrid.iterate(startRow, 0, endRow, this.numColumns, this.insertCellWidgetsFunction);
/*      */     } 
/*      */     
/*  763 */     this.firstVisibleRow = startRow;
/*  764 */     this.firstVisibleColumn = startColumn;
/*  765 */     this.lastVisibleRow = endRow;
/*  766 */     this.lastVisibleColumn = endColumn;
/*      */     
/*  768 */     if (this.numColumns > 0) {
/*  769 */       int offsetX = getOffsetX();
/*  770 */       int colStartPos = getColumnStartPosition(0);
/*  771 */       for (int i = 0; i < this.numColumns; i++) {
/*  772 */         int colEndPos = getColumnEndPosition(i);
/*  773 */         Widget w = this.columnHeaders[i];
/*  774 */         if (w != null) {
/*  775 */           assert w.getParent() == this;
/*  776 */           w.setPosition(offsetX + colStartPos + this.columnDividerDragableDistance, getInnerY());
/*      */           
/*  778 */           w.setSize(Math.max(0, colEndPos - colStartPos - 2 * this.columnDividerDragableDistance), this.columnHeaderHeight);
/*      */           
/*  780 */           w.setVisible((this.columnHeaderHeight > 0));
/*  781 */           AnimationState animationState = w.getAnimationState();
/*  782 */           animationState.setAnimationState(STATE_FIRST_COLUMNHEADER, (i == 0));
/*  783 */           animationState.setAnimationState(STATE_LAST_COLUMNHEADER, (i == this.numColumns - 1));
/*      */         } 
/*  785 */         colStartPos = colEndPos;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void paintWidget(GUI gui) {
/*  792 */     if (this.firstVisibleRow < 0 || this.firstVisibleRow >= this.numRows) {
/*      */       return;
/*      */     }
/*      */     
/*  796 */     int innerX = getInnerX();
/*  797 */     int innerY = getInnerY() + this.columnHeaderHeight;
/*  798 */     int innerWidth = getInnerWidth();
/*  799 */     int innerHeight = getInnerHeight() - this.columnHeaderHeight;
/*  800 */     int offsetX = getOffsetX();
/*  801 */     int offsetY = getOffsetY();
/*  802 */     Renderer renderer = gui.getRenderer();
/*      */     
/*  804 */     renderer.clipEnter(innerX, innerY, innerWidth, innerHeight); try {
/*      */       int leadRow; boolean isCellSelection;
/*  806 */       AnimationState animState = getAnimationState();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  811 */       if (this.selectionManager != null) {
/*  812 */         leadRow = this.selectionManager.getLeadRow();
/*  813 */         int leadColumn = this.selectionManager.getLeadColumn();
/*  814 */         isCellSelection = (this.selectionManager.getSelectionGranularity() == TableSelectionManager.SelectionGranularity.CELLS);
/*      */       } else {
/*      */         
/*  817 */         leadRow = -1;
/*  818 */         int leadColumn = -1;
/*  819 */         isCellSelection = false;
/*      */       } 
/*      */       
/*  822 */       if (this.imageRowBackground != null) {
/*  823 */         paintRowImage(this.imageRowBackground, leadRow);
/*      */       }
/*      */       
/*  826 */       if (this.imageColumnDivider != null) {
/*  827 */         animState.setAnimationState(STATE_ROW_SELECTED, false);
/*  828 */         for (int col = this.firstVisibleColumn; col <= this.lastVisibleColumn; col++) {
/*  829 */           int colEndPos = getColumnEndPosition(col);
/*  830 */           int curX = offsetX + colEndPos;
/*  831 */           this.imageColumnDivider.draw(animState, curX, innerY, 1, innerHeight);
/*      */         } 
/*      */       } 
/*      */       
/*  835 */       int rowStartPos = getRowStartPosition(this.firstVisibleRow);
/*  836 */       for (int row = this.firstVisibleRow; row <= this.lastVisibleRow; row++) {
/*  837 */         int rowEndPos = getRowEndPosition(row);
/*  838 */         int curRowHeight = rowEndPos - rowStartPos;
/*  839 */         int curY = offsetY + rowStartPos;
/*  840 */         TreeTableNode rowNode = getNodeFromRow(row);
/*  841 */         boolean isRowSelected = (!isCellSelection && isRowSelected(row));
/*      */         
/*  843 */         int colStartPos = getColumnStartPosition(this.firstVisibleColumn);
/*  844 */         for (int col = this.firstVisibleColumn; col <= this.lastVisibleColumn; ) {
/*  845 */           int colEndPos = getColumnEndPosition(col);
/*  846 */           CellRenderer cellRenderer = getCellRenderer(row, col, rowNode);
/*  847 */           boolean isCellSelected = (isRowSelected || isCellSelected(row, col));
/*      */           
/*  849 */           int curX = offsetX + colStartPos;
/*  850 */           int colSpan = 1;
/*      */           
/*  852 */           if (cellRenderer != null) {
/*  853 */             colSpan = cellRenderer.getColumnSpan();
/*  854 */             if (colSpan > 1) {
/*  855 */               colEndPos = getColumnEndPosition(Math.max(this.numColumns - 1, col + colSpan - 1));
/*      */             }
/*      */             
/*  858 */             Widget cellRendererWidget = cellRenderer.getCellRenderWidget(curX, curY, colEndPos - colStartPos, curRowHeight, isCellSelected);
/*      */ 
/*      */             
/*  861 */             if (cellRendererWidget != null) {
/*  862 */               if (cellRendererWidget.getParent() != this) {
/*  863 */                 insertCellRenderer(cellRendererWidget);
/*      */               }
/*  865 */               paintChild(gui, cellRendererWidget);
/*      */             } 
/*      */           } 
/*      */           
/*  869 */           col += Math.max(1, colSpan);
/*  870 */           colStartPos = colEndPos;
/*      */         } 
/*      */         
/*  873 */         rowStartPos = rowEndPos;
/*      */       } 
/*      */       
/*  876 */       if (this.imageRowOverlay != null) {
/*  877 */         paintRowImage(this.imageRowOverlay, leadRow);
/*      */       }
/*      */       
/*  880 */       if (this.dropMarkerRow >= 0 && this.dropMarkerBeforeRow && this.imageRowDropMarker != null) {
/*  881 */         int y = (this.rowModel != null) ? this.rowModel.getPosition(this.dropMarkerRow) : (this.dropMarkerRow * this.rowHeight);
/*  882 */         this.imageRowDropMarker.draw(animState, getOffsetX(), getOffsetY() + y, this.columnModel.getEndPosition(), 1);
/*      */       } 
/*      */     } finally {
/*  885 */       renderer.clipLeave();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void paintRowImage(Image img, int leadRow) {
/*  890 */     AnimationState animState = getAnimationState();
/*  891 */     int x = getOffsetX();
/*  892 */     int width = this.columnModel.getEndPosition();
/*  893 */     int offsetY = getOffsetY();
/*      */     
/*  895 */     int rowStartPos = getRowStartPosition(this.firstVisibleRow);
/*  896 */     for (int row = this.firstVisibleRow; row <= this.lastVisibleRow; row++) {
/*  897 */       int rowEndPos = getRowEndPosition(row);
/*  898 */       int curRowHeight = rowEndPos - rowStartPos;
/*  899 */       int curY = offsetY + rowStartPos;
/*      */       
/*  901 */       animState.setAnimationState(STATE_ROW_SELECTED, isRowSelected(row));
/*  902 */       animState.setAnimationState(STATE_ROW_HOVER, (this.dragActive == 0 && this.lastMouseY >= curY && this.lastMouseY < curY + curRowHeight));
/*      */       
/*  904 */       animState.setAnimationState(STATE_LEAD_ROW, (row == leadRow));
/*  905 */       animState.setAnimationState(STATE_ROW_DROPTARGET, (!this.dropMarkerBeforeRow && row == this.dropMarkerRow));
/*  906 */       animState.setAnimationState(STATE_ROW_ODD, ((row & 0x1) == 1));
/*  907 */       img.draw(animState, x, curY, width, curRowHeight);
/*      */       
/*  909 */       rowStartPos = rowEndPos;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void insertCellRenderer(Widget widget) {
/*  914 */     int posX = widget.getX();
/*  915 */     int posY = widget.getY();
/*  916 */     widget.setVisible(false);
/*  917 */     insertChild(widget, getNumChildren());
/*  918 */     widget.setPosition(posX, posY);
/*      */   }
/*      */   protected abstract TreeTableNode getNodeFromRow(int paramInt);
/*      */   protected abstract Object getCellData(int paramInt1, int paramInt2, TreeTableNode paramTreeTableNode);
/*      */   
/*      */   protected abstract Object getTooltipContentFromRow(int paramInt1, int paramInt2);
/*      */   
/*      */   protected boolean isRowSelected(int row) {
/*  926 */     if (this.selectionManager != null) {
/*  927 */       return this.selectionManager.isRowSelected(row);
/*      */     }
/*  929 */     return false;
/*      */   }
/*      */   
/*      */   protected boolean isCellSelected(int row, int column) {
/*  933 */     if (this.selectionManager != null) {
/*  934 */       return this.selectionManager.isCellSelected(row, column);
/*      */     }
/*  936 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColumnDefaultCellRenderer(int column, CellRenderer cellRenderer) {
/*  947 */     if (column >= this.columnDefaultCellRenderer.length) {
/*  948 */       CellRenderer[] tmp = new CellRenderer[Math.max(column + 1, this.numColumns)];
/*  949 */       System.arraycopy(this.columnDefaultCellRenderer, 0, tmp, 0, this.columnDefaultCellRenderer.length);
/*  950 */       this.columnDefaultCellRenderer = tmp;
/*      */     } 
/*      */     
/*  953 */     this.columnDefaultCellRenderer[column] = cellRenderer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CellRenderer getColumnDefaultCellRenderer(int column) {
/*  962 */     if (column < this.columnDefaultCellRenderer.length) {
/*  963 */       return this.columnDefaultCellRenderer[column];
/*      */     }
/*  965 */     return null;
/*      */   }
/*      */   
/*      */   protected CellRenderer getCellRendererNoDefault(Object data) {
/*  969 */     Class<? extends Object> dataClass = (Class)data.getClass();
/*  970 */     return (CellRenderer)this.cellRenderers.get(dataClass);
/*      */   }
/*      */   
/*      */   protected CellRenderer getDefaultCellRenderer(int col) {
/*  974 */     CellRenderer cellRenderer = getColumnDefaultCellRenderer(col);
/*  975 */     if (cellRenderer == null) {
/*  976 */       cellRenderer = this.stringCellRenderer;
/*      */     }
/*  978 */     return cellRenderer;
/*      */   }
/*      */   
/*      */   protected CellRenderer getCellRenderer(Object data, int col) {
/*  982 */     CellRenderer cellRenderer = getCellRendererNoDefault(data);
/*  983 */     if (cellRenderer == null) {
/*  984 */       cellRenderer = getDefaultCellRenderer(col);
/*      */     }
/*  986 */     return cellRenderer;
/*      */   }
/*      */   
/*      */   protected CellRenderer getCellRenderer(int row, int col, TreeTableNode node) {
/*  990 */     Object data = getCellData(row, col, node);
/*  991 */     if (data != null) {
/*  992 */       CellRenderer cellRenderer = getCellRenderer(data, col);
/*  993 */       cellRenderer.setCellData(row, col, data);
/*  994 */       return cellRenderer;
/*      */     } 
/*  996 */     return null;
/*      */   }
/*      */   
/*      */   protected int computeRowHeight(int row) {
/* 1000 */     TreeTableNode rowNode = getNodeFromRow(row);
/* 1001 */     int height = 0;
/* 1002 */     for (int column = 0; column < this.numColumns; column++) {
/* 1003 */       CellRenderer cellRenderer = getCellRenderer(row, column, rowNode);
/* 1004 */       if (cellRenderer != null) {
/* 1005 */         height = Math.max(height, cellRenderer.getPreferredHeight());
/* 1006 */         column += Math.max(cellRenderer.getColumnSpan() - 1, 0);
/*      */       } 
/*      */     } 
/* 1009 */     return height;
/*      */   }
/*      */   
/*      */   protected int clampColumnWidth(int width) {
/* 1013 */     return Math.max(2 * this.columnDividerDragableDistance + 1, width);
/*      */   }
/*      */   
/*      */   protected int computePreferredColumnWidth(int index) {
/* 1017 */     return clampColumnWidth(this.columnHeaders[index].getPreferredWidth());
/*      */   }
/*      */   
/*      */   protected boolean autoSizeRow(int row) {
/* 1021 */     int height = computeRowHeight(row);
/* 1022 */     return this.rowModel.setSize(row, height);
/*      */   }
/*      */   
/*      */   protected void autoSizeAllRows() {
/* 1026 */     if (this.rowModel != null) {
/* 1027 */       this.rowModel.initializeAll(this.numRows);
/*      */     }
/* 1029 */     this.autoSizeAllRows = false;
/*      */   }
/*      */   
/*      */   protected void removeCellWidget(Widget widget) {
/* 1033 */     int idx = this.cellWidgetContainer.getChildIndex(widget);
/* 1034 */     if (idx >= 0) {
/* 1035 */       this.cellWidgetContainer.removeChild(idx);
/*      */     }
/*      */   }
/*      */   
/*      */   void insertCellWidget(int row, int column, WidgetEntry widgetEntry) {
/* 1040 */     CellWidgetCreator cwc = (CellWidgetCreator)getCellRenderer(row, column, (TreeTableNode)null);
/* 1041 */     Widget widget = widgetEntry.widget;
/*      */     
/* 1043 */     if (widget != null) {
/* 1044 */       if (widget.getParent() != this.cellWidgetContainer) {
/* 1045 */         this.cellWidgetContainer.insertChild(widget, this.cellWidgetContainer.getNumChildren());
/*      */       }
/*      */       
/* 1048 */       int x = getColumnStartPosition(column);
/* 1049 */       int w = getColumnEndPosition(column) - x;
/* 1050 */       int y = getRowStartPosition(row);
/* 1051 */       int h = getRowEndPosition(row) - y;
/*      */       
/* 1053 */       cwc.positionWidget(widget, x + getOffsetX(), y + getOffsetY(), w, h);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void updateCellWidget(int row, int column) {
/* 1058 */     WidgetEntry we = (WidgetEntry)this.widgetGrid.get(row, column);
/* 1059 */     Widget oldWidget = (we != null) ? we.widget : null;
/* 1060 */     Widget newWidget = null;
/*      */     
/* 1062 */     TreeTableNode rowNode = getNodeFromRow(row);
/* 1063 */     CellRenderer cellRenderer = getCellRenderer(row, column, rowNode);
/* 1064 */     if (cellRenderer instanceof CellWidgetCreator) {
/* 1065 */       CellWidgetCreator cellWidgetCreator = (CellWidgetCreator)cellRenderer;
/* 1066 */       if (we != null && we.creator != cellWidgetCreator) {
/*      */ 
/*      */         
/* 1069 */         removeCellWidget(oldWidget);
/* 1070 */         oldWidget = null;
/*      */       } 
/* 1072 */       newWidget = cellWidgetCreator.updateWidget(oldWidget);
/* 1073 */       if (newWidget != null) {
/* 1074 */         if (we == null) {
/* 1075 */           we = new WidgetEntry();
/* 1076 */           this.widgetGrid.set(row, column, we);
/*      */         } 
/* 1078 */         we.widget = newWidget;
/* 1079 */         we.creator = cellWidgetCreator;
/*      */       } 
/*      */     } 
/*      */     
/* 1083 */     if (newWidget == null && we != null) {
/* 1084 */       this.widgetGrid.remove(row, column);
/*      */     }
/*      */     
/* 1087 */     if (oldWidget != null && newWidget != oldWidget) {
/* 1088 */       removeCellWidget(oldWidget);
/*      */     }
/*      */   }
/*      */   
/*      */   protected void updateAllCellWidgets() {
/* 1093 */     if (!this.widgetGrid.isEmpty() || this.hasCellWidgetCreators) {
/* 1094 */       for (int row = 0; row < this.numRows; row++) {
/* 1095 */         for (int col = 0; col < this.numColumns; col++) {
/* 1096 */           updateCellWidget(row, col);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/* 1101 */     this.updateAllCellWidgets = false;
/*      */   }
/*      */   
/*      */   protected void removeAllCellWidgets() {
/* 1105 */     this.cellWidgetContainer.removeAllChildren();
/*      */   }
/*      */   
/*      */   protected DialogLayout.Gap getColumnMPM(int column) {
/* 1109 */     if (this.tableBaseThemeInfo != null) {
/* 1110 */       ParameterMap columnWidthMap = this.tableBaseThemeInfo.getParameterMap("columnWidths");
/* 1111 */       Object obj = columnWidthMap.getParameterValue(Integer.toString(column), false);
/* 1112 */       if (obj instanceof DialogLayout.Gap) {
/* 1113 */         return (DialogLayout.Gap)obj;
/*      */       }
/* 1115 */       if (obj instanceof Integer) {
/* 1116 */         return new DialogLayout.Gap(((Integer)obj).intValue());
/*      */       }
/*      */     } 
/* 1119 */     return null;
/*      */   }
/*      */   
/*      */   protected ColumnHeader createColumnHeader(int column) {
/* 1123 */     ColumnHeader btn = new ColumnHeader();
/* 1124 */     btn.setTheme("columnHeader");
/* 1125 */     btn.setCanAcceptKeyboardFocus(false);
/* 1126 */     insertChild(btn, getNumChildren());
/* 1127 */     return btn;
/*      */   }
/*      */   
/*      */   protected void updateColumnHeader(int column) {
/* 1131 */     Button columnHeader = this.columnHeaders[column];
/* 1132 */     columnHeader.setText(this.columnHeaderModel.getColumnHeaderText(column));
/* 1133 */     AnimationState.StateKey[] states = this.columnHeaderModel.getColumnHeaderStates();
/* 1134 */     if (states.length > 0) {
/* 1135 */       AnimationState animationState = columnHeader.getAnimationState();
/* 1136 */       for (int i = 0; i < states.length; i++) {
/* 1137 */         animationState.setAnimationState(states[i], this.columnHeaderModel.getColumnHeaderState(column, i));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateColumnHeaderNumbers() {
/* 1144 */     for (int i = 0; i < this.columnHeaders.length; i++) {
/* 1145 */       (this.columnHeaders[i]).column = i;
/*      */     }
/*      */   }
/*      */   
/*      */   private void removeColumnHeaders(int column, int count) throws IndexOutOfBoundsException {
/* 1150 */     for (int i = 0; i < count; i++) {
/* 1151 */       int idx = getChildIndex(this.columnHeaders[column + i]);
/* 1152 */       if (idx >= 0) {
/* 1153 */         removeChild(idx);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean isMouseInColumnHeader(int y) {
/* 1159 */     y -= getInnerY();
/* 1160 */     return (y >= 0 && y < this.columnHeaderHeight);
/*      */   }
/*      */   
/*      */   protected int getColumnSeparatorUnderMouse(int x) {
/* 1164 */     x -= getOffsetX();
/* 1165 */     x += this.columnDividerDragableDistance;
/* 1166 */     int col = this.columnModel.getIndex(x);
/* 1167 */     int dist = x - this.columnModel.getPosition(col);
/* 1168 */     if (dist < 2 * this.columnDividerDragableDistance) {
/* 1169 */       return col - 1;
/*      */     }
/* 1171 */     return -1;
/*      */   }
/*      */   
/*      */   protected int getRowUnderMouse(int y) {
/* 1175 */     y -= getOffsetY();
/* 1176 */     int row = getRowFromPosition(y);
/* 1177 */     return row;
/*      */   }
/*      */   
/*      */   protected int getColumnUnderMouse(int x) {
/* 1181 */     x -= getOffsetX();
/* 1182 */     int col = this.columnModel.getIndex(x);
/* 1183 */     return col;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean handleEvent(Event evt) {
/* 1188 */     if (this.dragActive != 0) {
/* 1189 */       return handleDragEvent(evt);
/*      */     }
/*      */     
/* 1192 */     if (evt.isKeyEvent() && this.keyboardSearchHandler != null && this.keyboardSearchHandler.isActive() && this.keyboardSearchHandler.handleKeyEvent(evt))
/*      */     {
/*      */ 
/*      */       
/* 1196 */       return true;
/*      */     }
/*      */     
/* 1199 */     if (super.handleEvent(evt)) {
/* 1200 */       return true;
/*      */     }
/*      */     
/* 1203 */     if (evt.isMouseEvent()) {
/* 1204 */       return handleMouseEvent(evt);
/*      */     }
/*      */     
/* 1207 */     if (evt.isKeyEvent() && this.keyboardSearchHandler != null && this.keyboardSearchHandler.handleKeyEvent(evt))
/*      */     {
/*      */       
/* 1210 */       return true;
/*      */     }
/*      */     
/* 1213 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean handleKeyStrokeAction(String action, Event event) {
/* 1218 */     if (!super.handleKeyStrokeAction(action, event)) {
/* 1219 */       if (this.selectionManager == null) {
/* 1220 */         return false;
/*      */       }
/* 1222 */       if (!this.selectionManager.handleKeyStrokeAction(action, event)) {
/* 1223 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 1227 */     requestKeyboardFocus(null);
/* 1228 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void cancelDragging() {
/* 1243 */     if (this.dragActive == 2) {
/* 1244 */       if (this.dragListener != null) {
/* 1245 */         this.dragListener.dragCanceled();
/*      */       }
/* 1247 */       this.dragActive = 3;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected boolean handleDragEvent(Event evt) {
/* 1252 */     if (evt.isMouseEvent()) {
/* 1253 */       return handleMouseEvent(evt);
/*      */     }
/*      */     
/* 1256 */     if (evt.isKeyPressedEvent() && evt.getKeyCode() == 1) {
/* 1257 */       switch (this.dragActive) {
/*      */         case 2:
/* 1259 */           cancelDragging();
/*      */           break;
/*      */         case 1:
/* 1262 */           columnHeaderDragged(this.dragStartColWidth);
/* 1263 */           this.dragActive = 3;
/*      */           break;
/*      */       } 
/* 1266 */       setMouseCursor(this.normalCursor);
/*      */     } 
/*      */     
/* 1269 */     return true;
/*      */   }
/*      */   
/*      */   void mouseLeftTableArea() {
/* 1273 */     this.lastMouseY = Integer.MIN_VALUE;
/* 1274 */     this.lastMouseRow = -1;
/* 1275 */     this.lastMouseColumn = -1;
/*      */   }
/*      */ 
/*      */   
/*      */   Widget routeMouseEvent(Event evt) {
/* 1280 */     if (evt.getType() == Event.Type.MOUSE_EXITED) {
/* 1281 */       mouseLeftTableArea();
/*      */     } else {
/* 1283 */       this.lastMouseY = evt.getMouseY();
/*      */     } 
/*      */     
/* 1286 */     if (this.dragActive == 0) {
/* 1287 */       boolean inHeader = isMouseInColumnHeader(evt.getMouseY());
/* 1288 */       if (inHeader) {
/* 1289 */         if (this.lastMouseRow != -1 || this.lastMouseColumn != -1) {
/* 1290 */           this.lastMouseRow = -1;
/* 1291 */           this.lastMouseColumn = -1;
/* 1292 */           resetTooltip();
/*      */         } 
/*      */       } else {
/* 1295 */         int row = getRowUnderMouse(evt.getMouseY());
/* 1296 */         int column = getColumnUnderMouse(evt.getMouseX());
/*      */         
/* 1298 */         if (this.lastMouseRow != row || this.lastMouseColumn != column) {
/* 1299 */           this.lastMouseRow = row;
/* 1300 */           this.lastMouseColumn = column;
/* 1301 */           resetTooltip();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1306 */     return super.routeMouseEvent(evt);
/*      */   }
/*      */   
/*      */   protected boolean handleMouseEvent(Event evt) {
/* 1310 */     Event.Type evtType = evt.getType();
/*      */     
/* 1312 */     if (this.dragActive != 0) {
/* 1313 */       int innerWidth; switch (this.dragActive) {
/*      */         case 1:
/* 1315 */           innerWidth = getInnerWidth();
/* 1316 */           if (this.dragColumn >= 0 && innerWidth > 0) {
/* 1317 */             int newWidth = clampColumnWidth(evt.getMouseX() - this.dragStartX);
/* 1318 */             columnHeaderDragged(newWidth);
/*      */           } 
/*      */           break;
/*      */         
/*      */         case 2:
/* 1323 */           setMouseCursor(this.dragListener.dragged(evt));
/* 1324 */           if (evt.isMouseDragEnd()) {
/* 1325 */             this.dragListener.dragStopped(evt);
/*      */           }
/*      */           break;
/*      */         
/*      */         case 3:
/*      */           break;
/*      */         default:
/* 1332 */           throw new AssertionError();
/*      */       } 
/* 1334 */       if (evt.isMouseDragEnd()) {
/* 1335 */         this.dragActive = 0;
/* 1336 */         setMouseCursor(this.normalCursor);
/*      */       } 
/* 1338 */       return true;
/*      */     } 
/*      */     
/* 1341 */     boolean inHeader = isMouseInColumnHeader(evt.getMouseY());
/* 1342 */     if (inHeader) {
/* 1343 */       int column = getColumnSeparatorUnderMouse(evt.getMouseX());
/* 1344 */       boolean fixedWidthMode = isFixedWidthMode();
/*      */ 
/*      */ 
/*      */       
/* 1348 */       if (column >= 0 && (column < getNumColumns() - 1 || !fixedWidthMode)) {
/* 1349 */         setMouseCursor(this.columnResizeCursor);
/*      */         
/* 1351 */         if (evtType == Event.Type.MOUSE_BTNDOWN) {
/* 1352 */           this.dragStartColWidth = getColumnWidth(column);
/* 1353 */           this.dragColumn = column;
/* 1354 */           this.dragStartX = evt.getMouseX() - this.dragStartColWidth;
/* 1355 */           if (fixedWidthMode) {
/* 1356 */             for (int i = 0; i < this.numColumns; i++) {
/* 1357 */               this.columnHeaders[i].setColumnWidth(getColumnWidth(i));
/*      */             }
/* 1359 */             this.dragStartSumWidth = this.dragStartColWidth + getColumnWidth(column + 1);
/*      */           } 
/*      */         } 
/*      */         
/* 1363 */         if (evt.isMouseDragEvent()) {
/* 1364 */           this.dragActive = 1;
/*      */         }
/* 1366 */         return true;
/*      */       } 
/*      */     } else {
/*      */       
/* 1370 */       int row = this.lastMouseRow;
/* 1371 */       int column = this.lastMouseColumn;
/*      */       
/* 1373 */       if (evt.isMouseDragEvent()) {
/* 1374 */         if (this.dragListener != null && this.dragListener.dragStarted(row, row, evt)) {
/* 1375 */           setMouseCursor(this.dragListener.dragged(evt));
/* 1376 */           this.dragActive = 2;
/*      */         } else {
/* 1378 */           this.dragActive = 3;
/* 1379 */           setMouseCursor(this.dragNotPossibleCursor);
/*      */         } 
/* 1381 */         return true;
/*      */       } 
/*      */       
/* 1384 */       if (this.selectionManager != null) {
/* 1385 */         this.selectionManager.handleMouseEvent(row, column, evt);
/*      */       }
/*      */       
/* 1388 */       if (evtType == Event.Type.MOUSE_CLICKED && evt.getMouseClickCount() == 2 && 
/* 1389 */         this.callbacks != null) {
/* 1390 */         for (Callback cb : this.callbacks) {
/* 1391 */           cb.mouseDoubleClicked(row, column);
/*      */         }
/*      */       }
/*      */ 
/*      */       
/* 1396 */       if (evtType == Event.Type.MOUSE_BTNUP && evt.getMouseButton() == 1 && 
/* 1397 */         this.callbacks != null) {
/* 1398 */         for (Callback cb : this.callbacks) {
/* 1399 */           cb.mouseRightClick(row, column, evt);
/*      */         }
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1405 */     setMouseCursor(this.normalCursor);
/*      */ 
/*      */     
/* 1408 */     return (evtType != Event.Type.MOUSE_WHEEL);
/*      */   }
/*      */   
/*      */   private void columnHeaderDragged(int newWidth) {
/* 1412 */     if (isFixedWidthMode()) {
/* 1413 */       assert this.dragColumn + 1 < this.numColumns;
/* 1414 */       newWidth = Math.min(newWidth, this.dragStartSumWidth - 2 * this.columnDividerDragableDistance);
/* 1415 */       this.columnHeaders[this.dragColumn].setColumnWidth(newWidth);
/* 1416 */       this.columnHeaders[this.dragColumn + 1].setColumnWidth(this.dragStartSumWidth - newWidth);
/* 1417 */       this.updateAllColumnWidth = true;
/* 1418 */       invalidateLayout();
/*      */     } else {
/* 1420 */       setColumnWidth(this.dragColumn, newWidth);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void columnHeaderClicked(int column) {
/* 1425 */     if (this.callbacks != null) {
/* 1426 */       for (Callback cb : this.callbacks) {
/* 1427 */         cb.columnHeaderClicked(column);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   protected void updateAllColumnWidth() {
/* 1433 */     if (getInnerWidth() > 0) {
/* 1434 */       this.columnModel.initializeAll(this.numColumns);
/* 1435 */       this.updateAllColumnWidth = false;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void updateAll() {
/* 1440 */     if (!this.widgetGrid.isEmpty()) {
/* 1441 */       removeAllCellWidgets();
/* 1442 */       this.widgetGrid.clear();
/*      */     } 
/*      */     
/* 1445 */     if (this.rowModel != null) {
/* 1446 */       this.autoSizeAllRows = true;
/*      */     }
/*      */     
/* 1449 */     this.updateAllCellWidgets = true;
/* 1450 */     this.updateAllColumnWidth = true;
/* 1451 */     invalidateLayout();
/*      */   }
/*      */   
/*      */   protected void modelAllChanged() {
/* 1455 */     if (this.columnHeaders != null) {
/* 1456 */       removeColumnHeaders(0, this.columnHeaders.length);
/*      */     }
/*      */     
/* 1459 */     this.dropMarkerRow = -1;
/* 1460 */     this.columnHeaders = new ColumnHeader[this.numColumns];
/* 1461 */     for (int i = 0; i < this.numColumns; i++) {
/* 1462 */       this.columnHeaders[i] = createColumnHeader(i);
/* 1463 */       updateColumnHeader(i);
/*      */     } 
/* 1465 */     updateColumnHeaderNumbers();
/*      */     
/* 1467 */     if (this.selectionManager != null) {
/* 1468 */       this.selectionManager.modelChanged();
/*      */     }
/*      */     
/* 1471 */     updateAll();
/*      */   }
/*      */   
/*      */   protected void modelRowChanged(int row) {
/* 1475 */     if (this.rowModel != null && 
/* 1476 */       autoSizeRow(row)) {
/* 1477 */       invalidateLayout();
/*      */     }
/*      */     
/* 1480 */     for (int col = 0; col < this.numColumns; col++) {
/* 1481 */       updateCellWidget(row, col);
/*      */     }
/* 1483 */     invalidateLayoutLocally();
/*      */   }
/*      */   
/*      */   protected void modelRowsChanged(int idx, int count) {
/* 1487 */     checkRowRange(idx, count);
/* 1488 */     boolean rowHeightChanged = false;
/* 1489 */     for (int i = 0; i < count; i++) {
/* 1490 */       if (this.rowModel != null) {
/* 1491 */         rowHeightChanged |= autoSizeRow(idx + i);
/*      */       }
/* 1493 */       for (int col = 0; col < this.numColumns; col++) {
/* 1494 */         updateCellWidget(idx + i, col);
/*      */       }
/*      */     } 
/* 1497 */     invalidateLayoutLocally();
/* 1498 */     if (rowHeightChanged) {
/* 1499 */       invalidateLayout();
/*      */     }
/*      */   }
/*      */   
/*      */   protected void modelCellChanged(int row, int column) {
/* 1504 */     checkRowIndex(row);
/* 1505 */     checkColumnIndex(column);
/* 1506 */     if (this.rowModel != null) {
/* 1507 */       autoSizeRow(row);
/*      */     }
/* 1509 */     updateCellWidget(row, column);
/* 1510 */     invalidateLayout();
/*      */   }
/*      */   
/*      */   protected void modelRowsInserted(int row, int count) {
/* 1514 */     checkRowRange(row, count);
/* 1515 */     if (this.rowModel != null) {
/* 1516 */       this.rowModel.insert(row, count);
/*      */     }
/* 1518 */     if (this.dropMarkerRow > row || (this.dropMarkerRow == row && this.dropMarkerBeforeRow)) {
/* 1519 */       this.dropMarkerRow += count;
/*      */     }
/* 1521 */     if (!this.widgetGrid.isEmpty() || this.hasCellWidgetCreators) {
/* 1522 */       removeAllCellWidgets();
/* 1523 */       this.widgetGrid.insertRows(row, count);
/*      */       
/* 1525 */       for (int i = 0; i < count; i++) {
/* 1526 */         for (int col = 0; col < this.numColumns; col++) {
/* 1527 */           updateCellWidget(row + i, col);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1533 */     invalidateLayout();
/* 1534 */     if (row < getRowFromPosition(this.scrollPosY)) {
/* 1535 */       ScrollPane sp = ScrollPane.getContainingScrollPane(this);
/* 1536 */       if (sp != null) {
/* 1537 */         int rowsStart = getRowStartPosition(row);
/* 1538 */         int rowsEnd = getRowEndPosition(row + count - 1);
/* 1539 */         sp.setScrollPositionY(this.scrollPosY + rowsEnd - rowsStart);
/*      */       } 
/*      */     } 
/* 1542 */     if (this.selectionManager != null) {
/* 1543 */       this.selectionManager.rowsInserted(row, count);
/*      */     }
/*      */   }
/*      */   
/*      */   protected void modelRowsDeleted(int row, int count) {
/* 1548 */     if (row + count <= getRowFromPosition(this.scrollPosY)) {
/* 1549 */       ScrollPane sp = ScrollPane.getContainingScrollPane(this);
/* 1550 */       if (sp != null) {
/* 1551 */         int rowsStart = getRowStartPosition(row);
/* 1552 */         int rowsEnd = getRowEndPosition(row + count - 1);
/* 1553 */         sp.setScrollPositionY(this.scrollPosY - rowsEnd + rowsStart);
/*      */       } 
/*      */     } 
/* 1556 */     if (this.rowModel != null) {
/* 1557 */       this.rowModel.remove(row, count);
/*      */     }
/* 1559 */     if (this.dropMarkerRow >= row) {
/* 1560 */       if (this.dropMarkerRow < row + count) {
/* 1561 */         this.dropMarkerRow = -1;
/*      */       } else {
/* 1563 */         this.dropMarkerRow -= count;
/*      */       } 
/*      */     }
/* 1566 */     if (!this.widgetGrid.isEmpty()) {
/* 1567 */       this.widgetGrid.iterate(row, 0, row + count - 1, this.numColumns, this.removeCellWidgetsFunction);
/* 1568 */       this.widgetGrid.removeRows(row, count);
/*      */     } 
/* 1570 */     if (this.selectionManager != null) {
/* 1571 */       this.selectionManager.rowsDeleted(row, count);
/*      */     }
/* 1573 */     invalidateLayout();
/*      */   }
/*      */   
/*      */   protected void modelColumnsInserted(int column, int count) {
/* 1577 */     checkColumnRange(column, count);
/* 1578 */     ColumnHeader[] newColumnHeaders = new ColumnHeader[this.numColumns];
/* 1579 */     System.arraycopy(this.columnHeaders, 0, newColumnHeaders, 0, column);
/* 1580 */     System.arraycopy(this.columnHeaders, column, newColumnHeaders, column + count, this.numColumns - column + count);
/*      */     
/* 1582 */     for (int i = 0; i < count; i++) {
/* 1583 */       newColumnHeaders[column + i] = createColumnHeader(column + i);
/*      */     }
/* 1585 */     this.columnHeaders = newColumnHeaders;
/* 1586 */     updateColumnHeaderNumbers();
/*      */     
/* 1588 */     this.columnModel.insert(column, count);
/*      */     
/* 1590 */     if (!this.widgetGrid.isEmpty() || this.hasCellWidgetCreators) {
/* 1591 */       removeAllCellWidgets();
/* 1592 */       this.widgetGrid.insertColumns(column, count);
/*      */       
/* 1594 */       for (int row = 0; row < this.numRows; row++) {
/* 1595 */         for (int j = 0; j < count; j++) {
/* 1596 */           updateCellWidget(row, column + j);
/*      */         }
/*      */       } 
/*      */     } 
/* 1600 */     if (column < getColumnStartPosition(this.scrollPosX)) {
/* 1601 */       ScrollPane sp = ScrollPane.getContainingScrollPane(this);
/* 1602 */       if (sp != null) {
/* 1603 */         int columnsStart = getColumnStartPosition(column);
/* 1604 */         int columnsEnd = getColumnEndPosition(column + count - 1);
/* 1605 */         sp.setScrollPositionX(this.scrollPosX + columnsEnd - columnsStart);
/*      */       } 
/*      */     } 
/* 1608 */     invalidateLayout();
/*      */   }
/*      */   
/*      */   protected void modelColumnsDeleted(int column, int count) {
/* 1612 */     if (column + count <= getColumnStartPosition(this.scrollPosX)) {
/* 1613 */       ScrollPane sp = ScrollPane.getContainingScrollPane(this);
/* 1614 */       if (sp != null) {
/* 1615 */         int columnsStart = getColumnStartPosition(column);
/* 1616 */         int columnsEnd = getColumnEndPosition(column + count - 1);
/* 1617 */         sp.setScrollPositionY(this.scrollPosX - columnsEnd + columnsStart);
/*      */       } 
/*      */     } 
/* 1620 */     this.columnModel.remove(column, count);
/* 1621 */     if (!this.widgetGrid.isEmpty()) {
/* 1622 */       this.widgetGrid.iterate(0, column, this.numRows, column + count - 1, this.removeCellWidgetsFunction);
/* 1623 */       this.widgetGrid.removeColumns(column, count);
/*      */     } 
/*      */     
/* 1626 */     removeColumnHeaders(column, count);
/*      */     
/* 1628 */     ColumnHeader[] newColumnHeaders = new ColumnHeader[this.numColumns];
/* 1629 */     System.arraycopy(this.columnHeaders, 0, newColumnHeaders, 0, column);
/* 1630 */     System.arraycopy(this.columnHeaders, column + count, newColumnHeaders, column, this.numColumns - count);
/* 1631 */     this.columnHeaders = newColumnHeaders;
/* 1632 */     updateColumnHeaderNumbers();
/*      */     
/* 1634 */     invalidateLayout();
/*      */   } public static interface Callback {
/*      */     void mouseDoubleClicked(int param1Int1, int param1Int2); void mouseRightClick(int param1Int1, int param1Int2, Event param1Event); void columnHeaderClicked(int param1Int); } public static interface CellRenderer {
/*      */     void applyTheme(ThemeInfo param1ThemeInfo); String getTheme(); void setCellData(int param1Int1, int param1Int2, Object param1Object); int getColumnSpan(); int getPreferredHeight(); Widget getCellRenderWidget(int param1Int1, int param1Int2, int param1Int3, int param1Int4, boolean param1Boolean); } public static interface CellWidgetCreator extends CellRenderer { Widget updateWidget(Widget param1Widget); void positionWidget(Widget param1Widget, int param1Int1, int param1Int2, int param1Int3, int param1Int4); } protected void modelColumnHeaderChanged(int column) {
/* 1638 */     checkColumnIndex(column);
/* 1639 */     updateColumnHeader(column);
/*      */   } public static interface KeyboardSearchHandler {
/*      */     boolean handleKeyEvent(Event param1Event); boolean isActive(); void updateInfoWindowPosition(); } public static interface DragListener {
/*      */     boolean dragStarted(int param1Int1, int param1Int2, Event param1Event); MouseCursor dragged(Event param1Event); void dragStopped(Event param1Event); void dragCanceled(); }
/*      */   class RowSizeSequence extends SizeSequence { public RowSizeSequence(int initialCapacity) {
/* 1644 */       super(initialCapacity);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void initializeSizes(int index, int count) {
/* 1649 */       for (int i = 0; i < count; i++, index++)
/* 1650 */         this.table[index] = TableBase.this.computeRowHeight(index); 
/*      */     } }
/*      */ 
/*      */   
/*      */   protected class ColumnSizeSequence
/*      */     extends SizeSequence
/*      */   {
/*      */     protected void initializeSizes(int index, int count) {
/* 1658 */       boolean useSprings = TableBase.this.isFixedWidthMode();
/* 1659 */       if (!useSprings) {
/* 1660 */         int sum = 0;
/* 1661 */         for (int i = 0; i < count; i++) {
/* 1662 */           int width = TableBase.this.computePreferredColumnWidth(index + i);
/* 1663 */           this.table[index + i] = width;
/* 1664 */           sum += width;
/*      */         } 
/* 1666 */         useSprings = (sum < TableBase.this.getInnerWidth());
/*      */       } 
/* 1668 */       if (useSprings) {
/* 1669 */         computeColumnHeaderLayout();
/* 1670 */         for (int i = 0; i < count; i++)
/* 1671 */           this.table[index + i] = TableBase.this.clampColumnWidth((TableBase.this.columnHeaders[i]).springWidth); 
/*      */       } 
/*      */     }
/*      */     
/*      */     protected boolean update(int index) {
/*      */       int width;
/* 1677 */       if (TableBase.this.isFixedWidthMode()) {
/* 1678 */         computeColumnHeaderLayout();
/* 1679 */         width = TableBase.this.clampColumnWidth((TableBase.this.columnHeaders[index]).springWidth);
/*      */       } else {
/* 1681 */         width = TableBase.this.computePreferredColumnWidth(index);
/* 1682 */         if (TableBase.this.ensureColumnHeaderMinWidth) {
/* 1683 */           width = Math.max(width, TableBase.this.columnHeaders[index].getMinWidth());
/*      */         }
/*      */       } 
/* 1686 */       return setSize(index, width);
/*      */     }
/*      */     void computeColumnHeaderLayout() {
/* 1689 */       if (TableBase.this.columnHeaders != null) {
/* 1690 */         DialogLayout.SequentialGroup g = (DialogLayout.SequentialGroup)(new DialogLayout()).createSequentialGroup();
/* 1691 */         for (TableBase.ColumnHeader h : TableBase.this.columnHeaders) {
/* 1692 */           g.addSpring(h.spring);
/*      */         }
/* 1694 */         g.setSize(0, 0, TableBase.this.getInnerWidth());
/*      */       } 
/*      */     }
/*      */     int computePreferredWidth() {
/* 1698 */       int count = TableBase.this.getNumColumns();
/* 1699 */       if (!TableBase.this.isFixedWidthMode()) {
/* 1700 */         int sum = 0;
/* 1701 */         for (int i = 0; i < count; i++) {
/* 1702 */           int width = TableBase.this.computePreferredColumnWidth(i);
/* 1703 */           sum += width;
/*      */         } 
/* 1705 */         return sum;
/*      */       } 
/* 1707 */       if (TableBase.this.columnHeaders != null) {
/* 1708 */         DialogLayout.SequentialGroup g = (DialogLayout.SequentialGroup)(new DialogLayout()).createSequentialGroup();
/* 1709 */         for (TableBase.ColumnHeader h : TableBase.this.columnHeaders) {
/* 1710 */           g.addSpring(h.spring);
/*      */         }
/* 1712 */         return g.getPrefSize(0);
/*      */       } 
/* 1714 */       return 0;
/*      */     }
/*      */   }
/*      */   
/*      */   class RemoveCellWidgets implements SparseGrid.GridFunction {
/*      */     public void apply(int row, int column, SparseGrid.Entry e) {
/* 1720 */       TableBase.WidgetEntry widgetEntry = (TableBase.WidgetEntry)e;
/* 1721 */       Widget widget = widgetEntry.widget;
/* 1722 */       if (widget != null)
/* 1723 */         TableBase.this.removeCellWidget(widget); 
/*      */     }
/*      */   }
/*      */   
/*      */   class InsertCellWidgets
/*      */     implements SparseGrid.GridFunction {
/*      */     public void apply(int row, int column, SparseGrid.Entry e) {
/* 1730 */       TableBase.this.insertCellWidget(row, column, (TableBase.WidgetEntry)e);
/*      */     }
/*      */   }
/*      */   
/*      */   protected class ColumnHeader
/*      */     extends Button
/*      */     implements Runnable
/*      */   {
/*      */     int column;
/*      */     private int columnWidth;
/*      */     int springWidth;
/*      */     final DialogLayout.Spring spring;
/*      */     
/*      */     public ColumnHeader() {
/* 1744 */       this.spring = new DialogLayout.Spring()
/*      */         {
/*      */           int getMinSize(int axis) {
/* 1747 */             return TableBase.this.clampColumnWidth(TableBase.ColumnHeader.this.getMinWidth());
/*      */           }
/*      */           
/*      */           int getPrefSize(int axis) {
/* 1751 */             return TableBase.ColumnHeader.this.getPreferredWidth();
/*      */           }
/*      */           
/*      */           int getMaxSize(int axis) {
/* 1755 */             return TableBase.ColumnHeader.this.getMaxWidth();
/*      */           }
/*      */           
/*      */           void setSize(int axis, int pos, int size) {
/* 1759 */             TableBase.ColumnHeader.this.springWidth = size;
/*      */           }
/*      */         };
/*      */       addCallback(this);
/*      */     } public int getColumnWidth() {
/* 1764 */       return this.columnWidth;
/*      */     }
/*      */     
/*      */     public void setColumnWidth(int columnWidth) {
/* 1768 */       this.columnWidth = columnWidth;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getPreferredWidth() {
/* 1773 */       if (this.columnWidth > 0) {
/* 1774 */         return this.columnWidth;
/*      */       }
/* 1776 */       DialogLayout.Gap mpm = TableBase.this.getColumnMPM(this.column);
/* 1777 */       int prefWidth = (mpm != null) ? mpm.preferred : TableBase.this.defaultColumnWidth;
/* 1778 */       return Math.max(prefWidth, super.getPreferredWidth());
/*      */     }
/*      */ 
/*      */     
/*      */     public int getMinWidth() {
/* 1783 */       DialogLayout.Gap mpm = TableBase.this.getColumnMPM(this.column);
/* 1784 */       int minWidth = (mpm != null) ? mpm.min : 0;
/* 1785 */       return Math.max(minWidth, super.getPreferredWidth());
/*      */     }
/*      */ 
/*      */     
/*      */     public int getMaxWidth() {
/* 1790 */       DialogLayout.Gap mpm = TableBase.this.getColumnMPM(this.column);
/* 1791 */       int maxWidth = (mpm != null) ? mpm.max : 32767;
/* 1792 */       return maxWidth;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void adjustSize() {}
/*      */ 
/*      */ 
/*      */     
/*      */     protected boolean handleEvent(Event evt) {
/* 1802 */       if (evt.isMouseEventNoWheel()) {
/* 1803 */         TableBase.this.mouseLeftTableArea();
/*      */       }
/* 1805 */       return super.handleEvent(evt);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void paintWidget(GUI gui) {
/* 1810 */       Renderer renderer = gui.getRenderer();
/* 1811 */       renderer.clipEnter(getX(), getY(), getWidth(), getHeight());
/*      */       try {
/* 1813 */         paintLabelText(getAnimationState());
/*      */       } finally {
/* 1815 */         renderer.clipLeave();
/*      */       } 
/*      */     }
/*      */     
/*      */     public void run() {
/* 1820 */       TableBase.this.columnHeaderClicked(this.column);
/*      */     }
/*      */   }
/*      */   
/*      */   static class WidgetEntry extends SparseGrid.Entry {
/*      */     Widget widget;
/*      */     TableBase.CellWidgetCreator creator;
/*      */   }
/*      */   
/*      */   static class CellWidgetContainer extends Widget {
/*      */     CellWidgetContainer() {
/* 1831 */       setTheme("");
/* 1832 */       setClip(true);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void childInvalidateLayout(Widget child) {}
/*      */ 
/*      */ 
/*      */     
/*      */     protected void sizeChanged() {}
/*      */ 
/*      */ 
/*      */     
/*      */     protected void childAdded(Widget child) {}
/*      */ 
/*      */ 
/*      */     
/*      */     protected void childRemoved(Widget exChild) {}
/*      */ 
/*      */ 
/*      */     
/*      */     protected void allChildrenRemoved() {}
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class StringCellRenderer
/*      */     extends TextWidget
/*      */     implements CellRenderer
/*      */   {
/*      */     public StringCellRenderer() {
/* 1863 */       setCache(false);
/* 1864 */       setClip(true);
/*      */     }
/*      */ 
/*      */     
/*      */     public void applyTheme(ThemeInfo themeInfo) {
/* 1869 */       super.applyTheme(themeInfo);
/*      */     }
/*      */     
/*      */     public void setCellData(int row, int column, Object data) {
/* 1873 */       setCharSequence(String.valueOf(data));
/*      */     }
/*      */     
/*      */     public int getColumnSpan() {
/* 1877 */       return 1;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void sizeChanged() {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Widget getCellRenderWidget(int x, int y, int width, int height, boolean isSelected) {
/* 1890 */       setPosition(x, y);
/* 1891 */       setSize(width, height);
/* 1892 */       getAnimationState().setAnimationState(TableBase.STATE_SELECTED, isSelected);
/* 1893 */       return this;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\TableBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */