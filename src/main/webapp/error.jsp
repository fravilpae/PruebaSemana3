<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERROR</title>
</head>
<body>
<%String error = (String) request.getAttribute("errorMensaje");%>
<h1>Ha ocurrido un error durante la ejecución de la aplicación</h1>
<p style="color:red"><%=error%></p>
<a href="index.jsp">Volver a la página principal</a>
</body>
</html>