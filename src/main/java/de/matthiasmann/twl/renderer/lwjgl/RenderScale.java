/*    */ package de.matthiasmann.twl.renderer.lwjgl;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class RenderScale
/*    */ {
/*  7 */   public static int scale = 2;
/*    */ 
/*    */   
/*    */   public static void doscale() {
/* 11 */     GL11.glPushMatrix();
/* 12 */     GL11.glScalef(scale, scale, scale);
/*    */   }
/*    */   
/*    */   public static void descale() {
/* 16 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\RenderScale.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */