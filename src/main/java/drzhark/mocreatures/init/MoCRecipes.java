package drzhark.mocreatures.init;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder("mocreatures")
public class MoCRecipes
{
  @EventBusSubscriber(modid = "mocreatures")
  public static class RegistrationHandler
  {
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
      GameRegistry.addSmelting((Item)MoCItems.crabraw, new ItemStack((Item)MoCItems.crabcooked, 1), 0.0F);

      GameRegistry.addSmelting((Item)MoCItems.ratRaw, new ItemStack((Item)MoCItems.ratCooked, 1), 0.0F);

      GameRegistry.addSmelting((Item)MoCItems.ostrichraw, new ItemStack((Item)MoCItems.ostrichcooked, 1), 0.0F);

      GameRegistry.addSmelting((Item)MoCItems.rawTurkey, new ItemStack((Item)MoCItems.cookedTurkey, 1), 0.0F);

      GameRegistry.addSmelting((Item)MoCItems.mocegg, new ItemStack((Item)MoCItems.omelet, 1), 0.0F);

      GameRegistry.addSmelting(Items.EGG, new ItemStack((Item)MoCItems.omelet, 1), 0.0F);
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\init\MoCRecipes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
