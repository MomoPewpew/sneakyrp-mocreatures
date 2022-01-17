/*    */ package drzhark.guiapi.widget;
/*    */ 
/*    */ import de.matthiasmann.twl.Widget;
/*    */ import de.matthiasmann.twl.model.FloatModel;
/*    */ import de.matthiasmann.twl.model.SimpleFloatModel;
/*    */ import drzhark.guiapi.ModSettingScreen;
/*    */ import drzhark.guiapi.ModSettings;
/*    */ import drzhark.guiapi.setting.SettingInt;
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
/*    */ public class WidgetInt
/*    */   extends WidgetSetting
/*    */   implements Runnable
/*    */ {
/*    */   public SettingInt settingReference;
/*    */   public WidgetSlider slider;
/*    */   
/*    */   public WidgetInt(SettingInt setting, String title) {
/* 32 */     super(title);
/* 33 */     setTheme("");
/* 34 */     this.settingReference = setting;
/* 35 */     this.settingReference.displayWidget = this;
/*    */     
/* 37 */     SimpleFloatModel smodel = new SimpleFloatModel(this.settingReference.minimumValue, this.settingReference.maximumValue, ((Integer)this.settingReference.get()).intValue());
/* 38 */     this.slider = new WidgetSlider((FloatModel)smodel);
/* 39 */     this.slider.setFormat(String.format("%s: %%.0f", new Object[] { this.niceName }));
/* 40 */     if (this.settingReference.stepValue > 1 && this.settingReference.stepValue <= this.settingReference.maximumValue) {
/* 41 */       this.slider.setStepSize(this.settingReference.stepValue);
/*    */     }
/* 43 */     smodel.addCallback(this);
/* 44 */     add((Widget)this.slider);
/* 45 */     update();
/*    */   }
/*    */ 
/*    */   
/*    */   public void addCallback(Runnable paramRunnable) {
/* 50 */     this.slider.getModel().addCallback(paramRunnable);
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeCallback(Runnable paramRunnable) {
/* 55 */     this.slider.getModel().removeCallback(paramRunnable);
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 60 */     ModSettings.dbgout("run " + (int)this.slider.getValue());
/* 61 */     this.settingReference.set(Integer.valueOf((int)this.slider.getValue()), ModSettingScreen.guiContext);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 66 */     this.slider.setValue(this.settingReference.get(ModSettingScreen.guiContext).intValue());
/* 67 */     this.slider.setMinMaxValue(this.settingReference.minimumValue, this.settingReference.maximumValue);
/* 68 */     this.slider.setFormat(String.format("%s: %%.0f", new Object[] { this.niceName }));
/* 69 */     ModSettings.dbgout("update " + this.settingReference.get(ModSettingScreen.guiContext) + " -> " + (int)this.slider.getValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public String userString() {
/* 74 */     return String.format("%s: %.0d", new Object[] { this.niceName, this.settingReference.get(ModSettingScreen.guiContext) });
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */