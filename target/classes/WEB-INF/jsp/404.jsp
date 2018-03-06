<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="<c:url value="/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>"
	rel="stylesheet">
<script
	src="<c:url value="/resources/js/jquery-3.2.1/jquery-3.2.1.min.js"/>"></script>
<script type="text/javascript">
setTimeout(function() {
	$(location).attr('href', "http://"+window.location.host+"/Ratatoullie/");	
	}, 4000);
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recurso no encontrado</title>
</head>
<body>
	<h1 class="text-center">Recurso no encontrado</h1>
	<h6 class="text-center">Redireccionando...</h6>
</body>
</html>