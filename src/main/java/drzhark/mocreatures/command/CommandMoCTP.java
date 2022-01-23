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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class CommandMoCTP
  extends CommandBase {
  private static List<String> commands = new ArrayList<>();
  private static List<String> aliases = new ArrayList<>();

  static {
    commands.add("/moctp <entityid> <playername>");
    commands.add("/moctp <petname> <playername>");
    aliases.add("moctp");
  }



  public String getName() {
    return "moctp";
  }


  public List<String> getAliases() {
    return aliases;
  }





  public int getRequiredPermissionLevel() {
    return 2;
  }


  public String getUsage(ICommandSender par1ICommandSender) {
    return "commands.moctp.usage";
  }


  public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
    int petId = 0;
    if (args == null || args.length == 0) {
      sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "Error" + TextFormatting.WHITE + ": You must enter a valid entity ID.", new Object[0]));

      return;
    }
    if (!(sender instanceof EntityPlayer)) {
      return;
    }
    try {
      petId = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      petId = -1;
    }
    String playername = sender.getName();
    EntityPlayer player = (EntityPlayer)sender;

    MoCPetData ownerPetData = MoCreatures.instance.mapData.getPetData(player.getUniqueID());
    if ((((player != null) ? 1 : 0) & ((ownerPetData != null) ? 1 : 0)) != 0) {
      for (int i = 0; i < ownerPetData.getTamedList().tagCount(); i++) {
        NBTTagCompound nbt = ownerPetData.getTamedList().getCompoundTagAt(i);
        if (nbt.hasKey("PetId") && nbt.getInteger("PetId") == petId) {
          String petName = nbt.getString("Name");
          WorldServer world = DimensionManager.getWorld(nbt.getInteger("Dimension"));
          if (!teleportLoadedPet(world, player, petId, petName, sender)) {
            double posX = nbt.getTagList("Pos", 6).getDoubleAt(0);
            double posY = nbt.getTagList("Pos", 6).getDoubleAt(1);
            double posZ = nbt.getTagList("Pos", 6).getDoubleAt(2);
            int x = MathHelper.floor(posX);
            int y = MathHelper.floor(posY);
            int z = MathHelper.floor(posZ);
            sender.sendMessage((ITextComponent)new TextComponentTranslation("Found unloaded pet " + TextFormatting.GREEN + nbt
                  .getString("id") + TextFormatting.WHITE + " with name " + TextFormatting.AQUA + nbt.getString("Name") + TextFormatting.WHITE + " at location " + TextFormatting.LIGHT_PURPLE + x + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + y + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + z + TextFormatting.WHITE + " with Pet ID " + TextFormatting.BLUE + nbt


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
  }





  protected List<String> getSortedPossibleCommands(ICommandSender par1ICommandSender) {
    Collections.sort(commands);
    return commands;
  }

  public boolean teleportLoadedPet(WorldServer world, EntityPlayer player, int petId, String petName, ICommandSender par1ICommandSender) {
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
    for (int i = 0; i < commands.size(); i++)
      sender.sendMessage((ITextComponent)new TextComponentTranslation(commands.get(i), new Object[0]));
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\command\CommandMoCTP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
