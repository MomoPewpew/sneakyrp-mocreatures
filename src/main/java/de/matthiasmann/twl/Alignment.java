/*    */ package de.matthiasmann.twl;
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
/*    */ public enum Alignment
/*    */ {
/* 38 */   LEFT(HAlignment.LEFT, 0, 1),
/* 39 */   CENTER(HAlignment.CENTER, 1, 1),
/* 40 */   RIGHT(HAlignment.RIGHT, 2, 1),
/* 41 */   TOP(HAlignment.CENTER, 1, 0),
/* 42 */   BOTTOM(HAlignment.CENTER, 1, 2),
/* 43 */   TOPLEFT(HAlignment.LEFT, 0, 0),
/* 44 */   TOPRIGHT(HAlignment.RIGHT, 2, 0),
/* 45 */   BOTTOMLEFT(HAlignment.LEFT, 0, 2),
/* 46 */   BOTTOMRIGHT(HAlignment.RIGHT, 2, 2),
/* 47 */   FILL(HAlignment.CENTER, 1, 1);
/*    */   
/*    */   final HAlignment fontHAlignment;
/*    */   final byte hpos;
/*    */   final byte vpos;
/*    */   
/*    */   Alignment(HAlignment fontHAlignment, int hpos, int vpos) {
/* 54 */     this.fontHAlignment = fontHAlignment;
/* 55 */     this.hpos = (byte)hpos;
/* 56 */     this.vpos = (byte)vpos;
/*    */   }
/*    */   
/*    */   public HAlignment getFontHAlignment() {
/* 60 */     return this.fontHAlignment;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getHPosition() {
/* 68 */     return this.hpos;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getVPosition() {
/* 76 */     return this.vpos;
/*    */   }
/*    */ 
/*    */   
/*    */   public int computePositionX(int containerWidth, int objectWidth) {
/* 81 */     return Math.max(0, containerWidth - objectWidth) * this.hpos / 2;
/*    */   }
/*    */   
/*    */   public int computePositionY(int containerHeight, int objectHeight) {
/* 85 */     return Math.max(0, containerHeight - objectHeight) * this.vpos / 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Alignment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */