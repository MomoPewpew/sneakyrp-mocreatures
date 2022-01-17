/*    */ package drzhark.guiapi;
/*    */ 
/*    */ import drzhark.guiapi.widget.WidgetSetting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ 
/*    */ public class GuiApiButton
/*    */   extends GuiButton {
/*    */   public GuiApiButton(int par1, int par2, int par3, int par4, int par5, String par6Str) {
/* 10 */     super(par1, par2, par3, par4, par5, par6Str);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {
/* 15 */     if (super.mousePressed(par1Minecraft, par2, par3)) {
/* 16 */       par1Minecraft.gameSettings.saveOptions();
/* 17 */       ModSettingScreen.guiContext = "";
/* 18 */       WidgetSetting.updateAll();
/* 19 */       GuiModScreen.show(new GuiModSelect(par1Minecraft.currentScreen));
/* 20 */       return true;
/*    */     } 
/* 22 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\GuiApiButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */