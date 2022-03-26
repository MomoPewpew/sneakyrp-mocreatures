package drzhark.mocreatures;

import drzhark.mocreatures.entity.IMoCEntity;
import drzhark.mocreatures.entity.IMoCTameable;
import drzhark.mocreatures.entity.MoCEntityAnimal;
import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
import drzhark.mocreatures.entity.ambient.MoCEntityMaggot;
import drzhark.mocreatures.entity.item.MoCEntityThrowableRock;
import drzhark.mocreatures.init.MoCItems;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.inventory.MoCAnimalChest;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageNameGUI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.Path;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;




public class MoCTools
{
  public static void spawnSlimes(World world, Entity entity) {
    if (!world.isRemote) {

      int var2 = 1 + world.rand.nextInt(1);

      for (int i = 0; i < var2; i++) {
        float var4 = ((i % 2) - 0.5F) * 1.0F / 4.0F;
        float var5 = ((i / 2) - 0.5F) * 1.0F / 4.0F;
        EntitySlime var6 = new EntitySlime(world);

        var6.setLocationAndAngles(entity.posX + var4, entity.posY + 0.5D, entity.posZ + var5, world.rand.nextFloat() * 360.0F, 0.0F);
        world.spawnEntity((Entity)var6);
      }
    }
  }




  public static void dropSaddle(MoCEntityAnimal entity, World world) {
    if (!entity.getIsRideable() || world.isRemote) {
      return;
    }
    dropCustomItem((Entity)entity, world, new ItemStack((Item)MoCItems.horsesaddle, 1));
    entity.setRideable(false);
  }




  public static void dropBags(MoCEntityAnimal entity, World world) {
    if (world.isRemote) {
      return;
    }
    dropCustomItem((Entity)entity, world, new ItemStack((Block)Blocks.CHEST, 1));
  }




  public static void dropCustomItem(Entity entity, World world, ItemStack itemstack) {
    if (world.isRemote) {
      return;
    }

    EntityItem entityitem = new EntityItem(world, entity.posX, entity.posY, entity.posZ, itemstack);
    float f3 = 0.05F;
    entityitem.motionX = ((float)world.rand.nextGaussian() * f3);
    entityitem.motionY = ((float)world.rand.nextGaussian() * f3 + 0.2F);
    entityitem.motionZ = ((float)world.rand.nextGaussian() * f3);
    world.spawnEntity((Entity)entityitem);
  }

  public static void bigsmack(Entity entity, Entity entity1, float force) {
    double d = entity.posX - entity1.posX;
    double d1 = entity.posZ - entity1.posZ;
    for (d1 = entity.posZ - entity1.posZ; d * d + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
      d = (Math.random() - Math.random()) * 0.01D;
    }

    float f = MathHelper.sqrt(d * d + d1 * d1);
    entity1.motionX /= 2.0D;
    entity1.motionY /= 2.0D;
    entity1.motionZ /= 2.0D;
    entity1.motionX -= d / f * force;
    entity1.motionY += force;
    entity1.motionZ -= d1 / f * force;
    if (entity1.motionY > force) {
      entity1.motionY = force;
    }
  }

  public static void buckleMobs(EntityLiving entityattacker, Double dist, World world) {
    List<Entity> list = world.getEntitiesWithinAABBExcludingEntity((Entity)entityattacker, entityattacker.getEntityBoundingBox().expand(dist.doubleValue(), 2.0D, dist.doubleValue()));
    for (int i = 0; i < list.size(); i++) {
      Entity entitytarget = list.get(i);
      if (entitytarget instanceof EntityLiving && (!entityattacker.isBeingRidden() || entitytarget != entityattacker.getRidingEntity())) {



        entitytarget.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)entityattacker), 2.0F);
        bigsmack((Entity)entityattacker, entitytarget, 0.6F);
        playCustomSound((Entity)entityattacker, MoCSoundEvents.ENTITY_GENERIC_TUD);
      }
    }
  }

  public static void buckleMobsNotPlayers(EntityLiving entityattacker, Double dist, World world) {
    List<Entity> list = world.getEntitiesWithinAABBExcludingEntity((Entity)entityattacker, entityattacker.getEntityBoundingBox().expand(dist.doubleValue(), 2.0D, dist.doubleValue()));
    for (int i = 0; i < list.size(); i++) {
      Entity entitytarget = list.get(i);
      if (entitytarget instanceof EntityLiving && !(entitytarget instanceof EntityPlayer) && (
        !entityattacker.isBeingRidden() || entitytarget != entityattacker.getRidingEntity())) {



        entitytarget.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)entityattacker), 2.0F);
        bigsmack((Entity)entityattacker, entitytarget, 0.6F);
        playCustomSound((Entity)entityattacker, MoCSoundEvents.ENTITY_GENERIC_TUD);
      }
    }
  }



  public static void spawnNearPlayer(EntityPlayer player, int entityId, int numberToSpawn) {
    WorldServer world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(player.world.provider.getDimensionType().getId());
    for (int i = 0; i < numberToSpawn; i++) {
      EntityLiving entityliving = null;
      try {
        Class<? extends EntityLiving> entityClass = MoCreatures.instaSpawnerMap.get(Integer.valueOf(entityId));
        entityliving = entityClass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
      } catch (Exception e) {
        e.printStackTrace();
      }

      if (entityliving != null) {
        entityliving.setLocationAndAngles(player.posX - 1.0D, player.posY, player.posZ - 1.0D, player.rotationYaw, player.rotationPitch);
        world.spawnEntity((Entity)entityliving);
      }
    }
  }


  public static void spawnNearPlayerbyName(EntityPlayer player, String eName, int numberToSpawn) {
    WorldServer world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(player.world.provider.getDimensionType().getId());

    for (int i = 0; i < numberToSpawn; i++) {
      EntityLiving entityToSpawn = null;
      try {
        MoCEntityData entityData = MoCreatures.mocEntityMap.get(eName);
        Class<? extends EntityLiving> myClass = entityData.getEntityClass();
        entityToSpawn = myClass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
      } catch (Exception e) {
        e.printStackTrace();
      }

      if (entityToSpawn != null) {
        IEntityLivingData entitylivingdata = null;
        entityToSpawn.onInitialSpawn(player.world.getDifficultyForLocation(new BlockPos((Entity)entityToSpawn)), entitylivingdata);
        entityToSpawn.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
        world.spawnEntity((Entity)entityToSpawn);
      }
    }
  }

  public static void playCustomSound(Entity entity, SoundEvent customSound) {
    playCustomSound(entity, customSound, 1.0F);
  }

  public static void playCustomSound(Entity entity, SoundEvent customSound, float volume) {
    entity.playSound(customSound, volume, 1.0F + (entity.world.rand.nextFloat() - entity.world.rand.nextFloat()) * 0.2F);
  }

  public static boolean NearMaterialWithDistance(Entity entity, Double double1, Material mat) {
    AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(double1.doubleValue(), double1.doubleValue(), double1.doubleValue());
    int i = MathHelper.floor(axisalignedbb.minX);
    int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
    int k = MathHelper.floor(axisalignedbb.minY);
    int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
    int i1 = MathHelper.floor(axisalignedbb.minZ);
    int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
    for (int k1 = i; k1 < j; k1++) {
      for (int l1 = k; l1 < l; l1++) {
        for (int i2 = i1; i2 < j1; i2++) {
          IBlockState blockstate = entity.world.getBlockState(new BlockPos(k1, l1, i2));
          if (blockstate.getBlock() != Blocks.AIR && blockstate.getMaterial() == mat) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public static boolean isNearBlockName(Entity entity, Double dist, String blockName) {
    AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(dist.doubleValue(), dist.doubleValue() / 2.0D, dist.doubleValue());
    int i = MathHelper.floor(axisalignedbb.minX);
    int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
    int k = MathHelper.floor(axisalignedbb.minY);
    int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
    int i1 = MathHelper.floor(axisalignedbb.minZ);
    int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
    for (int k1 = i; k1 < j; k1++) {
      for (int l1 = k; l1 < l; l1++) {
        for (int i2 = i1; i2 < j1; i2++) {
          IBlockState blockstate = entity.world.getBlockState(new BlockPos(k1, l1, i2));

          if (blockstate.getBlock() != Blocks.AIR) {
            String nameToCheck = "";
            nameToCheck = blockstate.getBlock().getUnlocalizedName();
            if (nameToCheck != null && nameToCheck != "" &&
              nameToCheck.equals(blockName)) {
              return true;
            }
          }
        }
      }
    }

    return false;
  }

  public static BlockJukebox.TileEntityJukebox nearJukeBoxRecord(Entity entity, Double dist) {
    AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(dist.doubleValue(), dist.doubleValue() / 2.0D, dist.doubleValue());
    int i = MathHelper.floor(axisalignedbb.minX);
    int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
    int k = MathHelper.floor(axisalignedbb.minY);
    int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
    int i1 = MathHelper.floor(axisalignedbb.minZ);
    int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
    for (int k1 = i; k1 < j; k1++) {
      for (int l1 = k; l1 < l; l1++) {
        for (int i2 = i1; i2 < j1; i2++) {
          BlockPos pos = new BlockPos(k1, l1, i2);
          IBlockState blockstate = entity.world.getBlockState(pos);
          if (!entity.world.isAirBlock(pos) &&
            blockstate.getBlock() instanceof BlockJukebox) {
            BlockJukebox.TileEntityJukebox juky = (BlockJukebox.TileEntityJukebox)entity.world.getTileEntity(pos);
            return juky;
          }
        }
      }
    }

    return null;
  }

  public static void checkForTwistedEntities(World world) {
    for (int l = 0; l < world.loadedEntityList.size(); l++) {
      Entity entity = world.loadedEntityList.get(l);
      if (entity instanceof EntityLivingBase) {
        EntityLivingBase twisted = (EntityLivingBase)entity;
        if (twisted.deathTime > 0 && twisted.getRidingEntity() == null && twisted.getHealth() > 0.0F) {
          twisted.deathTime = 0;
        }
      }
    }
  }

  public static double getSqDistanceTo(Entity entity, double i, double j, double k) {
    double l = entity.posX - i;
    double i1 = entity.posY - j;
    double j1 = entity.posZ - k;
    return Math.sqrt(l * l + i1 * i1 + j1 * j1);
  }

  public static int[] ReturnNearestMaterialCoord(Entity entity, Material material, Double double1, Double yOff) {
    double shortestDistance = -1.0D;
    double distance = 0.0D;
    int x = -9999;
    int y = -1;
    int z = -1;

    AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(double1.doubleValue(), yOff.doubleValue(), double1.doubleValue());
    int i = MathHelper.floor(axisalignedbb.minX);
    int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
    int k = MathHelper.floor(axisalignedbb.minY);
    int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
    int i1 = MathHelper.floor(axisalignedbb.minZ);
    int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
    for (int k1 = i; k1 < j; k1++) {
      for (int l1 = k; l1 < l; l1++) {
        for (int i2 = i1; i2 < j1; i2++) {
          BlockPos pos = new BlockPos(k1, l1, i2);
          IBlockState blockstate = entity.world.getBlockState(pos);
          if (blockstate.getBlock() != Blocks.AIR && blockstate.getMaterial() == material) {
            distance = getSqDistanceTo(entity, k1, l1, i2);
            if (shortestDistance == -1.0D) {
              x = k1;
              y = l1;
              z = i2;
              shortestDistance = distance;
            }

            if (distance < shortestDistance) {
              x = k1;
              y = l1;
              z = i2;
              shortestDistance = distance;
            }
          }
        }
      }
    }

    if (entity.posX > x) {
      x -= 2;
    } else {
      x += 2;
    }
    if (entity.posZ > z) {
      z -= 2;
    } else {
      z += 2;
    }
    return new int[] { x, y, z };
  }

  public static int[] ReturnNearestBlockCoord(Entity entity, Block block1, Double dist) {
    double shortestDistance = -1.0D;
    double distance = 0.0D;
    int x = -9999;
    int y = -1;
    int z = -1;

    AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(dist.doubleValue(), dist.doubleValue(), dist.doubleValue());
    int i = MathHelper.floor(axisalignedbb.minX);
    int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
    int k = MathHelper.floor(axisalignedbb.minY);
    int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
    int i1 = MathHelper.floor(axisalignedbb.minZ);
    int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
    for (int k1 = i; k1 < j; k1++) {
      for (int l1 = k; l1 < l; l1++) {
        for (int i2 = i1; i2 < j1; i2++) {
          BlockPos pos = new BlockPos(k1, l1, i2);
          IBlockState blockstate = entity.world.getBlockState(pos);
          if (blockstate.getBlock() != Blocks.AIR && blockstate.getBlock() == block1) {
            distance = getSqDistanceTo(entity, k1, l1, i2);
            if (shortestDistance == -1.0D) {
              x = k1;
              y = l1;
              z = i2;
              shortestDistance = distance;
            }

            if (distance < shortestDistance) {
              x = k1;
              y = l1;
              z = i2;
              shortestDistance = distance;
            }
          }
        }
      }
    }

    if (entity.posX > x) {
      x -= 2;
    } else {
      x += 2;
    }
    if (entity.posZ > z) {
      z -= 2;
    } else {
      z += 2;
    }
    return new int[] { x, y, z };
  }


  public static void MoveCreatureToXYZ(EntityCreature movingEntity, int x, int y, int z, float f) {
    Path pathentity = movingEntity.getNavigator().getPathToXYZ(x, y, z);
    if (pathentity != null) {
      movingEntity.getNavigator().setPath(pathentity, f);
    }
  }

  public static void MoveToWater(EntityCreature entity) {
    int[] ai = ReturnNearestMaterialCoord((Entity)entity, Material.WATER, Double.valueOf(20.0D), Double.valueOf(2.0D));
    if (ai[0] > -1000) {
      MoveCreatureToXYZ(entity, ai[0], ai[1], ai[2], 24.0F);
    }
  }







  public static float realAngle(float origAngle) {
    return origAngle % 360.0F;
  }

  public static void SlideEntityToXYZ(Entity entity, int x, int y, int z) {
    if (entity != null) {
      if (entity.posY < y) {
        entity.motionY += 0.15D;
      }
      if (entity.posX < x) {
        double d = x - entity.posX;
        if (d > 0.5D) {
          entity.motionX += 0.05D;
        }
      } else {
        double d1 = entity.posX - x;
        if (d1 > 0.5D) {
          entity.motionX -= 0.05D;
        }
      }
      if (entity.posZ < z) {
        double d2 = z - entity.posZ;
        if (d2 > 0.5D) {
          entity.motionZ += 0.05D;
        }
      } else {
        double d3 = entity.posZ - z;
        if (d3 > 0.5D) {
          entity.motionZ -= 0.05D;
        }
      }
    }
  }

  public static float distanceToSurface(Entity entity) {
    int i = MathHelper.floor(entity.posX);
    int j = MathHelper.floor(entity.posY);
    int k = MathHelper.floor(entity.posZ);
    IBlockState blockstate = entity.world.getBlockState(new BlockPos(i, j, k));
    if (blockstate.getBlock() != Blocks.AIR && blockstate.getMaterial() == Material.WATER) {
      for (int x = 1; x < 64; x++) {
        blockstate = entity.world.getBlockState(new BlockPos(i, j + x, k));
        if (blockstate.getBlock() == Blocks.AIR || blockstate.getMaterial() != Material.WATER) {
          return x;
        }
      }
    }
    return 0.0F;
  }

  public static double waterSurfaceAtGivenPosition(double posX, double posY, double posZ, World worldIn) {
    int i = MathHelper.floor(posX);
    int j = MathHelper.floor(posY);
    int k = MathHelper.floor(posZ);
    IBlockState blockstate = worldIn.getBlockState(new BlockPos(i, j, k));
    if (blockstate.getBlock() != Blocks.AIR && blockstate.getMaterial() == Material.WATER) {
      for (int x = 1; x < 64; x++) {
        blockstate = worldIn.getBlockState(new BlockPos(i, j + x, k));
        if (blockstate.getBlock() == Blocks.AIR || blockstate.getMaterial() != Material.WATER) {
          return (j + x);
        }
      }
    }
    return 0.0D;
  }

  public static double waterSurfaceAtGivenEntity(Entity entity) {
    return waterSurfaceAtGivenPosition(entity.posX, entity.posY, entity.posZ, entity.world);
  }

  public static float distanceToSurface(double posX, double posY, double posZ, World worldIn) {
    int i = MathHelper.floor(posX);
    int j = MathHelper.floor(posY);
    int k = MathHelper.floor(posZ);
    IBlockState blockstate = worldIn.getBlockState(new BlockPos(i, j, k));
    if (blockstate.getBlock() != Blocks.AIR && blockstate.getMaterial() == Material.WATER) {
      for (int x = 1; x < 64; x++) {
        blockstate = worldIn.getBlockState(new BlockPos(i, j + x, k));
        if (blockstate.getBlock() == Blocks.AIR || blockstate.getMaterial() != Material.WATER) {
          return x;
        }
      }
    }
    return 0.0F;
  }

  public static int distanceToFloor(Entity entity) {
    int i = MathHelper.floor(entity.posX);
    int j = MathHelper.floor(entity.posY);
    int k = MathHelper.floor(entity.posZ);
    for (int x = 0; x < 64; x++) {
      Block block = entity.world.getBlockState(new BlockPos(i, j - x, k)).getBlock();
      if (block != Blocks.AIR) {
        return x;
      }
    }

    return 0;
  }

  public boolean isInsideOfMaterial(Material material, Entity entity) {
    double d = entity.posY + entity.getEyeHeight();
    int i = MathHelper.floor(entity.posX);
    int j = MathHelper.floor(MathHelper.floor(d));
    int k = MathHelper.floor(entity.posZ);
    BlockPos pos = new BlockPos(i, j, k);
    IBlockState blockstate = entity.world.getBlockState(pos);
    if (blockstate.getBlock() != Blocks.AIR && blockstate.getMaterial() == material) {
      float f = BlockLiquid.getLiquidHeightPercent(blockstate.getBlock().getMetaFromState(blockstate)) - 0.1111111F;
      float f1 = (j + 1) - f;
      return (d < f1);
    }
    return false;
  }


  public static void disorientEntity(Entity entity) {
    double rotD = 0.0D;
    double motD = 0.0D;
    double d = entity.world.rand.nextGaussian();
    double d1 = 0.1D * d;
    motD = 0.2D * d1 + 0.8D * motD;
    entity.motionX += motD;
    entity.motionZ += motD;
    double d2 = 0.78D * d;
    rotD = 0.125D * d2 + 0.875D * rotD;
    entity.rotationYaw = (float)(entity.rotationYaw + rotD);
    entity.rotationPitch = (float)(entity.rotationPitch + rotD);
  }

  public static void slowEntity(Entity entity) {
    entity.motionX *= 0.8D;
    entity.motionZ *= 0.8D;
  }

  public static int colorize(int i) {
    return (i ^ 0xFFFFFFFF) & 0xF;
  }

  public int countEntities(Class<? extends EntityLiving> class1, World world) {
    int i = 0;
    for (int j = 0; j < world.loadedEntityList.size(); j++) {
      Entity entity = world.loadedEntityList.get(j);
      if (class1.isAssignableFrom(entity.getClass())) {
        i++;
      }
    }

    return i;
  }


  public static float distToPlayer(Entity entity) {
    return 0.0F;
  }


  public static Biome Biomekind(World world, BlockPos pos) {
    return world.getBiome(pos);
  }


  public static void destroyDrops(Entity entity, double d) {
    if (!MoCreatures.proxy.destroyDrops) {
      return;
    }

    List<Entity> list = entity.world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().expand(d, d, d));

    for (int i = 0; i < list.size(); i++) {
      Entity entity1 = list.get(i);
      if (entity1 instanceof EntityItem) {


        EntityItem entityitem = (EntityItem)entity1;
        if (entityitem != null && entityitem.getAge() < 50)
          entityitem.setDead();
      }
    }
  }

  public static void repelMobs(Entity entity1, Double dist, World world) {
    List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity1, entity1.getEntityBoundingBox().expand(dist.doubleValue(), 4.0D, dist.doubleValue()));
    for (int i = 0; i < list.size(); i++) {
      Entity entity = list.get(i);
      if (entity instanceof EntityMob) {


        EntityMob entitymob = (EntityMob)entity;
        entitymob.setAttackTarget(null);

        entitymob.getNavigator().clearPath();
      }
    }
  }






  public static void dropGoodies(World world, Entity entity) {
    if (world.isRemote) {
      return;
    }

    EntityItem entityitem = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Blocks.LOG, 16));
    entityitem.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem);

    EntityItem entityitem2 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.DIAMOND, 64));
    entityitem2.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem2);

    EntityItem entityitem3 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Blocks.PUMPKIN, 6));
    entityitem3.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem3);

    EntityItem entityitem4 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Blocks.COBBLESTONE, 64));
    entityitem4.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem4);

    EntityItem entityitem5 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.APPLE, 24));
    entityitem5.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem5);

    EntityItem entityitem6 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.LEATHER, 64));
    entityitem6.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem6);

    EntityItem entityitem7 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack((Item)MoCItems.recordshuffle, 6));
    entityitem7.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem7);

    EntityItem entityitem8 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.IRON_INGOT, 64));
    entityitem8.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem8);

    EntityItem entityitem9 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.GOLD_INGOT, 12));
    entityitem9.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem9);

    EntityItem entityitem10 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.STRING, 32));
    entityitem10.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem10);

    EntityItem entityitem12 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack((Block)Blocks.RED_FLOWER, 6));
    entityitem12.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem12);

    EntityItem entityitem13 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.BLAZE_ROD, 12));
    entityitem13.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem13);

    EntityItem entityitem14 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.ENDER_PEARL, 12));
    entityitem14.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem14);

    EntityItem entityitem15 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.GHAST_TEAR, 12));
    entityitem15.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem15);

    EntityItem entityitem16 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Blocks.LAPIS_BLOCK, 2));
    entityitem16.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem16);

    EntityItem entityitem17 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.BONE, 12));
    entityitem17.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem17);

    EntityItem entityitem18 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack((Item)MoCItems.unicornhorn, 16));
    entityitem18.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem18);

    EntityItem entityitem19 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack((Block)Blocks.FIRE, 32));
    entityitem19.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem19);

    EntityItem entityitem20 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack((Item)MoCItems.essencedarkness, 6));
    entityitem20.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem20);

    EntityItem entityitem21 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack((Item)MoCItems.essenceundead, 6));
    entityitem21.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem21);

    EntityItem entityitem22 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack((Item)MoCItems.essencefire, 6));
    entityitem22.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem22);


    EntityItem entityitem23 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 6, 15));
    entityitem23.setPickupDelay(10);
    world.spawnEntity((Entity)entityitem23);
  }


  public static boolean mobGriefing(World world) {
    return world.getGameRules().getBoolean("mobGriefing");
  }

  public static void DestroyBlast(Entity entity, double d, double d1, double d2, float f, boolean flag) {
    EntityPlayer player = (entity instanceof EntityPlayer) ? (EntityPlayer)entity : null;
    entity.world.playSound(player, d, d1, d2, MoCSoundEvents.ENTITY_GENERIC_DESTROY, SoundCategory.HOSTILE, 4.0F, (1.0F + (entity.world.rand
        .nextFloat() - entity.world.rand.nextFloat()) * 0.2F) * 0.7F);

    boolean mobGriefing = mobGriefing(entity.world);

    HashSet<BlockPos> hashset = new HashSet<>();
    float f1 = f;
    int i = 16;
    for (int j = 0; j < i; j++) {
      for (int l = 0; l < i; l++) {
        for (int j1 = 0; j1 < i; j1++) {
          if (j == 0 || j == i - 1 || l == 0 || l == i - 1 || j1 == 0 || j1 == i - 1) {


            double d3 = (j / (i - 1.0F) * 2.0F - 1.0F);
            double d4 = (l / (i - 1.0F) * 2.0F - 1.0F);
            double d5 = (j1 / (i - 1.0F) * 2.0F - 1.0F);
            double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
            d3 /= d6;
            d4 /= d6;
            d5 /= d6;
            float f2 = f * (0.7F + entity.world.rand.nextFloat() * 0.6F);
            double d8 = d;
            double d10 = d1;
            double d12 = d2;
            float f3 = 0.3F;
            float f4 = 5.0F;

            while (f2 > 0.0F) {


              int k5 = MathHelper.floor(d8);
              int l5 = MathHelper.floor(d10);
              int i6 = MathHelper.floor(d12);
              BlockPos pos = new BlockPos(k5, l5, i6);
              IBlockState blockstate = entity.world.getBlockState(pos);
              if (blockstate.getBlock() != Blocks.AIR) {
                f4 = blockstate.getBlockHardness(entity.world, pos);
                f2 -= (blockstate.getBlock().getExplosionResistance(entity) + 0.3F) * f3 / 10.0F;
              }
              if (f2 > 0.0F && d10 > entity.posY && f4 < 3.0F) {
                hashset.add(pos);
              }
              d8 += d3 * f3;
              d10 += d4 * f3;
              d12 += d5 * f3;
              f2 -= f3 * 0.75F;
            }
          }
        }
      }
    }


    f *= 2.0F;
    if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
      int k = MathHelper.floor(d - f - 1.0D);
      int i1 = MathHelper.floor(d + f + 1.0D);
      int k1 = MathHelper.floor(d1 - f - 1.0D);
      int l1 = MathHelper.floor(d1 + f + 1.0D);
      int i2 = MathHelper.floor(d2 - f - 1.0D);
      int j2 = MathHelper.floor(d2 + f + 1.0D);
      List<Entity> list = entity.world.getEntitiesWithinAABBExcludingEntity(entity, new AxisAlignedBB(k, k1, i2, i1, l1, j2));
      Vec3d vec3d = new Vec3d(d, d1, d2);
      for (int k2 = 0; k2 < list.size(); k2++) {
        Entity entity1 = list.get(k2);
        double d7 = entity1.getDistance(d, d1, d2) / f;
        if (d7 <= 1.0D) {


          double d9 = entity1.posX - d;
          double d11 = entity1.posY - d1;
          double d13 = entity1.posZ - d2;
          double d15 = MathHelper.sqrt(d9 * d9 + d11 * d11 + d13 * d13);
          d9 /= d15;
          d11 /= d15;
          d13 /= d15;
          double d17 = entity.world.getBlockDensity(vec3d, entity1.getEntityBoundingBox());
          double d19 = (1.0D - d7) * d17;


          if (!(entity1 instanceof drzhark.mocreatures.entity.monster.MoCEntityOgre)) {
            entity1.attackEntityFrom(DamageSource.GENERIC, (int)((d19 * d19 + d19) / 2.0D * 3.0D * f + 1.0D));
            double d21 = d19;
            entity1.motionX += d9 * d21;
            entity1.motionY += d11 * d21;
            entity1.motionZ += d13 * d21;
          }
        }
      }
    }
    f = f1;
    ArrayList<BlockPos> arraylist = new ArrayList<>();
    arraylist.addAll(hashset);

    for (int l2 = arraylist.size() - 1; l2 >= 0; l2--) {
      BlockPos chunkposition = arraylist.get(l2);
      IBlockState blockstate = entity.world.getBlockState(chunkposition);
      for (int j5 = 0; j5 < 5; j5++) {
        double d14 = (chunkposition.getX() + entity.world.rand.nextFloat());
        double d16 = (chunkposition.getY() + entity.world.rand.nextFloat());
        double d18 = (chunkposition.getZ() + entity.world.rand.nextFloat());
        double d20 = d14 - d;
        double d22 = d16 - d1;
        double d23 = d18 - d2;
        double d24 = MathHelper.sqrt(d20 * d20 + d22 * d22 + d23 * d23);
        d20 /= d24;
        d22 /= d24;
        d23 /= d24;
        double d25 = 0.5D / (d24 / f + 0.1D);
        d25 *= (entity.world.rand.nextFloat() * entity.world.rand.nextFloat() + 0.3F);
        d25--;
        d20 *= d25;
        d22 *= d25 - 1.0D;
        d23 *= d25;




        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
          entity.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d14 + d * 1.0D) / 2.0D, (d16 + d1 * 1.0D) / 2.0D, (d18 + d2 * 1.0D) / 2.0D, d20, d22, d23, new int[0]);

          entity.motionX -= 0.001000000047497451D;
          entity.motionY -= 0.001000000047497451D;
        }
      }



      if (mobGriefing && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && blockstate.getBlock() != Blocks.AIR) {
        BlockEvent.BreakEvent event = null;
        if (!entity.world.isRemote) {

          try {
            event = new BlockEvent.BreakEvent(entity.world, chunkposition, blockstate, (EntityPlayer)FakePlayerFactory.get(
                  DimensionManager.getWorld(entity.world.provider.getDimensionType().getId()), MoCreatures.MOCFAKEPLAYER));
          } catch (Throwable throwable) {}
        }

        if (event != null && !event.isCanceled()) {
          blockstate.getBlock().dropBlockAsItemWithChance(entity.world, chunkposition, blockstate, 0.3F, 1);
          entity.world.setBlockToAir(chunkposition);


          Explosion explosion = new Explosion(entity.world, null, chunkposition.getX(), chunkposition.getY(), chunkposition.getZ(), 3.0F, false, false);
          blockstate.getBlock().onBlockDestroyedByExplosion(entity.world, chunkposition, explosion);
        }
      }
    }


    if (mobGriefing && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && flag) {
      for (int i3 = arraylist.size() - 1; i3 >= 0; i3--) {
        BlockPos chunkposition1 = arraylist.get(i3);
        IBlockState blockstate = entity.world.getBlockState(chunkposition1);
        if (blockstate.getBlock() == Blocks.AIR && entity.world.rand.nextInt(8) == 0) {
          BlockEvent.BreakEvent event = null;
          if (!entity.world.isRemote)
          {
            event = new BlockEvent.BreakEvent(entity.world, chunkposition1, blockstate, (EntityPlayer)FakePlayerFactory.get((WorldServer)entity.world, MoCreatures.MOCFAKEPLAYER));
          }

          if (event != null && !event.isCanceled()) {
            entity.world.setBlockState(chunkposition1, Blocks.FIRE.getDefaultState(), 3);
          }
        }
      }
    }
  }

  public static int despawnVanillaAnimals(World world) {
    return despawnVanillaAnimals(world, null);
  }


  public static int despawnVanillaAnimals(World world, List<Entity>[] classList) {
    int count = 0;
    for (int j = 0; j < world.loadedEntityList.size(); j++) {
      Entity entity = world.loadedEntityList.get(j);
      if (entity instanceof EntityLiving && (entity instanceof net.minecraft.entity.passive.EntityCow || entity instanceof net.minecraft.entity.passive.EntitySheep || entity instanceof net.minecraft.entity.passive.EntityPig || entity instanceof net.minecraft.entity.passive.EntityChicken || entity instanceof net.minecraft.entity.passive.EntitySquid || entity instanceof net.minecraft.entity.passive.EntityWolf))
      {

        count += entityDespawnCheck(world, (EntityLiving)entity);
      }
    }
    return count;
  }


  protected static int entityDespawnCheck(World world, EntityLiving entityliving) {
    int count = 0;
    EntityPlayer entityplayer = world.getClosestPlayerToEntity((Entity)entityliving, -1.0D);
    if (entityplayer != null) {

      double d = ((Entity)entityplayer).posX - entityliving.posX;
      double d1 = ((Entity)entityplayer).posY - entityliving.posY;
      double d2 = ((Entity)entityplayer).posZ - entityliving.posZ;
      double d3 = d * d + d1 * d1 + d2 * d2;
      if (d3 > 16384.0D) {
        entityliving.setDead();
        count++;
      }

      if (entityliving.getIdleTime() > 600 && world.rand.nextInt(800) == 0) {
        if (d3 < 1024.0D) {

          entityliving.attackEntityFrom(DamageSource.causeMobDamage(null), 0.0F);
        } else {
          entityliving.setDead();
          count++;
        }
      }
    }
    return count;
  }















  public static void updatePlayerArmorEffects(EntityPlayer player) {
    ItemStack[] mystack = new ItemStack[4];
    mystack[0] = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
    mystack[1] = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
    mystack[2] = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
    mystack[3] = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);


    if (mystack[0] != null && mystack[0].getItem() == MoCItems.scorpBootsCave && mystack[1] != null && mystack[1]
      .getItem() == MoCItems.scorpLegsCave && mystack[2] != null && mystack[2].getItem() == MoCItems.scorpPlateCave && mystack[3] != null && mystack[3]
      .getItem() == MoCItems.scorpHelmetCave) {
      player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 0));

      return;
    }

    if (mystack[0] != null && mystack[0].getItem() == MoCItems.scorpBootsNether && mystack[1] != null && mystack[1]
      .getItem() == MoCItems.scorpLegsNether && mystack[2] != null && mystack[2].getItem() == MoCItems.scorpPlateNether && mystack[3] != null && mystack[3]
      .getItem() == MoCItems.scorpHelmetNether) {
      player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 300, 0));

      return;
    }

    if (mystack[0] != null && mystack[0].getItem() == MoCItems.scorpBootsFrost && mystack[1] != null && mystack[1]
      .getItem() == MoCItems.scorpLegsFrost && mystack[2] != null && mystack[2].getItem() == MoCItems.scorpPlateFrost && mystack[3] != null && mystack[3]
      .getItem() == MoCItems.scorpHelmetFrost) {
      player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 300, 0));

      return;
    }

    if (mystack[0] != null && mystack[0].getItem() == MoCItems.scorpBootsDirt && mystack[1] != null && mystack[1]
      .getItem() == MoCItems.scorpLegsDirt && mystack[2] != null && mystack[2].getItem() == MoCItems.scorpPlateDirt && mystack[3] != null && mystack[3]
      .getItem() == MoCItems.scorpHelmetDirt) {
      player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 70, 0));
      return;
    }
  }











  public static int destroyRandomBlock(Entity entity, double distance) {
    int l = (int)(distance * distance * distance);
    for (int i = 0; i < l; i++) {
      int x = (int)(entity.posX + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
      int y = (int)(entity.posY + entity.world.rand.nextInt((int)distance) - (int)(distance / 4.0D));
      int z = (int)(entity.posZ + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
      BlockPos pos = new BlockPos(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
      IBlockState blockstate = entity.world.getBlockState(pos.up());
      IBlockState blockstate1 = entity.world.getBlockState(pos);

      if (blockstate.getBlock() != Blocks.AIR && blockstate1.getBlock() == Blocks.AIR) {
        if (mobGriefing(entity.world)) {
          blockstate1 = entity.world.getBlockState(pos);
          BlockEvent.BreakEvent event = null;
          if (!entity.world.isRemote)
          {
            event = new BlockEvent.BreakEvent(entity.world, pos.up(), blockstate, (EntityPlayer)FakePlayerFactory.get((WorldServer)entity.world, MoCreatures.MOCFAKEPLAYER));
          }

          if (event != null && !event.isCanceled()) {
            entity.world.setBlockToAir(pos);
          }
        }
        return Block.getIdFromBlock(blockstate.getBlock());
      }
    }
    return 0;
  }

  public static IBlockState destroyRandomBlockWithIBlockState(Entity entity, double distance) {
    int l = (int)(distance * distance * distance);
    for (int i = 0; i < l; i++) {
      int x = (int)(entity.posX + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
      int y = (int)(entity.posY + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
      int z = (int)(entity.posZ + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
      BlockPos pos = new BlockPos(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
      IBlockState stateAbove = entity.world.getBlockState(pos.up());
      IBlockState stateTarget = entity.world.getBlockState(pos);

      if (pos.getY() != (int)entity.posY - 1.0D || pos.getX() != (int)Math.floor(entity.posX) || pos.getZ() != (int)Math.floor(entity.posZ))
      {

        if (stateTarget.getBlock() != Blocks.AIR && stateTarget.getBlock() != Blocks.WATER && stateTarget.getBlock() != Blocks.BEDROCK && stateAbove
          .getBlock() == Blocks.AIR) {

          if (mobGriefing(entity.world)) {
            BlockEvent.BreakEvent event = null;
            if (!entity.world.isRemote)
            {
              event = new BlockEvent.BreakEvent(entity.world, pos, stateTarget, (EntityPlayer)FakePlayerFactory.get((WorldServer)entity.world, MoCreatures.MOCFAKEPLAYER));
            }

            if (event != null && !event.isCanceled()) {
              entity.world.setBlockToAir(pos);
            } else {

              stateTarget = null;
            }
          }
          if (stateTarget != null)
            return stateTarget;
        }
      }
    }
    return null;
  }











  public static int[] getRandomBlockCoords(Entity entity, double distance) {
    int tempX = -9999;
    int tempY = -1;
    int tempZ = -1;
    int ii = (int)(distance * distance * distance / 2.0D);
    for (int i = 0; i < ii; i++) {
      int x = (int)(entity.posX + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
      int y = (int)(entity.posY + entity.world.rand.nextInt((int)(distance / 2.0D)) - (int)(distance / 4.0D));
      int z = (int)(entity.posZ + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
      BlockPos pos = new BlockPos(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
      IBlockState blockstate1 = entity.world.getBlockState(pos.up());
      IBlockState blockstate2 = entity.world.getBlockState(pos);
      IBlockState blockstate3 = entity.world.getBlockState(pos.east());
      IBlockState blockstate4 = entity.world.getBlockState(pos.west());
      IBlockState blockstate5 = entity.world.getBlockState(pos.down());
      IBlockState blockstate6 = entity.world.getBlockState(pos.south());
      IBlockState blockstate7 = entity.world.getBlockState(pos.north());

      float tX = x - (float)entity.posX;
      float tY = y - (float)entity.posY;
      float tZ = z - (float)entity.posZ;
      float spawnDist = tX * tX + tY * tY + tZ * tZ;

      if (allowedBlock(Block.getIdFromBlock(blockstate1.getBlock()))) if ((((blockstate2
          .getBlock() == Blocks.AIR || blockstate3.getBlock() == Blocks.AIR || blockstate4.getBlock() == Blocks.AIR || blockstate5
          .getBlock() == Blocks.AIR || blockstate6.getBlock() == Blocks.AIR || blockstate7.getBlock() == Blocks.AIR) ? 1 : 0) & ((spawnDist > 100.0F) ? 1 : 0)) != 0) {

          tempX = x;
          tempY = y;
          tempZ = z;
          break;
        }
    }
    return new int[] { tempX, tempY, tempZ };
  }

  public static BlockPos getRandomBlockPos(Entity entity, double distance) {
    int tempX = -9999;
    int tempY = -1;
    int tempZ = -1;
    int ii = (int)(distance * distance * distance / 2.0D);
    for (int i = 0; i < ii; i++) {
      int x = (int)(entity.posX + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
      int y = (int)(entity.posY + entity.world.rand.nextInt((int)(distance / 2.0D)) - (int)(distance / 4.0D));
      int z = (int)(entity.posZ + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
      BlockPos pos = new BlockPos(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
      IBlockState blockstate1 = entity.world.getBlockState(pos.up());
      IBlockState blockstate2 = entity.world.getBlockState(pos);
      IBlockState blockstate3 = entity.world.getBlockState(pos.east());
      IBlockState blockstate4 = entity.world.getBlockState(pos.west());
      IBlockState blockstate5 = entity.world.getBlockState(pos.down());
      IBlockState blockstate6 = entity.world.getBlockState(pos.south());
      IBlockState blockstate7 = entity.world.getBlockState(pos.north());

      float tX = x - (float)entity.posX;
      float tY = y - (float)entity.posY;
      float tZ = z - (float)entity.posZ;
      float spawnDist = tX * tX + tY * tY + tZ * tZ;

      if (allowedBlock(Block.getIdFromBlock(blockstate1.getBlock()))) if ((((blockstate2
          .getBlock() == Blocks.AIR || blockstate3.getBlock() == Blocks.AIR || blockstate4.getBlock() == Blocks.AIR || blockstate5
          .getBlock() == Blocks.AIR || blockstate6.getBlock() == Blocks.AIR || blockstate7.getBlock() == Blocks.AIR) ? 1 : 0) & ((spawnDist > 100.0F) ? 1 : 0)) != 0) {

          tempX = x;
          tempY = y;
          tempZ = z;
          break;
        }
    }
    return new BlockPos(MathHelper.floor(tempX), MathHelper.floor(tempY), MathHelper.floor(tempZ));
  }

  public static boolean allowedBlock(int ID) {
    return (ID != 0 && ID != 7 && ID != 8 && ID != 9 && ID != 10 && ID != 11 && ID != 23 && ID != 37 && ID != 38 && ID != 50 && ID != 51 && ID != 54 && (ID < 63 || ID > 77) && ID != 95 && ID != 90 && ID != 93 && ID != 94 && ID < 134);
  }

























  public static boolean tameWithName(EntityPlayer ep, IMoCTameable storedCreature) {
    if (ep == null) {
      return false;
    }

    if (MoCreatures.proxy.enableOwnership) {
      if (storedCreature == null) {
        ep.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "ERROR:" + TextFormatting.WHITE + "The stored creature is NULL and could not be created. Report to admin.", new Object[0]));

        return false;
      }
      int max = 0;
      max = MoCreatures.proxy.maxTamed;

      if (!MoCreatures.instance.mapData.isExistingPet(ep.getUniqueID(), storedCreature)) {
        int count = numberTamedByPlayer(ep);
        if (isThisPlayerAnOP(ep)) {
          max = MoCreatures.proxy.maxOPTamed;
        }
        if (count >= max) {
          String message = "§4" + ep.getName() + " can not tame more creatures, limit of " + max + " reached";
          ep.sendMessage((ITextComponent)new TextComponentTranslation(message, new Object[0]));
          return false;
        }
      }
    }

    storedCreature.setOwnerId(ep.getUniqueID());
    MoCMessageHandler.INSTANCE.sendTo((IMessage)new MoCMessageNameGUI(((Entity)storedCreature).getEntityId()), (EntityPlayerMP)ep);
    storedCreature.setTamed(true);

    if (MoCreatures.instance.mapData != null && storedCreature.getOwnerPetId() == -1) {
      MoCreatures.instance.mapData.updateOwnerPet(storedCreature);
    }
    return true;
  }

  public static int numberTamedByPlayer(EntityPlayer ep) {
    if (MoCreatures.instance.mapData != null &&
      MoCreatures.instance.mapData.getPetData(ep.getUniqueID()) != null) {
      return MoCreatures.instance.mapData.getPetData(ep.getUniqueID()).getTamedList().tagCount();
    }

    return 0;
  }













  public static int destroyBlocksInFront(Entity entity, double distance, int strength, int height) {
    if (strength == 0) {
      return 0;
    }
    int count = 0;
    float strengthF = strength;
    double newPosX = entity.posX - distance * Math.cos((realAngle(entity.rotationYaw - 90.0F) / 57.29578F));
    double newPosZ = entity.posZ - distance * Math.sin((realAngle(entity.rotationYaw - 90.0F) / 57.29578F));
    double newPosY = entity.posY;
    int x = MathHelper.floor(newPosX);
    int y = MathHelper.floor(newPosY);
    int z = MathHelper.floor(newPosZ);

    for (int i = 0; i < height; i++) {
      BlockPos pos = new BlockPos(x, y + i, z);
      IBlockState blockstate = entity.world.getBlockState(pos);
      if (blockstate.getBlock() != Blocks.AIR &&
        blockstate.getBlockHardness(entity.world, pos) <= strengthF) {
        BlockEvent.BreakEvent event = null;
        if (!entity.world.isRemote)
        {
          event = new BlockEvent.BreakEvent(entity.world, pos, blockstate, (EntityPlayer)FakePlayerFactory.get((WorldServer)entity.world, MoCreatures.MOCFAKEPLAYER));
        }

        if (event != null && !event.isCanceled()) {
          blockstate.getBlock().dropBlockAsItemWithChance(entity.world, pos, blockstate, 0.2F * strengthF, 1);
          entity.world.setBlockToAir(pos);
          if (entity.world.rand.nextInt(3) == 0) {
            playCustomSound(entity, MoCSoundEvents.ENTITY_GOLEM_WALK);
            count++;
          }
        }
      }
    }

    return count;
  }

  public static void dropInventory(Entity entity, MoCAnimalChest animalchest) {
    if (animalchest == null || entity.world.isRemote) {
      return;
    }

    int i = MathHelper.floor(entity.posX);
    int j = MathHelper.floor((entity.getEntityBoundingBox()).minY);
    int k = MathHelper.floor(entity.posZ);

    for (int l = 0; l < animalchest.getSizeInventory(); l++) {
      ItemStack itemstack = animalchest.getStackInSlot(l);
      if (!itemstack.isEmpty()) {


        float f = entity.world.rand.nextFloat() * 0.8F + 0.1F;
        float f1 = entity.world.rand.nextFloat() * 0.8F + 0.1F;
        float f2 = entity.world.rand.nextFloat() * 0.8F + 0.1F;
        float f3 = 0.05F;

        EntityItem entityitem = new EntityItem(entity.world, (i + f), (j + f1), (k + f2), itemstack);
        entityitem.motionX = ((float)entity.world.rand.nextGaussian() * f3);
        entityitem.motionY = ((float)entity.world.rand.nextGaussian() * f3 + 0.2F);
        entityitem.motionZ = ((float)entity.world.rand.nextGaussian() * f3);
        entity.world.spawnEntity((Entity)entityitem);
        animalchest.setInventorySlotContents(l, ItemStack.EMPTY);
      }
    }
  }





  public static void dropHorseAmulet(MoCEntityTameableAnimal entity) {
    if (!entity.world.isRemote) {
      ItemStack stack = getProperAmulet((MoCEntityAnimal)entity);
      if (stack == null) {
        return;
      }
      if (stack.getTagCompound() == null) {
        stack.setTagCompound(new NBTTagCompound());
      }
      NBTTagCompound nbtt = stack.getTagCompound();
      EntityPlayer epOwner = entity.world.getPlayerEntityByUUID(entity.getOwnerId());

      try {
        nbtt.setString("SpawnClass", "WildHorse");
        nbtt.setFloat("Health", entity.getHealth());
        nbtt.setInteger("Edad", entity.getEdad());
        nbtt.setString("Name", entity.getPetName());
        nbtt.setBoolean("Rideable", entity.getIsRideable());
        nbtt.setInteger("Armor", entity.getArmorType());
        nbtt.setInteger("CreatureType", entity.getType());
        nbtt.setBoolean("Adult", entity.getIsAdult());
        nbtt.setString("OwnerName", (epOwner != null) ? epOwner.getName() : "");
        if (entity.getOwnerId() != null) {
          nbtt.setUniqueId("OwnerUUID", entity.getOwnerId());
        }
        nbtt.setInteger("PetId", entity.getOwnerPetId());
        int amuletType = 1;
        if (stack.getItem() == MoCItems.petamuletfull) {
          amuletType = 2;
        } else if (stack.getItem() == MoCItems.amuletghostfull) {
          amuletType = 3;
        }
        nbtt.setBoolean("Ghost", (amuletType == 3));
      } catch (Exception e) {
        e.printStackTrace();
      }

      if (epOwner != null && epOwner.inventory.getFirstEmptyStack() != -1) {

        epOwner.inventory.addItemStackToInventory(stack);
      } else {
        EntityItem entityitem = new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, stack);
        entityitem.setPickupDelay(20);
        entity.world.spawnEntity((Entity)entityitem);
      }
    }
  }





  public static void dropAmulet(IMoCEntity entity, int amuletType, EntityPlayer player) {
    if (!player.world.isRemote) {
      ItemStack stack = new ItemStack((Item)MoCItems.fishnetfull, 1, 0);
      if (amuletType == 2) {
        stack = new ItemStack((Item)MoCItems.petamuletfull, 1, 0);
      }
      if (amuletType == 3) {
        stack = new ItemStack((Item)MoCItems.amuletghostfull, 1, 0);
      }
      if (stack.getTagCompound() == null) {
        stack.setTagCompound(new NBTTagCompound());
      }
      NBTTagCompound nbtt = stack.getTagCompound();
      try {
        EntityEntry entry = EntityRegistry.getEntry(((Entity)entity).getClass());
        String petClass = entry.getName().replace("mocreatures:", "");
        nbtt.setString("SpawnClass", petClass);
        nbtt.setUniqueId("OwnerUUID", player.getUniqueID());
        nbtt.setString("OwnerName", player.getName());
        nbtt.setFloat("Health", ((EntityLiving)entity).getHealth());
        nbtt.setInteger("Edad", entity.getEdad());
        nbtt.setString("Name", entity.getPetName());
        nbtt.setInteger("CreatureType", entity.getType());
        nbtt.setBoolean("Adult", entity.getIsAdult());
        nbtt.setInteger("PetId", entity.getOwnerPetId());
        nbtt.setBoolean("Ghost", (amuletType == 3));
      } catch (Exception e) {
        e.printStackTrace();
      }

      if (!player.inventory.addItemStackToInventory(stack)) {
        EntityItem entityitem = new EntityItem(((EntityLivingBase)entity).world, ((EntityLivingBase)entity).posX, ((EntityLivingBase)entity).posY, ((EntityLivingBase)entity).posZ, stack);


        entityitem.setPickupDelay(20);
        ((EntityLivingBase)entity).world.spawnEntity((Entity)entityitem);
      }
    }
  }







  public static ItemStack getProperAmulet(MoCEntityAnimal entity) {
    if (entity instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse) {
      if (entity.getType() == 26 || entity.getType() == 27 || entity.getType() == 28) {
        return new ItemStack((Item)MoCItems.amuletbonefull, 1, 0);
      }
      if (entity.getType() > 47 && entity.getType() < 60) {
        return new ItemStack((Item)MoCItems.amuletfairyfull, 1, 0);
      }
      if (entity.getType() == 39 || entity.getType() == 40) {
        return new ItemStack((Item)MoCItems.amuletpegasusfull, 1, 0);
      }
      if (entity.getType() == 21 || entity.getType() == 22) {
        return new ItemStack((Item)MoCItems.amuletghostfull, 1, 0);
      }
    }
    return null;
  }








  public static ItemStack getProperEmptyAmulet(MoCEntityAnimal entity) {
    if (entity instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse) {
      if (entity.getType() == 26 || entity.getType() == 27 || entity.getType() == 28) {
        return new ItemStack((Item)MoCItems.amuletbone, 1, 0);
      }
      if (entity.getType() > 49 && entity.getType() < 60) {
        return new ItemStack((Item)MoCItems.amuletfairy, 1, 0);
      }
      if (entity.getType() == 39 || entity.getType() == 40) {
        return new ItemStack((Item)MoCItems.amuletpegasus, 1, 0);
      }
      if (entity.getType() == 21 || entity.getType() == 22) {
        return new ItemStack((Item)MoCItems.amuletghost, 1, 0);
      }
    }
    return null;
  }

  public static int countPlayersInDimension(WorldServer world, int dimension) {
    int playersInDimension = 0;
    for (int j = 0; j < world.playerEntities.size(); j++) {
      EntityPlayer entityplayermp = world.playerEntities.get(j);

      if (entityplayermp.dimension == dimension) {
        playersInDimension++;
      }
    }
    return playersInDimension;
  }

  public static boolean isThisPlayerAnOP(EntityPlayer player) {
    if (player.world.isRemote) {
      return false;
    }

    return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().canSendCommands(player.getGameProfile());
  }

  public static void spawnMaggots(World world, Entity entity) {
    if (!world.isRemote) {
      int var2 = 1 + world.rand.nextInt(4);
      for (int i = 0; i < var2; i++) {
        float var4 = ((i % 2) - 0.5F) * 1.0F / 4.0F;
        float var5 = ((i / 2) - 0.5F) * 1.0F / 4.0F;
        MoCEntityMaggot maggot = new MoCEntityMaggot(world);
        maggot.setLocationAndAngles(entity.posX + var4, entity.posY + 0.5D, entity.posZ + var5, world.rand.nextFloat() * 360.0F, 0.0F);
        world.spawnEntity((Entity)maggot);
      }
    }
  }

  public static void getPathToEntity(EntityLiving creatureToMove, Entity entityTarget, float f) {
    Path pathentity = creatureToMove.getNavigator().getPathToEntityLiving(entityTarget);
    if (pathentity != null && f < 12.0F) {
      creatureToMove.getNavigator().setPath(pathentity, 1.0D);
    }
  }

  public static void runLikeHell(EntityLiving runningEntity, Entity boogey) {
    double d = runningEntity.posX - boogey.posX;
    double d1 = runningEntity.posZ - boogey.posZ;
    double d2 = Math.atan2(d, d1);
    d2 += (runningEntity.world.rand.nextFloat() - runningEntity.world.rand.nextFloat()) * 0.75D;
    double d3 = runningEntity.posX + Math.sin(d2) * 8.0D;
    double d4 = runningEntity.posZ + Math.cos(d2) * 8.0D;
    int i = MathHelper.floor(d3);
    int j = MathHelper.floor((runningEntity.getEntityBoundingBox()).minY);
    int k = MathHelper.floor(d4);
    int l = 0;

    while (l < 16) {


      int i1 = i + runningEntity.world.rand.nextInt(4) - runningEntity.world.rand.nextInt(4);
      int j1 = j + runningEntity.world.rand.nextInt(3) - runningEntity.world.rand.nextInt(3);
      int k1 = k + runningEntity.world.rand.nextInt(4) - runningEntity.world.rand.nextInt(4);
      BlockPos pos = new BlockPos(i1, j1, k1);
      if (j1 > 4 && (runningEntity.world
        .getBlockState(pos).getBlock() == Blocks.AIR || runningEntity.world.getBlockState(pos).getBlock() == Blocks.SNOW) && runningEntity.world
        .getBlockState(pos.down()).getBlock() != Blocks.AIR) {
        runningEntity.getNavigator().tryMoveToXYZ(i1, j1, k1, 1.0D);
        break;
      }
      l++;
    }
  }










  public static boolean findNearPlayerAndPoison(Entity poisoner, boolean needsToBeInWater) {
    EntityPlayer entityplayertarget = poisoner.world.getClosestPlayerToEntity(poisoner, 2.0D);
    if (entityplayertarget == null || ((!needsToBeInWater || !entityplayertarget.isInWater()) && needsToBeInWater) || poisoner
      .getDistance((Entity)entityplayertarget) >= 2.0F || entityplayertarget.capabilities.disableDamage || (
      entityplayertarget.getRidingEntity() != null && entityplayertarget.getRidingEntity() instanceof net.minecraft.entity.item.EntityBoat))
    {






      return false; }
    MoCreatures.poisonPlayer(entityplayertarget);
    entityplayertarget.addPotionEffect(new PotionEffect(MobEffects.POISON, 120, 0));
    return true; } public static boolean isTamed(Entity entity) {
    if (entity instanceof EntityTameable && (
      (EntityTameable)entity).isTamed()) {
      return true;
    }

    NBTTagCompound nbt = new NBTTagCompound();
    entity.writeToNBT(nbt);
    if (nbt != null) {
      if (nbt.hasKey("Owner") && !nbt.getString("Owner").equals("")) {
        return true;
      }
      if (nbt.hasKey("Tamed") && nbt.getBoolean("Tamed") == true) {
        return true;
      }
    }
    return false;
  }








  public static void ThrowStone(Entity throwerEntity, Entity targetEntity, IBlockState state, double speedMod, double height) {
    ThrowStone(throwerEntity, (int)targetEntity.posX, (int)targetEntity.posY, (int)targetEntity.posZ, state, speedMod, height);
  }










  public static void ThrowStone(Entity throwerEntity, int X, int Y, int Z, IBlockState state) {
    ThrowStone(throwerEntity, X, Y, Z, state, 10.0D, 0.25D);
  }








  public static void ThrowStone(Entity throwerEntity, int X, int Y, int Z, IBlockState state, double speedMod, double height) {
    MoCEntityThrowableRock etrock = new MoCEntityThrowableRock(throwerEntity.world, throwerEntity, throwerEntity.posX, throwerEntity.posY + 0.5D, throwerEntity.posZ);

    throwerEntity.world.spawnEntity((Entity)etrock);
    etrock.setState(state);
    etrock.setBehavior(0);
    etrock.motionX = (X - throwerEntity.posX) / speedMod;
    etrock.motionY = (Y - throwerEntity.posY) / speedMod + height;
    etrock.motionZ = (Z - throwerEntity.posZ) / speedMod;
  }






  public static float getMyMovementSpeed(Entity entity) {
    return MathHelper.sqrt(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ);
  }

  public static EntityItem getClosestFood(Entity entity, double d) {
    double d1 = -1.0D;
    EntityItem entityitem = null;
    List<Entity> list = entity.world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().expand(d, d, d));
    for (int k = 0; k < list.size(); k++) {
      Entity entity1 = list.get(k);
      if (entity1 instanceof EntityItem) {


        EntityItem entityitem1 = (EntityItem)entity1;
        if (isItemEdible(entityitem1.getItem().getItem())) {


          double d2 = entityitem1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
          if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
            d1 = d2;
            entityitem = entityitem1;
          }
        }
      }
    }  return entityitem;
  }







  public static boolean isItemEdible(Item item1) {
    return (item1 instanceof net.minecraft.item.ItemFood || item1 instanceof net.minecraft.item.ItemSeeds || item1 == Items.WHEAT || item1 == Items.SUGAR || item1 == Items.CAKE || item1 == Items.EGG);
  }


  public static boolean isItemEdibleforCarnivores(Item item1) {
    return (item1 == Items.BEEF || item1 == Items.CHICKEN || item1 == Items.COOKED_BEEF || item1 == Items.COOKED_CHICKEN || item1 == Items.COOKED_FISH || item1 == Items.RABBIT || item1 == Items.COOKED_MUTTON || item1 == Items.COOKED_PORKCHOP || item1 == Items.MUTTON || item1 == Items.COOKED_RABBIT || item1 == Items.FISH || item1 == Items.PORKCHOP);
  }




  public static NBTTagCompound getEntityData(Entity entity) {
    if (!entity.getEntityData().hasKey("mocreatures")) {
      entity.getEntityData().setTag("mocreatures", (NBTBase)new NBTTagCompound());
    }

    return entity.getEntityData().getCompoundTag("mocreatures");
  }

  public static void findMobRider(Entity mountEntity) {
    List<Entity> list = mountEntity.world.getEntitiesWithinAABBExcludingEntity(mountEntity, mountEntity.getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
    for (int i = 0; i < list.size(); i++) {
      Entity entity = list.get(i);
      if (entity instanceof EntityMob) {


        EntityMob entitymob = (EntityMob)entity;
        if (entitymob.getRidingEntity() == null && (entitymob instanceof net.minecraft.entity.monster.EntitySkeleton || entitymob instanceof net.minecraft.entity.monster.EntityZombie || entitymob instanceof drzhark.mocreatures.entity.monster.MoCEntitySilverSkeleton)) {

          if (!mountEntity.world.isRemote)
            entitymob.startRiding(mountEntity);
          break;
        }
      }
    }
  }

  public static void copyDataFromOld(Entity source, Entity target) {
    NBTTagCompound nbttagcompound = target.writeToNBT(new NBTTagCompound());
    nbttagcompound.removeTag("Dimension");
    source.readFromNBT(nbttagcompound);
  }

  public static boolean dismountSneakingPlayer(EntityLiving entity) {
    if (!entity.isRiding()) return false;
    Entity entityRidden = entity.getRidingEntity();
    if (entityRidden instanceof EntityLivingBase && ((EntityLivingBase)entityRidden).isSneaking()) {
      entity.dismountRidingEntity();
      double dist = -1.5D;
      double newPosX = entityRidden.posX + dist * Math.sin((((EntityLivingBase)entityRidden).renderYawOffset / 57.29578F));
      double newPosZ = entityRidden.posZ - dist * Math.cos((((EntityLivingBase)entityRidden).renderYawOffset / 57.29578F));
      entity.setPosition(newPosX, entityRidden.posY + 2.0D, newPosZ);
      playCustomSound((Entity)entity, SoundEvents.ENTITY_CHICKEN_EGG);
      return true;
    }
    return false;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\MoCTools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
