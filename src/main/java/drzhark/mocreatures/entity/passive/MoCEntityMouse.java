/*     */ package drzhark.mocreatures.entity.passive;
/*     */ import drzhark.mocreatures.MoCEntityData;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIPanic;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */
/*     */ public class MoCEntityMouse extends MoCEntityAnimal {
/*     */   public MoCEntityMouse(World world) {
/*  32 */     super(world);
/*  33 */     setSize(0.3F, 0.3F);
/*     */   }
/*     */
/*     */
/*     */   protected void initEntityAI() {
/*  38 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  39 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 1.2D, 4.0D));
/*  40 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.4D));
/*  41 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*  42 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
/*     */   }
/*     */
/*     */
/*     */   protected void applyEntityAttributes() {
/*  47 */     super.applyEntityAttributes();
/*  48 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
/*  49 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
/*     */   }
/*     */
/*     */
/*     */   public void selectType() {
/*  54 */     checkSpawningBiome();
/*     */
/*  56 */     if (getType() == 0) {
/*  57 */       setType(this.rand.nextInt(3) + 1);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public ResourceLocation getTexture() {
/*  63 */     switch (getType()) {
/*     */       case 1:
/*  65 */         return MoCreatures.proxy.getTexture("miceg.png");
/*     */       case 2:
/*  67 */         return MoCreatures.proxy.getTexture("miceb.png");
/*     */       case 3:
/*  69 */         return MoCreatures.proxy.getTexture("micew.png");
/*     */     }
/*     */
/*  72 */     return MoCreatures.proxy.getTexture("miceg.png");
/*     */   }
/*     */
/*     */
/*     */
/*     */   public boolean checkSpawningBiome() {
/*  78 */     BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), this.posZ);
/*  79 */     Biome currentbiome = MoCTools.Biomekind(this.world, pos);
/*     */
/*     */     try {
/*  82 */       if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
/*  83 */         setType(3);
/*     */       }
/*  85 */     } catch (Exception exception) {}
/*     */
/*  87 */     return true;
/*     */   }
/*     */
/*     */   public boolean getIsPicked() {
/*  91 */     return (getRidingEntity() != null);
/*     */   }
/*     */
/*     */   public boolean climbing() {
/*  95 */     return (!this.onGround && isOnLadder());
/*     */   }
/*     */
/*     */
/*     */   public boolean getCanSpawnHere() {
/* 100 */     int i = MathHelper.floor(this.posX);
/* 101 */     int j = MathHelper.floor((getEntityBoundingBox()).minY);
/* 102 */     int k = MathHelper.floor(this.posZ);
/* 103 */     BlockPos pos = new BlockPos(i, j, k);
/* 104 */     Block block = this.world.getBlockState(pos.down()).getBlock();
/* 105 */     return (((MoCEntityData)MoCreatures.entityMap.get(getClass())).getFrequency() > 0 && this.world.checkNoEntityCollision(getEntityBoundingBox()) && this.world
/* 106 */       .getCollisionBoxes((Entity)this, getEntityBoundingBox()).size() == 0 &&
/* 107 */       !this.world.containsAnyLiquid(getEntityBoundingBox()) && (block == Blocks.COBBLESTONE || block == Blocks.PLANKS || block == Blocks.DIRT || block == Blocks.STONE || block == Blocks.GRASS));
/*     */   }
/*     */
/*     */
/*     */
/*     */   protected Item getDropItem() {
/* 113 */     return Items.WHEAT_SEEDS;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getDeathSound() {
/* 118 */     return MoCSoundEvents.ENTITY_MOUSE_DEATH;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 123 */     return MoCSoundEvents.ENTITY_MOUSE_HURT;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getAmbientSound() {
/* 128 */     return MoCSoundEvents.ENTITY_MOUSE_AMBIENT;
/*     */   }
/*     */
/*     */
/*     */
/*     */   public void fall(float f, float f1) {}
/*     */
/*     */
/*     */   public double getYOffset() {
/* 137 */     if (getRidingEntity() instanceof EntityPlayer && getRidingEntity() == MoCreatures.proxy.getPlayer() && this.world.isRemote) {
/* 138 */       return super.getYOffset() - 0.699999988079071D;
/*     */     }
/*     */
/* 141 */     if (getRidingEntity() instanceof EntityPlayer && this.world.isRemote) {
/* 142 */       return super.getYOffset() - 0.10000000149011612D;
/*     */     }
/* 144 */     return super.getYOffset();
/*     */   }
/*     */
/*     */
/*     */
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 150 */     if (getRidingEntity() == null) {
/* 151 */       if (startRiding((Entity)player)) {
/* 152 */         this.rotationYaw = player.rotationYaw;
/*     */       }
/*     */
/* 155 */       return true;
/*     */     }
/*     */
/* 158 */     return super.processInteract(player, hand);
/*     */   }
/*     */
/*     */
/*     */   public boolean isOnLadder() {
/* 163 */     return this.collidedHorizontally;
/*     */   }
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/* 168 */     super.onLivingUpdate();
/* 169 */     if (!this.onGround && getRidingEntity() != null) {
/* 170 */       this.rotationYaw = (getRidingEntity()).rotationYaw;
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public boolean upsideDown() {
/* 176 */     return getIsPicked();
/*     */   }
/*     */
/*     */
/*     */
/*     */   public boolean canRidePlayer() {
/* 182 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityMouse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
