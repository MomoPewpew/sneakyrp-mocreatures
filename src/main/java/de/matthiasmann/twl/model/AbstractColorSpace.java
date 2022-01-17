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
/*    */ public abstract class AbstractColorSpace
/*    */   implements ColorSpace
/*    */ {
/*    */   private final String colorSpaceName;
/*    */   private final String[] names;
/*    */   
/*    */   public AbstractColorSpace(String colorSpaceName, String... names) {
/* 43 */     this.colorSpaceName = colorSpaceName;
/* 44 */     this.names = names;
/*    */   }
/*    */   
/*    */   public String getComponentName(int component) {
/* 48 */     return this.names[component];
/*    */   }
/*    */   
/*    */   public String getColorSpaceName() {
/* 52 */     return this.colorSpaceName;
/*    */   }
/*    */   
/*    */   public int getNumComponents() {
/* 56 */     return this.names.length;
/*    */   }
/*    */   
/*    */   public float getMinValue(int component) {
/* 60 */     return 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\AbstractColorSpace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */