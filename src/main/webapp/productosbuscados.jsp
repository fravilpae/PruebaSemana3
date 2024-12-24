<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.curso.modelo.Producto" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Productos encontrados</title>
</head>
<%String option = (String) request.getAttribute("option"); 
String valor = (String) request.getAttribute("valor");
List<Producto> productos = (List<Producto>) request.getAttribute("productos");%>
<body>
<h2>Has buscado productos por el parámetro <%=option%></h2>
<h3>Has indicado en el parámetro el valor <%=valor%></h3>
<%if (productos.isEmpty()) {%>
	<p>No se han encontrado productos.</p>
<%} else {%>
	<h3>Productos encontrados:</h3>
	<%for (Producto p: productos) {%>
		<p><%=p.getNombre()%> - <%=p.getDescripcion()%> - Categoría: <%=p.getCategoria()%></p>
		<p>Precio: <%=p.getPrecio()%>. Stock: <%=p.getStock()%></p><br>
	<% }%>
<%} %>
<br>
<a href="index.jsp">Volver a la página principal</a>
</body>
</html>