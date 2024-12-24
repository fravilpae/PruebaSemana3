package com.curso.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BuscarControlador
 * @author Francisco Manuel Villalobos
 * @version 1.0 23/12/2024
 */
public class BuscarControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Dependiendo de la opción que se haya elegido para buscar, se buscan los productos a partir de
	 * la opción deseada
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		String urlVista = "/";
		switch (option) {
		case "nombre":
			urlVista = "BuscarNombreServlet";
			break;
		case "descripcion":
			urlVista = "BuscarDescripcionServlet";
			break;
		case "categoria":
			urlVista = "BuscarCategoriaServlet";
			break;
		}
		request.setAttribute("option", option);
		RequestDispatcher dispatch = request.getRequestDispatcher(urlVista);
		dispatch.forward(request, response);
	}

}
