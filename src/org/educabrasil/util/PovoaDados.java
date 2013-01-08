package org.educabrasil.util;

import java.util.List;

import org.educabrasil.beans.Municipio;
import org.educabrasil.parsers.CarregarDados;
import org.educabrasil.util.GeraTabelas;
import org.hibernate.Session;

public class PovoaDados {
	
	public static void main (String[] args){

		System.out.println("Carregando dados dos municipios");
		CarregarDados carregarDados = new CarregarDados();
		
		new Thread(carregarDados).start();
		
		try {
			Thread.sleep(4 * 60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Salvando dados dos municipios no banco de dados local");
		List<Municipio> municipios = carregarDados.pegarMunicipios();

		
		System.out.println("Criando Esquema do Banco de Dados");
		GeraTabelas.reiniciaEsquemaBD();
		
		Session session = PreparaSessao.pegarSessao();
		session.beginTransaction();
		
		for (Municipio municipio : municipios) {
			session.save(municipio);
		}
		
		
		session.getTransaction().commit();
		
		session.getSessionFactory().close();
		session.close();
		System.out.println("Concluido");
	}
	
	public static void instalar(){
		GeraTabelas.reiniciaEsquemaBD();
	}
	
}
