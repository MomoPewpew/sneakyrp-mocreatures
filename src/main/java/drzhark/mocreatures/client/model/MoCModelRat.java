package drzhark.mocreatures.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;











@SideOnly(Side.CLIENT)
public class MoCModelRat
  extends ModelBase
{
  public ModelRenderer Head;
  public ModelRenderer EarR;
  public ModelRenderer EarL;
  public ModelRenderer WhiskerR;
  public ModelRenderer WhiskerL;
  public ModelRenderer Body;
  public ModelRenderer Tail;
  public ModelRenderer FrontL;
  public ModelRenderer FrontR;
  public ModelRenderer RearL;
  public ModelRenderer RearR;
  public ModelRenderer BodyF;

  public MoCModelRat() {
    this.Head = new ModelRenderer(this, 0, 0);
    this.Head.addBox(-1.5F, -1.0F, -6.0F, 3, 4, 6, 0.0F);
    this.Head.setRotationPoint(0.0F, 18.0F, -9.0F);
    this.EarR = new ModelRenderer(this, 16, 26);
    this.EarR.addBox(-3.5F, -3.0F, -2.0F, 3, 3, 1, 0.0F);
    this.EarR.setRotationPoint(0.0F, 18.0F, -9.0F);
    this.EarL = new ModelRenderer(this, 24, 26);
    this.EarL.addBox(0.5F, -3.0F, -2.0F, 3, 3, 1, 0.0F);
    this.EarL.setRotationPoint(0.0F, 18.0F, -9.0F);
    this.WhiskerR = new ModelRenderer(this, 24, 16);
    this.WhiskerR.addBox(-4.5F, -1.0F, -6.0F, 3, 3, 1, 0.0F);
    this.WhiskerR.setRotationPoint(0.0F, 18.0F, -9.0F);
    this.WhiskerL = new ModelRenderer(this, 24, 20);
    this.WhiskerL.addBox(1.5F, -1.0F, -6.0F, 3, 3, 1, 0.0F);
    this.WhiskerL.setRotationPoint(0.0F, 18.0F, -9.0F);
    this.Body = new ModelRenderer(this, 24, 0);
    this.Body.addBox(-4.0F, -3.0F, -3.0F, 8, 8, 8, 0.0F);
    this.Body.setRotationPoint(0.0F, 19.0F, 0.0F);
    this.Body.rotateAngleX = 1.570796F;
    this.Tail = new ModelRenderer(this, 56, 0);
    this.Tail.addBox(-1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F);
    this.Tail.setRotationPoint(0.0F, 19.0F, 5.0F);
    this.Tail.rotateAngleX = 1.570796F;
    this.FrontL = new ModelRenderer(this, 0, 18);
    this.FrontL.addBox(-2.0F, 0.0F, -3.0F, 2, 1, 4, 0.0F);
    this.FrontL.setRotationPoint(3.0F, 22.0F, -7.0F);
    this.FrontR = new ModelRenderer(this, 0, 18);
    this.FrontR.addBox(0.0F, 0.0F, -3.0F, 2, 1, 4, 0.0F);
    this.FrontR.setRotationPoint(-3.0F, 22.0F, -7.0F);
    this.RearL = new ModelRenderer(this, 0, 24);
    this.RearL.addBox(-2.0F, 0.0F, -4.0F, 2, 1, 5, 0.0F);
    this.RearL.setRotationPoint(4.0F, 22.0F, 2.0F);
    this.RearR = new ModelRenderer(this, 0, 24);
    this.RearR.addBox(0.0F, 0.0F, -4.0F, 2, 1, 5, 0.0F);
    this.RearR.setRotationPoint(-4.0F, 22.0F, 2.0F);
    this.BodyF = new ModelRenderer(this, 32, 16);
    this.BodyF.addBox(-3.0F, -3.0F, -7.0F, 6, 6, 6, 0.0F);
    this.BodyF.setRotationPoint(0.0F, 19.0F, -2.0F);
  }


  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    this.Head.render(f5);
    this.EarR.render(f5);
    this.EarL.render(f5);
    this.WhiskerR.render(f5);
    this.WhiskerL.render(f5);
    this.Body.render(f5);
    this.Tail.render(f5);
    this.FrontL.render(f5);
    this.FrontR.render(f5);
    this.RearL.render(f5);
    this.RearR.render(f5);
    this.BodyF.render(f5);
  }

  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    this.Head.rotateAngleX = -(f4 / 57.29578F);
    this.Head.rotateAngleY = f3 / 57.29578F;
    this.EarR.rotateAngleX = this.Head.rotateAngleX;
    this.EarR.rotateAngleY = this.Head.rotateAngleY;
    this.EarL.rotateAngleX = this.Head.rotateAngleX;
    this.EarL.rotateAngleY = this.Head.rotateAngleY;
    this.WhiskerR.rotateAngleX = this.Head.rotateAngleX;
    this.WhiskerR.rotateAngleY = this.Head.rotateAngleY;
    this.WhiskerL.rotateAngleX = this.Head.rotateAngleX;
    this.WhiskerL.rotateAngleY = this.Head.rotateAngleY;
    this.FrontL.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.6F * f1;
    this.RearL.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
    this.RearR.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.6F * f1;
    this.FrontR.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
    this.Tail.rotateAngleY = this.FrontL.rotateAngleX * 0.625F;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelRat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
