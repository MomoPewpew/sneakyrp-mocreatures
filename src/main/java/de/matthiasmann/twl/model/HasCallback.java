/*    */ package de.matthiasmann.twl.model;
/*    */ 
/*    */ import de.matthiasmann.twl.utils.CallbackSupport;
/*    */ import de.matthiasmann.twl.utils.WithRunnableCallback;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HasCallback
/*    */   implements WithRunnableCallback
/*    */ {
/*    */   private Runnable[] callbacks;
/*    */   
/*    */   public void addCallback(Runnable callback) {
/* 52 */     this.callbacks = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.callbacks, callback, Runnable.class);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void removeCallback(Runnable callback) {
/* 60 */     this.callbacks = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, callback);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasCallbacks() {
/* 68 */     return (this.callbacks != null);
/*    */   }
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
/*    */   protected void doCallback() {
/* 82 */     CallbackSupport.fireCallbacks(this.callbacks);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\HasCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */