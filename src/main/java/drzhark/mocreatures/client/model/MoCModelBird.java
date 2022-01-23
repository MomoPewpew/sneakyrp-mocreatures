package drzhark.mocreatures.client.model;

import drzhark.mocreatures.entity.passive.MoCEntityBird;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MoCModelBird
  extends ModelBase {
  public ModelRenderer head;
  public ModelRenderer body;
  public ModelRenderer leftleg;
  public ModelRenderer rightleg;
  public ModelRenderer rwing;
  public ModelRenderer lwing;
  public ModelRenderer beak;
  public ModelRenderer tail;
  private boolean isOnAir;

  public MoCModelBird() {
    byte byte0 = 16;
    this.head = new ModelRenderer(this, 0, 0);
    this.head.addBox(-1.5F, -3.0F, -2.0F, 3, 3, 3, 0.0F);
    this.head.setRotationPoint(0.0F, (-1 + byte0), -4.0F);
    this.beak = new ModelRenderer(this, 14, 0);
    this.beak.addBox(-0.5F, -1.5F, -3.0F, 1, 1, 2, 0.0F);
    this.beak.setRotationPoint(0.0F, (-1 + byte0), -4.0F);
    this.body = new ModelRenderer(this, 0, 9);
    this.body.addBox(-2.0F, -4.0F, -3.0F, 4, 8, 4, 0.0F);
    this.body.setRotationPoint(0.0F, (0 + byte0), 0.0F);
    this.body.rotateAngleX = 1.047198F;
    this.leftleg = new ModelRenderer(this, 26, 0);
    this.leftleg.addBox(-1.0F, 0.0F, -4.0F, 3, 4, 3);
    this.leftleg.setRotationPoint(-2.0F, (3 + byte0), 1.0F);
    this.rightleg = new ModelRenderer(this, 26, 0);
    this.rightleg.addBox(-1.0F, 0.0F, -4.0F, 3, 4, 3);
    this.rightleg.setRotationPoint(1.0F, (3 + byte0), 1.0F);
    this.rwing = new ModelRenderer(this, 24, 13);
    this.rwing.addBox(-1.0F, 0.0F, -3.0F, 1, 5, 5);
    this.rwing.setRotationPoint(-2.0F, (-2 + byte0), 0.0F);
    this.lwing = new ModelRenderer(this, 24, 13);
    this.lwing.addBox(0.0F, 0.0F, -3.0F, 1, 5, 5);
    this.lwing.setRotationPoint(2.0F, (-2 + byte0), 0.0F);
    this.tail = new ModelRenderer(this, 0, 23);
    this.tail.addBox(-6.0F, 5.0F, 2.0F, 4, 1, 4, 0.0F);
    this.tail.setRotationPoint(4.0F, (-3 + byte0), 0.0F);
    this.tail.rotateAngleX = 0.261799F;
  }


  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    MoCEntityBird bird = (MoCEntityBird)entity;
    this.isOnAir = (bird.isOnAir() && bird.getRidingEntity() == null);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    this.head.render(f5);
    this.beak.render(f5);
    this.body.render(f5);
    this.leftleg.render(f5);
    this.rightleg.render(f5);
    this.rwing.render(f5);
    this.lwing.render(f5);
    this.tail.render(f5);
  }


  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    this.head.rotateAngleX = -(f4 / 2.0F / 57.29578F);

    this.head.rotateAngleY = f3 / 57.29578F;
    this.beak.rotateAngleY = this.head.rotateAngleY;

    if (this.isOnAir) {
      this.leftleg.rotateAngleX = 1.4F;
      this.rightleg.rotateAngleX = 1.4F;
    } else {
      this.leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
      this.rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * f1;
    }
    this.rwing.rotateAngleZ = f2;
    this.lwing.rotateAngleZ = -f2;
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelBird.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
