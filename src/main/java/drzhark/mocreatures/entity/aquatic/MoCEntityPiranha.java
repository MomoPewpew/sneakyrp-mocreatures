package drzhark.mocreatures.entity.aquatic;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.ai.EntityAIFollowHerd;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.world.World;

public class MoCEntityPiranha extends MoCEntitySmallFish {
  public static final String[] fishNames = new String[] { "Piranha" };

  public MoCEntityPiranha(World world) {
    super(world);
  }


  protected void initEntityAI() {
    this.tasks.addTask(3, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowHerd((EntityLiving)this, 0.6D, 4.0D, 20.0D, 1));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
    getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
  }


  public void selectType() {
    setType(1);
  }


  public ResourceLocation getTexture() {
    return MoCreatures.proxy.getTexture("smallfish_piranha.png");
  }












  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (super.attackEntityFrom(damagesource, i) && this.world.getDifficulty().getDifficultyId()  > 0) {
      Entity entity = damagesource.getTrueSource();
      if (entity instanceof EntityLivingBase) {
        if (isRidingOrBeingRiddenBy(entity)) {
          return true;
        }
        if (entity != this) {
          setAttackTarget((EntityLivingBase)entity);
        }
        return true;
      }
      return false;
    }
    return false;
  }



  public boolean isNotScared() {
    return true;
  }


  protected void dropFewItems(boolean flag, int x) {
    int i = this.rand.nextInt(100);
    if (i < 70) {
      entityDropItem(new ItemStack(Items.FISH, 1, 0), 0.0F);
    } else {
      int j = this.rand.nextInt(2);
      for (int k = 0; k < j; k++)
        entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, 90), 0.0F);
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityPiranha.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
