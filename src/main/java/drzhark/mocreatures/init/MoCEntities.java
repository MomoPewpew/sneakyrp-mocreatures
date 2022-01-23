/*     */ package drzhark.mocreatures.init;
/*     */ 
/*     */ import drzhark.mocreatures.MoCEntityData;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityAnt;
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityBee;
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityButterfly;
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityCrab;
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityCricket;
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityDragonfly;
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityFirefly;
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityFly;
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityMaggot;
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntityRoach;
/*     */ import drzhark.mocreatures.entity.ambient.MoCEntitySnail;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityAnchovy;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityAngelFish;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityAngler;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityBass;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityClownFish;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityCod;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityDolphin;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityFishy;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityGoldFish;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityHippoTang;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityJellyFish;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityManderin;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityMantaRay;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityPiranha;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntitySalmon;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityShark;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityStingRay;
/*     */ import drzhark.mocreatures.entity.item.MoCEntityEgg;
/*     */ import drzhark.mocreatures.entity.item.MoCEntityKittyBed;
/*     */ import drzhark.mocreatures.entity.item.MoCEntityLitterBox;
/*     */ import drzhark.mocreatures.entity.item.MoCEntityThrowableRock;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityCaveOgre;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityFireOgre;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityFlameWraith;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityGolem;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityGreenOgre;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityHellRat;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityHorseMob;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityManticore;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityMiniGolem;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityRat;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityScorpion;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntitySilverSkeleton;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityWWolf;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityWerewolf;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityWraith;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityBird;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityBlackBear;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityBoar;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityBunny;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityCrocodile;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityDeer;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityDuck;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityElephant;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityEnt;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityFox;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityGoat;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityGrizzlyBear;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityHorse;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityKitty;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityKomodo;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityLeoger;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityLeopard;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityLiard;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityLiger;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityLion;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityLither;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityManticorePet;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityMole;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityMouse;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityOstrich;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityPandaBear;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityPanthard;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityPanther;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityPanthger;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityPetScorpion;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityPolarBear;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityRaccoon;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntitySnake;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityTiger;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityTurkey;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityTurtle;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityWyvern;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.event.RegistryEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.registry.EntityEntry;
/*     */ import net.minecraftforge.fml.common.registry.EntityRegistry;
/*     */ import net.minecraftforge.registries.IForgeRegistry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoCEntities
/*     */ {
/* 111 */   public static List<EntityEntry> ENTITIES = new ArrayList<>();
/* 112 */   public static List<EntityEntry> SPAWN_ENTITIES = new ArrayList<>();
/* 113 */   static int MoCEntityID = 0;
/*     */   
/*     */   private static EntityEntry createEntityEntry(Class<? extends Entity> cls, String name) {
/* 116 */     EntityEntry entityEntry = new EntityEntry(cls, name);
/* 117 */     entityEntry.setRegistryName(new ResourceLocation("mocreatures:" + name.toLowerCase()));
/* 118 */     ENTITIES.add(entityEntry);
/* 119 */     return entityEntry;
/*     */   }
/*     */   
/*     */   private static EntityEntry createEntityEntry(Class<? extends Entity> cls, String name, int primaryColorIn, int secondaryColorIn) {
/* 123 */     EntityEntry entityEntry = new EntityEntry(cls, name);
/* 124 */     entityEntry.setRegistryName(new ResourceLocation("mocreatures:" + name.toLowerCase()));
/* 125 */     entityEntry.setEgg(new EntityList.EntityEggInfo(new ResourceLocation("mocreatures:" + name.toLowerCase()), primaryColorIn, secondaryColorIn));
/* 126 */     SPAWN_ENTITIES.add(entityEntry);
/* 127 */     return entityEntry;
/*     */   }
/*     */   
/*     */   private static void registerEntity(Class<? extends Entity> entityClass, String entityName) {
/* 131 */     ResourceLocation resourceLocation = new ResourceLocation("mocreatures:" + entityName.toLowerCase());
/* 132 */     EntityRegistry.registerModEntity(resourceLocation, entityClass, resourceLocation.toString(), MoCEntityID, MoCreatures.instance, 80, 1, true);
/* 133 */     MoCEntityID++;
/*     */   }
/*     */   
/*     */   private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int eggColor, int eggDotsColor) {
/* 137 */     ResourceLocation resourceLocation = new ResourceLocation("mocreatures:" + entityName.toLowerCase());
/* 138 */     EntityRegistry.registerModEntity(resourceLocation, entityClass, resourceLocation.toString(), MoCEntityID, MoCreatures.instance, 80, 1, true, eggColor, eggDotsColor);
/* 139 */     MoCEntityID++;
/*     */   }
/*     */   
/* 142 */   public static EntityEntry BIRD = createEntityEntry((Class)MoCEntityBird.class, "Bird", 14020607, 14020607);
/* 143 */   public static EntityEntry BEAR = createEntityEntry((Class)MoCEntityBlackBear.class, "BlackBear", 10, 1);
/* 144 */   public static EntityEntry BOAR = createEntityEntry((Class)MoCEntityBoar.class, "Boar", 14772545, 9141102);
/* 145 */   public static EntityEntry BUNNY = createEntityEntry((Class)MoCEntityBunny.class, "Bunny", 12623485, 9141102);
/* 146 */   public static EntityEntry CROCODILE = createEntityEntry((Class)MoCEntityCrocodile.class, "Crocodile", 16711680, 65407);
/* 147 */   public static EntityEntry DUCK = createEntityEntry((Class)MoCEntityDuck.class, "Duck", 14021607, 15656192);
/* 148 */   public static EntityEntry DEER = createEntityEntry((Class)MoCEntityDeer.class, "Deer", 14021607, 33023);
/* 149 */   public static EntityEntry ELEPHANT = createEntityEntry((Class)MoCEntityElephant.class, "Elephant", 14772545, 23423);
/* 150 */   public static EntityEntry ENT = createEntityEntry((Class)MoCEntityEnt.class, "Ent", 12623485, 16711680);
/* 151 */   public static EntityEntry FOX = createEntityEntry((Class)MoCEntityFox.class, "Fox", 14772545, 5253242);
/* 152 */   public static EntityEntry GOAT = createEntityEntry((Class)MoCEntityGoat.class, "Goat", 7434694, 6053069);
/* 153 */   public static EntityEntry GRIZZLY_BEAR = createEntityEntry((Class)MoCEntityGrizzlyBear.class, "GrizzlyBear", 14772545, 1);
/* 154 */   public static EntityEntry KITTY = createEntityEntry((Class)MoCEntityKitty.class, "Kitty", 12623485, 5253242);
/* 155 */   public static EntityEntry KOMODO_DRAGON = createEntityEntry((Class)MoCEntityKomodo.class, "KomodoDragon", 16711680, 23423);
/* 156 */   public static EntityEntry LEOGER = createEntityEntry((Class)MoCEntityLeoger.class, "Leoger", 14772545, 14772545);
/* 157 */   public static EntityEntry LEOPARD = createEntityEntry((Class)MoCEntityLeopard.class, "Leopard", 13749760, 10);
/* 158 */   public static EntityEntry LIARD = createEntityEntry((Class)MoCEntityLiard.class, "Liard", 15313474, 13749760);
/* 159 */   public static EntityEntry LION = createEntityEntry((Class)MoCEntityLion.class, "Lion", 15313474, 13749760);
/* 160 */   public static EntityEntry LIGER = createEntityEntry((Class)MoCEntityLiger.class, "Liger", 15313474, 12623485);
/* 161 */   public static EntityEntry LITHER = createEntityEntry((Class)MoCEntityLither.class, "Lither", 15313474, 14772545);
/* 162 */   public static EntityEntry MANTICORE_PET = createEntityEntry((Class)MoCEntityManticorePet.class, "ManticorePet");
/* 163 */   public static EntityEntry MOLE = createEntityEntry((Class)MoCEntityMole.class, "Mole", 14020607, 16711680);
/* 164 */   public static EntityEntry MOUSE = createEntityEntry((Class)MoCEntityMouse.class, "Mouse", 14772545, 0);
/* 165 */   public static EntityEntry OSTRICH = createEntityEntry((Class)MoCEntityOstrich.class, "Ostrich", 14020607, 9639167);
/* 166 */   public static EntityEntry PANDA_BEAR = createEntityEntry((Class)MoCEntityPandaBear.class, "PandaBear", 10, 9141102);
/* 167 */   public static EntityEntry PANTHARD = createEntityEntry((Class)MoCEntityPanthard.class, "Panthard", 10, 13749760);
/* 168 */   public static EntityEntry PANTHER = createEntityEntry((Class)MoCEntityPanther.class, "Panther", 10, 205);
/* 169 */   public static EntityEntry PANTHGER = createEntityEntry((Class)MoCEntityPanthger.class, "Panthger", 10, 14772545);
/* 170 */   public static EntityEntry PET_SCORPION = createEntityEntry((Class)MoCEntityPetScorpion.class, "PetScorpion");
/* 171 */   public static EntityEntry POLAR_BEAR = createEntityEntry((Class)MoCEntityPolarBear.class, "WildPolarBear", 14020607, 9141102);
/* 172 */   public static EntityEntry RACCOON = createEntityEntry((Class)MoCEntityRaccoon.class, "Raccoon", 14772545, 13749760);
/* 173 */   public static EntityEntry SNAKE = createEntityEntry((Class)MoCEntitySnake.class, "Snake", 14020607, 13749760);
/* 174 */   public static EntityEntry TIGER = createEntityEntry((Class)MoCEntityTiger.class, "Tiger", 14772545, 0);
/* 175 */   public static EntityEntry TURTLE = createEntityEntry((Class)MoCEntityTurtle.class, "Turtle", 14772545, 9320590);
/* 176 */   public static EntityEntry TURKEY = createEntityEntry((Class)MoCEntityTurkey.class, "Turkey", 14020607, 16711680);
/* 177 */   public static EntityEntry WILDHORSE = createEntityEntry((Class)MoCEntityHorse.class, "WildHorse", 12623485, 15656192);
/* 178 */   public static EntityEntry WYVERN = createEntityEntry((Class)MoCEntityWyvern.class, "Wyvern", 14772545, 65407);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 183 */   public static EntityEntry CAVE_OGRE = createEntityEntry((Class)MoCEntityCaveOgre.class, "CaveOgre", 16711680, 33023);
/* 184 */   public static EntityEntry FLAME_WRAITH = createEntityEntry((Class)MoCEntityFlameWraith.class, "FlameWraith", 16711680, 12623485);
/* 185 */   public static EntityEntry FIRE_OGRE = createEntityEntry((Class)MoCEntityFireOgre.class, "FireOgre", 16711680, 9320595);
/* 186 */   public static EntityEntry GREEN_OGRE = createEntityEntry((Class)MoCEntityGreenOgre.class, "GreenOgre", 16711680, 65407);
/* 187 */   public static EntityEntry BIG_GOLEM = createEntityEntry((Class)MoCEntityGolem.class, "BigGolem", 16711680, 16622);
/* 188 */   public static EntityEntry HORSEMOB = createEntityEntry((Class)MoCEntityHorseMob.class, "HorseMob", 16711680, 9320590);
/* 189 */   public static EntityEntry HELLRAT = createEntityEntry((Class)MoCEntityHellRat.class, "HellRat", 16711680, 14772545);
/* 190 */   public static EntityEntry MANTICORE = createEntityEntry((Class)MoCEntityManticore.class, "Manticore", 16711680, 0);
/* 191 */   public static EntityEntry MINI_GOLEM = createEntityEntry((Class)MoCEntityMiniGolem.class, "MiniGolem", 16711680, 13749760);
/* 192 */   public static EntityEntry RAT = createEntityEntry((Class)MoCEntityRat.class, "Rat", 16711680, 9141102);
/* 193 */   public static EntityEntry SILVER_SKELETON = createEntityEntry((Class)MoCEntitySilverSkeleton.class, "SilverSkeleton", 16711680, 33023);
/* 194 */   public static EntityEntry SCORPION = createEntityEntry((Class)MoCEntityScorpion.class, "Scorpion", 16711680, 6053069);
/* 195 */   public static EntityEntry WEREWOLF = createEntityEntry((Class)MoCEntityWerewolf.class, "Werewolf", 16711680, 7434694);
/* 196 */   public static EntityEntry WRAITH = createEntityEntry((Class)MoCEntityWraith.class, "Wraith", 16711680, 0);
/* 197 */   public static EntityEntry WWOLF = createEntityEntry((Class)MoCEntityWWolf.class, "WWolf", 16711680, 13749760);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public static EntityEntry ANCHOVY = createEntityEntry((Class)MoCEntityAnchovy.class, "Anchovy", 5665535, 205);
/* 203 */   public static EntityEntry ANGELFISH = createEntityEntry((Class)MoCEntityAngelFish.class, "AngelFish", 5665535, 7434694);
/* 204 */   public static EntityEntry ANGLER = createEntityEntry((Class)MoCEntityAngler.class, "Angler", 5665535, 10);
/* 205 */   public static EntityEntry BASS = createEntityEntry((Class)MoCEntityBass.class, "Bass", 33023, 2372490);
/* 206 */   public static EntityEntry CLOWNFISH = createEntityEntry((Class)MoCEntityClownFish.class, "ClownFish", 5665535, 14772545);
/* 207 */   public static EntityEntry COD = createEntityEntry((Class)MoCEntityCod.class, "Cod", 33023, 16622);
/* 208 */   public static EntityEntry DOLPHIN = createEntityEntry((Class)MoCEntityDolphin.class, "Dolphin", 33023, 15631086);
/* 209 */   public static EntityEntry FISHY = createEntityEntry((Class)MoCEntityFishy.class, "Fishy", 5665535, 65407);
/* 210 */   public static EntityEntry GOLDFISH = createEntityEntry((Class)MoCEntityGoldFish.class, "GoldFish", 5665535, 15656192);
/* 211 */   public static EntityEntry HIPPOTANG = createEntityEntry((Class)MoCEntityHippoTang.class, "HippoTang", 5665535, 2037680);
/* 212 */   public static EntityEntry JELLYFISH = createEntityEntry((Class)MoCEntityJellyFish.class, "JellyFish", 33023, 14772545);
/* 213 */   public static EntityEntry MANDERIN = createEntityEntry((Class)MoCEntityManderin.class, "Manderin", 5665535, 12623485);
/* 214 */   public static EntityEntry PIRANHA = createEntityEntry((Class)MoCEntityPiranha.class, "Piranha", 33023, 16711680);
/* 215 */   public static EntityEntry SALMON = createEntityEntry((Class)MoCEntitySalmon.class, "Salmon", 33023, 12623485);
/* 216 */   public static EntityEntry MANTARAY = createEntityEntry((Class)MoCEntityMantaRay.class, "MantaRay", 33023, 9141102);
/* 217 */   public static EntityEntry SHARK = createEntityEntry((Class)MoCEntityShark.class, "Shark", 33023, 9013643);
/* 218 */   public static EntityEntry STINGRAY = createEntityEntry((Class)MoCEntityStingRay.class, "StingRay", 33023, 6053069);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 223 */   public static EntityEntry ANT = createEntityEntry((Class)MoCEntityAnt.class, "Ant", 65407, 12623485);
/* 224 */   public static EntityEntry BEE = createEntityEntry((Class)MoCEntityBee.class, "Bee", 65407, 15656192);
/* 225 */   public static EntityEntry BUTTERFLY = createEntityEntry((Class)MoCEntityButterfly.class, "ButterFly", 65407, 7434694);
/* 226 */   public static EntityEntry CRAB = createEntityEntry((Class)MoCEntityCrab.class, "Crab", 65407, 13749760);
/* 227 */   public static EntityEntry CRICKET = createEntityEntry((Class)MoCEntityCricket.class, "Cricket", 65407, 16622);
/* 228 */   public static EntityEntry DRAGONFLY = createEntityEntry((Class)MoCEntityDragonfly.class, "DragonFly", 65407, 14020607);
/* 229 */   public static EntityEntry FIREFLY = createEntityEntry((Class)MoCEntityFirefly.class, "Firefly", 65407, 9320590);
/* 230 */   public static EntityEntry FLY = createEntityEntry((Class)MoCEntityFly.class, "Fly", 65407, 1);
/* 231 */   public static EntityEntry MAGGOT = createEntityEntry((Class)MoCEntityMaggot.class, "Maggot", 65407, 9141102);
/* 232 */   public static EntityEntry SNAIL = createEntityEntry((Class)MoCEntitySnail.class, "Snail", 65407, 14772545);
/* 233 */   public static EntityEntry ROACH = createEntityEntry((Class)MoCEntityRoach.class, "Roach", 65407, 13749760);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 238 */   public static EntityEntry EGG = createEntityEntry((Class)MoCEntityEgg.class, "Egg");
/* 239 */   public static EntityEntry KITTY_BED = createEntityEntry((Class)MoCEntityKittyBed.class, "KittyBed");
/* 240 */   public static EntityEntry LITTERBOX = createEntityEntry((Class)MoCEntityLitterBox.class, "LitterBox");
/* 241 */   public static EntityEntry TROCK = createEntityEntry((Class)MoCEntityThrowableRock.class, "TRock");
/*     */   
/*     */   @EventBusSubscriber(modid = "mocreatures")
/*     */   public static class RegistrationHandler
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
/* 248 */       MoCreatures.LOGGER.info("Registering entities...");
/* 249 */       IForgeRegistry<EntityEntry> registry = event.getRegistry();
/* 250 */       for (EntityEntry entry : MoCEntities.ENTITIES)
/*     */       {
/*     */         
/* 253 */         MoCEntities.registerEntity(entry.getEntityClass(), entry.getName());
/*     */       }
/* 255 */       for (EntityEntry entry : MoCEntities.SPAWN_ENTITIES)
/*     */       {
/*     */         
/* 258 */         MoCEntities.registerEntity(entry.getEntityClass(), entry.getName(), (entry.getEgg()).primaryColor, (entry.getEgg()).secondaryColor);
/*     */       }
/*     */ 
/*     */       
/* 262 */       MoCreatures.mocEntityMap.put("Ant", new MoCEntityData("Ant", 4, EnumCreatureType.AMBIENT, new Biome.SpawnListEntry(MoCEntityAnt.class, 7, 1, 4), new ArrayList(
/* 263 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND }))));
/* 264 */       MoCreatures.mocEntityMap.put("Bee", new MoCEntityData("Bee", 3, EnumCreatureType.AMBIENT, new Biome.SpawnListEntry(MoCEntityBee.class, 6, 1, 2), new ArrayList(
/* 265 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS }))));
/* 266 */       MoCreatures.mocEntityMap.put("ButterFly", new MoCEntityData("ButterFly", 3, EnumCreatureType.AMBIENT, new Biome.SpawnListEntry(MoCEntityButterfly.class, 8, 1, 3), new ArrayList(
/* 267 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS }))));
/* 268 */       MoCreatures.mocEntityMap.put("Crab", new MoCEntityData("Crab", 2, EnumCreatureType.AMBIENT, new Biome.SpawnListEntry(MoCEntityCrab.class, 8, 1, 2), new ArrayList(
/* 269 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.WATER }))));
/* 270 */       MoCreatures.mocEntityMap.put("Cricket", new MoCEntityData("Cricket", 2, EnumCreatureType.AMBIENT, new Biome.SpawnListEntry(MoCEntityCricket.class, 8, 1, 2), new ArrayList(
/* 271 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP }))));
/* 272 */       MoCreatures.mocEntityMap.put("DragonFly", new MoCEntityData("DragonFly", 2, EnumCreatureType.AMBIENT, new Biome.SpawnListEntry(MoCEntityDragonfly.class, 6, 1, 2), new ArrayList(
/* 273 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.BEACH }))));
/* 274 */       MoCreatures.mocEntityMap.put("Firefly", new MoCEntityData("Firefly", 3, EnumCreatureType.AMBIENT, new Biome.SpawnListEntry(MoCEntityFirefly.class, 8, 1, 2), new ArrayList(
/* 275 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP }))));
/* 276 */       MoCreatures.mocEntityMap.put("Fly", new MoCEntityData("Fly", 2, EnumCreatureType.AMBIENT, new Biome.SpawnListEntry(MoCEntityFly.class, 8, 1, 2), new ArrayList(
/* 277 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND }))));
/* 278 */       MoCreatures.mocEntityMap.put("Maggot", new MoCEntityData("Maggot", 2, EnumCreatureType.AMBIENT, new Biome.SpawnListEntry(MoCEntityMaggot.class, 8, 1, 2), new ArrayList(
/* 279 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP }))));
/* 280 */       MoCreatures.mocEntityMap.put("Snail", new MoCEntityData("Snail", 2, EnumCreatureType.AMBIENT, new Biome.SpawnListEntry(MoCEntitySnail.class, 7, 1, 2), new ArrayList(
/* 281 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.PLAINS }))));
/* 282 */       MoCreatures.mocEntityMap.put("Roach", new MoCEntityData("Roach", 2, EnumCreatureType.AMBIENT, new Biome.SpawnListEntry(MoCEntityRoach.class, 8, 1, 2), new ArrayList(
/* 283 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP }))));
/*     */ 
/*     */       
/* 286 */       MoCreatures.mocEntityMap.put("BlackBear", new MoCEntityData("BlackBear", 4, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityBlackBear.class, 6, 1, 3), new ArrayList(
/* 287 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS }))));
/* 288 */       MoCreatures.mocEntityMap.put("GrizzlyBear", new MoCEntityData("GrizzlyBear", 4, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityGrizzlyBear.class, 6, 1, 2), new ArrayList(
/* 289 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS }))));
/* 290 */       MoCreatures.mocEntityMap.put("WildPolarBear", new MoCEntityData("WildPolarBear", 4, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityPolarBear.class, 6, 1, 2), new ArrayList(
/* 291 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.SNOWY }))));
/* 292 */       MoCreatures.mocEntityMap.put("PandaBear", new MoCEntityData("PandaBear", 4, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityPandaBear.class, 6, 1, 3), new ArrayList(
/* 293 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.PLAINS }))));
/* 294 */       MoCreatures.mocEntityMap.put("Bird", new MoCEntityData("Bird", 4, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityBird.class, 15, 2, 3), new ArrayList(
/* 295 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS }))));
/* 296 */       MoCreatures.mocEntityMap.put("Boar", new MoCEntityData("Boar", 3, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityBoar.class, 8, 2, 2), new ArrayList(
/* 297 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.PLAINS }))));
/* 298 */       MoCreatures.mocEntityMap.put("Bunny", new MoCEntityData("Bunny", 4, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityBunny.class, 8, 2, 3), new ArrayList(
/* 299 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SNOWY }))));
/* 300 */       MoCreatures.mocEntityMap.put("Crocodile", new MoCEntityData("Crocodile", 2, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityCrocodile.class, 6, 1, 2), new ArrayList(
/* 301 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.SWAMP }))));
/* 302 */       MoCreatures.mocEntityMap.put("Deer", new MoCEntityData("Deer", 2, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityDeer.class, 8, 1, 2), new ArrayList(
/* 303 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.PLAINS }))));
/* 304 */       MoCreatures.mocEntityMap.put("Duck", new MoCEntityData("Duck", 3, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityDuck.class, 8, 1, 2), new ArrayList(
/* 305 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.PLAINS }))));
/* 306 */       MoCreatures.mocEntityMap.put("Elephant", new MoCEntityData("Elephant", 3, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityElephant.class, 4, 1, 1), new ArrayList(
/*     */               
/* 308 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SNOWY }))));
/* 309 */       MoCreatures.mocEntityMap.put("Ent", new MoCEntityData("Ent", 3, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityEnt.class, 4, 1, 2), new ArrayList(
/* 310 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.PLAINS }))));
/* 311 */       MoCreatures.mocEntityMap.put("Fox", new MoCEntityData("Fox", 2, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityFox.class, 8, 1, 1), new ArrayList(
/* 312 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SNOWY }))));
/* 313 */       MoCreatures.mocEntityMap.put("Goat", new MoCEntityData("Goat", 2, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityGoat.class, 8, 1, 3), new ArrayList(
/* 314 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS }))));
/* 315 */       MoCreatures.mocEntityMap.put("Kitty", new MoCEntityData("Kitty", 3, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityKitty.class, 8, 1, 2), new ArrayList(
/* 316 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.PLAINS }))));
/* 317 */       MoCreatures.mocEntityMap.put("KomodoDragon", new MoCEntityData("KomodoDragon", 2, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityKomodo.class, 8, 1, 2), new ArrayList(
/* 318 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.SWAMP }))));
/* 319 */       MoCreatures.mocEntityMap.put("Leopard", new MoCEntityData("Leopard", 4, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityLeopard.class, 6, 1, 2), new ArrayList(
/* 320 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.HILLS, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.SNOWY }))));
/* 321 */       MoCreatures.mocEntityMap.put("Lion", new MoCEntityData("Lion", 4, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityLion.class, 6, 1, 2), new ArrayList(
/* 322 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.PLAINS }))));
/* 323 */       MoCreatures.mocEntityMap.put("Mole", new MoCEntityData("Mole", 3, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityMole.class, 7, 1, 2), new ArrayList(
/* 324 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.PLAINS }))));
/* 325 */       MoCreatures.mocEntityMap.put("Mouse", new MoCEntityData("Mouse", 2, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityMouse.class, 7, 1, 2), new ArrayList(
/* 326 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS }))));
/* 327 */       MoCreatures.mocEntityMap.put("Ostrich", new MoCEntityData("Ostrich", 3, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityOstrich.class, 4, 1, 1), new ArrayList(
/* 328 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.SANDY, BiomeDictionary.Type.PLAINS }))));
/* 329 */       MoCreatures.mocEntityMap.put("Panther", new MoCEntityData("Panther", 4, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityPanther.class, 6, 1, 2), new ArrayList(
/* 330 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MOUNTAIN }))));
/* 331 */       MoCreatures.mocEntityMap.put("Raccoon", new MoCEntityData("Raccoon", 2, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityRaccoon.class, 8, 1, 2), new ArrayList(
/* 332 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS }))));
/* 333 */       MoCreatures.mocEntityMap.put("Snake", new MoCEntityData("Snake", 3, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntitySnake.class, 8, 1, 2), new ArrayList(
/*     */ 
/*     */               
/* 336 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND }))));
/* 337 */       MoCreatures.mocEntityMap.put("Tiger", new MoCEntityData("Tiger", 4, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityTiger.class, 6, 1, 2), new ArrayList(
/* 338 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.PLAINS }))));
/* 339 */       MoCreatures.mocEntityMap.put("Turkey", new MoCEntityData("Turkey", 2, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityTurkey.class, 8, 1, 2), new ArrayList(
/* 340 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.PLAINS }))));
/* 341 */       MoCreatures.mocEntityMap.put("Turtle", new MoCEntityData("Turtle", 3, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityTurtle.class, 6, 1, 2), new ArrayList(
/* 342 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.SWAMP }))));
/* 343 */       MoCreatures.mocEntityMap.put("WildHorse", new MoCEntityData("WildHorse", 4, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityHorse.class, 8, 1, 4), new ArrayList(
/* 344 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS }))));
/* 345 */       MoCreatures.mocEntityMap.put("Wyvern", new MoCEntityData("Wyvern", 3, EnumCreatureType.CREATURE, new Biome.SpawnListEntry(MoCEntityWyvern.class, 8, 1, 3), new ArrayList()));
/*     */ 
/*     */       
/* 348 */       MoCreatures.mocEntityMap.put("Bass", new MoCEntityData("Bass", 4, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityBass.class, 10, 1, 4), new ArrayList(
/* 349 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.RIVER }))));
/* 350 */       MoCreatures.mocEntityMap.put("Cod", new MoCEntityData("Cod", 4, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityCod.class, 10, 1, 4), new ArrayList(
/* 351 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.RIVER }))));
/* 352 */       MoCreatures.mocEntityMap.put("Dolphin", new MoCEntityData("Dolphin", 3, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityDolphin.class, 6, 2, 4), new ArrayList(
/* 353 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.RIVER }))));
/* 354 */       MoCreatures.mocEntityMap.put("Fishy", new MoCEntityData("Fishy", 6, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityFishy.class, 12, 1, 6), new ArrayList(
/* 355 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.RIVER }))));
/* 356 */       MoCreatures.mocEntityMap.put("JellyFish", new MoCEntityData("JellyFish", 4, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityJellyFish.class, 8, 1, 4), new ArrayList(
/* 357 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.RIVER }))));
/* 358 */       MoCreatures.mocEntityMap.put("Salmon", new MoCEntityData("Salmon", 4, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntitySalmon.class, 10, 1, 4), new ArrayList(
/* 359 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.RIVER }))));
/* 360 */       MoCreatures.mocEntityMap.put("Piranha", new MoCEntityData("Piranha", 4, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityPiranha.class, 4, 1, 3), new ArrayList(
/* 361 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.RIVER }))));
/* 362 */       MoCreatures.mocEntityMap.put("MantaRay", new MoCEntityData("MantaRay", 3, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityMantaRay.class, 10, 1, 2), new ArrayList(
/* 363 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.OCEAN }))));
/* 364 */       MoCreatures.mocEntityMap.put("StingRay", new MoCEntityData("StingRay", 3, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityStingRay.class, 10, 1, 2), new ArrayList(
/* 365 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WATER, BiomeDictionary.Type.RIVER }))));
/* 366 */       MoCreatures.mocEntityMap.put("Shark", new MoCEntityData("Shark", 3, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityShark.class, 6, 1, 2), new ArrayList(
/* 367 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.RIVER }))));
/* 368 */       MoCreatures.mocEntityMap.put("Anchovy", new MoCEntityData("Anchovy", 6, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityAnchovy.class, 12, 1, 6), new ArrayList(
/* 369 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.RIVER }))));
/* 370 */       MoCreatures.mocEntityMap.put("AngelFish", new MoCEntityData("AngelFish", 6, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityAngelFish.class, 12, 1, 6), new ArrayList(
/* 371 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.RIVER }))));
/* 372 */       MoCreatures.mocEntityMap.put("Angler", new MoCEntityData("Angler", 6, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityAngler.class, 12, 1, 6), new ArrayList(
/* 373 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN }))));
/* 374 */       MoCreatures.mocEntityMap.put("ClownFish", new MoCEntityData("ClownFish", 6, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityClownFish.class, 12, 1, 6), new ArrayList(
/* 375 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN }))));
/* 376 */       MoCreatures.mocEntityMap.put("GoldFish", new MoCEntityData("GoldFish", 6, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityGoldFish.class, 12, 1, 6), new ArrayList(
/* 377 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WATER, BiomeDictionary.Type.RIVER }))));
/* 378 */       MoCreatures.mocEntityMap.put("HippoTang", new MoCEntityData("HippoTang", 6, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityHippoTang.class, 12, 1, 6), new ArrayList(
/* 379 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN }))));
/* 380 */       MoCreatures.mocEntityMap.put("Manderin", new MoCEntityData("Manderin", 6, EnumCreatureType.WATER_CREATURE, new Biome.SpawnListEntry(MoCEntityManderin.class, 12, 1, 6), new ArrayList(
/* 381 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.BEACH, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WATER, BiomeDictionary.Type.OCEAN }))));
/*     */ 
/*     */       
/* 384 */       MoCreatures.mocEntityMap.put("BigGolem", new MoCEntityData("BigGolem", 1, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntityGolem.class, 3, 1, 1), new ArrayList(
/*     */ 
/*     */               
/* 387 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND }))));
/*     */       
/* 389 */       MoCreatures.mocEntityMap.put("FlameWraith", new MoCEntityData("FlameWraith", 3, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntityFlameWraith.class, 5, 1, 2), new ArrayList(
/*     */ 
/*     */               
/* 392 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { 
/*     */                   BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.NETHER, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, 
/* 394 */                   BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY })))); MoCreatures.mocEntityMap.put("HellRat", new MoCEntityData("HellRat", 4, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntityHellRat.class, 6, 1, 4), new ArrayList(
/* 395 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.NETHER, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY }))));
/* 396 */       MoCreatures.mocEntityMap.put("HorseMob", new MoCEntityData("HorseMob", 3, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntityHorseMob.class, 8, 1, 3), new ArrayList(
/*     */ 
/*     */               
/* 399 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { 
/*     */                   BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.NETHER, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, 
/* 401 */                   BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY })))); MoCreatures.mocEntityMap.put("MiniGolem", new MoCEntityData("MiniGolem", 2, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntityMiniGolem.class, 6, 1, 3), new ArrayList(
/*     */ 
/*     */               
/* 404 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND }))));
/*     */       
/* 406 */       MoCreatures.mocEntityMap.put("GreenOgre", new MoCEntityData("GreenOgre", 3, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntityGreenOgre.class, 8, 1, 2), new ArrayList(
/*     */               
/* 408 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { 
/*     */                   BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.DEAD, 
/*     */                   BiomeDictionary.Type.SPOOKY }))));
/* 411 */       MoCreatures.mocEntityMap.put("FireOgre", new MoCEntityData("FireOgre", 3, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntityFireOgre.class, 6, 1, 2), new ArrayList(
/*     */               
/* 413 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { 
/*     */                   BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.NETHER, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND, 
/*     */                   BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY }))));
/* 416 */       MoCreatures.mocEntityMap.put("CaveOgre", new MoCEntityData("CaveOgre", 3, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntityCaveOgre.class, 5, 1, 2), new ArrayList(
/*     */               
/* 418 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { 
/*     */                   BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND, 
/*     */                   BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY }))));
/* 421 */       MoCreatures.mocEntityMap.put("Rat", new MoCEntityData("Rat", 2, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntityRat.class, 7, 1, 2), new ArrayList(
/*     */               
/* 423 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND }))));
/*     */       
/* 425 */       MoCreatures.mocEntityMap.put("Scorpion", new MoCEntityData("Scorpion", 3, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntityScorpion.class, 6, 1, 3), new ArrayList(
/*     */ 
/*     */               
/* 428 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { 
/*     */                   BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.NETHER, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, 
/* 430 */                   BiomeDictionary.Type.WASTELAND })))); MoCreatures.mocEntityMap.put("SilverSkeleton", new MoCEntityData("SilverSkeleton", 4, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntitySilverSkeleton.class, 6, 1, 4), new ArrayList(
/*     */ 
/*     */               
/* 433 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { 
/*     */                   BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND, 
/* 435 */                   BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY })))); MoCreatures.mocEntityMap.put("Werewolf", new MoCEntityData("Werewolf", 3, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntityWerewolf.class, 8, 1, 4), new ArrayList(
/*     */ 
/*     */               
/* 438 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND }))));
/*     */       
/* 440 */       MoCreatures.mocEntityMap.put("Wraith", new MoCEntityData("Wraith", 3, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntityWraith.class, 6, 1, 4), new ArrayList(
/*     */ 
/*     */               
/* 443 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { 
/*     */                   BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND, 
/* 445 */                   BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY })))); MoCreatures.mocEntityMap.put("WWolf", new MoCEntityData("WWolf", 3, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntityWWolf.class, 8, 1, 3), new ArrayList(
/*     */ 
/*     */               
/* 448 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND }))));
/*     */       
/* 450 */       MoCreatures.mocEntityMap.put("Manticore", new MoCEntityData("Manticore", 3, EnumCreatureType.MONSTER, new Biome.SpawnListEntry(MoCEntityManticore.class, 8, 1, 3), new ArrayList(
/*     */ 
/*     */               
/* 453 */               Arrays.asList((Object[])new BiomeDictionary.Type[] { 
/*     */                   BiomeDictionary.Type.SANDY, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.NETHER, 
/*     */                   BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY }))));
/* 456 */       for (MoCEntityData entityData : MoCreatures.mocEntityMap.values()) {
/* 457 */         if (entityData.getEntityName().equals("Wyvern")) {
/*     */           continue;
/*     */         }
/* 460 */         Biome.SpawnListEntry spawnEntry = entityData.getSpawnListEntry();
/* 461 */         for (BiomeDictionary.Type type : entityData.getBiomeTypes()) {
/* 462 */           for (Biome biome : BiomeDictionary.getBiomes(type)) {
/* 463 */             if (!biome.getSpawnableList(entityData.getType()).contains(spawnEntry)) {
/* 464 */               biome.getSpawnableList(entityData.getType()).add(spawnEntry);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 469 */       MoCreatures.LOGGER.info("Entity registration complete.");
/* 470 */       MoCreatures.proxy.readMocConfigValues();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\init\MoCEntities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */