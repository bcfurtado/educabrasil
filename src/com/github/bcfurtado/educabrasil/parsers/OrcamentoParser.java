package com.github.bcfurtado.educabrasil.parsers;

import java.io.IOException;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class OrcamentoParser extends DefaultHandler{

	private static final String ELEMENT_ORCAMENTO = "valor_total_fixado_orcamento";
	
	private Stack<String> pilha;
	
	private Double orcamento;
	
	public OrcamentoParser() {
		pilha = new Stack<String>();
	}
	
	
	public void parser(Integer ano, String idMunicipio) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			parser.parse("http://api.tcm.ce.gov.br/sim/1_0/dados_orcamentos.xml?codigo_municipio="+idMunicipio+"&exercicio_orcamento="+ano+"00", this);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Double pegarOrcamento(){
		return orcamento;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ( qName.equalsIgnoreCase(ELEMENT_ORCAMENTO) ){
			pilha.push(ELEMENT_ORCAMENTO);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ( qName.equalsIgnoreCase(ELEMENT_ORCAMENTO) ){
			pilha.pop();
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String string = new String(ch, start, length);
		if ( !pilha.empty() ) {
			if ( pilha.peek().equalsIgnoreCase(ELEMENT_ORCAMENTO) ){
				orcamento = Double.parseDouble(string);
			}
		}
	}

}
