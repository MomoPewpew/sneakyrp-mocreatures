/*     */ package drzhark.mocreatures;
/*     */ 
/*     */ import drzhark.customspawner.utils.CMSUtils;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.util.MoCLog;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingSpawnEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.Event;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoCEventHooks
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void onWorldUnload(WorldEvent.Unload event) {
/*  31 */     if ((event.getWorld()).provider.getDimensionType().getId() == 0) {
/*  32 */       MoCreatures.proxy.worldInitDone = false;
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldLoad(WorldEvent.Load event) {
/*  38 */     if (DimensionManager.getWorld(0) != null && !MoCreatures.proxy.worldInitDone) {
/*     */       
/*  40 */       MoCPetMapData data = (MoCPetMapData)DimensionManager.getWorld(0).getMapStorage().getOrLoadData(MoCPetMapData.class, "mocreatures");
/*  41 */       if (data == null) {
/*  42 */         data = new MoCPetMapData("mocreatures");
/*     */       }
/*     */       
/*  45 */       DimensionManager.getWorld(0).getMapStorage().setData("mocreatures", data);
/*  46 */       DimensionManager.getWorld(0).getMapStorage().saveAllData();
/*  47 */       MoCreatures.instance.mapData = data;
/*  48 */       MoCreatures.proxy.worldInitDone = true;
/*     */     } 
/*     */     
/*  51 */     GameRules gameRule = event.getWorld().getGameRules();
/*  52 */     if (gameRule != null && !MoCreatures.isCustomSpawnerLoaded) {
/*  53 */       gameRule.setOrCreateGameRule("doMobSpawning", "true");
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onLivingDeathEvent(LivingDeathEvent event) {
/*  59 */     if (!(event.getEntity()).world.isRemote && 
/*  60 */       IMoCTameable.class.isAssignableFrom(event.getEntityLiving().getClass())) {
/*  61 */       IMoCTameable mocEntity = (IMoCTameable)event.getEntityLiving();
/*  62 */       if (mocEntity.getIsTamed() && mocEntity.getPetHealth() > 0.0F && !mocEntity.isRiderDisconnecting()) {
/*     */         return;
/*     */       }
/*     */       
/*  66 */       if (mocEntity.getOwnerPetId() != -1)
/*     */       {
/*  68 */         MoCreatures.instance.mapData.removeOwnerPet(mocEntity, mocEntity.getOwnerPetId());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onLivingDespawn(LivingSpawnEvent.AllowDespawn event) {
/*  77 */     if (MoCreatures.proxy.forceDespawns && !MoCreatures.isCustomSpawnerLoaded) {
/*     */ 
/*     */       
/*  80 */       if (IMob.class.isAssignableFrom(event.getEntityLiving().getClass()) || IRangedAttackMob.class.isAssignableFrom(event.getEntityLiving().getClass()) || event
/*  81 */         .getEntityLiving().isCreatureType(EnumCreatureType.MONSTER, false)) {
/*     */         return;
/*     */       }
/*     */       
/*  85 */       if (event.getEntityLiving() instanceof EntityTameable && (
/*  86 */         (EntityTameable)event.getEntityLiving()).isTamed()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  91 */       if (event.getEntityLiving() instanceof net.minecraft.entity.passive.EntitySheep || event.getEntityLiving() instanceof net.minecraft.entity.passive.EntityPig || event.getEntityLiving() instanceof net.minecraft.entity.passive.EntityCow || event
/*  92 */         .getEntityLiving() instanceof net.minecraft.entity.passive.EntityChicken)
/*     */       {
/*  94 */         if (isValidDespawnLightLevel(event.getEntity(), event.getWorld(), MoCreatures.proxy.minDespawnLightLevel, MoCreatures.proxy.maxDespawnLightLevel)) {
/*     */           return;
/*     */         }
/*     */       }
/*     */ 
/*     */       
/* 100 */       NBTTagCompound nbt = new NBTTagCompound();
/* 101 */       event.getEntityLiving().writeToNBT(nbt);
/* 102 */       if (nbt != null) {
/* 103 */         if (nbt.hasKey("Owner") && !nbt.getString("Owner").equals("")) {
/*     */           return;
/*     */         }
/* 106 */         if (nbt.hasKey("Tamed") && nbt.getBoolean("Tamed") == true) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */       
/* 111 */       if (event.getEntityLiving().getIdleTime() > 600) {
/* 112 */         event.setResult(Event.Result.ALLOW);
/*     */       }
/*     */       
/* 115 */       if (MoCreatures.proxy.debug) {
/* 116 */         int x = MathHelper.floor((event.getEntity()).posX);
/* 117 */         int y = MathHelper.floor((event.getEntity().getEntityBoundingBox()).minY);
/* 118 */         int z = MathHelper.floor((event.getEntity()).posZ);
/* 119 */         MoCLog.logger.info("Forced Despawn of entity " + event.getEntityLiving() + " at " + x + ", " + y + ", " + z + ". To prevent forced despawns, use /moc forceDespawns false.");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isValidDespawnLightLevel(Entity entity, World world, int minDespawnLightLevel, int maxDespawnLightLevel) {
/* 126 */     int x = MathHelper.floor(entity.posX);
/* 127 */     int y = MathHelper.floor((entity.getEntityBoundingBox()).minY);
/* 128 */     int z = MathHelper.floor(entity.posZ);
/* 129 */     int blockLightLevel = 0;
/* 130 */     if (y >= 0) {
/* 131 */       if (y >= 256) {
/* 132 */         y = 255;
/*     */       }
/* 134 */       blockLightLevel = CMSUtils.getLightFromNeighbors(world.getChunk(x >> 4, z >> 4), x & 0xF, y, z & 0xF);
/*     */     } 
/* 136 */     if (blockLightLevel < minDespawnLightLevel && maxDespawnLightLevel != -1)
/*     */     {
/* 138 */       return false; } 
/* 139 */     if (blockLightLevel > maxDespawnLightLevel && maxDespawnLightLevel != -1)
/*     */     {
/* 141 */       return false;
/*     */     }
/* 143 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\MoCEventHooks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */