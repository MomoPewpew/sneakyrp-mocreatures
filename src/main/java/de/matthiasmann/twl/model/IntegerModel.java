package de.matthiasmann.twl.model;

import de.matthiasmann.twl.utils.WithRunnableCallback;

public interface IntegerModel extends WithRunnableCallback {
  int getValue();
  
  int getMinValue();
  
  int getMaxValue();
  
  void setValue(int paramInt);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\IntegerModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */