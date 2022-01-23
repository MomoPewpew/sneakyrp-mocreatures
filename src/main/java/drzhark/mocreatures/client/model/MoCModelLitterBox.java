package drzhark.mocreatures.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;







@SideOnly(Side.CLIENT)
public class MoCModelLitterBox
  extends ModelBase
{
  ModelRenderer Table1;
  ModelRenderer Table3;
  ModelRenderer Table2;
  ModelRenderer Litter;
  ModelRenderer Table4;
  ModelRenderer Bottom;
  ModelRenderer LitterUsed;
  public boolean usedlitter;

  public MoCModelLitterBox() {
    float f = 0.0F;
    this.Table1 = new ModelRenderer(this, 30, 0);
    this.Table1.addBox(-8.0F, 0.0F, 7.0F, 16, 6, 1, f);
    this.Table1.setRotationPoint(0.0F, 18.0F, 0.0F);
    this.Table3 = new ModelRenderer(this, 30, 0);
    this.Table3.addBox(-8.0F, 18.0F, -8.0F, 16, 6, 1, f);
    this.Table3.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.Table2 = new ModelRenderer(this, 30, 0);
    this.Table2.addBox(-8.0F, -3.0F, 0.0F, 16, 6, 1, f);
    this.Table2.setRotationPoint(8.0F, 21.0F, 0.0F);
    this.Table2.rotateAngleY = 1.5708F;
    this.Litter = new ModelRenderer(this, 0, 15);
    this.Litter.addBox(0.0F, 0.0F, 0.0F, 16, 2, 14, f);
    this.Litter.setRotationPoint(-8.0F, 21.0F, -7.0F);
    this.Table4 = new ModelRenderer(this, 30, 0);
    this.Table4.addBox(-8.0F, -3.0F, 0.0F, 16, 6, 1, f);
    this.Table4.setRotationPoint(-9.0F, 21.0F, 0.0F);
    this.Table4.rotateAngleY = 1.5708F;
    this.LitterUsed = new ModelRenderer(this, 16, 15);
    this.LitterUsed.addBox(0.0F, 0.0F, 0.0F, 16, 2, 14, f);
    this.LitterUsed.setRotationPoint(-8.0F, 21.0F, -7.0F);
    this.Bottom = new ModelRenderer(this, 16, 15);
    this.Bottom.addBox(-10.0F, 0.0F, -7.0F, 16, 1, 14, f);
    this.Bottom.setRotationPoint(2.0F, 23.0F, 0.0F);
  }


  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.Table1.render(f5);
    this.Table3.render(f5);
    this.Table2.render(f5);
    this.Table4.render(f5);
    this.Bottom.render(f5);
    if (this.usedlitter) {
      this.LitterUsed.render(f5);
    } else {
      this.Litter.render(f5);
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelLitterBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
