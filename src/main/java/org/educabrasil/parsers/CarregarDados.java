package org.educabrasil.parsers;

import java.util.ArrayList;
import java.util.List;

import org.educabrasil.beans.Despesa;
import org.educabrasil.beans.Orcamento;
import org.educabrasil.beans.Municipio;


public class CarregarDados implements Runnable {

	private List<Municipio> municipios;
	
	public CarregarDados() {
		
	}

	public List<Municipio> pegarMunicipios(){
		return municipios;
	}
	
	@Override
	public void run() {
		/* Carrega os Municipios */
		MunicipiosParser municipiosParser = new MunicipiosParser();
		municipiosParser.parser();
		
		municipios = municipiosParser.pegarMunicipios();
		
//		municipios = new ArrayList<Municipio>();
//		Municipio m = new  Municipio();
//		m.setNome("Fortaleza");
//		m.setId("057");
//		m.setDespesas(new ArrayList<Despesa>());
//		m.setOrcamentos(new ArrayList<Orcamento>());
//
//		municipios.add(m);
		/* Define os exercicios disponiveis para a aplicacao */
		for (Municipio municipio : municipios) {
			municipio.getOrcamentos().add(new Orcamento(2010));
			municipio.getOrcamentos().add(new Orcamento(2011));
			municipio.getOrcamentos().add(new Orcamento(2012));
			municipio.getOrcamentos().add(new Orcamento(2013));
			municipio.getOrcamentos().add(new Orcamento(2014));
		}

		/* Carrega os dados dos municipios */
		for (Municipio municipio : municipios){
			try {
				Thread.sleep(500);
				new Thread(new CarregarDadosMunicipio(municipio)).start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Todas as threads disparadas.");
		
	}

}
