/*     */ package de.matthiasmann.twl.renderer.lwjgl;
/*     */ 
/*     */ import de.matthiasmann.twl.renderer.CacheContext;
/*     */ import de.matthiasmann.twl.utils.PNGDecoder;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import org.lwjgl.BufferUtils;
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
/*     */ public class LWJGLCacheContext
/*     */   implements CacheContext
/*     */ {
/*     */   final LWJGLRenderer renderer;
/*     */   final HashMap<String, LWJGLTexture> textures;
/*     */   final HashMap<String, BitmapFont> fontCache;
/*     */   final ArrayList<LWJGLTexture> allTextures;
/*     */   boolean valid;
/*     */   
/*     */   protected LWJGLCacheContext(LWJGLRenderer renderer) {
/*  57 */     this.renderer = renderer;
/*  58 */     this.textures = new HashMap<String, LWJGLTexture>();
/*  59 */     this.fontCache = new HashMap<String, BitmapFont>();
/*  60 */     this.allTextures = new ArrayList<LWJGLTexture>();
/*  61 */     this.valid = true;
/*     */   }
/*     */   
/*     */   LWJGLTexture loadTexture(URL url, LWJGLTexture.Format fmt, LWJGLTexture.Filter filter) throws IOException {
/*  65 */     String urlString = url.toString();
/*  66 */     Util.checkGLError();
/*  67 */     LWJGLTexture texture = this.textures.get(urlString);
/*  68 */     Util.checkGLError();
/*  69 */     if (texture == null) {
/*  70 */       texture = createTexture(url, fmt, filter, null);
/*  71 */       Util.checkGLError();
/*  72 */       this.textures.put(urlString, texture);
/*     */     } 
/*  74 */     return texture;
/*     */   }
/*     */   
/*     */   LWJGLTexture createTexture(URL textureUrl, LWJGLTexture.Format fmt, LWJGLTexture.Filter filter, TexturePostProcessing tpp) throws IOException {
/*  78 */     if (!this.valid) {
/*  79 */       throw new IllegalStateException("CacheContext already destroyed");
/*     */     }
/*  81 */     Util.checkGLError();
/*  82 */     InputStream is = textureUrl.openStream();
/*  83 */     Util.checkGLError();
/*     */     try {
/*  85 */       Util.checkGLError();
/*  86 */       PNGDecoder dec = new PNGDecoder(is);
/*  87 */       Util.checkGLError();
/*  88 */       fmt = decideTextureFormat(dec, fmt);
/*  89 */       Util.checkGLError();
/*  90 */       int width = dec.getWidth();
/*  91 */       int height = dec.getHeight();
/*  92 */       int maxTextureSize = this.renderer.maxTextureSize;
/*     */       
/*  94 */       if (width > maxTextureSize || height > maxTextureSize) {
/*  95 */         throw new IOException("Texture size too large. Maximum supported texture by this system is " + maxTextureSize);
/*     */       }
/*  97 */       Util.checkGLError();
/*  98 */       if ((GLContext.getCapabilities()).GL_EXT_abgr) {
/*  99 */         Util.checkGLError();
/* 100 */         if (fmt == LWJGLTexture.Format.RGBA) {
/* 101 */           Util.checkGLError();
/* 102 */           fmt = LWJGLTexture.Format.ABGR;
/*     */         } 
/* 104 */       } else if (fmt == LWJGLTexture.Format.ABGR) {
/* 105 */         Util.checkGLError();
/* 106 */         fmt = LWJGLTexture.Format.RGBA;
/*     */       } 
/* 108 */       Util.checkGLError();
/* 109 */       int stride = width * fmt.getPixelSize();
/* 110 */       Util.checkGLError();
/* 111 */       ByteBuffer buf = BufferUtils.createByteBuffer(stride * height);
/* 112 */       Util.checkGLError();
/* 113 */       dec.decode(buf, stride, fmt.getPngFormat());
/* 114 */       Util.checkGLError();
/* 115 */       buf.flip();
/*     */       
/* 117 */       if (tpp != null) {
/* 118 */         Util.checkGLError();
/* 119 */         tpp.process(buf, stride, width, height, fmt);
/* 120 */         Util.checkGLError();
/*     */       } 
/* 122 */       Util.checkGLError();
/* 123 */       LWJGLTexture texture = new LWJGLTexture(this.renderer, width, height, buf, fmt, filter);
/* 124 */       this.allTextures.add(texture);
/* 125 */       return texture;
/* 126 */     } catch (IOException ex) {
/* 127 */       throw (IOException)(new IOException("Unable to load PNG file: " + textureUrl)).initCause(ex);
/*     */     } finally {
/*     */       try {
/* 130 */         is.close();
/* 131 */       } catch (IOException ex) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   BitmapFont loadBitmapFont(URL url) throws IOException {
/* 137 */     String urlString = url.toString();
/* 138 */     BitmapFont bmFont = this.fontCache.get(urlString);
/* 139 */     if (bmFont == null) {
/* 140 */       bmFont = BitmapFont.loadFont(this.renderer, url);
/* 141 */       this.fontCache.put(urlString, bmFont);
/*     */     } 
/* 143 */     return bmFont;
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/* 147 */     return this.valid;
/*     */   }
/*     */   
/*     */   public void destroy() {
/*     */     try {
/* 152 */       for (LWJGLTexture t : this.allTextures) {
/* 153 */         t.destroy();
/*     */       }
/* 155 */       for (BitmapFont f : this.fontCache.values()) {
/* 156 */         f.destroy();
/*     */       }
/*     */     } finally {
/* 159 */       this.textures.clear();
/* 160 */       this.fontCache.clear();
/* 161 */       this.allTextures.clear();
/* 162 */       this.valid = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static LWJGLTexture.Format decideTextureFormat(PNGDecoder decoder, LWJGLTexture.Format fmt) {
/* 167 */     if (fmt == LWJGLTexture.Format.COLOR) {
/* 168 */       fmt = autoColorFormat(decoder);
/*     */     }
/*     */     
/* 171 */     PNGDecoder.Format pngFormat = decoder.decideTextureFormat(fmt.getPngFormat());
/* 172 */     if (fmt.pngFormat == pngFormat) {
/* 173 */       return fmt;
/*     */     }
/*     */     
/* 176 */     switch (pngFormat) {
/*     */       case ALPHA:
/* 178 */         return LWJGLTexture.Format.ALPHA;
/*     */       case LUMINANCE:
/* 180 */         return LWJGLTexture.Format.LUMINANCE;
/*     */       case LUMINANCE_ALPHA:
/* 182 */         return LWJGLTexture.Format.LUMINANCE_ALPHA;
/*     */       case RGB:
/* 184 */         return LWJGLTexture.Format.RGB;
/*     */       case RGBA:
/* 186 */         return LWJGLTexture.Format.RGBA;
/*     */       case BGRA:
/* 188 */         return LWJGLTexture.Format.BGRA;
/*     */       case ABGR:
/* 190 */         return LWJGLTexture.Format.ABGR;
/*     */     } 
/* 192 */     throw new UnsupportedOperationException("PNGFormat not handled: " + pngFormat);
/*     */   }
/*     */ 
/*     */   
/*     */   private static LWJGLTexture.Format autoColorFormat(PNGDecoder decoder) {
/* 197 */     if (decoder.hasAlpha()) {
/* 198 */       if (decoder.isRGB()) {
/* 199 */         return LWJGLTexture.Format.ABGR;
/*     */       }
/* 201 */       return LWJGLTexture.Format.LUMINANCE_ALPHA;
/*     */     } 
/* 203 */     if (decoder.isRGB()) {
/* 204 */       return LWJGLTexture.Format.ABGR;
/*     */     }
/* 206 */     return LWJGLTexture.Format.LUMINANCE;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\LWJGLCacheContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */