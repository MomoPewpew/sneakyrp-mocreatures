/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.GraphLineModel;
/*     */ import de.matthiasmann.twl.model.GraphModel;
/*     */ import de.matthiasmann.twl.renderer.LineRenderer;
/*     */ import de.matthiasmann.twl.utils.TextUtil;
/*     */ import java.util.Arrays;
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
/*     */ public class Graph
/*     */   extends Widget
/*     */ {
/*     */   private final GraphArea area;
/*     */   GraphModel model;
/*     */   private ParameterMap themeLineStyles;
/*  48 */   private int sizeMultipleX = 1;
/*  49 */   private int sizeMultipleY = 1;
/*     */   
/*  51 */   LineStyle[] lineStyles = new LineStyle[8];
/*  52 */   private float[] renderXYBuffer = new float[128];
/*     */   
/*     */   public Graph() {
/*  55 */     this.area = new GraphArea();
/*  56 */     this.area.setClip(true);
/*  57 */     add(this.area);
/*     */   }
/*     */   private static final float EPSILON = 1.0E-4F;
/*     */   public Graph(GraphModel model) {
/*  61 */     this();
/*  62 */     setModel(model);
/*     */   }
/*     */   
/*     */   public GraphModel getModel() {
/*  66 */     return this.model;
/*     */   }
/*     */   
/*     */   public void setModel(GraphModel model) {
/*  70 */     this.model = model;
/*  71 */     invalidateLineStyles();
/*     */   }
/*     */   
/*     */   public int getSizeMultipleX() {
/*  75 */     return this.sizeMultipleX;
/*     */   }
/*     */   
/*     */   public void setSizeMultipleX(int sizeMultipleX) {
/*  79 */     if (sizeMultipleX < 1) {
/*  80 */       throw new IllegalArgumentException("sizeMultipleX must be >= 1");
/*     */     }
/*  82 */     this.sizeMultipleX = sizeMultipleX;
/*     */   }
/*     */   
/*     */   public int getSizeMultipleY() {
/*  86 */     return this.sizeMultipleY;
/*     */   }
/*     */   
/*     */   public void setSizeMultipleY(int sizeMultipleY) {
/*  90 */     if (sizeMultipleY < 1) {
/*  91 */       throw new IllegalArgumentException("sizeMultipleX must be >= 1");
/*     */     }
/*  93 */     this.sizeMultipleY = sizeMultipleY;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/*  98 */     super.applyTheme(themeInfo);
/*  99 */     applyThemeGraph(themeInfo);
/*     */   }
/*     */   
/*     */   protected void applyThemeGraph(ThemeInfo themeInfo) {
/* 103 */     this.themeLineStyles = themeInfo.getParameterMap("lineStyles");
/* 104 */     setSizeMultipleX(themeInfo.getParameter("sizeMultipleX", 1));
/* 105 */     setSizeMultipleY(themeInfo.getParameter("sizeMultipleY", 1));
/* 106 */     invalidateLineStyles();
/*     */   }
/*     */   
/*     */   protected void invalidateLineStyles() {
/* 110 */     Arrays.fill((Object[])this.lineStyles, (Object)null);
/*     */   }
/*     */   
/*     */   void syncLineStyles() {
/* 114 */     int numLines = this.model.getNumLines();
/* 115 */     if (this.lineStyles.length < numLines) {
/* 116 */       LineStyle[] newLineStyles = new LineStyle[numLines];
/* 117 */       System.arraycopy(this.lineStyles, 0, newLineStyles, 0, this.lineStyles.length);
/* 118 */       this.lineStyles = newLineStyles;
/*     */     } 
/*     */     
/* 121 */     for (int i = 0; i < numLines; i++) {
/* 122 */       GraphLineModel line = this.model.getLine(i);
/* 123 */       LineStyle style = this.lineStyles[i];
/* 124 */       if (style == null) {
/* 125 */         style = new LineStyle();
/* 126 */         this.lineStyles[i] = style;
/*     */       } 
/* 128 */       String visualStyle = TextUtil.notNull(line.getVisualStyleName());
/* 129 */       if (!style.name.equals(visualStyle)) {
/* 130 */         ParameterMap lineStyle = null;
/* 131 */         if (this.themeLineStyles != null) {
/* 132 */           lineStyle = this.themeLineStyles.getParameterMap(visualStyle);
/*     */         }
/* 134 */         style.setStyleName(visualStyle, lineStyle);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void renderLine(LineRenderer lineRenderer, GraphLineModel line, float minValue, float maxValue, LineStyle style) {
/* 143 */     int numPoints = line.getNumPoints();
/* 144 */     if (numPoints <= 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 149 */     if (this.renderXYBuffer.length < numPoints * 2)
/*     */     {
/* 151 */       this.renderXYBuffer = new float[numPoints * 2];
/*     */     }
/*     */     
/* 154 */     float[] xy = this.renderXYBuffer;
/*     */     
/* 156 */     float delta = maxValue - minValue;
/* 157 */     if (Math.abs(delta) < 1.0E-4F)
/*     */     {
/* 159 */       delta = copySign(1.0E-4F, delta);
/*     */     }
/*     */     
/* 162 */     float yscale = -getInnerHeight() / delta;
/* 163 */     float yoff = getInnerBottom();
/* 164 */     float xscale = getInnerWidth() / Math.max(1, numPoints - 1);
/* 165 */     float xoff = getInnerX();
/*     */     
/* 167 */     for (int i = 0; i < numPoints; i++) {
/* 168 */       float value = line.getPoint(i);
/* 169 */       xy[i * 2 + 0] = i * xscale + xoff;
/* 170 */       xy[i * 2 + 1] = (value - minValue) * yscale + yoff;
/*     */     } 
/*     */     
/* 173 */     if (numPoints == 1) {
/*     */ 
/*     */       
/* 176 */       xy[2] = xoff + xscale;
/* 177 */       xy[3] = xy[1];
/* 178 */       numPoints = 2;
/*     */     } 
/*     */     
/* 181 */     lineRenderer.drawLine(xy, numPoints, style.lineWidth, style.color, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static float copySign(float magnitude, float sign) {
/* 187 */     int rawMagnitude = Float.floatToRawIntBits(magnitude);
/* 188 */     int rawSign = Float.floatToRawIntBits(sign);
/* 189 */     int rawResult = rawMagnitude | rawSign & Integer.MIN_VALUE;
/* 190 */     return Float.intBitsToFloat(rawResult);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setSize(int width, int height) {
/* 195 */     return super.setSize(round(width, this.sizeMultipleX), round(height, this.sizeMultipleY));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int round(int value, int grid) {
/* 201 */     return value - value % grid;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 206 */     layoutChildFullInnerArea(this.area);
/*     */   }
/*     */   
/*     */   static class LineStyle {
/* 210 */     String name = "";
/* 211 */     Color color = Color.WHITE;
/* 212 */     float lineWidth = 1.0F;
/*     */     
/*     */     void setStyleName(String name, ParameterMap lineStyle) {
/* 215 */       this.name = name;
/* 216 */       if (lineStyle != null) {
/* 217 */         this.color = lineStyle.getParameter("color", Color.WHITE);
/* 218 */         this.lineWidth = Math.max(1.0E-4F, lineStyle.getParameter("width", 1.0F));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class GraphArea
/*     */     extends Widget
/*     */   {
/*     */     protected void paintWidget(GUI gui) {
/* 227 */       if (Graph.this.model != null) {
/* 228 */         Graph.this.syncLineStyles();
/* 229 */         LineRenderer lineRenderer = gui.getRenderer().getLineRenderer();
/*     */         
/* 231 */         int numLines = Graph.this.model.getNumLines();
/* 232 */         boolean independantScale = Graph.this.model.getScaleLinesIndependant();
/* 233 */         float minValue = Float.MAX_VALUE;
/* 234 */         float maxValue = -3.4028235E38F;
/* 235 */         if (independantScale) {
/* 236 */           for (int j = 0; j < numLines; j++) {
/* 237 */             GraphLineModel line = Graph.this.model.getLine(j);
/* 238 */             minValue = Math.min(minValue, line.getMinValue());
/* 239 */             maxValue = Math.max(maxValue, line.getMaxValue());
/*     */           } 
/*     */         }
/*     */         
/* 243 */         for (int i = 0; i < numLines; i++) {
/* 244 */           GraphLineModel line = Graph.this.model.getLine(i);
/* 245 */           Graph.LineStyle style = Graph.this.lineStyles[i];
/* 246 */           if (independantScale) {
/* 247 */             Graph.this.renderLine(lineRenderer, line, minValue, maxValue, style);
/*     */           } else {
/* 249 */             Graph.this.renderLine(lineRenderer, line, line.getMinValue(), line.getMaxValue(), style);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Graph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */