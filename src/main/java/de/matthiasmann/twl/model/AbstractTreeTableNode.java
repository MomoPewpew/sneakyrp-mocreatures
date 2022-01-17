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
/*     */ public abstract class AbstractTreeTableNode
/*     */   implements TreeTableNode
/*     */ {
/*     */   private final TreeTableNode parent;
/*     */   private ArrayList<TreeTableNode> children;
/*     */   private boolean leaf;
/*     */   
/*     */   protected AbstractTreeTableNode(TreeTableNode parent) {
/*  45 */     if (parent == null) {
/*  46 */       throw new NullPointerException("parent");
/*     */     }
/*  48 */     this.parent = parent;
/*  49 */     assert getTreeTableModel() != null;
/*     */   }
/*     */   
/*     */   public Object getTooltipContent(int column) {
/*  53 */     return null;
/*     */   }
/*     */   
/*     */   public final TreeTableNode getParent() {
/*  57 */     return this.parent;
/*     */   }
/*     */   
/*     */   public boolean isLeaf() {
/*  61 */     return this.leaf;
/*     */   }
/*     */   
/*     */   public int getNumChildren() {
/*  65 */     return (this.children != null) ? this.children.size() : 0;
/*     */   }
/*     */   
/*     */   public TreeTableNode getChild(int idx) {
/*  69 */     return this.children.get(idx);
/*     */   }
/*     */   
/*     */   public int getChildIndex(TreeTableNode child) {
/*  73 */     if (this.children != null) {
/*  74 */       for (int i = 0, n = this.children.size(); i < n; i++) {
/*  75 */         if (this.children.get(i) == child) {
/*  76 */           return i;
/*     */         }
/*     */       } 
/*     */     }
/*  80 */     return -1;
/*     */   }
/*     */   
/*     */   protected void setLeaf(boolean leaf) {
/*  84 */     if (this.leaf != leaf) {
/*  85 */       this.leaf = leaf;
/*  86 */       fireNodeChanged();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void insertChild(TreeTableNode node, int idx) {
/*  91 */     assert getChildIndex(node) < 0;
/*  92 */     assert node.getParent() == this;
/*  93 */     if (this.children == null) {
/*  94 */       this.children = new ArrayList<TreeTableNode>();
/*     */     }
/*  96 */     this.children.add(idx, node);
/*  97 */     getTreeTableModel().fireNodesAdded(this, idx, 1);
/*     */   }
/*     */   
/*     */   protected void removeChild(int idx) {
/* 101 */     this.children.remove(idx);
/* 102 */     getTreeTableModel().fireNodesRemoved(this, idx, 1);
/*     */   }
/*     */   
/*     */   protected void removeAllChildren() {
/* 106 */     if (this.children != null) {
/* 107 */       int count = this.children.size();
/* 108 */       this.children.clear();
/* 109 */       getTreeTableModel().fireNodesRemoved(this, 0, count);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected AbstractTreeTableModel getTreeTableModel() {
/* 114 */     TreeTableNode n = this.parent;
/*     */     while (true) {
/* 116 */       TreeTableNode p = n.getParent();
/* 117 */       if (p == null) {
/* 118 */         return (AbstractTreeTableModel)n;
/*     */       }
/* 120 */       n = p;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void fireNodeChanged() {
/* 125 */     int selfIdxInParent = this.parent.getChildIndex(this);
/* 126 */     if (selfIdxInParent >= 0)
/*     */     {
/* 128 */       getTreeTableModel().fireNodesChanged(this.parent, selfIdxInParent, 1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\AbstractTreeTableNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */