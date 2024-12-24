package com.curso.modelo;

import java.util.Arrays;
import java.util.List;

/**
 * @author Francisco Manuel Villalobos
 * @version 1.0 20/12/2024
 */
public final class Producto {

	private int id;
	private String nombre;
	private Categoria categoria;
	private String descripcion;
	private double precio;
	private int stock;
	
	public Producto(String nombre, Categoria categoria, String descripcion, double precio, int stock) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
	}

	public Producto(int id, String nombre, Categoria categoria, String descripcion, double precio, int stock) {
		this(nombre, categoria, descripcion, precio, stock);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return  id + ". " + nombre + ". Categoria: " + categoria + ". Descripcion: "+descripcion
				+ ". Precio: " + precio + ". Stock: " + stock;
	}
	
	/**
	 * Método que devuelve las categorías disponibles para los productos (usado para el select en dar de alta)
	 * @return Categorías disponibles
	 */
	public static List<Categoria> getAllCategorias() {
		return Arrays.asList(Categoria.values());
	}
	
	
	
	
	
	
}
