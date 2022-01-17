/*     */ package drzhark.mocreatures.item;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityAnimal;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityBear;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityBigCat;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityElephant;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityHorse;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityKitty;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityOstrich;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityPetScorpion;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityWyvern;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MoCItemWhip
/*     */   extends MoCItem {
/*     */   public MoCItemWhip(String name) {
/*  33 */     super(name);
/*  34 */     this.maxStackSize = 1;
/*  35 */     setMaxDamage(24);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFull3D() {
/*  40 */     return true;
/*     */   }
/*     */   
/*     */   public ItemStack onItemRightClick2(ItemStack itemstack, World world, EntityPlayer entityplayer) {
/*  44 */     return itemstack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  50 */     ItemStack stack = player.getHeldItem(hand);
/*  51 */     int i1 = 0;
/*  52 */     Block block = worldIn.getBlockState(pos).getBlock();
/*  53 */     Block block1 = worldIn.getBlockState(pos.up()).getBlock();
/*  54 */     if (side != EnumFacing.DOWN && block1 == Blocks.AIR && block != Blocks.AIR && block != Blocks.STANDING_SIGN) {
/*  55 */       whipFX(worldIn, pos);
/*  56 */       worldIn.playSound(player, pos, MoCSoundEvents.ENTITY_GENERIC_WHIP, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
/*  57 */       stack.damageItem(1, (EntityLivingBase)player);
/*  58 */       List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)player, player.getEntityBoundingBox().expand(12.0D, 12.0D, 12.0D));
/*  59 */       for (int l1 = 0; l1 < list.size(); l1++) {
/*  60 */         Entity entity = list.get(l1);
/*     */         
/*  62 */         if (entity instanceof MoCEntityAnimal) {
/*  63 */           MoCEntityAnimal animal = (MoCEntityAnimal)entity;
/*  64 */           if (MoCreatures.proxy.enableOwnership && animal.getOwnerId() != null && 
/*  65 */             !player.getName().equals(animal.getOwnerId()) && !MoCTools.isThisPlayerAnOP(player)) {
/*     */             continue;
/*     */           }
/*     */         } 
/*     */         
/*  70 */         if (entity instanceof MoCEntityBigCat) {
/*  71 */           MoCEntityBigCat entitybigcat = (MoCEntityBigCat)entity;
/*  72 */           if (entitybigcat.getIsTamed()) {
/*  73 */             entitybigcat.setSitting(!entitybigcat.getIsSitting());
/*  74 */             i1++;
/*  75 */           } else if (worldIn.getDifficulty().getId() > 0 && entitybigcat.getIsAdult()) {
/*  76 */             entitybigcat.setAttackTarget((EntityLivingBase)player);
/*     */           } 
/*     */         } 
/*  79 */         if (entity instanceof MoCEntityHorse) {
/*  80 */           MoCEntityHorse entityhorse = (MoCEntityHorse)entity;
/*  81 */           if (entityhorse.getIsTamed()) {
/*  82 */             if (entityhorse.getRidingEntity() == null) {
/*  83 */               entityhorse.setSitting(!entityhorse.getIsSitting());
/*  84 */             } else if (entityhorse.isNightmare()) {
/*  85 */               entityhorse.setNightmareInt(100);
/*  86 */             } else if (entityhorse.sprintCounter == 0) {
/*  87 */               entityhorse.sprintCounter = 1;
/*     */             } 
/*     */           }
/*     */         } 
/*     */         
/*  92 */         if (entity instanceof MoCEntityKitty) {
/*  93 */           MoCEntityKitty entitykitty = (MoCEntityKitty)entity;
/*  94 */           if (entitykitty.getKittyState() > 2 && entitykitty.whipeable()) {
/*  95 */             entitykitty.setSitting(!entitykitty.getIsSitting());
/*     */           }
/*     */         } 
/*     */         
/*  99 */         if (entity instanceof MoCEntityWyvern) {
/* 100 */           MoCEntityWyvern entitywyvern = (MoCEntityWyvern)entity;
/* 101 */           if (entitywyvern.getIsTamed() && entitywyvern.getRidingEntity() == null && !entitywyvern.isOnAir()) {
/* 102 */             entitywyvern.setSitting(!entitywyvern.getIsSitting());
/*     */           }
/*     */         } 
/*     */         
/* 106 */         if (entity instanceof MoCEntityPetScorpion) {
/* 107 */           MoCEntityPetScorpion petscorpion = (MoCEntityPetScorpion)entity;
/* 108 */           if (petscorpion.getIsTamed() && petscorpion.getRidingEntity() == null) {
/* 109 */             petscorpion.setSitting(!petscorpion.getIsSitting());
/*     */           }
/*     */         } 
/*     */         
/* 113 */         if (entity instanceof MoCEntityOstrich) {
/* 114 */           MoCEntityOstrich entityostrich = (MoCEntityOstrich)entity;
/*     */ 
/*     */           
/* 117 */           if (entityostrich.isBeingRidden() && entityostrich.sprintCounter == 0) {
/* 118 */             entityostrich.sprintCounter = 1;
/*     */           }
/*     */ 
/*     */           
/* 122 */           if (entityostrich.getIsTamed() && entityostrich.getRidingEntity() == null) {
/* 123 */             entityostrich.setHiding(!entityostrich.getHiding());
/*     */           }
/*     */         } 
/* 126 */         if (entity instanceof MoCEntityElephant) {
/* 127 */           MoCEntityElephant entityelephant = (MoCEntityElephant)entity;
/*     */ 
/*     */           
/* 130 */           if (entityelephant.isBeingRidden() && entityelephant.sprintCounter == 0) {
/* 131 */             entityelephant.sprintCounter = 1;
/*     */           }
/*     */         } 
/*     */         
/* 135 */         if (entity instanceof MoCEntityBear) {
/* 136 */           MoCEntityBear entitybear = (MoCEntityBear)entity;
/*     */           
/* 138 */           if (entitybear.getIsTamed()) {
/* 139 */             if (entitybear.getBearState() == 0) {
/* 140 */               entitybear.setBearState(2);
/*     */             } else {
/* 142 */               entitybear.setBearState(0);
/*     */             } 
/*     */           }
/*     */         } 
/*     */         continue;
/*     */       } 
/* 148 */       if (i1 > 6);
/*     */ 
/*     */       
/* 151 */       return EnumActionResult.SUCCESS;
/*     */     } 
/* 153 */     return EnumActionResult.FAIL;
/*     */   }
/*     */   
/*     */   public void whipFX(World world, BlockPos pos) {
/* 157 */     double d = (pos.getX() + 0.5F);
/* 158 */     double d1 = (pos.getY() + 1.0F);
/* 159 */     double d2 = (pos.getZ() + 0.5F);
/* 160 */     double d3 = 0.2199999988079071D;
/* 161 */     double d4 = 0.27000001072883606D;
/* 162 */     world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D, new int[0]);
/* 163 */     world.spawnParticle(EnumParticleTypes.FLAME, d - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D, new int[0]);
/* 164 */     world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D, new int[0]);
/* 165 */     world.spawnParticle(EnumParticleTypes.FLAME, d + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D, new int[0]);
/* 166 */     world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D, new int[0]);
/* 167 */     world.spawnParticle(EnumParticleTypes.FLAME, d, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D, new int[0]);
/* 168 */     world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
/* 169 */     world.spawnParticle(EnumParticleTypes.FLAME, d, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
/* 170 */     world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
/* 171 */     world.spawnParticle(EnumParticleTypes.FLAME, d, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemWhip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */