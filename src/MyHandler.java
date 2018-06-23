
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


/**
 * MyHandler class defines the actions to be taken
 * in response to SAX Parser callback events.
 */

public class MyHandler extends DefaultHandler {

    //List to hold Employees object
    private List<Computer> compList = null;
    private Computer comp = null;


    // Getter method for list of computers list
    public List<Computer> getCompList() {
        return compList;
    }

    boolean bModel;
    boolean bOs;
    boolean bCpu;
    boolean bRam;
    boolean bPrice;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("Inventory")) {
            // If the list of computers is null, then initialize it
            if (compList == null)
                compList = new ArrayList<Computer>();
        } else if (qName.equalsIgnoreCase("Computer")) {
            // Create a new Computer object, and set the serial number from the attribute
            comp = new Computer();
            // Get the serialNo attribute
            String serialNumber = attributes.getValue("serialno");
            comp.setSerialNo(serialNumber);

        // Set boolean values for fields, will be used in setting Employee variables
        } else if (qName.equalsIgnoreCase("model")) {
            bModel = true;
        } else if (qName.equalsIgnoreCase("os")) {
            bOs = true;
        } else if (qName.equalsIgnoreCase("cpu")) {
            bCpu = true;
        } else if (qName.equalsIgnoreCase("ram")) {
            bRam = true;
        } else if (qName.equalsIgnoreCase("price")) {
            bPrice = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Computer")) {
            // Add the Computer object to the list
            compList.add(comp);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bModel) {
            // Set computer model age
            comp.setModel(new String(ch, start, length));
            bModel = false;
        } else if (bOs) {
            comp.setOs(new String(ch, start, length));
            bOs = false;
        } else if (bCpu) {
            comp.setCpu(new String(ch, start, length));
            bCpu = false;
        } else if (bRam) {
            comp.setRam(new String(ch, start, length));
            bRam = false;
        } else if (bPrice) {
            comp.setPrice(Double.parseDouble(new String(ch, start, length)));
            bPrice = false;
        }
    }
}
