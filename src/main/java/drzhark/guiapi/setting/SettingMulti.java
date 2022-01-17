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
/*     */ public class SettingMulti
/*     */   extends Setting<Integer>
/*     */ {
/*     */   public String[] labelValues;
/*     */   
/*     */   public SettingMulti(String title, int defValue, String... labelValues) {
/*  26 */     if (labelValues.length == 0) {
/*     */       return;
/*     */     }
/*  29 */     this.values.put("", Integer.valueOf(defValue));
/*  30 */     this.defaultValue = Integer.valueOf(defValue);
/*  31 */     this.labelValues = labelValues;
/*  32 */     this.backendName = title;
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
/*     */   public SettingMulti(String title, String... labelValues) {
/*  44 */     this(title, 0, labelValues);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fromString(String s, String context) {
/*  49 */     int x = -1;
/*  50 */     for (int i = 0; i < this.labelValues.length; i++) {
/*  51 */       if (this.labelValues[i].equals(s)) {
/*  52 */         x = i;
/*     */       }
/*     */     } 
/*  55 */     if (x != -1) {
/*  56 */       this.values.put(context, Integer.valueOf(x));
/*     */     } else {
/*  58 */       this.values.put(context, Integer.valueOf((new Float(s)).intValue()));
/*     */     } 
/*  60 */     ModSettings.dbgout("fromstring multi " + s);
/*  61 */     if (this.displayWidget != null) {
/*  62 */       this.displayWidget.update();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Integer get(String context) {
/*  68 */     if (this.values.get(context) != null)
/*  69 */       return this.values.get(context); 
/*  70 */     if (this.values.get("") != null) {
/*  71 */       return this.values.get("");
/*     */     }
/*  73 */     return this.defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLabel() {
/*  83 */     return this.labelValues[get().intValue()];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLabel(String context) {
/*  93 */     return this.labelValues[get(context).intValue()];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void next() {
/* 100 */     next(ModSettings.currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void next(String context) {
/* 109 */     int tempvalue = get(context).intValue() + 1;
/* 110 */     while (tempvalue >= this.labelValues.length) {
/* 111 */       tempvalue -= this.labelValues.length;
/*     */     }
/* 113 */     set(Integer.valueOf(tempvalue), context);
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(Integer v, String context) {
/* 118 */     this.values.put(context, v);
/* 119 */     if (this.parent != null) {
/* 120 */       this.parent.save(context);
/*     */     }
/* 122 */     if (this.displayWidget != null) {
/* 123 */       this.displayWidget.update();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String v) {
/* 133 */     set(v, ModSettings.currentContext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(String v, String context) {
/* 143 */     int x = -1;
/* 144 */     for (int i = 0; i < this.labelValues.length; i++) {
/* 145 */       if (this.labelValues[i].equals(v)) {
/* 146 */         x = i;
/*     */       }
/*     */     } 
/* 149 */     if (x != -1) {
/* 150 */       set(Integer.valueOf(x), context);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString(String context) {
/* 156 */     return this.labelValues[get(context).intValue()];
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\setting\SettingMulti.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */