/*     */ package drzhark.mocreatures.client.renderer.entity;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.client.MoCClientProxy;
/*     */ import drzhark.mocreatures.entity.aquatic.MoCEntityDolphin;
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
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCRenderDolphin extends RenderLiving<MoCEntityDolphin> {
/*     */   public MoCRenderDolphin(ModelBase modelbase, float f) {
/*  20 */     super(MoCClientProxy.mc.getRenderManager(), modelbase, f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRender(MoCEntityDolphin entitydolphin, double d, double d1, double d2, float f, float f1) {
/*  25 */     super.doRender((EntityLiving)entitydolphin, d, d1, d2, f, f1);
/*  26 */     boolean flag = (MoCreatures.proxy.getDisplayPetName() && !entitydolphin.getPetName().isEmpty());
/*  27 */     boolean flag1 = MoCreatures.proxy.getDisplayPetHealth();
/*     */     
/*  29 */     if (entitydolphin.renderName()) {
/*  30 */       float f2 = 1.6F;
/*  31 */       float f3 = 0.01666667F * f2;
/*  32 */       float f4 = entitydolphin.getDistance(this.renderManager.renderViewEntity);
/*  33 */       if (f4 < 16.0F) {
/*  34 */         String s = "";
/*  35 */         s = s + entitydolphin.getPetName();
/*  36 */         float f5 = 0.1F;
/*  37 */         FontRenderer fontrenderer = getFontRendererFromRenderManager();
/*  38 */         GL11.glPushMatrix();
/*  39 */         GL11.glTranslatef((float)d + 0.0F, (float)d1 + f5, (float)d2);
/*  40 */         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/*  41 */         GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  42 */         GL11.glScalef(-f3, -f3, f3);
/*  43 */         GL11.glDisable(2896);
/*  44 */         Tessellator tessellator = Tessellator.getInstance();
/*  45 */         byte byte0 = -50;
/*  46 */         if (flag1) {
/*  47 */           GL11.glDisable(3553);
/*  48 */           if (!flag) {
/*  49 */             byte0 = (byte)(byte0 + 8);
/*     */           }
/*  51 */           tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
/*     */           
/*  53 */           float f6 = entitydolphin.getHealth();
/*     */           
/*  55 */           float f7 = entitydolphin.getMaxHealth();
/*  56 */           float f8 = f6 / f7;
/*  57 */           float f9 = 40.0F * f8;
/*  58 */           tessellator.getBuffer().pos((-20.0F + f9), (-10 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*  59 */           tessellator.getBuffer().pos((-20.0F + f9), (-6 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*  60 */           tessellator.getBuffer().pos(20.0D, (-6 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*  61 */           tessellator.getBuffer().pos(20.0D, (-10 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/*  62 */           tessellator.getBuffer().pos(-20.0D, (-10 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*  63 */           tessellator.getBuffer().pos(-20.0D, (-6 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*  64 */           tessellator.getBuffer().pos((f9 - 20.0F), (-6 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*  65 */           tessellator.getBuffer().pos((f9 - 20.0F), (-10 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*  66 */           tessellator.draw();
/*  67 */           GL11.glEnable(3553);
/*     */         } 
/*  69 */         if (flag) {
/*  70 */           GL11.glDepthMask(false);
/*  71 */           GL11.glDisable(2929);
/*  72 */           GL11.glEnable(3042);
/*  73 */           GL11.glBlendFunc(770, 771);
/*  74 */           GL11.glDisable(3553);
/*  75 */           tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
/*  76 */           int i = fontrenderer.getStringWidth(s) / 2;
/*  77 */           tessellator.getBuffer().pos((-i - 1), (-1 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*  78 */           tessellator.getBuffer().pos((-i - 1), (8 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*  79 */           tessellator.getBuffer().pos((i + 1), (8 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*  80 */           tessellator.getBuffer().pos((i + 1), (-1 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/*  81 */           tessellator.draw();
/*  82 */           GL11.glEnable(3553);
/*  83 */           fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, 553648127);
/*  84 */           GL11.glEnable(2929);
/*  85 */           GL11.glDepthMask(true);
/*  86 */           fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, -1);
/*  87 */           GL11.glDisable(3042);
/*  88 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */         } 
/*  90 */         GL11.glEnable(2896);
/*  91 */         GL11.glPopMatrix();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void doRender2(MoCEntityDolphin entitydolphin, double d, double d1, double d2, float f, float f1) {
/*  97 */     super.doRender((EntityLiving)entitydolphin, d, d1, d2, f, f1);
/*  98 */     if (entitydolphin.renderName()) {
/*  99 */       float f2 = 1.6F;
/* 100 */       float f3 = 0.01666667F * f2;
/* 101 */       float f4 = entitydolphin.getDistance(this.renderManager.renderViewEntity);
/* 102 */       String s = "";
/* 103 */       s = s + entitydolphin.getPetName();
/* 104 */       if (f4 < 12.0F && s.length() > 0) {
/* 105 */         FontRenderer fontrenderer = getFontRendererFromRenderManager();
/* 106 */         GL11.glPushMatrix();
/* 107 */         GL11.glTranslatef((float)d + 0.0F, (float)d1 + 0.3F, (float)d2);
/* 108 */         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 109 */         GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 110 */         GL11.glScalef(-f3, -f3, f3);
/* 111 */         GL11.glDisable(2896);
/* 112 */         GL11.glDepthMask(false);
/* 113 */         GL11.glDisable(2929);
/* 114 */         GL11.glEnable(3042);
/* 115 */         GL11.glBlendFunc(770, 771);
/* 116 */         Tessellator tessellator = Tessellator.getInstance();
/* 117 */         byte byte0 = -50;
/* 118 */         GL11.glDisable(3553);
/* 119 */         tessellator.getBuffer().begin(7, DefaultVertexFormats.POSITION_COLOR);
/* 120 */         int i = fontrenderer.getStringWidth(s) / 2;
/* 121 */         tessellator.getBuffer().pos((-i - 1), (-1 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/* 122 */         tessellator.getBuffer().pos((-i - 1), (8 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/* 123 */         tessellator.getBuffer().pos((i + 1), (8 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/* 124 */         tessellator.getBuffer().pos((i + 1), (-1 + byte0), 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
/* 125 */         if (MoCreatures.proxy.getDisplayPetHealth()) {
/* 126 */           float f5 = entitydolphin.getHealth();
/* 127 */           float f6 = entitydolphin.getMaxHealth();
/* 128 */           float f7 = f5 / f6;
/* 129 */           float f8 = 40.0F * f7;
/* 130 */           tessellator.getBuffer().pos((-20.0F + f8), (-10 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/* 131 */           tessellator.getBuffer().pos((-20.0F + f8), (-6 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/* 132 */           tessellator.getBuffer().pos(20.0D, (-6 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/* 133 */           tessellator.getBuffer().pos(20.0D, (-10 + byte0), 0.0D).color(0.7F, 0.0F, 0.0F, 1.0F).endVertex();
/* 134 */           tessellator.getBuffer().pos(-20.0D, (-10 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/* 135 */           tessellator.getBuffer().pos(-20.0D, (-6 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/* 136 */           tessellator.getBuffer().pos((f8 - 20.0F), (-6 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/* 137 */           tessellator.getBuffer().pos((f8 - 20.0F), (-10 + byte0), 0.0D).color(0.0F, 0.7F, 0.0F, 1.0F).endVertex();
/*     */         } 
/* 139 */         tessellator.draw();
/* 140 */         GL11.glEnable(3553);
/* 141 */         fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, 553648127);
/* 142 */         GL11.glEnable(2929);
/* 143 */         GL11.glDepthMask(true);
/* 144 */         fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, byte0, -1);
/* 145 */         GL11.glEnable(2896);
/* 146 */         GL11.glDisable(3042);
/* 147 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 148 */         GL11.glPopMatrix();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected float handleRotationFloat(MoCEntityDolphin entitydolphin, float f) {
/* 155 */     stretch(entitydolphin);
/* 156 */     return entitydolphin.ticksExisted + f;
/*     */   }
/*     */   
/*     */   protected void stretch(MoCEntityDolphin entitydolphin) {
/* 160 */     GL11.glScalef(entitydolphin.getEdad() * 0.01F, entitydolphin.getEdad() * 0.01F, entitydolphin.getEdad() * 0.01F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(MoCEntityDolphin entitydolphin) {
/* 165 */     return entitydolphin.getTexture();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\renderer\entity\MoCRenderDolphin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */