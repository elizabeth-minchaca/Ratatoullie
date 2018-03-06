<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="recommendFriendsModal" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<form ng-submit="recommendMenuFriends()" id="calificarForm" class="form-horizontal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title">
						<span class="glyphicon glyphicon-star-empty"></span> <strong>Recomendar men&uacute; a todos mis amigos</strong>
					</h3>
				</div>
				<div class="modal-body">
					<div class="form-group form-group-lg">
						<label class="col-sm-3 control-label">Recomendaci&oacute;n:</label>
						<div class="col-sm-9">
							<textarea class="form-control" ng-model="recomendation.text" rows="7" required="required"
								placeholder="Ingrese una recomendacion..."></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
					<button id="calificarSubmit" type="submit"
						data-loading-text="Calificando..." class="btn btn-primary">Recomendar</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>