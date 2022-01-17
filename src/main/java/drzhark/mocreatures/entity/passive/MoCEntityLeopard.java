/*     */ package drzhark.mocreatures.entity.passive;
/*     */ 
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraftforge.common.BiomeDictionary;
/*     */ 
/*     */ public class MoCEntityLeopard
/*     */   extends MoCEntityBigCat
/*     */ {
/*     */   public MoCEntityLeopard(World world) {
/*  21 */     super(world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  27 */     if (getType() == 0) {
/*  28 */       checkSpawningBiome();
/*     */     }
/*  30 */     super.selectType();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkSpawningBiome() {
/*  35 */     int i = MathHelper.floor(this.posX);
/*  36 */     int j = MathHelper.floor((getEntityBoundingBox()).minY);
/*  37 */     int k = MathHelper.floor(this.posZ);
/*  38 */     BlockPos pos = new BlockPos(i, j, k);
/*     */     
/*  40 */     Biome currentbiome = MoCTools.Biomekind(this.world, pos);
/*     */     try {
/*  42 */       if (BiomeDictionary.hasType(currentbiome, BiomeDictionary.Type.SNOWY)) {
/*  43 */         setType(2);
/*  44 */         return true;
/*     */       } 
/*  46 */     } catch (Exception exception) {}
/*     */     
/*  48 */     setType(1);
/*  49 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  54 */     switch (getType()) {
/*     */       case 1:
/*  56 */         return MoCreatures.proxy.getTexture("bcleopard.png");
/*     */       case 2:
/*  58 */         return MoCreatures.proxy.getTexture("bcsnowleopard.png");
/*     */     } 
/*  60 */     return MoCreatures.proxy.getTexture("bcleopard.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean processInteract(EntityPlayer player, EnumHand hand) {
/*  66 */     Boolean tameResult = processTameInteract(player, hand);
/*  67 */     if (tameResult != null) {
/*  68 */       return tameResult.booleanValue();
/*     */     }
/*     */     
/*  71 */     if (getIsRideable() && getIsAdult() && (!getIsChested() || !player.isSneaking()) && !isBeingRidden()) {
/*  72 */       if (!this.world.isRemote && player.startRiding((Entity)this)) {
/*  73 */         player.rotationYaw = this.rotationYaw;
/*  74 */         player.rotationPitch = this.rotationPitch;
/*  75 */         setSitting(false);
/*     */       } 
/*     */       
/*  78 */       return true;
/*     */     } 
/*     */     
/*  81 */     return super.processInteract(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOffspringClazz(IMoCTameable mate) {
/*  86 */     if (mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate).getType() == 1) {
/*  87 */       return "Pantard";
/*     */     }
/*  89 */     if (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() == 1) {
/*  90 */       return "Leoger";
/*     */     }
/*  92 */     if (mate instanceof MoCEntityLion && ((MoCEntityLion)mate).getType() == 2) {
/*  93 */       return "Liard";
/*     */     }
/*  95 */     return "Leopard";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOffspringTypeInt(IMoCTameable mate) {
/* 100 */     if (mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate).getType() == 1) {
/* 101 */       return 1;
/*     */     }
/* 103 */     if (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate).getType() == 1) {
/* 104 */       return 1;
/*     */     }
/* 106 */     if (mate instanceof MoCEntityLion && ((MoCEntityLion)mate).getType() == 2) {
/* 107 */       return 1;
/*     */     }
/* 109 */     return getType();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compatibleMate(Entity mate) {
/* 114 */     return ((mate instanceof MoCEntityLeopard && ((MoCEntityLeopard)mate).getType() == getType()) || (mate instanceof MoCEntityPanther && ((MoCEntityPanther)mate)
/* 115 */       .getType() == 1) || (mate instanceof MoCEntityTiger && ((MoCEntityTiger)mate)
/* 116 */       .getType() == 1) || (mate instanceof MoCEntityLion && ((MoCEntityLion)mate)
/* 117 */       .getType() == 2));
/*     */   }
/*     */ 
/*     */   
/*     */   public float calculateMaxHealth() {
/* 122 */     return 25.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public double calculateAttackDmg() {
/* 127 */     return 5.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAttackRange() {
/* 132 */     return 6.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxEdad() {
/* 137 */     return 95;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canAttackTarget(EntityLivingBase entity) {
/* 142 */     if (!getIsAdult() && getEdad() < getMaxEdad() * 0.8D) {
/* 143 */       return false;
/*     */     }
/* 145 */     if (entity instanceof MoCEntityLeopard) {
/* 146 */       return false;
/*     */     }
/* 148 */     return (entity.height < 1.3F && entity.width < 1.3F);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMoveSpeed() {
/* 153 */     return 1.6F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\passive\MoCEntityLeopard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */