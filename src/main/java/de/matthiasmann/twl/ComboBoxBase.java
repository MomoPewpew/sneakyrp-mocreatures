/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ComboBoxBase
/*     */   extends Widget
/*     */ {
/*  44 */   public static final AnimationState.StateKey STATE_COMBOBOX_KEYBOARD_FOCUS = AnimationState.StateKey.get("comboboxKeyboardFocus");
/*     */   
/*     */   protected final Button button;
/*     */   protected final PopupWindow popup;
/*     */   
/*     */   protected ComboBoxBase() {
/*  50 */     this.button = new Button(getAnimationState());
/*  51 */     this.popup = new PopupWindow(this);
/*     */     
/*  53 */     this.button.addCallback(new Runnable() {
/*     */           public void run() {
/*  55 */             ComboBoxBase.this.openPopup();
/*     */           }
/*     */         });
/*     */     
/*  59 */     add(this.button);
/*  60 */     setCanAcceptKeyboardFocus(true);
/*  61 */     setDepthFocusTraversal(false);
/*     */   }
/*     */   
/*     */   protected abstract Widget getLabel();
/*     */   
/*     */   protected boolean openPopup() {
/*  67 */     if (this.popup.openPopup()) {
/*  68 */       setPopupSize();
/*  69 */       return true;
/*     */     } 
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/*  76 */     return getLabel().getPreferredWidth() + this.button.getPreferredWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/*  81 */     return Math.max(getLabel().getPreferredHeight(), this.button.getPreferredHeight());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/*  86 */     int minWidth = super.getMinWidth();
/*  87 */     minWidth = Math.max(minWidth, getLabel().getMinWidth() + this.button.getMinWidth());
/*  88 */     return minWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/*  93 */     int minInnerHeight = Math.max(getLabel().getMinHeight(), this.button.getMinHeight());
/*  94 */     return Math.max(super.getMinHeight(), minInnerHeight + getBorderVertical());
/*     */   }
/*     */   
/*     */   protected void setPopupSize() {
/*  98 */     int minHeight = this.popup.getMinHeight();
/*  99 */     int popupHeight = computeSize(minHeight, this.popup.getPreferredHeight(), this.popup.getMaxHeight());
/*     */ 
/*     */     
/* 102 */     int popupMaxBottom = this.popup.getParent().getInnerBottom();
/* 103 */     if (getBottom() + minHeight > popupMaxBottom) {
/* 104 */       if (getY() - popupHeight >= this.popup.getParent().getInnerY()) {
/* 105 */         this.popup.setPosition(getX(), getY() - popupHeight);
/*     */       } else {
/* 107 */         this.popup.setPosition(getX(), popupMaxBottom - minHeight);
/*     */       } 
/*     */     } else {
/* 110 */       this.popup.setPosition(getX(), getBottom());
/*     */     } 
/* 112 */     popupHeight = Math.min(popupHeight, popupMaxBottom - this.popup.getY());
/* 113 */     this.popup.setSize(getWidth(), popupHeight);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 118 */     int btnWidth = this.button.getPreferredWidth();
/* 119 */     int innerHeight = getInnerHeight();
/* 120 */     int innerX = getInnerX();
/* 121 */     int innerY = getInnerY();
/* 122 */     this.button.setPosition(getInnerRight() - btnWidth, innerY);
/* 123 */     this.button.setSize(btnWidth, innerHeight);
/* 124 */     getLabel().setPosition(innerX, innerY);
/* 125 */     getLabel().setSize(Math.max(0, this.button.getX() - innerX), innerHeight);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void sizeChanged() {
/* 130 */     super.sizeChanged();
/* 131 */     if (this.popup.isOpen()) {
/* 132 */       setPopupSize();
/*     */     }
/*     */   }
/*     */   
/*     */   private void setRecursive(Widget w, AnimationState.StateKey what, boolean state) {
/* 137 */     w.getAnimationState().setAnimationState(what, state);
/* 138 */     for (int i = 0; i < w.getNumChildren(); i++) {
/* 139 */       Widget child = w.getChild(i);
/* 140 */       setRecursive(child, what, state);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyboardFocusGained() {
/* 146 */     super.keyboardFocusGained();
/* 147 */     setRecursive(getLabel(), STATE_COMBOBOX_KEYBOARD_FOCUS, true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyboardFocusLost() {
/* 152 */     super.keyboardFocusLost();
/* 153 */     setRecursive(getLabel(), STATE_COMBOBOX_KEYBOARD_FOCUS, false);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ComboBoxBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */