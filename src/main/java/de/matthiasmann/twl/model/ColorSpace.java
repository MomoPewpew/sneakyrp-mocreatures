package de.matthiasmann.twl.model;

public interface ColorSpace {
  String getColorSpaceName();
  
  int getNumComponents();
  
  String getComponentName(int paramInt);
  
  String getComponentShortName(int paramInt);
  
  float getMinValue(int paramInt);
  
  float getMaxValue(int paramInt);
  
  float getDefaultValue(int paramInt);
  
  int toRGB(float[] paramArrayOffloat);
  
  float[] fromRGB(int paramInt);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\ColorSpace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */