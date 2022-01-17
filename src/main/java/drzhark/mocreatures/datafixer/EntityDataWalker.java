/*    */ package drzhark.mocreatures.datafixer;
/*    */ 
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.datafix.IDataFixer;
/*    */ import net.minecraft.util.datafix.IDataWalker;
/*    */ 
/*    */ public class EntityDataWalker
/*    */   implements IDataWalker
/*    */ {
/*    */   public NBTTagCompound process(IDataFixer fixer, NBTTagCompound compound, int version) {
/* 11 */     String entityId = compound.getString("id");
/* 12 */     if (entityId.contains("mocreatures.")) {
/* 13 */       String entityName = entityId.replace("mocreatures.", "").toLowerCase();
/* 14 */       if (entityName.equalsIgnoreCase("polarbear")) {
/* 15 */         entityName = "wildpolarbear";
/*    */       }
/* 17 */       String fixedEntityId = "mocreatures:" + entityName;
/* 18 */       compound.setString("id", fixedEntityId);
/*    */     } 
/* 20 */     return compound;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\datafixer\EntityDataWalker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */