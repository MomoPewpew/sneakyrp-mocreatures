/*     */ package drzhark.mocreatures;
/*     */
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class MoCEventHooks
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void onWorldUnload(WorldEvent.Unload event) {
/*  31 */     if ((event.getWorld()).provider.getDimensionType().getId() == 0) {
/*  32 */       MoCreatures.proxy.worldInitDone = false;
/*     */     }
/*     */   }
/*     */
/*     */   @SubscribeEvent
/*     */   public void onWorldLoad(WorldEvent.Load event) {
/*  38 */     if (DimensionManager.getWorld(0) != null && !MoCreatures.proxy.worldInitDone) {
/*     */
/*  40 */       MoCPetMapData data = (MoCPetMapData)DimensionManager.getWorld(0).getMapStorage().getOrLoadData(MoCPetMapData.class, "mocreatures");
/*  41 */       if (data == null) {
/*  42 */         data = new MoCPetMapData("mocreatures");
/*     */       }
/*     */
/*  45 */       DimensionManager.getWorld(0).getMapStorage().setData("mocreatures", data);
/*  46 */       DimensionManager.getWorld(0).getMapStorage().saveAllData();
/*  47 */       MoCreatures.instance.mapData = data;
/*  48 */       MoCreatures.proxy.worldInitDone = true;
/*     */     }
/*     */
/*  51 */     GameRules gameRule = event.getWorld().getGameRules();
/*  52 */     if (gameRule != null && !MoCreatures.isCustomSpawnerLoaded) {
/*  53 */       gameRule.setOrCreateGameRule("doMobSpawning", "true");
/*     */     }
/*     */   }
/*     */
/*     */   @SubscribeEvent
/*     */   public void onLivingDeathEvent(LivingDeathEvent event) {
/*  59 */     if (!(event.getEntity()).world.isRemote &&
/*  60 */       IMoCTameable.class.isAssignableFrom(event.getEntityLiving().getClass())) {
/*  61 */       IMoCTameable mocEntity = (IMoCTameable)event.getEntityLiving();
/*  62 */       if (mocEntity.getIsTamed() && mocEntity.getPetHealth() > 0.0F && !mocEntity.isRiderDisconnecting()) {
/*     */         return;
/*     */       }
/*     */
/*  66 */       if (mocEntity.getOwnerPetId() != -1)
/*     */       {
/*  68 */         MoCreatures.instance.mapData.removeOwnerPet(mocEntity, mocEntity.getOwnerPetId());
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\MoCEventHooks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
