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
/*    */ public class EnumListModel<T extends Enum<T>>
/*    */   extends SimpleListModel<T>
/*    */ {
/*    */   private final Class<T> enumClass;
/*    */   private final T[] enumValues;
/*    */   
/*    */   public EnumListModel(Class<T> enumClass) {
/* 43 */     if (!enumClass.isEnum()) {
/* 44 */       throw new IllegalArgumentException("not an enum class");
/*    */     }
/* 46 */     this.enumClass = enumClass;
/* 47 */     this.enumValues = enumClass.getEnumConstants();
/*    */   }
/*    */   
/*    */   public Class<T> getEnumClass() {
/* 51 */     return this.enumClass;
/*    */   }
/*    */   
/*    */   public T getEntry(int index) {
/* 55 */     return this.enumValues[index];
/*    */   }
/*    */   
/*    */   public int getNumEntries() {
/* 59 */     return this.enumValues.length;
/*    */   }
/*    */   
/*    */   public int findEntry(T value) {
/* 63 */     for (int i = 0, n = this.enumValues.length; i < n; i++) {
/* 64 */       if (this.enumValues[i] == value) {
/* 65 */         return i;
/*    */       }
/*    */     } 
/* 68 */     return -1;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\EnumListModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */