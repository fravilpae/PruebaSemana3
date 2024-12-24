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
 * Servlet implementation class ListarServlet
 * @author Francisco Manuel Villalobos
 * @version 1.1 23/12/2024
 */
public class ListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductoDAO productoDAO;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Listado de todos los productos disponibles. Si ocurre algún error sobre elementos externos, te dirige a una
	 * página de error jsp
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Producto> productos = new ArrayList<Producto>();
		productoDAO = new ProductoDAO();
		try {
			productos = productoDAO.findAll();
			request.setAttribute("productos", productos);
			RequestDispatcher dispatch = request.getRequestDispatcher("listado.jsp");
			dispatch.forward(request, response);
		} catch (SQLException | NamingException e) {
			request.setAttribute("errorMensaje", e.getMessage());
			RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
			dispatch.forward(request, response);
		}
	}

}
