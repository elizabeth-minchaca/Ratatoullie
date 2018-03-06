app.controller("login",function($scope){
	$scope.loginFail=false;
	$scope.loginUser={};
	$scope.loginUser.email="";	
	$scope.login = function(){
		$scope.loginUser.password=CryptoJS.MD5($scope.password).toString();
		var requestUrl = site+"api/user/login";
		$.post(requestUrl, $scope.loginUser)
		.done(function(data, status, headers) {		 
			$(location).attr('href', site);	
		})				
		.fail(function(xhr, textStatus, errorThrown) {
			$scope.$apply(function() {
				$scope.loginFail=true;
			});
		});		
	};	
});