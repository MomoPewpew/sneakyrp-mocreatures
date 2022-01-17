/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.ButtonModel;
/*     */ import de.matthiasmann.twl.model.SimpleButtonModel;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.utils.TextUtil;
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
/*     */ public class Button
/*     */   extends TextWidget
/*     */ {
/*  44 */   public static final AnimationState.StateKey STATE_ARMED = AnimationState.StateKey.get("armed");
/*  45 */   public static final AnimationState.StateKey STATE_PRESSED = AnimationState.StateKey.get("pressed");
/*  46 */   public static final AnimationState.StateKey STATE_SELECTED = AnimationState.StateKey.get("selected");
/*     */   
/*     */   private final Runnable stateChangedCB;
/*     */   private ButtonModel model;
/*     */   private String themeText;
/*     */   private String text;
/*     */   private int mouseButton;
/*     */   
/*     */   public Button() {
/*  55 */     this((AnimationState)null, false, (ButtonModel)null);
/*     */   }
/*     */   
/*     */   public Button(ButtonModel model) {
/*  59 */     this((AnimationState)null, false, model);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Button(AnimationState animState) {
/*  68 */     this(animState, false, (ButtonModel)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Button(AnimationState animState, boolean inherit) {
/*  78 */     this(animState, inherit, (ButtonModel)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Button(String text) {
/*  83 */     this((AnimationState)null, false, (ButtonModel)null);
/*  84 */     setText(text);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Button(AnimationState animState, ButtonModel model) {
/*  94 */     this(animState, false, model);
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
/*     */   public Button(AnimationState animState, boolean inherit, ButtonModel model) {
/* 106 */     super(animState, inherit); SimpleButtonModel simpleButtonModel;
/* 107 */     this.mouseButton = 0;
/* 108 */     this.stateChangedCB = new Runnable() {
/*     */         public void run() {
/* 110 */           Button.this.modelStateChanged();
/*     */         }
/*     */       };
/* 113 */     if (model == null) {
/* 114 */       simpleButtonModel = new SimpleButtonModel();
/*     */     }
/* 116 */     setModel((ButtonModel)simpleButtonModel);
/* 117 */     setCanAcceptKeyboardFocus(true);
/*     */   }
/*     */   
/*     */   public ButtonModel getModel() {
/* 121 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(ButtonModel model) {
/* 125 */     if (model == null) {
/* 126 */       throw new NullPointerException("model");
/*     */     }
/* 128 */     boolean isConnected = (getGUI() != null);
/* 129 */     if (this.model != null) {
/* 130 */       if (isConnected) {
/* 131 */         this.model.disconnect();
/*     */       }
/* 133 */       this.model.removeStateCallback(this.stateChangedCB);
/*     */     } 
/* 135 */     this.model = model;
/* 136 */     this.model.addStateCallback(this.stateChangedCB);
/* 137 */     if (isConnected) {
/* 138 */       this.model.connect();
/*     */     }
/* 140 */     modelStateChanged();
/* 141 */     AnimationState as = getAnimationState();
/* 142 */     as.dontAnimate(STATE_ARMED);
/* 143 */     as.dontAnimate(STATE_PRESSED);
/* 144 */     as.dontAnimate(STATE_HOVER);
/* 145 */     as.dontAnimate(STATE_SELECTED);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void widgetDisabled() {
/* 150 */     disarm();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/* 155 */     this.model.setEnabled(enabled);
/*     */   }
/*     */   
/*     */   public void addCallback(Runnable callback) {
/* 159 */     this.model.addActionCallback(callback);
/*     */   }
/*     */   
/*     */   public void removeCallback(Runnable callback) {
/* 163 */     this.model.removeActionCallback(callback);
/*     */   }
/*     */   
/*     */   public boolean hasCallbacks() {
/* 167 */     return this.model.hasActionCallbacks();
/*     */   }
/*     */   
/*     */   public String getText() {
/* 171 */     return this.text;
/*     */   }
/*     */   
/*     */   public void setText(String text) {
/* 175 */     this.text = text;
/* 176 */     updateText();
/*     */   }
/*     */   
/*     */   public int getMouseButton() {
/* 180 */     return this.mouseButton;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMouseButton(int mouseButton) {
/* 189 */     if (mouseButton < 0 || mouseButton > 1) {
/* 190 */       throw new IllegalArgumentException("mouseButton");
/*     */     }
/* 192 */     this.mouseButton = mouseButton;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 197 */     super.applyTheme(themeInfo);
/* 198 */     applyThemeButton(themeInfo);
/*     */   }
/*     */   
/*     */   protected void applyThemeButton(ThemeInfo themeInfo) {
/* 202 */     this.themeText = themeInfo.<String>getParameterValue("text", false, String.class);
/* 203 */     updateText();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void afterAddToGUI(GUI gui) {
/* 208 */     super.afterAddToGUI(gui);
/* 209 */     if (this.model != null) {
/* 210 */       this.model.connect();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void beforeRemoveFromGUI(GUI gui) {
/* 216 */     if (this.model != null) {
/* 217 */       this.model.disconnect();
/*     */     }
/* 219 */     super.beforeRemoveFromGUI(gui);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/* 224 */     return Math.max(super.getMinWidth(), getPreferredWidth());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/* 229 */     return Math.max(super.getMinHeight(), getPreferredHeight());
/*     */   }
/*     */   
/*     */   protected final void doCallback() {
/* 233 */     getModel().fireActionCallback();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 238 */     super.setVisible(visible);
/* 239 */     if (!visible) {
/* 240 */       disarm();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void disarm() {
/* 246 */     this.model.setHover(false);
/* 247 */     this.model.setArmed(false);
/* 248 */     this.model.setPressed(false);
/*     */   }
/*     */   
/*     */   void modelStateChanged() {
/* 252 */     super.setEnabled(this.model.isEnabled());
/* 253 */     AnimationState as = getAnimationState();
/* 254 */     as.setAnimationState(STATE_SELECTED, this.model.isSelected());
/* 255 */     as.setAnimationState(STATE_HOVER, this.model.isHover());
/* 256 */     as.setAnimationState(STATE_ARMED, this.model.isArmed());
/* 257 */     as.setAnimationState(STATE_PRESSED, this.model.isPressed());
/*     */   }
/*     */   
/*     */   void updateText() {
/* 261 */     if (this.text == null) {
/* 262 */       setCharSequence(TextUtil.notNull(this.themeText));
/*     */     } else {
/* 264 */       setCharSequence(this.text);
/*     */     } 
/* 266 */     invalidateLayout();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean handleEvent(Event evt) {
/* 271 */     if (evt.isMouseEvent()) {
/* 272 */       boolean hover = (evt.getType() != Event.Type.MOUSE_EXITED && isMouseInside(evt));
/* 273 */       this.model.setHover(hover);
/* 274 */       this.model.setArmed((hover && this.model.isPressed()));
/*     */     } 
/* 276 */     switch (evt.getType()) {
/*     */       case MOUSE_BTNDOWN:
/* 278 */         if (evt.getMouseButton() == this.mouseButton) {
/* 279 */           this.model.setPressed(true);
/* 280 */           this.model.setArmed(true);
/*     */         } 
/*     */         break;
/*     */       case MOUSE_BTNUP:
/* 284 */         if (evt.getMouseButton() == this.mouseButton) {
/* 285 */           this.model.setPressed(false);
/* 286 */           this.model.setArmed(false);
/*     */         } 
/*     */         break;
/*     */       case KEY_PRESSED:
/* 290 */         switch (evt.getKeyCode()) {
/*     */           case 28:
/*     */           case 57:
/* 293 */             if (!evt.isKeyRepeated()) {
/* 294 */               this.model.setPressed(true);
/* 295 */               this.model.setArmed(true);
/*     */             } 
/* 297 */             return true;
/*     */         } 
/*     */         break;
/*     */       case KEY_RELEASED:
/* 301 */         switch (evt.getKeyCode()) {
/*     */           case 28:
/*     */           case 57:
/* 304 */             this.model.setPressed(false);
/* 305 */             this.model.setArmed(false);
/* 306 */             return true;
/*     */         } 
/*     */         break;
/*     */       case POPUP_OPENED:
/* 310 */         this.model.setHover(false);
/*     */         break;
/*     */       
/*     */       case MOUSE_WHEEL:
/* 314 */         return false;
/*     */     } 
/* 316 */     if (super.handleEvent(evt)) {
/* 317 */       return true;
/*     */     }
/*     */     
/* 320 */     return evt.isMouseEvent();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Button.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */