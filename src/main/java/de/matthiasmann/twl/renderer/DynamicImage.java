/*    */ package de.matthiasmann.twl.renderer;
/*    */ 
/*    */ import java.nio.ByteBuffer;
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
/*    */ public interface DynamicImage
/*    */   extends Image, Resource
/*    */ {
/*    */   void update(ByteBuffer paramByteBuffer, Format paramFormat);
/*    */   
/*    */   void update(ByteBuffer paramByteBuffer, int paramInt, Format paramFormat);
/*    */   
/*    */   void update(int paramInt1, int paramInt2, int paramInt3, int paramInt4, ByteBuffer paramByteBuffer, Format paramFormat);
/*    */   
/*    */   void update(int paramInt1, int paramInt2, int paramInt3, int paramInt4, ByteBuffer paramByteBuffer, int paramInt5, Format paramFormat);
/*    */   
/*    */   public enum Format
/*    */   {
/* 46 */     RGBA,
/*    */ 
/*    */ 
/*    */     
/* 50 */     BGRA;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\DynamicImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */