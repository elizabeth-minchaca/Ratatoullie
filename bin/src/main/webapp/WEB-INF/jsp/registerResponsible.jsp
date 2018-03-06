<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container" ng-controller="registerUserResponsible"
	id="registerUserResponsible">
	<div class="row">
		<div id="newResponsibleContainer" class="col-md-11">
			<div class="panel-heading panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title"></h3>
				</div>
				<div class="panel-body">
					<div class="page-header">
						<div class="row">
							<div class="col-md-6">
								<h2>
									<span class="glyphicon glyphicon-user"></span> Responsable <small>-
										Nuevo</small>
								</h2>
							</div>
						</div>
					</div>
					<form id="newResponsibleForm" ng-submit="submitResponsible()" class="form-horizontal" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group ">
									<label class="col-sm-3 control-label">Nombre:</label>
									<div class="col-md-9">
										<input type="text" ng-model="user.name"
											class="form-control" required placeholder="Ingrese nombre...">
									</div>
								</div>
								<div class="form-group ">
									<label class="col-sm-3 control-label">Apellido:</label>
									<div class="col-md-9">
										<input type="text" ng-model="user.lastName"
											class="form-control" required placeholder="Ingrese apellido...">
									</div>
								</div>
																					
								<div class="form-group ">
									<label class="col-sm-3 control-label">E-mail:</label>
									<div class="col-md-9">
										<input type="email" ng-model="user.email"
											class="form-control" required placeholder="Ingrese e-mail..." />
									</div>
								</div>
								<div class="form-group ">
									<label class="col-sm-3 control-label">Clave:</label>
									<div class="col-md-9">
										<input type="password" ng-model="user.password"
										 class="form-control" required placeholder="Ingrese clave...">
									</div>
								</div>
								<div class="form-group ">
									<label class="col-sm-3 control-label">Repetir Clave:</label>
									<div class="col-md-9">
										<input type="password" ng-model="user.password2" 
										class="form-control" required placeholder="Ingrese clave...">
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div id="mapResponsible"></div>
							</div>


						</div>

						<div class="page-header">
							<div class="row">
								<div class="col-md-6">
									<h2>
										<span class="glyphicon glyphicon-tags"></span> Restaurant <small>-
											Nuevo</small>
									</h2>
								</div>
							</div>
						</div>

															
						<div class="row">
							<div id="restaurant" class="col-md-6">
								<div class="form-group">
									<label for="restRestaurant" class="col-sm-3 control-label">Nombre:</label>
									<div class="col-md-9">
										<input type="text" ng-model="user.restName"
											class="form-control" required
											placeholder="Ingrese nombre...">
									</div>
								</div>
								<div class="form-group">
									<label for="dateRestaurant" class="col-sm-3 control-label">Fecha
										de Apertura:</label>
									<div class="col-md-9">
										<input type="date" ng-model="user.restDate"
											class="form-control" required
											placeholder="Ingrese fecha dd/mm/aaaa...">
									</div>
								</div><br>
								<div id="imagePreview" class="col-md-12" data-toggle="tooltip"
									data-placement="left" title="Agregar Foto...">
									<h4 class="text-center"><strong>FOTO DEL RESTAURANT</strong></h4>
								</div>
								<input id="uploadFile" type="file" accept="image/*" name="uploadFile" ng-model="user.uploadFile" required class="img hidden"/>
							</div>
							
							<div class="col-md-5">
								<div id="mapRestaurant"></div>																	
							</div>
						</div>
						<div class="page-header">
							<div class="row">
								<div class="col-md-6">
									<h2>
										<span class="glyphicon glyphicon-tags"></span> Men&uacute; <small>-
											Nuevo</small>
									</h2>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="nameMenu" class="col-sm-3 control-label">Nombre:</label>
									<div class="col-md-9">
										<input type="text" ng-model="user.menuName" name="nameMenu"
											class="form-control" required placeholder="Ingrese nombre...">
									</div>
								</div>
								<div class="form-group">
									<label for="nameMenu" class="col-sm-3 control-label">Descipci&oacute;n:</label>
									<div class="col-md-9">
                                        <textarea ng-model="user.menuDescription" name="descripcion" class="form-control" rows="5" required="required" placeholder="Ingrese descripción..."></textarea>																					
									</div>
								</div>																								
							</div>
							<div class="col-md-5">
								<div id="imagePreviewMenu" class="col-md-12" data-toggle="tooltip"
									data-placement="left" title="Agregar Foto...">
									<h4 class="text-center"><strong>FOTO DEL MENU</strong></h4>
								</div>
								<input id="uploadFileMenu" type="file" accept="image/*" name="uploadFileMenu" ng-model="user.uploadFileMenu" required class="img hidden"/>																
							</div>													
							
						</div><br>
						<div class="row">
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" data-loading-text="Enviando..."
										class="btn btn-primary active">
										<span class="glyphicon glyphicon-floppy-save"></span> REGISTRARME
									</button>
								</div>
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<link href="<c:url value="/resources/css/registerResponsible.css"/>"	rel="stylesheet">
<script src="<c:url value="/resources/js/chargeFile.js"/>"></script>
<script src="<c:url value="/resources/js/userResponsible.js"/>"></script>
<script src="<c:url value="/resources/js/mapUserResponsible.js"/>"></script>
