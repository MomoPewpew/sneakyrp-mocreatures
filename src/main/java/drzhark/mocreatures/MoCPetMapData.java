package drzhark.mocreatures;

import com.google.common.collect.Maps;
import drzhark.mocreatures.entity.IMoCTameable;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.DimensionManager;

public class MoCPetMapData
  extends WorldSavedData {
  private Map<UUID, MoCPetData> petMap = Maps.newHashMap();

  public MoCPetMapData(String par1Str) {
    super(par1Str);
    markDirty();
  }




  public MoCPetData getPetData(UUID ownerUniqueId) {
    return this.petMap.get(ownerUniqueId);
  }

  public Map<UUID, MoCPetData> getPetMap() {
    return this.petMap;
  }

  public boolean removeOwnerPet(IMoCTameable pet, int petId) {
    if (this.petMap.get(pet.getOwnerId()) != null)
    {
      if (((MoCPetData)this.petMap.get(pet.getOwnerId())).removePet(petId)) {
        markDirty();
        pet.setOwnerPetId(-1);
        return true;
      }
    }
    return false;
  }

  public void updateOwnerPet(IMoCTameable pet) {
    markDirty();
    if (pet.getOwnerPetId() == -1 || this.petMap.get(pet.getOwnerId()) == null) {
      UUID owner = MoCreatures.isServer() ? pet.getOwnerId() : (Minecraft.getMinecraft()).player.getUniqueID();
      MoCPetData petData = null;
      int id = -1;
      if (this.petMap.containsKey(owner)) {
        petData = this.petMap.get(owner);
        id = petData.addPet(pet);
      } else {

        petData = new MoCPetData(pet);
        id = petData.addPet(pet);
        this.petMap.put(owner, petData);
      }
      pet.setOwnerPetId(id);
    } else {

      UUID owner = pet.getOwnerId();
      MoCPetData petData = getPetData(owner);
      NBTTagCompound rootNBT = petData.getOwnerRootNBT();
      NBTTagList tag = rootNBT.getTagList("TamedList", 10);
      int id = -1;
      id = pet.getOwnerPetId();

      for (int i = 0; i < tag.tagCount(); i++) {
        NBTTagCompound nbt = tag.getCompoundTagAt(i);
        if (nbt.getInteger("PetId") == id) {

          nbt.setTag("Pos", (NBTBase)newDoubleNBTList(new double[] { ((Entity)pet).posX, ((Entity)pet).posY, ((Entity)pet).posZ }));
          nbt.setInteger("ChunkX", ((Entity)pet).chunkCoordX);
          nbt.setInteger("ChunkY", ((Entity)pet).chunkCoordY);
          nbt.setInteger("ChunkZ", ((Entity)pet).chunkCoordZ);
          nbt.setInteger("Dimension", ((Entity)pet).world.provider.getDimensionType().getId());
          nbt.setInteger("PetId", pet.getOwnerPetId());
        }
      }
    }
  }

  protected NBTTagList newDoubleNBTList(double... par1ArrayOfDouble) {
    NBTTagList nbttaglist = new NBTTagList();
    double[] adouble = par1ArrayOfDouble;
    int i = par1ArrayOfDouble.length;

    for (int j = 0; j < i; j++) {
      double d1 = adouble[j];
      nbttaglist.appendTag((NBTBase)new NBTTagDouble(d1));
    }

    return nbttaglist;
  }

  public boolean isExistingPet(UUID owner, IMoCTameable pet) {
    MoCPetData petData = MoCreatures.instance.mapData.getPetData(owner);
    if (petData != null) {
      NBTTagList tag = petData.getTamedList();
      for (int i = 0; i < tag.tagCount(); i++) {
        NBTTagCompound nbt = tag.getCompoundTagAt(i);
        if (nbt.getInteger("PetId") == pet.getOwnerPetId())
        {
          return true;
        }
      }
    }
    return false;
  }

  public void forceSave() {
    if (DimensionManager.getWorld(0) != null) {
      ISaveHandler saveHandler = DimensionManager.getWorld(0).getSaveHandler();
      if (saveHandler != null) {
        try {
          File file1 = saveHandler.getMapFileFromName("mocreatures");

          if (file1 != null) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            writeToNBT(nbttagcompound);
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setTag("data", (NBTBase)nbttagcompound);
            FileOutputStream fileoutputstream = new FileOutputStream(file1);
            CompressedStreamTools.writeCompressed(nbttagcompound1, fileoutputstream);
            fileoutputstream.close();
          }
        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }
    }
  }





  public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
    Iterator<String> iterator = par1NBTTagCompound.getKeySet().iterator();
    while (iterator.hasNext()) {
      String s = iterator.next();
      NBTTagCompound nbt = (NBTTagCompound)par1NBTTagCompound.getTag(s);
      UUID ownerUniqueId = UUID.fromString(s);

      if (!this.petMap.containsKey(ownerUniqueId)) {
        this.petMap.put(ownerUniqueId, new MoCPetData(nbt, ownerUniqueId));
      }
    }
  }







  public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound) {
    for (Map.Entry<UUID, MoCPetData> ownerEntry : this.petMap.entrySet()) {
      try {
        if (this.petMap.entrySet() != null && ownerEntry.getKey() != null)
        {
          par1NBTTagCompound.setTag(((UUID)ownerEntry.getKey()).toString(), (NBTBase)((MoCPetData)ownerEntry.getValue()).getOwnerRootNBT());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return par1NBTTagCompound;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\MoCPetMapData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
