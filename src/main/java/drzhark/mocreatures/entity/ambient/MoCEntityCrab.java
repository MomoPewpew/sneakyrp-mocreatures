package drzhark.mocreatures.entity.ambient;

import com.google.common.base.Predicate;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityTameableAmbient;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MoCEntityCrab extends MoCEntityTameableAmbient {
  public MoCEntityCrab(World world) {
    super(world);
    setSize(0.3F, 0.3F);
    setEdad(50 + this.rand.nextInt(50));
  }


  protected void initEntityAI() {
    this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.0D));
    this.tasks.addTask(1, (EntityAIBase)new EntityAIFleeFromEntityMoC((EntityCreature)this, new Predicate<Entity>()
          {
            public boolean apply(Entity entity) {
              return (!(entity instanceof MoCEntityCrab) && (entity.height > 0.3F || entity.width > 0.3F));
            }
          },  6.0F, 0.6D, 1.0D));
    this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 0.8D, 6.0F, 5.0F));
    this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
  }


  public void selectType() {
    if (getType() == 0) {
      setType(this.rand.nextInt(5) + 1);
    }
  }



  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("craba.png");
      case 2:
        return MoCreatures.proxy.getTexture("crabb.png");
      case 3:
        return MoCreatures.proxy.getTexture("crabc.png");
      case 4:
        return MoCreatures.proxy.getTexture("crabd.png");
      case 5:
        return MoCreatures.proxy.getTexture("crabe.png");
    }

    return MoCreatures.proxy.getTexture("craba.png");
  }



  public void fall(float f, float f1) {}



  protected Item getDropItem() {
    return (Item)MoCItems.crabraw;
  }


  public boolean isOnLadder() {
    return this.collidedHorizontally;
  }

  public boolean climbing() {
    return (!this.onGround && isOnLadder());
  }



  public void jump() {}


  @SideOnly(Side.CLIENT)
  public float getSizeFactor() {
    return 0.7F * getEdad() * 0.01F;
  }


  public boolean canBreatheUnderwater() {
    return true;
  }

  @SideOnly(Side.CLIENT)
  public boolean isFleeing() {
    return (MoCTools.getMyMovementSpeed((Entity)this) > 0.09F);
  }





  public EnumCreatureAttribute getCreatureAttribute() {
    return EnumCreatureAttribute.ARTHROPOD;
  }


  protected boolean canBeTrappedInNet() {
    return true;
  }


  public int nameYOffset() {
    return -20;
  }


  public boolean isNotScared() {
    return getIsTamed();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityCrab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
