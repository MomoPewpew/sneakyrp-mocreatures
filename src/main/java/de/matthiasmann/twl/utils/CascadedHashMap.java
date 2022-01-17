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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CascadedHashMap<K, V>
/*     */ {
/*     */   private Entry<K, V>[] table;
/*     */   private int size;
/*     */   private CascadedHashMap<K, V> fallback;
/*     */   
/*     */   public V get(K key) {
/*  56 */     Entry<K, V> entry = getEntry(this, key);
/*  57 */     if (entry != null) {
/*  58 */       return entry.value;
/*     */     }
/*  60 */     return null;
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
/*     */   public V put(K key, V value) {
/*  72 */     if (key == null) {
/*  73 */       throw new NullPointerException("key");
/*     */     }
/*     */     
/*  76 */     V oldValue = null;
/*  77 */     if (this.table != null) {
/*  78 */       Entry<K, V> entry = HashEntry.get(this.table, key);
/*  79 */       if (entry != null) {
/*  80 */         oldValue = entry.value;
/*  81 */         entry.value = value;
/*  82 */         return oldValue;
/*     */       } 
/*  84 */       if (this.fallback != null) {
/*  85 */         oldValue = this.fallback.get(key);
/*     */       }
/*     */     } 
/*     */     
/*  89 */     insertEntry(key, value);
/*  90 */     return oldValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void collapseAndSetFallback(CascadedHashMap<K, V> map) {
/* 100 */     if (this.fallback != null) {
/* 101 */       collapsePutAll(this.fallback);
/* 102 */       this.fallback = null;
/*     */     } 
/* 104 */     this.fallback = map;
/*     */   }
/*     */   
/*     */   protected static <K, V> Entry<K, V> getEntry(CascadedHashMap<K, V> map, K key) {
/*     */     while (true) {
/* 109 */       if (map.table != null) {
/* 110 */         Entry<K, V> entry = HashEntry.get(map.table, key);
/* 111 */         if (entry != null) {
/* 112 */           return entry;
/*     */         }
/*     */       } 
/* 115 */       map = map.fallback;
/* 116 */       if (map == null)
/* 117 */         return null; 
/*     */     } 
/*     */   }
/*     */   private void collapsePutAll(CascadedHashMap<K, V> map) {
/*     */     do {
/* 122 */       Entry<K, V>[] tab = map.table;
/* 123 */       if (tab != null) {
/* 124 */         for (int i = 0, n = tab.length; i < n; i++) {
/* 125 */           Entry<K, V> e = tab[i];
/* 126 */           while (e != null) {
/* 127 */             if (HashEntry.get(this.table, e.key) == null) {
/* 128 */               insertEntry(e.key, e.value);
/*     */             }
/* 130 */             e = e.next;
/*     */           } 
/*     */         } 
/*     */       }
/* 134 */       map = map.fallback;
/* 135 */     } while (map != null);
/*     */   }
/*     */ 
/*     */   
/*     */   private void insertEntry(K key, V value) {
/* 140 */     if (this.table == null) {
/* 141 */       this.table = (Entry<K, V>[])new Entry[16];
/*     */     }
/* 143 */     this.table = HashEntry.maybeResizeTable(this.table, ++this.size);
/* 144 */     Entry<K, V> entry = new Entry<K, V>(key, value);
/* 145 */     HashEntry.insertEntry(this.table, entry);
/*     */   }
/*     */   
/*     */   protected static class Entry<K, V> extends HashEntry<K, Entry<K, V>> {
/*     */     V value;
/*     */     
/*     */     public Entry(K key, V value) {
/* 152 */       super(key);
/* 153 */       this.value = value;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\CascadedHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */