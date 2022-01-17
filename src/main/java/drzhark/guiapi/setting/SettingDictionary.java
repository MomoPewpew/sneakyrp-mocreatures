/*    */ package drzhark.guiapi.setting;
/*    */ 
/*    */ import drzhark.guiapi.ModSettings;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.Properties;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SettingDictionary
/*    */   extends Setting<Properties>
/*    */ {
/*    */   public SettingDictionary(String title) {
/* 19 */     this(title, new Properties());
/*    */   }
/*    */   
/*    */   public SettingDictionary(String title, Properties defaultvalue) {
/* 23 */     this.backendName = title;
/* 24 */     this.defaultValue = defaultvalue;
/* 25 */     this.values.put("", defaultvalue);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromString(String s, String context) {
/* 30 */     Properties prop = new Properties();
/*    */     try {
/* 32 */       prop.loadFromXML(new ByteArrayInputStream(s.getBytes("UTF-8")));
/* 33 */     } catch (Throwable e) {
/* 34 */       ModSettings.dbgout("Error reading SettingDictionary from context '" + context + "': " + e);
/*    */     } 
/* 36 */     this.values.put(context, prop);
/* 37 */     if (this.displayWidget != null) {
/* 38 */       this.displayWidget.update();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public Properties get(String context) {
/* 44 */     if (this.values.get(context) != null)
/* 45 */       return this.values.get(context); 
/* 46 */     if (this.values.get("") != null) {
/* 47 */       return this.values.get("");
/*    */     }
/* 49 */     return this.defaultValue;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void set(Properties v, String context) {
/* 55 */     this.values.put(context, v);
/* 56 */     if (this.parent != null) {
/* 57 */       this.parent.save(context);
/*    */     }
/* 59 */     if (this.displayWidget != null) {
/* 60 */       this.displayWidget.update();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString(String context) {
/*    */     try {
/* 67 */       Properties prop = get(context);
/* 68 */       ByteArrayOutputStream output = new ByteArrayOutputStream();
/* 69 */       prop.storeToXML(output, "GuiAPI SettingDictionary: DO NOT EDIT.");
/* 70 */       return output.toString("UTF-8");
/* 71 */     } catch (IOException e) {
/* 72 */       ModSettings.dbgout("Error writing SettingDictionary from context '" + context + "': " + e);
/* 73 */       return "";
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\setting\SettingDictionary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */