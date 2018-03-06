app.controller("menuComment",function($scope,$http){
	$scope.comment = "";
	$scope.vote = "";
	$scope.date = "";
	
	$scope.submitComment = function(menu) {
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
			swal({
				  title: 'Listo!',
				  text: 'El comentario se realiz&oacute; correctamente.',
				  type: 'success',
				}).then(
				  function () {
					  location.reload();
				  })

	    }, function(response) {
			swal({
			  title: 'Error!',
			  text: 'El comentario no se realiz&oacute; correctamente.',
			  type: 'error',
			})

	    });
	}
});