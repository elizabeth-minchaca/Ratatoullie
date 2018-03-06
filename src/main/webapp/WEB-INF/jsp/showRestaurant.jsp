<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">   
<nav class="breadcrumb">
  <a class="breadcrumb-item" href="#!/">Home /</a>
    <a class="breadcrumb-item" href="#!restaurant/list/">Restaurantes - Listar /</a>
  <span class="breadcrumb-item active">Restaurante</span>
</nav> 
<div class="row">
	<div class="col-md-12">
			<h1 class="text-center" style="color: #b7b7b7;">{{ restaurant.name }}</h1>		
	</div>
</div>
<div class="container-fluid cnt-fld">	
	<div class="row">
		<div id="myCarousel" class="carousel slide center-block" data-ride="carousel">
			  <!-- Indicators -->
			  <ol class="carousel-indicators">
			    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
	<!-- 		    <li data-target="#myCarousel" data-slide-to="1"></li>
			    <li data-target="#myCarousel" data-slide-to="2"></li> -->
			  </ol>
			
			  <!-- Wrapper for slides -->
			  <div class="carousel-inner">
			    <div class="item active">
			    	<div class="col-md-6 center-block" style="float: none;">
			      		<img class="img-responsive rest-pic" ng-src="data:image/jpg;base64,{{restaurant.image}}" />
			    	</div>
			    </div>
			  </div>
			
			  <!-- Left and right controls -->
			  <a class="left carousel-control" href="" data-slide="prev">
			    <span class="glyphicon glyphicon-chevron-left"></span>
			  </a>
			  <a class="right carousel-control" href="" data-slide="next">
			    <span class="glyphicon glyphicon-chevron-right"></span>
			  </a>
		</div>
	</div>
	<br>
	<div class="row bar text-center">
		<div class="col-md-3"><h4>RESUMEN</h4></div>
		<div class="col-md-5"><h4>COMENTARIOS</h4></div>
		<div class="col-md-4"><h4>UBICACIÓN</h4></div>
	</div>
	<div class="row" style="padding: 10px;">
		<div class="col-md-3">		
			<div class="panel panel-default pnl">
				<div class="panel-body pnl-bdy">
					<h5 class="title">DUEÑO</h5>
					<h5><span class="glyphicon glyphicon-user"></span> {{ restaurant.owner.name }}</h5>
					<h5 class="title">FECHA DE APERTURA</h5>
					<h5><span class="glyphicon glyphicon-calendar"></span> {{ restaurant.openingDate | date:'medium' }}</h5>
					<h5 class="title">UBICACIÓN</h5>
					<h5><span class="glyphicon glyphicon-map-marker"></span> {{ restaurant.location | toCoordinates }}</h5>
					<h5 class="title">CATEGORÍA</h5>
					<h5><span class="glyphicon glyphicon-tags"></span> {{ restaurant.category.name }}</h5>
				</div>
			</div>
		</div>
		<div class="col-md-5 cmt-cntr">
			<c:if test="${user != null and user.enable}">
				<div class="col-md-3 center-block" style="float: none; margin-bottom: 15px;">
					<!-- Trigger the modal with a button -->
					<button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-comment"></span> Comentar</button>
				</div>
			</c:if>
			<div class="cmmt-pnl">
				<div ng-if="restaurant.comments.length==0">
					<p class="text-center">Este restaurante todavía no tiene comentarios.</p>
				</div>
				<div ng-if="restaurant.comments.length!=0">
					<div class="md-by col-xs-12" ng-repeat="c in restaurant.comments">
				    	<div class="col-xs-9">
				    		<h4 class="media-heading">{{ c.user.name }} <small><i class="">{{ c.date | date:'medium' }}</i></small></h4>
				    	</div>
				    	<div class="col-xs-3">
				    		<span class="glyphicon glyphicon-{{c.vote}}"></span>
				    	</div>
				    	<div class="col-xs-12">
				    		<p>{{ c.text }}</p>
				    	</div>
				    </div>
				</div>
			
			</div>
		</div>
		<div class="col-md-4">
			<div id="mapShowRestaurant"></div>
		</div>
	</div>
	<div class="row text-center" style="margin-top">
		<div class="col-md-5 col-md-offset-3 bar">
			<h4>MENÚ</h4>
		</div>
	</div>
	<div class="row">
		<div ng-if="restaurant.menus.length!=0">
			<div class="col-md-3 col-md-offset-4" ng-repeat="m in restaurant.menus  | orderBy:'id':true"">
				<div class="panel panel-default mn-pnl">
					<div class="panel-body">
						<img class="img-responsive" ng-src="data:image/jpg;base64,{{m.image}}" style="height: 178px; width: 420px;" />
						<a class="res-name" type="button" href="#!showMenu/{{restaurant.id}}/{{m.id}}"><h4 class="text-center">{{ m.text }}</h4></a>
						<p class="title">DISPONIBLE DESDE: </p>
						<h5><span class="glyphicon glyphicon-time"></span> {{ m.startDate | date:'medium' }}</h5>
						<div ng-if="m.endDate > 0">		
							<p class="title">HASTA: </p>										
							<h5><span class="glyphicon glyphicon-time"></span> {{ m.endDate | date:'medium' }}</h5>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div ng-if="restaurant.menus.length==0">
			<p class="col-md-5 col-md-offset-3 text-center">Este restaurante todavía no tiene menú.</p>
		</div>
	</div>
</div>


<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog" ng-controller="restaurantComment">
	<div class="modal-dialog">
	
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
		    	<button type="button" class="close" data-dismiss="modal">&times;</button>
		    	<h3 class="modal-title"><span class="glyphicon glyphicon-comment"></span> <strong>{{ restaurant.name }}</strong></h3>
		  	</div>
			<div class="modal-body">
				<form ng-submit="submitComment(restaurant)" class="form-horizontal" id="formComment">
					<div class="form-group form-group-lg">
						<label class="col-sm-3 control-label">Voto:</label>
						<div class="col-sm-9">
							<label class="radio-inline"><input type="radio" ng-model="vote" name="option" value="POSITIVE" required><span class="glyphicon glyphicon-thumbs-up"></span></label>
							<label class="radio-inline"><input type="radio" ng-model="vote" name="option" value="NEUTRAL"><span class="glyphicon glyphicon-hand-up"></span></label>
							<label class="radio-inline"><input type="radio" ng-model="vote" name="option" value="NEGATIVE"><span class="glyphicon glyphicon-thumbs-down"></span></label>
						</div>
					</div>					
					<div class="form-group form-group-lg">
						<label class="col-sm-3 control-label">Comentario:</label>
						<div class="col-sm-9">
							<textarea ng-model="comment" name="commnt" class="form-control" placeholder="Opine acerca de este restaurante." rows="7" required></textarea>
						</div>
					</div>					
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
						<button type="submit" data-loading-text="Comentando..." class="btn btn-success"><span class="glyphicon glyphicon-ok"></span> Comentar</button>
					</div>					
				</form>
			</div>
		  	
		</div>
	
	</div>
</div>