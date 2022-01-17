/*     */ package drzhark.mocreatures.entity.passive;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityAnimal;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCSoundEvents;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockGrass;
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
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.Path;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.util.FakePlayerFactory;
/*     */ import net.minecraftforge.event.world.BlockEvent;
/*     */ 
/*     */ public class MoCEntityEnt extends MoCEntityAnimal {
/*     */   public MoCEntityEnt(World world) {
/*  39 */     super(world);
/*  40 */     setSize(1.4F, 7.0F);
/*  41 */     this.stepHeight = 2.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  46 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  47 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/*  48 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*  49 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  54 */     super.applyEntityAttributes();
/*  55 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
/*  56 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/*  57 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
/*  58 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  63 */     if (getType() == 0) {
/*  64 */       setType(this.rand.nextInt(2) + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  70 */     switch (getType()) {
/*     */       case 1:
/*  72 */         return MoCreatures.proxy.getTexture("ent_oak.png");
/*     */       case 2:
/*  74 */         return MoCreatures.proxy.getTexture("ent_birch.png");
/*     */     } 
/*  76 */     return MoCreatures.proxy.getTexture("ent_oak.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/*  82 */     if (damagesource.getTrueSource() != null && damagesource.getTrueSource() instanceof EntityPlayer) {
/*  83 */       EntityPlayer ep = (EntityPlayer)damagesource.getTrueSource();
/*  84 */       ItemStack currentItem = ep.inventory.getCurrentItem();
/*  85 */       if (currentItem != null) {
/*  86 */         Item itemheld = currentItem.getItem();
/*  87 */         if (itemheld != null && itemheld instanceof net.minecraft.item.ItemAxe) {
/*  88 */           this.world.getDifficulty();
/*  89 */           if (shouldAttackPlayers()) {
/*  90 */             setAttackTarget((EntityLivingBase)ep);
/*     */           }
/*     */           
/*  93 */           return super.attackEntityFrom(damagesource, i);
/*     */         } 
/*     */       } 
/*     */     } 
/*  97 */     if (damagesource.isFireDamage()) {
/*  98 */       return super.attackEntityFrom(damagesource, i);
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean flag, int x) {
/* 105 */     int i = this.rand.nextInt(3);
/* 106 */     int qty = this.rand.nextInt(12) + 4;
/* 107 */     int typ = 0;
/* 108 */     if (getType() == 2) {
/* 109 */       typ = 2;
/*     */     }
/* 111 */     if (i == 0) {
/* 112 */       entityDropItem(new ItemStack(Blocks.LOG, qty, typ), 0.0F);
/*     */       return;
/*     */     } 
/* 115 */     if (i == 1) {
/* 116 */       entityDropItem(new ItemStack(Items.STICK, qty, 0), 0.0F);
/*     */       
/*     */       return;
/*     */     } 
/* 120 */     entityDropItem(new ItemStack(Blocks.SAPLING, qty, typ), 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getDeathSound() {
/* 125 */     return MoCSoundEvents.ENTITY_ENT_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getHurtSound(DamageSource source) {
/* 130 */     return MoCSoundEvents.ENTITY_ENT_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent getAmbientSound() {
/* 135 */     return MoCSoundEvents.ENTITY_ENT_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 140 */     super.onLivingUpdate();
/* 141 */     if (!this.world.isRemote) {
/*     */       
/* 143 */       if (getAttackTarget() == null && this.rand.nextInt(500) == 0) {
/* 144 */         plantOnFertileGround();
/*     */       }
/*     */       
/* 147 */       if (this.rand.nextInt(100) == 0) {
/* 148 */         atractCritter();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void atractCritter() {
/* 157 */     List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(8.0D, 3.0D, 8.0D));
/* 158 */     int n = this.rand.nextInt(3) + 1;
/* 159 */     int j = 0;
/* 160 */     for (int k = 0; k < list.size(); k++) {
/* 161 */       Entity entity = list.get(k);
/* 162 */       if (entity instanceof EntityAnimal && entity.width < 0.6F && entity.height < 0.6F) {
/* 163 */         EntityAnimal entityanimal = (EntityAnimal)entity;
/* 164 */         if (entityanimal.getAttackTarget() == null && !MoCTools.isTamed((Entity)entityanimal)) {
/* 165 */           Path pathentity = entityanimal.getNavigator().getPathToEntityLiving((Entity)this);
/* 166 */           entityanimal.setAttackTarget((EntityLivingBase)this);
/* 167 */           entityanimal.getNavigator().setPath(pathentity, 1.0D);
/* 168 */           j++;
/* 169 */           if (j > n) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean plantOnFertileGround() {
/* 179 */     BlockPos pos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY), MathHelper.floor(this.posZ));
/* 180 */     Block blockUnderFeet = this.world.getBlockState(pos.down()).getBlock();
/* 181 */     Block blockOnFeet = this.world.getBlockState(pos).getBlock();
/*     */     
/* 183 */     if (blockUnderFeet == Blocks.DIRT) {
/* 184 */       BlockGrass blockGrass = Blocks.GRASS;
/* 185 */       BlockEvent.BreakEvent event = null;
/* 186 */       if (!this.world.isRemote)
/*     */       {
/* 188 */         event = new BlockEvent.BreakEvent(this.world, pos, blockGrass.getDefaultState(), (EntityPlayer)FakePlayerFactory.get((WorldServer)this.world, MoCreatures.MOCFAKEPLAYER));
/*     */       }
/*     */       
/* 191 */       if (event != null && !event.isCanceled()) {
/* 192 */         this.world.setBlockState(pos.down(), blockGrass.getDefaultState(), 3);
/* 193 */         return true;
/*     */       } 
/* 195 */       return false;
/*     */     } 
/*     */     
/* 198 */     if (blockUnderFeet == Blocks.GRASS && blockOnFeet == Blocks.AIR) {
/* 199 */       IBlockState iblockstate = getBlockStateToBePlanted();
/* 200 */       int plantChance = 3;
/* 201 */       if (iblockstate.getBlock() == Blocks.SAPLING) {
/* 202 */         plantChance = 10;
/*     */       }
/*     */ 
/*     */       
/* 206 */       for (int x = -1; x < 2; x++) {
/* 207 */         for (int z = -1; z < 2; z++) {
/* 208 */           BlockPos pos1 = new BlockPos(MathHelper.floor(this.posX + x), MathHelper.floor(this.posY), MathHelper.floor(this.posZ + z));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 216 */           Block blockToPlant = this.world.getBlockState(pos1).getBlock();
/*     */           
/* 218 */           if (this.rand.nextInt(plantChance) == 0 && blockToPlant == Blocks.AIR) {
/* 219 */             this.world.setBlockState(pos1, iblockstate, 3);
/*     */           }
/*     */         } 
/*     */       } 
/* 223 */       return true;
/*     */     } 
/*     */     
/* 226 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private IBlockState getBlockStateToBePlanted() {
/* 235 */     int blockID = 0;
/* 236 */     int metaData = 0;
/* 237 */     switch (this.rand.nextInt(20))
/*     */     { case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/* 246 */         blockID = 31;
/* 247 */         metaData = this.rand.nextInt(2) + 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 283 */         iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData);
/* 284 */         return iblockstate;case 8: case 9: case 10: blockID = 175; metaData = this.rand.nextInt(6); iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData); return iblockstate;case 11: case 12: case 13: blockID = 37; iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData); return iblockstate;case 14: case 15: case 16: blockID = 38; metaData = this.rand.nextInt(9); iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData); return iblockstate;case 17: blockID = 39; iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData); return iblockstate;case 18: blockID = 40; iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData); return iblockstate;case 19: blockID = 6; if (getType() == 2) metaData = 2;  iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData); return iblockstate; }  blockID = 31; IBlockState iblockstate = Block.getBlockById(blockID).getStateFromMeta(metaData); return iblockstate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBePushed() {
/* 290 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
/* 306 */     MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOAT_SMACK);
/* 307 */     MoCTools.bigsmack((Entity)this, entityIn, 1.0F);
/* 308 */     super.applyEnchantments(entityLivingBaseIn, entityIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/* 313 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/* 318 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityEnt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */