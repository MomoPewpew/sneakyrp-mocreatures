package drzhark.mocreatures.item;

import drzhark.mocreatures.MoCreatures;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;

public class MoCItemSword
  extends ItemSword
{
  private int specialWeaponType = 0;
  private boolean breakable = false;

  public MoCItemSword(String name, Item.ToolMaterial material) {
    this(name, 0, material);
  }

  public MoCItemSword(String name, int meta, Item.ToolMaterial material) {
    super(material);
    setCreativeTab(MoCreatures.tabMoC);
    setRegistryName("mocreatures", name);
    setUnlocalizedName(name);
  }

  public MoCItemSword(String name, Item.ToolMaterial material, int damageType, boolean fragile) {
    this(name, material);
    this.specialWeaponType = damageType;
    this.breakable = fragile;
  }









  public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase target, EntityLivingBase attacker) {
    int i = 1;
    if (this.breakable) {
      i = 10;
    }
    par1ItemStack.damageItem(i, attacker);
    int potionTime = 100;
    switch (this.specialWeaponType) {
      case 1:
        target.addPotionEffect(new PotionEffect(MobEffects.POISON, potionTime, 0));
        break;
      case 2:
        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, potionTime, 0));
        break;
      case 3:
        target.setFire(10);
        break;
      case 4:
        target.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, potionTime, 0));
        break;
      case 5:
        target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, potionTime, 0));
        break;
    }



    return true;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
