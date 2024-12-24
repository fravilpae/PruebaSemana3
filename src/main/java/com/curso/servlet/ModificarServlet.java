package com.curso.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.curso.dao.ProductoDAO;
import com.curso.excepciones.IndexException;
import com.curso.modelo.Categoria;
import com.curso.modelo.Producto;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModificarServlet
 * @author Francisco Manuel Villalobos
 * @version 1.0 20/12/2024
 */
public class ModificarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductoDAO productoDAO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      Devuelve la lista de productos disponibles
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Producto> productos = new ArrayList<Producto>();
		productoDAO = new ProductoDAO();
		try {
			productos = productoDAO.findAll();
			request.setAttribute("productos", productos);
			RequestDispatcher dispatch = request.getRequestDispatcher("modifica.jsp");
			dispatch.forward(request, response);
		} catch (SQLException | NamingException e) {
			request.setAttribute("errorMensaje", e.getMessage());
			RequestDispatcher dispatch = request.getRequestDispatcher("error.jsp");
			dispatch.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      Comprueba que los valores dados son válidos. Si lo son, modifica el
	 *      producto seleccionado
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		productoDAO = new ProductoDAO();

		// Obtener parámetros del formulario y validarlos
		int id = Integer.parseInt(request.getParameter("index"));
		String nombre = request.getParameter("nombre");
		Categoria categoria = Categoria.valueOf(request.getParameter("categoria"));
		String descripcion = request.getParameter("descripcion");
		Double precio = null;
		if (!(request.getParameter("precio") == "")) {
			precio = Double.parseDouble(request.getParameter("precio"));
		}
		Integer stock = null;
		if (!(request.getParameter("stock") == "")) {
			stock = Integer.parseInt(request.getParameter("stock"));
		}
		String error = productoDAO.validarDatos(nombre, descripcion, categoria, precio, stock);
		//Sí -> SetAttribute("producto", p)
		//dispatch forward a modificacorrecto
		
		//NO -> Error al modificaincorrecto
		if(error.equals("")) {
			Producto p = new Producto(id, nombre, categoria, descripcion, precio, stock);
			try {
				productoDAO.update(p);
				request.setAttribute("producto", p);
				RequestDispatcher dispatch = request.getRequestDispatcher("modificacorrecto.jsp");
				dispatch.forward(request, response);
			} catch (NamingException | SQLException | IndexException e) {
				request.setAttribute("errorMensaje", e.getMessage());
				RequestDispatcher dispatch = request.getRequestDispatcher("modificaincorrecto.jsp");
				dispatch.forward(request, response);
			}
		} else {
			request.setAttribute("errorMensaje", error);
			RequestDispatcher dispatch = request.getRequestDispatcher("modificaincorrecto.jsp");
			dispatch.forward(request, response);
		}
	}

}
