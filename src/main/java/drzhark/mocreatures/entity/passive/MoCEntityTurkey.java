/*     */ package drzhark.mocreatures.entity.passive;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIPanic;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITempt;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */
/*     */ public class MoCEntityTurkey extends MoCEntityTameableAnimal {
/*     */   public MoCEntityTurkey(World world) {
/*  27 */     super(world);
/*  28 */     setSize(0.8F, 1.0F);
/*  29 */     this.texture = "turkey.png";
/*     */   }
/*     */
/*     */
/*     */   protected void initEntityAI() {
/*  34 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  35 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.4D));
/*  36 */     this.tasks.addTask(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.0D, Items.MELON_SEEDS, false));
/*  37 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*  38 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
/*     */   }
/*     */
/*     */
/*     */   protected void applyEntityAttributes() {
/*  43 */     super.applyEntityAttributes();
/*  44 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
/*  45 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*     */   }
/*     */
/*     */
/*     */   public void selectType() {
/*  50 */     if (getType() == 0) {
/*  51 */       setType(this.rand.nextInt(2) + 1);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public ResourceLocation getTexture() {
/*  57 */     if (getType() == 1) {
/*  58 */       return MoCreatures.proxy.getTexture("turkey.png");
/*     */     }
/*  60 */     return MoCreatures.proxy.getTexture("turkeyfemale.png");
/*     */   }
/*     */
/*     */
/*     */
/*     */   protected SoundEvent getDeathSound() {
/*  66 */     return MoCSoundEvents.ENTITY_TURKEY_HURT;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/*  71 */     return MoCSoundEvents.ENTITY_TURKEY_HURT;
/*     */   }
/*     */
/*     */
/*     */   protected SoundEvent getAmbientSound() {
/*  76 */     return MoCSoundEvents.ENTITY_TURKEY_AMBIENT;
/*     */   }
/*     */
/*     */
/*     */   protected Item getDropItem() {
/*  81 */     boolean flag = (this.rand.nextInt(2) == 0);
/*  82 */     if (flag) {
/*  83 */       return (Item)MoCItems.rawTurkey;
/*     */     }
/*  85 */     return Items.FEATHER;
/*     */   }
/*     */
/*     */
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/*  90 */     Boolean tameResult = processTameInteract(player, hand);
/*  91 */     if (tameResult != null) {
/*  92 */       return tameResult.booleanValue();
/*     */     }
/*     */
/*  95 */     ItemStack stack = player.getHeldItem(hand);
/*  96 */     if (!getIsTamed() && !stack.isEmpty() && stack.getItem() == Items.MELON_SEEDS) {
/*  97 */       if (!this.world.isRemote) {
/*  98 */         MoCTools.tameWithName(player, (IMoCTameable)this);
/*     */       }
/*     */
/* 101 */       return true;
/*     */     }
/*     */
/* 104 */     return super.processInteract(player, hand);
/*     */   }
/*     */
/*     */
/*     */   public void onLivingUpdate() {
/* 109 */     super.onLivingUpdate();
/* 110 */     if (!this.onGround && this.motionY < 0.0D) {
/* 111 */       this.motionY *= 0.8D;
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public boolean isMyHealFood(ItemStack stack) {
/* 117 */     return (!stack.isEmpty() && stack.getItem() == Items.PUMPKIN_SEEDS);
/*     */   }
/*     */
/*     */
/*     */   public int nameYOffset() {
/* 122 */     return -50;
/*     */   }
/*     */
/*     */
/*     */   public int getTalkInterval() {
/* 127 */     return 400;
/*     */   }
/*     */
/*     */
/*     */   public int getMaxSpawnedInChunk() {
/* 132 */     return 2;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityTurkey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
