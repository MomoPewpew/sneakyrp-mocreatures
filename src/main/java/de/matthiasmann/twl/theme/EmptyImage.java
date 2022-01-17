/*    */ package de.matthiasmann.twl.theme;
/*    */ 
/*    */ import de.matthiasmann.twl.Color;
/*    */ import de.matthiasmann.twl.renderer.AnimationState;
/*    */ import de.matthiasmann.twl.renderer.Image;
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
/*    */ public class EmptyImage
/*    */   implements Image
/*    */ {
/*    */   private final int width;
/*    */   private final int height;
/*    */   
/*    */   public EmptyImage(int width, int height) {
/* 47 */     this.width = width;
/* 48 */     this.height = height;
/*    */   }
/*    */ 
/*    */   
/*    */   public void draw(AnimationState as, int x, int y) {}
/*    */ 
/*    */   
/*    */   public void draw(AnimationState as, int x, int y, int width, int height) {}
/*    */   
/*    */   public int getWidth() {
/* 58 */     return this.width;
/*    */   }
/*    */   
/*    */   public int getHeight() {
/* 62 */     return this.height;
/*    */   }
/*    */   
/*    */   public Image createTintedVersion(Color color) {
/* 66 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\EmptyImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */