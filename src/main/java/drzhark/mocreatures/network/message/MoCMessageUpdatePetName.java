package drzhark.mocreatures.network.message;

import drzhark.mocreatures.MoCPetData;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCEntity;
import drzhark.mocreatures.entity.IMoCTameable;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


public class MoCMessageUpdatePetName
  implements IMessage, IMessageHandler<MoCMessageUpdatePetName, IMessage>
{
  public String name;
  public int entityId;

  public MoCMessageUpdatePetName() {}

  public MoCMessageUpdatePetName(int entityId) {
    this.entityId = entityId;
  }

  public MoCMessageUpdatePetName(int entityId, String name) {
    this.entityId = entityId;
    this.name = name;
  }


  public void toBytes(ByteBuf buffer) {
    ByteBufUtils.writeUTF8String(buffer, this.name);
    ByteBufUtils.writeVarInt(buffer, this.entityId, 5);
  }


  public void fromBytes(ByteBuf buffer) {
    this.name = ByteBufUtils.readUTF8String(buffer);
    this.entityId = ByteBufUtils.readVarInt(buffer, 5);
  }


  public IMessage onMessage(MoCMessageUpdatePetName message, MessageContext ctx) {
    Entity pet = null;
    List<Entity> entList = (ctx.getServerHandler()).player.world.loadedEntityList;
    UUID ownerUniqueId = null;

    for (Entity ent : entList) {
      if (ent.getEntityId() == message.entityId && ent instanceof IMoCTameable) {
        ((IMoCEntity)ent).setPetName(message.name);
        ownerUniqueId = ((IMoCEntity)ent).getOwnerId();
        pet = ent;

        break;
      }
    }
    MoCPetData petData = MoCreatures.instance.mapData.getPetData(ownerUniqueId);
    if (petData != null && pet != null && ((IMoCTameable)pet).getOwnerPetId() != -1) {
      int id = ((IMoCTameable)pet).getOwnerPetId();
      NBTTagList tag = petData.getOwnerRootNBT().getTagList("TamedList", 10);
      for (int i = 0; i < tag.tagCount(); i++) {
        NBTTagCompound nbt = tag.getCompoundTagAt(i);
        if (nbt.getInteger("PetId") == id) {
          nbt.setString("Name", message.name);
          ((IMoCTameable)pet).setPetName(message.name);
        }
      }
    }
    return null;
  }


  public String toString() {
    return String.format("MoCMessageUpdatePetName - entityId:%s, name:%s", new Object[] { Integer.valueOf(this.entityId), this.name });
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\message\MoCMessageUpdatePetName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
