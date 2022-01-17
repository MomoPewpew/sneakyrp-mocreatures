/*    */ package drzhark.mocreatures.client.gui.helpers;
/*    */ 
/*    */ import drzhark.guiapi.setting.SettingInt;
/*    */ import drzhark.mocreatures.configuration.MoCConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MoCSettingInt
/*    */   extends SettingInt
/*    */ {
/*    */   public String category;
/*    */   private MoCConfiguration config;
/*    */   
/*    */   public MoCSettingInt(String title) {
/* 18 */     super(title, 0, 0, 1, 100);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MoCSettingInt(String title, int defValue) {
/* 29 */     super(title, defValue, 0, 1, 100);
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
/*    */   public MoCSettingInt(String title, int defValue, int minValue, int maxValue) {
/* 41 */     super(title, defValue, minValue, 1, maxValue);
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
/*    */   public MoCSettingInt(String title, int defValue, int minValue, int stepValue, int maxValue) {
/* 54 */     super(title, defValue, minValue, stepValue, maxValue);
/*    */   }
/*    */   
/*    */   public MoCSettingInt(MoCConfiguration config, String cat, String title, int defValue, int minValue, int stepValue, int maxValue) {
/* 58 */     super(title, defValue, minValue, stepValue, maxValue);
/* 59 */     this.category = cat;
/* 60 */     this.config = config;
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(Integer v, String context) {
/* 65 */     if (this.stepValue > 1) {
/* 66 */       this.values.put(context, Integer.valueOf((int)(Math.round(v.intValue() / this.stepValue) * this.stepValue)));
/*    */     } else {
/* 68 */       this.values.put(context, v);
/*    */     } 
/*    */     
/* 71 */     if (this.parent != null) {
/* 72 */       ((MoCSettings)this.parent).save(context, this.backendName, this.category, this.config);
/*    */     }
/*    */     
/* 75 */     if (this.displayWidget != null)
/* 76 */       this.displayWidget.update(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\gui\helpers\MoCSettingInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */