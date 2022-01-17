/*    */ package de.matthiasmann.twl.theme;
/*    */ 
/*    */ import de.matthiasmann.twl.Border;
/*    */ import de.matthiasmann.twl.Color;
/*    */ import de.matthiasmann.twl.renderer.AnimationState;
/*    */ import de.matthiasmann.twl.renderer.Image;
/*    */ import de.matthiasmann.twl.utils.StateSelect;
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
/*    */ public class StateSelectImage
/*    */   implements Image, HasBorder
/*    */ {
/*    */   final Image[] images;
/*    */   final StateSelect select;
/*    */   final Border border;
/*    */   
/*    */   public StateSelectImage(StateSelect select, Border border, Image... images) {
/* 49 */     assert images.length >= select.getNumExpressions();
/* 50 */     assert images.length <= select.getNumExpressions() + 1;
/*    */     
/* 52 */     this.images = images;
/* 53 */     this.select = select;
/* 54 */     this.border = border;
/*    */   }
/*    */   
/*    */   public int getWidth() {
/* 58 */     return this.images[0].getWidth();
/*    */   }
/*    */   
/*    */   public int getHeight() {
/* 62 */     return this.images[0].getHeight();
/*    */   }
/*    */   
/*    */   public void draw(AnimationState as, int x, int y) {
/* 66 */     draw(as, x, y, getWidth(), getHeight());
/*    */   }
/*    */   
/*    */   public void draw(AnimationState as, int x, int y, int width, int height) {
/* 70 */     int idx = this.select.evaluate(as);
/* 71 */     if (idx < this.images.length) {
/* 72 */       this.images[idx].draw(as, x, y, width, height);
/*    */     }
/*    */   }
/*    */   
/*    */   public Border getBorder() {
/* 77 */     return this.border;
/*    */   }
/*    */   
/*    */   public Image createTintedVersion(Color color) {
/* 81 */     Image[] newImages = new Image[this.images.length];
/* 82 */     for (int i = 0; i < newImages.length; i++) {
/* 83 */       newImages[i] = this.images[i].createTintedVersion(color);
/*    */     }
/* 85 */     return new StateSelectImage(this.select, this.border, newImages);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\StateSelectImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */