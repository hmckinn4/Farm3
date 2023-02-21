package com.solvd.farm.XMLParser;

import com.solvd.farm.binary.Farmer;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class XMLWrapper <T> {
    private List<Farmer> farmers;

    public List<Farmer> parseXML(String fileName) {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            FarmerXMLReader handler = new FarmerXMLReader();
            reader.setContentHandler(handler);
            reader.parse(fileName);
            farmers = handler.getEnts();
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return farmers;
    }
}



