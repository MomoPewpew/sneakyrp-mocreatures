package de.matthiasmann.twl.model;

public interface TreeTableModel extends TableColumnHeaderModel, TreeTableNode {
  void addChangeListener(ChangeListener paramChangeListener);
  
  void removeChangeListener(ChangeListener paramChangeListener);
  
  public static interface ChangeListener extends TableColumnHeaderModel.ColumnHeaderChangeListener {
    void nodesAdded(TreeTableNode param1TreeTableNode, int param1Int1, int param1Int2);
    
    void nodesRemoved(TreeTableNode param1TreeTableNode, int param1Int1, int param1Int2);
    
    void nodesChanged(TreeTableNode param1TreeTableNode, int param1Int1, int param1Int2);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\TreeTableModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */