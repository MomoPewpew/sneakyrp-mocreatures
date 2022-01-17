/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.IntegerModel;
/*     */ import de.matthiasmann.twl.model.ListModel;
/*     */ import de.matthiasmann.twl.model.ListSelectionModel;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.Font;
/*     */ import de.matthiasmann.twl.utils.CallbackSupport;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ComboBox<T>
/*     */   extends ComboBoxBase
/*     */ {
/*  49 */   public static final AnimationState.StateKey STATE_ERROR = AnimationState.StateKey.get("error");
/*     */   
/*     */   private static final int INVALID_WIDTH = -1;
/*     */   
/*     */   private final ComboboxLabel label;
/*     */   
/*     */   private final ListBox<T> listbox;
/*     */   
/*     */   private Runnable[] selectionChangedListeners;
/*     */   private ListModel.ChangeListener modelChangeListener;
/*  59 */   String displayTextNoSelection = "";
/*     */   boolean noSelectionIsError;
/*     */   boolean computeWidthFromModel;
/*  62 */   int modelWidth = -1;
/*     */ 
/*     */   
/*     */   public ComboBox(ListSelectionModel<T> model) {
/*  66 */     this();
/*  67 */     setModel(model);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComboBox(ListModel<T> model, IntegerModel selectionModel) {
/*  72 */     this();
/*  73 */     setModel(model);
/*  74 */     setSelectionModel(selectionModel);
/*     */   }
/*     */ 
/*     */   
/*     */   public ComboBox(ListModel<T> model) {
/*  79 */     this();
/*  80 */     setModel(model);
/*     */   }
/*     */   
/*     */   public ComboBox() {
/*  84 */     this.label = new ComboboxLabel(getAnimationState());
/*  85 */     this.listbox = new ComboboxListbox<T>();
/*     */     
/*  87 */     this.button.getModel().addStateCallback(new Runnable() {
/*     */           public void run() {
/*  89 */             ComboBox.this.updateHover();
/*     */           }
/*     */         });
/*     */     
/*  93 */     this.label.addCallback(new CallbackWithReason<Label.CallbackReason>() {
/*     */           public void callback(Label.CallbackReason reason) {
/*  95 */             ComboBox.this.openPopup();
/*     */           }
/*     */         });
/*     */     
/*  99 */     this.listbox.addCallback(new CallbackWithReason<ListBox.CallbackReason>() {
/*     */           public void callback(ListBox.CallbackReason reason) {
/* 101 */             switch (reason) {
/*     */               case KEYBOARD_RETURN:
/*     */               case MOUSE_CLICK:
/*     */               case MOUSE_DOUBLE_CLICK:
/* 105 */                 ComboBox.this.listBoxSelectionChanged(true);
/*     */                 return;
/*     */             } 
/* 108 */             ComboBox.this.listBoxSelectionChanged(false);
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 114 */     this.popup.setTheme("comboboxPopup");
/* 115 */     this.popup.add(this.listbox);
/* 116 */     add(this.label);
/*     */   }
/*     */   
/*     */   public void addCallback(Runnable cb) {
/* 120 */     this.selectionChangedListeners = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.selectionChangedListeners, cb, Runnable.class);
/*     */   }
/*     */   
/*     */   public void removeCallback(Runnable cb) {
/* 124 */     this.selectionChangedListeners = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.selectionChangedListeners, cb);
/*     */   }
/*     */   
/*     */   private void doCallback() {
/* 128 */     CallbackSupport.fireCallbacks(this.selectionChangedListeners);
/*     */   }
/*     */   
/*     */   public void setModel(ListModel<T> model) {
/* 132 */     unregisterModelChangeListener();
/* 133 */     this.listbox.setModel(model);
/* 134 */     if (this.computeWidthFromModel) {
/* 135 */       registerModelChangeListener();
/*     */     }
/*     */   }
/*     */   
/*     */   public ListModel<T> getModel() {
/* 140 */     return this.listbox.getModel();
/*     */   }
/*     */   
/*     */   public void setSelectionModel(IntegerModel selectionModel) {
/* 144 */     this.listbox.setSelectionModel(selectionModel);
/*     */   }
/*     */   
/*     */   public IntegerModel getSelectionModel() {
/* 148 */     return this.listbox.getSelectionModel();
/*     */   }
/*     */   
/*     */   public void setModel(ListSelectionModel<T> model) {
/* 152 */     this.listbox.setModel(model);
/*     */   }
/*     */   
/*     */   public void setSelected(int selected) {
/* 156 */     this.listbox.setSelected(selected);
/* 157 */     updateLabel();
/*     */   }
/*     */   
/*     */   public int getSelected() {
/* 161 */     return this.listbox.getSelected();
/*     */   }
/*     */   
/*     */   public boolean isComputeWidthFromModel() {
/* 165 */     return this.computeWidthFromModel;
/*     */   }
/*     */   
/*     */   public void setComputeWidthFromModel(boolean computeWidthFromModel) {
/* 169 */     if (this.computeWidthFromModel != computeWidthFromModel) {
/* 170 */       this.computeWidthFromModel = computeWidthFromModel;
/* 171 */       if (computeWidthFromModel) {
/* 172 */         registerModelChangeListener();
/*     */       } else {
/* 174 */         unregisterModelChangeListener();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getDisplayTextNoSelection() {
/* 180 */     return this.displayTextNoSelection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDisplayTextNoSelection(String displayTextNoSelection) {
/* 191 */     if (displayTextNoSelection == null) {
/* 192 */       throw new NullPointerException("displayTextNoSelection");
/*     */     }
/* 194 */     this.displayTextNoSelection = displayTextNoSelection;
/* 195 */     updateLabel();
/*     */   }
/*     */   
/*     */   public boolean isNoSelectionIsError() {
/* 199 */     return this.noSelectionIsError;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNoSelectionIsError(boolean noSelectionIsError) {
/* 209 */     this.noSelectionIsError = noSelectionIsError;
/* 210 */     updateLabel();
/*     */   }
/*     */   
/*     */   private void registerModelChangeListener() {
/* 214 */     ListModel<?> model = getModel();
/* 215 */     if (model != null) {
/* 216 */       this.modelWidth = -1;
/* 217 */       if (this.modelChangeListener == null) {
/* 218 */         this.modelChangeListener = new ModelChangeListener();
/*     */       }
/* 220 */       model.addChangeListener(this.modelChangeListener);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void unregisterModelChangeListener() {
/* 225 */     if (this.modelChangeListener != null) {
/* 226 */       ListModel<T> model = getModel();
/* 227 */       if (model != null) {
/* 228 */         model.removeChangeListener(this.modelChangeListener);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean openPopup() {
/* 235 */     if (super.openPopup()) {
/* 236 */       this.popup.validateLayout();
/* 237 */       this.listbox.scrollToSelected();
/* 238 */       return true;
/*     */     } 
/* 240 */     return false;
/*     */   }
/*     */   
/*     */   protected void listBoxSelectionChanged(boolean close) {
/* 244 */     updateLabel();
/* 245 */     if (close) {
/* 246 */       this.popup.closePopup();
/*     */     }
/* 248 */     doCallback();
/*     */   }
/*     */   
/*     */   protected String getModelData(int idx) {
/* 252 */     return String.valueOf(getModel().getEntry(idx));
/*     */   }
/*     */   
/*     */   protected Widget getLabel() {
/* 256 */     return this.label;
/*     */   }
/*     */   
/*     */   protected void updateLabel() {
/* 260 */     int selected = getSelected();
/* 261 */     if (selected == -1) {
/* 262 */       this.label.setText(this.displayTextNoSelection);
/* 263 */       this.label.getAnimationState().setAnimationState(STATE_ERROR, this.noSelectionIsError);
/*     */     } else {
/* 265 */       this.label.setText(getModelData(selected));
/* 266 */       this.label.getAnimationState().setAnimationState(STATE_ERROR, false);
/*     */     } 
/* 268 */     if (!this.computeWidthFromModel) {
/* 269 */       invalidateLayout();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 275 */     super.applyTheme(themeInfo);
/* 276 */     this.modelWidth = -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean handleEvent(Event evt) {
/* 281 */     if (super.handleEvent(evt)) {
/* 282 */       return true;
/*     */     }
/* 284 */     if (evt.isKeyPressedEvent()) {
/* 285 */       switch (evt.getKeyCode()) {
/*     */         
/*     */         case 199:
/*     */         case 200:
/*     */         case 207:
/*     */         case 208:
/* 291 */           this.listbox.handleEvent(evt);
/* 292 */           return true;
/*     */         case 28:
/*     */         case 57:
/* 295 */           openPopup();
/* 296 */           return true;
/*     */       } 
/*     */     }
/* 299 */     return false;
/*     */   }
/*     */   
/*     */   void invalidateModelWidth() {
/* 303 */     if (this.computeWidthFromModel) {
/* 304 */       this.modelWidth = -1;
/* 305 */       invalidateLayout();
/*     */     } 
/*     */   }
/*     */   
/*     */   void updateModelWidth() {
/* 310 */     if (this.computeWidthFromModel) {
/* 311 */       this.modelWidth = 0;
/* 312 */       updateModelWidth(0, getModel().getNumEntries() - 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   void updateModelWidth(int first, int last) {
/* 317 */     if (this.computeWidthFromModel) {
/* 318 */       int newModelWidth = this.modelWidth;
/* 319 */       for (int idx = first; idx <= last; idx++) {
/* 320 */         newModelWidth = Math.max(newModelWidth, computeEntryWidth(idx));
/*     */       }
/* 322 */       if (newModelWidth > this.modelWidth) {
/* 323 */         this.modelWidth = newModelWidth;
/* 324 */         invalidateLayout();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected int computeEntryWidth(int idx) {
/* 330 */     int width = this.label.getBorderHorizontal();
/* 331 */     Font font = this.label.getFont();
/* 332 */     if (font != null) {
/* 333 */       width += font.computeMultiLineTextWidth(getModelData(idx));
/*     */     }
/* 335 */     return width;
/*     */   }
/*     */   
/*     */   void updateHover() {
/* 339 */     getAnimationState().setAnimationState(Label.STATE_HOVER, (this.label.hover || this.button.getModel().isHover()));
/*     */   }
/*     */   
/*     */   class ComboboxLabel
/*     */     extends Label {
/*     */     boolean hover;
/*     */     
/*     */     public ComboboxLabel(AnimationState animState) {
/* 347 */       super(animState);
/* 348 */       setAutoSize(false);
/* 349 */       setClip(true);
/* 350 */       setTheme("display");
/*     */     }
/*     */ 
/*     */     
/*     */     public int getPreferredInnerWidth() {
/* 355 */       if (ComboBox.this.computeWidthFromModel && ComboBox.this.getModel() != null) {
/* 356 */         if (ComboBox.this.modelWidth == -1) {
/* 357 */           ComboBox.this.updateModelWidth();
/*     */         }
/* 359 */         return ComboBox.this.modelWidth;
/*     */       } 
/* 361 */       return super.getPreferredInnerWidth();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getPreferredInnerHeight() {
/* 367 */       int prefHeight = super.getPreferredInnerHeight();
/* 368 */       if (getFont() != null) {
/* 369 */         prefHeight = Math.max(prefHeight, getFont().getLineHeight());
/*     */       }
/* 371 */       return prefHeight;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void handleMouseHover(Event evt) {
/* 376 */       if (evt.isMouseEvent()) {
/* 377 */         boolean newHover = (evt.getType() != Event.Type.MOUSE_EXITED);
/* 378 */         if (newHover != this.hover) {
/* 379 */           this.hover = newHover;
/* 380 */           ComboBox.this.updateHover();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class ModelChangeListener implements ListModel.ChangeListener {
/*     */     public void entriesInserted(int first, int last) {
/* 388 */       ComboBox.this.updateModelWidth(first, last);
/*     */     }
/*     */     public void entriesDeleted(int first, int last) {
/* 391 */       ComboBox.this.invalidateModelWidth();
/*     */     }
/*     */     public void entriesChanged(int first, int last) {
/* 394 */       ComboBox.this.invalidateModelWidth();
/*     */     }
/*     */     public void allChanged() {
/* 397 */       ComboBox.this.invalidateModelWidth();
/*     */     }
/*     */   }
/*     */   
/*     */   static class ComboboxListbox<T> extends ListBox<T> {
/*     */     public ComboboxListbox() {
/* 403 */       setTheme("listbox");
/*     */     }
/*     */ 
/*     */     
/*     */     protected ListBoxDisplay createDisplay() {
/* 408 */       return new ComboBox.ComboboxListboxLabel();
/*     */     }
/*     */   }
/*     */   
/*     */   static class ComboboxListboxLabel
/*     */     extends ListBox.ListBoxLabel {
/*     */     protected boolean handleListBoxEvent(Event evt) {
/* 415 */       if (evt.getType() == Event.Type.MOUSE_CLICKED) {
/* 416 */         doListBoxCallback(ListBox.CallbackReason.MOUSE_CLICK);
/* 417 */         return true;
/*     */       } 
/* 419 */       if (evt.getType() == Event.Type.MOUSE_BTNDOWN) {
/* 420 */         doListBoxCallback(ListBox.CallbackReason.SET_SELECTED);
/* 421 */         return true;
/*     */       } 
/* 423 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ComboBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */