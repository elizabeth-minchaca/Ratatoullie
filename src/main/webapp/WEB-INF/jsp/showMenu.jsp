<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">  
<nav class="breadcrumb">
  <a class="breadcrumb-item" href="#!/">Home /</a>
    <a class="breadcrumb-item" href="#!restaurant/list/">Restaurantes - Listar /</a>
  <a class="breadcrumb-item" href="#!/showRestaurant/{{menu.restaurant.id}}">Restaurante /</a>
  <span class="breadcrumb-item active">Men&uacute;</span>
</nav> 
<div class="container-fluid cnt-fld">
	<div class="col-md-12"><h1 class="text-center" style="color: #b7b7b7">{{ menu.text }}</h1></div>
	<br>
	<div class="row">
		<div class="col-md-6 col-md-offset-1">
			<img class="img-responsive rest-pic center-block" ng-src="data:image/jpg;base64,{{menu.image}}" />
		</div>
		<div class="col-md-4">		
			<div class="panel panel-default mn-pnl">
				<div class="panel-body">
					<h5 class="title">RESTAURANT</h5>
					<h5><span class="glyphicon glyphicon-user"></span> {{ menu.restaurant.name }}</h5>
					<h5 class="title">DESCRIPCIÓN</h5>
					<h5><span class="glyphicon glyphicon-tags"></span> {{ menu.description}}</h5>
					<h5 class="title">DESDE</h5>
					<h5><span class="glyphicon glyphicon-time"></span> {{ menu.startDate | date:'medium' }}</h5>
					<div ng-if="m.endDate>0">
						<h5 class="title">HASTA</h5>
						<h5><span class="glyphicon glyphicon-time"></span> {{ menu.endDate | date:'medium' }}</h5>
					</div>
				</div>
			</div>
			<c:if test="${user != null and user.enable}">
				<div class="panel panel-default pnl" ng-if="menu.restaurant.idCurrentMenu == menu.id">
					<div class="panel-body">
					    <div class="btn-group">
					      	<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" >
					      		<span class="glyphicon glyphicon-thumbs-up" style="color: white;"></span> Recomendar <span class="caret"></span>
					      	</button>
		 				    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
						      <li><a tabindex="-1" href="" data-toggle="modal" data-target="#recommendFriendModal"> A un amigo</a></li>
						      <li><a tabindex="-1" href="" data-toggle="modal" data-target="#recommendFriendsModal"> A todos mis amigos</a></li>					      
						    </ul>				      
					      
					    </div>    
					</div>
				</div>
			</c:if>
		</div>
	</div>
	<div class="row">
		<div class="col-md-5 bar col-md-offset-3">
			<div class="col-md-8 text-center">
				<h4>COMENTARIOS</h4>
			</div>
			<c:if test="${user != null and user.enable}">
				<div class="col-md-4" ng-if="menu.restaurant.idCurrentMenu == menu.id">
					<!-- Trigger the modal with a button -->
					<h4><button type="button" class="btn btn-success" data-toggle="modal" data-target="#modal"><span class="glyphicon glyphicon-comment"></span> Comentar</button></h4>
				</div>
			</c:if>
		</div>
	</div>
	<div class="row" style="padding-top: 10px;">
		<div class="col-md-5 col-md-offset-3">
			<div ng-if="menu.comments.length==0">
				<p class="text-center">Este menú todavía no tiene comentarios.</p>
			</div>
			<div ng-if="menu.comments.length!=0">
				<div class="md-by col-xs-12" ng-repeat="m in menu.comments">
			    	<div class="col-xs-9">
			    		<h4 class="media-heading">{{ m.user.name }} <small><i class="">{{ m.date | date:'medium' }}</i></small></h4>
			    	</div>
			    	<div class="col-xs-3">
			    		<span class="glyphicon glyphicon-{{m.vote}}"></span>
			    	</div>
			    	<div class="col-xs-12">
			    		<p>{{ m.text }}</p>
			    	</div>
			    </div>
			</div>
		</div>
	</div>
	<br>
</div>

<!-- Modal -->
<div id="modal" class="modal fade" role="dialog" ng-controller="menuComment">
	<div class="modal-dialog">
	
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
		    	<button type="button" class="close" data-dismiss="modal">&times;</button>
		    	<h3 class="modal-title"><span class="glyphicon glyphicon-comment"></span> <strong>{{ menu.text }}</strong></h3>
		  	</div>
			<div class="modal-body">
				<form ng-submit="submitComment(menu)" class="form-horizontal" id="formComment">
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
							<textarea ng-model="comment" name="commnt" class="form-control" placeholder="Opine acerca de este men&uacute;." rows="7" required></textarea>
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
<jsp:include page="/WEB-INF/jsp/recommendMenuFriend.jsp" flush="true" />
<jsp:include page="/WEB-INF/jsp/recommendMenuFriends.jsp" flush="true" />