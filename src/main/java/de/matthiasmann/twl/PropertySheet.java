/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.AbstractListModel;
/*     */ import de.matthiasmann.twl.model.AbstractTreeTableModel;
/*     */ import de.matthiasmann.twl.model.AbstractTreeTableNode;
/*     */ import de.matthiasmann.twl.model.ListModel;
/*     */ import de.matthiasmann.twl.model.Property;
/*     */ import de.matthiasmann.twl.model.PropertyList;
/*     */ import de.matthiasmann.twl.model.SimplePropertyList;
/*     */ import de.matthiasmann.twl.model.TreeTableModel;
/*     */ import de.matthiasmann.twl.model.TreeTableNode;
/*     */ import de.matthiasmann.twl.utils.TypeMapping;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PropertySheet
/*     */   extends TreeTable
/*     */ {
/*     */   private final SimplePropertyList rootList;
/*     */   private final PropertyListCellRenderer subListRenderer;
/*     */   private final TableBase.CellRenderer editorRenderer;
/*     */   private final TypeMapping<PropertyEditorFactory<?>> factories;
/*     */   
/*     */   public PropertySheet() {
/*  85 */     this(new Model());
/*     */   }
/*     */ 
/*     */   
/*     */   private PropertySheet(Model model) {
/*  90 */     super((TreeTableModel)model);
/*  91 */     this.rootList = new SimplePropertyList("<root>");
/*  92 */     this.subListRenderer = new PropertyListCellRenderer();
/*  93 */     this.editorRenderer = new EditorRenderer();
/*  94 */     this.factories = new TypeMapping();
/*  95 */     this.rootList.addValueChangedCallback(new TreeGenerator((PropertyList)this.rootList, model));
/*  96 */     registerPropertyEditorFactory(String.class, new StringEditorFactory());
/*     */   }
/*     */   
/*     */   public SimplePropertyList getPropertyList() {
/* 100 */     return this.rootList;
/*     */   }
/*     */   
/*     */   public <T> void registerPropertyEditorFactory(Class<T> clazz, PropertyEditorFactory<T> factory) {
/* 104 */     if (clazz == null) {
/* 105 */       throw new NullPointerException("clazz");
/*     */     }
/* 107 */     if (factory == null) {
/* 108 */       throw new NullPointerException("factory");
/*     */     }
/* 110 */     this.factories.put(clazz, factory);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModel(TreeTableModel model) {
/* 115 */     if (model instanceof Model) {
/* 116 */       super.setModel(model);
/*     */     } else {
/* 118 */       throw new UnsupportedOperationException("Do not call this method");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 124 */     super.applyTheme(themeInfo);
/* 125 */     applyThemePropertiesSheet(themeInfo);
/*     */   }
/*     */   
/*     */   protected void applyThemePropertiesSheet(ThemeInfo themeInfo) {
/* 129 */     applyCellRendererTheme(this.subListRenderer);
/* 130 */     applyCellRendererTheme(this.editorRenderer);
/*     */   } public static interface PropertyEditor {
/*     */     Widget getWidget(); void valueChanged(); void preDestroy(); void setSelected(boolean param1Boolean);
/*     */     boolean positionWidget(int param1Int1, int param1Int2, int param1Int3, int param1Int4); }
/*     */   protected TableBase.CellRenderer getCellRenderer(int row, int col, TreeTableNode node) {
/* 135 */     if (node == null) {
/* 136 */       node = getNodeFromRow(row);
/*     */     }
/* 138 */     if (node instanceof ListNode) {
/* 139 */       if (col == 0) {
/* 140 */         PropertyListCellRenderer propertyListCellRenderer = this.subListRenderer;
/* 141 */         TreeTable.NodeState nodeState = getOrCreateNodeState(node);
/* 142 */         propertyListCellRenderer.setCellData(row, col, node.getData(col), nodeState);
/* 143 */         return propertyListCellRenderer;
/*     */       } 
/* 145 */       return null;
/*     */     } 
/* 147 */     if (col == 0) {
/* 148 */       return super.getCellRenderer(row, col, node);
/*     */     }
/* 150 */     TableBase.CellRenderer cr = this.editorRenderer;
/* 151 */     cr.setCellData(row, col, node.getData(col));
/* 152 */     return cr;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   TreeTableNode createNode(TreeTableNode parent, Property<?> property) {
/* 158 */     if (property.getType() == PropertyList.class) {
/* 159 */       return new ListNode(parent, property);
/*     */     }
/* 161 */     Class<?> type = property.getType();
/* 162 */     PropertyEditorFactory<?> factory = (PropertyEditorFactory)this.factories.get(type);
/* 163 */     if (factory != null) {
/* 164 */       PropertyEditor editor = factory.createEditor(property);
/* 165 */       if (editor != null) {
/* 166 */         return new LeafNode(parent, property, editor);
/*     */       }
/*     */     } else {
/* 169 */       Logger.getLogger(PropertySheet.class.getName()).log(Level.WARNING, "No property editor factory for type {0}", type);
/*     */     } 
/* 171 */     return null;
/*     */   }
/*     */   public static interface PropertyEditorFactory<T> {
/*     */     PropertySheet.PropertyEditor createEditor(Property<T> param1Property); }
/*     */   
/*     */   static interface PSTreeTableNode extends TreeTableNode {
/*     */     void addChild(TreeTableNode param1TreeTableNode);
/*     */     
/*     */     void removeAllChildren(); }
/*     */   
/*     */   static abstract class PropertyNode extends AbstractTreeTableNode implements Runnable, PSTreeTableNode { protected final Property<?> property;
/*     */     
/*     */     public PropertyNode(TreeTableNode parent, Property<?> property) {
/* 184 */       super(parent);
/* 185 */       this.property = property;
/* 186 */       property.addValueChangedCallback(this);
/*     */     }
/*     */     protected void removeCallback() {
/* 189 */       this.property.removeValueChangedCallback(this);
/*     */     }
/*     */     
/*     */     public void removeAllChildren() {
/* 193 */       super.removeAllChildren();
/*     */     }
/*     */     public void addChild(TreeTableNode parent) {
/* 196 */       insertChild(parent, getNumChildren());
/*     */     } }
/*     */ 
/*     */   
/*     */   class TreeGenerator implements Runnable {
/*     */     private final PropertyList list;
/*     */     private final PropertySheet.PSTreeTableNode parent;
/*     */     
/*     */     public TreeGenerator(PropertyList list, PropertySheet.PSTreeTableNode parent) {
/* 205 */       this.list = list;
/* 206 */       this.parent = parent;
/*     */     }
/*     */     public void run() {
/* 209 */       this.parent.removeAllChildren();
/* 210 */       addSubProperties();
/*     */     }
/*     */     void removeChildCallbacks(PropertySheet.PSTreeTableNode parent) {
/* 213 */       for (int i = 0, n = parent.getNumChildren(); i < n; i++)
/* 214 */         ((PropertySheet.PropertyNode)parent.getChild(i)).removeCallback(); 
/*     */     }
/*     */     
/*     */     void addSubProperties() {
/* 218 */       for (int i = 0; i < this.list.getNumProperties(); i++) {
/* 219 */         TreeTableNode node = PropertySheet.this.createNode(this.parent, this.list.getProperty(i));
/* 220 */         if (node != null)
/* 221 */           this.parent.addChild(node); 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class LeafNode
/*     */     extends PropertyNode {
/*     */     private final PropertySheet.PropertyEditor editor;
/*     */     
/*     */     public LeafNode(TreeTableNode parent, Property<?> property, PropertySheet.PropertyEditor editor) {
/* 231 */       super(parent, property);
/* 232 */       this.editor = editor;
/* 233 */       setLeaf(true);
/*     */     }
/*     */     public Object getData(int column) {
/* 236 */       switch (column) { case 0:
/* 237 */           return this.property.getName();
/* 238 */         case 1: return this.editor; }
/* 239 */        return "???";
/*     */     }
/*     */     
/*     */     public void run() {
/* 243 */       this.editor.valueChanged();
/* 244 */       fireNodeChanged();
/*     */     }
/*     */   }
/*     */   
/*     */   class ListNode extends PropertyNode {
/*     */     protected final PropertySheet.TreeGenerator treeGenerator;
/*     */     
/*     */     public ListNode(TreeTableNode parent, Property<?> property) {
/* 252 */       super(parent, property);
/* 253 */       this.treeGenerator = new PropertySheet.TreeGenerator((PropertyList)property.getPropertyValue(), this);
/*     */       
/* 255 */       this.treeGenerator.run();
/*     */     }
/*     */     public Object getData(int column) {
/* 258 */       return this.property.getName();
/*     */     }
/*     */     public void run() {
/* 261 */       this.treeGenerator.run();
/*     */     }
/*     */     
/*     */     protected void removeCallback() {
/* 265 */       super.removeCallback();
/* 266 */       this.treeGenerator.removeChildCallbacks(this);
/*     */     }
/*     */   }
/*     */   
/*     */   class PropertyListCellRenderer extends TreeTable.TreeNodeCellRenderer {
/*     */     private final Widget bgRenderer;
/*     */     private final Label textRenderer;
/*     */     
/*     */     public PropertyListCellRenderer() {
/* 275 */       this.bgRenderer = new Widget();
/* 276 */       this.textRenderer = new Label(this.bgRenderer.getAnimationState());
/* 277 */       this.textRenderer.setAutoSize(false);
/* 278 */       this.bgRenderer.add(this.textRenderer);
/* 279 */       this.bgRenderer.setTheme(getTheme());
/*     */     }
/*     */     
/*     */     public int getColumnSpan() {
/* 283 */       return 2;
/*     */     }
/*     */     
/*     */     public Widget getCellRenderWidget(int x, int y, int width, int height, boolean isSelected) {
/* 287 */       this.bgRenderer.setPosition(x, y);
/* 288 */       this.bgRenderer.setSize(width, height);
/* 289 */       int indent = getIndentation();
/* 290 */       this.textRenderer.setPosition(x + indent, y);
/* 291 */       this.textRenderer.setSize(Math.max(0, width - indent), height);
/* 292 */       this.bgRenderer.getAnimationState().setAnimationState(TableBase.STATE_SELECTED, isSelected);
/* 293 */       return this.bgRenderer;
/*     */     }
/*     */     
/*     */     public void setCellData(int row, int column, Object data, TreeTable.NodeState nodeState) {
/* 297 */       super.setCellData(row, column, data, nodeState);
/* 298 */       this.textRenderer.setText((String)data);
/*     */     }
/*     */     
/*     */     protected void setSubRenderer(int row, int column, Object colData) {}
/*     */   }
/*     */   
/*     */   static class EditorRenderer
/*     */     implements TableBase.CellRenderer, TableBase.CellWidgetCreator {
/*     */     private PropertySheet.PropertyEditor editor;
/*     */     
/*     */     public void applyTheme(ThemeInfo themeInfo) {}
/*     */     
/*     */     public Widget getCellRenderWidget(int x, int y, int width, int height, boolean isSelected) {
/* 311 */       this.editor.setSelected(isSelected);
/* 312 */       return null;
/*     */     }
/*     */     public int getColumnSpan() {
/* 315 */       return 1;
/*     */     }
/*     */     public int getPreferredHeight() {
/* 318 */       return this.editor.getWidget().getPreferredHeight();
/*     */     }
/*     */     public String getTheme() {
/* 321 */       return "PropertyEditorCellRender";
/*     */     }
/*     */     public void setCellData(int row, int column, Object data) {
/* 324 */       this.editor = (PropertySheet.PropertyEditor)data;
/*     */     }
/*     */     public Widget updateWidget(Widget existingWidget) {
/* 327 */       return this.editor.getWidget();
/*     */     }
/*     */     public void positionWidget(Widget widget, int x, int y, int w, int h) {
/* 330 */       if (!this.editor.positionWidget(x, y, w, h)) {
/* 331 */         widget.setPosition(x, y);
/* 332 */         widget.setSize(w, h);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class Model extends AbstractTreeTableModel implements PSTreeTableNode {
/*     */     public String getColumnHeaderText(int column) {
/* 339 */       switch (column) { case 0:
/* 340 */           return "Name";
/* 341 */         case 1: return "Value"; }
/* 342 */        return "???";
/*     */     }
/*     */     
/*     */     public int getNumColumns() {
/* 346 */       return 2;
/*     */     }
/*     */     
/*     */     public void removeAllChildren() {
/* 350 */       super.removeAllChildren();
/*     */     }
/*     */     public void addChild(TreeTableNode parent) {
/* 353 */       insertChild(parent, getNumChildren());
/*     */     }
/*     */   }
/*     */   
/*     */   static class StringEditor
/*     */     implements PropertyEditor, EditField.Callback {
/*     */     private final EditField editField;
/*     */     private final Property<String> property;
/*     */     
/*     */     public StringEditor(Property<String> property) {
/* 363 */       this.property = property;
/* 364 */       this.editField = new EditField();
/* 365 */       this.editField.addCallback(this);
/* 366 */       resetValue();
/*     */     }
/*     */     public Widget getWidget() {
/* 369 */       return this.editField;
/*     */     }
/*     */     public void valueChanged() {
/* 372 */       resetValue();
/*     */     }
/*     */     public void preDestroy() {
/* 375 */       this.editField.removeCallback(this);
/*     */     }
/*     */     public void setSelected(boolean selected) {}
/*     */     
/*     */     public void callback(int key) {
/* 380 */       if (key == 1) {
/* 381 */         resetValue();
/* 382 */       } else if (!this.property.isReadOnly()) {
/*     */         try {
/* 384 */           this.property.setPropertyValue(this.editField.getText());
/* 385 */           this.editField.setErrorMessage(null);
/* 386 */         } catch (IllegalArgumentException ex) {
/* 387 */           this.editField.setErrorMessage(ex.getMessage());
/*     */         } 
/*     */       } 
/*     */     }
/*     */     private void resetValue() {
/* 392 */       this.editField.setText((String)this.property.getPropertyValue());
/* 393 */       this.editField.setErrorMessage(null);
/* 394 */       this.editField.setReadOnly(this.property.isReadOnly());
/*     */     }
/*     */     public boolean positionWidget(int x, int y, int width, int height) {
/* 397 */       return false;
/*     */     } }
/*     */   
/*     */   static class StringEditorFactory implements PropertyEditorFactory<String> {
/*     */     public PropertySheet.PropertyEditor createEditor(Property<String> property) {
/* 402 */       return new PropertySheet.StringEditor(property);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ComboBoxEditor<T>
/*     */     implements PropertyEditor, Runnable {
/*     */     protected final ComboBox<T> comboBox;
/*     */     protected final Property<T> property;
/*     */     protected final ListModel<T> model;
/*     */     
/*     */     public ComboBoxEditor(Property<T> property, ListModel<T> model) {
/* 413 */       this.property = property;
/* 414 */       this.comboBox = new ComboBox<T>(model);
/* 415 */       this.model = model;
/* 416 */       this.comboBox.addCallback(this);
/* 417 */       resetValue();
/*     */     }
/*     */     public Widget getWidget() {
/* 420 */       return this.comboBox;
/*     */     }
/*     */     public void valueChanged() {
/* 423 */       resetValue();
/*     */     }
/*     */     public void preDestroy() {
/* 426 */       this.comboBox.removeCallback(this);
/*     */     }
/*     */     public void setSelected(boolean selected) {}
/*     */     
/*     */     public void run() {
/* 431 */       if (this.property.isReadOnly()) {
/* 432 */         resetValue();
/*     */       } else {
/* 434 */         int idx = this.comboBox.getSelected();
/* 435 */         if (idx >= 0)
/* 436 */           this.property.setPropertyValue(this.model.getEntry(idx)); 
/*     */       } 
/*     */     }
/*     */     
/*     */     protected void resetValue() {
/* 441 */       this.comboBox.setSelected(findEntry((T)this.property.getPropertyValue()));
/*     */     }
/*     */     protected int findEntry(T value) {
/* 444 */       for (int i = 0, n = this.model.getNumEntries(); i < n; i++) {
/* 445 */         if (this.model.getEntry(i).equals(value)) {
/* 446 */           return i;
/*     */         }
/*     */       } 
/* 449 */       return -1;
/*     */     }
/*     */     public boolean positionWidget(int x, int y, int width, int height) {
/* 452 */       return false;
/*     */     } }
/*     */   
/*     */   public static class ComboBoxEditorFactory<T> implements PropertyEditorFactory<T> { private final ModelForwarder modelForwarder;
/*     */     
/*     */     public ComboBoxEditorFactory(ListModel<T> model) {
/* 458 */       this.modelForwarder = new ModelForwarder(model);
/*     */     }
/*     */     public ListModel<T> getModel() {
/* 461 */       return this.modelForwarder.getModel();
/*     */     }
/*     */     public void setModel(ListModel<T> model) {
/* 464 */       this.modelForwarder.setModel(model);
/*     */     }
/*     */     public PropertySheet.PropertyEditor createEditor(Property<T> property) {
/* 467 */       return new PropertySheet.ComboBoxEditor<T>(property, (ListModel<T>)this.modelForwarder);
/*     */     }
/*     */     
/*     */     class ModelForwarder extends AbstractListModel<T> implements ListModel.ChangeListener { private ListModel<T> model;
/*     */       
/*     */       public ModelForwarder(ListModel<T> model) {
/* 473 */         setModel(model);
/*     */       }
/*     */       public int getNumEntries() {
/* 476 */         return this.model.getNumEntries();
/*     */       }
/*     */       public T getEntry(int index) {
/* 479 */         return (T)this.model.getEntry(index);
/*     */       }
/*     */       public Object getEntryTooltip(int index) {
/* 482 */         return this.model.getEntryTooltip(index);
/*     */       }
/*     */       public boolean matchPrefix(int index, String prefix) {
/* 485 */         return this.model.matchPrefix(index, prefix);
/*     */       }
/*     */       public ListModel<T> getModel() {
/* 488 */         return this.model;
/*     */       }
/*     */       public void setModel(ListModel<T> model) {
/* 491 */         if (this.model != null) {
/* 492 */           this.model.removeChangeListener(this);
/*     */         }
/* 494 */         this.model = model;
/* 495 */         this.model.addChangeListener(this);
/* 496 */         fireAllChanged();
/*     */       }
/*     */       public void entriesInserted(int first, int last) {
/* 499 */         fireEntriesInserted(first, last);
/*     */       }
/*     */       public void entriesDeleted(int first, int last) {
/* 502 */         fireEntriesDeleted(first, last);
/*     */       }
/*     */       public void entriesChanged(int first, int last) {
/* 505 */         fireEntriesChanged(first, last);
/*     */       }
/*     */       public void allChanged() {
/* 508 */         fireAllChanged();
/*     */       } }
/*     */      }
/*     */ 
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\PropertySheet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */