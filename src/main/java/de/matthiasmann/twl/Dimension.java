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
/*    */ public class Dimension
/*    */ {
/* 38 */   public static final Dimension ZERO = new Dimension(0, 0);
/*    */   
/*    */   private final int x;
/*    */   private final int y;
/*    */   
/*    */   public Dimension(int x, int y) {
/* 44 */     this.x = x;
/* 45 */     this.y = y;
/*    */   }
/*    */   
/*    */   public int getX() {
/* 49 */     return this.x;
/*    */   }
/*    */   
/*    */   public int getY() {
/* 53 */     return this.y;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 58 */     if (obj == null || getClass() != obj.getClass()) {
/* 59 */       return false;
/*    */     }
/* 61 */     Dimension other = (Dimension)obj;
/* 62 */     return (this.x == other.x && this.y == other.y);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 67 */     int hash = 3;
/* 68 */     hash = 71 * hash + this.x;
/* 69 */     hash = 71 * hash + this.y;
/* 70 */     return hash;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 75 */     return "Dimension[x=" + this.x + ", y=" + this.y + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Dimension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */