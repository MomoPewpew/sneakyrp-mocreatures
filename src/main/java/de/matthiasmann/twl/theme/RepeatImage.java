/*     */ package de.matthiasmann.twl.theme;
/*     */ 
/*     */ import de.matthiasmann.twl.Border;
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import de.matthiasmann.twl.renderer.SupportsDrawRepeat;
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
/*     */ class RepeatImage
/*     */   implements Image, HasBorder, SupportsDrawRepeat
/*     */ {
/*     */   private final Image base;
/*     */   private final Border border;
/*     */   private final boolean repeatX;
/*     */   private final boolean repeatY;
/*     */   private final SupportsDrawRepeat sdr;
/*     */   
/*     */   RepeatImage(Image base, Border border, boolean repeatX, boolean repeatY) {
/*  51 */     assert repeatX || repeatY;
/*  52 */     this.base = base;
/*  53 */     this.border = border;
/*  54 */     this.repeatX = repeatX;
/*  55 */     this.repeatY = repeatY;
/*     */     
/*  57 */     if (base instanceof SupportsDrawRepeat) {
/*  58 */       this.sdr = (SupportsDrawRepeat)base;
/*     */     } else {
/*  60 */       this.sdr = this;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getWidth() {
/*  65 */     return this.base.getWidth();
/*     */   }
/*     */   
/*     */   public int getHeight() {
/*  69 */     return this.base.getHeight();
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y) {
/*  73 */     this.base.draw(as, x, y);
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y, int width, int height) {
/*  77 */     int countX = this.repeatX ? Math.max(1, width / this.base.getWidth()) : 1;
/*  78 */     int countY = this.repeatY ? Math.max(1, height / this.base.getHeight()) : 1;
/*  79 */     this.sdr.draw(as, x, y, width, height, countX, countY);
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y, int width, int height, int repeatCountX, int repeatCountY) {
/*  83 */     while (repeatCountY > 0) {
/*  84 */       int rowHeight = height / repeatCountY;
/*     */       
/*  86 */       int cx = 0;
/*  87 */       for (int xi = 0; xi < repeatCountX; ) {
/*  88 */         int nx = ++xi * width / repeatCountX;
/*  89 */         this.base.draw(as, x + cx, y, nx - cx, rowHeight);
/*  90 */         cx = nx;
/*     */       } 
/*     */       
/*  93 */       y += rowHeight;
/*  94 */       height -= rowHeight;
/*  95 */       repeatCountY--;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Border getBorder() {
/* 101 */     return this.border;
/*     */   }
/*     */   
/*     */   public Image createTintedVersion(Color color) {
/* 105 */     return new RepeatImage(this.base.createTintedVersion(color), this.border, this.repeatX, this.repeatY);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\RepeatImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */