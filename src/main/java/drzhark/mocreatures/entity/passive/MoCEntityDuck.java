/*     */ package drzhark.mocreatures.entity.passive;
/*     */ 
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIPanic;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityDuck extends MoCEntityAnimal {
/*  22 */   public float wingRotation = 0.0F; public boolean field_70885_d = false;
/*  23 */   public float destPos = 0.0F;
/*     */   public float oFlapSpeed;
/*     */   public float oFlap;
/*  26 */   public float wingRotDelta = 1.0F;
/*     */   
/*     */   public MoCEntityDuck(World world) {
/*  29 */     super(world);
/*  30 */     this.texture = "duck.png";
/*  31 */     setSize(0.4F, 0.7F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  36 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  37 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.4D));
/*  38 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*  39 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  44 */     super.applyEntityAttributes();
/*  45 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
/*  46 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/*  51 */     return MoCSoundEvents.ENTITY_DUCK_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/*  56 */     return MoCSoundEvents.ENTITY_DUCK_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/*  61 */     return MoCSoundEvents.ENTITY_DUCK_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/*  66 */     if (MoCreatures.proxy.forceDespawns) {
/*  67 */       return !getIsTamed();
/*     */     }
/*  69 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  75 */     super.onLivingUpdate();
/*  76 */     this.oFlap = this.wingRotation;
/*  77 */     this.oFlapSpeed = this.destPos;
/*  78 */     this.destPos = (float)(this.destPos + (this.onGround ? -1 : 4) * 0.3D);
/*     */     
/*  80 */     if (this.destPos < 0.0F) {
/*  81 */       this.destPos = 0.0F;
/*     */     }
/*     */     
/*  84 */     if (this.destPos > 1.0F) {
/*  85 */       this.destPos = 1.0F;
/*     */     }
/*     */     
/*  88 */     if (!this.onGround && this.wingRotDelta < 1.0F) {
/*  89 */       this.wingRotDelta = 1.0F;
/*     */     }
/*     */     
/*  92 */     this.wingRotDelta = (float)(this.wingRotDelta * 0.9D);
/*     */     
/*  94 */     if (!this.onGround && this.motionY < 0.0D) {
/*  95 */       this.motionY *= 0.6D;
/*     */     }
/*     */     
/*  98 */     this.wingRotation += this.wingRotDelta * 2.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {}
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 107 */     return Items.FEATHER;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityDuck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */