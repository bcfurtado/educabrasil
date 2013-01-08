package org.educabrasil.parsers;

import java.io.IOException;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.educabrasil.beans.Municipio;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class LatLonParser extends DefaultHandler {
		
	//private static final String URL = "http://api.geonames.org/getJSON?geonameId=3390906&username=demo";
	private static final String USER = "bcfurtado";
	
	private static final String ELEMENT_LATITUDE = "lat";
	private static final String ELEMENT_LONGITUDE = "lng";

	private Stack<String> pilha;
	
	private Municipio municipio;
	public LatLonParser() {
		
	}
	
	public Municipio parser( Municipio municipio ){
		this.municipio = municipio;
		pilha = new Stack<String>();
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			parser.parse("http://api.geonames.org/get?geonameId="+municipio.getGeoNameId()+"&username="+USER+"", this);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return municipio;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ( qName.equalsIgnoreCase(ELEMENT_LATITUDE) ){
			pilha.push(ELEMENT_LATITUDE);
		} else if ( qName.equalsIgnoreCase(ELEMENT_LONGITUDE) ){
			pilha.push(ELEMENT_LONGITUDE);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ( qName.equalsIgnoreCase(ELEMENT_LATITUDE) ){
			pilha.pop();
		} else if ( qName.equalsIgnoreCase(ELEMENT_LONGITUDE) ){
			pilha.pop();
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String string = new String(ch, start, length);
		if ( !pilha.empty() ) {
			if ( pilha.peek().equalsIgnoreCase(ELEMENT_LATITUDE) ){
				municipio.setLatitude(Double.parseDouble(string));
			} else if ( pilha.peek().equalsIgnoreCase(ELEMENT_LONGITUDE) ){
				municipio.setLongitude(Double.parseDouble(string));
			}
		}
	}
}
