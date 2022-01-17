/*    */ package drzhark.mocreatures.client.gui.helpers;
/*    */ 
/*    */ import drzhark.guiapi.setting.SettingFloat;
/*    */ import drzhark.mocreatures.configuration.MoCConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MoCSettingFloat
/*    */   extends SettingFloat
/*    */ {
/*    */   public String category;
/*    */   private MoCConfiguration config;
/*    */   
/*    */   public MoCSettingFloat(String title) {
/* 18 */     super(title, 0.0F, 0.0F, 0.1F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MoCSettingFloat(String title, float defValue) {
/* 29 */     super(title, defValue, 0.0F, 0.1F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MoCSettingFloat(String title, float defValue, float minValue, float maxValue) {
/* 41 */     super(title, defValue, minValue, 0.1F, maxValue);
/*    */   }
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
/*    */   public MoCSettingFloat(String title, float defValue, float minValue, float stepValue, float maxValue) {
/* 54 */     super(title, defValue, minValue, stepValue, maxValue);
/*    */   }
/*    */   
/*    */   public MoCSettingFloat(MoCConfiguration config, String cat, String title, float defValue, float minValue, float stepValue, float maxValue) {
/* 58 */     super(title, defValue, minValue, stepValue, maxValue);
/* 59 */     this.category = cat;
/* 60 */     this.config = config;
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(Float v, String context) {
/* 65 */     if (this.stepValue > 0.0F) {
/* 66 */       this.values.put(context, Float.valueOf((float)(Math.round(v.floatValue() * 100.0D) / 100.0D)));
/*    */     } else {
/* 68 */       this.values.put(context, v);
/*    */     } 
/* 70 */     if (this.parent != null) {
/* 71 */       ((MoCSettings)this.parent).save(context, this.backendName, this.category, this.config);
/*    */     }
/* 73 */     if (this.displayWidget != null)
/* 74 */       this.displayWidget.update(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\gui\helpers\MoCSettingFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */