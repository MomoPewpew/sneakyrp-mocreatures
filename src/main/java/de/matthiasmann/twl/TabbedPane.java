/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.BooleanModel;
/*     */ import de.matthiasmann.twl.model.HasCallback;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
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
/*     */ 
/*     */ public class TabbedPane
/*     */   extends Widget
/*     */ {
/*  44 */   public static final AnimationState.StateKey STATE_FIRST_TAB = AnimationState.StateKey.get("firstTab"); private final ArrayList<Tab> tabs; private final BoxLayout tabBox; private final Widget tabBoxClip; private final Container container; final Container innerContainer; DialogLayout scrollControlls;
/*  45 */   public static final AnimationState.StateKey STATE_LAST_TAB = AnimationState.StateKey.get("lastTab"); Button btnScrollLeft; Button btnScrollRight; boolean scrollTabs; int tabScrollPosition; TabPosition tabPosition;
/*     */   Tab activeTab;
/*     */   
/*  48 */   public enum TabPosition { TOP(true),
/*  49 */     LEFT(false),
/*  50 */     RIGHT(true),
/*  51 */     BOTTOM(false);
/*     */     final boolean horz;
/*     */     
/*     */     TabPosition(boolean horz) {
/*  55 */       this.horz = horz;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TabbedPane() {
/*  75 */     this.tabs = new ArrayList<Tab>();
/*  76 */     this.tabBox = new BoxLayout();
/*  77 */     this.tabBoxClip = new Widget();
/*  78 */     this.container = new Container();
/*  79 */     this.innerContainer = new Container();
/*  80 */     this.tabPosition = TabPosition.TOP;
/*     */     
/*  82 */     this.tabBox.setTheme("tabbox");
/*  83 */     this.tabBoxClip.setTheme("");
/*  84 */     this.innerContainer.setTheme("");
/*  85 */     this.innerContainer.setClip(true);
/*     */     
/*  87 */     this.tabBoxClip.add(this.tabBox);
/*  88 */     this.container.add(this.innerContainer);
/*     */     
/*  90 */     super.insertChild(this.container, 0);
/*  91 */     super.insertChild(this.tabBoxClip, 1);
/*     */     
/*  93 */     addActionMapping("nextTab", "cycleTabs", new Object[] { Integer.valueOf(1) });
/*  94 */     addActionMapping("prevTab", "cycleTabs", new Object[] { Integer.valueOf(-1) });
/*  95 */     setCanAcceptKeyboardFocus(false);
/*     */   }
/*     */   
/*     */   public TabPosition getTabPosition() {
/*  99 */     return this.tabPosition;
/*     */   }
/*     */   
/*     */   public void setTabPosition(TabPosition tabPosition) {
/* 103 */     if (tabPosition == null) {
/* 104 */       throw new NullPointerException("tabPosition");
/*     */     }
/* 106 */     if (this.tabPosition != tabPosition) {
/* 107 */       this.tabPosition = tabPosition;
/* 108 */       this.tabBox.setDirection(tabPosition.horz ? BoxLayout.Direction.HORIZONTAL : BoxLayout.Direction.VERTICAL);
/*     */ 
/*     */       
/* 111 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isScrollTabs() {
/* 116 */     return this.scrollTabs;
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
/*     */   public void setScrollTabs(boolean scrollTabs) {
/* 130 */     if (this.scrollTabs != scrollTabs) {
/* 131 */       this.scrollTabs = scrollTabs;
/*     */       
/* 133 */       if (this.scrollControlls == null && scrollTabs) {
/* 134 */         createScrollControlls();
/*     */       }
/*     */       
/* 137 */       this.tabBoxClip.setClip(scrollTabs);
/* 138 */       if (this.scrollControlls != null) {
/* 139 */         this.scrollControlls.setVisible(scrollTabs);
/*     */       }
/* 141 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Tab addTab(String title, Widget pane) {
/* 146 */     Tab tab = new Tab();
/* 147 */     tab.setTitle(title);
/* 148 */     tab.setPane(pane);
/* 149 */     this.tabBox.add(tab.button);
/* 150 */     this.tabs.add(tab);
/*     */     
/* 152 */     if (this.tabs.size() == 1) {
/* 153 */       setActiveTab(tab);
/*     */     }
/* 155 */     updateTabStates();
/* 156 */     return tab;
/*     */   }
/*     */   
/*     */   public Tab getActiveTab() {
/* 160 */     return this.activeTab;
/*     */   }
/*     */   
/*     */   public void setActiveTab(Tab tab) {
/* 164 */     if (tab != null) {
/* 165 */       validateTab(tab);
/*     */     }
/*     */     
/* 168 */     if (this.activeTab != tab) {
/* 169 */       Tab prevTab = this.activeTab;
/* 170 */       this.activeTab = tab;
/*     */       
/* 172 */       if (prevTab != null) {
/* 173 */         prevTab.doCallback();
/*     */       }
/* 175 */       if (tab != null) {
/* 176 */         tab.doCallback();
/*     */       }
/*     */       
/* 179 */       if (this.scrollTabs) {
/* 180 */         int pos, end, size; validateLayout();
/*     */ 
/*     */         
/* 183 */         if (this.tabPosition.horz) {
/* 184 */           pos = tab.button.getX() - this.tabBox.getX();
/* 185 */           end = tab.button.getWidth() + pos;
/* 186 */           size = this.tabBoxClip.getWidth();
/*     */         } else {
/* 188 */           pos = tab.button.getY() - this.tabBox.getY();
/* 189 */           end = tab.button.getHeight() + pos;
/* 190 */           size = this.tabBoxClip.getHeight();
/*     */         } 
/* 192 */         int border = (size + 19) / 20;
/* 193 */         pos -= border;
/* 194 */         end += border;
/* 195 */         if (pos < this.tabScrollPosition) {
/* 196 */           setScrollPos(pos);
/* 197 */         } else if (end > this.tabScrollPosition + size) {
/* 198 */           setScrollPos(end - size);
/*     */         } 
/*     */       } 
/*     */       
/* 202 */       if (tab != null && tab.pane != null) {
/* 203 */         tab.pane.requestKeyboardFocus();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeTab(Tab tab) {
/* 209 */     validateTab(tab);
/*     */     
/* 211 */     int idx = (tab == this.activeTab) ? this.tabs.indexOf(tab) : -1;
/* 212 */     tab.setPane(null);
/* 213 */     this.tabBox.removeChild(tab.button);
/* 214 */     this.tabs.remove(tab);
/*     */     
/* 216 */     if (idx >= 0 && !this.tabs.isEmpty()) {
/* 217 */       setActiveTab(this.tabs.get(Math.min(this.tabs.size() - 1, idx)));
/*     */     }
/* 219 */     updateTabStates();
/*     */   }
/*     */   
/*     */   public void removeAllTabs() {
/* 223 */     this.innerContainer.removeAllChildren();
/* 224 */     this.tabBox.removeAllChildren();
/* 225 */     this.tabs.clear();
/* 226 */     this.activeTab = null;
/*     */   }
/*     */   
/*     */   public int getNumTabs() {
/* 230 */     return this.tabs.size();
/*     */   }
/*     */   
/*     */   public Tab getTab(int index) {
/* 234 */     return this.tabs.get(index);
/*     */   }
/*     */   
/*     */   public int getActiveTabIndex() {
/* 238 */     if (this.tabs.isEmpty()) {
/* 239 */       return -1;
/*     */     }
/* 241 */     return this.tabs.indexOf(this.activeTab);
/*     */   }
/*     */   
/*     */   public void cycleTabs(int direction) {
/* 245 */     if (!this.tabs.isEmpty()) {
/* 246 */       int idx = this.tabs.indexOf(this.activeTab);
/* 247 */       if (idx < 0) {
/* 248 */         idx = 0;
/*     */       } else {
/* 250 */         idx += direction;
/* 251 */         idx %= this.tabs.size();
/* 252 */         idx += this.tabs.size();
/* 253 */         idx %= this.tabs.size();
/*     */       } 
/* 255 */       setActiveTab(this.tabs.get(idx));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/*     */     int minWidth;
/* 262 */     if (this.tabPosition.horz) {
/*     */       int tabBoxWidth;
/* 264 */       if (this.scrollTabs) {
/* 265 */         tabBoxWidth = this.tabBox.getBorderHorizontal() + BoxLayout.computeMinWidthVertical(this.tabBox) + this.scrollControlls.getPreferredWidth();
/*     */       }
/*     */       else {
/*     */         
/* 269 */         tabBoxWidth = this.tabBox.getMinWidth();
/*     */       } 
/* 271 */       minWidth = Math.max(this.container.getMinWidth(), tabBoxWidth);
/*     */     } else {
/* 273 */       minWidth = this.container.getMinWidth() + this.tabBox.getMinWidth();
/*     */     } 
/* 275 */     return Math.max(super.getMinWidth(), minWidth + getBorderHorizontal());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/*     */     int minHeight;
/* 281 */     if (this.tabPosition.horz) {
/* 282 */       minHeight = this.container.getMinHeight() + this.tabBox.getMinHeight();
/*     */     } else {
/* 284 */       minHeight = Math.max(this.container.getMinHeight(), this.tabBox.getMinHeight());
/*     */     } 
/* 286 */     return Math.max(super.getMinHeight(), minHeight + getBorderVertical());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 291 */     if (this.tabPosition.horz) {
/*     */       int tabBoxWidth;
/* 293 */       if (this.scrollTabs) {
/* 294 */         tabBoxWidth = this.tabBox.getBorderHorizontal() + BoxLayout.computePreferredWidthVertical(this.tabBox) + this.scrollControlls.getPreferredWidth();
/*     */       }
/*     */       else {
/*     */         
/* 298 */         tabBoxWidth = this.tabBox.getPreferredWidth();
/*     */       } 
/* 300 */       return Math.max(this.container.getPreferredWidth(), tabBoxWidth);
/*     */     } 
/* 302 */     return this.container.getPreferredWidth() + this.tabBox.getPreferredWidth();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 308 */     if (this.tabPosition.horz) {
/* 309 */       return this.container.getPreferredHeight() + this.tabBox.getPreferredHeight();
/*     */     }
/* 311 */     return Math.max(this.container.getPreferredHeight(), this.tabBox.getPreferredHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 317 */     int scrollCtrlsWidth = 0;
/* 318 */     int scrollCtrlsHeight = 0;
/* 319 */     int tabBoxWidth = this.tabBox.getPreferredWidth();
/* 320 */     int tabBoxHeight = this.tabBox.getPreferredHeight();
/*     */     
/* 322 */     if (this.scrollTabs) {
/* 323 */       scrollCtrlsWidth = this.scrollControlls.getPreferredWidth();
/* 324 */       scrollCtrlsHeight = this.scrollControlls.getPreferredHeight();
/*     */     } 
/*     */     
/* 327 */     if (this.tabPosition.horz) {
/* 328 */       tabBoxHeight = Math.max(scrollCtrlsHeight, tabBoxHeight);
/*     */     } else {
/* 330 */       tabBoxWidth = Math.max(scrollCtrlsWidth, tabBoxWidth);
/*     */     } 
/*     */     
/* 333 */     this.tabBox.setSize(tabBoxWidth, tabBoxHeight);
/*     */     
/* 335 */     switch (this.tabPosition) {
/*     */       case TOP:
/* 337 */         this.tabBoxClip.setPosition(getInnerX(), getInnerY());
/* 338 */         this.tabBoxClip.setSize(Math.max(0, getInnerWidth() - scrollCtrlsWidth), tabBoxHeight);
/* 339 */         this.container.setSize(getInnerWidth(), Math.max(0, getInnerHeight() - tabBoxHeight));
/* 340 */         this.container.setPosition(getInnerX(), this.tabBoxClip.getBottom());
/*     */         break;
/*     */       
/*     */       case LEFT:
/* 344 */         this.tabBoxClip.setPosition(getInnerX(), getInnerY());
/* 345 */         this.tabBoxClip.setSize(tabBoxWidth, Math.max(0, getInnerHeight() - scrollCtrlsHeight));
/* 346 */         this.container.setSize(Math.max(0, getInnerWidth() - tabBoxWidth), getInnerHeight());
/* 347 */         this.container.setPosition(this.tabBoxClip.getRight(), getInnerY());
/*     */         break;
/*     */       
/*     */       case RIGHT:
/* 351 */         this.tabBoxClip.setPosition(getInnerX() - tabBoxWidth, getInnerY());
/* 352 */         this.tabBoxClip.setSize(tabBoxWidth, Math.max(0, getInnerHeight() - scrollCtrlsHeight));
/* 353 */         this.container.setSize(Math.max(0, getInnerWidth() - tabBoxWidth), getInnerHeight());
/* 354 */         this.container.setPosition(getInnerX(), getInnerY());
/*     */         break;
/*     */       
/*     */       case BOTTOM:
/* 358 */         this.tabBoxClip.setPosition(getInnerX(), getInnerY() - tabBoxHeight);
/* 359 */         this.tabBoxClip.setSize(Math.max(0, getInnerWidth() - scrollCtrlsWidth), tabBoxHeight);
/* 360 */         this.container.setSize(getInnerWidth(), Math.max(0, getInnerHeight() - tabBoxHeight));
/* 361 */         this.container.setPosition(getInnerX(), getInnerY());
/*     */         break;
/*     */     } 
/*     */     
/* 365 */     if (this.scrollControlls != null) {
/* 366 */       if (this.tabPosition.horz) {
/* 367 */         this.scrollControlls.setPosition(this.tabBoxClip.getRight(), this.tabBoxClip.getY());
/* 368 */         this.scrollControlls.setSize(scrollCtrlsWidth, tabBoxHeight);
/*     */       } else {
/* 370 */         this.scrollControlls.setPosition(this.tabBoxClip.getX(), this.tabBoxClip.getBottom());
/* 371 */         this.scrollControlls.setSize(tabBoxWidth, scrollCtrlsHeight);
/*     */       } 
/* 373 */       setScrollPos(this.tabScrollPosition);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void createScrollControlls() {
/* 378 */     this.scrollControlls = new DialogLayout();
/* 379 */     this.scrollControlls.setTheme("scrollControls");
/*     */     
/* 381 */     this.btnScrollLeft = new Button();
/* 382 */     this.btnScrollLeft.setTheme("scrollLeft");
/* 383 */     this.btnScrollLeft.addCallback(new CB(-1));
/*     */     
/* 385 */     this.btnScrollRight = new Button();
/* 386 */     this.btnScrollRight.setTheme("scrollRight");
/* 387 */     this.btnScrollRight.addCallback(new CB(1));
/*     */     
/* 389 */     DialogLayout.Group horz = this.scrollControlls.createSequentialGroup().addWidget(this.btnScrollLeft).addGap("scrollButtons").addWidget(this.btnScrollRight);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 394 */     DialogLayout.Group vert = this.scrollControlls.createParallelGroup().addWidget(this.btnScrollLeft).addWidget(this.btnScrollRight);
/*     */ 
/*     */ 
/*     */     
/* 398 */     this.scrollControlls.setHorizontalGroup(horz);
/* 399 */     this.scrollControlls.setVerticalGroup(vert);
/*     */     
/* 401 */     super.insertChild(this.scrollControlls, 2);
/*     */   }
/*     */   
/*     */   void scrollTabs(int dir) {
/* 405 */     dir *= Math.max(1, this.tabBoxClip.getWidth() / 10);
/* 406 */     setScrollPos(this.tabScrollPosition + dir);
/*     */   }
/*     */   
/*     */   private void setScrollPos(int pos) {
/*     */     int maxPos;
/* 411 */     if (this.tabPosition.horz) {
/* 412 */       maxPos = this.tabBox.getWidth() - this.tabBoxClip.getWidth();
/*     */     } else {
/* 414 */       maxPos = this.tabBox.getHeight() - this.tabBoxClip.getHeight();
/*     */     } 
/* 416 */     pos = Math.max(0, Math.min(pos, maxPos));
/* 417 */     this.tabScrollPosition = pos;
/* 418 */     if (this.tabPosition.horz) {
/* 419 */       this.tabBox.setPosition(this.tabBoxClip.getX() - pos, this.tabBoxClip.getY());
/*     */     } else {
/* 421 */       this.tabBox.setPosition(this.tabBoxClip.getX(), this.tabBoxClip.getY() - pos);
/*     */     } 
/* 423 */     if (this.scrollControlls != null) {
/* 424 */       this.btnScrollLeft.setEnabled((pos > 0));
/* 425 */       this.btnScrollRight.setEnabled((pos < maxPos));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertChild(Widget child, int index) {
/* 431 */     throw new UnsupportedOperationException("use addTab/removeTab");
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAllChildren() {
/* 436 */     throw new UnsupportedOperationException("use addTab/removeTab");
/*     */   }
/*     */ 
/*     */   
/*     */   public Widget removeChild(int index) {
/* 441 */     throw new UnsupportedOperationException("use addTab/removeTab");
/*     */   }
/*     */   
/*     */   protected void updateTabStates() {
/* 445 */     for (int i = 0, n = this.tabs.size(); i < n; i++) {
/* 446 */       Tab tab = this.tabs.get(i);
/* 447 */       AnimationState animationState = tab.button.getAnimationState();
/* 448 */       animationState.setAnimationState(STATE_FIRST_TAB, (i == 0));
/* 449 */       animationState.setAnimationState(STATE_LAST_TAB, (i == n - 1));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void validateTab(Tab tab) {
/* 454 */     if (tab.button.getParent() != this.tabBox) {
/* 455 */       throw new IllegalArgumentException("Invalid tab");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class Tab
/*     */     extends HasCallback
/*     */     implements BooleanModel
/*     */   {
/* 466 */     final TabbedPane.TabButton button = new TabbedPane.TabButton(this); Widget pane; Runnable closeCallback;
/*     */     Object userValue;
/*     */     
/*     */     public boolean getValue() {
/* 470 */       return (TabbedPane.this.activeTab == this);
/*     */     }
/*     */     
/*     */     public void setValue(boolean value) {
/* 474 */       if (value) {
/* 475 */         TabbedPane.this.setActiveTab(this);
/*     */       }
/*     */     }
/*     */     
/*     */     public Widget getPane() {
/* 480 */       return this.pane;
/*     */     }
/*     */     
/*     */     public void setPane(Widget pane) {
/* 484 */       if (this.pane != pane) {
/* 485 */         if (this.pane != null) {
/* 486 */           TabbedPane.this.innerContainer.removeChild(this.pane);
/*     */         }
/* 488 */         this.pane = pane;
/* 489 */         if (pane != null) {
/* 490 */           pane.setVisible(getValue());
/* 491 */           TabbedPane.this.innerContainer.add(pane);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public Tab setTitle(String title) {
/* 497 */       this.button.setText(title);
/* 498 */       return this;
/*     */     }
/*     */     
/*     */     public String getTitle() {
/* 502 */       return this.button.getText();
/*     */     }
/*     */     
/*     */     public Object getUserValue() {
/* 506 */       return this.userValue;
/*     */     }
/*     */     
/*     */     public void setUserValue(Object userValue) {
/* 510 */       this.userValue = userValue;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Tab setTheme(String theme) {
/* 522 */       this.button.setUserTheme(theme);
/* 523 */       return this;
/*     */     }
/*     */     
/*     */     public Runnable getCloseCallback() {
/* 527 */       return this.closeCallback;
/*     */     }
/*     */     
/*     */     public void setCloseCallback(Runnable closeCallback) {
/* 531 */       if (this.closeCallback != null) {
/* 532 */         this.button.removeCloseButton();
/*     */       }
/* 534 */       this.closeCallback = closeCallback;
/* 535 */       if (closeCallback != null) {
/* 536 */         this.button.setCloseButton(closeCallback);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     protected void doCallback() {
/* 542 */       if (this.pane != null) {
/* 543 */         this.pane.setVisible(getValue());
/*     */       }
/* 545 */       super.doCallback();
/*     */     }
/*     */   }
/*     */   
/*     */   private static class TabButton extends ToggleButton {
/*     */     Button closeButton;
/*     */     Alignment closeButtonAlignment;
/*     */     int closeButtonOffsetX;
/*     */     int closeButtonOffsetY;
/*     */     String userTheme;
/*     */     
/*     */     TabButton(BooleanModel model) {
/* 557 */       super(model);
/* 558 */       setCanAcceptKeyboardFocus(false);
/* 559 */       this.closeButtonAlignment = Alignment.RIGHT;
/*     */     }
/*     */     
/*     */     public void setUserTheme(String userTheme) {
/* 563 */       this.userTheme = userTheme;
/* 564 */       doSetTheme();
/*     */     }
/*     */     
/*     */     private void doSetTheme() {
/* 568 */       if (this.userTheme != null) {
/* 569 */         setTheme(this.userTheme);
/* 570 */       } else if (this.closeButton != null) {
/* 571 */         setTheme("tabbuttonWithCloseButton");
/*     */       } else {
/* 573 */         setTheme("tabbutton");
/*     */       } 
/* 575 */       reapplyTheme();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void applyTheme(ThemeInfo themeInfo) {
/* 580 */       super.applyTheme(themeInfo);
/* 581 */       if (this.closeButton != null) {
/* 582 */         this.closeButtonAlignment = themeInfo.<Alignment>getParameter("closeButtonAlignment", Alignment.RIGHT);
/* 583 */         this.closeButtonOffsetX = themeInfo.getParameter("closeButtonOffsetX", 0);
/* 584 */         this.closeButtonOffsetY = themeInfo.getParameter("closeButtonOffsetY", 0);
/*     */       } else {
/* 586 */         this.closeButtonAlignment = Alignment.RIGHT;
/* 587 */         this.closeButtonOffsetX = 0;
/* 588 */         this.closeButtonOffsetY = 0;
/*     */       } 
/*     */     }
/*     */     
/*     */     void setCloseButton(Runnable callback) {
/* 593 */       this.closeButton = new Button();
/* 594 */       this.closeButton.setTheme("closeButton");
/* 595 */       doSetTheme();
/* 596 */       add(this.closeButton);
/* 597 */       this.closeButton.addCallback(callback);
/*     */     }
/*     */     
/*     */     void removeCloseButton() {
/* 601 */       removeChild(this.closeButton);
/* 602 */       this.closeButton = null;
/* 603 */       doSetTheme();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getPreferredInnerHeight() {
/* 608 */       return computeTextHeight();
/*     */     }
/*     */ 
/*     */     
/*     */     public int getPreferredInnerWidth() {
/* 613 */       return computeTextWidth();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void layout() {
/* 618 */       if (this.closeButton != null) {
/* 619 */         this.closeButton.adjustSize();
/* 620 */         this.closeButton.setPosition(getX() + this.closeButtonOffsetX + this.closeButtonAlignment.computePositionX(getWidth(), this.closeButton.getWidth()), getY() + this.closeButtonOffsetY + this.closeButtonAlignment.computePositionY(getHeight(), this.closeButton.getHeight()));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private class CB
/*     */     implements Runnable
/*     */   {
/*     */     final int dir;
/*     */     
/*     */     CB(int dir) {
/* 631 */       this.dir = dir;
/*     */     }
/*     */     
/*     */     public void run() {
/* 635 */       TabbedPane.this.scrollTabs(this.dir);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\TabbedPane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */