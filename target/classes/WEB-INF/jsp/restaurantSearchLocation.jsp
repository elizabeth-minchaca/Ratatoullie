<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<script
	src="<c:url value="/resources/js/mapRestaurantSearchLocation.js"/>"></script>
<nav class="breadcrumb">
  <a class="breadcrumb-item" href="#!/">Home /</a>
  <span class="breadcrumb-item active">Restaurantes - B&uacute;squeda por Ubicaci&oacute;n</span>
</nav> 
<div ng-controller="restaurantSearchLocation">
	<div class="container">
		<h1 class="text-center">B&uacute;squeda por Ubicaci&oacute;n</h1>
		</br> 
		<div class="row">
			<div id="map"></div>
		</div>
		</br>
		<div class="row">
			<form name="searchLocation" ng-submit="search()" class="form">
				<div class="row">
					<div class="col-xs-6">
						<div class="form-group form-group-sm">
							<div class="input-group col-xs-8 col-xs-offset-2">
								<input class="form-control " name="distance" id="distance" type="number"
									min="100" max="10000" ng-model="dataSearch.distance"
									required="required" placeholder="Distancia" /> <span
									class="input-group-addon">Metros</span>
							</div>
						</div>
					</div>
					<div class="col-xs-6">
						<div class="col-xs-8">
							<input class="form-control" name="distance" type="range" min="100"
								max="10000" ng-model="dataSearch.distance" required="required" />

						</div>

						<button type="submit" class="btn btn-primary ">Buscar</button>
					</div>
				</div>
			</form>

			<div class="container">
				<jsp:include page="/WEB-INF/jsp/templates/restaurantListCore.jsp" flush="true" />
			</div>
		</div>
	</div>
</div>