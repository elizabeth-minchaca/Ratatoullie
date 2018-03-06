<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div  id="viewRestaurant" ng-controller="MenuController">
<nav class="breadcrumb">
  <a class="breadcrumb-item" href="#!/">Home /</a>
    <a class="breadcrumb-item" href="#!myRestaurants">Mi Restaurant - Mis Restaurantes /</a>
    <a class="breadcrumb-item" href="#!viewRestaurant/{{menu.idRest}}">Restaurant - Ver /</a>
  <span class="breadcrumb-item active">Men&uacute; - Ver</span>
</nav> 
	<div  class=" containerrow">
		<div id="restaurantContainer" class="col-md-11">
			<div class="panel-heading panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title"></h3>
				</div>
				<div class="panel-body">
					<div class="page-header">
						<div class="row">
							<div class="col-md-6">
								<h1>
									<span class="glyphicon glyphicon-tags"></span> Men&uacute; <small>-
										Ver</small>
								</h1>
							</div>
							<c:if test="${user.enable}">							
								<div class="col-md-6 text-right active">
									<h1>
										<small> <a href="#!editMenu/{{menu.idRest}}/{{menu.idMenu}}">
												<span class="glyphicon glyphicon-edit"> </span> Editar
										</a>
										</small>
									</h1>
								</div>
							</c:if>
						</div>
					</div>
					<form class="form-horizontal">
						<div class="row">
							<div class="col-md-7">
								<div class="form-group form-group-lg">
									<label for="restRestaurant" class="col-md-3 control-label">Restaurant:</label>
									<div class="col-md-9">
										<p class="form-control-static">{{menu.nameRest}}</p>
									</div>
								</div>
							
								<div class="form-group form-group-lg">
									<label for="restRestaurant" class="col-md-3 control-label">Nombre:</label>
									<div class="col-md-9">
										<p class="form-control-static">{{menu.name}}</p>
									</div>
								</div>
								<div class="form-group form-group-lg">
									<label for="restRestaurant" class="col-md-3 control-label">Descripci&oacute;n:</label>
									<div class="col-md-9">
                                        <textarea readonly="readonly" ng-model="menu.description" name="descripcion" class="form-control form-control-static" rows="5" required="required" placeholder="Ingrese descripción...">{{menu.description}}</textarea>																																								
									</div>
								</div>	
								<div class="form-group form-group-lg">
									<label for="restRestaurant" class="col-md-3 control-label">Fecha Inicio:</label>
									<div class="col-md-9">
										<p class="form-control-static">{{menu.startDate | date:"dd/MM/yyyy 'a las' h:mm:ss a"}}</p>
										
									
									</div>
								</div>
								<div class="form-group form-group-lg">
									<label for="restRestaurant" class="col-md-3 control-label">Fecha Fin:</label>
									<div class="col-md-9">
										<p class="form-control-static"><span ng-if="menu.endDate == 0">MEN&Uacute; ACTIVO</span><span ng-if="menu.endDate != 0">{{menu.endDate | date:"dd/MM/yyyy 'a las' h:mm:ss a"}}</span></p>
									</div>
								</div>
															
							</div>
							<div class="col-md-5">
								<div id="imageEditPreview" class="col-md-12" data-toggle="tooltip" data-placement="left" title="Foto del {{menu.name}}">
                                        <img class="img-responsive" ng-src="data:image/jpg;base64,{{menu.image}}"/>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<link href="<c:url value="/resources/css/registerRestaurant.css"/>"	rel="stylesheet">
