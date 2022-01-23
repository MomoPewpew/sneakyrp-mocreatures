package drzhark.mocreatures.network.message;

import drzhark.mocreatures.entity.IMoCEntity;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;








public class MoCMessageEntityJump
  implements IMessage, IMessageHandler<MoCMessageEntityJump, IMessage>
{
  public void toBytes(ByteBuf buffer) {}

  public void fromBytes(ByteBuf buffer) {}

  public IMessage onMessage(MoCMessageEntityJump message, MessageContext ctx) {
    if ((ctx.getServerHandler()).player.getRidingEntity() != null && (ctx.getServerHandler()).player.getRidingEntity() instanceof IMoCEntity) {
      ((IMoCEntity)(ctx.getServerHandler()).player.getRidingEntity()).makeEntityJump();
    }
    return null;
  }


  public String toString() {
    return String.format("MoCMessageEntityJump", new Object[0]);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageEntityJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
