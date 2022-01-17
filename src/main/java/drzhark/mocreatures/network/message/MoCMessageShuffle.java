/*    */ package drzhark.mocreatures.network.message;
/*    */ 
/*    */ import drzhark.mocreatures.network.MoCMessageHandler;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ 
/*    */ public class MoCMessageShuffle
/*    */   implements IMessage, IMessageHandler<MoCMessageShuffle, IMessage>
/*    */ {
/*    */   public int entityId;
/*    */   public boolean flag;
/*    */   
/*    */   public MoCMessageShuffle() {}
/*    */   
/*    */   public MoCMessageShuffle(int entityId, boolean flag) {
/* 18 */     this.entityId = entityId;
/* 19 */     this.flag = flag;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 24 */     buffer.writeInt(this.entityId);
/* 25 */     buffer.writeBoolean(this.flag);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 30 */     this.entityId = buffer.readInt();
/* 31 */     this.flag = buffer.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(MoCMessageShuffle message, MessageContext ctx) {
/* 36 */     MoCMessageHandler.handleMessage(message, ctx);
/* 37 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     return String.format("MoCMessageShuffle - entityId:%s, flag:%s", new Object[] { Integer.valueOf(this.entityId), Boolean.valueOf(this.flag) });
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageShuffle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */