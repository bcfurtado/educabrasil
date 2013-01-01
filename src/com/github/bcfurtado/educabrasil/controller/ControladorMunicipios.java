package com.github.bcfurtado.educabrasil.controller;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.github.bcfurtado.educabrasil.beans.Municipio;
import com.github.bcfurtado.educabrasil.parsers.MunicipiosParser;

public class ControladorMunicipios {

	private List<Municipio> municipios;
	
	public ControladorMunicipios(){
		
	}
	
	
	public List<Municipio> listarMunicipios(){
		if ( municipios != null ) {
			return municipios;
		} else {
			MunicipiosParser municipiosParser = new MunicipiosParser();
			try {
				municipiosParser.parser();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			municipios = municipiosParser.pegarMunicipios();
			return municipios;
		}
	}
	
//	public static void main(String[] args) {
//		List<Municipio> municipios;
//		ControladorMunicipios controladorMunicipios = new ControladorMunicipios();
//		municipios = controladorMunicipios.listarMunicipios();
//		
////		for (Municipio municipio : municipios) {
////			System.out.println("ID: " + municipio.getId() );
////			System.out.println("Nome: " + municipio.getNome() );
////			System.out.println("GeoCode Id: " + municipio.getGeoNameId() );
////			System.out.println("Latitude: " + municipio.getLatitude() );
////		}
	
	
//	}
	
}
