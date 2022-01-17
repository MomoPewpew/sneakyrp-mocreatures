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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DraggableButton
/*     */   extends Button
/*     */ {
/*     */   private int dragStartX;
/*     */   private int dragStartY;
/*     */   private boolean dragging;
/*     */   private DragListener listener;
/*     */   
/*     */   public DraggableButton() {}
/*     */   
/*     */   public DraggableButton(AnimationState animState) {
/*  86 */     super(animState);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DraggableButton(AnimationState animState, boolean inherit) {
/*  96 */     super(animState, inherit);
/*     */   }
/*     */   
/*     */   public boolean isDragActive() {
/* 100 */     return this.dragging;
/*     */   }
/*     */   
/*     */   public DragListener getListener() {
/* 104 */     return this.listener;
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
/*     */   public void setListener(DragListener listener) {
/* 117 */     this.listener = listener;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean handleEvent(Event evt) {
/* 122 */     if (evt.isMouseEvent() && this.dragging) {
/* 123 */       if (evt.getType() == Event.Type.MOUSE_DRAGGED && 
/* 124 */         this.listener != null) {
/* 125 */         this.listener.dragged(evt.getMouseX() - this.dragStartX, evt.getMouseY() - this.dragStartY);
/*     */       }
/*     */       
/* 128 */       if (evt.isMouseDragEnd()) {
/* 129 */         stopDragging(evt);
/*     */       }
/* 131 */       return true;
/*     */     } 
/*     */     
/* 134 */     switch (evt.getType()) {
/*     */       case MOUSE_BTNDOWN:
/* 136 */         this.dragStartX = evt.getMouseX();
/* 137 */         this.dragStartY = evt.getMouseY();
/*     */         break;
/*     */       case MOUSE_DRAGGED:
/* 140 */         assert !this.dragging;
/* 141 */         this.dragging = true;
/* 142 */         getModel().setArmed(false);
/* 143 */         getModel().setPressed(true);
/* 144 */         if (this.listener != null) {
/* 145 */           this.listener.dragStarted();
/*     */         }
/* 147 */         return true;
/*     */     } 
/*     */     
/* 150 */     return super.handleEvent(evt);
/*     */   }
/*     */   
/*     */   private void stopDragging(Event evt) {
/* 154 */     if (this.listener != null) {
/* 155 */       this.listener.dragStopped();
/*     */     }
/* 157 */     this.dragging = false;
/* 158 */     getModel().setArmed(false);
/* 159 */     getModel().setPressed(false);
/* 160 */     getModel().setHover(isMouseInside(evt));
/*     */   }
/*     */   
/*     */   public static interface DragListener {
/*     */     void dragStarted();
/*     */     
/*     */     void dragged(int param1Int1, int param1Int2);
/*     */     
/*     */     void dragStopped();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\DraggableButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */