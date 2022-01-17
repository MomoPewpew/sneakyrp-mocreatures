/*    */ package de.matthiasmann.twl.model;
/*    */ 
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
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
/*    */ public class PersistentEnumModel<T extends Enum<T>>
/*    */   extends AbstractEnumModel<T>
/*    */ {
/*    */   private final Preferences prefs;
/*    */   private final String prefKey;
/*    */   private T value;
/*    */   
/*    */   public PersistentEnumModel(Preferences prefs, String prefKey, T defaultValue) {
/* 49 */     this(prefs, prefKey, defaultValue.getDeclaringClass(), defaultValue);
/*    */   }
/*    */   
/*    */   public PersistentEnumModel(Preferences prefs, String prefKey, Class<T> enumClass, T defaultValue) {
/* 53 */     super(enumClass);
/* 54 */     if (prefs == null) {
/* 55 */       throw new NullPointerException("prefs");
/*    */     }
/* 57 */     if (prefKey == null) {
/* 58 */       throw new NullPointerException("prefKey");
/*    */     }
/* 60 */     if (defaultValue == null) {
/* 61 */       throw new NullPointerException("value");
/*    */     }
/* 63 */     this.prefs = prefs;
/* 64 */     this.prefKey = prefKey;
/*    */     
/* 66 */     T storedValue = defaultValue;
/* 67 */     String storedStr = prefs.get(prefKey, null);
/* 68 */     if (storedStr != null) {
/*    */       try {
/* 70 */         storedValue = Enum.valueOf(enumClass, storedStr);
/* 71 */       } catch (IllegalArgumentException ex) {
/* 72 */         Logger.getLogger(PersistentEnumModel.class.getName()).log(Level.WARNING, "Unable to parse value '" + storedStr + "' of key '" + prefKey + "' of type " + enumClass, ex);
/*    */       } 
/*    */     }
/* 75 */     setValue(storedValue);
/*    */   }
/*    */   
/*    */   public T getValue() {
/* 79 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(T value) {
/* 83 */     if (value == null) {
/* 84 */       throw new NullPointerException("value");
/*    */     }
/* 86 */     if (this.value != value) {
/* 87 */       this.value = value;
/* 88 */       storeSetting();
/* 89 */       doCallback();
/*    */     } 
/*    */   }
/*    */   
/*    */   private void storeSetting() {
/* 94 */     if (this.prefs != null)
/* 95 */       this.prefs.put(this.prefKey, this.value.name()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\PersistentEnumModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */