/*    */ package de.matthiasmann.twl.renderer.lwjgl;
/*    */ 
/*    */ import java.nio.FloatBuffer;
/*    */ import org.lwjgl.BufferUtils;
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
/*    */ 
/*    */ public class VertexArray
/*    */ {
/*    */   private FloatBuffer va;
/*    */   
/*    */   public FloatBuffer allocate(int maxQuads) {
/* 48 */     int capacity = 16 * maxQuads;
/* 49 */     if (this.va == null || this.va.capacity() < capacity) {
/* 50 */       this.va = BufferUtils.createFloatBuffer(capacity);
/*    */     }
/* 52 */     this.va.clear();
/* 53 */     return this.va;
/*    */   }
/*    */   
/*    */   public void bind() {
/* 57 */     this.va.position(2);
/* 58 */     GL11.glVertexPointer(2, 16, this.va);
/* 59 */     this.va.position(0);
/* 60 */     GL11.glTexCoordPointer(2, 16, this.va);
/* 61 */     GL11.glEnableClientState(32884);
/* 62 */     GL11.glEnableClientState(32888);
/*    */   }
/*    */   
/*    */   public void drawVertices(int start, int count) {
/* 66 */     GL11.glDrawArrays(7, start, count);
/*    */   }
/*    */   
/*    */   public void drawQuads(int start, int count) {
/* 70 */     GL11.glDrawArrays(7, start * 4, count * 4);
/*    */   }
/*    */   
/*    */   public void unbind() {
/* 74 */     GL11.glDisableClientState(32884);
/* 75 */     GL11.glDisableClientState(32888);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\VertexArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */