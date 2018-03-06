<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<nav class="breadcrumb">
  <a class="breadcrumb-item" href="#!/">Home /</a>
   <a class="breadcrumb-item" href="#!viewNotifications">Notificaciones - Revisar /</a>
   <span class="breadcrumb-item active">Notificaci&oacute;n - Ver</span>
</nav> 
<div class="row" ng-controller="viewNotification">
	<div class="col-md-10 col-md-offset-2">
		<div class="col-md-8">
		
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h5 class="text-center">
							<strong>DETALLES DE LA NOTIFICACI&Oacute;N</strong>
						</h5>
					</div>
					<div class="panel-body">
						<br>
						<div class="col-md-7">
							<h5><strong>De:</strong> {{notification.comment.user.mail}}</h5>
							<h5><strong>Nombre completo:</strong> {{notification.comment.user.name}} {{notification.comment.user.lastName}}</h5>																					
						</div>
						<div class="col-md-5">
							<h5><strong>Fecha y hora del comentario:</strong> {{notification.comment.date | date:"dd/MM/yyyy 'a las' h:mm:ss a"}}</h5>
						</div>		
						<div class="col-md-5">
							<h5><strong>Voto: <em>{{notification.comment.vote}}</em> </strong></h5>													
						</div>												
						<div class="col-md-7">
							<h5><strong>Comentario:</strong></h5>							
							<h5>{{notification.comment.text}}</h5>						
						</div>
															
					</div>
					<br>
					<div class="panel-footer text-center">
							<h4><a role="button" ng-click="redirectToView()" class="active">	
								<span class="glyphicon glyphicon-hand-right"></span> <strong>  Ver el comentario en tu {{notification.comment.commenting.name}}</strong>
							</a></h4>											
					</div>
				</div>
				
		</div>
		<div class="col-md-3">
			<a role="button" ng-click="reloadRoute()" class="btn btn-info"><span class="glyphicon glyphicon-arrow-left"></span> Volver</a>				
		</div>
		
	</div>
</div>
