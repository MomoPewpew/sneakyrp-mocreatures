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
/*    */ public abstract class AbstractEnumModel<T extends Enum<T>>
/*    */   extends HasCallback
/*    */   implements EnumModel<T>
/*    */ {
/*    */   private final Class<T> enumClass;
/*    */   
/*    */   protected AbstractEnumModel(Class<T> clazz) {
/* 43 */     if (clazz == null) {
/* 44 */       throw new NullPointerException("clazz");
/*    */     }
/* 46 */     this.enumClass = clazz;
/*    */   }
/*    */   
/*    */   public Class<T> getEnumClass() {
/* 50 */     return this.enumClass;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\AbstractEnumModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */