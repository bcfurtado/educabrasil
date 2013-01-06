package com.github.bcfurtado.educabrasil.parsers;

import java.util.List;

import com.github.bcfurtado.educabrasil.beans.Despesa;
import com.github.bcfurtado.educabrasil.beans.Exercicio;
import com.github.bcfurtado.educabrasil.beans.Municipio;

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
		
		
		for (Integer ano : municipio.getExercicios().keySet()) {
			System.out.println("Municipio ID: " + municipio.getId() + " | Ano: " + ano);
			Exercicio exercicio = municipio.getExercicios().get(ano);
			
			/* Carrega Orcamento */
			OrcamentoParser orcamentoParser = new OrcamentoParser();
			orcamentoParser.parser(ano, municipio.getId());
			Double orcamento = orcamentoParser.pegarOrcamento();
			
			exercicio.setOrcamento(orcamento);
			
			/* Carrega Despesas */
			DespesaParser despesaParser = new DespesaParser();
			despesaParser.parser(ano, municipio.getId());
			List<Despesa> despesas = despesaParser.pegarDespesas();
			
			exercicio.setDespesas(despesas);
			
		}
		
		
		
	}

}
