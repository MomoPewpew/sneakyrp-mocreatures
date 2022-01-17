/*     */ package drzhark.mocreatures;
/*     */ 
/*     */ import drzhark.mocreatures.configuration.MoCConfigCategory;
/*     */ import drzhark.mocreatures.configuration.MoCConfiguration;
/*     */ import drzhark.mocreatures.configuration.MoCProperty;
/*     */ import drzhark.mocreatures.entity.IMoCEntity;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityGolem;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityHorse;
/*     */ import drzhark.mocreatures.util.MoCLog;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*     */ import net.minecraftforge.fml.common.network.IGuiHandler;
/*     */ 
/*     */ public class MoCProxy
/*     */   implements IGuiHandler
/*     */ {
/*  23 */   public static String ARMOR_TEXTURE = "textures/armor/";
/*  24 */   public static String BLOCK_TEXTURE = "textures/blocks/";
/*  25 */   public static String ITEM_TEXTURE = "textures/items/";
/*  26 */   public static String MODEL_TEXTURE = "textures/models/";
/*  27 */   public static String GUI_TEXTURE = "textures/gui/";
/*  28 */   public static String MISC_TEXTURE = "textures/misc/";
/*     */   
/*     */   public boolean displayPetHealth;
/*     */   
/*     */   public boolean displayPetName;
/*     */   
/*     */   public boolean displayPetIcons;
/*     */   
/*     */   public boolean animateTextures;
/*     */   
/*     */   public boolean attackDolphins;
/*     */   
/*     */   public boolean attackWolves;
/*     */   
/*     */   public boolean attackHorses;
/*     */   
/*     */   public boolean staticBed;
/*     */   
/*     */   public boolean staticLitter;
/*     */   
/*     */   public boolean easyBreeding;
/*     */   
/*     */   public boolean destroyDrops;
/*     */   public boolean enableOwnership;
/*     */   public boolean enableResetOwnership;
/*     */   public boolean elephantBulldozer;
/*     */   public boolean killallVillagers;
/*     */   public boolean golemDestroyBlocks;
/*     */   public int itemID;
/*     */   public int blockDirtID;
/*     */   public int blockGrassID;
/*     */   public int blockStoneID;
/*     */   public int blockLeafID;
/*     */   public int blockLogID;
/*     */   public int blockTallGrassID;
/*     */   public int blockPlanksID;
/*     */   public int WyvernDimension;
/*     */   public int WyvernBiomeID;
/*     */   public int maxTamed;
/*     */   public int maxOPTamed;
/*     */   public int zebraChance;
/*     */   public int ostrichEggDropChance;
/*     */   public int rareItemDropChance;
/*     */   public int wyvernEggDropChance;
/*     */   public int motherWyvernEggDropChance;
/*     */   public int particleFX;
/*  74 */   public int frequency = 6;
/*  75 */   public int minGroup = 1;
/*  76 */   public int maxGroup = 2;
/*  77 */   public int maxSpawnInChunk = 3;
/*  78 */   public float strength = 1.0F;
/*  79 */   public int minDespawnLightLevel = 2;
/*  80 */   public int maxDespawnLightLevel = 7;
/*     */   
/*     */   public float ogreStrength;
/*     */   
/*     */   public float caveOgreStrength;
/*     */   
/*     */   public float fireOgreStrength;
/*     */   public short ogreAttackRange;
/*     */   public short fireOgreChance;
/*     */   public short caveOgreChance;
/*     */   public boolean forceDespawns;
/*     */   public boolean enableHunters;
/*     */   public boolean debug = false;
/*     */   public boolean allowInstaSpawn;
/*     */   public boolean needsUpdate = false;
/*     */   public boolean worldInitDone = false;
/*  96 */   public int activeScreen = -1;
/*     */   
/*     */   public MoCConfiguration mocSettingsConfig;
/*     */   
/*     */   public MoCConfiguration mocEntityConfig;
/*     */   
/*     */   protected File configFile;
/*     */   protected static final String CATEGORY_MOC_GENERAL_SETTINGS = "global-settings";
/*     */   protected static final String CATEGORY_MOC_CREATURE_GENERAL_SETTINGS = "creature-general-settings";
/*     */   protected static final String CATEGORY_MOC_MONSTER_GENERAL_SETTINGS = "monster-general-settings";
/*     */   protected static final String CATEGORY_MOC_WATER_CREATURE_GENERAL_SETTINGS = "water-mob-general-settings";
/*     */   protected static final String CATEGORY_MOC_AMBIENT_GENERAL_SETTINGS = "ambient-general-settings";
/*     */   protected static final String CATEGORY_MOC_ID_SETTINGS = "custom-id-settings";
/*     */   private static final String CATEGORY_OWNERSHIP_SETTINGS = "ownership-settings";
/*     */   
/*     */   public void resetAllData() {
/* 112 */     readGlobalConfigValues();
/*     */   }
/*     */ 
/*     */   
/*     */   public void ConfigInit(FMLPreInitializationEvent event) {
/* 117 */     this
/* 118 */       .mocSettingsConfig = new MoCConfiguration(new File(event.getSuggestedConfigurationFile().getParent(), "MoCreatures" + File.separator + "MoCSettings.cfg"));
/* 119 */     this
/* 120 */       .mocEntityConfig = new MoCConfiguration(new File(event.getSuggestedConfigurationFile().getParent(), "MoCreatures" + File.separator + "MoCreatures.cfg"));
/* 121 */     this.configFile = event.getSuggestedConfigurationFile();
/* 122 */     this.mocSettingsConfig.load();
/* 123 */     this.mocEntityConfig.load();
/*     */     
/* 125 */     readGlobalConfigValues();
/* 126 */     if (this.debug) {
/* 127 */       MoCLog.logger.info("Initializing MoCreatures Config File at " + event.getSuggestedConfigurationFile().getParent() + "MoCSettings.cfg");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFrequency(String entityName) {
/* 133 */     if (MoCreatures.mocEntityMap.get(entityName) != null) {
/* 134 */       return ((MoCEntityData)MoCreatures.mocEntityMap.get(entityName)).getFrequency();
/*     */     }
/* 136 */     return this.frequency;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void UndeadFX(Entity entity) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void StarFX(MoCEntityHorse moCEntityHorse) {}
/*     */ 
/*     */   
/*     */   public void LavaFX(Entity entity) {}
/*     */ 
/*     */   
/*     */   public void VanishFX(MoCEntityHorse entity) {}
/*     */ 
/*     */   
/*     */   public void MaterializeFX(MoCEntityHorse entity) {}
/*     */ 
/*     */   
/*     */   public void VacuumFX(MoCEntityGolem entity) {}
/*     */ 
/*     */   
/*     */   public void hammerFX(EntityPlayer entityplayer) {}
/*     */ 
/*     */   
/*     */   public void teleportFX(EntityPlayer entity) {}
/*     */ 
/*     */   
/*     */   public boolean getAnimateTextures() {
/* 167 */     return false;
/*     */   }
/*     */   
/*     */   public boolean getDisplayPetName() {
/* 171 */     return this.displayPetName;
/*     */   }
/*     */   
/*     */   public boolean getDisplayPetIcons() {
/* 175 */     return this.displayPetIcons;
/*     */   }
/*     */   
/*     */   public boolean getDisplayPetHealth() {
/* 179 */     return this.displayPetHealth;
/*     */   }
/*     */   
/*     */   public int getParticleFX() {
/* 183 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initTextures() {}
/*     */   
/*     */   public ResourceLocation getTexture(String texture) {
/* 190 */     return null;
/*     */   }
/*     */   
/*     */   public EntityPlayer getPlayer() {
/* 194 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void printMessageToPlayer(String msg) {}
/*     */   
/*     */   public List<String> parseName(String biomeConfigEntry) {
/* 201 */     String tag = biomeConfigEntry.substring(0, biomeConfigEntry.indexOf('|'));
/* 202 */     String biomeName = biomeConfigEntry.substring(biomeConfigEntry.indexOf('|') + 1, biomeConfigEntry.length());
/* 203 */     List<String> biomeParts = new ArrayList<>();
/* 204 */     biomeParts.add(tag);
/* 205 */     biomeParts.add(biomeName);
/* 206 */     return biomeParts;
/*     */   }
/*     */   
/*     */   public void readMocConfigValues() {
/* 210 */     if (MoCreatures.mocEntityMap != null && !MoCreatures.mocEntityMap.isEmpty()) {
/* 211 */       for (MoCEntityData entityData : MoCreatures.mocEntityMap.values()) {
/* 212 */         MoCConfigCategory cat = this.mocEntityConfig.getCategory(entityData.getEntityName().toLowerCase());
/* 213 */         if (!cat.containsKey("frequency")) {
/* 214 */           cat.put("frequency", new MoCProperty("frequency", Integer.toString(entityData.getFrequency()), MoCProperty.Type.INTEGER));
/*     */         } else {
/* 216 */           entityData.setFrequency(Integer.parseInt((cat.get("frequency")).value));
/*     */         } 
/* 218 */         if (!cat.containsKey("minspawn")) {
/* 219 */           cat.put("minspawn", new MoCProperty("minspawn", Integer.toString(entityData.getMinSpawn()), MoCProperty.Type.INTEGER));
/*     */         } else {
/* 221 */           entityData.setMinSpawn(Integer.parseInt((cat.get("minspawn")).value));
/*     */         } 
/* 223 */         if (!cat.containsKey("maxspawn")) {
/* 224 */           cat.put("maxspawn", new MoCProperty("maxspawn", Integer.toString(entityData.getMaxSpawn()), MoCProperty.Type.INTEGER));
/*     */         } else {
/* 226 */           entityData.setMaxSpawn(Integer.parseInt((cat.get("maxspawn")).value));
/*     */         } 
/* 228 */         if (!cat.containsKey("maxchunk")) {
/* 229 */           cat.put("maxchunk", new MoCProperty("maxchunk", Integer.toString(entityData.getMaxInChunk()), MoCProperty.Type.INTEGER));
/*     */         } else {
/* 231 */           entityData.setMaxInChunk(Integer.parseInt((cat.get("maxchunk")).value));
/*     */         } 
/* 233 */         if (!cat.containsKey("canspawn")) {
/* 234 */           cat.put("canspawn", new MoCProperty("canspawn", Boolean.toString(entityData.getCanSpawn()), MoCProperty.Type.BOOLEAN)); continue;
/*     */         } 
/* 236 */         entityData.setCanSpawn(Boolean.parseBoolean((cat.get("canspawn")).value));
/*     */       } 
/*     */     }
/*     */     
/* 240 */     this.mocEntityConfig.save();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readGlobalConfigValues() {
/* 248 */     this
/* 249 */       .displayPetHealth = this.mocSettingsConfig.get("global-settings", "displayPetHealth", true, "Shows Pet Health").getBoolean(true);
/* 250 */     this.displayPetName = this.mocSettingsConfig.get("global-settings", "displayPetName", true, "Shows Pet Name").getBoolean(true);
/* 251 */     this
/* 252 */       .displayPetIcons = this.mocSettingsConfig.get("global-settings", "displayPetIcons", true, "Shows Pet Emotes").getBoolean(true);
/* 253 */     this
/* 254 */       .animateTextures = this.mocSettingsConfig.get("global-settings", "animateTextures", true, "Animate Textures").getBoolean(true);
/*     */     
/* 256 */     this
/*     */       
/* 258 */       .itemID = this.mocSettingsConfig.get("custom-id-settings", "ItemID", 8772, "The starting ID used for MoCreatures items. Each item will increment this number by 1 for its ID.").getInt();
/* 259 */     this
/*     */       
/* 261 */       .allowInstaSpawn = this.mocSettingsConfig.get("global-settings", "allowInstaSpawn", false, "Allows you to instantly spawn MoCreatures from GUI.").getBoolean(false);
/* 262 */     this.debug = this.mocSettingsConfig.get("global-settings", "debug", false, "Turns on verbose logging").getBoolean(false);
/* 263 */     this
/*     */ 
/*     */ 
/*     */       
/* 267 */       .minDespawnLightLevel = this.mocSettingsConfig.get("creature-general-settings", "despawnLightLevel", 2, "The minimum light level threshold used to determine whether or not to despawn a farm animal. Note: Configure this value in CMS if it is installed.").getInt();
/* 268 */     this
/*     */ 
/*     */ 
/*     */       
/* 272 */       .maxDespawnLightLevel = this.mocSettingsConfig.get("creature-general-settings", "despawnLightLevel", 7, "The maximum light level threshold used to determine whether or not to despawn a farm animal. Note: Configure this value in CMS if it is installed.").getInt();
/* 273 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 279 */       .forceDespawns = this.mocSettingsConfig.get("global-settings", "forceDespawns", false, "If true, it will force despawns on all creatures including vanilla for a more dynamic experience while exploring world. If false, all passive mocreatures will not despawn to prevent other creatures from taking over. Note: if you experience issues with farm animals despawning, adjust despawnLightLevel. If CMS is installed, this setting must remain true if you want MoCreatures to despawn.").getBoolean(false);
/* 280 */     this
/*     */       
/* 282 */       .maxTamed = this.mocSettingsConfig.get("ownership-settings", "maxTamedPerPlayer", 10, "Max tamed creatures a player can have. Requires enableOwnership to be set to true.").getInt();
/* 283 */     this
/*     */       
/* 285 */       .maxOPTamed = this.mocSettingsConfig.get("ownership-settings", "maxTamedPerOP", 20, "Max tamed creatures an op can have. Requires enableOwnership to be set to true.").getInt();
/* 286 */     this
/*     */ 
/*     */       
/* 289 */       .enableOwnership = this.mocSettingsConfig.get("ownership-settings", "enableOwnership", false, "Assigns player as owner for each creature they tame. Only the owner can interact with the tamed creature.").getBoolean(false);
/* 290 */     this
/*     */       
/* 292 */       .enableResetOwnership = this.mocSettingsConfig.get("ownership-settings", "enableResetOwnerScroll", false, "Allows players to remove a tamed creatures owner essentially untaming it.").getBoolean(false);
/* 293 */     this
/*     */       
/* 295 */       .easyBreeding = this.mocSettingsConfig.get("creature-general-settings", "EasyBreeding", false, "Makes horse breeding simpler.").getBoolean(false);
/* 296 */     this.elephantBulldozer = this.mocSettingsConfig.get("creature-general-settings", "ElephantBulldozer", true).getBoolean(true);
/* 297 */     this
/* 298 */       .zebraChance = this.mocSettingsConfig.get("creature-general-settings", "ZebraChance", 10, "The percent for spawning a zebra.").getInt();
/* 299 */     this
/*     */       
/* 301 */       .ostrichEggDropChance = this.mocSettingsConfig.get("creature-general-settings", "OstrichEggDropChance", 3, "A value of 3 means ostriches have a 3% chance to drop an egg.").getInt();
/* 302 */     this.staticBed = this.mocSettingsConfig.get("creature-general-settings", "StaticBed", true).getBoolean(true);
/* 303 */     this.staticLitter = this.mocSettingsConfig.get("creature-general-settings", "StaticLitter", true).getBoolean(true);
/* 304 */     this.particleFX = this.mocSettingsConfig.get("global-settings", "particleFX", 3).getInt();
/* 305 */     this
/*     */       
/* 307 */       .attackDolphins = this.mocSettingsConfig.get("water-mob-general-settings", "AttackDolphins", false, "Allows water creatures to attack dolphins.").getBoolean(false);
/* 308 */     this
/*     */       
/* 310 */       .attackHorses = this.mocSettingsConfig.get("creature-general-settings", "AttackHorses", false, "Allows creatures to attack horses.").getBoolean(false);
/* 311 */     this
/*     */       
/* 313 */       .attackWolves = this.mocSettingsConfig.get("creature-general-settings", "AttackWolves", false, "Allows creatures to attack wolves.").getBoolean(false);
/* 314 */     this
/*     */       
/* 316 */       .enableHunters = this.mocSettingsConfig.get("creature-general-settings", "EnableHunters", true, "Allows creatures to attack other creatures. Not recommended if despawning is off.").getBoolean(true);
/* 317 */     this.destroyDrops = this.mocSettingsConfig.get("creature-general-settings", "DestroyDrops", false).getBoolean(false);
/* 318 */     this.killallVillagers = this.mocSettingsConfig.get("creature-general-settings", "KillAllVillagers", false).getBoolean(false);
/* 319 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 325 */       .rareItemDropChance = this.mocSettingsConfig.get("creature-general-settings", "RareItemDropChance", 25, "A value of 25 means Horses/Ostriches/Scorpions/etc. have a 25% chance to drop a rare item such as a heart of darkness, unicorn, bone when killed. Raise the value if you want higher drop rates.").getInt();
/* 326 */     this
/*     */       
/* 328 */       .wyvernEggDropChance = this.mocSettingsConfig.get("creature-general-settings", "WyvernEggDropChance", 10, "A value of 10 means wyverns have a 10% chance to drop an egg.").getInt();
/* 329 */     this
/*     */       
/* 331 */       .motherWyvernEggDropChance = this.mocSettingsConfig.get("creature-general-settings", "MotherWyvernEggDropChance", 33, "A value of 33 means mother wyverns have a 33% chance to drop an egg.").getInt();
/*     */     
/* 333 */     this
/* 334 */       .ogreStrength = Float.parseFloat(this.mocSettingsConfig.get("monster-general-settings", "OgreStrength", 2.5D, "The block destruction radius of green Ogres")
/* 335 */         .getString());
/* 336 */     this
/* 337 */       .caveOgreStrength = Float.parseFloat(this.mocSettingsConfig.get("monster-general-settings", "CaveOgreStrength", 3.0D, "The block destruction radius of Cave Ogres")
/* 338 */         .getString());
/* 339 */     this
/* 340 */       .fireOgreStrength = Float.parseFloat(this.mocSettingsConfig.get("monster-general-settings", "FireOgreStrength", 2.0D, "The block destruction radius of Fire Ogres")
/* 341 */         .getString());
/* 342 */     this
/*     */       
/* 344 */       .ogreAttackRange = (short)this.mocSettingsConfig.get("monster-general-settings", "OgreAttackRange", 12, "The block radius where ogres 'smell' players").getInt();
/* 345 */     this
/*     */       
/* 347 */       .fireOgreChance = (short)this.mocSettingsConfig.get("monster-general-settings", "FireOgreChance", 25, "The chance percentage of spawning Fire ogres in the Overworld").getInt();
/* 348 */     this
/*     */       
/* 350 */       .caveOgreChance = (short)this.mocSettingsConfig.get("monster-general-settings", "CaveOgreChance", 75, "The chance percentage of spawning Cave ogres at depth of 50 in the Overworld").getInt();
/* 351 */     this
/*     */       
/* 353 */       .golemDestroyBlocks = this.mocSettingsConfig.get("monster-general-settings", "golemDestroyBlocks", true, "Allows Big Golems to break blocks.").getBoolean(true);
/* 354 */     this.WyvernDimension = this.mocSettingsConfig.get("custom-id-settings", "WyvernLairDimensionID", -17).getInt();
/* 355 */     this.WyvernBiomeID = this.mocSettingsConfig.get("custom-id-settings", "WyvernLairBiomeID", 207).getInt();
/* 356 */     this.mocSettingsConfig.save();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerRenderers() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerRenderInformation() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
/* 371 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
/* 377 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getProxyMode() {
/* 386 */     return 1;
/*     */   }
/*     */   
/*     */   public void setName(EntityPlayer player, IMoCEntity mocanimal) {}
/*     */   
/*     */   public void initGUI() {}
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\MoCProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */