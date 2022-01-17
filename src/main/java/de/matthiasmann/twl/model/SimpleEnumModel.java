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
/*    */ public class SimpleEnumModel<T extends Enum<T>>
/*    */   extends AbstractEnumModel<T>
/*    */ {
/*    */   private T value;
/*    */   
/*    */   public SimpleEnumModel(Class<T> clazz, T value) {
/* 43 */     super(clazz);
/* 44 */     if (value == null) {
/* 45 */       throw new NullPointerException("value");
/*    */     }
/* 47 */     if (!clazz.isInstance(value)) {
/* 48 */       throw new IllegalArgumentException("value");
/*    */     }
/* 50 */     this.value = value;
/*    */   }
/*    */   
/*    */   public T getValue() {
/* 54 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(T value) {
/* 58 */     if (!getEnumClass().isInstance(value)) {
/* 59 */       throw new IllegalArgumentException("value");
/*    */     }
/* 61 */     if (this.value != value) {
/* 62 */       this.value = value;
/* 63 */       doCallback();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleEnumModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */