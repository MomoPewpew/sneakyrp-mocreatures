/*     */ package de.matthiasmann.twl;
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
/*     */ 
/*     */ 
/*     */ public class Rect
/*     */ {
/*     */   private int x0;
/*     */   private int y0;
/*     */   private int x1;
/*     */   private int y1;
/*     */   
/*     */   public Rect() {}
/*     */   
/*     */   public Rect(int x, int y, int w, int h) {
/*  49 */     setXYWH(x, y, w, h);
/*     */   }
/*     */ 
/*     */   
/*     */   public Rect(Rect src) {
/*  54 */     set(src.getX(), src.getY(), src.getRight(), src.getBottom());
/*     */   }
/*     */   
/*     */   public void setXYWH(int x, int y, int w, int h) {
/*  58 */     this.x0 = x;
/*  59 */     this.y0 = y;
/*  60 */     this.x1 = x + Math.max(0, w);
/*  61 */     this.y1 = y + Math.max(0, h);
/*     */   }
/*     */   
/*     */   public void set(int x0, int y0, int x1, int y1) {
/*  65 */     this.x0 = x0;
/*  66 */     this.y0 = y0;
/*  67 */     this.x1 = x1;
/*  68 */     this.y1 = y1;
/*     */   }
/*     */   
/*     */   public void set(Rect src) {
/*  72 */     this.x0 = src.x0;
/*  73 */     this.y0 = src.y0;
/*  74 */     this.x1 = src.x1;
/*  75 */     this.y1 = src.y1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void intersect(Rect other) {
/*  85 */     this.x0 = Math.max(this.x0, other.x0);
/*  86 */     this.y0 = Math.max(this.y0, other.y0);
/*  87 */     this.x1 = Math.min(this.x1, other.x1);
/*  88 */     this.y1 = Math.min(this.y1, other.y1);
/*  89 */     if (this.x1 < this.x0 || this.y1 < this.y0) {
/*  90 */       this.x1 = this.x0;
/*  91 */       this.y1 = this.y0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isInside(int x, int y) {
/*  96 */     return (x >= this.x0 && y >= this.y0 && x < this.x1 && y < this.y1);
/*     */   }
/*     */   
/*     */   public int getX() {
/* 100 */     return this.x0;
/*     */   }
/*     */   
/*     */   public int getY() {
/* 104 */     return this.y0;
/*     */   }
/*     */   
/*     */   public int getRight() {
/* 108 */     return this.x1;
/*     */   }
/*     */   
/*     */   public int getBottom() {
/* 112 */     return this.y1;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 116 */     return this.x1 - this.x0;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 120 */     return this.y1 - this.y0;
/*     */   }
/*     */   
/*     */   public int getCenterX() {
/* 124 */     return (this.x0 + this.x1) / 2;
/*     */   }
/*     */   
/*     */   public int getCenterY() {
/* 128 */     return (this.y0 + this.y1) / 2;
/*     */   }
/*     */   
/*     */   public Dimension getSize() {
/* 132 */     return new Dimension(getWidth(), getHeight());
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 136 */     return (this.x1 <= this.x0 || this.y1 <= this.y0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 141 */     return "Rect[x0=" + this.x0 + ", y0=" + this.y0 + ", x1=" + this.x1 + ", y1=" + this.y1 + ']';
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Rect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */