<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.curso.modelo.Producto" %>
<%@ page import="com.curso.modelo.Categoria" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar producto</title>
</head>
<body>
<%List<Producto> productos = (List<Producto>) request.getAttribute("productos"); %>
<%List<Categoria> categorias = Producto.getAllCategorias();%>
<form action="ModificarServlet" method="post">
<h2>Seleccione el producto a modificar:</h2>
Producto: <select name="index">
<%for(Producto p: productos){%>
	<option value="<%=p.getId()%>"><%=p.toString()%></option>
<% }%>
</select>
<h2>Indique como quiere modificar los datos:</h2>
Nombre del producto: <input type="text" name="nombre"><br>
Descripción del producto: <input type="text" name="descripcion"><br>
Categoría del producto: <select name="categoria">
<%for(Categoria c: categorias) {%>
	<option value="<%=c%>"><%=c%></option>
<%} %>
</select><br>
Precio del producto: <input type="number" step="any" name="precio"><br>
Stock: <input type="number" name="stock"><br>
<input type="submit" value="Modificar producto">
</form>
</body>
</html>