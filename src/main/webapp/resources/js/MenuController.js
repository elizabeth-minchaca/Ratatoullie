app.controller("MenuController",function($scope, $routeParams, $http){	
	var idMenu = $routeParams.idm;
	var idRest = $routeParams.idr;
	$scope.menu={};
	$scope.menu.idMenu="";
	$scope.menu.idRest="";
	$scope.menu.name="";
	$scope.menu.description="";
	$scope.menu.uploadFileMenu="";
	$http.get(site+"api/restaurant/userresponsible/getmenu/"+idRest+"/"+idMenu).then(function (response) {
		var m = response.data;
		$scope.menu.idRest= idRest;
		$scope.menu.idMenu= idMenu;
		$scope.menu.nameRest= m.restaurant.name;
		$scope.menu.name= m.text;
		$scope.menu.startDate= m.startDate;
		$scope.menu.endDate= m.endDate;
		$scope.menu.image= m.image;
		$scope.menu.description= m.description;		
	});		
	$scope.submitEditMenu = function(){		
		var requestUrl = site+"api/restaurant/userresponsible/editmenu";
		var fd = new FormData();
		fd.append('idRest', $scope.menu.idRest);
		fd.append('idMenu', $scope.menu.idMenu);
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
				text: 'El Menu '+content.text+' ha sido modificado',
				type: 'success',
			}).then(
					function () {
						$(location).attr('href', site+"#!/viewMenu/"+idRest+"/"+idMenu);
					}
			)
		}, function(response) {
			swal("Error!", "No se ha podido guardar los cambios en el sistema.", "error");
		});
	};     
});