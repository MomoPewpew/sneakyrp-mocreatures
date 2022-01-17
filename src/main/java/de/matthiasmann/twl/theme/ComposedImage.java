/*    */ package de.matthiasmann.twl.theme;
/*    */ 
/*    */ import de.matthiasmann.twl.Border;
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
/*    */ 
/*    */ class ComposedImage
/*    */   implements Image, HasBorder
/*    */ {
/*    */   private final Image[] layers;
/*    */   private final Border border;
/*    */   
/*    */   public ComposedImage(Image[] layers, Border border) {
/* 49 */     this.layers = layers;
/* 50 */     this.border = border;
/*    */   }
/*    */   
/*    */   public void draw(AnimationState as, int x, int y) {
/* 54 */     draw(as, x, y, getWidth(), getHeight());
/*    */   }
/*    */   
/*    */   public void draw(AnimationState as, int x, int y, int width, int height) {
/* 58 */     for (Image layer : this.layers) {
/* 59 */       layer.draw(as, x, y, width, height);
/*    */     }
/*    */   }
/*    */   
/*    */   public int getHeight() {
/* 64 */     return this.layers[0].getHeight();
/*    */   }
/*    */   
/*    */   public int getWidth() {
/* 68 */     return this.layers[0].getWidth();
/*    */   }
/*    */   
/*    */   public Border getBorder() {
/* 72 */     return this.border;
/*    */   }
/*    */   
/*    */   public Image createTintedVersion(Color color) {
/* 76 */     Image[] newLayers = new Image[this.layers.length];
/* 77 */     for (int i = 0; i < newLayers.length; i++) {
/* 78 */       newLayers[i] = this.layers[i].createTintedVersion(color);
/*    */     }
/* 80 */     return new ComposedImage(newLayers, this.border);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\ComposedImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */