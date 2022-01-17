/*     */ package drzhark.mocreatures.command;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.passive.MoCEntityBigCat;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityHorse;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityManticorePet;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityWyvern;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAppear;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */
/*     */ public class CommandMoCSpawn extends CommandBase {
/*  26 */   private static List<String> commands = new ArrayList<>();
/*  27 */   private static List<String> aliases = new ArrayList<>();
/*     */
/*     */   static {
/*  30 */     commands.add("/mocspawn <horse|manticore|wyvern|wyvernghost> <int>");
/*  31 */     aliases.add("mocspawn");
/*     */   }
/*     */
/*     */
/*     */
/*     */   public String getName() {
/*  37 */     return "mocspawn";
/*     */   }
/*     */
/*     */
/*     */   public List<String> getAliases() {
/*  42 */     return aliases;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public int getRequiredPermissionLevel() {
/*  50 */     return 2;
/*     */   }
/*     */
/*     */
/*     */   public String getUsage(ICommandSender par1ICommandSender) {
/*  55 */     return "commands.mocspawn.usage";
/*     */   }
/*     */
/*     */
/*     */   public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
/*  60 */     if (args.length == 2) {
/*  61 */       MoCEntityTameableAnimal moCEntity;
				String entityType = args[0];
/*  62 */       int type = 0;
/*     */       try {
/*  64 */         type = Integer.parseInt(args[1]);
/*  65 */       } catch (NumberFormatException e) {
/*  66 */         sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "ERROR:" + TextFormatting.WHITE + "The spawn type " + type + " for " + entityType + " is not a valid type.", new Object[0]));
/*     */
/*     */         return;
/*     */       }
/*     */
/*  71 */       String playername = sender.getName();
/*     */
/*  73 */       EntityPlayerMP player = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(playername);
/*  74 */       MoCEntityTameableAnimal specialEntity = null;
/*  75 */       if (entityType.equalsIgnoreCase("horse")) {
/*  76 */         moCEntity = new MoCEntityHorse(player.world);
/*  77 */         moCEntity.setAdult(true);
/*  78 */       } else if (entityType.equalsIgnoreCase("manticore")) {
/*  79 */         moCEntity = new MoCEntityManticorePet(player.world);
/*  80 */         moCEntity.setAdult(true);
/*  81 */       } else if (entityType.equalsIgnoreCase("wyvern")) {
/*  82 */         moCEntity = new MoCEntityWyvern(player.world);
/*  83 */         moCEntity.setAdult(false);
/*  84 */       } else if (entityType.equalsIgnoreCase("wyvernghost")) {
/*  85 */         moCEntity = new MoCEntityWyvern(player.world);
/*  86 */         moCEntity.setAdult(false);
/*  87 */         ((MoCEntityWyvern) moCEntity).setIsGhost(true);
/*     */       } else {
/*  89 */         sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "ERROR:" + TextFormatting.WHITE + "The entity spawn type " + entityType + " is not a valid type.", new Object[0]));
/*     */
/*     */         return;
/*     */       }
/*  93 */       double dist = 3.0D;
/*  94 */       double newPosY = player.posY;
/*  95 */       double newPosX = player.posX - dist * Math.cos((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F));
/*  96 */       double newPosZ = player.posZ - dist * Math.sin((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F));
/*  97 */       moCEntity.setPosition(newPosX, newPosY, newPosZ);
/*  98 */       moCEntity.setTamed(true);
/*  99 */       moCEntity.setOwnerId(null);
/* 100 */       moCEntity.setPetName("Rename_Me");
/* 101 */       moCEntity.setType(type);
/*     */
/* 103 */       if ((entityType.equalsIgnoreCase("horse") && (type < 1 || type > 67)) || (entityType
/* 104 */         .equalsIgnoreCase("wyvern") && (type < 1 || type > 12)) || (entityType
/* 105 */         .equalsIgnoreCase("manticore") && (type < 1 || type > 4))) {
/* 106 */         sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "ERROR:" + TextFormatting.WHITE + "The spawn type " + type + " is not a valid type.", new Object[0]));
/*     */
/*     */         return;
/*     */       }
/* 110 */       player.world.spawnEntity((Entity)moCEntity);
/* 111 */       if (!player.world.isRemote) {
/* 112 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAppear(moCEntity.getEntityId()), new NetworkRegistry.TargetPoint(player.world.provider
/* 113 */               .getDimensionType().getId(), player.posX, player.posY, player.posZ, 64.0D));
/*     */       }
/* 115 */       MoCTools.playCustomSound((Entity)moCEntity, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR);
/*     */     } else {
/* 117 */       sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "ERROR:" + TextFormatting.WHITE + "Invalid spawn parameters entered.", new Object[0]));
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   protected List<String> getSortedPossibleCommands(ICommandSender par1ICommandSender) {
/* 127 */     Collections.sort(commands);
/* 128 */     return commands;
/*     */   }
/*     */
/*     */   public void sendCommandHelp(ICommandSender sender) {
/* 132 */     sender.sendMessage((ITextComponent)new TextComponentTranslation("§2Listing MoCreatures commands", new Object[0]));
/* 133 */     for (int i = 0; i < commands.size(); i++)
/* 134 */       sender.sendMessage((ITextComponent)new TextComponentTranslation(commands.get(i), new Object[0]));
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\command\CommandMoCSpawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
