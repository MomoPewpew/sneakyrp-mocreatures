/*    */ package drzhark.mocreatures.entity.aquatic;
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import drzhark.mocreatures.entity.ai.EntityAIFollowHerd;
/*    */ import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.EntityAIAttackMelee;
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import drzhark.mocreatures.init.MoCItems;
/*    */ import net.minecraft.world.World;
/*    */
/*    */ public class MoCEntityPiranha extends MoCEntitySmallFish {
/* 20 */   public static final String[] fishNames = new String[] { "Piranha" };
/*    */
/*    */   public MoCEntityPiranha(World world) {
/* 23 */     super(world);
/*    */   }
/*    */
/*    */
/*    */   protected void initEntityAI() {
/* 28 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
/* 29 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowHerd((EntityLiving)this, 0.6D, 4.0D, 20.0D, 1));
/* 30 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
/*    */   }
/*    */
/*    */
/*    */   protected void applyEntityAttributes() {
/* 35 */     super.applyEntityAttributes();
/* 36 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6.0D);
/* 37 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/* 38 */     getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
/*    */   }
/*    */
/*    */
/*    */   public void selectType() {
/* 43 */     setType(1);
/*    */   }
/*    */
/*    */
/*    */   public ResourceLocation getTexture() {
/* 48 */     return MoCreatures.proxy.getTexture("smallfish_piranha.png");
/*    */   }
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 63 */     if (super.attackEntityFrom(damagesource, i) && this.world.getDifficulty().getDifficultyId()  > 0) {
/* 64 */       Entity entity = damagesource.getTrueSource();
/* 65 */       if (entity instanceof EntityLivingBase) {
/* 66 */         if (isRidingOrBeingRiddenBy(entity)) {
/* 67 */           return true;
/*    */         }
/* 69 */         if (entity != this) {
/* 70 */           setAttackTarget((EntityLivingBase)entity);
/*    */         }
/* 72 */         return true;
/*    */       }
/* 74 */       return false;
/*    */     }
/* 76 */     return false;
/*    */   }
/*    */
/*    */
/*    */
/*    */   public boolean isNotScared() {
/* 82 */     return true;
/*    */   }
/*    */
/*    */
/*    */   protected void dropFewItems(boolean flag, int x) {
/* 87 */     int i = this.rand.nextInt(100);
/* 88 */     if (i < 70) {
/* 89 */       entityDropItem(new ItemStack(Items.FISH, 1, 0), 0.0F);
/*    */     } else {
/* 91 */       int j = this.rand.nextInt(2);
/* 92 */       for (int k = 0; k < j; k++)
/* 93 */         entityDropItem(new ItemStack((Item)MoCItems.mocegg, 1, 90), 0.0F);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityPiranha.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
