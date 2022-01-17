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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParameterStringParser
/*     */ {
/*     */   private final String str;
/*     */   private final char parameterSeparator;
/*     */   private final char keyValueSeparator;
/*     */   private boolean trim;
/*     */   private int pos;
/*     */   private String key;
/*     */   private String value;
/*     */   
/*     */   public ParameterStringParser(String str, char parameterSeparator, char keyValueSeparator) {
/*  57 */     if (str == null) {
/*  58 */       throw new NullPointerException("str");
/*     */     }
/*  60 */     if (parameterSeparator == keyValueSeparator) {
/*  61 */       throw new IllegalArgumentException("parameterSeperator == keyValueSeperator");
/*     */     }
/*  63 */     this.str = str;
/*  64 */     this.parameterSeparator = parameterSeparator;
/*  65 */     this.keyValueSeparator = keyValueSeparator;
/*     */   }
/*     */   
/*     */   public boolean isTrim() {
/*  69 */     return this.trim;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrim(boolean trim) {
/*  78 */     this.trim = trim;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean next() {
/*  86 */     while (this.pos < this.str.length()) {
/*  87 */       int kvPairEnd = TextUtil.indexOf(this.str, this.parameterSeparator, this.pos);
/*  88 */       int keyEnd = TextUtil.indexOf(this.str, this.keyValueSeparator, this.pos);
/*  89 */       if (keyEnd < kvPairEnd) {
/*  90 */         this.key = substring(this.pos, keyEnd);
/*  91 */         this.value = substring(keyEnd + 1, kvPairEnd);
/*  92 */         this.pos = kvPairEnd + 1;
/*  93 */         return true;
/*     */       } 
/*  95 */       this.pos = kvPairEnd + 1;
/*     */     } 
/*  97 */     this.key = null;
/*  98 */     this.value = null;
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getKey() {
/* 108 */     if (this.key == null) {
/* 109 */       throw new IllegalStateException("no key-value pair available");
/*     */     }
/* 111 */     return this.key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue() {
/* 120 */     if (this.value == null) {
/* 121 */       throw new IllegalStateException("no key-value pair available");
/*     */     }
/* 123 */     return this.value;
/*     */   }
/*     */   
/*     */   private String substring(int start, int end) {
/* 127 */     if (this.trim) {
/* 128 */       return TextUtil.trim(this.str, start, end);
/*     */     }
/* 130 */     return this.str.substring(start, end);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\ParameterStringParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */