package com.github.bcfurtado.educabrasil.controller;

import java.util.List;

import com.github.bcfurtado.educabrasil.beans.Municipio;
import com.github.bcfurtado.educabrasil.parsers.CarregarDados;
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
			municipiosParser.parser();

			municipios = municipiosParser.pegarMunicipios();
			return municipios;
		}
	}
	
	public static void main(String[] args) {
		List<Municipio> municipios;
		CarregarDados carregarDados = new CarregarDados();
		new Thread(carregarDados).start();
		
		try {
			Thread.sleep(4 * 60 * 1000);
			
			municipios = carregarDados.pegarMunicipios();
			for (Municipio municipio : municipios) {
				System.out.println("ID: " + municipio.getId() );
				System.out.println("Nome: " + municipio.getNome() );
//				System.out.println("GeoCode Id: " + municipio.getGeoNameId() );
//				System.out.println("Latitude: " + municipio.getLatitude() );
				System.out.println("Orcamento: " + municipio.getExercicios().get(2012).getOrcamento());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
}
