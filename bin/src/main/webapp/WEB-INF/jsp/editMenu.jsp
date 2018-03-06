<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container" id="viewRestaurant" ng-controller="MenuController">
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
									<span class="glyphicon glyphicon-tags"></span> Men&uacute; <small>-
										Editar</small>
								</h1>
							</div>
						</div>
					</div>
					<form id="newRestForm" ng-submit="submitEditMenu()" class="form-horizontal" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-7">
								<div class="form-group form-group-lg">
									<label for="menuName" class="col-md-3 control-label">Nombre:</label>
									<div class="col-md-9">
										<input name="menuName" type="text" ng-model="menu.name"
											class="form-control" required placeholder="Ingrese nombre...">
									</div>
								</div>
								<div class="form-group form-group-lg">
									<label for="menuDescription" class="col-md-3 control-label">Descripci&oacute;n:</label>
									<div class="col-md-9">
                                        <textarea ng-model="menu.description" name="descripcion" class="form-control" rows="5" required="required" placeholder="Ingrese descripci�n..."></textarea>																					
									</div>
								</div>								
							</div>
							<div class="col-md-5">
								<div id="imageEditPreview" class="col-md-12" data-toggle="tooltip" data-placement="left" title="Agregar Foto...">
                                        <img class="img-responsive" src="data:image/jpg;base64,{{menu.image}}"/>                                        
								</div>
								<input id="uploadFile" type="file" accept="image/*" name="uploadFile" ng-model="menu.uploadFile" class="img hidden"/>																
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
<link href="<c:url value="/resources/css/registerRestaurant.css"/>"	rel="stylesheet">
<script src="<c:url value="/resources/js/chargeFile.js"/>"></script>
