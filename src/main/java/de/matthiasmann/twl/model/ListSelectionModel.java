package de.matthiasmann.twl.model;

public interface ListSelectionModel<T> extends IntegerModel {
  public static final int NO_SELECTION = -1;
  
  ListModel<T> getListModel();
  
  T getSelectedEntry();
  
  boolean setSelectedEntry(T paramT);
  
  boolean setSelectedEntry(T paramT, int paramInt);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\ListSelectionModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */