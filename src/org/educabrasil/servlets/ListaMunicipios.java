package org.educabrasil.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.educabrasil.beans.Municipio;
import org.educabrasil.beans.Orcamento;
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
	private JSONArray array = null;
	
    public ListaMunicipios() {
    	controladorMunicipios = new ControladorMunicipios();
    	controladorDespesas = new ControladorDespesas();
    	controladorOrcamentos = new ControladorOrcamentos();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Municipio> municipios = controladorMunicipios.listarMunicipios();
		
		//JSONArray array = JSONArray.fromObject(controladorMunicipios.listarMunicipios());
		
		
		if ( array == null ) {
			array = new JSONArray();
			for (Municipio municipio : municipios) {

				JSONObject objeto = new JSONObject();
				objeto.put("id", municipio.getId());
				objeto.put("nome", municipio.getNome() );
				objeto.put("latitude", municipio.getLatitude());
				objeto.put("longitude", municipio.getLongitude());
				objeto.put("longitude", municipio.getLongitude());
				JSONArray investimentos = new JSONArray();
							
				List<Orcamento> orcamentos = controladorOrcamentos.pegarTodosOsOrcamentosDoMunicipio(municipio);
				
				for (Orcamento orcamento : orcamentos) {
					Double educacao = controladorDespesas.pegarDespesaTotalEmEducacao(municipio, orcamento.getAno());
					Double orcamentoValor = controladorOrcamentos.pegarOrcamento(municipio, orcamento.getAno());

					if ( educacao != null && orcamentoValor != null) {
						JSONObject investimento = new JSONObject();
						investimento.put("ano", orcamento.getAno().toString());
						investimento.put("educacao", educacao);
						investimento.put("outros", orcamentoValor - educacao);
						investimento.put("orcamento",orcamentoValor);
						investimentos.add(investimento);
					}
					
				}
				
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
