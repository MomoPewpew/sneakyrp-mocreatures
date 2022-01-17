/*    */ package drzhark.mocreatures.entity.monster;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.monster.IMob;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class MoCEntityFlameWraith
/*    */   extends MoCEntityWraith implements IMob {
/*    */   protected int burningTime;
/*    */   
/*    */   public MoCEntityFlameWraith(World world) {
/* 18 */     super(world);
/* 19 */     this.texture = "flamewraith.png";
/* 20 */     setSize(1.5F, 1.5F);
/* 21 */     this.isImmuneToFire = true;
/* 22 */     this.burningTime = 30;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 27 */     super.applyEntityAttributes();
/* 28 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Item getDropItem() {
/* 33 */     return Items.REDSTONE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onLivingUpdate() {
/* 38 */     if (!this.world.isRemote && 
/* 39 */       this.world.isDaytime()) {
/* 40 */       float f = getBrightness();
/* 41 */       if (f > 0.5F && this.world
/* 42 */         .canBlockSeeSky(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY), 
/* 43 */             MathHelper.floor(this.posZ))) && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
/* 44 */         setHealth(getHealth() - 2.0F);
/*    */       }
/*    */     } 
/*    */     
/* 48 */     super.onLivingUpdate();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void applyEnchantments(EntityLivingBase entityLivingBaseIn, Entity entityIn) {
/* 59 */     if (!this.world.isRemote && !this.world.provider.doesWaterVaporize()) {
/* 60 */       entityLivingBaseIn.setFire(this.burningTime);
/*    */     }
/* 62 */     super.applyEnchantments(entityLivingBaseIn, entityIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBurning() {
/* 67 */     return (this.rand.nextInt(100) == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isHarmedByDaylight() {
/* 72 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityFlameWraith.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */