app.controller("menuComment",function($scope,$http){
	$scope.comment = "";
	$scope.vote = "";
	$scope.date = "";
	$scope.submitComment = function(menu) {
		if ( menu.restaurant.idCurrentMenu != menu.id) {
			swal({
				title: 'Error!',
				text: 'No se puede comentar este men&uacute;, no esta activo.',
				type: 'error',
			}).then(
					function () {
						$("#modal").modal('hide');
					})
		} else {
			var requestUrl = site+"api/comment/commentmenu";
			var fd = new FormData();
			fd.append('restaurant', menu.restaurant.name);
			fd.append('text', $scope.comment);
			fd.append('latitude', menu.restaurant.location.latitude);
			fd.append('longitude', menu.restaurant.location.longitude);
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
						text: 'Usted no puede comentar este menu, por estar demasiado cerca.',
						type: 'error',
					})
					$("#modal").modal('hide');
				}
			}, function(response) {
				swal({
					title: 'Error!',
					text: 'El comentario no se realiz&oacute; correctamente.',
					type: 'error',
				})

			});

		}
	}
});