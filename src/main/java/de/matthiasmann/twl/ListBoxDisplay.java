package de.matthiasmann.twl;

public interface ListBoxDisplay {
  boolean isSelected();
  
  void setSelected(boolean paramBoolean);
  
  boolean isFocused();
  
  void setFocused(boolean paramBoolean);
  
  void setData(Object paramObject);
  
  void setTooltipContent(Object paramObject);
  
  Widget getWidget();
  
  void addListBoxCallback(CallbackWithReason<ListBox.CallbackReason> paramCallbackWithReason);
  
  void removeListBoxCallback(CallbackWithReason<ListBox.CallbackReason> paramCallbackWithReason);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ListBoxDisplay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */