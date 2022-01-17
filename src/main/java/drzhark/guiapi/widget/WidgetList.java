/*    */ package drzhark.guiapi.widget;
/*    */ 
/*    */ import de.matthiasmann.twl.CallbackWithReason;
/*    */ import de.matthiasmann.twl.Label;
/*    */ import de.matthiasmann.twl.ListBox;
/*    */ import de.matthiasmann.twl.Widget;
/*    */ import de.matthiasmann.twl.model.ListModel;
/*    */ import de.matthiasmann.twl.model.SimpleChangableListModel;
/*    */ import de.matthiasmann.twl.utils.CallbackSupport;
/*    */ import drzhark.guiapi.setting.SettingList;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WidgetList
/*    */   extends WidgetSetting
/*    */   implements CallbackWithReason<ListBox.CallbackReason>
/*    */ {
/*    */   private Runnable[] callbacks;
/*    */   public Label displayLabel;
/*    */   public ListBox<String> listBox;
/*    */   public SimpleChangableListModel<String> listBoxModel;
/*    */   public SettingList settingReference;
/*    */   
/*    */   public WidgetList(SettingList setting, String title) {
/* 29 */     super(title);
/* 30 */     setTheme("");
/* 31 */     this.settingReference = setting;
/* 32 */     this.settingReference.displayWidget = this;
/* 33 */     if (title != null) {
/* 34 */       this.displayLabel = new Label();
/* 35 */       this.displayLabel.setText(this.niceName);
/* 36 */       add((Widget)this.displayLabel);
/*    */     } 
/*    */     
/* 39 */     this.listBoxModel = new SimpleChangableListModel((Collection)setting.get());
/* 40 */     this.listBox = new ListBox((ListModel)this.listBoxModel);
/* 41 */     add((Widget)this.listBox);
/* 42 */     this.listBox.addCallback(this);
/* 43 */     update();
/*    */   }
/*    */ 
/*    */   
/*    */   public void addCallback(Runnable callback) {
/* 48 */     this.callbacks = (Runnable[])CallbackSupport.addCallbackToList((Object[])this.callbacks, callback, Runnable.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void callback(ListBox.CallbackReason paramT) {
/* 53 */     CallbackSupport.fireCallbacks(this.callbacks);
/*    */   }
/*    */ 
/*    */   
/*    */   public void layout() {
/* 58 */     if (this.displayLabel != null) {
/* 59 */       this.displayLabel.setPosition(getX(), getY());
/* 60 */       int offset = this.displayLabel.computeTextHeight();
/* 61 */       this.displayLabel.setSize(getWidth(), offset);
/* 62 */       this.listBox.setPosition(getX(), getY() + offset);
/* 63 */       this.listBox.setSize(getWidth(), getHeight() - offset);
/*    */     } else {
/* 65 */       this.listBox.setPosition(getX(), getY());
/* 66 */       this.listBox.setSize(getWidth(), getHeight());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeCallback(Runnable callback) {
/* 72 */     this.callbacks = (Runnable[])CallbackSupport.removeCallbackFromList((Object[])this.callbacks, callback);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 77 */     this.listBoxModel.clear();
/* 78 */     this.listBoxModel.addElements((Collection)this.settingReference.get());
/*    */   }
/*    */ 
/*    */   
/*    */   public String userString() {
/* 83 */     String output = "";
/* 84 */     if (this.niceName != null) {
/* 85 */       output = this.niceName + ": ";
/*    */     }
/*    */     
/* 88 */     int sel = this.listBox.getSelected();
/* 89 */     String text = (sel != -1) ? (String)this.listBoxModel.getEntry(sel) : "NOTHING";
/*    */     
/* 91 */     output = output + String.format("%s (Entry %s) currently selected from %s items.", new Object[] { text, Integer.valueOf(sel), Integer.valueOf(((ArrayList)this.settingReference.get()).size()) });
/*    */     
/* 93 */     return output;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */