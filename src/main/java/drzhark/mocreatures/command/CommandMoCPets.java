package drzhark.mocreatures.command;

import drzhark.mocreatures.MoCPetData;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

public class CommandMoCPets
  extends CommandBase {
  private static List<String> commands = new ArrayList<>();
  private static List<String> aliases = new ArrayList<>();

  static {
    commands.add("/mocpets");
    aliases.add("mocpets");
  }



  public String getName() {
    return "mocpets";
  }


  public List<String> getAliases() {
    return aliases;
  }





  public int getRequiredPermissionLevel() {
    return 2;
  }


  public String getUsage(ICommandSender par1ICommandSender) {
    return "commands.mocpets.usage";
  }


  public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
    int unloadedCount = 0;
    int loadedCount = 0;
    ArrayList<Integer> foundIds = new ArrayList<>();
    ArrayList<String> tamedlist = new ArrayList<>();
    if (!(sender instanceof EntityPlayer)) {
      return;
    }
    String playername = sender.getName();
    EntityPlayer player = (EntityPlayer)sender; Integer[] arrayOfInteger; int i;
    byte b;
    for (arrayOfInteger = DimensionManager.getIDs(), i = arrayOfInteger.length, b = 0; b < i; ) { int dimension = arrayOfInteger[b].intValue();
      WorldServer world = DimensionManager.getWorld(dimension);
      for (int j = 0; j < world.loadedEntityList.size(); j++) {
        Entity entity = world.loadedEntityList.get(j);
        if (IMoCTameable.class.isAssignableFrom(entity.getClass())) {
          IMoCTameable mocreature = (IMoCTameable)entity;
          if (mocreature.getOwnerId() != null && mocreature.getOwnerId().equals(playername)) {
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
      }  b++; }

    MoCPetData ownerPetData = MoCreatures.instance.mapData.getPetData(player.getUniqueID());
    if (ownerPetData != null) {
      MoCreatures.instance.mapData.forceSave();
      for (int j = 0; j < ownerPetData.getTamedList().tagCount(); j++) {
        NBTTagCompound nbt = ownerPetData.getTamedList().getCompoundTagAt(j);
        if (nbt.hasKey("PetId") && !foundIds.contains(Integer.valueOf(nbt.getInteger("PetId")))) {
          unloadedCount++;
          double posX = nbt.getTagList("Pos", 6).getDoubleAt(0);
          double posY = nbt.getTagList("Pos", 6).getDoubleAt(1);
          double posZ = nbt.getTagList("Pos", 6).getDoubleAt(2);
          if (nbt.getBoolean("InAmulet")) {
            tamedlist.add(TextFormatting.WHITE + "Found unloaded pet in " + TextFormatting.DARK_PURPLE + "AMULET" + TextFormatting.WHITE + " with " + TextFormatting.DARK_AQUA + "Type" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt

                .getString("id").replace("MoCreatures.", "") + TextFormatting.DARK_AQUA + ", Name" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
                .getString("Name") + TextFormatting.DARK_AQUA + ", Owner" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt

                .getString("Owner") + TextFormatting.DARK_AQUA + ", PetId" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
                .getInteger("PetId") + TextFormatting.WHITE + ".");
          } else {
            tamedlist.add(TextFormatting.WHITE + "Found unloaded pet with " + TextFormatting.DARK_AQUA + "Type" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
                .getString("id").replace("MoCreatures.", "") + TextFormatting.DARK_AQUA + ", Name" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt

                .getString("Name") + TextFormatting.DARK_AQUA + ", Owner" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
                .getString("Owner") + TextFormatting.DARK_AQUA + ", PetId" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
                .getInteger("PetId") + TextFormatting.DARK_AQUA + ", Dimension" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
                .getInteger("Dimension") + TextFormatting.DARK_AQUA + ", Pos" + TextFormatting.WHITE + ":" + TextFormatting.LIGHT_PURPLE +

                Math.round(posX) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + Math.round(posY) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE +
                Math.round(posZ));
          }
        }
      }
    }

    if (tamedlist.size() > 0) {
      sendPageHelp(sender, (byte)10, tamedlist, args);
      sender.sendMessage((ITextComponent)new TextComponentTranslation("Loaded tamed count : " + TextFormatting.AQUA + loadedCount + TextFormatting.WHITE + ", Unloaded count : " + TextFormatting.AQUA + unloadedCount + TextFormatting.WHITE + ", Total count : " + TextFormatting.AQUA + ((ownerPetData != null) ? ownerPetData

            .getTamedList().tagCount() : 0), new Object[0]));
    } else {
      sender.sendMessage((ITextComponent)new TextComponentTranslation("Loaded tamed count : " + TextFormatting.AQUA + loadedCount + TextFormatting.WHITE + (



            !MoCreatures.isServer() ? (", Unloaded Count : " + TextFormatting.AQUA + unloadedCount + TextFormatting.WHITE + ", Total count : " + TextFormatting.AQUA + (loadedCount + unloadedCount)) : ""), new Object[0]));
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

  public void sendPageHelp(ICommandSender sender, byte pagelimit, ArrayList<String> list, String[] par2ArrayOfStr) {
    int x = (list.size() - 1) / pagelimit;
    int j = 0;

    if (par2ArrayOfStr.length > 0 && Character.isDigit(par2ArrayOfStr[0].charAt(0))) {
      try {
        j = (par2ArrayOfStr.length == 0) ? 0 : (parseInt(par2ArrayOfStr[0], 1, x + 1) - 1);
      } catch (NumberInvalidException numberinvalidexception) {
        numberinvalidexception.printStackTrace();
      }
    }
    int k = Math.min((j + 1) * pagelimit, list.size());

    sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.DARK_GREEN + "--- Showing MoCreatures Help Info " + TextFormatting.AQUA +
          Integer.valueOf(j + 1) + TextFormatting.WHITE + " of " + TextFormatting.AQUA +
          Integer.valueOf(x + 1) + TextFormatting.GRAY + " (/mocpets <page>)" + TextFormatting.DARK_GREEN + "---", new Object[0]));

    for (int l = j * pagelimit; l < k; l++) {
      String tamedInfo = list.get(l);
      sender.sendMessage((ITextComponent)new TextComponentTranslation(tamedInfo, new Object[0]));
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\command\CommandMoCPets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
