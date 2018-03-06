app.controller("restaurantSearchComments",function($scope, $http){

	$scope.dataSearch={};
	$scope.restaurants=null;
	$scope.search = function(){
		$scope.dataSearch.initialDate=new Date($scope.initialDate).getTime();
		$scope.dataSearch.finalDate=new Date($scope.finalDate).getTime();
		$scope.requestUrl=site+"api/restaurant/topComments?initialDate="+$scope.dataSearch.initialDate+"&finalDate="+$scope.dataSearch.finalDate+"&quantity=50";
		$http.get($scope.requestUrl)
		.then(function successCallback(response) {
			$scope.restaurants = response.data;
		}, function errorCallback(response) {
			$scope.restaurants=[];
		});
	};
});