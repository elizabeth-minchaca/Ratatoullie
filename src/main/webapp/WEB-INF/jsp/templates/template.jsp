<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>${title}-Ratatoullie</title>
<link href="https://fonts.googleapis.com/css?family=Indie+Flower" rel="stylesheet">
<link rel="shortcut icon" href="resources/imgs/favicon-16.png" type="image/png">
<link rel="icon" href="resources/imgs/favicon-16.png" type="image/png">
<!-- Bootstrap Core CSS -->
<link href="<c:url value="/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrapValidator.min.css"/>"	rel="stylesheet">
<link	href="<c:url value="/resources/css/font-awesome-4.7.0/css/font-awesome.min.css"/>"	rel="stylesheet">
<!-- jQuery -->
<script	src="<c:url value="/resources/js/jquery-3.2.1/jquery-3.2.1.min.js"/>"></script>
<!-- Bootstrap Core JavaScript -->
<script	src="<c:url value="/resources/css/bootstrap-3.3.7-dist/js/bootstrap.min.js"/>"></script>
<!-- Angular -->
<script	src="<c:url value="/resources/js/angular-1.6.5/angular.min.js"/>"></script>
<script	src="<c:url value="/resources/js/angular-1.6.5/angular-route.js"/>"></script>
<link href="<c:url value="/resources/js/angular-1.6.5/angular-route.min.js.map"/>" rel="mime">
<link href="<c:url value="/resources/js/angular-1.6.5/angular.min.js.map"/>" rel="mime">
<script	src="<c:url value="/resources/js/angular-1.6.5/angular-cookies.js"/>"></script>
<!-- Scripts of ratatoullie -->
<script src="<c:url value="/resources/js/ratatoullie.js"/>"></script>
<!-- bootstrap-table -->
<link href="<c:url value="/resources/js/bootstrap-table/bootstrap-table.min.css"/>"	rel="stylesheet">
<script	src="<c:url value="/resources/js/bootstrap-table/bootstrap-table.min.js"/>"></script>
<script	src="<c:url value="/resources/js/bootstrap-table/bootstrap-table-locale-all.min.js"/>"></script>
<script src="<c:url value="/resources/js/tables.js"/>"></script>
<!-- 	SweetAlert -->
<script	src="<c:url value="/resources/css/sweetalert2/sweetalert2.min.js"/>"></script>
<link	href="<c:url value="/resources/css/sweetalert2/sweetalert2.min.css"/>"	rel="stylesheet" type="text/css" />
<!-- CryptoJS-MD5 -->
<script src="<c:url value="/resources/js/core.js"/>"></script>
<script src="<c:url value="/resources/js/md5.js"/>"></script>
<!-- Google Maps -->
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsM8jNH7OGoHn754CY5_hxXqsa8mbg1e4"></script>
</head>
<body ng-app="ratatoullie">
	<jsp:include page="/WEB-INF/jsp/templates/menu.jsp" flush="true" />
	<div ng-view></div>
	<c:if test="${empty user}">
		<jsp:include page="/WEB-INF/jsp/login.jsp" flush="true" />
	</c:if>
	<br><br><br>
	<jsp:include page="/WEB-INF/jsp/templates/footer.jsp" flush="true" />
</body>
</html>
