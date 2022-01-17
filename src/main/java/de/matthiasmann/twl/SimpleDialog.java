/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
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
/*     */ public class SimpleDialog
/*     */ {
/*  42 */   private String theme = "simpledialog";
/*     */   
/*     */   private String title;
/*     */   
/*     */   private Object msg;
/*     */   
/*     */   private Runnable cbOk;
/*     */   private Runnable cbCancel;
/*     */   private boolean focusCancelButton;
/*     */   
/*     */   public void setTheme(String theme) {
/*  53 */     if (theme == null) {
/*  54 */       throw new NullPointerException();
/*     */     }
/*  56 */     this.theme = theme;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/*  60 */     return this.title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitle(String title) {
/*  71 */     this.title = title;
/*     */   }
/*     */   
/*     */   public Object getMessage() {
/*  75 */     return this.msg;
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
/*     */   public void setMessage(Object msg) {
/*  87 */     this.msg = msg;
/*     */   }
/*     */   
/*     */   public Runnable getOkCallback() {
/*  91 */     return this.cbOk;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOkCallback(Runnable cbOk) {
/* 101 */     this.cbOk = cbOk;
/*     */   }
/*     */   
/*     */   public Runnable getCancelCallback() {
/* 105 */     return this.cbCancel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCancelCallback(Runnable cbCancel) {
/* 115 */     this.cbCancel = cbCancel;
/*     */   }
/*     */   
/*     */   public boolean isFocusCancelButton() {
/* 119 */     return this.focusCancelButton;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFocusCancelButton(boolean focusCancelButton) {
/* 129 */     this.focusCancelButton = focusCancelButton;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PopupWindow showDialog(Widget owner) {
/* 139 */     if (owner == null) {
/* 140 */       throw new NullPointerException("owner");
/*     */     }
/*     */     
/* 143 */     Widget msgWidget = null;
/*     */     
/* 145 */     if (this.msg instanceof Widget) {
/* 146 */       msgWidget = (Widget)this.msg;
/*     */ 
/*     */       
/* 149 */       if (msgWidget.getParent() instanceof DialogLayout && 
/* 150 */         msgWidget.getParent().getParent() instanceof PopupWindow) {
/* 151 */         PopupWindow prevPopup = (PopupWindow)msgWidget.getParent().getParent();
/* 152 */         if (!prevPopup.isOpen()) {
/* 153 */           msgWidget.getParent().removeChild(msgWidget);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 158 */       if (msgWidget.getParent() != null) {
/* 159 */         throw new IllegalArgumentException("message widget alreay in use");
/*     */       }
/* 161 */     } else if (this.msg instanceof String) {
/* 162 */       msgWidget = new Label((String)this.msg);
/* 163 */     } else if (this.msg != null) {
/* 164 */       Logger.getLogger(SimpleDialog.class.getName()).log(Level.WARNING, "Unsupported message type: {0}", this.msg.getClass());
/*     */     } 
/*     */     
/* 167 */     PopupWindow popupWindow = new PopupWindow(owner);
/*     */     
/* 169 */     Button btnOk = new Button("Ok");
/* 170 */     btnOk.setTheme("btnOk");
/* 171 */     btnOk.addCallback(new ButtonCB(popupWindow, this.cbOk));
/*     */     
/* 173 */     ButtonCB btnCancelCallback = new ButtonCB(popupWindow, this.cbCancel);
/* 174 */     popupWindow.setRequestCloseCallback(btnCancelCallback);
/*     */     
/* 176 */     Button btnCancel = new Button("Cancel");
/* 177 */     btnCancel.setTheme("btnCancel");
/* 178 */     btnCancel.addCallback(btnCancelCallback);
/*     */     
/* 180 */     DialogLayout layout = new DialogLayout();
/* 181 */     layout.setTheme("content");
/* 182 */     layout.setHorizontalGroup(layout.createParallelGroup());
/* 183 */     layout.setVerticalGroup(layout.createSequentialGroup());
/*     */     
/* 185 */     String vertPrevWidget = "top";
/*     */     
/* 187 */     if (this.title != null) {
/* 188 */       Label labelTitle = new Label(this.title);
/* 189 */       labelTitle.setTheme("title");
/* 190 */       labelTitle.setLabelFor(msgWidget);
/*     */       
/* 192 */       layout.getHorizontalGroup().addWidget(labelTitle);
/* 193 */       layout.getVerticalGroup().addWidget(labelTitle);
/* 194 */       vertPrevWidget = "title";
/*     */     } 
/*     */     
/* 197 */     if (msgWidget != null) {
/* 198 */       layout.getHorizontalGroup().addGroup(layout.createSequentialGroup().addGap("left-msg").addWidget(msgWidget).addGap("msg-right"));
/*     */ 
/*     */ 
/*     */       
/* 202 */       layout.getVerticalGroup().addGap(vertPrevWidget.concat("-msg")).addWidget(msgWidget).addGap("msg-buttons");
/*     */     } else {
/* 204 */       layout.getVerticalGroup().addGap(vertPrevWidget.concat("-buttons"));
/*     */     } 
/*     */     
/* 207 */     layout.getHorizontalGroup().addGroup(layout.createSequentialGroup().addGap("left-btnOk").addWidget(btnOk).addGap("btnOk-btnCancel").addWidget(btnCancel).addGap("btnCancel-right"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 213 */     layout.getVerticalGroup().addGroup(layout.createParallelGroup(new Widget[] { btnOk, btnCancel }));
/*     */     
/* 215 */     popupWindow.setTheme(this.theme);
/* 216 */     popupWindow.add(layout);
/* 217 */     popupWindow.openPopupCentered();
/*     */     
/* 219 */     if (this.focusCancelButton) {
/* 220 */       btnCancel.requestKeyboardFocus();
/* 221 */     } else if (msgWidget != null && msgWidget.canAcceptKeyboardFocus()) {
/* 222 */       msgWidget.requestKeyboardFocus();
/*     */     } 
/*     */     
/* 225 */     return popupWindow;
/*     */   }
/*     */   
/*     */   static class ButtonCB implements Runnable {
/*     */     private final PopupWindow popupWindow;
/*     */     private final Runnable cb;
/*     */     
/*     */     public ButtonCB(PopupWindow popupWindow, Runnable cb) {
/* 233 */       this.popupWindow = popupWindow;
/* 234 */       this.cb = cb;
/*     */     }
/*     */     
/*     */     public void run() {
/* 238 */       this.popupWindow.closePopup();
/* 239 */       if (this.cb != null)
/* 240 */         this.cb.run(); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\SimpleDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */