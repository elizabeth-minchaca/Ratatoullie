var markersResponsible = [];
var mapResponsible;
var markersRestaurant = [];
var mapRestaurant;	

function initMap() {
	mapResponsible = new google.maps.Map(document.getElementById('mapResponsible'), {
		center: {lat: -34.9215175038, lng: -57.95360565301},
		zoom: 13
	});
	mapRestaurant = new google.maps.Map(document.getElementById('mapRestaurant'), {
		center: {lat: -34.9215175038, lng: -57.95360565301},
		zoom: 13
	});
	placeMarker(new google.maps.LatLng(-34.92151750383363, -57.95360565301962), mapResponsible);
	placeMarker2(new google.maps.LatLng(-34.92151750383363, -57.95360565301962), mapRestaurant);
	mapResponsible.addListener('click', function(e) {
		placeMarker(e.latLng, mapResponsible);
	});
	mapRestaurant.addListener('click', function(e) {
		placeMarker2(e.latLng, mapRestaurant);
	});

	function placeMarker(position, map) {
		setMapOnAll(null);
		markersResponsible=[];
		var marker = new google.maps.Marker({
			position: position,
			map: map
		});
		markersResponsible.push(marker);
	}
	function placeMarker2(position, map) {
		setMapOnAll2(null);
		markersRestaurant=[];
		var marker = new google.maps.Marker({
			position: position,
			map: map
		});
		markersRestaurant.push(marker);
	}
	
	function setMapOnAll(map) {
		for (var i = 0; i < markersResponsible.length; i++) {
			markersResponsible[i].setMap(map);
		}
	}
	function setMapOnAll2(map) {
		for (var i = 0; i < markersRestaurant.length; i++) {
			markersRestaurant[i].setMap(map);
		}
	}
	// Try HTML5 geolocation.
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var pos = {
					lat: position.coords.latitude,
					lng: position.coords.longitude
			};
			placeMarker(pos, mapResponsible);
			placeMarker2(pos, mapRestaurant);
			mapResponsible.setCenter(pos);
			mapRestaurant.setCenter(pos);
		});
	}
}
$(function(){
	initMap();
});