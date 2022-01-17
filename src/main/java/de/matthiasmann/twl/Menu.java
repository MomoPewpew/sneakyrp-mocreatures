/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.BooleanModel;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.utils.CallbackSupport;
/*     */ import de.matthiasmann.twl.utils.TypeMapping;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Menu
/*     */   extends MenuElement
/*     */   implements Iterable<MenuElement>
/*     */ {
/*  46 */   public static final AnimationState.StateKey STATE_HAS_OPEN_MENUS = AnimationState.StateKey.get("hasOpenMenus");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   private final ArrayList<MenuElement> elements = new ArrayList<MenuElement>();
/*  77 */   private final TypeMapping<Alignment> classAlignments = new TypeMapping();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String popupTheme;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Listener[] listeners;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Menu() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Menu(String name) {
/* 102 */     super(name);
/*     */   }
/*     */   
/*     */   public void addListener(Listener listener) {
/* 106 */     this.listeners = (Listener[])CallbackSupport.addCallbackToList((Object[])this.listeners, listener, Listener.class);
/*     */   }
/*     */   
/*     */   public void removeListener(Listener listener) {
/* 110 */     this.listeners = (Listener[])CallbackSupport.removeCallbackFromList((Object[])this.listeners, listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPopupTheme() {
/* 118 */     return this.popupTheme;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPopupTheme(String popupTheme) {
/* 126 */     String oldPopupTheme = this.popupTheme;
/* 127 */     this.popupTheme = popupTheme;
/* 128 */     firePropertyChange("popupTheme", oldPopupTheme, this.popupTheme);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClassAlignment(Class<? extends MenuElement> clazz, Alignment value) {
/* 139 */     if (value == null) {
/* 140 */       throw new NullPointerException("value");
/*     */     }
/* 142 */     if (value == Alignment.FILL) {
/* 143 */       this.classAlignments.remove(clazz);
/*     */     } else {
/* 145 */       this.classAlignments.put(clazz, value);
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
/*     */   public Alignment getClassAlignment(Class<? extends MenuElement> clazz) {
/* 157 */     Alignment alignment = (Alignment)this.classAlignments.get(clazz);
/* 158 */     if (alignment == null) {
/* 159 */       return Alignment.FILL;
/*     */     }
/* 161 */     return alignment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<MenuElement> iterator() {
/* 169 */     return this.elements.iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MenuElement get(int index) {
/* 180 */     return this.elements.get(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumElements() {
/* 188 */     return this.elements.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 195 */     this.elements.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Menu add(MenuElement e) {
/* 206 */     this.elements.add(e);
/* 207 */     return this;
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
/*     */   public Menu add(String name, Runnable cb) {
/* 219 */     return add(new MenuAction(name, cb));
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
/*     */   public Menu add(String name, BooleanModel model) {
/* 231 */     return add(new MenuCheckbox(name, model));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Menu addSpacer() {
/* 241 */     return add(new MenuSpacer());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createMenuBar(Widget container) {
/* 251 */     MenuManager mm = createMenuManager(container, true);
/* 252 */     for (Widget w : createWidgets(mm, 0)) {
/* 253 */       container.add(w);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Widget createMenuBar() {
/* 264 */     DialogLayout l = new DialogLayout();
/* 265 */     setWidgetTheme(l, "menubar");
/*     */     
/* 267 */     MenuManager mm = createMenuManager(l, true);
/* 268 */     Widget[] widgets = createWidgets(mm, 0);
/*     */     
/* 270 */     l.setHorizontalGroup(l.createSequentialGroup().addWidgetsWithGap("menuitem", widgets));
/* 271 */     l.setVerticalGroup(l.createParallelGroup(widgets));
/*     */     
/* 273 */     for (int i = 0, n = this.elements.size(); i < n; i++) {
/* 274 */       MenuElement e = this.elements.get(i);
/*     */       
/* 276 */       Alignment alignment = e.getAlignment();
/* 277 */       if (alignment == null) {
/* 278 */         alignment = getClassAlignment((Class)e.getClass());
/*     */       }
/*     */       
/* 281 */       l.setWidgetAlignment(widgets[i], alignment);
/*     */     } 
/*     */     
/* 284 */     l.getHorizontalGroup().addGap();
/* 285 */     return l;
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
/*     */   public MenuManager openPopupMenu(Widget parent) {
/* 297 */     MenuManager mm = createMenuManager(parent, false);
/* 298 */     mm.openSubMenu(0, this, parent, true);
/* 299 */     return mm;
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
/*     */   public MenuManager openPopupMenu(Widget parent, int x, int y) {
/* 312 */     MenuManager mm = createMenuManager(parent, false);
/* 313 */     Widget popup = mm.openSubMenu(0, this, parent, false);
/* 314 */     if (popup != null) {
/* 315 */       popup.setPosition(x, y);
/*     */     }
/* 317 */     return mm;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Widget createMenuWidget(MenuManager mm, int level) {
/* 322 */     SubMenuBtn smb = new SubMenuBtn(mm, level);
/* 323 */     setWidgetTheme(smb, "submenu");
/* 324 */     return smb;
/*     */   }
/*     */   
/*     */   protected MenuManager createMenuManager(Widget parent, boolean isMenuBar) {
/* 328 */     return new MenuManager(parent, isMenuBar);
/*     */   }
/*     */   
/*     */   protected Widget[] createWidgets(MenuManager mm, int level) {
/* 332 */     Widget[] widgets = new Widget[this.elements.size()];
/* 333 */     for (int i = 0, n = this.elements.size(); i < n; i++) {
/* 334 */       MenuElement e = this.elements.get(i);
/* 335 */       widgets[i] = e.createMenuWidget(mm, level);
/*     */     } 
/* 337 */     return widgets;
/*     */   }
/*     */   
/*     */   DialogLayout createPopup(MenuManager mm, int level, Widget btn) {
/* 341 */     if (this.listeners != null) {
/* 342 */       for (Listener l : this.listeners) {
/* 343 */         l.menuOpening(this);
/*     */       }
/*     */     }
/*     */     
/* 347 */     Widget[] widgets = createWidgets(mm, level);
/* 348 */     MenuPopup popup = new MenuPopup(btn, level, this);
/* 349 */     if (this.popupTheme != null) {
/* 350 */       popup.setTheme(this.popupTheme);
/*     */     }
/* 352 */     popup.setHorizontalGroup(popup.createParallelGroup(widgets));
/* 353 */     popup.setVerticalGroup(popup.createSequentialGroup().addWidgetsWithGap("menuitem", widgets));
/* 354 */     return popup;
/*     */   }
/*     */   
/*     */   void fireMenuOpened() {
/* 358 */     if (this.listeners != null) {
/* 359 */       for (Listener l : this.listeners) {
/* 360 */         l.menuOpened(this);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   void fireMenuClosed() {
/* 366 */     if (this.listeners != null)
/* 367 */       for (Listener l : this.listeners)
/* 368 */         l.menuClosed(this);  
/*     */   }
/*     */   public static interface Listener {
/*     */     void menuOpening(Menu param1Menu);
/*     */     void menuOpened(Menu param1Menu);
/*     */     
/*     */     void menuClosed(Menu param1Menu); }
/*     */   
/*     */   static class MenuPopup extends DialogLayout { private final Widget btn;
/*     */     
/*     */     MenuPopup(Widget btn, int level, Menu menu) {
/* 379 */       this.btn = btn;
/* 380 */       this.menu = menu;
/* 381 */       this.level = level;
/*     */     }
/*     */     private final Menu menu; final int level;
/*     */     
/*     */     protected void afterAddToGUI(GUI gui) {
/* 386 */       super.afterAddToGUI(gui);
/* 387 */       this.menu.fireMenuOpened();
/* 388 */       this.btn.getAnimationState().setAnimationState(Menu.STATE_HAS_OPEN_MENUS, true);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void beforeRemoveFromGUI(GUI gui) {
/* 393 */       this.btn.getAnimationState().setAnimationState(Menu.STATE_HAS_OPEN_MENUS, false);
/* 394 */       this.menu.fireMenuClosed();
/* 395 */       super.beforeRemoveFromGUI(gui);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean handleEvent(Event evt) {
/* 400 */       return (super.handleEvent(evt) || evt.isMouseEventNoWheel());
/*     */     } }
/*     */ 
/*     */   
/*     */   class SubMenuBtn
/*     */     extends MenuElement.MenuBtn implements Runnable {
/*     */     private final MenuManager mm;
/*     */     private final int level;
/*     */     
/*     */     public SubMenuBtn(MenuManager mm, int level) {
/* 410 */       this.mm = mm;
/* 411 */       this.level = level;
/*     */       
/* 413 */       addCallback(this);
/*     */     }
/*     */     
/*     */     public void run() {
/* 417 */       this.mm.openSubMenu(this.level, Menu.this, this, true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Menu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */