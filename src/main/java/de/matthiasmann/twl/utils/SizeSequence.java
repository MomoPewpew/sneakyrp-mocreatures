/*     */ package de.matthiasmann.twl.utils;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SizeSequence
/*     */ {
/*     */   private static final int INITIAL_CAPACITY = 64;
/*     */   protected int[] table;
/*     */   protected int size;
/*     */   protected int defaultValue;
/*     */   
/*     */   public SizeSequence() {
/*  47 */     this(64);
/*     */   }
/*     */   
/*     */   public SizeSequence(int initialCapacity) {
/*  51 */     this.table = new int[initialCapacity];
/*     */   }
/*     */   
/*     */   public int size() {
/*  55 */     return this.size;
/*     */   }
/*     */   
/*     */   public int getPosition(int index) {
/*  59 */     int low = 0;
/*  60 */     int high = this.size;
/*  61 */     int result = 0;
/*  62 */     while (low < high) {
/*  63 */       int mid = low + high >>> 1;
/*  64 */       if (index <= mid) {
/*  65 */         high = mid; continue;
/*     */       } 
/*  67 */       result += this.table[mid];
/*  68 */       low = mid + 1;
/*     */     } 
/*     */     
/*  71 */     return result;
/*     */   }
/*     */   
/*     */   public int getEndPosition() {
/*  75 */     int low = 0;
/*  76 */     int high = this.size;
/*  77 */     int result = 0;
/*  78 */     while (low < high) {
/*  79 */       int mid = low + high >>> 1;
/*  80 */       result += this.table[mid];
/*  81 */       low = mid + 1;
/*     */     } 
/*  83 */     return result;
/*     */   }
/*     */   
/*     */   public int getIndex(int position) {
/*  87 */     int low = 0;
/*  88 */     int high = this.size;
/*  89 */     while (low < high) {
/*  90 */       int mid = low + high >>> 1;
/*  91 */       int pos = this.table[mid];
/*  92 */       if (position < pos) {
/*  93 */         high = mid; continue;
/*     */       } 
/*  95 */       low = mid + 1;
/*  96 */       position -= pos;
/*     */     } 
/*     */     
/*  99 */     return low;
/*     */   }
/*     */   
/*     */   public int getSize(int index) {
/* 103 */     return getPosition(index + 1) - getPosition(index);
/*     */   }
/*     */   
/*     */   public boolean setSize(int index, int size) {
/* 107 */     int delta = size - getSize(index);
/* 108 */     if (delta != 0) {
/* 109 */       adjustSize(index, delta);
/* 110 */       return true;
/*     */     } 
/* 112 */     return false;
/*     */   }
/*     */   
/*     */   protected void adjustSize(int index, int delta) {
/* 116 */     int low = 0;
/* 117 */     int high = this.size;
/*     */     
/* 119 */     while (low < high) {
/* 120 */       int mid = low + high >>> 1;
/* 121 */       if (index <= mid) {
/* 122 */         this.table[mid] = this.table[mid] + delta;
/* 123 */         high = mid; continue;
/*     */       } 
/* 125 */       low = mid + 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected int toSizes(int low, int high, int[] dst) {
/* 131 */     int subResult = 0;
/* 132 */     while (low < high) {
/* 133 */       int mid = low + high >>> 1;
/* 134 */       int pos = this.table[mid];
/* 135 */       dst[mid] = pos - toSizes(low, mid, dst);
/* 136 */       subResult += pos;
/* 137 */       low = mid + 1;
/*     */     } 
/* 139 */     return subResult;
/*     */   }
/*     */   
/*     */   protected int fromSizes(int low, int high) {
/* 143 */     int subResult = 0;
/* 144 */     while (low < high) {
/* 145 */       int mid = low + high >>> 1;
/* 146 */       int pos = this.table[mid] + fromSizes(low, mid);
/* 147 */       this.table[mid] = pos;
/* 148 */       subResult += pos;
/* 149 */       low = mid + 1;
/*     */     } 
/* 151 */     return subResult;
/*     */   }
/*     */   
/*     */   public void insert(int index, int count) {
/* 155 */     int newSize = this.size + count;
/* 156 */     if (newSize >= this.table.length) {
/* 157 */       int[] sizes = new int[newSize];
/* 158 */       toSizes(0, this.size, sizes);
/* 159 */       this.table = sizes;
/*     */     } else {
/* 161 */       toSizes(0, this.size, this.table);
/*     */     } 
/* 163 */     System.arraycopy(this.table, index, this.table, index + count, this.size - index);
/* 164 */     this.size = newSize;
/* 165 */     initializeSizes(index, count);
/* 166 */     fromSizes(0, newSize);
/*     */   }
/*     */   
/*     */   public void remove(int index, int count) {
/* 170 */     toSizes(0, this.size, this.table);
/* 171 */     int newSize = this.size - count;
/* 172 */     System.arraycopy(this.table, index + count, this.table, index, newSize - index);
/* 173 */     this.size = newSize;
/* 174 */     fromSizes(0, newSize);
/*     */   }
/*     */   
/*     */   public void initializeAll(int count) {
/* 178 */     if (this.table.length < count) {
/* 179 */       this.table = new int[count];
/*     */     }
/* 181 */     this.size = count;
/* 182 */     initializeSizes(0, count);
/* 183 */     fromSizes(0, count);
/*     */   }
/*     */   
/*     */   public void setDefaultValue(int defaultValue) {
/* 187 */     this.defaultValue = defaultValue;
/*     */   }
/*     */   
/*     */   protected void initializeSizes(int index, int count) {
/* 191 */     Arrays.fill(this.table, index, index + count, this.defaultValue);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\tw\\utils\SizeSequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */