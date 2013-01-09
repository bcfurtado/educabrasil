package org.educabrasil.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.educabrasil.beans.Municipio;
import org.educabrasil.controller.ControladorMunicipios;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ListaMunicipios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ControladorMunicipios controladorMunicipios;
	
    public ListaMunicipios() {
    	controladorMunicipios = new ControladorMunicipios();
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
			array.add(objeto);
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.println(array);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
