<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">   
<div class="container-fluid cnt-fld">
	<div class="col-md-12"><div class="col-md-6"><h1 class="text-center" style="color: #b7b7b7">{{ menu.text }}</h1></div></div>
	<br>
	<div class="row">
		<div class="col-md-6">
			<img class="img-responsive rest-pic center-block" ng-src="data:image/jpg;base64,{{menu.image}}" style="height: 280px !important; width: 350px;" />
		</div>
		<div class="col-md-3">		
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
			<c:if test="${user != null}">
				<div class="panel panel-default pnl">
					<div class="panel-body">
					    <div class="btn-group">
					      	<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" >
					      		Recomendar men&uacute; <span class="caret"></span>
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
		<div class="col-md-5 bar">
			<div class="col-md-8">
				<h4>COMENTARIOS</h4>
			</div>
			<c:if test="${ user != null }">
				<div class="col-md-4">
					<!-- Trigger the modal with a button -->
					<h4><button type="button" class="btn btn-success" data-toggle="modal" data-target="#modal">Comentar</button></h4>
				</div>
			</c:if>
		</div>
	</div>
	<div class="row" style="padding-top: 10px;">
		<div class="col-md-5">
			<div ng-if="menu.comments.length==0">
				<p class="text-center">Este menú todavía no tiene comentarios.</p>
			</div>
			<div ng-if="menu.comments.length!=0">
				<div class="media-body md-by col-md-12" ng-repeat="m in menu.comments">
			    	<h4 class="media-heading">{{ m.user.name }} <small><i class="idate">{{ m.date | date:'medium' }}</i> {{ m.vote }}</small></h4>
			    	<p>{{ m.text }}</p>
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
		    	<h3 class="modal-title">{{ menu.text }}</h3>
		  	</div>
			<div class="modal-body">
				<form ng-submit="submitComment(menu)" class="form-horizontal" id="formComment">
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
							<textarea ng-model="comment" name="commnt" class="form-control" placeholder="Opine acerca de este menú." rows="4" required></textarea>
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
<jsp:include page="/WEB-INF/jsp/recommendMenuFriend.jsp" flush="true" />
<jsp:include page="/WEB-INF/jsp/recommendMenuFriends.jsp" flush="true" />
<script src="<c:url value="/resources/js/showMenu.js"/>"></script>
<script src="<c:url value="/resources/js/menuComment.js"/>"></script>