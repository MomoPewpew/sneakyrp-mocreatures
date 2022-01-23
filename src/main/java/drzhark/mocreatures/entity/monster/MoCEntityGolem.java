package drzhark.mocreatures.entity.monster;
import drzhark.mocreatures.MoCTools;
import drzhark.mocreatures.MoCreatures;
import drzhark.mocreatures.entity.MoCEntityMob;
import drzhark.mocreatures.entity.ai.EntityAINearestAttackableTargetMoC;
import drzhark.mocreatures.entity.item.MoCEntityThrowableRock;
import drzhark.mocreatures.init.MoCSoundEvents;
import drzhark.mocreatures.network.MoCMessageHandler;
import drzhark.mocreatures.network.message.MoCMessageAnimation;
import drzhark.mocreatures.network.message.MoCMessageTwoBytes;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class MoCEntityGolem extends MoCEntityMob implements IEntityAdditionalSpawnData {
  public int tcounter;
  private int dCounter = 0; public MoCEntityThrowableRock tempRock; private byte[] golemCubes;
  private int sCounter;
  private static final DataParameter<Integer> GOLEM_STATE = EntityDataManager.createKey(MoCEntityGolem.class, DataSerializers.VARINT);

  public MoCEntityGolem(World world) {
    super(world);
    this.texture = "golemt.png";
    setSize(1.5F, 4.0F);
  }


  protected void initEntityAI() {
    this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackMelee((EntityCreature)this, 1.0D, true));
    this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTargetMoC((EntityCreature)this, EntityPlayer.class, true));
  }


  protected void applyEntityAttributes() {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
    getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
  }


  public void writeSpawnData(ByteBuf data) {
    for (int i = 0; i < 23; i++) {
      data.writeByte(this.golemCubes[i]);
    }
  }


  public void readSpawnData(ByteBuf data) {
    for (int i = 0; i < 23; i++) {
      this.golemCubes[i] = data.readByte();
    }
  }


  protected void entityInit() {
    super.entityInit();
    initGolemCubes();
    this.dataManager.register(GOLEM_STATE, Integer.valueOf(0));
  }

  public int getGolemState() {
    return ((Integer)this.dataManager.get(GOLEM_STATE)).intValue();
  }

  public void setGolemState(int i) {
    this.dataManager.set(GOLEM_STATE, Integer.valueOf(i));
  }


  public void onLivingUpdate() {
    super.onLivingUpdate();

    if (!this.world.isRemote) {
      if (getGolemState() == 0) {

        EntityPlayer entityplayer1 = this.world.getClosestPlayerToEntity((Entity)this, 8.0D);
        if (entityplayer1 != null) {
          setGolemState(1);
        }
      }

      if (getGolemState() == 1 && !isMissingCubes())
      {
        setGolemState(2);
      }

      if (getGolemState() > 2 && getGolemState() != 4 && getAttackTarget() == null) {
        setGolemState(1);
      }

      if (getGolemState() > 1 && getAttackTarget() != null && this.rand.nextInt(20) == 0) {
        if (getHealth() >= 30.0F) {
          setGolemState(2);
        }
        if (getHealth() < 30.0F && getHealth() >= 10.0F) {
          setGolemState(3);
        }
        if (getHealth() < 10.0F) {
          setGolemState(4);
        }
      }

      if (getGolemState() != 0 && getGolemState() != 4 && isMissingCubes()) {

        int freq = 21 - getGolemState() * this.world.getDifficulty().getDifficultyId() ;
        if (getGolemState() == 1) {
          freq = 10;
        }
        if (this.rand.nextInt(freq) == 0) {
          acquireRock(2);
        }
      }

      if (getGolemState() == 4) {
        getNavigator().clearPath();
        this.dCounter++;

        if (this.dCounter < 80 && this.rand.nextInt(3) == 0) {
          acquireRock(4);
        }

        if (this.dCounter == 120) {
          MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOLEM_DYING, 3.0F);
          MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 1), new NetworkRegistry.TargetPoint(this.world.provider
                .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
        }

        if (this.dCounter > 140) {
          MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOLEM_EXPLODE, 3.0F);
          destroyGolem();
        }
      }
    }

    if (this.tcounter == 0 && getAttackTarget() != null && canShoot()) {
      float distanceToTarget = getDistance((Entity)getAttackTarget());
      if (distanceToTarget > 6.0F) {
        this.tcounter = 1;
        MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageAnimation(getEntityId(), 0), new NetworkRegistry.TargetPoint(this.world.provider
              .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
      }
    }

    if (this.tcounter != 0) {
      if (this.tcounter++ == 70 && getAttackTarget() != null && canShoot() && !(getAttackTarget()).isDead &&
        canEntityBeSeen((Entity)getAttackTarget())) {
        shootBlock((Entity)getAttackTarget());
      } else if (this.tcounter > 90) {
        this.tcounter = 0;
      }
    }


    if (MoCreatures.proxy.getParticleFX() > 0 && getGolemState() == 4 && this.sCounter > 0) {
      for (int i = 0; i < 10; i++) {
        this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX, this.posY, this.posZ, this.rand.nextGaussian(), this.rand
            .nextGaussian(), this.rand.nextGaussian(), new int[0]);
      }
    }
  }

  private void destroyGolem() {
    List<Integer> usedBlocks = usedCubes();
    if (!usedBlocks.isEmpty() && MoCTools.mobGriefing(this.world) && MoCreatures.proxy.golemDestroyBlocks) {
      for (int i = 0; i < usedBlocks.size(); i++) {
        Block block = Block.getBlockById(generateBlock(this.golemCubes[((Integer)usedBlocks.get(i)).intValue()]));
        EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(block, 1, 0));
        entityitem.setPickupDelay(10);
        this.world.spawnEntity((Entity)entityitem);
      }
    }
    setDead();
  }


  public boolean isMovementCeased() {
    return (getGolemState() == 4);
  }



  protected void acquireRock(int type) {
    BlockPos myTRockPos = MoCTools.getRandomBlockPos((Entity)this, 24.0D);
    if (myTRockPos == null) {
      return;
    }

    boolean canDestroyBlocks = (MoCTools.mobGriefing(this.world) && MoCreatures.proxy.golemDestroyBlocks);
    IBlockState blockstate = this.world.getBlockState(myTRockPos);
    if (Block.getIdFromBlock(blockstate.getBlock()) == 0) {
      return;
    }
    BlockEvent.BreakEvent event = null;
    if (!this.world.isRemote)
    {
      event = new BlockEvent.BreakEvent(this.world, myTRockPos, blockstate, (EntityPlayer)FakePlayerFactory.get((WorldServer)this.world, MoCreatures.MOCFAKEPLAYER));
    }

    if (canDestroyBlocks && event != null && !event.isCanceled()) {

      this.world.setBlockToAir(myTRockPos);
    } else {

      canDestroyBlocks = false;
    }

    if (!canDestroyBlocks)
    {
      blockstate = Block.getStateById(returnRandomCheapBlock());
    }

    MoCEntityThrowableRock trock = new MoCEntityThrowableRock(this.world, (Entity)this, myTRockPos.getX(), (myTRockPos.getY() + 1), myTRockPos.getZ());
    trock.setState(blockstate);
    trock.setBehavior(type);


    this.world.spawnEntity((Entity)trock);
  }






  private int returnRandomCheapBlock() {
    int i = this.rand.nextInt(4);
    switch (i) {
      case 0:
        return 3;
      case 1:
        return 4;
      case 2:
        return 5;
      case 3:
        return 79;
    }
    return 3;
  }







  public void receiveRock(IBlockState state) {
    if (!this.world.isRemote) {
      byte myBlock = translateOre(Block.getIdFromBlock(state.getBlock()));
      byte slot = (byte)getRandomCubeAdj();
      if (slot != -1 && slot < 23 && myBlock != -1 && getGolemState() != 4) {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOLEM_ATTACH, 3.0F);
        int h = this.world.getDifficulty().getDifficultyId() ;
        setHealth(getHealth() + h);
        if (getHealth() > getMaxHealth()) {
          setHealth(getMaxHealth());
        }
        saveGolemCube(slot, myBlock);
      } else {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_TURTLE_HURT, 2.0F);
        if (MoCTools.mobGriefing(this.world) && MoCreatures.proxy.golemDestroyBlocks) {


          EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(state.getBlock(), 1, state.getBlock().getMetaFromState(state)));
          entityitem.setPickupDelay(10);
          entityitem.setAgeToCreativeDespawnTime();
        }
      }
    }
  }


  public void performAnimation(int animationType) {
    if (animationType == 0)
    {
      this.tcounter = 1;
    }
    if (animationType == 1)
    {
      this.sCounter = 1;
    }
  }





  private void shootBlock(Entity entity) {
    if (entity == null) {
      return;
    }
    List<Integer> armBlocks = new ArrayList<>();

    for (int i = 9; i < 15; i++) {
      if (this.golemCubes[i] != 30) {
        armBlocks.add(Integer.valueOf(i));
      }
    }
    if (armBlocks.isEmpty()) {
      return;
    }

    int j = this.rand.nextInt(armBlocks.size());
    int k = ((Integer)armBlocks.get(j)).intValue();
    int x = k;

    if (k == 9 || k == 12) {
      if (this.golemCubes[k + 2] != 30) {
        x = k + 2;
      } else if (this.golemCubes[k + 1] != 30) {
        x = k + 1;
      }
    }

    if ((k == 10 || k == 13) &&
      this.golemCubes[k + 1] != 30) {
      x = k + 1;
    }

    MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOLEM_SHOOT, 3.0F);
    MoCTools.ThrowStone((Entity)this, entity, Block.getStateById(generateBlock(this.golemCubes[x])), 10.0D, 0.4D);
    saveGolemCube((byte)x, (byte)30);
    this.tcounter = 0;
  }

  private boolean canShoot() {
    int x = 0;
    for (byte i = 9; i < 15; i = (byte)(i + 1)) {
      if (this.golemCubes[i] != 30) {
        x++;
      }
    }
    return (x != 0 && getGolemState() != 4 && getGolemState() != 1);
  }


  public boolean attackEntityFrom(DamageSource damagesource, float i) {
    if (getGolemState() == 4) {
      return false;
    }

    List<Integer> missingChestBlocks = missingChestCubes();
    boolean uncoveredChest = (missingChestBlocks.size() == 4);
    if (!openChest() && !uncoveredChest && getGolemState() != 1) {
      int j = this.world.getDifficulty().getDifficultyId() ;
      if (!this.world.isRemote && this.rand.nextInt(j) == 0) {
        destroyRandomGolemCube();
      } else {
        MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_TURTLE_HURT, 2.0F);
      }

      Entity entity = damagesource.getTrueSource();
      if (entity != this && this.world.getDifficulty().getDifficultyId()  > 0 && entity instanceof EntityLivingBase) {
        setAttackTarget((EntityLivingBase)entity);
        return true;
      }
      return false;
    }

    if (i > 5.0F) {
      i = 5.0F;
    }
    if (getGolemState() != 1 && super.attackEntityFrom(damagesource, i)) {
      Entity entity = damagesource.getTrueSource();
      if (entity != this && this.world.getDifficulty().getDifficultyId()  > 0 && entity instanceof EntityLivingBase) {
        setAttackTarget((EntityLivingBase)entity);
        return true;
      }
      return false;
    }

    if (getGolemState() == 1) {
      Entity entity = damagesource.getTrueSource();
      if (entity != this && this.world.getDifficulty().getDifficultyId()  > 0 && entity instanceof EntityLivingBase) {
        setAttackTarget((EntityLivingBase)entity);
        return true;
      }
      return false;
    }

    return false;
  }






  private void destroyRandomGolemCube() {
    int i = getRandomUsedCube();
    if (i == 4) {
      return;
    }


    int x = i;
    if ((i == 10 || i == 13 || i == 16 || i == 19) &&
      this.golemCubes[i + 1] != 30) {
      x = i + 1;
    }



    if (i == 9 || i == 12 || i == 15 || i == 18) {
      if (this.golemCubes[i + 2] != 30) {
        x = i + 2;
      } else if (this.golemCubes[i + 1] != 30) {
        x = i + 1;
      }
    }

    if (x != -1 && this.golemCubes[x] != 30) {
      Block block = Block.getBlockById(generateBlock(this.golemCubes[x]));
      saveGolemCube((byte)x, (byte)30);
      MoCTools.playCustomSound((Entity)this, MoCSoundEvents.ENTITY_GOLEM_HURT, 3.0F);
      if (MoCTools.mobGriefing(this.world) && MoCreatures.proxy.golemDestroyBlocks) {
        EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(block, 1, 0));
        entityitem.setPickupDelay(10);
        this.world.spawnEntity((Entity)entityitem);
      }
    }
  }


  public float getAdjustedYOffset() {
    if (this.golemCubes[17] != 30 || this.golemCubes[20] != 30)
    {
      return 0.0F;
    }
    if (this.golemCubes[16] != 30 || this.golemCubes[19] != 30)
    {
      return 0.4F;
    }
    if (this.golemCubes[15] != 30 || this.golemCubes[18] != 30)
    {
      return 0.7F;
    }

    if (this.golemCubes[1] != 30 || this.golemCubes[3] != 30)
    {
      return 0.8F;
    }

    return 1.45F;
  }





  public float getSizeFactor() {
    return 1.8F;
  }





  public byte getBlockText(int i) {
    return this.golemCubes[i];
  }


  public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    super.writeEntityToNBT(nbttagcompound);
    nbttagcompound.setInteger("golemState", getGolemState());
    NBTTagList cubeLists = new NBTTagList();

    for (int i = 0; i < 23; i++) {
      NBTTagCompound nbttag = new NBTTagCompound();
      nbttag.setByte("Slot", this.golemCubes[i]);
      cubeLists.appendTag((NBTBase)nbttag);
    }
    nbttagcompound.setTag("GolemBlocks", (NBTBase)cubeLists);
  }


  public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    super.readEntityFromNBT(nbttagcompound);
    setGolemState(nbttagcompound.getInteger("golemState"));
    NBTTagList nbttaglist = nbttagcompound.getTagList("GolemBlocks", 10);
    for (int i = 0; i < 23; i++) {
      NBTTagCompound var4 = nbttaglist.getCompoundTagAt(i);
      this.golemCubes[i] = var4.getByte("Slot");
    }
  }




  private void initGolemCubes() {
    this.golemCubes = new byte[23];

    for (int i = 0; i < 23; i++) {
      this.golemCubes[i] = 30;
    }

    int j = this.rand.nextInt(4);
    switch (j) {
      case 0:
        j = 7;
        break;
      case 1:
        j = 11;
        break;
      case 2:
        j = 15;
        break;
      case 3:
        j = 21;
        break;
    }
    saveGolemCube((byte)4, (byte)j);
  }








  public void saveGolemCube(byte slot, byte value) {
    this.golemCubes[slot] = value;
    if (!this.world.isRemote && MoCreatures.proxy.worldInitDone)
    {
      MoCMessageHandler.INSTANCE.sendToAllAround((IMessage)new MoCMessageTwoBytes(getEntityId(), slot, value), new NetworkRegistry.TargetPoint(this.world.provider
            .getDimensionType().getId(), this.posX, this.posY, this.posZ, 64.0D));
    }
  }






  private List<Integer> missingCubes() {
    List<Integer> emptyBlocks = new ArrayList<>();

    for (int i = 0; i < 23; i++) {
      if (this.golemCubes[i] == 30) {
        emptyBlocks.add(Integer.valueOf(i));
      }
    }
    return emptyBlocks;
  }






  public boolean isMissingCubes() {
    for (int i = 0; i < 23; i++) {
      if (this.golemCubes[i] == 30) {
        return true;
      }
    }
    return false;
  }

  private List<Integer> missingChestCubes() {
    List<Integer> emptyChestBlocks = new ArrayList<>();

    for (int i = 0; i < 4; i++) {
      if (this.golemCubes[i] == 30) {
        emptyChestBlocks.add(Integer.valueOf(i));
      }
    }
    return emptyChestBlocks;
  }






  private List<Integer> usedCubes() {
    List<Integer> usedBlocks = new ArrayList<>();

    for (int i = 0; i < 23; i++) {
      if (this.golemCubes[i] != 30) {
        usedBlocks.add(Integer.valueOf(i));
      }
    }
    return usedBlocks;
  }






  private int getRandomUsedCube() {
    List<Integer> usedBlocks = usedCubes();
    if (usedBlocks.isEmpty()) {
      return -1;
    }
    int randomEmptyBlock = this.rand.nextInt(usedBlocks.size());
    return ((Integer)usedBlocks.get(randomEmptyBlock)).intValue();
  }







  private int getRandomMissingCube() {
    List<Integer> emptyChestBlocks = missingChestCubes();
    if (!emptyChestBlocks.isEmpty()) {
      int i = this.rand.nextInt(emptyChestBlocks.size());
      return ((Integer)emptyChestBlocks.get(i)).intValue();
    }


    List<Integer> emptyBlocks = missingCubes();
    if (emptyBlocks.isEmpty()) {
      return -1;
    }
    int randomEmptyBlock = this.rand.nextInt(emptyBlocks.size());
    return ((Integer)emptyBlocks.get(randomEmptyBlock)).intValue();
  }







  private int getRandomCubeAdj() {
    int i = getRandomMissingCube();

    if (i == 10 || i == 13 || i == 16 || i == 19) {
      if (this.golemCubes[i - 1] == 30) {
        return i - 1;
      }
      saveGolemCube((byte)i, this.golemCubes[i - 1]);
      return i - 1;
    }


    if (i == 11 || i == 14 || i == 17 || i == 20) {
      if (this.golemCubes[i - 2] == 30 && this.golemCubes[i - 1] == 30) {
        return i - 2;
      }
      if (this.golemCubes[i - 1] == 30) {
        saveGolemCube((byte)(i - 1), this.golemCubes[i - 2]);
        return i - 2;
      }
      saveGolemCube((byte)i, this.golemCubes[i - 1]);
      saveGolemCube((byte)(i - 1), this.golemCubes[i - 2]);
      return i - 2;
    }

    return i;
  }


  public float rollRotationOffset() {
    int leftLeg = 0;
    int rightLeg = 0;
    if (this.golemCubes[15] != 30) {
      leftLeg++;
    }
    if (this.golemCubes[16] != 30) {
      leftLeg++;
    }
    if (this.golemCubes[17] != 30) {
      leftLeg++;
    }
    if (this.golemCubes[18] != 30) {
      rightLeg++;
    }
    if (this.golemCubes[19] != 30) {
      rightLeg++;
    }
    if (this.golemCubes[20] != 30) {
      rightLeg++;
    }
    return (leftLeg - rightLeg) * 10.0F;
  }







  public boolean openChest() {
    if (isMissingCubes()) {
      List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox().expand(2.0D, 2.0D, 2.0D));

      for (int i = 0; i < list.size(); i++) {
        Entity entity1 = list.get(i);
        if (entity1 instanceof MoCEntityThrowableRock) {
          if (MoCreatures.proxy.getParticleFX() > 0) {
            MoCreatures.proxy.VacuumFX(this);
          }
          return true;
        }
      }
    }
    return false;
  }








  private byte translateOre(int blockType) {
    switch (blockType) {
      case 0:
        return 0;
      case 1:
        return 0;
      case 18:
        return 10;
      case 2:
      case 3:
        return 1;
      case 4:
      case 48:
        return 2;
      case 5:
        return 3;
      case 12:
        return 4;
      case 13:
        return 5;
      case 16:
      case 21:
      case 56:
      case 73:
      case 74:
        return 24;
      case 14:
      case 41:
        return 7;
      case 15:
      case 42:
        return 11;
      case 57:
        return 15;
      case 17:
        return 6;
      case 20:
        return 8;
      case 22:
      case 35:
        return 9;
      case 45:
        return 12;
      case 49:
        return 14;
      case 58:
        return 16;
      case 61:
      case 62:
        return 17;
      case 78:
      case 79:
        return 18;
      case 81:
        return 19;
      case 82:
        return 20;
      case 86:
      case 91:
      case 103:
        return 22;
      case 87:
        return 23;
      case 89:
        return 25;
      case 98:
        return 26;
      case 112:
        return 27;
      case 129:
      case 133:
        return 21;
    }
    return -1;
  }








  private int generateBlock(int golemBlock) {
    switch (golemBlock) {
      case 0:
        return 1;
      case 1:
        return 3;
      case 2:
        return 4;
      case 3:
        return 5;
      case 4:
        return 12;
      case 5:
        return 13;
      case 6:
        return 17;
      case 7:
        return 41;
      case 8:
        return 20;
      case 9:
        return 35;
      case 10:
        return 18;
      case 11:
        return 42;
      case 12:
        return 45;
      case 13:
        return 2;
      case 14:
        return 49;
      case 15:
        return 57;
      case 16:
        return 58;
      case 17:
        return 51;
      case 18:
        return 79;
      case 19:
        return 81;
      case 20:
        return 82;
      case 21:
        return 133;
      case 22:
        return 86;
      case 23:
        return 87;
      case 24:
        return 56;
      case 25:
        return 89;
      case 26:
        return 98;
      case 27:
        return 112;
    }
    return 2;
  }


  private int countLegBlocks() {
    int x = 0;
    for (byte i = 15; i < 21; i = (byte)(i + 1)) {
      if (this.golemCubes[i] != 30) {
        x++;
      }
    }
    return x;
  }


  public float getAIMoveSpeed() {
    return 0.15F * countLegBlocks() / 6.0F;
  }






  public ResourceLocation getEffectTexture() {
    switch (getGolemState()) {
      case 1:
        return MoCreatures.proxy.getTexture("golemeffect1.png");
      case 2:
        return MoCreatures.proxy.getTexture("golemeffect2.png");
      case 3:
        return MoCreatures.proxy.getTexture("golemeffect3.png");
      case 4:
        return MoCreatures.proxy.getTexture("golemeffect4.png");
    }
    return null;
  }








  public float colorFX(int i) {
    switch (getGolemState()) {
      case 1:
        if (i == 1) {
          return 0.25490198F;
        }
        if (i == 2) {
          return 0.6156863F;
        }
        if (i == 3) {
          return 0.99607843F;
        }
      case 2:
        if (i == 1) {
          return 0.95686275F;
        }
        if (i == 2) {
          return 0.972549F;
        }
        if (i == 3) {
          return 0.14117648F;
        }
      case 3:
        if (i == 1) {
          return 1.0F;
        }
        if (i == 2) {
          return 0.6039216F;
        }
        if (i == 3) {
          return 0.08235294F;
        }
      case 4:
        if (i == 1) {
          return 0.972549F;
        }
        if (i == 2) {
          return 0.039215688F;
        }
        if (i == 3)
          return 0.039215688F;
        break;
    }
    return 0.0F;
  }





  protected void playStepSound(BlockPos p_180429_1_, Block p_180429_2_) {
    playSound(MoCSoundEvents.ENTITY_GOLEM_WALK, 1.0F, 1.0F);
  }


  protected SoundEvent getHurtSound(DamageSource source) {
    return MoCSoundEvents.ENTITY_GOLEM_AMBIENT;
  }


  public boolean getCanSpawnHere() {
    return (super.getCanSpawnHere() && this.world
      .canBlockSeeSky(new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY),
          MathHelper.floor(this.posZ))) && this.posY > 50.0D);
  }
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\monster\MoCEntityGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
