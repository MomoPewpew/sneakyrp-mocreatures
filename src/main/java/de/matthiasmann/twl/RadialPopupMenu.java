/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.ButtonModel;
/*     */ import java.util.ArrayList;
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
/*     */ public class RadialPopupMenu
/*     */   extends PopupWindow
/*     */ {
/*     */   private final ArrayList<RoundButton> buttons;
/*     */   private int radius;
/*     */   private int buttonRadius;
/*     */   private int mouseButton;
/*     */   int buttonRadiusSqr;
/*     */   
/*     */   public RadialPopupMenu(Widget owner) {
/*  50 */     super(owner);
/*  51 */     this.buttons = new ArrayList<RoundButton>();
/*     */   }
/*     */   
/*     */   public int getButtonRadius() {
/*  55 */     return this.buttonRadius;
/*     */   }
/*     */   
/*     */   public void setButtonRadius(int buttonRadius) {
/*  59 */     if (buttonRadius < 0) {
/*  60 */       throw new IllegalArgumentException("buttonRadius");
/*     */     }
/*  62 */     this.buttonRadius = buttonRadius;
/*  63 */     this.buttonRadiusSqr = buttonRadius * buttonRadius;
/*  64 */     invalidateLayout();
/*     */   }
/*     */   
/*     */   public int getRadius() {
/*  68 */     return this.radius;
/*     */   }
/*     */   
/*     */   public void setRadius(int radius) {
/*  72 */     if (radius < 0) {
/*  73 */       throw new IllegalArgumentException("radius");
/*     */     }
/*  75 */     this.radius = radius;
/*  76 */     invalidateLayout();
/*     */   }
/*     */   
/*     */   public int getMouseButton() {
/*  80 */     return this.mouseButton;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMouseButton(int mouseButton) {
/*  89 */     if (mouseButton < 0 || mouseButton > 1) {
/*  90 */       throw new IllegalArgumentException("mouseButton");
/*     */     }
/*  92 */     this.mouseButton = mouseButton;
/*  93 */     for (int i = 0, n = this.buttons.size(); i < n; i++) {
/*  94 */       ((RoundButton)this.buttons.get(i)).setMouseButton(mouseButton);
/*     */     }
/*     */   }
/*     */   
/*     */   public Button addButton(String theme, Runnable cb) {
/*  99 */     RoundButton button = new RoundButton();
/* 100 */     button.setTheme(theme);
/* 101 */     button.addCallback(cb);
/* 102 */     button.setMouseButton(this.mouseButton);
/* 103 */     addButton(button);
/* 104 */     return button;
/*     */   }
/*     */   
/*     */   public void removeButton(Button btn) {
/* 108 */     int idx = this.buttons.indexOf(btn);
/* 109 */     if (idx >= 0) {
/* 110 */       this.buttons.remove(idx);
/* 111 */       removeChild(btn);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void addButton(RoundButton button) {
/* 116 */     if (button == null) {
/* 117 */       throw new NullPointerException("button");
/*     */     }
/* 119 */     this.buttons.add(button);
/* 120 */     add(button);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean openPopup() {
/* 125 */     if (super.openPopup()) {
/* 126 */       if (bindMouseDrag(new Runnable() {
/*     */             public void run() {
/* 128 */               RadialPopupMenu.this.boundDragEventFinished();
/*     */             }
/*     */           })) {
/* 131 */         setAllButtonsPressed();
/*     */       }
/* 133 */       return true;
/*     */     } 
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean openPopupAt(int centerX, int centerY) {
/* 146 */     if (openPopup()) {
/* 147 */       adjustSize();
/* 148 */       Widget parent = getParent();
/* 149 */       int width = getWidth();
/* 150 */       int height = getHeight();
/* 151 */       setPosition(limit(centerX - width / 2, parent.getInnerX(), parent.getInnerRight() - width), limit(centerY - height / 2, parent.getInnerY(), parent.getInnerBottom() - height));
/*     */ 
/*     */       
/* 154 */       return true;
/*     */     } 
/* 156 */     return false;
/*     */   }
/*     */   
/*     */   protected static int limit(int value, int min, int max) {
/* 160 */     if (value < min) {
/* 161 */       return min;
/*     */     }
/* 163 */     if (value > max) {
/* 164 */       return max;
/*     */     }
/* 166 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean openPopup(Event evt) {
/* 177 */     if (evt.getType() == Event.Type.MOUSE_BTNDOWN) {
/* 178 */       setMouseButton(evt.getMouseButton());
/* 179 */       return openPopupAt(evt.getMouseX(), evt.getMouseY());
/*     */     } 
/* 181 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerWidth() {
/* 186 */     return 2 * (this.radius + this.buttonRadius);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredInnerHeight() {
/* 191 */     return 2 * (this.radius + this.buttonRadius);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTheme(ThemeInfo themeInfo) {
/* 196 */     super.applyTheme(themeInfo);
/* 197 */     applyThemeRadialPopupMenu(themeInfo);
/*     */   }
/*     */   
/*     */   protected void applyThemeRadialPopupMenu(ThemeInfo themeInfo) {
/* 201 */     setRadius(themeInfo.getParameter("radius", 40));
/* 202 */     setButtonRadius(themeInfo.getParameter("buttonRadius", 40));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void layout() {
/* 207 */     layoutRadial();
/*     */   }
/*     */   
/*     */   protected void layoutRadial() {
/* 211 */     int numButtons = this.buttons.size();
/* 212 */     if (numButtons > 0) {
/* 213 */       int centerX = getInnerX() + getInnerWidth() / 2;
/* 214 */       int centerY = getInnerY() + getInnerHeight() / 2;
/* 215 */       float toRad = 6.2831855F / numButtons;
/* 216 */       for (int i = 0; i < numButtons; i++) {
/* 217 */         float rad = i * toRad;
/* 218 */         int btnCenterX = centerX + (int)(this.radius * Math.sin(rad));
/* 219 */         int btnCenterY = centerY - (int)(this.radius * Math.cos(rad));
/* 220 */         RoundButton button = this.buttons.get(i);
/* 221 */         button.setPosition(btnCenterX - this.buttonRadius, btnCenterY - this.buttonRadius);
/* 222 */         button.setSize(2 * this.buttonRadius, 2 * this.buttonRadius);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void setAllButtonsPressed() {
/* 228 */     for (int i = 0, n = this.buttons.size(); i < n; i++) {
/* 229 */       ButtonModel model = ((RoundButton)this.buttons.get(i)).getModel();
/* 230 */       model.setPressed(true);
/* 231 */       model.setArmed(model.isHover());
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void boundDragEventFinished() {
/* 236 */     closePopup();
/*     */   }
/*     */   
/*     */   protected class RoundButton
/*     */     extends Button {
/*     */     public boolean isInside(int x, int y) {
/* 242 */       int dx = x - getX() + getWidth() / 2;
/* 243 */       int dy = y - getY() + getHeight() / 2;
/* 244 */       return (dx * dx + dy * dy <= RadialPopupMenu.this.buttonRadiusSqr);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\RadialPopupMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */