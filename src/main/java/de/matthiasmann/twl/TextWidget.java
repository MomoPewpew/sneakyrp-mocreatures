/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.Font;
/*     */ import de.matthiasmann.twl.renderer.FontCache;
/*     */ import de.matthiasmann.twl.utils.TextUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextWidget
/*     */   extends Widget
/*     */ {
/*  44 */   public static final AnimationState.StateKey STATE_HOVER = AnimationState.StateKey.get("hover");
/*  45 */   public static final AnimationState.StateKey STATE_TEXT_CHANGED = AnimationState.StateKey.get("textChanged");
/*  46 */   public static final AnimationState.StateKey STATE_TEXT_SELECTION = AnimationState.StateKey.get("textSelection");
/*     */   
/*     */   private static final int NOT_CACHED = -1;
/*     */   
/*     */   private Font font;
/*     */   private FontCache cache;
/*     */   private CharSequence text;
/*  53 */   private int cachedTextWidth = -1;
/*     */   private int numTextLines;
/*     */   private boolean useCache = true;
/*     */   private boolean cacheDirty;
/*  57 */   private Alignment alignment = Alignment.TOPLEFT;
/*     */   
/*     */   public TextWidget() {
/*  60 */     this((AnimationState)null, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextWidget(AnimationState animState) {
/*  69 */     this(animState, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextWidget(AnimationState animState, boolean inherit) {
/*  79 */     super(animState, inherit);
/*  80 */     this.text = "";
/*     */   }
/*     */   
/*     */   public Font getFont() {
/*  84 */     return this.font;
/*     */   }
/*     */   
/*     */   public void setFont(Font font) {
/*  88 */     if (this.cache != null) {
/*  89 */       this.cache.destroy();
/*  90 */       this.cache = null;
/*     */     } 
/*  92 */     this.font = font;
/*  93 */     this.cachedTextWidth = -1;
/*  94 */     if (this.useCache) {
/*  95 */       this.cacheDirty = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setCharSequence(CharSequence text) {
/* 107 */     if (text == null) {
/* 108 */       throw new NullPointerException("text");
/*     */     }
/* 110 */     this.text = text;
/* 111 */     this.cachedTextWidth = -1;
/* 112 */     this.numTextLines = TextUtil.countNumLines(text);
/* 113 */     this.cacheDirty = true;
/* 114 */     getAnimationState().resetAnimationTime(STATE_TEXT_CHANGED);
/*     */   }
/*     */   
/*     */   protected CharSequence getCharSequence() {
/* 118 */     return this.text;
/*     */   }
/*     */   
/*     */   public boolean hasText() {
/* 122 */     return (this.numTextLines > 0);
/*     */   }
/*     */   
/*     */   public boolean isMultilineText() {
/* 126 */     return (this.numTextLines > 1);
/*     */   }
/*     */   
/*     */   public int getNumTextLines() {
/* 130 */     return this.numTextLines;
/*     */   }
/*     */   
/*     */   public Alignment getAlignment() {
/* 134 */     return this.alignment;
/*     */   }
/*     */   
/*     */   public void setAlignment(Alignment alignment) {
/* 138 */     if (alignment == null) {
/* 139 */       throw new NullPointerException("alignment");
/*     */     }
/* 141 */     if (this.alignment != alignment) {
/* 142 */       this.alignment = alignment;
/* 143 */       this.cacheDirty = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isCache() {
/* 148 */     return this.useCache;
/*     */   }
/*     */   
/*     */   public void setCache(boolean cache) {
/* 152 */     if (this.useCache != cache) {
/* 153 */       this.useCache = cache;
/* 154 */       this.cacheDirty = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void applyThemeTextWidget(ThemeInfo themeInfo) {
/* 159 */     setFont(themeInfo.getFont("font"));
/* 160 */     setAlignment(themeInfo.<Alignment>getParameter("textAlignment", Alignment.TOPLEFT));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 165 */     super.applyTheme(themeInfo);
/* 166 */     applyThemeTextWidget(themeInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   public void destroy() {
/* 171 */     if (this.cache != null) {
/* 172 */       this.cache.destroy();
/* 173 */       this.cache = null;
/*     */     } 
/* 175 */     super.destroy();
/*     */   }
/*     */   
/*     */   protected int computeTextX() {
/* 179 */     int x = getInnerX();
/* 180 */     int pos = this.alignment.hpos;
/* 181 */     if (pos > 0) {
/* 182 */       return x + (getInnerWidth() - computeTextWidth()) * pos / 2;
/*     */     }
/* 184 */     return x;
/*     */   }
/*     */   
/*     */   protected int computeTextY() {
/* 188 */     int y = getInnerY();
/* 189 */     int pos = this.alignment.vpos;
/* 190 */     if (pos > 0) {
/* 191 */       return y + (getInnerHeight() - computeTextHeight()) * pos / 2;
/*     */     }
/* 193 */     return y;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintWidget(GUI gui) {
/* 198 */     paintLabelText(getAnimationState());
/*     */   }
/*     */   
/*     */   protected void paintLabelText(AnimationState animState) {
/* 202 */     if (this.cacheDirty) {
/* 203 */       updateCache();
/*     */     }
/* 205 */     if (hasText() && this.font != null) {
/* 206 */       int x = computeTextX();
/* 207 */       int y = computeTextY();
/*     */       
/* 209 */       paintTextAt(animState, x, y);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void paintTextAt(AnimationState animState, int x, int y) {
/* 214 */     if (this.cache != null) {
/* 215 */       this.cache.draw(animState, x, y);
/* 216 */     } else if (this.numTextLines > 1) {
/* 217 */       this.font.drawMultiLineText(animState, x, y, this.text, computeTextWidth(), this.alignment.fontHAlignment);
/*     */     } else {
/* 219 */       this.font.drawText(animState, x, y, this.text);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void paintWithSelection(AnimationState animState, int start, int end) {
/* 224 */     paintWithSelection(animState, start, end, 0, this.text.length(), computeTextY());
/*     */   }
/*     */   
/*     */   protected void paintWithSelection(AnimationState animState, int start, int end, int lineStart, int lineEnd, int y) {
/* 228 */     if (this.cacheDirty) {
/* 229 */       updateCache();
/*     */     }
/* 231 */     if (hasText() && this.font != null) {
/* 232 */       int x = computeTextX();
/*     */       
/* 234 */       start = limit(start, lineStart, lineEnd);
/* 235 */       end = limit(end, lineStart, lineEnd);
/*     */       
/* 237 */       if (start > lineStart) {
/* 238 */         x += this.font.drawText(animState, x, y, this.text, lineStart, start);
/*     */       }
/* 240 */       if (end > start) {
/* 241 */         animState.setAnimationState(STATE_TEXT_SELECTION, true);
/* 242 */         x += this.font.drawText(animState, x, y, this.text, start, end);
/* 243 */         animState.setAnimationState(STATE_TEXT_SELECTION, false);
/*     */       } 
/* 245 */       if (end < lineEnd) {
/* 246 */         this.font.drawText(animState, x, y, this.text, end, lineEnd);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int limit(int value, int min, int max) {
/* 252 */     if (value < min) {
/* 253 */       return min;
/*     */     }
/* 255 */     if (value > max) {
/* 256 */       return max;
/*     */     }
/* 258 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 263 */     int prefWidth = super.getPreferredInnerWidth();
/* 264 */     if (hasText() && this.font != null) {
/* 265 */       prefWidth = Math.max(prefWidth, computeTextWidth());
/*     */     }
/* 267 */     return prefWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 272 */     int prefHeight = super.getPreferredInnerHeight();
/* 273 */     if (hasText() && this.font != null) {
/* 274 */       prefHeight = Math.max(prefHeight, computeTextHeight());
/*     */     }
/* 276 */     return prefHeight;
/*     */   }
/*     */   
/*     */   public int computeRelativeCursorPositionX(int charIndex) {
/* 280 */     return computeRelativeCursorPositionX(0, charIndex);
/*     */   }
/*     */   
/*     */   public int computeRelativeCursorPositionX(int startIndex, int charIndex) {
/* 284 */     if (this.font != null && charIndex > startIndex) {
/* 285 */       return this.font.computeTextWidth(this.text, startIndex, charIndex);
/*     */     }
/* 287 */     return 0;
/*     */   }
/*     */   
/*     */   public int computeTextWidth() {
/* 291 */     if (this.font != null) {
/* 292 */       if (this.cachedTextWidth == -1 || this.cacheDirty) {
/* 293 */         if (this.numTextLines > 1) {
/* 294 */           this.cachedTextWidth = this.font.computeMultiLineTextWidth(this.text);
/*     */         } else {
/* 296 */           this.cachedTextWidth = this.font.computeTextWidth(this.text);
/*     */         } 
/*     */       }
/* 299 */       return this.cachedTextWidth;
/*     */     } 
/* 301 */     return 0;
/*     */   }
/*     */   
/*     */   public int computeTextHeight() {
/* 305 */     if (this.font != null) {
/* 306 */       return Math.max(1, this.numTextLines) * this.font.getLineHeight();
/*     */     }
/* 308 */     return 0;
/*     */   }
/*     */   
/*     */   private void updateCache() {
/* 312 */     this.cacheDirty = false;
/* 313 */     if (this.useCache && hasText() && this.font != null) {
/* 314 */       if (this.numTextLines > 1) {
/* 315 */         this.cache = this.font.cacheMultiLineText(this.cache, this.text, this.font.computeMultiLineTextWidth(this.text), this.alignment.fontHAlignment);
/*     */       }
/*     */       else {
/*     */         
/* 319 */         this.cache = this.font.cacheText(this.cache, this.text);
/*     */       } 
/* 321 */       if (this.cache != null) {
/* 322 */         this.cachedTextWidth = this.cache.getWidth();
/*     */       }
/*     */     } else {
/* 325 */       destroy();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void handleMouseHover(Event evt) {
/* 330 */     if (evt.isMouseEvent() && !hasSharedAnimationState())
/* 331 */       getAnimationState().setAnimationState(STATE_HOVER, (evt.getType() != Event.Type.MOUSE_EXITED)); 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\TextWidget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */