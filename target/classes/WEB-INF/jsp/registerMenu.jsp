<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div  id="viewRestaurant" ng-controller="registerMenu">
<nav class="breadcrumb">
  <a class="breadcrumb-item" href="#!/">Home /</a>
    <a class="breadcrumb-item" href="#!myRestaurants">Mi Restaurant - Mis Restaurantes /</a>
    <a class="breadcrumb-item" href="#!viewRestaurant/{{id}}">Restaurant - Ver /</a>
  <span class="breadcrumb-item active">Men&uacute; - Nuevo</span>
</nav> 
	<div class=" container row">
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
										Nuevo</small>
								</h1>
							</div>
						</div>
					</div>
					<form id="newRestForm" ng-submit="submitCreateMenu()" class="form-horizontal" enctype="multipart/form-data">
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
                                        <textarea ng-model="menu.description" name="descripcion" class="form-control" rows="5" required="required" placeholder="Ingrese descripción..."></textarea>																														
									</div>
								</div>
							</div>								
							<div class="col-md-5">
								<div id="imagePreview" class="col-md-12" data-toggle="tooltip"
									data-placement="left" title="Agregar foto del Menu...">
									<h4 class="text-center"><strong>FOTO DEL MEN&Uacute;</strong></h4>
								</div>
								<input id="uploadFile" type="file" accept="image/*" name="uploadFile" ng-model="menu.uploadFile" required class="img hidden"/>																
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
<link href="<c:url value="/resources/css/registerRestaurant.css"/>"	rel="stylesheet">
<script src="<c:url value="/resources/js/chargeFile.js"/>"></script>
