package de.matthiasmann.twl.model;

public interface ButtonModel {
  boolean isSelected();
  
  boolean isPressed();
  
  boolean isArmed();
  
  boolean isHover();
  
  boolean isEnabled();
  
  void setSelected(boolean paramBoolean);
  
  void setPressed(boolean paramBoolean);
  
  void setArmed(boolean paramBoolean);
  
  void setHover(boolean paramBoolean);
  
  void setEnabled(boolean paramBoolean);
  
  void addActionCallback(Runnable paramRunnable);
  
  void removeActionCallback(Runnable paramRunnable);
  
  void fireActionCallback();
  
  boolean hasActionCallbacks();
  
  void addStateCallback(Runnable paramRunnable);
  
  void removeStateCallback(Runnable paramRunnable);
  
  void connect();
  
  void disconnect();
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\ButtonModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */