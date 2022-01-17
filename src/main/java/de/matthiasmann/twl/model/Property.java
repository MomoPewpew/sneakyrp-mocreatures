package de.matthiasmann.twl.model;

public interface Property<T> {
  String getName();
  
  boolean isReadOnly();
  
  boolean canBeNull();
  
  T getPropertyValue();
  
  void setPropertyValue(T paramT) throws IllegalArgumentException;
  
  Class<T> getType();
  
  void addValueChangedCallback(Runnable paramRunnable);
  
  void removeValueChangedCallback(Runnable paramRunnable);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\Property.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */