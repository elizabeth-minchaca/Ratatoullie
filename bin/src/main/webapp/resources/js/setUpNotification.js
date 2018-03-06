app.controller("setUpNotification",function($scope,$http){
	$scope.config = {};
	$scope.config.visitor = false;
	$scope.config.commensal = false;
	$scope.config.gourmet = false;
	$scope.blockedRanking = [];
	$http.get(site+"api/user/getMy").then(function (response) {
		$scope.user = response.data;
		$scope.blockedRanking= response.data.blockedRanking;
		for (var i = 0; i < $scope.blockedRanking.length; i++) {
			var ranking = $scope.blockedRanking[i];
			if (ranking == "VISITOR") {
				$scope.config.visitor = true;
			}
			if (ranking == "COMMENSAL") {
				$scope.config.commensal = true;
			}
			if (ranking == "GOURMET") {
				$scope.config.gourmet = true;
			}
		}
	});
	
	
	$scope.submitConfiguration = function(){
		var requestUrl = site+"api/user/setUpNotification";
		$.post(requestUrl, $scope.config, $scope)
		.done(function(data, status, headers,$scope) {
			
			var blockeds = data.blockedRanking;
			var msj = "";
			for (var i = 0; i < blockeds.length; i++) {
				var ranking = blockeds[i];
				msj = msj + ranking + ' ';
			}
			swal({
				title: 'Listo!',
				text: 'Los datos fueron guardados correctamente, se bloqueo a '+msj ,
				type: 'success',
			}).then(
					function () {
						$(location).attr('href', site);
					}
			)

		})				
		.fail(function(xhr, textStatus, errorThrown) {
			swal("Error!", "No se ha podido guardar los cambios en el sistema.", "error");
		})
		;		
	};
});
