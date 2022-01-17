/*     */ package drzhark.mocreatures.entity.passive;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIHunt;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
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
/*     */ public class MoCEntityFox extends MoCEntityTameableAnimal {
/*     */   public MoCEntityFox(World world) {
/*  38 */     super(world);
/*  39 */     setSize(0.6F, 0.7F);
/*  40 */     setEdad(this.rand.nextInt(15) + 50);
/*  41 */     if (this.rand.nextInt(3) == 0) {
/*  42 */       setAdult(false);
/*     */     } else {
/*     */       
/*  45 */       setAdult(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  51 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  52 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.0D));
/*  53 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 1.0D, 4.0D));
/*  54 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 0.8D, 2.0F, 10.0F));
/*  55 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
/*  56 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  57 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*  58 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  59 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  64 */     super.applyEntityAttributes();
/*  65 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
/*  66 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  67 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
/*  68 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  73 */     checkSpawningBiome();
/*     */     
/*  75 */     if (getType() == 0) {
/*  76 */       setType(1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  83 */     if (!getIsAdult()) {
/*  84 */       if (getType() == 2) {
/*  85 */         return MoCreatures.proxy.getTexture("foxsnow.png");
/*     */       }
/*  87 */       return MoCreatures.proxy.getTexture("foxcub.png");
/*     */     } 
/*  89 */     switch (getType()) {
/*     */       case 1:
/*  91 */         return MoCreatures.proxy.getTexture("fox.png");
/*     */       case 2:
/*  93 */         return MoCreatures.proxy.getTexture("foxsnow.png");
/*     */     } 
/*     */     
/*  96 */     return MoCreatures.proxy.getTexture("fox.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 102 */     if (super.attackEntityFrom(damagesource, i)) {
/* 103 */       Entity entity = damagesource.getTrueSource();
/* 104 */       if (isRidingOrBeingRiddenBy(entity)) {
/* 105 */         return true;
/*     */       }
/* 107 */       if (entity != this && isNotScared() && entity instanceof EntityLivingBase && shouldAttackPlayers()) {
/* 108 */         setAttackTarget((EntityLivingBase)entity);
/* 109 */         setRevengeTarget((EntityLivingBase)entity);
/* 110 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 119 */     Boolean tameResult = processTameInteract(player, hand);
/* 120 */     if (tameResult != null) {
/* 121 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/* 124 */     ItemStack stack = player.getHeldItem(hand);
/* 125 */     if (!stack.isEmpty() && stack.getItem() == MoCItems.rawTurkey) {
/* 126 */       stack.shrink(1);
/* 127 */       if (stack.isEmpty()) {
/* 128 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/*     */       
/* 131 */       if (!this.world.isRemote) {
/* 132 */         MoCTools.tameWithName(player, (IMoCTameable)this);
/*     */       }
/* 134 */       setHealth(getMaxHealth());
/*     */       
/* 136 */       if (!this.world.isRemote && !getIsAdult() && getEdad() < 100) {
/* 137 */         setEdad(getEdad() + 1);
/*     */       }
/*     */       
/* 140 */       return true;
/*     */     } 
/*     */     
/* 143 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/* 148 */     return getIsAdult();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkSpawningBiome() {
/* 155 */     BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor((getEntityBoundingBox()).minY), MathHelper.floor(this.posZ));
/* 156 */     Biome currentbiome = MoCTools.Biomekind(this.world, pos);
/*     */     try {
/* 158 */       if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
/* 159 */         setType(2);
/*     */       }
/* 161 */     } catch (Exception exception) {}
/*     */     
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 168 */     return (Item)MoCItems.fur;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 173 */     return MoCSoundEvents.ENTITY_FOX_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 178 */     return MoCSoundEvents.ENTITY_FOX_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 183 */     return MoCSoundEvents.ENTITY_FOX_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/* 188 */     return 0.3F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMyHealFood(ItemStack stack) {
/* 193 */     return (!stack.isEmpty() && stack.getItem() == MoCItems.ratRaw);
/*     */   }
/*     */ 
/*     */   
/*     */   public int nameYOffset() {
/* 198 */     return -50;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 203 */     return (!(entity instanceof MoCEntityFox) && entity.height <= 0.7D && entity.width <= 0.7D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadyToHunt() {
/* 208 */     return (getIsAdult() && !isMovementCeased());
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSizeFactor() {
/* 213 */     if (getIsAdult()) {
/* 214 */       return 0.9F;
/*     */     }
/* 216 */     return 0.9F * getEdad() * 0.01F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityFox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */