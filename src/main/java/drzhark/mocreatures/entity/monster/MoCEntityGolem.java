/*     */ package drzhark.mocreatures.entity.monster;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityMob;
/*     */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*     */ import drzhark.mocreatures.entity.item.MoCEntityThrowableRock;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*     */ import drzhark.mocreatures.network.message.MoCMessageTwoBytes;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
/*     */ import net.minecraftforge.event.world.BlockEvent;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ 
/*     */ public class MoCEntityGolem extends MoCEntityMob implements IEntityAdditionalSpawnData {
/*     */   public int tcounter;
/*  50 */   private int dCounter = 0; public MoCEntityThrowableRock tempRock; private byte[] golemCubes;
/*     */   private int sCounter;
/*  52 */   private static final DataParameter<Integer> GOLEM_STATE = EntityDataManager.createKey(MoCEntityGolem.class, DataSerializers.VARINT);
/*     */   
/*     */   public MoCEntityGolem(World world) {
/*  55 */     super(world);
/*  56 */     this.texture = "golemt.png";
/*  57 */     setSize(1.5F, 4.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  62 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  63 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  64 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  65 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  70 */     super.applyEntityAttributes();
/*  71 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
/*  72 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*  73 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(ByteBuf data) {
/*  78 */     for (int i = 0; i < 23; i++) {
/*  79 */       data.writeByte(this.golemCubes[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(ByteBuf data) {
/*  85 */     for (int i = 0; i < 23; i++) {
/*  86 */       this.golemCubes[i] = data.readByte();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  92 */     super.entityInit();
/*  93 */     initGolemCubes();
/*  94 */     this.dataManager.register(GOLEM_STATE, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public int getGolemState() {
/*  98 */     return ((Integer)this.dataManager.get(GOLEM_STATE)).intValue();
/*     */   }
/*     */   
/*     */   public void setGolemState(int i) {
/* 102 */     this.dataManager.set(GOLEM_STATE, Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 107 */     super.onLivingUpdate();
/*     */     
/* 109 */     if (!this.world.isRemote) {
/* 110 */       if (getGolemState() == 0) {
/*     */         
/* 112 */         EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 8.0D);
/* 113 */         if (entityplayer1 != null) {
/* 114 */           setGolemState(1);
/*     */         }
/*     */       } 
/*     */       
/* 118 */       if (getGolemState() == 1 && !isMissingCubes())
/*     */       {
/* 120 */         setGolemState(2);
/*     */       }
/*     */       
/* 123 */       if (getGolemState() > 2 && getGolemState() != 4 && getAttackTarget() == null) {
/* 124 */         setGolemState(1);
/*     */       }
/*     */       
/* 127 */       if (getGolemState() > 1 && getAttackTarget() != null && this.rand.nextInt(20) == 0) {
/* 128 */         if (getHealth() >= 30.0F) {
/* 129 */           setGolemState(2);
/*     */         }
/* 131 */         if (getHealth() < 30.0F && getHealth() >= 10.0F) {
/* 132 */           setGolemState(3);
/*     */         }
/* 134 */         if (getHealth() < 10.0F) {
/* 135 */           setGolemState(4);
/*     */         }
/*     */       } 
/*     */       
/* 139 */       if (getGolemState() != 0 && getGolemState() != 4 && isMissingCubes()) {
/*     */         
/* 141 */         int freq = 21 - getGolemState() * this.world.getDifficulty().getId();
/* 142 */         if (getGolemState() == 1) {
/* 143 */           freq = 10;
/*     */         }
/* 145 */         if (this.rand.nextInt(freq) == 0) {
/* 146 */           acquireRock(2);
/*     */         }
/*     */       } 
/*     */       
/* 150 */       if (getGolemState() == 4) {
/* 151 */         getNavigator().clearPath();
/* 152 */         this.dCounter++;
/*     */         
/* 154 */         if (this.dCounter < 80 && this.rand.nextInt(3) == 0) {
/* 155 */           acquireRock(4);
/*     */         }
/*     */         
/* 158 */         if (this.dCounter == 120) {
/* 159 */           MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOLEM_DYING, 3.0F);
/* 160 */           MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
/* 161 */                 .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */         } 
/*     */         
/* 164 */         if (this.dCounter > 140) {
/* 165 */           MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOLEM_EXPLODE, 3.0F);
/* 166 */           destroyGolem();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 171 */     if (this.tcounter == 0 && getAttackTarget() != null && canShoot()) {
/* 172 */       float distanceToTarget = getDistance((Entity)getAttackTarget());
/* 173 */       if (distanceToTarget > 6.0F) {
/* 174 */         this.tcounter = 1;
/* 175 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
/* 176 */               .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */       } 
/*     */     } 
/*     */     
/* 180 */     if (this.tcounter != 0) {
/* 181 */       if (this.tcounter++ == 70 && getAttackTarget() != null && canShoot() && !(getAttackTarget()).isDead && 
/* 182 */         canEntityBeSeen((Entity)getAttackTarget())) {
/* 183 */         shootBlock((Entity)getAttackTarget());
/* 184 */       } else if (this.tcounter > 90) {
/* 185 */         this.tcounter = 0;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 190 */     if (MoCreatures.proxy.getParticleFX() > 0 && getGolemState() == 4 && this.sCounter > 0) {
/* 191 */       for (int i = 0; i < 10; i++) {
/* 192 */         this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX, this.posY, this.posZ, this.rand.nextGaussian(), this.rand
/* 193 */             .nextGaussian(), this.rand.nextGaussian(), new int[0]);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void destroyGolem() {
/* 199 */     List<Integer> usedBlocks = usedCubes();
/* 200 */     if (!usedBlocks.isEmpty() && MoCTools.mobGriefing(this.world) && MoCreatures.proxy.golemDestroyBlocks) {
/* 201 */       for (int i = 0; i < usedBlocks.size(); i++) {
/* 202 */         Block block = Block.getBlockById(generateBlock(this.golemCubes[((Integer)usedBlocks.get(i)).intValue()]));
/* 203 */         EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(block, 1, 0));
/* 204 */         entityitem.setPickupDelay(10);
/* 205 */         this.world.spawnEntity((Entity)entityitem);
/*     */       } 
/*     */     }
/* 208 */     setDead();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMovementCeased() {
/* 213 */     return (getGolemState() == 4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void acquireRock(int type) {
/* 219 */     BlockPos myTRockPos = MoCTools.getRandomBlockPos((Entity)this, 24.0D);
/* 220 */     if (myTRockPos == null) {
/*     */       return;
/*     */     }
/*     */     
/* 224 */     boolean canDestroyBlocks = (MoCTools.mobGriefing(this.world) && MoCreatures.proxy.golemDestroyBlocks);
/* 225 */     IBlockState blockstate = this.world.getBlockState(myTRockPos);
/* 226 */     if (Block.getIdFromBlock(blockstate.getBlock()) == 0) {
/*     */       return;
/*     */     }
/* 229 */     BlockEvent.BreakEvent event = null;
/* 230 */     if (!this.world.isRemote)
/*     */     {
/* 232 */       event = new BlockEvent.BreakEvent(this.world, myTRockPos, blockstate, (EntityPlayer)FakePlayerFactory.get((WorldServer)this.world, MoCreatures.MOCFAKEPLAYER));
/*     */     }
/*     */     
/* 235 */     if (canDestroyBlocks && event != null && !event.isCanceled()) {
/*     */       
/* 237 */       this.world.setBlockToAir(myTRockPos);
/*     */     } else {
/*     */       
/* 240 */       canDestroyBlocks = false;
/*     */     } 
/*     */     
/* 243 */     if (!canDestroyBlocks)
/*     */     {
/* 245 */       blockstate = Block.getStateById(returnRandomCheapBlock());
/*     */     }
/*     */     
/* 248 */     MoCEntityThrowableRock trock = new MoCEntityThrowableRock(this.world, (Entity)this, myTRockPos.getX(), (myTRockPos.getY() + 1), myTRockPos.getZ());
/* 249 */     trock.setState(blockstate);
/* 250 */     trock.setBehavior(type);
/*     */ 
/*     */     
/* 253 */     this.world.spawnEntity((Entity)trock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int returnRandomCheapBlock() {
/* 262 */     int i = this.rand.nextInt(4);
/* 263 */     switch (i) {
/*     */       case 0:
/* 265 */         return 3;
/*     */       case 1:
/* 267 */         return 4;
/*     */       case 2:
/* 269 */         return 5;
/*     */       case 3:
/* 271 */         return 79;
/*     */     } 
/* 273 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void receiveRock(IBlockState state) {
/* 283 */     if (!this.world.isRemote) {
/* 284 */       byte myBlock = translateOre(Block.getIdFromBlock(state.getBlock()));
/* 285 */       byte slot = (byte)getRandomCubeAdj();
/* 286 */       if (slot != -1 && slot < 23 && myBlock != -1 && getGolemState() != 4) {
/* 287 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOLEM_ATTACH, 3.0F);
/* 288 */         int h = this.world.getDifficulty().getId();
/* 289 */         setHealth(getHealth() + h);
/* 290 */         if (getHealth() > getMaxHealth()) {
/* 291 */           setHealth(getMaxHealth());
/*     */         }
/* 293 */         saveGolemCube(slot, myBlock);
/*     */       } else {
/* 295 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_TURTLE_HURT, 2.0F);
/* 296 */         if (MoCTools.mobGriefing(this.world) && MoCreatures.proxy.golemDestroyBlocks) {
/*     */ 
/*     */           
/* 299 */           EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)));
/* 300 */           entityitem.setPickupDelay(10);
/* 301 */           entityitem.setAgeToCreativeDespawnTime();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void performAnimation(int animationType) {
/* 309 */     if (animationType == 0)
/*     */     {
/* 311 */       this.tcounter = 1;
/*     */     }
/* 313 */     if (animationType == 1)
/*     */     {
/* 315 */       this.sCounter = 1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void shootBlock(Entity entity) {
/* 324 */     if (entity == null) {
/*     */       return;
/*     */     }
/* 327 */     List<Integer> armBlocks = new ArrayList<>();
/*     */     
/* 329 */     for (int i = 9; i < 15; i++) {
/* 330 */       if (this.golemCubes[i] != 30) {
/* 331 */         armBlocks.add(Integer.valueOf(i));
/*     */       }
/*     */     } 
/* 334 */     if (armBlocks.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 338 */     int j = this.rand.nextInt(armBlocks.size());
/* 339 */     int k = ((Integer)armBlocks.get(j)).intValue();
/* 340 */     int x = k;
/*     */     
/* 342 */     if (k == 9 || k == 12) {
/* 343 */       if (this.golemCubes[k + 2] != 30) {
/* 344 */         x = k + 2;
/* 345 */       } else if (this.golemCubes[k + 1] != 30) {
/* 346 */         x = k + 1;
/*     */       } 
/*     */     }
/*     */     
/* 350 */     if ((k == 10 || k == 13) && 
/* 351 */       this.golemCubes[k + 1] != 30) {
/* 352 */       x = k + 1;
/*     */     }
/*     */     
/* 355 */     MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOLEM_SHOOT, 3.0F);
/* 356 */     MoCTools.ThrowStone((Entity)this, entity, Block.getStateById(generateBlock(this.golemCubes[x])), 10.0D, 0.4D);
/* 357 */     saveGolemCube((byte)x, (byte)30);
/* 358 */     this.tcounter = 0;
/*     */   }
/*     */   
/*     */   private boolean canShoot() {
/* 362 */     int x = 0;
/* 363 */     for (byte i = 9; i < 15; i = (byte)(i + 1)) {
/* 364 */       if (this.golemCubes[i] != 30) {
/* 365 */         x++;
/*     */       }
/*     */     } 
/* 368 */     return (x != 0 && getGolemState() != 4 && getGolemState() != 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 373 */     if (getGolemState() == 4) {
/* 374 */       return false;
/*     */     }
/*     */     
/* 377 */     List<Integer> missingChestBlocks = missingChestCubes();
/* 378 */     boolean uncoveredChest = (missingChestBlocks.size() == 4);
/* 379 */     if (!openChest() && !uncoveredChest && getGolemState() != 1) {
/* 380 */       int j = this.world.getDifficulty().getId();
/* 381 */       if (!this.world.isRemote && this.rand.nextInt(j) == 0) {
/* 382 */         destroyRandomGolemCube();
/*     */       } else {
/* 384 */         MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_TURTLE_HURT, 2.0F);
/*     */       } 
/*     */       
/* 387 */       Entity entity = damagesource.getTrueSource();
/* 388 */       if (entity != this && this.world.getDifficulty().getId() > 0 && entity instanceof EntityLivingBase) {
/* 389 */         setAttackTarget((EntityLivingBase)entity);
/* 390 */         return true;
/*     */       } 
/* 392 */       return false;
/*     */     } 
/*     */     
/* 395 */     if (i > 5.0F) {
/* 396 */       i = 5.0F;
/*     */     }
/* 398 */     if (getGolemState() != 1 && super.attackEntityFrom(damagesource, i)) {
/* 399 */       Entity entity = damagesource.getTrueSource();
/* 400 */       if (entity != this && this.world.getDifficulty().getId() > 0 && entity instanceof EntityLivingBase) {
/* 401 */         setAttackTarget((EntityLivingBase)entity);
/* 402 */         return true;
/*     */       } 
/* 404 */       return false;
/*     */     } 
/*     */     
/* 407 */     if (getGolemState() == 1) {
/* 408 */       Entity entity = damagesource.getTrueSource();
/* 409 */       if (entity != this && this.world.getDifficulty().getId() > 0 && entity instanceof EntityLivingBase) {
/* 410 */         setAttackTarget((EntityLivingBase)entity);
/* 411 */         return true;
/*     */       } 
/* 413 */       return false;
/*     */     } 
/*     */     
/* 416 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void destroyRandomGolemCube() {
/* 425 */     int i = getRandomUsedCube();
/* 426 */     if (i == 4) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 431 */     int x = i;
/* 432 */     if ((i == 10 || i == 13 || i == 16 || i == 19) && 
/* 433 */       this.golemCubes[i + 1] != 30) {
/* 434 */       x = i + 1;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 439 */     if (i == 9 || i == 12 || i == 15 || i == 18) {
/* 440 */       if (this.golemCubes[i + 2] != 30) {
/* 441 */         x = i + 2;
/* 442 */       } else if (this.golemCubes[i + 1] != 30) {
/* 443 */         x = i + 1;
/*     */       } 
/*     */     }
/*     */     
/* 447 */     if (x != -1 && this.golemCubes[x] != 30) {
/* 448 */       Block block = Block.getBlockById(generateBlock(this.golemCubes[x]));
/* 449 */       saveGolemCube((byte)x, (byte)30);
/* 450 */       MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOLEM_HURT, 3.0F);
/* 451 */       if (MoCTools.mobGriefing(this.world) && MoCreatures.proxy.golemDestroyBlocks) {
/* 452 */         EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(block, 1, 0));
/* 453 */         entityitem.setPickupDelay(10);
/* 454 */         this.world.spawnEntity((Entity)entityitem);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAdjustedYOffset() {
/* 461 */     if (this.golemCubes[17] != 30 || this.golemCubes[20] != 30)
/*     */     {
/* 463 */       return 0.0F;
/*     */     }
/* 465 */     if (this.golemCubes[16] != 30 || this.golemCubes[19] != 30)
/*     */     {
/* 467 */       return 0.4F;
/*     */     }
/* 469 */     if (this.golemCubes[15] != 30 || this.golemCubes[18] != 30)
/*     */     {
/* 471 */       return 0.7F;
/*     */     }
/*     */     
/* 474 */     if (this.golemCubes[1] != 30 || this.golemCubes[3] != 30)
/*     */     {
/* 476 */       return 0.8F;
/*     */     }
/*     */     
/* 479 */     return 1.45F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSizeFactor() {
/* 487 */     return 1.8F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getBlockText(int i) {
/* 495 */     return this.golemCubes[i];
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 500 */     super.writeEntityToNBT(nbttagcompound);
/* 501 */     nbttagcompound.setInteger("golemState", getGolemState());
/* 502 */     NBTTagList cubeLists = new NBTTagList();
/*     */     
/* 504 */     for (int i = 0; i < 23; i++) {
/* 505 */       NBTTagCompound nbttag = new NBTTagCompound();
/* 506 */       nbttag.setByte("Slot", this.golemCubes[i]);
/* 507 */       cubeLists.appendTag((NBTBase)nbttag);
/*     */     } 
/* 509 */     nbttagcompound.setTag("GolemBlocks", (NBTBase)cubeLists);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 514 */     super.readEntityFromNBT(nbttagcompound);
/* 515 */     setGolemState(nbttagcompound.getInteger("golemState"));
/* 516 */     NBTTagList nbttaglist = nbttagcompound.getTagList("GolemBlocks", 10);
/* 517 */     for (int i = 0; i < 23; i++) {
/* 518 */       NBTTagCompound var4 = nbttaglist.getCompoundTagAt(i);
/* 519 */       this.golemCubes[i] = var4.getByte("Slot");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initGolemCubes() {
/* 527 */     this.golemCubes = new byte[23];
/*     */     
/* 529 */     for (int i = 0; i < 23; i++) {
/* 530 */       this.golemCubes[i] = 30;
/*     */     }
/*     */     
/* 533 */     int j = this.rand.nextInt(4);
/* 534 */     switch (j) {
/*     */       case 0:
/* 536 */         j = 7;
/*     */         break;
/*     */       case 1:
/* 539 */         j = 11;
/*     */         break;
/*     */       case 2:
/* 542 */         j = 15;
/*     */         break;
/*     */       case 3:
/* 545 */         j = 21;
/*     */         break;
/*     */     } 
/* 548 */     saveGolemCube((byte)4, (byte)j);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveGolemCube(byte slot, byte value) {
/* 559 */     this.golemCubes[slot] = value;
/* 560 */     if (!this.world.isRemote && MoCreatures.proxy.worldInitDone)
/*     */     {
/* 562 */       MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageTwoBytes(getEntityId(), slot, value), new NetworkRegistry.TargetPoint(this.world.provider
/* 563 */             .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Integer> missingCubes() {
/* 573 */     List<Integer> emptyBlocks = new ArrayList<>();
/*     */     
/* 575 */     for (int i = 0; i < 23; i++) {
/* 576 */       if (this.golemCubes[i] == 30) {
/* 577 */         emptyBlocks.add(Integer.valueOf(i));
/*     */       }
/*     */     } 
/* 580 */     return emptyBlocks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMissingCubes() {
/* 589 */     for (int i = 0; i < 23; i++) {
/* 590 */       if (this.golemCubes[i] == 30) {
/* 591 */         return true;
/*     */       }
/*     */     } 
/* 594 */     return false;
/*     */   }
/*     */   
/*     */   private List<Integer> missingChestCubes() {
/* 598 */     List<Integer> emptyChestBlocks = new ArrayList<>();
/*     */     
/* 600 */     for (int i = 0; i < 4; i++) {
/* 601 */       if (this.golemCubes[i] == 30) {
/* 602 */         emptyChestBlocks.add(Integer.valueOf(i));
/*     */       }
/*     */     } 
/* 605 */     return emptyChestBlocks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Integer> usedCubes() {
/* 614 */     List<Integer> usedBlocks = new ArrayList<>();
/*     */     
/* 616 */     for (int i = 0; i < 23; i++) {
/* 617 */       if (this.golemCubes[i] != 30) {
/* 618 */         usedBlocks.add(Integer.valueOf(i));
/*     */       }
/*     */     } 
/* 621 */     return usedBlocks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getRandomUsedCube() {
/* 630 */     List<Integer> usedBlocks = usedCubes();
/* 631 */     if (usedBlocks.isEmpty()) {
/* 632 */       return -1;
/*     */     }
/* 634 */     int randomEmptyBlock = this.rand.nextInt(usedBlocks.size());
/* 635 */     return ((Integer)usedBlocks.get(randomEmptyBlock)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getRandomMissingCube() {
/* 645 */     List<Integer> emptyChestBlocks = missingChestCubes();
/* 646 */     if (!emptyChestBlocks.isEmpty()) {
/* 647 */       int i = this.rand.nextInt(emptyChestBlocks.size());
/* 648 */       return ((Integer)emptyChestBlocks.get(i)).intValue();
/*     */     } 
/*     */ 
/*     */     
/* 652 */     List<Integer> emptyBlocks = missingCubes();
/* 653 */     if (emptyBlocks.isEmpty()) {
/* 654 */       return -1;
/*     */     }
/* 656 */     int randomEmptyBlock = this.rand.nextInt(emptyBlocks.size());
/* 657 */     return ((Integer)emptyBlocks.get(randomEmptyBlock)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getRandomCubeAdj() {
/* 667 */     int i = getRandomMissingCube();
/*     */     
/* 669 */     if (i == 10 || i == 13 || i == 16 || i == 19) {
/* 670 */       if (this.golemCubes[i - 1] == 30) {
/* 671 */         return i - 1;
/*     */       }
/* 673 */       saveGolemCube((byte)i, this.golemCubes[i - 1]);
/* 674 */       return i - 1;
/*     */     } 
/*     */ 
/*     */     
/* 678 */     if (i == 11 || i == 14 || i == 17 || i == 20) {
/* 679 */       if (this.golemCubes[i - 2] == 30 && this.golemCubes[i - 1] == 30) {
/* 680 */         return i - 2;
/*     */       }
/* 682 */       if (this.golemCubes[i - 1] == 30) {
/* 683 */         saveGolemCube((byte)(i - 1), this.golemCubes[i - 2]);
/* 684 */         return i - 2;
/*     */       } 
/* 686 */       saveGolemCube((byte)i, this.golemCubes[i - 1]);
/* 687 */       saveGolemCube((byte)(i - 1), this.golemCubes[i - 2]);
/* 688 */       return i - 2;
/*     */     } 
/*     */     
/* 691 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public float rollRotationOffset() {
/* 696 */     int leftLeg = 0;
/* 697 */     int rightLeg = 0;
/* 698 */     if (this.golemCubes[15] != 30) {
/* 699 */       leftLeg++;
/*     */     }
/* 701 */     if (this.golemCubes[16] != 30) {
/* 702 */       leftLeg++;
/*     */     }
/* 704 */     if (this.golemCubes[17] != 30) {
/* 705 */       leftLeg++;
/*     */     }
/* 707 */     if (this.golemCubes[18] != 30) {
/* 708 */       rightLeg++;
/*     */     }
/* 710 */     if (this.golemCubes[19] != 30) {
/* 711 */       rightLeg++;
/*     */     }
/* 713 */     if (this.golemCubes[20] != 30) {
/* 714 */       rightLeg++;
/*     */     }
/* 716 */     return (leftLeg - rightLeg) * 10.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean openChest() {
/* 726 */     if (isMissingCubes()) {
/* 727 */       List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(2.0D, 2.0D, 2.0D));
/*     */       
/* 729 */       for (int i = 0; i < list.size(); i++) {
/* 730 */         Entity entity1 = list.get(i);
/* 731 */         if (entity1 instanceof MoCEntityThrowableRock) {
/* 732 */           if (MoCreatures.proxy.getParticleFX() > 0) {
/* 733 */             MoCreatures.proxy.VacuumFX(this);
/*     */           }
/* 735 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 739 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte translateOre(int blockType) {
/* 750 */     switch (blockType) {
/*     */       case 0:
/* 752 */         return 0;
/*     */       case 1:
/* 754 */         return 0;
/*     */       case 18:
/* 756 */         return 10;
/*     */       case 2:
/*     */       case 3:
/* 759 */         return 1;
/*     */       case 4:
/*     */       case 48:
/* 762 */         return 2;
/*     */       case 5:
/* 764 */         return 3;
/*     */       case 12:
/* 766 */         return 4;
/*     */       case 13:
/* 768 */         return 5;
/*     */       case 16:
/*     */       case 21:
/*     */       case 56:
/*     */       case 73:
/*     */       case 74:
/* 774 */         return 24;
/*     */       case 14:
/*     */       case 41:
/* 777 */         return 7;
/*     */       case 15:
/*     */       case 42:
/* 780 */         return 11;
/*     */       case 57:
/* 782 */         return 15;
/*     */       case 17:
/* 784 */         return 6;
/*     */       case 20:
/* 786 */         return 8;
/*     */       case 22:
/*     */       case 35:
/* 789 */         return 9;
/*     */       case 45:
/* 791 */         return 12;
/*     */       case 49:
/* 793 */         return 14;
/*     */       case 58:
/* 795 */         return 16;
/*     */       case 61:
/*     */       case 62:
/* 798 */         return 17;
/*     */       case 78:
/*     */       case 79:
/* 801 */         return 18;
/*     */       case 81:
/* 803 */         return 19;
/*     */       case 82:
/* 805 */         return 20;
/*     */       case 86:
/*     */       case 91:
/*     */       case 103:
/* 809 */         return 22;
/*     */       case 87:
/* 811 */         return 23;
/*     */       case 89:
/* 813 */         return 25;
/*     */       case 98:
/* 815 */         return 26;
/*     */       case 112:
/* 817 */         return 27;
/*     */       case 129:
/*     */       case 133:
/* 820 */         return 21;
/*     */     } 
/* 822 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int generateBlock(int golemBlock) {
/* 833 */     switch (golemBlock) {
/*     */       case 0:
/* 835 */         return 1;
/*     */       case 1:
/* 837 */         return 3;
/*     */       case 2:
/* 839 */         return 4;
/*     */       case 3:
/* 841 */         return 5;
/*     */       case 4:
/* 843 */         return 12;
/*     */       case 5:
/* 845 */         return 13;
/*     */       case 6:
/* 847 */         return 17;
/*     */       case 7:
/* 849 */         return 41;
/*     */       case 8:
/* 851 */         return 20;
/*     */       case 9:
/* 853 */         return 35;
/*     */       case 10:
/* 855 */         return 18;
/*     */       case 11:
/* 857 */         return 42;
/*     */       case 12:
/* 859 */         return 45;
/*     */       case 13:
/* 861 */         return 2;
/*     */       case 14:
/* 863 */         return 49;
/*     */       case 15:
/* 865 */         return 57;
/*     */       case 16:
/* 867 */         return 58;
/*     */       case 17:
/* 869 */         return 51;
/*     */       case 18:
/* 871 */         return 79;
/*     */       case 19:
/* 873 */         return 81;
/*     */       case 20:
/* 875 */         return 82;
/*     */       case 21:
/* 877 */         return 133;
/*     */       case 22:
/* 879 */         return 86;
/*     */       case 23:
/* 881 */         return 87;
/*     */       case 24:
/* 883 */         return 56;
/*     */       case 25:
/* 885 */         return 89;
/*     */       case 26:
/* 887 */         return 98;
/*     */       case 27:
/* 889 */         return 112;
/*     */     } 
/* 891 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   private int countLegBlocks() {
/* 896 */     int x = 0;
/* 897 */     for (byte i = 15; i < 21; i = (byte)(i + 1)) {
/* 898 */       if (this.golemCubes[i] != 30) {
/* 899 */         x++;
/*     */       }
/*     */     } 
/* 902 */     return x;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAIMoveSpeed() {
/* 907 */     return 0.15F * countLegBlocks() / 6.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getEffectTexture() {
/* 916 */     switch (getGolemState()) {
/*     */       case 1:
/* 918 */         return MoCreatures.proxy.getTexture("golemeffect1.png");
/*     */       case 2:
/* 920 */         return MoCreatures.proxy.getTexture("golemeffect2.png");
/*     */       case 3:
/* 922 */         return MoCreatures.proxy.getTexture("golemeffect3.png");
/*     */       case 4:
/* 924 */         return MoCreatures.proxy.getTexture("golemeffect4.png");
/*     */     } 
/* 926 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float colorFX(int i) {
/* 937 */     switch (getGolemState()) {
/*     */       case 1:
/* 939 */         if (i == 1) {
/* 940 */           return 0.25490198F;
/*     */         }
/* 942 */         if (i == 2) {
/* 943 */           return 0.6156863F;
/*     */         }
/* 945 */         if (i == 3) {
/* 946 */           return 0.99607843F;
/*     */         }
/*     */       case 2:
/* 949 */         if (i == 1) {
/* 950 */           return 0.95686275F;
/*     */         }
/* 952 */         if (i == 2) {
/* 953 */           return 0.972549F;
/*     */         }
/* 955 */         if (i == 3) {
/* 956 */           return 0.14117648F;
/*     */         }
/*     */       case 3:
/* 959 */         if (i == 1) {
/* 960 */           return 1.0F;
/*     */         }
/* 962 */         if (i == 2) {
/* 963 */           return 0.6039216F;
/*     */         }
/* 965 */         if (i == 3) {
/* 966 */           return 0.08235294F;
/*     */         }
/*     */       case 4:
/* 969 */         if (i == 1) {
/* 970 */           return 0.972549F;
/*     */         }
/* 972 */         if (i == 2) {
/* 973 */           return 0.039215688F;
/*     */         }
/* 975 */         if (i == 3)
/* 976 */           return 0.039215688F; 
/*     */         break;
/*     */     } 
/* 979 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playStepSound(BlockPos p_180429_1_, Block p_180429_2_) {
/* 987 */     playSound(MoCSoundEvents.ENTITY_GOLEM_WALK, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 992 */     return MoCSoundEvents.ENTITY_GOLEM_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 997 */     return (super.getCanSpawnHere() && this.world
/* 998 */       .canBlockSeeSky(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY), 
/* 999 */           MathHelper.floor(this.posZ))) && this.posY > 50.0D);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */