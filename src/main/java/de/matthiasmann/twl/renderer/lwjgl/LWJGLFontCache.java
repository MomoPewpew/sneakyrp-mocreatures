/*     */ package de.matthiasmann.twl.renderer.lwjgl;
/*     */ 
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.FontCache;
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
/*     */ public class LWJGLFontCache
/*     */   implements FontCache
/*     */ {
/*     */   private final LWJGLRenderer renderer;
/*     */   private final LWJGLFont font;
/*     */   private int id;
/*     */   private int width;
/*     */   private int height;
/*     */   private int[] multiLineInfo;
/*     */   private int numLines;
/*     */   
/*     */   LWJGLFontCache(LWJGLRenderer renderer, LWJGLFont font) {
/*  52 */     this.renderer = renderer;
/*  53 */     this.font = font;
/*  54 */     this.id = GL11.glGenLists(1);
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y) {
/*  58 */     if (this.id != 0) {
/*  59 */       LWJGLFont.FontState fontState = this.font.evalFontState(as);
/*  60 */       this.renderer.tintStack.setColor(fontState.color);
/*  61 */       GL11.glPushMatrix();
/*  62 */       GL11.glTranslatef((x + fontState.offsetX), (y + fontState.offsetY), 0.0F);
/*  63 */       GL11.glCallList(this.id);
/*  64 */       if (fontState.style != 0) {
/*  65 */         if (this.numLines > 0) {
/*  66 */           this.font.drawLines(fontState, 0, 0, this.multiLineInfo, this.numLines);
/*     */         } else {
/*  68 */           this.font.drawLine(fontState, 0, 0, this.width);
/*     */         } 
/*     */       }
/*  71 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void destroy() {
/*  76 */     if (this.id != 0) {
/*  77 */       GL11.glDeleteLists(this.id, 1);
/*  78 */       this.id = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   boolean startCompile() {
/*  83 */     if (this.id != 0) {
/*  84 */       GL11.glNewList(this.id, 4864);
/*  85 */       this.numLines = 0;
/*  86 */       return true;
/*     */     } 
/*  88 */     return false;
/*     */   }
/*     */   
/*     */   void endCompile(int width, int height) {
/*  92 */     GL11.glEndList();
/*  93 */     this.width = width;
/*  94 */     this.height = height;
/*     */   }
/*     */   
/*     */   int[] getMultiLineInfo(int numLines) {
/*  98 */     if (this.multiLineInfo == null || this.multiLineInfo.length < numLines) {
/*  99 */       this.multiLineInfo = new int[numLines];
/*     */     }
/* 101 */     this.numLines = numLines;
/* 102 */     return this.multiLineInfo;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 106 */     return this.height;
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 110 */     return this.width;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\lwjgl\LWJGLFontCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */