/*    */ package drzhark.mocreatures.network.message;
/*    */ 
/*    */ import drzhark.mocreatures.network.MoCMessageHandler;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ 
/*    */ public class MoCMessageAnimation
/*    */   implements IMessage, IMessageHandler<MoCMessageAnimation, IMessage>
/*    */ {
/*    */   public int entityId;
/*    */   public int animationType;
/*    */   
/*    */   public MoCMessageAnimation() {}
/*    */   
/*    */   public MoCMessageAnimation(int entityId, int animationType) {
/* 18 */     this.entityId = entityId;
/* 19 */     this.animationType = animationType;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 24 */     buffer.writeInt(this.entityId);
/* 25 */     buffer.writeInt(this.animationType);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 30 */     this.entityId = buffer.readInt();
/* 31 */     this.animationType = buffer.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(MoCMessageAnimation message, MessageContext ctx) {
/* 36 */     MoCMessageHandler.handleMessage(message, ctx);
/* 37 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     return String.format("MoCMessageAnimation - entityId:%s, animationType:%s", new Object[] { Integer.valueOf(this.entityId), Integer.valueOf(this.animationType) });
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */