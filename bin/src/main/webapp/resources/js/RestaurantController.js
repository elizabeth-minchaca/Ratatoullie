app.controller("RestaurantController",function($scope, $routeParams, $http){
	var markersEditRest=[];
	var idRest = $routeParams.id;
	$scope.rest={};
	$scope.rest.id="";
	$scope.rest.name="";
	$scope.rest.date="";
	$scope.rest.latitude="";
	$scope.rest.longitude="";
	$scope.rest.uploadFile="";
	$http.get(site+"api/restaurant/userresponsible/getrestaurant/"+idRest).then(function (response) {
		var r = response.data;
		$scope.rest.id= r.id;
		$scope.rest.name= r.name;
		$scope.rest.date= new Date(r.openingDate);
		$scope.rest.latitude= r.location.latitude;
		$scope.rest.longitude= r.location.longitude;
		$scope.rest.location=r.location;
		$scope.rest.image= r.image;		
		$scope.rest.category= r.category.name;
		$scope.rest.menus= r.menus;
		if(document.getElementById("mapEditRestaurant") !== null){
			var mapRestaurantEdit = new google.maps.Map(document.getElementById('mapEditRestaurant'), {
				center: {lat: $scope.rest.latitude, lng: $scope.rest.longitude},
				zoom: 14
			});
			placeMarker(new google.maps.LatLng($scope.rest.latitude, $scope.rest.longitude), mapRestaurantEdit);
			mapRestaurantEdit.addListener('click', function(e) {
				placeMarker(e.latLng, mapRestaurantEdit);
			});
			function placeMarker(position, map) {
				setMapOnAll(null);
				markersEditRest=[];
				var marker = new google.maps.Marker({
					position: position,
					map: map
				});
				markersEditRest.push(marker);
			}

			function setMapOnAll(map) {
				for (var i = 0; i < markersEditRest.length; i++) {
					markersEditRest[i].setMap(map);
				}
			}
		}
	});	

	$scope.submitEditRestaurant = function(){		
		var requestUrl = site+"api/restaurant/userresponsible/edit";

		$scope.rest.openingDate = new Date($scope.rest.date).getTime();

		var fd = new FormData();
		fd.append('idRest', $scope.rest.id);
		fd.append('restaurant', $scope.rest.name);
		fd.append('date', $scope.rest.openingDate);
		fd.append('latitude', markersEditRest[0].position.lat());
		fd.append('longitude', markersEditRest[0].position.lng());
		fd.append("uploadFile",uploadFile.files[0]);
		$http.post(requestUrl, fd,
				{
			transformRequest: function(data, headersGetterFunction) {
				return data;
			},
			headers: {'Content-Type': undefined }
				}		
		)
		.then(function(response) {
			var content = response.data;
			swal({
				title: 'Listo!',
				text: 'El Restaurant '+content.name+' ha sido modificado',
				type: 'success',
			}).then(
					function () {
						$(location).attr('href', site+"#!/viewRestaurant/"+content.id);
					}
			)

		}, function(response) {
			swal("Error!", "No se ha podido guardar los cambios en el sistema.", "error");
		});
	};    


});