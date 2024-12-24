<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.curso.modelo.Producto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Producto modificado</title>
</head>
<body>
<h2>Producto modificado correctamente</h2>
<%Producto p = (Producto) request.getAttribute("producto"); %>
<p><%=p.getNombre()%> - <%=p.getDescripcion()%> - Categoría: <%=p.getCategoria()%></p>
<p>Precio: <%=p.getPrecio()%>. Stock: <%=p.getStock()%></p><br>
<a href="index.jsp">Volver a la página principal</a>
</body>
</html>