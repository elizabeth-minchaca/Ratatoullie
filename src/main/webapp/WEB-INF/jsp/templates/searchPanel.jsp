<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="<c:url value="/resources/css/customSP.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/js/searchPanel.js"/>"></script>
<div class="homepage">
	<div class="container ctr">
		<div class="col-md-12">
			<div class="h1">Encontr&aacute; el restaurante perfecto</div>
			<div class="col-md-12">
				<div class="col-xs-8 hld-picker">
					<input type="text" class="form-control picker" tabindex="1"
						placeholder="Nombre del restaurante" autocorrect="off"
						spellcheck="false" id="name">

				</div>
				<div class="col-xs-4 hld-btn">
					<button type="submit" class="btn btn-success search-btn"
						id="searchName">
						<span class="submit_text">Encontr&aacute; restaurantes</span>
					</button>
				</div>
			</div>
		</div>
	</div>
</div>