package com.curso.excepciones;
/**
 * Excepcion que ocurre cuando no se encuentra un producto con una ID específica
 * @author Francisco Manuel Villalobos
 * @version 1.0 23/12/2024
 */
public class IndexException extends Exception{
	private static final long serialVersionUID = 1L;
	private int codigoError;
	
	public IndexException(int codigoError) {
		super();
		this.codigoError = codigoError;
	}

	@Override
	public String getMessage() {
		String mensaje = "";
		switch(codigoError) {
		case 111:
			mensaje = "No se han encontrado productos con esa ID para borrar";
			break;
		case 222:
			mensaje = "No se han encontrado productos con esa ID para modificar";
			break;
		}
		return mensaje;
	}
	
}
