/*    */ package de.matthiasmann.twl.renderer;
/*    */ 
/*    */ import de.matthiasmann.twl.Color;
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
/*    */ public interface Texture
/*    */   extends Resource
/*    */ {
/*    */   int getWidth();
/*    */   
/*    */   int getHeight();
/*    */   
/*    */   Image getImage(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Color paramColor, boolean paramBoolean, Rotation paramRotation);
/*    */   
/*    */   MouseCursor createCursor(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, Image paramImage);
/*    */   
/*    */   void themeLoadingDone();
/*    */   
/*    */   public enum Rotation
/*    */   {
/* 75 */     NONE,
/* 76 */     CLOCKWISE_90,
/* 77 */     CLOCKWISE_180,
/* 78 */     CLOCKWISE_270;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\Texture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */