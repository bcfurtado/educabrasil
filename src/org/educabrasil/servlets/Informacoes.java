package org.educabrasil.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.educabrasil.beans.Despesa;
import org.educabrasil.beans.Municipio;
import org.educabrasil.beans.Orcamento;
import org.educabrasil.controller.ControladorDespesas;
import org.educabrasil.controller.ControladorMunicipios;
import org.educabrasil.controller.ControladorOrcamentos;

public class Informacoes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ControladorMunicipios controladorMunicipios;
	private ControladorDespesas controladorDespesas;
	private ControladorOrcamentos controladorOrcamentos;
	
    public Informacoes() {
    	controladorMunicipios = new ControladorMunicipios();
    	controladorDespesas = new ControladorDespesas();
    	controladorOrcamentos = new ControladorOrcamentos();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod_municipio = request.getParameter("cod_mun");
		String ano = request.getParameter("ano");
		
		List<Municipio> municipios = controladorMunicipios.listarMunicipios();
		Municipio municipio = null;
		List<Despesa> despesas = null;
		List<Orcamento> orcamentos = null;

		if ( cod_municipio != null && ano != null) {
			municipio = controladorMunicipios.pegarMunicipio(cod_municipio);
			despesas = controladorDespesas.pegarDepesasDeEducacaoDoMunicio(municipio,Integer.parseInt(ano));
			orcamentos = controladorOrcamentos.pegarTodosOsOrcamentosDoMunicipio(municipio);
			
		} else	if ( cod_municipio != null && ano == null) {
			municipio = controladorMunicipios.pegarMunicipio(cod_municipio);
			despesas = controladorDespesas.pegarDepesasDeEducacaoDoMunicio(municipio,2012);
			orcamentos = controladorOrcamentos.pegarTodosOsOrcamentosDoMunicipio(municipio);

		} else {
			municipio = controladorMunicipios.pegarMunicipio("057"); //Fortaleza
			despesas = controladorDespesas.pegarDepesasDeEducacaoDoMunicio(municipio,2012);
			orcamentos = controladorOrcamentos.pegarTodosOsOrcamentosDoMunicipio(municipio);
			
		}

		request.setAttribute("municipio", municipio);
		request.setAttribute("despesas", despesas);
		request.setAttribute("orcamentos", orcamentos);

		request.setAttribute("municipios", municipios);
		request.getRequestDispatcher("informacoes.jsp").forward(request, response);
	}

}
