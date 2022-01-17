/*     */ package drzhark.mocreatures.entity.passive;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
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
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityRaccoon extends MoCEntityTameableAnimal {
/*     */   public MoCEntityRaccoon(World world) {
/*  31 */     super(world);
/*  32 */     setSize(0.5F, 0.6F);
/*  33 */     this.texture = "raccoon.png";
/*  34 */     setEdad(50 + this.rand.nextInt(15));
/*     */     
/*  36 */     if (this.rand.nextInt(3) == 0) {
/*  37 */       setAdult(false);
/*     */     } else {
/*     */       
/*  40 */       setAdult(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  46 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  47 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.0D));
/*  48 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromPlayer((EntityCreature)this, 1.0D, 4.0D));
/*  49 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 0.8D, 2.0F, 10.0F));
/*  50 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, 1.0D));
/*  51 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  52 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*  53 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  54 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHunt((EntityCreature)this, EntityAnimal.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  59 */     super.applyEntityAttributes();
/*  60 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
/*  61 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  62 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
/*  63 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/*  68 */     if (super.attackEntityFrom(damagesource, i)) {
/*  69 */       Entity entity = damagesource.getTrueSource();
/*  70 */       if (isRidingOrBeingRiddenBy(entity)) {
/*  71 */         return true;
/*     */       }
/*  73 */       if (entity != this && isNotScared() && entity instanceof EntityLivingBase && shouldAttackPlayers()) {
/*  74 */         setAttackTarget((EntityLivingBase)entity);
/*  75 */         setRevengeTarget((EntityLivingBase)entity);
/*  76 */         return true;
/*     */       } 
/*     */     } 
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/*  84 */     Boolean tameResult = processTameInteract(player, hand);
/*  85 */     if (tameResult != null) {
/*  86 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/*  89 */     ItemStack stack = player.getHeldItem(hand);
/*  90 */     if (!stack.isEmpty() && MoCTools.isItemEdible(stack.getItem())) {
/*     */       
/*  92 */       stack.shrink(1);
/*  93 */       if (stack.isEmpty()) {
/*  94 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/*     */       
/*  97 */       if (!this.world.isRemote) {
/*  98 */         MoCTools.tameWithName(player, (IMoCTameable)this);
/*     */       }
/* 100 */       setHealth(getMaxHealth());
/*     */       
/* 102 */       if (!this.world.isRemote && !getIsAdult() && getEdad() < 100) {
/* 103 */         setEdad(getEdad() + 1);
/*     */       }
/*     */       
/* 106 */       return true;
/*     */     } 
/*     */     
/* 109 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 114 */     return (Item)MoCItems.fur;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 119 */     return MoCSoundEvents.ENTITY_RACCOON_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 124 */     return MoCSoundEvents.ENTITY_RACCOON_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 129 */     return MoCSoundEvents.ENTITY_RACCOON_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public int nameYOffset() {
/* 134 */     return -30;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSizeFactor() {
/* 139 */     if (getIsAdult()) {
/* 140 */       return 0.85F;
/*     */     }
/* 142 */     return 0.85F * getEdad() * 0.01F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTalkInterval() {
/* 147 */     return 400;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnedInChunk() {
/* 152 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/* 157 */     return getIsAdult();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 162 */     return (!(entity instanceof MoCEntityRaccoon) && super.canAttackTarget(entity));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadyToHunt() {
/* 167 */     return (getIsAdult() && !isMovementCeased());
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityRaccoon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */