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
/*    */ public class SettingText
/*    */   extends Setting<String>
/*    */ {
/*    */   public SettingText(String title, String defaulttext) {
/* 17 */     this.values.put("", defaulttext);
/* 18 */     this.defaultValue = defaulttext;
/* 19 */     this.backendName = title;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromString(String s, String context) {
/* 24 */     this.values.put(context, s);
/* 25 */     if (this.displayWidget != null) {
/* 26 */       this.displayWidget.update();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String get(String context) {
/* 32 */     if (this.values.get(context) != null)
/* 33 */       return this.values.get(context); 
/* 34 */     if (this.values.get("") != null) {
/* 35 */       return this.values.get("");
/*    */     }
/* 37 */     return this.defaultValue;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void set(String v, String context) {
/* 43 */     this.values.put(context, v);
/* 44 */     if (this.parent != null) {
/* 45 */       this.parent.save(context);
/*    */     }
/* 47 */     if (this.displayWidget != null) {
/* 48 */       this.displayWidget.update();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString(String context) {
/* 54 */     return get(context);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\setting\SettingText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */