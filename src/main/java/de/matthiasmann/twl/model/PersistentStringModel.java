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
/*    */ public class PersistentStringModel
/*    */   extends HasCallback
/*    */   implements StringModel
/*    */ {
/*    */   private final Preferences prefs;
/*    */   private final String prefKey;
/*    */   private String value;
/*    */   
/*    */   public PersistentStringModel(Preferences prefs, String prefKey, String defaultValue) {
/* 45 */     if (prefs == null) {
/* 46 */       throw new NullPointerException("prefs");
/*    */     }
/* 48 */     if (prefKey == null) {
/* 49 */       throw new NullPointerException("prefKey");
/*    */     }
/* 51 */     if (defaultValue == null) {
/* 52 */       throw new NullPointerException("defaultValue");
/*    */     }
/* 54 */     this.prefs = prefs;
/* 55 */     this.prefKey = prefKey;
/* 56 */     this.value = prefs.get(prefKey, defaultValue);
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 60 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(String value) {
/* 64 */     if (value == null) {
/* 65 */       throw new NullPointerException("value");
/*    */     }
/* 67 */     if (!this.value.equals(value)) {
/* 68 */       this.value = value;
/* 69 */       this.prefs.put(this.prefKey, value);
/* 70 */       doCallback();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\PersistentStringModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */