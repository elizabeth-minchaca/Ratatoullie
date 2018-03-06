<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="breadcrumb">
  <a class="breadcrumb-item" href="#!/">Home /</a>
    <span class="breadcrumb-item active">Mi Restaurant - Nuevo</span>
</nav> 
<div class="container" id="registerRestaurant" ng-controller="registerRestaurant">
	<div class="row">
		<div id="newRestContainer" class="col-md-11">
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
										Nuevo</small>
								</h1>
							</div>
						</div>
					</div>
					<form id="newRestForm" ng-submit="submitCreateRestaurant()"
						class="form-horizontal" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-7">
								<div class="form-group form-group-lg">
									<label for="restRestaurant" class="col-md-3 control-label">Nombre:</label>
									<div class="col-md-9">
										<input type="text" ng-model="rest.restaurant"
											class="form-control" required placeholder="Ingrese nombre...">
									</div>
								</div>
								<div class="form-group form-group-lg">
									<label for="dateRestaurant" class="col-md-3 control-label">Fecha
										de Apertura:</label>
									<div class="col-md-9">
										<input type="date" ng-model="rest.date" class="form-control"
											required placeholder="Ingrese fecha dd/mm/aaaa...">
									</div>
								</div>
								<div class="form-group form-group-lg">
									<div id="imagePreview" class="col-md-12" data-toggle="tooltip"
										data-placement="left" title="Agregar Foto...">
										<h4 class="text-center">
											<strong>FOTO DEL RESTAURANT</strong>
										</h4>
									</div>
									<input id="uploadFile" type="file" accept="image/*"
										name="uploadFile" ng-model="rest.uploadFile" required
										class="img hidden" />
								</div>
							</div>

							<div class="col-md-5">
								<div id="mapRegistRestaurant"></div>
							</div>
						</div>

						<div class="page-header">
							<div class="row">
								<div class="col-md-6">
									<h1>
										<span class="glyphicon glyphicon-tags"></span> Men&uacute; <small>-
											Nuevo</small>
									</h1>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-7">
								<div class="form-group form-group-lg">
									<label for="nameMenu" class="col-md-3 control-label">Nombre:</label>
									<div class="col-md-9">
										<input type="text" ng-model="rest.menuName" name="nameMenu"
											class="form-control" required placeholder="Ingrese nombre...">
									</div>
								</div>
								<div class="form-group form-group-lg">
									<label for="nameMenu" class="col-md-3 control-label">Descipci&oacute;n:</label>
									<div class="col-md-9">
										<!-- 											<input type="text" ng-model="rest.menuDescription" name="nameMenu" class="form-control" required placeholder="Ingrese descripcion...">
 -->
										<textarea ng-model="rest.menuDescription" name="descripcion"
											class="form-control" rows="5" required="required"
											placeholder="Ingrese descripción..."></textarea>
									</div>
								</div>
							</div>
							<div class="col-md-5">
								<div id="imagePreviewMenu" class="col-md-12"
									data-toggle="tooltip" data-placement="left"
									title="Agregar Foto...">
									<h4 class="text-center">
										<strong>FOTO DEL MENU</strong>
									</h4>
								</div>
								<input id="uploadFileMenu" type="file" accept="image/*"
									name="uploadFileMenu" ng-model="rest.uploadFileMenu" required
									class="img hidden" />
							</div>

						</div>
						<div class="row">
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" data-loading-text="Enviando..."
										class="btn btn-primary btn-lg active">
										<span class="glyphicon glyphicon-floppy-save"></span> CREAR
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

<link href="<c:url value="/resources/css/registerRestaurant.css"/>"
	rel="stylesheet">
<script src="<c:url value="/resources/js/chargeFile.js"/>"></script>
<script src="<c:url value="/resources/js/mapRegistRestaurant.js"/>"></script>

