/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ import java.util.BitSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultTableSelectionModel
/*     */   extends AbstractTableSelectionModel
/*     */ {
/*  46 */   private final BitSet value = new BitSet();
/*  47 */   private int minIndex = Integer.MAX_VALUE;
/*  48 */   private int maxIndex = Integer.MIN_VALUE;
/*     */ 
/*     */   
/*     */   public int getFirstSelected() {
/*  52 */     return this.minIndex;
/*     */   }
/*     */   
/*     */   public int getLastSelected() {
/*  56 */     return this.maxIndex;
/*     */   }
/*     */   
/*     */   public boolean hasSelection() {
/*  60 */     return (this.maxIndex >= this.minIndex);
/*     */   }
/*     */   
/*     */   public boolean isSelected(int index) {
/*  64 */     return this.value.get(index);
/*     */   }
/*     */   
/*     */   private void clearBit(int idx) {
/*  68 */     if (this.value.get(idx)) {
/*  69 */       this.value.clear(idx);
/*     */       
/*  71 */       if (idx == this.minIndex) {
/*  72 */         this.minIndex = this.value.nextSetBit(this.minIndex + 1);
/*  73 */         if (this.minIndex < 0) {
/*  74 */           this.minIndex = Integer.MAX_VALUE;
/*  75 */           this.maxIndex = Integer.MIN_VALUE;
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*  80 */       if (idx == this.maxIndex) {
/*     */         do {
/*  82 */           this.maxIndex--;
/*  83 */         } while (this.maxIndex >= this.minIndex && !this.value.get(this.maxIndex));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setBit(int idx) {
/*  89 */     if (!this.value.get(idx)) {
/*  90 */       this.value.set(idx);
/*     */       
/*  92 */       if (idx < this.minIndex) {
/*  93 */         this.minIndex = idx;
/*     */       }
/*  95 */       if (idx > this.maxIndex) {
/*  96 */         this.maxIndex = idx;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void toggleBit(int idx) {
/* 102 */     if (this.value.get(idx)) {
/* 103 */       clearBit(idx);
/*     */     } else {
/* 105 */       setBit(idx);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearSelection() {
/* 110 */     if (hasSelection()) {
/* 111 */       this.minIndex = Integer.MAX_VALUE;
/* 112 */       this.maxIndex = Integer.MIN_VALUE;
/* 113 */       this.value.clear();
/* 114 */       fireSelectionChange();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSelection(int index0, int index1) {
/* 119 */     updateLeadAndAnchor(index0, index1);
/* 120 */     this.minIndex = Math.min(index0, index1);
/* 121 */     this.maxIndex = Math.max(index0, index1);
/* 122 */     this.value.clear();
/* 123 */     this.value.set(this.minIndex, this.maxIndex + 1);
/* 124 */     fireSelectionChange();
/*     */   }
/*     */   
/*     */   public void addSelection(int index0, int index1) {
/* 128 */     updateLeadAndAnchor(index0, index1);
/* 129 */     int min = Math.min(index0, index1);
/* 130 */     int max = Math.max(index0, index1);
/* 131 */     for (int i = min; i <= max; i++) {
/* 132 */       setBit(i);
/*     */     }
/* 134 */     fireSelectionChange();
/*     */   }
/*     */   
/*     */   public void invertSelection(int index0, int index1) {
/* 138 */     updateLeadAndAnchor(index0, index1);
/* 139 */     int min = Math.min(index0, index1);
/* 140 */     int max = Math.max(index0, index1);
/* 141 */     for (int i = min; i <= max; i++) {
/* 142 */       toggleBit(i);
/*     */     }
/* 144 */     fireSelectionChange();
/*     */   }
/*     */   
/*     */   public void removeSelection(int index0, int index1) {
/* 148 */     updateLeadAndAnchor(index0, index1);
/* 149 */     if (hasSelection()) {
/* 150 */       int min = Math.min(index0, index1);
/* 151 */       int max = Math.max(index0, index1);
/* 152 */       for (int i = min; i <= max; i++) {
/* 153 */         clearBit(i);
/*     */       }
/* 155 */       fireSelectionChange();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int[] getSelection() {
/* 160 */     int[] result = new int[this.value.cardinality()];
/* 161 */     int idx = -1;
/* 162 */     for (int i = 0; (idx = this.value.nextSetBit(idx + 1)) >= 0; i++) {
/* 163 */       result[i] = idx;
/*     */     }
/* 165 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void rowsInserted(int index, int count) {
/* 170 */     if (index <= this.maxIndex) {
/* 171 */       for (int i = this.maxIndex; i >= index; i--) {
/* 172 */         if (this.value.get(i)) {
/* 173 */           this.value.set(i + count);
/*     */         } else {
/* 175 */           this.value.clear(i + count);
/*     */         } 
/*     */       } 
/* 178 */       this.value.clear(index, index + count);
/* 179 */       this.maxIndex += count;
/* 180 */       if (index <= this.minIndex) {
/* 181 */         this.minIndex += count;
/*     */       }
/*     */     } 
/* 184 */     super.rowsInserted(index, count);
/*     */   }
/*     */ 
/*     */   
/*     */   public void rowsDeleted(int index, int count) {
/* 189 */     if (index <= this.maxIndex) {
/* 190 */       for (int i = index; i <= this.maxIndex; i++) {
/* 191 */         if (this.value.get(i + count)) {
/* 192 */           this.value.set(i);
/*     */         } else {
/* 194 */           this.value.clear(i);
/*     */         } 
/*     */       } 
/* 197 */       this.minIndex = this.value.nextSetBit(0);
/* 198 */       if (this.minIndex < 0) {
/* 199 */         this.minIndex = Integer.MAX_VALUE;
/* 200 */         this.maxIndex = Integer.MIN_VALUE;
/*     */       } else {
/* 202 */         while (this.maxIndex >= this.minIndex && !this.value.get(this.maxIndex)) {
/* 203 */           this.maxIndex--;
/*     */         }
/*     */       } 
/*     */     } 
/* 207 */     super.rowsDeleted(index, count);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\DefaultTableSelectionModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */