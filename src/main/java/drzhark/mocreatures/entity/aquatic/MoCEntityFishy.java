/*     */ package drzhark.mocreatures.entity.aquatic;
/*     */ import com.google.common.base.Predicate;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAquatic;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageHeart;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ 
/*     */ public class MoCEntityFishy extends MoCEntityTameableAquatic {
/*  33 */   public static final String[] fishNames = new String[] { "Blue", "Orange", "Cyan", "Greeny", "Green", "Purple", "Yellow", "Striped", "Yellowy", "Red" }; public int gestationtime;
/*  34 */   private static final DataParameter<Boolean> HAS_EATEN = EntityDataManager.createKey(MoCEntityFishy.class, DataSerializers.BOOLEAN);
/*     */   
/*     */   public MoCEntityFishy(World world) {
/*  37 */     super(world);
/*  38 */     setSize(0.3F, 0.3F);
/*  39 */     setEdad(50 + this.rand.nextInt(50));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  44 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.3D));
/*  45 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeFromEntityMoC((EntityCreature)this, new Predicate<Entity>()
/*     */           {
/*     */             public boolean apply(Entity entity) {
/*  48 */               return (entity.height > 0.3F || entity.width > 0.3F);
/*     */             }
/*     */           },  2.0F, 0.6D, 1.5D));
/*  51 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D, 80));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  56 */     super.applyEntityAttributes();
/*  57 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
/*  58 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  63 */     if (getType() == 0) {
/*  64 */       setType(this.rand.nextInt(fishNames.length) + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  70 */     switch (getType()) {
/*     */       case 1:
/*  72 */         return MoCreatures.proxy.getTexture("fishy1.png");
/*     */       case 2:
/*  74 */         return MoCreatures.proxy.getTexture("fishy2.png");
/*     */       case 3:
/*  76 */         return MoCreatures.proxy.getTexture("fishy3.png");
/*     */       case 4:
/*  78 */         return MoCreatures.proxy.getTexture("fishy4.png");
/*     */       case 5:
/*  80 */         return MoCreatures.proxy.getTexture("fishy5.png");
/*     */       case 6:
/*  82 */         return MoCreatures.proxy.getTexture("fishy6.png");
/*     */       case 7:
/*  84 */         return MoCreatures.proxy.getTexture("fishy7.png");
/*     */       case 8:
/*  86 */         return MoCreatures.proxy.getTexture("fishy8.png");
/*     */       case 9:
/*  88 */         return MoCreatures.proxy.getTexture("fishy9.png");
/*     */       case 10:
/*  90 */         return MoCreatures.proxy.getTexture("fishy10.png");
/*     */     } 
/*  92 */     return MoCreatures.proxy.getTexture("fishy1.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  98 */     super.entityInit();
/*  99 */     this.dataManager.register(HAS_EATEN, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public boolean getHasEaten() {
/* 103 */     return ((Boolean)this.dataManager.get(HAS_EATEN)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setHasEaten(boolean flag) {
/* 107 */     this.dataManager.set(HAS_EATEN, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean flag, int x) {
/* 112 */     int i = this.rand.nextInt(100);
/* 113 */     if (i < 70) {
/* 114 */       entityDropItem(new ItemStack(Items.FISH, 1, 0), 0.0F);
/*     */     } else {
/* 116 */       int j = this.rand.nextInt(2);
/* 117 */       for (int k = 0; k < j; k++) {
/* 118 */         entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, getType()), 0.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 126 */     super.onLivingUpdate();
/*     */     
/* 128 */     if (!isInsideOfMaterial(Material.WATER)) {
/* 129 */       this.prevRenderYawOffset = this.renderYawOffset = this.rotationYaw = this.prevRotationYaw;
/* 130 */       this.rotationPitch = this.prevRotationPitch;
/*     */     } 
/*     */     
/* 133 */     if (!this.world.isRemote) {
/*     */       
/* 135 */       if (getIsTamed() && this.rand.nextInt(100) == 0 && getHealth() < getMaxHealth()) {
/* 136 */         setHealth(getMaxHealth());
/*     */       }
/*     */       
/* 139 */       if (!ReadyforParenting(this)) {
/*     */         return;
/*     */       }
/* 142 */       int i = 0;
/* 143 */       List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 3.0D, 4.0D));
/* 144 */       for (int j = 0; j < list.size(); j++) {
/* 145 */         Entity entity = list.get(j);
/* 146 */         if (entity instanceof MoCEntityFishy) {
/* 147 */           i++;
/*     */         }
/*     */       } 
/*     */       
/* 151 */       if (i > 1) {
/*     */         return;
/*     */       }
/* 154 */       List<Entity> list1 = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D));
/* 155 */       for (int k = 0; k < list.size(); k++) {
/* 156 */         Entity entity1 = list1.get(k);
/* 157 */         if (entity1 instanceof MoCEntityFishy && entity1 != this) {
/*     */ 
/*     */           
/* 160 */           MoCEntityFishy entityfishy = (MoCEntityFishy)entity1;
/* 161 */           if (ReadyforParenting(this) && ReadyforParenting(entityfishy) && getType() == entityfishy.getType()) {
/*     */ 
/*     */             
/* 164 */             if (this.rand.nextInt(100) == 0) {
/* 165 */               this.gestationtime++;
/*     */             }
/* 167 */             if (this.gestationtime % 3 == 0) {
/* 168 */               MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageHeart(getEntityId()), new NetworkRegistry.TargetPoint(this.world.provider
/* 169 */                     .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/*     */             }
/* 171 */             if (this.gestationtime > 50) {
/*     */ 
/*     */               
/* 174 */               int l = this.rand.nextInt(3) + 1;
/* 175 */               for (int i1 = 0; i1 < l; i1++) {
/* 176 */                 MoCEntityFishy entityfishy1 = new MoCEntityFishy(this.world);
/* 177 */                 entityfishy1.setPosition(this.posX, this.posY, this.posZ);
/* 178 */                 this.world.spawnEntity((Entity)entityfishy1);
/* 179 */                 MoCTools.playCustomSound((Entity)this, SoundEvents.ENTITY_CHICKEN_EGG);
/* 180 */                 setHasEaten(false);
/* 181 */                 entityfishy.setHasEaten(false);
/* 182 */                 this.gestationtime = 0;
/* 183 */                 entityfishy.gestationtime = 0;
/*     */                 
/* 185 */                 EntityPlayer entityplayer = this.world.getClosestPlayerToEntity((Entity)this, 24.0D);
/* 186 */                 if (entityplayer != null) {
/* 187 */                   MoCTools.tameWithName(entityplayer, (IMoCTameable)entityfishy1);
/*     */                 }
/*     */                 
/* 190 */                 entityfishy1.setEdad(20);
/* 191 */                 entityfishy1.setAdult(false);
/* 192 */                 entityfishy1.setTypeInt(getType());
/*     */               } 
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public boolean ReadyforParenting(MoCEntityFishy entityfishy) {
/* 202 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canBeTrappedInNet() {
/* 207 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int nameYOffset() {
/* 212 */     return -25;
/*     */   }
/*     */ 
/*     */   
/*     */   public float rollRotationOffset() {
/* 217 */     if (!isInsideOfMaterial(Material.WATER)) {
/* 218 */       return -90.0F;
/*     */     }
/* 220 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isFisheable() {
/* 225 */     return !getIsTamed();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean usesNewAI() {
/* 230 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAIMoveSpeed() {
/* 235 */     return 0.1F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMovementCeased() {
/* 240 */     return !isInWater();
/*     */   }
/*     */ 
/*     */   
/*     */   protected double minDivingDepth() {
/* 245 */     return 0.2D;
/*     */   }
/*     */ 
/*     */   
/*     */   protected double maxDivingDepth() {
/* 250 */     return 2.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSizeFactor() {
/* 255 */     return getEdad() * 0.01F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAdjustedZOffset() {
/* 260 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAdjustedXOffset() {
/* 265 */     if (!isInWater()) {
/* 266 */       return -0.1F;
/*     */     }
/* 268 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getAdjustedYOffset() {
/* 273 */     if (!isInsideOfMaterial(Material.WATER)) {
/* 274 */       return 0.2F;
/*     */     }
/* 276 */     return -0.5F;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityFishy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */