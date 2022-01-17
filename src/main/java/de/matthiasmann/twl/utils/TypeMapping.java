/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TypeMapping<V>
/*     */ {
/*  50 */   Entry<V>[] table = (Entry<V>[])new Entry[16];
/*     */   int size;
/*     */   
/*     */   public void put(Class<?> clazz, V value) {
/*  54 */     if (value == null) {
/*  55 */       throw new NullPointerException("value");
/*     */     }
/*  57 */     removeCached();
/*  58 */     Entry<V> entry = HashEntry.get(this.table, clazz);
/*  59 */     if (entry != null) {
/*  60 */       HashEntry.remove(this.table, entry);
/*  61 */       this.size--;
/*     */     } 
/*  63 */     insert(new Entry<V>(clazz, value, false));
/*     */   }
/*     */   
/*     */   public V get(Class<?> clazz) {
/*  67 */     Entry<V> entry = HashEntry.get(this.table, clazz);
/*  68 */     if (entry != null) {
/*  69 */       return entry.value;
/*     */     }
/*  71 */     return slowGet(clazz);
/*     */   }
/*     */   
/*     */   public boolean remove(Class<?> clazz) {
/*  75 */     if (HashEntry.remove(this.table, clazz) != null) {
/*  76 */       removeCached();
/*  77 */       this.size--;
/*  78 */       return true;
/*     */     } 
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   public Set<V> getUniqueValues() {
/*  84 */     HashSet<V> result = new HashSet<V>();
/*  85 */     for (Entry<V> e : this.table) {
/*  86 */       while (e != null) {
/*  87 */         if (!e.isCache) {
/*  88 */           result.add(e.value);
/*     */         }
/*  90 */         e = e.next();
/*     */       } 
/*     */     } 
/*  93 */     return result;
/*     */   }
/*     */   
/*     */   public Map<Class<?>, V> getEntries() {
/*  97 */     HashMap<Class<?>, V> result = new HashMap<Class<?>, V>();
/*  98 */     for (Entry<V> e : this.table) {
/*  99 */       while (e != null) {
/* 100 */         if (!e.isCache) {
/* 101 */           result.put(e.key, e.value);
/*     */         }
/* 103 */         e = e.next();
/*     */       } 
/*     */     } 
/* 106 */     return result;
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
/*     */   private V slowGet(Class<?> clazz) {
/*     */     // Byte code:
/*     */     //   0: aconst_null
/*     */     //   1: astore_2
/*     */     //   2: aload_1
/*     */     //   3: astore_3
/*     */     //   4: aload_3
/*     */     //   5: invokevirtual getInterfaces : ()[Ljava/lang/Class;
/*     */     //   8: astore #4
/*     */     //   10: aload #4
/*     */     //   12: arraylength
/*     */     //   13: istore #5
/*     */     //   15: iconst_0
/*     */     //   16: istore #6
/*     */     //   18: iload #6
/*     */     //   20: iload #5
/*     */     //   22: if_icmpge -> 58
/*     */     //   25: aload #4
/*     */     //   27: iload #6
/*     */     //   29: aaload
/*     */     //   30: astore #7
/*     */     //   32: aload_0
/*     */     //   33: getfield table : [Lde/matthiasmann/twl/utils/TypeMapping$Entry;
/*     */     //   36: aload #7
/*     */     //   38: invokestatic get : ([Lde/matthiasmann/twl/utils/HashEntry;Ljava/lang/Object;)Lde/matthiasmann/twl/utils/HashEntry;
/*     */     //   41: checkcast de/matthiasmann/twl/utils/TypeMapping$Entry
/*     */     //   44: astore_2
/*     */     //   45: aload_2
/*     */     //   46: ifnull -> 52
/*     */     //   49: goto -> 86
/*     */     //   52: iinc #6, 1
/*     */     //   55: goto -> 18
/*     */     //   58: aload_3
/*     */     //   59: invokevirtual getSuperclass : ()Ljava/lang/Class;
/*     */     //   62: astore_3
/*     */     //   63: aload_3
/*     */     //   64: ifnonnull -> 70
/*     */     //   67: goto -> 86
/*     */     //   70: aload_0
/*     */     //   71: getfield table : [Lde/matthiasmann/twl/utils/TypeMapping$Entry;
/*     */     //   74: aload_3
/*     */     //   75: invokestatic get : ([Lde/matthiasmann/twl/utils/HashEntry;Ljava/lang/Object;)Lde/matthiasmann/twl/utils/HashEntry;
/*     */     //   78: checkcast de/matthiasmann/twl/utils/TypeMapping$Entry
/*     */     //   81: astore_2
/*     */     //   82: aload_2
/*     */     //   83: ifnull -> 4
/*     */     //   86: aload_2
/*     */     //   87: ifnull -> 97
/*     */     //   90: aload_2
/*     */     //   91: getfield value : Ljava/lang/Object;
/*     */     //   94: goto -> 98
/*     */     //   97: aconst_null
/*     */     //   98: astore #4
/*     */     //   100: aload_0
/*     */     //   101: new de/matthiasmann/twl/utils/TypeMapping$Entry
/*     */     //   104: dup
/*     */     //   105: aload_1
/*     */     //   106: aload #4
/*     */     //   108: iconst_1
/*     */     //   109: invokespecial <init> : (Ljava/lang/Class;Ljava/lang/Object;Z)V
/*     */     //   112: invokespecial insert : (Lde/matthiasmann/twl/utils/TypeMapping$Entry;)V
/*     */     //   115: aload #4
/*     */     //   117: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #118	-> 0
/*     */     //   #119	-> 2
/*     */     //   #121	-> 4
/*     */     //   #122	-> 32
/*     */     //   #123	-> 45
/*     */     //   #124	-> 49
/*     */     //   #121	-> 52
/*     */     //   #128	-> 58
/*     */     //   #129	-> 63
/*     */     //   #130	-> 67
/*     */     //   #133	-> 70
/*     */     //   #134	-> 82
/*     */     //   #136	-> 86
/*     */     //   #137	-> 100
/*     */     //   #139	-> 115
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   32	20	7	ifClass	Ljava/lang/Class;
/*     */     //   10	48	4	arr$	[Ljava/lang/Class;
/*     */     //   15	43	5	len$	I
/*     */     //   18	40	6	i$	I
/*     */     //   0	118	0	this	Lde/matthiasmann/twl/utils/TypeMapping;
/*     */     //   0	118	1	clazz	Ljava/lang/Class;
/*     */     //   2	116	2	entry	Lde/matthiasmann/twl/utils/TypeMapping$Entry;
/*     */     //   4	114	3	baseClass	Ljava/lang/Class;
/*     */     //   100	18	4	value	Ljava/lang/Object;
/*     */     // Local variable type table:
/*     */     //   start	length	slot	name	signature
/*     */     //   32	20	7	ifClass	Ljava/lang/Class<*>;
/*     */     //   0	118	0	this	Lde/matthiasmann/twl/utils/TypeMapping<TV;>;
/*     */     //   0	118	1	clazz	Ljava/lang/Class<*>;
/*     */     //   2	116	2	entry	Lde/matthiasmann/twl/utils/TypeMapping$Entry<TV;>;
/*     */     //   4	114	3	baseClass	Ljava/lang/Class<*>;
/*     */     //   100	18	4	value	TV;
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
/*     */   private void insert(Entry<V> newEntry) {
/* 143 */     this.table = HashEntry.maybeResizeTable(this.table, this.size);
/* 144 */     HashEntry.insertEntry(this.table, newEntry);
/* 145 */     this.size++;
/*     */   }
/*     */   
/*     */   private void removeCached() {
/* 149 */     for (Entry<V> e : this.table) {
/* 150 */       while (e != null) {
/* 151 */         Entry<V> n = e.next();
/* 152 */         if (e.isCache) {
/* 153 */           HashEntry.remove(this.table, e);
/* 154 */           this.size--;
/*     */         } 
/* 156 */         e = n;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static class Entry<V> extends HashEntry<Class<?>, Entry<V>> {
/*     */     final V value;
/*     */     final boolean isCache;
/*     */     
/*     */     public Entry(Class<?> key, V value, boolean isCache) {
/* 166 */       super(key);
/* 167 */       this.value = value;
/* 168 */       this.isCache = isCache;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\TypeMapping.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */