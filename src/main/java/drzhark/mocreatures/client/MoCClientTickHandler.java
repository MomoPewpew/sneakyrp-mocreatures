/*    */ package drzhark.mocreatures.client;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MoCClientTickHandler
/*    */ {
/*    */   boolean inMenu;
/*    */   int lastTickRun;
/*    */   
/*    */   private void onTickInGame() {}
/*    */   
/*    */   private void onTickInGui(GuiScreen curScreen) {
/* 19 */     this.inMenu = true;
/* 20 */     this.lastTickRun = 0;
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void tickEnd(TickEvent.ClientTickEvent event) {
/* 25 */     if (event.type.equals(TickEvent.Type.CLIENT)) {
/* 26 */       GuiScreen curScreen = (Minecraft.getMinecraft()).currentScreen;
/* 27 */       if (curScreen != null) {
/* 28 */         onTickInGui(curScreen);
/*    */       } else {
/* 30 */         this.inMenu = false;
/* 31 */         onTickInGame();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\MoCClientTickHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */