/*     */ package drzhark.mocreatures.entity.item;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityOgre;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityLitterBox extends EntityLiving {
/*     */   public int littertime;
/*  34 */   private static final DataParameter<Boolean> PICKED_UP = EntityDataManager.createKey(MoCEntityLitterBox.class, DataSerializers.BOOLEAN);
/*  35 */   private static final DataParameter<Boolean> USED_LITTER = EntityDataManager.createKey(MoCEntityLitterBox.class, DataSerializers.BOOLEAN);
/*     */   
/*     */   public MoCEntityLitterBox(World world) {
/*  38 */     super(world);
/*  39 */     setSize(1.0F, 0.3F);
/*     */   }
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  43 */     return MoCreatures.proxy.getTexture("litterbox.png");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  48 */     super.applyEntityAttributes();
/*  49 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  54 */     super.entityInit();
/*  55 */     this.dataManager.register(PICKED_UP, Boolean.valueOf(false));
/*  56 */     this.dataManager.register(USED_LITTER, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean getPickedUp() {
/*  60 */     return ((Boolean)this.dataManager.get(PICKED_UP)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean getUsedLitter() {
/*  64 */     return ((Boolean)this.dataManager.get(USED_LITTER)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setPickedUp(boolean flag) {
/*  68 */     this.dataManager.set(PICKED_UP, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public void setUsedLitter(boolean flag) {
/*  72 */     this.dataManager.set(USED_LITTER, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean attackEntityFrom(Entity entity, int i) {
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/*  81 */     return !this.isDead;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBePushed() {
/*  86 */     return !this.isDead;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBreatheUnderwater() {
/*  91 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {}
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/* 105 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getYOffset() {
/* 110 */     if (getRidingEntity() instanceof EntityPlayer)
/*     */     {
/* 112 */       return ((EntityPlayer)getRidingEntity()).isSneaking() ? 0.25D : 0.5D;
/*     */     }
/* 114 */     return super.getYOffset();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleStatusUpdate(byte byte0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 124 */     ItemStack stack = player.getHeldItem(hand);
/* 125 */     if (!stack.isEmpty() && (stack.getItem() == Items.STONE_PICKAXE || stack.getItem() == Items.WOODEN_PICKAXE || stack
/* 126 */       .getItem() == Items.IRON_PICKAXE || stack.getItem() == Items.GOLDEN_PICKAXE || stack.getItem() == Items.DIAMOND_PICKAXE)) {
/* 127 */       player.inventory.addItemStackToInventory(new ItemStack((Item)MoCItems.litterbox));
/* 128 */       playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 129 */       setDead();
/* 130 */       return true;
/*     */     } 
/*     */     
/* 133 */     if (!stack.isEmpty() && stack.getItem() == Item.getItemFromBlock((Block)Blocks.SAND)) {
/* 134 */       stack.shrink(1);
/* 135 */       if (stack.isEmpty()) {
/* 136 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 138 */       setUsedLitter(false);
/* 139 */       this.littertime = 0;
/* 140 */       return true;
/*     */     } 
/*     */     
/* 143 */     if (getRidingEntity() == null) {
/* 144 */       if (startRiding((Entity)player)) {
/* 145 */         setPickedUp(true);
/* 146 */         this.rotationYaw = player.rotationYaw;
/*     */       } 
/*     */       
/* 149 */       return true;
/*     */     } 
/*     */     
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void move(MoverType type, double d, double d1, double d2) {
/* 157 */     if ((getRidingEntity() != null || !this.onGround || !MoCreatures.proxy.staticLitter) && 
/* 158 */       !this.world.isRemote) {
/* 159 */       super.move(type, d, d1, d2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 166 */     super.onUpdate();
/* 167 */     if (this.onGround) {
/* 168 */       setPickedUp(false);
/*     */     }
/* 170 */     if (getUsedLitter() && !this.world.isRemote) {
/* 171 */       this.littertime++;
/* 172 */       this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
/* 173 */       List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(12.0D, 4.0D, 12.0D));
/* 174 */       for (int i = 0; i < list.size(); i++) {
/* 175 */         Entity entity = list.get(i);
/* 176 */         if (entity instanceof EntityMob) {
/*     */ 
/*     */           
/* 179 */           EntityMob entitymob = (EntityMob)entity;
/* 180 */           entitymob.setAttackTarget((EntityLivingBase)this);
/* 181 */           if (entitymob instanceof EntityCreeper) {
/* 182 */             ((EntityCreeper)entitymob).setCreeperState(-1);
/*     */           }
/* 184 */           if (entitymob instanceof MoCEntityOgre) {
/* 185 */             ((MoCEntityOgre)entitymob).smashCounter = 0;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 190 */     if (this.littertime > 5000 && !this.world.isRemote) {
/* 191 */       setUsedLitter(false);
/* 192 */       this.littertime = 0;
/*     */     } 
/*     */     
/* 195 */     if (isRiding()) MoCTools.dismountSneakingPlayer(this);
/*     */   
/*     */   }
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 200 */     nbttagcompound = MoCTools.getEntityData((Entity)this);
/* 201 */     nbttagcompound.setBoolean("UsedLitter", getUsedLitter());
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 206 */     nbttagcompound = MoCTools.getEntityData((Entity)this);
/* 207 */     setUsedLitter(nbttagcompound.getBoolean("UsedLitter"));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 212 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\item\MoCEntityLitterBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */