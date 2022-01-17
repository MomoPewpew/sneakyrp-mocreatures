/*      */ package drzhark.mocreatures.client.model;
/*      */ 
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityBigCat;
/*      */ import net.minecraft.client.model.ModelBase;
/*      */ import net.minecraft.client.model.ModelRenderer;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ 
/*      */ public class MoCModelNewBigCat
/*      */   extends ModelBase
/*      */ {
/*      */   ModelRenderer RightHindFoot;
/*      */   ModelRenderer Stinger;
/*      */   ModelRenderer RightHindUpperLeg;
/*      */   ModelRenderer RightAnkle;
/*      */   ModelRenderer RightHindLowerLeg;
/*      */   ModelRenderer Ass;
/*      */   ModelRenderer TailTusk;
/*      */   ModelRenderer LeftChinBeard;
/*      */   ModelRenderer NeckBase;
/*      */   ModelRenderer RightEar;
/*      */   ModelRenderer LeftEar;
/*      */   ModelRenderer ForeheadHair;
/*      */   ModelRenderer LeftHarness;
/*      */   ModelRenderer RightHarness;
/*      */   ModelRenderer LeftUpperLip;
/*      */   ModelRenderer RightChinBeard;
/*      */   ModelRenderer LeftHindUpperLeg;
/*      */   ModelRenderer LeftHindLowerLeg;
/*      */   ModelRenderer LeftHindFoot;
/*      */   ModelRenderer LeftAnkle;
/*      */   ModelRenderer InsideMouth;
/*      */   ModelRenderer RightUpperLip;
/*      */   ModelRenderer LowerJawTeeth;
/*      */   ModelRenderer Nose;
/*      */   ModelRenderer LeftFang;
/*      */   ModelRenderer UpperTeeth;
/*      */   ModelRenderer RightFang;
/*      */   ModelRenderer LowerJaw;
/*      */   ModelRenderer SaddleFront;
/*      */   ModelRenderer LeftUpperLeg;
/*      */   ModelRenderer LeftLowerLeg;
/*      */   ModelRenderer LeftFrontFoot;
/*      */   ModelRenderer LeftClaw2;
/*      */   ModelRenderer LeftClaw1;
/*      */   ModelRenderer LeftClaw3;
/*      */   ModelRenderer RightClaw1;
/*      */   ModelRenderer RightClaw2;
/*      */   ModelRenderer RightClaw3;
/*      */   ModelRenderer RightFrontFoot;
/*      */   ModelRenderer RightLowerLeg;
/*      */   ModelRenderer RightUpperLeg;
/*      */   ModelRenderer Head;
/*      */   ModelRenderer ChinHair;
/*      */   ModelRenderer NeckHair;
/*      */   ModelRenderer Mane;
/*      */   ModelRenderer InnerWing;
/*      */   ModelRenderer MidWing;
/*      */   ModelRenderer OuterWing;
/*      */   ModelRenderer InnerWingR;
/*      */   ModelRenderer MidWingR;
/*      */   ModelRenderer OuterWingR;
/*      */   ModelRenderer Abdomen;
/*      */   ModelRenderer STailRoot;
/*      */   ModelRenderer STail2;
/*      */   ModelRenderer STail3;
/*      */   ModelRenderer STail4;
/*      */   ModelRenderer STail5;
/*      */   ModelRenderer StingerLump;
/*      */   ModelRenderer TailRoot;
/*      */   ModelRenderer Tail2;
/*      */   ModelRenderer Tail3;
/*      */   ModelRenderer Tail4;
/*      */   ModelRenderer TailTip;
/*      */   ModelRenderer Chest;
/*      */   ModelRenderer SaddleBack;
/*      */   ModelRenderer LeftFootRing;
/*      */   ModelRenderer Saddle;
/*      */   ModelRenderer LeftFootHarness;
/*      */   ModelRenderer RightFootHarness;
/*      */   ModelRenderer RightFootRing;
/*      */   ModelRenderer HeadBack;
/*      */   ModelRenderer HarnessStick;
/*      */   ModelRenderer NeckHarness;
/*      */   ModelRenderer Collar;
/*      */   ModelRenderer StorageChest;
/*   89 */   private float radianF = 57.29578F;
/*      */   
/*      */   protected boolean hasMane;
/*      */   protected boolean isRidden;
/*      */   private boolean isChested;
/*      */   protected boolean isSaddled;
/*      */   protected boolean flapwings;
/*      */   protected boolean onAir;
/*      */   private boolean diving;
/*      */   private boolean isSitting;
/*      */   protected boolean isFlyer;
/*      */   protected boolean floating;
/*      */   protected boolean poisoning;
/*      */   protected boolean isTamed;
/*      */   protected boolean movingTail;
/*  104 */   private float lLegMov = 0.0F;
/*  105 */   private float rLegMov = 0.0F;
/*      */   protected int openMouthCounter;
/*      */   protected boolean hasSaberTeeth;
/*      */   protected boolean hasChest;
/*      */   protected boolean hasStinger;
/*      */   protected boolean isGhost;
/*      */   protected boolean isMovingVertically;
/*      */   
/*      */   public MoCModelNewBigCat() {
/*  114 */     this.textureWidth = 128;
/*  115 */     this.textureHeight = 128;
/*      */     
/*  117 */     this.Chest = new ModelRenderer(this, 0, 18);
/*  118 */     this.Chest.addBox(-3.5F, 0.0F, -8.0F, 7, 8, 9);
/*  119 */     this.Chest.setRotationPoint(0.0F, 8.0F, 0.0F);
/*      */     
/*  121 */     this.NeckBase = new ModelRenderer(this, 0, 7);
/*  122 */     this.NeckBase.addBox(-2.5F, 0.0F, -2.5F, 5, 6, 5);
/*  123 */     this.NeckBase.setRotationPoint(0.0F, -0.5F, -8.0F);
/*  124 */     setRotation(this.NeckBase, -14.0F / this.radianF, 0.0F, 0.0F);
/*  125 */     this.Chest.addChild(this.NeckBase);
/*      */     
/*  127 */     this.Collar = new ModelRenderer(this, 18, 0);
/*  128 */     this.Collar.addBox(-2.5F, 0.0F, 0.0F, 5, 4, 1, 0.0F);
/*  129 */     this.Collar.setRotationPoint(0.0F, 6.0F, -2.0F);
/*  130 */     setRotation(this.Collar, 20.0F / this.radianF, 0.0F, 0.0F);
/*  131 */     this.NeckBase.addChild(this.Collar);
/*      */     
/*  133 */     this.HeadBack = new ModelRenderer(this, 0, 0);
/*  134 */     this.HeadBack.addBox(-2.51F, -2.5F, -1.0F, 5, 5, 2);
/*  135 */     this.HeadBack.setRotationPoint(0.0F, 2.7F, -2.9F);
/*  136 */     setRotation(this.HeadBack, 14.0F / this.radianF, 0.0F, 0.0F);
/*  137 */     this.NeckBase.addChild(this.HeadBack);
/*      */     
/*  139 */     this.NeckHarness = new ModelRenderer(this, 85, 32);
/*  140 */     this.NeckHarness.addBox(-3.0F, -3.0F, -2.0F, 6, 6, 2);
/*  141 */     this.NeckHarness.setRotationPoint(0.0F, 0.0F, 0.95F);
/*  142 */     this.HeadBack.addChild(this.NeckHarness);
/*      */     
/*  144 */     this.HarnessStick = new ModelRenderer(this, 85, 42);
/*  145 */     this.HarnessStick.addBox(-3.5F, -0.5F, -0.5F, 7, 1, 1);
/*  146 */     this.HarnessStick.setRotationPoint(0.0F, -1.8F, 0.5F);
/*  147 */     setRotation(this.HarnessStick, 45.0F / this.radianF, 0.0F, 0.0F);
/*  148 */     this.HeadBack.addChild(this.HarnessStick);
/*      */     
/*  150 */     this.LeftHarness = new ModelRenderer(this, 85, 32);
/*  151 */     this.LeftHarness.addBox(3.2F, -0.6F, 1.5F, 0, 1, 9);
/*  152 */     this.LeftHarness.setRotationPoint(0.0F, 8.6F, -13.0F);
/*  153 */     setRotation(this.LeftHarness, 25.0F / this.radianF, 0.0F, 0.0F);
/*      */     
/*  155 */     this.RightHarness = new ModelRenderer(this, 85, 31);
/*  156 */     this.RightHarness.addBox(-3.2F, -0.6F, 1.5F, 0, 1, 9);
/*  157 */     this.RightHarness.setRotationPoint(0.0F, 8.6F, -13.0F);
/*  158 */     setRotation(this.RightHarness, 25.0F / this.radianF, 0.0F, 0.0F);
/*      */     
/*  160 */     this.Head = new ModelRenderer(this, 32, 0);
/*  161 */     this.Head.addBox(-3.5F, -3.0F, -2.0F, 7, 6, 4);
/*  162 */     this.Head.setRotationPoint(0.0F, 0.2F, -2.2F);
/*  163 */     this.HeadBack.addChild(this.Head);
/*      */     
/*  165 */     this.Nose = new ModelRenderer(this, 46, 19);
/*  166 */     this.Nose.addBox(-1.5F, -1.0F, -2.0F, 3, 2, 4);
/*  167 */     this.Nose.setRotationPoint(0.0F, 0.0F, -3.0F);
/*  168 */     setRotation(this.Nose, 27.0F / this.radianF, 0.0F, 0.0F);
/*  169 */     this.Head.addChild(this.Nose);
/*      */     
/*  171 */     this.RightUpperLip = new ModelRenderer(this, 34, 19);
/*  172 */     this.RightUpperLip.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 4);
/*  173 */     this.RightUpperLip.setRotationPoint(-1.25F, 1.0F, -2.8F);
/*  174 */     setRotation(this.RightUpperLip, 10.0F / this.radianF, 2.0F / this.radianF, -15.0F / this.radianF);
/*  175 */     this.Head.addChild(this.RightUpperLip);
/*      */     
/*  177 */     this.LeftUpperLip = new ModelRenderer(this, 34, 25);
/*  178 */     this.LeftUpperLip.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 4);
/*  179 */     this.LeftUpperLip.setRotationPoint(1.25F, 1.0F, -2.8F);
/*  180 */     setRotation(this.LeftUpperLip, 10.0F / this.radianF, -2.0F / this.radianF, 15.0F / this.radianF);
/*  181 */     this.Head.addChild(this.LeftUpperLip);
/*      */     
/*  183 */     this.UpperTeeth = new ModelRenderer(this, 20, 7);
/*  184 */     this.UpperTeeth.addBox(-1.5F, -1.0F, -1.5F, 3, 2, 3);
/*  185 */     this.UpperTeeth.setRotationPoint(0.0F, 2.0F, -2.5F);
/*  186 */     setRotation(this.UpperTeeth, 15.0F / this.radianF, 0.0F, 0.0F);
/*  187 */     this.Head.addChild(this.UpperTeeth);
/*      */     
/*  189 */     this.LeftFang = new ModelRenderer(this, 44, 10);
/*  190 */     this.LeftFang.addBox(-0.5F, -1.5F, -0.5F, 1, 3, 1);
/*  191 */     this.LeftFang.setRotationPoint(1.2F, 2.8F, -3.4F);
/*  192 */     setRotation(this.LeftFang, 15.0F / this.radianF, 0.0F, 0.0F);
/*  193 */     this.Head.addChild(this.LeftFang);
/*      */     
/*  195 */     this.RightFang = new ModelRenderer(this, 48, 10);
/*  196 */     this.RightFang.addBox(-0.5F, -1.5F, -0.5F, 1, 3, 1);
/*  197 */     this.RightFang.setRotationPoint(-1.2F, 2.8F, -3.4F);
/*  198 */     setRotation(this.RightFang, 15.0F / this.radianF, 0.0F, 0.0F);
/*  199 */     this.Head.addChild(this.RightFang);
/*      */     
/*  201 */     this.InsideMouth = new ModelRenderer(this, 50, 0);
/*  202 */     this.InsideMouth.addBox(-1.5F, -1.0F, -1.0F, 3, 2, 2);
/*  203 */     this.InsideMouth.setRotationPoint(0.0F, 2.0F, -1.0F);
/*  204 */     this.Head.addChild(this.InsideMouth);
/*      */     
/*  206 */     this.LowerJaw = new ModelRenderer(this, 46, 25);
/*  207 */     this.LowerJaw.addBox(-1.5F, -1.0F, -4.0F, 3, 2, 4);
/*  208 */     this.LowerJaw.setRotationPoint(0.0F, 2.1F, 0.0F);
/*  209 */     this.Head.addChild(this.LowerJaw);
/*      */     
/*  211 */     this.LowerJawTeeth = new ModelRenderer(this, 20, 12);
/*  212 */     this.LowerJawTeeth.addBox(-1.0F, 0.0F, -1.0F, 2, 1, 2);
/*  213 */     this.LowerJawTeeth.setRotationPoint(0.0F, -1.8F, -2.7F);
/*  214 */     this.LowerJawTeeth.mirror = true;
/*  215 */     this.LowerJaw.addChild(this.LowerJawTeeth);
/*      */     
/*  217 */     this.ChinHair = new ModelRenderer(this, 76, 7);
/*  218 */     this.ChinHair.addBox(-2.5F, 0.0F, -2.0F, 5, 6, 4);
/*  219 */     this.ChinHair.setRotationPoint(0.0F, 0.0F, 1.0F);
/*  220 */     this.LowerJaw.addChild(this.ChinHair);
/*      */     
/*  222 */     this.LeftChinBeard = new ModelRenderer(this, 48, 10);
/*  223 */     this.LeftChinBeard.addBox(-1.0F, -2.5F, -2.0F, 2, 5, 4);
/*  224 */     this.LeftChinBeard.setRotationPoint(3.6F, 0.0F, 0.25F);
/*  225 */     setRotation(this.LeftChinBeard, 0.0F, 30.0F / this.radianF, 0.0F);
/*  226 */     this.Head.addChild(this.LeftChinBeard);
/*      */     
/*  228 */     this.RightChinBeard = new ModelRenderer(this, 36, 10);
/*  229 */     this.RightChinBeard.addBox(-1.0F, -2.5F, -2.0F, 2, 5, 4);
/*  230 */     this.RightChinBeard.setRotationPoint(-3.6F, 0.0F, 0.25F);
/*  231 */     setRotation(this.RightChinBeard, 0.0F, -30.0F / this.radianF, 0.0F);
/*  232 */     this.Head.addChild(this.RightChinBeard);
/*      */     
/*  234 */     this.ForeheadHair = new ModelRenderer(this, 88, 0);
/*  235 */     this.ForeheadHair.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3);
/*  236 */     this.ForeheadHair.setRotationPoint(0.0F, -3.2F, 0.0F);
/*  237 */     setRotation(this.ForeheadHair, 10.0F / this.radianF, 0.0F, 0.0F);
/*  238 */     this.Head.addChild(this.ForeheadHair);
/*      */     
/*  240 */     this.Mane = new ModelRenderer(this, 94, 0);
/*  241 */     this.Mane.addBox(-5.5F, -5.5F, -3.0F, 11, 11, 6);
/*  242 */     this.Mane.setRotationPoint(0.0F, 0.7F, 3.7F);
/*  243 */     setRotation(this.Mane, -5.0F / this.radianF, 0.0F, 0.0F);
/*  244 */     this.Head.addChild(this.Mane);
/*      */     
/*  246 */     this.RightEar = new ModelRenderer(this, 54, 7);
/*  247 */     this.RightEar.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 1);
/*  248 */     this.RightEar.setRotationPoint(-2.7F, -3.5F, 1.0F);
/*  249 */     setRotation(this.RightEar, 0.0F, 0.0F, -15.0F / this.radianF);
/*  250 */     this.Head.addChild(this.RightEar);
/*      */     
/*  252 */     this.LeftEar = new ModelRenderer(this, 54, 4);
/*  253 */     this.LeftEar.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 1);
/*  254 */     this.LeftEar.setRotationPoint(2.7F, -3.5F, 1.0F);
/*  255 */     setRotation(this.LeftEar, 0.0F, 0.0F, 15.0F / this.radianF);
/*  256 */     this.Head.addChild(this.LeftEar);
/*      */     
/*  258 */     this.NeckHair = new ModelRenderer(this, 108, 17);
/*  259 */     this.NeckHair.addBox(-2.0F, -1.0F, -3.0F, 4, 2, 6);
/*  260 */     this.NeckHair.setRotationPoint(0.0F, -0.5F, 3.0F);
/*  261 */     setRotation(this.NeckHair, -10.6F / this.radianF, 0.0F, 0.0F);
/*  262 */     this.NeckBase.addChild(this.NeckHair);
/*      */     
/*  264 */     this.InnerWing = new ModelRenderer(this, 26, 115);
/*  265 */     this.InnerWing.addBox(0.0F, 0.0F, 0.0F, 7, 2, 11);
/*  266 */     this.InnerWing.setRotationPoint(4.0F, 9.0F, -7.0F);
/*  267 */     setRotation(this.InnerWing, 0.0F, -20.0F / this.radianF, 0.0F);
/*      */     
/*  269 */     this.MidWing = new ModelRenderer(this, 36, 89);
/*  270 */     this.MidWing.addBox(1.0F, 0.1F, 1.0F, 12, 2, 11);
/*  271 */     this.MidWing.setRotationPoint(4.0F, 9.0F, -7.0F);
/*  272 */     setRotation(this.MidWing, 0.0F, 5.0F / this.radianF, 0.0F);
/*      */     
/*  274 */     this.OuterWing = new ModelRenderer(this, 62, 115);
/*  275 */     this.OuterWing.addBox(0.0F, 0.0F, 0.0F, 22, 2, 11);
/*  276 */     this.OuterWing.setRotationPoint(16.0F, 9.0F, -7.0F);
/*  277 */     setRotation(this.OuterWing, 0.0F, -18.0F / this.radianF, 0.0F);
/*      */     
/*  279 */     this.InnerWingR = new ModelRenderer(this, 26, 102);
/*  280 */     this.InnerWingR.addBox(-7.0F, 0.0F, 0.0F, 7, 2, 11);
/*  281 */     this.InnerWingR.setRotationPoint(-4.0F, 9.0F, -7.0F);
/*  282 */     setRotation(this.InnerWingR, 0.0F, 20.0F / this.radianF, 0.0F);
/*      */     
/*  284 */     this.MidWingR = new ModelRenderer(this, 82, 89);
/*  285 */     this.MidWingR.addBox(-13.0F, 0.1F, 1.0F, 12, 2, 11);
/*  286 */     this.MidWingR.setRotationPoint(-4.0F, 9.0F, -7.0F);
/*  287 */     setRotation(this.MidWingR, 0.0F, -5.0F / this.radianF, 0.0F);
/*      */     
/*  289 */     this.OuterWingR = new ModelRenderer(this, 62, 102);
/*  290 */     this.OuterWingR.addBox(-22.0F, 0.0F, 0.0F, 22, 2, 11);
/*  291 */     this.OuterWingR.setRotationPoint(-16.0F, 9.0F, -7.0F);
/*  292 */     setRotation(this.OuterWingR, 0.0F, 18.0F / this.radianF, 0.0F);
/*      */     
/*  294 */     this.Abdomen = new ModelRenderer(this, 0, 35);
/*  295 */     this.Abdomen.addBox(-3.0F, 0.0F, 0.0F, 6, 7, 7);
/*  296 */     this.Abdomen.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  297 */     setRotation(this.Abdomen, -0.0523599F, 0.0F, 0.0F);
/*  298 */     this.Chest.addChild(this.Abdomen);
/*      */     
/*  300 */     this.Ass = new ModelRenderer(this, 0, 49);
/*  301 */     this.Ass.addBox(-2.5F, 0.0F, 0.0F, 5, 5, 3);
/*  302 */     this.Ass.setRotationPoint(0.0F, 0.0F, 7.0F);
/*  303 */     setRotation(this.Ass, -20.0F / this.radianF, 0.0F, 0.0F);
/*  304 */     this.Abdomen.addChild(this.Ass);
/*      */     
/*  306 */     this.TailRoot = new ModelRenderer(this, 96, 83);
/*  307 */     this.TailRoot.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2);
/*  308 */     this.TailRoot.setRotationPoint(0.0F, 1.0F, 7.0F);
/*  309 */     setRotation(this.TailRoot, 87.0F / this.radianF, 0.0F, 0.0F);
/*  310 */     this.Abdomen.addChild(this.TailRoot);
/*      */     
/*  312 */     this.Tail2 = new ModelRenderer(this, 96, 75);
/*  313 */     this.Tail2.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2);
/*  314 */     this.Tail2.setRotationPoint(-0.01F, 3.5F, 0.0F);
/*  315 */     setRotation(this.Tail2, -30.0F / this.radianF, 0.0F, 0.0F);
/*  316 */     this.TailRoot.addChild(this.Tail2);
/*      */     
/*  318 */     this.Tail3 = new ModelRenderer(this, 96, 67);
/*  319 */     this.Tail3.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2);
/*  320 */     this.Tail3.setRotationPoint(0.01F, 5.5F, 0.0F);
/*  321 */     setRotation(this.Tail3, -17.0F / this.radianF, 0.0F, 0.0F);
/*  322 */     this.Tail2.addChild(this.Tail3);
/*      */     
/*  324 */     this.Tail4 = new ModelRenderer(this, 96, 61);
/*  325 */     this.Tail4.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2);
/*  326 */     this.Tail4.setRotationPoint(-0.01F, 5.5F, 0.0F);
/*  327 */     setRotation(this.Tail4, 21.0F / this.radianF, 0.0F, 0.0F);
/*  328 */     this.Tail3.addChild(this.Tail4);
/*      */     
/*  330 */     this.TailTip = new ModelRenderer(this, 96, 55);
/*  331 */     this.TailTip.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2);
/*  332 */     this.TailTip.setRotationPoint(0.01F, 3.5F, 0.0F);
/*  333 */     setRotation(this.TailTip, 21.0F / this.radianF, 0.0F, 0.0F);
/*  334 */     this.Tail4.addChild(this.TailTip);
/*      */     
/*  336 */     this.TailTusk = new ModelRenderer(this, 96, 49);
/*  337 */     this.TailTusk.addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3);
/*  338 */     this.TailTusk.setRotationPoint(0.0F, 3.5F, 0.0F);
/*  339 */     setRotation(this.TailTusk, 21.0F / this.radianF, 0.0F, 0.0F);
/*  340 */     this.Tail4.addChild(this.TailTusk);
/*      */     
/*  342 */     this.Saddle = new ModelRenderer(this, 79, 18);
/*  343 */     this.Saddle.addBox(-4.0F, -1.0F, -3.0F, 8, 2, 6);
/*  344 */     this.Saddle.setRotationPoint(0.0F, 0.5F, -1.0F);
/*  345 */     this.Chest.addChild(this.Saddle);
/*      */     
/*  347 */     this.SaddleFront = new ModelRenderer(this, 101, 26);
/*  348 */     this.SaddleFront.addBox(-2.5F, -1.0F, -1.5F, 5, 2, 3);
/*  349 */     this.SaddleFront.setRotationPoint(0.0F, -1.0F, -1.5F);
/*  350 */     setRotation(this.SaddleFront, -10.6F / this.radianF, 0.0F, 0.0F);
/*  351 */     this.Saddle.addChild(this.SaddleFront);
/*      */     
/*  353 */     this.SaddleBack = new ModelRenderer(this, 77, 26);
/*  354 */     this.SaddleBack.addBox(-4.0F, -2.0F, -2.0F, 8, 2, 4);
/*  355 */     this.SaddleBack.setRotationPoint(0.0F, 0.7F, 4.0F);
/*  356 */     setRotation(this.SaddleBack, 12.78F / this.radianF, 0.0F, 0.0F);
/*  357 */     this.Saddle.addChild(this.SaddleBack);
/*      */     
/*  359 */     this.LeftFootHarness = new ModelRenderer(this, 81, 18);
/*  360 */     this.LeftFootHarness.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1);
/*  361 */     this.LeftFootHarness.setRotationPoint(4.0F, 0.0F, 0.5F);
/*  362 */     this.Saddle.addChild(this.LeftFootHarness);
/*      */     
/*  364 */     this.LeftFootRing = new ModelRenderer(this, 107, 31);
/*  365 */     this.LeftFootRing.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
/*  366 */     this.LeftFootRing.setRotationPoint(-0.5F, 5.0F, -1.0F);
/*  367 */     this.LeftFootHarness.addChild(this.LeftFootRing);
/*      */     
/*  369 */     this.RightFootHarness = new ModelRenderer(this, 101, 18);
/*  370 */     this.RightFootHarness.addBox(-0.5F, 0.0F, -0.5F, 1, 5, 1);
/*  371 */     this.RightFootHarness.setRotationPoint(-4.0F, 0.0F, 0.5F);
/*  372 */     this.Saddle.addChild(this.RightFootHarness);
/*      */     
/*  374 */     this.RightFootRing = new ModelRenderer(this, 101, 31);
/*  375 */     this.RightFootRing.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
/*  376 */     this.RightFootRing.setRotationPoint(-0.5F, 5.0F, -1.0F);
/*  377 */     this.RightFootHarness.addChild(this.RightFootRing);
/*      */     
/*  379 */     this.StorageChest = new ModelRenderer(this, 32, 59);
/*  380 */     this.StorageChest.addBox(-5.0F, -2.0F, -2.5F, 10, 4, 5);
/*  381 */     this.StorageChest.setRotationPoint(0.0F, -2.0F, 5.5F);
/*  382 */     setRotation(this.StorageChest, -90.0F / this.radianF, 0.0F, 0.0F);
/*  383 */     this.Abdomen.addChild(this.StorageChest);
/*      */     
/*  385 */     this.STailRoot = new ModelRenderer(this, 104, 79);
/*  386 */     this.STailRoot.addBox(-3.0F, 4.0F, 5.0F, 6, 4, 6);
/*  387 */     this.STailRoot.setRotationPoint(0.0F, 8.0F, 0.0F);
/*  388 */     this.STailRoot.setTextureSize(128, 128);
/*  389 */     this.STailRoot.mirror = true;
/*  390 */     setRotation(this.STailRoot, 0.5796765F, 0.0F, 0.0F);
/*  391 */     this.STail2 = new ModelRenderer(this, 106, 69);
/*  392 */     this.STail2.addBox(-2.5F, 7.5F, 7.3F, 5, 4, 6);
/*  393 */     this.STail2.setRotationPoint(0.0F, 8.0F, 0.0F);
/*  394 */     this.STail2.setTextureSize(128, 128);
/*  395 */     this.STail2.mirror = true;
/*  396 */     setRotation(this.STail2, 0.9514626F, 0.0F, 0.0F);
/*  397 */     this.STail3 = new ModelRenderer(this, 108, 60);
/*  398 */     this.STail3.addBox(-2.0F, 13.5F, 3.3F, 4, 3, 6);
/*  399 */     this.STail3.setRotationPoint(0.0F, 8.0F, 0.0F);
/*  400 */     this.STail3.setTextureSize(128, 128);
/*  401 */     this.STail3.mirror = true;
/*  402 */     setRotation(this.STail3, 1.660128F, 0.0F, 0.0F);
/*  403 */     this.STail4 = new ModelRenderer(this, 108, 51);
/*  404 */     this.STail4.addBox(-2.0F, 15.2F, -5.3F, 4, 3, 6);
/*  405 */     this.STail4.setRotationPoint(0.0F, 8.0F, 0.0F);
/*  406 */     this.STail4.setTextureSize(128, 128);
/*  407 */     this.STail4.mirror = true;
/*  408 */     setRotation(this.STail4, 2.478058F, 0.0F, 0.0F);
/*  409 */     this.STail5 = new ModelRenderer(this, 108, 42);
/*  410 */     this.STail5.addBox(-2.0F, 12.9F, -9.0F, 4, 3, 6);
/*  411 */     this.STail5.setRotationPoint(0.0F, 8.0F, 0.0F);
/*  412 */     this.STail5.setTextureSize(128, 128);
/*  413 */     this.STail5.mirror = true;
/*  414 */     setRotation(this.STail5, 3.035737F, 0.0F, 0.0F);
/*  415 */     this.StingerLump = new ModelRenderer(this, 112, 34);
/*  416 */     this.StingerLump.addBox(-1.5F, 7.9F, 6.0F, 3, 3, 5);
/*  417 */     this.StingerLump.setRotationPoint(0.0F, 8.0F, 0.0F);
/*  418 */     this.StingerLump.setTextureSize(128, 128);
/*  419 */     this.StingerLump.mirror = true;
/*  420 */     setRotation(this.StingerLump, 2.031914F, 0.0F, 0.0F);
/*  421 */     this.Stinger = new ModelRenderer(this, 118, 29);
/*  422 */     this.Stinger.addBox(-0.5F, 1.9F, 8.0F, 1, 1, 4);
/*  423 */     this.Stinger.setRotationPoint(0.0F, 8.0F, 0.0F);
/*  424 */     this.Stinger.setTextureSize(128, 128);
/*  425 */     this.Stinger.mirror = true;
/*  426 */     setRotation(this.Stinger, 1.213985F, 0.0F, 0.0F);
/*      */     
/*  428 */     this.LeftUpperLeg = new ModelRenderer(this, 0, 96);
/*  429 */     this.LeftUpperLeg.addBox(-1.5F, 0.0F, -2.0F, 3, 7, 4);
/*  430 */     this.LeftUpperLeg.setRotationPoint(3.99F, 3.0F, -7.0F);
/*  431 */     setRotation(this.LeftUpperLeg, 15.0F / this.radianF, 0.0F, 0.0F);
/*  432 */     this.Chest.addChild(this.LeftUpperLeg);
/*      */     
/*  434 */     this.LeftLowerLeg = new ModelRenderer(this, 0, 107);
/*  435 */     this.LeftLowerLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3);
/*  436 */     this.LeftLowerLeg.setRotationPoint(-0.01F, 6.5F, 0.2F);
/*  437 */     setRotation(this.LeftLowerLeg, -21.5F / this.radianF, 0.0F, 0.0F);
/*  438 */     this.LeftUpperLeg.addChild(this.LeftLowerLeg);
/*      */     
/*  440 */     this.LeftFrontFoot = new ModelRenderer(this, 0, 116);
/*  441 */     this.LeftFrontFoot.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4);
/*  442 */     this.LeftFrontFoot.setRotationPoint(0.0F, 5.0F, -1.0F);
/*  443 */     setRotation(this.LeftFrontFoot, 6.5F / this.radianF, 0.0F, 0.0F);
/*  444 */     this.LeftLowerLeg.addChild(this.LeftFrontFoot);
/*      */     
/*  446 */     this.LeftClaw1 = new ModelRenderer(this, 16, 125);
/*  447 */     this.LeftClaw1.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2);
/*  448 */     this.LeftClaw1.setRotationPoint(-1.3F, 1.2F, -3.0F);
/*  449 */     setRotation(this.LeftClaw1, 45.0F / this.radianF, 0.0F, -1.0F / this.radianF);
/*  450 */     this.LeftFrontFoot.addChild(this.LeftClaw1);
/*      */     
/*  452 */     this.LeftClaw2 = new ModelRenderer(this, 16, 125);
/*  453 */     this.LeftClaw2.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2);
/*  454 */     this.LeftClaw2.setRotationPoint(0.0F, 1.1F, -3.0F);
/*  455 */     setRotation(this.LeftClaw2, 45.0F / this.radianF, 0.0F, 0.0F);
/*  456 */     this.LeftFrontFoot.addChild(this.LeftClaw2);
/*      */     
/*  458 */     this.LeftClaw3 = new ModelRenderer(this, 16, 125);
/*  459 */     this.LeftClaw3.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2);
/*  460 */     this.LeftClaw3.setRotationPoint(1.3F, 1.2F, -3.0F);
/*  461 */     setRotation(this.LeftClaw3, 45.0F / this.radianF, 0.0F, 1.0F / this.radianF);
/*  462 */     this.LeftFrontFoot.addChild(this.LeftClaw3);
/*      */     
/*  464 */     this.RightUpperLeg = new ModelRenderer(this, 14, 96);
/*  465 */     this.RightUpperLeg.addBox(-1.5F, 0.0F, -2.0F, 3, 7, 4);
/*  466 */     this.RightUpperLeg.setRotationPoint(-3.99F, 3.0F, -7.0F);
/*  467 */     setRotation(this.RightUpperLeg, 15.0F / this.radianF, 0.0F, 0.0F);
/*  468 */     this.Chest.addChild(this.RightUpperLeg);
/*      */     
/*  470 */     this.RightLowerLeg = new ModelRenderer(this, 12, 107);
/*  471 */     this.RightLowerLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 6, 3);
/*  472 */     this.RightLowerLeg.setRotationPoint(0.01F, 6.5F, 0.2F);
/*  473 */     setRotation(this.RightLowerLeg, -21.5F / this.radianF, 0.0F, 0.0F);
/*  474 */     this.RightUpperLeg.addChild(this.RightLowerLeg);
/*      */     
/*  476 */     this.RightFrontFoot = new ModelRenderer(this, 0, 122);
/*  477 */     this.RightFrontFoot.addBox(-2.0F, 0.0F, -2.0F, 4, 2, 4);
/*  478 */     this.RightFrontFoot.setRotationPoint(0.0F, 5.0F, -1.0F);
/*  479 */     setRotation(this.RightFrontFoot, 6.5F / this.radianF, 0.0F, 0.0F);
/*  480 */     this.RightLowerLeg.addChild(this.RightFrontFoot);
/*      */     
/*  482 */     this.RightClaw1 = new ModelRenderer(this, 16, 125);
/*  483 */     this.RightClaw1.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2);
/*  484 */     this.RightClaw1.setRotationPoint(-1.3F, 1.2F, -3.0F);
/*  485 */     setRotation(this.RightClaw1, 45.0F / this.radianF, 0.0F, -1.0F / this.radianF);
/*  486 */     this.RightFrontFoot.addChild(this.RightClaw1);
/*      */     
/*  488 */     this.RightClaw2 = new ModelRenderer(this, 16, 125);
/*  489 */     this.RightClaw2.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2);
/*  490 */     this.RightClaw2.setRotationPoint(0.0F, 1.1F, -3.0F);
/*  491 */     setRotation(this.RightClaw2, 45.0F / this.radianF, 0.0F, 0.0F);
/*  492 */     this.RightFrontFoot.addChild(this.RightClaw2);
/*      */     
/*  494 */     this.RightClaw3 = new ModelRenderer(this, 16, 125);
/*  495 */     this.RightClaw3.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2);
/*  496 */     this.RightClaw3.setRotationPoint(1.3F, 1.2F, -3.0F);
/*  497 */     setRotation(this.RightClaw3, 45.0F / this.radianF, 0.0F, 1.0F / this.radianF);
/*  498 */     this.RightFrontFoot.addChild(this.RightClaw3);
/*      */     
/*  500 */     this.LeftHindUpperLeg = new ModelRenderer(this, 0, 67);
/*  501 */     this.LeftHindUpperLeg.addBox(-2.0F, -1.0F, -1.5F, 3, 8, 5);
/*  502 */     this.LeftHindUpperLeg.setRotationPoint(3.0F, 3.0F, 6.8F);
/*  503 */     setRotation(this.LeftHindUpperLeg, -25.0F / this.radianF, 0.0F, 0.0F);
/*  504 */     this.Abdomen.addChild(this.LeftHindUpperLeg);
/*      */     
/*  506 */     this.LeftAnkle = new ModelRenderer(this, 0, 80);
/*  507 */     this.LeftAnkle.addBox(-1.0F, 0.0F, -1.5F, 2, 3, 3);
/*  508 */     this.LeftAnkle.setRotationPoint(-0.5F, 4.0F, 5.0F);
/*  509 */     this.LeftHindUpperLeg.addChild(this.LeftAnkle);
/*      */     
/*  511 */     this.LeftHindLowerLeg = new ModelRenderer(this, 0, 86);
/*  512 */     this.LeftHindLowerLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
/*  513 */     this.LeftHindLowerLeg.setRotationPoint(0.0F, 3.0F, 0.5F);
/*  514 */     this.LeftAnkle.addChild(this.LeftHindLowerLeg);
/*      */     
/*  516 */     this.LeftHindFoot = new ModelRenderer(this, 0, 91);
/*  517 */     this.LeftHindFoot.addBox(-1.5F, 0.0F, -1.5F, 3, 2, 3);
/*  518 */     this.LeftHindFoot.setRotationPoint(0.0F, 2.6F, -0.8F);
/*  519 */     setRotation(this.LeftHindFoot, 27.0F / this.radianF, 0.0F, 0.0F);
/*  520 */     this.LeftHindLowerLeg.addChild(this.LeftHindFoot);
/*      */     
/*  522 */     this.RightHindUpperLeg = new ModelRenderer(this, 16, 67);
/*  523 */     this.RightHindUpperLeg.addBox(-2.0F, -1.0F, -1.5F, 3, 8, 5);
/*  524 */     this.RightHindUpperLeg.setRotationPoint(-2.0F, 3.0F, 6.8F);
/*  525 */     setRotation(this.RightHindUpperLeg, -25.0F / this.radianF, 0.0F, 0.0F);
/*  526 */     this.Abdomen.addChild(this.RightHindUpperLeg);
/*      */     
/*  528 */     this.RightAnkle = new ModelRenderer(this, 10, 80);
/*  529 */     this.RightAnkle.addBox(-1.0F, 0.0F, -1.5F, 2, 3, 3);
/*  530 */     this.RightAnkle.setRotationPoint(-0.5F, 4.0F, 5.0F);
/*  531 */     this.RightHindUpperLeg.addChild(this.RightAnkle);
/*      */     
/*  533 */     this.RightHindLowerLeg = new ModelRenderer(this, 8, 86);
/*  534 */     this.RightHindLowerLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
/*  535 */     this.RightHindLowerLeg.setRotationPoint(0.0F, 3.0F, 0.5F);
/*  536 */     this.RightAnkle.addChild(this.RightHindLowerLeg);
/*      */     
/*  538 */     this.RightHindFoot = new ModelRenderer(this, 12, 91);
/*  539 */     this.RightHindFoot.addBox(-1.5F, 0.0F, -1.5F, 3, 2, 3);
/*  540 */     this.RightHindFoot.setRotationPoint(0.0F, 2.6F, -0.8F);
/*  541 */     setRotation(this.RightHindFoot, 27.0F / this.radianF, 0.0F, 0.0F);
/*  542 */     this.RightHindLowerLeg.addChild(this.RightHindFoot);
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateAnimationModifiers(Entity entity) {
/*  547 */     MoCEntityBigCat bigcat = (MoCEntityBigCat)entity;
/*  548 */     this.isFlyer = bigcat.isFlyer();
/*  549 */     this.isSaddled = bigcat.getIsRideable();
/*  550 */     this.flapwings = (bigcat.wingFlapCounter != 0);
/*  551 */     this.onAir = bigcat.isOnAir();
/*  552 */     this.floating = (this.isFlyer && this.onAir);
/*      */     
/*  554 */     this.openMouthCounter = bigcat.mouthCounter;
/*  555 */     this.isRidden = bigcat.isBeingRidden();
/*  556 */     this.hasMane = bigcat.hasMane();
/*  557 */     this.isTamed = bigcat.getHasAmulet();
/*  558 */     this.isSitting = bigcat.getIsSitting();
/*  559 */     this.movingTail = (bigcat.tailCounter != 0);
/*  560 */     this.hasSaberTeeth = bigcat.hasSaberTeeth();
/*  561 */     this.hasChest = bigcat.getIsChested();
/*  562 */     this.hasStinger = bigcat.getHasStinger();
/*  563 */     this.isGhost = bigcat.getIsGhost();
/*  564 */     this.isMovingVertically = (bigcat.motionY != 0.0D);
/*      */   }
/*      */   
/*      */   public float updateGhostTransparency(Entity entity) {
/*  568 */     MoCEntityBigCat bigcat = (MoCEntityBigCat)entity;
/*  569 */     return bigcat.tFloat();
/*      */   }
/*      */ 
/*      */   
/*      */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  574 */     updateAnimationModifiers(entity);
/*  575 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/*  576 */     renderSaddle(this.isSaddled);
/*  577 */     renderMane(this.hasMane);
/*  578 */     renderCollar(this.isTamed);
/*  579 */     renderTeeth(this.hasSaberTeeth);
/*  580 */     renderChest(this.hasChest);
/*      */     
/*  582 */     GL11.glPushMatrix();
/*      */ 
/*      */     
/*  585 */     if (this.isGhost) {
/*  586 */       GL11.glEnable(3042);
/*  587 */       GL11.glBlendFunc(770, 771);
/*  588 */       GL11.glColor4f(0.8F, 0.8F, 0.8F, updateGhostTransparency(entity));
/*      */     } 
/*      */ 
/*      */     
/*  592 */     this.Chest.render(f5);
/*      */     
/*  594 */     if (this.isFlyer) {
/*  595 */       this.InnerWing.render(f5);
/*  596 */       this.MidWing.render(f5);
/*  597 */       this.OuterWing.render(f5);
/*  598 */       this.InnerWingR.render(f5);
/*  599 */       this.MidWingR.render(f5);
/*  600 */       this.OuterWingR.render(f5);
/*      */     } 
/*      */     
/*  603 */     if (this.hasStinger) {
/*  604 */       this.STailRoot.render(f5);
/*  605 */       this.STail2.render(f5);
/*  606 */       this.STail3.render(f5);
/*  607 */       this.STail4.render(f5);
/*  608 */       this.STail5.render(f5);
/*  609 */       this.StingerLump.render(f5);
/*  610 */       this.Stinger.render(f5);
/*      */     } 
/*      */     
/*  613 */     if (this.isSaddled && this.isRidden) {
/*  614 */       this.LeftHarness.render(f5);
/*  615 */       this.RightHarness.render(f5);
/*      */     } 
/*      */     
/*  618 */     if (this.isGhost) {
/*  619 */       GL11.glDisable(3042);
/*      */     }
/*  621 */     GL11.glPopMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   private void renderTeeth(boolean flag) {
/*  626 */     this.LeftFang.isHidden = !flag;
/*  627 */     this.RightFang.isHidden = !flag;
/*      */   }
/*      */   
/*      */   private void renderCollar(boolean flag) {
/*  631 */     this.Collar.isHidden = !flag;
/*      */   }
/*      */   
/*      */   private void renderSaddle(boolean flag) {
/*  635 */     this.NeckHarness.isHidden = !flag;
/*  636 */     this.HarnessStick.isHidden = !flag;
/*  637 */     this.Saddle.isHidden = !flag;
/*      */   }
/*      */   
/*      */   private void renderMane(boolean flag) {
/*  641 */     this.Mane.isHidden = !flag;
/*  642 */     this.LeftChinBeard.isHidden = !flag;
/*  643 */     this.RightChinBeard.isHidden = !flag;
/*  644 */     this.ForeheadHair.isHidden = !flag;
/*  645 */     this.NeckHair.isHidden = !flag;
/*  646 */     this.ChinHair.isHidden = !flag;
/*      */   }
/*      */   
/*      */   private void renderChest(boolean flag) {
/*  650 */     this.StorageChest.isHidden = !flag;
/*      */   }
/*      */   
/*      */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  654 */     model.rotateAngleX = x;
/*  655 */     model.rotateAngleY = y;
/*  656 */     model.rotateAngleZ = z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/*  668 */     float RLegXRot = MathHelper.cos(f * 0.8F + 3.141593F) * 0.8F * f1;
/*  669 */     float LLegXRot = MathHelper.cos(f * 0.8F) * 0.8F * f1;
/*  670 */     float gallopRLegXRot = MathHelper.cos(f * 0.6F + 3.141593F) * 0.8F * f1;
/*  671 */     float gallopLLegXRot = MathHelper.cos(f * 0.6F) * 0.8F * f1;
/*      */     
/*  673 */     float stingYOffset = 8.0F;
/*  674 */     float stingZOffset = 0.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  691 */     if (this.movingTail) {
/*  692 */       this.Tail2.rotateAngleY = MathHelper.cos(f2 * 0.3F);
/*      */     } else {
/*      */       
/*  695 */       this.Tail2.rotateAngleY = 0.0F;
/*      */     } 
/*      */     
/*  698 */     if (this.isSitting) {
/*  699 */       stingYOffset = 17.0F;
/*  700 */       stingZOffset = -3.0F;
/*  701 */       this.Chest.rotationPointY = 14.0F;
/*  702 */       this.Abdomen.rotateAngleX = -10.0F / this.radianF;
/*  703 */       this.Chest.rotateAngleX = -45.0F / this.radianF;
/*  704 */       this.RightUpperLeg.rotateAngleX = 35.0F / this.radianF;
/*  705 */       this.RightLowerLeg.rotateAngleX = 5.0F / this.radianF;
/*  706 */       this.LeftUpperLeg.rotateAngleX = 35.0F / this.radianF;
/*  707 */       this.LeftLowerLeg.rotateAngleX = 5.0F / this.radianF;
/*  708 */       this.NeckBase.rotateAngleX = 20.0F / this.radianF;
/*  709 */       this.RightHindUpperLeg.rotationPointY = 1.0F;
/*  710 */       this.RightHindUpperLeg.rotateAngleX = -50.0F / this.radianF;
/*  711 */       this.LeftHindUpperLeg.rotationPointY = 1.0F;
/*  712 */       this.LeftHindUpperLeg.rotateAngleX = -50.0F / this.radianF;
/*  713 */       this.RightHindFoot.rotateAngleX = 90.0F / this.radianF;
/*  714 */       this.LeftHindFoot.rotateAngleX = 90.0F / this.radianF;
/*      */ 
/*      */       
/*  717 */       this.TailRoot.rotateAngleX = 100.0F / this.radianF;
/*  718 */       this.Tail2.rotateAngleX = 35.0F / this.radianF;
/*  719 */       this.Tail3.rotateAngleX = 10.0F / this.radianF;
/*  720 */       this.NeckHair.rotationPointY = 2.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  739 */       this.Collar.rotateAngleX = 0.0F / this.radianF;
/*  740 */       this.Collar.rotationPointY = 7.0F;
/*  741 */       this.Collar.rotationPointZ = -4.0F;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  746 */       stingYOffset = 8.0F;
/*  747 */       stingZOffset = 0.0F;
/*      */       
/*  749 */       this.Chest.rotationPointY = 8.0F;
/*  750 */       this.Abdomen.rotateAngleX = 0.0F;
/*  751 */       this.Chest.rotateAngleX = 0.0F;
/*  752 */       this.NeckBase.rotateAngleX = -14.0F / this.radianF;
/*  753 */       this.TailRoot.rotateAngleX = 87.0F / this.radianF;
/*  754 */       this.Tail2.rotateAngleX = -30.0F / this.radianF;
/*  755 */       this.Tail3.rotateAngleX = -17.0F / this.radianF;
/*  756 */       this.RightLowerLeg.rotateAngleX = -21.5F / this.radianF;
/*  757 */       this.LeftLowerLeg.rotateAngleX = -21.5F / this.radianF;
/*  758 */       this.RightHindUpperLeg.rotationPointY = 3.0F;
/*  759 */       this.LeftHindUpperLeg.rotationPointY = 3.0F;
/*  760 */       this.RightHindFoot.rotateAngleX = 27.0F / this.radianF;
/*  761 */       this.LeftHindFoot.rotateAngleX = 27.0F / this.radianF;
/*      */ 
/*      */       
/*  764 */       this.Collar.rotationPointZ = -2.0F;
/*  765 */       this.NeckHair.rotationPointY = -0.5F;
/*  766 */       if (this.hasMane) {
/*  767 */         this.Collar.rotationPointY = 9.0F;
/*      */       } else {
/*  769 */         this.Collar.rotationPointY = 6.0F;
/*      */       } 
/*  771 */       this.Collar.rotateAngleX = 20.0F / this.radianF + MathHelper.cos(f * 0.8F) * 0.5F * f1;
/*      */ 
/*      */ 
/*      */       
/*  775 */       boolean galloping = (f1 >= 0.97F);
/*  776 */       if (this.onAir || this.isGhost) {
/*  777 */         if (this.isGhost || (this.isFlyer && f1 > 0.0F)) {
/*  778 */           float speedMov = f1 * 0.5F;
/*  779 */           this.RightUpperLeg.rotateAngleX = 45.0F / this.radianF + speedMov;
/*  780 */           this.LeftUpperLeg.rotateAngleX = 45.0F / this.radianF + speedMov;
/*  781 */           this.RightHindUpperLeg.rotateAngleX = 10.0F / this.radianF + speedMov;
/*  782 */           this.LeftHindUpperLeg.rotateAngleX = 10.0F / this.radianF + speedMov;
/*  783 */         } else if (this.isMovingVertically) {
/*      */           
/*  785 */           this.RightUpperLeg.rotateAngleX = -35.0F / this.radianF;
/*  786 */           this.LeftUpperLeg.rotateAngleX = -35.0F / this.radianF;
/*  787 */           this.RightHindUpperLeg.rotateAngleX = 35.0F / this.radianF;
/*  788 */           this.LeftHindUpperLeg.rotateAngleX = 35.0F / this.radianF;
/*      */         }
/*      */       
/*      */       }
/*  792 */       else if (galloping) {
/*      */         
/*  794 */         this.RightUpperLeg.rotateAngleX = 15.0F / this.radianF + gallopRLegXRot;
/*  795 */         this.LeftUpperLeg.rotateAngleX = 15.0F / this.radianF + gallopRLegXRot;
/*  796 */         this.RightHindUpperLeg.rotateAngleX = -25.0F / this.radianF + gallopLLegXRot;
/*  797 */         this.LeftHindUpperLeg.rotateAngleX = -25.0F / this.radianF + gallopLLegXRot;
/*      */         
/*  799 */         this.Abdomen.rotateAngleY = 0.0F;
/*      */       } else {
/*  801 */         this.RightUpperLeg.rotateAngleX = 15.0F / this.radianF + RLegXRot;
/*  802 */         this.LeftHindUpperLeg.rotateAngleX = -25.0F / this.radianF + RLegXRot;
/*  803 */         this.LeftUpperLeg.rotateAngleX = 15.0F / this.radianF + LLegXRot;
/*  804 */         this.RightHindUpperLeg.rotateAngleX = -25.0F / this.radianF + LLegXRot;
/*  805 */         if (!this.hasStinger) {
/*  806 */           this.Abdomen.rotateAngleY = MathHelper.cos(f * 0.3F) * 0.25F * f1;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  811 */       if (this.isRidden) {
/*  812 */         this.LeftFootHarness.rotateAngleX = -60.0F / this.radianF;
/*  813 */         this.RightFootHarness.rotateAngleX = -60.0F / this.radianF;
/*      */       } else {
/*  815 */         this.LeftFootHarness.rotateAngleX = RLegXRot / 3.0F;
/*  816 */         this.RightFootHarness.rotateAngleX = RLegXRot / 3.0F;
/*      */         
/*  818 */         this.LeftFootHarness.rotateAngleZ = RLegXRot / 5.0F;
/*  819 */         this.RightFootHarness.rotateAngleZ = -RLegXRot / 5.0F;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  831 */       float TailXRot = MathHelper.cos(f * 0.4F) * 0.15F * f1;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  836 */       this.TailRoot.rotateAngleX = 87.0F / this.radianF + TailXRot;
/*  837 */       this.Tail2.rotateAngleX = -30.0F / this.radianF + TailXRot;
/*  838 */       this.Tail3.rotateAngleX = -17.0F / this.radianF + TailXRot;
/*  839 */       this.Tail4.rotateAngleX = 21.0F / this.radianF + TailXRot;
/*  840 */       this.TailTip.rotateAngleX = 21.0F / this.radianF + TailXRot;
/*  841 */       this.TailTusk.rotateAngleX = 21.0F / this.radianF + TailXRot;
/*      */     } 
/*      */ 
/*      */     
/*  845 */     float HeadXRot = f4 / 57.29578F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  858 */     this.HeadBack.rotateAngleX = 14.0F / this.radianF + HeadXRot;
/*  859 */     this.HeadBack.rotateAngleY = f3 / 57.29578F;
/*      */     
/*  861 */     float mouthMov = 0.0F;
/*  862 */     if (this.openMouthCounter != 0) {
/*  863 */       if (this.openMouthCounter < 10) {
/*  864 */         mouthMov = (22 + this.openMouthCounter * 3);
/*  865 */       } else if (this.openMouthCounter > 20) {
/*  866 */         mouthMov = (22 + 90 - this.openMouthCounter * 3);
/*      */       } else {
/*  868 */         mouthMov = 55.0F;
/*      */       } 
/*      */     }
/*  871 */     this.LowerJaw.rotateAngleX = mouthMov / this.radianF;
/*      */     
/*  873 */     if (this.isSaddled) {
/*  874 */       this.LeftHarness.rotateAngleX = 25.0F / this.radianF + this.HeadBack.rotateAngleX;
/*  875 */       this.LeftHarness.rotateAngleY = this.HeadBack.rotateAngleY;
/*  876 */       this.RightHarness.rotateAngleX = 25.0F / this.radianF + this.HeadBack.rotateAngleX;
/*  877 */       this.RightHarness.rotateAngleY = this.HeadBack.rotateAngleY;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  902 */     if (this.isFlyer) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  908 */       float WingRot = 0.0F;
/*  909 */       if (this.flapwings) {
/*  910 */         WingRot = MathHelper.cos(f2 * 0.3F + 3.141593F) * 1.2F;
/*      */       }
/*      */       else {
/*      */         
/*  914 */         WingRot = MathHelper.cos(f * 0.5F) * 0.1F;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  924 */       if (this.floating) {
/*  925 */         this.OuterWing.rotateAngleY = -0.3228859F + WingRot / 2.0F;
/*  926 */         this.OuterWingR.rotateAngleY = 0.3228859F - WingRot / 2.0F;
/*      */       } else {
/*      */         
/*  929 */         WingRot = 60.0F / this.radianF;
/*  930 */         this.OuterWing.rotateAngleY = -90.0F / this.radianF;
/*  931 */         this.OuterWingR.rotateAngleY = 90.0F / this.radianF;
/*      */       } 
/*      */       
/*  934 */       this.InnerWingR.rotationPointY = this.InnerWing.rotationPointY;
/*  935 */       this.InnerWingR.rotationPointZ = this.InnerWing.rotationPointZ;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  947 */       this.InnerWing.rotationPointX += MathHelper.cos(WingRot) * 12.0F;
/*  948 */       this.InnerWingR.rotationPointX -= MathHelper.cos(WingRot) * 12.0F;
/*      */       
/*  950 */       this.MidWing.rotationPointY = this.InnerWing.rotationPointY;
/*  951 */       this.MidWingR.rotationPointY = this.InnerWing.rotationPointY;
/*  952 */       this.InnerWing.rotationPointY += MathHelper.sin(WingRot) * 12.0F;
/*  953 */       this.InnerWingR.rotationPointY += MathHelper.sin(WingRot) * 12.0F;
/*      */       
/*  955 */       this.MidWing.rotationPointZ = this.InnerWing.rotationPointZ;
/*  956 */       this.MidWingR.rotationPointZ = this.InnerWing.rotationPointZ;
/*  957 */       this.OuterWing.rotationPointZ = this.InnerWing.rotationPointZ;
/*  958 */       this.OuterWingR.rotationPointZ = this.InnerWing.rotationPointZ;
/*      */       
/*  960 */       this.MidWing.rotateAngleZ = WingRot;
/*  961 */       this.InnerWing.rotateAngleZ = WingRot;
/*  962 */       this.OuterWing.rotateAngleZ = WingRot;
/*      */       
/*  964 */       this.InnerWingR.rotateAngleZ = -WingRot;
/*  965 */       this.MidWingR.rotateAngleZ = -WingRot;
/*  966 */       this.OuterWingR.rotateAngleZ = -WingRot;
/*      */       
/*  968 */       if (this.hasStinger)
/*      */       {
/*  970 */         if (!this.poisoning) {
/*      */           
/*  972 */           this.STailRoot.rotateAngleX = 33.0F / this.radianF;
/*  973 */           this.STailRoot.rotationPointY = stingYOffset;
/*  974 */           this.STailRoot.rotationPointZ = stingZOffset;
/*      */           
/*  976 */           this.STail2.rotateAngleX = 54.5F / this.radianF;
/*  977 */           this.STail2.rotationPointY = stingYOffset;
/*  978 */           this.STail2.rotationPointZ = stingZOffset;
/*      */           
/*  980 */           this.STail3.rotateAngleX = 95.1F / this.radianF;
/*  981 */           this.STail3.rotationPointY = stingYOffset;
/*  982 */           this.STail3.rotationPointZ = stingZOffset;
/*      */           
/*  984 */           this.STail4.rotateAngleX = 141.8F / this.radianF;
/*  985 */           this.STail4.rotationPointY = stingYOffset;
/*  986 */           this.STail4.rotationPointZ = stingZOffset;
/*      */           
/*  988 */           this.STail5.rotateAngleX = 173.9F / this.radianF;
/*  989 */           this.STail5.rotationPointY = stingYOffset;
/*  990 */           this.STail5.rotationPointZ = stingZOffset;
/*      */           
/*  992 */           this.StingerLump.rotateAngleX = 116.4F / this.radianF;
/*  993 */           this.StingerLump.rotationPointY = stingYOffset;
/*  994 */           this.StingerLump.rotationPointZ = stingZOffset;
/*      */           
/*  996 */           this.Stinger.rotateAngleX = 69.5F / this.radianF;
/*  997 */           this.Stinger.rotationPointY = stingYOffset;
/*  998 */           this.Stinger.rotationPointZ = stingZOffset;
/*      */         }
/* 1000 */         else if (!this.isSitting) {
/*      */           
/* 1002 */           this.STailRoot.rotateAngleX = 95.2F / this.radianF;
/* 1003 */           this.STailRoot.rotationPointY = 14.5F;
/* 1004 */           this.STailRoot.rotationPointZ = 2.0F;
/*      */           
/* 1006 */           this.STail2.rotateAngleX = 128.5F / this.radianF;
/* 1007 */           this.STail2.rotationPointY = 15.0F;
/* 1008 */           this.STail2.rotationPointZ = 4.0F;
/*      */           
/* 1010 */           this.STail3.rotateAngleX = 169.0F / this.radianF;
/* 1011 */           this.STail3.rotationPointY = 14.0F;
/* 1012 */           this.STail3.rotationPointZ = 3.8F;
/*      */           
/* 1014 */           this.STail4.rotateAngleX = 177.0F / this.radianF;
/* 1015 */           this.STail4.rotationPointY = 13.5F;
/* 1016 */           this.STail4.rotationPointZ = -8.5F;
/*      */           
/* 1018 */           this.STail5.rotateAngleX = 180.0F / this.radianF;
/* 1019 */           this.STail5.rotationPointY = 11.5F;
/* 1020 */           this.STail5.rotationPointZ = -17.0F;
/*      */           
/* 1022 */           this.StingerLump.rotateAngleX = 35.4F / this.radianF;
/* 1023 */           this.StingerLump.rotationPointY = -4.0F;
/* 1024 */           this.StingerLump.rotationPointZ = -28.0F;
/*      */           
/* 1026 */           this.Stinger.rotateAngleX = 25.5F / this.radianF;
/* 1027 */           this.Stinger.rotationPointY = 4.0F;
/* 1028 */           this.Stinger.rotationPointZ = -29.0F;
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelNewBigCat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */