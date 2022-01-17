/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ 
/*    */ import drzhark.mocreatures.client.MoCClientProxy;
/*    */ import drzhark.mocreatures.client.model.MoCModelLitterBox;
/*    */ import drzhark.mocreatures.entity.item.MoCEntityLitterBox;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderLitterBox extends RenderLiving<MoCEntityLitterBox> {
/*    */   public MoCRenderLitterBox(MoCModelLitterBox modellitterbox, float f) {
/* 17 */     super(MoCClientProxy.mc.getRenderManager(), (ModelBase)modellitterbox, f);
/* 18 */     this.litterbox = modellitterbox;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(MoCEntityLitterBox entitylitterbox, float f) {
/* 23 */     this.litterbox.usedlitter = entitylitterbox.getUsedLitter();
/*    */   }
/*    */   public MoCModelLitterBox litterbox;
/*    */   
/*    */   protected ResourceLocation getEntityTexture(MoCEntityLitterBox entitylitterbox) {
/* 28 */     return entitylitterbox.getTexture();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderLitterBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */