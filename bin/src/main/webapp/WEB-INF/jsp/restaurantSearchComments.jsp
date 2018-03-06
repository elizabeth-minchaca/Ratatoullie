<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<div ng-controller="restaurantSearchComments">
	<div class="container">
		<h1 class="text-center">Retaurantes mas comentados</h1>
		</br> </br> </br>
		<div class="row">
			<form name="searchLocation" ng-submit="search()"
				class="form-horizontal">
				<div class="row">
					<div class="col-sm-5">
						<div class="form-group">
							<label class="control-label col-md-4" for="initial">Fecha
								Inicial:</label>
							<div class="col-sm-8">
								<input class="form-control " type="datetime-local"
									ng-model="initialDate" name="initial" id="initial"
									required="required" />
							</div>
						</div>
					</div>
					<div class="col-sm-5">
						<div class="form-group">
							<label class="control-label col-md-4" for="final">Fecha
								Final:</label>
							<div class="col-sm-8">
								<input class="form-control" type="datetime-local"
									ng-model="finalDate" name="final" id="final"
									required="required" />
							</div>
						</div>
					</div>
					<div class="col-xs-2">
						<button type="submit" class="btn btn-primary ">Buscar</button>
					</div>
				</div>
			</form>
			<div class="container">
				<div ng-if="restaurants.length==0">
					<h1 class="text-center text-danger">Sin Resultados</h1>
				</div>
				<div ng-if="restaurants.length>0">
					<h2 class="text-center">{{restaurants.length}} Restaurantes mas comentados</h2>
					<div ng-repeat="r in restaurants">
						<div class="row">
							<div id="#rest-panel" class="panel panel-default">
								<div class="col-md-3">
									<img ng-src="data:image/jpg;base64,{{r.image}}"
										style="max-height: 100%; max-width: 100%;" />
								</div>
								<div class="col-md-6">
									<h3>{{ r.name }}</h3>
									<p>
										<strong>Ubicación: </strong>{{ r.location| toCoordinates}}
									</p>
									<p>
										<strong>Comentarios: </strong>{{ r.commenteSize }}
									</p>
								</div>
								<div class="col-md-3">
									<a href="#!showRestaurant/{{r.id}}" class="btn btn-success">
										<span class="glyphicon glyphicon-eye-open"></span> Ver
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>