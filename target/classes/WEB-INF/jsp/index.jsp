<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/index.css"/>" rel="stylesheet">

<jsp:include page="/WEB-INF/jsp/templates/searchPanel.jsp" flush="true" />
<br>
<div id="restaurantsContainer" class="row"
	ng-controller="restaurantList">
	<div class="col-md-8 col-md-offset-2">
		<div class="col-md-12">
			<div class="page-header">
				<div class="row">
					<div class="col-md-12">
						<h2 class="text-center">							
							<strong class="text-danger" ng-if="restaurants.length==0">Sin Resultados</strong>							
							<strong ng-if="restaurants.length!=0">Restaurantes {{restaurants.length}}</strong>
						</h2>
					</div>
				</div>
			</div>
			<div ng-repeat="r in restaurants">
				<div
					class="panel col-xs-8  col-sm-6 col-md-4 panel-default couchTipo {{r.category.benefits[0]}}">
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
								<p class="contenedorFecha text-center">Ubicaci&oacute;n:</p>
								<br>
								<p class="contenedorFecha text-center">
									<strong>{{r.location.name}}</strong>
									
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