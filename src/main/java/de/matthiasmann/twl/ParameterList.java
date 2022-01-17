package de.matthiasmann.twl;

import de.matthiasmann.twl.renderer.Font;
import de.matthiasmann.twl.renderer.Image;
import de.matthiasmann.twl.renderer.MouseCursor;

public interface ParameterList {
  int getSize();
  
  Font getFont(int paramInt);
  
  Image getImage(int paramInt);
  
  MouseCursor getMouseCursor(int paramInt);
  
  ParameterMap getParameterMap(int paramInt);
  
  ParameterList getParameterList(int paramInt);
  
  boolean getParameter(int paramInt, boolean paramBoolean);
  
  int getParameter(int paramInt1, int paramInt2);
  
  float getParameter(int paramInt, float paramFloat);
  
  String getParameter(int paramInt, String paramString);
  
  Color getParameter(int paramInt, Color paramColor);
  
  <E extends Enum<E>> E getParameter(int paramInt, E paramE);
  
  Object getParameterValue(int paramInt);
  
  <T> T getParameterValue(int paramInt, Class<T> paramClass);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ParameterList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */