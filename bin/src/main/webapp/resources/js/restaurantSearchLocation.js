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

		});
	};
//	$http.get(site+"api/user/list").then(function (response) {
//		markersSearch=[];
//		$.each( response.data, function( key, value ) {	
//			var marker = new google.maps.Marker({
//				position: new google.maps.LatLng(value.location.latitude, value.location.longitude),
//				map: mapRS,
//				title: 	value.name,
//			});
//			markersSearch.push(marker);
//					
//		});
//	});

});