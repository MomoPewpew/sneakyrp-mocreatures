/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.IntegerModel;
/*     */ import de.matthiasmann.twl.renderer.Image;
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
/*     */ public class Scrollbar
/*     */   extends Widget
/*     */ {
/*     */   private static final int INITIAL_DELAY = 300;
/*     */   private static final int REPEAT_DELAY = 75;
/*     */   private final Orientation orientation;
/*     */   private final Button btnUpLeft;
/*     */   private final Button btnDownRight;
/*     */   private final DraggableButton thumb;
/*     */   private final L dragTimerCB;
/*     */   private Timer timer;
/*     */   private int trackClicked;
/*     */   private int trackClickLimit;
/*     */   private Runnable[] callbacks;
/*     */   private Image trackImageUpLeft;
/*     */   private Image trackImageDownRight;
/*     */   private IntegerModel model;
/*     */   private Runnable modelCB;
/*     */   private int pageSize;
/*     */   private int stepSize;
/*     */   private boolean scaleThumb;
/*     */   private int minValue;
/*     */   private int maxValue;
/*     */   private int value;
/*     */   
/*     */   public enum Orientation
/*     */   {
/*  55 */     HORIZONTAL,
/*  56 */     VERTICAL;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Scrollbar() {
/*  85 */     this(Orientation.VERTICAL);
/*     */   }
/*     */   
/*     */   public Scrollbar(Orientation orientation) {
/*  89 */     this.orientation = orientation;
/*  90 */     this.btnUpLeft = new Button();
/*  91 */     this.btnDownRight = new Button();
/*  92 */     this.thumb = new DraggableButton();
/*     */     
/*  94 */     Runnable cbUpdateTimer = new Runnable() {
/*     */         public void run() {
/*  96 */           Scrollbar.this.updateTimer();
/*     */         }
/*     */       };
/*     */     
/* 100 */     if (orientation == Orientation.HORIZONTAL) {
/* 101 */       setTheme("hscrollbar");
/* 102 */       this.btnUpLeft.setTheme("leftbutton");
/* 103 */       this.btnDownRight.setTheme("rightbutton");
/*     */     } else {
/* 105 */       setTheme("vscrollbar");
/* 106 */       this.btnUpLeft.setTheme("upbutton");
/* 107 */       this.btnDownRight.setTheme("downbutton");
/*     */     } 
/*     */     
/* 110 */     this.dragTimerCB = new L();
/*     */     
/* 112 */     this.btnUpLeft.setCanAcceptKeyboardFocus(false);
/* 113 */     this.btnUpLeft.getModel().addStateCallback(cbUpdateTimer);
/* 114 */     this.btnDownRight.setCanAcceptKeyboardFocus(false);
/* 115 */     this.btnDownRight.getModel().addStateCallback(cbUpdateTimer);
/* 116 */     this.thumb.setCanAcceptKeyboardFocus(false);
/* 117 */     this.thumb.setTheme("thumb");
/* 118 */     this.thumb.setListener(this.dragTimerCB);
/*     */     
/* 120 */     add(this.btnUpLeft);
/* 121 */     add(this.btnDownRight);
/* 122 */     add(this.thumb);
/*     */     
/* 124 */     this.pageSize = 10;
/* 125 */     this.stepSize = 1;
/* 126 */     this.maxValue = 100;
/*     */     
/* 128 */     setSize(30, 200);
/* 129 */     setDepthFocusTraversal(false);
/*     */   }
/*     */   
/*     */   public void addCallback(Runnable cb) {
/* 133 */     this.callbacks = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.callbacks, cb, Runnable.class);
/*     */   }
/*     */   
/*     */   public void removeCallback(Runnable cb) {
/* 137 */     this.callbacks = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, cb);
/*     */   }
/*     */   
/*     */   protected void doCallback() {
/* 141 */     CallbackSupport.fireCallbacks(this.callbacks);
/*     */   }
/*     */   
/*     */   public Orientation getOrientation() {
/* 145 */     return this.orientation;
/*     */   }
/*     */   
/*     */   public IntegerModel getModel() {
/* 149 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(IntegerModel model) {
/* 153 */     if (this.model != model) {
/* 154 */       if (this.model != null) {
/* 155 */         this.model.removeCallback(this.modelCB);
/*     */       }
/* 157 */       this.model = model;
/* 158 */       if (model != null) {
/* 159 */         if (this.modelCB == null) {
/* 160 */           this.modelCB = new Runnable() {
/*     */               public void run() {
/* 162 */                 Scrollbar.this.syncModel();
/*     */               }
/*     */             };
/*     */         }
/* 166 */         model.addCallback(this.modelCB);
/* 167 */         syncModel();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getValue() {
/* 173 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(int current) {
/* 177 */     setValue(current, true);
/*     */   }
/*     */   
/*     */   public void setValue(int value, boolean fireCallbacks) {
/* 181 */     value = range(value);
/* 182 */     int oldValue = this.value;
/* 183 */     if (oldValue != value) {
/* 184 */       this.value = value;
/* 185 */       setThumbPos();
/* 186 */       firePropertyChange("value", oldValue, value);
/* 187 */       if (this.model != null) {
/* 188 */         this.model.setValue(value);
/*     */       }
/* 190 */       if (fireCallbacks) {
/* 191 */         doCallback();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void scroll(int amount) {
/* 197 */     if (this.minValue < this.maxValue) {
/* 198 */       setValue(this.value + amount);
/*     */     } else {
/* 200 */       setValue(this.value - amount);
/*     */     } 
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
/*     */   public void scrollToArea(int start, int size, int extra) {
/* 213 */     if (size <= 0) {
/*     */       return;
/*     */     }
/* 216 */     if (extra < 0) {
/* 217 */       extra = 0;
/*     */     }
/*     */     
/* 220 */     int end = start + size;
/* 221 */     start = range(start);
/* 222 */     int pos = this.value;
/*     */     
/* 224 */     int startWithExtra = range(start - extra);
/* 225 */     if (startWithExtra < pos) {
/* 226 */       pos = startWithExtra;
/*     */     }
/* 228 */     int pageEnd = pos + this.pageSize;
/* 229 */     int endWithExtra = end + extra;
/* 230 */     if (endWithExtra > pageEnd) {
/* 231 */       pos = range(endWithExtra - this.pageSize);
/* 232 */       if (pos > startWithExtra) {
/* 233 */         size = end - start;
/* 234 */         pos = start - Math.max(0, this.pageSize - size) / 2;
/*     */       } 
/*     */     } 
/*     */     
/* 238 */     setValue(pos);
/*     */   }
/*     */   
/*     */   public int getMinValue() {
/* 242 */     return this.minValue;
/*     */   }
/*     */   
/*     */   public int getMaxValue() {
/* 246 */     return this.maxValue;
/*     */   }
/*     */   
/*     */   public void setMinMaxValue(int minValue, int maxValue) {
/* 250 */     if (maxValue < minValue) {
/* 251 */       throw new IllegalArgumentException("maxValue < minValue");
/*     */     }
/* 253 */     this.minValue = minValue;
/* 254 */     this.maxValue = maxValue;
/* 255 */     this.value = range(this.value);
/* 256 */     setThumbPos();
/* 257 */     this.thumb.setVisible((minValue != maxValue));
/*     */   }
/*     */   
/*     */   public int getPageSize() {
/* 261 */     return this.pageSize;
/*     */   }
/*     */   
/*     */   public void setPageSize(int pageSize) {
/* 265 */     if (pageSize < 1) {
/* 266 */       throw new IllegalArgumentException("pageSize < 1");
/*     */     }
/* 268 */     this.pageSize = pageSize;
/* 269 */     if (this.scaleThumb) {
/* 270 */       setThumbPos();
/*     */     }
/*     */   }
/*     */   
/*     */   public int getStepSize() {
/* 275 */     return this.stepSize;
/*     */   }
/*     */   
/*     */   public void setStepSize(int stepSize) {
/* 279 */     if (stepSize < 1) {
/* 280 */       throw new IllegalArgumentException("stepSize < 1");
/*     */     }
/* 282 */     this.stepSize = stepSize;
/*     */   }
/*     */   
/*     */   public boolean isScaleThumb() {
/* 286 */     return this.scaleThumb;
/*     */   }
/*     */   
/*     */   public void setScaleThumb(boolean scaleThumb) {
/* 290 */     this.scaleThumb = scaleThumb;
/* 291 */     setThumbPos();
/*     */   }
/*     */   
/*     */   public void externalDragStart() {
/* 295 */     this.thumb.getAnimationState().setAnimationState(Button.STATE_PRESSED, true);
/* 296 */     this.dragTimerCB.dragStarted();
/*     */   }
/*     */   
/*     */   public void externalDragged(int deltaX, int deltaY) {
/* 300 */     this.dragTimerCB.dragged(deltaX, deltaY);
/*     */   }
/*     */ 
/*     */   
/*     */   public void externalDragStopped() {
/* 305 */     this.thumb.getAnimationState().setAnimationState(Button.STATE_PRESSED, false);
/*     */   }
/*     */   
/*     */   public boolean isUpLeftButtonArmed() {
/* 309 */     return this.btnUpLeft.getModel().isArmed();
/*     */   }
/*     */   
/*     */   public boolean isDownRightButtonArmed() {
/* 313 */     return this.btnDownRight.getModel().isArmed();
/*     */   }
/*     */   
/*     */   public boolean isThumbDragged() {
/* 317 */     return this.thumb.getModel().isPressed();
/*     */   }
/*     */   
/*     */   public void setThumbTooltipContent(Object tooltipContent) {
/* 321 */     this.thumb.setTooltipContent(tooltipContent);
/*     */   }
/*     */   
/*     */   public Object getThumbTooltipContent() {
/* 325 */     return this.thumb.getTooltipContent();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 330 */     super.applyTheme(themeInfo);
/* 331 */     applyThemeScrollbar(themeInfo);
/*     */   }
/*     */   
/*     */   protected void applyThemeScrollbar(ThemeInfo themeInfo) {
/* 335 */     setScaleThumb(themeInfo.getParameter("scaleThumb", false));
/* 336 */     if (this.orientation == Orientation.HORIZONTAL) {
/* 337 */       this.trackImageUpLeft = themeInfo.<Image>getParameterValue("trackImageLeft", false, Image.class);
/* 338 */       this.trackImageDownRight = themeInfo.<Image>getParameterValue("trackImageRight", false, Image.class);
/*     */     } else {
/* 340 */       this.trackImageUpLeft = themeInfo.<Image>getParameterValue("trackImageUp", false, Image.class);
/* 341 */       this.trackImageDownRight = themeInfo.<Image>getParameterValue("trackImageDown", false, Image.class);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintWidget(GUI gui) {
/* 347 */     int x = getInnerX();
/* 348 */     int y = getInnerY();
/* 349 */     if (this.orientation == Orientation.HORIZONTAL) {
/* 350 */       int h = getInnerHeight();
/* 351 */       if (this.trackImageUpLeft != null) {
/* 352 */         this.trackImageUpLeft.draw(getAnimationState(), x, y, this.thumb.getX() - x, h);
/*     */       }
/* 354 */       if (this.trackImageDownRight != null) {
/* 355 */         int thumbRight = this.thumb.getRight();
/* 356 */         this.trackImageDownRight.draw(getAnimationState(), thumbRight, y, getInnerRight() - thumbRight, h);
/*     */       } 
/*     */     } else {
/* 359 */       int w = getInnerWidth();
/* 360 */       if (this.trackImageUpLeft != null) {
/* 361 */         this.trackImageUpLeft.draw(getAnimationState(), x, y, w, this.thumb.getY() - y);
/*     */       }
/* 363 */       if (this.trackImageDownRight != null) {
/* 364 */         int thumbBottom = this.thumb.getBottom();
/* 365 */         this.trackImageDownRight.draw(getAnimationState(), x, thumbBottom, w, getInnerBottom() - thumbBottom);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void afterAddToGUI(GUI gui) {
/* 372 */     super.afterAddToGUI(gui);
/* 373 */     this.timer = gui.createTimer();
/* 374 */     this.timer.setCallback(this.dragTimerCB);
/* 375 */     this.timer.setContinuous(true);
/* 376 */     if (this.model != null)
/*     */     {
/* 378 */       this.model.addCallback(this.modelCB);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void beforeRemoveFromGUI(GUI gui) {
/* 384 */     super.beforeRemoveFromGUI(gui);
/* 385 */     if (this.model != null) {
/* 386 */       this.model.removeCallback(this.modelCB);
/*     */     }
/* 388 */     if (this.timer != null) {
/* 389 */       this.timer.stop();
/*     */     }
/* 391 */     this.timer = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean handleEvent(Event evt) {
/* 396 */     if (evt.getType() == Event.Type.MOUSE_BTNUP && evt.getMouseButton() == 0) {
/*     */       
/* 398 */       this.trackClicked = 0;
/* 399 */       updateTimer();
/*     */     } 
/*     */     
/* 402 */     if (!super.handleEvent(evt) && 
/* 403 */       evt.getType() == Event.Type.MOUSE_BTNDOWN && evt.getMouseButton() == 0)
/*     */     {
/* 405 */       if (isMouseInside(evt)) {
/* 406 */         if (this.orientation == Orientation.HORIZONTAL) {
/* 407 */           this.trackClickLimit = evt.getMouseX();
/* 408 */           if (evt.getMouseX() < this.thumb.getX()) {
/* 409 */             this.trackClicked = -1;
/*     */           } else {
/* 411 */             this.trackClicked = 1;
/*     */           } 
/*     */         } else {
/* 414 */           this.trackClickLimit = evt.getMouseY();
/* 415 */           if (evt.getMouseY() < this.thumb.getY()) {
/* 416 */             this.trackClicked = -1;
/*     */           } else {
/* 418 */             this.trackClicked = 1;
/*     */           } 
/*     */         } 
/* 421 */         updateTimer();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 426 */     boolean page = ((evt.getModifiers() & 0x24) != 0);
/* 427 */     int step = page ? this.pageSize : this.stepSize;
/*     */     
/* 429 */     if (evt.getType() == Event.Type.KEY_PRESSED) {
/* 430 */       switch (evt.getKeyCode()) {
/*     */         case 203:
/* 432 */           if (this.orientation == Orientation.HORIZONTAL) {
/* 433 */             setValue(this.value - step);
/* 434 */             return true;
/*     */           } 
/*     */           break;
/*     */         case 205:
/* 438 */           if (this.orientation == Orientation.HORIZONTAL) {
/* 439 */             setValue(this.value + step);
/* 440 */             return true;
/*     */           } 
/*     */           break;
/*     */         case 200:
/* 444 */           if (this.orientation == Orientation.VERTICAL) {
/* 445 */             setValue(this.value - step);
/* 446 */             return true;
/*     */           } 
/*     */           break;
/*     */         case 208:
/* 450 */           if (this.orientation == Orientation.VERTICAL) {
/* 451 */             setValue(this.value + step);
/* 452 */             return true;
/*     */           } 
/*     */           break;
/*     */         case 201:
/* 456 */           if (this.orientation == Orientation.VERTICAL) {
/* 457 */             setValue(this.value - this.pageSize);
/* 458 */             return true;
/*     */           } 
/*     */           break;
/*     */         case 209:
/* 462 */           if (this.orientation == Orientation.VERTICAL) {
/* 463 */             setValue(this.value + this.pageSize);
/* 464 */             return true;
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     
/*     */     }
/* 470 */     if (evt.getType() == Event.Type.MOUSE_WHEEL) {
/* 471 */       setValue(this.value - step * evt.getMouseWheelDelta());
/*     */     }
/*     */ 
/*     */     
/* 475 */     return evt.isMouseEvent();
/*     */   }
/*     */   
/*     */   int range(int current) {
/* 479 */     if (this.minValue < this.maxValue) {
/* 480 */       if (current < this.minValue) {
/* 481 */         current = this.minValue;
/* 482 */       } else if (current > this.maxValue) {
/* 483 */         current = this.maxValue;
/*     */       }
/*     */     
/* 486 */     } else if (current > this.minValue) {
/* 487 */       current = this.minValue;
/* 488 */     } else if (current < this.maxValue) {
/* 489 */       current = this.maxValue;
/*     */     } 
/*     */     
/* 492 */     return current;
/*     */   }
/*     */   
/*     */   void onTimer(int nextDelay) {
/* 496 */     this.timer.setDelay(nextDelay);
/* 497 */     if (this.trackClicked != 0) {
/*     */       int thumbPos;
/* 499 */       if (this.orientation == Orientation.HORIZONTAL) {
/* 500 */         thumbPos = this.thumb.getX();
/*     */       } else {
/* 502 */         thumbPos = this.thumb.getY();
/*     */       } 
/* 504 */       if ((this.trackClickLimit - thumbPos) * this.trackClicked > 0) {
/* 505 */         scroll(this.trackClicked * this.pageSize);
/*     */       }
/* 507 */     } else if (this.btnUpLeft.getModel().isArmed()) {
/* 508 */       scroll(-this.stepSize);
/* 509 */     } else if (this.btnDownRight.getModel().isArmed()) {
/* 510 */       scroll(this.stepSize);
/*     */     } 
/*     */   }
/*     */   
/*     */   void updateTimer() {
/* 515 */     if (this.timer != null) {
/* 516 */       if (this.trackClicked != 0 || this.btnUpLeft.getModel().isArmed() || this.btnDownRight.getModel().isArmed()) {
/*     */ 
/*     */         
/* 519 */         if (!this.timer.isRunning()) {
/* 520 */           onTimer(300);
/*     */ 
/*     */           
/* 523 */           if (this.timer != null) {
/* 524 */             this.timer.start();
/*     */           }
/*     */         } 
/*     */       } else {
/* 528 */         this.timer.stop();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   void syncModel() {
/* 534 */     setMinMaxValue(this.model.getMinValue(), this.model.getMaxValue());
/* 535 */     setValue(this.model.getValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/* 540 */     if (this.orientation == Orientation.HORIZONTAL) {
/* 541 */       return Math.max(super.getMinWidth(), this.btnUpLeft.getMinWidth() + this.thumb.getMinWidth() + this.btnDownRight.getMinWidth());
/*     */     }
/* 543 */     return Math.max(super.getMinWidth(), this.thumb.getMinWidth());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/* 549 */     if (this.orientation == Orientation.HORIZONTAL) {
/* 550 */       return Math.max(super.getMinHeight(), this.thumb.getMinHeight());
/*     */     }
/* 552 */     return Math.max(super.getMinHeight(), this.btnUpLeft.getMinHeight() + this.thumb.getMinHeight() + this.btnDownRight.getMinHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPreferredWidth() {
/* 558 */     return getMinWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredHeight() {
/* 563 */     return getMinHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 568 */     if (this.orientation == Orientation.HORIZONTAL) {
/* 569 */       this.btnUpLeft.setSize(this.btnUpLeft.getPreferredWidth(), getHeight());
/* 570 */       this.btnUpLeft.setPosition(getX(), getY());
/* 571 */       this.btnDownRight.setSize(this.btnUpLeft.getPreferredWidth(), getHeight());
/* 572 */       this.btnDownRight.setPosition(getX() + getWidth() - this.btnDownRight.getWidth(), getY());
/*     */     } else {
/* 574 */       this.btnUpLeft.setSize(getWidth(), this.btnUpLeft.getPreferredHeight());
/* 575 */       this.btnUpLeft.setPosition(getX(), getY());
/* 576 */       this.btnDownRight.setSize(getWidth(), this.btnDownRight.getPreferredHeight());
/* 577 */       this.btnDownRight.setPosition(getX(), getY() + getHeight() - this.btnDownRight.getHeight());
/*     */     } 
/* 579 */     setThumbPos();
/*     */   }
/*     */   
/*     */   int calcThumbArea() {
/* 583 */     if (this.orientation == Orientation.HORIZONTAL) {
/* 584 */       return Math.max(1, getWidth() - this.btnUpLeft.getWidth() - this.thumb.getWidth() - this.btnDownRight.getWidth());
/*     */     }
/* 586 */     return Math.max(1, getHeight() - this.btnUpLeft.getHeight() - this.thumb.getHeight() - this.btnDownRight.getHeight());
/*     */   }
/*     */ 
/*     */   
/*     */   private void setThumbPos() {
/* 591 */     int delta = this.maxValue - this.minValue;
/* 592 */     if (this.orientation == Orientation.HORIZONTAL) {
/* 593 */       int thumbWidth = this.thumb.getPreferredWidth();
/* 594 */       if (this.scaleThumb) {
/* 595 */         long availArea = Math.max(1, getWidth() - this.btnUpLeft.getWidth() - this.btnDownRight.getWidth());
/* 596 */         thumbWidth = (int)Math.max(thumbWidth, availArea * this.pageSize / (this.pageSize + delta + 1));
/*     */       } 
/* 598 */       this.thumb.setSize(thumbWidth, getHeight());
/*     */       
/* 600 */       int xpos = this.btnUpLeft.getX() + this.btnUpLeft.getWidth();
/* 601 */       if (delta != 0) {
/* 602 */         xpos += (this.value - this.minValue) * calcThumbArea() / delta;
/*     */       }
/* 604 */       this.thumb.setPosition(xpos, getY());
/*     */     } else {
/* 606 */       int thumbHeight = this.thumb.getPreferredHeight();
/* 607 */       if (this.scaleThumb) {
/* 608 */         long availArea = Math.max(1, getHeight() - this.btnUpLeft.getHeight() - this.btnDownRight.getHeight());
/* 609 */         thumbHeight = (int)Math.max(thumbHeight, availArea * this.pageSize / (this.pageSize + delta + 1));
/*     */       } 
/* 611 */       this.thumb.setSize(getWidth(), thumbHeight);
/*     */       
/* 613 */       int ypos = this.btnUpLeft.getY() + this.btnUpLeft.getHeight();
/* 614 */       if (delta != 0) {
/* 615 */         ypos += (this.value - this.minValue) * calcThumbArea() / delta;
/*     */       }
/* 617 */       this.thumb.setPosition(getX(), ypos);
/*     */     } 
/*     */   }
/*     */   
/*     */   final class L
/*     */     implements DraggableButton.DragListener, Runnable {
/*     */     public void dragStarted() {
/* 624 */       this.startValue = Scrollbar.this.getValue();
/*     */     } private int startValue;
/*     */     public void dragged(int deltaX, int deltaY) {
/*     */       int mouseDelta;
/* 628 */       if (Scrollbar.this.getOrientation() == Scrollbar.Orientation.HORIZONTAL) {
/* 629 */         mouseDelta = deltaX;
/*     */       } else {
/* 631 */         mouseDelta = deltaY;
/*     */       } 
/* 633 */       int delta = (Scrollbar.this.getMaxValue() - Scrollbar.this.getMinValue()) * mouseDelta / Scrollbar.this.calcThumbArea();
/* 634 */       int newValue = Scrollbar.this.range(this.startValue + delta);
/* 635 */       Scrollbar.this.setValue(newValue);
/*     */     }
/*     */     public void dragStopped() {}
/*     */     
/*     */     public void run() {
/* 640 */       Scrollbar.this.onTimer(75);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Scrollbar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */