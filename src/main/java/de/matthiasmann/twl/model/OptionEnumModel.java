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
/*    */ public class OptionEnumModel<T extends Enum<T>>
/*    */   extends AbstractOptionModel
/*    */ {
/*    */   private final EnumModel<T> optionState;
/*    */   private final T optionCode;
/*    */   
/*    */   public OptionEnumModel(EnumModel<T> optionState, T optionCode) {
/* 49 */     if (optionState == null) {
/* 50 */       throw new NullPointerException("optionState");
/*    */     }
/* 52 */     if (optionCode == null) {
/* 53 */       throw new NullPointerException("optionCode");
/*    */     }
/*    */     
/* 56 */     this.optionState = optionState;
/* 57 */     this.optionCode = optionCode;
/*    */   }
/*    */   
/*    */   public boolean getValue() {
/* 61 */     return (this.optionState.getValue() == this.optionCode);
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
/* 73 */     if (value) {
/* 74 */       this.optionState.setValue(this.optionCode);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void installSrcCallback(Runnable cb) {
/* 80 */     this.optionState.addCallback(cb);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void removeSrcCallback(Runnable cb) {
/* 85 */     this.optionState.removeCallback(cb);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\OptionEnumModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */