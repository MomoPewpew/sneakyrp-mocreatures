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
/*    */ public class SimpleBooleanModel
/*    */   extends HasCallback
/*    */   implements BooleanModel
/*    */ {
/*    */   private boolean value;
/*    */   
/*    */   public SimpleBooleanModel() {
/* 41 */     this(false);
/*    */   }
/*    */   
/*    */   public SimpleBooleanModel(boolean value) {
/* 45 */     this.value = value;
/*    */   }
/*    */   
/*    */   public boolean getValue() {
/* 49 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(boolean value) {
/* 53 */     if (this.value != value) {
/* 54 */       this.value = value;
/* 55 */       doCallback();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleBooleanModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */