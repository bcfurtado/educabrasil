package org.educabrasil.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.educabrasil.controller.ControladorMunicipios;

import net.sf.json.JSONArray;


public class ListaMunicipios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ControladorMunicipios controladorMunicipios;
	
    public ListaMunicipios() {
    	controladorMunicipios = new ControladorMunicipios();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray array = JSONArray.fromObject(controladorMunicipios.listarMunicipios());
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		out.println(array);
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
