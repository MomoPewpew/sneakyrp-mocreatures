package de.matthiasmann.twl.renderer;

import de.matthiasmann.twl.HAlignment;

public interface Font extends Resource {
  boolean isProportional();
  
  int getBaseLine();
  
  int getLineHeight();
  
  int getSpaceWidth();
  
  int getEM();
  
  int getEX();
  
  int computeMultiLineTextWidth(CharSequence paramCharSequence);
  
  int computeTextWidth(CharSequence paramCharSequence);
  
  int computeTextWidth(CharSequence paramCharSequence, int paramInt1, int paramInt2);
  
  int computeVisibleGlpyhs(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3);
  
  int drawMultiLineText(AnimationState paramAnimationState, int paramInt1, int paramInt2, CharSequence paramCharSequence, int paramInt3, HAlignment paramHAlignment);
  
  int drawText(AnimationState paramAnimationState, int paramInt1, int paramInt2, CharSequence paramCharSequence);
  
  int drawText(AnimationState paramAnimationState, int paramInt1, int paramInt2, CharSequence paramCharSequence, int paramInt3, int paramInt4);
  
  FontCache cacheMultiLineText(FontCache paramFontCache, CharSequence paramCharSequence, int paramInt, HAlignment paramHAlignment);
  
  FontCache cacheText(FontCache paramFontCache, CharSequence paramCharSequence);
  
  FontCache cacheText(FontCache paramFontCache, CharSequence paramCharSequence, int paramInt1, int paramInt2);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\Font.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */