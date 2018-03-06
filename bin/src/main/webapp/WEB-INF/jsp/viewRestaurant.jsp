<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container" id="viewRestaurant" ng-controller="RestaurantController">
	<div class="row">
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
									<span class="glyphicon glyphicon-tags"></span> Restaurant <small>-
										Ver</small>
								</h1>
							</div>
 							<c:if test="${user.enable}">																			
 								<div id="usuarioEditButton" class="col-md-6 text-right active">
									<h1>
										<small> <a href="#!editRestaurant/{{rest.id}}">
												<span class="glyphicon glyphicon-edit"> </span> Editar
										</a>
										</small>
									</h1>
								</div>
 							</c:if> 						
 						</div>
					</div>
					<form class="form-horizontal">
						<div class="form-group form-group-lg">
							<label for="newCouchTipo" class="col-sm-3 control-label ">Nombre:</label>
							<div class="col-md-9">
								<p class="form-control-static">{{rest.name}}</p>
							</div>
						</div>
						<div class="form-group form-group-lg">
							<label for="usuarioFormApellido" class="col-sm-3 control-label">Fecha
								de creaci&oacute;n:</label>
							<div class="col-md-9">
								<p class="form-control-static">{{rest.date | date:"dd/MM/yyyy"}}</p>
							</div>
						</div>
						<div class="form-group form-group-lg">
							<label for="usuarioFormNombre" class="col-sm-3 control-label">Categor&iacute;a:</label>
							<div class="col-md-9">
								<p class="form-control-static">{{rest.category}}</p>
							</div>
						</div>
						<div class="form-group form-group-lg">
							<label for="usuarioFormNombre" class="col-sm-3 control-label">Ucicaci&oacute;n:</label>
							<div class="col-md-9">
								<p class="form-control-static">{{rest.location|toCoordinates}}</p>
							</div>
						</div>
					</form>
					<div class="row">
						<div id="restauranContainer" class="col-md-12">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h3 class="panel-title"></h3>
								</div>
								<div class="panel-body">
									<div class="page-header">
										<h2><span class="glyphicon glyphicon-th-list"></span>Men&uacute;s <small>- listado</small></h2>
										<c:if test="${user.enable}">							
				                            <div class="text-right active">
				                                <a class="btn btn-primary btn-md" href="#!registerMenu/{{rest.id}}" role="button"><span class="glyphicon glyphicon-plus"></span> NUEVO MEN&Uacute;</a>			                            
				                            </div>
			                            </c:if>
									</div>									
									<table class="table table-striped table-bordered table-responsive">
										<thead>
											<tr>
												<th class="text-center">#</th>
												<th class="text-center">Descripci&oacute;n</th>
												<th class="text-center">Fecha de Inicio</th>
												<th class="text-center">Fecha de fin</th>
												<th class="text-center">Acci&oacute;n</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="m in rest.menus" ng-init="index=$index + 1;">
												<td class="text-center"><strong>{{index}}</strong></td>
												<td class="text-center">{{m.text}}</td>
												<td class="text-center">{{m.startDate | date:"dd/MM/yyyy 'a las' h:mm:ss a"}}</td>
												<td class="text-center">
													<span ng-if="m.endDate > 0 ">{{m.endDate | date:"dd/MM/yyyy 'a las' h:mm:ss a"}}</span> 
													<span ng-if="m.endDate == 0 ">MEN&Uacute; ACTIVO</span>												
												</td>
												<td class="text-center">
													<a title="VER" href="#!viewMenu/{{rest.id}}/{{m.id}}"> <span class="glyphicon glyphicon-eye-open active"></span> </a> 													
													<c:if test="${user.enable}">							
														<a title="EDITAR" href="#!editMenu/{{rest.id}}/{{m.id}}"> <span	class="glyphicon glyphicon-edit active"></span>	</a>
													</c:if>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="panel-footer"></div>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>
	</div>
</div>