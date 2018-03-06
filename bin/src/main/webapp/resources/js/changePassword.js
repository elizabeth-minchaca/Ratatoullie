app.controller("changePassword",function($scope){
	$scope.user={};
	$scope.user.oldPassword="";
	$scope.user.newPassword="";
	$scope.user.newPassword2="";

	$scope.submitChangePassword = function(){

		var requestUrl = site+"api/user/changePassword";
		$.post(requestUrl, $scope.user,$scope)
				.done(function(data, status, headers,$scope) {
                  //  swal("Listo!", "La contraseña fue actualizada correctamente.", "success");
					swal({
						  title: 'Listo!',
						  text: 'La contraseña fue actualizada correctamente.',
						  type: 'success',
						}).then(
						  function () {
			                    //$(location).attr('href', "http://localhost:8080/Ratatoullie/index#!/");
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