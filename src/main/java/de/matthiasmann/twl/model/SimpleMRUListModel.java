/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ import de.matthiasmann.twl.utils.CallbackSupport;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleMRUListModel<T>
/*     */   implements MRUListModel<T>
/*     */ {
/*     */   protected final ArrayList<T> entries;
/*     */   protected final int maxEntries;
/*     */   protected ListModel.ChangeListener[] listeners;
/*     */   
/*     */   public SimpleMRUListModel(int maxEntries) {
/*  49 */     if (maxEntries <= 1) {
/*  50 */       throw new IllegalArgumentException("maxEntries <= 1");
/*     */     }
/*  52 */     this.entries = new ArrayList<T>();
/*  53 */     this.maxEntries = maxEntries;
/*     */   }
/*     */   
/*     */   public int getMaxEntries() {
/*  57 */     return this.maxEntries;
/*     */   }
/*     */   
/*     */   public int getNumEntries() {
/*  61 */     return this.entries.size();
/*     */   }
/*     */   
/*     */   public T getEntry(int index) {
/*  65 */     return this.entries.get(index);
/*     */   }
/*     */   
/*     */   public void addEntry(T entry) {
/*  69 */     int idx = this.entries.indexOf(entry);
/*  70 */     if (idx >= 0) {
/*  71 */       doDeleteEntry(idx);
/*  72 */     } else if (this.entries.size() == this.maxEntries) {
/*  73 */       doDeleteEntry(this.maxEntries - 1);
/*     */     } 
/*     */     
/*  76 */     this.entries.add(0, entry);
/*     */     
/*  78 */     if (this.listeners != null) {
/*  79 */       for (ListModel.ChangeListener cl : this.listeners) {
/*  80 */         cl.entriesInserted(0, 0);
/*     */       }
/*     */     }
/*     */     
/*  84 */     saveEntries();
/*     */   }
/*     */   
/*     */   public void removeEntry(int index) {
/*  88 */     if (index < 0 && index >= this.entries.size()) {
/*  89 */       throw new IndexOutOfBoundsException();
/*     */     }
/*  91 */     doDeleteEntry(index);
/*     */     
/*  93 */     saveEntries();
/*     */   }
/*     */   
/*     */   public void addChangeListener(ListModel.ChangeListener listener) {
/*  97 */     this.listeners = (ListModel.ChangeListener[])CallbackSupport.addCallbackToList((Object[])this.listeners, listener, ListModel.ChangeListener.class);
/*     */   }
/*     */   
/*     */   public void removeChangeListener(ListModel.ChangeListener listener) {
/* 101 */     this.listeners = (ListModel.ChangeListener[])CallbackSupport.removeCallbackFromList((Object[])this.listeners, listener);
/*     */   }
/*     */   
/*     */   protected void doDeleteEntry(int idx) {
/* 105 */     this.entries.remove(idx);
/*     */     
/* 107 */     if (this.listeners != null) {
/* 108 */       for (ListModel.ChangeListener cl : this.listeners) {
/* 109 */         cl.entriesDeleted(idx, idx);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void saveEntries() {}
/*     */   
/*     */   public Object getEntryTooltip(int index) {
/* 118 */     return null;
/*     */   }
/*     */   
/*     */   public boolean matchPrefix(int index, String prefix) {
/* 122 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleMRUListModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */