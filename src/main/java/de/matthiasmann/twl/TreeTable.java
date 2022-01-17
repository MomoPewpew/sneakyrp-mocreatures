/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.BooleanModel;
/*     */ import de.matthiasmann.twl.model.TableColumnHeaderModel;
/*     */ import de.matthiasmann.twl.model.TreeTableModel;
/*     */ import de.matthiasmann.twl.model.TreeTableNode;
/*     */ import de.matthiasmann.twl.utils.CallbackSupport;
/*     */ import de.matthiasmann.twl.utils.HashEntry;
/*     */ import de.matthiasmann.twl.utils.SizeSequence;
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
/*     */ public class TreeTable
/*     */   extends TableBase
/*     */ {
/*  68 */   private final ModelChangeListener modelChangeListener = new ModelChangeListener();
/*  69 */   private NodeState[] nodeStateTable = new NodeState[64];
/*  70 */   private final TreeLeafCellRenderer leafRenderer = new TreeLeafCellRenderer();
/*  71 */   private final TreeNodeCellRenderer nodeRenderer = new TreeNodeCellRenderer(); private int nodeStateTableSize; TreeTableModel model; private NodeState rootNodeState; private ExpandListener[] expandListeners;
/*     */   
/*     */   public TreeTable() {
/*  74 */     ActionMap am = getOrCreateActionMap();
/*  75 */     am.addMapping("expandLeadRow", this, "setLeadRowExpanded", new Object[] { Boolean.TRUE }, 1);
/*  76 */     am.addMapping("collapseLeadRow", this, "setLeadRowExpanded", new Object[] { Boolean.FALSE }, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public TreeTable(TreeTableModel model) {
/*  81 */     this();
/*  82 */     setModel(model);
/*     */   }
/*     */   
/*     */   public void setModel(TreeTableModel model) {
/*  86 */     if (this.model != null) {
/*  87 */       this.model.removeChangeListener(this.modelChangeListener);
/*     */     }
/*  89 */     this.columnHeaderModel = (TableColumnHeaderModel)model;
/*  90 */     this.model = model;
/*  91 */     this.nodeStateTable = new NodeState[64];
/*  92 */     this.nodeStateTableSize = 0;
/*  93 */     if (this.model != null) {
/*  94 */       this.model.addChangeListener(this.modelChangeListener);
/*  95 */       this.rootNodeState = createNodeState((TreeTableNode)model);
/*  96 */       this.rootNodeState.level = -1;
/*  97 */       this.rootNodeState.expanded = true;
/*  98 */       this.rootNodeState.initChildSizes();
/*  99 */       this.numRows = computeNumRows();
/* 100 */       this.numColumns = model.getNumColumns();
/*     */     } else {
/* 102 */       this.rootNodeState = null;
/* 103 */       this.numRows = 0;
/* 104 */       this.numColumns = 0;
/*     */     } 
/* 106 */     modelAllChanged();
/* 107 */     invalidateLayout();
/*     */   }
/*     */   
/*     */   public void addExpandListener(ExpandListener listener) {
/* 111 */     this.expandListeners = (ExpandListener[])CallbackSupport.addCallbackToList((Object[])this.expandListeners, listener, ExpandListener.class);
/*     */   }
/*     */   
/*     */   public void removeExpandListener(ExpandListener listener) {
/* 115 */     this.expandListeners = (ExpandListener[])CallbackSupport.removeCallbackFromList((Object[])this.expandListeners, listener);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 120 */     super.applyTheme(themeInfo);
/* 121 */     applyThemeTreeTable(themeInfo);
/*     */   }
/*     */   
/*     */   protected void applyThemeTreeTable(ThemeInfo themeInfo) {
/* 125 */     applyCellRendererTheme(this.leafRenderer);
/* 126 */     applyCellRendererTheme(this.nodeRenderer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRowFromNode(TreeTableNode node) {
/* 136 */     int position = -1;
/* 137 */     TreeTableNode parent = node.getParent();
/* 138 */     while (parent != null) {
/* 139 */       NodeState ns = (NodeState)HashEntry.get((HashEntry[])this.nodeStateTable, parent);
/* 140 */       if (ns == null)
/*     */       {
/* 142 */         return -1;
/*     */       }
/* 144 */       int idx = parent.getChildIndex(node);
/* 145 */       if (idx < 0)
/*     */       {
/* 147 */         return -1;
/*     */       }
/* 149 */       if (ns.childSizes == null) {
/* 150 */         if (ns.expanded) {
/* 151 */           ns.initChildSizes();
/*     */         } else {
/* 153 */           return -1;
/*     */         } 
/*     */       }
/* 156 */       idx = ns.childSizes.getPosition(idx);
/* 157 */       position += idx + 1;
/* 158 */       node = parent;
/* 159 */       parent = node.getParent();
/*     */     } 
/* 161 */     return position;
/*     */   }
/*     */   
/*     */   public int getRowFromNodeExpand(TreeTableNode node) {
/* 165 */     if (node.getParent() != null) {
/* 166 */       TreeTableNode parent = node.getParent();
/* 167 */       int row = getRowFromNodeExpand(parent);
/* 168 */       int idx = parent.getChildIndex(node);
/* 169 */       NodeState ns = (NodeState)HashEntry.get((HashEntry[])this.nodeStateTable, parent);
/* 170 */       ns.setValue(true);
/* 171 */       if (ns.childSizes == null) {
/* 172 */         ns.initChildSizes();
/*     */       }
/* 174 */       return row + 1 + ns.childSizes.getPosition(idx);
/*     */     } 
/* 176 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public TreeTableNode getNodeFromRow(int row) {
/* 181 */     NodeState ns = this.rootNodeState;
/*     */     while (true) {
/*     */       int idx;
/* 184 */       if (ns.childSizes == null) {
/* 185 */         idx = Math.min(((TreeTableNode)ns.key).getNumChildren() - 1, row);
/* 186 */         row -= idx + 1;
/*     */       } else {
/* 188 */         idx = ns.childSizes.getIndex(row);
/* 189 */         row -= ns.childSizes.getPosition(idx) + 1;
/*     */       } 
/* 191 */       if (row < 0) {
/* 192 */         return ((TreeTableNode)ns.key).getChild(idx);
/*     */       }
/* 194 */       assert ns.children[idx] != null;
/* 195 */       ns = ns.children[idx];
/*     */     } 
/*     */   }
/*     */   
/*     */   public void collapseAll() {
/* 200 */     for (int i = 0; i < this.nodeStateTable.length; i++) {
/* 201 */       for (NodeState ns = this.nodeStateTable[i]; ns != null; ns = (NodeState)ns.next()) {
/* 202 */         if (ns != this.rootNodeState) {
/* 203 */           ns.setValue(false);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isRowExpanded(int row) {
/* 210 */     checkRowIndex(row);
/* 211 */     TreeTableNode node = getNodeFromRow(row);
/* 212 */     NodeState ns = (NodeState)HashEntry.get((HashEntry[])this.nodeStateTable, node);
/* 213 */     return (ns != null && ns.expanded);
/*     */   }
/*     */   
/*     */   public void setRowExpanded(int row, boolean expanded) {
/* 217 */     checkRowIndex(row);
/* 218 */     TreeTableNode node = getNodeFromRow(row);
/* 219 */     NodeState state = getOrCreateNodeState(node);
/* 220 */     state.setValue(expanded);
/*     */   }
/*     */   
/*     */   public void setLeadRowExpanded(boolean expanded) {
/* 224 */     TableSelectionManager sm = getSelectionManager();
/* 225 */     if (sm != null) {
/* 226 */       int row = sm.getLeadRow();
/* 227 */       if (row >= 0 && row < this.numRows) {
/* 228 */         setRowExpanded(row, expanded);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected NodeState getOrCreateNodeState(TreeTableNode node) {
/* 234 */     NodeState ns = (NodeState)HashEntry.get((HashEntry[])this.nodeStateTable, node);
/* 235 */     if (ns == null) {
/* 236 */       ns = createNodeState(node);
/*     */     }
/* 238 */     return ns;
/*     */   }
/*     */   
/*     */   protected NodeState createNodeState(TreeTableNode node) {
/* 242 */     TreeTableNode parent = node.getParent();
/* 243 */     NodeState nsParent = null;
/* 244 */     if (parent != null) {
/* 245 */       nsParent = (NodeState)HashEntry.get((HashEntry[])this.nodeStateTable, parent);
/* 246 */       assert nsParent != null;
/*     */     } 
/* 248 */     NodeState newNS = new NodeState(node, nsParent);
/* 249 */     this.nodeStateTable = (NodeState[])HashEntry.maybeResizeTable((HashEntry[])this.nodeStateTable, ++this.nodeStateTableSize);
/* 250 */     HashEntry.insertEntry((HashEntry[])this.nodeStateTable, newNS);
/* 251 */     return newNS;
/*     */   }
/*     */   
/*     */   protected void expandedChanged(NodeState ns) {
/* 255 */     TreeTableNode node = (TreeTableNode)ns.key;
/* 256 */     int count = ns.getChildRows();
/* 257 */     int size = ns.expanded ? count : 0;
/*     */     
/* 259 */     TreeTableNode parent = node.getParent();
/* 260 */     while (parent != null) {
/* 261 */       NodeState nsParent = (NodeState)HashEntry.get((HashEntry[])this.nodeStateTable, parent);
/* 262 */       if (nsParent.childSizes == null) {
/* 263 */         nsParent.initChildSizes();
/*     */       }
/*     */       
/* 266 */       int idx = ((TreeTableNode)nsParent.key).getChildIndex(node);
/* 267 */       nsParent.childSizes.setSize(idx, size + 1);
/* 268 */       size = nsParent.childSizes.getEndPosition();
/*     */       
/* 270 */       node = parent;
/* 271 */       parent = node.getParent();
/*     */     } 
/*     */     
/* 274 */     this.numRows = computeNumRows();
/* 275 */     int row = getRowFromNode((TreeTableNode)ns.key);
/* 276 */     if (ns.expanded) {
/* 277 */       modelRowsInserted(row + 1, count);
/*     */     } else {
/* 279 */       modelRowsDeleted(row + 1, count);
/*     */     } 
/* 281 */     modelRowsChanged(row, 1);
/*     */     
/* 283 */     if (ns.expanded) {
/* 284 */       ScrollPane scrollPane = ScrollPane.getContainingScrollPane(this);
/* 285 */       if (scrollPane != null) {
/* 286 */         scrollPane.validateLayout();
/* 287 */         int rowStart = getRowStartPosition(row);
/* 288 */         int rowEnd = getRowEndPosition(row + count);
/* 289 */         int height = rowEnd - rowStart;
/* 290 */         scrollPane.scrollToAreaY(rowStart, height, this.rowHeight / 2);
/*     */       } 
/*     */     } 
/*     */     
/* 294 */     if (this.expandListeners != null) {
/* 295 */       for (ExpandListener el : this.expandListeners) {
/* 296 */         if (ns.expanded) {
/* 297 */           el.nodeExpanded(row, (TreeTableNode)ns.key);
/*     */         } else {
/* 299 */           el.nodeCollapsed(row, (TreeTableNode)ns.key);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected int computeNumRows() {
/* 306 */     return this.rootNodeState.childSizes.getEndPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object getCellData(int row, int column, TreeTableNode node) {
/* 311 */     if (node == null) {
/* 312 */       node = getNodeFromRow(row);
/*     */     }
/* 314 */     return node.getData(column);
/*     */   }
/*     */ 
/*     */   
/*     */   protected TableBase.CellRenderer getCellRenderer(int row, int col, TreeTableNode node) {
/* 319 */     if (node == null) {
/* 320 */       node = getNodeFromRow(row);
/*     */     }
/* 322 */     if (col == 0) {
/* 323 */       Object data = node.getData(col);
/* 324 */       if (node.isLeaf()) {
/* 325 */         this.leafRenderer.setCellData(row, col, data, node);
/* 326 */         return this.leafRenderer;
/*     */       } 
/* 328 */       NodeState nodeState = getOrCreateNodeState(node);
/* 329 */       this.nodeRenderer.setCellData(row, col, data, nodeState);
/* 330 */       return this.nodeRenderer;
/*     */     } 
/* 332 */     return super.getCellRenderer(row, col, node);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Object getTooltipContentFromRow(int row, int column) {
/* 337 */     TreeTableNode node = getNodeFromRow(row);
/* 338 */     if (node != null) {
/* 339 */       return node.getTooltipContent(column);
/*     */     }
/* 341 */     return null;
/*     */   }
/*     */   
/*     */   private boolean updateParentSizes(NodeState ns) {
/* 345 */     while (ns.expanded && ns.parent != null) {
/* 346 */       NodeState parent = ns.parent;
/* 347 */       int idx = ((TreeTableNode)parent.key).getChildIndex((TreeTableNode)ns.key);
/* 348 */       assert parent.childSizes.size() == ((TreeTableNode)parent.key).getNumChildren();
/* 349 */       parent.childSizes.setSize(idx, ns.getChildRows() + 1);
/* 350 */       ns = parent;
/*     */     } 
/* 352 */     this.numRows = computeNumRows();
/* 353 */     return (ns.parent == null);
/*     */   }
/*     */   
/*     */   protected void modelNodesAdded(TreeTableNode parent, int idx, int count) {
/* 357 */     NodeState ns = (NodeState)HashEntry.get((HashEntry[])this.nodeStateTable, parent);
/*     */     
/* 359 */     if (ns != null) {
/* 360 */       if (ns.childSizes != null) {
/* 361 */         assert idx <= ns.childSizes.size();
/* 362 */         ns.childSizes.insert(idx, count);
/* 363 */         assert ns.childSizes.size() == parent.getNumChildren();
/*     */       } 
/* 365 */       if (ns.children != null) {
/* 366 */         NodeState[] newChilds = new NodeState[parent.getNumChildren()];
/* 367 */         System.arraycopy(ns.children, 0, newChilds, 0, idx);
/* 368 */         System.arraycopy(ns.children, idx, newChilds, idx + count, ns.children.length - idx);
/* 369 */         ns.children = newChilds;
/*     */       } 
/* 371 */       if (updateParentSizes(ns)) {
/* 372 */         int row = getRowFromNode(parent.getChild(idx));
/* 373 */         assert row < this.numRows;
/* 374 */         modelRowsInserted(row, count);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void recursiveRemove(NodeState ns) {
/* 380 */     if (ns != null) {
/* 381 */       this.nodeStateTableSize--;
/* 382 */       HashEntry.remove((HashEntry[])this.nodeStateTable, ns);
/* 383 */       if (ns.children != null) {
/* 384 */         for (NodeState nsChild : ns.children) {
/* 385 */           recursiveRemove(nsChild);
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void modelNodesRemoved(TreeTableNode parent, int idx, int count) {
/* 392 */     NodeState ns = (NodeState)HashEntry.get((HashEntry[])this.nodeStateTable, parent);
/*     */     
/* 394 */     if (ns != null) {
/* 395 */       int rowsBase = getRowFromNode(parent) + 1;
/* 396 */       int rowsStart = rowsBase + idx;
/* 397 */       int rowsEnd = rowsBase + idx + count;
/* 398 */       if (ns.childSizes != null) {
/* 399 */         assert ns.childSizes.size() == parent.getNumChildren() + count;
/* 400 */         rowsStart = rowsBase + ns.childSizes.getPosition(idx);
/* 401 */         rowsEnd = rowsBase + ns.childSizes.getPosition(idx + count);
/* 402 */         ns.childSizes.remove(idx, count);
/* 403 */         assert ns.childSizes.size() == parent.getNumChildren();
/*     */       } 
/* 405 */       if (ns.children != null) {
/* 406 */         for (int i = 0; i < count; i++) {
/* 407 */           recursiveRemove(ns.children[idx + i]);
/*     */         }
/* 409 */         int numChildren = parent.getNumChildren();
/* 410 */         if (numChildren > 0) {
/* 411 */           NodeState[] newChilds = new NodeState[numChildren];
/* 412 */           System.arraycopy(ns.children, 0, newChilds, 0, idx);
/* 413 */           System.arraycopy(ns.children, idx + count, newChilds, idx, newChilds.length - idx);
/* 414 */           ns.children = newChilds;
/*     */         } else {
/* 416 */           ns.children = null;
/*     */         } 
/*     */       } 
/* 419 */       if (updateParentSizes(ns)) {
/* 420 */         modelRowsDeleted(rowsStart, rowsEnd - rowsStart);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean isVisible(NodeState ns) {
/* 426 */     while (ns.expanded && ns.parent != null) {
/* 427 */       ns = ns.parent;
/*     */     }
/* 429 */     return ns.expanded;
/*     */   } public static interface ExpandListener {
/*     */     void nodeExpanded(int param1Int, TreeTableNode param1TreeTableNode); void nodeCollapsed(int param1Int, TreeTableNode param1TreeTableNode); }
/*     */   protected void modelNodesChanged(TreeTableNode parent, int idx, int count) {
/* 433 */     NodeState ns = (NodeState)HashEntry.get((HashEntry[])this.nodeStateTable, parent);
/*     */     
/* 435 */     if (ns != null && isVisible(ns)) {
/* 436 */       int rowsBase = getRowFromNode(parent) + 1;
/* 437 */       int rowsStart = rowsBase + idx;
/* 438 */       int rowsEnd = rowsBase + idx + count;
/* 439 */       if (ns.childSizes != null) {
/* 440 */         rowsStart = rowsBase + ns.childSizes.getPosition(idx);
/* 441 */         rowsEnd = rowsBase + ns.childSizes.getPosition(idx + count);
/*     */       } 
/* 443 */       modelRowsChanged(rowsStart, rowsEnd - rowsStart);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected class ModelChangeListener implements TreeTableModel.ChangeListener {
/*     */     public void nodesAdded(TreeTableNode parent, int idx, int count) {
/* 449 */       TreeTable.this.modelNodesAdded(parent, idx, count);
/*     */     }
/*     */     public void nodesRemoved(TreeTableNode parent, int idx, int count) {
/* 452 */       TreeTable.this.modelNodesRemoved(parent, idx, count);
/*     */     }
/*     */     public void nodesChanged(TreeTableNode parent, int idx, int count) {
/* 455 */       TreeTable.this.modelNodesChanged(parent, idx, count);
/*     */     }
/*     */     public void columnInserted(int idx, int count) {
/* 458 */       TreeTable.this.numColumns = TreeTable.this.model.getNumColumns();
/* 459 */       TreeTable.this.modelColumnsInserted(idx, count);
/*     */     }
/*     */     public void columnDeleted(int idx, int count) {
/* 462 */       TreeTable.this.numColumns = TreeTable.this.model.getNumColumns();
/* 463 */       TreeTable.this.modelColumnsDeleted(idx, count);
/*     */     }
/*     */     public void columnHeaderChanged(int column) {
/* 466 */       TreeTable.this.modelColumnHeaderChanged(column);
/*     */     }
/*     */   }
/*     */   
/*     */   protected class NodeState
/*     */     extends HashEntry<TreeTableNode, NodeState> implements BooleanModel {
/*     */     final NodeState parent;
/*     */     boolean expanded;
/*     */     boolean hasNoChildren;
/*     */     SizeSequence childSizes;
/*     */     NodeState[] children;
/*     */     Runnable[] callbacks;
/*     */     int level;
/*     */     
/*     */     public NodeState(TreeTableNode key, NodeState parent) {
/* 481 */       super(key);
/* 482 */       this.parent = parent;
/* 483 */       this.level = (parent != null) ? (parent.level + 1) : 0;
/*     */       
/* 485 */       if (parent != null) {
/* 486 */         if (parent.children == null) {
/* 487 */           parent.children = new NodeState[((TreeTableNode)parent.key).getNumChildren()];
/*     */         }
/* 489 */         parent.children[((TreeTableNode)parent.key).getChildIndex(key)] = this;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void addCallback(Runnable callback) {
/* 494 */       this.callbacks = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.callbacks, callback, Runnable.class);
/*     */     }
/*     */     
/*     */     public void removeCallback(Runnable callback) {
/* 498 */       this.callbacks = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, callback);
/*     */     }
/*     */     
/*     */     public boolean getValue() {
/* 502 */       return this.expanded;
/*     */     }
/*     */     
/*     */     public void setValue(boolean value) {
/* 506 */       if (this.expanded != value) {
/* 507 */         this.expanded = value;
/* 508 */         TreeTable.this.expandedChanged(this);
/* 509 */         CallbackSupport.fireCallbacks(this.callbacks);
/*     */       } 
/*     */     }
/*     */     
/*     */     void initChildSizes() {
/* 514 */       this.childSizes = new SizeSequence();
/* 515 */       this.childSizes.setDefaultValue(1);
/* 516 */       this.childSizes.initializeAll(((TreeTableNode)this.key).getNumChildren());
/*     */     }
/*     */     
/*     */     int getChildRows() {
/* 520 */       if (this.childSizes != null) {
/* 521 */         return this.childSizes.getEndPosition();
/*     */       }
/* 523 */       int childCount = ((TreeTableNode)this.key).getNumChildren();
/* 524 */       this.hasNoChildren = (childCount == 0);
/* 525 */       return childCount;
/*     */     }
/*     */     
/*     */     boolean hasNoChildren() {
/* 529 */       return this.hasNoChildren;
/*     */     }
/*     */   }
/*     */   
/*     */   static int getLevel(TreeTableNode node) {
/* 534 */     int level = -2;
/* 535 */     while (node != null) {
/* 536 */       level++;
/* 537 */       node = node.getParent();
/*     */     } 
/* 539 */     return level;
/*     */   }
/*     */   
/*     */   class TreeLeafCellRenderer implements TableBase.CellRenderer, TableBase.CellWidgetCreator {
/*     */     protected int treeIndent;
/*     */     protected int level;
/* 545 */     protected Dimension treeButtonSize = new Dimension(5, 5);
/*     */     protected TableBase.CellRenderer subRenderer;
/*     */     
/*     */     public TreeLeafCellRenderer() {
/* 549 */       TreeTable.this.setClip(true);
/*     */     }
/*     */     
/*     */     public void applyTheme(ThemeInfo themeInfo) {
/* 553 */       this.treeIndent = themeInfo.getParameter("treeIndent", 10);
/* 554 */       this.treeButtonSize = themeInfo.<Dimension>getParameterValue("treeButtonSize", true, Dimension.class, Dimension.ZERO);
/*     */     }
/*     */     
/*     */     public String getTheme() {
/* 558 */       return getClass().getSimpleName();
/*     */     }
/*     */     
/*     */     public void setCellData(int row, int column, Object data) {
/* 562 */       throw new UnsupportedOperationException("Don't call this method");
/*     */     }
/*     */     
/*     */     public void setCellData(int row, int column, Object data, TreeTableNode node) {
/* 566 */       this.level = TreeTable.getLevel(node);
/* 567 */       setSubRenderer(row, column, data);
/*     */     }
/*     */     
/*     */     protected int getIndentation() {
/* 571 */       return this.level * this.treeIndent + this.treeButtonSize.getX();
/*     */     }
/*     */     
/*     */     protected void setSubRenderer(int row, int column, Object colData) {
/* 575 */       this.subRenderer = TreeTable.this.getCellRenderer(colData, column);
/* 576 */       if (this.subRenderer != null) {
/* 577 */         this.subRenderer.setCellData(row, column, colData);
/*     */       }
/*     */     }
/*     */     
/*     */     public int getColumnSpan() {
/* 582 */       return (this.subRenderer != null) ? this.subRenderer.getColumnSpan() : 1;
/*     */     }
/*     */     
/*     */     public int getPreferredHeight() {
/* 586 */       if (this.subRenderer != null) {
/* 587 */         return Math.max(this.treeButtonSize.getY(), this.subRenderer.getPreferredHeight());
/*     */       }
/* 589 */       return this.treeButtonSize.getY();
/*     */     }
/*     */     
/*     */     public Widget getCellRenderWidget(int x, int y, int width, int height, boolean isSelected) {
/* 593 */       if (this.subRenderer != null) {
/* 594 */         int indent = getIndentation();
/* 595 */         Widget widget = this.subRenderer.getCellRenderWidget(x + indent, y, Math.max(0, width - indent), height, isSelected);
/*     */         
/* 597 */         return widget;
/*     */       } 
/* 599 */       return null;
/*     */     }
/*     */     
/*     */     public Widget updateWidget(Widget existingWidget) {
/* 603 */       if (this.subRenderer instanceof TableBase.CellWidgetCreator) {
/* 604 */         TableBase.CellWidgetCreator subCreator = (TableBase.CellWidgetCreator)this.subRenderer;
/* 605 */         return subCreator.updateWidget(existingWidget);
/*     */       } 
/* 607 */       return null;
/*     */     }
/*     */     
/*     */     public void positionWidget(Widget widget, int x, int y, int w, int h) {
/* 611 */       if (this.subRenderer instanceof TableBase.CellWidgetCreator) {
/* 612 */         TableBase.CellWidgetCreator subCreator = (TableBase.CellWidgetCreator)this.subRenderer;
/* 613 */         int indent = this.level * this.treeIndent;
/* 614 */         subCreator.positionWidget(widget, x + indent, y, Math.max(0, w - indent), h);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class WidgetChain extends Widget {
/*     */     final ToggleButton expandButton;
/*     */     Widget userWidget;
/*     */     
/*     */     WidgetChain() {
/* 624 */       setTheme("");
/* 625 */       this.expandButton = new ToggleButton();
/* 626 */       this.expandButton.setTheme("treeButton");
/* 627 */       add(this.expandButton);
/*     */     }
/*     */     
/*     */     void setUserWidget(Widget userWidget) {
/* 631 */       if (this.userWidget != userWidget) {
/* 632 */         if (this.userWidget != null) {
/* 633 */           removeChild(1);
/*     */         }
/* 635 */         this.userWidget = userWidget;
/* 636 */         if (userWidget != null)
/* 637 */           insertChild(userWidget, 1); 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class TreeNodeCellRenderer
/*     */     extends TreeLeafCellRenderer
/*     */   {
/*     */     private TreeTable.NodeState nodeState;
/*     */     
/*     */     public Widget updateWidget(Widget existingWidget) {
/* 648 */       if (this.subRenderer instanceof TableBase.CellWidgetCreator) {
/* 649 */         TableBase.CellWidgetCreator subCreator = (TableBase.CellWidgetCreator)this.subRenderer;
/* 650 */         TreeTable.WidgetChain widgetChain = null;
/* 651 */         if (existingWidget instanceof TreeTable.WidgetChain) {
/* 652 */           widgetChain = (TreeTable.WidgetChain)existingWidget;
/*     */         }
/* 654 */         if (this.nodeState.hasNoChildren()) {
/* 655 */           if (widgetChain != null) {
/* 656 */             existingWidget = null;
/*     */           }
/* 658 */           return subCreator.updateWidget(existingWidget);
/*     */         } 
/* 660 */         if (widgetChain == null) {
/* 661 */           widgetChain = new TreeTable.WidgetChain();
/*     */         }
/* 663 */         widgetChain.expandButton.setModel(this.nodeState);
/* 664 */         widgetChain.setUserWidget(subCreator.updateWidget(widgetChain.userWidget));
/* 665 */         return widgetChain;
/*     */       } 
/* 667 */       if (this.nodeState.hasNoChildren()) {
/* 668 */         return null;
/*     */       }
/* 670 */       ToggleButton tb = (ToggleButton)existingWidget;
/* 671 */       if (tb == null) {
/* 672 */         tb = new ToggleButton();
/* 673 */         tb.setTheme("treeButton");
/*     */       } 
/* 675 */       tb.setModel(this.nodeState);
/* 676 */       return tb;
/*     */     }
/*     */ 
/*     */     
/*     */     public void positionWidget(Widget widget, int x, int y, int w, int h) {
/* 681 */       int indent = this.level * this.treeIndent;
/* 682 */       int availWidth = Math.max(0, w - indent);
/* 683 */       int expandButtonWidth = Math.min(availWidth, this.treeButtonSize.getX());
/* 684 */       widget.setPosition(x + indent, y + (h - this.treeButtonSize.getY()) / 2);
/* 685 */       if (this.subRenderer instanceof TableBase.CellWidgetCreator) {
/* 686 */         TableBase.CellWidgetCreator subCreator = (TableBase.CellWidgetCreator)this.subRenderer;
/* 687 */         TreeTable.WidgetChain widgetChain = (TreeTable.WidgetChain)widget;
/* 688 */         ToggleButton expandButton = widgetChain.expandButton;
/* 689 */         widgetChain.setSize(Math.max(0, w - indent), h);
/* 690 */         expandButton.setSize(expandButtonWidth, this.treeButtonSize.getY());
/* 691 */         if (widgetChain.userWidget != null) {
/* 692 */           subCreator.positionWidget(widgetChain.userWidget, expandButton.getRight(), y, widget.getWidth(), h);
/*     */         }
/*     */       } else {
/*     */         
/* 696 */         widget.setSize(expandButtonWidth, this.treeButtonSize.getY());
/*     */       } 
/*     */     }
/*     */     
/*     */     public void setCellData(int row, int column, Object data, TreeTable.NodeState nodeState) {
/* 701 */       assert nodeState != null;
/* 702 */       this.nodeState = nodeState;
/* 703 */       setSubRenderer(row, column, data);
/* 704 */       this.level = nodeState.level;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\TreeTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */