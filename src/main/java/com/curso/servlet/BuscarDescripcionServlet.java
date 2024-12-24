package com.curso.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.curso.dao.ProductoDAO;
import com.curso.modelo.Producto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BuscarDescripcionServlet
 * @author Francisco Manuel Villalobos Páez
 * @version 1.0 23/12/2024
 */
public class BuscarDescripcionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductoDAO productoDAO;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Busca los productos cuya descripción concuerde con el valor recogido en el parámetro
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		productoDAO = new ProductoDAO();

		// Obtener parámetros
		String parametro = request.getParameter("descripcion");
		request.setAttribute("valor", parametro);
		List<Producto> productos = new ArrayList<Producto>();
		if (!(parametro.equals(""))) {
			try {
				productos = productoDAO.findByDescripcion(parametro);
				request.setAttribute("productos", productos);
				RequestDispatcher dispatch = request.getRequestDispatcher("productosbuscados.jsp");
				dispatch.forward(request, response);
			} catch (NamingException | SQLException e) {
				request.setAttribute("errorMensaje", e.getMessage());
				RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
				dispatch.forward(request, response);
			}
		} else {
			request.setAttribute("productos", productos);
			RequestDispatcher dispatch = request.getRequestDispatcher("productosbuscados.jsp");
			dispatch.forward(request, response);
		}
	}

}
