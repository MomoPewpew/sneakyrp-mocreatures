/*     */ package drzhark.mocreatures.entity.monster;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityMob;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoCEntityWWolf
/*     */   extends MoCEntityMob
/*     */ {
/*     */   public int mouthCounter;
/*     */   public int tailCounter;
/*     */   
/*     */   public MoCEntityWWolf(World world) {
/*  43 */     super(world);
/*  44 */     setSize(0.9F, 1.3F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  49 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  50 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  51 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  52 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  57 */     super.applyEntityAttributes();
/*  58 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
/*  59 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
/*  60 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  65 */     if (getType() == 0) {
/*  66 */       setType(this.rand.nextInt(4) + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  72 */     switch (getType()) {
/*     */       case 1:
/*  74 */         return MoCreatures.proxy.getTexture("wolfblack.png");
/*     */       case 2:
/*  76 */         return MoCreatures.proxy.getTexture("wolfwild.png");
/*     */       case 3:
/*  78 */         return MoCreatures.proxy.getTexture("wolftimber.png");
/*     */       case 4:
/*  80 */         return MoCreatures.proxy.getTexture("wolfdark.png");
/*     */       case 5:
/*  82 */         return MoCreatures.proxy.getTexture("wolfbright.png");
/*     */     } 
/*     */     
/*  85 */     return MoCreatures.proxy.getTexture("wolfwild.png");
/*     */   }
/*     */ 
/*     */   
/*     */   private void openMouth() {
/*  90 */     this.mouthCounter = 1;
/*     */   }
/*     */   
/*     */   private void moveTail() {
/*  94 */     this.tailCounter = 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  99 */     super.onUpdate();
/*     */     
/* 101 */     if (this.rand.nextInt(200) == 0) {
/* 102 */       moveTail();
/*     */     }
/*     */     
/* 105 */     if (this.mouthCounter > 0 && ++this.mouthCounter > 15) {
/* 106 */       this.mouthCounter = 0;
/*     */     }
/*     */     
/* 109 */     if (this.tailCounter > 0 && ++this.tailCounter > 8) {
/* 110 */       this.tailCounter = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkSpawningBiome() {
/* 116 */     int i = MathHelper.floor(this.posX);
/* 117 */     int j = MathHelper.floor((getEntityBoundingBox()).minY);
/* 118 */     int k = MathHelper.floor(this.posZ);
/*     */     
/* 120 */     Biome biome = MoCTools.Biomekind(this.world, new BlockPos(i, j, k));
/* 121 */     if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY)) {
/* 122 */       setType(3);
/*     */     }
/* 124 */     selectType();
/* 125 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 130 */     return (checkSpawningBiome() && this.world
/* 131 */       .canBlockSeeSky(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY), 
/* 132 */           MathHelper.floor(this.posZ))) && super.getCanSpawnHere());
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingBase getClosestTarget(Entity entity, double d) {
/* 137 */     double d1 = -1.0D;
/* 138 */     EntityLivingBase entityliving = null;
/* 139 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(d, d, d));
/* 140 */     for (int i = 0; i < list.size(); i++) {
/* 141 */       Entity entity1 = list.get(i);
/* 142 */       if (entity1 instanceof EntityLivingBase && entity1 != entity && entity1 != entity.getRidingEntity() && entity1 != entity
/* 143 */         .getRidingEntity() && !(entity1 instanceof EntityPlayer) && !(entity1 instanceof EntityMob) && !(entity1 instanceof drzhark.mocreatures.entity.passive.MoCEntityBigCat) && !(entity1 instanceof drzhark.mocreatures.entity.passive.MoCEntityBear) && !(entity1 instanceof net.minecraft.entity.passive.EntityCow) && (!(entity1 instanceof net.minecraft.entity.passive.EntityWolf) || MoCreatures.proxy.attackWolves) && (!(entity1 instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse) || MoCreatures.proxy.attackHorses)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 149 */         double d2 = entity1.getDistanceSq(entity.posX, entity.posY, entity.posZ);
/* 150 */         if ((d < 0.0D || d2 < d * d) && (d1 == -1.0D || d2 < d1) && ((EntityLivingBase)entity1).canEntityBeSeen(entity)) {
/* 151 */           d1 = d2;
/* 152 */           entityliving = (EntityLivingBase)entity1;
/*     */         } 
/*     */       } 
/*     */     } 
/* 156 */     return entityliving;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 161 */     return (Item)MoCItems.fur;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 166 */     return MoCSoundEvents.ENTITY_WOLF_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 171 */     openMouth();
/* 172 */     return MoCSoundEvents.ENTITY_WOLF_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 177 */     openMouth();
/* 178 */     return MoCSoundEvents.ENTITY_WOLF_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePassenger(Entity passenger) {
/* 183 */     double dist = 0.1D;
/* 184 */     double newPosX = this.posX + dist * Math.sin((this.renderYawOffset / 57.29578F));
/* 185 */     double newPosZ = this.posZ - dist * Math.cos((this.renderYawOffset / 57.29578F));
/* 186 */     passenger.setPosition(newPosX, this.posY + getMountedYOffset() + passenger.getYOffset(), newPosZ);
/* 187 */     passenger.rotationYaw = this.rotationYaw;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMountedYOffset() {
/* 192 */     return this.height * 0.75D - 0.1D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 197 */     super.onLivingUpdate();
/* 198 */     if (!this.world.isRemote && !isBeingRidden() && this.rand.nextInt(100) == 0) {
/* 199 */       List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
/* 200 */       for (int i = 0; i < list.size(); i++) {
/* 201 */         Entity entity = list.get(i);
/* 202 */         if (entity instanceof EntityMob) {
/*     */ 
/*     */           
/* 205 */           EntityMob entitymob = (EntityMob)entity;
/* 206 */           if (entitymob.getRidingEntity() == null && (entitymob instanceof net.minecraft.entity.monster.EntitySkeleton || entitymob instanceof net.minecraft.entity.monster.EntityZombie || entitymob instanceof MoCEntitySilverSkeleton)) {
/*     */             
/* 208 */             entitymob.startRiding((Entity)this);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityWWolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */