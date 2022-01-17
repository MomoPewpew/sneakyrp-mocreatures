/*    */ package de.matthiasmann.twl.model;
/*    */ 
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimpleAutoCompletionResult
/*    */   extends AutoCompletionResult
/*    */ {
/*    */   private final String[] results;
/*    */   
/*    */   public SimpleAutoCompletionResult(String text, int prefixLength, Collection<String> results) {
/* 43 */     super(text, prefixLength);
/* 44 */     this.results = results.<String>toArray(new String[results.size()]);
/*    */   }
/*    */   
/*    */   public SimpleAutoCompletionResult(String text, int prefixLength, String... results) {
/* 48 */     super(text, prefixLength);
/* 49 */     this.results = (String[])results.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getNumResults() {
/* 54 */     return this.results.length;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResult(int idx) {
/* 59 */     return this.results[idx];
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleAutoCompletionResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */