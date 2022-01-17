/*    */ package de.matthiasmann.twl.renderer.lwjgl;
/*    */ 
/*    */ import de.matthiasmann.twl.renderer.Image;
/*    */ import de.matthiasmann.twl.renderer.MouseCursor;
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
/*    */ class SWCursor
/*    */   extends TextureAreaBase
/*    */   implements MouseCursor
/*    */ {
/*    */   private final LWJGLTexture texture;
/*    */   private final int hotSpotX;
/*    */   private final int hotSpotY;
/*    */   private final Image imageRef;
/*    */   
/*    */   SWCursor(LWJGLTexture texture, int x, int y, int width, int height, int hotSpotX, int hotSpotY, Image imageRef) {
/* 48 */     super(x, y, width, height, texture.getTexWidth(), texture.getTexHeight());
/* 49 */     this.texture = texture;
/* 50 */     this.hotSpotX = hotSpotX;
/* 51 */     this.hotSpotY = hotSpotY;
/* 52 */     this.imageRef = imageRef;
/*    */   }
/*    */   
/*    */   void render(int x, int y) {
/* 56 */     if (this.imageRef != null) {
/* 57 */       this.imageRef.draw(this.texture.renderer.swCursorAnimState, x - this.hotSpotX, y - this.hotSpotY);
/* 58 */     } else if (this.texture.bind()) {
/* 59 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 60 */       GL11.glBegin(7);
/* 61 */       drawQuad(x - this.hotSpotX, y - this.hotSpotY, this.width, this.height);
/* 62 */       GL11.glEnd();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\SWCursor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */