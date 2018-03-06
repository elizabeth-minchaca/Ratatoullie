var markersRegistRestaurant = [];
var mapRegistRestaurant;
function initMap() {
	mapRegistRestaurant = new google.maps.Map(document.getElementById('mapRegistRestaurant'), {
		center: {lat: -34.9215175038, lng: -57.95360565301},
		zoom: 13
	});
	placeMarker(new google.maps.LatLng(-34.92151750383363, -57.95360565301962), mapRegistRestaurant);
	mapRegistRestaurant.addListener('click', function(e) {
		placeMarker(e.latLng, mapRegistRestaurant);
	});

	function placeMarker(position, map) {
		setMapOnAll(null);
		markersRegistRestaurant=[];
		var marker = new google.maps.Marker({
			position: position,
			map: map
		});
		markersRegistRestaurant.push(marker);
	}
	
	function setMapOnAll(map) {
		for (var i = 0; i < markersRegistRestaurant.length; i++) {
			markersRegistRestaurant[i].setMap(map);
		}
	}

	// Try HTML5 geolocation.
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var pos = {
					lat: position.coords.latitude,
					lng: position.coords.longitude
			};
			placeMarker(pos, mapRegistRestaurant);	
			mapRegistRestaurant.setCenter(pos);
		});
	}
}
$(function(){
	initMap();
});