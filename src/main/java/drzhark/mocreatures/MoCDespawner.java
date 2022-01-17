/*     */ package drzhark.mocreatures;
/*     */ 
/*     */ import drzhark.customspawner.utils.CMSUtils;
/*     */ import drzhark.mocreatures.util.MoCLog;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.entity.passive.EntityBat;
/*     */ import net.minecraft.entity.passive.EntityChicken;
/*     */ import net.minecraft.entity.passive.EntityCow;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.entity.passive.EntityOcelot;
/*     */ import net.minecraft.entity.passive.EntityPig;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.entity.passive.EntitySquid;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoCDespawner
/*     */ {
/*     */   public static boolean debug = false;
/*  33 */   public static int despawnLightLevel = 7;
/*  34 */   public static int despawnTickRate = 111;
/*  35 */   public List<Biome> biomeList = new ArrayList<>();
/*     */   private List<Class<? extends EntityLiving>> vanillaClassList;
/*     */   
/*     */   public MoCDespawner() {
/*  39 */     this.biomeList = new ArrayList<>();
/*     */     try {
/*  41 */       Iterator<Biome> iterator = Biome.REGISTRY.iterator();
/*  42 */       while (iterator.hasNext()) {
/*  43 */         Biome biome = iterator.next();
/*  44 */         if (biome == null) {
/*     */           continue;
/*     */         }
/*     */         
/*  48 */         this.biomeList.add(biome);
/*     */       } 
/*     */       
/*  51 */       this.vanillaClassList = new ArrayList<>();
/*  52 */       this.vanillaClassList.add(EntityChicken.class);
/*  53 */       this.vanillaClassList.add(EntityCow.class);
/*  54 */       this.vanillaClassList.add(EntityPig.class);
/*  55 */       this.vanillaClassList.add(EntitySheep.class);
/*  56 */       this.vanillaClassList.add(EntityWolf.class);
/*  57 */       this.vanillaClassList.add(EntitySquid.class);
/*  58 */       this.vanillaClassList.add(EntityOcelot.class);
/*  59 */       this.vanillaClassList.add(EntityBat.class);
/*  60 */       this.vanillaClassList.add(EntityHorse.class);
/*  61 */     } catch (Exception ex) {
/*  62 */       throw new RuntimeException(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final int entityDespawnCheck(WorldServer world, EntityLiving entityliving, int minDespawnLightLevel, int maxDespawnLightLevel) {
/*  69 */     if (entityliving instanceof EntityWolf && ((EntityWolf)entityliving).isTamed()) {
/*  70 */       return 0;
/*     */     }
/*  72 */     if (entityliving instanceof EntityOcelot && ((EntityOcelot)entityliving).isTamed()) {
/*  73 */       return 0;
/*     */     }
/*  75 */     if (!isValidDespawnLightLevel((Entity)entityliving, (World)world, minDespawnLightLevel, maxDespawnLightLevel)) {
/*  76 */       return 0;
/*     */     }
/*     */     
/*  79 */     EntityPlayer entityplayer = world.getClosestPlayerToEntity((Entity)entityliving, -1.0D);
/*  80 */     if (entityplayer != null) {
/*     */       
/*  82 */       double d = ((Entity)entityplayer).posX - entityliving.posX;
/*  83 */       double d1 = ((Entity)entityplayer).posY - entityliving.posY;
/*  84 */       double d2 = ((Entity)entityplayer).posZ - entityliving.posZ;
/*  85 */       double d3 = d * d + d1 * d1 + d2 * d2;
/*  86 */       if (d3 > 16384.0D) {
/*  87 */         entityliving.setDead();
/*  88 */         return 1;
/*     */       } 
/*  90 */       if (entityliving.getIdleTime() > 600 && world.rand.nextInt(800) == 0 && 
/*  91 */         d3 >= 1024.0D) {
/*     */ 
/*     */         
/*  94 */         entityliving.setDead();
/*  95 */         return 1;
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     return 0;
/*     */   }
/*     */   
/*     */   public static final int despawnVanillaAnimals(WorldServer world, int minDespawnLightLevel, int maxDespawnLightLevel) {
/* 103 */     int count = 0;
/* 104 */     for (int j = 0; j < world.loadedEntityList.size(); j++) {
/* 105 */       Entity entity = world.loadedEntityList.get(j);
/* 106 */       if (entity instanceof EntityLiving)
/*     */       {
/*     */         
/* 109 */         if (entity instanceof EntityHorse || entity instanceof EntityCow || entity instanceof EntitySheep || entity instanceof EntityPig || entity instanceof EntityOcelot || entity instanceof EntityChicken || entity instanceof EntitySquid || entity instanceof EntityWolf || entity instanceof net.minecraft.entity.passive.EntityMooshroom)
/*     */         {
/*     */           
/* 112 */           count += entityDespawnCheck(world, (EntityLiving)entity, minDespawnLightLevel, maxDespawnLightLevel); } 
/*     */       }
/*     */     } 
/* 115 */     return count;
/*     */   }
/*     */   
/*     */   public final int countEntities(Class<? extends EntityLiving> class1, World world) {
/* 119 */     int i = 0;
/* 120 */     for (int j = 0; j < world.loadedEntityList.size(); j++) {
/* 121 */       Entity entity = world.loadedEntityList.get(j);
/* 122 */       if (class1.isAssignableFrom(entity.getClass())) {
/* 123 */         i++;
/*     */       }
/*     */     } 
/*     */     
/* 127 */     return i;
/*     */   }
/*     */   
/*     */   public static boolean isValidLightLevel(Entity entity, WorldServer world, int lightLevel, boolean checkAmbientLightLevel) {
/* 131 */     if (entity.isCreatureType(EnumCreatureType.MONSTER, false))
/* 132 */       return true; 
/* 133 */     if (entity.isCreatureType(EnumCreatureType.AMBIENT, false) && !checkAmbientLightLevel)
/* 134 */       return true; 
/* 135 */     if (!entity.isCreatureType(EnumCreatureType.CREATURE, false)) {
/* 136 */       return true;
/*     */     }
/* 138 */     int x = MathHelper.floor(entity.posX);
/* 139 */     int y = MathHelper.floor((entity.getEntityBoundingBox()).minY);
/* 140 */     int z = MathHelper.floor(entity.posZ);
/* 141 */     int i = 0;
/* 142 */     if (y >= 0) {
/* 143 */       if (y >= 256) {
/* 144 */         y = 255;
/*     */       }
/* 146 */       i = getLightFromNeighbors(world.getChunk(x >> 4, z >> 4), x & 0xF, y, z & 0xF);
/*     */     } 
/* 148 */     if (i > lightLevel && 
/* 149 */       debug) {
/* 150 */       MoCLog.logger.info("Denied spawn! for " + entity.getName() + ". LightLevel over threshold of " + lightLevel + " in dimension " + world.provider
/* 151 */           .getDimensionType().getId() + " at coords " + x + ", " + y + ", " + z);
/*     */     }
/*     */     
/* 154 */     return (i <= lightLevel);
/*     */   }
/*     */   
/*     */   public static boolean isValidDespawnLightLevel(Entity entity, World world, int minDespawnLightLevel, int maxDespawnLightLevel) {
/* 158 */     int x = MathHelper.floor(entity.posX);
/* 159 */     int y = MathHelper.floor((entity.getEntityBoundingBox()).minY);
/* 160 */     int z = MathHelper.floor(entity.posZ);
/* 161 */     int blockLightLevel = 0;
/* 162 */     if (y >= 0) {
/* 163 */       if (y >= 256) {
/* 164 */         y = 255;
/*     */       }
/* 166 */       blockLightLevel = CMSUtils.getLightFromNeighbors(world.getChunk(x >> 4, z >> 4), x & 0xF, y, z & 0xF);
/*     */     } 
/* 168 */     if (blockLightLevel < minDespawnLightLevel && maxDespawnLightLevel != -1)
/*     */     {
/* 170 */       return false; } 
/* 171 */     if (blockLightLevel > maxDespawnLightLevel && maxDespawnLightLevel != -1)
/*     */     {
/* 173 */       return false;
/*     */     }
/* 175 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getLightFromNeighbors(Chunk chunk, int x, int y, int z) {
/* 182 */     ExtendedBlockStorage extendedblockstorage = chunk.getBlockStorageArray()[y >> 4];
/*     */     
/* 184 */     if (extendedblockstorage == null) {
/* 185 */       return 0;
/*     */     }
/* 187 */     return extendedblockstorage.getBlockLight(x, y & 0xF, z);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\MoCDespawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */