/*    */ package drzhark.mocreatures.item;
/*    */
/*    */ import drzhark.mocreatures.MoCProxy;
/*    */ import drzhark.mocreatures.MoCTools;
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import drzhark.mocreatures.init.MoCItems;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.EntityEquipmentSlot;
/*    */ import net.minecraft.item.ItemArmor;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */
/*    */ public class MoCItemArmor
/*    */   extends ItemArmor
/*    */ {
/*    */   public MoCItemArmor(String name, ItemArmor.ArmorMaterial materialIn, int renderIndex, EntityEquipmentSlot equipmentSlotIn) {
/* 18 */     super(materialIn, renderIndex, equipmentSlotIn);
/* 19 */     setCreativeTab(MoCreatures.tabMoC);
/* 20 */     setRegistryName("mocreatures", name);
/* 21 */     setUnlocalizedName(name);
/*    */   }
/*    */
/*    */
/*    */   public String getArmorTexture(ItemStack itemstack, Entity entity, EntityEquipmentSlot slot, String type) {
/* 26 */     String tempArmorTexture = "croc_1.png";
/* 27 */     if (itemstack.getItem() == MoCItems.helmetCroc || itemstack.getItem() == MoCItems.plateCroc || itemstack
/* 28 */       .getItem() == MoCItems.bootsCroc) {
/* 29 */       tempArmorTexture = "croc_1.png";
/*    */     }
/* 31 */     if (itemstack.getItem() == MoCItems.legsCroc) {
/* 32 */       tempArmorTexture = "croc_2.png";
/*    */     }
/*    */
/* 35 */     if (itemstack.getItem() == MoCItems.helmetFur || itemstack.getItem() == MoCItems.chestFur || itemstack
/* 36 */       .getItem() == MoCItems.bootsFur) {
/* 37 */       tempArmorTexture = "fur_1.png";
/*    */     }
/* 39 */     if (itemstack.getItem() == MoCItems.legsFur) {
/* 40 */       tempArmorTexture = "fur_2.png";
/*    */     }
/*    */
/* 43 */     if (itemstack.getItem() == MoCItems.helmetHide || itemstack.getItem() == MoCItems.chestHide || itemstack
/* 44 */       .getItem() == MoCItems.bootsHide) {
/* 45 */       tempArmorTexture = "hide_1.png";
/*    */     }
/* 47 */     if (itemstack.getItem() == MoCItems.legsHide) {
/* 48 */       tempArmorTexture = "hide_2.png";
/*    */     }
/*    */
/* 51 */     if (itemstack.getItem() == MoCItems.scorpHelmetDirt || itemstack.getItem() == MoCItems.scorpPlateDirt || itemstack
/* 52 */       .getItem() == MoCItems.scorpBootsDirt) {
/* 53 */       tempArmorTexture = "scorpd_1.png";
/*    */     }
/* 55 */     if (itemstack.getItem() == MoCItems.scorpLegsDirt) {
/* 56 */       tempArmorTexture = "scorpd_2.png";
/*    */     }
/*    */
/* 59 */     if (itemstack.getItem() == MoCItems.scorpHelmetFrost || itemstack.getItem() == MoCItems.scorpPlateFrost || itemstack
/* 60 */       .getItem() == MoCItems.scorpBootsFrost) {
/* 61 */       tempArmorTexture = "scorpf_1.png";
/*    */     }
/* 63 */     if (itemstack.getItem() == MoCItems.scorpLegsFrost) {
/* 64 */       tempArmorTexture = "scorpf_2.png";
/*    */     }
/*    */
/* 67 */     if (itemstack.getItem() == MoCItems.scorpHelmetCave || itemstack.getItem() == MoCItems.scorpPlateCave || itemstack
/* 68 */       .getItem() == MoCItems.scorpBootsCave) {
/* 69 */       tempArmorTexture = "scorpc_1.png";
/*    */     }
/* 71 */     if (itemstack.getItem() == MoCItems.scorpLegsCave) {
/* 72 */       tempArmorTexture = "scorpc_2.png";
/*    */     }
/*    */
/* 75 */     if (itemstack.getItem() == MoCItems.scorpHelmetNether || itemstack.getItem() == MoCItems.scorpPlateNether || itemstack
/* 76 */       .getItem() == MoCItems.scorpBootsNether) {
/* 77 */       tempArmorTexture = "scorpn_1.png";
/*    */     }
/* 79 */     if (itemstack.getItem() == MoCItems.scorpLegsNether) {
/* 80 */       tempArmorTexture = "scorpn_2.png";
/*    */     }
/*    */
/* 83 */     return "mocreatures:" + MoCProxy.ARMOR_TEXTURE + tempArmorTexture;
/*    */   }
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */   public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
/* 95 */     if (world.rand.nextInt(50) == 0 && player.getItemStackFromSlot(EntityEquipmentSlot.FEET) != null) {
/* 96 */       ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
/* 97 */       if (!stack.isEmpty() && stack.getItem() instanceof MoCItemArmor)
/* 98 */         MoCTools.updatePlayerArmorEffects(player);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
