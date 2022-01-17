/*    */ package drzhark.mocreatures.client.gui.helpers;
/*    */ 
/*    */ import drzhark.guiapi.setting.SettingList;
/*    */ import drzhark.mocreatures.configuration.MoCConfiguration;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class MoCSettingList
/*    */   extends SettingList
/*    */ {
/*    */   public String category;
/*    */   public MoCConfiguration config;
/*    */   
/*    */   public MoCSettingList(String title) {
/* 14 */     super(title, new ArrayList());
/*    */   }
/*    */   
/*    */   public MoCSettingList(String title, ArrayList<String> defaultvalue) {
/* 18 */     super(title, defaultvalue);
/*    */   }
/*    */   
/*    */   public MoCSettingList(MoCConfiguration config, String cat, String title, ArrayList<String> defaultvalue) {
/* 22 */     super(title, defaultvalue);
/* 23 */     this.category = cat;
/* 24 */     this.config = config;
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<String> get(String context) {
/* 29 */     if (this.values.get(context) != null)
/* 30 */       return (ArrayList<String>)this.values.get(context); 
/* 31 */     if (this.values.get("") != null) {
/* 32 */       return (ArrayList<String>)this.values.get("");
/*    */     }
/* 34 */     return (ArrayList<String>)this.defaultValue;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void set(ArrayList<String> v, String context) {
/* 40 */     this.values.put(context, v);
/* 41 */     if (this.parent != null) {
/* 42 */       ((MoCSettings)this.parent).save(context, this.backendName, this.category, this.config);
/*    */     }
/* 44 */     if (this.displayWidget != null)
/* 45 */       this.displayWidget.update(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\gui\helpers\MoCSettingList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */