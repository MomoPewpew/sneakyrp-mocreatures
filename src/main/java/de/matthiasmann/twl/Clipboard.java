/*    */ package de.matthiasmann.twl;
/*    */ 
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.datatransfer.DataFlavor;
/*    */ import java.awt.datatransfer.StringSelection;
/*    */ import java.awt.datatransfer.Transferable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Clipboard
/*    */ {
/*    */   public static String getClipboard() {
/*    */     try {
/* 50 */       java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 51 */       Transferable transferable = clipboard.getContents(null);
/* 52 */       if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
/* 53 */         return (String)transferable.getTransferData(DataFlavor.stringFlavor);
/*    */       }
/* 55 */     } catch (Exception ex) {
/* 56 */       ex.printStackTrace();
/*    */     } 
/* 58 */     return "";
/*    */   }
/*    */   
/*    */   public static void setClipboard(String str) {
/*    */     try {
/* 63 */       java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/*    */       
/* 65 */       StringSelection transferable = new StringSelection(str);
/* 66 */       clipboard.setContents(transferable, transferable);
/* 67 */     } catch (Exception ex) {
/* 68 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\Clipboard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */