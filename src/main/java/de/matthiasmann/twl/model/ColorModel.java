package de.matthiasmann.twl.model;

import de.matthiasmann.twl.Color;
import de.matthiasmann.twl.utils.WithRunnableCallback;

public interface ColorModel extends WithRunnableCallback {
  Color getValue();
  
  void setValue(Color paramColor);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\ColorModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */