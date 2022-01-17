/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import java.util.IdentityHashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MenuManager
/*     */   extends PopupWindow
/*     */ {
/*     */   private final boolean isMenuBar;
/*     */   private final IdentityHashMap<MenuElement, Widget> popups;
/*     */   private final Runnable closeCB;
/*     */   private final Runnable timerCB;
/*     */   private boolean mouseOverOwner;
/*     */   private Widget lastMouseOverWidget;
/*     */   private Timer timer;
/*     */   
/*     */   public MenuManager(Widget owner, boolean isMenuBar) {
/*  50 */     super(owner);
/*  51 */     this.isMenuBar = isMenuBar;
/*  52 */     this.popups = new IdentityHashMap<MenuElement, Widget>();
/*  53 */     this.closeCB = new Runnable() {
/*     */         public void run() {
/*  55 */           MenuManager.this.closePopup();
/*     */         }
/*     */       };
/*  58 */     this.timerCB = new Runnable() {
/*     */         public void run() {
/*  60 */           MenuManager.this.popupTimer();
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public Runnable getCloseCallback() {
/*  66 */     return this.closeCB;
/*     */   }
/*     */   
/*     */   boolean isSubMenuOpen(Menu menu) {
/*  70 */     Widget popup = this.popups.get(menu);
/*  71 */     if (popup != null) {
/*  72 */       return (popup.getParent() == this);
/*     */     }
/*  74 */     return false;
/*     */   }
/*     */   
/*     */   void closeSubMenu(int level) {
/*  78 */     while (getNumChildren() > level) {
/*  79 */       closeSubMenu();
/*     */     }
/*     */   }
/*     */   
/*     */   Widget openSubMenu(int level, Menu menu, Widget btn, boolean setPosition) {
/*  84 */     Widget popup = this.popups.get(menu);
/*  85 */     if (popup == null) {
/*  86 */       popup = menu.createPopup(this, level + 1, btn);
/*  87 */       this.popups.put(menu, popup);
/*     */     } 
/*     */     
/*  90 */     if (popup.getParent() == this) {
/*  91 */       closeSubMenu(level + 1);
/*  92 */       return popup;
/*     */     } 
/*     */     
/*  95 */     if (!isOpen()) {
/*  96 */       if (!openPopup()) {
/*  97 */         closePopup();
/*  98 */         return null;
/*     */       } 
/* 100 */       getParent().layoutChildFullInnerArea(this);
/*     */     } 
/*     */     
/* 103 */     while (getNumChildren() > level) {
/* 104 */       closeSubMenu();
/*     */     }
/* 106 */     add(popup);
/*     */     
/* 108 */     popup.adjustSize();
/*     */     
/* 110 */     if (setPosition) {
/* 111 */       int popupWidth = popup.getWidth();
/* 112 */       int popupX = btn.getRight();
/* 113 */       int popupY = btn.getY();
/*     */       
/* 115 */       if (level == 0) {
/* 116 */         popupX = btn.getX();
/* 117 */         popupY = btn.getBottom();
/*     */       } 
/*     */       
/* 120 */       if (popupWidth + btn.getRight() > getInnerRight()) {
/* 121 */         popupX = btn.getX() - popupWidth;
/* 122 */         if (popupX < getInnerX()) {
/* 123 */           popupX = getInnerRight() - popupWidth;
/*     */         }
/*     */       } 
/*     */       
/* 127 */       int popupHeight = popup.getHeight();
/* 128 */       if (popupY + popupHeight > getInnerBottom()) {
/* 129 */         popupY = Math.max(getInnerY(), getInnerBottom() - popupHeight);
/*     */       }
/*     */       
/* 132 */       popup.setPosition(popupX, popupY);
/*     */     } 
/*     */     
/* 135 */     return popup;
/*     */   }
/*     */   
/*     */   void closeSubMenu() {
/* 139 */     removeChild(getNumChildren() - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void closePopup() {
/* 144 */     stopTimer();
/* 145 */     GUI gui = getGUI();
/* 146 */     super.closePopup();
/* 147 */     removeAllChildren();
/* 148 */     this.popups.clear();
/* 149 */     if (gui != null) {
/* 150 */       gui.resendLastMouseMove();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Widget getPopupForMenu(Menu menu) {
/* 160 */     return this.popups.get(menu);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void afterAddToGUI(GUI gui) {
/* 165 */     super.afterAddToGUI(gui);
/* 166 */     this.timer = gui.createTimer();
/* 167 */     this.timer.setDelay(300);
/* 168 */     this.timer.setCallback(this.timerCB);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void layout() {}
/*     */ 
/*     */   
/*     */   Widget routeMouseEvent(Event evt) {
/* 177 */     this.mouseOverOwner = false;
/* 178 */     Widget widget = super.routeMouseEvent(evt);
/* 179 */     if (widget == this && this.isMenuBar && getOwner().isMouseInside(evt)) {
/* 180 */       Widget menuBarWidget = getOwner().routeMouseEvent(evt);
/* 181 */       if (menuBarWidget != null) {
/* 182 */         this.mouseOverOwner = true;
/* 183 */         widget = menuBarWidget;
/*     */       } 
/*     */     } 
/*     */     
/* 187 */     Widget mouseOverWidget = getWidgetUnderMouse();
/* 188 */     if (this.lastMouseOverWidget != mouseOverWidget) {
/* 189 */       this.lastMouseOverWidget = mouseOverWidget;
/* 190 */       if (this.isMenuBar && widget.getParent() == getOwner() && widget instanceof Menu.SubMenuBtn) {
/* 191 */         popupTimer();
/*     */       } else {
/* 193 */         startTimer();
/*     */       } 
/*     */     } 
/*     */     
/* 197 */     return widget;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean handleEventPopup(Event evt) {
/* 202 */     if (this.isMenuBar && getOwner().handleEvent(evt)) {
/* 203 */       return true;
/*     */     }
/* 205 */     if (super.handleEventPopup(evt)) {
/* 206 */       return true;
/*     */     }
/* 208 */     if (evt.getType() == Event.Type.MOUSE_CLICKED) {
/* 209 */       mouseClickedOutside(evt);
/* 210 */       return true;
/*     */     } 
/* 212 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   Widget getWidgetUnderMouse() {
/* 217 */     if (this.mouseOverOwner) {
/* 218 */       return getOwner().getWidgetUnderMouse();
/*     */     }
/* 220 */     return super.getWidgetUnderMouse();
/*     */   }
/*     */   
/*     */   void popupTimer() {
/* 224 */     if (this.lastMouseOverWidget instanceof Menu.SubMenuBtn && this.lastMouseOverWidget.isEnabled()) {
/* 225 */       ((Menu.SubMenuBtn)this.lastMouseOverWidget).run();
/* 226 */     } else if (this.lastMouseOverWidget != this) {
/* 227 */       int level = 0;
/*     */ 
/*     */       
/* 230 */       for (Widget w = this.lastMouseOverWidget; w != null; w = w.getParent()) {
/* 231 */         if (w instanceof Menu.MenuPopup) {
/* 232 */           level = ((Menu.MenuPopup)w).level;
/*     */           break;
/*     */         } 
/*     */       } 
/* 236 */       closeSubMenu(level);
/*     */     } 
/*     */   }
/*     */   
/*     */   void startTimer() {
/* 241 */     if (this.timer != null) {
/* 242 */       this.timer.stop();
/* 243 */       this.timer.start();
/*     */     } 
/*     */   }
/*     */   
/*     */   void stopTimer() {
/* 248 */     if (this.timer != null)
/* 249 */       this.timer.stop(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\MenuManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */