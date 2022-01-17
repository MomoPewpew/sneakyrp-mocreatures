/*     */ package drzhark.mocreatures.item;
/*     */ 
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MoCItemCreaturePedia
/*     */   extends MoCItem
/*     */ {
/*     */   public MoCItemCreaturePedia(String name) {
/*  23 */     super(name);
/*  24 */     this.maxStackSize = 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void itemInteractionForEntity2(ItemStack par1ItemStack, EntityLiving entityliving) {
/*  31 */     if (entityliving.world.isRemote) {
/*     */       return;
/*     */     }
/*     */     
/*  35 */     if (entityliving instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse) {
/*  36 */       MoCreatures.showCreaturePedia("/mocreatures/pedia/horse.png");
/*     */       
/*     */       return;
/*     */     } 
/*  40 */     if (entityliving instanceof drzhark.mocreatures.entity.passive.MoCEntityTurtle) {
/*  41 */       MoCreatures.showCreaturePedia("/mocreatures/pedia/turtle.png");
/*     */       
/*     */       return;
/*     */     } 
/*  45 */     if (entityliving instanceof drzhark.mocreatures.entity.passive.MoCEntityBunny) {
/*  46 */       MoCreatures.showCreaturePedia("/mocreatures/pedia/bunny.png");
/*     */       
/*     */       return;
/*     */     } 
/*  50 */     if (entityliving instanceof drzhark.mocreatures.entity.aquatic.MoCEntityDolphin) {
/*  51 */       MoCreatures.showCreaturePedia("/mocreatures/pedia/dolphin.png");
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
/*  58 */     ItemStack stack = player.getHeldItem(hand);
/*  59 */     if (!world.isRemote) {
/*  60 */       double dist = 5.0D;
/*  61 */       double d1 = -1.0D;
/*  62 */       EntityLivingBase entityliving = null;
/*  63 */       List<Entity> list = world.getEntitiesWithinAABBExcludingEntity((Entity)player, player.getEntityBoundingBox().expand(dist, dist, dist));
/*  64 */       for (int i = 0; i < list.size(); i++) {
/*  65 */         Entity entity1 = list.get(i);
/*  66 */         if (entity1 != null && entity1 instanceof EntityLivingBase)
/*     */         {
/*     */ 
/*     */           
/*  70 */           if (player.canEntityBeSeen(entity1)) {
/*     */ 
/*     */ 
/*     */             
/*  74 */             double d2 = entity1.getDistanceSq(player.posX, player.posY, player.posZ);
/*  75 */             if ((dist < 0.0D || d2 < dist * dist) && (d1 == -1.0D || d2 < d1) && ((EntityLivingBase)entity1)
/*  76 */               .canEntityBeSeen((Entity)player)) {
/*  77 */               d1 = d2;
/*  78 */               entityliving = (EntityLivingBase)entity1;
/*     */             } 
/*     */           }  } 
/*     */       } 
/*  82 */       if (entityliving == null) {
/*  83 */         return new ActionResult(EnumActionResult.PASS, stack);
/*     */       }
/*     */       
/*  86 */       if (entityliving instanceof drzhark.mocreatures.entity.passive.MoCEntityHorse) {
/*  87 */         MoCreatures.showCreaturePedia(player, "/mocreatures/pedia/horse.png");
/*  88 */         return new ActionResult(EnumActionResult.SUCCESS, stack);
/*     */       } 
/*     */       
/*  91 */       if (entityliving instanceof drzhark.mocreatures.entity.passive.MoCEntityTurtle) {
/*  92 */         MoCreatures.showCreaturePedia(player, "/mocreatures/pedia/turtle.png");
/*  93 */         return new ActionResult(EnumActionResult.SUCCESS, stack);
/*     */       } 
/*     */       
/*  96 */       if (entityliving instanceof drzhark.mocreatures.entity.passive.MoCEntityBunny) {
/*  97 */         MoCreatures.showCreaturePedia(player, "/mocreatures/pedia/bunny.png");
/*  98 */         return new ActionResult(EnumActionResult.SUCCESS, stack);
/*     */       } 
/*     */     } 
/*     */     
/* 102 */     return new ActionResult(EnumActionResult.PASS, stack);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\item\MoCItemCreaturePedia.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */