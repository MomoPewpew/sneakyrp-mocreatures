/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.MouseCursor;
/*     */ import de.matthiasmann.twl.utils.TintAnimator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResizableFrame
/*     */   extends Widget
/*     */ {
/*     */   private String title;
/*     */   private final MouseCursor[] cursors;
/*  50 */   public static final AnimationState.StateKey STATE_FADE = AnimationState.StateKey.get("fade");
/*     */   
/*     */   public enum ResizableAxis {
/*  53 */     NONE(false, false),
/*  54 */     HORIZONTAL(true, false),
/*  55 */     VERTICAL(false, true),
/*  56 */     BOTH(true, true);
/*     */     final boolean allowX;
/*     */     final boolean allowY;
/*     */     
/*     */     ResizableAxis(boolean allowX, boolean allowY) {
/*  61 */       this.allowX = allowX;
/*  62 */       this.allowY = allowY;
/*     */     }
/*     */   }
/*     */   
/*     */   private enum DragMode {
/*  67 */     NONE("mouseCursor"),
/*  68 */     EDGE_LEFT("mouseCursor.left"),
/*  69 */     EDGE_TOP("mouseCursor.top"),
/*  70 */     EDGE_RIGHT("mouseCursor.right"),
/*  71 */     EDGE_BOTTOM("mouseCursor.bottom"),
/*  72 */     CORNER_TL("mouseCursor.top-left"),
/*  73 */     CORNER_TR("mouseCursor.top-right"),
/*  74 */     CORNER_BR("mouseCursor.bottom-right"),
/*  75 */     CORNER_BL("mouseCursor.bottom-left"),
/*  76 */     POSITION("mouseCursor.all");
/*     */     final String cursorName;
/*     */     
/*     */     DragMode(String cursorName) {
/*  80 */       this.cursorName = cursorName;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   private ResizableAxis resizableAxis = ResizableAxis.BOTH;
/*     */   private boolean draggable = true;
/*  89 */   private DragMode dragMode = DragMode.NONE;
/*     */   
/*     */   private int dragStartX;
/*     */   private int dragStartY;
/*     */   private int dragInitialLeft;
/*     */   private int dragInitialTop;
/*     */   private int dragInitialRight;
/*     */   private int dragInitialBottom;
/*  97 */   private Color fadeColorInactive = Color.WHITE;
/*     */   
/*     */   private int fadeDurationActivate;
/*     */   
/*     */   private int fadeDurationDeactivate;
/*     */   
/*     */   private int fadeDurationShow;
/*     */   private int fadeDurationHide;
/*     */   private TextWidget titleWidget;
/*     */   private int titleAreaTop;
/*     */   private int titleAreaLeft;
/*     */   private int titleAreaRight;
/*     */   private int titleAreaBottom;
/*     */   private boolean hasCloseButton;
/*     */   private Button closeButton;
/*     */   private int closeButtonX;
/*     */   private int closeButtonY;
/*     */   private boolean hasResizeHandle;
/*     */   private Widget resizeHandle;
/*     */   private int resizeHandleX;
/*     */   private int resizeHandleY;
/*     */   private DragMode resizeHandleDragMode;
/*     */   
/*     */   public ResizableFrame() {
/* 121 */     this.title = "";
/* 122 */     this.cursors = new MouseCursor[(DragMode.values()).length];
/* 123 */     setCanAcceptKeyboardFocus(true);
/*     */   }
/*     */   
/*     */   public String getTitle() {
/* 127 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/* 131 */     this.title = title;
/* 132 */     if (this.titleWidget != null) {
/* 133 */       this.titleWidget.setCharSequence(title);
/*     */     }
/*     */   }
/*     */   
/*     */   public ResizableAxis getResizableAxis() {
/* 138 */     return this.resizableAxis;
/*     */   }
/*     */   
/*     */   public void setResizableAxis(ResizableAxis resizableAxis) {
/* 142 */     if (resizableAxis == null) {
/* 143 */       throw new NullPointerException("resizableAxis");
/*     */     }
/* 145 */     this.resizableAxis = resizableAxis;
/* 146 */     if (this.resizeHandle != null) {
/* 147 */       layoutResizeHandle();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isDraggable() {
/* 152 */     return this.draggable;
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
/*     */   public void setDraggable(boolean movable) {
/* 165 */     this.draggable = movable;
/*     */   }
/*     */   
/*     */   public boolean hasTitleBar() {
/* 169 */     return (this.titleWidget != null && this.titleWidget.getParent() == this);
/*     */   }
/*     */   
/*     */   public void addCloseCallback(Runnable cb) {
/* 173 */     if (this.closeButton == null) {
/* 174 */       this.closeButton = new Button();
/* 175 */       this.closeButton.setTheme("closeButton");
/* 176 */       this.closeButton.setCanAcceptKeyboardFocus(false);
/* 177 */       add(this.closeButton);
/* 178 */       layoutCloseButton();
/*     */     } 
/* 180 */     this.closeButton.setVisible(this.hasCloseButton);
/* 181 */     this.closeButton.addCallback(cb);
/*     */   }
/*     */   
/*     */   public void removeCloseCallback(Runnable cb) {
/* 185 */     if (this.closeButton != null) {
/* 186 */       this.closeButton.removeCallback(cb);
/* 187 */       this.closeButton.setVisible(this.closeButton.hasCallbacks());
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getFadeDurationActivate() {
/* 192 */     return this.fadeDurationActivate;
/*     */   }
/*     */   
/*     */   public int getFadeDurationDeactivate() {
/* 196 */     return this.fadeDurationDeactivate;
/*     */   }
/*     */   
/*     */   public int getFadeDurationHide() {
/* 200 */     return this.fadeDurationHide;
/*     */   }
/*     */   
/*     */   public int getFadeDurationShow() {
/* 204 */     return this.fadeDurationShow;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 210 */     if (visible) {
/* 211 */       TintAnimator tintAnimator = getTintAnimator();
/* 212 */       if ((tintAnimator != null && tintAnimator.hasTint()) || !isVisible()) {
/* 213 */         fadeTo(hasKeyboardFocus() ? Color.WHITE : this.fadeColorInactive, this.fadeDurationShow);
/*     */       }
/* 215 */     } else if (isVisible()) {
/* 216 */       fadeToHide(this.fadeDurationHide);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHardVisible(boolean visible) {
/* 226 */     super.setVisible(visible);
/*     */   }
/*     */   
/*     */   protected void applyThemeResizableFrame(ThemeInfo themeInfo) {
/* 230 */     for (DragMode m : DragMode.values()) {
/* 231 */       this.cursors[m.ordinal()] = themeInfo.getMouseCursor(m.cursorName);
/*     */     }
/* 233 */     this.titleAreaTop = themeInfo.getParameter("titleAreaTop", 0);
/* 234 */     this.titleAreaLeft = themeInfo.getParameter("titleAreaLeft", 0);
/* 235 */     this.titleAreaRight = themeInfo.getParameter("titleAreaRight", 0);
/* 236 */     this.titleAreaBottom = themeInfo.getParameter("titleAreaBottom", 0);
/* 237 */     this.closeButtonX = themeInfo.getParameter("closeButtonX", 0);
/* 238 */     this.closeButtonY = themeInfo.getParameter("closeButtonY", 0);
/* 239 */     this.hasCloseButton = themeInfo.getParameter("hasCloseButton", false);
/* 240 */     this.hasResizeHandle = themeInfo.getParameter("hasResizeHandle", false);
/* 241 */     this.resizeHandleX = themeInfo.getParameter("resizeHandleX", 0);
/* 242 */     this.resizeHandleY = themeInfo.getParameter("resizeHandleY", 0);
/* 243 */     this.fadeColorInactive = themeInfo.getParameter("fadeColorInactive", Color.WHITE);
/* 244 */     this.fadeDurationActivate = themeInfo.getParameter("fadeDurationActivate", 0);
/* 245 */     this.fadeDurationDeactivate = themeInfo.getParameter("fadeDurationDeactivate", 0);
/* 246 */     this.fadeDurationShow = themeInfo.getParameter("fadeDurationShow", 0);
/* 247 */     this.fadeDurationHide = themeInfo.getParameter("fadeDurationHide", 0);
/* 248 */     invalidateLayout();
/*     */     
/* 250 */     if (isVisible() && !hasKeyboardFocus() && (getTintAnimator() != null || !Color.WHITE.equals(this.fadeColorInactive)))
/*     */     {
/* 252 */       fadeTo(this.fadeColorInactive, 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 258 */     super.applyTheme(themeInfo);
/* 259 */     applyThemeResizableFrame(themeInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateTintAnimation() {
/* 264 */     TintAnimator tintAnimator = getTintAnimator();
/* 265 */     tintAnimator.update();
/* 266 */     if (!tintAnimator.isFadeActive() && tintAnimator.isZeroAlpha()) {
/* 267 */       setHardVisible(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fadeTo(Color color, int duration) {
/* 273 */     allocateTint().fadeTo(color, duration);
/* 274 */     if (!isVisible() && color.getAlpha() != 0) {
/* 275 */       setHardVisible(true);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void fadeToHide(int duration) {
/* 280 */     if (duration <= 0) {
/* 281 */       setHardVisible(false);
/*     */     } else {
/* 283 */       allocateTint().fadeToHide(duration);
/*     */     } 
/*     */   }
/*     */   
/*     */   private TintAnimator allocateTint() {
/* 288 */     TintAnimator tintAnimator = getTintAnimator();
/* 289 */     if (tintAnimator == null) {
/* 290 */       tintAnimator = new TintAnimator((TintAnimator.TimeSource)new TintAnimator.AnimationStateTimeSource(getAnimationState(), STATE_FADE));
/* 291 */       setTintAnimator(tintAnimator);
/* 292 */       if (!isVisible())
/*     */       {
/* 294 */         tintAnimator.fadeToHide(0);
/*     */       }
/*     */     } 
/* 297 */     return tintAnimator;
/*     */   }
/*     */   
/*     */   protected boolean isFrameElement(Widget widget) {
/* 301 */     return (widget == this.titleWidget || widget == this.closeButton || widget == this.resizeHandle);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 306 */     int minWidth = getMinWidth();
/* 307 */     int minHeight = getMinHeight();
/* 308 */     if (getWidth() < minWidth || getHeight() < minHeight) {
/* 309 */       int width = Math.max(getWidth(), minWidth);
/* 310 */       int height = Math.max(getHeight(), minHeight);
/* 311 */       if (getParent() != null) {
/* 312 */         int x = Math.min(getX(), getParent().getInnerRight() - width);
/* 313 */         int y = Math.min(getY(), getParent().getInnerBottom() - height);
/* 314 */         setPosition(x, y);
/*     */       } 
/* 316 */       setSize(width, height);
/*     */     } 
/*     */     
/* 319 */     for (int i = 0, n = getNumChildren(); i < n; i++) {
/* 320 */       Widget child = getChild(i);
/* 321 */       if (!isFrameElement(child)) {
/* 322 */         layoutChildFullInnerArea(child);
/*     */       }
/*     */     } 
/*     */     
/* 326 */     layoutTitle();
/* 327 */     layoutCloseButton();
/* 328 */     layoutResizeHandle();
/*     */   }
/*     */   
/*     */   protected void layoutTitle() {
/* 332 */     int titleX = getTitleX(this.titleAreaLeft);
/* 333 */     int titleY = getTitleY(this.titleAreaTop);
/* 334 */     int titleWidth = Math.max(0, getTitleX(this.titleAreaRight) - titleX);
/* 335 */     int titleHeight = Math.max(0, getTitleY(this.titleAreaBottom) - titleY);
/*     */     
/* 337 */     if (this.titleAreaLeft != this.titleAreaRight && this.titleAreaTop != this.titleAreaBottom) {
/* 338 */       if (this.titleWidget == null) {
/* 339 */         this.titleWidget = new TextWidget(getAnimationState());
/* 340 */         this.titleWidget.setTheme("title");
/* 341 */         this.titleWidget.setMouseCursor(this.cursors[DragMode.POSITION.ordinal()]);
/* 342 */         this.titleWidget.setCharSequence(this.title);
/* 343 */         this.titleWidget.setClip(true);
/*     */       } 
/* 345 */       if (this.titleWidget.getParent() == null) {
/* 346 */         insertChild(this.titleWidget, 0);
/*     */       }
/*     */       
/* 349 */       this.titleWidget.setPosition(titleX, titleY);
/* 350 */       this.titleWidget.setSize(titleWidth, titleHeight);
/* 351 */     } else if (this.titleWidget != null && this.titleWidget.getParent() == this) {
/* 352 */       this.titleWidget.destroy();
/* 353 */       removeChild(this.titleWidget);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void layoutCloseButton() {
/* 358 */     if (this.closeButton != null) {
/* 359 */       this.closeButton.adjustSize();
/* 360 */       this.closeButton.setPosition(getTitleX(this.closeButtonX), getTitleY(this.closeButtonY));
/*     */ 
/*     */       
/* 363 */       this.closeButton.setVisible((this.closeButton.hasCallbacks() && this.hasCloseButton));
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void layoutResizeHandle() {
/* 368 */     if (this.hasResizeHandle && this.resizeHandle == null) {
/* 369 */       this.resizeHandle = new Widget(getAnimationState(), true);
/* 370 */       this.resizeHandle.setTheme("resizeHandle");
/* 371 */       insertChild(this.resizeHandle, 0);
/*     */     } 
/* 373 */     if (this.resizeHandle != null) {
/* 374 */       if (this.resizeHandleX > 0) {
/* 375 */         if (this.resizeHandleY > 0) {
/* 376 */           this.resizeHandleDragMode = DragMode.CORNER_TL;
/*     */         } else {
/* 378 */           this.resizeHandleDragMode = DragMode.CORNER_TR;
/*     */         } 
/* 380 */       } else if (this.resizeHandleY > 0) {
/* 381 */         this.resizeHandleDragMode = DragMode.CORNER_BL;
/*     */       } else {
/* 383 */         this.resizeHandleDragMode = DragMode.CORNER_BR;
/*     */       } 
/*     */       
/* 386 */       this.resizeHandle.adjustSize();
/* 387 */       this.resizeHandle.setPosition(getTitleX(this.resizeHandleX), getTitleY(this.resizeHandleY));
/*     */ 
/*     */       
/* 390 */       this.resizeHandle.setVisible((this.hasResizeHandle && this.resizableAxis == ResizableAxis.BOTH));
/*     */     } else {
/*     */       
/* 393 */       this.resizeHandleDragMode = DragMode.NONE;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyboardFocusGained() {
/* 399 */     fadeTo(Color.WHITE, this.fadeDurationActivate);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyboardFocusLost() {
/* 404 */     if (!hasOpenPopups() && isVisible()) {
/* 405 */       fadeTo(this.fadeColorInactive, this.fadeDurationDeactivate);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/* 411 */     int minWidth = super.getMinWidth();
/* 412 */     for (int i = 0, n = getNumChildren(); i < n; i++) {
/* 413 */       Widget child = getChild(i);
/* 414 */       if (!isFrameElement(child)) {
/* 415 */         minWidth = Math.max(minWidth, child.getMinWidth() + getBorderHorizontal());
/*     */       }
/*     */     } 
/* 418 */     if (hasTitleBar() && this.titleAreaRight < 0) {
/* 419 */       minWidth = Math.max(minWidth, this.titleWidget.getPreferredWidth() + this.titleAreaLeft - this.titleAreaRight);
/*     */     }
/* 421 */     return minWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/* 426 */     int minHeight = super.getMinHeight();
/* 427 */     for (int i = 0, n = getNumChildren(); i < n; i++) {
/* 428 */       Widget child = getChild(i);
/* 429 */       if (!isFrameElement(child)) {
/* 430 */         minHeight = Math.max(minHeight, child.getMinHeight() + getBorderVertical());
/*     */       }
/*     */     } 
/* 433 */     return minHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxWidth() {
/* 438 */     int maxWidth = super.getMaxWidth();
/* 439 */     for (int i = 0, n = getNumChildren(); i < n; i++) {
/* 440 */       Widget child = getChild(i);
/* 441 */       if (!isFrameElement(child)) {
/* 442 */         int aMaxWidth = child.getMaxWidth();
/* 443 */         if (aMaxWidth > 0) {
/* 444 */           aMaxWidth += getBorderHorizontal();
/* 445 */           if (maxWidth == 0 || aMaxWidth < maxWidth) {
/* 446 */             maxWidth = aMaxWidth;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 451 */     return maxWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxHeight() {
/* 456 */     int maxHeight = super.getMaxHeight();
/* 457 */     for (int i = 0, n = getNumChildren(); i < n; i++) {
/* 458 */       Widget child = getChild(i);
/* 459 */       if (!isFrameElement(child)) {
/* 460 */         int aMaxHeight = child.getMaxHeight();
/* 461 */         if (aMaxHeight > 0) {
/* 462 */           aMaxHeight += getBorderVertical();
/* 463 */           if (maxHeight == 0 || aMaxHeight < maxHeight) {
/* 464 */             maxHeight = aMaxHeight;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 469 */     return maxHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 474 */     int prefWidth = 0;
/* 475 */     for (int i = 0, n = getNumChildren(); i < n; i++) {
/* 476 */       Widget child = getChild(i);
/* 477 */       if (!isFrameElement(child)) {
/* 478 */         prefWidth = Math.max(prefWidth, child.getPreferredWidth());
/*     */       }
/*     */     } 
/* 481 */     return prefWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredWidth() {
/* 486 */     int prefWidth = super.getPreferredWidth();
/* 487 */     if (hasTitleBar() && this.titleAreaRight < 0) {
/* 488 */       prefWidth = Math.max(prefWidth, this.titleWidget.getPreferredWidth() + this.titleAreaLeft - this.titleAreaRight);
/*     */     }
/* 490 */     return prefWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 495 */     int prefHeight = 0;
/* 496 */     for (int i = 0, n = getNumChildren(); i < n; i++) {
/* 497 */       Widget child = getChild(i);
/* 498 */       if (!isFrameElement(child)) {
/* 499 */         prefHeight = Math.max(prefHeight, child.getPreferredHeight());
/*     */       }
/*     */     } 
/* 502 */     return prefHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public void adjustSize() {
/* 507 */     layoutTitle();
/* 508 */     super.adjustSize();
/*     */   }
/*     */   
/*     */   private int getTitleX(int offset) {
/* 512 */     return (offset < 0) ? (getRight() + offset) : (getX() + offset);
/*     */   }
/*     */   
/*     */   private int getTitleY(int offset) {
/* 516 */     return (offset < 0) ? (getBottom() + offset) : (getY() + offset);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean handleEvent(Event evt) {
/* 521 */     boolean isMouseExit = (evt.getType() == Event.Type.MOUSE_EXITED);
/*     */     
/* 523 */     if (isMouseExit && this.resizeHandle != null && this.resizeHandle.isVisible()) {
/* 524 */       this.resizeHandle.getAnimationState().setAnimationState(TextWidget.STATE_HOVER, false);
/*     */     }
/*     */ 
/*     */     
/* 528 */     if (this.dragMode != DragMode.NONE) {
/* 529 */       if (evt.isMouseDragEnd()) {
/* 530 */         this.dragMode = DragMode.NONE;
/* 531 */       } else if (evt.getType() == Event.Type.MOUSE_DRAGGED) {
/* 532 */         handleMouseDrag(evt);
/*     */       } 
/* 534 */       return true;
/*     */     } 
/*     */     
/* 537 */     if (!isMouseExit && this.resizeHandle != null && this.resizeHandle.isVisible()) {
/* 538 */       this.resizeHandle.getAnimationState().setAnimationState(TextWidget.STATE_HOVER, this.resizeHandle.isMouseInside(evt));
/*     */     }
/*     */ 
/*     */     
/* 542 */     if (!evt.isMouseDragEvent() && 
/* 543 */       evt.getType() == Event.Type.MOUSE_BTNDOWN && evt.getMouseButton() == 0 && handleMouseDown(evt))
/*     */     {
/*     */       
/* 546 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 550 */     if (super.handleEvent(evt)) {
/* 551 */       return true;
/*     */     }
/*     */     
/* 554 */     return evt.isMouseEvent();
/*     */   }
/*     */ 
/*     */   
/*     */   public MouseCursor getMouseCursor(Event evt) {
/* 559 */     DragMode cursorMode = this.dragMode;
/* 560 */     if (cursorMode == DragMode.NONE) {
/* 561 */       cursorMode = getDragMode(evt.getMouseX(), evt.getMouseY());
/* 562 */       if (cursorMode == DragMode.NONE) {
/* 563 */         return getMouseCursor();
/*     */       }
/*     */     } 
/*     */     
/* 567 */     return this.cursors[cursorMode.ordinal()];
/*     */   }
/*     */   
/*     */   private DragMode getDragMode(int mx, int my) {
/* 571 */     boolean left = (mx < getInnerX());
/* 572 */     boolean right = (mx >= getInnerRight());
/*     */     
/* 574 */     boolean top = (my < getInnerY());
/* 575 */     boolean bot = (my >= getInnerBottom());
/*     */     
/* 577 */     if (this.titleWidget != null && this.titleWidget.getParent() == this) {
/* 578 */       if (this.titleWidget.isInside(mx, my)) {
/* 579 */         if (this.draggable) {
/* 580 */           return DragMode.POSITION;
/*     */         }
/* 582 */         return DragMode.NONE;
/*     */       } 
/*     */       
/* 585 */       top = (my < this.titleWidget.getY());
/*     */     } 
/*     */     
/* 588 */     if (this.closeButton != null && this.closeButton.isVisible() && this.closeButton.isInside(mx, my)) {
/* 589 */       return DragMode.NONE;
/*     */     }
/*     */     
/* 592 */     if (this.resizableAxis == ResizableAxis.NONE) {
/* 593 */       return DragMode.NONE;
/*     */     }
/*     */     
/* 596 */     if (this.resizeHandle != null && this.resizeHandle.isVisible() && this.resizeHandle.isInside(mx, my)) {
/* 597 */       return this.resizeHandleDragMode;
/*     */     }
/*     */     
/* 600 */     if (!this.resizableAxis.allowX) {
/* 601 */       left = false;
/* 602 */       right = false;
/*     */     } 
/* 604 */     if (!this.resizableAxis.allowY) {
/* 605 */       top = false;
/* 606 */       bot = false;
/*     */     } 
/*     */     
/* 609 */     if (left) {
/* 610 */       if (top) {
/* 611 */         return DragMode.CORNER_TL;
/*     */       }
/* 613 */       if (bot) {
/* 614 */         return DragMode.CORNER_BL;
/*     */       }
/* 616 */       return DragMode.EDGE_LEFT;
/*     */     } 
/* 618 */     if (right) {
/* 619 */       if (top) {
/* 620 */         return DragMode.CORNER_TR;
/*     */       }
/* 622 */       if (bot) {
/* 623 */         return DragMode.CORNER_BR;
/*     */       }
/* 625 */       return DragMode.EDGE_RIGHT;
/*     */     } 
/* 627 */     if (top) {
/* 628 */       return DragMode.EDGE_TOP;
/*     */     }
/* 630 */     if (bot) {
/* 631 */       return DragMode.EDGE_BOTTOM;
/*     */     }
/* 633 */     return DragMode.NONE;
/*     */   }
/*     */   
/*     */   private boolean handleMouseDown(Event evt) {
/* 637 */     int mx = evt.getMouseX();
/* 638 */     int my = evt.getMouseY();
/*     */     
/* 640 */     this.dragStartX = mx;
/* 641 */     this.dragStartY = my;
/* 642 */     this.dragInitialLeft = getX();
/* 643 */     this.dragInitialTop = getY();
/* 644 */     this.dragInitialRight = getRight();
/* 645 */     this.dragInitialBottom = getBottom();
/*     */     
/* 647 */     this.dragMode = getDragMode(mx, my);
/* 648 */     return (this.dragMode != DragMode.NONE);
/*     */   }
/*     */   
/*     */   private void handleMouseDrag(Event evt) {
/* 652 */     int dx = evt.getMouseX() - this.dragStartX;
/* 653 */     int dy = evt.getMouseY() - this.dragStartY;
/*     */     
/* 655 */     int minWidth = getMinWidth();
/* 656 */     int minHeight = getMinHeight();
/* 657 */     int maxWidth = getMaxWidth();
/* 658 */     int maxHeight = getMaxHeight();
/*     */ 
/*     */     
/* 661 */     if (maxWidth > 0 && maxWidth < minWidth) {
/* 662 */       maxWidth = minWidth;
/*     */     }
/* 664 */     if (maxHeight > 0 && maxHeight < minHeight) {
/* 665 */       maxHeight = minHeight;
/*     */     }
/*     */     
/* 668 */     int left = this.dragInitialLeft;
/* 669 */     int top = this.dragInitialTop;
/* 670 */     int right = this.dragInitialRight;
/* 671 */     int bottom = this.dragInitialBottom;
/*     */     
/* 673 */     switch (this.dragMode) {
/*     */       case CORNER_BL:
/*     */       case CORNER_TL:
/*     */       case EDGE_LEFT:
/* 677 */         left = Math.min(left + dx, right - minWidth);
/* 678 */         if (maxWidth > 0) {
/* 679 */           left = Math.max(left, Math.min(this.dragInitialLeft, right - maxWidth));
/*     */         }
/*     */         break;
/*     */       case CORNER_BR:
/*     */       case CORNER_TR:
/*     */       case EDGE_RIGHT:
/* 685 */         right = Math.max(right + dx, left + minWidth);
/* 686 */         if (maxWidth > 0) {
/* 687 */           right = Math.min(right, Math.max(this.dragInitialRight, left + maxWidth));
/*     */         }
/*     */         break;
/*     */       case POSITION:
/* 691 */         if (getParent() != null) {
/* 692 */           int minX = getParent().getInnerX();
/* 693 */           int maxX = getParent().getInnerRight();
/* 694 */           int width = this.dragInitialRight - this.dragInitialLeft;
/* 695 */           left = Math.max(minX, Math.min(maxX - width, left + dx));
/* 696 */           right = Math.min(maxX, Math.max(minX + width, right + dx)); break;
/*     */         } 
/* 698 */         left += dx;
/* 699 */         right += dx;
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 704 */     switch (this.dragMode) {
/*     */       case CORNER_TL:
/*     */       case CORNER_TR:
/*     */       case EDGE_TOP:
/* 708 */         top = Math.min(top + dy, bottom - minHeight);
/* 709 */         if (maxHeight > 0) {
/* 710 */           top = Math.max(top, Math.min(this.dragInitialTop, bottom - maxHeight));
/*     */         }
/*     */         break;
/*     */       case CORNER_BL:
/*     */       case CORNER_BR:
/*     */       case EDGE_BOTTOM:
/* 716 */         bottom = Math.max(bottom + dy, top + minHeight);
/* 717 */         if (maxHeight > 0) {
/* 718 */           bottom = Math.min(bottom, Math.max(this.dragInitialBottom, top + maxHeight));
/*     */         }
/*     */         break;
/*     */       case POSITION:
/* 722 */         if (getParent() != null) {
/* 723 */           int minY = getParent().getInnerY();
/* 724 */           int maxY = getParent().getInnerBottom();
/* 725 */           int height = this.dragInitialBottom - this.dragInitialTop;
/* 726 */           top = Math.max(minY, Math.min(maxY - height, top + dy));
/* 727 */           bottom = Math.min(maxY, Math.max(minY + height, bottom + dy)); break;
/*     */         } 
/* 729 */         top += dy;
/* 730 */         bottom += dy;
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 735 */     setArea(top, left, right, bottom);
/*     */   }
/*     */   
/*     */   private void setArea(int top, int left, int right, int bottom) {
/* 739 */     Widget p = getParent();
/* 740 */     if (p != null) {
/* 741 */       top = Math.max(top, p.getInnerY());
/* 742 */       left = Math.max(left, p.getInnerX());
/* 743 */       right = Math.min(right, p.getInnerRight());
/* 744 */       bottom = Math.min(bottom, p.getInnerBottom());
/*     */     } 
/*     */     
/* 747 */     setPosition(left, top);
/* 748 */     setSize(Math.max(getMinWidth(), right - left), Math.max(getMinHeight(), bottom - top));
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ResizableFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */