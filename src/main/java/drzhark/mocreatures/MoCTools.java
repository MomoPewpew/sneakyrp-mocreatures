/*      */ package drzhark.mocreatures;
/*      */
/*      */ import drzhark.mocreatures.entity.IMoCEntity;
/*      */ import drzhark.mocreatures.entity.IMoCTameable;
/*      */ import drzhark.mocreatures.entity.MoCEntityAnimal;
/*      */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*      */ import drzhark.mocreatures.entity.ambient.MoCEntityMaggot;
/*      */ import drzhark.mocreatures.entity.item.MoCEntityThrowableRock;
/*      */ import drzhark.mocreatures.init.MoCItems;
/*      */ import drzhark.mocreatures.init.MoCSoundEvents;
/*      */ import drzhark.mocreatures.inventory.MoCAnimalChest;
/*      */ import drzhark.mocreatures.network.MoCMessageHandler;
/*      */ import drzhark.mocreatures.network.message.MoCMessageNameGUI;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockJukebox;
/*      */ import net.minecraft.block.BlockLiquid;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.IEntityLivingData;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.monster.EntityMob;
/*      */ import net.minecraft.entity.monster.EntitySlime;
/*      */ import net.minecraft.entity.passive.EntityTameable;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.init.MobEffects;
/*      */ import net.minecraft.init.SoundEvents;
/*      */ import net.minecraft.inventory.EntityEquipmentSlot;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.pathfinding.Path;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.SoundCategory;
/*      */ import net.minecraft.util.SoundEvent;
/*      */ import net.minecraft.util.math.AxisAlignedBB;
/*      */ import net.minecraft.util.math.BlockPos;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraft.util.math.Vec3d;
/*      */ import net.minecraft.util.text.ITextComponent;
/*      */ import net.minecraft.util.text.TextComponentTranslation;
/*      */ import net.minecraft.util.text.TextFormatting;
/*      */ import net.minecraft.world.Explosion;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldServer;
/*      */ import net.minecraft.world.biome.Biome;
/*      */ import net.minecraft.world.biome.BiomeProvider;
/*      */ import net.minecraftforge.common.DimensionManager;
/*      */ import net.minecraftforge.common.util.FakePlayerFactory;
/*      */ import net.minecraftforge.event.world.BlockEvent;
/*      */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*      */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*      */ import net.minecraftforge.fml.common.registry.EntityEntry;
/*      */ import net.minecraftforge.fml.common.registry.EntityRegistry;
/*      */ import net.minecraftforge.fml.relauncher.Side;
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */ public class MoCTools
/*      */ {
/*      */   public static void spawnSlimes(World world, Entity entity) {
/*   88 */     if (!world.isRemote) {
/*      */
/*   90 */       int var2 = 1 + world.rand.nextInt(1);
/*      */
/*   92 */       for (int i = 0; i < var2; i++) {
/*   93 */         float var4 = ((i % 2) - 0.5F) * 1.0F / 4.0F;
/*   94 */         float var5 = ((i / 2) - 0.5F) * 1.0F / 4.0F;
/*   95 */         EntitySlime var6 = new EntitySlime(world);
/*      */
/*   97 */         var6.setLocationAndAngles(entity.posX + var4, entity.posY + 0.5D, entity.posZ + var5, world.rand.nextFloat() * 360.0F, 0.0F);
/*   98 */         world.spawnEntity((Entity)var6);
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */   public static void dropSaddle(MoCEntityAnimal entity, World world) {
/*  107 */     if (!entity.getIsRideable() || world.isRemote) {
/*      */       return;
/*      */     }
/*  110 */     dropCustomItem((Entity)entity, world, new ItemStack((Item)MoCItems.horsesaddle, 1));
/*  111 */     entity.setRideable(false);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */   public static void dropBags(MoCEntityAnimal entity, World world) {
/*  118 */     if (world.isRemote) {
/*      */       return;
/*      */     }
/*  121 */     dropCustomItem((Entity)entity, world, new ItemStack((Block)Blocks.CHEST, 1));
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */   public static void dropCustomItem(Entity entity, World world, ItemStack itemstack) {
/*  128 */     if (world.isRemote) {
/*      */       return;
/*      */     }
/*      */
/*  132 */     EntityItem entityitem = new EntityItem(world, entity.posX, entity.posY, entity.posZ, itemstack);
/*  133 */     float f3 = 0.05F;
/*  134 */     entityitem.motionX = ((float)world.rand.nextGaussian() * f3);
/*  135 */     entityitem.motionY = ((float)world.rand.nextGaussian() * f3 + 0.2F);
/*  136 */     entityitem.motionZ = ((float)world.rand.nextGaussian() * f3);
/*  137 */     world.spawnEntity((Entity)entityitem);
/*      */   }
/*      */
/*      */   public static void bigsmack(Entity entity, Entity entity1, float force) {
/*  141 */     double d = entity.posX - entity1.posX;
/*  142 */     double d1 = entity.posZ - entity1.posZ;
/*  143 */     for (d1 = entity.posZ - entity1.posZ; d * d + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
/*  144 */       d = (Math.random() - Math.random()) * 0.01D;
/*      */     }
/*      */
/*  147 */     float f = MathHelper.sqrt(d * d + d1 * d1);
/*  148 */     entity1.motionX /= 2.0D;
/*  149 */     entity1.motionY /= 2.0D;
/*  150 */     entity1.motionZ /= 2.0D;
/*  151 */     entity1.motionX -= d / f * force;
/*  152 */     entity1.motionY += force;
/*  153 */     entity1.motionZ -= d1 / f * force;
/*  154 */     if (entity1.motionY > force) {
/*  155 */       entity1.motionY = force;
/*      */     }
/*      */   }
/*      */
/*      */   public static void buckleMobs(EntityLiving entityattacker, Double dist, World world) {
/*  160 */     List<Entity> list = world.getEntitiesWithinAABBExcludingEntity((Entity)entityattacker, entityattacker.getEntityBoundingBox().expand(dist.doubleValue(), 2.0D, dist.doubleValue()));
/*  161 */     for (int i = 0; i < list.size(); i++) {
/*  162 */       Entity entitytarget = list.get(i);
/*  163 */       if (entitytarget instanceof EntityLiving && (!entityattacker.isBeingRidden() || entitytarget != entityattacker.getRidingEntity())) {
/*      */
/*      */
/*      */
/*  167 */         entitytarget.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)entityattacker), 2.0F);
/*  168 */         bigsmack((Entity)entityattacker, entitytarget, 0.6F);
/*  169 */         playCustomSound((Entity)entityattacker, MoCSoundEvents.ENTITY_GENERIC_TUD);
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */   public static void buckleMobsNotPlayers(EntityLiving entityattacker, Double dist, World world) {
/*  175 */     List<Entity> list = world.getEntitiesWithinAABBExcludingEntity((Entity)entityattacker, entityattacker.getEntityBoundingBox().expand(dist.doubleValue(), 2.0D, dist.doubleValue()));
/*  176 */     for (int i = 0; i < list.size(); i++) {
/*  177 */       Entity entitytarget = list.get(i);
/*  178 */       if (entitytarget instanceof EntityLiving && !(entitytarget instanceof EntityPlayer) && (
/*  179 */         !entityattacker.isBeingRidden() || entitytarget != entityattacker.getRidingEntity())) {
/*      */
/*      */
/*      */
/*  183 */         entitytarget.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)entityattacker), 2.0F);
/*  184 */         bigsmack((Entity)entityattacker, entitytarget, 0.6F);
/*  185 */         playCustomSound((Entity)entityattacker, MoCSoundEvents.ENTITY_GENERIC_TUD);
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */
/*      */
/*      */   public static void spawnNearPlayer(EntityPlayer player, int entityId, int numberToSpawn) {
/*  193 */     WorldServer world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(player.world.provider.getDimensionType().getId());
/*  194 */     for (int i = 0; i < numberToSpawn; i++) {
/*  195 */       EntityLiving entityliving = null;
/*      */       try {
/*  197 */         Class<? extends EntityLiving> entityClass = MoCreatures.instaSpawnerMap.get(Integer.valueOf(entityId));
/*  198 */         entityliving = entityClass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
/*  199 */       } catch (Exception e) {
/*  200 */         e.printStackTrace();
/*      */       }
/*      */
/*  203 */       if (entityliving != null) {
/*  204 */         entityliving.setLocationAndAngles(player.posX - 1.0D, player.posY, player.posZ - 1.0D, player.rotationYaw, player.rotationPitch);
/*  205 */         world.spawnEntity((Entity)entityliving);
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */
/*      */   public static void spawnNearPlayerbyName(EntityPlayer player, String eName, int numberToSpawn) {
/*  212 */     WorldServer world = FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(player.world.provider.getDimensionType().getId());
/*      */
/*  214 */     for (int i = 0; i < numberToSpawn; i++) {
/*  215 */       EntityLiving entityToSpawn = null;
/*      */       try {
/*  217 */         MoCEntityData entityData = MoCreatures.mocEntityMap.get(eName);
/*  218 */         Class<? extends EntityLiving> myClass = entityData.getEntityClass();
/*  219 */         entityToSpawn = myClass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
/*  220 */       } catch (Exception e) {
/*  221 */         e.printStackTrace();
/*      */       }
/*      */
/*  224 */       if (entityToSpawn != null) {
/*  225 */         IEntityLivingData entitylivingdata = null;
/*  226 */         entityToSpawn.onInitialSpawn(player.world.getDifficultyForLocation(new BlockPos((Entity)entityToSpawn)), entitylivingdata);
/*  227 */         entityToSpawn.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
/*  228 */         world.spawnEntity((Entity)entityToSpawn);
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */   public static void playCustomSound(Entity entity, SoundEvent customSound) {
/*  234 */     playCustomSound(entity, customSound, 1.0F);
/*      */   }
/*      */
/*      */   public static void playCustomSound(Entity entity, SoundEvent customSound, float volume) {
/*  238 */     entity.playSound(customSound, volume, 1.0F + (entity.world.rand.nextFloat() - entity.world.rand.nextFloat()) * 0.2F);
/*      */   }
/*      */
/*      */   public static boolean NearMaterialWithDistance(Entity entity, Double double1, Material mat) {
/*  242 */     AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(double1.doubleValue(), double1.doubleValue(), double1.doubleValue());
/*  243 */     int i = MathHelper.floor(axisalignedbb.minX);
/*  244 */     int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
/*  245 */     int k = MathHelper.floor(axisalignedbb.minY);
/*  246 */     int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
/*  247 */     int i1 = MathHelper.floor(axisalignedbb.minZ);
/*  248 */     int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
/*  249 */     for (int k1 = i; k1 < j; k1++) {
/*  250 */       for (int l1 = k; l1 < l; l1++) {
/*  251 */         for (int i2 = i1; i2 < j1; i2++) {
/*  252 */           IBlockState blockstate = entity.world.getBlockState(new BlockPos(k1, l1, i2));
/*  253 */           if (blockstate.getBlock() != Blocks.AIR && blockstate.getMaterial() == mat) {
/*  254 */             return true;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  259 */     return false;
/*      */   }
/*      */
/*      */   public static boolean isNearBlockName(Entity entity, Double dist, String blockName) {
/*  263 */     AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(dist.doubleValue(), dist.doubleValue() / 2.0D, dist.doubleValue());
/*  264 */     int i = MathHelper.floor(axisalignedbb.minX);
/*  265 */     int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
/*  266 */     int k = MathHelper.floor(axisalignedbb.minY);
/*  267 */     int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
/*  268 */     int i1 = MathHelper.floor(axisalignedbb.minZ);
/*  269 */     int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
/*  270 */     for (int k1 = i; k1 < j; k1++) {
/*  271 */       for (int l1 = k; l1 < l; l1++) {
/*  272 */         for (int i2 = i1; i2 < j1; i2++) {
/*  273 */           IBlockState blockstate = entity.world.getBlockState(new BlockPos(k1, l1, i2));
/*      */
/*  275 */           if (blockstate.getBlock() != Blocks.AIR) {
/*  276 */             String nameToCheck = "";
/*  277 */             nameToCheck = blockstate.getBlock().getUnlocalizedName();
/*  278 */             if (nameToCheck != null && nameToCheck != "" &&
/*  279 */               nameToCheck.equals(blockName)) {
/*  280 */               return true;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */
/*  287 */     return false;
/*      */   }
/*      */
/*      */   public static BlockJukebox.TileEntityJukebox nearJukeBoxRecord(Entity entity, Double dist) {
/*  291 */     AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(dist.doubleValue(), dist.doubleValue() / 2.0D, dist.doubleValue());
/*  292 */     int i = MathHelper.floor(axisalignedbb.minX);
/*  293 */     int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
/*  294 */     int k = MathHelper.floor(axisalignedbb.minY);
/*  295 */     int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
/*  296 */     int i1 = MathHelper.floor(axisalignedbb.minZ);
/*  297 */     int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
/*  298 */     for (int k1 = i; k1 < j; k1++) {
/*  299 */       for (int l1 = k; l1 < l; l1++) {
/*  300 */         for (int i2 = i1; i2 < j1; i2++) {
/*  301 */           BlockPos pos = new BlockPos(k1, l1, i2);
/*  302 */           IBlockState blockstate = entity.world.getBlockState(pos);
/*  303 */           if (!entity.world.isAirBlock(pos) &&
/*  304 */             blockstate.getBlock() instanceof BlockJukebox) {
/*  305 */             BlockJukebox.TileEntityJukebox juky = (BlockJukebox.TileEntityJukebox)entity.world.getTileEntity(pos);
/*  306 */             return juky;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */
/*  312 */     return null;
/*      */   }
/*      */
/*      */   public static void checkForTwistedEntities(World world) {
/*  316 */     for (int l = 0; l < world.loadedEntityList.size(); l++) {
/*  317 */       Entity entity = world.loadedEntityList.get(l);
/*  318 */       if (entity instanceof EntityLivingBase) {
/*  319 */         EntityLivingBase twisted = (EntityLivingBase)entity;
/*  320 */         if (twisted.deathTime > 0 && twisted.getRidingEntity() == null && twisted.getHealth() > 0.0F) {
/*  321 */           twisted.deathTime = 0;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */   public static double getSqDistanceTo(Entity entity, double i, double j, double k) {
/*  328 */     double l = entity.posX - i;
/*  329 */     double i1 = entity.posY - j;
/*  330 */     double j1 = entity.posZ - k;
/*  331 */     return Math.sqrt(l * l + i1 * i1 + j1 * j1);
/*      */   }
/*      */
/*      */   public static int[] ReturnNearestMaterialCoord(Entity entity, Material material, Double double1, Double yOff) {
/*  335 */     double shortestDistance = -1.0D;
/*  336 */     double distance = 0.0D;
/*  337 */     int x = -9999;
/*  338 */     int y = -1;
/*  339 */     int z = -1;
/*      */
/*  341 */     AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(double1.doubleValue(), yOff.doubleValue(), double1.doubleValue());
/*  342 */     int i = MathHelper.floor(axisalignedbb.minX);
/*  343 */     int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
/*  344 */     int k = MathHelper.floor(axisalignedbb.minY);
/*  345 */     int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
/*  346 */     int i1 = MathHelper.floor(axisalignedbb.minZ);
/*  347 */     int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
/*  348 */     for (int k1 = i; k1 < j; k1++) {
/*  349 */       for (int l1 = k; l1 < l; l1++) {
/*  350 */         for (int i2 = i1; i2 < j1; i2++) {
/*  351 */           BlockPos pos = new BlockPos(k1, l1, i2);
/*  352 */           IBlockState blockstate = entity.world.getBlockState(pos);
/*  353 */           if (blockstate.getBlock() != Blocks.AIR && blockstate.getMaterial() == material) {
/*  354 */             distance = getSqDistanceTo(entity, k1, l1, i2);
/*  355 */             if (shortestDistance == -1.0D) {
/*  356 */               x = k1;
/*  357 */               y = l1;
/*  358 */               z = i2;
/*  359 */               shortestDistance = distance;
/*      */             }
/*      */
/*  362 */             if (distance < shortestDistance) {
/*  363 */               x = k1;
/*  364 */               y = l1;
/*  365 */               z = i2;
/*  366 */               shortestDistance = distance;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */
/*  373 */     if (entity.posX > x) {
/*  374 */       x -= 2;
/*      */     } else {
/*  376 */       x += 2;
/*      */     }
/*  378 */     if (entity.posZ > z) {
/*  379 */       z -= 2;
/*      */     } else {
/*  381 */       z += 2;
/*      */     }
/*  383 */     return new int[] { x, y, z };
/*      */   }
/*      */
/*      */   public static int[] ReturnNearestBlockCoord(Entity entity, Block block1, Double dist) {
/*  387 */     double shortestDistance = -1.0D;
/*  388 */     double distance = 0.0D;
/*  389 */     int x = -9999;
/*  390 */     int y = -1;
/*  391 */     int z = -1;
/*      */
/*  393 */     AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(dist.doubleValue(), dist.doubleValue(), dist.doubleValue());
/*  394 */     int i = MathHelper.floor(axisalignedbb.minX);
/*  395 */     int j = MathHelper.floor(axisalignedbb.maxX + 1.0D);
/*  396 */     int k = MathHelper.floor(axisalignedbb.minY);
/*  397 */     int l = MathHelper.floor(axisalignedbb.maxY + 1.0D);
/*  398 */     int i1 = MathHelper.floor(axisalignedbb.minZ);
/*  399 */     int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
/*  400 */     for (int k1 = i; k1 < j; k1++) {
/*  401 */       for (int l1 = k; l1 < l; l1++) {
/*  402 */         for (int i2 = i1; i2 < j1; i2++) {
/*  403 */           BlockPos pos = new BlockPos(k1, l1, i2);
/*  404 */           IBlockState blockstate = entity.world.getBlockState(pos);
/*  405 */           if (blockstate.getBlock() != Blocks.AIR && blockstate.getBlock() == block1) {
/*  406 */             distance = getSqDistanceTo(entity, k1, l1, i2);
/*  407 */             if (shortestDistance == -1.0D) {
/*  408 */               x = k1;
/*  409 */               y = l1;
/*  410 */               z = i2;
/*  411 */               shortestDistance = distance;
/*      */             }
/*      */
/*  414 */             if (distance < shortestDistance) {
/*  415 */               x = k1;
/*  416 */               y = l1;
/*  417 */               z = i2;
/*  418 */               shortestDistance = distance;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */
/*  425 */     if (entity.posX > x) {
/*  426 */       x -= 2;
/*      */     } else {
/*  428 */       x += 2;
/*      */     }
/*  430 */     if (entity.posZ > z) {
/*  431 */       z -= 2;
/*      */     } else {
/*  433 */       z += 2;
/*      */     }
/*  435 */     return new int[] { x, y, z };
/*      */   }
/*      */
/*      */
/*      */   public static void MoveCreatureToXYZ(EntityCreature movingEntity, int x, int y, int z, float f) {
/*  440 */     Path pathentity = movingEntity.getNavigator().getPathToXYZ(x, y, z);
/*  441 */     if (pathentity != null) {
/*  442 */       movingEntity.getNavigator().setPath(pathentity, f);
/*      */     }
/*      */   }
/*      */
/*      */   public static void MoveToWater(EntityCreature entity) {
/*  447 */     int[] ai = ReturnNearestMaterialCoord((Entity)entity, Material.WATER, Double.valueOf(20.0D), Double.valueOf(2.0D));
/*  448 */     if (ai[0] > -1000) {
/*  449 */       MoveCreatureToXYZ(entity, ai[0], ai[1], ai[2], 24.0F);
/*      */     }
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static float realAngle(float origAngle) {
/*  460 */     return origAngle % 360.0F;
/*      */   }
/*      */
/*      */   public static void SlideEntityToXYZ(Entity entity, int x, int y, int z) {
/*  464 */     if (entity != null) {
/*  465 */       if (entity.posY < y) {
/*  466 */         entity.motionY += 0.15D;
/*      */       }
/*  468 */       if (entity.posX < x) {
/*  469 */         double d = x - entity.posX;
/*  470 */         if (d > 0.5D) {
/*  471 */           entity.motionX += 0.05D;
/*      */         }
/*      */       } else {
/*  474 */         double d1 = entity.posX - x;
/*  475 */         if (d1 > 0.5D) {
/*  476 */           entity.motionX -= 0.05D;
/*      */         }
/*      */       }
/*  479 */       if (entity.posZ < z) {
/*  480 */         double d2 = z - entity.posZ;
/*  481 */         if (d2 > 0.5D) {
/*  482 */           entity.motionZ += 0.05D;
/*      */         }
/*      */       } else {
/*  485 */         double d3 = entity.posZ - z;
/*  486 */         if (d3 > 0.5D) {
/*  487 */           entity.motionZ -= 0.05D;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */   public static float distanceToSurface(Entity entity) {
/*  494 */     int i = MathHelper.floor(entity.posX);
/*  495 */     int j = MathHelper.floor(entity.posY);
/*  496 */     int k = MathHelper.floor(entity.posZ);
/*  497 */     IBlockState blockstate = entity.world.getBlockState(new BlockPos(i, j, k));
/*  498 */     if (blockstate.getBlock() != Blocks.AIR && blockstate.getMaterial() == Material.WATER) {
/*  499 */       for (int x = 1; x < 64; x++) {
/*  500 */         blockstate = entity.world.getBlockState(new BlockPos(i, j + x, k));
/*  501 */         if (blockstate.getBlock() == Blocks.AIR || blockstate.getMaterial() != Material.WATER) {
/*  502 */           return x;
/*      */         }
/*      */       }
/*      */     }
/*  506 */     return 0.0F;
/*      */   }
/*      */
/*      */   public static double waterSurfaceAtGivenPosition(double posX, double posY, double posZ, World worldIn) {
/*  510 */     int i = MathHelper.floor(posX);
/*  511 */     int j = MathHelper.floor(posY);
/*  512 */     int k = MathHelper.floor(posZ);
/*  513 */     IBlockState blockstate = worldIn.getBlockState(new BlockPos(i, j, k));
/*  514 */     if (blockstate.getBlock() != Blocks.AIR && blockstate.getMaterial() == Material.WATER) {
/*  515 */       for (int x = 1; x < 64; x++) {
/*  516 */         blockstate = worldIn.getBlockState(new BlockPos(i, j + x, k));
/*  517 */         if (blockstate.getBlock() == Blocks.AIR || blockstate.getMaterial() != Material.WATER) {
/*  518 */           return (j + x);
/*      */         }
/*      */       }
/*      */     }
/*  522 */     return 0.0D;
/*      */   }
/*      */
/*      */   public static double waterSurfaceAtGivenEntity(Entity entity) {
/*  526 */     return waterSurfaceAtGivenPosition(entity.posX, entity.posY, entity.posZ, entity.world);
/*      */   }
/*      */
/*      */   public static float distanceToSurface(double posX, double posY, double posZ, World worldIn) {
/*  530 */     int i = MathHelper.floor(posX);
/*  531 */     int j = MathHelper.floor(posY);
/*  532 */     int k = MathHelper.floor(posZ);
/*  533 */     IBlockState blockstate = worldIn.getBlockState(new BlockPos(i, j, k));
/*  534 */     if (blockstate.getBlock() != Blocks.AIR && blockstate.getMaterial() == Material.WATER) {
/*  535 */       for (int x = 1; x < 64; x++) {
/*  536 */         blockstate = worldIn.getBlockState(new BlockPos(i, j + x, k));
/*  537 */         if (blockstate.getBlock() == Blocks.AIR || blockstate.getMaterial() != Material.WATER) {
/*  538 */           return x;
/*      */         }
/*      */       }
/*      */     }
/*  542 */     return 0.0F;
/*      */   }
/*      */
/*      */   public static int distanceToFloor(Entity entity) {
/*  546 */     int i = MathHelper.floor(entity.posX);
/*  547 */     int j = MathHelper.floor(entity.posY);
/*  548 */     int k = MathHelper.floor(entity.posZ);
/*  549 */     for (int x = 0; x < 64; x++) {
/*  550 */       Block block = entity.world.getBlockState(new BlockPos(i, j - x, k)).getBlock();
/*  551 */       if (block != Blocks.AIR) {
/*  552 */         return x;
/*      */       }
/*      */     }
/*      */
/*  556 */     return 0;
/*      */   }
/*      */
/*      */   public boolean isInsideOfMaterial(Material material, Entity entity) {
/*  560 */     double d = entity.posY + entity.getEyeHeight();
/*  561 */     int i = MathHelper.floor(entity.posX);
/*  562 */     int j = MathHelper.floor(MathHelper.floor(d));
/*  563 */     int k = MathHelper.floor(entity.posZ);
/*  564 */     BlockPos pos = new BlockPos(i, j, k);
/*  565 */     IBlockState blockstate = entity.world.getBlockState(pos);
/*  566 */     if (blockstate.getBlock() != Blocks.AIR && blockstate.getMaterial() == material) {
/*  567 */       float f = BlockLiquid.getLiquidHeightPercent(blockstate.getBlock().getMetaFromState(blockstate)) - 0.1111111F;
/*  568 */       float f1 = (j + 1) - f;
/*  569 */       return (d < f1);
/*      */     }
/*  571 */     return false;
/*      */   }
/*      */
/*      */
/*      */   public static void disorientEntity(Entity entity) {
/*  576 */     double rotD = 0.0D;
/*  577 */     double motD = 0.0D;
/*  578 */     double d = entity.world.rand.nextGaussian();
/*  579 */     double d1 = 0.1D * d;
/*  580 */     motD = 0.2D * d1 + 0.8D * motD;
/*  581 */     entity.motionX += motD;
/*  582 */     entity.motionZ += motD;
/*  583 */     double d2 = 0.78D * d;
/*  584 */     rotD = 0.125D * d2 + 0.875D * rotD;
/*  585 */     entity.rotationYaw = (float)(entity.rotationYaw + rotD);
/*  586 */     entity.rotationPitch = (float)(entity.rotationPitch + rotD);
/*      */   }
/*      */
/*      */   public static void slowEntity(Entity entity) {
/*  590 */     entity.motionX *= 0.8D;
/*  591 */     entity.motionZ *= 0.8D;
/*      */   }
/*      */
/*      */   public static int colorize(int i) {
/*  595 */     return (i ^ 0xFFFFFFFF) & 0xF;
/*      */   }
/*      */
/*      */   public int countEntities(Class<? extends EntityLiving> class1, World world) {
/*  599 */     int i = 0;
/*  600 */     for (int j = 0; j < world.loadedEntityList.size(); j++) {
/*  601 */       Entity entity = world.loadedEntityList.get(j);
/*  602 */       if (class1.isAssignableFrom(entity.getClass())) {
/*  603 */         i++;
/*      */       }
/*      */     }
/*      */
/*  607 */     return i;
/*      */   }
/*      */
/*      */
/*      */   public static float distToPlayer(Entity entity) {
/*  612 */     return 0.0F;
/*      */   }
/*      */
/*      */   public static String biomeName(World world, BlockPos pos) {
/*  616 */     BiomeProvider biomeProvider = world.getBiomeProvider();
/*  617 */     if (biomeProvider == null) {
/*  618 */       return null;
/*      */     }
/*      */
/*  621 */     Biome Biome = biomeProvider.getBiome(pos);
/*      */
/*      */
/*  624 */     if (Biome == null) {
/*  625 */       return null;
/*      */     }
/*  627 */     return Biome.getBiomeName();
/*      */   }
/*      */
/*      */
/*      */   public static Biome Biomekind(World world, BlockPos pos) {
/*  632 */     return world.getBiome(pos);
/*      */   }
/*      */
/*      */
/*      */   public static void destroyDrops(Entity entity, double d) {
/*  637 */     if (!MoCreatures.proxy.destroyDrops) {
/*      */       return;
/*      */     }
/*      */
/*  641 */     List<Entity> list = entity.world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().expand(d, d, d));
/*      */
/*  643 */     for (int i = 0; i < list.size(); i++) {
/*  644 */       Entity entity1 = list.get(i);
/*  645 */       if (entity1 instanceof EntityItem) {
/*      */
/*      */
/*  648 */         EntityItem entityitem = (EntityItem)entity1;
/*  649 */         if (entityitem != null && entityitem.getAge() < 50)
/*  650 */           entityitem.setDead();
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */   public static void repelMobs(Entity entity1, Double dist, World world) {
/*  656 */     List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity1, entity1.getEntityBoundingBox().expand(dist.doubleValue(), 4.0D, dist.doubleValue()));
/*  657 */     for (int i = 0; i < list.size(); i++) {
/*  658 */       Entity entity = list.get(i);
/*  659 */       if (entity instanceof EntityMob) {
/*      */
/*      */
/*  662 */         EntityMob entitymob = (EntityMob)entity;
/*  663 */         entitymob.setAttackTarget(null);
/*      */
/*  665 */         entitymob.getNavigator().clearPath();
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static void dropGoodies(World world, Entity entity) {
/*  676 */     if (world.isRemote) {
/*      */       return;
/*      */     }
/*      */
/*  680 */     EntityItem entityitem = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Blocks.LOG, 16));
/*  681 */     entityitem.setPickupDelay(10);
/*  682 */     world.spawnEntity((Entity)entityitem);
/*      */
/*  684 */     EntityItem entityitem2 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.DIAMOND, 64));
/*  685 */     entityitem2.setPickupDelay(10);
/*  686 */     world.spawnEntity((Entity)entityitem2);
/*      */
/*  688 */     EntityItem entityitem3 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Blocks.PUMPKIN, 6));
/*  689 */     entityitem3.setPickupDelay(10);
/*  690 */     world.spawnEntity((Entity)entityitem3);
/*      */
/*  692 */     EntityItem entityitem4 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Blocks.COBBLESTONE, 64));
/*  693 */     entityitem4.setPickupDelay(10);
/*  694 */     world.spawnEntity((Entity)entityitem4);
/*      */
/*  696 */     EntityItem entityitem5 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.APPLE, 24));
/*  697 */     entityitem5.setPickupDelay(10);
/*  698 */     world.spawnEntity((Entity)entityitem5);
/*      */
/*  700 */     EntityItem entityitem6 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.LEATHER, 64));
/*  701 */     entityitem6.setPickupDelay(10);
/*  702 */     world.spawnEntity((Entity)entityitem6);
/*      */
/*  704 */     EntityItem entityitem7 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack((Item)MoCItems.recordshuffle, 6));
/*  705 */     entityitem7.setPickupDelay(10);
/*  706 */     world.spawnEntity((Entity)entityitem7);
/*      */
/*  708 */     EntityItem entityitem8 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.IRON_INGOT, 64));
/*  709 */     entityitem8.setPickupDelay(10);
/*  710 */     world.spawnEntity((Entity)entityitem8);
/*      */
/*  712 */     EntityItem entityitem9 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.GOLD_INGOT, 12));
/*  713 */     entityitem9.setPickupDelay(10);
/*  714 */     world.spawnEntity((Entity)entityitem9);
/*      */
/*  716 */     EntityItem entityitem10 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.STRING, 32));
/*  717 */     entityitem10.setPickupDelay(10);
/*  718 */     world.spawnEntity((Entity)entityitem10);
/*      */
/*  720 */     EntityItem entityitem12 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack((Block)Blocks.RED_FLOWER, 6));
/*  721 */     entityitem12.setPickupDelay(10);
/*  722 */     world.spawnEntity((Entity)entityitem12);
/*      */
/*  724 */     EntityItem entityitem13 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.BLAZE_ROD, 12));
/*  725 */     entityitem13.setPickupDelay(10);
/*  726 */     world.spawnEntity((Entity)entityitem13);
/*      */
/*  728 */     EntityItem entityitem14 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.ENDER_PEARL, 12));
/*  729 */     entityitem14.setPickupDelay(10);
/*  730 */     world.spawnEntity((Entity)entityitem14);
/*      */
/*  732 */     EntityItem entityitem15 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.GHAST_TEAR, 12));
/*  733 */     entityitem15.setPickupDelay(10);
/*  734 */     world.spawnEntity((Entity)entityitem15);
/*      */
/*  736 */     EntityItem entityitem16 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Blocks.LAPIS_BLOCK, 2));
/*  737 */     entityitem16.setPickupDelay(10);
/*  738 */     world.spawnEntity((Entity)entityitem16);
/*      */
/*  740 */     EntityItem entityitem17 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Items.BONE, 12));
/*  741 */     entityitem17.setPickupDelay(10);
/*  742 */     world.spawnEntity((Entity)entityitem17);
/*      */
/*  744 */     EntityItem entityitem18 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack((Item)MoCItems.unicornhorn, 16));
/*  745 */     entityitem18.setPickupDelay(10);
/*  746 */     world.spawnEntity((Entity)entityitem18);
/*      */
/*  748 */     EntityItem entityitem19 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack((Block)Blocks.FIRE, 32));
/*  749 */     entityitem19.setPickupDelay(10);
/*  750 */     world.spawnEntity((Entity)entityitem19);
/*      */
/*  752 */     EntityItem entityitem20 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack((Item)MoCItems.essencedarkness, 6));
/*  753 */     entityitem20.setPickupDelay(10);
/*  754 */     world.spawnEntity((Entity)entityitem20);
/*      */
/*  756 */     EntityItem entityitem21 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack((Item)MoCItems.essenceundead, 6));
/*  757 */     entityitem21.setPickupDelay(10);
/*  758 */     world.spawnEntity((Entity)entityitem21);
/*      */
/*  760 */     EntityItem entityitem22 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack((Item)MoCItems.essencefire, 6));
/*  761 */     entityitem22.setPickupDelay(10);
/*  762 */     world.spawnEntity((Entity)entityitem22);
/*      */
/*      */
/*  765 */     EntityItem entityitem23 = new EntityItem(world, entity.posX, entity.posY, entity.posZ, new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 6, 15));
/*  766 */     entityitem23.setPickupDelay(10);
/*  767 */     world.spawnEntity((Entity)entityitem23);
/*      */   }
/*      */
/*      */
/*      */   public static boolean mobGriefing(World world) {
/*  772 */     return world.getGameRules().getBoolean("mobGriefing");
/*      */   }
/*      */
/*      */   public static void DestroyBlast(Entity entity, double d, double d1, double d2, float f, boolean flag) {
/*  776 */     EntityPlayer player = (entity instanceof EntityPlayer) ? (EntityPlayer)entity : null;
/*  777 */     entity.world.playSound(player, d, d1, d2, MoCSoundEvents.ENTITY_GENERIC_DESTROY, SoundCategory.HOSTILE, 4.0F, (1.0F + (entity.world.rand
/*  778 */         .nextFloat() - entity.world.rand.nextFloat()) * 0.2F) * 0.7F);
/*      */
/*  780 */     boolean mobGriefing = mobGriefing(entity.world);
/*      */
/*  782 */     HashSet<BlockPos> hashset = new HashSet<>();
/*  783 */     float f1 = f;
/*  784 */     int i = 16;
/*  785 */     for (int j = 0; j < i; j++) {
/*  786 */       for (int l = 0; l < i; l++) {
/*  787 */         for (int j1 = 0; j1 < i; j1++) {
/*  788 */           if (j == 0 || j == i - 1 || l == 0 || l == i - 1 || j1 == 0 || j1 == i - 1) {
/*      */
/*      */
/*  791 */             double d3 = (j / (i - 1.0F) * 2.0F - 1.0F);
/*  792 */             double d4 = (l / (i - 1.0F) * 2.0F - 1.0F);
/*  793 */             double d5 = (j1 / (i - 1.0F) * 2.0F - 1.0F);
/*  794 */             double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
/*  795 */             d3 /= d6;
/*  796 */             d4 /= d6;
/*  797 */             d5 /= d6;
/*  798 */             float f2 = f * (0.7F + entity.world.rand.nextFloat() * 0.6F);
/*  799 */             double d8 = d;
/*  800 */             double d10 = d1;
/*  801 */             double d12 = d2;
/*  802 */             float f3 = 0.3F;
/*  803 */             float f4 = 5.0F;
/*      */
/*  805 */             while (f2 > 0.0F) {
/*      */
/*      */
/*  808 */               int k5 = MathHelper.floor(d8);
/*  809 */               int l5 = MathHelper.floor(d10);
/*  810 */               int i6 = MathHelper.floor(d12);
/*  811 */               BlockPos pos = new BlockPos(k5, l5, i6);
/*  812 */               IBlockState blockstate = entity.world.getBlockState(pos);
/*  813 */               if (blockstate.getBlock() != Blocks.AIR) {
/*  814 */                 f4 = blockstate.getBlockHardness(entity.world, pos);
/*  815 */                 f2 -= (blockstate.getBlock().getExplosionResistance(entity) + 0.3F) * f3 / 10.0F;
/*      */               }
/*  817 */               if (f2 > 0.0F && d10 > entity.posY && f4 < 3.0F) {
/*  818 */                 hashset.add(pos);
/*      */               }
/*  820 */               d8 += d3 * f3;
/*  821 */               d10 += d4 * f3;
/*  822 */               d12 += d5 * f3;
/*  823 */               f2 -= f3 * 0.75F;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */
/*      */
/*  831 */     f *= 2.0F;
/*  832 */     if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
/*  833 */       int k = MathHelper.floor(d - f - 1.0D);
/*  834 */       int i1 = MathHelper.floor(d + f + 1.0D);
/*  835 */       int k1 = MathHelper.floor(d1 - f - 1.0D);
/*  836 */       int l1 = MathHelper.floor(d1 + f + 1.0D);
/*  837 */       int i2 = MathHelper.floor(d2 - f - 1.0D);
/*  838 */       int j2 = MathHelper.floor(d2 + f + 1.0D);
/*  839 */       List<Entity> list = entity.world.getEntitiesWithinAABBExcludingEntity(entity, new AxisAlignedBB(k, k1, i2, i1, l1, j2));
/*  840 */       Vec3d vec3d = new Vec3d(d, d1, d2);
/*  841 */       for (int k2 = 0; k2 < list.size(); k2++) {
/*  842 */         Entity entity1 = list.get(k2);
/*  843 */         double d7 = entity1.getDistance(d, d1, d2) / f;
/*  844 */         if (d7 <= 1.0D) {
/*      */
/*      */
/*  847 */           double d9 = entity1.posX - d;
/*  848 */           double d11 = entity1.posY - d1;
/*  849 */           double d13 = entity1.posZ - d2;
/*  850 */           double d15 = MathHelper.sqrt(d9 * d9 + d11 * d11 + d13 * d13);
/*  851 */           d9 /= d15;
/*  852 */           d11 /= d15;
/*  853 */           d13 /= d15;
/*  854 */           double d17 = entity.world.getBlockDensity(vec3d, entity1.getEntityBoundingBox());
/*  855 */           double d19 = (1.0D - d7) * d17;
/*      */
/*      */
/*  858 */           if (!(entity1 instanceof drzhark.mocreatures.entity.monster.MoCEntityOgre)) {
/*  859 */             entity1.attackEntityFrom(DamageSource.GENERIC, (int)((d19 * d19 + d19) / 2.0D * 3.0D * f + 1.0D));
/*  860 */             double d21 = d19;
/*  861 */             entity1.motionX += d9 * d21;
/*  862 */             entity1.motionY += d11 * d21;
/*  863 */             entity1.motionZ += d13 * d21;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*  868 */     f = f1;
/*  869 */     ArrayList<BlockPos> arraylist = new ArrayList<>();
/*  870 */     arraylist.addAll(hashset);
/*      */
/*  872 */     for (int l2 = arraylist.size() - 1; l2 >= 0; l2--) {
/*  873 */       BlockPos chunkposition = arraylist.get(l2);
/*  874 */       IBlockState blockstate = entity.world.getBlockState(chunkposition);
/*  875 */       for (int j5 = 0; j5 < 5; j5++) {
/*  876 */         double d14 = (chunkposition.getX() + entity.world.rand.nextFloat());
/*  877 */         double d16 = (chunkposition.getY() + entity.world.rand.nextFloat());
/*  878 */         double d18 = (chunkposition.getZ() + entity.world.rand.nextFloat());
/*  879 */         double d20 = d14 - d;
/*  880 */         double d22 = d16 - d1;
/*  881 */         double d23 = d18 - d2;
/*  882 */         double d24 = MathHelper.sqrt(d20 * d20 + d22 * d22 + d23 * d23);
/*  883 */         d20 /= d24;
/*  884 */         d22 /= d24;
/*  885 */         d23 /= d24;
/*  886 */         double d25 = 0.5D / (d24 / f + 0.1D);
/*  887 */         d25 *= (entity.world.rand.nextFloat() * entity.world.rand.nextFloat() + 0.3F);
/*  888 */         d25--;
/*  889 */         d20 *= d25;
/*  890 */         d22 *= d25 - 1.0D;
/*  891 */         d23 *= d25;
/*      */
/*      */
/*      */
/*      */
/*  896 */         if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
/*  897 */           entity.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (d14 + d * 1.0D) / 2.0D, (d16 + d1 * 1.0D) / 2.0D, (d18 + d2 * 1.0D) / 2.0D, d20, d22, d23, new int[0]);
/*      */
/*  899 */           entity.motionX -= 0.001000000047497451D;
/*  900 */           entity.motionY -= 0.001000000047497451D;
/*      */         }
/*      */       }
/*      */
/*      */
/*      */
/*  906 */       if (mobGriefing && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && blockstate.getBlock() != Blocks.AIR) {
/*  907 */         BlockEvent.BreakEvent event = null;
/*  908 */         if (!entity.world.isRemote) {
/*      */
/*      */           try {
/*  911 */             event = new BlockEvent.BreakEvent(entity.world, chunkposition, blockstate, (EntityPlayer)FakePlayerFactory.get(
/*  912 */                   DimensionManager.getWorld(entity.world.provider.getDimensionType().getId()), MoCreatures.MOCFAKEPLAYER));
/*  913 */           } catch (Throwable throwable) {}
/*      */         }
/*      */
/*  916 */         if (event != null && !event.isCanceled()) {
/*  917 */           blockstate.getBlock().dropBlockAsItemWithChance(entity.world, chunkposition, blockstate, 0.3F, 1);
/*  918 */           entity.world.setBlockToAir(chunkposition);
/*      */
/*      */
/*  921 */           Explosion explosion = new Explosion(entity.world, null, chunkposition.getX(), chunkposition.getY(), chunkposition.getZ(), 3.0F, false, false);
/*  922 */           blockstate.getBlock().onBlockDestroyedByExplosion(entity.world, chunkposition, explosion);
/*      */         }
/*      */       }
/*      */     }
/*      */
/*      */
/*  928 */     if (mobGriefing && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && flag) {
/*  929 */       for (int i3 = arraylist.size() - 1; i3 >= 0; i3--) {
/*  930 */         BlockPos chunkposition1 = arraylist.get(i3);
/*  931 */         IBlockState blockstate = entity.world.getBlockState(chunkposition1);
/*  932 */         if (blockstate.getBlock() == Blocks.AIR && entity.world.rand.nextInt(8) == 0) {
/*  933 */           BlockEvent.BreakEvent event = null;
/*  934 */           if (!entity.world.isRemote)
/*      */           {
/*  936 */             event = new BlockEvent.BreakEvent(entity.world, chunkposition1, blockstate, (EntityPlayer)FakePlayerFactory.get((WorldServer)entity.world, MoCreatures.MOCFAKEPLAYER));
/*      */           }
/*      */
/*  939 */           if (event != null && !event.isCanceled()) {
/*  940 */             entity.world.setBlockState(chunkposition1, Blocks.FIRE.getDefaultState(), 3);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */   public static int despawnVanillaAnimals(World world) {
/*  948 */     return despawnVanillaAnimals(world, null);
/*      */   }
/*      */
/*      */
/*      */   public static int despawnVanillaAnimals(World world, List[] classList) {
/*  953 */     int count = 0;
/*  954 */     for (int j = 0; j < world.loadedEntityList.size(); j++) {
/*  955 */       Entity entity = world.loadedEntityList.get(j);
/*  956 */       if (entity instanceof EntityLiving && (entity instanceof net.minecraft.entity.passive.EntityCow || entity instanceof net.minecraft.entity.passive.EntitySheep || entity instanceof net.minecraft.entity.passive.EntityPig || entity instanceof net.minecraft.entity.passive.EntityChicken || entity instanceof net.minecraft.entity.passive.EntitySquid || entity instanceof net.minecraft.entity.passive.EntityWolf))
/*      */       {
/*      */
/*  959 */         count += entityDespawnCheck(world, (EntityLiving)entity);
/*      */       }
/*      */     }
/*  962 */     return count;
/*      */   }
/*      */
/*      */
/*      */   protected static int entityDespawnCheck(World world, EntityLiving entityliving) {
/*  967 */     int count = 0;
/*  968 */     EntityPlayer entityplayer = world.getClosestPlayerToEntity((Entity)entityliving, -1.0D);
/*  969 */     if (entityplayer != null) {
/*      */
/*  971 */       double d = ((Entity)entityplayer).posX - entityliving.posX;
/*  972 */       double d1 = ((Entity)entityplayer).posY - entityliving.posY;
/*  973 */       double d2 = ((Entity)entityplayer).posZ - entityliving.posZ;
/*  974 */       double d3 = d * d + d1 * d1 + d2 * d2;
/*  975 */       if (d3 > 16384.0D) {
/*  976 */         entityliving.setDead();
/*  977 */         count++;
/*      */       }
/*      */
/*  980 */       if (entityliving.getIdleTime() > 600 && world.rand.nextInt(800) == 0) {
/*  981 */         if (d3 < 1024.0D) {
/*      */
/*  983 */           entityliving.attackEntityFrom(DamageSource.causeMobDamage(null), 0.0F);
/*      */         } else {
/*  985 */           entityliving.setDead();
/*  986 */           count++;
/*      */         }
/*      */       }
/*      */     }
/*  990 */     return count;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static void updatePlayerArmorEffects(EntityPlayer player) {
/* 1008 */     ItemStack[] mystack = new ItemStack[4];
/* 1009 */     mystack[0] = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
/* 1010 */     mystack[1] = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
/* 1011 */     mystack[2] = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
/* 1012 */     mystack[3] = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
/*      */
/*      */
/* 1015 */     if (mystack[0] != null && mystack[0].getItem() == MoCItems.scorpBootsCave && mystack[1] != null && mystack[1]
/* 1016 */       .getItem() == MoCItems.scorpLegsCave && mystack[2] != null && mystack[2].getItem() == MoCItems.scorpPlateCave && mystack[3] != null && mystack[3]
/* 1017 */       .getItem() == MoCItems.scorpHelmetCave) {
/* 1018 */       player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 0));
/*      */
/*      */       return;
/*      */     }
/*      */
/* 1023 */     if (mystack[0] != null && mystack[0].getItem() == MoCItems.scorpBootsNether && mystack[1] != null && mystack[1]
/* 1024 */       .getItem() == MoCItems.scorpLegsNether && mystack[2] != null && mystack[2].getItem() == MoCItems.scorpPlateNether && mystack[3] != null && mystack[3]
/* 1025 */       .getItem() == MoCItems.scorpHelmetNether) {
/* 1026 */       player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 300, 0));
/*      */
/*      */       return;
/*      */     }
/*      */
/* 1031 */     if (mystack[0] != null && mystack[0].getItem() == MoCItems.scorpBootsFrost && mystack[1] != null && mystack[1]
/* 1032 */       .getItem() == MoCItems.scorpLegsFrost && mystack[2] != null && mystack[2].getItem() == MoCItems.scorpPlateFrost && mystack[3] != null && mystack[3]
/* 1033 */       .getItem() == MoCItems.scorpHelmetFrost) {
/* 1034 */       player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 300, 0));
/*      */
/*      */       return;
/*      */     }
/*      */
/* 1039 */     if (mystack[0] != null && mystack[0].getItem() == MoCItems.scorpBootsDirt && mystack[1] != null && mystack[1]
/* 1040 */       .getItem() == MoCItems.scorpLegsDirt && mystack[2] != null && mystack[2].getItem() == MoCItems.scorpPlateDirt && mystack[3] != null && mystack[3]
/* 1041 */       .getItem() == MoCItems.scorpHelmetDirt) {
/* 1042 */       player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 70, 0));
/*      */       return;
/*      */     }
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static int destroyRandomBlock(Entity entity, double distance) {
/* 1058 */     int l = (int)(distance * distance * distance);
/* 1059 */     for (int i = 0; i < l; i++) {
/* 1060 */       int x = (int)(entity.posX + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
/* 1061 */       int y = (int)(entity.posY + entity.world.rand.nextInt((int)distance) - (int)(distance / 4.0D));
/* 1062 */       int z = (int)(entity.posZ + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
/* 1063 */       BlockPos pos = new BlockPos(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
/* 1064 */       IBlockState blockstate = entity.world.getBlockState(pos.up());
/* 1065 */       IBlockState blockstate1 = entity.world.getBlockState(pos);
/*      */
/* 1067 */       if (blockstate.getBlock() != Blocks.AIR && blockstate1.getBlock() == Blocks.AIR) {
/* 1068 */         if (mobGriefing(entity.world)) {
/* 1069 */           blockstate1 = entity.world.getBlockState(pos);
/* 1070 */           BlockEvent.BreakEvent event = null;
/* 1071 */           if (!entity.world.isRemote)
/*      */           {
/* 1073 */             event = new BlockEvent.BreakEvent(entity.world, pos.up(), blockstate, (EntityPlayer)FakePlayerFactory.get((WorldServer)entity.world, MoCreatures.MOCFAKEPLAYER));
/*      */           }
/*      */
/* 1076 */           if (event != null && !event.isCanceled()) {
/* 1077 */             entity.world.setBlockToAir(pos);
/*      */           }
/*      */         }
/* 1080 */         return Block.getIdFromBlock(blockstate.getBlock());
/*      */       }
/*      */     }
/* 1083 */     return 0;
/*      */   }
/*      */
/*      */   public static IBlockState destroyRandomBlockWithIBlockState(Entity entity, double distance) {
/* 1087 */     int l = (int)(distance * distance * distance);
/* 1088 */     for (int i = 0; i < l; i++) {
/* 1089 */       int x = (int)(entity.posX + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
/* 1090 */       int y = (int)(entity.posY + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
/* 1091 */       int z = (int)(entity.posZ + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
/* 1092 */       BlockPos pos = new BlockPos(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
/* 1093 */       IBlockState stateAbove = entity.world.getBlockState(pos.up());
/* 1094 */       IBlockState stateTarget = entity.world.getBlockState(pos);
/*      */
/* 1096 */       if (pos.getY() != (int)entity.posY - 1.0D || pos.getX() != (int)Math.floor(entity.posX) || pos.getZ() != (int)Math.floor(entity.posZ))
/*      */       {
/*      */
/* 1099 */         if (stateTarget.getBlock() != Blocks.AIR && stateTarget.getBlock() != Blocks.WATER && stateTarget.getBlock() != Blocks.BEDROCK && stateAbove
/* 1100 */           .getBlock() == Blocks.AIR) {
/*      */
/* 1102 */           if (mobGriefing(entity.world)) {
/* 1103 */             BlockEvent.BreakEvent event = null;
/* 1104 */             if (!entity.world.isRemote)
/*      */             {
/* 1106 */               event = new BlockEvent.BreakEvent(entity.world, pos, stateTarget, (EntityPlayer)FakePlayerFactory.get((WorldServer)entity.world, MoCreatures.MOCFAKEPLAYER));
/*      */             }
/*      */
/* 1109 */             if (event != null && !event.isCanceled()) {
/* 1110 */               entity.world.setBlockToAir(pos);
/*      */             } else {
/*      */
/* 1113 */               stateTarget = null;
/*      */             }
/*      */           }
/* 1116 */           if (stateTarget != null)
/* 1117 */             return stateTarget;
/*      */         }
/*      */       }
/*      */     }
/* 1121 */     return null;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static int[] getRandomBlockCoords(Entity entity, double distance) {
/* 1135 */     int tempX = -9999;
/* 1136 */     int tempY = -1;
/* 1137 */     int tempZ = -1;
/* 1138 */     int ii = (int)(distance * distance * distance / 2.0D);
/* 1139 */     for (int i = 0; i < ii; i++) {
/* 1140 */       int x = (int)(entity.posX + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
/* 1141 */       int y = (int)(entity.posY + entity.world.rand.nextInt((int)(distance / 2.0D)) - (int)(distance / 4.0D));
/* 1142 */       int z = (int)(entity.posZ + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
/* 1143 */       BlockPos pos = new BlockPos(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
/* 1144 */       IBlockState blockstate1 = entity.world.getBlockState(pos.up());
/* 1145 */       IBlockState blockstate2 = entity.world.getBlockState(pos);
/* 1146 */       IBlockState blockstate3 = entity.world.getBlockState(pos.east());
/* 1147 */       IBlockState blockstate4 = entity.world.getBlockState(pos.west());
/* 1148 */       IBlockState blockstate5 = entity.world.getBlockState(pos.down());
/* 1149 */       IBlockState blockstate6 = entity.world.getBlockState(pos.south());
/* 1150 */       IBlockState blockstate7 = entity.world.getBlockState(pos.north());
/*      */
/* 1152 */       float tX = x - (float)entity.posX;
/* 1153 */       float tY = y - (float)entity.posY;
/* 1154 */       float tZ = z - (float)entity.posZ;
/* 1155 */       float spawnDist = tX * tX + tY * tY + tZ * tZ;
/*      */
/* 1157 */       if (allowedBlock(Block.getIdFromBlock(blockstate1.getBlock()))) if ((((blockstate2
/* 1158 */           .getBlock() == Blocks.AIR || blockstate3.getBlock() == Blocks.AIR || blockstate4.getBlock() == Blocks.AIR || blockstate5
/* 1159 */           .getBlock() == Blocks.AIR || blockstate6.getBlock() == Blocks.AIR || blockstate7.getBlock() == Blocks.AIR) ? 1 : 0) & ((spawnDist > 100.0F) ? 1 : 0)) != 0) {
/*      */
/* 1161 */           tempX = x;
/* 1162 */           tempY = y;
/* 1163 */           tempZ = z;
/*      */           break;
/*      */         }
/*      */     }
/* 1167 */     return new int[] { tempX, tempY, tempZ };
/*      */   }
/*      */
/*      */   public static BlockPos getRandomBlockPos(Entity entity, double distance) {
/* 1171 */     int tempX = -9999;
/* 1172 */     int tempY = -1;
/* 1173 */     int tempZ = -1;
/* 1174 */     int ii = (int)(distance * distance * distance / 2.0D);
/* 1175 */     for (int i = 0; i < ii; i++) {
/* 1176 */       int x = (int)(entity.posX + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
/* 1177 */       int y = (int)(entity.posY + entity.world.rand.nextInt((int)(distance / 2.0D)) - (int)(distance / 4.0D));
/* 1178 */       int z = (int)(entity.posZ + entity.world.rand.nextInt((int)distance) - (int)(distance / 2.0D));
/* 1179 */       BlockPos pos = new BlockPos(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z));
/* 1180 */       IBlockState blockstate1 = entity.world.getBlockState(pos.up());
/* 1181 */       IBlockState blockstate2 = entity.world.getBlockState(pos);
/* 1182 */       IBlockState blockstate3 = entity.world.getBlockState(pos.east());
/* 1183 */       IBlockState blockstate4 = entity.world.getBlockState(pos.west());
/* 1184 */       IBlockState blockstate5 = entity.world.getBlockState(pos.down());
/* 1185 */       IBlockState blockstate6 = entity.world.getBlockState(pos.south());
/* 1186 */       IBlockState blockstate7 = entity.world.getBlockState(pos.north());
/*      */
/* 1188 */       float tX = x - (float)entity.posX;
/* 1189 */       float tY = y - (float)entity.posY;
/* 1190 */       float tZ = z - (float)entity.posZ;
/* 1191 */       float spawnDist = tX * tX + tY * tY + tZ * tZ;
/*      */
/* 1193 */       if (allowedBlock(Block.getIdFromBlock(blockstate1.getBlock()))) if ((((blockstate2
/* 1194 */           .getBlock() == Blocks.AIR || blockstate3.getBlock() == Blocks.AIR || blockstate4.getBlock() == Blocks.AIR || blockstate5
/* 1195 */           .getBlock() == Blocks.AIR || blockstate6.getBlock() == Blocks.AIR || blockstate7.getBlock() == Blocks.AIR) ? 1 : 0) & ((spawnDist > 100.0F) ? 1 : 0)) != 0) {
/*      */
/* 1197 */           tempX = x;
/* 1198 */           tempY = y;
/* 1199 */           tempZ = z;
/*      */           break;
/*      */         }
/*      */     }
/* 1203 */     return new BlockPos(MathHelper.floor(tempX), MathHelper.floor(tempY), MathHelper.floor(tempZ));
/*      */   }
/*      */
/*      */   public static boolean allowedBlock(int ID) {
/* 1207 */     return (ID != 0 && ID != 7 && ID != 8 && ID != 9 && ID != 10 && ID != 11 && ID != 23 && ID != 37 && ID != 38 && ID != 50 && ID != 51 && ID != 54 && (ID < 63 || ID > 77) && ID != 95 && ID != 90 && ID != 93 && ID != 94 && ID < 134);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static boolean tameWithName(EntityPlayer ep, IMoCTameable storedCreature) {
/* 1235 */     if (ep == null) {
/* 1236 */       return false;
/*      */     }
/*      */
/* 1239 */     if (MoCreatures.proxy.enableOwnership) {
/* 1240 */       if (storedCreature == null) {
/* 1241 */         ep.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "ERROR:" + TextFormatting.WHITE + "The stored creature is NULL and could not be created. Report to admin.", new Object[0]));
/*      */
/* 1243 */         return false;
/*      */       }
/* 1245 */       int max = 0;
/* 1246 */       max = MoCreatures.proxy.maxTamed;
/*      */
/* 1248 */       if (!MoCreatures.instance.mapData.isExistingPet(ep.getUniqueID(), storedCreature)) {
/* 1249 */         int count = numberTamedByPlayer(ep);
/* 1250 */         if (isThisPlayerAnOP(ep)) {
/* 1251 */           max = MoCreatures.proxy.maxOPTamed;
/*      */         }
/* 1253 */         if (count >= max) {
/* 1254 */           String message = "§4" + ep.getName() + " can not tame more creatures, limit of " + max + " reached";
/* 1255 */           ep.sendMessage((ITextComponent)new TextComponentTranslation(message, new Object[0]));
/* 1256 */           return false;
/*      */         }
/*      */       }
/*      */     }
/*      */
/* 1261 */     storedCreature.setOwnerId(ep.getUniqueID());
/* 1262 */     MoCMessageHandler.INSTANCE.sendTo((IMessage)new MoCMessageNameGUI(((Entity)storedCreature).getEntityId()), (EntityPlayerMP)ep);
/* 1263 */     storedCreature.setTamed(true);
/*      */
/* 1265 */     if (MoCreatures.instance.mapData != null && storedCreature.getOwnerPetId() == -1) {
/* 1266 */       MoCreatures.instance.mapData.updateOwnerPet(storedCreature);
/*      */     }
/* 1268 */     return true;
/*      */   }
/*      */
/*      */   public static int numberTamedByPlayer(EntityPlayer ep) {
/* 1272 */     if (MoCreatures.instance.mapData != null &&
/* 1273 */       MoCreatures.instance.mapData.getPetData(ep.getUniqueID()) != null) {
/* 1274 */       return MoCreatures.instance.mapData.getPetData(ep.getUniqueID()).getTamedList().tagCount();
/*      */     }
/*      */
/* 1277 */     return 0;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static int destroyBlocksInFront(Entity entity, double distance, int strength, int height) {
/* 1293 */     if (strength == 0) {
/* 1294 */       return 0;
/*      */     }
/* 1296 */     int count = 0;
/* 1297 */     float strengthF = strength;
/* 1298 */     double newPosX = entity.posX - distance * Math.cos((realAngle(entity.rotationYaw - 90.0F) / 57.29578F));
/* 1299 */     double newPosZ = entity.posZ - distance * Math.sin((realAngle(entity.rotationYaw - 90.0F) / 57.29578F));
/* 1300 */     double newPosY = entity.posY;
/* 1301 */     int x = MathHelper.floor(newPosX);
/* 1302 */     int y = MathHelper.floor(newPosY);
/* 1303 */     int z = MathHelper.floor(newPosZ);
/*      */
/* 1305 */     for (int i = 0; i < height; i++) {
/* 1306 */       BlockPos pos = new BlockPos(x, y + i, z);
/* 1307 */       IBlockState blockstate = entity.world.getBlockState(pos);
/* 1308 */       if (blockstate.getBlock() != Blocks.AIR &&
/* 1309 */         blockstate.getBlockHardness(entity.world, pos) <= strengthF) {
/* 1310 */         BlockEvent.BreakEvent event = null;
/* 1311 */         if (!entity.world.isRemote)
/*      */         {
/* 1313 */           event = new BlockEvent.BreakEvent(entity.world, pos, blockstate, (EntityPlayer)FakePlayerFactory.get((WorldServer)entity.world, MoCreatures.MOCFAKEPLAYER));
/*      */         }
/*      */
/* 1316 */         if (event != null && !event.isCanceled()) {
/* 1317 */           blockstate.getBlock().dropBlockAsItemWithChance(entity.world, pos, blockstate, 0.2F * strengthF, 1);
/* 1318 */           entity.world.setBlockToAir(pos);
/* 1319 */           if (entity.world.rand.nextInt(3) == 0) {
/* 1320 */             playCustomSound(entity, MoCSoundEvents.ENTITY_GOLEM_WALK);
/* 1321 */             count++;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */
/* 1327 */     return count;
/*      */   }
/*      */
/*      */   public static void dropInventory(Entity entity, MoCAnimalChest animalchest) {
/* 1331 */     if (animalchest == null || entity.world.isRemote) {
/*      */       return;
/*      */     }
/*      */
/* 1335 */     int i = MathHelper.floor(entity.posX);
/* 1336 */     int j = MathHelper.floor((entity.getEntityBoundingBox()).minY);
/* 1337 */     int k = MathHelper.floor(entity.posZ);
/*      */
/* 1339 */     for (int l = 0; l < animalchest.getSizeInventory(); l++) {
/* 1340 */       ItemStack itemstack = animalchest.getStackInSlot(l);
/* 1341 */       if (!itemstack.isEmpty()) {
/*      */
/*      */
/* 1344 */         float f = entity.world.rand.nextFloat() * 0.8F + 0.1F;
/* 1345 */         float f1 = entity.world.rand.nextFloat() * 0.8F + 0.1F;
/* 1346 */         float f2 = entity.world.rand.nextFloat() * 0.8F + 0.1F;
/* 1347 */         float f3 = 0.05F;
/*      */
/* 1349 */         EntityItem entityitem = new EntityItem(entity.world, (i + f), (j + f1), (k + f2), itemstack);
/* 1350 */         entityitem.motionX = ((float)entity.world.rand.nextGaussian() * f3);
/* 1351 */         entityitem.motionY = ((float)entity.world.rand.nextGaussian() * f3 + 0.2F);
/* 1352 */         entityitem.motionZ = ((float)entity.world.rand.nextGaussian() * f3);
/* 1353 */         entity.world.spawnEntity((Entity)entityitem);
/* 1354 */         animalchest.setInventorySlotContents(l, ItemStack.EMPTY);
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static void dropHorseAmulet(MoCEntityTameableAnimal entity) {
/* 1364 */     if (!entity.world.isRemote) {
/* 1365 */       ItemStack stack = getProperAmulet((MoCEntityAnimal)entity);
/* 1366 */       if (stack == null) {
/*      */         return;
/*      */       }
/* 1369 */       if (stack.getTagCompound() == null) {
/* 1370 */         stack.setTagCompound(new NBTTagCompound());
/*      */       }
/* 1372 */       NBTTagCompound nbtt = stack.getTagCompound();
/* 1373 */       EntityPlayer epOwner = entity.world.getPlayerEntityByUUID(entity.getOwnerId());
/*      */
/*      */       try {
/* 1376 */         nbtt.setString("SpawnClass", "WildHorse");
/* 1377 */         nbtt.setFloat("Health", entity.getHealth());
/* 1378 */         nbtt.setInteger("Edad", entity.getEdad());
/* 1379 */         nbtt.setString("Name", entity.getPetName());
/* 1380 */         nbtt.setBoolean("Rideable", entity.getIsRideable());
/* 1381 */         nbtt.setInteger("Armor", entity.getArmorType());
/* 1382 */         nbtt.setInteger("CreatureType", entity.getType());
/* 1383 */         nbtt.setBoolean("Adult", entity.getIsAdult());
/* 1384 */         nbtt.setString("OwnerName", (epOwner != null) ? epOwner.getName() : "");
/* 1385 */         if (entity.getOwnerId() != null) {
/* 1386 */           nbtt.setUniqueId("OwnerUUID", entity.getOwnerId());
/*      */         }
/* 1388 */         nbtt.setInteger("PetId", entity.getOwnerPetId());
/* 1389 */         int amuletType = 1;
/* 1390 */         if (stack.getItem() == MoCItems.petamuletfull) {
/* 1391 */           amuletType = 2;
/* 1392 */         } else if (stack.getItem() == MoCItems.amuletghostfull) {
/* 1393 */           amuletType = 3;
/*      */         }
/* 1395 */         nbtt.setBoolean("Ghost", (amuletType == 3));
/* 1396 */       } catch (Exception e) {
/* 1397 */         e.printStackTrace();
/*      */       }
/*      */
/* 1400 */       if (epOwner != null && epOwner.inventory.getFirstEmptyStack() != -1) {
/*      */
/* 1402 */         epOwner.inventory.addItemStackToInventory(stack);
/*      */       } else {
/* 1404 */         EntityItem entityitem = new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, stack);
/* 1405 */         entityitem.setPickupDelay(20);
/* 1406 */         entity.world.spawnEntity((Entity)entityitem);
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static void dropAmulet(IMoCEntity entity, int amuletType, EntityPlayer player) {
/* 1416 */     if (!player.world.isRemote) {
/* 1417 */       ItemStack stack = new ItemStack((Item)MoCItems.fishnetfull, 1, 0);
/* 1418 */       if (amuletType == 2) {
/* 1419 */         stack = new ItemStack((Item)MoCItems.petamuletfull, 1, 0);
/*      */       }
/* 1421 */       if (amuletType == 3) {
/* 1422 */         stack = new ItemStack((Item)MoCItems.amuletghostfull, 1, 0);
/*      */       }
/* 1424 */       if (stack.getTagCompound() == null) {
/* 1425 */         stack.setTagCompound(new NBTTagCompound());
/*      */       }
/* 1427 */       NBTTagCompound nbtt = stack.getTagCompound();
/*      */       try {
/* 1429 */         EntityEntry entry = EntityRegistry.getEntry(((Entity)entity).getClass());
/* 1430 */         String petClass = entry.getName().replace("mocreatures:", "");
/* 1431 */         nbtt.setString("SpawnClass", petClass);
/* 1432 */         nbtt.setUniqueId("OwnerUUID", player.getUniqueID());
/* 1433 */         nbtt.setString("OwnerName", player.getName());
/* 1434 */         nbtt.setFloat("Health", ((EntityLiving)entity).getHealth());
/* 1435 */         nbtt.setInteger("Edad", entity.getEdad());
/* 1436 */         nbtt.setString("Name", entity.getPetName());
/* 1437 */         nbtt.setInteger("CreatureType", entity.getType());
/* 1438 */         nbtt.setBoolean("Adult", entity.getIsAdult());
/* 1439 */         nbtt.setInteger("PetId", entity.getOwnerPetId());
/* 1440 */         nbtt.setBoolean("Ghost", (amuletType == 3));
/* 1441 */       } catch (Exception e) {
/* 1442 */         e.printStackTrace();
/*      */       }
/*      */
/* 1445 */       if (!player.inventory.addItemStackToInventory(stack)) {
/* 1446 */         EntityItem entityitem = new EntityItem(((EntityLivingBase)entity).world, ((EntityLivingBase)entity).posX, ((EntityLivingBase)entity).posY, ((EntityLivingBase)entity).posZ, stack);
/*      */
/*      */
/* 1449 */         entityitem.setPickupDelay(20);
/* 1450 */         ((EntityLivingBase)entity).world.spawnEntity((Entity)entityitem);
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static ItemStack getProperAmulet(MoCEntityAnimal entity) {
/* 1462 */     if (entity instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse) {
/* 1463 */       if (entity.getType() == 26 || entity.getType() == 27 || entity.getType() == 28) {
/* 1464 */         return new ItemStack((Item)MoCItems.amuletbonefull, 1, 0);
/*      */       }
/* 1466 */       if (entity.getType() > 47 && entity.getType() < 60) {
/* 1467 */         return new ItemStack((Item)MoCItems.amuletfairyfull, 1, 0);
/*      */       }
/* 1469 */       if (entity.getType() == 39 || entity.getType() == 40) {
/* 1470 */         return new ItemStack((Item)MoCItems.amuletpegasusfull, 1, 0);
/*      */       }
/* 1472 */       if (entity.getType() == 21 || entity.getType() == 22) {
/* 1473 */         return new ItemStack((Item)MoCItems.amuletghostfull, 1, 0);
/*      */       }
/*      */     }
/* 1476 */     return null;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static ItemStack getProperEmptyAmulet(MoCEntityAnimal entity) {
/* 1487 */     if (entity instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse) {
/* 1488 */       if (entity.getType() == 26 || entity.getType() == 27 || entity.getType() == 28) {
/* 1489 */         return new ItemStack((Item)MoCItems.amuletbone, 1, 0);
/*      */       }
/* 1491 */       if (entity.getType() > 49 && entity.getType() < 60) {
/* 1492 */         return new ItemStack((Item)MoCItems.amuletfairy, 1, 0);
/*      */       }
/* 1494 */       if (entity.getType() == 39 || entity.getType() == 40) {
/* 1495 */         return new ItemStack((Item)MoCItems.amuletpegasus, 1, 0);
/*      */       }
/* 1497 */       if (entity.getType() == 21 || entity.getType() == 22) {
/* 1498 */         return new ItemStack((Item)MoCItems.amuletghost, 1, 0);
/*      */       }
/*      */     }
/* 1501 */     return null;
/*      */   }
/*      */
/*      */   public static int countPlayersInDimension(WorldServer world, int dimension) {
/* 1505 */     int playersInDimension = 0;
/* 1506 */     for (int j = 0; j < world.playerEntities.size(); j++) {
/* 1507 */       EntityPlayer entityplayermp = world.playerEntities.get(j);
/*      */
/* 1509 */       if (entityplayermp.dimension == dimension) {
/* 1510 */         playersInDimension++;
/*      */       }
/*      */     }
/* 1513 */     return playersInDimension;
/*      */   }
/*      */
/*      */   public static boolean isThisPlayerAnOP(EntityPlayer player) {
/* 1517 */     if (player.world.isRemote) {
/* 1518 */       return false;
/*      */     }
/*      */
/* 1521 */     return FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().canSendCommands(player.getGameProfile());
/*      */   }
/*      */
/*      */   public static void spawnMaggots(World world, Entity entity) {
/* 1525 */     if (!world.isRemote) {
/* 1526 */       int var2 = 1 + world.rand.nextInt(4);
/* 1527 */       for (int i = 0; i < var2; i++) {
/* 1528 */         float var4 = ((i % 2) - 0.5F) * 1.0F / 4.0F;
/* 1529 */         float var5 = ((i / 2) - 0.5F) * 1.0F / 4.0F;
/* 1530 */         MoCEntityMaggot maggot = new MoCEntityMaggot(world);
/* 1531 */         maggot.setLocationAndAngles(entity.posX + var4, entity.posY + 0.5D, entity.posZ + var5, world.rand.nextFloat() * 360.0F, 0.0F);
/* 1532 */         world.spawnEntity((Entity)maggot);
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */   public static void getPathToEntity(EntityLiving creatureToMove, Entity entityTarget, float f) {
/* 1538 */     Path pathentity = creatureToMove.getNavigator().getPathToEntityLiving(entityTarget);
/* 1539 */     if (pathentity != null && f < 12.0F) {
/* 1540 */       creatureToMove.getNavigator().setPath(pathentity, 1.0D);
/*      */     }
/*      */   }
/*      */
/*      */   public static void runLikeHell(EntityLiving runningEntity, Entity boogey) {
/* 1545 */     double d = runningEntity.posX - boogey.posX;
/* 1546 */     double d1 = runningEntity.posZ - boogey.posZ;
/* 1547 */     double d2 = Math.atan2(d, d1);
/* 1548 */     d2 += (runningEntity.world.rand.nextFloat() - runningEntity.world.rand.nextFloat()) * 0.75D;
/* 1549 */     double d3 = runningEntity.posX + Math.sin(d2) * 8.0D;
/* 1550 */     double d4 = runningEntity.posZ + Math.cos(d2) * 8.0D;
/* 1551 */     int i = MathHelper.floor(d3);
/* 1552 */     int j = MathHelper.floor((runningEntity.getEntityBoundingBox()).minY);
/* 1553 */     int k = MathHelper.floor(d4);
/* 1554 */     int l = 0;
/*      */
/* 1556 */     while (l < 16) {
/*      */
/*      */
/* 1559 */       int i1 = i + runningEntity.world.rand.nextInt(4) - runningEntity.world.rand.nextInt(4);
/* 1560 */       int j1 = j + runningEntity.world.rand.nextInt(3) - runningEntity.world.rand.nextInt(3);
/* 1561 */       int k1 = k + runningEntity.world.rand.nextInt(4) - runningEntity.world.rand.nextInt(4);
/* 1562 */       BlockPos pos = new BlockPos(i1, j1, k1);
/* 1563 */       if (j1 > 4 && (runningEntity.world
/* 1564 */         .getBlockState(pos).getBlock() == Blocks.AIR || runningEntity.world.getBlockState(pos).getBlock() == Blocks.SNOW) && runningEntity.world
/* 1565 */         .getBlockState(pos.down()).getBlock() != Blocks.AIR) {
/* 1566 */         runningEntity.getNavigator().tryMoveToXYZ(i1, j1, k1, 1.0D);
/*      */         break;
/*      */       }
/* 1569 */       l++;
/*      */     }
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static boolean findNearPlayerAndPoison(Entity poisoner, boolean needsToBeInWater) {
/* 1583 */     EntityPlayer entityplayertarget = poisoner.world.getClosestPlayerToEntity(poisoner, 2.0D);
/* 1584 */     if (entityplayertarget == null || ((!needsToBeInWater || !entityplayertarget.isInWater()) && needsToBeInWater) || poisoner
/* 1585 */       .getDistance((Entity)entityplayertarget) >= 2.0F || entityplayertarget.capabilities.disableDamage || (
/* 1586 */       entityplayertarget.getRidingEntity() != null && entityplayertarget.getRidingEntity() instanceof net.minecraft.entity.item.EntityBoat))
/*      */     {
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/* 1594 */       return false; }
/*      */     MoCreatures.poisonPlayer(entityplayertarget);
/*      */     entityplayertarget.addPotionEffect(new PotionEffect(MobEffects.POISON, 120, 0));
/*      */     return true; } public static boolean isTamed(Entity entity) {
/* 1598 */     if (entity instanceof EntityTameable && (
/* 1599 */       (EntityTameable)entity).isTamed()) {
/* 1600 */       return true;
/*      */     }
/*      */
/* 1603 */     NBTTagCompound nbt = new NBTTagCompound();
/* 1604 */     entity.writeToNBT(nbt);
/* 1605 */     if (nbt != null) {
/* 1606 */       if (nbt.hasKey("Owner") && !nbt.getString("Owner").equals("")) {
/* 1607 */         return true;
/*      */       }
/* 1609 */       if (nbt.hasKey("Tamed") && nbt.getBoolean("Tamed") == true) {
/* 1610 */         return true;
/*      */       }
/*      */     }
/* 1613 */     return false;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static void ThrowStone(Entity throwerEntity, Entity targetEntity, IBlockState state, double speedMod, double height) {
/* 1624 */     ThrowStone(throwerEntity, (int)targetEntity.posX, (int)targetEntity.posY, (int)targetEntity.posZ, state, speedMod, height);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static void ThrowStone(Entity throwerEntity, int X, int Y, int Z, IBlockState state) {
/* 1637 */     ThrowStone(throwerEntity, X, Y, Z, state, 10.0D, 0.25D);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static void ThrowStone(Entity throwerEntity, int X, int Y, int Z, IBlockState state, double speedMod, double height) {
/* 1648 */     MoCEntityThrowableRock etrock = new MoCEntityThrowableRock(throwerEntity.world, throwerEntity, throwerEntity.posX, throwerEntity.posY + 0.5D, throwerEntity.posZ);
/*      */
/* 1650 */     throwerEntity.world.spawnEntity((Entity)etrock);
/* 1651 */     etrock.setState(state);
/* 1652 */     etrock.setBehavior(0);
/* 1653 */     etrock.motionX = (X - throwerEntity.posX) / speedMod;
/* 1654 */     etrock.motionY = (Y - throwerEntity.posY) / speedMod + height;
/* 1655 */     etrock.motionZ = (Z - throwerEntity.posZ) / speedMod;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static float getMyMovementSpeed(Entity entity) {
/* 1664 */     return MathHelper.sqrt(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ);
/*      */   }
/*      */
/*      */   public static EntityItem getClosestFood(Entity entity, double d) {
/* 1668 */     double d1 = -1.0D;
/* 1669 */     EntityItem entityitem = null;
/* 1670 */     List<Entity> list = entity.world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().expand(d, d, d));
/* 1671 */     for (int k = 0; k < list.size(); k++) {
/* 1672 */       Entity entity1 = list.get(k);
/* 1673 */       if (entity1 instanceof EntityItem) {
/*      */
/*      */
/* 1676 */         EntityItem entityitem1 = (EntityItem)entity1;
/* 1677 */         if (isItemEdible(entityitem1.getItem().getItem())) {
/*      */
/*      */
/* 1680 */           double d2 = entityitem1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/* 1681 */           if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1)) {
/* 1682 */             d1 = d2;
/* 1683 */             entityitem = entityitem1;
/*      */           }
/*      */         }
/*      */       }
/* 1687 */     }  return entityitem;
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */
/*      */   public static boolean isItemEdible(Item item1) {
/* 1697 */     return (item1 instanceof net.minecraft.item.ItemFood || item1 instanceof net.minecraft.item.ItemSeeds || item1 == Items.WHEAT || item1 == Items.SUGAR || item1 == Items.CAKE || item1 == Items.EGG);
/*      */   }
/*      */
/*      */
/*      */   public static boolean isItemEdibleforCarnivores(Item item1) {
/* 1702 */     return (item1 == Items.BEEF || item1 == Items.CHICKEN || item1 == Items.COOKED_BEEF || item1 == Items.COOKED_CHICKEN || item1 == Items.COOKED_FISH || item1 == Items.RABBIT || item1 == Items.COOKED_MUTTON || item1 == Items.COOKED_PORKCHOP || item1 == Items.MUTTON || item1 == Items.COOKED_RABBIT || item1 == Items.FISH || item1 == Items.PORKCHOP);
/*      */   }
/*      */
/*      */
/*      */
/*      */
/*      */   public static NBTTagCompound getEntityData(Entity entity) {
/* 1709 */     if (!entity.getEntityData().hasKey("mocreatures")) {
/* 1710 */       entity.getEntityData().setTag("mocreatures", (NBTBase)new NBTTagCompound());
/*      */     }
/*      */
/* 1713 */     return entity.getEntityData().getCompoundTag("mocreatures");
/*      */   }
/*      */
/*      */   public static void findMobRider(Entity mountEntity) {
/* 1717 */     List<Entity> list = mountEntity.world.getEntitiesWithinAABBExcludingEntity(mountEntity, mountEntity.getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
/* 1718 */     for (int i = 0; i < list.size(); i++) {
/* 1719 */       Entity entity = list.get(i);
/* 1720 */       if (entity instanceof EntityMob) {
/*      */
/*      */
/* 1723 */         EntityMob entitymob = (EntityMob)entity;
/* 1724 */         if (entitymob.getRidingEntity() == null && (entitymob instanceof net.minecraft.entity.monster.EntitySkeleton || entitymob instanceof net.minecraft.entity.monster.EntityZombie || entitymob instanceof drzhark.mocreatures.entity.monster.MoCEntitySilverSkeleton)) {
/*      */
/* 1726 */           if (!mountEntity.world.isRemote)
/* 1727 */             entitymob.startRiding(mountEntity);
/*      */           break;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */
/*      */   public static void copyDataFromOld(Entity source, Entity target) {
/* 1735 */     NBTTagCompound nbttagcompound = target.writeToNBT(new NBTTagCompound());
/* 1736 */     nbttagcompound.removeTag("Dimension");
/* 1737 */     source.readFromNBT(nbttagcompound);
/*      */   }
/*      */
/*      */   public static boolean dismountSneakingPlayer(EntityLiving entity) {
/* 1741 */     if (!entity.isRiding()) return false;
/* 1742 */     Entity entityRidden = entity.getRidingEntity();
/* 1743 */     if (entityRidden instanceof EntityLivingBase && ((EntityLivingBase)entityRidden).isSneaking()) {
/* 1744 */       entity.dismountRidingEntity();
/* 1745 */       double dist = -1.5D;
/* 1746 */       double newPosX = entityRidden.posX + dist * Math.sin((((EntityLivingBase)entityRidden).renderYawOffset / 57.29578F));
/* 1747 */       double newPosZ = entityRidden.posZ - dist * Math.cos((((EntityLivingBase)entityRidden).renderYawOffset / 57.29578F));
/* 1748 */       entity.setPosition(newPosX, entityRidden.posY + 2.0D, newPosZ);
/* 1749 */       playCustomSound((Entity)entity, SoundEvents.ENTITY_CHICKEN_EGG);
/* 1750 */       return true;
/*      */     }
/* 1752 */     return false;
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\MoCTools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
