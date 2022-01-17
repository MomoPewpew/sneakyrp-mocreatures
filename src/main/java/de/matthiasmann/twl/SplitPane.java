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
/*     */ public class SplitPane
/*     */   extends Widget
/*     */ {
/*     */   public static final int CENTER = -1;
/*     */   public static final int MIN_SIZE = -2;
/*     */   public static final int PREFERRED_SIZE = -3;
/*     */   private final DraggableButton splitter;
/*     */   private Direction direction;
/*     */   
/*     */   public enum Direction
/*     */   {
/*  39 */     HORIZONTAL("splitterHorizontal") {
/*     */       int get(int x, int y) {
/*  41 */         return x;
/*     */       }
/*     */       int getMinSize(Widget w) {
/*  44 */         return w.getMinWidth();
/*     */       }
/*     */       int getPrefSize(Widget w) {
/*  47 */         return w.getPreferredWidth();
/*     */       }
/*     */     },
/*  50 */     VERTICAL("splitterVertical") {
/*     */       int get(int x, int y) {
/*  52 */         return y;
/*     */       }
/*     */       int getMinSize(Widget w) {
/*  55 */         return w.getMinHeight();
/*     */       }
/*     */       int getPrefSize(Widget w) {
/*  58 */         return w.getPreferredHeight();
/*     */       } };
/*     */     
/*     */     final String splitterTheme;
/*     */     
/*     */     Direction(String splitterTheme) {
/*  64 */       this.splitterTheme = splitterTheme;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     abstract int get(int param1Int1, int param1Int2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     abstract int getMinSize(Widget param1Widget);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     abstract int getPrefSize(Widget param1Widget);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   private int splitPosition = -1;
/*     */   
/*     */   private boolean reverseSplitPosition;
/*     */   private boolean respectMinSizes;
/*     */   
/*     */   public SplitPane() {
/*  99 */     this.splitter = new DraggableButton();
/* 100 */     this.splitter.setCanAcceptKeyboardFocus(false);
/* 101 */     this.splitter.setListener(new DraggableButton.DragListener() { int initialPos;
/*     */           
/*     */           public void dragStarted() {
/* 104 */             this.initialPos = SplitPane.this.getEffectiveSplitPosition();
/*     */           }
/*     */           public void dragged(int deltaX, int deltaY) {
/* 107 */             SplitPane.this.dragged(this.initialPos, deltaX, deltaY);
/*     */           }
/*     */           
/*     */           public void dragStopped() {} }
/*     */       );
/* 112 */     setDirection(Direction.HORIZONTAL);
/* 113 */     add(this.splitter);
/*     */   }
/*     */   
/*     */   public Direction getDirection() {
/* 117 */     return this.direction;
/*     */   }
/*     */   
/*     */   public void setDirection(Direction direction) {
/* 121 */     if (direction == null) {
/* 122 */       throw new NullPointerException("direction");
/*     */     }
/* 124 */     this.direction = direction;
/* 125 */     this.splitter.setTheme(direction.splitterTheme);
/*     */   }
/*     */   
/*     */   public int getMaxSplitPosition() {
/* 129 */     return Math.max(0, this.direction.get(getInnerWidth() - this.splitter.getPreferredWidth(), getInnerHeight() - this.splitter.getPreferredHeight()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSplitPosition() {
/* 135 */     return this.splitPosition;
/*     */   }
/*     */   
/*     */   public void setSplitPosition(int pos) {
/* 139 */     if (pos < -3) {
/* 140 */       throw new IllegalArgumentException("pos");
/*     */     }
/* 142 */     this.splitPosition = pos;
/* 143 */     invalidateLayoutLocally();
/*     */   }
/*     */   
/*     */   public boolean getReverseSplitPosition() {
/* 147 */     return this.reverseSplitPosition;
/*     */   }
/*     */   
/*     */   public void setReverseSplitPosition(boolean reverseSplitPosition) {
/* 151 */     if (this.reverseSplitPosition != reverseSplitPosition) {
/* 152 */       this.reverseSplitPosition = reverseSplitPosition;
/* 153 */       invalidateLayoutLocally();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isRespectMinSizes() {
/* 158 */     return this.respectMinSizes;
/*     */   }
/*     */   
/*     */   public void setRespectMinSizes(boolean respectMinSizes) {
/* 162 */     if (this.respectMinSizes != respectMinSizes) {
/* 163 */       this.respectMinSizes = respectMinSizes;
/* 164 */       invalidateLayoutLocally();
/*     */     } 
/*     */   }
/*     */   
/*     */   void dragged(int initialPos, int deltaX, int deltaY) {
/* 169 */     int delta = this.direction.get(deltaX, deltaY);
/* 170 */     if (this.reverseSplitPosition) {
/* 171 */       delta = -delta;
/*     */     }
/* 173 */     setSplitPosition(clamp(initialPos + delta));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void childRemoved(Widget exChild) {
/* 178 */     super.childRemoved(exChild);
/* 179 */     if (exChild == this.splitter)
/*     */     {
/* 181 */       add(this.splitter);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void childAdded(Widget child) {
/* 187 */     super.childAdded(child);
/* 188 */     int numChildren = getNumChildren();
/* 189 */     if (numChildren > 0 && getChild(numChildren - 1) != this.splitter)
/*     */     {
/* 191 */       moveChild(getChildIndex(this.splitter), numChildren - 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/*     */     int min;
/* 199 */     if (this.direction == Direction.HORIZONTAL) {
/* 200 */       min = BoxLayout.computeMinWidthHorizontal(this, 0);
/*     */     } else {
/* 202 */       min = BoxLayout.computeMinWidthVertical(this);
/*     */     } 
/* 204 */     return Math.max(super.getMinWidth(), min);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/*     */     int min;
/* 210 */     if (this.direction == Direction.HORIZONTAL) {
/* 211 */       min = BoxLayout.computeMinHeightHorizontal(this);
/*     */     } else {
/* 213 */       min = BoxLayout.computeMinHeightVertical(this, 0);
/*     */     } 
/* 215 */     return Math.max(super.getMinHeight(), min);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 220 */     if (this.direction == Direction.HORIZONTAL) {
/* 221 */       return BoxLayout.computePreferredWidthHorizontal(this, 0);
/*     */     }
/* 223 */     return BoxLayout.computePreferredWidthVertical(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 229 */     if (this.direction == Direction.HORIZONTAL) {
/* 230 */       return BoxLayout.computePreferredHeightHorizontal(this);
/*     */     }
/* 232 */     return BoxLayout.computePreferredHeightVertical(this, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/*     */     int innerHeight, innerWidth;
/* 238 */     Widget a = null;
/* 239 */     Widget b = null;
/* 240 */     for (int i = 0; i < getNumChildren(); i++) {
/* 241 */       Widget w = getChild(i);
/* 242 */       if (w != this.splitter) {
/* 243 */         if (a == null) {
/* 244 */           a = w;
/*     */         } else {
/* 246 */           b = w;
/*     */           
/*     */           break;
/*     */         } 
/*     */       }
/*     */     } 
/* 252 */     int innerX = getInnerX();
/* 253 */     int innerY = getInnerY();
/* 254 */     int splitPos = getEffectiveSplitPosition();
/*     */     
/* 256 */     if (this.reverseSplitPosition) {
/* 257 */       splitPos = getMaxSplitPosition() - splitPos;
/*     */     }
/*     */     
/* 260 */     switch (this.direction) {
/*     */       case HORIZONTAL:
/* 262 */         innerHeight = getInnerHeight();
/* 263 */         this.splitter.setPosition(innerX + splitPos, innerY);
/* 264 */         this.splitter.setSize(this.splitter.getPreferredWidth(), innerHeight);
/* 265 */         if (a != null) {
/* 266 */           a.setPosition(innerX, innerY);
/* 267 */           a.setSize(splitPos, innerHeight);
/*     */         } 
/* 269 */         if (b != null) {
/* 270 */           b.setPosition(this.splitter.getRight(), innerY);
/* 271 */           b.setSize(Math.max(0, getInnerRight() - this.splitter.getRight()), innerHeight);
/*     */         } 
/*     */         break;
/*     */       
/*     */       case VERTICAL:
/* 276 */         innerWidth = getInnerWidth();
/* 277 */         this.splitter.setPosition(innerX, innerY + splitPos);
/* 278 */         this.splitter.setSize(innerWidth, this.splitter.getPreferredHeight());
/* 279 */         if (a != null) {
/* 280 */           a.setPosition(innerX, innerY);
/* 281 */           a.setSize(innerWidth, splitPos);
/*     */         } 
/* 283 */         if (b != null) {
/* 284 */           b.setPosition(innerX, this.splitter.getBottom());
/* 285 */           b.setSize(innerWidth, Math.max(0, getInnerBottom() - this.splitter.getBottom()));
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */   int getEffectiveSplitPosition() {
/*     */     Widget w;
/* 292 */     int maxSplitPosition = getMaxSplitPosition();
/*     */     
/* 294 */     int pos = this.splitPosition;
/* 295 */     switch (pos) {
/*     */       case -1:
/* 297 */         pos = maxSplitPosition / 2;
/*     */         break;
/*     */       case -2:
/* 300 */         w = getPrimaryWidget();
/* 301 */         if (w != null) {
/* 302 */           pos = this.direction.getMinSize(w); break;
/*     */         } 
/* 304 */         pos = maxSplitPosition / 2;
/*     */         break;
/*     */ 
/*     */       
/*     */       case -3:
/* 309 */         w = getPrimaryWidget();
/* 310 */         if (w != null) {
/* 311 */           pos = this.direction.getPrefSize(w); break;
/*     */         } 
/* 313 */         pos = maxSplitPosition / 2;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 319 */     int minValue = 0;
/* 320 */     int maxValue = maxSplitPosition;
/*     */     
/* 322 */     if (this.respectMinSizes) {
/* 323 */       Widget a = null;
/* 324 */       Widget b = null;
/* 325 */       for (int i = 0; i < getNumChildren(); i++) {
/* 326 */         Widget widget = getChild(i);
/* 327 */         if (widget != this.splitter) {
/* 328 */           if (a == null) {
/* 329 */             a = widget;
/*     */           } else {
/* 331 */             b = widget;
/*     */             
/*     */             break;
/*     */           } 
/*     */         }
/*     */       } 
/* 337 */       int aMinSize = (a != null) ? this.direction.getMinSize(a) : 0;
/* 338 */       int bMinSize = (b != null) ? this.direction.getMinSize(b) : 0;
/*     */       
/* 340 */       if (this.reverseSplitPosition) {
/* 341 */         minValue = bMinSize;
/* 342 */         maxValue = Math.max(0, maxSplitPosition - aMinSize);
/*     */       } else {
/* 344 */         minValue = aMinSize;
/* 345 */         maxValue = Math.max(0, maxSplitPosition - bMinSize);
/*     */       } 
/*     */     } 
/*     */     
/* 349 */     return Math.max(minValue, Math.min(maxValue, pos));
/*     */   }
/*     */   
/*     */   private Widget getPrimaryWidget() {
/* 353 */     int idx = this.reverseSplitPosition ? 1 : 0;
/* 354 */     if (getNumChildren() > idx) {
/* 355 */       return getChild(idx);
/*     */     }
/* 357 */     return null;
/*     */   }
/*     */   
/*     */   private int clamp(int pos) {
/* 361 */     return Math.max(0, Math.min(getMaxSplitPosition(), pos));
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\SplitPane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */