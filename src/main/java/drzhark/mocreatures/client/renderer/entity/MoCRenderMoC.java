/*     */ package drzhark.mocreatures.client.renderer.entity;
/*     */
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.client.MoCClientProxy;
/*     */ import drzhark.mocreatures.entity.IMoCEntity;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCRenderMoC<T extends EntityLiving> extends RenderLiving<T> {
/*     */   public MoCRenderMoC(ModelBase modelbase, float f) {
/*  22 */     super(MoCClientProxy.mc.getRenderManager(), modelbase, f);
/*     */   }
/*     */
/*     */
/*     */   public void doRender(T entity, double d, double d1, double d2, float f, float f1) {
/*  27 */     doRenderMoC(entity, d, d1, d2, f, f1);
/*     */   }
/*     */
/*     */   public void doRenderMoC(T entity, double d, double d1, double d2, float f, float f1) {
/*  31 */     super.doRender(entity, d, d1, d2, f, f1);
/*  32 */     IMoCEntity entityMoC = (IMoCEntity)entity;
/*  33 */     boolean flag = (MoCreatures.proxy.getDisplayPetName() && !entityMoC.getPetName().isEmpty());
/*  34 */     boolean flag1 = MoCreatures.proxy.getDisplayPetHealth();
/*  35 */     if (entityMoC.renderName()) {
/*  36 */       float f2 = 1.6F;
/*  37 */       float f3 = 0.01666667F * f2;
/*  38 */       float f5 = ((Entity)entityMoC).getDistance(this.renderManager.renderViewEntity);
/*  39 */       if (f5 < 16.0F) {
/*  40 */         String s = "";
/*  41 */         s = s + entityMoC.getPetName();
/*  42 */         float f7 = 0.1F;
/*  43 */         FontRenderer fontrenderer = getFontRendererFromRenderManager();
/*  44 */         GL11.glPushMatrix();
/*  45 */         GL11.glTranslatef((float)d + 0.0F, (float)d1 + f7, (float)d2);
/*  46 */         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/*  47 */         GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  48 */         GL11.glScalef(-f3, -f3, f3);
/*  49 */         GL11.glDisable(2896);
/*  50 */         Tessellator tessellator1 = Tessellator.getInstance();
/*  51 */         int yOff = entityMoC.nameYOffset();
/*  52 */         if (flag1) {
/*  53 */           GL11.glDisable(3553);
/*  54 */           if (!flag) {
/*  55 */             yOff += 8;
/*     */           }
/*  57 */           tessellator1.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
/*     */
/*  59 */           float f8 = ((EntityLiving)entityMoC).getHealth();
/*  60 */           float f9 = ((EntityLiving)entityMoC).getMaxHealth();
/*  61 */           float f10 = f8 / f9;
/*  62 */           float f11 = 40.0F * f10;
/*  63 */           tessellator1.getBuffer().pos((-20.0F + f11), (-10 + yOff), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*  64 */           tessellator1.getBuffer().pos((-20.0F + f11), (-6 + yOff), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*  65 */           tessellator1.getBuffer().pos(20.0D, (-6 + yOff), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*  66 */           tessellator1.getBuffer().pos(20.0D, (-10 + yOff), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*  67 */           tessellator1.getBuffer().pos(-20.0D, (-10 + yOff), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*  68 */           tessellator1.getBuffer().pos(-20.0D, (-6 + yOff), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*  69 */           tessellator1.getBuffer().pos((f11 - 20.0F), (-6 + yOff), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*  70 */           tessellator1.getBuffer().pos((f11 - 20.0F), (-10 + yOff), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*  71 */           tessellator1.draw();
/*  72 */           GL11.glEnable(3553);
/*     */         }
/*  74 */         if (flag) {
/*  75 */           GL11.glDepthMask(false);
/*  76 */           GL11.glDisable(2929);
/*  77 */           GL11.glEnable(3042);
/*  78 */           GL11.glBlendFunc(770, 771);
/*  79 */           GL11.glDisable(3553);
/*  80 */           tessellator1.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
/*  81 */           int i = fontrenderer.getStringWidth(s) / 2;
/*  82 */           tessellator1.getBuffer().pos((-i - 1), (-1 + yOff), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*  83 */           tessellator1.getBuffer().pos((-i - 1), (8 + yOff), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*  84 */           tessellator1.getBuffer().pos((i + 1), (8 + yOff), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*  85 */           tessellator1.getBuffer().pos((i + 1), (-1 + yOff), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*  86 */           tessellator1.draw();
/*  87 */           GL11.glEnable(3553);
/*  88 */           fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, yOff, 553648127);
/*  89 */           GL11.glEnable(2929);
/*  90 */           GL11.glDepthMask(true);
/*  91 */           fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, yOff, -1);
/*  92 */           GL11.glDisable(3042);
/*  93 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */         }
/*  95 */         GL11.glEnable(2896);
/*  96 */         GL11.glPopMatrix();
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   protected void stretch(IMoCEntity mocreature) {
/* 103 */     float f = mocreature.getSizeFactor();
/* 104 */     if (f != 0.0F) {
/* 105 */       GL11.glScalef(f, f, f);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   protected void preRenderCallback(T entityliving, float f) {
/* 111 */     IMoCEntity mocreature = (IMoCEntity)entityliving;
/* 112 */     super.preRenderCallback(entityliving, f);
/*     */
/*     */
/* 115 */     adjustPitch(mocreature);
/* 116 */     adjustRoll(mocreature);
/* 117 */     adjustYaw(mocreature);
/* 118 */     stretch(mocreature);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   protected void adjustPitch(IMoCEntity mocreature) {
/* 128 */     float f = mocreature.pitchRotationOffset();
/*     */
/* 130 */     if (f != 0.0F) {
/* 131 */       GL11.glRotatef(f, -1.0F, 0.0F, 0.0F);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   protected void adjustRoll(IMoCEntity mocreature) {
/* 141 */     float f = mocreature.rollRotationOffset();
/*     */
/* 143 */     if (f != 0.0F) {
/* 144 */       GL11.glRotatef(f, 0.0F, 0.0F, -1.0F);
/*     */     }
/*     */   }
/*     */
/*     */   protected void adjustYaw(IMoCEntity mocreature) {
/* 149 */     float f = mocreature.yawRotationOffset();
/* 150 */     if (f != 0.0F) {
/* 151 */       GL11.glRotatef(f, 0.0F, -1.0F, 0.0F);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   protected void adjustOffsets(float xOffset, float yOffset, float zOffset) {
/* 160 */     GL11.glTranslatef(xOffset, yOffset, zOffset);
/*     */   }
/*     */
/*     */
/*     */   protected ResourceLocation getEntityTexture(EntityLiving entity) {
/* 165 */     return ((IMoCEntity)entity).getTexture();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderMoC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
