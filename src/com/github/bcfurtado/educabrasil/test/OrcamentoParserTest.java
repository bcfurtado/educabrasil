package com.github.bcfurtado.educabrasil.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.bcfurtado.educabrasil.parsers.OrcamentoParser;

public class OrcamentoParserTest {

	@Test
	public void testaOrcamentoDeFortalezaDe2012() {
		
		OrcamentoParser orcamentoParser = new OrcamentoParser();
		orcamentoParser.parser(2012, "057");
		Double orcamento = orcamentoParser.pegarOrcamento();
		
		Double orcamentoFortaleza = Double.parseDouble("5056974000.00");
		assertEquals(orcamentoFortaleza, orcamento);
	}

}

