/*     */ package de.matthiasmann.twl.textarea;
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
/*     */ public class StyleSheetKey
/*     */ {
/*     */   final String element;
/*     */   final String className;
/*     */   final String id;
/*     */   
/*     */   public StyleSheetKey(String element, String className, String id) {
/*  43 */     this.element = element;
/*  44 */     this.className = className;
/*  45 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getClassName() {
/*  49 */     return this.className;
/*     */   }
/*     */   
/*     */   public String getElement() {
/*  53 */     return this.element;
/*     */   }
/*     */   
/*     */   public String getId() {
/*  57 */     return this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  62 */     if (obj instanceof StyleSheetKey) {
/*  63 */       StyleSheetKey other = (StyleSheetKey)obj;
/*  64 */       return (((this.element == null) ? (other.element == null) : this.element.equals(other.element)) && ((this.className == null) ? (other.className == null) : this.className.equals(other.className)) && ((this.id == null) ? (other.id == null) : this.id.equals(other.id)));
/*     */     } 
/*     */ 
/*     */     
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  73 */     int hash = 7;
/*  74 */     hash = 53 * hash + ((this.element != null) ? this.element.hashCode() : 0);
/*  75 */     hash = 53 * hash + ((this.className != null) ? this.className.hashCode() : 0);
/*  76 */     hash = 53 * hash + ((this.id != null) ? this.id.hashCode() : 0);
/*  77 */     return hash;
/*     */   }
/*     */   
/*     */   public boolean matches(StyleSheetKey what) {
/*  81 */     if (this.element != null && !this.element.equals(what.element)) {
/*  82 */       return false;
/*     */     }
/*  84 */     if (this.className != null && !this.className.equals(what.className)) {
/*  85 */       return false;
/*     */     }
/*  87 */     if (this.id != null && !this.id.equals(what.id)) {
/*  88 */       return false;
/*     */     }
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  95 */     StringBuilder sb = (new StringBuilder()).append(this.element);
/*  96 */     if (this.className != null) {
/*  97 */       sb.append('.').append(this.className);
/*     */     }
/*  99 */     if (this.id != null) {
/* 100 */       sb.append('#').append(this.id);
/*     */     }
/* 102 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\textarea\StyleSheetKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */