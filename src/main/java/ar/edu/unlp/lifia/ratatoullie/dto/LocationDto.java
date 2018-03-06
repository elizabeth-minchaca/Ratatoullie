package ar.edu.unlp.lifia.ratatoullie.dto;

public class LocationDto {
	private double latitude;
	private double longitude;
	public LocationDto(){
	}
	public LocationDto(double latitude, double longitude){
		setLatitude(latitude);
		setLongitude(longitude);
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
