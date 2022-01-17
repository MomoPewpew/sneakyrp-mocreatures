package de.matthiasmann.twl.renderer;

public interface Font2 extends Font {
  int drawText(int paramInt1, int paramInt2, AttributedString paramAttributedString);
  
  int drawText(int paramInt1, int paramInt2, AttributedString paramAttributedString, int paramInt3, int paramInt4);
  
  void drawMultiLineText(int paramInt1, int paramInt2, AttributedString paramAttributedString);
  
  void drawMultiLineText(int paramInt1, int paramInt2, AttributedString paramAttributedString, int paramInt3, int paramInt4);
  
  AttributedStringFontCache cacheText(AttributedStringFontCache paramAttributedStringFontCache, AttributedString paramAttributedString);
  
  AttributedStringFontCache cacheText(AttributedStringFontCache paramAttributedStringFontCache, AttributedString paramAttributedString, int paramInt1, int paramInt2);
  
  AttributedStringFontCache cacheMultiLineText(AttributedStringFontCache paramAttributedStringFontCache, AttributedString paramAttributedString);
  
  AttributedStringFontCache cacheMultiLineText(AttributedStringFontCache paramAttributedStringFontCache, AttributedString paramAttributedString, int paramInt1, int paramInt2);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\Font2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */