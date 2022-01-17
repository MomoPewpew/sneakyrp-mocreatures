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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ScrollPane
/*     */   extends Widget
/*     */ {
/*  51 */   public static final AnimationState.StateKey STATE_DOWNARROW_ARMED = AnimationState.StateKey.get("downArrowArmed");
/*  52 */   public static final AnimationState.StateKey STATE_RIGHTARROW_ARMED = AnimationState.StateKey.get("rightArrowArmed");
/*  53 */   public static final AnimationState.StateKey STATE_HORIZONTAL_SCROLLBAR_VISIBLE = AnimationState.StateKey.get("horizontalScrollbarVisible");
/*  54 */   public static final AnimationState.StateKey STATE_VERTICAL_SCROLLBAR_VISIBLE = AnimationState.StateKey.get("verticalScrollbarVisible");
/*  55 */   public static final AnimationState.StateKey STATE_AUTO_SCROLL_UP = AnimationState.StateKey.get("autoScrollUp");
/*  56 */   public static final AnimationState.StateKey STATE_AUTO_SCROLL_DOWN = AnimationState.StateKey.get("autoScrollDown");
/*     */   private static final int AUTO_SCROLL_DELAY = 50;
/*     */   final Scrollbar scrollbarH;
/*     */   final Scrollbar scrollbarV;
/*     */   private final Widget contentArea;
/*     */   private DraggableButton dragButton;
/*     */   private Widget content;
/*     */   
/*     */   public enum Fixed {
/*  65 */     NONE,
/*     */ 
/*     */ 
/*     */     
/*  69 */     HORIZONTAL,
/*     */ 
/*     */ 
/*     */     
/*  73 */     VERTICAL;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   private Fixed fixed = Fixed.NONE;
/* 145 */   private Dimension hscrollbarOffset = Dimension.ZERO;
/* 146 */   private Dimension vscrollbarOffset = Dimension.ZERO;
/* 147 */   private Dimension contentScrollbarSpacing = Dimension.ZERO;
/*     */   private boolean inLayout;
/*     */   private boolean expandContentSize;
/*     */   private boolean scrollbarsAlwaysVisible;
/*     */   private int scrollbarsToggleFlags;
/*     */   private int autoScrollArea;
/*     */   private int autoScrollSpeed;
/*     */   private Timer autoScrollTimer;
/*     */   private int autoScrollDirection;
/*     */   
/*     */   public ScrollPane() {
/* 158 */     this((Widget)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public ScrollPane(Widget content) {
/* 163 */     this.scrollbarH = new Scrollbar(Scrollbar.Orientation.HORIZONTAL);
/* 164 */     this.scrollbarV = new Scrollbar(Scrollbar.Orientation.VERTICAL);
/* 165 */     this.contentArea = new Widget();
/*     */     
/* 167 */     Runnable cb = new Runnable() {
/*     */         public void run() {
/* 169 */           ScrollPane.this.scrollContent();
/*     */         }
/*     */       };
/*     */     
/* 173 */     this.scrollbarH.addCallback(cb);
/* 174 */     this.scrollbarH.setVisible(false);
/* 175 */     this.scrollbarV.addCallback(cb);
/* 176 */     this.scrollbarV.setVisible(false);
/* 177 */     this.contentArea.setClip(true);
/* 178 */     this.contentArea.setTheme("");
/*     */     
/* 180 */     super.insertChild(this.contentArea, 0);
/* 181 */     super.insertChild(this.scrollbarH, 1);
/* 182 */     super.insertChild(this.scrollbarV, 2);
/* 183 */     setContent(content);
/* 184 */     setCanAcceptKeyboardFocus(true);
/*     */   }
/*     */   
/*     */   public Fixed getFixed() {
/* 188 */     return this.fixed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFixed(Fixed fixed) {
/* 199 */     if (fixed == null) {
/* 200 */       throw new NullPointerException("fixed");
/*     */     }
/* 202 */     if (this.fixed != fixed) {
/* 203 */       this.fixed = fixed;
/* 204 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Widget getContent() {
/* 209 */     return this.content;
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
/*     */   public void setContent(Widget content) {
/* 224 */     if (this.content != null) {
/* 225 */       this.contentArea.removeAllChildren();
/* 226 */       this.content = null;
/*     */     } 
/* 228 */     if (content != null) {
/* 229 */       this.content = content;
/* 230 */       this.contentArea.add(content);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isExpandContentSize() {
/* 235 */     return this.expandContentSize;
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
/*     */   public void setExpandContentSize(boolean expandContentSize) {
/* 250 */     if (this.expandContentSize != expandContentSize) {
/* 251 */       this.expandContentSize = expandContentSize;
/* 252 */       invalidateLayoutLocally();
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
/*     */ 
/*     */   
/*     */   public void updateScrollbarSizes() {
/* 267 */     invalidateLayoutLocally();
/* 268 */     validateLayout();
/*     */   }
/*     */   
/*     */   public int getScrollPositionX() {
/* 272 */     return this.scrollbarH.getValue();
/*     */   }
/*     */   
/*     */   public int getMaxScrollPosX() {
/* 276 */     return this.scrollbarH.getMaxValue();
/*     */   }
/*     */   
/*     */   public void setScrollPositionX(int pos) {
/* 280 */     this.scrollbarH.setValue(pos);
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
/*     */   public void scrollToAreaX(int start, int size, int extra) {
/* 293 */     this.scrollbarH.scrollToArea(start, size, extra);
/*     */   }
/*     */   
/*     */   public int getScrollPositionY() {
/* 297 */     return this.scrollbarV.getValue();
/*     */   }
/*     */   
/*     */   public int getMaxScrollPosY() {
/* 301 */     return this.scrollbarV.getMaxValue();
/*     */   }
/*     */   
/*     */   public void setScrollPositionY(int pos) {
/* 305 */     this.scrollbarV.setValue(pos);
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
/*     */   public void scrollToAreaY(int start, int size, int extra) {
/* 318 */     this.scrollbarV.scrollToArea(start, size, extra);
/*     */   }
/*     */   
/*     */   public int getContentAreaWidth() {
/* 322 */     return this.contentArea.getWidth();
/*     */   }
/*     */   
/*     */   public int getContentAreaHeight() {
/* 326 */     return this.contentArea.getHeight();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Scrollbar getHorizontalScrollbar() {
/* 334 */     return this.scrollbarH;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Scrollbar getVerticalScrollbar() {
/* 342 */     return this.scrollbarV;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DraggableButton.DragListener createDragListener() {
/* 350 */     return new DraggableButton.DragListener() {
/*     */         int startScrollX;
/*     */         
/*     */         public void dragStarted() {
/* 354 */           this.startScrollX = ScrollPane.this.getScrollPositionX();
/* 355 */           this.startScrollY = ScrollPane.this.getScrollPositionY();
/*     */         } int startScrollY;
/*     */         public void dragged(int deltaX, int deltaY) {
/* 358 */           ScrollPane.this.setScrollPositionX(this.startScrollX - deltaX);
/* 359 */           ScrollPane.this.setScrollPositionY(this.startScrollY - deltaY);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void dragStopped() {}
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkAutoScroll(Event evt) {
/* 375 */     GUI gui = getGUI();
/* 376 */     if (gui == null) {
/* 377 */       stopAutoScroll();
/* 378 */       return false;
/*     */     } 
/*     */     
/* 381 */     this.autoScrollDirection = getAutoScrollDirection(evt);
/* 382 */     if (this.autoScrollDirection == 0) {
/* 383 */       stopAutoScroll();
/* 384 */       return false;
/*     */     } 
/*     */     
/* 387 */     setAutoScrollMarker();
/*     */     
/* 389 */     if (this.autoScrollTimer == null) {
/* 390 */       this.autoScrollTimer = gui.createTimer();
/* 391 */       this.autoScrollTimer.setContinuous(true);
/* 392 */       this.autoScrollTimer.setDelay(50);
/* 393 */       this.autoScrollTimer.setCallback(new Runnable() {
/*     */             public void run() {
/* 395 */               ScrollPane.this.doAutoScroll();
/*     */             }
/*     */           });
/* 398 */       doAutoScroll();
/*     */     } 
/* 400 */     this.autoScrollTimer.start();
/* 401 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopAutoScroll() {
/* 411 */     if (this.autoScrollTimer != null) {
/* 412 */       this.autoScrollTimer.stop();
/*     */     }
/* 414 */     this.autoScrollDirection = 0;
/* 415 */     setAutoScrollMarker();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ScrollPane getContainingScrollPane(Widget widget) {
/* 426 */     Widget ca = widget.getParent();
/* 427 */     if (ca != null) {
/* 428 */       Widget sp = ca.getParent();
/* 429 */       if (sp instanceof ScrollPane) {
/* 430 */         ScrollPane scrollPane = (ScrollPane)sp;
/* 431 */         assert scrollPane.getContent() == widget;
/* 432 */         return scrollPane;
/*     */       } 
/*     */     } 
/* 435 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/* 440 */     int minWidth = super.getMinWidth();
/* 441 */     int border = getBorderHorizontal();
/*     */     
/* 443 */     if (this.fixed == Fixed.HORIZONTAL && this.content != null) {
/* 444 */       minWidth = Math.max(minWidth, this.content.getMinWidth() + border + this.scrollbarV.getMinWidth());
/*     */     }
/*     */     
/* 447 */     return minWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/* 452 */     int minHeight = super.getMinHeight();
/* 453 */     int border = getBorderVertical();
/*     */     
/* 455 */     if (this.fixed == Fixed.VERTICAL && this.content != null) {
/* 456 */       minHeight = Math.max(minHeight, this.content.getMinHeight() + border + this.scrollbarH.getMinHeight());
/*     */     }
/*     */     
/* 459 */     return minHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 464 */     if (this.content != null) {
/* 465 */       int prefWidth; switch (this.fixed) {
/*     */         case KEY_PRESSED:
/* 467 */           prefWidth = computeSize(this.content.getMinWidth(), this.content.getPreferredWidth(), this.content.getMaxWidth());
/*     */ 
/*     */ 
/*     */           
/* 471 */           if (this.scrollbarV.isVisible()) {
/* 472 */             prefWidth += this.scrollbarV.getPreferredWidth();
/*     */           }
/* 474 */           return prefWidth;
/*     */         case KEY_RELEASED:
/* 476 */           return this.content.getPreferredWidth();
/*     */       } 
/*     */     } 
/* 479 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 484 */     if (this.content != null) {
/* 485 */       int prefHeight; switch (this.fixed) {
/*     */         case KEY_PRESSED:
/* 487 */           return this.content.getPreferredHeight();
/*     */         case KEY_RELEASED:
/* 489 */           prefHeight = computeSize(this.content.getMinHeight(), this.content.getPreferredHeight(), this.content.getMaxHeight());
/*     */ 
/*     */ 
/*     */           
/* 493 */           if (this.scrollbarH.isVisible()) {
/* 494 */             prefHeight += this.scrollbarH.getPreferredHeight();
/*     */           }
/* 496 */           return prefHeight;
/*     */       } 
/*     */     } 
/* 499 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertChild(Widget child, int index) {
/* 504 */     throw new UnsupportedOperationException("use setContent");
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAllChildren() {
/* 509 */     throw new UnsupportedOperationException("use setContent");
/*     */   }
/*     */ 
/*     */   
/*     */   public Widget removeChild(int index) {
/* 514 */     throw new UnsupportedOperationException("use setContent");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 519 */     super.applyTheme(themeInfo);
/* 520 */     applyThemeScrollPane(themeInfo);
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
/*     */   protected void applyThemeScrollPane(ThemeInfo themeInfo) {
/* 547 */     this.autoScrollArea = themeInfo.getParameter("autoScrollArea", 5);
/* 548 */     this.autoScrollSpeed = themeInfo.getParameter("autoScrollSpeed", this.autoScrollArea * 2);
/* 549 */     this.hscrollbarOffset = themeInfo.<Dimension>getParameterValue("hscrollbarOffset", false, Dimension.class, Dimension.ZERO);
/* 550 */     this.vscrollbarOffset = themeInfo.<Dimension>getParameterValue("vscrollbarOffset", false, Dimension.class, Dimension.ZERO);
/* 551 */     this.contentScrollbarSpacing = themeInfo.<Dimension>getParameterValue("contentScrollbarSpacing", false, Dimension.class, Dimension.ZERO);
/* 552 */     this.scrollbarsAlwaysVisible = themeInfo.getParameter("scrollbarsAlwaysVisible", false);
/*     */     
/* 554 */     boolean hasDragButton = themeInfo.getParameter("hasDragButton", false);
/* 555 */     if (hasDragButton && this.dragButton == null) {
/* 556 */       this.dragButton = new DraggableButton();
/* 557 */       this.dragButton.setTheme("dragButton");
/* 558 */       this.dragButton.setListener(new DraggableButton.DragListener() {
/*     */             public void dragStarted() {
/* 560 */               ScrollPane.this.scrollbarH.externalDragStart();
/* 561 */               ScrollPane.this.scrollbarV.externalDragStart();
/*     */             }
/*     */             public void dragged(int deltaX, int deltaY) {
/* 564 */               ScrollPane.this.scrollbarH.externalDragged(deltaX, deltaY);
/* 565 */               ScrollPane.this.scrollbarV.externalDragged(deltaX, deltaY);
/*     */             }
/*     */             public void dragStopped() {
/* 568 */               ScrollPane.this.scrollbarH.externalDragStopped();
/* 569 */               ScrollPane.this.scrollbarV.externalDragStopped();
/*     */             }
/*     */           });
/* 572 */       super.insertChild(this.dragButton, 3);
/* 573 */     } else if (!hasDragButton && this.dragButton != null) {
/* 574 */       assert getChild(3) == this.dragButton;
/* 575 */       super.removeChild(3);
/* 576 */       this.dragButton = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected int getAutoScrollDirection(Event evt) {
/* 581 */     if (this.content instanceof AutoScrollable) {
/* 582 */       return ((AutoScrollable)this.content).getAutoScrollDirection(evt, this.autoScrollArea);
/*     */     }
/* 584 */     if (this.contentArea.isMouseInside(evt)) {
/* 585 */       int mouseY = evt.getMouseY();
/* 586 */       int areaY = this.contentArea.getY();
/* 587 */       if (mouseY - areaY <= this.autoScrollArea || this.contentArea.getBottom() - mouseY <= this.autoScrollArea) {
/*     */ 
/*     */         
/* 590 */         if (mouseY < areaY + this.contentArea.getHeight() / 2) {
/* 591 */           return -1;
/*     */         }
/* 593 */         return 1;
/*     */       } 
/*     */     } 
/*     */     
/* 597 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void validateLayout() {
/* 602 */     if (!this.inLayout) {
/*     */       try {
/* 604 */         this.inLayout = true;
/* 605 */         if (this.content != null) {
/* 606 */           this.content.validateLayout();
/*     */         }
/* 608 */         super.validateLayout();
/*     */       } finally {
/* 610 */         this.inLayout = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void childInvalidateLayout(Widget child) {
/* 617 */     if (child == this.contentArea) {
/*     */       
/* 619 */       invalidateLayoutLocally();
/*     */     } else {
/* 621 */       super.childInvalidateLayout(child);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void paintWidget(GUI gui) {
/* 628 */     this.scrollbarsToggleFlags = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 633 */     if (this.content != null) {
/* 634 */       int requiredWidth, requiredHeight, pageSizeX, pageSizeY, innerWidth = getInnerWidth();
/* 635 */       int innerHeight = getInnerHeight();
/* 636 */       int availWidth = innerWidth;
/* 637 */       int availHeight = innerHeight;
/* 638 */       innerWidth += this.vscrollbarOffset.getX();
/* 639 */       innerHeight += this.hscrollbarOffset.getY();
/* 640 */       int scrollbarHX = this.hscrollbarOffset.getX();
/* 641 */       int scrollbarHY = innerHeight;
/* 642 */       int scrollbarVX = innerWidth;
/* 643 */       int scrollbarVY = this.vscrollbarOffset.getY();
/*     */ 
/*     */ 
/*     */       
/* 647 */       boolean visibleH = false;
/* 648 */       boolean visibleV = false;
/*     */       
/* 650 */       switch (this.fixed) {
/*     */         case KEY_PRESSED:
/* 652 */           requiredWidth = availWidth;
/* 653 */           requiredHeight = this.content.getPreferredHeight();
/*     */           break;
/*     */         case KEY_RELEASED:
/* 656 */           requiredWidth = this.content.getPreferredWidth();
/* 657 */           requiredHeight = availHeight;
/*     */           break;
/*     */         default:
/* 660 */           requiredWidth = this.content.getPreferredWidth();
/* 661 */           requiredHeight = this.content.getPreferredHeight();
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 667 */       int hScrollbarMax = 0;
/* 668 */       int vScrollbarMax = 0;
/*     */ 
/*     */       
/* 671 */       if (availWidth > 0 && availHeight > 0) {
/*     */         int i; do {
/* 673 */           boolean repeat = false;
/*     */           
/* 675 */           if (this.fixed != Fixed.HORIZONTAL) {
/* 676 */             hScrollbarMax = Math.max(0, requiredWidth - availWidth);
/* 677 */             if (hScrollbarMax > 0 || this.scrollbarsAlwaysVisible || (this.scrollbarsToggleFlags & 0x3) == 3) {
/* 678 */               i = repeat | (!visibleH ? 1 : 0);
/* 679 */               visibleH = true;
/* 680 */               int prefHeight = this.scrollbarH.getPreferredHeight();
/* 681 */               scrollbarHY = innerHeight - prefHeight;
/* 682 */               availHeight = Math.max(0, scrollbarHY - this.contentScrollbarSpacing.getY());
/*     */             } 
/*     */           } else {
/* 685 */             hScrollbarMax = 0;
/* 686 */             requiredWidth = availWidth;
/*     */           } 
/*     */           
/* 689 */           if (this.fixed != Fixed.VERTICAL) {
/* 690 */             vScrollbarMax = Math.max(0, requiredHeight - availHeight);
/* 691 */             if (vScrollbarMax > 0 || this.scrollbarsAlwaysVisible || (this.scrollbarsToggleFlags & 0xC) == 12) {
/* 692 */               i |= !visibleV ? 1 : 0;
/* 693 */               visibleV = true;
/* 694 */               int prefWidth = this.scrollbarV.getPreferredWidth();
/* 695 */               scrollbarVX = innerWidth - prefWidth;
/* 696 */               availWidth = Math.max(0, scrollbarVX - this.contentScrollbarSpacing.getX());
/*     */             } 
/*     */           } else {
/* 699 */             vScrollbarMax = 0;
/* 700 */             requiredHeight = availHeight;
/*     */           } 
/* 702 */         } while (i != 0);
/*     */       } 
/*     */ 
/*     */       
/* 706 */       if (visibleH && !this.scrollbarH.isVisible()) {
/* 707 */         this.scrollbarsToggleFlags |= 0x1;
/*     */       }
/* 709 */       if (!visibleH && this.scrollbarH.isVisible()) {
/* 710 */         this.scrollbarsToggleFlags |= 0x2;
/*     */       }
/* 712 */       if (visibleV && !this.scrollbarV.isVisible()) {
/* 713 */         this.scrollbarsToggleFlags |= 0x4;
/*     */       }
/* 715 */       if (!visibleV && this.scrollbarV.isVisible()) {
/* 716 */         this.scrollbarsToggleFlags |= 0x8;
/*     */       }
/*     */       
/* 719 */       if (visibleH != this.scrollbarH.isVisible() || visibleV != this.scrollbarV.isVisible()) {
/* 720 */         invalidateLayoutLocally();
/*     */       }
/*     */ 
/*     */       
/* 724 */       if (this.content instanceof CustomPageSize) {
/* 725 */         CustomPageSize customPageSize = (CustomPageSize)this.content;
/* 726 */         pageSizeX = customPageSize.getPageSizeX(availWidth);
/* 727 */         pageSizeY = customPageSize.getPageSizeY(availHeight);
/*     */       } else {
/* 729 */         pageSizeX = availWidth;
/* 730 */         pageSizeY = availHeight;
/*     */       } 
/*     */       
/* 733 */       this.scrollbarH.setVisible(visibleH);
/* 734 */       this.scrollbarH.setMinMaxValue(0, hScrollbarMax);
/* 735 */       this.scrollbarH.setSize(Math.max(0, scrollbarVX - scrollbarHX), Math.max(0, innerHeight - scrollbarHY));
/* 736 */       this.scrollbarH.setPosition(getInnerX() + scrollbarHX, getInnerY() + scrollbarHY);
/* 737 */       this.scrollbarH.setPageSize(Math.max(1, pageSizeX));
/* 738 */       this.scrollbarH.setStepSize(Math.max(1, pageSizeX / 10));
/*     */       
/* 740 */       this.scrollbarV.setVisible(visibleV);
/* 741 */       this.scrollbarV.setMinMaxValue(0, vScrollbarMax);
/* 742 */       this.scrollbarV.setSize(Math.max(0, innerWidth - scrollbarVX), Math.max(0, scrollbarHY - scrollbarVY));
/* 743 */       this.scrollbarV.setPosition(getInnerX() + scrollbarVX, getInnerY() + scrollbarVY);
/* 744 */       this.scrollbarV.setPageSize(Math.max(1, pageSizeY));
/* 745 */       this.scrollbarV.setStepSize(Math.max(1, pageSizeY / 10));
/*     */       
/* 747 */       if (this.dragButton != null) {
/* 748 */         this.dragButton.setVisible((visibleH && visibleV));
/* 749 */         this.dragButton.setSize(Math.max(0, innerWidth - scrollbarVX), Math.max(0, innerHeight - scrollbarHY));
/* 750 */         this.dragButton.setPosition(getInnerX() + scrollbarVX, getInnerY() + scrollbarHY);
/*     */       } 
/*     */       
/* 753 */       this.contentArea.setPosition(getInnerX(), getInnerY());
/* 754 */       this.contentArea.setSize(availWidth, availHeight);
/* 755 */       if (this.content instanceof Scrollable) {
/* 756 */         this.content.setPosition(this.contentArea.getX(), this.contentArea.getY());
/* 757 */         this.content.setSize(availWidth, availHeight);
/* 758 */       } else if (this.expandContentSize) {
/* 759 */         this.content.setSize(Math.max(availWidth, requiredWidth), Math.max(availHeight, requiredHeight));
/*     */       } else {
/*     */         
/* 762 */         this.content.setSize(Math.max(0, requiredWidth), Math.max(0, requiredHeight));
/*     */       } 
/*     */       
/* 765 */       AnimationState animationState = getAnimationState();
/* 766 */       animationState.setAnimationState(STATE_HORIZONTAL_SCROLLBAR_VISIBLE, visibleH);
/* 767 */       animationState.setAnimationState(STATE_VERTICAL_SCROLLBAR_VISIBLE, visibleV);
/*     */       
/* 769 */       scrollContent();
/*     */     } else {
/* 771 */       this.scrollbarH.setVisible(false);
/* 772 */       this.scrollbarV.setVisible(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean handleEvent(Event evt) {
/*     */     int keyCode;
/* 778 */     if (evt.isKeyEvent() && this.content != null && this.content.canAcceptKeyboardFocus() && 
/* 779 */       this.content.handleEvent(evt)) {
/* 780 */       this.content.requestKeyboardFocus();
/* 781 */       return true;
/*     */     } 
/*     */     
/* 784 */     if (super.handleEvent(evt)) {
/* 785 */       return true;
/*     */     }
/* 787 */     switch (evt.getType()) {
/*     */       case KEY_PRESSED:
/*     */       case KEY_RELEASED:
/* 790 */         keyCode = evt.getKeyCode();
/* 791 */         if (keyCode == 203 || keyCode == 205)
/*     */         {
/* 793 */           return this.scrollbarH.handleEvent(evt);
/*     */         }
/* 795 */         if (keyCode == 200 || keyCode == 208 || keyCode == 201 || keyCode == 209)
/*     */         {
/*     */ 
/*     */           
/* 799 */           return this.scrollbarV.handleEvent(evt);
/*     */         }
/*     */         break;
/*     */       
/*     */       case MOUSE_WHEEL:
/* 804 */         if (this.scrollbarV.isVisible()) {
/* 805 */           return this.scrollbarV.handleEvent(evt);
/*     */         }
/* 807 */         return false;
/*     */     } 
/* 809 */     return (evt.isMouseEvent() && this.contentArea.isMouseInside(evt));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paint(GUI gui) {
/* 814 */     if (this.dragButton != null) {
/* 815 */       AnimationState as = this.dragButton.getAnimationState();
/* 816 */       as.setAnimationState(STATE_DOWNARROW_ARMED, this.scrollbarV.isDownRightButtonArmed());
/* 817 */       as.setAnimationState(STATE_RIGHTARROW_ARMED, this.scrollbarH.isDownRightButtonArmed());
/*     */     } 
/* 819 */     super.paint(gui);
/*     */   }
/*     */   
/*     */   void scrollContent() {
/* 823 */     if (this.content instanceof Scrollable) {
/* 824 */       Scrollable scrollable = (Scrollable)this.content;
/* 825 */       scrollable.setScrollPosition(this.scrollbarH.getValue(), this.scrollbarV.getValue());
/*     */     } else {
/* 827 */       this.content.setPosition(this.contentArea.getX() - this.scrollbarH.getValue(), this.contentArea.getY() - this.scrollbarV.getValue());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void setAutoScrollMarker() {
/* 834 */     int scrollPos = this.scrollbarV.getValue();
/* 835 */     AnimationState animationState = getAnimationState();
/* 836 */     animationState.setAnimationState(STATE_AUTO_SCROLL_UP, (this.autoScrollDirection < 0 && scrollPos > 0));
/* 837 */     animationState.setAnimationState(STATE_AUTO_SCROLL_DOWN, (this.autoScrollDirection > 0 && scrollPos < this.scrollbarV.getMaxValue()));
/*     */   }
/*     */   
/*     */   void doAutoScroll() {
/* 841 */     this.scrollbarV.setValue(this.scrollbarV.getValue() + this.autoScrollDirection * this.autoScrollSpeed);
/* 842 */     setAutoScrollMarker();
/*     */   }
/*     */   
/*     */   public static interface CustomPageSize {
/*     */     int getPageSizeX(int param1Int);
/*     */     
/*     */     int getPageSizeY(int param1Int);
/*     */   }
/*     */   
/*     */   public static interface AutoScrollable {
/*     */     int getAutoScrollDirection(Event param1Event, int param1Int);
/*     */   }
/*     */   
/*     */   public static interface Scrollable {
/*     */     void setScrollPosition(int param1Int1, int param1Int2);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ScrollPane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */