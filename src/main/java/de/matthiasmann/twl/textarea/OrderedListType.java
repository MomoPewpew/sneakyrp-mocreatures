/*    */ package de.matthiasmann.twl.textarea;
/*    */ 
/*    */ import de.matthiasmann.twl.utils.TextUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OrderedListType
/*    */ {
/* 41 */   public static final OrderedListType DECIMAL = new OrderedListType();
/*    */ 
/*    */   
/*    */   protected final String characterList;
/*    */ 
/*    */ 
/*    */   
/*    */   public OrderedListType() {
/* 49 */     this.characterList = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public OrderedListType(String characterList) {
/* 59 */     this.characterList = characterList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String format(int nr) {
/* 68 */     if (nr >= 1 && this.characterList != null) {
/* 69 */       return TextUtil.toCharListNumber(nr, this.characterList);
/*    */     }
/* 71 */     return Integer.toString(nr);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\textarea\OrderedListType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */