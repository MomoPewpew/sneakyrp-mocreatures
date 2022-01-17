/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.FileSystemModel;
/*     */ import de.matthiasmann.twl.model.FileSystemTreeModel;
/*     */ import de.matthiasmann.twl.model.JavaFileSystemModel;
/*     */ import de.matthiasmann.twl.model.ListModel;
/*     */ import de.matthiasmann.twl.model.SimpleListModel;
/*     */ import de.matthiasmann.twl.utils.CallbackSupport;
/*     */ import de.matthiasmann.twl.utils.NaturalSortComparator;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FolderBrowser
/*     */   extends Widget
/*     */ {
/*     */   final FileSystemModel fsm;
/*     */   final ListBox<Object> listbox;
/*     */   final FolderModel model;
/*     */   private final BoxLayout curFolderGroup;
/*     */   private Runnable[] selectionChangedCallbacks;
/*     */   Comparator<String> folderComparator;
/*     */   private Object currentFolder;
/*     */   private Runnable[] callbacks;
/*     */   
/*     */   public FolderBrowser() {
/*  61 */     this((FileSystemModel)JavaFileSystemModel.getInstance());
/*     */   }
/*     */   
/*     */   public FolderBrowser(FileSystemModel fsm) {
/*  65 */     if (fsm == null) {
/*  66 */       throw new NullPointerException("fsm");
/*     */     }
/*     */     
/*  69 */     this.fsm = fsm;
/*  70 */     this.model = new FolderModel();
/*  71 */     this.listbox = new ListBox((ListModel)this.model);
/*  72 */     this.curFolderGroup = new BoxLayout();
/*     */     
/*  74 */     this.curFolderGroup.setTheme("currentpathbox");
/*  75 */     this.curFolderGroup.setScroll(true);
/*  76 */     this.curFolderGroup.setClip(true);
/*  77 */     this.curFolderGroup.setAlignment(Alignment.BOTTOM);
/*     */     
/*  79 */     this.listbox.addCallback(new CallbackWithReason<ListBox.CallbackReason>()
/*     */         {
/*     */           public void callback(ListBox.CallbackReason reason) {
/*  82 */             if (FolderBrowser.this.listbox.getSelected() != -1 && 
/*  83 */               reason.actionRequested()) {
/*  84 */               FolderBrowser.this.setCurrentFolder(FolderBrowser.this.model.getFolder(FolderBrowser.this.listbox.getSelected()));
/*     */             }
/*     */             
/*  87 */             Object selection = FolderBrowser.this.getSelectedFolder();
/*  88 */             if (selection != this.lastSelection) {
/*  89 */               this.lastSelection = selection;
/*  90 */               FolderBrowser.this.fireSelectionChangedCallback();
/*     */             } 
/*     */           }
/*     */           private Object lastSelection;
/*     */         });
/*  95 */     add(this.listbox);
/*  96 */     add(this.curFolderGroup);
/*     */     
/*  98 */     setCurrentFolder((Object)null);
/*     */   }
/*     */   
/*     */   public void addCallback(Runnable cb) {
/* 102 */     this.callbacks = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.callbacks, cb, Runnable.class);
/*     */   }
/*     */   
/*     */   public void removeCallback(Runnable cb) {
/* 106 */     this.callbacks = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, cb);
/*     */   }
/*     */   
/*     */   protected void doCallback() {
/* 110 */     CallbackSupport.fireCallbacks(this.callbacks);
/*     */   }
/*     */   
/*     */   public Comparator<String> getFolderComparator() {
/* 114 */     return this.folderComparator;
/*     */   }
/*     */   
/*     */   public void setFolderComparator(Comparator<String> folderComparator) {
/* 118 */     this.folderComparator = folderComparator;
/*     */   }
/*     */   
/*     */   public FileSystemModel getFileSystemModel() {
/* 122 */     return this.fsm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getCurrentFolder() {
/* 130 */     return this.currentFolder;
/*     */   }
/*     */   
/*     */   public boolean setCurrentFolder(Object folder) {
/* 134 */     if (this.model.listFolders(folder)) {
/*     */       
/* 136 */       if (folder == null && this.model.getNumEntries() == 1 && 
/* 137 */         setCurrentFolder(this.model.getFolder(0))) {
/* 138 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 142 */       this.currentFolder = folder;
/* 143 */       this.listbox.setSelected(-1);
/*     */       
/* 145 */       rebuildCurrentFolderGroup();
/*     */       
/* 147 */       doCallback();
/* 148 */       return true;
/*     */     } 
/* 150 */     return false;
/*     */   }
/*     */   
/*     */   public boolean goToParentFolder() {
/* 154 */     if (this.currentFolder != null) {
/* 155 */       Object current = this.currentFolder;
/* 156 */       if (setCurrentFolder(this.fsm.getParent(current))) {
/* 157 */         selectFolder(current);
/* 158 */         return true;
/*     */       } 
/*     */     } 
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getSelectedFolder() {
/* 169 */     if (this.listbox.getSelected() != -1) {
/* 170 */       return this.model.getFolder(this.listbox.getSelected());
/*     */     }
/* 172 */     return null;
/*     */   }
/*     */   
/*     */   public boolean selectFolder(Object current) {
/* 176 */     int idx = this.model.findFolder(current);
/* 177 */     this.listbox.setSelected(idx);
/* 178 */     return (idx != -1);
/*     */   }
/*     */   
/*     */   public void addSelectionChangedCallback(Runnable cb) {
/* 182 */     this.callbacks = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.selectionChangedCallbacks, cb, Runnable.class);
/*     */   }
/*     */   
/*     */   public void removeSelectionChangedCallback(Runnable cb) {
/* 186 */     this.selectionChangedCallbacks = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.selectionChangedCallbacks, cb);
/*     */   }
/*     */   
/*     */   protected void fireSelectionChangedCallback() {
/* 190 */     CallbackSupport.fireCallbacks(this.selectionChangedCallbacks);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean handleEvent(Event evt) {
/* 195 */     if (evt.isKeyPressedEvent()) {
/* 196 */       switch (evt.getKeyCode()) {
/*     */         case 14:
/* 198 */           goToParentFolder();
/* 199 */           return true;
/*     */       } 
/*     */     }
/* 202 */     return super.handleEvent(evt);
/*     */   }
/*     */   
/*     */   private void rebuildCurrentFolderGroup() {
/* 206 */     this.curFolderGroup.removeAllChildren();
/* 207 */     recursiveAddFolder(this.currentFolder, (Object)null);
/*     */   }
/*     */   
/*     */   private void recursiveAddFolder(final Object folder, final Object subFolder) {
/* 211 */     if (folder != null) {
/* 212 */       recursiveAddFolder(this.fsm.getParent(folder), folder);
/*     */     }
/* 214 */     if (this.curFolderGroup.getNumChildren() > 0) {
/* 215 */       Label l = new Label(this.fsm.getSeparator());
/* 216 */       l.setTheme("pathseparator");
/* 217 */       this.curFolderGroup.add(l);
/*     */     } 
/* 219 */     String name = getFolderName(folder);
/* 220 */     if (name.endsWith(this.fsm.getSeparator())) {
/* 221 */       name = name.substring(0, name.length() - 1);
/*     */     }
/* 223 */     Button btn = new Button(name);
/* 224 */     btn.addCallback(new Runnable() {
/*     */           public void run() {
/* 226 */             if (FolderBrowser.this.setCurrentFolder(folder)) {
/* 227 */               FolderBrowser.this.selectFolder(subFolder);
/*     */             }
/* 229 */             FolderBrowser.this.listbox.requestKeyboardFocus();
/*     */           }
/*     */         });
/* 232 */     btn.setTheme("pathbutton");
/* 233 */     this.curFolderGroup.add(btn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void adjustSize() {}
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 242 */     this.curFolderGroup.setPosition(getInnerX(), getInnerY());
/* 243 */     this.curFolderGroup.setSize(getInnerWidth(), this.curFolderGroup.getHeight());
/* 244 */     this.listbox.setPosition(getInnerX(), this.curFolderGroup.getBottom());
/* 245 */     this.listbox.setSize(getInnerWidth(), Math.max(0, getInnerBottom() - this.listbox.getY()));
/*     */   }
/*     */   
/*     */   String getFolderName(Object folder) {
/* 249 */     if (folder != null) {
/* 250 */       return this.fsm.getName(folder);
/*     */     }
/* 252 */     return "ROOT";
/*     */   }
/*     */   
/*     */   class FolderModel
/*     */     extends SimpleListModel<Object> {
/* 257 */     private Object[] folders = new Object[0];
/*     */     
/*     */     public boolean listFolders(Object parent) {
/*     */       Object[] newFolders;
/* 261 */       if (parent == null) {
/* 262 */         newFolders = FolderBrowser.this.fsm.listRoots();
/*     */       } else {
/* 264 */         newFolders = FolderBrowser.this.fsm.listFolder(parent, (FileSystemModel.FileFilter)FileSystemTreeModel.FolderFilter.instance);
/*     */       } 
/* 266 */       if (newFolders == null) {
/* 267 */         Logger.getLogger(FolderModel.class.getName()).log(Level.WARNING, "can''t list folder: {0}", parent);
/* 268 */         return false;
/*     */       } 
/* 270 */       Arrays.sort(newFolders, new FileSelector.NameSorter(FolderBrowser.this.fsm, (FolderBrowser.this.folderComparator != null) ? FolderBrowser.this.folderComparator : NaturalSortComparator.stringComparator));
/*     */ 
/*     */       
/* 273 */       this.folders = newFolders;
/* 274 */       fireAllChanged();
/* 275 */       return true;
/*     */     }
/*     */     
/*     */     public int getNumEntries() {
/* 279 */       return this.folders.length;
/*     */     }
/*     */     
/*     */     public Object getFolder(int index) {
/* 283 */       return this.folders[index];
/*     */     }
/*     */     
/*     */     public Object getEntry(int index) {
/* 287 */       Object folder = getFolder(index);
/* 288 */       return FolderBrowser.this.getFolderName(folder);
/*     */     }
/*     */     
/*     */     public int findFolder(Object folder) {
/* 292 */       int idx = FolderBrowser.this.fsm.find(this.folders, folder);
/* 293 */       return (idx < 0) ? -1 : idx;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\FolderBrowser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */