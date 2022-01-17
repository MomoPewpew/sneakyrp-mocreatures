/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.AbstractTableModel;
/*     */ import de.matthiasmann.twl.model.DefaultTableSelectionModel;
/*     */ import de.matthiasmann.twl.model.FileSystemModel;
/*     */ import de.matthiasmann.twl.model.SortOrder;
/*     */ import de.matthiasmann.twl.model.TableModel;
/*     */ import de.matthiasmann.twl.model.TableSelectionModel;
/*     */ import de.matthiasmann.twl.model.TableSingleSelectionModel;
/*     */ import de.matthiasmann.twl.utils.CallbackSupport;
/*     */ import de.matthiasmann.twl.utils.NaturalSortComparator;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileTable
/*     */   extends Table
/*     */ {
/*     */   private final FileTableModel fileTableModel;
/*     */   private final Runnable selectionChangedListener;
/*     */   private TableSelectionModel fileTableSelectionModel;
/*     */   private TableSearchWindow tableSearchWindow;
/*     */   
/*     */   public enum SortColumn
/*     */   {
/*  56 */     NAME((String)FileTable.NameComparator.instance),
/*  57 */     TYPE((String)FileTable.ExtensionComparator.instance),
/*  58 */     SIZE((String)FileTable.SizeComparator.instance),
/*  59 */     LAST_MODIFIED((String)FileTable.LastModifiedComparator.instance);
/*     */     final Comparator<FileTable.Entry> comparator;
/*     */     
/*     */     SortColumn(Comparator<FileTable.Entry> comparator) {
/*  63 */       this.comparator = comparator;
/*     */     }
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
/*  76 */   private SortColumn sortColumn = SortColumn.NAME;
/*  77 */   private SortOrder sortOrder = SortOrder.ASCENDING;
/*     */   
/*     */   private boolean allowMultiSelection;
/*  80 */   private FileSystemModel.FileFilter fileFilter = null;
/*     */   
/*     */   private boolean showFolders = true;
/*     */   
/*     */   private boolean showHidden = false;
/*     */   private FileSystemModel fsm;
/*     */   private Object currentFolder;
/*     */   private Callback[] fileTableCallbacks;
/*     */   
/*     */   public FileTable() {
/*  90 */     this.fileTableModel = new FileTableModel();
/*  91 */     setModel((TableModel)this.fileTableModel);
/*     */     
/*  93 */     this.selectionChangedListener = new Runnable() {
/*     */         public void run() {
/*  95 */           FileTable.this.selectionChanged();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public void addCallback(Callback callback) {
/* 101 */     this.fileTableCallbacks = (Callback[])CallbackSupport.addCallbackToList((Object[])this.fileTableCallbacks, callback, Callback.class);
/*     */   }
/*     */   
/*     */   public void removeCallback(Callback callback) {
/* 105 */     this.fileTableCallbacks = (Callback[])CallbackSupport.removeCallbackFromList((Object[])this.fileTableCallbacks, callback);
/*     */   }
/*     */   
/*     */   public boolean getShowFolders() {
/* 109 */     return this.showFolders;
/*     */   }
/*     */   
/*     */   public void setShowFolders(boolean showFolders) {
/* 113 */     if (this.showFolders != showFolders) {
/* 114 */       this.showFolders = showFolders;
/* 115 */       refreshFileTable();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getShowHidden() {
/* 120 */     return this.showHidden;
/*     */   }
/*     */   
/*     */   public void setShowHidden(boolean showHidden) {
/* 124 */     if (this.showHidden != showHidden) {
/* 125 */       this.showHidden = showHidden;
/* 126 */       refreshFileTable();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFileFilter(FileSystemModel.FileFilter filter) {
/* 132 */     this.fileFilter = filter;
/* 133 */     refreshFileTable();
/*     */   }
/*     */   
/*     */   public FileSystemModel.FileFilter getFileFilter() {
/* 137 */     return this.fileFilter;
/*     */   }
/*     */   
/*     */   public Entry[] getSelection() {
/* 141 */     return this.fileTableModel.getEntries(this.fileTableSelectionModel.getSelection());
/*     */   }
/*     */   
/*     */   public void setSelection(Object... files) {
/* 145 */     this.fileTableSelectionModel.clearSelection();
/* 146 */     for (Object file : files) {
/* 147 */       int idx = this.fileTableModel.findFile(file);
/* 148 */       if (idx >= 0) {
/* 149 */         this.fileTableSelectionModel.addSelection(idx, idx);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean setSelection(Object file) {
/* 155 */     this.fileTableSelectionModel.clearSelection();
/* 156 */     int idx = this.fileTableModel.findFile(file);
/* 157 */     if (idx >= 0) {
/* 158 */       this.fileTableSelectionModel.addSelection(idx, idx);
/* 159 */       scrollToRow(idx);
/* 160 */       return true;
/*     */     } 
/* 162 */     return false;
/*     */   }
/*     */   
/*     */   public void clearSelection() {
/* 166 */     this.fileTableSelectionModel.clearSelection();
/*     */   }
/*     */   
/*     */   public void setSortColumn(SortColumn column) {
/* 170 */     if (column == null) {
/* 171 */       throw new NullPointerException("column");
/*     */     }
/* 173 */     if (this.sortColumn != column) {
/* 174 */       this.sortColumn = column;
/* 175 */       sortingChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSortOrder(SortOrder order) {
/* 180 */     if (order == null) {
/* 181 */       throw new NullPointerException("order");
/*     */     }
/* 183 */     if (this.sortOrder != order) {
/* 184 */       this.sortOrder = order;
/* 185 */       sortingChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getAllowMultiSelection() {
/* 190 */     return this.allowMultiSelection;
/*     */   }
/*     */   
/*     */   public void setAllowMultiSelection(boolean allowMultiSelection) {
/* 194 */     this.allowMultiSelection = allowMultiSelection;
/* 195 */     if (this.fileTableSelectionModel != null) {
/* 196 */       this.fileTableSelectionModel.removeSelectionChangeListener(this.selectionChangedListener);
/*     */     }
/* 198 */     if (this.tableSearchWindow != null) {
/* 199 */       this.tableSearchWindow.setModel(null, 0);
/*     */     }
/* 201 */     if (allowMultiSelection) {
/* 202 */       this.fileTableSelectionModel = (TableSelectionModel)new DefaultTableSelectionModel();
/*     */     } else {
/* 204 */       this.fileTableSelectionModel = (TableSelectionModel)new TableSingleSelectionModel();
/*     */     } 
/* 206 */     this.fileTableSelectionModel.addSelectionChangeListener(this.selectionChangedListener);
/* 207 */     this.tableSearchWindow = new TableSearchWindow(this, this.fileTableSelectionModel);
/* 208 */     this.tableSearchWindow.setModel((TableModel)this.fileTableModel, 0);
/* 209 */     setSelectionManager(new TableRowSelectionManager(this.fileTableSelectionModel));
/* 210 */     setKeyboardSearchHandler(this.tableSearchWindow);
/* 211 */     selectionChanged();
/*     */   }
/*     */   
/*     */   public FileSystemModel getFileSystemModel() {
/* 215 */     return this.fsm;
/*     */   }
/*     */   
/*     */   public final Object getCurrentFolder() {
/* 219 */     return this.currentFolder;
/*     */   }
/*     */   
/*     */   public final boolean isRoot() {
/* 223 */     return (this.currentFolder == null);
/*     */   }
/*     */   
/*     */   public void setCurrentFolder(FileSystemModel fsm, Object folder) {
/* 227 */     this.fsm = fsm;
/* 228 */     this.currentFolder = folder;
/* 229 */     refreshFileTable();
/*     */   }
/*     */   
/*     */   public void refreshFileTable() {
/* 233 */     Object[] objs = collectObjects();
/* 234 */     if (objs != null) {
/* 235 */       int lastFileIdx = objs.length;
/* 236 */       Entry[] entries = new Entry[lastFileIdx];
/* 237 */       int numFolders = 0;
/* 238 */       boolean isRoot = isRoot();
/* 239 */       for (int i = 0; i < objs.length; i++) {
/* 240 */         Entry e = new Entry(this.fsm, objs[i], isRoot);
/* 241 */         if (e.isFolder) {
/* 242 */           entries[numFolders++] = e;
/*     */         } else {
/* 244 */           entries[--lastFileIdx] = e;
/*     */         } 
/*     */       } 
/* 247 */       Arrays.sort(entries, 0, numFolders, NameComparator.instance);
/* 248 */       sortFilesAndUpdateModel(entries, numFolders);
/*     */     } else {
/* 250 */       sortFilesAndUpdateModel(EMPTY, 0);
/*     */     } 
/* 252 */     if (this.tableSearchWindow != null) {
/* 253 */       this.tableSearchWindow.cancelSearch();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void selectionChanged() {
/* 258 */     if (this.fileTableCallbacks != null) {
/* 259 */       for (Callback cb : this.fileTableCallbacks) {
/* 260 */         cb.selectionChanged();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void sortingChanged() {
/* 266 */     setSortArrows();
/* 267 */     sortFilesAndUpdateModel();
/* 268 */     if (this.fileTableCallbacks != null) {
/* 269 */       for (Callback cb : this.fileTableCallbacks) {
/* 270 */         cb.sortingChanged();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private Object[] collectObjects() {
/* 276 */     if (this.fsm == null) {
/* 277 */       return null;
/*     */     }
/* 279 */     if (isRoot()) {
/* 280 */       return this.fsm.listRoots();
/*     */     }
/* 282 */     FileSystemModel.FileFilter filter = this.fileFilter;
/* 283 */     if (filter != null || !getShowFolders() || !getShowHidden()) {
/* 284 */       filter = new FileFilterWrapper(filter, getShowFolders(), getShowHidden());
/*     */     }
/* 286 */     return this.fsm.listFolder(this.currentFolder, filter);
/*     */   }
/*     */   
/*     */   private void sortFilesAndUpdateModel(Entry[] entries, int numFolders) {
/* 290 */     StateSnapshot snapshot = makeSnapshot();
/* 291 */     Arrays.sort(entries, numFolders, entries.length, this.sortOrder.map(this.sortColumn.comparator));
/*     */     
/* 293 */     this.fileTableModel.setData(entries, numFolders);
/* 294 */     restoreSnapshot(snapshot);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void columnHeaderClicked(int column) {
/* 299 */     super.columnHeaderClicked(column);
/*     */     
/* 301 */     SortColumn thisColumn = SortColumn.values()[column];
/* 302 */     if (this.sortColumn == thisColumn) {
/* 303 */       setSortOrder(this.sortOrder.invert());
/*     */     } else {
/* 305 */       setSortColumn(thisColumn);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateColumnHeaderNumbers() {
/* 311 */     super.updateColumnHeaderNumbers();
/* 312 */     setSortArrows();
/*     */   }
/*     */   
/*     */   protected void setSortArrows() {
/* 316 */     setColumnSortOrderAnimationState(this.sortColumn.ordinal(), this.sortOrder);
/*     */   }
/*     */   
/*     */   private void sortFilesAndUpdateModel() {
/* 320 */     sortFilesAndUpdateModel(this.fileTableModel.entries, this.fileTableModel.numFolders);
/*     */   }
/*     */   
/*     */   private StateSnapshot makeSnapshot() {
/* 324 */     return new StateSnapshot(this.fileTableModel.getEntry(this.fileTableSelectionModel.getLeadIndex()), this.fileTableModel.getEntry(this.fileTableSelectionModel.getAnchorIndex()), this.fileTableModel.getEntries(this.fileTableSelectionModel.getSelection()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void restoreSnapshot(StateSnapshot snapshot) {
/* 331 */     for (Entry e : snapshot.selected) {
/* 332 */       int idx = this.fileTableModel.findEntry(e);
/* 333 */       if (idx >= 0) {
/* 334 */         this.fileTableSelectionModel.addSelection(idx, idx);
/*     */       }
/*     */     } 
/* 337 */     int leadIndex = this.fileTableModel.findEntry(snapshot.leadEntry);
/* 338 */     int anchorIndex = this.fileTableModel.findEntry(snapshot.anchorEntry);
/* 339 */     this.fileTableSelectionModel.setLeadIndex(leadIndex);
/* 340 */     this.fileTableSelectionModel.setAnchorIndex(anchorIndex);
/* 341 */     scrollToRow(Math.max(0, leadIndex));
/*     */   }
/*     */   
/* 344 */   static Entry[] EMPTY = new Entry[0];
/*     */   
/*     */   public static final class Entry
/*     */   {
/*     */     public final FileSystemModel fsm;
/*     */     public final Object obj;
/*     */     public final String name;
/*     */     public final boolean isFolder;
/*     */     public final long size;
/*     */     public final Date lastModified;
/*     */     
/*     */     public Entry(FileSystemModel fsm, Object obj, boolean isRoot) {
/* 356 */       this.fsm = fsm;
/* 357 */       this.obj = obj;
/* 358 */       this.name = fsm.getName(obj);
/* 359 */       if (isRoot) {
/*     */ 
/*     */         
/* 362 */         this.isFolder = true;
/* 363 */         this.lastModified = null;
/*     */       } else {
/* 365 */         this.isFolder = fsm.isFolder(obj);
/* 366 */         this.lastModified = new Date(fsm.getLastModified(obj));
/*     */       } 
/* 368 */       if (this.isFolder) {
/* 369 */         this.size = 0L;
/*     */       } else {
/* 371 */         this.size = fsm.getSize(obj);
/*     */       } 
/*     */     }
/*     */     
/*     */     public String getExtension() {
/* 376 */       int idx = this.name.lastIndexOf('.');
/* 377 */       if (idx >= 0) {
/* 378 */         return this.name.substring(idx + 1);
/*     */       }
/* 380 */       return "";
/*     */     }
/*     */ 
/*     */     
/*     */     public String getPath() {
/* 385 */       return this.fsm.getPath(this.obj);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/* 390 */       if (o == null || getClass() != o.getClass()) {
/* 391 */         return false;
/*     */       }
/* 393 */       Entry that = (Entry)o;
/* 394 */       return (this.fsm == that.fsm && this.fsm.equals(this.obj, that.obj));
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 399 */       return (this.obj != null) ? this.obj.hashCode() : 203;
/*     */     }
/*     */   }
/*     */   
/*     */   static class FileTableModel extends AbstractTableModel {
/* 404 */     private final DateFormat dateFormat = DateFormat.getDateInstance();
/*     */     
/* 406 */     FileTable.Entry[] entries = FileTable.EMPTY;
/*     */     int numFolders;
/*     */     
/*     */     public void setData(FileTable.Entry[] entries, int numFolders) {
/* 410 */       fireRowsDeleted(0, getNumRows());
/* 411 */       this.entries = entries;
/* 412 */       this.numFolders = numFolders;
/* 413 */       fireRowsInserted(0, getNumRows());
/*     */     }
/*     */     
/* 416 */     static String[] COLUMN_HEADER = new String[] { "File name", "Type", "Size", "Last modified" };
/*     */     
/*     */     public String getColumnHeaderText(int column) {
/* 419 */       return COLUMN_HEADER[column];
/*     */     }
/*     */     
/*     */     public int getNumColumns() {
/* 423 */       return COLUMN_HEADER.length;
/*     */     }
/*     */     public Object getCell(int row, int column) {
/*     */       String ext;
/* 427 */       FileTable.Entry e = this.entries[row];
/* 428 */       if (e.isFolder) {
/* 429 */         switch (column) { case 0:
/* 430 */             return "[" + e.name + "]";
/* 431 */           case 1: return "Folder";
/* 432 */           case 2: return "";
/* 433 */           case 3: return formatDate(e.lastModified); }
/* 434 */          return "??";
/*     */       } 
/*     */       
/* 437 */       switch (column) { case 0:
/* 438 */           return e.name;
/*     */         case 1:
/* 440 */           ext = e.getExtension();
/* 441 */           return (ext.length() == 0) ? "File" : (ext + "-file");
/*     */         case 2:
/* 443 */           return formatFileSize(e.size);
/* 444 */         case 3: return formatDate(e.lastModified); }
/* 445 */        return "??";
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getTooltipContent(int row, int column) {
/* 452 */       FileTable.Entry e = this.entries[row];
/* 453 */       StringBuilder sb = new StringBuilder(e.name);
/* 454 */       if (!e.isFolder) {
/* 455 */         sb.append("\nSize: ").append(formatFileSize(e.size));
/*     */       }
/* 457 */       if (e.lastModified != null) {
/* 458 */         sb.append("\nLast modified: ").append(formatDate(e.lastModified));
/*     */       }
/* 460 */       return sb.toString();
/*     */     }
/*     */     
/*     */     public int getNumRows() {
/* 464 */       return this.entries.length;
/*     */     }
/*     */     
/*     */     FileTable.Entry getEntry(int row) {
/* 468 */       if (row >= 0 && row < this.entries.length) {
/* 469 */         return this.entries[row];
/*     */       }
/* 471 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     int findEntry(FileTable.Entry entry) {
/* 476 */       for (int i = 0; i < this.entries.length; i++) {
/* 477 */         if (this.entries[i].equals(entry)) {
/* 478 */           return i;
/*     */         }
/*     */       } 
/* 481 */       return -1;
/*     */     }
/*     */     
/*     */     int findFile(Object file) {
/* 485 */       for (int i = 0; i < this.entries.length; i++) {
/* 486 */         FileTable.Entry e = this.entries[i];
/* 487 */         if (e.fsm.equals(e.obj, file)) {
/* 488 */           return i;
/*     */         }
/*     */       } 
/* 491 */       return -1;
/*     */     }
/*     */     
/*     */     FileTable.Entry[] getEntries(int[] selection) {
/* 495 */       int count = selection.length;
/* 496 */       if (count == 0) {
/* 497 */         return FileTable.EMPTY;
/*     */       }
/* 499 */       FileTable.Entry[] result = new FileTable.Entry[count];
/* 500 */       for (int i = 0; i < count; i++) {
/* 501 */         result[i] = this.entries[selection[i]];
/*     */       }
/* 503 */       return result;
/*     */     }
/*     */     
/* 506 */     static String[] SIZE_UNITS = new String[] { " MB", " KB", " B" };
/* 507 */     static long[] SIZE_FACTORS = new long[] { 1048576L, 1024L, 1L };
/*     */     
/*     */     private String formatFileSize(long size) {
/* 510 */       if (size <= 0L) {
/* 511 */         return "0 B";
/*     */       }
/* 513 */       for (int i = 0;; i++) {
/* 514 */         if (size >= SIZE_FACTORS[i]) {
/* 515 */           long value = size * 10L / SIZE_FACTORS[i];
/* 516 */           return Long.toString(value / 10L) + '.' + Character.forDigit((int)(value % 10L), 10) + SIZE_UNITS[i];
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private String formatDate(Date date) {
/* 525 */       if (date == null) {
/* 526 */         return "";
/*     */       }
/* 528 */       return this.dateFormat.format(date);
/*     */     }
/*     */   }
/*     */   
/*     */   static class StateSnapshot {
/*     */     final FileTable.Entry leadEntry;
/*     */     final FileTable.Entry anchorEntry;
/*     */     final FileTable.Entry[] selected;
/*     */     
/*     */     StateSnapshot(FileTable.Entry leadEntry, FileTable.Entry anchorEntry, FileTable.Entry[] selected) {
/* 538 */       this.leadEntry = leadEntry;
/* 539 */       this.anchorEntry = anchorEntry;
/* 540 */       this.selected = selected;
/*     */     }
/*     */   }
/*     */   
/*     */   static class NameComparator implements Comparator<Entry> {
/* 545 */     static final NameComparator instance = new NameComparator();
/*     */     public int compare(FileTable.Entry o1, FileTable.Entry o2) {
/* 547 */       return NaturalSortComparator.naturalCompare(o1.name, o2.name);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ExtensionComparator implements Comparator<Entry> {
/* 552 */     static final ExtensionComparator instance = new ExtensionComparator();
/*     */     public int compare(FileTable.Entry o1, FileTable.Entry o2) {
/* 554 */       return NaturalSortComparator.naturalCompare(o1.getExtension(), o2.getExtension());
/*     */     }
/*     */   }
/*     */   
/*     */   static class SizeComparator implements Comparator<Entry> {
/* 559 */     static final SizeComparator instance = new SizeComparator();
/*     */     public int compare(FileTable.Entry o1, FileTable.Entry o2) {
/* 561 */       return Long.signum(o1.size - o2.size);
/*     */     }
/*     */   }
/*     */   
/*     */   static class LastModifiedComparator implements Comparator<Entry> {
/* 566 */     static final LastModifiedComparator instance = new LastModifiedComparator();
/*     */     public int compare(FileTable.Entry o1, FileTable.Entry o2) {
/* 568 */       Date lm1 = o1.lastModified;
/* 569 */       Date lm2 = o2.lastModified;
/* 570 */       if (lm1 != null && lm2 != null) {
/* 571 */         return lm1.compareTo(lm2);
/*     */       }
/* 573 */       if (lm1 != null) {
/* 574 */         return 1;
/*     */       }
/* 576 */       if (lm2 != null) {
/* 577 */         return -1;
/*     */       }
/* 579 */       return 0;
/*     */     } }
/*     */   
/*     */   private static class FileFilterWrapper implements FileSystemModel.FileFilter {
/*     */     private final FileSystemModel.FileFilter base;
/*     */     private final boolean showFolder;
/*     */     private final boolean showHidden;
/*     */     
/*     */     public FileFilterWrapper(FileSystemModel.FileFilter base, boolean showFolder, boolean showHidden) {
/* 588 */       this.base = base;
/* 589 */       this.showFolder = showFolder;
/* 590 */       this.showHidden = showHidden;
/*     */     }
/*     */     public boolean accept(FileSystemModel fsm, Object file) {
/* 593 */       if (this.showHidden || !fsm.isHidden(file)) {
/* 594 */         if (fsm.isFolder(file)) {
/* 595 */           return this.showFolder;
/*     */         }
/* 597 */         return (this.base == null || this.base.accept(fsm, file));
/*     */       } 
/* 599 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface Callback {
/*     */     void selectionChanged();
/*     */     
/*     */     void sortingChanged();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\FileTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */