/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ 
/*    */ import drzhark.mocreatures.client.MoCClientProxy;
/*    */ import drzhark.mocreatures.client.model.MoCModelKittyBed;
/*    */ import drzhark.mocreatures.client.model.MoCModelKittyBed2;
/*    */ import drzhark.mocreatures.entity.item.MoCEntityKittyBed;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderKittyBed extends RenderLiving<MoCEntityKittyBed> {
/*    */   public MoCModelKittyBed kittybed;
/* 20 */   public static float[][] fleeceColorTable = new float[][] { { 1.0F, 1.0F, 1.0F }, { 0.95F, 0.7F, 0.2F }, { 0.9F, 0.5F, 0.85F }, { 0.6F, 0.7F, 0.95F }, { 0.9F, 0.9F, 0.2F }, { 0.5F, 0.8F, 0.1F }, { 0.95F, 0.7F, 0.8F }, { 0.3F, 0.3F, 0.3F }, { 0.6F, 0.6F, 0.6F }, { 0.3F, 0.6F, 0.7F }, { 0.7F, 0.4F, 0.9F }, { 0.2F, 0.4F, 0.8F }, { 0.5F, 0.4F, 0.3F }, { 0.4F, 0.5F, 0.2F }, { 0.8F, 0.3F, 0.3F }, { 0.1F, 0.1F, 0.1F } };
/*    */   
/*    */   private int mycolor;
/*    */   
/*    */   public MoCRenderKittyBed(MoCModelKittyBed modelkittybed, MoCModelKittyBed2 modelkittybed2, float f) {
/* 25 */     super(MoCClientProxy.mc.getRenderManager(), (ModelBase)modelkittybed, f);
/* 26 */     this.kittybed = modelkittybed;
/* 27 */     addLayer(new LayerMoCKittyBed(this));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(MoCEntityKittyBed entitykittybed, float f) {
/* 32 */     this.mycolor = entitykittybed.getSheetColor();
/* 33 */     this.kittybed.hasMilk = entitykittybed.getHasMilk();
/* 34 */     this.kittybed.hasFood = entitykittybed.getHasFood();
/* 35 */     this.kittybed.pickedUp = entitykittybed.getPickedUp();
/* 36 */     this.kittybed.milklevel = entitykittybed.milklevel;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(MoCEntityKittyBed entitykittybed) {
/* 41 */     return entitykittybed.getTexture();
/*    */   }
/*    */   
/*    */   private class LayerMoCKittyBed
/*    */     implements LayerRenderer<MoCEntityKittyBed> {
/*    */     private final MoCRenderKittyBed mocRenderer;
/* 47 */     private final MoCModelKittyBed2 mocModel = new MoCModelKittyBed2();
/*    */     
/*    */     public LayerMoCKittyBed(MoCRenderKittyBed render) {
/* 50 */       this.mocRenderer = render;
/*    */     }
/*    */     
/*    */     public void doRenderLayer(MoCEntityKittyBed entitykittybed, float f, float f1, float f2, float f3, float f4, float f5, float f6) {
/* 54 */       float f8 = 0.35F;
/* 55 */       int j = this.mocRenderer.mycolor;
/* 56 */       GL11.glColor3f(f8 * MoCRenderKittyBed.fleeceColorTable[j][0], f8 * MoCRenderKittyBed.fleeceColorTable[j][1], f8 * MoCRenderKittyBed.fleeceColorTable[j][2]);
/* 57 */       this.mocModel.setModelAttributes(this.mocRenderer.getMainModel());
/* 58 */       this.mocModel.setLivingAnimations((EntityLivingBase)entitykittybed, f, f1, f2);
/* 59 */       this.mocModel.render((Entity)entitykittybed, f, f1, f3, f4, f5, f6);
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean shouldCombineTextures() {
/* 64 */       return true;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderKittyBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */