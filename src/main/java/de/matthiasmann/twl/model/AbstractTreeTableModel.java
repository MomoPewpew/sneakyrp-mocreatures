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
/*     */ public abstract class AbstractTreeTableModel
/*     */   extends AbstractTableColumnHeaderModel
/*     */   implements TreeTableModel
/*     */ {
/*  45 */   private final ArrayList<TreeTableNode> children = new ArrayList<TreeTableNode>();
/*     */   private TreeTableModel.ChangeListener[] callbacks;
/*     */   
/*     */   public void addChangeListener(TreeTableModel.ChangeListener listener) {
/*  49 */     this.callbacks = (TreeTableModel.ChangeListener[])CallbackSupport.addCallbackToList((Object[])this.callbacks, listener, TreeTableModel.ChangeListener.class);
/*     */   }
/*     */   
/*     */   public void removeChangeListener(TreeTableModel.ChangeListener listener) {
/*  53 */     this.callbacks = (TreeTableModel.ChangeListener[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, listener);
/*     */   }
/*     */   
/*     */   public Object getData(int column) {
/*  57 */     return null;
/*     */   }
/*     */   
/*     */   public Object getTooltipContent(int column) {
/*  61 */     return null;
/*     */   }
/*     */   
/*     */   public final TreeTableNode getParent() {
/*  65 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isLeaf() {
/*  69 */     return false;
/*     */   }
/*     */   
/*     */   public int getNumChildren() {
/*  73 */     return this.children.size();
/*     */   }
/*     */   
/*     */   public TreeTableNode getChild(int idx) {
/*  77 */     return this.children.get(idx);
/*     */   }
/*     */   
/*     */   public int getChildIndex(TreeTableNode child) {
/*  81 */     for (int i = 0, n = this.children.size(); i < n; i++) {
/*  82 */       if (this.children.get(i) == child) {
/*  83 */         return i;
/*     */       }
/*     */     } 
/*  86 */     return -1;
/*     */   }
/*     */   
/*     */   protected void insertChild(TreeTableNode node, int idx) {
/*  90 */     assert getChildIndex(node) < 0;
/*  91 */     assert node.getParent() == this;
/*  92 */     this.children.add(idx, node);
/*  93 */     fireNodesAdded(this, idx, 1);
/*     */   }
/*     */   
/*     */   protected void removeChild(int idx) {
/*  97 */     this.children.remove(idx);
/*  98 */     fireNodesRemoved(this, idx, 1);
/*     */   }
/*     */   
/*     */   protected void removeAllChildren() {
/* 102 */     int count = this.children.size();
/* 103 */     if (count > 0) {
/* 104 */       this.children.clear();
/* 105 */       fireNodesRemoved(this, 0, count);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void fireNodesAdded(TreeTableNode parent, int idx, int count) {
/* 110 */     if (this.callbacks != null) {
/* 111 */       for (TreeTableModel.ChangeListener cl : this.callbacks) {
/* 112 */         cl.nodesAdded(parent, idx, count);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireNodesRemoved(TreeTableNode parent, int idx, int count) {
/* 118 */     if (this.callbacks != null) {
/* 119 */       for (TreeTableModel.ChangeListener cl : this.callbacks) {
/* 120 */         cl.nodesRemoved(parent, idx, count);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireNodesChanged(TreeTableNode parent, int idx, int count) {
/* 126 */     if (this.callbacks != null) {
/* 127 */       for (TreeTableModel.ChangeListener cl : this.callbacks) {
/* 128 */         cl.nodesChanged(parent, idx, count);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireColumnInserted(int idx, int count) {
/* 134 */     if (this.callbacks != null) {
/* 135 */       for (TreeTableModel.ChangeListener cl : this.callbacks) {
/* 136 */         cl.columnInserted(idx, count);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireColumnDeleted(int idx, int count) {
/* 142 */     if (this.callbacks != null) {
/* 143 */       for (TreeTableModel.ChangeListener cl : this.callbacks) {
/* 144 */         cl.columnDeleted(idx, count);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fireColumnHeaderChanged(int column) {
/* 150 */     if (this.callbacks != null)
/* 151 */       for (TreeTableModel.ChangeListener cl : this.callbacks)
/* 152 */         cl.columnHeaderChanged(column);  
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\AbstractTreeTableModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */