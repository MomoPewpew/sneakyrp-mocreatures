/*    */ package drzhark.guiapi.widget;
/*    */ 
/*    */ import de.matthiasmann.twl.Button;
/*    */ import de.matthiasmann.twl.Widget;
/*    */ import de.matthiasmann.twl.model.ButtonModel;
/*    */ import de.matthiasmann.twl.model.SimpleButtonModel;
/*    */ import drzhark.guiapi.GuiModScreen;
/*    */ import drzhark.guiapi.ModSettingScreen;
/*    */ import drzhark.guiapi.ModSettings;
/*    */ import drzhark.guiapi.setting.SettingMulti;
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
/*    */ public class WidgetMulti
/*    */   extends WidgetSetting
/*    */   implements Runnable
/*    */ {
/*    */   public Button button;
/*    */   public SettingMulti value;
/*    */   
/*    */   public WidgetMulti(SettingMulti setting, String title) {
/* 36 */     super(title);
/* 37 */     setTheme("");
/* 38 */     this.value = setting;
/* 39 */     this.value.displayWidget = this;
/* 40 */     SimpleButtonModel model = new SimpleButtonModel();
/* 41 */     this.button = new Button((ButtonModel)model);
/* 42 */     model.addActionCallback(this);
/* 43 */     add((Widget)this.button);
/* 44 */     update();
/*    */   }
/*    */ 
/*    */   
/*    */   public void addCallback(Runnable paramRunnable) {
/* 49 */     this.button.getModel().addActionCallback(paramRunnable);
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeCallback(Runnable paramRunnable) {
/* 54 */     this.button.getModel().removeActionCallback(paramRunnable);
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 59 */     this.value.next(ModSettingScreen.guiContext);
/* 60 */     update();
/* 61 */     GuiModScreen.clicksound();
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 66 */     this.button.setText(userString());
/* 67 */     ModSettings.dbgout("multi update " + userString());
/*    */   }
/*    */ 
/*    */   
/*    */   public String userString() {
/* 72 */     if (this.niceName.length() > 0) {
/* 73 */       return String.format("%s: %s", new Object[] { this.niceName, this.value.getLabel(ModSettingScreen.guiContext) });
/*    */     }
/* 75 */     return this.value.getLabel(ModSettingScreen.guiContext);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetMulti.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */