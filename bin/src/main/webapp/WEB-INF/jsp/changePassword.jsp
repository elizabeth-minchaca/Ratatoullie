<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 		prefix="c" %>
<div class="modal fade" tabindex="-1" role="dialog" id="passChangeModal" ng-controller="changePassword">
	<div class="modal-dialog">
		<div class="modal-content">
			<form  id="passChangeForm" ng-submit="submitChangePassword()" class="form-horizontal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Cambiar Contraseña</h4>
				</div>
				<div class="modal-body">
					<div class="form-group form-group-lg">
						<label for="oldPassword" class="col-sm-3 control-label" for="oldPassword">Actual</label>
						<div class="col-sm-9">
							<input id="passActual" required="required" type="password"
								class="form-control"  ng-model="user.oldPassword">
						</div>
					</div>
					<div class="form-group form-group-lg">
						<label for="newPassword" class="col-sm-3 control-label" for="newPassword">Nueva</label>
						<div class="col-sm-9">
							<input id="passChangeNueva" required="required" type="password"
								class="form-control"  ng-model="user.newPassword">
						</div>
					</div>
					<div class="form-group form-group-lg">
						<label for="newPassword2" class="col-sm-3 control-label" for="newPassword2">Confirmar</label>
						<div class="col-sm-9">
							<input id="passChangeNuevaConfirm" required="required" type="password" 
							class="form-control" ng-model="user.newPassword2">
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
					<button id="passChangeSubmit" type="submit" class="btn btn-primary">Cambiar</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<script src="<c:url value="/resources/js/changePassword.js"/>"></script>
    