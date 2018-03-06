app.controller("restaurantSearchLocation",function($scope, $http){
	var markersSearch=[];
	$scope.dataSearch={};
	$scope.dataSearch.distance=1000;
	$scope.restaurants=null;
	$scope.search = function(){
		$scope.dataSearch.latitude=markersRS.position.lat();
		$scope.dataSearch.longitude=markersRS.position.lng();
		$scope.requestUrl=site+"api/restaurant/searchDistance?latitude="+$scope.dataSearch.latitude+"&longitude="+$scope.dataSearch.longitude+"&distance="+$scope.dataSearch.distance;
		$http.get($scope.requestUrl)
		.then(function(response) {
			$scope.restaurants = response.data;
			for (var i = 0; i < markersSearch.length; i++) {
				markersSearch[i].setMap(null);
			}
			markersSearch=[];
			$.each( response.data, function( key, value ) {				
				var marker = new google.maps.Marker({
					position: new google.maps.LatLng(value.location.latitude, value.location.longitude),
					map: mapRS,
					icon:{
						scaledSize: new google.maps.Size(50,50),
						origin: new google.maps.Point(0,0),
						url: 'data:image/png;base64,'+value.image,
						anchor: new google.maps.Point(16,16)
					},
					animation: google.maps.Animation.DROP,
					title: 	value.name,
				});
				marker.addListener('click', function() {
					$(location).attr('href', site+"#!/showRestaurant/"+value.id); 
				});
				markersSearch.push(marker);

			});
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
	};
});