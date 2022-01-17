/*     */ package de.matthiasmann.twl.renderer.lwjgl;
/*     */ 
/*     */ import de.matthiasmann.twl.HAlignment;
/*     */ import de.matthiasmann.twl.renderer.FontCache;
/*     */ import de.matthiasmann.twl.utils.ParameterStringParser;
/*     */ import de.matthiasmann.twl.utils.TextUtil;
/*     */ import de.matthiasmann.twl.utils.XMLParser;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.HashMap;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.Util;
/*     */ import org.xmlpull.v1.XmlPullParserException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BitmapFont
/*     */ {
/*     */   private static final int LOG2_PAGE_SIZE = 9;
/*     */   private static final int PAGE_SIZE = 512;
/*     */   private static final int PAGES = 128;
/*     */   private final LWJGLTexture texture;
/*     */   private final Glyph[][] glyphs;
/*     */   private final int lineHeight;
/*     */   private final int baseLine;
/*     */   private final int spaceWidth;
/*     */   private final int ex;
/*     */   private final boolean proportional;
/*     */   
/*     */   static class Glyph
/*     */     extends TextureAreaBase
/*     */   {
/*     */     short xoffset;
/*     */     short yoffset;
/*     */     short xadvance;
/*     */     byte[][] kerning;
/*     */     
/*     */     public Glyph(int x, int y, int width, int height, int texWidth, int texHeight) {
/*  69 */       super(x, y, (height <= 0) ? 0 : width, height, texWidth, texHeight);
/*     */     }
/*     */     
/*     */     void draw(int x, int y) {
/*  73 */       drawQuad(x + this.xoffset, y + this.yoffset, this.width, this.height);
/*     */     }
/*     */     
/*     */     void draw(FloatBuffer va, int x, int y) {
/*  77 */       x += this.xoffset;
/*  78 */       y += this.yoffset;
/*  79 */       va.put(this.tx0).put(this.ty0).put(x).put(y);
/*  80 */       va.put(this.tx0).put(this.ty1).put(x).put((y + this.height));
/*  81 */       va.put(this.tx1).put(this.ty1).put((x + this.width)).put((y + this.height));
/*  82 */       va.put(this.tx1).put(this.ty0).put((x + this.width)).put(y);
/*     */     }
/*     */     
/*     */     int getKerning(char ch) {
/*  86 */       if (this.kerning != null) {
/*  87 */         byte[] page = this.kerning[ch >>> 9];
/*  88 */         if (page != null) {
/*  89 */           return page[ch & 0x1FF];
/*     */         }
/*     */       } 
/*  92 */       return 0;
/*     */     }
/*     */     
/*     */     void setKerning(int ch, int value) {
/*  96 */       if (this.kerning == null) {
/*  97 */         this.kerning = new byte[128][];
/*     */       }
/*  99 */       byte[] page = this.kerning[ch >>> 9];
/* 100 */       if (page == null) {
/* 101 */         this.kerning[ch >>> 9] = page = new byte[512];
/*     */       }
/* 103 */       page[ch & 0x1FF] = (byte)value;
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
/*     */   
/*     */   public BitmapFont(LWJGLRenderer renderer, XMLParser xmlp, URL baseUrl) throws XmlPullParserException, IOException {
/* 116 */     xmlp.require(2, null, "font");
/* 117 */     xmlp.nextTag();
/* 118 */     xmlp.require(2, null, "info");
/* 119 */     xmlp.ignoreOtherAttributes();
/* 120 */     xmlp.nextTag();
/* 121 */     xmlp.require(3, null, "info");
/* 122 */     xmlp.nextTag();
/* 123 */     xmlp.require(2, null, "common");
/* 124 */     this.lineHeight = xmlp.parseIntFromAttribute("lineHeight");
/* 125 */     this.baseLine = xmlp.parseIntFromAttribute("base");
/* 126 */     if (xmlp.parseIntFromAttribute("pages", 1) != 1) {
/* 127 */       throw new UnsupportedOperationException("multi page fonts not supported");
/*     */     }
/* 129 */     if (xmlp.parseIntFromAttribute("packed", 0) != 0) {
/* 130 */       throw new UnsupportedOperationException("packed fonts not supported");
/*     */     }
/* 132 */     xmlp.ignoreOtherAttributes();
/* 133 */     xmlp.nextTag();
/* 134 */     xmlp.require(3, null, "common");
/* 135 */     xmlp.nextTag();
/* 136 */     xmlp.require(2, null, "pages");
/* 137 */     xmlp.nextTag();
/* 138 */     xmlp.require(2, null, "page");
/* 139 */     int pageId = Integer.parseInt(xmlp.getAttributeValue(null, "id"));
/* 140 */     if (pageId != 0) {
/* 141 */       throw new UnsupportedOperationException("only page id 0 supported");
/*     */     }
/* 143 */     String textureName = xmlp.getAttributeValue(null, "file");
/* 144 */     Util.checkGLError();
/* 145 */     this.texture = renderer.load(new URL(baseUrl, textureName), LWJGLTexture.Format.ALPHA, LWJGLTexture.Filter.NEAREST);
/* 146 */     Util.checkGLError();
/* 147 */     xmlp.nextTag();
/* 148 */     xmlp.require(3, null, "page");
/* 149 */     xmlp.nextTag();
/* 150 */     xmlp.require(3, null, "pages");
/* 151 */     xmlp.nextTag();
/* 152 */     xmlp.require(2, null, "chars");
/* 153 */     xmlp.ignoreOtherAttributes();
/* 154 */     xmlp.nextTag();
/*     */     
/* 156 */     int firstXAdvance = Integer.MIN_VALUE;
/* 157 */     boolean prop = true;
/*     */     
/* 159 */     this.glyphs = new Glyph[128][];
/* 160 */     while (!xmlp.isEndTag()) {
/* 161 */       xmlp.require(2, null, "char");
/* 162 */       int idx = xmlp.parseIntFromAttribute("id");
/* 163 */       int x = xmlp.parseIntFromAttribute("x");
/* 164 */       int y = xmlp.parseIntFromAttribute("y");
/* 165 */       int w = xmlp.parseIntFromAttribute("width");
/* 166 */       int h = xmlp.parseIntFromAttribute("height");
/* 167 */       if (xmlp.parseIntFromAttribute("page", 0) != 0) {
/* 168 */         throw xmlp.error("Multiple pages not supported");
/*     */       }
/* 170 */       int chnl = xmlp.parseIntFromAttribute("chnl", 0);
/* 171 */       Glyph glyph = new Glyph(x, y, w, h, this.texture.getTexWidth(), this.texture.getTexHeight());
/* 172 */       glyph.xoffset = Short.parseShort(xmlp.getAttributeNotNull("xoffset"));
/* 173 */       glyph.yoffset = Short.parseShort(xmlp.getAttributeNotNull("yoffset"));
/* 174 */       glyph.xadvance = Short.parseShort(xmlp.getAttributeNotNull("xadvance"));
/* 175 */       addGlyph(idx, glyph);
/* 176 */       xmlp.nextTag();
/* 177 */       xmlp.require(3, null, "char");
/* 178 */       xmlp.nextTag();
/* 179 */       if (glyph.xadvance != firstXAdvance && glyph.xadvance > 0) {
/* 180 */         if (firstXAdvance == Integer.MIN_VALUE) {
/* 181 */           firstXAdvance = glyph.xadvance; continue;
/*     */         } 
/* 183 */         prop = false;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 188 */     xmlp.require(3, null, "chars");
/* 189 */     xmlp.nextTag();
/* 190 */     if (xmlp.isStartTag()) {
/* 191 */       xmlp.require(2, null, "kernings");
/* 192 */       xmlp.ignoreOtherAttributes();
/* 193 */       xmlp.nextTag();
/* 194 */       while (!xmlp.isEndTag()) {
/* 195 */         xmlp.require(2, null, "kerning");
/* 196 */         int first = xmlp.parseIntFromAttribute("first");
/* 197 */         int second = xmlp.parseIntFromAttribute("second");
/* 198 */         int amount = xmlp.parseIntFromAttribute("amount");
/* 199 */         addKerning(first, second, amount);
/* 200 */         xmlp.nextTag();
/* 201 */         xmlp.require(3, null, "kerning");
/* 202 */         xmlp.nextTag();
/*     */       } 
/* 204 */       xmlp.require(3, null, "kernings");
/* 205 */       xmlp.nextTag();
/*     */     } 
/* 207 */     xmlp.require(3, null, "font");
/*     */     
/* 209 */     Glyph g = getGlyph(' ');
/* 210 */     this.spaceWidth = (g != null) ? (g.xadvance + g.width) : 1;
/*     */     
/* 212 */     Glyph gx = getGlyph('x');
/* 213 */     this.ex = (gx != null) ? gx.height : 1;
/*     */     
/* 215 */     this.proportional = prop;
/*     */   }
/*     */   
/*     */   public BitmapFont(LWJGLRenderer renderer, Reader reader, URL baseUrl) throws IOException {
/* 219 */     BufferedReader br = new BufferedReader(reader);
/* 220 */     HashMap<String, String> params = new HashMap<String, String>();
/* 221 */     parseFntLine(br, "info");
/* 222 */     parseFntLine(parseFntLine(br, "common"), params);
/* 223 */     this.lineHeight = parseInt(params, "lineHeight");
/* 224 */     this.baseLine = parseInt(params, "base");
/* 225 */     if (parseInt(params, "pages", 1) != 1) {
/* 226 */       throw new UnsupportedOperationException("multi page fonts not supported");
/*     */     }
/* 228 */     if (parseInt(params, "packed", 0) != 0) {
/* 229 */       throw new UnsupportedOperationException("packed fonts not supported");
/*     */     }
/* 231 */     parseFntLine(parseFntLine(br, "page"), params);
/* 232 */     if (parseInt(params, "id", 0) != 0) {
/* 233 */       throw new UnsupportedOperationException("only page id 0 supported");
/*     */     }
/* 235 */     this.texture = renderer.load(new URL(baseUrl, getParam(params, "file")), LWJGLTexture.Format.ALPHA, LWJGLTexture.Filter.NEAREST);
/*     */     
/* 237 */     this.glyphs = new Glyph[128][];
/* 238 */     parseFntLine(parseFntLine(br, "chars"), params);
/* 239 */     int charCount = parseInt(params, "count");
/* 240 */     int firstXAdvance = Integer.MIN_VALUE;
/* 241 */     boolean prop = true;
/* 242 */     for (int charIdx = 0; charIdx < charCount; charIdx++) {
/* 243 */       parseFntLine(parseFntLine(br, "char"), params);
/* 244 */       int idx = parseInt(params, "id");
/* 245 */       int x = parseInt(params, "x");
/* 246 */       int y = parseInt(params, "y");
/* 247 */       int w = parseInt(params, "width");
/* 248 */       int h = parseInt(params, "height");
/* 249 */       if (parseInt(params, "page", 0) != 0) {
/* 250 */         throw new IOException("Multiple pages not supported");
/*     */       }
/* 252 */       Glyph glyph = new Glyph(x, y, w, h, this.texture.getTexWidth(), this.texture.getTexHeight());
/* 253 */       glyph.xoffset = parseShort(params, "xoffset");
/* 254 */       glyph.yoffset = parseShort(params, "yoffset");
/* 255 */       glyph.xadvance = parseShort(params, "xadvance");
/* 256 */       addGlyph(idx, glyph);
/*     */       
/* 258 */       if (glyph.xadvance != firstXAdvance && glyph.xadvance > 0) {
/* 259 */         if (firstXAdvance == Integer.MIN_VALUE) {
/* 260 */           firstXAdvance = glyph.xadvance;
/*     */         } else {
/* 262 */           prop = false;
/*     */         } 
/*     */       }
/*     */     } 
/* 266 */     parseFntLine(parseFntLine(br, "kernings"), params);
/* 267 */     int kerningCount = parseInt(params, "count");
/* 268 */     for (int kerningIdx = 0; kerningIdx < kerningCount; kerningIdx++) {
/* 269 */       parseFntLine(parseFntLine(br, "kerning"), params);
/* 270 */       int first = parseInt(params, "first");
/* 271 */       int second = parseInt(params, "second");
/* 272 */       int amount = parseInt(params, "amount");
/* 273 */       addKerning(first, second, amount);
/*     */     } 
/*     */     
/* 276 */     Glyph g = getGlyph(' ');
/* 277 */     this.spaceWidth = (g != null) ? (g.xadvance + g.width) : 1;
/*     */     
/* 279 */     Glyph gx = getGlyph('x');
/* 280 */     this.ex = (gx != null) ? gx.height : 1;
/*     */     
/* 282 */     this.proportional = prop;
/*     */   }
/*     */   
/*     */   public static BitmapFont loadFont(LWJGLRenderer renderer, URL url) throws IOException {
/* 286 */     boolean startTagSeen = false;
/*     */     try {
/* 288 */       XMLParser xmlp = new XMLParser(url);
/*     */       try {
/* 290 */         xmlp.require(0, null, null);
/* 291 */         xmlp.nextTag();
/* 292 */         startTagSeen = true;
/* 293 */         Util.checkGLError();
/* 294 */         return new BitmapFont(renderer, xmlp, url);
/*     */       } finally {
/* 296 */         xmlp.close();
/*     */       } 
/* 298 */     } catch (XmlPullParserException ex) {
/* 299 */       if (startTagSeen) {
/* 300 */         throw (IOException)(new IOException()).initCause(ex);
/*     */       }
/* 302 */       InputStream is = url.openStream();
/*     */       try {
/* 304 */         InputStreamReader isr = new InputStreamReader(is, "UTF8");
/* 305 */         return new BitmapFont(renderer, isr, url);
/*     */       } finally {
/* 307 */         is.close();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isProportional() {
/* 313 */     return this.proportional;
/*     */   }
/*     */   
/*     */   public int getBaseLine() {
/* 317 */     return this.baseLine;
/*     */   }
/*     */   
/*     */   public int getLineHeight() {
/* 321 */     return this.lineHeight;
/*     */   }
/*     */   
/*     */   public int getSpaceWidth() {
/* 325 */     return this.spaceWidth;
/*     */   }
/*     */   
/*     */   public int getEM() {
/* 329 */     return this.lineHeight;
/*     */   }
/*     */   
/*     */   public int getEX() {
/* 333 */     return this.ex;
/*     */   }
/*     */   
/*     */   public void destroy() {
/* 337 */     this.texture.destroy();
/*     */   }
/*     */   
/*     */   private void addGlyph(int idx, Glyph g) {
/* 341 */     if (idx <= 65535) {
/* 342 */       Glyph[] page = this.glyphs[idx >> 9];
/* 343 */       if (page == null) {
/* 344 */         this.glyphs[idx >> 9] = page = new Glyph[512];
/*     */       }
/* 346 */       page[idx & 0x1FF] = g;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addKerning(int first, int second, int amount) {
/* 351 */     if (first >= 0 && first <= 65535 && second >= 0 && second <= 65535) {
/*     */       
/* 353 */       Glyph g = getGlyph((char)first);
/* 354 */       if (g != null) {
/* 355 */         g.setKerning(second, amount);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   final Glyph getGlyph(char ch) {
/* 361 */     Glyph[] page = this.glyphs[ch >> 9];
/* 362 */     if (page != null) {
/* 363 */       return page[ch & 0x1FF];
/*     */     }
/* 365 */     return null;
/*     */   }
/*     */   
/*     */   public int computeTextWidth(CharSequence str, int start, int end) {
/* 369 */     int width = 0;
/* 370 */     Glyph lastGlyph = null;
/* 371 */     while (start < end) {
/* 372 */       lastGlyph = getGlyph(str.charAt(start++));
/* 373 */       if (lastGlyph != null) {
/* 374 */         width = lastGlyph.xadvance;
/*     */         break;
/*     */       } 
/*     */     } 
/* 378 */     while (start < end) {
/* 379 */       char ch = str.charAt(start++);
/* 380 */       Glyph g = getGlyph(ch);
/* 381 */       if (g != null) {
/* 382 */         width += lastGlyph.getKerning(ch);
/* 383 */         lastGlyph = g;
/* 384 */         width += g.xadvance;
/*     */       } 
/*     */     } 
/* 387 */     return width;
/*     */   }
/*     */   
/*     */   public int computeVisibleGlpyhs(CharSequence str, int start, int end, int availWidth) {
/* 391 */     int index = start;
/* 392 */     int width = 0;
/* 393 */     Glyph lastGlyph = null;
/* 394 */     for (; index < end; index++) {
/* 395 */       char ch = str.charAt(index);
/* 396 */       Glyph g = getGlyph(ch);
/* 397 */       if (g != null) {
/* 398 */         if (lastGlyph != null) {
/* 399 */           width += lastGlyph.getKerning(ch);
/*     */         }
/* 401 */         lastGlyph = g;
/* 402 */         if (this.proportional) {
/* 403 */           width += g.xadvance;
/* 404 */           if (width > availWidth) {
/*     */             break;
/*     */           }
/*     */         } else {
/* 408 */           if (width + g.width + g.xoffset > availWidth) {
/*     */             break;
/*     */           }
/* 411 */           width += g.xadvance;
/*     */         } 
/*     */       } 
/*     */     } 
/* 415 */     return index - start;
/*     */   }
/*     */   
/*     */   protected int drawText(int x, int y, CharSequence str, int start, int end) {
/* 419 */     int startX = x;
/* 420 */     Glyph lastGlyph = null;
/* 421 */     while (start < end) {
/* 422 */       lastGlyph = getGlyph(str.charAt(start++));
/* 423 */       if (lastGlyph != null) {
/* 424 */         if (lastGlyph.width > 0) {
/* 425 */           lastGlyph.draw(x, y);
/*     */         }
/* 427 */         x += lastGlyph.xadvance;
/*     */         break;
/*     */       } 
/*     */     } 
/* 431 */     while (start < end) {
/* 432 */       char ch = str.charAt(start++);
/* 433 */       Glyph g = getGlyph(ch);
/* 434 */       if (g != null) {
/* 435 */         x += lastGlyph.getKerning(ch);
/* 436 */         lastGlyph = g;
/* 437 */         if (g.width > 0) {
/* 438 */           g.draw(x, y);
/*     */         }
/* 440 */         x += g.xadvance;
/*     */       } 
/*     */     } 
/* 443 */     return x - startX;
/*     */   }
/*     */   
/*     */   protected int drawMultiLineText(int x, int y, CharSequence str, int width, HAlignment align) {
/* 447 */     int start = 0;
/* 448 */     int numLines = 0;
/* 449 */     while (start < str.length()) {
/* 450 */       int lineEnd = TextUtil.indexOf(str, '\n', start);
/* 451 */       int xoff = 0;
/* 452 */       if (align != HAlignment.LEFT) {
/* 453 */         int lineWidth = computeTextWidth(str, start, lineEnd);
/* 454 */         xoff = width - lineWidth;
/* 455 */         if (align == HAlignment.CENTER) {
/* 456 */           xoff /= 2;
/*     */         }
/*     */       } 
/* 459 */       drawText(x + xoff, y, str, start, lineEnd);
/* 460 */       start = lineEnd + 1;
/* 461 */       y += this.lineHeight;
/* 462 */       numLines++;
/*     */     } 
/* 464 */     return numLines;
/*     */   }
/*     */   
/*     */   public void computeMultiLineInfo(CharSequence str, int width, HAlignment align, int[] multiLineInfo) {
/* 468 */     int start = 0;
/* 469 */     int idx = 0;
/* 470 */     while (start < str.length()) {
/* 471 */       int lineEnd = TextUtil.indexOf(str, '\n', start);
/* 472 */       int lineWidth = computeTextWidth(str, start, lineEnd);
/* 473 */       int xoff = width - lineWidth;
/* 474 */       if (align == HAlignment.LEFT) {
/* 475 */         xoff = 0;
/* 476 */       } else if (align == HAlignment.CENTER) {
/* 477 */         xoff /= 2;
/*     */       } 
/* 479 */       multiLineInfo[idx++] = lineWidth << 16 | xoff & 0xFFFF;
/* 480 */       start = lineEnd + 1;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void beginLine() {
/* 485 */     GL11.glDisable(3553);
/* 486 */     GL11.glBegin(7);
/*     */   }
/*     */   
/*     */   protected void endLine() {
/* 490 */     GL11.glEnd();
/* 491 */     GL11.glEnable(3553);
/*     */   }
/*     */   
/*     */   public void drawMultiLineLines(int x, int y, int[] multiLineInfo, int numLines) {
/* 495 */     beginLine();
/*     */     try {
/* 497 */       for (int i = 0; i < numLines; i++) {
/* 498 */         int info = multiLineInfo[i];
/* 499 */         int xoff = x + (short)info;
/* 500 */         int lineWidth = info >>> 16;
/* 501 */         GL11.glVertex2i(xoff, y);
/* 502 */         GL11.glVertex2i(xoff + lineWidth, y);
/* 503 */         GL11.glVertex2i(xoff + lineWidth, y + 1);
/* 504 */         GL11.glVertex2i(xoff, y + 1);
/* 505 */         y += this.lineHeight;
/*     */       } 
/*     */     } finally {
/* 508 */       endLine();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawLine(int x0, int y, int x1) {
/* 513 */     beginLine();
/* 514 */     GL11.glVertex2i(x0, y);
/* 515 */     GL11.glVertex2i(x1, y);
/* 516 */     GL11.glVertex2i(x1, y + 1);
/* 517 */     GL11.glVertex2i(x0, y + 1);
/* 518 */     endLine();
/*     */   }
/*     */   
/*     */   public int computeMultiLineTextWidth(CharSequence str) {
/* 522 */     int start = 0;
/* 523 */     int width = 0;
/* 524 */     while (start < str.length()) {
/* 525 */       int lineEnd = TextUtil.indexOf(str, '\n', start);
/* 526 */       int lineWidth = computeTextWidth(str, start, lineEnd);
/* 527 */       width = Math.max(width, lineWidth);
/* 528 */       start = lineEnd + 1;
/*     */     } 
/* 530 */     return width;
/*     */   }
/*     */   
/*     */   public FontCache cacheMultiLineText(LWJGLFontCache cache, CharSequence str, int width, HAlignment align) {
/* 534 */     if (cache.startCompile()) {
/* 535 */       int numLines = 0;
/*     */       try {
/* 537 */         if (prepare()) {
/*     */           try {
/* 539 */             numLines = drawMultiLineText(0, 0, str, width, align);
/*     */           } finally {
/* 541 */             cleanup();
/*     */           } 
/* 543 */           computeMultiLineInfo(str, width, align, cache.getMultiLineInfo(numLines));
/*     */         } 
/*     */       } finally {
/* 546 */         cache.endCompile(width, numLines * this.lineHeight);
/*     */       } 
/* 548 */       return cache;
/*     */     } 
/* 550 */     return null;
/*     */   }
/*     */   
/*     */   public FontCache cacheText(LWJGLFontCache cache, CharSequence str, int start, int end) {
/* 554 */     if (cache.startCompile()) {
/* 555 */       int width = 0;
/*     */       try {
/* 557 */         if (prepare()) {
/*     */           try {
/* 559 */             width = drawText(0, 0, str, start, end);
/*     */           } finally {
/* 561 */             cleanup();
/*     */           } 
/*     */         }
/*     */       } finally {
/* 565 */         cache.endCompile(width, getLineHeight());
/*     */       } 
/* 567 */       return cache;
/*     */     } 
/* 569 */     return null;
/*     */   }
/*     */   
/*     */   boolean bind() {
/* 573 */     return this.texture.bind();
/*     */   }
/*     */   
/*     */   protected boolean prepare() {
/* 577 */     if (this.texture.bind()) {
/* 578 */       GL11.glBegin(7);
/* 579 */       return true;
/*     */     } 
/* 581 */     return false;
/*     */   }
/*     */   
/*     */   protected void cleanup() {
/* 585 */     GL11.glEnd();
/*     */   }
/*     */   
/*     */   private static String parseFntLine(BufferedReader br, String tag) throws IOException {
/* 589 */     String line = br.readLine();
/* 590 */     if (line == null || line.length() <= tag.length() || line.charAt(tag.length()) != ' ' || !line.startsWith(tag))
/*     */     {
/* 592 */       throw new IOException("'" + tag + "' line expected");
/*     */     }
/* 594 */     return line;
/*     */   }
/*     */   
/*     */   private static void parseFntLine(String line, HashMap<String, String> params) {
/* 598 */     params.clear();
/* 599 */     ParameterStringParser psp = new ParameterStringParser(line, ' ', '=');
/* 600 */     while (psp.next()) {
/* 601 */       params.put(psp.getKey(), psp.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   private static String getParam(HashMap<String, String> params, String key) throws IOException {
/* 606 */     String value = params.get(key);
/* 607 */     if (value == null) {
/* 608 */       throw new IOException("Required parameter '" + key + "' not found");
/*     */     }
/* 610 */     return value;
/*     */   }
/*     */   
/*     */   private static int parseInt(HashMap<String, String> params, String key) throws IOException {
/* 614 */     String value = getParam(params, key);
/*     */     try {
/* 616 */       return Integer.parseInt(value);
/* 617 */     } catch (IllegalArgumentException ex) {
/* 618 */       throw canParseParam(key, value, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int parseInt(HashMap<String, String> params, String key, int defaultValue) throws IOException {
/* 623 */     String value = params.get(key);
/* 624 */     if (value == null) {
/* 625 */       return defaultValue;
/*     */     }
/*     */     try {
/* 628 */       return Integer.parseInt(value);
/* 629 */     } catch (IllegalArgumentException ex) {
/* 630 */       throw canParseParam(key, value, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static short parseShort(HashMap<String, String> params, String key) throws IOException {
/* 635 */     String value = getParam(params, key);
/*     */     try {
/* 637 */       return Short.parseShort(value);
/* 638 */     } catch (IllegalArgumentException ex) {
/* 639 */       throw canParseParam(key, value, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static IOException canParseParam(String key, String value, IllegalArgumentException ex) {
/* 644 */     return (IOException)(new IOException("Can't parse parameter: " + key + '=' + value)).initCause(ex);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\BitmapFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */