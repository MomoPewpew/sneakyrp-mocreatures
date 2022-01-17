/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReorderListModel<T>
/*     */   extends AbstractListModel<T>
/*     */ {
/*     */   private final ListModel<T> base;
/*     */   private final ListModel.ChangeListener listener;
/*     */   private int[] reorderList;
/*     */   private int size;
/*     */   private static final int INSERTIONSORT_THRESHOLD = 7;
/*     */   
/*     */   public ReorderListModel(ListModel<T> base) {
/*  49 */     this.base = base;
/*  50 */     this.reorderList = new int[0];
/*     */     
/*  52 */     this.listener = new ListModel.ChangeListener() {
/*     */         public void entriesInserted(int first, int last) {
/*  54 */           ReorderListModel.this.entriesInserted(first, last);
/*     */         }
/*     */         
/*     */         public void entriesDeleted(int first, int last) {
/*  58 */           ReorderListModel.this.entriesDeleted(first, last);
/*     */         }
/*     */ 
/*     */         
/*     */         public void entriesChanged(int first, int last) {}
/*     */         
/*     */         public void allChanged() {
/*  65 */           ReorderListModel.this.buildNewList();
/*     */         }
/*     */       };
/*     */     
/*  69 */     base.addChangeListener(this.listener);
/*  70 */     buildNewList();
/*     */   }
/*     */   
/*     */   public void destroy() {
/*  74 */     this.base.removeChangeListener(this.listener);
/*     */   }
/*     */   
/*     */   public int getNumEntries() {
/*  78 */     return this.size;
/*     */   }
/*     */   
/*     */   public T getEntry(int index) {
/*  82 */     int remappedIndex = this.reorderList[index];
/*  83 */     return this.base.getEntry(remappedIndex);
/*     */   }
/*     */   
/*     */   public Object getEntryTooltip(int index) {
/*  87 */     int remappedIndex = this.reorderList[index];
/*  88 */     return this.base.getEntryTooltip(remappedIndex);
/*     */   }
/*     */   
/*     */   public boolean matchPrefix(int index, String prefix) {
/*  92 */     int remappedIndex = this.reorderList[index];
/*  93 */     return this.base.matchPrefix(remappedIndex, prefix);
/*     */   }
/*     */   
/*     */   public int findEntry(Object o) {
/*  97 */     int[] list = this.reorderList;
/*  98 */     for (int i = 0, n = this.size; i < n; i++) {
/*  99 */       T entry = this.base.getEntry(list[i]);
/* 100 */       if (entry == o || (entry != null && entry.equals(o))) {
/* 101 */         return i;
/*     */       }
/*     */     } 
/* 104 */     return -1;
/*     */   }
/*     */   
/*     */   public void shuffle() {
/* 108 */     Random r = new Random();
/* 109 */     for (int i = this.size; i > 1; ) {
/* 110 */       int j = r.nextInt(i--);
/* 111 */       int temp = this.reorderList[i];
/* 112 */       this.reorderList[i] = this.reorderList[j];
/* 113 */       this.reorderList[j] = temp;
/*     */     } 
/* 115 */     fireAllChanged();
/*     */   }
/*     */ 
/*     */   
/*     */   public void sort(Comparator<T> c) {
/* 120 */     int[] aux = new int[this.size];
/* 121 */     System.arraycopy(this.reorderList, 0, aux, 0, this.size);
/* 122 */     mergeSort(aux, this.reorderList, 0, this.size, c);
/* 123 */     fireAllChanged();
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
/*     */   private void mergeSort(int[] src, int[] dest, int low, int high, Comparator<T> c) {
/* 141 */     int length = high - low;
/*     */ 
/*     */     
/* 144 */     if (length < 7) {
/* 145 */       for (int j = low; j < high; j++) {
/* 146 */         for (int k = j; k > low && compare(dest, k - 1, k, c) > 0; k--) {
/* 147 */           swap(dest, k, k - 1);
/*     */         }
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 154 */     int mid = low + high >>> 1;
/* 155 */     mergeSort(dest, src, low, mid, c);
/* 156 */     mergeSort(dest, src, mid, high, c);
/*     */ 
/*     */ 
/*     */     
/* 160 */     if (compare(src, mid - 1, mid, c) <= 0) {
/* 161 */       System.arraycopy(src, low, dest, low, length);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 166 */     for (int i = low, p = low, q = mid; i < high; i++) {
/* 167 */       if (q >= high || (p < mid && compare(src, p, q, c) <= 0)) {
/* 168 */         dest[i] = src[p++];
/*     */       } else {
/* 170 */         dest[i] = src[q++];
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int compare(int[] list, int a, int b, Comparator<T> c) {
/* 176 */     int aIdx = list[a];
/* 177 */     int bIdx = list[b];
/* 178 */     T objA = this.base.getEntry(aIdx);
/* 179 */     T objB = this.base.getEntry(bIdx);
/* 180 */     return c.compare(objA, objB);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void swap(int[] x, int a, int b) {
/* 187 */     int t = x[a];
/* 188 */     x[a] = x[b];
/* 189 */     x[b] = t;
/*     */   }
/*     */   
/*     */   private void buildNewList() {
/* 193 */     this.size = this.base.getNumEntries();
/* 194 */     this.reorderList = new int[this.size + 1024];
/* 195 */     for (int i = 0; i < this.size; i++) {
/* 196 */       this.reorderList[i] = i;
/*     */     }
/* 198 */     fireAllChanged();
/*     */   }
/*     */   
/*     */   private void entriesInserted(int first, int last) {
/* 202 */     int delta = last - first + 1;
/* 203 */     for (int i = 0; i < this.size; i++) {
/* 204 */       if (this.reorderList[i] >= first) {
/* 205 */         this.reorderList[i] = this.reorderList[i] + delta;
/*     */       }
/*     */     } 
/* 208 */     if (this.size + delta > this.reorderList.length) {
/* 209 */       int[] newList = new int[Math.max(this.size * 2, this.size + delta + 1024)];
/* 210 */       System.arraycopy(this.reorderList, 0, newList, 0, this.size);
/* 211 */       this.reorderList = newList;
/*     */     } 
/* 213 */     int oldSize = this.size;
/* 214 */     for (int j = 0; j < delta; j++) {
/* 215 */       this.reorderList[this.size++] = first + j;
/*     */     }
/* 217 */     fireEntriesInserted(oldSize, this.size - 1);
/*     */   }
/*     */   
/*     */   private void entriesDeleted(int first, int last) {
/* 221 */     int delta = last - first + 1;
/* 222 */     for (int i = 0; i < this.size; i++) {
/* 223 */       int entry = this.reorderList[i];
/* 224 */       if (entry >= first) {
/* 225 */         if (entry <= last) {
/*     */           
/* 227 */           entriesDeletedCopy(first, last, i);
/*     */           return;
/*     */         } 
/* 230 */         this.reorderList[i] = entry - delta;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void entriesDeletedCopy(int first, int last, int i) {
/* 236 */     int delta = last - first + 1;
/* 237 */     int oldSize = this.size;
/* 238 */     int j = i; while (true) { int entry; if (i < oldSize)
/* 239 */       { entry = this.reorderList[i];
/* 240 */         if (entry >= first)
/* 241 */         { if (entry <= last)
/* 242 */           { this.size--;
/* 243 */             fireEntriesDeleted(j, j); }
/*     */           else
/*     */           
/* 246 */           { entry -= delta;
/*     */             
/* 248 */             this.reorderList[j++] = entry; }  continue; }  } else { break; }  this.reorderList[j++] = entry; i++; }
/*     */     
/* 250 */     assert this.size == j;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\ReorderListModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */