/*     */ package de.matthiasmann.twl;
/*     */ 
/*     */ import de.matthiasmann.twl.model.AutoCompletionDataSource;
/*     */ import de.matthiasmann.twl.model.AutoCompletionResult;
/*     */ import de.matthiasmann.twl.model.ListModel;
/*     */ import de.matthiasmann.twl.model.SimpleListModel;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Future;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EditFieldAutoCompletionWindow
/*     */   extends InfoWindow
/*     */ {
/*     */   private final ResultListModel listModel;
/*     */   private final ListBox<String> listBox;
/*     */   private boolean captureKeys;
/*     */   private boolean useInvokeAsync;
/*     */   private AutoCompletionDataSource dataSource;
/*     */   private ExecutorService executorService;
/*     */   private Future<AutoCompletionResult> future;
/*     */   
/*     */   public EditFieldAutoCompletionWindow(EditField editField) {
/*  66 */     super(editField);
/*     */     
/*  68 */     this.listModel = new ResultListModel();
/*  69 */     this.listBox = new ListBox<String>((ListModel<String>)this.listModel);
/*     */     
/*  71 */     add(this.listBox);
/*     */     
/*  73 */     Callbacks cb = new Callbacks();
/*  74 */     this.listBox.addCallback(cb);
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
/*     */   public EditFieldAutoCompletionWindow(EditField editField, AutoCompletionDataSource dataSource) {
/*  87 */     this(editField);
/*  88 */     this.dataSource = dataSource;
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
/*     */   public EditFieldAutoCompletionWindow(EditField editField, AutoCompletionDataSource dataSource, ExecutorService executorService) {
/* 106 */     this(editField);
/* 107 */     this.dataSource = dataSource;
/* 108 */     this.executorService = executorService;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final EditField getEditField() {
/* 116 */     return (EditField)getOwner();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ExecutorService getExecutorService() {
/* 124 */     return this.executorService;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUseInvokeAsync() {
/* 132 */     return this.useInvokeAsync;
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
/*     */ 
/*     */   
/*     */   public void setExecutorService(ExecutorService executorService) {
/* 153 */     this.executorService = executorService;
/* 154 */     this.useInvokeAsync = false;
/* 155 */     cancelFuture();
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
/*     */   
/*     */   public void setUseInvokeAsync(boolean useInvokeAsync) {
/* 175 */     this.executorService = null;
/* 176 */     this.useInvokeAsync = useInvokeAsync;
/* 177 */     cancelFuture();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AutoCompletionDataSource getDataSource() {
/* 185 */     return this.dataSource;
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
/*     */   public void setDataSource(AutoCompletionDataSource dataSource) {
/* 198 */     this.dataSource = dataSource;
/* 199 */     cancelFuture();
/* 200 */     if (isOpen()) {
/* 201 */       updateAutoCompletion();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateAutoCompletion() {
/* 210 */     cancelFuture();
/* 211 */     AutoCompletionResult result = null;
/* 212 */     if (this.dataSource != null) {
/* 213 */       EditField ef = getEditField();
/* 214 */       int cursorPos = ef.getCursorPos();
/* 215 */       if (cursorPos > 0) {
/* 216 */         String text = ef.getText();
/* 217 */         GUI gui = ef.getGUI();
/* 218 */         if (this.listModel.result != null) {
/* 219 */           result = this.listModel.result.refine(text, cursorPos);
/*     */         }
/* 221 */         if (result == null) {
/* 222 */           if (gui != null && (this.useInvokeAsync || this.executorService != null)) {
/* 223 */             this.future = (this.useInvokeAsync ? gui.executorService : this.executorService).submit(new AsyncQuery(gui, this.dataSource, text, cursorPos, this.listModel.result));
/*     */           } else {
/*     */             
/*     */             try {
/* 227 */               result = this.dataSource.collectSuggestions(text, cursorPos, this.listModel.result);
/* 228 */             } catch (Exception ex) {
/* 229 */               reportQueryException(ex);
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/* 235 */     updateAutoCompletion(result);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopAutoCompletion() {
/* 244 */     this.listModel.setResult(null);
/* 245 */     installAutoCompletion();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void infoWindowClosed() {
/* 250 */     stopAutoCompletion();
/*     */   }
/*     */   
/*     */   protected void updateAutoCompletion(AutoCompletionResult results) {
/* 254 */     this.listModel.setResult(results);
/* 255 */     this.captureKeys = false;
/* 256 */     installAutoCompletion();
/*     */   }
/*     */   
/*     */   void checkFuture() {
/* 260 */     if (this.future != null && 
/* 261 */       this.future.isDone()) {
/* 262 */       AutoCompletionResult result = null;
/*     */       try {
/* 264 */         result = this.future.get();
/* 265 */       } catch (InterruptedException ex) {
/*     */         
/* 267 */         Thread.currentThread().interrupt();
/* 268 */       } catch (ExecutionException ex) {
/* 269 */         reportQueryException(ex.getCause());
/*     */       } 
/* 271 */       this.future = null;
/* 272 */       updateAutoCompletion(result);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void cancelFuture() {
/* 278 */     if (this.future != null) {
/* 279 */       this.future.cancel(true);
/* 280 */       this.future = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void reportQueryException(Throwable ex) {
/* 285 */     Logger.getLogger(EditFieldAutoCompletionWindow.class.getName()).log(Level.SEVERE, "Exception while collecting auto completion results", ex);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean handleEvent(Event evt) {
/* 291 */     if (evt.isKeyEvent()) {
/* 292 */       if (this.captureKeys) {
/* 293 */         if (evt.isKeyPressedEvent())
/* 294 */         { switch (evt.getKeyCode())
/*     */           { case 28:
/*     */             case 156:
/* 297 */               return acceptAutoCompletion();
/*     */             
/*     */             case 1:
/* 300 */               stopAutoCompletion();
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
/* 326 */               return true;case 199: case 200: case 201: case 207: case 208: case 209: this.listBox.handleEvent(evt); return true;case 203: case 205: return false; }  if (evt.hasKeyChar() || evt.getKeyCode() == 14) { if (!acceptAutoCompletion()) stopAutoCompletion();  return false; }  }  return true;
/*     */       } 
/* 328 */       switch (evt.getKeyCode()) {
/*     */         case 200:
/*     */         case 208:
/*     */         case 209:
/* 332 */           this.listBox.handleEvent(evt);
/* 333 */           startCapture();
/* 334 */           return this.captureKeys;
/*     */         case 1:
/* 336 */           stopAutoCompletion();
/* 337 */           return false;
/*     */         case 57:
/* 339 */           if ((evt.getModifiers() & 0x24) != 0) {
/* 340 */             updateAutoCompletion();
/* 341 */             return true;
/*     */           } 
/* 343 */           return false;
/*     */       } 
/* 345 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 350 */     return super.handleEvent(evt);
/*     */   }
/*     */   
/*     */   boolean acceptAutoCompletion() {
/* 354 */     int selected = this.listBox.getSelected();
/* 355 */     if (selected >= 0) {
/* 356 */       EditField editField = getEditField();
/* 357 */       String text = this.listModel.getEntry(selected);
/* 358 */       int pos = this.listModel.getCursorPosForEntry(selected);
/*     */       
/* 360 */       editField.setText(text);
/* 361 */       if (pos >= 0 && pos < text.length()) {
/* 362 */         editField.setCursorPos(pos);
/*     */       }
/*     */       
/* 365 */       stopAutoCompletion();
/* 366 */       return true;
/*     */     } 
/* 368 */     return false;
/*     */   }
/*     */   
/*     */   private void startCapture() {
/* 372 */     this.captureKeys = true;
/* 373 */     installAutoCompletion();
/*     */   }
/*     */   
/*     */   private void installAutoCompletion() {
/* 377 */     if (this.listModel.getNumEntries() > 0) {
/* 378 */       openInfo();
/*     */     } else {
/* 380 */       this.captureKeys = false;
/* 381 */       closeInfo();
/*     */     } 
/*     */   }
/*     */   
/*     */   static class ResultListModel extends SimpleListModel<String> {
/*     */     AutoCompletionResult result;
/*     */     
/*     */     public void setResult(AutoCompletionResult result) {
/* 389 */       this.result = result;
/* 390 */       fireAllChanged();
/*     */     }
/*     */     
/*     */     public int getNumEntries() {
/* 394 */       return (this.result == null) ? 0 : this.result.getNumResults();
/*     */     }
/*     */     
/*     */     public String getEntry(int index) {
/* 398 */       return this.result.getResult(index);
/*     */     }
/*     */     
/*     */     public int getCursorPosForEntry(int index) {
/* 402 */       return this.result.getCursorPosForResult(index);
/*     */     }
/*     */   }
/*     */   
/*     */   class Callbacks implements CallbackWithReason<ListBox.CallbackReason> {
/*     */     public void callback(ListBox.CallbackReason reason) {
/* 408 */       switch (reason) {
/*     */         case MOUSE_DOUBLE_CLICK:
/* 410 */           EditFieldAutoCompletionWindow.this.acceptAutoCompletion();
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class AsyncQuery implements Callable<AutoCompletionResult>, Runnable {
/*     */     private final GUI gui;
/*     */     private final AutoCompletionDataSource dataSource;
/*     */     private final String text;
/*     */     private final int cursorPos;
/*     */     private final AutoCompletionResult prevResult;
/*     */     
/*     */     public AsyncQuery(GUI gui, AutoCompletionDataSource dataSource, String text, int cursorPos, AutoCompletionResult prevResult) {
/* 424 */       this.gui = gui;
/* 425 */       this.dataSource = dataSource;
/* 426 */       this.text = text;
/* 427 */       this.cursorPos = cursorPos;
/* 428 */       this.prevResult = prevResult;
/*     */     }
/*     */     
/*     */     public AutoCompletionResult call() throws Exception {
/* 432 */       AutoCompletionResult acr = this.dataSource.collectSuggestions(this.text, this.cursorPos, this.prevResult);
/* 433 */       this.gui.invokeLater(this);
/* 434 */       return acr;
/*     */     }
/*     */     
/*     */     public void run() {
/* 438 */       EditFieldAutoCompletionWindow.this.checkFuture();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\EditFieldAutoCompletionWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */