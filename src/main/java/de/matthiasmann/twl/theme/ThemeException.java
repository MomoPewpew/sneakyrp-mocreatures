/*     */ package de.matthiasmann.twl.theme;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
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
/*     */ public class ThemeException
/*     */   extends IOException
/*     */ {
/*     */   protected final Source source;
/*     */   
/*     */   public ThemeException(String msg, URL url, int lineNumber, int columnNumber, Throwable cause) {
/*  45 */     super(msg);
/*  46 */     this.source = new Source(url, lineNumber, columnNumber);
/*  47 */     initCause(cause);
/*     */   }
/*     */   
/*     */   void addIncludedBy(URL url, int lineNumber, int columnNumber) {
/*  51 */     Source head = this.source;
/*  52 */     while (head.includedBy != null) {
/*  53 */       head = head.includedBy;
/*     */     }
/*  55 */     head.includedBy = new Source(url, lineNumber, columnNumber);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*  60 */     StringBuilder sb = new StringBuilder(super.getMessage());
/*  61 */     String prefix = "\n           in ";
/*  62 */     for (Source src = this.source; src != null; src = src.includedBy) {
/*  63 */       sb.append(prefix).append(src.url).append(" @").append(src.lineNumber).append(':').append(src.columnNumber);
/*     */ 
/*     */       
/*  66 */       prefix = "\n  included by ";
/*     */     } 
/*  68 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Source getSource() {
/*  77 */     return this.source;
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class Source
/*     */   {
/*     */     protected final URL url;
/*     */     
/*     */     protected final int lineNumber;
/*     */     protected final int columnNumber;
/*     */     protected Source includedBy;
/*     */     
/*     */     Source(URL url, int lineNumber, int columnNumber) {
/*  90 */       this.url = url;
/*  91 */       this.lineNumber = lineNumber;
/*  92 */       this.columnNumber = columnNumber;
/*     */     }
/*     */     
/*     */     public URL getUrl() {
/*  96 */       return this.url;
/*     */     }
/*     */     
/*     */     public int getLineNumber() {
/* 100 */       return this.lineNumber;
/*     */     }
/*     */     
/*     */     public int getColumnNumber() {
/* 104 */       return this.columnNumber;
/*     */     }
/*     */     
/*     */     public Source getIncludedBy() {
/* 108 */       return this.includedBy;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\ThemeException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */