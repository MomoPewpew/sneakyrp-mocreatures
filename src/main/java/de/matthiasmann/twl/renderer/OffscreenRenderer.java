package de.matthiasmann.twl.renderer;

import de.matthiasmann.twl.Widget;

public interface OffscreenRenderer {
  OffscreenSurface startOffscreenRendering(Widget paramWidget, OffscreenSurface paramOffscreenSurface, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  void endOffscreenRendering();
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\OffscreenRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */