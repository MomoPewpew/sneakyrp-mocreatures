/*    */ package drzhark.mocreatures.client.gui.helpers;
/*    */ 
/*    */ import drzhark.guiapi.setting.SettingBoolean;
/*    */ import drzhark.mocreatures.configuration.MoCConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MoCSettingBoolean
/*    */   extends SettingBoolean
/*    */ {
/*    */   public String category;
/*    */   private MoCConfiguration config;
/*    */   
/*    */   public MoCSettingBoolean(String name) {
/* 18 */     super(name, Boolean.valueOf(false));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MoCSettingBoolean(String name, Boolean defValue) {
/* 28 */     super(name, defValue);
/*    */   }
/*    */   
/*    */   public MoCSettingBoolean(MoCConfiguration config, String cat, String name, Boolean defValue) {
/* 32 */     super(name, defValue);
/* 33 */     this.category = cat;
/* 34 */     this.config = config;
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(Boolean v, String context) {
/* 39 */     this.values.put(context, v);
/* 40 */     if (this.parent != null) {
/* 41 */       ((MoCSettings)this.parent).save(context, this.backendName, this.category, this.config);
/*    */     }
/* 43 */     if (this.displayWidget != null)
/* 44 */       this.displayWidget.update(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\gui\helpers\MoCSettingBoolean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */