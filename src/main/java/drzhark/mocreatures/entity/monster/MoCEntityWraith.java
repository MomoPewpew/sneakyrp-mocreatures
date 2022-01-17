/*     */ package drzhark.mocreatures.entity.monster;
/*     */ 
/*     */ import drzhark.mocreatures.entity.MoCEntityMob;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ 
/*     */ public class MoCEntityWraith extends MoCEntityMob {
/*     */   public MoCEntityWraith(World world) {
/*  28 */     super(world);
/*  29 */     this.collidedVertically = false;
/*  30 */     this.texture = "wraith.png";
/*  31 */     setSize(1.5F, 1.5F);
/*  32 */     this.isImmuneToFire = false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  37 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  38 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  39 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */   public int attackCounter;
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  44 */     super.applyEntityAttributes();
/*  45 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE)
/*  46 */       .setBaseValue((this.world.getDifficulty().getId() == 1) ? 2.0D : 3.0D);
/*  47 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
/*  48 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/*  53 */     return Items.GUNPOWDER;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/*  58 */     return MoCSoundEvents.ENTITY_WRAITH_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/*  63 */     return MoCSoundEvents.ENTITY_WRAITH_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/*  68 */     return MoCSoundEvents.ENTITY_WRAITH_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlyer() {
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBePushed() {
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void collideWithEntity(Entity par1Entity) {}
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {}
/*     */ 
/*     */   
/*     */   protected void updateFallState(double y, boolean onGroundIn, Block blockIn, BlockPos pos) {}
/*     */ 
/*     */   
/*     */   public int maxFlyingHeight() {
/*  93 */     return 10;
/*     */   }
/*     */   
/*     */   public int minFlyingHeight() {
/*  97 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity entityIn) {
/* 102 */     startArmSwingAttack();
/* 103 */     return super.attackEntityAsMob(entityIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void startArmSwingAttack() {
/* 110 */     if (!this.world.isRemote) {
/* 111 */       this.attackCounter = 1;
/* 112 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
/* 113 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 119 */     if (this.attackCounter > 0) {
/* 120 */       this.attackCounter += 2;
/* 121 */       if (this.attackCounter > 10)
/* 122 */         this.attackCounter = 0; 
/*     */     } 
/* 124 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void performAnimation(int animationType) {
/* 129 */     if (animationType == 1)
/* 130 */       this.attackCounter = 1; 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityWraith.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */