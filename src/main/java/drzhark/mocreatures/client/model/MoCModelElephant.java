/*      */ package drzhark.mocreatures.client.model;
/*      */ 
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityElephant;
/*      */ import net.minecraft.client.model.ModelBase;
/*      */ import net.minecraft.client.model.ModelRenderer;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraftforge.fml.relauncher.Side;
/*      */ import net.minecraftforge.fml.relauncher.SideOnly;
/*      */ 
/*      */ 
/*      */ @SideOnly(Side.CLIENT)
/*      */ public class MoCModelElephant
/*      */   extends ModelBase
/*      */ {
/*      */   ModelRenderer Head;
/*      */   ModelRenderer Neck;
/*      */   ModelRenderer HeadBump;
/*      */   ModelRenderer Chin;
/*      */   ModelRenderer LowerLip;
/*      */   ModelRenderer Back;
/*      */   ModelRenderer LeftSmallEar;
/*      */   ModelRenderer LeftBigEar;
/*      */   ModelRenderer RightSmallEar;
/*      */   ModelRenderer RightBigEar;
/*      */   ModelRenderer Hump;
/*      */   ModelRenderer Body;
/*      */   ModelRenderer Skirt;
/*      */   ModelRenderer RightTuskA;
/*      */   ModelRenderer RightTuskB;
/*      */   ModelRenderer RightTuskC;
/*      */   ModelRenderer RightTuskD;
/*      */   ModelRenderer LeftTuskA;
/*      */   ModelRenderer LeftTuskB;
/*      */   ModelRenderer LeftTuskC;
/*      */   ModelRenderer LeftTuskD;
/*      */   ModelRenderer TrunkA;
/*      */   ModelRenderer TrunkB;
/*      */   ModelRenderer TrunkC;
/*      */   ModelRenderer TrunkD;
/*      */   ModelRenderer TrunkE;
/*      */   ModelRenderer FrontRightUpperLeg;
/*      */   ModelRenderer FrontRightLowerLeg;
/*      */   ModelRenderer FrontLeftUpperLeg;
/*      */   ModelRenderer FrontLeftLowerLeg;
/*      */   ModelRenderer BackRightUpperLeg;
/*      */   ModelRenderer BackRightLowerLeg;
/*      */   ModelRenderer BackLeftUpperLeg;
/*      */   ModelRenderer BackLeftLowerLeg;
/*      */   ModelRenderer TailRoot;
/*      */   ModelRenderer Tail;
/*      */   ModelRenderer TailPlush;
/*      */   ModelRenderer StorageRightBedroll;
/*      */   ModelRenderer StorageLeftBedroll;
/*      */   ModelRenderer StorageFrontRightChest;
/*      */   ModelRenderer StorageBackRightChest;
/*      */   ModelRenderer StorageFrontLeftChest;
/*      */   ModelRenderer StorageBackLeftChest;
/*      */   ModelRenderer StorageRightBlankets;
/*      */   ModelRenderer StorageLeftBlankets;
/*      */   ModelRenderer HarnessBlanket;
/*      */   ModelRenderer HarnessUpperBelt;
/*      */   ModelRenderer HarnessLowerBelt;
/*      */   ModelRenderer CabinPillow;
/*      */   ModelRenderer CabinLeftRail;
/*      */   ModelRenderer Cabin;
/*      */   ModelRenderer CabinRightRail;
/*      */   ModelRenderer CabinBackRail;
/*      */   ModelRenderer CabinRoof;
/*      */   ModelRenderer FortNeckBeam;
/*      */   ModelRenderer FortBackBeam;
/*      */   ModelRenderer TuskLD1;
/*      */   ModelRenderer TuskLD2;
/*      */   ModelRenderer TuskLD3;
/*      */   ModelRenderer TuskLD4;
/*      */   ModelRenderer TuskLD5;
/*      */   ModelRenderer TuskRD1;
/*      */   ModelRenderer TuskRD2;
/*      */   ModelRenderer TuskRD3;
/*      */   ModelRenderer TuskRD4;
/*      */   ModelRenderer TuskRD5;
/*      */   ModelRenderer TuskLI1;
/*      */   ModelRenderer TuskLI2;
/*      */   ModelRenderer TuskLI3;
/*      */   ModelRenderer TuskLI4;
/*      */   ModelRenderer TuskLI5;
/*      */   ModelRenderer TuskRI1;
/*      */   ModelRenderer TuskRI2;
/*      */   ModelRenderer TuskRI3;
/*      */   ModelRenderer TuskRI4;
/*      */   ModelRenderer TuskRI5;
/*      */   ModelRenderer TuskLW1;
/*      */   ModelRenderer TuskLW2;
/*      */   ModelRenderer TuskLW3;
/*      */   ModelRenderer TuskLW4;
/*      */   ModelRenderer TuskLW5;
/*      */   ModelRenderer TuskRW1;
/*      */   ModelRenderer TuskRW2;
/*      */   ModelRenderer TuskRW3;
/*      */   ModelRenderer TuskRW4;
/*      */   ModelRenderer TuskRW5;
/*      */   ModelRenderer FortFloor1;
/*      */   ModelRenderer FortFloor2;
/*      */   ModelRenderer FortFloor3;
/*      */   ModelRenderer FortBackWall;
/*      */   ModelRenderer FortBackLeftWall;
/*      */   ModelRenderer FortBackRightWall;
/*      */   ModelRenderer StorageUpLeft;
/*      */   ModelRenderer StorageUpRight;
/*  110 */   float radianF = 57.29578F;
/*      */   
/*      */   private boolean isSitting;
/*      */   private int tailCounter;
/*      */   private int earCounter;
/*      */   private int trunkCounter;
/*      */   int tusks;
/*      */   
/*      */   public MoCModelElephant() {
/*  119 */     this.textureWidth = 128;
/*  120 */     this.textureHeight = 256;
/*      */     
/*  122 */     this.Head = new ModelRenderer(this, 60, 0);
/*  123 */     this.Head.addBox(-5.5F, -6.0F, -8.0F, 11, 15, 10);
/*  124 */     this.Head.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  125 */     setRotation(this.Head, -0.1745329F, 0.0F, 0.0F);
/*      */     
/*  127 */     this.Neck = new ModelRenderer(this, 46, 48);
/*  128 */     this.Neck.addBox(-4.95F, -6.0F, -8.0F, 10, 14, 8);
/*  129 */     this.Neck.setRotationPoint(0.0F, -8.0F, -10.0F);
/*  130 */     setRotation(this.Neck, -0.2617994F, 0.0F, 0.0F);
/*      */     
/*  132 */     this.HeadBump = new ModelRenderer(this, 104, 41);
/*  133 */     this.HeadBump.addBox(-3.0F, -9.0F, -6.0F, 6, 3, 6);
/*  134 */     this.HeadBump.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  135 */     setRotation(this.HeadBump, -0.1745329F, 0.0F, 0.0F);
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
/*  146 */     this.Chin = new ModelRenderer(this, 86, 56);
/*  147 */     this.Chin.addBox(-1.5F, -6.0F, -10.7F, 3, 5, 4);
/*  148 */     this.Chin.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  149 */     setRotation(this.Chin, 2.054118F, 0.0F, 0.0F);
/*      */     
/*  151 */     this.LowerLip = new ModelRenderer(this, 80, 65);
/*  152 */     this.LowerLip.addBox(-2.0F, -2.0F, -14.0F, 4, 2, 6);
/*  153 */     this.LowerLip.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  154 */     setRotation(this.LowerLip, 1.570796F, 0.0F, 0.0F);
/*      */     
/*  156 */     this.Back = new ModelRenderer(this, 0, 48);
/*  157 */     this.Back.addBox(-5.0F, -10.0F, -10.0F, 10, 2, 26);
/*  158 */     this.Back.setRotationPoint(0.0F, -4.0F, -3.0F);
/*      */     
/*  160 */     this.LeftSmallEar = new ModelRenderer(this, 102, 0);
/*  161 */     this.LeftSmallEar.addBox(2.0F, -8.0F, -5.0F, 8, 10, 1);
/*  162 */     this.LeftSmallEar.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  163 */     setRotation(this.LeftSmallEar, -0.1745329F, -0.5235988F, 0.5235988F);
/*  164 */     this.LeftBigEar = new ModelRenderer(this, 102, 0);
/*  165 */     this.LeftBigEar.addBox(2.0F, -8.0F, -5.0F, 12, 14, 1);
/*  166 */     this.LeftBigEar.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  167 */     setRotation(this.LeftBigEar, -0.1745329F, -0.5235988F, 0.5235988F);
/*  168 */     this.RightSmallEar = new ModelRenderer(this, 106, 15);
/*  169 */     this.RightSmallEar.addBox(-10.0F, -8.0F, -5.0F, 8, 10, 1);
/*  170 */     this.RightSmallEar.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  171 */     setRotation(this.RightSmallEar, -0.1745329F, 0.5235988F, -0.5235988F);
/*  172 */     this.RightBigEar = new ModelRenderer(this, 102, 15);
/*  173 */     this.RightBigEar.addBox(-14.0F, -8.0F, -5.0F, 12, 14, 1);
/*  174 */     this.RightBigEar.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  175 */     setRotation(this.RightBigEar, -0.1745329F, 0.5235988F, -0.5235988F);
/*      */     
/*  177 */     this.Hump = new ModelRenderer(this, 88, 30);
/*  178 */     this.Hump.addBox(-6.0F, -2.0F, -3.0F, 12, 3, 8);
/*  179 */     this.Hump.setRotationPoint(0.0F, -13.0F, -5.5F);
/*      */     
/*  181 */     this.Body = new ModelRenderer(this, 0, 0);
/*  182 */     this.Body.addBox(-8.0F, -10.0F, -10.0F, 16, 20, 28);
/*  183 */     this.Body.setRotationPoint(0.0F, -2.0F, -3.0F);
/*      */     
/*  185 */     this.Skirt = new ModelRenderer(this, 28, 94);
/*  186 */     this.Skirt.addBox(-8.0F, -10.0F, -6.0F, 16, 28, 6);
/*  187 */     this.Skirt.setRotationPoint(0.0F, 8.0F, -3.0F);
/*  188 */     setRotation(this.Skirt, 1.570796F, 0.0F, 0.0F);
/*      */     
/*  190 */     this.RightTuskA = new ModelRenderer(this, 2, 60);
/*  191 */     this.RightTuskA.addBox(-3.8F, -3.5F, -19.0F, 2, 2, 10);
/*  192 */     this.RightTuskA.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  193 */     setRotation(this.RightTuskA, 1.22173F, 0.0F, 0.1745329F);
/*      */     
/*  195 */     this.RightTuskB = new ModelRenderer(this, 0, 0);
/*  196 */     this.RightTuskB.addBox(-3.8F, 6.2F, -24.2F, 2, 2, 7);
/*  197 */     this.RightTuskB.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  198 */     setRotation(this.RightTuskB, 0.6981317F, 0.0F, 0.1745329F);
/*      */     
/*  200 */     this.RightTuskC = new ModelRenderer(this, 0, 18);
/*  201 */     this.RightTuskC.addBox(-3.8F, 17.1F, -21.9F, 2, 2, 5);
/*  202 */     this.RightTuskC.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  203 */     setRotation(this.RightTuskC, 0.1745329F, 0.0F, 0.1745329F);
/*      */     
/*  205 */     this.RightTuskD = new ModelRenderer(this, 14, 18);
/*  206 */     this.RightTuskD.addBox(-3.8F, 25.5F, -14.5F, 2, 2, 5);
/*  207 */     this.RightTuskD.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  208 */     setRotation(this.RightTuskD, -0.3490659F, 0.0F, 0.1745329F);
/*      */     
/*  210 */     this.LeftTuskA = new ModelRenderer(this, 2, 48);
/*  211 */     this.LeftTuskA.addBox(1.8F, -3.5F, -19.0F, 2, 2, 10);
/*  212 */     this.LeftTuskA.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  213 */     setRotation(this.LeftTuskA, 1.22173F, 0.0F, -0.1745329F);
/*      */     
/*  215 */     this.LeftTuskB = new ModelRenderer(this, 0, 9);
/*  216 */     this.LeftTuskB.addBox(1.8F, 6.2F, -24.2F, 2, 2, 7);
/*  217 */     this.LeftTuskB.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  218 */     setRotation(this.LeftTuskB, 0.6981317F, 0.0F, -0.1745329F);
/*      */     
/*  220 */     this.LeftTuskC = new ModelRenderer(this, 0, 18);
/*  221 */     this.LeftTuskC.addBox(1.8F, 17.1F, -21.9F, 2, 2, 5);
/*  222 */     this.LeftTuskC.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  223 */     setRotation(this.LeftTuskC, 0.1745329F, 0.0F, -0.1745329F);
/*      */     
/*  225 */     this.LeftTuskD = new ModelRenderer(this, 14, 18);
/*  226 */     this.LeftTuskD.addBox(1.8F, 25.5F, -14.5F, 2, 2, 5);
/*  227 */     this.LeftTuskD.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  228 */     setRotation(this.LeftTuskD, -0.3490659F, 0.0F, -0.1745329F);
/*      */ 
/*      */     
/*  231 */     this.TrunkA = new ModelRenderer(this, 0, 76);
/*  232 */     this.TrunkA.addBox(-4.0F, -2.5F, -18.0F, 8, 7, 10);
/*  233 */     this.TrunkA.setRotationPoint(0.0F, -3.0F, -22.46667F);
/*  234 */     setRotation(this.TrunkA, 1.570796F, 0.0F, 0.0F);
/*      */     
/*  236 */     this.TrunkB = new ModelRenderer(this, 0, 93);
/*  237 */     this.TrunkB.addBox(-3.0F, -2.5F, -7.0F, 6, 5, 7);
/*  238 */     this.TrunkB.setRotationPoint(0.0F, 6.5F, -22.5F);
/*  239 */     setRotation(this.TrunkB, 1.658063F, 0.0F, 0.0F);
/*      */     
/*  241 */     this.TrunkC = new ModelRenderer(this, 0, 105);
/*  242 */     this.TrunkC.addBox(-2.5F, -2.0F, -4.0F, 5, 4, 5);
/*  243 */     this.TrunkC.setRotationPoint(0.0F, 13.0F, -22.0F);
/*  244 */     setRotation(this.TrunkC, 1.919862F, 0.0F, 0.0F);
/*      */     
/*  246 */     this.TrunkD = new ModelRenderer(this, 0, 114);
/*  247 */     this.TrunkD.addBox(-2.0F, -1.5F, -5.0F, 4, 3, 5);
/*  248 */     this.TrunkD.setRotationPoint(0.0F, 16.0F, -21.5F);
/*  249 */     setRotation(this.TrunkD, 2.216568F, 0.0F, 0.0F);
/*      */     
/*  251 */     this.TrunkE = new ModelRenderer(this, 0, 122);
/*  252 */     this.TrunkE.addBox(-1.5F, -1.0F, -4.0F, 3, 2, 4);
/*  253 */     this.TrunkE.setRotationPoint(0.0F, 19.5F, -19.0F);
/*  254 */     setRotation(this.TrunkE, 2.530727F, 0.0F, 0.0F);
/*      */     
/*  256 */     this.FrontRightUpperLeg = new ModelRenderer(this, 100, 109);
/*  257 */     this.FrontRightUpperLeg.addBox(-3.5F, 0.0F, -3.5F, 7, 12, 7);
/*  258 */     this.FrontRightUpperLeg.setRotationPoint(-4.6F, 4.0F, -9.6F);
/*      */     
/*  260 */     this.FrontRightLowerLeg = new ModelRenderer(this, 100, 73);
/*  261 */     this.FrontRightLowerLeg.addBox(-3.5F, 0.0F, -3.5F, 7, 10, 7);
/*  262 */     this.FrontRightLowerLeg.setRotationPoint(-4.6F, 14.0F, -9.6F);
/*      */     
/*  264 */     this.FrontLeftUpperLeg = new ModelRenderer(this, 100, 90);
/*  265 */     this.FrontLeftUpperLeg.addBox(-3.5F, 0.0F, -3.5F, 7, 12, 7);
/*  266 */     this.FrontLeftUpperLeg.setRotationPoint(4.6F, 4.0F, -9.6F);
/*      */     
/*  268 */     this.FrontLeftLowerLeg = new ModelRenderer(this, 72, 73);
/*  269 */     this.FrontLeftLowerLeg.addBox(-3.5F, 0.0F, -3.5F, 7, 10, 7);
/*  270 */     this.FrontLeftLowerLeg.setRotationPoint(4.6F, 14.0F, -9.6F);
/*      */     
/*  272 */     this.BackRightUpperLeg = new ModelRenderer(this, 72, 109);
/*  273 */     this.BackRightUpperLeg.addBox(-3.5F, 0.0F, -3.5F, 7, 12, 7);
/*  274 */     this.BackRightUpperLeg.setRotationPoint(-4.6F, 4.0F, 11.6F);
/*      */     
/*  276 */     this.BackRightLowerLeg = new ModelRenderer(this, 100, 56);
/*  277 */     this.BackRightLowerLeg.addBox(-3.5F, 0.0F, -3.5F, 7, 10, 7);
/*  278 */     this.BackRightLowerLeg.setRotationPoint(-4.6F, 14.0F, 11.6F);
/*      */     
/*  280 */     this.BackLeftUpperLeg = new ModelRenderer(this, 72, 90);
/*  281 */     this.BackLeftUpperLeg.addBox(-3.5F, 0.0F, -3.5F, 7, 12, 7);
/*  282 */     this.BackLeftUpperLeg.setRotationPoint(4.6F, 4.0F, 11.6F);
/*      */     
/*  284 */     this.BackLeftLowerLeg = new ModelRenderer(this, 44, 77);
/*  285 */     this.BackLeftLowerLeg.addBox(-3.5F, 0.0F, -3.5F, 7, 10, 7);
/*  286 */     this.BackLeftLowerLeg.setRotationPoint(4.6F, 14.0F, 11.6F);
/*      */     
/*  288 */     this.TailRoot = new ModelRenderer(this, 20, 105);
/*  289 */     this.TailRoot.addBox(-1.0F, 0.0F, -2.0F, 2, 10, 2);
/*  290 */     this.TailRoot.setRotationPoint(0.0F, -8.0F, 15.0F);
/*  291 */     setRotation(this.TailRoot, 0.296706F, 0.0F, 0.0F);
/*      */     
/*  293 */     this.Tail = new ModelRenderer(this, 20, 117);
/*  294 */     this.Tail.addBox(-1.0F, 9.7F, -0.2F, 2, 6, 2);
/*  295 */     this.Tail.setRotationPoint(0.0F, -8.0F, 15.0F);
/*  296 */     setRotation(this.Tail, 0.1134464F, 0.0F, 0.0F);
/*      */     
/*  298 */     this.TailPlush = new ModelRenderer(this, 26, 76);
/*  299 */     this.TailPlush.addBox(-1.5F, 15.5F, -0.7F, 3, 6, 3);
/*  300 */     this.TailPlush.setRotationPoint(0.0F, -8.0F, 15.0F);
/*  301 */     setRotation(this.TailPlush, 0.1134464F, 0.0F, 0.0F);
/*      */     
/*  303 */     this.StorageRightBedroll = new ModelRenderer(this, 90, 231);
/*  304 */     this.StorageRightBedroll.addBox(-2.5F, 8.0F, -8.0F, 3, 3, 16);
/*  305 */     this.StorageRightBedroll.setRotationPoint(-9.0F, -10.2F, 1.0F);
/*  306 */     setRotation(this.StorageRightBedroll, 0.0F, 0.0F, 0.418879F);
/*      */     
/*  308 */     this.StorageLeftBedroll = new ModelRenderer(this, 90, 231);
/*  309 */     this.StorageLeftBedroll.addBox(-0.5F, 8.0F, -8.0F, 3, 3, 16);
/*  310 */     this.StorageLeftBedroll.setRotationPoint(9.0F, -10.2F, 1.0F);
/*  311 */     setRotation(this.StorageLeftBedroll, 0.0F, 0.0F, -0.418879F);
/*      */     
/*  313 */     this.StorageFrontRightChest = new ModelRenderer(this, 76, 208);
/*  314 */     this.StorageFrontRightChest.addBox(-3.5F, 0.0F, -5.0F, 5, 8, 10);
/*  315 */     this.StorageFrontRightChest.setRotationPoint(-11.0F, -1.2F, -4.5F);
/*  316 */     setRotation(this.StorageFrontRightChest, 0.0F, 0.0F, -0.2617994F);
/*      */     
/*  318 */     this.StorageBackRightChest = new ModelRenderer(this, 76, 208);
/*  319 */     this.StorageBackRightChest.addBox(-3.5F, 0.0F, -5.0F, 5, 8, 10);
/*  320 */     this.StorageBackRightChest.setRotationPoint(-11.0F, -1.2F, 6.5F);
/*  321 */     setRotation(this.StorageBackRightChest, 0.0F, 0.0F, -0.2617994F);
/*      */     
/*  323 */     this.StorageFrontLeftChest = new ModelRenderer(this, 76, 226);
/*  324 */     this.StorageFrontLeftChest.addBox(-1.5F, 0.0F, -5.0F, 5, 8, 10);
/*  325 */     this.StorageFrontLeftChest.setRotationPoint(11.0F, -1.2F, -4.5F);
/*  326 */     setRotation(this.StorageFrontLeftChest, 0.0F, 0.0F, 0.2617994F);
/*      */     
/*  328 */     this.StorageBackLeftChest = new ModelRenderer(this, 76, 226);
/*  329 */     this.StorageBackLeftChest.addBox(-1.5F, 0.0F, -5.0F, 5, 8, 10);
/*  330 */     this.StorageBackLeftChest.setRotationPoint(11.0F, -1.2F, 6.5F);
/*  331 */     setRotation(this.StorageBackLeftChest, 0.0F, 0.0F, 0.2617994F);
/*      */     
/*  333 */     this.StorageRightBlankets = new ModelRenderer(this, 0, 228);
/*  334 */     this.StorageRightBlankets.addBox(-4.5F, -1.0F, -7.0F, 5, 10, 14);
/*  335 */     this.StorageRightBlankets.setRotationPoint(-9.0F, -10.2F, 1.0F);
/*      */     
/*  337 */     this.StorageLeftBlankets = new ModelRenderer(this, 38, 228);
/*  338 */     this.StorageLeftBlankets.addBox(-0.5F, -1.0F, -7.0F, 5, 10, 14);
/*  339 */     this.StorageLeftBlankets.setRotationPoint(9.0F, -10.2F, 1.0F);
/*      */     
/*  341 */     this.HarnessBlanket = new ModelRenderer(this, 0, 196);
/*  342 */     this.HarnessBlanket.addBox(-8.5F, -2.0F, -3.0F, 17, 14, 18);
/*  343 */     this.HarnessBlanket.setRotationPoint(0.0F, -13.2F, -3.5F);
/*      */     
/*  345 */     this.HarnessUpperBelt = new ModelRenderer(this, 70, 196);
/*  346 */     this.HarnessUpperBelt.addBox(-8.5F, 0.5F, -2.0F, 17, 10, 2);
/*  347 */     this.HarnessUpperBelt.setRotationPoint(0.0F, -2.0F, -2.5F);
/*      */     
/*  349 */     this.HarnessLowerBelt = new ModelRenderer(this, 70, 196);
/*  350 */     this.HarnessLowerBelt.addBox(-8.5F, 0.5F, -2.5F, 17, 10, 2);
/*  351 */     this.HarnessLowerBelt.setRotationPoint(0.0F, -2.0F, 7.0F);
/*      */     
/*  353 */     this.CabinPillow = new ModelRenderer(this, 76, 146);
/*  354 */     this.CabinPillow.addBox(-6.5F, 0.0F, -6.5F, 13, 4, 13);
/*  355 */     this.CabinPillow.setRotationPoint(0.0F, -16.0F, 2.0F);
/*      */     
/*  357 */     this.CabinLeftRail = new ModelRenderer(this, 56, 147);
/*  358 */     this.CabinLeftRail.addBox(-7.0F, 0.0F, 7.0F, 14, 1, 1);
/*  359 */     this.CabinLeftRail.setRotationPoint(0.0F, -23.0F, 1.5F);
/*  360 */     setRotation(this.CabinLeftRail, 0.0F, 1.570796F, 0.0F);
/*      */     
/*  362 */     this.Cabin = new ModelRenderer(this, 0, 128);
/*  363 */     this.Cabin.addBox(-7.0F, 0.0F, -7.0F, 14, 20, 14);
/*  364 */     this.Cabin.setRotationPoint(0.0F, -35.0F, 2.0F);
/*      */     
/*  366 */     this.CabinRightRail = new ModelRenderer(this, 56, 147);
/*  367 */     this.CabinRightRail.addBox(-7.0F, 0.0F, 7.0F, 14, 1, 1);
/*  368 */     this.CabinRightRail.setRotationPoint(0.0F, -23.0F, 1.5F);
/*  369 */     setRotation(this.CabinRightRail, 0.0F, -1.570796F, 0.0F);
/*      */     
/*  371 */     this.CabinBackRail = new ModelRenderer(this, 56, 147);
/*  372 */     this.CabinBackRail.addBox(-7.0F, 0.0F, 7.0F, 14, 1, 1);
/*  373 */     this.CabinBackRail.setRotationPoint(0.0F, -23.0F, 1.5F);
/*      */     
/*  375 */     this.CabinRoof = new ModelRenderer(this, 56, 128);
/*  376 */     this.CabinRoof.addBox(-7.5F, 0.0F, -7.5F, 15, 4, 15);
/*  377 */     this.CabinRoof.setRotationPoint(0.0F, -34.0F, 2.0F);
/*      */     
/*  379 */     this.FortNeckBeam = new ModelRenderer(this, 26, 180);
/*  380 */     this.FortNeckBeam.addBox(-12.0F, 0.0F, -20.5F, 24, 4, 4);
/*  381 */     this.FortNeckBeam.setRotationPoint(0.0F, -16.0F, 10.0F);
/*      */     
/*  383 */     this.FortBackBeam = new ModelRenderer(this, 26, 180);
/*  384 */     this.FortBackBeam.addBox(-12.0F, 0.0F, 0.0F, 24, 4, 4);
/*  385 */     this.FortBackBeam.setRotationPoint(0.0F, -16.0F, 10.0F);
/*      */     
/*  387 */     this.TuskLD1 = new ModelRenderer(this, 108, 207);
/*  388 */     this.TuskLD1.addBox(1.3F, 5.5F, -24.2F, 3, 3, 7);
/*  389 */     this.TuskLD1.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  390 */     setRotation(this.TuskLD1, 0.6981317F, 0.0F, -0.1745329F);
/*      */     
/*  392 */     this.TuskLD2 = new ModelRenderer(this, 112, 199);
/*  393 */     this.TuskLD2.addBox(1.29F, 16.5F, -21.9F, 3, 3, 5);
/*  394 */     this.TuskLD2.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  395 */     setRotation(this.TuskLD2, 0.1745329F, 0.0F, -0.1745329F);
/*      */     
/*  397 */     this.TuskLD3 = new ModelRenderer(this, 110, 190);
/*  398 */     this.TuskLD3.addBox(1.3F, 24.9F, -15.5F, 3, 3, 6);
/*  399 */     this.TuskLD3.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  400 */     setRotation(this.TuskLD3, -0.3490659F, 0.0F, -0.1745329F);
/*      */     
/*  402 */     this.TuskLD4 = new ModelRenderer(this, 86, 175);
/*  403 */     this.TuskLD4.addBox(2.7F, 14.5F, -21.9F, 0, 7, 5);
/*  404 */     this.TuskLD4.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  405 */     setRotation(this.TuskLD4, 0.1745329F, 0.0F, -0.1745329F);
/*      */     
/*  407 */     this.TuskLD5 = new ModelRenderer(this, 112, 225);
/*  408 */     this.TuskLD5.addBox(2.7F, 22.9F, -17.5F, 0, 7, 8);
/*  409 */     this.TuskLD5.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  410 */     setRotation(this.TuskLD5, -0.3490659F, 0.0F, -0.1745329F);
/*      */     
/*  412 */     this.TuskRD1 = new ModelRenderer(this, 108, 207);
/*  413 */     this.TuskRD1.addBox(-4.3F, 5.5F, -24.2F, 3, 3, 7);
/*  414 */     this.TuskRD1.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  415 */     setRotation(this.TuskRD1, 0.6981317F, 0.0F, 0.1745329F);
/*      */     
/*  417 */     this.TuskRD2 = new ModelRenderer(this, 112, 199);
/*  418 */     this.TuskRD2.addBox(-4.29F, 16.5F, -21.9F, 3, 3, 5);
/*  419 */     this.TuskRD2.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  420 */     setRotation(this.TuskRD2, 0.1745329F, 0.0F, 0.1745329F);
/*      */     
/*  422 */     this.TuskRD3 = new ModelRenderer(this, 110, 190);
/*  423 */     this.TuskRD3.addBox(-4.3F, 24.9F, -15.5F, 3, 3, 6);
/*  424 */     this.TuskRD3.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  425 */     setRotation(this.TuskRD3, -0.3490659F, 0.0F, 0.1745329F);
/*      */     
/*  427 */     this.TuskRD4 = new ModelRenderer(this, 86, 163);
/*  428 */     this.TuskRD4.addBox(-2.8F, 14.5F, -21.9F, 0, 7, 5);
/*  429 */     this.TuskRD4.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  430 */     setRotation(this.TuskRD4, 0.1745329F, 0.0F, 0.1745329F);
/*      */     
/*  432 */     this.TuskRD5 = new ModelRenderer(this, 112, 232);
/*  433 */     this.TuskRD5.addBox(-2.8F, 22.9F, -17.5F, 0, 7, 8);
/*  434 */     this.TuskRD5.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  435 */     setRotation(this.TuskRD5, -0.3490659F, 0.0F, 0.1745329F);
/*      */     
/*  437 */     this.TuskLI1 = new ModelRenderer(this, 108, 180);
/*  438 */     this.TuskLI1.addBox(1.3F, 5.5F, -24.2F, 3, 3, 7);
/*  439 */     this.TuskLI1.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  440 */     setRotation(this.TuskLI1, 0.6981317F, 0.0F, -0.1745329F);
/*      */     
/*  442 */     this.TuskLI2 = new ModelRenderer(this, 112, 172);
/*  443 */     this.TuskLI2.addBox(1.29F, 16.5F, -21.9F, 3, 3, 5);
/*  444 */     this.TuskLI2.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  445 */     setRotation(this.TuskLI2, 0.1745329F, 0.0F, -0.1745329F);
/*      */     
/*  447 */     this.TuskLI3 = new ModelRenderer(this, 110, 163);
/*  448 */     this.TuskLI3.addBox(1.3F, 24.9F, -15.5F, 3, 3, 6);
/*  449 */     this.TuskLI3.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  450 */     setRotation(this.TuskLI3, -0.3490659F, 0.0F, -0.1745329F);
/*      */     
/*  452 */     this.TuskLI4 = new ModelRenderer(this, 96, 175);
/*  453 */     this.TuskLI4.addBox(2.7F, 14.5F, -21.9F, 0, 7, 5);
/*  454 */     this.TuskLI4.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  455 */     setRotation(this.TuskLI4, 0.1745329F, 0.0F, -0.1745329F);
/*      */     
/*  457 */     this.TuskLI5 = new ModelRenderer(this, 112, 209);
/*  458 */     this.TuskLI5.addBox(2.7F, 22.9F, -17.5F, 0, 7, 8);
/*  459 */     this.TuskLI5.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  460 */     setRotation(this.TuskLI5, -0.3490659F, 0.0F, -0.1745329F);
/*      */     
/*  462 */     this.TuskRI1 = new ModelRenderer(this, 108, 180);
/*  463 */     this.TuskRI1.addBox(-4.3F, 5.5F, -24.2F, 3, 3, 7);
/*  464 */     this.TuskRI1.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  465 */     setRotation(this.TuskRI1, 0.6981317F, 0.0F, 0.1745329F);
/*      */     
/*  467 */     this.TuskRI2 = new ModelRenderer(this, 112, 172);
/*  468 */     this.TuskRI2.addBox(-4.29F, 16.5F, -21.9F, 3, 3, 5);
/*  469 */     this.TuskRI2.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  470 */     setRotation(this.TuskRI2, 0.1745329F, 0.0F, 0.1745329F);
/*      */     
/*  472 */     this.TuskRI3 = new ModelRenderer(this, 110, 163);
/*  473 */     this.TuskRI3.addBox(-4.3F, 24.9F, -15.5F, 3, 3, 6);
/*  474 */     this.TuskRI3.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  475 */     setRotation(this.TuskRI3, -0.3490659F, 0.0F, 0.1745329F);
/*      */     
/*  477 */     this.TuskRI4 = new ModelRenderer(this, 96, 163);
/*  478 */     this.TuskRI4.addBox(-2.8F, 14.5F, -21.9F, 0, 7, 5);
/*  479 */     this.TuskRI4.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  480 */     setRotation(this.TuskRI4, 0.1745329F, 0.0F, 0.1745329F);
/*      */     
/*  482 */     this.TuskRI5 = new ModelRenderer(this, 112, 216);
/*  483 */     this.TuskRI5.addBox(-2.8F, 22.9F, -17.5F, 0, 7, 8);
/*  484 */     this.TuskRI5.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  485 */     setRotation(this.TuskRI5, -0.3490659F, 0.0F, 0.1745329F);
/*      */     
/*  487 */     this.TuskLW1 = new ModelRenderer(this, 56, 166);
/*  488 */     this.TuskLW1.addBox(1.3F, 5.5F, -24.2F, 3, 3, 7);
/*  489 */     this.TuskLW1.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  490 */     setRotation(this.TuskLW1, 0.6981317F, 0.0F, -0.1745329F);
/*      */     
/*  492 */     this.TuskLW2 = new ModelRenderer(this, 60, 158);
/*  493 */     this.TuskLW2.addBox(1.29F, 16.5F, -21.9F, 3, 3, 5);
/*  494 */     this.TuskLW2.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  495 */     setRotation(this.TuskLW2, 0.1745329F, 0.0F, -0.1745329F);
/*      */     
/*  497 */     this.TuskLW3 = new ModelRenderer(this, 58, 149);
/*  498 */     this.TuskLW3.addBox(1.3F, 24.9F, -15.5F, 3, 3, 6);
/*  499 */     this.TuskLW3.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  500 */     setRotation(this.TuskLW3, -0.3490659F, 0.0F, -0.1745329F);
/*      */     
/*  502 */     this.TuskLW4 = new ModelRenderer(this, 46, 164);
/*  503 */     this.TuskLW4.addBox(2.7F, 14.5F, -21.9F, 0, 7, 5);
/*  504 */     this.TuskLW4.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  505 */     setRotation(this.TuskLW4, 0.1745329F, 0.0F, -0.1745329F);
/*      */     
/*  507 */     this.TuskLW5 = new ModelRenderer(this, 52, 192);
/*  508 */     this.TuskLW5.addBox(2.7F, 22.9F, -17.5F, 0, 7, 8);
/*  509 */     this.TuskLW5.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  510 */     setRotation(this.TuskLW5, -0.3490659F, 0.0F, -0.1745329F);
/*      */     
/*  512 */     this.TuskRW1 = new ModelRenderer(this, 56, 166);
/*  513 */     this.TuskRW1.addBox(-4.3F, 5.5F, -24.2F, 3, 3, 7);
/*  514 */     this.TuskRW1.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  515 */     setRotation(this.TuskRW1, 0.6981317F, 0.0F, 0.1745329F);
/*      */     
/*  517 */     this.TuskRW2 = new ModelRenderer(this, 60, 158);
/*  518 */     this.TuskRW2.addBox(-4.29F, 16.5F, -21.9F, 3, 3, 5);
/*  519 */     this.TuskRW2.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  520 */     setRotation(this.TuskRW2, 0.1745329F, 0.0F, 0.1745329F);
/*      */     
/*  522 */     this.TuskRW3 = new ModelRenderer(this, 58, 149);
/*  523 */     this.TuskRW3.addBox(-4.3F, 24.9F, -15.5F, 3, 3, 6);
/*  524 */     this.TuskRW3.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  525 */     setRotation(this.TuskRW3, -0.3490659F, 0.0F, 0.1745329F);
/*      */     
/*  527 */     this.TuskRW4 = new ModelRenderer(this, 46, 157);
/*  528 */     this.TuskRW4.addBox(-2.8F, 14.5F, -21.9F, 0, 7, 5);
/*  529 */     this.TuskRW4.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  530 */     setRotation(this.TuskRW4, 0.1745329F, 0.0F, 0.1745329F);
/*      */     
/*  532 */     this.TuskRW5 = new ModelRenderer(this, 52, 199);
/*  533 */     this.TuskRW5.addBox(-2.8F, 22.9F, -17.5F, 0, 7, 8);
/*  534 */     this.TuskRW5.setRotationPoint(0.0F, -10.0F, -16.5F);
/*  535 */     setRotation(this.TuskRW5, -0.3490659F, 0.0F, 0.1745329F);
/*      */     
/*  537 */     this.FortFloor1 = new ModelRenderer(this, 0, 176);
/*  538 */     this.FortFloor1.addBox(-0.5F, -20.0F, -6.0F, 1, 8, 12);
/*  539 */     this.FortFloor1.setRotationPoint(0.0F, -16.0F, 10.0F);
/*  540 */     setRotation(this.FortFloor1, 1.570796F, 0.0F, 1.570796F);
/*      */     
/*  542 */     this.FortFloor2 = new ModelRenderer(this, 0, 176);
/*  543 */     this.FortFloor2.addBox(-0.5F, -12.0F, -6.0F, 1, 8, 12);
/*  544 */     this.FortFloor2.setRotationPoint(0.0F, -16.0F, 10.0F);
/*  545 */     setRotation(this.FortFloor2, 1.570796F, 0.0F, 1.570796F);
/*      */     
/*  547 */     this.FortFloor3 = new ModelRenderer(this, 0, 176);
/*  548 */     this.FortFloor3.addBox(-0.5F, -4.0F, -6.0F, 1, 8, 12);
/*  549 */     this.FortFloor3.setRotationPoint(0.0F, -16.0F, 10.0F);
/*  550 */     setRotation(this.FortFloor3, 1.570796F, 0.0F, 1.570796F);
/*      */     
/*  552 */     this.FortBackWall = new ModelRenderer(this, 0, 176);
/*  553 */     this.FortBackWall.addBox(-5.0F, -6.2F, -6.0F, 1, 8, 12);
/*  554 */     this.FortBackWall.setRotationPoint(0.0F, -16.0F, 10.0F);
/*  555 */     setRotation(this.FortBackWall, 0.0F, 1.570796F, 0.0F);
/*      */     
/*  557 */     this.FortBackLeftWall = new ModelRenderer(this, 0, 176);
/*  558 */     this.FortBackLeftWall.addBox(6.0F, -6.0F, -7.0F, 1, 8, 12);
/*  559 */     this.FortBackLeftWall.setRotationPoint(0.0F, -16.0F, 10.0F);
/*      */     
/*  561 */     this.FortBackRightWall = new ModelRenderer(this, 0, 176);
/*  562 */     this.FortBackRightWall.addBox(-7.0F, -6.0F, -7.0F, 1, 8, 12);
/*  563 */     this.FortBackRightWall.setRotationPoint(0.0F, -16.0F, 10.0F);
/*      */     
/*  565 */     this.StorageUpLeft = new ModelRenderer(this, 76, 226);
/*  566 */     this.StorageUpLeft.addBox(6.5F, 1.0F, -14.0F, 5, 8, 10);
/*  567 */     this.StorageUpLeft.setRotationPoint(0.0F, -16.0F, 10.0F);
/*  568 */     setRotation(this.StorageUpLeft, 0.0F, 0.0F, -0.3839724F);
/*      */     
/*  570 */     this.StorageUpRight = new ModelRenderer(this, 76, 208);
/*  571 */     this.StorageUpRight.addBox(-11.5F, 1.0F, -14.0F, 5, 8, 10);
/*  572 */     this.StorageUpRight.setRotationPoint(0.0F, -16.0F, 10.0F);
/*  573 */     setRotation(this.StorageUpRight, 0.0F, 0.0F, 0.3839724F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  579 */     MoCEntityElephant elephant = (MoCEntityElephant)entity;
/*  580 */     this.tusks = elephant.getTusks();
/*  581 */     int type = elephant.getType();
/*  582 */     this.tailCounter = elephant.tailCounter;
/*  583 */     this.earCounter = elephant.earCounter;
/*  584 */     this.trunkCounter = elephant.trunkCounter;
/*  585 */     int harness = elephant.getArmorType();
/*  586 */     int storage = elephant.getStorage();
/*  587 */     this.isSitting = (elephant.sitCounter != 0);
/*      */ 
/*      */     
/*  590 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/*      */     
/*  592 */     if (this.tusks == 0) {
/*  593 */       this.LeftTuskB.render(f5);
/*  594 */       this.RightTuskB.render(f5);
/*  595 */       if (elephant.getIsAdult() || elephant.getEdad() > 70) {
/*  596 */         this.LeftTuskC.render(f5);
/*  597 */         this.RightTuskC.render(f5);
/*      */       } 
/*  599 */       if (elephant.getIsAdult() || elephant.getEdad() > 90) {
/*  600 */         this.LeftTuskD.render(f5);
/*  601 */         this.RightTuskD.render(f5);
/*      */       } 
/*  603 */     } else if (this.tusks == 1) {
/*  604 */       this.TuskLW1.render(f5);
/*  605 */       this.TuskLW2.render(f5);
/*  606 */       this.TuskLW3.render(f5);
/*  607 */       this.TuskLW4.render(f5);
/*  608 */       this.TuskLW5.render(f5);
/*  609 */       this.TuskRW1.render(f5);
/*  610 */       this.TuskRW2.render(f5);
/*  611 */       this.TuskRW3.render(f5);
/*  612 */       this.TuskRW4.render(f5);
/*  613 */       this.TuskRW5.render(f5);
/*  614 */     } else if (this.tusks == 2) {
/*  615 */       this.TuskLI1.render(f5);
/*  616 */       this.TuskLI2.render(f5);
/*  617 */       this.TuskLI3.render(f5);
/*  618 */       this.TuskLI4.render(f5);
/*  619 */       this.TuskLI5.render(f5);
/*  620 */       this.TuskRI1.render(f5);
/*  621 */       this.TuskRI2.render(f5);
/*  622 */       this.TuskRI3.render(f5);
/*  623 */       this.TuskRI4.render(f5);
/*  624 */       this.TuskRI5.render(f5);
/*  625 */     } else if (this.tusks == 3) {
/*  626 */       this.TuskLD1.render(f5);
/*  627 */       this.TuskLD2.render(f5);
/*  628 */       this.TuskLD3.render(f5);
/*  629 */       this.TuskLD4.render(f5);
/*  630 */       this.TuskLD5.render(f5);
/*  631 */       this.TuskRD1.render(f5);
/*  632 */       this.TuskRD2.render(f5);
/*  633 */       this.TuskRD3.render(f5);
/*  634 */       this.TuskRD4.render(f5);
/*  635 */       this.TuskRD5.render(f5);
/*      */     } 
/*      */     
/*  638 */     if (type == 1) {
/*      */       
/*  640 */       this.LeftBigEar.render(f5);
/*  641 */       this.RightBigEar.render(f5);
/*      */     } else {
/*  643 */       this.LeftSmallEar.render(f5);
/*  644 */       this.RightSmallEar.render(f5);
/*      */     } 
/*      */     
/*  647 */     if (type == 3 || type == 4) {
/*      */       
/*  649 */       this.HeadBump.render(f5);
/*  650 */       this.Skirt.render(f5);
/*      */     } 
/*      */     
/*  653 */     if (harness >= 1) {
/*  654 */       this.HarnessBlanket.render(f5);
/*  655 */       this.HarnessUpperBelt.render(f5);
/*  656 */       this.HarnessLowerBelt.render(f5);
/*  657 */       if (type == 5) {
/*  658 */         this.Skirt.render(f5);
/*      */       }
/*      */     } 
/*      */     
/*  662 */     if (harness == 3) {
/*  663 */       if (type == 5) {
/*  664 */         this.CabinPillow.render(f5);
/*  665 */         this.CabinLeftRail.render(f5);
/*  666 */         this.Cabin.render(f5);
/*  667 */         this.CabinRightRail.render(f5);
/*  668 */         this.CabinBackRail.render(f5);
/*  669 */         this.CabinRoof.render(f5);
/*      */       } 
/*      */       
/*  672 */       if (type == 4) {
/*      */         
/*  674 */         this.FortBackRightWall.render(f5);
/*  675 */         this.FortBackLeftWall.render(f5);
/*  676 */         this.FortBackWall.render(f5);
/*  677 */         this.FortFloor1.render(f5);
/*  678 */         this.FortFloor2.render(f5);
/*  679 */         this.FortFloor3.render(f5);
/*  680 */         this.FortNeckBeam.render(f5);
/*  681 */         this.FortBackBeam.render(f5);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  687 */     if (storage >= 1) {
/*  688 */       this.StorageRightBedroll.render(f5);
/*  689 */       this.StorageFrontRightChest.render(f5);
/*  690 */       this.StorageBackRightChest.render(f5);
/*  691 */       this.StorageRightBlankets.render(f5);
/*      */     } 
/*      */     
/*  694 */     if (storage >= 2) {
/*  695 */       this.StorageLeftBlankets.render(f5);
/*  696 */       this.StorageLeftBedroll.render(f5);
/*  697 */       this.StorageFrontLeftChest.render(f5);
/*  698 */       this.StorageBackLeftChest.render(f5);
/*      */     } 
/*      */     
/*  701 */     if (storage >= 3) {
/*  702 */       this.StorageUpLeft.render(f5);
/*      */     }
/*      */     
/*  705 */     if (storage >= 4) {
/*  706 */       this.StorageUpRight.render(f5);
/*      */     }
/*      */     
/*  709 */     this.Head.render(f5);
/*  710 */     this.Neck.render(f5);
/*  711 */     this.Chin.render(f5);
/*  712 */     this.LowerLip.render(f5);
/*  713 */     this.Back.render(f5);
/*      */     
/*  715 */     this.Hump.render(f5);
/*  716 */     this.Body.render(f5);
/*      */     
/*  718 */     this.RightTuskA.render(f5);
/*  719 */     this.LeftTuskA.render(f5);
/*      */     
/*  721 */     this.TrunkA.render(f5);
/*  722 */     this.TrunkB.render(f5);
/*  723 */     this.TrunkC.render(f5);
/*  724 */     this.TrunkD.render(f5);
/*  725 */     this.TrunkE.render(f5);
/*  726 */     this.FrontRightUpperLeg.render(f5);
/*  727 */     this.FrontRightLowerLeg.render(f5);
/*  728 */     this.FrontLeftUpperLeg.render(f5);
/*  729 */     this.FrontLeftLowerLeg.render(f5);
/*  730 */     this.BackRightUpperLeg.render(f5);
/*  731 */     this.BackRightLowerLeg.render(f5);
/*  732 */     this.BackLeftUpperLeg.render(f5);
/*  733 */     this.BackLeftLowerLeg.render(f5);
/*  734 */     this.TailRoot.render(f5);
/*  735 */     this.Tail.render(f5);
/*  736 */     this.TailPlush.render(f5);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  741 */     model.rotateAngleX = x;
/*  742 */     model.rotateAngleY = y;
/*  743 */     model.rotateAngleZ = z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void AdjustY(float f) {
/*  750 */     float yOff = f;
/*  751 */     this.Head.rotationPointY = yOff + -10.0F;
/*  752 */     this.Neck.rotationPointY = yOff + -8.0F;
/*  753 */     this.HeadBump.rotationPointY = yOff + -10.0F;
/*  754 */     this.Chin.rotationPointY = yOff + -10.0F;
/*  755 */     this.LowerLip.rotationPointY = yOff + -10.0F;
/*  756 */     this.Back.rotationPointY = yOff + -4.0F;
/*  757 */     this.LeftSmallEar.rotationPointY = yOff + -10.0F;
/*  758 */     this.LeftBigEar.rotationPointY = yOff + -10.0F;
/*  759 */     this.RightSmallEar.rotationPointY = yOff + -10.0F;
/*  760 */     this.RightBigEar.rotationPointY = yOff + -10.0F;
/*  761 */     this.Hump.rotationPointY = yOff + -13.0F;
/*  762 */     this.Body.rotationPointY = yOff + -2.0F;
/*  763 */     this.Skirt.rotationPointY = yOff + 8.0F;
/*  764 */     this.RightTuskA.rotationPointY = yOff + -10.0F;
/*  765 */     this.RightTuskB.rotationPointY = yOff + -10.0F;
/*  766 */     this.RightTuskC.rotationPointY = yOff + -10.0F;
/*  767 */     this.RightTuskD.rotationPointY = yOff + -10.0F;
/*  768 */     this.LeftTuskA.rotationPointY = yOff + -10.0F;
/*  769 */     this.LeftTuskB.rotationPointY = yOff + -10.0F;
/*  770 */     this.LeftTuskC.rotationPointY = yOff + -10.0F;
/*  771 */     this.LeftTuskD.rotationPointY = yOff + -10.0F;
/*  772 */     this.TrunkA.rotationPointY = yOff + -3.0F;
/*  773 */     this.TrunkB.rotationPointY = yOff + 5.5F;
/*  774 */     this.TrunkC.rotationPointY = yOff + 13.0F;
/*  775 */     this.TrunkD.rotationPointY = yOff + 16.0F;
/*  776 */     this.TrunkE.rotationPointY = yOff + 19.5F;
/*  777 */     this.FrontRightUpperLeg.rotationPointY = yOff + 4.0F;
/*  778 */     this.FrontRightLowerLeg.rotationPointY = yOff + 14.0F;
/*  779 */     this.FrontLeftUpperLeg.rotationPointY = yOff + 4.0F;
/*  780 */     this.FrontLeftLowerLeg.rotationPointY = yOff + 14.0F;
/*  781 */     this.BackRightUpperLeg.rotationPointY = yOff + 4.0F;
/*  782 */     this.BackRightLowerLeg.rotationPointY = yOff + 14.0F;
/*  783 */     this.BackLeftUpperLeg.rotationPointY = yOff + 4.0F;
/*  784 */     this.BackLeftLowerLeg.rotationPointY = yOff + 14.0F;
/*  785 */     this.TailRoot.rotationPointY = yOff + -8.0F;
/*  786 */     this.Tail.rotationPointY = yOff + -8.0F;
/*  787 */     this.TailPlush.rotationPointY = yOff + -8.0F;
/*  788 */     this.StorageRightBedroll.rotationPointY = yOff + -10.2F;
/*  789 */     this.StorageLeftBedroll.rotationPointY = yOff + -10.2F;
/*  790 */     this.StorageFrontRightChest.rotationPointY = yOff + -1.2F;
/*  791 */     this.StorageBackRightChest.rotationPointY = yOff + -1.2F;
/*  792 */     this.StorageFrontLeftChest.rotationPointY = yOff + -1.2F;
/*  793 */     this.StorageBackLeftChest.rotationPointY = yOff + -1.2F;
/*  794 */     this.StorageRightBlankets.rotationPointY = yOff + -10.2F;
/*  795 */     this.StorageLeftBlankets.rotationPointY = yOff + -10.2F;
/*  796 */     this.HarnessBlanket.rotationPointY = yOff + -13.2F;
/*  797 */     this.HarnessUpperBelt.rotationPointY = yOff + -2.0F;
/*  798 */     this.HarnessLowerBelt.rotationPointY = yOff + -2.0F;
/*  799 */     this.CabinPillow.rotationPointY = yOff + -16.0F;
/*  800 */     this.CabinLeftRail.rotationPointY = yOff + -23.0F;
/*  801 */     this.Cabin.rotationPointY = yOff + -35.0F;
/*  802 */     this.CabinRightRail.rotationPointY = yOff + -23.0F;
/*  803 */     this.CabinBackRail.rotationPointY = yOff + -23.0F;
/*  804 */     this.CabinRoof.rotationPointY = yOff + -34.0F;
/*  805 */     this.FortBackRightWall.rotationPointY = yOff + -16.0F;
/*  806 */     this.FortBackLeftWall.rotationPointY = yOff + -16.0F;
/*  807 */     this.FortBackWall.rotationPointY = yOff + -16.0F;
/*  808 */     this.FortNeckBeam.rotationPointY = yOff + -16.0F;
/*  809 */     this.FortBackBeam.rotationPointY = yOff + -16.0F;
/*  810 */     this.FortFloor1.rotationPointY = yOff + -16.0F;
/*  811 */     this.FortFloor2.rotationPointY = yOff + -16.0F;
/*  812 */     this.FortFloor3.rotationPointY = yOff + -16.0F;
/*      */     
/*  814 */     this.StorageUpLeft.rotationPointY = yOff + -16.0F;
/*  815 */     this.StorageUpRight.rotationPointY = yOff + -16.0F;
/*      */     
/*  817 */     this.TuskLD1.rotationPointY = yOff + -10.0F;
/*  818 */     this.TuskLD2.rotationPointY = yOff + -10.0F;
/*  819 */     this.TuskLD3.rotationPointY = yOff + -10.0F;
/*  820 */     this.TuskLD4.rotationPointY = yOff + -10.0F;
/*  821 */     this.TuskLD5.rotationPointY = yOff + -10.0F;
/*  822 */     this.TuskRD1.rotationPointY = yOff + -10.0F;
/*  823 */     this.TuskRD2.rotationPointY = yOff + -10.0F;
/*  824 */     this.TuskRD3.rotationPointY = yOff + -10.0F;
/*  825 */     this.TuskRD4.rotationPointY = yOff + -10.0F;
/*  826 */     this.TuskRD5.rotationPointY = yOff + -10.0F;
/*  827 */     this.TuskLI1.rotationPointY = yOff + -10.0F;
/*  828 */     this.TuskLI2.rotationPointY = yOff + -10.0F;
/*  829 */     this.TuskLI3.rotationPointY = yOff + -10.0F;
/*  830 */     this.TuskLI4.rotationPointY = yOff + -10.0F;
/*  831 */     this.TuskLI5.rotationPointY = yOff + -10.0F;
/*  832 */     this.TuskRI1.rotationPointY = yOff + -10.0F;
/*  833 */     this.TuskRI2.rotationPointY = yOff + -10.0F;
/*  834 */     this.TuskRI3.rotationPointY = yOff + -10.0F;
/*  835 */     this.TuskRI4.rotationPointY = yOff + -10.0F;
/*  836 */     this.TuskRI5.rotationPointY = yOff + -10.0F;
/*  837 */     this.TuskLW1.rotationPointY = yOff + -10.0F;
/*  838 */     this.TuskLW2.rotationPointY = yOff + -10.0F;
/*  839 */     this.TuskLW3.rotationPointY = yOff + -10.0F;
/*  840 */     this.TuskLW4.rotationPointY = yOff + -10.0F;
/*  841 */     this.TuskLW5.rotationPointY = yOff + -10.0F;
/*  842 */     this.TuskRW1.rotationPointY = yOff + -10.0F;
/*  843 */     this.TuskRW2.rotationPointY = yOff + -10.0F;
/*  844 */     this.TuskRW3.rotationPointY = yOff + -10.0F;
/*  845 */     this.TuskRW4.rotationPointY = yOff + -10.0F;
/*  846 */     this.TuskRW5.rotationPointY = yOff + -10.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/*  852 */     float RLegXRot = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
/*  853 */     float LLegXRot = MathHelper.cos(f * 0.6662F) * 0.8F * f1;
/*      */     
/*  855 */     if (f4 < 0.0F) {
/*  856 */       f4 = 0.0F;
/*      */     }
/*      */     
/*  859 */     float HeadXRot = f4 / 57.29578F;
/*  860 */     if (f3 > 20.0F) {
/*  861 */       f3 = 20.0F;
/*      */     }
/*  863 */     if (f3 < -20.0F) {
/*  864 */       f3 = -20.0F;
/*      */     }
/*  866 */     float HeadYRot = f3 / 57.29578F;
/*      */     
/*  868 */     float f10 = 0.0F;
/*  869 */     if (this.isSitting) {
/*  870 */       f10 = 8.0F;
/*      */     }
/*  872 */     AdjustY(f10);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  877 */     float TrunkXRot = 0.0F;
/*      */     
/*  879 */     if (this.trunkCounter != 0) {
/*  880 */       HeadXRot = 0.0F;
/*  881 */       TrunkXRot = MathHelper.cos(this.trunkCounter * 0.2F) * 12.0F;
/*      */     } 
/*  883 */     if (this.isSitting) {
/*  884 */       HeadXRot = 0.0F;
/*  885 */       TrunkXRot = 25.0F;
/*      */     } 
/*  887 */     this.Head.rotateAngleX = -10.0F / this.radianF + HeadXRot;
/*      */     
/*  889 */     this.Head.rotateAngleY = HeadYRot;
/*  890 */     this.HeadBump.rotateAngleY = this.Head.rotateAngleY;
/*  891 */     this.HeadBump.rotateAngleX = this.Head.rotateAngleX;
/*      */     
/*  893 */     this.RightTuskA.rotateAngleY = HeadYRot;
/*  894 */     this.LeftTuskA.rotateAngleY = HeadYRot;
/*  895 */     this.RightTuskA.rotateAngleX = 70.0F / this.radianF + HeadXRot;
/*  896 */     this.LeftTuskA.rotateAngleX = 70.0F / this.radianF + HeadXRot;
/*      */     
/*  898 */     this.Chin.rotateAngleY = HeadYRot;
/*  899 */     this.Chin.rotateAngleX = 113.0F / this.radianF + HeadXRot;
/*  900 */     this.LowerLip.rotateAngleY = HeadYRot;
/*  901 */     this.LowerLip.rotateAngleX = 85.0F / this.radianF + HeadXRot;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  910 */     float EarF = 0.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  917 */     if (this.earCounter != 0) {
/*  918 */       EarF = MathHelper.cos(this.earCounter * 0.5F) * 0.35F;
/*      */     }
/*      */     
/*  921 */     this.RightBigEar.rotateAngleY = 30.0F / this.radianF + HeadYRot + EarF;
/*  922 */     this.RightSmallEar.rotateAngleY = 30.0F / this.radianF + HeadYRot + EarF;
/*  923 */     this.LeftBigEar.rotateAngleY = -30.0F / this.radianF + HeadYRot - EarF;
/*  924 */     this.LeftSmallEar.rotateAngleY = -30.0F / this.radianF + HeadYRot - EarF;
/*      */     
/*  926 */     this.RightBigEar.rotateAngleX = -10.0F / this.radianF + HeadXRot;
/*  927 */     this.RightSmallEar.rotateAngleX = -10.0F / this.radianF + HeadXRot;
/*  928 */     this.LeftBigEar.rotateAngleX = -10.0F / this.radianF + HeadXRot;
/*  929 */     this.LeftSmallEar.rotateAngleX = -10.0F / this.radianF + HeadXRot;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  934 */     this.TrunkA.rotationPointZ = -22.5F;
/*  935 */     adjustAllRotationPoints(this.TrunkA, this.Head);
/*      */     
/*  937 */     this.TrunkA.rotateAngleY = HeadYRot;
/*  938 */     float TrunkARotX = 90.0F - TrunkXRot;
/*  939 */     if (TrunkARotX < 85.0F) {
/*  940 */       TrunkARotX = 85.0F;
/*      */     }
/*  942 */     this.TrunkA.rotateAngleX = TrunkARotX / this.radianF + HeadXRot;
/*      */ 
/*      */     
/*  945 */     this.TrunkB.rotationPointZ = -22.5F;
/*      */     
/*  947 */     adjustAllRotationPoints(this.TrunkB, this.TrunkA);
/*  948 */     this.TrunkB.rotateAngleY = HeadYRot;
/*  949 */     this.TrunkB.rotateAngleX = (95.0F - TrunkXRot * 1.5F) / this.radianF + HeadXRot;
/*      */ 
/*      */ 
/*      */     
/*  953 */     this.TrunkC.rotationPointZ = -22.5F;
/*  954 */     adjustAllRotationPoints(this.TrunkC, this.TrunkB);
/*  955 */     this.TrunkC.rotateAngleY = HeadYRot;
/*  956 */     this.TrunkC.rotateAngleX = (110.0F - TrunkXRot * 3.0F) / this.radianF + HeadXRot;
/*      */ 
/*      */     
/*  959 */     this.TrunkD.rotationPointZ = -21.5F;
/*  960 */     adjustAllRotationPoints(this.TrunkD, this.TrunkC);
/*  961 */     this.TrunkD.rotateAngleY = HeadYRot;
/*  962 */     this.TrunkD.rotateAngleX = (127.0F - TrunkXRot * 4.5F) / this.radianF + HeadXRot;
/*      */ 
/*      */     
/*  965 */     this.TrunkE.rotationPointZ = -19.0F;
/*  966 */     adjustAllRotationPoints(this.TrunkE, this.TrunkD);
/*  967 */     this.TrunkE.rotateAngleY = HeadYRot;
/*  968 */     this.TrunkE.rotateAngleX = (145.0F - TrunkXRot * 6.0F) / this.radianF + HeadXRot;
/*      */ 
/*      */     
/*  971 */     if (this.isSitting) {
/*  972 */       this.FrontRightUpperLeg.rotateAngleX = -30.0F / this.radianF;
/*  973 */       this.FrontLeftUpperLeg.rotateAngleX = -30.0F / this.radianF;
/*  974 */       this.BackLeftUpperLeg.rotateAngleX = -30.0F / this.radianF;
/*  975 */       this.BackRightUpperLeg.rotateAngleX = -30.0F / this.radianF;
/*      */     } else {
/*  977 */       this.FrontRightUpperLeg.rotateAngleX = RLegXRot;
/*  978 */       this.FrontLeftUpperLeg.rotateAngleX = LLegXRot;
/*  979 */       this.BackLeftUpperLeg.rotateAngleX = RLegXRot;
/*  980 */       this.BackRightUpperLeg.rotateAngleX = LLegXRot;
/*      */     } 
/*      */     
/*  983 */     adjustXRotationPoints(this.FrontRightLowerLeg, this.FrontRightUpperLeg);
/*  984 */     adjustXRotationPoints(this.BackRightLowerLeg, this.BackRightUpperLeg);
/*  985 */     adjustXRotationPoints(this.FrontLeftLowerLeg, this.FrontLeftUpperLeg);
/*  986 */     adjustXRotationPoints(this.BackLeftLowerLeg, this.BackLeftUpperLeg);
/*      */ 
/*      */ 
/*      */     
/*  990 */     if (this.isSitting) {
/*  991 */       this.FrontLeftLowerLeg.rotateAngleX = 90.0F / this.radianF;
/*  992 */       this.FrontRightLowerLeg.rotateAngleX = 90.0F / this.radianF;
/*  993 */       this.BackLeftLowerLeg.rotateAngleX = 90.0F / this.radianF;
/*  994 */       this.BackRightLowerLeg.rotateAngleX = 90.0F / this.radianF;
/*      */     } else {
/*  996 */       float LLegXRotD = LLegXRot * 57.29578F;
/*  997 */       float RLegXRotD = RLegXRot * 57.29578F;
/*      */       
/*  999 */       if (LLegXRotD > 0.0F) {
/* 1000 */         LLegXRotD *= 2.0F;
/*      */       }
/* 1002 */       if (RLegXRotD > 0.0F) {
/* 1003 */         RLegXRotD *= 2.0F;
/*      */       }
/*      */       
/* 1006 */       this.FrontLeftLowerLeg.rotateAngleX = LLegXRotD / this.radianF;
/* 1007 */       this.FrontRightLowerLeg.rotateAngleX = RLegXRotD / this.radianF;
/* 1008 */       this.BackLeftLowerLeg.rotateAngleX = RLegXRotD / this.radianF;
/* 1009 */       this.BackRightLowerLeg.rotateAngleX = LLegXRotD / this.radianF;
/*      */     } 
/*      */     
/* 1012 */     if (this.tusks == 0) {
/* 1013 */       this.LeftTuskB.rotateAngleY = HeadYRot;
/* 1014 */       this.LeftTuskC.rotateAngleY = HeadYRot;
/* 1015 */       this.LeftTuskD.rotateAngleY = HeadYRot;
/* 1016 */       this.RightTuskB.rotateAngleY = HeadYRot;
/* 1017 */       this.RightTuskC.rotateAngleY = HeadYRot;
/* 1018 */       this.RightTuskD.rotateAngleY = HeadYRot;
/*      */       
/* 1020 */       this.LeftTuskB.rotateAngleX = 40.0F / this.radianF + HeadXRot;
/* 1021 */       this.LeftTuskC.rotateAngleX = 10.0F / this.radianF + HeadXRot;
/* 1022 */       this.LeftTuskD.rotateAngleX = -20.0F / this.radianF + HeadXRot;
/* 1023 */       this.RightTuskB.rotateAngleX = 40.0F / this.radianF + HeadXRot;
/* 1024 */       this.RightTuskC.rotateAngleX = 10.0F / this.radianF + HeadXRot;
/* 1025 */       this.RightTuskD.rotateAngleX = -20.0F / this.radianF + HeadXRot;
/* 1026 */     } else if (this.tusks == 1) {
/* 1027 */       this.TuskLW1.rotateAngleY = HeadYRot;
/* 1028 */       this.TuskLW2.rotateAngleY = HeadYRot;
/* 1029 */       this.TuskLW3.rotateAngleY = HeadYRot;
/* 1030 */       this.TuskLW4.rotateAngleY = HeadYRot;
/* 1031 */       this.TuskLW5.rotateAngleY = HeadYRot;
/* 1032 */       this.TuskRW1.rotateAngleY = HeadYRot;
/* 1033 */       this.TuskRW2.rotateAngleY = HeadYRot;
/* 1034 */       this.TuskRW3.rotateAngleY = HeadYRot;
/* 1035 */       this.TuskRW4.rotateAngleY = HeadYRot;
/* 1036 */       this.TuskRW5.rotateAngleY = HeadYRot;
/*      */       
/* 1038 */       this.TuskLW1.rotateAngleX = 40.0F / this.radianF + HeadXRot;
/* 1039 */       this.TuskLW2.rotateAngleX = 10.0F / this.radianF + HeadXRot;
/* 1040 */       this.TuskLW3.rotateAngleX = -20.0F / this.radianF + HeadXRot;
/* 1041 */       this.TuskLW4.rotateAngleX = 10.0F / this.radianF + HeadXRot;
/* 1042 */       this.TuskLW5.rotateAngleX = -20.0F / this.radianF + HeadXRot;
/* 1043 */       this.TuskRW1.rotateAngleX = 40.0F / this.radianF + HeadXRot;
/* 1044 */       this.TuskRW2.rotateAngleX = 10.0F / this.radianF + HeadXRot;
/* 1045 */       this.TuskRW3.rotateAngleX = -20.0F / this.radianF + HeadXRot;
/* 1046 */       this.TuskRW4.rotateAngleX = 10.0F / this.radianF + HeadXRot;
/* 1047 */       this.TuskRW5.rotateAngleX = -20.0F / this.radianF + HeadXRot;
/* 1048 */     } else if (this.tusks == 2) {
/* 1049 */       this.TuskLI1.rotateAngleY = HeadYRot;
/* 1050 */       this.TuskLI2.rotateAngleY = HeadYRot;
/* 1051 */       this.TuskLI3.rotateAngleY = HeadYRot;
/* 1052 */       this.TuskLI4.rotateAngleY = HeadYRot;
/* 1053 */       this.TuskLI5.rotateAngleY = HeadYRot;
/* 1054 */       this.TuskRI1.rotateAngleY = HeadYRot;
/* 1055 */       this.TuskRI2.rotateAngleY = HeadYRot;
/* 1056 */       this.TuskRI3.rotateAngleY = HeadYRot;
/* 1057 */       this.TuskRI4.rotateAngleY = HeadYRot;
/* 1058 */       this.TuskRI5.rotateAngleY = HeadYRot;
/*      */       
/* 1060 */       this.TuskLI1.rotateAngleX = 40.0F / this.radianF + HeadXRot;
/* 1061 */       this.TuskLI2.rotateAngleX = 10.0F / this.radianF + HeadXRot;
/* 1062 */       this.TuskLI3.rotateAngleX = -20.0F / this.radianF + HeadXRot;
/* 1063 */       this.TuskLI4.rotateAngleX = 10.0F / this.radianF + HeadXRot;
/* 1064 */       this.TuskLI5.rotateAngleX = -20.0F / this.radianF + HeadXRot;
/* 1065 */       this.TuskRI1.rotateAngleX = 40.0F / this.radianF + HeadXRot;
/* 1066 */       this.TuskRI2.rotateAngleX = 10.0F / this.radianF + HeadXRot;
/* 1067 */       this.TuskRI3.rotateAngleX = -20.0F / this.radianF + HeadXRot;
/* 1068 */       this.TuskRI4.rotateAngleX = 10.0F / this.radianF + HeadXRot;
/* 1069 */       this.TuskRI5.rotateAngleX = -20.0F / this.radianF + HeadXRot;
/* 1070 */     } else if (this.tusks == 3) {
/* 1071 */       this.TuskLD1.rotateAngleY = HeadYRot;
/* 1072 */       this.TuskLD2.rotateAngleY = HeadYRot;
/* 1073 */       this.TuskLD3.rotateAngleY = HeadYRot;
/* 1074 */       this.TuskLD4.rotateAngleY = HeadYRot;
/* 1075 */       this.TuskLD5.rotateAngleY = HeadYRot;
/* 1076 */       this.TuskRD1.rotateAngleY = HeadYRot;
/* 1077 */       this.TuskRD2.rotateAngleY = HeadYRot;
/* 1078 */       this.TuskRD3.rotateAngleY = HeadYRot;
/* 1079 */       this.TuskRD4.rotateAngleY = HeadYRot;
/* 1080 */       this.TuskRD5.rotateAngleY = HeadYRot;
/*      */       
/* 1082 */       this.TuskLD1.rotateAngleX = 40.0F / this.radianF + HeadXRot;
/* 1083 */       this.TuskLD2.rotateAngleX = 10.0F / this.radianF + HeadXRot;
/* 1084 */       this.TuskLD3.rotateAngleX = -20.0F / this.radianF + HeadXRot;
/* 1085 */       this.TuskLD4.rotateAngleX = 10.0F / this.radianF + HeadXRot;
/* 1086 */       this.TuskLD5.rotateAngleX = -20.0F / this.radianF + HeadXRot;
/* 1087 */       this.TuskRD1.rotateAngleX = 40.0F / this.radianF + HeadXRot;
/* 1088 */       this.TuskRD2.rotateAngleX = 10.0F / this.radianF + HeadXRot;
/* 1089 */       this.TuskRD3.rotateAngleX = -20.0F / this.radianF + HeadXRot;
/* 1090 */       this.TuskRD4.rotateAngleX = 10.0F / this.radianF + HeadXRot;
/* 1091 */       this.TuskRD5.rotateAngleX = -20.0F / this.radianF + HeadXRot;
/*      */     } 
/*      */ 
/*      */     
/* 1095 */     this.StorageLeftBedroll.rotateAngleX = LLegXRot / 10.0F;
/* 1096 */     this.StorageFrontLeftChest.rotateAngleX = LLegXRot / 5.0F;
/* 1097 */     this.StorageBackLeftChest.rotateAngleX = LLegXRot / 5.0F;
/*      */     
/* 1099 */     this.StorageRightBedroll.rotateAngleX = RLegXRot / 10.0F;
/* 1100 */     this.StorageFrontRightChest.rotateAngleX = RLegXRot / 5.0F;
/* 1101 */     this.StorageBackRightChest.rotateAngleX = RLegXRot / 5.0F;
/*      */     
/* 1103 */     this.FortNeckBeam.rotateAngleZ = LLegXRot / 50.0F;
/* 1104 */     this.FortBackBeam.rotateAngleZ = LLegXRot / 50.0F;
/*      */     
/* 1106 */     this.FortBackRightWall.rotateAngleZ = LLegXRot / 50.0F;
/* 1107 */     this.FortBackLeftWall.rotateAngleZ = LLegXRot / 50.0F;
/* 1108 */     this.FortBackWall.rotateAngleX = 0.0F - LLegXRot / 50.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1113 */     float tailMov = f1 * 0.9F;
/* 1114 */     if (tailMov < 0.0F) {
/* 1115 */       tailMov = 0.0F;
/*      */     }
/*      */     
/* 1118 */     if (this.tailCounter != 0) {
/* 1119 */       this.TailRoot.rotateAngleY = MathHelper.cos(f2 * 0.4F) * 1.3F;
/* 1120 */       tailMov = 30.0F / this.radianF;
/*      */     } else {
/* 1122 */       this.TailRoot.rotateAngleY = 0.0F;
/*      */     } 
/*      */     
/* 1125 */     this.TailRoot.rotateAngleX = 17.0F / this.radianF + tailMov;
/* 1126 */     this.Tail.rotateAngleX = 6.5F / this.radianF + tailMov;
/* 1127 */     this.TailPlush.rotateAngleY = this.TailRoot.rotateAngleY;
/* 1128 */     this.Tail.rotateAngleY = this.TailPlush.rotateAngleY;
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
/*      */ 
/*      */   
/*      */   private void adjustXRotationPoints(ModelRenderer target, ModelRenderer origin) {
/* 1142 */     float distance = target.rotationPointY - origin.rotationPointY;
/* 1143 */     if (distance < 0.0F) {
/* 1144 */       distance *= -1.0F;
/*      */     }
/* 1146 */     origin.rotationPointZ += MathHelper.sin(origin.rotateAngleX) * distance;
/* 1147 */     origin.rotationPointY += MathHelper.cos(origin.rotateAngleX) * distance;
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
/*      */ 
/*      */ 
/*      */   
/*      */   private void adjustYRotationPoints(ModelRenderer target, ModelRenderer origin) {
/* 1162 */     float distanceZ = 0.0F;
/* 1163 */     if (target.rotationPointZ > origin.rotationPointZ) {
/* 1164 */       distanceZ = target.rotationPointZ - origin.rotationPointZ;
/*      */     } else {
/* 1166 */       distanceZ = origin.rotationPointZ - target.rotationPointZ;
/*      */     } 
/*      */     
/* 1169 */     origin.rotationPointZ -= MathHelper.cos(origin.rotateAngleY) * distanceZ;
/* 1170 */     origin.rotationPointX -= MathHelper.sin(origin.rotateAngleY) * distanceZ;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void adjustAllRotationPoints(ModelRenderer target, ModelRenderer origin) {
/* 1176 */     float distanceY = 0.0F;
/* 1177 */     if (target.rotationPointY > origin.rotationPointY) {
/* 1178 */       distanceY = target.rotationPointY - origin.rotationPointY;
/*      */     } else {
/* 1180 */       distanceY = origin.rotationPointY - target.rotationPointY;
/*      */     } 
/*      */     
/* 1183 */     float distanceZ = 0.0F;
/* 1184 */     if (target.rotationPointZ > origin.rotationPointZ) {
/* 1185 */       distanceZ = target.rotationPointZ - origin.rotationPointZ;
/*      */     } else {
/* 1187 */       distanceZ = origin.rotationPointZ - target.rotationPointZ;
/*      */     } 
/*      */     
/* 1190 */     origin.rotationPointY += MathHelper.sin(origin.rotateAngleX) * distanceY;
/* 1191 */     origin.rotationPointZ -= MathHelper.cos(origin.rotateAngleY) * MathHelper.cos(origin.rotateAngleX) * distanceY;
/* 1192 */     origin.rotationPointX -= MathHelper.sin(origin.rotateAngleY) * MathHelper.cos(origin.rotateAngleX) * distanceY;
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelElephant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */