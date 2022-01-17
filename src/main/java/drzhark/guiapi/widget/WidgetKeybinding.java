/*     */ package drzhark.guiapi.widget;
/*     */ 
/*     */ import de.matthiasmann.twl.Event;
/*     */ import de.matthiasmann.twl.ToggleButton;
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import de.matthiasmann.twl.model.BooleanModel;
/*     */ import de.matthiasmann.twl.model.SimpleBooleanModel;
/*     */ import drzhark.guiapi.GuiModScreen;
/*     */ import drzhark.guiapi.ModSettingScreen;
/*     */ import drzhark.guiapi.setting.SettingKey;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WidgetKeybinding
/*     */   extends WidgetSetting
/*     */   implements Runnable
/*     */ {
/*     */   public SimpleBooleanModel booleanModel;
/*  26 */   public int CLEARKEY = 211;
/*     */ 
/*     */ 
/*     */   
/*  30 */   public int NEVERMINDKEY = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SettingKey settingReference;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ToggleButton toggleButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetKeybinding(SettingKey setting, String title) {
/*  48 */     super(title);
/*  49 */     setTheme("");
/*  50 */     this.settingReference = setting;
/*  51 */     this.settingReference.displayWidget = this;
/*  52 */     this.booleanModel = new SimpleBooleanModel(false);
/*  53 */     this.toggleButton = new ToggleButton((BooleanModel)this.booleanModel);
/*  54 */     add((Widget)this.toggleButton);
/*  55 */     update();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCallback(Runnable paramRunnable) {
/*  60 */     this.booleanModel.addCallback(paramRunnable);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean handleEvent(Event evt) {
/*  65 */     if (evt.isKeyEvent() && !evt.isKeyPressedEvent() && this.booleanModel.getValue()) {
/*  66 */       System.out.println(Keyboard.getKeyName(evt.getKeyCode()));
/*  67 */       int tmpvalue = evt.getKeyCode();
/*  68 */       if (tmpvalue == this.CLEARKEY) {
/*  69 */         this.settingReference.set(Integer.valueOf(0), ModSettingScreen.guiContext);
/*  70 */       } else if (tmpvalue != this.NEVERMINDKEY) {
/*  71 */         this.settingReference.set(Integer.valueOf(tmpvalue), ModSettingScreen.guiContext);
/*     */       } 
/*  73 */       this.booleanModel.setValue(false);
/*  74 */       update();
/*  75 */       GuiModScreen.clicksound();
/*  76 */       return true;
/*     */     } 
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void keyboardFocusLost() {
/*  83 */     GuiModScreen.clicksound();
/*  84 */     this.booleanModel.setValue(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeCallback(Runnable paramRunnable) {
/*  89 */     this.booleanModel.removeCallback(paramRunnable);
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*  94 */     GuiModScreen.clicksound();
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  99 */     this.toggleButton.setText(userString());
/*     */   }
/*     */ 
/*     */   
/*     */   public String userString() {
/* 104 */     return String.format("%s: %s", new Object[] { this.niceName, Keyboard.getKeyName(this.settingReference.get(ModSettingScreen.guiContext).intValue()) });
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetKeybinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */