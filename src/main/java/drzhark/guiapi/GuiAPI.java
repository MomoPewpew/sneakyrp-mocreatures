/*    */ package drzhark.guiapi;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiOptions;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiAPI
/*    */ {
/* 18 */   public Object cacheCheck = null;
/*    */   
/*    */   public Field controlListField;
/*    */   
/*    */   public List getControlList(GuiOptions gui) {
/*    */     try {
/* 24 */       return (List)this.controlListField.get(gui);
/* 25 */     } catch (Throwable e) {
/* 26 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void processGuiOptions(GuiOptions gui) {
/* 32 */     List<GuiApiButton> controlList = getControlList(gui);
/* 33 */     if (controlList == null) {
/*    */       return;
/*    */     }
/* 36 */     if (controlList.get(0) == this.cacheCheck) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 41 */     ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
/* 42 */     int width = scaledresolution.getScaledWidth();
/* 43 */     int height = scaledresolution.getScaledHeight();
/* 44 */     controlList.add(new GuiApiButton(300, width / 2 - 155, height / 6 + 12, 150, 20, "Global Mod Options"));
/*    */ 
/*    */     
/* 47 */     this.cacheCheck = controlList.get(0);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void clientTick(TickEvent.ClientTickEvent event) {
/* 52 */     if (event.type != TickEvent.Type.CLIENT) {
/*    */       return;
/*    */     }
/* 55 */     if (Minecraft.getMinecraft() == null) {
/*    */       return;
/*    */     }
/* 58 */     if ((Minecraft.getMinecraft()).currentScreen == null) {
/*    */       return;
/*    */     }
/* 61 */     if ((Minecraft.getMinecraft()).currentScreen instanceof GuiOptions)
/* 62 */       processGuiOptions((GuiOptions)(Minecraft.getMinecraft()).currentScreen); 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\GuiAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */