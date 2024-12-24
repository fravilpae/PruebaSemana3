package com.curso.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.curso.dao.ProductoDAO;
import com.curso.modelo.Categoria;
import com.curso.modelo.Producto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DarAltaServlet
 * @author Francisco Manuel Villalobos
 * @version 1.1 23/12/2024
 */
public class DarAltaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductoDAO productoDAO;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Comprueba que los valores dados son válidos. Si lo son, crea el producto y lo añade a la base de datos.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productoDAO = new ProductoDAO();
		//Obtener parámetros del formulario y validarlos
		String nombre = request.getParameter("nombre");
		Categoria categoria = Categoria.valueOf(request.getParameter("categoria"));
		String descripcion = request.getParameter("descripcion");
		Double precio = null;
		if(!(request.getParameter("precio")=="")) {
			precio = Double.parseDouble(request.getParameter("precio"));
		}
		Integer stock = null;
		if(!(request.getParameter("stock")=="")) {
			stock = Integer.parseInt(request.getParameter("stock"));
		}
		String error = productoDAO.validarDatos(nombre, descripcion, categoria, precio, stock);
		
		//Ejecutar sentencia SQL
		if(error.equals("")) {
			Producto p = new Producto(nombre, categoria, descripcion, precio, stock);
			try {
				productoDAO.save(p);
				request.setAttribute("producto", p);
				RequestDispatcher dispatch = request.getRequestDispatcher("altacorrecto.jsp");
				dispatch.forward(request, response);
			} catch (NamingException | SQLException e) {
				request.setAttribute("errorMensaje", e.getMessage());
				RequestDispatcher dispatch = request.getRequestDispatcher("alta.jsp");
				dispatch.include(request, response);
			}
		} else {
			request.setAttribute("errorMensaje", error);
			RequestDispatcher dispatch = request.getRequestDispatcher("alta.jsp");
			dispatch.include(request, response);
		}
	}
}
