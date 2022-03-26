package drzhark.mocreatures.item;

import drzhark.mocreatures.MoCreatures;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;





public class MoCItemCreaturePedia
  extends MoCItem
{
  public MoCItemCreaturePedia(String name) {
    super(name);
    this.maxStackSize = 1;
  }




  public void itemInteractionForEntity2(ItemStack par1ItemStack, EntityLiving entityliving) {
    if (entityliving.world.isRemote) {
      return;
    }

    if (entityliving instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse) {
      MoCreatures.showCreaturePedia("/mocreatures/pedia/horse.png");

      return;
    }
    if (entityliving instanceof drzhark.mocreatures.entity.passive.MoCEntityTurtle) {
      MoCreatures.showCreaturePedia("/mocreatures/pedia/turtle.png");

      return;
    }
    if (entityliving instanceof drzhark.mocreatures.entity.passive.MoCEntityBunny) {
      MoCreatures.showCreaturePedia("/mocreatures/pedia/bunny.png");

      return;
    }
    if (entityliving instanceof drzhark.mocreatures.entity.aquatic.MoCEntityDolphin) {
      MoCreatures.showCreaturePedia("/mocreatures/pedia/dolphin.png");
      return;
    }
  }


  public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
    ItemStack stack = player.getHeldItem(hand);
    if (!world.isRemote) {
      double dist = 5.0D;
      double d1 = -1.0D;
      EntityLivingBase entityliving = null;
      List<Entity> list = world.getEntitiesWithinAABBExcludingEntity((Entity)player, player.getEntityBoundingBox().expand(dist, dist, dist));
      for (int i = 0; i < list.size(); i++) {
        Entity entity1 = list.get(i);
        if (entity1 != null && entity1 instanceof EntityLivingBase)
        {


          if (player.canEntityBeSeen(entity1)) {



            double d2 = entity1.getDistanceSq(player.posX, player.posY, player.posZ);
            if ((dist < 0.0D || d2 < dist * dist) && (d1 == -1.0D || d2 < d1) && ((EntityLivingBase)entity1)
              .canEntityBeSeen((Entity)player)) {
              d1 = d2;
              entityliving = (EntityLivingBase)entity1;
            }
          }  }
      }
      if (entityliving == null) {
        return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
      }

      if (entityliving instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse) {
        MoCreatures.showCreaturePedia(player, "/mocreatures/pedia/horse.png");
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
      }

      if (entityliving instanceof drzhark.mocreatures.entity.passive.MoCEntityTurtle) {
        MoCreatures.showCreaturePedia(player, "/mocreatures/pedia/turtle.png");
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
      }

      if (entityliving instanceof drzhark.mocreatures.entity.passive.MoCEntityBunny) {
        MoCreatures.showCreaturePedia(player, "/mocreatures/pedia/bunny.png");
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
      }
    }

    return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemCreaturePedia.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
