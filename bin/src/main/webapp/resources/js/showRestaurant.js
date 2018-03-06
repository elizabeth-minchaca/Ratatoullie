app.controller("showrestaurant",function($scope, $routeParams, $http) {
	var idRes = $routeParams.idRes;
	$scope.restaurant=[];
	$scope.vote="";
	$http.get(site+"api/restaurant/showRestaurant/"+idRes)
	.then(function (response) {
		$scope.restaurant = response.data;
		var c = $scope.restaurant.comments
		for (var i = 0; i < c.length; i++) {
			switch(c[i].vote) {
			    case "POSITIVE":
			    	$scope.restaurant.comments[i].vote = "POSITIVO";
			        break;
			    case "NEUTRAL":
			    	$scope.restaurant.comments[i].vote = "NEUTRO";
			        break;
			    case "NEGATIVE":
			    	$scope.restaurant.comments[i].vote = "NEGATIVO";
			}
		}
//		if(document.getElementById("mapShowRestaurant") !== null){
		
			var mapShowRestaurant = new google.maps.Map(document.getElementById('mapShowRestaurant'), {
				center: {lat: $scope.restaurant.location.latitude, lng: $scope.restaurant.location.longitude},
				zoom: 14
			});
			var marker = new google.maps.Marker({
				position: new google.maps.LatLng($scope.restaurant.location.latitude, $scope.restaurant.location.longitude),
				map: mapShowRestaurant
			});
//		}
	});
});
