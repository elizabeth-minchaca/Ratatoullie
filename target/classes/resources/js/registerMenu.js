app.controller("registerMenu",function($scope, $routeParams, $http){
	var idRest = $routeParams.idr;
	$scope.id = $routeParams.idr;
	console.log($scope.idRest);
	$scope.menu={};
	$scope.menu.name="";
	$scope.menu.description="";
	$scope.menu.uploadFileMenu="";
	$scope.submitCreateMenu = function(){		
		var requestUrl = site+"api/restaurant/userresponsible/addmenu";
		var fd = new FormData();
		fd.append('idRest',idRest);
		fd.append('name', $scope.menu.name);
		fd.append('description', $scope.menu.description);
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
	        var idMenu = content.id;
			swal({
				  title: 'Listo!',
				  text: 'El Menu '+content.text+' ha sido creado',
				  type: 'success',
				}).then(
				  function () {
	                  $(location).attr('href', site+"#!/viewMenu/"+idRest+"/"+idMenu);
				  }
				)

	    }, function(response) {
            swal("Error!", "No se ha podido crear el menu.", "error");
	    });
	};    
});