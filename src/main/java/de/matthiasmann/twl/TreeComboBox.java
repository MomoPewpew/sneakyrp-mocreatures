/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.TableSelectionModel;
/*     */ import de.matthiasmann.twl.model.TableSingleSelectionModel;
/*     */ import de.matthiasmann.twl.model.TreeTableModel;
/*     */ import de.matthiasmann.twl.model.TreeTableNode;
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
/*     */ public class TreeComboBox
/*     */   extends ComboBoxBase
/*     */ {
/*     */   private static final String DEFAULT_POPUP_THEME = "treecomboboxPopup";
/*     */   final TableSingleSelectionModel selectionModel;
/*     */   final TreePathDisplay display;
/*     */   final TreeTable table;
/*     */   private TreeTableModel model;
/*     */   private Callback[] callbacks;
/*     */   private PathResolver pathResolver;
/*     */   private boolean suppressCallback;
/*     */   boolean suppressTreeSelectionUpdating;
/*     */   
/*     */   public TreeComboBox() {
/*  81 */     this.selectionModel = new TableSingleSelectionModel();
/*  82 */     this.display = new TreePathDisplay();
/*  83 */     this.display.setTheme("display");
/*  84 */     this.table = new TreeTable();
/*  85 */     this.table.setSelectionManager(new TableRowSelectionManager((TableSelectionModel)this.selectionModel)
/*     */         {
/*     */           protected boolean handleMouseClick(int row, int column, boolean isShift, boolean isCtrl) {
/*  88 */             if (!isShift && !isCtrl && row >= 0 && row < getNumRows()) {
/*  89 */               TreeComboBox.this.popup.closePopup();
/*  90 */               return true;
/*     */             } 
/*  92 */             return super.handleMouseClick(row, column, isShift, isCtrl);
/*     */           }
/*     */         });
/*     */     
/*  96 */     this.display.addCallback(new TreePathDisplay.Callback() {
/*     */           public void pathElementClicked(TreeTableNode node, TreeTableNode child) {
/*  98 */             TreeComboBox.this.fireSelectedNodeChanged(node, child);
/*     */           }
/*     */           
/*     */           public boolean resolvePath(String path) {
/* 102 */             return TreeComboBox.this.resolvePath(path);
/*     */           }
/*     */         });
/*     */     
/* 106 */     this.selectionModel.addSelectionChangeListener(new Runnable() {
/*     */           public void run() {
/* 108 */             int row = TreeComboBox.this.selectionModel.getFirstSelected();
/* 109 */             if (row >= 0) {
/* 110 */               TreeComboBox.this.suppressTreeSelectionUpdating = true;
/*     */               try {
/* 112 */                 TreeComboBox.this.nodeChanged(TreeComboBox.this.table.getNodeFromRow(row));
/*     */               } finally {
/* 114 */                 TreeComboBox.this.suppressTreeSelectionUpdating = false;
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/*     */     
/* 120 */     ScrollPane scrollPane = new ScrollPane(this.table);
/* 121 */     scrollPane.setFixed(ScrollPane.Fixed.HORIZONTAL);
/*     */     
/* 123 */     add(this.display);
/* 124 */     this.popup.setTheme("treecomboboxPopup");
/* 125 */     this.popup.add(scrollPane);
/*     */   }
/*     */   
/*     */   public TreeComboBox(TreeTableModel model) {
/* 129 */     this();
/* 130 */     setModel(model);
/*     */   }
/*     */   
/*     */   public TreeTableModel getModel() {
/* 134 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(TreeTableModel model) {
/* 138 */     if (this.model != model) {
/* 139 */       this.model = model;
/* 140 */       this.table.setModel(model);
/* 141 */       this.display.setCurrentNode((TreeTableNode)model);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setCurrentNode(TreeTableNode node) {
/* 146 */     if (node == null) {
/* 147 */       throw new NullPointerException("node");
/*     */     }
/* 149 */     this.display.setCurrentNode(node);
/* 150 */     if (this.popup.isOpen()) {
/* 151 */       tableSelectToCurrentNode();
/*     */     }
/*     */   }
/*     */   
/*     */   public TreeTableNode getCurrentNode() {
/* 156 */     return this.display.getCurrentNode();
/*     */   }
/*     */   
/*     */   public void setSeparator(String separator) {
/* 160 */     this.display.setSeparator(separator);
/*     */   }
/*     */   
/*     */   public String getSeparator() {
/* 164 */     return this.display.getSeparator();
/*     */   }
/*     */   
/*     */   public PathResolver getPathResolver() {
/* 168 */     return this.pathResolver;
/*     */   }
/*     */   
/*     */   public void setPathResolver(PathResolver pathResolver) {
/* 172 */     this.pathResolver = pathResolver;
/* 173 */     this.display.setAllowEdit((pathResolver != null));
/*     */   }
/*     */   
/*     */   public TreeTable getTreeTable() {
/* 177 */     return this.table;
/*     */   }
/*     */   
/*     */   public EditField getEditField() {
/* 181 */     return this.display.getEditField();
/*     */   }
/*     */   
/*     */   public void addCallback(Callback callback) {
/* 185 */     this.callbacks = (Callback[])CallbackSupport.addCallbackToList((Object[])this.callbacks, callback, Callback.class);
/*     */   }
/*     */   
/*     */   public void removeCallback(Callback callback) {
/* 189 */     this.callbacks = (Callback[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, callback);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 194 */     super.applyTheme(themeInfo);
/* 195 */     applyTreeComboboxPopupThemeName(themeInfo);
/*     */   }
/*     */   
/*     */   protected void applyTreeComboboxPopupThemeName(ThemeInfo themeInfo) {
/* 199 */     this.popup.setTheme(themeInfo.getParameter("popupThemeName", "treecomboboxPopup"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Widget getLabel() {
/* 204 */     return this.display;
/*     */   }
/*     */   
/*     */   void fireSelectedNodeChanged(TreeTableNode node, TreeTableNode child) {
/* 208 */     if (this.callbacks != null) {
/* 209 */       for (Callback cb : this.callbacks) {
/* 210 */         cb.selectedNodeChanged(node, child);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   boolean resolvePath(String path) {
/* 216 */     if (this.pathResolver != null) {
/*     */       try {
/* 218 */         TreeTableNode node = this.pathResolver.resolvePath(this.model, path);
/* 219 */         assert node != null;
/* 220 */         nodeChanged(node);
/* 221 */         return true;
/* 222 */       } catch (IllegalArgumentException ex) {
/* 223 */         this.display.setEditErrorMessage(ex.getMessage());
/*     */       } 
/*     */     }
/* 226 */     return false;
/*     */   }
/*     */   
/*     */   void nodeChanged(TreeTableNode node) {
/* 230 */     TreeTableNode oldNode = this.display.getCurrentNode();
/* 231 */     this.display.setCurrentNode(node);
/* 232 */     if (!this.suppressCallback) {
/* 233 */       fireSelectedNodeChanged(node, getChildOf(node, oldNode));
/*     */     }
/*     */   }
/*     */   
/*     */   private TreeTableNode getChildOf(TreeTableNode parent, TreeTableNode node) {
/* 238 */     while (node != null && node != parent) {
/* 239 */       node = node.getParent();
/*     */     }
/* 241 */     return node;
/*     */   }
/*     */   
/*     */   private void tableSelectToCurrentNode() {
/* 245 */     if (!this.suppressTreeSelectionUpdating) {
/* 246 */       this.table.collapseAll();
/* 247 */       int idx = this.table.getRowFromNodeExpand(this.display.getCurrentNode());
/* 248 */       this.suppressCallback = true;
/*     */       try {
/* 250 */         this.selectionModel.setSelection(idx, idx);
/*     */       } finally {
/* 252 */         this.suppressCallback = false;
/*     */       } 
/* 254 */       this.table.scrollToRow(Math.max(0, idx));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean openPopup() {
/* 260 */     if (super.openPopup()) {
/* 261 */       this.popup.validateLayout();
/* 262 */       tableSelectToCurrentNode();
/* 263 */       return true;
/*     */     } 
/* 265 */     return false;
/*     */   }
/*     */   
/*     */   public static interface Callback {
/*     */     void selectedNodeChanged(TreeTableNode param1TreeTableNode1, TreeTableNode param1TreeTableNode2);
/*     */   }
/*     */   
/*     */   public static interface PathResolver {
/*     */     TreeTableNode resolvePath(TreeTableModel param1TreeTableModel, String param1String) throws IllegalArgumentException;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\TreeComboBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */