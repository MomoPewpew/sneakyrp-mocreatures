/*     */ package de.matthiasmann.twl.renderer.lwjgl;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import de.matthiasmann.twl.renderer.SupportsDrawRepeat;
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
/*     */ public class TextureArea
/*     */   extends TextureAreaBase
/*     */   implements Image, SupportsDrawRepeat
/*     */ {
/*     */   protected static final int REPEAT_CACHE_SIZE = 10;
/*     */   protected final LWJGLTexture texture;
/*     */   protected final Color tintColor;
/*  49 */   protected int repeatCacheID = -1;
/*     */   
/*     */   public TextureArea(LWJGLTexture texture, int x, int y, int width, int height, Color tintColor) {
/*  52 */     super(x, y, width, height, texture.getTexWidth(), texture.getTexHeight());
/*  53 */     this.texture = texture;
/*  54 */     this.tintColor = (tintColor == null) ? Color.WHITE : tintColor;
/*     */   }
/*     */   
/*     */   TextureArea(TextureArea src, Color tintColor) {
/*  58 */     super(src);
/*  59 */     this.texture = src.texture;
/*  60 */     this.tintColor = tintColor;
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y) {
/*  64 */     draw(as, x, y, this.width, this.height);
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y, int w, int h) {
/*  68 */     if (this.texture.bind(this.tintColor)) {
/*  69 */       GL11.glBegin(7);
/*  70 */       drawQuad(x, y, w, h);
/*  71 */       GL11.glEnd();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y, int width, int height, int repeatCountX, int repeatCountY) {
/*  76 */     if (this.texture.bind(this.tintColor)) {
/*  77 */       if (repeatCountX * this.width != width || repeatCountY * this.height != height) {
/*  78 */         drawRepeatSlow(x, y, width, height, repeatCountX, repeatCountY);
/*     */         return;
/*     */       } 
/*  81 */       if (repeatCountX < 10 || repeatCountY < 10) {
/*  82 */         drawRepeat(x, y, repeatCountX, repeatCountY);
/*     */         return;
/*     */       } 
/*  85 */       drawRepeatCached(x, y, repeatCountX, repeatCountY);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawRepeatSlow(int x, int y, int width, int height, int repeatCountX, int repeatCountY) {
/*  90 */     GL11.glBegin(7);
/*  91 */     while (repeatCountY > 0) {
/*  92 */       int rowHeight = height / repeatCountY;
/*     */       
/*  94 */       int cx = 0;
/*  95 */       for (int xi = 0; xi < repeatCountX; ) {
/*  96 */         int nx = ++xi * width / repeatCountX;
/*  97 */         drawQuad(x + cx, y, nx - cx, rowHeight);
/*  98 */         cx = nx;
/*     */       } 
/*     */       
/* 101 */       y += rowHeight;
/* 102 */       height -= rowHeight;
/* 103 */       repeatCountY--;
/*     */     } 
/* 105 */     GL11.glEnd();
/*     */   }
/*     */   
/*     */   protected void drawRepeat(int x, int y, int repeatCountX, int repeatCountY) {
/* 109 */     int w = this.width;
/* 110 */     int h = this.height;
/* 111 */     GL11.glBegin(7);
/* 112 */     while (repeatCountY-- > 0) {
/* 113 */       int curX = x;
/* 114 */       int cntX = repeatCountX;
/* 115 */       while (cntX-- > 0) {
/* 116 */         drawQuad(curX, y, w, h);
/* 117 */         curX += w;
/*     */       } 
/* 119 */       y += h;
/*     */     } 
/* 121 */     GL11.glEnd();
/*     */   }
/*     */   
/*     */   protected void drawRepeatCached(int x, int y, int repeatCountX, int repeatCountY) {
/* 125 */     if (this.repeatCacheID < 0) {
/* 126 */       createRepeatCache();
/*     */     }
/*     */     
/* 129 */     int cacheBlocksX = repeatCountX / 10;
/* 130 */     int repeatsByCacheX = cacheBlocksX * 10;
/*     */     
/* 132 */     if (repeatCountX > repeatsByCacheX) {
/* 133 */       drawRepeat(x + this.width * repeatsByCacheX, y, repeatCountX - repeatsByCacheX, repeatCountY);
/*     */     }
/*     */ 
/*     */     
/*     */     do {
/* 138 */       GL11.glPushMatrix();
/* 139 */       GL11.glTranslatef(x, y, 0.0F);
/* 140 */       GL11.glCallList(this.repeatCacheID);
/*     */       
/* 142 */       for (int i = 1; i < cacheBlocksX; i++) {
/* 143 */         GL11.glTranslatef((this.width * 10), 0.0F, 0.0F);
/* 144 */         GL11.glCallList(this.repeatCacheID);
/*     */       } 
/*     */       
/* 147 */       GL11.glPopMatrix();
/* 148 */       repeatCountY -= 10;
/* 149 */       y += this.height * 10;
/* 150 */     } while (repeatCountY >= 10);
/*     */     
/* 152 */     if (repeatCountY > 0) {
/* 153 */       drawRepeat(x, y, repeatsByCacheX, repeatCountY);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void createRepeatCache() {
/* 158 */     this.repeatCacheID = GL11.glGenLists(1);
/* 159 */     this.texture.renderer.textureAreas.add(this);
/*     */     
/* 161 */     GL11.glNewList(this.repeatCacheID, 4864);
/* 162 */     drawRepeat(0, 0, 10, 10);
/* 163 */     GL11.glEndList();
/*     */   }
/*     */   
/*     */   void destroyRepeatCache() {
/* 167 */     GL11.glDeleteLists(this.repeatCacheID, 1);
/* 168 */     this.repeatCacheID = -1;
/*     */   }
/*     */   
/*     */   public Image createTintedVersion(Color color) {
/* 172 */     if (color == null) {
/* 173 */       throw new NullPointerException("color");
/*     */     }
/* 175 */     Color newTintColor = this.tintColor.multiply(color);
/* 176 */     if (newTintColor.equals(this.tintColor)) {
/* 177 */       return this;
/*     */     }
/* 179 */     return new TextureArea(this, newTintColor);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\TextureArea.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */