/*     */ package de.matthiasmann.twl.renderer.lwjgl;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.DynamicImage;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import java.nio.ByteBuffer;
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
/*     */ public class LWJGLDynamicImage
/*     */   extends TextureAreaBase
/*     */   implements DynamicImage
/*     */ {
/*     */   private final LWJGLRenderer renderer;
/*     */   private final int target;
/*     */   private final Color tintColor;
/*     */   private int id;
/*     */   
/*     */   public LWJGLDynamicImage(LWJGLRenderer renderer, int target, int id, int width, int height, int texWidth, int texHeight, Color tintColor) {
/*  53 */     super(0, 0, width, height, (target == 3553) ? texWidth : 1.0F, (target == 3553) ? texHeight : 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  57 */     this.renderer = renderer;
/*  58 */     this.tintColor = tintColor;
/*  59 */     this.target = target;
/*  60 */     this.id = id;
/*     */   }
/*     */   
/*     */   LWJGLDynamicImage(LWJGLDynamicImage src, Color tintColor) {
/*  64 */     super(src);
/*  65 */     this.renderer = src.renderer;
/*  66 */     this.tintColor = tintColor;
/*  67 */     this.target = src.target;
/*  68 */     this.id = src.id;
/*     */   }
/*     */   
/*     */   public void destroy() {
/*  72 */     if (this.id != 0) {
/*  73 */       GL11.glDeleteTextures(this.id);
/*  74 */       this.renderer.dynamicImages.remove(this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update(ByteBuffer data, DynamicImage.Format format) {
/*  79 */     update(0, 0, this.width, this.height, data, this.width * 4, format);
/*     */   }
/*     */   
/*     */   public void update(ByteBuffer data, int stride, DynamicImage.Format format) {
/*  83 */     update(0, 0, this.width, this.height, data, stride, format);
/*     */   }
/*     */   
/*     */   public void update(int xoffset, int yoffset, int width, int height, ByteBuffer data, DynamicImage.Format format) {
/*  87 */     update(xoffset, yoffset, width, height, data, width * 4, format);
/*     */   }
/*     */   
/*     */   public void update(int xoffset, int yoffset, int width, int height, ByteBuffer data, int stride, DynamicImage.Format format) {
/*  91 */     if (xoffset < 0 || yoffset < 0 || getWidth() <= 0 || getHeight() <= 0) {
/*  92 */       throw new IllegalArgumentException("Negative offsets or size <= 0");
/*     */     }
/*  94 */     if (xoffset >= getWidth() || yoffset >= getHeight()) {
/*  95 */       throw new IllegalArgumentException("Offset outside of texture");
/*     */     }
/*  97 */     if (width > getWidth() - xoffset || height > getHeight() - yoffset) {
/*  98 */       throw new IllegalArgumentException("Rectangle outside of texture");
/*     */     }
/* 100 */     if (data == null) {
/* 101 */       throw new NullPointerException("data");
/*     */     }
/* 103 */     if (format == null) {
/* 104 */       throw new NullPointerException("format");
/*     */     }
/* 106 */     if (stride < 0 || (stride & 0x3) != 0) {
/* 107 */       throw new IllegalArgumentException("stride");
/*     */     }
/* 109 */     if (stride < width * 4) {
/* 110 */       throw new IllegalArgumentException("stride too short for width");
/*     */     }
/* 112 */     if (data.remaining() < stride * (height - 1) + width * 4) {
/* 113 */       throw new IllegalArgumentException("Not enough data remaining in the buffer");
/*     */     }
/* 115 */     int glFormat = (format == DynamicImage.Format.RGBA) ? 6408 : 32993;
/* 116 */     bind();
/* 117 */     GL11.glPixelStorei(3314, stride / 4);
/* 118 */     GL11.glTexSubImage2D(this.target, 0, xoffset, yoffset, width, height, glFormat, 5121, data);
/* 119 */     GL11.glPixelStorei(3314, 0);
/*     */   }
/*     */   
/*     */   public Image createTintedVersion(Color color) {
/* 123 */     if (color == null) {
/* 124 */       throw new NullPointerException("color");
/*     */     }
/* 126 */     Color newTintColor = this.tintColor.multiply(color);
/* 127 */     if (newTintColor.equals(this.tintColor)) {
/* 128 */       return (Image)this;
/*     */     }
/* 130 */     return (Image)new LWJGLDynamicImage(this, newTintColor);
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y) {
/* 134 */     draw(as, x, y, this.width, this.height);
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y, int width, int height) {
/* 138 */     bind();
/* 139 */     this.renderer.tintStack.setColor(this.tintColor);
/* 140 */     if (this.target != 3553) {
/* 141 */       GL11.glDisable(3553);
/* 142 */       GL11.glEnable(this.target);
/*     */     } 
/* 144 */     GL11.glBegin(7);
/* 145 */     drawQuad(x, y, width, height);
/* 146 */     GL11.glEnd();
/* 147 */     if (this.target != 3553) {
/* 148 */       GL11.glDisable(this.target);
/* 149 */       GL11.glEnable(3553);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void bind() {
/* 154 */     if (this.id == 0) {
/* 155 */       throw new IllegalStateException("destroyed");
/*     */     }
/* 157 */     GL11.glBindTexture(this.target, this.id);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\LWJGLDynamicImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */