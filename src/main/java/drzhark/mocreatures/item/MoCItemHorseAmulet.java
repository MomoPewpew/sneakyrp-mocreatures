/*     */ package drzhark.mocreatures.item;
/*     */
/*     */ import drzhark.mocreatures.MoCPetData;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityBigCat;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityHorse;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityWyvern;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAppear;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
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
/*     */ public class MoCItemHorseAmulet
/*     */   extends MoCItem
/*     */ {
/*     */   private int ageCounter;
/*     */   private String name;
/*     */   private float health;
/*     */   private int edad;
/*     */   private int creatureType;
/*     */   private String spawnClass;
/*     */   private boolean isGhost;
/*     */   private boolean rideable;
/*     */   private byte armor;
/*     */   private boolean adult;
/*     */   private UUID ownerUniqueId;
/*     */   private String ownerName;
/*     */   private int PetId;
/*     */
/*     */   public MoCItemHorseAmulet(String name) {
/*  53 */     super(name);
/*  54 */     this.maxStackSize = 1;
/*  55 */     setHasSubtypes(true);
/*  56 */     this.ageCounter = 0;
/*     */   }
/*     */
/*     */
/*     */   public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
/*  61 */     ItemStack stack = player.getHeldItem(hand);
/*  62 */     if (++this.ageCounter < 2) {
/*  63 */       return new ActionResult(EnumActionResult.PASS, stack);
/*     */     }
/*     */
/*  66 */     if (!worldIn.isRemote) {
/*  67 */       initAndReadNBT(stack);
/*     */     }
/*     */
/*  70 */     double dist = 3.0D;
/*  71 */     double newPosY = player.posY;
/*  72 */     double newPosX = player.posX - dist * Math.cos((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F));
/*  73 */     double newPosZ = player.posZ - dist * Math.sin((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F));
/*     */
/*  75 */     if (!player.world.isRemote) {
/*     */       try {
/*     */         MoCEntityTameableAnimal storedCreature;
/*  78 */         this.spawnClass = this.spawnClass.replace("mocreatures:", "").toLowerCase();
/*  79 */         if (this.spawnClass.equalsIgnoreCase("Wyvern")) {
/*  80 */           MoCEntityWyvern moCEntityWyvern = new MoCEntityWyvern(worldIn);
/*  81 */           moCEntityWyvern.setIsGhost(true);
/*  82 */           this.isGhost = true;
                    storedCreature = (MoCEntityTameableAnimal)moCEntityWyvern;
/*  83 */         } else if (this.spawnClass.equalsIgnoreCase("WildHorse")) {
/*  84 */           MoCEntityHorse moCEntityHorse = new MoCEntityHorse(worldIn);
                    storedCreature = (MoCEntityTameableAnimal)moCEntityHorse;
/*     */         } else {
/*  86 */           storedCreature = (MoCEntityTameableAnimal)EntityList.createEntityByIDFromName(new ResourceLocation("mocreatures:" + this.spawnClass.toLowerCase()), worldIn);
/*  87 */           if (storedCreature instanceof MoCEntityBigCat) {
/*  88 */             this.isGhost = true;
/*  89 */             ((MoCEntityBigCat)storedCreature).setIsGhost(true);
/*     */           }
/*     */         }
/*     */
/*  93 */         storedCreature.setPosition(newPosX, newPosY, newPosZ);
/*  94 */         storedCreature.setType(this.creatureType);
/*  95 */         storedCreature.setTamed(true);
/*  96 */         storedCreature.setRideable(this.rideable);
/*  97 */         storedCreature.setEdad(this.edad);
/*  98 */         storedCreature.setPetName(this.name);
/*  99 */         storedCreature.setHealth(this.health);
/* 100 */         storedCreature.setAdult(this.adult);
/* 101 */         storedCreature.setArmorType(this.armor);
/* 102 */         storedCreature.setOwnerPetId(this.PetId);
/* 103 */         storedCreature.setOwnerId(player.getUniqueID());
/* 104 */         this.ownerName = player.getName();
/*     */
/* 106 */         if (this.ownerUniqueId == null) {
/* 107 */           this.ownerUniqueId = player.getUniqueID();
/* 108 */           if (MoCreatures.instance.mapData != null) {
/* 109 */             MoCPetData newOwner = MoCreatures.instance.mapData.getPetData(player.getUniqueID());
/* 110 */             int maxCount = MoCreatures.proxy.maxTamed;
/* 111 */             if (MoCTools.isThisPlayerAnOP(player)) {
/* 112 */               maxCount = MoCreatures.proxy.maxOPTamed;
/*     */             }
/* 114 */             if (newOwner == null) {
/* 115 */               if (maxCount > 0 || !MoCreatures.proxy.enableOwnership)
/*     */               {
/* 117 */                 MoCreatures.instance.mapData.updateOwnerPet((IMoCTameable)storedCreature);
/*     */
/*     */               }
/*     */             }
/* 121 */             else if (newOwner.getTamedList().tagCount() < maxCount || !MoCreatures.proxy.enableOwnership) {
/* 122 */               MoCreatures.instance.mapData.updateOwnerPet((IMoCTameable)storedCreature);
/*     */             }
/*     */
/*     */           }
/*     */
/*     */         }
/* 128 */         else if (!this.ownerUniqueId.equals(player.getUniqueID()) && MoCreatures.instance.mapData != null) {
/* 129 */           MoCPetData oldOwner = MoCreatures.instance.mapData.getPetData(this.ownerUniqueId);
/* 130 */           MoCPetData newOwner = MoCreatures.instance.mapData.getPetData(player.getUniqueID());
/* 131 */           int maxCount = MoCreatures.proxy.maxTamed;
/* 132 */           if (MoCTools.isThisPlayerAnOP(player)) {
/* 133 */             maxCount = MoCreatures.proxy.maxOPTamed;
/*     */           }
/* 135 */           if (newOwner == null) {
/* 136 */             if (maxCount > 0 || !MoCreatures.proxy.enableOwnership)
/*     */             {
/* 138 */               MoCreatures.instance.mapData.updateOwnerPet((IMoCTameable)storedCreature);
/*     */
/*     */             }
/*     */           }
/* 142 */           else if (newOwner.getTamedList().tagCount() < maxCount || !MoCreatures.proxy.enableOwnership) {
/* 143 */             MoCreatures.instance.mapData.updateOwnerPet((IMoCTameable)storedCreature);
/*     */           }
/*     */
/*     */
/* 147 */           if (oldOwner != null) {
/* 148 */             for (int j = 0; j < oldOwner.getTamedList().tagCount(); j++) {
/* 149 */               NBTTagCompound petEntry = oldOwner.getTamedList().getCompoundTagAt(j);
/* 150 */               if (petEntry.getInteger("PetId") == this.PetId)
/*     */               {
/* 152 */                 oldOwner.getTamedList().removeTag(j);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */
/*     */
/* 159 */         if (player.world.spawnEntity((Entity)storedCreature)) {
/* 160 */           MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAppear(storedCreature.getEntityId()), new NetworkRegistry.TargetPoint(player.world.provider
/* 161 */                 .getDimensionType().getId(), player.posX, player.posY, player.posZ, 64.0D));
/* 162 */           MoCTools.playCustomSound((Entity)storedCreature, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR);
/*     */
/* 164 */           if (storedCreature instanceof MoCEntityBigCat || storedCreature instanceof MoCEntityWyvern || this.creatureType == 21 || this.creatureType == 22) {
/* 165 */             player.setHeldItem(hand, new ItemStack(MoCItems.amuletghost, 1, 0));
/* 166 */           } else if (this.creatureType == 26 || this.creatureType == 27 || this.creatureType == 28) {
/* 167 */             player.setHeldItem(hand, new ItemStack(MoCItems.amuletbone, 1, 0));
/* 168 */           } else if (this.creatureType > 47 && this.creatureType < 60) {
/* 169 */             player.setHeldItem(hand, new ItemStack(MoCItems.amuletfairy, 1, 0));
/* 170 */           } else if (this.creatureType == 39 || this.creatureType == 40) {
/* 171 */             player.setHeldItem(hand, new ItemStack(MoCItems.amuletpegasus, 1, 0));
/*     */           }
/* 173 */           MoCPetData petData = MoCreatures.instance.mapData.getPetData(storedCreature.getOwnerId());
/* 174 */           if (petData != null) {
/* 175 */             petData.setInAmulet(storedCreature.getOwnerPetId(), false);
/*     */           }
/*     */         }
/* 178 */       } catch (Exception ex) {
/* 179 */         System.out.println("Unable to find class for entity " + this.spawnClass);
/* 180 */         ex.printStackTrace();
/*     */       }
/*     */     }
/* 183 */     this.ageCounter = 0;
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
/* 194 */     int spawnClassOld = nbt.getInteger("SpawnClass");
/* 195 */     if (spawnClassOld > 0) {
/* 196 */       if (spawnClassOld == 100) {
/* 197 */         this.spawnClass = "Wyvern";
/* 198 */         this.isGhost = true;
/*     */       } else {
/* 200 */         this.spawnClass = "WildHorse";
/*     */       }
/* 202 */       nbt.removeTag("SpawnClass");
/*     */     } else {
/* 204 */       this.spawnClass = nbt.getString("SpawnClass");
/*     */     }
/* 206 */     this.rideable = nbt.getBoolean("Rideable");
/* 207 */     this.armor = nbt.getByte("Armor");
/* 208 */     this.adult = nbt.getBoolean("Adult");
/* 209 */     this.ownerName = nbt.getString("OwnerName");
/* 210 */     if (nbt.hasUniqueId("OwnerUUID")) {
/* 211 */       this.ownerUniqueId = nbt.getUniqueId("OwnerUUID");
/*     */     }
/*     */   }
/*     */
/*     */   public void writeToNBT(NBTTagCompound nbt) {
/* 216 */     nbt.setInteger("PetId", this.PetId);
/* 217 */     nbt.setInteger("CreatureType", this.creatureType);
/* 218 */     nbt.setFloat("Health", this.health);
/* 219 */     nbt.setInteger("Edad", this.edad);
/* 220 */     nbt.setString("Name", this.name);
/* 221 */     nbt.setString("SpawnClass", this.spawnClass);
/* 222 */     nbt.setBoolean("Rideable", this.rideable);
/* 223 */     nbt.setByte("Armor", this.armor);
/* 224 */     nbt.setBoolean("Adult", this.adult);
/* 225 */     nbt.setString("OwnerName", this.ownerName);
/* 226 */     if (this.ownerUniqueId != null) {
/* 227 */       nbt.setUniqueId("OwnerUUID", this.ownerUniqueId);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/* 237 */     initAndReadNBT(stack);
/* 238 */     tooltip.add(TextFormatting.AQUA + this.spawnClass);
/* 239 */     if (this.name != "") {
/* 240 */       tooltip.add(TextFormatting.BLUE + this.name);
/*     */     }
/* 242 */     if (this.ownerName != "") {
/* 243 */       tooltip.add(TextFormatting.DARK_BLUE + "Owned by " + this.ownerName);
/*     */     }
/*     */   }
/*     */
/*     */   private void initAndReadNBT(ItemStack itemstack) {
/* 248 */     if (itemstack.getTagCompound() == null) {
/* 249 */       itemstack.setTagCompound(new NBTTagCompound());
/*     */     }
/* 251 */     NBTTagCompound nbtcompound = itemstack.getTagCompound();
/* 252 */     readFromNBT(nbtcompound);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemHorseAmulet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
