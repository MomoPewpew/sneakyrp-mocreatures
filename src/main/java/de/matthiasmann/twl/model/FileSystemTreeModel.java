/*     */ package de.matthiasmann.twl.model;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FileSystemTreeModel
/*     */   extends AbstractTreeTableModel
/*     */ {
/*     */   private final FileSystemModel fsm;
/*     */   private final boolean includeLastModified;
/*     */   protected Comparator<Object> sorter;
/*     */   
/*     */   public FileSystemTreeModel(FileSystemModel fsm, boolean includeLastModified) {
/*  49 */     this.fsm = fsm;
/*  50 */     this.includeLastModified = includeLastModified;
/*     */     
/*  52 */     insertRoots();
/*     */   }
/*     */   
/*     */   public FileSystemTreeModel(FileSystemModel fsm) {
/*  56 */     this(fsm, false);
/*     */   }
/*     */   
/*     */   public int getNumColumns() {
/*  60 */     return this.includeLastModified ? 2 : 1;
/*     */   }
/*     */   
/*     */   public String getColumnHeaderText(int column) {
/*  64 */     switch (column) {
/*     */       case 0:
/*  66 */         return "Folder";
/*     */       case 1:
/*  68 */         return "Last modified";
/*     */     } 
/*  70 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public FileSystemModel getFileSystemModel() {
/*  75 */     return this.fsm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertRoots() {
/*  82 */     removeAllChildren();
/*     */     
/*  84 */     for (Object root : this.fsm.listRoots())
/*  85 */       insertChild(new FolderNode(this, this.fsm, root), getNumChildren()); 
/*     */   }
/*     */   
/*     */   public FolderNode getNodeForFolder(Object obj) {
/*     */     TreeTableNode parentNode;
/*  90 */     Object parent = this.fsm.getParent(obj);
/*     */     
/*  92 */     if (parent == null) {
/*  93 */       parentNode = this;
/*     */     } else {
/*  95 */       parentNode = getNodeForFolder(parent);
/*     */     } 
/*  97 */     if (parentNode != null) {
/*  98 */       for (int i = 0; i < parentNode.getNumChildren(); i++) {
/*  99 */         FolderNode node = (FolderNode)parentNode.getChild(i);
/* 100 */         if (this.fsm.equals(node.folder, obj)) {
/* 101 */           return node;
/*     */         }
/*     */       } 
/*     */     }
/* 105 */     return null;
/*     */   }
/*     */   
/*     */   public Comparator<Object> getSorter() {
/* 109 */     return this.sorter;
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
/*     */   public void setSorter(Comparator<Object> sorter) {
/* 121 */     if (this.sorter != sorter) {
/* 122 */       this.sorter = sorter;
/* 123 */       insertRoots();
/*     */     } 
/*     */   }
/*     */   
/* 127 */   static final FolderNode[] NO_CHILDREN = new FolderNode[0];
/*     */   
/*     */   public static class FolderNode implements TreeTableNode {
/*     */     private final TreeTableNode parent;
/*     */     private final FileSystemModel fsm;
/*     */     final Object folder;
/*     */     FolderNode[] children;
/*     */     
/*     */     protected FolderNode(TreeTableNode parent, FileSystemModel fsm, Object folder) {
/* 136 */       this.parent = parent;
/* 137 */       this.fsm = fsm;
/* 138 */       this.folder = folder;
/*     */     }
/*     */     
/*     */     public Object getFolder() {
/* 142 */       return this.folder;
/*     */     }
/*     */     
/*     */     public Object getData(int column) {
/* 146 */       switch (column) {
/*     */         case 0:
/* 148 */           return this.fsm.getName(this.folder);
/*     */         case 1:
/* 150 */           return getlastModified();
/*     */       } 
/* 152 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object getTooltipContent(int column) {
/* 157 */       StringBuilder sb = new StringBuilder(this.fsm.getPath(this.folder));
/* 158 */       Date lastModified = getlastModified();
/* 159 */       if (lastModified != null) {
/* 160 */         sb.append("\nLast modified: ").append(lastModified);
/*     */       }
/* 162 */       return sb.toString();
/*     */     }
/*     */     
/*     */     public TreeTableNode getChild(int idx) {
/* 166 */       return this.children[idx];
/*     */     }
/*     */     
/*     */     public int getChildIndex(TreeTableNode child) {
/* 170 */       for (int i = 0, n = this.children.length; i < n; i++) {
/* 171 */         if (this.children[i] == child) {
/* 172 */           return i;
/*     */         }
/*     */       } 
/* 175 */       return -1;
/*     */     }
/*     */     
/*     */     public int getNumChildren() {
/* 179 */       if (this.children == null) {
/* 180 */         collectChilds();
/*     */       }
/* 182 */       return this.children.length;
/*     */     }
/*     */     
/*     */     public TreeTableNode getParent() {
/* 186 */       return this.parent;
/*     */     }
/*     */     
/*     */     public boolean isLeaf() {
/* 190 */       return false;
/*     */     }
/*     */     
/*     */     public FileSystemTreeModel getTreeModel() {
/* 194 */       TreeTableNode node = this.parent;
/*     */       TreeTableNode nodeParent;
/* 196 */       while ((nodeParent = node.getParent()) != null) {
/* 197 */         node = nodeParent;
/*     */       }
/* 199 */       return (FileSystemTreeModel)node;
/*     */     }
/*     */     
/*     */     private void collectChilds() {
/* 203 */       this.children = FileSystemTreeModel.NO_CHILDREN;
/*     */       try {
/* 205 */         Object[] subFolder = this.fsm.listFolder(this.folder, FileSystemTreeModel.FolderFilter.instance);
/* 206 */         if (subFolder != null && subFolder.length > 0) {
/* 207 */           Comparator<Object> sorter = (getTreeModel()).sorter;
/* 208 */           if (sorter != null) {
/* 209 */             Arrays.sort(subFolder, sorter);
/*     */           }
/* 211 */           FolderNode[] newChildren = new FolderNode[subFolder.length];
/* 212 */           for (int i = 0; i < subFolder.length; i++) {
/* 213 */             newChildren[i] = new FolderNode(this, this.fsm, subFolder[i]);
/*     */           }
/* 215 */           this.children = newChildren;
/*     */         } 
/* 217 */       } catch (Exception ex) {
/* 218 */         ex.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/*     */     private Date getlastModified() {
/* 223 */       if (this.parent instanceof FileSystemTreeModel)
/*     */       {
/*     */         
/* 226 */         return null;
/*     */       }
/* 228 */       return new Date(this.fsm.getLastModified(this.folder));
/*     */     }
/*     */   }
/*     */   
/*     */   public static final class FolderFilter implements FileSystemModel.FileFilter {
/* 233 */     public static final FolderFilter instance = new FolderFilter();
/*     */     
/*     */     public boolean accept(FileSystemModel model, Object file) {
/* 236 */       return model.isFolder(file);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\FileSystemTreeModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */