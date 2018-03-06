<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div ng-if="restaurants.length==0">
		<h1 class="text-center text-danger">Sin Resultados</h1>
	</div>
	<div ng-if="restaurants.length>0">
		<h2 class="text-center">Restaurantes encontrados:
			{{restaurants.length}}</h2>

		<div ng-repeat="r in restaurants">
			<div class="row rowRest {{r.category.benefits[0]}} {{r.category.benefits[1]}}">
				<div class="col-md-4">
					<img class="img-responsive center-block" ng-src="data:image/jpg;base64,{{r.image}}"/>
				</div>
				<div class="col-md-8 center-block">
					<a class="res-name" type="button" href="#!showRestaurant/{{r.id}}"><h3>{{
							r.name }}</h3></a>
					<h5>
						<span class="glyphicon glyphicon-user"></span> <strong>Dueño:
						</strong>{{ r.owner.name }}
					</h5>
					<h5>
						<span class="glyphicon glyphicon-calendar"></span> <strong>Apertura:
						</strong>{{ r.openingDate | date:'medium' }}
					</h5>
					<h5>
						<span class="glyphicon glyphicon-map-marker"></span> <strong>Ubicación:
						</strong>{{r.location.name}}
					</h5>
					<h5>
						<span class="glyphicon glyphicon-tags"></span> <strong>Categoría:
						</strong>{{ r.category.name }}
					</h5>
				</div>
			</div>
		</div>
	</div>