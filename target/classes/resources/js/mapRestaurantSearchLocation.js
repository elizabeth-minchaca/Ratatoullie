var markersRS;
var mapRS;
var circleRs;
function initMap() {
	mapRS = new google.maps.Map(document.getElementById('map'), {
		center: {lat: -34.9215175038, lng: -57.95360565301},
		zoom: 13
	});
	markersRS = new google.maps.Marker({
		position: mapRS.getCenter(),
		map: mapRS,
	});
	circleRs = new google.maps.Circle({
        strokeColor: '#0040ff',
        strokeOpacity: 0.8,
        strokeWeight: 2,
        fillColor: '#0040ff',
        fillOpacity: 0.35,
        map: mapRS,
        center: markersRS.getPosition(),
        radius: parseInt($( '#distance').val())	
      });

	mapRS.addListener('click', function(e) {
		markersRS.setPosition(e.latLng);
		circleRs.setCenter(e.latLng);
	});
	circleRs.addListener('click', function(e) {
		markersRS.setPosition(e.latLng);
		circleRs.setCenter(e.latLng);
	});
	
	// Try HTML5 geolocation.
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var pos = {
					lat: position.coords.latitude,
					lng: position.coords.longitude
			};
			markersRS.setPosition(pos);
			circleRs.setCenter(pos);
			mapRS.setCenter(pos);
		});
	} 
}	
$(function(){
	initMap();
	$( '[name=distance]').change(function() {		
		circleRs.setRadius(parseInt($('#distance').val()));
	});
});