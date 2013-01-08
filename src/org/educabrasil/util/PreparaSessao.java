package org.educabrasil.util;

import org.hibernate.classic.Session;
import org.educabrasil.util.GeraTabelas;

public class PreparaSessao {

	private static Session session = GeraTabelas.preparaSessao();
	
	public static Session pegarSessao(){
		if ( session == null  ) {
			session = GeraTabelas.preparaSessao(); 
		} else if ( !session.isOpen() ) {
			session = session.getSessionFactory().openSession();
		}
		return session;
	}
	
	
}
