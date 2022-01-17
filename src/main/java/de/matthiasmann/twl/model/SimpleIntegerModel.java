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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimpleIntegerModel
/*    */   extends HasCallback
/*    */   implements IntegerModel
/*    */ {
/*    */   private final int minValue;
/*    */   private final int maxValue;
/*    */   private int value;
/*    */   
/*    */   public SimpleIntegerModel(int minValue, int maxValue, int value) {
/* 51 */     if (maxValue < minValue) {
/* 52 */       throw new IllegalArgumentException("maxValue < minValue");
/*    */     }
/* 54 */     this.minValue = minValue;
/* 55 */     this.maxValue = maxValue;
/* 56 */     this.value = value;
/*    */   }
/*    */   
/*    */   public int getMaxValue() {
/* 60 */     return this.maxValue;
/*    */   }
/*    */   
/*    */   public int getMinValue() {
/* 64 */     return this.minValue;
/*    */   }
/*    */   
/*    */   public int getValue() {
/* 68 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(int value) {
/* 72 */     if (this.value != value) {
/* 73 */       this.value = value;
/* 74 */       doCallback();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleIntegerModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */