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
/*     */ public class Border
/*     */ {
/*  38 */   public static final Border ZERO = new Border(0);
/*     */   
/*     */   private final int top;
/*     */   private final int left;
/*     */   private final int bottom;
/*     */   private final int right;
/*     */   
/*     */   public Border(int all) {
/*  46 */     this.top = all;
/*  47 */     this.left = all;
/*  48 */     this.bottom = all;
/*  49 */     this.right = all;
/*     */   }
/*     */   
/*     */   public Border(int horz, int vert) {
/*  53 */     this.top = vert;
/*  54 */     this.left = horz;
/*  55 */     this.bottom = vert;
/*  56 */     this.right = horz;
/*     */   }
/*     */   
/*     */   public Border(int top, int left, int bottom, int right) {
/*  60 */     this.top = top;
/*  61 */     this.left = left;
/*  62 */     this.bottom = bottom;
/*  63 */     this.right = right;
/*     */   }
/*     */   
/*     */   public int getBorderBottom() {
/*  67 */     return this.bottom;
/*     */   }
/*     */   
/*     */   public int getBorderLeft() {
/*  71 */     return this.left;
/*     */   }
/*     */   
/*     */   public int getBorderRight() {
/*  75 */     return this.right;
/*     */   }
/*     */   
/*     */   public int getBorderTop() {
/*  79 */     return this.top;
/*     */   }
/*     */   
/*     */   public int getBottom() {
/*  83 */     return this.bottom;
/*     */   }
/*     */   
/*     */   public int getLeft() {
/*  87 */     return this.left;
/*     */   }
/*     */   
/*     */   public int getRight() {
/*  91 */     return this.right;
/*     */   }
/*     */   
/*     */   public int getTop() {
/*  95 */     return this.top;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 100 */     return "[Border top=" + this.top + " left=" + this.left + " bottom=" + this.bottom + " right=" + this.right + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Border.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */