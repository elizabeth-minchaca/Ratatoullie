<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container" ng-controller="editUser" id="editUser">
	<div class="row">
		<div class="col-md-11">
			<div class="panel-heading panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title"></h3>
				</div>
				<div class="panel-body">
					<div class="page-header">
						<div class="row">
							<div class="col-md-6">
								<h1>
									<span class="glyphicon glyphicon-user"></span> Mi Perfil <small>- Editar</small>
								</h1>
							</div>
						</div>
					</div>
					<form ng-submit="submitEditUser()" class="form-horizontal" id="formEditUser">
                            <div class="form-group form-group-lg">
                                <label class="col-md-3 control-label ">E-mail</label>
                                <div class="col-sm-9">
                                    <p class="form-control-static">${user.mail}</p>
                                </div>
                            </div>
                            
                            <div class="form-group form-group-lg">
                                <label class="col-md-3 control-label ">Contraseña</label>
                                <div class="col-lg-9">
                                    <div class="input-group">
                                        <input type="text" class="form-control" readonly value="**********">
                                        <span class="input-group-btn">                                      
                                            <button data-toggle="modal" data-target="#passChangeModal" class="btn btn-primary btn-lg active" type="button"><span class="glyphicon glyphicon-edit"></span>  CAMBIAR</button>									
 										</span>
                                    </div><!-- /input-group -->
                                </div>
                            </div>
                            
                            <div class="form-group form-group-lg">
                                <label class="col-md-3 control-label ">Estado de su cuenta</label>
                                <div class="col-lg-9">
									<c:choose>
										<c:when test="${user.enable}">
	                                       <div class="input-group">
		                                        <input type="text" class="form-control" readonly value="HABILITADO">
		                                        <span class="input-group-btn">                                      
		                                            <button class="btn btn-danger btn-lg active" type="button" ng-click="disableUser()"><span class="glyphicon glyphicon-remove-sign"></span> DESHABILITAR</button>									
		 										</span>
		                                    </div><!-- /input-group -->
		                                </c:when>
		                                <c:otherwise>  
		                                    <div class="input-group">
		                                        <input type="text" class="form-control" readonly value="DESHABILITADO">
		                                        <span class="input-group-btn">                                      
		                                            <button class="btn btn-success btn-lg active" type="button" ng-click="enableUser()"><span class="glyphicon glyphicon-ok-sign"></span> HABILITAR</button>									
		 										</span>
		                                    </div><!-- /input-group -->
		                               </c:otherwise>     
	                                 </c:choose>   
                                </div>
                            </div>
                            
                            <div class="form-group form-group-lg">
                                <label for="usuarioFormApellido" class="col-sm-3 control-label">Apellido:</label>
                                <div class="col-sm-9">
                                    <input required="required" type="text" ng-model="dataUser.lastName" class="form-control" >
                                </div>
                            </div>
							<div class="form-group form-group-lg">
								<label for="usuarioFormNombre" class="col-sm-3 control-label">Nombre:</label>
								<div class="col-sm-9">
	                                   <input required="required" type="text" ng-model="dataUser.name" class="form-control" >
								</div>
							</div>
							<div class="form-group form-group-lg">
								<label for="usuarioFormDireccion" class="col-sm-3 control-label">Dirección</label>
							</div>
						
						
						
							<div class="form-group form-group-lg">
								<div id="mapUserEdit" class="col-sm-offset-3"></div>
							</div>
	
						
                            <div class="form-group form-group-lg">
                                <div class="col-md-3 col-md-offset-3">
                                    <button id="usuarioSubmit" type="submit" data-loading-text="Guardando..."  class="btn btn-primary btn-lg active">
                                        <span class="glyphicon glyphicon-floppy-disk"></span> GUARDAR</button>    
                                </div>
                            </div>
					</form>
				</div>
			</div>
		</div>		
	</div>
</div>
<jsp:include page="/WEB-INF/jsp/changePassword.jsp" flush="true" />
<script src="<c:url value="/resources/js/editUser.js"/>"></script>
<link href="<c:url value="/resources/css/editUser.css"/>"	rel="stylesheet">

	