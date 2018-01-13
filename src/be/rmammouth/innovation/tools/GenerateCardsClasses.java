package be.rmammouth.innovation.tools;

import java.io.*;
import java.net.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import be.rmammouth.innovation.model.*;

public class GenerateCardsClasses
{

  public static void main(String[] args) throws Exception
  {
    File genDir=new File("generated");
    if (genDir.exists()) delete(genDir);
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    File file = new File("db/cards.xml");
    Document doc = builder.parse(file);    
    NodeList cards=doc.getDocumentElement().getChildNodes();
    for (int i=0;i<cards.getLength();i++)
    {
      Node node=cards.item(i);
      if (node instanceof Element)
      {
        Element card=(Element) cards.item(i);        
        NodeList set=card.getElementsByTagName("set");
        if ((set.getLength()>0) && set.item(0).getTextContent().equals("0"))
        {
          String id=card.getAttribute("id");
          String name=card.getElementsByTagName("name").item(0).getTextContent();
          String cleanUpName=name.replaceAll(" of ", " Of ").replaceAll("[ .]", "");
          String period=card.getElementsByTagName("age").item(0).getTextContent();
          String color=card.getElementsByTagName("color").item(0).getTextContent();
          NodeList nlResources=card.getElementsByTagName("icons");
          System.err.println(id+" : "+name);
          File directory=new File("generated/p"+period);
          directory.mkdirs();
          PrintWriter pw=new PrintWriter(new FileWriter("generated/p"+period+"/"+cleanUpName+".java"));
          pw.write("package be.rmammouth.innovation.model.cards.p"+period+";\n");
          pw.write("\n");
          pw.write("import java.util.*;\n");
          pw.write("\n");
          pw.write("import be.rmammouth.innovation.model.*;\n");
          pw.write("import be.rmammouth.innovation.model.moves.*;\n");
          pw.write("\n");
          pw.write("public class "+cleanUpName+" extends Card\n");
          pw.write("{\n");          
          pw.write("  public "+cleanUpName+"()\n");
          pw.write("  {\n");
          pw.write("    super(\""+cleanUpName+"\", Period."+Period.fromInt(Integer.parseInt(period)).toString()+", Color."+color.toUpperCase()+",\n");
          pw.write("          "+getResource(nlResources.item(0).getTextContent())+",\n");
          pw.write("          "+getResource(nlResources.item(1).getTextContent())+", "+getResource(nlResources.item(2).getTextContent())+", "+getResource(nlResources.item(3).getTextContent())+");\n");
          NodeList nlDogmas=card.getElementsByTagName("dogmas");
          for (int d=0;d<nlDogmas.getLength();d++)
          {
            addDogma(pw, nlDogmas.item(d));
          }
          pw.write("  }\n");          
          pw.write("}\n");
              
          pw.close();
          
        }
      }
    }
  }
    
  private static void addDogma(PrintWriter pw, Node item)
  {
    String text=item.getTextContent();
    text=text.replaceAll("\t", "");
    text=text.replaceAll("\n", "");
    text=text.replaceAll("[ ]+", " ");
    String resource="";
    if (text.startsWith("<img src=\"/static/icons/inline-lightbulb.png")) resource="BULB";
    else if (text.startsWith("<img src=\"/static/icons/inline-castle.png")) resource="TOWER";
    else if (text.startsWith("<img src=\"/static/icons/inline-clock.png")) resource="CLOCK";
    else if (text.startsWith("<img src=\"/static/icons/inline-crown.png")) resource="CROWN";
    else if (text.startsWith("<img src=\"/static/icons/inline-factory.png")) resource="FACTORY";
    else if (text.startsWith("<img src=\"/static/icons/inline-leaf.png")) resource="LEAF";
    text=text.replaceAll("^<img src=\"/static/icons/inline-[a-z]+.png\">: ", "");
    text=text.replaceAll("<b>", "");
    text=text.replaceAll("</b>", "");
    for (int i=1;i<=10;i++)
    {
      text=text.replaceAll("<span class=age>"+i+"</span>", "["+i+"]");
    }
    text=text.replaceAll("<img src=\"/static/icons/inline-castle.png\">", "[TOWER]");
    text=text.replaceAll("<img src=\"/static/icons/inline-crown.png\">", "[CROWN]");
    text=text.replaceAll("<img src=\"/static/icons/inline-clock.png\">", "[CLOCK]");
    text=text.replaceAll("<img src=\"/static/icons/inline-lightbulb.png\">", "[BULB]");
    text=text.replaceAll("<img src=\"/static/icons/inline-factory.png\">", "[FACTORY]");
    text=text.replaceAll("<img src=\"/static/icons/inline-leaf.png\">", "[LEAF]");
    System.err.println(text);
    boolean supremacy=text.startsWith("I demand");
    
    pw.write("\n");
    pw.write("    addDogma(new "+(supremacy?"Supremacy":"Cooperation")+"Dogma(Resource."+resource+")\n");
    pw.write("    {\n");
    pw.write("      @Override\n");
    if (supremacy) pw.write("      public void activateOnPlayer(CardActivationState cas, Player affectedPlayer)\n");
    else pw.write("      public boolean activateOnPlayer(CardActivationState cas, Player player)\n");
    pw.write("      {\n");
    pw.write("      }\n");
    pw.write("    });\n");
    
  }

  static void delete(File f) throws IOException 
  {
    if (f.isDirectory())
    {
      for (File c : f.listFiles())
        delete(c);
    }
    if (!f.delete())
      throw new FileNotFoundException("Failed to delete file: " + f);
  }
  
  static String getResource(String nodeText)
  {
    if (nodeText.equals("hex")) return "null";
    if (nodeText.equals("castle")) return "Resource.TOWER";
    if (nodeText.equals("lightbulb")) return "Resource.BULB";
    return "Resource."+nodeText.toUpperCase();
  }
}
