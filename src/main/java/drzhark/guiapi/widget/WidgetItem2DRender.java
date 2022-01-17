/*     */ package drzhark.guiapi.widget;
/*     */ 
/*     */ import de.matthiasmann.twl.GUI;
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import drzhark.guiapi.GuiWidgetScreen;
/*     */ import drzhark.guiapi.ModSettings;
/*     */ import java.lang.reflect.Field;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.RenderItem;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public class WidgetItem2DRender
/*     */   extends Widget
/*     */ {
/*     */   private static Field isDrawingField;
/*  30 */   private static RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
/*     */   
/*     */   static {
/*     */     try {
/*  34 */       isDrawingField = Tessellator.class.getDeclaredField("z");
/*  35 */       isDrawingField.setAccessible(true);
/*  36 */     } catch (Throwable e) {
/*     */       try {
/*  38 */         isDrawingField = Tessellator.class.getDeclaredField("isDrawing");
/*  39 */         isDrawingField.setAccessible(true);
/*  40 */       } catch (Throwable e2) {
/*  41 */         System.out
/*  42 */           .println("GuiAPI Warning: Unable to get Tessellator.isDrawing field! There will be a chance of crashes if you attempt to render a mod item!");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ItemStack renderStack;
/*     */   
/*  51 */   private int scaleType = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetItem2DRender() {
/*  57 */     this(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetItem2DRender(int renderID) {
/*  68 */     this(new ItemStack(Item.getItemById(renderID), 0, 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetItem2DRender(ItemStack renderStack) {
/*  77 */     setMinSize(16, 16);
/*  78 */     setTheme("/progressbar");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderID() {
/*  88 */     return (this.renderStack == null) ? 0 : Item.getIdFromItem(this.renderStack.getItem());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getRenderStack() {
/*  97 */     return this.renderStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getScaleType() {
/* 106 */     return this.scaleType;
/*     */   }
/*     */   
/*     */   private boolean isDrawing(Tessellator tesselator) {
/* 110 */     if (isDrawingField == null) {
/* 111 */       return false;
/*     */     }
/*     */     try {
/* 114 */       isDrawingField.getBoolean(tesselator);
/* 115 */     } catch (Throwable throwable) {}
/*     */ 
/*     */     
/* 118 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintWidget(GUI gui) {
/*     */     int size;
/* 124 */     Minecraft minecraft = ModSettings.getMcinst();
/*     */     
/* 126 */     int x = getX();
/* 127 */     int y = getY();
/* 128 */     float scalex = 1.0F;
/* 129 */     float scaley = 1.0F;
/*     */     
/* 131 */     int maxWidth = getInnerWidth() - 4;
/* 132 */     int maxHeight = getInnerHeight() - 4;
/*     */     
/* 134 */     int scale = getScaleType();
/*     */     
/* 136 */     if (scale == -1 && (maxWidth < 16 || maxHeight < 16)) {
/* 137 */       scale = 0;
/*     */     }
/*     */     
/* 140 */     switch (scale) {
/*     */       
/*     */       case 0:
/* 143 */         size = 0;
/* 144 */         if (maxWidth > maxHeight) {
/* 145 */           size = maxHeight;
/*     */         } else {
/* 147 */           size = maxWidth;
/*     */         } 
/*     */         
/* 150 */         x += (maxWidth - size) / 2;
/* 151 */         y += (maxHeight - size) / 2;
/*     */         
/* 153 */         scalex = size / 16.0F;
/* 154 */         scaley = scalex;
/* 155 */         x = (int)(x / scalex);
/* 156 */         y = (int)(y / scaley);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case -1:
/* 162 */         size = maxWidth - 16;
/* 163 */         x += size / 2;
/*     */         
/* 165 */         size = maxHeight - 16;
/* 166 */         y += size / 2;
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 172 */         scalex = maxWidth / 16.0F;
/* 173 */         scaley = maxHeight / 16.0F;
/* 174 */         x = (int)(x / scalex);
/* 175 */         y = (int)(y / scaley);
/*     */         break;
/*     */       
/*     */       default:
/* 179 */         throw new IndexOutOfBoundsException("Scale Type is out of bounds! This should never happen!");
/*     */     } 
/*     */     
/* 182 */     x += 2;
/* 183 */     y++;
/*     */     
/* 185 */     if (minecraft == null || getRenderStack() == null || getRenderStack().getItem() == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 190 */     GuiWidgetScreen screen = GuiWidgetScreen.getInstance();
/* 191 */     screen.renderer.pauseRendering();
/*     */     
/* 193 */     screen.renderer.setClipRect();
/* 194 */     GL11.glEnable(3089);
/* 195 */     GL11.glPushMatrix();
/* 196 */     GL11.glDisable(3042);
/* 197 */     GL11.glEnable(32826);
/* 198 */     RenderHelper.enableStandardItemLighting();
/* 199 */     RenderHelper.enableGUIStandardItemLighting();
/*     */     
/* 201 */     GL11.glScalef(scalex, scaley, 1.0F);
/*     */     
/* 203 */     ItemStack stack = getRenderStack();
/*     */     
/* 205 */     if (isDrawing(Tessellator.getInstance()))
/*     */     {
/* 207 */       setDrawing(Tessellator.getInstance(), false);
/*     */     }
/* 209 */     int stackBeforeDraw = GL11.glGetInteger(2979);
/*     */     
/*     */     try {
/* 212 */       itemRenderer.renderItemIntoGUI(stack, x, y);
/* 213 */       if (isDrawing(Tessellator.getInstance()))
/*     */       {
/* 215 */         setDrawing(Tessellator.getInstance(), false);
/*     */       }
/* 217 */       itemRenderer.renderItemOverlayIntoGUI(minecraft.fontRenderer, stack, x, y, null);
/* 218 */       if (isDrawing(Tessellator.getInstance()))
/*     */       {
/* 220 */         setDrawing(Tessellator.getInstance(), false);
/*     */       }
/* 222 */     } catch (Throwable e) {
/* 223 */       if (isDrawing(Tessellator.getInstance()))
/*     */       {
/* 225 */         setDrawing(Tessellator.getInstance(), false);
/*     */       }
/*     */     } 
/*     */     
/* 229 */     int stackAfterDraw = GL11.glGetInteger(2979);
/*     */     
/* 231 */     if (stackBeforeDraw != stackAfterDraw)
/*     */     {
/* 233 */       for (int i = 0; i < stackAfterDraw - stackBeforeDraw; i++) {
/* 234 */         GL11.glPopMatrix();
/*     */       }
/*     */     }
/*     */     
/* 238 */     RenderHelper.disableStandardItemLighting();
/* 239 */     GL11.glDisable(32826);
/*     */     
/* 241 */     GL11.glPopMatrix();
/* 242 */     GL11.glDisable(3089);
/* 243 */     screen.renderer.resumeRendering();
/*     */   }
/*     */   
/*     */   private void setDrawing(Tessellator tesselator, boolean state) {
/* 247 */     if (isDrawingField == null) {
/*     */       return;
/*     */     }
/*     */     try {
/* 251 */       isDrawingField.setBoolean(tesselator, state);
/* 252 */     } catch (Throwable throwable) {}
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScaleType(int scaleType) {
/* 304 */     if (scaleType > 1) {
/* 305 */       scaleType = 1;
/*     */     }
/* 307 */     if (scaleType < -1) {
/* 308 */       scaleType = -1;
/*     */     }
/* 310 */     this.scaleType = scaleType;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetItem2DRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */