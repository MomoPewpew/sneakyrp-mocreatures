/*     */ package drzhark.mocreatures.entity.item;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.player.EntityPlayer;
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
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityKittyBed extends EntityLiving {
/*     */   public float milklevel;
/*  28 */   private static final DataParameter<Boolean> HAS_MILK = EntityDataManager.createKey(MoCEntityKittyBed.class, DataSerializers.BOOLEAN);
/*  29 */   private static final DataParameter<Boolean> HAS_FOOD = EntityDataManager.createKey(MoCEntityKittyBed.class, DataSerializers.BOOLEAN);
/*  30 */   private static final DataParameter<Boolean> PICKED_UP = EntityDataManager.createKey(MoCEntityKittyBed.class, DataSerializers.BOOLEAN);
/*  31 */   private static final DataParameter<Integer> SHEET_COLOR = EntityDataManager.createKey(MoCEntityKittyBed.class, DataSerializers.VARINT);
/*     */   
/*     */   public MoCEntityKittyBed(World world) {
/*  34 */     super(world);
/*  35 */     setSize(1.0F, 0.3F);
/*  36 */     this.milklevel = 0.0F;
/*     */   }
/*     */   
/*     */   public MoCEntityKittyBed(World world, double d, double d1, double d2) {
/*  40 */     super(world);
/*  41 */     setSize(1.0F, 0.3F);
/*  42 */     this.milklevel = 0.0F;
/*     */   }
/*     */   
/*     */   public MoCEntityKittyBed(World world, int i) {
/*  46 */     this(world);
/*  47 */     setSheetColor(i);
/*     */   }
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  51 */     return MoCreatures.proxy.getTexture("fullkittybed.png");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  56 */     super.applyEntityAttributes();
/*  57 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  62 */     super.entityInit();
/*  63 */     this.dataManager.register(HAS_MILK, Boolean.valueOf(false));
/*  64 */     this.dataManager.register(HAS_FOOD, Boolean.valueOf(false));
/*  65 */     this.dataManager.register(PICKED_UP, Boolean.valueOf(false));
/*  66 */     this.dataManager.register(SHEET_COLOR, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public boolean getHasFood() {
/*  70 */     return ((Boolean)this.dataManager.get(HAS_FOOD)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean getHasMilk() {
/*  74 */     return ((Boolean)this.dataManager.get(HAS_MILK)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean getPickedUp() {
/*  78 */     return ((Boolean)this.dataManager.get(PICKED_UP)).booleanValue();
/*     */   }
/*     */   
/*     */   public int getSheetColor() {
/*  82 */     return ((Integer)this.dataManager.get(SHEET_COLOR)).intValue();
/*     */   }
/*     */   
/*     */   public void setHasFood(boolean flag) {
/*  86 */     this.dataManager.set(HAS_FOOD, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public void setHasMilk(boolean flag) {
/*  90 */     this.dataManager.set(HAS_MILK, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public void setPickedUp(boolean flag) {
/*  94 */     this.dataManager.set(PICKED_UP, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public void setSheetColor(int i) {
/*  98 */     this.dataManager.set(SHEET_COLOR, Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(Entity entity, int i) {
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 108 */     return !this.isDead;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBePushed() {
/* 113 */     return !this.isDead;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBreatheUnderwater() {
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canEntityBeSeen(Entity entity) {
/* 128 */     return (this.world.rayTraceBlocks(new Vec3d(this.posX, this.posY + getEyeHeight(), this.posZ), new Vec3d(entity.posX, entity.posY + entity
/* 129 */           .getEyeHeight(), entity.posZ)) == null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {}
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/* 138 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getYOffset() {
/* 143 */     if (getRidingEntity() instanceof EntityPlayer)
/*     */     {
/* 145 */       return ((EntityPlayer)getRidingEntity()).isSneaking() ? 0.25D : 0.5D;
/*     */     }
/*     */     
/* 148 */     return super.getYOffset();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleStatusUpdate(byte byte0) {}
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/* 157 */     ItemStack stack = player.getHeldItem(hand);
/* 158 */     if (!stack.isEmpty() && stack.getItem() == Items.MILK_BUCKET) {
/* 159 */       player.setHeldItem(hand, new ItemStack(Items.BUCKET, 1));
/* 160 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_KITTYBED_POURINGMILK);
/* 161 */       setHasMilk(true);
/* 162 */       setHasFood(false);
/* 163 */       return true;
/*     */     } 
/* 165 */     if (!stack.isEmpty() && !getHasFood() && stack.getItem() == MoCItems.petfood) {
/* 166 */       stack.shrink(1);
/* 167 */       if (stack.isEmpty()) {
/* 168 */         player.setHeldItem(hand, ItemStack.EMPTY);
/*     */       }
/* 170 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_KITTYBED_POURINGFOOD);
/* 171 */       setHasMilk(false);
/* 172 */       setHasFood(true);
/* 173 */       return true;
/*     */     } 
/* 175 */     if (!stack.isEmpty() && (stack.getItem() == Items.STONE_PICKAXE || stack.getItem() == Items.WOODEN_PICKAXE || stack
/* 176 */       .getItem() == Items.IRON_PICKAXE || stack.getItem() == Items.GOLDEN_PICKAXE || stack.getItem() == Items.DIAMOND_PICKAXE)) {
/* 177 */       int color = getSheetColor();
/* 178 */       player.inventory.addItemStackToInventory(new ItemStack((Item)MoCItems.kittybed[color], 1));
/* 179 */       playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 180 */       setDead();
/* 181 */       return true;
/*     */     } 
/* 183 */     if (getRidingEntity() == null) {
/* 184 */       if (startRiding((Entity)player)) {
/* 185 */         this.rotationYaw = player.rotationYaw;
/* 186 */         setPickedUp(true);
/*     */       } 
/*     */       
/* 189 */       return true;
/*     */     } 
/*     */     
/* 192 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void move(MoverType type, double d, double d1, double d2) {
/* 197 */     if ((getRidingEntity() != null || !this.onGround || !MoCreatures.proxy.staticLitter) && 
/* 198 */       !this.world.isRemote) {
/* 199 */       super.move(type, d, d1, d2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 206 */     super.onUpdate();
/* 207 */     if (this.onGround) {
/* 208 */       setPickedUp(false);
/*     */     }
/* 210 */     if ((getHasMilk() || getHasFood()) && isBeingRidden() && !this.world.isRemote) {
/* 211 */       this.milklevel += 0.003F;
/* 212 */       if (this.milklevel > 2.0F) {
/* 213 */         this.milklevel = 0.0F;
/* 214 */         setHasMilk(false);
/* 215 */         setHasFood(false);
/*     */       } 
/*     */     } 
/* 218 */     if (isRiding()) MoCTools.dismountSneakingPlayer(this);
/*     */   
/*     */   }
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 223 */     setHasMilk(nbttagcompound.getBoolean("HasMilk"));
/* 224 */     setSheetColor(nbttagcompound.getInteger("SheetColour"));
/* 225 */     setHasFood(nbttagcompound.getBoolean("HasFood"));
/* 226 */     this.milklevel = nbttagcompound.getFloat("MilkLevel");
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 231 */     nbttagcompound.setBoolean("HasMilk", getHasMilk());
/* 232 */     nbttagcompound.setInteger("SheetColour", getSheetColor());
/* 233 */     nbttagcompound.setBoolean("HasFood", getHasFood());
/* 234 */     nbttagcompound.setFloat("MilkLevel", this.milklevel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 239 */     this.moveStrafing = 0.0F;
/* 240 */     this.moveForward = 0.0F;
/* 241 */     this.randomYawVelocity = 0.0F;
/* 242 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 247 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\item\MoCEntityKittyBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */