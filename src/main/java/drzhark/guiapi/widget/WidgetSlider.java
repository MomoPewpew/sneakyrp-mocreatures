/*    */ package drzhark.guiapi.widget;
/*    */ 
/*    */ import de.matthiasmann.twl.ValueAdjusterFloat;
/*    */ import de.matthiasmann.twl.model.FloatModel;
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
/*    */ public class WidgetSlider
/*    */   extends ValueAdjusterFloat
/*    */ {
/*    */   private boolean canEdit;
/*    */   
/*    */   public WidgetSlider(FloatModel f) {
/* 22 */     super(f);
/*    */ 
/*    */     
/* 25 */     this.canEdit = false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCanEdit(boolean value) {
/* 34 */     this.canEdit = value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean getCanEdit() {
/* 42 */     return this.canEdit;
/*    */   }
/*    */ 
/*    */   
/*    */   public void startEdit() {
/* 47 */     if (!getCanEdit()) {
/* 48 */       cancelEdit();
/*    */     } else {
/* 50 */       super.startEdit();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected String onEditStart() {
/* 56 */     return Float.toString(getValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetSlider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */