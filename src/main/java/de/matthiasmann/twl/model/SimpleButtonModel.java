/*     */ package de.matthiasmann.twl.model;
/*     */ 
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
/*     */ public class SimpleButtonModel
/*     */   implements ButtonModel
/*     */ {
/*     */   protected static final int STATE_MASK_HOVER = 1;
/*     */   protected static final int STATE_MASK_PRESSED = 2;
/*     */   protected static final int STATE_MASK_ARMED = 4;
/*     */   protected static final int STATE_MASK_DISABLED = 8;
/*     */   protected Runnable[] actionCallbacks;
/*     */   protected Runnable[] stateCallbacks;
/*     */   protected int state;
/*     */   
/*     */   public boolean isSelected() {
/*  52 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isPressed() {
/*  56 */     return ((this.state & 0x2) != 0);
/*     */   }
/*     */   
/*     */   public boolean isArmed() {
/*  60 */     return ((this.state & 0x4) != 0);
/*     */   }
/*     */   
/*     */   public boolean isHover() {
/*  64 */     return ((this.state & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/*  69 */     return ((this.state & 0x8) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(boolean selected) {}
/*     */   
/*     */   public void setPressed(boolean pressed) {
/*  76 */     if (pressed != isPressed()) {
/*  77 */       boolean fireAction = (!pressed && isArmed() && isEnabled());
/*  78 */       setStateBit(2, pressed);
/*  79 */       fireStateCallback();
/*  80 */       if (fireAction) {
/*  81 */         buttonAction();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setArmed(boolean armed) {
/*  87 */     if (armed != isArmed()) {
/*  88 */       setStateBit(4, armed);
/*  89 */       fireStateCallback();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setHover(boolean hover) {
/*  94 */     if (hover != isHover()) {
/*  95 */       setStateBit(1, hover);
/*  96 */       fireStateCallback();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 101 */     if (enabled != isEnabled()) {
/* 102 */       setStateBit(8, !enabled);
/* 103 */       fireStateCallback();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void buttonAction() {
/* 108 */     fireActionCallback();
/*     */   }
/*     */   
/*     */   protected void setStateBit(int mask, boolean set) {
/* 112 */     if (set) {
/* 113 */       this.state |= mask;
/*     */     } else {
/* 115 */       this.state &= mask ^ 0xFFFFFFFF;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void fireStateCallback() {
/* 120 */     CallbackSupport.fireCallbacks(this.stateCallbacks);
/*     */   }
/*     */   
/*     */   public void fireActionCallback() {
/* 124 */     CallbackSupport.fireCallbacks(this.actionCallbacks);
/*     */   }
/*     */   
/*     */   public void addActionCallback(Runnable callback) {
/* 128 */     this.actionCallbacks = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.actionCallbacks, callback, Runnable.class);
/*     */   }
/*     */   
/*     */   public void removeActionCallback(Runnable callback) {
/* 132 */     this.actionCallbacks = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.actionCallbacks, callback);
/*     */   }
/*     */   
/*     */   public boolean hasActionCallbacks() {
/* 136 */     return (this.actionCallbacks != null);
/*     */   }
/*     */   
/*     */   public void addStateCallback(Runnable callback) {
/* 140 */     this.stateCallbacks = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.stateCallbacks, callback, Runnable.class);
/*     */   }
/*     */   
/*     */   public void removeStateCallback(Runnable callback) {
/* 144 */     this.stateCallbacks = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.stateCallbacks, callback);
/*     */   }
/*     */   
/*     */   public void connect() {}
/*     */   
/*     */   public void disconnect() {}
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\SimpleButtonModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */