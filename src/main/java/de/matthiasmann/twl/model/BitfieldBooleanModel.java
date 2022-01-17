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
/*    */ public class BitfieldBooleanModel
/*    */   extends HasCallback
/*    */   implements BooleanModel
/*    */ {
/*    */   private final IntegerModel bitfield;
/*    */   private final int bitmask;
/*    */   
/*    */   public BitfieldBooleanModel(IntegerModel bitfield, int bit) {
/* 43 */     if (bitfield == null) {
/* 44 */       throw new NullPointerException("bitfield");
/*    */     }
/* 46 */     if (bit < 0 || bit > 30) {
/* 47 */       throw new IllegalArgumentException("invalid bit index");
/*    */     }
/* 49 */     if (bitfield.getMinValue() != 0) {
/* 50 */       throw new IllegalArgumentException("bitfield.getMinValue() != 0");
/*    */     }
/* 52 */     int bitfieldMax = bitfield.getMaxValue();
/* 53 */     if ((bitfieldMax & bitfieldMax + 1) != 0) {
/* 54 */       throw new IllegalArgumentException("bitfield.getmaxValue() must eb 2^x");
/*    */     }
/* 56 */     if (bitfieldMax < 1 << bit) {
/* 57 */       throw new IllegalArgumentException("bit index outside of bitfield range");
/*    */     }
/* 59 */     this.bitfield = bitfield;
/* 60 */     this.bitmask = 1 << bit;
/* 61 */     bitfield.addCallback(new CB());
/*    */   }
/*    */   
/*    */   public boolean getValue() {
/* 65 */     return ((this.bitfield.getValue() & this.bitmask) != 0);
/*    */   }
/*    */   
/*    */   public void setValue(boolean value) {
/* 69 */     int oldBFValue = this.bitfield.getValue();
/* 70 */     int newBFValue = value ? (oldBFValue | this.bitmask) : (oldBFValue & (this.bitmask ^ 0xFFFFFFFF));
/* 71 */     if (oldBFValue != newBFValue)
/* 72 */       this.bitfield.setValue(newBFValue); 
/*    */   }
/*    */   
/*    */   class CB
/*    */     implements Runnable
/*    */   {
/*    */     public void run() {
/* 79 */       BitfieldBooleanModel.this.doCallback();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\BitfieldBooleanModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */