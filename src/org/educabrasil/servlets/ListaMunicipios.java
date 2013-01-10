package org.educabrasil.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.educabrasil.beans.Municipio;
import org.educabrasil.controller.ControladorDespesas;
import org.educabrasil.controller.ControladorMunicipios;
import org.educabrasil.controller.ControladorOrcamentos;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ListaMunicipios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ControladorMunicipios controladorMunicipios;
	private ControladorDespesas controladorDespesas;
	private ControladorOrcamentos controladorOrcamentos;
	
    public ListaMunicipios() {
    	controladorMunicipios = new ControladorMunicipios();
    	controladorDespesas = new ControladorDespesas();
    	controladorOrcamentos = new ControladorOrcamentos();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Municipio> municipios = controladorMunicipios.listarMunicipios();
		
		//JSONArray array = JSONArray.fromObject(controladorMunicipios.listarMunicipios());
		
		JSONArray array = new JSONArray();
		for (Municipio municipio : municipios) {

			JSONObject objeto = new JSONObject();
			objeto.put("id", municipio.getIdMunicipio());
			objeto.put("nome", municipio.getNome() );
			objeto.put("latitude", municipio.getLatitude());
			objeto.put("longitude", municipio.getLongitude());
			objeto.put("longitude", municipio.getLongitude());

			Double educacao = controladorDespesas.pegarDespesaTotalEmEducacao(municipio, 2012);
			Double orcamento = controladorOrcamentos.pegarOrcamento(municipio, 2012);

			if ( educacao != null && orcamento != null) {
				JSONObject investimentos = new JSONObject();
				investimentos.put("educacao", educacao);
				investimentos.put("outros", orcamento - educacao);
				investimentos.put("orcamento",orcamento);
				objeto.put("investimentos", investimentos);
				
				array.add(objeto);
			}
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.println(array);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
