package drzhark.mocreatures.entity.monster;

import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class MoCEntityFireOgre extends MoCEntityOgre {
  public MoCEntityFireOgre(World world) {
    super(world);
    this.isImmuneToFire = true;
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture("ogrered.png");
  }


  public boolean isFireStarter() {
    return true;
  }


  public float getDestroyForce() {
    return MoCreatures.proxy.fireOgreStrength;
  }


  protected boolean isHarmedByDaylight() {
    return true;
  }


  protected Item getDropItem() {
    boolean flag = (this.rand.nextInt(100) < MoCreatures.proxy.rareItemDropChance);
    if (!flag) {
      return Item.getItemFromBlock((Block)Blocks.FIRE);
    }
    return (Item)MoCItems.heartfire;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityFireOgre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
