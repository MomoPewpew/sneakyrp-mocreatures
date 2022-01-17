/*     */ package drzhark.guiapi;
/*     */ 
/*     */ import de.matthiasmann.twl.GUI;
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import de.matthiasmann.twl.input.Input;
/*     */ import de.matthiasmann.twl.input.lwjgl.LWJGLInput;
/*     */ import de.matthiasmann.twl.renderer.Renderer;
/*     */ import de.matthiasmann.twl.renderer.lwjgl.LWJGLRenderer;
/*     */ import de.matthiasmann.twl.theme.ThemeManager;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLStreamHandler;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiWidgetScreen
/*     */   extends Widget
/*     */ {
/*     */   public static GuiWidgetScreen instance;
/*     */   public static int screenheight;
/*     */   public static int screenwidth;
/*     */   public static URL themeURL;
/*     */   
/*     */   public static GuiWidgetScreen getInstance() {
/*  45 */     if (instance != null) {
/*  46 */       return instance;
/*     */     }
/*     */     
/*     */     try {
/*  50 */       instance = new GuiWidgetScreen();
/*  51 */       instance.renderer = new LWJGLRenderer();
/*  52 */       String themename = "gui/twlGuiTheme.xml";
/*  53 */       instance.gui = new GUI(instance, (Renderer)instance.renderer, (Input)new LWJGLInput());
/*     */       
/*  55 */       themeURL = new URL("classloader", "", -1, themename, new URLStreamHandler()
/*     */           {
/*     */             protected URLConnection openConnection(URL paramURL) throws IOException
/*     */             {
/*  59 */               String file = paramURL.getFile();
/*  60 */               if (file.startsWith("/")) {
/*  61 */                 file = file.substring(1);
/*     */               }
/*     */               
/*  64 */               return GuiWidgetScreen.class.getClassLoader().getResource(file).openConnection();
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */       
/*  70 */       instance.theme = ThemeManager.createThemeManager(themeURL, (Renderer)instance.renderer);
/*  71 */       if (instance.theme == null) {
/*  72 */         throw new RuntimeException("I don't think you installed the theme correctly ...");
/*     */       }
/*  74 */       instance.setTheme("");
/*  75 */       instance.gui.applyTheme(instance.theme);
/*  76 */       instance.minecraftInstance = ModSettings.getMcinst();
/*  77 */       instance.screenSize = new ScaledResolution(instance.minecraftInstance);
/*     */     }
/*  79 */     catch (Throwable e) {
/*  80 */       e.printStackTrace();
/*  81 */       RuntimeException e2 = new RuntimeException("error loading theme");
/*  82 */       e2.initCause(e);
/*  83 */       throw e2;
/*     */     } 
/*  85 */     return instance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public Widget currentWidget = null;
/*     */ 
/*     */ 
/*     */   
/*  95 */   public GUI gui = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public Minecraft minecraftInstance;
/*     */ 
/*     */ 
/*     */   
/* 103 */   public LWJGLRenderer renderer = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public ScaledResolution screenSize = null;
/*     */ 
/*     */ 
/*     */   
/* 112 */   public ThemeManager theme = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void layout() {
/* 123 */     this.screenSize = new ScaledResolution(this.minecraftInstance);
/* 124 */     if (this.currentWidget != null) {
/* 125 */       screenwidth = this.screenSize.getScaledWidth();
/* 126 */       screenheight = this.screenSize.getScaledHeight();
/* 127 */       this.currentWidget.setSize(screenwidth, screenheight);
/* 128 */       this.currentWidget.setPosition(0, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetScreen() {
/* 136 */     removeAllChildren();
/* 137 */     this.currentWidget = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScreen(Widget widget) {
/* 147 */     this.gui.resyncTimerAfterPause();
/* 148 */     this.gui.clearKeyboardState();
/* 149 */     this.gui.clearMouseState();
/* 150 */     removeAllChildren();
/* 151 */     add(widget);
/* 152 */     GuiApiFontHelper.resyncCustomFonts();
/*     */     
/* 154 */     this.currentWidget = widget;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\GuiWidgetScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */