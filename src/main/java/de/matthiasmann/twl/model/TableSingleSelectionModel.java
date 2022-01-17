/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TableSingleSelectionModel
/*     */   extends AbstractTableSelectionModel
/*     */ {
/*     */   public static final int NO_SELECTION = -1;
/*     */   private int selection;
/*     */   
/*     */   public void rowsInserted(int index, int count) {
/*  45 */     boolean changed = false;
/*  46 */     if (this.selection >= index) {
/*  47 */       this.selection += count;
/*  48 */       changed = true;
/*     */     } 
/*  50 */     super.rowsInserted(index, count);
/*  51 */     if (changed) {
/*  52 */       fireSelectionChange();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void rowsDeleted(int index, int count) {
/*  58 */     boolean changed = false;
/*  59 */     if (this.selection >= index) {
/*  60 */       if (this.selection < index + count) {
/*  61 */         this.selection = -1;
/*     */       } else {
/*  63 */         this.selection -= count;
/*     */       } 
/*  65 */       changed = true;
/*     */     } 
/*  67 */     super.rowsDeleted(index, count);
/*  68 */     if (changed) {
/*  69 */       fireSelectionChange();
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearSelection() {
/*  74 */     if (hasSelection()) {
/*  75 */       this.selection = -1;
/*  76 */       fireSelectionChange();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSelection(int index0, int index1) {
/*  81 */     updateLeadAndAnchor(index0, index1);
/*  82 */     this.selection = index1;
/*  83 */     fireSelectionChange();
/*     */   }
/*     */   
/*     */   public void addSelection(int index0, int index1) {
/*  87 */     updateLeadAndAnchor(index0, index1);
/*  88 */     this.selection = index1;
/*  89 */     fireSelectionChange();
/*     */   }
/*     */   
/*     */   public void invertSelection(int index0, int index1) {
/*  93 */     updateLeadAndAnchor(index0, index1);
/*  94 */     if (this.selection == index1) {
/*  95 */       this.selection = -1;
/*     */     } else {
/*  97 */       this.selection = index1;
/*     */     } 
/*  99 */     fireSelectionChange();
/*     */   }
/*     */   
/*     */   public void removeSelection(int index0, int index1) {
/* 103 */     updateLeadAndAnchor(index0, index1);
/* 104 */     if (hasSelection()) {
/* 105 */       int first = Math.min(index0, index1);
/* 106 */       int last = Math.max(index0, index1);
/* 107 */       if (this.selection >= first && this.selection <= last) {
/* 108 */         this.selection = -1;
/*     */       }
/* 110 */       fireSelectionChange();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isSelected(int index) {
/* 115 */     return (this.selection == index);
/*     */   }
/*     */   
/*     */   public boolean hasSelection() {
/* 119 */     return (this.selection >= 0);
/*     */   }
/*     */   
/*     */   public int getFirstSelected() {
/* 123 */     return this.selection;
/*     */   }
/*     */   
/*     */   public int getLastSelected() {
/* 127 */     return this.selection;
/*     */   }
/*     */   
/*     */   public int[] getSelection() {
/* 131 */     if (this.selection >= 0) {
/* 132 */       return new int[] { this.selection };
/*     */     }
/* 134 */     return new int[0];
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\TableSingleSelectionModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */