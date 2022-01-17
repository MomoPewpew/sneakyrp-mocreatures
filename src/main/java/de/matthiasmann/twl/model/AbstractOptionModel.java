/*    */ package de.matthiasmann.twl.model;
/*    */ 
/*    */ import de.matthiasmann.twl.utils.CallbackSupport;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractOptionModel
/*    */   implements BooleanModel
/*    */ {
/*    */   Runnable[] callbacks;
/*    */   Runnable srcCallback;
/*    */   
/*    */   public void addCallback(Runnable callback) {
/* 57 */     if (callback == null) {
/* 58 */       throw new NullPointerException("callback");
/*    */     }
/* 60 */     if (this.callbacks == null) {
/* 61 */       this.srcCallback = new Runnable() {
/* 62 */           boolean lastValue = AbstractOptionModel.this.getValue();
/*    */           public void run() {
/* 64 */             boolean value = AbstractOptionModel.this.getValue();
/* 65 */             if (this.lastValue != value) {
/* 66 */               this.lastValue = value;
/* 67 */               CallbackSupport.fireCallbacks(AbstractOptionModel.this.callbacks);
/*    */             } 
/*    */           }
/*    */         };
/* 71 */       this.callbacks = new Runnable[] { callback };
/* 72 */       installSrcCallback(this.srcCallback);
/*    */     } else {
/* 74 */       this.callbacks = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.callbacks, callback, Runnable.class);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void removeCallback(Runnable callback) {
/* 79 */     this.callbacks = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, callback);
/* 80 */     if (this.callbacks == null && this.srcCallback != null) {
/* 81 */       removeSrcCallback(this.srcCallback);
/* 82 */       this.srcCallback = null;
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract void installSrcCallback(Runnable paramRunnable);
/*    */   
/*    */   protected abstract void removeSrcCallback(Runnable paramRunnable);
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\AbstractOptionModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */