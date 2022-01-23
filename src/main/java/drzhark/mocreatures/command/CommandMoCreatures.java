package drzhark.mocreatures.command;

import com.mojang.authlib.GameProfile;
import drzhark.mocreatures.MoCEntityData;
import drzhark.mocreatures.MoCPetData;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.configuration.MoCConfigCategory;
import drzhark.mocreatures.configuration.MoCConfiguration;
import drzhark.mocreatures.configuration.MoCProperty;
import drzhark.mocreatures.entity.IMoCTameable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommandMoCreatures extends CommandBase {
  private static List<String> commands = new ArrayList<>();
  private static List<String> aliases = new ArrayList<>();
  private static List<String> tabCompletionStrings = new ArrayList<>();

  static {
    commands.add("/moc attackdolphins <boolean>");
    commands.add("/moc attackhorses <boolean>");
    commands.add("/moc attackwolves <boolean>");
    commands.add("/moc canspawn <boolean>");
    commands.add("/moc caveogrechance <float>");
    commands.add("/moc caveogrestrength <float>");
    commands.add("/moc debug <boolean>");

    commands.add("/moc destroydrops <boolean>");
    commands.add("/moc enablehunters <boolean>");
    commands.add("/moc easybreeding <boolean>");
    commands.add("/moc elephantbulldozer <boolean>");
    commands.add("/moc enableownership <boolean>");
    commands.add("/moc enableresetownerscroll <boolean>");
    commands.add("/moc fireogrechance <int>");
    commands.add("/moc fireogrestrength <float>");
    commands.add("/moc frequency <entity> <int>");
    commands.add("/moc golemdestroyblocks <boolean>");
    commands.add("/moc tamed");
    commands.add("/moc tamed <playername>");
    commands.add("/moc maxchunk <entity> <int>");
    commands.add("/moc maxspawn <entity> <int>");
    commands.add("/moc maxtamedperop <int>");
    commands.add("/moc maxtamedperplayer <int>");
    commands.add("/moc minspawn <entity> <int>");
    commands.add("/moc motherwyverneggdropchance <int>");
    commands.add("/moc ogreattackrange <int>");
    commands.add("/moc ogrestrength <float>");
    commands.add("/moc ostricheggdropchance <int>");
    commands.add("/moc rareitemdropchance <int>");
    commands.add("/moc spawnhorse <int>");
    commands.add("/moc spawnwyvern <int>");
    commands.add("/moc tamedcount <playername>");
    commands.add("/moc tp <petid> <playername>");
    commands.add("/moc <command> value");
    commands.add("/moc wyverneggdropchance <int>");
    commands.add("/moc zebrachance <int>");
    aliases.add("moc");
    tabCompletionStrings.add("attackdolphins");
    tabCompletionStrings.add("attackhorses");
    tabCompletionStrings.add("attackwolves");
    tabCompletionStrings.add("canspawn");
    tabCompletionStrings.add("caveogrechance");
    tabCompletionStrings.add("caveogrestrength");
    tabCompletionStrings.add("debug");

    tabCompletionStrings.add("destroydrops");
    tabCompletionStrings.add("easybreeding");
    tabCompletionStrings.add("elephantbulldozer");
    tabCompletionStrings.add("enableownership");
    tabCompletionStrings.add("enableresetownerscroll");
    tabCompletionStrings.add("fireogrechance");
    tabCompletionStrings.add("fireogrestrength");
    tabCompletionStrings.add("forcedespawns");
    tabCompletionStrings.add("frequency");
    tabCompletionStrings.add("golemdestroyblocks");
    tabCompletionStrings.add("tamed");
    tabCompletionStrings.add("maxchunk");
    tabCompletionStrings.add("maxspawn");
    tabCompletionStrings.add("maxtamedperop");
    tabCompletionStrings.add("maxtamedperplayer");
    tabCompletionStrings.add("minspawn");
    tabCompletionStrings.add("motherwyverneggdropchance");
    tabCompletionStrings.add("ogreattackrange");
    tabCompletionStrings.add("ogreattackstrength");
    tabCompletionStrings.add("ostricheggdropchance");
    tabCompletionStrings.add("rareitemdropchance");
    tabCompletionStrings.add("spawnhorse");
    tabCompletionStrings.add("spawnwyvern");
    tabCompletionStrings.add("tamedcount");
    tabCompletionStrings.add("tp");
    tabCompletionStrings.add("wyverneggdropchance");
    tabCompletionStrings.add("zebrachance");
  }


  public String getName() {
    return "mocreatures";
  }


  public List<String> getAliases() {
    return aliases;
  }





  public int getRequiredPermissionLevel() {
    return 2;
  }


  public String getUsage(ICommandSender par1ICommandSender) {
    return "commands.mocreatures.usage";
  }





  public List<String> addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    return getListOfStringsMatchingLastWord(par2ArrayOfStr, tabCompletionStrings.<String>toArray(new String[tabCompletionStrings.size()]));
  }


  public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
    String command = "";
    if (args.length == 0) {
      command = "help";
    } else {
      command = args[0];
    }
    String par2 = "";
    if (args.length > 1) {
      par2 = args[1];
    }
    String par3 = "";
    if (args.length == 3) {
      par3 = args[2];
    }

    MoCConfiguration config = MoCreatures.proxy.mocSettingsConfig;
    boolean saved = false;

    if (command.equalsIgnoreCase("tamed") || command.equalsIgnoreCase("tame")) {
      if (args.length == 2 && !Character.isDigit(args[1].charAt(0))) {
        int unloadedCount = 0;
        int loadedCount = 0;
        ArrayList<Integer> foundIds = new ArrayList<>();
        ArrayList<String> tamedlist = new ArrayList<>();
        String playername = par2;
        GameProfile profile = server.getPlayerProfileCache().getGameProfileForUsername(playername);
        if (profile == null)
          return;  Integer[] arrayOfInteger;
        int i;
        byte b;
        for (arrayOfInteger = DimensionManager.getIDs(), i = arrayOfInteger.length, b = 0; b < i; ) { int dimension = arrayOfInteger[b].intValue();
          WorldServer world = DimensionManager.getWorld(dimension);
          for (int j = 0; j < world.loadedEntityList.size(); j++) {
            Entity entity = world.loadedEntityList.get(j);
            if (IMoCTameable.class.isAssignableFrom(entity.getClass())) {
              IMoCTameable mocreature = (IMoCTameable)entity;
              if (mocreature.getOwnerId().equals(profile.getId())) {
                loadedCount++;
                foundIds.add(Integer.valueOf(mocreature.getOwnerPetId()));
                tamedlist.add(TextFormatting.WHITE + "Found pet with " + TextFormatting.DARK_AQUA + "Type" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + ((EntityLiving)mocreature)

                    .getName() + TextFormatting.DARK_AQUA + ", Name" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + mocreature
                    .getPetName() + TextFormatting.DARK_AQUA + ", Owner" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + profile

                    .getName() + TextFormatting.DARK_AQUA + ", PetId" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + mocreature
                    .getOwnerPetId() + TextFormatting.DARK_AQUA + ", Dimension" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + entity.dimension + TextFormatting.DARK_AQUA + ", Pos" + TextFormatting.WHITE + ":" + TextFormatting.LIGHT_PURPLE +

                    Math.round(entity.posX) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE +
                    Math.round(entity.posY) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE +
                    Math.round(entity.posZ));
              }
            }
          }  b++; }

        MoCPetData ownerPetData = MoCreatures.instance.mapData.getPetData(profile.getId());
        if (ownerPetData != null) {
          for (int j = 0; j < ownerPetData.getTamedList().tagCount(); j++) {
            NBTTagCompound nbt = ownerPetData.getTamedList().getCompoundTagAt(j);
            if (nbt.hasKey("PetId") && !foundIds.contains(Integer.valueOf(nbt.getInteger("PetId")))) {
              unloadedCount++;
              double posX = nbt.getTagList("Pos", 6).getDoubleAt(0);
              double posY = nbt.getTagList("Pos", 6).getDoubleAt(1);
              double posZ = nbt.getTagList("Pos", 6).getDoubleAt(2);
              tamedlist.add(TextFormatting.WHITE + "Found unloaded pet with " + TextFormatting.DARK_AQUA + "Type" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
                  .getString("EntityName") + TextFormatting.DARK_AQUA + ", Name" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt

                  .getString("Name") + TextFormatting.DARK_AQUA + ", Owner" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
                  .getString("Owner") + TextFormatting.DARK_AQUA + ", PetId" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
                  .getInteger("PetId") + TextFormatting.DARK_AQUA + ", Dimension" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt

                  .getInteger("Dimension") + TextFormatting.DARK_AQUA + ", Pos" + TextFormatting.WHITE + ":" + TextFormatting.LIGHT_PURPLE +
                  Math.round(posX) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE +
                  Math.round(posY) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE +
                  Math.round(posZ));
            }
          }
        }
        if (tamedlist.size() > 0) {
          sendPageHelp(sender, (byte)10, tamedlist, args, "Listing tamed pets");
          sender.sendMessage((ITextComponent)new TextComponentTranslation("Loaded tamed count : " + TextFormatting.AQUA + loadedCount + TextFormatting.WHITE + ", Unloaded count : " + TextFormatting.AQUA + unloadedCount + TextFormatting.WHITE + ", Total count : " + TextFormatting.AQUA + ((ownerPetData != null) ? ownerPetData

                .getTamedList().tagCount() : 0), new Object[0]));
        } else {
          sender.sendMessage((ITextComponent)new TextComponentTranslation("Player " + TextFormatting.GREEN + playername + TextFormatting.WHITE + " does not have any tamed animals.", new Object[0]));
        }

      } else if (command.equalsIgnoreCase("tamed") || (command.equalsIgnoreCase("tame") && !par2.equals(""))) {
        int unloadedCount = 0;
        int loadedCount = 0;
        ArrayList<Integer> foundIds = new ArrayList<>();
        ArrayList<String> tamedlist = new ArrayList<>(); Integer[] arrayOfInteger; int i;
        byte b;
        for (arrayOfInteger = DimensionManager.getIDs(), i = arrayOfInteger.length, b = 0; b < i; ) { int dimension = arrayOfInteger[b].intValue();
          WorldServer world = DimensionManager.getWorld(dimension);
          for (int j = 0; j < world.loadedEntityList.size(); j++) {
            Entity entity = world.loadedEntityList.get(j);
            if (IMoCTameable.class.isAssignableFrom(entity.getClass())) {
              IMoCTameable mocreature = (IMoCTameable)entity;
              if (mocreature.getOwnerPetId() != -1) {
                loadedCount++;
                foundIds.add(Integer.valueOf(mocreature.getOwnerPetId()));
                tamedlist.add(TextFormatting.WHITE + "Found pet with " + TextFormatting.DARK_AQUA + "Type" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + ((EntityLiving)mocreature)

                    .getName() + TextFormatting.DARK_AQUA + ", Name" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + mocreature
                    .getPetName() + TextFormatting.DARK_AQUA + ", Owner" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + mocreature

                    .getOwnerId() + TextFormatting.DARK_AQUA + ", PetId" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + mocreature
                    .getOwnerPetId() + TextFormatting.DARK_AQUA + ", Dimension" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + entity.dimension + TextFormatting.DARK_AQUA + ", Pos" + TextFormatting.WHITE + ":" + TextFormatting.LIGHT_PURPLE +

                    Math.round(entity.posX) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE +
                    Math.round(entity.posY) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE +
                    Math.round(entity.posZ));
              }
            }
          }

          b++; }

        for (MoCPetData ownerPetData : MoCreatures.instance.mapData.getPetMap().values()) {
          for (int j = 0; j < ownerPetData.getTamedList().tagCount(); j++) {
            NBTTagCompound nbt = ownerPetData.getTamedList().getCompoundTagAt(j);
            if (nbt.hasKey("PetId") && !foundIds.contains(Integer.valueOf(nbt.getInteger("PetId")))) {
              unloadedCount++;
              double posX = nbt.getTagList("Pos", 10).getDoubleAt(0);
              double posY = nbt.getTagList("Pos", 10).getDoubleAt(1);
              double posZ = nbt.getTagList("Pos", 10).getDoubleAt(2);
              tamedlist.add(TextFormatting.WHITE + "Found unloaded pet with " + TextFormatting.DARK_AQUA + "Type" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
                  .getString("EntityName") + TextFormatting.DARK_AQUA + ", Name" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt

                  .getString("Name") + TextFormatting.DARK_AQUA + ", Owner" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
                  .getString("Owner") + TextFormatting.DARK_AQUA + ", PetId" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
                  .getInteger("PetId") + TextFormatting.DARK_AQUA + ", Dimension" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt

                  .getInteger("Dimension") + TextFormatting.DARK_AQUA + ", Pos" + TextFormatting.WHITE + ":" + TextFormatting.LIGHT_PURPLE +
                  Math.round(posX) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE +
                  Math.round(posY) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE +
                  Math.round(posZ));
            }
          }
        }

        sendPageHelp(sender, (byte)10, tamedlist, args, "Listing tamed pets");
        sender.sendMessage((ITextComponent)new TextComponentTranslation("Loaded tamed count : " + TextFormatting.AQUA + loadedCount + TextFormatting.WHITE + (



              !MoCreatures.isServer() ? (", Unloaded Count : " + TextFormatting.AQUA + unloadedCount + TextFormatting.WHITE + ", Total count : " + TextFormatting.AQUA + (loadedCount + unloadedCount)) : ""), new Object[0]));
      }

    } else if (command.equalsIgnoreCase("tp") && args.length == 3) {
      int petId = 0;
      try {
        petId = Integer.parseInt(par2);
      } catch (NumberFormatException e) {
        petId = -1;
      }
      String playername = args[2];

      EntityPlayerMP player = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(playername);
      if (player == null) {
        return;
      }

      MoCPetData ownerPetData = MoCreatures.instance.mapData.getPetData(player.getUniqueID());
      if (ownerPetData != null) {
        for (int i = 0; i < ownerPetData.getTamedList().tagCount(); i++) {
          NBTTagCompound nbt = ownerPetData.getTamedList().getCompoundTagAt(i);
          if (nbt.hasKey("PetId") && nbt.getInteger("PetId") == petId) {
            String petName = nbt.getString("Name");
            WorldServer world = DimensionManager.getWorld(nbt.getInteger("Dimension"));
            if (!teleportLoadedPet(world, player, petId, petName, sender)) {
              double posX = nbt.getTagList("Pos", 10).getDoubleAt(0);
              double posY = nbt.getTagList("Pos", 10).getDoubleAt(1);
              double posZ = nbt.getTagList("Pos", 10).getDoubleAt(2);
              sender.sendMessage((ITextComponent)new TextComponentTranslation("Found unloaded pet " + TextFormatting.GREEN + nbt
                    .getString("id") + TextFormatting.WHITE + " with name " + TextFormatting.AQUA + nbt
                    .getString("Name") + TextFormatting.WHITE + " at location " + TextFormatting.LIGHT_PURPLE +
                    Math.round(posX) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + Math.round(posY) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE +
                    Math.round(posZ) + TextFormatting.WHITE + " with Pet ID " + TextFormatting.BLUE + nbt
                    .getInteger("PetId"), new Object[0]));
              boolean result = teleportLoadedPet(world, player, petId, petName, sender);
              if (!result) {
                sender.sendMessage((ITextComponent)new TextComponentTranslation("Unable to transfer entity ID " + TextFormatting.GREEN + petId + TextFormatting.WHITE + ". It may only be transferred to " + TextFormatting.AQUA + player

                      .getName(), new Object[0]));
              }
            }
            break;
          }
        }
      } else {
        sender.sendMessage((ITextComponent)new TextComponentTranslation("Tamed entity could not be located.", new Object[0]));
      }
    } else if (command.equalsIgnoreCase("tamedcount")) {
      String playername = par2;
      List<EntityPlayerMP> players = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers();
      for (int i = 0; i < players.size(); i++) {
        EntityPlayerMP player = players.get(i);
        if (player.getName().equalsIgnoreCase(playername)) {
          int tamedCount = MoCTools.numberTamedByPlayer((EntityPlayer)player);
          sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + playername + "'s recorded tamed count is " + TextFormatting.AQUA + tamedCount, new Object[0]));
        }
      }

      sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "Could not find player " + TextFormatting.GREEN + playername + TextFormatting.RED + ". Please verify the player is online and/or name was entered correctly.", new Object[0]));



    }
    else if (args.length >= 2 && (command
      .equalsIgnoreCase("frequency") || command.equalsIgnoreCase("minspawn") || command.equalsIgnoreCase("maxspawn") || command
      .equalsIgnoreCase("maxchunk") || command.equalsIgnoreCase("canspawn"))) {
      MoCEntityData entityData = (MoCEntityData)MoCreatures.mocEntityMap.get(par2);

      if (entityData != null) {
        if (command.equalsIgnoreCase("frequency")) {
          if (par3 == null) {
            sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + entityData.getEntityName() + TextFormatting.WHITE + " frequency is " + TextFormatting.AQUA + entityData
                  .getFrequency() + TextFormatting.WHITE + ".", new Object[0]));
          } else {

            try {
              entityData.setFrequency(Integer.parseInt(par3));
              MoCProperty prop = MoCreatures.proxy.mocEntityConfig.get(entityData.getEntityName(), "frequency");
              prop.value = par3;
              saved = true;
              sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + entityData
                    .getEntityName() + TextFormatting.WHITE + " frequency to " + TextFormatting.AQUA + par3 + TextFormatting.WHITE + ".", new Object[0]));
            }
            catch (NumberFormatException ex) {
              sendCommandHelp(sender);
            }
          }
        } else if (command.equalsIgnoreCase("min") || command.equalsIgnoreCase("minspawn")) {
          if (par3 == null) {
            sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + entityData.getEntityName() + TextFormatting.WHITE + " minGroupSpawn is " + TextFormatting.AQUA + entityData
                  .getMinSpawn() + TextFormatting.WHITE + ".", new Object[0]));
          } else {

            try {
              entityData.setMinSpawn(Integer.parseInt(par3));
              MoCProperty prop = MoCreatures.proxy.mocEntityConfig.get(entityData.getEntityName(), "minspawn");
              prop.value = par3;
              saved = true;
              sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + entityData
                    .getEntityName() + TextFormatting.WHITE + " minGroupSpawn to " + TextFormatting.AQUA + par3 + TextFormatting.WHITE + ".", new Object[0]));
            }
            catch (NumberFormatException ex) {
              sendCommandHelp(sender);
            }
          }
        } else if (command.equalsIgnoreCase("max") || command.equalsIgnoreCase("maxspawn")) {
          if (par3 == null) {
            sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + entityData.getEntityName() + TextFormatting.WHITE + " maxGroupSpawn is " + TextFormatting.AQUA + entityData
                  .getMaxSpawn() + TextFormatting.WHITE + ".", new Object[0]));
          } else {

            try {
              entityData.setMaxSpawn(Integer.parseInt(par3));
              MoCProperty prop = MoCreatures.proxy.mocEntityConfig.get(entityData.getEntityName(), "maxspawn");
              prop.value = par3;
              saved = true;
              sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + entityData
                    .getEntityName() + TextFormatting.WHITE + " maxGroupSpawn to " + TextFormatting.AQUA + par3 + TextFormatting.WHITE + ".", new Object[0]));
            }
            catch (NumberFormatException ex) {
              sendCommandHelp(sender);
            }
          }
        } else if (command.equalsIgnoreCase("chunk") || command.equalsIgnoreCase("maxchunk")) {
          if (par3 == null) {
            sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + entityData.getEntityName() + TextFormatting.WHITE + " maxInChunk is " + TextFormatting.AQUA + entityData
                  .getMaxInChunk() + TextFormatting.WHITE + ".", new Object[0]));
          } else {

            try {
              entityData.setMaxSpawn(Integer.parseInt(par3));
              MoCProperty prop = MoCreatures.proxy.mocEntityConfig.get(entityData.getEntityName(), "maxchunk");
              prop.value = par3;
              saved = true;
              sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + entityData
                    .getEntityName() + TextFormatting.WHITE + " maxInChunk to " + TextFormatting.AQUA + par3 + TextFormatting.WHITE + ".", new Object[0]));
            }
            catch (NumberFormatException ex) {
              sendCommandHelp(sender);
            }
          }
        } else if (command.equalsIgnoreCase("canspawn")) {
          if (par3 == null) {
            sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + entityData.getEntityName() + TextFormatting.WHITE + " canSpawn is " + TextFormatting.AQUA + entityData
                  .getCanSpawn() + TextFormatting.WHITE + ".", new Object[0]));
          } else {

            try {
              entityData.setCanSpawn(Boolean.parseBoolean(par3));
              MoCProperty prop = MoCreatures.proxy.mocEntityConfig.get(entityData.getEntityName(), "canspawn");
              prop.set(par3);
              saved = true;
              sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + entityData
                    .getEntityName() + TextFormatting.WHITE + " canSpawn to " + TextFormatting.AQUA + par3 + TextFormatting.WHITE + ".", new Object[0]));
            }
            catch (NumberFormatException ex) {
              sendCommandHelp(sender);
            }

          }
        }
      }
    } else if (args.length == 1) {
      for (Map.Entry<String, MoCConfigCategory> catEntry : (Iterable<Map.Entry<String, MoCConfigCategory>>)config.categories.entrySet()) {
        String catName = ((MoCConfigCategory)catEntry.getValue()).getQualifiedName();
        if (catName.equalsIgnoreCase("custom-id-settings")) {
          continue;
        }

        for (Map.Entry<String, MoCProperty> propEntry : (Iterable<Map.Entry<String, MoCProperty>>)((MoCConfigCategory)catEntry.getValue()).entrySet()) {
          if (propEntry.getValue() == null || !((String)propEntry.getKey()).equalsIgnoreCase(command)) {
            continue;
          }
          List<String> propList = ((MoCProperty)propEntry.getValue()).valueList;
          String propValue = ((MoCProperty)propEntry.getValue()).value;
          if (propList == null && propValue == null) {
            continue;
          }
          if (par2.equals("")) {
            sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + (String)propEntry.getKey() + TextFormatting.WHITE + " is " + TextFormatting.AQUA + propValue, new Object[0]));




            // Byte code: goto -> 5244
          }
        }
      }
    } else {
      for (Map.Entry<String, MoCConfigCategory> catEntry : (Iterable<Map.Entry<String, MoCConfigCategory>>)config.categories.entrySet()) {
        for (Map.Entry<String, MoCProperty> propEntry : (Iterable<Map.Entry<String, MoCProperty>>)((MoCConfigCategory)catEntry.getValue()).entrySet()) {
          if (propEntry.getValue() == null || !((String)propEntry.getKey()).equalsIgnoreCase(command)) {
            continue;
          }
          MoCProperty property = propEntry.getValue();
          List<String> propList = ((MoCProperty)propEntry.getValue()).valueList;
          String propValue = ((MoCProperty)propEntry.getValue()).getString();

          if (propList == null && propValue == null) {
            continue;
          }

          if (((MoCProperty)propEntry.getValue()).getType() == MoCProperty.Type.BOOLEAN) {
            if (par2.equalsIgnoreCase("true") || par2.equalsIgnoreCase("false")) {
              property.set(par2);
              saved = true;
              sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + (String)propEntry.getKey() + " to " + TextFormatting.AQUA + par2 + ".", new Object[0]));
            }  continue;
          }
          if (((MoCProperty)propEntry.getValue()).getType() == MoCProperty.Type.INTEGER) {
            try {
              Integer.parseInt(par2);
              property.set(par2);
              saved = true;
              sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + (String)propEntry.getKey() + " to " + TextFormatting.AQUA + par2 + ".", new Object[0]));
            }
            catch (NumberFormatException ex) {
              sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "Invalid value entered. Please enter a valid number.", new Object[0]));
            }
            continue;
          }
          if (((MoCProperty)propEntry.getValue()).getType() == MoCProperty.Type.DOUBLE) {
            try {
              Double.parseDouble(par2);
              property.set(par2);
              saved = true;
              sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + (String)propEntry.getKey() + " to " + TextFormatting.AQUA + par2 + ".", new Object[0]));
            }
            catch (NumberFormatException ex) {
              sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "Invalid value entered. Please enter a valid number.", new Object[0]));
            }
          }
        }
      }
    }



    if (command.equalsIgnoreCase("help")) {
      List<String> list = getSortedPossibleCommands(sender);
      byte b0 = 10;
      int i = (list.size() - 1) / b0;
      int j = 0;

      if (args.length > 1) {
        try {
          j = (args.length == 0) ? 0 : (parseInt(args[1], 1, i + 1) - 1);
        } catch (NumberInvalidException numberinvalidexception) {
          numberinvalidexception.printStackTrace();
        }
      }

      int k = Math.min((j + 1) * b0, list.size());
      sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.DARK_GREEN + "--- Showing MoCreatures help page " +
            Integer.valueOf(j + 1) + " of " + Integer.valueOf(i + 1) + "(/moc help <page>)---", new Object[0]));

      for (int l = j * b0; l < k; l++) {
        String commandToSend = list.get(l);
        sender.sendMessage((ITextComponent)new TextComponentTranslation(commandToSend, new Object[0]));
      }
    }

    if (saved) {

      config.save();
      MoCreatures.proxy.readGlobalConfigValues();
    }
  }





  protected List<String> getSortedPossibleCommands(ICommandSender par1ICommandSender) {
    Collections.sort(commands);
    return commands;
  }

  public boolean teleportLoadedPet(WorldServer world, EntityPlayerMP player, int petId, String petName, ICommandSender par1ICommandSender) {
    for (int j = 0; j < world.loadedEntityList.size(); j++) {
      Entity entity = world.loadedEntityList.get(j);

      if (IMoCTameable.class.isAssignableFrom(entity.getClass()) && !((IMoCTameable)entity).getPetName().equals("") && ((IMoCTameable)entity)
        .getOwnerPetId() == petId) {

        NBTTagCompound compound = new NBTTagCompound();
        entity.writeToNBT(compound);
        if (compound != null && compound.getString("Owner") != null) {
          String owner = compound.getString("Owner");
          String name = compound.getString("Name");
          if (owner != null && owner.equalsIgnoreCase(player.getName())) {

            if (entity.dimension == player.dimension) {
              entity.setPosition(player.posX, player.posY, player.posZ);
            } else if (!player.world.isRemote) {

              Entity newEntity = EntityList.newEntity(entity.getClass(), player.world);
              if (newEntity != null) {
                MoCTools.copyDataFromOld(newEntity, entity);
                newEntity.setPosition(player.posX, player.posY, player.posZ);
                DimensionManager.getWorld(player.dimension).spawnEntity(newEntity);
              }
              if (entity.getRidingEntity() == null) {
                entity.isDead = true;
              } else {

                entity.getRidingEntity().dismountRidingEntity();
                entity.isDead = true;
              }
              world.resetUpdateEntityTick();
              DimensionManager.getWorld(player.dimension).resetUpdateEntityTick();
            }
            par1ICommandSender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + name + TextFormatting.WHITE + " has been tp'd to location " +
                  Math.round(player.posX) + ", " + Math.round(player.posY) + ", " +
                  Math.round(player.posZ) + " in dimension " + player.dimension, new Object[0]));
            return true;
          }
        }
      }
    }
    return false;
  }

  public void sendCommandHelp(ICommandSender sender) {
    sender.sendMessage((ITextComponent)new TextComponentTranslation("§2Listing MoCreatures commands", new Object[0]));
    for (int i = 0; i < commands.size(); i++) {
      sender.sendMessage((ITextComponent)new TextComponentTranslation(commands.get(i), new Object[0]));
    }
  }

  public void sendPageHelp(ICommandSender sender, byte pagelimit, ArrayList<String> list, String[] par2ArrayOfStr, String title) {
    int x = (list.size() - 1) / pagelimit;
    int j = 0;

    if (Character.isDigit(par2ArrayOfStr[par2ArrayOfStr.length - 1].charAt(0))) {
      try {
        j = (par2ArrayOfStr.length == 0) ? 0 : (parseInt(par2ArrayOfStr[par2ArrayOfStr.length - 1], 1, x + 1) - 1);
      } catch (NumberInvalidException numberinvalidexception) {
        numberinvalidexception.printStackTrace();
      }
    }
    int k = Math.min((j + 1) * pagelimit, list.size());

    sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.WHITE + title + " (pg " + TextFormatting.WHITE +
          Integer.valueOf(j + 1) + TextFormatting.DARK_GREEN + "/" + TextFormatting.WHITE + Integer.valueOf(x + 1) + ")", new Object[0]));

    for (int l = j * pagelimit; l < k; l++) {
      String tamedInfo = list.get(l);
      sender.sendMessage((ITextComponent)new TextComponentTranslation(tamedInfo, new Object[0]));
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\command\CommandMoCreatures.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
