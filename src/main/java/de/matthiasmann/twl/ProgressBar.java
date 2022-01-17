/*     */ package de.matthiasmann.twl;
/*     */ 
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
/*     */ 
/*     */ public class ProgressBar
/*     */   extends TextWidget
/*     */ {
/*  42 */   public static final AnimationState.StateKey STATE_VALUE_CHANGED = AnimationState.StateKey.get("valueChanged");
/*  43 */   public static final AnimationState.StateKey STATE_INDETERMINATE = AnimationState.StateKey.get("indeterminate");
/*     */   
/*     */   public static final float VALUE_INDETERMINATE = -1.0F;
/*     */   
/*     */   private Image progressImage;
/*     */   private float value;
/*     */   
/*     */   public ProgressBar() {
/*  51 */     getAnimationState().resetAnimationTime(STATE_VALUE_CHANGED);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getValue() {
/*  59 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIndeterminate() {
/*  67 */     if (this.value >= 0.0F) {
/*  68 */       this.value = -1.0F;
/*  69 */       AnimationState animationState = getAnimationState();
/*  70 */       animationState.setAnimationState(STATE_INDETERMINATE, true);
/*  71 */       animationState.resetAnimationTime(STATE_VALUE_CHANGED);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(float value) {
/*  82 */     if (value <= 0.0F) {
/*  83 */       value = 0.0F;
/*  84 */     } else if (value > 1.0F) {
/*  85 */       value = 1.0F;
/*     */     } 
/*  87 */     if (this.value != value) {
/*  88 */       this.value = value;
/*  89 */       AnimationState animationState = getAnimationState();
/*  90 */       animationState.setAnimationState(STATE_INDETERMINATE, false);
/*  91 */       animationState.resetAnimationTime(STATE_VALUE_CHANGED);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getText() {
/*  96 */     return (String)getCharSequence();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setText(String text) {
/* 104 */     setCharSequence(text);
/*     */   }
/*     */   
/*     */   public Image getProgressImage() {
/* 108 */     return this.progressImage;
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
/*     */   public void setProgressImage(Image progressImage) {
/* 123 */     this.progressImage = progressImage;
/*     */   }
/*     */   
/*     */   protected void applyThemeProgressBar(ThemeInfo themeInfo) {
/* 127 */     setProgressImage(themeInfo.getImage("progressImage"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 132 */     super.applyTheme(themeInfo);
/* 133 */     applyThemeProgressBar(themeInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paintWidget(GUI gui) {
/* 138 */     int width = getInnerWidth();
/* 139 */     int height = getInnerHeight();
/* 140 */     if (this.progressImage != null && this.value >= 0.0F) {
/* 141 */       int imageWidth = this.progressImage.getWidth();
/* 142 */       int progressWidth = width - imageWidth;
/* 143 */       int scaledWidth = (int)(progressWidth * this.value);
/* 144 */       if (scaledWidth < 0) {
/* 145 */         scaledWidth = 0;
/* 146 */       } else if (scaledWidth > progressWidth) {
/* 147 */         scaledWidth = progressWidth;
/*     */       } 
/* 149 */       this.progressImage.draw(getAnimationState(), getInnerX(), getInnerY(), imageWidth + scaledWidth, height);
/*     */     } 
/* 151 */     super.paintWidget(gui);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/* 156 */     int minWidth = super.getMinWidth();
/* 157 */     Image bg = getBackground();
/* 158 */     if (bg != null) {
/* 159 */       minWidth = Math.max(minWidth, bg.getWidth() + getBorderHorizontal());
/*     */     }
/* 161 */     return minWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/* 166 */     int minHeight = super.getMinHeight();
/* 167 */     Image bg = getBackground();
/* 168 */     if (bg != null) {
/* 169 */       minHeight = Math.max(minHeight, bg.getHeight() + getBorderVertical());
/*     */     }
/* 171 */     return minHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 176 */     int prefWidth = super.getPreferredInnerWidth();
/* 177 */     if (this.progressImage != null) {
/* 178 */       prefWidth = Math.max(prefWidth, this.progressImage.getWidth());
/*     */     }
/* 180 */     return prefWidth;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 185 */     int prefHeight = super.getPreferredInnerHeight();
/* 186 */     if (this.progressImage != null) {
/* 187 */       prefHeight = Math.max(prefHeight, this.progressImage.getHeight());
/*     */     }
/* 189 */     return prefHeight;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ProgressBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */