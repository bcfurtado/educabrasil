package org.educabrasil.controller;

import java.util.List;

import org.educabrasil.beans.Municipio;
import org.educabrasil.util.PreparaSessao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class ControladorMunicipios {

	private Session session;
	
	public ControladorMunicipios(){

	}
	
	
	public List<Municipio> listarMunicipios(){
		session = PreparaSessao.pegarSessao();
		
		Criteria criteria = session.createCriteria(Municipio.class);
		
		List<Municipio> municipios = criteria.list();
		session.close();
		return municipios;
		
	}
	
	public Municipio pegarMunicipio( String id ){
		session = PreparaSessao.pegarSessao();
		
		Criteria criteria = session.createCriteria(Municipio.class)
				.add(Restrictions.eq("id", id ));
		
		Municipio municipio = (Municipio) criteria.uniqueResult();
		session.close();
		return municipio;
		
	}
	
	

	
}
