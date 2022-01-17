/*     */ package de.matthiasmann.twl.renderer.lwjgl;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public class TintStack
/*     */ {
/*     */   private static final float ONE_OVER_255 = 0.003921569F;
/*     */   final TintStack prev;
/*     */   TintStack next;
/*     */   float r;
/*     */   float g;
/*     */   float b;
/*     */   float a;
/*     */   
/*     */   public TintStack() {
/*  50 */     this.prev = this;
/*  51 */     this.r = 0.003921569F;
/*  52 */     this.g = 0.003921569F;
/*  53 */     this.b = 0.003921569F;
/*  54 */     this.a = 0.003921569F;
/*     */   }
/*     */   
/*     */   private TintStack(TintStack prev) {
/*  58 */     this.prev = prev;
/*     */   }
/*     */   
/*     */   public TintStack pushReset() {
/*  62 */     if (this.next == null) {
/*  63 */       this.next = new TintStack(this);
/*     */     }
/*  65 */     this.next.r = 0.003921569F;
/*  66 */     this.next.g = 0.003921569F;
/*  67 */     this.next.b = 0.003921569F;
/*  68 */     this.next.a = 0.003921569F;
/*  69 */     return this.next;
/*     */   }
/*     */   
/*     */   public TintStack push(float r, float g, float b, float a) {
/*  73 */     if (this.next == null) {
/*  74 */       this.next = new TintStack(this);
/*     */     }
/*  76 */     this.r *= r;
/*  77 */     this.g *= g;
/*  78 */     this.b *= b;
/*  79 */     this.a *= a;
/*  80 */     return this.next;
/*     */   }
/*     */   
/*     */   public TintStack push(Color color) {
/*  84 */     return push(color.getRedFloat(), color.getGreenFloat(), color.getBlueFloat(), color.getAlphaFloat());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TintStack pop() {
/*  92 */     return this.prev;
/*     */   }
/*     */   
/*     */   public float getR() {
/*  96 */     return this.r;
/*     */   }
/*     */   
/*     */   public float getG() {
/* 100 */     return this.g;
/*     */   }
/*     */   
/*     */   public float getB() {
/* 104 */     return this.b;
/*     */   }
/*     */   
/*     */   public float getA() {
/* 108 */     return this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColor(Color color) {
/* 117 */     GL11.glColor4f(this.r * color.getRed(), this.g * color.getGreen(), this.b * color.getBlue(), this.a * color.getAlpha());
/*     */   }
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
/*     */   public void setColor(float r, float g, float b, float a) {
/* 133 */     GL11.glColor4f(this.r * r, this.g * g, this.b * b, this.a * a);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\TintStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */