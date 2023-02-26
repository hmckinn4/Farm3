package com.solvd.farm.XMLParser;

import com.solvd.farm.binary.Farmer;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

public class FarmerXMLReader implements ContentHandler {
    private List<Farmer> farmers;
    private Farmer farmer;
    private StringBuilder content;

    public FarmerXMLReader() {
        farmers = new ArrayList<>();
    }

    public List<Farmer> getEnts() {
        return farmers;
    }

    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("farmer")) {
            farmer = new Farmer(1, "", "", "");
        }
        content = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content.append(ch, start, length);
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("farmer")) {
            farmers.add(farmer);
        } else if (qName.equals("farmer_name")) {
            farmer.setName(content.toString());
        } else if (qName.equals("farmer_address")) {
            farmer.setAddress(content.toString());
        } else if (qName.equals("farmer_phone")) {
            farmer.setPhone(content.toString());
        }
    }

    // other methods omitted for brevity
}
