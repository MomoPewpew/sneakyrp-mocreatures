package drzhark.mocreatures.client.model;

import drzhark.mocreatures.entity.passive.MoCEntityBigCat;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;


public class MoCModelNewBigCat
  extends ModelBase
{
  ModelRenderer RightHindFoot;
  ModelRenderer Stinger;
  ModelRenderer RightHindUpperLeg;
  ModelRenderer RightAnkle;
  ModelRenderer RightHindLowerLeg;
  ModelRenderer Ass;
  ModelRenderer TailTusk;
  ModelRenderer LeftChinBeard;
  ModelRenderer NeckBase;
  ModelRenderer RightEar;
  ModelRenderer LeftEar;
  ModelRenderer ForeheadHair;
  ModelRenderer LeftHarness;
  ModelRenderer RightHarness;
  ModelRenderer LeftUpperLip;
  ModelRenderer RightChinBeard;
  ModelRenderer LeftHindUpperLeg;
  ModelRenderer LeftHindLowerLeg;
  ModelRenderer LeftHindFoot;
  ModelRenderer LeftAnkle;
  ModelRenderer InsideMouth;
  ModelRenderer RightUpperLip;
  ModelRenderer LowerJawTeeth;
  ModelRenderer Nose;
  ModelRenderer LeftFang;
  ModelRenderer UpperTeeth;
  ModelRenderer RightFang;
  ModelRenderer LowerJaw;
  ModelRenderer SaddleFront;
  ModelRenderer LeftUpperLeg;
  ModelRenderer LeftLowerLeg;
  ModelRenderer LeftFrontFoot;
  ModelRenderer LeftClaw2;
  ModelRenderer LeftClaw1;
  ModelRenderer LeftClaw3;
  ModelRenderer RightClaw1;
  ModelRenderer RightClaw2;
  ModelRenderer RightClaw3;
  ModelRenderer RightFrontFoot;
  ModelRenderer RightLowerLeg;
  ModelRenderer RightUpperLeg;
  ModelRenderer Head;
  ModelRenderer ChinHair;
  ModelRenderer NeckHair;
  ModelRenderer Mane;
  ModelRenderer InnerWing;
  ModelRenderer MidWing;
  ModelRenderer OuterWing;
  ModelRenderer InnerWingR;
  ModelRenderer MidWingR;
  ModelRenderer OuterWingR;
  ModelRenderer Abdomen;
  ModelRenderer STailRoot;
  ModelRenderer STail2;
  ModelRenderer STail3;
  ModelRenderer STail4;
  ModelRenderer STail5;
  ModelRenderer StingerLump;
  ModelRenderer TailRoot;
  ModelRenderer Tail2;
  ModelRenderer Tail3;
  ModelRenderer Tail4;
  ModelRenderer TailTip;
  ModelRenderer Chest;
  ModelRenderer SaddleBack;
  ModelRenderer LeftFootRing;
  ModelRenderer Saddle;
  ModelRenderer LeftFootHarness;
  ModelRenderer RightFootHarness;
  ModelRenderer RightFootRing;
  ModelRenderer HeadBack;
  ModelRenderer HarnessStick;
  ModelRenderer NeckHarness;
  ModelRenderer Collar;
  ModelRenderer StorageChest;
  private float radianF = 57.29578F;

  protected boolean hasMane;
  protected boolean isRidden;
  private boolean isChested;
  protected boolean isSaddled;
  protected boolean flapwings;
  protected boolean onAir;
  private boolean diving;
  private boolean isSitting;
  protected boolean isFlyer;
  protected boolean floating;
  protected boolean poisoning;
  protected boolean isTamed;
  protected boolean movingTail;
  private float lLegMov = 0.0F;
  private float rLegMov = 0.0F;
  protected int openMouthCounter;
  protected boolean hasSaberTeeth;
  protected boolean hasChest;
  protected boolean hasStinger;
  protected boolean isGhost;
  protected boolean isMovingVertically;

  public MoCModelNewBigCat() {
    this.textureWidth = 128;
    this.textureHeight = 128;

    this.Chest = new ModelRenderer(this, 0, 18);
    this.Chest.addBox(-3.5F, 0.0F, -8.0F, 7, 8, 9);
    this.Chest.setRotationPoint(0.0F, 8.0F, 0.0F);

    this.NeckBase = new ModelRenderer(this, 0, 7);
    this.NeckBase.addBox(-2.5F, 0.0F, -2.5F, 5, 6, 5);
    this.NeckBase.setRotationPoint(0.0F, -0.5F, -8.0F);
    setRotation(this.NeckBase, -14.0F / this.radianF, 0.0F, 0.0F);
    this.Chest.addChild(this.NeckBase);

    this.Collar = new ModelRenderer(this, 18, 0);
    this.Collar.addBox(-2.5F, 0.0F, 0.0F, 5, 4, 1, 0.0F);
    this.Collar.setRotationPoint(0.0F, 6.0F, -2.0F);
    setRotation(this.Collar, 20.0F / this.radianF, 0.0F, 0.0F);
    this.NeckBase.addChild(this.Collar);

    this.HeadBack = new ModelRenderer(this, 0, 0);
    this.HeadBack.addBox(-2.51F, -2.5F, -1.0F, 5, 5, 2);
    this.HeadBack.setRotationPoint(0.0F, 2.7F, -2.9F);
    setRotation(this.HeadBack, 14.0F / this.radianF, 0.0F, 0.0F);
    this.NeckBase.addChild(this.HeadBack);

    this.NeckHarness = new ModelRenderer(this, 85, 32);
    this.NeckHarness.addBox(-3.0F, -3.0F, -2.0F, 6, 6, 2);
    this.NeckHarness.setRotationPoint(0.0F, 0.0F, 0.95F);
    this.HeadBack.addChild(this.NeckHarness);

    this.HarnessStick = new ModelRenderer(this, 85, 42);
    this.HarnessStick.addBox(-3.5F, -0.5F, -0.5F, 7, 1, 1);
    this.HarnessStick.setRotationPoint(0.0F, -1.8F, 0.5F);
    setRotation(this.HarnessStick, 45.0F / this.radianF, 0.0F, 0.0F);
    this.HeadBack.addChild(this.HarnessStick);

    this.LeftHarness = new ModelRenderer(this, 85, 32);
    this.LeftHarness.addBox(3.2F, -0.6F, 1.5F, 0, 1, 9);
    this.LeftHarness.setRotationPoint(0.0F, 8.6F, -13.0F);
    setRotation(this.LeftHarness, 25.0F / this.radianF, 0.0F, 0.0F);

    this.RightHarness = new ModelRenderer(this, 85, 31);
    this.RightHarness.addBox(-3.2F, -0.6F, 1.5F, 0, 1, 9);
    this.RightHarness.setRotationPoint(0.0F, 8.6F, -13.0F);
    setRotation(this.RightHarness, 25.0F / this.radianF, 0.0F, 0.0F);

    this.Head = new ModelRenderer(this, 32, 0);
    this.Head.addBox(-3.5F, -3.0F, -2.0F, 7, 6, 4);
    this.Head.setRotationPoint(0.0F, 0.2F, -2.2F);
    this.HeadBack.addChild(this.Head);

    this.Nose = new ModelRenderer(this, 46, 19);
    this.Nose.addBox(-1.5F, -1.0F, -2.0F, 3, 2, 4);
    this.Nose.setRotationPoint(0.0F, 0.0F, -3.0F);
    setRotation(this.Nose, 27.0F / this.radianF, 0.0F, 0.0F);
    this.Head.addChild(this.Nose);

    this.RightUpperLip = new ModelRenderer(this, 34, 19);
    this.RightUpperLip.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 4);
    this.RightUpperLip.setRotationPoint(-1.25F, 1.0F, -2.8F);
    setRotation(this.RightUpperLip, 10.0F / this.radianF, 2.0F / this.radianF, -15.0F / this.radianF);
    this.Head.addChild(this.RightUpperLip);

    this.LeftUpperLip = new ModelRenderer(this, 34, 25);
    this.LeftUpperLip.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 4);
    this.LeftUpperLip.setRotationPoint(1.25F, 1.0F, -2.8F);
    setRotation(this.LeftUpperLip, 10.0F / this.radianF, -2.0F / this.radianF, 15.0F / this.radianF);
    this.Head.addChild(this.LeftUpperLip);

    this.UpperTeeth = new ModelRenderer(this, 20, 7);
    this.UpperTeeth.addBox(-1.5F, -1.0F, -1.5F, 3, 2, 3);
    this.UpperTeeth.setRotationPoint(0.0F, 2.0F, -2.5F);
    setRotation(this.UpperTeeth, 15.0F / this.radianF, 0.0F, 0.0F);
    this.Head.addChild(this.UpperTeeth);

    this.LeftFang = new ModelRenderer(this, 44, 10);
    this.LeftFang.addBox(-0.5F, -1.5F, -0.5F, 1, 3, 1);
    this.LeftFang.setRotationPoint(1.2F, 2.8F, -3.4F);
    setRotation(this.LeftFang, 15.0F / this.radianF, 0.0F, 0.0F);
    this.Head.addChild(this.LeftFang);

    this.RightFang = new ModelRenderer(this, 48, 10);
    this.RightFang.addBox(-0.5F, -1.5F, -0.5F, 1, 3, 1);
    this.RightFang.setRotationPoint(-1.2F, 2.8F, -3.4F);
    setRotation(this.RightFang, 15.0F / this.radianF, 0.0F, 0.0F);
    this.Head.addChild(this.RightFang);

    this.InsideMouth = new ModelRenderer(this, 50, 0);
    this.InsideMouth.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 2);
    this.InsideMouth.setRotationPoint(0.0F, 2.0F, -1.0F);
    this.Head.addChild(this.InsideMouth);

    this.LowerJaw = new ModelRenderer(this, 46, 25);
    this.LowerJaw.addBox(-1.5F, -1.0F, -4.0F, 3, 2, 4);
    this.LowerJaw.setRotationPoint(0.0F, 2.1F, 0.0F);
    this.Head.addChild(this.LowerJaw);

    this.LowerJawTeeth = new ModelRenderer(this, 20, 12);
    this.LowerJawTeeth.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
    this.LowerJawTeeth.setRotationPoint(0.0F, -1.8F, -2.7F);
    this.LowerJawTeeth.mirror = true;
    this.LowerJaw.addChild(this.LowerJawTeeth);

    this.ChinHair = new ModelRenderer(this, 76, 7);
    this.ChinHair.addBox(-2.5F, 0.0F, -2.0F, 5, 6, 4);
    this.ChinHair.setRotationPoint(0.0F, 0.0F, 1.0F);
    this.LowerJaw.addChild(this.ChinHair);

    this.LeftChinBeard = new ModelRenderer(this, 48, 10);
    this.LeftChinBeard.addBox(-1.0F, -2.5F, -2.0F, 2, 5, 4);
    this.LeftChinBeard.setRotationPoint(3.6F, 0.0F, 0.25F);
    setRotation(this.LeftChinBeard, 0.0F, 30.0F / this.radianF, 0.0F);
    this.Head.addChild(this.LeftChinBeard);

    this.RightChinBeard = new ModelRenderer(this, 36, 10);
    this.RightChinBeard.addBox(-1.0F, -2.5F, -2.0F, 2, 5, 4);
    this.RightChinBeard.setRotationPoint(-3.6F, 0.0F, 0.25F);
    setRotation(this.RightChinBeard, 0.0F, -30.0F / this.radianF, 0.0F);
    this.Head.addChild(this.RightChinBeard);

    this.ForeheadHair = new ModelRenderer(this, 88, 0);
    this.ForeheadHair.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
    this.ForeheadHair.setRotationPoint(0.0F, -3.2F, 0.0F);
    setRotation(this.ForeheadHair, 10.0F / this.radianF, 0.0F, 0.0F);
    this.Head.addChild(this.ForeheadHair);

    this.Mane = new ModelRenderer(this, 94, 0);
    this.Mane.addBox(-5.5F, -5.5F, -3.0F, 11, 11, 6);
    this.Mane.setRotationPoint(0.0F, 0.7F, 3.7F);
    setRotation(this.Mane, -5.0F / this.radianF, 0.0F, 0.0F);
    this.Head.addChild(this.Mane);

    this.RightEar = new ModelRenderer(this, 54, 7);
    this.RightEar.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 1);
    this.RightEar.setRotationPoint(-2.7F, -3.5F, 1.0F);
    setRotation(this.RightEar, 0.0F, 0.0F, -15.0F / this.radianF);
    this.Head.addChild(this.RightEar);

    this.LeftEar = new ModelRenderer(this, 54, 4);
    this.LeftEar.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 1);
    this.LeftEar.setRotationPoint(2.7F, -3.5F, 1.0F);
    setRotation(this.LeftEar, 0.0F, 0.0F, 15.0F / this.radianF);
    this.Head.addChild(this.LeftEar);

    this.NeckHair = new ModelRenderer(this, 108, 17);
    this.NeckHair.addBox(-2.0F, -1.0F, -3.0F, 4, 2, 6);
    this.NeckHair.setRotationPoint(0.0F, -0.5F, 3.0F);
    setRotation(this.NeckHair, -10.6F / this.radianF, 0.0F, 0.0F);
    this.NeckBase.addChild(this.NeckHair);

    this.InnerWing = new ModelRenderer(this, 26, 115);
    this.InnerWing.addBox(0.0F, 0.0F, 0.0F, 7, 2, 11);
    this.InnerWing.setRotationPoint(4.0F, 9.0F, -7.0F);
    setRotation(this.InnerWing, 0.0F, -20.0F / this.radianF, 0.0F);

    this.MidWing = new ModelRenderer(this, 36, 89);
    this.MidWing.addBox(1.0F, 0.1F, 1.0F, 12, 2, 11);
    this.MidWing.setRotationPoint(4.0F, 9.0F, -7.0F);
    setRotation(this.MidWing, 0.0F, 5.0F / this.radianF, 0.0F);

    this.OuterWing = new ModelRenderer(this, 62, 115);
    this.OuterWing.addBox(0.0F, 0.0F, 0.0F, 22, 2, 11);
    this.OuterWing.setRotationPoint(16.0F, 9.0F, -7.0F);
    setRotation(this.OuterWing, 0.0F, -18.0F / this.radianF, 0.0F);

    this.InnerWingR = new ModelRenderer(this, 26, 102);
    this.InnerWingR.addBox(-7.0F, 0.0F, 0.0F, 7, 2, 11);
    this.InnerWingR.setRotationPoint(-4.0F, 9.0F, -7.0F);
    setRotation(this.InnerWingR, 0.0F, 20.0F / this.radianF, 0.0F);

    this.MidWingR = new ModelRenderer(this, 82, 89);
    this.MidWingR.addBox(-13.0F, 0.1F, 1.0F, 12, 2, 11);
    this.MidWingR.setRotationPoint(-4.0F, 9.0F, -7.0F);
    setRotation(this.MidWingR, 0.0F, -5.0F / this.radianF, 0.0F);

    this.OuterWingR = new ModelRenderer(this, 62, 102);
    this.OuterWingR.addBox(-22.0F, 0.0F, 0.0F, 22, 2, 11);
    this.OuterWingR.setRotationPoint(-16.0F, 9.0F, -7.0F);
    setRotation(this.OuterWingR, 0.0F, 18.0F / this.radianF, 0.0F);

    this.Abdomen = new ModelRenderer(this, 0, 35);
    this.Abdomen.addBox(-3.0F, 0.0F, 0.0F, 6, 7, 7);
    this.Abdomen.setRotationPoint(0.0F, 0.0F, 0.0F);
    setRotation(this.Abdomen, -0.0523599F, 0.0F, 0.0F);
    this.Chest.addChild(this.Abdomen);

    this.Ass = new ModelRenderer(this, 0, 49);
    this.Ass.addBox(-2.5F, 0.0F, 0.0F, 5, 5, 3);
    this.Ass.setRotationPoint(0.0F, 0.0F, 7.0F);
    setRotation(this.Ass, -20.0F / this.radianF, 0.0F, 0.0F);
    this.Abdomen.addChild(this.Ass);

    this.TailRoot = new ModelRenderer(this, 96, 83);
    this.TailRoot.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2);
    this.TailRoot.setRotationPoint(0.0F, 1.0F, 7.0F);
    setRotation(this.TailRoot, 87.0F / this.radianF, 0.0F, 0.0F);
    this.Abdomen.addChild(this.TailRoot);

    this.Tail2 = new ModelRenderer(this, 96, 75);
    this.Tail2.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2);
    this.Tail2.setRotationPoint(-0.01F, 3.5F, 0.0F);
    setRotation(this.Tail2, -30.0F / this.radianF, 0.0F, 0.0F);
    this.TailRoot.addChild(this.Tail2);

    this.Tail3 = new ModelRenderer(this, 96, 67);
    this.Tail3.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2);
    this.Tail3.setRotationPoint(0.01F, 5.5F, 0.0F);
    setRotation(this.Tail3, -17.0F / this.radianF, 0.0F, 0.0F);
    this.Tail2.addChild(this.Tail3);

    this.Tail4 = new ModelRenderer(this, 96, 61);
    this.Tail4.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2);
    this.Tail4.setRotationPoint(-0.01F, 5.5F, 0.0F);
    setRotation(this.Tail4, 21.0F / this.radianF, 0.0F, 0.0F);
    this.Tail3.addChild(this.Tail4);

    this.TailTip = new ModelRenderer(this, 96, 55);
    this.TailTip.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2);
    this.TailTip.setRotationPoint(0.01F, 3.5F, 0.0F);
    setRotation(this.TailTip, 21.0F / this.radianF, 0.0F, 0.0F);
    this.Tail4.addChild(this.TailTip);

    this.TailTusk = new ModelRenderer(this, 96, 49);
    this.TailTusk.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
    this.TailTusk.setRotationPoint(0.0F, 3.5F, 0.0F);
    setRotation(this.TailTusk, 21.0F / this.radianF, 0.0F, 0.0F);
    this.Tail4.addChild(this.TailTusk);

    this.Saddle = new ModelRenderer(this, 79, 18);
    this.Saddle.addBox(-4.0F, -1.0F, -3.0F, 8, 2, 6);
    this.Saddle.setRotationPoint(0.0F, 0.5F, -1.0F);
    this.Chest.addChild(this.Saddle);

    this.SaddleFront = new ModelRenderer(this, 101, 26);
    this.SaddleFront.addBox(-2.5F, -1.0F, -1.5F, 5, 2, 3);
    this.SaddleFront.setRotationPoint(0.0F, -1.0F, -1.5F);
    setRotation(this.SaddleFront, -10.6F / this.radianF, 0.0F, 0.0F);
    this.Saddle.addChild(this.SaddleFront);

    this.SaddleBack = new ModelRenderer(this, 77, 26);
    this.SaddleBack.addBox(-4.0F, -2.0F, -2.0F, 8, 2, 4);
    this.SaddleBack.setRotationPoint(0.0F, 0.7F, 4.0F);
    setRotation(this.SaddleBack, 12.78F / this.radianF, 0.0F, 0.0F);
    this.Saddle.addChild(this.SaddleBack);

    this.LeftFootHarness = new ModelRenderer(this, 81, 18);
    this.LeftFootHarness.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1);
    this.LeftFootHarness.setRotationPoint(4.0F, 0.0F, 0.5F);
    this.Saddle.addChild(this.LeftFootHarness);

    this.LeftFootRing = new ModelRenderer(this, 107, 31);
    this.LeftFootRing.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
    this.LeftFootRing.setRotationPoint(-0.5F, 5.0F, -1.0F);
    this.LeftFootHarness.addChild(this.LeftFootRing);

    this.RightFootHarness = new ModelRenderer(this, 101, 18);
    this.RightFootHarness.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1);
    this.RightFootHarness.setRotationPoint(-4.0F, 0.0F, 0.5F);
    this.Saddle.addChild(this.RightFootHarness);

    this.RightFootRing = new ModelRenderer(this, 101, 31);
    this.RightFootRing.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
    this.RightFootRing.setRotationPoint(-0.5F, 5.0F, -1.0F);
    this.RightFootHarness.addChild(this.RightFootRing);

    this.StorageChest = new ModelRenderer(this, 32, 59);
    this.StorageChest.addBox(-5.0F, -2.0F, -2.5F, 10, 4, 5);
    this.StorageChest.setRotationPoint(0.0F, -2.0F, 5.5F);
    setRotation(this.StorageChest, -90.0F / this.radianF, 0.0F, 0.0F);
    this.Abdomen.addChild(this.StorageChest);

    this.STailRoot = new ModelRenderer(this, 104, 79);
    this.STailRoot.addBox(-3.0F, 4.0F, 5.0F, 6, 4, 6);
    this.STailRoot.setRotationPoint(0.0F, 8.0F, 0.0F);
    this.STailRoot.setTextureSize(128, 128);
    this.STailRoot.mirror = true;
    setRotation(this.STailRoot, 0.5796765F, 0.0F, 0.0F);
    this.STail2 = new ModelRenderer(this, 106, 69);
    this.STail2.addBox(-2.5F, 7.5F, 7.3F, 5, 4, 6);
    this.STail2.setRotationPoint(0.0F, 8.0F, 0.0F);
    this.STail2.setTextureSize(128, 128);
    this.STail2.mirror = true;
    setRotation(this.STail2, 0.9514626F, 0.0F, 0.0F);
    this.STail3 = new ModelRenderer(this, 108, 60);
    this.STail3.addBox(-2.0F, 13.5F, 3.3F, 4, 3, 6);
    this.STail3.setRotationPoint(0.0F, 8.0F, 0.0F);
    this.STail3.setTextureSize(128, 128);
    this.STail3.mirror = true;
    setRotation(this.STail3, 1.660128F, 0.0F, 0.0F);
    this.STail4 = new ModelRenderer(this, 108, 51);
    this.STail4.addBox(-2.0F, 15.2F, -5.3F, 4, 3, 6);
    this.STail4.setRotationPoint(0.0F, 8.0F, 0.0F);
    this.STail4.setTextureSize(128, 128);
    this.STail4.mirror = true;
    setRotation(this.STail4, 2.478058F, 0.0F, 0.0F);
    this.STail5 = new ModelRenderer(this, 108, 42);
    this.STail5.addBox(-2.0F, 12.9F, -9.0F, 4, 3, 6);
    this.STail5.setRotationPoint(0.0F, 8.0F, 0.0F);
    this.STail5.setTextureSize(128, 128);
    this.STail5.mirror = true;
    setRotation(this.STail5, 3.035737F, 0.0F, 0.0F);
    this.StingerLump = new ModelRenderer(this, 112, 34);
    this.StingerLump.addBox(-1.5F, 7.9F, 6.0F, 3, 3, 5);
    this.StingerLump.setRotationPoint(0.0F, 8.0F, 0.0F);
    this.StingerLump.setTextureSize(128, 128);
    this.StingerLump.mirror = true;
    setRotation(this.StingerLump, 2.031914F, 0.0F, 0.0F);
    this.Stinger = new ModelRenderer(this, 118, 29);
    this.Stinger.addBox(-0.5F, 1.9F, 8.0F, 1, 1, 4);
    this.Stinger.setRotationPoint(0.0F, 8.0F, 0.0F);
    this.Stinger.setTextureSize(128, 128);
    this.Stinger.mirror = true;
    setRotation(this.Stinger, 1.213985F, 0.0F, 0.0F);

    this.LeftUpperLeg = new ModelRenderer(this, 0, 96);
    this.LeftUpperLeg.addBox(-1.5F, 0.0F, -2.0F, 3, 7, 4);
    this.LeftUpperLeg.setRotationPoint(3.99F, 3.0F, -7.0F);
    setRotation(this.LeftUpperLeg, 15.0F / this.radianF, 0.0F, 0.0F);
    this.Chest.addChild(this.LeftUpperLeg);

    this.LeftLowerLeg = new ModelRenderer(this, 0, 107);
    this.LeftLowerLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3);
    this.LeftLowerLeg.setRotationPoint(-0.01F, 6.5F, 0.2F);
    setRotation(this.LeftLowerLeg, -21.5F / this.radianF, 0.0F, 0.0F);
    this.LeftUpperLeg.addChild(this.LeftLowerLeg);

    this.LeftFrontFoot = new ModelRenderer(this, 0, 116);
    this.LeftFrontFoot.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4);
    this.LeftFrontFoot.setRotationPoint(0.0F, 5.0F, -1.0F);
    setRotation(this.LeftFrontFoot, 6.5F / this.radianF, 0.0F, 0.0F);
    this.LeftLowerLeg.addChild(this.LeftFrontFoot);

    this.LeftClaw1 = new ModelRenderer(this, 16, 125);
    this.LeftClaw1.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2);
    this.LeftClaw1.setRotationPoint(-1.3F, 1.2F, -3.0F);
    setRotation(this.LeftClaw1, 45.0F / this.radianF, 0.0F, -1.0F / this.radianF);
    this.LeftFrontFoot.addChild(this.LeftClaw1);

    this.LeftClaw2 = new ModelRenderer(this, 16, 125);
    this.LeftClaw2.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2);
    this.LeftClaw2.setRotationPoint(0.0F, 1.1F, -3.0F);
    setRotation(this.LeftClaw2, 45.0F / this.radianF, 0.0F, 0.0F);
    this.LeftFrontFoot.addChild(this.LeftClaw2);

    this.LeftClaw3 = new ModelRenderer(this, 16, 125);
    this.LeftClaw3.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2);
    this.LeftClaw3.setRotationPoint(1.3F, 1.2F, -3.0F);
    setRotation(this.LeftClaw3, 45.0F / this.radianF, 0.0F, 1.0F / this.radianF);
    this.LeftFrontFoot.addChild(this.LeftClaw3);

    this.RightUpperLeg = new ModelRenderer(this, 14, 96);
    this.RightUpperLeg.addBox(-1.5F, 0.0F, -2.0F, 3, 7, 4);
    this.RightUpperLeg.setRotationPoint(-3.99F, 3.0F, -7.0F);
    setRotation(this.RightUpperLeg, 15.0F / this.radianF, 0.0F, 0.0F);
    this.Chest.addChild(this.RightUpperLeg);

    this.RightLowerLeg = new ModelRenderer(this, 12, 107);
    this.RightLowerLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3);
    this.RightLowerLeg.setRotationPoint(0.01F, 6.5F, 0.2F);
    setRotation(this.RightLowerLeg, -21.5F / this.radianF, 0.0F, 0.0F);
    this.RightUpperLeg.addChild(this.RightLowerLeg);

    this.RightFrontFoot = new ModelRenderer(this, 0, 122);
    this.RightFrontFoot.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4);
    this.RightFrontFoot.setRotationPoint(0.0F, 5.0F, -1.0F);
    setRotation(this.RightFrontFoot, 6.5F / this.radianF, 0.0F, 0.0F);
    this.RightLowerLeg.addChild(this.RightFrontFoot);

    this.RightClaw1 = new ModelRenderer(this, 16, 125);
    this.RightClaw1.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2);
    this.RightClaw1.setRotationPoint(-1.3F, 1.2F, -3.0F);
    setRotation(this.RightClaw1, 45.0F / this.radianF, 0.0F, -1.0F / this.radianF);
    this.RightFrontFoot.addChild(this.RightClaw1);

    this.RightClaw2 = new ModelRenderer(this, 16, 125);
    this.RightClaw2.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2);
    this.RightClaw2.setRotationPoint(0.0F, 1.1F, -3.0F);
    setRotation(this.RightClaw2, 45.0F / this.radianF, 0.0F, 0.0F);
    this.RightFrontFoot.addChild(this.RightClaw2);

    this.RightClaw3 = new ModelRenderer(this, 16, 125);
    this.RightClaw3.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2);
    this.RightClaw3.setRotationPoint(1.3F, 1.2F, -3.0F);
    setRotation(this.RightClaw3, 45.0F / this.radianF, 0.0F, 1.0F / this.radianF);
    this.RightFrontFoot.addChild(this.RightClaw3);

    this.LeftHindUpperLeg = new ModelRenderer(this, 0, 67);
    this.LeftHindUpperLeg.addBox(-2.0F, -1.0F, -1.5F, 3, 8, 5);
    this.LeftHindUpperLeg.setRotationPoint(3.0F, 3.0F, 6.8F);
    setRotation(this.LeftHindUpperLeg, -25.0F / this.radianF, 0.0F, 0.0F);
    this.Abdomen.addChild(this.LeftHindUpperLeg);

    this.LeftAnkle = new ModelRenderer(this, 0, 80);
    this.LeftAnkle.addBox(-1.0F, 0.0F, -1.5F, 2, 3, 3);
    this.LeftAnkle.setRotationPoint(-0.5F, 4.0F, 5.0F);
    this.LeftHindUpperLeg.addChild(this.LeftAnkle);

    this.LeftHindLowerLeg = new ModelRenderer(this, 0, 86);
    this.LeftHindLowerLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
    this.LeftHindLowerLeg.setRotationPoint(0.0F, 3.0F, 0.5F);
    this.LeftAnkle.addChild(this.LeftHindLowerLeg);

    this.LeftHindFoot = new ModelRenderer(this, 0, 91);
    this.LeftHindFoot.addBox(-1.5F, 0.0F, -1.5F, 3, 2, 3);
    this.LeftHindFoot.setRotationPoint(0.0F, 2.6F, -0.8F);
    setRotation(this.LeftHindFoot, 27.0F / this.radianF, 0.0F, 0.0F);
    this.LeftHindLowerLeg.addChild(this.LeftHindFoot);

    this.RightHindUpperLeg = new ModelRenderer(this, 16, 67);
    this.RightHindUpperLeg.addBox(-2.0F, -1.0F, -1.5F, 3, 8, 5);
    this.RightHindUpperLeg.setRotationPoint(-2.0F, 3.0F, 6.8F);
    setRotation(this.RightHindUpperLeg, -25.0F / this.radianF, 0.0F, 0.0F);
    this.Abdomen.addChild(this.RightHindUpperLeg);

    this.RightAnkle = new ModelRenderer(this, 10, 80);
    this.RightAnkle.addBox(-1.0F, 0.0F, -1.5F, 2, 3, 3);
    this.RightAnkle.setRotationPoint(-0.5F, 4.0F, 5.0F);
    this.RightHindUpperLeg.addChild(this.RightAnkle);

    this.RightHindLowerLeg = new ModelRenderer(this, 8, 86);
    this.RightHindLowerLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
    this.RightHindLowerLeg.setRotationPoint(0.0F, 3.0F, 0.5F);
    this.RightAnkle.addChild(this.RightHindLowerLeg);

    this.RightHindFoot = new ModelRenderer(this, 12, 91);
    this.RightHindFoot.addBox(-1.5F, 0.0F, -1.5F, 3, 2, 3);
    this.RightHindFoot.setRotationPoint(0.0F, 2.6F, -0.8F);
    setRotation(this.RightHindFoot, 27.0F / this.radianF, 0.0F, 0.0F);
    this.RightHindLowerLeg.addChild(this.RightHindFoot);
  }


  public void updateAnimationModifiers(Entity entity) {
    MoCEntityBigCat bigcat = (MoCEntityBigCat)entity;
    this.isFlyer = bigcat.isFlyer();
    this.isSaddled = bigcat.getIsRideable();
    this.flapwings = (bigcat.wingFlapCounter != 0);
    this.onAir = bigcat.isOnAir();
    this.floating = (this.isFlyer && this.onAir);

    this.openMouthCounter = bigcat.mouthCounter;
    this.isRidden = bigcat.isBeingRidden();
    this.hasMane = bigcat.hasMane();
    this.isTamed = bigcat.getHasAmulet();
    this.isSitting = bigcat.getIsSitting();
    this.movingTail = (bigcat.tailCounter != 0);
    this.hasSaberTeeth = bigcat.hasSaberTeeth();
    this.hasChest = bigcat.getIsChested();
    this.hasStinger = bigcat.getHasStinger();
    this.isGhost = bigcat.getIsGhost();
    this.isMovingVertically = (bigcat.motionY != 0.0D);
  }

  public float updateGhostTransparency(Entity entity) {
    MoCEntityBigCat bigcat = (MoCEntityBigCat)entity;
    return bigcat.tFloat();
  }


  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    updateAnimationModifiers(entity);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    renderSaddle(this.isSaddled);
    renderMane(this.hasMane);
    renderCollar(this.isTamed);
    renderTeeth(this.hasSaberTeeth);
    renderChest(this.hasChest);

    GL11.glPushMatrix();


    if (this.isGhost) {
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(0.8F, 0.8F, 0.8F, updateGhostTransparency(entity));
    }


    this.Chest.render(f5);

    if (this.isFlyer) {
      this.InnerWing.render(f5);
      this.MidWing.render(f5);
      this.OuterWing.render(f5);
      this.InnerWingR.render(f5);
      this.MidWingR.render(f5);
      this.OuterWingR.render(f5);
    }

    if (this.hasStinger) {
      this.STailRoot.render(f5);
      this.STail2.render(f5);
      this.STail3.render(f5);
      this.STail4.render(f5);
      this.STail5.render(f5);
      this.StingerLump.render(f5);
      this.Stinger.render(f5);
    }

    if (this.isSaddled && this.isRidden) {
      this.LeftHarness.render(f5);
      this.RightHarness.render(f5);
    }

    if (this.isGhost) {
      GL11.glDisable(3042);
    }
    GL11.glPopMatrix();
  }


  private void renderTeeth(boolean flag) {
    this.LeftFang.isHidden = !flag;
    this.RightFang.isHidden = !flag;
  }

  private void renderCollar(boolean flag) {
    this.Collar.isHidden = !flag;
  }

  private void renderSaddle(boolean flag) {
    this.NeckHarness.isHidden = !flag;
    this.HarnessStick.isHidden = !flag;
    this.Saddle.isHidden = !flag;
  }

  private void renderMane(boolean flag) {
    this.Mane.isHidden = !flag;
    this.LeftChinBeard.isHidden = !flag;
    this.RightChinBeard.isHidden = !flag;
    this.ForeheadHair.isHidden = !flag;
    this.NeckHair.isHidden = !flag;
    this.ChinHair.isHidden = !flag;
  }

  private void renderChest(boolean flag) {
    this.StorageChest.isHidden = !flag;
  }

  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }









  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    float RLegXRot = MathHelper.cos(f * 0.8F + 3.141593F) * 0.8F * f1;
    float LLegXRot = MathHelper.cos(f * 0.8F) * 0.8F * f1;
    float gallopRLegXRot = MathHelper.cos(f * 0.6F + 3.141593F) * 0.8F * f1;
    float gallopLLegXRot = MathHelper.cos(f * 0.6F) * 0.8F * f1;

    float stingYOffset = 8.0F;
    float stingZOffset = 0.0F;
















    if (this.movingTail) {
      this.Tail2.rotateAngleY = MathHelper.cos(f2 * 0.3F);
    } else {

      this.Tail2.rotateAngleY = 0.0F;
    }

    if (this.isSitting) {
      stingYOffset = 17.0F;
      stingZOffset = -3.0F;
      this.Chest.rotationPointY = 14.0F;
      this.Abdomen.rotateAngleX = -10.0F / this.radianF;
      this.Chest.rotateAngleX = -45.0F / this.radianF;
      this.RightUpperLeg.rotateAngleX = 35.0F / this.radianF;
      this.RightLowerLeg.rotateAngleX = 5.0F / this.radianF;
      this.LeftUpperLeg.rotateAngleX = 35.0F / this.radianF;
      this.LeftLowerLeg.rotateAngleX = 5.0F / this.radianF;
      this.NeckBase.rotateAngleX = 20.0F / this.radianF;
      this.RightHindUpperLeg.rotationPointY = 1.0F;
      this.RightHindUpperLeg.rotateAngleX = -50.0F / this.radianF;
      this.LeftHindUpperLeg.rotationPointY = 1.0F;
      this.LeftHindUpperLeg.rotateAngleX = -50.0F / this.radianF;
      this.RightHindFoot.rotateAngleX = 90.0F / this.radianF;
      this.LeftHindFoot.rotateAngleX = 90.0F / this.radianF;


      this.TailRoot.rotateAngleX = 100.0F / this.radianF;
      this.Tail2.rotateAngleX = 35.0F / this.radianF;
      this.Tail3.rotateAngleX = 10.0F / this.radianF;
      this.NeckHair.rotationPointY = 2.0F;


















      this.Collar.rotateAngleX = 0.0F / this.radianF;
      this.Collar.rotationPointY = 7.0F;
      this.Collar.rotationPointZ = -4.0F;

    }
    else {

      stingYOffset = 8.0F;
      stingZOffset = 0.0F;

      this.Chest.rotationPointY = 8.0F;
      this.Abdomen.rotateAngleX = 0.0F;
      this.Chest.rotateAngleX = 0.0F;
      this.NeckBase.rotateAngleX = -14.0F / this.radianF;
      this.TailRoot.rotateAngleX = 87.0F / this.radianF;
      this.Tail2.rotateAngleX = -30.0F / this.radianF;
      this.Tail3.rotateAngleX = -17.0F / this.radianF;
      this.RightLowerLeg.rotateAngleX = -21.5F / this.radianF;
      this.LeftLowerLeg.rotateAngleX = -21.5F / this.radianF;
      this.RightHindUpperLeg.rotationPointY = 3.0F;
      this.LeftHindUpperLeg.rotationPointY = 3.0F;
      this.RightHindFoot.rotateAngleX = 27.0F / this.radianF;
      this.LeftHindFoot.rotateAngleX = 27.0F / this.radianF;


      this.Collar.rotationPointZ = -2.0F;
      this.NeckHair.rotationPointY = -0.5F;
      if (this.hasMane) {
        this.Collar.rotationPointY = 9.0F;
      } else {
        this.Collar.rotationPointY = 6.0F;
      }
      this.Collar.rotateAngleX = 20.0F / this.radianF + MathHelper.cos(f * 0.8F) * 0.5F * f1;



      boolean galloping = (f1 >= 0.97F);
      if (this.onAir || this.isGhost) {
        if (this.isGhost || (this.isFlyer && f1 > 0.0F)) {
          float speedMov = f1 * 0.5F;
          this.RightUpperLeg.rotateAngleX = 45.0F / this.radianF + speedMov;
          this.LeftUpperLeg.rotateAngleX = 45.0F / this.radianF + speedMov;
          this.RightHindUpperLeg.rotateAngleX = 10.0F / this.radianF + speedMov;
          this.LeftHindUpperLeg.rotateAngleX = 10.0F / this.radianF + speedMov;
        } else if (this.isMovingVertically) {

          this.RightUpperLeg.rotateAngleX = -35.0F / this.radianF;
          this.LeftUpperLeg.rotateAngleX = -35.0F / this.radianF;
          this.RightHindUpperLeg.rotateAngleX = 35.0F / this.radianF;
          this.LeftHindUpperLeg.rotateAngleX = 35.0F / this.radianF;
        }

      }
      else if (galloping) {

        this.RightUpperLeg.rotateAngleX = 15.0F / this.radianF + gallopRLegXRot;
        this.LeftUpperLeg.rotateAngleX = 15.0F / this.radianF + gallopRLegXRot;
        this.RightHindUpperLeg.rotateAngleX = -25.0F / this.radianF + gallopLLegXRot;
        this.LeftHindUpperLeg.rotateAngleX = -25.0F / this.radianF + gallopLLegXRot;

        this.Abdomen.rotateAngleY = 0.0F;
      } else {
        this.RightUpperLeg.rotateAngleX = 15.0F / this.radianF + RLegXRot;
        this.LeftHindUpperLeg.rotateAngleX = -25.0F / this.radianF + RLegXRot;
        this.LeftUpperLeg.rotateAngleX = 15.0F / this.radianF + LLegXRot;
        this.RightHindUpperLeg.rotateAngleX = -25.0F / this.radianF + LLegXRot;
        if (!this.hasStinger) {
          this.Abdomen.rotateAngleY = MathHelper.cos(f * 0.3F) * 0.25F * f1;
        }
      }


      if (this.isRidden) {
        this.LeftFootHarness.rotateAngleX = -60.0F / this.radianF;
        this.RightFootHarness.rotateAngleX = -60.0F / this.radianF;
      } else {
        this.LeftFootHarness.rotateAngleX = RLegXRot / 3.0F;
        this.RightFootHarness.rotateAngleX = RLegXRot / 3.0F;

        this.LeftFootHarness.rotateAngleZ = RLegXRot / 5.0F;
        this.RightFootHarness.rotateAngleZ = -RLegXRot / 5.0F;
      }










      float TailXRot = MathHelper.cos(f * 0.4F) * 0.15F * f1;




      this.TailRoot.rotateAngleX = 87.0F / this.radianF + TailXRot;
      this.Tail2.rotateAngleX = -30.0F / this.radianF + TailXRot;
      this.Tail3.rotateAngleX = -17.0F / this.radianF + TailXRot;
      this.Tail4.rotateAngleX = 21.0F / this.radianF + TailXRot;
      this.TailTip.rotateAngleX = 21.0F / this.radianF + TailXRot;
      this.TailTusk.rotateAngleX = 21.0F / this.radianF + TailXRot;
    }


    float HeadXRot = f4 / 57.29578F;












    this.HeadBack.rotateAngleX = 14.0F / this.radianF + HeadXRot;
    this.HeadBack.rotateAngleY = f3 / 57.29578F;

    float mouthMov = 0.0F;
    if (this.openMouthCounter != 0) {
      if (this.openMouthCounter < 10) {
        mouthMov = (22 + this.openMouthCounter * 3);
      } else if (this.openMouthCounter > 20) {
        mouthMov = (22 + 90 - this.openMouthCounter * 3);
      } else {
        mouthMov = 55.0F;
      }
    }
    this.LowerJaw.rotateAngleX = mouthMov / this.radianF;

    if (this.isSaddled) {
      this.LeftHarness.rotateAngleX = 25.0F / this.radianF + this.HeadBack.rotateAngleX;
      this.LeftHarness.rotateAngleY = this.HeadBack.rotateAngleY;
      this.RightHarness.rotateAngleX = 25.0F / this.radianF + this.HeadBack.rotateAngleX;
      this.RightHarness.rotateAngleY = this.HeadBack.rotateAngleY;
    }























    if (this.isFlyer) {





      float WingRot = 0.0F;
      if (this.flapwings) {
        WingRot = MathHelper.cos(f2 * 0.3F + 3.141593F) * 1.2F;
      }
      else {

        WingRot = MathHelper.cos(f * 0.5F) * 0.1F;
      }








      if (this.floating) {
        this.OuterWing.rotateAngleY = -0.3228859F + WingRot / 2.0F;
        this.OuterWingR.rotateAngleY = 0.3228859F - WingRot / 2.0F;
      } else {

        WingRot = 60.0F / this.radianF;
        this.OuterWing.rotateAngleY = -90.0F / this.radianF;
        this.OuterWingR.rotateAngleY = 90.0F / this.radianF;
      }

      this.InnerWingR.rotationPointY = this.InnerWing.rotationPointY;
      this.InnerWingR.rotationPointZ = this.InnerWing.rotationPointZ;











      this.InnerWing.rotationPointX += MathHelper.cos(WingRot) * 12.0F;
      this.InnerWingR.rotationPointX -= MathHelper.cos(WingRot) * 12.0F;

      this.MidWing.rotationPointY = this.InnerWing.rotationPointY;
      this.MidWingR.rotationPointY = this.InnerWing.rotationPointY;
      this.InnerWing.rotationPointY += MathHelper.sin(WingRot) * 12.0F;
      this.InnerWingR.rotationPointY += MathHelper.sin(WingRot) * 12.0F;

      this.MidWing.rotationPointZ = this.InnerWing.rotationPointZ;
      this.MidWingR.rotationPointZ = this.InnerWing.rotationPointZ;
      this.OuterWing.rotationPointZ = this.InnerWing.rotationPointZ;
      this.OuterWingR.rotationPointZ = this.InnerWing.rotationPointZ;

      this.MidWing.rotateAngleZ = WingRot;
      this.InnerWing.rotateAngleZ = WingRot;
      this.OuterWing.rotateAngleZ = WingRot;

      this.InnerWingR.rotateAngleZ = -WingRot;
      this.MidWingR.rotateAngleZ = -WingRot;
      this.OuterWingR.rotateAngleZ = -WingRot;

      if (this.hasStinger)
      {
        if (!this.poisoning) {

          this.STailRoot.rotateAngleX = 33.0F / this.radianF;
          this.STailRoot.rotationPointY = stingYOffset;
          this.STailRoot.rotationPointZ = stingZOffset;

          this.STail2.rotateAngleX = 54.5F / this.radianF;
          this.STail2.rotationPointY = stingYOffset;
          this.STail2.rotationPointZ = stingZOffset;

          this.STail3.rotateAngleX = 95.1F / this.radianF;
          this.STail3.rotationPointY = stingYOffset;
          this.STail3.rotationPointZ = stingZOffset;

          this.STail4.rotateAngleX = 141.8F / this.radianF;
          this.STail4.rotationPointY = stingYOffset;
          this.STail4.rotationPointZ = stingZOffset;

          this.STail5.rotateAngleX = 173.9F / this.radianF;
          this.STail5.rotationPointY = stingYOffset;
          this.STail5.rotationPointZ = stingZOffset;

          this.StingerLump.rotateAngleX = 116.4F / this.radianF;
          this.StingerLump.rotationPointY = stingYOffset;
          this.StingerLump.rotationPointZ = stingZOffset;

          this.Stinger.rotateAngleX = 69.5F / this.radianF;
          this.Stinger.rotationPointY = stingYOffset;
          this.Stinger.rotationPointZ = stingZOffset;
        }
        else if (!this.isSitting) {

          this.STailRoot.rotateAngleX = 95.2F / this.radianF;
          this.STailRoot.rotationPointY = 14.5F;
          this.STailRoot.rotationPointZ = 2.0F;

          this.STail2.rotateAngleX = 128.5F / this.radianF;
          this.STail2.rotationPointY = 15.0F;
          this.STail2.rotationPointZ = 4.0F;

          this.STail3.rotateAngleX = 169.0F / this.radianF;
          this.STail3.rotationPointY = 14.0F;
          this.STail3.rotationPointZ = 3.8F;

          this.STail4.rotateAngleX = 177.0F / this.radianF;
          this.STail4.rotationPointY = 13.5F;
          this.STail4.rotationPointZ = -8.5F;

          this.STail5.rotateAngleX = 180.0F / this.radianF;
          this.STail5.rotationPointY = 11.5F;
          this.STail5.rotationPointZ = -17.0F;

          this.StingerLump.rotateAngleX = 35.4F / this.radianF;
          this.StingerLump.rotationPointY = -4.0F;
          this.StingerLump.rotationPointZ = -28.0F;

          this.Stinger.rotateAngleX = 25.5F / this.radianF;
          this.Stinger.rotationPointY = 4.0F;
          this.Stinger.rotationPointZ = -29.0F;
        }
      }
    }
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelNewBigCat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
