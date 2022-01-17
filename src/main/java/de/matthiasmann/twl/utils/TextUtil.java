/*     */ package de.matthiasmann.twl.utils;
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
/*     */ public final class TextUtil
/*     */ {
/*     */   private static final String ROMAN_NUMBERS = "ↂMↂↁMↁMCMDCDCXCLXLXIXVIVI";
/*     */   private static final String ROMAN_VALUES = "✐⌨ᎈྠϨ΄ǴƐdZ2(\n\t\005\004\001";
/*     */   public static final int MAX_ROMAN_INTEGER = 39999;
/*     */   
/*     */   public static int countNumLines(CharSequence str) {
/*  47 */     int n = str.length();
/*  48 */     int count = 0;
/*  49 */     if (n > 0) {
/*  50 */       count++;
/*  51 */       for (int i = 0; i < n; i++) {
/*  52 */         if (str.charAt(i) == '\n') {
/*  53 */           count++;
/*     */         }
/*     */       } 
/*     */     } 
/*  57 */     return count;
/*     */   }
/*     */   
/*     */   public static String stripNewLines(String str) {
/*  61 */     int idx = str.lastIndexOf('\n');
/*  62 */     if (idx < 0)
/*     */     {
/*  64 */       return str;
/*     */     }
/*  66 */     StringBuilder sb = new StringBuilder(str);
/*     */     while (true) {
/*  68 */       if (sb.charAt(idx) == '\n') {
/*  69 */         sb.deleteCharAt(idx);
/*     */       }
/*  71 */       if (--idx < 0)
/*  72 */         return sb.toString(); 
/*     */     } 
/*     */   }
/*     */   public static String limitStringLength(String str, int length) {
/*  76 */     if (str.length() > length) {
/*  77 */       return str.substring(0, length);
/*     */     }
/*  79 */     return str;
/*     */   }
/*     */   
/*     */   public static String notNull(String str) {
/*  83 */     if (str == null) {
/*  84 */       return "";
/*     */     }
/*  86 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int indexOf(CharSequence cs, char ch, int start) {
/*  97 */     return indexOf(cs, ch, start, cs.length());
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
/*     */   public static int indexOf(CharSequence cs, char ch, int start, int end) {
/* 109 */     for (; start < end; start++) {
/* 110 */       if (cs.charAt(start) == ch) {
/* 111 */         return start;
/*     */       }
/*     */     } 
/* 114 */     return end;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int indexOf(String str, char ch, int start) {
/* 125 */     int idx = str.indexOf(ch, start);
/* 126 */     if (idx < 0) {
/* 127 */       return str.length();
/*     */     }
/* 129 */     return idx;
/*     */   }
/*     */   
/*     */   public static int skipSpaces(CharSequence s, int start) {
/* 133 */     return skipSpaces(s, start, s.length());
/*     */   }
/*     */   
/*     */   public static int skipSpaces(CharSequence s, int start, int end) {
/* 137 */     while (start < end && Character.isWhitespace(s.charAt(start))) {
/* 138 */       start++;
/*     */     }
/* 140 */     return start;
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
/*     */   public static String trim(CharSequence s, int start) {
/* 155 */     return trim(s, start, s.length());
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
/*     */   public static String trim(CharSequence s, int start, int end) {
/* 171 */     start = skipSpaces(s, start, end);
/* 172 */     while (end > start && Character.isWhitespace(s.charAt(end - 1))) {
/* 173 */       end--;
/*     */     }
/* 175 */     if (s instanceof String) {
/* 176 */       return ((String)s).substring(start, end);
/*     */     }
/* 178 */     if (s instanceof StringBuilder) {
/* 179 */       return ((StringBuilder)s).substring(start, end);
/*     */     }
/* 181 */     return s.subSequence(start, end).toString();
/*     */   }
/*     */   
/*     */   public static String createString(char ch, int len) {
/* 185 */     char[] buf = new char[len];
/* 186 */     for (int i = 0; i < len; i++) {
/* 187 */       buf[i] = ch;
/*     */     }
/* 189 */     return new String(buf);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int[] parseIntArray(String str) throws NumberFormatException {
/* 199 */     int count = countElements(str);
/* 200 */     int[] result = new int[count];
/* 201 */     for (int idx = 0, pos = 0; idx < count; idx++) {
/* 202 */       int comma = indexOf(str, ',', pos);
/* 203 */       result[idx] = Integer.parseInt(str.substring(pos, comma));
/* 204 */       pos = comma + 1;
/*     */     } 
/* 206 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int countElements(String str) {
/* 215 */     int count = 0;
/* 216 */     for (int pos = 0; pos < str.length(); ) {
/* 217 */       count++;
/* 218 */       pos = indexOf(str, ',', pos) + 1;
/*     */     } 
/* 220 */     return count;
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
/*     */   public static String toPrintableString(char ch) {
/* 232 */     if (Character.isISOControl(ch)) {
/* 233 */       return '\\' + Integer.toOctalString(ch);
/*     */     }
/* 235 */     return Character.toString(ch);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toRomanNumberString(int value) throws IllegalArgumentException {
/* 258 */     if (value < 1 || value > 39999) {
/* 259 */       throw new IllegalArgumentException();
/*     */     }
/* 261 */     StringBuilder sb = new StringBuilder();
/* 262 */     int idxValues = 0;
/* 263 */     int idxNumbers = 0;
/*     */     while (true) {
/* 265 */       int romanValue = "✐⌨ᎈྠϨ΄ǴƐdZ2(\n\t\005\004\001".charAt(idxValues);
/* 266 */       int romanNumberLen = (idxValues & 0x1) + 1;
/* 267 */       while (value >= romanValue) {
/* 268 */         sb.append("ↂMↂↁMↁMCMDCDCXCLXLXIXVIVI", idxNumbers, idxNumbers + romanNumberLen);
/* 269 */         value -= romanValue;
/*     */       } 
/* 271 */       idxNumbers += romanNumberLen;
/* 272 */       if (++idxValues >= 17) {
/* 273 */         return sb.toString();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toCharListNumber(int value, String list) throws IllegalArgumentException {
/* 284 */     if (value < 1) {
/* 285 */       throw new IllegalArgumentException("value");
/*     */     }
/* 287 */     int pos = 16;
/* 288 */     char[] tmp = new char[pos];
/*     */     while (true) {
/* 290 */       tmp[--pos] = list.charAt(--value % list.length());
/* 291 */       value /= list.length();
/* 292 */       if (value <= 0)
/* 293 */         return new String(tmp, pos, tmp.length - pos); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\TextUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */