package drzhark.mocreatures.init;

import drzhark.mocreatures.MoCConstants;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.item.ItemBuilderHammer;
import drzhark.mocreatures.item.ItemStaffPortal;
import drzhark.mocreatures.item.ItemStaffTeleport;
import drzhark.mocreatures.item.MoCItem;
import drzhark.mocreatures.item.MoCItemArmor;
import drzhark.mocreatures.item.MoCItemEgg;
import drzhark.mocreatures.item.MoCItemFood;
import drzhark.mocreatures.item.MoCItemHayStack;
import drzhark.mocreatures.item.MoCItemHorseAmulet;
import drzhark.mocreatures.item.MoCItemHorseSaddle;
import drzhark.mocreatures.item.MoCItemKittyBed;
import drzhark.mocreatures.item.MoCItemLitterBox;
import drzhark.mocreatures.item.MoCItemPetAmulet;
import drzhark.mocreatures.item.MoCItemRecord;
import drzhark.mocreatures.item.MoCItemSugarLump;
import drzhark.mocreatures.item.MoCItemSword;
import drzhark.mocreatures.item.MoCItemTurtleSoup;
import drzhark.mocreatures.item.MoCItemWeapon;
import drzhark.mocreatures.item.MoCItemWhip;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;



public class MoCItems
{
  public static final Set<Item> ITEMS = new HashSet<>();

  static ItemArmor.ArmorMaterial scorpARMOR = EnumHelper.addArmorMaterial("crocARMOR", "crocARMOR", 15, new int[] { 2, 6, 5, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
  static ItemArmor.ArmorMaterial furARMOR = EnumHelper.addArmorMaterial("furARMOR", "furARMOR", 15, new int[] { 2, 6, 5, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
  static ItemArmor.ArmorMaterial hideARMOR = EnumHelper.addArmorMaterial("hideARMOR", "hideARMOR", 15, new int[] { 2, 6, 5, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
  static ItemArmor.ArmorMaterial scorpdARMOR = EnumHelper.addArmorMaterial("scorpdARMOR", "scorpdARMOR", 15, new int[] { 2, 6, 5, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
  static ItemArmor.ArmorMaterial scorpfARMOR = EnumHelper.addArmorMaterial("scorpfARMOR", "scorpdARMOR", 18, new int[] { 2, 7, 6, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
  static ItemArmor.ArmorMaterial scorpnARMOR = EnumHelper.addArmorMaterial("scorpnARMOR", "scorpdARMOR", 20, new int[] { 3, 7, 6, 3 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
  static ItemArmor.ArmorMaterial scorpcARMOR = EnumHelper.addArmorMaterial("scorpcARMOR", "scorpdARMOR", 15, new int[] { 2, 6, 5, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
  static ItemArmor.ArmorMaterial silverARMOR = EnumHelper.addArmorMaterial("silverARMOR", "scorpdARMOR", 15, new int[] { 2, 6, 5, 2 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);

  public static final MoCItemRecord recordshuffle = new MoCItemRecord("recordshuffle", MoCSoundEvents.ITEM_RECORD_SHUFFLING);
  public static final MoCItem horsesaddle = (MoCItem)new MoCItemHorseSaddle("horsesaddle");

  public static final MoCItem sharkteeth = new MoCItem("sharkteeth");
  public static final MoCItem haystack = (MoCItem)new MoCItemHayStack("haystack");
  public static final MoCItemSugarLump sugarlump = new MoCItemSugarLump("sugarlump");
  public static final MoCItem mocegg = (MoCItem)new MoCItemEgg("mocegg");
  public static final MoCItem bigcatclaw = new MoCItem("bigcatclaw");
  public static final MoCItem whip = (MoCItem)new MoCItemWhip("whip");
  public static final MoCItem staffPortal = (MoCItem)new ItemStaffPortal("staffportal");
  public static final MoCItem staffTeleport = (MoCItem)new ItemStaffTeleport("staffteleport");

  public static final MoCItem medallion = new MoCItem("medallion");
  public static final MoCItemKittyBed[] kittybed = new MoCItemKittyBed[16];
  public static final MoCItem litterbox = (MoCItem)new MoCItemLitterBox("kittylitter");
  public static final MoCItem woolball = new MoCItem("woolball");

  public static final MoCItem petfood = new MoCItem("petfood");
  public static final MoCItem builderHammer = (MoCItem)new ItemBuilderHammer("builderhammer");

  public static final MoCItem hideCroc = new MoCItem("reptilehide");
  public static final MoCItem fur = new MoCItem("fur");

  public static final MoCItem essencedarkness = new MoCItem("essencedarkness");
  public static final MoCItem essencefire = new MoCItem("essencefire");
  public static final MoCItem essenceundead = new MoCItem("essenceundead");
  public static final MoCItem essencelight = new MoCItem("essencelight");

  public static final MoCItem amuletbone = (MoCItem)new MoCItemHorseAmulet("amuletbone");
  public static final MoCItem amuletbonefull = (MoCItem)new MoCItemHorseAmulet("amuletbonefull");
  public static final MoCItem amuletghost = (MoCItem)new MoCItemHorseAmulet("amuletghost");
  public static final MoCItem amuletghostfull = (MoCItem)new MoCItemHorseAmulet("amuletghostfull");
  public static final MoCItem amuletfairy = (MoCItem)new MoCItemHorseAmulet("amuletfairy");
  public static final MoCItem amuletfairyfull = (MoCItem)new MoCItemHorseAmulet("amuletfairyfull");
  public static final MoCItem amuletpegasus = (MoCItem)new MoCItemHorseAmulet("amuletpegasus");
  public static final MoCItem amuletpegasusfull = (MoCItem)new MoCItemHorseAmulet("amuletpegasusfull");
  public static final MoCItem fishnet = (MoCItem)new MoCItemPetAmulet("fishnet");
  public static final MoCItem fishnetfull = (MoCItem)new MoCItemPetAmulet("fishnetfull");
  public static final MoCItem petamulet = (MoCItem)new MoCItemPetAmulet("petamulet", 1);
  public static final MoCItem petamuletfull = (MoCItem)new MoCItemPetAmulet("petamuletfull", 1);

  public static final MoCItem heartdarkness = new MoCItem("heartdarkness");
  public static final MoCItem heartfire = new MoCItem("heartfire");
  public static final MoCItem heartundead = new MoCItem("heartundead");
  public static final MoCItem unicornhorn = new MoCItem("unicornhorn");

  public static final MoCItem horsearmorcrystal = new MoCItem("horsearmorcrystal");

  public static final MoCItem animalHide = new MoCItem("hide");
  public static final MoCItem chitinCave = new MoCItem("chitinblack");
  public static final MoCItem chitinFrost = new MoCItem("chitinfrost");
  public static final MoCItem chitinNether = new MoCItem("chitinnether");
  public static final MoCItem chitin = new MoCItem("chitin");


  public static final MoCItemSword nunchaku = new MoCItemSword("nunchaku", Item.ToolMaterial.IRON);
  public static final MoCItemSword sai = new MoCItemSword("sai", Item.ToolMaterial.IRON);
  public static final MoCItemSword bo = new MoCItemSword("bo", Item.ToolMaterial.IRON);
  public static final MoCItemSword katana = new MoCItemSword("katana", Item.ToolMaterial.IRON);
  public static final MoCItemSword sharksword = new MoCItemSword("sharksword", Item.ToolMaterial.IRON);
  public static final MoCItemSword silversword = new MoCItemSword("silversword", EnumHelper.addToolMaterial("SILVER", 0, 250, 6.0F, 4.0F, 15));

  public static final MoCItemSword scorpSwordCave = new MoCItemSword("scorpswordcave", Item.ToolMaterial.IRON, 4, false);
  public static final MoCItemSword scorpSwordFrost = new MoCItemSword("scorpswordfrost", Item.ToolMaterial.IRON, 2, false);
  public static final MoCItemSword scorpSwordNether = new MoCItemSword("scorpswordnether", Item.ToolMaterial.IRON, 3, false);
  public static final MoCItemSword scorpSwordDirt = new MoCItemSword("scorpsworddirt", Item.ToolMaterial.IRON, 1, false);
  public static final MoCItemWeapon scorpStingCave = new MoCItemWeapon("scorpstingcave", Item.ToolMaterial.GOLD, 4, true);
  public static final MoCItemWeapon scorpStingFrost = new MoCItemWeapon("scorpstingfrost", Item.ToolMaterial.GOLD, 2, true);
  public static final MoCItemWeapon scorpStingNether = new MoCItemWeapon("scorpstingnether", Item.ToolMaterial.GOLD, 3, true);
  public static final MoCItemWeapon scorpStingDirt = new MoCItemWeapon("scorpstingdirt", Item.ToolMaterial.GOLD, 1, true);

  public static final MoCItemWeapon tusksWood = new MoCItemWeapon("tuskswood", Item.ToolMaterial.WOOD);
  public static final MoCItemWeapon tusksIron = new MoCItemWeapon("tusksiron", Item.ToolMaterial.IRON);
  public static final MoCItemWeapon tusksDiamond = new MoCItemWeapon("tusksdiamond", Item.ToolMaterial.DIAMOND);


  public static final MoCItemArmor plateCroc = new MoCItemArmor("reptileplate", scorpARMOR, 4, EntityEquipmentSlot.CHEST);
  public static final MoCItemArmor helmetCroc = new MoCItemArmor("reptilehelmet", scorpARMOR, 4, EntityEquipmentSlot.HEAD);
  public static final MoCItemArmor legsCroc = new MoCItemArmor("reptilelegs", scorpARMOR, 4, EntityEquipmentSlot.LEGS);
  public static final MoCItemArmor bootsCroc = new MoCItemArmor("reptileboots", scorpARMOR, 4, EntityEquipmentSlot.FEET);

  public static final MoCItemArmor chestFur = new MoCItemArmor("furchest", furARMOR, 4, EntityEquipmentSlot.CHEST);
  public static final MoCItemArmor helmetFur = new MoCItemArmor("furhelmet", furARMOR, 4, EntityEquipmentSlot.HEAD);
  public static final MoCItemArmor legsFur = new MoCItemArmor("furlegs", furARMOR, 4, EntityEquipmentSlot.LEGS);
  public static final MoCItemArmor bootsFur = new MoCItemArmor("furboots", furARMOR, 4, EntityEquipmentSlot.FEET);

  public static final MoCItemArmor chestHide = new MoCItemArmor("hidechest", hideARMOR, 4, EntityEquipmentSlot.CHEST);
  public static final MoCItemArmor helmetHide = new MoCItemArmor("hidehelmet", hideARMOR, 4, EntityEquipmentSlot.HEAD);
  public static final MoCItemArmor legsHide = new MoCItemArmor("hidelegs", hideARMOR, 4, EntityEquipmentSlot.LEGS);
  public static final MoCItemArmor bootsHide = new MoCItemArmor("hideboots", hideARMOR, 4, EntityEquipmentSlot.FEET);

  public static final MoCItemArmor scorpPlateDirt = new MoCItemArmor("scorpplatedirt", scorpARMOR, 4, EntityEquipmentSlot.CHEST);
  public static final MoCItemArmor scorpHelmetDirt = new MoCItemArmor("scorphelmetdirt", scorpARMOR, 4, EntityEquipmentSlot.HEAD);
  public static final MoCItemArmor scorpLegsDirt = new MoCItemArmor("scorplegsdirt", scorpARMOR, 4, EntityEquipmentSlot.LEGS);
  public static final MoCItemArmor scorpBootsDirt = new MoCItemArmor("scorpbootsdirt", scorpARMOR, 4, EntityEquipmentSlot.FEET);

  public static final MoCItemArmor scorpPlateFrost = new MoCItemArmor("scorpplatefrost", scorpARMOR, 4, EntityEquipmentSlot.CHEST);
  public static final MoCItemArmor scorpHelmetFrost = new MoCItemArmor("scorphelmetfrost", scorpARMOR, 4, EntityEquipmentSlot.HEAD);
  public static final MoCItemArmor scorpLegsFrost = new MoCItemArmor("scorplegsfrost", scorpARMOR, 4, EntityEquipmentSlot.LEGS);
  public static final MoCItemArmor scorpBootsFrost = new MoCItemArmor("scorpbootsfrost", scorpARMOR, 4, EntityEquipmentSlot.FEET);

  public static final MoCItemArmor scorpPlateCave = new MoCItemArmor("scorpplatecave", scorpARMOR, 4, EntityEquipmentSlot.CHEST);
  public static final MoCItemArmor scorpHelmetCave = new MoCItemArmor("scorphelmetcave", scorpARMOR, 4, EntityEquipmentSlot.HEAD);
  public static final MoCItemArmor scorpLegsCave = new MoCItemArmor("scorplegscave", scorpARMOR, 4, EntityEquipmentSlot.LEGS);
  public static final MoCItemArmor scorpBootsCave = new MoCItemArmor("scorpbootscave", scorpARMOR, 4, EntityEquipmentSlot.FEET);

  public static final MoCItemArmor scorpPlateNether = new MoCItemArmor("scorpplatenether", scorpARMOR, 4, EntityEquipmentSlot.CHEST);
  public static final MoCItemArmor scorpHelmetNether = new MoCItemArmor("scorphelmetnether", scorpARMOR, 4, EntityEquipmentSlot.HEAD);
  public static final MoCItemArmor scorpLegsNether = new MoCItemArmor("scorplegsnether", scorpARMOR, 4, EntityEquipmentSlot.LEGS);
  public static final MoCItemArmor scorpBootsNether = new MoCItemArmor("scorpbootsnether", scorpARMOR, 4, EntityEquipmentSlot.FEET);

  public static final MoCItem elephantHarness = new MoCItem("elephantharness");
  public static final MoCItem elephantChest = new MoCItem("elephantchest");
  public static final MoCItem elephantGarment = new MoCItem("elephantgarment");
  public static final MoCItem elephantHowdah = new MoCItem("elephanthowdah");
  public static final MoCItem mammothPlatform = new MoCItem("mammothplatform");

  public static final MoCItem scrollFreedom = new MoCItem("scrolloffreedom");
  public static final MoCItem scrollOfSale = new MoCItem("scrollofsale");
  public static final MoCItem scrollOfOwner = new MoCItem("scrollofowner");


  public static final MoCItemFood cookedTurkey = new MoCItemFood("turkeycooked", 8, 0.6F, false);
  public static final MoCItemFood crabraw = (MoCItemFood)(new MoCItemFood("crabraw", 2, 0.3F, false)).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 30, 0), 0.8F);
  public static final MoCItemFood crabcooked = new MoCItemFood("crabcooked", 6, 0.6F, false);
  public static final MoCItemFood omelet = new MoCItemFood("omelet", 4, 0.6F, false);
  public static final MoCItemFood ostrichraw = (MoCItemFood)(new MoCItemFood("ostrichraw", 2, 0.3F, false)).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 30, 0), 0.8F);
  public static final MoCItemFood ostrichcooked = new MoCItemFood("ostrichcooked", 6, 0.6F, false);
  public static final MoCItemFood ratBurger = new MoCItemFood("ratburger", 8, 0.6F, false);
  public static final MoCItemFood ratCooked = new MoCItemFood("ratcooked", 4, 0.6F, false);
  public static final MoCItemFood ratRaw = (MoCItemFood)(new MoCItemFood("ratraw", 2, 0.3F, false)).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 30, 0), 0.8F);
  public static final MoCItemFood rawTurkey = (MoCItemFood)(new MoCItemFood("turkeyraw", 3, 0.3F, false)).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 30, 0), 0.8F);
  public static final MoCItemFood turtleraw = new MoCItemFood("turtleraw", 2, 0.3F, false);
  public static final MoCItemFood turtlesoup = (MoCItemFood)new MoCItemTurtleSoup("turtlesoup", 6, 0.6F, false);





  @EventBusSubscriber(modid = "mocreatures")
  public static class RegistrationHandler
  {
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
      List<Item> items = new ArrayList<>(Arrays.asList(new Item[] { MoCItems.horsesaddle, MoCItems.sharkteeth, MoCItems.haystack, MoCItems.sugarlump, MoCItems.mocegg, MoCItems.bigcatclaw, MoCItems.whip, MoCItems.medallion, MoCItems.litterbox, MoCItems.woolball, MoCItems.petfood, MoCItems.hideCroc, MoCItems.plateCroc, MoCItems.helmetCroc, MoCItems.legsCroc, MoCItems.bootsCroc, MoCItems.fur, MoCItems.omelet, MoCItems.turtleraw, MoCItems.turtlesoup, MoCItems.staffPortal, MoCItems.staffTeleport, MoCItems.builderHammer, MoCItems.nunchaku, MoCItems.sai, MoCItems.bo, MoCItems.katana, MoCItems.sharksword, MoCItems.silversword, MoCItems.essencedarkness, MoCItems.essencefire, MoCItems.essenceundead, MoCItems.essencelight, MoCItems.amuletbone, MoCItems.amuletbonefull, MoCItems.amuletghost, MoCItems.amuletghostfull, MoCItems.amuletfairy, MoCItems.amuletfairyfull, MoCItems.amuletpegasus, MoCItems.amuletpegasusfull, MoCItems.fishnet, MoCItems.fishnetfull, MoCItems.petamulet, MoCItems.petamuletfull, MoCItems.chestFur, MoCItems.helmetFur, MoCItems.legsFur, MoCItems.bootsFur, MoCItems.heartdarkness, MoCItems.heartfire, MoCItems.heartundead, MoCItems.ostrichraw, MoCItems.ostrichcooked, MoCItems.unicornhorn, MoCItems.horsearmorcrystal, MoCItems.recordshuffle, MoCItems.animalHide, MoCItems.rawTurkey, MoCItems.cookedTurkey, MoCItems.chestHide, MoCItems.helmetHide, MoCItems.legsHide, MoCItems.bootsHide, MoCItems.ratRaw, MoCItems.ratCooked, MoCItems.ratBurger, MoCItems.chitinCave, MoCItems.chitinFrost, MoCItems.chitinNether, MoCItems.chitin, MoCItems.scorpSwordDirt, MoCItems.scorpSwordFrost, MoCItems.scorpSwordCave, MoCItems.scorpSwordNether, MoCItems.scorpPlateDirt, MoCItems.scorpHelmetDirt, MoCItems.scorpLegsDirt, MoCItems.scorpBootsDirt, MoCItems.scorpPlateFrost, MoCItems.scorpHelmetFrost, MoCItems.scorpLegsFrost, MoCItems.scorpBootsFrost, MoCItems.scorpPlateNether, MoCItems.scorpHelmetNether, MoCItems.scorpLegsNether, MoCItems.scorpBootsNether, MoCItems.scorpHelmetCave, MoCItems.scorpPlateCave, MoCItems.scorpLegsCave, MoCItems.scorpBootsCave, MoCItems.scorpStingDirt, MoCItems.scorpStingFrost, MoCItems.scorpStingCave, MoCItems.scorpStingNether, MoCItems.tusksWood, MoCItems.tusksIron, MoCItems.tusksDiamond, MoCItems.elephantChest, MoCItems.elephantGarment, MoCItems.elephantHarness, MoCItems.elephantHowdah, MoCItems.mammothPlatform, MoCItems.scrollFreedom, MoCItems.scrollOfSale, MoCItems.scrollOfOwner, MoCItems.crabraw, MoCItems.crabcooked }));


      IForgeRegistry<Item> registry = event.getRegistry();

      for (int i = 0; i < 16; i++) {
        String s = EnumDyeColor.byMetadata(i).getUnlocalizedName().toLowerCase();
        if (s.equalsIgnoreCase("lightBlue")) s = "light_blue";
        MoCItems.kittybed[i] = new MoCItemKittyBed("kittybed_" + s, i);
        registry.register(MoCItems.kittybed[i]);
        if (!MoCreatures.isServer()) {
          ModelLoader.setCustomModelResourceLocation(MoCItems.kittybed[i], 0, new ModelResourceLocation(MoCConstants.MOD_PREFIX + MoCItems.kittybed[i].getUnlocalizedName().replace("item.", ""), "inventory"));
        }
      }

      for (Item item : items) {
        registry.register(item);
        MoCItems.ITEMS.add(item);
        if (!MoCreatures.isServer()) {
          ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(MoCConstants.MOD_PREFIX + item.getUnlocalizedName().replace("item.",  ""), "inventory"));
        }
        if (item instanceof MoCItemEgg)
          for (int j = 0; j < 91; j++) {
            if (!MoCreatures.isServer())
              ModelLoader.setCustomModelResourceLocation(item, j, new ModelResourceLocation(MoCConstants.MOD_PREFIX + "mocegg", "inventory"));
          }
      }
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\init\MoCItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
