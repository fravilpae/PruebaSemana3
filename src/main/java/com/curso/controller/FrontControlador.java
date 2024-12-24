package com.curso.controller;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.curso.modelo.Categoria;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontControlador
 * @author Francisco Manuel Villalobos
 * @version 1.0 20/12/2024
 */
public class FrontControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String option = request.getParameter("option");
		String urlVista = "/";
		switch (option) {
		case "alta":
			urlVista = "alta.jsp";
			break;
		case "baja":
			urlVista = "DarBajaServlet";
			break;
		case "modificar":
			urlVista = "ModificarServlet";
			break;
		case "buscar":
			urlVista = "buscador.jsp";
			break;
		case "listar":
			urlVista = "ListarServlet";
			break;
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(urlVista);
		dispatch.forward(request, response);
	}
	
	/**
	 * Obtiene el datasource de la base de datos. Si ocurre algún error, te dirige una página de error.jsp
	 */
	public static DataSource obtenerDataSource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/DataSourceAlmacen");
		} catch (NamingException e) {
			request.setAttribute("errorMensaje", e.getMessage());
			RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
			dispatch.forward(request, response);
		}
		return ds;
	}
	
	/**
	 * Método para comprobar que todos los inputs se han introducido correctamente. Útil en dar de alta y modificar.
	 * 
	 * @param nombre Nombre del producto
	 * @param descripcion Descripcion del producto
	 * @param categoria Categoria del producto
	 * @param precio Precio del producto
	 * @param stock Stock del producto
	 * @return Mensaje de error a mostrar
	 */
	public static String validarDatos(String nombre, String descripcion, Categoria categoria, Double precio, Integer stock) {
		StringBuffer error = new StringBuffer();
		if(nombre == null || nombre == "") {
			error.append("Introduzca un nombre válido<br>");
		}
		if (descripcion == null || descripcion == "") {
			error.append("Introduzca una descripción válida<br>");
		}
		if (categoria == null) {
			error.append("La categoría se ha introducido incorrectamente.<br>");
		}
		if (precio==null || precio <=0) {
			error.append("Introduzca un precio válido<br>");
		}
		if (stock == null || stock <=0) {
			error.append("Introduzca un stock válido<br>");
		}
		return error.toString();
	}

}
