<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.curso.modelo.Producto" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dar de baja producto</title>
</head>
<body>
<%List<Producto> productos = (List<Producto>) request.getAttribute("productos"); %>
<h2>Seleccione el producto a dar de baja:</h2>
<form action="DarBajaServlet" method="post">
Producto: <select name="producto">
<%for(Producto p: productos){%>
	<option value="<%=p.getId()%>"><%=p.toString()%></option>
<% }%>
</select>
<input type="submit" value="Dar de baja">
</form>
</body>
</html>