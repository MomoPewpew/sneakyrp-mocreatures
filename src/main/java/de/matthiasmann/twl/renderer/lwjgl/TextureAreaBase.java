/*    */ package de.matthiasmann.twl.renderer.lwjgl;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TextureAreaBase
/*    */ {
/*    */   protected final float tx0;
/*    */   protected final float ty0;
/*    */   protected final float tx1;
/*    */   protected final float ty1;
/*    */   protected final short width;
/*    */   protected final short height;
/*    */   
/*    */   TextureAreaBase(int x, int y, int width, int height, float texWidth, float texHeight) {
/* 50 */     this.width = (short)Math.abs(width);
/* 51 */     this.height = (short)Math.abs(height);
/* 52 */     float fx = x;
/* 53 */     float fy = y;
/* 54 */     if (width == 1 || width == -1) {
/* 55 */       fx += 0.5F;
/* 56 */       width = 0;
/* 57 */     } else if (width < 0) {
/* 58 */       fx -= width;
/*    */     } 
/* 60 */     if (height == 1 || height == -1) {
/* 61 */       fy += 0.5F;
/* 62 */       height = 0;
/* 63 */     } else if (height < 0) {
/* 64 */       fy -= height;
/*    */     } 
/* 66 */     this.tx0 = fx / texWidth;
/* 67 */     this.ty0 = fy / texHeight;
/* 68 */     this.tx1 = this.tx0 + width / texWidth;
/* 69 */     this.ty1 = this.ty0 + height / texHeight;
/*    */   }
/*    */   
/*    */   TextureAreaBase(TextureAreaBase src) {
/* 73 */     this.tx0 = src.tx0;
/* 74 */     this.ty0 = src.ty0;
/* 75 */     this.tx1 = src.tx1;
/* 76 */     this.ty1 = src.ty1;
/* 77 */     this.width = src.width;
/* 78 */     this.height = src.height;
/*    */   }
/*    */   
/*    */   public int getWidth() {
/* 82 */     return this.width;
/*    */   }
/*    */   
/*    */   public int getHeight() {
/* 86 */     return this.height;
/*    */   }
/*    */   
/*    */   void drawQuad(int x, int y, int w, int h) {
/* 90 */     GL11.glTexCoord2f(this.tx0, this.ty0); GL11.glVertex2i(x, y);
/* 91 */     GL11.glTexCoord2f(this.tx0, this.ty1); GL11.glVertex2i(x, y + h);
/* 92 */     GL11.glTexCoord2f(this.tx1, this.ty1); GL11.glVertex2i(x + w, y + h);
/* 93 */     GL11.glTexCoord2f(this.tx1, this.ty0); GL11.glVertex2i(x + w, y);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\TextureAreaBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */