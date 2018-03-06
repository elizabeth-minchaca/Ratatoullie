app.controller("changePassword",function($scope){
	$scope.user={};	
	$scope.submitChangePassword = function(){
		var requestUrl = site+"api/user/changePassword";
		$scope.user.oldPassword=CryptoJS.MD5($scope.oldPassword).toString();
		$scope.user.newPassword=CryptoJS.MD5($scope.newPassword).toString();
		$scope.user.newPassword2=CryptoJS.MD5($scope.newPassword2).toString();		
		$.post(requestUrl, $scope.user,$scope)
		.done(function(data, status, headers,$scope) {
			swal({
				title: 'Listo!',
				text: 'La contrase&ntilde;a fue actualizada correctamente.',
				type: 'success',
			}).then(
					function () {
						location.reload();
					}
			)
		})				
		.fail(function(xhr, textStatus, errorThrown) {
			$scope.$apply(function() {
				swal("Error!", "No se ha podido guardar los cambios en el sistema.", "error");
			});
		})
		;		
	};
});