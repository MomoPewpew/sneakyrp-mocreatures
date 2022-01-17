/*      */ package de.matthiasmann.twl;
/*      */ 
/*      */ import de.matthiasmann.twl.model.AutoCompletionDataSource;
/*      */ import de.matthiasmann.twl.model.DefaultEditFieldModel;
/*      */ import de.matthiasmann.twl.model.EditFieldModel;
/*      */ import de.matthiasmann.twl.model.ObservableCharSequence;
/*      */ import de.matthiasmann.twl.model.StringAttributes;
/*      */ import de.matthiasmann.twl.model.StringModel;
/*      */ import de.matthiasmann.twl.renderer.AnimationState;
/*      */ import de.matthiasmann.twl.renderer.AttributedString;
/*      */ import de.matthiasmann.twl.renderer.AttributedStringFontCache;
/*      */ import de.matthiasmann.twl.renderer.Font;
/*      */ import de.matthiasmann.twl.renderer.Font2;
/*      */ import de.matthiasmann.twl.renderer.Image;
/*      */ import de.matthiasmann.twl.utils.CallbackSupport;
/*      */ import de.matthiasmann.twl.utils.TextUtil;
/*      */ import java.util.concurrent.ExecutorService;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EditField
/*      */   extends Widget
/*      */ {
/*   62 */   public static final AnimationState.StateKey STATE_ERROR = AnimationState.StateKey.get("error");
/*   63 */   public static final AnimationState.StateKey STATE_READONLY = AnimationState.StateKey.get("readonly");
/*   64 */   public static final AnimationState.StateKey STATE_HOVER = AnimationState.StateKey.get("hover");
/*   65 */   public static final AnimationState.StateKey STATE_CURSOR_MOVED = AnimationState.StateKey.get("cursorMoved");
/*      */   
/*      */   final EditFieldModel editBuffer;
/*      */   
/*      */   public TextRenderer textRenderer;
/*      */   
/*      */   private PasswordMasker passwordMasking;
/*      */   
/*      */   private Runnable modelChangeListener;
/*      */   
/*      */   private StringModel model;
/*      */   
/*      */   private boolean readOnly;
/*      */   
/*      */   StringAttributes attributes;
/*      */   
/*      */   private int cursorPos;
/*      */   
/*      */   int scrollPos;
/*      */   
/*      */   int selectionStart;
/*      */   
/*      */   int selectionEnd;
/*      */   
/*      */   int numberOfLines;
/*      */   
/*      */   boolean multiLine;
/*      */   
/*      */   boolean pendingScrollToCursor;
/*      */   
/*      */   boolean pendingScrollToCursorForce;
/*   96 */   private int maxTextLength = 32767;
/*      */   
/*   98 */   private int columns = 5;
/*      */   
/*      */   private Image cursorImage;
/*      */   Image selectionImage;
/*      */   private char passwordChar;
/*      */   private Object errorMsg;
/*      */   private boolean errorMsgFromModel;
/*      */   private Callback[] callbacks;
/*      */   private Menu popupMenu;
/*      */   private boolean textLongerThenWidget;
/*      */   private boolean forwardUnhandledKeysToCallback;
/*      */   private boolean autoCompletionOnSetText = true;
/*      */   boolean scrollToCursorOnSizeChange = true;
/*      */   private EditFieldAutoCompletionWindow autoCompletionWindow;
/*  112 */   private int autoCompletionHeight = 100;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private InfoWindow errorInfoWindow;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Label errorInfoLabel;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EditField(AnimationState parentAnimationState, EditFieldModel editFieldModel) {
/*  129 */     super(parentAnimationState, true);
/*      */     
/*  131 */     if (editFieldModel == null) {
/*  132 */       throw new NullPointerException("editFieldModel");
/*      */     }
/*      */     
/*  135 */     this.editBuffer = editFieldModel;
/*  136 */     this.textRenderer = new TextRenderer(getAnimationState());
/*  137 */     this.passwordChar = '*';
/*      */     
/*  139 */     this.textRenderer.setTheme("renderer");
/*  140 */     this.textRenderer.setClip(true);
/*      */     
/*  142 */     add(this.textRenderer);
/*  143 */     setCanAcceptKeyboardFocus(true);
/*  144 */     setDepthFocusTraversal(false);
/*      */     
/*  146 */     addActionMapping("cut", "cutToClipboard", new Object[0]);
/*  147 */     addActionMapping("copy", "copyToClipboard", new Object[0]);
/*  148 */     addActionMapping("paste", "pasteFromClipboard", new Object[0]);
/*  149 */     addActionMapping("selectAll", "selectAll", new Object[0]);
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
/*      */   public EditField(AnimationState parentAnimationState) {
/*  163 */     this(parentAnimationState, (EditFieldModel)new DefaultEditFieldModel());
/*      */   }
/*      */   
/*      */   public EditField() {
/*  167 */     this((AnimationState)null);
/*      */   }
/*      */   
/*      */   public void addCallback(Callback cb) {
/*  171 */     this.callbacks = (Callback[])CallbackSupport.addCallbackToList((Object[])this.callbacks, cb, Callback.class);
/*      */   }
/*      */   
/*      */   public void removeCallback(Callback cb) {
/*  175 */     this.callbacks = (Callback[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, cb);
/*      */   }
/*      */   
/*      */   public boolean isForwardUnhandledKeysToCallback() {
/*  179 */     return this.forwardUnhandledKeysToCallback;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setForwardUnhandledKeysToCallback(boolean forwardUnhandledKeysToCallback) {
/*  190 */     this.forwardUnhandledKeysToCallback = forwardUnhandledKeysToCallback;
/*      */   }
/*      */   
/*      */   public boolean isAutoCompletionOnSetText() {
/*  194 */     return this.autoCompletionOnSetText;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoCompletionOnSetText(boolean autoCompletionOnSetText) {
/*  205 */     this.autoCompletionOnSetText = autoCompletionOnSetText;
/*      */   }
/*      */   
/*      */   public boolean isScrollToCursorOnSizeChange() {
/*  209 */     return this.scrollToCursorOnSizeChange;
/*      */   }
/*      */   
/*      */   public void setScrollToCursorOnSizeChange(boolean scrollToCursorOnSizeChange) {
/*  213 */     this.scrollToCursorOnSizeChange = scrollToCursorOnSizeChange;
/*      */   }
/*      */   
/*      */   protected void doCallback(int key) {
/*  217 */     if (this.callbacks != null) {
/*  218 */       for (Callback cb : this.callbacks) {
/*  219 */         cb.callback(key);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean isPasswordMasking() {
/*  225 */     return (this.passwordMasking != null);
/*      */   }
/*      */   
/*      */   public void setPasswordMasking(boolean passwordMasking) {
/*  229 */     if (passwordMasking != isPasswordMasking()) {
/*  230 */       if (passwordMasking) {
/*  231 */         this.passwordMasking = new PasswordMasker((CharSequence)this.editBuffer, this.passwordChar);
/*      */       } else {
/*  233 */         this.passwordMasking = null;
/*      */       } 
/*  235 */       updateTextDisplay();
/*      */     } 
/*      */   }
/*      */   
/*      */   public char getPasswordChar() {
/*  240 */     return this.passwordChar;
/*      */   }
/*      */   
/*      */   public void setPasswordChar(char passwordChar) {
/*  244 */     this.passwordChar = passwordChar;
/*  245 */     if (this.passwordMasking != null && this.passwordMasking.maskingChar != passwordChar) {
/*  246 */       this.passwordMasking = new PasswordMasker((CharSequence)this.editBuffer, passwordChar);
/*  247 */       updateTextDisplay();
/*      */     } 
/*      */   }
/*      */   
/*      */   public int getColumns() {
/*  252 */     return this.columns;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColumns(int columns) {
/*  263 */     if (columns < 0) {
/*  264 */       throw new IllegalArgumentException("columns");
/*      */     }
/*  266 */     this.columns = columns;
/*      */   }
/*      */   
/*      */   public boolean isMultiLine() {
/*  270 */     return this.multiLine;
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
/*      */   public void setMultiLine(boolean multiLine) {
/*  284 */     this.multiLine = multiLine;
/*  285 */     if (!multiLine && this.numberOfLines > 1) {
/*  286 */       setText("");
/*      */     }
/*      */   }
/*      */   
/*      */   public StringModel getModel() {
/*  291 */     return this.model;
/*      */   }
/*      */   
/*      */   public void setModel(StringModel model) {
/*  295 */     removeModelChangeListener();
/*  296 */     if (this.model != null) {
/*  297 */       this.model.removeCallback(this.modelChangeListener);
/*      */     }
/*  299 */     this.model = model;
/*  300 */     if (getGUI() != null) {
/*  301 */       addModelChangeListener();
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
/*      */   public void setText(String text) {
/*  318 */     setText(text, false);
/*      */   }
/*      */   
/*      */   void setText(String text, boolean fromModel) {
/*  322 */     text = TextUtil.limitStringLength(text, this.maxTextLength);
/*  323 */     this.editBuffer.replace(0, this.editBuffer.length(), text);
/*  324 */     this.cursorPos = this.multiLine ? 0 : this.editBuffer.length();
/*  325 */     this.selectionStart = 0;
/*  326 */     this.selectionEnd = 0;
/*  327 */     updateSelection();
/*  328 */     updateText(this.autoCompletionOnSetText, fromModel, 0);
/*  329 */     scrollToCursor(true);
/*      */   }
/*      */   
/*      */   public String getText() {
/*  333 */     return this.editBuffer.toString();
/*      */   }
/*      */   
/*      */   public StringAttributes getStringAttributes() {
/*  337 */     if (this.attributes == null) {
/*  338 */       this.textRenderer.setCache(false);
/*  339 */       this.attributes = new StringAttributes((ObservableCharSequence)this.editBuffer, getAnimationState());
/*      */     } 
/*  341 */     return this.attributes;
/*      */   }
/*      */   
/*      */   public void disableStringAttributes() {
/*  345 */     if (this.attributes != null) {
/*  346 */       this.attributes = null;
/*      */     }
/*      */   }
/*      */   
/*      */   public String getSelectedText() {
/*  351 */     return this.editBuffer.substring(this.selectionStart, this.selectionEnd);
/*      */   }
/*      */   
/*      */   public boolean hasSelection() {
/*  355 */     return (this.selectionStart != this.selectionEnd);
/*      */   }
/*      */   
/*      */   public int getCursorPos() {
/*  359 */     return this.cursorPos;
/*      */   }
/*      */   
/*      */   public int getTextLength() {
/*  363 */     return this.editBuffer.length();
/*      */   }
/*      */   
/*      */   public boolean isReadOnly() {
/*  367 */     return this.readOnly;
/*      */   }
/*      */   
/*      */   public void setReadOnly(boolean readOnly) {
/*  371 */     if (this.readOnly != readOnly) {
/*  372 */       this.readOnly = readOnly;
/*  373 */       this.popupMenu = null;
/*  374 */       getAnimationState().setAnimationState(STATE_READONLY, readOnly);
/*  375 */       firePropertyChange("readonly", !readOnly, readOnly);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void insertText(String str) {
/*  380 */     if (!this.readOnly) {
/*  381 */       boolean update = false;
/*  382 */       if (hasSelection()) {
/*  383 */         deleteSelection();
/*  384 */         update = true;
/*      */       } 
/*  386 */       int insertLength = Math.min(str.length(), this.maxTextLength - this.editBuffer.length());
/*  387 */       if (insertLength > 0) {
/*  388 */         int inserted = this.editBuffer.replace(this.cursorPos, 0, str.substring(0, insertLength));
/*  389 */         if (inserted > 0) {
/*  390 */           this.cursorPos += inserted;
/*  391 */           update = true;
/*      */         } 
/*      */       } 
/*  394 */       if (update) {
/*  395 */         updateText(true, false, 0);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void pasteFromClipboard() {
/*  401 */     String cbText = Clipboard.getClipboard();
/*  402 */     if (cbText != null) {
/*  403 */       if (!this.multiLine) {
/*  404 */         cbText = TextUtil.stripNewLines(cbText);
/*      */       }
/*  406 */       insertText(cbText);
/*      */     } 
/*      */   }
/*      */   
/*      */   public void copyToClipboard() {
/*      */     String text;
/*  412 */     if (hasSelection()) {
/*  413 */       text = getSelectedText();
/*      */     } else {
/*  415 */       text = getText();
/*      */     } 
/*  417 */     if (isPasswordMasking()) {
/*  418 */       text = TextUtil.createString(this.passwordChar, text.length());
/*      */     }
/*  420 */     Clipboard.setClipboard(text);
/*      */   }
/*      */ 
/*      */   
/*      */   public void cutToClipboard() {
/*  425 */     if (!hasSelection()) {
/*  426 */       selectAll();
/*      */     }
/*  428 */     String text = getSelectedText();
/*  429 */     if (!this.readOnly) {
/*  430 */       deleteSelection();
/*  431 */       updateText(true, false, 211);
/*      */     } 
/*  433 */     if (isPasswordMasking()) {
/*  434 */       text = TextUtil.createString(this.passwordChar, text.length());
/*      */     }
/*  436 */     Clipboard.setClipboard(text);
/*      */   }
/*      */   
/*      */   public int getMaxTextLength() {
/*  440 */     return this.maxTextLength;
/*      */   }
/*      */   
/*      */   public void setMaxTextLength(int maxTextLength) {
/*  444 */     this.maxTextLength = maxTextLength;
/*      */   }
/*      */   
/*      */   void removeModelChangeListener() {
/*  448 */     if (this.model != null && this.modelChangeListener != null) {
/*  449 */       this.model.removeCallback(this.modelChangeListener);
/*      */     }
/*      */   }
/*      */   
/*      */   void addModelChangeListener() {
/*  454 */     if (this.model != null) {
/*  455 */       if (this.modelChangeListener == null) {
/*  456 */         this.modelChangeListener = new ModelChangeListener();
/*      */       }
/*  458 */       this.model.addCallback(this.modelChangeListener);
/*  459 */       modelChanged();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void afterAddToGUI(GUI gui) {
/*  465 */     super.afterAddToGUI(gui);
/*  466 */     addModelChangeListener();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void beforeRemoveFromGUI(GUI gui) {
/*  471 */     removeModelChangeListener();
/*  472 */     super.beforeRemoveFromGUI(gui);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyTheme(ThemeInfo themeInfo) {
/*  477 */     super.applyTheme(themeInfo);
/*  478 */     applyThemeEditField(themeInfo);
/*      */   }
/*      */   
/*      */   protected void applyThemeEditField(ThemeInfo themeInfo) {
/*  482 */     this.cursorImage = themeInfo.getImage("cursor");
/*  483 */     this.selectionImage = themeInfo.getImage("selection");
/*  484 */     this.autoCompletionHeight = themeInfo.getParameter("autocompletion-height", 100);
/*  485 */     this.columns = themeInfo.getParameter("columns", 5);
/*  486 */     setPasswordChar((char)themeInfo.getParameter("passwordChar", 42));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void layout() {
/*  491 */     layoutChildFullInnerArea(this.textRenderer);
/*  492 */     checkTextWidth();
/*  493 */     layoutInfoWindows();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void positionChanged() {
/*  498 */     layoutInfoWindows();
/*      */   }
/*      */   
/*      */   private void layoutInfoWindows() {
/*  502 */     if (this.autoCompletionWindow != null) {
/*  503 */       layoutAutocompletionWindow();
/*      */     }
/*  505 */     if (this.errorInfoWindow != null) {
/*  506 */       layoutErrorInfoWindow();
/*      */     }
/*      */   }
/*      */   
/*      */   private void layoutAutocompletionWindow() {
/*  511 */     int y = getBottom();
/*  512 */     GUI gui = getGUI();
/*  513 */     if (gui != null && 
/*  514 */       y + this.autoCompletionHeight > gui.getInnerBottom()) {
/*  515 */       int ytop = getY() - this.autoCompletionHeight;
/*  516 */       if (ytop >= gui.getInnerY()) {
/*  517 */         y = ytop;
/*      */       }
/*      */     } 
/*      */     
/*  521 */     this.autoCompletionWindow.setPosition(getX(), y);
/*  522 */     this.autoCompletionWindow.setSize(getWidth(), this.autoCompletionHeight);
/*      */   }
/*      */   
/*      */   private int computeInnerWidth() {
/*  526 */     if (this.columns > 0) {
/*  527 */       Font font = getFont();
/*  528 */       if (font != null) {
/*  529 */         return font.computeTextWidth("X") * this.columns;
/*      */       }
/*      */     } 
/*  532 */     return 0;
/*      */   }
/*      */   
/*      */   private int computeInnerHeight() {
/*  536 */     int lineHeight = getLineHeight();
/*  537 */     if (this.multiLine) {
/*  538 */       return lineHeight * this.numberOfLines;
/*      */     }
/*  540 */     return lineHeight;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMinWidth() {
/*  545 */     int minWidth = super.getMinWidth();
/*  546 */     minWidth = Math.max(minWidth, computeInnerWidth() + getBorderHorizontal());
/*  547 */     return minWidth;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMinHeight() {
/*  552 */     int minHeight = super.getMinHeight();
/*  553 */     minHeight = Math.max(minHeight, computeInnerHeight() + getBorderVertical());
/*  554 */     return minHeight;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPreferredInnerWidth() {
/*  559 */     return computeInnerWidth();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPreferredInnerHeight() {
/*  564 */     return computeInnerHeight();
/*      */   }
/*      */   
/*      */   public void setErrorMessage(Object errorMsg) {
/*  568 */     this.errorMsgFromModel = false;
/*  569 */     getAnimationState().setAnimationState(STATE_ERROR, (errorMsg != null));
/*  570 */     if (this.errorMsg != errorMsg) {
/*  571 */       this.errorMsg = errorMsg;
/*  572 */       updateTooltip();
/*      */     } 
/*  574 */     if (errorMsg != null) {
/*  575 */       if (hasKeyboardFocus()) {
/*  576 */         openErrorInfoWindow();
/*      */       }
/*  578 */     } else if (this.errorInfoWindow != null) {
/*  579 */       this.errorInfoWindow.closeInfo();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Object getTooltipContent() {
/*  585 */     if (this.errorMsg != null) {
/*  586 */       return this.errorMsg;
/*      */     }
/*  588 */     Object tooltip = super.getTooltipContent();
/*  589 */     if (tooltip == null && !isPasswordMasking() && this.textLongerThenWidget && !hasKeyboardFocus()) {
/*  590 */       tooltip = getText();
/*      */     }
/*  592 */     return tooltip;
/*      */   }
/*      */   
/*      */   public void setAutoCompletionWindow(EditFieldAutoCompletionWindow window) {
/*  596 */     if (this.autoCompletionWindow != window) {
/*  597 */       if (this.autoCompletionWindow != null) {
/*  598 */         this.autoCompletionWindow.closeInfo();
/*      */       }
/*  600 */       this.autoCompletionWindow = window;
/*      */     } 
/*      */   }
/*      */   
/*      */   public EditFieldAutoCompletionWindow getAutoCompletionWindow() {
/*  605 */     return this.autoCompletionWindow;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoCompletion(AutoCompletionDataSource dataSource) {
/*  615 */     if (dataSource == null) {
/*  616 */       setAutoCompletionWindow((EditFieldAutoCompletionWindow)null);
/*      */     } else {
/*  618 */       setAutoCompletionWindow(new EditFieldAutoCompletionWindow(this, dataSource));
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
/*      */   public void setAutoCompletion(AutoCompletionDataSource dataSource, ExecutorService executorService) {
/*  630 */     if (dataSource == null) {
/*  631 */       setAutoCompletionWindow((EditFieldAutoCompletionWindow)null);
/*      */     } else {
/*  633 */       setAutoCompletionWindow(new EditFieldAutoCompletionWindow(this, dataSource, executorService));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean handleEvent(Event evt) {
/*  639 */     boolean selectPressed = ((evt.getModifiers() & 0x9) != 0);
/*      */     
/*  641 */     if (evt.isMouseEvent()) {
/*  642 */       boolean hover = (evt.getType() != Event.Type.MOUSE_EXITED && isMouseInside(evt));
/*  643 */       getAnimationState().setAnimationState(STATE_HOVER, hover);
/*      */     } 
/*      */     
/*  646 */     if (evt.isMouseDragEvent()) {
/*  647 */       if (evt.getType() == Event.Type.MOUSE_DRAGGED && (evt.getModifiers() & 0x40) != 0) {
/*      */         
/*  649 */         int newPos = getCursorPosFromMouse(evt.getMouseX(), evt.getMouseY());
/*  650 */         setCursorPos(newPos, true);
/*      */       } 
/*  652 */       return true;
/*      */     } 
/*      */     
/*  655 */     if (super.handleEvent(evt)) {
/*  656 */       return true;
/*      */     }
/*      */     
/*  659 */     if (this.autoCompletionWindow != null && 
/*  660 */       this.autoCompletionWindow.handleEvent(evt)) {
/*  661 */       return true;
/*      */     }
/*      */ 
/*      */     
/*  665 */     switch (evt.getType()) {
/*      */       case KEY_PRESSED:
/*  667 */         switch (evt.getKeyCode()) {
/*      */           case 14:
/*  669 */             deletePrev();
/*  670 */             return true;
/*      */           case 211:
/*  672 */             deleteNext();
/*  673 */             return true;
/*      */           case 28:
/*      */           case 156:
/*  676 */             if (this.multiLine)
/*  677 */             { if (evt.hasKeyCharNoModifiers())
/*  678 */               { insertChar('\n');
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  685 */                 return true; }  break; }  doCallback(28); return true;
/*      */           case 1:
/*  687 */             doCallback(evt.getKeyCode());
/*  688 */             return true;
/*      */           case 199:
/*  690 */             setCursorPos(computeLineStart(this.cursorPos), selectPressed);
/*  691 */             return true;
/*      */           case 207:
/*  693 */             setCursorPos(computeLineEnd(this.cursorPos), selectPressed);
/*  694 */             return true;
/*      */           case 203:
/*  696 */             moveCursor(-1, selectPressed);
/*  697 */             return true;
/*      */           case 205:
/*  699 */             moveCursor(1, selectPressed);
/*  700 */             return true;
/*      */           case 200:
/*  702 */             if (this.multiLine) {
/*  703 */               moveCursorY(-1, selectPressed);
/*  704 */               return true;
/*      */             } 
/*      */             break;
/*      */           case 208:
/*  708 */             if (this.multiLine) {
/*  709 */               moveCursorY(1, selectPressed);
/*  710 */               return true;
/*      */             } 
/*      */             break;
/*      */           case 15:
/*  714 */             return false;
/*      */           default:
/*  716 */             if (evt.hasKeyCharNoModifiers()) {
/*  717 */               insertChar(evt.getKeyChar());
/*  718 */               return true;
/*      */             }  break;
/*      */         } 
/*  721 */         if (this.forwardUnhandledKeysToCallback) {
/*  722 */           doCallback(evt.getKeyCode());
/*  723 */           return true;
/*      */         } 
/*  725 */         return false;
/*      */       
/*      */       case KEY_RELEASED:
/*  728 */         switch (evt.getKeyCode()) {
/*      */           case 1:
/*      */           case 14:
/*      */           case 28:
/*      */           case 156:
/*      */           case 199:
/*      */           case 203:
/*      */           case 205:
/*      */           case 207:
/*      */           case 211:
/*  738 */             return true;
/*      */         } 
/*  740 */         return (evt.hasKeyCharNoModifiers() || this.forwardUnhandledKeysToCallback);
/*      */ 
/*      */       
/*      */       case MOUSE_BTNUP:
/*  744 */         if (evt.getMouseButton() == 1 && isMouseInside(evt)) {
/*  745 */           showPopupMenu(evt);
/*  746 */           return true;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case MOUSE_BTNDOWN:
/*  751 */         if (evt.getMouseButton() == 0 && isMouseInside(evt)) {
/*  752 */           int newPos = getCursorPosFromMouse(evt.getMouseX(), evt.getMouseY());
/*  753 */           setCursorPos(newPos, selectPressed);
/*  754 */           this.scrollPos = this.textRenderer.lastScrollPos;
/*  755 */           return true;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case MOUSE_CLICKED:
/*  760 */         if (evt.getMouseClickCount() == 2) {
/*  761 */           int newPos = getCursorPosFromMouse(evt.getMouseX(), evt.getMouseY());
/*  762 */           selectWordFromMouse(newPos);
/*  763 */           this.cursorPos = this.selectionStart;
/*  764 */           scrollToCursor(false);
/*  765 */           this.cursorPos = this.selectionEnd;
/*  766 */           scrollToCursor(false);
/*  767 */           return true;
/*      */         } 
/*  769 */         if (evt.getMouseClickCount() == 3) {
/*  770 */           selectAll();
/*  771 */           return true;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case MOUSE_WHEEL:
/*  776 */         return false;
/*      */     } 
/*      */     
/*  779 */     return evt.isMouseEvent();
/*      */   }
/*      */   
/*      */   protected void showPopupMenu(Event evt) {
/*  783 */     if (this.popupMenu == null) {
/*  784 */       this.popupMenu = createPopupMenu();
/*      */     }
/*  786 */     if (this.popupMenu != null) {
/*  787 */       this.popupMenu.openPopupMenu(this, evt.getMouseX(), evt.getMouseY());
/*      */     }
/*      */   }
/*      */   
/*      */   protected Menu createPopupMenu() {
/*  792 */     Menu menu = new Menu();
/*  793 */     if (!this.readOnly) {
/*  794 */       menu.add("cut", new Runnable() {
/*      */             public void run() {
/*  796 */               EditField.this.cutToClipboard();
/*      */             }
/*      */           });
/*      */     }
/*  800 */     menu.add("copy", new Runnable() {
/*      */           public void run() {
/*  802 */             EditField.this.copyToClipboard();
/*      */           }
/*      */         });
/*  805 */     if (!this.readOnly) {
/*  806 */       menu.add("paste", new Runnable() {
/*      */             public void run() {
/*  808 */               EditField.this.pasteFromClipboard();
/*      */             }
/*      */           });
/*  811 */       menu.add("clear", new Runnable() {
/*      */             public void run() {
/*  813 */               if (!EditField.this.isReadOnly()) {
/*  814 */                 EditField.this.setText("");
/*      */               }
/*      */             }
/*      */           });
/*      */     } 
/*  819 */     menu.addSpacer();
/*  820 */     menu.add("select all", new Runnable() {
/*      */           public void run() {
/*  822 */             EditField.this.selectAll();
/*      */           }
/*      */         });
/*  825 */     return menu;
/*      */   }
/*      */   
/*      */   private void updateText(boolean updateAutoCompletion, boolean fromModel, int key) {
/*  829 */     if (this.model != null && !fromModel) {
/*      */       try {
/*  831 */         this.model.setValue(getText());
/*  832 */         if (this.errorMsgFromModel) {
/*  833 */           setErrorMessage((Object)null);
/*      */         }
/*  835 */       } catch (Exception ex) {
/*  836 */         if (this.errorMsg == null || this.errorMsgFromModel) {
/*  837 */           setErrorMessage(ex.getMessage());
/*  838 */           this.errorMsgFromModel = true;
/*      */         } 
/*      */       } 
/*      */     }
/*  842 */     updateTextDisplay();
/*  843 */     if (this.multiLine) {
/*  844 */       int numLines = this.textRenderer.getNumTextLines();
/*  845 */       if (this.numberOfLines != numLines) {
/*  846 */         this.numberOfLines = numLines;
/*  847 */         invalidateLayout();
/*      */       } 
/*      */     } 
/*  850 */     doCallback(key);
/*  851 */     if ((this.autoCompletionWindow != null && this.autoCompletionWindow.isOpen()) || updateAutoCompletion) {
/*  852 */       updateAutoCompletion();
/*      */     }
/*      */   }
/*      */   
/*      */   private void updateTextDisplay() {
/*  857 */     this.textRenderer.setCharSequence((this.passwordMasking != null) ? this.passwordMasking : (CharSequence)this.editBuffer);
/*  858 */     this.textRenderer.cacheDirty = true;
/*  859 */     checkTextWidth();
/*  860 */     scrollToCursor(false);
/*      */   }
/*      */   
/*      */   private void checkTextWidth() {
/*  864 */     this.textLongerThenWidget = (this.textRenderer.getPreferredWidth() > this.textRenderer.getWidth());
/*      */   }
/*      */   
/*      */   protected void moveCursor(int dir, boolean select) {
/*  868 */     setCursorPos(this.cursorPos + dir, select);
/*      */   }
/*      */   
/*      */   protected void moveCursorY(int dir, boolean select) {
/*  872 */     if (this.multiLine) {
/*  873 */       int lineStart, x = computeRelativeCursorPositionX(this.cursorPos);
/*      */       
/*  875 */       if (dir < 0) {
/*  876 */         lineStart = computeLineStart(this.cursorPos);
/*  877 */         if (lineStart == 0) {
/*  878 */           setCursorPos(0, select);
/*      */           return;
/*      */         } 
/*  881 */         lineStart = computeLineStart(lineStart - 1);
/*      */       } else {
/*  883 */         lineStart = Math.min(computeLineEnd(this.cursorPos) + 1, this.editBuffer.length());
/*      */       } 
/*  885 */       setCursorPos(computeCursorPosFromX(x, lineStart), select);
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void setCursorPos(int pos, boolean select) {
/*  890 */     pos = Math.max(0, Math.min(this.editBuffer.length(), pos));
/*  891 */     if (!select) {
/*  892 */       boolean hadSelection = hasSelection();
/*  893 */       this.selectionStart = pos;
/*  894 */       this.selectionEnd = pos;
/*  895 */       if (hadSelection) {
/*  896 */         updateSelection();
/*      */       }
/*      */     } 
/*  899 */     if (this.cursorPos != pos) {
/*  900 */       if (select) {
/*  901 */         if (hasSelection()) {
/*  902 */           if (this.cursorPos == this.selectionStart) {
/*  903 */             this.selectionStart = pos;
/*      */           } else {
/*  905 */             this.selectionEnd = pos;
/*      */           } 
/*      */         } else {
/*  908 */           this.selectionStart = this.cursorPos;
/*  909 */           this.selectionEnd = pos;
/*      */         } 
/*  911 */         if (this.selectionStart > this.selectionEnd) {
/*  912 */           int t = this.selectionStart;
/*  913 */           this.selectionStart = this.selectionEnd;
/*  914 */           this.selectionEnd = t;
/*      */         } 
/*  916 */         updateSelection();
/*      */       } 
/*      */       
/*  919 */       if (this.cursorPos != pos) {
/*  920 */         getAnimationState().resetAnimationTime(STATE_CURSOR_MOVED);
/*      */       }
/*  922 */       this.cursorPos = pos;
/*  923 */       scrollToCursor(false);
/*  924 */       updateAutoCompletion();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void updateSelection() {
/*  929 */     if (this.attributes != null) {
/*  930 */       this.attributes.removeAnimationState(TextWidget.STATE_TEXT_SELECTION);
/*  931 */       this.attributes.setAnimationState(TextWidget.STATE_TEXT_SELECTION, this.selectionStart, this.selectionEnd, true);
/*      */       
/*  933 */       this.attributes.optimize();
/*  934 */       this.textRenderer.cacheDirty = true;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setCursorPos(int pos) {
/*  939 */     if (pos < 0 || pos > this.editBuffer.length()) {
/*  940 */       throw new IllegalArgumentException("pos");
/*      */     }
/*  942 */     setCursorPos(pos, false);
/*      */   }
/*      */   
/*      */   public void selectAll() {
/*  946 */     this.selectionStart = 0;
/*  947 */     this.selectionEnd = this.editBuffer.length();
/*  948 */     updateSelection();
/*      */   }
/*      */   
/*      */   public void setSelection(int start, int end) {
/*  952 */     if (start < 0 || start > end || end > this.editBuffer.length()) {
/*  953 */       throw new IllegalArgumentException();
/*      */     }
/*  955 */     this.selectionStart = start;
/*  956 */     this.selectionEnd = end;
/*  957 */     updateSelection();
/*      */   }
/*      */   
/*      */   protected void selectWordFromMouse(int index) {
/*  961 */     this.selectionStart = index;
/*  962 */     this.selectionEnd = index;
/*  963 */     while (this.selectionStart > 0 && !Character.isWhitespace(this.editBuffer.charAt(this.selectionStart - 1))) {
/*  964 */       this.selectionStart--;
/*      */     }
/*  966 */     while (this.selectionEnd < this.editBuffer.length() && !Character.isWhitespace(this.editBuffer.charAt(this.selectionEnd))) {
/*  967 */       this.selectionEnd++;
/*      */     }
/*  969 */     updateSelection();
/*      */   }
/*      */   
/*      */   protected void scrollToCursor(boolean force) {
/*  973 */     int renderWidth = this.textRenderer.getWidth() - 5;
/*  974 */     if (renderWidth <= 0) {
/*  975 */       this.pendingScrollToCursor = true;
/*  976 */       this.pendingScrollToCursorForce = force;
/*      */       return;
/*      */     } 
/*  979 */     this.pendingScrollToCursor = false;
/*  980 */     int xpos = computeRelativeCursorPositionX(this.cursorPos);
/*  981 */     if (xpos < this.scrollPos + 5) {
/*  982 */       this.scrollPos = Math.max(0, xpos - 5);
/*  983 */     } else if (force || xpos - this.scrollPos > renderWidth) {
/*  984 */       this.scrollPos = Math.max(0, xpos - renderWidth);
/*      */     } 
/*  986 */     if (this.multiLine) {
/*  987 */       ScrollPane sp = ScrollPane.getContainingScrollPane(this);
/*  988 */       if (sp != null) {
/*  989 */         int lineHeight = getLineHeight();
/*  990 */         int lineY = computeLineNumber(this.cursorPos) * lineHeight;
/*  991 */         sp.validateLayout();
/*  992 */         sp.scrollToAreaY(lineY, lineHeight, lineHeight / 2);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void insertChar(char ch) {
/*  999 */     if (!this.readOnly && (!Character.isISOControl(ch) || (this.multiLine && ch == '\n'))) {
/* 1000 */       boolean update = false;
/* 1001 */       if (hasSelection()) {
/* 1002 */         deleteSelection();
/* 1003 */         update = true;
/*      */       } 
/* 1005 */       if (this.editBuffer.length() < this.maxTextLength && 
/* 1006 */         this.editBuffer.replace(this.cursorPos, 0, ch)) {
/* 1007 */         this.cursorPos++;
/* 1008 */         update = true;
/*      */       } 
/*      */       
/* 1011 */       if (update) {
/* 1012 */         updateText(true, false, 0);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void deletePrev() {
/* 1018 */     if (!this.readOnly) {
/* 1019 */       if (hasSelection()) {
/* 1020 */         deleteSelection();
/* 1021 */         updateText(true, false, 211);
/* 1022 */       } else if (this.cursorPos > 0) {
/* 1023 */         this.cursorPos--;
/* 1024 */         deleteNext();
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   protected void deleteNext() {
/* 1030 */     if (!this.readOnly) {
/* 1031 */       if (hasSelection()) {
/* 1032 */         deleteSelection();
/* 1033 */         updateText(true, false, 211);
/* 1034 */       } else if (this.cursorPos < this.editBuffer.length() && 
/* 1035 */         this.editBuffer.replace(this.cursorPos, 1, "") >= 0) {
/* 1036 */         updateText(true, false, 211);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void deleteSelection() {
/* 1043 */     if (this.editBuffer.replace(this.selectionStart, this.selectionEnd - this.selectionStart, "") >= 0) {
/* 1044 */       setCursorPos(this.selectionStart, false);
/*      */     }
/*      */   }
/*      */   
/*      */   protected void modelChanged() {
/* 1049 */     String modelText = this.model.getValue();
/* 1050 */     if (this.editBuffer.length() != modelText.length() || !getText().equals(modelText)) {
/* 1051 */       setText(modelText, true);
/*      */     }
/*      */   }
/*      */   
/*      */   protected boolean hasFocusOrPopup() {
/* 1056 */     return (hasKeyboardFocus() || hasOpenPopups());
/*      */   }
/*      */   
/*      */   protected Font getFont() {
/* 1060 */     return this.textRenderer.getFont();
/*      */   }
/*      */   
/*      */   protected int getLineHeight() {
/* 1064 */     Font font = getFont();
/* 1065 */     if (font != null) {
/* 1066 */       return font.getLineHeight();
/*      */     }
/* 1068 */     return 0;
/*      */   }
/*      */   
/*      */   protected int computeLineNumber(int cursorPos) {
/* 1072 */     EditFieldModel eb = this.editBuffer;
/* 1073 */     int lineNr = 0;
/* 1074 */     for (int i = 0; i < cursorPos; i++) {
/* 1075 */       if (eb.charAt(i) == '\n') {
/* 1076 */         lineNr++;
/*      */       }
/*      */     } 
/* 1079 */     return lineNr;
/*      */   }
/*      */   
/*      */   protected int computeLineStart(int cursorPos) {
/* 1083 */     if (!this.multiLine) {
/* 1084 */       return 0;
/*      */     }
/* 1086 */     EditFieldModel eb = this.editBuffer;
/* 1087 */     while (cursorPos > 0 && eb.charAt(cursorPos - 1) != '\n') {
/* 1088 */       cursorPos--;
/*      */     }
/* 1090 */     return cursorPos;
/*      */   }
/*      */   
/*      */   protected int computeLineEnd(int cursorPos) {
/* 1094 */     EditFieldModel eb = this.editBuffer;
/* 1095 */     int endIndex = eb.length();
/* 1096 */     if (!this.multiLine) {
/* 1097 */       return endIndex;
/*      */     }
/* 1099 */     while (cursorPos < endIndex && eb.charAt(cursorPos) != '\n') {
/* 1100 */       cursorPos++;
/*      */     }
/* 1102 */     return cursorPos;
/*      */   }
/*      */   
/*      */   protected int computeRelativeCursorPositionX(int cursorPos) {
/* 1106 */     int lineStart = 0;
/* 1107 */     if (this.multiLine) {
/* 1108 */       lineStart = computeLineStart(cursorPos);
/*      */     }
/* 1110 */     return this.textRenderer.computeRelativeCursorPositionX(lineStart, cursorPos);
/*      */   }
/*      */   
/*      */   protected int computeRelativeCursorPositionY(int cursorPos) {
/* 1114 */     if (this.multiLine) {
/* 1115 */       return getLineHeight() * computeLineNumber(cursorPos);
/*      */     }
/* 1117 */     return 0;
/*      */   }
/*      */   
/*      */   protected int getCursorPosFromMouse(int x, int y) {
/* 1121 */     Font font = getFont();
/* 1122 */     if (font != null) {
/* 1123 */       x -= this.textRenderer.lastTextX;
/* 1124 */       int lineStart = 0;
/* 1125 */       int lineEnd = this.editBuffer.length();
/* 1126 */       if (this.multiLine) {
/* 1127 */         y -= this.textRenderer.computeTextY();
/* 1128 */         int lineHeight = font.getLineHeight();
/* 1129 */         int endIndex = lineEnd;
/*      */         while (true) {
/* 1131 */           lineEnd = computeLineEnd(lineStart);
/*      */           
/* 1133 */           if (lineStart >= endIndex || y < lineHeight) {
/*      */             break;
/*      */           }
/*      */           
/* 1137 */           lineStart = Math.min(lineEnd + 1, endIndex);
/* 1138 */           y -= lineHeight;
/*      */         } 
/*      */       } 
/* 1141 */       return computeCursorPosFromX(x, lineStart, lineEnd);
/*      */     } 
/* 1143 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   protected int computeCursorPosFromX(int x, int lineStart) {
/* 1148 */     return computeCursorPosFromX(x, lineStart, computeLineEnd(lineStart));
/*      */   }
/*      */   
/*      */   protected int computeCursorPosFromX(int x, int lineStart, int lineEnd) {
/* 1152 */     Font font = getFont();
/* 1153 */     if (font != null) {
/* 1154 */       return lineStart + font.computeVisibleGlpyhs((this.passwordMasking != null) ? this.passwordMasking : (CharSequence)this.editBuffer, lineStart, lineEnd, x + font.getSpaceWidth() / 2);
/*      */     }
/*      */ 
/*      */     
/* 1158 */     return lineStart;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void paintOverlay(GUI gui) {
/* 1163 */     if (this.cursorImage != null && hasFocusOrPopup()) {
/* 1164 */       int xpos = this.textRenderer.lastTextX + computeRelativeCursorPositionX(this.cursorPos);
/* 1165 */       int ypos = this.textRenderer.computeTextY() + computeRelativeCursorPositionY(this.cursorPos);
/* 1166 */       this.cursorImage.draw(getAnimationState(), xpos, ypos, this.cursorImage.getWidth(), getLineHeight());
/*      */     } 
/* 1168 */     super.paintOverlay(gui);
/*      */   }
/*      */   
/*      */   private void openErrorInfoWindow() {
/* 1172 */     if (this.autoCompletionWindow == null || !this.autoCompletionWindow.isOpen()) {
/* 1173 */       if (this.errorInfoWindow == null) {
/* 1174 */         this.errorInfoLabel = new Label();
/* 1175 */         this.errorInfoLabel.setClip(true);
/* 1176 */         this.errorInfoWindow = new InfoWindow(this);
/* 1177 */         this.errorInfoWindow.setTheme("editfield-errorinfowindow");
/* 1178 */         this.errorInfoWindow.add(this.errorInfoLabel);
/*      */       } 
/* 1180 */       this.errorInfoLabel.setText(this.errorMsg.toString());
/* 1181 */       this.errorInfoWindow.openInfo();
/* 1182 */       layoutErrorInfoWindow();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void layoutErrorInfoWindow() {
/* 1187 */     int x = getX();
/* 1188 */     int width = getWidth();
/*      */     
/* 1190 */     Widget container = this.errorInfoWindow.getParent();
/* 1191 */     if (container != null) {
/* 1192 */       width = Math.max(width, computeSize(this.errorInfoWindow.getMinWidth(), this.errorInfoWindow.getPreferredWidth(), this.errorInfoWindow.getMaxWidth()));
/*      */ 
/*      */ 
/*      */       
/* 1196 */       int popupMaxRight = container.getInnerRight();
/* 1197 */       if (x + width > popupMaxRight) {
/* 1198 */         x = popupMaxRight - Math.min(width, container.getInnerWidth());
/*      */       }
/* 1200 */       this.errorInfoWindow.setSize(width, this.errorInfoWindow.getPreferredHeight());
/* 1201 */       this.errorInfoWindow.setPosition(x, getBottom());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void keyboardFocusGained() {
/* 1207 */     if (this.errorMsg != null) {
/* 1208 */       openErrorInfoWindow();
/*      */     } else {
/* 1210 */       updateAutoCompletion();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void keyboardFocusLost() {
/* 1216 */     super.keyboardFocusLost();
/* 1217 */     if (this.errorInfoWindow != null) {
/* 1218 */       this.errorInfoWindow.closeInfo();
/*      */     }
/* 1220 */     if (this.autoCompletionWindow != null) {
/* 1221 */       this.autoCompletionWindow.closeInfo();
/*      */     }
/*      */   }
/*      */   
/*      */   protected void updateAutoCompletion() {
/* 1226 */     if (this.autoCompletionWindow != null)
/* 1227 */       this.autoCompletionWindow.updateAutoCompletion(); 
/*      */   }
/*      */   
/*      */   protected class ModelChangeListener
/*      */     implements Runnable {
/*      */     public void run() {
/* 1233 */       EditField.this.modelChanged();
/*      */     }
/*      */   }
/*      */   
/*      */   protected class TextRenderer extends TextWidget {
/*      */     int lastTextX;
/*      */     int lastScrollPos;
/*      */     AttributedStringFontCache cache;
/*      */     boolean cacheDirty;
/*      */     
/*      */     protected TextRenderer(AnimationState animState) {
/* 1244 */       super(animState);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void paintWidget(GUI gui) {
/* 1249 */       if (EditField.this.pendingScrollToCursor) {
/* 1250 */         EditField.this.scrollToCursor(EditField.this.pendingScrollToCursorForce);
/*      */       }
/* 1252 */       this.lastScrollPos = EditField.this.hasFocusOrPopup() ? EditField.this.scrollPos : 0;
/* 1253 */       this.lastTextX = computeTextX();
/* 1254 */       Font font = getFont();
/* 1255 */       if (EditField.this.attributes != null && font instanceof Font2) {
/* 1256 */         paintWithAttributes((Font2)font);
/* 1257 */       } else if (EditField.this.hasSelection() && EditField.this.hasFocusOrPopup()) {
/* 1258 */         if (EditField.this.multiLine) {
/* 1259 */           paintMultiLineWithSelection();
/*      */         } else {
/* 1261 */           paintWithSelection(0, EditField.this.editBuffer.length(), computeTextY());
/*      */         } 
/*      */       } else {
/* 1264 */         paintLabelText(getAnimationState());
/*      */       } 
/*      */     }
/*      */     
/*      */     protected void paintWithSelection(int lineStart, int lineEnd, int yoff) {
/* 1269 */       int selStart = EditField.this.selectionStart;
/* 1270 */       int selEnd = EditField.this.selectionEnd;
/* 1271 */       if (EditField.this.selectionImage != null && selEnd > lineStart && selStart <= lineEnd) {
/* 1272 */         int xpos0 = this.lastTextX + computeRelativeCursorPositionX(lineStart, selStart);
/* 1273 */         int xpos1 = (lineEnd < selEnd) ? getInnerRight() : (this.lastTextX + computeRelativeCursorPositionX(lineStart, Math.min(lineEnd, selEnd)));
/*      */         
/* 1275 */         EditField.this.selectionImage.draw(getAnimationState(), xpos0, yoff, xpos1 - xpos0, getFont().getLineHeight());
/*      */       } 
/*      */ 
/*      */       
/* 1279 */       paintWithSelection(getAnimationState(), selStart, selEnd, lineStart, lineEnd, yoff);
/*      */     }
/*      */     
/*      */     protected void paintMultiLineWithSelection() {
/* 1283 */       EditFieldModel eb = EditField.this.editBuffer;
/* 1284 */       int lineStart = 0;
/* 1285 */       int endIndex = eb.length();
/* 1286 */       int yoff = computeTextY();
/* 1287 */       int lineHeight = EditField.this.getLineHeight();
/* 1288 */       while (lineStart < endIndex) {
/* 1289 */         int lineEnd = EditField.this.computeLineEnd(lineStart);
/*      */         
/* 1291 */         paintWithSelection(lineStart, lineEnd, yoff);
/*      */         
/* 1293 */         yoff += lineHeight;
/* 1294 */         lineStart = lineEnd + 1;
/*      */       } 
/*      */     }
/*      */     
/*      */     protected void paintMultiLineSelectionBackground() {
/* 1299 */       int lineHeight = EditField.this.getLineHeight();
/* 1300 */       int lineStart = EditField.this.computeLineStart(EditField.this.selectionStart);
/* 1301 */       int lineNumber = EditField.this.computeLineNumber(lineStart);
/* 1302 */       int endIndex = EditField.this.selectionEnd;
/* 1303 */       int yoff = computeTextY() + lineHeight * lineNumber;
/* 1304 */       int xstart = this.lastTextX + computeRelativeCursorPositionX(lineStart, EditField.this.selectionStart);
/* 1305 */       while (lineStart < endIndex) {
/* 1306 */         int xend, lineEnd = EditField.this.computeLineEnd(lineStart);
/*      */ 
/*      */         
/* 1309 */         if (lineEnd < endIndex) {
/* 1310 */           xend = getInnerRight();
/*      */         } else {
/* 1312 */           xend = this.lastTextX + computeRelativeCursorPositionX(lineStart, endIndex);
/*      */         } 
/*      */         
/* 1315 */         EditField.this.selectionImage.draw(getAnimationState(), xstart, yoff, xend - xstart, lineHeight);
/*      */         
/* 1317 */         yoff += lineHeight;
/* 1318 */         lineStart = lineEnd + 1;
/* 1319 */         xstart = getInnerX();
/*      */       } 
/*      */     }
/*      */     
/*      */     protected void paintWithAttributes(Font2 font) {
/* 1324 */       if (EditField.this.selectionEnd > EditField.this.selectionStart && EditField.this.selectionImage != null) {
/* 1325 */         paintMultiLineSelectionBackground();
/*      */       }
/* 1327 */       if (this.cache == null || this.cacheDirty) {
/* 1328 */         this.cacheDirty = false;
/* 1329 */         if (EditField.this.multiLine) {
/* 1330 */           this.cache = font.cacheMultiLineText(this.cache, (AttributedString)EditField.this.attributes);
/*      */         } else {
/* 1332 */           this.cache = font.cacheText(this.cache, (AttributedString)EditField.this.attributes);
/*      */         } 
/*      */       } 
/* 1335 */       int y = computeTextY();
/* 1336 */       if (this.cache != null) {
/* 1337 */         this.cache.draw(this.lastTextX, y);
/* 1338 */       } else if (EditField.this.multiLine) {
/* 1339 */         font.drawMultiLineText(this.lastTextX, y, (AttributedString)EditField.this.attributes);
/*      */       } else {
/* 1341 */         font.drawText(this.lastTextX, y, (AttributedString)EditField.this.attributes);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     protected void sizeChanged() {
/* 1347 */       if (EditField.this.scrollToCursorOnSizeChange) {
/* 1348 */         EditField.this.scrollToCursor(true);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     protected int computeTextX() {
/* 1354 */       int x = getInnerX();
/* 1355 */       int pos = (getAlignment()).hpos;
/* 1356 */       if (pos > 0) {
/* 1357 */         x += Math.max(0, getInnerWidth() - computeTextWidth()) * pos / 2;
/*      */       }
/* 1359 */       return x - this.lastScrollPos;
/*      */     }
/*      */ 
/*      */     
/*      */     public void destroy() {
/* 1364 */       super.destroy();
/* 1365 */       if (this.cache != null) {
/* 1366 */         this.cache.destroy();
/* 1367 */         this.cache = null;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   static class PasswordMasker implements CharSequence {
/*      */     final CharSequence base;
/*      */     final char maskingChar;
/*      */     
/*      */     public PasswordMasker(CharSequence base, char maskingChar) {
/* 1377 */       this.base = base;
/* 1378 */       this.maskingChar = maskingChar;
/*      */     }
/*      */     
/*      */     public int length() {
/* 1382 */       return this.base.length();
/*      */     }
/*      */     
/*      */     public char charAt(int index) {
/* 1386 */       return this.maskingChar;
/*      */     }
/*      */     
/*      */     public CharSequence subSequence(int start, int end) {
/* 1390 */       throw new UnsupportedOperationException("Not supported.");
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface Callback {
/*      */     void callback(int param1Int);
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\EditField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */