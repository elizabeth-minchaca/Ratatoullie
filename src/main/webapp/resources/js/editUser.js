app.controller("editUser",function($scope,$http,$location){
	$scope.dataUser={};
	$scope.dataUser.name="";
	$scope.dataUser.lastName="";

	var markersEditUser=[];
	$http.get(site+"api/user/getMy").then(function (response) {
		$scope.dataUser= response.data;
		$scope.dataUser.latitude=response.data.location.latitude;
		$scope.dataUser.longitude=response.data.location.longitude;
		if(document.getElementById("mapUserEdit") !== null){
			var mapUserEdit = new google.maps.Map(document.getElementById('mapUserEdit'), {
				center: {lat: response.data.location.latitude, lng: response.data.location.longitude},
				zoom: 14
			});
			placeMarker(new google.maps.LatLng(response.data.location.latitude, response.data.location.longitude), mapUserEdit);
			mapUserEdit.addListener('click', function(e) {
				placeMarker(e.latLng, mapUserEdit);
			});
			function placeMarker(position, map) {
				setMapOnAll(null);
				markersEditUser=[];
				var marker = new google.maps.Marker({
					position: position,
					map: map
				});
				markersEditUser.push(marker);
			}

			function setMapOnAll(map) {
				for (var i = 0; i < markersEditUser.length; i++) {
					markersEditUser[i].setMap(map);
				}
			}
		}
	});
	$scope.submitEditUser = function(){

		var requestUrl = site+"api/user/editUser";
		$scope.dataUser.latitude=markersEditUser[0].position.lat();
		$scope.dataUser.longitude=markersEditUser[0].position.lng();
		$.post(requestUrl, $scope.dataUser,$scope)
		.done(function(data, status, headers,$scope) {
			swal({
				title: 'Listo!',
				text: 'Los datos fueron actualizados correctamente.',
				type: 'success',
			}).then(
					function () {
						$(location).attr('href', site+"#!/showPerfil/");
					}
			)

		})				
		.fail(function(xhr, textStatus, errorThrown) {

			swal("Error!", "No se ha podido guardar los cambios en el sistema.", "error");
			$http.get(site+"api/user/getMy").then(function (response) {
				$scope.dataUser= response.data;
				$scope.dataUser.latitude=response.data.location.latitude;
				$scope.dataUser.longitude=response.data.location.longitude;
			});
		})
		;		
	};

	$scope.disableUser = function(){
		var requestUrl = site+"api/user/enableDisableUser";
		var _result;
		swal({
			title:'Deshabilitar?',
			text: "Esta por deshabilitar su cuenta!",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#3085d6",
			cancelButtonColor: '#d33',
			confirmButtonText: "S&iacute;, deshabilitar!",
			cancelButtonText: "No, cancelar!",
			preConfirm: function () {
				return new Promise(function (resolve, reject) {
					$.post(requestUrl, {state: false}).done(function (data) {
						_result = data;
						$scope.dataUser = data;
						resolve();
					});
				});
			},
			allowOutsideClick: false
		}).then(function () {

			if (_result != null) {
				swal({
					type: 'success',
					title: 'Felicitaciones!',
					html: 'Usuario '+_result.mail+' se ha deshabilitado su cuenta.'
				}).then(function () {
//					$(location).attr('href', site+"#!/showPerfil/");
					location.reload();		
				}); 
			} else {
				swal({
					type: 'error',
					title: 'Error!',
					html: 'No se han guardado los cambios en el sistema.'
				});
			}


		}, function (dismiss) {
			if (dismiss === 'cancel') {
				swal({
					type: 'info',
					title: 'Cancelado!',
					html: 'Se cancel&oacute; la deshabilitaci&oacute;n de su cuenta.',
				}).then(function () {
					$(location).attr('href', site+"#!/showPerfil/");
				}); 
			}
		});

	};

	$scope.enableUser = function(){
		var requestUrl = site+"api/user/enableDisableUser";
		var _result;
		swal({
			title: "Habilitar?",
			text: "Esta por habilitar su cuenta!",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#3085d6",
			cancelButtonColor: '#d33',
			confirmButtonText: "S&iacute;, habilitar!",
			cancelButtonText: "No, cancelar!",
			preConfirm: function () {
				return new Promise(function (resolve, reject) {
					$.post(requestUrl, {state: true}).done(function (data) {
						_result = data;
						$scope.dataUser = data;
						resolve();
					});
				});
			},
			allowOutsideClick: false
		}).then(function () {

			if (_result != null) {
				swal({
					type: 'success',
					title: 'Felicitaciones!',
					html: 'Usuario '+_result.mail+' se ha habilitado su cuenta.'
				}).then(function () {
//					$(location).attr('href', site+"#!/showPerfil/");
					location.reload();		
				}); 
			} else {
				swal({
					type: 'error',
					title: 'Error!',
					html: 'No se han guardado los cambios en el sistema.'
				});
			}


		}, function (dismiss) {
			if (dismiss === 'cancel') {
				swal({
					type: 'info',
					title: 'Cancelado!',
					html: 'Se cancel&oacute; la habilitaci&oacute;n de su cuenta.',
				}).then(function () {
					$(location).attr('href', site+"#!/showPerfil/");
				}); 
			}
		});
	};
});	