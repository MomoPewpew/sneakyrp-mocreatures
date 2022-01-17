/*    */ package drzhark.mocreatures.network.message;
/*    */ 
/*    */ import drzhark.mocreatures.network.MoCMessageHandler;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ 
/*    */ public class MoCMessageTwoBytes
/*    */   implements IMessage, IMessageHandler<MoCMessageTwoBytes, IMessage>
/*    */ {
/*    */   public int entityId;
/*    */   public byte slot;
/*    */   public byte value;
/*    */   
/*    */   public MoCMessageTwoBytes() {}
/*    */   
/*    */   public MoCMessageTwoBytes(int entityId, byte slot, byte value) {
/* 19 */     this.entityId = entityId;
/* 20 */     this.slot = slot;
/* 21 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 26 */     buffer.writeInt(this.entityId);
/* 27 */     buffer.writeByte(this.slot);
/* 28 */     buffer.writeByte(this.value);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 33 */     this.entityId = buffer.readInt();
/* 34 */     this.slot = buffer.readByte();
/* 35 */     this.value = buffer.readByte();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(MoCMessageTwoBytes message, MessageContext ctx) {
/* 40 */     MoCMessageHandler.handleMessage(message, ctx);
/* 41 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return String.format("MoCMessageTwoBytes - entityId:%s, slot:%s, value:%s", new Object[] { Integer.valueOf(this.entityId), Byte.valueOf(this.slot), Byte.valueOf(this.value) });
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageTwoBytes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */