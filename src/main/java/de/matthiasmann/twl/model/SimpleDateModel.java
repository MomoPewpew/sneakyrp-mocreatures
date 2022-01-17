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
/*    */ public class SimpleDateModel
/*    */   extends HasCallback
/*    */   implements DateModel
/*    */ {
/*    */   private long date;
/*    */   
/*    */   public SimpleDateModel() {
/* 46 */     this.date = System.currentTimeMillis();
/*    */   }
/*    */   
/*    */   public SimpleDateModel(long date) {
/* 50 */     this.date = date;
/*    */   }
/*    */   
/*    */   public long getValue() {
/* 54 */     return this.date;
/*    */   }
/*    */   
/*    */   public void setValue(long date) {
/* 58 */     if (this.date != date) {
/* 59 */       this.date = date;
/* 60 */       doCallback();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleDateModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */