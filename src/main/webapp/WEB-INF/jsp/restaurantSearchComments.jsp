<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<c:url value="/resources/css/custom.css"/>" rel="stylesheet">
<nav class="breadcrumb">
  <a class="breadcrumb-item" href="#!/">Home /</a>
  <span class="breadcrumb-item active">Restaurantes - B&uacute;squeda m&aacute;s comentados</span>
</nav> 
<div ng-controller="restaurantSearchComments">
	<div class="container">
		<h1 class="text-center">Retaurantes mas comentados</h1>
		</br> </br> </br>
		<div class="row">
			<form name="searchLocation" ng-submit="search()"
				class="form-horizontal">
				<div class="row">
					<div class="col-sm-5">
						<div class="form-group">
							<label class="control-label col-md-4" for="initial">Fecha
								Inicial:</label>
							<div class="col-sm-8">
								<input class="form-control " type="datetime-local"
									ng-model="initialDate" name="initial" id="initial"
									required="required" />
							</div>
						</div>
					</div>
					<div class="col-sm-5">
						<div class="form-group">
							<label class="control-label col-md-4" for="final">Fecha
								Final:</label>
							<div class="col-sm-8">
								<input class="form-control" type="datetime-local"
									ng-model="finalDate" name="final" id="final"
									required="required" />
							</div>
						</div>
					</div>
					<div class="col-xs-2">
						<button type="submit" class="btn btn-primary ">Buscar</button>
					</div>
				</div>
			</form>
			<div class="container">
				<jsp:include page="/WEB-INF/jsp/templates/restaurantListCore.jsp" flush="true" />
			</div>

		</div>
	</div>
</div>