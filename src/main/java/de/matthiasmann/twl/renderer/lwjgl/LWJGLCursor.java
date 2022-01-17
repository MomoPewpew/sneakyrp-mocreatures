/*     */ package de.matthiasmann.twl.renderer.lwjgl;
/*     */ 
/*     */ import de.matthiasmann.twl.renderer.MouseCursor;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.input.Cursor;
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
/*     */ class LWJGLCursor
/*     */   implements MouseCursor
/*     */ {
/*     */   Cursor cursor;
/*     */   
/*     */   LWJGLCursor(ByteBuffer src, LWJGLTexture.Format srcFmt, int srcStride, int x, int y, int width, int height, int hotSpotX, int hotSpotY) {
/*  49 */     width = Math.min(Cursor.getMaxCursorSize(), width);
/*  50 */     height = Math.min(Cursor.getMaxCursorSize(), height);
/*  51 */     int dstSize = Math.max(Cursor.getMinCursorSize(), Math.max(width, height));
/*     */     
/*  53 */     IntBuffer buf = BufferUtils.createIntBuffer(dstSize * dstSize); int dstPos;
/*  54 */     for (int row = height; row-- > 0; dstPos += dstSize) {
/*  55 */       int col, offset = srcStride * (y + row) + x * srcFmt.getPixelSize();
/*  56 */       buf.position(dstPos);
/*     */       
/*  58 */       switch (srcFmt) {
/*     */         case RGB:
/*  60 */           for (col = 0; col < width; col++) {
/*  61 */             int r = src.get(offset + col * 3 + 0) & 0xFF;
/*  62 */             int g = src.get(offset + col * 3 + 1) & 0xFF;
/*  63 */             int b = src.get(offset + col * 3 + 2) & 0xFF;
/*  64 */             buf.put(makeColor(r, g, b, 255));
/*     */           } 
/*     */           break;
/*     */         case RGBA:
/*  68 */           for (col = 0; col < width; col++) {
/*  69 */             int r = src.get(offset + col * 4 + 0) & 0xFF;
/*  70 */             int g = src.get(offset + col * 4 + 1) & 0xFF;
/*  71 */             int b = src.get(offset + col * 4 + 2) & 0xFF;
/*  72 */             int a = src.get(offset + col * 4 + 3) & 0xFF;
/*  73 */             buf.put(makeColor(r, g, b, a));
/*     */           } 
/*     */           break;
/*     */         case ABGR:
/*  77 */           for (col = 0; col < width; col++) {
/*  78 */             int r = src.get(offset + col * 4 + 3) & 0xFF;
/*  79 */             int g = src.get(offset + col * 4 + 2) & 0xFF;
/*  80 */             int b = src.get(offset + col * 4 + 1) & 0xFF;
/*  81 */             int a = src.get(offset + col * 4 + 0) & 0xFF;
/*  82 */             buf.put(makeColor(r, g, b, a));
/*     */           } 
/*     */           break;
/*     */         default:
/*  86 */           throw new IllegalStateException("Unsupported color format");
/*     */       } 
/*     */     } 
/*  89 */     buf.clear();
/*     */     
/*     */     try {
/*  92 */       this.cursor = new Cursor(dstSize, dstSize, hotSpotX, Math.min(dstSize - 1, height - hotSpotY - 1), 1, buf, null);
/*     */     }
/*  94 */     catch (LWJGLException ex) {
/*  95 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int makeColor(int r, int g, int b, int a) {
/* 100 */     a = (a > 222) ? 255 : 0;
/* 101 */     return a << 24 | r << 16 | g << 8 | b;
/*     */   }
/*     */   
/*     */   void destroy() {
/* 105 */     if (this.cursor != null) {
/* 106 */       this.cursor.destroy();
/* 107 */       this.cursor = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\LWJGLCursor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */