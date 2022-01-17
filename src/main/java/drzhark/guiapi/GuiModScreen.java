/*     */ package drzhark.guiapi;
/*     */ 
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
/*     */ import de.matthiasmann.twl.renderer.lwjgl.RenderScale;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.PositionedSoundRecord;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.init.SoundEvents;
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
/*     */ public class GuiModScreen
/*     */   extends GuiScreen
/*     */ {
/*     */   public static GuiModScreen currentScreen;
/*     */   
/*     */   public static void back() {
/*  34 */     if (currentScreen != null) {
/*  35 */       Minecraft m = ModSettings.getMcinst();
/*  36 */       m.displayGuiScreen(currentScreen.parentScreen);
/*  37 */       if (currentScreen.parentScreen instanceof GuiModScreen) {
/*  38 */         currentScreen = (GuiModScreen)currentScreen.parentScreen;
/*  39 */         currentScreen.setActive();
/*     */       } else {
/*  41 */         currentScreen = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clicksound() {
/*  51 */     Minecraft m = ModSettings.getMcinst();
/*  52 */     m.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void show(GuiModScreen screen) {
/*  63 */     Minecraft m = ModSettings.getMcinst();
/*  64 */     m.displayGuiScreen(screen);
/*  65 */     screen.setActive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void show(Widget screen) {
/*  76 */     show(new GuiModScreen(currentScreen, screen));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public int backgroundType = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Widget mainwidget;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GuiScreen parentScreen;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GuiModScreen(GuiScreen screen) {
/* 108 */     this.parentScreen = screen;
/* 109 */     currentScreen = this;
/* 110 */     this.allowUserInput = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GuiModScreen(GuiScreen screen, Widget widget) {
/* 120 */     this.mainwidget = widget;
/* 121 */     this.parentScreen = screen;
/* 122 */     currentScreen = this;
/* 123 */     this.allowUserInput = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int var1, int var2, float var3) {
/* 128 */     switch (this.backgroundType) {
/*     */       case 0:
/* 130 */         drawDefaultBackground();
/*     */         break;
/*     */ 
/*     */       
/*     */       case 1:
/* 135 */         drawDefaultBackground();
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     LWJGLRenderer var4 = (LWJGLRenderer)(GuiWidgetScreen.getInstance()).gui.getRenderer();
/*     */     
/* 144 */     ScaledResolution var5 = new ScaledResolution((GuiWidgetScreen.getInstance()).minecraftInstance);
/* 145 */     RenderScale.scale = var5.getScaleFactor();
/* 146 */     var4.syncViewportSize();
/* 147 */     (GuiWidgetScreen.getInstance()).gui.update();
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleInput() {}
/*     */ 
/*     */   
/*     */   private void setActive() {
/* 155 */     GuiWidgetScreen.getInstance().setScreen(this.mainwidget);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\GuiModScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */