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

	.when("/register", {templateUrl:site+"core/user/register"})
	.when("/registerResponsible", {templateUrl:site+"core/user/registerResponsible"})
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