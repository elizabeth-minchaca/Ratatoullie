app
.controller('homeUser', function($scope, $http){
	$scope.user={};
	$scope.quantityRecommendations=null;
	$scope.requestUrl=site+"api/user/getMy";	
	$http.get($scope.requestUrl)
    .then(function(response) {
        $scope.user = response.data;
    	$scope.quantityRecommendations=response.data.recommendations.length;

    });
	$scope.userRankUrl=site+"api/user/quantityUsersRank";
	$scope.topUsersUrl=site+"api/user/topUsersComments?quantity=10";
	
	
});