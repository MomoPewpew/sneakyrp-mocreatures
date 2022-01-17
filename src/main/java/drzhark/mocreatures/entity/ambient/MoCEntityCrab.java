/*     */ package drzhark.mocreatures.entity.ambient;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.entity.MoCEntityTameableAmbient;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFleeFromEntityMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIFollowOwnerPlayer;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIPanicMoC;
/*     */ import drzhark.mocreatures.entity.ai.EntityAIWanderMoC2;
/*     */ import drzhark.mocreatures.init.MoCItems;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ public class MoCEntityCrab extends MoCEntityTameableAmbient {
/*     */   public MoCEntityCrab(World world) {
/*  26 */     super(world);
/*  27 */     setSize(0.3F, 0.3F);
/*  28 */     setEdad(50 + this.rand.nextInt(50));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initEntityAI() {
/*  33 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIPanicMoC((EntityCreature)this, 1.0D));
/*  34 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIFleeFromEntityMoC((EntityCreature)this, new Predicate<Entity>()
/*     */           {
/*     */             public boolean apply(Entity entity) {
/*  37 */               return (!(entity instanceof MoCEntityCrab) && (entity.height > 0.3F || entity.width > 0.3F));
/*     */             }
/*     */           },  6.0F, 0.6D, 1.0D));
/*  40 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFollowOwnerPlayer((EntityLiving)this, 0.8D, 6.0F, 5.0F));
/*  41 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWanderMoC2((EntityCreature)this, 1.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  46 */     super.applyEntityAttributes();
/*  47 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
/*  48 */     getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectType() {
/*  53 */     if (getType() == 0) {
/*  54 */       setType(this.rand.nextInt(5) + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTexture() {
/*  61 */     switch (getType()) {
/*     */       case 1:
/*  63 */         return MoCreatures.proxy.getTexture("craba.png");
/*     */       case 2:
/*  65 */         return MoCreatures.proxy.getTexture("crabb.png");
/*     */       case 3:
/*  67 */         return MoCreatures.proxy.getTexture("crabc.png");
/*     */       case 4:
/*  69 */         return MoCreatures.proxy.getTexture("crabd.png");
/*     */       case 5:
/*  71 */         return MoCreatures.proxy.getTexture("crabe.png");
/*     */     } 
/*     */     
/*  74 */     return MoCreatures.proxy.getTexture("craba.png");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float f, float f1) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/*  84 */     return (Item)MoCItems.crabraw;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnLadder() {
/*  89 */     return this.collidedHorizontally;
/*     */   }
/*     */   
/*     */   public boolean climbing() {
/*  93 */     return (!this.onGround && isOnLadder());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void jump() {}
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float getSizeFactor() {
/* 103 */     return 0.7F * getEdad() * 0.01F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBreatheUnderwater() {
/* 108 */     return true;
/*     */   }
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean isFleeing() {
/* 113 */     return (MoCTools.getMyMovementSpeed((Entity)this) > 0.09F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 121 */     return EnumCreatureAttribute.ARTHROPOD;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canBeTrappedInNet() {
/* 126 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int nameYOffset() {
/* 131 */     return -20;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isNotScared() {
/* 136 */     return getIsTamed();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\ambient\MoCEntityCrab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */