/*     */ package de.matthiasmann.twl.renderer.lwjgl;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.Image;
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
/*     */ public class TextureAreaTiled
/*     */   extends TextureArea
/*     */ {
/*     */   public TextureAreaTiled(LWJGLTexture texture, int x, int y, int width, int height, Color tintColor) {
/*  45 */     super(texture, x, y, width, height, tintColor);
/*     */   }
/*     */   
/*     */   TextureAreaTiled(TextureAreaTiled src, Color tintColor) {
/*  49 */     super(src, tintColor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void draw(AnimationState as, int x, int y, int w, int h) {
/*  54 */     if (this.texture.bind(this.tintColor)) {
/*  55 */       int repeatCountX = w / this.width;
/*  56 */       int repeatCountY = h / this.height;
/*     */       
/*  58 */       if (repeatCountX < 10 || repeatCountY < 10) {
/*  59 */         drawRepeat(x, y, repeatCountX, repeatCountY);
/*     */       } else {
/*  61 */         drawRepeatCached(x, y, repeatCountX, repeatCountY);
/*     */       } 
/*     */       
/*  64 */       int drawnX = repeatCountX * this.width;
/*  65 */       int drawnY = repeatCountY * this.height;
/*  66 */       int restWidth = w - drawnX;
/*  67 */       int restHeight = h - drawnY;
/*  68 */       if (restWidth > 0 || restHeight > 0) {
/*  69 */         GL11.glBegin(7);
/*  70 */         if (restWidth > 0 && repeatCountY > 0) {
/*  71 */           drawClipped(x + drawnX, y, restWidth, this.height, 1, repeatCountY);
/*     */         }
/*  73 */         if (restHeight > 0) {
/*  74 */           if (repeatCountX > 0) {
/*  75 */             drawClipped(x, y + drawnY, this.width, restHeight, repeatCountX, 1);
/*     */           }
/*  77 */           if (restWidth > 0) {
/*  78 */             drawClipped(x + drawnX, y + drawnY, restWidth, restHeight, 1, 1);
/*     */           }
/*     */         } 
/*  81 */         GL11.glEnd();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawClipped(int x, int y, int width, int height, int repeatCountX, int repeatCountY) {
/*  87 */     float ctx0 = this.tx0;
/*  88 */     float cty0 = this.ty0;
/*  89 */     float ctx1 = this.tx1;
/*  90 */     float cty1 = this.ty1;
/*  91 */     if (this.width > 1) {
/*  92 */       ctx1 = ctx0 + width / this.texture.getTexWidth();
/*     */     }
/*  94 */     if (this.height > 1) {
/*  95 */       cty1 = cty0 + height / this.texture.getTexHeight();
/*     */     }
/*     */     
/*  98 */     while (repeatCountY-- > 0) {
/*  99 */       int y1 = y + height;
/* 100 */       int x0 = x;
/* 101 */       for (int cx = repeatCountX; cx-- > 0; ) {
/* 102 */         int x1 = x0 + width;
/* 103 */         GL11.glTexCoord2f(ctx0, cty0); GL11.glVertex2i(x0, y);
/* 104 */         GL11.glTexCoord2f(ctx0, cty1); GL11.glVertex2i(x0, y1);
/* 105 */         GL11.glTexCoord2f(ctx1, cty1); GL11.glVertex2i(x1, y1);
/* 106 */         GL11.glTexCoord2f(ctx1, cty0); GL11.glVertex2i(x1, y);
/* 107 */         x0 = x1;
/*     */       } 
/* 109 */       y = y1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Image createTintedVersion(Color color) {
/* 115 */     if (color == null) {
/* 116 */       throw new NullPointerException("color");
/*     */     }
/* 118 */     Color newTintColor = this.tintColor.multiply(color);
/* 119 */     if (newTintColor.equals(this.tintColor)) {
/* 120 */       return this;
/*     */     }
/* 122 */     return new TextureAreaTiled(this, newTintColor);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\TextureAreaTiled.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */