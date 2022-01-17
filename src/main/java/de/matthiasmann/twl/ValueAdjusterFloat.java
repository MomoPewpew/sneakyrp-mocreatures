/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.FloatModel;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.ParseException;
/*     */ import java.util.IllegalFormatException;
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
/*     */ public class ValueAdjusterFloat
/*     */   extends ValueAdjuster
/*     */ {
/*     */   private float value;
/*     */   private float minValue;
/*  47 */   private float maxValue = 100.0F;
/*     */   private float dragStartValue;
/*  49 */   private float stepSize = 1.0F;
/*     */   private FloatModel model;
/*     */   private Runnable modelCallback;
/*  52 */   private String format = "%.2f";
/*  53 */   private Locale locale = Locale.ENGLISH;
/*     */   
/*     */   public ValueAdjusterFloat() {
/*  56 */     setTheme("valueadjuster");
/*  57 */     setDisplayText();
/*     */   }
/*     */ 
/*     */   
/*     */   public ValueAdjusterFloat(FloatModel model) {
/*  62 */     setTheme("valueadjuster");
/*  63 */     setModel(model);
/*     */   }
/*     */   
/*     */   public float getMaxValue() {
/*  67 */     return this.maxValue;
/*     */   }
/*     */   
/*     */   public float getMinValue() {
/*  71 */     return this.minValue;
/*     */   }
/*     */   
/*     */   public void setMinMaxValue(float minValue, float maxValue) {
/*  75 */     if (maxValue < minValue) {
/*  76 */       throw new IllegalArgumentException("maxValue < minValue");
/*     */     }
/*  78 */     this.minValue = minValue;
/*  79 */     this.maxValue = maxValue;
/*  80 */     setValue(this.value);
/*     */   }
/*     */   
/*     */   public float getValue() {
/*  84 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(float value) {
/*  88 */     if (value > this.maxValue) {
/*  89 */       value = this.maxValue;
/*  90 */     } else if (value < this.minValue) {
/*  91 */       value = this.minValue;
/*     */     } 
/*  93 */     if (this.value != value) {
/*  94 */       this.value = value;
/*  95 */       if (this.model != null) {
/*  96 */         this.model.setValue(value);
/*     */       }
/*  98 */       setDisplayText();
/*     */     } 
/*     */   }
/*     */   
/*     */   public float getStepSize() {
/* 103 */     return this.stepSize;
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
/*     */ 
/*     */   
/*     */   public void setStepSize(float stepSize) {
/* 117 */     if (stepSize <= 0.0F) {
/* 118 */       throw new IllegalArgumentException("stepSize");
/*     */     }
/* 120 */     this.stepSize = stepSize;
/*     */   }
/*     */   
/*     */   public FloatModel getModel() {
/* 124 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(FloatModel model) {
/* 128 */     if (this.model != model) {
/* 129 */       removeModelCallback();
/* 130 */       this.model = model;
/* 131 */       if (model != null) {
/* 132 */         this.minValue = model.getMinValue();
/* 133 */         this.maxValue = model.getMaxValue();
/* 134 */         addModelCallback();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getFormat() {
/* 140 */     return this.format;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFormat(String format) throws IllegalFormatException {
/* 145 */     String.format(this.locale, format, new Object[] { Float.valueOf(42.0F) });
/* 146 */     this.format = format;
/*     */   }
/*     */   
/*     */   public Locale getLocale() {
/* 150 */     return this.locale;
/*     */   }
/*     */   
/*     */   public void setLocale(Locale locale) {
/* 154 */     if (locale == null) {
/* 155 */       throw new NullPointerException("locale");
/*     */     }
/* 157 */     this.locale = locale;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String onEditStart() {
/* 162 */     return formatText();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean onEditEnd(String text) {
/*     */     try {
/* 168 */       setValue(parseText(text));
/* 169 */       return true;
/* 170 */     } catch (ParseException ex) {
/* 171 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected String validateEdit(String text) {
/*     */     try {
/* 178 */       parseText(text);
/* 179 */       return null;
/* 180 */     } catch (ParseException ex) {
/* 181 */       return ex.toString();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onEditCanceled() {}
/*     */ 
/*     */   
/*     */   protected boolean shouldStartEdit(char ch) {
/* 191 */     return ((ch >= '0' && ch <= '9') || ch == '-' || ch == '.');
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDragStart() {
/* 196 */     this.dragStartValue = this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDragUpdate(int dragDelta) {
/* 201 */     float range = Math.max(1.0E-4F, Math.abs(getMaxValue() - getMinValue()));
/* 202 */     setValue(this.dragStartValue + dragDelta / Math.max(3.0F, getWidth() / range));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDragCancelled() {
/* 207 */     setValue(this.dragStartValue);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doDecrement() {
/* 212 */     setValue(this.value - getStepSize());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doIncrement() {
/* 217 */     setValue(this.value + getStepSize());
/*     */   }
/*     */ 
/*     */   
/*     */   protected String formatText() {
/* 222 */     return String.format(this.locale, this.format, new Object[] { Float.valueOf(this.value) });
/*     */   }
/*     */   
/*     */   protected float parseText(String value) throws ParseException {
/* 226 */     return NumberFormat.getNumberInstance(this.locale).parse(value).floatValue();
/*     */   }
/*     */   
/*     */   protected void syncWithModel() {
/* 230 */     cancelEdit();
/* 231 */     this.minValue = this.model.getMinValue();
/* 232 */     this.maxValue = this.model.getMaxValue();
/* 233 */     this.value = this.model.getValue();
/* 234 */     setDisplayText();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void afterAddToGUI(GUI gui) {
/* 239 */     super.afterAddToGUI(gui);
/* 240 */     addModelCallback();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void beforeRemoveFromGUI(GUI gui) {
/* 245 */     removeModelCallback();
/* 246 */     super.beforeRemoveFromGUI(gui);
/*     */   }
/*     */   
/*     */   protected void removeModelCallback() {
/* 250 */     if (this.model != null && this.modelCallback != null) {
/* 251 */       this.model.removeCallback(this.modelCallback);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void addModelCallback() {
/* 256 */     if (this.model != null && getGUI() != null) {
/* 257 */       if (this.modelCallback == null) {
/* 258 */         this.modelCallback = new ValueAdjuster.ModelCallback(this);
/*     */       }
/* 260 */       this.model.addCallback(this.modelCallback);
/* 261 */       syncWithModel();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ValueAdjusterFloat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */