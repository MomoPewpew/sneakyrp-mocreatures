/*     */ package drzhark.mocreatures.entity.item;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityGolem;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityThrowableRock
/*     */   extends Entity
/*     */ {
/*     */   public int rockTimer;
/*  26 */   public int acceleration = 100;
/*     */   private double oPosX;
/*     */   private double oPosY;
/*     */   private double oPosZ;
/*  30 */   private static final DataParameter<Integer> ROCK_STATE = EntityDataManager.createKey(MoCEntityThrowableRock.class, DataSerializers.VARINT);
/*  31 */   private static final DataParameter<Integer> MASTERS_ID = EntityDataManager.createKey(MoCEntityThrowableRock.class, DataSerializers.VARINT);
/*  32 */   private static final DataParameter<Integer> BEHAVIOUR_TYPE = EntityDataManager.createKey(MoCEntityThrowableRock.class, DataSerializers.VARINT);
/*     */   
/*     */   public MoCEntityThrowableRock(World par1World) {
/*  35 */     super(par1World);
/*  36 */     this.preventEntitySpawning = true;
/*  37 */     setSize(1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MoCEntityThrowableRock(World par1World, Entity entitythrower, double par2, double par4, double par6) {
/*  43 */     this(par1World);
/*  44 */     setPosition(par2, par4, par6);
/*  45 */     this.rockTimer = 250;
/*  46 */     this.prevPosX = this.oPosX = par2;
/*  47 */     this.prevPosY = this.oPosY = par4;
/*  48 */     this.prevPosZ = this.oPosZ = par6;
/*  49 */     setMasterID(entitythrower.getEntityId());
/*     */   }
/*     */   
/*     */   public void setState(IBlockState state) {
/*  53 */     this.dataManager.set(ROCK_STATE, Integer.valueOf(Block.getStateId(state) & 0xFFFF));
/*     */   }
/*     */   
/*     */   public IBlockState getState() {
/*  57 */     return Block.getStateById(((Integer)this.dataManager.get(ROCK_STATE)).intValue() & 0xFFFF);
/*     */   }
/*     */   
/*     */   public void setMasterID(int i) {
/*  61 */     this.dataManager.set(MASTERS_ID, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int getMasterID() {
/*  65 */     return ((Integer)this.dataManager.get(MASTERS_ID)).intValue();
/*     */   }
/*     */   
/*     */   public void setBehavior(int i) {
/*  69 */     this.dataManager.set(BEHAVIOUR_TYPE, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public int getBehavior() {
/*  73 */     return ((Integer)this.dataManager.get(BEHAVIOUR_TYPE)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  78 */     this.dataManager.register(BEHAVIOUR_TYPE, Integer.valueOf(0));
/*  79 */     this.dataManager.register(ROCK_STATE, Integer.valueOf(0));
/*  80 */     this.dataManager.register(MASTERS_ID, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/*  85 */     IBlockState iblockstate = getState();
/*  86 */     nbttagcompound = MoCTools.getEntityData(this);
/*  87 */     nbttagcompound.setInteger("Behavior", getBehavior());
/*  88 */     nbttagcompound.setInteger("MasterID", getMasterID());
/*  89 */     nbttagcompound.setShort("BlockID", (short)Block.getIdFromBlock(iblockstate.getBlock()));
/*  90 */     nbttagcompound.setShort("BlockMetadata", (short)iblockstate.getBlock().getMetaFromState(iblockstate));
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/*  95 */     nbttagcompound = MoCTools.getEntityData(this);
/*  96 */     setBehavior(nbttagcompound.getInteger("Behavior"));
/*  97 */     setMasterID(nbttagcompound.getInteger("MasterID"));
/*     */     
/*  99 */     IBlockState iblockstate = Block.getBlockById(nbttagcompound.getShort("BlockID")).getStateFromMeta(nbttagcompound.getShort("BlockMetadata") & 0xFFFF);
/* 100 */     setState(iblockstate);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 105 */     return !this.isDead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityUpdate() {
/* 113 */     Entity master = getMaster();
/* 114 */     if (this.rockTimer-- <= -50 && getBehavior() == 0) {
/* 115 */       transformToItem();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     if (getBehavior() == 1) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 127 */     if (!this.onGround) {
/*     */       
/* 129 */       List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, 
/* 130 */           getEntityBoundingBox().contract(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
/*     */       
/* 132 */       for (int i = 0; i < list.size(); i++) {
/* 133 */         Entity entity1 = list.get(i);
/* 134 */         if (master == null || entity1.getEntityId() != master.getEntityId())
/*     */         {
/*     */           
/* 137 */           if (!(entity1 instanceof MoCEntityGolem))
/*     */           {
/*     */             
/* 140 */             if (entity1 == null || entity1 instanceof EntityLivingBase)
/*     */             {
/*     */ 
/*     */               
/* 144 */               if (master != null) {
/* 145 */                 entity1.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)master), 4.0F);
/*     */               } else {
/* 147 */                 entity1.attackEntityFrom(DamageSource.GENERIC, 4.0F);
/*     */               }  }  } 
/*     */         }
/*     */       } 
/*     */     } 
/* 152 */     this.prevPosX = this.posX;
/* 153 */     this.prevPosY = this.posY;
/* 154 */     this.prevPosZ = this.posZ;
/*     */     
/* 156 */     if (getBehavior() == 2) {
/* 157 */       if (master == null) {
/* 158 */         setBehavior(0);
/* 159 */         this.rockTimer = -50;
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 164 */       this.acceleration--;
/* 165 */       if (this.acceleration < 10) {
/* 166 */         this.acceleration = 10;
/*     */       }
/*     */       
/* 169 */       float tX = (float)this.posX - (float)master.posX;
/* 170 */       float tZ = (float)this.posZ - (float)master.posZ;
/* 171 */       float distXZToMaster = tX * tX + tZ * tZ;
/*     */       
/* 173 */       if (distXZToMaster < 1.5F && master instanceof MoCEntityGolem) {
/* 174 */         ((MoCEntityGolem)master).receiveRock(getState());
/* 175 */         setBehavior(0);
/* 176 */         setDead();
/*     */       } 
/*     */       
/* 179 */       double summonedSpeed = this.acceleration;
/* 180 */       this.motionX = (master.posX - this.posX) / summonedSpeed;
/* 181 */       this.motionY = (master.posY - this.posY) / 20.0D + 0.15D;
/* 182 */       this.motionZ = (master.posZ - this.posZ) / summonedSpeed;
/* 183 */       if (!this.world.isRemote) {
/* 184 */         move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/* 189 */     if (getBehavior() == 4) {
/*     */       
/* 191 */       if (master == null) {
/* 192 */         if (!this.world.isRemote) {
/* 193 */           setBehavior(5);
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 199 */       this.acceleration = 10;
/*     */       
/* 201 */       float tX = (float)this.posX - (float)master.posX;
/* 202 */       float tZ = (float)this.posZ - (float)master.posZ;
/* 203 */       float distXZToMaster = tX * tX + tZ * tZ;
/*     */       
/* 205 */       double summonedSpeed = this.acceleration;
/* 206 */       this.motionX = (master.posX - this.posX) / summonedSpeed;
/* 207 */       this.motionY = (master.posY - this.posY) / 20.0D + 0.15D;
/* 208 */       this.motionZ = (master.posZ - this.posZ) / summonedSpeed;
/*     */       
/* 210 */       if (distXZToMaster < 2.5F && master instanceof MoCEntityGolem) {
/* 211 */         this.motionX = 0.0D;
/* 212 */         this.motionY = 0.0D;
/* 213 */         this.motionZ = 0.0D;
/*     */       } 
/*     */       
/* 216 */       if (!this.world.isRemote) {
/* 217 */         move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 223 */     if (getBehavior() == 5) {
/*     */       
/* 225 */       this.acceleration = 5;
/* 226 */       double summonedSpeed = this.acceleration;
/* 227 */       this.motionX = (this.oPosX - this.posX) / summonedSpeed;
/* 228 */       this.motionY = (this.oPosY - this.posY) / 20.0D + 0.15D;
/* 229 */       this.motionZ = (this.oPosZ - this.posZ) / summonedSpeed;
/* 230 */       if (!this.world.isRemote) {
/* 231 */         move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
/*     */       }
/* 233 */       setBehavior(0);
/*     */       
/*     */       return;
/*     */     } 
/* 237 */     this.motionY -= 0.04D;
/* 238 */     if (!this.world.isRemote) {
/* 239 */       move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
/*     */     }
/* 241 */     this.motionX *= 0.98D;
/* 242 */     this.motionY *= 0.98D;
/* 243 */     this.motionZ *= 0.98D;
/*     */     
/* 245 */     if (this.onGround) {
/* 246 */       this.motionX *= 0.699D;
/* 247 */       this.motionZ *= 0.699D;
/* 248 */       this.motionY *= -0.5D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void transformToItem() {
/* 254 */     if (!this.world.isRemote && MoCTools.mobGriefing(this.world) && MoCreatures.proxy.golemDestroyBlocks) {
/*     */ 
/*     */ 
/*     */       
/* 258 */       EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(getState().getBlock(), 1, getState().getBlock().getMetaFromState(getState())));
/* 259 */       entityitem.setPickupDelay(10);
/* 260 */       entityitem.setAgeToCreativeDespawnTime();
/* 261 */       this.world.spawnEntity((Entity)entityitem);
/*     */     } 
/* 263 */     setDead();
/*     */   }
/*     */   
/*     */   public Block getMyBlock() {
/* 267 */     if (getState() != null) {
/* 268 */       return getState().getBlock();
/*     */     }
/* 270 */     return Blocks.STONE;
/*     */   }
/*     */   
/*     */   private Entity getMaster() {
/* 274 */     List<Entity> entityList = this.world.loadedEntityList;
/* 275 */     for (Entity ent : entityList) {
/* 276 */       if (ent.getEntityId() == getMasterID()) {
/* 277 */         return ent;
/*     */       }
/*     */     } 
/*     */     
/* 281 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\item\MoCEntityThrowableRock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */