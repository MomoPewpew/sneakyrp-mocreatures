/*    */ package drzhark.mocreatures.client.gui.helpers;
/*    */ 
/*    */ import drzhark.guiapi.setting.SettingText;
/*    */ import drzhark.mocreatures.configuration.MoCConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MoCSettingText
/*    */   extends SettingText
/*    */ {
/*    */   public String category;
/*    */   private MoCConfiguration config;
/*    */   
/*    */   public MoCSettingText(String title, String defaulttext) {
/* 18 */     super(title, defaulttext);
/*    */   }
/*    */   
/*    */   public MoCSettingText(MoCConfiguration config, String title, String defaulttext) {
/* 22 */     super(title, defaulttext);
/* 23 */     this.config = config;
/*    */   }
/*    */   
/*    */   public MoCSettingText(MoCConfiguration config, String cat, String title, String defaulttext) {
/* 27 */     super(title, defaulttext);
/* 28 */     this.category = cat;
/* 29 */     this.config = config;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromString(String s, String context) {
/* 34 */     this.values.put(context, s);
/* 35 */     if (this.displayWidget != null) {
/* 36 */       this.displayWidget.update();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String get(String context) {
/* 42 */     if (this.values.get(context) != null)
/* 43 */       return (String)this.values.get(context); 
/* 44 */     if (this.values.get("") != null) {
/* 45 */       return (String)this.values.get("");
/*    */     }
/* 47 */     return (String)this.defaultValue;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void set(String v, String context) {
/* 53 */     this.values.put(context, v);
/* 54 */     if (this.parent != null) {
/* 55 */       ((MoCSettings)this.parent).save(context, this.backendName, this.category, this.config);
/*    */     }
/* 57 */     if (this.displayWidget != null) {
/* 58 */       this.displayWidget.update();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString(String context) {
/* 64 */     return get(context);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\gui\helpers\MoCSettingText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */