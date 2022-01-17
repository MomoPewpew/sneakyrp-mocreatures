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
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ 
/*     */ public class CommandMoCTP
/*     */   extends CommandBase {
/*  26 */   private static List<String> commands = new ArrayList<>();
/*  27 */   private static List<String> aliases = new ArrayList<>();
/*     */   
/*     */   static {
/*  30 */     commands.add("/moctp <entityid> <playername>");
/*  31 */     commands.add("/moctp <petname> <playername>");
/*  32 */     aliases.add("moctp");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  38 */     return "moctp";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getAliases() {
/*  43 */     return aliases;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/*  51 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandSender par1ICommandSender) {
/*  56 */     return "commands.moctp.usage";
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
/*  61 */     int petId = 0;
/*  62 */     if (args == null || args.length == 0) {
/*  63 */       sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "Error" + TextFormatting.WHITE + ": You must enter a valid entity ID.", new Object[0]));
/*     */       
/*     */       return;
/*     */     } 
/*  67 */     if (!(sender instanceof EntityPlayer)) {
/*     */       return;
/*     */     }
/*     */     try {
/*  71 */       petId = Integer.parseInt(args[0]);
/*  72 */     } catch (NumberFormatException e) {
/*  73 */       petId = -1;
/*     */     } 
/*  75 */     String playername = sender.getName();
/*  76 */     EntityPlayer player = (EntityPlayer)sender;
/*     */     
/*  78 */     MoCPetData ownerPetData = MoCreatures.instance.mapData.getPetData(player.getUniqueID());
/*  79 */     if ((((player != null) ? 1 : 0) & ((ownerPetData != null) ? 1 : 0)) != 0) {
/*  80 */       for (int i = 0; i < ownerPetData.getTamedList().tagCount(); i++) {
/*  81 */         NBTTagCompound nbt = ownerPetData.getTamedList().getCompoundTagAt(i);
/*  82 */         if (nbt.hasKey("PetId") && nbt.getInteger("PetId") == petId) {
/*  83 */           String petName = nbt.getString("Name");
/*  84 */           WorldServer world = DimensionManager.getWorld(nbt.getInteger("Dimension"));
/*  85 */           if (!teleportLoadedPet(world, player, petId, petName, sender)) {
/*  86 */             double posX = nbt.getTagList("Pos", 6).getDoubleAt(0);
/*  87 */             double posY = nbt.getTagList("Pos", 6).getDoubleAt(1);
/*  88 */             double posZ = nbt.getTagList("Pos", 6).getDoubleAt(2);
/*  89 */             int x = MathHelper.floor(posX);
/*  90 */             int y = MathHelper.floor(posY);
/*  91 */             int z = MathHelper.floor(posZ);
/*  92 */             sender.sendMessage((ITextComponent)new TextComponentTranslation("Found unloaded pet " + TextFormatting.GREEN + nbt
/*  93 */                   .getString("id") + TextFormatting.WHITE + " with name " + TextFormatting.AQUA + nbt.getString("Name") + TextFormatting.WHITE + " at location " + TextFormatting.LIGHT_PURPLE + x + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + y + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + z + TextFormatting.WHITE + " with Pet ID " + TextFormatting.BLUE + nbt
/*     */ 
/*     */                   
/*  96 */                   .getInteger("PetId"), new Object[0]));
/*  97 */             boolean result = teleportLoadedPet(world, player, petId, petName, sender);
/*  98 */             if (!result) {
/*  99 */               sender.sendMessage((ITextComponent)new TextComponentTranslation("Unable to transfer entity ID " + TextFormatting.GREEN + petId + TextFormatting.WHITE + ". It may only be transferred to " + TextFormatting.AQUA + player
/*     */                     
/* 101 */                     .getName(), new Object[0]));
/*     */             }
/*     */           } 
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 108 */       sender.sendMessage((ITextComponent)new TextComponentTranslation("Tamed entity could not be located.", new Object[0]));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<String> getSortedPossibleCommands(ICommandSender par1ICommandSender) {
/* 117 */     Collections.sort(commands);
/* 118 */     return commands;
/*     */   }
/*     */   
/*     */   public boolean teleportLoadedPet(WorldServer world, EntityPlayer player, int petId, String petName, ICommandSender par1ICommandSender) {
/* 122 */     for (int j = 0; j < world.loadedEntityList.size(); j++) {
/* 123 */       Entity entity = world.loadedEntityList.get(j);
/*     */       
/* 125 */       if (IMoCTameable.class.isAssignableFrom(entity.getClass()) && !((IMoCTameable)entity).getPetName().equals("") && ((IMoCTameable)entity)
/* 126 */         .getOwnerPetId() == petId) {
/*     */         
/* 128 */         NBTTagCompound compound = new NBTTagCompound();
/* 129 */         entity.writeToNBT(compound);
/* 130 */         if (compound != null && compound.getString("Owner") != null) {
/* 131 */           String owner = compound.getString("Owner");
/* 132 */           String name = compound.getString("Name");
/* 133 */           if (owner != null && owner.equalsIgnoreCase(player.getName())) {
/*     */             
/* 135 */             if (entity.dimension == player.dimension) {
/* 136 */               entity.setPosition(player.posX, player.posY, player.posZ);
/* 137 */             } else if (!player.world.isRemote) {
/*     */               
/* 139 */               Entity newEntity = EntityList.newEntity(entity.getClass(), player.world);
/* 140 */               if (newEntity != null) {
/* 141 */                 MoCTools.copyDataFromOld(newEntity, entity);
/* 142 */                 newEntity.setPosition(player.posX, player.posY, player.posZ);
/* 143 */                 DimensionManager.getWorld(player.dimension).spawnEntity(newEntity);
/*     */               } 
/* 145 */               if (entity.getRidingEntity() == null) {
/* 146 */                 entity.isDead = true;
/*     */               } else {
/*     */                 
/* 149 */                 entity.getRidingEntity().dismountRidingEntity();
/* 150 */                 entity.isDead = true;
/*     */               } 
/* 152 */               world.resetUpdateEntityTick();
/* 153 */               DimensionManager.getWorld(player.dimension).resetUpdateEntityTick();
/*     */             } 
/* 155 */             par1ICommandSender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + name + TextFormatting.WHITE + " has been tp'd to location " + 
/* 156 */                   Math.round(player.posX) + ", " + Math.round(player.posY) + ", " + 
/* 157 */                   Math.round(player.posZ) + " in dimension " + player.dimension, new Object[0]));
/* 158 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 163 */     return false;
/*     */   }
/*     */   
/*     */   public void sendCommandHelp(ICommandSender sender) {
/* 167 */     sender.sendMessage((ITextComponent)new TextComponentTranslation("§2Listing MoCreatures commands", new Object[0]));
/* 168 */     for (int i = 0; i < commands.size(); i++)
/* 169 */       sender.sendMessage((ITextComponent)new TextComponentTranslation(commands.get(i), new Object[0])); 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\command\CommandMoCTP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */