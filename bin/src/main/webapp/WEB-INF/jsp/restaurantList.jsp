<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/js/restaurantList.js"/>"></script>
<br>
<div class="container col-md-9 col-md-offset-2">
	<div class="panel panel-default res-pnl">
		<div class="panel-heading text-center"><h3 id="lst-ttl">TODOS LOS RESTAURANTES</h3></div>
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
