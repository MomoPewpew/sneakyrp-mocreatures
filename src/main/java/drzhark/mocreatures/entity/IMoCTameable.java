package drzhark.mocreatures.entity;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public interface IMoCTameable extends IMoCEntity {
  boolean isRiderDisconnecting();
  
  void setRiderDisconnecting(boolean paramBoolean);
  
  void playTameEffect(boolean paramBoolean);
  
  void setTamed(boolean paramBoolean);
  
  void setDead();
  
  void writeEntityToNBT(NBTTagCompound paramNBTTagCompound);
  
  void readEntityFromNBT(NBTTagCompound paramNBTTagCompound);
  
  void setOwnerId(@Nullable UUID paramUUID);
  
  float getPetHealth();
  
  void spawnHeart();
  
  boolean readytoBreed();
  
  String getOffspringClazz(IMoCTameable paramIMoCTameable);
  
  int getOffspringTypeInt(IMoCTameable paramIMoCTameable);
  
  boolean compatibleMate(Entity paramEntity);
  
  void setHasEaten(boolean paramBoolean);
  
  boolean getHasEaten();
  
  void setGestationTime(int paramInt);
  
  int getGestationTime();
}


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\entity\IMoCTameable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */