/*    */ package de.matthiasmann.twl;
/*    */ 
/*    */ import de.matthiasmann.twl.model.TableSelectionModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface TableSelectionManager
/*    */ {
/*    */   TableSelectionModel getSelectionModel();
/*    */   
/*    */   void setAssociatedTable(TableBase paramTableBase);
/*    */   
/*    */   SelectionGranularity getSelectionGranularity();
/*    */   
/*    */   boolean handleKeyStrokeAction(String paramString, Event paramEvent);
/*    */   
/*    */   boolean handleMouseEvent(int paramInt1, int paramInt2, Event paramEvent);
/*    */   
/*    */   boolean isRowSelected(int paramInt);
/*    */   
/*    */   boolean isCellSelected(int paramInt1, int paramInt2);
/*    */   
/*    */   int getLeadRow();
/*    */   
/*    */   int getLeadColumn();
/*    */   
/*    */   void modelChanged();
/*    */   
/*    */   void rowsInserted(int paramInt1, int paramInt2);
/*    */   
/*    */   void rowsDeleted(int paramInt1, int paramInt2);
/*    */   
/*    */   void columnInserted(int paramInt1, int paramInt2);
/*    */   
/*    */   void columnsDeleted(int paramInt1, int paramInt2);
/*    */   
/*    */   public enum SelectionGranularity
/*    */   {
/* 41 */     ROWS,
/* 42 */     CELLS;
/*    */   }
/*    */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\TableSelectionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */