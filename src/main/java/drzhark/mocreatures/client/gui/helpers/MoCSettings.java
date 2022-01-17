/*     */ package drzhark.mocreatures.client.gui.helpers;
/*     */ import de.matthiasmann.twl.Widget;
/*     */ import drzhark.guiapi.ModSettings;
/*     */ import drzhark.guiapi.setting.Setting;
/*     */ import drzhark.guiapi.setting.SettingInt;
/*     */ import drzhark.guiapi.setting.SettingList;
/*     */ import drzhark.guiapi.setting.SettingMulti;
/*     */ import drzhark.guiapi.widget.WidgetInt;
/*     */ import drzhark.guiapi.widget.WidgetList;
/*     */ import drzhark.guiapi.widget.WidgetMulti;
/*     */ import drzhark.mocreatures.MoCEntityData;
/*     */ import drzhark.mocreatures.MoCreatures;
/*     */ import drzhark.mocreatures.configuration.MoCConfiguration;
/*     */ import drzhark.mocreatures.configuration.MoCProperty;
/*     */ import drzhark.mocreatures.util.MoCLog;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class MoCSettings extends ModSettings {
/*     */   public MoCSettings(String modbackendname) {
/*  26 */     super(modbackendname);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoCSettingInt addSetting(Widget w2, String nicename, String backendname, int value, int min, int max) {
/*  34 */     MoCSettingInt s = new MoCSettingInt(backendname, value, min, 1, max);
/*  35 */     WidgetInt w = new WidgetInt(s, nicename);
/*  36 */     w2.add((Widget)w);
/*  37 */     append((Setting)s);
/*  38 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoCSettingMulti addSetting(Widget w2, String nicename, String backendname, int value, String... labels) {
/*  46 */     MoCSettingMulti s = new MoCSettingMulti(backendname, value, labels);
/*  47 */     WidgetMulti w = new WidgetMulti(s, nicename);
/*  48 */     w2.add((Widget)w);
/*  49 */     append((Setting)s);
/*  50 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoCSettingList addSetting(Widget w2, String nicename, String backendname, String... options) {
/*  59 */     ArrayList<String> arrayList = new ArrayList<>();
/*     */     
/*  61 */     for (int i = 0; i < options.length; i++) {
/*  62 */       arrayList.add(options[i]);
/*     */     }
/*     */     
/*  65 */     MoCSettingList s = new MoCSettingList(backendname, arrayList);
/*  66 */     WidgetList w = new WidgetList(s, nicename);
/*  67 */     w2.add((Widget)w);
/*  68 */     append((Setting)s);
/*  69 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoCSettingList addSetting(Widget w2, String nicename, String backendname, ArrayList<String> options) {
/*  77 */     MoCSettingList s = new MoCSettingList(backendname, options);
/*  78 */     WidgetList w = new WidgetList(s, nicename);
/*  79 */     w2.add((Widget)w);
/*  80 */     append((Setting)s);
/*  81 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MoCSettingList addSetting(Widget w2, String nicename, String backendname, ArrayList<String> options, MoCConfiguration config, String category) {
/*  89 */     MoCSettingList s = new MoCSettingList(config, category, backendname, options);
/*  90 */     WidgetList w = new WidgetList(s, nicename);
/*  91 */     w2.add((Widget)w);
/*  92 */     append((Setting)s);
/*  93 */     return s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void load(String context) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void save(String context, String backendName, String category, MoCConfiguration config) {
/* 117 */     if (!this.settingsLoaded) {
/*     */       return;
/*     */     }
/*     */     try {
/* 121 */       File path = ModSettings.getAppDir("/" + (String)ModSettings.contextDatadirs.get(context) + "/" + this.backendname + "/");
/* 122 */       ModSettings.dbgout("saving context " + context + " (" + path.getAbsolutePath() + " [" + (String)ModSettings.contextDatadirs.get(context) + "])");
/* 123 */       if (!path.exists()) {
/* 124 */         path.mkdirs();
/*     */       }
/*     */       
/* 127 */       Setting z = null;
/* 128 */       for (int i = 0; i < this.Settings.size(); i++) {
/* 129 */         z = this.Settings.get(i);
/* 130 */         if (z.backendName.equals(backendName)) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */       
/* 135 */       if (config == MoCreatures.proxy.mocEntityConfig) {
/* 136 */         for (Map.Entry<String, MoCProperty> propEntry : (Iterable<Map.Entry<String, MoCProperty>>)config.getCategory(category.toLowerCase()).getValues().entrySet()) {
/*     */           
/* 138 */           MoCEntityData entityData = (MoCEntityData)MoCreatures.mocEntityMap.get(category);
/*     */           
/* 140 */           if (entityData != null && z.backendName.contains(propEntry.getKey())) {
/* 141 */             MoCProperty property = propEntry.getValue();
/* 142 */             if (((String)propEntry.getKey()).equalsIgnoreCase("type")) {
/* 143 */               if (MoCreatures.proxy.debug) {
/* 144 */                 MoCLog.logger.info("setting type to " + z.toString(context));
/*     */               }
/*     */               
/* 147 */               if (entityData.getType() != null) {
/* 148 */                 if (z.toString(context).equalsIgnoreCase("CREATURE")) {
/* 149 */                   entityData.setType(EnumCreatureType.CREATURE);
/* 150 */                 } else if (z.toString(context).equalsIgnoreCase("MONSTER")) {
/* 151 */                   entityData.setType(EnumCreatureType.MONSTER);
/* 152 */                 } else if (z.toString(context).equalsIgnoreCase("WATERCREATURE")) {
/* 153 */                   entityData.setType(EnumCreatureType.WATER_CREATURE);
/* 154 */                 } else if (z.toString(context).equalsIgnoreCase("AMBIENT")) {
/* 155 */                   entityData.setType(EnumCreatureType.AMBIENT);
/*     */                 } 
/*     */               }
/* 158 */               property.value = z.toString(context); break;
/*     */             } 
/* 160 */             if (((String)propEntry.getKey()).equalsIgnoreCase("frequency")) {
/* 161 */               if (MoCreatures.proxy.debug) {
/* 162 */                 MoCLog.logger.info("setting frequency to " + z.toString(context));
/*     */               }
/* 164 */               entityData.setFrequency(Integer.parseInt(z.toString(context)));
/* 165 */               property.value = z.toString(context); break;
/*     */             } 
/* 167 */             if (((String)propEntry.getKey()).equalsIgnoreCase("minspawn")) {
/* 168 */               if (MoCreatures.proxy.debug) {
/* 169 */                 MoCLog.logger.info("setting min to " + z.toString(context));
/*     */               }
/* 171 */               entityData.setMinSpawn(Integer.parseInt(z.toString(context)));
/* 172 */               property.value = z.toString(context); break;
/*     */             } 
/* 174 */             if (((String)propEntry.getKey()).equalsIgnoreCase("maxspawn")) {
/* 175 */               if (MoCreatures.proxy.debug) {
/* 176 */                 MoCLog.logger.info("setting max to " + z.toString(context));
/*     */               }
/* 178 */               entityData.setMaxSpawn(Integer.parseInt(z.toString(context)));
/* 179 */               property.value = z.toString(context); break;
/*     */             } 
/* 181 */             if (((String)propEntry.getKey()).equalsIgnoreCase("maxchunk")) {
/* 182 */               if (MoCreatures.proxy.debug) {
/* 183 */                 MoCLog.logger.info("setting chunk to " + z.toString(context));
/*     */               }
/* 185 */               entityData.setMaxInChunk(Integer.parseInt(z.toString(context)));
/* 186 */               property.value = z.toString(context); break;
/*     */             } 
/* 188 */             if (((String)propEntry.getKey()).equalsIgnoreCase("canspawn")) {
/* 189 */               if (MoCreatures.proxy.debug) {
/* 190 */                 MoCLog.logger.info("setting canspawn to " + z.toString(context));
/*     */               }
/* 192 */               entityData.setCanSpawn(Boolean.parseBoolean(z.toString(context)));
/* 193 */               property.value = z.toString(context);
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } else {
/* 200 */         for (Map.Entry<String, MoCProperty> propEntry : (Iterable<Map.Entry<String, MoCProperty>>)config.getCategory(category).getValues().entrySet()) {
/* 201 */           if (((String)propEntry.getKey()).equalsIgnoreCase(z.backendName)) {
/* 202 */             MoCProperty property = propEntry.getValue();
/* 203 */             property.value = z.toString(context);
/* 204 */             if (MoCreatures.proxy.debug) {
/* 205 */               MoCLog.logger.info("set config value " + z.backendName + " to " + property.value);
/*     */             }
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 213 */       config.save();
/* 214 */       MoCreatures.proxy.readGlobalConfigValues();
/* 215 */     } catch (Exception e) {
/* 216 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\mocreatures\client\gui\helpers\MoCSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */