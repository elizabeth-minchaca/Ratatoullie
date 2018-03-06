app.controller('restaurantList', function($scope, $http, $location){
	$scope.restaurants=[];
	$scope.requestUrl=site+"api/restaurant/list";	
	$http.get($scope.requestUrl)
	.then(function(response) {
		$scope.restaurants = response.data;
		$.each( response.data, function( key, value ) {
			$http.get('http://maps.googleapis.com/maps/api/geocode/json?latlng='+response.data[key].location.latitude+','+response.data[key].location.longitude+'&sensor=true')
			.then(function successCallback(response) {
				if(response.data.status=="ZERO_RESULTS"|response.data.status=="OVER_QUERY_LIMIT"){
					$scope.restaurants[key].location.name=String(locationFormatter($scope.restaurants[key].location)).replace(/&#176;/g,String.fromCharCode(176));
				}else{    						
					$scope.restaurants[key].location.name=response.data.results[0].formatted_address;    						
				}            	
			}, function errorCallback(response) {
				$scope.restaurants[key].location.name=String(locationFormatter($scope.restaurants[key].location)).replace(/&#176;/g,String.fromCharCode(176));
			});        	
		});
	});
});
