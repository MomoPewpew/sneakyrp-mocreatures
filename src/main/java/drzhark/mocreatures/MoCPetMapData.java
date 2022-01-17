/*     */ package drzhark.mocreatures;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.nbt.CompressedStreamTools;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagDouble;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.world.storage.ISaveHandler;
/*     */ import net.minecraft.world.storage.WorldSavedData;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ 
/*     */ public class MoCPetMapData
/*     */   extends WorldSavedData {
/*  23 */   private Map<UUID, MoCPetData> petMap = Maps.newHashMap();
/*     */   
/*     */   public MoCPetMapData(String par1Str) {
/*  26 */     super(par1Str);
/*  27 */     markDirty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoCPetData getPetData(UUID ownerUniqueId) {
/*  34 */     return this.petMap.get(ownerUniqueId);
/*     */   }
/*     */   
/*     */   public Map<UUID, MoCPetData> getPetMap() {
/*  38 */     return this.petMap;
/*     */   }
/*     */   
/*     */   public boolean removeOwnerPet(IMoCTameable pet, int petId) {
/*  42 */     if (this.petMap.get(pet.getOwnerId()) != null)
/*     */     {
/*  44 */       if (((MoCPetData)this.petMap.get(pet.getOwnerId())).removePet(petId)) {
/*  45 */         markDirty();
/*  46 */         pet.setOwnerPetId(-1);
/*  47 */         return true;
/*     */       } 
/*     */     }
/*  50 */     return false;
/*     */   }
/*     */   
/*     */   public void updateOwnerPet(IMoCTameable pet) {
/*  54 */     markDirty();
/*  55 */     if (pet.getOwnerPetId() == -1 || this.petMap.get(pet.getOwnerId()) == null) {
/*  56 */       UUID owner = MoCreatures.isServer() ? pet.getOwnerId() : (Minecraft.getMinecraft()).player.getUniqueID();
/*  57 */       MoCPetData petData = null;
/*  58 */       int id = -1;
/*  59 */       if (this.petMap.containsKey(owner)) {
/*  60 */         petData = this.petMap.get(owner);
/*  61 */         id = petData.addPet(pet);
/*     */       } else {
/*     */         
/*  64 */         petData = new MoCPetData(pet);
/*  65 */         id = petData.addPet(pet);
/*  66 */         this.petMap.put(owner, petData);
/*     */       } 
/*  68 */       pet.setOwnerPetId(id);
/*     */     } else {
/*     */       
/*  71 */       UUID owner = pet.getOwnerId();
/*  72 */       MoCPetData petData = getPetData(owner);
/*  73 */       NBTTagCompound rootNBT = petData.getOwnerRootNBT();
/*  74 */       NBTTagList tag = rootNBT.getTagList("TamedList", 10);
/*  75 */       int id = -1;
/*  76 */       id = pet.getOwnerPetId();
/*     */       
/*  78 */       for (int i = 0; i < tag.tagCount(); i++) {
/*  79 */         NBTTagCompound nbt = tag.getCompoundTagAt(i);
/*  80 */         if (nbt.getInteger("PetId") == id) {
/*     */           
/*  82 */           nbt.setTag("Pos", (NBTBase)newDoubleNBTList(new double[] { ((Entity)pet).posX, ((Entity)pet).posY, ((Entity)pet).posZ }));
/*  83 */           nbt.setInteger("ChunkX", ((Entity)pet).chunkCoordX);
/*  84 */           nbt.setInteger("ChunkY", ((Entity)pet).chunkCoordY);
/*  85 */           nbt.setInteger("ChunkZ", ((Entity)pet).chunkCoordZ);
/*  86 */           nbt.setInteger("Dimension", ((Entity)pet).world.provider.getDimensionType().getId());
/*  87 */           nbt.setInteger("PetId", pet.getOwnerPetId());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected NBTTagList newDoubleNBTList(double... par1ArrayOfDouble) {
/*  94 */     NBTTagList nbttaglist = new NBTTagList();
/*  95 */     double[] adouble = par1ArrayOfDouble;
/*  96 */     int i = par1ArrayOfDouble.length;
/*     */     
/*  98 */     for (int j = 0; j < i; j++) {
/*  99 */       double d1 = adouble[j];
/* 100 */       nbttaglist.appendTag((NBTBase)new NBTTagDouble(d1));
/*     */     } 
/*     */     
/* 103 */     return nbttaglist;
/*     */   }
/*     */   
/*     */   public boolean isExistingPet(UUID owner, IMoCTameable pet) {
/* 107 */     MoCPetData petData = MoCreatures.instance.mapData.getPetData(owner);
/* 108 */     if (petData != null) {
/* 109 */       NBTTagList tag = petData.getTamedList();
/* 110 */       for (int i = 0; i < tag.tagCount(); i++) {
/* 111 */         NBTTagCompound nbt = tag.getCompoundTagAt(i);
/* 112 */         if (nbt.getInteger("PetId") == pet.getOwnerPetId())
/*     */         {
/* 114 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 118 */     return false;
/*     */   }
/*     */   
/*     */   public void forceSave() {
/* 122 */     if (DimensionManager.getWorld(0) != null) {
/* 123 */       ISaveHandler saveHandler = DimensionManager.getWorld(0).getSaveHandler();
/* 124 */       if (saveHandler != null) {
/*     */         try {
/* 126 */           File file1 = saveHandler.getMapFileFromName("mocreatures");
/*     */           
/* 128 */           if (file1 != null) {
/* 129 */             NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 130 */             writeToNBT(nbttagcompound);
/* 131 */             NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 132 */             nbttagcompound1.setTag("data", (NBTBase)nbttagcompound);
/* 133 */             FileOutputStream fileoutputstream = new FileOutputStream(file1);
/* 134 */             CompressedStreamTools.writeCompressed(nbttagcompound1, fileoutputstream);
/* 135 */             fileoutputstream.close();
/*     */           } 
/* 137 */         } catch (Exception exception) {
/* 138 */           exception.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 149 */     Iterator<String> iterator = par1NBTTagCompound.getKeySet().iterator();
/* 150 */     while (iterator.hasNext()) {
/* 151 */       String s = iterator.next();
/* 152 */       NBTTagCompound nbt = (NBTTagCompound)par1NBTTagCompound.getTag(s);
/* 153 */       UUID ownerUniqueId = UUID.fromString(s);
/*     */       
/* 155 */       if (!this.petMap.containsKey(ownerUniqueId)) {
/* 156 */         this.petMap.put(ownerUniqueId, new MoCPetData(nbt, ownerUniqueId));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound) {
/* 168 */     for (Map.Entry<UUID, MoCPetData> ownerEntry : this.petMap.entrySet()) {
/*     */       try {
/* 170 */         if (this.petMap.entrySet() != null && ownerEntry.getKey() != null)
/*     */         {
/* 172 */           par1NBTTagCompound.setTag(((UUID)ownerEntry.getKey()).toString(), (NBTBase)((MoCPetData)ownerEntry.getValue()).getOwnerRootNBT());
/*     */         }
/* 174 */       } catch (Exception e) {
/* 175 */         e.printStackTrace();
/*     */       } 
/*     */     } 
/* 178 */     return par1NBTTagCompound;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\MoCPetMapData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */