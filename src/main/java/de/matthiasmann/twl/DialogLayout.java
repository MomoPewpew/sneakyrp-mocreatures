/*      */ package de.matthiasmann.twl;
/*      */ 
/*      */ import de.matthiasmann.twl.renderer.AnimationState;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
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
/*      */ public class DialogLayout
/*      */   extends Widget
/*      */ {
/*      */   public static final int SMALL_GAP = -1;
/*      */   public static final int MEDIUM_GAP = -2;
/*      */   public static final int LARGE_GAP = -3;
/*      */   public static final int DEFAULT_GAP = -4;
/*  111 */   private static final boolean DEBUG_LAYOUT_GROUPS = Widget.getSafeBooleanProperty("debugLayoutGroups");
/*      */   
/*      */   protected Dimension smallGap;
/*      */   
/*      */   protected Dimension mediumGap;
/*      */   
/*      */   protected Dimension largeGap;
/*      */   
/*      */   protected Dimension defaultGap;
/*      */   
/*      */   protected ParameterMap namedGaps;
/*      */   
/*      */   protected boolean addDefaultGaps = true;
/*      */   
/*      */   protected boolean includeInvisibleWidgets = true;
/*      */   
/*      */   protected boolean redoDefaultGaps;
/*      */   
/*      */   protected boolean isPrepared;
/*      */   
/*      */   protected boolean blockInvalidateLayoutTree;
/*      */   
/*      */   protected boolean warnOnIncomplete;
/*      */   
/*      */   private Group horz;
/*      */   
/*      */   private Group vert;
/*      */   
/*      */   Throwable debugStackTrace;
/*      */   final HashMap<Widget, WidgetSpring> widgetSprings;
/*      */   static final int AXIS_X = 0;
/*      */   static final int AXIS_Y = 1;
/*      */   
/*      */   public DialogLayout() {
/*  145 */     this.widgetSprings = new HashMap<Widget, WidgetSpring>();
/*  146 */     collectDebugStack();
/*      */   }
/*      */   
/*      */   public Group getHorizontalGroup() {
/*  150 */     return this.horz;
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
/*      */   public void setHorizontalGroup(Group g) {
/*  172 */     if (g != null) {
/*  173 */       g.checkGroup(this);
/*      */     }
/*  175 */     this.horz = g;
/*  176 */     collectDebugStack();
/*  177 */     layoutGroupsChanged();
/*      */   }
/*      */   
/*      */   public Group getVerticalGroup() {
/*  181 */     return this.vert;
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
/*      */   public void setVerticalGroup(Group g) {
/*  195 */     if (g != null) {
/*  196 */       g.checkGroup(this);
/*      */     }
/*  198 */     this.vert = g;
/*  199 */     collectDebugStack();
/*  200 */     layoutGroupsChanged();
/*      */   }
/*      */   
/*      */   public Dimension getSmallGap() {
/*  204 */     return this.smallGap;
/*      */   }
/*      */   
/*      */   public void setSmallGap(Dimension smallGap) {
/*  208 */     this.smallGap = smallGap;
/*  209 */     maybeInvalidateLayoutTree();
/*      */   }
/*      */   
/*      */   public Dimension getMediumGap() {
/*  213 */     return this.mediumGap;
/*      */   }
/*      */   
/*      */   public void setMediumGap(Dimension mediumGap) {
/*  217 */     this.mediumGap = mediumGap;
/*  218 */     maybeInvalidateLayoutTree();
/*      */   }
/*      */   
/*      */   public Dimension getLargeGap() {
/*  222 */     return this.largeGap;
/*      */   }
/*      */   
/*      */   public void setLargeGap(Dimension largeGap) {
/*  226 */     this.largeGap = largeGap;
/*  227 */     maybeInvalidateLayoutTree();
/*      */   }
/*      */   
/*      */   public Dimension getDefaultGap() {
/*  231 */     return this.defaultGap;
/*      */   }
/*      */   
/*      */   public void setDefaultGap(Dimension defaultGap) {
/*  235 */     this.defaultGap = defaultGap;
/*  236 */     maybeInvalidateLayoutTree();
/*      */   }
/*      */   
/*      */   public boolean isAddDefaultGaps() {
/*  240 */     return this.addDefaultGaps;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAddDefaultGaps(boolean addDefaultGaps) {
/*  249 */     this.addDefaultGaps = addDefaultGaps;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeDefaultGaps() {
/*  256 */     if (this.horz != null && this.vert != null) {
/*  257 */       this.horz.removeDefaultGaps();
/*  258 */       this.vert.removeDefaultGaps();
/*  259 */       maybeInvalidateLayoutTree();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addDefaultGaps() {
/*  267 */     if (this.horz != null && this.vert != null) {
/*  268 */       this.horz.addDefaultGap();
/*  269 */       this.vert.addDefaultGap();
/*  270 */       maybeInvalidateLayoutTree();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isIncludeInvisibleWidgets() {
/*  275 */     return this.includeInvisibleWidgets;
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
/*      */   public void setIncludeInvisibleWidgets(boolean includeInvisibleWidgets) {
/*  289 */     if (this.includeInvisibleWidgets != includeInvisibleWidgets) {
/*  290 */       this.includeInvisibleWidgets = includeInvisibleWidgets;
/*  291 */       layoutGroupsChanged();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void collectDebugStack() {
/*  296 */     this.warnOnIncomplete = true;
/*  297 */     if (DEBUG_LAYOUT_GROUPS) {
/*  298 */       this.debugStackTrace = (new Throwable("DialogLayout created/used here")).fillInStackTrace();
/*      */     }
/*      */   }
/*      */   
/*      */   private void warnOnIncomplete() {
/*  303 */     this.warnOnIncomplete = false;
/*  304 */     getLogger().log(Level.WARNING, "Dialog layout has incomplete state", this.debugStackTrace);
/*      */   }
/*      */   
/*      */   static Logger getLogger() {
/*  308 */     return Logger.getLogger(DialogLayout.class.getName());
/*      */   }
/*      */   
/*      */   protected void applyThemeDialogLayout(ThemeInfo themeInfo) {
/*      */     try {
/*  313 */       this.blockInvalidateLayoutTree = true;
/*  314 */       setSmallGap(themeInfo.<Dimension>getParameterValue("smallGap", true, Dimension.class, Dimension.ZERO));
/*  315 */       setMediumGap(themeInfo.<Dimension>getParameterValue("mediumGap", true, Dimension.class, Dimension.ZERO));
/*  316 */       setLargeGap(themeInfo.<Dimension>getParameterValue("largeGap", true, Dimension.class, Dimension.ZERO));
/*  317 */       setDefaultGap(themeInfo.<Dimension>getParameterValue("defaultGap", true, Dimension.class, Dimension.ZERO));
/*  318 */       this.namedGaps = themeInfo.getParameterMap("namedGaps");
/*      */     } finally {
/*  320 */       this.blockInvalidateLayoutTree = false;
/*      */     } 
/*  322 */     invalidateLayout();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyTheme(ThemeInfo themeInfo) {
/*  327 */     super.applyTheme(themeInfo);
/*  328 */     applyThemeDialogLayout(themeInfo);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMinWidth() {
/*  333 */     if (this.horz != null) {
/*  334 */       prepare();
/*  335 */       return this.horz.getMinSize(0) + getBorderHorizontal();
/*      */     } 
/*  337 */     return super.getMinWidth();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMinHeight() {
/*  342 */     if (this.vert != null) {
/*  343 */       prepare();
/*  344 */       return this.vert.getMinSize(1) + getBorderVertical();
/*      */     } 
/*  346 */     return super.getMinHeight();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPreferredInnerWidth() {
/*  351 */     if (this.horz != null) {
/*  352 */       prepare();
/*  353 */       return this.horz.getPrefSize(0);
/*      */     } 
/*  355 */     return super.getPreferredInnerWidth();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPreferredInnerHeight() {
/*  360 */     if (this.vert != null) {
/*  361 */       prepare();
/*  362 */       return this.vert.getPrefSize(1);
/*      */     } 
/*  364 */     return super.getPreferredInnerHeight();
/*      */   }
/*      */ 
/*      */   
/*      */   public void adjustSize() {
/*  369 */     if (this.horz != null && this.vert != null) {
/*  370 */       prepare();
/*  371 */       int minWidth = this.horz.getMinSize(0);
/*  372 */       int minHeight = this.vert.getMinSize(1);
/*  373 */       int prefWidth = this.horz.getPrefSize(0);
/*  374 */       int prefHeight = this.vert.getPrefSize(1);
/*  375 */       int maxWidth = getMaxWidth();
/*  376 */       int maxHeight = getMaxHeight();
/*  377 */       setInnerSize(computeSize(minWidth, prefWidth, maxWidth), computeSize(minHeight, prefHeight, maxHeight));
/*      */ 
/*      */       
/*  380 */       doLayout();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void layout() {
/*  386 */     if (this.horz != null && this.vert != null) {
/*  387 */       prepare();
/*  388 */       doLayout();
/*  389 */     } else if (this.warnOnIncomplete) {
/*  390 */       warnOnIncomplete();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void prepare() {
/*  395 */     if (this.redoDefaultGaps) {
/*  396 */       if (this.addDefaultGaps) {
/*      */         try {
/*  398 */           this.blockInvalidateLayoutTree = true;
/*  399 */           removeDefaultGaps();
/*  400 */           addDefaultGaps();
/*      */         } finally {
/*  402 */           this.blockInvalidateLayoutTree = false;
/*      */         } 
/*      */       }
/*  405 */       this.redoDefaultGaps = false;
/*  406 */       this.isPrepared = false;
/*      */     } 
/*  408 */     if (!this.isPrepared) {
/*  409 */       for (WidgetSpring s : this.widgetSprings.values()) {
/*  410 */         if (this.includeInvisibleWidgets || s.w.isVisible()) {
/*  411 */           s.prepare();
/*      */         }
/*      */       } 
/*  414 */       this.isPrepared = true;
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void doLayout() {
/*  419 */     this.horz.setSize(0, getInnerX(), getInnerWidth());
/*  420 */     this.vert.setSize(1, getInnerY(), getInnerHeight());
/*      */     try {
/*  422 */       for (WidgetSpring s : this.widgetSprings.values()) {
/*  423 */         if (this.includeInvisibleWidgets || s.w.isVisible()) {
/*  424 */           s.apply();
/*      */         }
/*      */       } 
/*  427 */     } catch (IllegalStateException ex) {
/*  428 */       if (this.debugStackTrace != null && ex.getCause() == null) {
/*  429 */         ex.initCause(this.debugStackTrace);
/*      */       }
/*  431 */       throw ex;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void invalidateLayout() {
/*  437 */     this.isPrepared = false;
/*  438 */     super.invalidateLayout();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void paintWidget(GUI gui) {
/*  443 */     this.isPrepared = false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void sizeChanged() {
/*  449 */     this.isPrepared = false;
/*  450 */     super.sizeChanged();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void afterAddToGUI(GUI gui) {
/*  455 */     this.isPrepared = false;
/*  456 */     super.afterAddToGUI(gui);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Group createParallelGroup() {
/*  466 */     return new ParallelGroup();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Group createParallelGroup(Widget... widgets) {
/*  477 */     return createParallelGroup().addWidgets(widgets);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Group createParallelGroup(Group... groups) {
/*  488 */     return createParallelGroup().addGroups(groups);
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
/*      */   public Group createSequentialGroup() {
/*  501 */     return new SequentialGroup();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Group createSequentialGroup(Widget... widgets) {
/*  512 */     return createSequentialGroup().addWidgets(widgets);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Group createSequentialGroup(Group... groups) {
/*  523 */     return createSequentialGroup().addGroups(groups);
/*      */   }
/*      */ 
/*      */   
/*      */   public void insertChild(Widget child, int index) throws IndexOutOfBoundsException {
/*  528 */     super.insertChild(child, index);
/*  529 */     this.widgetSprings.put(child, new WidgetSpring(child));
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeAllChildren() {
/*  534 */     super.removeAllChildren();
/*  535 */     this.widgetSprings.clear();
/*  536 */     recheckWidgets();
/*  537 */     layoutGroupsChanged();
/*      */   }
/*      */ 
/*      */   
/*      */   public Widget removeChild(int index) throws IndexOutOfBoundsException {
/*  542 */     Widget widget = super.removeChild(index);
/*  543 */     this.widgetSprings.remove(widget);
/*  544 */     recheckWidgets();
/*  545 */     layoutGroupsChanged();
/*  546 */     return widget;
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
/*      */   public boolean setWidgetAlignment(Widget widget, Alignment alignment) {
/*  560 */     if (widget == null) {
/*  561 */       throw new NullPointerException("widget");
/*      */     }
/*  563 */     if (alignment == null) {
/*  564 */       throw new NullPointerException("alignment");
/*      */     }
/*  566 */     WidgetSpring ws = this.widgetSprings.get(widget);
/*  567 */     if (ws != null) {
/*  568 */       assert widget.getParent() == this;
/*  569 */       ws.alignment = alignment;
/*  570 */       return true;
/*      */     } 
/*  572 */     return false;
/*      */   }
/*      */   
/*      */   protected void recheckWidgets() {
/*  576 */     if (this.horz != null) {
/*  577 */       this.horz.recheckWidgets();
/*      */     }
/*  579 */     if (this.vert != null) {
/*  580 */       this.vert.recheckWidgets();
/*      */     }
/*      */   }
/*      */   
/*      */   protected void layoutGroupsChanged() {
/*  585 */     this.redoDefaultGaps = true;
/*  586 */     maybeInvalidateLayoutTree();
/*      */   }
/*      */   
/*      */   protected void maybeInvalidateLayoutTree() {
/*  590 */     if (this.horz != null && this.vert != null && !this.blockInvalidateLayoutTree) {
/*  591 */       invalidateLayout();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void childVisibilityChanged(Widget child) {
/*  597 */     if (!this.includeInvisibleWidgets) {
/*  598 */       layoutGroupsChanged();
/*      */     }
/*      */   }
/*      */   
/*      */   void removeChild(WidgetSpring widgetSpring) {
/*  603 */     Widget widget = widgetSpring.w;
/*  604 */     int idx = getChildIndex(widget);
/*  605 */     assert idx >= 0;
/*  606 */     super.removeChild(idx);
/*  607 */     this.widgetSprings.remove(widget);
/*      */   }
/*      */   
/*      */   public static class Gap {
/*      */     public final int min;
/*      */     public final int preferred;
/*      */     public final int max;
/*      */     
/*      */     public Gap() {
/*  616 */       this(0, 0, 32767);
/*      */     }
/*      */     public Gap(int size) {
/*  619 */       this(size, size, size);
/*      */     }
/*      */     public Gap(int min, int preferred) {
/*  622 */       this(min, preferred, 32767);
/*      */     }
/*      */     public Gap(int min, int preferred, int max) {
/*  625 */       if (min < 0) {
/*  626 */         throw new IllegalArgumentException("min");
/*      */       }
/*  628 */       if (preferred < min) {
/*  629 */         throw new IllegalArgumentException("preferred");
/*      */       }
/*  631 */       if (max < 0 || (max > 0 && max < preferred)) {
/*  632 */         throw new IllegalArgumentException("max");
/*      */       }
/*  634 */       this.min = min;
/*  635 */       this.preferred = preferred;
/*  636 */       this.max = max;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static abstract class Spring
/*      */   {
/*      */     abstract int getMinSize(int param1Int);
/*      */ 
/*      */     
/*      */     abstract int getPrefSize(int param1Int);
/*      */     
/*      */     abstract int getMaxSize(int param1Int);
/*      */     
/*      */     abstract void setSize(int param1Int1, int param1Int2, int param1Int3);
/*      */     
/*      */     void collectAllSprings(HashSet<Spring> result) {
/*  653 */       result.add(this);
/*      */     }
/*      */     
/*      */     boolean isVisible() {
/*  657 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   private static class WidgetSpring extends Spring {
/*      */     final Widget w;
/*      */     Alignment alignment;
/*      */     int x;
/*      */     int y;
/*      */     int width;
/*      */     int height;
/*      */     int minWidth;
/*      */     int minHeight;
/*      */     int maxWidth;
/*      */     int maxHeight;
/*      */     int prefWidth;
/*      */     int prefHeight;
/*      */     int flags;
/*      */     
/*      */     WidgetSpring(Widget w) {
/*  677 */       this.w = w;
/*  678 */       this.alignment = Alignment.FILL;
/*      */     }
/*      */     
/*      */     void prepare() {
/*  682 */       this.x = this.w.getX();
/*  683 */       this.y = this.w.getY();
/*  684 */       this.width = this.w.getWidth();
/*  685 */       this.height = this.w.getHeight();
/*  686 */       this.minWidth = this.w.getMinWidth();
/*  687 */       this.minHeight = this.w.getMinHeight();
/*  688 */       this.maxWidth = this.w.getMaxWidth();
/*  689 */       this.maxHeight = this.w.getMaxHeight();
/*  690 */       this.prefWidth = Widget.computeSize(this.minWidth, this.w.getPreferredWidth(), this.maxWidth);
/*  691 */       this.prefHeight = Widget.computeSize(this.minHeight, this.w.getPreferredHeight(), this.maxHeight);
/*  692 */       this.flags = 0;
/*      */     }
/*      */ 
/*      */     
/*      */     int getMinSize(int axis) {
/*  697 */       switch (axis) { case 0:
/*  698 */           return this.minWidth;
/*  699 */         case 1: return this.minHeight; }
/*  700 */        throw new IllegalArgumentException("axis");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     int getPrefSize(int axis) {
/*  706 */       switch (axis) { case 0:
/*  707 */           return this.prefWidth;
/*  708 */         case 1: return this.prefHeight; }
/*  709 */        throw new IllegalArgumentException("axis");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     int getMaxSize(int axis) {
/*  715 */       switch (axis) { case 0:
/*  716 */           return this.maxWidth;
/*  717 */         case 1: return this.maxHeight; }
/*  718 */        throw new IllegalArgumentException("axis");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     void setSize(int axis, int pos, int size) {
/*  724 */       this.flags |= 1 << axis;
/*  725 */       switch (axis) {
/*      */         case 0:
/*  727 */           this.x = pos;
/*  728 */           this.width = size;
/*      */           return;
/*      */         case 1:
/*  731 */           this.y = pos;
/*  732 */           this.height = size;
/*      */           return;
/*      */       } 
/*  735 */       throw new IllegalArgumentException("axis");
/*      */     }
/*      */ 
/*      */     
/*      */     void apply() {
/*  740 */       if (this.flags != 3) {
/*  741 */         invalidState();
/*      */       }
/*  743 */       if (this.alignment != Alignment.FILL) {
/*  744 */         int newWidth = Math.min(this.width, this.prefWidth);
/*  745 */         int newHeight = Math.min(this.height, this.prefHeight);
/*  746 */         this.w.setPosition(this.x + this.alignment.computePositionX(this.width, newWidth), this.y + this.alignment.computePositionY(this.height, newHeight));
/*      */ 
/*      */         
/*  749 */         this.w.setSize(newWidth, newHeight);
/*      */       } else {
/*  751 */         this.w.setPosition(this.x, this.y);
/*  752 */         this.w.setSize(this.width, this.height);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     boolean isVisible() {
/*  758 */       return this.w.isVisible();
/*      */     }
/*      */ 
/*      */     
/*      */     void invalidState() {
/*  763 */       StringBuilder sb = new StringBuilder();
/*  764 */       sb.append("Widget ").append(this.w).append(" with theme ").append(this.w.getTheme()).append(" is not part of the following groups:");
/*      */ 
/*      */       
/*  767 */       if ((this.flags & 0x1) == 0) {
/*  768 */         sb.append(" horizontal");
/*      */       }
/*  770 */       if ((this.flags & 0x2) == 0) {
/*  771 */         sb.append(" vertical");
/*      */       }
/*  773 */       throw new IllegalStateException(sb.toString());
/*      */     }
/*      */   }
/*      */   
/*      */   private class GapSpring extends Spring {
/*      */     final int min;
/*      */     final int pref;
/*      */     final int max;
/*      */     final boolean isDefault;
/*      */     
/*      */     GapSpring(int min, int pref, int max, boolean isDefault) {
/*  784 */       convertConstant(0, min);
/*  785 */       convertConstant(0, pref);
/*  786 */       convertConstant(0, max);
/*  787 */       this.min = min;
/*  788 */       this.pref = pref;
/*  789 */       this.max = max;
/*  790 */       this.isDefault = isDefault;
/*      */     }
/*      */ 
/*      */     
/*      */     int getMinSize(int axis) {
/*  795 */       return convertConstant(axis, this.min);
/*      */     }
/*      */ 
/*      */     
/*      */     int getPrefSize(int axis) {
/*  800 */       return convertConstant(axis, this.pref);
/*      */     }
/*      */ 
/*      */     
/*      */     int getMaxSize(int axis) {
/*  805 */       return convertConstant(axis, this.max);
/*      */     }
/*      */ 
/*      */     
/*      */     void setSize(int axis, int pos, int size) {}
/*      */     
/*      */     private int convertConstant(int axis, int value) {
/*      */       Dimension dim;
/*  813 */       if (value >= 0) {
/*  814 */         return value;
/*      */       }
/*      */       
/*  817 */       switch (value) {
/*      */         case -1:
/*  819 */           dim = DialogLayout.this.smallGap;
/*      */           break;
/*      */         case -2:
/*  822 */           dim = DialogLayout.this.mediumGap;
/*      */           break;
/*      */         case -3:
/*  825 */           dim = DialogLayout.this.largeGap;
/*      */           break;
/*      */         case -4:
/*  828 */           dim = DialogLayout.this.defaultGap;
/*      */           break;
/*      */         default:
/*  831 */           throw new IllegalArgumentException("Invalid gap size: " + value);
/*      */       } 
/*  833 */       if (dim == null)
/*  834 */         return 0; 
/*  835 */       if (axis == 0) {
/*  836 */         return dim.getX();
/*      */       }
/*  838 */       return dim.getY();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*  843 */   static final Gap NO_GAP = new Gap(0, 0, 32767);
/*      */   
/*      */   private class NamedGapSpring extends Spring {
/*      */     final String name;
/*      */     
/*      */     public NamedGapSpring(String name) {
/*  849 */       this.name = name;
/*      */     }
/*      */ 
/*      */     
/*      */     int getMaxSize(int axis) {
/*  854 */       return (getGap()).max;
/*      */     }
/*      */ 
/*      */     
/*      */     int getMinSize(int axis) {
/*  859 */       return (getGap()).min;
/*      */     }
/*      */ 
/*      */     
/*      */     int getPrefSize(int axis) {
/*  864 */       return (getGap()).preferred;
/*      */     }
/*      */ 
/*      */     
/*      */     void setSize(int axis, int pos, int size) {}
/*      */ 
/*      */     
/*      */     private DialogLayout.Gap getGap() {
/*  872 */       if (DialogLayout.this.namedGaps != null) {
/*  873 */         return DialogLayout.this.namedGaps.<DialogLayout.Gap>getParameterValue(this.name, true, DialogLayout.Gap.class, DialogLayout.NO_GAP);
/*      */       }
/*  875 */       return DialogLayout.NO_GAP;
/*      */     }
/*      */   }
/*      */   
/*      */   public abstract class Group extends Spring {
/*  880 */     final ArrayList<DialogLayout.Spring> springs = new ArrayList<DialogLayout.Spring>();
/*      */     boolean alreadyAdded;
/*      */     
/*      */     void checkGroup(DialogLayout owner) {
/*  884 */       if (DialogLayout.this != owner) {
/*  885 */         throw new IllegalArgumentException("Can't add group from different layout");
/*      */       }
/*  887 */       if (this.alreadyAdded) {
/*  888 */         throw new IllegalArgumentException("Group already added to another group");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Group addGroup(Group g) {
/*  901 */       g.checkGroup(DialogLayout.this);
/*  902 */       g.alreadyAdded = true;
/*  903 */       addSpring(g);
/*  904 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Group addGroups(Group... groups) {
/*  916 */       for (Group g : groups) {
/*  917 */         addGroup(g);
/*      */       }
/*  919 */       return this;
/*      */     }
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
/*      */     public Group addWidget(Widget w) {
/*  934 */       if (w.getParent() != DialogLayout.this) {
/*  935 */         DialogLayout.this.add(w);
/*      */       }
/*  937 */       DialogLayout.WidgetSpring s = DialogLayout.this.widgetSprings.get(w);
/*  938 */       if (s == null) {
/*  939 */         throw new IllegalStateException("WidgetSpring for Widget not found: " + w);
/*      */       }
/*  941 */       addSpring(s);
/*  942 */       return this;
/*      */     }
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
/*      */     public Group addWidget(Widget w, Alignment alignment) {
/*  959 */       addWidget(w);
/*  960 */       DialogLayout.this.setWidgetAlignment(w, alignment);
/*  961 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Group addWidgets(Widget... widgets) {
/*  971 */       for (Widget w : widgets) {
/*  972 */         addWidget(w);
/*      */       }
/*  974 */       return this;
/*      */     }
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
/*      */     public Group addWidgetsWithGap(String gapName, Widget... widgets) {
/*  989 */       AnimationState.StateKey stateNotFirst = AnimationState.StateKey.get(gapName.concat("NotFirst"));
/*  990 */       AnimationState.StateKey stateNotLast = AnimationState.StateKey.get(gapName.concat("NotLast"));
/*  991 */       for (int i = 0, n = widgets.length; i < n; i++) {
/*  992 */         if (i > 0) {
/*  993 */           addGap(gapName);
/*      */         }
/*  995 */         Widget w = widgets[i];
/*  996 */         addWidget(w);
/*  997 */         AnimationState as = w.getAnimationState();
/*  998 */         as.setAnimationState(stateNotFirst, (i > 0));
/*  999 */         as.setAnimationState(stateNotLast, (i < n - 1));
/*      */       } 
/* 1001 */       return this;
/*      */     }
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
/*      */     public Group addGap(int min, int pref, int max) {
/* 1017 */       addSpring(new DialogLayout.GapSpring(min, pref, max, false));
/* 1018 */       return this;
/*      */     }
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
/*      */     public Group addGap(int size) {
/* 1032 */       addSpring(new DialogLayout.GapSpring(size, size, size, false));
/* 1033 */       return this;
/*      */     }
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
/*      */     public Group addMinGap(int minSize) {
/* 1047 */       addSpring(new DialogLayout.GapSpring(minSize, minSize, 32767, false));
/* 1048 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Group addGap() {
/* 1058 */       addSpring(new DialogLayout.GapSpring(0, 0, 32767, false));
/* 1059 */       return this;
/*      */     }
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
/*      */     public Group addGap(String name) {
/* 1074 */       if (name.length() == 0) {
/* 1075 */         throw new IllegalArgumentException("name");
/*      */       }
/* 1077 */       addSpring(new DialogLayout.NamedGapSpring(name));
/* 1078 */       return this;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void removeDefaultGaps() {
/* 1085 */       for (int i = this.springs.size(); i-- > 0; ) {
/* 1086 */         DialogLayout.Spring s = this.springs.get(i);
/* 1087 */         if (s instanceof DialogLayout.GapSpring) {
/* 1088 */           if (((DialogLayout.GapSpring)s).isDefault)
/* 1089 */             this.springs.remove(i);  continue;
/*      */         } 
/* 1091 */         if (s instanceof Group) {
/* 1092 */           ((Group)s).removeDefaultGaps();
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void addDefaultGap() {
/* 1101 */       for (int i = 0; i < this.springs.size(); i++) {
/* 1102 */         DialogLayout.Spring s = this.springs.get(i);
/* 1103 */         if (s instanceof Group) {
/* 1104 */           ((Group)s).addDefaultGap();
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean removeGroup(Group g, boolean removeWidgets) {
/* 1118 */       for (int i = 0; i < this.springs.size(); i++) {
/* 1119 */         if (this.springs.get(i) == g) {
/* 1120 */           this.springs.remove(i);
/* 1121 */           if (removeWidgets) {
/* 1122 */             g.removeWidgets();
/* 1123 */             DialogLayout.this.recheckWidgets();
/*      */           } 
/* 1125 */           DialogLayout.this.layoutGroupsChanged();
/* 1126 */           return true;
/*      */         } 
/*      */       } 
/* 1129 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void clear(boolean removeWidgets) {
/* 1139 */       if (removeWidgets) {
/* 1140 */         removeWidgets();
/*      */       }
/* 1142 */       this.springs.clear();
/* 1143 */       if (removeWidgets) {
/* 1144 */         DialogLayout.this.recheckWidgets();
/*      */       }
/* 1146 */       DialogLayout.this.layoutGroupsChanged();
/*      */     }
/*      */     
/*      */     void addSpring(DialogLayout.Spring s) {
/* 1150 */       this.springs.add(s);
/* 1151 */       DialogLayout.this.layoutGroupsChanged();
/*      */     }
/*      */     
/*      */     void recheckWidgets() {
/* 1155 */       for (int i = this.springs.size(); i-- > 0; ) {
/* 1156 */         DialogLayout.Spring s = this.springs.get(i);
/* 1157 */         if (s instanceof DialogLayout.WidgetSpring) {
/* 1158 */           if (!DialogLayout.this.widgetSprings.containsKey(((DialogLayout.WidgetSpring)s).w))
/* 1159 */             this.springs.remove(i);  continue;
/*      */         } 
/* 1161 */         if (s instanceof Group) {
/* 1162 */           ((Group)s).recheckWidgets();
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*      */     void removeWidgets() {
/* 1168 */       for (int i = this.springs.size(); i-- > 0; ) {
/* 1169 */         DialogLayout.Spring s = this.springs.get(i);
/* 1170 */         if (s instanceof DialogLayout.WidgetSpring) {
/* 1171 */           DialogLayout.this.removeChild((DialogLayout.WidgetSpring)s); continue;
/* 1172 */         }  if (s instanceof Group)
/* 1173 */           ((Group)s).removeWidgets(); 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   static class SpringDelta
/*      */     implements Comparable<SpringDelta> {
/*      */     final int idx;
/*      */     final int delta;
/*      */     
/*      */     SpringDelta(int idx, int delta) {
/* 1184 */       this.idx = idx;
/* 1185 */       this.delta = delta;
/*      */     }
/*      */     
/*      */     public int compareTo(SpringDelta o) {
/* 1189 */       return this.delta - o.delta;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   class SequentialGroup
/*      */     extends Group
/*      */   {
/*      */     int getMinSize(int axis) {
/* 1199 */       int size = 0;
/* 1200 */       for (int i = 0, n = this.springs.size(); i < n; i++) {
/* 1201 */         DialogLayout.Spring s = this.springs.get(i);
/* 1202 */         if (DialogLayout.this.includeInvisibleWidgets || s.isVisible()) {
/* 1203 */           size += s.getMinSize(axis);
/*      */         }
/*      */       } 
/* 1206 */       return size;
/*      */     }
/*      */ 
/*      */     
/*      */     int getPrefSize(int axis) {
/* 1211 */       int size = 0;
/* 1212 */       for (int i = 0, n = this.springs.size(); i < n; i++) {
/* 1213 */         DialogLayout.Spring s = this.springs.get(i);
/* 1214 */         if (DialogLayout.this.includeInvisibleWidgets || s.isVisible()) {
/* 1215 */           size += s.getPrefSize(axis);
/*      */         }
/*      */       } 
/* 1218 */       return size;
/*      */     }
/*      */ 
/*      */     
/*      */     int getMaxSize(int axis) {
/* 1223 */       int size = 0;
/* 1224 */       boolean hasMax = false;
/* 1225 */       for (int i = 0, n = this.springs.size(); i < n; i++) {
/* 1226 */         DialogLayout.Spring s = this.springs.get(i);
/* 1227 */         if (DialogLayout.this.includeInvisibleWidgets || s.isVisible()) {
/* 1228 */           int max = s.getMaxSize(axis);
/* 1229 */           if (max > 0) {
/* 1230 */             size += max;
/* 1231 */             hasMax = true;
/*      */           } else {
/* 1233 */             size += s.getPrefSize(axis);
/*      */           } 
/*      */         } 
/*      */       } 
/* 1237 */       return hasMax ? size : 0;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void addDefaultGap() {
/* 1245 */       if (this.springs.size() > 1) {
/* 1246 */         boolean wasGap = true;
/* 1247 */         for (int i = 0; i < this.springs.size(); i++) {
/* 1248 */           DialogLayout.Spring s = this.springs.get(i);
/* 1249 */           if (DialogLayout.this.includeInvisibleWidgets || s.isVisible()) {
/* 1250 */             boolean isGap = (s instanceof DialogLayout.GapSpring || s instanceof DialogLayout.NamedGapSpring);
/* 1251 */             if (!isGap && !wasGap) {
/* 1252 */               this.springs.add(i++, new DialogLayout.GapSpring(-4, -4, -4, true));
/*      */             }
/* 1254 */             wasGap = isGap;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1258 */       super.addDefaultGap();
/*      */     }
/*      */ 
/*      */     
/*      */     void setSize(int axis, int pos, int size) {
/* 1263 */       int prefSize = getPrefSize(axis);
/* 1264 */       if (size == prefSize) {
/* 1265 */         for (DialogLayout.Spring s : this.springs) {
/* 1266 */           if (DialogLayout.this.includeInvisibleWidgets || s.isVisible()) {
/* 1267 */             int spref = s.getPrefSize(axis);
/* 1268 */             s.setSize(axis, pos, spref);
/* 1269 */             pos += spref;
/*      */           } 
/*      */         } 
/* 1272 */       } else if (this.springs.size() == 1) {
/*      */         
/* 1274 */         DialogLayout.Spring s = this.springs.get(0);
/* 1275 */         s.setSize(axis, pos, size);
/* 1276 */       } else if (this.springs.size() > 1) {
/* 1277 */         setSizeNonPref(axis, pos, size, prefSize);
/*      */       } 
/*      */     }
/*      */     
/*      */     private void setSizeNonPref(int axis, int pos, int size, int prefSize) {
/* 1282 */       int delta = size - prefSize;
/* 1283 */       boolean useMin = (delta < 0);
/* 1284 */       if (useMin) {
/* 1285 */         delta = -delta;
/*      */       }
/*      */       
/* 1288 */       DialogLayout.SpringDelta[] deltas = new DialogLayout.SpringDelta[this.springs.size()];
/* 1289 */       int resizeable = 0;
/* 1290 */       for (int i = 0; i < this.springs.size(); i++) {
/* 1291 */         DialogLayout.Spring s = this.springs.get(i);
/* 1292 */         if (DialogLayout.this.includeInvisibleWidgets || s.isVisible()) {
/* 1293 */           int sdelta = useMin ? (s.getPrefSize(axis) - s.getMinSize(axis)) : (s.getMaxSize(axis) - s.getPrefSize(axis));
/*      */ 
/*      */           
/* 1296 */           if (sdelta > 0) {
/* 1297 */             deltas[resizeable++] = new DialogLayout.SpringDelta(i, sdelta);
/*      */           }
/*      */         } 
/*      */       } 
/* 1301 */       if (resizeable > 0) {
/* 1302 */         if (resizeable > 1) {
/* 1303 */           Arrays.sort((Object[])deltas, 0, resizeable);
/*      */         }
/*      */         
/* 1306 */         int[] sizes = new int[this.springs.size()];
/*      */         
/* 1308 */         int remaining = resizeable; int j;
/* 1309 */         for (j = 0; j < resizeable; j++) {
/* 1310 */           DialogLayout.SpringDelta d = deltas[j];
/*      */           
/* 1312 */           int sdelta = delta / remaining;
/* 1313 */           int ddelta = Math.min(d.delta, sdelta);
/* 1314 */           delta -= ddelta;
/* 1315 */           remaining--;
/*      */           
/* 1317 */           if (useMin) {
/* 1318 */             ddelta = -ddelta;
/*      */           }
/* 1320 */           sizes[d.idx] = ddelta;
/*      */         } 
/*      */         
/* 1323 */         for (j = 0; j < this.springs.size(); j++) {
/* 1324 */           DialogLayout.Spring s = this.springs.get(j);
/* 1325 */           if (DialogLayout.this.includeInvisibleWidgets || s.isVisible()) {
/* 1326 */             int ssize = s.getPrefSize(axis) + sizes[j];
/* 1327 */             s.setSize(axis, pos, ssize);
/* 1328 */             pos += ssize;
/*      */           } 
/*      */         } 
/*      */       } else {
/* 1332 */         for (DialogLayout.Spring s : this.springs) {
/* 1333 */           if (DialogLayout.this.includeInvisibleWidgets || s.isVisible()) {
/*      */             int ssize;
/* 1335 */             if (useMin) {
/* 1336 */               ssize = s.getMinSize(axis);
/*      */             } else {
/* 1338 */               ssize = s.getMaxSize(axis);
/* 1339 */               if (ssize == 0) {
/* 1340 */                 ssize = s.getPrefSize(axis);
/*      */               }
/*      */             } 
/* 1343 */             s.setSize(axis, pos, ssize);
/* 1344 */             pos += ssize;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   class ParallelGroup
/*      */     extends Group
/*      */   {
/*      */     int getMinSize(int axis) {
/* 1357 */       int size = 0;
/* 1358 */       for (int i = 0, n = this.springs.size(); i < n; i++) {
/* 1359 */         DialogLayout.Spring s = this.springs.get(i);
/* 1360 */         if (DialogLayout.this.includeInvisibleWidgets || s.isVisible()) {
/* 1361 */           size = Math.max(size, s.getMinSize(axis));
/*      */         }
/*      */       } 
/* 1364 */       return size;
/*      */     }
/*      */ 
/*      */     
/*      */     int getPrefSize(int axis) {
/* 1369 */       int size = 0;
/* 1370 */       for (int i = 0, n = this.springs.size(); i < n; i++) {
/* 1371 */         DialogLayout.Spring s = this.springs.get(i);
/* 1372 */         if (DialogLayout.this.includeInvisibleWidgets || s.isVisible()) {
/* 1373 */           size = Math.max(size, s.getPrefSize(axis));
/*      */         }
/*      */       } 
/* 1376 */       return size;
/*      */     }
/*      */ 
/*      */     
/*      */     int getMaxSize(int axis) {
/* 1381 */       int size = 0;
/* 1382 */       for (int i = 0, n = this.springs.size(); i < n; i++) {
/* 1383 */         DialogLayout.Spring s = this.springs.get(i);
/* 1384 */         if (DialogLayout.this.includeInvisibleWidgets || s.isVisible()) {
/* 1385 */           size = Math.max(size, s.getMaxSize(axis));
/*      */         }
/*      */       } 
/* 1388 */       return size;
/*      */     }
/*      */ 
/*      */     
/*      */     void setSize(int axis, int pos, int size) {
/* 1393 */       for (int i = 0, n = this.springs.size(); i < n; i++) {
/* 1394 */         DialogLayout.Spring s = this.springs.get(i);
/* 1395 */         if (DialogLayout.this.includeInvisibleWidgets || s.isVisible()) {
/* 1396 */           s.setSize(axis, pos, size);
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public DialogLayout.Group addGap() {
/* 1403 */       DialogLayout.getLogger().log(Level.WARNING, "Useless call to addGap() on ParallelGroup", new Throwable());
/* 1404 */       return this;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\DialogLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */