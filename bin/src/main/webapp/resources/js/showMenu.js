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
				    	$scope.menu.comments[i].vote = "POSITIVO";
				        break;
				    case "NEUTRAL":
				    	$scope.menu.comments[i].vote = "NEUTRO";
				        break;
				    case "NEGATIVE":
				    	$scope.menu.comments[i].vote = "NEGATIVO";
				}
			}
		});
	$http.get(site+"api/user/getMy")
	.then(function (response) {
		$scope.user= response.data;
	});

	$scope.recommendMenuFriends = function(){
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
		})
		;		
	};
	
	$scope.recommendMenuFriend = function(){
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
	};
});