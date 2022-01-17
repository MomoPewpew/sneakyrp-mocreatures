/*    */ package drzhark.mocreatures.network.message;
/*    */ 
/*    */ import drzhark.mocreatures.network.MoCMessageHandler;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ public class MoCMessageNameGUI
/*    */   implements IMessage, IMessageHandler<MoCMessageNameGUI, IMessage>
/*    */ {
/*    */   public int entityId;
/*    */   
/*    */   public MoCMessageNameGUI() {}
/*    */   
/*    */   public MoCMessageNameGUI(int entityId) {
/* 19 */     this.entityId = entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 24 */     buffer.writeInt(this.entityId);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 29 */     this.entityId = buffer.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(MoCMessageNameGUI message, MessageContext ctx) {
/* 34 */     if (ctx.side == Side.CLIENT) {
/* 35 */       handleClientMessage(message, ctx);
/*    */     }
/* 37 */     return null;
/*    */   }
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void handleClientMessage(MoCMessageNameGUI message, MessageContext ctx) {
/* 42 */     MoCMessageHandler.handleMessage(message, ctx);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 47 */     return String.format("MoCMessageNameGUI - entityId:%s", new Object[] { Integer.valueOf(this.entityId) });
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageNameGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */