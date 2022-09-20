package task3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        townXml();
        outputTownXml();
    }

    public static void townXml() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.newDocument();

            Element rootElement = doc.createElement("catalog");

            doc.appendChild(rootElement);

            Element cityTeg = doc.createElement("city");
            rootElement.appendChild(cityTeg);

            Element cityNameTag = doc.createElement("cityName");
            cityNameTag.setTextContent("Vienna");
            cityNameTag.setAttribute("size", "Big");
            cityTeg.appendChild(cityNameTag);

            Element streetNameTag = doc.createElement("streetName");
            streetNameTag.setTextContent("Pielachgasse");
            cityTeg.appendChild(streetNameTag);

            Element houseNumberTag = doc.createElement("houseNumber");
            houseNumberTag.setTextContent("3");
            cityTeg.appendChild(houseNumberTag);


            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();

            DOMSource source = new DOMSource(doc);

            StreamResult sr = new StreamResult(new File("File_Task3.xml"));


            t.transform(source, sr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void outputTownXml() {
        final String fileName = "File_Task3.xml";
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                boolean cityName = false;
                boolean streetName = false;
                boolean houseNumber = false;
                boolean cityNameSize = false;
                String attribString = "";

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("city") ) {

                        if(!attributes.equals("")){
                            attribString =  (attributes.getValue("size"));
                            cityNameSize = true;
                        }
                        cityName = true;
                    }

                    if (qName.equalsIgnoreCase("streetName") ) {
                        streetName = true;
                    }

                    if (qName.equalsIgnoreCase("houseNumber") ) {
                        houseNumber = true;
                    }
                }

                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    // Если перед этим мы отметили, что имя тэга name - значит нам надо текст использовать.
                    if (cityName) {
                        System.out.println("city Name: " + new String(ch, start, length));
                        cityName = false;
                    }

                    if(cityNameSize){
                        System.out.println("size City Name: " + attribString);
                        cityNameSize = false;
                        attribString = "";
                    }

                    if (streetName) {
                        System.out.println("street Name: " + new String(ch, start, length));
                        streetName = false;
                    }

                    if (houseNumber) {
                        System.out.println("house Number: " + new String(ch, start, length));
                        houseNumber = false;
                    }

                }
            };

            saxParser.parse(fileName, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
