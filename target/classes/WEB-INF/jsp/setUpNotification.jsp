<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="breadcrumb">
  <a class="breadcrumb-item" href="#!/">Home /</a>
    <span class="breadcrumb-item active">Notificaciones - Configurar</span>
</nav> 
<div class="container" ng-controller="setUpNotification">
	<div class="row">
		<div class="col-md-10 col-md-offset-2">
			<div class="col-md-9 panel-heading panel-primary">
 				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-cog"></span><strong> Notificaciones</strong> <small>- Configuraci&oacute;n</small>
					</h3>
				</div>
				<br>
				<div style="color: saddlebrown;">
					<h5>Seleccione una o varias opciones para el bloqueo de recepci&oacute;n de notificaciones de comentarios realizados por usuarios con un tipo de ranking.</h5>
					<h6><strong>Visitante:</strong> Usuario con comentarios realizados menor a 20.</h6>
					<h6><strong>Commensal:</strong> Usuario con comentarios realizados entre 20 y 40.</h6>
					<h6><strong>Visitante:</strong> Usuario con comentarios realizados mayor a 40.</h6>
				</div>
				<div class="panel-body">
					<form ng-submit="submitConfiguration()" class="form-horizontal">
<!--                             <div class="form-group">
                                <label for="usuarioFormApellido" class="col-md-5 control-label">Bloquear todo:</label>
                                <div class="col-md-1">
                                    <input type="checkbox" ng-model="config.all" class="form-control" >
                                </div>
                            </div>
 -->                            <div class="form-group">
                                <label for="usuarioFormApellido" class="col-md-5 control-label">Visitante:</label>
                                <div class="col-md-1">
                                    <input type="checkbox" ng-model="config.visitor" ng-checked="config.all" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="usuarioFormApellido" class="col-md-5 control-label">Comensal:</label>
                               <div class="col-md-1">
                                    <input type="checkbox" ng-model="config.commensal" ng-checked="config.all" class="form-control" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="usuarioFormApellido" class="col-md-5 control-label">Gourmet:</label>
                                <div class="col-md-1">
                                    <input type="checkbox" ng-model="config.gourmet" ng-checked="config.all" class=" form-control" >
                                </div>
                            </div>
                            <br>
                            <div class="form-group">
                                <div class="col-md-3 col-md-offset-5">
                                    <button type="submit" data-loading-text="Guardando..."  class="btn btn-primary active">
                                        <span class="glyphicon glyphicon-floppy-disk"></span> GUARDAR</button>    
                                </div>
                            </div>
					</form>
				</div>
				<div class="panel-footer"></div>
			</div>
		</div>		
	</div>
</div>

	