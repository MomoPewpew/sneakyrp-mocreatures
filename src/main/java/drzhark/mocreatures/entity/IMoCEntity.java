package drzhark.mocreatures.entity;

import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public interface IMoCEntity {
  void selectType();

  String getPetName();

  void setPetName(String paramString);

  int getOwnerPetId();

  void setOwnerPetId(int paramInt);

  UUID getOwnerId();

  boolean getIsTamed();

  boolean getIsAdult();

  boolean getIsGhost();

  void setAdult(boolean paramBoolean);

  // boolean checkSpawningBiome();

  // boolean getCanSpawnHere();

  void performAnimation(int paramInt);

  boolean renderName();

  int nameYOffset();

  void makeEntityJump();

  void makeEntityDive();

  float getSizeFactor();

  float getAdjustedYOffset();

  void setArmorType(int paramInt);

  int getType();

  void setType(int paramInt);

  float rollRotationOffset();

  float pitchRotationOffset();

  void setEdad(int paramInt);

  int getEdad();

  float yawRotationOffset();

  float getAdjustedZOffset();

  float getAdjustedXOffset();

  ResourceLocation getTexture();

  boolean canAttackTarget(EntityLivingBase paramEntityLivingBase);

  boolean getIsSitting();

  boolean isNotScared();

  boolean isMovementCeased();

  boolean shouldAttackPlayers();

  double getDivingDepth();

  boolean isDiving();

  void forceEntityJump();

  int maxFlyingHeight();

  int minFlyingHeight();

  boolean isFlyer();

  boolean getIsFlying();

  String getClazzString();
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\IMoCEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
