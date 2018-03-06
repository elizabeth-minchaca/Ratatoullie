<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link
	href="<c:url value="/resources/js/bootstrap-table/bootstrap-table.min.css"/>"
	rel="stylesheet">
<script
	src="<c:url value="/resources/js/bootstrap-table/bootstrap-table.min.js"/>"></script>
<script
	src="<c:url value="/resources/js/bootstrap-table/bootstrap-table-locale-all.min.js"/>"></script>
<script src="<c:url value="/resources/js/friends.js"/>"></script>
<nav class="breadcrumb">
  <a class="breadcrumb-item" href="#!/">Home /</a>
  <span class="breadcrumb-item active">Amigos</span>
</nav> 
<div class="container" ng-controller="friends">
	<div class="row">
		<div class="col-xs-5">
			<div class="row">
				<h1 class="text-center">Seguidos</h1>
			</div>
			<div class="row">
				<form name="searchLocation" ng-submit="addFollow()"
					class="form-horizontal">
					<div class="col-xs-8">
						<input class="form-control" id="mailAdd" type="email" ng-model="friend.mail"
							required="required" placeholder="Mail" />
					</div>
					<button type="submit" class="btn btn-primary ">Seguir</button>
				</form>
			</div>
			<div class="row">
				<div id="toolbarFollowings" class="btn-group">
					<button id="removeButton" type="button" class="btn btn-default"
						data-toggle="modal" data-target="#remove" disabled="true"
						ng-click="removeFollow()" data-toogle="tooltip" title="Eliminar">
						<i class="glyphicon glyphicon-trash"></i>
					</button>
				</div>

				<table id="tableFollowings" data-show-columns="true"
					data-search="true" data-show-toggle="true" data-toolbar="#toolbarFollowings"
					data-pagination="true" data-page-size="10"
					data-page-list="[10, 25, 50, 100, All]" data-locale="es-ES"
					data-click-to-select="true" data-sort-name="name">
					<thead>
						<tr>
							<th data-field="check" data-checkbox="true"></th>
							<th data-field="name" data-sortable="true">Nombre</th>
							<th data-field="lastName" data-sortable="true">Apellido</th>
							<th data-field="mail" data-sortable="true">Mail</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div class="col-xs-5 col-xs-offset-2">
			<div class="row">
				<h1 class="text-center">Seguidores</h1>
			</div>
			<div class="row">
				<table id="tableFollowers" data-show-columns="true"
					data-search="true" data-show-toggle="true" data-toolbar="#toolbar"
					data-pagination="true" data-page-size="10"
					data-page-list="[10, 25, 50, 100, All]" data-locale="es-ES"
					data-click-to-select="true" data-sort-name="name">
					<thead>
						<tr>
							<th data-field="name" data-sortable="true">Nombre</th>
							<th data-field="lastName" data-sortable="true">Apellido</th>
							<th data-field="mail" data-sortable="true">Mail</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>