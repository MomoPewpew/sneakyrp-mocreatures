/*     */ package drzhark.mocreatures;
/*     */ 
/*     */ import drzhark.guiapi.widget.WidgetSimplewindow;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoCEntityData
/*     */ {
/*     */   private EnumCreatureType typeOfCreature;
/*     */   private Biome.SpawnListEntry spawnListEntry;
/*     */   private String entityName;
/*     */   private boolean canSpawn = true;
/*     */   private int entityId;
/*     */   private List<BiomeDictionary.Type> biomeTypes;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private WidgetSimplewindow entityWindow;
/*  24 */   private int frequency = 8;
/*  25 */   private int minGroup = 1;
/*  26 */   private int maxGroup = 1;
/*  27 */   private int maxSpawnInChunk = 1;
/*     */   
/*     */   public MoCEntityData(String name, int maxchunk, EnumCreatureType type, Biome.SpawnListEntry spawnListEntry, List<BiomeDictionary.Type> biomeTypes) {
/*  30 */     this.entityName = name;
/*  31 */     this.typeOfCreature = type;
/*  32 */     this.biomeTypes = biomeTypes;
/*  33 */     this.frequency = spawnListEntry.itemWeight;
/*  34 */     this.minGroup = spawnListEntry.minGroupCount;
/*  35 */     this.maxGroup = spawnListEntry.maxGroupCount;
/*  36 */     this.maxSpawnInChunk = maxchunk;
/*  37 */     this.spawnListEntry = spawnListEntry;
/*  38 */     MoCreatures.entityMap.put(spawnListEntry.entityClass, this);
/*     */   }
/*     */   
/*     */   public MoCEntityData(String name, int id, int maxchunk, EnumCreatureType type, Biome.SpawnListEntry spawnListEntry, List<BiomeDictionary.Type> biomeTypes) {
/*  42 */     this.entityId = id;
/*  43 */     this.entityName = name;
/*  44 */     this.typeOfCreature = type;
/*  45 */     this.biomeTypes = biomeTypes;
/*  46 */     this.frequency = spawnListEntry.itemWeight;
/*  47 */     this.minGroup = spawnListEntry.minGroupCount;
/*  48 */     this.maxGroup = spawnListEntry.maxGroupCount;
/*  49 */     this.maxSpawnInChunk = maxchunk;
/*  50 */     this.spawnListEntry = spawnListEntry;
/*  51 */     MoCreatures.entityMap.put(spawnListEntry.entityClass, this);
/*     */   }
/*     */   
/*     */   public Class<? extends EntityLiving> getEntityClass() {
/*  55 */     return this.spawnListEntry.entityClass;
/*     */   }
/*     */   
/*     */   public EnumCreatureType getType() {
/*  59 */     if (this.typeOfCreature != null) {
/*  60 */       return this.typeOfCreature;
/*     */     }
/*  62 */     return null;
/*     */   }
/*     */   
/*     */   public void setType(EnumCreatureType type) {
/*  66 */     this.typeOfCreature = type;
/*     */   }
/*     */   
/*     */   public List<BiomeDictionary.Type> getBiomeTypes() {
/*  70 */     return this.biomeTypes;
/*     */   }
/*     */   
/*     */   public int getEntityID() {
/*  74 */     return this.entityId;
/*     */   }
/*     */   
/*     */   public void setEntityID(int id) {
/*  78 */     this.entityId = id;
/*     */   }
/*     */   
/*     */   public int getFrequency() {
/*  82 */     return this.frequency;
/*     */   }
/*     */   
/*     */   public void setFrequency(int freq) {
/*  86 */     if (freq <= 0) {
/*  87 */       this.frequency = 0;
/*     */     } else {
/*  89 */       this.frequency = freq;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getMinSpawn() {
/*  94 */     return this.minGroup;
/*     */   }
/*     */   
/*     */   public void setMinSpawn(int min) {
/*  98 */     if (min <= 0) {
/*  99 */       this.minGroup = 0;
/*     */     } else {
/* 101 */       this.minGroup = min;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getMaxSpawn() {
/* 106 */     return this.maxGroup;
/*     */   }
/*     */   
/*     */   public void setMaxSpawn(int max) {
/* 110 */     if (max <= 0) {
/* 111 */       this.maxGroup = 0;
/*     */     } else {
/* 113 */       this.maxGroup = max;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getMaxInChunk() {
/* 118 */     return this.maxSpawnInChunk;
/*     */   }
/*     */   
/*     */   public void setMaxInChunk(int max) {
/* 122 */     if (max <= 0) {
/* 123 */       this.maxSpawnInChunk = 0;
/*     */     } else {
/* 125 */       this.maxSpawnInChunk = max;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getEntityName() {
/* 130 */     return this.entityName;
/*     */   }
/*     */   
/*     */   public void setEntityName(String name) {
/* 134 */     this.entityName = name;
/*     */   }
/*     */   
/*     */   public void setCanSpawn(boolean flag) {
/* 138 */     this.canSpawn = flag;
/*     */   }
/*     */   
/*     */   public boolean getCanSpawn() {
/* 142 */     return this.canSpawn;
/*     */   }
/*     */   
/*     */   public Biome.SpawnListEntry getSpawnListEntry() {
/* 146 */     return this.spawnListEntry;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public WidgetSimplewindow getEntityWindow() {
/* 151 */     return this.entityWindow;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void setEntityWindow(WidgetSimplewindow window) {
/* 156 */     this.entityWindow = window;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\MoCEntityData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */