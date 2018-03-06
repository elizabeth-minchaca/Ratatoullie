<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="breadcrumb">
  <a class="breadcrumb-item" href="#!/">Home /</a>
  <span class="breadcrumb-item active">Mi Perfil - Ver</span>
</nav> 
<div class="container" ng-controller="editUser">
	<div class="row">
		<div id="usuarioContainer" class="col-md-10 col-md-offset-1">
			<div class="panel-heading panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title"></h3>
				</div>
				<div class="panel-body">
					<div class="page-header">
						<div class="row">
							<div class="col-md-6">
								<h1>
									<span class="glyphicon glyphicon-user"></span> Mi Perfil <small>-
										Ver</small>
								</h1>
							</div>
							<div id="usuarioEditButton" class="col-md-6 text-right active">
								<h1>
									<small> <a href="#!editUser"> <span
											class="glyphicon glyphicon-edit"> </span> Editar
									</a>
									</small>
								</h1>
							</div>
						</div>
					</div>
					<form class="form-horizontal">
						<div class="form-group form-group-lg">
							<label for="newCouchTipo" class="col-sm-3 control-label ">E-mail:</label>
							<div class="col-md-9">
								<p class="form-control-static">{{dataUser.mail}}</p>
							</div>
						</div>
						<div class="form-group form-group-lg">
							<label for="usuarioFormApellido" class="col-sm-3 control-label">Apellido:</label>
							<div class="col-md-9">
								<p class="form-control-static">{{dataUser.lastName}}</p>
							</div>
						</div>
						<div class="form-group form-group-lg">
							<label for="usuarioFormNombre" class="col-sm-3 control-label">Nombre:</label>
							<div class="col-md-9">
								<p class="form-control-static">{{dataUser.name}}</p>
							</div>
						</div>
						<div class="form-group form-group-lg">
							<label for="usuarioFormApellido" class="col-sm-3 control-label">Ranking:</label>
							<div class="col-md-9">
								<p class="form-control-static">{{dataUser.ranking}}</p>
							</div>
						</div>						
						<div class="form-group form-group-lg">
							<label for="usuarioFormNombre" class="col-sm-3 control-label">Estado:</label>
							<div class="col-md-9">
								<p class="form-control-static">
									<span ng-if="dataUser.enable == true ">USUARIO HABILITADO</span> 
									<span ng-if="dataUser.enable == false ">USUARIO DESHABILITADO</span>								
								</p>
							</div>
						</div>
						
						<div class="form-group form-group-lg">
							<label for="usuarioFormNombre" class="col-sm-3 control-label">Ubicaci&oacute;n:</label>
							<div class="col-md-9">
								<p class="form-control-static">{{dataUser.location|toCoordinates}}</p>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>