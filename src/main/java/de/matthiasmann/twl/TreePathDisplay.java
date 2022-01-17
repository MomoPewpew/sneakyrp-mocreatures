/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.TreeTableNode;
/*     */ import de.matthiasmann.twl.utils.CallbackSupport;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TreePathDisplay
/*     */   extends Widget
/*     */ {
/*     */   private final BoxLayout pathBox;
/*     */   private final EditField editField;
/*     */   private Callback[] callbacks;
/*  51 */   private String separator = "/";
/*     */   private TreeTableNode currentNode;
/*     */   private boolean allowEdit;
/*     */   
/*     */   public TreePathDisplay() {
/*  56 */     this.pathBox = new PathBox();
/*  57 */     this.pathBox.setScroll(true);
/*  58 */     this.pathBox.setClip(true);
/*     */     
/*  60 */     this.editField = new PathEditField();
/*  61 */     this.editField.setVisible(false);
/*     */     
/*  63 */     add(this.pathBox);
/*  64 */     add(this.editField);
/*     */   }
/*     */   
/*     */   public void addCallback(Callback cb) {
/*  68 */     this.callbacks = (Callback[])CallbackSupport.addCallbackToList((Object[])this.callbacks, cb, Callback.class);
/*     */   }
/*     */   
/*     */   public void removeCallback(Callback cb) {
/*  72 */     this.callbacks = (Callback[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, cb);
/*     */   }
/*     */   
/*     */   public TreeTableNode getCurrentNode() {
/*  76 */     return this.currentNode;
/*     */   }
/*     */   
/*     */   public void setCurrentNode(TreeTableNode currentNode) {
/*  80 */     this.currentNode = currentNode;
/*  81 */     rebuildPathBox();
/*     */   }
/*     */   
/*     */   public String getSeparator() {
/*  85 */     return this.separator;
/*     */   }
/*     */   
/*     */   public void setSeparator(String separator) {
/*  89 */     this.separator = separator;
/*  90 */     rebuildPathBox();
/*     */   }
/*     */   
/*     */   public boolean isAllowEdit() {
/*  94 */     return this.allowEdit;
/*     */   }
/*     */   
/*     */   public void setAllowEdit(boolean allowEdit) {
/*  98 */     this.allowEdit = allowEdit;
/*  99 */     rebuildPathBox();
/*     */   }
/*     */   
/*     */   public void setEditErrorMessage(String msg) {
/* 103 */     this.editField.setErrorMessage(msg);
/*     */   }
/*     */   
/*     */   public EditField getEditField() {
/* 107 */     return this.editField;
/*     */   }
/*     */   
/*     */   protected String getTextFromNode(TreeTableNode node) {
/* 111 */     Object data = node.getData(0);
/* 112 */     String text = (data != null) ? data.toString() : "";
/* 113 */     if (text.endsWith(this.separator))
/*     */     {
/* 115 */       text = text.substring(0, text.length() - 1);
/*     */     }
/* 117 */     return text;
/*     */   }
/*     */   
/*     */   private void rebuildPathBox() {
/* 121 */     this.pathBox.removeAllChildren();
/* 122 */     if (this.currentNode != null) {
/* 123 */       recursiveAddNode(this.currentNode, (TreeTableNode)null);
/*     */     }
/*     */   }
/*     */   
/*     */   private void recursiveAddNode(final TreeTableNode node, final TreeTableNode child) {
/* 128 */     if (node.getParent() != null) {
/* 129 */       recursiveAddNode(node.getParent(), node);
/*     */       
/* 131 */       Button btn = new Button(getTextFromNode(node));
/* 132 */       btn.setTheme("node");
/* 133 */       btn.addCallback(new Runnable() {
/*     */             public void run() {
/* 135 */               TreePathDisplay.this.firePathElementClicked(node, child);
/*     */             }
/*     */           });
/* 138 */       this.pathBox.add(btn);
/*     */       
/* 140 */       Label l = new Label(this.separator);
/* 141 */       l.setTheme("separator");
/* 142 */       if (this.allowEdit) {
/* 143 */         l.addCallback(new CallbackWithReason<Label.CallbackReason>() {
/*     */               public void callback(Label.CallbackReason reason) {
/* 145 */                 if (reason == Label.CallbackReason.DOUBLE_CLICK) {
/* 146 */                   TreePathDisplay.this.editPath(node);
/*     */                 }
/*     */               }
/*     */             });
/*     */       }
/* 151 */       this.pathBox.add(l);
/*     */     } 
/*     */   }
/*     */   
/*     */   void endEdit() {
/* 156 */     this.editField.setVisible(false);
/* 157 */     requestKeyboardFocus();
/*     */   }
/*     */   
/*     */   void editPath(TreeTableNode cursorAfterNode) {
/* 161 */     StringBuilder sb = new StringBuilder();
/* 162 */     int cursorPos = 0;
/* 163 */     if (this.currentNode != null) {
/* 164 */       cursorPos = recursiveAddPath(sb, this.currentNode, cursorAfterNode);
/*     */     }
/* 166 */     this.editField.setErrorMessage((Object)null);
/* 167 */     this.editField.setText(sb.toString());
/* 168 */     this.editField.setCursorPos(cursorPos, false);
/* 169 */     this.editField.setVisible(true);
/* 170 */     this.editField.requestKeyboardFocus();
/*     */   }
/*     */   
/*     */   private int recursiveAddPath(StringBuilder sb, TreeTableNode node, TreeTableNode cursorAfterNode) {
/* 174 */     int cursorPos = 0;
/* 175 */     if (node.getParent() != null) {
/* 176 */       cursorPos = recursiveAddPath(sb, node.getParent(), cursorAfterNode);
/* 177 */       sb.append(getTextFromNode(node)).append(this.separator);
/*     */     } 
/* 179 */     if (node == cursorAfterNode) {
/* 180 */       return sb.length();
/*     */     }
/* 182 */     return cursorPos;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean fireResolvePath(String text) {
/* 187 */     if (this.callbacks != null) {
/* 188 */       for (Callback cb : this.callbacks) {
/* 189 */         if (cb.resolvePath(text)) {
/* 190 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 194 */     return false;
/*     */   }
/*     */   
/*     */   protected void firePathElementClicked(TreeTableNode node, TreeTableNode child) {
/* 198 */     if (this.callbacks != null) {
/* 199 */       for (Callback cb : this.callbacks) {
/* 200 */         cb.pathElementClicked(node, child);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 207 */     return this.pathBox.getPreferredWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 212 */     return Math.max(this.pathBox.getPreferredHeight(), this.editField.getPreferredHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/* 219 */     int minInnerHeight = Math.max(this.pathBox.getMinHeight(), this.editField.getMinHeight());
/* 220 */     return Math.max(super.getMinHeight(), minInnerHeight + getBorderVertical());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 225 */     layoutChildFullInnerArea(this.pathBox);
/* 226 */     layoutChildFullInnerArea(this.editField);
/*     */   } public static interface Callback {
/*     */     void pathElementClicked(TreeTableNode param1TreeTableNode1, TreeTableNode param1TreeTableNode2);
/*     */     boolean resolvePath(String param1String); }
/*     */   private class PathBox extends BoxLayout { public PathBox() {
/* 231 */       super(BoxLayout.Direction.HORIZONTAL);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean handleEvent(Event evt) {
/* 236 */       if (evt.isMouseEvent()) {
/* 237 */         if (evt.getType() == Event.Type.MOUSE_CLICKED && evt.getMouseClickCount() == 2) {
/* 238 */           TreePathDisplay.this.editPath(TreePathDisplay.this.getCurrentNode());
/* 239 */           return true;
/*     */         } 
/* 241 */         return (evt.getType() != Event.Type.MOUSE_WHEEL);
/*     */       } 
/* 243 */       return super.handleEvent(evt);
/*     */     } }
/*     */   
/*     */   private class PathEditField extends EditField {
/*     */     private PathEditField() {}
/*     */     
/*     */     protected void keyboardFocusLost() {
/* 250 */       if (!hasOpenPopups()) {
/* 251 */         setVisible(false);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void doCallback(int key) {
/* 258 */       super.doCallback(key);
/*     */       
/* 260 */       switch (key) {
/*     */         case 28:
/* 262 */           if (TreePathDisplay.this.fireResolvePath(getText())) {
/* 263 */             TreePathDisplay.this.endEdit();
/*     */           }
/*     */           break;
/*     */         case 1:
/* 267 */           TreePathDisplay.this.endEdit();
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\TreePathDisplay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */