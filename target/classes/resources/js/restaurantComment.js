app.controller("restaurantComment",function($scope,$http){
	$scope.comment = "";
	$scope.vote = "";
	$scope.date = "";	
	$scope.submitComment = function(restaurant) {
		var requestUrl = site+"api/comment/commentrestaurant";
		var fd = new FormData();
		fd.append('restaurant', restaurant.name);
		fd.append('text', $scope.comment);
		fd.append('latitude', restaurant.location.latitude);
		fd.append('longitude', restaurant.location.longitude);
		fd.append('vote', $scope.vote);
		fd.append('date', new Date().getTime());
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
			if(response.data.user){
				swal({
					title: 'Listo!',
					text: 'El comentario se realiz&oacute; correctamente.',
					type: 'success',
				}).then(
						function () {
							location.reload();
						})
			}else{
				swal({
					title: 'Error!',
					text: 'Usted no puede comentar este restaurante, por estar demasiado cerca.',
					type: 'error',
				})
				$("#myModal").modal('hide');
			}
		}, function(response) {
			swal({
				title: 'Error!',
				text: 'El comentario no se realiz&oacute; correctamente.',
				type: 'error',
			})
		});
	}
});