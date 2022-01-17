/*     */ package drzhark.mocreatures.entity.monster;
/*     */ import drzhark.mocreatures.entity.MoCEntityMob;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */
/*     */ public class MoCEntitySilverSkeleton extends MoCEntityMob {
/*     */   public int attackCounterLeft;
/*     */
/*     */   public MoCEntitySilverSkeleton(World world) {
/*  31 */     super(world);
/*  32 */     this.texture = "silverskeleton.png";
/*  33 */     setSize(0.9F, 1.4F);
/*     */   }
/*     */   public int attackCounterRight;
/*     */
/*     */   protected void initEntityAI() {
/*  38 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  39 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  40 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  41 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */
/*     */
/*     */   protected void applyEntityAttributes() {
/*  46 */     super.applyEntityAttributes();
/*  47 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
/*  48 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*  49 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
/*     */   }
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/*  54 */     if (!this.world.isRemote) {
/*  55 */       if (getAttackTarget() == null) {
/*  56 */         setSprinting(false);
/*     */       } else {
/*  58 */         setSprinting(true);
/*     */       }
/*     */     }
/*     */
/*  62 */     if (this.attackCounterLeft > 0 && ++this.attackCounterLeft > 10) {
/*  63 */       this.attackCounterLeft = 0;
/*     */     }
/*     */
/*  66 */     if (this.attackCounterRight > 0 && ++this.attackCounterRight > 10) {
/*  67 */       this.attackCounterRight = 0;
/*     */     }
/*     */
/*  70 */     super.onLivingUpdate();
/*     */   }
/*     */
/*     */
/*     */   protected Item getDropItem() {
/*  75 */     if (this.rand.nextInt(10) == 0) {
/*  76 */       return (Item)MoCItems.silversword;
/*     */     }
/*  78 */     return Items.BONE;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   public void performAnimation(int animationType) {
/*  85 */     if (animationType == 1)
/*     */     {
/*  87 */       this.attackCounterLeft = 1;
/*     */     }
/*  89 */     if (animationType == 2)
/*     */     {
/*  91 */       this.attackCounterRight = 1;
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   private void startAttackAnimation() {
/*  99 */     if (!this.world.isRemote) {
/* 100 */       boolean leftArmW = (this.rand.nextInt(2) == 0);
/*     */
/* 102 */       if (leftArmW) {
/* 103 */         this.attackCounterLeft = 1;
/* 104 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
/* 105 */               .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */       } else {
/* 107 */         this.attackCounterRight = 1;
/* 108 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 2), new NetworkRegistry.TargetPoint(this.world.provider
/* 109 */               .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public boolean attackEntityAsMob(Entity entityIn) {
/* 116 */     startAttackAnimation();
/* 117 */     return super.attackEntityAsMob(entityIn);
/*     */   }
/*     */
/*     */
/*     */   public float getAIMoveSpeed() {
/* 122 */     if (isSprinting()) {
/* 123 */       return 0.35F;
/*     */     }
/* 125 */     return 0.2F;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getDeathSound() {
/* 130 */     return SoundEvents.ENTITY_SKELETON_DEATH;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 135 */     return SoundEvents.ENTITY_SKELETON_HURT;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getAmbientSound() {
/* 140 */     return SoundEvents.ENTITY_SKELETON_AMBIENT;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 148 */     return EnumCreatureAttribute.UNDEAD;
/*     */   }
/*     */
/*     */
/*     */   protected void playStepSound(BlockPos pos, Block block) {
/* 153 */     playSound(SoundEvents.ENTITY_SKELETON_STEP, 0.15F, 1.0F);
/*     */   }
/*     */
/*     */
/*     */   protected boolean isHarmedByDaylight() {
/* 158 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntitySilverSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
