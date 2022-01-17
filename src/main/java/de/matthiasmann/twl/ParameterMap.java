package de.matthiasmann.twl;

import de.matthiasmann.twl.renderer.Font;
import de.matthiasmann.twl.renderer.Image;
import de.matthiasmann.twl.renderer.MouseCursor;

public interface ParameterMap {
  Font getFont(String paramString);
  
  Image getImage(String paramString);
  
  MouseCursor getMouseCursor(String paramString);
  
  ParameterMap getParameterMap(String paramString);
  
  ParameterList getParameterList(String paramString);
  
  boolean getParameter(String paramString, boolean paramBoolean);
  
  int getParameter(String paramString, int paramInt);
  
  float getParameter(String paramString, float paramFloat);
  
  String getParameter(String paramString1, String paramString2);
  
  Color getParameter(String paramString, Color paramColor);
  
  <E extends Enum<E>> E getParameter(String paramString, E paramE);
  
  Object getParameterValue(String paramString, boolean paramBoolean);
  
  <T> T getParameterValue(String paramString, boolean paramBoolean, Class<T> paramClass);
  
  <T> T getParameterValue(String paramString, boolean paramBoolean, Class<T> paramClass, T paramT);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\ParameterMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */