function dateFormatter(value) {
	if(value){
		return new Date(value).toLocaleDateString();
	}else{
		return "Sin fecha";		
	}    
}
function locationFormatter(value) {	
	var latitude=convertDDToDMS(value.latitude,false);
	var longitude=convertDDToDMS(value.longitude,true)
	return toCoordinates(latitude)+", "+toCoordinates(longitude); 
}
function toCoordinates(value) {
	return value.dir+" "+value.deg+"&#176; "+value.min+"' "+value.sec+"''";
}
function convertDDToDMS(deg, lng){
    var d = parseInt(deg);
    var minfloat  = Math.abs((deg-d) * 60); 
    var m = Math.floor(minfloat);
    var secfloat = (minfloat-m)*60;
    var s = Math.round(secfloat); 
    d = Math.abs(d);

    if (s==60) {
        m++;
        s=0;
    }
    if (m==60) {
        d++;
        m=0;
    }

    return {
        dir : deg<0?lng?'O':'S':lng?'E':'N',
        deg : d,
        min : m,
        sec : s
    };
}
function restaurantFormatter(value){	
	return '<a title="VER" href="#!viewRestaurant/'+value+'"> <span class="glyphicon glyphicon-eye-open active"></span> </a> <a title="EDITAR" href="#!editRestaurant/'+value+'"> <span class="glyphicon glyphicon-edit active"></span> </a>';
}
function restaurantDisableFormatter(value){	
	return '<a title="VER" href="#!viewRestaurant/'+value+'"> <span class="glyphicon glyphicon-eye-open active"></span> </a>';
}
function notificationFormatter(value){	
	return '<a title="VER" href="#!viewNotification/'+value+'"> <span class="glyphicon glyphicon-eye-open active"></span> </a>';
}