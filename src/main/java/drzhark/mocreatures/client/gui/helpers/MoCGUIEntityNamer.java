/*     */ package drzhark.mocreatures.client.gui.helpers;
/*     */ 
/*     */ import drzhark.mocreatures.MoCProxy;
/*     */ import drzhark.mocreatures.client.MoCClientProxy;
/*     */ import drzhark.mocreatures.entity.IMoCEntity;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import drzhark.mocreatures.network.MoCMessageHandler;
/*     */ import drzhark.mocreatures.network.message.MoCMessageUpdatePetName;
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.texture.TextureManager;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.util.ChatAllowedCharacters;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCGUIEntityNamer
/*     */   extends GuiScreen
/*     */ {
/*     */   protected String screenTitle;
/*     */   private final IMoCEntity NamedEntity;
/*     */   private int updateCounter;
/*     */   private String NameToSet;
/*     */   protected int xSize;
/*     */   protected int ySize;
/*  32 */   private static TextureManager textureManager = MoCClientProxy.mc.getTextureManager();
/*  33 */   private static final ResourceLocation TEXTURE_MOCNAME = new ResourceLocation("mocreatures", MoCProxy.GUI_TEXTURE + "mocname.png");
/*     */   
/*     */   public MoCGUIEntityNamer(IMoCEntity mocanimal, String s) {
/*  36 */     this.xSize = 256;
/*  37 */     this.ySize = 181;
/*  38 */     this.screenTitle = "Choose your Pet's name:";
/*  39 */     this.NamedEntity = mocanimal;
/*  40 */     this.NameToSet = s;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  45 */     this.buttonList.clear();
/*  46 */     Keyboard.enableRepeatEvents(true);
/*  47 */     this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, "Done"));
/*     */   }
/*     */   
/*     */   public void updateName() {
/*  51 */     this.NamedEntity.setPetName(this.NameToSet);
/*  52 */     MoCMessageHandler.INSTANCE.sendToServer((IMessage)new MoCMessageUpdatePetName(((EntityLiving)this.NamedEntity).getEntityId(), this.NameToSet));
/*  53 */     this.mc.displayGuiScreen(null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton guibutton) {
/*  58 */     if (!guibutton.enabled) {
/*     */       return;
/*     */     }
/*  61 */     if (guibutton.id == 0 && this.NameToSet != null && !this.NameToSet.equals("")) {
/*  62 */       updateName();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int i, int j, float f) {
/*  68 */     drawDefaultBackground();
/*  69 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  70 */     textureManager.bindTexture(TEXTURE_MOCNAME);
/*  71 */     int l = (this.width - this.xSize) / 2;
/*  72 */     int i1 = (this.height - this.ySize + 16) / 2;
/*  73 */     drawTexturedModalRect(l, i1, 0, 0, this.xSize, this.ySize);
/*  74 */     drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 100, 16777215);
/*  75 */     drawCenteredString(this.fontRenderer, this.NameToSet, this.width / 2, 120, 16777215);
/*  76 */     super.drawScreen(i, j, f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleKeyboardInput() throws IOException {
/*  81 */     if (Keyboard.getEventKeyState() && 
/*  82 */       Keyboard.getEventKey() == 28)
/*     */     {
/*  84 */       updateName();
/*     */     }
/*     */     
/*  87 */     super.handleKeyboardInput();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keyTyped(char c, int i) {
/*  92 */     if (i == 14 && this.NameToSet.length() > 0) {
/*  93 */       this.NameToSet = this.NameToSet.substring(0, this.NameToSet.length() - 1);
/*     */     }
/*  95 */     if (ChatAllowedCharacters.isAllowedCharacter(c) && this.NameToSet.length() < 15) {
/*     */       
/*  97 */       StringBuilder name = new StringBuilder(this.NameToSet);
/*  98 */       name.append(c);
/*  99 */       this.NameToSet = name.toString();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/* 105 */     Keyboard.enableRepeatEvents(false);
/* 106 */     if (this.NamedEntity instanceof IMoCTameable) {
/* 107 */       IMoCTameable tamedEntity = (IMoCTameable)this.NamedEntity;
/* 108 */       tamedEntity.playTameEffect(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 114 */     this.updateCounter++;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\gui\helpers\MoCGUIEntityNamer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */