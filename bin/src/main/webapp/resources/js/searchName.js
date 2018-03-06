app.controller('searchName', function($scope, $routeParams, $http){
	$scope.name = $routeParams.name;
	if($scope.name){
		$scope.requestUrl=site+"api/restaurant/search?name="+$scope.name;
	}else{
		$scope.requestUrl=site+"api/restaurant/list";	
	}
	$http.get($scope.requestUrl)
	.then(function(response) {
		$scope.restaurants = response.data;
	});
	$scope.search= function() {
		$scope.requestUrl=site+"api/restaurant/search?name="+$scope.name;
		$http.get($scope.requestUrl)
		.then(function(response) {			
				$scope.restaurants = response.data;
		});
	};
});