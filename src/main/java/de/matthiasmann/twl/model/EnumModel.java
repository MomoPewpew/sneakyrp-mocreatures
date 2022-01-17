package de.matthiasmann.twl.model;

import de.matthiasmann.twl.utils.WithRunnableCallback;

public interface EnumModel<T extends Enum<T>> extends WithRunnableCallback {
  Class<T> getEnumClass();
  
  T getValue();
  
  void setValue(T paramT);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\EnumModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */