/*     */ package drzhark.mocreatures.init;
/*     */ 
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.item.ItemBuilderHammer;
/*     */ import drzhark.mocreatures.item.ItemStaffPortal;
/*     */ import drzhark.mocreatures.item.ItemStaffTeleport;
/*     */ import drzhark.mocreatures.item.MoCItem;
/*     */ import drzhark.mocreatures.item.MoCItemArmor;
/*     */ import drzhark.mocreatures.item.MoCItemEgg;
/*     */ import drzhark.mocreatures.item.MoCItemFood;
/*     */ import drzhark.mocreatures.item.MoCItemHayStack;
/*     */ import drzhark.mocreatures.item.MoCItemHorseAmulet;
/*     */ import drzhark.mocreatures.item.MoCItemHorseSaddle;
/*     */ import drzhark.mocreatures.item.MoCItemKittyBed;
/*     */ import drzhark.mocreatures.item.MoCItemLitterBox;
/*     */ import drzhark.mocreatures.item.MoCItemPetAmulet;
/*     */ import drzhark.mocreatures.item.MoCItemRecord;
/*     */ import drzhark.mocreatures.item.MoCItemSugarLump;
/*     */ import drzhark.mocreatures.item.MoCItemSword;
/*     */ import drzhark.mocreatures.item.MoCItemTurtleSoup;
/*     */ import drzhark.mocreatures.item.MoCItemWeapon;
/*     */ import drzhark.mocreatures.item.MoCItemWhip;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.init.MobEffects;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.inventory.EntityEquipmentSlot;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraftforge.client.model.ModelLoader;
/*     */ import net.minecraftforge.common.util.EnumHelper;
/*     */ import net.minecraftforge.event.RegistryEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.registries.IForgeRegistry;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoCItems
/*     */ {
/*  48 */   public static final Set<Item> ITEMS = new HashSet<>();
/*     */   
/*  50 */   static ItemArmor.ArmorMaterial scorpARMOR = EnumHelper.addArmorMaterial("crocARMOR", "crocARMOR", 15, new int[] { 2, 6, 5, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
/*  51 */   static ItemArmor.ArmorMaterial furARMOR = EnumHelper.addArmorMaterial("furARMOR", "furARMOR", 15, new int[] { 2, 6, 5, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
/*  52 */   static ItemArmor.ArmorMaterial hideARMOR = EnumHelper.addArmorMaterial("hideARMOR", "hideARMOR", 15, new int[] { 2, 6, 5, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
/*  53 */   static ItemArmor.ArmorMaterial scorpdARMOR = EnumHelper.addArmorMaterial("scorpdARMOR", "scorpdARMOR", 15, new int[] { 2, 6, 5, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
/*  54 */   static ItemArmor.ArmorMaterial scorpfARMOR = EnumHelper.addArmorMaterial("scorpfARMOR", "scorpdARMOR", 18, new int[] { 2, 7, 6, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
/*  55 */   static ItemArmor.ArmorMaterial scorpnARMOR = EnumHelper.addArmorMaterial("scorpnARMOR", "scorpdARMOR", 20, new int[] { 3, 7, 6, 3 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
/*  56 */   static ItemArmor.ArmorMaterial scorpcARMOR = EnumHelper.addArmorMaterial("scorpcARMOR", "scorpdARMOR", 15, new int[] { 2, 6, 5, 2 }, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
/*  57 */   static ItemArmor.ArmorMaterial silverARMOR = EnumHelper.addArmorMaterial("silverARMOR", "scorpdARMOR", 15, new int[] { 2, 6, 5, 2 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0F);
/*     */   
/*  59 */   public static final MoCItemRecord recordshuffle = new MoCItemRecord("recordshuffle", MoCSoundEvents.ITEM_RECORD_SHUFFLING);
/*  60 */   public static final MoCItem horsesaddle = (MoCItem)new MoCItemHorseSaddle("horsesaddle");
/*     */   
/*  62 */   public static final MoCItem sharkteeth = new MoCItem("sharkteeth");
/*  63 */   public static final MoCItem haystack = (MoCItem)new MoCItemHayStack("haystack");
/*  64 */   public static final MoCItemSugarLump sugarlump = new MoCItemSugarLump("sugarlump");
/*  65 */   public static final MoCItem mocegg = (MoCItem)new MoCItemEgg("mocegg");
/*  66 */   public static final MoCItem bigcatclaw = new MoCItem("bigcatclaw");
/*  67 */   public static final MoCItem whip = (MoCItem)new MoCItemWhip("whip");
/*  68 */   public static final MoCItem staffPortal = (MoCItem)new ItemStaffPortal("staffportal");
/*  69 */   public static final MoCItem staffTeleport = (MoCItem)new ItemStaffTeleport("staffteleport");
/*     */   
/*  71 */   public static final MoCItem medallion = new MoCItem("medallion");
/*  72 */   public static final MoCItemKittyBed[] kittybed = new MoCItemKittyBed[16];
/*  73 */   public static final MoCItem litterbox = (MoCItem)new MoCItemLitterBox("kittylitter");
/*  74 */   public static final MoCItem woolball = new MoCItem("woolball");
/*     */   
/*  76 */   public static final MoCItem petfood = new MoCItem("petfood");
/*  77 */   public static final MoCItem builderHammer = (MoCItem)new ItemBuilderHammer("builderhammer");
/*     */   
/*  79 */   public static final MoCItem hideCroc = new MoCItem("reptilehide");
/*  80 */   public static final MoCItem fur = new MoCItem("fur");
/*     */   
/*  82 */   public static final MoCItem essencedarkness = new MoCItem("essencedarkness");
/*  83 */   public static final MoCItem essencefire = new MoCItem("essencefire");
/*  84 */   public static final MoCItem essenceundead = new MoCItem("essenceundead");
/*  85 */   public static final MoCItem essencelight = new MoCItem("essencelight");
/*     */   
/*  87 */   public static final MoCItem amuletbone = (MoCItem)new MoCItemHorseAmulet("amuletbone");
/*  88 */   public static final MoCItem amuletbonefull = (MoCItem)new MoCItemHorseAmulet("amuletbonefull");
/*  89 */   public static final MoCItem amuletghost = (MoCItem)new MoCItemHorseAmulet("amuletghost");
/*  90 */   public static final MoCItem amuletghostfull = (MoCItem)new MoCItemHorseAmulet("amuletghostfull");
/*  91 */   public static final MoCItem amuletfairy = (MoCItem)new MoCItemHorseAmulet("amuletfairy");
/*  92 */   public static final MoCItem amuletfairyfull = (MoCItem)new MoCItemHorseAmulet("amuletfairyfull");
/*  93 */   public static final MoCItem amuletpegasus = (MoCItem)new MoCItemHorseAmulet("amuletpegasus");
/*  94 */   public static final MoCItem amuletpegasusfull = (MoCItem)new MoCItemHorseAmulet("amuletpegasusfull");
/*  95 */   public static final MoCItem fishnet = (MoCItem)new MoCItemPetAmulet("fishnet");
/*  96 */   public static final MoCItem fishnetfull = (MoCItem)new MoCItemPetAmulet("fishnetfull");
/*  97 */   public static final MoCItem petamulet = (MoCItem)new MoCItemPetAmulet("petamulet", 1);
/*  98 */   public static final MoCItem petamuletfull = (MoCItem)new MoCItemPetAmulet("petamuletfull", 1);
/*     */   
/* 100 */   public static final MoCItem heartdarkness = new MoCItem("heartdarkness");
/* 101 */   public static final MoCItem heartfire = new MoCItem("heartfire");
/* 102 */   public static final MoCItem heartundead = new MoCItem("heartundead");
/* 103 */   public static final MoCItem unicornhorn = new MoCItem("unicornhorn");
/*     */   
/* 105 */   public static final MoCItem horsearmorcrystal = new MoCItem("horsearmorcrystal");
/*     */   
/* 107 */   public static final MoCItem animalHide = new MoCItem("hide");
/* 108 */   public static final MoCItem chitinCave = new MoCItem("chitinblack");
/* 109 */   public static final MoCItem chitinFrost = new MoCItem("chitinfrost");
/* 110 */   public static final MoCItem chitinNether = new MoCItem("chitinnether");
/* 111 */   public static final MoCItem chitin = new MoCItem("chitin");
/*     */ 
/*     */   
/* 114 */   public static final MoCItemSword nunchaku = new MoCItemSword("nunchaku", Item.ToolMaterial.IRON);
/* 115 */   public static final MoCItemSword sai = new MoCItemSword("sai", Item.ToolMaterial.IRON);
/* 116 */   public static final MoCItemSword bo = new MoCItemSword("bo", Item.ToolMaterial.IRON);
/* 117 */   public static final MoCItemSword katana = new MoCItemSword("katana", Item.ToolMaterial.IRON);
/* 118 */   public static final MoCItemSword sharksword = new MoCItemSword("sharksword", Item.ToolMaterial.IRON);
/* 119 */   public static final MoCItemSword silversword = new MoCItemSword("silversword", EnumHelper.addToolMaterial("SILVER", 0, 250, 6.0F, 4.0F, 15));
/*     */   
/* 121 */   public static final MoCItemSword scorpSwordCave = new MoCItemSword("scorpswordcave", Item.ToolMaterial.IRON, 4, false);
/* 122 */   public static final MoCItemSword scorpSwordFrost = new MoCItemSword("scorpswordfrost", Item.ToolMaterial.IRON, 2, false);
/* 123 */   public static final MoCItemSword scorpSwordNether = new MoCItemSword("scorpswordnether", Item.ToolMaterial.IRON, 3, false);
/* 124 */   public static final MoCItemSword scorpSwordDirt = new MoCItemSword("scorpsworddirt", Item.ToolMaterial.IRON, 1, false);
/* 125 */   public static final MoCItemWeapon scorpStingCave = new MoCItemWeapon("scorpstingcave", Item.ToolMaterial.GOLD, 4, true);
/* 126 */   public static final MoCItemWeapon scorpStingFrost = new MoCItemWeapon("scorpstingfrost", Item.ToolMaterial.GOLD, 2, true);
/* 127 */   public static final MoCItemWeapon scorpStingNether = new MoCItemWeapon("scorpstingnether", Item.ToolMaterial.GOLD, 3, true);
/* 128 */   public static final MoCItemWeapon scorpStingDirt = new MoCItemWeapon("scorpstingdirt", Item.ToolMaterial.GOLD, 1, true);
/*     */   
/* 130 */   public static final MoCItemWeapon tusksWood = new MoCItemWeapon("tuskswood", Item.ToolMaterial.WOOD);
/* 131 */   public static final MoCItemWeapon tusksIron = new MoCItemWeapon("tusksiron", Item.ToolMaterial.IRON);
/* 132 */   public static final MoCItemWeapon tusksDiamond = new MoCItemWeapon("tusksdiamond", Item.ToolMaterial.DIAMOND);
/*     */ 
/*     */   
/* 135 */   public static final MoCItemArmor plateCroc = new MoCItemArmor("reptileplate", scorpARMOR, 4, EntityEquipmentSlot.CHEST);
/* 136 */   public static final MoCItemArmor helmetCroc = new MoCItemArmor("reptilehelmet", scorpARMOR, 4, EntityEquipmentSlot.HEAD);
/* 137 */   public static final MoCItemArmor legsCroc = new MoCItemArmor("reptilelegs", scorpARMOR, 4, EntityEquipmentSlot.LEGS);
/* 138 */   public static final MoCItemArmor bootsCroc = new MoCItemArmor("reptileboots", scorpARMOR, 4, EntityEquipmentSlot.FEET);
/*     */   
/* 140 */   public static final MoCItemArmor chestFur = new MoCItemArmor("furchest", furARMOR, 4, EntityEquipmentSlot.CHEST);
/* 141 */   public static final MoCItemArmor helmetFur = new MoCItemArmor("furhelmet", furARMOR, 4, EntityEquipmentSlot.HEAD);
/* 142 */   public static final MoCItemArmor legsFur = new MoCItemArmor("furlegs", furARMOR, 4, EntityEquipmentSlot.LEGS);
/* 143 */   public static final MoCItemArmor bootsFur = new MoCItemArmor("furboots", furARMOR, 4, EntityEquipmentSlot.FEET);
/*     */   
/* 145 */   public static final MoCItemArmor chestHide = new MoCItemArmor("hidechest", hideARMOR, 4, EntityEquipmentSlot.CHEST);
/* 146 */   public static final MoCItemArmor helmetHide = new MoCItemArmor("hidehelmet", hideARMOR, 4, EntityEquipmentSlot.HEAD);
/* 147 */   public static final MoCItemArmor legsHide = new MoCItemArmor("hidelegs", hideARMOR, 4, EntityEquipmentSlot.LEGS);
/* 148 */   public static final MoCItemArmor bootsHide = new MoCItemArmor("hideboots", hideARMOR, 4, EntityEquipmentSlot.FEET);
/*     */   
/* 150 */   public static final MoCItemArmor scorpPlateDirt = new MoCItemArmor("scorpplatedirt", scorpARMOR, 4, EntityEquipmentSlot.CHEST);
/* 151 */   public static final MoCItemArmor scorpHelmetDirt = new MoCItemArmor("scorphelmetdirt", scorpARMOR, 4, EntityEquipmentSlot.HEAD);
/* 152 */   public static final MoCItemArmor scorpLegsDirt = new MoCItemArmor("scorplegsdirt", scorpARMOR, 4, EntityEquipmentSlot.LEGS);
/* 153 */   public static final MoCItemArmor scorpBootsDirt = new MoCItemArmor("scorpbootsdirt", scorpARMOR, 4, EntityEquipmentSlot.FEET);
/*     */   
/* 155 */   public static final MoCItemArmor scorpPlateFrost = new MoCItemArmor("scorpplatefrost", scorpARMOR, 4, EntityEquipmentSlot.CHEST);
/* 156 */   public static final MoCItemArmor scorpHelmetFrost = new MoCItemArmor("scorphelmetfrost", scorpARMOR, 4, EntityEquipmentSlot.HEAD);
/* 157 */   public static final MoCItemArmor scorpLegsFrost = new MoCItemArmor("scorplegsfrost", scorpARMOR, 4, EntityEquipmentSlot.LEGS);
/* 158 */   public static final MoCItemArmor scorpBootsFrost = new MoCItemArmor("scorpbootsfrost", scorpARMOR, 4, EntityEquipmentSlot.FEET);
/*     */   
/* 160 */   public static final MoCItemArmor scorpPlateCave = new MoCItemArmor("scorpplatecave", scorpARMOR, 4, EntityEquipmentSlot.CHEST);
/* 161 */   public static final MoCItemArmor scorpHelmetCave = new MoCItemArmor("scorphelmetcave", scorpARMOR, 4, EntityEquipmentSlot.HEAD);
/* 162 */   public static final MoCItemArmor scorpLegsCave = new MoCItemArmor("scorplegscave", scorpARMOR, 4, EntityEquipmentSlot.LEGS);
/* 163 */   public static final MoCItemArmor scorpBootsCave = new MoCItemArmor("scorpbootscave", scorpARMOR, 4, EntityEquipmentSlot.FEET);
/*     */   
/* 165 */   public static final MoCItemArmor scorpPlateNether = new MoCItemArmor("scorpplatenether", scorpARMOR, 4, EntityEquipmentSlot.CHEST);
/* 166 */   public static final MoCItemArmor scorpHelmetNether = new MoCItemArmor("scorphelmetnether", scorpARMOR, 4, EntityEquipmentSlot.HEAD);
/* 167 */   public static final MoCItemArmor scorpLegsNether = new MoCItemArmor("scorplegsnether", scorpARMOR, 4, EntityEquipmentSlot.LEGS);
/* 168 */   public static final MoCItemArmor scorpBootsNether = new MoCItemArmor("scorpbootsnether", scorpARMOR, 4, EntityEquipmentSlot.FEET);
/*     */   
/* 170 */   public static final MoCItem elephantHarness = new MoCItem("elephantharness");
/* 171 */   public static final MoCItem elephantChest = new MoCItem("elephantchest");
/* 172 */   public static final MoCItem elephantGarment = new MoCItem("elephantgarment");
/* 173 */   public static final MoCItem elephantHowdah = new MoCItem("elephanthowdah");
/* 174 */   public static final MoCItem mammothPlatform = new MoCItem("mammothplatform");
/*     */   
/* 176 */   public static final MoCItem scrollFreedom = new MoCItem("scrolloffreedom");
/* 177 */   public static final MoCItem scrollOfSale = new MoCItem("scrollofsale");
/* 178 */   public static final MoCItem scrollOfOwner = new MoCItem("scrollofowner");
/*     */ 
/*     */   
/* 181 */   public static final MoCItemFood cookedTurkey = new MoCItemFood("turkeycooked", 8, 0.6F, false);
/* 182 */   public static final MoCItemFood crabraw = (MoCItemFood)(new MoCItemFood("crabraw", 2, 0.3F, false)).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 30, 0), 0.8F);
/* 183 */   public static final MoCItemFood crabcooked = new MoCItemFood("crabcooked", 6, 0.6F, false);
/* 184 */   public static final MoCItemFood omelet = new MoCItemFood("omelet", 4, 0.6F, false);
/* 185 */   public static final MoCItemFood ostrichraw = (MoCItemFood)(new MoCItemFood("ostrichraw", 2, 0.3F, false)).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 30, 0), 0.8F);
/* 186 */   public static final MoCItemFood ostrichcooked = new MoCItemFood("ostrichcooked", 6, 0.6F, false);
/* 187 */   public static final MoCItemFood ratBurger = new MoCItemFood("ratburger", 8, 0.6F, false);
/* 188 */   public static final MoCItemFood ratCooked = new MoCItemFood("ratcooked", 4, 0.6F, false);
/* 189 */   public static final MoCItemFood ratRaw = (MoCItemFood)(new MoCItemFood("ratraw", 2, 0.3F, false)).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 30, 0), 0.8F);
/* 190 */   public static final MoCItemFood rawTurkey = (MoCItemFood)(new MoCItemFood("turkeyraw", 3, 0.3F, false)).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 30, 0), 0.8F);
/* 191 */   public static final MoCItemFood turtleraw = new MoCItemFood("turtleraw", 2, 0.3F, false);
/* 192 */   public static final MoCItemFood turtlesoup = (MoCItemFood)new MoCItemTurtleSoup("turtlesoup", 6, 0.6F, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventBusSubscriber(modid = "mocreatures")
/*     */   public static class RegistrationHandler
/*     */   {
/*     */     @SubscribeEvent
/*     */     public static void registerItems(RegistryEvent.Register<Item> event) {
/* 203 */       List<Item> items = new ArrayList<>(Arrays.asList(new Item[] { (Item)MoCItems.horsesaddle, (Item)MoCItems.sharkteeth, (Item)MoCItems.haystack, (Item)MoCItems.sugarlump, (Item)MoCItems.mocegg, (Item)MoCItems.bigcatclaw, (Item)MoCItems.whip, (Item)MoCItems.medallion, (Item)MoCItems.litterbox, (Item)MoCItems.woolball, (Item)MoCItems.petfood, (Item)MoCItems.hideCroc, (Item)MoCItems.plateCroc, (Item)MoCItems.helmetCroc, (Item)MoCItems.legsCroc, (Item)MoCItems.bootsCroc, (Item)MoCItems.fur, (Item)MoCItems.omelet, (Item)MoCItems.turtleraw, (Item)MoCItems.turtlesoup, (Item)MoCItems.staffPortal, (Item)MoCItems.staffTeleport, (Item)MoCItems.builderHammer, (Item)MoCItems.nunchaku, (Item)MoCItems.sai, (Item)MoCItems.bo, (Item)MoCItems.katana, (Item)MoCItems.sharksword, (Item)MoCItems.silversword, (Item)MoCItems.essencedarkness, (Item)MoCItems.essencefire, (Item)MoCItems.essenceundead, (Item)MoCItems.essencelight, (Item)MoCItems.amuletbone, (Item)MoCItems.amuletbonefull, (Item)MoCItems.amuletghost, (Item)MoCItems.amuletghostfull, (Item)MoCItems.amuletfairy, (Item)MoCItems.amuletfairyfull, (Item)MoCItems.amuletpegasus, (Item)MoCItems.amuletpegasusfull, (Item)MoCItems.fishnet, (Item)MoCItems.fishnetfull, (Item)MoCItems.petamulet, (Item)MoCItems.petamuletfull, (Item)MoCItems.chestFur, (Item)MoCItems.helmetFur, (Item)MoCItems.legsFur, (Item)MoCItems.bootsFur, (Item)MoCItems.heartdarkness, (Item)MoCItems.heartfire, (Item)MoCItems.heartundead, (Item)MoCItems.ostrichraw, (Item)MoCItems.ostrichcooked, (Item)MoCItems.unicornhorn, (Item)MoCItems.horsearmorcrystal, (Item)MoCItems.recordshuffle, (Item)MoCItems.animalHide, (Item)MoCItems.rawTurkey, (Item)MoCItems.cookedTurkey, (Item)MoCItems.chestHide, (Item)MoCItems.helmetHide, (Item)MoCItems.legsHide, (Item)MoCItems.bootsHide, (Item)MoCItems.ratRaw, (Item)MoCItems.ratCooked, (Item)MoCItems.ratBurger, (Item)MoCItems.chitinCave, (Item)MoCItems.chitinFrost, (Item)MoCItems.chitinNether, (Item)MoCItems.chitin, (Item)MoCItems.scorpSwordDirt, (Item)MoCItems.scorpSwordFrost, (Item)MoCItems.scorpSwordCave, (Item)MoCItems.scorpSwordNether, (Item)MoCItems.scorpPlateDirt, (Item)MoCItems.scorpHelmetDirt, (Item)MoCItems.scorpLegsDirt, (Item)MoCItems.scorpBootsDirt, (Item)MoCItems.scorpPlateFrost, (Item)MoCItems.scorpHelmetFrost, (Item)MoCItems.scorpLegsFrost, (Item)MoCItems.scorpBootsFrost, (Item)MoCItems.scorpPlateNether, (Item)MoCItems.scorpHelmetNether, (Item)MoCItems.scorpLegsNether, (Item)MoCItems.scorpBootsNether, (Item)MoCItems.scorpHelmetCave, (Item)MoCItems.scorpPlateCave, (Item)MoCItems.scorpLegsCave, (Item)MoCItems.scorpBootsCave, (Item)MoCItems.scorpStingDirt, (Item)MoCItems.scorpStingFrost, (Item)MoCItems.scorpStingCave, (Item)MoCItems.scorpStingNether, (Item)MoCItems.tusksWood, (Item)MoCItems.tusksIron, (Item)MoCItems.tusksDiamond, (Item)MoCItems.elephantChest, (Item)MoCItems.elephantGarment, (Item)MoCItems.elephantHarness, (Item)MoCItems.elephantHowdah, (Item)MoCItems.mammothPlatform, (Item)MoCItems.scrollFreedom, (Item)MoCItems.scrollOfSale, (Item)MoCItems.scrollOfOwner, (Item)MoCItems.crabraw, (Item)MoCItems.crabcooked }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 326 */       IForgeRegistry<Item> registry = event.getRegistry();
/*     */       
/* 328 */       for (int i = 0; i < 16; i++) {
/* 329 */         String s = EnumDyeColor.byMetadata(i).getTranslationKey().toLowerCase();
/* 330 */         if (s.equalsIgnoreCase("lightBlue")) s = "light_blue"; 
/* 331 */         MoCItems.kittybed[i] = new MoCItemKittyBed("kittybed_" + s, i);
/* 332 */         registry.register((IForgeRegistryEntry)MoCItems.kittybed[i]);
/* 333 */         if (!MoCreatures.isServer()) {
/* 334 */           ModelLoader.setCustomModelResourceLocation((Item)MoCItems.kittybed[i], 0, new ModelResourceLocation("mocreatures:" + MoCItems.kittybed[i].getTranslationKey().replace("item.", ""), "inventory"));
/*     */         }
/*     */       } 
/*     */       
/* 338 */       for (Item item : items) {
/* 339 */         registry.register((IForgeRegistryEntry)item);
/* 340 */         MoCItems.ITEMS.add(item);
/* 341 */         if (!MoCreatures.isServer()) {
/* 342 */           ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("mocreatures:" + item.getTranslationKey().replace("item.", ""), "inventory"));
/*     */         }
/* 344 */         if (item instanceof MoCItemEgg)
/* 345 */           for (int j = 0; j < 91; j++) {
/* 346 */             if (!MoCreatures.isServer())
/* 347 */               ModelLoader.setCustomModelResourceLocation(item, j, new ModelResourceLocation("mocreatures:mocegg", "inventory")); 
/*     */           }  
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\init\MoCItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */