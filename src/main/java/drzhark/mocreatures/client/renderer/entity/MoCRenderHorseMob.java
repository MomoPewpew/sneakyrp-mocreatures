/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.client.MoCClientProxy;
/*    */ import drzhark.mocreatures.client.model.MoCModelNewHorseMob;
/*    */ import drzhark.mocreatures.entity.monster.MoCEntityHorseMob;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.entity.RenderLiving;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderHorseMob extends RenderLiving<MoCEntityHorseMob> {
/*    */   public MoCRenderHorseMob(MoCModelNewHorseMob modelbase) {
/* 16 */     super(MoCClientProxy.mc.getRenderManager(), (ModelBase)modelbase, 0.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void adjustHeight(MoCEntityHorseMob entityhorsemob, float FHeight) {
/* 21 */     GL11.glTranslatef(0.0F, FHeight, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(MoCEntityHorseMob entityhorsemob) {
/* 26 */     return entityhorsemob.getTexture();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderHorseMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */