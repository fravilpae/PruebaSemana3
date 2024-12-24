package com.curso.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.curso.excepciones.IndexException;
import com.curso.modelo.Categoria;
import com.curso.modelo.Producto;
/**
 * @author Francisco Manuel Villalobos
 * @version 1.0 23/12/2024
 */
public class ProductoDAO implements IProducto{

	/**
	 * Obtiene el datasource de la base de datos. Si ocurre algún error, te dirige una página de error.jsp
	 */
	public static DataSource obtenerDataSource() throws NamingException {
		DataSource ds = null;
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		ds = (DataSource) envContext.lookup("jdbc/DataSourceAlmacen");
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
	public String validarDatos(String nombre, String descripcion, Categoria categoria, Double precio, Integer stock) {
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

	/** 
	 * Listado de todos los productos disponibles en la base de datos.
	 */
	@Override
	public List<Producto> findAll() throws SQLException, NamingException{
		List<Producto> productos = new ArrayList<Producto>();
		DataSource ds = obtenerDataSource();
		Connection conex = ds.getConnection();
		String query = "SELECT * FROM productos";
		Statement sentencia = conex.createStatement();
		ResultSet rs = sentencia.executeQuery(query);
		while (rs.next()) {
			int codigo = rs.getInt(1);
			String nombre = rs.getString(2);
			Categoria categoria = Categoria.valueOf(rs.getString(3));
			String descripcion = rs.getString(4);
			double precio = rs.getBigDecimal(5).doubleValue();
			int stock = rs.getInt(6);
			productos.add(new Producto(codigo, nombre, categoria, descripcion, precio, stock));
		}
		return productos;
	}

	/**
	 * Añadir un producto a la base de datos
	 * @param p Producto a añadir
	 */
	@Override
	public void save(Producto p) throws NamingException, SQLException {
		DataSource ds = obtenerDataSource();
		String query = "INSERT INTO productos (nombre, categoria, descripcion, precio, stock) "
				+ "VALUES(?,?,?,?,?)";
		Connection conex = ds.getConnection();
		PreparedStatement sentencia = conex.prepareStatement(query);
		sentencia.setString(1, p.getNombre());
		sentencia.setString(2, p.getCategoria().name());
		sentencia.setString(3, p.getDescripcion());
		sentencia.setBigDecimal(4, BigDecimal.valueOf(p.getPrecio()));
		sentencia.setInt(5, p.getStock());
		sentencia.executeUpdate();
		
	}

	/**
	 * Modifica el producto indicado
	 * @param p Producto a modificar
	 */
	@Override
	public void update(Producto p) throws NamingException, SQLException, IndexException {
		DataSource ds = obtenerDataSource();
		String query = "UPDATE productos SET nombre=?, categoria=?, descripcion=?, precio=?, stock=? WHERE id=?";
		Connection conex = ds.getConnection();
		PreparedStatement sentencia = conex.prepareStatement(query);
		sentencia.setString(1, p.getNombre());
		sentencia.setString(2, p.getCategoria().name());
		sentencia.setString(3, p.getDescripcion());
		sentencia.setBigDecimal(4, BigDecimal.valueOf(p.getPrecio()));
		sentencia.setInt(5, p.getStock());
		sentencia.setInt(6, p.getId());
		if(sentencia.executeUpdate()<=0) {
			throw new IndexException(222);
		}
		
	}

	/**
	 * Elimina a un producto de la base de datos
	 * @param id Id del producto a eliminar
	 */
	@Override
	public void delete(int id) throws NamingException, SQLException, IndexException {
		DataSource ds = obtenerDataSource();
		String query = "DELETE FROM productos WHERE id=?";
		Connection conex = ds.getConnection();
		PreparedStatement sentencia = conex.prepareStatement(query);
		sentencia.setInt(1, id);
		if(sentencia.executeUpdate()<=0) {
			throw new IndexException(111);
		}
	}

	/**
	 * Lista de productos cuyo nombre contiene el valor del parámetro
	 * @param nombre Nombre del producto a buscar
	 */
	@Override
	public List<Producto> findByNombre(String nombre) throws NamingException, SQLException {
		DataSource ds = obtenerDataSource();
		List<Producto> productos = new ArrayList<Producto>();
		String query = "SELECT * FROM productos WHERE LOWER(nombre) LIKE LOWER(?)";
		Connection conex = ds.getConnection();
		PreparedStatement sentencia = conex.prepareStatement(query);
		sentencia.setString(1, "%"+nombre+"%");
		ResultSet rs = sentencia.executeQuery();
		while (rs.next()) {
			int codigo = rs.getInt(1);
			String nombre2 = rs.getString(2);
			Categoria categoria = Categoria.valueOf(rs.getString(3));
			String descripcion = rs.getString(4);
			double precio = rs.getBigDecimal(5).doubleValue();
			int stock = rs.getInt(6);
			productos.add(new Producto(codigo, nombre2, categoria, descripcion, precio, stock));
		}
		return productos;
	}

	/**
	 * Lista de productos cuya descripcion contiene el valor del parámetro
	 * @param descripcion Descripcion del producto a buscar
	 */
	@Override
	public List<Producto> findByDescripcion(String descripcion) throws NamingException, SQLException {
		DataSource ds = obtenerDataSource();
		List<Producto> productos = new ArrayList<Producto>();
		String query = "SELECT * FROM productos WHERE LOWER(descripcion) LIKE LOWER(?)";
		Connection conex = ds.getConnection();
		PreparedStatement sentencia = conex.prepareStatement(query);
		sentencia.setString(1, "%"+descripcion+"%");
		ResultSet rs = sentencia.executeQuery();
		while (rs.next()) {
			int codigo = rs.getInt(1);
			String nombre = rs.getString(2);
			Categoria categoria = Categoria.valueOf(rs.getString(3));
			String descripcion2 = rs.getString(4);
			double precio = rs.getBigDecimal(5).doubleValue();
			int stock = rs.getInt(6);
			productos.add(new Producto(codigo, nombre, categoria, descripcion2, precio, stock));
		}
		return productos;
	}

	/**
	 * Lista de productos cuya categoría coincide con el valor del parámetro
	 * @param categoria Categoria del producto a buscar
	 */
	@Override
	public List<Producto> findByCategoria(Categoria categoria) throws NamingException, SQLException {
		DataSource ds = obtenerDataSource();
		List<Producto> productos = new ArrayList<Producto>();
		String query = "SELECT * FROM productos WHERE categoria = ?";
		Connection conex = ds.getConnection();
		PreparedStatement sentencia = conex.prepareStatement(query);
		sentencia.setString(1, categoria.name());
		ResultSet rs = sentencia.executeQuery();
		while (rs.next()) {
			int codigo = rs.getInt(1);
			String nombre = rs.getString(2);
			Categoria categoria2 = Categoria.valueOf(rs.getString(3));
			String descripcion = rs.getString(4);
			double precio = rs.getBigDecimal(5).doubleValue();
			int stock = rs.getInt(6);
			productos.add(new Producto(codigo, nombre, categoria2, descripcion, precio, stock));
		}
		return productos;
	}
}
