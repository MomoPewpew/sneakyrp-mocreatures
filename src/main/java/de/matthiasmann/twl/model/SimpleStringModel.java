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
/*    */ public class SimpleStringModel
/*    */   extends HasCallback
/*    */   implements StringModel
/*    */ {
/*    */   private String value;
/*    */   
/*    */   public SimpleStringModel() {
/* 42 */     this.value = "";
/*    */   }
/*    */   
/*    */   public SimpleStringModel(String value) {
/* 46 */     if (value == null) {
/* 47 */       throw new NullPointerException("value");
/*    */     }
/* 49 */     this.value = value;
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 53 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(String value) {
/* 57 */     if (value == null) {
/* 58 */       throw new NullPointerException("value");
/*    */     }
/* 60 */     if (!this.value.equals(value)) {
/* 61 */       this.value = value;
/* 62 */       doCallback();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleStringModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */