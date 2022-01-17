/*    */ package de.matthiasmann.twl;
/*    */ 
/*    */ import de.matthiasmann.twl.model.BooleanModel;
/*    */ import de.matthiasmann.twl.model.ButtonModel;
/*    */ import de.matthiasmann.twl.model.ToggleButtonModel;
/*    */ import java.beans.PropertyChangeEvent;
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
/*    */ public class MenuCheckbox
/*    */   extends MenuElement
/*    */ {
/*    */   private BooleanModel model;
/*    */   
/*    */   public MenuCheckbox() {}
/*    */   
/*    */   public MenuCheckbox(BooleanModel model) {
/* 48 */     this.model = model;
/*    */   }
/*    */   
/*    */   public MenuCheckbox(String name, BooleanModel model) {
/* 52 */     super(name);
/* 53 */     this.model = model;
/*    */   }
/*    */   
/*    */   public BooleanModel getModel() {
/* 57 */     return this.model;
/*    */   }
/*    */   
/*    */   public void setModel(BooleanModel model) {
/* 61 */     BooleanModel oldModel = this.model;
/* 62 */     this.model = model;
/* 63 */     firePropertyChange("model", oldModel, model);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Widget createMenuWidget(MenuManager mm, int level) {
/* 68 */     MenuElement.MenuBtn btn = new MenuElement.MenuBtn()
/*    */       {
/*    */         public void propertyChange(PropertyChangeEvent evt) {
/* 71 */           super.propertyChange(evt);
/* 72 */           ((ToggleButtonModel)getModel()).setModel(MenuCheckbox.this.getModel());
/*    */         }
/*    */       };
/* 75 */     btn.setModel((ButtonModel)new ToggleButtonModel(getModel()));
/* 76 */     setWidgetTheme(btn, "checkbox");
/* 77 */     btn.addCallback(mm.getCloseCallback());
/* 78 */     return btn;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\MenuCheckbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */