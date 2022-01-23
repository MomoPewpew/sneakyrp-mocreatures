package drzhark.mocreatures.client;
import drzhark.mocreatures.MoCEntityData;
import drzhark.mocreatures.MoCProxy;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.MoCConstants;
import drzhark.mocreatures.client.model.MoCModelAnt;
import drzhark.mocreatures.client.model.MoCModelBear;
import drzhark.mocreatures.client.model.MoCModelBird;
import drzhark.mocreatures.client.model.MoCModelBoar;
import drzhark.mocreatures.client.model.MoCModelBunny;
import drzhark.mocreatures.client.model.MoCModelButterfly;
import drzhark.mocreatures.client.model.MoCModelCrab;
import drzhark.mocreatures.client.model.MoCModelCricket;
import drzhark.mocreatures.client.model.MoCModelCrocodile;
import drzhark.mocreatures.client.model.MoCModelDeer;
import drzhark.mocreatures.client.model.MoCModelDolphin;
import drzhark.mocreatures.client.model.MoCModelDragonfly;
import drzhark.mocreatures.client.model.MoCModelDuck;
import drzhark.mocreatures.client.model.MoCModelEgg;
import drzhark.mocreatures.client.model.MoCModelElephant;
import drzhark.mocreatures.client.model.MoCModelEnt;
import drzhark.mocreatures.client.model.MoCModelFirefly;
import drzhark.mocreatures.client.model.MoCModelFishy;
import drzhark.mocreatures.client.model.MoCModelFox;
import drzhark.mocreatures.client.model.MoCModelGoat;
import drzhark.mocreatures.client.model.MoCModelGolem;
import drzhark.mocreatures.client.model.MoCModelJellyFish;
import drzhark.mocreatures.client.model.MoCModelKitty;
import drzhark.mocreatures.client.model.MoCModelKittyBed;
import drzhark.mocreatures.client.model.MoCModelKittyBed2;
import drzhark.mocreatures.client.model.MoCModelKomodo;
import drzhark.mocreatures.client.model.MoCModelLitterBox;
import drzhark.mocreatures.client.model.MoCModelMaggot;
import drzhark.mocreatures.client.model.MoCModelManticore;
import drzhark.mocreatures.client.model.MoCModelMediumFish;
import drzhark.mocreatures.client.model.MoCModelMiniGolem;
import drzhark.mocreatures.client.model.MoCModelMole;
import drzhark.mocreatures.client.model.MoCModelMouse;
import drzhark.mocreatures.client.model.MoCModelNewBigCat;
import drzhark.mocreatures.client.model.MoCModelNewHorse;
import drzhark.mocreatures.client.model.MoCModelNewHorseMob;
import drzhark.mocreatures.client.model.MoCModelOgre;
import drzhark.mocreatures.client.model.MoCModelOstrich;
import drzhark.mocreatures.client.model.MoCModelPetScorpion;
import drzhark.mocreatures.client.model.MoCModelRaccoon;
import drzhark.mocreatures.client.model.MoCModelRat;
import drzhark.mocreatures.client.model.MoCModelRay;
import drzhark.mocreatures.client.model.MoCModelScorpion;
import drzhark.mocreatures.client.model.MoCModelShark;
import drzhark.mocreatures.client.model.MoCModelSilverSkeleton;
import drzhark.mocreatures.client.model.MoCModelSmallFish;
import drzhark.mocreatures.client.model.MoCModelSnake;
import drzhark.mocreatures.client.model.MoCModelTurkey;
import drzhark.mocreatures.client.model.MoCModelTurtle;
import drzhark.mocreatures.client.model.MoCModelWere;
import drzhark.mocreatures.client.model.MoCModelWereHuman;
import drzhark.mocreatures.client.model.MoCModelWolf;
import drzhark.mocreatures.client.model.MoCModelWraith;
import drzhark.mocreatures.client.model.MoCModelWyvern;
import drzhark.mocreatures.client.model.MoCModelBee;
import drzhark.mocreatures.client.model.MoCModelFly;
import drzhark.mocreatures.client.model.MoCModelRoach;
import drzhark.mocreatures.client.model.MoCModelSnail;
import drzhark.mocreatures.client.renderer.entity.MoCRenderBunny;
import drzhark.mocreatures.client.renderer.entity.MoCRenderBird;
import drzhark.mocreatures.client.renderer.entity.MoCRenderButterfly;
import drzhark.mocreatures.client.renderer.entity.MoCRenderCricket;
import drzhark.mocreatures.client.renderer.entity.MoCRenderCrocodile;
import drzhark.mocreatures.client.renderer.entity.MoCRenderDolphin;
import drzhark.mocreatures.client.renderer.entity.MoCRenderEgg;
import drzhark.mocreatures.client.renderer.entity.MoCRenderFirefly;
import drzhark.mocreatures.client.renderer.entity.MoCRenderGoat;
import drzhark.mocreatures.client.renderer.entity.MoCRenderGolem;
import drzhark.mocreatures.client.renderer.entity.MoCRenderHellRat;
import drzhark.mocreatures.client.renderer.entity.MoCRenderHorseMob;
import drzhark.mocreatures.client.renderer.entity.MoCRenderInsect;
import drzhark.mocreatures.client.renderer.entity.MoCRenderKitty;
import drzhark.mocreatures.client.renderer.entity.MoCRenderKittyBed;
import drzhark.mocreatures.client.renderer.entity.MoCRenderLitterBox;
import drzhark.mocreatures.client.renderer.entity.MoCRenderMoC;
import drzhark.mocreatures.client.renderer.entity.MoCRenderMouse;
import drzhark.mocreatures.client.renderer.entity.MoCRenderNewHorse;
import drzhark.mocreatures.client.renderer.entity.MoCRenderOstrich;
import drzhark.mocreatures.client.renderer.entity.MoCRenderPetScorpion;
import drzhark.mocreatures.client.renderer.entity.MoCRenderRat;
import drzhark.mocreatures.client.renderer.entity.MoCRenderScorpion;
import drzhark.mocreatures.client.renderer.entity.MoCRenderShark;
import drzhark.mocreatures.client.renderer.entity.MoCRenderSnake;
import drzhark.mocreatures.client.renderer.entity.MoCRenderTRock;
import drzhark.mocreatures.client.renderer.entity.MoCRenderTurtle;
import drzhark.mocreatures.client.renderer.entity.MoCRenderWWolf;
import drzhark.mocreatures.client.renderer.entity.MoCRenderWerewolf;
import drzhark.mocreatures.client.renderer.entity.MoCRenderWraith;
import drzhark.mocreatures.configuration.MoCConfiguration;
import drzhark.mocreatures.entity.IMoCEntity;
import drzhark.mocreatures.entity.ambient.MoCEntityAnt;
import drzhark.mocreatures.entity.ambient.MoCEntityBee;
import drzhark.mocreatures.entity.ambient.MoCEntityButterfly;
import drzhark.mocreatures.entity.ambient.MoCEntityCrab;
import drzhark.mocreatures.entity.ambient.MoCEntityCricket;
import drzhark.mocreatures.entity.ambient.MoCEntityDragonfly;
import drzhark.mocreatures.entity.ambient.MoCEntityFirefly;
import drzhark.mocreatures.entity.ambient.MoCEntityFly;
import drzhark.mocreatures.entity.ambient.MoCEntityMaggot;
import drzhark.mocreatures.entity.ambient.MoCEntityRoach;
import drzhark.mocreatures.entity.ambient.MoCEntitySnail;
import drzhark.mocreatures.entity.aquatic.MoCEntityAnchovy;
import drzhark.mocreatures.entity.aquatic.MoCEntityAngelFish;
import drzhark.mocreatures.entity.aquatic.MoCEntityAngler;
import drzhark.mocreatures.entity.aquatic.MoCEntityBass;
import drzhark.mocreatures.entity.aquatic.MoCEntityClownFish;
import drzhark.mocreatures.entity.aquatic.MoCEntityCod;
import drzhark.mocreatures.entity.aquatic.MoCEntityDolphin;
import drzhark.mocreatures.entity.aquatic.MoCEntityFishy;
import drzhark.mocreatures.entity.aquatic.MoCEntityGoldFish;
import drzhark.mocreatures.entity.aquatic.MoCEntityHippoTang;
import drzhark.mocreatures.entity.aquatic.MoCEntityJellyFish;
import drzhark.mocreatures.entity.aquatic.MoCEntityManderin;
import drzhark.mocreatures.entity.aquatic.MoCEntityMantaRay;
import drzhark.mocreatures.entity.aquatic.MoCEntityPiranha;
import drzhark.mocreatures.entity.aquatic.MoCEntitySalmon;
import drzhark.mocreatures.entity.aquatic.MoCEntityShark;
import drzhark.mocreatures.entity.aquatic.MoCEntityStingRay;
import drzhark.mocreatures.entity.item.MoCEntityEgg;
import drzhark.mocreatures.entity.item.MoCEntityKittyBed;
import drzhark.mocreatures.entity.item.MoCEntityLitterBox;
import drzhark.mocreatures.entity.item.MoCEntityThrowableRock;
import drzhark.mocreatures.entity.monster.MoCEntityCaveOgre;
import drzhark.mocreatures.entity.monster.MoCEntityFireOgre;
import drzhark.mocreatures.entity.monster.MoCEntityFlameWraith;
import drzhark.mocreatures.entity.monster.MoCEntityGolem;
import drzhark.mocreatures.entity.monster.MoCEntityGreenOgre;
import drzhark.mocreatures.entity.monster.MoCEntityHellRat;
import drzhark.mocreatures.entity.monster.MoCEntityHorseMob;
import drzhark.mocreatures.entity.monster.MoCEntityManticore;
import drzhark.mocreatures.entity.monster.MoCEntityMiniGolem;
import drzhark.mocreatures.entity.monster.MoCEntityRat;
import drzhark.mocreatures.entity.monster.MoCEntityScorpion;
import drzhark.mocreatures.entity.monster.MoCEntitySilverSkeleton;
import drzhark.mocreatures.entity.monster.MoCEntityWWolf;
import drzhark.mocreatures.entity.monster.MoCEntityWerewolf;
import drzhark.mocreatures.entity.monster.MoCEntityWraith;
import drzhark.mocreatures.entity.passive.MoCEntityBoar;
import drzhark.mocreatures.entity.passive.MoCEntityBird;
import drzhark.mocreatures.entity.passive.MoCEntityBlackBear;
import drzhark.mocreatures.entity.passive.MoCEntityBunny;
import drzhark.mocreatures.entity.passive.MoCEntityCrocodile;
import drzhark.mocreatures.entity.passive.MoCEntityDeer;
import drzhark.mocreatures.entity.passive.MoCEntityDuck;
import drzhark.mocreatures.entity.passive.MoCEntityElephant;
import drzhark.mocreatures.entity.passive.MoCEntityEnt;
import drzhark.mocreatures.entity.passive.MoCEntityFox;
import drzhark.mocreatures.entity.passive.MoCEntityGoat;
import drzhark.mocreatures.entity.passive.MoCEntityGrizzlyBear;
import drzhark.mocreatures.entity.passive.MoCEntityHorse;
import drzhark.mocreatures.entity.passive.MoCEntityKitty;
import drzhark.mocreatures.entity.passive.MoCEntityKomodo;
import drzhark.mocreatures.entity.passive.MoCEntityLeoger;
import drzhark.mocreatures.entity.passive.MoCEntityLeopard;
import drzhark.mocreatures.entity.passive.MoCEntityLiard;
import drzhark.mocreatures.entity.passive.MoCEntityLiger;
import drzhark.mocreatures.entity.passive.MoCEntityLion;
import drzhark.mocreatures.entity.passive.MoCEntityLither;
import drzhark.mocreatures.entity.passive.MoCEntityManticorePet;
import drzhark.mocreatures.entity.passive.MoCEntityMole;
import drzhark.mocreatures.entity.passive.MoCEntityMouse;
import drzhark.mocreatures.entity.passive.MoCEntityOstrich;
import drzhark.mocreatures.entity.passive.MoCEntityPandaBear;
import drzhark.mocreatures.entity.passive.MoCEntityPanthard;
import drzhark.mocreatures.entity.passive.MoCEntityPanther;
import drzhark.mocreatures.entity.passive.MoCEntityPanthger;
import drzhark.mocreatures.entity.passive.MoCEntityPetScorpion;
import drzhark.mocreatures.entity.passive.MoCEntityPolarBear;
import drzhark.mocreatures.entity.passive.MoCEntityRaccoon;
import drzhark.mocreatures.entity.passive.MoCEntitySnake;
import drzhark.mocreatures.entity.passive.MoCEntityTiger;
import drzhark.mocreatures.entity.passive.MoCEntityTurkey;
import drzhark.mocreatures.entity.passive.MoCEntityTurtle;
import drzhark.mocreatures.entity.passive.MoCEntityWyvern;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageInstaSpawn;
import drzhark.mocreatures.util.MoCLog;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCClientProxy extends MoCProxy {
public static Minecraft mc = Minecraft.getMinecraft();
public static MoCClientProxy instance;




private static final String BUTTON_GENERAL_SETTINGS = "General Settings";

private static final String BUTTON_ID_SETTINGS = "ID Settings";

private static final String BUTTON_CREATURE_GENERAL_SETTINGS = "Creature General Settings";

private static final String BUTTON_CREATURE_SPAWN_SETTINGS = "Creature Spawn Settings";

private static final String BUTTON_MONSTER_GENERAL_SETTINGS = "Monster General Settings";

private static final String BUTTON_MONSTER_SPAWN_SETTINGS = "Monster Spawn Settings";

private static final String BUTTON_WATERMOB_GENERAL_SETTINGS = "Water Mob General Settings";

private static final String BUTTON_WATERMOB_SPAWN_SETTINGS = "Water Mob Spawn Settings";

private static final String BUTTON_AMBIENT_SPAWN_SETTINGS = "Ambient Spawn Settings";

private static final String BUTTON_OWNERSHIP_SETTINGS = "Ownership Settings";

private static final String BUTTON_DEFAULTS = "Reset to Defaults";

private static final String MOC_SCREEN_TITLE = "DrZhark's Mo'Creatures";


public void registerRenderers() {}


public void initTextures() {
}



@Override
public ResourceLocation getTexture(String texture) {
    return new ResourceLocation(MoCConstants.MOD_ID, "textures/models/" + texture);
}


public void registerRenderInformation() {
RenderingRegistry.registerEntityRenderingHandler(MoCEntityBunny.class, new MoCRenderBunny(new MoCModelBunny(), 0.3F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityBird.class, new MoCRenderBird(new MoCModelBird(), 0.3F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityTurtle.class, new MoCRenderTurtle(new MoCModelTurtle(), 0.4F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityMouse.class, new MoCRenderMouse(new MoCModelMouse(), 0.1F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntitySnake.class, new MoCRenderSnake(new MoCModelSnake(), 0.0F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityTurkey.class, new MoCRenderMoC(new MoCModelTurkey(), 0.4F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityButterfly.class, new MoCRenderButterfly(new MoCModelButterfly()));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityHorse.class, new MoCRenderNewHorse(new MoCModelNewHorse()));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityHorseMob.class, new MoCRenderHorseMob(new MoCModelNewHorseMob()));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityBoar.class, new MoCRenderMoC(new MoCModelBoar(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityBlackBear.class, new MoCRenderMoC(new MoCModelBear(), 0.7F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityGrizzlyBear.class, new MoCRenderMoC(new MoCModelBear(), 0.7F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityPandaBear.class, new MoCRenderMoC(new MoCModelBear(), 0.7F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityPolarBear.class, new MoCRenderMoC(new MoCModelBear(), 0.7F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityDuck.class, new MoCRenderMoC(new MoCModelDuck(), 0.3F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityDeer.class, new MoCRenderMoC(new MoCModelDeer(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityWWolf.class, new MoCRenderWWolf(new MoCModelWolf(), 0.7F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityWraith.class, new MoCRenderWraith(new MoCModelWraith(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityFlameWraith.class, new MoCRenderWraith(new MoCModelWraith(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityWerewolf.class, new MoCRenderWerewolf(new MoCModelWereHuman(), new MoCModelWere(), 0.7F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityFox.class, new MoCRenderMoC(new MoCModelFox(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityShark.class, new MoCRenderShark(new MoCModelShark(), 0.6F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityDolphin.class, new MoCRenderDolphin(new MoCModelDolphin(), 0.6F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityFishy.class, new MoCRenderMoC(new MoCModelFishy(), 0.1F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityEgg.class, new MoCRenderEgg(new MoCModelEgg(), 0.0F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityKitty.class, new MoCRenderKitty(new MoCModelKitty(0.0F, 15.0F), 0.4F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityKittyBed.class, new MoCRenderKittyBed(new MoCModelKittyBed(), new MoCModelKittyBed2(), 0.3F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityLitterBox.class, new MoCRenderLitterBox(new MoCModelLitterBox(), 0.3F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityRat.class, new MoCRenderRat(new MoCModelRat(), 0.2F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityHellRat.class, new MoCRenderHellRat(new MoCModelRat(), 0.4F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityScorpion.class, new MoCRenderScorpion(new MoCModelScorpion(), 0.4F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityCrocodile.class, new MoCRenderCrocodile(new MoCModelCrocodile(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityMantaRay.class, new MoCRenderMoC(new MoCModelRay(), 0.4F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityStingRay.class, new MoCRenderMoC(new MoCModelRay(), 0.4F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityJellyFish.class, new MoCRenderMoC(new MoCModelJellyFish(), 0.1F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityGoat.class, new MoCRenderGoat(new MoCModelGoat(), 0.3F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityOstrich.class, new MoCRenderOstrich(new MoCModelOstrich(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityBee.class, new MoCRenderInsect(new MoCModelBee()));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityFly.class, new MoCRenderInsect(new MoCModelFly()));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityDragonfly.class, new MoCRenderInsect(new MoCModelDragonfly()));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityFirefly.class, new MoCRenderFirefly(new MoCModelFirefly()));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityCricket.class, new MoCRenderCricket(new MoCModelCricket()));
RenderingRegistry.registerEntityRenderingHandler(MoCEntitySnail.class, new MoCRenderMoC(new MoCModelSnail(), 0.0F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityGolem.class, new MoCRenderGolem(new MoCModelGolem(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityThrowableRock.class, new MoCRenderTRock());
RenderingRegistry.registerEntityRenderingHandler(MoCEntityPetScorpion.class, new MoCRenderPetScorpion((MoCModelScorpion)new MoCModelPetScorpion(), 0.4F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityElephant.class, new MoCRenderMoC(new MoCModelElephant(), 0.7F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityKomodo.class, new MoCRenderMoC(new MoCModelKomodo(), 0.3F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityWyvern.class, new MoCRenderMoC(new MoCModelWyvern(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityGreenOgre.class, new MoCRenderMoC(new MoCModelOgre(), 0.6F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityCaveOgre.class, new MoCRenderMoC(new MoCModelOgre(), 0.6F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityFireOgre.class, new MoCRenderMoC(new MoCModelOgre(), 0.6F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityRoach.class, new MoCRenderInsect(new MoCModelRoach()));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityMaggot.class, new MoCRenderMoC(new MoCModelMaggot(), 0.0F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityCrab.class, new MoCRenderMoC(new MoCModelCrab(), 0.2F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityRaccoon.class, new MoCRenderMoC(new MoCModelRaccoon(), 0.4F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityMiniGolem.class, new MoCRenderMoC(new MoCModelMiniGolem(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntitySilverSkeleton.class, new MoCRenderMoC(new MoCModelSilverSkeleton(), 0.6F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityAnt.class, new MoCRenderInsect(new MoCModelAnt()));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityCod.class, new MoCRenderMoC(new MoCModelMediumFish(), 0.2F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntitySalmon.class, new MoCRenderMoC(new MoCModelMediumFish(), 0.2F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityBass.class, new MoCRenderMoC(new MoCModelMediumFish(), 0.2F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityAnchovy.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityAngelFish.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityAngler.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityClownFish.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityGoldFish.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityHippoTang.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityManderin.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityPiranha.class, new MoCRenderMoC(new MoCModelSmallFish(), 0.1F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityEnt.class, new MoCRenderMoC(new MoCModelEnt(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityMole.class, new MoCRenderMoC(new MoCModelMole(), 0.0F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityManticore.class, new MoCRenderMoC(new MoCModelManticore(), 0.7F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityLion.class, new MoCRenderMoC(new MoCModelNewBigCat(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityTiger.class, new MoCRenderMoC(new MoCModelNewBigCat(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityPanther.class, new MoCRenderMoC(new MoCModelNewBigCat(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityLeopard.class, new MoCRenderMoC(new MoCModelNewBigCat(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityManticorePet.class, new MoCRenderMoC(new MoCModelNewBigCat(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityLiger.class, new MoCRenderMoC(new MoCModelNewBigCat(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityLeoger.class, new MoCRenderMoC(new MoCModelNewBigCat(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityPanthger.class, new MoCRenderMoC(new MoCModelNewBigCat(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityPanthard.class, new MoCRenderMoC(new MoCModelNewBigCat(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityLither.class, new MoCRenderMoC(new MoCModelNewBigCat(), 0.5F));
RenderingRegistry.registerEntityRenderingHandler(MoCEntityLiard.class, new MoCRenderMoC(new MoCModelNewBigCat(), 0.5F));
}


public EntityPlayer getPlayer() {
return (EntityPlayer)mc.player;
}


public MoCClientProxy() {
instance = this;
}
             public void setName(EntityPlayer player, IMoCEntity mocanimal) { }
             public void UndeadFX(Entity entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0) return;  int i = densityInt / 2 * (entity.world.rand.nextInt(2) + 1); if (i == 0) i = 1;  if (i > 10) i = 10;  for (int x = 0; x < i; x++) { MoCEntityFXUndead FXUndead = new MoCEntityFXUndead(entity.world, entity.posX, entity.posY + (entity.world.rand.nextFloat() * entity.height), entity.posZ); mc.effectRenderer.addEffect(FXUndead); }  }
public void StarFX(MoCEntityHorse entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0) return;  if ((entity.getType() >= 50 && entity.getType() < 60) || entity.getType() == 36) { float fRed = entity.colorFX(1, entity.getType()); float fGreen = entity.colorFX(2, entity.getType()); float fBlue = entity.colorFX(3, entity.getType()); int i = densityInt * entity.world.rand.nextInt(2); for (int x = 0; x < i; x++) { MoCEntityFXStar FXStar = new MoCEntityFXStar((World)mc.world, entity.posX, entity.posY + (entity.world.rand.nextFloat() * entity.height), entity.posZ, fRed, fGreen, fBlue); mc.effectRenderer.addEffect(FXStar); }  }  }
public void LavaFX(Entity entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0) return;  double var2 = entity.world.rand.nextGaussian() * 0.02D; double var4 = entity.world.rand.nextGaussian() * 0.02D; double var6 = entity.world.rand.nextGaussian() * 0.02D; mc.world.spawnParticle(EnumParticleTypes.LAVA, entity.posX + (entity.world.rand.nextFloat() * entity.width) - entity.width, entity.posY + 0.5D + (entity.world.rand.nextFloat() * entity.height), entity.posZ + (entity.world.rand.nextFloat() * entity.width) - entity.width, var2, var4, var6, new int[0]); }
public void ConfigInit(FMLPreInitializationEvent event) { super.ConfigInit(event);
}
public void VanishFX(MoCEntityHorse entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
return;  for (int var6 = 0; var6 < densityInt * 8; var6++) { double newPosX = ((float)entity.posX + entity.world.rand.nextFloat()); double newPosY = 0.7D + ((float)entity.posY + entity.world.rand.nextFloat()); double newPosZ = ((float)entity.posZ + entity.world.rand.nextFloat()); int var19 = entity.world.rand.nextInt(2) * 2 - 1; double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D; double speedX = (entity.world.rand.nextFloat() * 2.0F * var19); double speedZ = (entity.world.rand.nextFloat() * 2.0F * var19); MoCEntityFXVanish FXVanish = new MoCEntityFXVanish(entity.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, entity.colorFX(1, entity.getType()), entity.colorFX(2, entity.getType()), entity.colorFX(3, entity.getType()), false); mc.effectRenderer.addEffect(FXVanish); }
}
public void MaterializeFX(MoCEntityHorse entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
return;  for (int var6 = 0; var6 < densityInt * 50; var6++) { double newPosX = ((float)entity.posX + entity.world.rand.nextFloat()); double newPosY = 0.7D + ((float)entity.posY + entity.world.rand.nextFloat()); double newPosZ = ((float)entity.posZ + entity.world.rand.nextFloat()); int var19 = entity.world.rand.nextInt(2) * 2 - 1; double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D; double speedX = (entity.world.rand.nextFloat() * 2.0F * var19); double speedZ = (entity.world.rand.nextFloat() * 2.0F * var19); MoCEntityFXVanish FXVanish = new MoCEntityFXVanish((World)mc.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, entity.colorFX(1, entity.getType()), entity.colorFX(2, entity.getType()), entity.colorFX(3, entity.getType()), true); mc.effectRenderer.addEffect(FXVanish); }
} public void initGUI() { MoCLog.logger.info("Initializing MoCreatures GUI API");


}
public void VacuumFX(MoCEntityGolem entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
return;  for (int var1 = 0; var1 < 2; var1++) { double newPosX = entity.posX - 1.5D * Math.cos((MoCTools.realAngle(entity.rotationYaw - 90.0F) / 57.29578F)); double newPosZ = entity.posZ - 1.5D * Math.sin((MoCTools.realAngle(entity.rotationYaw - 90.0F) / 57.29578F)); double newPosY = entity.posY + entity.height - 0.8D - entity.getAdjustedYOffset() * 1.8D; double speedX = (entity.world.rand.nextDouble() - 0.5D) * 4.0D; double speedY = -entity.world.rand.nextDouble(); double speedZ = (entity.world.rand.nextDouble() - 0.5D) * 4.0D; MoCEntityFXVacuum FXVacuum = new MoCEntityFXVacuum((World)mc.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, entity.colorFX(1), entity.colorFX(2), entity.colorFX(3), 146); mc.effectRenderer.addEffect(FXVacuum); }  }
public void hammerFX(EntityPlayer entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
return;  for (int var6 = 0; var6 < densityInt * 10; var6++) { double newPosX = ((float)entity.posX + entity.world.rand.nextFloat()); double newPosY = 0.3D + ((float)entity.posY + entity.world.rand.nextFloat()); double newPosZ = ((float)entity.posZ + entity.world.rand.nextFloat()); int var19 = entity.world.rand.nextInt(2) * 2 - 1; double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D; double speedX = (entity.world.rand.nextFloat() * 2.0F * var19); double d1 = (entity.world.rand.nextFloat() * 2.0F * var19); }  }
public void teleportFX(EntityPlayer entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
return;  for (int var6 = 0; var6 < densityInt * 50; var6++) { double newPosX = ((float)entity.posX + entity.world.rand.nextFloat()); double newPosY = 0.7D + ((float)entity.posY + entity.world.rand.nextFloat()); double newPosZ = ((float)entity.posZ + entity.world.rand.nextFloat()); int var19 = entity.world.rand.nextInt(2) * 2 - 1; double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D; double speedX = (entity.world.rand.nextFloat() * 2.0F * var19); double speedZ = (entity.world.rand.nextFloat() * 2.0F * var19); MoCEntityFXVanish hammerFX = new MoCEntityFXVanish((World)mc.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, 0.73828125F, 0.4296875F, 0.89453125F, true); mc.effectRenderer.addEffect(hammerFX); }  }
public int getProxyMode() { return 2; } public static final List<String> entityTypes = Arrays.asList(new String[] { "CREATURE", "MONSTER", "WATERCREATURE", "AMBIENT" }); public MoCEntityData currentSelectedEntity; public void showCreatureOptions() {  }


public void showCreatureSettings() {

}

public void showCreatureSpawnSettings() {

}


public void showMobOptions() {

}

public void showMobSpawnSettings() {

}

public void showMobSettings() {

}


public void showWaterMobOptions() {

}

public void showWaterSettings() {

}


public void showAmbientOptions() {

}

public void showAmbientSpawnSettings() {

}

public void showGeneralSpawnerOptions() {

}

public void showGeneralSettings() {

}

public void showIDSettings() {

}

public void showDefaults() {

}

public void showEntitySettings(MoCEntityData entityData) {

}

public void showInstaSpawner() {

}




public void resetAllData() {

super.resetAllData();
}

public void resetToDefaults() {
if (this.mocSettingsConfig.getFile().exists()) {
String parentDir = this.configFile.getParent();
if (!this.mocSettingsConfig.getFile().renameTo(new File(parentDir, "MoCreatures" + File.separator + "MoCGlobal.cfg.bak"))) {
File oldFile = new File(parentDir, "MoCreatures" + File.separator + "MoCGlobal.cfg.bak");
oldFile.delete();
this.mocSettingsConfig.getFile().renameTo(new File(parentDir, "MoCreatures" + File.separator + "MoCGlobal.cfg.bak"));
}
this.mocSettingsConfig = new MoCConfiguration(new File(parentDir, "MoCreatures" + File.separator + "MoCGlobal.cfg"));
File mocreaturesFile = new File(parentDir, "MoCreatures.cfg");
if (mocreaturesFile.exists() &&
!mocreaturesFile.renameTo(new File(parentDir, "MoCreatures.cfg.bak"))) {
File oldFile = new File(parentDir, "MoCreatures.cfg.bak");
oldFile.delete();
mocreaturesFile.renameTo(new File(parentDir, "MoCreatures.cfg.bak"));
}
}

resetAllData();
MoCreatures.proxy.initGUI();
}

public void cancelReset() {
}


public int getParticleFX() {
return this.particleFX;
}


public boolean getDisplayPetName() {
return this.displayPetName;
}


public boolean getDisplayPetIcons() {
return this.displayPetIcons;
}


public boolean getDisplayPetHealth() {
return this.displayPetHealth;
}


public boolean getAnimateTextures() {
return this.animateTextures;
}


public void printMessageToPlayer(String msg) {
(Minecraft.getMinecraft()).player.sendMessage((ITextComponent)new TextComponentTranslation(msg, new Object[0]));
}

public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
for (Map.Entry<T, E> entry : map.entrySet()) {
if (value.equals(entry.getValue())) {
return entry.getKey();
}
}
return null;
}
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\MoCClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
