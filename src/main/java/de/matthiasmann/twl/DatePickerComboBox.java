/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.DateModel;
/*     */ import java.text.DateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Locale;
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
/*     */ public class DatePickerComboBox
/*     */   extends ComboBoxBase
/*     */ {
/*     */   private final ComboboxLabel label;
/*     */   private final DatePicker datePicker;
/*     */   
/*     */   public DatePickerComboBox() {
/*  48 */     this(Locale.getDefault(), DateFormat.getDateInstance());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DatePickerComboBox(Locale locale, int style) {
/*  58 */     this(locale, DateFormat.getDateInstance(style, locale));
/*     */   }
/*     */   
/*     */   public DatePickerComboBox(Locale locale, DateFormat dateFormat) {
/*  62 */     L l = new L();
/*     */     
/*  64 */     this.label = new ComboboxLabel(getAnimationState());
/*  65 */     this.label.setTheme("display");
/*  66 */     this.label.addCallback(l);
/*     */     
/*  68 */     this.datePicker = new DatePicker(locale, dateFormat);
/*  69 */     this.datePicker.addCallback(l);
/*     */     
/*  71 */     this.popup.add(this.datePicker);
/*  72 */     this.popup.setTheme("datepickercomboboxPopup");
/*     */     
/*  74 */     this.button.getModel().addStateCallback(l);
/*     */     
/*  76 */     add(this.label);
/*     */   }
/*     */   
/*     */   public void setModel(DateModel model) {
/*  80 */     this.datePicker.setModel(model);
/*     */   }
/*     */   
/*     */   public DateModel getModel() {
/*  84 */     return this.datePicker.getModel();
/*     */   }
/*     */   
/*     */   public void setDateFormat(Locale locale, DateFormat dateFormat) {
/*  88 */     this.datePicker.setDateFormat(locale, dateFormat);
/*     */   }
/*     */   
/*     */   public DateFormat getDateFormat() {
/*  92 */     return this.datePicker.getDateFormat();
/*     */   }
/*     */   
/*     */   public Locale getLocale() {
/*  96 */     return this.datePicker.getLocale();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ComboboxLabel getLabel() {
/* 101 */     return this.label;
/*     */   }
/*     */   
/*     */   protected DatePicker getDatePicker() {
/* 105 */     return this.datePicker;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setPopupSize() {
/* 110 */     int minWidth = this.popup.getMinWidth();
/* 111 */     int minHeight = this.popup.getMinHeight();
/* 112 */     int popupWidth = computeSize(minWidth, this.popup.getPreferredWidth(), this.popup.getMaxWidth());
/*     */ 
/*     */     
/* 115 */     int popupHeight = computeSize(minHeight, this.popup.getPreferredHeight(), this.popup.getMaxHeight());
/*     */ 
/*     */     
/* 118 */     Widget container = this.popup.getParent();
/* 119 */     int popupMaxRight = container.getInnerRight();
/* 120 */     int popupMaxBottom = container.getInnerBottom();
/* 121 */     int x = getX();
/* 122 */     int y = getBottom();
/* 123 */     if (x + popupWidth > popupMaxRight) {
/* 124 */       if (getRight() - popupWidth >= container.getInnerX()) {
/* 125 */         x = getRight() - popupWidth;
/*     */       } else {
/* 127 */         x = popupMaxRight - minWidth;
/*     */       } 
/*     */     }
/* 130 */     if (y + popupHeight > popupMaxBottom) {
/* 131 */       if (getY() - popupHeight >= container.getInnerY()) {
/* 132 */         y = getY() - popupHeight;
/*     */       } else {
/* 134 */         y = popupMaxBottom - minHeight;
/*     */       } 
/*     */     }
/* 137 */     popupWidth = Math.min(popupWidth, popupMaxRight - x);
/* 138 */     popupHeight = Math.min(popupHeight, popupMaxBottom - y);
/* 139 */     this.popup.setPosition(x, y);
/* 140 */     this.popup.setSize(popupWidth, popupHeight);
/*     */   }
/*     */   
/*     */   protected void updateLabel() {
/* 144 */     this.label.setText(this.datePicker.formatDate());
/*     */   }
/*     */   
/*     */   void updateHover() {
/* 148 */     getAnimationState().setAnimationState(Label.STATE_HOVER, (this.label.hover || this.button.getModel().isHover()));
/*     */   }
/*     */   
/*     */   protected class ComboboxLabel
/*     */     extends Label {
/*     */     boolean hover;
/*     */     
/*     */     public ComboboxLabel(AnimationState animState) {
/* 156 */       super(animState);
/* 157 */       setAutoSize(false);
/* 158 */       setClip(true);
/* 159 */       setTheme("display");
/*     */     }
/*     */ 
/*     */     
/*     */     public int getPreferredInnerHeight() {
/* 164 */       int prefHeight = super.getPreferredInnerHeight();
/* 165 */       if (getFont() != null) {
/* 166 */         prefHeight = Math.max(prefHeight, getFont().getLineHeight());
/*     */       }
/* 168 */       return prefHeight;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void handleMouseHover(Event evt) {
/* 173 */       if (evt.isMouseEvent()) {
/* 174 */         boolean newHover = (evt.getType() != Event.Type.MOUSE_EXITED);
/* 175 */         if (newHover != this.hover) {
/* 176 */           this.hover = newHover;
/* 177 */           DatePickerComboBox.this.updateHover();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class L implements Runnable, CallbackWithReason<Label.CallbackReason>, DatePicker.Callback {
/*     */     public void run() {
/* 185 */       DatePickerComboBox.this.updateHover();
/*     */     }
/*     */     
/*     */     public void callback(Label.CallbackReason reason) {
/* 189 */       DatePickerComboBox.this.openPopup();
/*     */     }
/*     */     
/*     */     public void calendarChanged(Calendar calendar) {
/* 193 */       DatePickerComboBox.this.updateLabel();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\DatePickerComboBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */