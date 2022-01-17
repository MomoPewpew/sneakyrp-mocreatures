/*    */ package de.matthiasmann.twl.input.lwjgl;
/*    */ 
/*    */ import de.matthiasmann.twl.GUI;
/*    */ import de.matthiasmann.twl.input.Input;
/*    */ import de.matthiasmann.twl.renderer.lwjgl.RenderScale;
/*    */ import org.lwjgl.input.Keyboard;
/*    */ import org.lwjgl.input.Mouse;
/*    */ import org.lwjgl.opengl.Display;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LWJGLInput
/*    */   implements Input
/*    */ {
/*    */   private boolean wasActive;
/*    */   
/*    */   public boolean pollInput(GUI gui) {
/* 50 */     boolean active = Display.isActive();
/* 51 */     if (this.wasActive && !active) {
/* 52 */       this.wasActive = false;
/* 53 */       return false;
/*    */     } 
/* 55 */     this.wasActive = active;
/*    */     
/* 57 */     if (Keyboard.isCreated()) {
/* 58 */       while (Keyboard.next()) {
/* 59 */         gui.handleKey(Keyboard.getEventKey(), Keyboard.getEventCharacter(), Keyboard.getEventKeyState());
/*    */       }
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 65 */     if (Mouse.isCreated()) {
/* 66 */       while (Mouse.next()) {
/* 67 */         gui.handleMouse(Mouse.getEventX() / RenderScale.scale, (gui.getHeight() - Mouse.getEventY() - 1) / RenderScale.scale, Mouse.getEventButton(), Mouse.getEventButtonState());
/*    */ 
/*    */ 
/*    */         
/* 71 */         int wheelDelta = Mouse.getEventDWheel();
/* 72 */         if (wheelDelta != 0) {
/* 73 */           gui.handleMouseWheel(wheelDelta / 120);
/*    */         }
/*    */       } 
/*    */     }
/* 77 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\input\lwjgl\LWJGLInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */