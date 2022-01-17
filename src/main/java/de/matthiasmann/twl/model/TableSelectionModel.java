package de.matthiasmann.twl.model;

public interface TableSelectionModel {
  void rowsInserted(int paramInt1, int paramInt2);
  
  void rowsDeleted(int paramInt1, int paramInt2);
  
  void clearSelection();
  
  void setSelection(int paramInt1, int paramInt2);
  
  void addSelection(int paramInt1, int paramInt2);
  
  void invertSelection(int paramInt1, int paramInt2);
  
  void removeSelection(int paramInt1, int paramInt2);
  
  int getLeadIndex();
  
  int getAnchorIndex();
  
  void setLeadIndex(int paramInt);
  
  void setAnchorIndex(int paramInt);
  
  boolean isSelected(int paramInt);
  
  boolean hasSelection();
  
  int getFirstSelected();
  
  int getLastSelected();
  
  int[] getSelection();
  
  void addSelectionChangeListener(Runnable paramRunnable);
  
  void removeSelectionChangeListener(Runnable paramRunnable);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\TableSelectionModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */