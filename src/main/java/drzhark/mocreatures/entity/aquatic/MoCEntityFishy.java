package drzhark.mocreatures.entity.aquatic;
import com.google.common.base.Predicate;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageHeart;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MoCEntityFishy extends MoCEntityTameableAquatic {
  public static final String[] fishNames = new String[] { "Blue", "Orange", "Cyan", "Greeny", "Green", "Purple", "Yellow", "Striped", "Yellowy", "Red" }; public int gestationtime;
  private static final DataParameter<Boolean> HAS_EATEN = EntityDataManager.createKey(MoCEntityFishy.class, DataSerializers.BOOLEAN);

  public MoCEntityFishy(World world) {
    super(world);
    setSize(0.3F, 0.3F);
    setEdad(50 + this.rand.nextInt(50));
  }


  protected void initEntityAI() {
    this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.3D));
    this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromEntityMoC((EntityCreature)this, new Predicate<Entity>()
          {
            public boolean apply(Entity entity) {
              return (entity.height > 0.3F || entity.width > 0.3F);
            }
          },  2.0F, 0.6D, 1.5D));
    this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
  }


  public void selectType() {
    if (getType() == 0) {
      setType(this.rand.nextInt(fishNames.length) + 1);
    }
  }


  public ResourceLocation getTexture() {
    switch (getType()) {
      case 1:
        return MoCreatures.proxy.getTexture("fishy1.png");
      case 2:
        return MoCreatures.proxy.getTexture("fishy2.png");
      case 3:
        return MoCreatures.proxy.getTexture("fishy3.png");
      case 4:
        return MoCreatures.proxy.getTexture("fishy4.png");
      case 5:
        return MoCreatures.proxy.getTexture("fishy5.png");
      case 6:
        return MoCreatures.proxy.getTexture("fishy6.png");
      case 7:
        return MoCreatures.proxy.getTexture("fishy7.png");
      case 8:
        return MoCreatures.proxy.getTexture("fishy8.png");
      case 9:
        return MoCreatures.proxy.getTexture("fishy9.png");
      case 10:
        return MoCreatures.proxy.getTexture("fishy10.png");
    }
    return MoCreatures.proxy.getTexture("fishy1.png");
  }



  protected void entityInit() {
    super.entityInit();
    this.dataManager.register(HAS_EATEN, Boolean.valueOf(false));
  }

  public boolean getHasEaten() {
    return ((Boolean)this.dataManager.get(HAS_EATEN)).booleanValue();
  }

  public void setHasEaten(boolean flag) {
    this.dataManager.set(HAS_EATEN, Boolean.valueOf(flag));
  }


  protected void dropFewItems(boolean flag, int x) {
    int i = this.rand.nextInt(100);
    if (i < 70) {
      entityDropItem(new ItemStack(Items.FISH, 1, 0), 0.0F);
    } else {
      int j = this.rand.nextInt(2);
      for (int k = 0; k < j; k++) {
        entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, getType()), 0.0F);
      }
    }
  }



  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!isInsideOfMaterial(Material.WATER)) {
      this.prevRenderYawOffset = this.renderYawOffset = this.rotationYaw = this.prevRotationYaw;
      this.rotationPitch = this.prevRotationPitch;
    }

    if (!this.world.isRemote) {

      if (getIsTamed() && this.rand.nextInt(100) == 0 && getHealth() < getMaxHealth()) {
        setHealth(getMaxHealth());
      }

      if (!ReadyforParenting(this)) {
        return;
      }
      int i = 0;
      List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 3.0D, 4.0D));
      for (int j = 0; j < list.size(); j++) {
        Entity entity = list.get(j);
        if (entity instanceof MoCEntityFishy) {
          i++;
        }
      }

      if (i > 1) {
        return;
      }
      List<Entity> list1 = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
      for (int k = 0; k < list.size(); k++) {
        Entity entity1 = list1.get(k);
        if (entity1 instanceof MoCEntityFishy && entity1 != this) {


          MoCEntityFishy entityfishy = (MoCEntityFishy)entity1;
          if (ReadyforParenting(this) && ReadyforParenting(entityfishy) && getType() == entityfishy.getType()) {


            if (this.rand.nextInt(100) == 0) {
              this.gestationtime++;
            }
            if (this.gestationtime % 3 == 0) {
              MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHeart(getEntityId()), new NetworkRegistry.TargetPoint(this.world.provider
                    .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
            }
            if (this.gestationtime > 50) {


              int l = this.rand.nextInt(3) + 1;
              for (int i1 = 0; i1 < l; i1++) {
                MoCEntityFishy entityfishy1 = new MoCEntityFishy(this.world);
                entityfishy1.setPosition(this.posX, this.posY, this.posZ);
                this.world.spawnEntity((Entity)entityfishy1);
                MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
                setHasEaten(false);
                entityfishy.setHasEaten(false);
                this.gestationtime = 0;
                entityfishy.gestationtime = 0;

                EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
                if (entityplayer != null) {
                  MoCTools.tameWithName(entityplayer, (IMoCTameable)entityfishy1);
                }

                entityfishy1.setEdad(20);
                entityfishy1.setAdult(false);
                entityfishy1.setTypeInt(getType());
              }
              break;
            }
          }
        }
      }
    }
  }
  public boolean ReadyforParenting(MoCEntityFishy entityfishy) {
    return false;
  }


  protected boolean canBeTrappedInNet() {
    return true;
  }


  public int nameYOffset() {
    return -25;
  }


  public float rollRotationOffset() {
    if (!isInsideOfMaterial(Material.WATER)) {
      return -90.0F;
    }
    return 0.0F;
  }


  protected boolean isFisheable() {
    return !getIsTamed();
  }


  protected boolean usesNewAI() {
    return true;
  }


  public float getAIMoveSpeed() {
    return 0.1F;
  }


  public boolean isMovementCeased() {
    return !isInWater();
  }


  protected double minDivingDepth() {
    return 0.2D;
  }


  protected double maxDivingDepth() {
    return 2.0D;
  }


  public float getSizeFactor() {
    return getEdad() * 0.01F;
  }


  public float getAdjustedZOffset() {
    return 0.0F;
  }


  public float getAdjustedXOffset() {
    if (!isInWater()) {
      return -0.1F;
    }
    return 0.0F;
  }


  public float getAdjustedYOffset() {
    if (!isInsideOfMaterial(Material.WATER)) {
      return 0.2F;
    }
    return -0.5F;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityFishy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
