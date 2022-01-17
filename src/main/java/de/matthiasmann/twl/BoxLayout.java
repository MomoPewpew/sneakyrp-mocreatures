/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BoxLayout
/*     */   extends Widget
/*     */ {
/*     */   private Direction direction;
/*     */   private int spacing;
/*     */   private boolean scroll;
/*     */   
/*     */   public enum Direction
/*     */   {
/*  39 */     HORIZONTAL,
/*  40 */     VERTICAL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   private Alignment alignment = Alignment.TOP;
/*     */   
/*     */   public BoxLayout() {
/*  49 */     this(Direction.HORIZONTAL);
/*     */   }
/*     */   
/*     */   public BoxLayout(Direction direction) {
/*  53 */     this.direction = direction;
/*     */   }
/*     */   
/*     */   public int getSpacing() {
/*  57 */     return this.spacing;
/*     */   }
/*     */   
/*     */   public void setSpacing(int spacing) {
/*  61 */     if (this.spacing != spacing) {
/*  62 */       this.spacing = spacing;
/*  63 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isScroll() {
/*  68 */     return this.scroll;
/*     */   }
/*     */   
/*     */   public void setScroll(boolean scroll) {
/*  72 */     if (this.scroll != scroll) {
/*  73 */       this.scroll = scroll;
/*  74 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Alignment getAlignment() {
/*  79 */     return this.alignment;
/*     */   }
/*     */   
/*     */   public void setAlignment(Alignment alignment) {
/*  83 */     if (alignment == null) {
/*  84 */       throw new NullPointerException("alignment");
/*     */     }
/*  86 */     if (this.alignment != alignment) {
/*  87 */       this.alignment = alignment;
/*  88 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Direction getDirection() {
/*  93 */     return this.direction;
/*     */   }
/*     */   
/*     */   public void setDirection(Direction direction) {
/*  97 */     if (direction == null) {
/*  98 */       throw new NullPointerException("direction");
/*     */     }
/* 100 */     if (this.direction != direction) {
/* 101 */       this.direction = direction;
/* 102 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/* 108 */     int minWidth = (this.direction == Direction.HORIZONTAL) ? computeMinWidthHorizontal(this, this.spacing) : computeMinWidthVertical(this);
/*     */ 
/*     */     
/* 111 */     return Math.max(super.getMinWidth(), minWidth + getBorderHorizontal());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/* 116 */     int minHeight = (this.direction == Direction.HORIZONTAL) ? computeMinHeightHorizontal(this) : computeMinHeightVertical(this, this.spacing);
/*     */ 
/*     */     
/* 119 */     return Math.max(super.getMinHeight(), minHeight + getBorderVertical());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 124 */     return (this.direction == Direction.HORIZONTAL) ? computePreferredWidthHorizontal(this, this.spacing) : computePreferredWidthVertical(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 131 */     return (this.direction == Direction.HORIZONTAL) ? computePreferredHeightHorizontal(this) : computePreferredHeightVertical(this, this.spacing);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 138 */     super.applyTheme(themeInfo);
/* 139 */     setSpacing(themeInfo.getParameter("spacing", 0));
/* 140 */     setAlignment(themeInfo.<Alignment>getParameter("alignment", Alignment.TOP));
/*     */   }
/*     */   
/*     */   public static int computeMinWidthHorizontal(Widget container, int spacing) {
/* 144 */     int n = container.getNumChildren();
/* 145 */     int minWidth = Math.max(0, n - 1) * spacing;
/* 146 */     for (int i = 0; i < n; i++) {
/* 147 */       minWidth += container.getChild(i).getMinWidth();
/*     */     }
/* 149 */     return minWidth;
/*     */   }
/*     */   
/*     */   public static int computeMinHeightHorizontal(Widget container) {
/* 153 */     int n = container.getNumChildren();
/* 154 */     int minHeight = 0;
/* 155 */     for (int i = 0; i < n; i++) {
/* 156 */       minHeight = Math.max(minHeight, container.getChild(i).getMinHeight());
/*     */     }
/* 158 */     return minHeight;
/*     */   }
/*     */   
/*     */   public static int computePreferredWidthHorizontal(Widget container, int spacing) {
/* 162 */     int n = container.getNumChildren();
/* 163 */     int prefWidth = Math.max(0, n - 1) * spacing;
/* 164 */     for (int i = 0; i < n; i++) {
/* 165 */       prefWidth += getPrefChildWidth(container.getChild(i));
/*     */     }
/* 167 */     return prefWidth;
/*     */   }
/*     */   
/*     */   public static int computePreferredHeightHorizontal(Widget container) {
/* 171 */     int n = container.getNumChildren();
/* 172 */     int prefHeight = 0;
/* 173 */     for (int i = 0; i < n; i++) {
/* 174 */       prefHeight = Math.max(prefHeight, getPrefChildHeight(container.getChild(i)));
/*     */     }
/* 176 */     return prefHeight;
/*     */   }
/*     */   
/*     */   public static int computeMinWidthVertical(Widget container) {
/* 180 */     int n = container.getNumChildren();
/* 181 */     int minWidth = 0;
/* 182 */     for (int i = 0; i < n; i++) {
/* 183 */       minWidth = Math.max(minWidth, container.getChild(i).getMinWidth());
/*     */     }
/* 185 */     return minWidth;
/*     */   }
/*     */   
/*     */   public static int computeMinHeightVertical(Widget container, int spacing) {
/* 189 */     int n = container.getNumChildren();
/* 190 */     int minHeight = Math.max(0, n - 1) * spacing;
/* 191 */     for (int i = 0; i < n; i++) {
/* 192 */       minHeight += container.getChild(i).getMinHeight();
/*     */     }
/* 194 */     return minHeight;
/*     */   }
/*     */   
/*     */   public static int computePreferredWidthVertical(Widget container) {
/* 198 */     int n = container.getNumChildren();
/* 199 */     int prefWidth = 0;
/* 200 */     for (int i = 0; i < n; i++) {
/* 201 */       prefWidth = Math.max(prefWidth, getPrefChildWidth(container.getChild(i)));
/*     */     }
/* 203 */     return prefWidth;
/*     */   }
/*     */   
/*     */   public static int computePreferredHeightVertical(Widget container, int spacing) {
/* 207 */     int n = container.getNumChildren();
/* 208 */     int prefHeight = Math.max(0, n - 1) * spacing;
/* 209 */     for (int i = 0; i < n; i++) {
/* 210 */       prefHeight += getPrefChildHeight(container.getChild(i));
/*     */     }
/* 212 */     return prefHeight;
/*     */   }
/*     */   
/*     */   public static void layoutHorizontal(Widget container, int spacing, Alignment alignment, boolean scroll) {
/* 216 */     int numChildren = container.getNumChildren();
/* 217 */     int height = container.getInnerHeight();
/* 218 */     int x = container.getInnerX();
/* 219 */     int y = container.getInnerY();
/*     */ 
/*     */     
/* 222 */     if (scroll) {
/* 223 */       int width = computePreferredWidthHorizontal(container, spacing);
/* 224 */       if (width > container.getInnerWidth()) {
/* 225 */         x -= width - container.getInnerWidth();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 230 */     for (int idx = 0; idx < numChildren; idx++) {
/* 231 */       Widget child = container.getChild(idx);
/* 232 */       int childWidth = getPrefChildWidth(child);
/* 233 */       int childHeight = (alignment == Alignment.FILL) ? height : getPrefChildHeight(child);
/* 234 */       int yoff = (height - childHeight) * alignment.vpos / 2;
/* 235 */       child.setSize(childWidth, childHeight);
/* 236 */       child.setPosition(x, y + yoff);
/* 237 */       x += childWidth + spacing;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void layoutVertical(Widget container, int spacing, Alignment alignment, boolean scroll) {
/* 242 */     int numChildren = container.getNumChildren();
/* 243 */     int width = container.getInnerWidth();
/* 244 */     int x = container.getInnerX();
/* 245 */     int y = container.getInnerY();
/*     */ 
/*     */     
/* 248 */     if (scroll) {
/* 249 */       int height = computePreferredHeightVertical(container, spacing);
/* 250 */       if (height > container.getInnerHeight()) {
/* 251 */         x -= height - container.getInnerHeight();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 256 */     for (int idx = 0; idx < numChildren; idx++) {
/* 257 */       Widget child = container.getChild(idx);
/* 258 */       int childWidth = (alignment == Alignment.FILL) ? width : getPrefChildWidth(child);
/* 259 */       int childHeight = getPrefChildHeight(child);
/* 260 */       int xoff = (width - childWidth) * alignment.hpos / 2;
/* 261 */       child.setSize(childWidth, childHeight);
/* 262 */       child.setPosition(x + xoff, y);
/* 263 */       y += childHeight + spacing;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 269 */     if (getNumChildren() > 0) {
/* 270 */       if (this.direction == Direction.HORIZONTAL) {
/* 271 */         layoutHorizontal(this, this.spacing, this.alignment, this.scroll);
/*     */       } else {
/* 273 */         layoutVertical(this, this.spacing, this.alignment, this.scroll);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static int getPrefChildWidth(Widget child) {
/* 279 */     return computeSize(child.getMinWidth(), child.getPreferredWidth(), child.getMaxWidth());
/*     */   }
/*     */   
/*     */   private static int getPrefChildHeight(Widget child) {
/* 283 */     return computeSize(child.getMinHeight(), child.getPreferredHeight(), child.getMaxHeight());
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\BoxLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */