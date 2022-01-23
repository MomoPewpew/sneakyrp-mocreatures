package drzhark.mocreatures.network.message;

import drzhark.mocreatures.network.MoCMessageHandler;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MoCMessageHeart
  implements IMessage, IMessageHandler<MoCMessageHeart, IMessage>
{
  public int entityId;

  public MoCMessageHeart() {}

  public MoCMessageHeart(int entityId) {
    this.entityId = entityId;
  }


  public void toBytes(ByteBuf buffer) {
    buffer.writeInt(this.entityId);
  }


  public void fromBytes(ByteBuf buffer) {
    this.entityId = buffer.readInt();
  }


  public IMessage onMessage(MoCMessageHeart message, MessageContext ctx) {
    MoCMessageHandler.handleMessage(message, ctx);
    return null;
  }


  public String toString() {
    return String.format("MoCMessageHeart - entityId:%s", new Object[] { Integer.valueOf(this.entityId) });
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageHeart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
