/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
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
/*     */ public final class StringList
/*     */   implements Iterable<String>
/*     */ {
/*     */   private final String value;
/*     */   private final StringList next;
/*     */   
/*     */   public StringList(String value) {
/*  52 */     this(value, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringList(String value, StringList next) {
/*  63 */     if (value == null) {
/*  64 */       throw new NullPointerException("value");
/*     */     }
/*  66 */     this.value = value;
/*  67 */     this.next = next;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringList getNext() {
/*  75 */     return this.next;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValue() {
/*  83 */     return this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  88 */     if (!(obj instanceof StringList)) {
/*  89 */       return false;
/*     */     }
/*  91 */     StringList that = (StringList)obj;
/*  92 */     return (this.value.equals(that.value) && (this.next == that.next || (this.next != null && this.next.equals(that.next))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  98 */     int hash = this.value.hashCode();
/*  99 */     if (this.next != null) {
/* 100 */       hash = 67 * hash + this.next.hashCode();
/*     */     }
/* 102 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 107 */     if (this.next == null) {
/* 108 */       return this.value;
/*     */     }
/* 110 */     StringBuilder sb = new StringBuilder();
/* 111 */     sb.append(this.value);
/* 112 */     for (StringList list = this.next; list != null; list = list.next) {
/* 113 */       sb.append(", ").append(list.value);
/*     */     }
/* 115 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<String> iterator() {
/* 120 */     return new I(this);
/*     */   }
/*     */   
/*     */   static class I implements Iterator<String> {
/*     */     private StringList list;
/*     */     
/*     */     I(StringList list) {
/* 127 */       this.list = list;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 131 */       return (this.list != null);
/*     */     }
/*     */     
/*     */     public String next() {
/* 135 */       if (this.list == null) {
/* 136 */         throw new NoSuchElementException();
/*     */       }
/* 138 */       String value = this.list.getValue();
/* 139 */       this.list = this.list.getNext();
/* 140 */       return value;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 144 */       throw new UnsupportedOperationException("Not supported");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\StringList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */