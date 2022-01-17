/*    */ package de.matthiasmann.twl.model;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum SortOrder
/*    */ {
/* 41 */   ASCENDING {
/* 42 */     public <T> Comparator<T> map(Comparator<T> c) { return c; } public SortOrder invert() {
/* 43 */       return DESCENDING;
/*    */     } },
/* 45 */   DESCENDING {
/* 46 */     public <T> Comparator<T> map(Comparator<T> c) { return Collections.reverseOrder(c); } public SortOrder invert() {
/* 47 */       return ASCENDING;
/*    */     }
/*    */   };
/*    */   
/*    */   public abstract <T> Comparator<T> map(Comparator<T> paramComparator);
/*    */   
/*    */   public abstract SortOrder invert();
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SortOrder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */