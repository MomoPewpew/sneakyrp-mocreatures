/*     */ package drzhark.mocreatures.entity.passive;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
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
/*     */ public class MoCEntityBunny extends MoCEntityTameableAnimal {
/*     */   private int bunnyReproduceTickerA;
/*     */   private int bunnyReproduceTickerB;
/*     */   private int jumpTimer;
/*  41 */   private static final DataParameter<Boolean> HAS_EATEN = EntityDataManager.createKey(MoCEntityBunny.class, DataSerializers.BOOLEAN);
/*     */   
/*     */   public MoCEntityBunny(World world) {
/*  44 */     super(world);
/*  45 */     setAdult(true);
/*  46 */     setTamed(false);
/*  47 */     setEdad(50 + this.rand.nextInt(15));
/*  48 */     if (this.rand.nextInt(4) == 0) {
/*  49 */       setAdult(false);
/*     */     }
/*  51 */     setSize(0.5F, 0.5F);
/*  52 */     this.bunnyReproduceTickerA = this.rand.nextInt(64);
/*  53 */     this.bunnyReproduceTickerB = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  58 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  59 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 0.8D, 6.0F, 5.0F));
/*  60 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.0D));
/*  61 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 1.0D, 4.0D));
/*  62 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
/*  63 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 0.8D));
/*  64 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  69 */     super.applyEntityAttributes();
/*  70 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
/*  71 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  76 */     super.entityInit();
/*  77 */     this.dataManager.register(HAS_EATEN, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean getHasEaten() {
/*  81 */     return ((Boolean)this.dataManager.get(HAS_EATEN)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setHasEaten(boolean flag) {
/*  85 */     this.dataManager.set(HAS_EATEN, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  90 */     checkSpawningBiome();
/*     */     
/*  92 */     if (getType() == 0) {
/*  93 */       setType(this.rand.nextInt(5) + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkSpawningBiome() {
/* 100 */     int i = MathHelper.floor(this.posX);
/* 101 */     int j = MathHelper.floor((getEntityBoundingBox()).minY);
/* 102 */     int k = MathHelper.floor(this.posZ);
/* 103 */     BlockPos pos = new BlockPos(i, j, k);
/*     */     
/* 105 */     Biome currentbiome = MoCTools.Biomekind(this.world, pos);
/*     */     try {
/* 107 */       if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
/* 108 */         setType(3);
/* 109 */         return true;
/*     */       } 
/* 111 */     } catch (Exception exception) {}
/*     */     
/* 113 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/* 118 */     switch (getType()) {
/*     */       case 1:
/* 120 */         return MoCreatures.proxy.getTexture("bunny.png");
/*     */       case 2:
/* 122 */         return MoCreatures.proxy.getTexture("bunnyb.png");
/*     */       case 3:
/* 124 */         return MoCreatures.proxy.getTexture("bunnyc.png");
/*     */       case 4:
/* 126 */         return MoCreatures.proxy.getTexture("bunnyd.png");
/*     */       case 5:
/* 128 */         return MoCreatures.proxy.getTexture("bunnye.png");
/*     */     } 
/*     */     
/* 131 */     return MoCreatures.proxy.getTexture("bunny.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 141 */     return MoCSoundEvents.ENTITY_RABBIT_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 146 */     return MoCSoundEvents.ENTITY_RABBIT_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 151 */     return SoundEvents.ENTITY_RABBIT_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 156 */     Boolean tameResult = processTameInteract(player, hand);
/* 157 */     if (tameResult != null) {
/* 158 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/* 161 */     ItemStack stack = player.getHeldItem(hand);
/* 162 */     if (!stack.isEmpty() && stack.getItem() == Items.GOLDEN_CARROT && !getHasEaten()) {
/* 163 */       stack.shrink(1);
/* 164 */       if (stack.isEmpty()) {
/* 165 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 167 */       setHasEaten(true);
/* 168 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GENERIC_EATING);
/* 169 */       return true;
/*     */     } 
/* 171 */     if (getRidingEntity() == null) {
/* 172 */       if (startRiding((Entity)player)) {
/* 173 */         this.rotationYaw = player.rotationYaw;
/* 174 */         if (!getIsTamed() && !this.world.isRemote) {
/* 175 */           MoCTools.tameWithName(player, (IMoCTameable)this);
/*     */         }
/*     */       } 
/*     */       
/* 179 */       return true;
/*     */     } 
/*     */     
/* 182 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 187 */     super.onUpdate();
/*     */     
/* 189 */     if (getRidingEntity() != null) {
/* 190 */       this.rotationYaw = (getRidingEntity()).rotationYaw;
/*     */     }
/* 192 */     if (!this.world.isRemote) {
/*     */       
/* 194 */       if (--this.jumpTimer <= 0 && this.onGround && (this.motionX > 0.05D || this.motionZ > 0.05D || this.motionX < -0.05D || this.motionZ < -0.05D)) {
/*     */         
/* 196 */         this.motionY = 0.3D;
/* 197 */         this.jumpTimer = 15;
/*     */       } 
/*     */       
/* 200 */       if (!getIsTamed() || !getIsAdult() || !getHasEaten() || getRidingEntity() != null) {
/*     */         return;
/*     */       }
/* 203 */       if (this.bunnyReproduceTickerA < 1023) {
/* 204 */         this.bunnyReproduceTickerA++;
/* 205 */       } else if (this.bunnyReproduceTickerB < 127) {
/* 206 */         this.bunnyReproduceTickerB++;
/*     */       } else {
/* 208 */         List<Entity> list1 = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 4.0D, 4.0D));
/* 209 */         for (int i1 = 0; i1 < list1.size(); i1++) {
/* 210 */           Entity entity1 = list1.get(i1);
/* 211 */           if (entity1 instanceof MoCEntityBunny && entity1 != this) {
/*     */ 
/*     */             
/* 214 */             MoCEntityBunny entitybunny = (MoCEntityBunny)entity1;
/* 215 */             if (entitybunny.getRidingEntity() == null && entitybunny.bunnyReproduceTickerA >= 1023 && entitybunny.getIsAdult() && entitybunny
/* 216 */               .getHasEaten()) {
/*     */ 
/*     */               
/* 219 */               MoCEntityBunny entitybunny1 = new MoCEntityBunny(this.world);
/* 220 */               entitybunny1.setPosition(this.posX, this.posY, this.posZ);
/* 221 */               entitybunny1.setAdult(false);
/* 222 */               int babytype = getType();
/* 223 */               if (this.rand.nextInt(2) == 0) {
/* 224 */                 babytype = entitybunny.getType();
/*     */               }
/* 226 */               entitybunny1.setType(babytype);
/* 227 */               this.world.spawnEntity((Entity)entitybunny1);
/* 228 */               MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 229 */               proceed();
/* 230 */               entitybunny.proceed();
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   } public void proceed() {
/* 238 */     setHasEaten(false);
/* 239 */     this.bunnyReproduceTickerB = 0;
/* 240 */     this.bunnyReproduceTickerA = this.rand.nextInt(64);
/*     */   }
/*     */ 
/*     */   
/*     */   public int nameYOffset() {
/* 245 */     return -40;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMyHealFood(ItemStack stack) {
/* 250 */     return (!stack.isEmpty() && stack.getItem() == Items.CARROT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 258 */     if (getRidingEntity() != null) {
/* 259 */       return false;
/*     */     }
/* 261 */     return super.attackEntityFrom(damagesource, i);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/* 266 */     return getIsTamed();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getYOffset() {
/* 271 */     if (getRidingEntity() instanceof EntityPlayer) {
/* 272 */       return ((EntityPlayer)getRidingEntity()).isSneaking() ? 0.25D : 0.5D;
/*     */     }
/*     */     
/* 275 */     return super.getYOffset();
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAdjustedYOffset() {
/* 280 */     return 0.2F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRidePlayer() {
/* 286 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityBunny.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */