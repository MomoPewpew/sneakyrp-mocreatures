package drzhark.mocreatures;

import com.mojang.authlib.GameProfile;
import drzhark.mocreatures.client.MoCCreativeTabs;
import drzhark.mocreatures.client.handlers.MoCKeyHandler;
import drzhark.mocreatures.command.CommandMoCPets;
import drzhark.mocreatures.command.CommandMoCSpawn;
import drzhark.mocreatures.command.CommandMoCTP;
import drzhark.mocreatures.command.CommandMoCreatures;
import drzhark.mocreatures.network.MoCMessageHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = MoCConstants.MOD_ID, name = MoCConstants.MOD_NAME, version = MoCConstants.MOD_VERSION)
// @Mod(modid = "mocreatures", name = "DrZhark's Mo'Creatures", version = "12.0.5", acceptableRemoteVersions = "[12.0.5]", acceptedMinecraftVersions = "[1.12.2]")
public class MoCreatures
{
	@Instance(MoCConstants.MOD_ID)
  public static MoCreatures instance;
  @SidedProxy(clientSide = "drzhark.mocreatures.client.MoCClientProxy", serverSide = "drzhark.mocreatures.MoCProxy")
  public static MoCProxy proxy;
  public static final Logger LOGGER = LogManager.getLogger("mocreatures");
  public static final CreativeTabs tabMoC = (CreativeTabs)new MoCCreativeTabs(CreativeTabs.CREATIVE_TAB_ARRAY.length, "MoCreaturesTab");
  public MoCPetMapData mapData;
  public static boolean isCustomSpawnerLoaded = false;
  public static GameProfile MOCFAKEPLAYER = new GameProfile(UUID.fromString("6E379B45-1111-2222-3333-2FE1A88BCD66"), "[MoCreatures]");

  public static DimensionType WYVERN_LAIR;
  public static int WyvernLairDimensionID;
  public static Map<String, MoCEntityData> mocEntityMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
  public static Map<Class<? extends EntityLiving>, MoCEntityData> entityMap = new HashMap<>();
  public static Map<Integer, Class<? extends EntityLiving>> instaSpawnerMap = new HashMap<>();
  public static final String MOC_LOGO = TextFormatting.WHITE + "[" + TextFormatting.AQUA + "Mo'Creatures" + TextFormatting.WHITE + "]";

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    MoCMessageHandler.init();
    MinecraftForge.EVENT_BUS.register(new MoCEventHooks());
    proxy.ConfigInit(event);
    if (!isServer()) {
      MinecraftForge.EVENT_BUS.register(new MoCKeyHandler());
    }
  }



  @EventHandler
  public void load(FMLInitializationEvent event) {
    proxy.mocSettingsConfig.save();
    proxy.registerRenderers();
    proxy.registerRenderInformation();
  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    isCustomSpawnerLoaded = Loader.isModLoaded("CustomSpawner");
  }

  @EventHandler
  public void serverStarting(FMLServerStartingEvent event) {
    proxy.initGUI();
    event.registerServerCommand(new CommandMoCreatures());
    event.registerServerCommand(new CommandMoCTP());
    event.registerServerCommand(new CommandMoCPets());
    if (isServer() &&
      FMLCommonHandler.instance().getMinecraftServerInstance().isDedicatedServer()) {
      event.registerServerCommand(new CommandMoCSpawn());
    }
  }






  public static void burnPlayer(EntityPlayer player) {}






  public static void freezePlayer(EntityPlayer player) {}





  public static void poisonPlayer(EntityPlayer player) {}





  public static void showCreaturePedia(EntityPlayer player, String s) {}





  public static void showCreaturePedia(String s) {}





  public static void updateSettings() {
    proxy.readGlobalConfigValues();
  }

  public static boolean isServer() {
    return (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\MoCreatures.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
