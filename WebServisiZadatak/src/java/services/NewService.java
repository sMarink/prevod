package services;

import java.io.IOException;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
@Path("/Translation")
public class NewService {

    @POST
    @Path("translate")
    public String doGet(
            @FormParam("word") String word,
            @FormParam("language") String language
    ) throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        dbf.setIgnoringElementContentWhitespace(true);

        Document doc = db.parse("C:\\Users\\Sava\\Desktop\\WebServisiZadatak\\src\\java\\words\\wordlist.xml");
        Element xmlword = doc.getElementById(word);
        NodeList xmllang = xmlword.getElementsByTagName(language);
        Element eng = (Element) xmllang.item(0);
        Element srb = (Element) xmllang.item(0);

        String trToEng = eng.getTextContent();
        String trToSrb = srb.getTextContent();

        if (language.equals("serbian")) {

            return "<html><body><h1>Translation Result : </h1> <hr></body></html> From  English to Serbian/Sa engleskog na srpski : " + "'" + word + "'" + " is " + "'" + "'" + trToSrb + "'.";
        }

        if (language.equals("english")) {

            return "<html><body><h1>Translation Result : </h1> <hr></body></html> From  Serbian to English/Sa srpskog na engleski : " + "'" + word + "'" + " is " + "'" + "'" + trToEng + "'.";
        }

        return "Invalid";

    }
}
