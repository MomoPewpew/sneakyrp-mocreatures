/*     */ package drzhark.guiapi;
/*     */ 
/*     */ import de.matthiasmann.twl.Button;
/*     */ import de.matthiasmann.twl.TextArea;
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import de.matthiasmann.twl.model.ButtonModel;
/*     */ import de.matthiasmann.twl.model.SimpleButtonModel;
/*     */ import de.matthiasmann.twl.textarea.HTMLTextAreaModel;
/*     */ import de.matthiasmann.twl.textarea.SimpleTextAreaModel;
/*     */ import de.matthiasmann.twl.textarea.TextAreaModel;
/*     */ import drzhark.guiapi.widget.WidgetSimplewindow;
/*     */ import drzhark.guiapi.widget.WidgetSinglecolumn;
/*     */ import java.util.AbstractMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiApiHelper
/*     */ {
/*  35 */   public static final ModAction backModAction = new ModAction(GuiModScreen.class, "back", new Class[0]); static {
/*  36 */     backModAction.setTag("Helper Back ModAction");
/*  37 */   } public static final ModAction clickModAction = new ModAction(GuiModScreen.class, "clicksound", new Class[0]); static {
/*  38 */     clickModAction.setTag("Helper ClickSound ModAction");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList<AbstractMap.SimpleEntry<String, ModAction>> buttonInfo_;
/*     */ 
/*     */ 
/*     */   
/*     */   private String displayText_;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static GuiApiHelper createChoiceMenu(String displayText) {
/*  54 */     return new GuiApiHelper(displayText);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Widget createChoiceMenu(String displayText, Boolean showBackButton, Boolean autoBack, Object... args) {
/*  73 */     if (args.length % 2 == 1) {
/*  74 */       throw new IllegalArgumentException("Arguments not in correct format. You need to have an even number of arguments, in the form of String, ModAction for each button.");
/*     */     }
/*     */     
/*  77 */     GuiApiHelper helper = new GuiApiHelper(displayText);
/*     */     try {
/*  79 */       for (int i = 0; i < args.length; i += 2) {
/*  80 */         helper.addButton((String)args[i], (ModAction)args[i + 1], autoBack);
/*     */       }
/*  82 */     } catch (Throwable e) {
/*  83 */       throw new IllegalArgumentException("Arguments not in correct format. You need to have an even number of arguments, in the form of String, ModAction for each button.", e);
/*     */     } 
/*     */ 
/*     */     
/*  87 */     return (Widget)helper.genWidget(showBackButton);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Widget createChoiceMenu(String displayText, Boolean showBackButton, Boolean autoBack, String[] buttonTexts, ModAction[] buttonActions) {
/* 106 */     if (buttonTexts.length != buttonActions.length) {
/* 107 */       throw new IllegalArgumentException("Arguments not in correct format. buttonTexts needs to be the same size as buttonActions.");
/*     */     }
/* 109 */     GuiApiHelper helper = new GuiApiHelper(displayText);
/* 110 */     for (int i = 0; i < buttonTexts.length; i += 2) {
/* 111 */       helper.addButton(buttonTexts[i], buttonActions[i], autoBack);
/*     */     }
/* 113 */     return (Widget)helper.genWidget(showBackButton);
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
/*     */   public static Button makeButton(String displayText, ModAction action, Boolean addClick) {
/* 126 */     SimpleButtonModel simplebuttonmodel = new SimpleButtonModel();
/* 127 */     if (addClick.booleanValue()) {
/* 128 */       action = action.mergeAction(new ModAction[] { clickModAction });
/*     */     }
/* 130 */     simplebuttonmodel.addActionCallback(action);
/* 131 */     Button button = new Button((ButtonModel)simplebuttonmodel);
/* 132 */     button.setText(displayText);
/* 133 */     return button;
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
/*     */   
/*     */   public static Button makeButton(String displayText, String methodName, Object me, Boolean addClick) {
/* 147 */     return makeButton(displayText, new ModAction(me, methodName, new Class[0]), addClick);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Button makeButton(String displayText, String methodName, Object me, Boolean addClick, Class[] classes, Object... arguments) {
/* 164 */     return makeButton(displayText, (new ModAction(me, methodName, classes)).setDefaultArguments(arguments), addClick);
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
/*     */   public static TextArea makeTextArea(String text, Boolean htmlMode) {
/* 177 */     if (!htmlMode.booleanValue()) {
/* 178 */       SimpleTextAreaModel simpleTextAreaModel = new SimpleTextAreaModel();
/* 179 */       simpleTextAreaModel.setText(text, false);
/* 180 */       return new TextArea((TextAreaModel)simpleTextAreaModel);
/*     */     } 
/* 182 */     HTMLTextAreaModel model = new HTMLTextAreaModel();
/* 183 */     model.setHtml(text);
/* 184 */     return new TextArea((TextAreaModel)model);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Widget makeTextDisplayAndGoBack(String titleText, String displayText, String buttonText, Boolean htmlMode) {
/* 202 */     WidgetSinglecolumn widget = new WidgetSinglecolumn(new Widget[0]);
/* 203 */     widget.add((Widget)makeTextArea(displayText, htmlMode));
/* 204 */     widget.overrideHeight = false;
/* 205 */     WidgetSimplewindow window = new WidgetSimplewindow((Widget)widget, titleText);
/* 206 */     window.backButton.setText(buttonText);
/* 207 */     return (Widget)window;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setTextAreaText(TextArea textArea, String text) {
/* 218 */     TextAreaModel model = textArea.getModel();
/* 219 */     if (model instanceof SimpleTextAreaModel) {
/* 220 */       ((SimpleTextAreaModel)model).setText(text, false);
/*     */     } else {
/* 222 */       ((HTMLTextAreaModel)model).setHtml(text);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private GuiApiHelper(String displayText) {
/* 238 */     this.displayText_ = displayText;
/* 239 */     this.buttonInfo_ = new ArrayList<>();
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
/*     */   public void addButton(String text, ModAction action, Boolean mergeBack) {
/* 252 */     ModAction buttonAction = action;
/* 253 */     if (mergeBack.booleanValue()) {
/* 254 */       buttonAction = buttonAction.mergeAction(new ModAction[] { backModAction });
/* 255 */       buttonAction.setTag("Button '" + text + "' with back.");
/*     */     } 
/* 257 */     this.buttonInfo_.add(new AbstractMap.SimpleEntry<>(text, buttonAction));
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
/*     */   
/*     */   public void addButton(String text, String methodName, Object me, Boolean mergeBack) {
/* 271 */     addButton(text, new ModAction(me, methodName, new Class[0]), mergeBack);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addButton(String text, String methodName, Object me, Class[] types, Boolean mergeBack, Object... arguments) {
/* 289 */     addButton(text, (new ModAction(me, methodName, types)).setDefaultArguments(arguments), mergeBack);
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
/*     */   public WidgetSimplewindow genWidget(Boolean showBackButton) {
/* 301 */     WidgetSinglecolumn widget = new WidgetSinglecolumn(new Widget[0]);
/* 302 */     TextArea textarea = makeTextArea(this.displayText_, Boolean.valueOf(false));
/* 303 */     widget.add((Widget)textarea);
/* 304 */     widget.heightOverrideExceptions.put(textarea, Integer.valueOf(0));
/* 305 */     for (AbstractMap.SimpleEntry<String, ModAction> entry : this.buttonInfo_) {
/* 306 */       widget.add((Widget)makeButton(entry.getKey(), entry.getValue(), Boolean.valueOf(true)));
/*     */     }
/* 308 */     WidgetSimplewindow window = new WidgetSimplewindow((Widget)widget, null, showBackButton);
/* 309 */     return window;
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\GuiApiHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */