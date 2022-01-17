/*    */ package drzhark.mocreatures.entity.aquatic;
/*    */ 
/*    */ import drzhark.mocreatures.MoCTools;
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import drzhark.mocreatures.network.MoCMessageHandler;
/*    */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ 
/*    */ public class MoCEntityStingRay extends MoCEntityRay {
/*    */   private int poisoncounter;
/*    */   private int tailCounter;
/*    */   
/*    */   public MoCEntityStingRay(World world) {
/* 21 */     super(world);
/* 22 */     setSize(1.2F, 0.5F);
/* 23 */     setEdad(50 + this.rand.nextInt(40));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 28 */     super.applyEntityAttributes();
/* 29 */     getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation getTexture() {
/* 34 */     return MoCreatures.proxy.getTexture("stingray.png");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPoisoning() {
/* 39 */     return (this.tailCounter != 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onLivingUpdate() {
/* 44 */     super.onLivingUpdate();
/* 45 */     if (!this.world.isRemote) {
/* 46 */       if (!getIsTamed() && ++this.poisoncounter > 250 && this.world.getDifficulty().getId() > 0 && this.rand.nextInt(30) == 0 && 
/* 47 */         MoCTools.findNearPlayerAndPoison((Entity)this, true)) {
/* 48 */         MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
/* 49 */               .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
/* 50 */         this.poisoncounter = 0;
/*    */       
/*    */       }
/*    */     
/*    */     }
/* 55 */     else if (this.tailCounter > 0 && ++this.tailCounter > 50) {
/* 56 */       this.tailCounter = 0;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void performAnimation(int animationType) {
/* 63 */     if (animationType == 1)
/*    */     {
/* 65 */       this.tailCounter = 1;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean attackEntityFrom(DamageSource damagesource, float i) {
/* 71 */     if (super.attackEntityFrom(damagesource, i)) {
/* 72 */       if (this.world.getDifficulty().getId() == 0) {
/* 73 */         return true;
/*    */       }
/* 75 */       Entity entity = damagesource.getTrueSource();
/* 76 */       if (entity instanceof EntityLivingBase) {
/* 77 */         if (entity != this) {
/* 78 */           setAttackTarget((EntityLivingBase)entity);
/*    */         }
/* 80 */         return true;
/*    */       } 
/* 82 */       return false;
/*    */     } 
/* 84 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\aquatic\MoCEntityStingRay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */