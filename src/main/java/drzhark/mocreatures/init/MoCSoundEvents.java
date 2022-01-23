package drzhark.mocreatures.init;

import net.minecraft.init.Bootstrap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistryEntry;

















































































































































































@ObjectHolder("mocreatures")
public class MoCSoundEvents
{
  private static SoundEvent createSoundEvent(String soundName) {
    ResourceLocation soundID = new ResourceLocation("mocreatures", soundName);
    return (SoundEvent)(new SoundEvent(soundID)).setRegistryName(soundID);
  }

  @EventBusSubscriber(modid = "mocreatures")
  public static class RegistrationHandler {
    @SubscribeEvent
    public static void registerSoundEvents(RegistryEvent.Register<SoundEvent> event) {
      event.getRegistry().registerAll(new SoundEvent[] { MoCSoundEvents.ENTITY_BEAR_AMBIENT, MoCSoundEvents.ENTITY_BEAR_DEATH, MoCSoundEvents.ENTITY_BEAR_HURT, MoCSoundEvents.ENTITY_BEE_AMBIENT, MoCSoundEvents.ENTITY_BEE_UPSET, MoCSoundEvents.ENTITY_BIRD_AMBIENT_BLACK, MoCSoundEvents.ENTITY_BIRD_AMBIENT_BLUE, MoCSoundEvents.ENTITY_BIRD_AMBIENT_GREEN, MoCSoundEvents.ENTITY_BIRD_AMBIENT_RED, MoCSoundEvents.ENTITY_BIRD_AMBIENT_YELLOW, MoCSoundEvents.ENTITY_BIRD_AMBIENT_WHITE, MoCSoundEvents.ENTITY_BIRD_DEATH, MoCSoundEvents.ENTITY_BIRD_HURT, MoCSoundEvents.ENTITY_CRICKET_AMBIENT, MoCSoundEvents.ENTITY_CRICKET_FLY, MoCSoundEvents.ENTITY_CROCODILE_AMBIENT, MoCSoundEvents.ENTITY_CROCODILE_DEATH, MoCSoundEvents.ENTITY_CROCODILE_HURT, MoCSoundEvents.ENTITY_CROCODILE_JAWSNAP, MoCSoundEvents.ENTITY_CROCODILE_RESTING, MoCSoundEvents.ENTITY_CROCODILE_ROLL, MoCSoundEvents.ENTITY_DEER_AMBIENT_BABY, MoCSoundEvents.ENTITY_DEER_AMBIENT, MoCSoundEvents.ENTITY_DEER_DEATH, MoCSoundEvents.ENTITY_DEER_HURT, MoCSoundEvents.ENTITY_DOLPHIN_AMBIENT, MoCSoundEvents.ENTITY_DOLPHIN_DEATH, MoCSoundEvents.ENTITY_DOLPHIN_HURT, MoCSoundEvents.ENTITY_DOLPHIN_UPSET, MoCSoundEvents.ENTITY_DUCK_AMBIENT, MoCSoundEvents.ENTITY_DUCK_DEATH, MoCSoundEvents.ENTITY_DUCK_HURT, MoCSoundEvents.ENTITY_DRAGONFLY_AMBIENT, MoCSoundEvents.ENTITY_ENT_AMBIENT, MoCSoundEvents.ENTITY_ENT_DEATH, MoCSoundEvents.ENTITY_ENT_HURT, MoCSoundEvents.ENTITY_ELEPHANT_AMBIENT_BABY, MoCSoundEvents.ENTITY_ELEPHANT_AMBIENT, MoCSoundEvents.ENTITY_ELEPHANT_DEATH, MoCSoundEvents.ENTITY_ELEPHANT_HURT, MoCSoundEvents.ENTITY_FLY_AMBIENT, MoCSoundEvents.ENTITY_FOX_AMBIENT, MoCSoundEvents.ENTITY_FOX_DEATH, MoCSoundEvents.ENTITY_FOX_HURT, MoCSoundEvents.ENTITY_GENERIC_ARMOR_ON, MoCSoundEvents.ENTITY_GENERIC_ARMOR_OFF, MoCSoundEvents.ENTITY_GENERIC_DESTROY, MoCSoundEvents.ENTITY_GENERIC_DRINKING, MoCSoundEvents.ENTITY_GENERIC_EATING, MoCSoundEvents.ENTITY_GENERIC_MAGIC_APPEAR, MoCSoundEvents.ENTITY_GENERIC_ROPING, MoCSoundEvents.ENTITY_GENERIC_TRANSFORM, MoCSoundEvents.ENTITY_GENERIC_TUD, MoCSoundEvents.ENTITY_GENERIC_VANISH, MoCSoundEvents.ENTITY_GENERIC_WHIP, MoCSoundEvents.ENTITY_GENERIC_WINGFLAP, MoCSoundEvents.ENTITY_GOAT_AMBIENT, MoCSoundEvents.ENTITY_GOAT_AMBIENT_BABY, MoCSoundEvents.ENTITY_GOAT_AMBIENT_FEMALE, MoCSoundEvents.ENTITY_GOAT_DEATH, MoCSoundEvents.ENTITY_GOAT_HURT, MoCSoundEvents.ENTITY_GOAT_DIGG, MoCSoundEvents.ENTITY_GOAT_EATING, MoCSoundEvents.ENTITY_GOAT_SMACK, MoCSoundEvents.ENTITY_GOLEM_AMBIENT, MoCSoundEvents.ENTITY_GOLEM_ATTACH, MoCSoundEvents.ENTITY_GOLEM_DYING, MoCSoundEvents.ENTITY_GOLEM_EXPLODE, MoCSoundEvents.ENTITY_GOLEM_HURT, MoCSoundEvents.ENTITY_GOLEM_SHOOT, MoCSoundEvents.ENTITY_GOLEM_WALK, MoCSoundEvents.ENTITY_HORSE_MAD, MoCSoundEvents.ENTITY_HORSE_AMBIENT, MoCSoundEvents.ENTITY_HORSE_AMBIENT_GHOST, MoCSoundEvents.ENTITY_HORSE_AMBIENT_UNDEAD, MoCSoundEvents.ENTITY_HORSE_AMBIENT_ZEBRA, MoCSoundEvents.ENTITY_HORSE_ANGRY_GHOST, MoCSoundEvents.ENTITY_HORSE_ANGRY_UNDEAD, MoCSoundEvents.ENTITY_HORSE_DEATH, MoCSoundEvents.ENTITY_HORSE_DEATH_DONKEY, MoCSoundEvents.ENTITY_HORSE_DEATH_GHOST, MoCSoundEvents.ENTITY_HORSE_DEATH_UNDEAD, MoCSoundEvents.ENTITY_HORSE_HURT, MoCSoundEvents.ENTITY_HORSE_HURT_DONKEY, MoCSoundEvents.ENTITY_HORSE_HURT_GHOST, MoCSoundEvents.ENTITY_HORSE_HURT_UNDEAD, MoCSoundEvents.ENTITY_HORSE_HURT_ZEBRA, MoCSoundEvents.ENTITY_KITTY_AMBIENT, MoCSoundEvents.ENTITY_KITTY_AMBIENT_BABY, MoCSoundEvents.ENTITY_KITTY_ANGRY, MoCSoundEvents.ENTITY_KITTY_DEATH, MoCSoundEvents.ENTITY_KITTY_DEATH_BABY, MoCSoundEvents.ENTITY_KITTY_DRINKING, MoCSoundEvents.ENTITY_KITTY_EATING, MoCSoundEvents.ENTITY_KITTY_HUNGRY, MoCSoundEvents.ENTITY_KITTY_HURT, MoCSoundEvents.ENTITY_KITTY_HURT_BABY, MoCSoundEvents.ENTITY_KITTY_LITTER, MoCSoundEvents.ENTITY_KITTY_PURR, MoCSoundEvents.ENTITY_KITTY_TRAPPED, MoCSoundEvents.ENTITY_KITTYBED_POURINGFOOD, MoCSoundEvents.ENTITY_KITTYBED_POURINGMILK, MoCSoundEvents.ENTITY_LION_AMBIENT, MoCSoundEvents.ENTITY_LION_AMBIENT_BABY, MoCSoundEvents.ENTITY_LION_DEATH, MoCSoundEvents.ENTITY_LION_DEATH_BABY, MoCSoundEvents.ENTITY_LION_HURT, MoCSoundEvents.ENTITY_LION_HURT_BABY, MoCSoundEvents.ENTITY_MOUSE_AMBIENT, MoCSoundEvents.ENTITY_MOUSE_DEATH, MoCSoundEvents.ENTITY_MOUSE_HURT, MoCSoundEvents.ENTITY_OGRE_AMBIENT, MoCSoundEvents.ENTITY_OGRE_DEATH, MoCSoundEvents.ENTITY_OGRE_HURT, MoCSoundEvents.ENTITY_OSTRICH_AMBIENT, MoCSoundEvents.ENTITY_OSTRICH_AMBIENT_BABY, MoCSoundEvents.ENTITY_OSTRICH_DEATH, MoCSoundEvents.ENTITY_OSTRICH_HURT, MoCSoundEvents.ENTITY_RABBIT_DEATH, MoCSoundEvents.ENTITY_RABBIT_HURT, MoCSoundEvents.ENTITY_RABBIT_LIFT, MoCSoundEvents.ENTITY_RACCOON_AMBIENT, MoCSoundEvents.ENTITY_RACCOON_DEATH, MoCSoundEvents.ENTITY_RACCOON_HURT, MoCSoundEvents.ENTITY_RAT_AMBIENT, MoCSoundEvents.ENTITY_RAT_DEATH, MoCSoundEvents.ENTITY_RAT_HURT, MoCSoundEvents.ENTITY_SCORPION_AMBIENT, MoCSoundEvents.ENTITY_SCORPION_CLAW, MoCSoundEvents.ENTITY_SCORPION_DEATH, MoCSoundEvents.ENTITY_SCORPION_HURT, MoCSoundEvents.ENTITY_SCORPION_STING, MoCSoundEvents.ENTITY_SNAKE_AMBIENT, MoCSoundEvents.ENTITY_SNAKE_ANGRY, MoCSoundEvents.ENTITY_SNAKE_DEATH, MoCSoundEvents.ENTITY_SNAKE_HURT, MoCSoundEvents.ENTITY_SNAKE_RATTLE, MoCSoundEvents.ENTITY_SNAKE_SNAP, MoCSoundEvents.ENTITY_SNAKE_SWIM, MoCSoundEvents.ENTITY_TURKEY_AMBIENT, MoCSoundEvents.ENTITY_TURKEY_HURT, MoCSoundEvents.ENTITY_TURTLE_AMBIENT, MoCSoundEvents.ENTITY_TURTLE_ANGRY, MoCSoundEvents.ENTITY_TURTLE_DEATH, MoCSoundEvents.ENTITY_TURTLE_EATING, MoCSoundEvents.ENTITY_TURTLE_HURT, MoCSoundEvents.ENTITY_WEREWOLF_AMBIENT_HUMAN, MoCSoundEvents.ENTITY_WEREWOLF_DEATH_HUMAN, MoCSoundEvents.ENTITY_WEREWOLF_HURT_HUMAN, MoCSoundEvents.ENTITY_WEREWOLF_AMBIENT, MoCSoundEvents.ENTITY_WEREWOLF_DEATH, MoCSoundEvents.ENTITY_WEREWOLF_HURT, MoCSoundEvents.ENTITY_WEREWOLF_TRANSFORM, MoCSoundEvents.ENTITY_WOLF_AMBIENT, MoCSoundEvents.ENTITY_WOLF_DEATH, MoCSoundEvents.ENTITY_WOLF_HURT, MoCSoundEvents.ENTITY_WRAITH_AMBIENT, MoCSoundEvents.ENTITY_WRAITH_DEATH, MoCSoundEvents.ENTITY_WRAITH_HURT, MoCSoundEvents.ENTITY_WYVERN_AMBIENT, MoCSoundEvents.ENTITY_WYVERN_DEATH, MoCSoundEvents.ENTITY_WYVERN_HURT, MoCSoundEvents.ENTITY_WYVERN_WINGFLAP, MoCSoundEvents.ITEM_RECORD_SHUFFLING });
    }
  }











































































































































































  static {
    if (!Bootstrap.isRegistered())
      throw new RuntimeException("Accessed Sounds before Bootstrap!");
  }
  public static final SoundEvent ENTITY_BEAR_AMBIENT = createSoundEvent("beargrunt");
  public static final SoundEvent ENTITY_BEAR_DEATH = createSoundEvent("beardeath");
  public static final SoundEvent ENTITY_BEAR_HURT = createSoundEvent("bearhurt");
  public static final SoundEvent ENTITY_BEE_AMBIENT = createSoundEvent("bee");
  public static final SoundEvent ENTITY_BEE_UPSET = createSoundEvent("beeupset");
  public static final SoundEvent ENTITY_BIRD_AMBIENT_BLACK = createSoundEvent("birdblack");
  public static final SoundEvent ENTITY_BIRD_AMBIENT_BLUE = createSoundEvent("birdblue");
  public static final SoundEvent ENTITY_BIRD_AMBIENT_GREEN = createSoundEvent("birdgreen");
  public static final SoundEvent ENTITY_BIRD_AMBIENT_RED = createSoundEvent("birdred"); public static final SoundEvent ENTITY_BIRD_AMBIENT_WHITE;
  public static final SoundEvent ENTITY_BIRD_AMBIENT_YELLOW = createSoundEvent("birdyellow"); static {
    ENTITY_BIRD_AMBIENT_WHITE = createSoundEvent("birdwhite");
    ENTITY_BIRD_DEATH = createSoundEvent("birddying");
    ENTITY_BIRD_HURT = createSoundEvent("birdhurt");
    ENTITY_CRICKET_AMBIENT = createSoundEvent("cricket");
    ENTITY_CRICKET_FLY = createSoundEvent("cricketfly");
    ENTITY_CROCODILE_AMBIENT = createSoundEvent("crocgrunt");
    ENTITY_CROCODILE_DEATH = createSoundEvent("crocdying");
    ENTITY_CROCODILE_HURT = createSoundEvent("crochurt");
    ENTITY_CROCODILE_JAWSNAP = createSoundEvent("crocjawsnap");
    ENTITY_CROCODILE_RESTING = createSoundEvent("crocresting");
    ENTITY_CROCODILE_ROLL = createSoundEvent("crocroll");
    ENTITY_DEER_AMBIENT_BABY = createSoundEvent("deerbgrunt");
    ENTITY_DEER_AMBIENT = createSoundEvent("deerfgrunt");
    ENTITY_DEER_DEATH = createSoundEvent("deerdying");
    ENTITY_DEER_HURT = createSoundEvent("deerhurt");
    ENTITY_DOLPHIN_AMBIENT = createSoundEvent("dolphin");
    ENTITY_DOLPHIN_DEATH = createSoundEvent("dolphindying");
    ENTITY_DOLPHIN_HURT = createSoundEvent("dolphinhurt");
    ENTITY_DOLPHIN_UPSET = createSoundEvent("dolphinupset");
    ENTITY_DUCK_AMBIENT = createSoundEvent("duck");
    ENTITY_DUCK_DEATH = createSoundEvent("duckdying");
    ENTITY_DUCK_HURT = createSoundEvent("duckhurt");
    ENTITY_DRAGONFLY_AMBIENT = createSoundEvent("dragonfly");
    ENTITY_ENT_AMBIENT = createSoundEvent("entgrunt");
    ENTITY_ENT_DEATH = createSoundEvent("entdying");
    ENTITY_ENT_HURT = createSoundEvent("enthurt");
    ENTITY_ELEPHANT_AMBIENT_BABY = createSoundEvent("elephantcalf");
    ENTITY_ELEPHANT_AMBIENT = createSoundEvent("elephantgrunt");
    ENTITY_ELEPHANT_DEATH = createSoundEvent("elephantdying");
    ENTITY_ELEPHANT_HURT = createSoundEvent("elephanthurt");
    ENTITY_FLY_AMBIENT = createSoundEvent("fly");
    ENTITY_FOX_AMBIENT = createSoundEvent("foxcall");
    ENTITY_FOX_DEATH = createSoundEvent("foxdying");
    ENTITY_FOX_HURT = createSoundEvent("foxhurt");
    ENTITY_GENERIC_ARMOR_ON = createSoundEvent("armorput");
    ENTITY_GENERIC_ARMOR_OFF = createSoundEvent("armoroff");
    ENTITY_GENERIC_DESTROY = createSoundEvent("destroy");
    ENTITY_GENERIC_DRINKING = createSoundEvent("drinking");
    ENTITY_GENERIC_EATING = createSoundEvent("eating");
    ENTITY_GENERIC_MAGIC_APPEAR = createSoundEvent("appearmagic");
    ENTITY_GENERIC_ROPING = createSoundEvent("roping");
    ENTITY_GENERIC_TRANSFORM = createSoundEvent("transform");
    ENTITY_GENERIC_TUD = createSoundEvent("tud");
    ENTITY_GENERIC_VANISH = createSoundEvent("vanish");
    ENTITY_GENERIC_WHIP = createSoundEvent("whip");
    ENTITY_GENERIC_WINGFLAP = createSoundEvent("wingflap");
    ENTITY_GOAT_AMBIENT = createSoundEvent("goatgrunt");
    ENTITY_GOAT_AMBIENT_BABY = createSoundEvent("goatkid");
    ENTITY_GOAT_AMBIENT_FEMALE = createSoundEvent("goatfemale");
    ENTITY_GOAT_DEATH = createSoundEvent("goatdying");
    ENTITY_GOAT_HURT = createSoundEvent("goathurt");
    ENTITY_GOAT_DIGG = createSoundEvent("goatdigg");
    ENTITY_GOAT_EATING = createSoundEvent("goateating");
    ENTITY_GOAT_SMACK = createSoundEvent("goatsmack");
    ENTITY_GOLEM_AMBIENT = createSoundEvent("golemgrunt");
    ENTITY_GOLEM_ATTACH = createSoundEvent("golemattach");
    ENTITY_GOLEM_DYING = createSoundEvent("golemdying");
    ENTITY_GOLEM_EXPLODE = createSoundEvent("golemexplode");
    ENTITY_GOLEM_HURT = createSoundEvent("golemhurt");
    ENTITY_GOLEM_SHOOT = createSoundEvent("golemshoot");
    ENTITY_GOLEM_WALK = createSoundEvent("golemwalk");
    ENTITY_HORSE_MAD = createSoundEvent("horsemad");
    ENTITY_HORSE_AMBIENT = createSoundEvent("horsegrunt");

    ENTITY_HORSE_AMBIENT_GHOST = createSoundEvent("horsegruntghost");
    ENTITY_HORSE_AMBIENT_UNDEAD = createSoundEvent("horsegruntundead");
    ENTITY_HORSE_AMBIENT_ZEBRA = createSoundEvent("zebragrunt");


    ENTITY_HORSE_ANGRY_GHOST = createSoundEvent("horsemadghost");
    ENTITY_HORSE_ANGRY_UNDEAD = createSoundEvent("horsemadundead");

    ENTITY_HORSE_DEATH = createSoundEvent("horsedying");
    ENTITY_HORSE_DEATH_DONKEY = createSoundEvent("donkeydying");
    ENTITY_HORSE_DEATH_GHOST = createSoundEvent("horsedyingghost");
    ENTITY_HORSE_DEATH_UNDEAD = createSoundEvent("horsedyingundead");
    ENTITY_HORSE_HURT = createSoundEvent("horsehurt");
    ENTITY_HORSE_HURT_DONKEY = createSoundEvent("donkeyhurt");
    ENTITY_HORSE_HURT_GHOST = createSoundEvent("horsehurtghost");
    ENTITY_HORSE_HURT_UNDEAD = createSoundEvent("horsehurtundead");
    ENTITY_HORSE_HURT_ZEBRA = createSoundEvent("zebrahurt");
    ENTITY_KITTY_AMBIENT = createSoundEvent("kittygrunt");
    ENTITY_KITTY_AMBIENT_BABY = createSoundEvent("kittengrunt");
    ENTITY_KITTY_ANGRY = createSoundEvent("kittyupset");
    ENTITY_KITTY_DEATH = createSoundEvent("kittydying");
    ENTITY_KITTY_DEATH_BABY = createSoundEvent("kittendying");
    ENTITY_KITTY_DRINKING = createSoundEvent("kittyeatingm");
    ENTITY_KITTY_EATING = createSoundEvent("kittyfood");
    ENTITY_KITTY_HUNGRY = createSoundEvent("kittyeatingf");
    ENTITY_KITTY_HURT = createSoundEvent("kittyhurt");
    ENTITY_KITTY_HURT_BABY = createSoundEvent("kittenhurt");
    ENTITY_KITTY_LITTER = createSoundEvent("kittylitter");
    ENTITY_KITTY_PURR = createSoundEvent("kittypurr");
    ENTITY_KITTY_TRAPPED = createSoundEvent("kittytrapped");
    ENTITY_KITTYBED_POURINGFOOD = createSoundEvent("pouringfood");
    ENTITY_KITTYBED_POURINGMILK = createSoundEvent("pouringmilk");
    ENTITY_LION_AMBIENT = createSoundEvent("liongrunt");
    ENTITY_LION_AMBIENT_BABY = createSoundEvent("cubgrunt");
    ENTITY_LION_DEATH = createSoundEvent("liondeath");
    ENTITY_LION_DEATH_BABY = createSoundEvent("cubdying");
    ENTITY_LION_HURT = createSoundEvent("lionhurt");
    ENTITY_LION_HURT_BABY = createSoundEvent("cubhurt");
    ENTITY_MOUSE_AMBIENT = createSoundEvent("micegrunt");
    ENTITY_MOUSE_DEATH = createSoundEvent("micedying");
    ENTITY_MOUSE_HURT = createSoundEvent("micehurt");
    ENTITY_OGRE_AMBIENT = createSoundEvent("ogre");
    ENTITY_OGRE_DEATH = createSoundEvent("ogredying");
    ENTITY_OGRE_HURT = createSoundEvent("ogrehurt");
    ENTITY_OSTRICH_AMBIENT = createSoundEvent("ostrichgrunt");
    ENTITY_OSTRICH_AMBIENT_BABY = createSoundEvent("ostrichchick");
    ENTITY_OSTRICH_DEATH = createSoundEvent("ostrichdying");
    ENTITY_OSTRICH_HURT = createSoundEvent("ostrichhurt");
    ENTITY_RABBIT_DEATH = createSoundEvent("rabbitdeath");
    ENTITY_RABBIT_HURT = createSoundEvent("rabbithurt");
    ENTITY_RABBIT_LIFT = createSoundEvent("rabbitlift");
    ENTITY_RACCOON_AMBIENT = createSoundEvent("raccoongrunt");
    ENTITY_RACCOON_DEATH = createSoundEvent("raccoondying");
    ENTITY_RACCOON_HURT = createSoundEvent("raccoonhurt");
    ENTITY_RAT_AMBIENT = createSoundEvent("ratgrunt");
    ENTITY_RAT_DEATH = createSoundEvent("ratdying");
    ENTITY_RAT_HURT = createSoundEvent("rathurt");
    ENTITY_SCORPION_AMBIENT = createSoundEvent("scorpiongrunt");
    ENTITY_SCORPION_CLAW = createSoundEvent("scorpionclaw");
    ENTITY_SCORPION_DEATH = createSoundEvent("scorpiondying");
    ENTITY_SCORPION_HURT = createSoundEvent("scorpionhurt");
    ENTITY_SCORPION_STING = createSoundEvent("scorpionsting");
    ENTITY_SNAKE_AMBIENT = createSoundEvent("snakehiss");
    ENTITY_SNAKE_ANGRY = createSoundEvent("snakeupset");
    ENTITY_SNAKE_DEATH = createSoundEvent("snakedying");
    ENTITY_SNAKE_HURT = createSoundEvent("snakehurt");
    ENTITY_SNAKE_RATTLE = createSoundEvent("snakerattle");
    ENTITY_SNAKE_SNAP = createSoundEvent("snakesnap");
    ENTITY_SNAKE_SWIM = createSoundEvent("snakeswim");
    ENTITY_TURKEY_AMBIENT = createSoundEvent("turkey");

    ENTITY_TURKEY_HURT = createSoundEvent("turkeyhurt");
    ENTITY_TURTLE_AMBIENT = createSoundEvent("turtlegrunt");
    ENTITY_TURTLE_ANGRY = createSoundEvent("turtlehissing");
    ENTITY_TURTLE_DEATH = createSoundEvent("turtledying");
    ENTITY_TURTLE_EATING = createSoundEvent("turtleeating");
    ENTITY_TURTLE_HURT = createSoundEvent("turtlehurt");
    ENTITY_WEREWOLF_AMBIENT_HUMAN = createSoundEvent("werehumangrunt");
    ENTITY_WEREWOLF_DEATH_HUMAN = createSoundEvent("werehumandying");
    ENTITY_WEREWOLF_HURT_HUMAN = createSoundEvent("werehumanhurt");
    ENTITY_WEREWOLF_AMBIENT = createSoundEvent("werewolfgrunt");
    ENTITY_WEREWOLF_DEATH = createSoundEvent("werewolfdying");
    ENTITY_WEREWOLF_HURT = createSoundEvent("werewolfhurt");
    ENTITY_WEREWOLF_TRANSFORM = createSoundEvent("weretransform");
    ENTITY_WOLF_AMBIENT = createSoundEvent("wolfgrunt");
    ENTITY_WOLF_DEATH = createSoundEvent("wolfdeath");
    ENTITY_WOLF_HURT = createSoundEvent("wolfhurt");
    ENTITY_WRAITH_AMBIENT = createSoundEvent("wraith");
    ENTITY_WRAITH_DEATH = createSoundEvent("wraithdying");
    ENTITY_WRAITH_HURT = createSoundEvent("wraithhurt");
    ENTITY_WYVERN_AMBIENT = createSoundEvent("wyverngrunt");
    ENTITY_WYVERN_DEATH = createSoundEvent("wyverndying");
    ENTITY_WYVERN_HURT = createSoundEvent("wyvernhurt");
    ENTITY_WYVERN_WINGFLAP = createSoundEvent("wyvernwingflap");
    ITEM_RECORD_SHUFFLING = createSoundEvent("shuffling");
  }

  public static final SoundEvent ENTITY_BIRD_DEATH;
  public static final SoundEvent ENTITY_BIRD_HURT;
  public static final SoundEvent ENTITY_CRICKET_AMBIENT;
  public static final SoundEvent ENTITY_CRICKET_FLY;
  public static final SoundEvent ENTITY_CROCODILE_AMBIENT;
  public static final SoundEvent ENTITY_CROCODILE_DEATH;
  public static final SoundEvent ENTITY_CROCODILE_HURT;
  public static final SoundEvent ENTITY_CROCODILE_JAWSNAP;
  public static final SoundEvent ENTITY_CROCODILE_RESTING;
  public static final SoundEvent ENTITY_CROCODILE_ROLL;
  public static final SoundEvent ENTITY_DEER_AMBIENT_BABY;
  public static final SoundEvent ENTITY_DEER_AMBIENT;
  public static final SoundEvent ENTITY_DEER_DEATH;
  public static final SoundEvent ENTITY_DEER_HURT;
  public static final SoundEvent ENTITY_DOLPHIN_DEATH;
  public static final SoundEvent ENTITY_DOLPHIN_HURT;
  public static final SoundEvent ENTITY_DOLPHIN_AMBIENT;
  public static final SoundEvent ENTITY_DOLPHIN_UPSET;
  public static final SoundEvent ENTITY_DUCK_AMBIENT;
  public static final SoundEvent ENTITY_DUCK_DEATH;
  public static final SoundEvent ENTITY_DUCK_HURT;
  public static final SoundEvent ENTITY_DRAGONFLY_AMBIENT;
  public static final SoundEvent ENTITY_ELEPHANT_AMBIENT_BABY;
  public static final SoundEvent ENTITY_ELEPHANT_AMBIENT;
  public static final SoundEvent ENTITY_ELEPHANT_DEATH;
  public static final SoundEvent ENTITY_ELEPHANT_HURT;
  public static final SoundEvent ENTITY_ENT_AMBIENT;
  public static final SoundEvent ENTITY_ENT_DEATH;
  public static final SoundEvent ENTITY_ENT_HURT;
  public static final SoundEvent ENTITY_FLY_AMBIENT;
  public static final SoundEvent ENTITY_FOX_AMBIENT;
  public static final SoundEvent ENTITY_FOX_DEATH;
  public static final SoundEvent ENTITY_FOX_HURT;
  public static final SoundEvent ENTITY_GENERIC_ARMOR_ON;
  public static final SoundEvent ENTITY_GENERIC_ARMOR_OFF;
  public static final SoundEvent ENTITY_GENERIC_DESTROY;
  public static final SoundEvent ENTITY_GENERIC_DRINKING;
  public static final SoundEvent ENTITY_GENERIC_EATING;
  public static final SoundEvent ENTITY_GENERIC_MAGIC_APPEAR;
  public static final SoundEvent ENTITY_GENERIC_ROPING;
  public static final SoundEvent ENTITY_GENERIC_TRANSFORM;
  public static final SoundEvent ENTITY_GENERIC_TUD;
  public static final SoundEvent ENTITY_GENERIC_VANISH;
  public static final SoundEvent ENTITY_GENERIC_WHIP;
  public static final SoundEvent ENTITY_GENERIC_WINGFLAP;
  public static final SoundEvent ENTITY_GOAT_AMBIENT;
  public static final SoundEvent ENTITY_GOAT_AMBIENT_BABY;
  public static final SoundEvent ENTITY_GOAT_AMBIENT_FEMALE;
  public static final SoundEvent ENTITY_GOAT_DEATH;
  public static final SoundEvent ENTITY_GOAT_DIGG;
  public static final SoundEvent ENTITY_GOAT_EATING;
  public static final SoundEvent ENTITY_GOAT_HURT;
  public static final SoundEvent ENTITY_GOAT_SMACK;
  public static final SoundEvent ENTITY_GOLEM_AMBIENT;
  public static final SoundEvent ENTITY_GOLEM_ATTACH;
  public static final SoundEvent ENTITY_GOLEM_DYING;
  public static final SoundEvent ENTITY_GOLEM_EXPLODE;
  public static final SoundEvent ENTITY_GOLEM_HURT;
  public static final SoundEvent ENTITY_GOLEM_SHOOT;
  public static final SoundEvent ENTITY_GOLEM_WALK;
  public static final SoundEvent ENTITY_HORSE_MAD;
  public static final SoundEvent ENTITY_HORSE_AMBIENT;
  public static final SoundEvent ENTITY_HORSE_AMBIENT_GHOST;
  public static final SoundEvent ENTITY_HORSE_AMBIENT_UNDEAD;
  public static final SoundEvent ENTITY_HORSE_AMBIENT_ZEBRA;
  public static final SoundEvent ENTITY_HORSE_ANGRY_GHOST;
  public static final SoundEvent ENTITY_HORSE_ANGRY_UNDEAD;
  public static final SoundEvent ENTITY_HORSE_DEATH;
  public static final SoundEvent ENTITY_HORSE_DEATH_DONKEY;
  public static final SoundEvent ENTITY_HORSE_DEATH_GHOST;
  public static final SoundEvent ENTITY_HORSE_DEATH_UNDEAD;
  public static final SoundEvent ENTITY_HORSE_HURT;
  public static final SoundEvent ENTITY_HORSE_HURT_DONKEY;
  public static final SoundEvent ENTITY_HORSE_HURT_GHOST;
  public static final SoundEvent ENTITY_HORSE_HURT_UNDEAD;
  public static final SoundEvent ENTITY_HORSE_HURT_ZEBRA;
  public static final SoundEvent ENTITY_KITTY_AMBIENT;
  public static final SoundEvent ENTITY_KITTY_AMBIENT_BABY;
  public static final SoundEvent ENTITY_KITTY_ANGRY;
  public static final SoundEvent ENTITY_KITTY_DEATH;
  public static final SoundEvent ENTITY_KITTY_DEATH_BABY;
  public static final SoundEvent ENTITY_KITTY_DRINKING;
  public static final SoundEvent ENTITY_KITTY_EATING;
  public static final SoundEvent ENTITY_KITTY_HUNGRY;
  public static final SoundEvent ENTITY_KITTY_HURT;
  public static final SoundEvent ENTITY_KITTY_HURT_BABY;
  public static final SoundEvent ENTITY_KITTY_LITTER;
  public static final SoundEvent ENTITY_KITTY_PURR;
  public static final SoundEvent ENTITY_KITTY_TRAPPED;
  public static final SoundEvent ENTITY_KITTYBED_POURINGMILK;
  public static final SoundEvent ENTITY_KITTYBED_POURINGFOOD;
  public static final SoundEvent ENTITY_LION_AMBIENT;
  public static final SoundEvent ENTITY_LION_AMBIENT_BABY;
  public static final SoundEvent ENTITY_LION_DEATH;
  public static final SoundEvent ENTITY_LION_DEATH_BABY;
  public static final SoundEvent ENTITY_LION_HURT;
  public static final SoundEvent ENTITY_LION_HURT_BABY;
  public static final SoundEvent ENTITY_MOUSE_AMBIENT;
  public static final SoundEvent ENTITY_MOUSE_DEATH;
  public static final SoundEvent ENTITY_MOUSE_HURT;
  public static final SoundEvent ENTITY_OGRE_AMBIENT;
  public static final SoundEvent ENTITY_OGRE_DEATH;
  public static final SoundEvent ENTITY_OGRE_HURT;
  public static final SoundEvent ENTITY_OSTRICH_AMBIENT;
  public static final SoundEvent ENTITY_OSTRICH_AMBIENT_BABY;
  public static final SoundEvent ENTITY_OSTRICH_DEATH;
  public static final SoundEvent ENTITY_OSTRICH_HURT;
  public static final SoundEvent ENTITY_RABBIT_DEATH;
  public static final SoundEvent ENTITY_RABBIT_HURT;
  public static final SoundEvent ENTITY_RABBIT_LIFT;
  public static final SoundEvent ENTITY_RACCOON_AMBIENT;
  public static final SoundEvent ENTITY_RACCOON_DEATH;
  public static final SoundEvent ENTITY_RACCOON_HURT;
  public static final SoundEvent ENTITY_RAT_AMBIENT;
  public static final SoundEvent ENTITY_RAT_DEATH;
  public static final SoundEvent ENTITY_RAT_HURT;
  public static final SoundEvent ENTITY_SCORPION_AMBIENT;
  public static final SoundEvent ENTITY_SCORPION_CLAW;
  public static final SoundEvent ENTITY_SCORPION_DEATH;
  public static final SoundEvent ENTITY_SCORPION_HURT;
  public static final SoundEvent ENTITY_SCORPION_STING;
  public static final SoundEvent ENTITY_SNAKE_AMBIENT;
  public static final SoundEvent ENTITY_SNAKE_ANGRY;
  public static final SoundEvent ENTITY_SNAKE_DEATH;
  public static final SoundEvent ENTITY_SNAKE_HURT;
  public static final SoundEvent ENTITY_SNAKE_RATTLE;
  public static final SoundEvent ENTITY_SNAKE_SNAP;
  public static final SoundEvent ENTITY_SNAKE_SWIM;
  public static final SoundEvent ENTITY_TURKEY_AMBIENT;
  public static final SoundEvent ENTITY_TURKEY_HURT;
  public static final SoundEvent ENTITY_TURTLE_AMBIENT;
  public static final SoundEvent ENTITY_TURTLE_ANGRY;
  public static final SoundEvent ENTITY_TURTLE_DEATH;
  public static final SoundEvent ENTITY_TURTLE_EATING;
  public static final SoundEvent ENTITY_TURTLE_HURT;
  public static final SoundEvent ENTITY_WEREWOLF_AMBIENT;
  public static final SoundEvent ENTITY_WEREWOLF_AMBIENT_HUMAN;
  public static final SoundEvent ENTITY_WEREWOLF_DEATH;
  public static final SoundEvent ENTITY_WEREWOLF_DEATH_HUMAN;
  public static final SoundEvent ENTITY_WEREWOLF_HURT_HUMAN;
  public static final SoundEvent ENTITY_WEREWOLF_HURT;
  public static final SoundEvent ENTITY_WEREWOLF_TRANSFORM;
  public static final SoundEvent ENTITY_WRAITH_AMBIENT;
  public static final SoundEvent ENTITY_WOLF_AMBIENT;
  public static final SoundEvent ENTITY_WOLF_DEATH;
  public static final SoundEvent ENTITY_WOLF_HURT;
  public static final SoundEvent ENTITY_WRAITH_DEATH;
  public static final SoundEvent ENTITY_WRAITH_HURT;
  public static final SoundEvent ENTITY_WYVERN_AMBIENT;
  public static final SoundEvent ENTITY_WYVERN_DEATH;
  public static final SoundEvent ENTITY_WYVERN_HURT;
  public static final SoundEvent ENTITY_WYVERN_WINGFLAP;
  public static final SoundEvent ITEM_RECORD_SHUFFLING;
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\init\MoCSoundEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
