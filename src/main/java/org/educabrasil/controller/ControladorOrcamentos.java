package org.educabrasil.controller;

import java.util.List;

import org.educabrasil.beans.Municipio;
import org.educabrasil.beans.Orcamento;
import org.educabrasil.util.PreparaSessao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class ControladorOrcamentos {

	private Session session;
	
	public Double pegarOrcamento(Municipio municipio, Integer ano){
		session = PreparaSessao.pegarSessao();
		
		
		Criteria criteria = session.createCriteria(Orcamento.class)
				.add(Restrictions.eq("ano", ano))
				.add(Restrictions.eq("municipio", municipio));
		
		Orcamento orcamento = (Orcamento)criteria.uniqueResult();
		
		session.close();
		if ( orcamento != null ) {
			return orcamento.getOrcamento();
		} else {
			return null;
		}
	}
	
	public List<Orcamento> pegarTodosOsOrcamentosDoMunicipio(Municipio municipio){
		session = PreparaSessao.pegarSessao();
		
		Criteria criteria = session.createCriteria(Orcamento.class)
				.add(Restrictions.eq("municipio", municipio));
		
		List<Orcamento> orcamentos = (List<Orcamento>)criteria.list();
		
		session.close();
		
		return orcamentos;
	}
}
