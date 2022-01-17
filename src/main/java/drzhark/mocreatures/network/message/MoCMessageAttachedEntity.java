/*    */ package drzhark.mocreatures.network.message;
/*    */ 
/*    */ import drzhark.mocreatures.network.MoCMessageHandler;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ 
/*    */ public class MoCMessageAttachedEntity
/*    */   implements IMessage, IMessageHandler<MoCMessageAttachedEntity, IMessage>
/*    */ {
/*    */   public int sourceEntityId;
/*    */   public int targetEntityId;
/*    */   
/*    */   public MoCMessageAttachedEntity() {}
/*    */   
/*    */   public MoCMessageAttachedEntity(int sourceEntityId, int targetEntityId) {
/* 18 */     this.sourceEntityId = sourceEntityId;
/* 19 */     this.targetEntityId = targetEntityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 24 */     buffer.writeInt(this.sourceEntityId);
/* 25 */     buffer.writeInt(this.targetEntityId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 30 */     this.sourceEntityId = buffer.readInt();
/* 31 */     this.targetEntityId = buffer.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(MoCMessageAttachedEntity message, MessageContext ctx) {
/* 36 */     MoCMessageHandler.handleMessage(message, ctx);
/* 37 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     return String.format("MoCMessageAttachedEntity - sourceEntityId:%s, targetEntityId:%s", new Object[] { Integer.valueOf(this.sourceEntityId), Integer.valueOf(this.targetEntityId) });
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageAttachedEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */