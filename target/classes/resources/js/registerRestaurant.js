app.controller("registerRestaurant",function($scope,$http){
	$scope.rest={};
	$scope.rest.restaurant="";
	$scope.rest.date="";
	$scope.rest.uploadFile="";
	$scope.rest.menuName="";
	$scope.rest.menuDescription="";
	$scope.rest.uploadFileMenu="";
	$scope.submitCreateRestaurant = function(){	
		var requestUrl = site+"api/restaurant/userresponsible/create";
		var fd = new FormData();
		fd.append('restaurant', $scope.rest.restaurant);
		fd.append('date', new Date($scope.rest.date).getTime());
		fd.append('latitude', markersRegistRestaurant[0].position.lat());
		fd.append('longitude', markersRegistRestaurant[0].position.lng());
		fd.append("uploadFile",uploadFile.files[0]);
		fd.append('menuName', $scope.rest.menuName);
		fd.append('description', $scope.rest.menuDescription);
		fd.append("uploadFileMenu",uploadFileMenu.files[0]);
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
				text: 'El Restaurant '+content.name+' ha sido registrado',
				type: 'success',
			}).then(
					function () {
						$(location).attr('href', site+"#!/viewRestaurant/"+content.id);
					})
		}, function(response) {
			swal({
				title: 'Error!',
				text: 'El restaurant no ha sido registrado',
				type: 'error',
			})
		});
	};        
});