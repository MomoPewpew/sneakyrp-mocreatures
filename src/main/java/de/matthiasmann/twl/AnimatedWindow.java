/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.BooleanModel;
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
/*     */ public class AnimatedWindow
/*     */   extends Widget
/*     */ {
/*  42 */   private int numAnimSteps = 10;
/*     */   
/*     */   private int currentStep;
/*     */   private int animSpeed;
/*     */   private BooleanModel model;
/*     */   private Runnable modelCallback;
/*     */   private Runnable[] callbacks;
/*     */   
/*     */   public AnimatedWindow() {
/*  51 */     setVisible(false);
/*     */   }
/*     */   
/*     */   public void addCallback(Runnable cb) {
/*  55 */     this.callbacks = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.callbacks, cb, Runnable.class);
/*     */   }
/*     */   
/*     */   public void removeCallback(Runnable cb) {
/*  59 */     this.callbacks = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, cb);
/*     */   }
/*     */   
/*     */   private void doCallback() {
/*  63 */     CallbackSupport.fireCallbacks(this.callbacks);
/*     */   }
/*     */   
/*     */   public int getNumAnimSteps() {
/*  67 */     return this.numAnimSteps;
/*     */   }
/*     */   
/*     */   public void setNumAnimSteps(int numAnimSteps) {
/*  71 */     if (numAnimSteps < 1) {
/*  72 */       throw new IllegalArgumentException("numAnimSteps");
/*     */     }
/*  74 */     this.numAnimSteps = numAnimSteps;
/*     */   }
/*     */   
/*     */   public void setState(boolean open) {
/*  78 */     if (open && !isOpen()) {
/*  79 */       this.animSpeed = 1;
/*  80 */       setVisible(true);
/*  81 */       doCallback();
/*  82 */     } else if (!open && !isClosed()) {
/*  83 */       this.animSpeed = -1;
/*  84 */       doCallback();
/*     */     } 
/*  86 */     if (this.model != null) {
/*  87 */       this.model.setValue(open);
/*     */     }
/*     */   }
/*     */   
/*     */   public BooleanModel getModel() {
/*  92 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(BooleanModel model) {
/*  96 */     if (this.model != model) {
/*  97 */       if (this.model != null) {
/*  98 */         this.model.removeCallback(this.modelCallback);
/*     */       }
/* 100 */       this.model = model;
/* 101 */       if (model != null) {
/* 102 */         if (this.modelCallback == null) {
/* 103 */           this.modelCallback = new ModelCallback();
/*     */         }
/* 105 */         model.addCallback(this.modelCallback);
/* 106 */         syncWithModel();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isOpen() {
/* 112 */     return (this.currentStep == this.numAnimSteps && this.animSpeed >= 0);
/*     */   }
/*     */   
/*     */   public boolean isOpening() {
/* 116 */     return (this.animSpeed > 0);
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 120 */     return (this.currentStep == 0 && this.animSpeed <= 0);
/*     */   }
/*     */   
/*     */   public boolean isClosing() {
/* 124 */     return (this.animSpeed < 0);
/*     */   }
/*     */   
/*     */   public boolean isAnimating() {
/* 128 */     return (this.animSpeed != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean handleEvent(Event evt) {
/* 133 */     if (isOpen()) {
/* 134 */       if (super.handleEvent(evt)) {
/* 135 */         return true;
/*     */       }
/* 137 */       if (evt.isKeyPressedEvent()) {
/* 138 */         switch (evt.getKeyCode()) {
/*     */           case 1:
/* 140 */             setState(false);
/* 141 */             return true;
/*     */         } 
/*     */ 
/*     */       
/*     */       }
/* 146 */       return false;
/*     */     } 
/* 148 */     if (isClosed()) {
/* 149 */       return false;
/*     */     }
/*     */     
/* 152 */     int mouseX = evt.getMouseX() - getX();
/* 153 */     int mouseY = evt.getMouseY() - getY();
/* 154 */     return (mouseX >= 0 && mouseX < getAnimatedWidth() && mouseY >= 0 && mouseY < getAnimatedHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/* 160 */     int minWidth = 0;
/* 161 */     for (int i = 0, n = getNumChildren(); i < n; i++) {
/* 162 */       Widget child = getChild(i);
/* 163 */       minWidth = Math.max(minWidth, child.getMinWidth());
/*     */     } 
/* 165 */     return Math.max(super.getMinWidth(), minWidth + getBorderHorizontal());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/* 170 */     int minHeight = 0;
/* 171 */     for (int i = 0, n = getNumChildren(); i < n; i++) {
/* 172 */       Widget child = getChild(i);
/* 173 */       minHeight = Math.max(minHeight, child.getMinHeight());
/*     */     } 
/* 175 */     return Math.max(super.getMinHeight(), minHeight + getBorderVertical());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 180 */     return BoxLayout.computePreferredWidthVertical(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 185 */     return BoxLayout.computePreferredHeightHorizontal(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 190 */     layoutChildrenFullInnerArea();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paint(GUI gui) {
/* 195 */     if (this.animSpeed != 0) {
/* 196 */       animate();
/*     */     }
/*     */     
/* 199 */     if (isOpen()) {
/* 200 */       super.paint(gui);
/* 201 */     } else if (!isClosed() && getBackground() != null) {
/* 202 */       getBackground().draw(getAnimationState(), getX(), getY(), getAnimatedWidth(), getAnimatedHeight());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void animate() {
/* 208 */     this.currentStep += this.animSpeed;
/* 209 */     if (this.currentStep == 0 || this.currentStep == this.numAnimSteps) {
/* 210 */       setVisible((this.currentStep > 0));
/* 211 */       this.animSpeed = 0;
/* 212 */       doCallback();
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getAnimatedWidth() {
/* 217 */     return getWidth() * this.currentStep / this.numAnimSteps;
/*     */   }
/*     */   
/*     */   private int getAnimatedHeight() {
/* 221 */     return getHeight() * this.currentStep / this.numAnimSteps;
/*     */   }
/*     */   
/*     */   void syncWithModel() {
/* 225 */     setState(this.model.getValue());
/*     */   }
/*     */ 
/*     */   
/*     */   class ModelCallback
/*     */     implements Runnable
/*     */   {
/*     */     public void run() {
/* 233 */       AnimatedWindow.this.syncWithModel();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\AnimatedWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */