/*     */ package de.matthiasmann.twl;
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
/*     */ public class PopupWindow
/*     */   extends Container
/*     */ {
/*     */   private final Widget owner;
/*     */   private boolean closeOnClickedOutside = true;
/*     */   private boolean closeOnEscape = true;
/*     */   private Runnable requestCloseCallback;
/*     */   
/*     */   public PopupWindow(Widget owner) {
/*  73 */     if (owner == null) {
/*  74 */       throw new NullPointerException("owner");
/*     */     }
/*  76 */     this.owner = owner;
/*     */   }
/*     */   
/*     */   public Widget getOwner() {
/*  80 */     return this.owner;
/*     */   }
/*     */   
/*     */   public boolean isCloseOnClickedOutside() {
/*  84 */     return this.closeOnClickedOutside;
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
/*     */   public void setCloseOnClickedOutside(boolean closeOnClickedOutside) {
/*  97 */     this.closeOnClickedOutside = closeOnClickedOutside;
/*     */   }
/*     */   
/*     */   public boolean isCloseOnEscape() {
/* 101 */     return this.closeOnEscape;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCloseOnEscape(boolean closeOnEscape) {
/* 112 */     this.closeOnEscape = closeOnEscape;
/*     */   }
/*     */   
/*     */   public Runnable getRequestCloseCallback() {
/* 116 */     return this.requestCloseCallback;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRequestCloseCallback(Runnable requestCloseCallback) {
/* 126 */     this.requestCloseCallback = requestCloseCallback;
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
/*     */   public boolean openPopup() {
/* 140 */     GUI gui = this.owner.getGUI();
/* 141 */     if (gui != null) {
/*     */       
/* 143 */       setVisible(true);
/* 144 */       setEnabled(true);
/*     */       
/* 146 */       gui.openPopup(this);
/* 147 */       requestKeyboardFocus();
/* 148 */       focusFirstChild();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 153 */       return isOpen();
/*     */     } 
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void openPopupCentered() {
/* 166 */     if (openPopup()) {
/* 167 */       adjustSize();
/* 168 */       centerPopup();
/*     */     } 
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
/*     */   public void openPopupCentered(int width, int height) {
/* 184 */     if (openPopup()) {
/* 185 */       setSize(Math.min(getParent().getInnerWidth(), width), Math.min(getParent().getInnerHeight(), height));
/*     */       
/* 187 */       centerPopup();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void closePopup() {
/* 195 */     GUI gui = getGUI();
/* 196 */     if (gui != null) {
/*     */       
/* 198 */       gui.closePopup(this);
/* 199 */       this.owner.requestKeyboardFocus();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isOpen() {
/* 208 */     return (getParent() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void centerPopup() {
/* 218 */     Widget parent = getParent();
/* 219 */     if (parent != null) {
/* 220 */       setPosition(parent.getInnerX() + (parent.getInnerWidth() - getWidth()) / 2, parent.getInnerY() + (parent.getInnerHeight() - getHeight()) / 2);
/*     */     }
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
/*     */   public boolean bindMouseDrag(Runnable cb) {
/* 235 */     GUI gui = getGUI();
/* 236 */     if (gui != null) {
/* 237 */       return gui.bindDragEvent(this, cb);
/*     */     }
/* 239 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredWidth() {
/* 244 */     int parentWidth = (getParent() != null) ? getParent().getInnerWidth() : 32767;
/* 245 */     return Math.min(parentWidth, super.getPreferredWidth());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPreferredHeight() {
/* 250 */     int parentHeight = (getParent() != null) ? getParent().getInnerHeight() : 32767;
/* 251 */     return Math.min(parentHeight, super.getPreferredHeight());
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
/*     */   protected final boolean handleEvent(Event evt) {
/* 263 */     if (handleEventPopup(evt)) {
/* 264 */       return true;
/*     */     }
/* 266 */     if (evt.getType() == Event.Type.MOUSE_CLICKED && !isInside(evt.getMouseX(), evt.getMouseY())) {
/*     */       
/* 268 */       mouseClickedOutside(evt);
/* 269 */       return true;
/*     */     } 
/* 271 */     if (this.closeOnEscape && evt.isKeyPressedEvent() && evt.getKeyCode() == 1) {
/*     */ 
/*     */       
/* 274 */       requestPopupClose();
/* 275 */       return true;
/*     */     } 
/*     */     
/* 278 */     return true;
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
/*     */   protected boolean handleEventPopup(Event evt) {
/* 290 */     return super.handleEvent(evt);
/*     */   }
/*     */ 
/*     */   
/*     */   protected final boolean isMouseInside(Event evt) {
/* 295 */     return true;
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
/*     */   protected void requestPopupClose() {
/* 310 */     if (this.requestCloseCallback != null) {
/* 311 */       this.requestCloseCallback.run();
/*     */     } else {
/* 313 */       closePopup();
/*     */     } 
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
/*     */   protected void mouseClickedOutside(Event evt) {
/* 327 */     if (this.closeOnClickedOutside) {
/* 328 */       requestPopupClose();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void setParent(Widget parent) {
/* 334 */     if (!(parent instanceof GUI)) {
/* 335 */       throw new IllegalArgumentException("PopupWindow can't be used as child widget");
/*     */     }
/* 337 */     super.setParent(parent);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\PopupWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */