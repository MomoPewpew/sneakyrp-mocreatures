package de.matthiasmann.twl.renderer;

import de.matthiasmann.twl.Color;

public interface Image {
  int getWidth();
  
  int getHeight();
  
  void draw(AnimationState paramAnimationState, int paramInt1, int paramInt2);
  
  void draw(AnimationState paramAnimationState, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  Image createTintedVersion(Color paramColor);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\Image.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */