<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link
	href="<c:url value="/resources/js/bootstrap-table/bootstrap-table.min.css"/>"
	rel="stylesheet">
<script
	src="<c:url value="/resources/js/bootstrap-table/bootstrap-table.min.js"/>"></script>
<script
	src="<c:url value="/resources/js/bootstrap-table/bootstrap-table-locale-all.min.js"/>"></script>
<div class="row" ng-controller="homeUser">
	<div class="col-md-10 col-md-offset-2">
		<div class="col-md-6">
		
				<div class="panel panel-primary" ng-if="quantityRecommendations == 0">
					<div class="panel-heading">
						<h5 class="text-center">
							BIENVENIDO <strong> ${fn:toUpperCase(user.name)}  ${fn:toUpperCase(user.lastName)}</strong>
						</h5>
					</div>
					<div class="panel-body text-center">
						<h4>Sin actividades.</h4>						
					</div>
					<div class="panel-footer text-center"></div>
					
				</div>	
		
		
				<div ng-if="quantityRecommendations > 0">
					<div ng-repeat="r in user.recommendations">
		
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h5 class="text-center">
									<strong>{{r.adviser.name}} {{r.adviser.lastName}} </strong> te ha recomendado este men&uacute;
								</h5>
							</div>
							<div class="panel-body">
								<div class="col-md-7">
									<img class="img-responsive" ng-src="data:image/jpg;base64,{{r.menu.image}}" />
								</div>
		
								<div class="col-md-5">
										<h4>
											<a href="#!showMenu/{{r.menu.restaurant.id}}/{{r.menu.id}}" class="active">								
												<strong>{{ r.menu.text }}</strong>	<br>										
												<small><strong>{{r.menu.restaurant.name}}</strong></small> <br>									
												<small>Descripci&oacute;n: {{r.menu.description}}</small>
											</a>
										</h4>
										<strong>{{ r.date | date:"dd/MM/yyyy 'a las' h:mm:ssa"}}</strong>
		
								</div>
							</div>
							<div class="panel-footer text-center">
								<h6 >
									<strong>{{ r.text }}</strong>
								</h6>
							</div>
						</div>
		
					</div>
				</div>

		</div>
		
		<div class="col-md-3">

			<div class="row">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h5 class="text-center"><strong>Usuarios por Ranking</strong></h5>
					</div>

					<table
						class="table table-striped table-bordered table-responsive text-center"
						data-toggle="table" id="tableUserRank" data-url={{userRankUrl}}
						data-locale="es-ES">
						<thead>
							<tr>
								<th class="text-center" data-field="ranking">Ranking</th>
								<th class="text-center" data-field="quantity">Usuarios</th>
							</tr>
						</thead>
					</table>
				</div>

			</div>
			<br>

			<div class="row">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h5 class="text-center"><strong>Top 10 Usuarios</strong></h5>
					</div>
					<table
						class="table table-striped table-bordered table-responsive text-center"
						data-toggle="table" id="tableUserRank" data-url={{topUsersUrl}}
						data-locale="es-ES">
						<thead>
							<tr>
								<th class="text-center" data-field="name">Nombre</th>
								<th class="text-center" data-field="commentSize">Comentarios</th>
							</tr>
						</thead>
					</table>

				</div>
			</div>

		</div>
	</div>
</div>
