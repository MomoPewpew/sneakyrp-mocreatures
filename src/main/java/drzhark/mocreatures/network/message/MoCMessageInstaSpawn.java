package drzhark.mocreatures.network.message;

import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.util.MoCLog;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MoCMessageInstaSpawn
  implements IMessage, IMessageHandler<MoCMessageInstaSpawn, IMessage> {
  public int entityId;
  public int numberToSpawn;

  public MoCMessageInstaSpawn() {}

  public MoCMessageInstaSpawn(int entityId, int numberToSpawn) {
    this.entityId = entityId;
    this.numberToSpawn = numberToSpawn;
  }


  public void toBytes(ByteBuf buffer) {
    buffer.writeInt(this.entityId);
    buffer.writeInt(this.numberToSpawn);
  }


  public void fromBytes(ByteBuf buffer) {
    this.entityId = buffer.readInt();
  }


  public IMessage onMessage(MoCMessageInstaSpawn message, MessageContext ctx) {
    if ((MoCreatures.proxy.getProxyMode() == 1 && MoCreatures.proxy.allowInstaSpawn) || MoCreatures.proxy.getProxyMode() == 2) {
      MoCTools.spawnNearPlayer((EntityPlayer)(ctx.getServerHandler()).player, message.entityId, message.numberToSpawn);
      if (MoCreatures.proxy.debug) {
        MoCLog.logger.info("Player " + (ctx.getServerHandler()).player.getName() + " used MoC instaspawner and got " + message.numberToSpawn + " creatures spawned");

      }
    }
    else if (MoCreatures.proxy.debug) {
      MoCLog.logger.info("Player " + (ctx.getServerHandler()).player.getName() + " tried to use MoC instaspawner, but the allowInstaSpawn setting is set to " + MoCreatures.proxy.allowInstaSpawn);
    }


    return null;
  }


  public String toString() {
    return String.format("MoCMessageInstaSpawn - entityId:%s, numberToSpawn:%s", new Object[] { Integer.valueOf(this.entityId), Integer.valueOf(this.numberToSpawn) });
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageInstaSpawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
