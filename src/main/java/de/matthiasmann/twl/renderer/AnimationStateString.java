/*    */ package de.matthiasmann.twl.renderer;
/*    */ 
/*    */ import de.matthiasmann.twl.AnimationState;
/*    */ 
/*    */ public class AnimationStateString
/*    */   extends AnimationState
/*    */ {
/*    */   String stateKey;
/*    */   
/*    */   public AnimationStateString(String key) {
/* 11 */     this.stateKey = key;
/*    */   }
/*    */   
/*    */   public boolean getAnimationState(AnimationState.StateKey stateKey) {
/* 15 */     return stateKey.getName().contains(this.stateKey);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\AnimationStateString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */