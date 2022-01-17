/*     */ package drzhark.mocreatures.network;
/*     */ 
/*     */ import drzhark.mocreatures.client.MoCClientProxy;
/*     */ import drzhark.mocreatures.client.gui.helpers.MoCGUIEntityNamer;
/*     */ import drzhark.mocreatures.entity.IMoCEntity;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityGolem;
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityOgre;
/*     */ import drzhark.mocreatures.entity.passive.MoCEntityHorse;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAnimation;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAppear;
/*     */ import drzhark.mocreatures.network.message.MoCMessageAttachedEntity;
/*     */ import drzhark.mocreatures.network.message.MoCMessageEntityDive;
/*     */ import drzhark.mocreatures.network.message.MoCMessageEntityJump;
/*     */ import drzhark.mocreatures.network.message.MoCMessageExplode;
/*     */ import drzhark.mocreatures.network.message.MoCMessageHealth;
/*     */ import drzhark.mocreatures.network.message.MoCMessageHeart;
/*     */ import drzhark.mocreatures.network.message.MoCMessageInstaSpawn;
/*     */ import drzhark.mocreatures.network.message.MoCMessageNameGUI;
/*     */ import drzhark.mocreatures.network.message.MoCMessageShuffle;
/*     */ import drzhark.mocreatures.network.message.MoCMessageTwoBytes;
/*     */ import drzhark.mocreatures.network.message.MoCMessageUpdatePetName;
/*     */ import drzhark.mocreatures.network.message.MoCMessageVanish;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ 
/*     */ public class MoCMessageHandler
/*     */ {
/*  37 */   public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("MoCreatures");
/*     */   
/*     */   public static void init() {
/*  40 */     INSTANCE.registerMessage(MoCMessageAnimation.class, MoCMessageAnimation.class, 0, Side.CLIENT);
/*  41 */     INSTANCE.registerMessage(MoCMessageAppear.class, MoCMessageAppear.class, 1, Side.CLIENT);
/*  42 */     INSTANCE.registerMessage(MoCMessageAttachedEntity.class, MoCMessageAttachedEntity.class, 2, Side.CLIENT);
/*  43 */     INSTANCE.registerMessage(MoCMessageEntityDive.class, MoCMessageEntityDive.class, 3, Side.SERVER);
/*  44 */     INSTANCE.registerMessage(MoCMessageEntityJump.class, MoCMessageEntityJump.class, 4, Side.SERVER);
/*  45 */     INSTANCE.registerMessage(MoCMessageExplode.class, MoCMessageExplode.class, 5, Side.CLIENT);
/*  46 */     INSTANCE.registerMessage(MoCMessageHealth.class, MoCMessageHealth.class, 6, Side.CLIENT);
/*  47 */     INSTANCE.registerMessage(MoCMessageHeart.class, MoCMessageHeart.class, 7, Side.CLIENT);
/*  48 */     INSTANCE.registerMessage(MoCMessageInstaSpawn.class, MoCMessageInstaSpawn.class, 8, Side.SERVER);
/*  49 */     INSTANCE.registerMessage(MoCMessageNameGUI.class, MoCMessageNameGUI.class, 9, Side.CLIENT);
/*  50 */     INSTANCE.registerMessage(MoCMessageUpdatePetName.class, MoCMessageUpdatePetName.class, 10, Side.SERVER);
/*  51 */     INSTANCE.registerMessage(MoCMessageShuffle.class, MoCMessageShuffle.class, 11, Side.CLIENT);
/*  52 */     INSTANCE.registerMessage(MoCMessageTwoBytes.class, MoCMessageTwoBytes.class, 12, Side.CLIENT);
/*  53 */     INSTANCE.registerMessage(MoCMessageVanish.class, MoCMessageVanish.class, 13, Side.CLIENT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void handleMessage(IMessageHandler message, MessageContext ctx) {
/*  60 */     if (ctx.side == Side.CLIENT) {
/*  61 */       FMLCommonHandler.instance().getWorldThread(FMLCommonHandler.instance().getClientToServerNetworkManager().getNetHandler()).addScheduledTask(new ClientPacketTask(message, ctx));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ClientPacketTask
/*     */     implements Runnable
/*     */   {
/*     */     private IMessageHandler message;
/*     */     
/*     */     private MessageContext ctx;
/*     */ 
/*     */     
/*     */     public ClientPacketTask(IMessageHandler message, MessageContext ctx) {
/*  75 */       this.message = message;
/*  76 */       this.ctx = ctx;
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/*  81 */       if (this.message instanceof MoCMessageAnimation) {
/*  82 */         MoCMessageAnimation message = (MoCMessageAnimation)this.message;
/*  83 */         List<Entity> entList = MoCClientProxy.mc.player.world.loadedEntityList;
/*  84 */         for (Entity ent : entList) {
/*  85 */           if (ent.getEntityId() == message.entityId && ent instanceof IMoCEntity) {
/*  86 */             ((IMoCEntity)ent).performAnimation(message.animationType); break;
/*     */           } 
/*     */         } 
/*     */         return;
/*     */       } 
/*  91 */       if (this.message instanceof MoCMessageAppear) {
/*  92 */         MoCMessageAppear message = (MoCMessageAppear)this.message;
/*  93 */         List<Entity> entList = MoCClientProxy.mc.player.world.loadedEntityList;
/*  94 */         for (Entity ent : entList) {
/*  95 */           if (ent.getEntityId() == message.entityId && ent instanceof MoCEntityHorse) {
/*  96 */             ((MoCEntityHorse)ent).MaterializeFX(); break;
/*     */           } 
/*     */         } 
/*     */         return;
/*     */       } 
/* 101 */       if (this.message instanceof MoCMessageAttachedEntity) {
/* 102 */         MoCMessageAttachedEntity message = (MoCMessageAttachedEntity)this.message;
/* 103 */         Object var2 = MoCClientProxy.mc.player.world.getEntityByID(message.sourceEntityId);
/* 104 */         Entity var3 = MoCClientProxy.mc.player.world.getEntityByID(message.targetEntityId);
/*     */         
/* 106 */         if (var2 != null)
/* 107 */           ((Entity)var2).startRiding(var3); 
/*     */         return;
/*     */       } 
/* 110 */       if (this.message instanceof MoCMessageExplode) {
/* 111 */         MoCMessageExplode message = (MoCMessageExplode)this.message;
/* 112 */         List<Entity> entList = MoCClientProxy.mc.player.world.loadedEntityList;
/* 113 */         for (Entity ent : entList) {
/* 114 */           if (ent.getEntityId() == message.entityId && ent instanceof MoCEntityOgre) {
/* 115 */             ((MoCEntityOgre)ent).performDestroyBlastAttack(); break;
/*     */           } 
/*     */         } 
/*     */         return;
/*     */       } 
/* 120 */       if (this.message instanceof MoCMessageHealth) {
/* 121 */         MoCMessageHealth message = (MoCMessageHealth)this.message;
/* 122 */         List<Entity> entList = MoCClientProxy.mc.player.world.loadedEntityList;
/* 123 */         for (Entity ent : entList) {
/* 124 */           if (ent.getEntityId() == message.entityId && ent instanceof EntityLiving) {
/* 125 */             ((EntityLiving)ent).setHealth(message.health); break;
/*     */           } 
/*     */         } 
/*     */         return;
/*     */       } 
/* 130 */       if (this.message instanceof MoCMessageHeart) {
/* 131 */         MoCMessageHeart message = (MoCMessageHeart)this.message;
/* 132 */         Entity entity = null;
/* 133 */         while (entity == null) {
/* 134 */           entity = MoCClientProxy.mc.player.world.getEntityByID(message.entityId);
/* 135 */           if (entity != null && 
/* 136 */             entity instanceof IMoCTameable) {
/* 137 */             ((IMoCTameable)entity).spawnHeart();
/*     */           }
/*     */         } 
/*     */         return;
/*     */       } 
/* 142 */       if (this.message instanceof MoCMessageShuffle) {
/* 143 */         MoCMessageShuffle message = (MoCMessageShuffle)this.message;
/* 144 */         List<Entity> entList = MoCClientProxy.mc.player.world.loadedEntityList;
/* 145 */         for (Entity ent : entList) {
/* 146 */           if (ent.getEntityId() == message.entityId && ent instanceof MoCEntityHorse) {
/* 147 */             if (message.flag) {
/*     */               break;
/*     */             }
/* 150 */             ((MoCEntityHorse)ent).shuffleCounter = 0;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */         return;
/*     */       } 
/* 156 */       if (this.message instanceof MoCMessageTwoBytes) {
/* 157 */         MoCMessageTwoBytes message = (MoCMessageTwoBytes)this.message;
/* 158 */         Entity ent = MoCClientProxy.mc.player.world.getEntityByID(message.entityId);
/* 159 */         if (ent != null && ent instanceof MoCEntityGolem)
/* 160 */           ((MoCEntityGolem)ent).saveGolemCube(message.slot, message.value); 
/*     */         return;
/*     */       } 
/* 163 */       if (this.message instanceof MoCMessageVanish) {
/* 164 */         MoCMessageVanish message = (MoCMessageVanish)this.message;
/* 165 */         List<Entity> entList = MoCClientProxy.mc.player.world.loadedEntityList;
/* 166 */         for (Entity ent : entList) {
/* 167 */           if (ent.getEntityId() == message.entityId && ent instanceof MoCEntityHorse) {
/* 168 */             ((MoCEntityHorse)ent).setVanishC((byte)1); break;
/*     */           } 
/*     */         } 
/*     */         return;
/*     */       } 
/* 173 */       if (this.message instanceof MoCMessageNameGUI) {
/* 174 */         MoCMessageNameGUI message = (MoCMessageNameGUI)this.message;
/* 175 */         Entity entity = MoCClientProxy.mc.player.world.getEntityByID(message.entityId);
/* 176 */         MoCClientProxy.mc.displayGuiScreen((GuiScreen)new MoCGUIEntityNamer((IMoCEntity)entity, ((IMoCEntity)entity).getPetName()));
/*     */         return;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\network\MoCMessageHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */