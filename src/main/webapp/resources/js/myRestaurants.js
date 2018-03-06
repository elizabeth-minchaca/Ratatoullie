app.controller('myRestaurants', function($scope, $http){
	$scope.restaurants=[];
	$scope.requestUrl=site+"api/restaurant/userresponsible/myrestaurants";	
	$http.get($scope.requestUrl)
    .then(function(response) {
        $scope.restaurants = response.data;        
    });
});