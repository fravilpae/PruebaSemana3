package com.curso.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class BuscarCategoriaServlet
 * @author Francisco Manuel Villalobos
 * @version 1.0 23/12/2024
 */
public class BuscarCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductoDAO productoDAO;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Busca los productos cuya categoría concuerde con el valor recogido en el parámetro
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productoDAO = new ProductoDAO();

		// Obtener parámetros
		Categoria parametro = Categoria.valueOf(request.getParameter("categoria"));
		request.setAttribute("valor", parametro.name());
		List<Producto> productos = new ArrayList<Producto>();
			try {
				productos = productoDAO.findByCategoria(parametro);
				request.setAttribute("productos", productos);
				RequestDispatcher dispatch = request.getRequestDispatcher("productosbuscados.jsp");
				dispatch.forward(request, response);
			} catch (NamingException | SQLException e) {
				request.setAttribute("errorMensaje", e.getMessage());
				RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
				dispatch.forward(request, response);
			}
	}

}
