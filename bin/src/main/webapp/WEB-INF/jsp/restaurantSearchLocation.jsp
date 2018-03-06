<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<script
	src="<c:url value="/resources/js/mapRestaurantSearchLocation.js"/>"></script>
<div ng-controller="restaurantSearchLocation">
	<div class="container">
		<h1 class="text-center">Búsqueda por Ubicación</h1>
		</br> </br> </br>
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
				<div ng-if="restaurants.length==0">
					<h1 class="text-center text-danger">Sin Resultados</h1>
				</div>
				<div ng-if="restaurants.length>0">
					<div class="container col-md-9 col-md-offset-2">
						<div class="panel panel-default res-pnl">
							<div class="panel-heading text-center"><h3 id="lst-ttl">Restaurantes</h3></div>
							<div class="panel-body">
								<div ng-repeat="r in restaurants">
									<div class="row rowRest">
										<div class="col-md-4">
											<img ng-src="data:image/jpg;base64,{{r.image}}" style="height: 180px; width: 270px;" />
										</div>
										<div class="col-md-6">
											<a class="res-name" type="button" href="#!showRestaurant/{{r.id}}"><h3>{{ r.name }}</h3></a>
											<h5><span class="glyphicon glyphicon-user"></span> <strong>Dueño: </strong>{{ r.owner.name }}</h5>
											<h5><span class="glyphicon glyphicon-calendar"></span> <strong>Apertura: </strong>{{ r.openingDate | date:'medium' }}</h5>
											<h5><span class="glyphicon glyphicon-map-marker"></span> <strong>Ubicación: </strong>{{ r.location | toCoordinates }}</h5>
											<h5><span class="glyphicon glyphicon-tags"></span> <strong>Categoría: </strong>{{ r.category.name }}</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>