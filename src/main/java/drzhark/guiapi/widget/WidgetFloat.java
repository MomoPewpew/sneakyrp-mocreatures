/*    */ package drzhark.guiapi.widget;
/*    */ 
/*    */ import de.matthiasmann.twl.Widget;
/*    */ import de.matthiasmann.twl.model.FloatModel;
/*    */ import de.matthiasmann.twl.model.SimpleFloatModel;
/*    */ import drzhark.guiapi.ModSettingScreen;
/*    */ import drzhark.guiapi.setting.SettingFloat;
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
/*    */ public class WidgetFloat
/*    */   extends WidgetSetting
/*    */   implements Runnable
/*    */ {
/*    */   public int decimalPlaces;
/*    */   public SettingFloat settingReference;
/*    */   public WidgetSlider slider;
/*    */   
/*    */   public WidgetFloat(SettingFloat setting, String title) {
/* 36 */     this(setting, title, 2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WidgetFloat(SettingFloat setting, String title, int _decimalPlaces) {
/* 47 */     super(title);
/* 48 */     setTheme("");
/* 49 */     this.decimalPlaces = _decimalPlaces;
/* 50 */     this.settingReference = setting;
/* 51 */     this.settingReference.displayWidget = this;
/*    */     
/* 53 */     SimpleFloatModel smodel = new SimpleFloatModel(this.settingReference.minimumValue, this.settingReference.maximumValue, ((Float)this.settingReference.get()).floatValue());
/* 54 */     smodel.addCallback(this);
/* 55 */     this.slider = new WidgetSlider((FloatModel)smodel);
/* 56 */     if (this.settingReference.stepValue > 0.0F && this.settingReference.stepValue <= this.settingReference.maximumValue) {
/* 57 */       this.slider.setStepSize(this.settingReference.stepValue);
/*    */     }
/* 59 */     this.slider.setFormat(String.format("%s: %%.%df", new Object[] { this.niceName, Integer.valueOf(this.decimalPlaces) }));
/* 60 */     add((Widget)this.slider);
/* 61 */     update();
/*    */   }
/*    */ 
/*    */   
/*    */   public void addCallback(Runnable paramRunnable) {
/* 66 */     this.slider.getModel().addCallback(paramRunnable);
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeCallback(Runnable paramRunnable) {
/* 71 */     this.slider.getModel().removeCallback(paramRunnable);
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 76 */     this.settingReference.set(Float.valueOf(this.slider.getValue()), ModSettingScreen.guiContext);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 81 */     this.slider.setValue(this.settingReference.get(ModSettingScreen.guiContext).floatValue());
/* 82 */     this.slider.setMinMaxValue(this.settingReference.minimumValue, this.settingReference.maximumValue);
/* 83 */     this.slider.setFormat(String.format("%s: %%.%df", new Object[] { this.niceName, Integer.valueOf(this.decimalPlaces) }));
/*    */   }
/*    */ 
/*    */   
/*    */   public String userString() {
/* 88 */     String l = String.format("%02d", new Object[] { Integer.valueOf(this.decimalPlaces) });
/* 89 */     return String.format("%s: %." + l + "f", new Object[] { this.niceName, this.settingReference });
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */