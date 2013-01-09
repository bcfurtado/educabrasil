package org.educabrasil.controller;

import org.educabrasil.beans.Despesa;
import org.educabrasil.beans.Municipio;
import org.educabrasil.util.PreparaSessao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class ControladorDespesas {

	private Session session;
	
	public ControladorDespesas() {
	}
	
	public Double pegarDespesaTotalEmEducacao(Municipio municipio, Integer ano){
		
		session = PreparaSessao.pegarSessao();
		
		
		Criteria criteria = session.createCriteria(Despesa.class)
				.add(Restrictions.eq("ano", ano))
				.add(Restrictions.eq("municipio", municipio))
				.setProjection(Projections.sum("valor"));
		
		Double valor = (Double) criteria.uniqueResult();
		
		session.close();
		return valor;
		
	}
	
}
