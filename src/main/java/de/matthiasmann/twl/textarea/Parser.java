/*     */ package de.matthiasmann.twl.textarea;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class Parser
/*     */ {
/*     */   public static final int YYEOF = -1;
/*     */   private static final int ZZ_BUFFERSIZE = 16384;
/*     */   public static final int YYSTRING1 = 6;
/*     */   public static final int YYINITIAL = 0;
/*     */   public static final int YYSTYLE = 2;
/*     */   public static final int YYVALUE = 4;
/*     */   public static final int YYSTRING2 = 8;
/*     */   private static final String ZZ_CMAP_PACKED = "\t\000\001\003\001\002\001\000\001\003\001\001\022\000\001\003\001\000\001\023\001\f\003\000\001\022\002\000\001\005\001\000\001\n\001\006\001\t\001\004\n\b\001\r\001\021\002\000\001\013\001\000\001\016\032\007\004\000\001\007\001\000\032\007\001\017\001\000\001\020ﾂ\000";
/*  78 */   private static final char[] ZZ_CMAP = zzUnpackCMap("\t\000\001\003\001\002\001\000\001\003\001\001\022\000\001\003\001\000\001\023\001\f\003\000\001\022\002\000\001\005\001\000\001\n\001\006\001\t\001\004\n\b\001\r\001\021\002\000\001\013\001\000\001\016\032\007\004\000\001\007\001\000\032\007\001\017\001\000\001\020ﾂ\000");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   private static final int[] ZZ_ACTION = zzUnpackAction();
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String ZZ_ACTION_PACKED_0 = "\005\000\001\001\001\002\001\001\001\003\001\001\001\004\001\005\001\006\001\007\001\b\001\t\001\n\001\013\002\f\001\001\001\r\001\016\001\017\001\020\001\021\001\022\001\023\001\020\001\024\001\020\001\025\004\000";
/*     */ 
/*     */ 
/*     */   
/*     */   private static int[] zzUnpackAction() {
/*  92 */     int[] result = new int[36];
/*  93 */     zzUnpackAction("\005\000\001\001\001\002\001\001\001\003\001\001\001\004\001\005\001\006\001\007\001\b\001\t\001\n\001\013\002\f\001\001\001\r\001\016\001\017\001\020\001\021\001\022\001\023\001\020\001\024\001\020\001\025\004\000", 0, result);
/*  94 */     return result;
/*     */   }
/*     */   
/*     */   private static int zzUnpackAction(String packed, int offset, int[] result) {
/*  98 */     int i = 0;
/*  99 */     int j = offset;
/* 100 */     int l = packed.length();
/* 101 */     label10: while (i < l) {
/* 102 */       int count = packed.charAt(i++);
/* 103 */       int value = packed.charAt(i++); while (true)
/* 104 */       { result[j++] = value; if (--count <= 0)
/*     */           continue label10;  } 
/* 106 */     }  return j;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   private static final int[] ZZ_ROWMAP = zzUnpackRowMap();
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String ZZ_ROWMAP_PACKED_0 = "\000\000\000\024\000(\000<\000P\000d\000x\000\000d\000 \000´\000d\000d\000d\000d\000d\000d\000d\000È\000d\000Ü\000ð\000d\000d\000Ą\000d\000d\000d\000Ę\000d\000Ĭ\000d\000ŀ\000Ŕ\000Ũ\000ż";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int[] zzUnpackRowMap() {
/* 123 */     int[] result = new int[36];
/* 124 */     zzUnpackRowMap("\000\000\000\024\000(\000<\000P\000d\000x\000\000d\000 \000´\000d\000d\000d\000d\000d\000d\000d\000È\000d\000Ü\000ð\000d\000d\000Ą\000d\000d\000d\000Ę\000d\000Ĭ\000d\000ŀ\000Ŕ\000Ũ\000ż", 0, result);
/* 125 */     return result;
/*     */   }
/*     */   
/*     */   private static int zzUnpackRowMap(String packed, int offset, int[] result) {
/* 129 */     int i = 0;
/* 130 */     int j = offset;
/* 131 */     int l = packed.length();
/* 132 */     while (i < l) {
/* 133 */       int high = packed.charAt(i++) << 16;
/* 134 */       result[j++] = high | packed.charAt(i++);
/*     */     } 
/* 136 */     return j;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   private static final int[] ZZ_TRANS = zzUnpackTrans();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String ZZ_TRANS_PACKED_0 = "\001\006\003\007\001\b\001\t\001\n\001\013\001\006\001\f\001\r\001\016\001\017\001\020\001\021\001\022\005\006\001\023\002\024\001\b\001\006\001\025\001\026\005\006\001\027\002\006\001\030\003\006\020\031\001\030\001\032\001\033\001\034\022\035\001\036\001\035\023\037\001 \025\000\003\007\025\000\001!\025\000\001\013\022\000\003\013\r\000\001\024\030\000\001\026\022\000\003\026\013\000\020\031\004\000\022\035\001\000\001\035\023\037\001\000\005\"\001#\023\"\001$\016\"\004\000\001\024\001#\016\000\004\"\001\024\001$\016\"";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int[] zzUnpackTrans() {
/* 156 */     int[] result = new int[400];
/* 157 */     zzUnpackTrans("\001\006\003\007\001\b\001\t\001\n\001\013\001\006\001\f\001\r\001\016\001\017\001\020\001\021\001\022\005\006\001\023\002\024\001\b\001\006\001\025\001\026\005\006\001\027\002\006\001\030\003\006\020\031\001\030\001\032\001\033\001\034\022\035\001\036\001\035\023\037\001 \025\000\003\007\025\000\001!\025\000\001\013\022\000\003\013\r\000\001\024\030\000\001\026\022\000\003\026\013\000\020\031\004\000\022\035\001\000\001\035\023\037\001\000\005\"\001#\023\"\001$\016\"\004\000\001\024\001#\016\000\004\"\001\024\001$\016\"", 0, result);
/* 158 */     return result;
/*     */   }
/*     */   
/*     */   private static int zzUnpackTrans(String packed, int offset, int[] result) {
/* 162 */     int i = 0;
/* 163 */     int j = offset;
/* 164 */     int l = packed.length();
/* 165 */     label10: while (i < l) {
/* 166 */       int count = packed.charAt(i++);
/* 167 */       int value = packed.charAt(i++);
/* 168 */       value--; while (true)
/* 169 */       { result[j++] = value; if (--count <= 0)
/*     */           continue label10;  } 
/* 171 */     }  return j;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();
/*     */   
/*     */   private static final String ZZ_ATTRIBUTE_PACKED_0 = "\005\000\001\t\002\001\001\t\002\001\007\t\001\001\001\t\002\001\002\t\001\001\003\t\001\001\001\t\001\001\001\t\004\000";
/*     */   
/*     */   private Reader zzReader;
/*     */   private int zzState;
/*     */   
/*     */   private static int[] zzUnpackAttribute() {
/* 185 */     int[] result = new int[36];
/* 186 */     zzUnpackAttribute("\005\000\001\t\002\001\001\t\002\001\007\t\001\001\001\t\002\001\002\t\001\001\003\t\001\001\001\t\001\001\001\t\004\000", 0, result);
/* 187 */     return result;
/*     */   }
/*     */   
/*     */   private static int zzUnpackAttribute(String packed, int offset, int[] result) {
/* 191 */     int i = 0;
/* 192 */     int j = offset;
/* 193 */     int l = packed.length();
/* 194 */     label10: while (i < l) {
/* 195 */       int count = packed.charAt(i++);
/* 196 */       int value = packed.charAt(i++); while (true)
/* 197 */       { result[j++] = value; if (--count <= 0)
/*     */           continue label10;  } 
/* 199 */     }  return j;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   private int zzLexicalState = 0;
/*     */ 
/*     */ 
/*     */   
/* 213 */   private char[] zzBuffer = new char[16384];
/*     */ 
/*     */   
/*     */   private int zzMarkedPos;
/*     */   
/*     */   private int zzCurrentPos;
/*     */   
/*     */   private int zzStartRead;
/*     */   
/*     */   private int zzEndRead;
/*     */   
/*     */   private int yyline;
/*     */   
/*     */   private int yycolumn;
/*     */   
/*     */   private boolean zzAtEOF;
/*     */   
/*     */   static final int EOF = 0;
/*     */   
/*     */   static final int IDENT = 1;
/*     */   
/*     */   static final int STAR = 2;
/*     */   
/*     */   static final int DOT = 3;
/*     */   
/*     */   static final int HASH = 4;
/*     */   
/*     */   static final int GT = 5;
/*     */   
/*     */   static final int COMMA = 6;
/*     */   
/*     */   static final int STYLE_BEGIN = 7;
/*     */   
/*     */   static final int STYLE_END = 8;
/*     */   
/*     */   static final int COLON = 9;
/*     */   
/*     */   static final int SEMICOLON = 10;
/*     */   
/*     */   static final int ATRULE = 11;
/*     */   
/*     */   boolean sawWhitespace;
/*     */   
/* 256 */   final StringBuilder sb = new StringBuilder();
/*     */   private void append() {
/* 258 */     this.sb.append(this.zzBuffer, this.zzStartRead, this.zzMarkedPos - this.zzStartRead);
/*     */   }
/*     */   
/*     */   public void unexpected() throws IOException {
/* 262 */     throw new IOException("Unexpected \"" + yytext() + "\" at line " + this.yyline + ", column " + this.yycolumn);
/*     */   }
/*     */   
/*     */   public void expect(int token) throws IOException {
/* 266 */     if (yylex() != token) unexpected();
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Parser(Reader in) {
/* 277 */     this.zzReader = in;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static char[] zzUnpackCMap(String packed) {
/* 287 */     char[] map = new char[65536];
/* 288 */     int i = 0;
/* 289 */     int j = 0;
/* 290 */     label10: while (i < 72) {
/* 291 */       int count = packed.charAt(i++);
/* 292 */       char value = packed.charAt(i++); while (true)
/* 293 */       { map[j++] = value; if (--count <= 0)
/*     */           continue label10;  } 
/* 295 */     }  return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean zzRefill() throws IOException {
/* 309 */     if (this.zzStartRead > 0) {
/* 310 */       System.arraycopy(this.zzBuffer, this.zzStartRead, this.zzBuffer, 0, this.zzEndRead - this.zzStartRead);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 315 */       this.zzEndRead -= this.zzStartRead;
/* 316 */       this.zzCurrentPos -= this.zzStartRead;
/* 317 */       this.zzMarkedPos -= this.zzStartRead;
/* 318 */       this.zzStartRead = 0;
/*     */     } 
/*     */ 
/*     */     
/* 322 */     if (this.zzCurrentPos >= this.zzBuffer.length) {
/*     */       
/* 324 */       char[] newBuffer = new char[this.zzCurrentPos * 2];
/* 325 */       System.arraycopy(this.zzBuffer, 0, newBuffer, 0, this.zzBuffer.length);
/* 326 */       this.zzBuffer = newBuffer;
/*     */     } 
/*     */ 
/*     */     
/* 330 */     int numRead = this.zzReader.read(this.zzBuffer, this.zzEndRead, this.zzBuffer.length - this.zzEndRead);
/*     */ 
/*     */     
/* 333 */     if (numRead > 0) {
/* 334 */       this.zzEndRead += numRead;
/* 335 */       return false;
/*     */     } 
/*     */     
/* 338 */     if (numRead == 0) {
/* 339 */       int c = this.zzReader.read();
/* 340 */       if (c == -1) {
/* 341 */         return true;
/*     */       }
/* 343 */       this.zzBuffer[this.zzEndRead++] = (char)c;
/* 344 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 349 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void yybegin(int newState) {
/* 359 */     this.zzLexicalState = newState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String yytext() {
/* 367 */     return new String(this.zzBuffer, this.zzStartRead, this.zzMarkedPos - this.zzStartRead);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void zzScanError(String message) {
/* 386 */     throw new Error(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int yylex() throws IOException {
/* 404 */     int zzEndReadL = this.zzEndRead;
/* 405 */     char[] zzBufferL = this.zzBuffer;
/* 406 */     char[] zzCMapL = ZZ_CMAP;
/*     */     
/* 408 */     int[] zzTransL = ZZ_TRANS;
/* 409 */     int[] zzRowMapL = ZZ_ROWMAP;
/* 410 */     int[] zzAttrL = ZZ_ATTRIBUTE;
/*     */     
/*     */     while (true) {
/* 413 */       int zzInput, zzMarkedPosL = this.zzMarkedPos;
/*     */       
/* 415 */       boolean zzR = false; int zzCurrentPosL;
/* 416 */       for (zzCurrentPosL = this.zzStartRead; zzCurrentPosL < zzMarkedPosL; 
/* 417 */         zzCurrentPosL++) {
/* 418 */         switch (zzBufferL[zzCurrentPosL]) {
/*     */           case '\013':
/*     */           case '\f':
/*     */           case '':
/*     */           case ' ':
/*     */           case ' ':
/* 424 */             this.yyline++;
/* 425 */             this.yycolumn = 0;
/* 426 */             zzR = false;
/*     */             break;
/*     */           case '\r':
/* 429 */             this.yyline++;
/* 430 */             this.yycolumn = 0;
/* 431 */             zzR = true;
/*     */             break;
/*     */           case '\n':
/* 434 */             if (zzR) {
/* 435 */               zzR = false; break;
/*     */             } 
/* 437 */             this.yyline++;
/* 438 */             this.yycolumn = 0;
/*     */             break;
/*     */           
/*     */           default:
/* 442 */             zzR = false;
/* 443 */             this.yycolumn++;
/*     */             break;
/*     */         } 
/*     */       } 
/* 447 */       if (zzR) {
/*     */         boolean zzPeek;
/*     */         
/* 450 */         if (zzMarkedPosL < zzEndReadL) {
/* 451 */           zzPeek = (zzBufferL[zzMarkedPosL] == '\n');
/* 452 */         } else if (this.zzAtEOF) {
/* 453 */           zzPeek = false;
/*     */         } else {
/* 455 */           boolean eof = zzRefill();
/* 456 */           zzEndReadL = this.zzEndRead;
/* 457 */           zzMarkedPosL = this.zzMarkedPos;
/* 458 */           zzBufferL = this.zzBuffer;
/* 459 */           if (eof) {
/* 460 */             zzPeek = false;
/*     */           } else {
/* 462 */             zzPeek = (zzBufferL[zzMarkedPosL] == '\n');
/*     */           } 
/* 464 */         }  if (zzPeek) this.yyline--; 
/*     */       } 
/* 466 */       int zzAction = -1;
/*     */       
/* 468 */       zzCurrentPosL = this.zzCurrentPos = this.zzStartRead = zzMarkedPosL;
/*     */ 
/*     */       
/* 471 */       this.zzState = this.zzLexicalState / 2;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       while (true) {
/* 477 */         if (zzCurrentPosL < zzEndReadL)
/* 478 */         { zzInput = zzBufferL[zzCurrentPosL++]; }
/* 479 */         else { if (this.zzAtEOF) {
/* 480 */             int i = -1;
/*     */             
/*     */             break;
/*     */           } 
/*     */           
/* 485 */           this.zzCurrentPos = zzCurrentPosL;
/* 486 */           this.zzMarkedPos = zzMarkedPosL;
/* 487 */           boolean eof = zzRefill();
/*     */           
/* 489 */           zzCurrentPosL = this.zzCurrentPos;
/* 490 */           zzMarkedPosL = this.zzMarkedPos;
/* 491 */           zzBufferL = this.zzBuffer;
/* 492 */           zzEndReadL = this.zzEndRead;
/* 493 */           if (eof) {
/* 494 */             int i = -1;
/*     */             
/*     */             break;
/*     */           } 
/* 498 */           zzInput = zzBufferL[zzCurrentPosL++]; }
/*     */ 
/*     */         
/* 501 */         int zzNext = zzTransL[zzRowMapL[this.zzState] + zzCMapL[zzInput]];
/* 502 */         if (zzNext == -1)
/* 503 */           break;  this.zzState = zzNext;
/*     */         
/* 505 */         int zzAttributes = zzAttrL[this.zzState];
/* 506 */         if ((zzAttributes & 0x1) == 1) {
/* 507 */           zzAction = this.zzState;
/* 508 */           zzMarkedPosL = zzCurrentPosL;
/* 509 */           if ((zzAttributes & 0x8) == 8) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 516 */       this.zzMarkedPos = zzMarkedPosL;
/*     */       
/* 518 */       switch ((zzAction < 0) ? zzAction : ZZ_ACTION[zzAction]) {
/*     */         case 6:
/* 520 */           return 6;
/*     */         case 22:
/*     */           continue;
/*     */         case 20:
/* 524 */           yybegin(4); this.sb.append('\''); continue;
/*     */         case 23:
/*     */           continue;
/*     */         case 10:
/* 528 */           return 11;
/*     */         case 24:
/*     */           continue;
/*     */         case 3:
/* 532 */           this.sawWhitespace = false; return 2;
/*     */         case 25:
/*     */           continue;
/*     */         case 18:
/* 536 */           yybegin(6); this.sb.append('\''); continue;
/*     */         case 26:
/*     */           continue;
/*     */         case 19:
/* 540 */           yybegin(8); this.sb.append('"'); continue;
/*     */         case 27:
/*     */           continue;
/*     */         case 16:
/* 544 */           append(); continue;
/*     */         case 28:
/*     */           continue;
/*     */         case 4:
/* 548 */           this.sawWhitespace = false; return 1;
/*     */         case 29:
/*     */           continue;
/*     */         case 21:
/* 552 */           yybegin(4); this.sb.append('"'); continue;
/*     */         case 30:
/*     */           continue;
/*     */         case 9:
/* 556 */           return 9;
/*     */         case 31:
/*     */           continue;
/*     */         case 2:
/* 560 */           this.sawWhitespace = true; continue;
/*     */         case 32:
/*     */           continue;
/*     */         case 15:
/* 564 */           yybegin(0); return 8;
/*     */         case 33:
/*     */           continue;
/*     */         case 17:
/* 568 */           yybegin(2); return 10;
/*     */         case 34:
/*     */           continue;
/*     */         case 14:
/* 572 */           yybegin(4); this.sb.setLength(0); return 9;
/*     */         case 35:
/*     */           continue;
/*     */         case 7:
/* 576 */           return 5;
/*     */         case 36:
/*     */           continue;
/*     */         case 11:
/* 580 */           yybegin(2); return 7;
/*     */         case 37:
/*     */           continue;
/*     */         case 13:
/* 584 */           return 1;
/*     */         case 38:
/*     */           continue;
/*     */         case 1:
/* 588 */           unexpected(); continue;
/*     */         case 39:
/*     */           continue;
/*     */         case 5:
/* 592 */           return 3;
/*     */         case 40:
/*     */           continue;
/*     */         case 8:
/* 596 */           return 4;
/*     */         
/*     */         case 41:
/*     */         case 12:
/*     */         case 42:
/*     */           continue;
/*     */       } 
/*     */       
/* 604 */       if (zzInput == -1 && this.zzStartRead == this.zzCurrentPos) {
/* 605 */         this.zzAtEOF = true;
/*     */         
/* 607 */         return 0;
/*     */       } 
/*     */ 
/*     */       
/* 611 */       zzScanError("Error: could not match input");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\textarea\Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */