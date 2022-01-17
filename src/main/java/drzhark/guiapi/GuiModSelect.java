/*    */ package drzhark.guiapi;
/*    */ 
/*    */ import de.matthiasmann.twl.Widget;
/*    */ import drzhark.guiapi.widget.WidgetClassicTwocolumn;
/*    */ import drzhark.guiapi.widget.WidgetSimplewindow;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiModSelect
/*    */   extends GuiModScreen
/*    */ {
/*    */   static {
/*    */   
/*    */   }
/*    */   
/*    */   private static void selectScreen(Integer i) {
/* 23 */     GuiModScreen.show(((ModSettingScreen)ModSettingScreen.modScreens.get(i.intValue())).theWidget);
/* 24 */     GuiModScreen.clicksound();
/*    */   }
/*    */   
/*    */   protected GuiModSelect(GuiScreen screen) {
/* 28 */     super(screen);
/* 29 */     WidgetClassicTwocolumn w = new WidgetClassicTwocolumn(new Widget[0]);
/* 30 */     w.verticalPadding = 10;
/* 31 */     for (int i = 0; i < ModSettingScreen.modScreens.size(); i++) {
/* 32 */       ModSettingScreen m = ModSettingScreen.modScreens.get(i);
/* 33 */       w.add((Widget)GuiApiHelper.makeButton(m.buttonTitle, "selectScreen", GuiModSelect.class, Boolean.valueOf(false), new Class[] { Integer.class }, new Object[] { Integer.valueOf(i) }));
/*    */     } 
/* 35 */     WidgetSimplewindow mainwidget = new WidgetSimplewindow((Widget)w, "Select a Mod");
/* 36 */     mainwidget.hPadding = 0;
/* 37 */     mainwidget.mainWidget.setTheme("scrollpane-notch");
/* 38 */     this.mainwidget = (Widget)mainwidget;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\GuiModSelect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */