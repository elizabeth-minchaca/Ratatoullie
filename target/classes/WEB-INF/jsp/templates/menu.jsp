<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<nav class="navbar navbar-default btm-space">
	<div class="container-fluid ">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand logo" href="/${site}/"><img class="img-responsive" src="resources/imgs/favicon-32.png"></a>
			<a class="navbar-brand" href="/${site}/">Ratatouille</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><span class="glyphicon glyphicon-cutlery"></span> RESTAURANTES<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/${site}/#!restaurant/list"><span class="glyphicon glyphicon-th-list"></span> LISTAR</a></li>
							<li><a href="/${site}/#!restaurant/searchLocation"><span class="glyphicon glyphicon-search"></span> B&Uacute;SQUEDA POR UBICACI&Oacute;N</a></li>
							<li><a href="/${site}/#!restaurant/searchComments"><span class="glyphicon glyphicon-search"></span> B&Uacute;SQUEDA MAS COMENTADOS</a></li>
							<li><a href="/${site}/#!restaurant/searchName"><span class="glyphicon glyphicon-search"></span> B&Uacute;SQUEDA POR NOMBRE</a></li>
						</ul>
					</li>
					<c:if test="${user != null}">
						<c:if test="${userResponsible != null}">
							<li class="dropdown"><a href="" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false"><span class="glyphicon glyphicon-tag"></span> MI RESTAURANT<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<c:if test="${user.enable == true}">							
										<li><a href="#!registerRestaurant"><span class="glyphicon glyphicon-plus"></span> NUEVO</a></li>
									</c:if>
									<li><a href="#!myRestaurants"><span class="glyphicon glyphicon-th-list"></span> MIS RESTAURANTES</a></li>
								</ul>
							</li>
							<li class="dropdown"><a href="" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false" ng-controller="homeUser"><span class="badge">{{user.quantityOfNotificationsNotSeen}}</span> NOTIFICACIONES<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="#!viewNotifications"><span class="glyphicon glyphicon-th-list"></span> REVISAR</a></li>
									<li><a href="#!setUpNotification"><span class="glyphicon glyphicon-cog"></span> CONFIGURAR</a></li>
								
								</ul>
							</li>							
						</c:if>
				</c:if>		
				</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${user == null}">
						<li><a href="#!register" data-target="#registrarUsuario">REGISTRATE</a></li>
						<li><a href="#!registerResponsible" data-target="#registrarResponsable">REGISTRA TU RESTAURANT</a></li>
						<li><a href="" data-toggle="modal" data-target="#login"><span class="glyphicon glyphicon-log-in"></span><strong><small> INGRESAR</small></strong></a></li>					
					</c:when>
					<c:otherwise>
						<li class="dropdown">
							<a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
									<span class="glyphicon glyphicon-user"> </span><small> ${fn:toUpperCase(user.name)} ${fn:toUpperCase(user.lastName)}</small><span class="caret"> </span>
							</a>
							<ul class="dropdown-menu">
							    <li class="dropdown-header text-center"><strong>Bienvenido</strong></li>
            					<li class="dropdown-header text-center"><strong><em><small>${user.mail}</small></em></strong> </li>
            					<li role="separator" class="divider"></li>	
								<li><a href="#!showPerfil"><span class="glyphicon glyphicon-cog"> </span> MI PERFIL</a></li>
								<li><a href="#!friends"><span class="fa fa-users"> </span> Amigos</a></li>
<!-- 								<li><a href="#!editUser"><span class="glyphicon glyphicon-edit"> </span> EDITAR PERFIL</a></li>	-->
             					<li role="separator" class="divider"></li>            												
								<c:if test="${userResponsible != null}">
									<li class="dropdown-header">MI RESTAURANT</li>
									<li><a href="#!registerRestaurant"><span class="glyphicon glyphicon-plus"></span> NUEVO</a></li>
									<li><a href="#!myRestaurants"><span class="glyphicon glyphicon-th-list"></span> MIS RESTAURANTES</a></li>
            						<li role="separator" class="divider"></li>
								</c:if>
 								<li><a href="#" id="logout"><span class="glyphicon glyphicon-log-out"></span> SALIR</a></li>						
							</ul>
						</li>
						<script src="<c:url value="/resources/js/logout.js"/>"></script>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>