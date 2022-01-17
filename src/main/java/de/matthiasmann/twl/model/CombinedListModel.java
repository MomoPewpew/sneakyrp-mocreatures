/*     */ package de.matthiasmann.twl.model;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CombinedListModel<T>
/*     */   extends SimpleListModel<T>
/*     */ {
/*  48 */   private final ArrayList<Sublist> sublists = new ArrayList<Sublist>();
/*  49 */   private int[] sublistStarts = new int[1];
/*     */   private SubListsModel subListsModel;
/*     */   
/*     */   public int getNumEntries() {
/*  53 */     return this.sublistStarts[this.sublistStarts.length - 1];
/*     */   }
/*     */   
/*     */   public T getEntry(int index) {
/*  57 */     Sublist sl = getSublistForIndex(index);
/*  58 */     if (sl != null) {
/*  59 */       return sl.getEntry(index - sl.startIndex);
/*     */     }
/*  61 */     throw new IndexOutOfBoundsException();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getEntryTooltip(int index) {
/*  66 */     Sublist sl = getSublistForIndex(index);
/*  67 */     if (sl != null) {
/*  68 */       return sl.getEntryTooltip(index - sl.startIndex);
/*     */     }
/*  70 */     throw new IndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public int getNumSubLists() {
/*  74 */     return this.sublists.size();
/*     */   }
/*     */   
/*     */   public void addSubList(ListModel<T> model) {
/*  78 */     addSubList(this.sublists.size(), model);
/*     */   }
/*     */   
/*     */   public void addSubList(int index, ListModel<T> model) {
/*  82 */     Sublist sl = new Sublist(model);
/*  83 */     this.sublists.add(index, sl);
/*  84 */     adjustStartOffsets();
/*  85 */     int numEntries = sl.getNumEntries();
/*  86 */     if (numEntries > 0) {
/*  87 */       fireEntriesInserted(sl.startIndex, sl.startIndex + numEntries - 1);
/*     */     }
/*  89 */     if (this.subListsModel != null) {
/*  90 */       this.subListsModel.fireEntriesInserted(index, index);
/*     */     }
/*     */   }
/*     */   
/*     */   public int findSubList(ListModel<T> model) {
/*  95 */     for (int i = 0; i < this.sublists.size(); i++) {
/*  96 */       Sublist sl = this.sublists.get(i);
/*  97 */       if (sl.list == model) {
/*  98 */         return i;
/*     */       }
/*     */     } 
/* 101 */     return -1;
/*     */   }
/*     */   
/*     */   public void removeAllSubLists() {
/* 105 */     for (int i = 0; i < this.sublists.size(); i++) {
/* 106 */       ((Sublist)this.sublists.get(i)).removeChangeListener();
/*     */     }
/* 108 */     this.sublists.clear();
/* 109 */     adjustStartOffsets();
/* 110 */     fireAllChanged();
/* 111 */     if (this.subListsModel != null) {
/* 112 */       this.subListsModel.fireAllChanged();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean removeSubList(ListModel<T> model) {
/* 117 */     int index = findSubList(model);
/* 118 */     if (index >= 0) {
/* 119 */       removeSubList(index);
/* 120 */       return true;
/*     */     } 
/* 122 */     return false;
/*     */   }
/*     */   
/*     */   public ListModel<T> removeSubList(int index) {
/* 126 */     Sublist sl = this.sublists.remove(index);
/* 127 */     sl.removeChangeListener();
/* 128 */     adjustStartOffsets();
/* 129 */     int numEntries = sl.getNumEntries();
/* 130 */     if (numEntries > 0) {
/* 131 */       fireEntriesDeleted(sl.startIndex, sl.startIndex + numEntries - 1);
/*     */     }
/* 133 */     if (this.subListsModel != null) {
/* 134 */       this.subListsModel.fireEntriesDeleted(index, index);
/*     */     }
/* 136 */     return sl.list;
/*     */   }
/*     */   
/*     */   public ListModel<ListModel<T>> getModelForSubLists() {
/* 140 */     if (this.subListsModel == null) {
/* 141 */       this.subListsModel = new SubListsModel();
/*     */     }
/* 143 */     return this.subListsModel;
/*     */   }
/*     */   
/*     */   public int getStartIndexOfSublist(int sublistIndex) {
/* 147 */     return ((Sublist)this.sublists.get(sublistIndex)).startIndex;
/*     */   }
/*     */   
/*     */   private Sublist getSublistForIndex(int index) {
/* 151 */     int[] offsets = this.sublistStarts;
/* 152 */     int lo = 0;
/* 153 */     int hi = offsets.length - 1;
/* 154 */     while (lo <= hi) {
/* 155 */       int mid = lo + hi >>> 1;
/* 156 */       int delta = offsets[mid] - index;
/* 157 */       if (delta <= 0) {
/* 158 */         lo = mid + 1;
/*     */       }
/* 160 */       if (delta > 0) {
/* 161 */         hi = mid - 1;
/*     */       }
/*     */     } 
/* 164 */     if (lo > 0 && lo <= this.sublists.size()) {
/* 165 */       Sublist sl = this.sublists.get(lo - 1);
/* 166 */       assert sl.startIndex <= index;
/* 167 */       return sl;
/*     */     } 
/* 169 */     return null;
/*     */   }
/*     */   
/*     */   void adjustStartOffsets() {
/* 173 */     int[] offsets = new int[this.sublists.size() + 1];
/* 174 */     int startIdx = 0;
/* 175 */     for (int idx = 0; idx < this.sublists.size(); ) {
/* 176 */       Sublist sl = this.sublists.get(idx);
/* 177 */       sl.startIndex = startIdx;
/* 178 */       startIdx += sl.getNumEntries();
/* 179 */       offsets[++idx] = startIdx;
/*     */     } 
/* 181 */     this.sublistStarts = offsets;
/*     */   }
/*     */   
/*     */   class Sublist implements ListModel.ChangeListener {
/*     */     final ListModel<T> list;
/*     */     int startIndex;
/*     */     
/*     */     public Sublist(ListModel<T> list) {
/* 189 */       this.list = list;
/* 190 */       this.list.addChangeListener(this);
/*     */     }
/*     */     
/*     */     public void removeChangeListener() {
/* 194 */       this.list.removeChangeListener(this);
/*     */     }
/*     */     
/*     */     public boolean matchPrefix(int index, String prefix) {
/* 198 */       return this.list.matchPrefix(index, prefix);
/*     */     }
/*     */     
/*     */     public int getNumEntries() {
/* 202 */       return this.list.getNumEntries();
/*     */     }
/*     */     
/*     */     public Object getEntryTooltip(int index) {
/* 206 */       return this.list.getEntryTooltip(index);
/*     */     }
/*     */     
/*     */     public T getEntry(int index) {
/* 210 */       return this.list.getEntry(index);
/*     */     }
/*     */     
/*     */     public void entriesInserted(int first, int last) {
/* 214 */       CombinedListModel.this.adjustStartOffsets();
/* 215 */       CombinedListModel.this.fireEntriesInserted(this.startIndex + first, this.startIndex + last);
/*     */     }
/*     */     
/*     */     public void entriesDeleted(int first, int last) {
/* 219 */       CombinedListModel.this.adjustStartOffsets();
/* 220 */       CombinedListModel.this.fireEntriesDeleted(this.startIndex + first, this.startIndex + last);
/*     */     }
/*     */     
/*     */     public void entriesChanged(int first, int last) {
/* 224 */       CombinedListModel.this.fireEntriesChanged(this.startIndex + first, this.startIndex + last);
/*     */     }
/*     */     
/*     */     public void allChanged() {
/* 228 */       CombinedListModel.this.fireAllChanged();
/*     */     }
/*     */   }
/*     */   
/*     */   class SubListsModel extends SimpleListModel<ListModel<T>> {
/*     */     public int getNumEntries() {
/* 234 */       return CombinedListModel.access$000(CombinedListModel.this).size();
/*     */     }
/*     */     public ListModel<T> getEntry(int index) {
/* 237 */       return ((CombinedListModel.Sublist)CombinedListModel.access$000(CombinedListModel.this).get(index)).list;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\CombinedListModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */