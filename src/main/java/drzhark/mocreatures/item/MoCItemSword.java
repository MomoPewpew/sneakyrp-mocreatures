/*    */ package drzhark.mocreatures.item;
/*    */
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.init.MobEffects;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.ItemSword;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */
/*    */ public class MoCItemSword
/*    */   extends ItemSword
/*    */ {
/* 14 */   private int specialWeaponType = 0;
/*    */   private boolean breakable = false;
/*    */
/*    */   public MoCItemSword(String name, Item.ToolMaterial material) {
/* 18 */     this(name, 0, material);
/*    */   }
/*    */
/*    */   public MoCItemSword(String name, int meta, Item.ToolMaterial material) {
/* 22 */     super(material);
/* 23 */     setCreativeTab(MoCreatures.tabMoC);
/* 24 */     setRegistryName("mocreatures", name);
/* 25 */     setUnlocalizedName(name);
/*    */   }
/*    */
/*    */   public MoCItemSword(String name, Item.ToolMaterial material, int damageType, boolean fragile) {
/* 29 */     this(name, material);
/* 30 */     this.specialWeaponType = damageType;
/* 31 */     this.breakable = fragile;
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
/*    */   public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase target, EntityLivingBase attacker) {
/* 43 */     int i = 1;
/* 44 */     if (this.breakable) {
/* 45 */       i = 10;
/*    */     }
/* 47 */     par1ItemStack.damageItem(i, attacker);
/* 48 */     int potionTime = 100;
/* 49 */     switch (this.specialWeaponType) {
/*    */       case 1:
/* 51 */         target.addPotionEffect(new PotionEffect(MobEffects.POISON, potionTime, 0));
/*    */         break;
/*    */       case 2:
/* 54 */         target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, potionTime, 0));
/*    */         break;
/*    */       case 3:
/* 57 */         target.setFire(10);
/*    */         break;
/*    */       case 4:
/* 60 */         target.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, potionTime, 0));
/*    */         break;
/*    */       case 5:
/* 63 */         target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, potionTime, 0));
/*    */         break;
/*    */     }
/*    */
/*    */
/*    */
/* 69 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
