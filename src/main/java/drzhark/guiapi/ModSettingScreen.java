/*     */ package drzhark.guiapi;
/*     */ 
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import drzhark.guiapi.widget.WidgetClassicTwocolumn;
/*     */ import drzhark.guiapi.widget.WidgetSimplewindow;
/*     */ import drzhark.guiapi.widget.WidgetSinglecolumn;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModSettingScreen
/*     */ {
/*  22 */   public static String guiContext = "";
/*     */ 
/*     */ 
/*     */   
/*  26 */   public static ArrayList<ModSettingScreen> modScreens = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String buttonTitle;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String niceName;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Widget theWidget;
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetClassicTwocolumn widgetColumn;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModSettingScreen(String name) {
/*  51 */     this(name, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModSettingScreen(String nicename, String buttontitle) {
/*  63 */     modScreens.add(this);
/*  64 */     this.buttonTitle = buttontitle;
/*  65 */     this.niceName = nicename;
/*  66 */     this.widgetColumn = new WidgetClassicTwocolumn(new Widget[0]);
/*  67 */     this.theWidget = (Widget)new WidgetSimplewindow((Widget)this.widgetColumn, this.niceName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModSettingScreen(Widget widget, String buttontitle) {
/*  78 */     modScreens.add(this);
/*  79 */     this.buttonTitle = buttontitle;
/*  80 */     this.theWidget = widget;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void append(Widget newwidget) {
/*  89 */     if (this.widgetColumn != null) {
/*  90 */       this.widgetColumn.add(newwidget);
/*     */     } else {
/*  92 */       this.theWidget.add(newwidget);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(Widget child) {
/* 102 */     if (this.widgetColumn != null) {
/* 103 */       this.widgetColumn.removeChild(child);
/*     */     } else {
/* 105 */       this.theWidget.removeChild(child);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSingleColumn(Boolean value) {
/* 117 */     Boolean isSingle = Boolean.valueOf(WidgetSinglecolumn.class.isInstance(this.widgetColumn));
/* 118 */     if (isSingle == value) {
/*     */       return;
/*     */     }
/* 121 */     WidgetClassicTwocolumn w2 = value.booleanValue() ? (WidgetClassicTwocolumn)new WidgetSinglecolumn(new Widget[0]) : new WidgetClassicTwocolumn(new Widget[0]);
/* 122 */     for (int i = 0; i < this.widgetColumn.getNumChildren(); i++) {
/* 123 */       w2.add(this.widgetColumn.getChild(i));
/*     */     }
/* 125 */     this.widgetColumn = w2;
/* 126 */     this.theWidget = (Widget)new WidgetSimplewindow((Widget)this.widgetColumn, ((WidgetSimplewindow)this.theWidget).titleWidget.getText());
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\ModSettingScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */