package de.matthiasmann.twl.renderer;

import de.matthiasmann.twl.utils.StateSelect;
import de.matthiasmann.twl.utils.StringList;
import java.io.IOException;
import java.net.URL;

public interface FontMapper extends Resource {
  public static final int STYLE_NORMAL = 0;
  
  public static final int STYLE_BOLD = 1;
  
  public static final int STYLE_ITALIC = 2;
  
  public static final int REGISTER_WEAK = 256;
  
  Font getFont(StringList paramStringList, int paramInt1, int paramInt2, StateSelect paramStateSelect, FontParameter... paramVarArgs);
  
  boolean registerFont(String paramString, int paramInt, URL paramURL);
  
  boolean registerFont(String paramString, URL paramURL) throws IOException;
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\renderer\FontMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */