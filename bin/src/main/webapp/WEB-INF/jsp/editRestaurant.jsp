<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container" id="editRestaurant"
	ng-controller="RestaurantController">
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
										Editar</small>
								</h1>
							</div>
						</div>
					</div>
					<form id="newRestForm" ng-submit="submitEditRestaurant()"
						class="form-horizontal" enctype="multipart/form-data">
						<input type="hidden" ng-model="rest.id" />
						<div class="row">
							<div class="col-md-7">
								<div class="form-group form-group-lg">
									<label for="restRestaurant" class="col-md-3 control-label">Nombre:</label>
									<div class="col-md-9">
										<input type="text" ng-model="rest.name" class="form-control"
											required placeholder="Ingrese nombre...">
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
								<div class="row">
									<div id="imageEditPreview" class="col-md-4 col-md-offset-8"
										data-toggle="tooltip" data-placement="left"
										title="Agregar Foto...">
										<img class="img-responsive"
											src="data:image/jpg;base64,{{rest.image}}" />
									</div>
								</div>
								<input id="uploadFile" type="file" accept="image/*"
									name="uploadFile" ng-model="rest.uploadFile" class="img hidden" />
							</div>

							<div class="col-md-5">
								<div id="mapEditRestaurant"></div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" data-loading-text="Enviando..."
										class="btn btn-primary btn-lg active">
										<span class="glyphicon glyphicon-floppy-disk"></span> GUARDAR
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

