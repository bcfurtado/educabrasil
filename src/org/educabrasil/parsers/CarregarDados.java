package org.educabrasil.parsers;

import java.util.List;

import org.educabrasil.beans.Exercicio;
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
		
		/* Define os exercicios disponiveis para a aplicacao */
		for (Municipio municipio : municipios) {
			municipio.getExercicios().add(new Exercicio(2010));
			municipio.getExercicios().add(new Exercicio(2011));
			municipio.getExercicios().add(new Exercicio(2012));
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
