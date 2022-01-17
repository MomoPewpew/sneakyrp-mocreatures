/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.IntegerModel;
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
/*     */ public class ValueAdjusterInt
/*     */   extends ValueAdjuster
/*     */ {
/*     */   private int value;
/*     */   private int minValue;
/*  43 */   private int maxValue = 100;
/*     */   private int dragStartValue;
/*     */   private IntegerModel model;
/*     */   private Runnable modelCallback;
/*     */   
/*     */   public ValueAdjusterInt() {
/*  49 */     setTheme("valueadjuster");
/*  50 */     setDisplayText();
/*     */   }
/*     */ 
/*     */   
/*     */   public ValueAdjusterInt(IntegerModel model) {
/*  55 */     setTheme("valueadjuster");
/*  56 */     setModel(model);
/*     */   }
/*     */   
/*     */   public int getMaxValue() {
/*  60 */     if (this.model != null) {
/*  61 */       this.maxValue = this.model.getMaxValue();
/*     */     }
/*  63 */     return this.maxValue;
/*     */   }
/*     */   
/*     */   public int getMinValue() {
/*  67 */     if (this.model != null) {
/*  68 */       this.minValue = this.model.getMinValue();
/*     */     }
/*  70 */     return this.minValue;
/*     */   }
/*     */   
/*     */   public void setMinMaxValue(int minValue, int maxValue) {
/*  74 */     if (maxValue < minValue) {
/*  75 */       throw new IllegalArgumentException("maxValue < minValue");
/*     */     }
/*  77 */     this.minValue = minValue;
/*  78 */     this.maxValue = maxValue;
/*  79 */     setValue(this.value);
/*     */   }
/*     */   
/*     */   public int getValue() {
/*  83 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(int value) {
/*  87 */     value = Math.max(getMinValue(), Math.min(getMaxValue(), value));
/*  88 */     if (this.value != value) {
/*  89 */       this.value = value;
/*  90 */       if (this.model != null) {
/*  91 */         this.model.setValue(value);
/*     */       }
/*  93 */       setDisplayText();
/*     */     } 
/*     */   }
/*     */   
/*     */   public IntegerModel getModel() {
/*  98 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(IntegerModel model) {
/* 102 */     if (this.model != model) {
/* 103 */       removeModelCallback();
/* 104 */       this.model = model;
/* 105 */       if (model != null) {
/* 106 */         this.minValue = model.getMinValue();
/* 107 */         this.maxValue = model.getMaxValue();
/* 108 */         addModelCallback();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected String onEditStart() {
/* 116 */     return formatText();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean onEditEnd(String text) {
/*     */     try {
/* 122 */       setValue(Integer.parseInt(text));
/* 123 */       return true;
/* 124 */     } catch (NumberFormatException ex) {
/* 125 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected String validateEdit(String text) {
/*     */     try {
/* 132 */       Integer.parseInt(text);
/* 133 */       return null;
/* 134 */     } catch (NumberFormatException ex) {
/* 135 */       return ex.toString();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onEditCanceled() {}
/*     */ 
/*     */   
/*     */   protected boolean shouldStartEdit(char ch) {
/* 145 */     return ((ch >= '0' && ch <= '9') || ch == '-');
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDragStart() {
/* 150 */     this.dragStartValue = this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDragUpdate(int dragDelta) {
/* 155 */     int range = Math.max(1, Math.abs(getMaxValue() - getMinValue()));
/* 156 */     setValue(this.dragStartValue + dragDelta / Math.max(3, getWidth() / range));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDragCancelled() {
/* 161 */     setValue(this.dragStartValue);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doDecrement() {
/* 166 */     setValue(this.value - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doIncrement() {
/* 171 */     setValue(this.value + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String formatText() {
/* 176 */     return Integer.toString(this.value);
/*     */   }
/*     */   
/*     */   protected void syncWithModel() {
/* 180 */     cancelEdit();
/* 181 */     this.minValue = this.model.getMinValue();
/* 182 */     this.maxValue = this.model.getMaxValue();
/* 183 */     this.value = this.model.getValue();
/* 184 */     setDisplayText();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void afterAddToGUI(GUI gui) {
/* 189 */     super.afterAddToGUI(gui);
/* 190 */     addModelCallback();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void beforeRemoveFromGUI(GUI gui) {
/* 195 */     removeModelCallback();
/* 196 */     super.beforeRemoveFromGUI(gui);
/*     */   }
/*     */   
/*     */   protected void removeModelCallback() {
/* 200 */     if (this.model != null && this.modelCallback != null) {
/* 201 */       this.model.removeCallback(this.modelCallback);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void addModelCallback() {
/* 206 */     if (this.model != null && getGUI() != null) {
/* 207 */       if (this.modelCallback == null) {
/* 208 */         this.modelCallback = new ValueAdjuster.ModelCallback(this);
/*     */       }
/* 210 */       this.model.addCallback(this.modelCallback);
/* 211 */       syncWithModel();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ValueAdjusterInt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */