package org.educabrasil.util;

import java.util.ArrayList;
import java.util.List;

import org.educabrasil.beans.Despesa;
import org.educabrasil.beans.Exercicio;
import org.educabrasil.beans.Municipio;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.hibernate.tool.hbm2ddl.SchemaExport;


public class GeraTabelas {

	public static void exportarEsquema(List<Class<? extends Object>> classes) {
		AnnotationConfiguration annotConfig = adicionaClassesConfiguracao(classes);

		SchemaExport se = new SchemaExport(annotConfig);
		se.create(true, true);
	}

	private static AnnotationConfiguration adicionaClassesConfiguracao(
			List<Class<? extends Object>> classes) {
		AnnotationConfiguration annotConfig = new AnnotationConfiguration(); // dom4j.jar,
																				// slf4j
																				// api
																				// e
																				// log4j

		for (Class classe : classes) {
			annotConfig.addAnnotatedClass(classe);
		}
		return annotConfig;
	}

	static Session preparaSessao() {
		AnnotationConfiguration annotConfig = adicionaClassesConfiguracao(initialize());
		SessionFactory sf = annotConfig.buildSessionFactory();
		Session session = sf.openSession();
		return session;
	}

	static void reiniciaEsquemaBD() {
		exportarEsquema(initialize());
	}

	private static List<Class<? extends Object>> initialize() {
		List<Class<? extends Object>> classes = new ArrayList<Class<? extends Object>>();

		// classes.add(SuaClasseAqui.class);
		classes.add(Municipio.class);
		classes.add(Exercicio.class);
		classes.add(Despesa.class);


		return classes;
	}

}
