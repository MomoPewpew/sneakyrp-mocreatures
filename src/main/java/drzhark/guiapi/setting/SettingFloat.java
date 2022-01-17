/*     */ package drzhark.guiapi.setting;
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
/*     */ public class SettingFloat
/*     */   extends Setting<Float>
/*     */ {
/*     */   public float maximumValue;
/*     */   public float minimumValue;
/*     */   public float stepValue;
/*     */   
/*     */   public SettingFloat(String title) {
/*  30 */     this(title, 0.0F, 0.0F, 0.1F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingFloat(String title, float defValue) {
/*  41 */     this(title, defValue, 0.0F, 0.1F, 1.0F);
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
/*     */   public SettingFloat(String title, float defValue, float minValue, float maxValue) {
/*  53 */     this(title, defValue, minValue, 0.1F, maxValue);
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
/*     */   public SettingFloat(String title, float defValue, float minValue, float stepValue, float maxValue) {
/*  66 */     this.values.put("", Float.valueOf(defValue));
/*  67 */     this.defaultValue = Float.valueOf(defValue);
/*  68 */     this.minimumValue = minValue;
/*  69 */     this.stepValue = stepValue;
/*  70 */     this.maximumValue = maxValue;
/*  71 */     this.backendName = title;
/*  72 */     if (this.minimumValue > this.maximumValue) {
/*  73 */       float t = this.minimumValue;
/*  74 */       this.minimumValue = this.maximumValue;
/*  75 */       this.maximumValue = t;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void fromString(String s, String context) {
/*  81 */     this.values.put(context, new Float(s));
/*  82 */     if (this.displayWidget != null) {
/*  83 */       this.displayWidget.update();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Float get(String context) {
/*  89 */     if (this.values.get(context) != null)
/*  90 */       return this.values.get(context); 
/*  91 */     if (this.values.get("") != null) {
/*  92 */       return this.values.get("");
/*     */     }
/*  94 */     return this.defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Float v, String context) {
/* 100 */     if (this.stepValue > 0.0F) {
/* 101 */       this.values.put(context, Float.valueOf(Math.round(v.floatValue() / this.stepValue) * this.stepValue));
/*     */     } else {
/* 103 */       this.values.put(context, v);
/*     */     } 
/* 105 */     if (this.parent != null) {
/* 106 */       this.parent.save(context);
/*     */     }
/* 108 */     if (this.displayWidget != null) {
/* 109 */       this.displayWidget.update();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString(String context) {
/* 115 */     return "" + get(context);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\setting\SettingFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */