/*    */ package drzhark.mocreatures.network.message;
/*    */ 
/*    */ import drzhark.mocreatures.MoCPetData;
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import drzhark.mocreatures.entity.IMoCEntity;
/*    */ import drzhark.mocreatures.entity.IMoCTameable;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.nbt.NBTTagList;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ 
/*    */ 
/*    */ public class MoCMessageUpdatePetName
/*    */   implements IMessage, IMessageHandler<MoCMessageUpdatePetName, IMessage>
/*    */ {
/*    */   public String name;
/*    */   public int entityId;
/*    */   
/*    */   public MoCMessageUpdatePetName() {}
/*    */   
/*    */   public MoCMessageUpdatePetName(int entityId) {
/* 28 */     this.entityId = entityId;
/*    */   }
/*    */   
/*    */   public MoCMessageUpdatePetName(int entityId, String name) {
/* 32 */     this.entityId = entityId;
/* 33 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 38 */     ByteBufUtils.writeUTF8String(buffer, this.name);
/* 39 */     ByteBufUtils.writeVarInt(buffer, this.entityId, 5);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 44 */     this.name = ByteBufUtils.readUTF8String(buffer);
/* 45 */     this.entityId = ByteBufUtils.readVarInt(buffer, 5);
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(MoCMessageUpdatePetName message, MessageContext ctx) {
/* 50 */     Entity pet = null;
/* 51 */     List<Entity> entList = (ctx.getServerHandler()).player.world.loadedEntityList;
/* 52 */     UUID ownerUniqueId = null;
/*    */     
/* 54 */     for (Entity ent : entList) {
/* 55 */       if (ent.getEntityId() == message.entityId && ent instanceof IMoCTameable) {
/* 56 */         ((IMoCEntity)ent).setPetName(message.name);
/* 57 */         ownerUniqueId = ((IMoCEntity)ent).getOwnerId();
/* 58 */         pet = ent;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 63 */     MoCPetData petData = MoCreatures.instance.mapData.getPetData(ownerUniqueId);
/* 64 */     if (petData != null && pet != null && ((IMoCTameable)pet).getOwnerPetId() != -1) {
/* 65 */       int id = ((IMoCTameable)pet).getOwnerPetId();
/* 66 */       NBTTagList tag = petData.getOwnerRootNBT().getTagList("TamedList", 10);
/* 67 */       for (int i = 0; i < tag.tagCount(); i++) {
/* 68 */         NBTTagCompound nbt = tag.getCompoundTagAt(i);
/* 69 */         if (nbt.getInteger("PetId") == id) {
/* 70 */           nbt.setString("Name", message.name);
/* 71 */           ((IMoCTameable)pet).setPetName(message.name);
/*    */         } 
/*    */       } 
/*    */     } 
/* 75 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 80 */     return String.format("MoCMessageUpdatePetName - entityId:%s, name:%s", new Object[] { Integer.valueOf(this.entityId), this.name });
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageUpdatePetName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */