/*    */ package de.matthiasmann.twl.model;
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
/*    */ 
/*    */ 
/*    */ public class SimpleFloatModel
/*    */   extends AbstractFloatModel
/*    */ {
/*    */   private final float minValue;
/*    */   private final float maxValue;
/*    */   private float value;
/*    */   
/*    */   public SimpleFloatModel(float minValue, float maxValue, float value) {
/* 47 */     if (Float.isNaN(minValue)) {
/* 48 */       throw new IllegalArgumentException("minValue is NaN");
/*    */     }
/* 50 */     if (Float.isNaN(maxValue)) {
/* 51 */       throw new IllegalArgumentException("maxValue is NaN");
/*    */     }
/* 53 */     if (minValue > maxValue) {
/* 54 */       throw new IllegalArgumentException("minValue > maxValue");
/*    */     }
/* 56 */     this.minValue = minValue;
/* 57 */     this.maxValue = maxValue;
/* 58 */     this.value = limit(value);
/*    */   }
/*    */   
/*    */   public float getMaxValue() {
/* 62 */     return this.maxValue;
/*    */   }
/*    */   
/*    */   public float getMinValue() {
/* 66 */     return this.minValue;
/*    */   }
/*    */   
/*    */   public float getValue() {
/* 70 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(float value) {
/* 74 */     value = limit(value);
/* 75 */     if (this.value != value) {
/* 76 */       this.value = value;
/* 77 */       doCallback();
/*    */     } 
/*    */   }
/*    */   
/*    */   protected float limit(float value) {
/* 82 */     if (Float.isNaN(value)) {
/* 83 */       return this.minValue;
/*    */     }
/* 85 */     return Math.max(this.minValue, Math.min(this.maxValue, value));
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleFloatModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */