app.controller("login",function($scope){
	$scope.loginFail=false;
	$scope.loginUser={};
	$scope.loginUser.email="";
	$scope.loginUser.password="";
	$scope.login = function(){
		var requestUrl = site+"api/user/login";
		$.post(requestUrl, $scope.loginUser)
				.done(function(data, status, headers) {		 
	                $(location).attr('href', site);	
				})				
				.fail(function(xhr, textStatus, errorThrown) {
					$scope.$apply(function() {
						$scope.loginFail=true;
					});
				})
				;		
	};	
});