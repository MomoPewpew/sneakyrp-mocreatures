/*     */ package drzhark.mocreatures.item;
/*     */ 
/*     */ import drzhark.mocreatures.MoCPetData;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityBigCat;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityKitty;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAppear;
/*     */ import drzhark.mocreatures.util.MoCLog;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ 
/*     */ public class MoCItemPetAmulet
/*     */   extends MoCItem
/*     */ {
/*     */   private String name;
/*     */   private float health;
/*     */   private int edad;
/*     */   private int creatureType;
/*     */   private String spawnClass;
/*     */   private String ownerName;
/*     */   private UUID ownerUniqueId;
/*     */   private int amuletType;
/*     */   private boolean adult;
/*     */   private int PetId;
/*     */   
/*     */   public MoCItemPetAmulet(String name) {
/*  50 */     super(name);
/*  51 */     this.maxStackSize = 1;
/*  52 */     setHasSubtypes(true);
/*     */   }
/*     */   
/*     */   public MoCItemPetAmulet(String name, int type) {
/*  56 */     this(name);
/*  57 */     this.amuletType = type;
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
/*  62 */     ItemStack stack = player.getHeldItem(hand);
/*  63 */     double dist = 1.0D;
/*  64 */     double newPosY = player.posY;
/*  65 */     double newPosX = player.posX - dist * Math.cos((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F));
/*  66 */     double newPosZ = player.posZ - dist * Math.sin((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F));
/*     */     
/*  68 */     ItemStack emptyAmulet = new ItemStack(MoCItems.fishnet, 1, 0);
/*  69 */     if (this.amuletType == 1) {
/*  70 */       emptyAmulet = new ItemStack(MoCItems.petamulet, 1, 0);
/*     */     }
/*     */     
/*  73 */     if (!world.isRemote) {
/*  74 */       initAndReadNBT(stack);
/*  75 */       if (this.spawnClass.isEmpty()) {
/*  76 */         return new ActionResult(EnumActionResult.SUCCESS, stack);
/*     */       }
/*     */       try {
/*  79 */         this.spawnClass = this.spawnClass.replace("mocreatures:", "").toLowerCase();
/*  80 */         if (this.spawnClass.equalsIgnoreCase("MoCHorse")) {
/*  81 */           this.spawnClass = "WildHorse";
/*  82 */         } else if (this.spawnClass.equalsIgnoreCase("PolarBear")) {
/*  83 */           this.spawnClass = "WildPolarBear";
/*     */         } 
/*     */         
/*  86 */         if (this.spawnClass.equalsIgnoreCase("Ray")) {
/*  87 */           switch (this.creatureType) {
/*     */             case 1:
/*  89 */               this.spawnClass = "MantaRay";
/*     */               break;
/*     */             case 2:
/*  92 */               this.spawnClass = "StingRay";
/*     */               break;
/*     */           } 
/*     */         }
/*  96 */         EntityLiving tempLiving = (EntityLiving)EntityList.createEntityByIDFromName(new ResourceLocation("mocreatures:" + this.spawnClass.toLowerCase()), world);
/*  97 */         if (tempLiving != null && tempLiving instanceof drzhark.mocreatures.entity.IMoCEntity) {
/*  98 */           IMoCTameable storedCreature = (IMoCTameable)tempLiving;
/*  99 */           ((EntityLiving)storedCreature).setPosition(newPosX, newPosY, newPosZ);
/* 100 */           storedCreature.setType(this.creatureType);
/* 101 */           storedCreature.setTamed(true);
/* 102 */           storedCreature.setPetName(this.name);
/* 103 */           storedCreature.setOwnerPetId(this.PetId);
/* 104 */           storedCreature.setOwnerId(player.getUniqueID());
/* 105 */           this.ownerName = player.getName();
/* 106 */           ((EntityLiving)storedCreature).setHealth(this.health);
/* 107 */           storedCreature.setEdad(this.edad);
/* 108 */           storedCreature.setAdult(this.adult);
/* 109 */           if (storedCreature instanceof MoCEntityBigCat) {
/* 110 */             ((MoCEntityBigCat)storedCreature).setHasAmulet(true);
/*     */           }
/*     */           
/* 113 */           if (this.spawnClass.equalsIgnoreCase("Kitty")) {
/* 114 */             ((MoCEntityKitty)storedCreature).setKittyState(3);
/*     */           }
/*     */           
/* 117 */           if (this.ownerUniqueId == null) {
/* 118 */             this.ownerUniqueId = player.getUniqueID();
/* 119 */             if (MoCreatures.instance.mapData != null) {
/* 120 */               MoCPetData newOwner = MoCreatures.instance.mapData.getPetData(player.getUniqueID());
/* 121 */               int maxCount = MoCreatures.proxy.maxTamed;
/* 122 */               if (MoCTools.isThisPlayerAnOP(player)) {
/* 123 */                 maxCount = MoCreatures.proxy.maxOPTamed;
/*     */               }
/* 125 */               if (newOwner == null) {
/* 126 */                 if (maxCount > 0 || !MoCreatures.proxy.enableOwnership)
/*     */                 {
/* 128 */                   MoCreatures.instance.mapData.updateOwnerPet(storedCreature);
/*     */                 
/*     */                 }
/*     */               }
/* 132 */               else if (newOwner.getTamedList().tagCount() < maxCount || !MoCreatures.proxy.enableOwnership) {
/* 133 */                 MoCreatures.instance.mapData.updateOwnerPet(storedCreature);
/*     */               }
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 139 */           else if (!this.ownerUniqueId.equals(player.getUniqueID()) && MoCreatures.instance.mapData != null) {
/* 140 */             MoCPetData oldOwner = MoCreatures.instance.mapData.getPetData(this.ownerUniqueId);
/* 141 */             MoCPetData newOwner = MoCreatures.instance.mapData.getPetData(player.getUniqueID());
/* 142 */             int maxCount = MoCreatures.proxy.maxTamed;
/* 143 */             if (MoCTools.isThisPlayerAnOP(player)) {
/* 144 */               maxCount = MoCreatures.proxy.maxOPTamed;
/*     */             }
/* 146 */             if ((newOwner != null && newOwner.getTamedList().tagCount() < maxCount) || (newOwner == null && maxCount > 0) || !MoCreatures.proxy.enableOwnership)
/*     */             {
/* 148 */               MoCreatures.instance.mapData.updateOwnerPet(storedCreature);
/*     */             }
/*     */             
/* 151 */             if (oldOwner != null) {
/* 152 */               for (int j = 0; j < oldOwner.getTamedList().tagCount(); j++) {
/* 153 */                 NBTTagCompound petEntry = oldOwner.getTamedList().getCompoundTagAt(j);
/* 154 */                 if (petEntry.getInteger("PetId") == this.PetId)
/*     */                 {
/* 156 */                   oldOwner.getTamedList().removeTag(j);
/*     */                 }
/*     */               } 
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 163 */           if (player.getEntityWorld().spawnEntity((Entity)storedCreature)) {
/* 164 */             MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAppear(((EntityLiving)storedCreature).getEntityId()), new NetworkRegistry.TargetPoint(
/* 165 */                   (player.getEntityWorld()).provider.getDimensionType().getId(), player.posX, player.posY, player.posZ, 64.0D));
/*     */             
/* 167 */             if (this.ownerUniqueId == null || this.name.isEmpty()) {
/* 168 */               MoCTools.tameWithName(player, storedCreature);
/*     */             }
/*     */             
/* 171 */             player.setHeldItem(hand, emptyAmulet);
/* 172 */             MoCPetData petData = MoCreatures.instance.mapData.getPetData(storedCreature.getOwnerId());
/* 173 */             if (petData != null) {
/* 174 */               petData.setInAmulet(storedCreature.getOwnerPetId(), false);
/*     */             }
/*     */           } 
/*     */         } 
/* 178 */       } catch (Exception ex) {
/* 179 */         if (MoCreatures.proxy.debug) {
/* 180 */           MoCLog.logger.warn("Error spawning creature from fishnet/amulet " + ex);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     return new ActionResult(EnumActionResult.SUCCESS, stack);
/*     */   }
/*     */   
/*     */   public void readFromNBT(NBTTagCompound nbt) {
/* 189 */     this.PetId = nbt.getInteger("PetId");
/* 190 */     this.creatureType = nbt.getInteger("CreatureType");
/* 191 */     this.health = nbt.getFloat("Health");
/* 192 */     this.edad = nbt.getInteger("Edad");
/* 193 */     this.name = nbt.getString("Name");
/* 194 */     this.spawnClass = nbt.getString("SpawnClass");
/* 195 */     this.adult = nbt.getBoolean("Adult");
/* 196 */     this.ownerName = nbt.getString("OwnerName");
/* 197 */     if (nbt.hasUniqueId("OwnerUUID")) {
/* 198 */       this.ownerUniqueId = nbt.getUniqueId("OwnerUUID");
/*     */     }
/*     */   }
/*     */   
/*     */   public void writeToNBT(NBTTagCompound nbt) {
/* 203 */     nbt.setInteger("PetID", this.PetId);
/* 204 */     nbt.setInteger("CreatureType", this.creatureType);
/* 205 */     nbt.setFloat("Health", this.health);
/* 206 */     nbt.setInteger("Edad", this.edad);
/* 207 */     nbt.setString("Name", this.name);
/* 208 */     nbt.setString("SpawnClass", this.spawnClass);
/* 209 */     nbt.setBoolean("Adult", this.adult);
/* 210 */     nbt.setString("OwnerName", this.ownerName);
/* 211 */     if (this.ownerUniqueId != null) {
/* 212 */       nbt.setUniqueId("OwnerUUID", this.ownerUniqueId);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/* 222 */     initAndReadNBT(stack);
/* 223 */     if (this.spawnClass != "") {
/* 224 */       tooltip.add(TextFormatting.AQUA + this.spawnClass);
/*     */     }
/* 226 */     if (this.name != "") {
/* 227 */       tooltip.add(TextFormatting.BLUE + this.name);
/*     */     }
/* 229 */     if (this.ownerName != "") {
/* 230 */       tooltip.add(TextFormatting.DARK_BLUE + "Owned by " + this.ownerName);
/*     */     }
/*     */   }
/*     */   
/*     */   private void initAndReadNBT(ItemStack itemstack) {
/* 235 */     if (itemstack.getTagCompound() == null) {
/* 236 */       itemstack.setTagCompound(new NBTTagCompound());
/*     */     }
/* 238 */     NBTTagCompound nbtcompound = itemstack.getTagCompound();
/* 239 */     readFromNBT(nbtcompound);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemPetAmulet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */