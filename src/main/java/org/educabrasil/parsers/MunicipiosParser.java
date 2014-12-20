package org.educabrasil.parsers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.educabrasil.beans.Municipio;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class MunicipiosParser extends DefaultHandler {
	
	private static final String URL = "http://api.tcm.ce.gov.br/sim/1_0/municipios.xml";	
	private static final String ELEMENT_MUNICIPIO = "municipios";
	private static final String ELEMENT_ID_MUNICIPIO = "codigo_municipio";
	private static final String ELEMENT_NOME_MUNICIPIO = "nome_municipio";
	private static final String ELEMENT_GEO_MUNICIPIO = "geonamesId";
	
	private List<Municipio> municipios;
	private Stack<String> pilha;
	
	private Municipio municipio;
	
	public MunicipiosParser() {
		municipios = new ArrayList<Municipio>();
		pilha = new Stack<String>();
	}
	
	
	public void parser() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			//parser.parse(URL, this);
			parser.parse(new File("src/main/resources/municipios.xml"), this);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Municipio> pegarMunicipios(){
		return municipios;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ( qName.equalsIgnoreCase(ELEMENT_MUNICIPIO) ){
			municipio = new Municipio();
			pilha.push(ELEMENT_MUNICIPIO);
		} else if ( qName.equalsIgnoreCase(ELEMENT_ID_MUNICIPIO) ){
			pilha.push(ELEMENT_ID_MUNICIPIO);
		} else if ( qName.equalsIgnoreCase(ELEMENT_NOME_MUNICIPIO) ){
			pilha.push(ELEMENT_NOME_MUNICIPIO);
		} else if ( qName.equalsIgnoreCase(ELEMENT_GEO_MUNICIPIO) ) {
			pilha.push(ELEMENT_GEO_MUNICIPIO);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ( qName.equalsIgnoreCase(ELEMENT_MUNICIPIO) ){
			municipios.add(municipio);
			pilha.pop();
		} else if ( qName.equalsIgnoreCase(ELEMENT_ID_MUNICIPIO) ){
			pilha.pop();
		} else if ( qName.equalsIgnoreCase(ELEMENT_NOME_MUNICIPIO) ){
			pilha.pop();
		} else if ( qName.equalsIgnoreCase(ELEMENT_GEO_MUNICIPIO) ) {
			pilha.pop();
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String string = new String(ch, start, length);
		if ( !pilha.empty() ) {
			if ( pilha.peek().equalsIgnoreCase(ELEMENT_ID_MUNICIPIO) ){
				municipio.setId(string);
			} else if ( pilha.peek().equalsIgnoreCase(ELEMENT_NOME_MUNICIPIO) ){
				municipio.setNome(string);
			} else if ( pilha.peek().equalsIgnoreCase(ELEMENT_GEO_MUNICIPIO) ) {
				municipio.setGeoNameId(Long.parseLong(string));
			}
		}
	}

}
