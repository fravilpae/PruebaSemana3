package com.curso.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.curso.dao.ProductoDAO;
import com.curso.excepciones.IndexException;
import com.curso.modelo.Producto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DarBajaServlet
 * @author Francisco Manuel Villalobos
 * @version 1.0 20/12/2024
 */
public class DarBajaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductoDAO productoDAO;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Devuelve la lista de productos disponibles
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Producto> productos = new ArrayList<Producto>();
		productoDAO = new ProductoDAO();
		try {
			productos = productoDAO.findAll();
			request.setAttribute("productos", productos);
			RequestDispatcher dispatch = request.getRequestDispatcher("baja.jsp");
			dispatch.forward(request, response);
		} catch (SQLException | NamingException e) {
			request.setAttribute("errorMensaje", e.getMessage());
			RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
			dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * Elimina al producto seleccionado de la lista
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productoDAO = new ProductoDAO();
		int id = Integer.parseInt(request.getParameter("producto"));
		
		try {
			productoDAO.delete(id);
			RequestDispatcher dispatch = request.getRequestDispatcher("bajacorrecto.jsp");
			dispatch.forward(request, response);
		} catch (NamingException | SQLException | IndexException e) {
			request.setAttribute("errorMensaje", e.getMessage());
			RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
			dispatch.forward(request, response);
		}
		
	}

}
