/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.renderer.Font;
/*     */ import de.matthiasmann.twl.utils.CallbackSupport;
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
/*     */ public class Label
/*     */   extends TextWidget
/*     */ {
/*     */   public enum CallbackReason
/*     */   {
/*  44 */     CLICK,
/*  45 */     DOUBLE_CLICK;
/*     */   }
/*     */   
/*     */   private boolean autoSize = true;
/*     */   private Widget labelFor;
/*     */   private CallbackWithReason<?>[] callbacks;
/*     */   
/*     */   public Label() {
/*  53 */     this((AnimationState)null, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Label(AnimationState animState) {
/*  62 */     this(animState, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Label(AnimationState animState, boolean inherit) {
/*  72 */     super(animState, inherit);
/*     */   }
/*     */ 
/*     */   
/*     */   public Label(String text) {
/*  77 */     this();
/*  78 */     setText(text);
/*     */   }
/*     */   
/*     */   public void addCallback(CallbackWithReason<CallbackReason> cb) {
/*  82 */     this.callbacks = (CallbackWithReason<?>[])CallbackSupport.addCallbackToList((Object[])this.callbacks, cb, CallbackWithReason.class);
/*     */   }
/*     */   
/*     */   public void removeCallback(CallbackWithReason<CallbackReason> cb) {
/*  86 */     this.callbacks = (CallbackWithReason<?>[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, cb);
/*     */   }
/*     */   
/*     */   protected void doCallback(CallbackReason reason) {
/*  90 */     CallbackSupport.fireCallbacks((CallbackWithReason[])this.callbacks, reason);
/*     */   }
/*     */   
/*     */   public boolean isAutoSize() {
/*  94 */     return this.autoSize;
/*     */   }
/*     */   
/*     */   public void setAutoSize(boolean autoSize) {
/*  98 */     this.autoSize = autoSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 103 */     super.setFont(font);
/* 104 */     if (this.autoSize) {
/* 105 */       invalidateLayout();
/*     */     }
/*     */   }
/*     */   
/*     */   public String getText() {
/* 110 */     return getCharSequence().toString();
/*     */   }
/*     */   
/*     */   public void setText(String text) {
/* 114 */     text = TextUtil.notNull(text);
/* 115 */     if (!text.equals(getText())) {
/* 116 */       setCharSequence(text);
/* 117 */       if (this.autoSize) {
/* 118 */         invalidateLayout();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getTooltipContent() {
/* 125 */     Object toolTipContent = super.getTooltipContent();
/* 126 */     if (toolTipContent == null && this.labelFor != null) {
/* 127 */       return this.labelFor.getTooltipContent();
/*     */     }
/* 129 */     return toolTipContent;
/*     */   }
/*     */   
/*     */   public Widget getLabelFor() {
/* 133 */     return this.labelFor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLabelFor(Widget labelFor) {
/* 144 */     if (labelFor == this) {
/* 145 */       throw new IllegalArgumentException("labelFor == this");
/*     */     }
/* 147 */     this.labelFor = labelFor;
/*     */   }
/*     */   
/*     */   protected void applyThemeLabel(ThemeInfo themeInfo) {
/* 151 */     String themeText = themeInfo.<String>getParameterValue("text", false, String.class);
/* 152 */     if (themeText != null) {
/* 153 */       setText(themeText);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 159 */     super.applyTheme(themeInfo);
/* 160 */     applyThemeLabel(themeInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean requestKeyboardFocus() {
/* 165 */     if (this.labelFor != null) {
/* 166 */       return this.labelFor.requestKeyboardFocus();
/*     */     }
/* 168 */     return super.requestKeyboardFocus();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/* 174 */     return Math.max(super.getMinWidth(), getPreferredWidth());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/* 179 */     return Math.max(super.getMinHeight(), getPreferredHeight());
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean handleEvent(Event evt) {
/* 184 */     handleMouseHover(evt);
/* 185 */     if (evt.isMouseEvent()) {
/* 186 */       if (evt.getType() == Event.Type.MOUSE_CLICKED) {
/* 187 */         switch (evt.getMouseClickCount()) {
/*     */           case 1:
/* 189 */             handleClick(false);
/*     */             break;
/*     */           case 2:
/* 192 */             handleClick(true);
/*     */             break;
/*     */         } 
/*     */       }
/* 196 */       return (evt.getType() != Event.Type.MOUSE_WHEEL);
/*     */     } 
/* 198 */     return false;
/*     */   }
/*     */   
/*     */   protected void handleClick(boolean doubleClick) {
/* 202 */     doCallback(doubleClick ? CallbackReason.DOUBLE_CLICK : CallbackReason.CLICK);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Label.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */