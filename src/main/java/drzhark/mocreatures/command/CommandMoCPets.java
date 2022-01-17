/*     */ package drzhark.mocreatures.command;
/*     */ 
/*     */ import drzhark.mocreatures.MoCPetData;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.NumberInvalidException;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ 
/*     */ public class CommandMoCPets
/*     */   extends CommandBase {
/*  28 */   private static List<String> commands = new ArrayList<>();
/*  29 */   private static List<String> aliases = new ArrayList<>();
/*     */   
/*     */   static {
/*  32 */     commands.add("/mocpets");
/*  33 */     aliases.add("mocpets");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  39 */     return "mocpets";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getAliases() {
/*  44 */     return aliases;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  52 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandSender par1ICommandSender) {
/*  57 */     return "commands.mocpets.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
/*  62 */     int unloadedCount = 0;
/*  63 */     int loadedCount = 0;
/*  64 */     ArrayList<Integer> foundIds = new ArrayList<>();
/*  65 */     ArrayList<String> tamedlist = new ArrayList<>();
/*  66 */     if (!(sender instanceof EntityPlayer)) {
/*     */       return;
/*     */     }
/*  69 */     String playername = sender.getName();
/*  70 */     EntityPlayer player = (EntityPlayer)sender; Integer[] arrayOfInteger; int i;
/*     */     byte b;
/*  72 */     for (arrayOfInteger = DimensionManager.getIDs(), i = arrayOfInteger.length, b = 0; b < i; ) { int dimension = arrayOfInteger[b].intValue();
/*  73 */       WorldServer world = DimensionManager.getWorld(dimension);
/*  74 */       for (int j = 0; j < world.loadedEntityList.size(); j++) {
/*  75 */         Entity entity = world.loadedEntityList.get(j);
/*  76 */         if (IMoCTameable.class.isAssignableFrom(entity.getClass())) {
/*  77 */           IMoCTameable mocreature = (IMoCTameable)entity;
/*  78 */           if (mocreature.getOwnerId() != null && mocreature.getOwnerId().equals(playername)) {
/*  79 */             loadedCount++;
/*  80 */             foundIds.add(Integer.valueOf(mocreature.getOwnerPetId()));
/*  81 */             tamedlist.add(TextFormatting.WHITE + "Found pet with " + TextFormatting.DARK_AQUA + "Type" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + ((EntityLiving)mocreature)
/*  82 */                 .getName() + TextFormatting.DARK_AQUA + ", Name" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + mocreature
/*  83 */                 .getPetName() + TextFormatting.DARK_AQUA + ", Owner" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + mocreature
/*     */                 
/*  85 */                 .getOwnerId() + TextFormatting.DARK_AQUA + ", PetId" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + mocreature
/*  86 */                 .getOwnerPetId() + TextFormatting.DARK_AQUA + ", Dimension" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + entity.dimension + TextFormatting.DARK_AQUA + ", Pos" + TextFormatting.WHITE + ":" + TextFormatting.LIGHT_PURPLE + 
/*     */                 
/*  88 */                 Math.round(entity.posX) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + 
/*  89 */                 Math.round(entity.posY) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + 
/*  90 */                 Math.round(entity.posZ));
/*     */           } 
/*     */         } 
/*     */       }  b++; }
/*     */     
/*  95 */     MoCPetData ownerPetData = MoCreatures.instance.mapData.getPetData(player.getUniqueID());
/*  96 */     if (ownerPetData != null) {
/*  97 */       MoCreatures.instance.mapData.forceSave();
/*  98 */       for (int j = 0; j < ownerPetData.getTamedList().tagCount(); j++) {
/*  99 */         NBTTagCompound nbt = ownerPetData.getTamedList().getCompoundTagAt(j);
/* 100 */         if (nbt.hasKey("PetId") && !foundIds.contains(Integer.valueOf(nbt.getInteger("PetId")))) {
/* 101 */           unloadedCount++;
/* 102 */           double posX = nbt.getTagList("Pos", 6).getDoubleAt(0);
/* 103 */           double posY = nbt.getTagList("Pos", 6).getDoubleAt(1);
/* 104 */           double posZ = nbt.getTagList("Pos", 6).getDoubleAt(2);
/* 105 */           if (nbt.getBoolean("InAmulet")) {
/* 106 */             tamedlist.add(TextFormatting.WHITE + "Found unloaded pet in " + TextFormatting.DARK_PURPLE + "AMULET" + TextFormatting.WHITE + " with " + TextFormatting.DARK_AQUA + "Type" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/*     */                 
/* 108 */                 .getString("id").replace("MoCreatures.", "") + TextFormatting.DARK_AQUA + ", Name" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/* 109 */                 .getString("Name") + TextFormatting.DARK_AQUA + ", Owner" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/*     */                 
/* 111 */                 .getString("Owner") + TextFormatting.DARK_AQUA + ", PetId" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/* 112 */                 .getInteger("PetId") + TextFormatting.WHITE + ".");
/*     */           } else {
/* 114 */             tamedlist.add(TextFormatting.WHITE + "Found unloaded pet with " + TextFormatting.DARK_AQUA + "Type" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/* 115 */                 .getString("id").replace("MoCreatures.", "") + TextFormatting.DARK_AQUA + ", Name" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/*     */                 
/* 117 */                 .getString("Name") + TextFormatting.DARK_AQUA + ", Owner" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/* 118 */                 .getString("Owner") + TextFormatting.DARK_AQUA + ", PetId" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/* 119 */                 .getInteger("PetId") + TextFormatting.DARK_AQUA + ", Dimension" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/* 120 */                 .getInteger("Dimension") + TextFormatting.DARK_AQUA + ", Pos" + TextFormatting.WHITE + ":" + TextFormatting.LIGHT_PURPLE + 
/*     */                 
/* 122 */                 Math.round(posX) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + Math.round(posY) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + 
/* 123 */                 Math.round(posZ));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 129 */     if (tamedlist.size() > 0) {
/* 130 */       sendPageHelp(sender, (byte)10, tamedlist, args);
/* 131 */       sender.sendMessage((ITextComponent)new TextComponentTranslation("Loaded tamed count : " + TextFormatting.AQUA + loadedCount + TextFormatting.WHITE + ", Unloaded count : " + TextFormatting.AQUA + unloadedCount + TextFormatting.WHITE + ", Total count : " + TextFormatting.AQUA + ((ownerPetData != null) ? ownerPetData
/*     */             
/* 133 */             .getTamedList().tagCount() : 0), new Object[0]));
/*     */     } else {
/* 135 */       sender.sendMessage((ITextComponent)new TextComponentTranslation("Loaded tamed count : " + TextFormatting.AQUA + loadedCount + TextFormatting.WHITE + (
/*     */ 
/*     */ 
/*     */             
/* 139 */             !MoCreatures.isServer() ? (", Unloaded Count : " + TextFormatting.AQUA + unloadedCount + TextFormatting.WHITE + ", Total count : " + TextFormatting.AQUA + (loadedCount + unloadedCount)) : ""), new Object[0]));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<String> getSortedPossibleCommands(ICommandSender par1ICommandSender) {
/* 149 */     Collections.sort(commands);
/* 150 */     return commands;
/*     */   }
/*     */   
/*     */   public boolean teleportLoadedPet(WorldServer world, EntityPlayerMP player, int petId, String petName, ICommandSender par1ICommandSender) {
/* 154 */     for (int j = 0; j < world.loadedEntityList.size(); j++) {
/* 155 */       Entity entity = world.loadedEntityList.get(j);
/*     */       
/* 157 */       if (IMoCTameable.class.isAssignableFrom(entity.getClass()) && !((IMoCTameable)entity).getPetName().equals("") && ((IMoCTameable)entity)
/* 158 */         .getOwnerPetId() == petId) {
/*     */         
/* 160 */         NBTTagCompound compound = new NBTTagCompound();
/* 161 */         entity.writeToNBT(compound);
/* 162 */         if (compound != null && compound.getString("Owner") != null) {
/* 163 */           String owner = compound.getString("Owner");
/* 164 */           String name = compound.getString("Name");
/* 165 */           if (owner != null && owner.equalsIgnoreCase(player.getName())) {
/*     */             
/* 167 */             if (entity.dimension == player.dimension) {
/* 168 */               entity.setPosition(player.posX, player.posY, player.posZ);
/* 169 */             } else if (!player.world.isRemote) {
/*     */               
/* 171 */               Entity newEntity = EntityList.newEntity(entity.getClass(), player.world);
/* 172 */               if (newEntity != null) {
/* 173 */                 MoCTools.copyDataFromOld(newEntity, entity);
/* 174 */                 newEntity.setPosition(player.posX, player.posY, player.posZ);
/* 175 */                 DimensionManager.getWorld(player.dimension).spawnEntity(newEntity);
/*     */               } 
/* 177 */               if (entity.getRidingEntity() == null) {
/* 178 */                 entity.isDead = true;
/*     */               } else {
/*     */                 
/* 181 */                 entity.getRidingEntity().dismountRidingEntity();
/* 182 */                 entity.isDead = true;
/*     */               } 
/* 184 */               world.resetUpdateEntityTick();
/* 185 */               DimensionManager.getWorld(player.dimension).resetUpdateEntityTick();
/*     */             } 
/* 187 */             par1ICommandSender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + name + TextFormatting.WHITE + " has been tp'd to location " + 
/* 188 */                   Math.round(player.posX) + ", " + Math.round(player.posY) + ", " + 
/* 189 */                   Math.round(player.posZ) + " in dimension " + player.dimension, new Object[0]));
/* 190 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 195 */     return false;
/*     */   }
/*     */   
/*     */   public void sendCommandHelp(ICommandSender sender) {
/* 199 */     sender.sendMessage((ITextComponent)new TextComponentTranslation("§2Listing MoCreatures commands", new Object[0]));
/* 200 */     for (int i = 0; i < commands.size(); i++) {
/* 201 */       sender.sendMessage((ITextComponent)new TextComponentTranslation(commands.get(i), new Object[0]));
/*     */     }
/*     */   }
/*     */   
/*     */   public void sendPageHelp(ICommandSender sender, byte pagelimit, ArrayList<String> list, String[] par2ArrayOfStr) {
/* 206 */     int x = (list.size() - 1) / pagelimit;
/* 207 */     int j = 0;
/*     */     
/* 209 */     if (par2ArrayOfStr.length > 0 && Character.isDigit(par2ArrayOfStr[0].charAt(0))) {
/*     */       try {
/* 211 */         j = (par2ArrayOfStr.length == 0) ? 0 : (parseInt(par2ArrayOfStr[0], 1, x + 1) - 1);
/* 212 */       } catch (NumberInvalidException numberinvalidexception) {
/* 213 */         numberinvalidexception.printStackTrace();
/*     */       } 
/*     */     }
/* 216 */     int k = Math.min((j + 1) * pagelimit, list.size());
/*     */     
/* 218 */     sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.DARK_GREEN + "--- Showing MoCreatures Help Info " + TextFormatting.AQUA + 
/* 219 */           Integer.valueOf(j + 1) + TextFormatting.WHITE + " of " + TextFormatting.AQUA + 
/* 220 */           Integer.valueOf(x + 1) + TextFormatting.GRAY + " (/mocpets <page>)" + TextFormatting.DARK_GREEN + "---", new Object[0]));
/*     */     
/* 222 */     for (int l = j * pagelimit; l < k; l++) {
/* 223 */       String tamedInfo = list.get(l);
/* 224 */       sender.sendMessage((ITextComponent)new TextComponentTranslation(tamedInfo, new Object[0]));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\command\CommandMoCPets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */