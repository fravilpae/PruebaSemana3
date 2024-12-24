<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.curso.modelo.Categoria" %>
<%@ page import="com.curso.modelo.Producto" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dar de alta</title>
</head>
<body>
<%String error = (String) request.getAttribute("errorMensaje");
List<Categoria> categorias = Producto.getAllCategorias();%>
<h2>Dar de alta un producto</h2>
<form action="DarAltaServlet" method="post">
Nombre del producto: <input type="text" name="nombre"><br>
Descripción del producto: <input type="text" name="descripcion"><br>
Categoría del producto: <select name="categoria">
<%for(Categoria c: categorias) {%>
	<option value="<%=c%>"><%=c%></option>
<%} %>
</select><br>
Precio del producto: <input type="number" step="any" name="precio"><br>
Stock: <input type="number" name="stock"><br>
<input type="submit" value="Dar de alta">
</form>
<%if (error != null) {%>
	<p style="color:red"><%=error%></p>
<%} %>
</body>
</html>