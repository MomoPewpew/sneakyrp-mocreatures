/*     */ package drzhark.guiapi;
/*     */ 
/*     */ import de.matthiasmann.twl.Color;
/*     */ import de.matthiasmann.twl.EditField;
/*     */ import de.matthiasmann.twl.TextWidget;
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import de.matthiasmann.twl.renderer.AnimationState;
/*     */ import de.matthiasmann.twl.renderer.AnimationStateString;
/*     */ import de.matthiasmann.twl.renderer.Font;
/*     */ import de.matthiasmann.twl.renderer.lwjgl.LWJGLFont;
/*     */ import drzhark.guiapi.widget.WidgetText;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ public class GuiApiFontHelper
/*     */ {
/*     */   public enum FontStates
/*     */   {
/*  32 */     disabled, error, hover, normal, textSelection, warning;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   private static Map<Widget, GuiApiFontHelper> customFontWidgets = new HashMap<>(); static {
/*     */     try {
/*  40 */       stateTable = new HashMap<>();
/*  41 */       FontStates[] states = FontStates.values();
/*  42 */       for (int i = 0; i < states.length; i++) {
/*  43 */         stateTable.put(states[i], new AnimationStateString(states[i].name()));
/*     */       }
/*  45 */     } catch (Throwable e) {
/*  46 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static Map<FontStates, AnimationStateString> stateTable;
/*     */   
/*     */   private LWJGLFont myFont;
/*     */ 
/*     */   
/*     */   public static void resyncCustomFonts() {
/*  57 */     for (Map.Entry<Widget, GuiApiFontHelper> entry : customFontWidgets.entrySet()) {
/*     */       
/*  59 */       GuiApiFontHelper font = entry.getValue();
/*  60 */       Widget widget = entry.getKey();
/*  61 */       if (widget instanceof TextWidget) {
/*  62 */         font.setFont((TextWidget)widget);
/*     */       }
/*  64 */       if (widget instanceof EditField) {
/*  65 */         font.setFont((EditField)widget);
/*     */       }
/*  67 */       if (widget instanceof WidgetText) {
/*  68 */         font.setFont((WidgetText)widget);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GuiApiFontHelper() {
/*  80 */     GuiWidgetScreen widgetScreen = GuiWidgetScreen.getInstance();
/*  81 */     LWJGLFont baseFont = (LWJGLFont)widgetScreen.theme.getDefaultFont();
/*  82 */     this.myFont = baseFont.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getColor(FontStates state) {
/*  90 */     return this.myFont.evalFontState((AnimationState)stateTable.get(state)).getColor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getLineThrough(FontStates state) {
/*  98 */     return this.myFont.evalFontState((AnimationState)stateTable.get(state)).getLineThrough();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOffsetX(FontStates state) {
/* 106 */     return this.myFont.evalFontState((AnimationState)stateTable.get(state)).getOffsetX();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOffsetY(FontStates state) {
/* 114 */     return this.myFont.evalFontState((AnimationState)stateTable.get(state)).getOffsetY();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getUnderline(FontStates state) {
/* 122 */     return this.myFont.evalFontState((AnimationState)stateTable.get(state)).getUnderline();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUnderlineOffset(FontStates state) {
/* 131 */     return this.myFont.evalFontState((AnimationState)stateTable.get(state)).getUnderlineOffset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColor(FontStates state, Color col) {
/* 139 */     this.myFont.evalFontState((AnimationState)stateTable.get(state)).setColor(col);
/* 140 */     resyncCustomFonts();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFont(EditField widget) {
/*     */     try {
/* 148 */       setFont((TextWidget)widget.textRenderer);
/* 149 */       customFontWidgets.put(widget, this);
/* 150 */     } catch (Throwable e) {
/* 151 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFont(TextWidget widget) {
/* 159 */     widget.setFont((Font)this.myFont);
/* 160 */     customFontWidgets.put(widget, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFont(WidgetText widget) {
/* 168 */     if (widget.displayLabel != null) {
/* 169 */       widget.displayLabel.setFont((Font)this.myFont);
/* 170 */       customFontWidgets.put(widget, this);
/*     */     } 
/* 172 */     setFont(widget.editField);
/* 173 */     customFontWidgets.put(widget, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineThrough(FontStates state, boolean val) {
/* 182 */     this.myFont.evalFontState((AnimationState)stateTable.get(state)).setLineThrough(val);
/* 183 */     resyncCustomFonts();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOffsetX(FontStates state, int i) {
/* 191 */     this.myFont.evalFontState((AnimationState)stateTable.get(state)).setOffsetX(i);
/* 192 */     resyncCustomFonts();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOffsetY(FontStates state, int i) {
/* 200 */     this.myFont.evalFontState((AnimationState)stateTable.get(state)).setOffsetY(i);
/* 201 */     resyncCustomFonts();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnderline(FontStates state, boolean val) {
/* 210 */     this.myFont.evalFontState((AnimationState)stateTable.get(state)).setUnderline(val);
/* 211 */     resyncCustomFonts();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUnderlineOffset(FontStates state, int i) {
/* 220 */     this.myFont.evalFontState((AnimationState)stateTable.get(state)).setUnderlineOffset(i);
/* 221 */     resyncCustomFonts();
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\GuiApiFontHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */