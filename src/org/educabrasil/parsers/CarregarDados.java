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
			municipio.getExercicios().put(2010, new Exercicio());
			municipio.getExercicios().put(2011, new Exercicio());
			municipio.getExercicios().put(2012, new Exercicio());
			
		}

		/* Carrega os dados dos municipios */
		for (Municipio municipio : municipios){
			try {
				Thread.sleep(200);
				new Thread(new CarregarDadosMunicipio(municipio)).start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Todas as threads disparadas.");
		
	}

}
