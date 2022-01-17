/*      */ package de.matthiasmann.twl;
/*      */ 
/*      */ import de.matthiasmann.twl.renderer.AnimationState;
/*      */ import de.matthiasmann.twl.renderer.Font;
/*      */ import de.matthiasmann.twl.renderer.FontCache;
/*      */ import de.matthiasmann.twl.renderer.FontMapper;
/*      */ import de.matthiasmann.twl.renderer.FontParameter;
/*      */ import de.matthiasmann.twl.renderer.Image;
/*      */ import de.matthiasmann.twl.renderer.MouseCursor;
/*      */ import de.matthiasmann.twl.renderer.Renderer;
/*      */ import de.matthiasmann.twl.textarea.OrderedListType;
/*      */ import de.matthiasmann.twl.textarea.Style;
/*      */ import de.matthiasmann.twl.textarea.StyleAttribute;
/*      */ import de.matthiasmann.twl.textarea.StyleSheet;
/*      */ import de.matthiasmann.twl.textarea.StyleSheetResolver;
/*      */ import de.matthiasmann.twl.textarea.TextAreaModel;
/*      */ import de.matthiasmann.twl.textarea.TextDecoration;
/*      */ import de.matthiasmann.twl.textarea.Value;
/*      */ import de.matthiasmann.twl.theme.StateSelectImage;
/*      */ import de.matthiasmann.twl.utils.CallbackSupport;
/*      */ import de.matthiasmann.twl.utils.StateExpression;
/*      */ import de.matthiasmann.twl.utils.StateSelect;
/*      */ import de.matthiasmann.twl.utils.StringList;
/*      */ import de.matthiasmann.twl.utils.TextUtil;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
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
/*      */ public class TextArea
/*      */   extends Widget
/*      */ {
/*  108 */   public static final AnimationState.StateKey STATE_HOVER = AnimationState.StateKey.get("hover");
/*      */   
/*  110 */   static final char[] EMPTY_CHAR_ARRAY = new char[0];
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
/*  146 */   private final HashMap<String, Widget> widgets = new HashMap<String, Widget>();
/*  147 */   private final HashMap<String, WidgetResolver> widgetResolvers = new HashMap<String, WidgetResolver>();
/*  148 */   private final HashMap<String, Image> userImages = new HashMap<String, Image>();
/*  149 */   private final ArrayList<ImageResolver> imageResolvers = new ArrayList<ImageResolver>();
/*  150 */   private final LClip layoutRoot = new LClip(null);
/*  151 */   private final ArrayList<LImage> allBGImages = new ArrayList<LImage>();
/*  152 */   private final RenderInfo renderInfo = new RenderInfo(getAnimationState());
/*      */   
/*  154 */   private final Runnable modelCB = new Runnable() {
/*      */       public void run() {
/*  156 */         TextArea.this.forceRelayout();
/*      */       }
/*      */     };
/*      */   
/*      */   public TextArea() {}
/*      */   
/*      */   public TextArea(TextAreaModel model) {
/*  163 */     this();
/*  164 */     setModel(model);
/*      */   }
/*      */   
/*      */   public TextAreaModel getModel() {
/*  168 */     return this.model;
/*      */   }
/*      */   
/*      */   public void setModel(TextAreaModel model) {
/*  172 */     if (this.model != null) {
/*  173 */       this.model.removeCallback(this.modelCB);
/*      */     }
/*  175 */     this.model = model;
/*  176 */     if (model != null) {
/*  177 */       model.addCallback(this.modelCB);
/*      */     }
/*  179 */     forceRelayout();
/*      */   }
/*      */   
/*      */   public void registerWidget(String name, Widget widget) {
/*  183 */     if (name == null) {
/*  184 */       throw new NullPointerException("name");
/*      */     }
/*  186 */     if (widget.getParent() != null) {
/*  187 */       throw new IllegalArgumentException("Widget must not have a parent");
/*      */     }
/*  189 */     if (this.widgets.containsKey(name) || this.widgetResolvers.containsKey(name)) {
/*  190 */       throw new IllegalArgumentException("widget name already in registered");
/*      */     }
/*  192 */     if (this.widgets.containsValue(widget)) {
/*  193 */       throw new IllegalArgumentException("widget already registered");
/*      */     }
/*  195 */     this.widgets.put(name, widget);
/*      */   }
/*      */   
/*      */   public void registerWidgetResolver(String name, WidgetResolver resolver) {
/*  199 */     if (name == null) {
/*  200 */       throw new NullPointerException("name");
/*      */     }
/*  202 */     if (resolver == null) {
/*  203 */       throw new NullPointerException("resolver");
/*      */     }
/*  205 */     if (this.widgets.containsKey(name) || this.widgetResolvers.containsKey(name)) {
/*  206 */       throw new IllegalArgumentException("widget name already in registered");
/*      */     }
/*  208 */     this.widgetResolvers.put(name, resolver);
/*      */   }
/*      */   
/*      */   public void unregisterWidgetResolver(String name) {
/*  212 */     if (name == null) {
/*  213 */       throw new NullPointerException("name");
/*      */     }
/*  215 */     this.widgetResolvers.remove(name);
/*      */   }
/*      */   
/*      */   public void unregisterWidget(String name) {
/*  219 */     if (name == null) {
/*  220 */       throw new NullPointerException("name");
/*      */     }
/*  222 */     Widget w = this.widgets.get(name);
/*  223 */     if (w != null) {
/*  224 */       int idx = getChildIndex(w);
/*  225 */       if (idx >= 0) {
/*  226 */         super.removeChild(idx);
/*  227 */         forceRelayout();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void unregisterAllWidgets() {
/*  233 */     this.widgets.clear();
/*  234 */     super.removeAllChildren();
/*  235 */     forceRelayout();
/*      */   }
/*      */   
/*      */   public void registerImage(String name, Image image) {
/*  239 */     if (name == null) {
/*  240 */       throw new NullPointerException("name");
/*      */     }
/*  242 */     this.userImages.put(name, image);
/*      */   }
/*      */   
/*      */   public void registerImageResolver(ImageResolver resolver) {
/*  246 */     if (resolver == null) {
/*  247 */       throw new NullPointerException("resolver");
/*      */     }
/*  249 */     if (!this.imageResolvers.contains(resolver)) {
/*  250 */       this.imageResolvers.add(resolver);
/*      */     }
/*      */   }
/*      */   
/*      */   public void unregisterImage(String name) {
/*  255 */     this.userImages.remove(name);
/*      */   }
/*      */   
/*      */   public void unregisterImageResolver(ImageResolver imageResolver) {
/*  259 */     this.imageResolvers.remove(imageResolver);
/*      */   }
/*      */   
/*      */   public void addCallback(Callback cb) {
/*  263 */     this.callbacks = (Callback[])CallbackSupport.addCallbackToList((Object[])this.callbacks, cb, Callback.class);
/*      */   }
/*      */   
/*      */   public void removeCallback(Callback cb) {
/*  267 */     this.callbacks = (Callback[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, cb);
/*      */   }
/*      */   
/*      */   public DraggableButton.DragListener getDragListener() {
/*  271 */     return this.dragListener;
/*      */   }
/*      */   
/*      */   public void setDragListener(DraggableButton.DragListener dragListener) {
/*  275 */     this.dragListener = dragListener;
/*      */   }
/*      */   
/*      */   public StyleSheetResolver getStyleClassResolver() {
/*  279 */     return this.styleClassResolver;
/*      */   }
/*      */   
/*      */   public void setStyleClassResolver(StyleSheetResolver styleClassResolver) {
/*  283 */     this.styleClassResolver = styleClassResolver;
/*  284 */     forceRelayout();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDefaultStyleSheet() {
/*      */     try {
/*  295 */       StyleSheet styleSheet = new StyleSheet();
/*  296 */       styleSheet.parse("p,ul{margin-bottom:1em}");
/*  297 */       setStyleClassResolver((StyleSheetResolver)styleSheet);
/*  298 */     } catch (IOException ex) {
/*  299 */       Logger.getLogger(TextArea.class.getName()).log(Level.SEVERE, "Can't create default style sheet", ex);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Rect getElementRect(TextAreaModel.Element element) {
/*  305 */     int[] offset = new int[2];
/*  306 */     LElement le = this.layoutRoot.find(element, offset);
/*  307 */     if (le != null) {
/*  308 */       return new Rect(le.x + offset[0], le.y + offset[1], le.width, le.height);
/*      */     }
/*  310 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void applyTheme(ThemeInfo themeInfo) {
/*  316 */     super.applyTheme(themeInfo);
/*  317 */     applyThemeTextArea(themeInfo);
/*      */   }
/*      */   
/*      */   protected void applyThemeTextArea(ThemeInfo themeInfo) {
/*  321 */     this.fonts = themeInfo.getParameterMap("fonts");
/*  322 */     this.images = themeInfo.getParameterMap("images");
/*  323 */     this.defaultFont = themeInfo.getFont("font");
/*  324 */     this.mouseCursorNormal = themeInfo.getMouseCursor("mouseCursor");
/*  325 */     this.mouseCursorLink = themeInfo.getMouseCursor("mouseCursor.link");
/*  326 */     forceRelayout();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void afterAddToGUI(GUI gui) {
/*  331 */     super.afterAddToGUI(gui);
/*  332 */     this.renderInfo.asNormal.setGUI(gui);
/*  333 */     this.renderInfo.asHover.setGUI(gui);
/*      */   }
/*      */ 
/*      */   
/*      */   public void insertChild(Widget child, int index) {
/*  338 */     throw new UnsupportedOperationException("use registerWidget");
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeAllChildren() {
/*  343 */     throw new UnsupportedOperationException("use registerWidget");
/*      */   }
/*      */ 
/*      */   
/*      */   public Widget removeChild(int index) {
/*  348 */     throw new UnsupportedOperationException("use registerWidget");
/*      */   }
/*      */   
/*      */   private void computePreferredInnerSize() {
/*  352 */     int prefWidth = -1;
/*  353 */     int prefHeight = -1;
/*      */     
/*  355 */     if (this.model == null) {
/*  356 */       prefWidth = 0;
/*  357 */       prefHeight = 0;
/*      */     }
/*  359 */     else if (getMaxWidth() > 0) {
/*  360 */       int borderHorizontal = getBorderHorizontal();
/*  361 */       int maxWidth = Math.max(0, getMaxWidth() - borderHorizontal);
/*  362 */       int minWidth = Math.max(0, getMinWidth() - borderHorizontal);
/*      */       
/*  364 */       if (minWidth < maxWidth) {
/*      */ 
/*      */         
/*  367 */         LClip tmpRoot = new LClip(null);
/*  368 */         startLayout();
/*      */         try {
/*  370 */           tmpRoot.width = maxWidth;
/*  371 */           Box box = new Box(tmpRoot, 0, 0, 0, false);
/*  372 */           layoutElements(box, (Iterable<TextAreaModel.Element>)this.model);
/*  373 */           box.finish();
/*      */           
/*  375 */           prefWidth = Math.max(0, maxWidth - box.minRemainingWidth);
/*  376 */           prefHeight = box.curY;
/*      */         } finally {
/*  378 */           endLayout();
/*      */         } 
/*      */       } 
/*      */     } 
/*  382 */     this.preferredInnerSize = new Dimension(prefWidth, prefHeight);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPreferredInnerWidth() {
/*  387 */     if (this.preferredInnerSize == null) {
/*  388 */       computePreferredInnerSize();
/*      */     }
/*  390 */     if (this.preferredInnerSize.getX() >= 0) {
/*  391 */       return this.preferredInnerSize.getX();
/*      */     }
/*  393 */     return getInnerWidth();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPreferredInnerHeight() {
/*  398 */     if (getInnerWidth() == 0) {
/*  399 */       if (this.preferredInnerSize == null) {
/*  400 */         computePreferredInnerSize();
/*      */       }
/*  402 */       if (this.preferredInnerSize.getY() >= 0) {
/*  403 */         return this.preferredInnerSize.getY();
/*      */       }
/*      */     } 
/*  406 */     validateLayout();
/*  407 */     return this.layoutRoot.height;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPreferredWidth() {
/*  412 */     int maxWidth = getMaxWidth();
/*  413 */     return computeSize(getMinWidth(), super.getPreferredWidth(), maxWidth);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMaxSize(int width, int height) {
/*  418 */     if (width != getMaxWidth()) {
/*  419 */       this.preferredInnerSize = null;
/*  420 */       invalidateLayout();
/*      */     } 
/*  422 */     super.setMaxSize(width, height);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMinSize(int width, int height) {
/*  427 */     if (width != getMinWidth()) {
/*  428 */       this.preferredInnerSize = null;
/*  429 */       invalidateLayout();
/*      */     } 
/*  431 */     super.setMinSize(width, height);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void layout() {
/*  436 */     int targetWidth = getInnerWidth();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  441 */     if (this.layoutRoot.width != targetWidth || this.forceRelayout) {
/*  442 */       int requiredHeight; this.layoutRoot.width = targetWidth;
/*  443 */       this.inLayoutCode = true;
/*  444 */       this.forceRelayout = false;
/*      */ 
/*      */       
/*  447 */       startLayout();
/*      */       try {
/*  449 */         clearLayout();
/*  450 */         Box box = new Box(this.layoutRoot, 0, 0, 0, true);
/*  451 */         if (this.model != null) {
/*  452 */           layoutElements(box, (Iterable<TextAreaModel.Element>)this.model);
/*      */           
/*  454 */           box.finish();
/*      */ 
/*      */           
/*  457 */           this.layoutRoot.adjustWidget(getInnerX(), getInnerY());
/*  458 */           this.layoutRoot.collectBGImages(0, 0, this.allBGImages);
/*      */         } 
/*  460 */         updateMouseHover();
/*  461 */         requiredHeight = box.curY;
/*      */       } finally {
/*  463 */         this.inLayoutCode = false;
/*  464 */         endLayout();
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  469 */       if (this.layoutRoot.height != requiredHeight) {
/*  470 */         this.layoutRoot.height = requiredHeight;
/*  471 */         if (getInnerHeight() != requiredHeight)
/*      */         {
/*  473 */           invalidateLayout();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void paintWidget(GUI gui) {
/*  481 */     ArrayList<LImage> bi = this.allBGImages;
/*  482 */     RenderInfo ri = this.renderInfo;
/*  483 */     ri.offsetX = getInnerX();
/*  484 */     ri.offsetY = getInnerY();
/*  485 */     ri.renderer = gui.getRenderer();
/*      */     
/*  487 */     for (int i = 0, n = bi.size(); i < n; i++) {
/*  488 */       ((LImage)bi.get(i)).draw(ri);
/*      */     }
/*      */     
/*  491 */     this.layoutRoot.draw(ri);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void sizeChanged() {
/*  496 */     if (!this.inLayoutCode) {
/*  497 */       invalidateLayout();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void childAdded(Widget child) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void childRemoved(Widget exChild) {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected void allChildrenRemoved() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void destroy() {
/*  518 */     super.destroy();
/*  519 */     clearLayout();
/*  520 */     forceRelayout();
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean handleEvent(Event evt) {
/*  525 */     if (super.handleEvent(evt)) {
/*  526 */       return true;
/*      */     }
/*      */     
/*  529 */     if (evt.isMouseEvent()) {
/*  530 */       Event.Type eventType = evt.getType();
/*      */       
/*  532 */       if (this.dragging) {
/*  533 */         if (eventType == Event.Type.MOUSE_DRAGGED && 
/*  534 */           this.dragListener != null) {
/*  535 */           this.dragListener.dragged(evt.getMouseX() - this.dragStartX, evt.getMouseY() - this.dragStartY);
/*      */         }
/*      */         
/*  538 */         if (evt.isMouseDragEnd()) {
/*  539 */           if (this.dragListener != null) {
/*  540 */             this.dragListener.dragStopped();
/*      */           }
/*  542 */           this.dragging = false;
/*  543 */           updateMouseHover(evt);
/*      */         } 
/*  545 */         return true;
/*      */       } 
/*      */       
/*  548 */       updateMouseHover(evt);
/*      */       
/*  550 */       if (eventType == Event.Type.MOUSE_WHEEL) {
/*  551 */         return false;
/*      */       }
/*      */       
/*  554 */       if (eventType == Event.Type.MOUSE_BTNDOWN) {
/*  555 */         this.dragStartX = evt.getMouseX();
/*  556 */         this.dragStartY = evt.getMouseY();
/*      */       } 
/*      */       
/*  559 */       if (eventType == Event.Type.MOUSE_DRAGGED) {
/*  560 */         assert !this.dragging;
/*  561 */         this.dragging = true;
/*  562 */         if (this.dragListener != null) {
/*  563 */           this.dragListener.dragStarted();
/*      */         }
/*  565 */         return true;
/*      */       } 
/*      */       
/*  568 */       if (this.curLElementUnderMouse != null && (eventType == Event.Type.MOUSE_CLICKED || eventType == Event.Type.MOUSE_BTNDOWN || eventType == Event.Type.MOUSE_BTNUP)) {
/*      */ 
/*      */ 
/*      */         
/*  572 */         TextAreaModel.Element e = this.curLElementUnderMouse.element;
/*  573 */         if (this.callbacks != null) {
/*  574 */           for (Callback l : this.callbacks) {
/*  575 */             if (l instanceof Callback2) {
/*  576 */               ((Callback2)l).handleMouseButton(evt, e);
/*      */             }
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/*  582 */       if (eventType == Event.Type.MOUSE_CLICKED && 
/*  583 */         this.curLElementUnderMouse != null && this.curLElementUnderMouse.href != null) {
/*  584 */         String href = this.curLElementUnderMouse.href;
/*  585 */         if (this.callbacks != null) {
/*  586 */           for (Callback l : this.callbacks) {
/*  587 */             l.handleLinkClicked(href);
/*      */           }
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  593 */       return true;
/*      */     } 
/*      */     
/*  596 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Object getTooltipContentAt(int mouseX, int mouseY) {
/*  601 */     if (this.curLElementUnderMouse != null && 
/*  602 */       this.curLElementUnderMouse.element instanceof TextAreaModel.ImageElement) {
/*  603 */       return ((TextAreaModel.ImageElement)this.curLElementUnderMouse.element).getToolTip();
/*      */     }
/*      */     
/*  606 */     return super.getTooltipContentAt(mouseX, mouseY);
/*      */   }
/*      */   
/*      */   private void updateMouseHover(Event evt) {
/*  610 */     this.lastMouseInside = isMouseInside(evt);
/*  611 */     this.lastMouseX = evt.getMouseX();
/*  612 */     this.lastMouseY = evt.getMouseY();
/*  613 */     updateMouseHover();
/*      */   }
/*      */   
/*      */   private void updateMouseHover() {
/*  617 */     LElement le = null;
/*  618 */     if (this.lastMouseInside) {
/*  619 */       le = this.layoutRoot.find(this.lastMouseX - getInnerX(), this.lastMouseY - getInnerY());
/*      */     }
/*  621 */     if (this.curLElementUnderMouse != le) {
/*  622 */       this.curLElementUnderMouse = le;
/*  623 */       this.layoutRoot.setHover(le);
/*  624 */       this.renderInfo.asNormal.resetAnimationTime(STATE_HOVER);
/*  625 */       this.renderInfo.asHover.resetAnimationTime(STATE_HOVER);
/*  626 */       updateTooltip();
/*      */     } 
/*      */     
/*  629 */     if (le != null && le.href != null) {
/*  630 */       setMouseCursor(this.mouseCursorLink);
/*      */     } else {
/*  632 */       setMouseCursor(this.mouseCursorNormal);
/*      */     } 
/*      */     
/*  635 */     getAnimationState().setAnimationState(STATE_HOVER, this.lastMouseInside);
/*      */   }
/*      */   
/*      */   void forceRelayout() {
/*  639 */     this.forceRelayout = true;
/*  640 */     this.preferredInnerSize = null;
/*  641 */     invalidateLayout();
/*      */   }
/*      */   
/*      */   private void clearLayout() {
/*  645 */     this.layoutRoot.destroy();
/*  646 */     this.allBGImages.clear();
/*  647 */     super.removeAllChildren();
/*      */   }
/*      */   
/*      */   private void startLayout() {
/*  651 */     if (this.styleClassResolver != null) {
/*  652 */       this.styleClassResolver.startLayout();
/*      */     }
/*      */     
/*  655 */     GUI gui = getGUI();
/*  656 */     this.fontMapper = (gui != null) ? gui.getRenderer().getFontMapper() : null;
/*  657 */     this.fontMapperCache = null;
/*      */   }
/*      */   
/*      */   private void endLayout() {
/*  661 */     if (this.styleClassResolver != null) {
/*  662 */       this.styleClassResolver.layoutFinished();
/*      */     }
/*  664 */     this.fontMapper = null;
/*  665 */     this.fontMapperCache = null;
/*      */   }
/*      */   
/*      */   private void layoutElements(Box box, Iterable<TextAreaModel.Element> elements) {
/*  669 */     for (TextAreaModel.Element e : elements) {
/*  670 */       layoutElement(box, e);
/*      */     }
/*      */   }
/*      */   
/*      */   private void layoutElement(Box box, TextAreaModel.Element e) {
/*  675 */     box.clearFloater((TextAreaModel.Clear)e.getStyle().get(StyleAttribute.CLEAR, this.styleClassResolver));
/*      */     
/*  677 */     if (e instanceof TextAreaModel.TextElement) {
/*  678 */       layoutTextElement(box, (TextAreaModel.TextElement)e);
/*      */     } else {
/*  680 */       if (box.wasPreformatted) {
/*  681 */         box.nextLine(false);
/*  682 */         box.wasPreformatted = false;
/*      */       } 
/*  684 */       if (e instanceof TextAreaModel.ParagraphElement) {
/*  685 */         layoutParagraphElement(box, (TextAreaModel.ParagraphElement)e);
/*  686 */       } else if (e instanceof TextAreaModel.ImageElement) {
/*  687 */         layoutImageElement(box, (TextAreaModel.ImageElement)e);
/*  688 */       } else if (e instanceof TextAreaModel.WidgetElement) {
/*  689 */         layoutWidgetElement(box, (TextAreaModel.WidgetElement)e);
/*  690 */       } else if (e instanceof TextAreaModel.ListElement) {
/*  691 */         layoutListElement(box, (TextAreaModel.ListElement)e);
/*  692 */       } else if (e instanceof TextAreaModel.OrderedListElement) {
/*  693 */         layoutOrderedListElement(box, (TextAreaModel.OrderedListElement)e);
/*  694 */       } else if (e instanceof TextAreaModel.BlockElement) {
/*  695 */         layoutBlockElement(box, (TextAreaModel.ContainerElement)e);
/*  696 */       } else if (e instanceof TextAreaModel.TableElement) {
/*  697 */         layoutTableElement(box, (TextAreaModel.TableElement)e);
/*  698 */       } else if (e instanceof TextAreaModel.LinkElement) {
/*  699 */         layoutLinkElement(box, (TextAreaModel.LinkElement)e);
/*  700 */       } else if (e instanceof TextAreaModel.ContainerElement) {
/*  701 */         layoutContainerElement(box, (TextAreaModel.ContainerElement)e);
/*      */       } else {
/*  703 */         Logger.getLogger(TextArea.class.getName()).log(Level.SEVERE, "Unknown Element subclass: {0}", e.getClass());
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void layoutImageElement(Box box, TextAreaModel.ImageElement ie) {
/*  709 */     Image image = selectImage(ie.getImageName());
/*  710 */     if (image == null) {
/*      */       return;
/*      */     }
/*      */     
/*  714 */     LImage li = new LImage((TextAreaModel.Element)ie, image);
/*  715 */     li.href = box.href;
/*  716 */     layout(box, (TextAreaModel.Element)ie, li);
/*      */   }
/*      */   
/*      */   private void layoutWidgetElement(Box box, TextAreaModel.WidgetElement we) {
/*  720 */     Widget widget = this.widgets.get(we.getWidgetName());
/*  721 */     if (widget == null) {
/*  722 */       WidgetResolver resolver = this.widgetResolvers.get(we.getWidgetName());
/*  723 */       if (resolver != null) {
/*  724 */         widget = resolver.resolveWidget(we.getWidgetName(), we.getWidgetParam());
/*      */       }
/*  726 */       if (widget == null) {
/*      */         return;
/*      */       }
/*      */     } 
/*      */     
/*  731 */     if (widget.getParent() != null) {
/*  732 */       Logger.getLogger(TextArea.class.getName()).log(Level.SEVERE, "Widget already added: {0}", widget);
/*      */       
/*      */       return;
/*      */     } 
/*  736 */     super.insertChild(widget, getNumChildren());
/*  737 */     widget.adjustSize();
/*      */     
/*  739 */     LWidget lw = new LWidget((TextAreaModel.Element)we, widget);
/*  740 */     lw.width = widget.getWidth();
/*  741 */     lw.height = widget.getHeight();
/*      */     
/*  743 */     layout(box, (TextAreaModel.Element)we, lw);
/*      */   }
/*      */   
/*      */   private void layout(Box box, TextAreaModel.Element e, LElement le) {
/*  747 */     Style style = e.getStyle();
/*      */     
/*  749 */     TextAreaModel.FloatPosition floatPosition = (TextAreaModel.FloatPosition)style.get(StyleAttribute.FLOAT_POSITION, this.styleClassResolver);
/*  750 */     TextAreaModel.Display display = (TextAreaModel.Display)style.get(StyleAttribute.DISPLAY, this.styleClassResolver);
/*      */     
/*  752 */     le.marginTop = (short)convertToPX0(style, StyleAttribute.MARGIN_TOP, box.boxWidth);
/*  753 */     le.marginLeft = (short)convertToPX0(style, StyleAttribute.MARGIN_LEFT, box.boxWidth);
/*  754 */     le.marginRight = (short)convertToPX0(style, StyleAttribute.MARGIN_RIGHT, box.boxWidth);
/*  755 */     le.marginBottom = (short)convertToPX0(style, StyleAttribute.MARGIN_BOTTOM, box.boxWidth);
/*      */     
/*  757 */     int autoHeight = le.height;
/*  758 */     int width = convertToPX(style, StyleAttribute.WIDTH, box.boxWidth, le.width);
/*  759 */     if (width > 0) {
/*  760 */       if (le.width > 0) {
/*  761 */         autoHeight = width * le.height / le.width;
/*      */       }
/*  763 */       le.width = width;
/*      */     } 
/*      */     
/*  766 */     int height = convertToPX(style, StyleAttribute.HEIGHT, le.height, autoHeight);
/*  767 */     if (height > 0) {
/*  768 */       le.height = height;
/*      */     }
/*      */     
/*  771 */     layout(box, e, le, floatPosition, display);
/*      */   }
/*      */   
/*      */   private void layout(Box box, TextAreaModel.Element e, LElement le, TextAreaModel.FloatPosition floatPos, TextAreaModel.Display display) {
/*  775 */     boolean leftRight = (floatPos != TextAreaModel.FloatPosition.NONE);
/*      */     
/*  777 */     if (leftRight || display != TextAreaModel.Display.INLINE) {
/*  778 */       box.nextLine(false);
/*  779 */       if (!leftRight) {
/*  780 */         box.curY = box.computeTopPadding(le.marginTop);
/*  781 */         box.checkFloaters();
/*      */       } 
/*      */     } 
/*      */     
/*  785 */     box.advancePastFloaters(le.width, le.marginLeft, le.marginRight);
/*  786 */     if (le.width > box.lineWidth) {
/*  787 */       le.width = box.lineWidth;
/*      */     }
/*      */     
/*  790 */     if (leftRight) {
/*  791 */       if (floatPos == TextAreaModel.FloatPosition.RIGHT) {
/*  792 */         le.x = box.computeRightPadding(le.marginRight) - le.width;
/*  793 */         box.objRight.add(le);
/*      */       } else {
/*  795 */         le.x = box.computeLeftPadding(le.marginLeft);
/*  796 */         box.objLeft.add(le);
/*      */       } 
/*  798 */     } else if (display == TextAreaModel.Display.INLINE) {
/*  799 */       if (box.getRemaining() < le.width && !box.isAtStartOfLine()) {
/*  800 */         box.nextLine(false);
/*      */       }
/*  802 */       le.x = box.getXAndAdvance(le.width);
/*      */     } else {
/*  804 */       switch ((TextAreaModel.HAlignment)e.getStyle().get(StyleAttribute.HORIZONTAL_ALIGNMENT, this.styleClassResolver)) {
/*      */         case BOTTOM:
/*      */         case FILL:
/*  807 */           le.x = box.lineStartX + (box.lineWidth - le.width) / 2;
/*      */           break;
/*      */         
/*      */         case MIDDLE:
/*  811 */           le.x = box.computeRightPadding(le.marginRight) - le.width;
/*      */           break;
/*      */         
/*      */         default:
/*  815 */           le.x = box.computeLeftPadding(le.marginLeft);
/*      */           break;
/*      */       } 
/*      */     } 
/*  819 */     box.layout.add(le);
/*      */     
/*  821 */     if (leftRight) {
/*  822 */       assert box.lineStartIdx == box.layout.size() - 1;
/*  823 */       box.lineStartIdx++;
/*  824 */       le.y = box.computeTopPadding(le.marginTop);
/*  825 */       box.computePadding();
/*  826 */     } else if (display != TextAreaModel.Display.INLINE) {
/*  827 */       box.accountMinRemaining(Math.max(0, box.lineWidth - le.width));
/*  828 */       box.nextLine(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   int convertToPX(Style style, StyleAttribute<Value> attribute, int full, int auto) {
/*  835 */     style = style.resolve(attribute, this.styleClassResolver);
/*  836 */     Value valueUnit = (Value)style.getNoResolve(attribute, this.styleClassResolver);
/*      */     
/*  838 */     Font font = null;
/*  839 */     if (valueUnit.unit.isFontBased()) {
/*  840 */       if (attribute == StyleAttribute.FONT_SIZE) {
/*  841 */         style = style.getParent();
/*  842 */         if (style == null) {
/*  843 */           return 14;
/*      */         }
/*      */       } 
/*  846 */       font = selectFont(style);
/*  847 */       if (font == null) {
/*  848 */         return 0;
/*      */       }
/*      */     } 
/*      */     
/*  852 */     float value = valueUnit.value;
/*  853 */     switch (valueUnit.unit) {
/*      */       case BOTTOM:
/*  855 */         value *= font.getEM();
/*      */         break;
/*      */       case FILL:
/*  858 */         value *= font.getEX();
/*      */         break;
/*      */       case MIDDLE:
/*  861 */         value *= full * 0.01F;
/*      */         break;
/*      */       case TOP:
/*  864 */         value *= 1.33F;
/*      */         break;
/*      */       case null:
/*  867 */         return auto;
/*      */     } 
/*  869 */     if (value >= 32767.0F) {
/*  870 */       return 32767;
/*      */     }
/*  872 */     if (value <= -32768.0F) {
/*  873 */       return -32768;
/*      */     }
/*  875 */     return Math.round(value);
/*      */   }
/*      */   
/*      */   int convertToPX0(Style style, StyleAttribute<Value> attribute, int full) {
/*  879 */     return Math.max(0, convertToPX(style, attribute, full, 0));
/*      */   }
/*      */   
/*      */   private Font selectFont(Style style) {
/*  883 */     StringList fontFamilies = (StringList)style.get(StyleAttribute.FONT_FAMILIES, this.styleClassResolver);
/*  884 */     if (fontFamilies != null) {
/*  885 */       if (this.fontMapper != null) {
/*  886 */         Font font = selectFontMapper(style, this.fontMapper, fontFamilies);
/*  887 */         if (font != null) {
/*  888 */           return font;
/*      */         }
/*      */       } 
/*      */       
/*  892 */       if (this.fonts != null) {
/*      */         do {
/*  894 */           Font font = this.fonts.getFont(fontFamilies.getValue());
/*  895 */           if (font != null) {
/*  896 */             return font;
/*      */           }
/*  898 */         } while ((fontFamilies = fontFamilies.getNext()) != null);
/*      */       }
/*      */     } 
/*  901 */     return this.defaultFont;
/*      */   }
/*      */   StyleSheetResolver styleClassResolver; private TextAreaModel model; private ParameterMap fonts; private ParameterMap images; private Font defaultFont; private Callback[] callbacks; private MouseCursor mouseCursorNormal; private MouseCursor mouseCursorLink; private DraggableButton.DragListener dragListener; private boolean inLayoutCode; private boolean forceRelayout; private Dimension preferredInnerSize;
/*  904 */   private static final StateSelect HOVER_STATESELECT = new StateSelect(new StateExpression[] { (StateExpression)new StateExpression.Check(STATE_HOVER) }); private FontMapper fontMapper; private FontMapperCacheEntry[] fontMapperCache; private int lastMouseX; private int lastMouseY; private boolean lastMouseInside; private boolean dragging; private int dragStartX;
/*      */   private int dragStartY;
/*      */   private LElement curLElementUnderMouse;
/*      */   private static final int DEFAULT_FONT_SIZE = 14;
/*      */   private static final int FONT_MAPPER_CACHE_SIZE = 16;
/*      */   
/*      */   private static final class FontMapperCacheEntry { final int fontSize;
/*      */     final int fontStyle;
/*      */     final StringList fontFamilies;
/*      */     final TextDecoration tdNormal;
/*      */     final TextDecoration tdHover;
/*      */     final int hashCode;
/*      */     final Font font;
/*      */     FontMapperCacheEntry next;
/*      */     
/*      */     FontMapperCacheEntry(int fontSize, int fontStyle, StringList fontFamilies, TextDecoration tdNormal, TextDecoration tdHover, int hashCode, Font font) {
/*  920 */       this.fontSize = fontSize;
/*  921 */       this.fontStyle = fontStyle;
/*  922 */       this.fontFamilies = fontFamilies;
/*  923 */       this.tdNormal = tdNormal;
/*  924 */       this.tdHover = tdHover;
/*  925 */       this.hashCode = hashCode;
/*  926 */       this.font = font;
/*      */     } }
/*      */   private Font selectFontMapper(Style style, FontMapper fontMapper, StringList fontFamilies) {
/*      */     StateSelect select;
/*      */     FontParameter[] params;
/*  931 */     int fontSize = convertToPX(style, StyleAttribute.FONT_SIZE, 14, 14);
/*  932 */     int fontStyle = 0;
/*  933 */     if (((Integer)style.get(StyleAttribute.FONT_WEIGHT, this.styleClassResolver)).intValue() >= 550) {
/*  934 */       fontStyle |= 0x1;
/*      */     }
/*  936 */     if (((Boolean)style.get(StyleAttribute.FONT_ITALIC, this.styleClassResolver)).booleanValue()) {
/*  937 */       fontStyle |= 0x2;
/*      */     }
/*      */     
/*  940 */     TextDecoration textDecoration = (TextDecoration)style.get(StyleAttribute.TEXT_DECORATION, this.styleClassResolver);
/*  941 */     TextDecoration textDecorationHover = (TextDecoration)style.get(StyleAttribute.TEXT_DECORATION_HOVER, this.styleClassResolver);
/*      */     
/*  943 */     int hashCode = fontSize;
/*  944 */     hashCode = hashCode * 67 + fontStyle;
/*  945 */     hashCode = hashCode * 67 + fontFamilies.hashCode();
/*  946 */     hashCode = hashCode * 67 + textDecoration.hashCode();
/*  947 */     hashCode = hashCode * 67 + ((textDecorationHover != null) ? textDecorationHover.hashCode() : 0);
/*      */     
/*  949 */     int cacheIdx = hashCode & 0xF;
/*      */     
/*  951 */     if (this.fontMapperCache != null) {
/*  952 */       for (FontMapperCacheEntry fontMapperCacheEntry = this.fontMapperCache[cacheIdx]; fontMapperCacheEntry != null; fontMapperCacheEntry = fontMapperCacheEntry.next) {
/*  953 */         if (fontMapperCacheEntry.hashCode == hashCode && fontMapperCacheEntry.fontSize == fontSize && fontMapperCacheEntry.fontStyle == fontStyle && fontMapperCacheEntry.tdNormal == textDecoration && fontMapperCacheEntry.tdHover == textDecorationHover && fontMapperCacheEntry.fontFamilies.equals(fontFamilies))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  959 */           return fontMapperCacheEntry.font;
/*      */         }
/*      */       } 
/*      */     } else {
/*  963 */       this.fontMapperCache = new FontMapperCacheEntry[16];
/*      */     } 
/*      */     
/*  966 */     FontParameter fpNormal = createFontParameter(textDecoration);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  971 */     if (textDecorationHover != null) {
/*  972 */       FontParameter fpHover = createFontParameter(textDecorationHover);
/*      */       
/*  974 */       select = HOVER_STATESELECT;
/*  975 */       params = new FontParameter[] { fpHover, fpNormal };
/*      */     } else {
/*  977 */       select = StateSelect.EMPTY;
/*  978 */       params = new FontParameter[] { fpNormal };
/*      */     } 
/*      */     
/*  981 */     Font font = fontMapper.getFont(fontFamilies, fontSize, fontStyle, select, params);
/*      */     
/*  983 */     FontMapperCacheEntry ce = new FontMapperCacheEntry(fontSize, fontStyle, fontFamilies, textDecoration, textDecorationHover, hashCode, font);
/*      */     
/*  985 */     ce.next = this.fontMapperCache[cacheIdx];
/*  986 */     this.fontMapperCache[cacheIdx] = ce;
/*      */     
/*  988 */     return font;
/*      */   }
/*      */   
/*      */   private static FontParameter createFontParameter(TextDecoration deco) {
/*  992 */     FontParameter fp = new FontParameter();
/*  993 */     fp.put(FontParameter.UNDERLINE, Boolean.valueOf((deco == TextDecoration.UNDERLINE)));
/*  994 */     fp.put(FontParameter.LINETHROUGH, Boolean.valueOf((deco == TextDecoration.LINE_THROUGH)));
/*  995 */     return fp;
/*      */   }
/*      */   
/*      */   private FontData createFontData(Style style) {
/*  999 */     Font font = selectFont(style);
/* 1000 */     if (font == null) {
/* 1001 */       return null;
/*      */     }
/*      */     
/* 1004 */     return new FontData(font, (Color)style.get(StyleAttribute.COLOR, this.styleClassResolver), (Color)style.get(StyleAttribute.COLOR_HOVER, this.styleClassResolver));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Image selectImage(Style style, StyleAttribute<String> element) {
/* 1010 */     String imageName = (String)style.get(element, this.styleClassResolver);
/* 1011 */     if (imageName != null) {
/* 1012 */       return selectImage(imageName);
/*      */     }
/* 1014 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private Image selectImage(String name) {
/* 1019 */     Image image = this.userImages.get(name);
/* 1020 */     if (image != null) {
/* 1021 */       return image;
/*      */     }
/* 1023 */     for (int i = 0; i < this.imageResolvers.size(); i++) {
/* 1024 */       image = ((ImageResolver)this.imageResolvers.get(i)).resolveImage(name);
/* 1025 */       if (image != null) {
/* 1026 */         return image;
/*      */       }
/*      */     } 
/* 1029 */     if (this.images != null) {
/* 1030 */       return this.images.getImage(name);
/*      */     }
/* 1032 */     return null;
/*      */   }
/*      */   
/*      */   private void layoutParagraphElement(Box box, TextAreaModel.ParagraphElement pe) {
/* 1036 */     Style style = pe.getStyle();
/* 1037 */     Font font = selectFont(style);
/*      */     
/* 1039 */     doMarginTop(box, style);
/* 1040 */     LElement anchor = box.addAnchor((TextAreaModel.Element)pe);
/* 1041 */     box.setupTextParams(style, font, true);
/*      */     
/* 1043 */     layoutElements(box, (Iterable<TextAreaModel.Element>)pe);
/*      */     
/* 1045 */     if (box.textAlignment == TextAreaModel.HAlignment.JUSTIFY) {
/* 1046 */       box.textAlignment = TextAreaModel.HAlignment.LEFT;
/*      */     }
/* 1048 */     box.nextLine(false);
/* 1049 */     box.inParagraph = false;
/*      */     
/* 1051 */     anchor.height = box.curY - anchor.y;
/* 1052 */     doMarginBottom(box, style);
/*      */   }
/*      */   private void layoutTextElement(Box box, TextAreaModel.TextElement te) {
/*      */     boolean inheritHover;
/* 1056 */     String text = te.getText();
/* 1057 */     Style style = te.getStyle();
/* 1058 */     FontData fontData = createFontData(style);
/* 1059 */     boolean pre = ((Boolean)style.get(StyleAttribute.PREFORMATTED, this.styleClassResolver)).booleanValue();
/*      */     
/* 1061 */     if (fontData == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1066 */     Boolean inheritHoverStyle = (Boolean)style.resolve(StyleAttribute.INHERIT_HOVER, this.styleClassResolver).getRaw(StyleAttribute.INHERIT_HOVER);
/* 1067 */     if (inheritHoverStyle != null) {
/* 1068 */       inheritHover = inheritHoverStyle.booleanValue();
/*      */     } else {
/* 1070 */       inheritHover = (box.style != null && box.style == style.getParent());
/*      */     } 
/*      */     
/* 1073 */     box.setupTextParams(style, fontData.font, false);
/*      */     
/* 1075 */     if (pre && !box.wasPreformatted) {
/* 1076 */       box.nextLine(false);
/*      */     }
/*      */     
/* 1079 */     int idx = 0;
/* 1080 */     while (idx < text.length()) {
/* 1081 */       int end = TextUtil.indexOf(text, '\n', idx);
/* 1082 */       if (pre) {
/* 1083 */         layoutTextPre(box, te, fontData, text, idx, end, inheritHover);
/*      */       } else {
/* 1085 */         layoutText(box, te, fontData, text, idx, end, inheritHover);
/*      */       } 
/*      */       
/* 1088 */       if (end < text.length() && text.charAt(end) == '\n') {
/* 1089 */         end++;
/* 1090 */         box.nextLine(true);
/*      */       } 
/* 1092 */       idx = end;
/*      */     } 
/*      */     
/* 1095 */     box.wasPreformatted = pre;
/*      */   }
/*      */ 
/*      */   
/*      */   private void layoutText(Box box, TextAreaModel.TextElement te, FontData fontData, String text, int textStart, int textEnd, boolean inheritHover) {
/* 1100 */     int idx = textStart;
/*      */     
/* 1102 */     while (textStart < textEnd && isSkip(text.charAt(textStart))) {
/* 1103 */       textStart++;
/*      */     }
/*      */     
/* 1106 */     boolean endsWithSpace = false;
/* 1107 */     while (textEnd > textStart && isSkip(text.charAt(textEnd - 1))) {
/* 1108 */       endsWithSpace = true;
/* 1109 */       textEnd--;
/*      */     } 
/*      */     
/* 1112 */     Font font = fontData.font;
/*      */ 
/*      */ 
/*      */     
/* 1116 */     if (textStart > idx && box.prevOnLineEndsNotWithSpace()) {
/* 1117 */       box.curX += font.getSpaceWidth();
/*      */     }
/*      */     
/* 1120 */     Boolean breakWord = null;
/*      */     
/* 1122 */     idx = textStart;
/* 1123 */     while (idx < textEnd) {
/* 1124 */       assert !isSkip(text.charAt(idx));
/*      */       
/* 1126 */       int end = idx;
/* 1127 */       int visibleEnd = idx;
/* 1128 */       if (box.textAlignment != TextAreaModel.HAlignment.JUSTIFY) {
/* 1129 */         end = idx + font.computeVisibleGlpyhs(text, idx, textEnd, box.getRemaining());
/* 1130 */         visibleEnd = end;
/*      */         
/* 1132 */         if (end < textEnd) {
/*      */ 
/*      */ 
/*      */           
/* 1136 */           while (end > idx && isPunctuation(text.charAt(end))) {
/* 1137 */             end--;
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1142 */           if (!isBreak(text.charAt(end)))
/*      */           {
/*      */             
/* 1145 */             while (end > idx && !isBreak(text.charAt(end - 1))) {
/* 1146 */               end--;
/*      */             }
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1152 */         while (end > idx && isSkip(text.charAt(end - 1))) {
/* 1153 */           end--;
/*      */         }
/*      */       } 
/*      */       
/* 1157 */       boolean advancePastFloaters = false;
/*      */ 
/*      */       
/* 1160 */       if (end == idx) {
/*      */         
/* 1162 */         if (box.textAlignment != TextAreaModel.HAlignment.JUSTIFY && box.nextLine(false)) {
/*      */           continue;
/*      */         }
/* 1165 */         if (breakWord == null) {
/* 1166 */           breakWord = (Boolean)te.getStyle().get(StyleAttribute.BREAKWORD, this.styleClassResolver);
/*      */         }
/* 1168 */         if (breakWord.booleanValue()) {
/* 1169 */           if (visibleEnd == idx) {
/* 1170 */             end = idx + 1;
/*      */           } else {
/* 1172 */             end = visibleEnd;
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1177 */           while (end < textEnd && !isBreak(text.charAt(end))) {
/* 1178 */             end++;
/*      */           }
/*      */           
/* 1181 */           while (end < textEnd && isPunctuation(text.charAt(end))) {
/* 1182 */             end++;
/*      */           }
/*      */         } 
/* 1185 */         advancePastFloaters = true;
/*      */       } 
/*      */       
/* 1188 */       if (idx < end) {
/* 1189 */         LText lt = new LText((TextAreaModel.Element)te, fontData, text, idx, end, box.doCacheText);
/* 1190 */         if (advancePastFloaters) {
/* 1191 */           box.advancePastFloaters(lt.width, box.marginLeft, box.marginRight);
/*      */         }
/* 1193 */         if (box.textAlignment == TextAreaModel.HAlignment.JUSTIFY && box.getRemaining() < lt.width) {
/* 1194 */           box.nextLine(false);
/*      */         }
/*      */         
/* 1197 */         int width = lt.width;
/* 1198 */         if (end < textEnd && isSkip(text.charAt(end))) {
/* 1199 */           width += font.getSpaceWidth();
/*      */         }
/*      */         
/* 1202 */         lt.x = box.getXAndAdvance(width);
/* 1203 */         lt.marginTop = (short)box.marginTop;
/* 1204 */         lt.href = box.href;
/* 1205 */         lt.inheritHover = inheritHover;
/* 1206 */         box.layout.add(lt);
/*      */       } 
/*      */ 
/*      */       
/* 1210 */       idx = end;
/* 1211 */       while (idx < textEnd && isSkip(text.charAt(idx))) {
/* 1212 */         idx++;
/*      */       }
/*      */     } 
/*      */     
/* 1216 */     if (!box.isAtStartOfLine() && endsWithSpace) {
/* 1217 */       box.curX += font.getSpaceWidth();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void layoutTextPre(Box box, TextAreaModel.TextElement te, FontData fontData, String text, int textStart, int textEnd, boolean inheritHover) {
/*      */     // Byte code:
/*      */     //   0: aload_3
/*      */     //   1: getfield font : Lde/matthiasmann/twl/renderer/Font;
/*      */     //   4: astore #8
/*      */     //   6: iload #5
/*      */     //   8: istore #9
/*      */     //   10: iload #9
/*      */     //   12: iload #6
/*      */     //   14: if_icmpge -> 224
/*      */     //   17: aload #4
/*      */     //   19: iload #9
/*      */     //   21: invokevirtual charAt : (I)C
/*      */     //   24: bipush #9
/*      */     //   26: if_icmpne -> 72
/*      */     //   29: iinc #9, 1
/*      */     //   32: aload_1
/*      */     //   33: aload_2
/*      */     //   34: invokevirtual getStyle : ()Lde/matthiasmann/twl/textarea/Style;
/*      */     //   37: aload #8
/*      */     //   39: invokevirtual computeNextTabStop : (Lde/matthiasmann/twl/textarea/Style;Lde/matthiasmann/twl/renderer/Font;)I
/*      */     //   42: istore #10
/*      */     //   44: iload #10
/*      */     //   46: aload_1
/*      */     //   47: getfield lineWidth : I
/*      */     //   50: if_icmpge -> 62
/*      */     //   53: aload_1
/*      */     //   54: iload #10
/*      */     //   56: putfield curX : I
/*      */     //   59: goto -> 72
/*      */     //   62: aload_1
/*      */     //   63: invokevirtual isAtStartOfLine : ()Z
/*      */     //   66: ifne -> 72
/*      */     //   69: goto -> 224
/*      */     //   72: aload #4
/*      */     //   74: bipush #9
/*      */     //   76: iload #9
/*      */     //   78: invokevirtual indexOf : (II)I
/*      */     //   81: istore #10
/*      */     //   83: iload #6
/*      */     //   85: istore #11
/*      */     //   87: iload #10
/*      */     //   89: iflt -> 103
/*      */     //   92: iload #10
/*      */     //   94: iload #6
/*      */     //   96: if_icmpge -> 103
/*      */     //   99: iload #10
/*      */     //   101: istore #11
/*      */     //   103: iload #11
/*      */     //   105: iload #9
/*      */     //   107: if_icmple -> 217
/*      */     //   110: aload #8
/*      */     //   112: aload #4
/*      */     //   114: iload #9
/*      */     //   116: iload #11
/*      */     //   118: aload_1
/*      */     //   119: invokevirtual getRemaining : ()I
/*      */     //   122: invokeinterface computeVisibleGlpyhs : (Ljava/lang/CharSequence;III)I
/*      */     //   127: istore #12
/*      */     //   129: iload #12
/*      */     //   131: ifne -> 144
/*      */     //   134: aload_1
/*      */     //   135: invokevirtual isAtStartOfLine : ()Z
/*      */     //   138: ifne -> 144
/*      */     //   141: goto -> 224
/*      */     //   144: iload #9
/*      */     //   146: iconst_1
/*      */     //   147: iload #12
/*      */     //   149: invokestatic max : (II)I
/*      */     //   152: iadd
/*      */     //   153: istore #11
/*      */     //   155: new de/matthiasmann/twl/TextArea$LText
/*      */     //   158: dup
/*      */     //   159: aload_2
/*      */     //   160: aload_3
/*      */     //   161: aload #4
/*      */     //   163: iload #9
/*      */     //   165: iload #11
/*      */     //   167: aload_1
/*      */     //   168: getfield doCacheText : Z
/*      */     //   171: invokespecial <init> : (Lde/matthiasmann/twl/textarea/TextAreaModel$Element;Lde/matthiasmann/twl/TextArea$FontData;Ljava/lang/String;IIZ)V
/*      */     //   174: astore #13
/*      */     //   176: aload #13
/*      */     //   178: aload_1
/*      */     //   179: aload #13
/*      */     //   181: getfield width : I
/*      */     //   184: invokevirtual getXAndAdvance : (I)I
/*      */     //   187: putfield x : I
/*      */     //   190: aload #13
/*      */     //   192: aload_1
/*      */     //   193: getfield marginTop : I
/*      */     //   196: i2s
/*      */     //   197: putfield marginTop : S
/*      */     //   200: aload #13
/*      */     //   202: iload #7
/*      */     //   204: putfield inheritHover : Z
/*      */     //   207: aload_1
/*      */     //   208: getfield layout : Ljava/util/ArrayList;
/*      */     //   211: aload #13
/*      */     //   213: invokevirtual add : (Ljava/lang/Object;)Z
/*      */     //   216: pop
/*      */     //   217: iload #11
/*      */     //   219: istore #9
/*      */     //   221: goto -> 10
/*      */     //   224: iload #9
/*      */     //   226: iload #6
/*      */     //   228: if_icmplt -> 234
/*      */     //   231: goto -> 243
/*      */     //   234: aload_1
/*      */     //   235: iconst_0
/*      */     //   236: invokevirtual nextLine : (Z)Z
/*      */     //   239: pop
/*      */     //   240: goto -> 10
/*      */     //   243: return
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1223	-> 0
/*      */     //   #1224	-> 6
/*      */     //   #1226	-> 10
/*      */     //   #1227	-> 17
/*      */     //   #1228	-> 29
/*      */     //   #1229	-> 32
/*      */     //   #1230	-> 44
/*      */     //   #1231	-> 53
/*      */     //   #1232	-> 62
/*      */     //   #1233	-> 69
/*      */     //   #1237	-> 72
/*      */     //   #1238	-> 83
/*      */     //   #1239	-> 87
/*      */     //   #1240	-> 99
/*      */     //   #1243	-> 103
/*      */     //   #1244	-> 110
/*      */     //   #1245	-> 129
/*      */     //   #1246	-> 141
/*      */     //   #1249	-> 144
/*      */     //   #1251	-> 155
/*      */     //   #1252	-> 176
/*      */     //   #1253	-> 190
/*      */     //   #1254	-> 200
/*      */     //   #1255	-> 207
/*      */     //   #1258	-> 217
/*      */     //   #1259	-> 221
/*      */     //   #1261	-> 224
/*      */     //   #1262	-> 231
/*      */     //   #1265	-> 234
/*      */     //   #1267	-> 243
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   44	28	10	tabX	I
/*      */     //   129	88	12	count	I
/*      */     //   176	41	13	lt	Lde/matthiasmann/twl/TextArea$LText;
/*      */     //   83	138	10	tabIdx	I
/*      */     //   87	134	11	end	I
/*      */     //   0	244	0	this	Lde/matthiasmann/twl/TextArea;
/*      */     //   0	244	1	box	Lde/matthiasmann/twl/TextArea$Box;
/*      */     //   0	244	2	te	Lde/matthiasmann/twl/textarea/TextAreaModel$TextElement;
/*      */     //   0	244	3	fontData	Lde/matthiasmann/twl/TextArea$FontData;
/*      */     //   0	244	4	text	Ljava/lang/String;
/*      */     //   0	244	5	textStart	I
/*      */     //   0	244	6	textEnd	I
/*      */     //   0	244	7	inheritHover	Z
/*      */     //   6	238	8	font	Lde/matthiasmann/twl/renderer/Font;
/*      */     //   10	234	9	idx	I
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
/*      */ 
/*      */ 
/*      */   
/*      */   private void doMarginTop(Box box, Style style) {
/* 1270 */     int marginTop = convertToPX0(style, StyleAttribute.MARGIN_TOP, box.boxWidth);
/* 1271 */     box.nextLine(false);
/* 1272 */     box.advanceToY(box.computeTopPadding(marginTop));
/*      */   }
/*      */   
/*      */   private void doMarginBottom(Box box, Style style) {
/* 1276 */     int marginBottom = convertToPX0(style, StyleAttribute.MARGIN_BOTTOM, box.boxWidth);
/* 1277 */     box.setMarginBottom(marginBottom);
/*      */   }
/*      */   
/*      */   private void layoutContainerElement(Box box, TextAreaModel.ContainerElement ce) {
/* 1281 */     Style style = ce.getStyle();
/* 1282 */     doMarginTop(box, style);
/* 1283 */     box.addAnchor((TextAreaModel.Element)ce);
/* 1284 */     layoutElements(box, (Iterable<TextAreaModel.Element>)ce);
/* 1285 */     doMarginBottom(box, style);
/*      */   }
/*      */   
/*      */   private void layoutLinkElement(Box box, TextAreaModel.LinkElement le) {
/* 1289 */     String oldHref = box.href;
/* 1290 */     box.href = le.getHREF();
/*      */     
/* 1292 */     Style style = le.getStyle();
/* 1293 */     TextAreaModel.Display display = (TextAreaModel.Display)style.get(StyleAttribute.DISPLAY, this.styleClassResolver);
/* 1294 */     if (display == TextAreaModel.Display.BLOCK) {
/* 1295 */       layoutBlockElement(box, (TextAreaModel.ContainerElement)le);
/*      */     } else {
/* 1297 */       layoutContainerElement(box, (TextAreaModel.ContainerElement)le);
/*      */     } 
/*      */     
/* 1300 */     box.href = oldHref;
/*      */   }
/*      */   
/*      */   private void layoutListElement(Box box, TextAreaModel.ListElement le) {
/* 1304 */     Style style = le.getStyle();
/*      */     
/* 1306 */     doMarginTop(box, style);
/*      */     
/* 1308 */     Image image = selectImage(style, StyleAttribute.LIST_STYLE_IMAGE);
/* 1309 */     if (image != null) {
/* 1310 */       LImage li = new LImage((TextAreaModel.Element)le, image);
/* 1311 */       li.marginRight = (short)convertToPX0(style, StyleAttribute.PADDING_LEFT, box.boxWidth);
/* 1312 */       layout(box, (TextAreaModel.Element)le, li, TextAreaModel.FloatPosition.LEFT, TextAreaModel.Display.BLOCK);
/*      */       
/* 1314 */       int imageHeight = li.height;
/* 1315 */       li.height = 32767;
/*      */       
/* 1317 */       layoutElements(box, (Iterable<TextAreaModel.Element>)le);
/*      */       
/* 1319 */       li.height = imageHeight;
/*      */       
/* 1321 */       box.objLeft.remove(li);
/* 1322 */       box.advanceToY(li.bottom());
/* 1323 */       box.computePadding();
/*      */     } else {
/* 1325 */       layoutElements(box, (Iterable<TextAreaModel.Element>)le);
/* 1326 */       box.nextLine(false);
/*      */     } 
/*      */     
/* 1329 */     doMarginBottom(box, style);
/*      */   }
/*      */   
/*      */   private void layoutOrderedListElement(Box box, TextAreaModel.OrderedListElement ole) {
/* 1333 */     Style style = ole.getStyle();
/* 1334 */     FontData fontData = createFontData(style);
/*      */     
/* 1336 */     if (fontData == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1340 */     doMarginTop(box, style);
/* 1341 */     LElement anchor = box.addAnchor((TextAreaModel.Element)ole);
/*      */     
/* 1343 */     int start = Math.max(1, ole.getStart());
/* 1344 */     int count = ole.getNumElements();
/* 1345 */     OrderedListType type = (OrderedListType)style.get(StyleAttribute.LIST_STYLE_TYPE, this.styleClassResolver);
/*      */     
/* 1347 */     String[] labels = new String[count];
/* 1348 */     int maxLabelWidth = convertToPX0(style, StyleAttribute.PADDING_LEFT, box.boxWidth); int i;
/* 1349 */     for (i = 0; i < count; i++) {
/* 1350 */       labels[i] = type.format(start + i).concat(". ");
/* 1351 */       int width = fontData.font.computeTextWidth(labels[i]);
/* 1352 */       maxLabelWidth = Math.max(maxLabelWidth, width);
/*      */     } 
/*      */     
/* 1355 */     for (i = 0; i < count; i++) {
/* 1356 */       String label = labels[i];
/* 1357 */       TextAreaModel.Element li = ole.getElement(i);
/* 1358 */       Style liStyle = li.getStyle();
/* 1359 */       doMarginTop(box, liStyle);
/*      */       
/* 1361 */       LText lt = new LText((TextAreaModel.Element)ole, fontData, label, 0, label.length(), box.doCacheText);
/* 1362 */       int labelWidth = lt.width;
/* 1363 */       int labelHeight = lt.height;
/*      */       
/* 1365 */       lt.width += convertToPX0(liStyle, StyleAttribute.PADDING_LEFT, box.boxWidth);
/* 1366 */       layout(box, (TextAreaModel.Element)ole, lt, TextAreaModel.FloatPosition.LEFT, TextAreaModel.Display.BLOCK);
/* 1367 */       lt.x += Math.max(0, maxLabelWidth - labelWidth);
/* 1368 */       lt.height = 32767;
/*      */       
/* 1370 */       layoutElement(box, li);
/*      */       
/* 1372 */       lt.height = labelHeight;
/*      */       
/* 1374 */       box.objLeft.remove(lt);
/* 1375 */       box.advanceToY(lt.bottom());
/* 1376 */       box.computePadding();
/*      */       
/* 1378 */       doMarginBottom(box, liStyle);
/*      */     } 
/*      */     
/* 1381 */     anchor.height = box.curY - anchor.y;
/* 1382 */     doMarginBottom(box, style);
/*      */   }
/*      */   
/*      */   private Box layoutBox(LClip clip, int continerWidth, int paddingLeft, int paddingRight, TextAreaModel.ContainerElement ce, String href, boolean doCacheText) {
/* 1386 */     Style style = ce.getStyle();
/* 1387 */     int paddingTop = convertToPX0(style, StyleAttribute.PADDING_TOP, continerWidth);
/* 1388 */     int paddingBottom = convertToPX0(style, StyleAttribute.PADDING_BOTTOM, continerWidth);
/* 1389 */     int marginBottom = convertToPX0(style, StyleAttribute.MARGIN_BOTTOM, continerWidth);
/*      */     
/* 1391 */     Box box = new Box(clip, paddingLeft, paddingRight, paddingTop, doCacheText);
/* 1392 */     box.href = href;
/* 1393 */     box.style = style;
/* 1394 */     layoutElements(box, (Iterable<TextAreaModel.Element>)ce);
/* 1395 */     box.finish();
/*      */     
/* 1397 */     int contentHeight = box.curY + paddingBottom;
/* 1398 */     int boxHeight = Math.max(contentHeight, convertToPX(style, StyleAttribute.HEIGHT, contentHeight, contentHeight));
/* 1399 */     if (boxHeight > contentHeight) {
/* 1400 */       int amount = 0;
/* 1401 */       switch ((TextAreaModel.VAlignment)style.get(StyleAttribute.VERTICAL_ALIGNMENT, this.styleClassResolver)) {
/*      */         case BOTTOM:
/* 1403 */           amount = boxHeight - contentHeight;
/*      */           break;
/*      */         
/*      */         case FILL:
/*      */         case MIDDLE:
/* 1408 */           amount = (boxHeight - contentHeight) / 2;
/*      */           break;
/*      */       } 
/* 1411 */       if (amount > 0) {
/* 1412 */         clip.moveContentY(amount);
/*      */       }
/*      */     } 
/*      */     
/* 1416 */     clip.height = boxHeight;
/* 1417 */     clip.marginBottom = (short)Math.max(marginBottom, box.marginBottomAbs - box.curY);
/* 1418 */     return box;
/*      */   }
/*      */   
/*      */   private void layoutBlockElement(Box box, TextAreaModel.ContainerElement be) {
/* 1422 */     box.nextLine(false);
/*      */     
/* 1424 */     Style style = be.getStyle();
/* 1425 */     TextAreaModel.FloatPosition floatPosition = (TextAreaModel.FloatPosition)style.get(StyleAttribute.FLOAT_POSITION, this.styleClassResolver);
/*      */ 
/*      */     
/* 1428 */     LImage bgImage = createBGImage(box, (TextAreaModel.Element)be);
/*      */     
/* 1430 */     int marginTop = convertToPX0(style, StyleAttribute.MARGIN_TOP, box.boxWidth);
/* 1431 */     int marginLeft = convertToPX0(style, StyleAttribute.MARGIN_LEFT, box.boxWidth);
/* 1432 */     int marginRight = convertToPX0(style, StyleAttribute.MARGIN_RIGHT, box.boxWidth);
/*      */     
/* 1434 */     int bgX = box.computeLeftPadding(marginLeft);
/* 1435 */     int bgY = box.computeTopPadding(marginTop);
/*      */ 
/*      */     
/* 1438 */     int remaining = Math.max(0, box.computeRightPadding(marginRight) - bgX);
/* 1439 */     int paddingLeft = convertToPX0(style, StyleAttribute.PADDING_LEFT, box.boxWidth);
/* 1440 */     int paddingRight = convertToPX0(style, StyleAttribute.PADDING_RIGHT, box.boxWidth);
/*      */     
/* 1442 */     if (floatPosition == TextAreaModel.FloatPosition.NONE) {
/* 1443 */       bgWidth = convertToPX(style, StyleAttribute.WIDTH, remaining, remaining);
/*      */     } else {
/* 1445 */       bgWidth = convertToPX(style, StyleAttribute.WIDTH, box.boxWidth, -2147483648);
/* 1446 */       if (bgWidth == Integer.MIN_VALUE) {
/* 1447 */         LClip dummy = new LClip(null);
/* 1448 */         dummy.width = Math.max(0, box.lineWidth - paddingLeft - paddingRight);
/*      */         
/* 1450 */         Box dummyBox = layoutBox(dummy, box.boxWidth, paddingLeft, paddingRight, be, (String)null, false);
/* 1451 */         dummyBox.nextLine(false);
/*      */         
/* 1453 */         bgWidth = Math.max(0, dummy.width - dummyBox.minRemainingWidth);
/*      */       } 
/*      */     } 
/*      */     
/* 1457 */     int bgWidth = Math.max(0, bgWidth) + paddingLeft + paddingRight;
/*      */     
/* 1459 */     if (floatPosition != TextAreaModel.FloatPosition.NONE) {
/* 1460 */       box.advancePastFloaters(bgWidth, marginLeft, marginRight);
/*      */       
/* 1462 */       bgX = box.computeLeftPadding(marginLeft);
/* 1463 */       bgY = Math.max(bgY, box.curY);
/* 1464 */       remaining = Math.max(0, box.computeRightPadding(marginRight) - bgX);
/*      */     } 
/*      */     
/* 1467 */     bgWidth = Math.min(bgWidth, remaining);
/*      */     
/* 1469 */     if (floatPosition == TextAreaModel.FloatPosition.RIGHT) {
/* 1470 */       bgX = box.computeRightPadding(marginRight) - bgWidth;
/*      */     }
/*      */     
/* 1473 */     LClip clip = new LClip((TextAreaModel.Element)be);
/* 1474 */     clip.x = bgX;
/* 1475 */     clip.y = bgY;
/* 1476 */     clip.width = bgWidth;
/* 1477 */     clip.marginLeft = (short)marginLeft;
/* 1478 */     clip.marginRight = (short)marginRight;
/* 1479 */     clip.href = box.href;
/* 1480 */     box.layout.add(clip);
/*      */     
/* 1482 */     Box clipBox = layoutBox(clip, box.boxWidth, paddingLeft, paddingRight, be, box.href, box.doCacheText);
/*      */ 
/*      */     
/* 1485 */     box.lineStartIdx = box.layout.size();
/*      */     
/* 1487 */     if (floatPosition == TextAreaModel.FloatPosition.NONE) {
/* 1488 */       box.advanceToY(bgY + clip.height);
/* 1489 */       box.setMarginBottom(clip.marginBottom);
/* 1490 */       box.accountMinRemaining(clipBox.minRemainingWidth);
/*      */     } else {
/* 1492 */       if (floatPosition == TextAreaModel.FloatPosition.RIGHT) {
/* 1493 */         box.objRight.add(clip);
/*      */       } else {
/* 1495 */         box.objLeft.add(clip);
/*      */       } 
/* 1497 */       box.computePadding();
/*      */     } 
/*      */     
/* 1500 */     if (bgImage != null) {
/* 1501 */       bgImage.x = bgX;
/* 1502 */       bgImage.y = bgY;
/* 1503 */       bgImage.width = bgWidth;
/* 1504 */       bgImage.height = clip.height;
/* 1505 */       bgImage.hoverSrc = clip;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void computeTableWidth(TextAreaModel.TableElement te, int maxTableWidth, int[] columnWidth, int[] columnSpacing, boolean[] columnsWithFixedWidth) {
/* 1511 */     int numColumns = te.getNumColumns();
/* 1512 */     int numRows = te.getNumRows();
/* 1513 */     int cellSpacing = te.getCellSpacing();
/* 1514 */     int cellPadding = te.getCellPadding();
/*      */     
/* 1516 */     HashMap<Integer, Integer> colspanWidths = null;
/*      */     
/* 1518 */     for (int col = 0; col < numColumns; col++) {
/* 1519 */       int width = 0;
/* 1520 */       int marginLeft = 0;
/* 1521 */       int marginRight = 0;
/* 1522 */       boolean hasFixedWidth = false;
/*      */       
/* 1524 */       for (int row = 0; row < numRows; row++) {
/* 1525 */         TextAreaModel.TableCellElement cell = te.getCell(row, col);
/* 1526 */         if (cell != null) {
/* 1527 */           Style cellStyle = cell.getStyle();
/* 1528 */           int colspan = cell.getColspan();
/* 1529 */           int cellWidth = convertToPX(cellStyle, StyleAttribute.WIDTH, maxTableWidth, -2147483648);
/* 1530 */           if (cellWidth == Integer.MIN_VALUE && (colspan > 1 || !hasFixedWidth)) {
/* 1531 */             int paddingLeft = Math.max(cellPadding, convertToPX0(cellStyle, StyleAttribute.PADDING_LEFT, maxTableWidth));
/* 1532 */             int paddingRight = Math.max(cellPadding, convertToPX0(cellStyle, StyleAttribute.PADDING_RIGHT, maxTableWidth));
/*      */             
/* 1534 */             LClip dummy = new LClip(null);
/* 1535 */             dummy.width = maxTableWidth;
/* 1536 */             Box dummyBox = layoutBox(dummy, maxTableWidth, paddingLeft, paddingRight, (TextAreaModel.ContainerElement)cell, (String)null, false);
/* 1537 */             dummyBox.finish();
/*      */             
/* 1539 */             cellWidth = maxTableWidth - dummyBox.minRemainingWidth;
/* 1540 */           } else if (colspan == 1 && cellWidth >= 0) {
/* 1541 */             hasFixedWidth = true;
/*      */           } 
/*      */           
/* 1544 */           if (colspan > 1) {
/* 1545 */             if (colspanWidths == null) {
/* 1546 */               colspanWidths = new HashMap<Integer, Integer>();
/*      */             }
/* 1548 */             Integer key = Integer.valueOf((col << 16) + colspan);
/* 1549 */             Integer value = colspanWidths.get(key);
/* 1550 */             if (value == null || cellWidth > value.intValue()) {
/* 1551 */               colspanWidths.put(key, Integer.valueOf(cellWidth));
/*      */             }
/*      */           } else {
/* 1554 */             width = Math.max(width, cellWidth);
/* 1555 */             marginLeft = Math.max(marginLeft, convertToPX(cellStyle, StyleAttribute.MARGIN_LEFT, maxTableWidth, 0));
/* 1556 */             marginRight = Math.max(marginRight, convertToPX(cellStyle, StyleAttribute.MARGIN_LEFT, maxTableWidth, 0));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1561 */       columnsWithFixedWidth[col] = hasFixedWidth;
/* 1562 */       columnWidth[col] = width;
/* 1563 */       columnSpacing[col] = Math.max(columnSpacing[col], marginLeft);
/* 1564 */       columnSpacing[col + 1] = Math.max(cellSpacing, marginRight);
/*      */     } 
/*      */     
/* 1567 */     if (colspanWidths != null) {
/* 1568 */       for (Map.Entry<Integer, Integer> e : colspanWidths.entrySet()) {
/* 1569 */         int key = ((Integer)e.getKey()).intValue();
/* 1570 */         int j = key >>> 16;
/* 1571 */         int colspan = key & 0xFFFF;
/* 1572 */         int width = ((Integer)e.getValue()).intValue();
/* 1573 */         int remainingCols = colspan;
/*      */         int i;
/* 1575 */         for (i = 0; i < colspan; i++) {
/* 1576 */           if (columnsWithFixedWidth[j + i]) {
/* 1577 */             width -= columnWidth[j + i];
/* 1578 */             remainingCols--;
/*      */           } 
/*      */         } 
/*      */         
/* 1582 */         if (width > 0) {
/* 1583 */           for (i = 0; i < colspan && remainingCols > 0; i++) {
/* 1584 */             if (!columnsWithFixedWidth[j + i]) {
/* 1585 */               int colWidth = width / remainingCols;
/* 1586 */               columnWidth[j + i] = Math.max(columnWidth[j + i], colWidth);
/* 1587 */               width -= colWidth;
/* 1588 */               remainingCols--;
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private void layoutTableElement(Box box, TextAreaModel.TableElement te) {
/* 1597 */     int numColumns = te.getNumColumns();
/* 1598 */     int numRows = te.getNumRows();
/* 1599 */     int cellSpacing = te.getCellSpacing();
/* 1600 */     int cellPadding = te.getCellPadding();
/* 1601 */     Style tableStyle = te.getStyle();
/*      */     
/* 1603 */     if (numColumns == 0 || numRows == 0) {
/*      */       return;
/*      */     }
/*      */     
/* 1607 */     doMarginTop(box, tableStyle);
/* 1608 */     LElement anchor = box.addAnchor((TextAreaModel.Element)te);
/*      */     
/* 1610 */     int left = box.computeLeftPadding(convertToPX0(tableStyle, StyleAttribute.MARGIN_LEFT, box.boxWidth));
/* 1611 */     int right = box.computeRightPadding(convertToPX0(tableStyle, StyleAttribute.MARGIN_RIGHT, box.boxWidth));
/* 1612 */     int maxTableWidth = Math.max(0, right - left);
/* 1613 */     int tableWidth = Math.min(maxTableWidth, convertToPX(tableStyle, StyleAttribute.WIDTH, box.boxWidth, -2147483648));
/* 1614 */     boolean autoTableWidth = (tableWidth == Integer.MIN_VALUE);
/*      */     
/* 1616 */     if (tableWidth <= 0) {
/* 1617 */       tableWidth = maxTableWidth;
/*      */     }
/*      */     
/* 1620 */     int[] columnWidth = new int[numColumns];
/* 1621 */     int[] columnSpacing = new int[numColumns + 1];
/* 1622 */     boolean[] columnsWithFixedWidth = new boolean[numColumns];
/*      */     
/* 1624 */     columnSpacing[0] = Math.max(cellSpacing, convertToPX0(tableStyle, StyleAttribute.PADDING_LEFT, box.boxWidth));
/*      */     
/* 1626 */     computeTableWidth(te, tableWidth, columnWidth, columnSpacing, columnsWithFixedWidth);
/*      */     
/* 1628 */     columnSpacing[numColumns] = Math.max(columnSpacing[numColumns], convertToPX0(tableStyle, StyleAttribute.PADDING_RIGHT, box.boxWidth));
/*      */ 
/*      */     
/* 1631 */     int columnSpacingSum = 0;
/* 1632 */     for (int spacing : columnSpacing) {
/* 1633 */       columnSpacingSum += spacing;
/*      */     }
/*      */     
/* 1636 */     int columnWidthSum = 0;
/* 1637 */     for (int width : columnWidth) {
/* 1638 */       columnWidthSum += width;
/*      */     }
/*      */     
/* 1641 */     if (autoTableWidth) {
/* 1642 */       tableWidth = Math.min(maxTableWidth, columnWidthSum + columnSpacingSum);
/*      */     }
/*      */     
/* 1645 */     int availableColumnWidth = Math.max(0, tableWidth - columnSpacingSum);
/* 1646 */     if (availableColumnWidth != columnWidthSum && columnWidthSum > 0) {
/* 1647 */       int available = availableColumnWidth;
/* 1648 */       int toDistribute = columnWidthSum;
/* 1649 */       int remainingCols = numColumns;
/*      */       
/* 1651 */       for (int col = 0; col < numColumns; col++) {
/* 1652 */         if (columnsWithFixedWidth[col]) {
/* 1653 */           int width = columnWidth[col];
/* 1654 */           available -= width;
/* 1655 */           toDistribute -= width;
/* 1656 */           remainingCols--;
/*      */         } 
/*      */       } 
/*      */       
/* 1660 */       boolean allColumns = false;
/* 1661 */       if (availableColumnWidth < 0) {
/* 1662 */         available = availableColumnWidth;
/* 1663 */         toDistribute = columnWidthSum;
/* 1664 */         remainingCols = numColumns;
/* 1665 */         allColumns = true;
/*      */       } 
/*      */       
/* 1668 */       for (int i = 0; i < numColumns && remainingCols > 0; i++) {
/* 1669 */         if (allColumns || !columnsWithFixedWidth[i]) {
/* 1670 */           int width = columnWidth[i];
/* 1671 */           int newWidth = (toDistribute > 0) ? (width * available / toDistribute) : 0;
/* 1672 */           columnWidth[i] = newWidth;
/* 1673 */           available -= newWidth;
/* 1674 */           toDistribute -= width;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1679 */     LImage tableBGImage = createBGImage(box, (TextAreaModel.Element)te);
/*      */     
/* 1681 */     box.textAlignment = TextAreaModel.HAlignment.LEFT;
/* 1682 */     box.curY += Math.max(cellSpacing, convertToPX0(tableStyle, StyleAttribute.PADDING_TOP, box.boxWidth));
/*      */     
/* 1684 */     LImage[] bgImages = new LImage[numColumns];
/*      */     
/* 1686 */     for (int row = 0; row < numRows; row++) {
/* 1687 */       if (row > 0) {
/* 1688 */         box.curY += cellSpacing;
/*      */       }
/*      */       
/* 1691 */       LImage rowBGImage = null;
/* 1692 */       Style rowStyle = te.getRowStyle(row);
/* 1693 */       if (rowStyle != null) {
/* 1694 */         int marginTop = convertToPX0(rowStyle, StyleAttribute.MARGIN_TOP, tableWidth);
/* 1695 */         box.curY = box.computeTopPadding(marginTop);
/*      */         
/* 1697 */         Image image = selectImage(rowStyle, StyleAttribute.BACKGROUND_IMAGE);
/* 1698 */         if (image != null) {
/* 1699 */           rowBGImage = new LImage((TextAreaModel.Element)te, image);
/* 1700 */           rowBGImage.y = box.curY;
/* 1701 */           rowBGImage.x = left;
/* 1702 */           rowBGImage.width = tableWidth;
/* 1703 */           box.clip.bgImages.add(rowBGImage);
/*      */         } 
/*      */         
/* 1706 */         box.curY += convertToPX0(rowStyle, StyleAttribute.PADDING_TOP, tableWidth);
/* 1707 */         box.minLineHeight = convertToPX0(rowStyle, StyleAttribute.HEIGHT, tableWidth);
/*      */       } 
/*      */       
/* 1710 */       int x = left; int col;
/* 1711 */       for (col = 0; col < numColumns; col++) {
/* 1712 */         x += columnSpacing[col];
/* 1713 */         TextAreaModel.TableCellElement cell = te.getCell(row, col);
/* 1714 */         int width = columnWidth[col];
/* 1715 */         if (cell != null) {
/* 1716 */           for (int c = 1; c < cell.getColspan(); c++) {
/* 1717 */             width += columnSpacing[col + c] + columnWidth[col + c];
/*      */           }
/*      */           
/* 1720 */           Style cellStyle = cell.getStyle();
/*      */           
/* 1722 */           int paddingLeft = Math.max(cellPadding, convertToPX0(cellStyle, StyleAttribute.PADDING_LEFT, tableWidth));
/* 1723 */           int paddingRight = Math.max(cellPadding, convertToPX0(cellStyle, StyleAttribute.PADDING_RIGHT, tableWidth));
/*      */           
/* 1725 */           LClip clip = new LClip((TextAreaModel.Element)cell);
/* 1726 */           LImage bgImage = createBGImage(box, (TextAreaModel.Element)cell);
/* 1727 */           if (bgImage != null) {
/* 1728 */             bgImage.x = x;
/* 1729 */             bgImage.width = width;
/* 1730 */             bgImage.hoverSrc = clip;
/* 1731 */             bgImages[col] = bgImage;
/*      */           } 
/*      */           
/* 1734 */           clip.x = x;
/* 1735 */           clip.y = box.curY;
/* 1736 */           clip.width = width;
/* 1737 */           clip.marginTop = (short)convertToPX0(cellStyle, StyleAttribute.MARGIN_TOP, tableWidth);
/* 1738 */           box.layout.add(clip);
/*      */           
/* 1740 */           layoutBox(clip, tableWidth, paddingLeft, paddingRight, (TextAreaModel.ContainerElement)cell, (String)null, box.doCacheText);
/*      */           
/* 1742 */           col += Math.max(0, cell.getColspan() - 1);
/*      */         } 
/* 1744 */         x += width;
/*      */       } 
/* 1746 */       box.nextLine(false);
/*      */       
/* 1748 */       for (col = 0; col < numColumns; col++) {
/* 1749 */         LImage bgImage = bgImages[col];
/* 1750 */         if (bgImage != null) {
/* 1751 */           bgImage.height = box.curY - bgImage.y;
/* 1752 */           bgImages[col] = null;
/*      */         } 
/*      */       } 
/*      */       
/* 1756 */       if (rowStyle != null) {
/* 1757 */         box.curY += convertToPX0(rowStyle, StyleAttribute.PADDING_BOTTOM, tableWidth);
/*      */         
/* 1759 */         if (rowBGImage != null) {
/* 1760 */           rowBGImage.height = box.curY - rowBGImage.y;
/*      */         }
/*      */         
/* 1763 */         doMarginBottom(box, rowStyle);
/*      */       } 
/*      */     } 
/*      */     
/* 1767 */     box.curY += Math.max(cellSpacing, convertToPX0(tableStyle, StyleAttribute.PADDING_BOTTOM, box.boxWidth));
/* 1768 */     box.checkFloaters();
/* 1769 */     box.accountMinRemaining(Math.max(0, box.lineWidth - tableWidth));
/*      */     
/* 1771 */     if (tableBGImage != null) {
/* 1772 */       tableBGImage.height = box.curY - tableBGImage.y;
/* 1773 */       tableBGImage.x = left;
/* 1774 */       tableBGImage.width = tableWidth;
/*      */     } 
/*      */ 
/*      */     
/* 1778 */     anchor.x = left;
/* 1779 */     anchor.width = tableWidth;
/* 1780 */     anchor.height = box.curY - anchor.y;
/*      */     
/* 1782 */     doMarginBottom(box, tableStyle);
/*      */   }
/*      */   
/*      */   private LImage createBGImage(Box box, TextAreaModel.Element element) {
/* 1786 */     Style style = element.getStyle();
/* 1787 */     Image image = selectImage(style, StyleAttribute.BACKGROUND_IMAGE);
/* 1788 */     if (image == null) {
/* 1789 */       image = createBackgroundColor(style);
/*      */     }
/* 1791 */     if (image != null) {
/* 1792 */       LImage bgImage = new LImage(element, image);
/* 1793 */       bgImage.y = box.curY;
/* 1794 */       box.clip.bgImages.add(bgImage);
/* 1795 */       return bgImage;
/*      */     } 
/* 1797 */     return null;
/*      */   }
/*      */   
/*      */   private Image createBackgroundColor(Style style) {
/* 1801 */     Color color = (Color)style.get(StyleAttribute.BACKGROUND_COLOR, this.styleClassResolver);
/* 1802 */     if (color.getAlpha() != 0) {
/* 1803 */       Image white = selectImage("white");
/* 1804 */       if (white != null) {
/* 1805 */         Image image = white.createTintedVersion(color);
/* 1806 */         Color colorHover = (Color)style.get(StyleAttribute.BACKGROUND_COLOR_HOVER, this.styleClassResolver);
/* 1807 */         if (colorHover != null) {
/* 1808 */           return (Image)new StateSelectImage(HOVER_STATESELECT, null, new Image[] { white.createTintedVersion(colorHover), image });
/*      */         }
/*      */         
/* 1811 */         return image;
/*      */       } 
/*      */     } 
/* 1814 */     return null;
/*      */   }
/*      */   
/*      */   static boolean isSkip(char ch) {
/* 1818 */     return Character.isWhitespace(ch);
/*      */   }
/*      */   
/*      */   static boolean isPunctuation(char ch) {
/* 1822 */     return (":;,.-!?".indexOf(ch) >= 0);
/*      */   }
/*      */   
/*      */   static boolean isBreak(char ch) {
/* 1826 */     return (Character.isWhitespace(ch) || isPunctuation(ch) || ch == '、' || ch == '。');
/*      */   }
/*      */   
/*      */   class Box
/*      */   {
/*      */     final TextArea.LClip clip;
/*      */     final ArrayList<TextArea.LElement> layout;
/* 1833 */     final ArrayList<TextArea.LElement> objLeft = new ArrayList<TextArea.LElement>();
/* 1834 */     final ArrayList<TextArea.LElement> objRight = new ArrayList<TextArea.LElement>();
/* 1835 */     final StringBuilder lineInfo = new StringBuilder();
/*      */     final int boxLeft;
/*      */     final int boxWidth;
/*      */     final int boxMarginOffsetLeft;
/*      */     final int boxMarginOffsetRight;
/*      */     final boolean doCacheText;
/*      */     int curY;
/*      */     int curX;
/*      */     int lineStartIdx;
/*      */     int lastProcessedAnchorIdx;
/*      */     int marginTop;
/*      */     int marginLeft;
/*      */     int marginRight;
/*      */     int marginBottomAbs;
/*      */     int marginBottomNext;
/*      */     int lineStartX;
/*      */     int lineWidth;
/*      */     int fontLineHeight;
/*      */     int minLineHeight;
/*      */     int lastLineEnd;
/*      */     int lastLineBottom;
/*      */     int minRemainingWidth;
/*      */     boolean inParagraph;
/*      */     boolean wasAutoBreak;
/*      */     boolean wasPreformatted;
/*      */     TextAreaModel.HAlignment textAlignment;
/*      */     String href;
/*      */     Style style;
/*      */     
/*      */     Box(TextArea.LClip clip, int paddingLeft, int paddingRight, int paddingTop, boolean doCacheText) {
/* 1865 */       this.clip = clip;
/* 1866 */       this.layout = clip.layout;
/* 1867 */       this.boxLeft = paddingLeft;
/* 1868 */       this.boxWidth = Math.max(0, clip.width - paddingLeft - paddingRight);
/* 1869 */       this.boxMarginOffsetLeft = paddingLeft;
/* 1870 */       this.boxMarginOffsetRight = paddingRight;
/* 1871 */       this.doCacheText = doCacheText;
/* 1872 */       this.curX = paddingLeft;
/* 1873 */       this.curY = paddingTop;
/* 1874 */       this.lineStartX = paddingLeft;
/* 1875 */       this.lineWidth = this.boxWidth;
/* 1876 */       this.minRemainingWidth = this.boxWidth;
/* 1877 */       this.textAlignment = TextAreaModel.HAlignment.LEFT;
/* 1878 */       assert this.layout.isEmpty();
/*      */     }
/*      */     
/*      */     void computePadding() {
/* 1882 */       int left = computeLeftPadding(this.marginLeft);
/* 1883 */       int right = computeRightPadding(this.marginRight);
/*      */       
/* 1885 */       this.lineStartX = left;
/* 1886 */       this.lineWidth = Math.max(0, right - left);
/*      */       
/* 1888 */       if (isAtStartOfLine()) {
/* 1889 */         this.curX = this.lineStartX;
/*      */       }
/*      */       
/* 1892 */       accountMinRemaining(getRemaining());
/*      */     }
/*      */     
/*      */     int computeLeftPadding(int marginLeft) {
/* 1896 */       int left = this.boxLeft + Math.max(0, marginLeft - this.boxMarginOffsetLeft);
/*      */       
/* 1898 */       for (int i = 0, n = this.objLeft.size(); i < n; i++) {
/* 1899 */         TextArea.LElement e = this.objLeft.get(i);
/* 1900 */         left = Math.max(left, e.x + e.width + Math.max(e.marginRight, marginLeft));
/*      */       } 
/*      */       
/* 1903 */       return left;
/*      */     }
/*      */     
/*      */     int computeRightPadding(int marginRight) {
/* 1907 */       int right = this.boxLeft + this.boxWidth - Math.max(0, marginRight - this.boxMarginOffsetRight);
/*      */       
/* 1909 */       for (int i = 0, n = this.objRight.size(); i < n; i++) {
/* 1910 */         TextArea.LElement e = this.objRight.get(i);
/* 1911 */         right = Math.min(right, e.x - Math.max(e.marginLeft, marginRight));
/*      */       } 
/*      */       
/* 1914 */       return right;
/*      */     }
/*      */     
/*      */     int computePaddingWidth(int marginLeft, int marginRight) {
/* 1918 */       return Math.max(0, computeRightPadding(marginRight) - computeLeftPadding(marginLeft));
/*      */     }
/*      */     
/*      */     int computeTopPadding(int marginTop) {
/* 1922 */       return Math.max(this.marginBottomAbs, this.curY + marginTop);
/*      */     }
/*      */     
/*      */     void setMarginBottom(int marginBottom) {
/* 1926 */       if (isAtStartOfLine()) {
/* 1927 */         this.marginBottomAbs = Math.max(this.marginBottomAbs, this.curY + marginBottom);
/*      */       } else {
/* 1929 */         this.marginBottomNext = Math.max(this.marginBottomNext, marginBottom);
/*      */       } 
/*      */     }
/*      */     
/*      */     int getRemaining() {
/* 1934 */       return Math.max(0, this.lineWidth - this.curX + this.lineStartX);
/*      */     }
/*      */     
/*      */     void accountMinRemaining(int remaining) {
/* 1938 */       this.minRemainingWidth = Math.min(this.minRemainingWidth, remaining);
/*      */     }
/*      */     
/*      */     int getXAndAdvance(int amount) {
/* 1942 */       int x = this.curX;
/* 1943 */       this.curX = x + amount;
/* 1944 */       return x;
/*      */     }
/*      */     
/*      */     boolean isAtStartOfLine() {
/* 1948 */       return (this.lineStartIdx == this.layout.size());
/*      */     }
/*      */     
/*      */     boolean prevOnLineEndsNotWithSpace() {
/* 1952 */       int layoutSize = this.layout.size();
/* 1953 */       if (this.lineStartIdx < layoutSize) {
/* 1954 */         TextArea.LElement le = this.layout.get(layoutSize - 1);
/* 1955 */         if (le instanceof TextArea.LText) {
/* 1956 */           TextArea.LText lt = (TextArea.LText)le;
/* 1957 */           return !TextArea.isSkip(lt.text.charAt(lt.end - 1));
/*      */         } 
/* 1959 */         return true;
/*      */       } 
/* 1961 */       return false;
/*      */     }
/*      */     
/*      */     void checkFloaters() {
/* 1965 */       removeObjFromList(this.objLeft);
/* 1966 */       removeObjFromList(this.objRight);
/* 1967 */       computePadding();
/*      */     }
/*      */ 
/*      */     
/*      */     void clearFloater(TextAreaModel.Clear clear) {
/* 1972 */       if (clear != TextAreaModel.Clear.NONE) {
/* 1973 */         int targetY = -1;
/* 1974 */         if (clear == TextAreaModel.Clear.LEFT || clear == TextAreaModel.Clear.BOTH) {
/* 1975 */           for (int i = 0, n = this.objLeft.size(); i < n; i++) {
/* 1976 */             TextArea.LElement le = this.objLeft.get(i);
/* 1977 */             if (le.height != 32767) {
/* 1978 */               targetY = Math.max(targetY, le.y + le.height);
/*      */             }
/*      */           } 
/*      */         }
/* 1982 */         if (clear == TextAreaModel.Clear.RIGHT || clear == TextAreaModel.Clear.BOTH) {
/* 1983 */           for (int i = 0, n = this.objRight.size(); i < n; i++) {
/* 1984 */             TextArea.LElement le = this.objRight.get(i);
/* 1985 */             targetY = Math.max(targetY, le.y + le.height);
/*      */           } 
/*      */         }
/* 1988 */         if (targetY >= 0) {
/* 1989 */           advanceToY(targetY);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*      */     void advanceToY(int targetY) {
/* 1995 */       nextLine(false);
/* 1996 */       if (targetY > this.curY) {
/* 1997 */         this.curY = targetY;
/* 1998 */         checkFloaters();
/*      */       } 
/*      */     }
/*      */     
/*      */     void advancePastFloaters(int requiredWidth, int marginLeft, int marginRight) {
/* 2003 */       if (computePaddingWidth(marginLeft, marginRight) < requiredWidth) {
/* 2004 */         nextLine(false);
/*      */         do {
/* 2006 */           int targetY = Integer.MAX_VALUE;
/* 2007 */           if (!this.objLeft.isEmpty()) {
/* 2008 */             TextArea.LElement le = this.objLeft.get(this.objLeft.size() - 1);
/* 2009 */             if (le.height != 32767) {
/* 2010 */               targetY = Math.min(targetY, le.bottom());
/*      */             }
/*      */           } 
/* 2013 */           if (!this.objRight.isEmpty()) {
/* 2014 */             TextArea.LElement le = this.objRight.get(this.objRight.size() - 1);
/* 2015 */             targetY = Math.min(targetY, le.bottom());
/*      */           } 
/* 2017 */           if (targetY == Integer.MAX_VALUE || targetY < this.curY) {
/*      */             return;
/*      */           }
/* 2020 */           this.curY = targetY;
/* 2021 */           checkFloaters();
/* 2022 */         } while (computePaddingWidth(marginLeft, marginRight) < requiredWidth);
/*      */       } 
/*      */     }
/*      */     
/*      */     boolean nextLine(boolean force) {
/* 2027 */       if (isAtStartOfLine() && (this.wasAutoBreak || !force)) {
/* 2028 */         return false;
/*      */       }
/*      */       
/* 2031 */       accountMinRemaining(getRemaining());
/*      */       
/* 2033 */       int targetY = this.curY;
/* 2034 */       int lineHeight = this.minLineHeight;
/*      */       
/* 2036 */       if (isAtStartOfLine()) {
/* 2037 */         lineHeight = Math.max(lineHeight, this.fontLineHeight);
/*      */       } else {
/* 2039 */         int j, offset, k; for (int idx = this.lineStartIdx; idx < this.layout.size(); idx++) {
/* 2040 */           TextArea.LElement le = this.layout.get(idx);
/* 2041 */           lineHeight = Math.max(lineHeight, le.height);
/*      */         } 
/*      */         
/* 2044 */         TextArea.LElement lastElement = this.layout.get(this.layout.size() - 1);
/* 2045 */         int remaining = this.lineStartX + this.lineWidth - lastElement.x + lastElement.width;
/*      */         
/* 2047 */         switch (this.textAlignment) {
/*      */           case MIDDLE:
/* 2049 */             for (j = this.lineStartIdx; j < this.layout.size(); j++) {
/* 2050 */               TextArea.LElement le = this.layout.get(j);
/* 2051 */               le.x += remaining;
/*      */             } 
/*      */             break;
/*      */           
/*      */           case BOTTOM:
/* 2056 */             offset = remaining / 2;
/* 2057 */             for (k = this.lineStartIdx; k < this.layout.size(); k++) {
/* 2058 */               TextArea.LElement le = this.layout.get(k);
/* 2059 */               le.x += offset;
/*      */             } 
/*      */             break;
/*      */           
/*      */           case FILL:
/* 2064 */             if (remaining < this.lineWidth / 4) {
/* 2065 */               int num = this.layout.size() - this.lineStartIdx;
/* 2066 */               for (int m = 1; m < num; m++) {
/* 2067 */                 TextArea.LElement le = this.layout.get(this.lineStartIdx + m);
/* 2068 */                 int n = remaining * m / (num - 1);
/* 2069 */                 le.x += n;
/*      */               } 
/*      */             } 
/*      */             break;
/*      */         } 
/*      */         int i;
/* 2075 */         for (i = this.lineStartIdx; i < this.layout.size(); i++) {
/* 2076 */           TextArea.LElement le = this.layout.get(i);
/* 2077 */           switch ((TextAreaModel.VAlignment)le.element.getStyle().get(StyleAttribute.VERTICAL_ALIGNMENT, TextArea.this.styleClassResolver)) {
/*      */             case BOTTOM:
/* 2079 */               le.y = lineHeight - le.height;
/*      */               break;
/*      */             case TOP:
/* 2082 */               le.y = 0;
/*      */               break;
/*      */             case MIDDLE:
/* 2085 */               le.y = (lineHeight - le.height) / 2;
/*      */               break;
/*      */             case FILL:
/* 2088 */               le.y = 0;
/* 2089 */               le.height = lineHeight;
/*      */               break;
/*      */           } 
/* 2092 */           targetY = Math.max(targetY, computeTopPadding(le.marginTop - le.y));
/* 2093 */           this.marginBottomNext = Math.max(this.marginBottomNext, le.bottom() - lineHeight);
/*      */         } 
/*      */         
/* 2096 */         for (i = this.lineStartIdx; i < this.layout.size(); i++) {
/* 2097 */           TextArea.LElement le = this.layout.get(i);
/* 2098 */           le.y += targetY;
/*      */         } 
/*      */       } 
/*      */       
/* 2102 */       processAnchors(targetY, lineHeight);
/*      */       
/* 2104 */       this.minLineHeight = 0;
/* 2105 */       this.lineStartIdx = this.layout.size();
/* 2106 */       this.wasAutoBreak = !force;
/* 2107 */       this.curY = targetY + lineHeight;
/* 2108 */       this.marginBottomAbs = Math.max(this.marginBottomAbs, this.curY + this.marginBottomNext);
/* 2109 */       this.marginBottomNext = 0;
/* 2110 */       this.marginTop = 0;
/* 2111 */       checkFloaters();
/*      */       
/* 2113 */       return true;
/*      */     }
/*      */     
/*      */     void finish() {
/* 2117 */       nextLine(false);
/* 2118 */       clearFloater(TextAreaModel.Clear.BOTH);
/* 2119 */       processAnchors(this.curY, 0);
/* 2120 */       int lineInfoLength = this.lineInfo.length();
/* 2121 */       this.clip.lineInfo = new char[lineInfoLength];
/* 2122 */       this.lineInfo.getChars(0, lineInfoLength, this.clip.lineInfo, 0);
/*      */     }
/*      */     
/*      */     int computeNextTabStop(Style style, Font font) {
/* 2126 */       int em = font.getEM();
/* 2127 */       int tabSize = ((Integer)style.get(StyleAttribute.TAB_SIZE, TextArea.this.styleClassResolver)).intValue();
/* 2128 */       if (tabSize <= 0 || em <= 0)
/*      */       {
/* 2130 */         return this.curX + font.getSpaceWidth();
/*      */       }
/* 2132 */       int tabSizePX = Math.min(tabSize, 32767 / em) * em;
/* 2133 */       int x = this.curX - this.lineStartX + font.getSpaceWidth();
/* 2134 */       return this.curX + tabSizePX - x % tabSizePX;
/*      */     }
/*      */     
/*      */     private void removeObjFromList(ArrayList<TextArea.LElement> list) {
/* 2138 */       for (int i = list.size(); i-- > 0; ) {
/* 2139 */         TextArea.LElement e = list.get(i);
/* 2140 */         if (e.bottom() <= this.curY)
/*      */         {
/* 2142 */           list.remove(i);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*      */     void setupTextParams(Style style, Font font, boolean isParagraphStart) {
/* 2148 */       if (font != null) {
/* 2149 */         this.fontLineHeight = font.getLineHeight();
/*      */       } else {
/* 2151 */         this.fontLineHeight = 0;
/*      */       } 
/*      */       
/* 2154 */       if (isParagraphStart) {
/* 2155 */         nextLine(false);
/* 2156 */         this.inParagraph = true;
/*      */       } 
/*      */       
/* 2159 */       if (isParagraphStart || (!this.inParagraph && isAtStartOfLine())) {
/* 2160 */         this.marginLeft = TextArea.this.convertToPX0(style, StyleAttribute.MARGIN_LEFT, this.boxWidth);
/* 2161 */         this.marginRight = TextArea.this.convertToPX0(style, StyleAttribute.MARGIN_RIGHT, this.boxWidth);
/* 2162 */         this.textAlignment = (TextAreaModel.HAlignment)style.get(StyleAttribute.HORIZONTAL_ALIGNMENT, TextArea.this.styleClassResolver);
/* 2163 */         computePadding();
/* 2164 */         this.curX = Math.max(0, this.lineStartX + TextArea.this.convertToPX(style, StyleAttribute.TEXT_INDENT, this.boxWidth, 0));
/*      */       } 
/*      */       
/* 2167 */       this.marginTop = TextArea.this.convertToPX0(style, StyleAttribute.MARGIN_TOP, this.boxWidth);
/*      */     }
/*      */     
/*      */     TextArea.LElement addAnchor(TextAreaModel.Element e) {
/* 2171 */       TextArea.LElement le = new TextArea.LElement(e);
/* 2172 */       le.y = this.curY;
/* 2173 */       le.x = this.boxLeft;
/* 2174 */       le.width = this.boxWidth;
/* 2175 */       this.clip.anchors.add(le);
/* 2176 */       return le;
/*      */     }
/*      */     
/*      */     private void processAnchors(int y, int height) {
/* 2180 */       while (this.lastProcessedAnchorIdx < this.clip.anchors.size()) {
/* 2181 */         TextArea.LElement le = this.clip.anchors.get(this.lastProcessedAnchorIdx++);
/* 2182 */         if (le.height == 0) {
/* 2183 */           le.y = y;
/* 2184 */           le.height = height;
/*      */         } 
/*      */       } 
/* 2187 */       if (this.lineStartIdx > this.lastLineEnd) {
/* 2188 */         this.lineInfo.append(false).append((char)(this.lineStartIdx - this.lastLineEnd));
/*      */       }
/* 2190 */       if (y > this.lastLineBottom) {
/* 2191 */         this.lineInfo.append((char)y).append(false);
/*      */       }
/* 2193 */       this.lastLineBottom = y + height;
/* 2194 */       this.lineInfo.append((char)this.lastLineBottom).append((char)(this.layout.size() - this.lineStartIdx));
/* 2195 */       this.lastLineEnd = this.layout.size();
/*      */     }
/*      */   }
/*      */   
/*      */   static class RenderInfo {
/*      */     int offsetX;
/*      */     int offsetY;
/*      */     Renderer renderer;
/*      */     final AnimationState asNormal;
/*      */     final AnimationState asHover;
/*      */     
/*      */     RenderInfo(AnimationState parent) {
/* 2207 */       this.asNormal = new AnimationState(parent);
/* 2208 */       this.asNormal.setAnimationState(TextArea.STATE_HOVER, false);
/* 2209 */       this.asHover = new AnimationState(parent);
/* 2210 */       this.asHover.setAnimationState(TextArea.STATE_HOVER, true);
/*      */     }
/*      */     
/*      */     AnimationState getAnimationState(boolean isHover) {
/* 2214 */       return isHover ? this.asHover : this.asNormal;
/*      */     }
/*      */   }
/*      */   
/*      */   static class LElement {
/*      */     final TextAreaModel.Element element;
/*      */     int x;
/*      */     int y;
/*      */     int width;
/*      */     int height;
/*      */     short marginTop;
/*      */     short marginLeft;
/*      */     short marginRight;
/*      */     short marginBottom;
/*      */     String href;
/*      */     boolean isHover;
/*      */     boolean inheritHover;
/*      */     
/*      */     LElement(TextAreaModel.Element element) {
/* 2233 */       this.element = element;
/*      */     }
/*      */     void adjustWidget(int offX, int offY) {}
/*      */     void collectBGImages(int offX, int offY, ArrayList<TextArea.LImage> allBGImages) {}
/*      */     void draw(TextArea.RenderInfo ri) {}
/*      */     
/*      */     void destroy() {}
/*      */     
/*      */     boolean isInside(int x, int y) {
/* 2242 */       return (x >= this.x && x < this.x + this.width && y >= this.y && y < this.y + this.height);
/*      */     }
/*      */     
/*      */     LElement find(int x, int y) {
/* 2246 */       return this;
/*      */     }
/*      */     LElement find(TextAreaModel.Element element, int[] offset) {
/* 2249 */       if (this.element == element) {
/* 2250 */         return this;
/*      */       }
/* 2252 */       return null;
/*      */     }
/*      */     boolean setHover(LElement le) {
/* 2255 */       this.isHover = (this == le || (le != null && this.element == le.element));
/* 2256 */       return this.isHover;
/*      */     }
/*      */     
/*      */     int bottom() {
/* 2260 */       return this.y + this.height + this.marginBottom;
/*      */     }
/*      */   }
/*      */   
/*      */   static class FontData {
/*      */     final Font font;
/*      */     final Color color;
/*      */     final Color colorHover;
/*      */     
/*      */     FontData(Font font, Color color, Color colorHover) {
/* 2270 */       if (colorHover == null) {
/* 2271 */         colorHover = color;
/*      */       }
/* 2273 */       this.font = font;
/* 2274 */       this.color = maskWhite(color);
/* 2275 */       this.colorHover = maskWhite(colorHover);
/*      */     }
/*      */     
/*      */     public Color getColor(boolean isHover) {
/* 2279 */       return isHover ? this.colorHover : this.color;
/*      */     }
/*      */     
/*      */     private static Color maskWhite(Color c) {
/* 2283 */       return Color.WHITE.equals(c) ? null : c;
/*      */     }
/*      */   }
/*      */   
/*      */   static class LText extends LElement {
/*      */     final TextArea.FontData fontData;
/*      */     final String text;
/*      */     final int start;
/*      */     final int end;
/*      */     FontCache cache;
/*      */     
/*      */     LText(TextAreaModel.Element element, TextArea.FontData fontData, String text, int start, int end, boolean doCache) {
/* 2295 */       super(element);
/* 2296 */       Font font = fontData.font;
/* 2297 */       this.fontData = fontData;
/* 2298 */       this.text = text;
/* 2299 */       this.start = start;
/* 2300 */       this.end = end;
/* 2301 */       this.cache = doCache ? font.cacheText(null, text, start, end) : null;
/* 2302 */       this.height = font.getLineHeight();
/*      */       
/* 2304 */       if (this.cache != null) {
/* 2305 */         this.width = this.cache.getWidth();
/*      */       } else {
/* 2307 */         this.width = font.computeTextWidth(text, start, end);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     void draw(TextArea.RenderInfo ri) {
/* 2313 */       Color c = this.fontData.getColor(this.isHover);
/* 2314 */       if (c != null) {
/* 2315 */         drawTextWithColor(ri, c);
/*      */       } else {
/* 2317 */         drawText(ri);
/*      */       } 
/*      */     }
/*      */     
/*      */     private void drawTextWithColor(TextArea.RenderInfo ri, Color c) {
/* 2322 */       ri.renderer.pushGlobalTintColor(c.getRedFloat(), c.getGreenFloat(), c.getBlueFloat(), c.getAlphaFloat());
/* 2323 */       drawText(ri);
/* 2324 */       ri.renderer.popGlobalTintColor();
/*      */     }
/*      */     
/*      */     private void drawText(TextArea.RenderInfo ri) {
/* 2328 */       AnimationState as = ri.getAnimationState(this.isHover);
/* 2329 */       if (this.cache != null) {
/* 2330 */         this.cache.draw(as, this.x + ri.offsetX, this.y + ri.offsetY);
/*      */       } else {
/* 2332 */         this.fontData.font.drawText(as, this.x + ri.offsetX, this.y + ri.offsetY, this.text, this.start, this.end);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     void destroy() {
/* 2338 */       if (this.cache != null) {
/* 2339 */         this.cache.destroy();
/* 2340 */         this.cache = null;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   static class LWidget extends LElement {
/*      */     final Widget widget;
/*      */     
/*      */     LWidget(TextAreaModel.Element element, Widget widget) {
/* 2349 */       super(element);
/* 2350 */       this.widget = widget;
/*      */     }
/*      */ 
/*      */     
/*      */     void adjustWidget(int offX, int offY) {
/* 2355 */       this.widget.setPosition(this.x + offX, this.y + offY);
/* 2356 */       this.widget.setSize(this.width, this.height);
/*      */     }
/*      */   }
/*      */   
/*      */   static class LImage
/*      */     extends LElement {
/*      */     final Image img;
/*      */     TextArea.LElement hoverSrc;
/*      */     
/*      */     LImage(TextAreaModel.Element element, Image img) {
/* 2366 */       super(element);
/* 2367 */       this.img = img;
/* 2368 */       this.width = img.getWidth();
/* 2369 */       this.height = img.getHeight();
/* 2370 */       this.hoverSrc = this;
/*      */     }
/*      */ 
/*      */     
/*      */     void draw(TextArea.RenderInfo ri) {
/* 2375 */       this.img.draw(ri.getAnimationState(this.hoverSrc.isHover), this.x + ri.offsetX, this.y + ri.offsetY, this.width, this.height);
/*      */     }
/*      */   }
/*      */   
/*      */   static class LClip
/*      */     extends LElement {
/*      */     final ArrayList<TextArea.LElement> layout;
/*      */     final ArrayList<TextArea.LImage> bgImages;
/*      */     final ArrayList<TextArea.LElement> anchors;
/*      */     char[] lineInfo;
/*      */     
/*      */     LClip(TextAreaModel.Element element) {
/* 2387 */       super(element);
/* 2388 */       this.layout = new ArrayList<TextArea.LElement>();
/* 2389 */       this.bgImages = new ArrayList<TextArea.LImage>();
/* 2390 */       this.anchors = new ArrayList<TextArea.LElement>();
/* 2391 */       this.lineInfo = TextArea.EMPTY_CHAR_ARRAY;
/*      */     }
/*      */ 
/*      */     
/*      */     void draw(TextArea.RenderInfo ri) {
/* 2396 */       ri.offsetX += this.x;
/* 2397 */       ri.offsetY += this.y;
/* 2398 */       ri.renderer.clipEnter(ri.offsetX, ri.offsetY, this.width, this.height);
/*      */       try {
/* 2400 */         if (!ri.renderer.clipIsEmpty()) {
/* 2401 */           ArrayList<TextArea.LElement> ll = this.layout;
/* 2402 */           for (int i = 0, n = ll.size(); i < n; i++) {
/* 2403 */             ((TextArea.LElement)ll.get(i)).draw(ri);
/*      */           }
/*      */         } 
/*      */       } finally {
/* 2407 */         ri.renderer.clipLeave();
/* 2408 */         ri.offsetX -= this.x;
/* 2409 */         ri.offsetY -= this.y;
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     void adjustWidget(int offX, int offY) {
/* 2415 */       offX += this.x;
/* 2416 */       offY += this.y;
/* 2417 */       for (int i = 0, n = this.layout.size(); i < n; i++) {
/* 2418 */         ((TextArea.LElement)this.layout.get(i)).adjustWidget(offX, offY);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     void collectBGImages(int offX, int offY, ArrayList<TextArea.LImage> allBGImages) {
/* 2424 */       offX += this.x;
/* 2425 */       offY += this.y; int i, n;
/* 2426 */       for (i = 0, n = this.bgImages.size(); i < n; i++) {
/* 2427 */         TextArea.LImage img = this.bgImages.get(i);
/* 2428 */         img.x += offX;
/* 2429 */         img.y += offY;
/* 2430 */         allBGImages.add(img);
/*      */       } 
/* 2432 */       for (i = 0, n = this.layout.size(); i < n; i++) {
/* 2433 */         ((TextArea.LElement)this.layout.get(i)).collectBGImages(offX, offY, allBGImages);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     void destroy() {
/* 2439 */       for (int i = 0, n = this.layout.size(); i < n; i++) {
/* 2440 */         ((TextArea.LElement)this.layout.get(i)).destroy();
/*      */       }
/* 2442 */       this.layout.clear();
/* 2443 */       this.bgImages.clear();
/* 2444 */       this.lineInfo = TextArea.EMPTY_CHAR_ARRAY;
/*      */     }
/*      */ 
/*      */     
/*      */     TextArea.LElement find(int x, int y) {
/* 2449 */       x -= this.x;
/* 2450 */       y -= this.y;
/* 2451 */       int lineTop = 0;
/* 2452 */       int layoutIdx = 0;
/* 2453 */       for (int lineIdx = 0; lineIdx < this.lineInfo.length && y >= lineTop; ) {
/* 2454 */         int lineBottom = this.lineInfo[lineIdx++];
/* 2455 */         int layoutCount = this.lineInfo[lineIdx++];
/* 2456 */         if (layoutCount > 0) {
/* 2457 */           if (lineBottom == 0 || y < lineBottom) {
/* 2458 */             for (int i = 0; i < layoutCount; i++) {
/* 2459 */               TextArea.LElement le = this.layout.get(layoutIdx + i);
/* 2460 */               if (le.isInside(x, y)) {
/* 2461 */                 return le.find(x, y);
/*      */               }
/*      */             } 
/* 2464 */             if (lineBottom > 0 && x >= ((TextArea.LElement)this.layout.get(layoutIdx)).x) {
/* 2465 */               TextArea.LElement prev = null;
/* 2466 */               for (int j = 0; j < layoutCount; j++) {
/* 2467 */                 TextArea.LElement le = this.layout.get(layoutIdx + j);
/* 2468 */                 if (le.x >= x && (prev == null || prev.element == le.element)) {
/* 2469 */                   return le;
/*      */                 }
/* 2471 */                 prev = le;
/*      */               } 
/*      */             } 
/*      */           } 
/* 2475 */           layoutIdx += layoutCount;
/*      */         } 
/* 2477 */         if (lineBottom > 0) {
/* 2478 */           lineTop = lineBottom;
/*      */         }
/*      */       } 
/* 2481 */       return this;
/*      */     }
/*      */ 
/*      */     
/*      */     TextArea.LElement find(TextAreaModel.Element element, int[] offset) {
/* 2486 */       if (this.element == element) {
/* 2487 */         return this;
/*      */       }
/* 2489 */       TextArea.LElement match = find(this.layout, element, offset);
/* 2490 */       if (match == null) {
/* 2491 */         match = find(this.anchors, element, offset);
/*      */       }
/* 2493 */       return match;
/*      */     }
/*      */     
/*      */     private TextArea.LElement find(ArrayList<TextArea.LElement> l, TextAreaModel.Element e, int[] offset) {
/* 2497 */       for (int i = 0, n = l.size(); i < n; i++) {
/* 2498 */         TextArea.LElement match = ((TextArea.LElement)l.get(i)).find(e, offset);
/* 2499 */         if (match != null) {
/* 2500 */           if (offset != null) {
/* 2501 */             offset[0] = offset[0] + this.x;
/* 2502 */             offset[1] = offset[1] + this.y;
/*      */           } 
/* 2504 */           return match;
/*      */         } 
/*      */       } 
/* 2507 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     boolean setHover(TextArea.LElement le) {
/* 2512 */       boolean childHover = false; int i, n;
/* 2513 */       for (i = 0, n = this.layout.size(); i < n; i++) {
/* 2514 */         childHover |= ((TextArea.LElement)this.layout.get(i)).setHover(le);
/*      */       }
/* 2516 */       if (childHover) {
/* 2517 */         this.isHover = true;
/*      */       } else {
/* 2519 */         super.setHover(le);
/*      */       } 
/* 2521 */       for (i = 0, n = this.layout.size(); i < n; i++) {
/* 2522 */         TextArea.LElement child = this.layout.get(i);
/* 2523 */         if (child.inheritHover) {
/* 2524 */           child.isHover = this.isHover;
/*      */         }
/*      */       } 
/* 2527 */       return this.isHover;
/*      */     }
/*      */     
/*      */     void moveContentY(int amount) {
/* 2531 */       for (int i = 0, n = this.layout.size(); i < n; i++) {
/* 2532 */         ((TextArea.LElement)this.layout.get(i)).y += amount;
/*      */       }
/* 2534 */       if (this.lineInfo.length > 0)
/* 2535 */         if (this.lineInfo[1] == '\000') {
/* 2536 */           this.lineInfo[0] = (char)(this.lineInfo[0] + amount);
/*      */         } else {
/* 2538 */           int j = this.lineInfo.length;
/* 2539 */           char[] tmpLineInfo = new char[j + 2];
/* 2540 */           tmpLineInfo[0] = (char)amount;
/* 2541 */           for (int k = 0; k < j; k += 2) {
/* 2542 */             int lineBottom = this.lineInfo[k];
/* 2543 */             if (lineBottom > 0) {
/* 2544 */               lineBottom += amount;
/*      */             }
/* 2546 */             tmpLineInfo[k + 2] = (char)lineBottom;
/* 2547 */             tmpLineInfo[k + 3] = this.lineInfo[k + 1];
/*      */           } 
/* 2549 */           this.lineInfo = tmpLineInfo;
/*      */         }  
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface Callback2 extends Callback {
/*      */     void handleMouseButton(Event param1Event, TextAreaModel.Element param1Element);
/*      */   }
/*      */   
/*      */   public static interface Callback {
/*      */     void handleLinkClicked(String param1String);
/*      */   }
/*      */   
/*      */   public static interface ImageResolver {
/*      */     Image resolveImage(String param1String);
/*      */   }
/*      */   
/*      */   public static interface WidgetResolver {
/*      */     Widget resolveWidget(String param1String1, String param1String2);
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\TextArea.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */