package drzhark.mocreatures.command;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.passive.MoCEntityBigCat;
import drzhark.mocreatures.entity.passive.MoCEntityHorse;
import drzhark.mocreatures.entity.passive.MoCEntityManticorePet;
import drzhark.mocreatures.entity.passive.MoCEntityWyvern;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAppear;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class CommandMoCSpawn extends CommandBase {
  private static List<String> commands = new ArrayList<>();
  private static List<String> aliases = new ArrayList<>();

  static {
    commands.add("/mocspawn <horse|manticore|wyvern|wyvernghost> <int>");
    aliases.add("mocspawn");
  }



  public String getName() {
    return "mocspawn";
  }


  public List<String> getAliases() {
    return aliases;
  }





  public int getRequiredPermissionLevel() {
    return 2;
  }


  public String getUsage(ICommandSender par1ICommandSender) {
    return "commands.mocspawn.usage";
  }


  public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
    if (args.length == 2) {
      MoCEntityTameableAnimal moCEntity;
				String entityType = args[0];
      int type = 0;
      try {
        type = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "ERROR:" + TextFormatting.WHITE + "The spawn type " + type + " for " + entityType + " is not a valid type.", new Object[0]));

        return;
      }

      String playername = sender.getName();

      EntityPlayerMP player = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(playername);
      MoCEntityTameableAnimal specialEntity = null;
      if (entityType.equalsIgnoreCase("horse")) {
        moCEntity = new MoCEntityHorse(player.world);
        moCEntity.setAdult(true);
      } else if (entityType.equalsIgnoreCase("manticore")) {
        moCEntity = new MoCEntityManticorePet(player.world);
        moCEntity.setAdult(true);
      } else if (entityType.equalsIgnoreCase("wyvern")) {
        moCEntity = new MoCEntityWyvern(player.world);
        moCEntity.setAdult(false);
      } else if (entityType.equalsIgnoreCase("wyvernghost")) {
        moCEntity = new MoCEntityWyvern(player.world);
        moCEntity.setAdult(false);
        ((MoCEntityWyvern) moCEntity).setIsGhost(true);
      } else {
        sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "ERROR:" + TextFormatting.WHITE + "The entity spawn type " + entityType + " is not a valid type.", new Object[0]));

        return;
      }
      double dist = 3.0D;
      double newPosY = player.posY;
      double newPosX = player.posX - dist * Math.cos((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F));
      double newPosZ = player.posZ - dist * Math.sin((MoCTools.realAngle(player.rotationYaw - 90.0F) / 57.29578F));
      moCEntity.setPosition(newPosX, newPosY, newPosZ);
      moCEntity.setTamed(true);
      moCEntity.setOwnerId(null);
      moCEntity.setPetName("Rename_Me");
      moCEntity.setType(type);

      if ((entityType.equalsIgnoreCase("horse") && (type < 1 || type > 67)) || (entityType
        .equalsIgnoreCase("wyvern") && (type < 1 || type > 12)) || (entityType
        .equalsIgnoreCase("manticore") && (type < 1 || type > 4))) {
        sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "ERROR:" + TextFormatting.WHITE + "The spawn type " + type + " is not a valid type.", new Object[0]));

        return;
      }
      player.world.spawnEntity((Entity)moCEntity);
      if (!player.world.isRemote) {
        MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAppear(moCEntity.getEntityId()), new NetworkRegistry.TargetPoint(player.world.provider
              .getDimensionType().getId(), player.posX, player.posY, player.posZ, 64.0D));
      }
      MoCTools.playCustomSound((Entity)moCEntity, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR);
    } else {
      sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "ERROR:" + TextFormatting.WHITE + "Invalid spawn parameters entered.", new Object[0]));
    }
  }






  protected List<String> getSortedPossibleCommands(ICommandSender par1ICommandSender) {
    Collections.sort(commands);
    return commands;
  }

  public void sendCommandHelp(ICommandSender sender) {
    sender.sendMessage((ITextComponent)new TextComponentTranslation("§2Listing MoCreatures commands", new Object[0]));
    for (int i = 0; i < commands.size(); i++)
      sender.sendMessage((ITextComponent)new TextComponentTranslation(commands.get(i), new Object[0]));
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\command\CommandMoCSpawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
