var site="http://"+window.location.host+"/Ratatoullie/";
var app = angular.module('ratatoullie', ['ngRoute']);
app.config(function($routeProvider) {	
	$routeProvider
	.when("/restaurant/list", {
		templateUrl:site+"core/restaurant/list",
		controller: 'restaurantList'
	})
	.when("/restaurant/searchLocation", {templateUrl:site+"/core/restaurant/searchLocation"})
	.when("/restaurant/searchComments", {templateUrl:site+"/core/restaurant/searchComments"})

	.when("/register", {templateUrl:site+"core/register"})
	.when("/registerResponsible", {templateUrl:site+"core/registerResponsible"})
	.when("/myRestaurants", {
		templateUrl:site+"core/userresponsible/myrestaurants",
		controller: 'myRestaurants'
	})		
	.when("/registerRestaurant", {templateUrl:site+"/core/userresponsible/registerRestaurant"})
	.when('/viewRestaurant/:id', {
		templateUrl: 'core/userresponsible/viewRestaurant',
		controllerUrl:"RestaurantController"
	})	
	.when('/editRestaurant/:id', {
		templateUrl: 'core/userresponsible/editRestaurant',
		controller : "RestaurantController",
	})
	.when("/registerMenu/:idr", {
		templateUrl:'core/userresponsible/registerMenu',
		controller: 'registerMenu'
	})
	.when('/viewMenu/:idr/:idm', {
		templateUrl: 'core/userresponsible/viewMenu',
		controllerUrl:"MenuController"
	})	
	.when('/editMenu/:idr/:idm', {
		templateUrl: 'core/userresponsible/editMenu',
		controllerUrl:"MenuController"
	})							
	.when("/showPerfil", {templateUrl:site+"core/user/showPerfil"})
	.when("/editUser", {templateUrl:site+"core/user/editUser"})
	.when("/showRestaurant/:idRes", {
		templateUrl: "core/restaurant/showRestaurant",
		controller: 'showrestaurant'
	})
	.when("/showMenu/:idRe/:idMe", {
		templateUrl: "	core/restaurant/showMenu",
		controller: 'showmenu'
	})
	.when("/restaurant/searchName", {
		templateUrl: "core/restaurant/search",
		controller: 'searchName'
	})
	.when("/restaurant/searchName/:name", {
		templateUrl: "core/restaurant/search",
		controller: 'searchName'
	})		
	.when("/friends", {templateUrl:site+"core/user/friends"})
	.when("/viewNotifications", {templateUrl:site+"core/user/notifications"})	
	.when("/viewNotification/:id", {templateUrl:site+"core/user/viewNotification"})		
	.when("/setUpNotification", {templateUrl:site+"core/user/setUpNotification"})			
	.when("/", {templateUrl:site+"core/index"})
	.otherwise({templateUrl:site+"core/index"});
});	    		
app.filter('toCoordinates', function() {
	return function(location) {
		var resultLocation;
		if(location){
			$.ajax({
				url: 'http://maps.googleapis.com/maps/api/geocode/json?latlng='+location.latitude+','+location.longitude+'&sensor=true',
				async: false,
				success: function(result,status,xhr) {
					if(result.status=="ZERO_RESULTS"|result.status=="OVER_QUERY_LIMIT"){
						resultLocation=String(locationFormatter(location)).replace(/&#176;/g,String.fromCharCode(176));
					}else{
						resultLocation=result.results[0].formatted_address;
					}
				},
				error: function(xhr,status,error){
					resultLocation=String(locationFormatter(location)).replace(/&#176;/g,String.fromCharCode(176));
				}
			});
		}
		return resultLocation;
	};
});
app.controller("changePassword",function($scope){
	$scope.user={};	
	$scope.submitChangePassword = function(){
		var requestUrl = site+"api/user/changePassword";
		$scope.user.oldPassword=CryptoJS.MD5($scope.oldPassword).toString();
		$scope.user.newPassword=CryptoJS.MD5($scope.newPassword).toString();
		$scope.user.newPassword2=CryptoJS.MD5($scope.newPassword2).toString();		
		$.post(requestUrl, $scope.user,$scope)
		.done(function(data, status, headers,$scope) {
			swal({
				title: 'Listo!',
				text: 'La contrase&ntilde;a fue actualizada correctamente.',
				type: 'success',
			}).then(
					function () {
						location.reload();
					}
			)
		})				
		.fail(function(xhr, textStatus, errorThrown) {
			$scope.$apply(function() {
				swal("Error!", "No se ha podido guardar los cambios en el sistema.", "error");
			});
		})
		;		
	};
});
app.controller("friends",function($scope){
	$scope.friend={};
	$scope.user= {};
	$scope.addFollow = function(){		
		if ($scope.user.mail === $scope.friend.mail) {
			$scope.friend.mail = "";
			swal("Error!", "No puede seguirse a s&iacute; mismo", "error");
		} else {
			var requestUrl = site+"api/user/addFollow";
			$.post(requestUrl, $scope.friend)
			.done(function(data, status, headers) {
				$('#tableFollowings').bootstrapTable('load',{
					data: data.followings
				});
				swal(
						'Felicidades!',
						'Estas siguiendo a '+$scope.friend.mail,
						'success'
				)
			})				
			.fail(function(xhr, textStatus, errorThrown) {
				swal({
					title: 'Error!',
					text: 'No se puede seguir a '+ $scope.friend.mail,
					type: 'error',
				})
			});

		}
	};
	$scope.removeFollow = function(){		
		var requestUrl = site+"api/user/stopFollow";
		var item=$('#tableFollowings').bootstrapTable('getSelections')[0];
		swal({
			title: 'Eliminar',
			text: "Deseas eliminar a "+item.mail,
			type: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Eliminar',
			cancelButtonText: 'Cancelar'
		}).then(function () {
			$.post(requestUrl, {mail:item.mail})
			.done(function(data, status, headers) {
				$('#tableFollowings').bootstrapTable('load',{
					data: data.followings
				});
				swal(
						'Eliminado!',
						'Has dejado de seguir a '+item.mail,
						'success'
				)
			})				
			.fail(function(xhr, textStatus, errorThrown) {
				swal({
					title: 'Error!',
					text: 'No se puede dejar de seguir a '+ item.mail,
					type: 'error',
				})
			});
		})

	};
	$.get( site+"api/user/getMy")
	.done(function( data ) {
		$scope.user= data;
		$('#tableFollowings').bootstrapTable({
			data: data.followings
		});
		$('#tableFollowers').bootstrapTable({
			data: data.followers
		})		
	});
});
app.controller('homeUser', function($scope, $http){
	$scope.user={};
	$scope.quantityRecommendations=null;
	$scope.requestUrl=site+"api/user/getMy";	
	$http.get($scope.requestUrl)
	.then(function(response) {
		$scope.user = response.data;
		$scope.quantityRecommendations=response.data.recommendations.length;
	});
	$scope.userRankUrl=site+"api/user/quantityUsersRank";
	$scope.topUsersUrl=site+"api/user/topUsersComments?quantity=10";	
});
app.controller("login",function($scope){
	$scope.loginFail=false;
	$scope.loginUser={};
	$scope.loginUser.email="";	
	$scope.login = function(){
		$scope.loginUser.password=CryptoJS.MD5($scope.password).toString();
		var requestUrl = site+"api/user/login";
		$.post(requestUrl, $scope.loginUser)
		.done(function(data, status, headers) {		 
			$(location).attr('href', site);	
		})				
		.fail(function(xhr, textStatus, errorThrown) {
			$scope.$apply(function() {
				$scope.loginFail=true;
			});
		});		
	};	
});
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
app.controller('myRestaurants', function($scope, $http){
	$scope.restaurants=[];
	$scope.requestUrl=site+"api/restaurant/userresponsible/myrestaurants";	
	$http.get($scope.requestUrl)
    .then(function(response) {
        $scope.restaurants = response.data;        
    });
});
app.controller('notificationsResponsible', function($scope){
	$scope.user={};
	$scope.notificationsNotSeen = [];
	$scope.notificationsSeen = [];
	$scope.requestUrl=site+"api/user/getMy";	
	$.get($scope.requestUrl)
    .done(function(response) {
        $scope.user = response;
        var j=0;
        var k=0;
        var notifications = $scope.user.notifications.reverse();
        var notificationsNotSeen = [];
        var notificationsSeen = [];

        for(var i = 0; i < notifications.length; i++){
            if(notifications[i].seen) {
            	notificationsSeen[j] = angular.copy(notifications[i]);
            	j++;
            }else{
            	notificationsNotSeen[k] = angular.copy(notifications[i]);
            	k++;
            }
        }
    	$scope.notificationsSeen = notificationsSeen;
    	$scope.notificationsNotSeen = notificationsNotSeen;
    	$('#tableNotSeen').bootstrapTable({
			data: notificationsNotSeen
		});    	
    	$('#tableSeen').bootstrapTable({
			data: notificationsSeen
		});        
    });	
});
app.controller("registerMenu",function($scope, $routeParams, $http){
	var idRest = $routeParams.idr;
	$scope.id = $routeParams.idr;
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
app.controller('restaurantList', function($scope, $http, $location){
	$scope.restaurants=[];
	$scope.requestUrl=site+"api/restaurant/list";	
	$http.get($scope.requestUrl)
	.then(function(response) {
		$scope.restaurants = response.data;
		$.each( response.data, function( key, value ) {
			$http.get('http://maps.googleapis.com/maps/api/geocode/json?latlng='+response.data[key].location.latitude+','+response.data[key].location.longitude+'&sensor=true')
			.then(function successCallback(response) {
				if(response.data.status=="ZERO_RESULTS"|response.data.status=="OVER_QUERY_LIMIT"){
					$scope.restaurants[key].location.name=String(locationFormatter($scope.restaurants[key].location)).replace(/&#176;/g,String.fromCharCode(176));
				}else{    						
					$scope.restaurants[key].location.name=response.data.results[0].formatted_address;    						
				}            	
			}, function errorCallback(response) {
				$scope.restaurants[key].location.name=String(locationFormatter($scope.restaurants[key].location)).replace(/&#176;/g,String.fromCharCode(176));
			});        	
		});
	});
});
app.controller("restaurantSearchComments",function($scope, $http){
	$scope.dataSearch={};
	$scope.restaurants=null;
	$scope.search = function(){
		$scope.dataSearch.initialDate=new Date($scope.initialDate).getTime();
		$scope.dataSearch.finalDate=new Date($scope.finalDate).getTime();
		$scope.requestUrl=site+"api/restaurant/topComments?initialDate="+$scope.dataSearch.initialDate+"&finalDate="+$scope.dataSearch.finalDate+"&quantity=50";
		$http.get($scope.requestUrl)
		.then(function successCallback(response) {
			$scope.restaurants = response.data;
			$.each( response.data, function( key, value ) {
				$http.get('http://maps.googleapis.com/maps/api/geocode/json?latlng='+response.data[key].location.latitude+','+response.data[key].location.longitude+'&sensor=true')
				.then(function successCallback(response) {
					if(response.data.status=="ZERO_RESULTS"|response.data.status=="OVER_QUERY_LIMIT"){
						$scope.restaurants[key].location.name=String(locationFormatter($scope.restaurants[key].location)).replace(/&#176;/g,String.fromCharCode(176));
					}else{    						
						$scope.restaurants[key].location.name=response.data.results[0].formatted_address;    						
					}            	
				}, function errorCallback(response) {
					$scope.restaurants[key].location.name=String(locationFormatter($scope.restaurants[key].location)).replace(/&#176;/g,String.fromCharCode(176));
				});        	
			});
		}, function errorCallback(response) {
			$scope.restaurants=[];
		});
	};
});
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
			$.each( response.data, function( key, value ) {
				$http.get('http://maps.googleapis.com/maps/api/geocode/json?latlng='+response.data[key].location.latitude+','+response.data[key].location.longitude+'&sensor=true')
				.then(function successCallback(response) {
					if(response.data.status=="ZERO_RESULTS"|response.data.status=="OVER_QUERY_LIMIT"){
						$scope.restaurants[key].location.name=String(locationFormatter($scope.restaurants[key].location)).replace(/&#176;/g,String.fromCharCode(176));
					}else{    						
						$scope.restaurants[key].location.name=response.data.results[0].formatted_address;    						
					}            	
				}, function errorCallback(response) {
					$scope.restaurants[key].location.name=String(locationFormatter($scope.restaurants[key].location)).replace(/&#176;/g,String.fromCharCode(176));
				});        	
			});

		});
	};
});
app.controller('searchName', function($scope, $routeParams, $http){
	$scope.name = $routeParams.name;
	if($scope.name){
		$scope.requestUrl=site+"api/restaurant/search?name="+$scope.name;
	}else{
		$scope.requestUrl=site+"api/restaurant/list";	
	}
	$http.get($scope.requestUrl)
	.then(function(response) {
		$scope.restaurants = response.data;
		$.each( response.data, function( key, value ) {
			$http.get('http://maps.googleapis.com/maps/api/geocode/json?latlng='+response.data[key].location.latitude+','+response.data[key].location.longitude+'&sensor=true')
			.then(function successCallback(response) {
				if(response.data.status=="ZERO_RESULTS"|response.data.status=="OVER_QUERY_LIMIT"){
					$scope.restaurants[key].location.name=String(locationFormatter($scope.restaurants[key].location)).replace(/&#176;/g,String.fromCharCode(176));
				}else{    						
					$scope.restaurants[key].location.name=response.data.results[0].formatted_address;    						
				}            	
			}, function errorCallback(response) {
				$scope.restaurants[key].location.name=String(locationFormatter($scope.restaurants[key].location)).replace(/&#176;/g,String.fromCharCode(176));
			});        	
		});
	});
	$scope.search= function() {
		$scope.requestUrl=site+"api/restaurant/search?name="+$scope.name;
		$http.get($scope.requestUrl)
		.then(function(response) {			
			$scope.restaurants = response.data;
			$.each( response.data, function( key, value ) {
				$http.get('http://maps.googleapis.com/maps/api/geocode/json?latlng='+response.data[key].location.latitude+','+response.data[key].location.longitude+'&sensor=true')
				.then(function successCallback(response) {
					if(response.data.status=="ZERO_RESULTS"|response.data.status=="OVER_QUERY_LIMIT"){
						$scope.restaurants[key].location.name=String(locationFormatter($scope.restaurants[key].location)).replace(/&#176;/g,String.fromCharCode(176));
					}else{    						
						$scope.restaurants[key].location.name=response.data.results[0].formatted_address;    						
					}            	
				}, function errorCallback(response) {
					$scope.restaurants[key].location.name=String(locationFormatter($scope.restaurants[key].location)).replace(/&#176;/g,String.fromCharCode(176));
				});        	
			});
		});
	};
});
app.controller("setUpNotification",function($scope,$http){
	$scope.config = {};
	$scope.config.visitor = false;
	$scope.config.commensal = false;
	$scope.config.gourmet = false;
	$scope.blockedRanking = [];
	$http.get(site+"api/user/getMy").then(function (response) {
		$scope.user = response.data;
		$scope.blockedRanking= response.data.blockedRanking;
		for (var i = 0; i < $scope.blockedRanking.length; i++) {
			var ranking = $scope.blockedRanking[i];
			switch(ranking) {
			case "VISITOR":
				$scope.config.visitor = true;
				break;
			case "COMMENSAL":
				$scope.config.commensal = true;
				break;
			case "GOURMET":
				$scope.config.gourmet = true;
			}		
		}
	});	
	$scope.submitConfiguration = function(){
		var requestUrl = site+"api/user/setUpNotification";
		$.post(requestUrl, $scope.config, $scope)
		.done(function(data, status, headers,$scope) {

			var blockeds = data.blockedRanking;
			var msj = "";
			for (var i = 0; i < blockeds.length; i++) {
				var ranking = blockeds[i];
				msj = msj + ranking + ' ';
			}
			swal({
				title: 'Listo!',
				text: 'Los datos fueron guardados correctamente, se bloqueo a '+msj ,
				type: 'success',
			}).then(
					function () {
						$(location).attr('href', site);
					}
			)
		})				
		.fail(function(xhr, textStatus, errorThrown) {
			swal("Error!", "No se ha podido guardar los cambios en el sistema.", "error");
		});		
	};
});
app.controller("showmenu",function($scope, $routeParams, $http) {
	var idRest = $routeParams.idRe;
	var idMenu = $routeParams.idMe;
	$scope.menu=[];
	$scope.user = {};
	$scope.recomendation = {};
	$scope.recomendation.idMenu = idMenu;
	$scope.recomendation.email = "";
	$scope.recomendation.text = "";	
	$http.get(site+"api/restaurant/showMenu/"+idRest+"/"+idMenu)
	.then(function (response) {
		$scope.menu = response.data;
		var c = $scope.menu.comments
		for (var i = 0; i < c.length; i++) {
			switch(c[i].vote) {
			case "POSITIVE":
				$scope.menu.comments[i].vote = "thumbs-up";
				break;
			case "NEUTRAL":
				$scope.menu.comments[i].vote = "hand-up";
				break;
			case "NEGATIVE":
				$scope.menu.comments[i].vote = "thumbs-down";
			}
			if(!$scope.menu.comments[i].user.enable){
				$scope.menu.comments[i].user.name="Anonimo";
			}
		}
	});
	$http.get(site+"api/user/getMy")
	.then(function (response) {
		$scope.user= response.data;
	});
	$scope.recommendMenuFriends = function(){
		if ( $scope.menu.restaurant.idCurrentMenu != idMenu) {
			swal({
				title: 'Error!',
				text: 'No se puede recomendar este men&uacute;, no esta activo.',
				type: 'error',
			}).then(
					function () {
						$("#recommendFriendsModal").modal('hide');
					})
		}else {
			var requestUrl = site+"api/user/recommendFriends";
			$.post(requestUrl, {idMenu: $scope.recomendation.idMenu, text: $scope.recomendation.text},$scope)
			.done(function(data, status, headers,$scope) {
				swal({
					title: 'Listo!',
					text: 'Se ha recomendado el men&uacute; a todos tus amigos',
					type: 'success',
				}).then(
						function () {
							location.reload();
						})
			})				
			.fail(function(xhr, textStatus, errorThrown) {
				$scope.$apply(function() {
					swal("Error!", "No se ha recomendado el men&uacute;", "error");
				});
			});					
		}
	};	
	$scope.recommendMenuFriend = function(){
		if ( $scope.menu.restaurant.idCurrentMenu != idMenu) {
			swal({
				title: 'Error!',
				text: 'No se puede recomendar este men&uacute;, no esta activo.',
				type: 'error',
			}).then(
					function () {
						$("#recommendFriendModal").modal('hide');
					})
		}else {
			var email = $scope.recomendation.email;
			if ($scope.user.mail === email) {
				$scope.recomendation.email = "";
				$scope.recomendation.text = "";
				swal("Error!", "No puede recomendarse el men&uacute; a s&iacute; mismo", "error");
			}else{		
				var requestUrl = site+"api/user/recommendFriend";
				$.post(requestUrl, $scope.recomendation, $scope)
				.done(function(data, status, headers,$scope) {
					swal({
						title: 'Listo!',
						html: 'Se ha recomendado el men&uacute; a tu amigo ' + email,
						type: 'success',
					}).then(
							function () {
								location.reload();
							}
					)		
				})				
				.fail(function(xhr, textStatus, errorThrown) {
					$scope.$apply(function() {
						swal("Error!", "No se ha recomendado el men&uacute;", "error");
					});
				});		
			}	
		}
	};
});
app.controller("showrestaurant",function($scope, $routeParams, $http) {
	var idRes = $routeParams.idRes;
	$scope.restaurant=[];
	$scope.vote="";
	$http.get(site+"api/restaurant/showRestaurant/"+idRes)
	.then(function (response) {
		$scope.restaurant = response.data;
		var c = $scope.restaurant.comments
		for (var i = 0; i < c.length; i++) {
			switch(c[i].vote) {
			case "POSITIVE":
				$scope.restaurant.comments[i].vote = "thumbs-up";
				break;
			case "NEUTRAL":
				$scope.restaurant.comments[i].vote = "hand-up";
				break;
			case "NEGATIVE":
				$scope.restaurant.comments[i].vote = "thumbs-down";
			}
			if(!$scope.restaurant.comments[i].user.enable){
				$scope.restaurant.comments[i].user.name="Anonimo";
			}
		}
		var mapShowRestaurant = new google.maps.Map(document.getElementById('mapShowRestaurant'), {
			center: {lat: $scope.restaurant.location.latitude, lng: $scope.restaurant.location.longitude},
			zoom: 14
		});
		var marker = new google.maps.Marker({
			position: new google.maps.LatLng($scope.restaurant.location.latitude, $scope.restaurant.location.longitude),
			map: mapShowRestaurant
		});
	});
});
app.controller("registerUser",function($scope){
	$scope.user={};
	$scope.user.name="";
	$scope.user.lastName="";
	$scope.user.email="";	
	$scope.submitUser = function(){
		$scope.user.password=CryptoJS.MD5($scope.password).toString();
		$scope.user.password2=CryptoJS.MD5($scope.password2).toString();
		$scope.user.latitude=markersUser[0].position.lat();
		$scope.user.longitude=markersUser[0].position.lng();
		var requestUrl = site+"api/user/registerUser";
		$.post(requestUrl, $scope.user,$scope)
		.done(function(data, status, headers,$scope) {
			swal({
				title: 'Felicidades!',
				text: 'Usuario '+data.mail+' has sido registrado',
				type: 'success',
			}).then(
					function () {
						$(location).attr('href', site);
					}
			)
		})				
		.fail(function(xhr, textStatus, errorThrown) {
			$scope.$apply(function() {
				swal({
					title: 'Error!',
					text: 'No has sido registrado',
					type: 'error',
				})
			});
		});
	};
});
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
		fd.append('password',CryptoJS.MD5($scope.user.password).toString());
		fd.append("password2",CryptoJS.MD5($scope.user.password2).toString());
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
						$(location).attr('href', site);
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
app.controller('viewNotification', function($scope, $routeParams, $http){	
	$scope.id = $routeParams.id;
	$scope.notification = {};
	$scope.requestUrl = "";
	$http.get(site+"api/user/markAsSeen/"+$scope.id)
	.then(function (response) {
		$scope.notification = response.data;
	});	
	$scope.reloadRoute = function() {
		$(location).attr('href', site+"#!/viewNotifications");
		location.reload();
	};			
	$scope.redirectToView = function(){
		var name = "";
		var id = null;
		id = $scope.notification.comment.commenting.id;
		name = $scope.notification.comment.commenting.name;
		if (name == "Menu") {
			$scope.getMenu(id);
		} else {
			$scope.getRestaurant(id);
		}
	};	
	$scope.getMenu = function(id){
		var requestUrl = site+"api/restaurant/getMenu/"+id;
		$http.get(requestUrl)
		.then(function (response) {
			var menu = {};
			menu = response.data;
			var idRest = null;
			idRest = menu.restaurant.id;
			$(location).attr('href', site+"#!/showMenu/"+idRest+"/"+id);
		});		
	}	
	$scope.getRestaurant = function(id){
		var requestUrl = site+"api/restaurant/showRestaurant/"+id;
		$http.get(requestUrl)
		.then(function (response) {
			var restaurant = {};
			restaurant = response.data;
			$(location).attr('href', site+"#!/showRestaurant/"+restaurant.id);
		});		
	};
});
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
		
		var data={name: $scope.dataUser.name, lastName: $scope.dataUser.lastName , latitude:$scope.dataUser.latitude, longitude:$scope.dataUser.longitude};
		$.post(requestUrl, data)
		.done(function(data, status, headers) {
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