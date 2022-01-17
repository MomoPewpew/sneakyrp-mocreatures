/*     */ package drzhark.mocreatures.client.model;
/*     */ 
/*     */ import drzhark.mocreatures.entity.monster.MoCEntityOgre;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCModelOgre
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer Head;
/*     */   ModelRenderer Brow;
/*     */   ModelRenderer NoseBridge;
/*     */   ModelRenderer Nose;
/*     */   ModelRenderer RgtTusk;
/*     */   ModelRenderer RgtTooth;
/*     */   ModelRenderer LftTooth;
/*     */   ModelRenderer LftTusk;
/*     */   ModelRenderer Lip;
/*     */   ModelRenderer RgtEar;
/*     */   ModelRenderer RgtRing;
/*     */   ModelRenderer RgtRingHole;
/*     */   ModelRenderer LftEar;
/*     */   ModelRenderer LftRing;
/*     */   ModelRenderer LftRingHole;
/*     */   ModelRenderer HairRope;
/*     */   ModelRenderer Hair1;
/*     */   ModelRenderer Hair2;
/*     */   ModelRenderer Hair3;
/*     */   ModelRenderer DiamondHorn;
/*     */   ModelRenderer RgtHorn;
/*     */   ModelRenderer RgtHornTip;
/*     */   ModelRenderer LftHorn;
/*     */   ModelRenderer LftHornTip;
/*     */   ModelRenderer RgtShoulder;
/*     */   ModelRenderer LftShoulder;
/*     */   ModelRenderer NeckRest;
/*     */   ModelRenderer Chest;
/*     */   ModelRenderer Stomach;
/*     */   ModelRenderer ButtCover;
/*     */   ModelRenderer LoinCloth;
/*     */   ModelRenderer RgtThigh;
/*     */   ModelRenderer RgtKnee;
/*     */   ModelRenderer RgtLeg;
/*     */   ModelRenderer RgtToes;
/*     */   ModelRenderer RgtBigToe;
/*     */   ModelRenderer LftThigh;
/*     */   ModelRenderer LftKnee;
/*     */   ModelRenderer LftLeg;
/*     */   ModelRenderer LftToes;
/*     */   ModelRenderer LftBigToe;
/*     */   ModelRenderer LftArm;
/*     */   ModelRenderer LftElbow;
/*     */   ModelRenderer LftHand;
/*     */   ModelRenderer LftWeaponEnd;
/*     */   ModelRenderer LftWeaponRoot;
/*     */   ModelRenderer LftWeaponLump;
/*     */   ModelRenderer LftWeaponBetween;
/*     */   ModelRenderer LftWeaponTip;
/*     */   ModelRenderer LftSpike;
/*     */   ModelRenderer LftSpike1;
/*     */   ModelRenderer LftSpike2;
/*     */   ModelRenderer LftSpike3;
/*     */   ModelRenderer LftSpike4;
/*     */   ModelRenderer LftHammerNeck;
/*     */   ModelRenderer LftHammerHeadSupport;
/*     */   ModelRenderer LftHammerHead;
/*     */   ModelRenderer RgtArm;
/*     */   ModelRenderer RgtElbow;
/*     */   ModelRenderer RgtHand;
/*     */   ModelRenderer RgtWeaponEnd;
/*     */   ModelRenderer RgtWeaponRoot;
/*     */   ModelRenderer RgtWeaponLump;
/*     */   ModelRenderer RgtWeaponBetween;
/*     */   ModelRenderer RgtWeaponTip;
/*     */   ModelRenderer RgtSpike;
/*     */   ModelRenderer RgtSpike1;
/*     */   ModelRenderer RgtSpike2;
/*     */   ModelRenderer RgtSpike3;
/*     */   ModelRenderer RgtSpike4;
/*     */   ModelRenderer RgtHammerNeck;
/*     */   ModelRenderer RgtHammerHeadSupport;
/*     */   ModelRenderer RgtHammerHead;
/*     */   ModelRenderer Head3RgtEar;
/*     */   ModelRenderer Head3LftEar;
/*     */   ModelRenderer Head3Eyelid;
/*     */   ModelRenderer Head3Nose;
/*     */   ModelRenderer Head3;
/*     */   ModelRenderer Head3Brow;
/*     */   ModelRenderer Head3Hair;
/*     */   ModelRenderer Head3Lip;
/*     */   ModelRenderer Head3RgtTusk;
/*     */   ModelRenderer Head3RgtTooth;
/*     */   ModelRenderer Head3LftTooth;
/*     */   ModelRenderer Head3LftTusk;
/*     */   ModelRenderer Head3RingHole;
/*     */   ModelRenderer Head3Ring;
/*     */   ModelRenderer Head2Chin;
/*     */   ModelRenderer Head2;
/*     */   ModelRenderer Head2Lip;
/*     */   ModelRenderer Head2LftTusk;
/*     */   ModelRenderer Head2RgtTusk;
/*     */   ModelRenderer Head2Nose;
/*     */   ModelRenderer Head2NoseBridge;
/*     */   ModelRenderer Head2Brow;
/*     */   ModelRenderer Head2RgtHorn;
/*     */   ModelRenderer Head2LftHorn;
/*     */   ModelRenderer Head2DiamondHorn;
/* 113 */   private float radianF = 57.29578F;
/*     */   private int type;
/*     */   private int attackCounter;
/*     */   private int headMoving;
/*     */   private int armToAnimate;
/*     */   
/*     */   public MoCModelOgre() {
/* 120 */     this.textureWidth = 128;
/* 121 */     this.textureHeight = 128;
/*     */     
/* 123 */     this.Head = new ModelRenderer(this, 80, 0);
/* 124 */     this.Head.addBox(-6.0F, -12.0F, -6.0F, 12, 12, 12);
/* 125 */     this.Head.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/* 127 */     this.Brow = new ModelRenderer(this, 68, 7);
/* 128 */     this.Brow.addBox(-5.0F, -10.5F, -8.0F, 10, 3, 2);
/* 129 */     this.Brow.setRotationPoint(0.0F, -13.0F, 0.0F);
/* 130 */     setRotation(this.Brow, -0.0872665F, 0.0F, 0.0F);
/*     */     
/* 132 */     this.NoseBridge = new ModelRenderer(this, 80, 4);
/* 133 */     this.NoseBridge.addBox(-1.0F, -7.0F, -8.0F, 2, 2, 1);
/* 134 */     this.NoseBridge.setRotationPoint(0.0F, -13.0F, 0.0F);
/* 135 */     setRotation(this.NoseBridge, -0.1745329F, 0.0F, 0.0F);
/*     */     
/* 137 */     this.Nose = new ModelRenderer(this, 80, 0);
/* 138 */     this.Nose.addBox(-2.0F, -7.0F, -7.0F, 4, 2, 2);
/* 139 */     this.Nose.setRotationPoint(0.0F, -13.0F, 0.0F);
/* 140 */     setRotation(this.Nose, 0.0872665F, 0.0F, 0.0F);
/*     */     
/* 142 */     this.RgtTusk = new ModelRenderer(this, 60, 4);
/* 143 */     this.RgtTusk.addBox(-3.5F, -6.0F, -6.5F, 1, 2, 1);
/* 144 */     this.RgtTusk.setRotationPoint(0.0F, -13.0F, 0.0F);
/* 145 */     setRotation(this.RgtTusk, 0.1745329F, 0.0F, 0.0F);
/*     */     
/* 147 */     this.RgtTooth = new ModelRenderer(this, 64, 4);
/* 148 */     this.RgtTooth.addBox(-1.5F, -5.0F, -6.5F, 1, 1, 1);
/* 149 */     this.RgtTooth.setRotationPoint(0.0F, -13.0F, 0.0F);
/* 150 */     setRotation(this.RgtTooth, 0.1745329F, 0.0F, 0.0F);
/*     */     
/* 152 */     this.LftTooth = new ModelRenderer(this, 72, 4);
/* 153 */     this.LftTooth.addBox(0.5F, -5.0F, -6.5F, 1, 1, 1);
/* 154 */     this.LftTooth.setRotationPoint(0.0F, -13.0F, 0.0F);
/* 155 */     setRotation(this.LftTooth, 0.1745329F, 0.0F, 0.0F);
/*     */     
/* 157 */     this.LftTusk = new ModelRenderer(this, 76, 4);
/* 158 */     this.LftTusk.addBox(2.5F, -6.0F, -6.5F, 1, 2, 1);
/* 159 */     this.LftTusk.setRotationPoint(0.0F, -13.0F, 0.0F);
/* 160 */     setRotation(this.LftTusk, 0.1745329F, 0.0F, 0.0F);
/*     */     
/* 162 */     this.Lip = new ModelRenderer(this, 60, 0);
/* 163 */     this.Lip.addBox(-4.0F, -4.0F, -7.0F, 8, 2, 2);
/* 164 */     this.Lip.setRotationPoint(0.0F, -13.0F, 0.0F);
/* 165 */     setRotation(this.Lip, 0.1745329F, 0.0F, 0.0F);
/*     */     
/* 167 */     this.RgtEar = new ModelRenderer(this, 60, 12);
/* 168 */     this.RgtEar.addBox(-9.0F, -9.0F, -1.0F, 3, 5, 2);
/* 169 */     this.RgtEar.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/* 171 */     this.RgtRing = new ModelRenderer(this, 32, 58);
/* 172 */     this.RgtRing.addBox(-8.0F, -6.0F, -2.0F, 1, 4, 4);
/* 173 */     this.RgtRing.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/* 175 */     this.RgtRingHole = new ModelRenderer(this, 26, 50);
/* 176 */     this.RgtRingHole.addBox(-8.0F, -5.0F, -1.0F, 1, 2, 2);
/* 177 */     this.RgtRingHole.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/* 179 */     this.LftEar = new ModelRenderer(this, 70, 12);
/* 180 */     this.LftEar.addBox(6.0F, -9.0F, -1.0F, 3, 5, 2);
/* 181 */     this.LftEar.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/* 183 */     this.LftRing = new ModelRenderer(this, 32, 58);
/* 184 */     this.LftRing.addBox(7.0F, -6.0F, -2.0F, 1, 4, 4);
/* 185 */     this.LftRing.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/* 187 */     this.LftRingHole = new ModelRenderer(this, 26, 50);
/* 188 */     this.LftRingHole.addBox(7.0F, -5.0F, -1.0F, 1, 2, 2);
/* 189 */     this.LftRingHole.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/* 191 */     this.HairRope = new ModelRenderer(this, 82, 83);
/* 192 */     this.HairRope.addBox(-2.0F, -8.0F, 9.0F, 4, 4, 4);
/* 193 */     this.HairRope.setRotationPoint(0.0F, -13.0F, 0.0F);
/* 194 */     setRotation(this.HairRope, 0.6108652F, 0.0F, 0.0F);
/*     */     
/* 196 */     this.Hair1 = new ModelRenderer(this, 78, 107);
/* 197 */     this.Hair1.addBox(-3.0F, -9.0F, 13.0F, 6, 8, 3);
/* 198 */     this.Hair1.setRotationPoint(0.0F, -13.0F, 0.0F);
/* 199 */     setRotation(this.Hair1, 0.6108652F, 0.0F, 0.0F);
/*     */     
/* 201 */     this.Hair2 = new ModelRenderer(this, 60, 107);
/* 202 */     this.Hair2.addBox(-3.0F, -6.5F, 11.6F, 6, 8, 3);
/* 203 */     this.Hair2.setRotationPoint(0.0F, -13.0F, 0.0F);
/* 204 */     setRotation(this.Hair2, 0.2617994F, 0.0F, 0.0F);
/*     */     
/* 206 */     this.Hair3 = new ModelRenderer(this, 42, 107);
/* 207 */     this.Hair3.addBox(-3.0F, -2.4F, 11.4F, 6, 8, 3);
/* 208 */     this.Hair3.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/* 210 */     this.DiamondHorn = new ModelRenderer(this, 120, 31);
/* 211 */     this.DiamondHorn.addBox(-1.0F, -17.0F, -6.0F, 2, 6, 2);
/* 212 */     this.DiamondHorn.setRotationPoint(0.0F, -13.0F, 0.0F);
/* 213 */     setRotation(this.DiamondHorn, 0.0872665F, 0.0F, 0.0F);
/*     */     
/* 215 */     this.RgtHorn = new ModelRenderer(this, 46, 6);
/* 216 */     this.RgtHorn.addBox(-6.0F, -12.0F, -11.0F, 2, 2, 5);
/* 217 */     this.RgtHorn.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/* 219 */     this.RgtHornTip = new ModelRenderer(this, 44, 13);
/* 220 */     this.RgtHornTip.addBox(-6.0F, -15.0F, -11.0F, 2, 3, 2);
/* 221 */     this.RgtHornTip.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/* 223 */     this.LftHorn = new ModelRenderer(this, 46, 6);
/* 224 */     this.LftHorn.addBox(4.0F, -12.0F, -11.0F, 2, 2, 5);
/* 225 */     this.LftHorn.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/* 227 */     this.LftHornTip = new ModelRenderer(this, 52, 13);
/* 228 */     this.LftHornTip.addBox(4.0F, -15.0F, -11.0F, 2, 3, 2);
/* 229 */     this.LftHornTip.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/* 231 */     this.NeckRest = new ModelRenderer(this, 39, 20);
/* 232 */     this.NeckRest.addBox(-7.0F, -19.0F, -3.0F, 14, 3, 11);
/* 233 */     this.NeckRest.setRotationPoint(0.0F, 5.0F, 0.0F);
/*     */     
/* 235 */     this.Chest = new ModelRenderer(this, 32, 34);
/* 236 */     this.Chest.addBox(-9.5F, -17.8F, -7.3F, 19, 11, 13);
/* 237 */     this.Chest.setRotationPoint(0.0F, 5.0F, 0.0F);
/*     */     
/* 239 */     setRotation(this.Chest, -0.1745329F, 0.0F, 0.0F);
/* 240 */     this.Stomach = new ModelRenderer(this, 28, 58);
/* 241 */     this.Stomach.addBox(-11.0F, -8.0F, -6.0F, 22, 11, 14);
/* 242 */     this.Stomach.setRotationPoint(0.0F, 5.0F, 0.0F);
/*     */     
/* 244 */     this.ButtCover = new ModelRenderer(this, 32, 118);
/* 245 */     this.ButtCover.addBox(-4.0F, 0.0F, 0.0F, 8, 8, 2);
/* 246 */     this.ButtCover.setRotationPoint(0.0F, 8.0F, 6.0F);
/*     */     
/* 248 */     this.LoinCloth = new ModelRenderer(this, 32, 118);
/* 249 */     this.LoinCloth.addBox(-4.0F, 0.0F, -2.0F, 8, 8, 2);
/* 250 */     this.LoinCloth.setRotationPoint(0.0F, 8.0F, -4.0F);
/*     */     
/* 252 */     this.RgtThigh = new ModelRenderer(this, 0, 83);
/* 253 */     this.RgtThigh.addBox(-10.0F, 0.0F, -5.0F, 10, 11, 10);
/* 254 */     this.RgtThigh.setRotationPoint(-2.0F, 4.0F, 1.0F);
/*     */     
/* 256 */     this.RgtLeg = new ModelRenderer(this, 0, 104);
/* 257 */     this.RgtLeg.addBox(-4.0F, -1.0F, -4.0F, 8, 11, 8);
/* 258 */     this.RgtLeg.setRotationPoint(-5.0F, 10.0F, 0.0F);
/* 259 */     this.RgtThigh.addChild(this.RgtLeg);
/*     */     
/* 261 */     this.RgtKnee = new ModelRenderer(this, 0, 88);
/* 262 */     this.RgtKnee.addBox(-2.0F, -2.0F, -0.5F, 4, 4, 1);
/* 263 */     this.RgtKnee.setRotationPoint(0.0F, 2.0F, -4.25F);
/* 264 */     this.RgtLeg.addChild(this.RgtKnee);
/*     */     
/* 266 */     this.RgtToes = new ModelRenderer(this, 0, 123);
/* 267 */     this.RgtToes.addBox(-2.5F, -1.0F, -3.0F, 5, 2, 3);
/* 268 */     this.RgtToes.setRotationPoint(-1.5F, 9.0F, -3.5F);
/* 269 */     this.RgtLeg.addChild(this.RgtToes);
/*     */     
/* 271 */     this.RgtBigToe = new ModelRenderer(this, 20, 123);
/* 272 */     this.RgtBigToe.addBox(-1.5F, -1.0F, -3.0F, 3, 2, 3);
/* 273 */     this.RgtBigToe.setRotationPoint(2.5F, 9.0F, -4.0F);
/* 274 */     this.RgtLeg.addChild(this.RgtBigToe);
/*     */     
/* 276 */     this.LftThigh = new ModelRenderer(this, 88, 83);
/* 277 */     this.LftThigh.addBox(0.0F, 0.0F, -5.0F, 10, 11, 10);
/* 278 */     this.LftThigh.setRotationPoint(2.0F, 4.0F, 1.0F);
/*     */     
/* 280 */     this.LftLeg = new ModelRenderer(this, 96, 104);
/* 281 */     this.LftLeg.addBox(-4.0F, -1.0F, -4.0F, 8, 11, 8);
/* 282 */     this.LftLeg.setRotationPoint(5.0F, 10.0F, 0.0F);
/* 283 */     this.LftThigh.addChild(this.LftLeg);
/*     */     
/* 285 */     this.LftKnee = new ModelRenderer(this, 118, 88);
/* 286 */     this.LftKnee.addBox(-2.0F, -2.0F, -0.5F, 4, 4, 1);
/* 287 */     this.LftKnee.setRotationPoint(0.0F, 2.0F, -4.25F);
/* 288 */     this.LftLeg.addChild(this.LftKnee);
/*     */     
/* 290 */     this.LftToes = new ModelRenderer(this, 112, 123);
/* 291 */     this.LftToes.addBox(-2.5F, -1.0F, -3.0F, 5, 2, 3);
/* 292 */     this.LftToes.setRotationPoint(1.5F, 9.0F, -3.5F);
/* 293 */     this.LftLeg.addChild(this.LftToes);
/*     */     
/* 295 */     this.LftBigToe = new ModelRenderer(this, 96, 123);
/* 296 */     this.LftBigToe.addBox(-1.5F, -1.0F, -3.0F, 3, 2, 3);
/* 297 */     this.LftBigToe.setRotationPoint(-2.5F, 9.0F, -4.0F);
/* 298 */     this.LftLeg.addChild(this.LftBigToe);
/*     */ 
/*     */     
/* 301 */     this.LftShoulder = new ModelRenderer(this, 96, 31);
/* 302 */     this.LftShoulder.addBox(0.0F, -3.0F, -4.0F, 8, 7, 8);
/* 303 */     this.LftShoulder.setRotationPoint(7.0F, -10.0F, 2.0F);
/*     */     
/* 305 */     this.LftArm = new ModelRenderer(this, 100, 66);
/* 306 */     this.LftArm.addBox(0.0F, 0.0F, -4.0F, 6, 9, 8);
/* 307 */     this.LftArm.setRotationPoint(6.0F, -1.0F, 1.0F);
/* 308 */     this.LftShoulder.addChild(this.LftArm);
/*     */     
/* 310 */     this.LftHand = new ModelRenderer(this, 96, 46);
/* 311 */     this.LftHand.addBox(-4.0F, 0.0F, -4.0F, 8, 12, 8);
/* 312 */     this.LftHand.setRotationPoint(3.0F, 8.0F, -1.0F);
/* 313 */     this.LftArm.addChild(this.LftHand);
/*     */     
/* 315 */     this.LftElbow = new ModelRenderer(this, 86, 64);
/* 316 */     this.LftElbow.addBox(-2.0F, -1.5F, -0.5F, 4, 3, 1);
/* 317 */     this.LftElbow.setRotationPoint(0.0F, 2.5F, 4.0F);
/* 318 */     this.LftHand.addChild(this.LftElbow);
/*     */     
/* 320 */     this.LftWeaponRoot = new ModelRenderer(this, 24, 104);
/* 321 */     this.LftWeaponRoot.addBox(-1.5F, -1.5F, -4.0F, 3, 3, 4);
/* 322 */     this.LftWeaponRoot.setRotationPoint(-0.5F, 8.5F, -4.0F);
/* 323 */     this.LftHand.addChild(this.LftWeaponRoot);
/*     */     
/* 325 */     this.LftWeaponEnd = new ModelRenderer(this, 74, 90);
/* 326 */     this.LftWeaponEnd.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 2);
/* 327 */     this.LftWeaponEnd.setRotationPoint(0.0F, 0.0F, 8.0F);
/* 328 */     this.LftWeaponRoot.addChild(this.LftWeaponEnd);
/*     */     
/* 330 */     this.LftWeaponLump = new ModelRenderer(this, 30, 83);
/* 331 */     this.LftWeaponLump.addBox(-2.5F, -2.5F, -4.0F, 5, 5, 4);
/* 332 */     this.LftWeaponLump.setRotationPoint(0.0F, 0.0F, -4.0F);
/* 333 */     this.LftWeaponRoot.addChild(this.LftWeaponLump);
/*     */     
/* 335 */     this.LftWeaponBetween = new ModelRenderer(this, 83, 42);
/* 336 */     this.LftWeaponBetween.addBox(-1.5F, -1.5F, -2.0F, 3, 3, 2);
/* 337 */     this.LftWeaponBetween.setRotationPoint(0.0F, 0.0F, -4.0F);
/* 338 */     this.LftWeaponLump.addChild(this.LftWeaponBetween);
/*     */     
/* 340 */     this.LftWeaponTip = new ModelRenderer(this, 60, 118);
/* 341 */     this.LftWeaponTip.addBox(-2.5F, -2.5F, -5.0F, 5, 5, 5);
/* 342 */     this.LftWeaponTip.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 343 */     this.LftWeaponBetween.addChild(this.LftWeaponTip);
/*     */     
/* 345 */     this.LftHammerNeck = new ModelRenderer(this, 32, 39);
/* 346 */     this.LftHammerNeck.addBox(-0.5F, -4.0F, -4.0F, 1, 4, 4);
/* 347 */     this.LftHammerNeck.setRotationPoint(0.0F, -2.5F, -1.0F);
/* 348 */     this.LftWeaponTip.addChild(this.LftHammerNeck);
/*     */     
/* 350 */     this.LftHammerHeadSupport = new ModelRenderer(this, 0, 0);
/* 351 */     this.LftHammerHeadSupport.addBox(-1.0F, 0.0F, -2.0F, 2, 2, 4);
/* 352 */     this.LftHammerHeadSupport.setRotationPoint(0.0F, 2.5F, -3.0F);
/* 353 */     this.LftWeaponTip.addChild(this.LftHammerHeadSupport);
/*     */     
/* 355 */     this.LftHammerHead = new ModelRenderer(this, 32, 3);
/* 356 */     this.LftHammerHead.addBox(-2.0F, 0.0F, -2.5F, 4, 3, 5);
/* 357 */     this.LftHammerHead.setRotationPoint(0.0F, 2.0F, 0.0F);
/* 358 */     this.LftHammerHeadSupport.addChild(this.LftHammerHead);
/*     */     
/* 360 */     this.LftSpike = new ModelRenderer(this, 52, 118);
/* 361 */     this.LftSpike.addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3);
/* 362 */     this.LftSpike.setRotationPoint(0.0F, 0.0F, -5.0F);
/* 363 */     this.LftWeaponTip.addChild(this.LftSpike);
/*     */     
/* 365 */     this.LftSpike1 = new ModelRenderer(this, 52, 118);
/* 366 */     this.LftSpike1.addBox(-3.0F, -1.0F, -1.0F, 3, 2, 2);
/* 367 */     this.LftSpike1.setRotationPoint(-2.5F, 0.0F, -3.0F);
/* 368 */     this.LftWeaponTip.addChild(this.LftSpike1);
/*     */     
/* 370 */     this.LftSpike2 = new ModelRenderer(this, 52, 118);
/* 371 */     this.LftSpike2.addBox(3.0F, -1.0F, -1.0F, 3, 2, 2);
/* 372 */     this.LftSpike2.setRotationPoint(-0.5F, 0.0F, -3.0F);
/* 373 */     this.LftWeaponTip.addChild(this.LftSpike2);
/*     */     
/* 375 */     this.LftSpike3 = new ModelRenderer(this, 52, 118);
/* 376 */     this.LftSpike3.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
/* 377 */     this.LftSpike3.setRotationPoint(0.0F, 2.5F, -3.0F);
/* 378 */     this.LftWeaponTip.addChild(this.LftSpike3);
/*     */     
/* 380 */     this.LftSpike4 = new ModelRenderer(this, 52, 118);
/* 381 */     this.LftSpike4.addBox(-1.0F, -3.0F, -1.0F, 2, 3, 2);
/* 382 */     this.LftSpike4.setRotationPoint(0.0F, -2.5F, -3.0F);
/* 383 */     this.LftWeaponTip.addChild(this.LftSpike4);
/*     */ 
/*     */     
/* 386 */     this.RgtShoulder = new ModelRenderer(this, 0, 31);
/* 387 */     this.RgtShoulder.addBox(0.0F, -3.0F, -4.0F, 8, 7, 8);
/* 388 */     this.RgtShoulder.setRotationPoint(-15.0F, -10.0F, 2.0F);
/*     */     
/* 390 */     this.RgtArm = new ModelRenderer(this, 0, 66);
/* 391 */     this.RgtArm.addBox(0.0F, 0.0F, -4.0F, 6, 9, 8);
/* 392 */     this.RgtArm.setRotationPoint(-4.0F, -1.0F, 1.0F);
/* 393 */     this.RgtShoulder.addChild(this.RgtArm);
/*     */     
/* 395 */     this.RgtHand = new ModelRenderer(this, 0, 46);
/* 396 */     this.RgtHand.addBox(-4.0F, 0.0F, -4.0F, 8, 12, 8);
/* 397 */     this.RgtHand.setRotationPoint(3.0F, 8.0F, -1.0F);
/* 398 */     this.RgtArm.addChild(this.RgtHand);
/*     */     
/* 400 */     this.RgtElbow = new ModelRenderer(this, 86, 64);
/* 401 */     this.RgtElbow.addBox(-2.0F, -1.5F, -0.5F, 4, 3, 1);
/* 402 */     this.RgtElbow.setRotationPoint(0.0F, 2.5F, 4.0F);
/* 403 */     this.RgtHand.addChild(this.RgtElbow);
/*     */     
/* 405 */     this.RgtWeaponRoot = new ModelRenderer(this, 24, 104);
/* 406 */     this.RgtWeaponRoot.addBox(-1.5F, -1.5F, -4.0F, 3, 3, 4);
/* 407 */     this.RgtWeaponRoot.setRotationPoint(-0.5F, 8.5F, -4.0F);
/* 408 */     this.RgtHand.addChild(this.RgtWeaponRoot);
/*     */     
/* 410 */     this.RgtWeaponEnd = new ModelRenderer(this, 74, 90);
/* 411 */     this.RgtWeaponEnd.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 2);
/* 412 */     this.RgtWeaponEnd.setRotationPoint(0.0F, 0.0F, 8.0F);
/* 413 */     this.RgtWeaponRoot.addChild(this.RgtWeaponEnd);
/*     */     
/* 415 */     this.RgtWeaponLump = new ModelRenderer(this, 30, 83);
/* 416 */     this.RgtWeaponLump.addBox(-2.5F, -2.5F, -4.0F, 5, 5, 4);
/* 417 */     this.RgtWeaponLump.setRotationPoint(0.0F, 0.0F, -4.0F);
/* 418 */     this.RgtWeaponRoot.addChild(this.RgtWeaponLump);
/*     */     
/* 420 */     this.RgtWeaponBetween = new ModelRenderer(this, 83, 42);
/* 421 */     this.RgtWeaponBetween.addBox(-1.5F, -1.5F, -2.0F, 3, 3, 2);
/* 422 */     this.RgtWeaponBetween.setRotationPoint(0.0F, 0.0F, -4.0F);
/* 423 */     this.RgtWeaponLump.addChild(this.RgtWeaponBetween);
/*     */     
/* 425 */     this.RgtWeaponTip = new ModelRenderer(this, 60, 118);
/* 426 */     this.RgtWeaponTip.addBox(-2.5F, -2.5F, -5.0F, 5, 5, 5);
/* 427 */     this.RgtWeaponTip.setRotationPoint(0.0F, 0.0F, -2.0F);
/* 428 */     this.RgtWeaponBetween.addChild(this.RgtWeaponTip);
/*     */     
/* 430 */     this.RgtHammerNeck = new ModelRenderer(this, 32, 39);
/* 431 */     this.RgtHammerNeck.addBox(-0.5F, -4.0F, -4.0F, 1, 4, 4);
/* 432 */     this.RgtHammerNeck.setRotationPoint(0.0F, -2.5F, -1.0F);
/* 433 */     this.RgtWeaponTip.addChild(this.RgtHammerNeck);
/*     */     
/* 435 */     this.RgtHammerHeadSupport = new ModelRenderer(this, 0, 0);
/* 436 */     this.RgtHammerHeadSupport.addBox(-1.0F, 0.0F, -2.0F, 2, 2, 4);
/* 437 */     this.RgtHammerHeadSupport.setRotationPoint(0.0F, 2.5F, -3.0F);
/* 438 */     this.RgtWeaponTip.addChild(this.RgtHammerHeadSupport);
/*     */     
/* 440 */     this.RgtHammerHead = new ModelRenderer(this, 32, 3);
/* 441 */     this.RgtHammerHead.addBox(-2.0F, 0.0F, -2.5F, 4, 3, 5);
/* 442 */     this.RgtHammerHead.setRotationPoint(0.0F, 2.0F, 0.0F);
/* 443 */     this.RgtHammerHeadSupport.addChild(this.RgtHammerHead);
/*     */     
/* 445 */     this.RgtSpike = new ModelRenderer(this, 52, 118);
/* 446 */     this.RgtSpike.addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3);
/* 447 */     this.RgtSpike.setRotationPoint(0.0F, 0.0F, -5.0F);
/* 448 */     this.RgtWeaponTip.addChild(this.RgtSpike);
/*     */     
/* 450 */     this.RgtSpike1 = new ModelRenderer(this, 52, 118);
/* 451 */     this.RgtSpike1.addBox(-3.0F, -1.0F, -1.0F, 3, 2, 2);
/* 452 */     this.RgtSpike1.setRotationPoint(-2.5F, 0.0F, -3.0F);
/* 453 */     this.RgtWeaponTip.addChild(this.RgtSpike1);
/*     */     
/* 455 */     this.RgtSpike2 = new ModelRenderer(this, 52, 118);
/* 456 */     this.RgtSpike2.addBox(3.0F, -1.0F, -1.0F, 3, 2, 2);
/* 457 */     this.RgtSpike2.setRotationPoint(-0.5F, 0.0F, -3.0F);
/* 458 */     this.RgtWeaponTip.addChild(this.RgtSpike2);
/*     */     
/* 460 */     this.RgtSpike3 = new ModelRenderer(this, 52, 118);
/* 461 */     this.RgtSpike3.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2);
/* 462 */     this.RgtSpike3.setRotationPoint(0.0F, 2.5F, -3.0F);
/* 463 */     this.RgtWeaponTip.addChild(this.RgtSpike3);
/*     */     
/* 465 */     this.RgtSpike4 = new ModelRenderer(this, 52, 118);
/* 466 */     this.RgtSpike4.addBox(-1.0F, -3.0F, -1.0F, 2, 3, 2);
/* 467 */     this.RgtSpike4.setRotationPoint(0.0F, -2.5F, -3.0F);
/* 468 */     this.RgtWeaponTip.addChild(this.RgtSpike4);
/*     */     
/* 470 */     this.Head3RgtEar = new ModelRenderer(this, 110, 24);
/* 471 */     this.Head3RgtEar.addBox(-8.0F, -9.0F, -1.0F, 3, 5, 2);
/* 472 */     this.Head3RgtEar.setRotationPoint(7.0F, -13.0F, 0.0F);
/*     */     
/* 474 */     this.Head3LftEar = new ModelRenderer(this, 100, 24);
/* 475 */     this.Head3LftEar.addBox(5.0F, -9.0F, -1.0F, 3, 5, 2);
/* 476 */     this.Head3LftEar.setRotationPoint(7.0F, -13.0F, 0.0F);
/*     */     
/* 478 */     this.Head3Eyelid = new ModelRenderer(this, 46, 3);
/* 479 */     this.Head3Eyelid.addBox(-3.0F, -8.0F, -4.5F, 6, 2, 1);
/* 480 */     this.Head3Eyelid.setRotationPoint(7.0F, -13.0F, 0.0F);
/* 481 */     setRotation(this.Head3Eyelid, 0.2617994F, 0.0F, 0.0F);
/*     */     
/* 483 */     this.Head3Nose = new ModelRenderer(this, 60, 9);
/* 484 */     this.Head3Nose.addBox(-1.5F, -8.5F, -3.5F, 3, 2, 1);
/* 485 */     this.Head3Nose.setRotationPoint(7.0F, -13.0F, 0.0F);
/* 486 */     setRotation(this.Head3Nose, 0.4886922F, 0.0F, 0.0F);
/*     */     
/* 488 */     this.Head3 = new ModelRenderer(this, 42, 83);
/* 489 */     this.Head3.addBox(-5.0F, -12.0F, -6.0F, 10, 12, 12);
/* 490 */     this.Head3.setRotationPoint(7.0F, -13.0F, 0.0F);
/*     */     
/* 492 */     this.Head3Brow = new ModelRenderer(this, 46, 0);
/* 493 */     this.Head3Brow.addBox(-3.0F, -9.0F, -8.5F, 6, 2, 1);
/* 494 */     this.Head3Brow.setRotationPoint(7.0F, -13.0F, 0.0F);
/* 495 */     setRotation(this.Head3Brow, -0.2617994F, 0.0F, 0.0F);
/*     */     
/* 497 */     this.Head3Hair = new ModelRenderer(this, 80, 118);
/* 498 */     this.Head3Hair.addBox(-2.0F, -17.0F, -5.0F, 4, 6, 4);
/* 499 */     this.Head3Hair.setRotationPoint(7.0F, -13.0F, 0.0F);
/* 500 */     setRotation(this.Head3Hair, -0.6108652F, 0.0F, 0.0F);
/*     */     
/* 502 */     this.Head3Lip = new ModelRenderer(this, 22, 68);
/* 503 */     this.Head3Lip.addBox(-4.0F, -4.0F, -7.0F, 8, 2, 2);
/* 504 */     this.Head3Lip.setRotationPoint(7.0F, -13.0F, 0.0F);
/* 505 */     setRotation(this.Head3Lip, 0.1745329F, 0.0F, 0.0F);
/*     */     
/* 507 */     this.Head3RgtTusk = new ModelRenderer(this, 83, 34);
/* 508 */     this.Head3RgtTusk.addBox(-3.5F, -6.0F, -6.5F, 1, 2, 1);
/* 509 */     this.Head3RgtTusk.setRotationPoint(7.0F, -13.0F, 0.0F);
/*     */     
/* 511 */     setRotation(this.Head3RgtTusk, 0.1745329F, 0.0F, 0.0F);
/* 512 */     this.Head3RgtTooth = new ModelRenderer(this, 87, 34);
/* 513 */     this.Head3RgtTooth.addBox(-1.5F, -5.0F, -6.5F, 1, 1, 1);
/* 514 */     this.Head3RgtTooth.setRotationPoint(7.0F, -13.0F, 0.0F);
/* 515 */     setRotation(this.Head3RgtTooth, 0.1745329F, 0.0F, 0.0F);
/*     */     
/* 517 */     this.Head3LftTooth = new ModelRenderer(this, 96, 34);
/* 518 */     this.Head3LftTooth.addBox(0.5F, -5.0F, -6.5F, 1, 1, 1);
/* 519 */     this.Head3LftTooth.setRotationPoint(7.0F, -13.0F, 0.0F);
/* 520 */     setRotation(this.Head3LftTooth, 0.1745329F, 0.0F, 0.0F);
/*     */     
/* 522 */     this.Head3LftTusk = new ModelRenderer(this, 100, 34);
/* 523 */     this.Head3LftTusk.addBox(2.5F, -6.0F, -6.5F, 1, 2, 1);
/* 524 */     this.Head3LftTusk.setRotationPoint(7.0F, -13.0F, 0.0F);
/* 525 */     setRotation(this.Head3LftTusk, 0.1745329F, 0.0F, 0.0F);
/*     */     
/* 527 */     this.Head3RingHole = new ModelRenderer(this, 26, 50);
/* 528 */     this.Head3RingHole.addBox(6.0F, -5.0F, -1.0F, 1, 2, 2);
/* 529 */     this.Head3RingHole.setRotationPoint(7.0F, -13.0F, 0.0F);
/*     */     
/* 531 */     this.Head3Ring = new ModelRenderer(this, 32, 58);
/* 532 */     this.Head3Ring.addBox(6.0F, -6.0F, -2.0F, 1, 4, 4);
/* 533 */     this.Head3Ring.setRotationPoint(7.0F, -13.0F, 0.0F);
/*     */     
/* 535 */     this.Head2Chin = new ModelRenderer(this, 21, 24);
/* 536 */     this.Head2Chin.addBox(-3.0F, -5.0F, -8.0F, 6, 3, 3);
/* 537 */     this.Head2Chin.setRotationPoint(-7.0F, -13.0F, 0.0F);
/* 538 */     setRotation(this.Head2Chin, 0.2617994F, 0.0F, 0.0F);
/*     */     
/* 540 */     this.Head2 = new ModelRenderer(this, 0, 0);
/* 541 */     this.Head2.addBox(-5.0F, -12.0F, -6.0F, 10, 12, 12);
/* 542 */     this.Head2.setRotationPoint(-7.0F, -13.0F, 0.0F);
/*     */     
/* 544 */     this.Head2Lip = new ModelRenderer(this, 0, 24);
/* 545 */     this.Head2Lip.addBox(-4.0F, -5.0F, -8.0F, 8, 2, 2);
/* 546 */     this.Head2Lip.setRotationPoint(-7.0F, -13.0F, 0.0F);
/*     */     
/* 548 */     this.Head2LftTusk = new ModelRenderer(this, 46, 28);
/* 549 */     this.Head2LftTusk.addBox(2.5F, -8.0F, -6.5F, 1, 2, 1);
/* 550 */     this.Head2LftTusk.setRotationPoint(-7.0F, -13.0F, 0.0F);
/* 551 */     setRotation(this.Head2LftTusk, 0.1745329F, 0.0F, 0.0F);
/*     */     
/* 553 */     this.Head2RgtTusk = new ModelRenderer(this, 39, 28);
/* 554 */     this.Head2RgtTusk.addBox(-3.5F, -8.0F, -6.5F, 1, 2, 1);
/* 555 */     this.Head2RgtTusk.setRotationPoint(-7.0F, -13.0F, 0.0F);
/* 556 */     setRotation(this.Head2RgtTusk, 0.1745329F, 0.0F, 0.0F);
/*     */     
/* 558 */     this.Head2Nose = new ModelRenderer(this, 116, 0);
/* 559 */     this.Head2Nose.addBox(-2.0F, -7.0F, -7.0F, 4, 2, 2);
/* 560 */     this.Head2Nose.setRotationPoint(-7.0F, -13.0F, 0.0F);
/* 561 */     setRotation(this.Head2Nose, 0.0872665F, 0.0F, 0.0F);
/*     */     
/* 563 */     this.Head2NoseBridge = new ModelRenderer(this, 116, 4);
/* 564 */     this.Head2NoseBridge.addBox(-1.0F, -7.0F, -8.0F, 2, 2, 1);
/* 565 */     this.Head2NoseBridge.setRotationPoint(-7.0F, -13.0F, 0.0F);
/* 566 */     setRotation(this.Head2NoseBridge, -0.1745329F, 0.0F, 0.0F);
/*     */     
/* 568 */     this.Head2Brow = new ModelRenderer(this, 80, 24);
/* 569 */     this.Head2Brow.addBox(-4.0F, -10.5F, -8.0F, 8, 3, 2);
/* 570 */     this.Head2Brow.setRotationPoint(-7.0F, -13.0F, 0.0F);
/* 571 */     setRotation(this.Head2Brow, -0.0872665F, 0.0F, 0.0F);
/*     */     
/* 573 */     this.Head2RgtHorn = new ModelRenderer(this, 24, 30);
/* 574 */     this.Head2RgtHorn.addBox(-4.0F, -8.0F, -15.0F, 2, 2, 5);
/* 575 */     this.Head2RgtHorn.setRotationPoint(-7.0F, -13.0F, 0.0F);
/* 576 */     setRotation(this.Head2RgtHorn, -0.5235988F, 0.0F, 0.0F);
/*     */     
/* 578 */     this.Head2LftHorn = new ModelRenderer(this, 24, 30);
/* 579 */     this.Head2LftHorn.addBox(2.0F, -8.0F, -15.0F, 2, 2, 5);
/* 580 */     this.Head2LftHorn.setRotationPoint(-7.0F, -13.0F, 0.0F);
/* 581 */     setRotation(this.Head2LftHorn, -0.5235988F, 0.0F, 0.0F);
/*     */     
/* 583 */     this.Head2DiamondHorn = new ModelRenderer(this, 120, 46);
/* 584 */     this.Head2DiamondHorn.addBox(-1.0F, -17.0F, -6.0F, 2, 6, 2);
/* 585 */     this.Head2DiamondHorn.setRotationPoint(-7.0F, -13.0F, 0.0F);
/* 586 */     setRotation(this.Head2DiamondHorn, 0.0872665F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 592 */     MoCEntityOgre entityogre = (MoCEntityOgre)entity;
/* 593 */     this.type = entityogre.getType();
/*     */ 
/*     */     
/* 596 */     this.attackCounter = entityogre.attackCounter;
/* 597 */     this.headMoving = entityogre.getMovingHead();
/* 598 */     this.armToAnimate = entityogre.armToAnimate;
/*     */     
/* 600 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/*     */     
/* 602 */     if (this.type == 1) {
/* 603 */       this.Head.render(f5);
/* 604 */       this.Brow.render(f5);
/* 605 */       this.NoseBridge.render(f5);
/* 606 */       this.Nose.render(f5);
/* 607 */       this.RgtTusk.render(f5);
/* 608 */       this.RgtTooth.render(f5);
/* 609 */       this.LftTooth.render(f5);
/* 610 */       this.LftTusk.render(f5);
/* 611 */       this.Lip.render(f5);
/* 612 */       this.RgtEar.render(f5);
/* 613 */       this.RgtRing.render(f5);
/* 614 */       this.RgtRingHole.render(f5);
/* 615 */       this.LftEar.render(f5);
/* 616 */       this.LftRing.render(f5);
/* 617 */       this.LftRingHole.render(f5);
/* 618 */       this.HairRope.render(f5);
/* 619 */       this.Hair1.render(f5);
/* 620 */       this.Hair2.render(f5);
/* 621 */       this.Hair3.render(f5);
/* 622 */       this.DiamondHorn.render(f5);
/* 623 */       this.RgtHorn.render(f5);
/* 624 */       this.RgtHornTip.render(f5);
/* 625 */       this.LftHorn.render(f5);
/* 626 */       this.LftHornTip.render(f5);
/*     */       
/* 628 */       this.LftWeaponRoot.isHidden = true;
/*     */     } else {
/* 630 */       this.Head3RgtEar.render(f5);
/* 631 */       this.Head3LftEar.render(f5);
/* 632 */       this.Head3Eyelid.render(f5);
/* 633 */       this.Head3Nose.render(f5);
/* 634 */       this.Head3.render(f5);
/* 635 */       this.Head3Brow.render(f5);
/* 636 */       this.Head3Hair.render(f5);
/* 637 */       this.Head3Lip.render(f5);
/* 638 */       this.Head3RgtTusk.render(f5);
/* 639 */       this.Head3RgtTooth.render(f5);
/* 640 */       this.Head3LftTooth.render(f5);
/* 641 */       this.Head3LftTusk.render(f5);
/* 642 */       this.Head3RingHole.render(f5);
/* 643 */       this.Head3Ring.render(f5);
/*     */       
/* 645 */       this.Head2Chin.render(f5);
/* 646 */       this.Head2.render(f5);
/* 647 */       this.Head2Lip.render(f5);
/* 648 */       this.Head2LftTusk.render(f5);
/* 649 */       this.Head2RgtTusk.render(f5);
/* 650 */       this.Head2Nose.render(f5);
/* 651 */       this.Head2NoseBridge.render(f5);
/* 652 */       this.Head2Brow.render(f5);
/* 653 */       this.Head2RgtHorn.render(f5);
/* 654 */       this.Head2LftHorn.render(f5);
/* 655 */       this.Head2DiamondHorn.render(f5);
/*     */       
/* 657 */       this.LftWeaponRoot.isHidden = false;
/*     */     } 
/*     */     
/* 660 */     this.NeckRest.render(f5);
/* 661 */     this.Chest.render(f5);
/* 662 */     this.Stomach.render(f5);
/* 663 */     this.ButtCover.render(f5);
/* 664 */     this.LoinCloth.render(f5);
/* 665 */     this.RgtThigh.render(f5);
/* 666 */     this.LftThigh.render(f5);
/* 667 */     this.RgtShoulder.render(f5);
/* 668 */     this.LftShoulder.render(f5);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 673 */     model.rotateAngleX = x;
/* 674 */     model.rotateAngleY = y;
/* 675 */     model.rotateAngleZ = z;
/*     */   }
/*     */   
/*     */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/* 679 */     float hRotY = f3 / 57.29578F;
/* 680 */     float hRotX = f4 / 57.29578F;
/*     */     
/* 682 */     float RLegXRot = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
/* 683 */     float LLegXRot = MathHelper.cos(f * 0.6662F) * 0.8F * f1;
/* 684 */     float ClothRot = MathHelper.cos(f * 0.9F) * 0.6F * f1;
/*     */     
/* 686 */     float RLegXRotB = RLegXRot;
/* 687 */     float LLegXRotB = LLegXRot;
/*     */     
/* 689 */     this.RgtThigh.rotateAngleX = RLegXRot;
/* 690 */     this.LftThigh.rotateAngleX = LLegXRot;
/*     */     
/* 692 */     float RLegXRot2 = MathHelper.cos((f + 0.1F) * 0.6662F + 3.141593F) * 0.8F * f1;
/* 693 */     float LLegXRot2 = MathHelper.cos((f + 0.1F) * 0.6662F) * 0.8F * f1;
/* 694 */     if (f1 > 0.15F) {
/* 695 */       if (RLegXRot > RLegXRot2)
/*     */       {
/* 697 */         RLegXRotB = RLegXRot + 0.43633232F;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 704 */       if (LLegXRot > LLegXRot2)
/*     */       {
/* 706 */         LLegXRotB = LLegXRot + 0.43633232F;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 714 */     this.LftLeg.rotateAngleX = LLegXRotB;
/* 715 */     this.RgtLeg.rotateAngleX = RLegXRotB;
/* 716 */     this.LoinCloth.rotateAngleX = ClothRot;
/* 717 */     this.ButtCover.rotateAngleX = ClothRot;
/*     */     
/* 719 */     float armMov = -(MathHelper.cos(this.attackCounter * 0.18F) * 3.0F);
/*     */ 
/*     */ 
/*     */     
/* 723 */     if (this.armToAnimate == 1 || this.armToAnimate == 3) {
/* 724 */       this.LftShoulder.rotateAngleX = armMov;
/* 725 */       this.LftHand.rotateAngleX = -45.0F / this.radianF;
/*     */     } else {
/*     */       
/* 728 */       this.LftShoulder.rotateAngleZ = MathHelper.cos(f2 * 0.09F) * 0.05F - 0.05F;
/* 729 */       this.LftShoulder.rotateAngleX = RLegXRot;
/* 730 */       this.LftHand.rotateAngleX = 0.0F;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 735 */     if (this.armToAnimate == 2 || this.armToAnimate == 3) {
/* 736 */       this.RgtShoulder.rotateAngleX = armMov;
/* 737 */       this.RgtHand.rotateAngleX = -45.0F / this.radianF;
/*     */     } else {
/*     */       
/* 740 */       this.RgtShoulder.rotateAngleZ = -(MathHelper.cos(f2 * 0.09F) * 0.05F) + 0.05F;
/* 741 */       this.RgtShoulder.rotateAngleX = LLegXRot;
/* 742 */       this.RgtHand.rotateAngleX = 0.0F;
/*     */     } 
/*     */     
/* 745 */     if (this.headMoving == 2) {
/* 746 */       this.Head2.rotateAngleX = hRotX;
/* 747 */       this.Head2.rotateAngleY = hRotY;
/*     */     } 
/*     */     
/* 750 */     if (this.headMoving == 3) {
/* 751 */       this.Head3.rotateAngleX = hRotX;
/* 752 */       this.Head3.rotateAngleY = hRotY;
/*     */     } 
/*     */     
/* 755 */     if (this.type == 1 || this.type == 3 || this.type == 5) {
/* 756 */       this.Head.rotateAngleX = hRotX;
/* 757 */       this.Head.rotateAngleY = hRotY;
/*     */       
/* 759 */       this.Brow.rotateAngleX = this.Head.rotateAngleX;
/* 760 */       this.NoseBridge.rotateAngleX = this.Head.rotateAngleX;
/* 761 */       this.Nose.rotateAngleX = this.Head.rotateAngleX;
/* 762 */       this.RgtTusk.rotateAngleX = this.Head.rotateAngleX;
/* 763 */       this.RgtTooth.rotateAngleX = this.Head.rotateAngleX;
/* 764 */       this.LftTooth.rotateAngleX = this.Head.rotateAngleX;
/* 765 */       this.LftTusk.rotateAngleX = this.Head.rotateAngleX;
/* 766 */       this.Lip.rotateAngleX = this.Head.rotateAngleX;
/* 767 */       this.RgtEar.rotateAngleX = this.Head.rotateAngleX;
/* 768 */       this.RgtRing.rotateAngleX = this.Head.rotateAngleX;
/* 769 */       this.RgtRingHole.rotateAngleX = this.Head.rotateAngleX;
/* 770 */       this.LftEar.rotateAngleX = this.Head.rotateAngleX;
/* 771 */       this.LftRing.rotateAngleX = this.Head.rotateAngleX;
/* 772 */       this.LftRingHole.rotateAngleX = this.Head.rotateAngleX;
/* 773 */       this.HairRope.rotateAngleX = 0.6108652F + this.Head.rotateAngleX;
/* 774 */       this.Hair1.rotateAngleX = 0.6108652F + this.Head.rotateAngleX;
/* 775 */       this.Hair2.rotateAngleX = 0.2617994F + this.Head.rotateAngleX;
/* 776 */       this.Hair3.rotateAngleX = this.Head.rotateAngleX;
/* 777 */       this.DiamondHorn.rotateAngleX = 0.0872665F + this.Head.rotateAngleX;
/* 778 */       this.RgtHorn.rotateAngleX = this.Head.rotateAngleX;
/* 779 */       this.RgtHornTip.rotateAngleX = this.Head.rotateAngleX;
/* 780 */       this.LftHorn.rotateAngleX = this.Head.rotateAngleX;
/* 781 */       this.LftHornTip.rotateAngleX = this.Head.rotateAngleX;
/*     */       
/* 783 */       this.Brow.rotateAngleY = this.Head.rotateAngleY;
/* 784 */       this.NoseBridge.rotateAngleY = this.Head.rotateAngleY;
/* 785 */       this.Nose.rotateAngleY = this.Head.rotateAngleY;
/* 786 */       this.RgtTusk.rotateAngleY = this.Head.rotateAngleY;
/* 787 */       this.RgtTooth.rotateAngleY = this.Head.rotateAngleY;
/* 788 */       this.LftTooth.rotateAngleY = this.Head.rotateAngleY;
/* 789 */       this.LftTusk.rotateAngleY = this.Head.rotateAngleY;
/* 790 */       this.Lip.rotateAngleY = this.Head.rotateAngleY;
/* 791 */       this.RgtEar.rotateAngleY = this.Head.rotateAngleY;
/* 792 */       this.RgtRing.rotateAngleY = this.Head.rotateAngleY;
/* 793 */       this.RgtRingHole.rotateAngleY = this.Head.rotateAngleY;
/* 794 */       this.LftEar.rotateAngleY = this.Head.rotateAngleY;
/* 795 */       this.LftRing.rotateAngleY = this.Head.rotateAngleY;
/* 796 */       this.LftRingHole.rotateAngleY = this.Head.rotateAngleY;
/* 797 */       this.HairRope.rotateAngleY = this.Head.rotateAngleY;
/* 798 */       this.Hair1.rotateAngleY = this.Head.rotateAngleY;
/* 799 */       this.Hair2.rotateAngleY = this.Head.rotateAngleY;
/* 800 */       this.Hair3.rotateAngleY = this.Head.rotateAngleY;
/* 801 */       this.DiamondHorn.rotateAngleY = this.Head.rotateAngleY;
/* 802 */       this.RgtHorn.rotateAngleY = this.Head.rotateAngleY;
/* 803 */       this.RgtHornTip.rotateAngleY = this.Head.rotateAngleY;
/* 804 */       this.LftHorn.rotateAngleY = this.Head.rotateAngleY;
/* 805 */       this.LftHornTip.rotateAngleY = this.Head.rotateAngleY;
/*     */     } else {
/*     */       
/* 808 */       this.Head3RgtEar.rotateAngleX = this.Head3.rotateAngleX;
/* 809 */       this.Head3LftEar.rotateAngleX = this.Head3.rotateAngleX;
/* 810 */       this.Head3Eyelid.rotateAngleX = 0.2617994F + this.Head3.rotateAngleX;
/* 811 */       this.Head3Nose.rotateAngleX = 0.4886922F + this.Head3.rotateAngleX;
/* 812 */       this.Head3Brow.rotateAngleX = -0.2617994F + this.Head3.rotateAngleX;
/* 813 */       this.Head3Hair.rotateAngleX = -0.6108652F + this.Head3.rotateAngleX;
/* 814 */       this.Head3Lip.rotateAngleX = 0.1745329F + this.Head3.rotateAngleX;
/* 815 */       this.Head3RgtTusk.rotateAngleX = 0.1745329F + this.Head3.rotateAngleX;
/* 816 */       this.Head3RgtTooth.rotateAngleX = 0.1745329F + this.Head3.rotateAngleX;
/* 817 */       this.Head3LftTooth.rotateAngleX = 0.1745329F + this.Head3.rotateAngleX;
/* 818 */       this.Head3LftTusk.rotateAngleX = 0.1745329F + this.Head3.rotateAngleX;
/* 819 */       this.Head3RingHole.rotateAngleX = this.Head3.rotateAngleX;
/* 820 */       this.Head3Ring.rotateAngleX = this.Head3.rotateAngleX;
/*     */       
/* 822 */       this.Head3RgtEar.rotateAngleY = this.Head3.rotateAngleY;
/* 823 */       this.Head3LftEar.rotateAngleY = this.Head3.rotateAngleY;
/* 824 */       this.Head3Eyelid.rotateAngleY = this.Head3.rotateAngleY;
/* 825 */       this.Head3Nose.rotateAngleY = this.Head3.rotateAngleY;
/* 826 */       this.Head3Brow.rotateAngleY = this.Head3.rotateAngleY;
/* 827 */       this.Head3Hair.rotateAngleY = this.Head3.rotateAngleY;
/* 828 */       this.Head3Lip.rotateAngleY = this.Head3.rotateAngleY;
/* 829 */       this.Head3RgtTusk.rotateAngleY = this.Head3.rotateAngleY;
/* 830 */       this.Head3RgtTooth.rotateAngleY = this.Head3.rotateAngleY;
/* 831 */       this.Head3LftTooth.rotateAngleY = this.Head3.rotateAngleY;
/* 832 */       this.Head3LftTusk.rotateAngleY = this.Head3.rotateAngleY;
/* 833 */       this.Head3RingHole.rotateAngleY = this.Head3.rotateAngleY;
/* 834 */       this.Head3Ring.rotateAngleY = this.Head3.rotateAngleY;
/*     */       
/* 836 */       this.Head2Chin.rotateAngleX = 0.2617994F + this.Head2.rotateAngleX;
/* 837 */       this.Head2Lip.rotateAngleX = this.Head2.rotateAngleX;
/* 838 */       this.Head2LftTusk.rotateAngleX = 0.1745329F + this.Head2.rotateAngleX;
/* 839 */       this.Head2RgtTusk.rotateAngleX = 0.1745329F + this.Head2.rotateAngleX;
/* 840 */       this.Head2Nose.rotateAngleX = 0.0872665F + this.Head2.rotateAngleX;
/* 841 */       this.Head2NoseBridge.rotateAngleX = -0.1745329F + this.Head2.rotateAngleX;
/* 842 */       this.Head2Brow.rotateAngleX = -0.0872665F + this.Head2.rotateAngleX;
/* 843 */       this.Head2RgtHorn.rotateAngleX = -0.5235988F + this.Head2.rotateAngleX;
/* 844 */       this.Head2LftHorn.rotateAngleX = -0.5235988F + this.Head2.rotateAngleX;
/* 845 */       this.Head2DiamondHorn.rotateAngleX = 0.0872665F + this.Head2.rotateAngleX;
/*     */       
/* 847 */       this.Head2Chin.rotateAngleY = this.Head2.rotateAngleY;
/* 848 */       this.Head2Lip.rotateAngleY = this.Head2.rotateAngleY;
/* 849 */       this.Head2LftTusk.rotateAngleY = this.Head2.rotateAngleY;
/* 850 */       this.Head2RgtTusk.rotateAngleY = this.Head2.rotateAngleY;
/* 851 */       this.Head2Nose.rotateAngleY = this.Head2.rotateAngleY;
/* 852 */       this.Head2NoseBridge.rotateAngleY = this.Head2.rotateAngleY;
/* 853 */       this.Head2Brow.rotateAngleY = this.Head2.rotateAngleY;
/* 854 */       this.Head2RgtHorn.rotateAngleY = this.Head2.rotateAngleY;
/* 855 */       this.Head2LftHorn.rotateAngleY = this.Head2.rotateAngleY;
/* 856 */       this.Head2DiamondHorn.rotateAngleY = this.Head2.rotateAngleY;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelOgre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */