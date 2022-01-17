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
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ValueAdjuster
/*     */   extends Widget
/*     */ {
/*  47 */   public static final AnimationState.StateKey STATE_EDIT_ACTIVE = AnimationState.StateKey.get("editActive");
/*     */   
/*     */   private static final int INITIAL_DELAY = 300;
/*     */   
/*     */   private static final int REPEAT_DELAY = 75;
/*     */   
/*     */   private final DraggableButton label;
/*     */   private final EditField editField;
/*     */   private final Button decButton;
/*     */   private final Button incButton;
/*     */   private final Runnable timerCallback;
/*     */   private final L listeners;
/*     */   private Timer timer;
/*     */   private String displayPrefix;
/*  61 */   private String displayPrefixTheme = "";
/*     */   private boolean useMouseWheel = true;
/*     */   private boolean acceptValueOnFocusLoss = true;
/*     */   private boolean wasInEditOnFocusLost;
/*     */   private int width;
/*     */   
/*     */   public ValueAdjuster() {
/*  68 */     this.label = new DraggableButton(getAnimationState(), true);
/*     */     
/*  70 */     this.editField = new EditField(getAnimationState());
/*  71 */     this.decButton = new Button(getAnimationState(), true);
/*  72 */     this.incButton = new Button(getAnimationState(), true);
/*     */     
/*  74 */     this.label.setClip(true);
/*  75 */     this.label.setTheme("valueDisplay");
/*  76 */     this.editField.setTheme("valueEdit");
/*  77 */     this.decButton.setTheme("decButton");
/*  78 */     this.incButton.setTheme("incButton");
/*     */     
/*  80 */     Runnable cbUpdateTimer = new Runnable() {
/*     */         public void run() {
/*  82 */           ValueAdjuster.this.updateTimer();
/*     */         }
/*     */       };
/*     */     
/*  86 */     this.timerCallback = new Runnable() {
/*     */         public void run() {
/*  88 */           ValueAdjuster.this.onTimer(75);
/*     */         }
/*     */       };
/*     */     
/*  92 */     this.decButton.getModel().addStateCallback(cbUpdateTimer);
/*  93 */     this.incButton.getModel().addStateCallback(cbUpdateTimer);
/*     */     
/*  95 */     this.listeners = new L();
/*  96 */     this.label.addCallback(this.listeners);
/*  97 */     this.label.setListener(this.listeners);
/*     */     
/*  99 */     this.editField.setVisible(false);
/* 100 */     this.editField.addCallback(this.listeners);
/*     */     
/* 102 */     add(this.label);
/* 103 */     add(this.editField);
/* 104 */     add(this.decButton);
/* 105 */     add(this.incButton);
/* 106 */     setCanAcceptKeyboardFocus(true);
/* 107 */     setDepthFocusTraversal(false);
/*     */   }
/*     */   
/*     */   public String getDisplayPrefix() {
/* 111 */     return this.displayPrefix;
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
/*     */   public void setDisplayPrefix(String displayPrefix) {
/* 123 */     this.displayPrefix = displayPrefix;
/* 124 */     setDisplayText();
/*     */   }
/*     */   
/*     */   public boolean isUseMouseWheel() {
/* 128 */     return this.useMouseWheel;
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
/*     */   public void setAcceptValueOnFocusLoss(boolean acceptValueOnFocusLoss) {
/* 141 */     this.acceptValueOnFocusLoss = acceptValueOnFocusLoss;
/*     */   }
/*     */   
/*     */   public boolean isAcceptValueOnFocusLoss() {
/* 145 */     return this.acceptValueOnFocusLoss;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUseMouseWheel(boolean useMouseWheel) {
/* 154 */     this.useMouseWheel = useMouseWheel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTooltipContent(Object tooltipContent) {
/* 159 */     super.setTooltipContent(tooltipContent);
/* 160 */     this.label.setTooltipContent(tooltipContent);
/*     */   }
/*     */   
/*     */   public void startEdit() {
/* 164 */     if (this.label.isVisible()) {
/* 165 */       this.editField.setErrorMessage((Object)null);
/* 166 */       this.editField.setText(onEditStart());
/* 167 */       this.editField.setVisible(true);
/* 168 */       this.editField.requestKeyboardFocus();
/* 169 */       this.editField.selectAll();
/* 170 */       this.editField.getAnimationState().setAnimationState(EditField.STATE_HOVER, this.label.getModel().isHover());
/* 171 */       this.label.setVisible(false);
/* 172 */       getAnimationState().setAnimationState(STATE_EDIT_ACTIVE, true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void cancelEdit() {
/* 177 */     if (this.editField.isVisible()) {
/* 178 */       onEditCanceled();
/* 179 */       this.label.setVisible(true);
/* 180 */       this.editField.setVisible(false);
/* 181 */       this.label.getModel().setHover(this.editField.getAnimationState().getAnimationState(Label.STATE_HOVER));
/* 182 */       getAnimationState().setAnimationState(STATE_EDIT_ACTIVE, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void cancelOrAcceptEdit() {
/* 187 */     if (this.editField.isVisible()) {
/* 188 */       if (this.acceptValueOnFocusLoss) {
/* 189 */         onEditEnd(this.editField.getText());
/*     */       }
/* 191 */       cancelEdit();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 197 */     super.applyTheme(themeInfo);
/* 198 */     applyThemeValueAdjuster(themeInfo);
/*     */   }
/*     */   
/*     */   protected void applyThemeValueAdjuster(ThemeInfo themeInfo) {
/* 202 */     this.width = themeInfo.getParameter("width", 100);
/* 203 */     this.displayPrefixTheme = themeInfo.getParameter("displayPrefix", "");
/* 204 */     this.useMouseWheel = themeInfo.getParameter("useMouseWheel", this.useMouseWheel);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/* 209 */     int minWidth = super.getMinWidth();
/* 210 */     minWidth = Math.max(minWidth, getBorderHorizontal() + this.decButton.getMinWidth() + Math.max(this.width, this.label.getMinWidth()) + this.incButton.getMinWidth());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     return minWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/* 220 */     int minHeight = this.label.getMinHeight();
/* 221 */     minHeight = Math.max(minHeight, this.decButton.getMinHeight());
/* 222 */     minHeight = Math.max(minHeight, this.incButton.getMinHeight());
/* 223 */     minHeight += getBorderVertical();
/* 224 */     return Math.max(minHeight, super.getMinHeight());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 229 */     return this.decButton.getPreferredWidth() + Math.max(this.width, this.label.getPreferredWidth()) + this.incButton.getPreferredWidth();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 236 */     return Math.max(Math.max(this.decButton.getPreferredHeight(), this.incButton.getPreferredHeight()), this.label.getPreferredHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyboardFocusLost() {
/* 244 */     this.wasInEditOnFocusLost = this.editField.isVisible();
/* 245 */     cancelOrAcceptEdit();
/* 246 */     this.label.getAnimationState().setAnimationState(STATE_KEYBOARD_FOCUS, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyboardFocusGained() {
/* 252 */     this.label.getAnimationState().setAnimationState(STATE_KEYBOARD_FOCUS, true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyboardFocusGained(FocusGainedCause cause, Widget previousWidget) {
/* 257 */     keyboardFocusGained();
/* 258 */     checkStartEditOnFocusGained(cause, previousWidget);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 263 */     super.setVisible(visible);
/* 264 */     if (!visible) {
/* 265 */       cancelEdit();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void widgetDisabled() {
/* 271 */     cancelEdit();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 276 */     int height = getInnerHeight();
/* 277 */     int y = getInnerY();
/* 278 */     this.decButton.setPosition(getInnerX(), y);
/* 279 */     this.decButton.setSize(this.decButton.getPreferredWidth(), height);
/* 280 */     this.incButton.setPosition(getInnerRight() - this.incButton.getPreferredWidth(), y);
/* 281 */     this.incButton.setSize(this.incButton.getPreferredWidth(), height);
/* 282 */     int labelX = this.decButton.getRight();
/* 283 */     int labelWidth = Math.max(0, this.incButton.getX() - labelX);
/* 284 */     this.label.setSize(labelWidth, height);
/* 285 */     this.label.setPosition(labelX, y);
/* 286 */     this.editField.setSize(labelWidth, height);
/* 287 */     this.editField.setPosition(labelX, y);
/*     */   }
/*     */   
/*     */   protected void setDisplayText() {
/* 291 */     String prefix = (this.displayPrefix != null) ? this.displayPrefix : this.displayPrefixTheme;
/* 292 */     this.label.setText(prefix.concat(formatText()));
/*     */   }
/*     */   
/*     */   protected abstract String formatText();
/*     */   
/*     */   void checkStartEditOnFocusGained(FocusGainedCause cause, Widget previousWidget) {
/* 298 */     if (cause == FocusGainedCause.FOCUS_KEY) {
/* 299 */       if (previousWidget != null && !(previousWidget instanceof ValueAdjuster)) {
/* 300 */         previousWidget = previousWidget.getParent();
/*     */       }
/* 302 */       if (previousWidget != this && previousWidget instanceof ValueAdjuster && 
/* 303 */         ((ValueAdjuster)previousWidget).wasInEditOnFocusLost) {
/* 304 */         startEdit();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void onTimer(int nextDelay) {
/* 311 */     this.timer.setDelay(nextDelay);
/* 312 */     if (this.incButton.getModel().isArmed()) {
/* 313 */       cancelEdit();
/* 314 */       doIncrement();
/* 315 */     } else if (this.decButton.getModel().isArmed()) {
/* 316 */       cancelEdit();
/* 317 */       doDecrement();
/*     */     } 
/*     */   }
/*     */   
/*     */   void updateTimer() {
/* 322 */     if (this.timer != null) {
/* 323 */       if (this.incButton.getModel().isArmed() || this.decButton.getModel().isArmed()) {
/* 324 */         if (!this.timer.isRunning()) {
/* 325 */           onTimer(300);
/* 326 */           this.timer.start();
/*     */         } 
/*     */       } else {
/* 329 */         this.timer.stop();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void afterAddToGUI(GUI gui) {
/* 336 */     super.afterAddToGUI(gui);
/* 337 */     this.timer = gui.createTimer();
/* 338 */     this.timer.setCallback(this.timerCallback);
/* 339 */     this.timer.setContinuous(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void beforeRemoveFromGUI(GUI gui) {
/* 344 */     super.beforeRemoveFromGUI(gui);
/* 345 */     if (this.timer != null) {
/* 346 */       this.timer.stop();
/*     */     }
/* 348 */     this.timer = null;
/*     */   } protected abstract String onEditStart(); protected abstract boolean onEditEnd(String paramString); protected abstract String validateEdit(String paramString); protected abstract void onEditCanceled();
/*     */   protected abstract boolean shouldStartEdit(char paramChar);
/*     */   protected abstract void onDragStart();
/*     */   protected boolean handleEvent(Event evt) {
/* 353 */     if (evt.isKeyEvent()) {
/* 354 */       if (evt.isKeyPressedEvent() && evt.getKeyCode() == 1 && this.listeners.dragActive) {
/* 355 */         this.listeners.dragActive = false;
/* 356 */         onDragCancelled();
/* 357 */         return true;
/*     */       } 
/* 359 */       if (!this.editField.isVisible()) {
/* 360 */         switch (evt.getType()) {
/*     */           case KEY_PRESSED:
/* 362 */             switch (evt.getKeyCode()) {
/*     */               case 205:
/* 364 */                 doIncrement();
/* 365 */                 return true;
/*     */               case 203:
/* 367 */                 doDecrement();
/* 368 */                 return true;
/*     */               case 28:
/*     */               case 57:
/* 371 */                 startEdit();
/* 372 */                 return true;
/*     */             } 
/* 374 */             if (evt.hasKeyCharNoModifiers() && shouldStartEdit(evt.getKeyChar())) {
/* 375 */               startEdit();
/* 376 */               this.editField.handleEvent(evt);
/* 377 */               return true;
/*     */             } 
/*     */             break;
/*     */         } 
/* 381 */         return false;
/*     */       } 
/* 383 */     } else if (!this.editField.isVisible() && this.useMouseWheel && evt.getType() == Event.Type.MOUSE_WHEEL) {
/* 384 */       if (evt.getMouseWheelDelta() < 0) {
/* 385 */         doDecrement();
/* 386 */       } else if (evt.getMouseWheelDelta() > 0) {
/* 387 */         doIncrement();
/*     */       } 
/* 389 */       return true;
/*     */     } 
/* 391 */     return super.handleEvent(evt);
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void onDragUpdate(int paramInt);
/*     */ 
/*     */   
/*     */   protected abstract void onDragCancelled();
/*     */ 
/*     */   
/*     */   protected void onDragEnd() {}
/*     */ 
/*     */   
/*     */   protected abstract void doDecrement();
/*     */   
/*     */   protected abstract void doIncrement();
/*     */   
/*     */   void handleEditCallback(int key) {
/* 409 */     switch (key) {
/*     */       case 28:
/* 411 */         if (onEditEnd(this.editField.getText())) {
/* 412 */           this.label.setVisible(true);
/* 413 */           this.editField.setVisible(false);
/*     */         } 
/*     */         return;
/*     */       
/*     */       case 1:
/* 418 */         cancelEdit();
/*     */         return;
/*     */     } 
/*     */     
/* 422 */     this.editField.setErrorMessage(validateEdit(this.editField.getText()));
/*     */   }
/*     */   
/*     */   protected abstract void syncWithModel();
/*     */   
/*     */   class ModelCallback
/*     */     implements Runnable {
/*     */     public void run() {
/* 430 */       ValueAdjuster.this.syncWithModel();
/*     */     } }
/*     */   
/*     */   class L implements Runnable, DraggableButton.DragListener, EditField.Callback {
/*     */     boolean dragActive;
/*     */     
/*     */     public void run() {
/* 437 */       ValueAdjuster.this.startEdit();
/*     */     }
/*     */     public void dragStarted() {
/* 440 */       this.dragActive = true;
/* 441 */       ValueAdjuster.this.onDragStart();
/*     */     }
/*     */     public void dragged(int deltaX, int deltaY) {
/* 444 */       if (this.dragActive)
/* 445 */         ValueAdjuster.this.onDragUpdate(deltaX); 
/*     */     }
/*     */     
/*     */     public void dragStopped() {
/* 449 */       this.dragActive = false;
/* 450 */       ValueAdjuster.this.onDragEnd();
/*     */     }
/*     */     public void callback(int key) {
/* 453 */       ValueAdjuster.this.handleEditCallback(key);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ValueAdjuster.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */