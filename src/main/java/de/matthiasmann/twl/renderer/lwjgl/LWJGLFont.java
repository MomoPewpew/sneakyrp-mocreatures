/*     */ package de.matthiasmann.twl.renderer.lwjgl;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.HAlignment;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.AttributedString;
/*     */ import de.matthiasmann.twl.renderer.AttributedStringFontCache;
/*     */ import de.matthiasmann.twl.renderer.Font;
/*     */ import de.matthiasmann.twl.renderer.Font2;
/*     */ import de.matthiasmann.twl.renderer.FontCache;
/*     */ import de.matthiasmann.twl.renderer.FontParameter;
/*     */ import de.matthiasmann.twl.utils.StateSelect;
/*     */ import de.matthiasmann.twl.utils.TextUtil;
/*     */ import java.nio.FloatBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LWJGLFont
/*     */   implements Font, Font2
/*     */ {
/*     */   static final int STYLE_UNDERLINE = 1;
/*     */   static final int STYLE_LINETHROUGH = 2;
/*     */   private final LWJGLRenderer renderer;
/*     */   private final BitmapFont font;
/*     */   private final FontState[] fontStates;
/*     */   private final StateSelect stateSelect;
/*     */   private int[] multiLineInfo;
/*     */   
/*     */   public LWJGLFont clone() {
/*  62 */     return new LWJGLFont(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private LWJGLFont(LWJGLFont oldFont) {
/*  67 */     this.renderer = oldFont.renderer;
/*  68 */     this.font = oldFont.font;
/*  69 */     this.stateSelect = oldFont.stateSelect;
/*  70 */     this.fontStates = new FontState[oldFont.fontStates.length];
/*  71 */     for (int i = 0; i < this.fontStates.length; i++) {
/*  72 */       FontState oldState = oldFont.fontStates[i];
/*  73 */       this.fontStates[i] = new FontState(oldState);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   LWJGLFont(LWJGLRenderer renderer, BitmapFont font, StateSelect select, FontParameter... parameterList) {
/*  79 */     this.renderer = renderer;
/*  80 */     this.font = font;
/*  81 */     this.stateSelect = select;
/*  82 */     this.fontStates = new FontState[parameterList.length];
/*     */     
/*  84 */     for (int i = 0; i < parameterList.length; i++) {
/*  85 */       this.fontStates[i] = new FontState(parameterList[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   public FontState evalFontState(AnimationState as) {
/*  90 */     return this.fontStates[this.stateSelect.evaluate(as)];
/*     */   }
/*     */   
/*     */   private int[] getMultiLineInfo(int numLines) {
/*  94 */     if (this.multiLineInfo == null || this.multiLineInfo.length < numLines) {
/*  95 */       this.multiLineInfo = new int[numLines];
/*     */     }
/*  97 */     return this.multiLineInfo;
/*     */   }
/*     */   
/*     */   public void destroy() {
/* 101 */     this.font.destroy();
/*     */   }
/*     */   
/*     */   public boolean isProportional() {
/* 105 */     return this.font.isProportional();
/*     */   }
/*     */   
/*     */   public int getSpaceWidth() {
/* 109 */     return this.font.getSpaceWidth();
/*     */   }
/*     */   
/*     */   public int getLineHeight() {
/* 113 */     return this.font.getLineHeight();
/*     */   }
/*     */   
/*     */   public int getBaseLine() {
/* 117 */     return this.font.getBaseLine();
/*     */   }
/*     */   
/*     */   public int getEM() {
/* 121 */     return this.font.getEM();
/*     */   }
/*     */   
/*     */   public int getEX() {
/* 125 */     return this.font.getEX();
/*     */   }
/*     */   
/*     */   public int drawText(AnimationState as, int x, int y, CharSequence str) {
/* 129 */     return drawText(as, x, y, str, 0, str.length());
/*     */   }
/*     */   public int drawText(AnimationState as, int x, int y, CharSequence str, int start, int end) {
/*     */     int width;
/* 133 */     FontState fontState = evalFontState(as);
/* 134 */     x += fontState.offsetX;
/* 135 */     y += fontState.offsetY;
/*     */     
/* 137 */     if (!this.font.prepare()) {
/* 138 */       return 0;
/*     */     }
/*     */     try {
/* 141 */       this.renderer.tintStack.setColor(fontState.color);
/* 142 */       width = this.font.drawText(x, y, str, start, end);
/*     */     } finally {
/* 144 */       this.font.cleanup();
/*     */     } 
/* 146 */     drawLine(fontState, x, y, width);
/* 147 */     return width;
/*     */   }
/*     */   public int drawMultiLineText(AnimationState as, int x, int y, CharSequence str, int width, HAlignment align) {
/*     */     int numLines;
/* 151 */     FontState fontState = evalFontState(as);
/* 152 */     x += fontState.offsetX;
/* 153 */     y += fontState.offsetY;
/*     */     
/* 155 */     if (!this.font.prepare()) {
/* 156 */       return 0;
/*     */     }
/*     */     try {
/* 159 */       this.renderer.tintStack.setColor(fontState.color);
/* 160 */       numLines = this.font.drawMultiLineText(x, y, str, width, align);
/*     */     } finally {
/* 162 */       this.font.cleanup();
/*     */     } 
/* 164 */     if (fontState.style != 0) {
/* 165 */       int[] info = getMultiLineInfo(numLines);
/* 166 */       this.font.computeMultiLineInfo(str, width, align, info);
/* 167 */       drawLines(fontState, x, y, info, numLines);
/*     */     } 
/* 169 */     return numLines * this.font.getLineHeight();
/*     */   }
/*     */   
/*     */   void drawLines(FontState fontState, int x, int y, int[] info, int numLines) {
/* 173 */     if ((fontState.style & 0x1) != 0) {
/* 174 */       this.font.drawMultiLineLines(x, y + this.font.getBaseLine() + fontState.underlineOffset, info, numLines);
/*     */     }
/* 176 */     if ((fontState.style & 0x2) != 0) {
/* 177 */       this.font.drawMultiLineLines(x, y + this.font.getLineHeight() / 2, info, numLines);
/*     */     }
/*     */   }
/*     */   
/*     */   void drawLine(FontState fontState, int x, int y, int width) {
/* 182 */     if ((fontState.style & 0x1) != 0) {
/* 183 */       this.font.drawLine(x, y + this.font.getBaseLine() + fontState.underlineOffset, x + width);
/*     */     }
/* 185 */     if ((fontState.style & 0x2) != 0) {
/* 186 */       this.font.drawLine(x, y + this.font.getLineHeight() / 2, x + width);
/*     */     }
/*     */   }
/*     */   
/*     */   public int computeVisibleGlpyhs(CharSequence str, int start, int end, int availWidth) {
/* 191 */     return this.font.computeVisibleGlpyhs(str, start, end, availWidth);
/*     */   }
/*     */   
/*     */   public int computeTextWidth(CharSequence str) {
/* 195 */     return this.font.computeTextWidth(str, 0, str.length());
/*     */   }
/*     */   
/*     */   public int computeTextWidth(CharSequence str, int start, int end) {
/* 199 */     return this.font.computeTextWidth(str, start, end);
/*     */   }
/*     */   
/*     */   public int computeMultiLineTextWidth(CharSequence str) {
/* 203 */     return this.font.computeMultiLineTextWidth(str);
/*     */   }
/*     */   
/*     */   public FontCache cacheText(FontCache prevCache, CharSequence str) {
/* 207 */     return cacheText(prevCache, str, 0, str.length());
/*     */   }
/*     */   
/*     */   public FontCache cacheText(FontCache prevCache, CharSequence str, int start, int end) {
/* 211 */     LWJGLFontCache cache = (LWJGLFontCache)prevCache;
/* 212 */     if (cache == null) {
/* 213 */       cache = new LWJGLFontCache(this.renderer, this);
/*     */     }
/* 215 */     return this.font.cacheText(cache, str, start, end);
/*     */   }
/*     */   
/*     */   public FontCache cacheMultiLineText(FontCache prevCache, CharSequence str, int width, HAlignment align) {
/* 219 */     LWJGLFontCache cache = (LWJGLFontCache)prevCache;
/* 220 */     if (cache == null) {
/* 221 */       cache = new LWJGLFontCache(this.renderer, this);
/*     */     }
/* 223 */     return this.font.cacheMultiLineText(cache, str, width, align);
/*     */   }
/*     */   
/*     */   public int drawText(int x, int y, AttributedString attributedString) {
/* 227 */     return drawText(x, y, attributedString, 0, attributedString.length(), false);
/*     */   }
/*     */   
/*     */   public int drawText(int x, int y, AttributedString attributedString, int start, int end) {
/* 231 */     return drawText(x, y, attributedString, 0, attributedString.length(), false);
/*     */   }
/*     */   
/*     */   public void drawMultiLineText(int x, int y, AttributedString attributedString) {
/* 235 */     drawText(x, y, attributedString, 0, attributedString.length(), true);
/*     */   }
/*     */   
/*     */   public void drawMultiLineText(int x, int y, AttributedString attributedString, int start, int end) {
/* 239 */     drawText(x, y, attributedString, start, end, true);
/*     */   }
/*     */   
/*     */   private int drawText(int x, int y, AttributedString attributedString, int start, int end, boolean multiLine) {
/* 243 */     int startX = x;
/* 244 */     attributedString.setPosition(start);
/* 245 */     if (!this.font.prepare()) {
/* 246 */       return 0;
/*     */     }
/*     */     try {
/* 249 */       BitmapFont.Glyph lastGlyph = null;
/*     */       do {
/* 251 */         FontState fontState = evalFontState((AnimationState)attributedString);
/* 252 */         x += fontState.offsetX;
/* 253 */         y += fontState.offsetY;
/* 254 */         int runStart = x;
/* 255 */         this.renderer.tintStack.setColor(fontState.color);
/* 256 */         int nextStop = Math.min(end, attributedString.advance());
/* 257 */         if (multiLine) {
/* 258 */           nextStop = TextUtil.indexOf((CharSequence)attributedString, '\n', start, nextStop);
/*     */         }
/* 260 */         while (start < nextStop) {
/* 261 */           char ch = attributedString.charAt(start++);
/* 262 */           BitmapFont.Glyph g = this.font.getGlyph(ch);
/* 263 */           if (g != null) {
/* 264 */             if (lastGlyph != null) {
/* 265 */               x += lastGlyph.getKerning(ch);
/*     */             }
/* 267 */             lastGlyph = g;
/* 268 */             if (g.width > 0) {
/* 269 */               g.draw(x, y);
/*     */             }
/* 271 */             x += g.xadvance;
/*     */           } 
/*     */         } 
/* 274 */         drawLine(fontState, x, y, x - runStart);
/* 275 */         x -= fontState.offsetX;
/* 276 */         y -= fontState.offsetY;
/* 277 */         if (!multiLine || start >= end || attributedString.charAt(start) != '\n')
/* 278 */           continue;  attributedString.setPosition(++start);
/* 279 */         x = startX;
/* 280 */         y += this.font.getLineHeight();
/* 281 */         lastGlyph = null;
/*     */       }
/* 283 */       while (start < end);
/*     */     } finally {
/* 285 */       this.font.cleanup();
/*     */     } 
/* 287 */     return x - startX;
/*     */   }
/*     */   
/*     */   public AttributedStringFontCache cacheText(AttributedStringFontCache prevCache, AttributedString attributedString) {
/* 291 */     return cacheText(prevCache, attributedString, 0, attributedString.length(), false);
/*     */   }
/*     */   
/*     */   public AttributedStringFontCache cacheText(AttributedStringFontCache prevCache, AttributedString attributedString, int start, int end) {
/* 295 */     return cacheText(prevCache, attributedString, start, end, false);
/*     */   }
/*     */   
/*     */   public AttributedStringFontCache cacheMultiLineText(AttributedStringFontCache prevCache, AttributedString attributedString) {
/* 299 */     return cacheText(prevCache, attributedString, 0, attributedString.length(), true);
/*     */   }
/*     */   
/*     */   public AttributedStringFontCache cacheMultiLineText(AttributedStringFontCache prevCache, AttributedString attributedString, int start, int end) {
/* 303 */     return cacheText(prevCache, attributedString, start, end, true);
/*     */   }
/*     */   
/*     */   private AttributedStringFontCache cacheText(AttributedStringFontCache prevCache, AttributedString attributedString, int start, int end, boolean multiLine) {
/* 307 */     if (end <= start) {
/* 308 */       return null;
/*     */     }
/* 310 */     LWJGLAttributedStringFontCache cache = (LWJGLAttributedStringFontCache)prevCache;
/* 311 */     if (cache == null) {
/* 312 */       cache = new LWJGLAttributedStringFontCache(this.renderer, this.font);
/*     */     }
/* 314 */     FloatBuffer va = cache.allocate(end - start);
/* 315 */     attributedString.setPosition(start);
/* 316 */     BitmapFont.Glyph lastGlyph = null;
/* 317 */     int x = 0;
/* 318 */     int y = 0;
/* 319 */     int width = 0;
/*     */     do {
/* 321 */       FontState fontState = evalFontState((AnimationState)attributedString);
/*     */       
/* 323 */       x += fontState.offsetX;
/* 324 */       y += fontState.offsetY;
/* 325 */       int runLength = 0;
/* 326 */       int xStart = x;
/*     */       
/* 328 */       int nextStop = Math.min(end, attributedString.advance());
/* 329 */       while (nextStop < end && fontState == evalFontState((AnimationState)attributedString)) {
/* 330 */         nextStop = Math.min(end, attributedString.advance());
/*     */       }
/*     */       
/* 333 */       if (multiLine) {
/* 334 */         nextStop = TextUtil.indexOf((CharSequence)attributedString, '\n', start, nextStop);
/*     */       }
/*     */       
/* 337 */       while (start < nextStop) {
/* 338 */         char ch = attributedString.charAt(start++);
/* 339 */         BitmapFont.Glyph g = this.font.getGlyph(ch);
/* 340 */         if (g != null) {
/* 341 */           if (lastGlyph != null) {
/* 342 */             x += lastGlyph.getKerning(ch);
/*     */           }
/* 344 */           lastGlyph = g;
/* 345 */           if (g.width > 0 && g.height > 0) {
/* 346 */             g.draw(va, x, y);
/* 347 */             runLength++;
/*     */           } 
/* 349 */           x += g.xadvance;
/*     */         } 
/*     */       } 
/*     */       
/* 353 */       x -= fontState.offsetX;
/* 354 */       y -= fontState.offsetY;
/*     */       
/* 356 */       if (runLength > 0 || fontState.style != 0) {
/* 357 */         LWJGLAttributedStringFontCache.Run run = cache.addRun();
/* 358 */         run.state = fontState;
/* 359 */         run.numVertices = runLength * 4;
/* 360 */         run.x = xStart;
/* 361 */         run.xend = x;
/* 362 */         run.y = y;
/*     */       } 
/*     */       
/* 365 */       if (!multiLine || start >= end || attributedString.charAt(start) != '\n')
/* 366 */         continue;  attributedString.setPosition(++start);
/* 367 */       width = Math.max(width, x);
/* 368 */       x = 0;
/* 369 */       y += this.font.getLineHeight();
/* 370 */       lastGlyph = null;
/*     */     }
/* 372 */     while (start < end);
/*     */     
/* 374 */     if (x > 0) {
/* 375 */       width = Math.max(width, x);
/* 376 */       y += this.font.getLineHeight();
/*     */     } 
/*     */     
/* 379 */     cache.width = width;
/* 380 */     cache.height = y;
/* 381 */     return cache;
/*     */   }
/*     */   
/*     */   public static class FontState {
/*     */     Color color;
/*     */     int offsetX;
/*     */     int offsetY;
/*     */     int style;
/*     */     int underlineOffset;
/*     */     
/*     */     FontState(FontParameter fontParam) {
/* 392 */       int lineStyle = 0;
/* 393 */       if (((Boolean)fontParam.get(FontParameter.UNDERLINE)).booleanValue()) {
/* 394 */         lineStyle |= 0x1;
/*     */       }
/* 396 */       if (((Boolean)fontParam.get(FontParameter.LINETHROUGH)).booleanValue()) {
/* 397 */         lineStyle |= 0x2;
/*     */       }
/*     */       
/* 400 */       this.color = (Color)fontParam.get(FontParameter.COLOR);
/* 401 */       this.offsetX = ((Integer)fontParam.get(LWJGLRenderer.FONTPARAM_OFFSET_X)).intValue();
/* 402 */       this.offsetY = ((Integer)fontParam.get(LWJGLRenderer.FONTPARAM_OFFSET_Y)).intValue();
/* 403 */       this.style = lineStyle;
/* 404 */       this.underlineOffset = ((Integer)fontParam.get(LWJGLRenderer.FONTPARAM_UNDERLINE_OFFSET)).intValue();
/*     */     }
/*     */ 
/*     */     
/*     */     public FontState(FontState oldState) {
/* 409 */       this.color = oldState.color;
/* 410 */       this.offsetX = oldState.offsetX;
/* 411 */       this.offsetY = oldState.offsetY;
/* 412 */       this.style = oldState.style;
/* 413 */       this.underlineOffset = oldState.underlineOffset;
/*     */     }
/*     */ 
/*     */     
/*     */     public FontState(Color color, int offsetX, int offsetY, int style, int underlineOffset) {
/* 418 */       this.color = color;
/* 419 */       this.offsetX = offsetX;
/* 420 */       this.offsetY = offsetY;
/* 421 */       this.style = style;
/* 422 */       this.underlineOffset = underlineOffset;
/*     */     }
/*     */     
/*     */     public Color getColor() {
/* 426 */       return this.color;
/*     */     }
/*     */     
/*     */     public boolean getLineThrough() {
/* 430 */       return ((this.style & 0x2) == 2);
/*     */     }
/*     */     
/*     */     public int getOffsetX() {
/* 434 */       return this.offsetX;
/*     */     }
/*     */     
/*     */     public int getOffsetY() {
/* 438 */       return this.offsetY;
/*     */     }
/*     */     
/*     */     public boolean getUnderline() {
/* 442 */       return ((this.style & 0x1) == 1);
/*     */     }
/*     */     
/*     */     public int getUnderlineOffset() {
/* 446 */       return this.underlineOffset;
/*     */     }
/*     */     
/*     */     public void setColor(Color col) {
/* 450 */       this.color = col;
/*     */     }
/*     */     
/*     */     public void setUnderlineOffset(int i) {
/* 454 */       this.underlineOffset = i;
/*     */     }
/*     */     
/*     */     public void setUnderline(boolean val) {
/* 458 */       if (getUnderline() != val) {
/* 459 */         this.style ^= 0x1;
/*     */       }
/*     */     }
/*     */     
/*     */     public void setOffsetY(int i) {
/* 464 */       this.offsetY = i;
/*     */     }
/*     */     
/*     */     public void setOffsetX(int i) {
/* 468 */       this.offsetX = i;
/*     */     }
/*     */     
/*     */     public void setLineThrough(boolean val) {
/* 472 */       if (getLineThrough() != val)
/* 473 */         this.style ^= 0x2; 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\LWJGLFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */