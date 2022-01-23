package drzhark.mocreatures.network.message;

import drzhark.mocreatures.network.MoCMessageHandler;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MoCMessageHealth
  implements IMessage, IMessageHandler<MoCMessageHealth, IMessage>
{
  public int entityId;
  public float health;

  public MoCMessageHealth() {}

  public MoCMessageHealth(int entityId, float health) {
    this.entityId = entityId;
    this.health = health;
  }


  public void toBytes(ByteBuf buffer) {
    buffer.writeInt(this.entityId);
    buffer.writeFloat(this.health);
  }


  public void fromBytes(ByteBuf buffer) {
    this.entityId = buffer.readInt();
    this.health = buffer.readFloat();
  }


  public IMessage onMessage(MoCMessageHealth message, MessageContext ctx) {
    MoCMessageHandler.handleMessage(message, ctx);
    return null;
  }


  public String toString() {
    return String.format("MoCMessageHealth - entityId:%s, health:%s", new Object[] { Integer.valueOf(this.entityId), Float.valueOf(this.health) });
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageHealth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
