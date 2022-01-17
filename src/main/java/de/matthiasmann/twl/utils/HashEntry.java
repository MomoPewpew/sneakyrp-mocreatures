/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HashEntry<K, T extends HashEntry<K, T>>
/*     */ {
/*     */   public final K key;
/*     */   final int hash;
/*     */   T next;
/*     */   
/*     */   public HashEntry(K key) {
/*  48 */     this.key = key;
/*  49 */     this.hash = key.hashCode();
/*     */   }
/*     */   
/*     */   public T next() {
/*  53 */     return this.next;
/*     */   }
/*     */   
/*     */   public static <K, T extends HashEntry<K, T>> T get(T[] table, Object key) {
/*  57 */     int hash = key.hashCode();
/*  58 */     T e = table[hash & table.length - 1];
/*     */     Object k;
/*  60 */     while (e != null && (((HashEntry)e).hash != hash || ((k = ((HashEntry)e).key) != key && !key.equals(k)))) {
/*  61 */       e = ((HashEntry)e).next;
/*     */     }
/*  63 */     return e;
/*     */   }
/*     */   
/*     */   public static <K, T extends HashEntry<K, T>> void insertEntry(T[] table, T newEntry) {
/*  67 */     int idx = ((HashEntry)newEntry).hash & table.length - 1;
/*  68 */     ((HashEntry)newEntry).next = table[idx];
/*  69 */     table[idx] = newEntry;
/*     */   }
/*     */   
/*     */   public static <K, T extends HashEntry<K, T>> T remove(T[] table, Object key) {
/*  73 */     int hash = key.hashCode();
/*  74 */     int idx = hash & table.length - 1;
/*  75 */     T e = table[idx];
/*  76 */     T p = null;
/*     */     Object k;
/*  78 */     while (e != null && (((HashEntry)e).hash != hash || ((k = ((HashEntry)e).key) != key && !key.equals(k)))) {
/*  79 */       p = e;
/*  80 */       e = ((HashEntry)e).next;
/*     */     } 
/*  82 */     if (e != null) {
/*  83 */       if (p != null) {
/*  84 */         ((HashEntry)p).next = ((HashEntry)e).next;
/*     */       } else {
/*  86 */         table[idx] = ((HashEntry)e).next;
/*     */       } 
/*     */     }
/*  89 */     return e;
/*     */   }
/*     */   
/*     */   public static <K, T extends HashEntry<K, T>> void remove(T[] table, T entry) {
/*  93 */     int idx = ((HashEntry)entry).hash & table.length - 1;
/*  94 */     T e = table[idx];
/*  95 */     if (e == entry) {
/*  96 */       table[idx] = ((HashEntry)e).next;
/*     */     } else {
/*     */       
/*     */       while (true) {
/* 100 */         T p = e;
/* 101 */         e = ((HashEntry)e).next;
/* 102 */         if (e == entry) {
/* 103 */           ((HashEntry)p).next = ((HashEntry)e).next;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }  } public static <K, T extends HashEntry<K, T>> T[] maybeResizeTable(T[] table, int usedCount) {
/* 108 */     if (usedCount * 4 > table.length * 3) {
/* 109 */       table = resizeTable(table, table.length * 2);
/*     */     }
/* 111 */     return table;
/*     */   }
/*     */   
/*     */   private static <K, T extends HashEntry<K, T>> T[] resizeTable(T[] table, int newSize) {
/* 115 */     if (newSize < 4 || (newSize & newSize - 1) != 0) {
/* 116 */       throw new IllegalArgumentException("newSize");
/*     */     }
/*     */     
/* 119 */     HashEntry[] arrayOfHashEntry = (HashEntry[])Array.newInstance(table.getClass().getComponentType(), newSize);
/* 120 */     for (int i = 0, n = table.length; i < n; i++) {
/* 121 */       for (T e = table[i]; e != null; ) {
/* 122 */         T ne = ((HashEntry)e).next;
/* 123 */         int ni = ((HashEntry)e).hash & newSize - 1;
/* 124 */         ((HashEntry)e).next = (T)arrayOfHashEntry[ni];
/* 125 */         arrayOfHashEntry[ni] = (HashEntry)e;
/* 126 */         e = ne;
/*     */       } 
/*     */     } 
/* 129 */     return (T[])arrayOfHashEntry;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\HashEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */