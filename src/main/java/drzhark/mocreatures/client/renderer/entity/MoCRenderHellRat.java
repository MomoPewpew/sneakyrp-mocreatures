/*    */ package drzhark.mocreatures.client.renderer.entity;
/*    */ import drzhark.mocreatures.entity.monster.MoCEntityHellRat;
/*    */ import drzhark.mocreatures.entity.monster.MoCEntityRat;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class MoCRenderHellRat extends MoCRenderRat<MoCEntityHellRat> {
/*    */   public MoCRenderHellRat(ModelBase modelbase, float f) {
/* 14 */     super(modelbase, f);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void stretch(MoCEntityHellRat entityhellrat) {
/* 19 */     float f = 1.3F;
/* 20 */     GL11.glScalef(f, f, f);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(MoCEntityHellRat entityhellrat) {
/* 25 */     return entityhellrat.getTexture();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderHellRat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */