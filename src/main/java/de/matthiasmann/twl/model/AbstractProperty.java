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
/*    */ public abstract class AbstractProperty<T>
/*    */   implements Property<T>
/*    */ {
/*    */   private Runnable[] valueChangedCallbacks;
/*    */   
/*    */   public void addValueChangedCallback(Runnable cb) {
/* 45 */     this.valueChangedCallbacks = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.valueChangedCallbacks, cb, Runnable.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeValueChangedCallback(Runnable cb) {
/* 50 */     this.valueChangedCallbacks = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.valueChangedCallbacks, cb);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasValueChangedCallbacks() {
/* 55 */     return (this.valueChangedCallbacks != null);
/*    */   }
/*    */   
/*    */   protected void fireValueChangedCallback() {
/* 59 */     CallbackSupport.fireCallbacks(this.valueChangedCallbacks);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\AbstractProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */