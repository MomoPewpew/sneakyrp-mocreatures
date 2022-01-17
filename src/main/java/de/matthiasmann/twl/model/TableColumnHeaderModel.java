package de.matthiasmann.twl.model;

import de.matthiasmann.twl.renderer.AnimationState;

public interface TableColumnHeaderModel {
  int getNumColumns();
  
  AnimationState.StateKey[] getColumnHeaderStates();
  
  String getColumnHeaderText(int paramInt);
  
  boolean getColumnHeaderState(int paramInt1, int paramInt2);
  
  public static interface ColumnHeaderChangeListener {
    void columnInserted(int param1Int1, int param1Int2);
    
    void columnDeleted(int param1Int1, int param1Int2);
    
    void columnHeaderChanged(int param1Int);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\TableColumnHeaderModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */