/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ 
/*    */ import drzhark.mocreatures.MoCreatures;
/*    */ import drzhark.mocreatures.client.MoCClientProxy;
/*    */ import drzhark.mocreatures.client.model.MoCModelWere;
/*    */ import drzhark.mocreatures.client.model.MoCModelWereHuman;
/*    */ import drzhark.mocreatures.entity.monster.MoCEntityWerewolf;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderWerewolf extends RenderLiving<MoCEntityWerewolf> {
/*    */   public MoCRenderWerewolf(MoCModelWereHuman modelwerehuman, ModelBase modelbase, float f) {
/* 21 */     super(MoCClientProxy.mc.getRenderManager(), modelbase, f);
/* 22 */     addLayer(new LayerMoCWereHuman(this));
/* 23 */     this.tempWerewolf = (MoCModelWere)modelbase;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRender(MoCEntityWerewolf entitywerewolf, double d, double d1, double d2, float f, float f1) {
/* 28 */     this.tempWerewolf.hunched = entitywerewolf.getIsHunched();
/* 29 */     super.doRender((EntityLiving)entitywerewolf, d, d1, d2, f, f1);
/*    */   }
/*    */   
/*    */   private final MoCModelWere tempWerewolf;
/*    */   
/*    */   protected ResourceLocation getEntityTexture(MoCEntityWerewolf entitywerewolf) {
/* 35 */     return entitywerewolf.getTexture();
/*    */   }
/*    */   
/*    */   private class LayerMoCWereHuman
/*    */     implements LayerRenderer<MoCEntityWerewolf> {
/*    */     private final MoCRenderWerewolf mocRenderer;
/* 41 */     private final MoCModelWereHuman mocModel = new MoCModelWereHuman();
/*    */     
/*    */     public LayerMoCWereHuman(MoCRenderWerewolf render) {
/* 44 */       this.mocRenderer = render;
/*    */     }
/*    */     
/*    */     public void doRenderLayer(MoCEntityWerewolf entity, float f, float f1, float f2, float f3, float f4, float f5, float f6) {
/* 48 */       int myType = entity.getType();
/*    */       
/* 50 */       if (!entity.getIsHumanForm()) {
/* 51 */         MoCRenderWerewolf.this.bindTexture(MoCreatures.proxy.getTexture("wereblank.png"));
/*    */       } else {
/* 53 */         switch (myType) {
/*    */           
/*    */           case 1:
/* 56 */             MoCRenderWerewolf.this.bindTexture(MoCreatures.proxy.getTexture("weredude.png"));
/*    */             break;
/*    */           case 2:
/* 59 */             MoCRenderWerewolf.this.bindTexture(MoCreatures.proxy.getTexture("werehuman.png"));
/*    */             break;
/*    */           case 3:
/* 62 */             MoCRenderWerewolf.this.bindTexture(MoCreatures.proxy.getTexture("wereoldie.png"));
/*    */             break;
/*    */           case 4:
/* 65 */             MoCRenderWerewolf.this.bindTexture(MoCreatures.proxy.getTexture("werewoman.png"));
/*    */             break;
/*    */           default:
/* 68 */             MoCRenderWerewolf.this.bindTexture(MoCreatures.proxy.getTexture("wereoldie.png"));
/*    */             break;
/*    */         } 
/*    */       
/*    */       } 
/* 73 */       this.mocModel.setModelAttributes(this.mocRenderer.getMainModel());
/* 74 */       this.mocModel.setLivingAnimations((EntityLivingBase)entity, f, f1, f2);
/* 75 */       this.mocModel.render((Entity)entity, f, f1, f3, f4, f5, f6);
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean shouldCombineTextures() {
/* 80 */       return true;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderWerewolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */