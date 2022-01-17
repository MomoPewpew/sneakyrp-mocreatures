/*    */ package drzhark.mocreatures.network.message;
/*    */ 
/*    */ import drzhark.mocreatures.network.MoCMessageHandler;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ 
/*    */ public class MoCMessageAppear
/*    */   implements IMessage, IMessageHandler<MoCMessageAppear, IMessage>
/*    */ {
/*    */   public int entityId;
/*    */   
/*    */   public MoCMessageAppear() {}
/*    */   
/*    */   public MoCMessageAppear(int entityId) {
/* 17 */     this.entityId = entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 22 */     buffer.writeInt(this.entityId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 27 */     this.entityId = buffer.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(MoCMessageAppear message, MessageContext ctx) {
/* 32 */     MoCMessageHandler.handleMessage(message, ctx);
/* 33 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 38 */     return String.format("MoCMessageAppear - entityId:%s", new Object[] { Integer.valueOf(this.entityId) });
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageAppear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */