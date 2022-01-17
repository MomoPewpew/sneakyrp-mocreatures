/*      */ package drzhark.mocreatures.client;
/*      */ import drzhark.mocreatures.MoCEntityData;
/*      */ import drzhark.mocreatures.MoCProxy;
/*      */ import drzhark.mocreatures.MoCTools;
/*      */ import drzhark.mocreatures.MoCreatures;
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
/*      */ import drzhark.mocreatures.client.model.MoCModelBee;
/*      */ import drzhark.mocreatures.client.model.MoCModelFly;
/*      */ import drzhark.mocreatures.client.model.MoCModelRoach;
/*      */ import drzhark.mocreatures.client.model.MoCModelSnail;
/*      */ import drzhark.mocreatures.client.renderer.entity.MoCRenderBunny;
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
/*      */ import drzhark.mocreatures.entity.ambient.MoCEntityFirefly;
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
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityBoar;
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
/*      */
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
/*  711 */     instance = this;
/*      */   }
             public void setName(EntityPlayer player, IMoCEntity mocanimal) { }
             public void UndeadFX(Entity entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0) return;  int i = densityInt / 2 * (entity.world.rand.nextInt(2) + 1); if (i == 0) i = 1;  if (i > 10) i = 10;  for (int x = 0; x < i; x++) { MoCEntityFXUndead FXUndead = new MoCEntityFXUndead(entity.world, entity.posX, entity.posY + (entity.world.rand.nextFloat() * entity.height), entity.posZ); mc.effectRenderer.addEffect(FXUndead); }  }
/*      */   public void StarFX(MoCEntityHorse entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0) return;  if ((entity.getType() >= 50 && entity.getType() < 60) || entity.getType() == 36) { float fRed = entity.colorFX(1, entity.getType()); float fGreen = entity.colorFX(2, entity.getType()); float fBlue = entity.colorFX(3, entity.getType()); int i = densityInt * entity.world.rand.nextInt(2); for (int x = 0; x < i; x++) { MoCEntityFXStar FXStar = new MoCEntityFXStar((World)mc.world, entity.posX, entity.posY + (entity.world.rand.nextFloat() * entity.height), entity.posZ, fRed, fGreen, fBlue); mc.effectRenderer.addEffect(FXStar); }  }  }
/*      */   public void LavaFX(Entity entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0) return;  double var2 = entity.world.rand.nextGaussian() * 0.02D; double var4 = entity.world.rand.nextGaussian() * 0.02D; double var6 = entity.world.rand.nextGaussian() * 0.02D; mc.world.spawnParticle(EnumParticleTypes.LAVA, entity.posX + (entity.world.rand.nextFloat() * entity.width) - entity.width, entity.posY + 0.5D + (entity.world.rand.nextFloat() * entity.height), entity.posZ + (entity.world.rand.nextFloat() * entity.width) - entity.width, var2, var4, var6, new int[0]); }
/*  715 */   public void ConfigInit(FMLPreInitializationEvent event) { super.ConfigInit(event);
/*      */      }
/*      */   public void VanishFX(MoCEntityHorse entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
/*      */       return;  for (int var6 = 0; var6 < densityInt * 8; var6++) { double newPosX = ((float)entity.posX + entity.world.rand.nextFloat()); double newPosY = 0.7D + ((float)entity.posY + entity.world.rand.nextFloat()); double newPosZ = ((float)entity.posZ + entity.world.rand.nextFloat()); int var19 = entity.world.rand.nextInt(2) * 2 - 1; double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D; double speedX = (entity.world.rand.nextFloat() * 2.0F * var19); double speedZ = (entity.world.rand.nextFloat() * 2.0F * var19); MoCEntityFXVanish FXVanish = new MoCEntityFXVanish(entity.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, entity.colorFX(1, entity.getType()), entity.colorFX(2, entity.getType()), entity.colorFX(3, entity.getType()), false); mc.effectRenderer.addEffect(FXVanish); }
/*      */      }
/*      */   public void MaterializeFX(MoCEntityHorse entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
/*      */       return;  for (int var6 = 0; var6 < densityInt * 50; var6++) { double newPosX = ((float)entity.posX + entity.world.rand.nextFloat()); double newPosY = 0.7D + ((float)entity.posY + entity.world.rand.nextFloat()); double newPosZ = ((float)entity.posZ + entity.world.rand.nextFloat()); int var19 = entity.world.rand.nextInt(2) * 2 - 1; double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D; double speedX = (entity.world.rand.nextFloat() * 2.0F * var19); double speedZ = (entity.world.rand.nextFloat() * 2.0F * var19); MoCEntityFXVanish FXVanish = new MoCEntityFXVanish((World)mc.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, entity.colorFX(1, entity.getType()), entity.colorFX(2, entity.getType()), entity.colorFX(3, entity.getType()), true); mc.effectRenderer.addEffect(FXVanish); }
/*  737 */      } public void initGUI() { MoCLog.logger.info("Initializing MoCreatures GUI API");
/*      */
/*      */
/*      */ }
/*      */   public void VacuumFX(MoCEntityGolem entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
/*      */       return;  for (int var1 = 0; var1 < 2; var1++) { double newPosX = entity.posX - 1.5D * Math.cos((MoCTools.realAngle(entity.rotationYaw - 90.0F) / 57.29578F)); double newPosZ = entity.posZ - 1.5D * Math.sin((MoCTools.realAngle(entity.rotationYaw - 90.0F) / 57.29578F)); double newPosY = entity.posY + entity.height - 0.8D - entity.getAdjustedYOffset() * 1.8D; double speedX = (entity.world.rand.nextDouble() - 0.5D) * 4.0D; double speedY = -entity.world.rand.nextDouble(); double speedZ = (entity.world.rand.nextDouble() - 0.5D) * 4.0D; MoCEntityFXVacuum FXVacuum = new MoCEntityFXVacuum((World)mc.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, entity.colorFX(1), entity.colorFX(2), entity.colorFX(3), 146); mc.effectRenderer.addEffect(FXVacuum); }  }
/*      */   public void hammerFX(EntityPlayer entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
/*      */       return;  for (int var6 = 0; var6 < densityInt * 10; var6++) { double newPosX = ((float)entity.posX + entity.world.rand.nextFloat()); double newPosY = 0.3D + ((float)entity.posY + entity.world.rand.nextFloat()); double newPosZ = ((float)entity.posZ + entity.world.rand.nextFloat()); int var19 = entity.world.rand.nextInt(2) * 2 - 1; double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D; double speedX = (entity.world.rand.nextFloat() * 2.0F * var19); double d1 = (entity.world.rand.nextFloat() * 2.0F * var19); }  }
/*      */   public void teleportFX(EntityPlayer entity) { int densityInt = MoCreatures.proxy.getParticleFX(); if (densityInt == 0)
/*      */       return;  for (int var6 = 0; var6 < densityInt * 50; var6++) { double newPosX = ((float)entity.posX + entity.world.rand.nextFloat()); double newPosY = 0.7D + ((float)entity.posY + entity.world.rand.nextFloat()); double newPosZ = ((float)entity.posZ + entity.world.rand.nextFloat()); int var19 = entity.world.rand.nextInt(2) * 2 - 1; double speedY = (entity.world.rand.nextFloat() - 0.5D) * 0.5D; double speedX = (entity.world.rand.nextFloat() * 2.0F * var19); double speedZ = (entity.world.rand.nextFloat() * 2.0F * var19); MoCEntityFXVanish hammerFX = new MoCEntityFXVanish((World)mc.world, newPosX, newPosY, newPosZ, speedX, speedY, speedZ, 0.73828125F, 0.4296875F, 0.89453125F, true); mc.effectRenderer.addEffect(hammerFX); }  }
/*  809 */   public int getProxyMode() { return 2; } public static final List<String> entityTypes = Arrays.asList(new String[] { "CREATURE", "MONSTER", "WATERCREATURE", "AMBIENT" }); public MoCEntityData currentSelectedEntity; public void showCreatureOptions() {  }
/*      */
/*      */
/*      */   public void showCreatureSettings() {
/*  817 */
/*      */   }
/*      */
/*      */   public void showCreatureSpawnSettings() {
/*  869 */
/*      */   }
/*      */
/*      */
/*      */   public void showMobOptions() {
/*  885 */
/*      */   }
/*      */
/*      */   public void showMobSpawnSettings() {
/*  893 */
/*      */   }
/*      */
/*      */   public void showMobSettings() {
/*  909 */
/*      */   }
/*      */
/*      */
/*      */   public void showWaterMobOptions() {
/*  956 */
/*      */   }
/*      */
/*      */   public void showWaterSettings() {
/*  980 */
/*      */   }
/*      */
/*      */
/*      */   public void showAmbientOptions() {
/*  996 */
/*      */   }
/*      */
/*      */   public void showAmbientSpawnSettings() {
/* 1004 */
/*      */   }
/*      */
/*      */   public void showGeneralSpawnerOptions() {
/* 1019 */
/*      */   }
/*      */
/*      */   public void showGeneralSettings() {
/* 1026 */
/*      */   }
/*      */
/*      */   public void showIDSettings() {
/* 1066 */
/*      */   }
/*      */
/*      */   public void showDefaults() {
/* 1116 */
/*      */   }
/*      */
/*      */   public void showEntitySettings(MoCEntityData entityData) {
/* 1134 */
/*      */   }
/*      */
/*      */   public void showInstaSpawner() {
/* 1173 */
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */   public void resetAllData() {
/* 1210 */
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
/*      */   }
/*      */
/*      */   public void cancelReset() {
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
