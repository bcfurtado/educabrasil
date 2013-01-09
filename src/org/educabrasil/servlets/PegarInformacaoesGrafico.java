package org.educabrasil.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.educabrasil.beans.Municipio;
import org.educabrasil.controller.ControladorDespesas;
import org.educabrasil.controller.ControladorMunicipios;
import org.educabrasil.controller.ControladorOrcamentos;

public class PegarInformacaoesGrafico extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ControladorMunicipios controladorMunicipios;
	private ControladorDespesas controladorDespesas;
	private ControladorOrcamentos controladorOrcamentos;
	
    public PegarInformacaoesGrafico() {
    	controladorMunicipios = new ControladorMunicipios();
    	controladorDespesas = new ControladorDespesas();
    	controladorOrcamentos = new ControladorOrcamentos();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String anoString = request.getParameter("ano");

		if ( id != null  || anoString != null || id.isEmpty() || anoString.isEmpty() ){
			Municipio municipio = controladorMunicipios.pegarMunicipio(id);
			Integer ano = Integer.parseInt(anoString);
			
			Double investimento = controladorDespesas.pegarDespesaTotalEmEducacao(municipio, ano);
			Double orcamento = controladorOrcamentos.pegarOrcamento(municipio, ano);
			
			JSONObject objeto = new JSONObject();
			objeto.put("nome", municipio.getNome());
			objeto.put("investimento", investimento);
			objeto.put("orcamento", orcamento );
			
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            out.println(objeto);
            out.flush();
			

		}
		
		
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
