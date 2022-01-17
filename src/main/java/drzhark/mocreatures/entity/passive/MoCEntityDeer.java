/*     */ package drzhark.mocreatures.entity.passive;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowAdult;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIPanic;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityDeer extends MoCEntityTameableAnimal {
/*     */   public MoCEntityDeer(World world) {
/*  29 */     super(world);
/*  30 */     setEdad(75);
/*  31 */     setSize(0.9F, 1.3F);
/*  32 */     setAdult(true);
/*  33 */     setTamed(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  38 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  39 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIFleeFromEntityMoC((EntityCreature)this, new Predicate<Entity>() {
/*     */             public boolean apply(Entity entity) {
/*  41 */               return (!(entity instanceof MoCEntityDeer) && (entity.height > 0.8F || entity.width > 0.8F));
/*     */             }
/*  43 */           },  6.0F, getMyAISpeed(), getMyAISpeed() * 1.2D));
/*  44 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIPanic((EntityCreature)this, getMyAISpeed() * 1.2D));
/*  45 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowAdult((EntityLiving)this, getMyAISpeed()));
/*  46 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, getMyAISpeed()));
/*  47 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
/*     */   }
/*     */   private int readyToJumpTimer;
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  52 */     super.applyEntityAttributes();
/*  53 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
/*  54 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  59 */     if (getType() == 0) {
/*  60 */       int i = this.rand.nextInt(100);
/*  61 */       if (i <= 20) {
/*  62 */         setType(1);
/*  63 */       } else if (i <= 70) {
/*  64 */         setType(2);
/*     */       } else {
/*  66 */         setAdult(false);
/*  67 */         setType(3);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  75 */     switch (getType()) {
/*     */       case 1:
/*  77 */         return MoCreatures.proxy.getTexture("deer.png");
/*     */       case 2:
/*  79 */         return MoCreatures.proxy.getTexture("deerf.png");
/*     */       case 3:
/*  81 */         setAdult(false);
/*  82 */         return MoCreatures.proxy.getTexture("deerb.png");
/*     */     } 
/*     */     
/*  85 */     return MoCreatures.proxy.getTexture("deer.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean entitiesToInclude(Entity entity) {
/*  95 */     return (!(entity instanceof MoCEntityDeer) && super.entitiesToInclude(entity));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 100 */     return (Item)MoCItems.fur;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 105 */     return MoCSoundEvents.ENTITY_DEER_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 110 */     return MoCSoundEvents.ENTITY_DEER_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 115 */     if (!getIsAdult()) {
/* 116 */       return MoCSoundEvents.ENTITY_DEER_AMBIENT_BABY;
/*     */     }
/* 118 */     return MoCSoundEvents.ENTITY_DEER_AMBIENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getMyAISpeed() {
/* 128 */     return 1.1D;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTalkInterval() {
/* 133 */     return 400;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/* 138 */     return 130;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAdult(boolean flag) {
/* 143 */     if (!this.world.isRemote) {
/* 144 */       setType(this.rand.nextInt(1));
/*     */     }
/* 146 */     super.setAdult(flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsAdult() {
/* 151 */     return (getType() != 3 && super.getIsAdult());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 156 */     super.onUpdate();
/*     */     
/* 158 */     if (!this.world.isRemote)
/*     */     {
/* 160 */       if (this.onGround && --this.readyToJumpTimer <= 0 && 
/* 161 */         MoCTools.getMyMovementSpeed((Entity)this) > 0.17F) {
/* 162 */         float velX = (float)(0.5D * Math.cos((MoCTools.realAngle(this.rotationYaw - 90.0F) / 57.29578F)));
/* 163 */         float velZ = (float)(0.5D * Math.sin((MoCTools.realAngle(this.rotationYaw - 90.0F) / 57.29578F)));
/* 164 */         this.motionX -= velX;
/* 165 */         this.motionZ -= velZ;
/* 166 */         this.motionY = 0.5D;
/* 167 */         this.readyToJumpTimer = this.rand.nextInt(10) + 20;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float pitchRotationOffset() {
/* 175 */     if (!this.onGround && MoCTools.getMyMovementSpeed((Entity)this) > 0.08F) {
/* 176 */       if (this.motionY > 0.5D) {
/* 177 */         return 25.0F;
/*     */       }
/* 179 */       if (this.motionY < -0.5D) {
/* 180 */         return -25.0F;
/*     */       }
/* 182 */       return (float)(this.motionY * 70.0D);
/*     */     } 
/* 184 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSizeFactor() {
/* 189 */     if (getType() == 1) {
/* 190 */       return 1.6F;
/*     */     }
/* 192 */     if (getType() == 2) {
/* 193 */       return 1.3F;
/*     */     }
/* 195 */     return getEdad() * 0.01F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityDeer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */