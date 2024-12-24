<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.curso.modelo.Producto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Producto no modificado</title>
</head>
<body>
<%String error = (String) request.getAttribute("errorMensaje");%>
<h2>El producto no ha podido modificarse correctamente.</h2>
<p style="color:red"><%=error%></p>
<a href="index.jsp">Volver a la página principal</a>
</body>
</html>