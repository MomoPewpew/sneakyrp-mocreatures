/*      */ package de.matthiasmann.twl;
/*      */ 
/*      */ import de.matthiasmann.twl.input.Input;
/*      */ import de.matthiasmann.twl.input.lwjgl.LWJGLInput;
/*      */ import de.matthiasmann.twl.renderer.AnimationState;
/*      */ import de.matthiasmann.twl.renderer.MouseCursor;
/*      */ import de.matthiasmann.twl.renderer.Renderer;
/*      */ import de.matthiasmann.twl.theme.ThemeManager;
/*      */ import java.util.ArrayList;
/*      */ import java.util.concurrent.Callable;
/*      */ import java.util.concurrent.ExecutorService;
/*      */ import java.util.concurrent.Executors;
/*      */ import java.util.concurrent.Future;
/*      */ import java.util.concurrent.ThreadFactory;
/*      */ import java.util.concurrent.atomic.AtomicInteger;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class GUI
/*      */   extends Widget
/*      */ {
/*      */   private static final int DRAG_DIST = 3;
/*      */   private static final int DBLCLICK_TIME = 500;
/*      */   private static final int KEYREPEAT_INITIAL_DELAY = 250;
/*      */   private static final int KEYREPEAT_INTERVAL_DELAY = 33;
/*      */   private static final int NO_REPEAT = 0;
/*   88 */   private int tooltipOffsetX = 0;
/*   89 */   private int tooltipOffsetY = 0;
/*   90 */   private int tooltipDelay = 1000;
/*   91 */   private int tooltipReappearDelay = 100;
/*      */   
/*      */   private final Renderer renderer;
/*      */   
/*      */   private final Input input;
/*      */   
/*      */   long curTime;
/*      */   
/*      */   private int deltaTime;
/*      */   private Widget rootPane;
/*      */   boolean hasInvalidLayouts;
/*      */   final Event event;
/*      */   private boolean wasInside;
/*      */   private boolean dragActive;
/*      */   private int mouseClickCount;
/*  106 */   private int dragButton = -1;
/*      */   
/*      */   private int mouseDownX;
/*      */   private int mouseDownY;
/*      */   private int mouseLastX;
/*      */   private int mouseLastY;
/*      */   private int mouseClickedX;
/*      */   private int mouseClickedY;
/*      */   private long mouseEventTime;
/*      */   private long tooltipEventTime;
/*      */   private long mouseClickedTime;
/*      */   private long keyEventTime;
/*      */   private int keyRepeatDelay;
/*      */   private boolean popupEventOccured;
/*      */   private Widget lastMouseDownWidget;
/*      */   private Widget lastMouseClickWidget;
/*      */   private PopupWindow boundDragPopup;
/*      */   private Runnable boundDragCallback;
/*      */   private Widget focusKeyWidget;
/*  125 */   private int mouseIdleTime = 60;
/*      */   
/*      */   private boolean mouseIdleState;
/*      */   
/*      */   private MouseIdleListener mouseIdleListener;
/*      */   
/*      */   private InfoWindow activeInfoWindow;
/*      */   
/*      */   private final Widget infoWindowPlaceholder;
/*      */   
/*      */   private final TooltipWindow tooltipWindow;
/*      */   
/*      */   private final Label tooltipLabel;
/*      */   
/*      */   private Widget tooltipOwner;
/*      */   
/*      */   private boolean hadOpenTooltip;
/*      */   
/*      */   private long tooltipClosedTime;
/*      */   
/*      */   final ArrayList<Timer> activeTimers;
/*      */   
/*      */   final ExecutorService executorService;
/*      */   
/*      */   private final Object invokeLock;
/*      */   
/*      */   private Runnable[] invokeLaterQueue;
/*      */   private int invokeLaterQueueSize;
/*      */   private Runnable[] invokeRunnables;
/*      */   private static final int FOCUS_KEY = 15;
/*      */   
/*      */   public GUI(Renderer renderer) {
/*  157 */     this(new Widget(), renderer);
/*  158 */     this.rootPane.setTheme("");
/*  159 */     this.rootPane.setFocusKeyEnabled(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GUI(Widget rootPane, Renderer renderer) {
/*  170 */     this(rootPane, renderer, (Input)new LWJGLInput());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GUI(Widget rootPane, Renderer renderer, Input input) {
/*  182 */     if (rootPane == null) {
/*  183 */       throw new IllegalArgumentException("rootPane is null");
/*      */     }
/*  185 */     if (renderer == null) {
/*  186 */       throw new IllegalArgumentException("renderer is null");
/*      */     }
/*      */     
/*  189 */     this.guiInstance = this;
/*  190 */     this.renderer = renderer;
/*  191 */     this.input = input;
/*  192 */     this.event = new Event();
/*  193 */     this.rootPane = rootPane;
/*  194 */     this.rootPane.setFocusKeyEnabled(false);
/*      */     
/*  196 */     this.infoWindowPlaceholder = new Widget();
/*  197 */     this.infoWindowPlaceholder.setTheme("");
/*      */     
/*  199 */     this.tooltipLabel = new Label();
/*  200 */     this.tooltipWindow = new TooltipWindow();
/*  201 */     this.tooltipWindow.setVisible(false);
/*      */     
/*  203 */     this.activeTimers = new ArrayList<Timer>();
/*  204 */     this.executorService = Executors.newSingleThreadExecutor(new TF());
/*  205 */     this.invokeLock = new Object();
/*  206 */     this.invokeLaterQueue = new Runnable[16];
/*  207 */     this.invokeRunnables = new Runnable[16];
/*      */     
/*  209 */     setTheme("");
/*  210 */     setFocusKeyEnabled(false);
/*  211 */     setSize();
/*      */     
/*  213 */     super.insertChild(rootPane, 0);
/*  214 */     super.insertChild(this.infoWindowPlaceholder, 1);
/*  215 */     super.insertChild(this.tooltipWindow, 2);
/*      */     
/*  217 */     resyncTimerAfterPause();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void applyTheme(ThemeManager themeManager) {
/*  231 */     if (themeManager == null) {
/*  232 */       throw new IllegalArgumentException("themeManager is null");
/*      */     }
/*      */     
/*  235 */     super.applyTheme(themeManager);
/*      */   }
/*      */   
/*      */   public Widget getRootPane() {
/*  239 */     return this.rootPane;
/*      */   }
/*      */   
/*      */   public void setRootPane(Widget rootPane) {
/*  243 */     if (rootPane == null) {
/*  244 */       throw new IllegalArgumentException("rootPane is null");
/*      */     }
/*  246 */     this.rootPane = rootPane;
/*  247 */     super.removeChild(0);
/*  248 */     super.insertChild(rootPane, 0);
/*      */   }
/*      */   
/*      */   public Renderer getRenderer() {
/*  252 */     return this.renderer;
/*      */   }
/*      */   
/*      */   public Input getInput() {
/*  256 */     return this.input;
/*      */   }
/*      */   
/*      */   public MouseSensitiveRectangle createMouseSenitiveRectangle() {
/*  260 */     return new MouseSensitiveRectangle()
/*      */       {
/*      */         public boolean isMouseOver()
/*      */         {
/*  264 */           return isInside(GUI.this.event.mouseX, GUI.this.event.mouseY);
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Timer createTimer() {
/*  274 */     return new Timer(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getCurrentTime() {
/*  284 */     return this.curTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCurrentDeltaTime() {
/*  294 */     return this.deltaTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invokeLater(Runnable runnable) {
/*  305 */     if (runnable == null) {
/*  306 */       throw new IllegalArgumentException("runnable is null");
/*      */     }
/*  308 */     synchronized (this.invokeLock) {
/*  309 */       if (this.invokeLaterQueueSize == this.invokeLaterQueue.length) {
/*  310 */         growInvokeLaterQueue();
/*      */       }
/*  312 */       this.invokeLaterQueue[this.invokeLaterQueueSize++] = runnable;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <V> Future<V> invokeAsync(Callable<V> job, AsyncCompletionListener<V> listener) {
/*  332 */     if (job == null) {
/*  333 */       throw new IllegalArgumentException("job is null");
/*      */     }
/*  335 */     if (listener == null) {
/*  336 */       throw new IllegalArgumentException("listener is null");
/*      */     }
/*  338 */     return this.executorService.submit(new AC<V>(job, null, listener));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <V> Future<V> invokeAsync(Runnable job, AsyncCompletionListener<V> listener) {
/*  357 */     if (job == null) {
/*  358 */       throw new IllegalArgumentException("job is null");
/*      */     }
/*  360 */     if (listener == null) {
/*  361 */       throw new IllegalArgumentException("listener is null");
/*      */     }
/*  363 */     return this.executorService.submit(new AC<V>(null, job, listener));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean requestToolTip(Widget widget, int x, int y, Object content, Alignment alignment) {
/*  368 */     if (alignment == null) {
/*  369 */       throw new IllegalArgumentException("alignment is null");
/*      */     }
/*  371 */     if (widget == getWidgetUnderMouse()) {
/*  372 */       setTooltip(x, y, widget, content, alignment);
/*  373 */       return true;
/*      */     } 
/*  375 */     return false;
/*      */   }
/*      */   
/*      */   public MouseIdleListener getMouseIdleListener() {
/*  379 */     return this.mouseIdleListener;
/*      */   }
/*      */   
/*      */   public void setMouseIdleListener(MouseIdleListener mouseIdleListener) {
/*  383 */     this.mouseIdleListener = mouseIdleListener;
/*  384 */     callMouseIdleListener();
/*      */   }
/*      */   
/*      */   public int getMouseIdleTime() {
/*  388 */     return this.mouseIdleTime;
/*      */   }
/*      */   
/*      */   public void setMouseIdleTime(int mouseIdleTime) {
/*  392 */     if (mouseIdleTime < 1) {
/*  393 */       throw new IllegalArgumentException("mouseIdleTime < 1");
/*      */     }
/*  395 */     this.mouseIdleTime = mouseIdleTime;
/*      */   }
/*      */   
/*      */   public int getTooltipDelay() {
/*  399 */     return this.tooltipDelay;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTooltipDelay(int tooltipDelay) {
/*  407 */     if (tooltipDelay < 1) {
/*  408 */       throw new IllegalArgumentException("tooltipDelay");
/*      */     }
/*  410 */     this.tooltipDelay = tooltipDelay;
/*      */   }
/*      */   
/*      */   public int getTooltipReappearDelay() {
/*  414 */     return this.tooltipReappearDelay;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTooltipReappearDelay(int tooltipReappearDelay) {
/*  423 */     this.tooltipReappearDelay = tooltipReappearDelay;
/*      */   }
/*      */   
/*      */   public int getTooltipOffsetX() {
/*  427 */     return this.tooltipOffsetX;
/*      */   }
/*      */   
/*      */   public int getTooltipOffsetY() {
/*  431 */     return this.tooltipOffsetY;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTooltipOffset(int tooltipOffsetX, int tooltipOffsetY) {
/*  440 */     this.tooltipOffsetX = tooltipOffsetX;
/*  441 */     this.tooltipOffsetY = tooltipOffsetY;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTooltipWindowRenderOffscreen(Widget.RenderOffscreen renderOffscreen) {
/*  452 */     this.tooltipWindow.setRenderOffscreen(renderOffscreen);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTooltipWindowTheme(String theme) {
/*  462 */     this.tooltipWindow.setTheme(theme);
/*  463 */     this.tooltipWindow.reapplyTheme();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setPosition(int x, int y) {
/*  472 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void insertChild(Widget child, int index) {
/*  481 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeAllChildren() {
/*  490 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Widget removeChild(int index) {
/*  499 */     throw new UnsupportedOperationException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void adjustSize() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void layout() {
/*  511 */     layoutChildFullInnerArea(this.rootPane);
/*      */   }
/*      */ 
/*      */   
/*      */   public void validateLayout() {
/*  516 */     if (this.hasInvalidLayouts) {
/*  517 */       int MAX_ITERATIONS = 1000;
/*  518 */       int iterations = 0;
/*  519 */       while (this.hasInvalidLayouts && iterations < 1000) {
/*  520 */         this.hasInvalidLayouts = false;
/*  521 */         super.validateLayout();
/*  522 */         iterations++;
/*      */       } 
/*  524 */       ArrayList<Widget> widgetsInLoop = null;
/*  525 */       if (this.hasInvalidLayouts) {
/*  526 */         widgetsInLoop = new ArrayList<Widget>();
/*  527 */         collectLayoutLoop(widgetsInLoop);
/*      */       } 
/*  529 */       DebugHook.getDebugHook().guiLayoutValidated(iterations, widgetsInLoop);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSize() {
/*  537 */     setSize(this.renderer.getWidth(), this.renderer.getHeight());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void update() {
/*  560 */     setSize();
/*  561 */     updateTime();
/*  562 */     handleInput();
/*  563 */     handleKeyRepeat();
/*  564 */     handleTooltips();
/*  565 */     updateTimers();
/*  566 */     invokeRunables();
/*  567 */     validateLayout();
/*  568 */     draw();
/*  569 */     setCursor();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resyncTimerAfterPause() {
/*  578 */     this.curTime = this.renderer.getTimeMillis();
/*  579 */     this.deltaTime = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateTime() {
/*  594 */     long newTime = this.renderer.getTimeMillis();
/*  595 */     this.deltaTime = Math.max(0, (int)(newTime - this.curTime));
/*  596 */     this.curTime = newTime;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateTimers() {
/*  607 */     for (int i = 0; i < this.activeTimers.size(); ) {
/*  608 */       if (!((Timer)this.activeTimers.get(i)).tick(this.deltaTime)) {
/*  609 */         this.activeTimers.remove(i); continue;
/*      */       } 
/*  611 */       i++;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void invokeRunables() {
/*      */     int count;
/*  622 */     Runnable[] runnables = null;
/*      */     
/*  624 */     synchronized (this.invokeLock) {
/*  625 */       count = this.invokeLaterQueueSize;
/*  626 */       if (count > 0) {
/*  627 */         this.invokeLaterQueueSize = 0;
/*  628 */         runnables = this.invokeLaterQueue;
/*  629 */         this.invokeLaterQueue = this.invokeRunnables;
/*  630 */         this.invokeRunnables = runnables;
/*      */       } 
/*      */     } 
/*  633 */     for (int i = 0; i < count; ) {
/*  634 */       Runnable r = runnables[i];
/*  635 */       runnables[i++] = null;
/*      */       try {
/*  637 */         r.run();
/*  638 */       } catch (Throwable ex) {
/*  639 */         Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, "Exception in runnable", ex);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void draw() {
/*  652 */     if (this.renderer.startRendering()) {
/*      */       try {
/*  654 */         drawWidget(this);
/*      */         
/*  656 */         if (this.dragActive && this.boundDragPopup == null && this.lastMouseDownWidget != null) {
/*  657 */           this.lastMouseDownWidget.paintDragOverlay(this, this.event.mouseX, this.event.mouseY, this.event.modifier);
/*      */         }
/*      */       } finally {
/*      */         
/*  661 */         this.renderer.endRendering();
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCursor() {
/*  677 */     this.event.type = Event.Type.MOUSE_MOVED;
/*  678 */     Widget widget = getWidgetUnderMouse();
/*  679 */     MouseCursor cursor = null;
/*  680 */     while (widget != null) {
/*  681 */       if (widget.isEnabled()) {
/*  682 */         cursor = widget.getMouseCursor(this.event);
/*  683 */         if (cursor != null) {
/*      */           break;
/*      */         }
/*      */       } 
/*  687 */       widget = widget.getParent();
/*      */     } 
/*  689 */     this.renderer.setCursor(cursor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleInput() {
/*  707 */     if (this.input != null && !this.input.pollInput(this)) {
/*  708 */       clearKeyboardState();
/*  709 */       clearMouseState();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean handleMouse(int mouseX, int mouseY, int button, boolean pressed) {
/*  723 */     this.mouseEventTime = this.curTime;
/*  724 */     this.tooltipEventTime = this.curTime;
/*  725 */     this.event.mouseButton = button;
/*      */ 
/*      */     
/*  728 */     int prevButtonState = this.event.modifier & 0x1C0;
/*      */     
/*  730 */     int buttonMask = 0;
/*  731 */     switch (button) {
/*      */       case 0:
/*  733 */         buttonMask = 64;
/*      */         break;
/*      */       case 1:
/*  736 */         buttonMask = 128;
/*      */         break;
/*      */       case 2:
/*  739 */         buttonMask = 256;
/*      */         break;
/*      */     } 
/*  742 */     this.event.setModifier(buttonMask, pressed);
/*  743 */     boolean wasPressed = ((prevButtonState & buttonMask) != 0);
/*      */     
/*  745 */     if (buttonMask != 0) {
/*  746 */       this.renderer.setMouseButton(button, pressed);
/*      */     }
/*      */ 
/*      */     
/*  750 */     if (this.dragActive || prevButtonState == 0) {
/*  751 */       this.event.mouseX = mouseX;
/*  752 */       this.event.mouseY = mouseY;
/*      */     } else {
/*  754 */       this.event.mouseX = this.mouseDownX;
/*  755 */       this.event.mouseY = this.mouseDownY;
/*      */     } 
/*      */     
/*  758 */     boolean handled = this.dragActive;
/*      */     
/*  760 */     if (!this.dragActive) {
/*  761 */       if (!isInside(mouseX, mouseY)) {
/*  762 */         pressed = false;
/*  763 */         this.mouseClickCount = 0;
/*  764 */         if (this.wasInside) {
/*  765 */           sendMouseEvent(Event.Type.MOUSE_EXITED, (Widget)null);
/*  766 */           this.wasInside = false;
/*      */         } 
/*  768 */       } else if (!this.wasInside) {
/*  769 */         this.wasInside = true;
/*  770 */         if (sendMouseEvent(Event.Type.MOUSE_ENTERED, (Widget)null) != null) {
/*  771 */           handled = true;
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*  776 */     if (mouseX != this.mouseLastX || mouseY != this.mouseLastY) {
/*  777 */       this.mouseLastX = mouseX;
/*  778 */       this.mouseLastY = mouseY;
/*      */       
/*  780 */       if (prevButtonState != 0 && !this.dragActive && (
/*  781 */         Math.abs(mouseX - this.mouseDownX) > 3 || Math.abs(mouseY - this.mouseDownY) > 3)) {
/*      */         
/*  783 */         this.dragActive = true;
/*  784 */         this.mouseClickCount = 0;
/*      */         
/*  786 */         hideTooltip();
/*  787 */         this.hadOpenTooltip = false;
/*      */ 
/*      */         
/*  790 */         this.tooltipOwner = this.lastMouseDownWidget;
/*      */       } 
/*      */ 
/*      */       
/*  794 */       if (this.dragActive) {
/*  795 */         if (this.boundDragPopup != null) {
/*      */           
/*  797 */           assert getTopPane() == this.boundDragPopup;
/*  798 */           sendMouseEvent(Event.Type.MOUSE_MOVED, (Widget)null);
/*  799 */         } else if (this.lastMouseDownWidget != null) {
/*      */           
/*  801 */           sendMouseEvent(Event.Type.MOUSE_DRAGGED, this.lastMouseDownWidget);
/*      */         } 
/*  803 */       } else if (prevButtonState == 0 && 
/*  804 */         sendMouseEvent(Event.Type.MOUSE_MOVED, (Widget)null) != null) {
/*  805 */         handled = true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  810 */     if (buttonMask != 0 && pressed != wasPressed) {
/*  811 */       if (pressed) {
/*  812 */         if (this.dragButton < 0) {
/*  813 */           this.mouseDownX = mouseX;
/*  814 */           this.mouseDownY = mouseY;
/*  815 */           this.dragButton = button;
/*  816 */           this.lastMouseDownWidget = sendMouseEvent(Event.Type.MOUSE_BTNDOWN, (Widget)null);
/*  817 */         } else if (this.lastMouseDownWidget != null && this.boundDragPopup == null) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  822 */           sendMouseEvent(Event.Type.MOUSE_BTNDOWN, this.lastMouseDownWidget);
/*      */         } 
/*  824 */       } else if (this.dragButton >= 0 && (this.boundDragPopup == null || this.event.isMouseDragEnd())) {
/*      */         
/*  826 */         if (this.boundDragPopup != null && 
/*  827 */           button == this.dragButton)
/*      */         {
/*  829 */           sendMouseEvent(Event.Type.MOUSE_BTNUP, getWidgetUnderMouse());
/*      */         }
/*      */         
/*  832 */         if (this.lastMouseDownWidget != null)
/*      */         {
/*  834 */           sendMouseEvent(Event.Type.MOUSE_BTNUP, this.lastMouseDownWidget);
/*      */         }
/*      */       } 
/*      */       
/*  838 */       if (this.lastMouseDownWidget != null) {
/*  839 */         handled = true;
/*      */       }
/*      */       
/*  842 */       if (button == 0 && !this.popupEventOccured && 
/*  843 */         !pressed && !this.dragActive) {
/*  844 */         if (this.mouseClickCount == 0 || this.curTime - this.mouseClickedTime > 500L || this.lastMouseClickWidget != this.lastMouseDownWidget) {
/*      */ 
/*      */           
/*  847 */           this.mouseClickedX = mouseX;
/*  848 */           this.mouseClickedY = mouseY;
/*  849 */           this.lastMouseClickWidget = this.lastMouseDownWidget;
/*  850 */           this.mouseClickCount = 0;
/*  851 */           this.mouseClickedTime = this.curTime;
/*      */         } 
/*  853 */         if (Math.abs(mouseX - this.mouseClickedX) < 3 && Math.abs(mouseY - this.mouseClickedY) < 3) {
/*      */ 
/*      */           
/*  856 */           this.event.mouseX = this.mouseClickedX;
/*  857 */           this.event.mouseY = this.mouseClickedY;
/*  858 */           this.event.mouseClickCount = ++this.mouseClickCount;
/*  859 */           this.mouseClickedTime = this.curTime;
/*  860 */           if (this.lastMouseClickWidget != null) {
/*  861 */             sendMouseEvent(Event.Type.MOUSE_CLICKED, this.lastMouseClickWidget);
/*      */           }
/*      */         } else {
/*  864 */           this.lastMouseClickWidget = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  870 */     if (this.event.isMouseDragEnd()) {
/*  871 */       if (this.dragActive) {
/*  872 */         this.dragActive = false;
/*  873 */         sendMouseEvent(Event.Type.MOUSE_MOVED, (Widget)null);
/*      */       } 
/*  875 */       this.dragButton = -1;
/*  876 */       if (this.boundDragCallback != null) {
/*      */         try {
/*  878 */           this.boundDragCallback.run();
/*  879 */         } catch (Exception ex) {
/*  880 */           Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, "Exception in bound drag callback", ex);
/*      */         } finally {
/*      */           
/*  883 */           this.boundDragCallback = null;
/*  884 */           this.boundDragPopup = null;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  889 */     return handled;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearMouseState() {
/*  899 */     this.event.setModifier(64, false);
/*  900 */     this.event.setModifier(256, false);
/*  901 */     this.event.setModifier(128, false);
/*  902 */     this.renderer.setMouseButton(0, false);
/*  903 */     this.renderer.setMouseButton(2, false);
/*  904 */     this.renderer.setMouseButton(1, false);
/*  905 */     this.lastMouseClickWidget = null;
/*  906 */     this.mouseClickCount = 0;
/*  907 */     this.mouseClickedTime = this.curTime;
/*  908 */     this.boundDragPopup = null;
/*  909 */     this.boundDragCallback = null;
/*  910 */     if (this.dragActive) {
/*  911 */       this.dragActive = false;
/*  912 */       sendMouseEvent(Event.Type.MOUSE_MOVED, (Widget)null);
/*      */     } 
/*  914 */     this.dragButton = -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean handleMouseWheel(int wheelDelta) {
/*  924 */     this.event.mouseWheelDelta = wheelDelta;
/*  925 */     boolean handled = (sendMouseEvent(Event.Type.MOUSE_WHEEL, this.dragActive ? this.lastMouseDownWidget : null) != null);
/*      */     
/*  927 */     this.event.mouseWheelDelta = 0;
/*  928 */     return handled;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean handleKey(int keyCode, char keyChar, boolean pressed) {
/*  944 */     this.event.keyCode = keyCode;
/*  945 */     this.event.keyChar = keyChar;
/*  946 */     this.event.keyRepeated = false;
/*      */     
/*  948 */     this.keyEventTime = this.curTime;
/*  949 */     if (this.event.keyCode != 0 || this.event.keyChar != '\000') {
/*  950 */       this.event.setModifiers(pressed);
/*      */       
/*  952 */       if (pressed) {
/*  953 */         this.keyRepeatDelay = 250;
/*  954 */         return sendKeyEvent(Event.Type.KEY_PRESSED);
/*      */       } 
/*  956 */       this.keyRepeatDelay = 0;
/*  957 */       return sendKeyEvent(Event.Type.KEY_RELEASED);
/*      */     } 
/*      */     
/*  960 */     this.keyRepeatDelay = 0;
/*      */ 
/*      */     
/*  963 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void clearKeyboardState() {
/*  973 */     this.event.modifier &= 0xFFFFF9C0;
/*  974 */     this.keyRepeatDelay = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void handleKeyRepeat() {
/*  986 */     if (this.keyRepeatDelay != 0) {
/*  987 */       long keyDeltaTime = this.curTime - this.keyEventTime;
/*  988 */       if (keyDeltaTime > this.keyRepeatDelay) {
/*  989 */         this.keyEventTime = this.curTime;
/*  990 */         this.keyRepeatDelay = 33;
/*  991 */         this.event.keyRepeated = true;
/*  992 */         sendKeyEvent(Event.Type.KEY_PRESSED);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void handleTooltips() {
/* 1007 */     Widget widgetUnderMouse = getWidgetUnderMouse();
/* 1008 */     if (widgetUnderMouse != this.tooltipOwner) {
/* 1009 */       if (widgetUnderMouse != null && (this.curTime - this.tooltipEventTime > this.tooltipDelay || (this.hadOpenTooltip && this.curTime - this.tooltipClosedTime < this.tooltipReappearDelay))) {
/*      */ 
/*      */         
/* 1012 */         setTooltip(this.event.mouseX + this.tooltipOffsetX, this.event.mouseY + this.tooltipOffsetY, widgetUnderMouse, widgetUnderMouse.getTooltipContentAt(this.event.mouseX, this.event.mouseY), Alignment.BOTTOMLEFT);
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1019 */         hideTooltip();
/*      */       } 
/*      */     }
/*      */     
/* 1023 */     boolean mouseIdle = (this.curTime - this.mouseEventTime > this.mouseIdleTime);
/* 1024 */     if (this.mouseIdleState != mouseIdle) {
/* 1025 */       this.mouseIdleState = mouseIdle;
/* 1026 */       callMouseIdleListener();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private Widget getTopPane() {
/* 1032 */     return getChild(getNumChildren() - 3);
/*      */   }
/*      */ 
/*      */   
/*      */   Widget getWidgetUnderMouse() {
/* 1037 */     return getTopPane().getWidgetUnderMouse();
/*      */   }
/*      */   
/*      */   private Widget sendMouseEvent(Event.Type type, Widget target) {
/* 1041 */     assert type.isMouseEvent;
/* 1042 */     this.popupEventOccured = false;
/* 1043 */     this.event.type = type;
/* 1044 */     this.event.dragEvent = (this.dragActive && this.boundDragPopup == null);
/*      */     
/* 1046 */     this.renderer.setMousePosition(this.event.mouseX, this.event.mouseY);
/*      */     
/* 1048 */     if (target != null) {
/* 1049 */       if (target.isEnabled() || !isMouseAction(this.event)) {
/* 1050 */         target.handleEvent(target.translateMouseEvent(this.event));
/*      */       }
/* 1052 */       return target;
/*      */     } 
/* 1054 */     assert !this.dragActive || this.boundDragPopup != null;
/* 1055 */     Widget widget = null;
/* 1056 */     if (this.activeInfoWindow != null && 
/* 1057 */       this.activeInfoWindow.isMouseInside(this.event) && setMouseOverChild(this.activeInfoWindow, this.event)) {
/* 1058 */       widget = this.activeInfoWindow;
/*      */     }
/*      */     
/* 1061 */     if (widget == null) {
/* 1062 */       widget = getTopPane();
/* 1063 */       setMouseOverChild(widget, this.event);
/*      */     } 
/* 1065 */     return widget.routeMouseEvent(this.event);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isFocusKey() {
/* 1072 */     return (this.event.keyCode == 15 && (this.event.modifier & 0x636) == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   void setFocusKeyWidget(Widget widget) {
/* 1077 */     if (this.focusKeyWidget == null && isFocusKey()) {
/* 1078 */       this.focusKeyWidget = widget;
/*      */     }
/*      */   }
/*      */   
/*      */   private boolean sendKeyEvent(Event.Type type) {
/* 1083 */     assert type.isKeyEvent;
/* 1084 */     this.popupEventOccured = false;
/* 1085 */     this.focusKeyWidget = null;
/* 1086 */     this.event.type = type;
/* 1087 */     this.event.dragEvent = false;
/* 1088 */     boolean handled = getTopPane().handleEvent(this.event);
/* 1089 */     if (!handled && this.focusKeyWidget != null) {
/* 1090 */       this.focusKeyWidget.handleFocusKeyEvent(this.event);
/* 1091 */       handled = true;
/*      */     } 
/* 1093 */     this.focusKeyWidget = null;
/* 1094 */     return handled;
/*      */   }
/*      */   
/*      */   private void sendPopupEvent(Event.Type type) {
/* 1098 */     assert type == Event.Type.POPUP_OPENED || type == Event.Type.POPUP_CLOSED;
/* 1099 */     this.popupEventOccured = false;
/* 1100 */     this.event.type = type;
/* 1101 */     this.event.dragEvent = false;
/*      */     try {
/* 1103 */       getTopPane().routePopupEvent(this.event);
/* 1104 */     } catch (Exception ex) {
/* 1105 */       Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, "Exception in sendPopupEvent()", ex);
/*      */     } 
/*      */   }
/*      */   
/*      */   void resendLastMouseMove() {
/* 1110 */     if (!this.dragActive) {
/* 1111 */       sendMouseEvent(Event.Type.MOUSE_MOVED, (Widget)null);
/*      */     }
/*      */   }
/*      */   
/*      */   void openPopup(PopupWindow popup) {
/* 1116 */     if (popup.getParent() == this) {
/* 1117 */       closePopup(popup);
/* 1118 */     } else if (popup.getParent() != null) {
/* 1119 */       throw new IllegalArgumentException("popup must not be added anywhere");
/*      */     } 
/* 1121 */     hideTooltip();
/* 1122 */     this.hadOpenTooltip = false;
/* 1123 */     sendPopupEvent(Event.Type.POPUP_OPENED);
/* 1124 */     super.insertChild(popup, getNumChildren() - 2);
/* 1125 */     popup.getOwner().setOpenPopup(this, true);
/* 1126 */     this.popupEventOccured = true;
/* 1127 */     if (this.activeInfoWindow != null) {
/* 1128 */       closeInfo(this.activeInfoWindow);
/*      */     }
/*      */   }
/*      */   
/*      */   void closePopup(PopupWindow popup) {
/* 1133 */     if (this.boundDragPopup == popup) {
/* 1134 */       this.boundDragPopup = null;
/*      */     }
/* 1136 */     int idx = getChildIndex(popup);
/* 1137 */     if (idx > 0) {
/* 1138 */       super.removeChild(idx);
/*      */     }
/* 1140 */     popup.getOwner().recalcOpenPopups(this);
/* 1141 */     sendPopupEvent(Event.Type.POPUP_CLOSED);
/* 1142 */     this.popupEventOccured = true;
/* 1143 */     closeInfoFromWidget(popup);
/* 1144 */     requestKeyboardFocus(getTopPane());
/* 1145 */     resendLastMouseMove();
/*      */   }
/*      */   
/*      */   boolean hasOpenPopups(Widget owner) {
/* 1149 */     for (int i = getNumChildren() - 2; i-- > 1; ) {
/* 1150 */       PopupWindow popup = (PopupWindow)getChild(i);
/* 1151 */       if (popup.getOwner() == owner) {
/* 1152 */         return true;
/*      */       }
/*      */     } 
/* 1155 */     return false;
/*      */   }
/*      */   
/*      */   private boolean isOwner(Widget owner, Widget widget) {
/* 1159 */     while (owner != null && owner != widget) {
/* 1160 */       owner = owner.getParent();
/*      */     }
/* 1162 */     return (owner == widget);
/*      */   }
/*      */   
/*      */   void closePopupFromWidgets(Widget widget) {
/* 1166 */     for (int i = getNumChildren() - 2; i-- > 1; ) {
/* 1167 */       PopupWindow popup = (PopupWindow)getChild(i);
/* 1168 */       if (isOwner(popup.getOwner(), widget)) {
/* 1169 */         closePopup(popup);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   void closeIfPopup(Widget widget) {
/* 1175 */     if (widget instanceof PopupWindow) {
/* 1176 */       closePopup((PopupWindow)widget);
/*      */     }
/*      */   }
/*      */   
/*      */   boolean bindDragEvent(PopupWindow popup, Runnable cb) {
/* 1181 */     if (this.boundDragPopup == null && getTopPane() == popup && this.dragButton >= 0 && !isOwner(this.lastMouseDownWidget, popup)) {
/* 1182 */       this.dragActive = true;
/* 1183 */       this.boundDragPopup = popup;
/* 1184 */       this.boundDragCallback = cb;
/* 1185 */       sendMouseEvent(Event.Type.MOUSE_MOVED, (Widget)null);
/* 1186 */       return true;
/*      */     } 
/* 1188 */     return false;
/*      */   }
/*      */   
/*      */   void widgetHidden(Widget widget) {
/* 1192 */     closeIfPopup(widget);
/* 1193 */     closePopupFromWidgets(widget);
/* 1194 */     if (isOwner(this.tooltipOwner, widget)) {
/* 1195 */       hideTooltip();
/* 1196 */       this.hadOpenTooltip = false;
/*      */     } 
/* 1198 */     closeInfoFromWidget(widget);
/*      */   }
/*      */   
/*      */   void widgetDisabled(Widget widget) {
/* 1202 */     closeIfPopup(widget);
/* 1203 */     closeInfoFromWidget(widget);
/*      */   }
/*      */   
/*      */   void closeInfoFromWidget(Widget widget) {
/* 1207 */     if (this.activeInfoWindow != null && (
/* 1208 */       this.activeInfoWindow == widget || isOwner(this.activeInfoWindow.getOwner(), widget)))
/*      */     {
/* 1210 */       closeInfo(this.activeInfoWindow);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void openInfo(InfoWindow info) {
/* 1216 */     int idx = getNumChildren() - 2;
/* 1217 */     super.removeChild(idx);
/* 1218 */     super.insertChild(info, idx);
/* 1219 */     this.activeInfoWindow = info;
/*      */   }
/*      */   
/*      */   void closeInfo(InfoWindow info) {
/* 1223 */     if (info == this.activeInfoWindow) {
/* 1224 */       int idx = getNumChildren() - 2;
/* 1225 */       super.removeChild(idx);
/* 1226 */       super.insertChild(this.infoWindowPlaceholder, idx);
/* 1227 */       this.activeInfoWindow = null;
/*      */       try {
/* 1229 */         info.infoWindowClosed();
/* 1230 */       } catch (Exception ex) {
/* 1231 */         Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, "Exception in infoWindowClosed()", ex);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean requestKeyboardFocus() {
/* 1239 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean requestKeyboardFocus(Widget child) {
/* 1244 */     if (child != null && 
/* 1245 */       child != getTopPane()) {
/* 1246 */       return false;
/*      */     }
/*      */     
/* 1249 */     return super.requestKeyboardFocus(child);
/*      */   }
/*      */   
/*      */   void requestTooltipUpdate(Widget widget, boolean resetToolTipTimer) {
/* 1253 */     if (this.tooltipOwner == widget) {
/* 1254 */       this.tooltipOwner = null;
/* 1255 */       if (resetToolTipTimer) {
/* 1256 */         hideTooltip();
/* 1257 */         this.hadOpenTooltip = false;
/* 1258 */         this.tooltipEventTime = this.curTime;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void hideTooltip() {
/* 1264 */     if (this.tooltipWindow.isVisible()) {
/* 1265 */       this.tooltipClosedTime = this.curTime;
/* 1266 */       this.hadOpenTooltip = true;
/*      */     } 
/* 1268 */     this.tooltipWindow.setVisible(false);
/* 1269 */     this.tooltipOwner = null;
/*      */ 
/*      */     
/* 1272 */     if (this.tooltipLabel.getParent() != this.tooltipWindow) {
/* 1273 */       this.tooltipWindow.removeAllChildren();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void setTooltip(int x, int y, Widget widget, Object content, Alignment alignment) throws IllegalArgumentException {
/* 1279 */     if (content == null) {
/* 1280 */       hideTooltip();
/*      */       
/*      */       return;
/*      */     } 
/* 1284 */     if (content instanceof String) {
/* 1285 */       String text = (String)content;
/* 1286 */       if (text.length() == 0) {
/* 1287 */         hideTooltip();
/*      */         return;
/*      */       } 
/* 1290 */       if (this.tooltipLabel.getParent() != this.tooltipWindow) {
/* 1291 */         this.tooltipWindow.removeAllChildren();
/* 1292 */         this.tooltipWindow.add(this.tooltipLabel);
/*      */       } 
/* 1294 */       this.tooltipLabel.setBackground(null);
/* 1295 */       this.tooltipLabel.setText(text);
/* 1296 */     } else if (content instanceof Widget) {
/* 1297 */       Widget tooltipWidget = (Widget)content;
/* 1298 */       if (tooltipWidget.getParent() != null && tooltipWidget.getParent() != this.tooltipWindow) {
/* 1299 */         throw new IllegalArgumentException("Content widget must not be added to another widget");
/*      */       }
/* 1301 */       this.tooltipWindow.removeAllChildren();
/* 1302 */       this.tooltipWindow.add(tooltipWidget);
/*      */     } else {
/* 1304 */       throw new IllegalArgumentException("Unsupported data type");
/*      */     } 
/*      */     
/* 1307 */     this.tooltipWindow.adjustSize();
/*      */ 
/*      */ 
/*      */     
/* 1311 */     if (this.tooltipWindow.isLayoutInvalid()) {
/* 1312 */       this.tooltipWindow.adjustSize();
/*      */     }
/*      */     
/* 1315 */     int ttWidth = this.tooltipWindow.getWidth();
/* 1316 */     int ttHeight = this.tooltipWindow.getHeight();
/*      */     
/* 1318 */     switch (alignment) {
/*      */       case TOP:
/*      */       case CENTER:
/*      */       case BOTTOM:
/* 1322 */         x -= ttWidth / 2;
/*      */         break;
/*      */       case TOPRIGHT:
/*      */       case RIGHT:
/*      */       case BOTTOMRIGHT:
/* 1327 */         x -= ttWidth;
/*      */         break;
/*      */     } 
/*      */     
/* 1331 */     switch (alignment) {
/*      */       case CENTER:
/*      */       case RIGHT:
/*      */       case LEFT:
/* 1335 */         y -= ttHeight / 2;
/*      */         break;
/*      */       case BOTTOM:
/*      */       case BOTTOMRIGHT:
/*      */       case BOTTOMLEFT:
/* 1340 */         y -= ttHeight;
/*      */         break;
/*      */     } 
/*      */     
/* 1344 */     if (x + ttWidth > getWidth()) {
/* 1345 */       x = getWidth() - ttWidth;
/*      */     }
/* 1347 */     if (y + ttHeight > getHeight()) {
/* 1348 */       y = getHeight() - ttHeight;
/*      */     }
/* 1350 */     if (x < 0) {
/* 1351 */       x = 0;
/*      */     }
/* 1353 */     if (y < 0) {
/* 1354 */       y = 0;
/*      */     }
/*      */     
/* 1357 */     this.tooltipOwner = widget;
/* 1358 */     this.tooltipWindow.setPosition(x, y);
/* 1359 */     this.tooltipWindow.setVisible(true);
/*      */   }
/*      */   
/*      */   private void callMouseIdleListener() {
/* 1363 */     if (this.mouseIdleListener != null) {
/* 1364 */       if (this.mouseIdleState) {
/* 1365 */         this.mouseIdleListener.mouseEnterIdle();
/*      */       } else {
/* 1367 */         this.mouseIdleListener.mouseExitIdle();
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private void growInvokeLaterQueue() {
/* 1373 */     Runnable[] tmp = new Runnable[this.invokeLaterQueueSize * 2];
/* 1374 */     System.arraycopy(this.invokeLaterQueue, 0, tmp, 0, this.invokeLaterQueueSize);
/* 1375 */     this.invokeLaterQueue = tmp;
/*      */   }
/*      */   
/*      */   static class TooltipWindow extends Container {
/* 1379 */     public static final AnimationState.StateKey STATE_FADE = AnimationState.StateKey.get("fade");
/*      */     
/*      */     private int fadeInTime;
/*      */     
/*      */     protected void applyTheme(ThemeInfo themeInfo) {
/* 1384 */       super.applyTheme(themeInfo);
/* 1385 */       this.fadeInTime = themeInfo.getParameter("fadeInTime", 0);
/*      */     }
/*      */ 
/*      */     
/*      */     public void setVisible(boolean visible) {
/* 1390 */       super.setVisible(visible);
/* 1391 */       getAnimationState().resetAnimationTime(STATE_FADE);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void paint(GUI gui) {
/* 1396 */       int time = getAnimationState().getAnimationTime(STATE_FADE);
/* 1397 */       if (time < this.fadeInTime) {
/* 1398 */         float alpha = time / this.fadeInTime;
/* 1399 */         gui.getRenderer().pushGlobalTintColor(1.0F, 1.0F, 1.0F, alpha);
/*      */         try {
/* 1401 */           super.paint(gui);
/*      */         } finally {
/* 1403 */           gui.getRenderer().popGlobalTintColor();
/*      */         } 
/*      */       } else {
/* 1406 */         super.paint(gui);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   class AC<V> implements Callable<V>, Runnable {
/*      */     private final Callable<V> jobC;
/*      */     private final Runnable jobR;
/*      */     private final GUI.AsyncCompletionListener<V> listener;
/*      */     private V result;
/*      */     private Exception exception;
/*      */     
/*      */     AC(Callable<V> jobC, Runnable jobR, GUI.AsyncCompletionListener<V> listener) {
/* 1419 */       this.jobC = jobC;
/* 1420 */       this.jobR = jobR;
/* 1421 */       this.listener = listener;
/*      */     }
/*      */     
/*      */     public V call() throws Exception {
/*      */       try {
/* 1426 */         if (this.jobC != null) {
/* 1427 */           this.result = this.jobC.call();
/*      */         } else {
/* 1429 */           this.jobR.run();
/*      */         } 
/* 1431 */         GUI.this.invokeLater(this);
/* 1432 */         return this.result;
/* 1433 */       } catch (Exception ex) {
/* 1434 */         this.exception = ex;
/* 1435 */         GUI.this.invokeLater(this);
/* 1436 */         throw ex;
/*      */       } 
/*      */     }
/*      */     
/*      */     public void run() {
/* 1441 */       if (this.exception != null) {
/* 1442 */         this.listener.failed(this.exception);
/*      */       } else {
/* 1444 */         this.listener.completed(this.result);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   static class TF implements ThreadFactory {
/* 1450 */     static final AtomicInteger poolNumber = new AtomicInteger(1);
/* 1451 */     final AtomicInteger threadNumber = new AtomicInteger(1);
/*      */     final String prefix;
/*      */     
/*      */     TF() {
/* 1455 */       this.prefix = "GUI-" + poolNumber.getAndIncrement() + "-invokeAsync-";
/*      */     }
/*      */     
/*      */     public Thread newThread(Runnable r) {
/* 1459 */       Thread t = new Thread(r, this.prefix + this.threadNumber.getAndIncrement());
/* 1460 */       t.setDaemon(true);
/* 1461 */       t.setPriority(5);
/* 1462 */       return t;
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface AsyncCompletionListener<V> {
/*      */     void completed(V param1V);
/*      */     
/*      */     void failed(Exception param1Exception);
/*      */   }
/*      */   
/*      */   public static interface MouseIdleListener {
/*      */     void mouseEnterIdle();
/*      */     
/*      */     void mouseExitIdle();
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\GUI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */