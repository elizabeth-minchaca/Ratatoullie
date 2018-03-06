<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container" ng-controller="registerUser"
	id="registerUser">
	<div class="row">
		<div id="newuSERContainer" class="col-md-11">
			<div class="panel-heading panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title"></h3>
				</div>
				<div class="panel-body">
					<div class="page-header">
						<div class="row">
							<div class="col-md-6">
								<h2>
									<span class="glyphicon glyphicon-user"></span> Usuario <small>-
										Nuevo</small>
								</h2>
							</div>
						</div>
					</div>
					<form id="newResponsibleForm" ng-submit="submitUser()"
						class="form-horizontal">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group ">
									<label class="col-sm-3 control-label">Nombre:</label>
									<div class="col-md-9">
										<input type="text" ng-model="user.name" class="form-control"
											required placeholder="Ingrese nombre...">
									</div>
								</div>
								<div class="form-group ">
									<label class="col-sm-3 control-label">Apellido:</label>
									<div class="col-md-9">
										<input type="text" ng-model="user.lastName"
											class="form-control" required
											placeholder="Ingrese apellido...">
									</div>
								</div>
								<div class="form-group ">
									<label class="col-sm-3 control-label">E-mail:</label>
									<div class="col-md-9">
										<input type="email" pattern="\S+@\S+\.[a-zA-Z]{3,}" ng-model="user.email" class="form-control"
											oninvalid="setCustomValidity('Ingrese un mail correcto')" oninput="setCustomValidity('')"
											required placeholder="Ingrese e-mail..." />
									</div>
								</div>
								<div class="form-group ">
									<label class="col-sm-3 control-label">Clave:</label>
									<div class="col-md-9">
										<input type="password" ng-model="password"
											class="form-control" required placeholder="Ingrese clave...">
									</div>
								</div>
								<div class="form-group ">
									<label class="col-sm-3 control-label">Repetir Clave:</label>
									<div class="col-md-9">
										<input type="password" ng-model="password2"
											class="form-control" required placeholder="Ingrese clave...">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div id="mapUser"></div>

							</div>
						</div>
						<br>
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

<link href="<c:url value="/resources/css/registerResponsible.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/js/mapUser.js"/>"></script>