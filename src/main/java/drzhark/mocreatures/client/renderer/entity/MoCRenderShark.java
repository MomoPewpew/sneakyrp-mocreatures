/*     */ package drzhark.mocreatures.client.renderer.entity;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.client.MoCClientProxy;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityShark;
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
/*     */ public class MoCRenderShark extends RenderLiving<MoCEntityShark> {
/*     */   public MoCRenderShark(ModelBase modelbase, float f) {
/*  20 */     super(MoCClientProxy.mc.getRenderManager(), modelbase, f);
/*     */   }
/*     */
/*     */
/*     */   public void doRender(MoCEntityShark entityshark, double d, double d1, double d2, float f, float f1) {
/*  25 */     super.doRender(entityshark, d, d1, d2, f, f1);
/*  26 */     boolean flag = (MoCreatures.proxy.getDisplayPetName() && !entityshark.getPetName().isEmpty());
/*  27 */     boolean flag1 = MoCreatures.proxy.getDisplayPetHealth();
/*  28 */     if (entityshark.renderName()) {
/*  29 */       float f2 = 1.6F;
/*  30 */       float f3 = 0.01666667F * f2;
/*  31 */       float f4 = entityshark.getDistance(this.renderManager.renderViewEntity);
/*  32 */       if (f4 < 16.0F) {
/*  33 */         String s = "";
/*  34 */         s = s + entityshark.getPetName();
/*  35 */         float f5 = 0.1F;
/*  36 */         FontRenderer fontrenderer = getFontRendererFromRenderManager();
/*  37 */         GL11.glPushMatrix();
/*  38 */         GL11.glTranslatef((float)d + 0.0F, (float)d1 + f5, (float)d2);
/*  39 */         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/*  40 */         GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  41 */         GL11.glScalef(-f3, -f3, f3);
/*  42 */         GL11.glDisable(2896);
/*  43 */         Tessellator tessellator = Tessellator.getInstance();
/*  44 */         byte byte0 = -50;
/*  45 */         if (flag1) {
/*  46 */           GL11.glDisable(3553);
/*  47 */           if (!flag) {
/*  48 */             byte0 = (byte)(byte0 + 8);
/*     */           }
/*  50 */           tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
/*     */
/*  52 */           float f6 = entityshark.getHealth();
/*  53 */           float f7 = entityshark.getMaxHealth();
/*  54 */           float f8 = f6 / f7;
/*  55 */           float f9 = 40.0F * f8;
/*  56 */           tessellator.getBuffer().pos((-20.0F + f9), (-10 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*  57 */           tessellator.getBuffer().pos((-20.0F + f9), (-6 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*  58 */           tessellator.getBuffer().pos(20.0D, (-6 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*  59 */           tessellator.getBuffer().pos(20.0D, (-10 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*  60 */           tessellator.getBuffer().pos(-20.0D, (-10 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*  61 */           tessellator.getBuffer().pos(-20.0D, (-6 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*  62 */           tessellator.getBuffer().pos((f9 - 20.0F), (-6 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*  63 */           tessellator.getBuffer().pos((f9 - 20.0F), (-10 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*  64 */           tessellator.draw();
/*  65 */           GL11.glEnable(3553);
/*     */         }
/*  67 */         if (flag) {
/*  68 */           GL11.glDepthMask(false);
/*  69 */           GL11.glDisable(2929);
/*  70 */           GL11.glEnable(3042);
/*  71 */           GL11.glBlendFunc(770, 771);
/*  72 */           GL11.glDisable(3553);
/*  73 */           tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
/*  74 */           int i = fontrenderer.getStringWidth(s) / 2;
/*  75 */           tessellator.getBuffer().pos((-i - 1), (-1 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*  76 */           tessellator.getBuffer().pos((-i - 1), (8 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*  77 */           tessellator.getBuffer().pos((i + 1), (8 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*  78 */           tessellator.getBuffer().pos((i + 1), (-1 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*  79 */           tessellator.draw();
/*  80 */           GL11.glEnable(3553);
/*  81 */           fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, 553648127);
/*  82 */           GL11.glEnable(2929);
/*  83 */           GL11.glDepthMask(true);
/*  84 */           fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, -1);
/*  85 */           GL11.glDisable(3042);
/*  86 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */         }
/*  88 */         GL11.glEnable(2896);
/*  89 */         GL11.glPopMatrix();
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */   public void doRender2(MoCEntityShark entityshark, double d, double d1, double d2, float f, float f1) {
/*  95 */     super.doRender(entityshark, d, d1, d2, f, f1);
/*  96 */     if (entityshark.renderName()) {
/*  97 */       float f2 = 1.6F;
/*  98 */       float f3 = 0.01666667F * f2;
/*  99 */       float f4 = entityshark.getDistance(this.renderManager.renderViewEntity);
/* 100 */       String s = "";
/* 101 */       s = s + entityshark.getPetName();
/* 102 */       if (f4 < 12.0F && s.length() > 0) {
/* 103 */         FontRenderer fontrenderer = getFontRendererFromRenderManager();
/* 104 */         GL11.glPushMatrix();
/* 105 */         GL11.glTranslatef((float)d + 0.0F, (float)d1 + 0.2F, (float)d2);
/* 106 */         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 107 */         GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 108 */         GL11.glScalef(-f3, -f3, f3);
/* 109 */         GL11.glDisable(2896);
/* 110 */         GL11.glDepthMask(false);
/* 111 */         GL11.glDisable(2929);
/* 112 */         GL11.glEnable(3042);
/* 113 */         GL11.glBlendFunc(770, 771);
/* 114 */         Tessellator tessellator = Tessellator.getInstance();
/* 115 */         byte byte0 = -50;
/* 116 */         GL11.glDisable(3553);
/* 117 */         tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
/* 118 */         int i = fontrenderer.getStringWidth(s) / 2;
/* 119 */         tessellator.getBuffer().pos((-i - 1), (-1 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/* 120 */         tessellator.getBuffer().pos((-i - 1), (8 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/* 121 */         tessellator.getBuffer().pos((i + 1), (8 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/* 122 */         tessellator.getBuffer().pos((i + 1), (-1 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/* 123 */         if (MoCreatures.proxy.getDisplayPetHealth()) {
/* 124 */           float f5 = entityshark.getHealth();
/* 125 */           float f6 = entityshark.getMaxHealth();
/* 126 */           float f7 = f5 / f6;
/* 127 */           float f8 = 40.0F * f7;
/* 128 */           tessellator.getBuffer().pos((-20.0F + f8), (-10 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/* 129 */           tessellator.getBuffer().pos((-20.0F + f8), (-6 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/* 130 */           tessellator.getBuffer().pos(20.0D, (-6 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/* 131 */           tessellator.getBuffer().pos(20.0D, (-10 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/* 132 */           tessellator.getBuffer().pos(-20.0D, (-10 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/* 133 */           tessellator.getBuffer().pos(-20.0D, (-6 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/* 134 */           tessellator.getBuffer().pos((f8 - 20.0F), (-6 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/* 135 */           tessellator.getBuffer().pos((f8 - 20.0F), (-10 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*     */         }
/* 137 */         tessellator.draw();
/* 138 */         GL11.glEnable(3553);
/* 139 */         fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, 553648127);
/* 140 */         GL11.glEnable(2929);
/* 141 */         GL11.glDepthMask(true);
/* 142 */         fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, -1);
/* 143 */         GL11.glEnable(2896);
/* 144 */         GL11.glDisable(3042);
/* 145 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 146 */         GL11.glPopMatrix();
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   protected float handleRotationFloat(MoCEntityShark entityshark, float f) {
/* 153 */     stretch(entityshark);
/* 154 */     return entityshark.ticksExisted + f;
/*     */   }
/*     */
/*     */   protected void stretch(MoCEntityShark entityshark) {
/* 158 */     GL11.glScalef(entityshark.getEdad() * 0.01F, entityshark.getEdad() * 0.01F, entityshark.getEdad() * 0.01F);
/*     */   }
/*     */
/*     */
/*     */   protected ResourceLocation getEntityTexture(MoCEntityShark entityshark) {
/* 163 */     return entityshark.getTexture();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderShark.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
