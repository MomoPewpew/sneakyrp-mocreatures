/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ColumnLayout
/*     */   extends DialogLayout
/*     */ {
/*     */   final ArrayList<DialogLayout.Group> columnGroups;
/*     */   private final Panel rootPanel;
/*     */   private final HashMap<Columns, Columns> columns;
/*     */   
/*     */   public ColumnLayout() {
/*  53 */     this.columnGroups = new ArrayList<DialogLayout.Group>();
/*  54 */     this.rootPanel = new Panel(null);
/*  55 */     this.columns = new HashMap<Columns, Columns>();
/*     */     
/*  57 */     setHorizontalGroup(createParallelGroup());
/*  58 */     setVerticalGroup(this.rootPanel.rows);
/*     */   }
/*     */   
/*     */   public final Panel getRootPanel() {
/*  62 */     return this.rootPanel;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public Columns getColumns(String... columnNames) {
/*  78 */     if (columnNames.length == 0) {
/*  79 */       throw new IllegalArgumentException("columnNames");
/*     */     }
/*  81 */     Columns key = new Columns(columnNames);
/*  82 */     Columns cl = this.columns.get(key);
/*  83 */     if (cl != null) {
/*  84 */       return cl;
/*     */     }
/*  86 */     createColumns(key);
/*  87 */     return key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Row addRow(Columns columns) {
/*  97 */     return this.rootPanel.addRow(columns);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Row addRow(String... columnNames) {
/* 108 */     return this.rootPanel.addRow(getColumns(columnNames));
/*     */   }
/*     */   
/*     */   private void createColumns(Columns cl) {
/* 112 */     int prefixSize = 0;
/* 113 */     Columns prefixColumns = null;
/* 114 */     for (Columns c : this.columns.values()) {
/* 115 */       int match = c.match(cl);
/* 116 */       if (match > prefixSize) {
/* 117 */         prefixSize = match;
/* 118 */         prefixColumns = c;
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     int numColumns = 0;
/* 123 */     for (int i = 0, n = cl.names.length; i < n; i++) {
/* 124 */       if (!cl.isGap(i)) {
/* 125 */         numColumns++;
/*     */       }
/*     */     } 
/*     */     
/* 129 */     cl.numColumns = numColumns;
/* 130 */     cl.firstColumn = this.columnGroups.size();
/* 131 */     cl.childGroups = new DialogLayout.Group[cl.names.length];
/* 132 */     DialogLayout.Group h = createSequentialGroup();
/*     */     
/* 134 */     if (prefixColumns == null) {
/* 135 */       getHorizontalGroup().addGroup(h);
/*     */     } else {
/* 137 */       for (int m = 0; m < prefixSize; m++) {
/* 138 */         if (!cl.isGap(m)) {
/* 139 */           DialogLayout.Group g = this.columnGroups.get(prefixColumns.firstColumn + m);
/* 140 */           this.columnGroups.add(g);
/*     */         } 
/*     */       } 
/* 143 */       System.arraycopy(prefixColumns.childGroups, 0, cl.childGroups, 0, prefixSize);
/* 144 */       cl.childGroups[prefixSize - 1].addGroup(h);
/*     */     } 
/*     */     
/* 147 */     for (int j = prefixSize, k = cl.names.length; j < k; j++) {
/* 148 */       if (cl.isGap(j)) {
/* 149 */         h.addGap();
/*     */       } else {
/* 151 */         DialogLayout.Group g = createParallelGroup();
/* 152 */         h.addGroup(g);
/* 153 */         this.columnGroups.add(g);
/*     */       } 
/* 155 */       DialogLayout.Group nextSequential = createSequentialGroup();
/* 156 */       DialogLayout.Group childGroup = createParallelGroup().addGroup(nextSequential);
/* 157 */       h.addGroup(childGroup);
/* 158 */       h = nextSequential;
/* 159 */       cl.childGroups[j] = childGroup;
/*     */     } 
/* 161 */     this.columns.put(cl, cl);
/*     */   }
/*     */   
/*     */   public static final class Columns {
/*     */     final String[] names;
/*     */     final int hashcode;
/*     */     int firstColumn;
/*     */     int numColumns;
/*     */     DialogLayout.Group[] childGroups;
/*     */     
/*     */     Columns(String[] names) {
/* 172 */       this.names = (String[])names.clone();
/* 173 */       this.hashcode = Arrays.hashCode((Object[])this.names);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/* 178 */       if (obj == null || getClass() != obj.getClass()) {
/* 179 */         return false;
/*     */       }
/* 181 */       Columns other = (Columns)obj;
/* 182 */       return (this.hashcode == other.hashcode && Arrays.equals((Object[])this.names, (Object[])other.names));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getNumColumns() {
/* 191 */       return this.numColumns;
/*     */     }
/*     */     
/*     */     public int getNumColumnNames() {
/* 195 */       return this.names.length;
/*     */     }
/*     */     
/*     */     public String getColumnName(int idx) {
/* 199 */       return this.names[idx];
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 204 */       return this.hashcode;
/*     */     }
/*     */     
/*     */     boolean isGap(int column) {
/* 208 */       String name = this.names[column];
/* 209 */       return (name.length() == 0 || "-".equals(name));
/*     */     }
/*     */     
/*     */     int match(Columns other) {
/* 213 */       int cnt = Math.min(this.names.length, other.names.length);
/* 214 */       for (int i = 0; i < cnt; i++) {
/* 215 */         if (!this.names[i].equals(other.names[i])) {
/* 216 */           return i;
/*     */         }
/*     */       } 
/* 219 */       return cnt;
/*     */     }
/*     */   }
/*     */   
/*     */   public final class Row {
/*     */     final ColumnLayout.Columns columns;
/*     */     final ColumnLayout.Panel panel;
/*     */     final DialogLayout.Group row;
/*     */     int curColumn;
/*     */     
/*     */     Row(ColumnLayout.Columns columns, ColumnLayout.Panel panel, DialogLayout.Group row) {
/* 230 */       this.columns = columns;
/* 231 */       this.panel = panel;
/* 232 */       this.row = row;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getCurrentColumn() {
/* 241 */       return this.curColumn;
/*     */     }
/*     */     
/*     */     public ColumnLayout.Columns getColumns() {
/* 245 */       return this.columns;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Row add(Widget w) {
/* 256 */       if (this.curColumn == this.columns.numColumns) {
/* 257 */         throw new IllegalStateException("Too many widgets for column layout");
/*     */       }
/* 259 */       this.panel.getColumn(this.columns.firstColumn + this.curColumn).addWidget(w);
/* 260 */       this.row.addWidget(w);
/* 261 */       this.curColumn++;
/* 262 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Row add(Widget w, Alignment alignment) {
/* 274 */       add(w);
/* 275 */       ColumnLayout.this.setWidgetAlignment(w, alignment);
/* 276 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Row addLabel(String labelText) {
/* 289 */       if (labelText == null) {
/* 290 */         throw new NullPointerException("labelText");
/*     */       }
/* 292 */       return add(new Label(labelText));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Row addWithLabel(String labelText, Widget w) {
/* 306 */       if (labelText == null) {
/* 307 */         throw new NullPointerException("labelText");
/*     */       }
/* 309 */       Label labelWidget = new Label(labelText);
/* 310 */       labelWidget.setLabelFor(w);
/* 311 */       add(labelWidget, Alignment.TOPLEFT).add(w);
/* 312 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Row addWithLabel(String labelText, Widget w, Alignment alignment) {
/* 326 */       addWithLabel(labelText, w);
/* 327 */       ColumnLayout.this.setWidgetAlignment(w, alignment);
/* 328 */       return this;
/*     */     }
/*     */   }
/*     */   
/*     */   public final class Panel {
/*     */     final Panel parent;
/*     */     final ArrayList<DialogLayout.Group> usedColumnGroups;
/*     */     final ArrayList<Panel> children;
/*     */     final DialogLayout.Group rows;
/*     */     boolean valid;
/*     */     
/*     */     Panel(Panel parent) {
/* 340 */       this.parent = parent;
/* 341 */       this.usedColumnGroups = new ArrayList<DialogLayout.Group>();
/* 342 */       this.children = new ArrayList<Panel>();
/* 343 */       this.rows = ColumnLayout.this.createSequentialGroup();
/* 344 */       this.valid = true;
/*     */     }
/*     */     
/*     */     public boolean isValid() {
/* 348 */       return this.valid;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ColumnLayout.Columns getColumns(String... columnNames) {
/* 358 */       return ColumnLayout.this.getColumns(columnNames);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ColumnLayout.Row addRow(String... columnNames) {
/* 370 */       return addRow(ColumnLayout.this.getColumns(columnNames));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ColumnLayout.Row addRow(ColumnLayout.Columns columns) {
/* 381 */       if (columns == null) {
/* 382 */         throw new NullPointerException("columns");
/*     */       }
/* 384 */       checkValid();
/* 385 */       DialogLayout.Group row = ColumnLayout.this.createParallelGroup();
/* 386 */       this.rows.addGroup(row);
/* 387 */       return new ColumnLayout.Row(columns, this, row);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addVerticalGap(String name) {
/* 397 */       checkValid();
/* 398 */       this.rows.addGap(name);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Panel addPanel() {
/* 408 */       checkValid();
/* 409 */       Panel panel = new Panel(this);
/* 410 */       this.rows.addGroup(panel.rows);
/* 411 */       this.children.add(panel);
/* 412 */       return panel;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void removePanel(Panel panel) {
/* 420 */       if (panel == null) {
/* 421 */         throw new NullPointerException("panel");
/*     */       }
/* 423 */       if (this.valid && 
/* 424 */         this.children.remove(panel)) {
/* 425 */         panel.markInvalid();
/* 426 */         this.rows.removeGroup(panel.rows, true);
/* 427 */         for (int i = 0, n = panel.usedColumnGroups.size(); i < n; i++) {
/* 428 */           DialogLayout.Group column = panel.usedColumnGroups.get(i);
/* 429 */           if (column != null) {
/* 430 */             ((DialogLayout.Group)this.usedColumnGroups.get(i)).removeGroup(column, false);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void clearPanel() {
/* 441 */       if (this.valid) {
/* 442 */         this.children.clear();
/* 443 */         this.rows.clear(true);
/* 444 */         for (int i = 0, n = this.usedColumnGroups.size(); i < n; i++) {
/* 445 */           DialogLayout.Group column = this.usedColumnGroups.get(i);
/* 446 */           if (column != null) {
/* 447 */             column.clear(false);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     void markInvalid() {
/* 454 */       this.valid = false;
/* 455 */       for (int i = 0, n = this.children.size(); i < n; i++) {
/* 456 */         ((Panel)this.children.get(i)).markInvalid();
/*     */       }
/*     */     }
/*     */     
/*     */     void checkValid() {
/* 461 */       if (!this.valid) {
/* 462 */         throw new IllegalStateException("Panel has been removed");
/*     */       }
/*     */     }
/*     */     
/*     */     DialogLayout.Group getColumn(int idx) {
/* 467 */       checkValid();
/* 468 */       if (this.usedColumnGroups.size() > idx) {
/* 469 */         DialogLayout.Group column = this.usedColumnGroups.get(idx);
/* 470 */         if (column != null) {
/* 471 */           return column;
/*     */         }
/*     */       } 
/* 474 */       return makeColumn(idx);
/*     */     }
/*     */     
/*     */     private DialogLayout.Group makeColumn(int idx) {
/*     */       DialogLayout.Group parentColumn;
/* 479 */       if (this.parent != null) {
/* 480 */         parentColumn = this.parent.getColumn(idx);
/*     */       } else {
/* 482 */         parentColumn = ColumnLayout.this.columnGroups.get(idx);
/*     */       } 
/* 484 */       DialogLayout.Group column = ColumnLayout.this.createParallelGroup();
/* 485 */       parentColumn.addGroup(column);
/* 486 */       while (this.usedColumnGroups.size() <= idx) {
/* 487 */         this.usedColumnGroups.add(null);
/*     */       }
/* 489 */       this.usedColumnGroups.set(idx, column);
/* 490 */       return column;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ColumnLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */