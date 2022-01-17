package de.matthiasmann.twl.model;

public interface MRUListModel<T> extends ListModel<T> {
  int getMaxEntries();
  
  int getNumEntries();
  
  T getEntry(int paramInt);
  
  void addEntry(T paramT);
  
  void removeEntry(int paramInt);
  
  void addChangeListener(ListModel.ChangeListener paramChangeListener);
  
  void removeChangeListener(ListModel.ChangeListener paramChangeListener);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\MRUListModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */