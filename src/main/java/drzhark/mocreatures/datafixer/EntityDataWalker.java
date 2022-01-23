package drzhark.mocreatures.datafixer;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.datafix.IDataWalker;

public class EntityDataWalker
  implements IDataWalker
{
  public NBTTagCompound process(IDataFixer fixer, NBTTagCompound compound, int version) {
    String entityId = compound.getString("id");
    if (entityId.contains("mocreatures.")) {
      String entityName = entityId.replace("mocreatures.", "").toLowerCase();
      if (entityName.equalsIgnoreCase("polarbear")) {
        entityName = "wildpolarbear";
      }
      String fixedEntityId = "mocreatures:" + entityName;
      compound.setString("id", fixedEntityId);
    }
    return compound;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\datafixer\EntityDataWalker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
