package org.educabrasil.parsers;

import java.util.List;

import org.educabrasil.beans.Despesa;
import org.educabrasil.beans.Exercicio;
import org.educabrasil.beans.Municipio;


public class CarregarDadosMunicipio implements Runnable{

	private Municipio municipio;
	
	public CarregarDadosMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	@Override
	public void run() {
		
		
		
		/*Carregar Coordenadas*/
		// ...
//		LatLonParser latLonParser = new LatLonParser();
//		latLonParser.parser(municipio);
		
		
		for (Exercicio exercicio : municipio.getExercicios()) {
			System.out.println("Municipio ID: " + municipio.getId() + " | Ano: " + exercicio.getAno());
			
			/* Carrega Orcamento */
			OrcamentoParser orcamentoParser = new OrcamentoParser();
			orcamentoParser.parser(exercicio.getAno(), municipio.getId());
			Double orcamento = orcamentoParser.pegarOrcamento();
			
			exercicio.setOrcamento(orcamento);
			
			/* Carrega Despesas */
			DespesaParser despesaParser = new DespesaParser();
			despesaParser.parser(exercicio.getAno(), municipio.getId());
			List<Despesa> despesas = despesaParser.pegarDespesas();
			
			exercicio.setDespesas(despesas);
			
		}
		
		
		
	}

}
