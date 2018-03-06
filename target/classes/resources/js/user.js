app.controller("registerUser",function($scope){
	$scope.user={};
	$scope.user.name="";
	$scope.user.lastName="";
	$scope.user.email="";	
	$scope.submitUser = function(){
		$scope.user.password=CryptoJS.MD5($scope.password).toString();
		$scope.user.password2=CryptoJS.MD5($scope.password2).toString();
		$scope.user.latitude=markersUser[0].position.lat();
		$scope.user.longitude=markersUser[0].position.lng();
		var requestUrl = site+"api/user/registerUser";
		$.post(requestUrl, $scope.user,$scope)
		.done(function(data, status, headers,$scope) {
			swal({
				title: 'Felicidades!',
				text: 'Usuario '+data.mail+' has sido registrado',
				type: 'success',
			}).then(
					function () {
						$(location).attr('href', site);
					}
			)
		})				
		.fail(function(xhr, textStatus, errorThrown) {
			$scope.$apply(function() {
				swal({
					title: 'Error!',
					text: 'No has sido registrado',
					type: 'error',
				})
			});
		});
	};
});