package de.matthiasmann.twl.model;

import de.matthiasmann.twl.utils.WithRunnableCallback;

public interface FloatModel extends WithRunnableCallback {
  float getValue();
  
  float getMinValue();
  
  float getMaxValue();
  
  void setValue(float paramFloat);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\FloatModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */