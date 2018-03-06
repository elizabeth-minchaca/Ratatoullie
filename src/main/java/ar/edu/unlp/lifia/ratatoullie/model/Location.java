package ar.edu.unlp.lifia.ratatoullie.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Location on the map.
 * @author NATA
 *
 */
@Entity
@Table(name="location")
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "column_id")
	private Long id;
	
	@Column(name = "latitude")
	private double latitude;
	
	@Column(name = "longitude")
	private double longitude;
	
	public Location() {	
	}
	public Location(double latitude,double longitude) {
		setLatitude(latitude);
		setLongitude(longitude);
	}	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	/**
	 * Calculates the distance in meters to the {@link Location} sent as a parameter.
	 * @param location
	 * @return
	 */
	public double getDistance(Location location){
		 //double radioTierra = 3958.75;//in miles.
        double earthRadio = 6371000; //kms
        double latitude = Math.toRadians(location.getLatitude() - getLatitude());  
        double longitude = Math.toRadians(location.getLongitude() - getLongitude());  
        double sindLatitude = Math.sin(latitude / 2);  
        double sindLongitude = Math.sin(longitude / 2);  
        double va1 = Math.pow(sindLatitude, 2) + Math.pow(sindLongitude, 2)  
                * Math.cos(Math.toRadians(getLatitude())) * Math.cos(Math.toRadians(location.getLatitude()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distance = earthRadio * va2;  
   
        return distance;  		
		
	}
	@Override
	public boolean equals(Object obj) {		
		return getLatitude()==((Location)obj).getLatitude()
				&& getLongitude()==((Location)obj).getLongitude();
	}
	@Override
	public int hashCode() {
		return Objects.hash(getLatitude(),getLongitude());
	}
}