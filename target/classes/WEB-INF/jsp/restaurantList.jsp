<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<nav class="breadcrumb">
  <a class="breadcrumb-item" href="#!/">Home /</a>
  <span class="breadcrumb-item active">Restaurantes - Listar</span>
</nav> 
<br>
<div class="container col-md-9 center-block" style="float:none;">
	<div class="panel panel-default pnl">
		<div class="panel-heading text-center"><h3 id="lst-ttl">TODOS LOS RESTAURANTES</h3></div>
		<div class="panel-body">
			<jsp:include page="/WEB-INF/jsp/templates/restaurantListCore.jsp" flush="true" />
		</div>
	</div>
</div>
