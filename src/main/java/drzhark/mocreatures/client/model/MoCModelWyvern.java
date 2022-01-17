/*      */ package drzhark.mocreatures.client.model;
/*      */ 
/*      */ import drzhark.mocreatures.MoCTools;
/*      */ import drzhark.mocreatures.entity.passive.MoCEntityWyvern;
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
/*      */ @SideOnly(Side.CLIENT)
/*      */ public class MoCModelWyvern
/*      */   extends ModelBase
/*      */ {
/*      */   ModelRenderer back4;
/*      */   ModelRenderer back3;
/*      */   ModelRenderer back2;
/*      */   ModelRenderer back1;
/*      */   ModelRenderer Tail;
/*      */   ModelRenderer tail1;
/*      */   ModelRenderer tail2;
/*      */   ModelRenderer tail3;
/*      */   ModelRenderer tail4;
/*      */   ModelRenderer tail5;
/*      */   ModelRenderer chest;
/*      */   ModelRenderer headplate;
/*      */   ModelRenderer neckplate3;
/*      */   ModelRenderer neck3;
/*      */   ModelRenderer neckplate2;
/*      */   ModelRenderer neck2;
/*      */   ModelRenderer neckplate1;
/*      */   ModelRenderer neck1;
/*      */   ModelRenderer rightupleg;
/*      */   ModelRenderer rightmidleg;
/*      */   ModelRenderer rightlowleg;
/*      */   ModelRenderer rightfoot;
/*      */   ModelRenderer leftupleg;
/*      */   ModelRenderer leftmidleg;
/*      */   ModelRenderer leftlowleg;
/*      */   ModelRenderer leftupjaw;
/*      */   ModelRenderer rightupjaw;
/*      */   ModelRenderer righteyesock;
/*      */   ModelRenderer lefteyesock;
/*      */   ModelRenderer beak;
/*      */   ModelRenderer snout;
/*      */   ModelRenderer torso;
/*      */   ModelRenderer rightshoulder;
/*      */   ModelRenderer leftshoulder;
/*      */   ModelRenderer leftfoot;
/*      */   ModelRenderer righttoe3;
/*      */   ModelRenderer righttoe2;
/*      */   ModelRenderer righttoe1;
/*      */   ModelRenderer rightclaw1;
/*      */   ModelRenderer rightclaw2;
/*      */   ModelRenderer rightclaw3;
/*      */   ModelRenderer lefttoe3;
/*      */   ModelRenderer lefttoe2;
/*      */   ModelRenderer lefttoe1;
/*      */   ModelRenderer leftclaw1;
/*      */   ModelRenderer leftclaw2;
/*      */   ModelRenderer leftclaw3;
/*      */   ModelRenderer head;
/*      */   ModelRenderer Jaw;
/*      */   ModelRenderer saddle;
/*      */   ModelRenderer mouthrod;
/*      */   ModelRenderer controlrope1;
/*      */   ModelRenderer controlrope2;
/*      */   ModelRenderer storage;
/*      */   ModelRenderer helmetstrap1;
/*      */   ModelRenderer helmetstrap2;
/*      */   ModelRenderer leftspine1;
/*      */   ModelRenderer leftspine2;
/*      */   ModelRenderer leftspine3;
/*      */   ModelRenderer leftearskin;
/*      */   ModelRenderer rightspine3;
/*      */   ModelRenderer rightspine2;
/*      */   ModelRenderer rightspine1;
/*      */   ModelRenderer rightearskin;
/*      */   ModelRenderer chestbelt;
/*      */   ModelRenderer stomachbelt;
/*      */   ModelRenderer ironhelmethorn1;
/*      */   ModelRenderer ironhelmethorn2;
/*      */   ModelRenderer ironhelmet;
/*      */   ModelRenderer ironhelmetsnout;
/*      */   ModelRenderer ironrightlegarmor;
/*      */   ModelRenderer ironleftlegarmor;
/*      */   ModelRenderer ironchestarmor;
/*      */   ModelRenderer ironrightshoulderpad;
/*      */   ModelRenderer ironleftshoulderpad;
/*      */   ModelRenderer goldleftshoulder;
/*      */   ModelRenderer goldchestarmor;
/*      */   ModelRenderer goldrightshoulder;
/*      */   ModelRenderer goldleftlegarmor;
/*      */   ModelRenderer goldrightlegarmor;
/*      */   ModelRenderer goldhelmethorn1;
/*      */   ModelRenderer goldhelmethorn2;
/*      */   ModelRenderer goldhelmet;
/*      */   ModelRenderer goldhelmetsnout;
/*      */   ModelRenderer diamondleftshoulder;
/*      */   ModelRenderer diamondrightshoulder;
/*      */   ModelRenderer diamondchestarmor;
/*      */   ModelRenderer diamondleftlegarmor;
/*      */   ModelRenderer diamondrightlegarmor;
/*      */   ModelRenderer diamondhelmet;
/*      */   ModelRenderer diamondhelmethorn2;
/*      */   ModelRenderer diamondhelmethorn1;
/*      */   ModelRenderer diamondhelmetsnout;
/*      */   ModelRenderer rightfing1a;
/*      */   ModelRenderer rightfing2a;
/*      */   ModelRenderer rightfing3a;
/*      */   ModelRenderer rightfing1b;
/*      */   ModelRenderer rightfing2b;
/*      */   ModelRenderer rightfing3b;
/*      */   ModelRenderer leftfing3a;
/*      */   ModelRenderer leftfing2a;
/*      */   ModelRenderer leftfing1a;
/*      */   ModelRenderer leftfing3b;
/*      */   ModelRenderer leftfing2b;
/*      */   ModelRenderer leftfing1b;
/*      */   ModelRenderer leftlowarm;
/*      */   ModelRenderer rightlowarm;
/*      */   ModelRenderer rightuparm;
/*      */   ModelRenderer leftuparm;
/*      */   ModelRenderer LeftWing;
/*      */   ModelRenderer RightWing;
/*      */   ModelRenderer MainHead;
/*      */   ModelRenderer leftwingflap1;
/*      */   ModelRenderer leftwingflap2;
/*      */   ModelRenderer leftwingflap3;
/*      */   ModelRenderer rightwingflap1;
/*      */   ModelRenderer rightwingflap2;
/*      */   ModelRenderer rightwingflap3;
/*  137 */   private float radianF = 57.29578F;
/*      */   
/*      */   private boolean isRidden;
/*      */   private boolean isChested;
/*      */   private boolean isSaddled;
/*      */   private boolean flapwings;
/*      */   private boolean onAir;
/*      */   private boolean diving;
/*      */   private boolean isSitting;
/*      */   private boolean isGhost;
/*      */   private int openMouth;
/*      */   
/*      */   public MoCModelWyvern() {
/*  150 */     this.textureWidth = 128;
/*  151 */     this.textureHeight = 256;
/*      */ 
/*      */     
/*  154 */     this.Tail = new ModelRenderer(this);
/*  155 */     this.Tail.setRotationPoint(0.0F, 0.0F, 0.0F);
/*      */     
/*  157 */     this.back1 = new ModelRenderer(this, 92, 0);
/*  158 */     this.back1.addBox(-3.0F, -2.0F, -12.0F, 6, 2, 12);
/*  159 */     this.back1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*      */     
/*  161 */     this.tail1 = new ModelRenderer(this, 0, 22);
/*  162 */     this.tail1.addBox(-4.0F, 0.0F, 0.0F, 8, 8, 10);
/*  163 */     this.tail1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  164 */     this.Tail.addChild(this.tail1);
/*      */     
/*  166 */     this.back2 = new ModelRenderer(this, 100, 14);
/*  167 */     this.back2.addBox(-2.0F, -2.0F, 0.0F, 4, 2, 10);
/*  168 */     this.back2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  169 */     this.tail1.addChild(this.back2);
/*      */     
/*  171 */     this.tail2 = new ModelRenderer(this, 0, 40);
/*  172 */     this.tail2.addBox(-3.0F, 0.0F, 0.0F, 6, 6, 9);
/*  173 */     this.tail2.setRotationPoint(0.0F, 0.0F, 10.0F);
/*  174 */     this.tail1.addChild(this.tail2);
/*      */     
/*  176 */     this.back3 = new ModelRenderer(this, 104, 26);
/*  177 */     this.back3.addBox(-1.5F, -2.0F, 0.0F, 3, 2, 9);
/*  178 */     this.back3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  179 */     this.tail2.addChild(this.back3);
/*      */     
/*  181 */     this.tail3 = new ModelRenderer(this, 0, 55);
/*  182 */     this.tail3.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 8);
/*  183 */     this.tail3.setRotationPoint(0.0F, 0.0F, 8.0F);
/*  184 */     this.tail2.addChild(this.tail3);
/*      */     
/*  186 */     this.back4 = new ModelRenderer(this, 108, 37);
/*  187 */     this.back4.addBox(-1.0F, -2.0F, 0.0F, 2, 2, 8);
/*  188 */     this.back4.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  189 */     this.tail3.addChild(this.back4);
/*      */     
/*  191 */     this.tail4 = new ModelRenderer(this, 0, 68);
/*  192 */     this.tail4.addBox(-1.0F, 0.0F, 0.0F, 2, 5, 7);
/*  193 */     this.tail4.setRotationPoint(0.0F, -1.0F, 7.0F);
/*  194 */     this.tail3.addChild(this.tail4);
/*      */     
/*  196 */     this.tail5 = new ModelRenderer(this, 0, 80);
/*  197 */     this.tail5.addBox(-0.5F, 0.0F, 0.0F, 1, 3, 7);
/*  198 */     this.tail5.setRotationPoint(0.0F, 1.0F, 6.0F);
/*  199 */     this.tail4.addChild(this.tail5);
/*      */     
/*  201 */     this.chest = new ModelRenderer(this, 44, 0);
/*  202 */     this.chest.addBox(-4.5F, 2.7F, -13.0F, 9, 10, 4);
/*  203 */     this.chest.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  204 */     setRotation(this.chest, -0.2602503F, 0.0F, 0.0F);
/*      */     
/*  206 */     this.neckplate3 = new ModelRenderer(this, 112, 64);
/*  207 */     this.neckplate3.addBox(-2.0F, -2.0F, -2.0F, 4, 2, 4);
/*  208 */     this.neckplate3.setRotationPoint(0.0F, 0.0F, -12.0F);
/*  209 */     setRotation(this.neckplate3, -0.669215F, 0.0F, 0.0F);
/*      */     
/*  211 */     this.neck3 = new ModelRenderer(this, 100, 113);
/*  212 */     this.neck3.addBox(-3.0F, 0.0F, -2.0F, 6, 7, 8);
/*  213 */     this.neck3.setRotationPoint(0.0F, 0.0F, -12.0F);
/*  214 */     setRotation(this.neck3, -0.669215F, 0.0F, 0.0F);
/*      */ 
/*      */     
/*  217 */     this.MainHead = new ModelRenderer(this);
/*  218 */     this.MainHead.setRotationPoint(0.0F, 3.0F, -15.0F);
/*      */     
/*  220 */     this.neck2 = new ModelRenderer(this, 102, 99);
/*  221 */     this.neck2.addBox(-2.5F, -3.0F, -8.0F, 5, 6, 8);
/*      */     
/*  223 */     this.neck2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*      */     
/*  225 */     this.MainHead.addChild(this.neck2);
/*      */     
/*  227 */     this.neckplate2 = new ModelRenderer(this, 106, 54);
/*  228 */     this.neckplate2.addBox(-1.5F, -2.0F, -8.0F, 3, 2, 8);
/*  229 */     this.neckplate2.setRotationPoint(0.0F, -3.0F, 0.0F);
/*  230 */     this.neck2.addChild(this.neckplate2);
/*      */     
/*  232 */     this.neck1 = new ModelRenderer(this, 104, 85);
/*  233 */     this.neck1.addBox(-2.0F, -3.0F, -8.0F, 4, 6, 8);
/*      */     
/*  235 */     this.neck1.setRotationPoint(0.0F, -0.5F, -5.5F);
/*      */     
/*  237 */     this.neck2.addChild(this.neck1);
/*      */     
/*  239 */     this.neckplate1 = new ModelRenderer(this, 80, 108);
/*  240 */     this.neckplate1.addBox(-1.0F, -2.0F, -8.0F, 2, 2, 8);
/*  241 */     this.neckplate1.setRotationPoint(0.0F, -3.0F, 0.0F);
/*  242 */     this.neck1.addChild(this.neckplate1);
/*      */     
/*  244 */     this.head = new ModelRenderer(this, 98, 70);
/*  245 */     this.head.addBox(-3.5F, -3.5F, -8.0F, 7, 7, 8);
/*  246 */     this.head.setRotationPoint(0.0F, 0.0F, -7.0F);
/*      */     
/*  248 */     this.neck1.addChild(this.head);
/*      */     
/*  250 */     this.snout = new ModelRenderer(this, 72, 70);
/*  251 */     this.snout.addBox(-2.0F, -1.5F, -9.0F, 4, 3, 9);
/*  252 */     this.snout.setRotationPoint(0.0F, -1.5F, -8.0F);
/*  253 */     setRotation(this.snout, 2.0F / this.radianF, 0.0F, 0.0F);
/*  254 */     this.head.addChild(this.snout);
/*      */     
/*  256 */     this.headplate = new ModelRenderer(this, 80, 118);
/*  257 */     this.headplate.addBox(-1.0F, -1.0F, -4.0F, 2, 2, 8);
/*  258 */     this.headplate.setRotationPoint(0.0F, -3.0F, -1.0F);
/*  259 */     setRotation(this.headplate, 10.0F / this.radianF, 0.0F, 0.0F);
/*  260 */     this.head.addChild(this.headplate);
/*      */     
/*  262 */     this.beak = new ModelRenderer(this, 60, 85);
/*  263 */     this.beak.addBox(-1.5F, -2.5F, -1.5F, 3, 5, 3);
/*  264 */     this.beak.setRotationPoint(0.0F, 0.8F, -8.0F);
/*  265 */     setRotation(this.beak, -6.0F / this.radianF, 45.0F / this.radianF, -6.0F / this.radianF);
/*  266 */     this.snout.addChild(this.beak);
/*      */     
/*  268 */     this.righteyesock = new ModelRenderer(this, 70, 108);
/*  269 */     this.righteyesock.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
/*  270 */     this.righteyesock.setRotationPoint(-3.5F, -2.5F, -8.0F);
/*  271 */     this.head.addChild(this.righteyesock);
/*      */     
/*  273 */     this.lefteyesock = new ModelRenderer(this, 70, 114);
/*  274 */     this.lefteyesock.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
/*  275 */     this.lefteyesock.setRotationPoint(2.5F, -2.5F, -8.0F);
/*  276 */     this.head.addChild(this.lefteyesock);
/*      */     
/*  278 */     this.Jaw = new ModelRenderer(this, 72, 82);
/*  279 */     this.Jaw.addBox(-2.0F, -1.0F, -9.0F, 4, 2, 9);
/*  280 */     this.Jaw.setRotationPoint(0.0F, 2.5F, -7.5F);
/*  281 */     setRotation(this.Jaw, -10.0F / this.radianF, 0.0F, 0.0F);
/*  282 */     this.head.addChild(this.Jaw);
/*      */     
/*  284 */     this.leftupjaw = new ModelRenderer(this, 42, 93);
/*  285 */     this.leftupjaw.addBox(-1.0F, -1.0F, -6.5F, 2, 2, 13);
/*  286 */     this.leftupjaw.setRotationPoint(2.0F, 0.0F, -10.5F);
/*  287 */     setRotation(this.leftupjaw, -10.0F / this.radianF, 10.0F / this.radianF, 0.0F);
/*  288 */     this.head.addChild(this.leftupjaw);
/*      */     
/*  290 */     this.rightupjaw = new ModelRenderer(this, 72, 93);
/*  291 */     this.rightupjaw.addBox(-1.0F, -1.0F, -6.5F, 2, 2, 13);
/*  292 */     this.rightupjaw.setRotationPoint(-2.0F, 0.0F, -10.5F);
/*  293 */     setRotation(this.rightupjaw, -10.0F / this.radianF, -10.0F / this.radianF, 0.0F);
/*  294 */     this.head.addChild(this.rightupjaw);
/*      */     
/*  296 */     this.mouthrod = new ModelRenderer(this, 104, 50);
/*  297 */     this.mouthrod.addBox(-5.0F, -1.0F, -1.0F, 10, 2, 2);
/*  298 */     this.mouthrod.setRotationPoint(0.0F, 1.0F, -8.0F);
/*  299 */     this.head.addChild(this.mouthrod);
/*      */     
/*  301 */     this.helmetstrap1 = new ModelRenderer(this, 32, 146);
/*  302 */     this.helmetstrap1.addBox(-4.0F, -2.0F, 0.0F, 8, 4, 1);
/*  303 */     this.helmetstrap1.setRotationPoint(0.0F, 2.0F, -7.5F);
/*  304 */     this.head.addChild(this.helmetstrap1);
/*      */     
/*  306 */     this.helmetstrap2 = new ModelRenderer(this, 32, 141);
/*  307 */     this.helmetstrap2.addBox(-4.0F, -2.0F, 0.0F, 8, 4, 1);
/*  308 */     this.helmetstrap2.setRotationPoint(0.0F, 2.0F, -3.5F);
/*  309 */     this.head.addChild(this.helmetstrap2);
/*      */     
/*  311 */     this.controlrope1 = new ModelRenderer(this, 66, 43);
/*  312 */     this.controlrope1.addBox(0.0F, -2.0F, 0.0F, 0, 4, 23);
/*  313 */     this.controlrope1.setRotationPoint(4.5F, 1.0F, 0.0F);
/*  314 */     this.mouthrod.addChild(this.controlrope1);
/*      */     
/*  316 */     this.controlrope2 = new ModelRenderer(this, 66, 43);
/*  317 */     this.controlrope2.addBox(0.0F, -2.0F, 0.0F, 0, 4, 23);
/*  318 */     this.controlrope2.setRotationPoint(-4.5F, 1.0F, 0.0F);
/*  319 */     this.mouthrod.addChild(this.controlrope2);
/*      */     
/*  321 */     this.rightearskin = new ModelRenderer(this, 112, 201);
/*  322 */     this.rightearskin.addBox(0.0F, -4.0F, 0.0F, 0, 8, 8);
/*  323 */     this.rightearskin.setRotationPoint(-3.0F, -0.5F, 0.0F);
/*  324 */     this.head.addChild(this.rightearskin);
/*      */     
/*  326 */     this.leftearskin = new ModelRenderer(this, 96, 201);
/*  327 */     this.leftearskin.addBox(0.0F, -4.0F, 0.0F, 0, 8, 8);
/*  328 */     this.leftearskin.setRotationPoint(3.0F, -0.5F, 0.0F);
/*  329 */     this.head.addChild(this.leftearskin);
/*      */     
/*  331 */     this.rightspine1 = new ModelRenderer(this, 50, 141);
/*  332 */     this.rightspine1.addBox(-0.5F, -1.0F, 0.0F, 1, 2, 8);
/*  333 */     this.rightspine1.setRotationPoint(0.0F, -2.0F, 0.0F);
/*  334 */     setRotation(this.rightspine1, 15.0F / this.radianF, 0.0F, 0.0F);
/*  335 */     this.rightearskin.addChild(this.rightspine1);
/*      */     
/*  337 */     this.rightspine2 = new ModelRenderer(this, 50, 141);
/*  338 */     this.rightspine2.addBox(-0.5F, -1.0F, 0.0F, 1, 2, 8);
/*  339 */     this.rightspine2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*      */     
/*  341 */     this.rightearskin.addChild(this.rightspine2);
/*      */     
/*  343 */     this.rightspine3 = new ModelRenderer(this, 50, 141);
/*  344 */     this.rightspine3.addBox(-0.5F, -1.0F, 0.0F, 1, 2, 8);
/*  345 */     this.rightspine3.setRotationPoint(0.0F, 2.0F, 0.0F);
/*  346 */     setRotation(this.rightspine3, -15.0F / this.radianF, 0.0F, 0.0F);
/*  347 */     this.rightearskin.addChild(this.rightspine3);
/*      */     
/*  349 */     this.leftspine1 = new ModelRenderer(this, 68, 141);
/*  350 */     this.leftspine1.addBox(-0.5F, -1.0F, 0.0F, 1, 2, 8);
/*  351 */     this.leftspine1.setRotationPoint(0.0F, -2.0F, 0.0F);
/*  352 */     setRotation(this.leftspine1, 15.0F / this.radianF, 0.0F, 0.0F);
/*  353 */     this.leftearskin.addChild(this.leftspine1);
/*      */     
/*  355 */     this.leftspine2 = new ModelRenderer(this, 68, 141);
/*  356 */     this.leftspine2.addBox(-0.5F, -1.0F, 0.0F, 1, 2, 8);
/*  357 */     this.leftspine2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  358 */     this.leftearskin.addChild(this.leftspine2);
/*      */     
/*  360 */     this.leftspine3 = new ModelRenderer(this, 68, 141);
/*  361 */     this.leftspine3.addBox(-0.5F, -1.0F, 0.0F, 1, 2, 8);
/*  362 */     this.leftspine3.setRotationPoint(0.0F, 2.0F, 0.0F);
/*  363 */     setRotation(this.leftspine3, -15.0F / this.radianF, 0.0F, 0.0F);
/*  364 */     this.leftearskin.addChild(this.leftspine3);
/*      */ 
/*      */     
/*  367 */     this.ironhelmethorn1 = new ModelRenderer(this, 106, 139);
/*  368 */     this.ironhelmethorn1.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 8);
/*  369 */     this.ironhelmethorn1.setRotationPoint(-0.5F, 0.0F, 0.1F);
/*  370 */     this.leftspine1.addChild(this.ironhelmethorn1);
/*      */     
/*  372 */     this.ironhelmethorn2 = new ModelRenderer(this, 106, 128);
/*  373 */     this.ironhelmethorn2.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 8);
/*  374 */     this.ironhelmethorn2.setRotationPoint(0.5F, 0.0F, 0.1F);
/*  375 */     this.rightspine1.addChild(this.ironhelmethorn2);
/*      */     
/*  377 */     this.ironhelmet = new ModelRenderer(this, 32, 128);
/*  378 */     this.ironhelmet.addBox(-4.0F, -4.0F, -9.0F, 8, 4, 9);
/*  379 */     this.ironhelmet.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  380 */     this.head.addChild(this.ironhelmet);
/*      */     
/*  382 */     this.ironhelmetsnout = new ModelRenderer(this, 0, 144);
/*  383 */     this.ironhelmetsnout.addBox(-2.5F, -2.0F, -7.0F, 5, 2, 7);
/*  384 */     this.ironhelmetsnout.setRotationPoint(0.0F, 0.0F, -1.0F);
/*  385 */     this.snout.addChild(this.ironhelmetsnout);
/*      */     
/*  387 */     this.goldhelmethorn1 = new ModelRenderer(this, 106, 161);
/*  388 */     this.goldhelmethorn1.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 8);
/*  389 */     this.goldhelmethorn1.setRotationPoint(-0.5F, 0.0F, 0.1F);
/*  390 */     this.leftspine1.addChild(this.goldhelmethorn1);
/*      */     
/*  392 */     this.goldhelmethorn2 = new ModelRenderer(this, 106, 150);
/*  393 */     this.goldhelmethorn2.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 8);
/*  394 */     this.goldhelmethorn2.setRotationPoint(0.5F, 0.0F, 0.1F);
/*  395 */     this.rightspine1.addChild(this.goldhelmethorn2);
/*      */     
/*  397 */     this.goldhelmet = new ModelRenderer(this, 94, 226);
/*  398 */     this.goldhelmet.addBox(-4.0F, -4.0F, -9.0F, 8, 4, 9);
/*  399 */     this.goldhelmet.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  400 */     this.head.addChild(this.goldhelmet);
/*      */     
/*  402 */     this.goldhelmetsnout = new ModelRenderer(this, 71, 235);
/*  403 */     this.goldhelmetsnout.addBox(-2.5F, -2.0F, -7.0F, 5, 2, 7);
/*  404 */     this.goldhelmetsnout.setRotationPoint(0.0F, 0.0F, -1.0F);
/*  405 */     this.snout.addChild(this.goldhelmetsnout);
/*      */     
/*  407 */     this.diamondhelmet = new ModelRenderer(this, 23, 226);
/*  408 */     this.diamondhelmet.addBox(-4.0F, -4.0F, -9.0F, 8, 4, 9);
/*  409 */     this.diamondhelmet.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  410 */     this.head.addChild(this.diamondhelmet);
/*      */     
/*  412 */     this.diamondhelmethorn2 = new ModelRenderer(this, 49, 234);
/*  413 */     this.diamondhelmethorn2.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 8);
/*  414 */     this.diamondhelmethorn2.setRotationPoint(0.5F, 0.0F, 0.1F);
/*  415 */     this.rightspine1.addChild(this.diamondhelmethorn2);
/*      */     
/*  417 */     this.diamondhelmethorn1 = new ModelRenderer(this, 49, 245);
/*  418 */     this.diamondhelmethorn1.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 8);
/*  419 */     this.diamondhelmethorn1.setRotationPoint(-0.5F, 0.0F, 0.1F);
/*  420 */     this.leftspine1.addChild(this.diamondhelmethorn1);
/*      */     
/*  422 */     this.diamondhelmetsnout = new ModelRenderer(this, 0, 235);
/*  423 */     this.diamondhelmetsnout.addBox(-2.5F, -2.0F, -7.0F, 5, 2, 7);
/*  424 */     this.diamondhelmetsnout.setRotationPoint(0.0F, 0.0F, -1.0F);
/*  425 */     this.snout.addChild(this.diamondhelmetsnout);
/*      */     
/*  427 */     this.torso = new ModelRenderer(this, 0, 0);
/*  428 */     this.torso.addBox(-5.0F, 0.0F, -12.0F, 10, 10, 12);
/*  429 */     this.torso.setRotationPoint(0.0F, 0.0F, 0.0F);
/*      */     
/*  431 */     this.saddle = new ModelRenderer(this, 38, 70);
/*  432 */     this.saddle.addBox(-3.5F, -2.5F, -8.0F, 7, 3, 10);
/*  433 */     this.saddle.setRotationPoint(0.0F, 0.0F, 0.0F);
/*      */     
/*  435 */     this.rightshoulder = new ModelRenderer(this, 42, 83);
/*  436 */     this.rightshoulder.addBox(-6.0F, 1.0F, -12.5F, 4, 5, 5);
/*  437 */     this.rightshoulder.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  438 */     setRotation(this.rightshoulder, -0.2617994F, 0.0F, 0.0F);
/*      */     
/*  440 */     this.leftshoulder = new ModelRenderer(this, 24, 83);
/*  441 */     this.leftshoulder.addBox(2.0F, 1.0F, -12.5F, 4, 5, 5);
/*  442 */     this.leftshoulder.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  443 */     setRotation(this.leftshoulder, -0.2617994F, 0.0F, 0.0F);
/*      */ 
/*      */     
/*  446 */     this.LeftWing = new ModelRenderer(this);
/*  447 */     this.LeftWing.setRotationPoint(4.0F, 1.0F, -11.0F);
/*      */     
/*  449 */     this.leftuparm = new ModelRenderer(this, 44, 14);
/*  450 */     this.leftuparm.addBox(0.0F, -2.0F, -2.0F, 10, 4, 4);
/*  451 */     this.leftuparm.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  452 */     setRotation(this.leftuparm, 0.0F, -10.0F / this.radianF, 0.0F);
/*  453 */     this.LeftWing.addChild(this.leftuparm);
/*      */     
/*  455 */     this.leftlowarm = new ModelRenderer(this, 72, 14);
/*  456 */     this.leftlowarm.addBox(0.0F, -2.0F, -2.0F, 10, 4, 4);
/*  457 */     this.leftlowarm.setRotationPoint(9.0F, 0.0F, 0.0F);
/*  458 */     setRotation(this.leftlowarm, 0.0F, 10.0F / this.radianF, 0.0F);
/*  459 */     this.leftuparm.addChild(this.leftlowarm);
/*      */     
/*  461 */     this.leftfing1a = new ModelRenderer(this, 52, 30);
/*  462 */     this.leftfing1a.addBox(0.0F, 0.0F, -1.0F, 2, 15, 2);
/*  463 */     this.leftfing1a.setRotationPoint(9.0F, 1.0F, 0.0F);
/*  464 */     setRotation(this.leftfing1a, 90.0F / this.radianF, 70.0F / this.radianF, 0.0F);
/*  465 */     this.leftlowarm.addChild(this.leftfing1a);
/*      */     
/*  467 */     this.leftfing1b = new ModelRenderer(this, 52, 47);
/*  468 */     this.leftfing1b.addBox(0.0F, 0.0F, -1.0F, 2, 10, 2);
/*  469 */     this.leftfing1b.setRotationPoint(0.0F, 14.0F, 0.0F);
/*  470 */     setRotation(this.leftfing1b, 0.0F, 0.0F, 35.0F / this.radianF);
/*  471 */     this.leftfing1a.addChild(this.leftfing1b);
/*      */     
/*  473 */     this.leftfing2a = new ModelRenderer(this, 44, 30);
/*  474 */     this.leftfing2a.addBox(-1.0F, 0.0F, 0.0F, 2, 15, 2);
/*  475 */     this.leftfing2a.setRotationPoint(9.0F, 1.0F, 0.0F);
/*  476 */     setRotation(this.leftfing2a, 90.0F / this.radianF, 35.0F / this.radianF, 0.0F);
/*  477 */     this.leftlowarm.addChild(this.leftfing2a);
/*      */     
/*  479 */     this.leftfing2b = new ModelRenderer(this, 44, 47);
/*  480 */     this.leftfing2b.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
/*  481 */     this.leftfing2b.setRotationPoint(0.0F, 14.0F, 0.0F);
/*  482 */     setRotation(this.leftfing2b, 0.0F, 0.0F, 30.0F / this.radianF);
/*  483 */     this.leftfing2a.addChild(this.leftfing2b);
/*      */     
/*  485 */     this.leftfing3a = new ModelRenderer(this, 36, 30);
/*  486 */     this.leftfing3a.addBox(-1.0F, 0.0F, 1.0F, 2, 15, 2);
/*  487 */     this.leftfing3a.setRotationPoint(9.0F, 1.0F, 0.0F);
/*  488 */     setRotation(this.leftfing3a, 90.0F / this.radianF, -5.0F / this.radianF, 0.0F);
/*  489 */     this.leftlowarm.addChild(this.leftfing3a);
/*      */     
/*  491 */     this.leftfing3b = new ModelRenderer(this, 36, 47);
/*  492 */     this.leftfing3b.addBox(-1.0F, 0.0F, 1.0F, 2, 10, 2);
/*  493 */     this.leftfing3b.setRotationPoint(0.0F, 14.0F, 0.0F);
/*  494 */     setRotation(this.leftfing3b, 0.0F, 0.0F, 30.0F / this.radianF);
/*  495 */     this.leftfing3a.addChild(this.leftfing3b);
/*      */     
/*  497 */     this.leftwingflap1 = new ModelRenderer(this, 74, 153);
/*  498 */     this.leftwingflap1.addBox(3.5F, -3.0F, 0.95F, 14, 24, 0);
/*  499 */     this.leftwingflap1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  500 */     setRotation(this.leftwingflap1, 0.0F, 0.0F, 70.0F / this.radianF);
/*  501 */     this.leftfing1a.addChild(this.leftwingflap1);
/*      */     
/*  503 */     this.leftwingflap2 = new ModelRenderer(this, 36, 153);
/*  504 */     this.leftwingflap2.addBox(-7.0F, 1.05F, 1.05F, 19, 24, 0);
/*  505 */     this.leftwingflap2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  506 */     setRotation(this.leftwingflap2, 0.0F, 0.0F, 40.0F / this.radianF);
/*  507 */     this.leftfing2a.addChild(this.leftwingflap2);
/*      */     
/*  509 */     this.leftwingflap3 = new ModelRenderer(this, 0, 153);
/*  510 */     this.leftwingflap3.addBox(-17.5F, 1.0F, 1.1F, 18, 24, 0);
/*  511 */     this.leftwingflap3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  512 */     this.leftfing3a.addChild(this.leftwingflap3);
/*      */ 
/*      */     
/*  515 */     this.RightWing = new ModelRenderer(this);
/*  516 */     this.RightWing.setRotationPoint(-4.0F, 1.0F, -11.0F);
/*      */     
/*  518 */     this.rightuparm = new ModelRenderer(this, 44, 22);
/*  519 */     this.rightuparm.addBox(-10.0F, -2.0F, -2.0F, 10, 4, 4);
/*  520 */     this.rightuparm.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  521 */     setRotation(this.rightuparm, 0.0F, 10.0F / this.radianF, 0.0F);
/*  522 */     this.RightWing.addChild(this.rightuparm);
/*      */     
/*  524 */     this.rightlowarm = new ModelRenderer(this, 72, 22);
/*  525 */     this.rightlowarm.addBox(-10.0F, -2.0F, -2.0F, 10, 4, 4);
/*  526 */     this.rightlowarm.setRotationPoint(-9.0F, 0.0F, 0.0F);
/*  527 */     setRotation(this.rightlowarm, 0.0F, -10.0F / this.radianF, 0.0F);
/*  528 */     this.rightuparm.addChild(this.rightlowarm);
/*      */     
/*  530 */     this.rightfing1a = new ModelRenderer(this, 36, 30);
/*  531 */     this.rightfing1a.addBox(-1.0F, 0.0F, -1.0F, 2, 15, 2);
/*  532 */     this.rightfing1a.setRotationPoint(-9.0F, 1.0F, -1.0F);
/*  533 */     setRotation(this.rightfing1a, 90.0F / this.radianF, -70.0F / this.radianF, 0.0F);
/*  534 */     this.rightlowarm.addChild(this.rightfing1a);
/*      */     
/*  536 */     this.rightfing1b = new ModelRenderer(this, 36, 47);
/*  537 */     this.rightfing1b.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2);
/*  538 */     this.rightfing1b.setRotationPoint(0.0F, 14.0F, 0.0F);
/*  539 */     setRotation(this.rightfing1b, 0.0F, 0.0F, -35.0F / this.radianF);
/*  540 */     this.rightfing1a.addChild(this.rightfing1b);
/*      */     
/*  542 */     this.rightwingflap1 = new ModelRenderer(this, 74, 177);
/*  543 */     this.rightwingflap1.addBox(-17.5F, -3.0F, 0.95F, 14, 24, 0);
/*  544 */     this.rightwingflap1.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  545 */     setRotation(this.rightwingflap1, 0.0F, 0.0F, -70.0F / this.radianF);
/*  546 */     this.rightfing1a.addChild(this.rightwingflap1);
/*      */     
/*  548 */     this.rightfing2a = new ModelRenderer(this, 44, 30);
/*  549 */     this.rightfing2a.addBox(-1.0F, 0.0F, 0.0F, 2, 15, 2);
/*  550 */     this.rightfing2a.setRotationPoint(-9.0F, 1.0F, 0.0F);
/*  551 */     setRotation(this.rightfing2a, 90.0F / this.radianF, -35.0F / this.radianF, 0.0F);
/*  552 */     this.rightlowarm.addChild(this.rightfing2a);
/*      */     
/*  554 */     this.rightfing2b = new ModelRenderer(this, 44, 47);
/*  555 */     this.rightfing2b.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
/*  556 */     this.rightfing2b.setRotationPoint(0.0F, 14.0F, 0.0F);
/*  557 */     setRotation(this.rightfing2b, 0.0F, 0.0F, -30.0F / this.radianF);
/*  558 */     this.rightfing2a.addChild(this.rightfing2b);
/*      */     
/*  560 */     this.rightwingflap2 = new ModelRenderer(this, 36, 177);
/*  561 */     this.rightwingflap2.addBox(-19.0F, 1.05F, 1.05F, 19, 24, 0);
/*  562 */     this.rightwingflap2.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  563 */     setRotation(this.rightwingflap2, 0.0F, 0.0F, -40.0F / this.radianF);
/*  564 */     this.rightfing2a.addChild(this.rightwingflap2);
/*      */     
/*  566 */     this.rightfing3a = new ModelRenderer(this, 52, 30);
/*  567 */     this.rightfing3a.addBox(-1.0F, 0.0F, 1.0F, 2, 15, 2);
/*  568 */     this.rightfing3a.setRotationPoint(-9.0F, 1.0F, 0.0F);
/*  569 */     setRotation(this.rightfing3a, 90.0F / this.radianF, 5.0F / this.radianF, 0.0F);
/*  570 */     this.rightlowarm.addChild(this.rightfing3a);
/*      */     
/*  572 */     this.rightfing3b = new ModelRenderer(this, 52, 47);
/*  573 */     this.rightfing3b.addBox(-1.0F, 0.0F, 1.0F, 2, 10, 2);
/*  574 */     this.rightfing3b.setRotationPoint(0.0F, 14.0F, 0.0F);
/*  575 */     setRotation(this.rightfing3b, 0.0F, 0.0F, -30.0F / this.radianF);
/*  576 */     this.rightfing3a.addChild(this.rightfing3b);
/*      */     
/*  578 */     this.rightwingflap3 = new ModelRenderer(this, 0, 177);
/*  579 */     this.rightwingflap3.addBox(-0.5F, 1.0F, 1.1F, 18, 24, 0);
/*  580 */     this.rightwingflap3.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  581 */     this.rightfing3a.addChild(this.rightwingflap3);
/*      */ 
/*      */     
/*  584 */     this.leftupleg = new ModelRenderer(this, 0, 111);
/*  585 */     this.leftupleg.addBox(-2.0F, -3.0F, -3.0F, 4, 10, 7);
/*  586 */     this.leftupleg.setRotationPoint(5.0F, 6.0F, -5.0F);
/*  587 */     setRotation(this.leftupleg, -25.0F / this.radianF, 0.0F, 0.0F);
/*      */     
/*  589 */     this.leftmidleg = new ModelRenderer(this, 0, 102);
/*  590 */     this.leftmidleg.addBox(-1.5F, -2.0F, 0.0F, 3, 4, 5);
/*  591 */     this.leftmidleg.setRotationPoint(0.0F, 5.0F, 4.0F);
/*  592 */     this.leftupleg.addChild(this.leftmidleg);
/*      */     
/*  594 */     this.leftlowleg = new ModelRenderer(this, 0, 91);
/*  595 */     this.leftlowleg.addBox(-1.5F, 0.0F, -1.5F, 3, 8, 3);
/*  596 */     this.leftlowleg.setRotationPoint(0.0F, 2.0F, 3.5F);
/*  597 */     this.leftmidleg.addChild(this.leftlowleg);
/*      */     
/*  599 */     this.leftfoot = new ModelRenderer(this, 44, 121);
/*  600 */     this.leftfoot.addBox(-2.0F, -1.0F, -3.0F, 4, 3, 4);
/*  601 */     this.leftfoot.setRotationPoint(0.0F, 7.0F, 0.5F);
/*  602 */     setRotation(this.leftfoot, 25.0F / this.radianF, 0.0F, 0.0F);
/*  603 */     this.leftlowleg.addChild(this.leftfoot);
/*      */     
/*  605 */     this.lefttoe1 = new ModelRenderer(this, 96, 35);
/*  606 */     this.lefttoe1.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3);
/*  607 */     this.lefttoe1.setRotationPoint(-1.5F, 1.0F, -3.0F);
/*  608 */     this.leftfoot.addChild(this.lefttoe1);
/*      */     
/*  610 */     this.lefttoe3 = new ModelRenderer(this, 96, 30);
/*  611 */     this.lefttoe3.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3);
/*  612 */     this.lefttoe3.setRotationPoint(1.5F, 1.0F, -3.0F);
/*  613 */     this.leftfoot.addChild(this.lefttoe3);
/*      */     
/*  615 */     this.lefttoe2 = new ModelRenderer(this, 84, 30);
/*  616 */     this.lefttoe2.addBox(-1.0F, -1.5F, -4.0F, 2, 3, 4);
/*  617 */     this.lefttoe2.setRotationPoint(0.0F, 0.5F, -3.0F);
/*  618 */     this.leftfoot.addChild(this.lefttoe2);
/*      */     
/*  620 */     this.leftclaw1 = new ModelRenderer(this, 100, 26);
/*  621 */     this.leftclaw1.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
/*  622 */     this.leftclaw1.setRotationPoint(0.5F, -0.5F, -2.5F);
/*  623 */     setRotation(this.leftclaw1, -25.0F / this.radianF, 0.0F, 0.0F);
/*  624 */     this.lefttoe1.addChild(this.leftclaw1);
/*      */     
/*  626 */     this.leftclaw2 = new ModelRenderer(this, 100, 26);
/*  627 */     this.leftclaw2.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
/*  628 */     this.leftclaw2.setRotationPoint(0.0F, -1.0F, -3.5F);
/*  629 */     setRotation(this.leftclaw2, -25.0F / this.radianF, 0.0F, 0.0F);
/*  630 */     this.lefttoe2.addChild(this.leftclaw2);
/*      */     
/*  632 */     this.leftclaw3 = new ModelRenderer(this, 100, 26);
/*  633 */     this.leftclaw3.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
/*  634 */     this.leftclaw3.setRotationPoint(-0.5F, -0.5F, -2.5F);
/*  635 */     setRotation(this.leftclaw3, -25.0F / this.radianF, 0.0F, 0.0F);
/*  636 */     this.lefttoe3.addChild(this.leftclaw3);
/*      */     
/*  638 */     this.ironleftlegarmor = new ModelRenderer(this, 39, 97);
/*  639 */     this.ironleftlegarmor.addBox(-2.0F, -2.5F, -2.0F, 4, 5, 4);
/*  640 */     this.ironleftlegarmor.setRotationPoint(0.0F, 2.5F, 0.0F);
/*  641 */     this.leftlowleg.addChild(this.ironleftlegarmor);
/*      */     
/*  643 */     this.goldleftlegarmor = new ModelRenderer(this, 112, 181);
/*  644 */     this.goldleftlegarmor.addBox(-2.0F, -2.5F, -2.0F, 4, 5, 4);
/*  645 */     this.goldleftlegarmor.setRotationPoint(0.0F, 2.5F, 0.0F);
/*  646 */     this.leftlowleg.addChild(this.goldleftlegarmor);
/*      */     
/*  648 */     this.diamondleftlegarmor = new ModelRenderer(this, 43, 215);
/*  649 */     this.diamondleftlegarmor.addBox(-2.0F, -2.5F, -2.0F, 4, 5, 4);
/*  650 */     this.diamondleftlegarmor.setRotationPoint(0.0F, 2.5F, 0.0F);
/*  651 */     this.leftlowleg.addChild(this.diamondleftlegarmor);
/*      */ 
/*      */     
/*  654 */     this.rightupleg = new ModelRenderer(this, 0, 111);
/*  655 */     this.rightupleg.addBox(-2.0F, -3.0F, -3.0F, 4, 10, 7);
/*  656 */     this.rightupleg.setRotationPoint(-5.0F, 6.0F, -5.0F);
/*  657 */     setRotation(this.rightupleg, -25.0F / this.radianF, 0.0F, 0.0F);
/*      */     
/*  659 */     this.rightmidleg = new ModelRenderer(this, 0, 102);
/*  660 */     this.rightmidleg.addBox(-1.5F, -2.0F, 0.0F, 3, 4, 5);
/*  661 */     this.rightmidleg.setRotationPoint(0.0F, 5.0F, 4.0F);
/*  662 */     this.rightupleg.addChild(this.rightmidleg);
/*      */     
/*  664 */     this.rightlowleg = new ModelRenderer(this, 0, 91);
/*  665 */     this.rightlowleg.addBox(-1.5F, 0.0F, -1.5F, 3, 8, 3);
/*  666 */     this.rightlowleg.setRotationPoint(0.0F, 2.0F, 3.5F);
/*  667 */     this.rightmidleg.addChild(this.rightlowleg);
/*      */     
/*  669 */     this.rightfoot = new ModelRenderer(this, 44, 121);
/*  670 */     this.rightfoot.addBox(-2.0F, -1.0F, -3.0F, 4, 3, 4);
/*  671 */     this.rightfoot.setRotationPoint(0.0F, 7.0F, 0.5F);
/*  672 */     setRotation(this.rightfoot, 25.0F / this.radianF, 0.0F, 0.0F);
/*  673 */     this.rightlowleg.addChild(this.rightfoot);
/*      */     
/*  675 */     this.righttoe1 = new ModelRenderer(this, 96, 35);
/*  676 */     this.righttoe1.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3);
/*  677 */     this.righttoe1.setRotationPoint(-1.5F, 1.0F, -3.0F);
/*  678 */     this.rightfoot.addChild(this.righttoe1);
/*      */     
/*  680 */     this.righttoe3 = new ModelRenderer(this, 96, 30);
/*  681 */     this.righttoe3.addBox(-0.5F, -1.0F, -3.0F, 1, 2, 3);
/*  682 */     this.righttoe3.setRotationPoint(1.5F, 1.0F, -3.0F);
/*  683 */     this.rightfoot.addChild(this.righttoe3);
/*      */     
/*  685 */     this.righttoe2 = new ModelRenderer(this, 84, 30);
/*  686 */     this.righttoe2.addBox(-1.0F, -1.5F, -4.0F, 2, 3, 4);
/*  687 */     this.righttoe2.setRotationPoint(0.0F, 0.5F, -3.0F);
/*  688 */     this.rightfoot.addChild(this.righttoe2);
/*      */     
/*  690 */     this.rightclaw1 = new ModelRenderer(this, 100, 26);
/*  691 */     this.rightclaw1.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
/*  692 */     this.rightclaw1.setRotationPoint(0.5F, -0.5F, -2.5F);
/*  693 */     setRotation(this.rightclaw1, -25.0F / this.radianF, 0.0F, 0.0F);
/*  694 */     this.righttoe1.addChild(this.rightclaw1);
/*      */     
/*  696 */     this.rightclaw2 = new ModelRenderer(this, 100, 26);
/*  697 */     this.rightclaw2.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
/*  698 */     this.rightclaw2.setRotationPoint(0.0F, -1.0F, -3.5F);
/*  699 */     setRotation(this.rightclaw2, -25.0F / this.radianF, 0.0F, 0.0F);
/*  700 */     this.righttoe2.addChild(this.rightclaw2);
/*      */     
/*  702 */     this.rightclaw3 = new ModelRenderer(this, 100, 26);
/*  703 */     this.rightclaw3.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
/*  704 */     this.rightclaw3.setRotationPoint(-0.5F, -0.5F, -2.5F);
/*  705 */     setRotation(this.rightclaw3, -25.0F / this.radianF, 0.0F, 0.0F);
/*  706 */     this.righttoe3.addChild(this.rightclaw3);
/*      */     
/*  708 */     this.ironrightlegarmor = new ModelRenderer(this, 39, 97);
/*  709 */     this.ironrightlegarmor.addBox(-2.0F, -2.5F, -2.0F, 4, 5, 4);
/*  710 */     this.ironrightlegarmor.setRotationPoint(0.0F, 2.5F, 0.0F);
/*  711 */     this.rightlowleg.addChild(this.ironrightlegarmor);
/*      */     
/*  713 */     this.goldrightlegarmor = new ModelRenderer(this, 112, 181);
/*  714 */     this.goldrightlegarmor.addBox(-2.0F, -2.5F, -2.0F, 4, 5, 4);
/*  715 */     this.goldrightlegarmor.setRotationPoint(0.0F, 2.5F, 0.0F);
/*  716 */     this.rightlowleg.addChild(this.goldrightlegarmor);
/*      */     
/*  718 */     this.diamondrightlegarmor = new ModelRenderer(this, 43, 215);
/*  719 */     this.diamondrightlegarmor.addBox(-2.0F, -2.5F, -2.0F, 4, 5, 4);
/*  720 */     this.diamondrightlegarmor.setRotationPoint(0.0F, 2.5F, 0.0F);
/*  721 */     this.rightlowleg.addChild(this.diamondrightlegarmor);
/*      */     
/*  723 */     this.storage = new ModelRenderer(this, 28, 59);
/*  724 */     this.storage.addBox(-5.0F, -4.5F, 1.5F, 10, 5, 6);
/*  725 */     this.storage.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  726 */     setRotation(this.storage, -0.2268928F, 0.0F, 0.0F);
/*      */     
/*  728 */     this.chestbelt = new ModelRenderer(this, 0, 201);
/*  729 */     this.chestbelt.addBox(-5.5F, -0.5F, -9.0F, 11, 11, 2);
/*  730 */     this.chestbelt.setRotationPoint(0.0F, 0.0F, 0.0F);
/*      */     
/*  732 */     this.stomachbelt = new ModelRenderer(this, 0, 201);
/*  733 */     this.stomachbelt.addBox(-5.5F, -0.5F, -3.0F, 11, 11, 2);
/*  734 */     this.stomachbelt.setRotationPoint(0.0F, 0.0F, 0.0F);
/*      */     
/*  736 */     this.ironchestarmor = new ModelRenderer(this, 0, 128);
/*  737 */     this.ironchestarmor.addBox(-5.5F, 2.2F, -13.5F, 11, 11, 5);
/*  738 */     this.ironchestarmor.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  739 */     setRotation(this.ironchestarmor, -0.2602503F, 0.0F, 0.0F);
/*      */     
/*  741 */     this.ironrightshoulderpad = new ModelRenderer(this, 74, 201);
/*  742 */     this.ironrightshoulderpad.addBox(-6.5F, 0.5F, -13.0F, 5, 6, 6);
/*  743 */     this.ironrightshoulderpad.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  744 */     setRotation(this.ironrightshoulderpad, -0.2617994F, 0.0F, 0.0F);
/*      */     
/*  746 */     this.ironleftshoulderpad = new ModelRenderer(this, 26, 201);
/*  747 */     this.ironleftshoulderpad.addBox(1.5F, 0.5F, -13.0F, 5, 6, 6);
/*  748 */     this.ironleftshoulderpad.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  749 */     setRotation(this.ironleftshoulderpad, -0.2617994F, 0.0F, 0.0F);
/*      */     
/*  751 */     this.goldleftshoulder = new ModelRenderer(this, 71, 244);
/*  752 */     this.goldleftshoulder.addBox(1.5F, 0.5F, -13.0F, 5, 6, 6);
/*  753 */     this.goldleftshoulder.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  754 */     setRotation(this.goldleftshoulder, -0.2617994F, 0.0F, 0.0F);
/*      */     
/*  756 */     this.goldchestarmor = new ModelRenderer(this, 71, 219);
/*  757 */     this.goldchestarmor.addBox(-5.5F, 2.2F, -13.5F, 11, 11, 5);
/*  758 */     this.goldchestarmor.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  759 */     setRotation(this.goldchestarmor, -0.2602503F, 0.0F, 0.0F);
/*      */     
/*  761 */     this.goldrightshoulder = new ModelRenderer(this, 93, 244);
/*  762 */     this.goldrightshoulder.addBox(-6.5F, 0.5F, -13.0F, 5, 6, 6);
/*  763 */     this.goldrightshoulder.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  764 */     setRotation(this.goldrightshoulder, -0.2617994F, 0.0F, 0.0F);
/*      */     
/*  766 */     this.diamondleftshoulder = new ModelRenderer(this, 0, 244);
/*  767 */     this.diamondleftshoulder.addBox(1.5F, 0.5F, -13.0F, 5, 6, 6);
/*  768 */     this.diamondleftshoulder.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  769 */     setRotation(this.diamondleftshoulder, -0.2617994F, 0.0F, 0.0F);
/*      */     
/*  771 */     this.diamondrightshoulder = new ModelRenderer(this, 22, 244);
/*  772 */     this.diamondrightshoulder.addBox(-6.5F, 0.5F, -13.0F, 5, 6, 6);
/*  773 */     this.diamondrightshoulder.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  774 */     setRotation(this.diamondrightshoulder, -0.2617994F, 0.0F, 0.0F);
/*      */     
/*  776 */     this.diamondchestarmor = new ModelRenderer(this, 0, 219);
/*  777 */     this.diamondchestarmor.addBox(-5.5F, 2.2F, -13.5F, 11, 11, 5);
/*  778 */     this.diamondchestarmor.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  779 */     setRotation(this.diamondchestarmor, -0.2602503F, 0.0F, 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/*  786 */     MoCEntityWyvern wyvern = (MoCEntityWyvern)entity;
/*  787 */     int armor = wyvern.getArmorType();
/*  788 */     this.isRidden = wyvern.isBeingRidden();
/*  789 */     this.isChested = wyvern.getIsChested();
/*  790 */     this.isSaddled = wyvern.getIsRideable();
/*  791 */     this.flapwings = (wyvern.wingFlapCounter != 0);
/*  792 */     this.onAir = (wyvern.isOnAir() || wyvern.getIsFlying());
/*  793 */     this.diving = (wyvern.diveCounter != 0);
/*  794 */     this.isSitting = wyvern.getIsSitting();
/*  795 */     this.isGhost = wyvern.getIsGhost();
/*  796 */     this.openMouth = wyvern.mouthCounter;
/*      */     
/*  798 */     setRotationAngles(f, f1, f2, f3, f4, f5);
/*  799 */     float yOffset = wyvern.getAdjustedYOffset();
/*  800 */     GL11.glPushMatrix();
/*  801 */     GL11.glTranslatef(0.0F, yOffset, 0.0F);
/*      */     
/*  803 */     if (this.isGhost) {
/*  804 */       float transparency = wyvern.tFloat();
/*  805 */       GL11.glEnable(3042);
/*  806 */       GL11.glBlendFunc(770, 771);
/*  807 */       GL11.glColor4f(0.8F, 0.8F, 0.8F, transparency);
/*      */     } 
/*      */     
/*  810 */     this.back1.render(f5);
/*  811 */     this.Tail.render(f5);
/*      */     
/*  813 */     this.chest.render(f5);
/*      */     
/*  815 */     this.LeftWing.render(f5);
/*  816 */     this.RightWing.render(f5);
/*      */     
/*  818 */     this.rightshoulder.render(f5);
/*  819 */     this.leftshoulder.render(f5);
/*      */     
/*  821 */     this.neckplate3.render(f5);
/*  822 */     this.neck3.render(f5);
/*  823 */     this.torso.render(f5);
/*      */     
/*  825 */     if (this.isChested) {
/*  826 */       this.storage.render(f5);
/*      */     }
/*      */     
/*  829 */     if (this.isSaddled) {
/*  830 */       this.saddle.render(f5);
/*  831 */       this.mouthrod.isHidden = false;
/*  832 */       this.helmetstrap1.isHidden = false;
/*  833 */       this.helmetstrap2.isHidden = false;
/*  834 */       this.chestbelt.render(f5);
/*  835 */       this.stomachbelt.render(f5);
/*      */       
/*  837 */       if (this.isRidden) {
/*  838 */         this.controlrope1.isHidden = false;
/*  839 */         this.controlrope2.isHidden = false;
/*      */       } else {
/*  841 */         this.controlrope1.isHidden = true;
/*  842 */         this.controlrope2.isHidden = true;
/*      */       } 
/*      */     } else {
/*  845 */       this.mouthrod.isHidden = true;
/*  846 */       this.helmetstrap1.isHidden = true;
/*  847 */       this.helmetstrap2.isHidden = true;
/*      */     } 
/*      */     
/*  850 */     this.ironhelmethorn1.isHidden = true;
/*  851 */     this.ironhelmethorn2.isHidden = true;
/*  852 */     this.ironhelmet.isHidden = true;
/*  853 */     this.ironhelmetsnout.isHidden = true;
/*  854 */     this.ironrightlegarmor.isHidden = true;
/*  855 */     this.ironleftlegarmor.isHidden = true;
/*  856 */     this.ironchestarmor.isHidden = true;
/*  857 */     this.ironrightshoulderpad.isHidden = true;
/*  858 */     this.ironleftshoulderpad.isHidden = true;
/*      */     
/*  860 */     this.goldleftshoulder.isHidden = true;
/*  861 */     this.goldchestarmor.isHidden = true;
/*  862 */     this.goldrightshoulder.isHidden = true;
/*  863 */     this.goldleftlegarmor.isHidden = true;
/*  864 */     this.goldrightlegarmor.isHidden = true;
/*  865 */     this.goldhelmethorn1.isHidden = true;
/*  866 */     this.goldhelmethorn2.isHidden = true;
/*  867 */     this.goldhelmet.isHidden = true;
/*  868 */     this.goldhelmetsnout.isHidden = true;
/*      */     
/*  870 */     this.diamondleftshoulder.isHidden = true;
/*  871 */     this.diamondrightshoulder.isHidden = true;
/*  872 */     this.diamondchestarmor.isHidden = true;
/*  873 */     this.diamondleftlegarmor.isHidden = true;
/*  874 */     this.diamondrightlegarmor.isHidden = true;
/*  875 */     this.diamondhelmet.isHidden = true;
/*  876 */     this.diamondhelmethorn2.isHidden = true;
/*  877 */     this.diamondhelmethorn1.isHidden = true;
/*  878 */     this.diamondhelmetsnout.isHidden = true;
/*      */     
/*  880 */     switch (armor) {
/*      */       case 1:
/*  882 */         this.ironhelmethorn1.isHidden = false;
/*  883 */         this.ironhelmethorn2.isHidden = false;
/*  884 */         this.ironhelmet.isHidden = false;
/*  885 */         this.ironhelmetsnout.isHidden = false;
/*  886 */         this.ironrightlegarmor.isHidden = false;
/*  887 */         this.ironleftlegarmor.isHidden = false;
/*  888 */         this.ironchestarmor.isHidden = false;
/*  889 */         this.ironrightshoulderpad.isHidden = false;
/*  890 */         this.ironleftshoulderpad.isHidden = false;
/*      */         break;
/*      */       case 2:
/*  893 */         this.goldleftshoulder.isHidden = false;
/*  894 */         this.goldchestarmor.isHidden = false;
/*  895 */         this.goldrightshoulder.isHidden = false;
/*  896 */         this.goldleftlegarmor.isHidden = false;
/*  897 */         this.goldrightlegarmor.isHidden = false;
/*  898 */         this.goldhelmethorn1.isHidden = false;
/*  899 */         this.goldhelmethorn2.isHidden = false;
/*  900 */         this.goldhelmet.isHidden = false;
/*  901 */         this.goldhelmetsnout.isHidden = false;
/*      */         break;
/*      */       case 3:
/*  904 */         this.diamondleftshoulder.isHidden = false;
/*  905 */         this.diamondrightshoulder.isHidden = false;
/*  906 */         this.diamondchestarmor.isHidden = false;
/*  907 */         this.diamondleftlegarmor.isHidden = false;
/*  908 */         this.diamondrightlegarmor.isHidden = false;
/*  909 */         this.diamondhelmet.isHidden = false;
/*  910 */         this.diamondhelmethorn2.isHidden = false;
/*  911 */         this.diamondhelmethorn1.isHidden = false;
/*  912 */         this.diamondhelmetsnout.isHidden = false;
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  917 */     this.MainHead.render(f5);
/*  918 */     this.leftupleg.render(f5);
/*  919 */     this.rightupleg.render(f5);
/*      */     
/*  921 */     this.ironchestarmor.render(f5);
/*  922 */     this.ironrightshoulderpad.render(f5);
/*  923 */     this.ironleftshoulderpad.render(f5);
/*  924 */     this.goldleftshoulder.render(f5);
/*  925 */     this.goldchestarmor.render(f5);
/*  926 */     this.goldrightshoulder.render(f5);
/*  927 */     this.diamondleftshoulder.render(f5);
/*  928 */     this.diamondrightshoulder.render(f5);
/*  929 */     this.diamondchestarmor.render(f5);
/*  930 */     if (this.isGhost) {
/*  931 */       GL11.glDisable(3042);
/*      */     }
/*  933 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/*  937 */     model.rotateAngleX = x;
/*  938 */     model.rotateAngleY = y;
/*  939 */     model.rotateAngleZ = z;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
/*  944 */     float RLegXRot = MathHelper.cos(f * 0.6662F + 3.141593F) * 0.8F * f1;
/*  945 */     float LLegXRot = MathHelper.cos(f * 0.6662F) * 0.8F * f1;
/*      */     
/*  947 */     f3 = MoCTools.realAngle(f3);
/*      */     
/*  949 */     float f10 = 60.0F;
/*  950 */     if (f3 > f10) {
/*  951 */       f3 = f10;
/*      */     }
/*  953 */     if (f3 < -f10) {
/*  954 */       f3 = -f10;
/*      */     }
/*  956 */     this.neck2.rotateAngleX = -66.0F / this.radianF + f4 * 1.0F / 3.0F / this.radianF;
/*  957 */     this.neck1.rotateAngleX = 30.0F / this.radianF + f4 * 2.0F / 3.0F / this.radianF;
/*      */     
/*  959 */     this.head.rotateAngleX = 45.0F / this.radianF;
/*      */     
/*  961 */     this.neck2.rotateAngleY = f3 * 2.0F / 3.0F / this.radianF;
/*  962 */     this.neck1.rotateAngleY = f3 * 1.0F / 3.0F / this.radianF;
/*      */     
/*  964 */     this.head.rotateAngleY = 0.0F;
/*  965 */     this.head.rotateAngleZ = 0.0F;
/*      */     
/*  967 */     if (this.isRidden) {
/*      */       
/*  969 */       this.neck1.rotateAngleY = 0.0F;
/*  970 */       this.neck2.rotateAngleY = 0.0F;
/*      */       
/*  972 */       if (this.onAir) {
/*  973 */         this.neck1.rotateAngleX = 0.0F;
/*  974 */         this.neck2.rotateAngleX = 0.0F;
/*      */       }
/*      */       else {
/*      */         
/*  978 */         this.neck2.rotateAngleX = -66.0F / this.radianF + RLegXRot * 1.0F / 60.0F;
/*  979 */         this.neck1.rotateAngleX = 30.0F / this.radianF + RLegXRot * 2.0F / 60.0F;
/*      */       } 
/*      */     } 
/*  982 */     float TailXRot = MathHelper.cos(f * 0.4F) * 0.2F * f1;
/*  983 */     this.tail1.rotateAngleX = -19.0F / this.radianF;
/*  984 */     this.tail2.rotateAngleX = -16.0F / this.radianF;
/*  985 */     this.tail3.rotateAngleX = 7.0F / this.radianF;
/*  986 */     this.tail4.rotateAngleX = 11.0F / this.radianF;
/*  987 */     this.tail5.rotateAngleX = 8.0F / this.radianF;
/*      */     
/*  989 */     float t = f / 2.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  994 */     float A = 0.15F;
/*  995 */     float w = 0.9F;
/*  996 */     float k = 0.6F;
/*      */     
/*  998 */     int i = 0;
/*  999 */     float tailLat = 0.0F;
/* 1000 */     tailLat = A * MathHelper.sin(w * t - k * i++);
/* 1001 */     this.tail1.rotateAngleY = tailLat;
/* 1002 */     tailLat = A * MathHelper.sin(w * t - k * i++);
/* 1003 */     this.tail2.rotateAngleY = tailLat;
/* 1004 */     tailLat = A * MathHelper.sin(w * t - k * i++);
/* 1005 */     this.tail3.rotateAngleY = tailLat;
/* 1006 */     tailLat = A * MathHelper.sin(w * t - k * i++);
/* 1007 */     this.tail4.rotateAngleY = tailLat;
/* 1008 */     tailLat = A * MathHelper.sin(w * t - k * i++);
/* 1009 */     this.tail5.rotateAngleY = tailLat;
/*      */     
/* 1011 */     float WingSpread = MathHelper.cos(f * 0.3F) * 0.9F * f1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1018 */     if (this.flapwings && !this.isGhost) {
/* 1019 */       WingSpread = MathHelper.cos(f2 * 0.3F + 3.141593F) * 1.2F;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1024 */       WingSpread = MathHelper.cos(f * 0.5F) * 0.1F;
/*      */     } 
/*      */     
/* 1027 */     if (this.onAir || this.isGhost) {
/*      */ 
/*      */       
/* 1030 */       float speedMov = f1 * 0.5F;
/* 1031 */       if (this.isGhost) {
/* 1032 */         speedMov = 0.5F;
/*      */       }
/*      */       
/* 1035 */       this.leftuparm.rotateAngleZ = WingSpread * 2.0F / 3.0F;
/* 1036 */       this.rightuparm.rotateAngleZ = -WingSpread * 2.0F / 3.0F;
/* 1037 */       this.leftlowarm.rotateAngleZ = WingSpread * 0.1F;
/* 1038 */       this.leftfing1a.rotateAngleZ = WingSpread * 1.0F;
/* 1039 */       this.leftfing2a.rotateAngleZ = WingSpread * 0.8F;
/*      */       
/* 1041 */       this.rightlowarm.rotateAngleZ = -WingSpread * 0.1F;
/* 1042 */       this.rightfing1a.rotateAngleZ = -WingSpread * 1.0F;
/* 1043 */       this.rightfing2a.rotateAngleZ = -WingSpread * 0.8F;
/*      */ 
/*      */       
/* 1046 */       this.leftuparm.rotateAngleY = -10.0F / this.radianF - WingSpread / 2.5F;
/* 1047 */       this.leftlowarm.rotateAngleY = 15.0F / this.radianF + WingSpread / 2.5F;
/* 1048 */       this.leftfing1a.rotateAngleY = 70.0F / this.radianF;
/* 1049 */       this.leftfing2a.rotateAngleY = 35.0F / this.radianF;
/* 1050 */       this.leftfing3a.rotateAngleY = -5.0F / this.radianF;
/* 1051 */       this.rightuparm.rotateAngleY = 10.0F / this.radianF + WingSpread / 2.5F;
/* 1052 */       this.rightlowarm.rotateAngleY = -15.0F / this.radianF - WingSpread / 2.5F;
/* 1053 */       this.rightfing1a.rotateAngleY = -70.0F / this.radianF;
/* 1054 */       this.rightfing2a.rotateAngleY = -35.0F / this.radianF;
/* 1055 */       this.rightfing3a.rotateAngleY = 5.0F / this.radianF;
/*      */       
/* 1057 */       this.leftupleg.rotateAngleX = speedMov;
/* 1058 */       this.leftmidleg.rotateAngleX = speedMov;
/* 1059 */       this.leftfoot.rotateAngleX = 25.0F / this.radianF;
/* 1060 */       this.lefttoe1.rotateAngleX = speedMov;
/* 1061 */       this.lefttoe2.rotateAngleX = speedMov;
/* 1062 */       this.lefttoe3.rotateAngleX = speedMov;
/* 1063 */       this.rightfoot.rotateAngleX = 25.0F / this.radianF;
/* 1064 */       this.rightupleg.rotateAngleX = speedMov;
/* 1065 */       this.rightmidleg.rotateAngleX = speedMov;
/* 1066 */       this.righttoe1.rotateAngleX = speedMov;
/* 1067 */       this.righttoe2.rotateAngleX = speedMov;
/* 1068 */       this.righttoe3.rotateAngleX = speedMov;
/*      */     }
/*      */     else {
/*      */       
/* 1072 */       this.leftlowarm.rotateAngleZ = 0.0F;
/* 1073 */       this.leftfing1a.rotateAngleZ = 0.0F;
/* 1074 */       this.leftfing2a.rotateAngleZ = 0.0F;
/* 1075 */       this.rightlowarm.rotateAngleZ = 0.0F;
/* 1076 */       this.rightfing1a.rotateAngleZ = 0.0F;
/* 1077 */       this.rightfing2a.rotateAngleZ = 0.0F;
/*      */ 
/*      */ 
/*      */       
/* 1081 */       this.leftuparm.rotateAngleZ = 30.0F / this.radianF;
/* 1082 */       this.leftuparm.rotateAngleY = -60.0F / this.radianF + LLegXRot / 5.0F;
/*      */       
/* 1084 */       this.leftlowarm.rotateAngleY = 105.0F / this.radianF;
/* 1085 */       this.leftfing1a.rotateAngleY = -20.0F / this.radianF;
/* 1086 */       this.leftfing2a.rotateAngleY = -26.0F / this.radianF;
/* 1087 */       this.leftfing3a.rotateAngleY = -32.0F / this.radianF;
/*      */       
/* 1089 */       this.rightuparm.rotateAngleY = 60.0F / this.radianF - RLegXRot / 5.0F;
/* 1090 */       this.rightuparm.rotateAngleZ = -30.0F / this.radianF;
/*      */       
/* 1092 */       this.rightlowarm.rotateAngleY = -105.0F / this.radianF;
/* 1093 */       this.rightfing1a.rotateAngleY = 16.0F / this.radianF;
/* 1094 */       this.rightfing2a.rotateAngleY = 26.0F / this.radianF;
/* 1095 */       this.rightfing3a.rotateAngleY = 32.0F / this.radianF;
/*      */ 
/*      */ 
/*      */       
/* 1099 */       this.leftupleg.rotateAngleX = -25.0F / this.radianF + LLegXRot;
/* 1100 */       this.rightupleg.rotateAngleX = -25.0F / this.radianF + RLegXRot;
/*      */       
/* 1102 */       this.leftmidleg.rotateAngleX = 0.0F;
/* 1103 */       this.leftlowleg.rotateAngleX = 0.0F;
/* 1104 */       this.leftfoot.rotateAngleX = 25.0F / this.radianF - LLegXRot;
/* 1105 */       this.lefttoe1.rotateAngleX = LLegXRot;
/* 1106 */       this.lefttoe2.rotateAngleX = LLegXRot;
/* 1107 */       this.lefttoe3.rotateAngleX = LLegXRot;
/*      */       
/* 1109 */       this.rightmidleg.rotateAngleX = 0.0F;
/* 1110 */       this.rightlowleg.rotateAngleX = 0.0F;
/* 1111 */       this.rightfoot.rotateAngleX = 25.0F / this.radianF - RLegXRot;
/* 1112 */       this.righttoe1.rotateAngleX = RLegXRot;
/* 1113 */       this.righttoe2.rotateAngleX = RLegXRot;
/* 1114 */       this.righttoe3.rotateAngleX = RLegXRot;
/*      */     } 
/*      */ 
/*      */     
/* 1118 */     if (this.isSitting) {
/* 1119 */       this.leftupleg.rotateAngleX = 45.0F / this.radianF + LLegXRot;
/* 1120 */       this.rightupleg.rotateAngleX = 45.0F / this.radianF + RLegXRot;
/* 1121 */       this.leftmidleg.rotateAngleX = 30.0F;
/* 1122 */       this.rightmidleg.rotateAngleX = 30.0F;
/* 1123 */       this.neck2.rotateAngleX = -36.0F / this.radianF + f4 * 1.0F / 3.0F / this.radianF;
/* 1124 */       this.neck1.rotateAngleX = 30.0F / this.radianF + f4 * 2.0F / 3.0F / this.radianF;
/*      */     } 
/* 1126 */     if (this.diving) {
/* 1127 */       this.leftuparm.rotateAngleZ = -40.0F / this.radianF;
/* 1128 */       this.rightuparm.rotateAngleZ = 40.0F / this.radianF;
/* 1129 */       this.leftlowarm.rotateAngleZ = 0.0F;
/* 1130 */       this.leftfing1a.rotateAngleZ = 0.0F;
/* 1131 */       this.leftfing2a.rotateAngleZ = 0.0F;
/*      */       
/* 1133 */       this.rightlowarm.rotateAngleZ = 0.0F;
/* 1134 */       this.rightfing1a.rotateAngleZ = 0.0F;
/* 1135 */       this.rightfing2a.rotateAngleZ = 0.0F;
/*      */ 
/*      */       
/* 1138 */       this.leftuparm.rotateAngleY = -50.0F / this.radianF;
/* 1139 */       this.leftlowarm.rotateAngleY = 30.0F / this.radianF;
/* 1140 */       this.leftfing1a.rotateAngleY = 50.0F / this.radianF;
/* 1141 */       this.leftfing2a.rotateAngleY = 30.0F / this.radianF;
/* 1142 */       this.leftfing3a.rotateAngleY = 10.0F / this.radianF;
/*      */       
/* 1144 */       this.rightuparm.rotateAngleY = 50.0F / this.radianF;
/*      */ 
/*      */       
/* 1147 */       this.rightlowarm.rotateAngleY = -30.0F / this.radianF;
/* 1148 */       this.rightfing1a.rotateAngleY = -50.0F / this.radianF;
/* 1149 */       this.rightfing2a.rotateAngleY = -30.0F / this.radianF;
/* 1150 */       this.rightfing3a.rotateAngleY = -10.0F / this.radianF;
/*      */     } 
/*      */     
/* 1153 */     if (this.openMouth != 0) {
/* 1154 */       float mouthMov = MathHelper.cos((this.openMouth - 15) * 0.11F) * 0.8F;
/* 1155 */       this.Jaw.rotateAngleX = -10.0F / this.radianF + mouthMov;
/* 1156 */       this.leftearskin.rotateAngleY = mouthMov;
/* 1157 */       this.rightearskin.rotateAngleY = -mouthMov;
/*      */     } else {
/*      */       
/* 1160 */       this.Jaw.rotateAngleX = -10.0F / this.radianF;
/* 1161 */       this.leftearskin.rotateAngleY = 0.0F;
/* 1162 */       this.rightearskin.rotateAngleY = 0.0F;
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\model\MoCModelWyvern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */