package drzhark.mocreatures.network.message;

import drzhark.mocreatures.network.MoCMessageHandler;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MoCMessageAttachedEntity
  implements IMessage, IMessageHandler<MoCMessageAttachedEntity, IMessage>
{
  public int sourceEntityId;
  public int targetEntityId;

  public MoCMessageAttachedEntity() {}

  public MoCMessageAttachedEntity(int sourceEntityId, int targetEntityId) {
    this.sourceEntityId = sourceEntityId;
    this.targetEntityId = targetEntityId;
  }


  public void toBytes(ByteBuf buffer) {
    buffer.writeInt(this.sourceEntityId);
    buffer.writeInt(this.targetEntityId);
  }


  public void fromBytes(ByteBuf buffer) {
    this.sourceEntityId = buffer.readInt();
    this.targetEntityId = buffer.readInt();
  }


  public IMessage onMessage(MoCMessageAttachedEntity message, MessageContext ctx) {
    MoCMessageHandler.handleMessage(message, ctx);
    return null;
  }


  public String toString() {
    return String.format("MoCMessageAttachedEntity - sourceEntityId:%s, targetEntityId:%s", new Object[] { Integer.valueOf(this.sourceEntityId), Integer.valueOf(this.targetEntityId) });
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageAttachedEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
