package com.solvd.farm.XMLParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.List;

public interface XMLReader <T> {

    public List<T> getEnts();

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException;


    public void characters(char[] ch, int start, int length) throws SAXException;

    public void endElement(String uri, String localName, String qName) throws SAXException;


    public void startDocument() throws SAXException;
}
