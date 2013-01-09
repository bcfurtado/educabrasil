package org.educabrasil.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.educabrasil.beans.Despesa;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class DespesaParser extends DefaultHandler{
	
	private static final String ELEMENT_DESPESA = "despesa_projeto_atividade";
	private static final String ELEMENT_DESPESA_NOME = "nome_projeto_atividade";
	private static final String ELEMENT_DESPESA_DESCRICAO = "descricao_projeto_atividade";
	private static final String ELEMENT_DESPESA_VALOR = "valor_total_fixado_projeto_atividade";
	
	private Integer ano;
	
	private List<Despesa> despesas;
	private Stack<String> pilha;
	
	private Despesa despesa;
	
	public DespesaParser() {
		despesas = new ArrayList<Despesa>();
		pilha = new Stack<String>();
	}
	
	
	public void parser(Integer ano, String idMunicipio) {
		this.ano = ano;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			
			String URL = "http://api.tcm.ce.gov.br/sim/1_0/despesa_projeto_atividade.xml?codigo_municipio="+idMunicipio+"&exercicio_orcamento="+ano+"00&codigo_funcao=12";
			
			InputStream inputSteam = getHttpStream(URL);
			Reader reader = new InputStreamReader(inputSteam,"UTF-8");
			
			InputSource is = new InputSource(reader);
			is.setEncoding("UTF-8");
			
			parser.parse(is, this);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Despesa> pegarDespesas(){
		return despesas;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ( qName.equalsIgnoreCase(ELEMENT_DESPESA) ){
			despesa = new Despesa();
			despesa.setAno(ano);
			pilha.push(ELEMENT_DESPESA);
		} else if ( qName.equalsIgnoreCase(ELEMENT_DESPESA_NOME) ){
			pilha.push(ELEMENT_DESPESA_NOME);
		} else if ( qName.equalsIgnoreCase(ELEMENT_DESPESA_DESCRICAO) ){
			pilha.push(ELEMENT_DESPESA_DESCRICAO);
		} else if ( qName.equalsIgnoreCase(ELEMENT_DESPESA_VALOR) ) {
			pilha.push(ELEMENT_DESPESA_VALOR);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ( qName.equalsIgnoreCase(ELEMENT_DESPESA) ){
			despesas.add(despesa);
			pilha.pop();
		} else if ( qName.equalsIgnoreCase(ELEMENT_DESPESA_NOME) ){
			pilha.pop();
		} else if ( qName.equalsIgnoreCase(ELEMENT_DESPESA_DESCRICAO) ){
			pilha.pop();
		} else if ( qName.equalsIgnoreCase(ELEMENT_DESPESA_VALOR) ) {
			pilha.pop();
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String string = new String(ch, start, length);
		if ( !pilha.empty() ) {
			if ( pilha.peek().equalsIgnoreCase(ELEMENT_DESPESA_NOME) ){
				despesa.setNome(string);
			} else if ( pilha.peek().equalsIgnoreCase(ELEMENT_DESPESA_DESCRICAO) ){
				despesa.setDescricao(string);
			} else if ( pilha.peek().equalsIgnoreCase(ELEMENT_DESPESA_VALOR) ) {
				despesa.setValor(Double.parseDouble(string));
			}
		}
	}
	

	public InputStream getHttpStream(String urlString) throws IOException {
		InputStream in = null;
		int response = -1;

		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();

			response = httpConn.getResponseCode();

			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (Exception e) {
			throw new IOException("Error connecting");
		}

		return in;
	}

}