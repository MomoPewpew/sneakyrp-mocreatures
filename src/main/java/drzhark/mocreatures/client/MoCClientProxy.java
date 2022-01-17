/*      */ package drzhark.mocreatures.client;
/*      */ import de.matthiasmann.twl.Button;
/*      */ import de.matthiasmann.twl.ListBox;
/*      */ import de.matthiasmann.twl.Widget;
/*      */ import de.matthiasmann.twl.model.ButtonModel;
/*      */ import de.matthiasmann.twl.model.SimpleButtonModel;
/*      */ import drzhark.guiapi.GuiAPI;
/*      */ import drzhark.guiapi.GuiApiHelper;
/*      */ import drzhark.guiapi.GuiModScreen;
/*      */ import drzhark.guiapi.ModAction;
/*      */ import drzhark.guiapi.ModSettingScreen;
/*      */ import drzhark.guiapi.setting.Setting;
/*      */ import drzhark.guiapi.setting.SettingBoolean;
/*      */ import drzhark.guiapi.setting.SettingFloat;
/*      */ import drzhark.guiapi.setting.SettingInt;
/*      */ import drzhark.guiapi.widget.WidgetBoolean;
/*      */ import drzhark.guiapi.widget.WidgetClassicTwocolumn;
/*      */ import drzhark.guiapi.widget.WidgetFloat;
/*      */ import drzhark.guiapi.widget.WidgetInt;
/*      */ import drzhark.guiapi.widget.WidgetList;
/*      */ import drzhark.guiapi.widget.WidgetSimplewindow;
/*      */ import drzhark.guiapi.widget.WidgetSinglecolumn;
/*      */ import drzhark.mocreatures.MoCEntityData;
/*      */ import drzhark.mocreatures.MoCProxy;
/*      */ import drzhark.mocreatures.MoCTools;
/*      */ import drzhark.mocreatures.MoCreatures;
/*      */ import drzhark.mocreatures.client.gui.helpers.MoCGUIEntityNamer;
/*      */ import drzhark.mocreatures.client.gui.helpers.MoCSettingBoolean;
/*      */ import drzhark.mocreatures.client.gui.helpers.MoCSettingFloat;
/*      */ import drzhark.mocreatures.client.gui.helpers.MoCSettingInt;
/*      */ import drzhark.mocreatures.client.gui.helpers.MoCSettingList;
/*      */ import drzhark.mocreatures.client.gui.helpers.MoCSettings;
/*      */ import drzhark.mocreatures.client.model.MoCModelAnt;
/*      */ import drzhark.mocreatures.client.model.MoCModelBear;
/*      */ import drzhark.mocreatures.client.model.MoCModelBird;
/*      */ import drzhark.mocreatures.client.model.MoCModelBoar;
/*      */ import drzhark.mocreatures.client.model.MoCModelBunny;
/*      */ import drzhark.mocreatures.client.model.MoCModelButterfly;
/*      */ import drzhark.mocreatures.client.model.MoCModelCrab;
/*      */ import drzhark.mocreatures.client.model.MoCModelCricket;
/*      */ import drzhark.mocreatures.client.model.MoCModelCrocodile;
/*      */ import drzhark.mocreatures.client.model.MoCModelDeer;
/*      */ import drzhark.mocreatures.client.model.MoCModelDolphin;
/*      */ import drzhark.mocreatures.client.model.MoCModelDragonfly;
/*      */ import drzhark.mocreatures.client.model.MoCModelDuck;
/*      */ import drzhark.mocreatures.client.model.MoCModelEgg;
/*      */ import drzhark.mocreatures.client.model.MoCModelElephant;
/*      */ import drzhark.mocreatures.client.model.MoCModelEnt;
/*      */ import drzhark.mocreatures.client.model.MoCModelFirefly;
/*      */ import drzhark.mocreatures.client.model.MoCModelFishy;
/*      */ import drzhark.mocreatures.client.model.MoCModelFox;
/*      */ import drzhark.mocreatures.client.model.MoCModelGoat;
/*      */ import drzhark.mocreatures.client.model.MoCModelGolem;
/*      */ import drzhark.mocreatures.client.model.MoCModelJellyFish;
/*      */ import drzhark.mocreatures.client.model.MoCModelKitty;
/*      */ import drzhark.mocreatures.client.model.MoCModelKittyBed;
/*      */ import drzhark.mocreatures.client.model.MoCModelKittyBed2;
/*      */ import drzhark.mocreatures.client.model.MoCModelKomodo;
/*      */ import drzhark.mocreatures.client.model.MoCModelLitterBox;
/*      */ import drzhark.mocreatures.client.model.MoCModelMaggot;
/*      */ import drzhark.mocreatures.client.model.MoCModelManticore;
/*      */ import drzhark.mocreatures.client.model.MoCModelMediumFish;
/*      */ import drzhark.mocreatures.client.model.MoCModelMiniGolem;
/*      */ import drzhark.mocreatures.client.model.MoCModelMole;
/*      */ import drzhark.mocreatures.client.model.MoCModelMouse;
/*      */ import drzhark.mocreatures.client.model.MoCModelNewBigCat;
/*      */ import drzhark.mocreatures.client.model.MoCModelNewHorse;
/*      */ import drzhark.mocreatures.client.model.MoCModelNewHorseMob;
/*      */ import drzhark.mocreatures.client.model.MoCModelOgre;
/*      */ import drzhark.mocreatures.client.model.MoCModelOstrich;
/*      */ import drzhark.mocreatures.client.model.MoCModelPetScorpion;
/*      */ import drzhark.mocreatures.client.model.MoCModelRaccoon;
/*      */ import drzhark.mocreatures.client.model.MoCModelRat;
/*      */ import drzhark.mocreatures.client.model.MoCModelRay;
/*      */ import drzhark.mocreatures.client.model.MoCModelScorpion;
/*      */ import drzhark.mocreatures.client.model.MoCModelShark;
/*      */ import drzhark.mocreatures.client.model.MoCModelSilverSkeleton;
/*      */ import drzhark.mocreatures.client.model.MoCModelSmallFish;
/*      */ import drzhark.mocreatures.client.model.MoCModelSnake;
/*      */ import drzhark.mocreatures.client.model.MoCModelTurkey;
/*      */ import drzhark.mocreatures.client.model.MoCModelTurtle;
/*      */ import drzhark.mocreatures.client.model.MoCModelWere;
/*      */ import drzhark.mocreatures.client.model.MoCModelWereHuman;
/*      */ import drzhark.mocreatures.client.model.MoCModelWolf;
/*      */ import drzhark.mocreatures.client.model.MoCModelWraith;
/*      */ import drzhark.mocreatures.client.model.MoCModelWyvern;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderBird;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderButterfly;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderCricket;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderCrocodile;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderDolphin;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderEgg;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderFirefly;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderGoat;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderGolem;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderHellRat;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderHorseMob;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderInsect;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderKitty;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderKittyBed;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderLitterBox;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderMoC;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderMouse;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderNewHorse;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderOstrich;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderPetScorpion;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderRat;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderScorpion;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderShark;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderSnake;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderTRock;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderTurtle;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderWWolf;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderWerewolf;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderWraith;
/*      */ import drzhark.mocreatures.client.renderer.texture.MoCTextures;
/*      */ import drzhark.mocreatures.configuration.MoCConfiguration;
/*      */ import drzhark.mocreatures.entity.IMoCEntity;
/*      */ import drzhark.mocreatures.entity.ambient.MoCEntityAnt;
/*      */ import drzhark.mocreatures.entity.ambient.MoCEntityBee;
/*      */ import drzhark.mocreatures.entity.ambient.MoCEntityButterfly;
/*      */ import drzhark.mocreatures.entity.ambient.MoCEntityCrab;
/*      */ import drzhark.mocreatures.entity.ambient.MoCEntityCricket;
/*      */ import drzhark.mocreatures.entity.ambient.MoCEntityDragonfly;
/*      */ import drzhark.mocreatures.entity.ambient.MoCEntityFly;
/*      */ import drzhark.mocreatures.entity.ambient.MoCEntityMaggot;
/*      */ import drzhark.mocreatures.entity.ambient.MoCEntityRoach;
/*      */ import drzhark.mocreatures.entity.ambient.MoCEntitySnail;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityAnchovy;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityAngelFish;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityAngler;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityBass;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityClownFish;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityCod;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityDolphin;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityFishy;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityGoldFish;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityHippoTang;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityJellyFish;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityManderin;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityMantaRay;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityPiranha;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntitySalmon;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityShark;
/*      */ import drzhark.mocreatures.entity.aquatic.MoCEntityStingRay;
/*      */ import drzhark.mocreatures.entity.item.MoCEntityEgg;
/*      */ import drzhark.mocreatures.entity.item.MoCEntityKittyBed;
/*      */ import drzhark.mocreatures.entity.item.MoCEntityLitterBox;
/*      */ import drzhark.mocreatures.entity.item.MoCEntityThrowableRock;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntityCaveOgre;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntityFireOgre;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntityFlameWraith;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntityGolem;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntityGreenOgre;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntityHellRat;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntityHorseMob;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntityManticore;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntityMiniGolem;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntityRat;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntityScorpion;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntitySilverSkeleton;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntityWWolf;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntityWerewolf;
/*      */ import drzhark.mocreatures.entity.monster.MoCEntityWraith;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityBird;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityBlackBear;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityBunny;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityCrocodile;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityDeer;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityDuck;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityElephant;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityEnt;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityFox;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityGoat;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityGrizzlyBear;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityHorse;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityKitty;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityKomodo;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityLeoger;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityLeopard;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityLiard;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityLiger;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityLion;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityLither;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityManticorePet;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityMole;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityMouse;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityOstrich;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityPandaBear;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityPanthard;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityPanther;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityPanthger;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityPetScorpion;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityPolarBear;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityRaccoon;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntitySnake;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityTiger;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityTurkey;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityTurtle;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityWyvern;
/*      */ import drzhark.mocreatures.network.MoCMessageHandler;
/*      */ import drzhark.mocreatures.network.message.MoCMessageInstaSpawn;
/*      */ import drzhark.mocreatures.util.MoCLog;
/*      */ import java.io.File;
/*      */ import java.lang.reflect.Field;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.gui.GuiScreen;
/*      */ import net.minecraft.client.model.ModelBase;
/*      */ import net.minecraft.client.model.ModelBiped;
/*      */ import net.minecraft.client.renderer.entity.Render;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EnumCreatureType;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.text.ITextComponent;
/*      */ import net.minecraft.util.text.TextComponentTranslation;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraftforge.common.MinecraftForge;
/*      */ import net.minecraftforge.fml.client.registry.RenderingRegistry;
/*      */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*      */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*      */ 
/*      */ public class MoCClientProxy extends MoCProxy {
/*  230 */   public static Minecraft mc = Minecraft.getMinecraft();
/*      */   public static MoCClientProxy instance;
/*  232 */   public static MoCTextures mocTextures = new MoCTextures();
/*      */ 
/*      */   
/*      */   public static MoCSettingInt despawnTickRateS;
/*      */ 
/*      */   
/*      */   public static WidgetInt despawnTickRateW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt lightLevelS;
/*      */ 
/*      */   
/*      */   public static WidgetInt lightLevelW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt despawnLightLevelS;
/*      */ 
/*      */   
/*      */   public static WidgetInt despawnLightLevelW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean spawnPiranhaS;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt particleFXS;
/*      */ 
/*      */   
/*      */   public static WidgetInt particleFXW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean animateTexturesB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean animateTexturesW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean displaynameB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean displaynameW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean displayhealthB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean displayhealthW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean displayemoB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean displayemoW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean staticbedB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean staticbedW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean staticlitterB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean staticlitterW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean attackdolphinsB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean attackdolphinsW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean attackhorses;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean attackhorsesW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean attackwolvesB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean attackwolvesW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean destroyitemsB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean destroyitemsW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean killallVillagersB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean killallVillagersW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean killallUseLightLevelB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean killallUseLightLevelW;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean spawnpiranhaW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt pegasusChanceS;
/*      */ 
/*      */   
/*      */   public static WidgetInt pegasusChanceW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt ostrichEggDropChanceS;
/*      */ 
/*      */   
/*      */   public static WidgetInt ostrichEggDropChanceW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt rareItemDropChanceS;
/*      */ 
/*      */   
/*      */   public static WidgetInt rareItemDropChanceW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt wyvernEggDropChanceS;
/*      */ 
/*      */   
/*      */   public static WidgetInt wyvernEggDropChanceW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt motherWyvernEggDropChanceS;
/*      */ 
/*      */   
/*      */   public static WidgetInt motherWyvernEggDropChanceW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean easybreedingB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean easybreedingW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt ogreAttackRangeS;
/*      */ 
/*      */   
/*      */   public static MoCSettingFloat ogreStrengthS;
/*      */ 
/*      */   
/*      */   public static MoCSettingFloat fireOgreStrengthS;
/*      */ 
/*      */   
/*      */   public static MoCSettingFloat caveOgreStrengthS;
/*      */ 
/*      */   
/*      */   public static WidgetFloat ogreStrengthW;
/*      */ 
/*      */   
/*      */   public static WidgetFloat fireOgreStrengthW;
/*      */ 
/*      */   
/*      */   public static WidgetFloat caveOgreStrengthW;
/*      */ 
/*      */   
/*      */   public static WidgetInt ogreAttackRangeW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt caveOgreChanceS;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt fireOgreChanceS;
/*      */ 
/*      */   
/*      */   public static WidgetInt caveOgreChanceW;
/*      */ 
/*      */   
/*      */   public static WidgetInt fireOgreChanceW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean golemDestroyBlocksB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean golemDestroyBlocksW;
/*      */ 
/*      */   
/*      */   public MoCSettings guiapiSettings;
/*      */ 
/*      */   
/*      */   public ModSettingScreen MoCScreen;
/*      */ 
/*      */   
/*      */   public MoCSettingList settingBiomeGroups;
/*      */ 
/*      */   
/*      */   public MoCSettingList biomesList;
/*      */ 
/*      */   
/*      */   public MoCSettingList entityList;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean despawnVanillaB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean despawnVanillaW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean modifyVanillaSpawnsB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean modifyVanillaSpawnsW;
/*      */ 
/*      */   
/*      */   public static MoCSettingBoolean debugB;
/*      */ 
/*      */   
/*      */   public static WidgetBoolean debugW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt mocitemidA;
/*      */ 
/*      */   
/*      */   public static WidgetInt mocitemidW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt eggidA;
/*      */ 
/*      */   
/*      */   public static WidgetInt eggidW;
/*      */ 
/*      */   
/*      */   public static WidgetInt blockDirtIdW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt blockDirtIdS;
/*      */ 
/*      */   
/*      */   public static WidgetInt blockGrassIdW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt blockGrassIdS;
/*      */ 
/*      */   
/*      */   public static WidgetInt blockStoneIdW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt blockStoneIdS;
/*      */ 
/*      */   
/*      */   public static WidgetInt blockLeafIdW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt blockLeafIdS;
/*      */ 
/*      */   
/*      */   public static WidgetInt blockLogIdW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt blockLogIdS;
/*      */ 
/*      */   
/*      */   public static WidgetInt blockTallGrassIdW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt blockTallGrassIdS;
/*      */ 
/*      */   
/*      */   public static WidgetInt blockPlanksIdW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt blockPlanksIdS;
/*      */ 
/*      */   
/*      */   public static WidgetInt wyvernDimensionIdW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt wyvernDimensionIdS;
/*      */ 
/*      */   
/*      */   public static WidgetInt wyvernBiomeIdW;
/*      */ 
/*      */   
/*      */   public static MoCSettingInt wyvernBiomeIdS;
/*      */   
/*      */   private WidgetSimplewindow instaSpawnerWindow;
/*      */   
/*      */   private MoCSettingInt settingNumberToSpawn;
/*      */   
/*      */   public WidgetSimplewindow creatureOptionsWindow;
/*      */   
/*      */   public WidgetSimplewindow creatureSettingsWindow;
/*      */   
/*      */   public WidgetSimplewindow creatureSpawnSettingsWindow;
/*      */   
/*      */   public WidgetSimplewindow waterMobOptionsWindow;
/*      */   
/*      */   public WidgetSimplewindow waterMobSettingsWindow;
/*      */   
/*      */   public WidgetSimplewindow waterMobSpawnSettingsWindow;
/*      */   
/*      */   public WidgetSimplewindow mobOptionsWindow;
/*      */   
/*      */   public WidgetSimplewindow mobSettingsWindow;
/*      */   
/*      */   public WidgetSimplewindow mobSpawnSettingsWindow;
/*      */   
/*      */   public WidgetSimplewindow ambientOptionsWindow;
/*      */   
/*      */   public WidgetSimplewindow ambientSpawnSettingsWindow;
/*      */   
/*      */   public WidgetSimplewindow generalSettingsWindow;
/*      */   
/*      */   public WidgetSimplewindow IDSettingsWindow;
/*      */   
/*      */   public WidgetSimplewindow vanillamobwindow;
/*      */   
/*      */   public WidgetSimplewindow defaultsWindow;
/*      */   
/*      */   private WidgetClassicTwocolumn widgetCreatureSettingsColumns;
/*      */   
/*      */   private WidgetClassicTwocolumn widgetCreatureSpawnSettingsColumns;
/*      */   
/*      */   private WidgetClassicTwocolumn widgetMobSettingsColumns;
/*      */   
/*      */   private WidgetClassicTwocolumn widgetMobSpawnSettingsColumns;
/*      */   
/*      */   private WidgetClassicTwocolumn widgetWaterMobSettingsColumns;
/*      */   
/*      */   private WidgetClassicTwocolumn widgetWaterMobSpawnSettingsColumns;
/*      */   
/*      */   private WidgetClassicTwocolumn widgetAmbientSettingsColumns;
/*      */   
/*      */   private WidgetClassicTwocolumn widgetAmbientSpawnSettingsColumns;
/*      */   
/*      */   private WidgetClassicTwocolumn widgetGeneralSettingsColumns;
/*      */   
/*      */   private WidgetClassicTwocolumn widgetIDSettingsColumns;
/*      */   
/*      */   private WidgetClassicTwocolumn defaultChoices;
/*      */   
/*      */   private WidgetSinglecolumn widgetInstaSpawnerColumn;
/*      */   
/*      */   private WidgetClassicTwocolumn creatureOptions;
/*      */   
/*      */   private WidgetClassicTwocolumn mobOptions;
/*      */   
/*      */   private WidgetClassicTwocolumn waterOptions;
/*      */   
/*      */   private WidgetClassicTwocolumn ambientOptions;
/*      */   
/*      */   private static final String BUTTON_GENERAL_SETTINGS = "General Settings";
/*      */   
/*      */   private static final String BUTTON_ID_SETTINGS = "ID Settings";
/*      */   
/*      */   private static final String BUTTON_CREATURE_GENERAL_SETTINGS = "Creature General Settings";
/*      */   
/*      */   private static final String BUTTON_CREATURE_SPAWN_SETTINGS = "Creature Spawn Settings";
/*      */   
/*      */   private static final String BUTTON_MONSTER_GENERAL_SETTINGS = "Monster General Settings";
/*      */   
/*      */   private static final String BUTTON_MONSTER_SPAWN_SETTINGS = "Monster Spawn Settings";
/*      */   
/*      */   private static final String BUTTON_WATERMOB_GENERAL_SETTINGS = "Water Mob General Settings";
/*      */   
/*      */   private static final String BUTTON_WATERMOB_SPAWN_SETTINGS = "Water Mob Spawn Settings";
/*      */   
/*      */   private static final String BUTTON_AMBIENT_SPAWN_SETTINGS = "Ambient Spawn Settings";
/*      */   
/*      */   private static final String BUTTON_OWNERSHIP_SETTINGS = "Ownership Settings";
/*      */   
/*      */   private static final String BUTTON_DEFAULTS = "Reset to Defaults";
/*      */   
/*      */   private static final String MOC_SCREEN_TITLE = "DrZhark's Mo'Creatures";
/*      */ 
/*      */   
/*      */   public void registerRenderers() {}
/*      */ 
/*      */   
/*      */   public void initTextures() {
/*      */     mocTextures.loadTextures();
/*      */   }
/*      */ 
/*      */   
/*      */   public ResourceLocation getTexture(String texture) {
/*      */     return mocTextures.getTexture(texture);
/*      */   }
/*      */ 
/*      */   
/*      */   public void registerRenderInformation() {
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityBunny.class, (Render)new MoCRenderBunny((ModelBase)new MoCModelBunny(), 0.3F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityBird.class, (Render)new MoCRenderBird((ModelBase)new MoCModelBird(), 0.3F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityTurtle.class, (Render)new MoCRenderTurtle(new MoCModelTurtle(), 0.4F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityMouse.class, (Render)new MoCRenderMouse((ModelBase)new MoCModelMouse(), 0.1F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntitySnake.class, (Render)new MoCRenderSnake((ModelBase)new MoCModelSnake(), 0.0F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityTurkey.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelTurkey(), 0.4F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityButterfly.class, (Render)new MoCRenderButterfly((ModelBase)new MoCModelButterfly()));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityHorse.class, (Render)new MoCRenderNewHorse(new MoCModelNewHorse()));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityHorseMob.class, (Render)new MoCRenderHorseMob(new MoCModelNewHorseMob()));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityBoar.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelBoar(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityBlackBear.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelBear(), 0.7F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityGrizzlyBear.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelBear(), 0.7F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityPandaBear.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelBear(), 0.7F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityPolarBear.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelBear(), 0.7F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityDuck.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelDuck(), 0.3F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityDeer.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelDeer(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityWWolf.class, (Render)new MoCRenderWWolf((ModelBase)new MoCModelWolf(), 0.7F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityWraith.class, (Render)new MoCRenderWraith((ModelBiped)new MoCModelWraith(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityFlameWraith.class, (Render)new MoCRenderWraith((ModelBiped)new MoCModelWraith(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityWerewolf.class, (Render)new MoCRenderWerewolf(new MoCModelWereHuman(), (ModelBase)new MoCModelWere(), 0.7F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityFox.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelFox(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityShark.class, (Render)new MoCRenderShark((ModelBase)new MoCModelShark(), 0.6F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityDolphin.class, (Render)new MoCRenderDolphin((ModelBase)new MoCModelDolphin(), 0.6F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityFishy.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelFishy(), 0.1F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityEgg.class, (Render)new MoCRenderEgg((ModelBase)new MoCModelEgg(), 0.0F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityKitty.class, (Render)new MoCRenderKitty((ModelBase)new MoCModelKitty(0.0F, 15.0F), 0.4F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityKittyBed.class, (Render)new MoCRenderKittyBed(new MoCModelKittyBed(), new MoCModelKittyBed2(), 0.3F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityLitterBox.class, (Render)new MoCRenderLitterBox(new MoCModelLitterBox(), 0.3F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityRat.class, (Render)new MoCRenderRat((ModelBase)new MoCModelRat(), 0.2F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityHellRat.class, (Render)new MoCRenderHellRat((ModelBase)new MoCModelRat(), 0.4F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityScorpion.class, (Render)new MoCRenderScorpion(new MoCModelScorpion(), 0.4F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityCrocodile.class, (Render)new MoCRenderCrocodile(new MoCModelCrocodile(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityMantaRay.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelRay(), 0.4F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityStingRay.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelRay(), 0.4F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityJellyFish.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelJellyFish(), 0.1F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityGoat.class, (Render)new MoCRenderGoat((ModelBase)new MoCModelGoat(), 0.3F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityOstrich.class, (Render)new MoCRenderOstrich((ModelBase)new MoCModelOstrich(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityBee.class, (Render)new MoCRenderInsect((ModelBase)new MoCModelBee()));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityFly.class, (Render)new MoCRenderInsect((ModelBase)new MoCModelFly()));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityDragonfly.class, (Render)new MoCRenderInsect((ModelBase)new MoCModelDragonfly()));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityFirefly.class, (Render)new MoCRenderFirefly((ModelBase)new MoCModelFirefly()));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityCricket.class, (Render)new MoCRenderCricket((ModelBase)new MoCModelCricket()));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntitySnail.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelSnail(), 0.0F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityGolem.class, (Render)new MoCRenderGolem((ModelBase)new MoCModelGolem(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityThrowableRock.class, (Render)new MoCRenderTRock());
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityPetScorpion.class, (Render)new MoCRenderPetScorpion((MoCModelScorpion)new MoCModelPetScorpion(), 0.4F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityElephant.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelElephant(), 0.7F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityKomodo.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelKomodo(), 0.3F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityWyvern.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelWyvern(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityGreenOgre.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelOgre(), 0.6F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityCaveOgre.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelOgre(), 0.6F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityFireOgre.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelOgre(), 0.6F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityRoach.class, (Render)new MoCRenderInsect((ModelBase)new MoCModelRoach()));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityMaggot.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelMaggot(), 0.0F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityCrab.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelCrab(), 0.2F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityRaccoon.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelRaccoon(), 0.4F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityMiniGolem.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelMiniGolem(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntitySilverSkeleton.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelSilverSkeleton(), 0.6F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityAnt.class, (Render)new MoCRenderInsect((ModelBase)new MoCModelAnt()));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityCod.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelMediumFish(), 0.2F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntitySalmon.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelMediumFish(), 0.2F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityBass.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelMediumFish(), 0.2F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityAnchovy.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelSmallFish(), 0.1F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityAngelFish.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelSmallFish(), 0.1F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityAngler.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelSmallFish(), 0.1F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityClownFish.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelSmallFish(), 0.1F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityGoldFish.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelSmallFish(), 0.1F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityHippoTang.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelSmallFish(), 0.1F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityManderin.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelSmallFish(), 0.1F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityPiranha.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelSmallFish(), 0.1F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityEnt.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelEnt(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityMole.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelMole(), 0.0F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityManticore.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelManticore(), 0.7F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityLion.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelNewBigCat(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityTiger.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelNewBigCat(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityPanther.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelNewBigCat(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityLeopard.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelNewBigCat(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityManticorePet.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelNewBigCat(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityLiger.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelNewBigCat(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityLeoger.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelNewBigCat(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityPanthger.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelNewBigCat(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityPanthard.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelNewBigCat(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityLither.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelNewBigCat(), 0.5F));
/*      */     RenderingRegistry.registerEntityRenderingHandler(MoCEntityLiard.class, (Render)new MoCRenderMoC((ModelBase)new MoCModelNewBigCat(), 0.5F));
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityPlayer getPlayer() {
/*      */     return (EntityPlayer)mc.player;
/*      */   }
/*      */ 
/*      */   
/*      */   public MoCClientProxy() {
/*  711 */     this.gui = new GuiAPI(); instance = this;
/*      */   } public void setName(EntityPlayer player, IMoCEntity mocanimal) { mc.displayGuiScreen((GuiScreen)new MoCGUIEntityNamer(mocanimal, mocanimal.getPetName())); } public void UndeadFX(Entity entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0) return;  int i = densityInt / 2 * (entity.world.rand.nextInt(2) + 1); if (i == 0) i = 1;  if (i > 10) i = 10;  for (int x = 0; x < i; x++) { MoCEntityFXUndead FXUndead = new MoCEntityFXUndead(entity.world, entity.posX, entity.posY + (entity.world.rand.nextFloat() * entity.height), entity.posZ); mc.effectRenderer.addEffect(FXUndead); }  }
/*      */   public void StarFX(MoCEntityHorse entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0) return;  if ((entity.getType() >= 50 && entity.getType() < 60) || entity.getType() == 36) { float fRed = entity.colorFX(1, entity.getType()); float fGreen = entity.colorFX(2, entity.getType()); float fBlue = entity.colorFX(3, entity.getType()); int i = densityInt * entity.world.rand.nextInt(2); for (int x = 0; x < i; x++) { MoCEntityFXStar FXStar = new MoCEntityFXStar((World)mc.world, entity.posX, entity.posY + (entity.world.rand.nextFloat() * entity.height), entity.posZ, fRed, fGreen, fBlue); mc.effectRenderer.addEffect(FXStar); }  }  }
/*      */   public void LavaFX(Entity entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0) return;  double var2 = entity.world.rand.nextGaussian() * 0.02D; double var4 = entity.world.rand.nextGaussian() * 0.02D; double var6 = entity.world.rand.nextGaussian() * 0.02D; mc.world.spawnParticle(EnumParticleTypes.LAVA, entity.posX + (entity.world.rand.nextFloat() * entity.width) - entity.width, entity.posY + 0.5D + (entity.world.rand.nextFloat() * entity.height), entity.posZ + (entity.world.rand.nextFloat() * entity.width) - entity.width, var2, var4, var6, new int[0]); }
/*  715 */   public void ConfigInit(FMLPreInitializationEvent event) { super.ConfigInit(event);
/*      */     try {
/*  717 */       Field[] fields = GuiScreen.class.getDeclaredFields();
/*  718 */       for (int i = 0; i < fields.length; i++) {
/*  719 */         if (fields[i].getType() == List.class) {
/*  720 */           this.gui.controlListField = fields[i];
/*  721 */           this.gui.controlListField.setAccessible(true);
/*      */           break;
/*      */         } 
/*      */       } 
/*  725 */       if (this.gui.controlListField == null) {
/*  726 */         throw new Exception("No fields found on GuiScreen (" + GuiScreen.class.getSimpleName() + ") of type List! This should never happen!");
/*      */       }
/*  728 */     } catch (Throwable e) {
/*  729 */       throw new RuntimeException("Unable to get Field reference for GuiScreen.controlList!", e);
/*      */     } 
/*  731 */     MinecraftForge.EVENT_BUS.register(this.gui); }
/*      */   public void VanishFX(MoCEntityHorse entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
/*      */       return;  for (int var6 = 0; var6 < densityInt * 8; var6++) { double newPosX = ((float)entity.posX + entity.world.rand.nextFloat()); double newPosY = 0.7D + ((float)entity.posY + entity.world.rand.nextFloat()); double newPosZ = ((float)entity.posZ + entity.world.rand.nextFloat()); int var19 = entity.world.rand.nextInt(2) * 2 - 1; double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D; double speedX = (entity.world.rand.nextFloat() * 2.0F * var19); double speedZ = (entity.world.rand.nextFloat() * 2.0F * var19); MoCEntityFXVanish FXVanish = new MoCEntityFXVanish(entity.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, entity.colorFX(1, entity.getType()), entity.colorFX(2, entity.getType()), entity.colorFX(3, entity.getType()), false); mc.effectRenderer.addEffect(FXVanish); }
/*      */      }
/*      */   public void MaterializeFX(MoCEntityHorse entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
/*      */       return;  for (int var6 = 0; var6 < densityInt * 50; var6++) { double newPosX = ((float)entity.posX + entity.world.rand.nextFloat()); double newPosY = 0.7D + ((float)entity.posY + entity.world.rand.nextFloat()); double newPosZ = ((float)entity.posZ + entity.world.rand.nextFloat()); int var19 = entity.world.rand.nextInt(2) * 2 - 1; double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D; double speedX = (entity.world.rand.nextFloat() * 2.0F * var19); double speedZ = (entity.world.rand.nextFloat() * 2.0F * var19); MoCEntityFXVanish FXVanish = new MoCEntityFXVanish((World)mc.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, entity.colorFX(1, entity.getType()), entity.colorFX(2, entity.getType()), entity.colorFX(3, entity.getType()), true); mc.effectRenderer.addEffect(FXVanish); }
/*  737 */      } public void initGUI() { MoCLog.logger.info("Initializing MoCreatures GUI API");
/*      */     
/*  739 */     this.guiapiSettings = new MoCSettings("MoCreatures");
/*  740 */     this.MoCScreen = new ModSettingScreen("DrZhark's Mo'Creatures");
/*      */ 
/*      */     
/*  743 */     ModAction initCreatureWindow = new ModAction(this, "showCreatureOptions", new Class[0]);
/*  744 */     ModAction initMobWindow = new ModAction(this, "showMobOptions", new Class[0]);
/*  745 */     ModAction initWaterMobWindow = new ModAction(this, "showWaterMobOptions", new Class[0]);
/*  746 */     ModAction initAmbientWindow = new ModAction(this, "showAmbientOptions", new Class[0]);
/*  747 */     ModAction initGeneralSettingsWindow = new ModAction(this, "showGeneralSettings", new Class[0]);
/*  748 */     ModAction initIDSettingsWindow = new ModAction(this, "showIDSettings", new Class[0]);
/*  749 */     ModAction initDefaultsWindow = new ModAction(this, "showDefaults", new Class[0]);
/*  750 */     ModAction showInstaSpawner = new ModAction(this, "showInstaSpawner", new Class[0]);
/*  751 */     this.MoCScreen.append((Widget)GuiApiHelper.makeButton("General Settings", initGeneralSettingsWindow, Boolean.valueOf(true)));
/*  752 */     this.MoCScreen.append((Widget)GuiApiHelper.makeButton("ID Settings", initIDSettingsWindow, Boolean.valueOf(true)));
/*  753 */     this.MoCScreen.append((Widget)GuiApiHelper.makeButton("Insta-Spawner", showInstaSpawner, Boolean.valueOf(true)));
/*  754 */     this.MoCScreen.append((Widget)GuiApiHelper.makeButton("Creatures", initCreatureWindow, Boolean.valueOf(true)));
/*  755 */     this.MoCScreen.append((Widget)GuiApiHelper.makeButton("Mobs", initMobWindow, Boolean.valueOf(true)));
/*  756 */     this.MoCScreen.append((Widget)GuiApiHelper.makeButton("Water Mobs", initWaterMobWindow, Boolean.valueOf(true)));
/*  757 */     this.MoCScreen.append((Widget)GuiApiHelper.makeButton("Ambient", initAmbientWindow, Boolean.valueOf(true)));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  762 */     this.creatureOptions = new WidgetClassicTwocolumn(new Widget[0]);
/*      */     
/*  764 */     if (!MoCreatures.isCustomSpawnerLoaded) {
/*  765 */       this.creatureOptions.add((Widget)GuiApiHelper.makeButton("Spawn Settings", new ModAction(this, "showCreatureSpawnSettings", new Class[0]), Boolean.valueOf(true)));
/*      */     }
/*  767 */     this.creatureOptions.add((Widget)GuiApiHelper.makeButton("General Settings", new ModAction(this, "showCreatureSettings", new Class[0]), Boolean.valueOf(true)));
/*      */ 
/*      */ 
/*      */     
/*  771 */     this.mobOptions = new WidgetClassicTwocolumn(new Widget[0]);
/*  772 */     if (!MoCreatures.isCustomSpawnerLoaded) {
/*  773 */       this.mobOptions.add((Widget)GuiApiHelper.makeButton("Spawn Settings", new ModAction(this, "showMobSpawnSettings", new Class[0]), Boolean.valueOf(true)));
/*      */     }
/*  775 */     this.mobOptions.add((Widget)GuiApiHelper.makeButton("General Settings", new ModAction(this, "showMobSettings", new Class[0]), Boolean.valueOf(true)));
/*      */ 
/*      */ 
/*      */     
/*  779 */     this.waterOptions = new WidgetClassicTwocolumn(new Widget[0]);
/*  780 */     if (!MoCreatures.isCustomSpawnerLoaded) {
/*  781 */       this.waterOptions.add((Widget)GuiApiHelper.makeButton("Spawn Settings", new ModAction(this, "showWaterSpawnSettings", new Class[0]), Boolean.valueOf(true)));
/*      */     }
/*  783 */     this.waterOptions.add((Widget)GuiApiHelper.makeButton("General Settings", new ModAction(this, "showWaterSettings", new Class[0]), Boolean.valueOf(true)));
/*      */ 
/*      */ 
/*      */     
/*  787 */     this.ambientOptions = new WidgetClassicTwocolumn(new Widget[0]);
/*  788 */     if (!MoCreatures.isCustomSpawnerLoaded) {
/*  789 */       this.ambientOptions.add((Widget)GuiApiHelper.makeButton("Spawn Settings", new ModAction(this, "showAmbientSpawnSettings", new Class[0]), Boolean.valueOf(true)));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  802 */     this.guiapiSettings.load(); }
/*      */   public void VacuumFX(MoCEntityGolem entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
/*      */       return;  for (int var1 = 0; var1 < 2; var1++) { double newPosX = entity.posX - 1.5D * Math.cos((MoCTools.realAngle(entity.rotationYaw - 90.0F) / 57.29578F)); double newPosZ = entity.posZ - 1.5D * Math.sin((MoCTools.realAngle(entity.rotationYaw - 90.0F) / 57.29578F)); double newPosY = entity.posY + entity.height - 0.8D - entity.getAdjustedYOffset() * 1.8D; double speedX = (entity.world.rand.nextDouble() - 0.5D) * 4.0D; double speedY = -entity.world.rand.nextDouble(); double speedZ = (entity.world.rand.nextDouble() - 0.5D) * 4.0D; MoCEntityFXVacuum FXVacuum = new MoCEntityFXVacuum((World)mc.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, entity.colorFX(1), entity.colorFX(2), entity.colorFX(3), 146); mc.effectRenderer.addEffect(FXVacuum); }  }
/*      */   public void hammerFX(EntityPlayer entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
/*      */       return;  for (int var6 = 0; var6 < densityInt * 10; var6++) { double newPosX = ((float)entity.posX + entity.world.rand.nextFloat()); double newPosY = 0.3D + ((float)entity.posY + entity.world.rand.nextFloat()); double newPosZ = ((float)entity.posZ + entity.world.rand.nextFloat()); int var19 = entity.world.rand.nextInt(2) * 2 - 1; double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D; double speedX = (entity.world.rand.nextFloat() * 2.0F * var19); double d1 = (entity.world.rand.nextFloat() * 2.0F * var19); }  }
/*      */   public void teleportFX(EntityPlayer entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
/*      */       return;  for (int var6 = 0; var6 < densityInt * 50; var6++) { double newPosX = ((float)entity.posX + entity.world.rand.nextFloat()); double newPosY = 0.7D + ((float)entity.posY + entity.world.rand.nextFloat()); double newPosZ = ((float)entity.posZ + entity.world.rand.nextFloat()); int var19 = entity.world.rand.nextInt(2) * 2 - 1; double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D; double speedX = (entity.world.rand.nextFloat() * 2.0F * var19); double speedZ = (entity.world.rand.nextFloat() * 2.0F * var19); MoCEntityFXVanish hammerFX = new MoCEntityFXVanish((World)mc.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, 0.73828125F, 0.4296875F, 0.89453125F, true); mc.effectRenderer.addEffect(hammerFX); }  }
/*  809 */   public int getProxyMode() { return 2; } public static final List<String> entityTypes = Arrays.asList(new String[] { "CREATURE", "MONSTER", "WATERCREATURE", "AMBIENT" }); public MoCEntityData currentSelectedEntity; public GuiAPI gui; public void showCreatureOptions() { if (this.creatureOptionsWindow == null) {
/*  810 */       this.creatureOptionsWindow = new WidgetSimplewindow((Widget)this.creatureOptions, "Creature Options");
/*      */     }
/*      */     
/*  813 */     GuiModScreen.show((Widget)this.creatureOptionsWindow); }
/*      */ 
/*      */   
/*      */   public void showCreatureSettings() {
/*  817 */     if (this.creatureSettingsWindow == null) {
/*  818 */       this.widgetCreatureSettingsColumns = new WidgetClassicTwocolumn(new Widget[0]);
/*  819 */       this.guiapiSettings.append(
/*  820 */           (Setting)(easybreedingB = new MoCSettingBoolean(this.mocSettingsConfig, "creature-general-settings", "EasyBreeding", Boolean.valueOf(this.easyBreeding))));
/*  821 */       easybreedingW = new WidgetBoolean((SettingBoolean)easybreedingB, "Easy Horse Breed", "Yes", "No");
/*  822 */       this.widgetCreatureSettingsColumns.add((Widget)easybreedingW);
/*  823 */       this.guiapiSettings.append((Setting)(pegasusChanceS = new MoCSettingInt(this.mocSettingsConfig, "creature-general-settings", "ZebraChance", this.zebraChance, 1, 1, 10)));
/*      */       
/*  825 */       pegasusChanceW = new WidgetInt((SettingInt)pegasusChanceS, "Zebra chance");
/*  826 */       this.widgetCreatureSettingsColumns.add((Widget)pegasusChanceW);
/*  827 */       this.guiapiSettings.append((Setting)(ostrichEggDropChanceS = new MoCSettingInt(this.mocSettingsConfig, "creature-general-settings", "OstrichEggDropChance", this.ostrichEggDropChance, 1, 1, 3)));
/*      */ 
/*      */       
/*  830 */       ostrichEggDropChanceW = new WidgetInt((SettingInt)ostrichEggDropChanceS, "Ostrich Egg Drop Chance");
/*  831 */       this.widgetCreatureSettingsColumns.add((Widget)ostrichEggDropChanceW);
/*  832 */       this.guiapiSettings.append((Setting)(rareItemDropChanceS = new MoCSettingInt(this.mocSettingsConfig, "creature-general-settings", "RareItemDropChance", this.rareItemDropChance, 1, 1, 25)));
/*      */ 
/*      */       
/*  835 */       rareItemDropChanceW = new WidgetInt((SettingInt)rareItemDropChanceS, "Rare Item Drop Chance");
/*  836 */       this.widgetCreatureSettingsColumns.add((Widget)rareItemDropChanceW);
/*  837 */       this.guiapiSettings.append((Setting)(wyvernEggDropChanceS = new MoCSettingInt(this.mocSettingsConfig, "creature-general-settings", "WyvernEggDropChance", this.wyvernEggDropChance, 1, 1, 10)));
/*      */ 
/*      */       
/*  840 */       wyvernEggDropChanceW = new WidgetInt((SettingInt)wyvernEggDropChanceS, "Wyvern Egg Drop Chance");
/*  841 */       this.widgetCreatureSettingsColumns.add((Widget)wyvernEggDropChanceW);
/*  842 */       this.guiapiSettings.append((Setting)(motherWyvernEggDropChanceS = new MoCSettingInt(this.mocSettingsConfig, "creature-general-settings", "MotherWyvernEggDropChance", this.motherWyvernEggDropChance, 1, 1, 33)));
/*      */ 
/*      */       
/*  845 */       motherWyvernEggDropChanceW = new WidgetInt((SettingInt)motherWyvernEggDropChanceS, "M. Wyvern Egg Drop Chance");
/*  846 */       this.widgetCreatureSettingsColumns.add((Widget)motherWyvernEggDropChanceW);
/*  847 */       this.guiapiSettings.append(
/*  848 */           (Setting)(attackhorses = new MoCSettingBoolean(this.mocSettingsConfig, "creature-general-settings", "AttackHorses", Boolean.valueOf(this.attackHorses))));
/*  849 */       attackhorsesW = new WidgetBoolean((SettingBoolean)attackhorses, "Target horses?", "Yes", "No");
/*  850 */       this.widgetCreatureSettingsColumns.add((Widget)attackhorsesW);
/*  851 */       this.guiapiSettings.append(
/*  852 */           (Setting)(attackwolvesB = new MoCSettingBoolean(this.mocSettingsConfig, "creature-general-settings", "AttackWolves", Boolean.valueOf(this.attackWolves))));
/*  853 */       attackwolvesW = new WidgetBoolean((SettingBoolean)attackwolvesB, "Target dogs?", "Yes", "No");
/*  854 */       this.widgetCreatureSettingsColumns.add((Widget)attackwolvesW);
/*  855 */       this.guiapiSettings.append(
/*  856 */           (Setting)(destroyitemsB = new MoCSettingBoolean(this.mocSettingsConfig, "creature-general-settings", "DestroyDrops", Boolean.valueOf(this.destroyDrops))));
/*  857 */       destroyitemsW = new WidgetBoolean((SettingBoolean)destroyitemsB, "Destroy drops?", "Yes", "No");
/*  858 */       this.widgetCreatureSettingsColumns.add((Widget)destroyitemsW);
/*  859 */       this.guiapiSettings.append(
/*  860 */           (Setting)(killallVillagersB = new MoCSettingBoolean(this.mocSettingsConfig, "creature-general-settings", "KillAllVillagers", Boolean.valueOf(this.killallVillagers))));
/*  861 */       killallVillagersW = new WidgetBoolean((SettingBoolean)killallVillagersB, "Killall Villagers?", "Yes", "No");
/*  862 */       this.widgetCreatureSettingsColumns.add((Widget)killallVillagersW);
/*  863 */       this.creatureSettingsWindow = new WidgetSimplewindow((Widget)this.widgetCreatureSettingsColumns, "Creature General Settings");
/*      */     } 
/*  865 */     GuiModScreen.show((Widget)this.creatureSettingsWindow);
/*      */   }
/*      */   
/*      */   public void showCreatureSpawnSettings() {
/*  869 */     if (this.creatureSpawnSettingsWindow == null) {
/*  870 */       this.widgetCreatureSpawnSettingsColumns = new WidgetClassicTwocolumn(new Widget[0]);
/*      */       
/*  872 */       for (Map.Entry<String, MoCEntityData> entityEntry : (Iterable<Map.Entry<String, MoCEntityData>>)MoCreatures.mocEntityMap.entrySet()) {
/*  873 */         if (((MoCEntityData)entityEntry.getValue()).getType() == EnumCreatureType.CREATURE) {
/*  874 */           this.widgetCreatureSpawnSettingsColumns.add((Widget)GuiApiHelper.makeButton(entityEntry.getKey(), (new ModAction(this, "showEntitySettings", new Class[] { MoCEntityData.class
/*  875 */                   })).setDefaultArguments(new Object[] { entityEntry.getValue() }), Boolean.valueOf(true)));
/*      */         }
/*      */       } 
/*  878 */       this.creatureSpawnSettingsWindow = new WidgetSimplewindow((Widget)this.widgetCreatureSpawnSettingsColumns, "Creature Spawn Settings");
/*      */     } 
/*  880 */     GuiModScreen.show((Widget)this.creatureSpawnSettingsWindow);
/*      */   }
/*      */ 
/*      */   
/*      */   public void showMobOptions() {
/*  885 */     if (this.mobOptionsWindow == null) {
/*  886 */       this.mobOptionsWindow = new WidgetSimplewindow((Widget)this.mobOptions, "Mob Options");
/*      */     }
/*      */     
/*  889 */     GuiModScreen.show((Widget)this.mobOptionsWindow);
/*      */   }
/*      */   
/*      */   public void showMobSpawnSettings() {
/*  893 */     if (this.mobSpawnSettingsWindow == null) {
/*  894 */       this.widgetMobSpawnSettingsColumns = new WidgetClassicTwocolumn(new Widget[0]);
/*      */       
/*  896 */       for (Map.Entry<String, MoCEntityData> entityEntry : (Iterable<Map.Entry<String, MoCEntityData>>)MoCreatures.mocEntityMap.entrySet()) {
/*  897 */         if (((MoCEntityData)entityEntry.getValue()).getType() == EnumCreatureType.MONSTER) {
/*  898 */           this.widgetMobSpawnSettingsColumns.add((Widget)GuiApiHelper.makeButton(entityEntry.getKey(), (new ModAction(this, "showEntitySettings", new Class[] { MoCEntityData.class
/*  899 */                   })).setDefaultArguments(new Object[] { entityEntry.getValue() }), Boolean.valueOf(true)));
/*      */         }
/*      */       } 
/*  902 */       this.mobSpawnSettingsWindow = new WidgetSimplewindow((Widget)this.widgetMobSpawnSettingsColumns, "Monster Spawn Settings");
/*      */     } 
/*      */     
/*  905 */     GuiModScreen.show((Widget)this.mobSpawnSettingsWindow);
/*      */   }
/*      */   
/*      */   public void showMobSettings() {
/*  909 */     if (this.mobSettingsWindow == null) {
/*  910 */       this.widgetMobSettingsColumns = new WidgetClassicTwocolumn(new Widget[0]);
/*  911 */       this.guiapiSettings.append((Setting)(ogreStrengthS = new MoCSettingFloat(this.mocSettingsConfig, "monster-general-settings", "OgreStrength", this.ogreStrength, 0.1F, 0.1F, 5.0F)));
/*      */ 
/*      */       
/*  914 */       ogreStrengthW = new WidgetFloat((SettingFloat)ogreStrengthS, "Ogre Strength");
/*  915 */       this.widgetMobSettingsColumns.add((Widget)ogreStrengthW);
/*  916 */       this.guiapiSettings.append((Setting)(fireOgreStrengthS = new MoCSettingFloat(this.mocSettingsConfig, "monster-general-settings", "FireOgreStrength", this.fireOgreStrength, 0.1F, 0.1F, 5.0F)));
/*      */ 
/*      */       
/*  919 */       fireOgreStrengthW = new WidgetFloat((SettingFloat)fireOgreStrengthS, "Fire O. Strength");
/*  920 */       this.widgetMobSettingsColumns.add((Widget)fireOgreStrengthW);
/*  921 */       this.guiapiSettings.append((Setting)(caveOgreStrengthS = new MoCSettingFloat(this.mocSettingsConfig, "monster-general-settings", "CaveOgreStrength", this.caveOgreStrength, 0.1F, 0.1F, 5.0F)));
/*      */ 
/*      */       
/*  924 */       caveOgreStrengthW = new WidgetFloat((SettingFloat)caveOgreStrengthS, "Cave O. Strength");
/*  925 */       this.widgetMobSettingsColumns.add((Widget)caveOgreStrengthW);
/*  926 */       this.guiapiSettings.append((Setting)(ogreAttackRangeS = new MoCSettingInt(this.mocSettingsConfig, "monster-general-settings", "OgreAttackRange", this.ogreAttackRange, 1, 1, 24)));
/*      */ 
/*      */       
/*  929 */       ogreAttackRangeW = new WidgetInt((SettingInt)ogreAttackRangeS, "Ogre Attack Range");
/*  930 */       this.widgetMobSettingsColumns.add((Widget)ogreAttackRangeW);
/*  931 */       this.guiapiSettings
/*  932 */         .append((Setting)(caveOgreChanceS = new MoCSettingInt(this.mocSettingsConfig, "monster-general-settings", "caveOgreChance", this.caveOgreChance, 0, 1, 100)));
/*      */ 
/*      */       
/*  935 */       caveOgreChanceW = new WidgetInt((SettingInt)caveOgreChanceS, "Cave Ogre Chance");
/*  936 */       this.widgetMobSettingsColumns.add((Widget)caveOgreChanceW);
/*  937 */       this.guiapiSettings
/*  938 */         .append((Setting)(fireOgreChanceS = new MoCSettingInt(this.mocSettingsConfig, "monster-general-settings", "fireOgreChance", this.fireOgreChance, 0, 1, 100)));
/*      */ 
/*      */       
/*  941 */       fireOgreChanceW = new WidgetInt((SettingInt)fireOgreChanceS, "Fire Ogre Chance");
/*  942 */       this.widgetMobSettingsColumns.add((Widget)fireOgreChanceW);
/*  943 */       this.guiapiSettings.append(
/*      */           
/*  945 */           (Setting)(golemDestroyBlocksB = new MoCSettingBoolean(this.mocSettingsConfig, "monster-general-settings", "golemDestroyBlocks", Boolean.valueOf(this.golemDestroyBlocks))));
/*  946 */       golemDestroyBlocksW = new WidgetBoolean((SettingBoolean)golemDestroyBlocksB, "Golem Destroy Blocks?");
/*  947 */       this.widgetMobSettingsColumns.add((Widget)golemDestroyBlocksW);
/*  948 */       this.mobSettingsWindow = new WidgetSimplewindow((Widget)this.widgetMobSettingsColumns, "Monster General Settings");
/*      */     } 
/*      */     
/*  951 */     GuiModScreen.show((Widget)this.mobSettingsWindow);
/*      */   }
/*      */ 
/*      */   
/*      */   public void showWaterMobOptions() {
/*  956 */     if (this.waterMobOptionsWindow == null) {
/*  957 */       this.waterMobOptionsWindow = new WidgetSimplewindow((Widget)this.waterOptions, "Water Mob Options");
/*      */     }
/*      */     
/*  960 */     GuiModScreen.show((Widget)this.waterMobOptionsWindow);
/*      */   }
/*      */   
/*      */   public void showWaterSpawnSettings() {
/*  964 */     if (this.waterMobSpawnSettingsWindow == null) {
/*  965 */       this.widgetWaterMobSpawnSettingsColumns = new WidgetClassicTwocolumn(new Widget[0]);
/*      */       
/*  967 */       for (Map.Entry<String, MoCEntityData> entityEntry : (Iterable<Map.Entry<String, MoCEntityData>>)MoCreatures.mocEntityMap.entrySet()) {
/*  968 */         if (((MoCEntityData)entityEntry.getValue()).getType() == EnumCreatureType.WATER_CREATURE) {
/*  969 */           this.widgetWaterMobSpawnSettingsColumns.add((Widget)GuiApiHelper.makeButton(entityEntry.getKey(), (new ModAction(this, "showEntitySettings", new Class[] { MoCEntityData.class
/*  970 */                   })).setDefaultArguments(new Object[] { entityEntry.getValue() }), Boolean.valueOf(true)));
/*      */         }
/*      */       } 
/*  973 */       this.waterMobSpawnSettingsWindow = new WidgetSimplewindow((Widget)this.widgetWaterMobSpawnSettingsColumns, "Water Mob Spawn Settings");
/*      */     } 
/*      */     
/*  976 */     GuiModScreen.show((Widget)this.waterMobSpawnSettingsWindow);
/*      */   }
/*      */   
/*      */   public void showWaterSettings() {
/*  980 */     if (this.waterMobSettingsWindow == null) {
/*  981 */       this.widgetWaterMobSettingsColumns = new WidgetClassicTwocolumn(new Widget[0]);
/*  982 */       this.guiapiSettings
/*  983 */         .append(
/*      */           
/*  985 */           (Setting)(attackdolphinsB = new MoCSettingBoolean(this.mocSettingsConfig, "water-mob-general-settings", "AttackDolphins", Boolean.valueOf(this.attackDolphins))));
/*  986 */       attackdolphinsW = new WidgetBoolean((SettingBoolean)attackdolphinsB, "Aggresive Dolphins?", "Yes", "No");
/*  987 */       this.widgetWaterMobSettingsColumns.add((Widget)attackdolphinsW);
/*  988 */       this.waterMobSettingsWindow = new WidgetSimplewindow((Widget)this.widgetWaterMobSettingsColumns, "Water Mob General Settings");
/*      */     } 
/*      */     
/*  991 */     GuiModScreen.show((Widget)this.waterMobSettingsWindow);
/*      */   }
/*      */ 
/*      */   
/*      */   public void showAmbientOptions() {
/*  996 */     if (this.ambientOptionsWindow == null) {
/*  997 */       this.ambientOptionsWindow = new WidgetSimplewindow((Widget)this.ambientOptions, "Ambient Options");
/*      */     }
/*      */     
/* 1000 */     GuiModScreen.show((Widget)this.ambientOptionsWindow);
/*      */   }
/*      */   
/*      */   public void showAmbientSpawnSettings() {
/* 1004 */     if (this.ambientSpawnSettingsWindow == null) {
/* 1005 */       this.widgetAmbientSpawnSettingsColumns = new WidgetClassicTwocolumn(new Widget[0]);
/*      */       
/* 1007 */       for (Map.Entry<String, MoCEntityData> entityEntry : (Iterable<Map.Entry<String, MoCEntityData>>)MoCreatures.mocEntityMap.entrySet()) {
/* 1008 */         if (((MoCEntityData)entityEntry.getValue()).getType() == EnumCreatureType.AMBIENT) {
/* 1009 */           this.widgetAmbientSpawnSettingsColumns.add((Widget)GuiApiHelper.makeButton(entityEntry.getKey(), (new ModAction(this, "showEntitySettings", new Class[] { MoCEntityData.class
/* 1010 */                   })).setDefaultArguments(new Object[] { entityEntry.getValue() }), Boolean.valueOf(true)));
/*      */         }
/*      */       } 
/* 1013 */       this.ambientSpawnSettingsWindow = new WidgetSimplewindow((Widget)this.widgetAmbientSpawnSettingsColumns, "Ambient Spawn Settings");
/*      */     } 
/* 1015 */     GuiModScreen.show((Widget)this.ambientSpawnSettingsWindow);
/*      */   }
/*      */   
/*      */   public void showGeneralSpawnerOptions() {
/* 1019 */     if (this.instaSpawnerWindow == null) {
/* 1020 */       this.instaSpawnerWindow = new WidgetSimplewindow((Widget)this.widgetInstaSpawnerColumn, "Select the number and creature to spawn");
/*      */     }
/* 1022 */     GuiModScreen.show((Widget)this.instaSpawnerWindow);
/*      */   }
/*      */   
/*      */   public void showGeneralSettings() {
/* 1026 */     if (this.generalSettingsWindow == null) {
/* 1027 */       this.widgetGeneralSettingsColumns = new WidgetClassicTwocolumn(new Widget[0]);
/* 1028 */       this.guiapiSettings.append((Setting)(debugB = new MoCSettingBoolean(this.mocSettingsConfig, "global-settings", "debug", Boolean.valueOf(this.debug))));
/* 1029 */       debugW = new WidgetBoolean((SettingBoolean)debugB, "Show Debug Logging?", "Yes", "No");
/* 1030 */       this.widgetGeneralSettingsColumns.add((Widget)debugW);
/* 1031 */       this.guiapiSettings.append(
/* 1032 */           (Setting)(displaynameB = new MoCSettingBoolean(this.mocSettingsConfig, "global-settings", "displayPetName", Boolean.valueOf(this.displayPetName))));
/* 1033 */       displaynameW = new WidgetBoolean((SettingBoolean)displaynameB, "Show Pet Name?", "Yes", "No");
/* 1034 */       this.widgetGeneralSettingsColumns.add((Widget)displaynameW);
/* 1035 */       this.guiapiSettings.append(
/* 1036 */           (Setting)(displayhealthB = new MoCSettingBoolean(this.mocSettingsConfig, "global-settings", "displayPetHealth", Boolean.valueOf(this.displayPetHealth))));
/* 1037 */       displayhealthW = new WidgetBoolean((SettingBoolean)displayhealthB, "Show Pet Health?", "Yes", "No");
/* 1038 */       this.widgetGeneralSettingsColumns.add((Widget)displayhealthW);
/* 1039 */       this.guiapiSettings.append(
/* 1040 */           (Setting)(displayemoB = new MoCSettingBoolean(this.mocSettingsConfig, "global-settings", "displayPetIcons", Boolean.valueOf(this.displayPetIcons))));
/* 1041 */       displayemoW = new WidgetBoolean((SettingBoolean)displayemoB, "Show Pet Icons?", "Yes", "No");
/* 1042 */       this.widgetGeneralSettingsColumns.add((Widget)displayemoW);
/* 1043 */       this.guiapiSettings.append(
/* 1044 */           (Setting)(staticbedB = new MoCSettingBoolean(this.mocSettingsConfig, "creature-general-settings", "StaticBed", Boolean.valueOf(this.staticBed))));
/* 1045 */       staticbedW = new WidgetBoolean((SettingBoolean)staticbedB, "Static K.Bed?", "Yes", "No");
/* 1046 */       this.widgetGeneralSettingsColumns.add((Widget)staticbedW);
/* 1047 */       this.guiapiSettings.append(
/* 1048 */           (Setting)(staticlitterB = new MoCSettingBoolean(this.mocSettingsConfig, "creature-general-settings", "StaticLitter", Boolean.valueOf(this.staticLitter))));
/* 1049 */       staticlitterW = new WidgetBoolean((SettingBoolean)staticlitterB, "Static Litter?", "Yes", "No");
/* 1050 */       this.widgetGeneralSettingsColumns.add((Widget)staticlitterW);
/* 1051 */       this.guiapiSettings.append((Setting)(particleFXS = new MoCSettingInt(this.mocSettingsConfig, "global-settings", "particleFX", this.particleFX, 0, 1, 10)));
/*      */       
/* 1053 */       particleFXW = new WidgetInt((SettingInt)particleFXS, "FX Particle density");
/* 1054 */       this.widgetGeneralSettingsColumns.add((Widget)particleFXW);
/* 1055 */       this.guiapiSettings.append(
/* 1056 */           (Setting)(animateTexturesB = new MoCSettingBoolean(this.mocSettingsConfig, "global-settings", "AnimateTextures", Boolean.valueOf(this.animateTextures))));
/* 1057 */       animateTexturesW = new WidgetBoolean((SettingBoolean)animateTexturesB, "Animate Textures", "Yes", "No");
/* 1058 */       this.widgetGeneralSettingsColumns.add((Widget)animateTexturesW);
/* 1059 */       this.generalSettingsWindow = new WidgetSimplewindow((Widget)this.widgetGeneralSettingsColumns, "General Settings");
/*      */     } 
/*      */     
/* 1062 */     GuiModScreen.show((Widget)this.generalSettingsWindow);
/*      */   }
/*      */   
/*      */   public void showIDSettings() {
/* 1066 */     if (this.IDSettingsWindow == null) {
/* 1067 */       this.widgetIDSettingsColumns = new WidgetClassicTwocolumn(new Widget[0]);
/* 1068 */       this.guiapiSettings.append((Setting)(mocitemidA = new MoCSettingInt(this.mocSettingsConfig, "custom-id-settings", "ItemID", this.itemID, 4096, 1, 60000)));
/*      */       
/* 1070 */       mocitemidW = new WidgetInt((SettingInt)mocitemidA, "Item ID");
/* 1071 */       this.widgetIDSettingsColumns.add((Widget)mocitemidW);
/* 1072 */       this.guiapiSettings.append((Setting)(blockDirtIdS = new MoCSettingInt(this.mocSettingsConfig, "custom-id-settings", "DirtBlockID", this.blockDirtID, 1, 1, 255)));
/*      */       
/* 1074 */       blockDirtIdW = new WidgetInt((SettingInt)blockDirtIdS, "DirtBlock ID");
/* 1075 */       this.widgetIDSettingsColumns.add((Widget)blockDirtIdW);
/* 1076 */       this.guiapiSettings.append((Setting)(blockGrassIdS = new MoCSettingInt(this.mocSettingsConfig, "custom-id-settings", "GrassBlockID", this.blockGrassID, 1, 1, 255)));
/*      */       
/* 1078 */       blockGrassIdW = new WidgetInt((SettingInt)blockGrassIdS, "GrassBlock ID");
/* 1079 */       this.widgetIDSettingsColumns.add((Widget)blockGrassIdW);
/* 1080 */       this.guiapiSettings.append((Setting)(blockStoneIdS = new MoCSettingInt(this.mocSettingsConfig, "custom-id-settings", "StoneBlockID", this.blockStoneID, 256, 1, 60000)));
/*      */       
/* 1082 */       blockStoneIdW = new WidgetInt((SettingInt)blockStoneIdS, "StoneBlock ID");
/* 1083 */       this.widgetIDSettingsColumns.add((Widget)blockStoneIdW);
/* 1084 */       this.guiapiSettings.append((Setting)(blockLeafIdS = new MoCSettingInt(this.mocSettingsConfig, "custom-id-settings", "LeafBlockID", this.blockLeafID, 256, 1, 60000)));
/*      */       
/* 1086 */       blockLeafIdW = new WidgetInt((SettingInt)blockLeafIdS, "LeafBlock ID");
/* 1087 */       this.widgetIDSettingsColumns.add((Widget)blockLeafIdW);
/* 1088 */       this.guiapiSettings.append((Setting)(blockLogIdS = new MoCSettingInt(this.mocSettingsConfig, "custom-id-settings", "LogBlockID", this.blockLogID, 256, 1, 60000)));
/*      */       
/* 1090 */       blockLogIdW = new WidgetInt((SettingInt)blockLogIdS, "LogBlock ID");
/* 1091 */       this.widgetIDSettingsColumns.add((Widget)blockLogIdW);
/* 1092 */       this.guiapiSettings.append((Setting)(blockTallGrassIdS = new MoCSettingInt(this.mocSettingsConfig, "custom-id-settings", "TallGrassBlockID", this.blockTallGrassID, 256, 1, 60000)));
/*      */       
/* 1094 */       blockTallGrassIdW = new WidgetInt((SettingInt)blockTallGrassIdS, "TallG.Block ID");
/* 1095 */       this.widgetIDSettingsColumns.add((Widget)blockTallGrassIdW);
/* 1096 */       this.guiapiSettings.append((Setting)(blockPlanksIdS = new MoCSettingInt(this.mocSettingsConfig, "custom-id-settings", "PlanksBlockID", this.blockPlanksID, 256, 1, 60000)));
/*      */       
/* 1098 */       blockPlanksIdW = new WidgetInt((SettingInt)blockPlanksIdS, "PlanksBlock ID");
/* 1099 */       this.widgetIDSettingsColumns.add((Widget)blockPlanksIdW);
/* 1100 */       this.guiapiSettings.append((Setting)(wyvernBiomeIdS = new MoCSettingInt(this.mocSettingsConfig, "custom-id-settings", "WyvernLairBiomeID", this.WyvernBiomeID, 22, 1, 255)));
/*      */       
/* 1102 */       wyvernBiomeIdW = new WidgetInt((SettingInt)wyvernBiomeIdS, "WyvernBiome ID");
/* 1103 */       this.widgetIDSettingsColumns.add((Widget)wyvernBiomeIdW);
/* 1104 */       this.guiapiSettings.append((Setting)(wyvernDimensionIdS = new MoCSettingInt(this.mocSettingsConfig, "custom-id-settings", "WyvernLairDimensionID", this.WyvernDimension, -1000, 1, 60000)));
/*      */ 
/*      */       
/* 1107 */       wyvernDimensionIdW = new WidgetInt((SettingInt)wyvernDimensionIdS, "Wyv. Dimension");
/* 1108 */       this.widgetIDSettingsColumns.add((Widget)wyvernDimensionIdW);
/* 1109 */       this.IDSettingsWindow = new WidgetSimplewindow((Widget)this.widgetIDSettingsColumns, "ID Settings");
/*      */     } 
/*      */     
/* 1112 */     GuiModScreen.show((Widget)this.IDSettingsWindow);
/*      */   }
/*      */   
/*      */   public void showDefaults() {
/* 1116 */     if (this.defaultsWindow == null) {
/* 1117 */       this.defaultChoices = new WidgetClassicTwocolumn(new Widget[0]);
/* 1118 */       SimpleButtonModel defaultChoiceButtonModel = new SimpleButtonModel();
/* 1119 */       defaultChoiceButtonModel.addActionCallback((Runnable)new ModAction(this, "resetToDefaults", new Class[0]));
/* 1120 */       Button defaultChoiceButton = new Button((ButtonModel)defaultChoiceButtonModel);
/* 1121 */       defaultChoiceButton.setText("Yes");
/* 1122 */       this.defaultChoices.add((Widget)defaultChoiceButton);
/* 1123 */       SimpleButtonModel defaultChoiceButtonModel2 = new SimpleButtonModel();
/* 1124 */       defaultChoiceButtonModel2.addActionCallback((Runnable)new ModAction(this, "cancelReset", new Class[0]));
/* 1125 */       Button defaultChoiceButton2 = new Button((ButtonModel)defaultChoiceButtonModel2);
/* 1126 */       defaultChoiceButton2.setText("No");
/* 1127 */       this.defaultChoices.add((Widget)defaultChoiceButton2);
/* 1128 */       this.defaultsWindow = new WidgetSimplewindow((Widget)this.defaultChoices, "Are you sure ? All settings will be erased.");
/*      */     } 
/* 1130 */     GuiModScreen.show((Widget)this.defaultsWindow);
/*      */   }
/*      */   
/*      */   public void showEntitySettings(MoCEntityData entityData) {
/* 1134 */     if (entityData != null) {
/* 1135 */       if (entityData.getEntityWindow() == null) {
/* 1136 */         WidgetSinglecolumn widgetEntitySettingColumn = new WidgetSinglecolumn(new Widget[0]);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1142 */         MoCSettingInt settingFrequency = new MoCSettingInt(this.mocEntityConfig, entityData.getEntityName(), entityData.getEntityName() + " frequency", entityData.getFrequency(), 0, 1, 20);
/* 1143 */         this.guiapiSettings.append((Setting)settingFrequency);
/* 1144 */         widgetEntitySettingColumn.add((Widget)new WidgetInt((SettingInt)settingFrequency, "Frequency"));
/*      */ 
/*      */         
/* 1147 */         MoCSettingInt settingMinGroup = new MoCSettingInt(this.mocEntityConfig, entityData.getEntityName(), entityData.getEntityName() + " minspawn", entityData.getMinSpawn(), 1, 1, 20);
/* 1148 */         this.guiapiSettings.append((Setting)settingMinGroup);
/* 1149 */         widgetEntitySettingColumn.add((Widget)new WidgetInt((SettingInt)settingMinGroup, "MinSpawn"));
/*      */ 
/*      */         
/* 1152 */         MoCSettingInt settingMaxGroup = new MoCSettingInt(this.mocEntityConfig, entityData.getEntityName(), entityData.getEntityName() + " maxspawn", entityData.getMaxSpawn(), 1, 1, 20);
/* 1153 */         this.guiapiSettings.append((Setting)settingMaxGroup);
/* 1154 */         widgetEntitySettingColumn.add((Widget)new WidgetInt((SettingInt)settingMaxGroup, "MaxSpawn"));
/*      */ 
/*      */         
/* 1157 */         MoCSettingInt settingChunkGroup = new MoCSettingInt(this.mocEntityConfig, entityData.getEntityName(), entityData.getEntityName() + " chunkspawn", entityData.getMaxInChunk(), 1, 1, 20);
/* 1158 */         this.guiapiSettings.append((Setting)settingChunkGroup);
/* 1159 */         widgetEntitySettingColumn.add((Widget)new WidgetInt((SettingInt)settingChunkGroup, "MaxChunk"));
/*      */ 
/*      */         
/* 1162 */         MoCSettingBoolean settingCanSpawn = new MoCSettingBoolean(this.mocEntityConfig, entityData.getEntityName(), entityData.getEntityName() + " canspawn", Boolean.valueOf(entityData.getCanSpawn()));
/* 1163 */         this.guiapiSettings.append((Setting)settingCanSpawn);
/* 1164 */         widgetEntitySettingColumn.add((Widget)new WidgetBoolean((SettingBoolean)settingCanSpawn, "CanSpawn"));
/* 1165 */         entityData.setEntityWindow(new WidgetSimplewindow((Widget)widgetEntitySettingColumn, entityData.getEntityName()));
/*      */       } 
/* 1167 */       this.currentSelectedEntity = entityData;
/* 1168 */       GuiModScreen.show((Widget)entityData.getEntityWindow());
/*      */     } 
/*      */   }
/*      */   
/*      */   public void showInstaSpawner() {
/* 1173 */     this.widgetInstaSpawnerColumn = new WidgetSinglecolumn(new Widget[0]);
/*      */     
/* 1175 */     ArrayList<String> moCreaturesList = new ArrayList<>();
/* 1176 */     for (Map.Entry<String, MoCEntityData> entityEntry : (Iterable<Map.Entry<String, MoCEntityData>>)MoCreatures.mocEntityMap.entrySet()) {
/* 1177 */       moCreaturesList.add(((MoCEntityData)entityEntry.getValue()).getEntityName());
/*      */     }
/* 1179 */     Collections.sort(moCreaturesList);
/* 1180 */     this
/* 1181 */       .entityList = this.guiapiSettings.addSetting((Widget)this.widgetInstaSpawnerColumn, "Creature Type:", "SpawnEntityList", moCreaturesList, this.mocSettingsConfig, "");
/*      */     
/* 1183 */     ((WidgetList)this.entityList.displayWidget).listBox.setTheme("/listbox");
/* 1184 */     this.widgetInstaSpawnerColumn.heightOverrideExceptions.put(this.entityList.displayWidget, Integer.valueOf(130));
/* 1185 */     this.settingNumberToSpawn = this.guiapiSettings.addSetting((Widget)this.widgetInstaSpawnerColumn, "Number to Spawn", "spawnN", 3, 1, 10);
/* 1186 */     this.widgetInstaSpawnerColumn.add((Widget)GuiApiHelper.makeButton("Perform Spawn", (new ModAction(this, "instaSpawn", new Class[] { MoCSettingList.class, ArrayList.class
/* 1187 */             })).setDefaultArguments(new Object[] { this.entityList, moCreaturesList }), Boolean.valueOf(true)));
/* 1188 */     this.instaSpawnerWindow = new WidgetSimplewindow((Widget)this.widgetInstaSpawnerColumn, "Select the Creature to Spawn");
/* 1189 */     GuiModScreen.show((Widget)this.instaSpawnerWindow);
/*      */   }
/*      */   
/*      */   public void instaSpawn(MoCSettingList setting, ArrayList<String> aList) {
/* 1193 */     ListBox<String> listbox = ((WidgetList)setting.displayWidget).listBox;
/* 1194 */     int selected = listbox.getSelected();
/* 1195 */     int numberToSpawn = ((Integer)this.settingNumberToSpawn.get()).intValue();
/* 1196 */     String entityName = aList.get(selected);
/* 1197 */     for (Map.Entry<String, MoCEntityData> entityEntry : (Iterable<Map.Entry<String, MoCEntityData>>)MoCreatures.mocEntityMap.entrySet()) {
/* 1198 */       if (((MoCEntityData)entityEntry.getValue()).getEntityName().equalsIgnoreCase(entityName)) {
/*      */         try {
/* 1200 */           MoCMessageHandler.INSTANCE.sendToServer((IMessage)new MoCMessageInstaSpawn(((MoCEntityData)entityEntry.getValue()).getEntityID(), numberToSpawn));
/* 1201 */         } catch (Exception exception) {}
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetAllData() {
/* 1210 */     this.instaSpawnerWindow = null;
/* 1211 */     this.settingNumberToSpawn = null;
/* 1212 */     this.creatureOptionsWindow = null;
/* 1213 */     this.creatureSettingsWindow = null;
/* 1214 */     this.creatureSpawnSettingsWindow = null;
/* 1215 */     this.waterMobOptionsWindow = null;
/* 1216 */     this.waterMobSettingsWindow = null;
/* 1217 */     this.waterMobSpawnSettingsWindow = null;
/* 1218 */     this.mobOptionsWindow = null;
/* 1219 */     this.mobSettingsWindow = null;
/* 1220 */     this.mobSpawnSettingsWindow = null;
/* 1221 */     this.ambientOptionsWindow = null;
/* 1222 */     this.ambientSpawnSettingsWindow = null;
/* 1223 */     this.generalSettingsWindow = null;
/* 1224 */     this.IDSettingsWindow = null;
/* 1225 */     this.vanillamobwindow = null;
/* 1226 */     this.defaultsWindow = null;
/*      */     
/* 1228 */     for (int i = 0; i < ModSettingScreen.modScreens.size(); i++) {
/* 1229 */       if (((ModSettingScreen)ModSettingScreen.modScreens.get(i)).niceName.equalsIgnoreCase("DrZhark's Mo'Creatures")) {
/* 1230 */         ModSettingScreen.modScreens.remove(i);
/*      */       }
/*      */     } 
/*      */     
/* 1234 */     this.MoCScreen = null;
/* 1235 */     this.guiapiSettings = null;
/* 1236 */     super.resetAllData();
/*      */   }
/*      */   
/*      */   public void resetToDefaults() {
/* 1240 */     if (this.mocSettingsConfig.getFile().exists()) {
/* 1241 */       String parentDir = this.configFile.getParent();
/* 1242 */       if (!this.mocSettingsConfig.getFile().renameTo(new File(parentDir, "MoCreatures" + File.separator + "MoCGlobal.cfg.bak"))) {
/* 1243 */         File oldFile = new File(parentDir, "MoCreatures" + File.separator + "MoCGlobal.cfg.bak");
/* 1244 */         oldFile.delete();
/* 1245 */         this.mocSettingsConfig.getFile().renameTo(new File(parentDir, "MoCreatures" + File.separator + "MoCGlobal.cfg.bak"));
/*      */       } 
/* 1247 */       this.mocSettingsConfig = new MoCConfiguration(new File(parentDir, "MoCreatures" + File.separator + "MoCGlobal.cfg"));
/* 1248 */       File mocreaturesFile = new File(parentDir, "MoCreatures.cfg");
/* 1249 */       if (mocreaturesFile.exists() && 
/* 1250 */         !mocreaturesFile.renameTo(new File(parentDir, "MoCreatures.cfg.bak"))) {
/* 1251 */         File oldFile = new File(parentDir, "MoCreatures.cfg.bak");
/* 1252 */         oldFile.delete();
/* 1253 */         mocreaturesFile.renameTo(new File(parentDir, "MoCreatures.cfg.bak"));
/*      */       } 
/*      */     } 
/*      */     
/* 1257 */     resetAllData();
/* 1258 */     MoCreatures.proxy.initGUI();
/* 1259 */     GuiModScreen.show(this.MoCScreen.theWidget);
/*      */   }
/*      */   
/*      */   public void cancelReset() {
/* 1263 */     GuiModScreen.show(instance.MoCScreen.theWidget);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getParticleFX() {
/* 1268 */     return this.particleFX;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getDisplayPetName() {
/* 1273 */     return this.displayPetName;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getDisplayPetIcons() {
/* 1278 */     return this.displayPetIcons;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getDisplayPetHealth() {
/* 1283 */     return this.displayPetHealth;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAnimateTextures() {
/* 1288 */     return this.animateTextures;
/*      */   }
/*      */ 
/*      */   
/*      */   public void printMessageToPlayer(String msg) {
/* 1293 */     (Minecraft.getMinecraft()).player.sendMessage((ITextComponent)new TextComponentTranslation(msg, new Object[0]));
/*      */   }
/*      */   
/*      */   public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
/* 1297 */     for (Map.Entry<T, E> entry : map.entrySet()) {
/* 1298 */       if (value.equals(entry.getValue())) {
/* 1299 */         return entry.getKey();
/*      */       }
/*      */     } 
/* 1302 */     return null;
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\MoCClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */