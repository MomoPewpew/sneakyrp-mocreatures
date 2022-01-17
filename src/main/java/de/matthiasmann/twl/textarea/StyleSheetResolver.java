package de.matthiasmann.twl.textarea;

public interface StyleSheetResolver {
  void startLayout();
  
  Style resolve(Style paramStyle);
  
  void layoutFinished();
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\textarea\StyleSheetResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */