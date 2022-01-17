/*     */ package de.matthiasmann.twl.renderer.lwjgl;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import de.matthiasmann.twl.renderer.Texture;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TextureAreaRotated
/*     */   implements Image
/*     */ {
/*     */   protected static final int REPEAT_CACHE_SIZE = 10;
/*     */   private final LWJGLTexture texture;
/*     */   private final Color tintColor;
/*     */   private final float txTL;
/*     */   private final float tyTL;
/*     */   private final float txTR;
/*     */   private final float tyTR;
/*     */   private final float txBL;
/*     */   private final float tyBL;
/*     */   private final float txBR;
/*     */   private final float tyBR;
/*     */   private final char width;
/*     */   private final char height;
/*     */   private final boolean tiled;
/*  60 */   protected int repeatCacheID = -1;
/*     */ 
/*     */ 
/*     */   
/*     */   public TextureAreaRotated(LWJGLTexture texture, int x, int y, int width, int height, Color tintColor, boolean tiled, Texture.Rotation rotation) {
/*  65 */     if (rotation == Texture.Rotation.CLOCKWISE_90 || rotation == Texture.Rotation.CLOCKWISE_270) {
/*  66 */       this.width = (char)Math.abs(height);
/*  67 */       this.height = (char)Math.abs(width);
/*     */     } else {
/*  69 */       this.width = (char)Math.abs(width);
/*  70 */       this.height = (char)Math.abs(height);
/*     */     } 
/*     */     
/*  73 */     float fx = x;
/*  74 */     float fy = y;
/*  75 */     if (width == 1) {
/*  76 */       fx += 0.5F;
/*  77 */       width = 0;
/*  78 */     } else if (width < -1) {
/*  79 */       fx -= (width + 1);
/*     */     } 
/*  81 */     if (height == 1) {
/*  82 */       fy += 0.5F;
/*  83 */       height = 0;
/*  84 */     } else if (height < -1) {
/*  85 */       fy -= (height + 1);
/*     */     } 
/*     */     
/*  88 */     float texWidth = texture.getTexWidth();
/*  89 */     float texHeight = texture.getTexHeight();
/*     */     
/*  91 */     float tx0 = fx / texWidth;
/*  92 */     float ty0 = fy / texHeight;
/*  93 */     float tx1 = tx0 + width / texWidth;
/*  94 */     float ty1 = ty0 + height / texHeight;
/*     */     
/*  96 */     switch (rotation) {
/*     */       default:
/*  98 */         this.txTL = this.txBL = tx0;
/*  99 */         this.txTR = this.txBR = tx1;
/* 100 */         this.tyTL = this.tyTR = ty0;
/* 101 */         this.tyBL = this.tyBR = ty1;
/*     */         break;
/*     */       case CLOCKWISE_90:
/* 104 */         this.txTL = tx0; this.tyTL = ty1;
/* 105 */         this.txTR = tx0; this.tyTR = ty0;
/* 106 */         this.txBL = tx1; this.tyBL = ty1;
/* 107 */         this.txBR = tx1; this.tyBR = ty0;
/*     */         break;
/*     */       case CLOCKWISE_180:
/* 110 */         this.txTL = tx1; this.tyTL = ty1;
/* 111 */         this.txTR = tx0; this.tyTR = ty1;
/* 112 */         this.txBL = tx1; this.tyBL = ty0;
/* 113 */         this.txBR = tx0; this.tyBR = ty0;
/*     */         break;
/*     */       case CLOCKWISE_270:
/* 116 */         this.txTL = tx1; this.tyTL = ty0;
/* 117 */         this.txTR = tx1; this.tyTR = ty1;
/* 118 */         this.txBL = tx0; this.tyBL = ty0;
/* 119 */         this.txBR = tx0; this.tyBR = ty1;
/*     */         break;
/*     */     } 
/* 122 */     this.texture = texture;
/* 123 */     this.tintColor = (tintColor == null) ? Color.WHITE : tintColor;
/* 124 */     this.tiled = tiled;
/*     */   }
/*     */   
/*     */   TextureAreaRotated(TextureAreaRotated src, Color tintColor) {
/* 128 */     this.txTL = src.txTL;
/* 129 */     this.tyTL = src.tyTL;
/* 130 */     this.txTR = src.txTR;
/* 131 */     this.tyTR = src.tyTR;
/* 132 */     this.txBL = src.txBL;
/* 133 */     this.tyBL = src.tyBL;
/* 134 */     this.txBR = src.txBR;
/* 135 */     this.tyBR = src.tyBR;
/* 136 */     this.width = src.width;
/* 137 */     this.height = src.height;
/* 138 */     this.texture = src.texture;
/* 139 */     this.tiled = src.tiled;
/* 140 */     this.tintColor = tintColor;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 144 */     return this.height;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 148 */     return this.width;
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y) {
/* 152 */     draw(as, x, y, this.width, this.height);
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y, int w, int h) {
/* 156 */     if (this.texture.bind(this.tintColor)) {
/* 157 */       if (this.tiled) {
/* 158 */         drawTiled(x, y, w, h);
/*     */       } else {
/* 160 */         GL11.glBegin(7);
/* 161 */         drawQuad(x, y, w, h);
/* 162 */         GL11.glEnd();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawRepeat(int x, int y, int repeatCountX, int repeatCountY) {
/* 168 */     GL11.glBegin(7);
/* 169 */     int w = this.width;
/* 170 */     int h = this.height;
/* 171 */     while (repeatCountY-- > 0) {
/* 172 */       int curX = x;
/* 173 */       int cntX = repeatCountX;
/* 174 */       while (cntX-- > 0) {
/* 175 */         drawQuad(curX, y, w, h);
/* 176 */         curX += w;
/*     */       } 
/* 178 */       y += h;
/*     */     } 
/* 180 */     GL11.glEnd();
/*     */   }
/*     */   
/*     */   private void drawTiled(int x, int y, int w, int h) {
/* 184 */     int repeatCountX = w / this.width;
/* 185 */     int repeatCountY = h / this.height;
/*     */     
/* 187 */     if (repeatCountX < 10 || repeatCountY < 10) {
/* 188 */       drawRepeat(x, y, repeatCountX, repeatCountY);
/*     */     } else {
/* 190 */       drawRepeatCached(x, y, repeatCountX, repeatCountY);
/*     */     } 
/*     */     
/* 193 */     int drawnX = repeatCountX * this.width;
/* 194 */     int drawnY = repeatCountY * this.height;
/* 195 */     int restWidth = w - drawnX;
/* 196 */     int restHeight = h - drawnY;
/* 197 */     if (restWidth > 0 || restHeight > 0) {
/* 198 */       GL11.glBegin(7);
/* 199 */       if (restWidth > 0 && repeatCountY > 0) {
/* 200 */         drawClipped(x + drawnX, y, restWidth, this.height, 1, repeatCountY);
/*     */       }
/* 202 */       if (restHeight > 0) {
/* 203 */         if (repeatCountX > 0) {
/* 204 */           drawClipped(x, y + drawnY, this.width, restHeight, repeatCountX, 1);
/*     */         }
/* 206 */         if (restWidth > 0) {
/* 207 */           drawClipped(x + drawnX, y + drawnY, restWidth, restHeight, 1, 1);
/*     */         }
/*     */       } 
/* 210 */       GL11.glEnd();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawRepeatCached(int x, int y, int repeatCountX, int repeatCountY) {
/* 216 */     if (this.repeatCacheID < 0) {
/* 217 */       createRepeatCache();
/*     */     }
/*     */     
/* 220 */     int cacheBlocksX = repeatCountX / 10;
/* 221 */     int repeatsByCacheX = cacheBlocksX * 10;
/*     */     
/* 223 */     if (repeatCountX > repeatsByCacheX) {
/* 224 */       drawRepeat(x + this.width * repeatsByCacheX, y, repeatCountX - repeatsByCacheX, repeatCountY);
/*     */     }
/*     */ 
/*     */     
/*     */     do {
/* 229 */       GL11.glPushMatrix();
/* 230 */       GL11.glTranslatef(x, y, 0.0F);
/* 231 */       GL11.glCallList(this.repeatCacheID);
/*     */       
/* 233 */       for (int i = 1; i < cacheBlocksX; i++) {
/* 234 */         GL11.glTranslatef((this.width * 10), 0.0F, 0.0F);
/* 235 */         GL11.glCallList(this.repeatCacheID);
/*     */       } 
/*     */       
/* 238 */       GL11.glPopMatrix();
/* 239 */       repeatCountY -= 10;
/* 240 */       y += this.height * 10;
/* 241 */     } while (repeatCountY >= 10);
/*     */     
/* 243 */     if (repeatCountY > 0) {
/* 244 */       drawRepeat(x, y, repeatsByCacheX, repeatCountY);
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawClipped(int x, int y, int width, int height, int repeatCountX, int repeatCountY) {
/* 249 */     float ctxTL = this.txTL;
/* 250 */     float ctyTL = this.tyTL;
/* 251 */     float ctxTR = this.txTR;
/* 252 */     float ctyTR = this.tyTR;
/* 253 */     float ctxBL = this.txBL;
/* 254 */     float ctyBL = this.tyBL;
/* 255 */     float ctxBR = this.txBR;
/* 256 */     float ctyBR = this.tyBR;
/*     */     
/* 258 */     if (this.width > '\001') {
/* 259 */       float f = width / this.width;
/* 260 */       ctxTR = ctxTL + (ctxTR - ctxTL) * f;
/* 261 */       ctyTR = ctyTL + (ctyTR - ctyTL) * f;
/* 262 */       ctxBR = ctxBL + (ctxBR - ctxBL) * f;
/* 263 */       ctyBR = ctyBL + (ctyBR - ctyBL) * f;
/*     */     } 
/* 265 */     if (this.height > '\001') {
/* 266 */       float f = height / this.height;
/* 267 */       ctxBL = ctxTL + (ctxBL - ctxTL) * f;
/* 268 */       ctyBL = ctyTL + (ctyBL - ctyTL) * f;
/* 269 */       ctxBR = ctxTR + (ctxBR - ctxTR) * f;
/* 270 */       ctyBR = ctyTR + (ctyBR - ctyTR) * f;
/*     */     } 
/*     */     
/* 273 */     while (repeatCountY-- > 0) {
/* 274 */       int y1 = y + height;
/* 275 */       int x0 = x;
/* 276 */       for (int cx = repeatCountX; cx-- > 0; ) {
/* 277 */         int x1 = x0 + width;
/* 278 */         GL11.glTexCoord2f(ctxTL, ctyTL); GL11.glVertex2i(x0, y);
/* 279 */         GL11.glTexCoord2f(ctxBL, ctyBL); GL11.glVertex2i(x0, y1);
/* 280 */         GL11.glTexCoord2f(ctxBR, ctyBR); GL11.glVertex2i(x1, y1);
/* 281 */         GL11.glTexCoord2f(ctxTR, ctyTR); GL11.glVertex2i(x1, y);
/* 282 */         x0 = x1;
/*     */       } 
/* 284 */       y = y1;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawQuad(int x, int y, int w, int h) {
/* 289 */     GL11.glTexCoord2f(this.txTL, this.tyTL); GL11.glVertex2i(x, y);
/* 290 */     GL11.glTexCoord2f(this.txBL, this.tyBL); GL11.glVertex2i(x, y + h);
/* 291 */     GL11.glTexCoord2f(this.txBR, this.tyBR); GL11.glVertex2i(x + w, y + h);
/* 292 */     GL11.glTexCoord2f(this.txTR, this.tyTR); GL11.glVertex2i(x + w, y);
/*     */   }
/*     */   
/*     */   private void createRepeatCache() {
/* 296 */     this.repeatCacheID = GL11.glGenLists(1);
/* 297 */     this.texture.renderer.rotatedTextureAreas.add(this);
/*     */     
/* 299 */     GL11.glNewList(this.repeatCacheID, 4864);
/* 300 */     drawRepeat(0, 0, 10, 10);
/* 301 */     GL11.glEndList();
/*     */   }
/*     */   
/*     */   void destroyRepeatCache() {
/* 305 */     GL11.glDeleteLists(this.repeatCacheID, 1);
/* 306 */     this.repeatCacheID = -1;
/*     */   }
/*     */   
/*     */   public Image createTintedVersion(Color color) {
/* 310 */     if (color == null) {
/* 311 */       throw new NullPointerException("color");
/*     */     }
/* 313 */     Color newTintColor = this.tintColor.multiply(color);
/* 314 */     if (newTintColor.equals(this.tintColor)) {
/* 315 */       return this;
/*     */     }
/* 317 */     return new TextureAreaRotated(this, newTintColor);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\TextureAreaRotated.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */