/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import java.text.ParseException;
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
/*     */ public class SimpleMathParser
/*     */ {
/*     */   final String str;
/*     */   final Interpreter interpreter;
/*     */   int pos;
/*     */   
/*     */   private SimpleMathParser(String str, Interpreter interpreter) {
/*  58 */     this.str = str;
/*  59 */     this.interpreter = interpreter;
/*     */   }
/*     */   
/*     */   public static void interpret(String str, Interpreter interpreter) throws ParseException {
/*  63 */     (new SimpleMathParser(str, interpreter)).parse(false);
/*     */   }
/*     */   
/*     */   public static int interpretArray(String str, Interpreter interpreter) throws ParseException {
/*  67 */     return (new SimpleMathParser(str, interpreter)).parse(true);
/*     */   }
/*     */   
/*     */   private int parse(boolean allowArray) throws ParseException {
/*     */     try {
/*  72 */       if (peek() == -1) {
/*  73 */         if (allowArray) {
/*  74 */           return 0;
/*     */         }
/*  76 */         unexpected(-1);
/*     */       } 
/*  78 */       int count = 0;
/*     */       while (true) {
/*  80 */         count++;
/*  81 */         parseAddSub();
/*  82 */         int ch = peek();
/*  83 */         if (ch == -1) {
/*  84 */           return count;
/*     */         }
/*  86 */         if (ch != 44 || !allowArray) {
/*  87 */           unexpected(ch);
/*     */         }
/*  89 */         this.pos++;
/*     */       } 
/*  91 */     } catch (ParseException ex) {
/*  92 */       throw ex;
/*  93 */     } catch (Exception ex) {
/*  94 */       throw (ParseException)(new ParseException("Unable to execute", this.pos)).initCause(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void parseAddSub() throws ParseException {
/*  99 */     parseMulDiv();
/*     */     while (true) {
/* 101 */       int ch = peek();
/* 102 */       switch (ch) {
/*     */         case 43:
/* 104 */           this.pos++;
/* 105 */           parseMulDiv();
/* 106 */           this.interpreter.add();
/*     */           continue;
/*     */         case 45:
/* 109 */           this.pos++;
/* 110 */           parseMulDiv();
/* 111 */           this.interpreter.sub();
/*     */           continue;
/*     */       } 
/*     */       break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseMulDiv() throws ParseException {
/* 120 */     parseIdentOrConst();
/*     */     while (true) {
/* 122 */       int ch = peek();
/* 123 */       switch (ch) {
/*     */         case 42:
/* 125 */           this.pos++;
/* 126 */           parseIdentOrConst();
/* 127 */           this.interpreter.mul();
/*     */           continue;
/*     */         case 47:
/* 130 */           this.pos++;
/* 131 */           parseIdentOrConst();
/* 132 */           this.interpreter.div();
/*     */           continue;
/*     */       } 
/*     */       break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void parseIdentOrConst() throws ParseException {
/* 141 */     int ch = peek();
/* 142 */     if (Character.isJavaIdentifierStart((char)ch)) {
/* 143 */       String ident = parseIdent();
/* 144 */       ch = peek();
/* 145 */       if (ch == 40) {
/* 146 */         this.pos++;
/* 147 */         parseCall(ident);
/*     */         return;
/*     */       } 
/* 150 */       this.interpreter.accessVariable(ident);
/* 151 */       while (ch == 46 || ch == 91) {
/* 152 */         this.pos++;
/* 153 */         if (ch == 46) {
/* 154 */           String field = parseIdent();
/* 155 */           this.interpreter.accessField(field);
/*     */         } else {
/* 157 */           parseIdentOrConst();
/* 158 */           expect(93);
/* 159 */           this.interpreter.accessArray();
/*     */         } 
/* 161 */         ch = peek();
/*     */       } 
/* 163 */     } else if (ch == 45) {
/* 164 */       this.pos++;
/* 165 */       parseIdentOrConst();
/* 166 */       this.interpreter.negate();
/* 167 */     } else if (ch == 46 || ch == 43 || Character.isDigit((char)ch)) {
/* 168 */       parseConst();
/* 169 */     } else if (ch == 40) {
/* 170 */       this.pos++;
/* 171 */       parseAddSub();
/* 172 */       expect(41);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void parseCall(String name) throws ParseException {
/* 177 */     int count = 1;
/* 178 */     parseAddSub();
/*     */     while (true) {
/* 180 */       int ch = peek();
/* 181 */       if (ch == 41) {
/* 182 */         this.pos++;
/* 183 */         this.interpreter.callFunction(name, count);
/*     */         return;
/*     */       } 
/* 186 */       if (ch == 44) {
/* 187 */         this.pos++;
/* 188 */         count++;
/* 189 */         parseAddSub(); continue;
/*     */       } 
/* 191 */       unexpected(ch);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void parseConst() throws ParseException {
/*     */     Number n;
/* 197 */     int len = this.str.length();
/* 198 */     int start = this.pos;
/*     */     
/* 200 */     switch (this.str.charAt(this.pos)) {
/*     */       
/*     */       case '+':
/* 203 */         start = ++this.pos;
/*     */         break;
/*     */       case '0':
/* 206 */         if (this.pos + 1 < len && this.str.charAt(this.pos + 1) == 'x') {
/* 207 */           this.pos += 2;
/* 208 */           parseHexInt();
/*     */           return;
/*     */         } 
/*     */         break;
/*     */     } 
/* 213 */     while (this.pos < len && Character.isDigit(this.str.charAt(this.pos))) {
/* 214 */       this.pos++;
/*     */     }
/* 216 */     if (this.pos < len && this.str.charAt(this.pos) == '.') {
/* 217 */       this.pos++;
/* 218 */       while (this.pos < len && Character.isDigit(this.str.charAt(this.pos))) {
/* 219 */         this.pos++;
/*     */       }
/* 221 */       if (this.pos - start <= 1) {
/* 222 */         unexpected(-1);
/*     */       }
/* 224 */       n = Float.valueOf(this.str.substring(start, this.pos));
/*     */     } else {
/* 226 */       n = Integer.valueOf(this.str.substring(start, this.pos));
/*     */     } 
/* 228 */     this.interpreter.loadConst(n);
/*     */   }
/*     */   
/*     */   private void parseHexInt() throws ParseException {
/* 232 */     int len = this.str.length();
/* 233 */     int start = this.pos;
/* 234 */     while (this.pos < len && "0123456789abcdefABCDEF".indexOf(this.str.charAt(this.pos)) >= 0) {
/* 235 */       this.pos++;
/*     */     }
/* 237 */     if (this.pos - start > 8) {
/* 238 */       throw new ParseException("Number to large at " + this.pos, this.pos);
/*     */     }
/* 240 */     if (this.pos == start) {
/* 241 */       unexpected((this.pos < len) ? this.str.charAt(this.pos) : -1);
/*     */     }
/* 243 */     this.interpreter.loadConst(Integer.valueOf((int)Long.parseLong(this.str.substring(start, this.pos), 16)));
/*     */   }
/*     */   
/*     */   private boolean skipSpaces() {
/*     */     while (true) {
/* 248 */       if (this.pos == this.str.length()) {
/* 249 */         return false;
/*     */       }
/* 251 */       if (!Character.isWhitespace(this.str.charAt(this.pos))) {
/* 252 */         return true;
/*     */       }
/* 254 */       this.pos++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private int peek() {
/* 259 */     if (skipSpaces()) {
/* 260 */       return this.str.charAt(this.pos);
/*     */     }
/* 262 */     return -1;
/*     */   }
/*     */   
/*     */   private String parseIdent() {
/* 266 */     int start = this.pos;
/* 267 */     while (this.pos < this.str.length() && Character.isJavaIdentifierPart(this.str.charAt(this.pos))) {
/* 268 */       this.pos++;
/*     */     }
/* 270 */     return this.str.substring(start, this.pos);
/*     */   }
/*     */   
/*     */   private void expect(int what) throws ParseException {
/* 274 */     int ch = peek();
/* 275 */     if (ch != what) {
/* 276 */       unexpected(ch);
/*     */     } else {
/* 278 */       this.pos++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void unexpected(int ch) throws ParseException {
/* 283 */     if (ch < 0) {
/* 284 */       throw new ParseException("Unexpected end of string", this.pos);
/*     */     }
/* 286 */     throw new ParseException("Unexpected character '" + (char)ch + "' at " + this.pos, this.pos);
/*     */   }
/*     */   
/*     */   public static interface Interpreter {
/*     */     void accessVariable(String param1String);
/*     */     
/*     */     void accessField(String param1String);
/*     */     
/*     */     void accessArray();
/*     */     
/*     */     void loadConst(Number param1Number);
/*     */     
/*     */     void add();
/*     */     
/*     */     void sub();
/*     */     
/*     */     void mul();
/*     */     
/*     */     void div();
/*     */     
/*     */     void callFunction(String param1String, int param1Int);
/*     */     
/*     */     void negate();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\SimpleMathParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */