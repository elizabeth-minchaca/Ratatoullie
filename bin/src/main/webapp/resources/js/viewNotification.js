app.controller('viewNotification', function($scope, $routeParams, $http){
	
	$scope.id = $routeParams.id;
	$scope.notification = {};
	$scope.requestUrl = "";

	$http.get(site+"api/user/markAsSeen/"+$scope.id)
	.then(function (response) {
		$scope.notification = response.data;
	});	
	$scope.reloadRoute = function() {
        $(location).attr('href', site+"#!/viewNotifications");
		location.reload();
	};	
		
	$scope.redirectToView = function(){
		var name = "";
		var id = null;
		id = $scope.notification.comment.commenting.id;
		name = $scope.notification.comment.commenting.name;
		if (name == "Menu") {
			$scope.getMenu(id);
		} else {
			$scope.getRestaurant(id);
		}
	};
	
	$scope.getMenu = function(id){
		var requestUrl = site+"api/restaurant/getMenu/"+id;
		$http.get(requestUrl)
		.then(function (response) {
			var menu = {};
			menu = response.data;
			var idRest = null;
			idRest = menu.restaurant.id;
            $(location).attr('href', site+"#!/showMenu/"+idRest+"/"+id);

		});		
	};
	
	$scope.getRestaurant = function(id){
		var requestUrl = site+"api/restaurant/showRestaurant/"+id;
		$http.get(requestUrl)
		.then(function (response) {
			var restaurant = {};
			restaurant = response.data;
            $(location).attr('href', site+"#!/showRestaurant/"+restaurant.id);

		});		
	};

});