<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.curso.modelo.Producto" %>
<%@ page import="com.curso.modelo.Categoria" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buscador de productos</title>
</head>
<body>
<%List<Categoria> categorias = Producto.getAllCategorias();%>
<h2>Eliga como desea buscar los productos:</h2>

<h5>Buscar por nombre</h5>
<form action="BuscarControlador?option=nombre" method="post">
Nombre: <input type="text" name="nombre">
<input type="submit" value="Buscar por nombre">
</form><br>

<h5>Buscar por descripción</h5>
<form action="BuscarControlador?option=descripcion" method="post">
Descripción: <input type="text" name="descripcion">
<input type="submit" value="Buscar por descripcion">
</form><br>

<h5>Buscar por categoría</h5>
<form action="BuscarControlador?option=categoria" method="post">
Categoría: <select name="categoria">
<%for(Categoria c: categorias) {%>
	<option value="<%=c%>"><%=c%></option>
<%} %>
</select>
<input type="submit" value="Buscar por categoría">
</form><br>

</body>
</html>