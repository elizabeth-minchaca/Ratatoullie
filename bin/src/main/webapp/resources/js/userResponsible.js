app.controller("registerUserResponsible", function($scope, $http){
	$scope.user={};
	$scope.user.name="";
	$scope.user.lastName="";
	$scope.user.latitude="";
	$scope.user.longitude="";
	$scope.user.email="";
	$scope.user.password="";
	$scope.user.password2="";
	$scope.user.restName="";
	$scope.user.restDate="";
	$scope.user.restLatitude="";
	$scope.user.restLongitude="";
	$scope.user.uploadFile="";
	$scope.user.menuName="";
	$scope.user.menuDescription="";
	$scope.user.uploadFileMenu="";

	
	$scope.submitResponsible = function(){		
		var requestUrl = site+"api/user/registerResponsible";
		var fd = new FormData();
		fd.append('name', $scope.user.name);
		fd.append('lastName', $scope.user.lastName);
		fd.append('latitude', markersResponsible[0].position.lat());
		fd.append('longitude', markersResponsible[0].position.lng());
		fd.append('email', $scope.user.email);
		fd.append('password', $scope.user.password);
        fd.append("password2",$scope.user.password2);
		fd.append('restName', $scope.user.restName);
		fd.append('restDate', new Date($scope.user.restDate).getTime());
		fd.append('restLatitude', markersRestaurant[0].position.lat());
		fd.append('restLongitude', markersRestaurant[0].position.lng());
		fd.append('uploadFile', uploadFile.files[0]);
		fd.append('menuName', $scope.user.menuName);
		fd.append('description', $scope.user.menuDescription);
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
				  title: 'Felicidades!',
				  text: 'Usuario responsable '+content.mail+' has sido registrado',
				  type: 'success',
				}).then(
				  function () {
	                  $(location).attr('href', "http://localhost:8080/Ratatoullie/#!/");
				  }
				)

	    }, function(errResponse) {
			swal({
			  title: 'Error!',
			  text: 'No has sido registrado',
			  type: 'error',
			})

	    });
	};

});