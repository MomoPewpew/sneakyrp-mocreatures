/*     */ package drzhark.guiapi.widget;
/*     */ 
/*     */ import de.matthiasmann.twl.Button;
/*     */ import de.matthiasmann.twl.Label;
/*     */ import de.matthiasmann.twl.ScrollPane;
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import de.matthiasmann.twl.model.ButtonModel;
/*     */ import de.matthiasmann.twl.model.SimpleButtonModel;
/*     */ import drzhark.guiapi.GuiApiHelper;
/*     */ import drzhark.guiapi.ModAction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WidgetSimplewindow
/*     */   extends Widget
/*     */ {
/*  23 */   public Button backButton = new Button();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   public WidgetSingleRow buttonBar = new WidgetSingleRow(0, 0, new Widget[0]);
/*     */ 
/*     */ 
/*     */   
/*  32 */   public int hPadding = 30;
/*     */ 
/*     */ 
/*     */   
/*  36 */   public Widget mainWidget = new Widget();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public Widget scrollPane = null;
/*     */ 
/*     */ 
/*     */   
/*  45 */   public Label titleWidget = new Label();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public int vBottomPadding = 40;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public int vTopPadding = 30;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetSimplewindow() {
/*  62 */     this(new WidgetClassicTwocolumn(new Widget[0]), "", Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetSimplewindow(Widget w) {
/*  72 */     this(w, "", Boolean.valueOf(true));
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
/*     */   
/*     */   public WidgetSimplewindow(Widget w, String s) {
/*  85 */     this(w, s, Boolean.valueOf(true));
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
/*     */   
/*     */   public WidgetSimplewindow(Widget w, String s, Boolean showbackButton) {
/*  98 */     ScrollPane scrollpane = new ScrollPane(w);
/*  99 */     scrollpane.setFixed(ScrollPane.Fixed.HORIZONTAL);
/* 100 */     this.scrollPane = (Widget)scrollpane;
/* 101 */     this.mainWidget = w;
/* 102 */     setTheme("");
/* 103 */     init(showbackButton, s);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void init(Boolean showBack, String titleText) {
/* 114 */     if (titleText != null) {
/* 115 */       this.titleWidget = new Label(titleText);
/* 116 */       add((Widget)this.titleWidget);
/*     */     } else {
/* 118 */       this.vTopPadding = 10;
/*     */     } 
/* 120 */     if (showBack.booleanValue()) {
/* 121 */       this.backButton = new Button((ButtonModel)new SimpleButtonModel());
/* 122 */       this.backButton.getModel().addActionCallback((Runnable)GuiApiHelper.backModAction.mergeAction(new ModAction[] { GuiApiHelper.clickModAction }));
/* 123 */       this.backButton.setText("Back");
/* 124 */       this.buttonBar = new WidgetSingleRow(200, 20, new Widget[] { (Widget)this.backButton });
/* 125 */       add(this.buttonBar);
/*     */     } else {
/* 127 */       this.vBottomPadding = 0;
/*     */     } 
/* 129 */     add(this.scrollPane);
/*     */   }
/*     */ 
/*     */   
/*     */   public void layout() {
/* 134 */     if (this.buttonBar != null) {
/* 135 */       this.buttonBar.setSize(this.buttonBar.getPreferredWidth(), this.buttonBar.getPreferredHeight());
/* 136 */       this.buttonBar.setPosition(getWidth() / 2 - this.buttonBar.getPreferredWidth() / 2, 
/* 137 */           getHeight() - this.buttonBar.getPreferredHeight() + 4);
/*     */     } 
/* 139 */     if (this.titleWidget != null) {
/* 140 */       this.titleWidget.setPosition(getWidth() / 2 - this.titleWidget.computeTextWidth() / 2, 10);
/* 141 */       this.titleWidget.setSize(this.titleWidget.computeTextWidth(), this.titleWidget.computeTextHeight());
/*     */     } 
/* 143 */     this.scrollPane.setPosition(this.hPadding, this.vTopPadding);
/* 144 */     this.scrollPane.setSize(getWidth() - this.hPadding * 2, getHeight() - this.vTopPadding + this.vBottomPadding);
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\widget\WidgetSimplewindow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */