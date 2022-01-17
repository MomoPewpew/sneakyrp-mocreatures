/*    */ package de.matthiasmann.twl.model;
/*    */ 
/*    */ import de.matthiasmann.twl.renderer.AnimationState;
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
/*    */ public abstract class AbstractTableColumnHeaderModel
/*    */   implements TableColumnHeaderModel
/*    */ {
/* 40 */   private static final AnimationState.StateKey[] EMPTY_STATE_ARRAY = new AnimationState.StateKey[0];
/*    */   
/*    */   public AnimationState.StateKey[] getColumnHeaderStates() {
/* 43 */     return EMPTY_STATE_ARRAY;
/*    */   }
/*    */   
/*    */   public boolean getColumnHeaderState(int column, int stateIdx) {
/* 47 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\AbstractTableColumnHeaderModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */