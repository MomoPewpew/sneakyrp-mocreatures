/*     */ package drzhark.guiapi.widget;
/*     */ 
/*     */ import de.matthiasmann.twl.Button;
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import de.matthiasmann.twl.model.ButtonModel;
/*     */ import de.matthiasmann.twl.model.SimpleButtonModel;
/*     */ import drzhark.guiapi.GuiModScreen;
/*     */ import drzhark.guiapi.ModSettingScreen;
/*     */ import drzhark.guiapi.setting.SettingBoolean;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WidgetBoolean
/*     */   extends WidgetSetting
/*     */   implements Runnable
/*     */ {
/*     */   public Button button;
/*     */   public String falseText;
/*  28 */   public SettingBoolean settingReference = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String trueText;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetBoolean(SettingBoolean setting, String title) {
/*  43 */     this(setting, title, "true", "false");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetBoolean(SettingBoolean setting, String title, String truetext, String falsetext) {
/*  57 */     super(title);
/*  58 */     setTheme("");
/*  59 */     this.trueText = truetext;
/*  60 */     this.falseText = falsetext;
/*  61 */     SimpleButtonModel bmodel = new SimpleButtonModel();
/*  62 */     this.button = new Button((ButtonModel)bmodel);
/*  63 */     bmodel.addActionCallback(this);
/*  64 */     add((Widget)this.button);
/*  65 */     this.settingReference = setting;
/*  66 */     this.settingReference.displayWidget = this;
/*  67 */     update();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCallback(Runnable paramRunnable) {
/*  72 */     this.button.getModel().addActionCallback(paramRunnable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeCallback(Runnable paramRunnable) {
/*  77 */     this.button.getModel().removeActionCallback(paramRunnable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  82 */     if (this.settingReference != null) {
/*  83 */       this.settingReference.set(Boolean.valueOf(!this.settingReference.get(ModSettingScreen.guiContext).booleanValue()), ModSettingScreen.guiContext);
/*     */     }
/*  85 */     update();
/*  86 */     GuiModScreen.clicksound();
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  91 */     this.button.setText(userString());
/*     */   }
/*     */ 
/*     */   
/*     */   public String userString() {
/*  96 */     if (this.settingReference != null) {
/*  97 */       if (this.niceName.length() > 0)
/*     */       {
/*  99 */         return String.format("%s: %s", new Object[] { this.niceName, this.settingReference.get(ModSettingScreen.guiContext).booleanValue() ? this.trueText : this.falseText });
/*     */       }
/* 101 */       return this.settingReference.get(ModSettingScreen.guiContext).booleanValue() ? this.trueText : this.falseText;
/*     */     } 
/*     */     
/* 104 */     if (this.niceName.length() > 0) {
/* 105 */       return String.format("%s: %s", new Object[] { this.niceName, "no value" });
/*     */     }
/* 107 */     return "no value or title";
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetBoolean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */