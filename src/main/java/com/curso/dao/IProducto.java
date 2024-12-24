package com.curso.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.curso.excepciones.IndexException;
import com.curso.modelo.Categoria;
import com.curso.modelo.Producto;
/**
 * @author Francisco Manuel Villalobos
 * @version 1.0 23/12/2024
 */
public interface IProducto {

	List<Producto> findAll() throws NamingException, SQLException;
	void save(Producto p) throws NamingException, SQLException;
	void update(Producto p) throws NamingException, SQLException, IndexException;
	void delete(int id) throws NamingException, SQLException, IndexException;
	List<Producto> findByNombre(String nombre) throws NamingException, SQLException;
	List<Producto> findByDescripcion(String descripcion) throws NamingException, SQLException;
	List<Producto> findByCategoria(Categoria categoria) throws NamingException, SQLException;
}
