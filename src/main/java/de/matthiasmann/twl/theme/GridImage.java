/*     */ package de.matthiasmann.twl.theme;
/*     */ 
/*     */ import de.matthiasmann.twl.Border;
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.Image;
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
/*     */ public class GridImage
/*     */   implements Image, HasBorder
/*     */ {
/*     */   private final Image[] images;
/*     */   private final int[] weightX;
/*     */   private final int[] weightY;
/*     */   private final Border border;
/*     */   private final int width;
/*     */   private final int height;
/*     */   private final int[] columnWidth;
/*     */   private final int[] rowHeight;
/*     */   private final int weightSumX;
/*     */   private final int weightSumY;
/*     */   
/*     */   GridImage(Image[] images, int[] weightX, int[] weightY, Border border) {
/*  55 */     if (weightX.length == 0 || weightY.length == 0) {
/*  56 */       throw new IllegalArgumentException("zero dimension size not allowed");
/*     */     }
/*  58 */     assert weightX.length * weightY.length == images.length;
/*  59 */     this.images = images;
/*  60 */     this.weightX = weightX;
/*  61 */     this.weightY = weightY;
/*  62 */     this.border = border;
/*  63 */     this.columnWidth = new int[weightX.length];
/*  64 */     this.rowHeight = new int[weightY.length];
/*     */     
/*  66 */     int widthTmp = 0;
/*  67 */     for (int x = 0; x < weightX.length; x++) {
/*  68 */       int widthColumn = 0;
/*  69 */       for (int i = 0; i < weightY.length; i++) {
/*  70 */         widthColumn = Math.max(widthColumn, getImage(x, i).getWidth());
/*     */       }
/*  72 */       widthTmp += widthColumn;
/*  73 */       this.columnWidth[x] = widthColumn;
/*     */     } 
/*  75 */     this.width = widthTmp;
/*     */     
/*  77 */     int heightTmp = 0;
/*  78 */     for (int y = 0; y < weightY.length; y++) {
/*  79 */       int heightRow = 0;
/*  80 */       for (int i = 0; i < weightX.length; i++) {
/*  81 */         heightRow = Math.max(heightRow, getImage(i, y).getHeight());
/*     */       }
/*  83 */       heightTmp += heightRow;
/*  84 */       this.rowHeight[y] = heightRow;
/*     */     } 
/*  86 */     this.height = heightTmp;
/*     */     
/*  88 */     int tmpSumX = 0;
/*  89 */     for (int weight : weightX) {
/*  90 */       if (weight < 0) {
/*  91 */         throw new IllegalArgumentException("negative weight in weightX");
/*     */       }
/*  93 */       tmpSumX += weight;
/*     */     } 
/*  95 */     this.weightSumX = tmpSumX;
/*     */     
/*  97 */     int tmpSumY = 0;
/*  98 */     for (int weight : weightY) {
/*  99 */       if (weight < 0) {
/* 100 */         throw new IllegalArgumentException("negative weight in weightY");
/*     */       }
/* 102 */       tmpSumY += weight;
/*     */     } 
/* 104 */     this.weightSumY = tmpSumY;
/*     */     
/* 106 */     if (this.weightSumX <= 0) {
/* 107 */       throw new IllegalArgumentException("zero weightX not allowed");
/*     */     }
/* 109 */     if (this.weightSumY <= 0) {
/* 110 */       throw new IllegalArgumentException("zero weightX not allowed");
/*     */     }
/*     */   }
/*     */   
/*     */   private GridImage(Image[] images, GridImage src) {
/* 115 */     this.images = images;
/* 116 */     this.weightX = src.weightX;
/* 117 */     this.weightY = src.weightY;
/* 118 */     this.border = src.border;
/* 119 */     this.columnWidth = src.columnWidth;
/* 120 */     this.rowHeight = src.rowHeight;
/* 121 */     this.weightSumX = src.weightSumX;
/* 122 */     this.weightSumY = src.weightSumY;
/* 123 */     this.width = src.width;
/* 124 */     this.height = src.height;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 129 */     return this.width;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 133 */     return this.height;
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y) {
/* 137 */     draw(as, x, y, this.width, this.height);
/*     */   }
/*     */   
/*     */   public void draw(AnimationState as, int x, int y, int width, int height) {
/* 141 */     int deltaY = height - this.height;
/* 142 */     int remWeightY = this.weightSumY;
/* 143 */     for (int yi = 0, idx = 0; yi < this.weightY.length; yi++) {
/* 144 */       int heightRow = this.rowHeight[yi];
/* 145 */       if (remWeightY > 0) {
/* 146 */         int partY = deltaY * this.weightY[yi] / remWeightY;
/* 147 */         remWeightY -= this.weightY[yi];
/* 148 */         heightRow += partY;
/* 149 */         deltaY -= partY;
/*     */       } 
/*     */       
/* 152 */       int tmpX = x;
/* 153 */       int deltaX = width - this.width;
/* 154 */       int remWeightX = this.weightSumX;
/* 155 */       for (int xi = 0; xi < this.weightX.length; xi++, idx++) {
/* 156 */         int widthColumn = this.columnWidth[xi];
/* 157 */         if (remWeightX > 0) {
/* 158 */           int partX = deltaX * this.weightX[xi] / remWeightX;
/* 159 */           remWeightX -= this.weightX[xi];
/* 160 */           widthColumn += partX;
/* 161 */           deltaX -= partX;
/*     */         } 
/*     */         
/* 164 */         this.images[idx].draw(as, tmpX, y, widthColumn, heightRow);
/* 165 */         tmpX += widthColumn;
/*     */       } 
/*     */       
/* 168 */       y += heightRow;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Border getBorder() {
/* 173 */     return this.border;
/*     */   }
/*     */   
/*     */   public Image createTintedVersion(Color color) {
/* 177 */     Image[] newImages = new Image[this.images.length];
/* 178 */     for (int i = 0; i < newImages.length; i++) {
/* 179 */       newImages[i] = this.images[i].createTintedVersion(color);
/*     */     }
/* 181 */     return new GridImage(newImages, this);
/*     */   }
/*     */   
/*     */   private Image getImage(int x, int y) {
/* 185 */     return this.images[x + y * this.weightX.length];
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\theme\GridImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */