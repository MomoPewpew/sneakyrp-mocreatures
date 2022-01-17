/*     */ package drzhark.mocreatures.init;
/*     */ 
/*     */ import net.minecraft.init.Bootstrap;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraftforge.event.RegistryEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ObjectHolder("mocreatures")
/*     */ public class MoCSoundEvents
/*     */ {
/*     */   private static SoundEvent createSoundEvent(String soundName) {
/* 192 */     ResourceLocation soundID = new ResourceLocation("mocreatures", soundName);
/* 193 */     return (SoundEvent)(new SoundEvent(soundID)).setRegistryName(soundID);
/*     */   }
/*     */   
/*     */   @EventBusSubscriber(modid = "mocreatures")
/*     */   public static class RegistrationHandler {
/*     */     @SubscribeEvent
/*     */     public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
/* 200 */       event.getRegistry().registerAll((IForgeRegistryEntry[])new SoundEvent[] { MoCSoundEvents.ENTITY_BEAR_AMBIENT, MoCSoundEvents.ENTITY_BEAR_DEATH, MoCSoundEvents.ENTITY_BEAR_HURT, MoCSoundEvents.ENTITY_BEE_AMBIENT, MoCSoundEvents.ENTITY_BEE_UPSET, MoCSoundEvents.ENTITY_BIRD_AMBIENT_BLACK, MoCSoundEvents.ENTITY_BIRD_AMBIENT_BLUE, MoCSoundEvents.ENTITY_BIRD_AMBIENT_GREEN, MoCSoundEvents.ENTITY_BIRD_AMBIENT_RED, MoCSoundEvents.ENTITY_BIRD_AMBIENT_YELLOW, MoCSoundEvents.ENTITY_BIRD_AMBIENT_WHITE, MoCSoundEvents.ENTITY_BIRD_DEATH, MoCSoundEvents.ENTITY_BIRD_HURT, MoCSoundEvents.ENTITY_CRICKET_AMBIENT, MoCSoundEvents.ENTITY_CRICKET_FLY, MoCSoundEvents.ENTITY_CROCODILE_AMBIENT, MoCSoundEvents.ENTITY_CROCODILE_DEATH, MoCSoundEvents.ENTITY_CROCODILE_HURT, MoCSoundEvents.ENTITY_CROCODILE_JAWSNAP, MoCSoundEvents.ENTITY_CROCODILE_RESTING, MoCSoundEvents.ENTITY_CROCODILE_ROLL, MoCSoundEvents.ENTITY_DEER_AMBIENT_BABY, MoCSoundEvents.ENTITY_DEER_AMBIENT, MoCSoundEvents.ENTITY_DEER_DEATH, MoCSoundEvents.ENTITY_DEER_HURT, MoCSoundEvents.ENTITY_DOLPHIN_AMBIENT, MoCSoundEvents.ENTITY_DOLPHIN_DEATH, MoCSoundEvents.ENTITY_DOLPHIN_HURT, MoCSoundEvents.ENTITY_DOLPHIN_UPSET, MoCSoundEvents.ENTITY_DUCK_AMBIENT, MoCSoundEvents.ENTITY_DUCK_DEATH, MoCSoundEvents.ENTITY_DUCK_HURT, MoCSoundEvents.ENTITY_DRAGONFLY_AMBIENT, MoCSoundEvents.ENTITY_ENT_AMBIENT, MoCSoundEvents.ENTITY_ENT_DEATH, MoCSoundEvents.ENTITY_ENT_HURT, MoCSoundEvents.ENTITY_ELEPHANT_AMBIENT_BABY, MoCSoundEvents.ENTITY_ELEPHANT_AMBIENT, MoCSoundEvents.ENTITY_ELEPHANT_DEATH, MoCSoundEvents.ENTITY_ELEPHANT_HURT, MoCSoundEvents.ENTITY_FLY_AMBIENT, MoCSoundEvents.ENTITY_FOX_AMBIENT, MoCSoundEvents.ENTITY_FOX_DEATH, MoCSoundEvents.ENTITY_FOX_HURT, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF, MoCSoundEvents.ENTITY_GENERIC_DESTROY, MoCSoundEvents.ENTITY_GENERIC_DRINKING, MoCSoundEvents.ENTITY_GENERIC_EATING, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR, MoCSoundEvents.ENTITY_GENERIC_ROPING, MoCSoundEvents.ENTITY_GENERIC_TRANSFORM, MoCSoundEvents.ENTITY_GENERIC_TUD, MoCSoundEvents.ENTITY_GENERIC_VANISH, MoCSoundEvents.ENTITY_GENERIC_WHIP, MoCSoundEvents.ENTITY_GENERIC_WINGFLAP, MoCSoundEvents.ENTITY_GOAT_AMBIENT, MoCSoundEvents.ENTITY_GOAT_AMBIENT_BABY, MoCSoundEvents.ENTITY_GOAT_AMBIENT_FEMALE, MoCSoundEvents.ENTITY_GOAT_DEATH, MoCSoundEvents.ENTITY_GOAT_HURT, MoCSoundEvents.ENTITY_GOAT_DIGG, MoCSoundEvents.ENTITY_GOAT_EATING, MoCSoundEvents.ENTITY_GOAT_SMACK, MoCSoundEvents.ENTITY_GOLEM_AMBIENT, MoCSoundEvents.ENTITY_GOLEM_ATTACH, MoCSoundEvents.ENTITY_GOLEM_DYING, MoCSoundEvents.ENTITY_GOLEM_EXPLODE, MoCSoundEvents.ENTITY_GOLEM_HURT, MoCSoundEvents.ENTITY_GOLEM_SHOOT, MoCSoundEvents.ENTITY_GOLEM_WALK, MoCSoundEvents.ENTITY_HORSE_MAD, MoCSoundEvents.ENTITY_HORSE_AMBIENT, MoCSoundEvents.ENTITY_HORSE_AMBIENT_GHOST, MoCSoundEvents.ENTITY_HORSE_AMBIENT_UNDEAD, MoCSoundEvents.ENTITY_HORSE_AMBIENT_ZEBRA, MoCSoundEvents.ENTITY_HORSE_ANGRY_GHOST, MoCSoundEvents.ENTITY_HORSE_ANGRY_UNDEAD, MoCSoundEvents.ENTITY_HORSE_DEATH, MoCSoundEvents.ENTITY_HORSE_DEATH_DONKEY, MoCSoundEvents.ENTITY_HORSE_DEATH_GHOST, MoCSoundEvents.ENTITY_HORSE_DEATH_UNDEAD, MoCSoundEvents.ENTITY_HORSE_HURT, MoCSoundEvents.ENTITY_HORSE_HURT_DONKEY, MoCSoundEvents.ENTITY_HORSE_HURT_GHOST, MoCSoundEvents.ENTITY_HORSE_HURT_UNDEAD, MoCSoundEvents.ENTITY_HORSE_HURT_ZEBRA, MoCSoundEvents.ENTITY_KITTY_AMBIENT, MoCSoundEvents.ENTITY_KITTY_AMBIENT_BABY, MoCSoundEvents.ENTITY_KITTY_ANGRY, MoCSoundEvents.ENTITY_KITTY_DEATH, MoCSoundEvents.ENTITY_KITTY_DEATH_BABY, MoCSoundEvents.ENTITY_KITTY_DRINKING, MoCSoundEvents.ENTITY_KITTY_EATING, MoCSoundEvents.ENTITY_KITTY_HUNGRY, MoCSoundEvents.ENTITY_KITTY_HURT, MoCSoundEvents.ENTITY_KITTY_HURT_BABY, MoCSoundEvents.ENTITY_KITTY_LITTER, MoCSoundEvents.ENTITY_KITTY_PURR, MoCSoundEvents.ENTITY_KITTY_TRAPPED, MoCSoundEvents.ENTITY_KITTYBED_POURINGFOOD, MoCSoundEvents.ENTITY_KITTYBED_POURINGMILK, MoCSoundEvents.ENTITY_LION_AMBIENT, MoCSoundEvents.ENTITY_LION_AMBIENT_BABY, MoCSoundEvents.ENTITY_LION_DEATH, MoCSoundEvents.ENTITY_LION_DEATH_BABY, MoCSoundEvents.ENTITY_LION_HURT, MoCSoundEvents.ENTITY_LION_HURT_BABY, MoCSoundEvents.ENTITY_MOUSE_AMBIENT, MoCSoundEvents.ENTITY_MOUSE_DEATH, MoCSoundEvents.ENTITY_MOUSE_HURT, MoCSoundEvents.ENTITY_OGRE_AMBIENT, MoCSoundEvents.ENTITY_OGRE_DEATH, MoCSoundEvents.ENTITY_OGRE_HURT, MoCSoundEvents.ENTITY_OSTRICH_AMBIENT, MoCSoundEvents.ENTITY_OSTRICH_AMBIENT_BABY, MoCSoundEvents.ENTITY_OSTRICH_DEATH, MoCSoundEvents.ENTITY_OSTRICH_HURT, MoCSoundEvents.ENTITY_RABBIT_DEATH, MoCSoundEvents.ENTITY_RABBIT_HURT, MoCSoundEvents.ENTITY_RABBIT_LIFT, MoCSoundEvents.ENTITY_RACCOON_AMBIENT, MoCSoundEvents.ENTITY_RACCOON_DEATH, MoCSoundEvents.ENTITY_RACCOON_HURT, MoCSoundEvents.ENTITY_RAT_AMBIENT, MoCSoundEvents.ENTITY_RAT_DEATH, MoCSoundEvents.ENTITY_RAT_HURT, MoCSoundEvents.ENTITY_SCORPION_AMBIENT, MoCSoundEvents.ENTITY_SCORPION_CLAW, MoCSoundEvents.ENTITY_SCORPION_DEATH, MoCSoundEvents.ENTITY_SCORPION_HURT, MoCSoundEvents.ENTITY_SCORPION_STING, MoCSoundEvents.ENTITY_SNAKE_AMBIENT, MoCSoundEvents.ENTITY_SNAKE_ANGRY, MoCSoundEvents.ENTITY_SNAKE_DEATH, MoCSoundEvents.ENTITY_SNAKE_HURT, MoCSoundEvents.ENTITY_SNAKE_RATTLE, MoCSoundEvents.ENTITY_SNAKE_SNAP, MoCSoundEvents.ENTITY_SNAKE_SWIM, MoCSoundEvents.ENTITY_TURKEY_AMBIENT, MoCSoundEvents.ENTITY_TURKEY_HURT, MoCSoundEvents.ENTITY_TURTLE_AMBIENT, MoCSoundEvents.ENTITY_TURTLE_ANGRY, MoCSoundEvents.ENTITY_TURTLE_DEATH, MoCSoundEvents.ENTITY_TURTLE_EATING, MoCSoundEvents.ENTITY_TURTLE_HURT, MoCSoundEvents.ENTITY_WEREWOLF_AMBIENT_HUMAN, MoCSoundEvents.ENTITY_WEREWOLF_DEATH_HUMAN, MoCSoundEvents.ENTITY_WEREWOLF_HURT_HUMAN, MoCSoundEvents.ENTITY_WEREWOLF_AMBIENT, MoCSoundEvents.ENTITY_WEREWOLF_DEATH, MoCSoundEvents.ENTITY_WEREWOLF_HURT, MoCSoundEvents.ENTITY_WEREWOLF_TRANSFORM, MoCSoundEvents.ENTITY_WOLF_AMBIENT, MoCSoundEvents.ENTITY_WOLF_DEATH, MoCSoundEvents.ENTITY_WOLF_HURT, MoCSoundEvents.ENTITY_WRAITH_AMBIENT, MoCSoundEvents.ENTITY_WRAITH_DEATH, MoCSoundEvents.ENTITY_WRAITH_HURT, MoCSoundEvents.ENTITY_WYVERN_AMBIENT, MoCSoundEvents.ENTITY_WYVERN_DEATH, MoCSoundEvents.ENTITY_WYVERN_HURT, MoCSoundEvents.ENTITY_WYVERN_WINGFLAP, MoCSoundEvents.ITEM_RECORD_SHUFFLING });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 375 */     if (!Bootstrap.isRegistered())
/* 376 */       throw new RuntimeException("Accessed Sounds before Bootstrap!"); 
/*     */   }
/* 378 */   public static final SoundEvent ENTITY_BEAR_AMBIENT = createSoundEvent("beargrunt");
/* 379 */   public static final SoundEvent ENTITY_BEAR_DEATH = createSoundEvent("beardeath");
/* 380 */   public static final SoundEvent ENTITY_BEAR_HURT = createSoundEvent("bearhurt");
/* 381 */   public static final SoundEvent ENTITY_BEE_AMBIENT = createSoundEvent("bee");
/* 382 */   public static final SoundEvent ENTITY_BEE_UPSET = createSoundEvent("beeupset");
/* 383 */   public static final SoundEvent ENTITY_BIRD_AMBIENT_BLACK = createSoundEvent("birdblack");
/* 384 */   public static final SoundEvent ENTITY_BIRD_AMBIENT_BLUE = createSoundEvent("birdblue");
/* 385 */   public static final SoundEvent ENTITY_BIRD_AMBIENT_GREEN = createSoundEvent("birdgreen");
/* 386 */   public static final SoundEvent ENTITY_BIRD_AMBIENT_RED = createSoundEvent("birdred"); public static final SoundEvent ENTITY_BIRD_AMBIENT_WHITE;
/* 387 */   public static final SoundEvent ENTITY_BIRD_AMBIENT_YELLOW = createSoundEvent("birdyellow"); static {
/* 388 */     ENTITY_BIRD_AMBIENT_WHITE = createSoundEvent("birdwhite");
/* 389 */     ENTITY_BIRD_DEATH = createSoundEvent("birddying");
/* 390 */     ENTITY_BIRD_HURT = createSoundEvent("birdhurt");
/* 391 */     ENTITY_CRICKET_AMBIENT = createSoundEvent("cricket");
/* 392 */     ENTITY_CRICKET_FLY = createSoundEvent("cricketfly");
/* 393 */     ENTITY_CROCODILE_AMBIENT = createSoundEvent("crocgrunt");
/* 394 */     ENTITY_CROCODILE_DEATH = createSoundEvent("crocdying");
/* 395 */     ENTITY_CROCODILE_HURT = createSoundEvent("crochurt");
/* 396 */     ENTITY_CROCODILE_JAWSNAP = createSoundEvent("crocjawsnap");
/* 397 */     ENTITY_CROCODILE_RESTING = createSoundEvent("crocresting");
/* 398 */     ENTITY_CROCODILE_ROLL = createSoundEvent("crocroll");
/* 399 */     ENTITY_DEER_AMBIENT_BABY = createSoundEvent("deerbgrunt");
/* 400 */     ENTITY_DEER_AMBIENT = createSoundEvent("deerfgrunt");
/* 401 */     ENTITY_DEER_DEATH = createSoundEvent("deerdying");
/* 402 */     ENTITY_DEER_HURT = createSoundEvent("deerhurt");
/* 403 */     ENTITY_DOLPHIN_AMBIENT = createSoundEvent("dolphin");
/* 404 */     ENTITY_DOLPHIN_DEATH = createSoundEvent("dolphindying");
/* 405 */     ENTITY_DOLPHIN_HURT = createSoundEvent("dolphinhurt");
/* 406 */     ENTITY_DOLPHIN_UPSET = createSoundEvent("dolphinupset");
/* 407 */     ENTITY_DUCK_AMBIENT = createSoundEvent("duck");
/* 408 */     ENTITY_DUCK_DEATH = createSoundEvent("duckdying");
/* 409 */     ENTITY_DUCK_HURT = createSoundEvent("duckhurt");
/* 410 */     ENTITY_DRAGONFLY_AMBIENT = createSoundEvent("dragonfly");
/* 411 */     ENTITY_ENT_AMBIENT = createSoundEvent("entgrunt");
/* 412 */     ENTITY_ENT_DEATH = createSoundEvent("entdying");
/* 413 */     ENTITY_ENT_HURT = createSoundEvent("enthurt");
/* 414 */     ENTITY_ELEPHANT_AMBIENT_BABY = createSoundEvent("elephantcalf");
/* 415 */     ENTITY_ELEPHANT_AMBIENT = createSoundEvent("elephantgrunt");
/* 416 */     ENTITY_ELEPHANT_DEATH = createSoundEvent("elephantdying");
/* 417 */     ENTITY_ELEPHANT_HURT = createSoundEvent("elephanthurt");
/* 418 */     ENTITY_FLY_AMBIENT = createSoundEvent("fly");
/* 419 */     ENTITY_FOX_AMBIENT = createSoundEvent("foxcall");
/* 420 */     ENTITY_FOX_DEATH = createSoundEvent("foxdying");
/* 421 */     ENTITY_FOX_HURT = createSoundEvent("foxhurt");
/* 422 */     ENTITY_GENERIC_ARMOR_ON = createSoundEvent("armorput");
/* 423 */     ENTITY_GENERIC_ARMOR_OFF = createSoundEvent("armoroff");
/* 424 */     ENTITY_GENERIC_DESTROY = createSoundEvent("destroy");
/* 425 */     ENTITY_GENERIC_DRINKING = createSoundEvent("drinking");
/* 426 */     ENTITY_GENERIC_EATING = createSoundEvent("eating");
/* 427 */     ENTITY_GENERIC_MAGIC_APPEAR = createSoundEvent("appearmagic");
/* 428 */     ENTITY_GENERIC_ROPING = createSoundEvent("roping");
/* 429 */     ENTITY_GENERIC_TRANSFORM = createSoundEvent("transform");
/* 430 */     ENTITY_GENERIC_TUD = createSoundEvent("tud");
/* 431 */     ENTITY_GENERIC_VANISH = createSoundEvent("vanish");
/* 432 */     ENTITY_GENERIC_WHIP = createSoundEvent("whip");
/* 433 */     ENTITY_GENERIC_WINGFLAP = createSoundEvent("wingflap");
/* 434 */     ENTITY_GOAT_AMBIENT = createSoundEvent("goatgrunt");
/* 435 */     ENTITY_GOAT_AMBIENT_BABY = createSoundEvent("goatkid");
/* 436 */     ENTITY_GOAT_AMBIENT_FEMALE = createSoundEvent("goatfemale");
/* 437 */     ENTITY_GOAT_DEATH = createSoundEvent("goatdying");
/* 438 */     ENTITY_GOAT_HURT = createSoundEvent("goathurt");
/* 439 */     ENTITY_GOAT_DIGG = createSoundEvent("goatdigg");
/* 440 */     ENTITY_GOAT_EATING = createSoundEvent("goateating");
/* 441 */     ENTITY_GOAT_SMACK = createSoundEvent("goatsmack");
/* 442 */     ENTITY_GOLEM_AMBIENT = createSoundEvent("golemgrunt");
/* 443 */     ENTITY_GOLEM_ATTACH = createSoundEvent("golemattach");
/* 444 */     ENTITY_GOLEM_DYING = createSoundEvent("golemdying");
/* 445 */     ENTITY_GOLEM_EXPLODE = createSoundEvent("golemexplode");
/* 446 */     ENTITY_GOLEM_HURT = createSoundEvent("golemhurt");
/* 447 */     ENTITY_GOLEM_SHOOT = createSoundEvent("golemshoot");
/* 448 */     ENTITY_GOLEM_WALK = createSoundEvent("golemwalk");
/* 449 */     ENTITY_HORSE_MAD = createSoundEvent("horsemad");
/* 450 */     ENTITY_HORSE_AMBIENT = createSoundEvent("horsegrunt");
/*     */     
/* 452 */     ENTITY_HORSE_AMBIENT_GHOST = createSoundEvent("horsegruntghost");
/* 453 */     ENTITY_HORSE_AMBIENT_UNDEAD = createSoundEvent("horsegruntundead");
/* 454 */     ENTITY_HORSE_AMBIENT_ZEBRA = createSoundEvent("zebragrunt");
/*     */ 
/*     */     
/* 457 */     ENTITY_HORSE_ANGRY_GHOST = createSoundEvent("horsemadghost");
/* 458 */     ENTITY_HORSE_ANGRY_UNDEAD = createSoundEvent("horsemadundead");
/*     */     
/* 460 */     ENTITY_HORSE_DEATH = createSoundEvent("horsedying");
/* 461 */     ENTITY_HORSE_DEATH_DONKEY = createSoundEvent("donkeydying");
/* 462 */     ENTITY_HORSE_DEATH_GHOST = createSoundEvent("horsedyingghost");
/* 463 */     ENTITY_HORSE_DEATH_UNDEAD = createSoundEvent("horsedyingundead");
/* 464 */     ENTITY_HORSE_HURT = createSoundEvent("horsehurt");
/* 465 */     ENTITY_HORSE_HURT_DONKEY = createSoundEvent("donkeyhurt");
/* 466 */     ENTITY_HORSE_HURT_GHOST = createSoundEvent("horsehurtghost");
/* 467 */     ENTITY_HORSE_HURT_UNDEAD = createSoundEvent("horsehurtundead");
/* 468 */     ENTITY_HORSE_HURT_ZEBRA = createSoundEvent("zebrahurt");
/* 469 */     ENTITY_KITTY_AMBIENT = createSoundEvent("kittygrunt");
/* 470 */     ENTITY_KITTY_AMBIENT_BABY = createSoundEvent("kittengrunt");
/* 471 */     ENTITY_KITTY_ANGRY = createSoundEvent("kittyupset");
/* 472 */     ENTITY_KITTY_DEATH = createSoundEvent("kittydying");
/* 473 */     ENTITY_KITTY_DEATH_BABY = createSoundEvent("kittendying");
/* 474 */     ENTITY_KITTY_DRINKING = createSoundEvent("kittyeatingm");
/* 475 */     ENTITY_KITTY_EATING = createSoundEvent("kittyfood");
/* 476 */     ENTITY_KITTY_HUNGRY = createSoundEvent("kittyeatingf");
/* 477 */     ENTITY_KITTY_HURT = createSoundEvent("kittyhurt");
/* 478 */     ENTITY_KITTY_HURT_BABY = createSoundEvent("kittenhurt");
/* 479 */     ENTITY_KITTY_LITTER = createSoundEvent("kittylitter");
/* 480 */     ENTITY_KITTY_PURR = createSoundEvent("kittypurr");
/* 481 */     ENTITY_KITTY_TRAPPED = createSoundEvent("kittytrapped");
/* 482 */     ENTITY_KITTYBED_POURINGFOOD = createSoundEvent("pouringfood");
/* 483 */     ENTITY_KITTYBED_POURINGMILK = createSoundEvent("pouringmilk");
/* 484 */     ENTITY_LION_AMBIENT = createSoundEvent("liongrunt");
/* 485 */     ENTITY_LION_AMBIENT_BABY = createSoundEvent("cubgrunt");
/* 486 */     ENTITY_LION_DEATH = createSoundEvent("liondeath");
/* 487 */     ENTITY_LION_DEATH_BABY = createSoundEvent("cubdying");
/* 488 */     ENTITY_LION_HURT = createSoundEvent("lionhurt");
/* 489 */     ENTITY_LION_HURT_BABY = createSoundEvent("cubhurt");
/* 490 */     ENTITY_MOUSE_AMBIENT = createSoundEvent("micegrunt");
/* 491 */     ENTITY_MOUSE_DEATH = createSoundEvent("micedying");
/* 492 */     ENTITY_MOUSE_HURT = createSoundEvent("micehurt");
/* 493 */     ENTITY_OGRE_AMBIENT = createSoundEvent("ogre");
/* 494 */     ENTITY_OGRE_DEATH = createSoundEvent("ogredying");
/* 495 */     ENTITY_OGRE_HURT = createSoundEvent("ogrehurt");
/* 496 */     ENTITY_OSTRICH_AMBIENT = createSoundEvent("ostrichgrunt");
/* 497 */     ENTITY_OSTRICH_AMBIENT_BABY = createSoundEvent("ostrichchick");
/* 498 */     ENTITY_OSTRICH_DEATH = createSoundEvent("ostrichdying");
/* 499 */     ENTITY_OSTRICH_HURT = createSoundEvent("ostrichhurt");
/* 500 */     ENTITY_RABBIT_DEATH = createSoundEvent("rabbitdeath");
/* 501 */     ENTITY_RABBIT_HURT = createSoundEvent("rabbithurt");
/* 502 */     ENTITY_RABBIT_LIFT = createSoundEvent("rabbitlift");
/* 503 */     ENTITY_RACCOON_AMBIENT = createSoundEvent("raccoongrunt");
/* 504 */     ENTITY_RACCOON_DEATH = createSoundEvent("raccoondying");
/* 505 */     ENTITY_RACCOON_HURT = createSoundEvent("raccoonhurt");
/* 506 */     ENTITY_RAT_AMBIENT = createSoundEvent("ratgrunt");
/* 507 */     ENTITY_RAT_DEATH = createSoundEvent("ratdying");
/* 508 */     ENTITY_RAT_HURT = createSoundEvent("rathurt");
/* 509 */     ENTITY_SCORPION_AMBIENT = createSoundEvent("scorpiongrunt");
/* 510 */     ENTITY_SCORPION_CLAW = createSoundEvent("scorpionclaw");
/* 511 */     ENTITY_SCORPION_DEATH = createSoundEvent("scorpiondying");
/* 512 */     ENTITY_SCORPION_HURT = createSoundEvent("scorpionhurt");
/* 513 */     ENTITY_SCORPION_STING = createSoundEvent("scorpionsting");
/* 514 */     ENTITY_SNAKE_AMBIENT = createSoundEvent("snakehiss");
/* 515 */     ENTITY_SNAKE_ANGRY = createSoundEvent("snakeupset");
/* 516 */     ENTITY_SNAKE_DEATH = createSoundEvent("snakedying");
/* 517 */     ENTITY_SNAKE_HURT = createSoundEvent("snakehurt");
/* 518 */     ENTITY_SNAKE_RATTLE = createSoundEvent("snakerattle");
/* 519 */     ENTITY_SNAKE_SNAP = createSoundEvent("snakesnap");
/* 520 */     ENTITY_SNAKE_SWIM = createSoundEvent("snakeswim");
/* 521 */     ENTITY_TURKEY_AMBIENT = createSoundEvent("turkey");
/*     */     
/* 523 */     ENTITY_TURKEY_HURT = createSoundEvent("turkeyhurt");
/* 524 */     ENTITY_TURTLE_AMBIENT = createSoundEvent("turtlegrunt");
/* 525 */     ENTITY_TURTLE_ANGRY = createSoundEvent("turtlehissing");
/* 526 */     ENTITY_TURTLE_DEATH = createSoundEvent("turtledying");
/* 527 */     ENTITY_TURTLE_EATING = createSoundEvent("turtleeating");
/* 528 */     ENTITY_TURTLE_HURT = createSoundEvent("turtlehurt");
/* 529 */     ENTITY_WEREWOLF_AMBIENT_HUMAN = createSoundEvent("werehumangrunt");
/* 530 */     ENTITY_WEREWOLF_DEATH_HUMAN = createSoundEvent("werehumandying");
/* 531 */     ENTITY_WEREWOLF_HURT_HUMAN = createSoundEvent("werehumanhurt");
/* 532 */     ENTITY_WEREWOLF_AMBIENT = createSoundEvent("werewolfgrunt");
/* 533 */     ENTITY_WEREWOLF_DEATH = createSoundEvent("werewolfdying");
/* 534 */     ENTITY_WEREWOLF_HURT = createSoundEvent("werewolfhurt");
/* 535 */     ENTITY_WEREWOLF_TRANSFORM = createSoundEvent("weretransform");
/* 536 */     ENTITY_WOLF_AMBIENT = createSoundEvent("wolfgrunt");
/* 537 */     ENTITY_WOLF_DEATH = createSoundEvent("wolfdeath");
/* 538 */     ENTITY_WOLF_HURT = createSoundEvent("wolfhurt");
/* 539 */     ENTITY_WRAITH_AMBIENT = createSoundEvent("wraith");
/* 540 */     ENTITY_WRAITH_DEATH = createSoundEvent("wraithdying");
/* 541 */     ENTITY_WRAITH_HURT = createSoundEvent("wraithhurt");
/* 542 */     ENTITY_WYVERN_AMBIENT = createSoundEvent("wyverngrunt");
/* 543 */     ENTITY_WYVERN_DEATH = createSoundEvent("wyverndying");
/* 544 */     ENTITY_WYVERN_HURT = createSoundEvent("wyvernhurt");
/* 545 */     ENTITY_WYVERN_WINGFLAP = createSoundEvent("wyvernwingflap");
/* 546 */     ITEM_RECORD_SHUFFLING = createSoundEvent("shuffling");
/*     */   }
/*     */   
/*     */   public static final SoundEvent ENTITY_BIRD_DEATH;
/*     */   public static final SoundEvent ENTITY_BIRD_HURT;
/*     */   public static final SoundEvent ENTITY_CRICKET_AMBIENT;
/*     */   public static final SoundEvent ENTITY_CRICKET_FLY;
/*     */   public static final SoundEvent ENTITY_CROCODILE_AMBIENT;
/*     */   public static final SoundEvent ENTITY_CROCODILE_DEATH;
/*     */   public static final SoundEvent ENTITY_CROCODILE_HURT;
/*     */   public static final SoundEvent ENTITY_CROCODILE_JAWSNAP;
/*     */   public static final SoundEvent ENTITY_CROCODILE_RESTING;
/*     */   public static final SoundEvent ENTITY_CROCODILE_ROLL;
/*     */   public static final SoundEvent ENTITY_DEER_AMBIENT_BABY;
/*     */   public static final SoundEvent ENTITY_DEER_AMBIENT;
/*     */   public static final SoundEvent ENTITY_DEER_DEATH;
/*     */   public static final SoundEvent ENTITY_DEER_HURT;
/*     */   public static final SoundEvent ENTITY_DOLPHIN_DEATH;
/*     */   public static final SoundEvent ENTITY_DOLPHIN_HURT;
/*     */   public static final SoundEvent ENTITY_DOLPHIN_AMBIENT;
/*     */   public static final SoundEvent ENTITY_DOLPHIN_UPSET;
/*     */   public static final SoundEvent ENTITY_DUCK_AMBIENT;
/*     */   public static final SoundEvent ENTITY_DUCK_DEATH;
/*     */   public static final SoundEvent ENTITY_DUCK_HURT;
/*     */   public static final SoundEvent ENTITY_DRAGONFLY_AMBIENT;
/*     */   public static final SoundEvent ENTITY_ELEPHANT_AMBIENT_BABY;
/*     */   public static final SoundEvent ENTITY_ELEPHANT_AMBIENT;
/*     */   public static final SoundEvent ENTITY_ELEPHANT_DEATH;
/*     */   public static final SoundEvent ENTITY_ELEPHANT_HURT;
/*     */   public static final SoundEvent ENTITY_ENT_AMBIENT;
/*     */   public static final SoundEvent ENTITY_ENT_DEATH;
/*     */   public static final SoundEvent ENTITY_ENT_HURT;
/*     */   public static final SoundEvent ENTITY_FLY_AMBIENT;
/*     */   public static final SoundEvent ENTITY_FOX_AMBIENT;
/*     */   public static final SoundEvent ENTITY_FOX_DEATH;
/*     */   public static final SoundEvent ENTITY_FOX_HURT;
/*     */   public static final SoundEvent ENTITY_GENERIC_ARMOR_ON;
/*     */   public static final SoundEvent ENTITY_GENERIC_ARMOR_OFF;
/*     */   public static final SoundEvent ENTITY_GENERIC_DESTROY;
/*     */   public static final SoundEvent ENTITY_GENERIC_DRINKING;
/*     */   public static final SoundEvent ENTITY_GENERIC_EATING;
/*     */   public static final SoundEvent ENTITY_GENERIC_MAGIC_APPEAR;
/*     */   public static final SoundEvent ENTITY_GENERIC_ROPING;
/*     */   public static final SoundEvent ENTITY_GENERIC_TRANSFORM;
/*     */   public static final SoundEvent ENTITY_GENERIC_TUD;
/*     */   public static final SoundEvent ENTITY_GENERIC_VANISH;
/*     */   public static final SoundEvent ENTITY_GENERIC_WHIP;
/*     */   public static final SoundEvent ENTITY_GENERIC_WINGFLAP;
/*     */   public static final SoundEvent ENTITY_GOAT_AMBIENT;
/*     */   public static final SoundEvent ENTITY_GOAT_AMBIENT_BABY;
/*     */   public static final SoundEvent ENTITY_GOAT_AMBIENT_FEMALE;
/*     */   public static final SoundEvent ENTITY_GOAT_DEATH;
/*     */   public static final SoundEvent ENTITY_GOAT_DIGG;
/*     */   public static final SoundEvent ENTITY_GOAT_EATING;
/*     */   public static final SoundEvent ENTITY_GOAT_HURT;
/*     */   public static final SoundEvent ENTITY_GOAT_SMACK;
/*     */   public static final SoundEvent ENTITY_GOLEM_AMBIENT;
/*     */   public static final SoundEvent ENTITY_GOLEM_ATTACH;
/*     */   public static final SoundEvent ENTITY_GOLEM_DYING;
/*     */   public static final SoundEvent ENTITY_GOLEM_EXPLODE;
/*     */   public static final SoundEvent ENTITY_GOLEM_HURT;
/*     */   public static final SoundEvent ENTITY_GOLEM_SHOOT;
/*     */   public static final SoundEvent ENTITY_GOLEM_WALK;
/*     */   public static final SoundEvent ENTITY_HORSE_MAD;
/*     */   public static final SoundEvent ENTITY_HORSE_AMBIENT;
/*     */   public static final SoundEvent ENTITY_HORSE_AMBIENT_GHOST;
/*     */   public static final SoundEvent ENTITY_HORSE_AMBIENT_UNDEAD;
/*     */   public static final SoundEvent ENTITY_HORSE_AMBIENT_ZEBRA;
/*     */   public static final SoundEvent ENTITY_HORSE_ANGRY_GHOST;
/*     */   public static final SoundEvent ENTITY_HORSE_ANGRY_UNDEAD;
/*     */   public static final SoundEvent ENTITY_HORSE_DEATH;
/*     */   public static final SoundEvent ENTITY_HORSE_DEATH_DONKEY;
/*     */   public static final SoundEvent ENTITY_HORSE_DEATH_GHOST;
/*     */   public static final SoundEvent ENTITY_HORSE_DEATH_UNDEAD;
/*     */   public static final SoundEvent ENTITY_HORSE_HURT;
/*     */   public static final SoundEvent ENTITY_HORSE_HURT_DONKEY;
/*     */   public static final SoundEvent ENTITY_HORSE_HURT_GHOST;
/*     */   public static final SoundEvent ENTITY_HORSE_HURT_UNDEAD;
/*     */   public static final SoundEvent ENTITY_HORSE_HURT_ZEBRA;
/*     */   public static final SoundEvent ENTITY_KITTY_AMBIENT;
/*     */   public static final SoundEvent ENTITY_KITTY_AMBIENT_BABY;
/*     */   public static final SoundEvent ENTITY_KITTY_ANGRY;
/*     */   public static final SoundEvent ENTITY_KITTY_DEATH;
/*     */   public static final SoundEvent ENTITY_KITTY_DEATH_BABY;
/*     */   public static final SoundEvent ENTITY_KITTY_DRINKING;
/*     */   public static final SoundEvent ENTITY_KITTY_EATING;
/*     */   public static final SoundEvent ENTITY_KITTY_HUNGRY;
/*     */   public static final SoundEvent ENTITY_KITTY_HURT;
/*     */   public static final SoundEvent ENTITY_KITTY_HURT_BABY;
/*     */   public static final SoundEvent ENTITY_KITTY_LITTER;
/*     */   public static final SoundEvent ENTITY_KITTY_PURR;
/*     */   public static final SoundEvent ENTITY_KITTY_TRAPPED;
/*     */   public static final SoundEvent ENTITY_KITTYBED_POURINGMILK;
/*     */   public static final SoundEvent ENTITY_KITTYBED_POURINGFOOD;
/*     */   public static final SoundEvent ENTITY_LION_AMBIENT;
/*     */   public static final SoundEvent ENTITY_LION_AMBIENT_BABY;
/*     */   public static final SoundEvent ENTITY_LION_DEATH;
/*     */   public static final SoundEvent ENTITY_LION_DEATH_BABY;
/*     */   public static final SoundEvent ENTITY_LION_HURT;
/*     */   public static final SoundEvent ENTITY_LION_HURT_BABY;
/*     */   public static final SoundEvent ENTITY_MOUSE_AMBIENT;
/*     */   public static final SoundEvent ENTITY_MOUSE_DEATH;
/*     */   public static final SoundEvent ENTITY_MOUSE_HURT;
/*     */   public static final SoundEvent ENTITY_OGRE_AMBIENT;
/*     */   public static final SoundEvent ENTITY_OGRE_DEATH;
/*     */   public static final SoundEvent ENTITY_OGRE_HURT;
/*     */   public static final SoundEvent ENTITY_OSTRICH_AMBIENT;
/*     */   public static final SoundEvent ENTITY_OSTRICH_AMBIENT_BABY;
/*     */   public static final SoundEvent ENTITY_OSTRICH_DEATH;
/*     */   public static final SoundEvent ENTITY_OSTRICH_HURT;
/*     */   public static final SoundEvent ENTITY_RABBIT_DEATH;
/*     */   public static final SoundEvent ENTITY_RABBIT_HURT;
/*     */   public static final SoundEvent ENTITY_RABBIT_LIFT;
/*     */   public static final SoundEvent ENTITY_RACCOON_AMBIENT;
/*     */   public static final SoundEvent ENTITY_RACCOON_DEATH;
/*     */   public static final SoundEvent ENTITY_RACCOON_HURT;
/*     */   public static final SoundEvent ENTITY_RAT_AMBIENT;
/*     */   public static final SoundEvent ENTITY_RAT_DEATH;
/*     */   public static final SoundEvent ENTITY_RAT_HURT;
/*     */   public static final SoundEvent ENTITY_SCORPION_AMBIENT;
/*     */   public static final SoundEvent ENTITY_SCORPION_CLAW;
/*     */   public static final SoundEvent ENTITY_SCORPION_DEATH;
/*     */   public static final SoundEvent ENTITY_SCORPION_HURT;
/*     */   public static final SoundEvent ENTITY_SCORPION_STING;
/*     */   public static final SoundEvent ENTITY_SNAKE_AMBIENT;
/*     */   public static final SoundEvent ENTITY_SNAKE_ANGRY;
/*     */   public static final SoundEvent ENTITY_SNAKE_DEATH;
/*     */   public static final SoundEvent ENTITY_SNAKE_HURT;
/*     */   public static final SoundEvent ENTITY_SNAKE_RATTLE;
/*     */   public static final SoundEvent ENTITY_SNAKE_SNAP;
/*     */   public static final SoundEvent ENTITY_SNAKE_SWIM;
/*     */   public static final SoundEvent ENTITY_TURKEY_AMBIENT;
/*     */   public static final SoundEvent ENTITY_TURKEY_HURT;
/*     */   public static final SoundEvent ENTITY_TURTLE_AMBIENT;
/*     */   public static final SoundEvent ENTITY_TURTLE_ANGRY;
/*     */   public static final SoundEvent ENTITY_TURTLE_DEATH;
/*     */   public static final SoundEvent ENTITY_TURTLE_EATING;
/*     */   public static final SoundEvent ENTITY_TURTLE_HURT;
/*     */   public static final SoundEvent ENTITY_WEREWOLF_AMBIENT;
/*     */   public static final SoundEvent ENTITY_WEREWOLF_AMBIENT_HUMAN;
/*     */   public static final SoundEvent ENTITY_WEREWOLF_DEATH;
/*     */   public static final SoundEvent ENTITY_WEREWOLF_DEATH_HUMAN;
/*     */   public static final SoundEvent ENTITY_WEREWOLF_HURT_HUMAN;
/*     */   public static final SoundEvent ENTITY_WEREWOLF_HURT;
/*     */   public static final SoundEvent ENTITY_WEREWOLF_TRANSFORM;
/*     */   public static final SoundEvent ENTITY_WRAITH_AMBIENT;
/*     */   public static final SoundEvent ENTITY_WOLF_AMBIENT;
/*     */   public static final SoundEvent ENTITY_WOLF_DEATH;
/*     */   public static final SoundEvent ENTITY_WOLF_HURT;
/*     */   public static final SoundEvent ENTITY_WRAITH_DEATH;
/*     */   public static final SoundEvent ENTITY_WRAITH_HURT;
/*     */   public static final SoundEvent ENTITY_WYVERN_AMBIENT;
/*     */   public static final SoundEvent ENTITY_WYVERN_DEATH;
/*     */   public static final SoundEvent ENTITY_WYVERN_HURT;
/*     */   public static final SoundEvent ENTITY_WYVERN_WINGFLAP;
/*     */   public static final SoundEvent ITEM_RECORD_SHUFFLING;
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\init\MoCSoundEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */