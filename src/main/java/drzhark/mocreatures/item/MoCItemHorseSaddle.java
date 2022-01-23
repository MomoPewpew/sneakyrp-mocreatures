package drzhark.mocreatures.item;

import drzhark.mocreatures.entity.passive.MoCEntityHorse;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class MoCItemHorseSaddle
  extends MoCItem {
  public MoCItemHorseSaddle(String name) {
    super(name);
    this.maxStackSize = 32;
  }


  public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
    if (target instanceof MoCEntityHorse) {
      MoCEntityHorse entityhorse = (MoCEntityHorse)target;
      if (!entityhorse.getIsRideable() && entityhorse.getIsAdult()) {
        entityhorse.setRideable(true);
        stack.shrink(1);
        return true;
      }
    }
    return false;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemHorseSaddle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
