/*      */ package drzhark.mocreatures.client.model;
/*      */ 
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityHorse;
/*      */ import net.minecraft.client.model.ModelBase;
/*      */ import net.minecraft.client.model.ModelRenderer;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.util.math.MathHelper;
/*      */ import net.minecraftforge.fml.relauncher.Side;
/*      */ import net.minecraftforge.fml.relauncher.SideOnly;
/*      */ import org.lwjgl.opengl.GL11;
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
/*      */ @SideOnly(Side.CLIENT)
/*      */ public class MoCModelNewHorse
/*      */   extends ModelBase
/*      */ {
/*      */   ModelRenderer Head;
/*      */   ModelRenderer UMouth;
/*      */   ModelRenderer LMouth;
/*      */   ModelRenderer UMouth2;
/*      */   ModelRenderer LMouth2;
/*      */   ModelRenderer Unicorn;
/*      */   ModelRenderer Ear1;
/*      */   ModelRenderer Ear2;
/*      */   ModelRenderer MuleEarL;
/*      */   ModelRenderer MuleEarR;
/*      */   ModelRenderer Neck;
/*      */   ModelRenderer HeadSaddle;
/*      */   ModelRenderer Mane;
/*      */   ModelRenderer Body;
/*      */   ModelRenderer TailA;
/*      */   ModelRenderer TailB;
/*      */   ModelRenderer TailC;
/*      */   ModelRenderer Leg1A;
/*      */   ModelRenderer Leg1B;
/*      */   ModelRenderer Leg1C;
/*      */   ModelRenderer Leg2A;
/*      */   ModelRenderer Leg2B;
/*      */   ModelRenderer Leg2C;
/*      */   ModelRenderer Leg3A;
/*      */   ModelRenderer Leg3B;
/*      */   ModelRenderer Leg3C;
/*      */   ModelRenderer Leg4A;
/*      */   ModelRenderer Leg4B;
/*      */   ModelRenderer Leg4C;
/*      */   ModelRenderer Bag1;
/*      */   ModelRenderer Bag2;
/*      */   ModelRenderer Saddle;
/*      */   ModelRenderer SaddleB;
/*      */   ModelRenderer SaddleC;
/*      */   ModelRenderer SaddleL;
/*      */   ModelRenderer SaddleL2;
/*      */   ModelRenderer SaddleR;
/*      */   ModelRenderer SaddleR2;
/*      */   ModelRenderer SaddleMouthL;
/*      */   ModelRenderer SaddleMouthR;
/*      */   ModelRenderer SaddleMouthLine;
/*      */   ModelRenderer SaddleMouthLineR;
/*      */   ModelRenderer MidWing;
/*      */   ModelRenderer InnerWing;
/*      */   ModelRenderer OuterWing;
/*      */   ModelRenderer InnerWingR;
/*      */   ModelRenderer MidWingR;
/*      */   ModelRenderer OuterWingR;
/*      */   ModelRenderer ButterflyL;
/*      */   ModelRenderer ButterflyR;
/*      */   
/*      */   public MoCModelNewHorse() {
/*  113 */     this.textureWidth = 128;
/*  114 */     this.textureHeight = 128;
/*      */     
/*  116 */     this.Body = new ModelRenderer(this, 0, 34);
/*  117 */     this.Body.addBox(-5.0F, -8.0F, -19.0F, 10, 10, 24);
/*  118 */     this.Body.setRotationPoint(0.0F, 11.0F, 9.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  126 */     this.TailA = new ModelRenderer(this, 44, 0);
/*  127 */     this.TailA.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3);
/*  128 */     this.TailA.setRotationPoint(0.0F, 3.0F, 14.0F);
/*  129 */     setRotation(this.TailA, -1.134464F, 0.0F, 0.0F);
/*      */     
/*  131 */     this.TailB = new ModelRenderer(this, 38, 7);
/*  132 */     this.TailB.addBox(-1.5F, -2.0F, 3.0F, 3, 4, 7);
/*  133 */     this.TailB.setRotationPoint(0.0F, 3.0F, 14.0F);
/*  134 */     setRotation(this.TailB, -1.134464F, 0.0F, 0.0F);
/*      */     
/*  136 */     this.TailC = new ModelRenderer(this, 24, 3);
/*  137 */     this.TailC.addBox(-1.5F, -4.5F, 9.0F, 3, 4, 7);
/*  138 */     this.TailC.setRotationPoint(0.0F, 3.0F, 14.0F);
/*  139 */     setRotation(this.TailC, -1.40215F, 0.0F, 0.0F);
/*      */     
/*  141 */     this.Leg1A = new ModelRenderer(this, 78, 29);
/*  142 */     this.Leg1A.addBox(-2.5F, -2.0F, -2.5F, 4, 9, 5);
/*  143 */     this.Leg1A.setRotationPoint(4.0F, 9.0F, 11.0F);
/*      */     
/*  145 */     this.Leg1B = new ModelRenderer(this, 78, 43);
/*  146 */     this.Leg1B.addBox(-2.0F, 0.0F, -1.5F, 3, 5, 3);
/*  147 */     this.Leg1B.setRotationPoint(4.0F, 16.0F, 11.0F);
/*      */     
/*  149 */     this.Leg1C = new ModelRenderer(this, 78, 51);
/*  150 */     this.Leg1C.addBox(-2.5F, 5.1F, -2.0F, 4, 3, 4);
/*  151 */     this.Leg1C.setRotationPoint(4.0F, 16.0F, 11.0F);
/*      */     
/*  153 */     this.Leg2A = new ModelRenderer(this, 96, 29);
/*  154 */     this.Leg2A.addBox(-1.5F, -2.0F, -2.5F, 4, 9, 5);
/*  155 */     this.Leg2A.setRotationPoint(-4.0F, 9.0F, 11.0F);
/*      */     
/*  157 */     this.Leg2B = new ModelRenderer(this, 96, 43);
/*  158 */     this.Leg2B.addBox(-1.0F, 0.0F, -1.5F, 3, 5, 3);
/*  159 */     this.Leg2B.setRotationPoint(-4.0F, 16.0F, 11.0F);
/*      */     
/*  161 */     this.Leg2C = new ModelRenderer(this, 96, 51);
/*  162 */     this.Leg2C.addBox(-1.5F, 5.1F, -2.0F, 4, 3, 4);
/*  163 */     this.Leg2C.setRotationPoint(-4.0F, 16.0F, 11.0F);
/*      */     
/*  165 */     this.Leg3A = new ModelRenderer(this, 44, 29);
/*  166 */     this.Leg3A.addBox(-1.9F, -1.0F, -2.1F, 3, 8, 4);
/*  167 */     this.Leg3A.setRotationPoint(4.0F, 9.0F, -8.0F);
/*      */     
/*  169 */     this.Leg3B = new ModelRenderer(this, 44, 41);
/*  170 */     this.Leg3B.addBox(-1.9F, 0.0F, -1.6F, 3, 5, 3);
/*  171 */     this.Leg3B.setRotationPoint(4.0F, 16.0F, -8.0F);
/*      */     
/*  173 */     this.Leg3C = new ModelRenderer(this, 44, 51);
/*  174 */     this.Leg3C.addBox(-2.4F, 5.1F, -2.1F, 4, 3, 4);
/*  175 */     this.Leg3C.setRotationPoint(4.0F, 16.0F, -8.0F);
/*      */     
/*  177 */     this.Leg4A = new ModelRenderer(this, 60, 29);
/*  178 */     this.Leg4A.addBox(-1.1F, -1.0F, -2.1F, 3, 8, 4);
/*  179 */     this.Leg4A.setRotationPoint(-4.0F, 9.0F, -8.0F);
/*      */     
/*  181 */     this.Leg4B = new ModelRenderer(this, 60, 41);
/*  182 */     this.Leg4B.addBox(-1.1F, 0.0F, -1.6F, 3, 5, 3);
/*  183 */     this.Leg4B.setRotationPoint(-4.0F, 16.0F, -8.0F);
/*      */     
/*  185 */     this.Leg4C = new ModelRenderer(this, 60, 51);
/*  186 */     this.Leg4C.addBox(-1.6F, 5.1F, -2.1F, 4, 3, 4);
/*  187 */     this.Leg4C.setRotationPoint(-4.0F, 16.0F, -8.0F);
/*      */     
/*  189 */     this.Head = new ModelRenderer(this, 0, 0);
/*  190 */     this.Head.addBox(-2.5F, -10.0F, -1.5F, 5, 5, 7);
/*  191 */     this.Head.setRotationPoint(0.0F, 4.0F, -10.0F);
/*  192 */     setRotation(this.Head, 0.5235988F, 0.0F, 0.0F);
/*      */     
/*  194 */     this.UMouth = new ModelRenderer(this, 24, 18);
/*  195 */     this.UMouth.addBox(-2.0F, -10.0F, -7.0F, 4, 3, 6);
/*  196 */     this.UMouth.setRotationPoint(0.0F, 4.0F, -10.0F);
/*  197 */     setRotation(this.UMouth, 0.5235988F, 0.0F, 0.0F);
/*      */     
/*  199 */     this.LMouth = new ModelRenderer(this, 24, 27);
/*  200 */     this.LMouth.addBox(-2.0F, -7.0F, -6.5F, 4, 2, 5);
/*  201 */     this.LMouth.setRotationPoint(0.0F, 4.0F, -10.0F);
/*  202 */     setRotation(this.LMouth, 0.5235988F, 0.0F, 0.0F);
/*      */     
/*  204 */     this.UMouth2 = new ModelRenderer(this, 24, 18);
/*  205 */     this.UMouth2.addBox(-2.0F, -10.0F, -8.0F, 4, 3, 6);
/*  206 */     this.UMouth2.setRotationPoint(0.0F, 4.0F, -10.0F);
/*  207 */     setRotation(this.UMouth2, 0.4363323F, 0.0F, 0.0F);
/*      */     
/*  209 */     this.LMouth2 = new ModelRenderer(this, 24, 27);
/*  210 */     this.LMouth2.addBox(-2.0F, -7.0F, -5.5F, 4, 2, 5);
/*  211 */     this.LMouth2.setRotationPoint(0.0F, 4.0F, -10.0F);
/*  212 */     setRotation(this.LMouth2, 0.7853982F, 0.0F, 0.0F);
/*      */     
/*  214 */     this.Unicorn = new ModelRenderer(this, 24, 0);
/*  215 */     this.Unicorn.addBox(-0.5F, -18.0F, 2.0F, 1, 8, 1);
/*  216 */     this.Unicorn.setRotationPoint(0.0F, 4.0F, -10.0F);
/*  217 */     setRotation(this.Unicorn, 0.5235988F, 0.0F, 0.0F);
/*      */     
/*  219 */     this.Ear1 = new ModelRenderer(this, 0, 0);
/*  220 */     this.Ear1.addBox(0.45F, -12.0F, 4.0F, 2, 3, 1);
/*  221 */     this.Ear1.setRotationPoint(0.0F, 4.0F, -10.0F);
/*  222 */     setRotation(this.Ear1, 0.5235988F, 0.0F, 0.0F);
/*      */     
/*  224 */     this.Ear2 = new ModelRenderer(this, 0, 0);
/*  225 */     this.Ear2.addBox(-2.45F, -12.0F, 4.0F, 2, 3, 1);
/*  226 */     this.Ear2.setRotationPoint(0.0F, 4.0F, -10.0F);
/*  227 */     setRotation(this.Ear2, 0.5235988F, 0.0F, 0.0F);
/*      */     
/*  229 */     this.MuleEarL = new ModelRenderer(this, 0, 12);
/*  230 */     this.MuleEarL.addBox(-2.0F, -16.0F, 4.0F, 2, 7, 1);
/*  231 */     this.MuleEarL.setRotationPoint(0.0F, 4.0F, -10.0F);
/*  232 */     setRotation(this.MuleEarL, 0.5235988F, 0.0F, 0.2617994F);
/*      */     
/*  234 */     this.MuleEarR = new ModelRenderer(this, 0, 12);
/*  235 */     this.MuleEarR.addBox(0.0F, -16.0F, 4.0F, 2, 7, 1);
/*  236 */     this.MuleEarR.setRotationPoint(0.0F, 4.0F, -10.0F);
/*  237 */     setRotation(this.MuleEarR, 0.5235988F, 0.0F, -0.2617994F);
/*      */     
/*  239 */     this.Neck = new ModelRenderer(this, 0, 12);
/*  240 */     this.Neck.addBox(-2.05F, -9.8F, -2.0F, 4, 14, 8);
/*  241 */     this.Neck.setRotationPoint(0.0F, 4.0F, -10.0F);
/*  242 */     setRotation(this.Neck, 0.5235988F, 0.0F, 0.0F);
/*      */     
/*  244 */     this.Bag1 = new ModelRenderer(this, 0, 34);
/*  245 */     this.Bag1.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3);
/*  246 */     this.Bag1.setRotationPoint(-7.5F, 3.0F, 10.0F);
/*  247 */     setRotation(this.Bag1, 0.0F, 1.570796F, 0.0F);
/*      */     
/*  249 */     this.Bag2 = new ModelRenderer(this, 0, 47);
/*  250 */     this.Bag2.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3);
/*  251 */     this.Bag2.setRotationPoint(4.5F, 3.0F, 10.0F);
/*  252 */     setRotation(this.Bag2, 0.0F, 1.570796F, 0.0F);
/*      */     
/*  254 */     this.Saddle = new ModelRenderer(this, 80, 0);
/*  255 */     this.Saddle.addBox(-5.0F, 0.0F, -3.0F, 10, 1, 8);
/*  256 */     this.Saddle.setRotationPoint(0.0F, 2.0F, 2.0F);
/*      */     
/*  258 */     this.SaddleB = new ModelRenderer(this, 106, 9);
/*  259 */     this.SaddleB.addBox(-1.5F, -1.0F, -3.0F, 3, 1, 2);
/*  260 */     this.SaddleB.setRotationPoint(0.0F, 2.0F, 2.0F);
/*      */     
/*  262 */     this.SaddleC = new ModelRenderer(this, 80, 9);
/*  263 */     this.SaddleC.addBox(-4.0F, -1.0F, 3.0F, 8, 1, 2);
/*  264 */     this.SaddleC.setRotationPoint(0.0F, 2.0F, 2.0F);
/*      */     
/*  266 */     this.SaddleL2 = new ModelRenderer(this, 74, 0);
/*  267 */     this.SaddleL2.addBox(-0.5F, 6.0F, -1.0F, 1, 2, 2);
/*  268 */     this.SaddleL2.setRotationPoint(5.0F, 3.0F, 2.0F);
/*      */     
/*  270 */     this.SaddleL = new ModelRenderer(this, 70, 0);
/*  271 */     this.SaddleL.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
/*  272 */     this.SaddleL.setRotationPoint(5.0F, 3.0F, 2.0F);
/*      */     
/*  274 */     this.SaddleR2 = new ModelRenderer(this, 74, 4);
/*  275 */     this.SaddleR2.addBox(-0.5F, 6.0F, -1.0F, 1, 2, 2);
/*  276 */     this.SaddleR2.setRotationPoint(-5.0F, 3.0F, 2.0F);
/*      */     
/*  278 */     this.SaddleR = new ModelRenderer(this, 80, 0);
/*  279 */     this.SaddleR.addBox(-0.5F, 0.0F, -0.5F, 1, 6, 1);
/*  280 */     this.SaddleR.setRotationPoint(-5.0F, 3.0F, 2.0F);
/*      */     
/*  282 */     this.SaddleMouthL = new ModelRenderer(this, 74, 13);
/*  283 */     this.SaddleMouthL.addBox(1.5F, -8.0F, -4.0F, 1, 2, 2);
/*  284 */     this.SaddleMouthL.setRotationPoint(0.0F, 4.0F, -10.0F);
/*  285 */     setRotation(this.SaddleMouthL, 0.5235988F, 0.0F, 0.0F);
/*      */     
/*  287 */     this.SaddleMouthR = new ModelRenderer(this, 74, 13);
/*  288 */     this.SaddleMouthR.addBox(-2.5F, -8.0F, -4.0F, 1, 2, 2);
/*  289 */     this.SaddleMouthR.setRotationPoint(0.0F, 4.0F, -10.0F);
/*  290 */     setRotation(this.SaddleMouthR, 0.5235988F, 0.0F, 0.0F);
/*      */     
/*  292 */     this.SaddleMouthLine = new ModelRenderer(this, 44, 10);
/*  293 */     this.SaddleMouthLine.addBox(2.6F, -6.0F, -6.0F, 0, 3, 16);
/*  294 */     this.SaddleMouthLine.setRotationPoint(0.0F, 4.0F, -10.0F);
/*      */     
/*  296 */     this.SaddleMouthLineR = new ModelRenderer(this, 44, 5);
/*  297 */     this.SaddleMouthLineR.addBox(-2.6F, -6.0F, -6.0F, 0, 3, 16);
/*  298 */     this.SaddleMouthLineR.setRotationPoint(0.0F, 4.0F, -10.0F);
/*      */     
/*  300 */     this.Mane = new ModelRenderer(this, 58, 0);
/*  301 */     this.Mane.addBox(-1.0F, -11.5F, 5.0F, 2, 16, 4);
/*  302 */     this.Mane.setRotationPoint(0.0F, 4.0F, -10.0F);
/*      */ 
/*      */     
/*  305 */     setRotation(this.Mane, 0.5235988F, 0.0F, 0.0F);
/*      */     
/*  307 */     this.HeadSaddle = new ModelRenderer(this, 80, 12);
/*  308 */     this.HeadSaddle.addBox(-2.5F, -10.1F, -7.0F, 5, 5, 12, 0.2F);
/*  309 */     this.HeadSaddle.setRotationPoint(0.0F, 4.0F, -10.0F);
/*  310 */     setRotation(this.HeadSaddle, 0.5235988F, 0.0F, 0.0F);
/*      */     
/*  312 */     this.MidWing = new ModelRenderer(this, 82, 68);
/*  313 */     this.MidWing.addBox(1.0F, 0.1F, 1.0F, 12, 2, 11);
/*  314 */     this.MidWing.setRotationPoint(5.0F, 3.0F, -6.0F);
/*  315 */     setRotation(this.MidWing, 0.0F, 0.0872665F, 0.0F);
/*      */     
/*  317 */     this.InnerWing = new ModelRenderer(this, 0, 96);
/*  318 */     this.InnerWing.addBox(0.0F, 0.0F, 0.0F, 7, 2, 11);
/*  319 */     this.InnerWing.setRotationPoint(5.0F, 3.0F, -6.0F);
/*  320 */     setRotation(this.InnerWing, 0.0F, -0.3490659F, 0.0F);
/*      */     
/*  322 */     this.OuterWing = new ModelRenderer(this, 0, 68);
/*  323 */     this.OuterWing.addBox(0.0F, 0.0F, 0.0F, 22, 2, 11);
/*  324 */     this.OuterWing.setRotationPoint(17.0F, 3.0F, -6.0F);
/*  325 */     setRotation(this.OuterWing, 0.0F, -0.3228859F, 0.0F);
/*      */     
/*  327 */     this.InnerWingR = new ModelRenderer(this, 0, 110);
/*  328 */     this.InnerWingR.addBox(-7.0F, 0.0F, 0.0F, 7, 2, 11);
/*  329 */     this.InnerWingR.setRotationPoint(-5.0F, 3.0F, -6.0F);
/*  330 */     setRotation(this.InnerWingR, 0.0F, 0.3490659F, 0.0F);
/*      */     
/*  332 */     this.MidWingR = new ModelRenderer(this, 82, 82);
/*  333 */     this.MidWingR.addBox(-13.0F, 0.1F, 1.0F, 12, 2, 11);
/*  334 */     this.MidWingR.setRotationPoint(-5.0F, 3.0F, -6.0F);
/*  335 */     setRotation(this.MidWingR, 0.0F, -0.0872665F, 0.0F);
/*      */     
/*  337 */     this.OuterWingR = new ModelRenderer(this, 0, 82);
/*  338 */     this.OuterWingR.addBox(-22.0F, 0.0F, 0.0F, 22, 2, 11);
/*  339 */     this.OuterWingR.setRotationPoint(-17.0F, 3.0F, -6.0F);
/*  340 */     setRotation(this.OuterWingR, 0.0F, 0.3228859F, 0.0F);
/*      */     
/*  342 */     this.ButterflyL = new ModelRenderer(this, 0, 98);
/*  343 */     this.ButterflyL.addBox(-1.0F, 0.0F, -14.0F, 26, 0, 30);
/*  344 */     this.ButterflyL.setRotationPoint(4.5F, 3.0F, -2.0F);
/*  345 */     setRotation(this.ButterflyL, 0.0F, 0.0F, -0.78539F);
/*      */     
/*  347 */     this.ButterflyR = new ModelRenderer(this, 0, 68);
/*  348 */     this.ButterflyR.addBox(-25.0F, 0.0F, -14.0F, 26, 0, 30);
/*  349 */     this.ButterflyR.setRotationPoint(-4.5F, 3.0F, -2.0F);
/*  350 */     setRotation(this.ButterflyR, 0.0F, 0.0F, 0.78539F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  356 */     MoCEntityHorse entityhorse = (MoCEntityHorse)entity;
/*      */ 
/*      */     
/*  359 */     int type = entityhorse.getType();
/*  360 */     int vanishingInt = entityhorse.getVanishC();
/*  361 */     int wingflapInt = entityhorse.wingFlapCounter;
/*  362 */     boolean flapwings = (entityhorse.wingFlapCounter != 0);
/*  363 */     boolean shuffling = (entityhorse.shuffleCounter != 0);
/*  364 */     boolean saddled = entityhorse.getIsRideable();
/*  365 */     boolean wings = (entityhorse.isFlyer() && !entityhorse.getIsGhost() && type < 45);
/*      */     
/*  367 */     boolean eating = entityhorse.getIsSitting();
/*      */     
/*  369 */     boolean standing = (entityhorse.standCounter != 0 && entityhorse.getRidingEntity() == null);
/*  370 */     boolean openMouth = (entityhorse.mouthCounter != 0);
/*  371 */     boolean moveTail = (entityhorse.tailCounter != 0);
/*      */     
/*  373 */     boolean rider = entityhorse.isBeingRidden();
/*  374 */     boolean floating = (entityhorse.getIsGhost() || (entityhorse.isFlyer() && entityhorse.isOnAir()));
/*      */ 
/*      */     
/*  377 */     setRotationAngles(f, f1, f2, f3, f4, f5, eating, rider, floating, standing, saddled, moveTail, wings, flapwings, shuffling, type);
/*      */     
/*  379 */     if (!entityhorse.getIsGhost() && vanishingInt == 0) {
/*  380 */       if (saddled) {
/*  381 */         this.HeadSaddle.render(f5);
/*  382 */         this.Saddle.render(f5);
/*  383 */         this.SaddleB.render(f5);
/*  384 */         this.SaddleC.render(f5);
/*  385 */         this.SaddleL.render(f5);
/*  386 */         this.SaddleL2.render(f5);
/*  387 */         this.SaddleR.render(f5);
/*  388 */         this.SaddleR2.render(f5);
/*  389 */         this.SaddleMouthL.render(f5);
/*  390 */         this.SaddleMouthR.render(f5);
/*  391 */         if (rider) {
/*  392 */           this.SaddleMouthLine.render(f5);
/*  393 */           this.SaddleMouthLineR.render(f5);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  398 */       if (type == 65 || type == 66 || type == 67) {
/*      */         
/*  400 */         this.MuleEarL.render(f5);
/*  401 */         this.MuleEarR.render(f5);
/*      */       } else {
/*  403 */         this.Ear1.render(f5);
/*  404 */         this.Ear2.render(f5);
/*      */       } 
/*      */       
/*  407 */       this.Neck.render(f5);
/*  408 */       this.Head.render(f5);
/*  409 */       if (openMouth) {
/*  410 */         this.UMouth2.render(f5);
/*  411 */         this.LMouth2.render(f5);
/*      */       } else {
/*  413 */         this.UMouth.render(f5);
/*  414 */         this.LMouth.render(f5);
/*      */       } 
/*  416 */       this.Mane.render(f5);
/*  417 */       this.Body.render(f5);
/*  418 */       this.TailA.render(f5);
/*  419 */       this.TailB.render(f5);
/*  420 */       this.TailC.render(f5);
/*      */       
/*  422 */       this.Leg1A.render(f5);
/*  423 */       this.Leg1B.render(f5);
/*  424 */       this.Leg1C.render(f5);
/*      */       
/*  426 */       this.Leg2A.render(f5);
/*  427 */       this.Leg2B.render(f5);
/*  428 */       this.Leg2C.render(f5);
/*      */       
/*  430 */       this.Leg3A.render(f5);
/*  431 */       this.Leg3B.render(f5);
/*  432 */       this.Leg3C.render(f5);
/*      */       
/*  434 */       this.Leg4A.render(f5);
/*  435 */       this.Leg4B.render(f5);
/*  436 */       this.Leg4C.render(f5);
/*      */       
/*  438 */       if (entityhorse.isUnicorned()) {
/*  439 */         this.Unicorn.render(f5);
/*      */       }
/*      */       
/*  442 */       if (entityhorse.getIsChested()) {
/*  443 */         this.Bag1.render(f5);
/*  444 */         this.Bag2.render(f5);
/*      */       } 
/*      */       
/*  447 */       if (entityhorse.isFlyer() && type < 45) {
/*  448 */         this.MidWing.render(f5);
/*  449 */         this.InnerWing.render(f5);
/*  450 */         this.OuterWing.render(f5);
/*  451 */         this.InnerWingR.render(f5);
/*  452 */         this.MidWingR.render(f5);
/*  453 */         this.OuterWingR.render(f5);
/*  454 */       } else if (type > 44 && type < 60) {
/*  455 */         GL11.glPushMatrix();
/*  456 */         GL11.glEnable(3042);
/*  457 */         float transparency = 0.7F;
/*  458 */         GL11.glBlendFunc(770, 771);
/*  459 */         GL11.glColor4f(1.2F, 1.2F, 1.2F, transparency);
/*  460 */         GL11.glScalef(1.3F, 1.0F, 1.3F);
/*  461 */         this.ButterflyL.render(f5);
/*  462 */         this.ButterflyR.render(f5);
/*  463 */         GL11.glDisable(3042);
/*  464 */         GL11.glPopMatrix();
/*      */       } 
/*      */     } else {
/*      */       float transparency;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  474 */       if (vanishingInt != 0) {
/*      */         
/*  476 */         transparency = 1.0F - vanishingInt / 100.0F;
/*      */       } else {
/*  478 */         transparency = entityhorse.tFloat();
/*      */       } 
/*      */       
/*  481 */       GL11.glPushMatrix();
/*  482 */       GL11.glEnable(3042);
/*  483 */       GL11.glBlendFunc(770, 771);
/*  484 */       GL11.glColor4f(0.8F, 0.8F, 0.8F, transparency);
/*  485 */       GL11.glScalef(1.3F, 1.0F, 1.3F);
/*      */       
/*  487 */       this.Ear1.render(f5);
/*  488 */       this.Ear2.render(f5);
/*      */       
/*  490 */       this.Neck.render(f5);
/*  491 */       this.Head.render(f5);
/*  492 */       if (openMouth) {
/*  493 */         this.UMouth2.render(f5);
/*  494 */         this.LMouth2.render(f5);
/*      */       } else {
/*  496 */         this.UMouth.render(f5);
/*  497 */         this.LMouth.render(f5);
/*      */       } 
/*      */       
/*  500 */       this.Mane.render(f5);
/*  501 */       this.Body.render(f5);
/*  502 */       this.TailA.render(f5);
/*  503 */       this.TailB.render(f5);
/*  504 */       this.TailC.render(f5);
/*      */       
/*  506 */       this.Leg1A.render(f5);
/*  507 */       this.Leg1B.render(f5);
/*  508 */       this.Leg1C.render(f5);
/*      */       
/*  510 */       this.Leg2A.render(f5);
/*  511 */       this.Leg2B.render(f5);
/*  512 */       this.Leg2C.render(f5);
/*      */       
/*  514 */       this.Leg3A.render(f5);
/*  515 */       this.Leg3B.render(f5);
/*  516 */       this.Leg3C.render(f5);
/*      */       
/*  518 */       this.Leg4A.render(f5);
/*  519 */       this.Leg4B.render(f5);
/*  520 */       this.Leg4C.render(f5);
/*      */       
/*  522 */       if (type == 39 || type == 40 || type == 28) {
/*  523 */         this.MidWing.render(f5);
/*  524 */         this.InnerWing.render(f5);
/*  525 */         this.OuterWing.render(f5);
/*  526 */         this.InnerWingR.render(f5);
/*  527 */         this.MidWingR.render(f5);
/*  528 */         this.OuterWingR.render(f5);
/*      */       } 
/*  530 */       if (type >= 50 && type < 60) {
/*  531 */         this.ButterflyL.render(f5);
/*  532 */         this.ButterflyR.render(f5);
/*      */       } 
/*      */       
/*  535 */       if (saddled) {
/*  536 */         this.HeadSaddle.render(f5);
/*  537 */         this.Saddle.render(f5);
/*  538 */         this.SaddleB.render(f5);
/*  539 */         this.SaddleC.render(f5);
/*  540 */         this.SaddleL.render(f5);
/*  541 */         this.SaddleL2.render(f5);
/*  542 */         this.SaddleR.render(f5);
/*  543 */         this.SaddleR2.render(f5);
/*  544 */         this.SaddleMouthL.render(f5);
/*  545 */         this.SaddleMouthR.render(f5);
/*  546 */         if (rider) {
/*  547 */           this.SaddleMouthLine.render(f5);
/*  548 */           this.SaddleMouthLineR.render(f5);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  553 */       GL11.glDisable(3042);
/*  554 */       GL11.glPopMatrix();
/*      */       
/*  556 */       if (type == 21 || type == 22) {
/*      */         
/*  558 */         float wingTransparency = 0.0F;
/*  559 */         if (wingflapInt != 0) {
/*  560 */           wingTransparency = 1.0F - wingflapInt / 25.0F;
/*      */         }
/*  562 */         if (wingTransparency > transparency) {
/*  563 */           wingTransparency = transparency;
/*      */         }
/*  565 */         GL11.glPushMatrix();
/*  566 */         GL11.glEnable(3042);
/*  567 */         GL11.glBlendFunc(770, 771);
/*  568 */         GL11.glColor4f(0.8F, 0.8F, 0.8F, wingTransparency);
/*  569 */         GL11.glScalef(1.3F, 1.0F, 1.3F);
/*  570 */         this.ButterflyL.render(f5);
/*  571 */         this.ButterflyR.render(f5);
/*  572 */         GL11.glDisable(3042);
/*  573 */         GL11.glPopMatrix();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  580 */     model.rotateAngleX = x;
/*  581 */     model.rotateAngleY = y;
/*  582 */     model.rotateAngleZ = z;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean eating, boolean rider, boolean floating, boolean standing, boolean saddled, boolean tail, boolean wings, boolean flapwings, boolean shuffle, int type) {
/*  587 */     float RLegXRot = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
/*  588 */     float LLegXRot = MathHelper.cos(f * 0.6662F) * 0.8F * f1;
/*  589 */     float HeadXRot = f4 / 57.29578F;
/*  590 */     if (f3 > 20.0F) {
/*  591 */       f3 = 20.0F;
/*      */     }
/*  593 */     if (f3 < -20.0F) {
/*  594 */       f3 = -20.0F;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  601 */     if (shuffle) {
/*  602 */       HeadXRot += MathHelper.cos(f2 * 0.4F) * 0.15F;
/*  603 */     } else if (f1 > 0.2F && !floating) {
/*  604 */       HeadXRot += MathHelper.cos(f * 0.4F) * 0.15F * f1;
/*      */     } 
/*      */     
/*  607 */     this.Head.rotationPointY = 4.0F;
/*  608 */     this.Head.rotationPointZ = -10.0F;
/*  609 */     this.Head.rotateAngleX = 0.5235988F + HeadXRot;
/*  610 */     this.Head.rotateAngleY = f3 / 57.29578F;
/*  611 */     this.TailA.rotationPointY = 3.0F;
/*  612 */     this.TailB.rotationPointZ = 14.0F;
/*  613 */     this.Bag2.rotationPointY = 3.0F;
/*  614 */     this.Bag2.rotationPointZ = 10.0F;
/*  615 */     this.Body.rotateAngleX = 0.0F;
/*      */     
/*  617 */     if (standing && !shuffle) {
/*      */       
/*  619 */       this.Head.rotationPointY = -6.0F;
/*  620 */       this.Head.rotationPointZ = -1.0F;
/*  621 */       this.Head.rotateAngleX = 0.2617994F + HeadXRot;
/*      */       
/*  623 */       this.Head.rotateAngleY = f3 / 57.29578F;
/*  624 */       this.TailA.rotationPointY = 9.0F;
/*  625 */       this.TailB.rotationPointZ = 18.0F;
/*  626 */       this.Bag2.rotationPointY = 5.5F;
/*  627 */       this.Bag2.rotationPointZ = 15.0F;
/*  628 */       this.Body.rotateAngleX = -0.7853981F;
/*      */     }
/*  630 */     else if (eating && !shuffle) {
/*      */       
/*  632 */       this.Head.rotationPointY = 11.0F;
/*  633 */       this.Head.rotationPointZ = -10.0F;
/*  634 */       this.Head.rotateAngleX = 2.18166F;
/*  635 */       this.Head.rotateAngleY = 0.0F;
/*      */     } 
/*      */ 
/*      */     
/*  639 */     this.Ear1.rotationPointY = this.Head.rotationPointY;
/*  640 */     this.Ear2.rotationPointY = this.Head.rotationPointY;
/*  641 */     this.MuleEarL.rotationPointY = this.Head.rotationPointY;
/*  642 */     this.MuleEarR.rotationPointY = this.Head.rotationPointY;
/*  643 */     this.Neck.rotationPointY = this.Head.rotationPointY;
/*  644 */     this.UMouth.rotationPointY = this.Head.rotationPointY;
/*  645 */     this.UMouth2.rotationPointY = this.Head.rotationPointY;
/*  646 */     this.LMouth.rotationPointY = this.Head.rotationPointY;
/*  647 */     this.LMouth2.rotationPointY = this.Head.rotationPointY;
/*  648 */     this.Mane.rotationPointY = this.Head.rotationPointY;
/*  649 */     this.Unicorn.rotationPointY = this.Head.rotationPointY;
/*      */     
/*  651 */     this.Ear1.rotationPointZ = this.Head.rotationPointZ;
/*  652 */     this.Ear2.rotationPointZ = this.Head.rotationPointZ;
/*  653 */     this.MuleEarL.rotationPointZ = this.Head.rotationPointZ;
/*  654 */     this.MuleEarR.rotationPointZ = this.Head.rotationPointZ;
/*  655 */     this.Neck.rotationPointZ = this.Head.rotationPointZ;
/*  656 */     this.UMouth.rotationPointZ = this.Head.rotationPointZ;
/*  657 */     this.UMouth2.rotationPointZ = this.Head.rotationPointZ;
/*  658 */     this.LMouth.rotationPointZ = this.Head.rotationPointZ;
/*  659 */     this.LMouth2.rotationPointZ = this.Head.rotationPointZ;
/*  660 */     this.Mane.rotationPointZ = this.Head.rotationPointZ;
/*  661 */     this.Unicorn.rotationPointZ = this.Head.rotationPointZ;
/*      */     
/*  663 */     this.Ear1.rotateAngleX = this.Head.rotateAngleX;
/*  664 */     this.Ear2.rotateAngleX = this.Head.rotateAngleX;
/*  665 */     this.MuleEarL.rotateAngleX = this.Head.rotateAngleX;
/*  666 */     this.MuleEarR.rotateAngleX = this.Head.rotateAngleX;
/*  667 */     this.Neck.rotateAngleX = this.Head.rotateAngleX;
/*  668 */     this.UMouth.rotateAngleX = this.Head.rotateAngleX;
/*  669 */     this.Head.rotateAngleX -= 0.0872664F;
/*  670 */     this.LMouth.rotateAngleX = this.Head.rotateAngleX;
/*  671 */     this.Head.rotateAngleX += 0.261799F;
/*  672 */     this.Mane.rotateAngleX = this.Head.rotateAngleX;
/*  673 */     this.Unicorn.rotateAngleX = this.Head.rotateAngleX;
/*      */     
/*  675 */     this.Ear1.rotateAngleY = this.Head.rotateAngleY;
/*  676 */     this.Ear2.rotateAngleY = this.Head.rotateAngleY;
/*  677 */     this.MuleEarL.rotateAngleY = this.Head.rotateAngleY;
/*  678 */     this.MuleEarR.rotateAngleY = this.Head.rotateAngleY;
/*  679 */     this.Neck.rotateAngleY = this.Head.rotateAngleY;
/*  680 */     this.UMouth.rotateAngleY = this.Head.rotateAngleY;
/*  681 */     this.LMouth.rotateAngleY = this.Head.rotateAngleY;
/*  682 */     this.UMouth2.rotateAngleY = this.Head.rotateAngleY;
/*  683 */     this.LMouth2.rotateAngleY = this.Head.rotateAngleY;
/*  684 */     this.Mane.rotateAngleY = this.Head.rotateAngleY;
/*  685 */     this.Unicorn.rotateAngleY = this.Head.rotateAngleY;
/*      */ 
/*      */     
/*  688 */     this.Bag1.rotateAngleX = RLegXRot / 5.0F;
/*  689 */     this.Bag2.rotateAngleX = -RLegXRot / 5.0F;
/*      */     
/*  691 */     if (wings) {
/*  692 */       this.InnerWing.rotateAngleX = this.Body.rotateAngleX;
/*  693 */       this.MidWing.rotateAngleX = this.Body.rotateAngleX;
/*  694 */       this.OuterWing.rotateAngleX = this.Body.rotateAngleX;
/*  695 */       this.InnerWingR.rotateAngleX = this.Body.rotateAngleX;
/*  696 */       this.MidWingR.rotateAngleX = this.Body.rotateAngleX;
/*  697 */       this.OuterWingR.rotateAngleX = this.Body.rotateAngleX;
/*      */       
/*  699 */       if (standing) {
/*  700 */         this.InnerWing.rotationPointY = -5.0F;
/*  701 */         this.InnerWing.rotationPointZ = 4.0F;
/*      */       } else {
/*  703 */         this.InnerWing.rotationPointY = 3.0F;
/*  704 */         this.InnerWing.rotationPointZ = -6.0F;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  711 */       float WingRot = 0.0F;
/*  712 */       if (flapwings) {
/*  713 */         WingRot = MathHelper.cos(f2 * 0.3F + 3.141593F) * 1.2F;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  718 */         WingRot = MathHelper.cos(f * 0.5F) * 0.1F;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  728 */       if (floating) {
/*  729 */         this.OuterWing.rotateAngleY = -0.3228859F + WingRot / 2.0F;
/*  730 */         this.OuterWingR.rotateAngleY = 0.3228859F - WingRot / 2.0F;
/*      */       } else {
/*      */         
/*  733 */         WingRot = 1.0471976F;
/*  734 */         this.OuterWing.rotateAngleY = -1.5707963F;
/*  735 */         this.OuterWingR.rotateAngleY = 1.5707963F;
/*      */       } 
/*      */       
/*  738 */       this.InnerWingR.rotationPointY = this.InnerWing.rotationPointY;
/*  739 */       this.InnerWingR.rotationPointZ = this.InnerWing.rotationPointZ;
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
/*  751 */       this.InnerWing.rotationPointX += MathHelper.cos(WingRot) * 12.0F;
/*  752 */       this.InnerWingR.rotationPointX -= MathHelper.cos(WingRot) * 12.0F;
/*      */       
/*  754 */       this.MidWing.rotationPointY = this.InnerWing.rotationPointY;
/*  755 */       this.MidWingR.rotationPointY = this.InnerWing.rotationPointY;
/*  756 */       this.InnerWing.rotationPointY += MathHelper.sin(WingRot) * 12.0F;
/*  757 */       this.InnerWingR.rotationPointY += MathHelper.sin(WingRot) * 12.0F;
/*      */       
/*  759 */       this.MidWing.rotationPointZ = this.InnerWing.rotationPointZ;
/*  760 */       this.MidWingR.rotationPointZ = this.InnerWing.rotationPointZ;
/*  761 */       this.OuterWing.rotationPointZ = this.InnerWing.rotationPointZ;
/*  762 */       this.OuterWingR.rotationPointZ = this.InnerWing.rotationPointZ;
/*      */       
/*  764 */       this.MidWing.rotateAngleZ = WingRot;
/*  765 */       this.InnerWing.rotateAngleZ = WingRot;
/*  766 */       this.OuterWing.rotateAngleZ = WingRot;
/*      */       
/*  768 */       this.InnerWingR.rotateAngleZ = -WingRot;
/*  769 */       this.MidWingR.rotateAngleZ = -WingRot;
/*  770 */       this.OuterWingR.rotateAngleZ = -WingRot;
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
/*  793 */     if ((type > 44 && type < 60) || type == 21) {
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
/*  806 */       float f2a = f2 % 100.0F;
/*  807 */       float WingRot = 0.0F;
/*      */       
/*  809 */       if (type != 21) {
/*      */         
/*  811 */         if (flapwings)
/*      */         {
/*  813 */           WingRot = MathHelper.cos(f2 * 0.9F) * 0.9F;
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*  818 */         else if (floating)
/*      */         {
/*  820 */           WingRot = MathHelper.cos(f2 * 0.6662F) * 0.5F;
/*      */         }
/*  822 */         else if ((((f2a > 40.0F) ? 1 : 0) & ((f2a < 60.0F) ? 1 : 0)) != 0)
/*      */         {
/*  824 */           WingRot = MathHelper.cos(f2 * 0.15F) * 1.2F;
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  834 */         WingRot = MathHelper.cos(f2 * 0.1F);
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
/*      */ 
/*      */ 
/*      */       
/*  849 */       if (standing) {
/*  850 */         this.ButterflyL.rotationPointY = -2.5F;
/*  851 */         this.ButterflyL.rotationPointZ = 6.5F;
/*      */       } else {
/*  853 */         this.ButterflyL.rotationPointY = 3.0F;
/*  854 */         this.ButterflyL.rotationPointZ = -2.0F;
/*      */       } 
/*      */       
/*  857 */       this.ButterflyR.rotationPointY = this.ButterflyL.rotationPointY;
/*  858 */       this.ButterflyR.rotationPointZ = this.ButterflyL.rotationPointZ;
/*  859 */       this.ButterflyL.rotateAngleX = this.Body.rotateAngleX;
/*  860 */       this.ButterflyR.rotateAngleX = this.Body.rotateAngleX;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  866 */       float baseAngle = 0.52359F;
/*  867 */       if (type == 21) {
/*  868 */         baseAngle = 0.0F;
/*      */       }
/*  870 */       this.ButterflyL.rotateAngleZ = -baseAngle + WingRot;
/*  871 */       this.ButterflyR.rotateAngleZ = baseAngle - WingRot;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  879 */     float RLegXRotB = RLegXRot;
/*  880 */     float LLegXRotB = LLegXRot;
/*  881 */     float RLegXRotC = RLegXRot;
/*  882 */     float LLegXRotC = LLegXRot;
/*      */     
/*  884 */     if (floating) {
/*  885 */       RLegXRot = 0.2617994F;
/*  886 */       LLegXRot = RLegXRot;
/*  887 */       RLegXRotB = 0.7853981F;
/*  888 */       RLegXRotC = RLegXRotB;
/*  889 */       LLegXRotB = RLegXRotB;
/*  890 */       LLegXRotC = RLegXRotB;
/*      */     } 
/*      */     
/*  893 */     if (standing) {
/*  894 */       this.Leg3A.rotationPointY = -2.0F;
/*  895 */       this.Leg3A.rotationPointZ = -2.0F;
/*  896 */       this.Leg4A.rotationPointY = this.Leg3A.rotationPointY;
/*  897 */       this.Leg4A.rotationPointZ = this.Leg3A.rotationPointZ;
/*      */       
/*  899 */       RLegXRot = -1.0471976F + MathHelper.cos(f2 * 0.4F + 3.141593F);
/*  900 */       LLegXRot = -1.0471976F + MathHelper.cos(f2 * 0.4F);
/*      */       
/*  902 */       RLegXRotB = 0.7853981F;
/*  903 */       LLegXRotB = RLegXRotB;
/*      */       
/*  905 */       RLegXRotC = -0.2617994F;
/*  906 */       LLegXRotC = 0.2617994F;
/*      */       
/*  908 */       this.Leg3A.rotationPointY += MathHelper.sin(1.5707963F + RLegXRot) * 7.0F;
/*  909 */       this.Leg3A.rotationPointZ += MathHelper.cos(4.712389F + RLegXRot) * 7.0F;
/*      */ 
/*      */       
/*  912 */       this.Leg4A.rotationPointY += MathHelper.sin(1.5707963F + LLegXRot) * 7.0F;
/*  913 */       this.Leg4A.rotationPointZ += MathHelper.cos(4.712389F + LLegXRot) * 7.0F;
/*      */       
/*  915 */       this.Leg1A.rotationPointY += MathHelper.sin(1.5707963F + RLegXRotC) * 7.0F;
/*  916 */       this.Leg1A.rotationPointZ += MathHelper.cos(4.712389F + RLegXRotC) * 7.0F;
/*      */ 
/*      */       
/*  919 */       this.Leg2B.rotationPointY = this.Leg1B.rotationPointY;
/*  920 */       this.Leg2B.rotationPointZ = this.Leg1B.rotationPointZ;
/*      */       
/*  922 */       this.Leg1A.rotateAngleX = RLegXRotC;
/*  923 */       this.Leg1B.rotateAngleX = LLegXRotC;
/*  924 */       this.Leg1C.rotateAngleX = this.Leg1B.rotateAngleX;
/*      */       
/*  926 */       this.Leg2A.rotateAngleX = RLegXRotC;
/*  927 */       this.Leg2B.rotateAngleX = LLegXRotC;
/*  928 */       this.Leg2C.rotateAngleX = this.Leg2B.rotateAngleX;
/*      */       
/*  930 */       this.Leg3A.rotateAngleX = RLegXRot;
/*  931 */       this.Leg3B.rotateAngleX = RLegXRotB;
/*  932 */       this.Leg3C.rotateAngleX = RLegXRotB;
/*      */       
/*  934 */       this.Leg4A.rotateAngleX = LLegXRot;
/*  935 */       this.Leg4B.rotateAngleX = LLegXRotB;
/*  936 */       this.Leg4C.rotateAngleX = LLegXRotB;
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  942 */       this.Leg3A.rotationPointY = 9.0F;
/*  943 */       this.Leg3A.rotationPointZ = -8.0F;
/*  944 */       this.Leg4A.rotationPointY = this.Leg3A.rotationPointY;
/*  945 */       this.Leg4A.rotationPointZ = this.Leg3A.rotationPointZ;
/*      */       
/*  947 */       if (!floating && f1 > 0.2F) {
/*      */         
/*  949 */         float RLegXRot2 = MathHelper.cos((f + 0.1F) * 0.6662F + 3.141593F) * 0.8F * f1;
/*  950 */         float LLegXRot2 = MathHelper.cos((f + 0.1F) * 0.6662F) * 0.8F * f1;
/*  951 */         if (RLegXRot > RLegXRot2)
/*      */         {
/*  953 */           RLegXRotB = RLegXRot + 0.9599311F;
/*      */         }
/*      */ 
/*      */         
/*  957 */         if (RLegXRot < RLegXRot2)
/*      */         {
/*  959 */           RLegXRotC = RLegXRot + 0.2617994F;
/*      */         }
/*      */ 
/*      */         
/*  963 */         if (LLegXRot > LLegXRot2)
/*      */         {
/*  965 */           LLegXRotB = LLegXRot + 0.9599311F;
/*      */         }
/*  967 */         if (LLegXRot < LLegXRot2)
/*      */         {
/*  969 */           LLegXRotC = LLegXRot + 0.2617994F;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  974 */       this.Leg1A.rotationPointY += MathHelper.sin(1.5707963F + LLegXRot) * 7.0F;
/*  975 */       this.Leg1A.rotationPointZ += MathHelper.cos(4.712389F + LLegXRot) * 7.0F;
/*      */ 
/*      */       
/*  978 */       this.Leg2A.rotationPointY += MathHelper.sin(1.5707963F + RLegXRot) * 7.0F;
/*  979 */       this.Leg2A.rotationPointZ += MathHelper.cos(4.712389F + RLegXRot) * 7.0F;
/*      */ 
/*      */       
/*  982 */       this.Leg3A.rotationPointY += MathHelper.sin(1.5707963F + RLegXRot) * 7.0F;
/*  983 */       this.Leg3A.rotationPointZ += MathHelper.cos(4.712389F + RLegXRot) * 7.0F;
/*      */ 
/*      */       
/*  986 */       this.Leg4A.rotationPointY += MathHelper.sin(1.5707963F + LLegXRot) * 7.0F;
/*  987 */       this.Leg4A.rotationPointZ += MathHelper.cos(4.712389F + LLegXRot) * 7.0F;
/*      */       
/*  989 */       this.Leg1A.rotateAngleX = LLegXRot;
/*  990 */       this.Leg1B.rotateAngleX = LLegXRotC;
/*  991 */       this.Leg1C.rotateAngleX = LLegXRotC;
/*      */       
/*  993 */       this.Leg2A.rotateAngleX = RLegXRot;
/*  994 */       this.Leg2B.rotateAngleX = RLegXRotC;
/*  995 */       this.Leg2C.rotateAngleX = RLegXRotC;
/*      */       
/*  997 */       this.Leg3A.rotateAngleX = RLegXRot;
/*  998 */       this.Leg3B.rotateAngleX = RLegXRotB;
/*  999 */       this.Leg3C.rotateAngleX = RLegXRotB;
/*      */       
/* 1001 */       this.Leg4A.rotateAngleX = LLegXRot;
/* 1002 */       this.Leg4B.rotateAngleX = LLegXRotB;
/* 1003 */       this.Leg4C.rotateAngleX = LLegXRotB;
/*      */     } 
/*      */ 
/*      */     
/* 1007 */     if (type == 60 && shuffle) {
/* 1008 */       this.Leg3A.rotationPointY = 9.0F;
/* 1009 */       this.Leg3A.rotationPointZ = -8.0F;
/* 1010 */       this.Leg4A.rotationPointY = this.Leg3A.rotationPointY;
/* 1011 */       this.Leg4A.rotationPointZ = this.Leg3A.rotationPointZ;
/*      */       
/* 1013 */       if (!floating) {
/*      */ 
/*      */ 
/*      */         
/* 1017 */         RLegXRot = MathHelper.cos(f2 * 0.4F);
/* 1018 */         if (RLegXRot > 0.1F) {
/* 1019 */           RLegXRot = 0.3F;
/*      */         }
/*      */         
/* 1022 */         LLegXRot = MathHelper.cos(f2 * 0.4F + 3.141593F);
/* 1023 */         if (LLegXRot > 0.1F) {
/* 1024 */           LLegXRot = 0.3F;
/*      */         }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1044 */       this.Leg1A.rotationPointY += MathHelper.sin(1.5707963F + LLegXRot) * 7.0F;
/* 1045 */       this.Leg1A.rotationPointZ += MathHelper.cos(4.712389F + LLegXRot) * 7.0F;
/*      */ 
/*      */       
/* 1048 */       this.Leg2A.rotationPointY += MathHelper.sin(1.5707963F + RLegXRot) * 7.0F;
/* 1049 */       this.Leg2A.rotationPointZ += MathHelper.cos(4.712389F + RLegXRot) * 7.0F;
/*      */ 
/*      */       
/* 1052 */       this.Leg3A.rotationPointY += MathHelper.sin(1.5707963F + LLegXRot) * 7.0F;
/* 1053 */       this.Leg3A.rotationPointZ += MathHelper.cos(4.712389F + LLegXRot) * 7.0F;
/*      */ 
/*      */       
/* 1056 */       this.Leg4A.rotationPointY += MathHelper.sin(1.5707963F + RLegXRot) * 7.0F;
/* 1057 */       this.Leg4A.rotationPointZ += MathHelper.cos(4.712389F + RLegXRot) * 7.0F;
/*      */       
/* 1059 */       this.Leg1A.rotateAngleX = LLegXRot;
/* 1060 */       this.Leg1B.rotateAngleX = LLegXRotB;
/* 1061 */       this.Leg1C.rotateAngleX = LLegXRotB;
/*      */       
/* 1063 */       this.Leg3A.rotateAngleX = LLegXRot;
/* 1064 */       this.Leg3B.rotateAngleX = LLegXRotB;
/* 1065 */       this.Leg3C.rotateAngleX = LLegXRotB;
/*      */       
/* 1067 */       this.Leg2A.rotateAngleX = RLegXRot;
/* 1068 */       this.Leg2B.rotateAngleX = RLegXRotB;
/* 1069 */       this.Leg2C.rotateAngleX = RLegXRotB;
/*      */       
/* 1071 */       this.Leg4A.rotateAngleX = RLegXRot;
/* 1072 */       this.Leg4B.rotateAngleX = RLegXRotB;
/* 1073 */       this.Leg4C.rotateAngleX = RLegXRotB;
/*      */     } 
/*      */ 
/*      */     
/* 1077 */     this.Leg1C.rotationPointY = this.Leg1B.rotationPointY;
/* 1078 */     this.Leg1C.rotationPointZ = this.Leg1B.rotationPointZ;
/* 1079 */     this.Leg2C.rotationPointY = this.Leg2B.rotationPointY;
/* 1080 */     this.Leg2C.rotationPointZ = this.Leg2B.rotationPointZ;
/* 1081 */     this.Leg3C.rotationPointY = this.Leg3B.rotationPointY;
/* 1082 */     this.Leg3C.rotationPointZ = this.Leg3B.rotationPointZ;
/* 1083 */     this.Leg4C.rotationPointY = this.Leg4B.rotationPointY;
/* 1084 */     this.Leg4C.rotationPointZ = this.Leg4B.rotationPointZ;
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
/* 1102 */     if (saddled) {
/*      */       
/* 1104 */       if (standing) {
/* 1105 */         this.Saddle.rotationPointY = 0.5F;
/* 1106 */         this.Saddle.rotationPointZ = 11.0F;
/*      */       } else {
/*      */         
/* 1109 */         this.Saddle.rotationPointY = 2.0F;
/* 1110 */         this.Saddle.rotationPointZ = 2.0F;
/*      */       } 
/*      */ 
/*      */       
/* 1114 */       this.SaddleB.rotationPointY = this.Saddle.rotationPointY;
/* 1115 */       this.SaddleC.rotationPointY = this.Saddle.rotationPointY;
/* 1116 */       this.SaddleL.rotationPointY = this.Saddle.rotationPointY;
/* 1117 */       this.SaddleR.rotationPointY = this.Saddle.rotationPointY;
/* 1118 */       this.SaddleL2.rotationPointY = this.Saddle.rotationPointY;
/* 1119 */       this.SaddleR2.rotationPointY = this.Saddle.rotationPointY;
/* 1120 */       this.Bag1.rotationPointY = this.Bag2.rotationPointY;
/*      */       
/* 1122 */       this.SaddleB.rotationPointZ = this.Saddle.rotationPointZ;
/* 1123 */       this.SaddleC.rotationPointZ = this.Saddle.rotationPointZ;
/* 1124 */       this.SaddleL.rotationPointZ = this.Saddle.rotationPointZ;
/* 1125 */       this.SaddleR.rotationPointZ = this.Saddle.rotationPointZ;
/* 1126 */       this.SaddleL2.rotationPointZ = this.Saddle.rotationPointZ;
/* 1127 */       this.SaddleR2.rotationPointZ = this.Saddle.rotationPointZ;
/* 1128 */       this.Bag1.rotationPointZ = this.Bag2.rotationPointZ;
/*      */       
/* 1130 */       this.Saddle.rotateAngleX = this.Body.rotateAngleX;
/* 1131 */       this.SaddleB.rotateAngleX = this.Body.rotateAngleX;
/* 1132 */       this.SaddleC.rotateAngleX = this.Body.rotateAngleX;
/*      */       
/* 1134 */       this.SaddleMouthLine.rotationPointY = this.Head.rotationPointY;
/* 1135 */       this.SaddleMouthLineR.rotationPointY = this.Head.rotationPointY;
/* 1136 */       this.HeadSaddle.rotationPointY = this.Head.rotationPointY;
/* 1137 */       this.SaddleMouthL.rotationPointY = this.Head.rotationPointY;
/* 1138 */       this.SaddleMouthR.rotationPointY = this.Head.rotationPointY;
/*      */       
/* 1140 */       this.SaddleMouthLine.rotationPointZ = this.Head.rotationPointZ;
/* 1141 */       this.SaddleMouthLineR.rotationPointZ = this.Head.rotationPointZ;
/* 1142 */       this.HeadSaddle.rotationPointZ = this.Head.rotationPointZ;
/* 1143 */       this.SaddleMouthL.rotationPointZ = this.Head.rotationPointZ;
/* 1144 */       this.SaddleMouthR.rotationPointZ = this.Head.rotationPointZ;
/*      */       
/* 1146 */       this.SaddleMouthLine.rotateAngleX = HeadXRot;
/* 1147 */       this.SaddleMouthLineR.rotateAngleX = HeadXRot;
/* 1148 */       this.HeadSaddle.rotateAngleX = this.Head.rotateAngleX;
/* 1149 */       this.SaddleMouthL.rotateAngleX = this.Head.rotateAngleX;
/* 1150 */       this.SaddleMouthR.rotateAngleX = this.Head.rotateAngleX;
/* 1151 */       this.HeadSaddle.rotateAngleY = this.Head.rotateAngleY;
/* 1152 */       this.SaddleMouthL.rotateAngleY = this.Head.rotateAngleY;
/* 1153 */       this.SaddleMouthLine.rotateAngleY = this.Head.rotateAngleY;
/* 1154 */       this.SaddleMouthR.rotateAngleY = this.Head.rotateAngleY;
/* 1155 */       this.SaddleMouthLineR.rotateAngleY = this.Head.rotateAngleY;
/*      */       
/* 1157 */       if (rider) {
/* 1158 */         this.SaddleL.rotateAngleX = -1.0471976F;
/* 1159 */         this.SaddleL2.rotateAngleX = -1.0471976F;
/* 1160 */         this.SaddleR.rotateAngleX = -1.0471976F;
/* 1161 */         this.SaddleR2.rotateAngleX = -1.0471976F;
/*      */         
/* 1163 */         this.SaddleL.rotateAngleZ = 0.0F;
/* 1164 */         this.SaddleL2.rotateAngleZ = 0.0F;
/* 1165 */         this.SaddleR.rotateAngleZ = 0.0F;
/* 1166 */         this.SaddleR2.rotateAngleZ = 0.0F;
/*      */       } else {
/*      */         
/* 1169 */         this.SaddleL.rotateAngleX = RLegXRot / 3.0F;
/* 1170 */         this.SaddleL2.rotateAngleX = RLegXRot / 3.0F;
/* 1171 */         this.SaddleR.rotateAngleX = RLegXRot / 3.0F;
/* 1172 */         this.SaddleR2.rotateAngleX = RLegXRot / 3.0F;
/*      */         
/* 1174 */         this.SaddleL.rotateAngleZ = RLegXRot / 5.0F;
/* 1175 */         this.SaddleL2.rotateAngleZ = RLegXRot / 5.0F;
/* 1176 */         this.SaddleR.rotateAngleZ = -RLegXRot / 5.0F;
/* 1177 */         this.SaddleR2.rotateAngleZ = -RLegXRot / 5.0F;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1184 */     float tailMov = -1.3089F + f1 * 1.5F;
/* 1185 */     if (tailMov > 0.0F) {
/* 1186 */       tailMov = 0.0F;
/*      */     }
/*      */     
/* 1189 */     if (tail) {
/* 1190 */       this.TailA.rotateAngleY = MathHelper.cos(f2 * 0.7F);
/* 1191 */       tailMov = 0.0F;
/*      */     } else {
/* 1193 */       this.TailA.rotateAngleY = 0.0F;
/*      */     } 
/* 1195 */     this.TailB.rotateAngleY = this.TailA.rotateAngleY;
/* 1196 */     this.TailC.rotateAngleY = this.TailA.rotateAngleY;
/*      */     
/* 1198 */     this.TailB.rotationPointY = this.TailA.rotationPointY;
/* 1199 */     this.TailC.rotationPointY = this.TailA.rotationPointY;
/* 1200 */     this.TailB.rotationPointZ = this.TailA.rotationPointZ;
/* 1201 */     this.TailC.rotationPointZ = this.TailA.rotationPointZ;
/*      */     
/* 1203 */     this.TailA.rotateAngleX = tailMov;
/* 1204 */     this.TailB.rotateAngleX = tailMov;
/* 1205 */     this.TailC.rotateAngleX = -0.2618F + tailMov;
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelNewHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */