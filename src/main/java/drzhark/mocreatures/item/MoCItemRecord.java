package drzhark.mocreatures.item;

import drzhark.mocreatures.MoCreatures;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MoCItemRecord
  extends ItemRecord
{
  public static ResourceLocation RECORD_SHUFFLE_RESOURCE = new ResourceLocation("mocreatures", "shuffling");

  public MoCItemRecord(String name, SoundEvent soundEvent) {
    super(name, soundEvent);
    setCreativeTab(MoCreatures.tabMoC);
    setRegistryName("mocreatures", name);
    setUnlocalizedName(name);
  }




  @SideOnly(Side.CLIENT)
  public String getRecordTitle() {
    return "MoC - " + getRecordNameLocal();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
