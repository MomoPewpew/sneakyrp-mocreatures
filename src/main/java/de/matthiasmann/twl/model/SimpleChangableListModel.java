/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleChangableListModel<T>
/*     */   extends SimpleListModel<T>
/*     */ {
/*     */   private final ArrayList<T> content;
/*     */   
/*     */   public SimpleChangableListModel() {
/*  48 */     this.content = new ArrayList<T>();
/*     */   }
/*     */   
/*     */   public SimpleChangableListModel(Collection<T> content) {
/*  52 */     this.content = new ArrayList<T>(content);
/*     */   }
/*     */   
/*     */   public SimpleChangableListModel(T... content) {
/*  56 */     this.content = new ArrayList<T>(Arrays.asList(content));
/*     */   }
/*     */   
/*     */   public T getEntry(int index) {
/*  60 */     return this.content.get(index);
/*     */   }
/*     */   
/*     */   public int getNumEntries() {
/*  64 */     return this.content.size();
/*     */   }
/*     */   
/*     */   public void addElement(T element) {
/*  68 */     insertElement(getNumEntries(), element);
/*     */   }
/*     */   
/*     */   public void addElements(Collection<T> elements) {
/*  72 */     insertElements(getNumEntries(), elements);
/*     */   }
/*     */   
/*     */   public void addElements(T... elements) {
/*  76 */     insertElements(getNumEntries(), elements);
/*     */   }
/*     */   
/*     */   public void insertElement(int idx, T element) {
/*  80 */     this.content.add(idx, element);
/*  81 */     fireEntriesInserted(idx, idx);
/*     */   }
/*     */   
/*     */   public void insertElements(int idx, Collection<T> elements) {
/*  85 */     this.content.addAll(idx, elements);
/*  86 */     fireEntriesInserted(idx, idx + elements.size() - 1);
/*     */   }
/*     */   
/*     */   public void insertElements(int idx, T... elements) {
/*  90 */     insertElements(idx, Arrays.asList(elements));
/*     */   }
/*     */   
/*     */   public T removeElement(int idx) {
/*  94 */     T result = this.content.remove(idx);
/*  95 */     fireEntriesDeleted(idx, idx);
/*  96 */     return result;
/*     */   }
/*     */   
/*     */   public T setElement(int idx, T element) {
/* 100 */     T result = this.content.set(idx, element);
/* 101 */     fireEntriesChanged(idx, idx);
/* 102 */     return result;
/*     */   }
/*     */   
/*     */   public int findElement(Object element) {
/* 106 */     return this.content.indexOf(element);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 110 */     this.content.clear();
/* 111 */     fireAllChanged();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleChangableListModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */