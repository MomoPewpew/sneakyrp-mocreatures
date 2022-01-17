/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.BooleanModel;
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
/*     */ public class PositionAnimatedPanel
/*     */   extends Widget
/*     */ {
/*     */   private final Widget animatedWidget;
/*     */   private MouseSensitiveRectangle rect;
/*     */   
/*     */   public enum Direction
/*     */   {
/*  42 */     TOP(0, -1),
/*  43 */     LEFT(-1, 0),
/*  44 */     BOTTOM(0, 1),
/*  45 */     RIGHT(1, 0);
/*     */     final int x;
/*     */     final int y;
/*     */     
/*     */     Direction(int x, int y) {
/*  50 */       this.x = x;
/*  51 */       this.y = y;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  57 */   private Direction direction = Direction.TOP;
/*     */   
/*     */   private int moveSpeedIn;
/*     */   private int moveSpeedOut;
/*     */   private int auraSizeX;
/*     */   private int auraSizeY;
/*     */   private boolean forceVisible;
/*     */   private boolean forceJumps;
/*     */   private BooleanModel forceVisibleModel;
/*     */   private Runnable forceVisibleModelCallback;
/*     */   
/*     */   public PositionAnimatedPanel(Widget animatedWidget) {
/*  69 */     if (animatedWidget == null) {
/*  70 */       throw new NullPointerException("animatedWidget");
/*     */     }
/*     */     
/*  73 */     this.animatedWidget = animatedWidget;
/*     */     
/*  75 */     setClip(true);
/*  76 */     add(animatedWidget);
/*     */   }
/*     */   
/*     */   public Direction getHideDirection() {
/*  80 */     return this.direction;
/*     */   }
/*     */   
/*     */   public void setHideDirection(Direction direction) {
/*  84 */     if (direction == null) {
/*  85 */       throw new NullPointerException("direction");
/*     */     }
/*  87 */     this.direction = direction;
/*     */   }
/*     */   
/*     */   public int getMoveSpeedIn() {
/*  91 */     return this.moveSpeedIn;
/*     */   }
/*     */   
/*     */   public void setMoveSpeedIn(int moveSpeedIn) {
/*  95 */     this.moveSpeedIn = moveSpeedIn;
/*     */   }
/*     */   
/*     */   public int getMoveSpeedOut() {
/*  99 */     return this.moveSpeedOut;
/*     */   }
/*     */   
/*     */   public void setMoveSpeedOut(int moveSpeedOut) {
/* 103 */     this.moveSpeedOut = moveSpeedOut;
/*     */   }
/*     */   
/*     */   public int getAuraSizeX() {
/* 107 */     return this.auraSizeX;
/*     */   }
/*     */   
/*     */   public void setAuraSizeX(int auraSizeX) {
/* 111 */     this.auraSizeX = auraSizeX;
/*     */   }
/*     */   
/*     */   public int getAuraSizeY() {
/* 115 */     return this.auraSizeY;
/*     */   }
/*     */   
/*     */   public void setAuraSizeY(int auraSizeY) {
/* 119 */     this.auraSizeY = auraSizeY;
/*     */   }
/*     */   
/*     */   public boolean isForceVisible() {
/* 123 */     return this.forceVisible;
/*     */   }
/*     */   
/*     */   public void setForceVisible(boolean forceVisible) {
/* 127 */     this.forceVisible = forceVisible;
/* 128 */     if (this.forceVisibleModel != null) {
/* 129 */       this.forceVisibleModel.setValue(forceVisible);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isForceVisibleJumps() {
/* 134 */     return this.forceJumps;
/*     */   }
/*     */   
/*     */   public void setForceVisibleJumps(boolean forceJumps) {
/* 138 */     this.forceJumps = forceJumps;
/*     */   }
/*     */   
/*     */   public BooleanModel getForceVisibleModel() {
/* 142 */     return this.forceVisibleModel;
/*     */   }
/*     */   
/*     */   public void setForceVisibleModel(BooleanModel forceVisibleModel) {
/* 146 */     if (this.forceVisibleModel != forceVisibleModel) {
/* 147 */       if (this.forceVisibleModel != null) {
/* 148 */         this.forceVisibleModel.removeCallback(this.forceVisibleModelCallback);
/*     */       }
/* 150 */       this.forceVisibleModel = forceVisibleModel;
/* 151 */       if (forceVisibleModel != null) {
/* 152 */         if (this.forceVisibleModelCallback == null) {
/* 153 */           this.forceVisibleModelCallback = new ForceVisibleModelCallback();
/*     */         }
/* 155 */         forceVisibleModel.addCallback(this.forceVisibleModelCallback);
/* 156 */         syncWithForceVisibleModel();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isHidden() {
/* 162 */     int x = this.animatedWidget.getX();
/* 163 */     int y = this.animatedWidget.getY();
/* 164 */     return (x == getInnerX() + this.direction.x * this.animatedWidget.getWidth() && y == getInnerY() + this.direction.y * this.animatedWidget.getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinWidth() {
/* 170 */     return Math.max(super.getMinWidth(), this.animatedWidget.getMinWidth() + getBorderHorizontal());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinHeight() {
/* 175 */     return Math.max(super.getMinHeight(), this.animatedWidget.getMinHeight() + getBorderVertical());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 180 */     return this.animatedWidget.getPreferredWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 185 */     return this.animatedWidget.getPreferredHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 190 */     super.applyTheme(themeInfo);
/* 191 */     setHideDirection(themeInfo.<Direction>getParameter("hidedirection", Direction.TOP));
/* 192 */     setMoveSpeedIn(themeInfo.getParameter("speed.in", 2));
/* 193 */     setMoveSpeedOut(themeInfo.getParameter("speed.out", 1));
/* 194 */     setAuraSizeX(themeInfo.getParameter("aura.width", 50));
/* 195 */     setAuraSizeY(themeInfo.getParameter("aura.height", 50));
/* 196 */     setForceVisibleJumps(themeInfo.getParameter("forceVisibleJumps", false));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void afterAddToGUI(GUI gui) {
/* 201 */     super.afterAddToGUI(gui);
/* 202 */     this.rect = gui.createMouseSenitiveRectangle();
/* 203 */     setRectSize();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void beforeRemoveFromGUI(GUI gui) {
/* 208 */     super.beforeRemoveFromGUI(gui);
/* 209 */     this.rect = null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 214 */     this.animatedWidget.setSize(getInnerWidth(), getInnerHeight());
/* 215 */     setRectSize();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void positionChanged() {
/* 220 */     setRectSize();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void paint(GUI gui) {
/* 225 */     if (this.rect != null) {
/* 226 */       int x = getInnerX();
/* 227 */       int y = getInnerY();
/* 228 */       boolean forceOpen = (this.forceVisible || hasOpenPopups());
/* 229 */       if (forceOpen && this.forceJumps) {
/* 230 */         this.animatedWidget.setPosition(x, y);
/* 231 */       } else if (forceOpen || this.rect.isMouseOver()) {
/*     */         
/* 233 */         this.animatedWidget.setPosition(calcPosIn(this.animatedWidget.getX(), x, this.direction.x), calcPosIn(this.animatedWidget.getY(), y, this.direction.y));
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 238 */         this.animatedWidget.setPosition(calcPosOut(this.animatedWidget.getX(), x, this.direction.x * this.animatedWidget.getWidth()), calcPosOut(this.animatedWidget.getY(), y, this.direction.y * this.animatedWidget.getHeight()));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 243 */     super.paint(gui);
/*     */   }
/*     */   
/*     */   private void setRectSize() {
/* 247 */     if (this.rect != null) {
/* 248 */       this.rect.setXYWH(getX() - this.auraSizeX, getY() - this.auraSizeY, getWidth() + 2 * this.auraSizeX, getHeight() + 2 * this.auraSizeY);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private int calcPosIn(int cur, int org, int dir) {
/* 254 */     if (dir < 0) {
/* 255 */       return Math.min(org, cur + this.moveSpeedIn);
/*     */     }
/* 257 */     return Math.max(org, cur - this.moveSpeedIn);
/*     */   }
/*     */ 
/*     */   
/*     */   private int calcPosOut(int cur, int org, int dist) {
/* 262 */     if (dist < 0) {
/* 263 */       return Math.max(org + dist, cur - this.moveSpeedIn);
/*     */     }
/* 265 */     return Math.min(org + dist, cur + this.moveSpeedIn);
/*     */   }
/*     */ 
/*     */   
/*     */   void syncWithForceVisibleModel() {
/* 270 */     setForceVisible(this.forceVisibleModel.getValue());
/*     */   }
/*     */   
/*     */   class ForceVisibleModelCallback
/*     */     implements Runnable
/*     */   {
/*     */     public void run() {
/* 277 */       PositionAnimatedPanel.this.syncWithForceVisibleModel();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\PositionAnimatedPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */