/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.AutoCompletionDataSource;
/*     */ import de.matthiasmann.twl.model.BitfieldBooleanModel;
/*     */ import de.matthiasmann.twl.model.BooleanModel;
/*     */ import de.matthiasmann.twl.model.ButtonModel;
/*     */ import de.matthiasmann.twl.model.FileSystemAutoCompletionDataSource;
/*     */ import de.matthiasmann.twl.model.FileSystemModel;
/*     */ import de.matthiasmann.twl.model.FileSystemTreeModel;
/*     */ import de.matthiasmann.twl.model.IntegerModel;
/*     */ import de.matthiasmann.twl.model.ListModel;
/*     */ import de.matthiasmann.twl.model.MRUListModel;
/*     */ import de.matthiasmann.twl.model.PersistentIntegerModel;
/*     */ import de.matthiasmann.twl.model.PersistentMRUListModel;
/*     */ import de.matthiasmann.twl.model.SimpleIntegerModel;
/*     */ import de.matthiasmann.twl.model.SimpleListModel;
/*     */ import de.matthiasmann.twl.model.SimpleMRUListModel;
/*     */ import de.matthiasmann.twl.model.ToggleButtonModel;
/*     */ import de.matthiasmann.twl.model.TreeTableModel;
/*     */ import de.matthiasmann.twl.model.TreeTableNode;
/*     */ import de.matthiasmann.twl.utils.CallbackSupport;
/*     */ import de.matthiasmann.twl.utils.NaturalSortComparator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.prefs.Preferences;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileSelector
/*     */   extends DialogLayout
/*     */ {
/*     */   public static class NamedFileFilter
/*     */   {
/*     */     private final String name;
/*     */     private final FileSystemModel.FileFilter fileFilter;
/*     */     
/*     */     public NamedFileFilter(String name, FileSystemModel.FileFilter fileFilter) {
/*  76 */       this.name = name;
/*  77 */       this.fileFilter = fileFilter;
/*     */     }
/*     */     public String getDisplayName() {
/*  80 */       return this.name;
/*     */     }
/*     */     public FileSystemModel.FileFilter getFileFilter() {
/*  83 */       return this.fileFilter;
/*     */     }
/*     */   }
/*     */   
/*  87 */   public static final NamedFileFilter AllFilesFilter = new NamedFileFilter("All files", null);
/*     */   
/*     */   private final IntegerModel flags;
/*     */   
/*     */   private final MRUListModel<String> folderMRU;
/*     */   
/*     */   final MRUListModel<String> filesMRU;
/*     */   
/*     */   private final TreeComboBox currentFolder;
/*     */   
/*     */   private final Label labelCurrentFolder;
/*     */   
/*     */   private final FileTable fileTable;
/*     */   
/*     */   private final ScrollPane fileTableSP;
/*     */   
/*     */   private final Button btnUp;
/*     */   
/*     */   private final Button btnHome;
/*     */   private final Button btnFolderMRU;
/*     */   private final Button btnFilesMRU;
/*     */   private final Button btnOk;
/*     */   private final Button btnCancel;
/*     */   private final Button btnRefresh;
/*     */   private final Button btnShowFolders;
/*     */   private final Button btnShowHidden;
/*     */   private final ComboBox<String> fileFilterBox;
/*     */   private final FileFiltersModel fileFiltersModel;
/*     */   private final EditFieldAutoCompletionWindow autoCompletion;
/*     */   private boolean allowFolderSelection;
/*     */   private Callback[] callbacks;
/*     */   private NamedFileFilter activeFileFilter;
/*     */   FileSystemModel fsm;
/*     */   private FileSystemTreeModel model;
/*     */   private Widget userWidgetBottom;
/*     */   private Widget userWidgetRight;
/*     */   private Object fileToSelectOnSetCurrentNode;
/*     */   
/*     */   public FileSelector() {
/* 126 */     this((Preferences)null, (String)null);
/*     */   }
/*     */   
/*     */   public FileSelector(Preferences prefs, String prefsKey) {
/* 130 */     if (((prefs == null) ? true : false) != ((prefsKey == null) ? true : false)) {
/* 131 */       throw new IllegalArgumentException("'prefs' and 'prefsKey' must both be valid or both null");
/*     */     }
/*     */     
/* 134 */     if (prefs != null) {
/* 135 */       this.flags = (IntegerModel)new PersistentIntegerModel(prefs, prefsKey.concat("_Flags"), 0, 65535, 0);
/* 136 */       this.folderMRU = (MRUListModel<String>)new PersistentMRUListModel(10, String.class, prefs, prefsKey.concat("_foldersMRU"));
/* 137 */       this.filesMRU = (MRUListModel<String>)new PersistentMRUListModel(20, String.class, prefs, prefsKey.concat("_filesMRU"));
/*     */     } else {
/* 139 */       this.flags = (IntegerModel)new SimpleIntegerModel(0, 65535, 0);
/* 140 */       this.folderMRU = (MRUListModel<String>)new SimpleMRUListModel(10);
/* 141 */       this.filesMRU = (MRUListModel<String>)new SimpleMRUListModel(20);
/*     */     } 
/*     */     
/* 144 */     this.currentFolder = new TreeComboBox();
/* 145 */     this.currentFolder.setTheme("currentFolder");
/* 146 */     this.fileTable = new FileTable();
/* 147 */     this.fileTable.setTheme("fileTable");
/* 148 */     this.fileTable.addCallback(new FileTable.Callback() {
/*     */           public void selectionChanged() {
/* 150 */             FileSelector.this.selectionChanged();
/*     */           }
/*     */ 
/*     */           
/*     */           public void sortingChanged() {}
/*     */         });
/* 156 */     this.btnUp = new Button();
/* 157 */     this.btnUp.setTheme("buttonUp");
/* 158 */     this.btnUp.addCallback(new Runnable() {
/*     */           public void run() {
/* 160 */             FileSelector.this.goOneLevelUp();
/*     */           }
/*     */         });
/*     */     
/* 164 */     this.btnHome = new Button();
/* 165 */     this.btnHome.setTheme("buttonHome");
/* 166 */     this.btnHome.addCallback(new Runnable() {
/*     */           public void run() {
/* 168 */             FileSelector.this.goHome();
/*     */           }
/*     */         });
/*     */     
/* 172 */     this.btnFolderMRU = new Button();
/* 173 */     this.btnFolderMRU.setTheme("buttonFoldersMRU");
/* 174 */     this.btnFolderMRU.addCallback(new Runnable() {
/*     */           public void run() {
/* 176 */             FileSelector.this.showFolderMRU();
/*     */           }
/*     */         });
/*     */     
/* 180 */     this.btnFilesMRU = new Button();
/* 181 */     this.btnFilesMRU.setTheme("buttonFilesMRU");
/* 182 */     this.btnFilesMRU.addCallback(new Runnable() {
/*     */           public void run() {
/* 184 */             FileSelector.this.showFilesMRU();
/*     */           }
/*     */         });
/*     */     
/* 188 */     this.btnOk = new Button();
/* 189 */     this.btnOk.setTheme("buttonOk");
/* 190 */     this.btnOk.addCallback(new Runnable() {
/*     */           public void run() {
/* 192 */             FileSelector.this.acceptSelection();
/*     */           }
/*     */         });
/*     */     
/* 196 */     this.btnCancel = new Button();
/* 197 */     this.btnCancel.setTheme("buttonCancel");
/* 198 */     this.btnCancel.addCallback(new Runnable() {
/*     */           public void run() {
/* 200 */             FileSelector.this.fireCanceled();
/*     */           }
/*     */         });
/*     */     
/* 204 */     this.currentFolder.setPathResolver(new TreeComboBox.PathResolver() {
/*     */           public TreeTableNode resolvePath(TreeTableModel model, String path) throws IllegalArgumentException {
/* 206 */             return FileSelector.this.resolvePath(path);
/*     */           }
/*     */         });
/* 209 */     this.currentFolder.addCallback(new TreeComboBox.Callback() {
/*     */           public void selectedNodeChanged(TreeTableNode node, TreeTableNode previousChildNode) {
/* 211 */             FileSelector.this.setCurrentNode(node, previousChildNode);
/*     */           }
/*     */         });
/*     */     
/* 215 */     this.autoCompletion = new EditFieldAutoCompletionWindow(this.currentFolder.getEditField());
/* 216 */     this.autoCompletion.setUseInvokeAsync(true);
/* 217 */     this.currentFolder.getEditField().setAutoCompletionWindow(this.autoCompletion);
/*     */     
/* 219 */     this.fileTable.setAllowMultiSelection(true);
/* 220 */     this.fileTable.addCallback(new TableBase.Callback() {
/*     */           public void mouseDoubleClicked(int row, int column) {
/* 222 */             FileSelector.this.acceptSelection();
/*     */           }
/*     */ 
/*     */           
/*     */           public void mouseRightClick(int row, int column, Event evt) {}
/*     */           
/*     */           public void columnHeaderClicked(int column) {}
/*     */         });
/* 230 */     this.activeFileFilter = AllFilesFilter;
/* 231 */     this.fileFiltersModel = new FileFiltersModel();
/* 232 */     this.fileFilterBox = new ComboBox<String>((ListModel<String>)this.fileFiltersModel);
/* 233 */     this.fileFilterBox.setTheme("fileFiltersBox");
/* 234 */     this.fileFilterBox.setComputeWidthFromModel(true);
/* 235 */     this.fileFilterBox.setVisible(false);
/* 236 */     this.fileFilterBox.addCallback(new Runnable() {
/*     */           public void run() {
/* 238 */             FileSelector.this.fileFilterChanged();
/*     */           }
/*     */         });
/*     */     
/* 242 */     this.labelCurrentFolder = new Label("Folder");
/* 243 */     this.labelCurrentFolder.setLabelFor(this.currentFolder);
/*     */     
/* 245 */     this.fileTableSP = new ScrollPane(this.fileTable);
/*     */     
/* 247 */     Runnable showBtnCallback = new Runnable() {
/*     */         public void run() {
/* 249 */           FileSelector.this.refreshFileTable();
/*     */         }
/*     */       };
/*     */     
/* 253 */     this.btnRefresh = new Button();
/* 254 */     this.btnRefresh.setTheme("buttonRefresh");
/* 255 */     this.btnRefresh.addCallback(showBtnCallback);
/*     */     
/* 257 */     this.btnShowFolders = new Button((ButtonModel)new ToggleButtonModel((BooleanModel)new BitfieldBooleanModel(this.flags, 0), true));
/* 258 */     this.btnShowFolders.setTheme("buttonShowFolders");
/* 259 */     this.btnShowFolders.addCallback(showBtnCallback);
/*     */     
/* 261 */     this.btnShowHidden = new Button((ButtonModel)new ToggleButtonModel((BooleanModel)new BitfieldBooleanModel(this.flags, 1), false));
/* 262 */     this.btnShowHidden.setTheme("buttonShowHidden");
/* 263 */     this.btnShowHidden.addCallback(showBtnCallback);
/*     */     
/* 265 */     addActionMapping("goOneLevelUp", "goOneLevelUp", new Object[0]);
/* 266 */     addActionMapping("acceptSelection", "acceptSelection", new Object[0]);
/*     */   }
/*     */   
/*     */   protected void createLayout() {
/* 270 */     setHorizontalGroup((DialogLayout.Group)null);
/* 271 */     setVerticalGroup((DialogLayout.Group)null);
/* 272 */     removeAllChildren();
/*     */     
/* 274 */     add(this.fileTableSP);
/* 275 */     add(this.fileFilterBox);
/* 276 */     add(this.btnOk);
/* 277 */     add(this.btnCancel);
/* 278 */     add(this.btnRefresh);
/* 279 */     add(this.btnShowFolders);
/* 280 */     add(this.btnShowHidden);
/* 281 */     add(this.labelCurrentFolder);
/* 282 */     add(this.currentFolder);
/* 283 */     add(this.btnFolderMRU);
/* 284 */     add(this.btnUp);
/*     */     
/* 286 */     DialogLayout.Group hCurrentFolder = createSequentialGroup().addWidget(this.labelCurrentFolder).addWidget(this.currentFolder).addWidget(this.btnFolderMRU).addWidget(this.btnUp).addWidget(this.btnHome);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 292 */     DialogLayout.Group vCurrentFolder = createParallelGroup().addWidget(this.labelCurrentFolder).addWidget(this.currentFolder).addWidget(this.btnFolderMRU).addWidget(this.btnUp).addWidget(this.btnHome);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 299 */     DialogLayout.Group hButtonGroup = createSequentialGroup().addWidget(this.btnRefresh).addGap(-2).addWidget(this.btnShowFolders).addWidget(this.btnShowHidden).addWidget(this.fileFilterBox).addGap("buttonBarLeft").addWidget(this.btnFilesMRU).addGap("buttonBarSpacer").addWidget(this.btnOk).addGap("buttonBarSpacer").addWidget(this.btnCancel).addGap("buttonBarRight");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 312 */     DialogLayout.Group vButtonGroup = createParallelGroup().addWidget(this.btnRefresh).addWidget(this.btnShowFolders).addWidget(this.btnShowHidden).addWidget(this.fileFilterBox).addWidget(this.btnFilesMRU).addWidget(this.btnOk).addWidget(this.btnCancel);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 321 */     DialogLayout.Group horz = createParallelGroup().addGroup(hCurrentFolder).addWidget(this.fileTableSP);
/*     */ 
/*     */ 
/*     */     
/* 325 */     DialogLayout.Group vert = createSequentialGroup().addGroup(vCurrentFolder).addWidget(this.fileTableSP);
/*     */ 
/*     */ 
/*     */     
/* 329 */     if (this.userWidgetBottom != null) {
/* 330 */       horz.addWidget(this.userWidgetBottom);
/* 331 */       vert.addWidget(this.userWidgetBottom);
/*     */     } 
/*     */     
/* 334 */     if (this.userWidgetRight != null) {
/* 335 */       horz = createParallelGroup().addGroup(createSequentialGroup().addGroup(horz).addWidget(this.userWidgetRight));
/*     */ 
/*     */       
/* 338 */       vert = createSequentialGroup().addGroup(createParallelGroup().addGroup(vert).addWidget(this.userWidgetRight));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 343 */     setHorizontalGroup(horz.addGroup(hButtonGroup));
/* 344 */     setVerticalGroup(vert.addGroup(vButtonGroup));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void afterAddToGUI(GUI gui) {
/* 349 */     super.afterAddToGUI(gui);
/* 350 */     createLayout();
/*     */   }
/*     */   
/*     */   public FileSystemModel getFileSystemModel() {
/* 354 */     return this.fsm;
/*     */   }
/*     */   
/*     */   public void setFileSystemModel(FileSystemModel fsm) {
/* 358 */     this.fsm = fsm;
/* 359 */     if (fsm == null) {
/* 360 */       this.model = null;
/* 361 */       this.currentFolder.setModel((TreeTableModel)null);
/* 362 */       this.fileTable.setCurrentFolder((FileSystemModel)null, (Object)null);
/* 363 */       this.autoCompletion.setDataSource(null);
/*     */     } else {
/* 365 */       this.model = new FileSystemTreeModel(fsm);
/* 366 */       this.model.setSorter(new NameSorter(fsm));
/* 367 */       this.currentFolder.setModel((TreeTableModel)this.model);
/* 368 */       this.currentFolder.setSeparator(fsm.getSeparator());
/* 369 */       this.autoCompletion.setDataSource((AutoCompletionDataSource)new FileSystemAutoCompletionDataSource(fsm, (FileSystemModel.FileFilter)FileSystemTreeModel.FolderFilter.instance));
/*     */       
/* 371 */       if (!gotoFolderFromMRU(0) && !goHome()) {
/* 372 */         setCurrentNode((TreeTableNode)this.model);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getAllowMultiSelection() {
/* 378 */     return this.fileTable.getAllowMultiSelection();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAllowMultiSelection(boolean allowMultiSelection) {
/* 389 */     this.fileTable.setAllowMultiSelection(allowMultiSelection);
/*     */   }
/*     */   
/*     */   public boolean getAllowFolderSelection() {
/* 393 */     return this.allowFolderSelection;
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
/*     */   public void setAllowFolderSelection(boolean allowFolderSelection) {
/* 405 */     this.allowFolderSelection = allowFolderSelection;
/* 406 */     selectionChanged();
/*     */   }
/*     */   
/*     */   public boolean getAllowHorizontalScrolling() {
/* 410 */     return (this.fileTableSP.getFixed() != ScrollPane.Fixed.HORIZONTAL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAllowHorizontalScrolling(boolean allowHorizontalScrolling) {
/* 421 */     this.fileTableSP.setFixed(allowHorizontalScrolling ? ScrollPane.Fixed.NONE : ScrollPane.Fixed.HORIZONTAL);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCallback(Callback callback) {
/* 427 */     this.callbacks = (Callback[])CallbackSupport.addCallbackToList((Object[])this.callbacks, callback, Callback.class);
/*     */   }
/*     */   
/*     */   public void removeCallback(Callback callback) {
/* 431 */     this.callbacks = (Callback[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, callback);
/*     */   }
/*     */   
/*     */   public Widget getUserWidgetBottom() {
/* 435 */     return this.userWidgetBottom;
/*     */   }
/*     */   
/*     */   public void setUserWidgetBottom(Widget userWidgetBottom) {
/* 439 */     this.userWidgetBottom = userWidgetBottom;
/* 440 */     createLayout();
/*     */   }
/*     */   
/*     */   public Widget getUserWidgetRight() {
/* 444 */     return this.userWidgetRight;
/*     */   }
/*     */   
/*     */   public void setUserWidgetRight(Widget userWidgetRight) {
/* 448 */     this.userWidgetRight = userWidgetRight;
/* 449 */     createLayout();
/*     */   }
/*     */   
/*     */   public FileTable getFileTable() {
/* 453 */     return this.fileTable;
/*     */   }
/*     */   
/*     */   public void setOkButtonEnabled(boolean enabled) {
/* 457 */     this.btnOk.setEnabled(enabled);
/*     */   }
/*     */   
/*     */   public Object getCurrentFolder() {
/* 461 */     Object node = this.currentFolder.getCurrentNode();
/* 462 */     if (node instanceof FileSystemTreeModel.FolderNode) {
/* 463 */       return ((FileSystemTreeModel.FolderNode)node).getFolder();
/*     */     }
/* 465 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setCurrentFolder(Object folder) {
/* 470 */     FileSystemTreeModel.FolderNode node = this.model.getNodeForFolder(folder);
/* 471 */     if (node != null) {
/* 472 */       setCurrentNode((TreeTableNode)node);
/* 473 */       return true;
/*     */     } 
/* 475 */     return false;
/*     */   }
/*     */   
/*     */   public boolean selectFile(Object file) {
/* 479 */     if (this.fsm == null) {
/* 480 */       return false;
/*     */     }
/* 482 */     Object parent = this.fsm.getParent(file);
/* 483 */     if (setCurrentFolder(parent)) {
/* 484 */       return this.fileTable.setSelection(file);
/*     */     }
/* 486 */     return false;
/*     */   }
/*     */   
/*     */   public void clearSelection() {
/* 490 */     this.fileTable.clearSelection();
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
/*     */   public void addFileFilter(NamedFileFilter filter) {
/* 503 */     if (filter == null) {
/* 504 */       throw new NullPointerException("filter");
/*     */     }
/* 506 */     this.fileFiltersModel.addFileFilter(filter);
/* 507 */     this.fileFilterBox.setVisible((this.fileFiltersModel.getNumEntries() > 0));
/* 508 */     if (this.fileFilterBox.getSelected() < 0) {
/* 509 */       this.fileFilterBox.setSelected(0);
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeFileFilter(NamedFileFilter filter) {
/* 514 */     if (filter == null) {
/* 515 */       throw new NullPointerException("filter");
/*     */     }
/* 517 */     this.fileFiltersModel.removeFileFilter(filter);
/* 518 */     if (this.fileFiltersModel.getNumEntries() == 0) {
/* 519 */       this.fileFilterBox.setVisible(false);
/* 520 */       setFileFilter(AllFilesFilter);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeAllFileFilters() {
/* 525 */     this.fileFiltersModel.removeAll();
/* 526 */     this.fileFilterBox.setVisible(false);
/* 527 */     setFileFilter(AllFilesFilter);
/*     */   }
/*     */   
/*     */   public void setFileFilter(NamedFileFilter filter) {
/* 531 */     if (filter == null) {
/* 532 */       throw new NullPointerException("filter");
/*     */     }
/* 534 */     int idx = this.fileFiltersModel.findFilter(filter);
/* 535 */     if (idx < 0) {
/* 536 */       throw new IllegalArgumentException("filter not registered");
/*     */     }
/* 538 */     this.fileFilterBox.setSelected(idx);
/*     */   }
/*     */   
/*     */   public NamedFileFilter getFileFilter() {
/* 542 */     return this.activeFileFilter;
/*     */   }
/*     */   
/*     */   public boolean getShowFolders() {
/* 546 */     return this.btnShowFolders.getModel().isSelected();
/*     */   }
/*     */   
/*     */   public void setShowFolders(boolean showFolders) {
/* 550 */     this.btnShowFolders.getModel().setSelected(showFolders);
/*     */   }
/*     */   
/*     */   public boolean getShowHidden() {
/* 554 */     return this.btnShowHidden.getModel().isSelected();
/*     */   }
/*     */   
/*     */   public void setShowHidden(boolean showHidden) {
/* 558 */     this.btnShowHidden.getModel().setSelected(showHidden);
/*     */   }
/*     */   
/*     */   public void goOneLevelUp() {
/* 562 */     TreeTableNode node = this.currentFolder.getCurrentNode();
/* 563 */     TreeTableNode parent = node.getParent();
/* 564 */     if (parent != null) {
/* 565 */       setCurrentNode(parent, node);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean goHome() {
/* 570 */     if (this.fsm != null) {
/* 571 */       Object folder = this.fsm.getSpecialFolder("user.home");
/* 572 */       if (folder != null) {
/* 573 */         return setCurrentFolder(folder);
/*     */       }
/*     */     } 
/* 576 */     return false;
/*     */   }
/*     */   
/*     */   public void acceptSelection() {
/* 580 */     FileTable.Entry[] selection = this.fileTable.getSelection();
/* 581 */     if (selection.length == 1) {
/* 582 */       FileTable.Entry entry = selection[0];
/* 583 */       if (entry != null && entry.isFolder) {
/* 584 */         setCurrentFolder(entry.obj);
/*     */         return;
/*     */       } 
/*     */     } 
/* 588 */     fireAcceptCallback(selection);
/*     */   }
/*     */   
/*     */   void fileFilterChanged() {
/* 592 */     int idx = this.fileFilterBox.getSelected();
/* 593 */     if (idx >= 0) {
/* 594 */       NamedFileFilter filter = this.fileFiltersModel.getFileFilter(idx);
/* 595 */       this.activeFileFilter = filter;
/* 596 */       this.fileTable.setFileFilter(filter.getFileFilter());
/*     */     } 
/*     */   }
/*     */   
/*     */   void fireAcceptCallback(FileTable.Entry[] selection) {
/* 601 */     if (this.callbacks != null) {
/* 602 */       Object[] objects = new Object[selection.length];
/* 603 */       for (int i = 0; i < selection.length; i++) {
/* 604 */         FileTable.Entry e = selection[i];
/* 605 */         if (e.isFolder && !this.allowFolderSelection) {
/*     */           return;
/*     */         }
/* 608 */         objects[i] = e.obj;
/*     */       } 
/* 610 */       addToMRU(selection);
/* 611 */       for (Callback cb : this.callbacks) {
/* 612 */         cb.filesSelected(objects);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   void fireCanceled() {
/* 618 */     if (this.callbacks != null) {
/* 619 */       for (Callback cb : this.callbacks) {
/* 620 */         cb.canceled();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   void selectionChanged() {
/* 626 */     boolean foldersSelected = false;
/* 627 */     boolean filesSelected = false;
/* 628 */     FileTable.Entry[] selection = this.fileTable.getSelection();
/* 629 */     for (FileTable.Entry entry : selection) {
/* 630 */       if (entry.isFolder) {
/* 631 */         foldersSelected = true;
/*     */       } else {
/* 633 */         filesSelected = true;
/*     */       } 
/*     */     } 
/* 636 */     if (this.allowFolderSelection) {
/* 637 */       this.btnOk.setEnabled((filesSelected || foldersSelected));
/*     */     } else {
/* 639 */       this.btnOk.setEnabled((filesSelected && !foldersSelected));
/*     */     } 
/* 641 */     if (this.callbacks != null) {
/* 642 */       for (Callback cb : this.callbacks) {
/* 643 */         if (cb instanceof Callback2) {
/* 644 */           ((Callback2)cb).selectionChanged(selection);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   protected void setCurrentNode(TreeTableNode node, TreeTableNode childToSelect) {
/* 651 */     if (childToSelect instanceof FileSystemTreeModel.FolderNode) {
/* 652 */       this.fileToSelectOnSetCurrentNode = ((FileSystemTreeModel.FolderNode)childToSelect).getFolder();
/*     */     }
/* 654 */     setCurrentNode(node);
/*     */   }
/*     */   
/*     */   protected void setCurrentNode(TreeTableNode node) {
/* 658 */     this.currentFolder.setCurrentNode(node);
/* 659 */     refreshFileTable();
/* 660 */     if (this.callbacks != null) {
/* 661 */       Object curFolder = getCurrentFolder();
/* 662 */       for (Callback cb : this.callbacks) {
/* 663 */         if (cb instanceof Callback2) {
/* 664 */           ((Callback2)cb).folderChanged(curFolder);
/*     */         }
/*     */       } 
/*     */     } 
/* 668 */     if (this.fileToSelectOnSetCurrentNode != null) {
/* 669 */       this.fileTable.setSelection(this.fileToSelectOnSetCurrentNode);
/* 670 */       this.fileToSelectOnSetCurrentNode = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   void refreshFileTable() {
/* 675 */     this.fileTable.setShowFolders(this.btnShowFolders.getModel().isSelected());
/* 676 */     this.fileTable.setShowHidden(this.btnShowHidden.getModel().isSelected());
/* 677 */     this.fileTable.setCurrentFolder(this.fsm, getCurrentFolder());
/*     */   }
/*     */   
/*     */   TreeTableNode resolvePath(String path) throws IllegalArgumentException {
/* 681 */     Object obj = this.fsm.getFile(path);
/* 682 */     this.fileToSelectOnSetCurrentNode = null;
/* 683 */     if (obj != null) {
/* 684 */       if (this.fsm.isFile(obj)) {
/* 685 */         this.fileToSelectOnSetCurrentNode = obj;
/* 686 */         obj = this.fsm.getParent(obj);
/*     */       } 
/* 688 */       FileSystemTreeModel.FolderNode node = this.model.getNodeForFolder(obj);
/* 689 */       if (node != null) {
/* 690 */         return (TreeTableNode)node;
/*     */       }
/*     */     } 
/* 693 */     throw new IllegalArgumentException("Could not resolve: " + path);
/*     */   }
/*     */   
/*     */   void showFolderMRU() {
/* 697 */     final PopupWindow popup = new PopupWindow(this);
/* 698 */     final ListBox<String> listBox = new ListBox<String>((ListModel<String>)this.folderMRU);
/* 699 */     popup.setTheme("fileselector-folderMRUpopup");
/* 700 */     popup.add(listBox);
/* 701 */     if (popup.openPopup()) {
/* 702 */       popup.setInnerSize(getInnerWidth() * 2 / 3, getInnerHeight() * 2 / 3);
/* 703 */       popup.setPosition(this.btnFolderMRU.getX() - popup.getWidth(), this.btnFolderMRU.getY());
/* 704 */       listBox.addCallback(new CallbackWithReason<ListBox.CallbackReason>() {
/*     */             public void callback(ListBox.CallbackReason reason) {
/* 706 */               if (reason.actionRequested()) {
/* 707 */                 popup.closePopup();
/* 708 */                 int idx = listBox.getSelected();
/* 709 */                 if (idx >= 0) {
/* 710 */                   FileSelector.this.gotoFolderFromMRU(idx);
/*     */                 }
/*     */               } 
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */   
/*     */   void showFilesMRU() {
/* 719 */     final PopupWindow popup = new PopupWindow(this);
/* 720 */     DialogLayout layout = new DialogLayout();
/* 721 */     final ListBox<String> listBox = new ListBox<String>((ListModel<String>)this.filesMRU);
/* 722 */     Button popupBtnOk = new Button();
/* 723 */     Button popupBtnCancel = new Button();
/* 724 */     popupBtnOk.setTheme("buttonOk");
/* 725 */     popupBtnCancel.setTheme("buttonCancel");
/* 726 */     popup.setTheme("fileselector-filesMRUpopup");
/* 727 */     popup.add(layout);
/* 728 */     layout.add(listBox);
/* 729 */     layout.add(popupBtnOk);
/* 730 */     layout.add(popupBtnCancel);
/*     */     
/* 732 */     DialogLayout.Group hBtnGroup = layout.createSequentialGroup().addGap().addWidget(popupBtnOk).addWidget(popupBtnCancel);
/*     */     
/* 734 */     DialogLayout.Group vBtnGroup = layout.createParallelGroup().addWidget(popupBtnOk).addWidget(popupBtnCancel);
/*     */     
/* 736 */     layout.setHorizontalGroup(layout.createParallelGroup().addWidget(listBox).addGroup(hBtnGroup));
/* 737 */     layout.setVerticalGroup(layout.createSequentialGroup().addWidget(listBox).addGroup(vBtnGroup));
/*     */     
/* 739 */     if (popup.openPopup()) {
/* 740 */       popup.setInnerSize(getInnerWidth() * 2 / 3, getInnerHeight() * 2 / 3);
/* 741 */       popup.setPosition(getInnerX() + (getInnerWidth() - popup.getWidth()) / 2, this.btnFilesMRU.getY() - popup.getHeight());
/*     */       
/* 743 */       final Runnable okCB = new Runnable() {
/*     */           public void run() {
/* 745 */             int idx = listBox.getSelected();
/* 746 */             if (idx >= 0) {
/* 747 */               Object obj = FileSelector.this.fsm.getFile((String)FileSelector.this.filesMRU.getEntry(idx));
/* 748 */               if (obj != null) {
/* 749 */                 popup.closePopup();
/* 750 */                 FileSelector.this.fireAcceptCallback(new FileTable.Entry[] { new FileTable.Entry(this.this$0.fsm, obj, (this.this$0.fsm.getParent(obj) == null)) });
/*     */               }
/*     */               else {
/*     */                 
/* 754 */                 FileSelector.this.filesMRU.removeEntry(idx);
/*     */               } 
/*     */             } 
/*     */           }
/*     */         };
/* 759 */       popupBtnOk.addCallback(okCB);
/* 760 */       popupBtnCancel.addCallback(new Runnable() {
/*     */             public void run() {
/* 762 */               popup.closePopup();
/*     */             }
/*     */           });
/* 765 */       listBox.addCallback(new CallbackWithReason<ListBox.CallbackReason>() {
/*     */             public void callback(ListBox.CallbackReason reason) {
/* 767 */               if (reason.actionRequested()) {
/* 768 */                 okCB.run();
/*     */               }
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addToMRU(FileTable.Entry[] selection) {
/* 776 */     for (FileTable.Entry entry : selection) {
/* 777 */       this.filesMRU.addEntry(entry.getPath());
/*     */     }
/* 779 */     this.folderMRU.addEntry(this.fsm.getPath(getCurrentFolder()));
/*     */   }
/*     */   
/*     */   boolean gotoFolderFromMRU(int idx) {
/* 783 */     if (idx >= this.folderMRU.getNumEntries()) {
/* 784 */       return false;
/*     */     }
/* 786 */     String path = (String)this.folderMRU.getEntry(idx);
/*     */     try {
/* 788 */       TreeTableNode node = resolvePath(path);
/* 789 */       setCurrentNode(node);
/* 790 */       return true;
/* 791 */     } catch (IllegalArgumentException ex) {
/* 792 */       this.folderMRU.removeEntry(idx);
/* 793 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   static class FileFiltersModel extends SimpleListModel<String> {
/* 798 */     private final ArrayList<FileSelector.NamedFileFilter> filters = new ArrayList<FileSelector.NamedFileFilter>();
/*     */     public FileSelector.NamedFileFilter getFileFilter(int index) {
/* 800 */       return this.filters.get(index);
/*     */     }
/*     */     public String getEntry(int index) {
/* 803 */       FileSelector.NamedFileFilter filter = getFileFilter(index);
/* 804 */       return filter.getDisplayName();
/*     */     }
/*     */     public int getNumEntries() {
/* 807 */       return this.filters.size();
/*     */     }
/*     */     public void addFileFilter(FileSelector.NamedFileFilter filter) {
/* 810 */       int index = this.filters.size();
/* 811 */       this.filters.add(filter);
/* 812 */       fireEntriesInserted(index, index);
/*     */     }
/*     */     public void removeFileFilter(FileSelector.NamedFileFilter filter) {
/* 815 */       int idx = this.filters.indexOf(filter);
/* 816 */       if (idx >= 0) {
/* 817 */         this.filters.remove(idx);
/* 818 */         fireEntriesDeleted(idx, idx);
/*     */       } 
/*     */     }
/*     */     public int findFilter(FileSelector.NamedFileFilter filter) {
/* 822 */       return this.filters.indexOf(filter);
/*     */     }
/*     */     void removeAll() {
/* 825 */       this.filters.clear();
/* 826 */       fireAllChanged();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class NameSorter
/*     */     implements Comparator<Object>
/*     */   {
/*     */     private final FileSystemModel fsm;
/*     */ 
/*     */     
/*     */     private final Comparator<String> nameComparator;
/*     */ 
/*     */ 
/*     */     
/*     */     public NameSorter(FileSystemModel fsm) {
/* 843 */       this.fsm = fsm;
/* 844 */       this.nameComparator = NaturalSortComparator.stringComparator;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public NameSorter(FileSystemModel fsm, Comparator<String> nameComparator) {
/* 853 */       this.fsm = fsm;
/* 854 */       this.nameComparator = nameComparator;
/*     */     }
/*     */     
/*     */     public int compare(Object o1, Object o2) {
/* 858 */       return this.nameComparator.compare(this.fsm.getName(o1), this.fsm.getName(o2));
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface Callback2 extends Callback {
/*     */     void folderChanged(Object param1Object);
/*     */     
/*     */     void selectionChanged(FileTable.Entry[] param1ArrayOfEntry);
/*     */   }
/*     */   
/*     */   public static interface Callback {
/*     */     void filesSelected(Object[] param1ArrayOfObject);
/*     */     
/*     */     void canceled();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\FileSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */