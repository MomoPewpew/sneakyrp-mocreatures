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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OptionBooleanModel
/*    */   extends AbstractOptionModel
/*    */ {
/*    */   private final IntegerModel optionState;
/*    */   private final int optionCode;
/*    */   
/*    */   public OptionBooleanModel(IntegerModel optionState, int optionCode) {
/* 55 */     if (optionState == null) {
/* 56 */       throw new NullPointerException("optionState");
/*    */     }
/* 58 */     if (optionCode < optionState.getMinValue() || optionCode > optionState.getMaxValue())
/*    */     {
/* 60 */       throw new IllegalArgumentException("optionCode");
/*    */     }
/* 62 */     this.optionState = optionState;
/* 63 */     this.optionCode = optionCode;
/*    */   }
/*    */   
/*    */   public boolean getValue() {
/* 67 */     return (this.optionState.getValue() == this.optionCode);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setValue(boolean value) {
/* 79 */     if (value) {
/* 80 */       this.optionState.setValue(this.optionCode);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void installSrcCallback(Runnable cb) {
/* 86 */     this.optionState.addCallback(cb);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void removeSrcCallback(Runnable cb) {
/* 91 */     this.optionState.removeCallback(cb);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\OptionBooleanModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */