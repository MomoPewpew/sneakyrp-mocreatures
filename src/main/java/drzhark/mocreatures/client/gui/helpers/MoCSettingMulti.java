/*    */ package drzhark.mocreatures.client.gui.helpers;
/*    */ 
/*    */ import drzhark.guiapi.setting.SettingMulti;
/*    */ import drzhark.mocreatures.configuration.MoCConfiguration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MoCSettingMulti
/*    */   extends SettingMulti
/*    */ {
/*    */   public String category;
/*    */   public String previousValue;
/*    */   public MoCConfiguration config;
/*    */   
/*    */   public MoCSettingMulti(String title, int defValue, String... labelValues) {
/* 21 */     super(title, defValue, labelValues);
/*    */   }
/*    */   
/*    */   public MoCSettingMulti(MoCConfiguration config, String cat, String title, int defValue, String... labelValues) {
/* 25 */     super(title, defValue, labelValues);
/* 26 */     this.category = cat;
/* 27 */     this.config = config;
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
/*    */   public MoCSettingMulti(String title, String... labelValues) {
/* 39 */     super(title, 0, labelValues);
/*    */   }
/*    */   
/*    */   public MoCSettingMulti(MoCConfiguration config, String cat, String title, String... labelValues) {
/* 43 */     super(title, 0, labelValues);
/* 44 */     this.category = cat;
/* 45 */     this.config = config;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void next(String context) {
/* 55 */     this.previousValue = this.labelValues[get(context).intValue()];
/* 56 */     int tempvalue = get(context).intValue() + 1;
/* 57 */     while (tempvalue >= this.labelValues.length) {
/* 58 */       tempvalue -= this.labelValues.length;
/*    */     }
/* 60 */     set(Integer.valueOf(tempvalue), context);
/*    */   }
/*    */ 
/*    */   
/*    */   public void set(Integer v, String context) {
/* 65 */     this.values.put(context, v);
/* 66 */     if (this.parent != null) {
/* 67 */       ((MoCSettings)this.parent).save(context, this.backendName, this.category, this.config);
/*    */     }
/* 69 */     if (this.displayWidget != null)
/* 70 */       this.displayWidget.update(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\gui\helpers\MoCSettingMulti.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */