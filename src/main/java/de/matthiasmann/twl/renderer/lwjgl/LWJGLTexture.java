/*     */ package de.matthiasmann.twl.renderer.lwjgl;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import de.matthiasmann.twl.renderer.MouseCursor;
/*     */ import de.matthiasmann.twl.renderer.Resource;
/*     */ import de.matthiasmann.twl.renderer.Texture;
/*     */ import de.matthiasmann.twl.utils.PNGDecoder;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.ArrayList;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GLContext;
/*     */ import org.lwjgl.opengl.OpenGLException;
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
/*     */ public class LWJGLTexture
/*     */   implements Texture, Resource
/*     */ {
/*     */   final LWJGLRenderer renderer;
/*     */   private int id;
/*     */   private final int width;
/*     */   private final int height;
/*     */   private final int texWidth;
/*     */   private final int texHeight;
/*     */   private ByteBuffer texData;
/*     */   private Format texDataFmt;
/*     */   private ArrayList<LWJGLCursor> cursors;
/*     */   
/*     */   public enum Format
/*     */   {
/*  55 */     ALPHA(6406, 32828, PNGDecoder.Format.ALPHA),
/*  56 */     LUMINANCE(6409, 32832, PNGDecoder.Format.LUMINANCE),
/*  57 */     LUMINANCE_ALPHA(6410, 32837, PNGDecoder.Format.LUMINANCE_ALPHA),
/*  58 */     RGB(6407, 32849, PNGDecoder.Format.RGB),
/*  59 */     RGB_SMALL(6407, 32855, PNGDecoder.Format.RGB),
/*  60 */     RGBA(6408, 32856, PNGDecoder.Format.RGBA),
/*  61 */     BGRA(32993, 32856, PNGDecoder.Format.BGRA),
/*  62 */     ABGR(32768, 32856, PNGDecoder.Format.ABGR),
/*  63 */     COLOR(-1, -1, null);
/*     */     
/*     */     final int glFormat;
/*     */     final int glInternalFormat;
/*     */     final PNGDecoder.Format pngFormat;
/*     */     
/*     */     Format(int fmt, int ifmt, PNGDecoder.Format pf) {
/*  70 */       this.glFormat = fmt;
/*  71 */       this.glInternalFormat = ifmt;
/*  72 */       this.pngFormat = pf;
/*     */     }
/*     */     
/*     */     public int getPixelSize() {
/*  76 */       return this.pngFormat.getNumComponents();
/*     */     }
/*     */     
/*     */     public PNGDecoder.Format getPngFormat() {
/*  80 */       return this.pngFormat;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum Filter {
/*  85 */     NEAREST(9728),
/*  86 */     LINEAR(9729);
/*     */     final int glValue;
/*     */     
/*     */     Filter(int value) {
/*  90 */       this.glValue = value;
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
/*     */   public LWJGLTexture(LWJGLRenderer renderer, int width, int height, ByteBuffer buf, Format fmt, Filter filter) {
/* 106 */     this.renderer = renderer;
/*     */     
/* 108 */     if (width <= 0 || height <= 0) {
/* 109 */       throw new IllegalArgumentException("size <= 0");
/*     */     }
/* 111 */     Util.checkGLError();
/* 112 */     this.id = GL11.glGenTextures();
/* 113 */     Util.checkGLError();
/* 114 */     if (this.id == 0) {
/* 115 */       throw new OpenGLException("failed to allocate texture ID");
/*     */     }
/* 117 */     Util.checkGLError();
/* 118 */     GL11.glBindTexture(3553, this.id);
/* 119 */     Util.checkGLError();
/* 120 */     GL11.glPixelStorei(3314, 0);
/* 121 */     Util.checkGLError();
/* 122 */     GL11.glPixelStorei(3317, 1);
/* 123 */     Util.checkGLError();
/* 124 */     if ((GLContext.getCapabilities()).OpenGL12) {
/* 125 */       Util.checkGLError();
/* 126 */       GL11.glTexParameteri(3553, 10242, 33071);
/* 127 */       Util.checkGLError();
/* 128 */       GL11.glTexParameteri(3553, 10243, 33071);
/*     */     } else {
/* 130 */       Util.checkGLError();
/* 131 */       GL11.glTexParameteri(3553, 10242, 10496);
/* 132 */       Util.checkGLError();
/* 133 */       GL11.glTexParameteri(3553, 10243, 10496);
/*     */     } 
/* 135 */     Util.checkGLError();
/* 136 */     GL11.glTexParameteri(3553, 10240, filter.glValue);
/* 137 */     Util.checkGLError();
/* 138 */     GL11.glTexParameteri(3553, 10241, filter.glValue);
/* 139 */     Util.checkGLError();
/* 140 */     this.texWidth = roundUpPOT(width);
/* 141 */     Util.checkGLError();
/* 142 */     this.texHeight = roundUpPOT(height);
/* 143 */     Util.checkGLError();
/* 144 */     if (this.texWidth != width || this.texHeight != height) {
/* 145 */       Util.checkGLError();
/* 146 */       GL11.glTexImage2D(3553, 0, fmt.glInternalFormat, this.texWidth, this.texHeight, 0, fmt.glFormat, 5121, (ByteBuffer)null);
/*     */ 
/*     */ 
/*     */       
/* 150 */       if (buf != null) {
/* 151 */         Util.checkGLError();
/* 152 */         GL11.glTexSubImage2D(3553, 0, 0, 0, width, height, fmt.glFormat, 5121, buf);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 157 */       Util.checkGLError();
/* 158 */       GL11.glTexImage2D(3553, 0, fmt.glInternalFormat, this.texWidth, this.texHeight, 0, fmt.glFormat, 5121, buf);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 163 */     Util.checkGLError();
/*     */     
/* 165 */     this.width = width;
/* 166 */     this.height = height;
/* 167 */     this.texData = buf;
/* 168 */     this.texDataFmt = fmt;
/*     */   }
/*     */   
/*     */   public void destroy() {
/* 172 */     if (this.id != 0) {
/*     */       
/* 174 */       GL11.glBindTexture(3553, 0);
/* 175 */       GL11.glDeleteTextures(this.id);
/* 176 */       this.id = 0;
/*     */     } 
/* 178 */     if (this.cursors != null) {
/* 179 */       for (LWJGLCursor cursor : this.cursors) {
/* 180 */         cursor.destroy();
/*     */       }
/* 182 */       this.cursors.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 187 */     return this.width;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 191 */     return this.height;
/*     */   }
/*     */   
/*     */   public int getTexWidth() {
/* 195 */     return this.texWidth;
/*     */   }
/*     */   
/*     */   public int getTexHeight() {
/* 199 */     return this.texHeight;
/*     */   }
/*     */   
/*     */   public boolean bind(Color color) {
/* 203 */     if (this.id != 0) {
/* 204 */       GL11.glBindTexture(3553, this.id);
/* 205 */       this.renderer.tintStack.setColor(color);
/* 206 */       return true;
/*     */     } 
/* 208 */     return false;
/*     */   }
/*     */   
/*     */   public boolean bind() {
/* 212 */     if (this.id != 0) {
/* 213 */       GL11.glBindTexture(3553, this.id);
/* 214 */       return true;
/*     */     } 
/* 216 */     return false;
/*     */   }
/*     */   
/*     */   public Image getImage(int x, int y, int width, int height, Color tintColor, boolean tiled, Texture.Rotation rotation) {
/* 220 */     if (x < 0 || x >= getWidth()) {
/* 221 */       throw new IllegalArgumentException("x");
/*     */     }
/* 223 */     if (y < 0 || y >= getHeight()) {
/* 224 */       throw new IllegalArgumentException("y");
/*     */     }
/* 226 */     if (x + Math.abs(width) > getWidth()) {
/* 227 */       throw new IllegalArgumentException("width");
/*     */     }
/* 229 */     if (y + Math.abs(height) > getHeight()) {
/* 230 */       throw new IllegalArgumentException("height");
/*     */     }
/* 232 */     if (rotation != Texture.Rotation.NONE || (tiled && (width < 0 || height < 0)))
/* 233 */       return new TextureAreaRotated(this, x, y, width, height, tintColor, tiled, rotation); 
/* 234 */     if (tiled) {
/* 235 */       return new TextureAreaTiled(this, x, y, width, height, tintColor);
/*     */     }
/* 237 */     return new TextureArea(this, x, y, width, height, tintColor);
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseCursor createCursor(int x, int y, int width, int height, int hotSpotX, int hotSpotY, Image imageRef) {
/* 242 */     if (this.renderer.isUseSWMouseCursors() || imageRef != null) {
/* 243 */       return new SWCursor(this, x, y, width, height, hotSpotX, hotSpotY, imageRef);
/*     */     }
/* 245 */     if (this.texData != null) {
/* 246 */       LWJGLCursor cursor = new LWJGLCursor(this.texData, this.texDataFmt, this.texDataFmt.getPixelSize() * this.width, x, y, width, height, hotSpotX, hotSpotY);
/*     */ 
/*     */       
/* 249 */       if (this.cursors == null) {
/* 250 */         this.cursors = new ArrayList<LWJGLCursor>();
/*     */       }
/* 252 */       this.cursors.add(cursor);
/*     */       
/* 254 */       return cursor;
/*     */     } 
/* 256 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void themeLoadingDone() {}
/*     */ 
/*     */   
/*     */   static int roundUpPOT(int value) {
/* 265 */     return 1 << 32 - Integer.numberOfLeadingZeros(value - 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\LWJGLTexture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */