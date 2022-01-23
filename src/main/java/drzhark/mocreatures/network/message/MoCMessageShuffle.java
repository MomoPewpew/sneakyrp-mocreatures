package drzhark.mocreatures.network.message;

import drzhark.mocreatures.network.MoCMessageHandler;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MoCMessageShuffle
  implements IMessage, IMessageHandler<MoCMessageShuffle, IMessage>
{
  public int entityId;
  public boolean flag;

  public MoCMessageShuffle() {}

  public MoCMessageShuffle(int entityId, boolean flag) {
    this.entityId = entityId;
    this.flag = flag;
  }


  public void toBytes(ByteBuf buffer) {
    buffer.writeInt(this.entityId);
    buffer.writeBoolean(this.flag);
  }


  public void fromBytes(ByteBuf buffer) {
    this.entityId = buffer.readInt();
    this.flag = buffer.readBoolean();
  }


  public IMessage onMessage(MoCMessageShuffle message, MessageContext ctx) {
    MoCMessageHandler.handleMessage(message, ctx);
    return null;
  }


  public String toString() {
    return String.format("MoCMessageShuffle - entityId:%s, flag:%s", new Object[] { Integer.valueOf(this.entityId), Boolean.valueOf(this.flag) });
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageShuffle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
