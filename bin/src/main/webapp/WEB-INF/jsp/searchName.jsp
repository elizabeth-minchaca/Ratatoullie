<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/index.css"/>" rel="stylesheet">
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
							spellcheck="false" id="name" ng-model="name">

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
	<div ng-if="restaurants.length==0">
		<h1 class="text-center text-danger">Sin Resultados</h1>
	</div>
	<div ng-if="restaurants.length>0">

		<div id="restaurantsContainer" class="row">
			<div class="col-md-8 col-md-offset-2">
				<div class="col-md-12">
					<div class="page-header">
						<div class="row">
							<div class="col-md-12">
								<h2 class="text-center">
									<strong>Restaurantes encontrados {{restaurants.length}}</strong>
								</h2>
							</div>
						</div>
					</div>
					<div ng-repeat="r in restaurants">
						<div
							class="panel col-xs-8  col-sm-6 col-md-4 panel-default couchTipo">
							<div class="panel-body">
								<div class="thumbnail">
									<img class="img-responsive"
										ng-src="data:image/jpg;base64,{{r.image}}"
										style="height: 178px; width: 420px;" />
									<div class="caption">
										<div class="col-md-12 restaurantTitulo">
											<h4 class="text-center">
												<strong>{{ r.name }}</strong>
											</h4>
										</div>
										<div class="col-md-12 restaurantDetalle">
											<div class="col-md-3"></div>
											<div class="col-md-6">
												<p class="text-center contenedorFecha">
													Brindando servicio desde<br> <span><strong>{{
															r.openingDate | date:"dd/MM/yyyy " }}</strong></span>
												</p>
											</div>
											<c:choose>
												<c:when test="${ user != null }">
													<div class="col-md-12 text-center restDetalleVer">
														<a type="button" href="#!showRestaurant/{{r.id}}"
															class="btn btn-primary btn-lg active">VISITAR <span
															class="glyphicon glyphicon-hand-right"></span>
														</a>
													</div>
												</c:when>
												<c:otherwise>
													<div class="col-md-12 text-center restDetalleVer">
														<a type="button" href="#!showRestaurant/{{r.id}}"
															class="btn btn-primary btn-lg active">ENTRAR</a>
													</div>
												</c:otherwise>
											</c:choose>
										</div>
										<p class="contenedorFecha text-center">Ubicación:</p>
										<br>
										<p class="contenedorFecha text-center">
											<strong>{{ r.location | toCoordinates }}</strong>
										</p>
									</div>
								</div>
							</div>
							<div class="panel-footer text-center">{{r.category.name}}</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>