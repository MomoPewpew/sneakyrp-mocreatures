/*     */ package drzhark.mocreatures.entity.item;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityFishy;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityMediumFish;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityPiranha;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityShark;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntitySmallFish;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityKomodo;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityManticorePet;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityOstrich;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityPetScorpion;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntitySnake;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityWyvern;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCEntityEgg extends EntityLiving {
/*     */   private int tCounter;
/*     */   
/*     */   public MoCEntityEgg(World world, int type) {
/*  35 */     this(world);
/*  36 */     this.eggType = type;
/*     */   }
/*     */   private int lCounter; public int eggType;
/*     */   public MoCEntityEgg(World world) {
/*  40 */     super(world);
/*  41 */     setSize(0.25F, 0.25F);
/*  42 */     this.tCounter = 0;
/*  43 */     this.lCounter = 0;
/*     */   }
/*     */   
/*     */   public MoCEntityEgg(World world, double d, double d1, double d2) {
/*  47 */     super(world);
/*     */     
/*  49 */     setSize(0.25F, 0.25F);
/*  50 */     this.tCounter = 0;
/*  51 */     this.lCounter = 0;
/*     */   }
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  55 */     return MoCreatures.proxy.getTexture("egg.png");
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  60 */     super.applyEntityAttributes();
/*  61 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBreatheUnderwater() {
/*  66 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/*  71 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean handleWaterMovement() {
/*  76 */     if (this.world.handleMaterialAcceleration(getEntityBoundingBox(), Material.WATER, (Entity)this)) {
/*  77 */       this.inWater = true;
/*  78 */       return true;
/*     */     } 
/*  80 */     this.inWater = false;
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCollideWithPlayer(EntityPlayer entityplayer) {
/*  87 */     int i = this.eggType;
/*  88 */     if (i == 30) {
/*  89 */       i = 31;
/*     */     }
/*  91 */     if (this.lCounter > 10 && entityplayer.inventory.addItemStackToInventory(new ItemStack((Item)MoCItems.mocegg, 1, i))) {
/*  92 */       playSound(SoundEvents.ENTITY_ITEM_PICKUP, 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*  93 */       if (!this.world.isRemote) {
/*  94 */         entityplayer.onItemPickup((Entity)this, 1);
/*     */       }
/*     */       
/*  97 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 103 */     this.moveStrafing = 0.0F;
/* 104 */     this.moveForward = 0.0F;
/* 105 */     this.randomYawVelocity = 0.0F;
/* 106 */     travel(this.moveStrafing, this.moveVertical, this.moveForward);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 111 */     super.onUpdate();
/* 112 */     if (!this.world.isRemote) {
/* 113 */       if (this.rand.nextInt(20) == 0) {
/* 114 */         this.lCounter++;
/*     */       }
/*     */       
/* 117 */       if (this.lCounter > 500) {
/* 118 */         EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 119 */         if (entityplayer1 == null) {
/* 120 */           setDead();
/*     */         }
/*     */       } 
/*     */       
/* 124 */       if (isInWater() && (getEggType() < 12 || getEggType() > 69) && this.rand.nextInt(20) == 0) {
/* 125 */         this.tCounter++;
/* 126 */         if (this.tCounter % 5 == 0) {
/* 127 */           this.motionY += 0.2D;
/*     */         }
/*     */         
/* 130 */         if (this.tCounter == 5) {
/* 131 */           NotifyEggHatching();
/*     */         }
/*     */         
/* 134 */         if (this.tCounter >= 30) {
/* 135 */           if (getEggType() <= 10) {
/*     */             
/* 137 */             MoCEntityFishy entityspawn = new MoCEntityFishy(this.world);
/* 138 */             entityspawn.setPosition(this.posX, this.posY, this.posZ);
/* 139 */             entityspawn.setType(getEggType());
/* 140 */             entityspawn.setEdad(30);
/* 141 */             this.world.spawnEntity((Entity)entityspawn);
/* 142 */             EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 143 */             if (entityplayer != null) {
/* 144 */               MoCTools.tameWithName(entityplayer, (IMoCTameable)entityspawn);
/*     */             
/*     */             }
/*     */           }
/* 148 */           else if (getEggType() == 11) {
/*     */             
/* 150 */             MoCEntityShark entityspawn = new MoCEntityShark(this.world);
/* 151 */             entityspawn.setPosition(this.posX, this.posY, this.posZ);
/* 152 */             entityspawn.setEdad(30);
/* 153 */             this.world.spawnEntity((Entity)entityspawn);
/* 154 */             EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 155 */             if (entityplayer != null) {
/* 156 */               MoCTools.tameWithName(entityplayer, (IMoCTameable)entityspawn);
/*     */             
/*     */             }
/*     */           }
/* 160 */           else if (getEggType() == 90) {
/*     */             
/* 162 */             MoCEntityPiranha entityspawn = new MoCEntityPiranha(this.world);
/* 163 */             entityspawn.setPosition(this.posX, this.posY, this.posZ);
/* 164 */             this.world.spawnEntity((Entity)entityspawn);
/* 165 */             entityspawn.setEdad(30);
/* 166 */             EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 167 */             if (entityplayer != null) {
/* 168 */               MoCTools.tameWithName(entityplayer, (IMoCTameable)entityspawn);
/*     */             
/*     */             }
/*     */           }
/* 172 */           else if (getEggType() >= 80 && getEggType() < 80 + MoCEntitySmallFish.fishNames.length) {
/*     */             
/* 174 */             int type = getEggType() - 79;
/* 175 */             MoCEntitySmallFish entityspawn = MoCEntitySmallFish.createEntity(this.world, type);
/* 176 */             entityspawn.setPosition(this.posX, this.posY, this.posZ);
/* 177 */             this.world.spawnEntity((Entity)entityspawn);
/* 178 */             entityspawn.setEdad(30);
/* 179 */             EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 180 */             if (entityplayer != null) {
/* 181 */               MoCTools.tameWithName(entityplayer, (IMoCTameable)entityspawn);
/*     */             
/*     */             }
/*     */           }
/* 185 */           else if (getEggType() >= 70 && getEggType() < 70 + MoCEntityMediumFish.fishNames.length) {
/*     */             
/* 187 */             int type = getEggType() - 69;
/* 188 */             MoCEntityMediumFish entityspawn = MoCEntityMediumFish.createEntity(this.world, type);
/* 189 */             entityspawn.setPosition(this.posX, this.posY, this.posZ);
/* 190 */             this.world.spawnEntity((Entity)entityspawn);
/* 191 */             entityspawn.setEdad(30);
/* 192 */             EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 193 */             if (entityplayer != null) {
/* 194 */               MoCTools.tameWithName(entityplayer, (IMoCTameable)entityspawn);
/*     */             }
/*     */           } 
/* 197 */           MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 198 */           setDead();
/*     */         }
/*     */       
/*     */       }
/* 202 */       else if (!isInWater() && getEggType() > 20 && this.rand.nextInt(20) == 0) {
/*     */         
/* 204 */         this.tCounter++;
/*     */ 
/*     */         
/* 207 */         if (this.tCounter % 5 == 0) {
/* 208 */           this.motionY += 0.2D;
/*     */         }
/*     */         
/* 211 */         if (this.tCounter == 5) {
/* 212 */           NotifyEggHatching();
/*     */         }
/*     */         
/* 215 */         if (this.tCounter >= 30) {
/* 216 */           if (getEggType() > 20 && getEggType() < 29) {
/*     */             
/* 218 */             MoCEntitySnake entityspawn = new MoCEntitySnake(this.world);
/*     */             
/* 220 */             entityspawn.setPosition(this.posX, this.posY, this.posZ);
/* 221 */             entityspawn.setType(getEggType() - 20);
/* 222 */             entityspawn.setEdad(50);
/* 223 */             this.world.spawnEntity((Entity)entityspawn);
/* 224 */             EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 225 */             if (entityplayer != null) {
/* 226 */               MoCTools.tameWithName(entityplayer, (IMoCTameable)entityspawn);
/*     */             }
/*     */           } 
/*     */           
/* 230 */           if (getEggType() == 30 || getEggType() == 31 || getEggType() == 32) {
/*     */             
/* 232 */             MoCEntityOstrich entityspawn = new MoCEntityOstrich(this.world);
/* 233 */             int typeInt = 1;
/* 234 */             if (this.world.provider.doesWaterVaporize() || getEggType() == 32) {
/* 235 */               typeInt = 5;
/*     */             }
/* 237 */             entityspawn.setPosition(this.posX, this.posY, this.posZ);
/* 238 */             entityspawn.setType(typeInt);
/* 239 */             entityspawn.setEdad(35);
/* 240 */             this.world.spawnEntity((Entity)entityspawn);
/* 241 */             entityspawn.setHealth(entityspawn.getMaxHealth());
/*     */             
/* 243 */             if (getEggType() == 31) {
/*     */               
/* 245 */               EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 246 */               if (entityplayer != null) {
/* 247 */                 MoCTools.tameWithName(entityplayer, (IMoCTameable)entityspawn);
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 252 */           if (getEggType() == 33) {
/*     */             
/* 254 */             MoCEntityKomodo entityspawn = new MoCEntityKomodo(this.world);
/*     */             
/* 256 */             entityspawn.setPosition(this.posX, this.posY, this.posZ);
/* 257 */             entityspawn.setEdad(30);
/* 258 */             this.world.spawnEntity((Entity)entityspawn);
/* 259 */             EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 260 */             if (entityplayer != null) {
/* 261 */               MoCTools.tameWithName(entityplayer, (IMoCTameable)entityspawn);
/*     */             }
/*     */           } 
/*     */           
/* 265 */           if (getEggType() > 40 && getEggType() < 46) {
/*     */             
/* 267 */             MoCEntityPetScorpion entityspawn = new MoCEntityPetScorpion(this.world);
/* 268 */             int typeInt = getEggType() - 40;
/* 269 */             entityspawn.setPosition(this.posX, this.posY, this.posZ);
/* 270 */             entityspawn.setType(typeInt);
/* 271 */             entityspawn.setAdult(false);
/* 272 */             this.world.spawnEntity((Entity)entityspawn);
/* 273 */             entityspawn.setHealth(entityspawn.getMaxHealth());
/* 274 */             EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 275 */             if (entityplayer != null) {
/* 276 */               MoCTools.tameWithName(entityplayer, (IMoCTameable)entityspawn);
/*     */             }
/*     */           } 
/*     */           
/* 280 */           if (getEggType() > 49 && getEggType() < 62) {
/*     */             
/* 282 */             MoCEntityWyvern entityspawn = new MoCEntityWyvern(this.world);
/* 283 */             int typeInt = getEggType() - 49;
/* 284 */             entityspawn.setPosition(this.posX, this.posY, this.posZ);
/* 285 */             entityspawn.setType(typeInt);
/* 286 */             entityspawn.setAdult(false);
/* 287 */             entityspawn.setEdad(30);
/* 288 */             this.world.spawnEntity((Entity)entityspawn);
/* 289 */             entityspawn.setHealth(entityspawn.getMaxHealth());
/* 290 */             EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 291 */             if (entityplayer != null) {
/* 292 */               MoCTools.tameWithName(entityplayer, (IMoCTameable)entityspawn);
/*     */             }
/*     */           } 
/* 295 */           if (getEggType() > 61 && getEggType() < 66) {
/*     */             
/* 297 */             MoCEntityManticorePet entityspawn = new MoCEntityManticorePet(this.world);
/* 298 */             int typeInt = getEggType() - 61;
/* 299 */             entityspawn.setPosition(this.posX, this.posY, this.posZ);
/* 300 */             entityspawn.setType(typeInt);
/* 301 */             entityspawn.setAdult(false);
/* 302 */             entityspawn.setEdad(30);
/* 303 */             this.world.spawnEntity((Entity)entityspawn);
/* 304 */             entityspawn.setHealth(entityspawn.getMaxHealth());
/* 305 */             EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 306 */             if (entityplayer != null) {
/* 307 */               MoCTools.tameWithName(entityplayer, (IMoCTameable)entityspawn);
/*     */             }
/*     */           } 
/* 310 */           MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 311 */           setDead();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void NotifyEggHatching() {
/* 318 */     EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 319 */     if (entityplayer != null) {
/* 320 */       entityplayer.sendMessage((ITextComponent)new TextComponentTranslation("Egg hatching soon! KEEP WATCH! The hatched creature located @ " + (int)this.posX + ", " + (int)this.posY + ", " + (int)this.posZ + " will be lost if you leave area", new Object[0]));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 326 */     if (getEggType() == 30 || getEggType() == 31) {
/* 327 */       return 170;
/*     */     }
/* 329 */     return 100;
/*     */   }
/*     */   
/*     */   public int getEggType() {
/* 333 */     return this.eggType;
/*     */   }
/*     */   
/*     */   public void setEggType(int eggType) {
/* 337 */     this.eggType = eggType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
/* 342 */     super.readEntityFromNBT(nbttagcompound);
/* 343 */     nbttagcompound = MoCTools.getEntityData((Entity)this);
/* 344 */     setEggType(nbttagcompound.getInteger("EggType"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
/* 349 */     super.writeEntityToNBT(nbttagcompound);
/* 350 */     nbttagcompound = MoCTools.getEntityData((Entity)this);
/* 351 */     nbttagcompound.setInteger("EggType", getEggType());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntityInsideOpaqueBlock() {
/* 356 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\item\MoCEntityEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */