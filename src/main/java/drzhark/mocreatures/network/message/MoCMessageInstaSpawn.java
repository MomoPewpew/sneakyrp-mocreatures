/*    */ package drzhark.mocreatures.network.message;
/*    */ 
/*    */ import drzhark.mocreatures.MoCTools;
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import drzhark.mocreatures.util.MoCLog;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ 
/*    */ public class MoCMessageInstaSpawn
/*    */   implements IMessage, IMessageHandler<MoCMessageInstaSpawn, IMessage> {
/*    */   public int entityId;
/*    */   public int numberToSpawn;
/*    */   
/*    */   public MoCMessageInstaSpawn() {}
/*    */   
/*    */   public MoCMessageInstaSpawn(int entityId, int numberToSpawn) {
/* 20 */     this.entityId = entityId;
/* 21 */     this.numberToSpawn = numberToSpawn;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 26 */     buffer.writeInt(this.entityId);
/* 27 */     buffer.writeInt(this.numberToSpawn);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 32 */     this.entityId = buffer.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(MoCMessageInstaSpawn message, MessageContext ctx) {
/* 37 */     if ((MoCreatures.proxy.getProxyMode() == 1 && MoCreatures.proxy.allowInstaSpawn) || MoCreatures.proxy.getProxyMode() == 2) {
/* 38 */       MoCTools.spawnNearPlayer((EntityPlayer)(ctx.getServerHandler()).player, message.entityId, message.numberToSpawn);
/* 39 */       if (MoCreatures.proxy.debug) {
/* 40 */         MoCLog.logger.info("Player " + (ctx.getServerHandler()).player.getName() + " used MoC instaspawner and got " + message.numberToSpawn + " creatures spawned");
/*    */       
/*    */       }
/*    */     }
/* 44 */     else if (MoCreatures.proxy.debug) {
/* 45 */       MoCLog.logger.info("Player " + (ctx.getServerHandler()).player.getName() + " tried to use MoC instaspawner, but the allowInstaSpawn setting is set to " + MoCreatures.proxy.allowInstaSpawn);
/*    */     } 
/*    */ 
/*    */     
/* 49 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     return String.format("MoCMessageInstaSpawn - entityId:%s, numberToSpawn:%s", new Object[] { Integer.valueOf(this.entityId), Integer.valueOf(this.numberToSpawn) });
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageInstaSpawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */