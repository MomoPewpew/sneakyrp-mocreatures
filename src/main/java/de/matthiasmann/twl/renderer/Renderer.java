package de.matthiasmann.twl.renderer;

import de.matthiasmann.twl.Rect;
import de.matthiasmann.twl.utils.StateSelect;
import java.io.IOException;
import java.net.URL;

public interface Renderer {
  long getTimeMillis();
  
  boolean startRendering();
  
  void endRendering();
  
  int getWidth();
  
  int getHeight();
  
  CacheContext createNewCacheContext();
  
  void setActiveCacheContext(CacheContext paramCacheContext) throws IllegalStateException;
  
  CacheContext getActiveCacheContext();
  
  Font loadFont(URL paramURL, StateSelect paramStateSelect, FontParameter... paramVarArgs) throws IOException;
  
  Texture loadTexture(URL paramURL, String paramString1, String paramString2) throws IOException;
  
  LineRenderer getLineRenderer();
  
  OffscreenRenderer getOffscreenRenderer();
  
  FontMapper getFontMapper();
  
  DynamicImage createDynamicImage(int paramInt1, int paramInt2);
  
  Image createGradient(Gradient paramGradient);
  
  void clipEnter(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  void clipEnter(Rect paramRect);
  
  boolean clipIsEmpty();
  
  void clipLeave();
  
  void setCursor(MouseCursor paramMouseCursor);
  
  void setMousePosition(int paramInt1, int paramInt2);
  
  void setMouseButton(int paramInt, boolean paramBoolean);
  
  void pushGlobalTintColor(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  void popGlobalTintColor();
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\Renderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */