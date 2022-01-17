/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.IntegerModel;
/*     */ import de.matthiasmann.twl.model.ListModel;
/*     */ import de.matthiasmann.twl.renderer.Image;
/*     */ import de.matthiasmann.twl.utils.TypeMapping;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WheelWidget<T>
/*     */   extends Widget
/*     */ {
/*     */   private final TypeMapping<ItemRenderer> itemRenderer;
/*     */   private final L listener;
/*     */   private final R renderer;
/*     */   private final Runnable timerCB;
/*     */   protected int itemHeight;
/*     */   protected int numVisibleItems;
/*     */   protected Image selectedOverlay;
/*     */   private static final int TIMER_INTERVAL = 30;
/*     */   private static final int MIN_SPEED = 3;
/*     */   private static final int MAX_SPEED = 100;
/*     */   protected Timer timer;
/*     */   protected int dragStartY;
/*     */   protected long lastDragTime;
/*     */   protected long lastDragDelta;
/*     */   protected int lastDragDist;
/*     */   protected boolean hasDragStart;
/*     */   protected boolean dragActive;
/*     */   protected int scrollOffset;
/*     */   protected int scrollAmount;
/*     */   protected ListModel<T> model;
/*     */   protected IntegerModel selectedModel;
/*     */   protected int selected;
/*     */   protected boolean cyclic;
/*     */   
/*     */   public WheelWidget() {
/*  79 */     this.itemRenderer = new TypeMapping();
/*  80 */     this.listener = new L();
/*  81 */     this.renderer = new R();
/*  82 */     this.timerCB = new Runnable() {
/*     */         public void run() {
/*  84 */           WheelWidget.this.onTimer();
/*     */         }
/*     */       };
/*     */     
/*  88 */     this.itemRenderer.put(String.class, new StringItemRenderer());
/*     */     
/*  90 */     super.insertChild(this.renderer, 0);
/*  91 */     setCanAcceptKeyboardFocus(true);
/*     */   }
/*     */   
/*     */   public WheelWidget(ListModel<T> model) {
/*  95 */     this();
/*  96 */     this.model = model;
/*     */   }
/*     */   
/*     */   public ListModel<T> getModel() {
/* 100 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(ListModel<T> model) {
/* 104 */     removeListener();
/* 105 */     this.model = model;
/* 106 */     addListener();
/* 107 */     invalidateLayout();
/*     */   }
/*     */   
/*     */   public IntegerModel getSelectedModel() {
/* 111 */     return this.selectedModel;
/*     */   }
/*     */   
/*     */   public void setSelectedModel(IntegerModel selectedModel) {
/* 115 */     removeSelectedListener();
/* 116 */     this.selectedModel = selectedModel;
/* 117 */     addSelectedListener();
/*     */   }
/*     */   
/*     */   public int getSelected() {
/* 121 */     return this.selected;
/*     */   }
/*     */   
/*     */   public void setSelected(int selected) {
/* 125 */     int oldSelected = this.selected;
/* 126 */     if (oldSelected != selected) {
/* 127 */       this.selected = selected;
/* 128 */       if (this.selectedModel != null) {
/* 129 */         this.selectedModel.setValue(selected);
/*     */       }
/* 131 */       firePropertyChange("selected", oldSelected, selected);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isCyclic() {
/* 136 */     return this.cyclic;
/*     */   }
/*     */   
/*     */   public void setCyclic(boolean cyclic) {
/* 140 */     this.cyclic = cyclic;
/*     */   }
/*     */   
/*     */   public int getItemHeight() {
/* 144 */     return this.itemHeight;
/*     */   }
/*     */   
/*     */   public int getNumVisibleItems() {
/* 148 */     return this.numVisibleItems;
/*     */   }
/*     */   
/*     */   public boolean removeItemRenderer(Class<? extends T> clazz) {
/* 152 */     if (this.itemRenderer.remove(clazz)) {
/* 153 */       super.removeAllChildren();
/* 154 */       invalidateLayout();
/* 155 */       return true;
/*     */     } 
/* 157 */     return false;
/*     */   }
/*     */   
/*     */   public void registerItemRenderer(Class<? extends T> clazz, ItemRenderer value) {
/* 161 */     this.itemRenderer.put(clazz, value);
/* 162 */     invalidateLayout();
/*     */   }
/*     */   
/*     */   public void scroll(int amount) {
/* 166 */     scrollInt(amount);
/* 167 */     this.scrollAmount = 0;
/*     */   }
/*     */   
/*     */   protected void scrollInt(int amount) {
/* 171 */     int pos = this.selected;
/* 172 */     int half = this.itemHeight / 2;
/*     */     
/* 174 */     this.scrollOffset += amount;
/* 175 */     while (this.scrollOffset >= half) {
/* 176 */       this.scrollOffset -= this.itemHeight;
/* 177 */       pos++;
/*     */     } 
/* 179 */     while (this.scrollOffset <= -half) {
/* 180 */       this.scrollOffset += this.itemHeight;
/* 181 */       pos--;
/*     */     } 
/*     */     
/* 184 */     if (!this.cyclic) {
/* 185 */       int n = getNumEntries();
/* 186 */       if (n > 0) {
/* 187 */         while (pos >= n) {
/* 188 */           pos--;
/* 189 */           this.scrollOffset += this.itemHeight;
/*     */         } 
/*     */       }
/* 192 */       while (pos < 0) {
/* 193 */         pos++;
/* 194 */         this.scrollOffset -= this.itemHeight;
/*     */       } 
/* 196 */       this.scrollOffset = Math.max(-this.itemHeight, Math.min(this.itemHeight, this.scrollOffset));
/*     */     } 
/*     */     
/* 199 */     setSelected(pos);
/*     */     
/* 201 */     if (this.scrollOffset == 0 && this.scrollAmount == 0) {
/* 202 */       stopTimer();
/*     */     } else {
/* 204 */       startTimer();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void autoScroll(int dir) {
/* 209 */     if (dir != 0) {
/* 210 */       if (this.scrollAmount != 0 && Integer.signum(this.scrollAmount) != Integer.signum(dir)) {
/* 211 */         this.scrollAmount = dir;
/*     */       } else {
/* 213 */         this.scrollAmount += dir;
/*     */       } 
/* 215 */       startTimer();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 221 */     return this.numVisibleItems * this.itemHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 226 */     int width = 0;
/* 227 */     for (int i = 0, n = getNumEntries(); i < n; i++) {
/* 228 */       Widget w = getItemRenderer(i);
/* 229 */       if (w != null) {
/* 230 */         width = Math.max(width, w.getPreferredWidth());
/*     */       }
/*     */     } 
/* 233 */     return width;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintOverlay(GUI gui) {
/* 238 */     super.paintOverlay(gui);
/* 239 */     if (this.selectedOverlay != null) {
/* 240 */       int y = getInnerY() + this.itemHeight * this.numVisibleItems / 2;
/* 241 */       if ((this.numVisibleItems & 0x1) == 0) {
/* 242 */         y -= this.itemHeight / 2;
/*     */       }
/* 244 */       this.selectedOverlay.draw(getAnimationState(), getX(), y, getWidth(), this.itemHeight);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean handleEvent(Event evt) {
/* 250 */     if (evt.isMouseDragEnd() && this.dragActive) {
/* 251 */       int absDist = Math.abs(this.lastDragDist);
/* 252 */       if (absDist > 3 && this.lastDragDelta > 0L) {
/* 253 */         int amount = (int)Math.min(1000L, (absDist * 100) / this.lastDragDelta);
/* 254 */         autoScroll(amount * Integer.signum(this.lastDragDist));
/*     */       } 
/*     */       
/* 257 */       this.hasDragStart = false;
/* 258 */       this.dragActive = false;
/* 259 */       return true;
/*     */     } 
/*     */     
/* 262 */     if (evt.isMouseDragEvent()) {
/* 263 */       if (this.hasDragStart) {
/* 264 */         long time = getTime();
/* 265 */         this.dragActive = true;
/* 266 */         this.lastDragDist = this.dragStartY - evt.getMouseY();
/* 267 */         this.lastDragDelta = Math.max(1L, time - this.lastDragTime);
/* 268 */         scroll(this.lastDragDist);
/* 269 */         this.dragStartY = evt.getMouseY();
/* 270 */         this.lastDragTime = time;
/*     */       } 
/* 272 */       return true;
/*     */     } 
/*     */     
/* 275 */     if (super.handleEvent(evt)) {
/* 276 */       return true;
/*     */     }
/*     */     
/* 279 */     switch (evt.getType()) {
/*     */       case MOUSE_WHEEL:
/* 281 */         autoScroll(this.itemHeight * evt.getMouseWheelDelta());
/* 282 */         return true;
/*     */       
/*     */       case MOUSE_BTNDOWN:
/* 285 */         if (evt.getMouseButton() == 0) {
/* 286 */           this.dragStartY = evt.getMouseY();
/* 287 */           this.lastDragTime = getTime();
/* 288 */           this.hasDragStart = true;
/*     */         } 
/* 290 */         return true;
/*     */       
/*     */       case KEY_PRESSED:
/* 293 */         switch (evt.getKeyCode()) {
/*     */           case 200:
/* 295 */             autoScroll(-this.itemHeight);
/* 296 */             return true;
/*     */           case 208:
/* 298 */             autoScroll(this.itemHeight);
/* 299 */             return true;
/*     */         } 
/* 301 */         return false;
/*     */     } 
/*     */     
/* 304 */     return evt.isMouseEvent();
/*     */   }
/*     */   
/*     */   protected long getTime() {
/* 308 */     GUI gui = getGUI();
/* 309 */     return (gui != null) ? gui.getCurrentTime() : 0L;
/*     */   }
/*     */   
/*     */   protected int getNumEntries() {
/* 313 */     return (this.model == null) ? 0 : this.model.getNumEntries();
/*     */   }
/*     */   
/*     */   protected Widget getItemRenderer(int i) {
/* 317 */     T item = (T)this.model.getEntry(i);
/* 318 */     if (item != null) {
/* 319 */       ItemRenderer ir = (ItemRenderer)this.itemRenderer.get(item.getClass());
/* 320 */       if (ir != null) {
/* 321 */         Widget w = ir.getRenderWidget(item);
/* 322 */         if (w != null) {
/* 323 */           if (w.getParent() != this.renderer) {
/* 324 */             w.setVisible(false);
/* 325 */             this.renderer.add(w);
/*     */           } 
/* 327 */           return w;
/*     */         } 
/*     */       } 
/*     */     } 
/* 331 */     return null;
/*     */   }
/*     */   
/*     */   protected void startTimer() {
/* 335 */     if (this.timer != null && !this.timer.isRunning()) {
/* 336 */       this.timer.start();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void stopTimer() {
/* 341 */     if (this.timer != null) {
/* 342 */       this.timer.stop();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void onTimer() {
/* 347 */     int amount = this.scrollAmount;
/* 348 */     int newAmount = amount;
/*     */     
/* 350 */     if (amount == 0 && !this.dragActive) {
/* 351 */       amount = -this.scrollOffset;
/*     */     }
/*     */     
/* 354 */     if (amount != 0) {
/* 355 */       int absAmount = Math.abs(amount);
/* 356 */       int speed = absAmount * 30 / 200;
/* 357 */       int dir = Integer.signum(amount) * Math.min(absAmount, Math.max(3, Math.min(100, speed)));
/*     */ 
/*     */       
/* 360 */       if (newAmount != 0) {
/* 361 */         newAmount -= dir;
/*     */       }
/*     */       
/* 364 */       this.scrollAmount = newAmount;
/* 365 */       scrollInt(dir);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 371 */     layoutChildFullInnerArea(this.renderer);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 376 */     super.applyTheme(themeInfo);
/* 377 */     applyThemeWheel(themeInfo);
/*     */   }
/*     */   
/*     */   protected void applyThemeWheel(ThemeInfo themeInfo) {
/* 381 */     this.itemHeight = themeInfo.getParameter("itemHeight", 10);
/* 382 */     this.numVisibleItems = themeInfo.getParameter("visibleItems", 5);
/* 383 */     this.selectedOverlay = themeInfo.getImage("selectedOverlay");
/* 384 */     invalidateLayout();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void afterAddToGUI(GUI gui) {
/* 389 */     super.afterAddToGUI(gui);
/* 390 */     addListener();
/* 391 */     addSelectedListener();
/* 392 */     this.timer = gui.createTimer();
/* 393 */     this.timer.setCallback(this.timerCB);
/* 394 */     this.timer.setDelay(30);
/* 395 */     this.timer.setContinuous(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void beforeRemoveFromGUI(GUI gui) {
/* 400 */     this.timer.stop();
/* 401 */     this.timer = null;
/* 402 */     removeListener();
/* 403 */     removeSelectedListener();
/* 404 */     super.beforeRemoveFromGUI(gui);
/*     */   }
/*     */ 
/*     */   
/*     */   public void insertChild(Widget child, int index) throws UnsupportedOperationException {
/* 409 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAllChildren() throws UnsupportedOperationException {
/* 414 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public Widget removeChild(int index) throws UnsupportedOperationException {
/* 419 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   private void addListener() {
/* 423 */     if (this.model != null) {
/* 424 */       this.model.addChangeListener(this.listener);
/*     */     }
/*     */   }
/*     */   
/*     */   private void removeListener() {
/* 429 */     if (this.model != null) {
/* 430 */       this.model.removeChangeListener(this.listener);
/*     */     }
/*     */   }
/*     */   
/*     */   private void addSelectedListener() {
/* 435 */     if (this.selectedModel != null) {
/* 436 */       this.selectedModel.addCallback(this.listener);
/* 437 */       syncSelected();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void removeSelectedListener() {
/* 442 */     if (this.selectedModel != null) {
/* 443 */       this.selectedModel.removeCallback(this.listener);
/*     */     }
/*     */   }
/*     */   
/*     */   void syncSelected() {
/* 448 */     setSelected(this.selectedModel.getValue());
/*     */   }
/*     */   
/*     */   void entriesDeleted(int first, int last) {
/* 452 */     if (this.selected > first) {
/* 453 */       if (this.selected > last) {
/* 454 */         setSelected(this.selected - last - first + 1);
/*     */       } else {
/* 456 */         setSelected(first);
/*     */       } 
/*     */     }
/* 459 */     invalidateLayout();
/*     */   }
/*     */   
/*     */   void entriesInserted(int first, int last) {
/* 463 */     if (this.selected >= first) {
/* 464 */       setSelected(this.selected + last - first + 1);
/*     */     }
/* 466 */     invalidateLayout();
/*     */   }
/*     */   
/*     */   class L implements ListModel.ChangeListener, Runnable {
/*     */     public void allChanged() {
/* 471 */       WheelWidget.this.invalidateLayout();
/*     */     }
/*     */     public void entriesChanged(int first, int last) {
/* 474 */       WheelWidget.this.invalidateLayout();
/*     */     }
/*     */     public void entriesDeleted(int first, int last) {
/* 477 */       WheelWidget.this.entriesDeleted(first, last);
/*     */     }
/*     */     public void entriesInserted(int first, int last) {
/* 480 */       WheelWidget.this.entriesInserted(first, last);
/*     */     }
/*     */     public void run() {
/* 483 */       WheelWidget.this.syncSelected();
/*     */     }
/*     */   }
/*     */   
/*     */   class R extends Widget {
/*     */     public R() {
/* 489 */       setTheme("");
/* 490 */       setClip(true);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void paintWidget(GUI gui) {
/* 495 */       if (WheelWidget.this.model == null) {
/*     */         return;
/*     */       }
/*     */       
/* 499 */       int width = getInnerWidth();
/* 500 */       int x = getInnerX();
/* 501 */       int y = getInnerY();
/*     */       
/* 503 */       int numItems = WheelWidget.this.model.getNumEntries();
/* 504 */       int numDraw = WheelWidget.this.numVisibleItems;
/* 505 */       int startIdx = WheelWidget.this.selected - WheelWidget.this.numVisibleItems / 2;
/*     */       
/* 507 */       if ((numDraw & 0x1) == 0) {
/* 508 */         y -= WheelWidget.this.itemHeight / 2;
/* 509 */         numDraw++;
/*     */       } 
/*     */       
/* 512 */       if (WheelWidget.this.scrollOffset > 0) {
/* 513 */         y -= WheelWidget.this.scrollOffset;
/* 514 */         numDraw++;
/*     */       } 
/* 516 */       if (WheelWidget.this.scrollOffset < 0) {
/* 517 */         y -= WheelWidget.this.itemHeight + WheelWidget.this.scrollOffset;
/* 518 */         numDraw++;
/* 519 */         startIdx--;
/*     */       } 
/*     */       int i;
/* 522 */       label33: for (i = 0; i < numDraw; i++) {
/* 523 */         int idx = startIdx + i;
/*     */         
/* 525 */         while (idx < 0) {
/* 526 */           if (!WheelWidget.this.cyclic) {
/*     */             continue label33;
/*     */           }
/* 529 */           idx += numItems;
/*     */         } 
/*     */         
/* 532 */         while (idx >= numItems) {
/* 533 */           if (!WheelWidget.this.cyclic) {
/*     */             continue label33;
/*     */           }
/* 536 */           idx -= numItems;
/*     */         } 
/*     */         
/* 539 */         Widget w = WheelWidget.this.getItemRenderer(idx);
/* 540 */         if (w != null) {
/* 541 */           w.setSize(width, WheelWidget.this.itemHeight);
/* 542 */           w.setPosition(x, y + i * WheelWidget.this.itemHeight);
/* 543 */           w.validateLayout();
/* 544 */           paintChild(gui, w);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void invalidateLayout() {}
/*     */     
/*     */     protected void sizeChanged() {}
/*     */   }
/*     */   
/*     */   public static class StringItemRenderer
/*     */     extends Label
/*     */     implements ItemRenderer
/*     */   {
/*     */     public StringItemRenderer() {
/* 560 */       setCache(false);
/*     */     }
/*     */     
/*     */     public Widget getRenderWidget(Object data) {
/* 564 */       setText(String.valueOf(data));
/* 565 */       return this;
/*     */     }
/*     */     
/*     */     protected void sizeChanged() {}
/*     */   }
/*     */   
/*     */   public static interface ItemRenderer {
/*     */     Widget getRenderWidget(Object param1Object);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\WheelWidget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */