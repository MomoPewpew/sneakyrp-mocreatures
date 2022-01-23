package drzhark.mocreatures.client.handlers;

import drzhark.mocreatures.client.MoCClientProxy;
import drzhark.mocreatures.entity.IMoCEntity;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageEntityDive;
import drzhark.mocreatures.network.message.MoCMessageEntityJump;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class MoCKeyHandler {
  int keyCount;
  static KeyBinding diveBinding = new KeyBinding("MoCreatures Dive", 44, "key.categories.movement");






  public MoCKeyHandler() {
    ClientRegistry.registerKeyBinding(diveBinding);
  }

  @SubscribeEvent
  public void onInput(InputEvent event) {
    int keyPressed = Mouse.getEventButton() + -100;
    if (keyPressed == -101) {
      keyPressed = Keyboard.getEventKey();
    }

    EntityPlayerSP entityPlayerSP = MoCClientProxy.mc.player;
    if (entityPlayerSP == null) {
      return;
    }
    if ((FMLClientHandler.instance().getClient()).ingameGUI.getChatGUI().getChatOpen()) {
      return;
    }
    if (Keyboard.getEventKeyState() && entityPlayerSP.getRidingEntity() != null) {
      Keyboard.enableRepeatEvents(true);
    }


    boolean kbJump = MoCClientProxy.mc.gameSettings.keyBindJump.isKeyDown();
    boolean kbDive = diveBinding.isKeyDown();




    if (kbJump && entityPlayerSP != null && entityPlayerSP.getRidingEntity() != null && entityPlayerSP.getRidingEntity() instanceof IMoCEntity) {


      ((IMoCEntity)entityPlayerSP.getRidingEntity()).makeEntityJump();
      MoCMessageHandler.INSTANCE.sendToServer((IMessage)new MoCMessageEntityJump());
    }

    if (kbDive && entityPlayerSP != null && entityPlayerSP.getRidingEntity() != null && entityPlayerSP.getRidingEntity() instanceof IMoCEntity) {


      ((IMoCEntity)entityPlayerSP.getRidingEntity()).makeEntityDive();
      MoCMessageHandler.INSTANCE.sendToServer((IMessage)new MoCMessageEntityDive());
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\handlers\MoCKeyHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
