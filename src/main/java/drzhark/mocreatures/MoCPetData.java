package drzhark.mocreatures;

import drzhark.mocreatures.entity.IMoCTameable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;

public class MoCPetData
{
  private NBTTagCompound ownerData = new NBTTagCompound();
  private NBTTagList tamedList = new NBTTagList();
  private BitSet IDMap = new BitSet(1024);

  private ArrayList<Integer> usedPetIds = new ArrayList<>();

  public MoCPetData(IMoCTameable pet) {
    this.ownerData.setTag("TamedList", (NBTBase)this.tamedList);
  }

  public MoCPetData(NBTTagCompound nbt, UUID owner) {
    this.ownerData = nbt;
    this.tamedList = nbt.getTagList("TamedList", 10);
    loadPetDataMap(nbt.getCompoundTag("PetIdData"));
  }

  public int addPet(IMoCTameable pet) {
    BlockPos coords = new BlockPos(((Entity)pet).chunkCoordX, ((Entity)pet).chunkCoordY, ((Entity)pet).chunkCoordZ);
    NBTTagCompound petNBT = MoCTools.getEntityData((Entity)pet);
    if (this.tamedList != null) {
      int id = getNextFreePetId();
      petNBT.setInteger("PetId", id);
      NBTTagCompound petData = petNBT.copy();
      petData.setInteger("ChunkX", coords.getX());
      petData.setInteger("ChunkY", coords.getY());
      petData.setInteger("ChunkZ", coords.getZ());
      petData.setInteger("Dimension", ((Entity)pet).world.provider.getDimensionType().getId());
      this.tamedList.appendTag((NBTBase)petData);
      this.ownerData.setTag("PetIdData", (NBTBase)savePetDataMap());
      return id;
    }
    return -1;
  }


  public boolean removePet(int id) {
    for (int i = 0; i < this.tamedList.tagCount(); i++) {
      NBTTagCompound nbt = this.tamedList.getCompoundTagAt(i);
      if (nbt.hasKey("PetId") && nbt.getInteger("PetId") == id) {
        this.tamedList.removeTag(i);
        this.usedPetIds.remove(new Integer(id));
        this.IDMap.clear(id);
        if (this.usedPetIds.size() == 0) {
          this.IDMap.clear();
        }
        this.ownerData.setTag("PetIdData", (NBTBase)savePetDataMap());
        return true;
      }
    }
    return false;
  }

  public NBTTagCompound getPetData(int id) {
    if (this.tamedList != null) {
      for (int i = 0; i < this.tamedList.tagCount(); i++) {
        NBTTagCompound nbt = this.tamedList.getCompoundTagAt(i);
        if (nbt.hasKey("PetId") && nbt.getInteger("PetId") == id) {
          return nbt;
        }
      }
    }
    return null;
  }

  public NBTTagCompound getOwnerRootNBT() {
    return this.ownerData;
  }

  public NBTTagList getTamedList() {
    return this.tamedList;
  }

  public String getOwner() {
    if (this.ownerData != null) {
      return this.ownerData.getString("Owner");
    }
    return null;
  }


  public boolean getInAmulet(int petId) {
    NBTTagCompound petData = getPetData(petId);
    if (petData != null) {
      return petData.getBoolean("InAmulet");
    }
    return false;
  }

  public void setInAmulet(int petId, boolean flag) {
    NBTTagCompound petData = getPetData(petId);
    if (petData != null) {
      petData.setBoolean("InAmulet", flag);
    }
  }






  public int getNextFreePetId() {
    int next = 0;

    next = this.IDMap.nextClearBit(next);
    while (this.usedPetIds.contains(new Integer(next))) {
      this.IDMap.set(next);
    }
    this.usedPetIds.add(new Integer(next));
    return next;
  }



  public NBTTagCompound savePetDataMap() {
    int[] data = new int[(this.IDMap.length() + 32 - 1) / 32];
    NBTTagCompound dataMap = new NBTTagCompound();
    for (int i = 0; i < data.length; i++) {
      int val = 0;
      for (int j = 0; j < 32; j++) {
        val |= this.IDMap.get(i * 32 + j) ? (1 << j) : 0;
      }
      data[i] = val;
    }
    dataMap.setIntArray("PetIdArray", data);
    return dataMap;
  }

  public void loadPetDataMap(NBTTagCompound compoundTag) {
    if (compoundTag == null) {
      this.IDMap.clear();
    } else {
      int[] intArray = compoundTag.getIntArray("PetIdArray");
      for (int i = 0; i < intArray.length; i++) {
        for (int j = 0; j < 32; j++) {
          this.IDMap.set(i * 32 + j, ((intArray[i] & 1 << j) != 0));
        }
      }

      int next = 0;

      next = this.IDMap.nextClearBit(next);
      while (!this.usedPetIds.contains(new Integer(next)))
        this.usedPetIds.add(new Integer(next));
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\MoCPetData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
