/*     */ package de.matthiasmann.twl.theme;
/*     */ 
/*     */ import de.matthiasmann.twl.Border;
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import de.matthiasmann.twl.utils.StateExpression;
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
/*     */ 
/*     */ class ImageAdjustments
/*     */   implements Image, HasBorder
/*     */ {
/*     */   final Image image;
/*     */   final Border border;
/*     */   final Border inset;
/*     */   final int sizeOverwriteH;
/*     */   final int sizeOverwriteV;
/*     */   final boolean center;
/*     */   final StateExpression condition;
/*     */   
/*     */   ImageAdjustments(Image image, Border border, Border inset, int sizeOverwriteH, int sizeOverwriteV, boolean center, StateExpression condition) {
/*  55 */     this.image = image;
/*  56 */     this.border = border;
/*  57 */     this.inset = inset;
/*  58 */     this.sizeOverwriteH = sizeOverwriteH;
/*  59 */     this.sizeOverwriteV = sizeOverwriteV;
/*  60 */     this.center = center;
/*  61 */     this.condition = condition;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/*  65 */     if (this.sizeOverwriteH >= 0)
/*  66 */       return this.sizeOverwriteH; 
/*  67 */     if (this.inset != null) {
/*  68 */       return this.image.getWidth() + this.inset.getBorderLeft() + this.inset.getBorderRight();
/*     */     }
/*  70 */     return this.image.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHeight() {
/*  75 */     if (this.sizeOverwriteV >= 0)
/*  76 */       return this.sizeOverwriteV; 
/*  77 */     if (this.inset != null) {
/*  78 */       return this.image.getHeight() + this.inset.getBorderTop() + this.inset.getBorderBottom();
/*     */     }
/*  80 */     return this.image.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public void draw(AnimationState as, int x, int y, int width, int height) {
/*  85 */     if (this.condition == null || this.condition.evaluate(as)) {
/*  86 */       if (this.inset != null) {
/*  87 */         x += this.inset.getBorderLeft();
/*  88 */         y += this.inset.getBorderTop();
/*  89 */         width = Math.max(0, width - this.inset.getBorderLeft() - this.inset.getBorderRight());
/*  90 */         height = Math.max(0, height - this.inset.getBorderTop() - this.inset.getBorderBottom());
/*     */       } 
/*  92 */       if (this.center) {
/*  93 */         int w = Math.min(width, this.image.getWidth());
/*  94 */         int h = Math.min(height, this.image.getHeight());
/*  95 */         x += (width - w) / 2;
/*  96 */         y += (height - h) / 2;
/*  97 */         width = w;
/*  98 */         height = h;
/*     */       } 
/* 100 */       this.image.draw(as, x, y, width, height);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y) {
/* 105 */     draw(as, x, y, this.image.getWidth(), this.image.getHeight());
/*     */   }
/*     */   
/*     */   public Border getBorder() {
/* 109 */     return this.border;
/*     */   }
/*     */   
/*     */   public Image createTintedVersion(Color color) {
/* 113 */     return new ImageAdjustments(this.image.createTintedVersion(color), this.border, this.inset, this.sizeOverwriteH, this.sizeOverwriteV, this.center, this.condition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isSimple() {
/* 120 */     return (!this.center && this.inset == null && this.sizeOverwriteH < 0 && this.sizeOverwriteV < 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\ImageAdjustments.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */