package de.matthiasmann.twl.model;

public interface TreeTableNode {
  Object getData(int paramInt);
  
  Object getTooltipContent(int paramInt);
  
  TreeTableNode getParent();
  
  boolean isLeaf();
  
  int getNumChildren();
  
  TreeTableNode getChild(int paramInt);
  
  int getChildIndex(TreeTableNode paramTreeTableNode);
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\de\matthiasmann\twl\model\TreeTableNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */