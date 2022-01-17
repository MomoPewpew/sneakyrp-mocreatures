/*    */ package de.matthiasmann.twl.model;
/*    */ 
/*    */ import de.matthiasmann.twl.Color;
/*    */ import java.util.prefs.Preferences;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PersistentColorModel
/*    */   extends HasCallback
/*    */   implements ColorModel
/*    */ {
/*    */   private final Preferences prefs;
/*    */   private final String prefKey;
/*    */   private Color value;
/*    */   private IllegalArgumentException initialError;
/*    */   
/*    */   public PersistentColorModel(Preferences prefs, String prefKey, Color defaultValue) {
/* 49 */     if (prefs == null) {
/* 50 */       throw new NullPointerException("prefs");
/*    */     }
/* 52 */     if (prefKey == null) {
/* 53 */       throw new NullPointerException("prefKey");
/*    */     }
/* 55 */     if (defaultValue == null) {
/* 56 */       throw new NullPointerException("defaultValue");
/*    */     }
/*    */     
/* 59 */     this.prefs = prefs;
/* 60 */     this.prefKey = prefKey;
/* 61 */     this.value = defaultValue;
/*    */     
/*    */     try {
/* 64 */       String text = prefs.get(prefKey, null);
/* 65 */       if (text != null) {
/* 66 */         Color aValue = Color.parserColor(text);
/* 67 */         if (aValue != null) {
/* 68 */           this.value = aValue;
/*    */         } else {
/* 70 */           this.initialError = new IllegalArgumentException("Unknown color name: " + text);
/*    */         } 
/*    */       } 
/* 73 */     } catch (IllegalArgumentException ex) {
/* 74 */       this.initialError = ex;
/*    */     } 
/*    */   }
/*    */   
/*    */   public IllegalArgumentException getInitialError() {
/* 79 */     return this.initialError;
/*    */   }
/*    */   
/*    */   public void clearInitialError() {
/* 83 */     this.initialError = null;
/*    */   }
/*    */   
/*    */   public Color getValue() {
/* 87 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(Color value) {
/* 91 */     if (this.value != value) {
/* 92 */       this.value = value;
/* 93 */       storeSettings();
/* 94 */       doCallback();
/*    */     } 
/*    */   }
/*    */   
/*    */   private void storeSettings() {
/* 99 */     this.prefs.put(this.prefKey, this.value.toString());
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\PersistentColorModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */