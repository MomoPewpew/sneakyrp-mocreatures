/*     */ package de.matthiasmann.twl.textarea;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface TextAreaModel
/*     */   extends Iterable<TextAreaModel.Element>
/*     */ {
/*     */   void addCallback(Runnable paramRunnable);
/*     */   
/*     */   void removeCallback(Runnable paramRunnable);
/*     */   
/*     */   public enum HAlignment
/*     */   {
/*  43 */     LEFT,
/*  44 */     RIGHT,
/*  45 */     CENTER,
/*  46 */     JUSTIFY;
/*     */   }
/*     */   
/*     */   public enum Display {
/*  50 */     INLINE,
/*  51 */     BLOCK;
/*     */   }
/*     */   
/*     */   public enum VAlignment {
/*  55 */     TOP,
/*  56 */     MIDDLE,
/*  57 */     BOTTOM,
/*  58 */     FILL;
/*     */   }
/*     */   
/*     */   public enum Clear {
/*  62 */     NONE,
/*  63 */     LEFT,
/*  64 */     RIGHT,
/*  65 */     BOTH;
/*     */   }
/*     */   
/*     */   public enum FloatPosition {
/*  69 */     NONE,
/*  70 */     LEFT,
/*  71 */     RIGHT;
/*     */   }
/*     */   
/*     */   public static abstract class Element {
/*     */     private Style style;
/*     */     
/*     */     protected Element(Style style) {
/*  78 */       notNull(style, "style");
/*  79 */       this.style = style;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Style getStyle() {
/*  87 */       return this.style;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setStyle(Style style) {
/*  97 */       notNull(style, "style");
/*  98 */       this.style = style;
/*     */     }
/*     */     
/*     */     static void notNull(Object o, String name) {
/* 102 */       if (o == null)
/* 103 */         throw new NullPointerException(name); 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TextElement
/*     */     extends Element {
/*     */     private String text;
/*     */     
/*     */     public TextElement(Style style, String text) {
/* 112 */       super(style);
/* 113 */       notNull(text, "text");
/* 114 */       this.text = text;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getText() {
/* 122 */       return this.text;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setText(String text) {
/* 132 */       notNull(text, "text");
/* 133 */       this.text = text;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ImageElement extends Element {
/*     */     private final String imageName;
/*     */     private final String tooltip;
/*     */     
/*     */     public ImageElement(Style style, String imageName, String tooltip) {
/* 142 */       super(style);
/* 143 */       this.imageName = imageName;
/* 144 */       this.tooltip = tooltip;
/*     */     }
/*     */     
/*     */     public ImageElement(Style style, String imageName) {
/* 148 */       this(style, imageName, null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getImageName() {
/* 156 */       return this.imageName;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getToolTip() {
/* 164 */       return this.tooltip;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class WidgetElement extends Element {
/*     */     private final String widgetName;
/*     */     private final String widgetParam;
/*     */     
/*     */     public WidgetElement(Style style, String widgetName, String widgetParam) {
/* 173 */       super(style);
/* 174 */       this.widgetName = widgetName;
/* 175 */       this.widgetParam = widgetParam;
/*     */     }
/*     */     
/*     */     public String getWidgetName() {
/* 179 */       return this.widgetName;
/*     */     }
/*     */     
/*     */     public String getWidgetParam() {
/* 183 */       return this.widgetParam;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ContainerElement extends Element implements Iterable<Element> {
/*     */     protected final ArrayList<TextAreaModel.Element> children;
/*     */     
/*     */     public ContainerElement(Style style) {
/* 191 */       super(style);
/* 192 */       this.children = new ArrayList<TextAreaModel.Element>();
/*     */     }
/*     */     
/*     */     public Iterator<TextAreaModel.Element> iterator() {
/* 196 */       return this.children.iterator();
/*     */     }
/*     */     
/*     */     public TextAreaModel.Element getElement(int index) {
/* 200 */       return this.children.get(index);
/*     */     }
/*     */     
/*     */     public int getNumElements() {
/* 204 */       return this.children.size();
/*     */     }
/*     */     
/*     */     public void add(TextAreaModel.Element element) {
/* 208 */       this.children.add(element);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ParagraphElement extends ContainerElement {
/*     */     public ParagraphElement(Style style) {
/* 214 */       super(style);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class LinkElement extends ContainerElement {
/*     */     private String href;
/*     */     
/*     */     public LinkElement(Style style, String href) {
/* 222 */       super(style);
/* 223 */       this.href = href;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getHREF() {
/* 231 */       return this.href;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setHREF(String href) {
/* 241 */       this.href = href;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ListElement
/*     */     extends ContainerElement
/*     */   {
/*     */     public ListElement(Style style) {
/* 250 */       super(style);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class OrderedListElement
/*     */     extends ContainerElement
/*     */   {
/*     */     private final int start;
/*     */     
/*     */     public OrderedListElement(Style style, int start) {
/* 261 */       super(style);
/* 262 */       this.start = start;
/*     */     }
/*     */     
/*     */     public int getStart() {
/* 266 */       return this.start;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BlockElement extends ContainerElement {
/*     */     public BlockElement(Style style) {
/* 272 */       super(style);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TableCellElement extends ContainerElement {
/*     */     private final int colspan;
/*     */     
/*     */     public TableCellElement(Style style) {
/* 280 */       this(style, 1);
/*     */     }
/*     */     
/*     */     public TableCellElement(Style style, int colspan) {
/* 284 */       super(style);
/* 285 */       this.colspan = colspan;
/*     */     }
/*     */     
/*     */     public int getColspan() {
/* 289 */       return this.colspan;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class TableElement extends Element {
/*     */     private final int numColumns;
/*     */     private final int numRows;
/*     */     private final int cellSpacing;
/*     */     private final int cellPadding;
/*     */     private final TextAreaModel.TableCellElement[] cells;
/*     */     private final Style[] rowStyles;
/*     */     
/*     */     public TableElement(Style style, int numColumns, int numRows, int cellSpacing, int cellPadding) {
/* 302 */       super(style);
/* 303 */       if (numColumns < 0) {
/* 304 */         throw new IllegalArgumentException("numColumns");
/*     */       }
/* 306 */       if (numRows < 0) {
/* 307 */         throw new IllegalArgumentException("numRows");
/*     */       }
/*     */       
/* 310 */       this.numColumns = numColumns;
/* 311 */       this.numRows = numRows;
/* 312 */       this.cellSpacing = cellSpacing;
/* 313 */       this.cellPadding = cellPadding;
/* 314 */       this.cells = new TextAreaModel.TableCellElement[numRows * numColumns];
/* 315 */       this.rowStyles = new Style[numRows];
/*     */     }
/*     */     
/*     */     public int getNumColumns() {
/* 319 */       return this.numColumns;
/*     */     }
/*     */     
/*     */     public int getNumRows() {
/* 323 */       return this.numRows;
/*     */     }
/*     */     
/*     */     public int getCellPadding() {
/* 327 */       return this.cellPadding;
/*     */     }
/*     */     
/*     */     public int getCellSpacing() {
/* 331 */       return this.cellSpacing;
/*     */     }
/*     */     
/*     */     public TextAreaModel.TableCellElement getCell(int row, int column) {
/* 335 */       if (column < 0 || column >= this.numColumns) {
/* 336 */         throw new IndexOutOfBoundsException("column");
/*     */       }
/* 338 */       if (row < 0 || row >= this.numRows) {
/* 339 */         throw new IndexOutOfBoundsException("row");
/*     */       }
/* 341 */       return this.cells[row * this.numColumns + column];
/*     */     }
/*     */     
/*     */     public Style getRowStyle(int row) {
/* 345 */       return this.rowStyles[row];
/*     */     }
/*     */     
/*     */     public void setCell(int row, int column, TextAreaModel.TableCellElement cell) {
/* 349 */       if (column < 0 || column >= this.numColumns) {
/* 350 */         throw new IndexOutOfBoundsException("column");
/*     */       }
/* 352 */       if (row < 0 || row >= this.numRows) {
/* 353 */         throw new IndexOutOfBoundsException("row");
/*     */       }
/* 355 */       this.cells[row * this.numColumns + column] = cell;
/*     */     }
/*     */     
/*     */     public void setRowStyle(int row, Style style) {
/* 359 */       this.rowStyles[row] = style;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\textarea\TextAreaModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */