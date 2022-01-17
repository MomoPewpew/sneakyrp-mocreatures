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
/*     */ public class FileSystemAutoCompletionDataSource
/*     */   implements AutoCompletionDataSource
/*     */ {
/*     */   final FileSystemModel fsm;
/*     */   final FileSystemModel.FileFilter fileFilter;
/*     */   
/*     */   public FileSystemAutoCompletionDataSource(FileSystemModel fsm, FileSystemModel.FileFilter fileFilter) {
/*  46 */     if (fsm == null) {
/*  47 */       throw new NullPointerException("fsm");
/*     */     }
/*     */     
/*  50 */     this.fsm = fsm;
/*  51 */     this.fileFilter = fileFilter;
/*     */   }
/*     */   
/*     */   public FileSystemModel getFileSystemModel() {
/*  55 */     return this.fsm;
/*     */   }
/*     */   
/*     */   public FileSystemModel.FileFilter getFileFilter() {
/*  59 */     return this.fileFilter;
/*     */   }
/*     */   public AutoCompletionResult collectSuggestions(String text, int cursorPos, AutoCompletionResult prev) {
/*     */     Object parent;
/*  63 */     text = text.substring(0, cursorPos);
/*  64 */     int prefixLength = computePrefixLength(text);
/*  65 */     String prefix = text.substring(0, prefixLength);
/*     */ 
/*     */     
/*  68 */     if (prev instanceof Result && prev.getPrefixLength() == prefixLength && prev.getText().startsWith(prefix)) {
/*     */ 
/*     */       
/*  71 */       parent = ((Result)prev).parent;
/*     */     } else {
/*  73 */       parent = this.fsm.getFile(prefix);
/*     */     } 
/*     */     
/*  76 */     if (parent == null) {
/*  77 */       return null;
/*     */     }
/*     */     
/*  80 */     Result result = new Result(text, prefixLength, parent);
/*  81 */     this.fsm.listFolder(parent, result);
/*     */     
/*  83 */     if (result.getNumResults() == 0) {
/*  84 */       return null;
/*     */     }
/*     */     
/*  87 */     return result;
/*     */   }
/*     */   
/*     */   int computePrefixLength(String text) {
/*  91 */     String separator = this.fsm.getSeparator();
/*  92 */     int prefixLength = text.lastIndexOf(separator) + separator.length();
/*  93 */     if (prefixLength < 0) {
/*  94 */       prefixLength = 0;
/*     */     }
/*  96 */     return prefixLength;
/*     */   }
/*     */   
/*     */   class Result
/*     */     extends AutoCompletionResult implements FileSystemModel.FileFilter {
/*     */     final Object parent;
/*     */     final String nameFilter;
/* 103 */     final ArrayList<String> results1 = new ArrayList<String>();
/* 104 */     final ArrayList<String> results2 = new ArrayList<String>();
/*     */     
/*     */     public Result(String text, int prefixLength, Object parent) {
/* 107 */       super(text, prefixLength);
/* 108 */       this.parent = parent;
/* 109 */       this.nameFilter = text.substring(prefixLength).toUpperCase();
/*     */     }
/*     */     
/*     */     public boolean accept(FileSystemModel fsm, Object file) {
/* 113 */       FileSystemModel.FileFilter ff = FileSystemAutoCompletionDataSource.this.fileFilter;
/* 114 */       if (ff == null || ff.accept(fsm, file)) {
/* 115 */         int idx = getMatchIndex(fsm.getName(file));
/* 116 */         if (idx >= 0) {
/* 117 */           addName(fsm.getPath(file), idx);
/*     */         }
/*     */       } 
/* 120 */       return false;
/*     */     }
/*     */     
/*     */     private int getMatchIndex(String partName) {
/* 124 */       return partName.toUpperCase().indexOf(this.nameFilter);
/*     */     }
/*     */     private void addName(String fullName, int matchIdx) {
/* 127 */       if (matchIdx == 0) {
/* 128 */         this.results1.add(fullName);
/* 129 */       } else if (matchIdx > 0) {
/* 130 */         this.results2.add(fullName);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void addFiltedNames(ArrayList<String> results) {
/* 135 */       for (int i = 0, n = results.size(); i < n; i++) {
/* 136 */         String fullName = results.get(i);
/* 137 */         int idx = getMatchIndex(fullName.substring(this.prefixLength));
/* 138 */         addName(fullName, idx);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public int getNumResults() {
/* 144 */       return this.results1.size() + this.results2.size();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getResult(int idx) {
/* 149 */       int size1 = this.results1.size();
/* 150 */       if (idx >= size1) {
/* 151 */         return this.results2.get(idx - size1);
/*     */       }
/* 153 */       return this.results1.get(idx);
/*     */     }
/*     */ 
/*     */     
/*     */     boolean canRefine(String text) {
/* 158 */       return (this.prefixLength == FileSystemAutoCompletionDataSource.this.computePrefixLength(text) && text.startsWith(this.text));
/*     */     }
/*     */ 
/*     */     
/*     */     public AutoCompletionResult refine(String text, int cursorPos) {
/* 163 */       text = text.substring(0, cursorPos);
/* 164 */       if (canRefine(text)) {
/* 165 */         Result result = new Result(text, this.prefixLength, this.parent);
/* 166 */         result.addFiltedNames(this.results1);
/* 167 */         result.addFiltedNames(this.results2);
/* 168 */         return result;
/*     */       } 
/* 170 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\FileSystemAutoCompletionDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */