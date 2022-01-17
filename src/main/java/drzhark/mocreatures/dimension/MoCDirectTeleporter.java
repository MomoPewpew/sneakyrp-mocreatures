/*    */ package drzhark.mocreatures.dimension;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.Teleporter;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldServer;
/*    */ 
/*    */ public class MoCDirectTeleporter
/*    */   extends Teleporter
/*    */ {
/*    */   private boolean portalDone;
/*    */   
/*    */   public MoCDirectTeleporter(WorldServer par1WorldServer) {
/* 18 */     super(par1WorldServer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void placeInPortal(Entity par1Entity, float rotationYaw) {
/* 23 */     int var9 = MathHelper.floor(par1Entity.posX);
/* 24 */     int var10 = MathHelper.floor(par1Entity.posY) - 1;
/* 25 */     int var11 = MathHelper.floor(par1Entity.posZ);
/* 26 */     par1Entity.setLocationAndAngles(var9, var10, var11, par1Entity.rotationYaw, 0.0F);
/* 27 */     par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
/*    */   }
/*    */   
/*    */   public void createPortal(World par1World, Random par2Random) {
/* 31 */     MoCWorldGenPortal myPortal = new MoCWorldGenPortal(Blocks.QUARTZ_BLOCK, 2, Blocks.QUARTZ_STAIRS, 0, Blocks.QUARTZ_BLOCK, 1, Blocks.QUARTZ_BLOCK, 0);
/*    */     
/* 33 */     for (int i = 0; i < 14; i++) {
/* 34 */       if (!this.portalDone) {
/* 35 */         int randPosY = 58 + i;
/* 36 */         this.portalDone = myPortal.generate(par1World, par2Random, new BlockPos(0, randPosY, 0));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\dimension\MoCDirectTeleporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */