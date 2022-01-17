/*      */ package de.matthiasmann.twl;
/*      */ 
/*      */ import de.matthiasmann.twl.renderer.AnimationState;
/*      */ import de.matthiasmann.twl.renderer.Image;
/*      */ import de.matthiasmann.twl.renderer.MouseCursor;
/*      */ import de.matthiasmann.twl.renderer.OffscreenRenderer;
/*      */ import de.matthiasmann.twl.renderer.OffscreenSurface;
/*      */ import de.matthiasmann.twl.renderer.Renderer;
/*      */ import de.matthiasmann.twl.theme.ThemeManager;
/*      */ import de.matthiasmann.twl.utils.TextUtil;
/*      */ import de.matthiasmann.twl.utils.TintAnimator;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.beans.PropertyChangeSupport;
/*      */ import java.security.AccessControlException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
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
/*      */ public class Widget
/*      */ {
/*   89 */   public static final AnimationState.StateKey STATE_KEYBOARD_FOCUS = AnimationState.StateKey.get("keyboardFocus");
/*   90 */   public static final AnimationState.StateKey STATE_HAS_OPEN_POPUPS = AnimationState.StateKey.get("hasOpenPopups");
/*   91 */   public static final AnimationState.StateKey STATE_HAS_FOCUSED_CHILD = AnimationState.StateKey.get("hasFocusedChild");
/*   92 */   public static final AnimationState.StateKey STATE_DISABLED = AnimationState.StateKey.get("disabled");
/*      */   
/*      */   private static final int LAYOUT_INVALID_LOCAL = 1;
/*      */   
/*      */   private static final int LAYOUT_INVALID_GLOBAL = 3;
/*      */   
/*      */   private Widget parent;
/*      */   
/*      */   private int posX;
/*      */   
/*      */   private int posY;
/*      */   
/*      */   private int width;
/*      */   
/*      */   private int height;
/*      */   
/*      */   private int layoutInvalid;
/*      */   
/*      */   private boolean clip;
/*      */   
/*      */   private boolean visible = true;
/*      */   
/*      */   private boolean hasOpenPopup;
/*      */   
/*      */   private boolean enabled = true;
/*      */   
/*      */   private boolean locallyEnabled = true;
/*      */   
/*      */   private String theme;
/*      */   
/*      */   private ThemeManager themeManager;
/*      */   private Image background;
/*      */   private Image overlay;
/*      */   private Object tooltipContent;
/*      */   private Object themeTooltipContent;
/*      */   private InputMap inputMap;
/*      */   private ActionMap actionMap;
/*      */   private TintAnimator tintAnimator;
/*      */   private PropertyChangeSupport propertyChangeSupport;
/*      */   volatile GUI guiInstance;
/*      */   private OffscreenSurface offscreenSurface;
/*      */   private RenderOffscreen renderOffscreen;
/*      */   private final AnimationState animState;
/*      */   private final boolean sharedAnimState;
/*      */   private short borderLeft;
/*      */   private short borderTop;
/*      */   private short borderRight;
/*      */   private short borderBottom;
/*      */   private short minWidth;
/*      */   private short minHeight;
/*      */   private short maxWidth;
/*      */   private short maxHeight;
/*      */   private short offscreenExtraLeft;
/*      */   private short offscreenExtraTop;
/*      */   private short offscreenExtraRight;
/*      */   private short offscreenExtraBottom;
/*      */   private ArrayList<Widget> children;
/*      */   private Widget lastChildMouseOver;
/*      */   private Widget focusChild;
/*      */   private MouseCursor mouseCursor;
/*      */   private FocusGainedCause focusGainedCause;
/*      */   private boolean focusKeyEnabled = true;
/*      */   private boolean canAcceptKeyboardFocus;
/*      */   private boolean depthFocusTraversal = true;
/*  156 */   private static final ThreadLocal<Widget[]> focusTransferInfo = (ThreadLocal)new ThreadLocal<Widget>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Widget() {
/*  168 */     this(null, false);
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
/*      */   public Widget(AnimationState animState) {
/*  182 */     this(animState, false);
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
/*      */   public Widget(AnimationState animState, boolean inherit) {
/*  200 */     Class<?> clazz = getClass();
/*      */     do {
/*  202 */       this.theme = clazz.getSimpleName().toLowerCase(Locale.ENGLISH);
/*  203 */       clazz = clazz.getSuperclass();
/*  204 */     } while (this.theme.length() == 0 && clazz != null);
/*      */     
/*  206 */     if (animState == null || inherit) {
/*  207 */       this.animState = new AnimationState(animState);
/*  208 */       this.sharedAnimState = false;
/*      */     } else {
/*  210 */       this.animState = animState;
/*  211 */       this.sharedAnimState = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPropertyChangeListener(PropertyChangeListener listener) {
/*  222 */     createPropertyChangeSupport().addPropertyChangeListener(listener);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/*  233 */     createPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removePropertyChangeListener(PropertyChangeListener listener) {
/*  243 */     if (this.propertyChangeSupport != null) {
/*  244 */       this.propertyChangeSupport.removePropertyChangeListener(listener);
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
/*      */   public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
/*  256 */     if (this.propertyChangeSupport != null) {
/*  257 */       this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasOpenPopups() {
/*  267 */     return this.hasOpenPopup;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Widget getParent() {
/*  276 */     return this.parent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Widget getRootWidget() {
/*  285 */     Widget w = this;
/*      */     Widget p;
/*  287 */     while ((p = w.parent) != null) {
/*  288 */       w = p;
/*      */     }
/*  290 */     return w;
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
/*      */   public final GUI getGUI() {
/*  314 */     return this.guiInstance;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isVisible() {
/*  323 */     return this.visible;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVisible(boolean visible) {
/*  333 */     if (this.visible != visible) {
/*  334 */       this.visible = visible;
/*  335 */       if (!visible) {
/*  336 */         GUI gui = getGUI();
/*  337 */         if (gui != null) {
/*  338 */           gui.widgetHidden(this);
/*      */         }
/*  340 */         if (this.parent != null) {
/*  341 */           this.parent.childHidden(this);
/*      */         }
/*      */       } 
/*  344 */       if (this.parent != null) {
/*  345 */         this.parent.childVisibilityChanged(this);
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
/*      */   public final boolean isLocallyEnabled() {
/*  361 */     return this.locallyEnabled;
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
/*      */   public final boolean isEnabled() {
/*  379 */     return this.enabled;
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
/*      */   public void setEnabled(boolean enabled) {
/*  400 */     if (this.locallyEnabled != enabled) {
/*  401 */       this.locallyEnabled = enabled;
/*  402 */       firePropertyChange("locallyEnabled", !enabled, enabled);
/*  403 */       recursivelyEnabledChanged(getGUI(), (this.parent != null) ? this.parent.enabled : true);
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
/*      */   public final int getX() {
/*  418 */     return this.posX;
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
/*      */   public final int getY() {
/*  432 */     return this.posY;
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
/*      */   public final int getWidth() {
/*  445 */     return this.width;
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
/*      */   public final int getHeight() {
/*  458 */     return this.height;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getRight() {
/*  466 */     return this.posX + this.width;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getBottom() {
/*  474 */     return this.posY + this.height;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getInnerX() {
/*  482 */     return this.posX + this.borderLeft;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getInnerY() {
/*  490 */     return this.posY + this.borderTop;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getInnerWidth() {
/*  498 */     return Math.max(0, this.width - this.borderLeft - this.borderRight);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getInnerHeight() {
/*  506 */     return Math.max(0, this.height - this.borderTop - this.borderBottom);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getInnerRight() {
/*  514 */     return this.posX + Math.max(this.borderLeft, this.width - this.borderRight);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getInnerBottom() {
/*  522 */     return this.posY + Math.max(this.borderTop, this.height - this.borderBottom);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInside(int x, int y) {
/*  533 */     return (x >= this.posX && y >= this.posY && x < this.posX + this.width && y < this.posY + this.height);
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
/*      */   public boolean setPosition(int x, int y) {
/*  557 */     return setPositionImpl(x, y);
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
/*      */   public boolean setSize(int width, int height) {
/*  582 */     if (width < 0 || height < 0) {
/*  583 */       throw new IllegalArgumentException("negative size");
/*      */     }
/*  585 */     int oldWidth = this.width;
/*  586 */     int oldHeight = this.height;
/*  587 */     if (oldWidth != width || oldHeight != height) {
/*  588 */       this.width = width;
/*  589 */       this.height = height;
/*      */       
/*  591 */       sizeChanged();
/*      */       
/*  593 */       if (this.propertyChangeSupport != null) {
/*  594 */         firePropertyChange("width", oldWidth, width);
/*  595 */         firePropertyChange("height", oldHeight, height);
/*      */       } 
/*  597 */       return true;
/*      */     } 
/*  599 */     return false;
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
/*      */   public boolean setInnerSize(int width, int height) {
/*  612 */     return setSize(width + this.borderLeft + this.borderRight, height + this.borderTop + this.borderBottom);
/*      */   }
/*      */   
/*      */   public short getBorderTop() {
/*  616 */     return this.borderTop;
/*      */   }
/*      */   
/*      */   public short getBorderLeft() {
/*  620 */     return this.borderLeft;
/*      */   }
/*      */   
/*      */   public short getBorderBottom() {
/*  624 */     return this.borderBottom;
/*      */   }
/*      */   
/*      */   public short getBorderRight() {
/*  628 */     return this.borderRight;
/*      */   }
/*      */   
/*      */   public int getBorderHorizontal() {
/*  632 */     return this.borderLeft + this.borderRight;
/*      */   }
/*      */   
/*      */   public int getBorderVertical() {
/*  636 */     return this.borderTop + this.borderBottom;
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
/*      */   public boolean setBorderSize(int top, int left, int bottom, int right) {
/*  649 */     if (top < 0 || left < 0 || bottom < 0 || right < 0) {
/*  650 */       throw new IllegalArgumentException("negative border size");
/*      */     }
/*  652 */     if (this.borderTop != top || this.borderBottom != bottom || this.borderLeft != left || this.borderRight != right) {
/*      */       
/*  654 */       int innerWidth = getInnerWidth();
/*  655 */       int innerHeight = getInnerHeight();
/*  656 */       int deltaLeft = left - this.borderLeft;
/*  657 */       int deltaTop = top - this.borderTop;
/*  658 */       this.borderLeft = (short)left;
/*  659 */       this.borderTop = (short)top;
/*  660 */       this.borderRight = (short)right;
/*  661 */       this.borderBottom = (short)bottom;
/*      */ 
/*      */       
/*  664 */       if (this.children != null && (deltaLeft != 0 || deltaTop != 0)) {
/*  665 */         for (int i = 0, n = this.children.size(); i < n; i++) {
/*  666 */           adjustChildPosition(this.children.get(i), deltaLeft, deltaTop);
/*      */         }
/*      */       }
/*      */ 
/*      */       
/*  671 */       setInnerSize(innerWidth, innerHeight);
/*  672 */       borderChanged();
/*  673 */       return true;
/*      */     } 
/*  675 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setBorderSize(int horizontal, int vertical) {
/*  686 */     return setBorderSize(vertical, horizontal, vertical, horizontal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setBorderSize(int border) {
/*  696 */     return setBorderSize(border, border, border, border);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setBorderSize(Border border) {
/*  705 */     if (border == null) {
/*  706 */       return setBorderSize(0, 0, 0, 0);
/*      */     }
/*  708 */     return setBorderSize(border.getBorderTop(), border.getBorderLeft(), border.getBorderBottom(), border.getBorderRight());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public short getOffscreenExtraTop() {
/*  714 */     return this.offscreenExtraTop;
/*      */   }
/*      */   
/*      */   public short getOffscreenExtraLeft() {
/*  718 */     return this.offscreenExtraLeft;
/*      */   }
/*      */   
/*      */   public short getOffscreenExtraBottom() {
/*  722 */     return this.offscreenExtraBottom;
/*      */   }
/*      */   
/*      */   public short getOffscreenExtraRight() {
/*  726 */     return this.offscreenExtraRight;
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
/*      */   public void setOffscreenExtra(int top, int left, int bottom, int right) {
/*  739 */     if (top < 0 || left < 0 || bottom < 0 || right < 0) {
/*  740 */       throw new IllegalArgumentException("negative offscreen extra size");
/*      */     }
/*  742 */     this.offscreenExtraTop = (short)top;
/*  743 */     this.offscreenExtraLeft = (short)left;
/*  744 */     this.offscreenExtraBottom = (short)bottom;
/*  745 */     this.offscreenExtraRight = (short)right;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOffscreenExtra(Border offscreenExtra) {
/*  755 */     if (offscreenExtra == null) {
/*  756 */       setOffscreenExtra(0, 0, 0, 0);
/*      */     } else {
/*  758 */       setOffscreenExtra(offscreenExtra.getBorderTop(), offscreenExtra.getBorderLeft(), offscreenExtra.getBorderBottom(), offscreenExtra.getBorderRight());
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
/*      */   public int getMinWidth() {
/*  774 */     return Math.max(this.minWidth, this.borderLeft + this.borderRight);
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
/*      */   public int getMinHeight() {
/*  788 */     return Math.max(this.minHeight, this.borderTop + this.borderBottom);
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
/*      */   public void setMinSize(int width, int height) {
/*  803 */     if (width < 0 || height < 0) {
/*  804 */       throw new IllegalArgumentException("negative size");
/*      */     }
/*  806 */     this.minWidth = (short)Math.min(width, 32767);
/*  807 */     this.minHeight = (short)Math.min(height, 32767);
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
/*      */   public int getPreferredInnerWidth() {
/*  824 */     int right = getInnerX();
/*  825 */     if (this.children != null) {
/*  826 */       for (int i = 0, n = this.children.size(); i < n; i++) {
/*  827 */         Widget child = this.children.get(i);
/*  828 */         right = Math.max(right, child.getRight());
/*      */       } 
/*      */     }
/*  831 */     return right - getInnerX();
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
/*      */   public int getPreferredWidth() {
/*  843 */     int prefWidth = this.borderLeft + this.borderRight + getPreferredInnerWidth();
/*  844 */     Image bg = getBackground();
/*  845 */     if (bg != null) {
/*  846 */       prefWidth = Math.max(prefWidth, bg.getWidth());
/*      */     }
/*  848 */     return Math.max(this.minWidth, prefWidth);
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
/*      */   public int getPreferredInnerHeight() {
/*  865 */     int bottom = getInnerY();
/*  866 */     if (this.children != null) {
/*  867 */       for (int i = 0, n = this.children.size(); i < n; i++) {
/*  868 */         Widget child = this.children.get(i);
/*  869 */         bottom = Math.max(bottom, child.getBottom());
/*      */       } 
/*      */     }
/*  872 */     return bottom - getInnerY();
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
/*      */   public int getPreferredHeight() {
/*  885 */     int prefHeight = this.borderTop + this.borderBottom + getPreferredInnerHeight();
/*  886 */     Image bg = getBackground();
/*  887 */     if (bg != null) {
/*  888 */       prefHeight = Math.max(prefHeight, bg.getHeight());
/*      */     }
/*  890 */     return Math.max(this.minHeight, prefHeight);
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
/*      */   public int getMaxWidth() {
/*  904 */     return this.maxWidth;
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
/*      */   public int getMaxHeight() {
/*  918 */     return this.maxHeight;
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
/*      */   public void setMaxSize(int width, int height) {
/*  934 */     if (width < 0 || height < 0) {
/*  935 */       throw new IllegalArgumentException("negative size");
/*      */     }
/*  937 */     this.maxWidth = (short)Math.min(width, 32767);
/*  938 */     this.maxHeight = (short)Math.min(height, 32767);
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
/*      */   public static int computeSize(int min, int preferred, int max) {
/*  954 */     if (max > 0) {
/*  955 */       preferred = Math.min(preferred, max);
/*      */     }
/*  957 */     return Math.max(min, preferred);
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
/*      */   public void adjustSize() {
/*  971 */     setSize(computeSize(getMinWidth(), getPreferredWidth(), getMaxWidth()), computeSize(getMinHeight(), getPreferredHeight(), getMaxHeight()));
/*      */     
/*  973 */     validateLayout();
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
/*      */   public void invalidateLayout() {
/*  987 */     if (this.layoutInvalid < 3) {
/*  988 */       invalidateLayoutLocally();
/*  989 */       if (this.parent != null) {
/*  990 */         this.layoutInvalid = 3;
/*  991 */         this.parent.childInvalidateLayout(this);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void validateLayout() {
/* 1002 */     if (this.layoutInvalid != 0) {
/*      */ 
/*      */ 
/*      */       
/* 1006 */       this.layoutInvalid = 0;
/* 1007 */       layout();
/*      */     } 
/* 1009 */     if (this.children != null) {
/* 1010 */       for (int i = 0, n = this.children.size(); i < n; i++) {
/* 1011 */         ((Widget)this.children.get(i)).validateLayout();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTheme() {
/* 1022 */     return this.theme;
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
/*      */   public void setTheme(String theme) {
/* 1045 */     if (theme == null) {
/* 1046 */       throw new IllegalArgumentException("theme is null");
/*      */     }
/* 1048 */     if (theme.length() > 0) {
/* 1049 */       int slashIdx = theme.lastIndexOf('/');
/* 1050 */       if (slashIdx > 0) {
/* 1051 */         throw new IllegalArgumentException("'/' is only allowed as first character in theme name");
/*      */       }
/* 1053 */       if (slashIdx < 0) {
/* 1054 */         if (theme.indexOf('.') >= 0) {
/* 1055 */           throw new IllegalArgumentException("'.' is only allowed for absolute theme paths");
/*      */         }
/* 1057 */       } else if (theme.length() == 1) {
/* 1058 */         throw new IllegalArgumentException("'/' requires a theme path");
/*      */       } 
/* 1060 */       for (int i = 0, n = theme.length(); i < n; i++) {
/* 1061 */         char ch = theme.charAt(i);
/* 1062 */         if (Character.isISOControl(ch) || ch == '*') {
/* 1063 */           throw new IllegalArgumentException("invalid character '" + TextUtil.toPrintableString(ch) + "' in theme name");
/*      */         }
/*      */       } 
/*      */     } 
/* 1067 */     this.theme = theme;
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
/*      */   public final String getThemePath() {
/* 1082 */     return getThemePath(0).toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isClip() {
/* 1090 */     return this.clip;
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
/*      */   public void setClip(boolean clip) {
/* 1107 */     this.clip = clip;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFocusKeyEnabled() {
/* 1115 */     return this.focusKeyEnabled;
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
/*      */   public void setFocusKeyEnabled(boolean focusKeyEnabled) {
/* 1127 */     this.focusKeyEnabled = focusKeyEnabled;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Image getBackground() {
/* 1136 */     return this.background;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBackground(Image background) {
/* 1145 */     this.background = background;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Image getOverlay() {
/* 1154 */     return this.overlay;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOverlay(Image overlay) {
/* 1163 */     this.overlay = overlay;
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
/*      */   public MouseCursor getMouseCursor(Event evt) {
/* 1176 */     return getMouseCursor();
/*      */   }
/*      */   
/*      */   public MouseCursor getMouseCursor() {
/* 1180 */     return this.mouseCursor;
/*      */   }
/*      */   
/*      */   public void setMouseCursor(MouseCursor mouseCursor) {
/* 1184 */     this.mouseCursor = mouseCursor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getNumChildren() {
/* 1192 */     if (this.children != null) {
/* 1193 */       return this.children.size();
/*      */     }
/* 1195 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final Widget getChild(int index) throws IndexOutOfBoundsException {
/* 1205 */     if (this.children != null) {
/* 1206 */       return this.children.get(index);
/*      */     }
/* 1208 */     throw new IndexOutOfBoundsException();
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
/*      */   public void add(Widget child) {
/* 1222 */     insertChild(child, getNumChildren());
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
/*      */   public void insertChild(Widget child, int index) throws IndexOutOfBoundsException {
/* 1237 */     if (child == null) {
/* 1238 */       throw new IllegalArgumentException("child is null");
/*      */     }
/* 1240 */     if (child == this) {
/* 1241 */       throw new IllegalArgumentException("can't add to self");
/*      */     }
/* 1243 */     if (child.parent != null) {
/* 1244 */       throw new IllegalArgumentException("child widget already in tree");
/*      */     }
/* 1246 */     if (this.children == null) {
/* 1247 */       this.children = new ArrayList<Widget>();
/*      */     }
/* 1249 */     if (index < 0 || index > this.children.size()) {
/* 1250 */       throw new IndexOutOfBoundsException();
/*      */     }
/* 1252 */     child.setParent(this);
/* 1253 */     this.children.add(index, child);
/* 1254 */     GUI gui = getGUI();
/* 1255 */     if (gui != null) {
/* 1256 */       child.recursivelySetGUI(gui);
/*      */     }
/* 1258 */     adjustChildPosition(child, this.posX + this.borderLeft, this.posY + this.borderTop);
/* 1259 */     child.recursivelyEnabledChanged(null, this.enabled);
/* 1260 */     if (gui != null) {
/* 1261 */       child.recursivelyAddToGUI(gui);
/*      */     }
/* 1263 */     if (this.themeManager != null) {
/* 1264 */       child.applyTheme(this.themeManager);
/*      */     }
/*      */     try {
/* 1267 */       childAdded(child);
/* 1268 */     } catch (Exception ex) {
/* 1269 */       getLogger().log(Level.SEVERE, "Exception in childAdded()", ex);
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
/*      */   public final int getChildIndex(Widget child) {
/* 1282 */     if (this.children != null)
/*      */     {
/* 1284 */       for (int i = 0, n = this.children.size(); i < n; i++) {
/* 1285 */         if (this.children.get(i) == child) {
/* 1286 */           return i;
/*      */         }
/*      */       } 
/*      */     }
/* 1290 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean removeChild(Widget child) {
/* 1300 */     int idx = getChildIndex(child);
/* 1301 */     if (idx >= 0) {
/* 1302 */       removeChild(idx);
/* 1303 */       return true;
/*      */     } 
/* 1305 */     return false;
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
/*      */   public Widget removeChild(int index) throws IndexOutOfBoundsException {
/* 1320 */     if (this.children != null) {
/* 1321 */       Widget child = this.children.remove(index);
/* 1322 */       unparentChild(child);
/* 1323 */       if (this.lastChildMouseOver == child) {
/* 1324 */         this.lastChildMouseOver = null;
/*      */       }
/* 1326 */       if (this.focusChild == child) {
/* 1327 */         this.focusChild = null;
/*      */       }
/* 1329 */       childRemoved(child);
/* 1330 */       return child;
/*      */     } 
/* 1332 */     throw new IndexOutOfBoundsException();
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
/*      */   public void removeAllChildren() {
/* 1344 */     if (this.children != null) {
/* 1345 */       this.focusChild = null;
/* 1346 */       this.lastChildMouseOver = null;
/* 1347 */       for (int i = 0, n = this.children.size(); i < n; i++) {
/* 1348 */         Widget child = this.children.get(i);
/* 1349 */         unparentChild(child);
/*      */       } 
/* 1351 */       this.children.clear();
/* 1352 */       if (this.hasOpenPopup) {
/* 1353 */         GUI gui = getGUI();
/* 1354 */         assert gui != null;
/* 1355 */         recalcOpenPopups(gui);
/*      */       } 
/* 1357 */       allChildrenRemoved();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void destroy() {
/* 1365 */     if (this.children != null) {
/* 1366 */       for (int i = 0, n = this.children.size(); i < n; i++) {
/* 1367 */         ((Widget)this.children.get(i)).destroy();
/*      */       }
/*      */     }
/* 1370 */     if (this.offscreenSurface != null) {
/* 1371 */       this.offscreenSurface.destroy();
/* 1372 */       this.offscreenSurface = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean canAcceptKeyboardFocus() {
/* 1377 */     return this.canAcceptKeyboardFocus;
/*      */   }
/*      */   
/*      */   public void setCanAcceptKeyboardFocus(boolean canAcceptKeyboardFocus) {
/* 1381 */     this.canAcceptKeyboardFocus = canAcceptKeyboardFocus;
/*      */   }
/*      */   
/*      */   public boolean isDepthFocusTraversal() {
/* 1385 */     return this.depthFocusTraversal;
/*      */   }
/*      */   
/*      */   public void setDepthFocusTraversal(boolean depthFocusTraversal) {
/* 1389 */     this.depthFocusTraversal = depthFocusTraversal;
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
/*      */   public boolean requestKeyboardFocus() {
/* 1403 */     if (this.parent != null && this.visible) {
/* 1404 */       if (this.parent.focusChild == this) {
/* 1405 */         return true;
/*      */       }
/*      */       
/* 1408 */       boolean clear = focusTransferStart();
/*      */       try {
/* 1410 */         return this.parent.requestKeyboardFocus(this);
/*      */       } finally {
/* 1412 */         focusTransferClear(clear);
/*      */       } 
/*      */     } 
/* 1415 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void giveupKeyboardFocus() {
/* 1423 */     if (this.parent != null && this.parent.focusChild == this) {
/* 1424 */       this.parent.requestKeyboardFocus(null);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasKeyboardFocus() {
/* 1433 */     if (this.parent != null) {
/* 1434 */       return (this.parent.focusChild == this);
/*      */     }
/* 1436 */     return false;
/*      */   }
/*      */   
/*      */   public boolean focusNextChild() {
/* 1440 */     return moveFocus(true, 1);
/*      */   }
/*      */   
/*      */   public boolean focusPrevChild() {
/* 1444 */     return moveFocus(true, -1);
/*      */   }
/*      */   
/*      */   public boolean focusFirstChild() {
/* 1448 */     return moveFocus(false, 1);
/*      */   }
/*      */   
/*      */   public boolean focusLastChild() {
/* 1452 */     return moveFocus(false, -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AnimationState getAnimationState() {
/* 1460 */     return this.animState;
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
/*      */   public boolean hasSharedAnimationState() {
/* 1475 */     return this.sharedAnimState;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TintAnimator getTintAnimator() {
/* 1483 */     return this.tintAnimator;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTintAnimator(TintAnimator tintAnimator) {
/* 1491 */     this.tintAnimator = tintAnimator;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RenderOffscreen getRenderOffscreen() {
/* 1499 */     return this.renderOffscreen;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRenderOffscreen(RenderOffscreen renderOffscreen) {
/* 1507 */     this.renderOffscreen = renderOffscreen;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object getTooltipContent() {
/* 1516 */     return this.tooltipContent;
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
/*      */   public void setTooltipContent(Object tooltipContent) {
/* 1528 */     this.tooltipContent = tooltipContent;
/* 1529 */     updateTooltip();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InputMap getInputMap() {
/* 1537 */     return this.inputMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInputMap(InputMap inputMap) {
/* 1547 */     this.inputMap = inputMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionMap getActionMap() {
/* 1556 */     return this.actionMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ActionMap getOrCreateActionMap() {
/* 1565 */     if (this.actionMap == null) {
/* 1566 */       this.actionMap = new ActionMap();
/*      */     }
/* 1568 */     return this.actionMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setActionMap(ActionMap actionMap) {
/* 1576 */     this.actionMap = actionMap;
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
/*      */   public Widget getWidgetAt(int x, int y) {
/* 1590 */     Widget child = getChildAt(x, y);
/* 1591 */     if (child != null) {
/* 1592 */       return child.getWidgetAt(x, y);
/*      */     }
/* 1594 */     return this;
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
/*      */   protected void applyTheme(ThemeInfo themeInfo) {
/* 1609 */     applyThemeBackground(themeInfo);
/* 1610 */     applyThemeOverlay(themeInfo);
/* 1611 */     applyThemeBorder(themeInfo);
/* 1612 */     applyThemeOffscreenExtra(themeInfo);
/* 1613 */     applyThemeMinSize(themeInfo);
/* 1614 */     applyThemeMaxSize(themeInfo);
/* 1615 */     applyThemeMouseCursor(themeInfo);
/* 1616 */     applyThemeInputMap(themeInfo);
/* 1617 */     applyThemeTooltip(themeInfo);
/* 1618 */     invalidateLayout();
/*      */   }
/*      */   
/*      */   protected void applyThemeBackground(ThemeInfo themeInfo) {
/* 1622 */     setBackground(themeInfo.getImage("background"));
/*      */   }
/*      */   
/*      */   protected void applyThemeOverlay(ThemeInfo themeInfo) {
/* 1626 */     setOverlay(themeInfo.getImage("overlay"));
/*      */   }
/*      */   
/*      */   protected void applyThemeBorder(ThemeInfo themeInfo) {
/* 1630 */     setBorderSize(themeInfo.<Border>getParameterValue("border", false, Border.class));
/*      */   }
/*      */   
/*      */   protected void applyThemeOffscreenExtra(ThemeInfo themeInfo) {
/* 1634 */     setOffscreenExtra(themeInfo.<Border>getParameterValue("offscreenExtra", false, Border.class));
/*      */   }
/*      */   
/*      */   protected void applyThemeMinSize(ThemeInfo themeInfo) {
/* 1638 */     setMinSize(themeInfo.getParameter("minWidth", 0), themeInfo.getParameter("minHeight", 0));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void applyThemeMaxSize(ThemeInfo themeInfo) {
/* 1644 */     setMaxSize(themeInfo.getParameter("maxWidth", 32767), themeInfo.getParameter("maxHeight", 32767));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void applyThemeMouseCursor(ThemeInfo themeInfo) {
/* 1650 */     setMouseCursor(themeInfo.getMouseCursor("mouseCursor"));
/*      */   }
/*      */   
/*      */   protected void applyThemeInputMap(ThemeInfo themeInfo) {
/* 1654 */     setInputMap(themeInfo.<InputMap>getParameterValue("inputMap", false, InputMap.class));
/*      */   }
/*      */   
/*      */   protected void applyThemeTooltip(ThemeInfo themeInfo) {
/* 1658 */     this.themeTooltipContent = themeInfo.getParameterValue("tooltip", false);
/* 1659 */     if (this.tooltipContent == null) {
/* 1660 */       updateTooltip();
/*      */     }
/*      */   }
/*      */   
/*      */   protected Object getThemeTooltipContent() {
/* 1665 */     return this.themeTooltipContent;
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
/*      */   protected Object getTooltipContentAt(int mouseX, int mouseY) {
/* 1687 */     Object content = getTooltipContent();
/* 1688 */     if (content == null) {
/* 1689 */       content = getThemeTooltipContent();
/*      */     }
/* 1691 */     return content;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateTooltip() {
/* 1702 */     GUI gui = getGUI();
/* 1703 */     if (gui != null) {
/* 1704 */       gui.requestTooltipUpdate(this, false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void resetTooltip() {
/* 1715 */     GUI gui = getGUI();
/* 1716 */     if (gui != null) {
/* 1717 */       gui.requestTooltipUpdate(this, true);
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
/*      */   protected void addActionMapping(String action, String methodName, Object... params) {
/* 1738 */     getOrCreateActionMap().addMapping(action, this, methodName, params, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void reapplyTheme() {
/* 1747 */     if (this.themeManager != null) {
/* 1748 */       applyTheme(this.themeManager);
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
/*      */   protected boolean isMouseInside(Event evt) {
/* 1760 */     return isInside(evt.getMouseX(), evt.getMouseY());
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
/*      */   protected boolean handleEvent(Event evt) {
/* 1786 */     if (evt.isKeyEvent()) {
/* 1787 */       return handleKeyEvent(evt);
/*      */     }
/* 1789 */     return false;
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
/*      */   protected boolean handleKeyStrokeAction(String action, Event event) {
/* 1801 */     if (this.actionMap != null) {
/* 1802 */       return this.actionMap.invoke(action, event);
/*      */     }
/* 1804 */     return false;
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
/*      */   protected void moveChild(int from, int to) {
/* 1816 */     if (this.children == null) {
/* 1817 */       throw new IndexOutOfBoundsException();
/*      */     }
/* 1819 */     if (to < 0 || to >= this.children.size()) {
/* 1820 */       throw new IndexOutOfBoundsException("to");
/*      */     }
/* 1822 */     if (from < 0 || from >= this.children.size()) {
/* 1823 */       throw new IndexOutOfBoundsException("from");
/*      */     }
/* 1825 */     Widget child = this.children.remove(from);
/* 1826 */     this.children.add(to, child);
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
/*      */   protected boolean requestKeyboardFocus(Widget child) {
/* 1838 */     if (child != null && child.parent != this) {
/* 1839 */       throw new IllegalArgumentException("not a direct child");
/*      */     }
/* 1841 */     if (this.focusChild != child) {
/* 1842 */       if (child == null) {
/* 1843 */         recursivelyChildFocusLost(this.focusChild);
/* 1844 */         this.focusChild = null;
/* 1845 */         keyboardFocusChildChanged(null);
/*      */       } else {
/* 1847 */         boolean clear = focusTransferStart();
/*      */ 
/*      */         
/*      */         try {
/* 1851 */           FocusGainedCause savedCause = this.focusGainedCause;
/* 1852 */           if (savedCause == null) {
/* 1853 */             this.focusGainedCause = FocusGainedCause.CHILD_FOCUSED;
/*      */           }
/*      */           try {
/* 1856 */             if (!requestKeyboardFocus()) {
/* 1857 */               return false;
/*      */             }
/*      */           } finally {
/* 1860 */             this.focusGainedCause = savedCause;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1865 */           recursivelyChildFocusLost(this.focusChild);
/* 1866 */           this.focusChild = child;
/* 1867 */           keyboardFocusChildChanged(child);
/* 1868 */           if (!child.sharedAnimState) {
/* 1869 */             child.animState.setAnimationState(STATE_KEYBOARD_FOCUS, true);
/*      */           }
/*      */ 
/*      */           
/* 1873 */           FocusGainedCause cause = child.focusGainedCause;
/* 1874 */           Widget[] fti = focusTransferInfo.get();
/* 1875 */           child.keyboardFocusGained((cause != null) ? cause : FocusGainedCause.MANUAL, (fti != null) ? fti[0] : null);
/*      */         }
/*      */         finally {
/*      */           
/* 1879 */           focusTransferClear(clear);
/*      */         } 
/*      */       } 
/*      */     }
/* 1883 */     if (!this.sharedAnimState) {
/* 1884 */       this.animState.setAnimationState(STATE_HAS_FOCUSED_CHILD, (this.focusChild != null));
/*      */     }
/* 1886 */     return (this.focusChild != null);
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
/*      */   protected void beforeRemoveFromGUI(GUI gui) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void afterAddToGUI(GUI gui) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void layout() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void positionChanged() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void sizeChanged() {
/* 1935 */     invalidateLayoutLocally();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void borderChanged() {
/* 1945 */     invalidateLayout();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void childInvalidateLayout(Widget child) {
/* 1956 */     invalidateLayout();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void childAdded(Widget child) {
/* 1967 */     invalidateLayout();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void childRemoved(Widget exChild) {
/* 1978 */     invalidateLayout();
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
/*      */   protected void allChildrenRemoved() {
/* 1990 */     invalidateLayout();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void childVisibilityChanged(Widget child) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void keyboardFocusChildChanged(Widget child) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void keyboardFocusLost() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void keyboardFocusGained() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void keyboardFocusGained(FocusGainedCause cause, Widget previousWidget) {
/* 2037 */     keyboardFocusGained();
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
/*      */   protected void widgetDisabled() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void paint(GUI gui) {
/* 2062 */     paintBackground(gui);
/* 2063 */     paintWidget(gui);
/* 2064 */     paintChildren(gui);
/* 2065 */     paintOverlay(gui);
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
/*      */   protected void paintWidget(GUI gui) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void paintBackground(GUI gui) {
/* 2088 */     Image bgImage = getBackground();
/* 2089 */     if (bgImage != null) {
/* 2090 */       bgImage.draw(getAnimationState(), this.posX, this.posY, this.width, this.height);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void paintOverlay(GUI gui) {
/* 2100 */     Image ovImage = getOverlay();
/* 2101 */     if (ovImage != null) {
/* 2102 */       ovImage.draw(getAnimationState(), this.posX, this.posY, this.width, this.height);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void paintChildren(GUI gui) {
/* 2112 */     if (this.children != null) {
/* 2113 */       for (int i = 0, n = this.children.size(); i < n; i++) {
/* 2114 */         Widget child = this.children.get(i);
/* 2115 */         if (child.visible) {
/* 2116 */           child.drawWidget(gui);
/*      */         }
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
/*      */   protected void paintChild(GUI gui, Widget child) {
/* 2129 */     if (child.parent != this) {
/* 2130 */       throw new IllegalArgumentException("can only render direct children");
/*      */     }
/* 2132 */     child.drawWidget(gui);
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
/*      */   protected void paintDragOverlay(GUI gui, int mouseX, int mouseY, int modifier) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void invalidateLayoutLocally() {
/* 2156 */     if (this.layoutInvalid < 1) {
/* 2157 */       this.layoutInvalid = 1;
/* 2158 */       GUI gui = getGUI();
/* 2159 */       if (gui != null) {
/* 2160 */         gui.hasInvalidLayouts = true;
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
/*      */   protected void layoutChildFullInnerArea(Widget child) {
/* 2172 */     if (child.parent != this) {
/* 2173 */       throw new IllegalArgumentException("can only layout direct children");
/*      */     }
/* 2175 */     child.setPosition(getInnerX(), getInnerY());
/* 2176 */     child.setSize(getInnerWidth(), getInnerHeight());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void layoutChildrenFullInnerArea() {
/* 2184 */     if (this.children != null) {
/* 2185 */       for (int i = 0, n = this.children.size(); i < n; i++) {
/* 2186 */         layoutChildFullInnerArea(this.children.get(i));
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
/*      */   protected List<Widget> getKeyboardFocusOrder() {
/* 2199 */     if (this.children == null) {
/* 2200 */       return Collections.emptyList();
/*      */     }
/* 2202 */     return Collections.unmodifiableList(this.children);
/*      */   }
/*      */   
/*      */   private int collectFocusOrderList(ArrayList<Widget> list) {
/* 2206 */     int idx = -1;
/* 2207 */     for (Widget child : getKeyboardFocusOrder()) {
/* 2208 */       if (child.visible && child.isEnabled()) {
/* 2209 */         if (child.canAcceptKeyboardFocus) {
/* 2210 */           if (child == this.focusChild) {
/* 2211 */             idx = list.size();
/*      */           }
/* 2213 */           list.add(child);
/*      */         } 
/* 2215 */         if (child.depthFocusTraversal) {
/* 2216 */           int subIdx = child.collectFocusOrderList(list);
/* 2217 */           if (subIdx != -1) {
/* 2218 */             idx = subIdx;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/* 2223 */     return idx;
/*      */   }
/*      */   
/*      */   private boolean moveFocus(boolean relative, int dir) {
/* 2227 */     ArrayList<Widget> focusList = new ArrayList<Widget>();
/* 2228 */     int curIndex = collectFocusOrderList(focusList);
/* 2229 */     if (focusList.isEmpty()) {
/* 2230 */       return false;
/*      */     }
/* 2232 */     if (dir < 0) {
/* 2233 */       if (!relative || --curIndex < 0) {
/* 2234 */         curIndex = focusList.size() - 1;
/*      */       }
/* 2236 */     } else if (!relative || ++curIndex >= focusList.size()) {
/* 2237 */       curIndex = 0;
/*      */     } 
/* 2239 */     Widget widget = focusList.get(curIndex);
/*      */     try {
/* 2241 */       widget.focusGainedCause = FocusGainedCause.FOCUS_KEY;
/* 2242 */       widget.requestKeyboardFocus(null);
/* 2243 */       widget.requestKeyboardFocus();
/*      */     } finally {
/* 2245 */       widget.focusGainedCause = null;
/*      */     } 
/* 2247 */     return true;
/*      */   }
/*      */   
/*      */   private boolean focusTransferStart() {
/* 2251 */     Widget[] fti = focusTransferInfo.get();
/* 2252 */     if (fti == null) {
/* 2253 */       Widget root = getRootWidget();
/* 2254 */       Widget w = root;
/* 2255 */       while (w.focusChild != null) {
/* 2256 */         w = w.focusChild;
/*      */       }
/* 2258 */       if (w == root) {
/* 2259 */         w = null;
/*      */       }
/* 2261 */       focusTransferInfo.set(new Widget[] { w });
/* 2262 */       return true;
/*      */     } 
/* 2264 */     return false;
/*      */   }
/*      */   
/*      */   private void focusTransferClear(boolean clear) {
/* 2268 */     if (clear) {
/* 2269 */       focusTransferInfo.set(null);
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
/*      */   protected final Widget getChildAt(int x, int y) {
/* 2283 */     if (this.children != null) {
/* 2284 */       for (int i = this.children.size(); i-- > 0; ) {
/* 2285 */         Widget child = this.children.get(i);
/* 2286 */         if (child.visible && child.isInside(x, y)) {
/* 2287 */           return child;
/*      */         }
/*      */       } 
/*      */     }
/* 2291 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateTintAnimation() {
/* 2301 */     this.tintAnimator.update();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final void firePropertyChange(PropertyChangeEvent evt) {
/* 2311 */     if (this.propertyChangeSupport != null) {
/* 2312 */       this.propertyChangeSupport.firePropertyChange(evt);
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
/*      */   protected final void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
/* 2325 */     if (this.propertyChangeSupport != null) {
/* 2326 */       this.propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
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
/*      */   protected final void firePropertyChange(String propertyName, int oldValue, int newValue) {
/* 2339 */     if (this.propertyChangeSupport != null) {
/* 2340 */       this.propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
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
/*      */   protected final void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
/* 2353 */     if (this.propertyChangeSupport != null) {
/* 2354 */       this.propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setParent(Widget parent) {
/* 2363 */     this.parent = parent;
/*      */   }
/*      */   
/*      */   private void unparentChild(Widget child) {
/* 2367 */     GUI gui = getGUI();
/* 2368 */     if (child.hasOpenPopup) {
/* 2369 */       assert gui != null;
/* 2370 */       gui.closePopupFromWidgets(child);
/*      */     } 
/* 2372 */     recursivelyChildFocusLost(child);
/* 2373 */     if (gui != null) {
/* 2374 */       child.recursivelyRemoveFromGUI(gui);
/*      */     }
/* 2376 */     child.recursivelyClearGUI(gui);
/* 2377 */     child.parent = null;
/*      */     try {
/* 2379 */       child.destroy();
/* 2380 */     } catch (Exception ex) {
/* 2381 */       getLogger().log(Level.SEVERE, "Exception in destroy()", ex);
/*      */     } 
/* 2383 */     adjustChildPosition(child, -this.posX, -this.posY);
/* 2384 */     child.recursivelyEnabledChanged(null, child.locallyEnabled);
/*      */   }
/*      */   
/*      */   private void recursivelySetGUI(GUI gui) {
/* 2388 */     assert this.guiInstance == null : "guiInstance must be null";
/* 2389 */     this.guiInstance = gui;
/* 2390 */     if (this.children != null) {
/* 2391 */       for (int i = this.children.size(); i-- > 0;) {
/* 2392 */         ((Widget)this.children.get(i)).recursivelySetGUI(gui);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void recursivelyAddToGUI(GUI gui) {
/* 2398 */     assert this.guiInstance == gui : "guiInstance must be equal to gui";
/* 2399 */     if (this.layoutInvalid != 0) {
/* 2400 */       gui.hasInvalidLayouts = true;
/*      */     }
/* 2402 */     if (!this.sharedAnimState) {
/* 2403 */       this.animState.setGUI(gui);
/*      */     }
/*      */     try {
/* 2406 */       afterAddToGUI(gui);
/* 2407 */     } catch (Exception ex) {
/* 2408 */       getLogger().log(Level.SEVERE, "Exception in afterAddToGUI()", ex);
/*      */     } 
/* 2410 */     if (this.children != null) {
/* 2411 */       for (int i = this.children.size(); i-- > 0;) {
/* 2412 */         ((Widget)this.children.get(i)).recursivelyAddToGUI(gui);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void recursivelyClearGUI(GUI gui) {
/* 2418 */     assert this.guiInstance == gui : "guiInstance must be null";
/* 2419 */     this.guiInstance = null;
/* 2420 */     this.themeManager = null;
/* 2421 */     if (this.children != null) {
/* 2422 */       for (int i = this.children.size(); i-- > 0;) {
/* 2423 */         ((Widget)this.children.get(i)).recursivelyClearGUI(gui);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void recursivelyRemoveFromGUI(GUI gui) {
/* 2429 */     assert this.guiInstance == gui : "guiInstance must be equal to gui";
/* 2430 */     if (this.children != null) {
/* 2431 */       for (int i = this.children.size(); i-- > 0;) {
/* 2432 */         ((Widget)this.children.get(i)).recursivelyRemoveFromGUI(gui);
/*      */       }
/*      */     }
/* 2435 */     this.focusChild = null;
/* 2436 */     if (!this.sharedAnimState) {
/* 2437 */       this.animState.setGUI(null);
/*      */     }
/*      */     try {
/* 2440 */       beforeRemoveFromGUI(gui);
/* 2441 */     } catch (Exception ex) {
/* 2442 */       getLogger().log(Level.SEVERE, "Exception in beforeRemoveFromGUI()", ex);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void recursivelyChildFocusLost(Widget w) {
/* 2447 */     while (w != null) {
/* 2448 */       Widget next = w.focusChild;
/* 2449 */       if (!w.sharedAnimState) {
/* 2450 */         w.animState.setAnimationState(STATE_KEYBOARD_FOCUS, false);
/*      */       }
/*      */       try {
/* 2453 */         w.keyboardFocusLost();
/* 2454 */       } catch (Exception ex) {
/* 2455 */         getLogger().log(Level.SEVERE, "Exception in keyboardFocusLost()", ex);
/*      */       } 
/* 2457 */       w.focusChild = null;
/* 2458 */       w = next;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void recursivelyEnabledChanged(GUI gui, boolean enabled) {
/* 2463 */     enabled &= this.locallyEnabled;
/* 2464 */     if (this.enabled != enabled) {
/* 2465 */       this.enabled = enabled;
/* 2466 */       if (!this.sharedAnimState) {
/* 2467 */         getAnimationState().setAnimationState(STATE_DISABLED, !enabled);
/*      */       }
/* 2469 */       if (!enabled) {
/* 2470 */         if (gui != null) {
/* 2471 */           gui.widgetDisabled(this);
/*      */         }
/*      */         try {
/* 2474 */           widgetDisabled();
/* 2475 */         } catch (Exception ex) {
/* 2476 */           getLogger().log(Level.SEVERE, "Exception in widgetDisabled()", ex);
/*      */         } 
/*      */         try {
/* 2479 */           giveupKeyboardFocus();
/* 2480 */         } catch (Exception ex) {
/* 2481 */           getLogger().log(Level.SEVERE, "Exception in giveupKeyboardFocus()", ex);
/*      */         } 
/*      */       } 
/*      */       try {
/* 2485 */         firePropertyChange("enabled", !enabled, enabled);
/* 2486 */       } catch (Exception ex) {
/* 2487 */         getLogger().log(Level.SEVERE, "Exception in firePropertyChange(\"enabled\")", ex);
/*      */       } 
/* 2489 */       if (this.children != null) {
/* 2490 */         for (int i = this.children.size(); i-- > 0; ) {
/* 2491 */           Widget child = this.children.get(i);
/* 2492 */           child.recursivelyEnabledChanged(gui, enabled);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void childHidden(Widget child) {
/* 2499 */     if (this.focusChild == child) {
/* 2500 */       recursivelyChildFocusLost(this.focusChild);
/* 2501 */       this.focusChild = null;
/*      */     } 
/* 2503 */     if (this.lastChildMouseOver == child) {
/* 2504 */       this.lastChildMouseOver = null;
/*      */     }
/*      */   }
/*      */   
/*      */   final void setOpenPopup(GUI gui, boolean hasOpenPopup) {
/* 2509 */     if (this.hasOpenPopup != hasOpenPopup) {
/* 2510 */       this.hasOpenPopup = hasOpenPopup;
/* 2511 */       if (!this.sharedAnimState) {
/* 2512 */         getAnimationState().setAnimationState(STATE_HAS_OPEN_POPUPS, hasOpenPopup);
/*      */       }
/* 2514 */       if (this.parent != null) {
/* 2515 */         if (hasOpenPopup) {
/* 2516 */           this.parent.setOpenPopup(gui, true);
/*      */         } else {
/* 2518 */           this.parent.recalcOpenPopups(gui);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   final void recalcOpenPopups(GUI gui) {
/* 2526 */     if (gui.hasOpenPopups(this)) {
/* 2527 */       setOpenPopup(gui, true);
/*      */       
/*      */       return;
/*      */     } 
/* 2531 */     if (this.children != null) {
/* 2532 */       for (int i = this.children.size(); i-- > 0;) {
/* 2533 */         if (((Widget)this.children.get(i)).hasOpenPopup) {
/* 2534 */           setOpenPopup(gui, true);
/*      */           return;
/*      */         } 
/*      */       } 
/*      */     }
/* 2539 */     setOpenPopup(gui, false);
/*      */   }
/*      */   
/*      */   final boolean isLayoutInvalid() {
/* 2543 */     return (this.layoutInvalid != 0);
/*      */   }
/*      */   
/*      */   final void drawWidget(GUI gui) {
/* 2547 */     if (this.renderOffscreen != null) {
/* 2548 */       drawWidgetOffscreen(gui);
/*      */       return;
/*      */     } 
/* 2551 */     if (this.tintAnimator != null && this.tintAnimator.hasTint()) {
/* 2552 */       drawWidgetTint(gui);
/*      */       return;
/*      */     } 
/* 2555 */     if (this.clip) {
/* 2556 */       drawWidgetClip(gui);
/*      */       return;
/*      */     } 
/* 2559 */     paint(gui);
/*      */   }
/*      */   
/*      */   private void drawWidgetTint(GUI gui) {
/* 2563 */     if (this.tintAnimator.isFadeActive()) {
/* 2564 */       updateTintAnimation();
/*      */     }
/* 2566 */     Renderer renderer = gui.getRenderer();
/* 2567 */     this.tintAnimator.paintWithTint(renderer);
/*      */     try {
/* 2569 */       if (this.clip) {
/* 2570 */         drawWidgetClip(gui);
/*      */       } else {
/* 2572 */         paint(gui);
/*      */       } 
/*      */     } finally {
/* 2575 */       renderer.popGlobalTintColor();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void drawWidgetClip(GUI gui) {
/* 2580 */     Renderer renderer = gui.getRenderer();
/* 2581 */     renderer.clipEnter(this.posX, this.posY, this.width, this.height);
/*      */     try {
/* 2583 */       paint(gui);
/*      */     } finally {
/* 2585 */       renderer.clipLeave();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void drawWidgetOffscreen(GUI gui) {
/* 2590 */     RenderOffscreen ro = this.renderOffscreen;
/* 2591 */     Renderer renderer = gui.getRenderer();
/* 2592 */     OffscreenRenderer offscreenRenderer = renderer.getOffscreenRenderer();
/* 2593 */     if (offscreenRenderer != null) {
/* 2594 */       int extraTop = this.offscreenExtraTop;
/* 2595 */       int extraLeft = this.offscreenExtraLeft;
/* 2596 */       int extraRight = this.offscreenExtraRight;
/* 2597 */       int extraBottom = this.offscreenExtraBottom;
/* 2598 */       int[] effectExtra = ro.getEffectExtraArea(this);
/* 2599 */       if (effectExtra != null) {
/* 2600 */         extraTop += effectExtra[0];
/* 2601 */         extraLeft += effectExtra[1];
/* 2602 */         extraRight += effectExtra[2];
/* 2603 */         extraBottom += effectExtra[3];
/*      */       } 
/* 2605 */       if (this.offscreenSurface != null && !ro.needPainting(gui, this.parent, this.offscreenSurface)) {
/* 2606 */         ro.paintOffscreenSurface(gui, this, this.offscreenSurface);
/*      */         return;
/*      */       } 
/* 2609 */       this.offscreenSurface = offscreenRenderer.startOffscreenRendering(this, this.offscreenSurface, this.posX - extraLeft, this.posY - extraTop, this.width + extraLeft + extraRight, this.height + extraTop + extraBottom);
/*      */ 
/*      */       
/* 2612 */       if (this.offscreenSurface != null) {
/*      */         try {
/* 2614 */           if (this.tintAnimator != null && this.tintAnimator.hasTint()) {
/* 2615 */             drawWidgetTint(gui);
/*      */           } else {
/* 2617 */             paint(gui);
/*      */           } 
/*      */         } finally {
/* 2620 */           offscreenRenderer.endOffscreenRendering();
/*      */         } 
/* 2622 */         ro.paintOffscreenSurface(gui, this, this.offscreenSurface);
/*      */         return;
/*      */       } 
/*      */     } 
/* 2626 */     this.renderOffscreen = null;
/* 2627 */     ro.offscreenRenderingFailed(this);
/* 2628 */     drawWidget(gui);
/*      */   }
/*      */   
/*      */   Widget getWidgetUnderMouse() {
/* 2632 */     if (!this.visible) {
/* 2633 */       return null;
/*      */     }
/* 2635 */     Widget w = this;
/* 2636 */     while (w.lastChildMouseOver != null && w.visible) {
/* 2637 */       w = w.lastChildMouseOver;
/*      */     }
/* 2639 */     return w;
/*      */   }
/*      */   
/*      */   private static void adjustChildPosition(Widget child, int deltaX, int deltaY) {
/* 2643 */     child.setPositionImpl(child.posX + deltaX, child.posY + deltaY);
/*      */   }
/*      */   
/*      */   final boolean setPositionImpl(int x, int y) {
/* 2647 */     int deltaX = x - this.posX;
/* 2648 */     int deltaY = y - this.posY;
/* 2649 */     if (deltaX != 0 || deltaY != 0) {
/* 2650 */       this.posX = x;
/* 2651 */       this.posY = y;
/*      */       
/* 2653 */       if (this.children != null) {
/* 2654 */         for (int i = 0, n = this.children.size(); i < n; i++) {
/* 2655 */           adjustChildPosition(this.children.get(i), deltaX, deltaY);
/*      */         }
/*      */       }
/*      */       
/* 2659 */       positionChanged();
/*      */       
/* 2661 */       if (this.propertyChangeSupport != null) {
/* 2662 */         firePropertyChange("x", x - deltaX, x);
/* 2663 */         firePropertyChange("y", y - deltaY, y);
/*      */       } 
/* 2665 */       return true;
/*      */     } 
/* 2667 */     return false;
/*      */   }
/*      */   
/*      */   void applyTheme(ThemeManager themeManager) {
/* 2671 */     this.themeManager = themeManager;
/*      */     
/* 2673 */     String themePath = getThemePath();
/* 2674 */     if (themePath.length() == 0) {
/* 2675 */       if (this.children != null) {
/* 2676 */         for (int i = 0, n = this.children.size(); i < n; i++) {
/* 2677 */           ((Widget)this.children.get(i)).applyTheme(themeManager);
/*      */         }
/*      */       }
/*      */       
/*      */       return;
/*      */     } 
/* 2683 */     DebugHook hook = DebugHook.getDebugHook();
/* 2684 */     hook.beforeApplyTheme(this);
/*      */     
/* 2686 */     ThemeInfo themeInfo = null;
/*      */     try {
/* 2688 */       themeInfo = themeManager.findThemeInfo(themePath);
/* 2689 */       if (themeInfo != null && this.theme.length() > 0) {
/*      */         try {
/* 2691 */           applyTheme(themeInfo);
/* 2692 */         } catch (Exception ex) {
/* 2693 */           getLogger().log(Level.SEVERE, "Exception in applyTheme()", ex);
/*      */         } 
/*      */       }
/*      */     } finally {
/* 2697 */       hook.afterApplyTheme(this);
/*      */     } 
/*      */     
/* 2700 */     applyThemeToChildren(themeManager, themeInfo, hook);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isAbsoluteTheme(String theme) {
/* 2711 */     return (theme.length() > 1 && theme.charAt(0) == '/');
/*      */   }
/*      */   
/*      */   private void applyThemeImpl(ThemeManager themeManager, ThemeInfo themeInfo, DebugHook hook) {
/* 2715 */     this.themeManager = themeManager;
/* 2716 */     if (this.theme.length() > 0) {
/* 2717 */       hook.beforeApplyTheme(this);
/*      */       try {
/* 2719 */         if (isAbsoluteTheme(this.theme)) {
/* 2720 */           themeInfo = themeManager.findThemeInfo(this.theme.substring(1));
/*      */         } else {
/* 2722 */           themeInfo = themeInfo.getChildTheme(this.theme);
/*      */         } 
/* 2724 */         if (themeInfo != null) {
/*      */           try {
/* 2726 */             applyTheme(themeInfo);
/* 2727 */           } catch (Exception ex) {
/* 2728 */             getLogger().log(Level.SEVERE, "Exception in applyTheme()", ex);
/*      */           } 
/*      */         }
/*      */       } finally {
/* 2732 */         hook.afterApplyTheme(this);
/*      */       } 
/*      */     } 
/* 2735 */     applyThemeToChildren(themeManager, themeInfo, hook);
/*      */   }
/*      */   
/*      */   private void applyThemeToChildren(ThemeManager themeManager, ThemeInfo themeInfo, DebugHook hook) {
/* 2739 */     if (this.children != null && themeInfo != null) {
/* 2740 */       for (int i = 0, n = this.children.size(); i < n; i++) {
/* 2741 */         Widget child = this.children.get(i);
/* 2742 */         child.applyThemeImpl(themeManager, themeInfo, hook);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private StringBuilder getThemePath(int length) {
/*      */     StringBuilder sb;
/* 2749 */     length += this.theme.length();
/* 2750 */     boolean abs = isAbsoluteTheme(this.theme);
/* 2751 */     if (this.parent != null && !abs) {
/* 2752 */       sb = this.parent.getThemePath(length + 1);
/* 2753 */       if (this.theme.length() > 0 && sb.length() > 0) {
/* 2754 */         sb.append('.');
/*      */       }
/*      */     } else {
/* 2757 */       sb = new StringBuilder(length);
/*      */     } 
/* 2759 */     if (abs) {
/* 2760 */       return sb.append(this.theme.substring(1));
/*      */     }
/* 2762 */     return sb.append(this.theme);
/*      */   }
/*      */   
/*      */   Event translateMouseEvent(Event evt) {
/* 2766 */     if (this.renderOffscreen instanceof OffscreenMouseAdjustments) {
/* 2767 */       int[] newXY = ((OffscreenMouseAdjustments)this.renderOffscreen).adjustMouseCoordinates(this, evt);
/* 2768 */       evt = evt.createSubEvent(newXY[0], newXY[1]);
/*      */     } 
/* 2770 */     return evt;
/*      */   }
/*      */   
/*      */   Widget routeMouseEvent(Event evt) {
/* 2774 */     assert !evt.isMouseDragEvent();
/* 2775 */     evt = translateMouseEvent(evt);
/* 2776 */     if (this.children != null) {
/* 2777 */       for (int i = this.children.size(); i-- > 0; ) {
/* 2778 */         Widget child = this.children.get(i);
/* 2779 */         if (child.visible && child.isMouseInside(evt))
/*      */         {
/* 2781 */           if (setMouseOverChild(child, evt)) {
/* 2782 */             if (evt.getType() == Event.Type.MOUSE_ENTERED || evt.getType() == Event.Type.MOUSE_EXITED)
/*      */             {
/* 2784 */               return child;
/*      */             }
/* 2786 */             Widget result = child.routeMouseEvent(evt);
/* 2787 */             if (result != null) {
/*      */ 
/*      */ 
/*      */               
/* 2791 */               if (evt.getType() == Event.Type.MOUSE_BTNDOWN && this.focusChild != child) {
/*      */                 try {
/* 2793 */                   child.focusGainedCause = FocusGainedCause.MOUSE_BTNDOWN;
/* 2794 */                   if (child.isEnabled() && child.canAcceptKeyboardFocus()) {
/* 2795 */                     requestKeyboardFocus(child);
/*      */                   }
/*      */                 } finally {
/* 2798 */                   child.focusGainedCause = null;
/*      */                 } 
/*      */               }
/* 2801 */               return result;
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2813 */     if (evt.getType() == Event.Type.MOUSE_BTNDOWN && isEnabled() && canAcceptKeyboardFocus()) {
/*      */       try {
/* 2815 */         this.focusGainedCause = FocusGainedCause.MOUSE_BTNDOWN;
/* 2816 */         if (this.focusChild == null) {
/* 2817 */           requestKeyboardFocus();
/*      */         } else {
/* 2819 */           requestKeyboardFocus(null);
/*      */         } 
/*      */       } finally {
/* 2822 */         this.focusGainedCause = null;
/*      */       } 
/*      */     }
/* 2825 */     if (evt.getType() != Event.Type.MOUSE_WHEEL)
/*      */     {
/* 2827 */       setMouseOverChild(null, evt);
/*      */     }
/* 2829 */     if (!isEnabled() && isMouseAction(evt)) {
/* 2830 */       return this;
/*      */     }
/* 2832 */     if (handleEvent(evt)) {
/* 2833 */       return this;
/*      */     }
/* 2835 */     return null;
/*      */   }
/*      */   
/*      */   static boolean isMouseAction(Event evt) {
/* 2839 */     Event.Type type = evt.getType();
/* 2840 */     return (type == Event.Type.MOUSE_BTNDOWN || type == Event.Type.MOUSE_BTNUP || type == Event.Type.MOUSE_CLICKED || type == Event.Type.MOUSE_DRAGGED);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void routePopupEvent(Event evt) {
/* 2847 */     handleEvent(evt);
/* 2848 */     if (this.children != null) {
/* 2849 */       for (int i = 0, n = this.children.size(); i < n; i++) {
/* 2850 */         ((Widget)this.children.get(i)).routePopupEvent(evt);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   static boolean getSafeBooleanProperty(String name) {
/*      */     try {
/* 2857 */       return Boolean.getBoolean(name);
/* 2858 */     } catch (AccessControlException ex) {
/* 2859 */       return false;
/*      */     } 
/*      */   }
/*      */   
/* 2863 */   private static final boolean WARN_ON_UNHANDLED_ACTION = getSafeBooleanProperty("warnOnUnhandledAction");
/*      */   
/*      */   private boolean handleKeyEvent(Event evt) {
/* 2866 */     if (this.children != null) {
/* 2867 */       if (this.focusKeyEnabled && this.guiInstance != null) {
/* 2868 */         this.guiInstance.setFocusKeyWidget(this);
/*      */       }
/* 2870 */       if (this.focusChild != null && this.focusChild.isVisible() && 
/* 2871 */         this.focusChild.handleEvent(evt)) {
/* 2872 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 2876 */     if (this.inputMap != null) {
/* 2877 */       String action = this.inputMap.mapEvent(evt);
/* 2878 */       if (action != null) {
/* 2879 */         if (handleKeyStrokeAction(action, evt)) {
/* 2880 */           return true;
/*      */         }
/* 2882 */         if (WARN_ON_UNHANDLED_ACTION) {
/* 2883 */           Logger.getLogger(getClass().getName()).log(Level.WARNING, "Unhandled action ''{0}'' for class ''{1}''", new Object[] { action, getClass().getName() });
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2889 */     return false;
/*      */   }
/*      */   
/*      */   void handleFocusKeyEvent(Event evt) {
/* 2893 */     if (evt.isKeyPressedEvent()) {
/* 2894 */       if ((evt.getModifiers() & 0x9) != 0) {
/* 2895 */         focusPrevChild();
/*      */       } else {
/* 2897 */         focusNextChild();
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   boolean setMouseOverChild(Widget child, Event evt) {
/* 2903 */     if (this.lastChildMouseOver != child) {
/* 2904 */       if (child != null) {
/* 2905 */         Widget result = child.routeMouseEvent(evt.createSubEvent(Event.Type.MOUSE_ENTERED));
/* 2906 */         if (result == null)
/*      */         {
/* 2908 */           return false;
/*      */         }
/*      */       } 
/* 2911 */       if (this.lastChildMouseOver != null) {
/* 2912 */         this.lastChildMouseOver.routeMouseEvent(evt.createSubEvent(Event.Type.MOUSE_EXITED));
/*      */       }
/* 2914 */       this.lastChildMouseOver = child;
/*      */     } 
/* 2916 */     return true;
/*      */   }
/*      */   
/*      */   void collectLayoutLoop(ArrayList<Widget> result) {
/* 2920 */     if (this.layoutInvalid != 0) {
/* 2921 */       result.add(this);
/*      */     }
/* 2923 */     if (this.children != null) {
/* 2924 */       for (int i = 0, n = this.children.size(); i < n; i++) {
/* 2925 */         ((Widget)this.children.get(i)).collectLayoutLoop(result);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private PropertyChangeSupport createPropertyChangeSupport() {
/* 2931 */     if (this.propertyChangeSupport == null) {
/* 2932 */       this.propertyChangeSupport = new PropertyChangeSupport(this);
/*      */     }
/* 2934 */     return this.propertyChangeSupport;
/*      */   }
/*      */   
/*      */   private Logger getLogger() {
/* 2938 */     return Logger.getLogger(Widget.class.getName());
/*      */   }
/*      */   
/*      */   public static interface OffscreenMouseAdjustments extends RenderOffscreen {
/*      */     int[] adjustMouseCoordinates(Widget param1Widget, Event param1Event);
/*      */   }
/*      */   
/*      */   public static interface RenderOffscreen {
/*      */     void paintOffscreenSurface(GUI param1GUI, Widget param1Widget, OffscreenSurface param1OffscreenSurface);
/*      */     
/*      */     void offscreenRenderingFailed(Widget param1Widget);
/*      */     
/*      */     int[] getEffectExtraArea(Widget param1Widget);
/*      */     
/*      */     boolean needPainting(GUI param1GUI, Widget param1Widget, OffscreenSurface param1OffscreenSurface);
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Widget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */