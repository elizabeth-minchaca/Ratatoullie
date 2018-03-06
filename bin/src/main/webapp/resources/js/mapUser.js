var markersUser = [];
var mapUser;
function initMap() {
	mapUser = new google.maps.Map(document.getElementById('mapUser'), {
		center: {lat: -34.9215175038, lng: -57.95360565301},
		zoom: 13
	});
	placeMarker(new google.maps.LatLng(-34.92151750383363, -57.95360565301962), mapUser);
	mapUser.addListener('click', function(e) {
		placeMarker(e.latLng, mapUser);
	});

	function placeMarker(position, map) {
		setMapOnAll(null);
		markersUser=[];
		var marker = new google.maps.Marker({
			position: position,
			map: map
		});
		markersUser.push(marker);
	}	
	function setMapOnAll(map) {
		for (var i = 0; i < markersUser.length; i++) {
			markersUser[i].setMap(map);
		}
	}
	// Try HTML5 geolocation.
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var pos = {
					lat: position.coords.latitude,
					lng: position.coords.longitude
			};
			placeMarker(pos, mapUser);	
			mapUser.setCenter(pos);
		});
	}
}
$(function(){
	initMap();
});