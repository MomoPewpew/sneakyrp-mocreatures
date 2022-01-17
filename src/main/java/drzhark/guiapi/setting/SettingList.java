/*     */ package drzhark.guiapi.setting;
/*     */ 
/*     */ import drzhark.guiapi.ModSettings;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ 
/*     */ public class SettingList
/*     */   extends Setting<ArrayList<String>>
/*     */ {
/*     */   public SettingList(String title) {
/*  22 */     this(title, new ArrayList<>());
/*     */   }
/*     */   
/*     */   public SettingList(String title, ArrayList<String> defaultvalue) {
/*  26 */     this.backendName = title;
/*  27 */     this.defaultValue = defaultvalue;
/*  28 */     this.values.put("", defaultvalue);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fromString(String s, String context) {
/*  33 */     ArrayList<String> list = new ArrayList<>();
/*     */     
/*     */     try {
/*  36 */       DocumentBuilderFactory builderFact = DocumentBuilderFactory.newInstance();
/*  37 */       builderFact.setIgnoringElementContentWhitespace(true);
/*  38 */       builderFact.setValidating(true);
/*  39 */       builderFact.setCoalescing(true);
/*  40 */       builderFact.setIgnoringComments(true);
/*  41 */       DocumentBuilder docBuilder = builderFact.newDocumentBuilder();
/*  42 */       Document doc = docBuilder.parse(s);
/*     */       
/*  44 */       Element localElement = (Element)doc.getChildNodes().item(1);
/*     */       
/*  46 */       NodeList localNodeList = localElement.getChildNodes();
/*     */       
/*  48 */       for (int i = 0; i < localNodeList.getLength(); i++) {
/*  49 */         String val = localNodeList.item(i).getNodeValue();
/*  50 */         list.add(val);
/*     */       } 
/*  52 */       this.values.put(context, list);
/*  53 */       if (this.displayWidget != null) {
/*  54 */         this.displayWidget.update();
/*     */       }
/*  56 */     } catch (Throwable throwable) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> get(String context) {
/*  63 */     if (this.values.get(context) != null)
/*  64 */       return this.values.get(context); 
/*  65 */     if (this.values.get("") != null) {
/*  66 */       return this.values.get("");
/*     */     }
/*  68 */     return this.defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(ArrayList<String> v, String context) {
/*  74 */     this.values.put(context, v);
/*  75 */     if (this.parent != null) {
/*  76 */       this.parent.save(context);
/*     */     }
/*  78 */     if (this.displayWidget != null) {
/*  79 */       this.displayWidget.update();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString(String context) {
/*     */     try {
/*  86 */       DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
/*  87 */       Document doc = docBuilder.newDocument();
/*  88 */       Element baseElement = (Element)doc.appendChild(doc.createElement("list"));
/*  89 */       ArrayList<String> prop = get(context);
/*  90 */       synchronized (prop) {
/*  91 */         Iterator<String> localIterator = prop.iterator();
/*  92 */         while (localIterator.hasNext()) {
/*  93 */           String str = localIterator.next();
/*  94 */           baseElement.appendChild(doc.createTextNode(str));
/*     */         } 
/*     */       } 
/*     */       
/*  98 */       TransformerFactory localTransformerFactory = TransformerFactory.newInstance();
/*  99 */       Transformer localTransformer = null;
/* 100 */       localTransformer = localTransformerFactory.newTransformer();
/* 101 */       localTransformer.setOutputProperty("method", "xml");
/* 102 */       localTransformer.setOutputProperty("encoding", "UTF8");
/* 103 */       DOMSource localDOMSource = new DOMSource(doc);
/* 104 */       ByteArrayOutputStream output = new ByteArrayOutputStream();
/* 105 */       StreamResult localStreamResult = new StreamResult(output);
/* 106 */       localTransformer.transform(localDOMSource, localStreamResult);
/*     */       
/* 108 */       return output.toString("UTF-8");
/* 109 */     } catch (Throwable e) {
/* 110 */       ModSettings.dbgout("Error writing SettingList from context '" + context + "': " + e);
/* 111 */       return "";
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\mami\files\games\minecraft\sneakyrp\mocreatures-fix\DrZharks MoCreatures Mod-12.0.5-deobf.jar!\drzhark\guiapi\setting\SettingList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */