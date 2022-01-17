/*    */ package de.matthiasmann.twl;
/*    */ 
/*    */ import de.matthiasmann.twl.model.BooleanModel;
/*    */ import de.matthiasmann.twl.model.ButtonModel;
/*    */ import de.matthiasmann.twl.model.ToggleButtonModel;
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
/*    */ public class ToggleButton
/*    */   extends Button
/*    */ {
/*    */   public ToggleButton() {
/* 42 */     super((ButtonModel)new ToggleButtonModel());
/*    */   }
/*    */   
/*    */   public ToggleButton(BooleanModel model) {
/* 46 */     super((ButtonModel)new ToggleButtonModel(model));
/*    */   }
/*    */   
/*    */   public ToggleButton(String text) {
/* 50 */     this();
/* 51 */     setText(text);
/*    */   }
/*    */   
/*    */   public void setModel(BooleanModel model) {
/* 55 */     ((ToggleButtonModel)getModel()).setModel(model);
/*    */   }
/*    */   
/*    */   public boolean isActive() {
/* 59 */     return getModel().isSelected();
/*    */   }
/*    */   
/*    */   public void setActive(boolean active) {
/* 63 */     getModel().setSelected(active);
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ToggleButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */