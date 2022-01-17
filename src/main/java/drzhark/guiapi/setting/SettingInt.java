/*     */ package drzhark.guiapi.setting;
/*     */ 
/*     */ import drzhark.guiapi.ModSettings;
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
/*     */ public class SettingInt
/*     */   extends Setting<Integer>
/*     */ {
/*     */   public int maximumValue;
/*     */   public int minimumValue;
/*     */   public int stepValue;
/*     */   
/*     */   public SettingInt(String title) {
/*  32 */     this(title, 0, 0, 1, 100);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingInt(String title, int defValue) {
/*  43 */     this(title, defValue, 0, 1, 100);
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
/*     */   public SettingInt(String title, int defValue, int minValue, int maxValue) {
/*  55 */     this(title, defValue, minValue, 1, maxValue);
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
/*     */   public SettingInt(String title, int defValue, int minValue, int stepValue, int maxValue) {
/*  68 */     this.values.put("", Integer.valueOf(defValue));
/*  69 */     this.defaultValue = Integer.valueOf(defValue);
/*  70 */     this.minimumValue = minValue;
/*  71 */     this.stepValue = stepValue;
/*  72 */     this.maximumValue = maxValue;
/*  73 */     this.backendName = title;
/*  74 */     if (this.minimumValue > this.maximumValue) {
/*  75 */       int t = this.minimumValue;
/*  76 */       this.minimumValue = this.maximumValue;
/*  77 */       this.maximumValue = t;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void fromString(String s, String context) {
/*  83 */     this.values.put(context, new Integer(s));
/*  84 */     if (this.displayWidget != null) {
/*  85 */       this.displayWidget.update();
/*     */     }
/*  87 */     ModSettings.dbgout("fromstring " + s);
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer get(String context) {
/*  92 */     if (this.values.get(context) != null)
/*  93 */       return this.values.get(context); 
/*  94 */     if (this.values.get("") != null) {
/*  95 */       return this.values.get("");
/*     */     }
/*  97 */     return this.defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Integer v, String context) {
/* 103 */     ModSettings.dbgout("set " + v);
/* 104 */     if (this.stepValue > 1) {
/* 105 */       this.values.put(context, Integer.valueOf((int)(Math.round(v.intValue() / this.stepValue) * this.stepValue)));
/*     */     } else {
/* 107 */       this.values.put(context, v);
/*     */     } 
/* 109 */     if (this.parent != null) {
/* 110 */       this.parent.save(context);
/*     */     }
/* 112 */     if (this.displayWidget != null) {
/* 113 */       this.displayWidget.update();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString(String context) {
/* 119 */     return "" + get(context);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\setting\SettingInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */