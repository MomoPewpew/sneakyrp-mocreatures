/*    */ package de.matthiasmann.twl.utils;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.Reader;
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
/*    */ public class MultiStringReader
/*    */   extends Reader
/*    */ {
/*    */   private final String[] strings;
/*    */   private String cur;
/*    */   private int nr;
/*    */   private int pos;
/*    */   
/*    */   public MultiStringReader(String... strings) {
/* 47 */     this.strings = strings;
/*    */   }
/*    */ 
/*    */   
/*    */   public int read(char[] cbuf, int off, int len) throws IOException {
/* 52 */     while (this.cur == null || this.pos == this.cur.length()) {
/* 53 */       if (this.nr == this.strings.length) {
/* 54 */         return -1;
/*    */       }
/* 56 */       this.cur = this.strings[this.nr++];
/* 57 */       this.pos = 0;
/*    */     } 
/*    */     
/* 60 */     int remain = this.cur.length() - this.pos;
/* 61 */     if (len > remain) {
/* 62 */       len = remain;
/*    */     }
/* 64 */     this.cur.getChars(this.pos, this.pos += len, cbuf, off);
/* 65 */     return len;
/*    */   }
/*    */   
/*    */   public void close() {}
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\MultiStringReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */