/*    */ package de.matthiasmann.twl.model;
/*    */ 
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
/*    */ 
/*    */ public class PersistentBooleanModel
/*    */   extends HasCallback
/*    */   implements BooleanModel
/*    */ {
/*    */   private final Preferences prefs;
/*    */   private final String prefKey;
/*    */   private boolean value;
/*    */   
/*    */   public PersistentBooleanModel(Preferences prefs, String prefKey, boolean defaultValue) {
/* 48 */     if (prefs == null) {
/* 49 */       throw new NullPointerException("prefs");
/*    */     }
/* 51 */     if (prefKey == null) {
/* 52 */       throw new NullPointerException("prefKey");
/*    */     }
/* 54 */     this.prefs = prefs;
/* 55 */     this.prefKey = prefKey;
/* 56 */     this.value = prefs.getBoolean(prefKey, defaultValue);
/*    */   }
/*    */   
/*    */   public boolean getValue() {
/* 60 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(boolean value) {
/* 64 */     if (this.value != value) {
/* 65 */       this.value = value;
/* 66 */       storeSettings();
/* 67 */       doCallback();
/*    */     } 
/*    */   }
/*    */   
/*    */   private void storeSettings() {
/* 72 */     this.prefs.putBoolean(this.prefKey, this.value);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\PersistentBooleanModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */