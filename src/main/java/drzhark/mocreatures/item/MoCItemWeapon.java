package drzhark.mocreatures.item;

import com.google.common.collect.Multimap;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class MoCItemWeapon extends MoCItem {
  private float attackDamage;
  private final Item.ToolMaterial material;
  private int specialWeaponType = 0;
  private boolean breakable = false;

  public MoCItemWeapon(String name, Item.ToolMaterial par2ToolMaterial) {
    super(name);
    this.material = par2ToolMaterial;
    this.maxStackSize = 1;
    setMaxDamage(par2ToolMaterial.getMaxUses());
    this.attackDamage = 4.0F + par2ToolMaterial.getAttackDamage();
  }








  public MoCItemWeapon(String name, Item.ToolMaterial par2ToolMaterial, int damageType, boolean fragile) {
    this(name, par2ToolMaterial);
    this.specialWeaponType = damageType;
    this.breakable = fragile;
  }




  public float getAttackDamage() {
    return this.material.getAttackDamage();
  }

  public float getStrVsBlock(ItemStack stack, IBlockState state) {
    if (state.getBlock() == Blocks.WEB) {
      return 15.0F;
    }
    Material material = state.getMaterial();
    return (material != Material.PLANTS && material != Material.VINE && material != Material.CORAL && material != Material.LEAVES && material != Material.GOURD) ? 1.0F : 1.5F;
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

  public boolean onBlockDestroyed(ItemStack par1ItemStack, int par2, int par3, int par4, int par5, EntityLiving par6EntityLiving) {
    par1ItemStack.damageItem(2, (EntityLivingBase)par6EntityLiving);
    return true;
  }





  @SideOnly(Side.CLIENT)
  public boolean isFull3D() {
    return true;
  }






  public EnumAction getItemUseAction(ItemStack par1ItemStack) {
    return EnumAction.BLOCK;
  }





  public int getMaxItemUseDuration(ItemStack par1ItemStack) {
    return 72000;
  }






  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
    ItemStack stack = player.getHeldItem(hand);
    player.setActiveHand(hand);
    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
  }





  public boolean canHarvestBlock(IBlockState state) {
    return (state.getBlock() == Blocks.WEB);
  }






  public int getItemEnchantability() {
    return this.material.getEnchantability();
  }




  public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase playerIn) {
    if (state.getBlockHardness(worldIn, pos) != 0.0D) {
      stack.damageItem(2, playerIn);
    }

    return true;
  }




  public String getToolMaterialName() {
    return this.material.toString();
  }







  public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
    ItemStack mat = this.material.getRepairItemStack();
    if (mat != null && OreDictionary.itemMatches(mat, repair, false))
      return true;
    return super.getIsRepairable(toRepair, repair);
  }





  public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
    Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);
    if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
      multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamage, 0));
    }

    return multimap;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemWeapon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
