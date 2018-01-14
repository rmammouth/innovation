package be.rmammouth.innovation.view;

import java.io.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import be.rmammouth.innovation.model.*;
import be.rmammouth.innovation.model.cards.*;

public class I18N
{
  public static void loadLanguage(String language)
  {
    try
    {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc=builder.parse(new File("text_"+language+".xml"));
      NodeList nlCards=doc.getDocumentElement().getChildNodes();
      for (int i=0;i<nlCards.getLength();i++)
      {
        Node node=nlCards.item(i);
        if (node instanceof Element)
        {
          Element elmCard=(Element) nlCards.item(i);
          System.out.println("loading "+elmCard.getAttribute("id"));
          Card card=Cards.get(elmCard.getAttribute("id"));
          card.setName(elmCard.getAttribute("name"));
          NodeList nlDogmas=elmCard.getElementsByTagName("dogma");
          for (int d=0;d<nlDogmas.getLength();d++)
          {
            Element elmDogma=(Element)nlDogmas.item(d);
            Dogma dogma=card.getDogmas().get(Integer.parseInt(elmDogma.getAttribute("id")));
            dogma.setText(elmDogma.getTextContent());
          }
          
        }
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
}
