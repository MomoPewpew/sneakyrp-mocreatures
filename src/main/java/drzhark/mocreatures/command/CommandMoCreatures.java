/*     */ package drzhark.mocreatures.command;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import drzhark.mocreatures.MoCEntityData;
/*     */ import drzhark.mocreatures.MoCPetData;
/*     */ import drzhark.mocreatures.MoCTools;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.configuration.MoCConfigCategory;
/*     */ import drzhark.mocreatures.configuration.MoCConfiguration;
/*     */ import drzhark.mocreatures.configuration.MoCProperty;
/*     */ import drzhark.mocreatures.entity.IMoCTameable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.NumberInvalidException;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.fml.common.FMLCommonHandler;
/*     */ 
/*     */ public class CommandMoCreatures extends CommandBase {
/*  34 */   private static List<String> commands = new ArrayList<>();
/*  35 */   private static List<String> aliases = new ArrayList<>();
/*  36 */   private static List<String> tabCompletionStrings = new ArrayList<>();
/*     */   
/*     */   static {
/*  39 */     commands.add("/moc attackdolphins <boolean>");
/*  40 */     commands.add("/moc attackhorses <boolean>");
/*  41 */     commands.add("/moc attackwolves <boolean>");
/*  42 */     commands.add("/moc canspawn <boolean>");
/*  43 */     commands.add("/moc caveogrechance <float>");
/*  44 */     commands.add("/moc caveogrestrength <float>");
/*  45 */     commands.add("/moc debug <boolean>");
/*     */     
/*  47 */     commands.add("/moc destroydrops <boolean>");
/*  48 */     commands.add("/moc enablehunters <boolean>");
/*  49 */     commands.add("/moc easybreeding <boolean>");
/*  50 */     commands.add("/moc elephantbulldozer <boolean>");
/*  51 */     commands.add("/moc enableownership <boolean>");
/*  52 */     commands.add("/moc enableresetownerscroll <boolean>");
/*  53 */     commands.add("/moc fireogrechance <int>");
/*  54 */     commands.add("/moc fireogrestrength <float>");
/*  55 */     commands.add("/moc frequency <entity> <int>");
/*  56 */     commands.add("/moc golemdestroyblocks <boolean>");
/*  57 */     commands.add("/moc tamed");
/*  58 */     commands.add("/moc tamed <playername>");
/*  59 */     commands.add("/moc maxchunk <entity> <int>");
/*  60 */     commands.add("/moc maxspawn <entity> <int>");
/*  61 */     commands.add("/moc maxtamedperop <int>");
/*  62 */     commands.add("/moc maxtamedperplayer <int>");
/*  63 */     commands.add("/moc minspawn <entity> <int>");
/*  64 */     commands.add("/moc motherwyverneggdropchance <int>");
/*  65 */     commands.add("/moc ogreattackrange <int>");
/*  66 */     commands.add("/moc ogrestrength <float>");
/*  67 */     commands.add("/moc ostricheggdropchance <int>");
/*  68 */     commands.add("/moc rareitemdropchance <int>");
/*  69 */     commands.add("/moc spawnhorse <int>");
/*  70 */     commands.add("/moc spawnwyvern <int>");
/*  71 */     commands.add("/moc tamedcount <playername>");
/*  72 */     commands.add("/moc tp <petid> <playername>");
/*  73 */     commands.add("/moc <command> value");
/*  74 */     commands.add("/moc wyverneggdropchance <int>");
/*  75 */     commands.add("/moc zebrachance <int>");
/*  76 */     aliases.add("moc");
/*  77 */     tabCompletionStrings.add("attackdolphins");
/*  78 */     tabCompletionStrings.add("attackhorses");
/*  79 */     tabCompletionStrings.add("attackwolves");
/*  80 */     tabCompletionStrings.add("canspawn");
/*  81 */     tabCompletionStrings.add("caveogrechance");
/*  82 */     tabCompletionStrings.add("caveogrestrength");
/*  83 */     tabCompletionStrings.add("debug");
/*     */     
/*  85 */     tabCompletionStrings.add("destroydrops");
/*  86 */     tabCompletionStrings.add("easybreeding");
/*  87 */     tabCompletionStrings.add("elephantbulldozer");
/*  88 */     tabCompletionStrings.add("enableownership");
/*  89 */     tabCompletionStrings.add("enableresetownerscroll");
/*  90 */     tabCompletionStrings.add("fireogrechance");
/*  91 */     tabCompletionStrings.add("fireogrestrength");
/*  92 */     tabCompletionStrings.add("forcedespawns");
/*  93 */     tabCompletionStrings.add("frequency");
/*  94 */     tabCompletionStrings.add("golemdestroyblocks");
/*  95 */     tabCompletionStrings.add("tamed");
/*  96 */     tabCompletionStrings.add("maxchunk");
/*  97 */     tabCompletionStrings.add("maxspawn");
/*  98 */     tabCompletionStrings.add("maxtamedperop");
/*  99 */     tabCompletionStrings.add("maxtamedperplayer");
/* 100 */     tabCompletionStrings.add("minspawn");
/* 101 */     tabCompletionStrings.add("motherwyverneggdropchance");
/* 102 */     tabCompletionStrings.add("ogreattackrange");
/* 103 */     tabCompletionStrings.add("ogreattackstrength");
/* 104 */     tabCompletionStrings.add("ostricheggdropchance");
/* 105 */     tabCompletionStrings.add("rareitemdropchance");
/* 106 */     tabCompletionStrings.add("spawnhorse");
/* 107 */     tabCompletionStrings.add("spawnwyvern");
/* 108 */     tabCompletionStrings.add("tamedcount");
/* 109 */     tabCompletionStrings.add("tp");
/* 110 */     tabCompletionStrings.add("wyverneggdropchance");
/* 111 */     tabCompletionStrings.add("zebrachance");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 116 */     return "mocreatures";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getAliases() {
/* 121 */     return aliases;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRequiredPermissionLevel() {
/* 129 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsage(ICommandSender par1ICommandSender) {
/* 134 */     return "commands.mocreatures.usage";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 142 */     return getListOfStringsMatchingLastWord(par2ArrayOfStr, tabCompletionStrings.<String>toArray(new String[tabCompletionStrings.size()]));
/*     */   }
/*     */ 
/*     */   
/*     */   public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
/* 147 */     String command = "";
/* 148 */     if (args.length == 0) {
/* 149 */       command = "help";
/*     */     } else {
/* 151 */       command = args[0];
/*     */     } 
/* 153 */     String par2 = "";
/* 154 */     if (args.length > 1) {
/* 155 */       par2 = args[1];
/*     */     }
/* 157 */     String par3 = "";
/* 158 */     if (args.length == 3) {
/* 159 */       par3 = args[2];
/*     */     }
/*     */     
/* 162 */     MoCConfiguration config = MoCreatures.proxy.mocSettingsConfig;
/* 163 */     boolean saved = false;
/*     */     
/* 165 */     if (command.equalsIgnoreCase("tamed") || command.equalsIgnoreCase("tame")) {
/* 166 */       if (args.length == 2 && !Character.isDigit(args[1].charAt(0))) {
/* 167 */         int unloadedCount = 0;
/* 168 */         int loadedCount = 0;
/* 169 */         ArrayList<Integer> foundIds = new ArrayList<>();
/* 170 */         ArrayList<String> tamedlist = new ArrayList<>();
/* 171 */         String playername = par2;
/* 172 */         GameProfile profile = server.getPlayerProfileCache().getGameProfileForUsername(playername);
/* 173 */         if (profile == null)
/*     */           return;  Integer[] arrayOfInteger;
/*     */         int i;
/*     */         byte b;
/* 177 */         for (arrayOfInteger = DimensionManager.getIDs(), i = arrayOfInteger.length, b = 0; b < i; ) { int dimension = arrayOfInteger[b].intValue();
/* 178 */           WorldServer world = DimensionManager.getWorld(dimension);
/* 179 */           for (int j = 0; j < world.loadedEntityList.size(); j++) {
/* 180 */             Entity entity = world.loadedEntityList.get(j);
/* 181 */             if (IMoCTameable.class.isAssignableFrom(entity.getClass())) {
/* 182 */               IMoCTameable mocreature = (IMoCTameable)entity;
/* 183 */               if (mocreature.getOwnerId().equals(profile.getId())) {
/* 184 */                 loadedCount++;
/* 185 */                 foundIds.add(Integer.valueOf(mocreature.getOwnerPetId()));
/* 186 */                 tamedlist.add(TextFormatting.WHITE + "Found pet with " + TextFormatting.DARK_AQUA + "Type" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + ((EntityLiving)mocreature)
/*     */                     
/* 188 */                     .getName() + TextFormatting.DARK_AQUA + ", Name" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + mocreature
/* 189 */                     .getPetName() + TextFormatting.DARK_AQUA + ", Owner" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + profile
/*     */                     
/* 191 */                     .getName() + TextFormatting.DARK_AQUA + ", PetId" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + mocreature
/* 192 */                     .getOwnerPetId() + TextFormatting.DARK_AQUA + ", Dimension" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + entity.dimension + TextFormatting.DARK_AQUA + ", Pos" + TextFormatting.WHITE + ":" + TextFormatting.LIGHT_PURPLE + 
/*     */                     
/* 194 */                     Math.round(entity.posX) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + 
/* 195 */                     Math.round(entity.posY) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + 
/* 196 */                     Math.round(entity.posZ));
/*     */               } 
/*     */             } 
/*     */           }  b++; }
/*     */         
/* 201 */         MoCPetData ownerPetData = MoCreatures.instance.mapData.getPetData(profile.getId());
/* 202 */         if (ownerPetData != null) {
/* 203 */           for (int j = 0; j < ownerPetData.getTamedList().tagCount(); j++) {
/* 204 */             NBTTagCompound nbt = ownerPetData.getTamedList().getCompoundTagAt(j);
/* 205 */             if (nbt.hasKey("PetId") && !foundIds.contains(Integer.valueOf(nbt.getInteger("PetId")))) {
/* 206 */               unloadedCount++;
/* 207 */               double posX = nbt.getTagList("Pos", 6).getDoubleAt(0);
/* 208 */               double posY = nbt.getTagList("Pos", 6).getDoubleAt(1);
/* 209 */               double posZ = nbt.getTagList("Pos", 6).getDoubleAt(2);
/* 210 */               tamedlist.add(TextFormatting.WHITE + "Found unloaded pet with " + TextFormatting.DARK_AQUA + "Type" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/* 211 */                   .getString("EntityName") + TextFormatting.DARK_AQUA + ", Name" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/*     */                   
/* 213 */                   .getString("Name") + TextFormatting.DARK_AQUA + ", Owner" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/* 214 */                   .getString("Owner") + TextFormatting.DARK_AQUA + ", PetId" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/* 215 */                   .getInteger("PetId") + TextFormatting.DARK_AQUA + ", Dimension" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/*     */                   
/* 217 */                   .getInteger("Dimension") + TextFormatting.DARK_AQUA + ", Pos" + TextFormatting.WHITE + ":" + TextFormatting.LIGHT_PURPLE + 
/* 218 */                   Math.round(posX) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + 
/* 219 */                   Math.round(posY) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + 
/* 220 */                   Math.round(posZ));
/*     */             } 
/*     */           } 
/*     */         }
/* 224 */         if (tamedlist.size() > 0) {
/* 225 */           sendPageHelp(sender, (byte)10, tamedlist, args, "Listing tamed pets");
/* 226 */           sender.sendMessage((ITextComponent)new TextComponentTranslation("Loaded tamed count : " + TextFormatting.AQUA + loadedCount + TextFormatting.WHITE + ", Unloaded count : " + TextFormatting.AQUA + unloadedCount + TextFormatting.WHITE + ", Total count : " + TextFormatting.AQUA + ((ownerPetData != null) ? ownerPetData
/*     */                 
/* 228 */                 .getTamedList().tagCount() : 0), new Object[0]));
/*     */         } else {
/* 230 */           sender.sendMessage((ITextComponent)new TextComponentTranslation("Player " + TextFormatting.GREEN + playername + TextFormatting.WHITE + " does not have any tamed animals.", new Object[0]));
/*     */         }
/*     */       
/* 233 */       } else if (command.equalsIgnoreCase("tamed") || (command.equalsIgnoreCase("tame") && !par2.equals(""))) {
/* 234 */         int unloadedCount = 0;
/* 235 */         int loadedCount = 0;
/* 236 */         ArrayList<Integer> foundIds = new ArrayList<>();
/* 237 */         ArrayList<String> tamedlist = new ArrayList<>(); Integer[] arrayOfInteger; int i;
/*     */         byte b;
/* 239 */         for (arrayOfInteger = DimensionManager.getIDs(), i = arrayOfInteger.length, b = 0; b < i; ) { int dimension = arrayOfInteger[b].intValue();
/* 240 */           WorldServer world = DimensionManager.getWorld(dimension);
/* 241 */           for (int j = 0; j < world.loadedEntityList.size(); j++) {
/* 242 */             Entity entity = world.loadedEntityList.get(j);
/* 243 */             if (IMoCTameable.class.isAssignableFrom(entity.getClass())) {
/* 244 */               IMoCTameable mocreature = (IMoCTameable)entity;
/* 245 */               if (mocreature.getOwnerPetId() != -1) {
/* 246 */                 loadedCount++;
/* 247 */                 foundIds.add(Integer.valueOf(mocreature.getOwnerPetId()));
/* 248 */                 tamedlist.add(TextFormatting.WHITE + "Found pet with " + TextFormatting.DARK_AQUA + "Type" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + ((EntityLiving)mocreature)
/*     */                     
/* 250 */                     .getName() + TextFormatting.DARK_AQUA + ", Name" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + mocreature
/* 251 */                     .getPetName() + TextFormatting.DARK_AQUA + ", Owner" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + mocreature
/*     */                     
/* 253 */                     .getOwnerId() + TextFormatting.DARK_AQUA + ", PetId" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + mocreature
/* 254 */                     .getOwnerPetId() + TextFormatting.DARK_AQUA + ", Dimension" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + entity.dimension + TextFormatting.DARK_AQUA + ", Pos" + TextFormatting.WHITE + ":" + TextFormatting.LIGHT_PURPLE + 
/*     */                     
/* 256 */                     Math.round(entity.posX) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + 
/* 257 */                     Math.round(entity.posY) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + 
/* 258 */                     Math.round(entity.posZ));
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/*     */           b++; }
/*     */         
/* 265 */         for (MoCPetData ownerPetData : MoCreatures.instance.mapData.getPetMap().values()) {
/* 266 */           for (int j = 0; j < ownerPetData.getTamedList().tagCount(); j++) {
/* 267 */             NBTTagCompound nbt = ownerPetData.getTamedList().getCompoundTagAt(j);
/* 268 */             if (nbt.hasKey("PetId") && !foundIds.contains(Integer.valueOf(nbt.getInteger("PetId")))) {
/* 269 */               unloadedCount++;
/* 270 */               double posX = nbt.getTagList("Pos", 10).getDoubleAt(0);
/* 271 */               double posY = nbt.getTagList("Pos", 10).getDoubleAt(1);
/* 272 */               double posZ = nbt.getTagList("Pos", 10).getDoubleAt(2);
/* 273 */               tamedlist.add(TextFormatting.WHITE + "Found unloaded pet with " + TextFormatting.DARK_AQUA + "Type" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/* 274 */                   .getString("EntityName") + TextFormatting.DARK_AQUA + ", Name" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/*     */                   
/* 276 */                   .getString("Name") + TextFormatting.DARK_AQUA + ", Owner" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/* 277 */                   .getString("Owner") + TextFormatting.DARK_AQUA + ", PetId" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/* 278 */                   .getInteger("PetId") + TextFormatting.DARK_AQUA + ", Dimension" + TextFormatting.WHITE + ":" + TextFormatting.GREEN + nbt
/*     */                   
/* 280 */                   .getInteger("Dimension") + TextFormatting.DARK_AQUA + ", Pos" + TextFormatting.WHITE + ":" + TextFormatting.LIGHT_PURPLE + 
/* 281 */                   Math.round(posX) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + 
/* 282 */                   Math.round(posY) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + 
/* 283 */                   Math.round(posZ));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 288 */         sendPageHelp(sender, (byte)10, tamedlist, args, "Listing tamed pets");
/* 289 */         sender.sendMessage((ITextComponent)new TextComponentTranslation("Loaded tamed count : " + TextFormatting.AQUA + loadedCount + TextFormatting.WHITE + (
/*     */ 
/*     */ 
/*     */               
/* 293 */               !MoCreatures.isServer() ? (", Unloaded Count : " + TextFormatting.AQUA + unloadedCount + TextFormatting.WHITE + ", Total count : " + TextFormatting.AQUA + (loadedCount + unloadedCount)) : ""), new Object[0]));
/*     */       }
/*     */     
/* 296 */     } else if (command.equalsIgnoreCase("tp") && args.length == 3) {
/* 297 */       int petId = 0;
/*     */       try {
/* 299 */         petId = Integer.parseInt(par2);
/* 300 */       } catch (NumberFormatException e) {
/* 301 */         petId = -1;
/*     */       } 
/* 303 */       String playername = args[2];
/*     */       
/* 305 */       EntityPlayerMP player = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(playername);
/* 306 */       if (player == null) {
/*     */         return;
/*     */       }
/*     */       
/* 310 */       MoCPetData ownerPetData = MoCreatures.instance.mapData.getPetData(player.getUniqueID());
/* 311 */       if (ownerPetData != null) {
/* 312 */         for (int i = 0; i < ownerPetData.getTamedList().tagCount(); i++) {
/* 313 */           NBTTagCompound nbt = ownerPetData.getTamedList().getCompoundTagAt(i);
/* 314 */           if (nbt.hasKey("PetId") && nbt.getInteger("PetId") == petId) {
/* 315 */             String petName = nbt.getString("Name");
/* 316 */             WorldServer world = DimensionManager.getWorld(nbt.getInteger("Dimension"));
/* 317 */             if (!teleportLoadedPet(world, player, petId, petName, sender)) {
/* 318 */               double posX = nbt.getTagList("Pos", 10).getDoubleAt(0);
/* 319 */               double posY = nbt.getTagList("Pos", 10).getDoubleAt(1);
/* 320 */               double posZ = nbt.getTagList("Pos", 10).getDoubleAt(2);
/* 321 */               sender.sendMessage((ITextComponent)new TextComponentTranslation("Found unloaded pet " + TextFormatting.GREEN + nbt
/* 322 */                     .getString("id") + TextFormatting.WHITE + " with name " + TextFormatting.AQUA + nbt
/* 323 */                     .getString("Name") + TextFormatting.WHITE + " at location " + TextFormatting.LIGHT_PURPLE + 
/* 324 */                     Math.round(posX) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + Math.round(posY) + TextFormatting.WHITE + ", " + TextFormatting.LIGHT_PURPLE + 
/* 325 */                     Math.round(posZ) + TextFormatting.WHITE + " with Pet ID " + TextFormatting.BLUE + nbt
/* 326 */                     .getInteger("PetId"), new Object[0]));
/* 327 */               boolean result = teleportLoadedPet(world, player, petId, petName, sender);
/* 328 */               if (!result) {
/* 329 */                 sender.sendMessage((ITextComponent)new TextComponentTranslation("Unable to transfer entity ID " + TextFormatting.GREEN + petId + TextFormatting.WHITE + ". It may only be transferred to " + TextFormatting.AQUA + player
/*     */                       
/* 331 */                       .getName(), new Object[0]));
/*     */               }
/*     */             } 
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } else {
/* 338 */         sender.sendMessage((ITextComponent)new TextComponentTranslation("Tamed entity could not be located.", new Object[0]));
/*     */       } 
/* 340 */     } else if (command.equalsIgnoreCase("tamedcount")) {
/* 341 */       String playername = par2;
/* 342 */       List<EntityPlayerMP> players = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers();
/* 343 */       for (int i = 0; i < players.size(); i++) {
/* 344 */         EntityPlayerMP player = players.get(i);
/* 345 */         if (player.getName().equalsIgnoreCase(playername)) {
/* 346 */           int tamedCount = MoCTools.numberTamedByPlayer((EntityPlayer)player);
/* 347 */           sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + playername + "'s recorded tamed count is " + TextFormatting.AQUA + tamedCount, new Object[0]));
/*     */         } 
/*     */       } 
/*     */       
/* 351 */       sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "Could not find player " + TextFormatting.GREEN + playername + TextFormatting.RED + ". Please verify the player is online and/or name was entered correctly.", new Object[0]));
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 356 */     else if (args.length >= 2 && (command
/* 357 */       .equalsIgnoreCase("frequency") || command.equalsIgnoreCase("minspawn") || command.equalsIgnoreCase("maxspawn") || command
/* 358 */       .equalsIgnoreCase("maxchunk") || command.equalsIgnoreCase("canspawn"))) {
/* 359 */       MoCEntityData entityData = (MoCEntityData)MoCreatures.mocEntityMap.get(par2);
/*     */       
/* 361 */       if (entityData != null) {
/* 362 */         if (command.equalsIgnoreCase("frequency")) {
/* 363 */           if (par3 == null) {
/* 364 */             sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + entityData.getEntityName() + TextFormatting.WHITE + " frequency is " + TextFormatting.AQUA + entityData
/* 365 */                   .getFrequency() + TextFormatting.WHITE + ".", new Object[0]));
/*     */           } else {
/*     */             
/*     */             try {
/* 369 */               entityData.setFrequency(Integer.parseInt(par3));
/* 370 */               MoCProperty prop = MoCreatures.proxy.mocEntityConfig.get(entityData.getEntityName(), "frequency");
/* 371 */               prop.value = par3;
/* 372 */               saved = true;
/* 373 */               sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + entityData
/* 374 */                     .getEntityName() + TextFormatting.WHITE + " frequency to " + TextFormatting.AQUA + par3 + TextFormatting.WHITE + ".", new Object[0]));
/*     */             }
/* 376 */             catch (NumberFormatException ex) {
/* 377 */               sendCommandHelp(sender);
/*     */             } 
/*     */           } 
/* 380 */         } else if (command.equalsIgnoreCase("min") || command.equalsIgnoreCase("minspawn")) {
/* 381 */           if (par3 == null) {
/* 382 */             sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + entityData.getEntityName() + TextFormatting.WHITE + " minGroupSpawn is " + TextFormatting.AQUA + entityData
/* 383 */                   .getMinSpawn() + TextFormatting.WHITE + ".", new Object[0]));
/*     */           } else {
/*     */             
/*     */             try {
/* 387 */               entityData.setMinSpawn(Integer.parseInt(par3));
/* 388 */               MoCProperty prop = MoCreatures.proxy.mocEntityConfig.get(entityData.getEntityName(), "minspawn");
/* 389 */               prop.value = par3;
/* 390 */               saved = true;
/* 391 */               sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + entityData
/* 392 */                     .getEntityName() + TextFormatting.WHITE + " minGroupSpawn to " + TextFormatting.AQUA + par3 + TextFormatting.WHITE + ".", new Object[0]));
/*     */             }
/* 394 */             catch (NumberFormatException ex) {
/* 395 */               sendCommandHelp(sender);
/*     */             } 
/*     */           } 
/* 398 */         } else if (command.equalsIgnoreCase("max") || command.equalsIgnoreCase("maxspawn")) {
/* 399 */           if (par3 == null) {
/* 400 */             sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + entityData.getEntityName() + TextFormatting.WHITE + " maxGroupSpawn is " + TextFormatting.AQUA + entityData
/* 401 */                   .getMaxSpawn() + TextFormatting.WHITE + ".", new Object[0]));
/*     */           } else {
/*     */             
/*     */             try {
/* 405 */               entityData.setMaxSpawn(Integer.parseInt(par3));
/* 406 */               MoCProperty prop = MoCreatures.proxy.mocEntityConfig.get(entityData.getEntityName(), "maxspawn");
/* 407 */               prop.value = par3;
/* 408 */               saved = true;
/* 409 */               sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + entityData
/* 410 */                     .getEntityName() + TextFormatting.WHITE + " maxGroupSpawn to " + TextFormatting.AQUA + par3 + TextFormatting.WHITE + ".", new Object[0]));
/*     */             }
/* 412 */             catch (NumberFormatException ex) {
/* 413 */               sendCommandHelp(sender);
/*     */             } 
/*     */           } 
/* 416 */         } else if (command.equalsIgnoreCase("chunk") || command.equalsIgnoreCase("maxchunk")) {
/* 417 */           if (par3 == null) {
/* 418 */             sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + entityData.getEntityName() + TextFormatting.WHITE + " maxInChunk is " + TextFormatting.AQUA + entityData
/* 419 */                   .getMaxInChunk() + TextFormatting.WHITE + ".", new Object[0]));
/*     */           } else {
/*     */             
/*     */             try {
/* 423 */               entityData.setMaxSpawn(Integer.parseInt(par3));
/* 424 */               MoCProperty prop = MoCreatures.proxy.mocEntityConfig.get(entityData.getEntityName(), "maxchunk");
/* 425 */               prop.value = par3;
/* 426 */               saved = true;
/* 427 */               sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + entityData
/* 428 */                     .getEntityName() + TextFormatting.WHITE + " maxInChunk to " + TextFormatting.AQUA + par3 + TextFormatting.WHITE + ".", new Object[0]));
/*     */             }
/* 430 */             catch (NumberFormatException ex) {
/* 431 */               sendCommandHelp(sender);
/*     */             } 
/*     */           } 
/* 434 */         } else if (command.equalsIgnoreCase("canspawn")) {
/* 435 */           if (par3 == null) {
/* 436 */             sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + entityData.getEntityName() + TextFormatting.WHITE + " canSpawn is " + TextFormatting.AQUA + entityData
/* 437 */                   .getCanSpawn() + TextFormatting.WHITE + ".", new Object[0]));
/*     */           } else {
/*     */             
/*     */             try {
/* 441 */               entityData.setCanSpawn(Boolean.parseBoolean(par3));
/* 442 */               MoCProperty prop = MoCreatures.proxy.mocEntityConfig.get(entityData.getEntityName(), "canspawn");
/* 443 */               prop.set(par3);
/* 444 */               saved = true;
/* 445 */               sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + entityData
/* 446 */                     .getEntityName() + TextFormatting.WHITE + " canSpawn to " + TextFormatting.AQUA + par3 + TextFormatting.WHITE + ".", new Object[0]));
/*     */             }
/* 448 */             catch (NumberFormatException ex) {
/* 449 */               sendCommandHelp(sender);
/*     */             }
/*     */           
/*     */           } 
/*     */         } 
/*     */       }
/* 455 */     } else if (args.length == 1) {
/* 456 */       for (Map.Entry<String, MoCConfigCategory> catEntry : (Iterable<Map.Entry<String, MoCConfigCategory>>)config.categories.entrySet()) {
/* 457 */         String catName = ((MoCConfigCategory)catEntry.getValue()).getQualifiedName();
/* 458 */         if (catName.equalsIgnoreCase("custom-id-settings")) {
/*     */           continue;
/*     */         }
/*     */         
/* 462 */         for (Map.Entry<String, MoCProperty> propEntry : (Iterable<Map.Entry<String, MoCProperty>>)((MoCConfigCategory)catEntry.getValue()).entrySet()) {
/* 463 */           if (propEntry.getValue() == null || !((String)propEntry.getKey()).equalsIgnoreCase(command)) {
/*     */             continue;
/*     */           }
/* 466 */           List<String> propList = ((MoCProperty)propEntry.getValue()).valueList;
/* 467 */           String propValue = ((MoCProperty)propEntry.getValue()).value;
/* 468 */           if (propList == null && propValue == null) {
/*     */             continue;
/*     */           }
/* 471 */           if (par2.equals("")) {
/* 472 */             sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + (String)propEntry.getKey() + TextFormatting.WHITE + " is " + TextFormatting.AQUA + propValue, new Object[0]));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             // Byte code: goto -> 5244
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 482 */       for (Map.Entry<String, MoCConfigCategory> catEntry : (Iterable<Map.Entry<String, MoCConfigCategory>>)config.categories.entrySet()) {
/* 483 */         for (Map.Entry<String, MoCProperty> propEntry : (Iterable<Map.Entry<String, MoCProperty>>)((MoCConfigCategory)catEntry.getValue()).entrySet()) {
/* 484 */           if (propEntry.getValue() == null || !((String)propEntry.getKey()).equalsIgnoreCase(command)) {
/*     */             continue;
/*     */           }
/* 487 */           MoCProperty property = propEntry.getValue();
/* 488 */           List<String> propList = ((MoCProperty)propEntry.getValue()).valueList;
/* 489 */           String propValue = ((MoCProperty)propEntry.getValue()).getString();
/*     */           
/* 491 */           if (propList == null && propValue == null) {
/*     */             continue;
/*     */           }
/*     */           
/* 495 */           if (((MoCProperty)propEntry.getValue()).getType() == MoCProperty.Type.BOOLEAN) {
/* 496 */             if (par2.equalsIgnoreCase("true") || par2.equalsIgnoreCase("false")) {
/* 497 */               property.set(par2);
/* 498 */               saved = true;
/* 499 */               sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + (String)propEntry.getKey() + " to " + TextFormatting.AQUA + par2 + ".", new Object[0]));
/*     */             }  continue;
/*     */           } 
/* 502 */           if (((MoCProperty)propEntry.getValue()).getType() == MoCProperty.Type.INTEGER) {
/*     */             try {
/* 504 */               Integer.parseInt(par2);
/* 505 */               property.set(par2);
/* 506 */               saved = true;
/* 507 */               sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + (String)propEntry.getKey() + " to " + TextFormatting.AQUA + par2 + ".", new Object[0]));
/*     */             }
/* 509 */             catch (NumberFormatException ex) {
/* 510 */               sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "Invalid value entered. Please enter a valid number.", new Object[0]));
/*     */             } 
/*     */             continue;
/*     */           } 
/* 514 */           if (((MoCProperty)propEntry.getValue()).getType() == MoCProperty.Type.DOUBLE) {
/*     */             try {
/* 516 */               Double.parseDouble(par2);
/* 517 */               property.set(par2);
/* 518 */               saved = true;
/* 519 */               sender.sendMessage((ITextComponent)new TextComponentTranslation("Set " + TextFormatting.GREEN + (String)propEntry.getKey() + " to " + TextFormatting.AQUA + par2 + ".", new Object[0]));
/*     */             }
/* 521 */             catch (NumberFormatException ex) {
/* 522 */               sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.RED + "Invalid value entered. Please enter a valid number.", new Object[0]));
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 531 */     if (command.equalsIgnoreCase("help")) {
/* 532 */       List<String> list = getSortedPossibleCommands(sender);
/* 533 */       byte b0 = 10;
/* 534 */       int i = (list.size() - 1) / b0;
/* 535 */       int j = 0;
/*     */       
/* 537 */       if (args.length > 1) {
/*     */         try {
/* 539 */           j = (args.length == 0) ? 0 : (parseInt(args[1], 1, i + 1) - 1);
/* 540 */         } catch (NumberInvalidException numberinvalidexception) {
/* 541 */           numberinvalidexception.printStackTrace();
/*     */         } 
/*     */       }
/*     */       
/* 545 */       int k = Math.min((j + 1) * b0, list.size());
/* 546 */       sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.DARK_GREEN + "--- Showing MoCreatures help page " + 
/* 547 */             Integer.valueOf(j + 1) + " of " + Integer.valueOf(i + 1) + "(/moc help <page>)---", new Object[0]));
/*     */       
/* 549 */       for (int l = j * b0; l < k; l++) {
/* 550 */         String commandToSend = list.get(l);
/* 551 */         sender.sendMessage((ITextComponent)new TextComponentTranslation(commandToSend, new Object[0]));
/*     */       } 
/*     */     } 
/*     */     
/* 555 */     if (saved) {
/*     */       
/* 557 */       config.save();
/* 558 */       MoCreatures.proxy.readGlobalConfigValues();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<String> getSortedPossibleCommands(ICommandSender par1ICommandSender) {
/* 567 */     Collections.sort(commands);
/* 568 */     return commands;
/*     */   }
/*     */   
/*     */   public boolean teleportLoadedPet(WorldServer world, EntityPlayerMP player, int petId, String petName, ICommandSender par1ICommandSender) {
/* 572 */     for (int j = 0; j < world.loadedEntityList.size(); j++) {
/* 573 */       Entity entity = world.loadedEntityList.get(j);
/*     */       
/* 575 */       if (IMoCTameable.class.isAssignableFrom(entity.getClass()) && !((IMoCTameable)entity).getPetName().equals("") && ((IMoCTameable)entity)
/* 576 */         .getOwnerPetId() == petId) {
/*     */         
/* 578 */         NBTTagCompound compound = new NBTTagCompound();
/* 579 */         entity.writeToNBT(compound);
/* 580 */         if (compound != null && compound.getString("Owner") != null) {
/* 581 */           String owner = compound.getString("Owner");
/* 582 */           String name = compound.getString("Name");
/* 583 */           if (owner != null && owner.equalsIgnoreCase(player.getName())) {
/*     */             
/* 585 */             if (entity.dimension == player.dimension) {
/* 586 */               entity.setPosition(player.posX, player.posY, player.posZ);
/* 587 */             } else if (!player.world.isRemote) {
/*     */               
/* 589 */               Entity newEntity = EntityList.newEntity(entity.getClass(), player.world);
/* 590 */               if (newEntity != null) {
/* 591 */                 MoCTools.copyDataFromOld(newEntity, entity);
/* 592 */                 newEntity.setPosition(player.posX, player.posY, player.posZ);
/* 593 */                 DimensionManager.getWorld(player.dimension).spawnEntity(newEntity);
/*     */               } 
/* 595 */               if (entity.getRidingEntity() == null) {
/* 596 */                 entity.isDead = true;
/*     */               } else {
/*     */                 
/* 599 */                 entity.getRidingEntity().dismountRidingEntity();
/* 600 */                 entity.isDead = true;
/*     */               } 
/* 602 */               world.resetUpdateEntityTick();
/* 603 */               DimensionManager.getWorld(player.dimension).resetUpdateEntityTick();
/*     */             } 
/* 605 */             par1ICommandSender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.GREEN + name + TextFormatting.WHITE + " has been tp'd to location " + 
/* 606 */                   Math.round(player.posX) + ", " + Math.round(player.posY) + ", " + 
/* 607 */                   Math.round(player.posZ) + " in dimension " + player.dimension, new Object[0]));
/* 608 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 613 */     return false;
/*     */   }
/*     */   
/*     */   public void sendCommandHelp(ICommandSender sender) {
/* 617 */     sender.sendMessage((ITextComponent)new TextComponentTranslation("§2Listing MoCreatures commands", new Object[0]));
/* 618 */     for (int i = 0; i < commands.size(); i++) {
/* 619 */       sender.sendMessage((ITextComponent)new TextComponentTranslation(commands.get(i), new Object[0]));
/*     */     }
/*     */   }
/*     */   
/*     */   public void sendPageHelp(ICommandSender sender, byte pagelimit, ArrayList<String> list, String[] par2ArrayOfStr, String title) {
/* 624 */     int x = (list.size() - 1) / pagelimit;
/* 625 */     int j = 0;
/*     */     
/* 627 */     if (Character.isDigit(par2ArrayOfStr[par2ArrayOfStr.length - 1].charAt(0))) {
/*     */       try {
/* 629 */         j = (par2ArrayOfStr.length == 0) ? 0 : (parseInt(par2ArrayOfStr[par2ArrayOfStr.length - 1], 1, x + 1) - 1);
/* 630 */       } catch (NumberInvalidException numberinvalidexception) {
/* 631 */         numberinvalidexception.printStackTrace();
/*     */       } 
/*     */     }
/* 634 */     int k = Math.min((j + 1) * pagelimit, list.size());
/*     */     
/* 636 */     sender.sendMessage((ITextComponent)new TextComponentTranslation(TextFormatting.WHITE + title + " (pg " + TextFormatting.WHITE + 
/* 637 */           Integer.valueOf(j + 1) + TextFormatting.DARK_GREEN + "/" + TextFormatting.WHITE + Integer.valueOf(x + 1) + ")", new Object[0]));
/*     */     
/* 639 */     for (int l = j * pagelimit; l < k; l++) {
/* 640 */       String tamedInfo = list.get(l);
/* 641 */       sender.sendMessage((ITextComponent)new TextComponentTranslation(tamedInfo, new Object[0]));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\command\CommandMoCreatures.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */