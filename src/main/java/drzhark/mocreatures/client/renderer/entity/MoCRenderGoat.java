/*     */ package drzhark.mocreatures.client.renderer.entity;
/*     */ import drzhark.mocreatures.client.MoCClientProxy;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.client.model.MoCModelGoat;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityGoat;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import org.lwjgl.opengl.GL11;
/*     */
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCRenderGoat extends RenderLiving<MoCEntityGoat> {
/*     */   private final MoCModelGoat tempGoat;
/*     */
/*     */   protected ResourceLocation getEntityTexture(MoCEntityGoat entitygoat) {
/*  22 */     return entitygoat.getTexture();
/*     */   }
/*     */   float depth;
/*     */   public MoCRenderGoat(ModelBase modelbase, float f) {
/*  26 */     super(MoCClientProxy.mc.getRenderManager(), modelbase, f);
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/* 124 */     this.depth = 0.0F;
/*     */     this.tempGoat = (MoCModelGoat)modelbase;
/*     */   }
/*     */
/*     */   protected void preRenderCallback(MoCEntityGoat entitygoat, float f) {
/*     */     GL11.glTranslatef(0.0F, this.depth, 0.0F);
/*     */     stretch(entitygoat);
/*     */   }
/*     */
/*     */   public void doRender(MoCEntityGoat entitygoat, double d, double d1, double d2, float f, float f1) {
/*     */     this.tempGoat.typeInt = entitygoat.getType();
/*     */     this.tempGoat.edad = entitygoat.getEdad() * 0.01F;
/*     */     this.tempGoat.bleat = entitygoat.getBleating();
/*     */     this.tempGoat.attacking = entitygoat.getAttacking();
/*     */     this.tempGoat.legMov = entitygoat.legMovement();
/*     */     this.tempGoat.earMov = entitygoat.earMovement();
/*     */     this.tempGoat.tailMov = entitygoat.tailMovement();
/*     */     this.tempGoat.eatMov = entitygoat.mouthMovement();
/*     */     super.doRender(entitygoat, d, d1, d2, f, f1);
/*     */     boolean flag = (MoCreatures.proxy.getDisplayPetName() && !entitygoat.getPetName().isEmpty());
/*     */     boolean flag1 = MoCreatures.proxy.getDisplayPetHealth();
/*     */     if (entitygoat.renderName()) {
/*     */       float f2 = 1.6F;
/*     */       float f3 = 0.01666667F * f2;
/*     */       float f4 = entitygoat.getDistance(this.renderManager.renderViewEntity);
/*     */       if (f4 < 16.0F) {
/*     */         String s = "";
/*     */         s = s + entitygoat.getPetName();
/*     */         float f5 = 0.1F;
/*     */         FontRenderer fontrenderer = getFontRendererFromRenderManager();
/*     */         GL11.glPushMatrix();
/*     */         GL11.glTranslatef((float)d + 0.0F, (float)d1 + f5, (float)d2);
/*     */         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/*     */         GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*     */         GL11.glScalef(-f3, -f3, f3);
/*     */         GL11.glDisable(2896);
/*     */         Tessellator tessellator = Tessellator.getInstance();
/*     */         byte byte0 = (byte)(int)(-15.0F + (-40 * entitygoat.getEdad()) * 0.01F);
/*     */         if (flag1) {
/*     */           GL11.glDisable(3553);
/*     */           if (!flag)
/*     */             byte0 = (byte)(byte0 + 8);
/*     */           tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
/*     */           float f6 = entitygoat.getHealth();
/*     */           float f7 = entitygoat.getMaxHealth();
/*     */           float f8 = f6 / f7;
/*     */           float f9 = 40.0F * f8;
/*     */           tessellator.getBuffer().pos((-20.0F + f9), (-10 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*     */           tessellator.getBuffer().pos((-20.0F + f9), (-6 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*     */           tessellator.getBuffer().pos(20.0D, (-6 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*     */           tessellator.getBuffer().pos(20.0D, (-10 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*     */           tessellator.getBuffer().pos(-20.0D, (-10 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*     */           tessellator.getBuffer().pos(-20.0D, (-6 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*     */           tessellator.getBuffer().pos((f9 - 20.0F), (-6 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*     */           tessellator.getBuffer().pos((f9 - 20.0F), (-10 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*     */           tessellator.draw();
/*     */           GL11.glEnable(3553);
/*     */         }
/*     */         if (flag) {
/*     */           GL11.glDepthMask(false);
/*     */           GL11.glDisable(2929);
/*     */           GL11.glEnable(3042);
/*     */           GL11.glBlendFunc(770, 771);
/*     */           GL11.glDisable(3553);
/*     */           tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
/*     */           int i = fontrenderer.getStringWidth(s) / 2;
/*     */           tessellator.getBuffer().pos((-i - 1), (-1 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*     */           tessellator.getBuffer().pos((-i - 1), (8 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*     */           tessellator.getBuffer().pos((i + 1), (8 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*     */           tessellator.getBuffer().pos((i + 1), (-1 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*     */           tessellator.draw();
/*     */           GL11.glEnable(3553);
/*     */           fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, 553648127);
/*     */           GL11.glEnable(2929);
/*     */           GL11.glDepthMask(true);
/*     */           fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, -1);
/*     */           GL11.glDisable(3042);
/*     */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */         GL11.glEnable(2896);
/*     */         GL11.glPopMatrix();
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */   protected void stretch(MoCEntityGoat entitygoat) {
/*     */     GL11.glScalef(entitygoat.getEdad() * 0.01F, entitygoat.getEdad() * 0.01F, entitygoat.getEdad() * 0.01F);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderGoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
