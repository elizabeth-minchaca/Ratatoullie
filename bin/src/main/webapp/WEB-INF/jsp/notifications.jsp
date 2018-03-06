<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<link
	href="<c:url value="/resources/js/bootstrap-table/bootstrap-table.min.css"/>"
	rel="stylesheet">
<script
	src="<c:url value="/resources/js/bootstrap-table/bootstrap-table.min.js"/>"></script>
<script
	src="<c:url value="/resources/js/bootstrap-table/bootstrap-table-locale-all.min.js"/>"></script>
<script src="<c:url value="/resources/js/notificationsResponsible.js"/>"></script>

 <div class="container" ng-controller="notificationsResponsible">
     <div class="row" >
            <div class="col-md-12" >
            
                <div class="panel panel-danger">
                    <div class="panel-heading">
                            <h4 class="text-center"><strong>NOTIFICACIONES NO VISTAS</strong></h4>
                    </div>                
                    <div class="panel-body">
						<table id="tableNotSeen" class="table table-striped table-bordered table-responsive text-center"
						 	data-pagination="true"
							data-page-size="6" data-page-list="[10, 25, 50, 100, All]"
							data-locale="es-ES" data-sort-name="name">
							<thead>
								<tr>
					 				<th class="text-center" data-field="comment.user.mail" data-sortable="true">De</th>
					 				<th class="text-center" data-field="comment.commenting.name" data-sortable="true">Men&uacute;/Restaurant</th>					 				
					 				<th class="text-center" data-field="comment.vote" data-sortable="true">Voto</th>					 													
					 				<th class="text-center" data-field="comment.commenting.name" data-sortable="true">Men&uacute;/Restaurant</th>					 				
									<th class="text-center" data-field="date" data-formatter="dateFormatter">Fecha de recepci&oacute;n</th>
									<th class="text-center" data-field="id" data-formatter="notificationFormatter">Acci&oacute;n</th>										
								</tr>
							</thead>
						</table>
                    </div>
                    <div class="panel-footer">
                    </div>
                </div>
            </div>            
        </div>
        
     <div class="row" >
            <div class="col-md-12" >
            
                <div class="panel panel-success">
                    <div class="panel-heading">
                            <h4 class="text-center"><strong>NOTIFICACIONES VISTAS</strong></h4>
                    </div>                
                    <div class="panel-body">
						<table id="tableSeen" class="table table-striped table-bordered table-responsive text-center"
						 	data-pagination="true"
							data-page-size="6" data-page-list="[10, 25, 50, 100, All]"
							data-locale="es-ES" data-sort-name="name">
							<thead>
								<tr>
					 				<th class="text-center" data-field="comment.user.name" data-sortable="true">De</th>
					 				<th class="text-center" data-field="comment.commenting.name" data-sortable="true">Men&uacute;/Restaurant</th>					 				
					 				<th class="text-center" data-field="comment.vote" data-sortable="true">Voto</th>					 																				
									<th class="text-center" data-field="date" data-formatter="dateFormatter">Fecha de recepci&oacute;n</th>
									<th class="text-center" data-field="id" data-formatter="notificationFormatter">Acci&oacute;n</th>										
								</tr>
							</thead>
						</table>
                    </div>
                    <div class="panel-footer">
                    </div>
                </div>
            </div>            
        </div>
        
 </div>
 