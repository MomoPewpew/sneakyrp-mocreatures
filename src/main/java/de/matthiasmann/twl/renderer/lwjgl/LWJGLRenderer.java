/*     */ package de.matthiasmann.twl.renderer.lwjgl;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.Rect;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.CacheContext;
/*     */ import de.matthiasmann.twl.renderer.DynamicImage;
/*     */ import de.matthiasmann.twl.renderer.Font;
/*     */ import de.matthiasmann.twl.renderer.FontMapper;
/*     */ import de.matthiasmann.twl.renderer.FontParameter;
/*     */ import de.matthiasmann.twl.renderer.Gradient;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import de.matthiasmann.twl.renderer.LineRenderer;
/*     */ import de.matthiasmann.twl.renderer.MouseCursor;
/*     */ import de.matthiasmann.twl.renderer.OffscreenRenderer;
/*     */ import de.matthiasmann.twl.renderer.Renderer;
/*     */ import de.matthiasmann.twl.renderer.Texture;
/*     */ import de.matthiasmann.twl.utils.ClipStack;
/*     */ import de.matthiasmann.twl.utils.StateSelect;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Locale;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.Sys;
/*     */ import org.lwjgl.input.Cursor;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.ContextCapabilities;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GLContext;
/*     */ import org.lwjgl.opengl.Util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LWJGLRenderer
/*     */   implements Renderer, LineRenderer
/*     */ {
/*  81 */   public static final AnimationState.StateKey STATE_LEFT_MOUSE_BUTTON = AnimationState.StateKey.get("leftMouseButton");
/*  82 */   public static final AnimationState.StateKey STATE_MIDDLE_MOUSE_BUTTON = AnimationState.StateKey.get("middleMouseButton");
/*  83 */   public static final AnimationState.StateKey STATE_RIGHT_MOUSE_BUTTON = AnimationState.StateKey.get("rightMouseButton");
/*     */   
/*  85 */   public static final FontParameter.Parameter<Integer> FONTPARAM_OFFSET_X = FontParameter.newParameter("offsetX", Integer.valueOf(0));
/*  86 */   public static final FontParameter.Parameter<Integer> FONTPARAM_OFFSET_Y = FontParameter.newParameter("offsetY", Integer.valueOf(0));
/*  87 */   public static final FontParameter.Parameter<Integer> FONTPARAM_UNDERLINE_OFFSET = FontParameter.newParameter("underlineOffset", Integer.valueOf(0));
/*     */   
/*     */   private final IntBuffer ib16;
/*     */   
/*     */   final int maxTextureSize;
/*     */   
/*     */   private int viewportX;
/*     */   
/*     */   private int viewportBottom;
/*     */   
/*     */   private int width;
/*     */   private int height;
/*     */   private boolean hasScissor;
/*     */   private final TintStack tintStateRoot;
/*     */   private final Cursor emptyCursor;
/*     */   private boolean useQuadsForLines;
/*     */   private boolean useSWMouseCursors;
/*     */   private SWCursor swCursor;
/*     */   private int mouseX;
/*     */   private int mouseY;
/*     */   private LWJGLCacheContext cacheContext;
/*     */   private FontMapper fontMapper;
/*     */   final SWCursorAnimState swCursorAnimState;
/*     */   final ArrayList<TextureArea> textureAreas;
/*     */   final ArrayList<TextureAreaRotated> rotatedTextureAreas;
/*     */   final ArrayList<LWJGLDynamicImage> dynamicImages;
/*     */   protected TintStack tintStack;
/*     */   protected final ClipStack clipStack;
/*     */   protected final Rect clipRectTemp;
/*     */   
/*     */   public LWJGLRenderer() throws LWJGLException {
/* 118 */     this.ib16 = BufferUtils.createIntBuffer(16);
/* 119 */     this.textureAreas = new ArrayList<TextureArea>();
/* 120 */     this.rotatedTextureAreas = new ArrayList<TextureAreaRotated>();
/* 121 */     this.dynamicImages = new ArrayList<LWJGLDynamicImage>();
/* 122 */     this.tintStateRoot = new TintStack();
/* 123 */     this.tintStack = this.tintStateRoot;
/* 124 */     this.clipStack = new ClipStack();
/* 125 */     this.clipRectTemp = new Rect();
/* 126 */     syncViewportSize();
/*     */     
/* 128 */     GL11.glGetInteger(3379, this.ib16);
/* 129 */     this.maxTextureSize = this.ib16.get(0);
/*     */     
/* 131 */     if (Mouse.isCreated()) {
/* 132 */       int minCursorSize = Cursor.getMinCursorSize();
/* 133 */       IntBuffer tmp = BufferUtils.createIntBuffer(minCursorSize * minCursorSize);
/* 134 */       this.emptyCursor = new Cursor(minCursorSize, minCursorSize, minCursorSize / 2, minCursorSize / 2, 1, tmp, null);
/*     */     } else {
/*     */       
/* 137 */       this.emptyCursor = null;
/*     */     } 
/*     */     
/* 140 */     this.swCursorAnimState = new SWCursorAnimState();
/*     */   }
/*     */   
/*     */   public boolean isUseQuadsForLines() {
/* 144 */     return this.useQuadsForLines;
/*     */   }
/*     */   
/*     */   public void setUseQuadsForLines(boolean useQuadsForLines) {
/* 148 */     this.useQuadsForLines = useQuadsForLines;
/*     */   }
/*     */   
/*     */   public boolean isUseSWMouseCursors() {
/* 152 */     return this.useSWMouseCursors;
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
/*     */   public void setUseSWMouseCursors(boolean useSWMouseCursors) {
/* 164 */     this.useSWMouseCursors = useSWMouseCursors;
/*     */   }
/*     */   
/*     */   public CacheContext createNewCacheContext() {
/* 168 */     return new LWJGLCacheContext(this);
/*     */   }
/*     */   
/*     */   private LWJGLCacheContext activeCacheContext() {
/* 172 */     if (this.cacheContext == null) {
/* 173 */       setActiveCacheContext(createNewCacheContext());
/*     */     }
/* 175 */     return this.cacheContext;
/*     */   }
/*     */   
/*     */   public CacheContext getActiveCacheContext() {
/* 179 */     return activeCacheContext();
/*     */   }
/*     */   
/*     */   public void setActiveCacheContext(CacheContext cc) throws IllegalStateException {
/* 183 */     if (cc == null) {
/* 184 */       throw new NullPointerException();
/*     */     }
/* 186 */     if (!cc.isValid()) {
/* 187 */       throw new IllegalStateException("CacheContext is invalid");
/*     */     }
/* 189 */     if (!(cc instanceof LWJGLCacheContext)) {
/* 190 */       throw new IllegalArgumentException("CacheContext object not from this renderer");
/*     */     }
/* 192 */     LWJGLCacheContext lwjglCC = (LWJGLCacheContext)cc;
/* 193 */     if (lwjglCC.renderer != this) {
/* 194 */       throw new IllegalArgumentException("CacheContext object not from this renderer");
/*     */     }
/* 196 */     this.cacheContext = lwjglCC;
/*     */     try {
/* 198 */       for (TextureArea ta : this.textureAreas) {
/* 199 */         ta.destroyRepeatCache();
/*     */       }
/* 201 */       for (TextureAreaRotated tar : this.rotatedTextureAreas) {
/* 202 */         tar.destroyRepeatCache();
/*     */       }
/*     */     } finally {
/* 205 */       this.textureAreas.clear();
/* 206 */       this.rotatedTextureAreas.clear();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void syncViewportSize() {
/* 224 */     this.ib16.clear();
/* 225 */     GL11.glGetInteger(2978, this.ib16);
/* 226 */     this.viewportX = this.ib16.get(0);
/* 227 */     this.width = this.ib16.get(2);
/* 228 */     this.height = this.ib16.get(3);
/* 229 */     this.viewportBottom = this.ib16.get(1) + this.height;
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
/*     */   public void setViewport(int x, int y, int width, int height) {
/* 243 */     this.viewportX = x;
/* 244 */     this.viewportBottom = y + height;
/* 245 */     this.width = width;
/* 246 */     this.height = height;
/*     */   }
/*     */   
/*     */   public long getTimeMillis() {
/* 250 */     long res = Sys.getTimerResolution();
/* 251 */     long time = Sys.getTime();
/* 252 */     if (res != 1000L) {
/* 253 */       time = time * 1000L / res;
/*     */     }
/* 255 */     return time;
/*     */   }
/*     */   
/*     */   protected void setupGLState() {
/* 259 */     GL11.glPushAttrib(847876);
/*     */     
/* 261 */     GL11.glMatrixMode(5889);
/* 262 */     GL11.glPushMatrix();
/* 263 */     GL11.glLoadIdentity();
/* 264 */     GL11.glOrtho(0.0D, this.width, this.height, 0.0D, -1.0D, 1.0D);
/* 265 */     GL11.glMatrixMode(5888);
/* 266 */     GL11.glPushMatrix();
/* 267 */     GL11.glLoadIdentity();
/* 268 */     GL11.glEnable(3553);
/* 269 */     GL11.glEnable(3042);
/* 270 */     GL11.glEnable(2848);
/* 271 */     GL11.glDisable(2929);
/* 272 */     GL11.glDisable(2896);
/* 273 */     GL11.glDisable(3089);
/* 274 */     GL11.glBlendFunc(770, 771);
/* 275 */     GL11.glHint(3154, 4354);
/*     */   }
/*     */   
/*     */   protected void revertGLState() {
/* 279 */     GL11.glPopMatrix();
/* 280 */     GL11.glMatrixMode(5889);
/* 281 */     GL11.glPopMatrix();
/* 282 */     GL11.glPopAttrib();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean startRendering() {
/* 289 */     if (this.width <= 0 || this.height <= 0) {
/* 290 */       return false;
/*     */     }
/*     */     
/* 293 */     prepareForRendering();
/* 294 */     setupGLState();
/* 295 */     RenderScale.doscale();
/* 296 */     return true;
/*     */   }
/*     */   
/*     */   public void endRendering() {
/* 300 */     renderSWCursor();
/* 301 */     RenderScale.descale();
/* 302 */     revertGLState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pauseRendering() {
/* 311 */     RenderScale.descale();
/* 312 */     revertGLState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resumeRendering() {
/* 319 */     this.hasScissor = false;
/* 320 */     setupGLState();
/* 321 */     RenderScale.doscale();
/* 322 */     setClipRect();
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 326 */     return this.height;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 330 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getViewportX() {
/* 338 */     return this.viewportX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getViewportY() {
/* 346 */     return this.viewportBottom - this.height;
/*     */   }
/*     */   
/*     */   public Font loadFont(URL url, StateSelect select, FontParameter... parameterList) throws IOException {
/* 350 */     Util.checkGLError();
/* 351 */     if (url == null) {
/* 352 */       throw new NullPointerException("url");
/*     */     }
/* 354 */     if (select == null) {
/* 355 */       throw new NullPointerException("select");
/*     */     }
/* 357 */     if (parameterList == null) {
/* 358 */       throw new NullPointerException("parameterList");
/*     */     }
/*     */     
/* 361 */     if (select.getNumExpressions() + 1 != parameterList.length) {
/* 362 */       throw new IllegalArgumentException("select.getNumExpressions() + 1 != parameterList.length");
/*     */     }
/* 364 */     Util.checkGLError();
/* 365 */     BitmapFont bmFont = activeCacheContext().loadBitmapFont(url);
/* 366 */     Util.checkGLError();
/* 367 */     return new LWJGLFont(this, bmFont, select, parameterList);
/*     */   }
/*     */   
/*     */   public Texture loadTexture(URL url, String formatStr, String filterStr) throws IOException {
/* 371 */     LWJGLTexture.Format format = LWJGLTexture.Format.COLOR;
/* 372 */     LWJGLTexture.Filter filter = LWJGLTexture.Filter.NEAREST;
/* 373 */     if (formatStr != null) {
/*     */       try {
/* 375 */         format = LWJGLTexture.Format.valueOf(formatStr.toUpperCase(Locale.ENGLISH));
/* 376 */       } catch (IllegalArgumentException ex) {
/* 377 */         getLogger().log(Level.WARNING, "Unknown texture format: {0}", formatStr);
/*     */       } 
/*     */     }
/* 380 */     if (filterStr != null) {
/*     */       try {
/* 382 */         filter = LWJGLTexture.Filter.valueOf(filterStr.toUpperCase(Locale.ENGLISH));
/* 383 */       } catch (IllegalArgumentException ex) {
/* 384 */         getLogger().log(Level.WARNING, "Unknown texture filter: {0}", filterStr);
/*     */       } 
/*     */     }
/* 387 */     return load(url, format, filter);
/*     */   }
/*     */   
/*     */   public LineRenderer getLineRenderer() {
/* 391 */     return this;
/*     */   }
/*     */   
/*     */   public OffscreenRenderer getOffscreenRenderer() {
/* 395 */     return null;
/*     */   }
/*     */   
/*     */   public FontMapper getFontMapper() {
/* 399 */     return this.fontMapper;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFontMapper(FontMapper fontMapper) {
/* 410 */     this.fontMapper = fontMapper;
/*     */   }
/*     */   
/*     */   public DynamicImage createDynamicImage(int width, int height) {
/* 414 */     if (width <= 0) {
/* 415 */       throw new IllegalArgumentException("width");
/*     */     }
/* 417 */     if (height <= 0) {
/* 418 */       throw new IllegalArgumentException("height");
/*     */     }
/* 420 */     if (width > this.maxTextureSize || height > this.maxTextureSize) {
/* 421 */       getLogger().log(Level.WARNING, "requested size {0} x {1} exceeds maximum texture size {3}", new Object[] { Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(this.maxTextureSize) });
/*     */       
/* 423 */       return null;
/*     */     } 
/*     */     
/* 426 */     int texWidth = width;
/* 427 */     int texHeight = height;
/*     */     
/* 429 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 430 */     boolean useTextureRectangle = (caps.GL_EXT_texture_rectangle || caps.GL_ARB_texture_rectangle);
/*     */     
/* 432 */     if (!useTextureRectangle && !caps.GL_ARB_texture_non_power_of_two) {
/* 433 */       texWidth = nextPowerOf2(width);
/* 434 */       texHeight = nextPowerOf2(height);
/*     */     } 
/*     */ 
/*     */     
/* 438 */     int proxyTarget = useTextureRectangle ? 34039 : 32868;
/*     */ 
/*     */     
/* 441 */     GL11.glTexImage2D(proxyTarget, 0, 6408, texWidth, texHeight, 0, 6408, 5121, (ByteBuffer)null);
/* 442 */     this.ib16.clear();
/* 443 */     GL11.glGetTexLevelParameter(proxyTarget, 0, 4096, this.ib16);
/* 444 */     if (this.ib16.get(0) != texWidth) {
/* 445 */       getLogger().log(Level.WARNING, "requested size {0} x {1} failed proxy texture test", new Object[] { Integer.valueOf(texWidth), Integer.valueOf(texHeight) });
/*     */       
/* 447 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 451 */     int target = useTextureRectangle ? 34037 : 3553;
/*     */     
/* 453 */     int id = GL11.glGenTextures();
/*     */     
/* 455 */     GL11.glBindTexture(target, id);
/* 456 */     GL11.glTexImage2D(target, 0, 6408, texWidth, texHeight, 0, 6408, 5121, (ByteBuffer)null);
/* 457 */     GL11.glTexParameteri(target, 10240, 9728);
/* 458 */     GL11.glTexParameteri(target, 10241, 9728);
/*     */     
/* 460 */     LWJGLDynamicImage image = new LWJGLDynamicImage(this, target, id, width, height, texWidth, texHeight, Color.WHITE);
/* 461 */     this.dynamicImages.add(image);
/* 462 */     return image;
/*     */   }
/*     */   
/*     */   public Image createGradient(Gradient gradient) {
/* 466 */     return new GradientImage(this, gradient);
/*     */   }
/*     */   
/*     */   public void clipEnter(int x, int y, int w, int h) {
/* 470 */     this.clipStack.push(x, y, w, h);
/* 471 */     setClipRect();
/*     */   }
/*     */   
/*     */   public void clipEnter(Rect rect) {
/* 475 */     this.clipStack.push(rect);
/* 476 */     setClipRect();
/*     */   }
/*     */   
/*     */   public void clipLeave() {
/* 480 */     this.clipStack.pop();
/* 481 */     setClipRect();
/*     */   }
/*     */   
/*     */   public boolean clipIsEmpty() {
/* 485 */     return this.clipStack.isClipEmpty();
/*     */   }
/*     */   
/*     */   public void setCursor(MouseCursor cursor) {
/*     */     try {
/* 490 */       this.swCursor = null;
/* 491 */       if (isMouseInsideWindow()) {
/* 492 */         if (cursor instanceof LWJGLCursor) {
/* 493 */           setNativeCursor(((LWJGLCursor)cursor).cursor);
/* 494 */         } else if (cursor instanceof SWCursor) {
/* 495 */           setNativeCursor(this.emptyCursor);
/* 496 */           this.swCursor = (SWCursor)cursor;
/*     */         } else {
/* 498 */           setNativeCursor(null);
/*     */         } 
/*     */       }
/* 501 */     } catch (LWJGLException ex) {
/* 502 */       getLogger().log(Level.WARNING, "Could not set native cursor", (Throwable)ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setMousePosition(int mouseX, int mouseY) {
/* 507 */     this.mouseX = mouseX;
/* 508 */     this.mouseY = mouseY;
/*     */   }
/*     */   
/*     */   public void setMouseButton(int button, boolean state) {
/* 512 */     this.swCursorAnimState.setAnimationState(button, state);
/*     */   }
/*     */   
/*     */   public LWJGLTexture load(URL textureUrl, LWJGLTexture.Format fmt, LWJGLTexture.Filter filter) throws IOException {
/* 516 */     Util.checkGLError();
/* 517 */     return load(textureUrl, fmt, filter, null);
/*     */   }
/*     */   
/*     */   public LWJGLTexture load(URL textureUrl, LWJGLTexture.Format fmt, LWJGLTexture.Filter filter, TexturePostProcessing tpp) throws IOException {
/* 521 */     if (textureUrl == null) {
/* 522 */       throw new NullPointerException("textureUrl");
/*     */     }
/* 524 */     Util.checkGLError();
/* 525 */     LWJGLCacheContext cc = activeCacheContext();
/* 526 */     Util.checkGLError();
/* 527 */     if (tpp != null) {
/* 528 */       Util.checkGLError();
/* 529 */       return cc.createTexture(textureUrl, fmt, filter, tpp);
/*     */     } 
/* 531 */     Util.checkGLError();
/* 532 */     return cc.loadTexture(textureUrl, fmt, filter);
/*     */   }
/*     */ 
/*     */   
/*     */   public void pushGlobalTintColor(float r, float g, float b, float a) {
/* 537 */     this.tintStack = this.tintStack.push(r, g, b, a);
/*     */   }
/*     */   
/*     */   public void popGlobalTintColor() {
/* 541 */     this.tintStack = this.tintStack.pop();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pushGlobalTintColorReset() {
/* 550 */     this.tintStack = this.tintStack.pushReset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColor(Color color) {
/* 559 */     this.tintStack.setColor(color);
/*     */   }
/*     */   
/*     */   public void drawLine(float[] pts, int numPts, float width, Color color, boolean drawAsLoop) {
/* 563 */     if (numPts * 2 > pts.length) {
/* 564 */       throw new ArrayIndexOutOfBoundsException(numPts * 2);
/*     */     }
/* 566 */     if (numPts >= 2) {
/* 567 */       this.tintStack.setColor(color);
/* 568 */       GL11.glDisable(3553);
/* 569 */       if (this.useQuadsForLines) {
/* 570 */         drawLinesAsQuads(numPts, pts, width, drawAsLoop);
/*     */       } else {
/* 572 */         drawLinesAsLines(numPts, pts, width, drawAsLoop);
/*     */       } 
/* 574 */       GL11.glEnable(3553);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawLinesAsLines(int numPts, float[] pts, float width, boolean drawAsLoop) {
/* 579 */     GL11.glLineWidth(width);
/* 580 */     GL11.glBegin(drawAsLoop ? 2 : 3);
/* 581 */     for (int i = 0; i < numPts; i++) {
/* 582 */       GL11.glVertex2f(pts[i * 2 + 0], pts[i * 2 + 1]);
/*     */     }
/* 584 */     GL11.glEnd();
/*     */   }
/*     */   
/*     */   private void drawLinesAsQuads(int numPts, float[] pts, float width, boolean drawAsLoop) {
/* 588 */     width *= 0.5F;
/* 589 */     GL11.glBegin(7);
/* 590 */     for (int i = 1; i < numPts; i++) {
/* 591 */       drawLineAsQuad(pts[i * 2 - 2], pts[i * 2 - 1], pts[i * 2 + 0], pts[i * 2 + 1], width);
/*     */     }
/* 593 */     if (drawAsLoop) {
/* 594 */       int idx = numPts * 2;
/* 595 */       drawLineAsQuad(pts[idx], pts[idx + 1], pts[0], pts[1], width);
/*     */     } 
/* 597 */     GL11.glEnd();
/*     */   }
/*     */   
/*     */   private static void drawLineAsQuad(float x0, float y0, float x1, float y1, float w) {
/* 601 */     float dx = x1 - x0;
/* 602 */     float dy = y1 - y0;
/* 603 */     float l = (float)Math.sqrt((dx * dx + dy * dy)) / w;
/* 604 */     dx /= l;
/* 605 */     dy /= l;
/* 606 */     GL11.glVertex2f(x0 - dx + dy, y0 - dy - dx);
/* 607 */     GL11.glVertex2f(x0 - dx - dy, y0 - dy + dx);
/* 608 */     GL11.glVertex2f(x1 + dx - dy, y1 + dy + dx);
/* 609 */     GL11.glVertex2f(x1 + dx + dy, y1 + dy - dx);
/*     */   }
/*     */   
/*     */   protected void prepareForRendering() {
/* 613 */     this.hasScissor = false;
/* 614 */     this.tintStack = this.tintStateRoot;
/* 615 */     this.clipStack.clearStack();
/*     */   }
/*     */   
/*     */   protected void renderSWCursor() {
/* 619 */     if (this.swCursor != null) {
/* 620 */       this.tintStack = this.tintStateRoot;
/* 621 */       this.swCursor.render(this.mouseX, this.mouseY);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void setNativeCursor(Cursor cursor) throws LWJGLException {
/* 626 */     Mouse.setNativeCursor(cursor);
/*     */   }
/*     */   
/*     */   protected boolean isMouseInsideWindow() {
/* 630 */     return Mouse.isInsideWindow();
/*     */   }
/*     */   
/*     */   protected void getTintedColor(Color color, float[] result) {
/* 634 */     result[0] = this.tintStack.r * color.getRed();
/* 635 */     result[1] = this.tintStack.g * color.getGreen();
/* 636 */     result[2] = this.tintStack.b * color.getBlue();
/* 637 */     result[3] = this.tintStack.a * color.getAlpha();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getTintedColor(float[] color, float[] result) {
/* 646 */     result[0] = this.tintStack.r * color[0];
/* 647 */     result[1] = this.tintStack.g * color[1];
/* 648 */     result[2] = this.tintStack.b * color[2];
/* 649 */     result[3] = this.tintStack.a * color[3];
/*     */   }
/*     */   
/*     */   public void setClipRect() {
/* 653 */     Rect rect = this.clipRectTemp;
/* 654 */     if (this.clipStack.getClipRect(rect)) {
/* 655 */       GL11.glScissor(this.viewportX + rect.getX() * RenderScale.scale, this.viewportBottom - rect.getBottom() * RenderScale.scale, rect.getWidth() * RenderScale.scale, rect.getHeight() * RenderScale.scale);
/* 656 */       if (!this.hasScissor) {
/* 657 */         GL11.glEnable(3089);
/* 658 */         this.hasScissor = true;
/*     */       } 
/* 660 */     } else if (this.hasScissor) {
/* 661 */       GL11.glDisable(3089);
/* 662 */       this.hasScissor = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getClipRect(Rect rect) {
/* 672 */     return this.clipStack.getClipRect(rect);
/*     */   }
/*     */   
/*     */   Logger getLogger() {
/* 676 */     return Logger.getLogger(LWJGLRenderer.class.getName());
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
/*     */   private static int nextPowerOf2(int i) {
/* 689 */     i--;
/* 690 */     i |= i >> 1;
/* 691 */     i |= i >> 2;
/* 692 */     i |= i >> 4;
/* 693 */     i |= i >> 8;
/* 694 */     i |= i >> 16;
/* 695 */     return i + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class SWCursorAnimState
/*     */     implements AnimationState
/*     */   {
/* 703 */     private final long[] lastTime = new long[3];
/* 704 */     private final boolean[] active = new boolean[3];
/*     */ 
/*     */     
/*     */     void setAnimationState(int idx, boolean isActive) {
/* 708 */       if (idx >= 0 && idx < 3 && this.active[idx] != isActive) {
/* 709 */         this.lastTime[idx] = Sys.getTime();
/* 710 */         this.active[idx] = isActive;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int getAnimationTime(AnimationState.StateKey state) {
/* 715 */       long curTime = Sys.getTime();
/* 716 */       int idx = getMouseButton(state);
/* 717 */       if (idx >= 0) {
/* 718 */         curTime -= this.lastTime[idx];
/*     */       }
/* 720 */       return (int)curTime & Integer.MAX_VALUE;
/*     */     }
/*     */     
/*     */     public boolean getAnimationState(AnimationState.StateKey state) {
/* 724 */       int idx = getMouseButton(state);
/* 725 */       if (idx >= 0) {
/* 726 */         return this.active[idx];
/*     */       }
/* 728 */       return false;
/*     */     }
/*     */     
/*     */     public boolean getShouldAnimateState(AnimationState.StateKey state) {
/* 732 */       return true;
/*     */     }
/*     */     
/*     */     private int getMouseButton(AnimationState.StateKey key) {
/* 736 */       if (key == LWJGLRenderer.STATE_LEFT_MOUSE_BUTTON) {
/* 737 */         return 0;
/*     */       }
/* 739 */       if (key == LWJGLRenderer.STATE_MIDDLE_MOUSE_BUTTON) {
/* 740 */         return 2;
/*     */       }
/* 742 */       if (key == LWJGLRenderer.STATE_RIGHT_MOUSE_BUTTON) {
/* 743 */         return 1;
/*     */       }
/* 745 */       return -1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\LWJGLRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */