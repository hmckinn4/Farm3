package com.solvd.farm.XMLParser;

import com.solvd.farm.binary.Farmer;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.util.List;

public class XMLWrapper {
    private List<Farmer> farmers;

    public List<Farmer> parseXML(String fileName) {
        try {
            System.out.println("Parsing " + fileName);
            XMLReader reader = XMLReaderFactory.createXMLReader();
            FarmerXMLReader handler = new FarmerXMLReader();
            reader.setContentHandler(handler);
            reader.parse(fileName);
            farmers = handler.getEnts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return farmers;
    }
}
