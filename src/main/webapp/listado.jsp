<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.curso.modelo.Producto" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de productos</title>
</head>
<body>
<%List<Producto> productos = (List<Producto>) request.getAttribute("productos"); %>
<h2>Productos disponibles:</h2>
<%if (productos.size()<=0) { %>
	<p>No hay productos que mostrar</p>
<%} else { %>
	<%for (Producto p: productos) {%>
		<p><%=p.getId()%>. <%=p.getNombre()%> - <%=p.getDescripcion()%> - Categoría: <%=p.getCategoria()%></p>
		<p>Precio: <%=p.getPrecio()%>. Stock: <%=p.getStock()%></p><br>
	<% }%>
<%} %>
<br>
<a href="index.jsp">Volver a la página principal</a>
</body>
</html>