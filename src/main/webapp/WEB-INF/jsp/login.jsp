<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 		prefix="c" %>
<div class="modal fade" tabindex="-1" role="dialog" id=login ng-controller="login">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Iniciar Sesión</h4>
      </div>
      <div class="modal-body">
      		<form id="loginForm" name="loginForm" ng-submit="login()" class="form-horizontal">
      		<p ng-show="loginFail==true" class="text-danger text-center">
  				<strong>Usuario o contraseña invalida</strong>
			</p>   	
        	<div class="form-group">
				<label class="col-sm-4 col-md-4 col-lg-4 control-label" for="userEmail">Usuario</label>
				<div class="col-sm-8 col-md-6 col-lg-6">
					<input id="mail" name="mail" class="form-control" ng-model="loginUser.email" type="text" placeholder="Ingrese su e-mail..." required="required"/>
				</div>
			</div>			
	 		<div class="form-group">
				<label class="col-sm-4 col-md-4 col-lg-4 control-label" for="userPassword">Contraseña</label>
				<div class="col-sm-8 col-md-6 col-lg-6">
					<input id="password" name="password" class="form-control" ng-model="password" type="password" placeholder="Ingrese su password..." required="required"/>	
				</div>
			</div>
			
	  		
		      <div class="modal-footer">
		        <a href="/${site}/regist" class="pull-left">Registrarse</a>
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		        <button type="submit" class="btn btn-primary" value="Submit">Iniciar</button>
		     </div>
        </form>        
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</div>