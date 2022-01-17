/*     */ package de.matthiasmann.twl.textarea;
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
/*     */ public final class Value
/*     */ {
/*     */   public final float value;
/*     */   public final Unit unit;
/*     */   
/*     */   public Value(float value, Unit unit) {
/*  42 */     this.value = value;
/*  43 */     this.unit = unit;
/*     */     
/*  45 */     if (unit == null) {
/*  46 */       throw new NullPointerException("unit");
/*     */     }
/*  48 */     if (unit == Unit.AUTO && value != 0.0F) {
/*  49 */       throw new IllegalArgumentException("value must be 0 for Unit.AUTO");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  55 */     if (this.unit == Unit.AUTO) {
/*  56 */       return this.unit.getPostfix();
/*     */     }
/*  58 */     return this.value + this.unit.getPostfix();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  63 */     if (obj instanceof Value) {
/*  64 */       Value other = (Value)obj;
/*  65 */       return (this.value == other.value && this.unit == other.unit);
/*     */     } 
/*  67 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  72 */     int hash = 3;
/*  73 */     hash = 17 * hash + Float.floatToIntBits(this.value);
/*  74 */     hash = 17 * hash + this.unit.hashCode();
/*  75 */     return hash;
/*     */   }
/*     */   
/*  78 */   public static final Value ZERO_PX = new Value(0.0F, Unit.PX);
/*  79 */   public static final Value AUTO = new Value(0.0F, Unit.AUTO);
/*     */   
/*     */   public enum Unit {
/*  82 */     PX(false, "px"),
/*  83 */     PT(false, "pt"),
/*  84 */     EM(true, "em"),
/*  85 */     EX(true, "ex"),
/*  86 */     PERCENT(false, "%"),
/*  87 */     AUTO(false, "auto");
/*     */     final boolean fontBased;
/*     */     final String postfix;
/*     */     
/*     */     Unit(boolean fontBased, String postfix) {
/*  92 */       this.fontBased = fontBased;
/*  93 */       this.postfix = postfix;
/*     */     }
/*     */     
/*     */     public boolean isFontBased() {
/*  97 */       return this.fontBased;
/*     */     }
/*     */     
/*     */     public String getPostfix() {
/* 101 */       return this.postfix;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\textarea\Value.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */