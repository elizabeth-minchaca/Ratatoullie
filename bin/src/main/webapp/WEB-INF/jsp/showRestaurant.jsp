<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">   
<div class="row">
	<div class="col-md-12">
		<div class="col-md-6 col-md-offset-2">
			<h1 class="text-center" style="color: #b7b7b7;">{{ restaurant.name }}</h1>
		</div>
		<c:if test="${ user != null }">
			<div class="col-md-3">
				<!-- Trigger the modal with a button -->
				<h1><button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">Comentar</button></h1>
			</div>
		</c:if>
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
			      <img class="img-responsive rest-pic center-block" ng-src="data:image/jpg;base64,{{restaurant.image}}" style="height: 280px !important; width: 450px;" />
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
		<div class="col-md-4"><h4>UBICACIÓN</h4<></div>
	</div>
	<div class="row" style="padding-top: 10px;">
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
			<div class="cmmt-pnl">
				<div ng-if="restaurant.comments.length==0">
					<p class="text-center">Este restaurante todavía no tiene comentarios.</p>
				</div>
				<div ng-if="restaurant.comments.length!=0">
					<div class="media-body md-by col-md-12" ng-repeat="c in restaurant.comments">
				    	<h4 class="media-heading">{{ c.user.name }} <small><i class="idate">{{ c.date | date:'medium' }}</i> {{ c.vote }}</small></h4>
				    	<p>{{ c.text }}</p>
				    </div>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div id="mapShowRestaurant"></div>
		</div>
	</div>
	<div class="row text-center">
		<div class="col-md-5 col-md-offset-3 bar">
			<h4>MENÚ</h4>
		</div>
	</div>
	<div class="row">
		<div ng-if="restaurant.menus.length!=0">
			<div class="col-md-3" ng-repeat="m in restaurant.menus">
				<div class="panel panel-default mn-pnl">
					<div class="panel-body">
						<img class="img-responsive" ng-src="data:image/jpg;base64,{{m.image}}" style="height: 178px; width: 420px;" />
						<a class="res-name" type="button" href="#!showMenu/{{restaurant.id}}/{{m.id}}"><h4 class="text-center">{{ m.text }}</h4></a>
						<p class="title">DISPONIBLE DESDE: </p>
						<h5><span class="glyphicon glyphicon-time"></span> {{ m.startDate | date:'medium' }}</h5>
						<p class="title">HASTA: </p>
						<h5><span class="glyphicon glyphicon-time"></span> {{ m.endDate | date:'medium' }}</h5>
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
		    	<h3 class="modal-title">{{ restaurant.name }}</h3>
		  	</div>
			<div class="modal-body">
				<form ng-submit="submitComment(restaurant)" class="form-horizontal" id="formComment">
					<div class="form-group">
						<div class="col-md-12">
							<div><h4><label for="option">Voto</label></h4></div>
							<label class="radio-inline"><input type="radio" ng-model="vote" name="option" value="POSITIVE" required>Positivo</label>
							<label class="radio-inline"><input type="radio" ng-model="vote" name="option" value="NEUTRAL">Neutral</label>
							<label class="radio-inline"><input type="radio" ng-model="vote" name="option" value="NEGATIVE">Negativo</label>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">
							<h4><label for="commnt">Comentario</label></h4>
							<textarea ng-model="comment" name="commnt" class="form-control" placeholder="Opine acerca de este restaurante." rows="4" required></textarea>
						</div>
					</div>
				</form>
			</div>
		  	<div class="modal-footer">
		  		 <div class="form-group"> 
    				<div class="col-md-4 center-block" style="float:none;">
		    			<button type="submit" class="btn btn-success btn-send btn-block" form="formComment" value="Submit"><span class="glyphicon glyphicon-ok"></span> Confirmar</button>
		  			</div>
		  		</div>
		  	</div>
		</div>
	
	</div>
</div>

<script src="<c:url value="/resources/js/showRestaurant.js"/>"></script>
<script src="<c:url value="/resources/js/restaurantComment.js"/>"></script>
