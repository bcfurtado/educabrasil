package org.educabrasil.parsers;

import java.util.List;

import org.educabrasil.beans.Despesa;
import org.educabrasil.beans.Orcamento;
import org.educabrasil.beans.Municipio;


public class CarregarDadosMunicipio implements Runnable{

	private Municipio municipio;
	
	public CarregarDadosMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	@Override
	public void run() {
		
		
		
		/*Carregar Coordenadas*/
		LatLonParser latLonParser = new LatLonParser();
		latLonParser.parser(municipio);
		
		
		for (Orcamento orcamento : municipio.getOrcamentos()) {
			System.out.println("Municipio ID: " + municipio.getId() + " | Ano: " + orcamento.getAno());
			
			/* Carrega Orcamento */
			OrcamentoParser orcamentoParser = new OrcamentoParser();
			orcamentoParser.parser(orcamento.getAno(), municipio.getId());
			Double valor = orcamentoParser.pegarOrcamento();
			
			orcamento.setOrcamento(valor);
			orcamento.setMunicipio(municipio);
			
			/* Carrega Despesas */
			DespesaParser despesaParser = new DespesaParser();
			despesaParser.parser(orcamento.getAno(), municipio.getId());
			List<Despesa> despesas = despesaParser.pegarDespesas();
			
			for (Despesa despesa : despesas) {
				despesa.setMunicipio(municipio);
			}
			
			municipio.getDespesas().addAll(despesas);
			
		}
		
		
		
	}

}
