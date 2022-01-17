/*    */ package drzhark.guiapi.setting;
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
/*    */ public class SettingBoolean
/*    */   extends Setting<Boolean>
/*    */ {
/*    */   public SettingBoolean(String name) {
/* 17 */     this(name, Boolean.valueOf(false));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SettingBoolean(String name, Boolean defValue) {
/* 27 */     this.defaultValue = defValue;
/* 28 */     this.values.put("", this.defaultValue);
/* 29 */     this.backendName = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromString(String s, String context) {
/* 34 */     this.values.put(context, Boolean.valueOf(s.equals("true")));
/* 35 */     if (this.displayWidget != null) {
/* 36 */       this.displayWidget.update();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public Boolean get(String context) {
/* 42 */     if (this.values.get(context) != null)
/* 43 */       return this.values.get(context); 
/* 44 */     if (this.values.get("") != null) {
/* 45 */       return this.values.get("");
/*    */     }
/* 47 */     return this.defaultValue;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void set(Boolean v, String context) {
/* 53 */     this.values.put(context, v);
/* 54 */     if (this.parent != null) {
/* 55 */       this.parent.save(context);
/*    */     }
/* 57 */     if (this.displayWidget != null) {
/* 58 */       this.displayWidget.update();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString(String context) {
/* 64 */     return get(context).booleanValue() ? "true" : "false";
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\setting\SettingBoolean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */