/*     */ package drzhark.mocreatures;
/*     */ 
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.BitSet;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ 
/*     */ public class MoCPetData
/*     */ {
/*  16 */   private NBTTagCompound ownerData = new NBTTagCompound();
/*  17 */   private NBTTagList tamedList = new NBTTagList();
/*  18 */   private BitSet IDMap = new BitSet(1024);
/*     */   
/*     */   private final UUID ownerUniqueId;
/*  21 */   private ArrayList<Integer> usedPetIds = new ArrayList<>();
/*     */   
/*     */   public MoCPetData(IMoCTameable pet) {
/*  24 */     this.ownerData.setTag("TamedList", (NBTBase)this.tamedList);
/*  25 */     this.ownerUniqueId = MoCreatures.isServer() ? pet.getOwnerId() : (Minecraft.getMinecraft()).player.getUniqueID();
/*     */   }
/*     */   
/*     */   public MoCPetData(NBTTagCompound nbt, UUID owner) {
/*  29 */     this.ownerData = nbt;
/*  30 */     this.tamedList = nbt.getTagList("TamedList", 10);
/*  31 */     this.ownerUniqueId = owner;
/*  32 */     loadPetDataMap(nbt.getCompoundTag("PetIdData"));
/*     */   }
/*     */   
/*     */   public int addPet(IMoCTameable pet) {
/*  36 */     BlockPos coords = new BlockPos(((Entity)pet).chunkCoordX, ((Entity)pet).chunkCoordY, ((Entity)pet).chunkCoordZ);
/*  37 */     NBTTagCompound petNBT = MoCTools.getEntityData((Entity)pet);
/*  38 */     if (this.tamedList != null) {
/*  39 */       int id = getNextFreePetId();
/*  40 */       petNBT.setInteger("PetId", id);
/*  41 */       NBTTagCompound petData = petNBT.copy();
/*  42 */       petData.setInteger("ChunkX", coords.getX());
/*  43 */       petData.setInteger("ChunkY", coords.getY());
/*  44 */       petData.setInteger("ChunkZ", coords.getZ());
/*  45 */       petData.setInteger("Dimension", ((Entity)pet).world.provider.getDimensionType().getId());
/*  46 */       this.tamedList.appendTag((NBTBase)petData);
/*  47 */       this.ownerData.setTag("PetIdData", (NBTBase)savePetDataMap());
/*  48 */       return id;
/*     */     } 
/*  50 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removePet(int id) {
/*  55 */     for (int i = 0; i < this.tamedList.tagCount(); i++) {
/*  56 */       NBTTagCompound nbt = this.tamedList.getCompoundTagAt(i);
/*  57 */       if (nbt.hasKey("PetId") && nbt.getInteger("PetId") == id) {
/*  58 */         this.tamedList.removeTag(i);
/*  59 */         this.usedPetIds.remove(new Integer(id));
/*  60 */         this.IDMap.clear(id);
/*  61 */         if (this.usedPetIds.size() == 0) {
/*  62 */           this.IDMap.clear();
/*     */         }
/*  64 */         this.ownerData.setTag("PetIdData", (NBTBase)savePetDataMap());
/*  65 */         return true;
/*     */       } 
/*     */     } 
/*  68 */     return false;
/*     */   }
/*     */   
/*     */   public NBTTagCompound getPetData(int id) {
/*  72 */     if (this.tamedList != null) {
/*  73 */       for (int i = 0; i < this.tamedList.tagCount(); i++) {
/*  74 */         NBTTagCompound nbt = this.tamedList.getCompoundTagAt(i);
/*  75 */         if (nbt.hasKey("PetId") && nbt.getInteger("PetId") == id) {
/*  76 */           return nbt;
/*     */         }
/*     */       } 
/*     */     }
/*  80 */     return null;
/*     */   }
/*     */   
/*     */   public NBTTagCompound getOwnerRootNBT() {
/*  84 */     return this.ownerData;
/*     */   }
/*     */   
/*     */   public NBTTagList getTamedList() {
/*  88 */     return this.tamedList;
/*     */   }
/*     */   
/*     */   public String getOwner() {
/*  92 */     if (this.ownerData != null) {
/*  93 */       return this.ownerData.getString("Owner");
/*     */     }
/*  95 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getInAmulet(int petId) {
/* 100 */     NBTTagCompound petData = getPetData(petId);
/* 101 */     if (petData != null) {
/* 102 */       return petData.getBoolean("InAmulet");
/*     */     }
/* 104 */     return false;
/*     */   }
/*     */   
/*     */   public void setInAmulet(int petId, boolean flag) {
/* 108 */     NBTTagCompound petData = getPetData(petId);
/* 109 */     if (petData != null) {
/* 110 */       petData.setBoolean("InAmulet", flag);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNextFreePetId() {
/* 120 */     int next = 0;
/*     */     
/* 122 */     next = this.IDMap.nextClearBit(next);
/* 123 */     while (this.usedPetIds.contains(new Integer(next))) {
/* 124 */       this.IDMap.set(next);
/*     */     }
/* 126 */     this.usedPetIds.add(new Integer(next));
/* 127 */     return next;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound savePetDataMap() {
/* 133 */     int[] data = new int[(this.IDMap.length() + 32 - 1) / 32];
/* 134 */     NBTTagCompound dataMap = new NBTTagCompound();
/* 135 */     for (int i = 0; i < data.length; i++) {
/* 136 */       int val = 0;
/* 137 */       for (int j = 0; j < 32; j++) {
/* 138 */         val |= this.IDMap.get(i * 32 + j) ? (1 << j) : 0;
/*     */       }
/* 140 */       data[i] = val;
/*     */     } 
/* 142 */     dataMap.setIntArray("PetIdArray", data);
/* 143 */     return dataMap;
/*     */   }
/*     */   
/*     */   public void loadPetDataMap(NBTTagCompound compoundTag) {
/* 147 */     if (compoundTag == null) {
/* 148 */       this.IDMap.clear();
/*     */     } else {
/* 150 */       int[] intArray = compoundTag.getIntArray("PetIdArray");
/* 151 */       for (int i = 0; i < intArray.length; i++) {
/* 152 */         for (int j = 0; j < 32; j++) {
/* 153 */           this.IDMap.set(i * 32 + j, ((intArray[i] & 1 << j) != 0));
/*     */         }
/*     */       } 
/*     */       
/* 157 */       int next = 0;
/*     */       
/* 159 */       next = this.IDMap.nextClearBit(next);
/* 160 */       while (!this.usedPetIds.contains(new Integer(next)))
/* 161 */         this.usedPetIds.add(new Integer(next)); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\MoCPetData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */