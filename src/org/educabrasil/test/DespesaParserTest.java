package org.educabrasil.test;

import static org.junit.Assert.*;

import java.util.List;

import org.educabrasil.beans.Despesa;
import org.educabrasil.parsers.DespesaParser;
import org.junit.Test;


public class DespesaParserTest {

	@Test
	public void testaTotalDeDespesasComEducacaoEmFortalezaEm2012() {
		DespesaParser despesaParser = new DespesaParser();
		despesaParser.parser(2012, "057");
		List<Despesa> despesas = despesaParser.pegarDespesas();
		
		Double despesasTotal = 0.0;
		for (Despesa despesa : despesas) {
			System.out.println("Nome: " + despesa.getNome() + " | Descrição: " + despesa.getDescricao());
			despesasTotal += despesa.getValor();
		}
		
		Double despesaTotalEsperada = Double.parseDouble("9.05575E8");
		assertEquals(despesaTotalEsperada, despesasTotal);
	
	}

}
