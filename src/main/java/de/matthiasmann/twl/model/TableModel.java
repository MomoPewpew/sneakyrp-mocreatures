package de.matthiasmann.twl.model;

public interface TableModel extends TableColumnHeaderModel {
  int getNumRows();
  
  Object getCell(int paramInt1, int paramInt2);
  
  Object getTooltipContent(int paramInt1, int paramInt2);
  
  void addChangeListener(ChangeListener paramChangeListener);
  
  void removeChangeListener(ChangeListener paramChangeListener);
  
  public static interface ChangeListener extends TableColumnHeaderModel.ColumnHeaderChangeListener {
    void rowsInserted(int param1Int1, int param1Int2);
    
    void rowsDeleted(int param1Int1, int param1Int2);
    
    void rowsChanged(int param1Int1, int param1Int2);
    
    void cellChanged(int param1Int1, int param1Int2);
    
    void allChanged();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\TableModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */