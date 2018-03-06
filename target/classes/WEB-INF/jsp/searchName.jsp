<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/index.css"/>" rel="stylesheet">
<nav class="breadcrumb">
  <a class="breadcrumb-item" href="#!/">Home /</a>
  <span class="breadcrumb-item active">Restaurantes - B&uacute;squeda por Nombre</span>
</nav> 
<div>
	<div class="homepage">
		<div class="container ctr">
			<div class="col-md-12">
				<div class="h1">Encontrá el restaurante perfecto</div>
				<div class="col-xs-12">
					<form ng-submit="search()">
						<div class="col-xs-8 hld-picker">
							<input type="text" class="form-control picker" tabindex="1"
								placeholder="Nombre del restaurante" autocorrect="off"
								spellcheck="false" id="name" ng-model="name" required="required">

						</div>
						<div class="col-xs-4 hld-btn">
							<button type="submit" value="sumit"
								class="btn btn-success search-btn" id="searchName">
								<span class="submit_text">Encontrá restaurantes</span>
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<br>
	<div class="container col-md-9 center-block" style="float:none;">
		<div class="panel panel-default pnl">
			<div class="panel-body">
				<jsp:include page="/WEB-INF/jsp/templates/restaurantListCore.jsp" flush="true" />
			</div>
		</div>
	</div>
</div>