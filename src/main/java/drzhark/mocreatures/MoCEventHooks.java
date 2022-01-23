package drzhark.mocreatures;

import drzhark.mocreatures.entity.IMoCTameable;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;






public class MoCEventHooks
{
  @SubscribeEvent
  public void onWorldUnload(WorldEvent.Unload event) {
    if ((event.getWorld()).provider.getDimensionType().getId() == 0) {
      MoCreatures.proxy.worldInitDone = false;
    }
  }

  @SubscribeEvent
  public void onWorldLoad(WorldEvent.Load event) {
    if (DimensionManager.getWorld(0) != null && !MoCreatures.proxy.worldInitDone) {

      MoCPetMapData data = (MoCPetMapData)DimensionManager.getWorld(0).getMapStorage().getOrLoadData(MoCPetMapData.class, "mocreatures");
      if (data == null) {
        data = new MoCPetMapData("mocreatures");
      }

      DimensionManager.getWorld(0).getMapStorage().setData("mocreatures", data);
      DimensionManager.getWorld(0).getMapStorage().saveAllData();
      MoCreatures.instance.mapData = data;
      MoCreatures.proxy.worldInitDone = true;
    }

    GameRules gameRule = event.getWorld().getGameRules();
    if (gameRule != null && !MoCreatures.isCustomSpawnerLoaded) {
      gameRule.setOrCreateGameRule("doMobSpawning", "true");
    }
  }

  @SubscribeEvent
  public void onLivingDeathEvent(LivingDeathEvent event) {
    if (!(event.getEntity()).world.isRemote &&
      IMoCTameable.class.isAssignableFrom(event.getEntityLiving().getClass())) {
      IMoCTameable mocEntity = (IMoCTameable)event.getEntityLiving();
      if (mocEntity.getIsTamed() && mocEntity.getPetHealth() > 0.0F && !mocEntity.isRiderDisconnecting()) {
        return;
      }

      if (mocEntity.getOwnerPetId() != -1)
      {
        MoCreatures.instance.mapData.removeOwnerPet(mocEntity, mocEntity.getOwnerPetId());
      }
    }
  }



}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\MoCEventHooks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
