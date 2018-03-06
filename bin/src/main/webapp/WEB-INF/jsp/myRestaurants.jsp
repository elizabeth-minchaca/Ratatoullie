<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<link
	href="<c:url value="/resources/js/bootstrap-table/bootstrap-table.min.css"/>"
	rel="stylesheet">
<script
	src="<c:url value="/resources/js/bootstrap-table/bootstrap-table.min.js"/>"></script>
<script
	src="<c:url value="/resources/js/bootstrap-table/bootstrap-table-locale-all.min.js"/>"></script>

 <div class="container" ng-controller="myRestaurants">
     <div class="row" >
            <div id="restauranContainer" class="col-md-12" >
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"></h3>
                    </div>
                    <div class="panel-body">
                        <div class="page-header">
                            <h1><span class="glyphicon glyphicon-th-list"></span>  Mis Restaurantes <small>- listado</small></h1>
                        </div>
						<table class="table table-striped table-bordered table-responsive text-center" data-toggle="table" id="table"
							data-url="{{requestUrl}}"
							data-show-columns="true" data-search="true" data-show-refresh="true"
							data-show-toggle="true" data-toolbar="#toolbar" data-pagination="true"
							data-page-size="10" data-page-list="[10, 25, 50, 100, All]"
							data-locale="es-ES" data-click-to-select="true" data-sort-name="name">
							<thead>
								<tr>
					 				<th class="text-center" data-field="check" data-checkbox="true"></th>
					 				<th class="text-center" data-field="name" data-sortable="true">Nombre</th>
									<th class="text-center" data-field="owner.name" data-sortable="true">Dueño</th>
									<th class="text-center" data-field="category.name" data-sortable="true">Categoria</th>
									<th class="text-center" data-field="location" data-formatter="locationFormatter">Ubicaci&oacute;n</th>
									<th class="text-center" data-field="openingDate" data-formatter="dateFormatter">Fecha de Apertura</th>
									<c:choose>
										<c:when test="${user.enable}">
											<th class="text-center" data-field="id" data-formatter="restaurantFormatter">Acci&oacute;n</th>										
										</c:when>
										<c:otherwise>
											<th class="text-center" data-field="id" data-formatter="restaurantDisableFormatter">Acci&oacute;n</th>																				
										</c:otherwise>
									</c:choose>	
									
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
 