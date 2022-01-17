/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ToggleButtonModel
/*     */   extends SimpleButtonModel
/*     */ {
/*     */   protected static final int STATE_MASK_SELECTED = 256;
/*     */   private BooleanModel model;
/*     */   private Runnable modelCallback;
/*     */   private boolean invertModelState;
/*     */   private boolean isConnected;
/*     */   
/*     */   public ToggleButtonModel() {}
/*     */   
/*     */   public ToggleButtonModel(BooleanModel model) {
/*  51 */     this(model, false);
/*     */   }
/*     */   
/*     */   public ToggleButtonModel(BooleanModel model, boolean invertModelState) {
/*  55 */     setModel(model, invertModelState);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSelected() {
/*  60 */     return ((this.state & 0x100) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelected(boolean selected) {
/*  65 */     if (this.model != null) {
/*  66 */       this.model.setValue(selected ^ this.invertModelState);
/*     */     } else {
/*  68 */       setSelectedState(selected);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonAction() {
/*  74 */     setSelected(!isSelected());
/*  75 */     super.buttonAction();
/*     */   }
/*     */   
/*     */   public BooleanModel getModel() {
/*  79 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(BooleanModel model) {
/*  83 */     setModel(model, false);
/*     */   }
/*     */   
/*     */   public void setModel(BooleanModel model, boolean invertModelState) {
/*  87 */     this.invertModelState = invertModelState;
/*  88 */     if (this.model != model) {
/*  89 */       removeModelCallback();
/*  90 */       this.model = model;
/*  91 */       addModelCallback();
/*     */     } 
/*  93 */     if (model != null) {
/*  94 */       syncWithModel();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isInvertModelState() {
/*  99 */     return this.invertModelState;
/*     */   }
/*     */   
/*     */   void syncWithModel() {
/* 103 */     setSelectedState(this.model.getValue() ^ this.invertModelState);
/*     */   }
/*     */ 
/*     */   
/*     */   public void connect() {
/* 108 */     this.isConnected = true;
/* 109 */     addModelCallback();
/*     */   }
/*     */ 
/*     */   
/*     */   public void disconnect() {
/* 114 */     this.isConnected = false;
/* 115 */     removeModelCallback();
/*     */   }
/*     */   
/*     */   private void addModelCallback() {
/* 119 */     if (this.model != null && this.isConnected) {
/* 120 */       if (this.modelCallback == null) {
/* 121 */         this.modelCallback = new ModelCallback();
/*     */       }
/* 123 */       this.model.addCallback(this.modelCallback);
/* 124 */       syncWithModel();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void removeModelCallback() {
/* 129 */     if (this.model != null && this.modelCallback != null) {
/* 130 */       this.model.removeCallback(this.modelCallback);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setSelectedState(boolean selected) {
/* 135 */     if (selected != isSelected()) {
/* 136 */       setStateBit(256, selected);
/* 137 */       fireStateCallback();
/*     */     } 
/*     */   }
/*     */   
/*     */   class ModelCallback
/*     */     implements Runnable
/*     */   {
/*     */     public void run() {
/* 145 */       ToggleButtonModel.this.syncWithModel();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\ToggleButtonModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */