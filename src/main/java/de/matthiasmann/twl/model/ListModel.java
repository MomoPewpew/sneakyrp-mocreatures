package de.matthiasmann.twl.model;

public interface ListModel<T> {
  int getNumEntries();
  
  T getEntry(int paramInt);
  
  Object getEntryTooltip(int paramInt);
  
  boolean matchPrefix(int paramInt, String paramString);
  
  void addChangeListener(ChangeListener paramChangeListener);
  
  void removeChangeListener(ChangeListener paramChangeListener);
  
  public static interface ChangeListener {
    void entriesInserted(int param1Int1, int param1Int2);
    
    void entriesDeleted(int param1Int1, int param1Int2);
    
    void entriesChanged(int param1Int1, int param1Int2);
    
    void allChanged();
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\ListModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */