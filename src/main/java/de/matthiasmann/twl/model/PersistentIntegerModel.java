/*     */ package de.matthiasmann.twl.model;
/*     */ 
/*     */ import java.util.prefs.Preferences;
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
/*     */ public class PersistentIntegerModel
/*     */   extends AbstractIntegerModel
/*     */ {
/*     */   private final Preferences prefs;
/*     */   private final String prefKey;
/*     */   private final int minValue;
/*     */   private final int maxValue;
/*     */   private int value;
/*     */   
/*     */   public PersistentIntegerModel(Preferences prefs, String prefKey, int minValue, int maxValue, int defaultValue) {
/*  48 */     if (maxValue < minValue) {
/*  49 */       throw new IllegalArgumentException("maxValue < minValue");
/*     */     }
/*  51 */     if (prefs == null) {
/*  52 */       throw new NullPointerException("prefs");
/*     */     }
/*  54 */     if (prefKey == null) {
/*  55 */       throw new NullPointerException("prefKey");
/*     */     }
/*  57 */     this.prefs = prefs;
/*  58 */     this.prefKey = prefKey;
/*  59 */     this.minValue = minValue;
/*  60 */     this.maxValue = maxValue;
/*  61 */     setValue(prefs.getInt(prefKey, defaultValue));
/*     */   }
/*     */   
/*     */   public PersistentIntegerModel(int minValue, int maxValue, int value) {
/*  65 */     if (maxValue < minValue) {
/*  66 */       throw new IllegalArgumentException("maxValue < minValue");
/*     */     }
/*  68 */     this.prefs = null;
/*  69 */     this.prefKey = null;
/*  70 */     this.minValue = minValue;
/*  71 */     this.maxValue = maxValue;
/*  72 */     setValue(value);
/*     */   }
/*     */   
/*     */   public int getValue() {
/*  76 */     return this.value;
/*     */   }
/*     */   
/*     */   public int getMinValue() {
/*  80 */     return this.minValue;
/*     */   }
/*     */   
/*     */   public int getMaxValue() {
/*  84 */     return this.maxValue;
/*     */   }
/*     */   
/*     */   public void setValue(int value) {
/*  88 */     if (value > this.maxValue) {
/*  89 */       value = this.maxValue;
/*  90 */     } else if (value < this.minValue) {
/*  91 */       value = this.minValue;
/*     */     } 
/*  93 */     if (this.value != value) {
/*  94 */       this.value = value;
/*  95 */       storeSetting();
/*  96 */       doCallback();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void storeSetting() {
/* 101 */     if (this.prefs != null)
/* 102 */       this.prefs.putInt(this.prefKey, this.value); 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\PersistentIntegerModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */