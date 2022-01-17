/*     */ package drzhark.guiapi.widget;
/*     */ 
/*     */ import de.matthiasmann.twl.EditField;
/*     */ import de.matthiasmann.twl.Label;
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import de.matthiasmann.twl.model.StringModel;
/*     */ import de.matthiasmann.twl.utils.CallbackSupport;
/*     */ import drzhark.guiapi.GuiModScreen;
/*     */ import drzhark.guiapi.ModSettingScreen;
/*     */ import drzhark.guiapi.ModSettings;
/*     */ import drzhark.guiapi.setting.SettingText;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WidgetText
/*     */   extends WidgetSetting
/*     */   implements StringModel
/*     */ {
/*     */   private Runnable[] callbacks;
/*     */   public Label displayLabel;
/*     */   public EditField editField;
/*  38 */   public int setmode = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingText settingReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetText(SettingText setting, String title) {
/*  52 */     super(title);
/*  53 */     setTheme("");
/*  54 */     this.settingReference = setting;
/*  55 */     this.settingReference.displayWidget = this;
/*  56 */     this.editField = new EditField();
/*  57 */     add((Widget)this.editField);
/*  58 */     if (title != null) {
/*  59 */       this.displayLabel = new Label();
/*  60 */       this.displayLabel.setText(String.format("%s: ", new Object[] { this.niceName }));
/*  61 */       add((Widget)this.displayLabel);
/*     */     } 
/*  63 */     this.editField.setModel(this);
/*  64 */     update();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCallback(Runnable callback) {
/*  69 */     this.callbacks = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.callbacks, callback, Runnable.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValue() {
/*  74 */     return (String)this.settingReference.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public void layout() {
/*  79 */     if (this.displayLabel != null) {
/*  80 */       this.displayLabel.setPosition(getX(), getY() + getHeight() / 2 - this.displayLabel.computeTextHeight() / 2);
/*  81 */       this.displayLabel.setSize(this.displayLabel.computeTextWidth(), this.displayLabel.computeTextHeight());
/*  82 */       this.editField.setPosition(getX() + this.displayLabel.computeTextWidth(), getY());
/*  83 */       this.editField.setSize(getWidth() - this.displayLabel.computeTextWidth(), getHeight());
/*     */     } else {
/*  85 */       this.editField.setPosition(getX(), getY());
/*  86 */       this.editField.setSize(getWidth(), getHeight());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeCallback(Runnable callback) {
/*  92 */     this.callbacks = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, callback);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValue(String _value) {
/*  97 */     GuiModScreen.clicksound();
/*  98 */     ModSettings.dbgout(String.format("setvalue %s", new Object[] { this.editField.getText() }));
/*  99 */     if (this.setmode <= 0) {
/* 100 */       this.setmode = -1;
/* 101 */       this.settingReference.set(this.editField.getText(), ModSettingScreen.guiContext);
/* 102 */       this.setmode = 0;
/*     */     } 
/* 104 */     CallbackSupport.fireCallbacks(this.callbacks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 109 */     ModSettings.dbgout("update");
/* 110 */     if (this.displayLabel != null) {
/* 111 */       this.displayLabel.setText(String.format("%s: ", new Object[] { this.niceName }));
/*     */     }
/* 113 */     if (this.setmode >= 0) {
/* 114 */       this.setmode = 1;
/* 115 */       this.editField.setText(this.settingReference.get(ModSettingScreen.guiContext));
/* 116 */       this.setmode = 0;
/*     */     } 
/* 118 */     ModSettings.dbgout(String.format("update %s", new Object[] { this.editField.getText() }));
/*     */   }
/*     */ 
/*     */   
/*     */   public String userString() {
/* 123 */     return String.format("%s: %s", new Object[] { this.niceName, this.settingReference.get(ModSettingScreen.guiContext) });
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */