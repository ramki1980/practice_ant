
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


public class JavaSAXParse {

    // Define the file path for the XML data file
    //    Default to project root
    static final String XML_DATA_FILE_PATH = "inventory.xml";


    public static void main(String[] args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MyHandler handler = new MyHandler();
            saxParser.parse(new File(XML_DATA_FILE_PATH), handler);
            // Get Computer list
            List<Computer> compList = handler.getCompList();
            // Display it to the user
            for (Computer comp : compList)
                System.out.println(comp);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
